//This file is only for our reference. Not part of project 4

package gui;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class NOTPROJ4MainFrame extends JFrame
{
    private NOTPROJ4TextPanel textPanel;
    private NOTPROJ4Toolbar toolbar;
    private NOTPROJ4TextPanel tp;
    
    public NOTPROJ4MainFrame()
    {
        super("hello");
        
        setLayout(new BorderLayout());
        toolbar = new NOTPROJ4Toolbar();
        textPanel = new NOTPROJ4TextPanel();
        tp = new NOTPROJ4TextPanel();
        
        toolbar.setTextPanel(textPanel);
        
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(tp, BorderLayout.SOUTH);
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
}
