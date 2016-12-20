import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Assets {
	
	private static final int width = 60, height = 60, aikeWidth = 40, aikeHeight = 64;
	public static BufferedImage aike, aike2, floor, platform, background, help;
	public static Font ttfBase, ttfReal;
	public static Color goodPink;
	
	public static void init(){
		ttfBase = null;
        ttfReal = null;
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("SpriteSheet.png"));
		SpriteSheet aikesheet = new SpriteSheet(ImageLoader.loadImage("aikeSpriteSheetNew.png"));
		background = ImageLoader.loadImage("sky.png");
		help = ImageLoader.loadImage("help.png");
		floor = sheet.crop(0, 0, width, height);
		platform = sheet.crop(width, 0, width, height);
		aike = aikesheet.crop(0,0, aikeWidth, aikeHeight);
		aike2 = aikesheet.crop(aikeWidth,0, aikeWidth, aikeHeight);
		goodPink = Color.decode("#F2A0FF");
		try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("res/logo.ttf"));
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);
        } catch (Exception ex) {
        	ex.printStackTrace();
        	System.err.println("font not loaded.");
        }
	}
	
	public static Font getFont(int size){
		ttfReal = ttfBase.deriveFont(Font.PLAIN, size);
		return ttfReal;
	}
}
