package server;

import java.util.Timer;
import java.util.TimerTask;

public class Session extends TimerTask
{
	private String ID;
	private Timer timer;
	private final int MAX_TIME = 300;
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
		if(!isSessionAlive())
		{
			this.isAlive = false;
			this.timer.cancel();
		}
	}
	
	public boolean isSessionAlive()
	{
		if(this.timeElapsed == MAX_TIME)
			return false;
		else
			return true;
	}
	
	public String getID(){
		return this.ID;
	}
	
	public int getMaxTime(){
		return this.MAX_TIME;
	}
	
	public int getTimeAlive(){
		return this.timeElapsed;
	}
	
	
}
