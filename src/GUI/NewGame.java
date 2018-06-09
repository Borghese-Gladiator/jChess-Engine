package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import BoardMovement.ChessBoard;

public class NewGame extends JDialog implements ActionListener
{
	
	/*JRadioButton whiteStart = addRadioButton("White", 1, 1, 1, 1); 
	JRadioButton blackStart = addRadioButton("Black", 2, 1, 1, 1);
	JRadioButton singlePlayer = addRadioButton("One Player", 1, 2, 1, 1);
	JRadioButton twoPlayer = addRadioButton("Two Player", 2, 2, 1, 1);*/
	
	private final ChessFrame parent;

    /** White player selector. */
    private final PlayerChooser whitePanel;

    /** Black player selector. */
    private final PlayerChooser blackPanel;

    /** Vertical padding around this panel. */
    static final int V_PADDING = 15;

    /** Horizontal padding around this panel. */
    static final int H_PADDING = 10;

    /** True if the dialog was cancelled away. */
    private boolean cancelled = true;

    public NewGame(final ChessFrame owner) {
        super(owner, "New game", true);
        parent = owner;
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        whitePanel = new PlayerChooser("White:", true);
        blackPanel = new PlayerChooser("Black:", false);
        add(whitePanel, BorderLayout.LINE_START);
        add(blackPanel, BorderLayout.CENTER);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(this);
        cancel.addActionListener(this);
        getRootPane().setDefaultButton(ok);
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setBorder(BorderFactory.createEmptyBorder(H_PADDING,
                            V_PADDING,
                            H_PADDING,
                            V_PADDING));
        row.add(Box.createHorizontalGlue());
        row.add(ok);
        row.add(cancel);
        add(row, BorderLayout.PAGE_END);

        pack();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("OK".equals(e.getActionCommand())) {
            cancelled = false;
        }
        setVisible(false);
        dispose();
	}

    /**
     * Get the game selected/created by the user.
     *
     * @return the new game
     */
    public final ChessBoard getGame() {
        if (cancelled) {
            return null;
        }
        ChessBoard game = new ChessBoard();
        return game;
    }
}