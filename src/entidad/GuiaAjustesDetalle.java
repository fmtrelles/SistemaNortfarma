/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author root
 */
public class GuiaAjustesDetalle {

    private int id_guiaAjusteDet;
    private int id_guiaAjuste;
    private String tipoMov;
    private String numMov;

    public GuiaAjustesDetalle() {

    }

    public int getId_guiaAjuste() {
        return id_guiaAjuste;
    }

    public void setId_guiaAjuste(int id_guiaAjuste) {
        this.id_guiaAjuste = id_guiaAjuste;
    }

    public int getId_guiaAjusteDet() {
        return id_guiaAjusteDet;
    }

    public void setId_guiaAjusteDet(int id_guiaAjusteDet) {
        this.id_guiaAjusteDet = id_guiaAjusteDet;
    }

    public String getNumMov() {
        return numMov;
    }

    public void setNumMov(String numMov) {
        this.numMov = numMov;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    

}
