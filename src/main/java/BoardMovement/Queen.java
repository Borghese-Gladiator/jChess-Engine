package BoardMovement;

import GUI.ImageDatabase;

public class Queen extends Piece {
	final static char type = 'q';
	private final int val = 90;
	public Queen(boolean isWhite){
		super(isWhite);
		if (isWhite)
		{
			setImg(ImageDatabase.getTile("Queen-WHITE"));
		}
		else
		{
			setImg(ImageDatabase.getTile("Queen-BLACK"));
		}
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
	public int actionvalue(){
		return 1;
	}

	
}
