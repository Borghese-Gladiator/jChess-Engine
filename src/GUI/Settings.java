package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;

import BreezySwing.GBDialog;

public class Settings extends GBDialog
{
	JButton cancel = addButton("Cancel", 3, 2, 1, 1);
	JButton apply = addButton("Apply Changes", 3, 1, 1, 1);
	public Settings(JFrame parent)
	{
		super(parent);
		setDlgCloseIndicator("Cancel");
		setSize(300, 300);
		setTitle("Settings");
	}
	public void buttonClicked(JButton button)
	{
		if (button == apply)
		{
			
			setDlgCloseIndicator("Apply");
		}
		dispose();
	}
}