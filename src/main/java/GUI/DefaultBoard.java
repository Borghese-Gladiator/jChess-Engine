package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;

import BoardMovement.Position;
public class DefaultBoard 
{
    /** The internal board array. */
    private Image[][] board;
    /** The size of this game board. */
    private int boardWidth, boardHeight;
    public DefaultBoard()
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
		final int WHITEPawnRow = 6;
    	final int BLACKPawnRow = 1;
    	for (int x = 0; x < 8; x++) 
    	{
    		Position pos = new Position(WHITEPawnRow, x);
	    	setPiece(pos, getTile("Pawn-WHITE"));
	    	pos.setX(BLACKPawnRow);
	    	setPiece(pos, getTile("Pawn-BLACK"));
    	}
    	//BLACK Backrow
    	setPiece(new Position(0, 0), getTile("Rook-BLACK"));
    	setPiece(new Position(0, 1), getTile("Knight-BLACK"));
    	setPiece(new Position(0, 2), getTile("Bishop-BLACK"));
    	setPiece(new Position(0, 3), getTile("Queen-BLACK"));
    	setPiece(new Position(0, 4), getTile("King-BLACK"));
    	setPiece(new Position(0, 5), getTile("Bishop-BLACK"));
    	setPiece(new Position(0, 6), getTile("Knight-BLACK"));
    	setPiece(new Position(0, 7), getTile("Rook-BLACK"));
    	//WHITE Backrow                                
    	setPiece(new Position(7, 0), getTile("Rook-WHITE"));
    	setPiece(new Position(7, 1), getTile("Knight-WHITE"));
    	setPiece(new Position(7, 2), getTile("Bishop-WHITE"));
    	setPiece(new Position(7, 3), getTile("Queen-WHITE"));
    	setPiece(new Position(7, 4), getTile("King-WHITE"));
    	setPiece(new Position(7, 5), getTile("Bishop-WHITE"));
    	setPiece(new Position(7, 6), getTile("Knight-WHITE"));
    	setPiece(new Position(7, 7), getTile("Rook-WHITE"));
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
