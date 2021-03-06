package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;


public class Slime extends Avatar implements MonsterAI{
	
	public GamePanel panel;
	
	ImageIcon Slime1 = new ImageIcon ((getClass().getResource( "/Images/Slime1.png" )));
	ImageIcon Slime2 = new ImageIcon ((getClass().getResource( "/Images/Slime2.png" )));
	
	private boolean faceUp = false;
	private boolean faceDown = true;
	private boolean faceLeft = false;
	private boolean faceRight = false;
	
	
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
	
	Rectangle2D.Double slime; 
	Ellipse2D.Double aggroCircle;
	Rectangle2D.Double healthbar;
	
	Random rand = new Random();
	
	public Slime(GamePanel panel){
		setName();
		setPlayable();
		setMonsterDamage();
		setHealth();
		setDamage();
		setScore();
		setXPosition(START_ONEX);
		setYPosition(START_ONEY);
		setDealDamage(true);
		setTakeDamage(true);
		setRanged();
		
		
		idleTimer();
		slime = new Rectangle2D.Double(yPos, xPos, SLIME_SIZE, SLIME_SIZE);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-SLIME_SIZE)/2, xPos-(AGGRO_RANGE-SLIME_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		healthbar = new Rectangle2D.Double(slime.getX(), slime.getY(), getHealth(), 10);
		this.panel = panel;
		setAggro();
	}
	
	public Slime(GamePanel panel, int x, int y){
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
		slime = new Rectangle2D.Double(yPos, xPos, SLIME_SIZE, SLIME_SIZE);
		aggroCircle = new Ellipse2D.Double(yPos-(AGGRO_RANGE-SLIME_SIZE)/2, xPos-(AGGRO_RANGE-SLIME_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
		healthbar = new Rectangle2D.Double(slime.getX(), slime.getY(), getHealth(), 10);
		this.panel = panel;
		setAggro();
	}

	@Override
	public void setName() {
		this.name = "Slime";
	}

	@Override
	public void setPlayable() {
		playable = false;
	}

	@Override
	public void setHealth() {
		this.health = 20;
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
		this.dmg = 2;
	}

	@Override
	public void setScore() {
		this.score = 50;
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

	int walkTic=0;
	ImageIcon img = new ImageIcon();
	@Override
	public void paint(Graphics2D g) {
		
		if(walkTic<5){
			img = Slime1;
			walkTic++;
		}else if(walkTic<10){
			img = Slime2;
			walkTic++;
		}else{
			walkTic=0;
		}
		
		//g.fill(aggroCircle);
		
		if (isAlive()){
			//g.fill(slime);
			g.drawImage(img.getImage(), xPos-5 ,yPos-5, 35, 35, null);
			
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
		xSpeed = SLIME_IDLE_SPEED;
		ySpeed = SLIME_IDLE_SPEED;
		
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
				yPos += -SLIME_AGGRO_SPEED;
				faceUp = true;
				faceDown = false;
				faceLeft = false;
				faceRight = false;
			}
			else {
				yPos += SLIME_AGGRO_SPEED;
				faceUp = false;
				faceDown = true;
				faceLeft = false;
				faceRight = false;
			}
		}
		else if (xPriority){
			if (!playerXBigger){
				xPos += -SLIME_AGGRO_SPEED;
				faceUp = false;
				faceDown = false;
				faceLeft = true;
				faceRight = false;
			}
			else {
				xPos += SLIME_AGGRO_SPEED;
				faceUp = false;
				faceDown = false;
				faceLeft = false;
				faceRight = true;
			}
		}
		else {
			if (!playerXBigger && !playerYBigger){
				xPos += -SLIME_AGGRO_SPEED;
				yPos += -SLIME_AGGRO_SPEED;
			}
			else if(!playerXBigger && playerYBigger){
				xPos += -SLIME_AGGRO_SPEED;
				yPos += SLIME_AGGRO_SPEED;
			}
			else if (playerXBigger && !playerYBigger){
				xPos += SLIME_AGGRO_SPEED;
				yPos += -SLIME_AGGRO_SPEED;
			}
			else if (playerXBigger && playerYBigger) {
				xPos += SLIME_AGGRO_SPEED;
				yPos += SLIME_AGGRO_SPEED;
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
	
	private void whereToFace(){
		if(playerXBigger){
			faceUp = false;
			faceDown = false;
			faceLeft = false;
			faceRight = true;
		}else if(playerYBigger){
			faceUp = false;
			faceDown = false;
			faceLeft = true;
			faceRight = false;
		}else if(ySpeed>0){
			faceUp = false;
			faceDown = true;
			faceLeft = false;
			faceRight = false;
		}else if(ySpeed<0){
			faceUp = true;
			faceDown = false;
			faceLeft = false;
			faceRight = false;
		}
	}

	@Override
	public void update() {
		if (isAlive()){
			checkIntersecting();
			setAggro();
			move();
			knockBack();
			playerKnockback();
			whereToFace();
			//System.out.println("XPRI: " + getXPriority() + " " + "YPRI: " + getYPriority());
			//System.out.println(playerYBigger + "Y");
			//System.out.println(playerXBigger + "X");
			//System.out.println(slime.getCenterY());
			//System.out.println(panel.player.getYPosition());
			setXPriority();
			setYPriority();
			//System.out.println(getHealth());
			slime = new Rectangle2D.Double(xPos, yPos, SLIME_SIZE, SLIME_SIZE);
			healthbar = new Rectangle2D.Double(slime.getX(), (slime.getY() - 15), getHealth(), 10);
			aggroCircle = new Ellipse2D.Double(xPos-(AGGRO_RANGE-SLIME_SIZE)/2, yPos-(AGGRO_RANGE-SLIME_SIZE)/2 , AGGRO_RANGE, AGGRO_RANGE);
			
		}
		else {
			addScore();
		}
	}

	@Override
	public void knockBack() {
		if(!slime.intersects(panel.arena.rightWall) && !slime.intersects(panel.arena.topWall) && !slime.intersects(panel.arena.bottomWall) && !slime.intersects(panel.arena.leftWall)){
		if (isAlive()){
			if (slime.intersects(panel.player.attack1)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (SLIME_SIZE * 3);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(SLIME_SIZE * 3);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (SLIME_SIZE * 3);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(SLIME_SIZE * 3);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (SLIME_SIZE * 3);
						yPos += (SLIME_SIZE * 3);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (SLIME_SIZE * 3);
						yPos += -(SLIME_SIZE * 3);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(SLIME_SIZE * 3);
						yPos += (SLIME_SIZE * 3);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(SLIME_SIZE * 3);
						yPos += -(SLIME_SIZE * 3);
					}
				}
				reduceHealth(10);
			}
			else if (panel.player.attack2.intersects(slime)){
				if (yPriority){
					if (!playerYBigger){
						if (checkBotWall()){
							yPos += 0;
						}
						else{
							yPos += (SLIME_SIZE * 3);
						}
					}
					else {
						if (checkTopWall()){
							yPos += 0;
						}
						else {
							yPos += -(SLIME_SIZE * 3);	
						}
					}
				}
				else if (xPriority){
					if (!playerXBigger){
						if (checkRightWall()){
							xPos += 0;
						}
						else {
							xPos += (SLIME_SIZE * 3);
						}
					}
					else {
						if (checkLeftWall()){
							xPos += 0;
						}
						else {
							xPos += -(SLIME_SIZE * 3);
						}
					}
				}
				else {
					if (!playerXBigger && !playerYBigger){
						xPos += (SLIME_SIZE * 3);
						yPos += (SLIME_SIZE * 3);
					}
					else if(!playerXBigger && playerYBigger){
						xPos += (SLIME_SIZE * 3);
						yPos += -(SLIME_SIZE * 3);
					}
					else if (playerXBigger && !playerYBigger){
						xPos += -(SLIME_SIZE * 3);
						yPos += (SLIME_SIZE * 3);
					}
					else if (playerXBigger && playerYBigger) {
						xPos += -(SLIME_SIZE * 3);
						yPos += -(SLIME_SIZE * 3);
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
			if (slime.intersects(panel.player.player)){
				panel.player.iHitYou((int)(slime.getX()+(slime.getWidth()/2)), (int)(slime.getY()+(slime.getHeight()/2)));
				panel.player.reduceHealth(this.getDamage());
			}	
		}
	}
	
	@Override
	public void setYPriority() {
		if (getXPosition() +  (SLIME_SIZE/2) > panel.player.getXPosition() && getXPosition() +  (SLIME_SIZE/2) < panel.player.getXPosition()+ panel.player.playerSize){
			yPriority = true;
			if (getXPosition() + (SLIME_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
				playerXBigger = false;
			}
			else {
				playerXBigger = true;
			}
		}
		else {
			yPriority = false;
			if (getXPosition() + (SLIME_SIZE/2) > panel.player.getXPosition()+(panel.player.playerSize/2)){
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
		if (getYPosition() + (SLIME_SIZE/2) > panel.player.getYPosition() && getYPosition()+  (SLIME_SIZE/2) < panel.player.getYPosition()+ panel.player.playerSize){
			xPriority = true;
			if (getYPosition() + (SLIME_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
				playerYBigger = false;
			}
			else {
				playerYBigger = true;
			}
		}
		else {
			xPriority = false;
			if (getYPosition() + (SLIME_SIZE/2) > panel.player.getYPosition()+(panel.player.playerSize/2)){
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
		if (slime.intersects(panel.arena.topWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkBotWall() {
		if (slime.intersects(panel.arena.bottomWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkLeftWall() {
		if (slime.intersects(panel.arena.leftWall)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkRightWall() {
		if (slime.intersects(panel.arena.rightWall)){
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
