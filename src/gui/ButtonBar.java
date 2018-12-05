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

/**
 * @author Robert, Grady
 * This is objectively the best panel. its the bar at the bottom with the buttons.
 */
public class ButtonBar extends JPanel implements ActionListener
    {
        
    /**
     * Jbutton for the calculate button
     */
    private JButton calcButton;
        
    /**
     * JButton for the exit button
     */
    private JButton exitButton;
        
    /**
     * the table of that the data will be displayed in
     */
    private TablePanel table;
        
    /**
     * the map data thing
     */
    private MapData mapData;

        /**
         * constructor for button bar.
         */
        public ButtonBar()
        {
            //initializing
            calcButton = new JButton("Calculate");
            exitButton = new JButton("Exit");
        
            //adding action listeners
            calcButton.addActionListener(this);
            exitButton.addActionListener(this);
            
            //adding buttons
            add(calcButton);
            add(exitButton);
            
            setBackground(Color.GRAY);
        }
        
        
        /**
         * general setter
         * @param table
         */
        public void setTablePanel(TablePanel table)
        {
            this.table = table;
        }
        
        
        /**
         * general setter
         * @param mapData
         */
        public void setMapData(MapData mapData)
        {
            this.mapData = mapData;
        }
        
        

        /** 
         *  This is a cool action event
         */
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
