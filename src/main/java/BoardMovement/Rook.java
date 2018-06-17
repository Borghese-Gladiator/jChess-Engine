package BoardMovement;

import java.awt.Image;

import GUI.ImageDatabase;

public class Rook extends Piece {
	final static char type = 'r';
	private final int val = 50;
	
	public Rook(boolean isWhite){
		super(isWhite);
		hasMoved = false;
		if (isWhite)
		{
			setImg(ImageDatabase.getTile("Rook-WHITE"));
		}
		else
		{
			setImg(ImageDatabase.getTile("Rook-BLACK"));
		}
	}
	
	public char getType(){
		return type;
	}
	
	public int getval(){
		return val;
	}
	public int actionvale(){
		return 2;
	}
	public void setattack(int i){
		attack+=i;
	}
	public void setdefend(int i){
		defend+=i;
	}

	@Override
	public int getattack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getdefend() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actionvalue() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
