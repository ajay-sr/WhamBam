import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Floor extends Entity{
	
	private int fx;
	private Rectangle plat[];
	private BufferedReader reader;
	private int numFloor;
	private int initialLoc[];
	
	public Floor(float x, float y, int width, int height) {
		super(x, y, width, height);
		sprite = Assets.floor;
		fx = 0;
		try {
			reader = new BufferedReader(new FileReader("res/floor.txt"));
			numFloor = Integer.parseInt(reader.readLine());
			initialLoc = new int[numFloor];
			plat = new Rectangle[numFloor];
			for(int i = 0; i< numFloor; i++){
				initialLoc[i] = Integer.parseInt(reader.readLine());
				plat[i] = new Rectangle(initialLoc[i], (int)y, 1500, 60);
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public void tick() {
		for(int k = 0; k<numFloor; k++){
			plat[k].x = initialLoc[k] + fx;
		}
	}

	public void render(Graphics g) {
		for(int k = 0; k<numFloor; k++){
			
			for(int i = plat[k].x; i < plat[k].x + plat[k].width; i+=60){
				for (int j = 0; j<240; j+=60){
					if(i>-60 && i<Game.WIDTH)
						g.drawImage(sprite, i, (int)y+j, 60, 60, null);
				}

			}
			
		}
	}
	
	public boolean checkIntersect(Rectangle object){
		
		for(int i = 0; i< numFloor; i++){
			if(plat[i].intersects(object)){
				return true;
			}
		}
		return false;
		
	}
	
	public void setFX(int i){
		fx+= i;
	}

}
