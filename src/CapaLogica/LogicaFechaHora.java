/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.RetornaFechaHora;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaFechaHora {

    RetornaFechaHora onjfechahora;

    public LogicaFechaHora() {
        onjfechahora = new RetornaFechaHora();
    }

    public Date RetornaFecha() {
        return onjfechahora.RetornaFecha();
    }
    public Date RetornaFecha1(int mes, int anio) {
        return onjfechahora.RetornaFecha1(mes, anio);
    }

    public String RetornaIdentificacion() {
        return onjfechahora.RetornaIdentificacion();
    }

    public Date Retorna_Fecha_Cuadre(String idbotica, int idcaja) {
        return onjfechahora.Retorna_Fecha_Cuadre(idbotica, idcaja);
    }

    public String RetornaHora() {
        return onjfechahora.RetornaHora();
    }

    public String ConvierteFecha(Date fechaOtroFormato) {
        return onjfechahora.ConvierteFecha(fechaOtroFormato);
    }

    public Date ConvierteFecha2(Date fechaOtroFormato) {
        return onjfechahora.ConvierteFecha2(fechaOtroFormato);
    }

    public String MysqlToJuliano(String fechaOtroFormato) {
        return onjfechahora.MysqlToJuliano(fechaOtroFormato);
    }
    public String MysqlToJuliano1(String fechaOtroFormato) {
        return onjfechahora.MysqlToJuliano1(fechaOtroFormato);
    }

    public String RetornaMes(String ElMes) {
        return onjfechahora.DevuelveMes(ElMes);
    }

    public Integer RecuperaEntero(String Cantidad) {
        return onjfechahora.devolverEntero(Cantidad);
    }
    public Integer EsConsumoMostrador(String Cantidad) {
        return onjfechahora.devolverEntero(Cantidad);
    }

    public Integer RecuperaFraccion(String Cantidad) {
        return onjfechahora.devolverFraccion(Cantidad);
    }

    public int VerificaFecha1(String idbotica) {
        return onjfechahora.VerificaFecha1(idbotica);
    }

    public boolean VerificaFecha(String idbotica) {
        return onjfechahora.VerificaFecha(idbotica);
    }
}
