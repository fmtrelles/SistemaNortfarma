/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Laboratorios {

    private String Id_Lab;
    private String Descripcion;

    public Laboratorios() {
    }

    public Laboratorios(String Id_Lab, String Descripcion) {
        this.Id_Lab = Id_Lab;
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return this.Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getId_Lab() {
        return this.Id_Lab;
    }

    public void setId_Lab(String Id_Lab) {
        this.Id_Lab = Id_Lab;
    }
}
