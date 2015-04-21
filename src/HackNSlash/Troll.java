package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Troll extends Avatar implements MonsterAI{

public GamePanel panel;


	ImageIcon TrollFrontStandby = new ImageIcon ((getClass().getResource( "/Images/TrollFrontStandby.png" )));
	ImageIcon TrollFrontRun1 = new ImageIcon ((getClass().getResource( "/Images/TrollFrontRun1.png" )));
	ImageIcon TrollFrontRun2 = new ImageIcon ((getClass().getResource( "/Images/TrollFrontRun2.png" )));

	ImageIcon TrollBackStandby = new ImageIcon ((getClass().getResource( "/Images/TrollBackStandby.png" )));
	ImageIcon TrollBackRun1 = new ImageIcon ((getClass().getResource( "/Images/TrollBackRun1.png" )));
	ImageIcon TrollBackRun2 = new ImageIcon ((getClass().getResource( "/Images/TrollBackRun2.png" )));

	ImageIcon TrollLeftStandby = new ImageIcon ((getClass().getResource( "/Images/TrollLeftStandby.png" )));
	ImageIcon TrollLeftRun1 = new ImageIcon ((getClass().getResource( "/Images/TrollLeftRun1.png" )));
	ImageIcon TrollLeftRun2 = new ImageIcon ((getClass().getResource( "/Images/TrollLeftRun2.png" )));

	ImageIcon TrollRightStandby = new ImageIcon ((getClass().getResource( "/Images/TrollRightStandby.png" )));
	ImageIcon TrollRightRun1 = new ImageIcon ((getClass().getResource( "/Images/TrollRightRun1.png" )));
	ImageIcon TrollRightRun2 = new ImageIcon ((getClass().getResource( "/Images/TrollRightRun2.png" )));
	
	ImageIcon img = TrollFrontStandby;
	

	private int ySpeed=0;
	private int xSpeed=0;
	private boolean aggro;
	@SuppressWarnings("unused")
	private int playerX;
	@SuppressWarnings("unused")
	private int playerY;
	@SuppressWarnings("unused")
	private boolean playerHit;
	private boolean yPriority;
	private boolean xPriority;
	private boolean playerXBigger;
	private boolean playerYBigger;
	
	private long idleTimerStart;
	private long idleTimerEnd;
	private long wantedTimer = 200;
	
	Rectangle2D.Double troll; 
	Ellipse2D.Double aggroCircle;
	Rectangle2D.Double healthbar;
	
	Random rand = new Random();
	
	public Troll(GamePanel panel){
		setName();
		setPlayable();
		setMonsterDamage();
		setHealth();
		setDamage();
		setScore();
		setXPosition(START_TWOX);
		setYPosition(START_TWOY);
		setDealDamage(true);
		setTakeDamage(true);
		setRanged();
		
		idleTimer();
		troll = new Rectangle2D.Double(yPos, xPos, TROLL_SIZE, TROLL_SIZE);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-TROLL_SIZE)/2, xPos-(AGGRO_RANGE-TROLL_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		healthbar = new Rectangle2D.Double(troll.getX(), (troll.getY() - 50), getHealth(), 10);
		this.panel = panel;
		setAggro();
	}
	
	public Troll(GamePanel panel, int x, int y){
		setName();
		setPlayable();
		setMonsterDamage();
		setHealth();
		setDamage();
		setScore();
		setXPosition(x);
		setYPosition(y);
		setDealDamage(true);
		setTakeDamage(true);
		setRanged();
		
		idleTimer();
		troll = new Rectangle2D.Double(yPos, xPos, TROLL_SIZE, TROLL_SIZE);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-TROLL_SIZE)/2, xPos-(AGGRO_RANGE-TROLL_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		healthbar = new Rectangle2D.Double(troll.getX(), (troll.getY() - 50), getHealth(), 10);
		this.panel = panel;
		setAggro();
	}

	@Override
	public void setName() {
		this.name = "Troll";
	}

	@Override
	public void setPlayable() {
		playable = false;
	}

	@Override
	public void setHealth() {
		this.health = 80;
	}

	@Override
	public void reduceHealth(int DMG_TAKEN) {
		this.health-=DMG_TAKEN;
	}

	@Override
	public void setDirection(int DIRECTION) {
		// Ignore
	}

	@Override
	public void setXPosition(int XPOS) {
		this.xPos = XPOS;
	}

	@Override
	public void setYPosition(int YPOS) {
		this.yPos = YPOS;
	}

	@Override
	public void setAttackSpeed() {
		// Ignore
	}

	@Override
	public void setDamage() {
		this.dmg = 8;
	}

	@Override
	public void setScore() {
		this.score = 200;
	}

	@Override
	public void setDealDamage(boolean DEAL_DAMAGE) {
		dealDamage = DEAL_DAMAGE;
	}

	@Override
	public void setTakeDamage(boolean TAKE_DAMAGE) {
		takeDamage = TAKE_DAMAGE;
	}

	@Override
	public void setRanged() {
		ranged = false;
	}

	@Override
	public void attack(int ATTACKTYPE) {
		// Ignore
	}

	int walkTic = 0;
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		//g.fill(aggroCircle);
		g.setColor(Color.LIGHT_GRAY);
		if (isAlive()){
			//g.fill(troll);
			if(aggro && panel.player.yPos>yPos && panel.player.xPos<xPos+70 && panel.player.xPos>xPos-70){
				if(walkTic<5){
					img = TrollFrontRun1;
					walkTic++;
				}else if(walkTic<10){
					img = TrollFrontRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
				
			}else if(aggro && panel.player.yPos<yPos && panel.player.xPos<xPos+70 && panel.player.xPos>xPos-70){
				if(walkTic<5){
					img = TrollBackRun1;
					walkTic++;
				}else if(walkTic<10){
					img = TrollBackRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}
			else if(aggro && panel.player.xPos>xPos){
				if(walkTic<5){
					img = TrollRightRun1;
					walkTic++;
				}else if(walkTic<10){
					img = TrollRightRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}else if(aggro && panel.player.xPos<xPos){
				if(walkTic<5){
					img = TrollLeftRun1;
					walkTic++;
				}else if(walkTic<10){
					img = TrollLeftRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}
			
			else{
				if(panel.player.yPos>yPos){
					img = TrollFrontStandby;
				}else if(panel.player.yPos<yPos){
					img = TrollBackStandby;
				}else{
					img = TrollFrontStandby;
				}
			}
			g.drawImage(img.getImage(), xPos-30 ,yPos-50, 140, 140, null);
			
			g.setColor(Color.RED);
			g.fill(healthbar);
		}
	}

	@Override
	public void move() {
		idleTimerEnd = System.currentTimeMillis();
		if (!getAggro()){
			if (idleTimerEnd - getIdleTimer() > wantedTimer){
				idle();
				idleTimer();
			}
		}
		else {
			aggroed();
		}
	}

	@Override
	public void idle() {
		// TODO Auto-generated method stub
		xSpeed = TROLL_IDLE_SPEED;
		ySpeed = TROLL_IDLE_SPEED;
		
		int direction = rand.nextInt(200);
		//System.out.println(direction);
		if (direction < 25){
			//Right and down
			if (checkRightWall() && checkBotWall()){
				xPos+=-xSpeed;
				yPos+=-ySpeed;
			}
			else if (checkRightWall()){
				xPos+=0;
				yPos+=ySpeed;
			}
			else if (checkBotWall()){
				xPos+=xSpeed;
				yPos+=0;
			}
			else{
				xPos+=xSpeed;
				yPos+=ySpeed;
			}
		}
		else if (direction < 50) {
			//Right and up
			if (checkRightWall() && checkTopWall()){
				xPos+=xSpeed;
				yPos+=-ySpeed;
			}
			else if (checkRightWall()){
				xPos+=0;
				yPos+=ySpeed;
			}
			else if (checkTopWall()){
				xPos+=xSpeed;
				yPos+=-0;
			}
			else {
				xPos+=xSpeed;
				yPos+=-ySpeed;
			}
		}
		else if (direction < 75) {
			//Left and down
			if (checkRightWall() && checkBotWall()){
				xPos+=xSpeed;
				yPos+=ySpeed;
			}
			else if (checkLeftWall()){
				xPos+=-0;
				yPos+=ySpeed;
			}
			else if (checkBotWall()){
				xPos+=xSpeed;
				yPos+=0;
			}
			else {
				xPos+=-xSpeed;
				yPos+=ySpeed;
			}
		}
		else if (direction < 100){
			//Left and up
			if (checkLeftWall() && checkTopWall()){
				xPos+=-xSpeed;
				yPos+=-ySpeed;
			}
			else if (checkLeftWall()){
				xPos+=-0;
				yPos+=-ySpeed;
			}
			else if (checkTopWall()){
				xPos+=-xSpeed;
				yPos+=-0;
			}
			else {
				xPos+=-xSpeed;
				yPos+=-ySpeed;
			}
		}		
	}

	@Override
	public void aggroed() {
		if (yPriority){
			if (!playerYBigger){
				yPos += -TROLL_AGGRO_SPEED;
			}
			else {
				yPos += TROLL_AGGRO_SPEED;
			}
		}
		else if (xPriority){
			if (!playerXBigger){
				xPos += -TROLL_AGGRO_SPEED;
			}
			else {
				xPos += TROLL_AGGRO_SPEED;
			}
		}
		else {
			if (!playerXBigger && !playerYBigger){
				xPos += -TROLL_AGGRO_SPEED;
				yPos += -TROLL_AGGRO_SPEED;
			}
			else if(!playerXBigger && playerYBigger){
				xPos += -TROLL_AGGRO_SPEED;
				yPos += TROLL_AGGRO_SPEED;
			}
			else if (playerXBigger && !playerYBigger){
				xPos += TROLL_AGGRO_SPEED;
				yPos += -TROLL_AGGRO_SPEED;
			}
			else if (playerXBigger && playerYBigger) {
				xPos += TROLL_AGGRO_SPEED;
				yPos += TROLL_AGGRO_SPEED;
			}
		}
	}

	@Override
	public void setAggro() {
		if (checkIntersecting()){
			aggro = true;
			playerX = panel.player.getXPosition();
			playerY = panel.player.getYPosition();
			//System.out.println(aggro);
		}
		else {
			aggro = false;
		}
	}

	@Override
	public boolean getAggro() {
		return aggro;
	}

	@Override
	public boolean checkIntersecting() {
		if (aggroCircle.intersects(panel.player.player)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void update() {
		if (isAlive()){
		checkIntersecting();
		setAggro();
		setXPriority();
		setYPriority();
		move();
		knockBack();
		playerKnockback();
		//System.out.println("XPRI: " + getXPriority() + " " + "YPRI: " + getYPriority());
		//System.out.println(playerYBigger + "Y");
		//System.out.println(playerXBigger + "X");
		troll = new Rectangle2D.Double(xPos, yPos, TROLL_SIZE, TROLL_SIZE);
		healthbar = new Rectangle2D.Double(troll.getX()+5, (troll.getY() - 50), getHealth(), 10);
		aggroCircle = new Ellipse2D.Double(xPos-(AGGRO_RANGE-TROLL_SIZE)/2, yPos-(AGGRO_RANGE-TROLL_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		}
		else {
			addScore();
		}
	}

	@Override
	public void knockBack() {
		if(!troll.intersects(panel.arena.rightWall) && !troll.intersects(panel.arena.topWall) && !troll.intersects(panel.arena.bottomWall) && !troll.intersects(panel.arena.leftWall)){
		if (isAlive()){
			if (troll.intersects(panel.player.attack1)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (TROLL_SIZE / 2);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(TROLL_SIZE / 2);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (TROLL_SIZE / 2);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(TROLL_SIZE / 2);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (TROLL_SIZE / 2);
						yPos += (TROLL_SIZE / 2);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (TROLL_SIZE / 2);
						yPos += -(TROLL_SIZE / 2);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(TROLL_SIZE / 2);
						yPos += (TROLL_SIZE / 2);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(TROLL_SIZE / 2);
						yPos += -(TROLL_SIZE / 2);
					}
				}
				reduceHealth(10);
			}
			else if (panel.player.attack2.intersects(troll)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (TROLL_SIZE / 2);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(TROLL_SIZE / 2);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (TROLL_SIZE / 2);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(TROLL_SIZE / 2);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (TROLL_SIZE / 2);
						yPos += (TROLL_SIZE / 2);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (TROLL_SIZE / 2);
						yPos += -(TROLL_SIZE / 2);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(TROLL_SIZE / 2);
						yPos += (TROLL_SIZE / 2);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(TROLL_SIZE / 2);
						yPos += -(TROLL_SIZE / 2);
					}
				}
				reduceHealth(3);
			}
		}
		}
	}
	
	
	public boolean isAlive(){
		if (getHealth() > 0){
			return true;
		}
		else {
			setDealDamage(false);
			setTakeDamage(false);
			return false;
		}
	}

	public void playerKnockback(){
		if (getDealDamage()){
			if (troll.intersects(panel.player.player)){
				panel.player.iHitYou((int)(troll.getX()+(troll.getWidth()/2)), (int)(troll.getY()+(troll.getHeight()/2)));
				panel.player.reduceHealth(this.getDamage());
			}	
		}
	}
	
	@Override
	public void setYPriority() {
		if (getXPosition() +  (TROLL_SIZE/2) > panel.player.getXPosition() && getXPosition() +  (TROLL_SIZE/2) < panel.player.getXPosition()+ panel.player.playerSize){
			yPriority = true;
			if (getXPosition() + (TROLL_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
				playerXBigger = false;
			}
			else {
				playerXBigger = true;
			}
		}
		else {
			yPriority = false;
			if (getXPosition() + (TROLL_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
				playerXBigger = false;
			}
			else {
				playerXBigger = true;
			}
		}
		//System.out.println(yPriority);
	}

	@Override
	public void setXPriority() {
		if (getYPosition() + (TROLL_SIZE/2) > panel.player.getYPosition() && getYPosition()+  (TROLL_SIZE/2) < panel.player.getYPosition()+ panel.player.playerSize){
			xPriority = true;
			if (getYPosition() + (TROLL_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
				playerYBigger = false;
			}
			else {
				playerYBigger = true;
			}
		}
		else {
			xPriority = false;
			if (getYPosition() + (TROLL_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
				playerYBigger = false;
			}
			else {
				playerYBigger = true;
			}
		}
		//System.out.println(xPriority);
	}

	@Override
	public boolean getYPriority() {
		return yPriority;
	}

	@Override
	public boolean getXPriority() {
		return xPriority;
	}
	
	public void idleTimer(){
		idleTimerStart = System.currentTimeMillis();
	}
	
	public long getIdleTimer(){
		return idleTimerStart;
	}

	@Override
	public boolean checkTopWall() {
		if (troll.intersects(panel.arena.topWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkBotWall() {
		if (troll.intersects(panel.arena.bottomWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkLeftWall() {
		if (troll.intersects(panel.arena.leftWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkRightWall() {
		if (troll.intersects(panel.arena.rightWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void addScore() {
		panel.highscoreList.addPoints(getScore());
		this.score = 0;
	}

}
