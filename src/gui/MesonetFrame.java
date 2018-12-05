package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import dataAnalysis.MapData;

/**
 * @author Robert, Grady
 * This class is the overall frame of the project
 */
public class MesonetFrame extends JFrame
{
    
    /**
     * this is a file. cool.
     */
    private static File file = null;
    
    
    /**
     * Its a button bar.
     */
    private static ButtonBar buttonBar = new ButtonBar();
    
    /**
     * this is a top panel.
     */
    private static TopPanel topPanel = new TopPanel();
   
    /**
     * this left panel is the combined stat and param panels
     */
    protected static LeftPanel leftPanel = new LeftPanel();
    
    /**
     * this panel displays the data
     */
    private static TablePanel tablePanel = new TablePanel();
   
    

    /**
     * Constructor for mesonet frame.
     * @throws IOException Actually it should never throw an exception. Our code is just that good.
     */
    public MesonetFrame() throws IOException
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        
        //linking the table to the buttons
        buttonBar.setTablePanel(tablePanel);
        
        //adds the jpanels to the frame
                
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        
        //setting general but important things.
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * allows the user to choose the file he wants to read
     * @param selectedFile this is just a file that we want to read
     * @throws IOException again, this will never happen.
     */
    public static void setFile(File selectedFile) throws IOException
    {
        MesonetFrame.file = selectedFile;
        MapData data = new MapData(file);
        buttonBar.setMapData(data);
    }
}
