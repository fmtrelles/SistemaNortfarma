/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Detalle_VentaDelivery {

    private Venta_Delivery delivery;
    private Venta venta;

    public Venta_Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Venta_Delivery delivery) {
        this.delivery = delivery;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
