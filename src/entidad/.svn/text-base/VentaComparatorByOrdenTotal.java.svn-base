/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Comparator;

/**
 *
 * @author PEDRO
 */
public class VentaComparatorByOrdenTotal implements Comparator<Venta> {

    @Override
    public int compare(Venta o1, Venta o2) {

        int flag = Integer.parseInt(o1.getOrden()) - Integer.parseInt(o2.getOrden());
        if (flag == 0) {
            flag = (int)o2.getTotal() - (int)o1.getTotal();
        }
        return flag;
    }
}
