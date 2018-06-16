package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class StalemateDialog extends JDialog implements ActionListener
{
	private JTextArea stalemateMsg;
	private JButton exit;
	public StalemateDialog(JFrame owner) {
		super(owner);
		setTitle("Stalemate Screen");
		setSize(300, 300);
		stalemateMsg = new JTextArea();
		String stalemate = "STALEMATE";
		stalemateMsg.setText(stalemate);
		stalemateMsg.setEditable(false);
		add(stalemateMsg);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}
}
