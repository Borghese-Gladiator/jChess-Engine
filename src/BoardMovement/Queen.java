package BoardMovement;

public class Queen extends Piece {
	final static char type = 'q';
	
	public Queen(boolean isWhite){
		super(isWhite);
	}
	
	public char getType(){
		return type;
	}
}
