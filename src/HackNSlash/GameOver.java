package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GameOver {
	
	private GamePanel panel;
	
	private int positionTracker = 0;
	
	private int xPos1 = 300;
	private int xPos2 = 500;
	private int yPos = 500;
	private int xPosSelected = xPos1;
	
	private int width = 150;
	private int height = 50;
	
	Rectangle2D.Double playAgain = new Rectangle2D.Double(xPos1, yPos, width, height);
	Rectangle2D.Double menu = new Rectangle2D.Double(xPos2, yPos, width, height);
	
	Rectangle2D.Double selected = new Rectangle2D.Double(xPosSelected, yPos, width, height);
	
	Rectangle2D.Double background = new Rectangle2D.Double(0, 0, panel.SCREEN_WIDTH, panel.SCREEN_HEIGHT);
	
	public void setPositionTracker(String s){
		if (s.equals("RIGHT") && positionTracker!=1){
			this.positionTracker += 1 ;
		}
		else if (s.equals("LEFT") && positionTracker!=0){
			this.positionTracker -= 1 ;
		}
	}
	
	public int getPositionTracker(){
		return positionTracker;
	}
	
	public GameOver(GamePanel panel){
		this.panel = panel;
	}
	
	public boolean newHighscore(){
		for (int i=0; i<3; i++){
			if (Integer.parseInt(panel.highscoreList.getScore()) == panel.highscoreList.getElement(i)){
				return true;
			}
		}return false;
	}
	
	public void enter(){
		if(xPosSelected == xPos1){
			panel.gameState = 2;
		}else if(xPosSelected == xPos2){
			panel.gameState = 1;
		}
	}
	
	public void update(){
		if(positionTracker==0){
			xPosSelected = xPos1;
		}else if(positionTracker==1){
			xPosSelected = xPos2;
		}
		selected = new Rectangle2D.Double(xPosSelected, yPos, width, height);
	}
	
	public void paint(Graphics2D g){
		
		g.setColor(Color.BLACK);
		g.fill(background);
		g.setColor(Color.RED);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
        g.drawString("YOU BE DEADED!", 310, 90);
        g.drawString("Your score:" + panel.highscoreList.getScore(), 310, 120);
        g.drawString("TOP 3:", 310, 180);
        g.drawString("1." + "   " + panel.highscoreList.getElement(0), 310, 230);
        g.drawString("2." + "   " + panel.highscoreList.getElement(1), 310, 280);
        g.drawString("3." + "   " + panel.highscoreList.getElement(2), 310, 330);
        if(newHighscore()){
        	g.setColor(Color.GREEN);
        	g.setFont(new Font ("Times New Roman", Font.BOLD, 70));
        	g.drawString("YOU ARE TOP 3!!!!!!!!!", 150, 400);
        }
        
        g.setColor(Color.GRAY);
		g.fill(playAgain);
		g.fill(menu);
		
		g.setColor(Color.RED);
		g.draw(selected);
		
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("Play Again", xPos1+5, yPos+35);
		g.drawString("Menu", xPos2+35, yPos+35);
        	
	}


}
