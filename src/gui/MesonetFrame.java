package gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import dataAnalysis.MapData;

public class MesonetFrame extends JFrame
{
    
    private static ButtonBar buttonBar = new ButtonBar();
    private static TopPanel topPanel = new TopPanel();
    protected static LeftPanel leftPanel = new LeftPanel();
    private static TablePanel tablePanel = new TablePanel();
   
    

    public MesonetFrame() throws IOException
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        
        //linking the table to the buttons
        buttonBar.setTablePanel(tablePanel);
        
        //FIXME : this inputs a random mesonet data file. Needs to be changed when the file opener is fixed.
        
        
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        
        MapData test1 = new MapData(2018, 8, 1, 7, 0, "data1");
        buttonBar.setMapData(test1);

        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
