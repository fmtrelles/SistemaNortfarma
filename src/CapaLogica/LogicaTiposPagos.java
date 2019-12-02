package CapaLogica;

import CapaDatos.TiposPagosData;
import entidad.TiposPagos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaTiposPagos {

    TiposPagosData objData;

    public LogicaTiposPagos() {
        objData = new TiposPagosData();
    }

    public List<TiposPagos> retornaTipoPagos(int band) throws SQLException {
        return objData.retornaTipoPagos(band);
    }
    public List<TiposPagos> retornaMoneda() throws SQLException {
        return objData.retornaMoneda();
    }

    public List<TiposPagos> retornaTipoPagosSOAT(int band) throws SQLException {
        return objData.retornaTipoPagosSOAT(band);
    }

    public List<TiposPagos> retornaTarjetas(int band) throws SQLException {
        return objData.Retorna_Tarjetas(band);
    }

    public List<TiposPagos> ListarTipoPago() {
        return objData.ListarTipoPago();
    }

    public void ActualizarTipoPago(String antiguo, String Nuevo) {
        objData.ActualizarTipoPago(antiguo, Nuevo);
    }

    public void AgregarTipoDocumentoPago(String Nuevo) {
        objData.AgregarTipoPago(Nuevo);
    }

    public Integer EliminarTipoPago(String Antiguo) {
        return objData.EliminarTipoPago(Antiguo);
    }

    public boolean Verifica_Clave_BCP(String idbotica, String clave) {
        return objData.Verifica_Clave_BCP(idbotica, clave);
    }

    public boolean Agregar_Movimiento_deBCP(TiposPagos Objbcp) {
        return objData.Agregar_Movimiento_deBCP(Objbcp);
    }

    public boolean Agregar_Movimiento_deBCP_Cuadre(TiposPagos Objbcp, String Fecha) {
        return objData.Agregar_Movimiento_deBCP_Cuadre(Objbcp, Fecha);
    }

    public List<TiposPagos> Lista_movimientos_BCP(String idbotica, int idpersonal, int turno, int idcaja) {
        return objData.Lista_movimientos_BCP(idbotica, idpersonal, turno, idcaja);
    }

    public List<TiposPagos> Lista_movimientos_BCP_Cuadre(String idbotica, int idpersonal, int caja, String fecha, int turno, int idcaja) {
        return objData.Lista_movimientos_BCP_Cuadre(idbotica, idpersonal, caja, fecha, turno, idcaja);
    }

    public List<TiposPagos> Lista_Totales_BCP(String idbotica, int idpersonal, int turno) {
        return objData.Lista_Totales_BCP(idbotica, idpersonal, turno);
    }

    public boolean Elimina_Movimiento_BCP(String idbotica, String numero, String FECHA) {
        return objData.Elimina_Movimiento_BCP(idbotica, numero, FECHA);
    }

    public List<TiposPagos> Lista_Tipos_Descuentos() {
        return objData.Lista_Tipos_Descuentos();
    }
}
