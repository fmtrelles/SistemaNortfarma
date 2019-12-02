/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Paginas {

    public int Id_Paginas;
    public String Descripcion;

    public Paginas() {
    }

    public Paginas(int Id_Paginas, String Descripcion) {
        this.Id_Paginas = Id_Paginas;
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getId_Paginas() {
        return Id_Paginas;
    }

    public void setId_Paginas(int Id_Paginas) {
        this.Id_Paginas = Id_Paginas;
    }
}
