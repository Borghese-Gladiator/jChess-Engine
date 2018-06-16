package BoardMovement;

import GUI.ImageDatabase;

public class Pawn extends Piece{
	final static char type = 'p';
	boolean twomove;
	public final int val = 10;
	private int defend = 0;
	private int attack = 0;
	public Pawn(boolean isWhite){
		super(isWhite);
		twomove = false;
		if (isWhite)
		{
			setImg(ImageDatabase.getTile("Pawn-WHITE"));
		}
		else
		{
			setImg(ImageDatabase.getTile("Pawn-BLACK"));
		}
	}
	
	public char getType(){
		return type;
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

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
}
