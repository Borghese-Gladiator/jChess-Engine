package BoardMovement;

public class Pawn extends Piece{
	final static char type = 'p';
	boolean hasmoved;
	boolean twomove;
	public Pawn(boolean isWhite){
		super(isWhite);
		hasmoved = false;
		twomove = false;
	}
	
	public char getType(){
		return type;
	}
	
	public boolean getHasMoved(){
		return hasmoved;
	}
	
	public void setHasMoved(){
		hasmoved = true;
	}
	public void settwomove(){
		twomove = true;
	}
	public boolean gettwomove(){
		return twomove;
	}
	

}
