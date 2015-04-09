package HackNSlash;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	
	public int gameState = 1;

	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 700;
	public static int SLEEP_TIME = 100;
	public static boolean running = true;
	
	FPScalculator FPS = new FPScalculator(this);
	Arena arena = new Arena(this);
	Warrior player = new Warrior(this,200,200);
	Menu menu = new Menu(this);
	Slime slime = new Slime(this);
	Goblin goblin = new Goblin(this);
	Troll troll = new Troll(this);
	Boss boss = new Boss(this);
	WaveTimer waveTimer = new WaveTimer(this);
	HighscoreList highscoreList = new HighscoreList(this);
	GameOver gameOver = new GameOver(this);
	
	public void startTimer(){
		FPS.startTimer();
	}
	public void endTimer(){
		FPS.endTimer();
		SLEEP_TIME=FPS.getSleepTime();
	}
	
	public void update(){
		if(gameState==1){
			menu.update();
		}
		else if(gameState==2){
			player.update();
			waveTimer.update();
			if(waveTimer.seconds<20){
				slime.update();
				troll.update();
				goblin.update();
			}else if(waveTimer.seconds<100){
				boss.update();
			}
		}
		else if(gameState==3){
			highscoreList.update();
		}
		else if(gameState==4){
			gameOver.update();
		}
		
		
	}
	
	public void loadHighscores(){
		highscoreList.loadHighscores();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(gameState==1){
			menu.paint(g2d);
		}
		else if(gameState==2){
			arena.paint(g2d);
			player.paint(g2d);
			if(waveTimer.seconds<20){
				slime.paint(g2d);
				troll.paint(g2d);
				goblin.paint(g2d);
			}else if(waveTimer.seconds<100){
				boss.paint(g2d);
			}
			waveTimer.paint(g2d);
		}
		else if(gameState==3){
			highscoreList.paint(g2d);
		}
		else if(gameState==4){
			gameOver.paint(g2d);
		}
		
		
		FPS.paint(g2d);
		
	}
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hack N Slash");
		GamePanel game = new GamePanel();
		frame.add(game);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.loadHighscores();
		
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
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if(gameState==2){
						player.leftPressed();
					}else if(gameState==4){
						gameOver.setPositionTracker("LEFT");
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					if(gameState==2){
						player.rightPressed();
					}else if(gameState==4){
						gameOver.setPositionTracker("RIGHT");
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					if(gameState==1){
						menu.setPositionTracker("UP");
					}else if(gameState==2){
						player.upPressed();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if(gameState==1){
						menu.setPositionTracker("DOWN");
					}else if(gameState==2){
						player.downPressed();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(gameState==1){
						menu.enter();
					}else if(gameState==4){
						gameOver.enter();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(gameState==3){
						gameState = 1;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					if(gameState==3){
						gameState = 1;
					}
				}
				//annaClass.keyPressed(e); sender tastetrykk til den classen
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//annaClass.keyReleased(e); sender tasteslipp til den classen
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					player.leftReleased();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					player.rightReleased();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					player.upReleased();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					player.downReleased();
				}
				if(e.getKeyCode() == KeyEvent.VK_Z){
					player.attack(1);
					highscoreList.addPoints(10);
				}
				if(e.getKeyCode() == KeyEvent.VK_X){
					player.attack(2);
					player.reduceHealth(20);
				}
			}
			
			
		};
		
		addKeyListener(listener);
		setFocusable(true);
	}

}
