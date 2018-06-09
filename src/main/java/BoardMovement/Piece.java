package BoardMovement;

public abstract class Piece {
	private boolean isWhite;
	protected boolean hasMoved;
	protected int attack;
	protected int defend;
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
	
	public String toString(){
		String str;
		if(isWhite)
			str = "w";
		else
			str = "b";
		return str + Character.toUpperCase(getType());
	}

	public abstract int getval();
	public abstract int getattack();
	public abstract int getdefend();
	public abstract void setattack(int i);
	public abstract void setdefend(int i);
	public abstract int actionvalue();

}
