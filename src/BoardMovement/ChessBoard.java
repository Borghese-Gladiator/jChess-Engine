package BoardMovement;
import java.util.ArrayList;

public class ChessBoard {
	private Piece[][] board;
	
	public ChessBoard(){
		board = new Piece[8][8];
		//Initialize pieces on board
	}
	
	public Piece getPiece(Position pos){
		return board[x][y];
	}
	
	public boolean move(Position posFrom, Position posTo){
		if(!isValidMove(posFrom, posTo))
			throw new IllegalArgumentException("Invalid move");
		//Move piece
	}
	
	private boolean isValidMove(Position posFrom, Position posTo){
		for(Position move: moves(posFrom))
			if(move.getX()==posTo.getX()&&move.getY()==posTo.getY())
				return true;
		return false;
	}
	
	public ArrayList<Position> moves(Position pos){
		if(board[pos.getX()][pos.getY()]==null)
			throw new IllegalArgumentException("No Piece at that Location");
		switch(board[pos.getX()][pos.getY()].getType()){
		case 'k':
			return getMovesK(pos);
		case 'q':
			return getMovesQ(pos);
		case 'b':
			return getMovesB(pos);
		case 'n':
			return getMovesN(pos);
		case 'r':
			return getMovesR(pos);
		default://case 'p':
			return getMovesP(pos);
		}
	}

	public ArrayList<Position> getMovesK(Position pos){
		return null;//Do code
	}
	public ArrayList<Position> getMovesQ(Position pos){
		return null;//Do code
	}
	public ArrayList<Position> getMovesB(Position pos){
		return null;//Do code
	}
	public ArrayList<Position> getMovesN(Position pos){
		return null;//Do code
	}
	public ArrayList<Position> getMovesR(Position pos){
		return null;//Do code
	}
	public ArrayList<Position> getMovesP(Position pos){
		return null;//Do code
	}
	
	public void doMove(Position pos, Position posTo){
		
	}
	
	public boolean isBeingAttacked(boolean byWhite, Position pos){
		
	}
}
