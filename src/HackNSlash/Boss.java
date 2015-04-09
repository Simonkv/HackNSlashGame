package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Boss{
	
	private GamePanel panel;
	
	
	
	private int bossWidth=200;
	private int bossHeight=200;
	
	private int xPos = 500;
	private int yPos = 100;
	
	private long startTimer = System.currentTimeMillis();
	private int health = 500;
	private Ellipse2D.Double bossRectangle = new Ellipse2D.Double(xPos, yPos, bossWidth, bossHeight);
	
	
	//*************XERATH ULT *******************
	private boolean xerathUltFired = false;
	private long xerathUltNotificationInMillis;
	private long xerathNotificationDuration = 1000;
	private boolean xerathExplosion = false;
	private long xerathUltExplosionInMillis;
	private long xerathUltExplosionDuration = 200;
	private int xerathUltX;
	private int xerathUltY;
	
	private int xerathUltExplosionDiameter = 200;
	private int xerathUltNotificationDiameter = 50;
	
	private Ellipse2D.Double xerathUlt = new Ellipse2D.Double(-1000,-1000,50,50);
	//***********************************************
	
	//**************GAREN SPIN TO WIN**************
	private int X_SPEED = 30;
	private int Y_SPEED = 15;
	private int xSpeed = 30;
	private int ySpeed = 12;
	//***********************************************
	
	//**************SHOOT BALLS!**************
	private long shootStartTimer;
	private long howLongShouldIShoot = 1400;
	private boolean gottenYPos = false;
	private int yToGoTo;
	private boolean atYPos = false;
	private Ellipse2D.Double leftShot = new Ellipse2D.Double(-1000,-1000,50,50);
	private Ellipse2D.Double rightShot = new Ellipse2D.Double(-1000,-1000,50,50);
	private int bulletSpeed = 30;
	private int bulletXPlus;
	private int bulletXMinus;
	private int ballDiameter = 50;
	private int shootBallStateYSpeed = 60;
	
	Boss(GamePanel panel){
		this.panel = panel;
	}
	public void shootBalls(){
		if(!gottenYPos){
			yToGoTo = panel.player.yPos-(panel.player.playerSize/2);
			gottenYPos = true;
		}if (gottenYPos && !atYPos){
			if(yPos<yToGoTo-40 && yPos<yToGoTo+40){
				if(yPos+shootBallStateYSpeed+bossHeight>panel.SCREEN_HEIGHT-panel.arena.bottomWall.getHeight()){
					yToGoTo = yPos;
				}else{
					yPos+=shootBallStateYSpeed;
				}
			}else if(yPos>yToGoTo-40 && yPos>yToGoTo+40){
				if(yPos-(shootBallStateYSpeed/2)<panel.arena.topWall.getHeight()){
					yToGoTo = yPos;
				}else{
					yPos-=shootBallStateYSpeed;
				}
			}
			else{
				bulletXPlus = xPos+(bossWidth/2);
				bulletXMinus = xPos+(bossWidth/2);
				shootStartTimer = System.currentTimeMillis();
				atYPos = true;
			}
		}else{
			if(System.currentTimeMillis()-shootStartTimer<howLongShouldIShoot){
				bulletXPlus += bulletSpeed;
				bulletXMinus -= bulletSpeed;
				leftShot = new Ellipse2D.Double(bulletXMinus, yPos+(bossHeight/2)-(ballDiameter/2) ,ballDiameter,ballDiameter);
				rightShot = new Ellipse2D.Double(bulletXPlus,yPos+(bossHeight/2)-(ballDiameter/2),ballDiameter,ballDiameter);
			}else{
				stopShootBalls();
			}
			
		}
	}
	
	private void stopShootBalls(){
		gottenYPos = false;
		atYPos = false;
		leftShot = new Ellipse2D.Double(-1000,-1000,50,50);
		rightShot = new Ellipse2D.Double(-1000,-1000,50,50);
	}
	
	public void update(){
		//HARDKODA BOSS STATE!!!
		if(System.currentTimeMillis()-startTimer<=5000){
			
		}
		else if(System.currentTimeMillis()-startTimer<=10000){
			doXerathUlt();
		}
		else if(System.currentTimeMillis()-startTimer<=17000){
			stopXerathUlt();
			shootBalls();
		}else if(System.currentTimeMillis()-startTimer<=24000){
			stopShootBalls();
			doXerathUlt();
			
		}else if(System.currentTimeMillis()-startTimer<=31000){
			stopXerathUlt();
			doSpinToWin();
		}
		else if(System.currentTimeMillis()-startTimer<=38000){
			doSpinToWin();
			doXerathUlt();
		}else{
			startTimer=System.currentTimeMillis();
		}
		
		
		
		
		bossRectangle = new Ellipse2D.Double(xPos, yPos, bossWidth, bossHeight);
	}
	//XERATH ULT
	//GRAGAS SLAM
	//GAREN SPIN TO WIN
	
	public void stopXerathUlt(){
		xerathUlt = new Ellipse2D.Double(-1000,-1000,50,50);
		xerathUltFired = false;
		xerathExplosion = false;
	}
	
	public void doXerathUlt(){
		
		if(!xerathUltFired){
			xerathUltX = panel.player.xPos+(panel.player.playerSize/2);
			xerathUltY = panel.player.yPos+(panel.player.playerSize/2);
			xerathUlt = new Ellipse2D.Double(xerathUltX-(xerathUltNotificationDiameter/2),xerathUltY-(xerathUltNotificationDiameter/2),50,50);
			xerathUltNotificationInMillis = System.currentTimeMillis();
			xerathUltFired = true;
		}else{
			
			if(System.currentTimeMillis()-xerathUltNotificationInMillis>=xerathNotificationDuration && !xerathExplosion){
				xerathUlt = new Ellipse2D.Double(xerathUltX-(xerathUltExplosionDiameter/2),xerathUltY-(xerathUltExplosionDiameter/2),xerathUltExplosionDiameter,xerathUltExplosionDiameter);
				xerathUltExplosionInMillis = System.currentTimeMillis();
				xerathExplosion = true;
			}else if (System.currentTimeMillis()-xerathUltExplosionInMillis>=xerathUltExplosionDuration && xerathExplosion){
				stopXerathUlt();
				
			}
		}
			
	}
	
	public void doSpinToWin(){
		xPos += xSpeed;
		yPos += ySpeed;
		if(bossRectangle.intersects(panel.arena.rightWall)){
			xSpeed = -X_SPEED;
		}
		if(bossRectangle.intersects(panel.arena.leftWall)){
			xSpeed = X_SPEED;
		}
		if(bossRectangle.intersects(panel.arena.bottomWall)){
			ySpeed = -Y_SPEED;
		}
		if(bossRectangle.intersects(panel.arena.topWall)){
			ySpeed = Y_SPEED;
		}
	}
	
	
	
	public void paint(Graphics2D g){
		g.fill(bossRectangle);
		g.setColor(Color.RED);
		g.fill(xerathUlt);
		g.setColor(Color.BLACK);
		g.fill(leftShot);
		g.fill(rightShot);
	}

}


