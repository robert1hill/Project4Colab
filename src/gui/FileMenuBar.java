package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Robert, Grady
 * This panel allows the user to select a file.
 */
public class FileMenuBar extends JPanel implements ActionListener
{
    /**
     * a menu bar. What would you like for dinner?
     */
    private JMenuBar menuBar; 
    
    /**
     * a drop down menu
     */
    private JMenu dropDown;
    
    /**
     * item that lets one exit the program
     */
    private JMenuItem exitItem;
    
    /**
     * item that lets one do cool stuff to the program
     */
    private JMenuItem openItem;
    
    /**
     * this is the file one opens.
     */
    protected File file = null;
    
    /**
     * Constructor for filemenubar
     */
    public FileMenuBar()
    {
        //Add functionality to the menu items when they are pressed.                
        //general set up stuff
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        //making the menu bar and adding it to the panel
        menuBar = new JMenuBar();
        add(menuBar, BorderLayout.WEST);
        
        //Creating the menu itself and adding it to the menubar
        dropDown = new JMenu("File");
        menuBar.add(dropDown);
        
        //creating individual menu items and adding them to the menu
        openItem = new JMenuItem("Open Data File");
        exitItem = new JMenuItem("Exit");
        openItem.addActionListener(this);
        exitItem.addActionListener(this);
        
        dropDown.add(openItem);
        dropDown.add(exitItem);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem clicked = (JMenuItem)e.getSource();
        
        //checks to see which button is clicked.
        if (clicked == exitItem)
        {
            System.exit(0);
        }
        else if (clicked == openItem)
        {

            //Implement this to open files and all that stuff... Not sure if the next line should be there. So we leaving it.
            //JFileChooser chooser = new JFileChooser(directoryPath);
            JFileChooser chooser = new JFileChooser("data");

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "mdf file", "mdf");
                chooser.setFileFilter(filter);
            chooser.showOpenDialog(this);
            
            //use to catch any IO exceptions.
            try
            {
                MesonetFrame.setFile(chooser.getSelectedFile());
            } catch (IOException e1)
            {
                
            }
            
        }
    }
}
