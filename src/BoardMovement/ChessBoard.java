package BoardMovement;
import java.util.ArrayList;

public class ChessBoard {
	private Piece[][] board;
	private boolean isWhiteTurn;
	private ArrayList<Position> lastUpdate;
	
	public ChessBoard(){
		board = new Piece[8][8];
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
	
	/**
	 * Returns the board
	 * @return the 2D array with the pieces. null where there isn't a piece.
	 */
	public Piece[][] getBoard(){
		return board;
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
	 * Moves a piece
	 * @param posFrom The initial position of the piece
	 * @param posTo The position of the piece after the move
	 */
	public void move(Position posFrom, Position posTo){
		if(!isValidMove(posFrom, posTo))
			throw new IllegalArgumentException("Invalid move");
		if(/*condition for castling*/)
			castle(posFrom, posTo);
		else if(/*condition for en passant*/)
			enPassant(posFrom posTo);
		else{
			
		}
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
	public ArrayList<Position> getMoves(Position pos){
		if(board[pos.getX()][pos.getY()]==null)
			throw new IllegalArgumentException("No Piece at Position " + pos);
		switch(board[pos.getX()][pos.getY()].getType()){
		case 'k':
			return getMovesK(pos);
		case 'q':
			return getMovesQ(pos);
		case 'b':
			return getMovesB(pos);
		case 'n':
			return getMovesN(pos);
		case 'r':
			return getMovesR(pos);
		default://case 'p':
			return getMovesP(pos);
		}
	}

	public ArrayList<Position> getMovesK(Position pos){// capturing and check to see if the piece is and opposite color
		ArrayList<Position> list = new ArrayList <Position>();
		boolean iswhite = getPiece(pos).isWhite();
<<<<<<< HEAD
		try{
			if(board[pos.getX() +1][pos.getY()] == null || getPiece(new Position(pos.getX() +1,pos.getY())).isWhite() != iswhite)
				list.add(new Position(pos.getX() +1, pos.getY()));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX() -1][pos.getY()]== null|| getPiece(new Position(pos.getX() -1,pos.getY())).isWhite() != iswhite)
				list.add(new Position(pos.getX() -1,pos.getY()));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX()][pos.getY() +1] == null|| getPiece(new Position(pos.getX() ,pos.getY()+1)).isWhite() != iswhite)
				list.add(new Position(pos.getX(), pos.getY() +1));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX()][pos.getY()-1]== null|| getPiece(new Position(pos.getX(),pos.getY()-1)).isWhite() != iswhite)
				list.add(new Position(pos.getX(),pos.getX()-1));
		}catch(ArrayIndexOutOfBoundsException e){}
		if(Checkcastleright(pos) == true)
=======
		if(board[pos.getX() +1][pos.getY()] == null || getPiece(new Position(pos.getX() +1,pos.getY())).isWhite() != iswhite)
			list.add(new Position(pos.getX() +1, pos.getY()));
		if(board[pos.getX() -1][pos.getY()]== null)
			list.add(new Position(pos.getX() -1,pos.getY()));
		if(board[pos.getX()][pos.getY() +1] == null)
			list.add(new Position(pos.getX(), pos.getY() +1));
		if(board[pos.getX()][pos.getY()-1]== null)
			list.add(new Position(pos.getX(),pos.getX()-1));
		if(checkCastleRight(pos) == true)
>>>>>>> 198fc417ca14a4925920bcd7504125b5e37a80bf
			list.add(new Position(pos.getX()+2,pos.getY()));
		if(checkCastleLeft(pos) ==true)
			list.add(new Position(pos.getX()-2,pos.getY()));
		return list;
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
		ArrayList<Position> list = new ArrayList <Position>();
		boolean iswhite = getPiece(pos).isWhite();
		try{
			if(board[pos.getX()+2][pos.getY()+2] == null || board[pos.getX()+2][pos.getY()+2].isWhite()!=iswhite)
				list.add(new Position(pos.getX()+2,pos.getY()+2));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX()+2][pos.getY()-2] == null||board[pos.getX()+2][pos.getY()-2].isWhite()!=iswhite)
				list.add(new Position(pos.getX()+2,pos.getY()-2));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX()-2][pos.getY()+2] == null||board[pos.getX()-2][pos.getY()+2].isWhite()!=iswhite)
				list.add(new Position(pos.getX()-2,pos.getY()+2));
		}catch(ArrayIndexOutOfBoundsException e){}
		try{
			if(board[pos.getX()-2][pos.getY()-2] == null||board[pos.getX()-2][pos.getY()-2].isWhite()!=iswhite)
				list.add(new Position(pos.getX()-2,pos.getY()-2));
		}catch(ArrayIndexOutOfBoundsException e){}
		return null;//Do code
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
		ArrayList<Position> list = new ArrayList <Position>();
		boolean iswhite = getPiece(pos).isWhite();
		if(board[pos.getX()][pos.getY()+1] == null )
			list.add(new Position(pos.getX(),pos.getY()+1));
		if(board[pos.getX()+1][pos.getY()+1].isWhite()!= iswhite)
			list.add(new Position(pos.getX()+1,pos.getY()+1));
		if(board[pos.getX()-1][pos.getY()-1].isWhite()!=iswhite)
			list.add(new Position(pos.getX()-1,pos.getY()-1));
		return null;//Do code
	}
	
	public boolean isBeingAttacked(boolean byWhite, Position pos){
		
	}
	private boolean checkCastleRight(Position from){
		Piece hold = getPiece(from);
		if(((King)hold).getHasMoved()== true)
			return false;
		if(!(getPiece(new Position(7,from.getY()))instanceof Rook))
			return false;
		if(((Rook) (getPiece(new Position(7,from.getY())))).getHasMoved() == true)
				return false;
		if(board[from.getX()+1][from.getY()]!=null)
			return false;
		if(board[from.getX()+2][from.getY()]==null)
			return true;
		return false;
		
	}
	private boolean checkCastleLeft(Position from){
		Piece hold = getPiece(from);
		if(((King)hold).getHasMoved()== true)
			return false;
		if(!(getPiece(new Position(0,from.getY()))instanceof Rook))
			return false;
		if(((Rook) (getPiece(new Position(0,from.getY())))).getHasMoved() == true)
				return false;
		if(board[from.getX()-1][from.getY()]!=null)
			return false;
		if(board[from.getX()-2][from.getY()]==null)
			return true;
		return false;
	}
	public void castle(Position from, Position to){
		if(to.getX() == 6){
			move(from, to);
			move(new Position(7, from.getY()), new Position(5,from.getY()));
		}
		else if(to.getX() == 2){
			move(from, to);
			move(new Position(0, from.getY()), new Position(3,from.getY()));
		}
	}
	private boolean checkenpassant(Position f){
		
	}
}
