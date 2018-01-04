import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Entity{

	private int bx;
	
	public Block(float x, float y, int width, int height) {
		super(x, y, width, height);
		sprite = Assets.platform;
		bx = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {

		for (int j = 0; j<120; j+=60){
			if((int)x+bx>0 && (int)x+bx<Game.WIDTH)
				g.drawImage(sprite, (int)x + bx, (int)y+j, 60, 60, null);
			bounds.height+=j;
			bounds.x = (int)x+bx;
		}
	}

	public void setBX(int i){
		this.bx += i;
	}
	
	public boolean checkIntersect(Rectangle player){
		return this.bounds.intersects(player);
	}

	public int getMiddle(){
		return (bounds.x+(bounds.x+bounds.width))/2;
	}
}
