package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

public class FileMenuBar extends JPanel implements ActionListener
{
    private final String directoryPath = "/Users/gradylynn/eclipse-workspace/Project4/data";
    
    private JMenuBar menuBar; 
    private JMenu dropDown;
    private JMenuItem exitItem;
    private JMenuItem openItem;
    
    protected File file = null;
    
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem clicked = (JMenuItem)e.getSource();
        
        if (clicked == exitItem)
        {
            System.exit(0);
        }
        else if (clicked == openItem)
        {
            // TODO: Implement this to open files and all that stuff...
            JFileChooser chooser = new JFileChooser(directoryPath);
            chooser.showOpenDialog(null);
            MesonetFrame.setFile(chooser.getSelectedFile());
        }
    }
}
