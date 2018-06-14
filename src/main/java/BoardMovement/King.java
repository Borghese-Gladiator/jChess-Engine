package BoardMovement;

public class King extends Piece{
	
	final static char type = 'k';
	private final int val = 900;
	private int defend = 0;
	private int attack = 0;
	public King(boolean isWhite){
		super(isWhite);
	}
	
	public char getType(){
		return type;
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
		return 1;
	}
}
