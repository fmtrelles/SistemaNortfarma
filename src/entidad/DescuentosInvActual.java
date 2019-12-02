/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author root
 */
public class DescuentosInvActual {

    private String idDescuentoActual;
    private String anio;
    private double monto;

    public DescuentosInvActual() {

    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getIdDescuentoActual() {
        return idDescuentoActual;
    }

    public void setIdDescuentoActual(String idDescuentoActual) {
        this.idDescuentoActual = idDescuentoActual;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
