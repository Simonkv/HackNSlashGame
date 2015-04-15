package HackNSlash;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	Warrior player = new Warrior(this,500,500);
	Menu menu = new Menu(this);
	
	HighscoreList highscoreList = new HighscoreList(this);
	GameOver gameOver = new GameOver(this);
	WaveTimer waveTimer = new WaveTimer(this);
	
	Boss boss = new Boss(this);
	
	
	Slime slime = new Slime(this);
	Goblin goblin = new Goblin(this);
	Troll troll = new Troll(this);
	
	
	Slime slime1 = new Slime(this);
	Goblin goblin1 = new Goblin(this);
	Troll troll1 = new Troll(this);
	Slime slime2 = new Slime(this);
	Goblin goblin2 = new Goblin(this);
	Troll troll2 = new Troll(this);
	
	
	Goblin goblin3 = new Goblin(this,50,100);
	Goblin goblin4 = new Goblin(this,150,100);
	Goblin goblin5 = new Goblin(this,250,100);
	Goblin goblin6 = new Goblin(this,350,100);
	Goblin goblin7 = new Goblin(this,450,100);
	Goblin goblin8 = new Goblin(this,550,100);
	Goblin goblin9 = new Goblin(this,600,100);
	Goblin goblin10 = new Goblin(this,750,100);
	Goblin goblin11 = new Goblin(this,850,100);
	Goblin goblin12 = new Goblin(this,950,100);
	
	
	
	
	ArrayList<MonsterAI> wave1 = new ArrayList<MonsterAI>();
	ArrayList<MonsterAI> wave2 = new ArrayList<MonsterAI>();
	ArrayList<MonsterAI> wave3 = new ArrayList<MonsterAI>();
	ArrayList<MonsterAI> wave4 = new ArrayList<MonsterAI>();
	ArrayList<MonsterAI> wave5 = new ArrayList<MonsterAI>();
	ArrayList<MonsterAI> wave6 = new ArrayList<MonsterAI>();

	
	private boolean wavesStarted = false;
	
	public void startWave1(){
		
	}
	
	public void addAllEnemies(){
		wave1.add(slime);
		wave1.add(troll);
		wave1.add(goblin);
		
		wave2.add(slime1);
		wave2.add(slime2);
		wave2.add(goblin1);
		wave2.add(goblin2);
		wave2.add(troll1);
		wave2.add(troll2);
		
		wave3.add(goblin3);
		wave3.add(goblin4);
		wave3.add(goblin5);
		wave3.add(goblin6);
		wave3.add(goblin7);
		wave3.add(goblin8);
		wave3.add(goblin9);
		wave3.add(goblin10);
		wave3.add(goblin11);
		wave3.add(goblin12);
		wavesStarted=true;
	}
	
	public void startTimer(){
		FPS.startTimer();
	}
	public void endTimer(){
		FPS.endTimer();
		SLEEP_TIME=FPS.getSleepTime();
	}
	
	private boolean waveIsOver(ArrayList<MonsterAI> wave){
		for(MonsterAI enemy: wave){
			if(enemy.isAlive()){
				return false;
			}
		}
		return true;
	}
	
	public void update(){
		if(gameState==1){
			menu.update();
		}
		else if(gameState==2){
			
			checkGameOver();
			player.update();
			waveTimer.update();
			if(!wavesStarted){
				addAllEnemies();
			}
			if(!waveIsOver(wave1)){
				slime.update();
				troll.update();
				goblin.update();
			}else if(!waveIsOver(wave2)){
				slime1.update();
				troll1.update();
				goblin1.update();
				slime2.update();
				troll2.update();
				goblin2.update();
			}else if(!waveIsOver(wave3)){
				goblin3.update();
				goblin4.update();
				goblin5.update();
				goblin6.update();
				goblin7.update();
				goblin8.update();
				goblin9.update();
				goblin10.update();
				goblin11.update();
				goblin12.update();
			}else if(!waveIsOver(wave4)){
				
			}else if(!waveIsOver(wave5)){
				
			}else if(!waveIsOver(wave6)){
				
			}
			
			else{
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
	
	public void checkGameOver(){
		if(player.health<=0 || boss.isDead()){
        	gameState = 4;
        	highscoreList.addResult();
        	waveTimer.started = false;
        	
        }
	}
	
	public void reset(){
		slime = new Slime(this);
		goblin = new Goblin(this);
		troll = new Troll(this);
		player = new Warrior(this,500,500);
		boss = new Boss(this);
		wavesStarted=false;
		highscoreList.bufferScore = 0;
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
			if(!waveIsOver(wave1)){
				slime.paint(g2d);
				troll.paint(g2d);
				goblin.paint(g2d);
			}else if(!waveIsOver(wave2)){
				slime1.paint(g2d);
				troll1.paint(g2d);
				goblin1.paint(g2d);
				slime2.paint(g2d);
				troll2.paint(g2d);
				goblin2.paint(g2d);
			}else if(!waveIsOver(wave3)){
				goblin3.paint(g2d);
				goblin4.paint(g2d);
				goblin5.paint(g2d);
				goblin6.paint(g2d);
				goblin7.paint(g2d);
				goblin8.paint(g2d);
				goblin9.paint(g2d);
				goblin10.paint(g2d);
				goblin11.paint(g2d);
				goblin12.paint(g2d);
			}else if(!waveIsOver(wave4)){
				
			}else if(!waveIsOver(wave5)){
				
			}else if(!waveIsOver(wave6)){
				
			}
			
			
			else{
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
				if(e.getKeyCode() == KeyEvent.VK_Z){
					player.attack(1);
					
				}
				if(e.getKeyCode() == KeyEvent.VK_X){
					player.attack(2);
					
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
				
			}
			
			
		};
		
		addKeyListener(listener);
		setFocusable(true);
	}

}
