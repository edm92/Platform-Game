/*
 * Created on May 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;
import org.lwjgl.*;

/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class moveableObject extends GameObject{
	//public GameObject[] charBits;
	public int x, y;
	public int			centrex;
	public int			centrey;
	public int movespeed;
	public int dir;
	public int lastDirPressed;
	public String headDir;
	public boolean jumpActive;
	public int duck;	
	public int flip;
	public boolean sprint;
	public int energy;
	public long sprintcounter;
	
	public final double gravity;
	public double accel;
	public double velocity;
	
	public static CapeObject myCape;
	
	public moveableObject(){
		gravity = .2;
		accel = 0;
		velocity = 0;

		
		lastDirPressed = 0;
		dir = 1;
		//String headDir = null;
		duck = 0;
		flip = 0;
		sprint = false;
		energy =100;
		sprintcounter = Sys.getTime();
		movespeed = 4;
		width = ImageRef.get("head_l").getWidth();
		height = ImageRef.get("head_l").getHeight() + ImageRef.get("body").getHeight() +
				ImageRef.get("leg_l").getHeight() + ImageRef.get("shoe_l").getHeight();
		height -= 5;

		x = 50; 
		y = GridBox.bound_downlow - (height);
		
		UpdatePosition();
		
		myCape = new CapeObject();
		myCape.init(centrex, centrey);
	}
	
	public void UpdatePosition(){
		top=y;
		bottom=y+height;
		left=x;
		right=x+width;
		centrex = width/2 + left;
		centrey = top + ImageRef.get("head_l").getHeight() + (ImageRef.get("body").getHeight()/2);
	}
	
	public void move(String dir){
		if(!jumpActive)
		if(((GridBox.checkInner(this, 10, 0)) == 0)){
			// Check if up or down
				//going down
			//System.out.println("checking");
			if(!(bottom == GridBox.bound_downlow)){
				velocity = 2;
				accel = .1;
				jumpActive = true;
			}
		}
		

		
		int bCheck = GridBox.checkOuter(this);
		if((bCheck == 0 || bCheck == -2 || bCheck == 2)){	// Don't worry up updown checks
			if(dir.equals("left")){
				if(x >= GridBox.bound_left){
					if((x - movespeed <= GridBox.bound_left) && (GridBox.checkInner(this, 0, -movespeed) == 0))
						x = GridBox.bound_left;
					else if((GridBox.checkInner(this, 0, -movespeed) == 0))
						x -= movespeed;
				}else x = GridBox.bound_left;
				lastDirPressed = 0;
			}else
				if(dir.equals("right")){
					if(right <= GridBox.bound_right){
						if(right + movespeed >= GridBox.bound_right && (GridBox.checkInner(this, 0, movespeed) == 0))
							x = GridBox.bound_right - width;
						else if((GridBox.checkInner(this, 0, movespeed) == 0)){
							x += movespeed;
						}
					}else x = GridBox.bound_right - width;
					lastDirPressed = 1;
				}
		}
		if(bCheck == -2){
			velocity = 0;
			accel = 0;
			jumpActive = false;
			y = GridBox.bound_downlow - (height);
		}
		
		//if(jumpActive)jump();
		UpdatePosition();
		
		
	}
	public void jump(){
//		 Do bounds checking
		int ground = 0;
		if(!((ground = GridBox.checkInner(this, (int)velocity, 0)) == 0)){
			// Check if up or down
			if(velocity < 0){	// going up
				// let the user jump up through walls
			}else{
				//going down
				velocity = 0;
				accel = 0;
				jumpActive = false;
				y = ground - height;
				move("none");
			}
		}
		// jump
		if(jumpActive){
			y += velocity;
			velocity += accel;
			accel += gravity;
			move("none");
		}
	}

	public void Sprint(){
		if(sprintcounter+1000 > Sys.getTime())
			movespeed = 10;
		else{
			movespeed = 4;
			sprint = false;
		}
	}
	public void duck(){
		duck = 0;
	}

	
	public void draw(){
		myCape.moveCape();
		if(jumpActive){
			ImageRef.get("arm_l").angle = 180;
			ImageRef.get("arm_r").angle = 180;
			flip = ImageRef.get("body").getWidth()/2;
		}else{
			ImageRef.get("arm_l").angle = 0;
			ImageRef.get("arm_r").angle = 0;
			flip = 0;
		}
		int ducking = 0;
		if(duck == 1)
			ducking = 2*(int)ImageRef.get("leg_l").getHeight();
		
		//gives the head a direction depending on the last direction.
		if(lastDirPressed == 0)
			headDir = "head_l";
		else
			headDir = "head_r";
		
		
		//ImageRef.get(headDir).draw(x-(int)(ImageRef.get(headDir).getWidth()/2.75),y-(int)((ImageRef.get(headDir).getHeight()/2.75)+ImageRef.get("body").getHeight())+ducking);
		//ImageRef.get("arm_l").draw(x-(ImageRef.get("body").getWidth()/2)+flip,y+ducking);
		//ImageRef.get("arm_r").draw(x+(ImageRef.get("body").getWidth())-2+flip,y+ducking);
		double bodyL = centrex - (ImageRef.get("body").getWidth() / 2);
		double bodyT = top + ImageRef.get("head_l").getHeight()-4.5;
		
		myCape.drawCape();
		
		ImageRef.get("body").draw((int)bodyL,(int)bodyT+ducking);
				
		ImageRef.get(headDir).draw(left,top+ducking);
		ImageRef.get("arm_l").draw((int)(bodyL - ImageRef.get("arm_l").getWidth())+flip,(int)(bodyT+ducking));
		ImageRef.get("arm_r").draw((int)(bodyL+(ImageRef.get("body").getWidth())-2+flip),(int)(bodyT+ducking));
		bodyL += 1.5;
		bodyT += ImageRef.get("body").getHeight();
		//don't draw legs if ducking
		if(duck ==0){
			ImageRef.get("leg_l").draw((int)(bodyL),(int)bodyT);
			ImageRef.get("leg_r").draw((int)(bodyL)+(ImageRef.get("leg_l").getWidth()), (int)bodyT);
		}
		bodyT += ImageRef.get("leg_l").getHeight();
		ImageRef.get("shoe_l").draw((int)(bodyL)-(ImageRef.get("leg_l").getWidth()), (int)bodyT);
		ImageRef.get("shoe_r").draw((int)(bodyL)+(ImageRef.get("leg_l").getWidth()), (int)bodyT);
		if (duck == 1)duck = 0;
		
	}
	
	
}
