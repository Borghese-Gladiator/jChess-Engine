package BoardMovement;

public class Pawn extends Piece{
	final static char type = 'p';
	boolean twomove;
	int move;
	
	public Pawn(boolean isWhite){
		super(isWhite);
		twomove = false;
		move =0;
	}
	
	public char getType(){
		return type;
	}
	
	public boolean getHasMoved(){
		return twomove;
	}
	
	public void setHasMoved(boolean hasMoved){
		this.twomove = hasMoved;
	}
	

}
