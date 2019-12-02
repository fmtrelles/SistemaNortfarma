/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author KELVIN
 */
public class InformeInv {

    private int idInforme;
    private String CodInforme;
    private String idBotica;
    private String fecha;

    public InformeInv() {

    }

    public String getCodInforme() {
        return CodInforme;
    }

    public void setCodInforme(String CodInforme) {
        this.CodInforme = CodInforme;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdBotica() {
        return idBotica;
    }

    public void setIdBotica(String idBotica) {
        this.idBotica = idBotica;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }
    
}
