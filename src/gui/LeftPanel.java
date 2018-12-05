package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @author Robert, Grady
 * this panel just combines the stat and param panels to allow us to use BorderLayout.
 */
public class LeftPanel extends JPanel
{
    /**
     * number 1
     */
    protected static ParameterPanel param;
    
    /**
     * number 2
     */
    protected static StatisticsPanel stats;
    
    /**
     * number 1 + 2. IE the constructor.
     */
    LeftPanel()
    {
        setLayout(new BorderLayout());
        param = new ParameterPanel();
        stats = new StatisticsPanel();
        
        add(param, BorderLayout.WEST);
        add(stats, BorderLayout.EAST);
    }
}
