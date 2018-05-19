package com.nullprogram.chess.gui;

import com.nullprogram.chess.Board;
import com.nullprogram.chess.Chess;
import com.nullprogram.chess.Game;
import com.nullprogram.chess.GameEvent;
import com.nullprogram.chess.GameListener;
import com.nullprogram.chess.Player;
import com.nullprogram.chess.boards.EmptyBoard;
import com.nullprogram.chess.pieces.ImageServer;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 * The JFrame that contains all GUI elements.
 */
public class ChessFrame extends JFrame
    implements ComponentListener, GameListener {

    /** Version for object serialization. */
    private static final long serialVersionUID = 1L;

    /** The board display. */
    private final BoardPanel display;

    /** The progress bar on the display. */
    private final StatusBar progress;

    /** The current game. */
    private Game game;

    /**
     * Create a new ChessFrame for the given board.
     */
    public ChessFrame() {
        super(Chess.getTitle());
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(ImageServer.getTile("King-WHITE"));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        MenuHandler handler = new MenuHandler(this);
        handler.setUpMenu();

        display = new BoardPanel(new EmptyBoard());
        progress = new StatusBar(null);
        add(display);
        add(progress);
        pack();

        addComponentListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Set up a new game.
     */
    public final void newGame() {
        NewGame ngFrame = new NewGame(this);
        ngFrame.setVisible(true);
        Game newGame = ngFrame.getGame();
        if (newGame == null) {
            return;
        }
        if (game != null) {
            game.end();
        }
        game = newGame;
        Board board = game.getBoard();
        display.setBoard(board);
        display.invalidate();
        setSize(getPreferredSize());

        progress.setGame(game);
        game.addGameListener(this);
        game.addGameListener(display);
        game.begin();
    }
    public final void intro()
    {
    	Intro introFrame = new Intro(this);
    }
    /**
     * Return the GUI (human) play handler.
     *
     * @return the player
     */
    public final Player getPlayer() {
        return display;
    }

    /**
     * Used for manaing menu events.
     */
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
            	
            }
            else if ("Settings".equals(e.getActionCommand()))
            {
            	
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
            JMenuItem intro = new JMenuItem("How to Play");
            intro.addActionListener(this);
            intro.setMnemonic('H');
            game.add(intro);
            game.add(new JSeparator());
            JMenuItem settings = new JMenuItem("Settings");
            settings.addActionListener(this);
            settings.setMnemonic('S');
            game.add(settings);
            game.add(new JSeparator());
            JMenuItem exitGame = new JMenuItem("Exit");
            exitGame.addActionListener(this);
            exitGame.setMnemonic('x');
            game.add(exitGame);
            menuBar.add(game);
            setJMenuBar(menuBar);
        }
    }

    @Override
    public final void componentResized(final ComponentEvent e) {
        if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0) {
            /* If the frame is maxmized, the battle has been lost. */
            return;
        }
        double ratio = display.getRatio();
        double barh = progress.getPreferredSize().getHeight();
        Container p = getContentPane();
        Dimension d = null;
        if (p.getWidth() * ratio < (p.getHeight() - barh)) {
            d = new Dimension((int) ((p.getHeight() - barh) * ratio),
                              p.getHeight());
        } else if (p.getWidth() * ratio > (p.getHeight() - barh)) {
            d = new Dimension(p.getWidth(),
                              (int) (p.getWidth() / ratio + barh));
        }
        if (d != null) {
            p.setPreferredSize(d);
            pack();
        }
    }

    @Override
    public final void gameEvent(final GameEvent e) {
        progress.repaint();
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
        /* Do nothing. */
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
        /* Do nothing. */
    }

    @Override
    public void componentShown(final ComponentEvent e) {
        /* Do nothing. */
    }
}
