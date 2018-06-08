package BoardMovement;

public class Pawn extends Piece{
	final static char type = 'p';
	boolean hasmoved;
	boolean twomove;
	public final int val = 10;
	private int defend = 0;
	private int attack = 0;
	public Pawn(boolean isWhite){
		super(isWhite);
		hasmoved = false;
		twomove = false;
	}
	
	public char getType(){
		return type;
	}
	
	public boolean getHasMoved(){
		return hasmoved;
	}
	
	public void setHasMoved(){
		hasmoved = true;
	}
	public void settwomove(){
		twomove = true;
	}
	public boolean gettwomove(){
		return twomove;
	}
	public void settwomove(boolean move){
		twomove = move;
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
	public int actionvalue(){
		return 6;
	}
}
