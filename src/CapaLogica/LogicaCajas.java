/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.CajasData;
import java.util.List;
import entidad.Cajas;
import entidad.DocumentoVentas;
import entidad.Turno;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaCajas {

    CajasData objcaja;

    public LogicaCajas() {
        objcaja = new CajasData();
    }

    public List<Cajas> ListarCajas() {
        return objcaja.ListarCajas();
    }

    public List<Cajas> ListarObs() {
        return objcaja.ListarObs();
    }

    public List<Cajas> Lista_Saldo_Inicial(String idbotica, String fecha) {
        return objcaja.Lista_Saldo_Inicial(idbotica, fecha);
    }

    public List<Cajas> Lista_Documen_Gastos() {
        return objcaja.Lista_Documen_Gastos();
    }

    public List<Cajas> Lista_Documen_Motivo() {
        return objcaja.Lista_Documen_Motivo();
    }

    public List<Cajas> Lista_Documen_SubMotivo() {
        return objcaja.Lista_Documen_SubMotivo();
    }

    public List<Cajas> Lista_Documen_Ingresos() {
        return objcaja.Lista_Documen_Ingresos();
    }

    public boolean Ingreso_Gastos_Caja(List<Cajas> obj) {
        return objcaja.Ingreso_Gastos_Caja(obj);
    }

    public boolean Ingreso_Caja(Cajas obj) {
        return objcaja.Ingreso_Caja(obj);
    }

    public List<Cajas> Devuelve_Data_Retencion(int retencion) {
        return objcaja.Devuelve_Data_Retencion(retencion);
    }

    public List<Cajas> Lista_Cajas_Cierre(String idbotica, String fecha) {
        return objcaja.Lista_Cajas_Cierre(idbotica, fecha);
    }

    public boolean Verifica_Caja_Cerrada(Cajas obj) {
        return objcaja.Verifica_Caja_Cerrada(obj);
    }

    public List<Turno> Lista_Turnos_Apertura(String idbotica, int idcaja, int idpersonal) {
        return objcaja.Lista_Turnos_Aperura(idbotica, idcaja, idpersonal);
    }

    public List<Turno> Lista_Turnos() {
        return objcaja.Lista_Turnos();
    }

    public List<Cajas> Lista_Gastos_Caja(Cajas obj) {
        return objcaja.Lista_Gastos_Caja(obj);
    }

    public boolean Elimina_Gasto(Cajas obj) {
        return objcaja.Elimina_Gasto(obj);
    }

    public List<DocumentoVentas> ListaDocumentos(Cajas obj) {
        return objcaja.ListaDocumentos(obj);
    }

    public int VerificaEsBCP(Cajas obj) {
        return objcaja.VerificaEsBCP(obj);
    }
    public int Consulta_Caja_Cerrada(Cajas obj) {
        return objcaja.Consulta_Caja_Cerrada(obj);
    }

    public List<Cajas> ListarCajas_Aperturadas() {
        return objcaja.ListarCajas_Aperturadas();
    }

    public int Consulta_Caja(Cajas obj) {
        return objcaja.Consulta_Caja(obj);
    }
}
