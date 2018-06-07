package BoardMovement;

public abstract class Piece {
	private boolean isWhite;
	public Piece(boolean isWhite){
		this.isWhite = isWhite;
	}
	
	public boolean isWhite(){
		return isWhite;
	}
	
	public abstract char getType();
	public abstract boolean getHasMoved();
	public abstract void setHasMoved(boolean move);
	public abstract int getval();
	public abstract int getattack();
	public abstract int getdefend();
	public abstract void setattack();
	public abstract void setdefend();

}
