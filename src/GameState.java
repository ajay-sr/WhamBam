import java.awt.Font;
import java.awt.Graphics;

public class GameState extends States{

	private Player player;
	private Floor floor;
	private Block block;
	private boolean gameOver;

	public GameState(Game game){
		super(game);
		player = new Player(400, 400);
		floor = new Floor(0, 500, 60, 60);
		block = new Block(600, 380, 60, 60);
		gameOver = false;
	}

	public void tick() {
		getInput();
		floor.tick();

		if(player.getY()>Game.HEIGHT + 10){
			gameOver = true;
		}

		if(!gameOver){
			if(player.getBounds().x<=0){
				player.setMaxLeft(true);
			}

			if(!floor.checkIntersect(player.getBounds()) && !(block.checkIntersect(player.getBounds()) && block.getBounds().y>player.getBounds().y+30)){
				player.setFalling(true);
				player.setColliding(false);
				player.setOnTop(false);
			}else if(floor.checkIntersect(player.getBounds()) || (block.checkIntersect(player.getBounds()) && block.getBounds().y>player.getBounds().y+30 && player.getX()+player.getWidth() > block.getX()+10 && player.getX()<block.getX()+block.getWidth()-10)){

				if(player.getY()+player.getHeight() > floor.getY()+10){
					player.setY((int)floor.getY() - player.getHeight());
				}

				player.setFalling(false);
				player.setVelocity(0);
				if(block.checkIntersect(player.getBounds()) && block.getBounds().y>player.getBounds().y+30){
					player.setColliding(false);
					player.setOnTop(true);
				}
			}


			if(player.getBounds().x >= Game.WIDTH/2){
				player.setMaxReached(true);
			}else if(player.getBounds().x<Game.WIDTH/2){
				player.setMaxReached(false);;
			}

			if(block.checkIntersect(player.getBounds()) && block.getMiddle()<player.getBounds().x)
				player.setColliding(true);
			player.tick();
		}
	}

	public void render(Graphics g) {
		floor.render(g);
		player.render(g);
		block.render(g);
		if(gameOver){
			g.setColor(Assets.goodPink);
			Font fnt0 = Assets.getFont(100);
			g.setFont(fnt0);
			g.drawString("Game Over", Game.WIDTH/2-473/2, 150);
			g.drawString("ESC to Exit", Game.WIDTH/2-473/2, 250);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public Floor getFloor() {
		return floor;
	}

	public void getInput(){

		if(!gameOver){
			if(game.getKeyManager().up){
				if(!player.getJumping() && !player.getFalling() && player.getJumpAgain())
					player.setJumping(true);
				player.setJumpAgain(false);
			}
			if(game.getKeyManager().left){
				if(block.checkIntersect(player.getBounds()) && block.getMiddle()<player.getBounds().x && !player.getOnTop()){
					player.setLeft(false);
					player.setTempVel(0);
				}else
					player.setLeft(true);
			}
			if(game.getKeyManager().right){
				if(block.checkIntersect(player.getBounds()) && block.getMiddle()>player.getBounds().x && !player.getOnTop()){
					player.setRight(false);
					player.setTempVel(0);
				}else
					player.setRight(true);

				if(player.getMaxRight()){
					floor.setFX((int)-player.getTempVel());
					block.setBX((int)-player.getTempVel());
					game.setBX((int)-player.getTempVel()/2);
				}
			}
			if(game.getKeyManager().down && !player.getJumping() && !player.getFalling() && player.getBackAgain() && !player.getBackStep()){

				player.setBackStep(true);
				player.setBackAgain(false);

			}
			if(!game.getKeyManager().up){
				player.setJumpAgain(true);
			}
			if(!game.getKeyManager().down){
				player.setBackAgain(true);
			}
			if(!game.getKeyManager().left){
				player.setLeft(false);
			}
			if(!game.getKeyManager().right){
				player.setRight(false);
			}
			if(!game.getKeyManager().left && !game.getKeyManager().right && !player.getBackStep()){
				player.setTempVel(0);
			}
		}else{
			if(game.getKeyManager().escape){
				System.exit(1);
			}
		}
	}
	
}
