package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MesonetFrame extends JFrame
{
    
    private ButtonBar buttonBar;
    private TopPanel topPanel;
    private LeftPanel leftPanel;
    private TablePanel tablePanel;
    

    public MesonetFrame()
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        buttonBar = new ButtonBar();
        topPanel = new TopPanel();
        leftPanel = new LeftPanel();
        tablePanel = new TablePanel();
        
        //linking the table to the buttons
        buttonBar.setTablePanel(tablePanel);
        
        
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
}
