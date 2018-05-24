package BoardMovement;

public class Move {
	int xTo;
	int yTo;
	
	public Move(int x, int y){
		xTo = x;
		yTo = y;
	}
	
	public int getX(){
		return xTo;
	}
	
	public int getY(){
		return yTo;
	}
}
