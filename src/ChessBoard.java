import java.util.ArrayList;

public class ChessBoard {
	private Piece[][] board;
	
	public ChessBoard(){
		board = new Piece[8][8];
		//Initialize pieces on board
	}
	
	public Piece getPiece(int x, int y){
		return board[x][y];
	}
	
	public boolean move(int x, int y, int xTo, int yTo){
		
	}
	
	public ArrayList<Move> moves(int x, int y){
		switch(board[x][y].getType()){
		case 'k':
			return getMovesK(x, y);
		case 'q':
			return getMovesQ(x, y);
		case 'b':
			return getMovesB(x, y);
		case 'n':
			return getMovesN(x, y);
		case 'r':
			return getMovesR(x, y);
		default://case 'p':
			return getMovesP(x, y);
		}
	}
	
	public ArrayList<Move> getMovesK(int x, int y){
		return null;//Do code
	}
	public ArrayList<Move> getMovesQ(int x, int y){
		return null;//Do code
	}
	public ArrayList<Move> getMovesB(int x, int y){
		return null;//Do code
	}
	public ArrayList<Move> getMovesN(int x, int y){
		return null;//Do code
	}
	public ArrayList<Move> getMovesR(int x, int y){
		return null;//Do code
	}
	public ArrayList<Move> getMovesP(int x, int y){
		return null;//Do code
	}
	
	public void doMove(int x, int y, int xto, int yto){
		
	}
	
	public boolean isBeingAttacked(boolean byWhite, int x, int y){
		
	}
}
