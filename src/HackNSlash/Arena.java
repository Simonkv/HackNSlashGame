package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;

public class Arena extends GameMap{
	
	private GamePanel panel;
	private final int WALL_THICKNESS = 30;
	
	public Arena(GamePanel panel){
		this.panel = panel;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, panel.SCREEN_WIDTH, panel.SCREEN_HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WALL_THICKNESS, panel.SCREEN_HEIGHT);
		g.fillRect(0, 0, panel.SCREEN_WIDTH, WALL_THICKNESS);
		g.fillRect(panel.SCREEN_WIDTH-WALL_THICKNESS, 0, WALL_THICKNESS, panel.SCREEN_HEIGHT);
		g.fillRect(0, panel.SCREEN_HEIGHT-WALL_THICKNESS,panel.SCREEN_WIDTH, WALL_THICKNESS);

		
	}

}
