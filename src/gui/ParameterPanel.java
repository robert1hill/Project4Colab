package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ParameterPanel extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private static final int NUM_BUTTONS = 5;
    
    /**
     * 
     */
    private JLabel title = new JLabel("Parameters");
    
    /**
     * 
     */
    private GridBagConstraints constraints = new GridBagConstraints();
    
    /**
     * 
     */
    private Border in = BorderFactory.createLineBorder(Color.blue, 1);
    
    /**
     * 
     */
    private Border out = BorderFactory.createLineBorder(Color.gray, 5);
    
    /**
     * 
     */
    private CompoundBorder border = new CompoundBorder(out,in);
            
    /**
     * 
     */
    private JCheckBox tair = new JCheckBox("TAIR");
    
    /**
     * 
     */
    private JCheckBox ta9m = new JCheckBox("TA9M");
    
    /**
     * 
     */
    private JCheckBox srad = new JCheckBox("SRAD");
    
    /**
     * 
     */
    private JCheckBox wspd = new JCheckBox("WSPD");
    
    /**
     * 
     */
    private JCheckBox pres = new JCheckBox("PRES");
    
    public ParameterPanel()
    {
        super(new GridLayout(NUM_BUTTONS+1, 1));
        setBackground(Color.gray);
        constraints.insets = new Insets(5,5,5,5);
        setBorder(border);
        add(title);
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
    }
}
