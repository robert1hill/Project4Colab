package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/* This panel exists as a container to hold the
 * filemanubar and sloganbar panels. We can only have one
 * panel at position NORTH on the frame, so this is needed.
 */

public class TopPanel extends JPanel
{
    
    private SloganBar sloganBar;
    private FileMenuBar fileMenuBar;
    
    TopPanel()
    {
     
        setLayout(new BorderLayout());
        sloganBar = new SloganBar();
        fileMenuBar = new FileMenuBar();
        
        add(sloganBar, BorderLayout.SOUTH);
        add(fileMenuBar, BorderLayout.NORTH);
        
    }
}
