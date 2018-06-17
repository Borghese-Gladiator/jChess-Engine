package BoardMovement;

import java.util.ArrayList;

public class aiValidMoves {
 

	public static void checkpiece(Piece[][]board, Position current, ArrayList<Position> list){
		boolean white = board[current.getX()][current.getY()].isWhite();
		
		for(int i = 0; i<list.size(); i++){
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()== white)
				board[list.get(i).getX()][list.get(i).getY()].setdefend(board[current.getX()][current.getY()].actionvalue());
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()!= white)
				board[list.get(i).getX()][list.get(i).getY()].setattack(board[current.getX()][current.getY()].actionvalue());
		}
	}
	public static void checkmove(Piece[][]board, Position old,ArrayList<Position> list){
		boolean white = board[old.getX()][old.getY()].isWhite();
		
		for(int i = 0; i<list.size(); i++){
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()== white)
				board[old.getX()][old.getY()].settotal(board[list.get(i).getX()][list.get(i).getY()].getval()/2);
			if(board[list.get(i).getX()][list.get(i).getY()].isWhite()!= white)
				board[old.getX()][old.getY()].settotal(board[list.get(i).getX()][list.get(i).getY()].getval());

			
		}
	}

}
