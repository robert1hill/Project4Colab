package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MesonetFrame extends JFrame
{
    
    private ButtonBar buttonBar;
    private TopPanel topPanel;
    private FileMenuBar fileMenuBar;

    public MesonetFrame()
    {
        super("Oklahoma Mesonet - Statistics Calculator");
        
        setLayout(new BorderLayout());
        buttonBar = new ButtonBar();
        //sloganBar = new SloganBar();
        topPanel = new TopPanel();
        fileMenuBar = new FileMenuBar();
        //textPanel = new TextPanel();
        //tp = new TextPanel();
        
        //toolbar.setTextPanel(textPanel);
        
        add(buttonBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        //add(fileMenuBar, BorderLayout.NORTH);
        //add(textPanel, BorderLayout.CENTER);
        //add(tp, BorderLayout.SOUTH);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
    
}
