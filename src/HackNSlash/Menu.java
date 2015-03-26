package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Menu{
	@SuppressWarnings("unused")
	private GamePanel menuScreen;
	private int positionTracker = 0;
	
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
	
	public void paint(Graphics2D g){

		Rectangle2D.Double start = new Rectangle2D.Double(275, 200, 100, 50);
		Rectangle2D.Double highscore = new Rectangle2D.Double(275, 300, 100, 50);
		Rectangle2D.Double quit = new Rectangle2D.Double(275, 400, 100, 50);
		
		g.setColor(Color.GRAY);
		g.fill(start);
		g.fill(highscore);
		g.fill(quit);
		
	}
	
}
