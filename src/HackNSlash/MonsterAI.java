package HackNSlash;

public interface MonsterAI {

	final static int IDLE_SPEED = 3;
	final static int AGGRO_SPEED = 5;
	final static int AGGRO_RANGE = 400;
	final static int SLIME_SIZE = 25;
	final static int GOBLIN_SIZE = 60;
	final static int TROLL_SIZE = 90;
	final static int START_ONEX = 600;
	final static int START_ONEY = 100;
	
	
	public void idle();
	
	public void aggroed();
	
	public void setAggro();
	
	public void knockBack();
	
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
}
