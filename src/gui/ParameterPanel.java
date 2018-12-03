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
import javax.swing.border.TitledBorder;

import dataAnalysis.StatsType;

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
    private Border in = BorderFactory.createLineBorder(Color.blue, 1);
    
    /**
     * 
     */
    private Border out = BorderFactory.createLineBorder(Color.lightGray, 2);
    
    /**
     * 
     */
    private CompoundBorder border = new CompoundBorder(out,in);
    
    /**
     * 
     */
    private TitledBorder titledBorder = new TitledBorder(border, "Parameters");
    
    /**
     * 
     */
    private JCheckBox tair = new JCheckBox("TAIR    ");
    
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
        super(new GridLayout(NUM_BUTTONS, 1));
        setBackground(Color.lightGray);
        setBorder(titledBorder);
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
    }
    
    /**
     * @return
     */
    public String[] getSelected()
    {
        
        String[] selectedParams = new String[5];
        
        if (tair.isSelected())
        {
            selectedParams[0] = "TAIR";
        }
        if (ta9m.isSelected())
        {
            selectedParams[0] = "TA9M";
        }
        if (srad.isSelected())
        {
            selectedParams[0] = "SRAD";
        }
        if (wspd.isSelected())
        {
            selectedParams[0] = "WSPD";
        }
        if (pres.isSelected())
        {
            selectedParams[0] = "PRES";
        }

    }
}
