package GUI;

import BoardMovement.Piece;
public abstract class Board 
{
    /** The internal board array. */
    private Piece[][] board;
    /** The size of this game board. */
    private int boardWidth, boardHeight;
    
    public final void clear() {
    	board = new Piece[boardWidth][boardHeight];
    }
    public final int getHeight() {
        return boardHeight;
    }
    public final int getWidth() {
        return boardWidth;
    }
}
