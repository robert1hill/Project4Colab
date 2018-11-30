package gui;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ParameterPanel extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
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
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
    }
}
