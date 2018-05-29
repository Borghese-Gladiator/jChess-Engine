package GUI;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import BreezySwing.GBDialog;
public class NewGame extends GBDialog
{
	private final ChessFrame parent;
	
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
	public NewGame(final ChessFrame owner) {
        super(owner);
        
        ButtonGroup start = new ButtonGroup();
		start.add(blackStart);
		start.add(whiteStart);
		ButtonGroup player = new ButtonGroup();
		player.add(singlePlayer);
		player.add(twoPlayer);
		whiteStart.setSelected(true);
		singlePlayer.setSelected(true);
        
        parent = owner;
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(ok);
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setBorder(BorderFactory.createEmptyBorder(H_PADDING,
                            V_PADDING,
                            H_PADDING,
                            V_PADDING));
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(ok);
        buttonRow.add(cancel);
        add(buttonRow, BorderLayout.PAGE_END);

        pack();
    }
	public void buttonClicked(JButton button)
	{
		if (button == ok)
		{
			BoardPanel.setOnePlayer(singlePlayer.isSelected());
			BoardPanel.setWhiteStart(whiteStart.isSelected());
		}
		dispose();
	}
}