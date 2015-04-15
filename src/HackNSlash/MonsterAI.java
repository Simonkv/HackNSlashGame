package HackNSlash;

public interface MonsterAI {

	final static int SLIME_IDLE_SPEED = 3;
	final static int SLIME_AGGRO_SPEED = 5;
	final static int GOBLIN_IDLE_SPEED = 2;
	final static int GOBLIN_AGGRO_SPEED = 4;
	final static int TROLL_IDLE_SPEED = 1;
	final static int TROLL_AGGRO_SPEED = 3;
	final static int AGGRO_RANGE = 400;
	final static int SLIME_SIZE = 25;
	final static int GOBLIN_SIZE = 60;
	final static int TROLL_SIZE = 90;
	final static int START_ONEX = 600;
	final static int START_ONEY = 100;
	final static int START_TWOX = 900;
	final static int START_TWOY = 100;
	final static int START_THREEX = 300;
	final static int START_THREEY = 100;
	
	
	public void idle();
	
	public void aggroed();
	
	public void setAggro();
	
	public void knockBack();
	
	public void addScore();
	
	public boolean isAlive();
	
	public boolean getAggro();
	
	public boolean checkIntersecting();
	public boolean checkTopWall();
	public boolean checkBotWall();
	public boolean checkLeftWall();
	public boolean checkRightWall();
	
	public void update();
	
	public void setYPriority();
	public void setXPriority();
	
	public boolean getYPriority();
	public boolean getXPriority();
	
	public long getIdleTimer();
	public void idleTimer();
	
	public boolean isAlive();
}
