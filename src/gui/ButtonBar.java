package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonBar extends JPanel implements ActionListener
    {
        private JButton calcButton;
        private JButton exitButton;
        
        public ButtonBar()
        {
            calcButton = new JButton("Calculate");
            exitButton = new JButton("Exit");
        
            calcButton.addActionListener(this);
            exitButton.addActionListener(this);
            
            add(calcButton);
            add(exitButton);
            
        }
        
        

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //this tells me what button was clicked
            JButton clicked = (JButton)e.getSource();
            
            if (clicked == calcButton)
            {
                //TODO : add running the calc
                //textPanel.appendText("Start your imagination" + '\n');
            }
            else if (clicked == exitButton)
            {
                //TODO : exit the system
                //textPanel.appendText("Keep dreaming" + '\n');
            }
            
        }
}
