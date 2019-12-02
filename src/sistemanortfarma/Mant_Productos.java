/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanortfarma;

import CapaLogica.LogicaIGV;
import CapaLogica.LogicaProducto;
import entidad.ListaDetalles;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class Mant_Productos implements Productos {

    Cotizacion obcoiza = null;
    static RealizaVenta objventa = null;
    static BuscarProductos objBusqueda = null;
    private double IGV = 0;
    LogicaIGV objlogicaIGV = new LogicaIGV();
    LogicaProducto objProducto = new LogicaProducto();

    public Mant_Productos() {
    }

    public BuscarProductos getObjBusqueda() {
        return objBusqueda;
    }

    public void setObjBusqueda(BuscarProductos objBusqueda) {
        this.objBusqueda = objBusqueda;
    }

    public static RealizaVenta getObjventa() {
        return objventa;
    }

    public static void setObjventa(RealizaVenta objventa) {
        Mant_Productos.objventa = objventa;
    }

    @Override
    public double Captura_IGV() {
        try {
            IGV = objlogicaIGV.RecuperaIGV();
        } catch (Exception ex) {
            System.out.println("ERROR AL RECUPERRAR EL IGV :" + ex.toString());
        }

        return IGV;
    }

    //Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    @Override
    public double recupera_Igv_Exonerado(String idproducto) {
        return objlogicaIGV.recupera_Igv_Exonerado(idproducto);
    }

    //@Override
    public List<ListaDetalles> verificaPromocion(String idproducto,int idpromo) {
        return objProducto.verificaPromocion(idproducto,idpromo);
    }

    public List<ListaDetalles> verificaPromocion1(String idproducto,int idpromo) {
        return objProducto.verificaPromocion(idproducto,idpromo);
    }
    //@Override
    public List<ListaDetalles> verificaGrupoPromocion(String idproducto) {
        return objProducto.verificaGrupoPromocion(idproducto);
    }
    //@Override
    public List<ListaDetalles> verificaDatosCliente(String DocCliente,String V_RUCDNI) {
        return objProducto.verificaDatosCliente(DocCliente,V_RUCDNI);
    }

    //@Override
    public List<ListaDetalles> verificaPromocionPrecio(String idproducto) {
        return objProducto.verificaPromocionPrecio(idproducto);
    }

//    @Override
    public Object Recupera_Promo_Codigo(String CodPRo,int idprom,int EsMultiDcto) {
        return objProducto.Recupera_Promo_Codigo(CodPRo, idprom, EsMultiDcto);
    }
    public Object Recupera_Promo_Codigo1(String CodPRo,int idprom) {
        return objProducto.Recupera_Promo_Codigo(CodPRo, idprom,0);
    }

    public Object Recupera_Promo_Comprar(String CodPRo,int idpromo, String opc) {
        return objProducto.Recupera_Promo_Comprar(CodPRo,idpromo,opc);
    }
    
    public Object MarcarPromo(String CodPRo/*,String codpromocion,int idpromo*/) {
        return objProducto.MarcaPromo(CodPRo/*,codpromocion,idpromo*/);
    }

    public Object Recupera_promodscto(String CodPRo,String botica, String tipoprecio,int opc) {
        return objProducto.Recupera_promodscto(CodPRo,botica,tipoprecio,opc);
    }

    public Object NuevocodigoPromocion(String CodPRo) {
        return objProducto.verificanuevocodigoPromocion(CodPRo);
    }

     public Object Recupera_Codigo_Promo_Aeliminar(String CodPRo) {//MAX_CODIGO
        return objProducto.Recupera_Codigo_Promo_Aeliminar(CodPRo);
    }public Object Verifica_caja_And_Personal(String idpersonal, String idcaja) {//MAX_CODIGO
        return objProducto.Verifica_caja_And_Personal(idpersonal,idcaja);
    }


    public Object Recupera_Promo_CodigoPrecio() {
        return objProducto.Recupera_Promo_CodigoPrecio();
    }
    public Object Recupera_Moneda(String idventa,String Fechavta) {
        return objProducto.Recupera_Moneda(idventa,Fechavta);
    }

    public Object Recupera_Imprimible(String idventa,String botica) {
        return objProducto.Recupera_Imprimible(idventa,botica);
    }

    public Object Recupera_MensajeProducto(String ProductoCod) {
        return objProducto.Recupera_MensajeProducto(ProductoCod);
    }
    public Object Recupera_Poliza() {
        return objProducto.Recupera_Poliza_Codigo();
    }

    @Override
    public Object Recupera_Promo_Nombre(String CodPRo) {
        return objProducto.Recupera_Promo_Nombre(CodPRo);
    }
    
    
    public Object Recupera_Promo_NombreMultiple(String CodPRo,int opc) {
        return objProducto.Recupera_Promo_Nombre_Multiple(CodPRo,opc);
    }
    
    public Object Recupera_Promo_Nombredscto(String CodPRo) {
        return objProducto.Recupera_Promo_Nombredscto(CodPRo);
    }


    public Object Recupera_Promo_NombrePrecio(String CodPRo) {
        return objProducto.Recupera_Promo_NombrePrecio(CodPRo);
    }

    @Override
    public int Recupera_Empaque(String codpro) {
        return objProducto.Recupera_Empaque(codpro);
    }   
    
    
    public int Recupera_EsBotiquin(String codpro,int opc) {
        return objProducto.Recupera_EsBotiquin(codpro,opc);
    }
    public int Recupera_EsBlister(String codpro,int opc) {
        return objProducto.Recupera_EsBlister(codpro,opc);
    }
    
    public double recupera_PrecBotiquin(String idproducto,int opc) {
        return objlogicaIGV.recupera_PrecBotiquin(idproducto,opc);
    }

    @Override
    public int cantidadDecimales() {
        return objProducto.Cantidad_Decimales();
    }
    public int CantidadDecimalesPantalla() {
        return objProducto.CantidadDecimalesPantalla();
    }

    @Override
    public List<ListaDetalles> verificaPromocion(String idproducto) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public Object Recupera_Promo_Codigo(String CodPRo) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return objProducto.Recupera_Promo_Codigo(CodPRo, idprom);
    }
}
