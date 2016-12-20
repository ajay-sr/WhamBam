import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuState extends States{

	private int logoSize, waitTimer;
	private Color logoCol;
	private Font fnt0, fnt1, fnt2;

	public MenuState(Game game) {
		super(game);
		logoSize = 10;
		logoCol = Assets.goodPink;
        waitTimer = 0;
        fnt1 = Assets.getFont(65);
        fnt2 = Assets.getFont(25);
	}

	public void tick() {
		getInput();
		//System.out.println(game.getMouseManager().getX());
	}

	public void render(Graphics g) {

		g.setColor(logoCol);
		if(logoSize < 150){
			logoSize+=2;
			fnt0 = Assets.getFont(logoSize);
		}
		g.setFont(fnt0);
		g.drawString("Wham Bam", 337, 250);

		if(logoSize == 150 && waitTimer == 50){
			
			g.setFont(fnt1);
			g.drawString("Press Enter to Start", 329, 650);
			
			g.setFont(fnt2);
			g.drawString("Press H for Help", 1080, 50);
		}else if(logoSize == 150){
			waitTimer++;
		}
	}
	
	public void getInput(){
		if(game.getKeyManager().enter){
			States.setState(game.getGameState());
		}else if (game.getKeyManager().help){
			States.setState(game.getHelpState());
		}
	}

}
