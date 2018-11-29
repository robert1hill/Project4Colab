//This file is only for our reference. Not part of project 4

package gui;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel
{
    
    private JTextArea textArea;
    
    public TextPanel()
    {
        textArea = new JTextArea();
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(textArea));
        
    }
    
    
    public void appendText(String text)
    {
        textArea.append(text);
    }
    
}
