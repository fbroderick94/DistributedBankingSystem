package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.InvalidLogin;
import exception.InvalidSession;
import interfaces.IBank;
import interfaces.IStatement;

public class Bank extends UnicastRemoteObject implements IBank {


	private List<Account> Users;
	private List<Session> sessions;
	private boolean isAuthenticated = false;
	
	public Bank() throws RemoteException
	{
		super();
		Users = new ArrayList<>();
		sessions = new ArrayList<>();
		
		Account acc1 = new Account("Joe", "Bloggs", "123");
		Account acc2 = new Account("Mary", "Smith", "123");
		Account acc3 = new Account("Tom", "Kelly", "123");
		
		System.out.println("Acc Num: " + acc1.getAccountNum() + "Username: " + acc1.getUsername());
		System.out.println("Acc Num: " + acc2.getAccountNum() + "Username: " + acc2.getUsername());
		Users.add(acc1);
		Users.add(acc2);
		Users.add(acc3);
	}
	
	public static void main(String args[]) throws Exception 
	{
		try
		{	
			System.setProperty("java.security.policy", "file:/Users/fergalbroderick/Documents/workspace/DistributedBankingSystem/src/server.policy");
            System.setSecurityManager(new SecurityManager());
            System.out.println("Security Manager Set");
            
            

			String name = "Bank";
            IBank b = new Bank();
           
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(args[0]));
            registry.rebind(name, b);
	        System.out.println("Bank bound");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		

	}

	
	@Override
	public long login(String username, String password) throws RemoteException, InvalidLogin {
		
		for(int i=0; i<Users.size();i++)
		{
			if(Users.get(i).getUsername().equals(username))
			{
				if(Users.get(i).isPasswordValid(password))
				{
					isAuthenticated = true;
					System.out.println(username + " has successfully logged in. Your session will expire in 5 minutes!");
					String sID = UUID.randomUUID().toString();
					Users.get(i).setSessionID(sID);
					Session session = new Session(sID);
					sessions.add(session);
					return Long.parseLong(sID);
				}
				else{
					throw new InvalidLogin();
				}
					
			}
		}
		throw new InvalidLogin();
	}

	@Override
	public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		Account acc = Users.get(getAccount(accountnum));
		
		if(isAuthenticated && checkActiveSession(acc))
		{
			double currentBalance = acc.getBal() + amount;
			acc.setBal(currentBalance);
			System.out.println("€" + amount + " deposited to account " + accountnum);
			
		}

	}

	@Override
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		
		Account acc = Users.get(getAccount(accountnum));
		
		if(isAuthenticated && checkActiveSession(acc))
		{
			if(acc.getBal() > 0 && (acc.getBal()-amount)>0)
			{
				double currentBalance = acc.getBal() - amount;
				acc.setBal(currentBalance);
				System.out.println("€" + amount + " withdrawn from account " + accountnum);
			}
			else
				System.out.println("Insufficient Funds");
		}
	}

	@Override
	public double inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		
		Account acc = Users.get(getAccount(accountnum));
		
		if(isAuthenticated && checkActiveSession(acc))
		{
			return acc.getBal();
		}
		return 0;
	}


	@Override
	public IStatement getStatement(int accountnum, Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
		Account acc = Users.get(getAccount(accountnum));
		
		if(isAuthenticated && checkActiveSession(acc))
		{	
			Statement statement = new Statement(acc, from, to);
			return statement;
		}
		
		throw new InvalidSession();
	}
	
	
	public int getAccount(int accountNum)
	{
		int position=-1;
		for(int i=0; i<Users.size(); i++)
		{
			if(Users.get(i).getAccountNum() == accountNum)
			{
				position = i;
			}
		}
		return position;
	}
	
	public boolean checkActiveSession(Account acc) throws InvalidSession
	{
		String sID = acc.getSessionID();
		
		for(int i=0; i<sessions.size();i++)
		{
			if(sessions.get(i).getID().equals(sID) && sessions.get(i).isSessionAlive())
			{
				System.out.println("Time Remaining: " + (sessions.get(i).getMaxTime()-sessions.get(i).getTimeAlive()));
				return true;
			}
			else if(!sessions.get(i).isSessionAlive())
			{
				sessions.remove(i);
			}
		}
		
		throw new InvalidSession();
	}
	
	
	
	
		

}
