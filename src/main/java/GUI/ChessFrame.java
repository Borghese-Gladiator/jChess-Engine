package GUI; 
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import BoardMovement.ChessBoard;
import BoardMovement.Position;

public class ChessFrame extends JFrame
{
	private static boolean whiteStart = true;
	private static boolean onePlayer = true;
	private final JPanel display;
    public  Tile[][] boardTiles = new Tile[8][8];
    public Position origin;
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
                    Tile b = new Tile(origin, boardTiles, game,
                    		new Position(i,j), img);
                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    boardTiles[i][j] = b;
                    display.add(b);
            	}
            	else
            	{
            		Tile b = new Tile(origin, boardTiles, game,
            				new Position(i, j), (new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                    boardTiles[i][j] = b;
            		display.add(b);
            	}
            }
        }
    }
    public final void paintComponent(final Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
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