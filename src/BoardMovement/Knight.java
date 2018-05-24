package BoardMovement;

public class Knight extends Piece{
	final static char type = 'n';
	boolean hasMoved;
	
	public Knight(boolean isWhite, char type){
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
