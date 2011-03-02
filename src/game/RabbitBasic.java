package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class RabbitBasic {
	static String fs = std.strings.fs;
	static String base_character_location = fs + "data" + fs + "defaultCharacter.png";
	
	Image basicCharacterImg = null;
	
	float x,y;
	float scale = 1.0f;
	public  float width = 0;
	public float bounce = 0;
	public double bounceHeight = 20;
	long jumpDelay = 50 ; //(default one second).
	long startTime;
	
	public float currentArc = 0;
	
	public RabbitBasic(){
		try {
			basicCharacterImg = new Image(base_character_location);
			x=0;
			y = boot.booter.app.getHeight() - basicCharacterImg.getHeight();
			width = x * scale;
			startTime = System.currentTimeMillis();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void RabbitDraw(){
		if (((System.currentTimeMillis() - startTime) > jumpDelay)){ 
			bounce = (float) (Math.sin(currentArc++) * bounceHeight);
			startTime  = System.currentTimeMillis();
		}
		basicCharacterImg.draw(x,y+bounce,scale);
		
		
	}
}
