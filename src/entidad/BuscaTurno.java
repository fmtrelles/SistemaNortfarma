/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Fernando Muñoz
 */
public class BuscaTurno {

    int idturno;
    String descripcion;

    public BuscaTurno() {
    }

    public BuscaTurno(int idturno, String descripcion) {
        this.idturno = idturno;
        this.descripcion = descripcion;
    }

    public int getIdturno() {
        return idturno;
    }

    public void setIdturno(int idturno) {
        this.idturno = idturno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
