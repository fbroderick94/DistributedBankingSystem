package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import exception.InvalidSession;
import server.Bank;
import server.IBank;


public class ATM {

	private static int sessionID = 0;
	private static IBank bank;
	
	public static void main(String args[]){
		
		System.setSecurityManager(new SecurityManager());
		
		int accNum = 0;
		int value = 0;
		try{
			
			String name = "Bank";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			bank = (Bank) registry.lookup(name);
			
			
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
				//sessionID = bank.login(username, password)
				
				
				System.out.println(username + " has successfully logged in.");
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
					
					e.printStackTrace();
				}
				
				System.out.println("€" + value + " deposited from account " + accNum);
				
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
					
					e.printStackTrace();
				}
				
				System.out.println("€" + value + " withdrawn from account " + accNum);
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
					
					e.printStackTrace();
				}
				
				System.out.println("Account No. " + accNum + " has a balance of €" + balance);
			}
			else if(args[2]=="statement")
			{
	
			}
			else
			{
				System.out.println("Unknown operation");
			}
			
			
			
		
	}
}
