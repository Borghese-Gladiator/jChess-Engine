package GUI;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.nullprogram.chess.Board;

import BoardMovement.ChessBoard;
import BoardMovement.Position;
public class BoardPanel extends JPanel
{
	private static boolean whiteStart = true;
	private static boolean onePlayer = true;
	
	private boolean flipped = true;
	private Position selected = null;
	

	private static final double TILE_SIZE = 200.0;
	private static final Shape TILE =
	        new Rectangle2D.Double(0, 0, TILE_SIZE, TILE_SIZE);
	
    private JButton[][] chessBoardSquares = new JButton[8][8];
	private Board board;
	public BoardPanel() {
        board = new ChessBoard(whiteStart);
      //  chessBoard = new JPanel(new GridLayout(0, 9));
      //  chessBoard.setBorder(new LineBorder(Color.BLACK));
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1)
                        //) {
                        || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[j][i] = b;
            }
        }
    }
	private Position getPixelPosition(final Point2D p) {
        Point2D pout = null;
        try {
            pout = getTransform().inverseTransform(p, null);
        } catch (java.awt.geom.NoninvertibleTransformException t) {
            /* This will never happen. */
            return null;
        }
        int x = (int) (pout.getX() / TILE_SIZE);
        int y = (int) (pout.getY() / TILE_SIZE);
        if (flipped) {
            y = board.getHeight() - 1 - y;
        }
        return new Position(x, y);
    }
	public final void setBoard(final Board b) {
        board = b;
        updateSize();
        repaint();
    }
	public static void setWhiteStart(boolean whiteStart) {
		BoardPanel.whiteStart = whiteStart;
	}
	public static void setOnePlayer(boolean onePlayer) {
		BoardPanel.onePlayer = onePlayer;
	}
	public void setBoard(ChessBoard aBoard)
	{
		board = aBoard;
	}
}
