package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StatisticsPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private static final int NUM_BUTTONS = 3;
    
    /**
     * 
     */
    private JRadioButton min = new JRadioButton("MINIMUM");
    
    /**
     * 
     */
    private JRadioButton max = new JRadioButton("MAXIMUM");
    
    /**
     * 
     */
    private JRadioButton avg = new JRadioButton("AVERAGE");
    
    /**
     * 
     */
    private ButtonGroup buttons = new ButtonGroup();
    
    public StatisticsPanel()
    {
        super(new GridLayout(NUM_BUTTONS, 1));
        setBackground(Color.darkGray);
        buttons.add(min);
        buttons.add(max);
        buttons.add(avg);
        add(min);
        add(max);
        add(avg);
    }
}
