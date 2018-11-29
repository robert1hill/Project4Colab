package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MesonetFrame extends JFrame
{
    
    private ButtonBar buttonBar;

    public MesonetFrame()
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        buttonBar = new ButtonBar();
        //textPanel = new TextPanel();
        //tp = new TextPanel();
        
        //toolbar.setTextPanel(textPanel);
        
        add(buttonBar, BorderLayout.SOUTH);
        //add(textPanel, BorderLayout.CENTER);
        //add(tp, BorderLayout.SOUTH);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
    
}
