package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Intro extends JDialog implements ActionListener
{
	private JTextArea instruct;
	private JButton exit;
	public Intro(JFrame owner)
	{
		super(owner);
		setTitle("How to Play");
		setSize(300, 300);
		instruct = new JTextArea();
		String instructText = "How to Play\nTake the enemy's king to win";
		instruct.setText(instructText);
		instruct.setEditable(false);
		add(instruct);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
		
	}
}