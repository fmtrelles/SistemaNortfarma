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
public class ProformaComparatorByOrdenTotal implements Comparator<Proforma> {

    @Override
    public int compare(Proforma o1, Proforma o2) {
        int flag = Integer.parseInt(o1.getOrdenProducto()) - Integer.parseInt(o2.getOrdenProducto());
        if (flag == 0) {
            flag = (int) o2.getTotal() - (int) o1.getTotal();
        }
        return flag;
    }
}
