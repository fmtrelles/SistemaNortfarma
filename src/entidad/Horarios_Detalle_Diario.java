/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Horarios_Detalle_Diario {

    private int Id_Horario_Detalle_Diario;
    private Horarios_Detalle Id_Horario_Detalle;
    private Justificacion Id_Justificacion;
    private String Fecha;
    private String T1_Hora_Inicio;
    private String T1_Hora_Final;
    private String T2_Hora_Inicio;
    private String T2_Hora_Final;
    private String Observacion;
    private int Desactivado;

    public int getDesactivado() {
        return Desactivado;
    }

    public void setDesactivado(int Desactivado) {
        this.Desactivado = Desactivado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Horarios_Detalle getId_Horario_Detalle() {
        return Id_Horario_Detalle;
    }

    public void setId_Horario_Detalle(Horarios_Detalle Id_Horario_Detalle) {
        this.Id_Horario_Detalle = Id_Horario_Detalle;
    }

    public int getId_Horario_Detalle_Diario() {
        return Id_Horario_Detalle_Diario;
    }

    public void setId_Horario_Detalle_Diario(int Id_Horario_Detalle_Diario) {
        this.Id_Horario_Detalle_Diario = Id_Horario_Detalle_Diario;
    }

    public Justificacion getId_Justificacion() {
        return Id_Justificacion;
    }

    public void setId_Justificacion(Justificacion Id_Justificacion) {
        this.Id_Justificacion = Id_Justificacion;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getT1_Hora_Final() {
        return T1_Hora_Final;
    }

    public void setT1_Hora_Final(String T1_Hora_Final) {
        this.T1_Hora_Final = T1_Hora_Final;
    }

    public String getT1_Hora_Inicio() {
        return T1_Hora_Inicio;
    }

    public void setT1_Hora_Inicio(String T1_Hora_Inicio) {
        this.T1_Hora_Inicio = T1_Hora_Inicio;
    }

    public String getT2_Hora_Final() {
        return T2_Hora_Final;
    }

    public void setT2_Hora_Final(String T2_Hora_Final) {
        this.T2_Hora_Final = T2_Hora_Final;
    }

    public String getT2_Hora_Inicio() {
        return T2_Hora_Inicio;
    }

    public void setT2_Hora_Inicio(String T2_Hora_Inicio) {
        this.T2_Hora_Inicio = T2_Hora_Inicio;
    }
}
