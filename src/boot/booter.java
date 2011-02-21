package boot;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import game.SimpleGame;

public class booter {
	public static AppGameContainer app;
	public static void startup(){
		std.debug.popup("ImWithStupid Demo...\n\n Use 'a' and 'd' to move");
		SimpleGame myGame = new SimpleGame();
		 
		try {
			app = new AppGameContainer(myGame);
			app.setDisplayMode(800, 600, false);
		     app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
}
