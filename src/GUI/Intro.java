package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import BreezySwing.GBDialog;

public class Intro extends GBDialog
{
	JTextArea instruct = addTextArea("", 1, 1, 2, 5);;
	JButton exit = addButton("Exit", 3, 1, 1, 1);
	public Intro(JFrame parent)
	{
		super(parent);
		setTitle("How to Play");
		setDlgCloseIndicator("Cancel");
		setSize(300, 300);
		String instructText = "How to Play, blah blah blah";
		instruct.setText(instructText);
		instruct.setEditable(false);
	}
	public void buttonClicked(JButton button)
	{
		dispose();
	}
}