package BoardMovement;

public class Bishop extends Piece {
	final static char type = 'b';
	private final static int val=30;
	public Bishop(boolean isWhite){
		super(isWhite);
	}
	
	public char getType(){
		return type;
	}
	public int getval(){
		return val;
	}
	
}
