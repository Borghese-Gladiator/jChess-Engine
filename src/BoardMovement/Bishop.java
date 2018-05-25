package BoardMovement;

public class Bishop extends Piece {
	final static char type = 'b';
	
	public Bishop(boolean isWhite){
		super(isWhite);
	}
	
	public char getType(){
		return type;
	}
}
