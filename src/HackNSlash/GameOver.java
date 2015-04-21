package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class GameOver {
	
	private GamePanel panel;
	
	ImageIcon GameOver = new ImageIcon ((getClass().getResource( "/Images/GameOver.png" )));
	ImageIcon GameWon = new ImageIcon ((getClass().getResource( "/Images/GameWon.png" )));
	
	public boolean won = false;
	
	private int positionTracker = 0;
	
	private int xPos1 = 138;
	private int xPos2 = 718;
	private int yPos = 470;
	private int xPosSelected = xPos1;
	
	private int width = 423;
	private int height = 80;
	
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
			panel.reset();
			panel.gameState = 2;
		}else if(xPosSelected == xPos2){
			panel.gameState = 1;
		}
	}
	
	public void update(){
		if(positionTracker==0){
			width = 423;
			xPosSelected = xPos1;
		}else if(positionTracker==1){
			width = 373;
			xPosSelected = xPos2;
		}
		selected = new Rectangle2D.Double(xPosSelected, yPos, width, height);
	}
	
	public void paint(Graphics2D g){
		
		if(won){
			g.drawImage(GameWon.getImage(), 0 ,0, 1200, 700, null);
		}else{
			g.drawImage(GameOver.getImage(), 0 ,0, 1200, 700, null);
		}
		
		
		
		
		//g.fill(background);
		g.setColor(Color.ORANGE);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 80));
        //g.drawString("YOU BE DEADED!", 310, 90);
        g.drawString(panel.highscoreList.getScore(), 365, 330);
        //g.drawString("TOP 3:", 310, 180);
        //g.drawString("1." + "   " + panel.highscoreList.getElement(0), 310, 230);
        //g.drawString("2." + "   " + panel.highscoreList.getElement(1), 310, 280);
        //g.drawString("3." + "   " + panel.highscoreList.getElement(2), 310, 330);
        
        
        g.setColor(Color.GRAY);
		//g.fill(playAgain);
		//g.fill(menu);
		
		g.setColor(Color.WHITE);
		g.draw(selected);
		
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		
        	
	}


}
