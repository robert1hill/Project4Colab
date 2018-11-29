//This file is only for our reference. Not part of project 4

package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener
{
    private JButton startButton;
    private JButton endButton;
    private TextPanel textPanel;
    
    public Toolbar()
    {
        startButton = new JButton("Start");
        endButton = new JButton("terminate");
    
        startButton.addActionListener(this);
        endButton.addActionListener(this);
        
        add(startButton);
        add(endButton);
        
    }
    
    public void setTextPanel(TextPanel textPanel)
    {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //this tells me what button was clicked
        JButton clicked = (JButton)e.getSource();
        
        if (clicked == startButton)
        {
            textPanel.appendText("Start your imagination" + '\n');
        }
        else if (clicked == endButton)
        {
            textPanel.appendText("Keep dreaming" + '\n');
        }
        
    }
    
    
    
}
