package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class VictoryDialog extends JDialog implements ActionListener
{
	private JTextArea winMsg;
	private JButton exit;
	public VictoryDialog(JFrame owner) {
		super(owner);
		setTitle("Victory Screen");
		setSize(300, 300);
		winMsg = new JTextArea();
		String victory = "WINNNNNER. White wins";
		winMsg.setText(victory);
		winMsg.setEditable(false);
		add(winMsg);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}
}
