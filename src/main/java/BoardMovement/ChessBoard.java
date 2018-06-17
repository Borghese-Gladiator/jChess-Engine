package BoardMovement;
import java.util.ArrayList;

public class ChessBoard{
	private Piece[][] board;
	private boolean isWhiteTurn;
	private ArrayList<Move> allMoves;
	
	public ChessBoard(){
		board = new Piece[8][8];
		allMoves = new ArrayList<Move>();
		isWhiteTurn = true;
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
	
	public int movesMade(){
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
	public boolean hasPiece(Position pos) {
		if (getPiece(pos) != null)
		{
			return true;
		}
		return false;
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
		if((getPiece(posFrom)instanceof King)&&(Math.abs(posFrom.getX()-posTo.getX())>1))
			castle(posFrom, posTo);
		else if((getPiece(posFrom)instanceof Pawn)&&(posFrom.getX()!=posTo.getX())&&(getPiece(posTo)==null))
			enPassant(posFrom, posTo);
		else{
			allMoves.add(new Move(posFrom,posTo,getPiece(posFrom),getPiece(posTo)));
			board[posTo.getX()][posTo.getY()] = getPiece(posFrom);
			board[posTo.getX()][posTo.getY()].setHasMoved(true);
			board[posFrom.getX()][posFrom.getY()] = null;
			if((getPiece(posTo)instanceof Pawn)&&((getPiece(posTo).isWhite()&&posTo.getY()==0)||(!getPiece(posTo).isWhite()&&posTo.getY()==7)))
				board[posTo.getX()][posTo.getY()] = new Queen(posTo.getY()==0);
		}
		isWhiteTurn = !isWhiteTurn;
	}
	
	private void castle(Position posFrom, Position posTo){
			allMoves.add(new Move(posFrom,posTo,getPiece(posFrom),getPiece(posTo)));
			board[posTo.getX()][posTo.getY()] = getPiece(posFrom);
			board[posTo.getX()][posTo.getY()].setHasMoved(true);
			board[posFrom.getX()][posFrom.getY()] = null;
		if(posTo.getX() == 6){
			lastMove().addSpot(board[7][posFrom.getY()], new Position(7,posFrom.getY()));
			lastMove().addSpot(null, new Position(5,posFrom.getY()));
			board[5][posTo.getY()] = getPiece(new Position(7,posFrom.getY()));
			board[5][posTo.getY()].setHasMoved(true);
			board[7][posFrom.getY()] = null;
		}
		else/*if(posTo.getX() == 2)*/{
			lastMove().addSpot(board[0][posFrom.getY()], new Position(0,posFrom.getY()));
			lastMove().addSpot(null, new Position(3,posFrom.getY()));
			board[3][posTo.getY()] = getPiece(new Position(0,posFrom.getY()));
			board[3][posTo.getY()].setHasMoved(true);
			board[0][posFrom.getY()] = null;
		}
	}
	
	private void enPassant(Position posFrom, Position posTo){
		allMoves.add(new Move(posFrom,posTo,getPiece(posFrom),null));
		lastMove().addSpot(board[posTo.getX()][posFrom.getY()], new Position(posTo.getX(),posFrom.getY()));
		board[posTo.getX()][posTo.getY()] = getPiece(posFrom);
		board[posTo.getX()][posTo.getY()].setHasMoved(true);
		board[posFrom.getX()][posFrom.getY()] = null;
		board[posTo.getX()][posFrom.getY()] = null;
	}
	
	/**
	 * Undoes last move
	 */
	public void undoMove(){
		Move lastMove = lastMove();
		for(int i = 0; i < lastMove.size(); i++){
			board[lastMove.changedPos(i).getX()][lastMove.changedPos(i).getY()] = lastMove.changedPiece(i);
			if(lastMove.changedPiece(i)!=null)
				lastMove.changedPiece(i).setHasMoved(lastMove.changedMoved(i));
		}
		allMoves.remove(allMoves.size()-1);
		isWhiteTurn = !isWhiteTurn;
	}
	
	/**
	 * Determines if a move is valid
	 * @param posFrom the position from which the piece will move
	 * @param posTo the position to which the piece will move
	 * @return Returns true if that is a valid move for that piece. False if invalid.
	 */
	private boolean isValidMove(Position posFrom, Position posTo){
		for(Position move: getMoves(posFrom,board))
			if(move.getX()==posTo.getX()&&move.getY()==posTo.getY())
				return true;
		return false;
	}
	
	/**
	 * Returns an ArrayList of all the valid positions the specified piece can move to
	 * @param pos The position of the piece
	 * @return an ArrayList with all valid positions the piece can move to
	 */
	public ArrayList<Position> getMoves(Position pos,Piece [][]board){
		if(board[pos.getX()][pos.getY()]==null)
			throw new IllegalArgumentException("No Piece at Position " + pos);
		
		ArrayList<Position> moves;
		switch(board[pos.getX()][pos.getY()].getType()){
			case 'k':moves = getMovesK(pos); break;
			case 'q':moves = getMovesQ(pos); break;
			case 'b':moves = getMovesB(pos); break;
			case 'n':moves = getMovesN(pos); break;
			case 'r':moves = getMovesR(pos); break;
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
	
	private boolean checkEnPassant(Position pos, boolean rightSide){
		if(movesMade()==0)
			return false;
		int x = pos.getX();
		int y = pos.getY();
		if((x==0&&!rightSide)||(x==7&&rightSide))
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
		{
			int x = pos.getX();
			int y = pos.getY();
			{
				int[] Xs = {1,2,2,1,-1,-2,-2,-1};
				int[] Ys = {2,1,-1,-2,-2,-1,1,2};
				for(int i = 0; i<8; i++){
					int newx = x+Xs[i];
					int newy = y+Ys[i];
					if(newx>=0&&newx<8&&newy>=0&&newy<8&&((board[newx][newy]!=null)&&(board[newx][newy].isWhite()!=thisIsWhite)&&(board[newx][newy]instanceof Knight)))
						return true;
				}
			}
			
			{
				int[] Xs = { 1, 1, 1, 0,-1,-1,-1, 0};
				int[] Ys = {-1, 0, 1, 1, 1, 0,-1,-1};
				for(int i = 0; i<8; i++){
					int newx = x+Xs[i];
					int newy = y+Ys[i];
					if(newx>=0&&newx<8&&newy>=0&&newy<8&&((board[newx][newy]!=null)&&(board[newx][newy].isWhite()!=thisIsWhite)&&(board[newx][newy]instanceof King)))
						return true;
				}
			}
			
			{
				int forward;
				if(thisIsWhite)
					forward = -1;
				else
					forward = 1;
				if(x+1>=0&&x+1<8&&y+forward>=0&&y+forward<8&&((board[x+1][y+forward]!=null)&&(board[x+1][y+forward].isWhite()!=thisIsWhite)&&(board[x+1][y+forward]instanceof Pawn)))
					return true;
				if(x-1>=0&&x-1<8&&y+forward>=0&&y+forward<8&&((board[x-1][y+forward]!=null)&&(board[x-1][y+forward].isWhite()!=thisIsWhite)&&(board[x-1][y+forward]instanceof Pawn)))
					return true;
			}
		}
		for(int x = pos.getX()+1, y = pos.getY(); x<8; x++){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Rook || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX()-1, y = pos.getY();x>=0; x--){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Rook || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX(), y = pos.getY()+1; y<8; y++){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Rook || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX(), y = pos.getY()-1;y>=0; y--){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Rook || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		
		for(int x = pos.getX()+1, y = pos.getY()+1;( x<8)&&( y<8);x++,y++){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Bishop || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX()-1, y = pos.getY()+1;(x>=0)&&( y<8);x--,y++){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Bishop || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX()-1, y = pos.getY()-1;(x>=0)&&(y>=0);x--,y--){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Bishop || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
		for(int x = pos.getX()+1, y = pos.getY()-1;( x<8)&&(y>=0);x++,y--){
			if(board[x][y]!=null){
				if(board[x][y].isWhite()!=thisIsWhite&&(board[x][y] instanceof Bishop || board[x][y] instanceof Queen))
					return true;
				break;
			}
		}
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
	
	/**
	 * Gets the position of the king of the given side
	 * @param whiteSide which side's king to get
	 * @return the position of the king
	 */
	private Position getKingPosition(boolean whiteSide){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(board[x][y]!=null&&(board[x][y] instanceof King)&&board[x][y].isWhite()==whiteSide)
					return new Position(x,y);
		throw new IllegalArgumentException("No King of that color");
	}
	
	/**
	 * Gets positions of all pieces of one side
	 * @param whiteSide the side
	 * @return an ArrayList of the positions of the pieces of the given side
	 */
	public ArrayList<Position> getAllPieces(boolean whiteSide){
		ArrayList<Position> positions = new ArrayList<Position>();
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(board[x][y]!=null&&board[x][y].isWhite()==whiteSide)
					positions.add(new Position(x,y));
		return positions;
	}
	
	/**
	 * Determines if the side that is currently going is checkmated
	 * @return true if checkmated
	 */
	public boolean isCheckMated(){
		return hasNoMoves()&&isChecked();
	}
	
	public boolean isStalemated(){
		return hasNoMoves()&&!isChecked();
	}
	
	private boolean hasNoMoves(){
		ArrayList<Position> piecePos = getAllPieces(isWhiteTurn);
		ArrayList<Position> movePos = new ArrayList<Position>();
		for(Position pos: piecePos){
			ArrayList<Position> temp = getMoves(pos,board);
			movePos.addAll(temp);
		}
		return movePos.size()==0;
	}
	
	public String toString(){
		String str = "yx 0  1  2  3  4  5  6  7  \n";
		for(int y = 0; y<8; y++){
			str += y + "  ";
			for(int x = 0; x<8; x++){
				if(board[x][y]==null)
					str += "[] ";
				else{
					str += board[x][y] + " ";
				}
			}
			str += "\n";
		}
		return str;
	}
}
