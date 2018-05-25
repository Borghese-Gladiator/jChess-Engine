package BoardMovement;

public class King extends Piece{
	
	final static char type = 'k';
	boolean hasMoved;
	
	public King(boolean isWhite){
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
