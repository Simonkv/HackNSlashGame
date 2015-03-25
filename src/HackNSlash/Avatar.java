package HackNSlash;

public abstract class Avatar {

	protected String name;
	protected int health;
	protected int score;
	protected int attackSpeed;
	protected int dmg;
	protected int xPos;
	protected int yPos;
	protected int dir;
	protected boolean dealDamage;
	protected boolean ranged;
	
	
	public abstract void setName();
	
	public String getName(){
		return name;
	}
	
	public abstract void setHealth();
	
	public abstract void reduceHealth();
	
	public int getHealth(){
		return health;
	}
	
	public abstract void setDirection();
	
	public int getDirection(){
		return dir;
	}
	
	public abstract void setXPosition();
	
	public abstract void setYPosition();
	
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
	
	public abstract void setDamage();
	
	public int getDamage(){
		return dmg;
	}
	
	public abstract void setScore();
	
	public int getScore(){
		return score;
	}
	
	public abstract void setDealDamage();
	
	public boolean getDealDamage(){
		return dealDamage;
	}
	
	public abstract void setRanged();
	
	public boolean getRanged(){
		return ranged;
	}
	
	public abstract void move();
	
	public abstract void attack();
	
	public abstract void draw();
	
}
