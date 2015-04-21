package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



public class WaveTimer {

public GamePanel panel;
public boolean started=false;
private long startTime;
private String timerString="0";
private String timeText = "Time:";

private int timerFrameWidth = 140;
private int timerFrameHeight = 40;
private int timerFrameXPos = panel.SCREEN_WIDTH - 180;
private int timerFrameYPos = 4;
Rectangle2D.Double timerFrame = new Rectangle2D.Double(timerFrameXPos, timerFrameYPos, timerFrameWidth, timerFrameHeight);

public int seconds;

public WaveTimer(GamePanel panel){
        this.panel = panel;
}

public void updateTime(){
		if(!started){
			 startTime = System.currentTimeMillis();
			 started = true;
		}
        long timerLong = System.currentTimeMillis()-startTime;
        seconds = (int) (timerLong/1000);
        timerString = Integer.toString(seconds);

}

public void update(){
        updateTime();
}

public void paint(Graphics2D g){
        g.setColor(Color.GRAY);
        g.fill(timerFrame);
        g.setColor(Color.WHITE);
        g.setFont(new Font ("Times New Roman", Font.BOLD, 25));
        g.drawString(timeText, 1025, 33);
        g.setFont(new Font ("Times New Roman", Font.BOLD, 25));
        g.drawString(timerString, 1100, 33);
}

}
