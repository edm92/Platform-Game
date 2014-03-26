/*
 * Created on May 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;
import javax.print.attribute.EnumSyntax;
/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class outerEnum extends EnumSyntax{
	private static final long serialVersionUID = -2739521845085831642L;

	public static final outerEnum UPPER		= new outerEnum(0);
	public static final outerEnum LOWER		= new outerEnum(1);
    public static final outerEnum LEFT 		= new outerEnum(2);
    public static final outerEnum RIGHT		= new outerEnum(3);

    public outerEnum(int objType)
    {
    	super(objType);
    }
	    
	    private static final String[] stringTable = {
	         "UPPER",
	         "LOWER",
			 "LEFT",
			 "RIGHT"
	    };

	    protected String[] getStringTable() {
	        return stringTable;
	    }

	    private static final outerEnum[] enumValueTable = {
	    	UPPER, 
	    	LOWER,
	    	LEFT,
	    	RIGHT
	    };    
	    
	    protected EnumSyntax[] getEnumValueTable() {
	        return enumValueTable;
	    }	
		
	    String eval(){
	    	return stringTable[super.getValue()];
	    }

}
