import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	protected float x,y;
	protected int width, height;
	protected Rectangle bounds;
	protected BufferedImage sprite;
	
	public Entity(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle ((int)x,(int)y,width, height);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public float getX() {
		return this.bounds.x;
	}

	public void setX(int x) {
		this.bounds.x = x;
	}

	public float getY() {
		return this.bounds.y;
	}

	public void setY(int y) {
		this.bounds.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
}
