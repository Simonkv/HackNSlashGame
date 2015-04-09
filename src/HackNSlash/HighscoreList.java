package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;

public class HighscoreList {
	
	private GamePanel panel;
	
	private int newHighscore;
	private int bufferScore;
	ArrayList<Integer> highscores = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
	
	Rectangle2D.Double background = new Rectangle2D.Double(0, 0, panel.SCREEN_WIDTH, panel.SCREEN_HEIGHT);
	Rectangle2D.Double HSWall = new Rectangle2D.Double(300, 50, 600, 570);
	
	public HighscoreList(GamePanel panel){
		this.panel = panel;
	}
	
	public String getScore(){
		return Integer.toString(bufferScore);
	}
	
	public void addPoints(int score){
		this.newHighscore+=score;
	}
	
	public int size(){
		return highscores.size();
	}
	
	public int getElement(int pos){
		return highscores.get(pos);
	}
	
	public void addResult(){
		if (newHighscore > highscores.get(size()-1)){
			boolean higher = true;
			int counter = -1;
			while (higher){
				counter++;
				if (newHighscore > getElement(counter)){
					highscores.add(counter, newHighscore);
					highscores.remove(size()-1);
					bufferScore = newHighscore;
					higher = false;
				}
			}
		}newHighscore=0;
	}
	
	public void update(){
		
	}
	
	public void paint(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fill(background);
		g.setColor(Color.GRAY);
		g.fill(HSWall);
		g.setColor(Color.RED);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
        g.drawString("Your highscores:", 310, 90);
        g.drawString("1." + "   " + getElement(0), 310, 150);
        g.drawString("2." + "   " + getElement(1), 310, 200);
        g.drawString("3." + "   " + getElement(2), 310, 250);
        g.drawString("4." + "   " + getElement(3), 310, 300);
        g.drawString("5." + "   " + getElement(4), 310, 350);
        g.drawString("6." + "   " + getElement(5), 310, 400);
        g.drawString("7." + "   " + getElement(6), 310, 450);
        g.drawString("8." + "   " + getElement(7), 310, 500);
        g.drawString("9." + "   " + getElement(8), 310, 550);
        g.drawString("10." + " " + getElement(9), 310, 600);
        
		
	}

}
