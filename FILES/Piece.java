
public abstract class Piece {
	char type;
	boolean isWhite;
	
	public Piece(boolean isWhite){
		this.isWhite = isWhite;
	}
	
	public abstract char getType();
}
