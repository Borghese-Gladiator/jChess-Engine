package GUI; 
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;

public class ChessFrame extends GBFrame
{
	private final BoardPanel display;
	JMenuItem newGame = addMenuItem("Chess", "New Game");
	JMenuItem intro = addMenuItem("Chess", "How to Play");
	JMenuItem exit = addMenuItem("Chess", "Exit");
	public void menuItemSelected(JMenuItem item)
	{
		if (item == newGame)
		{
			NewGame ngFrame = new NewGame(this);
			ngFrame.setVisible(true);
		}
		else if (item == intro)
		{
			Intro howToPlay = new Intro(this);
			howToPlay.setVisible(true);
		}
		else //item == exit
		{
			System.exit(0);
		}
	}
	public ChessFrame()
	{
		super();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Play Chess");
		setSize(500, 500);
		display = new BoardPanel();
		add(display);
		setVisible(true);
	}
}