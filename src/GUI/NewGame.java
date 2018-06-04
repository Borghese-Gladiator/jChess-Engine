package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import BreezySwing.GBDialog;
public class NewGame extends GBDialog
{
	
	JRadioButton whiteStart = addRadioButton("White", 1, 1, 1, 1); 
	JRadioButton blackStart = addRadioButton("Black", 2, 1, 1, 1);
	JRadioButton singlePlayer = addRadioButton("One Player", 1, 2, 1, 1);
	JRadioButton twoPlayer = addRadioButton("Two Player", 2, 2, 1, 1);
	
	/** Vertical padding around this panel. */
    static final int V_PADDING = 15;
    /** Horizontal padding around this panel. */
    static final int H_PADDING = 10;
    JButton ok = addButton("OK", 3, 1, 1, 1);
    JButton cancel = addButton("Cancel", 3, 2, 1, 1);
	private boolean cancelled = true;
	public NewGame(JFrame parent) {
        super(parent);
		setTitle("New Game");
        setSize(300, 300);
        setDlgCloseIndicator("Cancel");
        
        ButtonGroup start = new ButtonGroup();
		start.add(blackStart);
		start.add(whiteStart);
		ButtonGroup player = new ButtonGroup();
		player.add(singlePlayer);
		player.add(twoPlayer);
		whiteStart.setSelected(true);
		singlePlayer.setSelected(true);
        
        getRootPane().setDefaultButton(ok);
    }
	public void buttonClicked(JButton button)
	{
		if (button == ok)
		{
			BoardPanel.setOnePlayer(singlePlayer.isSelected());
			BoardPanel.setWhiteStart(whiteStart.isSelected());
			setDlgCloseIndicator("Ok");
		}
		dispose();
	}
}