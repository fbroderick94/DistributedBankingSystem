package exception;

public class InvalidSession extends Exception {
	
	public InvalidSession()
	{
		super("Your session has expired, Please login again!");
	}

}
