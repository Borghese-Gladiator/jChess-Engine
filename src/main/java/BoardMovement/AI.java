package BoardMovement;

import java.util.ArrayList;

public class AI {
	 private ArrayList <Position> allmoves = new ArrayList<Position>();
	 private Piece[][] board;
	 private char [][] whiteAttack;
	 private char[][]blackdefend;
	 private ChessBoard chess;
	 private Position bestMove;
	 public AI(){
		whiteAttack = new char[8][8];
		blackdefend = new char[8][8];
	 }
	 public Position getmove(Piece [][] b){
		 for(int i = 0; i < 8;i++){
			 for(int x = 0; x<8; x++){
				 board[i][x]= b[i][x];
			 }
		 }
		 setattack();
		 for(int i = 0; i < 8;i++){
			for(int x=0; x<8;x++){
				if(board[i][x].isWhite()== false){
					ArrayList<Position> list= chess.getMoves(new Position(i,x));
					for(int y = 0; y< list.size();y++){
						if(whiteAttack[list.get(y).getX()][list.get(i).getY()]=='a'&&blackdefend[list.get(y).getX()][list.get(i).getY()]==' ')						
							list.remove(y);
						else{
							aiValidMoves.checkmove(board,list.get(i),chess.getMoves(list.get(i)));
							
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
					 whiteAttack[i][x]='p';
						ArrayList<Position> list= chess.getMoves(new Position(i,x));
						for(int y = 0;y<list.size();y++){
							whiteAttack[list.get(y).getX()][list.get(y).getY()]='a';
						}
				 }
				 else if(board[i][x].isWhite()== false){
					 blackdefend[i][x]='p';
					 ArrayList<Position> list= chess.getMoves(new Position(i,x));
						for(int y = 0;y<list.size();y++){
							blackdefend[list.get(y).getX()][list.get(y).getY()]='d';
						}
				 }
				 
			 }
		 }
	 }

}
