package game;



import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
 
public class SimpleGame extends BasicGame{
 
	// Need to seperate this
	public RabbitBasic Alex;
	public InputChecker myUpdate;
	public background myBackground;
	
	public float centreWidth = 400;
	public float centreHeight = 300;
	
    public SimpleGame()
    {
        super(std.strings.title);
        
        // Load function needs to go here
        // Lets Rocket!
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	Alex = new RabbitBasic();
        myUpdate = new InputChecker(this);
        myBackground = new background(this);
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	// Need global update function
    	myUpdate.CheckInput(gc, delta);
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	myBackground.RabbitDraw();
    	Alex.RabbitDraw();
    	
    }
 

}