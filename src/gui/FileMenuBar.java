package gui;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileMenuBar extends JPanel
{
    private JMenuBar menuBar; 
    private JMenu dropDown;
    private JMenuItem exitItem;
    private JMenuItem openItem;
    
    public FileMenuBar()
    {
        //TODO: Add functionality to the menu items when they are pressed.                
        //general set up stuff
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        //making the menu bar and adding it to the panel
        menuBar = new JMenuBar();
        add(menuBar, BorderLayout.WEST);
        
        //Creating the menu itself and adding it to the menubar
        dropDown = new JMenu("file");
        menuBar.add(dropDown);
        
        //creating individual menu items and adding them to the menu
        openItem = new JMenuItem("Open Data File");
        exitItem = new JMenuItem("Exit");
        
        dropDown.add(openItem);
        dropDown.add(exitItem);
    }
}
