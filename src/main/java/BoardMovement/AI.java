package BoardMovement;

import java.util.ArrayList;

public class AI {
	 private ArrayList <Position> allmoves = new ArrayList<Position>();
	 private Piece[][] board;
	 private boolean [][] whiteAttack;
	 private boolean[][]blackdefend;
	 private ChessBoard chess;
	 private Position bestMove;
	 public AI(Piece [][] b){
		 for(int i = 0; i < 8;i++){
			 for(int x = 0; x<8; x++){
				 board[i][x]= b[i][x];
			 }
		 }
	 }
	 public Position getmove(){
		 for(int i = 0; i < 8;i++){
			for(int x=0; x<8;x++){
				if(board[i][x].isWhite()== false){
					ArrayList<Position> list= chess.getMoves(new Position(i,x));
					for(int y = 0; y< list.size();y++){
						ArrayList<Position> possible= chess.getMoves(list.get(y));
						

					}
				}
			}
		 }
		return bestMove;
	 }
	 private void setattack(){
		 for(int i = 0; i < 8;i++){
			 for(int x = 0; x<8; x++){
				 if(board[i][x].isWhite()== true){
						ArrayList<Position> list= chess.getMoves(new Position(i,x));
						for(int y = 0;y<list.size();y++){
<<<<<<< HEAD
							whiteAttack
=======
							//whiteAttack[list.get(y).getX()];
>>>>>>> 00e70b4b8fa8976bff91a0d65a84beccd66b5cb8
						}
				 }

			 }
		 }
	 }

}
