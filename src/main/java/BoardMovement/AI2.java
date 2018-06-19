package BoardMovement;

import java.util.ArrayList;

public class AI2 {
	/**
	 * Finds the optimal next move.
	 * @param board The ChessBoard to find the move for
	 * @param depth How many moves ahead should be analyzed (0 for most stupid)
	 * @return The optimal move in the form [Position posFrom, Position posTo] (an ArrayList)
	 */
	public static ArrayList<Position> getMove(ChessBoard board, int depth){
		boolean thisSide = board.getMovingSide();
		ArrayList<Position> positions = board.getAllPieces(thisSide);
		int bestVal = Integer.MIN_VALUE;
		ArrayList<ArrayList<Position>> bestMoves = new ArrayList<ArrayList<Position>>();
		for(Position posFrom: positions){
			ArrayList<Position> moves = board.getMoves(posFrom);
			for(Position posTo: moves){
				board.move(posFrom, posTo);
				int thisMove = returnBest(board, thisSide, depth);
				if(thisMove > bestVal){
					bestVal = thisMove;
					bestMoves.clear();
					bestMoves.add(new ArrayList<Position>());
					bestMoves.get(0).add(posFrom);
					bestMoves.get(0).add(posTo);
				}
				else if(thisMove == bestVal){
					bestMoves.add(new ArrayList<Position>());
					bestMoves.get(bestMoves.size()-1).add(posFrom);
					bestMoves.get(bestMoves.size()-1).add(posTo);
				}
				board.undoMove();
			}
		}
		return bestMoves.get((int)(bestMoves.size()*Math.random()));
	}

	public static int returnBest(ChessBoard board, boolean isWhiteSide, int movesLeft){
		if(movesLeft==0)
			return board.getVal(isWhiteSide);
		boolean movingSide = board.getMovingSide();
		if(movingSide==isWhiteSide){
			ArrayList<Position> positions = board.getAllPieces(movingSide);
			int bestVal = Integer.MIN_VALUE;
			for(Position posFrom: positions){
				ArrayList<Position> moves = board.getMoves(posFrom);
				for(Position posTo: moves){
					board.move(posFrom, posTo);
					bestVal = Math.max(bestVal,returnBest(board, isWhiteSide, movesLeft-1));
					board.undoMove();
				}
			}
			return bestVal;
		}
		else{
			ArrayList<Position> positions = board.getAllPieces(movingSide);
			int bestVal = Integer.MAX_VALUE;
			for(Position posFrom: positions){
				ArrayList<Position> moves = board.getMoves(posFrom);
				for(Position posTo: moves){
					board.move(posFrom, posTo);
					bestVal = Math.min(bestVal,returnBest(board, isWhiteSide, movesLeft-1));
					board.undoMove();
				}
			}
			return bestVal;
		}
	}
}
