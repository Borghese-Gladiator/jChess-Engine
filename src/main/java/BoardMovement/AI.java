package BoardMovement;

import java.util.ArrayList;

public class AI {
	 private ArrayList <Position> allmoves = new ArrayList<Position>();
	 private Piece[][] board;
	 private char [][] whiteAttack;
	 private char[][]blackdefend;
	 private ChessBoard chess;
	 public AI(){
		whiteAttack = new char[8][8];
		blackdefend = new char[8][8];
	 }
	 // get moves needs to pass in the board 
	 public ArrayList<Position> getmove(Piece [][] b){
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
						if(whiteAttack[list.get(y).getX()][list.get(y).getY()]=='a'&&blackdefend[list.get(y).getX()][list.get(y).getY()]==' ')						
							list.remove(y);
						else{
							Piece [][] temp= new Piece[8][8];
							for(int r = 0; i < 8;i++){
								 for(int c = 0; x<8; x++){
									 temp[r][c]= board[r][c];
								 }
							 }
							Piece test = temp[i][x];
							temp[i][x]= null;
							temp[list.get(y).getX()][list.get(y).getY()] = test;
							aiValidMoves.checkmove(temp,list.get(y),chess.getMoves(list.get(y)));
							Evaluation.move(new Position(i,x), list.get(y), temp);		
						}		
					}		
				}
			}
		 }
		ArrayList<Position> bestMove = null;
		bestMove = Evaluation.gethighest();
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
