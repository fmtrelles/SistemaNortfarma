/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author kelvin
 */
public class Horario_Marcacion {

    private int Id_Horario_Marcacion;
    private int Id_Horario_Trabajo;
    private int Id_Rol;
    private String Fecha;
    private String T1_H_Entrada;
    private String T1_H_Salida;
    private String T2_H_Entrada;
    private String T2_H_Salida;
    private String Tardanza;
    private String CID;
    private char Falta;
    private char Justificacion;
    private char JustificacionFalta;
    private String Observacion;
    private String Cmp;
    private String T1_P_Entrada;
    private String T1_P_Salida;
    private String T2_P_Entrada;
    private String T2_P_Salida;

    public String getT1_P_Entrada() {
        return T1_P_Entrada;
    }

    public void setT1_P_Entrada(String T1_P_Entrada) {
        this.T1_P_Entrada = T1_P_Entrada;
    }

    public String getT1_P_Salida() {
        return T1_P_Salida;
    }

    public void setT1_P_Salida(String T1_P_Salida) {
        this.T1_P_Salida = T1_P_Salida;
    }

    public String getT2_P_Entrada() {
        return T2_P_Entrada;
    }

    public void setT2_P_Entrada(String T2_P_Entrada) {
        this.T2_P_Entrada = T2_P_Entrada;
    }

    public String getT2_P_Salida() {
        return T2_P_Salida;
    }

    public void setT2_P_Salida(String T2_P_Salida) {
        this.T2_P_Salida = T2_P_Salida;
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(int Id_Rol) {
        this.Id_Rol = Id_Rol;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getId_Horario_Marcacion() {
        return Id_Horario_Marcacion;
    }

    public void setId_Horario_Marcacion(int Id_Horario_Marcacion) {
        this.Id_Horario_Marcacion = Id_Horario_Marcacion;
    }

    public int getId_Horario_Trabajo() {
        return Id_Horario_Trabajo;
    }

    public void setId_Horario_Trabajo(int Id_Horario_Trabajo) {
        this.Id_Horario_Trabajo = Id_Horario_Trabajo;
    }

    public char getJustificacion() {
        return Justificacion;
    }

    public void setJustificacion(char Justificacion) {
        this.Justificacion = Justificacion;
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

    public String getTardanza() {
        return Tardanza;
    }

    public void setTardanza(String Tardanza) {
        this.Tardanza = Tardanza;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public char getFalta() {
        return Falta;
    }

    public void setFalta(char Falta) {
        this.Falta = Falta;
    }

    public char getJustificacionFalta() {
        return JustificacionFalta;
    }

    public void setJustificacionFalta(char JustificacionFalta) {
        this.JustificacionFalta = JustificacionFalta;
    }

    public String getCmp() {
        return Cmp;
    }

    public void setCmp(String Cmp) {
        this.Cmp = Cmp;
    }
    
}
