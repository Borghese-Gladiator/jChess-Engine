package BoardMovement;

import java.util.*;

public class AI {
	private ArrayList <Position> allmoves = new ArrayList<Position>;
	private ChessBoard player = new ChessBoard();
	public AI(){
		allmoves = player.getallmoves();
	}

}
