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
<<<<<<< HEAD
	public abstract boolean getHasMoved();
	public abstract void setHasMoved(boolean move);
	public abstract int getval();
	public abstract int getattack();
	public abstract int getdefend();
	public abstract void setattack();
	public abstract void setdefend();
=======
>>>>>>> 6f23f7e97ed38c6242b9e6699a098a3e0961ed68

}
