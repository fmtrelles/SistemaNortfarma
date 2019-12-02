/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Caracteristicas {

    Integer Id_Caracteristicas;
    String Descripcion;
    String Valor;
    String Categoria;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Integer getId_Caracteristicas() {
        return Id_Caracteristicas;
    }

    public void setId_Caracteristicas(Integer Id_Caracteristicas) {
        this.Id_Caracteristicas = Id_Caracteristicas;
    }

    public Caracteristicas(Integer Id_Caracteristicas, String Descripcion) {
        this.Id_Caracteristicas = Id_Caracteristicas;
        this.Descripcion = Descripcion;
    }

    public Caracteristicas(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Caracteristicas(String Descripcion, String Valor) {
        this.Descripcion = Descripcion;
        this.Valor = Valor;
    }

    public Caracteristicas(String Descripcion, String Valor, String Categoria) {
        this.Descripcion = Descripcion;
        this.Valor = Valor;
        this.Categoria = Categoria;
    }
}
