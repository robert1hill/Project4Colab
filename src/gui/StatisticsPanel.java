package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import dataAnalysis.StatsType;


/**
 * @author Robert, Grady
 * This class creates the radiobuttons that allow a user to select which type of statistic they want
 */
public class StatisticsPanel extends JPanel
{
    /**
     * a final of the serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * the number of buttons
     */
    private static final int NUM_BUTTONS = 3;
    
    /**
     * a border
     */
    private Border in = BorderFactory.createLineBorder(Color.yellow, 1);
    
    /**
     * another border
     */
    private Border out = BorderFactory.createLineBorder(Color.gray, 2);
    
    /**
     * yet another border
     */
    private CompoundBorder border = new CompoundBorder(out,in);
    
    /**
     * borders are complicated.
     */
    private TitledBorder titledBorder = new TitledBorder(border, "Statistic");
    
    /**
     * the minimum button
     */
    private JRadioButton min = new JRadioButton("MINIMUM");
    
    /**
     * the maximum button
     */
    private JRadioButton max = new JRadioButton("MAXIMUM");
    
    /**
     * the average button
     */
    private JRadioButton avg = new JRadioButton("AVERAGE");
    
    /**
     * a grouping of the buttons
     */
    private ButtonGroup buttons = new ButtonGroup();
    
    /**
     * constructor for statistics panel
     */
    public StatisticsPanel()
    {
        
        //doing general things that should always be done
        super(new GridLayout(NUM_BUTTONS, 1));
        setBackground(Color.gray);
        
        //adding individual buttons to the group
        buttons.add(min);
        buttons.add(max);
        buttons.add(avg);
        
        //setting the border
        setBorder(titledBorder);
        
        //adding buttons to the panel
        add(min);
        add(max);
        add(avg);
    }
    
    /**
     * this method tells us which button is pressed.
     * 
     * @return StatsType of the selected button
     */
    public StatsType getSelected()
    {
        if (max.isSelected())
        {
            return StatsType.MAXIMUM;
        }
        else if (min.isSelected())
        {
            return StatsType.MINIMUM;
        }
        else if (avg.isSelected())
        {
            return StatsType.AVERAGE;
        }
        else
        {
            return null;
        }
    }
}
