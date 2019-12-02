package CapaLogica;

import CapaDatos.TipoDocumentoData;
import CapaDatos.VentaData;
import entidad.Cajas;
import entidad.Detalle_VentaDelivery;
import entidad.Movimiento_Detalle;
import entidad.NotaCredito;
import entidad.Proforma;
import entidad.ResultadoVenta;
import entidad.TipoCambio;
import entidad.TipoDocumento;
import entidad.Venta;
import entidad.Venta_Delivery;
import entidad.Ventas_Tipo_Pago;
import java.util.ArrayList;
import java.util.List;

public class LogicaVenta {

    VentaData objventa;
    TipoDocumentoData objtipoventas;    

    public LogicaVenta() {
        objventa = new VentaData();
        objtipoventas = new TipoDocumentoData();        
    }

    public List<Venta> Lista_Internos_Ventas(Venta obj, int op) {
        return objventa.Lista_Internos_Ventas(obj, op);
    }
    public List<Venta> Lista_guiasajuste(Venta obj) {
        return objventa.Lista_guiasajuste(obj);
    }


    public List<Venta> ReimprimeLista_Internos_Ventas(Venta obj, int op) {
        return objventa.ReimprimeLista_Internos_Ventas(obj, op);
    }
    public List<Venta> Lista_Datos_Ventas(Venta obj) {
        return objventa.Lista_Datos_Ventas(obj);
    }

    public List<Venta> Lista_Internos_VentasAnuladas(Venta obj, int op) {
        return objventa.Lista_Internos_VentasAnuladas(obj, op);
    }
    public List<Venta> Lista_Descargo(Venta obj) {
        return objventa.Lista_Descargos_Inv(obj);
    }
    public List<Venta> Lista_Internos_Ventas_Delivery(Venta obj, int op) {
        return objventa.Lista_Internos_Ventas_Delivery(obj, op);
    }

    public List<Venta> Lista_Internos_Ventas_SOAT(Venta obj, int op) {
        return objventa.Lista_Internos_Ventas_SOAT(obj, op);
    }
    
    public List<Venta> Busca_Internos_Movimientos(Venta objvent, int op) {
        return objventa.Busca_Internos_Movimientos(objvent, op);
    }

    public List<Venta> Lista_Mostrar_Cargos(Venta obj) {
        return objventa.Lista_Mostrar_Cargos(obj);
    }

    public List<Venta> Lista_Int_Ventas_Fecha(Venta obj) {
        return objventa.Lista_Int_Ventas_Fecha(obj);
    }
    public List<Venta> Lista_EnviosEfectivo(Venta obj) {
        return objventa.Lista_EnviosOficina(obj);
    }

    public List<Venta> ListaEnvioEfectivo_Detalle(Venta objvta) {
        return objventa.ListaEnvioEfectivo_Detalle(objvta);
    }
    public List<Venta> ListaVenta_Detalle(Venta objvta) {
        return objventa.ListaVenta_Detalle(objvta);
    }

    public List<Venta> ListaGuia_Detalle(String idguia,String serie, String numero) {
        return objventa.ListaGuia_Detalle(idguia, serie,  numero);
    }
    public List<Venta> ReimprimeListaVenta_Detalle(Venta objvta) {
        return objventa.ReimprimeListaVenta_Detalle(objvta);
    }
    public List<Venta> ListaVenta_DetalleND(Venta objvta) {
        return objventa.ListaVenta_DetalleND(objvta);
    }
    public List<Venta> ListaAnulaEnvioEfectivo(Venta objvta) {
        return objventa.ListaAnulaEnvioEfectivo(objvta);
    }

    /**********************************************
     *
     * Programador  : Gino Paredes Zurita
     * Fecha        : 16-11-2013
     * Modulo       : Pre-Anulados
     * Tipo         : NUEVO MODULO - FormPreAnulacionInterno.java
     *
     ***********************************************/
    public List<Venta> ListaVenta_Detalle_pre(Venta objvta) {
        return objventa.ListaVenta_Detalle_pre(objvta);
    }
    public boolean PreAnula(String Botica, String interno, String var, int usuario) {
        return objtipoventas.PreAnula(Botica, interno, var, usuario);
    }
    /*
     * fin
     */
    
    public boolean InsertaNotaCredito(Venta obj, List<Venta> lista, double auxigv,double opinafecta, double OpDescuentoCabecera,double OpGratuitoCabecera,String money,String OPC,String cmbObs) {
        return objventa.InsertaNotaCredito(obj, lista,auxigv,opinafecta,OpDescuentoCabecera,OpGratuitoCabecera,money,OPC,cmbObs);
    }

    public boolean InsertaTmpDetalleNC(Venta obj, List<Venta> lista, String InternoVta,String Opc) {
        return objventa.InsertaTmpDetalleNC(obj, lista, InternoVta, Opc);
    }

    public List<NotaCredito> listaNotaCredito(String numdocumento, String idbotica) {
        return objventa.listaNotaCredito(numdocumento, idbotica);
    }

    public ResultadoVenta GuardarVenta(Venta obj, List<Venta> listasinigv, List<Venta> lista, List<Ventas_Tipo_Pago> lista_pagos, Proforma EntidadProforma, String VarPoliza,String Moneda, String numbercupon,
            double LeerRedondeo,double LeerTotFinal) {
        return objventa.GuardarVenta(obj, listasinigv, lista, lista_pagos, EntidadProforma, VarPoliza,Moneda,numbercupon,LeerRedondeo,LeerTotFinal);
    }

    public List<TipoDocumento> ListarTipoDocumento() {
        return objtipoventas.ListarTipoDocumento();
    }
    public List<TipoDocumento> ListarTipoDocumentoReimprime() {
        return objtipoventas.ListarTipoDocumentoReimprime();
    }

    public void ActualizarTipoDocumento(String antiguo, String Nuevo) {
        objtipoventas.ActualizarTipoDocumento(antiguo, Nuevo);
    }

    public void AgregarTipoDocumentoVenta(String Nuevo) {
        objtipoventas.AgregarTipoDocumentoVenta(Nuevo);
    }

    public void EliminarTipoDocumentoVenta(String Antiguo) {
        objtipoventas.EliminarTipoDocumentoVenta(Antiguo);
    }

    public boolean Activa(String Botica, String Documento, int idpersonal, String observacion, String fechavta) {
        return objtipoventas.Activa(Botica, Documento, idpersonal, observacion,fechavta);
    }
    public boolean Anula(String Botica, String Documento, int idpersonal, String observacion) {
        return objtipoventas.Anula(Botica, Documento, idpersonal, observacion);
    }

    public ResultadoVenta AnulaCargoDescargo(String Botica, String TipoMovimiento, String Documento, String Personal, String almacen, String proveedor, String Observacion, String movi) {
        return objtipoventas.AnulaCargoDescargo(Botica, TipoMovimiento, Documento, Personal, almacen, proveedor,Observacion,movi);
    }

    public List<Venta> Lista_Tipos_Pagos_Venta(Venta objven) {
        return objventa.Lista_Tipos_Pagos_Venta(objven);
    }

    public boolean EsCredito(int tipventa) {
        return objventa.EsCredito(tipventa);
    }

    public List<Venta> REPORTE_CAJA_SUBDETALLE(Venta obj) {
        return objventa.REPORTE_CAJA_SUBDETALLE(obj);
    }

    public List<Venta> REPORTE_CAJA_TIPOS_PAGOS(Venta objve) {
        return objventa.REPORTE_CAJA_TIPOS_PAGOS(objve);
    }

    public int EliminaCorrelativos(Venta obj) {
        return objventa.EliminaCorrelativos(obj);
    }

    public List<String> Lista_Bancos() {
        return objventa.Lista_Bancos();
    }

    public List<Venta> ListaMovimiento_Detalle(Venta obj, String docu) {
        return objventa.ListaMovimiento_Detalle(obj, docu);
    }

    public int ObtieneTipoVenta(String Interno) {
        return objventa.ObtieneTipoVenta(Interno);

    }

    public int ObtieneValidaHay(String Interno) {
        return objventa.ObtieneValidaHay(Interno);
    }

    public int Devuelve_Tpo_Venta(String Interno) {
        return objventa.Devuelve_Tpo_Venta(Interno);
    }

    public void Imprime_Boleta(String interno, String idbotica) {
        objventa.Imprime_Boleta(interno, idbotica);
    }

    public void Imprime_Factura(String interno, String idbotica) {
        objventa.Imprime_Factura(interno, idbotica);
    }
    public void ReImprime_Factura(List<Venta> listaInternos, String idbotica) {
        objventa.ReImprime_Factura(listaInternos,idbotica);
    }
   
    public boolean ReImpresion_Ticketera(List<Venta> listaInternos, String idbotica, int tipventa) {
        return objventa.ReImpresion_Ticketera(listaInternos, idbotica, tipventa);
        
    }

    public boolean Verifica_Venta_Botiquin(Venta obj) {
        return objventa.Verifica_Venta_Botiquin(obj);
    }
    public boolean Verifica_Venta_EsSAP(Venta obj) {
        return objventa.Verifica_Venta_EsSAP(obj);
    }

    public List<Venta> Lista_Ventas_Facturas(Venta p) {
        return objventa.Lista_Ventas_Facturas(p);
    }

    public List<TipoCambio> Retorna_Tipos_Cambios() {
        return objventa.Retorna_Tipos_Cambios();
    }
    public List<TipoCambio> Retorna_Moneda() {
        return objventa.Retorna_Moneda();
    }

    public boolean Ingresa_Tipo_Cambio(double tipcambio, String idbotica) {
        return objventa.Ingresa_Tipo_Cambio(tipcambio, idbotica);
    }

    public boolean Verifica_Ingreso_TipoCambio() {
        return objventa.Verifica_Ingreso_TipoCambio();
    }

    public boolean Corrige_Numeracion(Venta obj) {
        return objventa.Corrige_Numeracion(obj);
    }

    public List<Venta> Muestra_Ventas_Anuladas_RESP(String idbotica, String fecha) {
        return objventa.Muestra_Ventas_Anuladas_RESP(idbotica, fecha);
    }

    public boolean Revisa_Ventas_Anuladas(List<Venta> obj) {
        return objventa.Revisa_Ventas_Anuladas(obj);
    }

    public List<Venta> Verifica_Ventas_Salida(String idbotica, String idproforma) {
        return objventa.Verifica_Ventas_Salida(idbotica, idproforma);
    }

    public boolean Despachar_Venta(String idventa, String idbotica, String idproforma) {
        return objventa.Despachar_Venta(idventa, idbotica, idproforma);
    }

    public String Recupera_Interno(String idbotica) {
        return objventa.Recupera_Interno(idbotica);
    }
    public String Recupera_FacturaOK(String IdBotica,String idventa, String fechavta) {
        return objventa.Recupera_InternoOK(IdBotica,idventa,fechavta);
    }
    public String Recupera_Observacion(String IdBotica,String serie, String numero) {
        return objventa.Recupera_Obs(IdBotica,serie,numero);
    }
    public String Recupera_Caja(String IdBotica,String idventa, String fechavta,String id_peronal) {
        return objventa.Recupera_Caja(IdBotica,idventa,fechavta,id_peronal);
    }
    public String Recupera_BotNCXCAJA(String IdBotica) {
        return objventa.Recupera_BotNCXCAJA(IdBotica);
    }
    public String UpdateVtaDsctoGlobal(String interno,double totalModif,double subtotalModif, double igvModif,double DsctoGlobal) {
        return objventa.UpdateVtaDsctoGlobal(interno,totalModif,subtotalModif, igvModif,DsctoGlobal);
    }
    
    public String actualizaguiaajustedet(List<Movimiento_Detalle> lstdetguiajuste,String Tserie,String Tnumero,String opc) {
        return objventa.actualizaguiaajustedet(lstdetguiajuste,Tserie,Tnumero,opc);
    }


    public String recuperainternoNC(String serie,String numero, String Fecha, String tipovta) {
        return objventa.recuperainternoNC(serie, numero,  Fecha, tipovta);
    }
    public String recuperaImpresionDouble() {
        return objventa.recuperaImpresionDouble();
    }
    public String recuperaImpresionDoubleNC() {
        return objventa.recuperaImpresionDoubleNC();
    }

    public List<Venta> Lista_Cuadres_Cajas(String idbotica, String fecha) {
        return objventa.Lista_Cuadres_Cajas(idbotica, fecha);
    }

    public List<Venta> Lista_Movimeintos_Caja(Venta p) {
        return objventa.Lista_Movimeintos_Caja(p);
    }

    public List<Venta> Busca_Movimientos_Caja(Venta p, String idventa) {
        return objventa.Busca_Movimientos_Caja(p, idventa);
    }

    public int Modificar_Tipo_Pagos(Venta entidad, List<Ventas_Tipo_Pago> lista_pagos) {
        return objventa.Modificar_Tipo_Pagos(entidad, lista_pagos);
    }

    public boolean Cierre_Revision_Caja(Venta entidad) {
        return objventa.Cierre_Revision_Caja(entidad);
    }

    public String RetornaEmpaque(String botica,String producto) {
        return objventa.RetornaEmpaque(botica,producto);
    }
    public double VerificaIGVProducto(String idproducto) {
        return objventa.VerificaIGVProducto(idproducto);
    }   
    
    public boolean Existe_Serie_Numero(Venta venta) {
        return objventa.Existe_Serie_Numero(venta);
    }

    public double Recupera_MontoVenta(Cajas caja) {
        return objventa.Recupera_MontoVenta(caja);
    }

    public String Recupera_DocumentoSalida(String idbotica) {
        return objventa.Recupera_DocumentoSalida(idbotica);
    }

    public String RetornaCantidadCompara(String tipovta) {
        return objventa.RetornaCantidad_Compara(tipovta);
    }

    public Venta_Delivery GuardaSalida(Venta_Delivery obj, List<String> Internos) {
        return objventa.GuardaSalida(obj, Internos);
    }

    public List<Venta_Delivery> Lista_Salidas_Delivery(String idbotica) {
        return objventa.Lista_Salidas_Delivery(idbotica);
    }

    public List<Detalle_VentaDelivery> Detallle_Salidas_Delivery(String idbotica, String salida) {
        return objventa.Detallle_Salidas_Delivery(idbotica, salida);
    }

    public boolean Actualiza_EntradaDelivery(String idbotica, String docnum, double vuelto, String observ) {
        return objventa.Actualiza_EntradaDelivery(idbotica, docnum, vuelto, observ);
    }
    public ArrayList<Venta> Lista_Internos_Para_Ndebito(String IdProducto){
        return objventa.Lista_Internos_Para_Ndebito(IdProducto);
    }
}
