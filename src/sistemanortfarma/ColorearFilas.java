package sistemanortfarma;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {

    private final int columna_patron;

    public ColorearFilas(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        
        int fila = Integer.parseInt(table.getValueAt(row,columna_patron).toString());
        switch (fila) {
            
            case 1:              
                setForeground(Color.RED);
                setBackground(Color.RED);
                setFont(font);
                break;
            case 0:
                setForeground(Color.BLUE);
                setBackground(Color.WHITE);
                setFont(font);
                break;
            default:
                break;
        }
        
        
        
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
