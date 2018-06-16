package BoardMovement;

import GUI.ImageDatabase;

public class Bishop extends Piece {
	final static char type = 'b';
	private final static int val=30;
	public Bishop(boolean isWhite){
		super(isWhite);
		if (isWhite)
		{
			setImg(ImageDatabase.getTile("Bishop-WHITE"));
		}
		else
		{
			setImg(ImageDatabase.getTile("Bishop-BLACK"));
		}
	}
	
	
	public int actionvalue(){
		return 3;
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


	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
