package gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import dataAnalysis.MapData;

public class MesonetFrame extends JFrame
{
    
    private ButtonBar buttonBar;
    private TopPanel topPanel;
    protected static LeftPanel leftPanel;
    private TablePanel tablePanel;
   
    

    public MesonetFrame() throws IOException
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        buttonBar = new ButtonBar();
        topPanel = new TopPanel();
        leftPanel = new LeftPanel();
        tablePanel = new TablePanel();
        
        //linking the table to the buttons
        buttonBar.setTablePanel(tablePanel);
        
        //FIXME : this inputs a random mesonet data file. Needs to be changed when the file opener is fixed.
        MapData test1 = new MapData(2018, 8, 1, 7, 0, "data1");
        buttonBar.setMapData(test1);
        
        
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
