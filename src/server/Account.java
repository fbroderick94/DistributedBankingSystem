package server;

public class Account {

	private int accIncrementer = 10000;
	private String firstname, surname, password, username;
	private int accountNum = 0;
	private int balance = 0;
	
	public Account(String fname, String sname, String pass)
	{
		this.firstname = fname;
		this.surname = sname;
		this.password = pass;
		this.username = firstname.charAt(0) + surname;
		this.accountNum = accIncrementer;
		accIncrementer++;
	}
	
	
	public String getUsername()
	{
		return this.username;
	}
	
	public int getBal()
	{
		return this.balance;
	}
	
	public int getAccountNum()
	{
		return this.accountNum;
	}
}
