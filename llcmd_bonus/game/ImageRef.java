/*
 * Created on Nov 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * @author admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImageRef {
	 public static Map<String, Sprite> imgs = Collections.synchronizedMap(new HashMap<String, Sprite>());
	 
	 public static void add(String name, Sprite value){
	 	imgs.put(name, value);
	 }
	 
	 public static Sprite get(String name){
	 	return imgs.get(name);
	 }
	 
}
