package game;

public class SpriteObjects {
	// Background Images
	
	public SpriteObjects(){
		// Load all sprites here with their appropriate Sprite Ref

		// Static objects  
		ImageRef.add("background", getSprite("background/background.gif"));
		ImageRef.add("wall", getSprite("background/wall.gif"));
		ImageRef.add("roof", getSprite("background/roof.gif"));
		ImageRef.add("ground", getSprite("background/ground.gif"));
		ImageRef.add("block", getSprite("background/block.gif"));
		
		ImageRef.add("chest_1", getSprite("chest_1.gif"));
		ImageRef.add("chest_2", getSprite("chest_2.gif"));
		ImageRef.add("chest_3", getSprite("chest_3.gif"));
		ImageRef.add("chest_4", getSprite("chest_4.gif"));
		ImageRef.add("chest_5", getSprite("chest_5.gif"));
		
		ImageRef.add("chest_open", getSprite("chest_open.gif"));
		
		ImageRef.add("key_1", getSprite("key_1.gif"));
		ImageRef.add("key_2", getSprite("key_2.gif"));
		ImageRef.add("key_3", getSprite("key_3.gif"));
		ImageRef.add("key_4", getSprite("key_4.gif"));
		ImageRef.add("key_5", getSprite("key_5.gif"));
		
		ImageRef.add("correct", getSprite("correct.gif"));
		ImageRef.add("wrong", getSprite("wrong.gif"));
		
		ImageRef.get("chest_1").setWidth(20);
		ImageRef.get("chest_1").setHeight(20);
		ImageRef.get("chest_2").setWidth(20);
		ImageRef.get("chest_2").setHeight(20);
		ImageRef.get("chest_3").setWidth(20);
		ImageRef.get("chest_3").setHeight(20);
		ImageRef.get("chest_4").setWidth(20);
		ImageRef.get("chest_4").setHeight(20);
		ImageRef.get("chest_5").setWidth(20);
		ImageRef.get("chest_5").setHeight(20);
		ImageRef.get("chest_open").setWidth(20);
		ImageRef.get("chest_open").setHeight(20);
		
		ImageRef.get("key_1").setWidth(15);
		ImageRef.get("key_1").setHeight(15);
		ImageRef.get("key_2").setWidth(15);
		ImageRef.get("key_2").setHeight(15);
		ImageRef.get("key_3").setWidth(15);
		ImageRef.get("key_3").setHeight(15);
		ImageRef.get("key_4").setWidth(15);
		ImageRef.get("key_4").setHeight(15);
		ImageRef.get("key_5").setWidth(15);
		ImageRef.get("key_5").setHeight(15);
		
		ImageRef.add("splash", getSprite("splash.jpg"));
		ImageRef.add("endgame", getSprite("end.jpg"));
		ImageRef.add("key_chain", getSprite("key_chain.gif"));
		ImageRef.add("cape", getSprite("cape.gif"));
		
		ImageRef.add("splash-1", getSprite("c.JPG"));
		ImageRef.add("splash-2", getSprite("cpp.JPG"));
		ImageRef.add("splash-3", getSprite("java.JPG"));		
		//Set the Height and Width of the required objects to 10 for a better grid
		
		ImageRef.get("wall").setWidth(10);
		ImageRef.get("wall").setHeight(10);
		ImageRef.get("roof").setWidth(10);
		ImageRef.get("roof").setHeight(10);
		ImageRef.get("ground").setHeight(10);
		ImageRef.get("block").setWidth(10);
		ImageRef.get("block").setHeight(10);
		
		
		
		// Moving Objects
		ImageRef.add("body", getSprite("hero/body.gif"));
		ImageRef.add("head_l", getSprite("hero/head_left.gif"));
		ImageRef.add("head_r", getSprite("hero/head_right.gif"));
		ImageRef.add("arm_l", getSprite("hero/left_arm.gif"));
		ImageRef.add("arm_r", getSprite("hero/right_arm.gif"));
		ImageRef.add("leg_l", getSprite("hero/left_leg.gif"));
		ImageRef.add("leg_r", getSprite("hero/right_leg.gif"));
		ImageRef.add("shoe_l", getSprite("hero/shoe_left.gif"));
		ImageRef.add("shoe_r", getSprite("hero/shoe_right.gif"));

		
	
	}
	
	public Sprite getSprite(String ref) {
		return new Sprite(MainGameLoop.textureLoader, ref);
	}
}
