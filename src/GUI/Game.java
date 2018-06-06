package GUI;

import java.util.logging.Logger;

import BoardMovement.Piece;
import BoardMovement.Position;

/**
 * Drives a game of chess, given players and a board.
 *
 * This will, in turn, inform players of their turn and wait for them
 * to respond with a move.
 */
public class Game implements Runnable {

    /** This class's Logger. */
    private static final Logger LOG =
        Logger.getLogger("com.nullprogram.chess.Game");

    /** Conversion from milliseconds to seconds. */
    private static final double MSEC_TO_SEC = 1000.0;

    /** Whose turn it is right now. */
    private 
    
    /** The board being used for this game. */
    private Board board;

    /** Weighting of old value in timing estimates. */
    private static final double ALPHA = 0.4;

    /** Set to true when the board is in a completed state. */
    private volatile Boolean done = false;

    /**
     * Create a new game with the given board and players.
     *
     * @param gameBoard   the game board
     */
    public Game(final Board gameBoard) {
        board = gameBoard;
    }
    @Override
    public final void run() {
    }
}
