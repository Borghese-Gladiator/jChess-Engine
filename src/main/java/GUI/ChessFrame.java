package GUI; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import BoardMovement.ChessBoard;
import BoardMovement.Move;
import BoardMovement.Position;

public class ChessFrame extends JFrame
{
	private static boolean whiteStart = true;
	private static boolean onePlayer = true;
	static ArrayList<Tile> white = new ArrayList<Tile>();
    static ArrayList<Tile> black = new ArrayList<Tile>();
    static ArrayList<Tile> legalMoves = new ArrayList<Tile>();
	private final JPanel display;
    public Tile[][] boardTiles = new Tile[8][8];
    static Position origin;
    public static Position getOrigin() {
		return origin;
	}
	public static void setOrigin(Position origin) {
		ChessFrame.origin = origin;
	}
    static boolean whiteTurn = true;
	private ChessBoard game;
	public void setTileAsAMove(Position pos)
	{
		boardTiles[pos.getX()][pos.getY()].setLegalMove(true);
	}
	public ChessFrame()
	{
		super();
		
		DefaultBoard images = new DefaultBoard();
        display = new JPanel();
        origin = null;
        
        MenuHandler handler = new MenuHandler(this);
        handler.setUpMenu();
        game = new ChessBoard(whiteStart);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        pack();
        addTiles(images);
        disableBtns();
        enableBtns(whiteTurn);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Play Chess");
		setSize(900, 900);
		setVisible(true);
	}
    private void addTiles(DefaultBoard images)
    {
    	for (int i = 0; i < boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[i].length; j++) {
            	if (!(i > 1 && i < 6))
            	{
            		Image img = images.getBoard(new Position(i, j));  
                    Tile b = new Tile(this, new Position(j,i), img);
            		addTileToEnableList(b, game.getPiece(new Position(j, i)).isWhite());
                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    boardTiles[j][i] = b;
                    //b.setIcon(new ImageIcon(ImageDatabase.getTile("Pawn-WHITE")));
                    display.add(b);
            	}
            	else
            	{
            		Tile b = new Tile(this,
            				new Position(j, i), (new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                    boardTiles[j][i] = b;
            		display.add(b);
            	}
            }
        }
    }
    private void enableBtns(boolean isWhite)
	{
		if (isWhite)
		{
			for(Tile i: white)
			{
				i.setEnabled(true);
			}
		}
		else
		{
			for(Tile i: black)
			{
				i.setEnabled(true);
			}
		}
	}
	private void disableBtns()
	{
		for (Tile i: white)
		{
			i.setEnabled(false);
		}
		for (Tile i: black)
		{
			i.setEnabled(false);
		}
	}
	private void switchTurns()
	{
		if (whiteTurn)
		{
			whiteTurn = false;
			setTitle("BLack's turn");
			
		}
		else
		{
			whiteTurn = true;
			setTitle("White's turn");
		}
	}
	private void addTileToEnableList(Tile aTile, boolean isWhite)
	{
		if (isWhite)
		{
			white.add(aTile);
			if (origin != null)
			{
				white.remove(boardTiles[origin.getX()][origin.getY()]); //remove old pos
			}
		}
		else
		{
			black.add(aTile);
			if (origin != null)
			{
				black.remove(boardTiles[origin.getX()][origin.getY()]);
			}
		}
	}
	private void addNewPosToEnableList(Tile aTile)
	{
		if (whiteTurn)
		{
			white.add(aTile);
			white.remove(boardTiles[origin.getX()][origin.getY()]);
		}
		else
		{
			black.add(aTile);
			black.remove(boardTiles[origin.getX()][origin.getY()]);
		}
	}
	private void disableBtnIfCapture(Position posTo) {
		if (!whiteTurn){ //capture opposing, remove
			for (Tile i: white){
				if (posTo.equals(i.getCoords())){
					white.remove(i);
					break;
				}
			}
		}
		else{
			for (Tile i: black){
				if (posTo.equals(i.getCoords()))
				{
					black.remove(i);
					break;
				}
			}
		}
	}
	private void clearLegalMoves()
	{
		for (Tile i: legalMoves)
		{
			i.setBackground();
			i.setLegalMove(false);
		}
		legalMoves.clear();
	}
    public void move(Position posTo)
    {
    	
		//move();
		//Move x = new Move(origin, posTo, game.getPiece(origin), game.getPiece(posTo));
		clearLegalMoves();
		if (!(posTo.equals(origin)))
		{
			Tile temp = boardTiles[posTo.getX()][posTo.getY()];
			temp.setIcon(boardTiles[origin.getX()][origin.getY()].makeImgIcon());
			temp.setImg(boardTiles[origin.getX()][origin.getY()].getImg());
			boardTiles[origin.getX()][origin.getY()].removeIcon();
			disableBtnIfCapture(posTo);
			addNewPosToEnableList(temp);
			disableBtns();
			
			game.move(origin, posTo);
			switchTurns();
			enableBtns(whiteTurn);
			//if (game.isCheckMate()), VictoryDialog vd = new VictoryDialog(); Disable all pieces
		}
		origin = null;
    }
    public void showMoves(Position pos)
    {
    	clearLegalMoves();
    	ArrayList<Position> moves = game.getMoves(pos);
		setOrigin(pos);
		for (Position i: moves)
		{
			Tile temp = boardTiles[i.getX()][i.getY()];
			temp.setLegalMove(true);
			temp.setBackground(Color.ORANGE);
			legalMoves.add(temp);
		}
    }
	public final void intro()
	{
		Intro intro = new Intro(this);
		intro.setVisible(true);
		setSize(getPreferredSize());
	}
	public final void newGame() {
        NewGame ngFrame = new NewGame(this);
        ngFrame.setVisible(true);
        if (ngFrame == null) {
            return;
        }
    }
	private class MenuHandler implements ActionListener {

        /** The "Game" menu. */
        private JMenu menu;

        /** The parent chess frame, for callbacks. */
        private final ChessFrame frame;

        /**
         * Create the menu handler.
         *
         * @param parent parent frame
         */
        public MenuHandler(final ChessFrame parent) {
            frame = parent;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if ("New Game".equals(e.getActionCommand())) {
                frame.newGame();
            }
            else if ("How to Play".equals(e.getActionCommand()))
            {
            	frame.intro();
            }
    		else if ("Exit".equals(e.getActionCommand())) {
                System.exit(0);
            }
        }

        /**
         * Set up the menu bar.
         */
        public final void setUpMenu() {
            JMenuBar menuBar = new JMenuBar();

            menu = new JMenu("Game");
            menu.setMnemonic('G');
            JMenuItem newGame = new JMenuItem("New Game");
            newGame.addActionListener(this);
            newGame.setMnemonic('N');
            menu.add(newGame);
            menu.add(new JSeparator());
            JMenuItem howToPlay = new JMenuItem("How to Play");
            howToPlay.addActionListener(this);
            howToPlay.setMnemonic('h');
            menu.add(howToPlay);
            menu.add(new JSeparator());
            JMenuItem exitGame = new JMenuItem("Exit");
            exitGame.addActionListener(this);
            exitGame.setMnemonic('x');
            menu.add(exitGame);
            menuBar.add(menu);
            setJMenuBar(menuBar);
        }
    }
	public ChessBoard getGame() {
		return game;
	}
	public void setGame(ChessBoard game) {
		this.game = game;
	}
	
	
}