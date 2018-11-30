package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MesonetFrame extends JFrame
{
    
    private ButtonBar buttonBar;
    private TopPanel topPanel;
    private StatisticsPanel statsPanel;
    private ParameterPanel paramPanel;
    private TablePanel tablePanel;
    

    public MesonetFrame()
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        buttonBar = new ButtonBar();
        topPanel = new TopPanel();
        statsPanel = new StatisticsPanel();
        paramPanel = new ParameterPanel();
        tablePanel = new TablePanel();
        
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        
        //may need to restructure this, making params and stats into one panel, like with top panel
        //add(paramPanel, BorderLayout.WEST);
        //add(statsPanel, BorderLayout.CENTER);
        //add(tablePanel, BorderLayout.EAST);

        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
    
}
