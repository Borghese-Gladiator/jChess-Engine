package BoardMovement;

public class King extends Piece{
	
	final static char type = 'k';
	boolean hasMoved;
	private final int val = 900;
	private int defend = 0;
	private int attack = 0;
	public King(boolean isWhite){
		super(isWhite);
		hasMoved = false;
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
	public void setattack(){
		attack++;
	}
	public void setdefend(){
		defend++;
	}
}
