/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author root
 */
public class DescuentosInvDetalle {

    private String idDescDetalle;
    private DescuentosInvTipo descuentoTipo;
    private DescuentosInv descuento;
    private double montoDesc;

    public DescuentosInvDetalle(){

    }

    public DescuentosInv getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentosInv descuento) {
        this.descuento = descuento;
    }

    public DescuentosInvTipo getDescuentoTipo() {
        return descuentoTipo;
    }

    public void setDescuentoTipo(DescuentosInvTipo descuentoTipo) {
        this.descuentoTipo = descuentoTipo;
    }

    public String getIdDescDetalle() {
        return idDescDetalle;
    }

    public void setIdDescDetalle(String idDescDetalle) {
        this.idDescDetalle = idDescDetalle;
    }

    public double getMontoDesc() {
        return montoDesc;
    }

    public void setMontoDesc(double montoDesc) {
        this.montoDesc = montoDesc;
    }

    

}
