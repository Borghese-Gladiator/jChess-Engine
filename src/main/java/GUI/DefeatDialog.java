package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DefeatDialog extends JDialog implements ActionListener
{
	private JTextArea loseMsg;
	private JButton exit;
	public DefeatDialog(JFrame owner) {
		super(owner);
		setTitle("Defeat Screen");
		setSize(300, 300);
		loseMsg = new JTextArea();
		String lose = "DEFEAAT. White loses.";
		loseMsg.setText(lose);
		loseMsg.setEditable(false);
		add(loseMsg);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}
}
