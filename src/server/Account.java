package server;

public class Account {

	private int accIncrementer = 10000;
	private String firstname, surname, password, username;
	private int accountNum = 0;
	private double balance = 0;
	
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
