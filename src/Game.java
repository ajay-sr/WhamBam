
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game implements Runnable{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH*9/16;
	
	private JFrame frame;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private States gameState, menuState, helpState;
	
	private int bx;
	
	public Game(){
		createDisplay();
	}
	
	public void createDisplay(){
		frame = new JFrame("Wham Bam");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setFocusable(false);
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.add(canvas);
		frame.pack();
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init(){
		Assets.init();
		gameState = new GameState(this);
		menuState = new MenuState(this);
		helpState = new HelpState(this);
		frame.addKeyListener(keyManager);
		frame.addMouseListener(mouseManager);
		frame.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		States.setState(menuState);
	}
	
	private void tick(){
		keyManager.tick();
		
		if(States.getState() != null){
			States.getState().tick();
		}
	}
	
	private void render(){
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		///////Draw here////////////
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(Assets.background, bx, 0, WIDTH*2, HEIGHT, null);
		if(States.getState() != null){
			States.getState().render(g);
		}
		//////Draw ends here//////////
		
		g.dispose();
		bs.show();
	}
	
	public void run() {
		
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				delta--;
			}
			render();
		}
		stop();
		
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public States getGameState(){
		return gameState;
	}
	
	public States getMenuState(){
		return menuState;
	}
	
	public States getHelpState(){
		return helpState;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setBX(int i){
		this.bx+= i;
	}
}
