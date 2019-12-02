package CapaLogica;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NOE
 */
public class MiDefaultTableModel extends DefaultTableModel{

    public MiDefaultTableModel() {
    }

    public MiDefaultTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    public boolean isCellEditable (int row, int column){
        if(columEditables!=null){
            for(int i=0;i<columEditables.length;i++) {
                if(columEditables[i] == column){
                    return true;
                }
            }
        }
        return false;
    }

    private int[] columEditables={};

    public int[] getColumEditables() {
        return columEditables;
    }

    public void setColumEditables(int[] columEditables) {
        this.columEditables = columEditables;
    }

    public Class getColumnClass(int columnIndex) {
        Boolean op;
        try {
            if(super.getValueAt(0, columnIndex)!=null) {
                op = (Boolean) super.getValueAt(0, columnIndex);
                return Boolean.class;
            }else {
                return String.class;
            }
            
        }catch(Exception e) {
            return String.class;
        }
    }

}
