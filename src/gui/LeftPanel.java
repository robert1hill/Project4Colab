package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class LeftPanel extends JPanel
{
    private ParameterPanel param;
    protected static StatisticsPanel stats;
    
    LeftPanel()
    {
        setLayout(new BorderLayout());
        param = new ParameterPanel();
        stats = new StatisticsPanel();
        
        add(param, BorderLayout.WEST);
        add(stats, BorderLayout.EAST);
    }
}
