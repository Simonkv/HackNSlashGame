package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Goblin extends Avatar implements MonsterAI{

public GamePanel panel;


	ImageIcon GoblinFrontStandby = new ImageIcon ((getClass().getResource( "/Images/GoblinFrontStandby.png" )));
	ImageIcon GoblinFrontRun1 = new ImageIcon ((getClass().getResource( "/Images/GoblinFrontRun1.png" )));
	ImageIcon GoblinFrontRun2 = new ImageIcon ((getClass().getResource( "/Images/GoblinFrontRun2.png" )));
	
	ImageIcon GoblinBackStandby = new ImageIcon ((getClass().getResource( "/Images/GoblinBackStandby.png" )));
	ImageIcon GoblinBackRun1 = new ImageIcon ((getClass().getResource( "/Images/GoblinBackRun1.png" )));
	ImageIcon GoblinBackRun2 = new ImageIcon ((getClass().getResource( "/Images/GoblinBackRun2.png" )));
	
	ImageIcon GoblinLeftStandby = new ImageIcon ((getClass().getResource( "/Images/GoblinLeftStandby.png" )));
	ImageIcon GoblinLeftRun1 = new ImageIcon ((getClass().getResource( "/Images/GoblinLeftRun1.png" )));
	ImageIcon GoblinLeftRun2 = new ImageIcon ((getClass().getResource( "/Images/GoblinLeftRun2.png" )));
	
	ImageIcon GoblinRightStandby = new ImageIcon ((getClass().getResource( "/Images/GoblinRightStandby.png" )));
	ImageIcon GoblinRightRun1 = new ImageIcon ((getClass().getResource( "/Images/GoblinRightRun1.png" )));
	ImageIcon GoblinRightRun2 = new ImageIcon ((getClass().getResource( "/Images/GoblinRightRun2.png" )));
	
	ImageIcon img = GoblinFrontStandby;
	
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
	
	Rectangle2D.Double goblin; 
	Ellipse2D.Double aggroCircle;
	Rectangle2D.Double healthbar; 
	
	Random rand = new Random();
	
	public Goblin(GamePanel panel){
		setName();
		setPlayable();
		setMonsterDamage();
		setHealth();
		setDamage();
		setScore();
		setXPosition(START_THREEX);
		setYPosition(START_THREEY);
		setDealDamage(true);
		setTakeDamage(true);
		setRanged();
		
		idleTimer();
		goblin = new Rectangle2D.Double(yPos, xPos, GOBLIN_SIZE, GOBLIN_SIZE);
		healthbar = new Rectangle2D.Double(goblin.getX(), (goblin.getY() - 15), getHealth(), 10);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-GOBLIN_SIZE)/2, xPos-(AGGRO_RANGE-GOBLIN_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		this.panel = panel;
		setAggro();
	}
	
	public Goblin(GamePanel panel, int x, int y){
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
		goblin = new Rectangle2D.Double(yPos, xPos, GOBLIN_SIZE, GOBLIN_SIZE);
		healthbar = new Rectangle2D.Double(goblin.getX(), (goblin.getY() - 15), getHealth(), 10);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-GOBLIN_SIZE)/2, xPos-(AGGRO_RANGE-GOBLIN_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		this.panel = panel;
		setAggro();
	}

	@Override
	public void setName() {
		this.name = "Goblin";
	}

	@Override
	public void setPlayable() {
		playable = false;
	}

	@Override
	public void setHealth() {
		this.health = 40;
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
		this.dmg = 4;
	}

	@Override
	public void setScore() {
		this.score = 100;
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
		g.setColor(Color.CYAN);
		if (isAlive()){
			//g.fill(goblin);
			if(aggro && panel.player.yPos>yPos && panel.player.xPos<xPos+70 && panel.player.xPos>xPos-70){
				if(walkTic<5){
					img = GoblinFrontRun1;
					walkTic++;
				}else if(walkTic<10){
					img = GoblinFrontRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
				
			}else if(aggro && panel.player.yPos<yPos && panel.player.xPos<xPos+70 && panel.player.xPos>xPos-70){
				if(walkTic<5){
					img = GoblinBackRun1;
					walkTic++;
				}else if(walkTic<10){
					img = GoblinBackRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}
			else if(aggro && panel.player.xPos>xPos){
				if(walkTic<5){
					img = GoblinRightRun1;
					walkTic++;
				}else if(walkTic<10){
					img = GoblinRightRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}else if(aggro && panel.player.xPos<xPos){
				if(walkTic<5){
					img = GoblinLeftRun1;
					walkTic++;
				}else if(walkTic<10){
					img = GoblinLeftRun2;
					walkTic++;
				}else{
					walkTic=0;
				}
			}
			
			else{
				if(panel.player.yPos>yPos){
					img = GoblinFrontStandby;
				}else if(panel.player.yPos<yPos){
					img = GoblinBackStandby;
				}else{
					img = GoblinFrontStandby;
				}
			}
			g.drawImage(img.getImage(), xPos-15 ,yPos-20, 90, 90, null);
			
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
		xSpeed = GOBLIN_IDLE_SPEED;
		ySpeed = GOBLIN_IDLE_SPEED;
		
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
				yPos += -GOBLIN_AGGRO_SPEED;
			}
			else {
				yPos += GOBLIN_AGGRO_SPEED;
			}
		}
		else if (xPriority){
			if (!playerXBigger){
				xPos += -GOBLIN_AGGRO_SPEED;
			}
			else {
				xPos += GOBLIN_AGGRO_SPEED;
			}
		}
		else {
			if (!playerXBigger && !playerYBigger){
				xPos += -GOBLIN_AGGRO_SPEED;
				yPos += -GOBLIN_AGGRO_SPEED;
			}
			else if(!playerXBigger && playerYBigger){
				xPos += -GOBLIN_AGGRO_SPEED;
				yPos += GOBLIN_AGGRO_SPEED;
			}
			else if (playerXBigger && !playerYBigger){
				xPos += GOBLIN_AGGRO_SPEED;
				yPos += -GOBLIN_AGGRO_SPEED;
			}
			else if (playerXBigger && playerYBigger) {
				xPos += GOBLIN_AGGRO_SPEED;
				yPos += GOBLIN_AGGRO_SPEED;
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
		goblin = new Rectangle2D.Double(xPos, yPos, GOBLIN_SIZE, GOBLIN_SIZE);
		healthbar = new Rectangle2D.Double(goblin.getX()+7, (goblin.getY() - 30), getHealth(), 10);
		aggroCircle = new Ellipse2D.Double(xPos-(AGGRO_RANGE-GOBLIN_SIZE)/2, yPos-(AGGRO_RANGE-GOBLIN_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		}
		else {
			addScore();
		}
	}

	@Override
	public void knockBack() {
		if(!goblin.intersects(panel.arena.rightWall) && !goblin.intersects(panel.arena.topWall) && !goblin.intersects(panel.arena.bottomWall) && !goblin.intersects(panel.arena.leftWall)){
		if (isAlive()){
			if (goblin.intersects(panel.player.attack1)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (GOBLIN_SIZE);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(GOBLIN_SIZE);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (GOBLIN_SIZE);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(GOBLIN_SIZE);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (GOBLIN_SIZE);
						yPos += (GOBLIN_SIZE);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (GOBLIN_SIZE);
						yPos += -(GOBLIN_SIZE);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(GOBLIN_SIZE);
						yPos += (GOBLIN_SIZE);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(GOBLIN_SIZE);
						yPos += -(GOBLIN_SIZE);
					}
				}
				reduceHealth(10);
			}
			else if (panel.player.attack2.intersects(goblin)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (GOBLIN_SIZE);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(GOBLIN_SIZE);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (GOBLIN_SIZE);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(GOBLIN_SIZE);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (GOBLIN_SIZE);
						yPos += (GOBLIN_SIZE);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (GOBLIN_SIZE);
						yPos += -(GOBLIN_SIZE);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(GOBLIN_SIZE);
						yPos += (GOBLIN_SIZE);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(GOBLIN_SIZE);
						yPos += -(GOBLIN_SIZE);
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
			if (goblin.intersects(panel.player.player)){
				panel.player.iHitYou((int)(goblin.getX()+(goblin.getWidth()/2)), (int)(goblin.getY()+(goblin.getHeight()/2)));
				panel.player.reduceHealth(this.getDamage());
			}	
		}
	}

	@Override
	public void setYPriority() {
		if (getXPosition() +  (GOBLIN_SIZE/2) > panel.player.getXPosition() && getXPosition() +  (GOBLIN_SIZE/2) < panel.player.getXPosition()+ panel.player.playerSize){
			yPriority = true;
			if (getXPosition() + (GOBLIN_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
				playerXBigger = false;
			}
			else {
				playerXBigger = true;
			}
		}
		else {
			yPriority = false;
			if (getXPosition() + (GOBLIN_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
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
		if (getYPosition() + (GOBLIN_SIZE/2) > panel.player.getYPosition() && getYPosition()+  (GOBLIN_SIZE/2) < panel.player.getYPosition()+ panel.player.playerSize){
			xPriority = true;
			if (getYPosition() + (GOBLIN_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
				playerYBigger = false;
			}
			else {
				playerYBigger = true;
			}
		}
		else {
			xPriority = false;
			if (getYPosition() + (GOBLIN_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
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
		if (goblin.intersects(panel.arena.topWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkBotWall() {
		if (goblin.intersects(panel.arena.bottomWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkLeftWall() {
		if (goblin.intersects(panel.arena.leftWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkRightWall() {
		if (goblin.intersects(panel.arena.rightWall)){
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
