package GUI; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import BoardMovement.ChessBoard;

public class ChessFrame extends JFrame
{
	BoardPanel display = new BoardPanel();
	private Game game;
	
	public ChessFrame()
	{
		super();
		
		MenuHandler handler = new MenuHandler(this);
        handler.setUpMenu();
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Play Chess");
		setSize(500, 500);
		setVisible(true);
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
        ChessBoard newGame = ngFrame.getGame();
        if (newGame == null) {
            return;
        }
        if (game != null) {
            game.end();
        }

        progress.setGame(game);
        game.addGameListener(this);
        game.addGameListener(display);
        game.begin();
    }
	private class MenuHandler implements ActionListener {

        /** The "Game" menu. */
        private JMenu game;

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

            game = new JMenu("Game");
            game.setMnemonic('G');
            JMenuItem newGame = new JMenuItem("New Game");
            newGame.addActionListener(this);
            newGame.setMnemonic('N');
            game.add(newGame);
            game.add(new JSeparator());
            JMenuItem howToPlay = new JMenuItem("How to Play");
            howToPlay.addActionListener(this);
            howToPlay.setMnemonic('h');
            game.add(howToPlay);
            game.add(new JSeparator());
            JMenuItem exitGame = new JMenuItem("Exit");
            exitGame.addActionListener(this);
            exitGame.setMnemonic('x');
            game.add(exitGame);
            menuBar.add(game);

            setJMenuBar(menuBar);
        }
    }
}