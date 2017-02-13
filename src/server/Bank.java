package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.Date;
import java.util.List;

import exception.InvalidLogin;
import exception.InvalidSession;

public class Bank extends UnicastRemoteObject implements IBank {

	private List<Account> Users;
	private boolean isAuthenticated = false;
	
	
	
	
	public Bank() throws RemoteException
	{
		super();
	}
	
	@Override
	public long login(String username, String password) throws RemoteException, InvalidLogin {
		
		for(int i=0; i<Users.size();i++)
		{
			if(Users.get(i).getUsername() == username)
			{
				if(Users.get(i).isPasswordValid(password))
				{
					isAuthenticated = true;
					String sID = UUID.randomUUID().toString();
					//System.out.println("uuid = " + sID);
					Session session = new Session(sID);
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
		if(isAuthenticated)
		{
			Account acc = Users.get(getAccount(accountnum));
			double currentBalance = acc.getBal() + amount;
			acc.setBal(currentBalance);
		}

	}

	@Override
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		if(isAuthenticated)
		{
			Account acc = Users.get(getAccount(accountnum));
			double currentBalance = acc.getBal() - amount;
			acc.setBal(currentBalance);
		}
	}

	@Override
	public double inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		if(isAuthenticated)
		{
			Account acc = Users.get(getAccount(accountnum));
			return acc.getBal();
		}
		return 0;
	}


	@Override
	public IStatement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	public static void main(String args[]) throws Exception 
	{

		
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		
		
		
		try
		{
			
			
			IBank b = new Bank();
			IBank stub = (IBank) UnicastRemoteObject.exportObject(b,0);
			Registry registry = LocateRegistry.getRegistry();
	        registry.rebind("Bank", stub);
	        System.out.println("Bank bound");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		

	}

		

}
