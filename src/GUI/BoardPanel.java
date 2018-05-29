package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import BoardMovement.ChessBoard;
import BreezySwing.GBPanel;
public class BoardPanel extends GBPanel
{
	private static boolean whiteStart = true;
	private static boolean onePlayer = true;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
	private ChessBoard board;
	public BoardPanel() {
        board = new ChessBoard(whiteStart);
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
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
	private void updateSize() 
	{
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
