package HackNSlash;

import java.awt.Graphics2D;

public abstract class Avatar {

	protected String name;
	protected int health;
	protected int score;
	protected int attackSpeed;
	protected int dmg;
	protected int xPos;
	protected int yPos;
	protected int dir;
	protected int movementSpeed;
	protected boolean dealDamage;
	protected boolean ranged;
	protected boolean playable;
	protected boolean takeDamage;
	protected boolean monsterDamage;
	
	
	public abstract void setName();
	
	public String getName(){
		return name;
	}
	
	public abstract void setPlayable();
	
	public boolean getPlayable(){
		return playable;
	}
	
	public abstract void setHealth();
	
	public abstract void reduceHealth(int DMG_TAKEN);
	
	public int getHealth(){
		return health;
	}
	
	public abstract void setDirection(int DIRECTION);
	
	public int getDirection(){
		return dir;
	}
	
	public abstract void setXPosition(int XPOS);
	
	public abstract void setYPosition(int YPOS);
	
	public int getXPosition(){
		return xPos;
	}
	
	public int getYPosition(){
		return yPos;
	}
	
	public abstract void setAttackSpeed();
	
	public int getAttackSpeed(){
		return attackSpeed;
	}
	
	public abstract void setMovementSpeed();
	
	public int getMovementSpeed(){
		return movementSpeed;
	}
	
	public abstract void setDamage();
	
	public int getDamage(){
		return dmg;
	}
	
	public abstract void setScore();
	
	public int getScore(){
		return score;
	}
	
	public abstract void setDealDamage(boolean DEAL_DAMAGE);
	
	public boolean getDealDamage(){
		return dealDamage;
	}
	
	public abstract void setTakeDamage(boolean TAKE_DAMAGE);
	
	public boolean getTakeDamage(){
		return takeDamage;
	}
	
	public abstract void setRanged();
	
	public boolean getRanged(){
		return ranged;
	}
	
	//Call this every time a class is initialized!
	public void setMonsterDamage(){
		if (playable == true){
			monsterDamage = true;
		}
		else {
			monsterDamage = false;
		}
	}
	
	public boolean getMonsterDamage(){
		return monsterDamage;
	}
	
	public abstract void move();
	
	public abstract void attack(int ATTACKTYPE);
	
	public abstract void paint(Graphics2D g);
	
}
