package BoardMovement;
import java.util.ArrayList;

public class ChessBoard {
	private Piece[][] board;
	
	public ChessBoard(){
		board = new Piece[8][8];
		//Initialize pieces on board
	}
	
	/**
	 * Returns the Piece at the specified position
	 * @param pos The position of the piece to return
	 * @return The piece at that position
	 */
	public Piece getPiece(Position pos){
		return board[pos.getX()][pos.getY()];
	}
	
	/**
	 * Moves a piece
	 * @param posFrom The initial position of the piece
	 * @param posTo The position of the piece after the move
	 */
	public void move(Position posFrom, Position posTo){
		if(!isValidMove(posFrom, posTo))
			throw new IllegalArgumentException("Invalid move");
		//Move piece
	}
	
	/**
	 * Determines if a move is valid
	 * @param posFrom the position from which the piece will move
	 * @param posTo the position to which the piece will move
	 * @return Returns true if that is a valid move for that piece. False if invalid.
	 */
	private boolean isValidMove(Position posFrom, Position posTo){
		for(Position move: moves(posFrom))
			if(move.getX()==posTo.getX()&&move.getY()==posTo.getY())
				return true;
		return false;
	}
	
	/**
	 * Returns an ArrayList of all the valid positions the specified piece can move to
	 * @param pos The position of the piece
	 * @return an ArrayList with all valid positions the piece can move to
	 */
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

	private ArrayList<Position> getMovesK(Position pos){
		return null;//Do code
	}
	private ArrayList<Position> getMovesQ(Position pos){
		return null;//Do code
	}
	private ArrayList<Position> getMovesB(Position pos){
		return null;//Do code
	}
	private ArrayList<Position> getMovesN(Position pos){
		return null;//Do code
	}
	private ArrayList<Position> getMovesR(Position pos){
		return null;//Do code
	}
	private ArrayList<Position> getMovesP(Position pos){
		return null;//Do code
	}
	
	public boolean isBeingAttacked(boolean byWhite, Position pos){
		
	}
}
