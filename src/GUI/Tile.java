package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sun.glass.events.MouseEvent;

import BoardMovement.Piece;
import BoardMovement.Position;

public class Tile extends JButton
{
	private final static int TILE_HEIGHT_AND_WIDTH = 64;
	Position coords;
	Piece thePiece;
	ImageIcon img;
	boolean hasPiece;
	public Tile(Position pos, Image img)
	{
		super(setIcon(img));
        if ((pos.getY() % 2 == 1 && pos.getX() % 2 == 1)
                //) {
                || (pos.getY() % 2 == 0 && pos.getX() % 2 == 0)) {
            setBackground(Color.WHITE);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.BLACK);
            setForeground(Color.BLACK);
        }
		
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
		hasPiece = false;
		img = null;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) 
	{
		if (hasPiece == true)
		{
			remove();
			//sound of piece being taken
		}
		this.img = img;
	}
}
