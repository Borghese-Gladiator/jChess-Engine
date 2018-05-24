package GUI;
import java.awt.Dimension;

import BoardMovement.ChessBoard;
import BreezySwing.GBPanel;
public class BoardPanel extends GBFrame
{
	private ChessBoard board;
	//Currently selected tile 
	private Position selected = null;
	 /** The move selected by the player. */
    private Move selectedMove;
	public BoardPanel(final ChessBoard displayBoard) {
        board = displayBoard;
        
        updateSize();
    }
	private void updateSize() {
		setSize(490, 490);
        setPreferredSize(new Dimension(PREF_SIZE * board.getWidth(),
                                       PREF_SIZE * board.getHeight()));
        setMinimumSize(new Dimension(MIN_SIZE * board.getWidth(),
                                     MIN_SIZE * board.getHeight()));
    }
}
