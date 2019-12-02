package CapaLogica;

import CapaDatos.ProductoData;
import entidad.Descuento;
import entidad.Genericos;
import entidad.ListaDetalles;
import entidad.Producto;
import java.sql.SQLException;
import java.util.List;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.Tipo_Producto;
import entidad.Varios;
import entidad.Venta;
import entidad.CABECERA;

/**
 *
 * @author  Miguel Gomez S. :D
 */
public class LogicaProducto {

    ProductoData objproducto;

    public LogicaProducto() {
        objproducto = new ProductoData();
    }

    public List<ProductosPrecios> ListarProductos(String filtro, String idbotica, String tipprecio, int op, int opconstock) {
        return objproducto.ListarProductos(filtro, idbotica, tipprecio, op, opconstock);
    }
    public List<ProductosPrecios> Listardsctogratuita(int grupo, int op) {
        return objproducto.Listardsctogratuita(grupo, op);
    }
    public List<ProductosPrecios> ListarProductosINV(String filtro, String idbotica, String tipprecio, int op, int opconstock) {
        return objproducto.ListarProductos(filtro, idbotica, tipprecio, op, opconstock);
    }

    public List<ProductosPrecios> ListarProductosPromocionesPrecios(String filtro, String idbotica, String tipprecio, int op, int opconstock) {
        return objproducto.ListarProductosPromocionesPrecio(filtro, idbotica, tipprecio, op, opconstock);
    }
    
    public List<ProductosPrecios> ListarProductos_Detalle(String idfamilia, String idbotica, String tipprecio) throws SQLException {
        return objproducto.ListarProductos_Detalle(idfamilia, idbotica, tipprecio);
    }
    public List<ProductosPrecios> ListarDatosBotiquin(String idbotica, String prd) throws SQLException {
        return objproducto.ListarDatosBotiquin(idbotica, prd);
    }

    public List<ProductosPrecios> ListarProductos_Promocion(String idbotica, int idgrupo, int idpromocion) {
        return objproducto.ListarProductos_Promocion(idbotica, idgrupo, idpromocion);
    }

    public double VerificaIGVProducto(String idproducto) {
        return objproducto.VerificaIGVProducto(idproducto);
    }

    public boolean AgregaProductoEnvio(List<ProductosPrecios> Obj) throws SQLException {
        return objproducto.AgregaProductoEnvio(Obj);
    }

    public String ActualizaProductoEnvio(List<ProductosPrecios> ListaProducto, String IP, Integer fila) throws SQLException {
        return objproducto.ActualizaProductoEnvio(ListaProducto, IP, fila);
    }

    public List<ProductosPrecios> ListarProductosCarDes(String filtro, String IDBOTICA, int opcion) {
        return objproducto.ListarProductosCarDes(filtro, IDBOTICA, opcion);
    }
    
    public List<ProductosPrecios> ListarProductosCarDes1(String filtro, String IDBOTICA, int opcion, String tipo,int aux) {
        return objproducto.ListarProductosCarDes1(filtro, IDBOTICA, opcion,tipo, aux);
    }
    
    public String recTipoMOv(String filtro, String IDBOTICA, int opcion, String tipo, int aux) throws SQLException {
        return objproducto.RetornaCB(filtro, IDBOTICA, opcion,tipo, aux);
    }
    public String recnumero(int opc) throws SQLException {
        return objproducto.recnumero(opc);
    }
    
    public List<ProductosPrecios> ListarProductosSAP(String filtro, String IDBOTICA, int opcion, String SAPTIPO) {
        return objproducto.ListarProductosSAP(filtro, IDBOTICA, opcion, SAPTIPO);
    }
    public List<ProductosPrecios> ListarProductosSAPCargo(String filtro, String IDBOTICA, int opcion, String SAPTIPO) {
        return objproducto.ListarProductosSAPCargo(filtro, IDBOTICA, opcion, SAPTIPO);
    }
    public List<ProductosPrecios> ListarProductosCarDesPsicotropicos(String Busqueda,String IDBOTICA, int opcion, int cargar) {
        return objproducto.ListarProductosCarDesPsicotropicos(Busqueda,IDBOTICA,opcion,cargar);
    }

    public List<Productos_Botica> RecuperEmpaque(String IDProducto, String idbotica) {
        return objproducto.RecuperEmpaque(IDProducto, idbotica);
    }

    public List<Productos_Botica> RecuperEmpaque_Almacen(String IDProducto, String idbotica, String almacen) {
        return objproducto.RecuperEmpaque_Alamcen(IDProducto, idbotica, almacen);
    }

    public List<Varios> RecuperaStock(String CodPro, String Botica) {
        return objproducto.RecuperaStock(CodPro, Botica);
    }

    public List<Producto> ListarProductos(String idboti, String descrip) {
        return objproducto.ListarProductos(idboti, descrip);
    }

    public List<ListaDetalles> verificaPromocion(String IdProducto,int idpromo) {
        return objproducto.verificaPromocion(IdProducto,idpromo);
    }
    public List<ListaDetalles> verificaGrupoPromocion(String IdProducto) {
        return objproducto.verificaGrupoPromocion(IdProducto);
    }
    public List<ListaDetalles> verificaDatosCliente(String DocCliente,String V_RUCDNI) {
        return objproducto.verificaDatosGeneralesCliente(DocCliente,V_RUCDNI);
    }
    public List<ListaDetalles> verificaPromocionPrecio(String IdProducto) {
        return objproducto.verificaPromocionPrecio(IdProducto);
    }
    public Double Recupera_Dscto(String CodProducto, int i,int idprom,int flatmultiple) {
        return objproducto.Recupera_Dscto(CodProducto, i,idprom, flatmultiple);
    }
    

    public String Recupera_Promo_Nombre(String CodPRo) {
        return objproducto.Recupera_Promo_Nombre(CodPRo);
    }
    
    
    public String Recupera_Promo_Nombre_Multiple(String CodPRo,int opc) {
        return objproducto.Recupera_Promo_Nombre_Muiltiple(CodPRo,opc);
    }
    
    public int Recupera_EsBolsa(String CodPRo) {
        return objproducto.EsBolsa(CodPRo);
    }
    
    public Double Recupera_PrecioVenta(String CodPRo,int opc) {
        return objproducto.Recupera_PrecioVenta(CodPRo,opc);
    }
    
    public String Recupera_Promo_Nombredscto(String CodPRo) {
        return objproducto.Recupera_Promo_Nombredscto(CodPRo);
    }
    public String Recupera_Promo_NombrePrecio(String CodPRo) {
        return objproducto.Recupera_Promo_NombrePrecio(CodPRo);
    }

    public Object Recupera_Promo_Codigo(String CodPRo,int idprom,int EsMultiDcto) {
        return objproducto.Recupera_Promo_Codigo(CodPRo, idprom,EsMultiDcto);
    }
    public Object Recupera_Promo_Comprar(String CodPRo,int idpromo,String opc) {
        return objproducto.Recupera_Promo_Comprar(CodPRo,idpromo,opc);
    }
    
    public Object MarcaPromo(String CodPRo/*,String codpromo,int idpromo*/) {
        return objproducto.MarcarPromociones(CodPRo/*,codpromo,idpromo*/);
    }
    public Object Recupera_promodscto(String CodPRo,String botica, String tipoprecio,int opc) {
        return objproducto.Recupera_promodscto(CodPRo,botica,tipoprecio,opc);
    }
    public Object verificanuevocodigoPromocion(String CodPRo) {
        return objproducto.verificanuevocodigoPromocion(CodPRo);
    }
    public Object Recupera_Codigo_Promo_Aeliminar(String CodPRo) {//MAX_CODIGO
        return objproducto.RecuperaCodPromoAeliminar(CodPRo);
    }public Object Verifica_caja_And_Personal(String idpersonal, String idcaja) {//MAX_CODIGO
        return objproducto.Verifica_caja_And_Personal(idpersonal,idcaja);
    }


    public Object Recupera_Promo_CodigoPrecio() {
        return objproducto.Recupera_Promo_CodigoPrecio();
    }
    public Object Recupera_Moneda(String idventa,String Fechavta) {
        return objproducto.Recupera_Moneda(idventa,Fechavta);
    }
    public Object Recupera_Imprimible(String idventa,String botica) {
        return objproducto.Recupera_Imprimible(idventa,botica);
    }
    public Object Recupera_MensajeProducto(String ProductoCod) {
        return objproducto.Recupera_MensajeProducto(ProductoCod);
    }
    public Object Recupera_Poliza_Codigo() {
        return objproducto.Recupera_Poliza_Codigo();
    }

    public Double RecuperaPrecio(String CodProducto, String IDBOTICA) {
        return objproducto.RecuperaPrecio(CodProducto, IDBOTICA);
    }

    public Productos_Botica InformacionProducto(String idbotica, String idproduc) {
        return objproducto.InformacionProducto(idbotica, idproduc);
    }

    public List<ProductosPrecios> ListarProductos_PorLaboratorio(String filtro, String laboratorio, String idbotica, String tipprecio, int opconstock) {
        return objproducto.ListarProductos_PorLaboratorio(filtro, laboratorio, idbotica, tipprecio, opconstock);
    }

    public List<Genericos> Lista_Genericos(String filtro) {
        return objproducto.Lista_Genericos(filtro);
    }

    public List<ProductosPrecios> ListarProductos_Lab_Geneico(String filtro, String idbotica, String tipprecio, String generico) {
        return objproducto.ListarProductos_Lab_Geneico(filtro, idbotica, tipprecio, generico);
    }

    public Descuento Recupera_Porcen_Descuento(String idproducto) {
        return objproducto.Recupera_Porcen_Descuento(idproducto);
    }

    public Venta Recupera_Descuento_Producto(String idproducto, String idproforma, String idtipoprecio,String precio) {
        return objproducto.Recupera_Descuento_Producto(idproducto, idproforma, idtipoprecio,precio);
    }

    public double Recupera_Descuento_Producto(String idproducto, String idbotica) {
        return objproducto.Recupera_Descuento_Producto(idproducto, idbotica);
    }

    public double Recupera_Desct_Producto(String idproducto, String idbotica, String tipoprecio,String precio) {
        return objproducto.Recupera_Desct_Producto(idproducto, idbotica, tipoprecio,precio);
    }
    public String RetornaCantidadCompara(String tipovta) {
        return objproducto.RetornaCantidad_Compara(tipovta);
    }
    public String RetornaEsGratis(String producto) {
        return objproducto.RetornaEsGratis(producto);
    }

    public String RetornaTipoPrecio(String producto, int unid, int fracc, int opc) {
        return objproducto.RetornaTipoPrecio(producto,unid,fracc,opc);
    }
    public String RetornaNoMostrar(String producto) {
        return objproducto.RetornaNoMostrar(producto);
    }
    public String RetornaEsGratisPromo(String producto,String Promo) {
        return objproducto.RetornaEsGratisPromo(producto,Promo);
    }

    public String RetornaVerificaInterno(String interno, String monto,String fechadoc) {
        return objproducto.RetornaValidaInterno(interno,monto,fechadoc);
    }

    public String RetornaEmpaque(String botica,String producto) {
        return objproducto.RetornaEmpaque(botica,producto);
    }

    public int RetornaPersonalApertura(String dni, int caja) {
        return objproducto.RetornaValidaPersonal(dni,caja);
    }
    public int Essolofactura(int idcliente) {
        return objproducto.Essolofactura(idcliente);
    }

    public String RetornaPersonalModifica(String Proforma) {
        return objproducto.RetornaProformaModificada(Proforma);
    }

    public boolean validaPosibleDescargo(String CodigoProducto, Integer enterito, Integer fraccionado) {
        return objproducto.validaPosibleDescargo(CodigoProducto, enterito, fraccionado);
    }

    public int Recupera_Empaque(String idproducto) {
        return objproducto.Recupera_Empaque(idproducto);
    }
    public int Recupera_EsBotiquin(String idproducto,int opc) {
        return objproducto.Recupera_Empaque1(idproducto,opc);
    }
    public int Recupera_EsBlister(String idproducto,int opc) {
        return objproducto.Recupera_EsBlister(idproducto,opc);
    }
    
    public String Recupera_CantCB(String idproducto) {
        return objproducto.Recupera_CantCB(idproducto);
    }

    public List<Tipo_Producto> Listar_Tipo_Producto() {
        return objproducto.Listar_Tipo_Producto();
    }

    public void VaciarFS(int i) throws SQLException {
        objproducto.vaciarFaltantesSobrantes(i);
    }

    public boolean VerificaExisteCruce(String Producto_Padre, String Producto_Hembra) {
        return objproducto.VerificaExisteCruce(Producto_Padre, Producto_Hembra);
    }

    public void GuardarCombCruce(String Producto_Padre, String Producto_Hembra) {
        objproducto.GuardarCombCruce(Producto_Padre, Producto_Hembra);
    }    

    public List<Tipo_Producto> ListarTipoProducto() {
        return objproducto.Listar_Tipo_Producto();
    }

    public int Cantidad_Decimales() {
        return objproducto.CantidadDecimales();
    }

    public int CantidadDecimalesPantalla() {
        return objproducto.CantidadDecimalesPantalla();
    }
    
    public List<ListaDetalles> Muestra_Promociones(String IdProducto) {
        return objproducto.Muestra_Promociones(IdProducto);
    }
    public List<ListaDetalles> Muestra_PromocionesMultiple(String IdProducto) {
        return objproducto.Muestra_Promociones_Multiple(IdProducto);
    }

    public int Verifica_Promocion(String IdProducto/*,int idprom*/) {
        return objproducto.Verifica_Promocion(IdProducto/*,idprom*/);
    }
    public int Verifica_EsRecarga(String IdProducto) {
        return objproducto.Verifica_EsRecarga(IdProducto);
    }
    public String Recupera_OperadorRecarga(String idproforma,String IdProducto) {
        return objproducto.Recupera_OperadorRecarga(idproforma,IdProducto);
    }
    public int Verifica_PromocionPrecio(String IdProducto) {
        return objproducto.Verifica_PromocionPrecio(IdProducto);
    }
    public List<ProductosPrecios> Lista_Kardex(String filtro, String IDBOTICA, int opcion) {
        return objproducto.Lista_Kardex(filtro, IDBOTICA, opcion);
    }
    public int Verifica_Es_Manual(String CodProducto) {
        return objproducto.Verifica_Es_Manual(CodProducto);
    }
    public int Verifica_Cantidad_Producto(String CodProducto) {
        return objproducto.Verifica_Cantidad_Producto(CodProducto);
    }
    public int Verifica_Es_Promo_Frac(String CodProducto) {
        return objproducto.Verifica_Es_Promo_Frac(CodProducto);
    }
    public int Verifica_Es_Mitad_Gratis(String CodProducto) {
        return objproducto.Verifica_Es_Mitad_Gratis(CodProducto);
    }
    public int esPromoEmpFraccion(String CodProducto) {//MAX_CODIGO
        return objproducto.esPromoEmpFraccion(CodProducto);
    }

    public int Verifica_Promo_PorPrecio(String CodProducto, int opc,int idpromo) {
        return objproducto.Verifica_Promo_PorPrecio(CodProducto,opc, idpromo);
    }    

    public int Verifica_Promo_PorPrecio1(String CodProducto,String cupon, int opc) {
        return objproducto.Verifica_Promo_PorPrecio1(CodProducto,cupon,opc);
    }

    public double Dscto_Promo_PorPrecio(String idproducto,int opc) {
        return objproducto.Dscto_Promo_PorPrecio(idproducto,opc);
    }
    public List<ProductosPrecios> ListarDatos(List<CABECERA> listaXml,String valor) {
        return objproducto.ListarDatos(listaXml,valor);
    }
    public String RetornaDato(String campo,String idventa,String IDBOTI,String opc) {
        return objproducto.RetornaCampo(campo,idventa,IDBOTI,opc);
    }
    public String RetornaDatoNC(String campo,String idventa,String IDBOTI,String opc) {
        return objproducto.RetornaCampoNC(campo,idventa,IDBOTI,opc);
    }
    public String RetornaDatoND(String campo,String idventa,String IDBOTI,String opc) {
        return objproducto.RetornaCampoND(campo,idventa,IDBOTI,opc);
    }
    public String RetornaDatoCabecera(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {
        return objproducto.RetornaCampoCabecera(campo,idventa,IDBOTI,TOTALTEXTO);
    }
    public String RetornaDatoCabeceraNC(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {
        return objproducto.RetornaCampoCabeceraNC(campo,idventa,IDBOTI,TOTALTEXTO);
    }
    public String RetornaDatoCabeceraND(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {
        return objproducto.RetornaCampoCabeceraND(campo,idventa,IDBOTI,TOTALTEXTO);
    }
    public String RetornaDatoDetalle(String campo,String idventa,String IDBOTI) {
        return objproducto.RetornaCampoDetalle(campo,idventa,IDBOTI);
    }
    public String RetornaDatoDetalleNC(String campo,String idventa,String IDBOTI) {
        return objproducto.RetornaCampoDetalleNC(campo,idventa,IDBOTI);
    }
    public String RetornaDatoDetalleND(String campo,String idventa,String IDBOTI) {
        return objproducto.RetornaCampoDetalleND(campo,idventa,IDBOTI);
    }
    public int[]  PromoEstaEnDosGrupos(String IdProducto,int idpromo) {//MAX_CODIGO
        return objproducto.PromoEstaEnDosGrupos(IdProducto,idpromo);//MAX_CODIGO
    }
    public int dsctogratuita(int idgrup,int opc) {
        return objproducto.dsctogratuita(idgrup,opc);
    }
    public int G_EsMultipleDscto(int idgrup,String codpromo,int opc) {
        return objproducto.G_EsMultipleDscto(idgrup,codpromo,opc);
    }
    public int G_EsPsicotropico(String codpromo,int opc) {
        return objproducto.G_EsPsicotropico(codpromo,opc);
    }
    public int EsICBPER(String codprod) {
        return objproducto.G_EsICBPER(codprod);
    }
    public int CANTBOLSA(String prof) {
        return objproducto.C_BOLSA(prof);
    }
    public String RetornaLab(String codprod) {
        return objproducto.RetornaLab(codprod);
    }
    public double PrecioICBPER(String codprod) {
        return objproducto.Precio_EsICBPER(codprod);
    }
    public double PrecioICBPEREMP(String codprod) {
        return objproducto.Precio_EsICBPEREMP(codprod);
    }
    public int MostrarPantallaMultiple(String idProd,int opc) {
        return objproducto.MostrarPantallaMultiple(idProd,opc);
    }
    public int dsctocant(String IdProducto) {
        return objproducto.dsctocant(IdProducto);//MAX_CODIGO
    }

    public int  VerificaEsGratuita(String IdProducto) {//MAX_CODIGO
        return objproducto.Verifica_Es_Gratuita(IdProducto);//MAX_CODIGO
    }
}
