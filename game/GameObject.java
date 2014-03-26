package game;

public class GameObject {
	public int width;
	public int height;
	public int x;
	public int y;
	
	public int			top;
	public int			bottom;
	public int			left;
	public int			right;
	public int			centrex;
	public int			centrey;
	
	public String imgRef;
	
	public GameObject(){
		imgRef="";
		width=0;
		height=0;
		x=0;
		y=0;
		top=0;
		bottom=0;
		left=0;
		right=0;
		centrex=0;
		centrey=0;
	}
	
	public GameObject(String nref){
		imgRef = nref;
		width =ImageRef.get(imgRef).getWidth();
		height=ImageRef.get(imgRef).getHeight();
		x=0;
		y=0;
		top=y;
		bottom=y+height;
		left=x;
		right=x+width;
		centrex = width/2 + left;
		centrey = height/2 + top;
	}
	
}
