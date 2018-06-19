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
import BoardMovement.AI2;
import BoardMovement.ChessBoard;
import BoardMovement.Move;
import BoardMovement.Position;

public class ChessFrame extends JFrame implements Runnable
{
	boolean isWhiteTurn = true;
	static ArrayList<Tile> white = new ArrayList<Tile>();
    static ArrayList<Tile> black = new ArrayList<Tile>();
    static ArrayList<Tile> legalMoves = new ArrayList<Tile>();
	private final JPanel display;
    public Tile[][] boardTiles = new Tile[8][8];
    Position origin;
    AI whiteAI = null;
    AI blackAI = null;
    public Position getOrigin() {
		return origin;
	}
	public  void setOrigin(Position origin) {
		this.origin = origin;
	}
	private ChessBoard game;
	private boolean twoPlayer;
	private static boolean isHardAI;
	public void setTileAsAMove(Position pos)
	{
		boardTiles[pos.getX()][pos.getY()].setLegalMove(true);
	}
	public ChessFrame(boolean isWhiteAI, boolean isBlackAI)
	{
		super();
		new Thread(this).start();
		twoPlayer = true;
		if (isBlackAI) //Black is always AI
		{
			twoPlayer = false;
			/*if (isWhiteAI)
			{
				whiteAI = new AI();
			}*/
			if (isBlackAI){
				blackAI = new AI();
			}
		}
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
    private void enableBtns(boolean isWhite){
		if (isWhite){
			for(Tile i: white){
				i.setEnabled(true);
			}
		}
		else{
			for(Tile i: black){
				i.setEnabled(true);
			}
		}
	}
	private void disableBtns(){
		for (Tile i: white){
			i.setEnabled(false);
		}
		for (Tile i: black){
			i.setEnabled(false);
		}
	}
	private void switchTurns()
	{
		if (isWhiteTurn)
		{
			setTitle("Black's turn");
			isWhiteTurn = false;
		}
		else
		{
			setTitle("White's turn");
			isWhiteTurn = true;
		}
	}
	private void addTileToEnableList(Tile aTile, boolean isWhite)
	{
		if (isWhite)
		{
			white.add(aTile);
		}
		else
		{
			black.add(aTile);
		}
	}
	private void removeUsingPos(ArrayList<Tile> list, Position origin)
	{
		for (Tile i: list)
		{
			if (i.getCoords().equals(origin))
			{
				list.remove(i);
				break;
			}
		}
	}
	private void addNewPosToEnableList(Tile aTile, Position origin){
		if (isWhiteTurn){
			white.add(aTile);
			removeUsingPos(white, origin);
			
		}
		else{
			black.add(aTile);
			removeUsingPos(black, origin);
		}
	}
	private void disableBtnIfCapture(Position posTo) {
		if (!(isWhiteTurn)){ //capture opposing, remove from enable list
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
	private boolean checkIfLegalMove(Position posTo) {
		for (Tile i: legalMoves)
    	{
    		if (posTo.equals(i.getCoords()))
    		{
    			return true;
    		}
    	}
		return false;
	}
    public void move(Position posTo){
    	if (checkIfLegalMove(posTo)){
    		if (!(posTo.equals(origin))){
    			promoteIconChange(posTo);
            	game.move(origin, posTo);
        		Move move = game.lastMove();
    			changeIconAtPos(posTo, origin);
    			if (move.size() > 2) { //special move
    				if (move.size() == 3) {//en passant
    					Position removePawn = move.changedPos(2);
    					boardTiles[removePawn.getX()][removePawn.getY()].removeIcon();
    				}
    				else {//move.size() == 4, castling
    				
    					changeIconAtPos(move.changedPos(2), move.changedPos(3));
    				}
    			}
    			switchTurns();
    			if (twoPlayer) {
    				disableBtns();
    			}/*
    			else {
    				isWhiteTurn = false;
    				if (!checkGameOver())
    				{
        				ArrayList<Position> aiMove = blackAI.getmove(game.getBoard());
        				changeIconAtPos(aiMove.get(1), aiMove.get(0));
            			game.move(aiMove.get(0), aiMove.get(1));
    				}
    				isWhiteTurn = true;
    			}*/
    			checkGameOver();
    			enableBtns(isWhiteTurn);
    		}
    	}
		clearLegalMoves();
		System.out.println("WHITE: " + posTo.toString() + " to " + origin.toString());
		origin = null;
    }
	private void changeIconAtPos(Position posTo, Position origin) {
		Tile temp = boardTiles[posTo.getX()][posTo.getY()];
		temp.setIcon(boardTiles[origin.getX()][origin.getY()].makeImgIcon());
		temp.setImg(boardTiles[origin.getX()][origin.getY()].getImg());
		boardTiles[origin.getX()][origin.getY()].removeIcon();
		disableBtnIfCapture(posTo);
		addNewPosToEnableList(temp, origin);
	}
	private boolean checkGameOver() {
		if (game.isCheckMated()){
			if (twoPlayer)
			{
				if (!(isWhiteTurn)){
					VictoryDialog vd = new VictoryDialog(this);
					vd.setVisible(true);
				}
				else
				{
					DefeatDialog dd = new DefeatDialog(this);
					dd.setVisible(true);
				}
			}
			else
			{
				if (!(isWhiteTurn))
				{
					VictoryDialog vd = new VictoryDialog(this);
					vd.setVisible(true);
				}
				else
				{
					DefeatDialog dd = new DefeatDialog(this);
					dd.setVisible(true);
				}
			}
			disableBtns();
			return true;
		}
		if (game.isStalemated())
		{ 
			StalemateDialog sd = new StalemateDialog(this);
			sd.setVisible(true);
			disableBtns();
			return true;
		}
		return false;
	}
	private void promoteIconChange(Position posTo) {
		if (game.getPiece(origin).isPawn() && (posTo.getY() == 0 || posTo.getY() == 7))
		{
			Image queen;
			if (game.getMovingSide())
			{
				queen = ImageDatabase.getTile("Queen-WHITE");
			}
			else
			{
				queen = ImageDatabase.getTile("Queen-BLACK");
			}
			boardTiles[origin.getX()][origin.getY()].setImg(queen);
		}
	}
    public void showMoves(Position pos)
    {
    	if (checkMove(pos))
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
    }
    public void undoMove()
    {
    	game.undoMove();
    	//to be implemented
    }
    private boolean checkMove(Position pos)
    {
    	if (game.getMovingSide()){ //capture opposing, remove from enable list
			for (Tile i: white){
				if (pos.equals(i.getCoords()))
				{
					return true;
				}
			}
		}
		else{
			for (Tile i: black){
				if (pos.equals(i.getCoords()))
				{
					return true;
				}
			}
		}
    	return false;
    }
	public final void intro()
	{
		Intro intro = new Intro(this);
		intro.setVisible(true);
	}
	public void make2PGame()
	{
		new ChessFrame(false, false);
	}
	/*public final void newGame(boolean  isAHardAI) {
		NewGame ng = new NewGame(this);
		ng.setVisible(true);*/
	
	public void makeAIGame(boolean isHardAI)
	{
		this.isHardAI = isHardAI;
		new ChessFrame(false, true);
    }
	private class MenuHandler implements ActionListener {

        private JMenu menu;
        private JMenu undoMenu;

        private final ChessFrame frame;
        public MenuHandler(final ChessFrame parent) {
            frame = parent;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if ("New Game".equals(e.getActionCommand())) {
            	frame.dispose();
                frame.make2PGame();
            }
            else if ("Undo".equals(e.getActionCommand()))
            {
            	frame.undoMove();
            }
            else if ("vs Person".equals(e.getActionCommand()))
            {
            	frame.dispose();
            	frame.make2PGame();
            }
            else if ("AI - (Easy)".equals(e.getActionCommand()))
            {
            	frame.dispose();
            	frame.makeAIGame(false);
            }
            else if ("AI - (Hard)".equals(e.getActionCommand()))
            {
            	frame.dispose();
            	frame.makeAIGame(true);
            }
            else if ("How to Play".equals(e.getActionCommand()))
            {
            	frame.intro();
            }
    		else if ("Exit".equals(e.getActionCommand())) {
                System.exit(0);
            }
        }
        public final void setUpMenu() {
            JMenuBar menuBar = new JMenuBar();

            undoMenu = new JMenu("Undo Move?");
            undoMenu.setMnemonic('U');
            JMenuItem undo = new JMenuItem("Undo");
            undo.addActionListener(this);
            undo.setMnemonic('u');
            undoMenu.add(undo);
            menu = new JMenu("Game");
            menu.setMnemonic('G');
            /*JMenuItem newGame = new JMenuItem("New Game");
            newGame.addActionListener(this);
            newGame.setMnemonic('N');
            menu.add(newGame);
            menu.add(new JSeparator());*/
            JMenuItem twoP = new JMenuItem("vs Person");
            twoP.addActionListener(this);
            twoP.setMnemonic('P');
            menu.add(twoP);
            menu.add(new JSeparator());
            JMenuItem easyAI = new JMenuItem("AI - (Easy)");
            easyAI.addActionListener(this);
            easyAI.setMnemonic('E');
            menu.add(easyAI);
            menu.add(new JSeparator());
            JMenuItem hardAI = new JMenuItem("AI - (Hard)");
            hardAI.addActionListener(this);
            hardAI.setMnemonic('H');
            menu.add(hardAI);
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			if (!(isWhiteTurn))
			{
				if (!checkGameOver())
				{
					if (isHardAI)
					{
						ArrayList<Position> aiMove = AI2.getMove(game, 2);
						changeIconAtPos(aiMove.get(1), aiMove.get(0));
						game.move(aiMove.get(0), aiMove.get(1));
						System.out.println("BLACK: " + aiMove.get(0) + " to " + aiMove.get(1));
					}
					else
					{
						ArrayList<Position> aiMove = blackAI.getmove(game.getBoard());
						changeIconAtPos(aiMove.get(1), aiMove.get(0));
		    			game.move(aiMove.get(0), aiMove.get(1));
		    			System.out.println("BLACK: " + aiMove.get(0) + " to " + aiMove.get(1));
					}
				}
				switchTurns();
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}