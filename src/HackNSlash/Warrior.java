package HackNSlash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Warrior extends Avatar {
	
	public GamePanel panel;
	
	
	ImageIcon HeroFrontStandby = new ImageIcon ((getClass().getResource( "/Images/HeroFrontStandby.png" )));
	ImageIcon HeroFrontRun1 = new ImageIcon ((getClass().getResource( "/Images/HeroFrontRun1.png" )));
	ImageIcon HeroFrontRun2 = new ImageIcon ((getClass().getResource( "/Images/HeroFrontRun2.png" )));
	ImageIcon HeroFrontSwosh1 = new ImageIcon ((getClass().getResource( "/Images/HeroFrontSwosh1.png" )));
	ImageIcon HeroFrontSwosh2 = new ImageIcon ((getClass().getResource( "/Images/HeroFrontSwosh2.png" )));
	
	ImageIcon HeroBackStandby = new ImageIcon ((getClass().getResource( "/Images/HeroBackStandby.png" )));
	ImageIcon HeroBackRun1 = new ImageIcon ((getClass().getResource( "/Images/HeroBackRun1.png" )));
	ImageIcon HeroBackRun2 = new ImageIcon ((getClass().getResource( "/Images/HeroBackRun2.png" )));
	ImageIcon HeroBackSwosh1 = new ImageIcon ((getClass().getResource( "/Images/HeroBackSwosh1.png" )));
	ImageIcon HeroBackSwosh2 = new ImageIcon ((getClass().getResource( "/Images/HeroBackSwosh2.png" )));
	
	ImageIcon HeroLeftStandby = new ImageIcon ((getClass().getResource( "/Images/HeroLeftStandby.png" )));
	ImageIcon HeroLeftRun1 = new ImageIcon ((getClass().getResource( "/Images/HeroLeftRun1.png" )));
	ImageIcon HeroLeftRun2 = new ImageIcon ((getClass().getResource( "/Images/HeroLeftRun2.png" )));
	ImageIcon HeroLeftSwosh1 = new ImageIcon ((getClass().getResource( "/Images/HeroLeftSwosh1.png" )));
	ImageIcon HeroLeftSwosh2 = new ImageIcon ((getClass().getResource( "/Images/HeroLeftSwosh2v3.png" )));
	
	ImageIcon HeroRightStandby = new ImageIcon ((getClass().getResource( "/Images/HeroRightStandby.png" )));
	ImageIcon HeroRightRun1 = new ImageIcon ((getClass().getResource( "/Images/HeroRightRun1.png" )));
	ImageIcon HeroRightRun2 = new ImageIcon ((getClass().getResource( "/Images/HeroRightRun2.png" )));
	ImageIcon HeroRightSwosh1 = new ImageIcon ((getClass().getResource( "/Images/HeroRightSwosh1.png" )));
	ImageIcon HeroRightSwosh2 = new ImageIcon ((getClass().getResource( "/Images/HeroRightSwosh2v4.png" )));
	
	ImageIcon HeroSpin1 = new ImageIcon ((getClass().getResource( "/Images/HeroSpin1.png" )));
	ImageIcon HeroSpin2 = new ImageIcon ((getClass().getResource( "/Images/HeroSpin2.png" )));
	
	ImageIcon img = HeroFrontStandby;
	
	
	
	private boolean walkUp = false;
	private boolean walkDown = false;
	private boolean walkLeft = false;
	private boolean walkRight = false;
	
	private boolean faceUp = false;
	private boolean faceDown = true;
	private boolean faceLeft = false;
	private boolean faceRight = false;
	
	private boolean hitUp = false;
	private boolean hitDown = false;
	private boolean hitLeft = false;
	private boolean hitRight = false;
	
	private long cooldownInMillis = 500;
	private long cooldownStart = 0;
	
	private long attackLengthInMillis = 300;
	
	private boolean attack1On = false;
	private boolean attack2On = false;
	private int attack1Length = 130;
	private int attack1Width = 50;
	private int attack2Diameter = 150;
	public int attack1Damage = 4;
	public int attack2Damage = 1;
	
	private int ySpeed=0;
	private int xSpeed=0;
	
	private int acceleration = 2;
	private int maxSpeed = 10;
	
	public int playerSize = 60;
	Rectangle2D.Double player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
	
	Rectangle2D.Double attack1 = new Rectangle2D.Double(-1000,-1000,10,10);
	Ellipse2D.Double attack2 = new Ellipse2D.Double(-1000,-1000,10,10);
	
	private long lastTookDamage = System.currentTimeMillis();
	private long takeDamageCooldown = 1000;
	
	private int knockbackSpeed = 20;
	

	
	//****JOACHIM****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM****
	public int health = 100;
	private double healthbarWidthCalc = health * 5.88;
	private int healthbarWidth = (int) healthbarWidthCalc;

	private int healthbarFrameWidth = panel.SCREEN_WIDTH/2; //600
	private int healthbarFrameHeight = 40; //25
	private int healthbarFrameXPos = panel.SCREEN_WIDTH/4; //300
	private int healthbarFrameYPos = panel.SCREEN_HEIGHT-(43+healthbarFrameHeight); //625
	Rectangle2D.Double healthbarFrame = new Rectangle2D.Double(healthbarFrameXPos, healthbarFrameYPos, healthbarFrameWidth, healthbarFrameHeight);

	private int healthbarBackgroundXPos = healthbarFrameXPos + 5; //305
	private int healthbarBackgroundYPos = healthbarFrameYPos + 5; //630
	private int healthbarBackgroundWidth = healthbarFrameWidth - 10; //590
	private int healthbarBackgroundHeight = healthbarFrameHeight - 10; //15
	Rectangle2D.Double healthbarBackground = new Rectangle2D.Double(healthbarBackgroundXPos, healthbarBackgroundYPos, healthbarBackgroundWidth, healthbarBackgroundHeight);

	private int healthbarValueXPos = healthbarBackgroundXPos + 1; //306
	private int healthbarValueYPos = healthbarBackgroundYPos + 1; //631
	private int healthbarValueWidth = healthbarWidth;
	private int healthbarValueHeight = healthbarBackgroundHeight -2; //13
	Rectangle2D.Double healthbarValue = new Rectangle2D.Double(healthbarValueXPos, healthbarValueYPos, healthbarValueWidth, healthbarValueHeight);

	private void updateHealthbar(){                    
	        healthbarWidthCalc = health * 5.88;
	        healthbarValueWidth = (int) healthbarWidthCalc;
	        healthbarValue = new Rectangle2D.Double(healthbarValueXPos, healthbarValueYPos, healthbarValueWidth, healthbarValueHeight);
	}

	//****JOACHIM****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM*****JOACHIM****
	
	public void reset(){
		gainHealth();
	}
	
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
		updateHealthbar();
		move();
		whereToFace();
		turnOffAttack();
		makeAttackFollowPlayer();
		yPos+=ySpeed;
		xPos+=xSpeed;
		player = new Rectangle2D.Double(xPos,yPos,playerSize,playerSize);
		updateHealthbar();
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
		if(System.currentTimeMillis()-lastTookDamage>takeDamageCooldown){
	        this.health = health - DMG_TAKEN;
	        if(health<=0){
	        }
	        lastTookDamage = System.currentTimeMillis();
		}
	}

	public void gainHealth(){
	        this.health = 100;
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
			//System.out.println("FORTSATT COOLDOWN!");
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
			hitUp = false;
			hitDown = false;
			hitLeft = false;
			hitRight = false;
		}
		if(System.currentTimeMillis()-cooldownStart > attackLengthInMillis){
			attack2 = new Ellipse2D.Double(-1000,-1000,10,10);
			attack2On = false;
			hitUp = false;
			hitDown = false;
			hitLeft = false;
			hitRight = false;
		}
	}
	
	public void makeAttackFollowPlayer(){
		if(attack1On && faceUp){
			attack1 = new Rectangle2D.Double(xPos-((attack1Length-playerSize)/2),
					yPos-attack1Width,attack1Length,attack1Width);
			hitUp = true;
			hitDown = false;
			hitLeft = false;
			hitRight = false;
		}else if(attack1On && faceDown){
			attack1 = new Rectangle2D.Double(xPos-((attack1Length-playerSize)/2),
					yPos+playerSize,attack1Length,attack1Width);
			hitUp = false;
			hitDown = true;
			hitLeft = false;
			hitRight = false;
		}else if(attack1On && faceLeft){
			attack1 = new Rectangle2D.Double(xPos-attack1Width,
					yPos-((attack1Length-playerSize)/2),attack1Width,attack1Length);
			hitUp = false;
			hitDown = false;
			hitLeft = true;
			hitRight = false;
		}else if(attack1On && faceRight){
			attack1 = new Rectangle2D.Double(xPos+playerSize,
					yPos-((attack1Length-playerSize)/2),attack1Width,attack1Length);
			hitUp = false;
			hitDown = false;
			hitLeft = false;
			hitRight = true;
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
	
	public void iHitYou(int x, int y){
		if(System.currentTimeMillis()-lastTookDamage>takeDamageCooldown){
			int middleX = xPos+(playerSize/2);
			int middleY = yPos+(playerSize/2);
			//POSISJONEN SOM GIES SKAL VÆRE MIDTEN AV MONSTERET!
			if(x<middleX && (y>yPos && y<yPos+playerSize)){
				xSpeed+=knockbackSpeed;
			}else if(x>middleX && (y>yPos && y<yPos+playerSize)){
				xSpeed-=knockbackSpeed;
			}else if(y<middleY && (x>xPos && x<xPos+playerSize)){
				ySpeed+=knockbackSpeed;
			}else if(y>middleY && (x>xPos && x<xPos+playerSize)){
				ySpeed-=knockbackSpeed;
			}else if(x<middleX){
				xSpeed+=knockbackSpeed;
			}else if(x>middleX){
				xSpeed-=knockbackSpeed;
			}else if(y<middleY){
				ySpeed+=knockbackSpeed;
			}else if(y>middleY){
				ySpeed-=knockbackSpeed;
			}
		}
		
	}

	
	int runTick = 0;
	int hitTick = 0;
	int spaceTicks = 3;
	@Override
	public void paint(Graphics2D g) {
		
		
		// TODO Auto-generated method stub
		if(faceUp){
			img = HeroBackStandby;
			if(!attack1On && !attack2On){
				hitTick = 0;
			}
		}else if(faceDown){
			img = HeroFrontStandby;
			if(!attack1On && !attack2On){
				hitTick = 0;
			}
		}else if(faceLeft){
			img = HeroLeftStandby;
			if(!attack1On && !attack2On){
				hitTick = 0;
			}
		}else if(faceRight){
			img = HeroRightStandby;
			if(!attack1On && !attack2On){
				hitTick = 0;
			}
		}
		if(walkUp){
			if(runTick<spaceTicks){
				runTick++;
				img = HeroBackRun1;
			}else if (runTick<spaceTicks*2){
				img = HeroBackRun2;
				runTick++;
			}else{
				runTick=0;
			}
		}else if(walkDown){
			if(runTick<spaceTicks){
				img = HeroFrontRun1;
				runTick++;
			}else if(runTick<spaceTicks*2){
				img = HeroFrontRun2;
				runTick++;
			}else{
				runTick=0;
			}
		}else if(walkLeft){
			if(runTick<spaceTicks){
				img = HeroLeftRun1;
				runTick++;
			}else if(runTick<spaceTicks*2){
				img = HeroLeftRun2;
				runTick++;
			}else{
				runTick = 0;
			}
		}else if(walkRight){
			if(runTick<spaceTicks){
				img = HeroRightRun1;
				runTick++;
			}else if(runTick<spaceTicks*2){
				img = HeroRightRun2;
				runTick++;
			}else{
				runTick = 0;
			}
		}
		if(hitUp){
			if(hitTick<spaceTicks){
				img = HeroBackSwosh1;
				hitTick++;
			}else if(hitTick<spaceTicks*2){
				img = HeroBackSwosh2;
				hitTick++;
			}else{
				hitTick = 0;
			}
		}else if(hitDown){
			if(hitTick<spaceTicks){
				img = HeroFrontSwosh1;
				hitTick++;
			}else if(hitTick<spaceTicks*2){
				img = HeroFrontSwosh2;
				hitTick++;
			}else{
				hitTick = 0;
			}
		}else if(hitRight){
			if(hitTick<spaceTicks-1){
				img = HeroRightSwosh1;
				hitTick++;
			}else if(hitTick<spaceTicks*2){
				img = HeroRightSwosh2;
				hitTick++;
			}else{
				hitTick = 0;
			}
		}else if(hitLeft){
			if(hitTick<spaceTicks){
				img = HeroLeftSwosh1;
				hitTick++;
			}else if(hitTick<spaceTicks*2){
				img = HeroLeftSwosh2;
				hitTick++;
			}else{
				hitTick = 0;
			}
		}
		if(attack2On){
			if(hitTick<spaceTicks){
				img = HeroSpin1;
				hitTick++;
			}else if(hitTick<spaceTicks*2){
				img = HeroSpin2;
				hitTick++;
			}else{
				hitTick = 0;
			}
		}
		
		//g.fill(player);
		//g.fill(attack1);
		//g.fill(attack2);
		if(attack2On){
			g.drawImage(img.getImage(), xPos-74 ,yPos-70, 210, 190, null);
		}else{
			g.drawImage(img.getImage(), xPos-44 ,yPos-55, 150, 150, null);
		}
		
		
		//JOACHIM
		g.setColor(Color.ORANGE);
        g.fill(healthbarFrame);
        g.setColor(Color.darkGray);
        g.fill(healthbarBackground);
        g.setColor(Color.RED);
        g.fill(healthbarValue);
        g.setColor(Color.BLACK);
	}


}
