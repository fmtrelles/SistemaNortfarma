/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author gparedes
 */
public class Marcas {
    
    private String RUC;
    private String DIRECCION;
    private String Descripcion;
    private int IdAsociado;

    public Marcas() {
    }

    public Marcas(int aInt, String string) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getIdAsociado() {
        return IdAsociado;
    }

    public void setIdAsociado(int IdAsociado) {
        this.IdAsociado = IdAsociado;
    }


    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}