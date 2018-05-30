package BoardMovement;

public class Position {
	int x;
	int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}
