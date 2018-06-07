package BoardMovement;

public abstract class Piece {
	private boolean isWhite;
	private boolean hasMoved;
	public Piece(boolean isWhite){
		this.isWhite = isWhite;
		hasMoved = false;
	}
	
	public boolean isWhite(){
		return isWhite;
	}
	
	public boolean getHasMoved(){
		return hasMoved;
	}
	
	public void setHasMoved(boolean move){
		hasMoved = move;
	}
	
	public abstract char getType();

	public abstract int getval();
	public abstract int getattack();
	public abstract int getdefend();
	public abstract void setattack();
	public abstract void setdefend();

}
