package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Arena extends GameMap{
	
	private GamePanel panel;
	private final int WALL_THICKNESS = 50;
	ImageIcon arena = new ImageIcon ( "Images/ArenaInGamePNG.png" );
	
	Rectangle2D.Double grass = new Rectangle2D.Double(0, 0, panel.SCREEN_WIDTH, panel.SCREEN_HEIGHT);
	Rectangle2D.Double leftWall = new Rectangle2D.Double(0, 0, WALL_THICKNESS, panel.SCREEN_HEIGHT);
	Rectangle2D.Double topWall = new Rectangle2D.Double(0, 0, panel.SCREEN_WIDTH, WALL_THICKNESS);
	Rectangle2D.Double rightWall = new Rectangle2D.Double(panel.SCREEN_WIDTH-WALL_THICKNESS, 0, WALL_THICKNESS, panel.SCREEN_HEIGHT);
	Rectangle2D.Double bottomWall = new Rectangle2D.Double(0, panel.SCREEN_HEIGHT-WALL_THICKNESS-50,panel.SCREEN_WIDTH, WALL_THICKNESS+50);
	
	public Arena(GamePanel panel){
		this.panel = panel;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		
		g.fill(grass);
		g.setColor(Color.BLACK);
		g.fill(topWall);
		g.fill(leftWall);
		g.fill(bottomWall);
		g.fill(rightWall);
		g.drawImage(arena.getImage(), -25 ,0, 1250, 700, null);

		
	}

}
