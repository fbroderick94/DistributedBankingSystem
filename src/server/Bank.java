package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import exception.InvalidLogin;
import exception.InvalidSession;

public class Bank extends UnicastRemoteObject implements IBank {

	private List<Account> Users;
	public Bank() throws RemoteException
	{
		super();
	}
	
	@Override
	public long login(String username, String password) throws RemoteException, InvalidLogin {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub

	}

	@Override
	public int inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public IStatement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]) throws Exception 
	{

		
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		// initialise Bank server - see sample code in the notes for details

	}

		

}
