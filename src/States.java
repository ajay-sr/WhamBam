import java.awt.Graphics;

public abstract class States {

	private static States currentState = null;
	
	public static void setState(States state){
		currentState = state;
	}
	
	public static States getState(){
		return currentState;
	}
	
	protected Game game;
	
	public States(Game game){
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
