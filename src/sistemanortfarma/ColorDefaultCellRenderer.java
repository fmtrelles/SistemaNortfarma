/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanortfarma;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author gparedes
 */
public class ColorDefaultCellRenderer extends DefaultTableCellRenderer{
private final int columna_patron;

    

    ColorDefaultCellRenderer(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        /*String fila;
        for(int i=0; i<table.getRowCount(); i++){ 
            fila = String.valueOf(table.getValueAt(i,6));
            if(fila.equals("1"))
            {
                setForeground(Color.RED);
            }
        }*/
        
        if (table.getValueAt(row,columna_patron).toString().equals("1")){
            setForeground(Color.RED);
        }
        
        return componente;
    }

    
    
}
