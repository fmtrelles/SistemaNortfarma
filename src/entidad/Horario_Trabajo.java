/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author kelvin
 */
public class Horario_Trabajo {

    private int Id_Horario_Trabajo;
    private Horarios_Detalle Horario_Detalle;
    private int rol;
    private String T1_H_Entrada;
    private String T1_H_Salida;
    private String T2_H_Entrada;
    private String T2_H_Salida;
    private String descestado;
    private char Modificado;
    private char Baja;
    private String Observacion;

    public char getBaja() {
        return Baja;
    }

    public void setBaja(char Baja) {
        this.Baja = Baja;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Horarios_Detalle getHorario_Detalle() {
        return Horario_Detalle;
    }

    public void setHorario_Detalle(Horarios_Detalle Horario_Detalle) {
        this.Horario_Detalle = Horario_Detalle;
    }

    public int getId_Horario_Trabajo() {
        return Id_Horario_Trabajo;
    }

    public void setId_Horario_Trabajo(int Id_Horario_Trabajo) {
        this.Id_Horario_Trabajo = Id_Horario_Trabajo;
    }

    public char getModificado() {
        return Modificado;
    }

    public void setModificado(char Modificado) {
        this.Modificado = Modificado;
    }

    public String getdescestado() {
        return descestado;
    }

    public void setdescestado(String descestado) {
        this.descestado = descestado;
    }


    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getT1_H_Entrada() {
        return T1_H_Entrada;
    }

    public void setT1_H_Entrada(String T1_H_Entrada) {
        this.T1_H_Entrada = T1_H_Entrada;
    }

    public String getT1_H_Salida() {
        return T1_H_Salida;
    }

    public void setT1_H_Salida(String T1_H_Salida) {
        this.T1_H_Salida = T1_H_Salida;
    }

    public String getT2_H_Entrada() {
        return T2_H_Entrada;
    }

    public void setT2_H_Entrada(String T2_H_Entrada) {
        this.T2_H_Entrada = T2_H_Entrada;
    }

    public String getT2_H_Salida() {
        return T2_H_Salida;
    }

    public void setT2_H_Salida(String T2_H_Salida) {
        this.T2_H_Salida = T2_H_Salida;
    }

}