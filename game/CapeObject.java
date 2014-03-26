package game;

public class CapeObject {
	public int numCapes;
	public int capeX[] = new int[5];
	public int capeY[] = new int[5];
	public double gravityX;
	public double gravityY;
	
	public void init(int centrex, int centrey){
		numCapes = 5;
		gravityX = 2;
		gravityY = 2;

		for(int i = 0; i < numCapes; i++){
			capeX[i] = centrex - 10;
			capeY[i] = centrey - ImageRef.get("cape").getHeight();
		}
	}
	
	public void moveCape(){
		capeX[0] = MainGameLoop.character.centrex - 10;
		capeY[0] = MainGameLoop.character.centrey - ImageRef.get("cape").getHeight();

		for(int i = 1; i < numCapes; i++){
			if(capeY[i] > capeY[0] + (i * (ImageRef.get("cape").getHeight()-2))){
				capeY[i] = capeY[0] + (i * (ImageRef.get("cape").getHeight() -2 ));
			}else
			if(capeY[i] < capeY[0] - (i * (ImageRef.get("cape").getHeight() - 2))){
				capeY[i] = capeY[0] - (i * (ImageRef.get("cape").getHeight() - 2));
			}
			
			capeY[i] += gravityY;
				
			if(capeX[i] > capeX[0] + (i * (ImageRef.get("cape").getHeight() - 2))){
				capeX[i] = capeX[0] + (i * (ImageRef.get("cape").getHeight() - 2));
			}else
			if(capeX[i] < capeX[0] - (i* (ImageRef.get("cape").getHeight() -2))){
				capeX[i] = capeX[0] - (i* (ImageRef.get("cape").getHeight() -2));
			}
			
			if(capeX[i] < capeX[i-1]) capeX[i] += gravityX;
			if(capeX[i] > capeX[i-1]) capeX[i] -= gravityX;
		}

	}
	
	public void drawCape(){
		for(int i = 0; i < numCapes; i++){
			ImageRef.get("cape").draw(capeX[i], capeY[i]);
			//System.out.println("cape " + i + " = " + capeX[i] + " " + capeY[i]);
		}
	}
	
	

}
