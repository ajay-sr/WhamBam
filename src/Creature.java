
public abstract class Creature extends Entity{

	public static final float DEFAULT_SPEED = 2.0f, MAX_SPEED = 6;
	
	protected float xMove, yMove, tempVel;
	protected float speed, velocity, gravity, force;
	protected boolean maxReached, onTop;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		velocity = 0;
		force = 0;
		gravity = 1;
		tempVel = 0;
		onTop = false;
	}
	
	public void right(){
		if(tempVel < MAX_SPEED){
			tempVel++;
		}else if(tempVel >= MAX_SPEED){
			tempVel = MAX_SPEED;
		}
	}
	
	public void left(){
		if(tempVel > -MAX_SPEED){
			tempVel--;
		}else if(tempVel <= -MAX_SPEED){
			tempVel = -MAX_SPEED;
		}
	}
	
	public void move(){
		bounds.x+=xMove;
		bounds.y+=yMove;
	}
	
	public void fall(){
		velocity+=gravity;
		if(velocity>10){
			velocity = 10;
		}
	}
	
	public void jump(){
		bounds.y-=force;
		if(force-velocity>=0){
			maxReached = true;
			velocity = 0;
		}
	}
	
	//Getters and Setters
	public boolean getMaxReached(){
		return maxReached;
	}
	public void setVelocity(float velocity){
		this.velocity = velocity;
	}
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public void setForce(float i){
		this.force = i;
	}
	public void setOnTop(boolean on){
		this.onTop = on;
	}
	public boolean getOnTop(){
		return onTop;
	}
	

}
