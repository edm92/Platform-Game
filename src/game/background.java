package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class background {
	static String base_background_location = "\\data\\backdrop.jpg";
	
	Image basicBackgroundImg = null;
	
	SimpleGame SGparent;
	
	float x,y;
	float scale = 1.0f;
	float width ;
	
	public background(SimpleGame myParent){
		try {
			basicBackgroundImg = new Image(base_background_location);
			
			SGparent = myParent;
			x = 0;
			y = 0;
			this.scale = (basicBackgroundImg.getHeight()/boot.booter.app.getHeight() );
			width = basicBackgroundImg.getWidth() * scale;
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void RabbitDraw(){
		basicBackgroundImg.draw(x,y,scale);
	}
}
