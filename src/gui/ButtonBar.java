package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dataAnalysis.MapData;
import dataAnalysis.Statistics;
import dataAnalysis.StatsType;

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
                
                
                //checks which buttons are selected
                //check which buttons are selected.
                StatsType stat = MesonetFrame.leftPanel.stats.getSelected();
                //started to add this.
                ArrayList<String> paramIDs = MesonetFrame.leftPanel.param.getSelected();
                
              //retreives the desired data
                if (stat != null && !paramIDs.isEmpty())
                {
                    for (String eachParam : paramIDs)
                    {
                            Statistics currStat = mapData.getStatistics(stat, eachParam);
                        
                            //adds a new row of data with the retrieved data.
                            table.newDataRow(currStat.getStid(), eachParam, currStat.getStatsType().toString(), currStat.getValue(), currStat.getNumberOfReportingStations(), currStat.getUTCDateTime());
                        }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ensure that a Parameter and a Statistic option is selected!!!!", "FYI: You Failed!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (clicked == exitButton)
            {
                System.exit(0);
            }
            
        
                
            
        }
        
}
