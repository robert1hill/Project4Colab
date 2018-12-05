package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Robert, Grady
 * This panel is intended to inspire the user by displaying an encouraging message above the data.
 */
public class SloganBar extends JPanel
{
    
    /**
     * the text area for the message
     */
    private JTextArea slogan;
    
    /**
     * constructor for sloganbar.
     */
    public SloganBar()
    {
       
        //actually very simple code. If you don't understand this, you have no hope.
        slogan = new JTextArea();
        slogan.setText("Mesonet - We make a MES Of the interNET");
        slogan.setBackground(Color.lightGray);
        add(slogan);
        setBackground(Color.lightGray);
    }
}
