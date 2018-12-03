package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JButton;
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
                //TODO : check which buttons are selected.
                StatsType stat = MesonetFrame.leftPanel.stats.getSelected();
                ArrayList<String> params = MesonetFrame.leftPanel.param.getSelected();
                
                if (stat != null && !params.isEmpty())
                {
                    for (int i = 0; i < params.size(); i++)
                    {
                        String paramID = params.get(i);
                        //retreives the desired data
                        //FIXME : change this to match the buttons that are selected.
                        Statistics currStat = mapData.getStatistics(stat, paramID);
                        
                        //adds a new row of data with the retrieved data.
                        table.newDataRow(currStat.getStid(), paramID, currStat.getStatsType().toString(), currStat.getValue(), currStat.getNumberOfReportingStations(), currStat.getUTCDateTime());
                    }
                }
            }
            else if (clicked == exitButton)
            {
                System.exit(0);
            }
            
        }
}
