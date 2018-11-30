package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SloganBar extends JPanel
{
    
    private JTextArea slogan;
    
    public SloganBar()
    {
       
        slogan = new JTextArea();
        slogan.setText("Mesonet - We make a MES Of the interNET");
        slogan.setBackground(Color.LIGHT_GRAY);
        add(slogan);
        setBackground(Color.LIGHT_GRAY);
    }
}
