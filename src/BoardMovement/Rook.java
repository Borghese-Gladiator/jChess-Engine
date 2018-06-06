package BoardMovement;

public class Rook extends Piece {
	final static char type = 'r';
	boolean hasMoved;
	private final int val = 50;
	
	public Rook(boolean isWhite){
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
