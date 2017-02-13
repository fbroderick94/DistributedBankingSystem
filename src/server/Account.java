package server;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private int accIncrementer = 10000;
	private String firstname, surname, password, username;
	private int accountNum = 0;
	private double balance = 0;
	private String sessionID;
	private List<Transaction> transactions;
	
	public Account(String fname, String sname, String pass)
	{
		this.firstname = fname;
		this.surname = sname;
		this.password = pass;
		this.transactions = new ArrayList<Transaction>();
		this.username = firstname.charAt(0) + surname;
		this.accountNum = accIncrementer;
		accIncrementer++;
	}
	
	
	public String getUsername()
	{
		return this.username;
	}
	
	public double getBal()
	{
		return this.balance;
	}
	
	public void setBal(double bal)
	{
		this.balance = bal;
	}
	
	public int getAccountNum()
	{
		return this.accountNum;
	}
	
	public String getSessionID(){
		return this.sessionID;
	}
	
	public void setSessionID(String id)
	{
		this.sessionID = id;
	}
	
	public List<Transaction> getTransactions()
	{
		return this.transactions;
	
	}
	
	public boolean isPasswordValid(String pass)
	{
		if(pass == this.password){
			return true;
		}
		else{
			return false;
		}
	}
}
