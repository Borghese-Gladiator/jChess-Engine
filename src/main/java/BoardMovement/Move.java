package BoardMovement;

import java.util.ArrayList;

public class Move {
	//First is where piece moved from (has the piece)
	//Second is where piece moved to (piece = null)
	//third is pos of removed pawn if enpassant, or where rook moved from if castle (has pawn, or has rook)
	//fourth is where rook moved to (piece = null)
	private ArrayList<Position> origPos;
	private ArrayList<Piece> origPiece;
	private ArrayList<Boolean> origMoved;
	
	public Move(){
		origPos = new ArrayList<Position>();
		origPiece = new ArrayList<Piece>();
		origMoved = new ArrayList<Boolean>();
	}
	
	public Move(Position from, Position to, Piece pieceFrom, Piece pieceTo){
		this();
		origPos.add(from);
		origPos.add(to);
		origPiece.add(pieceFrom);
		origPiece.add(pieceTo);
		origMoved.add(pieceFrom.getHasMoved());
		if(pieceTo==null)
			origMoved.add(null);
		else
			origMoved.add(pieceTo.getHasMoved());
	}
	
	public void addSpot(Piece piece, Position pos){
		origPos.add(pos);
		origPiece.add(piece);
		if(piece==null)
			origMoved.add(null);
		else
			origMoved.add(piece.getHasMoved());
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
	
	public Boolean changedMoved(int index){
		return origMoved.get(index);
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
	
	public String toString(){
		String str = "";
		for(int i = 0; i < size(); i++)
			str += origPos.get(i) + " was " + origPiece.get(i) + " ";
		return str;
	}
}
