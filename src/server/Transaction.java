package server;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

	
	private Date date;
	private int accountnum;
	private boolean transactionType;
	
	public Transaction(int accNum, boolean type)
	{
		this.accountnum = accNum;
		this.transactionType = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(int accountnum) {
		this.accountnum = accountnum;
	}

	public boolean isTransactionType() {
		return transactionType;
	}

	public void setTransactionType(boolean transactionType) {
		this.transactionType = transactionType;
	}
	
	
}
