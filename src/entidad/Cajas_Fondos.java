/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Fernando Muñoz
 */
public class Cajas_Fondos {

    int idcaja;
    String idbotica;
    double monto;
    String fecha;
    int idpersonal;

    public Cajas_Fondos() {
    }

    public Cajas_Fondos(int idcaja, String idbotica, double monto, String fecha, int idpersonal) {
        this.idcaja = idcaja;
        this.idbotica = idbotica;
        this.monto = monto;
        this.fecha = fecha;
        this.idpersonal = idpersonal;
    }

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }

    public String getIdbotica() {
        return idbotica;
    }

    public void setIdbotica(String idbotica) {
        this.idbotica = idbotica;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

}
