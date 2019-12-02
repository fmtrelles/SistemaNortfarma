/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.NotasCreditoData;
import entidad.NotasCredito;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaNotasCredito {

    NotasCreditoData objnodcr;

    public LogicaNotasCredito() {
        objnodcr = new NotasCreditoData();
    }

    public List<NotasCredito> Lista_Notas_Credito(String idbotica, String fecha1, String fecha2) {
        return objnodcr.Lista_Notas_Credito(idbotica, fecha1, fecha2);
    }

    public List<NotasCredito> Lista_Notas(String idbotica, String doc) {
        return objnodcr.Lista_Notas(idbotica, doc);
    }

    public List<NotasCredito> Lista_Detalle_NotasCredito(String idbotica, String doc) {
        return objnodcr.Lista_Notas_Detalle(idbotica, doc);
    }
}
