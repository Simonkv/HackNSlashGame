package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Menu{
	@SuppressWarnings("unused")
	private GamePanel menuScreen;
	private int positionTracker = 0;
	
	private int xPos = 575;
	private int yPos0 = 200;
	private int yPos1 = 300;
	private int yPos2 = 400;
	private int yPosSelected = yPos0;
	
	private int width = 100;
	private int height = 50;
	
	Rectangle2D.Double start = new Rectangle2D.Double(xPos, yPos0, width, height);
	Rectangle2D.Double highscore = new Rectangle2D.Double(xPos, yPos1, width, height);
	Rectangle2D.Double quit = new Rectangle2D.Double(xPos, yPos2, width, height);
	
	Rectangle2D.Double selected = new Rectangle2D.Double(xPos, yPosSelected, width, height);
	
	public Menu(GamePanel menu){
		this.menuScreen = menu;
	}
	
	public void setPositionTracker(String s){
		if (s.equals("UP") && positionTracker!=0){
			this.positionTracker -= 1 ;
		}
		else if (s.equals("DOWN") && positionTracker!=2){
			this.positionTracker += 1 ;
		}
	}
	
	public int getPositionTracker(){
		return positionTracker;
	}
	
	public void update(){
		if(positionTracker==0){
			yPosSelected = yPos0;
		}else if(positionTracker==1){
			yPosSelected = yPos1;
		}else if(positionTracker==2){
			yPosSelected = yPos2;
		}
		selected = new Rectangle2D.Double(xPos, yPosSelected, width, height);
	}
	
	public void enter(){
		if(yPosSelected == yPos0){
			menuScreen.gameState = 2;
		}else if(yPosSelected == yPos1){
			
		}else if(yPosSelected == yPos2){
			System.exit(0);
		}
	}
	
	public void paint(Graphics2D g){

		
		
		g.setColor(Color.GRAY);
		g.fill(start);
		
		g.fill(highscore);
		
		g.fill(quit);
		
		g.setColor(Color.RED);
		g.draw(selected);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("START", xPos, yPos0+30);
		g.drawString("HIGHSCORES", xPos, yPos1+30);
		g.drawString("QUIT", xPos, yPos2+30);
		
	}
	
}