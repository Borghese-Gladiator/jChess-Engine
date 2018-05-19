package com.nullprogram.chess.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class Settings extends JDialog implements ActionListener
{
	 /** Parent to this dialog. */
    private final ChessFrame parent;
	private boolean cancelled = true;
	//private final IntroExpla explanation; --->write this people
	public Settings(final ChessFrame owner)
	{
		super(owner, "Introduction", true);
		parent = owner;
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //explanation = new IntroExpla(); --->write this people
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
        ok.addActionListener(this);
        cancel.addActionListener(this);
        JPanel buttonRow = new JPanel();
        buttonRow.add(ok);
        buttonRow.add(cancel);
        add(buttonRow, BorderLayout.PAGE_END);
	}
	 @Override
	    public final void actionPerformed(final ActionEvent e) {
	        if ("OK".equals(e.getActionCommand())) {
	            cancelled = false;
	        }
	        setVisible(false);
	        dispose();
	    }
}
