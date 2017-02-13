package server;

import java.util.Timer;
import java.util.TimerTask;

public class Session extends TimerTask
{
	private String ID;
	private Timer timer;
	private static final int MAX_TIME = 300;
	private int timeElapsed; 
	private boolean isAlive;
	
	public Session(String SessionID)
	{
		this.ID = SessionID;
		this.timer = new Timer();
		this.timeElapsed = 0;
		this.isAlive = true;
		startTimer();
		
	}

	public void startTimer()
	{
		this.timer.scheduleAtFixedRate(this, System.currentTimeMillis(), 1000);
	}
	
	@Override
	public void run() {
		this.timeElapsed++;
		if(isSessionExpired())
		{
			this.isAlive = false;
			this.timer.cancel();
		}
	}
	
	public boolean isSessionExpired()
	{
		if(this.timeElapsed == MAX_TIME)
			return true;
		else
			return false;
	}
	
	
}
