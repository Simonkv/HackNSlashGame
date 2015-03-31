package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Warrior extends Avatar {
	
	public GamePanel panel;
	
	private boolean walkUp = false;
	private boolean walkDown = false;
	private boolean walkLeft = false;
	private boolean walkRight = false;
	
	private boolean faceUp = false;
	private boolean faceDown = true;
	private boolean faceLeft = false;
	private boolean faceRight = false;
	
	private long cooldownInMillis = 500;
	private long cooldownStart = 0;
	
	private long attackLengthInMillis = 200;
	
	private boolean attack1On = false;
	private boolean attack2On = false;
	private int attack1Length = 130;
	private int attack1Width = 65;
	private int attack2Diameter = 200;
	
	private int ySpeed=0;
	private int xSpeed=0;
	
	private int acceleration = 2;
	private int maxSpeed = 10;
	
	public int playerSize = 80;
	Rectangle2D.Double player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
	
	Rectangle2D.Double attack1 = new Rectangle2D.Double(-1000,-1000,10,10);
	Ellipse2D.Double attack2 = new Ellipse2D.Double(-1000,-1000,10,10);
	

	
	public Warrior(GamePanel panel,int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		this.panel = panel;
	}
	
	public void leftPressed(){
		walkLeft=true;
	}
	public void leftReleased(){
		walkLeft=false;
	}
	public void rightPressed(){
		walkRight=true;
	}
	public void rightReleased(){
		walkRight=false;
	}
	public void upPressed(){
		walkUp=true;
	}
	public void upReleased(){
		walkUp=false;
	}
	public void downPressed(){
		walkDown=true;
	}
	public void downReleased(){
		walkDown=false;
	}
	
	
	public void update(){
		//System.out.println("UP: "+faceUp+"\nDOWN: "+faceDown+"\nLEFT: "+faceLeft+"\nRIGHT: "+faceRight);
		checkIntersecting();
		move();
		whereToFace();
		turnOffAttack();
		makeAttackFollowPlayer();
		yPos+=ySpeed;
		xPos+=xSpeed;
		player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
	}
	
	public void whereToFace(){
		if(xSpeed>0){
			faceUp = false;
			faceDown = false;
			faceLeft = false;
			faceRight = true;
		}else if(xSpeed<0){
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
	
	public void checkIntersecting(){
		if(player.intersects(panel.arena.rightWall)){
			walkRight=false;
			xSpeed=0;
		}
		if(player.intersects(panel.arena.leftWall)){
			walkLeft=false;
			xSpeed=0;
		}
		if(player.intersects(panel.arena.bottomWall)){
			walkDown=false;
			ySpeed=0;
		}
		if(player.intersects(panel.arena.topWall)){
			walkUp=false;
			ySpeed=0;
		}
	}
	
	public int goToZero(int speed){
		if(speed>0){
			return -1;
		}else if(speed<0){
			return 1;
		}else{
			return 0;
		}
	}
	
	public void move(){
		if(!walkRight && !walkLeft){
			xSpeed+=goToZero(xSpeed);
		}
		if(!walkUp && !walkDown){
			ySpeed+=goToZero(ySpeed);
		}
		if(walkUp){
			ySpeed-=acceleration;
			if(ySpeed<-maxSpeed){
				ySpeed=-maxSpeed;
			}
		}else if(walkDown){
			ySpeed+=acceleration;
			if(ySpeed>maxSpeed){
				ySpeed=maxSpeed;
			}
		}
		if(walkLeft){
			xSpeed-=acceleration;
			if(xSpeed<-maxSpeed){
				xSpeed=-maxSpeed;
			}
		}else if(walkRight){
			xSpeed+=acceleration;
			if(xSpeed>maxSpeed){
				xSpeed=maxSpeed;
			}
		}
	}
	
	
	
	@Override
	public void setName() {
		this.name = "Warrior";
		
	}

	@Override
	public void setPlayable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduceHealth(int DMG_TAKEN) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirection(int DIRECTION) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXPosition(int XPOS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYPosition(int YPOS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttackSpeed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDamage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDealDamage(boolean DEAL_DAMAGE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTakeDamage(boolean TAKE_DAMAGE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRanged() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean attackOffCooldown(){
		if(System.currentTimeMillis()-cooldownStart<cooldownInMillis){
			return false;
		}else{
			return true;
		}
	}
	public void startCooldownTimer(){
		cooldownStart = System.currentTimeMillis();
	}
	
	public void turnOffAttack(){
		if(System.currentTimeMillis()-cooldownStart > attackLengthInMillis){
			attack1 = new Rectangle2D.Double(-1000,-1000,10,10);
			attack1On = false;
		}
		if(System.currentTimeMillis()-cooldownStart > attackLengthInMillis){
			attack2 = new Ellipse2D.Double(-1000,-1000,10,10);
			attack2On = false;
		}
	}
	
	public void makeAttackFollowPlayer(){
		if(attack1On && faceUp){
			attack1 = new Rectangle2D.Double(xPos-((attack1Length-playerSize)/2),
					yPos-attack1Width,attack1Length,attack1Width);
		}else if(attack1On && faceDown){
			attack1 = new Rectangle2D.Double(xPos-((attack1Length-playerSize)/2),
					yPos+playerSize,attack1Length,attack1Width);
		}else if(attack1On && faceLeft){
			attack1 = new Rectangle2D.Double(xPos-attack1Width,
					yPos-((attack1Length-playerSize)/2),attack1Width,attack1Length);
		}else if(attack1On && faceRight){
			attack1 = new Rectangle2D.Double(xPos+playerSize,
					yPos-((attack1Length-playerSize)/2),attack1Width,attack1Length);
		}
		
		
		
		else if(attack2On){
			attack2 = new Ellipse2D.Double(xPos-(attack2Diameter-playerSize)/2, yPos-(attack2Diameter-playerSize)/2, attack2Diameter, attack2Diameter);
		}
	}


	@Override
	public void attack(int ATTACKTYPE) {
		if(ATTACKTYPE==1){
			if(attackOffCooldown()){
				attack1On = true;
				startCooldownTimer();
			}
		}else if(ATTACKTYPE == 2){
			if(attackOffCooldown()){
				attack2On = true;
				startCooldownTimer();
			}
		}
		
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fill(player);
		g.fill(attack1);
		g.fill(attack2);
		
	}


}
