package BoardMovement;

public class Knight extends Piece{
	final static char type = 'n';
	boolean hasMoved;
	private final int val = 30;
	public Knight(boolean isWhite){
		super(isWhite);
		hasMoved = false;
	}
	
	public char getType(){
		return type;
	}
	
	public boolean getHasMoved(){
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved){
		this.hasMoved = hasMoved;
	}
	public int getval(){
		return val;
	}

}
