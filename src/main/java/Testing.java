import BoardMovement.*;

public class Testing {

	
	public static void main(String[] args) {
		
		
		ChessBoard board = new ChessBoard(true);
		System.out.println(board);
		board.move(new Position(7,6), new Position(7,4));
		board.move(new Position(0,1), new Position(0,3));
		board.move(new Position(7,4), new Position(7,3));
		board.move(new Position(6,1), new Position(6,3));
		System.out.println(board);
		System.out.println(board.getMoves(new Position(7,3)));
		System.out.println(board);
		board.move(new Position(7,3), new Position(6,2));
		System.out.println(board);
		System.out.println(board.lastMove().getPositions());
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
