package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import exception.InvalidLogin;
import exception.InvalidSession;
import server.Bank;
import server.IBank;
import server.IStatement;


public class ATM {

	private static long sessionID = 0;
	private static IBank bank;
	
	public static void main(String args[]){
		
		System.setProperty("java.security.policy", "file:/Users/fergalbroderick/Documents/workspace/DistributedBankingSystem/src/client.policy");
        System.setSecurityManager(new SecurityManager());
		
		int accNum = 0;
		int value = 0;
		try{
			
			String name = "Bank";
			Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			bank = (Bank) registry.lookup(name);
			System.out.println("*****Client Connected******");
			
		}
		catch(Exception ex)
		{
			System.err.println("ATM Exeception: ");
			ex.printStackTrace();
		}
			
		
		if(args[2]=="login")
			{
				String username = args[3];
				String password = args[4];
				try {
					sessionID = bank.login(username, password);
				} catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (InvalidLogin e) {
					
					System.out.println(e.getMessage());
				}
				
				
				System.out.println(username + " has successfully logged in. Your session will expire in 5 minutes!");
			}
			else if(args[2]=="deposit")
			{
				
				accNum = Integer.parseInt(args[3]);
				value = Integer.parseInt(args[4]);
				
				try {
					bank.deposit(accNum, value, sessionID);
				} catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (InvalidSession e) {
					
					System.out.println(e.getMessage());
				}
				
				
				
			}
			else if(args[2]=="withdraw")
			{
			
				accNum = Integer.parseInt(args[3]);
				value = Integer.parseInt(args[4]);
				
				try {
					bank.withdraw(accNum, value, sessionID);
				} catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (InvalidSession e) {
					
					System.out.println(e.getMessage());
				}
				
				
			}
			else if(args[2]=="inquiry")
			{
	
				accNum = Integer.parseInt(args[3]);
				
				
				double balance=0.0;
				try {
					balance = bank.inquiry(accNum, sessionID);
					
				} catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (InvalidSession e) {
					
					System.out.println(e.getMessage());
				}
				
				System.out.println("Account No. " + accNum + " has a balance of €" + balance);
			}
			else if(args[2]=="statement")
			{
				accNum = Integer.parseInt(args[3]);
				
				Date from = new Date(args[4]);
				Date to = new Date(args[5]);
				
				try {
					IStatement statement = bank.getStatement(accNum, from, to, sessionID);
					System.out.println("Statement for Account " + accNum);
					System.out.println("-------------------------------------");
					System.out.println("From " + from + " to " + to);
					for (Object s : statement.getTransactions())
					{
						System.out.println(s);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InvalidSession e) {
					System.out.println(e.getMessage());
				}
			}
			else
			{
				System.out.println("Unknown operation");
			}
			
			
			
		
	}
}
