package BoardMovement;

public class Bishop extends Piece {
	final static char type = 'b';
	private final static int val=30;
	public Bishop(boolean isWhite){
		super(isWhite);
	}
	
	
	public int actionvalue(){
		return 3;
	}
	public char getType(){
		return type;
	}
	
	public boolean getHasMoved(){
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved){
		this.hasMoved = hasMoved;
	}
	public int getval(){
		return val;
	}
	public int getdefend(){
		return defend;
	}
	public int getattack(){
		return attack;
	}
	public void setattack(int i){
		attack+=i;
	}
	public void setdefend(int i){
		defend+=i;
	}
	
}
