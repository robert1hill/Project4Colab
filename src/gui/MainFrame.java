//This file is only for our reference. Not part of project 4

package gui;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
    private TextPanel textPanel;
    private Toolbar toolbar;
    private TextPanel tp;
    
    public MainFrame()
    {
        super("hello");
        
        setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        tp = new TextPanel();
        
        toolbar.setTextPanel(textPanel);
        
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(tp, BorderLayout.SOUTH);
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
}
