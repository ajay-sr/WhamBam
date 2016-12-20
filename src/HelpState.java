import java.awt.Graphics;

public class HelpState extends States{

	private Player player;
	
	public HelpState(Game game) {
		super(game);
		player = new Player(600, 400);
	}

	@Override
	public void tick() {
		getInput();
		if(player.getY()<400){
			player.setFalling(true);
		}else if(player.getY()>=400){
			player.setFalling(false);
			player.setVelocity(0);
			player.setJumpAgain(true);
		}
		
		if(player.getX()>Game.WIDTH-player.getWidth()){
			player.setMaxReached(true);
		}else if(player.getX()<0){
			player.setMaxLeft(true);
		}
		
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.help, 0, 0, null);
		player.render(g);
	}
	
	public void getInput(){
		if(game.getKeyManager().enter){
			States.setState(game.getGameState());
		}
		if(game.getKeyManager().up && player.getJumpAgain()){
			player.setJumping(true);
			player.setJumpAgain(false);
		}
		if(game.getKeyManager().right){
			player.setRight(true);
		}
		if(!game.getKeyManager().right){
			player.setRight(false);
		}
		if(game.getKeyManager().left){
			player.setLeft(true);
		}
		if(!game.getKeyManager().left){
			player.setLeft(false);
		}
		if(!game.getKeyManager().left && !game.getKeyManager().right){
			player.setTempVel(0);
		}
	}

}
