/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author Kelvin
 */
public class Sobrantes_Detalles {

    private int idSobDetalle;
    private Sobrantes idSobrante;
    private Producto codProd;
    private int unidades;
    private int fracciones;
    private double precio;
    private double monto;
    private double pvp;
    private double descuento;

    public Sobrantes_Detalles() {

    }

    public Producto getCodProd() {
        return codProd;
    }

    public void setCodProd(Producto codProd) {
        this.codProd = codProd;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getFracciones() {
        return fracciones;
    }

    public void setFracciones(int fracciones) {
        this.fracciones = fracciones;
    }

    public int getIdSobDetalle() {
        return idSobDetalle;
    }

    public void setIdSobDetalle(int idSobDetalle) {
        this.idSobDetalle = idSobDetalle;
    }

    public Sobrantes getIdSobrante() {
        return idSobrante;
    }

    public void setIdSobrante(Sobrantes idSobrante) {
        this.idSobrante = idSobrante;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    
}
