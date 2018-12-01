package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class TablePanel extends JPanel
{
    /**
     * 
     */
    private JTable table = new JTable();
    
    TablePanel()
    {
        super(new BorderLayout());
        add(table, BorderLayout.CENTER);
    }
}
