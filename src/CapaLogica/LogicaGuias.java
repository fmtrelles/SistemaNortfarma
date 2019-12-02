package CapaLogica;

import CapaDatos.BoticasData;
import CapaDatos.GuiasData;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import entidad.Producto;
import java.sql.SQLException;
import entidad.Proforma;
import java.util.List;

public class LogicaGuias {

    GuiasData objGuia;
    private BoticasData objBoticas;

    public LogicaGuias() {
        objGuia = new GuiasData();
    }

    public String AgregarGuias(Guias objGuias) throws SQLException {
        return objGuia.AgregarGuia(objGuias);

    }

    public String AgregarDetalleGuia(List<Guias> objGuias, Guias entidadguias_cabecera) throws SQLException {
        return objGuia.AgregarDetalleGuia(objGuias, entidadguias_cabecera);
    }

    public Integer ValidarProductosGuia(String Botica, String Producto) {
        return objGuia.ValidarProductosGuia(Botica, Producto);
    }

    public Integer VerificaExisteGuiaBoticaDescargo(String BoticaOrigen, String IP, String NumeroGuia) {
        return objGuia.VerificaExisteGuiaBoticaDescargo(BoticaOrigen, IP, NumeroGuia);
    }

    public List<Guias> RecuperaDetalleCargo(String BoticaDestino, String NumeroDocumento, String BoticaOrigen) {
        return objGuia.RecuperaDetalleCargo(BoticaDestino, NumeroDocumento, BoticaOrigen);
    }

    public String RecuperaFechaDocto(String BoticaDestino, String BoticaOrigen, String NumeroDocumento) {
        return objGuia.RecuperaFechaDocto(BoticaDestino, BoticaOrigen, NumeroDocumento);
    }
    

    public void CargarGuiaBotica(Guias entidadGuia, String Obns, int tipo) {
        objGuia.CargarGuiaBotica(entidadGuia, Obns, tipo);
    }

    public void DescargarGuiaBotica(Guias entidadGuia, String Obns) throws SQLException {
        objGuia.DescargarGuiaBotica(entidadGuia, Obns);
    }

    public Movimientos CargarDetalleGuia(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie) {
        return objGuia.CargarDetalleGuia(entidadGuia, array, tipo, EsCar, Obns, mitipo, serie);
    }
    public Movimientos CargarDetalleGuia1(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie, String idventa, String fechavta) {
        return objGuia.CargarDetalleGuia1(entidadGuia, array, tipo, EsCar, Obns, mitipo, serie,idventa,fechavta);
    }

    public Movimientos CargarDetalleGuiaCargo(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie,Proforma entidadproformaCliente) {
        return objGuia.CargarDetalleGuiaCargo(entidadGuia, array, tipo, EsCar, Obns, mitipo, serie,entidadproformaCliente);
    }

    public Movimientos DescargarDetalleGuia(Guias entidadGuia, List<Movimiento_Detalle> array, String obsr,int opc) {
        return objGuia.DescargarDetalleGuia(entidadGuia, array, obsr,opc);
    }
    public Movimientos DescargarDetalleGuiaDescargo(Guias entidadGuia, List<Movimiento_Detalle> array, String obsr, Proforma entidadproformaCliente) {
        return objGuia.DescargarDetalleGuiaDescargo(entidadGuia, array, obsr,entidadproformaCliente);
    }

    public int guiaCargada(Guias entidadGuia, int Tipo, int Escargo) {
        return objGuia.guiaCargada(entidadGuia, Tipo, Escargo);
    }
    
    public boolean InsertaTmpProdGuia( List<Movimiento_Detalle> listProdGuia, int opc) {
        return objGuia.InsertaTmpProdGuia(listProdGuia,opc);
    }
    
    public List<Guias> recuperaProdGuia(int limite, String docu,int opc) {
        return objGuia.recuperaProdGuia(limite,docu,opc);
    }
    public List<Guias> ActGuia(int limite, String docu,int opc) {
        return objGuia.ActGuia(limite,docu,opc);
    }
    
    public int VerificaAnulacion(String idventa,String fechavta) {
        return objGuia.VerificaAnulacion(idventa, fechavta);
    }

    public List<Guias> RecuperaDetalleMovimiento(String fechaDocumento, String numeroDocumento, String tipoMovimiento) {
        return objGuia.RecuperaDetalleMovimiento(fechaDocumento, numeroDocumento, tipoMovimiento);
    }

    public void GenerarDBFEnvio(Guias entidadGuia, Producto entidadProductos) {
        objGuia.GenerarDBFEnvio(entidadGuia, entidadProductos);
    }

    public String GeneraNuevoNumero(String Movimiento, String almacen) {
        return objGuia.GeneraNuevoNumero(Movimiento, almacen);
    }
    public String RecuperaIdTipoOperacion(String Movimiento) {
        return objGuia.RecuperaTipoOperacion(Movimiento);
    }

    public List<Guias> RecuperaTodosLosMovimientos(int FILTRO, String idboti, String fecha1, String fecha2, String doc) {
        return objGuia.RecuperaTodosLosMovimientos(FILTRO, idboti, fecha1, fecha2, doc);
    }

    public List<Guias> RecuperaTodosLosMovimientos_Quim(int FILTRO, String idboti, String fecha1, String fecha2, String doc) {
        return objGuia.RecuperaTodosLosMovimientos_Quim(FILTRO, idboti, fecha1, fecha2, doc);
    }

    public Movimientos DescargarGuiaBotica(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo,double totalXguia, int opc){
        return objGuia.DescargarGuiaBotica(entidadGuia, Obns, lstDescargo,totalXguia, opc);
    }
    
    public Movimientos DescargarGuiaBoticaAdicional(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo,double totalXguia, int numult, int opc){
        return objGuia.DescargarGuiaBoticaAdicional(entidadGuia, Obns, lstDescargo,totalXguia,numult,opc);
    }
    
    public Movimientos DescargarGuiaClientes(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo,double totalXguia){
        return objGuia.DescargarGuiaClientes(entidadGuia, Obns, lstDescargo,totalXguia);
    }
    
    public Movimientos DescargarGuiaBoticaTrastienda(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo){
        return objGuia.DescargarGuiaBoticaTrastienda(entidadGuia, Obns, lstDescargo);
    }

    public boolean CargarGuiaBotica(Guias entidadGuia, String Obns, int tipo, List<Movimiento_Detalle> listProdCargo, int EsCar) {
        return objGuia.CargarGuiaBotica(entidadGuia, Obns, tipo, listProdCargo, EsCar);
    }

    public int DiasCargo(String idbotica, int idpersonal) {
        return objGuia.DiasCargo(idbotica, idpersonal);
    }
}
