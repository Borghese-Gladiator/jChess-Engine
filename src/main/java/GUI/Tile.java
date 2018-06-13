package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import BoardMovement.Piece;
import BoardMovement.Position;

public class Tile extends JButton implements MouseListener
{
	private final static int TILE_HEIGHT_AND_WIDTH = 64;
    static final Color DARK = new Color(0xD1, 0x8B, 0x47);

    static final Color LIGHT = new Color(0xFF, 0xCE, 0x9E);

    ChessFrame parent;
    Position coords;
	Piece thePiece;
	ImageIcon img;
	boolean hasPiece;
	boolean isLegalMove;
	public Tile(ChessFrame parent, Position pos, Image img)
	{
		super(setIcon(img));
		this.parent = parent;
		isLegalMove = false;
        if (((pos.getY()+ pos.getX()) % 2 == 0)) 
        {
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
	
	public void makeMove(Position pos1, Position pos2)
    {
    }
	public void showMoves(Position pos)
	{
		
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
	}/*
	 public final AffineTransform getTransform() {
	        AffineTransform at = new AffineTransform();
	        at.scale(getWidth() / (TILE_SIZE * board.getWidth()),
	                 getHeight() / (TILE_SIZE * board.getHeight()));
	        return at;
	    }
	/**
     * Standard painting method.
     *
     * @param graphics the drawing surface
     
    public final void paintComponent(final Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        int h = board.getHeight();
        int w = board.getWidth();
        g.transform(getTransform());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                           RenderingHints.VALUE_STROKE_PURE);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                           RenderingHints.VALUE_RENDER_QUALITY);

        /* Temp AffineTransform for the method 
        AffineTransform at = new AffineTransform();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if ((x + y) % 2 == 0) {
                    g.setColor(LIGHT);
                } else {
                    g.setColor(DARK);
                }
                at.setToTranslation(x * TILE_SIZE, y * TILE_SIZE);
                g.fill(at.createTransformedShape(TILE));
            }
        }
        */
     /*   /* Place the pieces 
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Piece p = board.getPiece(new Position(x, y));
                if (p != null) {
                    Image tile = p.getImage();
                    int yy = y;
                    if (flipped) {
                        yy = board.getHeight() - 1 - y;
                    }
                    at.setToTranslation(x * TILE_SIZE, yy * TILE_SIZE);
                    g.drawImage(tile, at, null);
                }
            }
        }

        /* Draw last move 
        Move last = board.last();
        if (last != null) {
            g.setColor(LAST);
            highlight(g, last.getOrigin());
            highlight(g, last.getDest());
        }

        /* Draw selected square 
        if (selected != null) {
            g.setColor(SELECTED);
            highlight(g, selected);

            /* Draw piece moves 
            if (moves != null) {
                g.setColor(MOVEMENT);
                for (Move move : moves) {
                    highlight(g, move.getDest());
                }
            }
        }/*/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (isLegalMove)
		showMoves(coords);
		//ArrayList<Position> moves = .getMoves(pos1);
    		//for (Position i: moves)
    		{
    			//showSquare(i);
    		}
    		//parent.set the tile as legal move(true); --and green dot? 
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
	public boolean hasPiece() {
		return hasPiece;
	}
	public void setHasPiece(boolean hasPiece) {
		this.hasPiece = hasPiece;
	}
    }

   
/*
    /** The current interaction mode. 
    private Mode mode = Mode.WAIT;

    /** Current player making a move, when interactive. 
    private Piece.Side side;

    /** Latch to hold down the Game thread while the user makes a selection. 
    private CountDownLatch latch;

    /** The move selected by the player. 
    private Move selectedMove;*/
