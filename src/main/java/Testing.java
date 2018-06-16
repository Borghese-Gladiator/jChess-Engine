import BoardMovement.*;
//import TerminalIO.KeyboardReader;

public class Testing {

	
	public static void main(String[] args) {
		//KeyboardReader b = new KeyboardReader();
		ChessBoard board = new ChessBoard(true);
		System.out.println(board);
		board.move(new Position(4,6), new Position(4,5));
		board.move(new Position(0,1), new Position(0,2));
		board.move(new Position(3,7), new Position(7,3));
		board.move(new Position(0,2), new Position(0,3));
		board.move(new Position(5,7), new Position(2,4));
		board.move(new Position(0,3), new Position(0,4));
		board.move(new Position(2,4), new Position(5,1));
		System.out.println(board);
		System.out.println(board.isCheckMated());
		/*
		while(true){
			//board.move(new Position(b.readInt(),b.readInt()), new Position(b.readInt(),b.readInt()));
			System.out.println(board);
			System.out.println(board.isCheckMated());
		}
		*/
		
		
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
