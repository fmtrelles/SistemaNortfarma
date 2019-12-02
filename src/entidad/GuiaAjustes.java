/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author root
 */
public class GuiaAjustes {

    private int id_guiaAjuste;
    private String fecha;
    private String serie;
    private String numero;
    private String obs;

    public GuiaAjustes() {

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_guiaAjuste() {
        return id_guiaAjuste;
    }

    public void setId_guiaAjuste(int id_guiaAjuste) {
        this.id_guiaAjuste = id_guiaAjuste;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }



}
