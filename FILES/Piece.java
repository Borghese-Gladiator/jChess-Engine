
public abstract class Piece {
	char type;
	boolean isWhite;
	boolean hasMoved;
	
	public Piece(boolean isWhite){
		this.isWhite = isWhite;
		hasMoved = false;
	}
	
	public abstract char getType();
}
