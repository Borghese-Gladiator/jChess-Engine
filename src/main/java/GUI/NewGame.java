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
	private ChessFrame owner;
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

    public NewGame(final ChessFrame aOwner) {
        super(aOwner, "New game", true);
        owner = aOwner;
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        whitePanel = new PlayerChooser("White:", true, this);
        blackPanel = new PlayerChooser("Black:", false, this);
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
            getGame();
           
        }
        setVisible(false);
        dispose();
	}
	private void getGame()
	{
		owner =  new ChessFrame(whitePanel.isHuman(), blackPanel.isHuman());
	}
}