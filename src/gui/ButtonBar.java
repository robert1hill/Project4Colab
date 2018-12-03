package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import dataAnalysis.MapData;
import dataAnalysis.Statistics;

public class ButtonBar extends JPanel implements ActionListener
    {
        private JButton calcButton;
        private JButton exitButton;
        private TablePanel table;
        private MapData mapData;

        public ButtonBar()
        {
            calcButton = new JButton("Calculate");
            exitButton = new JButton("Exit");
        
            calcButton.addActionListener(this);
            exitButton.addActionListener(this);
            calcButton.addActionListener(this);
            exitButton.addActionListener(this);
            
            add(calcButton);
            add(exitButton);
            
            setBackground(Color.GRAY);
            
            
        }
        
        public void setTablePanel(TablePanel table)
        {
            this.table = table;
        }
        
        public void setMapData(MapData mapData)
        {
            this.mapData = mapData;
        }
        

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //this tells me what button was clicked
            JButton clicked = (JButton)e.getSource();
            
            if (clicked == calcButton)
            {
                //TODO : add running the calc
                
                //checks which buttons are selected
                
                
                //retreives the desired data
                
                Statistics currStat = mapData.getStatistics(type, paramId);
                
                
                //adds a new row of data with the retrieved data.
                table.newDataRow(, currStat.getStid(), , currStat.getValue());
            }
            else if (clicked == exitButton)
            {
                System.exit(0);
            }
            
        }
}
