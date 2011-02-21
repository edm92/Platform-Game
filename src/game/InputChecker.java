package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class InputChecker {
	SimpleGame SGparent;
	
	public int MoveSpeed = 1;
	public int FarOffCentre = 50;
	public int FarInCentre = -50;
	public int bounceHeight = 10;
	
	public int bounce = 0; 
	
	private boolean prevDirection = true; // true = forwar, false = backwards
	
	private boolean gotToMiddle = false;
	
	
	int mover = 5;
	int backgroundFix = -5;
	boolean motionswitch = false;
	boolean revmotionswitch = false;
	int distanceTraveled = 0;
	public InputChecker(SimpleGame myParent){
		SGparent = myParent;
	}

	public void CheckInput(GameContainer gc, int delta){
		Input input = gc.getInput();
		 
        
        if(input.isKeyDown(Input.KEY_D))
        {
        	if((distanceTraveled + SGparent.Alex.width) <= SGparent.myBackground.width + - SGparent.centreWidth){
        		SGparent.Alex.x += mover;
        		
    			SGparent.myBackground.x += backgroundFix;
    			distanceTraveled += MoveSpeed*2;
        	}
        	if(SGparent.Alex.x > SGparent.centreWidth) gotToMiddle = true;
        	
        	if(SGparent.Alex.x > SGparent.centreWidth + FarOffCentre ) { mover = -MoveSpeed; backgroundFix = -2*MoveSpeed; motionswitch = true;}
        	if(SGparent.Alex.x < SGparent.centreWidth + FarInCentre && motionswitch) { mover = MoveSpeed; backgroundFix =-MoveSpeed; motionswitch = false;}
        	prevDirection = true;
        }
        if(input.isKeyDown(Input.KEY_A) && gotToMiddle)
        {
        	// Flip character
        	
        	if(prevDirection) revmotionswitch = true;
        	if(distanceTraveled > 0 - SGparent.centreWidth ){
        	  	SGparent.Alex.x -= mover;
        	  	SGparent.myBackground.x -= backgroundFix;
        	  	distanceTraveled -= MoveSpeed*2;
        	}
        	
    		
        	if(SGparent.Alex.x > SGparent.centreWidth + FarOffCentre &&revmotionswitch) { mover = MoveSpeed; backgroundFix = -MoveSpeed; revmotionswitch = false;}        	
        	if(SGparent.Alex.x < SGparent.centreWidth + FarInCentre){mover = -MoveSpeed; backgroundFix =-2*MoveSpeed; revmotionswitch = true;}
        	prevDirection = false;
        	
        	
        }
        
	}
}
