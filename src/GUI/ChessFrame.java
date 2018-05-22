package GUI; 
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import BreezySwing.GBFrame;

public class ChessFrame extends GBFrame
{
	private Color boardColor;
	JMenuItem newGame = addMenuItem("Chess", "New Game");
	JMenuItem settings = addMenuItem("Chess", "Settings");
	JMenuItem intro = addMenuItem("Chess", "How to Play");
	JMenuItem exit = addMenuItem("Chess", "Exit");
	public void menuItemSelected(JMenuItem item)
	{
		if (item == newGame)
		{
			NewGame nwGame = new NewGame(this);
		}
		else if (item == settings)
		{
			Settings sett = new Settings(this);
			if (sett.getDlgCloseIndicator().equals("Apply"))
			{
				
			}
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
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Play Chess");
		setSize(400, 400);
		setVisible(true);
	}
}