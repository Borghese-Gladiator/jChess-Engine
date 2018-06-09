package BoardMovement;

import java.util.ArrayList;

public class AI {
	private ArrayList <Position> allmoves = new ArrayList<Position>();
	private ChessBoard player = new ChessBoard();
	private Position bestMove;
	private double bestVal;
	public AI(){
		allmoves = player.getAllMoves();
		bestMove =null;
		bestVal = -9999;
	}
	

}
