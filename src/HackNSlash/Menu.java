package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Menu extends JFrame{
	@SuppressWarnings("unused")
	private GamePanel menuScreen;
	private boolean menuOperational;
	
	public Menu(GamePanel menu){
		this.menuScreen = menu;
		setMenuOperational(true);
	}
	
	public void setMenuOperational(boolean menu){
		this.menuOperational = menu;
	}
	
	public boolean getMenuOperational(){
		return menuOperational;
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
