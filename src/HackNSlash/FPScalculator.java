package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;

public class FPScalculator {
	
	private GamePanel gamePanel;
	private long startTime;
	private long endTime;
	private long diff;
	private int sleepTimeInt;
	private long sleepTimeLong;
	
	public FPScalculator(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}
	
	public void startTimer(){
		startTime = System.currentTimeMillis();
		
	}
	public int calculateFPS(){
		return (int)(1000/(sleepTimeLong+diff));
	}
	
	public void endTimer(){
		endTime = System.currentTimeMillis();
		diff = endTime - startTime;
	}
	
	public int getSleepTime(){
		sleepTimeLong = (1000/25)-diff;
		sleepTimeInt = (int) sleepTimeLong;
		
		return sleepTimeInt;
	}
	public void paint(Graphics2D g){
		String diffString = "FPS: "+Integer.toString(calculateFPS());
		g.setColor(Color.RED);
		g.drawString(diffString, 10, 15);
	}
	

}
