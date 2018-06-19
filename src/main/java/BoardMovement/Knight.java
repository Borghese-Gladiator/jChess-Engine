package BoardMovement;

import GUI.ImageDatabase;

public class Knight extends Piece{
	final static char type = 'n';
	private final int val = 30;
	private int defend = 0;
	private int attack = 0;
	public Knight(boolean isWhite){
		super(isWhite);
		if (isWhite)
		{
			setImg(ImageDatabase.getTile("Knight-WHITE"));
		}
		else
		{
			setImg(ImageDatabase.getTile("Knight-BLACK"));
		}
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
		return 3;
	}
}
