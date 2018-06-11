package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
     */
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

        /* Temp AffineTransform for the method */
        AffineTransform at = new AffineTransform();

        /* Draw the background */
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
    }

    /** This class's Logger. */
    private static final Logger LOG =
        Logger.getLogger("com.nullprogram.chess.gui.BoardPanel");

    /** Size of a tile in working coordinates. */
    private static final double TILE_SIZE = 200.0;

    /** Shape provided for drawing background tiles. */
    private static final Shape TILE =
        new Rectangle2D.Double(0, 0, TILE_SIZE, TILE_SIZE);

    /** Padding between the highlight and tile border. */
    static final int HIGHLIGHT_PADDING = 15;

    /** Thickness of highlighting. */
    static final Stroke HIGHLIGHT_STROKE = new BasicStroke(12);

    /** Shape for drawing the highlights. */
    private static final Shape HIGHLIGHT =
        new RoundRectangle2D.Double(HIGHLIGHT_PADDING, HIGHLIGHT_PADDING,
                                    TILE_SIZE - HIGHLIGHT_PADDING * 2,
                                    TILE_SIZE - HIGHLIGHT_PADDING * 2,
                                    HIGHLIGHT_PADDING * 4,
                                    HIGHLIGHT_PADDING * 4);

    /** Version for object serialization. */
    private static final long serialVersionUID = 1L;

    /** The board being displayed. */
    private Board board;

    /** Indicate flipped status. */
    private boolean flipped = true;

    /** The currently selected tile. */
    private Position selected = null;
/*
    /** The list of moves for the selected tile.
    private MoveList moves = null;*/

    /** The color for the dark tiles on the board. */
    static final Color DARK = new Color(0xD1, 0x8B, 0x47);

    /** The color for the light tiles on the board. */
    static final Color LIGHT = new Color(0xFF, 0xCE, 0x9E);

    /** Border color for a selected tile. */
    static final Color SELECTED = new Color(0x00, 0xFF, 0xFF);

    /** Border color for a highlighted movement tile. */
    static final Color MOVEMENT = new Color(0x7F, 0x00, 0x00);

    /** Last move highlight color. */
    static final Color LAST = new Color(0x00, 0x7F, 0xFF);

    /** Minimum size of a tile, in pixels. */
    static final int MIN_SIZE = 25;

    /** Preferred size of a tile, in pixels. */
    static final int PREF_SIZE = 75;
/*
    /** The current interaction mode. 
    private Mode mode = Mode.WAIT;

    /** Current player making a move, when interactive. 
    private Piece.Side side;

    /** Latch to hold down the Game thread while the user makes a selection. 
    private CountDownLatch latch;

    /** The move selected by the player. 
    private Move selectedMove;*/

}
