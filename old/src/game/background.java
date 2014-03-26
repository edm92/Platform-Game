package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class background {
	static String fs = std.strings.fs;
	
	static String base_background_location = std.strings.home + fs +"data" +fs +"backdrop.jpg";
	
	Image basicBackgroundImg = null;
	
	SimpleGame SGparent;
	
	float x,y;
	double scale = 1.0f;
	float width ;
	
	
	public background(SimpleGame myParent){
		try {
			basicBackgroundImg = new Image(base_background_location);
			basicBackgroundImg.draw();
			SGparent = myParent;
			x = 0;
			y = 0;
			scale = (double)boot.booter.app.getHeight()/  (double)basicBackgroundImg.getHeight();
			width = basicBackgroundImg.getWidth() * (float)scale;
			std.debug.print("Loaded " + std.strings.home + " scale = " + scale + " height = " + basicBackgroundImg.getHeight() + "," + boot.booter.app.getHeight() + " alt = " + basicBackgroundImg.getHeight() / boot.booter.app.getHeight());
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void RabbitDraw(){
		basicBackgroundImg.draw(x,y,(float)scale);
	}
}
