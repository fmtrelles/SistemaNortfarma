/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class TipoCambio {

    private int Id_Cambio;
    private int Id_Moneda;
    private Date Fecha;
    private String Moneda;
    private String Moneda1;
    private double TipoCambio;

    public TipoCambio() {
    }

    public TipoCambio(int Id_Cambio, Date Fecha, String Moneda, double TipoCambio) {
        this.Id_Cambio = Id_Cambio;
        this.Fecha = Fecha;
        this.Moneda = Moneda;
        this.TipoCambio = TipoCambio;
    }

    public TipoCambio(int Id_Cambio, String Moneda, double TipoCambio) {
        this.Id_Cambio = Id_Cambio;
        this.Moneda = Moneda;
        this.TipoCambio = TipoCambio;
    }

    public TipoCambio(int Id_Moneda, String Moneda1) {
        this.Id_Moneda = Id_Moneda;
        this.Moneda1 = Moneda1;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public int getId_Cambio() {
        return Id_Cambio;
    }

    public void setId_Cambio(int Id_Cambio) {
        this.Id_Cambio = Id_Cambio;
    }

    public int getId_Moneda() {
        return Id_Moneda;
    }

    public void setId_Moneda(int Id_Moneda) {
        this.Id_Moneda = Id_Moneda;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getMoneda1() {
        return Moneda1;
    }

    public void setMoneda1(String Moneda1) {
        this.Moneda1 = Moneda1;
    }

    public double getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(double TipoCambio) {
        this.TipoCambio = TipoCambio;
    }
}
