package HackNSlash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Boss{
	
	private GamePanel panel;
	
	ImageIcon FireballLeft = new ImageIcon ((getClass().getResource( "/Images/FireballLeft.png" )));
	ImageIcon FireballRight = new ImageIcon ((getClass().getResource( "/Images/FireballRight.png" )));
	ImageIcon ExplosionNotification = new ImageIcon ((getClass().getResource( "/Images/ExplosionNotification.png" )));
	ImageIcon ExplosionFlame = new ImageIcon ((getClass().getResource( "/Images/ExplosionFlame.png" )));
	
	ImageIcon BossStandby1 = new ImageIcon ((getClass().getResource( "/Images/BossStandby1.png" )));
	ImageIcon BossStandby2 = new ImageIcon ((getClass().getResource( "/Images/BossStandby2.png" )));
	ImageIcon BossPreJump = new ImageIcon ((getClass().getResource( "/Images/BossPreJump.png" )));
	ImageIcon BossPostJump = new ImageIcon ((getClass().getResource( "/Images/BossPostJump.png" )));
	ImageIcon BossFireSides = new ImageIcon ((getClass().getResource( "/Images/BossFireSides.png" )));
	ImageIcon BossFireUp1 = new ImageIcon ((getClass().getResource( "/Images/BossFireUp1.png" )));
	ImageIcon BossFireUp2 = new ImageIcon ((getClass().getResource( "/Images/BossFireUp2.png" )));
	ImageIcon BossSpin1 = new ImageIcon ((getClass().getResource( "/Images/BossSpin1.png" )));
	ImageIcon BossSpin2 = new ImageIcon ((getClass().getResource( "/Images/BossSpin2.png" )));
	
	ImageIcon img = BossStandby1;
	
	private int bossWidth=200;
	private int bossHeight=200;
	
	private int xPos = 500;
	private int yPos = 100;
	
	private long startTimer = System.currentTimeMillis();
	private int health = 600;
	private int healthBarLength = 500;
	private String bossName = "Penis";
	private int bossPoints = 10000;
	private Rectangle2D.Double healthbar = new Rectangle2D.Double((panel.SCREEN_WIDTH/2)-healthBarLength/2, 10, healthBarLength, 30);
	private Rectangle2D.Double bossRectangle = new Rectangle2D.Double(xPos, yPos, bossWidth, bossHeight);
	
	private int BOSS_TOUCH_DAMAGE = 10;
	
	
	//*************XERATH ULT *******************
	private boolean xerathUltFired = false;
	private long xerathUltNotificationInMillis;
	private long xerathNotificationDuration = 1000;
	private boolean xerathExplosion = false;
	private long xerathUltExplosionInMillis;
	private long xerathUltExplosionDuration = 200;
	private int xerathUltX;
	private int xerathUltY;
	
	private int xerathUltExplosionDiameter = 150;
	private int xerathUltNotificationDiameter = 50;
	
	private int XERATH_ULT_DAMAGE = 10;
	
	private Ellipse2D.Double xerathUlt = new Ellipse2D.Double(-1000,-1000,50,50);
	private Ellipse2D.Double xerathUltNotification = new Ellipse2D.Double(-1000,-1000,50,50);
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
	private int BALL_DAMAGE = 10;
	
	private boolean bossStandby = false;
	private boolean doXerathUlt = false;
	private boolean shootFlames = false;
	private boolean startJump = false;
	private boolean jump = false;
	private boolean spin = false;
	
	public void reset(){
		health = 500;
	}
	
	Boss(GamePanel panel){
		this.panel = panel;
	}
	public void shootBalls(){
		if(!gottenYPos){
			yToGoTo = panel.player.yPos-(panel.player.playerSize/2)-20; //HER ENDRA Æ! MENE DEN SKYT BALLAN LITT LAVT!
			gottenYPos = true;
			
			bossStandby = false;
			doXerathUlt = false;
			shootFlames = false;
			startJump = true;
			jump = false;
			spin = false;
		}if (gottenYPos && !atYPos){
			bossStandby = false;
			doXerathUlt = false;
			shootFlames = false;
			startJump = false;
			jump = true;
			spin = false;
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
				bossStandby = false;
				doXerathUlt = false;
				shootFlames = true;
				startJump = false;
				jump = false;
				spin = false;
				bulletXPlus += bulletSpeed;
				bulletXMinus -= bulletSpeed;
				leftShot = new Ellipse2D.Double(bulletXMinus, yPos+(bossHeight/2)-(ballDiameter/2) ,ballDiameter,ballDiameter);
				rightShot = new Ellipse2D.Double(bulletXPlus,yPos+(bossHeight/2)-(ballDiameter/2),ballDiameter,ballDiameter);
			}else{
				stopShootBalls();
			}
			
		}
	}
	
	private int getHealth(){
		return 0;
	}
	
	private void stopShootBalls(){
		bossStandby = true;
		doXerathUlt = false;
		shootFlames = false;
		startJump = false;
		jump = false;
		spin = false;
		gottenYPos = false;
		atYPos = false;
		leftShot = new Ellipse2D.Double(-1000,-1000,50,50);
		rightShot = new Ellipse2D.Double(-1000,-1000,50,50);
	}
	
	public void update(){
		
		//HARDKODA BOSS STATE!!!
		takeDamage();
		if(System.currentTimeMillis()-startTimer<=2500){
			bossStandby = true;
			doXerathUlt = false;
			shootFlames = false;
			startJump = false;
			jump = false;
			spin = false;
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
			doXerathUlt();
			doSpinToWin();
			
		}else{
			startTimer=System.currentTimeMillis();
			stopXerathUlt();
		}
		
		
		
		
		bossRectangle = new Rectangle2D.Double(xPos, yPos, bossWidth, bossHeight);
		playerKnockback();
		healthbar = new Rectangle2D.Double((panel.SCREEN_WIDTH/2)-healthBarLength/2, 10, health, 30);
	}
	//XERATH ULT
	//GRAGAS SLAM
	//GAREN SPIN TO WIN
	
	public boolean isDead(){
		if(health<=0){
			panel.highscoreList.addPoints(bossPoints);
			return true;
		}else{
			return false;
		}
	}
	
	public void takeDamage(){
		if (bossRectangle.intersects(panel.player.attack1)){
			health -= panel.player.attack1Damage;
		}
		if (panel.player.attack2.intersects(bossRectangle)){
			health -= panel.player.attack2Damage;
		}
	}
	
	public void stopXerathUlt(){
		xerathUlt = new Ellipse2D.Double(-1000,-1000,50,50);
		xerathUlt = new Ellipse2D.Double(-1000,-1000,50,50);
		xerathUltNotification = new Ellipse2D.Double(-1000,-1000,50,50);
		xerathUltFired = false;
		xerathExplosion = false;
		bossStandby = true;
		doXerathUlt = false;
		shootFlames = false;
		startJump = false;
		jump = false;
		spin = false;
	}
	
	public void doXerathUlt(){
		bossStandby = false;
		doXerathUlt = true;
		shootFlames = false;
		startJump = false;
		jump = false;
		spin = false;
		
		if(!xerathUltFired){
			xerathUltX = panel.player.xPos+(panel.player.playerSize/2);
			xerathUltY = panel.player.yPos+(panel.player.playerSize/2);
			xerathUltNotification = new Ellipse2D.Double(xerathUltX-(xerathUltNotificationDiameter/2),xerathUltY-(xerathUltNotificationDiameter/2),50,50);
			xerathUltNotificationInMillis = System.currentTimeMillis();
			xerathUltFired = true;
			img = BossFireUp1;
		}else{
			
			if(System.currentTimeMillis()-xerathUltNotificationInMillis>=xerathNotificationDuration && !xerathExplosion){
				xerathUlt = new Ellipse2D.Double(xerathUltX-(xerathUltExplosionDiameter/2),xerathUltY-(xerathUltExplosionDiameter/2),xerathUltExplosionDiameter,xerathUltExplosionDiameter);
				xerathUltExplosionInMillis = System.currentTimeMillis();
				xerathExplosion = true;
				img = BossFireUp2;
			}else if (System.currentTimeMillis()-xerathUltExplosionInMillis>=xerathUltExplosionDuration && xerathExplosion){
				stopXerathUlt();
				img = BossStandby1;
				
			}
		}
			
	}
	
	public void doSpinToWin(){
		bossStandby = false;
		doXerathUlt = false;
		shootFlames = false;
		startJump = false;
		jump = false;
		spin = true;
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
	public void playerKnockback(){
		if (xerathUlt.intersects(panel.player.player)){
			panel.player.iHitYou((int)(xerathUlt.getX()+(xerathUlt.getWidth()/2)), (int)(xerathUlt.getY()+(xerathUlt.getHeight()/2)));
			panel.player.reduceHealth(XERATH_ULT_DAMAGE);
		}
		
		if (bossRectangle.intersects(panel.player.player)){
			panel.player.iHitYou((int)(bossRectangle.getX()+(bossRectangle.getWidth()/2)), (int)(bossRectangle.getY()+(bossRectangle.getHeight()/2)));
			panel.player.reduceHealth(BOSS_TOUCH_DAMAGE);
		}
		if (leftShot.intersects(panel.player.player)){
			panel.player.iHitYou((int)(leftShot.getX()+(leftShot.getWidth()/2)), (int)(leftShot.getY()+(leftShot.getHeight()/2)));
			panel.player.reduceHealth(BALL_DAMAGE);
		}
		if (rightShot.intersects(panel.player.player)){
			panel.player.iHitYou((int)(rightShot.getX()+(rightShot.getWidth()/2)), (int)(rightShot.getY()+(rightShot.getHeight()/2)));
			panel.player.reduceHealth(BALL_DAMAGE);
		}
}
	
	
	private int tick = 0;
	public void paint(Graphics2D g){
		
		g.setColor(Color.RED);
		//g.fill(xerathUlt);
		//g.fill(xerathUltNotification);
		//g.fill(leftShot);
		//g.fill(rightShot);
		g.drawImage(ExplosionNotification.getImage(), (int)(xerathUltNotification.getX()+xerathUltNotification.width/2)-75 ,(int)(xerathUltNotification.getY()+xerathUltNotification.height/2)-75, 150, 150, null);
		g.drawImage(ExplosionFlame.getImage(), (int)(xerathUlt.getX()+xerathUlt.width/2)-90 ,(int)(xerathUlt.getY()+xerathUlt.height/2)-90, 180, 180, null);
		g.drawImage(FireballLeft.getImage(), (int)leftShot.getX()+5 ,(int)leftShot.getY()-12, 80, 80, null);
		g.drawImage(FireballRight.getImage(), (int)rightShot.getX()-5 ,(int)rightShot.getY()-12, 80, 80, null);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
		g.drawString(bossName+": ", (int)healthbar.getX()-100, (int)(healthbar.getY()+20));
		g.fill(healthbar);
		//g.fill(bossRectangle);
		
		if(bossStandby){
			if(tick<5){
				img = BossStandby1;
				tick++;
			}else if(tick<10){
				img = BossStandby2;
				tick++;
			}else{
				tick=0;
			}
		}else if(spin){
			if(tick<3){
				img = BossSpin1;
				tick++;
			}else if(tick<6){
				img = BossSpin2;
				tick++;
			}else{
				tick=0;
			}
			
		}
		else if(doXerathUlt){
			if(tick<5){
				img = BossFireUp1;
				tick++;
			}else if(tick<10){
				img = BossFireUp2;
				tick++;
			}else{
				tick=0;
			}
		}else if(shootFlames){
			img = BossFireSides;
		}else if(startJump){
			img = BossPreJump;
		}else if(jump){
			img = BossPostJump;
		}
		g.drawImage(img.getImage(), xPos ,yPos, bossWidth, bossHeight, null);
		g.setColor(Color.BLACK);
		
	}

}


