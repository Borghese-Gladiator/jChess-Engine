import BoardMovement.*;

public class Testing {

	
	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		board.addPiece(new Position(4,0), new King(true));
		board.addPiece(new Position(0,0), new Rook(true));
		board.addPiece(new Position(7,0), new Rook(false));
		System.out.print(board.getMoves(new Position(4,0)));
	}

}
