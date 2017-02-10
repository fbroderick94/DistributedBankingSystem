package server;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IStatement extends Serializable {

	public int getAccountNum();
	public Date getStartDate();
	public Date getEndDate();
	public String getAccountName();
	public List getTransactions();
}
