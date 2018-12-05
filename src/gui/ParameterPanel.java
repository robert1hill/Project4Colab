package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dataAnalysis.StatsType;

/**
 * @author Robert, Grady
 * this class allows the user to select which parameters he would like to retrieve data for. can select multiple.
 */
public class ParameterPanel extends JPanel
{

    /**
     * the final value of the serial version UID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * the number of buttons
     */
    private static final int NUM_BUTTONS = 5;
    
    /**
     * a border
     */
    private Border in = BorderFactory.createLineBorder(Color.blue, 1);
    
    /**
     * another border
     */
    private Border out = BorderFactory.createLineBorder(Color.lightGray, 2);
    
    /**
     * borders are fun
     */
    private CompoundBorder border = new CompoundBorder(out,in);
    
    /**
     * actually the last border
     */
    private TitledBorder titledBorder = new TitledBorder(border, "Parameters");
    
    /**
     * check box for tair
     */
    private JCheckBox tair = new JCheckBox("TAIR    ");
    
    /**
     * check box for ta9m
     */
    private JCheckBox ta9m = new JCheckBox("TA9M");
    
    /**
     * check box for srad
     */
    private JCheckBox srad = new JCheckBox("SRAD");
    
    /**
     * check box for wspd
     */
    private JCheckBox wspd = new JCheckBox("WSPD");
    
    /**
     * check box for pres
     */
    private JCheckBox pres = new JCheckBox("PRES");
    
    /**
     * constructor for parameterpanel.
     */
    public ParameterPanel()
    {
        //doing general stuff
        super(new GridLayout(NUM_BUTTONS, 1));
        setBackground(Color.lightGray);
        
        //making that border
        setBorder(titledBorder);
        
        //adding checkboxes to the panel
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
    }
    
    /**
     * @return ArrayList of the checkboxes that are selected
     */
    public ArrayList<String> getSelected()
    {
        
        ArrayList<String> selectedParams = new ArrayList<String>();
        
        if (tair.isSelected())
        {
            selectedParams.add("TAIR");
        }
        if (ta9m.isSelected())
        {
            selectedParams.add("TA9M");
        }
        if (srad.isSelected())
        {
            selectedParams.add("SRAD");
        }
        if (wspd.isSelected())
        {
            selectedParams.add("WSPD");
        }
        if (pres.isSelected())
        {
            selectedParams.add("PRES");
        }
        
        return selectedParams;
    }
}
