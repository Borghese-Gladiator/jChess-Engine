package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import BoardMovement.ChessBoard;
import BoardMovement.Position;

public class Tile extends JButton implements MouseListener
{
	private final static int TILE_HEIGHT_AND_WIDTH = 64;
    static final Color DARK = new Color(0xD1, 0x8B, 0x47);

    static final Color LIGHT = new Color(0xFF, 0xCE, 0x9E);

    static Position origin;
    static Tile[][] boardParent;
    static ChessBoard gameParent;
    Position coords;
	ImageIcon img;
	boolean isLegalMove;
	public Tile(Position origin, Tile[][] boardParent, ChessBoard gameParent, Position pos, Image img)
	{
		super(setIcon(img));
		coords = pos;
		this.origin = origin;
		this.boardParent = boardParent;
		this.gameParent = gameParent;
		isLegalMove = false;
        setBackground();
        MouseListener mouseListener = new MouseAdapter()
		{
			@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if (isLegalMove)
        		{
        			//move();
        			isLegalMove = false;
        			setBackground();
        		}
        		else
        		{
        			ArrayList<Position> moves = gameParent.getMoves(coords);
        			Tile.setOrigin(coords);
        			for (Position i: moves)
        			{
        				Tile temp = boardParent[i.getX()][i.getY()];
        				temp.setLegalMove(true);
        				temp.setBackground(Color.ORANGE);
        			}
        		}
            		//parent.set the tile as legal move(true); --and green dot? 
        	}
	    	public void mousePresssed(MouseEvent mouseEvent)
	    	{
	    		System.out.println("Something");
	    	}
		};
        addMouseListener(mouseListener);
		
		/*this.addMouseListener(new MouseAdapter()
		{
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Clicked();
                } 
                else if(e.getButton() == MouseEvent.BUTTON3) {
                    Mark();
                }
            }
		}*/
	}
	public static ImageIcon setIcon(Image img)
	{
		Image newimg = img.getScaledInstance(TILE_HEIGHT_AND_WIDTH, TILE_HEIGHT_AND_WIDTH,  java.awt.Image.SCALE_SMOOTH ) ; 
        ImageIcon icon = new ImageIcon(newimg);
        return icon;
	}
	public void remove()
	{
		img = null;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) 
	{
		remove();
		//sound of piece being taken
		this.img = img;
	}
	private void setBackground()
	{
		if (((coords.getY()+ coords.getX()) % 2 == 0)) 
        {
            setBackground(Color.WHITE);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.BLACK);
            setForeground(Color.BLACK);
        }
	}
	public void move(Position newPos)
    {
		coords = newPos;
    }
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*public boolean hasPiece() {
		return hasPiece;
	}
	public void setHasPiece(boolean hasPiece) {
		this.hasPiece = hasPiece;
	}*/
	public boolean isLegalMove() {
		return isLegalMove;
	}
	public void setLegalMove(boolean isLegalMove) {
		this.isLegalMove = isLegalMove;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static Position getOrigin() {
		return origin;
	}
	public static void setOrigin(Position origin) {
		Tile.origin = origin;
	}
}