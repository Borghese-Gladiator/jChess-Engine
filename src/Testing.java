import BoardMovement.*;

public class Testing {

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		board.addPiece(new Position(3,3), new Knight(true));
		board.addPiece(new Position(4,5), new Knight(true));
		board.addPiece(new Position(5,4), new Knight(false));
		System.out.print(board.getMoves(new Position(3,3)));
		System.out.print('a');
	}

}
