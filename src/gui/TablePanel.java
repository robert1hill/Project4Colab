package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Robert, Grady
 * This class creates the table that will display retrieved data.
 */
public class TablePanel extends JPanel
{
    /**
     * J table for the data to be displayed in.
     */
    private JTable table = new JTable(1000, 6);
    
    /**
     * the scroll pane for the table to be displayed in
     */
    private JScrollPane outline;
    
    /**
     * integer that tracks the number of filled data rows.
     */
    private int numDataRows;
    
    
    /**
     * Constructor for table panel.
     */
    public TablePanel()
    {
        
        setLayout(new BorderLayout());
        outline = new JScrollPane(table);
        table.setShowGrid(false);
        
        //setting table headers
        table.getColumnModel().getColumn(0).setHeaderValue("Station");
        table.getColumnModel().getColumn(1).setHeaderValue("Parameter");
        table.getColumnModel().getColumn(2).setHeaderValue("Statistics");
        table.getColumnModel().getColumn(3).setHeaderValue("Value");
        table.getColumnModel().getColumn(4).setHeaderValue("Reporting Stations");
        table.getColumnModel().getColumn(5).setHeaderValue("Date");
        
        numDataRows = 0;
        
        add(outline, BorderLayout.CENTER);
    }
    
    
    /**
     * Lots of parameters, I know. Don't get scared. It just adds a row of data to the table.
     * @param inStation
     * @param inParam
     * @param inStat
     * @param inVal
     * @param inRepNum
     * @param inDate
     */
    public void newDataRow(String inStation, String inParam, String inStat, double inVal, int inRepNum, String inDate)
    {
        
        //filling a row with new data.
        table.setValueAt(inStation, numDataRows, 0);
        table.setValueAt(inParam, numDataRows, 1);
        table.setValueAt(inStat, numDataRows, 2);
        table.setValueAt(inVal, numDataRows, 3);
        table.setValueAt(inRepNum, numDataRows, 4);
        table.setValueAt(inDate, numDataRows, 5);
        
        
        ++numDataRows;
    }
}
