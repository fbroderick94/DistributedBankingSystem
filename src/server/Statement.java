package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Statement implements IStatement, Serializable {

	
	private List<Transaction> transactions;
	private Account account;
	private Date startDate, endDate;
	
	public Statement(Account acc, Date start, Date end)
	{
		this.transactions = new ArrayList<Transaction>();
		this.account = acc;
		this.startDate = start;
		this.endDate = start;
		
	}
	@Override
	public int getAccountNum() {
		return this.account.getAccountNum();
	}

	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	@Override
	public Date getEndDate() {
		return this.endDate;
	}

	@Override
	public String getAccountName() {
		return this.account.getUsername();
	}

	@Override
	public List<Transaction> getTransactions() {
		List<Transaction> t = this.account.getTransactions();
		
		for(int i=0; i<t.size();i++)
		{
			if((t.get(i).getDate().equals(this.getStartDate()) || t.get(i).getDate().after(this.getStartDate())) && (t.get(i).getDate().equals(this.getEndDate()) || t.get(i).getDate().before(this.getEndDate()))){
				transactions.add(t.get(i));
			}
		}
		return this.transactions;
	}

}
