package HackNSlash;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public static final int SCREEN_WIDTH = 700;
	public static final int SCREEN_HEIGHT = 700;
	public static int SLEEP_TIME = 100;
	public static boolean running = true;
	
	FPScalculator FPS = new FPScalculator(this);
	
	public void startTimer(){
		FPS.startTimer();
	}
	public void endTimer(){
		FPS.endTimer();
		SLEEP_TIME=FPS.getSleepTime();
	}
	
	public void update(){
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//otherClass.paint(g2d);
		
		
		
		
		FPS.paint(g2d);
		
	}
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hack N Slach");
		GamePanel game = new GamePanel();
		frame.add(game);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(running){
			game.startTimer();
			
			game.update();
			game.repaint();
			
			game.endTimer();
			try{
				Thread.sleep(SLEEP_TIME);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public GamePanel(){
		KeyListener listener = new KeyListener(){
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//annaClass.keyPressed(e); sender tastetrykk til den classen
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//annaClass.keyReleased(e); sender tasteslipp til den classen

			}
			
			
		};
		
		addKeyListener(listener);
		setFocusable(true);
	}

}
