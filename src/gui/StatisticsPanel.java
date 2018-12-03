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
    private Border in = BorderFactory.createLineBorder(Color.yellow, 1);
    
    /**
     * 
     */
    private Border out = BorderFactory.createLineBorder(Color.gray, 2);
    
    /**
     * 
     */
    private CompoundBorder border = new CompoundBorder(out,in);
    
    /**
     * 
     */
    private TitledBorder titledBorder = new TitledBorder(border, "Statistic");
    
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
        setBackground(Color.gray);
        buttons.add(min);
        buttons.add(max);
        buttons.add(avg);
        setBorder(titledBorder);
        add(min);
        add(max);
        add(avg);
    }
    
    /**
     * @return
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
