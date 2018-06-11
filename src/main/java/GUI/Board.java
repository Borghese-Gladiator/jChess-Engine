package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;

import BoardMovement.Position;
import Images.ImageDatabase;
public class Board 
{
    /** The internal board array. */
    private Image[][] board;
    /** The size of this game board. */
    private int boardWidth, boardHeight;
    public Board()
    {
    	board = new BufferedImage[8][8];
    	for (int i = 0; i < board.length; i++)
    	{
    		for (int j = 0; j < board[i].length; j++)
    		{
    			board[i][j] = null;
    		}
    	}
    	//Initialize pawns
		final int whitePawnRow = 6;
    	final int blackPawnRow = 1;
    	for (int x = 0; x < 8; x++) 
    	{
    		Position pos = new Position(whitePawnRow, x);
	    	setPiece(pos, getTile("Pawn-WHITE"));
	    	pos.setX(blackPawnRow);
	    	setPiece(pos, getTile("Pawn-BLACK"));
    	}
    	//Black Backrow
    	setPiece(new Position(0, 0), getTile("Rook-Black"));
    	setPiece(new Position(0, 1), getTile("Knight-Black"));
    	setPiece(new Position(0, 2), getTile("Bishop-Black"));
    	setPiece(new Position(0, 3), getTile("Queen-Black"));
    	setPiece(new Position(0, 4), getTile("King-Black"));
    	setPiece(new Position(0, 5), getTile("Bishop-Black"));
    	setPiece(new Position(0, 6), getTile("Knight-Black"));
    	setPiece(new Position(0, 7), getTile("Rook-Black"));
    	//White Backrow                                
    	setPiece(new Position(7, 0), getTile("Rook-White"));
    	setPiece(new Position(7, 1), getTile("Knight-White"));
    	setPiece(new Position(7, 2), getTile("Bishop-White"));
    	setPiece(new Position(7, 3), getTile("Queen-White"));
    	setPiece(new Position(7, 4), getTile("King-White"));
    	setPiece(new Position(7, 5), getTile("Bishop-White"));
    	setPiece(new Position(7, 6), getTile("Knight-White"));
    	setPiece(new Position(7, 7), getTile("Rook-White"));
    }
    private void setPiece(Position spot, Image pic)
    {
    	board[spot.getX()][spot.getY()] = pic;
    }
    public static Image getTile(final String name) {
        return ImageDatabase.getTile(name);
    }
    public final int getHeight() {
        return boardHeight;
    }
    public final int getWidth() {
        return boardWidth;
    }
	public Image getBoard(Position pos) {
		return board[pos.getX()][pos.getY()];
	}
	public void setBoard(Image[][] board) {
		this.board = board;
	}
}
