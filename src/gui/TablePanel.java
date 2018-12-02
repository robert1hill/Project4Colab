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
        
        //setting table column widths
        //table.getTableHeader().getDefaultRenderer()
        
        
        
        add(outline, BorderLayout.CENTER);
    }
}
