package BoardMovement;

import java.util.ArrayList;

public class aiValidMoves {
	private static boolean[][]BlackAttack = new boolean[8][8];
	private static boolean [][]WhiteAttack = new boolean[8][8];
	private static Position BK;
	private static Position WK;
	
	private static void checkmove(Piece[][]board, Position current, ArrayList<Position> list){
		boolean white = board[current.getX()][current.getY()].isWhite();
		if(white == true)
			BlackAttack[current.getX()][current.getY()] =true;
		else
			WhiteAttack[current.getX()][current.getY()] =true;
		for(int i = 0; i<list.size(); i++){
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()== white)
				board[list.get(i).getX()][list.get(i).getY()].setdefend(board[current.getX()][current.getY()].actionvalue());
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()!= white)
				board[list.get(i).getX()][list.get(i).getY()].setattack(board[current.getX()][current.getY()].actionvalue());
			
			
		}
	}

}
