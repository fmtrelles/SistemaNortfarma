/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Proveedor {

    public String Id_Proveedor;
    public String Descripcion;
    public String Direccion;
    public String Telefono;

    public Proveedor() {
    }

    public Proveedor(String Id_Proveedor, String Descripcion) {
        this.Id_Proveedor = Id_Proveedor;
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getId_Proveedor() {
        return Id_Proveedor;
    }

    public void setId_Proveedor(String Id_Proveedor) {
        this.Id_Proveedor = Id_Proveedor;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
}
