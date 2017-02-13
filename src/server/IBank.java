package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import exception.InvalidLogin;
import exception.InvalidSession;

public interface IBank extends Remote{
	
	public long login(String username, String password) throws RemoteException, InvalidLogin;

	public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

	public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

	public double inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession;

	public IStatement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession;



}
