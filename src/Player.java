import java.awt.Graphics;

public class Player extends Creature{

	//private Game game;
	private boolean falling, jump, left, right, maxRight, backStep, 
					shadowOn, jumpAgain, backAgain, maxLeft, colliding;
	private int animationCounter, afterShadow, frame, x1, y1, x2,y2, backStepCount, backStepVel;

	public Player(float x, float y) {
		super(x, y, 60, 96);
		//this.game = game;
		sprite = Assets.aike;
		animationCounter = 0;
		frame = 1;
		backStepCount = 0;
		maxLeft = false;
		maxRight = false;
		backStep = false;
		shadowOn = false;
		jumpAgain = true;
		backAgain = true;
		colliding = false;
		backStepVel = -17;
		x1 = -100;
		y1 = -100;
		x2 = -100;
		y2 = -100;
	}

	public void tick() {
		animationCounter++;
		
		if(colliding && !onTop){
			backStepVel = 17;
		}
		
		if(backStep){

			if(maxRight)
				maxRight = false;
			shadowOn = true;
			afterShadow++;
			backStepCount++;
			tempVel = backStepVel;

			
			if(backStepCount == 10){
				backStep = false;
				shadowOn = false;
				backStepCount = 0;
				afterShadow = 0;
				x1 = -100;
				y1 = -100;
				x2 = -100;
				y2 = -100;
				colliding = false;
				backStepVel = -17;
			}
		}else if(this.right){
			right();
			maxLeft = false;
		}else if (this.left){
			if(!maxLeft)
				left();
			else{
				tempVel = 0;
			}
			maxRight = false;
		}
		
		if(this.falling){
			velocity+=gravity;
			if(velocity>17){
				velocity = 17;
			}
		}

		if(this.jump){
			setForce(17);
			if(force-velocity<=0){
				maxReached = true;
				velocity = 0;
			}
			if(maxReached){
				this.jump = false;
				maxReached = false;
				setForce(0);
			}
		}
		yMove = -force + velocity;
		xMove = tempVel;
		
		//Add animation to the creature class as a method
		if(shadowOn){
			if(afterShadow == 2){
				x1 = bounds.x;
				y1 = bounds.y;
			}else if (afterShadow == 5){
				x2 = bounds.x;
				y2 = bounds.y;
				afterShadow = 0;
			}
		}
		
		if(animationCounter == 15){
			if(frame == 1){
				frame = 2;
				sprite = Assets.aike2;
			}else if (frame == 2){
				frame = 1;
				sprite = Assets.aike;
			}
			animationCounter =0;
		}
		
		if(maxRight || maxLeft){
			bounds.y+=yMove;
		}else
			move();
	}

	public void render(Graphics g) {
		if(backStep){
			g.drawImage(sprite, x2, y2, width, height, null);
			g.drawImage(sprite,x1, y1, width, height, null);
		}
		g.drawImage(sprite, bounds.x, bounds.y, width, height, null);
	}

	public void setFalling(boolean falling){
		this.falling = falling;
	}

	public boolean getFalling(){
		return this.falling;
	}

	public boolean getJumping(){
		return jump;
	}

	public void setJumping(boolean on){
		this.jump = on;
	}
	
	public void setRight(boolean on){
		right = on;
	}
	public void setLeft(boolean on){
		left = on;
	}
	public void setTempVel(float i){
		tempVel = i;
	}
	public float getTempVel(){
		return tempVel;
	}
	public void setMaxReached(boolean on){
		this.maxRight = on;
	}
	public void setBackStep(boolean on){
		this.backStep = on;
	}
	public boolean getBackStep(){
		return backStep;
	}
	public boolean getJumpAgain(){
		return jumpAgain;
	}
	public void setJumpAgain(boolean on){
		this.jumpAgain = on;
	}
	public boolean getBackAgain(){
		return backAgain;
	}
	public void setBackAgain(boolean on){
		this.backAgain = on;
	}
	public void setMaxLeft(boolean on){
		this.maxLeft = on;
	}
	public boolean getMaxLeft(){
		return maxLeft;
	}
	public boolean getMaxRight(){
		return maxRight;
	}
	public void setColliding(boolean on){
		this.colliding = on;
	}
}
