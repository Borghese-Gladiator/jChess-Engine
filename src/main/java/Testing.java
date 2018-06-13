import BoardMovement.*;

public class Testing {

	
	public static void main(String[] args) {
		
		
		ChessBoard board = new ChessBoard();
		board.addPiece(new Position(0,0),new King(false));
		board.addPiece(new Position(7,7), new King(true));
		board.addPiece(new Position(0,4), new Pawn(false));
		board.addPiece(new Position(0,7), new Rook(true));
		board.addPiece(new Position(1,6), new Pawn(true));
		System.out.println(board);
		board.move(new Position(1,6),new Position(1,4));
		System.out.println(board);
		System.out.println(board.getMoves(new Position(0,4)));
		
	}
	
	public static String displayBoard(ChessBoard brd){
		Piece[][] board = brd.getBoard();
		String str = "yx 0  1  2  3  4  5  6  7  \n";
		for(int y = 0; y<8; y++){
			str += y + "  ";
			for(int x = 0; x<8; x++){
				if(board[x][y]==null)
					str += "[] ";
				else{
					str += board[x][y] + " ";
				}
			}
			str += "\n";
		}
		return str;
	}

}
