import BoardMovement.*;

public class Testing {

	
	public static void main(String[] args) {
		ChessBoard board = new ChessBoard(true);
		System.out.println(board.getMovingSide() + " \n" + displayBoard(board));
		board.addPiece(new Position(5,5),new Knight(false));
		System.out.println(board.getMoves(new Position(4,6)));
		board.move(new Position(4,6), new Position(4,4));
		System.out.println(board.getMovingSide() + " \n" + displayBoard(board));
		board.undoMove();
		System.out.println(board.getMovingSide() + " \n" + displayBoard(board));
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
					if(board[x][y].isWhite())
						str += "w";
					else
						str += "b";
					str += Character.toUpperCase(board[x][y].getType()) + " ";
				}
			}
			str += "\n";
		}
		return str;
	}

}
