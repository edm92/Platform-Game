package game;

//import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
//import java.awt.event.*;
public class MainGameLoop {
		
	public static int Width = 640;
	public static int Height = 480;
	public static String WindowTitle = "Linked List Commander";
	private static final int FRAMERATE = 60;
	private static boolean finished;
	private static long		timerTicksPerSecond		= Sys.getTimerResolution();
	public static TextureLoader textureLoader;
	public static SpriteObjects sprites;
	public static GridBox gridbox;
	public static StaticObject[] background;
	public static StaticObject blocks;	// todo remove
	public static moveableObject character;
	public static long msgTimer;
	public static long SHOW_TIME;
	public static boolean showMsg;
	public static String msgName;
	
	public static int current_chest_number;
	public static int total_chest_number;
	
	private static int splashShow;
	
	public static long scoreTimeStart;
	public static boolean uploaded;
	
	private static int codeShow;
	private static int codeTime;
	
	/**
	 * @param args
	 */
	public static void Init(boolean fullscreen) {
		DisplayMode chosenMode = null;
		finished = false;
	
		try {	// Loop through all possible display modes
		     DisplayMode[] modes = Display.getAvailableDisplayModes();
		 
		     for (int i=0;i<modes.length;i++) {
		          if ((modes[i].getWidth() == Width) && (modes[i].getHeight() == Height) && modes[i].getFrequency() == FRAMERATE) {
		               chosenMode = modes[i];
		               break;
		          }
		     }
		} catch (LWJGLException e) {     
		     Sys.alert("Error", "Unable to determine display modes. Exiting");
		     System.exit(0);
		}
		 
//		If no mode selected then exit.
		if (chosenMode == null) {
		     Sys.alert("Error", "Unable to find appropriate display mode.");
		     System.exit(0);
		}
	      
		try {
		    Display.setDisplayMode(chosenMode);
		    Display.setTitle(WindowTitle);
		    Display.setFullscreen(fullscreen);
		    Display.create();
		} catch (LWJGLException e) {
		    Sys.alert("Error", "Unable to create display.");
		    System.exit(0);
		}
		
		// Setup keyboard
		try {
			Keyboard.create();
			
			if(!Keyboard.isCreated()){
				Sys.alert("Error", "Unable to find keyboard");
				System.exit(0);
			}
		}catch (LWJGLException e){
		     Sys.alert("Error", "Unable to get the keyboard");
		     System.exit(0);						
		}
		
		
//		 go on to do any initialisation of OpenGL here, for example loading textures
//		 or setting up basic global settings 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Width, Height, 0, -1, 1);
//		GL11.glClearColor(0,0,0,0);		
		textureLoader = new TextureLoader();
		// Load Sprites
		sprites = new SpriteObjects();
		gridbox = new GridBox(
				ImageRef.get("wall").getWidth(),
				ImageRef.get("wall").getHeight(),
				ImageRef.get("ground").getWidth(),
				ImageRef.get("ground").getHeight(),
				ImageRef.get("block").getWidth(),
				ImageRef.get("block").getHeight()
					);
		
		// Setup the background for drawing
		background = new StaticObject[4];
		background[0] = new StaticObject("wall");
		background[1] = new StaticObject("wall");
		background[2] = new StaticObject("wall");
		background[3] = new StaticObject("ground");
		background[0].isOuterBounds(true, outerEnum.UPPER);
		background[1].isOuterBounds(true, outerEnum.LEFT);
		background[2].isOuterBounds(true, outerEnum.RIGHT);
		background[3].isOuterBounds(true, outerEnum.LOWER);
		
		// Blocks test
		blocks = new StaticObject("block");
		
		// Character
		character = new moveableObject();
		msgTimer = 0;
		showMsg = false;
		msgName = "correct";
		SHOW_TIME = 20;
		current_chest_number = 1;
		total_chest_number = 5;
		
		// Score 
		scoreTimeStart = getTime();
		uploaded =false;
		
		splashShow = -1;
		codeShow = 1;
		codeTime = 1;
	}
	
	public static void run(){
	    while (!finished) {
	        Display.update();
	        if (Display.isCloseRequested()) {
	        	finished = true;
	        } 
	   
	        // The window is in the foreground, so we should play the game
	        else if (Display.isActive()) {
	        	keyboard();
	        	logic();
	        	render();
	        	Display.sync(FRAMERATE);
	        }
	   
	        // The window is not in the foreground, so we can allow other stuff to run and
	        // infrequently update
	        else {
	          try {
	        	  Thread.sleep(100);
	          } catch (InterruptedException e) {
	          }
	          logic();
	   
	          // Only bother rendering if the window is visible or dirty
	          if (Display.isVisible() || Display.isDirty()) {
	        	  render();
	          }
	        }
	      }
	}
	
	public static void logic(){
		// Game updates happen here before game is drawn
		if(character.jumpActive) character.jump();
		/*
		 * Errors in Double Jump Inactive until improved
		if(character.jumpActive && character.doubleJumpActive) character.doubleJump();
		*/
		if(character.sprint)character.Sprint();
		
		if(total_chest_number == current_chest_number){
			//System.out.println("You Win");
			splashShow = 2;
			//
			long endTime = getTime();
			scoreTimeStart = endTime - scoreTimeStart;
			if(!uploaded){
				UploadScore.doScore(scoreTimeStart);
				uploaded=true;
			}
		}
	}
	
	public static void render(){
		// Draw the pretty graphics on the screen
		
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
	    
	    // Draw Background Image
	    ImageRef.get("background").setHeight(Height);
	    ImageRef.get("background").setWidth(Width);
	    ImageRef.get("background").draw(0, 0);
	    
	    // Draw OuterBoundary walls
	    for(int i = 0; i < 4; i++)
	    	background[i].drawOuter();
	    
	    // Draw blocks
	    blocks.draw();
	    
	    character.draw();
	    
	    //ImageRef.get("chest").draw(50,50);
	    //ImageRef.get("key_g").draw(50,50);
	    
	    
	    if(showMsg){
	    	if(msgTimer != 0 && msgTimer <= SHOW_TIME){
	    		ImageRef.get(msgName).draw((Width/2)-(ImageRef.get(msgName).getWidth()/2), 
	    								(Height/2)-(ImageRef.get(msgName).getHeight()/2));
	    		msgTimer++;
	    	}else{
	    		msgTimer = 0;
	    		showMsg = false;
	    	}	    	
	    }
	    if(splashShow == -1){
	    	if(codeTime != 0 && codeTime <= SHOW_TIME + 100){
	    		ImageRef.get("splash-" + codeShow).draw((Width/2)-(ImageRef.get("splash-" + codeShow).getWidth()/2), 
					(Height/2)-(ImageRef.get("splash-" + codeShow).getHeight()/2));
	    		codeTime++;	
	    	}else{	    	
	    		codeShow++;
	    		codeTime=1;
	    		System.out.println(codeShow);
	    		}
	    	
	    	if(codeShow >= 4) { msgTimer=0;splashShow = 0; }
	    	
	    	
	    }
	    else	    
	    if(splashShow == 0){
	    	ImageRef.get("splash").draw((Width/2)-(ImageRef.get("splash").getWidth()/2), 
					(Height/2)-(ImageRef.get("splash").getHeight()/2));
	    }
	    if(splashShow == 2){
	    	ImageRef.get("endgame").draw((Width/2)-(ImageRef.get("endgame").getWidth()/2), 
					(Height/2)-(ImageRef.get("endgame").getHeight()/2));
	    }
		
	}
	
	public static void keyboard(){
		// Handle keyboard events
		Keyboard.poll();
		//int count = Keyboard.getNumKeyboardEvents();
		Keyboard.next();
/*		while (Keyboard.next()) {
			int character_code = ((int)Keyboard.getEventCharacter()) & 0xffff;
//			System.out.println("Checking key:" + Keyboard.getKeyName(Keyboard.getEventKey()));
//			System.out.println("Pressed:" + Keyboard.getEventKeyState());
//			System.out.println("Key character code: " + character_code);
//			System.out.println("Key character: " + Keyboard.getEventCharacter());
		}
*/		
		
		if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
			finished = true;
			return;
			}
		if(splashShow == 1 ){
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			// Key Right
			character.move("right");
			}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			// Key left
			character.move("left");
			}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			// Keyup
				if (!character.jumpActive){
					character.jumpActive = true;
					character.accel = -2;
					character.velocity = -2;
					
				}
			}
			
 
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			//duck
			character.duck = 1;

			// Keydown
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if (!character.sprint && character.sprintcounter+10000 < Sys.getTime()){
				character.sprint = true;
				character.sprintcounter = getTime();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			int boxID;
			if(!showMsg)
			if((boxID = GridBox.checkBoxes(character)) != 0){
				if(current_chest_number + 1 == boxID){				
					showMsg = true;
					msgTimer = 1;
					msgName = "correct";
					current_chest_number++;
				}else{
					showMsg = true;
					msgTimer = 1;
					msgName = "wrong";		
				}
			}
		}
		}else{
			// Splash Screen Keys\
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && splashShow == -1)
				{
				splashShow = 0;
				msgTimer = 0;
				}
			else
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && splashShow == 0){
				splashShow = 1;
			}else
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
					//finish();
				}
			}
		}
		
		
	}
	
	public static void finish(){
		// Extra cleanup
		//Keyboard.destroy();
		finished = true;
		
	}
	
	public static void main(String[] args) {
		boolean fullscreen = (args.length == 1 && args[0].equals("-fullscreen"));
		Init(fullscreen);
		run();
		finish();
	}
	
	/**
	 * Get the high resolution time in milliseconds
	 * 
	 * @return The high resolution time in milliseconds
	 */
	public static long getTime() {
		// we get the "timer ticks" from the high resolution timer
		// multiply by 1000 so our end result is in milliseconds
		// then divide by the number of ticks in a second giving
		// us a nice clear time in milliseconds
		return (Sys.getTime() * 1000) / timerTicksPerSecond;
	}

}
