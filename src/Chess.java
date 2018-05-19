import java.util.ArrayList;

public class Chess {
	private Piece[][] board;
	
	public Chess(){
		board = new Piece[8][8];
		//Initialize pieces on board
	}
	
	public ArrayList<Move> moves(int x, int y){
		switch(board[x][y].getType()){
		case KING:
			return getMovesK(x, y);
		case QUEEN:
			return getMovesQ(x, y);
		case BISHOP:
			return getMovesB(x, y);
		case KNIGHT:
			return getMovesN(x, y);
		case ROOK:
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
}
