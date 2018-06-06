package BoardMovement;

public class Queen extends Piece {
	final static char type = 'q';
	private final int val = 90;
	public Queen(boolean isWhite){
		super(isWhite);
	}
	
	public char getType(){
		return type;
	}
	public int getval(){
		return val;
	}
}
