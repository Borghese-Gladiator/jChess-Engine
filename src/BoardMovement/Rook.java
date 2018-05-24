package BoardMovement;

public class Rook extends Piece {
	final static char type = 'r';
	boolean hasMoved;
	
	public Rook(boolean isWhite, char type){
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
