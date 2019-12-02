/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author miguel
 */
public class Personal_contrato {

    private Personal personal;
    private Modelos_contratos contrato;
    private String Fecha_Inicio;
    private String Fecha_Fin;
    private String Codigo_Administrativo;
    private String Observacion;

    public String getCodigo_Administrativo() {
        return Codigo_Administrativo;
    }

    public void setCodigo_Administrativo(String Codigo_Administrativo) {
        this.Codigo_Administrativo = Codigo_Administrativo;
    }

    public String getFecha_Fin() {
        return Fecha_Fin;
    }

    public void setFecha_Fin(String Fecha_Fin) {
        this.Fecha_Fin = Fecha_Fin;
    }

    public String getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(String Fecha_Inicio) {
        this.Fecha_Inicio = Fecha_Inicio;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Modelos_contratos getContrato() {
        return contrato;
    }

    public void setContrato(Modelos_contratos contrato) {
        this.contrato = contrato;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
