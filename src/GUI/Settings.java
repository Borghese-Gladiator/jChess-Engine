package GUI;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import BreezySwing.GBDialog;

public class Settings extends GBDialog
{
	JRadioButton whiteStart = addRadioButton("White", 1, 1, 1, 1); 
	JRadioButton blackStart = addRadioButton("Black", 2, 1, 1, 1);
	JRadioButton singlePlayer = addRadioButton("One Player", 1, 2, 1, 1);
	JRadioButton twoPlayer = addRadioButton("Two Player", 2, 2, 1, 1);
	JButton cancel = addButton("Cancel", 4, 2, 1, 1);
	JButton apply = addButton("Apply Changes", 4, 1, 1, 1);
	public Settings(JFrame parent)
	{
		super(parent);
		setTitle("Settings");
		setDlgCloseIndicator("Cancel");
		setSize(300, 300);
		ButtonGroup start = new ButtonGroup();
		start.add(blackStart);
		start.add(whiteStart);
		ButtonGroup player = new ButtonGroup();
		player.add(singlePlayer);
		player.add(twoPlayer);
		whiteStart.setSelected(true);
		singlePlayer.setSelected(true);
	}
	public void buttonClicked(JButton button)
	{
		boolean x = whiteStart.isSelected();
		if (button == apply)
		{
			ChessFrame.setOnePlayer(singlePlayer.isSelected());
			ChessFrame.setWhiteStart(whiteStart.isSelected());
		}
		dispose();
	}
}