package BoardMovement;

public class Pawn extends Piece{
	final static char type = 'p';
	boolean hasMoved;
	
	public Pawn(boolean isWhite){
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

}
