/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Roles {

    public int Id_Rol;
    public String Descripcion;

    public Roles() {
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(int Id_Rol) {
        this.Id_Rol = Id_Rol;
    }
}
