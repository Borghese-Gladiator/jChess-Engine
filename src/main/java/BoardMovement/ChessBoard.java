package BoardMovement;
import java.util.ArrayList;

import GUI.Board;

public class ChessBoard extends Board{
	private Piece[][] board;
	private boolean isWhiteTurn;
	private ArrayList<Move> allMoves;
	
	public ChessBoard(){
		board = new Piece[8][8];
		allMoves = new ArrayList<Move>();
	}
	
	public ChessBoard(boolean whiteStart){
		this();
		board[0][0] = new Rook(false);
		board[1][0] = new Knight(false);
		board[2][0] = new Bishop(false);
		board[3][0] = new Queen(false);
		board[4][0] = new King(false);
		board[5][0] = new Bishop(false);
		board[6][0] = new Knight(false);
		board[7][0] = new Rook(false);
		for(int x = 0; x<8; x++)
			board[x][1] = new Pawn(false);
		for(int x = 0; x<8; x++)
			board[x][6] = new Pawn(true);
		board[0][7] = new Rook(true);
		board[1][7] = new Knight(true);
		board[2][7] = new Bishop(true);
		board[3][7] = new Queen(true);
		board[4][7] = new King(true);
		board[5][7] = new Bishop(true);
		board[6][7] = new Knight(true);
		board[7][7] = new Rook(true);
		isWhiteTurn = whiteStart;
	}
	
	public boolean getMovingSide(){
		return isWhiteTurn;
	}
	
	/**
	 * Returns the board
	 * @return the 2D array with the pieces. null where there isn't a piece.
	 */
	public Piece[][] getBoard(){
		return board;
	}
	
	public Move lastMove(){
		if(allMoves.size()==0)
			throw new IllegalArgumentException("No moves have been made");
		return allMoves.get(allMoves.size()-1);
	}
	
	private int movesMade(){
		return allMoves.size();
	}
	
	public void addPiece(Position pos, Piece piece){
		board[pos.getX()][pos.getY()] = piece;
	}
	
	/**
	 * Returns the Piece at the specified position
	 * @param pos The position of the piece to return
	 * @return The piece at that position
	 */
	public Piece getPiece(Position pos){
		return board[pos.getX()][pos.getY()];
	}
	
	/**
	 * Returns the positions modified by the last move
	 * @return an ArrayList of the positions
	 */
	public ArrayList<Position> updatedPosition(){
		if(movesMade()==0)
			throw new IllegalArgumentException("No moves have been made yet.");
		return lastMove().getPositions();
	}
	
	/**
	 * Moves a piece
	 * @param posFrom The initial position of the piece
	 * @param posTo The position of the piece after the move
	 */
	public void move(Position posFrom, Position posTo){
		if(!isValidMove(posFrom, posTo))
			throw new IllegalArgumentException("Invalid move");
		moveWithoutCheck(posFrom, posTo);
	}
	
	private void moveWithoutCheck(Position posFrom, Position posTo){
		if(/*condition for castling*/false)
			castle(posFrom, posTo);
		else if(/*condition for en passant*/false)
			enPassant(posFrom, posTo);
		else{
			allMoves.add(new Move(posFrom,posTo,getPiece(posFrom)));
			board[posTo.getX()][posTo.getY()] = getPiece(posFrom);
			board[posTo.getX()][posTo.getY()].setHasMoved(true);
			board[posFrom.getX()][posFrom.getY()] = null;
		}
		isWhiteTurn = !isWhiteTurn;
	}
	
	/**
	 * Undoes last move
	 */
	public void undoMove(){
		Move lastMove = lastMove();
		for(int i = 0; i < lastMove.size(); i++){
			board[lastMove.changedPos(i).getX()][lastMove.changedPos(i).getY()] = lastMove.changedPiece(i);
		}
		isWhiteTurn = !isWhiteTurn;
	}
	
	/**
	 * Determines if a move is valid
	 * @param posFrom the position from which the piece will move
	 * @param posTo the position to which the piece will move
	 * @return Returns true if that is a valid move for that piece. False if invalid.
	 */
	private boolean isValidMove(Position posFrom, Position posTo){
		for(Position move: getMoves(posFrom))
			if(move.getX()==posTo.getX()&&move.getY()==posTo.getY())
				return true;
		return false;
	}
	
	/**
	 * Returns an ArrayList of all the valid positions the specified piece can move to
	 * @param pos The position of the piece
	 * @return an ArrayList with all valid positions the piece can move to
	 */
	public ArrayList<Position> getMovesWithoutCheck(Position pos){
		if(board[pos.getX()][pos.getY()]==null)
			throw new IllegalArgumentException("No Piece at Position " + pos);
		
		ArrayList<Position> moves;
		switch(board[pos.getX()][pos.getY()].getType()){
			case 'k':moves = getMovesK(pos);
			case 'q':moves = getMovesQ(pos);
			case 'b':moves = getMovesB(pos);
			case 'n':moves = getMovesN(pos);
			case 'r':moves = getMovesR(pos);
			default :moves = getMovesP(pos);
		}
		for(int i = 0; i < moves.size(); i++){
			moveWithoutCheck(pos,moves.get(i));
			if(isChecked(!isWhiteTurn)){
				moves.remove(i);
				i--;
			}
			undoMove();
		}
		return moves;
	}

	public ArrayList<Position> getMovesK(Position pos){// capturing and check to see if the piece is and opposite color
		ArrayList<Position> moves = new ArrayList <Position>();
		boolean thisIsWhite = getPiece(pos).isWhite();
		int x = pos.getX();
		int y = pos.getY();
		int[] Xs = { 1, 1, 1, 0,-1,-1,-1, 0};
		int[] Ys = {-1, 0, 1, 1, 1, 0,-1,-1};
		for(int i = 0; i<8; i++){
			int newx = x+Xs[i];
			int newy = y+Ys[i];
			if(newx>=0&&newx<8&&newy>=0&&newy<8&&(board[newx][newy]==null||board[newx][newy].isWhite()!=thisIsWhite))
				moves.add(new Position(newx,newy));
		}
		if(checkCastleRight(pos) == true)
			moves.add(new Position(x+2,y));
		if(checkCastleLeft(pos) ==true)
			moves.add(new Position(x-2,y));
		return moves;
	}

	private ArrayList<Position> getMovesQ(Position pos){
		ArrayList<Position> moves = getMovesR(pos);
		moves.addAll(getMovesB(pos));
		return moves;
	}
	
	private ArrayList<Position> getMovesB(Position pos){
		ArrayList<Position> moves = new ArrayList<Position>();
		boolean thisIsWhite = board[pos.getX()][pos.getY()].isWhite();
		for(int x = pos.getX()+1, y = pos.getY()+1;(x<8) &&(y<8) &&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x++, y++){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX()+1, y = pos.getY()-1;(x<8) &&(y>=0)&&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x++, y--){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX()-1, y = pos.getY()+1;(x>=0)&&(y<8) &&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x--, y++){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX()-1, y = pos.getY()-1;(x>=0)&&(y>=0)&&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x--, y--){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		return moves;
	}
	private ArrayList<Position> getMovesN(Position pos){ // capturing and check to see if the piece is and opposite color
		ArrayList<Position> moves = new ArrayList <Position>();
		boolean thisIsWhite = getPiece(pos).isWhite();
		int x = pos.getX();
		int y = pos.getY();
		int[] Xs = {1,2,2,1,-1,-2,-2,-1};
		int[] Ys = {2,1,-1,-2,-2,-1,1,2};
		for(int i = 0; i<8; i++){
			int newx = x+Xs[i];
			int newy = y+Ys[i];
			if(newx>=0&&newx<8&&newy>=0&&newy<8&&(board[newx][newy]==null||board[newx][newy].isWhite()!=thisIsWhite))
				moves.add(new Position(newx,newy));
		}
		return moves;
	}
	private ArrayList<Position> getMovesR(Position pos){
		ArrayList<Position> moves = new ArrayList<Position>();
		boolean thisIsWhite = board[pos.getX()][pos.getY()].isWhite();
		for(int x = pos.getX()+1, y = pos.getY();(x<8) &&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x++){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX()-1, y = pos.getY();(x>=0)&&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); x--){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX(), y = pos.getY()+1;(y<8) &&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); y++){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		for(int x = pos.getX(), y = pos.getY()-1;(y>=0)&&(board[x][y]==null||board[x][y].isWhite()!=thisIsWhite); y--){
			moves.add(new Position(x,y));
			if((board[x][y] instanceof Piece)&&(board[x][y].isWhite()!=thisIsWhite))
				break;
		}
		return moves;
	}
	private ArrayList<Position> getMovesP(Position pos){// capturing and check to see if the piece is and opposite color
		ArrayList<Position> moves = new ArrayList <Position>();
		boolean thisIsWhite = getPiece(pos).isWhite();
		int x = pos.getX();
		int y = pos.getY();
		int forward;
		if(thisIsWhite)
			forward = -1;
		else
			forward = 1;
		if(board[x][y+forward]==null){
			moves.add(new Position(x,y+forward));
			if(!getPiece(pos).getHasMoved()&&board[x][y+forward+forward]==null)
				moves.add(new Position(x,y+forward+forward));
		}
		if(checkEnPassant(pos,true)||(x!=7&&(board[x+1][y+forward]!=null&&board[x+1][y+forward].isWhite()!=thisIsWhite)))
			moves.add(new Position(x+1,y+forward));
		if(checkEnPassant(pos,false)||(x!=0&&(board[x-1][y+forward]!=null&&board[x-1][y+forward].isWhite()!=thisIsWhite)))
			moves.add(new Position(x-1,y+forward));
		return moves;
	}
	
	private boolean checkCastleRight(Position from){
		Piece hold = getPiece(from);
		if(((King)hold).getHasMoved()== true)
			return false;
		if(!(getPiece(new Position(7,from.getY()))instanceof Rook))
			return false;
		if(((Rook) (getPiece(new Position(7,from.getY())))).isWhite() != hold.isWhite())
			return false;
		if(((Rook) (getPiece(new Position(7,from.getY())))).getHasMoved() == true)
				return false;
		if(board[from.getX()+1][from.getY()]!=null)
			return false;
		if(board[from.getX()+2][from.getY()]!=null)
			return false;
		return true;
		
	}
	
	private boolean checkCastleLeft(Position from){
		Piece hold = getPiece(from);
		if(((King)hold).getHasMoved()== true)
			return false;
		if(!(getPiece(new Position(0,from.getY()))instanceof Rook))
			return false;
		if(((Rook) (getPiece(new Position(0,from.getY())))).isWhite() != hold.isWhite())
				return false;
		if(((Rook) (getPiece(new Position(0,from.getY())))).getHasMoved() == true)
				return false;
		if(board[from.getX()-1][from.getY()]!=null)
			return false;
		if(board[from.getX()-2][from.getY()]!=null)
			return false;
		if(board[from.getX()-3][from.getY()]!=null)
			return false;
		return true;
	}
	
	private void castle(Position from, Position to){
		if(to.getX() == 6){
			move(from, to);
			move(new Position(7, from.getY()), new Position(5,from.getY()));
		}
		else if(to.getX() == 2){
			move(from, to);
			move(new Position(0, from.getY()), new Position(3,from.getY()));
		}
	}
	
	public void enPassant(Position from, Position to){
		
	}
	
	private boolean checkEnPassantR(Position pos){
		boolean iswhite = getPiece(pos).isWhite();
		if(board[pos.getX()+1][pos.getY()].isWhite()!= iswhite && board[pos.getX()+1][pos.getY()]!= null && getPiece(pos) instanceof Pawn){
			if(((Pawn)board[pos.getX()+1][pos.getY()]).gettwomove()== true/* && lastUpdate.get(1).equals(new Position(pos.getX()+1,pos.getY()))*/)
				return true;	
			}
		
		return false;		
	}
	private boolean checkEnPassant(Position pos, boolean rightSide){
		if(movesMade()==0)
			return false;
		int x = pos.getX();
		int y = pos.getY();
		if(x==0)
			return false;
		int side;
		if(rightSide)
			side = 1;
		else
			side = -1;
		boolean thisIsWhite = getPiece(pos).isWhite();
		int forward;
		if(thisIsWhite)
			forward = -2;
		else
			forward = 2;
		if((lastMove().changedPos(1).equals(new Position(x+side,y)))&&((lastMove().changedPos(0).equals(new Position(x+side,y+forward)))))
			return true;
		return false;
	}
	public void enpassant(Position from, Position to){
		move(from,to);
		board[to.getX()][to.getY()-1] = null;
	}
	
	public ArrayList<Position> getAllMoves(){
		return getAllMoves(isWhiteTurn);
	}
	private ArrayList<Position> getAllMoves(boolean whiteSide){
		ArrayList<Position> list = new ArrayList <Position>();
		for(int i = 0; i < board.length; i++){
			for(int x = 0; x < board[i].length; x++){
				if(board[i][x]!=null&&board[i][x].isWhite()==whiteSide){
					list.addAll(getMoves(new Position(i,x)));
				}
			}
		}
		return list;
	}
	/**
	 * Returns if the current moving side is in check
	 * @return true if in check, false otherwise
	 */
	public boolean isChecked(){
		return isChecked(isWhiteTurn);
	}
	
	/**
	 * Returns if the specified side is in check
	 * @return true if in check, false otherwise
	 */
	public boolean isChecked(boolean whiteSide){
		return isUnderAttack(getKingPosition(whiteSide));
	}
	
	public boolean isUnderAttack(Position pos){
		if(getPiece(pos)==null)
				throw new IllegalArgumentException("No Piece there");
		boolean thisIsWhite = getPiece(pos).isWhite();
		ArrayList<Position> enemyMoves = getAllMovesWithoutCheck(!thisIsWhite);
		for(Position move: enemyMoves)
			if(move.equals(pos))
				return true;
		return false;
	}
	
	/**
	 * Returns a list of the positions of the pieces of one side
	 * @param whiteSide which side's pieces to get
	 * @return an ArrayList of the positions of the pieces
	 */
	public ArrayList<Position> piecePositions(boolean whiteSide){
		ArrayList<Position> piecePos = new ArrayList<Position>();
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(board[x][y]!=null&&board[x][y].isWhite()==whiteSide)
					piecePos.add(new Position(x,y));
		return piecePos;
	}
	
	private Position getKingPosition(boolean whiteSide){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(board[x][y]!=null&&(board[x][y] instanceof King)&&board[x][y].isWhite()==whiteSide)
					return new Position(x,y);
		throw new IllegalArgumentException("No King of that color");
	}
}
