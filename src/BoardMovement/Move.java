package BoardMovement;

import java.util.ArrayList;

public class Move {
	//First is where piece moved from (has the piece)
	//Second is where piece moved to (piece = null)
	//third is pos of removed pawn if enpassant, or where rook moved from if castle (has pawn, or has rook)
	//fourth is where rook moved to (piece = null)
	private ArrayList<Position> origPos;
	private ArrayList<Piece> origPiece;
	
	public Move(){
		origPos = new ArrayList<Position>();
		origPiece = new ArrayList<Piece>();
	}
	
	public Move(Position from, Position to, Piece piece){
		this();
		origPos.add(from);
		origPos.add(to);
		origPiece.add(piece);
		origPiece.add(null);
	}
	
	public void addSpot(Piece piece, Position pos){
		origPos.add(pos);
		origPiece.add(piece);
	}
	
	public ArrayList<Position> getPositions(){
		return origPos;
	}
	
	public Position changedPos(int index){
		return origPos.get(index);
	}
	
	public Piece changedPiece(int index){
		return origPiece.get(index);
	}
	
	public int size(){
		return origPos.size();
	}
	
	public boolean isPawnDoubleForward(){
		if(origPos.size()!=2)
			return false;
		if(!(origPiece.get(0) instanceof Pawn))
			return false;
		if((origPos.get(0).getY()+2==origPos.get(1).getY())||(origPos.get(0).getY()-2==origPos.get(1).getY()))
			return true;
		return false;
	}
}
