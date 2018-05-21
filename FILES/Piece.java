
public abstract class Piece {
	char type;
	boolean isWhite;
	
	public Piece(boolean isWhite, char type){
		this.isWhite = isWhite;
		this.type = type;
	}
	
	public abstract char getType();
}
