/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.Movimientos_Data;
import entidad.Movimientos;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaMovimientos {

    Movimientos_Data objdata;

    public LogicaMovimientos() {
        objdata = new Movimientos_Data();
    }

    public List<Movimientos> Lista_Descargos_Inv(String IDBOTICA, String tipmov, String alm) {
        return objdata.Lista_Descargos_Inv(IDBOTICA, tipmov, alm);
    }

    public List<Movimientos> Busca_Descargos_Inv(String IDBOTICA, String tipmov, String doc) {
        return objdata.Busca_Descargos_Inv(IDBOTICA, tipmov, doc);
    }

    public Movimientos Find_Descargos_Inv(String idbotica, Movimientos p) {
        return objdata.Find_Descargos_Inv(idbotica, p);
    }

    public List<Movimientos> Find_Descargos__Detalle_Inv(String idbotica, Movimientos p) {
        return objdata.Find_Descargos__Detalle_Inv(idbotica, p);
    }

    public Movimientos Verifica_Movimiento(String idbotica, String doc/*, int opc*/) {
        return objdata.Verifica_Movimiento(idbotica, doc/*,opc*/);
    }

    public Movimientos Devuelve_Movimeinto(String mov) {
        return objdata.Devuelve_Movimeinto(mov);
    }    
    
    public List<Movimientos> Devuelve_SerieNumero(String mov) {
        return objdata.Devuelve_SerieNumero(mov);
    }
    
    public Movimientos Devuelve_SerienumeroNC(String idventa,String serieTransaction, String numeroTransaction, String fechaVta,String OPC) {
        return objdata.Devuelve_SerienumeroNC(idventa,serieTransaction, numeroTransaction, fechaVta,OPC);
    }
}
