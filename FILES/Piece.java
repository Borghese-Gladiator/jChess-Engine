
public abstract class Piece {
	char type;
	boolean isWhite;
	
	public Piece(boolean isWhite, char type){
		this.isWhite = isWhite;
	}
	
	public abstract char getType();
}
