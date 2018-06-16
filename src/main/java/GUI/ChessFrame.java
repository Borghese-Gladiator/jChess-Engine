package GUI; 
import java.awt.BorderLayout;
import java.awt.Color;
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

import BoardMovement.AI;
import BoardMovement.ChessBoard;
import BoardMovement.Move;
import BoardMovement.Position;

public class ChessFrame extends JFrame
{
	static ArrayList<Tile> white = new ArrayList<Tile>();
    static ArrayList<Tile> black = new ArrayList<Tile>();
    static ArrayList<Tile> legalMoves = new ArrayList<Tile>();
	private final JPanel display;
    public Tile[][] boardTiles = new Tile[8][8];
    static Position origin;
    AI whiteAI = null;
    AI blackAI = null;
    public static Position getOrigin() {
		return origin;
	}
	public static void setOrigin(Position origin) {
		ChessFrame.origin = origin;
	}
    static boolean isWhiteTurn = true;
	private ChessBoard game;
	private boolean twoPlayer;
	public void setTileAsAMove(Position pos)
	{
		boardTiles[pos.getX()][pos.getY()].setLegalMove(true);
	}
	public ChessFrame(boolean isWhiteAI, boolean isBlackAI)
	{
		super();
		
		twoPlayer = true;
		if (isWhiteAI || isBlackAI)
		{
			twoPlayer = false;
			if (isWhiteAI)
			{
				whiteAI = new AI();
			}
			if (isBlackAI)
			{
				blackAI = new AI();
			}
		}
		
		/*
		if (ai == null)
		{
			switchTurns();
		}
		else
		{
			move(ai.getmove(game.getBoard())); //   will be ArrayList<Position>, first is origin, second is moveTo
		}*/
		DefaultBoard images = new DefaultBoard();
        display = new JPanel();
        origin = null;
        
        MenuHandler handler = new MenuHandler(this);
        handler.setUpMenu();
        game = new ChessBoard(true);// whitestart
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        pack();
        addTiles(images);
        disableBtns();
        enableBtns(isWhiteTurn);
        
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
		if (isWhiteTurn)
		{
			isWhiteTurn = false;
			setTitle("BLack's turn");
			
		}
		else
		{
			isWhiteTurn = true;
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
		if (isWhiteTurn)
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
		if (!isWhiteTurn){ //capture opposing, remove from enable list
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
		game.move(origin, posTo);
		Move move = game.lastMove();
		clearLegalMoves();
		if (!(posTo.equals(origin)))
		{
			Tile temp = boardTiles[posTo.getX()][posTo.getY()];
			temp.setIcon(boardTiles[origin.getX()][origin.getY()].makeImgIcon());
			temp.setImg(boardTiles[origin.getX()][origin.getY()].getImg());
			boardTiles[origin.getX()][origin.getY()].removeIcon();
			if (move.size() > 2) //special move
			{
				if (move.size() == 3) //en passant
				{
					Position removePawn = move.changedPos(2);
					boardTiles[removePawn.getX()][removePawn.getY()].removeIcon();
				}
				else //move.size() == 4, castling
				{
					Position removeRook = move.changedPos(2);
					Position rookNewPos = move.changedPos(3);
					Tile tempRook = boardTiles[rookNewPos.getX()][rookNewPos.getY()];
					tempRook.setIcon(boardTiles[removeRook.getX()][removeRook.getY()].makeImgIcon());
					tempRook.setImg(boardTiles[removeRook.getX()][removeRook.getY()].getImg());
					boardTiles[removeRook.getX()][removeRook.getY()].removeIcon();
					addNewPosToEnableList(tempRook);
				}
			}
			disableBtnIfCapture(posTo);
			addNewPosToEnableList(temp);
			disableBtns();
			if (twoPlayer)
			{
				switchTurns();
			}
			enableBtns(isWhiteTurn);
			if (game.isCheckMated())
			{
				if (isWhiteTurn)
				{
					VictoryDialog vd = new VictoryDialog(this);
					vd.setVisible(true);
				}
				else
				{
					DefeatDialog dd = new DefeatDialog(this);
					dd.setVisible(true);
				}
				disableBtns();
			}
			if (game.isStalemated())
			{ 
				StalemateDialog sd = new StalemateDialog(this);
				sd.setVisible(true);
				disableBtns();
			}
		}
		origin = null;
    }
    public void showMoves(Position pos)
    {
    	clearLegalMoves();
    	ArrayList<Position> moves = game.getMoves(pos);
    	moves.add(pos);
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