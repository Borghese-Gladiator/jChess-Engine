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

import BoardMovement.ChessBoard;
import BoardMovement.Position;
public class BoardPanel extends JPanel
{
	
	private boolean flipped = true;
	private Position selected = null;
	

	
	private Board board;
	public BoardPanel() {
      //  chessBoard = new JPanel(new GridLayout(0, 9));
      //  chessBoard.setBorder(new LineBorder(Color.BLACK));
        
        
    }
	public void setBoard(ChessBoard aBoard)
	{
		board = aBoard;
		repaint();
	}
}
