package game;

public class GridBox {
	// Outer Bounds checks
	public static int block_w, 
				block_h, 
				bound_uphigh,
				bound_downlow,
				bound_left,
				bound_right,
				box_w,
				box_h;
	public static int[][] gridMatrix;
	
	public GridBox(int wall_width,
					int wall_height,
					int ground_width,
					int ground_height,
					int block_width,
					int block_height){
		int window_width = MainGameLoop.Width;
		int window_height = MainGameLoop.Height;
		
		block_w = block_width;
		block_h = block_height;
		bound_uphigh = wall_height;
		bound_downlow = window_height - ground_height;
		bound_left = wall_width;
		bound_right = window_width - wall_width;
		
		box_h = ((window_height - ground_height - wall_height) / block_height) ;
		box_w = ((window_width - wall_width - wall_width) / block_width) ;
		
		gridMatrix = new int[box_h][box_w];
		for(int i = 0; i < box_h; i++){
			for(int j = 0; j < box_w; j++){
				gridMatrix[i][j] = 0;	// Set Grid Matrix to all empty
			}
		}
		
		//System.out.println("Gridbox dim = " + box_w + ", " + box_h);
	}
	
	public static int checkOuter(moveableObject checkit){
		if(checkit.left < bound_left)
			return -1;
		if(checkit.right > bound_right){
			return 1;}
		if(checkit.top < bound_uphigh)
			return 2;
		if(checkit.bottom > bound_downlow)
			return -2;
		return 0;
	}
	
	public static int checkInner(moveableObject checkit, int upmove, int sidemove){
		if(checkit.bottom > bound_downlow) return 0;
		
		int startx = (int)((checkit.left + sidemove) / block_w); 
		int starty = (int)((checkit.top  + upmove)  / block_h);
		int charx  = (int)(checkit.width  / block_w);
		int chary  = (int)(checkit.height / block_h);
		
		if(startx < 0) startx = 0;
		if(startx > box_w) startx = box_w - 1;
		if(startx+charx < 0) charx = 0;
		if(startx+charx > box_w) charx = box_w - 1;
		
		if(starty < 0) starty = 0;
		if(starty > box_h) starty = box_h - 1;
		if(starty+chary < 0) chary = 0;
		if(starty+chary > box_w) chary = (box_h-1) - starty;
		
		for(int i = 0; i < charx; i++){
			for(int j = 0; j < chary; j++){
				//System.out.println(gridMatrix[starty+j][startx+i]);
				if(startx+i >= box_w) break;
				if(starty+j >= box_h) break;
				if(gridMatrix[starty+j][startx+i] == 1) return findNextY(checkit, upmove, sidemove, startx+i, starty+j);
			}
		}
		
		return 0;
	}
	
	public static int findNextY(moveableObject checkit, int updown, int side, int gridX, int gridY){
		if(updown > 0){ // catch a falling player
			for(int i = gridY; i >= 0; i++){			
				if(gridMatrix[i][gridX] != 1)
					return i*ImageRef.get("wall").getHeight() ;
				
			}
		}		
		return 0;
	}
	
	public static int checkBoxes(moveableObject checkit){
		int startx = (int)((checkit.left) / block_w); 
		int starty = (int)((checkit.top)  / block_h);
		int charx  = (int)(checkit.width  / block_w);
		int chary  = (int)(checkit.height / block_h);
		if(checkOuter(checkit) != 0) return 0;
		for(int i = 0; i < charx; i++){
			for(int j = 0; j < chary; j++){
				if(gridMatrix[starty+j][startx+i] != 1 && gridMatrix[starty+j][startx+i] != 0){
					//System.out.println("Found a box");
					switch(gridMatrix[starty+j][startx+i]){
						case 2: // Chest 1
							break;
					}
					return gridMatrix[starty+j][startx+i];
					
				}
			}
		}
		
		return 0;
	}

}
