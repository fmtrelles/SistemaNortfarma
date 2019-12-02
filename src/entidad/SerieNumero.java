/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class SerieNumero {

    private String serie;
    private String numero;

    public SerieNumero() {
    }

    public SerieNumero(String serie, String numero) {
        this.serie = serie;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
