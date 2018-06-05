package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PlayerSelector extends JPanel
{
	/** Selection for a human player. */
    private final JRadioButton human = new JRadioButton("Human");;

    /** Selection for a computer player. */
    private final JRadioButton minimax = new JRadioButton("Computer");
    /** Vertical padding around this panel. */
    static final int V_PADDING = 15;

    /** Horizontal padding around this panel. */
    static final int H_PADDING = 10;
    
    public PlayerSelector(final String title, final boolean humanSet) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(title);
        add(label);

        /* Set up widgets. */
        ButtonGroup group = new ButtonGroup();
        group.add(human);
        group.add(minimax);
        human.setSelected(humanSet);
        minimax.setSelected(!humanSet);

        /* Set up widget alignment. */
        human.setAlignmentX(Component.LEFT_ALIGNMENT);
        minimax.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        add(human);
        add(minimax);
        setBorder(BorderFactory.createEmptyBorder(H_PADDING, V_PADDING,
                  H_PADDING, V_PADDING));
    }

}
