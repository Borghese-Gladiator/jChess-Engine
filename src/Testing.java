import BoardMovement.*;

public class Testing {

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		board.addPiece(new Position(0,6), new Bishop(true));
		board.addPiece(new Position(2,4), new Pawn(false));
		System.out.print(board.getMoves(new Position(0,6)));

	}

}
