package exception;

public class InvalidLogin extends Exception {

	public InvalidLogin(){
		super("Your login credentials are invalid");
	}
}
