/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.TipoMovimientoData;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import java.util.List;
import entidad.TipoMovimiento;
import entidad.Venta;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaTipoMovimiento {

    TipoMovimientoData objTipoMovimiento;

    public LogicaTipoMovimiento() {
        objTipoMovimiento = new TipoMovimientoData();
    }

    public List<TipoMovimiento> ListarTipoMovimiento() {
        return objTipoMovimiento.ListarTipoMovimiento();
    }

    public List<Venta> Lista_Internos(String Botica, String Docto, int TV) {
        return objTipoMovimiento.Lista_Internos(Botica, Docto, TV);
    }
    public List<Venta> Lista_DescargosBoticas(String Botica, String Docto,String fechaini, String fechafin, int opc) {
        return objTipoMovimiento.Lista_DescargosBoticas(Botica, Docto, fechaini, fechafin, opc);
    }
    public int AnulaDescargoAdicionales(String idbotica,String numdoc,int opc_aux){
        return objTipoMovimiento.AnulaDescargoAdicionales(idbotica,numdoc,opc_aux);
    }
    public int VerificaImpreDescargo(){
        return objTipoMovimiento.VerificaImpreDescargo();
    }
    public List<Venta> Lista_DescargosBoticas_1(String Botica, String Docto,String fechaini, String fechafin, int opc) {
        return objTipoMovimiento.Lista_DescargosBoticas_1(Botica, Docto, fechaini, fechafin, opc);
    }
    public List<Venta> Lista_DescargosBoticasImpre(String Botica, String Docto,String fechaini, String fechafin, int opc) {
        return objTipoMovimiento.Lista_DescargosBoticasImpre(Botica, Docto, fechaini, fechafin, opc);
    }
    public List<Venta> Lista_DescargosBoticasImpreDetalle(String Botica, String Docto,String fechaini, String fechafin, int opc) {
        return objTipoMovimiento.Lista_DescargosBoticasImpreDetalle(Botica, Docto, fechaini, fechafin, opc);
    }
    
    public List<Venta> Lista_Parametros() {
        return objTipoMovimiento.Lista_Parametros();
    }
    public List<Venta> Lista_DatosVta(String idventa) {
        return objTipoMovimiento.Lista_DatosVtas(idventa);
    }

    public List<TipoMovimiento> ListarTipoMovimientoFiltradoDescargo(Integer valor) {
        return objTipoMovimiento.ListarTipoMovimientoFiltradoDescargo(valor);
    }
    
    public List<TipoMovimiento> ListarTipoMovimientoDescargoSAP(Integer valor) {
        return objTipoMovimiento.ListarTipoMovimientoDescargoSAP(valor);
    }
    public List<TipoMovimiento> ListarTipoMovimientoDescargoSAP1(Integer valor) {
        return objTipoMovimiento.ListarTipoMovimientoDescargoSAP1(valor);
    }

    public List<TipoMovimiento> ListarMovimientosCargosDescargos(String Botica, String Docto, String movi, String alma, String fecha1, String fecha2) {
        return objTipoMovimiento.ListarMovimientosCargosDescargos(Botica, Docto, movi, alma, fecha1, fecha2);
    }

    public List<TipoMovimiento> ListarMovimientosPorRol(int Rol, int tipo) {
        return objTipoMovimiento.ListarMovimientosPorRol(Rol, tipo);
    }

    public List<Movimientos> Recupera_Movimiento(Movimientos mov) {
        return objTipoMovimiento.Recupera_Movimiento(mov);
    }
    public List<Movimientos> Recupera_MovimientoFactura(Movimientos mov, int opc) {
        return objTipoMovimiento.Recupera_MovimientoFactura(mov,opc);
    }

    public List<Movimiento_Detalle> Recupera_Movimiento_Detalle(Movimientos mov) {
        return objTipoMovimiento.Recupera_Movimiento_Detalle(mov);
    }
    
    public List<Movimiento_Detalle> Recupera_Movimiento_Detalle_Factura(Movimientos mov,String fecha) {
        return objTipoMovimiento.Recupera_Movimiento_Detalle_Factura(mov,fecha);
    }
    
}
