package GUI;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.nullprogram.chess.pieces.ImageServer;

import BoardMovement.*;
public class Board 
{
    /** The internal board array. */
    private Piece[][] board;
    /** The size of this game board. */
    private int boardWidth, boardHeight;
    /** The standard board width. */
    static final int WIDTH = 8;

    /** The standard board height. */
    static final int HEIGHT = 8;

    /** Row of the white pawns. */
    static final int WHITE_PAWN_ROW = 1;

    /** Row of the black pawns. */
    static final int BLACK_PAWN_ROW = 6;

    /** White home row. */
    static final int WHITE_ROW = 0;

    /** Black home row. */
    static final int BLACK_ROW = 7;

    /** Queen side rook column. */
    static final int Q_ROOK = 0;

    /** Queen side knight column. */
    static final int Q_KNIGHT = 1;

    /** Queen side bishop column. */
    static final int Q_BISHOP = 2;

    /** Queen column. */
    static final int QUEEN = 3;

    /** King column. */
    static final int KING = 4;

    /** King side bishop column. */
    static final int K_BISHOP = 5;

    /** King side knight column. */
    static final int K_KNIGHT = 6;

    /** King side rook column. */
    static final int K_ROOK = 7;
    
    private static final Logger LOG =
            Logger.getLogger("com.nullprogram.chess.pieces.ImageServer");

        /** The image cache. */
        private static Map<String, Image> cache =
            new HashMap<String, Image>();
    public final void clear() {
    	board = new Piece[boardWidth][boardHeight];
    	 setWidth(WIDTH);
         setHeight(HEIGHT);
         clear();
         for (int x = 0; x < WIDTH; x++) {
             setPiece(x, WHITE_PAWN_ROW, new Pawn(Piece.Side.WHITE));
             setPiece(x, BLACK_PAWN_ROW, new Pawn(Piece.Side.BLACK));
         }
         setPiece(Q_ROOK, WHITE_ROW, new Rook(Piece.Side.WHITE));
         setPiece(K_ROOK, WHITE_ROW, new Rook(Piece.Side.WHITE));
         setPiece(Q_ROOK, BLACK_ROW, new Rook(Piece.Side.BLACK));
         setPiece(K_ROOK, BLACK_ROW, new Rook(Piece.Side.BLACK));
         setPiece(Q_KNIGHT, WHITE_ROW, new Knight(Piece.Side.WHITE));
         setPiece(K_KNIGHT, WHITE_ROW, new Knight(Piece.Side.WHITE));
         setPiece(Q_KNIGHT, BLACK_ROW, new Knight(Piece.Side.BLACK));
         setPiece(K_KNIGHT, BLACK_ROW, new Knight(Piece.Side.BLACK));
         setPiece(Q_BISHOP, WHITE_ROW, new Bishop(Piece.Side.WHITE));
         setPiece(K_BISHOP, WHITE_ROW, new Bishop(Piece.Side.WHITE));
         setPiece(Q_BISHOP, BLACK_ROW, new Bishop(Piece.Side.BLACK));
         setPiece(K_BISHOP, BLACK_ROW, new Bishop(Piece.Side.BLACK));
         setPiece(QUEEN, WHITE_ROW, new Queen(Piece.Side.WHITE));
         setPiece(QUEEN, BLACK_ROW, new Queen(Piece.Side.BLACK));
         setPiece(KING, WHITE_ROW, new King(Piece.Side.WHITE));
         setPiece(KING, BLACK_ROW, new King(Piece.Side.BLACK));
    }
    public static Image getTile(final String name) {
        Image cached = cache.get(name);
        if (cached != null) {
            return cached;
        }

        String file = name + ".png";
        try {
            Image i = ImageIO.read(ImageServer.class.getResource(file));
            cache.put(name, i);
            return i;
        } catch (java.io.IOException e) {
            String message = "Failed to read image: " + file + ": " + e;
            LOG.severe(message);
            System.exit(1);
        } catch (IllegalArgumentException e) {
            String message = "Failed to find image: " + file + ": " + e;
            LOG.severe(message);
            System.exit(1);
        }
        return null;
    }
    public final int getHeight() {
        return boardHeight;
    }
    public final int getWidth() {
        return boardWidth;
    }
}
