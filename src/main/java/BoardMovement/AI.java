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
		board = new Piece[8][8];
	 }
	 // get moves needs to pass in the board 
	 public ArrayList<Position> getmove(Piece [][] b){
		ArrayList<Position> bestMove = null;
		 for(int i = 0; i < 8;i++){
			 for(int x = 0; x<8; x++){
				 if(b[i][x] instanceof Rook) 		board[i][x]= new Rook(b[i][x].isWhite());
				 else if(b[i][x] instanceof Queen) 	board[i][x]= new Queen(b[i][x].isWhite());
				 else if(b[i][x] instanceof Bishop) 	board[i][x]= new Bishop(b[i][x].isWhite());
				 else if(b[i][x] instanceof Knight)	board[i][x]= new Knight(b[i][x].isWhite());
				 else if(b[i][x] instanceof Pawn)	board[i][x]= new Pawn(b[i][x].isWhite());
				 else if(b[i][x] instanceof King)	board[i][x]= new King(b[i][x].isWhite());
				 else if(b[i][x] ==null) board[i][x]= null;
				 
			 }
		 }
		
		chess = new ChessBoard(board, false); 
		 setattack();
		 for(int i = 0; i < 8;i++){
			for(int x=0; x<8;x++){
				boolean same =false;
				if(board[i][x]!=null){
				if(board[i][x].isWhite()== false){
					ArrayList<Position> list= chess.getMoves(new Position(i,x));
					if(board[i][x] instanceof King){
						if(chess.canCastle()==true){
							for(int z = 0; z<list.size();z++){
								if(Math.abs(i-list.get(z).getX()) == 2){
									bestMove.add(new Position(i,x));
									bestMove.add(list.get(z));
								}
							}
						}		
					}
					for(int y = 0; y< list.size();y++){
						if(whiteAttack[list.get(y).getX()][list.get(y).getY()]=='a'&&blackdefend[list.get(y).getX()][list.get(y).getY()]==' ') {						
							list.remove(y);
							y--;
						}
						if(board[list.get(y).getX()][list.get(y).getY()].isWhite() == true &&  board[list.get(y).getX()][list.get(y).getY()].actionvalue() == board[i][x].actionvalue()){
							same = true;
						}

						else{
							chess.move(new Position(i,x), list.get(y));
							ArrayList <Position> temp = chess.getMoves(list.get(y));
							chess.undoMove();
							aiValidMoves.checkmove(board,new Position(i,x),temp);
							Evaluation.move(new Position(i,x), list.get(y), board, same);	
						}		
					}		
				}
				}
			}
		 }
		bestMove = Evaluation.gethighest();
		Evaluation.reset();
		return bestMove;
	 }
	 private void setattack(){
		 for(int i = 0; i < 8;i++){
			 for(int x = 0; x<8; x++){
				 if(board[i][x]!= null){
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
	 


}
