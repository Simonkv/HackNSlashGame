package HackNSlash;

import java.awt.Graphics;
import java.applet.*;
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
	
	
	Slime slime1 = new Slime(this,100,100);
	Goblin goblin1 = new Goblin(this,350,100);
	Troll troll1 = new Troll(this,500,100);
	Slime slime2 = new Slime(this,1050,100);
	Goblin goblin2 = new Goblin(this,750,100);
	Troll troll2 = new Troll(this,600,100);
	
	
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
	
	Slime slime3 = new Slime(this,50,100);
	Slime slime4 = new Slime(this,125,100);
	Slime slime5 = new Slime(this,250,100);
	Slime slime6 = new Slime(this,350,100);
	Slime slime7 = new Slime(this,450,100);
	Slime slime8 = new Slime(this,550,100);
	Slime slime9 = new Slime(this,650,100);
	Slime slime10 = new Slime(this,750,100);
	Goblin goblin13 = new Goblin(this,100,300);
	Goblin goblin14 = new Goblin(this,900,300);
	Troll troll3 = new Troll(this,50,500);
	Troll troll4 = new Troll(this,250,500);
	Troll troll5 = new Troll(this,450,500);
	Troll troll6 = new Troll(this,650,500);
	Troll troll7 = new Troll(this,850,500);
	
	
	
	
	
	
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
		
		wave4.add(slime3);
		wave4.add(slime4);
		wave4.add(slime5);
		wave4.add(slime6);
		wave4.add(slime7);
		wave4.add(slime8);
		wave4.add(slime9);
		wave4.add(slime10);
		wave4.add(goblin13);
		wave4.add(goblin14);
		wave4.add(troll3);
		wave4.add(troll4);
		wave4.add(troll5);
		wave4.add(troll6);
		wave4.add(troll7);
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
				slime3.update();
				slime4.update();
				slime5.update();
				slime6.update();
				slime7.update();
				slime8.update();
				slime9.update();
				slime10.update();
				
				goblin13.update();
				goblin14.update();
				
				troll3.update();
				troll4.update();
				troll5.update();
				troll6.update();
				troll7.update();
				
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
        	highscoreList.save();
        	
        }
	}
	
	public void reset(){
		slime = new Slime(this);
		goblin = new Goblin(this);
		troll = new Troll(this);
		
		slime1 = new Slime(this,100,100);
		goblin1 = new Goblin(this,350,100);
		troll1 = new Troll(this,500,100);
		slime2 = new Slime(this,1050,100);
		goblin2 = new Goblin(this,750,100);
		troll2 = new Troll(this,600,100);
		
		
		goblin3 = new Goblin(this,50,100);
		goblin4 = new Goblin(this,150,100);
		goblin5 = new Goblin(this,250,100);
		goblin6 = new Goblin(this,350,100);
		goblin7 = new Goblin(this,450,100);
		goblin8 = new Goblin(this,550,100);
		goblin9 = new Goblin(this,600,100);
		goblin10 = new Goblin(this,750,100);
		goblin11 = new Goblin(this,850,100);
		goblin12 = new Goblin(this,950,100);
		
		slime3 = new Slime(this,100,100);
		slime4 = new Slime(this,200,100);
		slime5 = new Slime(this,300,100);
		slime6 = new Slime(this,400,100);
		slime7 = new Slime(this,500,100);
		slime8 = new Slime(this,600,100);
		slime9 = new Slime(this,700,100);
		slime10 = new Slime(this,800,100);
		goblin13 = new Goblin(this,100,300);
		goblin14 = new Goblin(this,1050,300);
		troll3 = new Troll(this,100,500);
		troll4 = new Troll(this,300,500);
		troll5 = new Troll(this,500,500);
		troll6 = new Troll(this,700,500);
		troll7 = new Troll(this,900,500);
		
		
		
		
		player = new Warrior(this,500,500);
		boss = new Boss(this);
		wavesStarted=false;
		//highscoreList.bufferScore = 0;
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
				slime3.paint(g2d);
				slime4.paint(g2d);
				slime5.paint(g2d);
				slime6.paint(g2d);
				slime7.paint(g2d);
				slime8.paint(g2d);
				slime9.paint(g2d);
				slime10.paint(g2d);
				
				goblin13.paint(g2d);
				goblin14.paint(g2d);
				
				troll3.paint(g2d);
				troll4.paint(g2d);
				troll5.paint(g2d);
				troll6.paint(g2d);
				troll7.paint(g2d);
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
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					player.reduceHealth(1000);
					
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
