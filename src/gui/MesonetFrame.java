package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import dataAnalysis.MapData;

public class MesonetFrame extends JFrame
{
    private static File file = null;
    
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
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void setFile(File selectedFile) throws IOException
    {
        MesonetFrame.file = selectedFile;
        MapData data = new MapData(file);
        buttonBar.setMapData(data);
    }
}
