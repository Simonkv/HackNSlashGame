package HackNSlash;

import java.awt.Graphics2D;

public class FPScalculator {
	
	private GamePanel gamePanel;
	private long startTime;
	private long endTime;
	private long diff;
	private int sleepTimeInt;
	
	public FPScalculator(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}
	
	public void startTimer(){
		startTime = System.currentTimeMillis();
		
	}
	
	public void endTimer(){
		endTime = System.currentTimeMillis();
		diff = endTime - startTime;
	}
	
	public int getSleepTime(){
		long sleepTimeLong = (1000/25)-diff;
		sleepTimeInt = (int) sleepTimeLong;
		
		return sleepTimeInt;
	}
	public void paint(Graphics2D g){
		String diffString = Integer.toString(sleepTimeInt);
		g.drawString(diffString, 10, 10);
	}
	

}
