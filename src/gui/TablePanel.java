package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel
{
    /**
     * 
     */
    private JTable table = new JTable(100, 6);
    
    /**
     * 
     */
    private JScrollPane outline;
    
    /**
     * 
     */
    private int numDataRows;
    
    
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
    
    
    public void newDataRow(String inStation, String inParam, String inStat, double inVal, int inRepNum, String inDate)
    {
        
        
        table.setValueAt(inStation, numDataRows, 0);
        table.setValueAt(inParam, numDataRows, 1);
        table.setValueAt(inStat, numDataRows, 2);
        table.setValueAt(inVal, numDataRows, 3);
        table.setValueAt(inRepNum, numDataRows, 4);
        table.setValueAt(inDate, numDataRows, 5);
        
        
        ++numDataRows;
    }
}
