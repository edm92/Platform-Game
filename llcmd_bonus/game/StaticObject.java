package game;

public class StaticObject extends GameObject{
	boolean outerBoundary;		// Is part of the gridbag or an outer Limit
	
	// If Outer is upper, lower, left, right
	outerEnum type;
	
	public StaticObject(){
		super();
		outerBoundary = false;
	}
	
	public StaticObject(String nref){
		super(nref);
		outerBoundary = false;
	}
	
	public void isOuterBounds(boolean yesno, outerEnum ntype){
		outerBoundary = yesno;
		type = ntype;
	}
	
	public void draw(){
		//if the outerBoundary has not been reached and
		//if imgRef is not pointing to null.
		drawLevel();
	}
	
	public void drawOuter(){
		int adder = 0;
		if(outerBoundary && imgRef != ""){
			if(type == outerEnum.UPPER){
				for(int i = 0; i <= (MainGameLoop.Width/ImageRef.get(imgRef).getWidth()); i++){
					ImageRef.get(imgRef).draw(adder, 0);
					adder += ImageRef.get(imgRef).getWidth();
				}
			}else
			if(type == outerEnum.LEFT){
				adder = 0;
				for(int i = 0; i < (MainGameLoop.Height/ImageRef.get(imgRef).getHeight()); i++){
					ImageRef.get(imgRef).draw(0, adder);
					adder += ImageRef.get(imgRef).getHeight();
				}
			}else
			if(type == outerEnum.RIGHT){
				adder = 0;
				for(int i = 0; i < (MainGameLoop.Height/ImageRef.get(imgRef).getHeight()); i++){
					ImageRef.get(imgRef).draw(GridBox.bound_right, adder);
					adder += ImageRef.get(imgRef).getHeight();
				}
			}else
			if(type == outerEnum.LOWER){
				int plus = ImageRef.get(imgRef).getWidth();
				for(int i = 0; i < MainGameLoop.Width;){
					ImageRef.get(imgRef).draw(i, GridBox.bound_downlow);
					i += plus;
				}
			}
		}
	}
	
	public void drawLevel(){
		if(!outerBoundary && imgRef!=""){
			
			
			int h_add = ImageRef.get("wall").getHeight() * 30,
				w_add;
		
			w_add = ImageRef.get("wall").getWidth();
			for(int j = 0; j < GridBox.box_w-30; j++){
				GridBox.gridMatrix[30][j] = 1;
				ImageRef.get(imgRef).draw(w_add, h_add);
				w_add +=ImageRef.get(imgRef).getWidth();
			}
			h_add = ImageRef.get("wall").getHeight() * 10;
			w_add = ImageRef.get(imgRef).getWidth() * 30;
			for(int j = 30; j < GridBox.box_w; j++){
				GridBox.gridMatrix[10][j] = 1;
				ImageRef.get(imgRef).draw(w_add, h_add);
				w_add +=ImageRef.get(imgRef).getWidth();
			}
			
			h_add = ImageRef.get("wall").getHeight() * 1;
			w_add = ImageRef.get(imgRef).getWidth() * 1;
			ImageRef.get("key_chain").draw(h_add, w_add);
			// Preopened
			
			h_add = ImageRef.get("wall").getHeight() * 46;
			w_add = ImageRef.get("wall").getWidth() * 10;
			//GridBox.gridMatrix[45][10] = 2;
			//GridBox.gridMatrix[44][10] = 2;
			//GridBox.gridMatrix[45][11] = 2;
			//GridBox.gridMatrix[44][11] = 2;
			h_add -= ImageRef.get("wall").getHeight();
			ImageRef.get("chest_open").draw(w_add, h_add);
			
			
			// Treasure
			// 1
			h_add = ImageRef.get("wall").getHeight() * 29;
			w_add = ImageRef.get("wall").getWidth() * 10;
			GridBox.gridMatrix[29][10] = 2;
			GridBox.gridMatrix[28][10] = 2;
			GridBox.gridMatrix[29][11] = 2;
			GridBox.gridMatrix[28][11] = 2;
			h_add -= ImageRef.get("wall").getHeight();
			if(MainGameLoop.current_chest_number > 1)
				ImageRef.get("chest_open").draw(w_add, h_add);
			else
				ImageRef.get("chest_2").draw(w_add, h_add);
			// 2
			h_add = ImageRef.get("wall").getHeight() * 29;
			w_add = ImageRef.get("wall").getWidth() * 15;
			GridBox.gridMatrix[29][15] = 3;
			GridBox.gridMatrix[28][15] = 3;
			GridBox.gridMatrix[29][16] = 3;
			GridBox.gridMatrix[28][16] = 3;
			h_add -= ImageRef.get("wall").getHeight();
			if(MainGameLoop.current_chest_number > 2)
				ImageRef.get("chest_open").draw(w_add, h_add);
			else
				ImageRef.get("chest_3").draw(w_add, h_add);
			
			// 3
			h_add = ImageRef.get("wall").getHeight() * 9;
			w_add = ImageRef.get("wall").getWidth() * 31;
			GridBox.gridMatrix[9][31] = 4;
			GridBox.gridMatrix[8][31] = 4;
			GridBox.gridMatrix[9][32] = 4;
			GridBox.gridMatrix[8][32] = 4;
			h_add -= ImageRef.get("wall").getHeight();
			if(MainGameLoop.current_chest_number > 3)
				ImageRef.get("chest_open").draw(w_add, h_add);
			else
				ImageRef.get("chest_4").draw(w_add, h_add);
			
			// 4
			h_add = ImageRef.get("wall").getHeight() * 46;
			w_add = ImageRef.get("wall").getWidth() * 45;
			GridBox.gridMatrix[45][45] = 5;
			GridBox.gridMatrix[44][45] = 5;
			GridBox.gridMatrix[45][46] = 5;
			GridBox.gridMatrix[44][46] = 5;
			h_add -= ImageRef.get("wall").getHeight();
			if(MainGameLoop.current_chest_number > 4)
				ImageRef.get("chest_open").draw(w_add, h_add);
			else
				ImageRef.get("chest_5").draw(w_add, h_add);
			
			// Keys
			h_add = (ImageRef.get("wall").getHeight()+5) * (1);
			w_add = ImageRef.get("wall").getWidth() + 8;
			ImageRef.get("key_" + (1)).draw(w_add, h_add);
			for(int i = 0; i < MainGameLoop.current_chest_number; i++){
				h_add = (ImageRef.get("wall").getHeight()+5) * (2 + i);
				w_add = ImageRef.get("wall").getWidth() + 8;
				if(i != MainGameLoop.total_chest_number-1)
					ImageRef.get("key_" + (i + 2)).draw(w_add, h_add);
			}
		}
	}

	
}
