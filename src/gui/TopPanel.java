package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/* This panel exists as a container to hold the
 * filemanubar and sloganbar panels. We can only have one
 * panel at position NORTH on the frame, so this is needed.
 */

/**
 * @author Robert, Grady
 * Class that stores the slogan and the file menu bar together
 */
public class TopPanel extends JPanel
{
    
    /**
     * the slogan bar
     */
    private SloganBar sloganBar;
    
    
    /**
     * the file menu bar
     */
    private FileMenuBar fileMenuBar;
    
    /**
     * constructor for top panel
     */
    TopPanel()
    {
        //initializing variables.
        setLayout(new BorderLayout());
        sloganBar = new SloganBar();
        fileMenuBar = new FileMenuBar();
        
        //adding stuff to the panel
        add(sloganBar, BorderLayout.SOUTH);
        add(fileMenuBar, BorderLayout.NORTH);
    }
}
