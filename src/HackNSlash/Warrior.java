package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Warrior extends Avatar {
	
	public GamePanel panel;
	
	private boolean walkUp = false;
	private boolean walkDown = false;
	private boolean walkLeft = false;
	private boolean walkRight = false;
	
	private int ySpeed=0;
	private int xSpeed=0;
	
	private int acceleration = 1;
	private int maxSpeed = 8;
	
	public int playerSize = 80;
	Rectangle2D.Double player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
	
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
		checkIntersecting();
		move();
		yPos+=ySpeed;
		xPos+=xSpeed;
		player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
	}
	
	public void checkIntersecting(){
		if(player.intersects(panel.arena.rightWall)){
			walkRight=false;
		}
		if(player.intersects(panel.arena.leftWall)){
			walkLeft=false;
		}
		if(player.intersects(panel.arena.bottomWall)){
			walkDown=false;
		}
		if(player.intersects(panel.arena.topWall)){
			walkUp=false;
		}
	}
	
	public void move(){
		if(!walkRight && !walkLeft){
			xSpeed=0;
		}
		if(!walkUp && !walkDown){
			ySpeed=0;
			
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


	@Override
	public void attack(int ATTACKTYPE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fill(player);
		
	}


}
