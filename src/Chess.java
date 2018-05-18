import java.util.ArrayList;

public class Chess {
	private Piece[][] board;
	
	public Chess(){
		board = new Piece[8][8];
		//Initialize pieces on board
	}
	
	public ArrayList<Piece> moves(int x, int y){
		switch(board[x][y].getType()){
		case 'k':
			return movesK(x, y);
		case 'q':
			return movesQ(x, y);
		case 'b':
			return movesB(x, y);
		case 'n':
			return movesN(x, y);
		case 'r':
			return movesR(x, y);
		default://case 'p':
			return movesP(x, y);
		}
	}
	
	public ArrayList<Piece> movesK(int x, int y){
		return null;
	}
	public ArrayList<Piece> movesQ(int x, int y){
		return null;
	}
	public ArrayList<Piece> movesB(int x, int y){
		return null;
	}
	public ArrayList<Piece> movesN(int x, int y){
		return null;
	}
	public ArrayList<Piece> movesR(int x, int y){
		return null;
	}
	public ArrayList<Piece> movesP(int x, int y){
		return null;
	}
}
