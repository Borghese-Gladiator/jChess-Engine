import BoardMovement.*;
//import TerminalIO.KeyboardReader;

public class Testing {

	
	public static void main(String[] args) {
		//KeyboardReader b = new KeyboardReader();
		ChessBoard board = new ChessBoard(true);
		System.out.println(board);
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
