package CapaDatos;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaIGV;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import entidad.Cajas;
import CapaLogica.LogicaGuias;
import entidad.Detalle_VentaDelivery;
import entidad.NotaCredito;
import entidad.Personal;
import entidad.Proforma;
import entidad.ResultadoVenta;
import CapaLogica.XML_GENERAR;
import entidad.TipoCambio;
import entidad.Venta;
import entidad.Venta_Delivery;
import entidad.Ventas_Tipo_Pago;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.jcraft.jsch.JSch.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sistemanortfarma.FormImpresora;
import sistemanortfarma.MailClient;
import sistemanortfarma.OpcionesMenu;
//import CapaLogica.LogicaProducto;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import CapaLogica.LogicaTipoMovimiento;
import com.lowagie.text.Font;
import entidad.Movimiento_Detalle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.text.NumberFormat;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemanortfarma.Mant_Productos;
import sistemanortfarma.VerificaSistema;

/*import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.sourceforge.jbarcodebean.BarcodeException;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Interleaved25;


import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import javax.print.attribute.standard.Copies;*/


/**
 *
 * @author Miguel Gomez S. 
 */
public class VentaData {

    Connection conex;
    Connection conex2;
    ResultadoVenta objresultado;
    XML_GENERAR objXML_GENERAR;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    private ConexionPool db;
    List<Venta> listaParametros;
    List<Venta> listaDatosVtas;
    ResultSet rs, rs1, rs2, rs3, rsigv, rsvalida, rs4;
    Statement stm;
    private String cant;
    private String tipo;    
    private String empaque;
    List<Venta> listventa = new ArrayList<Venta>();
    Mant_Productos mantProduc = new Mant_Productos();
    //LogicaProducto objProducto = new LogicaProducto();
    List<Venta> listVentas = new ArrayList<Venta>();
    List<Venta> listaInternos = new ArrayList<Venta>();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    List<Integer> lisunidad = new ArrayList<Integer>();
    //LogicaGuias logFirma = new LogicaGuias();
    List<Integer> listfraccion = new ArrayList<Integer>();
    List<Venta> lista_VentaFinal = new ArrayList<Venta>();
    LogicaIGV objlogicaIGV = new LogicaIGV();
    MailClient objmail = new MailClient();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    LogicaCPersonal logPersonal = new LogicaCPersonal();
    double mayor, menor, acumula, inserciones;
    double continsertar = 0;
    private String idbotica;
    OpcionesMenu obj;
    private String Impresora_Boleta = obj.getImpresora_Boleta();
    private String Impresora_Factura = obj.getImpresora_Factura();
    private double MIIGV;
    private String validavta;
    int podecimal = 4;
    int podecimalPantalla = 2;
    JFrame ventana;
    private FileInputStream fileInputStream = null;

    Map parameters = new HashMap();
    VerificaSistema obj1;
    URL report_file;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;

    public VentaData() {
        db = new ConexionPool();
    }

    public int Cantidad_Productos(int tipventa) {
        int cant = 0;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_ITEM_IMPRESION ('" + tipventa + "') }");
            CallableStatement procedure1 = conex.prepareCall("{ call RETORNA_IGV }");
            rs1 = procedure1.executeQuery();
            rs1.next();
            MIIGV = rs1.getDouble("IGV");
            rs = procedure.executeQuery();
            rs.next();
            cant = rs.getInt("Items_Impresion");
            rs.close();
            rs1.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cant;
    }

    public ResultadoVenta GuardarVenta(Venta obj, List<Venta> listasinigv, List<Venta> lista, List<Ventas_Tipo_Pago> lista_pagos, Proforma EntidadProforma, String PolizaCod, String Moneda, String numbercupon,
           double LeerRedondeo,double LeerTotFinal) {

        double cantidad = 0;
        int ind = 0, posini = 0, posfin = 0,idOperadorRec=0;
        double total = 0.0, SubTotal = 0.0, resto = 0,igvporcentaje=0.0,opgravada=0.0,opexonerada=0.0,
                opinafecta=0.0,opgratuita=0.0,opisc=0.0,OpBonificacion=0.0,OpDescuentoTotal = 0.0,OpDescuento=0.0,OpDescuento_aux=0.0,OpDescuento_aux_Detalle=0.0,preciounitario=0.0,
                opgravadaDetalle=0.0,opexoneradaDetalle=0.0,opinafectaDetalle =0.0,opiscDetalle=0.0,OpBonificacionDetalle =0.0,
                opDescuentoDetalle=0.0,opgratuitaDetalle,opdctoSuma=0.0,opdctoSuma1=0.0,OpGratuitaTotal = 0.0,opinafecta1=0.0;

        int cantsinigv = listasinigv.size();
        double tamano = lista.size();
        int tipventa = obj.getId_Tipo_Venta();
        //String vueltoT = obj.getvueltoT();
        double vueltoT = Double.parseDouble(obj.getvueltoT());
        double montoPagoT = Double.parseDouble(obj.getmontoPagoT()); 
        CallableStatement procedure = null, procedure1 = null, procedure2 = null,procedure3 = null;
        int cantpag = lista_pagos.size();
        int idipopago = obj.getId_TipoPago();
        //int idOperadorRecarga = lista.
        idbotica = obj.getId_Botica();
        String numero = "";
        String Cliente ="";
        PreparedStatement st1 = null, st2 = null;
        boolean varios = false;
        int Cantidad_En_Empaque = 0;
        int Almacen_Stock_Empaque = 0;
        int Almacen_Stock_Fraccion = 0;
        int trastienda_Stock_Empaque = 0;
        int trastienda_Stock_Fraccion = 0;
        listaInternos.removeAll(listaInternos);
        String Id_TipoAlmacen = "";
        String Id_TipoMovimiento = "";
        String Id_Proveedor = "";
        int marcado = 0;
        boolean espositiva = false;
        String NumeroProforma = null;
        podecimal = OpcionesMenu.getCantidadDecimales();
        int esImprimible = 0;
        String idtipdocsunat="";
        String fechavta="";
        String VarPoliza = PolizaCod;
        String MonedaFinal = Moneda;
        


        if (cantpag > 1) //SI HAY MAS DE UN TIPO DE PAGO
        {
            mayor = lista_pagos.get(0).getMonto();
            menor = lista_pagos.get(1).getMonto();

            if (menor > mayor) {
                mayor = menor;
                menor = lista_pagos.get(0).getMonto();
                ind = 1;
            }
            varios = true;
        }


        cantidad = Cantidad_Productos(tipventa);     // recupera cantidad si es boleta (6)  -  2 si es factura
        inserciones = Math.ceil(cantsinigv / cantidad);
        posfin = (int) (cantidad - 1);

        /*
         * INSERTO LOS PRODUCTOS CON IGV
         */

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
      
            lisunidad.removeAll(lisunidad);
            listfraccion.removeAll(listfraccion);
            listasinigv.removeAll(listasinigv);

            posini = 0;
            posfin = (int) (cantidad - 1);

            /*
             * RECUPERO LOS DAOS PARA LOS PRODUCTOS CON IGV
             */

            inserciones = Math.ceil(tamano / cantidad);

            if (inserciones > 0) {

                List<Venta> listaverifica = new ArrayList<Venta>();
                listaverifica = lista;                
               

                cantsinigv = 0;
                inserciones = 0;
                int auxorden = -1;

                List<Object> ordenimpresion;
                ordenimpresion = new ArrayList<Object>();
                int maxitems = -1;
                Object[] itemprint = null;


                //if (tipventa == 9){
                if (tipventa == 11 || tipventa == 12 || tipventa == 7){
                    for (int x = 0; x < listaverifica.size(); x++) {
                        Venta item = listaverifica.get(x);
                        int ovta = Integer.parseInt(listaverifica.get(x).getOrden());
                        item.setOrden("0"); 
                        item.setOrdenVta(ovta);
                        listaverifica.set(x, item);
                    }
                }

                for (int x = 0; x < listaverifica.size(); x++) {
                    int orden = Integer.parseInt(listaverifica.get(x).getOrden());
                    if (auxorden != orden) {
                        maxitems = 1;
                        itemprint = new Object[2];
                        auxorden = orden;
                        inserciones = inserciones + 1;
                        itemprint[0] = inserciones;
                        itemprint[1] = maxitems;
                        ordenimpresion.add(itemprint);

                    } else {
                        maxitems = maxitems + 1;
                        itemprint[1] = maxitems;
                        int info = (int) inserciones - 1;
                        ordenimpresion.set(info, itemprint);
                    }
                }

                int PosicionInicial = 0;
                int PosicionFinal = 0;
                int IdxPosicion = 1;
                List<Object> LstPrintFinal = new ArrayList<Object>();
                Object[] itemprintfinal = null;

                for (int y = 0; y < ordenimpresion.size(); y++) {

                    Object[] valor = new Object[1];
                    valor[0] = ordenimpresion.get(y);

                    Object[] datos = new Object[2];
                    datos = (Object[]) valor[0];

                    if (Double.parseDouble(datos[1].toString()) > cantidad) {
                        PosicionInicial = 0;
                        PosicionFinal = -1;
                        double tamanoprint = 0;
                        tamanoprint = Math.ceil(Double.parseDouble(datos[1].toString()) / cantidad);

                        if (tamanoprint > 1) {
                            for (double z = 0; z < tamanoprint; z++) {
                                itemprintfinal = new Object[3];
                                itemprintfinal[0] = IdxPosicion;

                                if (z < tamanoprint - 1) {
                                    PosicionFinal = PosicionFinal + (int) cantidad ;
                                    itemprintfinal[1] = PosicionInicial;
                                    itemprintfinal[2] = PosicionFinal;

                                } else {
                                    PosicionFinal = (int) Integer.parseInt(datos[1].toString()) - 1;
                                    itemprintfinal[1] = PosicionInicial;
                                    itemprintfinal[2] = PosicionFinal;

                                }

                                LstPrintFinal.add(itemprintfinal);
                                IdxPosicion = IdxPosicion + 1;
                                PosicionInicial = PosicionFinal + 1;
                                //itemprintfinal = null;
                            }
                        }

                    } else {
                        PosicionFinal = PosicionInicial + Integer.parseInt(datos[1].toString()) - 1;
                        itemprintfinal = new Object[3];
                        itemprintfinal[0] = IdxPosicion;
                        itemprintfinal[1] = PosicionInicial;
                        itemprintfinal[2] = PosicionFinal;
                        LstPrintFinal.add(itemprintfinal);
                        //itemprintfinal = null;
                        PosicionInicial = PosicionFinal + 1;
                    }
                    IdxPosicion = IdxPosicion + 1;
                }


                //for (int i = posini; i < inserciones; i++) {
                for (int i = posini; i < LstPrintFinal.size(); i++) {
                    Object[] valor = new Object[1];
                    valor[0] = LstPrintFinal.get(i);

                    Object[] datos = new Object[3];
                    datos = (Object[]) valor[0];

                    PosicionInicial = Integer.parseInt(datos[1].toString());
                    PosicionFinal = Integer.parseInt(datos[2].toString());

                    total = 0;
                    SubTotal = 0;
                    igvporcentaje = 0;
                    opgravada = 0;
                    
                    //for (int j = posini; j <= posfin; j++) {
                    for (int j = PosicionInicial; j <= PosicionFinal; j++) {
                        if (j < tamano) {
                            //String prod = lista.get(j).getId_Producto();
                            String Esgratuita = lista.get(j).getesgratuita();
                            String EsgratuitaPromo = lista.get(j).getesgratuitaPromo();
                            
                            total = total + lista.get(j).getTotal();
                            SubTotal = SubTotal + lista.get(j).getSubTotal();
                            idOperadorRec = lista.get(j).getIdOperadorrec();
                            igvporcentaje = igvporcentaje + lista.get(j).getIGV();
                            opgravada = opgravada + lista.get(j).getOpGravada(); // nueva linea
                            //opinafecta = opinafecta + lista.get(j).getOpInafecta();

                            opdctoSuma = lista.get(j).getOpDescuento() ;

                            if (EsgratuitaPromo.equals("1") ){

                                opdctoSuma = 0;
                                //opinafecta = 0;
                            }
                            opdctoSuma1 += opdctoSuma;
                            //opinafecta1 = opinafecta;

                            if (EsgratuitaPromo.equals("1") ){

                                //opgravada = lista.get(j).getOpGravada();
                                opexonerada = 0;
                                if (opinafecta > 0){
                                    opinafecta = opinafecta;
                                }else{
                                    opinafecta = 0;
                                }
                                opisc = 0;
                                OpBonificacion = 0;
                                OpDescuento = opdctoSuma1;
                                OpDescuento_aux = OpDescuento_aux + lista.get(j).getTotEsGratuita();   //max
                                opgratuita = OpDescuento_aux;

                            }else{

                                //opgravada = lista.get(j).getOpGravada();
                                opexonerada = 0;// opexonerada + lista.get(j).getOpExonerada();
                                opinafecta = opinafecta + lista.get(j).getOpInafecta();                                
                                opisc = lista.get(j).getOpISC();
                                OpBonificacion = OpBonificacion + lista.get(j).getOpBonificacion();
                                OpDescuento = OpDescuento + lista.get(j).getOpDescuento();
                                opgratuita = opgratuita + lista.get(j).getTotEsGratuita();//getOpGratuita();
                                opdctoSuma = OpDescuento + lista.get(j).getOpDescuento();
                            }

                            
                            OpDescuentoTotal = lista.get(j).getOpDescuentoTotal();                            
                            preciounitario = preciounitario + lista.get(j).getpreciounitario();
                        }
                    }

                    double igvvta = 0.0;
                     try {

                        //conex = new ConexionPool().getConnection();
                        procedure = conex.prepareCall("{ call RECUPERA_IGVVTA() }");
                        rsigv = procedure.executeQuery();

                        while (rsigv.next()) {
                            igvvta = rsigv.getDouble("IGVVENTA");
                        }

                        //conex.close();
                    }catch (Exception ex){
                        System.out.print(ex);
                    }

                    if (igvporcentaje > igvvta){
                        igvporcentaje = igvvta;
                    }
                    /*if (opinafecta > 0){                              //lo quite
                        opgravada = opgravada -opinafecta;
                    }*/
                    
                    
                    String original = "&";
                    String ascii= "Y";
                    if (tipventa != 7){
                     Cliente = obj.getNomCliente();
                    for (int b=0; b<=original.length(); b++) {
                        Cliente = Cliente.replace(original.charAt(i), ascii.charAt(i));
                    }
                    }
                    double nuevo_T = total + obj.getOpISC_CAB();
                    
                    //INSETO EN LA CABECERA DE LA VENTA
                    String inserta1 = "";
                    String inserta2 = "";
                    procedure = conex.prepareCall("{ CALL GUARDA_VENTA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                    procedure.setString("SERIE1", obj.getSerie());
                    procedure.setString("IDPROFOR", obj.getId_proforma());
                    procedure.setInt("IdCliente", obj.getId_Cliente());
                    procedure.setInt("IdTipoPago", idipopago);
                    procedure.setInt("IdTipoVenta", tipventa);
                    procedure.setInt("IdCaja", obj.getId_Caja());
                    procedure.setString("IdBotica", idbotica);
                    procedure.setDouble("TOTAL", total);
                    procedure.setDouble("SUBTOTAL", SubTotal);
                    procedure.setDouble("PORCENTAJEIGV", igvporcentaje);
                    procedure.setDouble("OPGRAVADA", opgravada);
                    procedure.setDouble("OPEXONERADA", opexonerada);
                    procedure.setDouble("OPINAFECTA", opinafecta);
                    procedure.setDouble("OPGRATUITA", opgratuita);
                    procedure.setDouble("OPISC", obj.getOpISC_CAB());
                    procedure.setInt("IDPERSONAL_CAJA", obj.getId_Personal_Botica_Caja());
                    procedure.setString("DNI_MODIF", obj.getDniresp_modifica());
                    procedure.setString("RUCRESP", obj.getRUC());
                    //procedure.setString("nomcliente", obj.getNomCliente());
                    procedure.setString("nomcliente", Cliente);
                    procedure.setString("DNIAUX", obj.getDNI());
                    procedure.setString("DIRECCIONCLIENTE", obj.getDireccion());
                    procedure.setString("MIIDMEDICO", obj.getId_Medico());
                    procedure.setString("INCOLEGIATURA", obj.getIdcolegiatura());
                    procedure.setInt("ESDELIVERY", obj.getVentaDelivery());
                    procedure.setString("VPOLIZA", VarPoliza);
                    //procedure.setDouble("OPBONIFICACION", OpBonificacion);
                    procedure.setDouble("OPDESCUENTO", OpDescuento);
                    procedure.setString("VMONEDAFINAL", MonedaFinal);
                    procedure.setString("VCUPON", numbercupon);
                    procedure.setDouble("T_REDONDEO", LeerRedondeo);
                    procedure.setDouble("T_TOTFINAL", LeerTotFinal);
                    
                    procedure.setString("T_CUPON", obj.getReccupon());
                    procedure.setDouble("NUEVOTOTAL", nuevo_T);    
                    
                    procedure.setInt("CANTIBOLSA", obj.getCantiBolsa());
                    

                    rs1 = procedure.executeQuery();
                    rs1.next();
                    String idventa = rs1.getString(1);

                    if (Integer.parseInt(idventa) == -2) {
                        objresultado = new ResultadoVenta(3, "", numero, espositiva, NumeroProforma); //LO SENTIMOS NO HA APERTURADO CAJA
                        conex.rollback();
                        listaInternos.removeAll(listaInternos);
                        return objresultado;
                    }

                    String idventadeta = rs1.getString(3);//para venta detalle
                    String serie = rs1.getString(5);
                    String numer = rs1.getString(2);
                    String idmovde = rs1.getString(4); //para mi movimiento detalle
                    Id_TipoAlmacen = rs1.getString(6);
                    Id_TipoMovimiento = rs1.getString(7);
                    Id_Proveedor = rs1.getString(8);
                    inserta2 = rs1.getString(10);
                    inserta1 = rs1.getString(11);
                    esImprimible = rs1.getInt(12);
                    idtipdocsunat = rs1.getString(13);
                    fechavta = rs1.getString(14);
                    String numerFE = rs1.getString(15);
                    String configimpresion = rs1.getString(16);
                    String modoimpresion = rs1.getString(17);

                    listaInternos.add(new Venta(idbotica,idventa, serie, numer, total, tipventa,idtipdocsunat,fechavta,numerFE,configimpresion,idipopago,modoimpresion));

                    int idvent = Integer.parseInt(idventadeta);
                    int nummonv = Integer.parseInt(idmovde);
                    Double descu = 0.0;
                    double igvVenta = 0.0;
                    double subtotal = 0.0;
                    double precunit = 0.0;
                    double fracnum = 0.0;
                    int mosunida;
                    int mosfraccion;

                    //INSERTO EN LA VENTA_DETALLE Y MODIFICO MI STOCK                   

                    //for (int j = posini; j <= posfin; j++) {
                    for (int j = PosicionInicial; j <= PosicionFinal; j++) {
                        if (j < tamano) {
                            
                            int prodblister = mantProduc.Recupera_EsBlister(lista.get(j).getId_Producto(),1);
                            if (prodblister > 0){
                                
                                if (Integer.valueOf(lista.get(j).getUnidad())==0 && Integer.valueOf(lista.get(j).getFraccion())>0){
                                    descu = (lista.get(j).getPrecio_Venta() - ((lista.get(j).getPrecio_Venta() * lista.get(j).getDescuento()) / 100))/prodblister;
                                }else if (Integer.valueOf(lista.get(j).getUnidad())>0 && Integer.valueOf(lista.get(j).getFraccion())==0){
                                    descu = lista.get(j).getPrecio_Venta();
                                }
                            }else{
                                descu = lista.get(j).getPrecio_Venta() - ((lista.get(j).getPrecio_Venta() * lista.get(j).getDescuento()) / 100);
                            }
                            
                            igvVenta = lista.get(j).getTotal() - lista.get(j).getSubTotal();
                            subtotal = lista.get(j).getSubTotal();
                            if (igvVenta == 0){
                               subtotal = 0.0;
                            }
                            idvent++;
                            String EsgratuitaDetalle = lista.get(j).getesgratuita(); //producto
                            String EsgratuitaPromoDetalle = lista.get(j).getesgratuitaPromo(); //promo del producto
                            if (EsgratuitaPromoDetalle.equals("1") ){

                                opgravadaDetalle = subtotal;
                                opexoneradaDetalle = 0;
                                opinafectaDetalle = 0;
                                opiscDetalle = 0;
                                OpBonificacionDetalle = 0;
                                opDescuentoDetalle = 0;
                                OpDescuento_aux_Detalle = lista.get(j).getOpDescuento();
                                opgratuitaDetalle = lista.get(j).getTotEsGratuita();

                                
                            }else{

                                opgravadaDetalle = subtotal;
                                opexoneradaDetalle = 0;//lista.get(j).getOpExonerada();
                                opinafectaDetalle = lista.get(j).getOpInafecta();
                                opiscDetalle = lista.get(j).getOpISC();
                                OpBonificacionDetalle = lista.get(j).getOpBonificacion();
                                opDescuentoDetalle = lista.get(j).getOpDescuento();
                                opgratuitaDetalle = lista.get(j).getTotEsGratuita();//.getOpGratuita();
                            }

                            String empaque = String.valueOf(lista.get(j).getEmpaque());
                            //objProducto.RetornaEmpaque(idbotica,lista.get(j).getId_Producto());
                            double precvta = lista.get(j).getPrecio_Venta();
                            if (empaque.equals("0")){
                                empaque = "1";
                            }

                            
                            
                            if (prodblister > 0){                                
                            
                                if (Integer.valueOf(lista.get(j).getUnidad())==0 && Integer.valueOf(lista.get(j).getFraccion())>0){
                                    precunit = (lista.get(j).getPrecio_Venta() - ((lista.get(j).getPrecio_Venta() * lista.get(j).getDescuento()) / 100))/prodblister;  
                                }else if (Integer.valueOf(lista.get(j).getUnidad())>0 && Integer.valueOf(lista.get(j).getFraccion())==0){
                                    precunit = descu;
                                }
                            
                            }else{
                                
                                if (Integer.valueOf(lista.get(j).getUnidad())>0 && Integer.valueOf(lista.get(j).getFraccion())>0){
                                    precunit = descu / Double.parseDouble(empaque);
                                }else if (Integer.valueOf(lista.get(j).getUnidad())==0 && Integer.valueOf(lista.get(j).getFraccion())>0){
                                    precunit = descu / Double.parseDouble(empaque);
                                }else if (Integer.valueOf(lista.get(j).getUnidad())>0 && Integer.valueOf(lista.get(j).getFraccion())==0){
                                    precunit = descu;
                                }
                            }
                            //precunit = descu / Double.parseDouble(empaque);


                            double unidad = Integer.valueOf((lista.get(j).getUnidad()));
                            double fraccion = Integer.valueOf(lista.get(j).getFraccion());

                            fracnum = ((unidad * Integer.parseInt(empaque)) + fraccion) / Integer.parseInt(empaque);

                            //(PP.Precio_Venta - (PP.Precio_Venta * (PP.Descuento_Venta / 100))) / if(P.Empaque=0,1,P.Empaque) TOTAL_UNITARIO
    

                            inserta1 += "('" + idbotica + "',"
                                    + "'" + (idvent) + "','" + idventa + "',"
                                    + "'" + lista.get(j).getId_Tipo_Precio() + "','" + lista.get(j).getId_Producto() + "',"
                                    + "'" + lista.get(j).getUnidad() + "','" + lista.get(j).getFraccion() + "',"
                                    + "'" + lista.get(j).getPrecio_Venta() + "',"
                                    + "'" + lista.get(j).getDescuento() + "','" + descu + "','" + lista.get(j).getTotal() + "','"
                                    //+ 0 + "','" + lista.get(j).getIdOperadorrec() + "','" + igvVenta + "','" + lista.get(j).getIGV() + "','" + subtotal + "','" + lista.get(j).getOpExonerada() + "','" + lista.get(j).getOpInafecta() + "','" + lista.get(j).getOpGratuita() + "','" + lista.get(j).getOpISC() + "','" + lista.get(j).getOpDescuento() + "','" + precunit + "','" + lista.get(j).getOpBonificacion() + "','" + empaque + "','" + fracnum + "')" + ",";
                                    + 0 + "','" + lista.get(j).getIdOperadorrec() + "','" + igvVenta + "','" + lista.get(j).getIGV() + "',truncate('" + opgravadaDetalle + "',4),'" + opexoneradaDetalle + "','" + opinafectaDetalle + "','" + opgratuitaDetalle * -1 + "','" + opiscDetalle + "','" + opDescuentoDetalle + "','" + precunit + "','" + OpBonificacionDetalle + "','" + empaque + "','" + fracnum + "','" + lista.get(j).getIdpromocion() + "','" + lista.get(j).getOrdenVta()+ "','" + lista.get(j).getCertMedico()+ "')" + ",";

                            procedure1 = conex.prepareCall("{ CALL MODIFICA_STOCK (?,?,?,?) }");
                            procedure1.setString("IdBotica", idbotica);
                            procedure1.setString("IdProducto", lista.get(j).getId_Producto());
                            procedure1.setInt("unidad", lista.get(j).getUnidad());
                            procedure1.setInt("fraccion", lista.get(j).getFraccion());
                            rs = procedure1.executeQuery();
                            rs.next();

                            if (rs.getInt(1) != -1) {
                                mosunida = rs.getInt(1);
                                mosfraccion = rs.getInt(2);
                                Almacen_Stock_Empaque = rs.getInt(3);
                                Almacen_Stock_Fraccion = rs.getInt(4);
                                trastienda_Stock_Empaque = rs.getInt(5);
                                trastienda_Stock_Fraccion = rs.getInt(6);
                            } else {
                                objresultado = new ResultadoVenta(1, lista.get(j).getDescr_Producto(), numero, espositiva, NumeroProforma);
                                listaInternos.removeAll(listaInternos);
                                lisunidad.removeAll(lisunidad);
                                listfraccion.removeAll(listfraccion);
                                conex.rollback();
                                return objresultado;
                            }

                            nummonv++;
                            int TotalStockEmpaque = Almacen_Stock_Empaque + mosunida + trastienda_Stock_Empaque;
                            int Total_Stock_Fraccion = Almacen_Stock_Fraccion + mosfraccion + trastienda_Stock_Fraccion;

                            //inserto en MOVIMIENTOS_DETALLES

                            inserta2 += "('" + idbotica + "',"
                                    + "'" + Id_TipoAlmacen + "','" + Id_TipoMovimiento + "',"
                                    + "'" + Id_Proveedor + "','" + idventa + "',"
                                    + "'" + nummonv + "','" + lista.get(j).getId_Producto() + "',"
                                    + "'" + lista.get(j).getPrecio_Venta() + "',"
                                    + "'" + lista.get(j).getDescuento() + "','" + lista.get(j).getUnidad() + "',"
                                    + "'" + lista.get(j).getFraccion() + "','" + Cantidad_En_Empaque + "',"
                                    + "'" + Almacen_Stock_Empaque + "','" + Almacen_Stock_Fraccion + "',"
                                    + "'" + mosunida + "','" + mosfraccion + "','" + TotalStockEmpaque + "',"
                                    + "'" + Total_Stock_Fraccion + "','" + obj.getFecha_Venta() + "',"
                                    + "'" + obj.getId_Personal_Botica_Caja() + "','" + serie + "','" + numer + "',"
                                    + "'" + trastienda_Stock_Empaque + "','" + trastienda_Stock_Fraccion + "')" + ",";

                        } else {
                            break;
                        }

                    }

                    inserta1 = inserta1.substring(0, inserta1.length() - 1);
                    st1 = conex.prepareStatement(inserta1);
                    st1.executeUpdate();

                    inserta2 = inserta2.substring(0, inserta2.length() - 1);
                    st2 = conex.prepareStatement(inserta2);
                    st2.executeUpdate();

                    //INSERTO EN VENTAS_TIPO_PAGO

                    if (marcado == 0) {
                        if (varios == true) {
                            resto = mayor - total;
                        } else {
                            resto = total;
                        }
                    } else {
                        resto = mayor;
                    }

                    if (resto <= 0.0 && varios == true) {
                        for (int pos = 0; pos < lista_pagos.size(); pos++) {
                            if (pos == 0) {
                                procedure2 = conex.prepareCall("{ CALL GUARDA_TIPOS_PAGOS (?,?,?,?,?,?,?,?) }");
                                procedure2.setString("IDBOTICA", idbotica);
                                procedure2.setString("IDVENTA", idventa);
                                procedure2.setInt("TIPOPAGO", lista_pagos.get(ind).getId_TipoPago());
                                procedure2.setDouble("MONTO", Math.abs(mayor));                                
                                procedure2.setString("NUMERODOC", lista_pagos.get(ind).getNumDocumento());//
                                procedure2.setInt("IdCliente", obj.getId_Cliente());
                                procedure2.setInt("IDPERSONAL_CAJA", obj.getId_Personal_Botica_Caja());
                                procedure2.setString("MIOPERADOR", lista_pagos.get(ind).getOperador());                                

                            } else {
                                if (ind == 1) {
                                    ind--;
                                } else {
                                    ind++;
                                }

                                procedure2 = conex.prepareCall("{ CALL GUARDA_TIPOS_PAGOS (?,?,?,?,?,?,?,?) }");
                                procedure2.setString("IDBOTICA", idbotica);
                                procedure2.setString("IDVENTA", idventa);
                                procedure2.setInt("TIPOPAGO", lista_pagos.get(ind).getId_TipoPago());
                                //procedure2.setDouble("MONTO", Math.abs(total - mayor));
                                procedure2.setDouble("MONTO", Math.abs(nuevo_T - mayor));
                                procedure2.setString("NUMERODOC", lista_pagos.get(ind).getNumDocumento());
                                procedure2.setInt("IdCliente", obj.getId_Cliente());
                                procedure2.setInt("IDPERSONAL_CAJA", obj.getId_Personal_Botica_Caja());
                                procedure2.setString("MIOPERADOR", lista_pagos.get(ind).getOperador());                                
                            }

                            procedure2.executeQuery();

                        }
                        mayor = menor - Math.abs(total - mayor);
                        marcado = 1;

                    } else {
                        procedure2 = conex.prepareCall("{ CALL GUARDA_TIPOS_PAGOS (?,?,?,?,?,?,?,?) }");
                        procedure2.setString("IDBOTICA", idbotica);
                        procedure2.setString("IDVENTA", idventa);
                        procedure2.setInt("TIPOPAGO", lista_pagos.get(ind).getId_TipoPago());
                        //procedure2.setDouble("MONTO", total);
                        procedure2.setDouble("MONTO", nuevo_T);
                        procedure2.setString("NUMERODOC", lista_pagos.get(ind).getNumDocumento());
                        procedure2.setInt("IdCliente", obj.getId_Cliente());
                        procedure2.setInt("IDPERSONAL_CAJA", obj.getId_Personal_Botica_Caja());
                        procedure2.setString("MIOPERADOR", lista_pagos.get(ind).getOperador());
                        procedure2.executeQuery();
                        mayor = mayor - total;
                        marcado = 0;
                    }

                    posini = (int) (posini + cantidad);
                    posfin = (int) (posfin + cantidad);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String fecvta = dateFormat.format(obj.getFecha_Venta());

              /*guardaTXTVenta(idventa,total,SubTotal,igvporcentaje,opgravada,opexonerada,opinafecta,opgratuita,opisc,
                      obj.getId_Caja(),OpDescuentoTotal,fecvta);
              enviosftp(idventa,fecvta);*/
                } //CIERRE DEL FOR i

            } //CIERRE DEL IF

            /* VERIFICO SI EXISTE UN DESCUENTO ESPECIAL DE COASOCIADOS */
            if (EntidadProforma != null) {
                CallableStatement procedure5 = conex.prepareCall("{ call GUARDA_PROFORMA_DESCUENTO (?,?,?,?,?,?,?,?,?,?,?)}");
                procedure5.setString(1, EntidadProforma.getId_Boticas());
                procedure5.setString(2, EntidadProforma.getId_Tipo_Precio());
                procedure5.setDouble(3, EntidadProforma.getSubTotal());
                procedure5.setDouble(4, EntidadProforma.getIGV());
                procedure5.setDouble(5, EntidadProforma.getTotal());
                procedure5.setInt(6, EntidadProforma.getId_Personal_Botica_Venta());
                procedure5.setDouble(7, EntidadProforma.getPrecio_Venta());
                procedure5.setDouble(8, EntidadProforma.getDescuento());
                procedure5.setDouble(9, EntidadProforma.getPvx());
                procedure5.setDouble(10, EntidadProforma.getDsctoAdicional());
                procedure5.setString(11, EntidadProforma.getIdproducto());
                rs = procedure5.executeQuery();
                rs.next();
                NumeroProforma = rs.getString(1);
                rs.close();
                espositiva = true;
            }

            if (listaInternos.size() > 0) {
            try{
                String interno = listaInternos.get(0).getId_Venta().toString().trim();
                procedure3 = conex.prepareCall("{ call SP_VALIDA_INTERNO(?) }");
                procedure3.setString(1, interno);
                rsvalida = procedure3.executeQuery();
                while (rsvalida.next()) {
                   validavta = rsvalida.getString("HAY");
                }
                //conex.commit();
                if (validavta.equals("0")){
                conex.commit();
                objresultado = new ResultadoVenta(0, "", numero, espositiva, NumeroProforma);

                for (int it = 0; it < listaInternos.size(); it++) {
                    String InternoVta = listaInternos.get(it).getId_Venta().toString().trim();

                    objXML_GENERAR = new XML_GENERAR();
                    //objXML_GENERAR.Execute(LoadInterno(InternoVta));
                    objXML_GENERAR.Execute(listaInternos);
                }
                }else{
                    objresultado = new ResultadoVenta(2, "", numero, espositiva, NumeroProforma);
                    listaInternos.removeAll(listaInternos);
                    lisunidad.removeAll(lisunidad);
                    listfraccion.removeAll(listfraccion);
                    listasinigv.removeAll(listasinigv);
                    lista.removeAll(lista);
                    lista_pagos.removeAll(lista_pagos);
                    conex.rollback();
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                objresultado = new ResultadoVenta(2, "", numero, espositiva, NumeroProforma);
                listaInternos.removeAll(listaInternos);
                lisunidad.removeAll(lisunidad);
                listfraccion.removeAll(listfraccion);
                listasinigv.removeAll(listasinigv);
                lista.removeAll(lista);
                lista_pagos.removeAll(lista_pagos);
                conex.rollback();
            }


            }


        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                objresultado = new ResultadoVenta(2, "", numero, espositiva, NumeroProforma);
                listaInternos.removeAll(listaInternos);
                lisunidad.removeAll(lisunidad);
                listfraccion.removeAll(listfraccion);
                listasinigv.removeAll(listasinigv);
                lista.removeAll(lista);
                lista_pagos.removeAll(lista_pagos);
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
            }

        } finally {
            if (null != conex) {

                try {
                    
                    lisunidad.removeAll(lisunidad);
                    listfraccion.removeAll(listfraccion);
                    lista.removeAll(lista);
                    listasinigv.removeAll(listasinigv);

                    conex.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


         try {
                    //METODO QUE REALIZA LA IMPRESION
                    if (esImprimible == 1) {
                        if (tipventa == 11 || tipventa ==12) {

                            Impresion_Ticketera(listaInternos, idbotica, tipventa,vueltoT);
                            //Impresion_Ticketera_nueva(listaInternos, idbotica, tipventa);
                        } else {
                            GeneraImpresion(listaInternos);
                        }

                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }finally{
                    listaInternos.removeAll(listaInternos);
                }

        return objresultado;
    }
   
    public String LeerLoadInterno(String idventa) {
        String TIPODOCSUNAT = "" ;
        //conex = new ConexionPool().getConnection();
        try {

            
            CallableStatement procedure = conex.prepareCall("{ call XML_LOAD_INTERNO (?) }");
            procedure.setString("PId_Venta", idventa);

            rs = procedure.executeQuery();

            while (rs.next()) {
                 TIPODOCSUNAT =  rs.getString("ID_TIPO_DOCUMENTO_SUNAT")+"-"+rs.getString("Numero");
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return TIPODOCSUNAT;
    }

    
    private void enviosftp(String idventaTXT,String FechaVentaTXT){
        try {
            String UserFTP="";
            String hostFTP="";
            String Puerto="";
            String Passwd="";
            String SubFolder="";

            listaParametros = objTipoMovimiento.Lista_Parametros();
            //String filename = "/root/backups/" + config.nombre_carpeta() + "/" + nombre_compreso;
            if (listaParametros.size() > 0) {
            for (Integer i = 0; i < listaParametros.size(); i++) {

                UserFTP = listaParametros.get(i).getUsuario();
                hostFTP = listaParametros.get(i).gethostEnvio();
                Puerto = listaParametros.get(i).getPuerto();
                Passwd = listaParametros.get(i).getPasswd();
                SubFolder = listaParametros.get(i).getSubFolder();
            }
            }


            String user = UserFTP;
            String filenames = "c:/AENVIO/txt/" + idventaTXT+""+ FechaVentaTXT +".txt";
            String nombre_compreso = idventaTXT+""+ FechaVentaTXT +".txt";
            String host = hostFTP;
            Integer port = Integer.valueOf(Puerto);
            String pass = Passwd;
            System.out.println("------------------- Enviando a Central");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            UserInfo ui = new SUserInfo_1(pass, null);
            session.setUserInfo(ui);
            session.setPassword(pass);
            session.connect();
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            /*if (!sftp.isConnected()) {
                return false;
            }*/
            System.out.println("Subiendo  ...");
            sftp.cd(SubFolder);
            sftp.put(filenames, nombre_compreso);
            System.out.println("Archivos subidos.");
            sftp.exit();
            sftp.disconnect();
            session.disconnect();            
            System.out.println("----------------- FIN");
        } catch (SftpException ex) {
            Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSchException ex) {
            Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void guardaTXTVenta(String idventaTXT,double totalFinal,double SubTotalFinal,double igvporcentaje,
            double opgravadaFinal,double opexoneradaFinal,double opinafectaFinal,double opgratuitaFinal,
            double opiscFinal,int IdCajaTXT,double OpDescuentoTotalFinal,String FechaVentaTXT){

        double igvVta=0.0;
        BigDecimal totalTXT = new BigDecimal(totalFinal);
        totalTXT = totalTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal SubTotalTXT = new BigDecimal(SubTotalFinal);
        SubTotalTXT = SubTotalTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
        
        if (igvporcentaje == 0.0){
            igvVta = 0.0;
        }else{
            igvVta = totalFinal - SubTotalFinal;
        }

        BigDecimal igvVtaTXT = new BigDecimal(igvVta);
        igvVtaTXT = igvVtaTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal opgravadaTXT = new BigDecimal(opgravadaFinal);
        opgravadaTXT = opgravadaTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal opexoneradaTXT = new BigDecimal(opexoneradaFinal);
        opexoneradaTXT = opexoneradaTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal opinafectaTXT = new BigDecimal(opinafectaFinal);
        opinafectaTXT = opinafectaTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal opgratuitaTXT = new BigDecimal(opgratuitaFinal);
        opgratuitaTXT = opgratuitaTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal opiscTXT = new BigDecimal(opiscFinal);
        opiscTXT = opiscTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

        BigDecimal OpDescuentoTotalTXT = new BigDecimal(OpDescuentoTotalFinal);
        OpDescuentoTotalTXT = OpDescuentoTotalTXT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);


        String archivoVtaTXT = idventaTXT +";"+ totalTXT +";"+ SubTotalTXT +";"+ igvVtaTXT +";"+ opgravadaTXT +";"+
                            opexoneradaTXT +";"+ opinafectaTXT +";"+ opgratuitaTXT +";"+ opiscTXT +";"+ IdCajaTXT +";"+
                            OpDescuentoTotalTXT +";"+ FechaVentaTXT;
        
        try
        {   //("/datos/AENVIO/")            
            File archivo=new File("c:/AENVIO/txt/" + idventaTXT+""+ FechaVentaTXT +".txt");
            FileWriter escribir=new FileWriter(archivo,true);            
            escribir.write(archivoVtaTXT);
            escribir.close();
        }        
        catch(Exception e)
        {
            System.out.println("Error al escribir");
        }
    }

    private boolean EsImprimible(int tipventa) {
        boolean valor = true;

        try {
            CallableStatement procedure = conex.prepareCall("{call ES_IMPRIMIBLE(?)  }");
            procedure.setInt("TIPVENTA", tipventa);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                valor = false;
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        }

        return valor;

    }

    public List<Venta> Lista_Mostrar_Cargos(Venta obj) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_INTERNOS_MOVIMIENTOS (?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setString("ALMACEN", obj.getAlmacen());
            procedure.setString("IDPROVEEDOR", obj.getProveedor());
            procedure.setString("DOC", obj.getDocumento());
            procedure.setString("IDTIPMOV", obj.getIdMovimiento());
            procedure.setString("MOVI", obj.getMovi());
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(
                        new Venta(rs.getString("Numero_Documento"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getDate("Fecha_Documento"), rs.getDate("Fecha_Registro"), rs.getString("vendedor"),
                        rs.getString("tdocu")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Mostrar_Cargos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;
    }

    public List<Venta> Lista_Internos_Ventas(Venta objventa, int op) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_INTERNOS_VENTAS (?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", objventa.getId_Venta());
            procedure.setInt("OPCION", op);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"),
                        rs.getDouble("IGV"), rs.getString("CAJERO"), rs.getInt("Id_Cliente"),
                        rs.getDouble("Redondeo"),rs.getDouble("TotFinal"),
                        rs.getString("tpago"), rs.getString("tredondeo"), rs.getString("MODOIMPRESION")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Lista_guiasajuste(Venta objventa) {
        List<Venta> listaguias = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call sp_guiasrealizadas (?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());            
            procedure.setString("FECHA1", objventa.getFecha1().toString());
            procedure.setString("FECHA2", objventa.getFecha2().toString());
            procedure.setString("IDGUIAAJUSTE", "");
            procedure.setInt("OPC", 1);
            procedure.setString("TSERIE", "");
            procedure.setString("TNUMERO", "");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaguias.add(new Venta(rs.getString("id_gruiaAjuste"),
                        rs.getString("serie"), rs.getString("numero"),
                        rs.getDate("fecha"), rs.getString("obs"),
                        rs.getString("numMov"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error sp_guiasrealizadas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaguias;

    }

    public List<Venta> Lista_Datos_Ventas(Venta objventa) {
        List<Venta> Datosventas = new ArrayList<Venta>();
        int opc=0;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call TRAE_DATOS_VENTA (?,?,?,?) }");
            procedure.setString("NIDBOTICA", objventa.getId_Botica());
            procedure.setString("NNUMERO", objventa.getNumero());
            procedure.setString("VSERIE", objventa.getSerie());
            procedure.setString("VINTERNO", objventa.getId_Venta());

            rs = procedure.executeQuery();

            while (rs.next()) {
                Datosventas.add(new Venta(rs.getString("Serie1"),
                        rs.getString("Numero1"),rs.getString("DESCRIPCION"),opc,rs.getString("DNI"),
                        rs.getString("Fecha_Venta"),rs.getString("RUC"),rs.getString("MODOIMPRESION")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Datosventas;

    }

    public List<Venta> ReimprimeLista_Internos_Ventas(Venta objventa, int op) {
        List<Venta> Reimprimelistaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REIMPRIME_INTERNOS_VENTAS (?,?,?,?,?) }");
            procedure.setString("NIDBOTICA", objventa.getId_Botica());
            procedure.setString("NDOCUMENTO", objventa.getNDocumento());
            procedure.setInt("NIDCAJA", objventa.getId_Caja());
            procedure.setInt("VTIPVTA", objventa.gettipvta());
            procedure.setString("VSERIE", objventa.getSerie());
            rs = procedure.executeQuery();

            while (rs.next()) {
                Reimprimelistaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"),
                        rs.getDouble("IGV"), rs.getString("CAJERO"), rs.getInt("Id_Cliente"),
                        rs.getDouble("Redondeo"),rs.getDouble("TotFinal"),
                        rs.getString("tpago"), rs.getString("tredondeo"), rs.getString("MODOIMPRESION")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error REIMPRIME_INTERNOS_VENTAS" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Reimprimelistaventas;

    }

    public List<Venta> Lista_Internos_VentasAnuladas(Venta objventa, int op) {
        List<Venta> listaventasAnuladas = new ArrayList<Venta>();
        String idventa = objventa.getId_Venta();
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_INTERNOSANULADOS (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", idventa);            
            procedure.setString("FECHA1", objventa.getFecha1());
            procedure.setString("FECHA2", objventa.getFecha2());
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventasAnuladas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"), rs.getDouble("IGV"),
                        rs.getString("CAJERO"), rs.getDouble("NOTOMAR"), rs.getInt("Id_Cliente"),
                        rs.getInt("Id_Personal_Botica_Caja")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Int_Ventas_Fecha" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventasAnuladas;
    }


        public List<Venta> Lista_Descargos_Inv(Venta objventa) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCARGO_INV (?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("ISERIE", objventa.getSerie());
            procedure.setString("INUMERO", objventa.getNumero());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("BoticaDestino"),
                        rs.getString("Partida"),
                        rs.getDate("Docdat"), rs.getString("Codalm"),
                        rs.getString("Typmov"), rs.getString("Docnum"),
                        rs.getString("Codpro"), rs.getString("Stkprf"),
                        rs.getString("Despro"), rs.getString("Codlab"),
                        rs.getString("Codprv"), rs.getString("Dtopro"),
                        rs.getString("Prisal"), rs.getString("Stksed"),
                        rs.getString("Stkfra"), rs.getString("Codbar"),
                        rs.getDate("Datreg"),rs.getString("Doctot"),rs.getDate("Fecha_Recepcion")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error RECUPERA_DESCARGO_INV" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Lista_Internos_Ventas_SOAT(Venta objventa, int op) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_INTERNOS_VENTAS_SOAT (?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", objventa.getId_Venta());
            procedure.setInt("OPCION", op);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"),
                        rs.getDouble("IGV"), rs.getString("CAJERO"), rs.getInt("Id_Cliente"),
                        rs.getString("numeroserie"), rs.getString("placa"), rs.getString("Poliza"), rs.getString("Certificado")));
            }


            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Busca_Internos_Movimientos(Venta objventa, int op) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BUSCA_INTERNOS_MOVIMIENTOS (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", objventa.getId_Venta());
            procedure.setString("FECHA1", objventa.getFecha1());
            procedure.setString("FECHA2", objventa.getFecha2());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"),
                        rs.getDouble("Total"), rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("VENDEDOR"), rs.getString("CAJERO"), rs.getInt("Anulado")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Lista_Int_Ventas_Fecha(Venta objventa) {

        List<Venta> listaventas = new ArrayList<Venta>();
        String idventa = objventa.getId_Venta() + '%';

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LIS_INTER_VENTAS_RANGO (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", idventa);
            procedure.setString("FECHA1", objventa.getFecha1().toString());
            procedure.setString("FECHA2", objventa.getFecha2().toString());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"), rs.getDouble("IGV"),
                        rs.getString("CAJERO"), rs.getDouble("NOTOMAR"), rs.getInt("Id_Cliente"),
                        rs.getString("tpago"),rs.getString("tredondeo"),rs.getString("MODOIMPRESION")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Int_Ventas_Fecha" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Lista_EnviosOficina(Venta objventa) {

        List<Venta> listaventas = new ArrayList<Venta>();
        String idventa = objventa.getId_Venta();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LIS_ABONOENVIOOFICINA (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", idventa);
            procedure.setString("FECHA1", objventa.getFecha1().toString());
            procedure.setString("FECHA2", objventa.getFecha2().toString());
            rs = procedure.executeQuery();
          
            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Botica"),
                        rs.getInt("ID_CAJA"),rs.getString("CAJA"),
                        rs.getInt("Turno"),rs.getString("turnodesc"), rs.getDate("FECHA"),
                        rs.getInt("Id_Personal"),rs.getString("Cajero"),
                        rs.getInt("Id_Billete"),
                        rs.getDouble("Valor"), rs.getInt("Cantidad"),rs.getDouble("Total"),
                        rs.getString("NroEntrega"), rs.getInt("Id_Gerente"),
                        rs.getString("Gerente"), rs.getInt("Id_UsuarioSistema"),
                        rs.getString("UsuarioIngreso")));

            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Int_Ventas_Fecha" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> ListaVenta_Detalle(Venta objventa) {

        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_VENTA ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Producto"), rs.getString("PRODUCTO"),
                        rs.getInt("Empaque"), rs.getInt("unidad"), rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta"), rs.getDouble("Descuento"),
                        rs.getDouble("Precio_Venta_Final"),
                        rs.getDouble("Total"), rs.getDouble("IGV_Exonerado"), rs.getInt("Mostrador_Stock_Empaque"),
                        rs.getInt("Mostrador_Stock_Fraccion"),rs.getString("Id_Laboratorio"),rs.getDouble("OP_GRATUITA"),
                        rs.getDouble("OP_DESCUENTO"),rs.getInt("Id_VentaDetalle"),rs.getInt("Id_Producto_Recarga"),
                        rs.getInt("IDPROMO"),rs.getInt("orden"), rs.getString("Id_Tipo_Precio"),
                        rs.getDouble("isc1"),rs.getDouble("OP_ISC")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaventas;
    }

    public List<Venta> ListaGuia_Detalle(String idguia,String serie, String numero) {

        List<Venta> listaguiasdetalle = new ArrayList<Venta>();

        String IDBOTICA="";
        String FECHA1 ="";
        String FECHA2 = "";
        int OPC = 2;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call sp_guiasrealizadas ('" + IDBOTICA + "','" + FECHA1 + "','" + FECHA2 + "','" + idguia + "','" + OPC + "','" + serie + "','" + numero + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaguiasdetalle.add(new Venta(rs.getString("Codigo"), rs.getString("Producto"),
                        rs.getString("LAB"), rs.getInt("unidad"),rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta_Final"),rs.getDouble("Total"),rs.getString("Movimiento"),
                        rs.getString("id_gruiaAjusteDet"),rs.getString("Id_Botica"),
                        rs.getString("id_gruiaAjuste"),rs.getString("tipoMov"),rs.getString("numMov")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaguiasdetalle;
    }

    public List<Venta> ReimprimeListaVenta_Detalle(Venta objventa) {

        List<Venta> Reimprimelistaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REIMPRIMERECUPERA_VENTA ('" + objventa.getId_Botica() + "','" + objventa.getNDocumento() + "','" + objventa.getId_Caja() + "','" + objventa.gettipvta() + "','" + objventa.getSerie() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                Reimprimelistaventas.add(new Venta(rs.getString("Id_Producto"), rs.getString("PRODUCTO"),
                        rs.getInt("Empaque"), rs.getInt("unidad"), rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta"), rs.getDouble("Descuento"),
                        rs.getDouble("Precio_Venta_Final"),
                        rs.getDouble("Total"), rs.getDouble("IGV_Exonerado"), rs.getInt("Mostrador_Stock_Empaque"),
                        rs.getInt("Mostrador_Stock_Fraccion"),rs.getString("Id_Laboratorio"),rs.getDouble("OP_GRATUITA"),
                        rs.getDouble("OP_DESCUENTO"),rs.getInt("Id_VentaDetalle"),rs.getInt("Id_Producto_Recarga"),
                        rs.getInt("IDPROMO"),rs.getInt("orden"), rs.getString("Id_Tipo_Precio"),
                        rs.getDouble("isc1"),rs.getDouble("OP_ISC")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Reimprimelistaventas;
    }


    public List<Venta> ListaVenta_DetalleND(Venta objventa) {

        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_VENTA_ND ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Producto"), rs.getString("PRODUCTO"),
                        rs.getInt("Empaque"), rs.getInt("unidad"), rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta"), rs.getDouble("Descuento"),
                        rs.getDouble("Precio_Venta_Final"),
                        rs.getDouble("Total"), rs.getDouble("IGV_Exonerado"), rs.getInt("Mostrador_Stock_Empaque"),
                        rs.getInt("Mostrador_Stock_Fraccion"),rs.getString("Id_laboratorio"),rs.getDouble("OP_GRATUITA"),
                        rs.getDouble("OP_DESCUENTO"),rs.getInt("Id_VentaDetalle"),rs.getInt("Id_Producto_Recarga"),
                        rs.getInt("IDPROMO"),rs.getInt("orden"), rs.getString("Id_Tipo_Precio"),
                        rs.getDouble("isc1"),rs.getDouble("OP_ISC")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaventas;
    }

    public List<Venta> ListaEnvioEfectivo_Detalle(Venta objventa) {

        List<Venta> listaEnvioEfec = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ENVIOEFECTIVOOFICINA ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaEnvioEfec.add(new Venta(rs.getString("NroEntrega"),
                        rs.getDouble("Valor"), rs.getInt("Cantidad"),rs.getDouble("Total")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaEnvioEfec;
    }

    public List<Venta> ListaAnulaEnvioEfectivo(Venta objventa) {

        List<Venta> listaAnulEnvioEfec = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ANULA_ENVIOEFECTIVOOFICINA ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaAnulEnvioEfec.add(new Venta(rs.getString("NUMERO")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaAnulEnvioEfec;
    }
    /**********************************************
     *
     * Programador  : Gino Paredes Zurita
     * Fecha        : 16-11-2013
     * Modulo       : Pre-Anulados
     * Tipo         : NUEVO MODULO - FormPreAnulacionInterno.java
     *
     ***********************************************/
    public List<Venta> ListaVenta_Detalle_pre(Venta objventa) {

        List<Venta> listaventas = new ArrayList<Venta>();

        try {
            listaventas.clear();
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_VENTA_PRE ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Botica"), rs.getString("Interno"), rs.getInt("preanulado"), rs.getInt("anulado")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaventas;
    }
    /*
     * fin
     */

    public boolean InsertaNotaCredito(Venta obj, List<Venta> lista,double igv,double opinafecta, double OpDescuentoCabecera,double OpGratuitoCabecera,String money,String OPC,String cmbObs) {
        boolean resul = false;
        String serie;
        String numero;
        String interno;
        double total;
        Double descu = 0.0;
        double igvVenta = 0.0;
        double opgravada =0.0;
        double auxopcisc= 0.0;
        double subtotal = 0.0;
        double precunit = 0.0;
        int esImprimible = 0;
        double fracnum = 0.0;
        double preciovta;
        double dscto;
        double opDescuentoDetalle=0.0,opgratuitaDetalle=0.0;
        String inserta1 = "";
        String inserta2 = "";
        String tipoprecio = "01";
        int Cantidad_En_Empaque = 0;
        int Almacen_Stock_Empaque = 0;
        int Almacen_Stock_Fraccion = 0;
        int Trastienda_Stock_Empaque = 0;
        int Trastienda_Stock_Fraccion = 0;
        int TotalStockEmpaque = 0;
        int Total_Stock_Fraccion;
        String Id_TipoAlmacen = "";
        String Id_TipoMovimiento = "";
        String Id_Proveedor = "";
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        CallableStatement procedure2;
        CallableStatement procedure3;

        try {
            
            idbotica = obj.getId_Botica();
            serie = obj.getSerie();
            numero = obj.getNumero();
            total = obj.getTotal();
            interno = obj.getId_Venta();

            for (int i = 0; i < lista.size(); i++) {
                auxopcisc = lista.get(i).getOpISC_CAB();
            }
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            procedure2 = conex.prepareCall(" { CALL GUARDA_MOVIMIENTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure2.setString("IdBotica", idbotica);
            procedure2.setString("SERIE", serie);
            procedure2.setString("NUMERO", numero);
            procedure2.setDate("FechaDocumento", (Date) obj.getFecha_Venta());
            procedure2.setDate("FECHAREGISTRO", (Date) obj.getFecha_Venta());
            procedure2.setDouble("TOTAL", total);
            procedure2.setDouble("Descuento", obj.getDescuento());
            procedure2.setInt("IDPERSONAL", obj.getId_Personal_Botica_Venta());
            procedure2.setString("INTERNO", interno);
            procedure2.setString("CLIENTE", obj.getNomCliente());
            procedure2.setString("DNIAUX", obj.getDNI());
            procedure2.setString("RUXAUX", obj.getRUC());
            procedure2.setString("OBSERVACION", obj.getDESCRIPCION());
            procedure2.setString("DIRECCION", obj.getDireccion());
            procedure2.setDouble("SUBTOTAL", obj.getSubTotal());
            procedure2.setDouble("OPGRAVADA", obj.getOpGravada());
            //procedure2.setDouble("OPGRAVADA", obj.getOpGravadaDetalle());
            procedure2.setDouble("PORCENTAJEIGV", igv);
            procedure2.setDouble("OPINAFECTA", opinafecta);
            procedure2.setDouble("OPDESCUENTO", OpDescuentoCabecera);
            procedure2.setDouble("OPGRATUITA", OpGratuitoCabecera);
            procedure2.setString("IDMONEDA", money);
            procedure2.setString("OPCION", OPC);
            procedure2.setString("VCMBOBS", cmbObs);
            procedure2.setDouble("VREDONDEO", obj.getredondeo());
            procedure2.setDouble("VTOTFINAL", obj.gettotfinal());
            procedure2.setDouble("OPISC", auxopcisc);


            rs = procedure2.executeQuery();
            rs.next();
            int nummonv = rs.getInt(1);
            Id_TipoAlmacen = rs.getString(2);
            Id_TipoMovimiento = rs.getString(3);
            Id_Proveedor = rs.getString(4);
            inserta1 = rs.getString(6);
            inserta2 = rs.getString(7);
            String numvta = rs.getString(8);
            int numvtadet = rs.getInt(9);
            String fecha = rs.getString(10);
            //esImprimible = rs.getInt(11);
        
            String documento = serie + "-" + numero;
            nummonv++;
            numvtadet++;

            //MODIFICO STOCK DE PRODUCTO BOTICA
            for (int i = 0; i < lista.size(); i++) {
                procedure3 = conex.prepareCall("CALL MODIFICA_STOCK_PRODUCTO_BOTICA('" + lista.get(i).getUnidad() + "',"
                        + "'" + lista.get(i).getFraccion() + "' ,'" + idbotica + "',"
                        + "'" + lista.get(i).getId_Producto() + "')");

                rs = procedure3.executeQuery();
                rs.next();
                int mosunida = rs.getInt(1);
                int mosfraccion = rs.getInt(2);
                Almacen_Stock_Empaque = rs.getInt(3);
                Almacen_Stock_Fraccion = rs.getInt(4);  
                Trastienda_Stock_Empaque = rs.getInt(5);
                Trastienda_Stock_Fraccion = rs.getInt(6);

                TotalStockEmpaque = Almacen_Stock_Empaque + mosunida + Trastienda_Stock_Empaque;
                Total_Stock_Fraccion = Almacen_Stock_Fraccion + mosfraccion + Trastienda_Stock_Fraccion;

                                
                int prodblister = mantProduc.Recupera_EsBlister(lista.get(i).getId_Producto(),1);
                if (prodblister > 0){
                               
                    if (Integer.valueOf(lista.get(i).getUnidad())==0 && Integer.valueOf(lista.get(i).getFraccion())>0){
                        descu = (lista.get(i).getpvp() - ((lista.get(i).getpvp() * lista.get(i).getdcto()) / 100))/prodblister;
                    }else if (Integer.valueOf(lista.get(i).getUnidad())>0 && Integer.valueOf(lista.get(i).getFraccion())==0){
                        descu = lista.get(i).getpvp();
                    }
                }else{                    
                    descu = lista.get(i).getpvp() - ((lista.get(i).getpvp() * lista.get(i).getdcto()) / 100);
                }
                            
                
                if (lista.get(i).getIGV() != 0){
                    igvVenta = lista.get(i).getPrecio_Venta() - lista.get(i).getOpGravadaDetalle();
                    opgravada= lista.get(i).getOpGravadaDetalle();
                }else{
                    igvVenta = lista.get(i).getIGV();
                    opgravada= 0.0;
                }
               
                /*subtotal = lista.get(i).getSubTotal();
                if (igvVenta == 0){
                   subtotal = 0.0;
                }*/
                //idvent++;
                //String empaque = objProducto.RetornaEmpaque(idbotica,lista.get(i).getId_Producto());
                String empaque = String.valueOf(lista.get(i).getempaque());
                //int prodblister = mantProduc.Recupera_EsBlister(lista.get(i).getId_Producto(),1);
                
                double precvta = lista.get(i).getPrecio_Venta();
                if (empaque.equals("0")){
                    empaque = "1";
                }

                if (prodblister > 0){                                
                            
                    if (Integer.valueOf(lista.get(i).getUnidad())==0 && Integer.valueOf(lista.get(i).getFraccion())>0){
                        precunit = (lista.get(i).getpvp()- ((lista.get(i).getpvp() * lista.get(i).getDescuento()) / 100))/prodblister;  
                    }else if (Integer.valueOf(lista.get(i).getUnidad())>0 && Integer.valueOf(lista.get(i).getFraccion())==0){
                        precunit = descu;
                    }
                            
                }else{
                                
                    if (Integer.valueOf(lista.get(i).getUnidad())>0 && Integer.valueOf(lista.get(i).getFraccion())>0){
                        precunit = descu / Double.parseDouble(empaque);
                    }else if (Integer.valueOf(lista.get(i).getUnidad())==0 && Integer.valueOf(lista.get(i).getFraccion())>0){
                        precunit = descu / Double.parseDouble(empaque);
                    }else if (Integer.valueOf(lista.get(i).getUnidad())>0 && Integer.valueOf(lista.get(i).getFraccion())==0){
                        precunit = descu;
                    }
                }
                
                
                

                //precunit = descu / Double.parseDouble(empaque);

                double unidad = Integer.valueOf((lista.get(i).getUnidad()));
                double fraccion = Integer.valueOf(lista.get(i).getFraccion());

                fracnum = ((unidad * Integer.parseInt(empaque)) + fraccion) / Integer.parseInt(empaque);

                /*if (lista.get(i).getesgratuitaPromo().equals("1") ){
                   
                   opgratuitaDetalle = lista.get(i).getgratuita();
                   //opDescuentoDetalle = 0.00;
                }else{
                   opgratuitaDetalle = 0.00;
                   //opDescuentoDetalle = lista.get(i).getOpDescuentoDetalle();
                }*/


                inserta1 += "('" + idbotica + "','" + Id_TipoAlmacen + "',"
                        + "'" + Id_TipoMovimiento + "','" + Id_Proveedor + "',"
                        + "'" + documento + "','" + nummonv + "','" + lista.get(i).getId_Producto() + "',"
                        + "'" + lista.get(i).getPrecio_Venta() + "','" + lista.get(i).getDescuento() + "',"
                        + "'" + lista.get(i).getUnidad() + "','" + lista.get(i).getFraccion() + "',"
                        + "'" + Cantidad_En_Empaque + "','" + Almacen_Stock_Empaque + "',"
                        + "'" + Almacen_Stock_Fraccion + "','" + mosunida + "','" + mosfraccion + "',"
                        + "'" + TotalStockEmpaque + "','" + Total_Stock_Fraccion + "',"
                        + "'" + obj.getFecha_Venta() + "',"
                        + "'" + obj.getId_Personal_Botica_Venta() + "','" + serie + "','" + numero + "',"
                        + "'" + Trastienda_Stock_Empaque + "','" + Trastienda_Stock_Fraccion + "')" + ",";

                nummonv++;

                inserta2 += "('" + idbotica + "','" + numvtadet + "',"
                        + "'" + numvta + "','" + lista.get(i).getId_Tipo_Precio()+ "',"
                        + "'" + lista.get(i).getId_Producto() + "','" + lista.get(i).getUnidad() + "',"
                        + "'" + lista.get(i).getFraccion() + "',"
                        + "'" + lista.get(i).getpvp() + "','" + lista.get(i).getdcto() + "',"
                        + "'" + descu/*lista.get(i).getpvpx()*/ + "','" + lista.get(i).getPrecio_Venta() + "',"
                        + "'" + lista.get(i).getDescuento() + "','" + fecha + "','" + igvVenta + "','" + lista.get(i).getIGV() + "','" + opgravada + "','" + lista.get(i).getOpInafecta() + "','" + lista.get(i).getgratuita() + "','" + precunit + "','" + lista.get(i).getIdprodrecarga() + "','" + empaque + "','" + fracnum + "','" + lista.get(i).getOPDescuento() + "','" + lista.get(i).getOpISC()+ "')" + ",";

                numvtadet++;
// + "','" + lista.get(j).getIGV() + "','" + subtotal + "','" + lista.get(j).getOpExonerada() + "','" + lista.get(j).getOpInafecta() + "','" + lista.get(j).getOpGratuita() + "','" + lista.get(j).getOpISC() + "','" + lista.get(j).getOpDescuento() + "','" +  + "','" + lista.get(j).getOpBonificacion()  "')" + ",";

            }

            inserta1 = inserta1.substring(0, inserta1.length() - 1);
            st1 = conex.prepareStatement(inserta1);
            st1.executeUpdate();

            inserta2 = inserta2.substring(0, inserta2.length() - 1);
            st2 = conex.prepareStatement(inserta2);
            st2.executeUpdate();

            conex.commit();
            rs.close();
            resul = true;

        } catch (SQLException ex) {
            try {
                System.out.println("ERROR EL INSERTAR EN TABLA MOVIMIENTOS SE HIZO UN ROLLBACK: " + ex.getMessage());
                conex.rollback();
            } catch (Exception ex1) {
                System.out.println("ERROR " + ex.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;

    }

    public boolean InsertaTmpDetalleNC(Venta obj, List<Venta> lista,String InternoVta,String Opc) {
        boolean resul = false;       

        try {            
            conex = new ConexionPool().getConnection();
            idbotica = obj.getId_Botica();
            
            for (int i = 0; i < lista.size(); i++) {

                CallableStatement procedure = conex.prepareCall("{ call INSERTA_TMPDETALLENC ('" + idbotica + "','" + InternoVta + "','" + lista.get(i).getUnidad() + "','" + lista.get(i).getFraccion() + "','" + lista.get(i).getId_Producto() + "','" + lista.get(i).getempaque() + "','" + lista.get(i).getidvtadet() + "') }");
                rs = procedure.executeQuery();
            }
           
            rs.close();
            resul = true;

        } catch (OutOfMemoryError ex) {
            System.out.println("Error en INSERTA_TMPDETALLENC" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;

    }

    public List<NotaCredito> listaNotaCredito(String numdocumento, String idbotica) {
        List<NotaCredito> lista = new ArrayList<NotaCredito>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_NOTA_CREDITO ('" + idbotica + "','" + numdocumento + "') }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lista.add(new NotaCredito(rs.getString("Numero_Documento"),
                        rs.getDate("Fecha_Documento"), rs.getDouble("Total"),
                        rs.getString("Id_Venta_Nota_Credito")));
            }

            rs.close();

        } catch (Exception ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return lista;

    }

    public void Impresion_Ticketera_nueva(List<Venta> listaInternos, String idbotica, int tipventa) {
        String Direccion = "", DireccionFiscal = "", botica = "", RUC = "", DNI = "",money = "";
        String serie = null, numero = "", fecha = "",fecha_aux = "", hora = "";
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0,Redondeo=0.0,TotFinal=0.0,
                OP_GRATUITA=0.0,opimportetotal=0.0,optotalpagar=0.0,
                opimportetotal1=0.0, opgravada1=0.0,opinafecta1 =0.0, opigv1 = 0.0, Redondeo1 = 0.0,
                optotalpagar1 = 0.0, opdescuento1=0.0,totaldet=0.0;

        String cliente = "", vendedor = null, cajero = null;
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> TpDescripcion = new ArrayList<String>();
        List<String> Cantidad = new ArrayList<String>();
        List<String> DecTP = new ArrayList<String>();
        List<String> TotTP = new ArrayList<String>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> punit = new ArrayList<Double>();
        List<String> lab = new ArrayList<String>();
        String CantidadPedida = "", Np = "",Np1 = "",direcCliente="", Nomvendedor="",Nomcajero="",tipodocumento="",
                numdocumento="",documentoemitido="",serienumero="",idvta="",observacion="",docreferencia="",
                sernumref="",fecharef = "", cantidad="", producto="",simbolo="",idmoneda="",disclaimer="",
                rucbarcode = "",tipdocbarcode="",igvbarcode="",totalbarcode="",tipodocadquiriente="",
                numerodocadquiriente="",firmabarcode="",tituloopgravada="",tituloopinafecta="",tituloopigv="",
                tituloopimportetotal="",tituloopredondeo="",titulooptotalpagar="",titulooptotaldscto="";
        Integer Largo = 0;
        int lineas = 0;
        int dos = 1;
        CallableStatement procedure, procedure1, procedure3, procedure4, procedure5;
        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps;
        String ConvTotaLetras ="";
        Venta objlista_Titulo = null;
        Venta objlista_Cabecera = null;

        try {

            String modoImpresionDoc = listaInternos.get(0).getmodoimpresion().toString().trim();
            String impresionDouble = listaInternos.get(0).getconfigimpresion().toString().trim();
            if (impresionDouble.equals("1")){
                dos = 2;
            }else{
                dos = 1;
            }

            conex = new ConexionPool().getConnection();
            for (int i = 0; i < dos; i++) { //Inicio del ticket boleta

                String miinterno = listaInternos.get(0).getId_Venta().toString().trim();
                procedure1 = conex.prepareCall("{ call RECUPERA_DATA_TICKET_BOLETA(?,?) }");
                procedure1.setString(1, miinterno);
                procedure1.setString(2, idbotica);
                rs1 = procedure1.executeQuery();

                while (rs1.next()) {
                    botica = rs1.getString("mibotica");
                    serie = rs1.getString("Serie");
                    numero = rs1.getString("Numero");
                    fecha = String.valueOf(rs1.getDate("Fecha_Venta"));
                    TotFinal = rs1.getDouble("TotFinal");
                    RUC = rs1.getString("RUCEmpresa");
                }

                ProductoDescripcion.removeAll(ProductoDescripcion);
                TpDescripcion.removeAll(TpDescripcion);
                Cantidad.removeAll(Cantidad);
                Precio.removeAll(Precio);
                punit.removeAll(punit);
                lab.removeAll(lab);
                DecTP.remove(DecTP);
                TotTP.remove(TotTP);


               
                fecha = objFechaHora.MysqlToJuliano(fecha).toString();                
                BigDecimal bdoptotfinal = new BigDecimal(TotFinal); 
               
                bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                TotFinal = bdoptotfinal.doubleValue();
                String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                
                String delimiter = "/";
                String[] temp;
                temp = fecha.split(delimiter);

                String leerfecha = temp[2]+temp[1]+temp[0];
                String leerStrTipoDoc = listaInternos.get(0).getidtipdocsunat();
                String leerNumero = listaInternos.get(0).getnumerFE();

                String LeerNuevaRutaXML = "/mnt/XML/" + leerfecha + "/" + idbotica + "/";         //   1
                //String LeerNuevaRutaXML = "D:\\XML\\" + leerfecha + "\\" + idbotica + "\\";
                String LeerMyFile = RUC + '-' + leerStrTipoDoc + '-' + serie + '-' + leerNumero;

                String XMLGENERADO = objXML_GENERAR.readXML(miinterno,LeerNuevaRutaXML,LeerMyFile);
                String recSignature = objXML_GENERAR.readXMLSignature(miinterno,LeerNuevaRutaXML,LeerMyFile);

                procedure4 = conex.prepareCall("{ call GUARDA_FIRMADIGITAL(?,?,?,?,?,?) }");
                procedure4.setString("PId_Botica", idbotica);
                procedure4.setString("PId_Venta", miinterno);
                procedure4.setString("PId_Serie", serie);
                procedure4.setString("PId_Numero", numero);
                procedure4.setString("PDigest", XMLGENERADO);
                procedure4.setString("PSignature", recSignature);
                rs3 = procedure4.executeQuery();

                while (rs3.next()) {
                String firma =  rs3.getString("resultado");
                }



                procedure = conex.prepareCall("{ call PDF_VENTAFINAL(?,?) }");
                procedure.setString(1, miinterno);
                procedure.setString(2, ConvTotaLetras);

                rs = procedure.executeQuery();

                
                lista_VentaFinal.removeAll(lista_VentaFinal);
                NumberFormat formatter = new DecimalFormat("#,##0.00");

                while (rs.next()) {

                    botica = rs.getString("Empresa_Botica");
                    Direccion = rs.getString("Direccion_Botica");
                    DireccionFiscal = rs.getString("Direccion_Empresa");
                    RUC = rs.getString("RUC");
                    cliente = rs.getString("NOMCLIENTE");
                    direcCliente = rs.getString("DIRECCION_CLIENTE");
                    tipodocumento = rs.getString("TIPO_DOCUMENTO");
                    numdocumento = rs.getString("NUMERO_DOCUMENTO");
                    documentoemitido = rs.getString("Documento");
                    serienumero = rs.getString("SerNum");
                    fecha_aux = String.valueOf(rs.getDate("Fecha_Venta"));
                    fecha = objFechaHora.MysqlToJuliano(fecha_aux).toString();
                    hora = rs.getString("Hora_Venta");
                    idvta = rs.getString("Id_Venta");
                    cajero = rs.getString("CAJERO");
                    vendedor = rs.getString("VENDEDOR");
                    TotFinal = rs.getDouble("Total");
                    money = rs.getString("MONEDA");
                    idmoneda = rs.getString("Id_Moneda");
                    observacion = rs.getString("Observacion");
                    docreferencia = rs.getString("DocumentoRef");
                    sernumref = rs.getString("SerNumRef");
                    fecharef = rs.getString("Fecha_VentaRef");
                    serie = rs.getString("Serie");
                    numero = rs.getString("Numero");
                    cantidad = rs.getString("Cantidad");
                    producto = rs.getString("Producto");
                    totaldet = rs.getDouble("Totaldet");
                    String totaldetx = formatter.format(totaldet);
                    String totaldetx1 = totaldetx.replace(".", ";");
                    String totaldetx2 = totaldetx1.replace(",", ".");
                    String totaldetx3 = totaldetx2.replace(";", ",");

                    tituloopgravada = rs.getString("TITULOOPGRAVADA");
                    opgravada1 = rs.getDouble("OPGRAVADA");
                    String opgravada1x = formatter.format(opgravada1);
                    String opgravada1x1 = opgravada1x.replace(".", ";");
                    String opgravada1x2 = opgravada1x1.replace(",", ".");
                    String opgravada1x3 = opgravada1x2.replace(";", ",");
                    if (opgravada1x3.equals("0")|| opgravada1x3.equals("0.0")|| opgravada1x3.equals("0.00")){
                        opgravada1x3 = "";
                    }
                    tituloopinafecta = rs.getString("TITULOINAFECTA");
                    opinafecta1 = rs.getDouble("OPINAFECTA");
                    String opinafecta1x = formatter.format(opinafecta1);
                    String opinafecta1x1 = opinafecta1x.replace(".", ";");
                    String opinafecta1x2 = opinafecta1x1.replace(",", ".");
                    String opinafecta1x3 = opinafecta1x2.replace(";", ",");
                    if (opinafecta1x3.equals("0")|| opinafecta1x3.equals("0.0")|| opinafecta1x3.equals("0.00")){
                        opinafecta1x3 = "";
                    }
                    tituloopigv = rs.getString("TITULOIGV");
                    opigv1 = rs.getDouble("IGV");
                    String opigv1x = formatter.format(opigv1);
                    String opigv1x1 = opigv1x.replace(".", ";");
                    String opigv1x2 = opigv1x1.replace(",", ".");
                    String opigv1x3 = opigv1x2.replace(";", ",");
                    if (opigv1x3.equals("0")|| opigv1x3.equals("0.0")|| opigv1x3.equals("0.00")){
                        opigv1x3 = "";
                    }
                    tituloopimportetotal = rs.getString("TITULOIMPORTETOTAL");
                    opimportetotal1 = rs.getDouble("IMPORTETOTAL");
                    String opimportetotx = formatter.format(opimportetotal1);
                    String opimportetotx1 = opimportetotx.replace(".", ";");
                    String opimportetotx2 = opimportetotx1.replace(",", ".");
                    String opimportetotx3 = opimportetotx2.replace(";", ",");
                    if (opimportetotx3.equals("0")|| opimportetotx3.equals("0.0")|| opimportetotx3.equals("0.00")){
                        opimportetotx3 = "";
                    }
                    tituloopredondeo = rs.getString("TITULOREDONDEO");
                    Redondeo1 = rs.getDouble("REDONDEO");
                    String Redondeo1x = formatter.format(Redondeo1);
                    String Redondeo1x1 = Redondeo1x.replace(".", ";");
                    String Redondeo1x2 = Redondeo1x1.replace(",", ".");
                    String Redondeo1x3 = Redondeo1x2.replace(";", ",");
                    if (Redondeo1x3.equals("0")|| Redondeo1x3.equals("0.0")|| Redondeo1x3.equals("0.00")){
                        Redondeo1x3 = "";
                    }
                    titulooptotalpagar = rs.getString("TITULOTOTALPAGAR");
                    optotalpagar1 = rs.getDouble("TOTALPAGAR");
                    String optotalpagar1x = formatter.format(optotalpagar1);
                    String optotalpagar1x1 = optotalpagar1x.replace(".", ";");
                    String optotalpagar1x2 = optotalpagar1x1.replace(",", ".");
                    String optotalpagar1x3 = optotalpagar1x2.replace(";", ",");
                    if (optotalpagar1x3.equals("0")|| optotalpagar1x3.equals("0.0")|| optotalpagar1x3.equals("0.00")){
                        optotalpagar1x3 = "";
                    }
                    titulooptotaldscto = rs.getString("TITULOTOTALDSCTO");
                    opdescuento1 = rs.getDouble("TOTALDSCTO");
                    String opdescuento1x = formatter.format(opdescuento1);
                    String opdescuento1x1 = opdescuento1x.replace(".", ";");
                    String opdescuento1x2 = opdescuento1x1.replace(",", ".");
                    String opdescuento1x3 = opdescuento1x2.replace(";", ",");
                    if (opdescuento1x3.equals("0")|| opdescuento1x3.equals("0.0")|| opdescuento1x3.equals("0.00")){
                        opdescuento1x3 = "";
                    }

                    disclaimer = rs.getString("DISCLAIMER");
                    rucbarcode = rs.getString("RUCBARCODE");
                    tipdocbarcode = rs.getString("TIPODOCUMENTOBARCODE");
                    igvbarcode = rs.getString("IGVBARCODE");
                    totalbarcode = rs.getString("TOTALBARCODE");
                    tipodocadquiriente = rs.getString("TIPODOCADQUIRIENTE");
                    numerodocadquiriente = rs.getString("NUMERODOCADQUIRIENTE");
                    firmabarcode = rs.getString("Firma");

                lista_VentaFinal.add(new Venta(botica,Direccion,DireccionFiscal,RUC,
                        serie,numero,TotFinal,fecha,cliente,direcCliente,tipodocumento,numdocumento,
                        documentoemitido,serienumero,hora,idvta,cajero,vendedor,money,observacion,
                        docreferencia,sernumref,fecharef,cantidad,producto,totaldetx3,opgravada1x3,
                        opinafecta1x3,opigv1x3,opimportetotx3,Redondeo1x3,optotalpagar1x3,opdescuento1x3,
                        idmoneda,disclaimer,tituloopgravada,tituloopinafecta,tituloopigv,tituloopimportetotal,
                        tituloopredondeo,titulooptotalpagar,titulooptotaldscto,
                        rucbarcode,tipdocbarcode,igvbarcode,totalbarcode,tipodocadquiriente,numerodocadquiriente,firmabarcode
                        ));
                }

                
                file = new FileWriter(Impresora_Boleta);
                //file = new FileWriter("//10.10.1.253//BIXOLON//");
                buffer = new BufferedWriter(file);
                ps = new PrintWriter(buffer);

/*Modo impresion*/ //termica

                if (modoImpresionDoc.equals("1")){

                      String cadenaPDF417 = rucbarcode.trim()+"|"+tipdocbarcode.trim()+"|"+igvbarcode.trim()+"|"+totalbarcode.trim()+"|"+tipodocadquiriente.trim()+"|"+numerodocadquiriente.trim()+"|"+firmabarcode.trim();
                      Numero_a_Letra NumLetra = new Numero_a_Letra();
                      ConvTotaLetras = NumLetra.Convertir(optotfinal, true,idmoneda);

                      imprimeBoletaNueva(listaInternos,idbotica,ConvTotaLetras,lista_VentaFinal,cadenaPDF417/*,
                              rucbarcode,tipdocbarcode,serie,numero,igvbarcode,totalbarcode,fecha,tipodocadquiriente,
                              numerodocadquiriente,firmabarcode*/);
                      //imprimeAdicionalNueva(listaInternos,idbotica,lista_VentaFinal);

                }
/*Fin modo impresion*/

                ps.close();

                }//fin del ticket Boleta
                rs.close();
                rs1.close();
                //rs2.close();
                rs3.close();
                conex.close();
                //listaInternos.set(i, null);
                listaInternos.clear();


        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora(ex.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora(et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception", listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Impresion_Ticketera(List<Venta> listaInternos, String idbotica, int tipventa,double vueltoT) {
        String Direccion = "", DireccionFiscal = "", botica = "", RUC = "", Delivery = "", DNI = "", PLACA = "",money = "";
        String correo = "", serie = null, numero = "", fecha = "", maq = "", hora = "";        
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0,Redondeo=0.0,TotFinal=0.0,OP_GRATUITA=0.0;
        String cliente = "", vendedor = null, cajero = null;
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> TpDescripcion = new ArrayList<String>();
        List<String> Cantidad = new ArrayList<String>();
        List<String> DecTP = new ArrayList<String>();
        List<String> TotTP = new ArrayList<String>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> punit = new ArrayList<Double>();
        List<String> lab = new ArrayList<String>();
        String CantidadPedida = "", Np = "",Np1 = "",direcCliente="", Nomvendedor="",Nomcajero="";
        String DescripcionTP ="";
        String montoPago = "";
        String vuelto = "";
        String TotalTP = "";
        Integer Largo = 0;
        int lineas = 0;
        int dos = 1;
        CallableStatement procedure, procedure1, procedure3, procedure4, procedure5;
        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps;

        try {

            String modoImpresionDoc = listaInternos.get(0).getmodoimpresion().toString().trim(); 
            String impresionDouble = listaInternos.get(0).getconfigimpresion().toString().trim();
            if (impresionDouble.equals("1")){
                dos = 2;
            }else{
                dos = 1;
            }

            conex = new ConexionPool().getConnection();
            for (int i = 0; i < dos; i++) { //Inicio del ticket boleta
            //for (int i = 0; i < listaInternos.size(); i++) { //Inicio del ticket boleta
                String miinterno = listaInternos.get(0).getId_Venta().toString().trim();
                procedure = conex.prepareCall("{ call RECUPERA_DATA_TICKET_BOLETA(?,?) }");
                procedure.setString(1, miinterno);
                procedure.setString(2, idbotica);
                rs = procedure.executeQuery();

                /*procedure1 = conex.prepareCall("{ call RECUPERA_DETALLE_TICKET_BOLETA(?,?) }");
                procedure1.setString(1, miinterno);
                procedure1.setString(2, idbotica);
                rs1 = procedure1.executeQuery();

                procedure3 = conex.prepareCall("{ call RECUPERA_MONTOTIPOVENTA(?) }");
                procedure3.setInt("TIPVENTA", tipventa);
                rs2 = procedure3.executeQuery();
                */
                ProductoDescripcion.removeAll(ProductoDescripcion);
                TpDescripcion.removeAll(TpDescripcion);

                Cantidad.removeAll(Cantidad);
                Precio.removeAll(Precio);
                punit.removeAll(punit);
                lab.removeAll(lab);
                DecTP.remove(DecTP);
                TotTP.remove(TotTP);

                while (rs.next()) {
                    botica = rs.getString("mibotica");
                    Direccion = rs.getString("Direccion");
                    DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                    serie = rs.getString("Serie");
                    numero = rs.getString("Numero");
                    fecha = String.valueOf(rs.getDate("Fecha_Venta"));
                    hora = rs.getString("Hora_Venta");
                    Total = rs.getDouble("Total");
                    vendedor = rs.getString("vendedor");
                    cajero = rs.getString("cajero");
                    RUC = rs.getString("RUCEmpresa");
                    Delivery = rs.getString("Delivery");
                    correo = rs.getString("CorreoEmpresa");
                    cliente = rs.getString("NOMCLIENTE");
                    maq = rs.getString("MISERIE");
                    DNI = rs.getString("DNICLIENTE");
                    PLACA = rs.getString("PLACA");
                    opgravada = rs.getDouble("Op_Gravada");
                    opinafecta = rs.getDouble("Op_Inafecta");
                    opigv = rs.getDouble("IGV");
                    opdescuento = rs.getDouble("Op_Descuento");
                    money = rs.getString("Id_Moneda");
                    Redondeo = rs.getDouble("Redondeo");
                    TotFinal = rs.getDouble("TotFinal");
                    direcCliente = rs.getString("DIRECCION_CLIENTE");
                    OP_GRATUITA=rs.getDouble("OP_GRATUITA");//CODIGO_MAX y aumente variable OP_GRATUTITA double aumento en el proc de ventas.OP_GRATUITA
                    /*Nomvendedor = rs.getString("Nomvendedor");
                    Nomcajero = rs.getString("Nomcajero");
                    montoPago = rs.getString("MontoPago");
                    vuelto = rs.getString("vuelto");*/
                }

                /*while (rs1.next()) {
                    ProductoDescripcion.add(rs1.getString(2));
                    
                    if (rs1.getInt(3) > 0) {
                        CantidadPedida = String.valueOf(rs1.getInt(3));
                    } else {
                        CantidadPedida = "";
                    }
                    if (rs1.getInt(4) > 0) {
                        CantidadPedida = CantidadPedida + "F" + String.valueOf(rs1.getInt(4));
                    }
                    Cantidad.add(CantidadPedida);
                    Precio.add(rs1.getDouble(5));

                    punit.add(rs1.getDouble(6));

                    lab.add(rs1.getString(7));
                }

                rs2.next();
                double MiMonto = rs2.getDouble("Monto");
                */
                fecha = objFechaHora.MysqlToJuliano(fecha).toString();                
                BigDecimal bd = new BigDecimal(Total);
                BigDecimal bdopgravada = new BigDecimal(opgravada);
                BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                BigDecimal bdigv = new BigDecimal(opigv);
                BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                BigDecimal bdopgratuita = new BigDecimal(OP_GRATUITA);//CODIGO_MAX

                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                Total = bd.doubleValue();
                String sumaTotal = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opgravada = bdopgravada.doubleValue();
                String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opinafecta = bdopinafecta.doubleValue();
                String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opigv = bdigv.doubleValue();
                String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opdescuento = bdopdescuento.doubleValue();
                String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                Redondeo = bdopredondeo.doubleValue();
                String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                TotFinal = bdoptotfinal.doubleValue();
                String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();               

                bdopgratuita = bdopgratuita.setScale(podecimal, BigDecimal.ROUND_HALF_UP);//CODIGO_MAX
                OP_GRATUITA = bdopgratuita.doubleValue();//CODIGO_MAX
                String opgratu = bdopgratuita.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();//CODIGO_MAX
                
                String delimiter = "/";
                String[] temp;                
                temp = fecha.split(delimiter);
                                
                String leerfecha = temp[2]+temp[1]+temp[0];                
                String leerStrTipoDoc = listaInternos.get(0).getidtipdocsunat();
                String leerNumero = listaInternos.get(0).getnumerFE();
                
                String LeerNuevaRutaXML = "/mnt/XML/" + leerfecha + "/" + idbotica + "/";         //   1
                //String LeerNuevaRutaXML = "D:\\XML\\" + leerfecha + "\\" + idbotica + "\\";
                String LeerMyFile = RUC + '-' + leerStrTipoDoc + '-' + serie + '-' + leerNumero;
                
                String XMLGENERADO = objXML_GENERAR.readXML(miinterno,LeerNuevaRutaXML,LeerMyFile);
                String recSignature = objXML_GENERAR.readXMLSignature(miinterno,LeerNuevaRutaXML,LeerMyFile);                
                
                procedure4 = conex.prepareCall("{ call GUARDA_FIRMADIGITAL(?,?,?,?,?,?) }");
                procedure4.setString("PId_Botica", idbotica);
                procedure4.setString("PId_Venta", miinterno);
                procedure4.setString("PId_Serie", serie);
                procedure4.setString("PId_Numero", numero);
                procedure4.setString("PDigest", XMLGENERADO);
                procedure4.setString("PSignature", recSignature);
                rs3 = procedure4.executeQuery();
                
                while (rs3.next()) {
                String firma =  rs3.getString("resultado");
                }  

                file = new FileWriter(Impresora_Boleta);
                //file = new FileWriter("//10.10.1.253//BIXOLON//");
                buffer = new BufferedWriter(file);
                ps = new PrintWriter(buffer);

/*Modo impresion*/ //termica

      if (modoImpresionDoc.equals("1")){


          Numero_a_Letra NumLetra = new Numero_a_Letra();
          String ConvTotaLetras = NumLetra.Convertir(optotfinal, true,money);

          imprimeBoleta(listaInternos,idbotica,ConvTotaLetras,vueltoT);
          //imprimeAdicional(listaInternos,idbotica);

                /*setFormato(1, ps);
                ps.println(botica);
                ps.println(Direccion);
                ps.println(DireccionFiscal);
                ps.println("RUC :" + RUC);
                Dibuja_Linea(ps);
                ps.println("          BOLETA DE VENTA ELECTRONICA ");//8
                ps.println("             " + serie + " - " + numero);//11
                Dibuja_Linea(ps);                
                ps.println("Fecha :    " + fecha + "  Hora :    " + hora);
                ps.println("Caj   :    " + cajero + " Ven : " + vendedor + " Int : " + miinterno);
                if (PLACA != null) {
                    ps.println("Placa   : " + PLACA);
                }
                if (!cliente.equals("")){
                Dibuja_Linea(ps);
                ps.println("Sr(a)     :" + cliente.trim());
                ps.println("DNI       :" + DNI);
                ps.println("DIRECCION :" + direcCliente);*/
                        /*if (Total >= MiMonto) {
                            ps.println("   DNI       :" + DNI);
                        }*/
                /*}
                Dibuja_Linea(ps);
                ps.println("Cant     " + "Descripcion" + "                     " + "Importe");//8
                Dibuja_Linea(ps);
                lineas = 7;
                */
                /*
                --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                 */

                /*int maximo = Obtener_Ancho(Cantidad);
                maximo++;
                Align formato = new Align(15, Align.JUST_RIGHT,null);

                for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    String cantidad = "";

                    for (Integer inicioP = k; inicioP < ProductoDescripcion.size(); inicioP++) {
                        cantidad = "";
                        int Largo1 = Cantidad.get(inicioP).toString().trim().length();

                        if (Largo1 > maximo) {
                            cantidad = Cantidad.get(inicioP).toString().substring(0, maximo);
                        } else {
                            cantidad = Cantidad.get(inicioP).toString().trim();
                            for (Integer cor = Largo1; cor < maximo; cor++) {
                                cantidad = cantidad + " ";
                            }
                        }

                        Largo = 0;
                        Largo = ProductoDescripcion.get(inicioP).toString().length();

                        if (Largo > 27) {
                            Np = ProductoDescripcion.get(inicioP).toString().substring(0, Largo);//25
                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 40; lpp++) { //30
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));
                            Np = Np + espacio1 + bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            */
                                    /*aqui
                                     */
                                    /*if(Precio.get(k)> 0.0){
                                    if (!lab.get(k).equals("DCTO")){
                                    Np = Np + "\n" + espacio1 + cantidad + "Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    }
                                    }*/
                           /*
                            String espacio = "";
                            for (int ki = 0; ki < maximo; ki++) {
                                espacio += " ";
                            }
                            //Np = Np + "\n" + espacio + ProductoDescripcion.get(inicioP).toString().substring(25, Largo);//25

                        } else {
                            Np = ProductoDescripcion.get(inicioP).toString().trim();

                            for (Integer cor = ProductoDescripcion.get(inicioP).toString().length(); cor < 20; cor++) {
                                Np = Np + " ";
                            }

                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 30; lpp++) {//29
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));
                            Np = Np + espacio1 + formato.format(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            */
                                    /*aqui
                                     */
                                    /*if(Precio.get(k)> 0.0){
                                    if (!lab.get(k).equals("DCTO")){
                                    Np = Np + "\n" + "   " + cantidad + "Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    }
                                    }*/
                        /*}

                        cantidad = cantidad + Np;
                        //cantidad = Np;
                        break;

                    }
                    ps.println(" " + cantidad);
                    lineas++;
                }

                Numero_a_Letra NumLetra = new Numero_a_Letra();
                Total = Math.round(Total * Math.pow(10, 2)) / Math.pow(10, 2);
                
                Align formato1 = new Align(28, Align.JUST_RIGHT,null);
                
                Dibuja_Linea(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }
                */
                        /*if (OP_GRATUITA<0)//CODIGO_MAX
                            ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                        */
                        //ps.println();

                /*char otilde = 162; //ó
                char utilde = 163; //ú
                String ultimo = "              ";
                //ultimo += "                   ";
                ps.println(ultimo);
                ps.println(" " + NumLetra.Convertir(optotfinal, true,money));
                ps.println(" ");
                ps.println("       " + XMLGENERADO);//5
                ps.println(" ");
                ps.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                ps.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                ps.println("www.nortfarma.com.pe");
                ps.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                ps.println("ESTIMADO CLIENTE:");
                ps.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                ps.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                ps.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                ps.println("NRO. 007-99/SUNAT.");
                ps.println("GRACIAS POR SU COMPRA");
                
                */
                
                            //ps.println("Hola pap\u00e1 \u00F1" );
                            //ps.println("u00e1 \n u00e9 \n \u00ed \n \u00f3 \n \u00fa \n \u00c1");

                            /*String file2 = "D://archivoConTildes.txt";
                             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2, true), "UTF8"));

                            BufferedReader  in = new BufferedReader (new InputStreamReader (new FileInputStream (file2), "utf-8"));
                            try{
                                while ((file2 = in.readLine())!=null) {
                                      System.out.println(file2);
                                      ps.println(file2);

                                }
                            }
                            catch (Exception e){

                            }
                            finally{
                              in.close();
                            }*/


                //correr(6, ps);//9
                /*cortar(ps);

                ps.println("" + botica);                
                //Dibuja_Linea(ps);
                ps.println("         BOLETA DE VENTA ELECTRONICA ");//8
                ps.println("          " + serie + " - " + numero);
                Dibuja_Linea(ps);
                ps.println("Fecha :    " + fecha + "   Hora :   " + hora);
                ps.println("Caj   :    " + cajero + "  Ven : " + vendedor + " Int : " + miinterno);

                Dibuja_Linea(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                if(opinafecta >0){
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                }
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                if(Redondeo < 0){
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                }
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }                
                correr(4, ps); //8
                cortar(ps);*/
                

    }else{ // ticketera

              setFormato(1, ps);
                //ps.println("/imagenes/logo");
                ps.println(botica);
                ps.println(Direccion);
                ps.println(DireccionFiscal);
                ps.println("RUC :" + RUC);
                Dibuja_Linea1(ps);
                ps.println("     BOLETA DE VENTA ELECTRONICA ");//8
                ps.println("      " + serie + " - " + numero);//11
                Dibuja_Linea1(ps);
                ps.println("Fecha : " + fecha + "  Hora : " + hora);
                ps.println("Caj   : " + cajero + " Ven : " + vendedor + " Int : " + miinterno);
                if (PLACA != null) {
                    ps.println("Placa   : " + PLACA);
                }
                if (!cliente.equals("")){
                Dibuja_Linea1(ps);
                ps.println("Sr(a)    :" + cliente.trim());
                ps.println("DNI      :" + DNI);
                ps.println("DIRECCION:" + direcCliente);
                /*if (Total >= MiMonto) {
                    ps.println("   DNI       :" + DNI);
                }*/
                }
                Dibuja_Linea1(ps);
                ps.println("Cant     " + "Descripcion" + "        " + "Importe");//8
                Dibuja_Linea1(ps);
                lineas = 7;

                /*
                --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                 */

                int maximo = Obtener_Ancho(Cantidad);
                maximo++;
                Align formato = new Align(15, Align.JUST_RIGHT,null);

                for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    String cantidad = "";

                    for (Integer inicioP = k; inicioP < ProductoDescripcion.size(); inicioP++) {
                        cantidad = "";
                        int Largo1 = Cantidad.get(inicioP).toString().trim().length();

                        if (Largo1 > maximo) {
                            cantidad = Cantidad.get(inicioP).toString().substring(0, maximo);
                        } else {
                            cantidad = Cantidad.get(inicioP).toString().trim();
                            for (Integer cor = Largo1; cor < maximo; cor++) {
                                cantidad = cantidad + " ";
                            }
                        }

                        Largo = 0;
                        Largo = ProductoDescripcion.get(inicioP).toString().length();

                        if (Largo > 25) {
                            Np = ProductoDescripcion.get(inicioP).toString().substring(0, 26);//Largo
                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));
                            Np = Np + espacio1 + bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            String espacio = "";
                            for (int ki = 0; ki < maximo; ki++) {
                                espacio += " ";
                            }
                            Np = Np + "\n" + espacio + ProductoDescripcion.get(inicioP).toString().substring(26, Largo);//25


                            /*aqui
                            */
                            /*if(Precio.get(k)> 0.0){
                            if (!lab.get(k).equals("DCTO")){
                            Np = Np + "\n" + espacio + cantidad + "Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            }
                            }*/



                        } else {
                            Np = ProductoDescripcion.get(inicioP).toString().trim();

                            for (Integer cor = ProductoDescripcion.get(inicioP).toString().length(); cor < 20; cor++) {
                                Np = Np + " ";
                            }

                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 28; lpp++) {//29
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));
                            //Np = Np + espacio1 + formato.format(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            Np = Np + espacio1 + bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            /*aqui
                             */
                            /*if(Precio.get(k)> 0.0){
                            if (!lab.get(k).equals("DCTO")){
                            Np = Np + "\n" + "   " + cantidad + "Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            }
                            }*/
                        }

                        cantidad = cantidad + Np;
                        //cantidad = Np;
                        break;

                    }
                    ps.println(" " + cantidad);
                    lineas++;
                }

                Numero_a_Letra NumLetra = new Numero_a_Letra();
                Total = Math.round(Total * Math.pow(10, 2)) / Math.pow(10, 2);

                Align formato1 = new Align(15, Align.JUST_RIGHT,null);

                Dibuja_Linea1(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }
                //if (OP_GRATUITA<0)//CODIGO_MAX
                //   ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                //ps.println();
                String ultimo = "              ";
                //ultimo += "                   ";
                ps.println(ultimo);
                ps.println(" " + NumLetra.Convertir(optotfinal, true,money));
                ps.println("");
                ps.println("    " + XMLGENERADO);//5
                ps.println(" ");
                ps.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                        + "VENTA ELECTRONICA PUEDE SER");
                ps.println("CONSULTADO EN www.nortfarma.com.pe");
                ps.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                ps.println("ESTIMADO CLIENTE: ");
                ps.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                                + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                ps.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                                + "NRO. 007-99/SUNAT.");
                ps.println("GRACIAS POR SU COMPRA");



                correr(9, ps);//9
                cortar(ps);
                /*
                ps.println(botica);
                Dibuja_Linea1(ps);
                ps.println("    BOLETA DE VENTA ELECTRONICA ");//8
                ps.println("     " + serie + " - " + numero);
                Dibuja_Linea1(ps);
                ps.println("Fecha : " + fecha + "  Hora : " + hora);
                ps.println("Caj   : " + cajero + "  Ven : " + vendedor + " Int : " + miinterno);

                Dibuja_Linea1(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                if(opinafecta >0){
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                }
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                if(Redondeo < 0){
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                }
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }
                correr(8, ps); //8
                cortar(ps);
*/
    }
/*Fin modo impresion*/
                
                ps.close();

                }//fin del ticket Boleta
                rs.close();
                rs1.close();
                //rs2.close();
                rs3.close();
                conex.close();
                //listaInternos.set(i, null);
                listaInternos.clear();
            

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora(ex.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora(et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception", listaInternos);
            JOptionPane.showMessageDialog(null,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void imprimeBoletaNueva(List<Venta> lstInternos,String idbotica, String ConvTotLetras,List<Venta> lstVentaFinal,
            String cadena
        /*,String rucbarcode1, String tipdocbarcode1,String seriebardcode, String numerobarcode,String igvbarcode1,
        String totalbarcode1, String fechabarcode, String tipodocadquiriente1, String numerodocadquiriente1,
        String firmabarcode1*/){

        String sistema = "Windows";

        try {

            String miinternovta = lstInternos.get(0).getId_Venta().toString().trim();
            
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            /*parameters.put("PID_NUMLETRAS", ConvTotLetras);
            parameters.put("cadpdf417", cadena);            
            parameters.put("PID_RUCBARCODE", rucbarcode1);
            parameters.put("PID_TIPDOCBARCODE", tipdocbarcode1);
            parameters.put("PID_SERIEBARCODE", seriebardcode);
            parameters.put("PID_NUMEROBARCODE", numerobarcode);
            parameters.put("PID_IGVBARCODE", igvbarcode1);
            parameters.put("PID_TOTALBARCODE", totalbarcode1);
            parameters.put("PID_FECHABARCODE", fechabarcode);
            parameters.put("PID_TIPDOCADQUIRIENTE", tipodocadquiriente1);
            parameters.put("PID_NUMDOCADQUIRIENTE", numerodocadquiriente1);
            parameters.put("PID_FIRMABARCODE", firmabarcode1);*/

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVtaNuevo-OTRO.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            //jasperPrint = JasperFillManager.fillReport(masterReport, parameters, new  JRBeanCollectionDataSource(lstVentaFinal));
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);

            JasperPrintManager.printReport(jasperPrint, false);

            conex.close();

        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public void imprimeAdicionalNueva(List<Venta> lstInternos,String idbotica,List<Venta> lstVentaFinal1) {
        String sistema = "Windows";

        try {

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteAdicionalNvo-OTRO.jasper");
            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, null, new  JRBeanCollectionDataSource(lstVentaFinal1));

            JasperPrintManager.printReport(jasperPrint, false);

            conex.close();
        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

      public void imprimeBoleta(List<Venta> lstInternos,String idbotica, String ConvTotLetras,double vuelto) {
        String sistema = "Windows";
        //conex = new ConexionPool().getConnection();
        
        String miinternovta = lstInternos.get(0).getId_Venta().toString().trim();

        try {
            
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);
            //parameters.put("P_VUELTOT", vuelto);
            

            if (obj1.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVta.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            //JasperViewer view = new JasperViewer(jasperPrint, false);

            JasperPrintManager.printReport(jasperPrint, false);

            //view.setTitle("REPORTE DE COMPROBANTES");
            //view.setVisible(true);
            //view.setSize(300, 500);
            conex.close();
        
        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        
    }

    public void imprimeAdicional(List<Venta> lstInternos,String idbotica) {
        String sistema = "Windows";
        conex = new ConexionPool().getConnection();
        String miinternovta = lstInternos.get(0).getId_Venta().toString().trim();
        String ConvTotLetras = "a";
        try {
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            if (obj1.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteAdicional.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            //JasperViewer view = new JasperViewer(jasperPrint, false);

            JasperPrintManager.printReport(jasperPrint, false);

            //view.setTitle("REPORTE DE COMPROBANTES");
            //view.setVisible(true);
            //view.setSize(300, 500);
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
    
    public boolean ReImpresion_Ticketera(List<Venta> listaInternos, String idbotica, int tipventa) {

        String Direccion = "", DireccionFiscal = "", botica = "", RUC = "", Delivery = "", DNI = "", PLACA = "",money = "";
        String correo = "", serie = null, numero = "", fecha = "", maq = "", hora = "";
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0,Redondeo=0.0,TotFinal=0.0,OP_GRATUITA=0.0;
        String cliente = "", vendedor = null, cajero = null;
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Cantidad = new ArrayList<String>();
        List<Double> punit = new ArrayList<Double>();
        List<Double> Precio = new ArrayList<Double>();
        List<String> lab = new ArrayList<String>();
        String CantidadPedida = "", Np = "",direcCliente="", modoimpre="1";
        Integer Largo = 0;
        int lineas = 0;
        CallableStatement procedure, procedure1, procedure3;
        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps;
        boolean valor = false;

        String ConvTotaLetras ="";
        String /*direcCliente="",*/ Nomvendedor="",Nomcajero="",tipodocumento="",
                numdocumento="",documentoemitido="",serienumero="",idvta="",observacion="",docreferencia="",
                sernumref="",fecharef = "", cantidad="", producto="",simbolo="",idmoneda="",disclaimer="",
                rucbarcode = "",tipdocbarcode="",igvbarcode="",totalbarcode="",tipodocadquiriente="",
                numerodocadquiriente="",firmabarcode="",tituloopgravada="",tituloopinafecta="",tituloopigv="",
                tituloopimportetotal="",tituloopredondeo="",titulooptotalpagar="",titulooptotaldscto="",
                fecha_aux = ""/*, hora = "",money = ""*/;

        Double  /*OP_GRATUITA=0.0,*/opimportetotal=0.0,optotalpagar=0.0,
                opimportetotal1=0.0, opgravada1=0.0,opinafecta1 =0.0, opigv1 = 0.0, Redondeo1 = 0.0,
                optotalpagar1 = 0.0, opdescuento1=0.0,totaldet=0.0;

        try {            

            conex = new ConexionPool().getConnection();
            for (int i = 0; i < listaInternos.size(); i++) { //Inicio del ticket boleta
                String miinterno = listaInternos.get(i).getId_Venta().toString().trim();
                //procedure = conex.prepareCall("{ call PDF_VENTAFINAL(?,?) }");
                procedure = conex.prepareCall("{ call RECUPERA_DATA_TICKET_BOLETA(?,?) }");
                procedure.setString(1, miinterno);
                //procedure.setString(2, ConvTotaLetras);
                procedure.setString(2, idbotica);
                rs = procedure.executeQuery();

                procedure1 = conex.prepareCall("{ call RECUPERA_DETALLE_TICKET_BOLETA(?,?) }");
                procedure1.setString(1, miinterno);
                procedure1.setString(2, idbotica);
                rs1 = procedure1.executeQuery();

                procedure3 = conex.prepareCall("{ call RECUPERA_MONTOTIPOVENTA(?) }");
                procedure3.setInt("TIPVENTA", tipventa);
                rs2 = procedure3.executeQuery();
                
                ProductoDescripcion.removeAll(ProductoDescripcion);
                Cantidad.removeAll(Cantidad);
                punit.removeAll(punit);
                Precio.removeAll(Precio);
                lab.removeAll(lab);
                lista_VentaFinal.removeAll(lista_VentaFinal);
                NumberFormat formatter = new DecimalFormat("#,##0.00");

                while (rs.next()) {

                    /*botica = rs.getString("Empresa_Botica");
                    Direccion = rs.getString("Direccion_Botica");
                    DireccionFiscal = rs.getString("Direccion_Empresa");
                    RUC = rs.getString("RUC");

                    cliente = rs.getString("NOMCLIENTE");
                    direcCliente = rs.getString("DIRECCION_CLIENTE");
                    tipodocumento = rs.getString("TIPO_DOCUMENTO");
                    numdocumento = rs.getString("NUMERO_DOCUMENTO");
                    documentoemitido = rs.getString("Documento");
                    serienumero = rs.getString("SerNum");
                    fecha_aux = String.valueOf(rs.getDate("Fecha_Venta"));
                    fecha = objFechaHora.MysqlToJuliano(fecha_aux).toString();
                    hora = rs.getString("Hora_Venta");
                    idvta = rs.getString("Id_Venta");
                    cajero = rs.getString("CAJERO");
                    vendedor = rs.getString("VENDEDOR");
                    TotFinal = rs.getDouble("Total");
                    money = rs.getString("MONEDA");
                    idmoneda = rs.getString("Id_Moneda");
                    observacion = rs.getString("Observacion");
                    docreferencia = rs.getString("DocumentoRef");
                    sernumref = rs.getString("SerNumRef");
                    fecharef = rs.getString("Fecha_VentaRef");
                    serie = rs.getString("Serie");
                    numero = rs.getString("Numero");
                    cantidad = rs.getString("Cantidad");
                    producto = rs.getString("Producto");
                    totaldet = rs.getDouble("Totaldet");
                    String totaldetx = formatter.format(totaldet);
                    String totaldetx1 = totaldetx.replace(".", ";");
                    String totaldetx2 = totaldetx1.replace(",", ".");
                    String totaldetx3 = totaldetx2.replace(";", ",");

                    tituloopgravada = rs.getString("TITULOOPGRAVADA");
                    opgravada1 = rs.getDouble("OPGRAVADA");
                    String opgravada1x = formatter.format(opgravada1);
                    String opgravada1x1 = opgravada1x.replace(".", ";");
                    String opgravada1x2 = opgravada1x1.replace(",", ".");
                    String opgravada1x3 = opgravada1x2.replace(";", ",");
                    if (opgravada1x3.equals("0")|| opgravada1x3.equals("0.0")|| opgravada1x3.equals("0.00")){
                        opgravada1x3 = "";
                    }
                    tituloopinafecta = rs.getString("TITULOINAFECTA");
                    opinafecta1 = rs.getDouble("OPINAFECTA");
                    String opinafecta1x = formatter.format(opinafecta1);
                    String opinafecta1x1 = opinafecta1x.replace(".", ";");
                    String opinafecta1x2 = opinafecta1x1.replace(",", ".");
                    String opinafecta1x3 = opinafecta1x2.replace(";", ",");
                    if (opinafecta1x3.equals("0")|| opinafecta1x3.equals("0.0")|| opinafecta1x3.equals("0.00")){
                       opinafecta1x3 = "";
                    }
                    tituloopigv = rs.getString("TITULOIGV");
                    opigv1 = rs.getDouble("IGV");
                    String opigv1x = formatter.format(opigv1);
                    String opigv1x1 = opigv1x.replace(".", ";");
                    String opigv1x2 = opigv1x1.replace(",", ".");
                    String opigv1x3 = opigv1x2.replace(";", ",");
                    if (opigv1x3.equals("0")|| opigv1x3.equals("0.0")|| opigv1x3.equals("0.00")){
                       opigv1x3 = "";
                    }
                    tituloopimportetotal = rs.getString("TITULOIMPORTETOTAL");
                    opimportetotal1 = rs.getDouble("IMPORTETOTAL");
                    String opimportetotx = formatter.format(opimportetotal1);
                    String opimportetotx1 = opimportetotx.replace(".", ";");
                    String opimportetotx2 = opimportetotx1.replace(",", ".");
                    String opimportetotx3 = opimportetotx2.replace(";", ",");
                    if (opimportetotx3.equals("0")|| opimportetotx3.equals("0.0")|| opimportetotx3.equals("0.00")){
                        opimportetotx3 = "";
                    }
                    tituloopredondeo = rs.getString("TITULOREDONDEO");
                    Redondeo1 = rs.getDouble("REDONDEO");
                    String Redondeo1x = formatter.format(Redondeo1);
                    String Redondeo1x1 = Redondeo1x.replace(".", ";");
                    String Redondeo1x2 = Redondeo1x1.replace(",", ".");
                    String Redondeo1x3 = Redondeo1x2.replace(";", ",");
                    if (Redondeo1x3.equals("0")|| Redondeo1x3.equals("0.0")|| Redondeo1x3.equals("0.00")){
                       Redondeo1x3 = "";
                    }
                    titulooptotalpagar = rs.getString("TITULOTOTALPAGAR");
                    optotalpagar1 = rs.getDouble("TOTALPAGAR");
                    String optotalpagar1x = formatter.format(optotalpagar1);
                    String optotalpagar1x1 = optotalpagar1x.replace(".", ";");
                    String optotalpagar1x2 = optotalpagar1x1.replace(",", ".");
                    String optotalpagar1x3 = optotalpagar1x2.replace(";", ",");
                    if (optotalpagar1x3.equals("0")|| optotalpagar1x3.equals("0.0")|| optotalpagar1x3.equals("0.00")){
                        optotalpagar1x3 = "";
                    }
                    titulooptotaldscto = rs.getString("TITULOTOTALDSCTO");
                    opdescuento1 = rs.getDouble("TOTALDSCTO");
                    String opdescuento1x = formatter.format(opdescuento1);
                    String opdescuento1x1 = opdescuento1x.replace(".", ";");
                    String opdescuento1x2 = opdescuento1x1.replace(",", ".");
                    String opdescuento1x3 = opdescuento1x2.replace(";", ",");
                    if (opdescuento1x3.equals("0")|| opdescuento1x3.equals("0.0")|| opdescuento1x3.equals("0.00")){
                        opdescuento1x3 = "";
                    }

                    disclaimer = rs.getString("DISCLAIMER");

                    rucbarcode = rs.getString("RUCBARCODE");
                    tipdocbarcode = rs.getString("TIPODOCUMENTOBARCODE");
                    igvbarcode = rs.getString("IGVBARCODE");
                    totalbarcode = rs.getString("TOTALBARCODE");
                    tipodocadquiriente = rs.getString("TIPODOCADQUIRIENTE");
                    numerodocadquiriente = rs.getString("NUMERODOCADQUIRIENTE");
                    firmabarcode = rs.getString("Firma");

                    lista_VentaFinal.add(new Venta(botica,Direccion,DireccionFiscal,RUC,
                            serie,numero,TotFinal,fecha,cliente,direcCliente,tipodocumento,numdocumento,
                            documentoemitido,serienumero,hora,idvta,cajero,vendedor,money,observacion,
                            docreferencia,sernumref,fecharef,cantidad,producto,totaldetx3,opgravada1x3,
                            opinafecta1x3,opigv1x3,opimportetotx3,Redondeo1x3,optotalpagar1x3,opdescuento1x3,
                            idmoneda,disclaimer,tituloopgravada,tituloopinafecta,tituloopigv,tituloopimportetotal,
                            tituloopredondeo,titulooptotalpagar,titulooptotaldscto,
                            rucbarcode,tipdocbarcode,igvbarcode,totalbarcode,tipodocadquiriente,numerodocadquiriente,firmabarcode
                            ));

                   */

                    botica = rs.getString("mibotica");
                    Direccion = rs.getString("Direccion");
                    DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                    serie = rs.getString("Serie");
                    numero = rs.getString("Numero");
                    fecha = String.valueOf(rs.getDate("Fecha_Venta"));
                    hora = rs.getString("Hora_Venta");
                    Total = rs.getDouble("Total");
                    vendedor = rs.getString("vendedor");
                    cajero = rs.getString("cajero");
                    RUC = rs.getString("RUCEmpresa");
                    Delivery = rs.getString("Delivery");
                    correo = rs.getString("CorreoEmpresa");
                    cliente = rs.getString("NOMCLIENTE");
                    maq = rs.getString("MISERIE");
                    DNI = rs.getString("DNICLIENTE");
                    PLACA = rs.getString("PLACA");
                    opgravada = rs.getDouble("Op_Gravada");
                    opinafecta = rs.getDouble("Op_Inafecta");
                    opigv = rs.getDouble("IGV");
                    opdescuento = rs.getDouble("Op_Descuento");
                    money = rs.getString("Id_Moneda");
                    Redondeo = rs.getDouble("Redondeo");
                    TotFinal = rs.getDouble("TotFinal");
                    direcCliente = rs.getString("DIRECCION_CLIENTE");
                    modoimpre = rs.getString("MODOIMPRESION");
                    OP_GRATUITA=rs.getDouble("OP_GRATUITA");
                }

                String modoImpresionDoc = modoimpre;

                while (rs1.next()) {
                    ProductoDescripcion.add(rs1.getString(2));
                    if (rs1.getInt(3) > 0) {
                        CantidadPedida = String.valueOf(rs1.getInt(3));
                    } else {
                        CantidadPedida = "";
                    }
                    if (rs1.getInt(4) > 0) {
                        CantidadPedida = CantidadPedida + "F" + String.valueOf(rs1.getInt(4));
                    }
                    Cantidad.add(CantidadPedida);
                    Precio.add(rs1.getDouble(5));

                    punit.add(rs1.getDouble(6));
                    lab.add(rs1.getString(7));
                }

                rs2.next();
                double MiMonto = rs2.getDouble("Monto");
                
                fecha = objFechaHora.MysqlToJuliano(fecha).toString();
                BigDecimal bd = new BigDecimal(Total);
                BigDecimal bdopgravada = new BigDecimal(opgravada);
                BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                BigDecimal bdigv = new BigDecimal(opigv);
                BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                BigDecimal bdopgratuita = new BigDecimal(OP_GRATUITA);//CODIGO_MAX

                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                Total = bd.doubleValue();
                String sumaTotal = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opgravada = bdopgravada.doubleValue();
                String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opinafecta = bdopinafecta.doubleValue();
                String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opigv = bdigv.doubleValue();
                String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opdescuento = bdopdescuento.doubleValue();
                String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                Redondeo = bdopredondeo.doubleValue();
                String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                
                bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                TotFinal = bdoptotfinal.doubleValue();
                String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                
                bdopgratuita = bdopgratuita.setScale(podecimal, BigDecimal.ROUND_HALF_UP);//CODIGO_MAX
                OP_GRATUITA = bdopgratuita.doubleValue();//CODIGO_MAX
                String opgratu = bdopgratuita.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();//CODIGO_MAX
                
                String StrTipoDoc = LeerLoadInterno(miinterno);

                String delimiter = "/";
                String delimiter1 = "-";
                String[] temp;
                String[] temp1;
                temp = fecha.split(delimiter);
                temp1 = StrTipoDoc.split(delimiter1);

                String leerfecha = temp[2]+temp[1]+temp[0];
                String leerStrTipoDoc = temp1[0];
                String leerNumero = temp1[1];


                String LeerNuevaRutaXML = "/mnt/XML/" + leerfecha + "/" + idbotica + "/";
                //String LeerNuevaRutaXML = "D:\\XML\\" + leerfecha + "\\" + idbotica + "\\";
                String LeerMyFile = RUC + '-' + leerStrTipoDoc + '-' + serie + '-' + leerNumero;
                objXML_GENERAR = new XML_GENERAR();
                String XMLGENERADOR = objXML_GENERAR.readXML(miinterno,LeerNuevaRutaXML,LeerMyFile);

                file = new FileWriter(Impresora_Boleta);
                //file = new FileWriter("//10.10.1.253//BIXOLON//");
                buffer = new BufferedWriter(file);
                ps = new PrintWriter(buffer);

/*Modo impresion*/ //termica
if (modoImpresionDoc.equals("1")){

            Numero_a_Letra NumLetra = new Numero_a_Letra();
            ConvTotaLetras = NumLetra.Convertir(optotfinal, true,money);
            //String cadenaPDF417 = rucbarcode.trim()+"|"+tipdocbarcode.trim()+"|"+igvbarcode.trim()+"|"+totalbarcode.trim()+"|"+tipodocadquiriente.trim()+"|"+numerodocadquiriente.trim()+"|"+firmabarcode.trim();

            //conex = new ConexionPool().getConnection();
            //imprimeBoletaNueva(listaInternos,idbotica,ConvTotaLetras,lista_VentaFinal,cadenaPDF417/*,
            //              rucbarcode,tipdocbarcode,serie,numero,igvbarcode,totalbarcode,fecha,tipodocadquiriente,
            //              numerodocadquiriente,firmabarcode*/);
            //conex.close();

            conex = new ConexionPool().getConnection();
            imprimeBoleta(listaInternos,idbotica,ConvTotaLetras,0);
            conex.close();
            
                /*setFormato(1, ps);
                ps.println(botica);
                ps.println(Direccion);
                ps.println(DireccionFiscal);
                ps.println("RUC :" + RUC);
                Dibuja_Linea(ps);
                ps.println("         BOLETA DE VENTA ELECTRONICA ");
                ps.println("           " + serie + " - " + numero);
                Dibuja_Linea(ps);     
                ps.println("Fecha :   " + fecha + "  Hora : " + hora);
                ps.println("Caj   :   " + cajero + " Ven : " + vendedor + " Int : " + miinterno);
                if (PLACA != null) {
                    ps.println("Placa   : " + PLACA);
                }
                if (!cliente.equals("")){
                Dibuja_Linea(ps);
                ps.println("Sr(a)     :" + cliente);
                ps.println("DNI       :" + DNI);
                ps.println("DIRECCION :" + direcCliente);
                        /*if (Total >= MiMonto) {
                            ps.println("   DNI       :" + DNI);
                        }*/
                /*}
                Dibuja_Linea(ps);
                ps.println(" Cant     " + "Descripcion" + "                      " + "Importe");
                Dibuja_Linea(ps);
                lineas = 7;

                /*
                --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                 */

                /*int maximo = Obtener_Ancho(Cantidad);
                maximo++;
                Align formato = new Align(15, Align.JUST_RIGHT,null);

                for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    String cantidad = "";

                    for (Integer inicioP = k; inicioP < ProductoDescripcion.size(); inicioP++) {
                        cantidad = "";
                        int Largo1 = Cantidad.get(inicioP).toString().trim().length();

                        if (Largo1 > maximo) {
                            cantidad = Cantidad.get(inicioP).toString().substring(0, maximo);
                        } else {
                            cantidad = Cantidad.get(inicioP).toString().trim();
                            for (Integer cor = Largo1; cor < maximo; cor++) {
                                cantidad = cantidad + " ";
                            }
                        }

                        Largo = 0;
                        Largo = ProductoDescripcion.get(inicioP).toString().length();

                        if (Largo > 27) {
                            Np = ProductoDescripcion.get(inicioP).toString().substring(0, Largo);//25
                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 40; lpp++) {//30
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));

                            Np = Np + espacio1 + bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            String espacio = "";
                            for (int ki = 0; ki < maximo; ki++) {
                                espacio += " ";
                            }
                            //Np = Np + "\n" + espacio + ProductoDescripcion.get(inicioP).toString().substring(25, Largo);

                                        /*aqui
                                        */
                                        /*if(Precio.get(k)> 0.0){
                                        if (!lab.get(k).equals("DCTO")){
                                        Np = Np + "\n" + espacio1 + cantidad + " Unidad x" + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        }
                                        }*/

                        /*} else {
                            Np = ProductoDescripcion.get(inicioP).toString().trim();

                            for (Integer cor = ProductoDescripcion.get(inicioP).toString().length(); cor < 20; cor++) {
                                Np = Np + " ";
                            }

                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 30; lpp++) { //29
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));

                            Np = Np + espacio1 + formato.format(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                                        /*aqui
                                        */
                                        /*if(Precio.get(k)> 0.0){
                                        if (!lab.get(k).equals("DCTO")){
                                        Np = Np + "\n" + "   " + cantidad + " Unidad x" + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        }
                                        }*/
                        //}

                        //cantidad = cantidad + Np;
                        //cantidad = Np;
                        //break;

                    /*}
                    ps.println(" " + cantidad);
                    lineas++;
                }

                Numero_a_Letra NumLetra = new Numero_a_Letra();
                Total = Math.round(Total * Math.pow(10, 2)) / Math.pow(10, 2);

                Align formato1 = new Align(28, Align.JUST_RIGHT,null);

                Dibuja_Linea(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }
                //if (OP_GRATUITA<0)//CODIGO_MAX
                //   ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX

          //CODIGO DE BARRAS GINO

/*
                JBarcodeBean barcode = new JBarcodeBean();
                // nuestro tipo de codigo de barra
                barcode.setCodeType(new Interleaved25());
                //barcode.setCodeType(new Code39());
                // nuestro valor a codificar y algunas configuraciones mas
                barcode.setCode("40606868");
                barcode.setCheckDigit(true);

                BufferedImage bufferedImage = barcode.draw(new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB));

                // guardar en disco como png
                File file1 = new File("codebar.png");
                ImageIO.write(bufferedImage, "png", file1);
                ps.println(ImageIO.write(bufferedImage, "png", file1));

                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                pras.add(new Copies(1));
                PrintService ps1 = PrintServiceLookup.lookupDefaultPrintService();
                fileInputStream = new FileInputStream(file1);
                DocPrintJob docPrintJob = ps1.createPrintJob();
                Doc doc = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.GIF, null);
                docPrintJob.print(doc, pras);
                fileInputStream.close();

*/

        // FIN DE CODIGO BARRAS GINO


/*
                ps.println();
                String ultimo = "   ";                
                ps.println(ultimo);
                ps.println(" " + NumLetra.Convertir(optotfinal, true,money));
                ps.println(" ");
                ps.println("     " + XMLGENERADOR);
                ps.println(" ");
                ps.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                ps.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                ps.println("www.nortfarma.com.pe");
                ps.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                ps.println("ESTIMADO CLIENTE:");
                ps.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                ps.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                ps.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                ps.println("NRO. 007-99/SUNAT.");
                ps.println("GRACIAS POR SU COMPRA");
                correr(5, ps); //9
                cortar(ps);*/

  }else{    // ticketera


                setFormato(1, ps);
                ps.println(botica);
                ps.println(Direccion);
                ps.println(DireccionFiscal);
                ps.println("RUC :" + RUC);
                Dibuja_Linea1(ps);
                ps.println("          BOLETA DE VENTA ELECTRONICA ");
                ps.println("            " + serie + " - " + numero);
                Dibuja_Linea1(ps);
                ps.println("Fecha : " + fecha + "  Hora : " + hora);
                ps.println("Caj   : " + cajero + " Ven : " + vendedor + " Int : " + miinterno);
                if (PLACA != null) {
                    ps.println("Placa   : " + PLACA);
                }
                if (!cliente.equals("")){
                Dibuja_Linea1(ps);
                ps.println("Sr(a)    :" + cliente);
                ps.println("DNI      :" + DNI);
                ps.println("DIRECCION:" + direcCliente);
                /*if (Total >= MiMonto) {
                    ps.println("   DNI       :" + DNI);
                }*/
                }
                Dibuja_Linea1(ps);
                ps.println("Cant     " + "Descripcion" + "        " + "Importe");
                Dibuja_Linea1(ps);
                lineas = 7;

                /*
                --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                 */

                int maximo = Obtener_Ancho(Cantidad);
                maximo++;
                Align formato = new Align(15, Align.JUST_RIGHT,null);

                for (int k = 0; k < ProductoDescripcion.size(); k++) {
                //    String cantidad = "";

                    for (Integer inicioP = k; inicioP < ProductoDescripcion.size(); inicioP++) {
                        cantidad = "";
                        int Largo1 = Cantidad.get(inicioP).toString().trim().length();

                        if (Largo1 > maximo) {
                            cantidad = Cantidad.get(inicioP).toString().substring(0, maximo);
                        } else {
                            cantidad = Cantidad.get(inicioP).toString().trim();
                            for (Integer cor = Largo1; cor < maximo; cor++) {
                                cantidad = cantidad + " ";
                            }
                        }

                        Largo = 0;
                        Largo = ProductoDescripcion.get(inicioP).toString().length();

                        if (Largo > 25) {
                            Np = ProductoDescripcion.get(inicioP).toString().substring(0, 26);//25
                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 28; lpp++) {//30
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));

                            Np = Np + espacio1 + bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            String espacio = "";
                            for (int ki = 0; ki < maximo; ki++) {
                                espacio += " ";
                            }
                            Np = Np + "\n" + espacio + ProductoDescripcion.get(inicioP).toString().substring(26, Largo);

                            /*aqui
                            */
                            /*if(Precio.get(k)> 0.0){
                            if (!lab.get(k).equals("DCTO")){
                            Np = Np + "\n" + espacio1 + cantidad + " Unidad x" + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            }
                            }*/

                        } else {
                            Np = ProductoDescripcion.get(inicioP).toString().trim();

                            for (Integer cor = ProductoDescripcion.get(inicioP).toString().length(); cor < 20; cor++) {
                                Np = Np + " ";
                            }

                            String espacio1 = "";
                            for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                espacio1 += " ";
                            }

                            BigDecimal bd1 = new BigDecimal(Precio.get(k));
                            BigDecimal bdPU = new BigDecimal(punit.get(k));

                            Np = Np + espacio1 + formato.format(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                            /*aqui
                            */
                            /*if(Precio.get(k)> 0.0){
                            if (!lab.get(k).equals("DCTO")){
                            Np = Np + "\n" + "   " + cantidad + " Unidad x" + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            }
                            }*/
                        }

                        cantidad = cantidad + Np;
                        //cantidad = Np;
                        break;

                    }
                    ps.println(" " + cantidad);
                    lineas++;
                }
                /*
                Numero_a_Letra NumLetra = new Numero_a_Letra();
                Total = Math.round(Total * Math.pow(10, 2)) / Math.pow(10, 2);

                Align formato1 = new Align(15, Align.JUST_RIGHT,null);

                Dibuja_Linea1(ps);
                ps.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                ps.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                ps.println("IGV           : S./ " + formato1.format(igvVta));
                ps.println("IMPORTE TOTAL : S./ " + formato1.format(sumaTotal));
                ps.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                ps.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                ps.println();
                if ((opdescuento * -1) > 0){
                ps.println("TOTAL DSCTO   : S./ " + opdcto);
                }
                //if (OP_GRATUITA<0)//CODIGO_MAX
                //   ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                ps.println();
                String ultimo = "   ";
                //ultimo += "         ";
                ps.println(ultimo);
                ps.println(" " + NumLetra.Convertir(optotfinal, true,money));
                ps.println(" ");
                ps.println("     " + XMLGENERADOR);
                ps.println(" ");
                ps.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                           + "VENTA ELECTRONICA PUEDE SER");
                ps.println("CONSULTADO EN www.nortfarma.com.pe");
                ps.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                ps.println("ESTIMADO CLIENTE: ");
                ps.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                           + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                ps.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                           + "NRO. 007-99/SUNAT.");
                ps.println("GRACIAS POR SU COMPRA");
                correr(8, ps); //9
                cortar(ps);*/


  }/*Fin modo impresion*/


                ps.close();
                rs.close();
                //rs1.close();
                //rs2.close();
                listaInternos.set(i, null);
                conex.close();
            }//fin del ticket Boleta
            valor = true;

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora(ex.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora(et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception", listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }

        return valor;

    }

    private void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println(" " + "-------------------------------------------------"); //36
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void Dibuja_Linea1(PrintWriter ps) {
        try {
            ps.println(" " + "------------------------------------"); //36
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private int Obtener_Ancho(List<String> Cantidad) {
        int mimayor = Cantidad.get(0).length();

        for (int kp = 0; kp < Cantidad.size(); kp++) {
            int pmayor = Cantidad.get(kp).length();
            if (pmayor > mimayor) {
                mimayor = pmayor;
            }
        }
        return mimayor;
    }

    private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};            
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setRojo(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 1};
            pw.write(ESC_CUT_PAPER);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setNegro(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 0};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void GeneraImpresion(List<Venta> listaInternos) {

        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Double> vtadettotal = new ArrayList<Double>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> tunit = new ArrayList<Double>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        
        Double IGV = 0.00, Totalizado = 0.00, sumaTotal = 0.00, SubSub = 0.00;
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0,Redondeo=0.0,TotFinal=0.0,OP_GRATUITA=0.0,totunit=0.0;
        String cliente = "", Np = "", salesman = "", direccion = "", RUC = "", Direccion = null, PLACA = "", DNICLI="",
                botica = "",DireccionFiscal="",serie = null, numero = "",Delivery="",correo="",money="";
        String vendedor = null, cajero = null;
        Integer Largo = 0;
        int lineas = 0;
        String fecha = null;
        String hora = null;
        Integer Espacios = 7;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        int confirma = 0;
        String StrTipoDoc1 = "" ;
        int dos=1;
        FileOutputStream os = null;
        PrintStream ps = null;
        CallableStatement procedure = null;
        CallableStatement procedure2 = null;
        CallableStatement procedure3 = null;

        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps1;

        try {

            String impresionDouble = listaInternos.get(0).getconfigimpresion().toString().trim();
            String modoImpresionDoc = listaInternos.get(0).getmodoimpresion().toString().trim(); 
            if (impresionDouble.equals("1")){
                dos = 2;
            }else{
                dos = 1;
            }
            //if (conex.isClosed()) {
            conex = new ConexionPool().getConnection();
            //            }

            //for (int i = 0; i < listaInternos.size(); i++) {
            for (int i = 0; i < dos; i++) {
                String miinterno = listaInternos.get(0).getId_Venta().toString().trim();
                if (listaInternos.get(0).getId_Tipo_Venta() == 1) {//PARA BOLETA
                    if (i > 0) {
                        JOptionPane.showMessageDialog(ventana, " " + (i + 1) + " Interno de Venta : " + miinterno + " ", "Impresion de Internos de Ventas", JOptionPane.INFORMATION_MESSAGE);
                        confirma = 1;
                    } else {
                        confirma = 1;
                    }
                } else {                                     //IMPRIMO FACTURA

                    if (0 > 0) {
                        JOptionPane.showMessageDialog(ventana, " " + (i + 1) + " Interno de Venta : " + miinterno + " ", "Impresion de Internos de Ventas", JOptionPane.INFORMATION_MESSAGE);
                        confirma = 1;
                    } else {
                        confirma = 1;
                    }

                    if (confirma == 1) {
                        
                        file = new FileWriter(Impresora_Factura);
                        //file = new FileWriter("//10.10.1.253//BIXOLON//");
                        buffer = new BufferedWriter(file);
                        ps1 = new PrintWriter(buffer);

                        sumaTotal = 0.00;                       

                        procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_FACTURA (?,?) }");
                        procedure.setString(1, miinterno);
                        procedure.setString(2, idbotica);
                        rs = procedure.executeQuery();

                        while (rs.next()) {
                            cliente = "  " + rs.getString(1);
                            Largo = cliente.length();

                            if (Largo < 35) {
                                for (Integer ini = Largo; ini < 35; ini++) {
                                    cliente = cliente + " ";
                                }
                            }

                            botica = rs.getString("mibotica");
                            DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                            vendedor = rs.getString("vendedor");
                            cajero = rs.getString("cajero");
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cliente = cliente + "     ";
                            ProductoDescripcion.add(rs.getString(6));
                            Labs.add(rs.getString(7));
                            CantidadU.add(rs.getInt(8));
                            CantidadF.add(rs.getInt(9));
                            Precio.add(rs.getDouble(12));  //pvf
                            PrecioF.add(rs.getDouble(10));
                            SubSub = rs.getDouble(13);
                            IGV = rs.getDouble(14);
                            Totalizado = rs.getDouble(15);
                            sumaTotal = sumaTotal + rs.getDouble(10);//+ rs.getDouble(11);
                            fecha = rs.getDate(4).toString();
                            salesman = rs.getString(5);
                            direccion = rs.getString(3);
                            RUC = rs.getString(2);
                            Direccion = rs.getString("Direccion");
                            opgravada = rs.getDouble("OP_GRAVADA");
                            opinafecta = rs.getDouble("Op_Inafecta");
                            opigv = rs.getDouble("IGV");
                            opdescuento = rs.getDouble("Op_Descuento");
                            Delivery = rs.getString("Delivery");
                            correo = rs.getString("CorreoEmpresa");
                            vtadettotal.add(rs.getDouble("ventadetalletotal"));
                            money = rs.getString("Id_Moneda");
                            Redondeo = rs.getDouble("Redondeo");
                            TotFinal = rs.getDouble("TotFinal");
                            hora = rs.getString("Hora_Venta");
                            OP_GRATUITA=rs.getDouble("OP_GRATUITA");//CODIGO_MAX
                            tunit.add(rs.getDouble("TOTAL_UNITARIO"));
                            
                        }

                        BigDecimal bdopgravada = new BigDecimal(opgravada);
                        BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                        BigDecimal bdigv = new BigDecimal(opigv);
                        BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                        BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                        BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                        BigDecimal bdopgratuita = new BigDecimal(OP_GRATUITA);//CODIGO_MAX

                        bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opgravada = bdopgravada.doubleValue();
                        String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opinafecta = bdopinafecta.doubleValue();
                        String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opigv = bdigv.doubleValue();
                        String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opdescuento = bdopdescuento.doubleValue();
                        String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        Redondeo = bdopredondeo.doubleValue();
                        String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        TotFinal = bdoptotfinal.doubleValue();
                        String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopgratuita = bdopgratuita.setScale(podecimal, BigDecimal.ROUND_HALF_UP);//CODIGO_MAX
                        OP_GRATUITA = bdopgratuita.doubleValue();//CODIGO_MAX
                        String opgratu = bdopgratuita.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();//CODIGO_MAX

                        //String StrTipoDoc1 = LeerLoadInterno(miinterno);                       
                        procedure3 = conex.prepareCall("{ call XML_LOAD_INTERNO(?) }");
                        procedure3.setString("PId_Venta", miinterno);
                        rs3 = procedure3.executeQuery();

                        while (rs3.next()) {
                            StrTipoDoc1 =  rs3.getString("ID_TIPO_DOCUMENTO_SUNAT")+"-"+rs3.getString("Numero");
                        }


                        String delimiter = "-";
                        String delimiter1 = "-";
                        String[] temp;
                        String[] temp1;
                        temp = fecha.split(delimiter);
                        temp1 = StrTipoDoc1.split(delimiter1);

                        String leerfecha1 = temp[0]+temp[1]+temp[2];
                        String leerStrTipoDoc1 = temp1[0];
                        String leerNumero1 = temp1[1];

                        String[] temp4;
                        temp4 = fecha.split(delimiter);
                        String leerfechavta = temp4[2]+"/"+temp4[1]+"/"+temp4[0];


                        String LeerNuevaRutaXML1 = "/mnt/XML/" + leerfecha1 + "/" + idbotica + "/";
                        //String LeerNuevaRutaXML1 = "D:\\XML\\" + leerfecha1 + "\\" + idbotica + "\\";
                        String LeerMyFile1 = "20399497257" + '-' + leerStrTipoDoc1 + '-' + serie + '-' + leerNumero1;

                        String XMLGENERADO1 ="";
                        try{
                         XMLGENERADO1 = objXML_GENERAR.readXML(miinterno,LeerNuevaRutaXML1,LeerMyFile1);
                        } catch (FileNotFoundException ex) {
                           XMLGENERADO1 ="";
                        }

                        if (conex.isClosed()) {
                            conex = new ConexionPool().getConnection();
                        }
                        procedure2 = conex.prepareCall("{ call GUARDA_FIRMADIGITAL(?,?,?,?,?) }");
                        procedure2.setString("PId_Botica", idbotica);
                        procedure2.setString("PId_Venta", miinterno);
                        procedure2.setString("PId_Serie", serie);
                        procedure2.setString("PId_Numero", numero);
                        procedure2.setString("PFirma", XMLGENERADO1);
                        rs3 = procedure2.executeQuery();

                        while (rs3.next()) {
                        String Firma1 =  rs3.getString("resultado");
                        }
      
 /*Modo impresion*/ //termica
        if (modoImpresionDoc.equals("1")){

                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC : 20399497257");
                        Dibuja_Linea(ps1);
                        ps1.println("           FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("             " + serie + " - " + numero);
                        Dibuja_Linea(ps1);                        
                        ps1.println("Fecha :    " + leerfechavta + "  Hora : " + hora);
                        ps1.println("Caj   :    " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     :" + cliente.trim());
                        ps1.println("DIRECCION :" + direccion);
                        ps1.println("RUC       :" + RUC);
                        Dibuja_Linea(ps1);                       
                        ps1.println("Cant     " + "Descripcion" + "                     " + "Importe");
                        Dibuja_Linea(ps1);
                        lineas = 7;

                        String Fusionado = "";

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */

                    //int maximo = Obtener_Ancho(Cantidad);
                    //maximo++;

                    //for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    //   Fusionado = "";

                        Align formato = new Align(15, Align.JUST_RIGHT,null);

                        for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE FACTURA
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            String sss = ProductoDescripcion.get(sll).toString();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }



                            Largo = 0;
                            Largo = ProductoDescripcion.get(sll).toString().length();

                            if (Largo > 26) {//25
                                Np = ProductoDescripcion.get(sll).toString().substring(0, Largo); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 40; lpp++) {  //30
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));

                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                //Np = Np + "\n" + espacio + ProductoDescripcion.get(sll).toString().substring(24, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            } else {
                                Np = ProductoDescripcion.get(sll).toString().trim();

                                for (Integer cor = ProductoDescripcion.get(sll).toString().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 40; lpp++) {//30
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);

                        }                    

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "    ";                        

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        Dibuja_Linea(ps1);
                        Align formato1 = new Align(28, Align.JUST_RIGHT,null);

                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        //if (OP_GRATUITA<0)//CODIGO_MAX
                        //    ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                        //ps1.println();
                        String ultimo = "      ";
                        //ultimo += "          ";
                        ps1.println(ultimo);
                        //ps1.println(NumLetra.Convertir(numero1, true,money));
                        ps1.println("  "+NumLetra.Convertir(optotfinal, true,money));
                        ps1.println(" ");
                        ps1.println("        " + XMLGENERADO1);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                        ps1.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                        ps1.println("www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                        ps1.println("ESTIMADO CLIENTE:");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                        ps1.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                        ps1.println("NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(6, ps1); //9
                        cortar(ps1);                        
                        
                        ps1.println(botica);                        
                        Dibuja_Linea(ps1);
                        ps1.println("          FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("             " + serie + " - " + numero);
                        Dibuja_Linea(ps1);                        
                        ps1.println("Fecha :    " + leerfechavta + "  Hora : " + hora);
                        ps1.println("Caj   :    " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     : " + cliente);
                        ps1.println("DIRECCION : " + direccion);
                        ps1.println("RUC       : " + RUC);
                        Dibuja_Linea(ps1);
                        
                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        if(opinafecta >0){
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        }
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        if(Redondeo < 0){
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        }
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        ps1.println();
                        correr(5, ps1); //8
                        cortar(ps1);


            }else{ // ticketera


                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC : 20399497257");
                        Dibuja_Linea1(ps1);
                        ps1.println("      FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("       " + serie + " - " + numero);
                        Dibuja_Linea1(ps1);
                        ps1.println("Fecha:" + leerfechavta + " Hora : " + hora);
                        ps1.println("Caj  :" + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        Dibuja_Linea1(ps1);
                        ps1.println("Sr(a)    :" + cliente.trim());
                        ps1.println("DIRECCION:" + direccion);
                        ps1.println("RUC      :" + RUC);
                        Dibuja_Linea1(ps1);
                        ps1.println("Cant     " + "Descripcion" + "        " + "Importe");
                        Dibuja_Linea1(ps1);
                        lineas = 7;

                        String Fusionado = "";

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */

                    //int maximo = Obtener_Ancho(Cantidad);
                    //maximo++;

                    //for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    //   Fusionado = "";

                        Align formato = new Align(15, Align.JUST_RIGHT,null);

                        for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE FACTURA
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            String sss = ProductoDescripcion.get(sll).toString();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = ProductoDescripcion.get(sll).toString().length();

                            if (Largo > 25) {//25
                                Np = ProductoDescripcion.get(sll).toString().substring(0, 26); //Largo
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) {  //40
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));

                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                Np = Np + "\n" + espacio + ProductoDescripcion.get(sll).toString().substring(26, Largo);


                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            } else {
                                Np = ProductoDescripcion.get(sll).toString().trim();

                                for (Integer cor = ProductoDescripcion.get(sll).toString().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) {//40
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);

                        }

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "    ";

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        Dibuja_Linea1(ps1);
                        Align formato1 = new Align(15, Align.JUST_RIGHT,null);

                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        //if (OP_GRATUITA<0)//CODIGO_MAX
                        //    ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                        //ps1.println();
                        String ultimo = "      ";
                        //ultimo += "          ";
                        ps1.println(ultimo);
                        //ps1.println(NumLetra.Convertir(numero1, true,money));
                        ps1.println("  "+NumLetra.Convertir(optotfinal, true,money));
                        ps1.println(" ");
                        ps1.println("       " + XMLGENERADO1);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                        + "VENTA ELECTRONICA PUEDE SER");
                        ps1.println("CONSULTADO EN www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                        ps1.println("ESTIMADO CLIENTE: ");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                                + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                                + "NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(9, ps1); //9
                        cortar(ps1);

                        ps1.println(" "+botica);
                        Dibuja_Linea1(ps1);
                        ps1.println("      FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("        " + serie + " - " + numero);
                        Dibuja_Linea1(ps1);
                        ps1.println("Fecha: " + leerfechavta + "  Hora : " + hora);
                        ps1.println("Caj  : " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        Dibuja_Linea1(ps1);
                        ps1.println("Sr(a)    :" + cliente.trim());
                        ps1.println("DIRECCION:" + direccion);
                        ps1.println("RUC      :" + RUC);
                        Dibuja_Linea1(ps1);

                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        if(opinafecta >0){
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        }
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        if(Redondeo < 0){
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        }
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        ps1.println();
                        correr(8, ps1); //8
                        cortar(ps1);

            }// fin de tipo de impresora


                        rs1.close();
                        rs3.close();
                        
                        ProductoDescripcion.removeAll(ProductoDescripcion);
                        Labs.removeAll(Labs);
                        PrecioF.removeAll(PrecioF);
                        Precio.removeAll(Precio);
                        CantidadU.removeAll(CantidadU);
                        CantidadF.removeAll(CantidadF);

                        for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }

                        ps1.close();
                        
                        //conex.close();
                    }
                }
            }


                        //listaInternos.set(i, null);
                        rs.close();
                        rs3.close();
                        listaInternos.clear();
                        conex.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            Error_Impresora("Error Impresora FileNotFoundException catch" + ex.getMessage(), listaInternos);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            Error_Impresora("Error Impresora IOException catch" + et.getMessage(), listaInternos);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            Error_Impresora("Error Impresora Exception  catch :" + pe.getMessage(), listaInternos);
        }

    }

    private void Error_Impresora(String mens, List<Venta> Internos) {
        if (Internos.size() > 0) {
            FormImpresora obprimter = new FormImpresora(ventana, true, idbotica, Internos, mens);
            obprimter.show(true);
        }
    }

    public void Imprime_Factura(String interno, String idbotica) {

        FileOutputStream os = null;
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        Double IGV = 0.00;
        Double Totalizado = 0.00;
        String cliente = "";
        Integer tmp = 0;
        Integer Largo = 0;
        String Np = "";
        String fecha = null;
        Integer Espacios = 7;
        String salesman = "";
        String direccion = "";
        Double sumaTotal = 0.00;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        String RUC = "";
        Double SubSub = 0.00;
        String Direccion = null;
        LogicaIGV logigv = new LogicaIGV();
        int numeros = 0;

        try {

            interno = interno.substring(0, 6);
            os = new FileOutputStream(obj.getImpresora_Factura());
            //   FileOutputStream os = new FileOutputStream(impresora);
            //INFORMATICA01//EPSONFX
            PrintStream ps = new PrintStream(os);
            ps.flush();
            sumaTotal = 0.00;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_FACTURA (?,?) }");
            procedure.setString(1, interno);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                numeros++;
                cliente = "  " + rs.getString(1);
                Largo = cliente.length();
                if (Largo > 35) {
                    // cliente = cliente.substring(0, 41);
                } else {
                    for (Integer ini = Largo; ini < 35; ini++) {
                        cliente = cliente + " ";
                    }
                }

                cliente = cliente + "     ";
                ProductoDescripcion.add(rs.getString(6));
                Labs.add(rs.getString(7));
                CantidadU.add(rs.getInt(8));
                CantidadF.add(rs.getInt(9));
                Precio.add(rs.getDouble(12));
                PrecioF.add(rs.getDouble(10));
                SubSub = rs.getDouble(13);
                IGV = rs.getDouble(14);
                Totalizado = rs.getDouble(15);
                sumaTotal = sumaTotal + rs.getDouble(11);
                fecha = rs.getDate(4).toString();
                salesman = rs.getString(5);
                direccion = rs.getString(3);
                RUC = rs.getString(2);
                Direccion = rs.getString("Direccion");
            }

            for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                ps.println("");
            }

            if (Direccion != null) {
                ps.print("           " + Direccion);
            }

            for (Integer EspaciosArriba = 0; EspaciosArriba < 2; EspaciosArriba++) {
                ps.println("");
            }

            ps.println("                                        " + salesman + "   " + objFechaHora.RetornaHora() + "");
            //   ps.println("                                        " +  "                      " + "   " + objFechaHora.RetornaHora() + "");

            ps.println("");
            ps.println("         " + cliente + "");
            ps.println("           " + direccion);
            ps.println("           " + RUC + "               " + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "              " + interno);
            ps.println("");
            ps.println("");
            String Fusionado = "";

            for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) {
                Fusionado = "";
                if (CantidadF.get(sll) > 0) {
                    Fusionado = "F" + CantidadF.get(sll).toString();
                }
                if (CantidadU.get(sll) > 0) {
                    Fusionado = CantidadU.get(sll).toString() + Fusionado;
                }
                if (Fusionado.length() < 5) {
                    for (Integer xx = Fusionado.length(); xx < 3; xx++) {
                        Fusionado = " " + Fusionado;
                    }
                }
                String sss = ProductoDescripcion.get(sll).toString();
                if (sss.length() < 40) {
                    for (Integer xx = sss.length(); xx < 40; xx++) {
                        sss = sss + " ";
                    }
                }
                String elPrecio = MiFormato.format(Precio.get(sll));
                if (elPrecio.length() < 43) {
                    for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                        elPrecio = " " + elPrecio;
                    }
                }
                String elPrecioF = MiFormato.format(PrecioF.get(sll));
                if (elPrecioF.length() < 22) {
                    for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                        elPrecioF = " " + elPrecioF;
                    }
                }
                ps.println(Fusionado + "                   " + sss + "    " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
            }

            for (Integer esp = Labs.size(); esp < 15; esp++) {
                ps.println("");
            }

            Numero_a_Letra NumLetra = new Numero_a_Letra();
            sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);
            String numero = SubSub.toString();
            String Subtotal = "";
            Subtotal = MiFormato.format(SubSub).toString();
            for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                Subtotal = " " + Subtotal;
            }

            String espacioFooterIGV = "";
            espacioFooterIGV = "                                  ";

            ps.println();
            ps.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));
            String elIGV = "";
            elIGV = MiFormato.format(IGV).toString().replace(",", ".");
            BigDecimal bd2 = new BigDecimal(logigv.RecuperaIGV());
            elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

            BigDecimal bd = new BigDecimal(Totalizado);
            String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

            String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            elTotalizado = "S/. " + elTotalizado;

            for (Integer paraIGV = elTotalizado.length(); paraIGV < 92; paraIGV++) {
                elIGV = " " + elIGV;
            }

            for (Integer lo = elTotalizado.length(); lo < 94; lo++) {
                elTotalizado = " " + elTotalizado;
            }


            ps.println(espacioFooterIGV + "    " + elIGV);
            ps.println(espacioFooterIGV + "    " + elTotalizado);
            ps.println(NumLetra.Convertir(numero1, true,"1"));
            ProductoDescripcion.removeAll(ProductoDescripcion);
            Labs.removeAll(Labs);
            PrecioF.removeAll(PrecioF);
            Precio.removeAll(Precio);
            CantidadU.removeAll(CantidadU);
            CantidadF.removeAll(CantidadF);

            for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                ps.println("");
            }

            ps.close();


        } catch (SQLException ex) {
            Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (Exception ex) {
                    System.out.println("open :" + ex.getMessage());
                }
            }
        }
    }
    public void ReImprime_Factura(List<Venta> listaInternos,String idbotica) {

        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Double> vtadettotal = new ArrayList<Double>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> tunit = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        Double IGV = 0.00, Totalizado = 0.00, sumaTotal = 0.00, SubSub = 0.00;
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0,Redondeo=0.0,TotFinal=0.0,OP_GRATUITA=0.0;
        String cliente = "", Np = "", salesman = "", direccion = "", RUC = "", Direccion = null, PLACA = "", DNICLI="",
                botica = "",DireccionFiscal="",serie = null, numero = "",Delivery="",correo="",money="",timpre="";
        String vendedor = null, cajero = null;
        Integer Largo = 0;
        int lineas = 0;
        String fecha = null;
        String hora = null;
        Integer Espacios = 7;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        int confirma = 0;
        FileOutputStream os = null;
        PrintStream ps = null;
        CallableStatement procedure = null;

        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps1;

        try {

            for (int i = 0; i < listaInternos.size(); i++) {
                String miinterno = listaInternos.get(i).getId_Venta().toString().trim();
                if (listaInternos.get(i).getId_Tipo_Venta() == 1) {//IMPRIMO BOLETA
                    if (i > 0) {
                        JOptionPane.showMessageDialog(ventana, " " + (i + 1) + " Interno de Venta : " + miinterno + " ", "Impresion de Internos de Ventas", JOptionPane.INFORMATION_MESSAGE);
                        confirma = 1;
                    } else {
                        confirma = 1;
                    }                   

                } else {                                     //IMPRIMO FACTURA

                    if (i > 0) {
                        JOptionPane.showMessageDialog(ventana, " " + (i + 1) + " Interno de Venta : " + miinterno + " ", "Impresion de Internos de Ventas", JOptionPane.INFORMATION_MESSAGE);
                        confirma = 1;
                    } else {
                        confirma = 1;
                    }

                    if (confirma == 1) {                        

                        file = new FileWriter(Impresora_Factura);
                        //file = new FileWriter("//10.10.1.253//BIXOLON//");
                        buffer = new BufferedWriter(file);
                        ps1 = new PrintWriter(buffer);

                        sumaTotal = 0.00;

                        if (conex.isClosed()) {
                            conex = new ConexionPool().getConnection();
                        }

                        procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_FACTURA (?,?) }");
                        procedure.setString(1, miinterno);
                        procedure.setString(2, idbotica);
                        rs = procedure.executeQuery();

                        while (rs.next()) {
                            cliente = "  " + rs.getString(1);
                            Largo = cliente.length();

                            if (Largo < 35) {
                                for (Integer ini = Largo; ini < 35; ini++) {
                                    cliente = cliente + " ";
                                }
                            }

                            botica = rs.getString("mibotica");
                            DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                            vendedor = rs.getString("vendedor");
                            cajero = rs.getString("cajero");
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cliente = cliente + "     ";
                            ProductoDescripcion.add(rs.getString(6));
                            Labs.add(rs.getString(7));
                            CantidadU.add(rs.getInt(8));
                            CantidadF.add(rs.getInt(9));
                            Precio.add(rs.getDouble(12));  //pvf
                            PrecioF.add(rs.getDouble(10));
                            SubSub = rs.getDouble(13);
                            IGV = rs.getDouble(14);
                            Totalizado = rs.getDouble(15);
                            sumaTotal = sumaTotal + rs.getDouble(10);//+ rs.getDouble(11);
                            fecha = rs.getDate(4).toString();
                            salesman = rs.getString(5);
                            direccion = rs.getString(3);
                            RUC = rs.getString(2);
                            Direccion = rs.getString("Direccion");
                            opgravada = rs.getDouble("OP_GRAVADA");
                            opinafecta = rs.getDouble("Op_Inafecta");
                            opigv = rs.getDouble("IGV");
                            opdescuento = rs.getDouble("Op_Descuento");
                            Delivery = rs.getString("Delivery");
                            correo = rs.getString("CorreoEmpresa");
                            vtadettotal.add(rs.getDouble("ventadetalletotal"));
                            money = rs.getString("Id_Moneda");
                            Redondeo = rs.getDouble("Redondeo");
                            TotFinal = rs.getDouble("TotFinal");
                            hora = rs.getString("Hora_Venta");
                            timpre = rs.getString("MODOIMPRESION");
                            OP_GRATUITA=rs.getDouble("OP_GRATUITA");//CODIGO_MAX
                            tunit.add(rs.getDouble("TOTAL_UNITARIO"));
                        }

                        String modoImpresionDoc = timpre;

                        BigDecimal bdopgravada = new BigDecimal(opgravada);
                        BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                        BigDecimal bdigv = new BigDecimal(opigv);
                        BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                        BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                        BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                        BigDecimal bdopgratuita = new BigDecimal(OP_GRATUITA);//CODIGO_MAX

                        bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opgravada = bdopgravada.doubleValue();
                        String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opinafecta = bdopinafecta.doubleValue();
                        String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opigv = bdigv.doubleValue();
                        String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opdescuento = bdopdescuento.doubleValue();
                        String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        Redondeo = bdopredondeo.doubleValue();
                        String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        TotFinal = bdoptotfinal.doubleValue();
                        String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopgratuita = bdopgratuita.setScale(podecimal, BigDecimal.ROUND_HALF_UP);//CODIGO_MAX
                        OP_GRATUITA = bdopgratuita.doubleValue();//CODIGO_MAX
                        String opgratu = bdopgratuita.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();//CODIGO_MAX

                        String StrTipoDoc1 = LeerLoadInterno(miinterno);
                        String delimiter = "-";
                        String delimiter1 = "-";
                        String[] temp;
                        String[] temp1;
                        temp = fecha.split(delimiter);
                        temp1 = StrTipoDoc1.split(delimiter1);

                        String leerfecha1 = temp[0]+temp[1]+temp[2];
                        String leerStrTipoDoc1 = temp1[0];
                        String leerNumero1 = temp1[1];

                        String[] temp4;
                        temp4 = fecha.split(delimiter);
                        String leerfechavta = temp4[2]+"/"+temp4[1]+"/"+temp4[0];


                        String LeerNuevaRutaXML1 = "/mnt/XML/" + leerfecha1 + "/" + idbotica + "/";
                        //String LeerNuevaRutaXML1 = "D:\\XML\\" + leerfecha1 + "\\" + idbotica + "\\";
                        String LeerMyFile1 = "20399497257" + '-' + leerStrTipoDoc1 + '-' + serie + '-' + leerNumero1;

                        objXML_GENERAR = new XML_GENERAR();
                        String XMLGENERADO1 = objXML_GENERAR.readXML(miinterno,LeerNuevaRutaXML1,LeerMyFile1);
    
/*Modo impresion*/ //termica
if (modoImpresionDoc.equals("1")){

                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC : 20399497257");
                        Dibuja_Linea(ps1);
                        ps1.println("         FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("            " + serie + " - " + numero);
                        Dibuja_Linea(ps1);
                        //ps.println("S/N       :" + maq);
                        //ps1.println(" Fecha :" + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "  Hora : " + objFechaHora.RetornaHora());
                        ps1.println("Fecha :    " + leerfechavta + "  Hora : " + hora);
                        ps1.println("Caj   :    " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        /*if (PLACA != null) {
                            ps1.println("Placa   : " + PLACA);
                        }*/
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     :" + cliente.trim());
                        ps1.println("DIRECCION :" + direccion.trim());
                        ps1.println("RUC       :" + RUC);
                        Dibuja_Linea(ps1);                        
                        ps1.println("Cant     " + "Descripcion" + "                      " + "Importe");
                        Dibuja_Linea(ps1);
                        lineas = 7;

                        String Fusionado = "";

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */
                        Align formato = new Align(15, Align.JUST_RIGHT,null);
                    
                        for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE FACTURA
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            String sss = ProductoDescripcion.get(sll).toString();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = ProductoDescripcion.get(sll).toString().length();

                            if (Largo > 26) { //25
                                Np = ProductoDescripcion.get(sll).toString().substring(0, Largo); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 40; lpp++) { //29
                                    espacio1 += " ";
                                }
                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                //Np = Np + "\n" + espacio + ProductoDescripcion.get(sll).toString().substring(24, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            } else {
                                Np = ProductoDescripcion.get(sll).toString().trim();

                                for (Integer cor = ProductoDescripcion.get(sll).toString().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 40; lpp++) {//30
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/
                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);


                        }
                    //}
                        /*for (Integer esp = Labs.size(); esp < 15; esp++) {
                            ps1.println("");
                        }*/

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";

                        //ps1.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;
                        
                        Dibuja_Linea(ps1);
                        Align formato1 = new Align(28, Align.JUST_RIGHT,null);

                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        //if (OP_GRATUITA<0)//CODIGO_MAX
                        //    ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                        ps1.println();
                        String ultimo = "              ";
                        //ultimo += "                   ";
                        ps1.println(ultimo);
                        //ps1.println(NumLetra.Convertir(numero1, true,money));
                        ps1.println(" "+NumLetra.Convertir(optotfinal, true,money));
                        ps1.println(" ");
                        ps1.println("      " + XMLGENERADO1);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                        ps1.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                        ps1.println("www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                        ps1.println("ESTIMADO CLIENTE:");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                        ps1.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                        ps1.println("NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(6, ps1); //9

  }else{ // ticketera

                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC : 20399497257");
                        Dibuja_Linea1(ps1);
                        ps1.println("          FACTURA DE VENTA ELECTRONICA ");
                        ps1.println("             " + serie + " - " + numero);
                        Dibuja_Linea1(ps1);
                        //ps.println("S/N       :" + maq);
                        //ps1.println(" Fecha :" + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "  Hora : " + objFechaHora.RetornaHora());
                        ps1.println("Fecha : " + leerfechavta + "  Hora : " + hora);
                        ps1.println("Caj   : " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        /*if (PLACA != null) {
                            ps1.println("Placa   : " + PLACA);
                        }*/
                        Dibuja_Linea1(ps1);
                        ps1.println("Sr(a)     :" + cliente.trim());
                        ps1.println("DIRECCIObN:" + direccion.trim());
                        ps1.println("RUC       :" + RUC);
                        Dibuja_Linea1(ps1);
                        ps1.println("Cant     " + "Descripcion" + "        " + "Importe");
                        Dibuja_Linea1(ps1);
                        lineas = 7;

                        String Fusionado = "";

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */
                        Align formato = new Align(15, Align.JUST_RIGHT,null);

                        for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE FACTURA
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            String sss = ProductoDescripcion.get(sll).toString();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = ProductoDescripcion.get(sll).toString().length();

                            if (Largo > 25) { //25
                                Np = ProductoDescripcion.get(sll).toString().substring(0, 26); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                    espacio1 += " ";
                                }
                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                Np = Np + "\n" + espacio + ProductoDescripcion.get(sll).toString().substring(26, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/
                                
                            } else {
                                Np = ProductoDescripcion.get(sll).toString().trim();

                                for (Integer cor = ProductoDescripcion.get(sll).toString().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) {//30
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);


                        }
                    //}
                        /*for (Integer esp = Labs.size(); esp < 15; esp++) {
                            ps1.println("");
                        }*/

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";

                        //ps1.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;

                        Dibuja_Linea1(ps1);
                        Align formato1 = new Align(15, Align.JUST_RIGHT,null);

                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("IMPORTE TOTAL : S./ " + formato1.format(elTotalizado));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        //if (OP_GRATUITA<0)//CODIGO_MAX
                        //    ps.println("TOTAL TR GR   : S./ " +opgratu );//CODIGO_MAX
                        ps1.println();
                        String ultimo = "              ";
                        //ultimo += "                   ";
                        ps1.println(ultimo);
                        //ps1.println(NumLetra.Convertir(numero1, true,money));
                        ps1.println(" "+NumLetra.Convertir(optotfinal, true,money));
                        ps1.println(" ");
                        ps1.println("      " + XMLGENERADO1);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                                   + "VENTA ELECTRONICA PUEDE SER");
                        ps1.println("CONSULTADO EN www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                        ps1.println("ESTIMADO CLIENTE: ");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                                   + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                                   + "NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(9, ps1); //9


  }     // fin de tipo de impresora

                        cortar(ps1);
                        //ps1.close();
                        //rs.close();
                        rs.close();
                        //rs2.close();
                        //listaInternos.set(i, null);

                        ProductoDescripcion.removeAll(ProductoDescripcion);
                        Labs.removeAll(Labs);
                        PrecioF.removeAll(PrecioF);
                        Precio.removeAll(Precio);
                        CantidadU.removeAll(CantidadU);
                        CantidadF.removeAll(CantidadF);

                        for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }

                        ps1.close();
                        listaInternos.set(i, null);

                        conex.close();
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora("Error Impresora FileNotFoundException catch" + ex.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora("Error Impresora IOException catch" + et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception  catch :" + pe.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(obj,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    public void Imprime_Boleta(String interno, String idbotica) {

        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        objFechaHora = new LogicaFechaHora();
        String cliente = "";
        Integer Largo = 0;
        String Np = "";
        String fecha = null;
        Integer Espacios = 7;
        String salesman = "";
        Double sumaTotal = 0.00;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        String Direccion = null;

        try {

            interno = interno.substring(0, 6);
            FileOutputStream os1 = new FileOutputStream(obj.getImpresora_Boleta());
            PrintStream ps1 = new PrintStream(os1);
            sumaTotal = 0.00;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_BOLETA (?,?) }");
            procedure.setString(1, interno);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();


            while (rs.next()) {

                Direccion = rs.getString(9);
                cliente = rs.getString(1);
                Largo = cliente.length();
                if (Largo > 35) {
                    // cliente = cliente.substring(0, 41);
                } else {
                    for (Integer ini = Largo; ini < 33; ini++) {
                        cliente = cliente + " ";
                    }
                }
                cliente = cliente + "    ";
                ProductoDescripcion.add(rs.getString(4));
                Labs.add(rs.getString(5));
                CantidadU.add(Integer.valueOf(rs.getString(6)));
                CantidadF.add(Integer.valueOf(rs.getString(7)));
                Precio.add(rs.getDouble(8));
                sumaTotal = sumaTotal + rs.getDouble(8);
                fecha = rs.getDate(2).toString();
                salesman = rs.getString(3);
            }

            BigDecimal bd = new BigDecimal(sumaTotal);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            sumaTotal = bd.doubleValue();
            String sumaTotal_1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

            ps1.println("");
            ps1.println("");
            String nInterno = "";
            nInterno = interno;

            try {
                if (Direccion != null) {
                    ps1.print("    " + Direccion);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            ps1.println("Ven: " + salesman + "                                " + objFechaHora.RetornaHora());
            //    ps1.println("     " + "                       " + "                                " + objFechaHora.RetornaHora());

            ps1.println(cliente + " " + objFechaHora.MysqlToJuliano(fecha) + "    " + nInterno);
            ps1.println("");


            for (Integer inicioP = 0; inicioP < ProductoDescripcion.size(); inicioP++) {
                String formato = "";
                Largo = 0;
                Largo = ProductoDescripcion.get(inicioP).toString().length();
                if (Largo > 33) {
                    Np = ProductoDescripcion.get(inicioP).toString().substring(0, 32);
                } else {
                    Np = ProductoDescripcion.get(inicioP).toString();
                    for (Integer cor = ProductoDescripcion.get(inicioP).toString().length(); cor < 32; cor++) {
                        Np = Np + " ";
                    }
                }
                if (CantidadF.get(inicioP) > 0) {
                    formato = "F" + CantidadF.get(inicioP).toString();
                }
                if (CantidadU.get(inicioP) > 0) {
                    formato = CantidadU.get(inicioP).toString() + formato;
                }
                String DecPrecio = "";
                DecPrecio = Precio.get(inicioP).toString();
                if (DecPrecio.length() < 6) {
                    for (Integer j = DecPrecio.length(); j < 6; j++) {
                        DecPrecio = " " + DecPrecio;
                    }
                }
                if (formato.length() < 9) {
                    for (Integer j = formato.length(); j < 9; j++) {
                        formato = " " + formato;
                    }
                }
                String NumeroPrecio = MiFormato.format(Precio.get(inicioP)).toString().replace(",", ".");
                if (NumeroPrecio.length() < 14) {
                    for (Integer XD = NumeroPrecio.length(); XD < 14; XD++) {
                        NumeroPrecio = " " + NumeroPrecio;
                    }
                }
                NumeroPrecio = " " + NumeroPrecio;
                ps1.println(Np + Labs.get(inicioP) + formato + NumeroPrecio);
            }

            for (Integer Es = ProductoDescripcion.size(); Es < Espacios - 1; Es++) {
                ps1.println("");
            }

            Numero_a_Letra NumLetra = new Numero_a_Letra();
            sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);
            String numero = sumaTotal.toString();
            ps1.println("                                                     " + MiFormato.format(sumaTotal).toString().replace(",", "."));
            ps1.println(NumLetra.Convertir(sumaTotal_1, true,"1"));
            ProductoDescripcion.removeAll(ProductoDescripcion);
            Labs.removeAll(Labs);
            CantidadU.removeAll(CantidadU);
            CantidadF.removeAll(CantidadF);
            Precio.removeAll(Precio);
            ps1.close();

        } catch (SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (Exception ex) {
                    System.out.println("open :" + ex.getMessage());
                }
            }
        }
    }

    public int ObtieneTipoVenta(String Interno) {
        Integer valor = 1;

        try {
            CallableStatement procedure2 = conex.prepareCall("{ call RECUPERA_TIPO_VENTA (?) }");
            procedure2.setString(1, Interno);
            rs2 = procedure2.executeQuery();
            rs2.next();
            valor = rs2.getInt(1);
            rs2.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return valor;
    }

    public int ObtieneValidaHay(String Interno) {
        Integer valor5 = 1;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure5 = conex.prepareCall("{ call SP_VALIDA_INTERNO (?) }");
            procedure5.setString(1, Interno);
            rs2 = procedure5.executeQuery();
            rs2.next();
            valor5 = rs2.getInt(1);
            rs2.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return valor5;
    }

    public int Devuelve_Tpo_Venta(String Interno) {
        Integer valor = 1;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure2 = conex.prepareCall("{ call RECUPERA_TIPO_VENTA (?) }");
            procedure2.setString(1, Interno);
            rs2 = procedure2.executeQuery();
            rs2.next();
            valor = rs2.getInt(1);
            rs2.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return valor;

    }

    public List<Venta> ListaMovimiento_Detalle(Venta obj, String docu) {

        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_THE_MOVIMIENTOS (?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setString("DOCU", docu);
            procedure.setString("PROVEEDOR", obj.getProveedor());
            procedure.setString("ALMA", obj.getAlmacen());
            procedure.setString("IDTIPOMOV", obj.getIdMovimiento());
            procedure.setString("MOVI", obj.getMovi());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Producto"),
                        rs.getString("Descripcion"), rs.getInt("unidad"),
                        rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta"), rs.getDouble("Descuento")));

            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaventas;


    }

    public class Numero_a_Letra {

        private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco", "seis", "siete", "ocho", "nueve"};
        private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte y ", "treinta y ", "cuarenta y ",
            "cincuenta y ", "sesenta y ", "setenta y ", "ochenta y ", "noventa y "};
        private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

        public Numero_a_Letra() {
        }

        public String Convertir(String numero, boolean mayusculas, String money) {

            // System.out.println(">Dentro de la clase<:"+numero);

            String literal = "";
            String parte_decimal;
            String desmoney = "";
            //si el numero utiliza (.) en lugar de (,) -> se reemplaza
            //XXX numero = numero.replace(",", ".");
            //XXX System.out.println(">Dentro de la 2<:"+numero);
            numero = numero.replace(".", ",");
            // System.out.println(">Dentro de la 2.1<:"+numero);
            //si el numero no tiene parte decimal, se le agrega ,00
            if (numero.indexOf(",") == -1) {
                numero = numero + ",00";
            }
            //se valida formato de entrada -> 0,00 y 999 999 999,00
            if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
                //se divide el numero 0000000,00 -> entero y decimal
                String Num[] = numero.split(",");
                //de da formato al numero decimal
                if (money.equals("1")){
                    desmoney = "Soles.";
                }else{
                    desmoney = "Dolares.";
                }

                parte_decimal = " con " + Num[1] + "/100 " + desmoney;
                //se convierte el numero a literal
                if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                    literal = "Cero ";
                } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                    literal = getMillones(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                    literal = getMiles(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 99) {//si es centena

                    if (Integer.parseInt(Num[1]) < 1 || Num[0].substring(Num[0].length()-2,2).trim().equals("y")) {
                        literal =  getCentenas(Num[0]).substring(0,getCentenas(Num[0]).length()-2 );
                    }else{
                    literal = getCentenas(Num[0]);
                    }
                } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                    literal = getDecenas(Num[0]);
                    
                } else {//sino unidades -> 9
                    literal = getUnidades(Num[0]);
                }
                //devuelve el resultado en mayusculas o minusculas
                if (mayusculas) {
                    return (literal + parte_decimal).toUpperCase();
                } else {
                    return (literal + parte_decimal);
                }
            } else {//error, no se puede convertir
                return literal = null;
            }
        }

        /* funciones para convertir los numeros a literales */
        private String getUnidades(String numero) {// 1 - 9
            //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
            String num = numero.substring(numero.length() - 1);
            return UNIDADES[Integer.parseInt(num)];
        }

        private String getDecenas(String num) {// 99
            int n = Integer.parseInt(num);
            if (n < 10) {//para casos como -> 01 - 09
                return getUnidades(num);
            } else if (n > 19) {//para 20...99
                String u = getUnidades(num);
                if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
                } else {
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + " " + u;
                }
            } else {//numeros entre 11 y 19
                return DECENAS[n - 10];
            }
        }

        private String getCentenas(String num) {// 999 o 099
            if (Integer.parseInt(num) > 99) {//es centena
                if (Integer.parseInt(num) == 100) {//caso especial
                    return " cien ";
                } else {
                    return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
                }
            } else {//por Ej. 099
                //se quita el 0 antes de convertir a decenas
                return getDecenas(Integer.parseInt(num) + "");
            }
        }

        private String getMiles(String numero) {// 999 999
            //obtiene las centenas
            String c = numero.substring(numero.length() - 3);
            //obtiene los miles
            String m = numero.substring(0, numero.length() - 3);
            String n = "";
            //se comprueba que miles tenga valor entero
            if (Integer.parseInt(m) > 0) {
                n = getCentenas(m);
                return n + "mil " + getCentenas(c);
            } else {
                return "" + getCentenas(c);
            }
        }

        private String getMillones(String numero) { //000 000 000
            //se obtiene los miles
            String miles = numero.substring(numero.length() - 6);
            //se obtiene los millones
            String millon = numero.substring(0, numero.length() - 6);
            String n = "";
            if (millon.length() > 1) {
                n = getCentenas(millon) + "millones ";
            } else {
                n = getUnidades(millon) + "millon ";
            }
            return n + getMiles(miles);
        }
    }

    public List<Venta> Lista_Tipos_Pagos_Venta(Venta objventa) {
        List<Venta> array = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PAGOS_DEVENTA ('" + objventa.getId_Botica() + "','" + objventa.getId_Venta() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                array.add(new Venta(rs.getString("DescripcionTipoPago"), rs.getDouble("Monto")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return array;

    }

    public boolean EsCredito(int tipventa) {
        boolean resul = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ES_CREDITO (?) }");
            procedure.setInt("MITIPOPAGO", tipventa);
            rs = procedure.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                resul = true;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;

    }

    public List<Venta> REPORTE_CAJA_SUBDETALLE(Venta objventa) {
        List<Venta> LISTA = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REPORTE_CAJA_SUBDETALLE (?,?,?,?,?,?) }");
            procedure.setString("FECHAINICIO", objventa.getFecha1());
            procedure.setString("FECHAFIN", objventa.getFecha2());
            procedure.setString("INIDBOTICA", objventa.getId_Botica());
            procedure.setInt("INIDCAJA", objventa.getId_Caja());
            procedure.setInt("INIDCAJERO", objventa.getId_Personal_Botica_Caja());
            procedure.setInt("TURNO", objventa.getTurno());
            rs = procedure.executeQuery();

            while (rs.next()) {
                LISTA.add(new Venta(rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getInt(6)));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("Error REPORTE_CAJA_SUBDETALLE" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return LISTA;
    }

    public List<Venta> REPORTE_CAJA_TIPOS_PAGOS(Venta objventa) {
        List<Venta> LISTA_Tipos_Pagos = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REPORTE_CAJA_TIPOS_PAGOS (?,?,?,?,?,?) }");
            procedure.setString("FECHAINICIO", objventa.getFecha1());
            procedure.setString("FECHAFIN", objventa.getFecha2());
            procedure.setString("INIDBOTICA", objventa.getId_Botica());
            procedure.setInt("INIDCAJA", objventa.getId_Caja());
            procedure.setInt("INIDCAJERO", objventa.getId_Personal_Botica_Caja());
            procedure.setInt("TURNO", objventa.getTurno());
            rs = procedure.executeQuery();

            while (rs.next()) {
                LISTA_Tipos_Pagos.add(new Venta(rs.getString(1), rs.getInt(2), rs.getDouble(3)));
            }


            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return LISTA_Tipos_Pagos;
    }

    public int EliminaCorrelativos(Venta obj) {
        int valor = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ELIMINA_CORRELATIVOS_NUMEROS (?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setString("NUMERO", obj.getNumero());
            procedure.setString("NUMERO1", obj.getCampo01());
            procedure.setInt("IDCAJA", obj.getId_Caja());
            procedure.setInt("TIPOVENTA", obj.getId_Tipo_Venta());
            procedure.setInt("IDPERSONAL", obj.getId_Personal_Botica_Caja());
            procedure.setString("MISERIE", obj.getSerie());
            rs = procedure.executeQuery();
            conex.commit();
            rs.next();
            valor = rs.getInt(1);

        } catch (Exception ex) {
            System.out.println("EliminaCorrelativos : " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return valor;
    }

    public boolean Corrige_Numeracion(Venta obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CORRIGE_NUMERACION (?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setString("NUMERO_FINAL", obj.getNumero());
            procedure.setInt("IDCAJA", obj.getId_Caja());
            procedure.setInt("TIPOVENTA", obj.getId_Tipo_Venta());
            procedure.setString("MISERIE", obj.getSerie());
            procedure.executeQuery();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            valor = false;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return valor;

    }

    public List<String> Lista_Bancos() {
        List<String> Lista_Bancos = new ArrayList<String>();
        Lista_Bancos.removeAll(Lista_Bancos);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_BANCOS () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Bancos.add(rs.getString("Descripcion"));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Bancos;
    }

    public boolean Verifica_Venta_Botiquin(Venta obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VALIDA_VENTA_BOTIQUIN (?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setInt("IDPERSONAL", obj.getId_Personal_Botica_Venta());
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                valor = true;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return valor;
    }
    public boolean Verifica_Venta_EsSAP(Venta obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VALIDA_VENTA_ESSAP (?,?) }");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setInt("IDPERSONAL", obj.getId_Personal_Botica_Venta());
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                valor = true;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return valor;
    }

    public List<Venta> Lista_Ventas_Facturas(Venta p) {
        List<Venta> Lista_ventas = new ArrayList<Venta>();
        Lista_ventas.removeAll(Lista_ventas);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_VENTAS_FACTURAS_MES (?,?,?) }");
            procedure.setString("IDBOTICA", p.getId_Botica());
            procedure.setString("IDVENTA", p.getId_Venta());
            procedure.setInt("MES", p.getMes());
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_ventas.add(new Venta(rs.getString("Id_Venta"), rs.getString("Serie"), rs.getString("Numero"),
                        rs.getDouble("Total"), rs.getDouble("SubTotal"), rs.getDouble("IGV"), rs.getString("TipoPago"),
                        rs.getDate("Fecha_Registro"), rs.getString("VENDEDOR"), rs.getString("CAJERO"), rs.getString("Nombre_RazonSocial"), rs.getString("RUC_DNI"), rs.getString("Direccion")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Ventas_Facturas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_ventas;
    }

    public List<TipoCambio> Retorna_Tipos_Cambios() {
        List<TipoCambio> entidad = new ArrayList<TipoCambio>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_TIPO_CAMBIO () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                entidad.add(new TipoCambio(rs.getInt("Id_Cambio"), rs.getString("Moneda"), rs.getDouble("TipoCambio")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Ventas_Facturas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return entidad;
    }

    public List<TipoCambio> Retorna_Moneda() {
        List<TipoCambio> entidadM = new ArrayList<TipoCambio>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_MONEDA () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                entidadM.add(new TipoCambio(rs.getInt("Id_Moneda"), rs.getString("Moneda")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Ventas_Facturas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return entidadM;
    }

    public boolean Ingresa_Tipo_Cambio(double tipcambio, String idbotica) {
        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call REGISTRA_TIPO_CAMBIO (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setDouble("TIPOCAMBIO", tipcambio);
            rs = procedure.executeQuery();
            conex.commit();
            resultado = true;

            rs.close();

        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return resultado;
    }

    public boolean Verifica_Ingreso_TipoCambio() {
        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_INGRESO_TIPO_CAMBIO () }");
            rs = procedure.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                resultado = true;
            }
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;
    }

    public List<Venta> Muestra_Ventas_Anuladas_RESP(String idbotica, String fecha) {
        List<Venta> milista = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REVISA_VENTAS_ANULADAS (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("MIFECHA", fecha);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Venta(rs.getString("Id_Venta"), rs.getString("DESCRIPCION"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getDouble("Total"), rs.getString("RESPONSABLE"), rs.getString("VENDEDOR")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Muestra_Ventas_Anuladas_RESP" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;
    }

    public boolean Revisa_Ventas_Anuladas(List<Venta> listaobj) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;

            for (int i = 0; i < listaobj.size(); i++) {
                //System.out.println("{ call APRUEBA_VENTAS_ANULADAS ('" + listaobj.get(i).getId_Botica() + "','" + listaobj.get(i).getId_Venta() + "','" + listaobj.get(i).getDESCRIPCION() + "','" + listaobj.get(i).getCantidadpagos() + "') }");
                procedure = conex.prepareCall("{ call APRUEBA_VENTAS_ANULADAS (?,?,?,?) }");
                procedure.setString("IDBOTICA", listaobj.get(i).getId_Botica());
                procedure.setString("IDINTERNO", listaobj.get(i).getId_Venta());
                procedure.setString("MIMOTIVO", listaobj.get(i).getDESCRIPCION());
                procedure.setInt("APROBADO", listaobj.get(i).getCantidadpagos());
                procedure.executeQuery();

            }


            conex.commit();
            resultado = true;
            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;
    }

    public String Recupera_Interno(String idbotica) {
        String interno = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_INTERNO (?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                interno = rs.getString("interno");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return interno;
    }

    public String Recupera_InternoOK(String IdBotica,String idVenta, String FechaVta) {
        String VOK = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_CAMPOINTERNOOK (?,?,?) }");
            procedure.setString("VIDBOTICA", IdBotica);
            procedure.setString("VIDVENTA", idVenta);
            procedure.setString("VFECHAVENTA", FechaVta);
            rs = procedure.executeQuery();

            while (rs.next()) {
                VOK = rs.getString("OK");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return VOK;
    }

    public String Recupera_Obs(String IdBotica,String serie, String numero) {
        String OBS = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_OBSERVACION (?,?,?) }");
            procedure.setString("VIDBOTICA", IdBotica);
            procedure.setString("VSERIE", serie);
            procedure.setString("VNUMERO", numero);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OBS = rs.getString("Descripcion");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return OBS;
    }

    

    public String Recupera_Caja(String IdBotica,String idVenta, String FechaVta,String id_peronal) {
        String IdCaja = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_CAMPOINTERNOOK (?,?,?,?) }");
            procedure.setString("VIDBOTICA", IdBotica);
            procedure.setString("VIDVENTA", idVenta);
            procedure.setString("VFECHAVENTA", FechaVta);
            procedure.setString("VIDPERSONAL", id_peronal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                IdCaja = rs.getString("ID_CAJA");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error RECUPERA_CAMPOINTERNOOK" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return IdCaja;
    }

    public String actualizaguiaajustedet(List<Movimiento_Detalle> listaproductos,String Tserie,String Tnumero,String opc) {

        String resultado = "0";
        ResultSet rs1 = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;            
            
                for (int i = 0; i < listaproductos.size(); i++) {
                    procedure = conex.prepareCall("{ call MODIFICA_DETALLEGUIAAJUSTE (?,?,?,?,?,?,?,?,?,?,?) }");
                    procedure.setString("IDBOTICA", listaproductos.get(i).getbotica());
                    procedure.setString("IDPRODUCTO", listaproductos.get(i).getidproducto());
                    procedure.setInt("UNID", listaproductos.get(i).getUnidad());
                    procedure.setInt("FRAC", listaproductos.get(i).getFraccion());
                    procedure.setString("TSERIE", Tserie);
                    procedure.setString("TNUMERO", Tnumero);
                    procedure.setString("TIDGUIAJUSTE", listaproductos.get(i).getidguiaajuste());
                    procedure.setString("TIDGUIAJUSTEDET", listaproductos.get(i).getidguiaajustedet());
                    procedure.setString("TTIPMOV", listaproductos.get(i).gettipmov());
                    procedure.setString("TNUNMOV", listaproductos.get(i).getnunmov());
                    procedure.setString("TOPC", opc);                    
                    rs1 = procedure.executeQuery();
                }

                rs1.next();
                resultado = rs1.getString(1);
                rs1.close();

                if (resultado.equals("1")) {
                    conex.commit();
                    return resultado; // proceso terminado
                } else {
                    conex.rollback();
                    return resultado; // si es 3 error discrepancia de venta
                }

            

        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;
    }


    public String Recupera_BotNCXCAJA(String IdBotica) {
        String botncxcaja = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_BOTNCXCAJA (?) }");
            procedure.setString("VIDBOTICA", IdBotica);            
            rs = procedure.executeQuery();

            while (rs.next()) {
                botncxcaja = rs.getString("botica");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error RECUPERA_CAMPOINTERNOOK" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return botncxcaja;
    }

    public String UpdateVtaDsctoGlobal(String interno,double totalModif,double subtotalModif, double igvModif,double DsctoGlobal) {
        String interno1 = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_VTA_INTERNO (?,?,?,?,?) }");
            procedure.setString("IDVENTA", interno);
            procedure.setDouble("TOTALMODIF", totalModif);
            procedure.setDouble("SUBTOTMODIF", subtotalModif);
            procedure.setDouble("IGV_MODIF", igvModif);
            procedure.setDouble("DSCTOGLOBAL", DsctoGlobal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                interno1 = rs.getString("interno");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return interno1;
    }

    public String recuperainternoNC(String serie,String numero, String Fecha, String tipovta) {
        String internoNC = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_INTERNONC (?,?,?,?) }");
            procedure.setString("VSERIE", serie);
            procedure.setString("VNUMERO", numero);
            procedure.setString("VFECHA", Fecha);
            procedure.setString("VTIPVENTA", tipovta);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                internoNC = rs.getString("interno");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return internoNC;
    }

    public String recuperaImpresionDouble() {
        String impresiondouble = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_IMPRESIONDOUBLE (?) }");
            procedure.setString("OPC", "1");
            rs = procedure.executeQuery();

            while (rs.next()) {
                impresiondouble = rs.getString("Valor");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return impresiondouble;
    }

    public String recuperaImpresionDoubleNC() {
        String impresiondouble = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_IMPRESIONDOUBLE (?) }");
            procedure.setString("OPC", "2");

            rs = procedure.executeQuery();

            while (rs.next()) {
                impresiondouble = rs.getString("Valor");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Bancos" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return impresiondouble;
    }

    public List<Venta> Verifica_Ventas_Salida(String idbotica, String idproforma) {
        List<Venta> milista = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_VENTAS_REVISAR (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDPROFORMA", idproforma);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Venta(rs.getString("Id_Venta"), rs.getString("Serie"),
                        rs.getString("Numero"), rs.getDouble("Total"),
                        rs.getString("vendedor"), rs.getString("cajero"), rs.getString("miproducto"),
                        rs.getString("Id_Laboratorio"), rs.getInt("unidad"), rs.getInt("fraccion"),
                        rs.getDouble("Precio_Venta_Final"), rs.getDouble("totaldetalle")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Verifica_Ventas_Salida" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;


    }

    public boolean Despachar_Venta(String idventa, String idbotica, String idproforma) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call GUARDAR_VENTA_ATENDIDA (?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDINTERNO", idventa);
            procedure.setString("IDPROFORMA", idproforma);
            procedure.executeQuery();
            conex.commit();
            resultado = true;
            rs.close();

        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public List<Venta> Lista_Cuadres_Cajas(String idbotica, String fecha) {
        List<Venta> milista = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CUADRES_CAJAS (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("MIFECHA", fecha);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Venta(rs.getString("cajero"), rs.getString("caja"),
                        rs.getString("DESTURNO"), rs.getInt("ID_CAJA"),
                        rs.getInt("Id_Personal"), rs.getInt("Turno"), rs.getString("InternoInicio"),
                        rs.getString("InternoFinal"), rs.getString("NroRendicion"), rs.getInt("Cierre")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Cuadres_Cajas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;

    }

    public List<Venta> Lista_Movimeintos_Caja(Venta p) {
        List<Venta> milista = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOVIMIENTOS_DE_CAJA (?,?,?,?,?)}");
            procedure.setString("FECHAINICIO", p.getFecha1());
            procedure.setString("INIDBOTICA", p.getId_Botica());
            procedure.setInt("INIDCAJA", p.getId_Caja());
            procedure.setInt("INIDCAJERO", p.getId_Personal_Botica_Caja());
            procedure.setInt("MITURNO", p.getTurno());
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Venta(rs.getString("Id_Venta"), rs.getString("Serie"),
                        rs.getString("Numero"), rs.getDouble("Monto"),
                        rs.getString("NOMCLIENTE"), rs.getString("DescripcionTipoPago"), rs.getString("tipoventa"),
                        rs.getString("vendedor"), rs.getTime("Hora_Venta"), rs.getDouble("SubTotal"), rs.getDouble("IGV")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Cuadres_Cajas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;
    }

    public List<Venta> Busca_Movimientos_Caja(Venta p, String idventa) {
        List<Venta> milista = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Busca_Movimientos_Caja (?,?,?,?,?,?)}");
            procedure.setString("FECHAINICIO", p.getFecha1());
            procedure.setString("INIDBOTICA", p.getId_Botica());
            procedure.setInt("INIDCAJA", p.getId_Caja());
            procedure.setInt("INIDCAJERO", p.getId_Personal_Botica_Caja());
            procedure.setInt("MITURNO", p.getTurno());
            procedure.setString("INTERNO", idventa);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Venta(rs.getString("Id_Venta"), rs.getString("Serie"),
                        rs.getString("Numero"), rs.getDouble("Monto"),
                        rs.getString("NOMCLIENTE"), rs.getString("DescripcionTipoPago"), rs.getString("tipoventa"),
                        rs.getString("vendedor"), rs.getTime("Hora_Venta"), rs.getDouble("SubTotal"), rs.getDouble("IGV")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Busca_Movimientos_Caja" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return milista;
    }

    public int Modificar_Tipo_Pagos(Venta entidad, List<Ventas_Tipo_Pago> lista_pagos) {

        int resultado = 0;
        ResultSet rs1 = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call MODIFICA_TIPOS_PAGOS (?,?,?,?) }");
            procedure.setString("IDBOTICA", entidad.getId_Botica());
            procedure.setInt("IDCAJA", entidad.getId_Caja());
            procedure.setString("IDVENTA", entidad.getId_Venta());
            procedure.setInt("IDTIPOPAGO", entidad.getId_TipoPago());
            rs = procedure.executeQuery();
            rs.next();
            resultado = rs.getInt(1);

            if (resultado == 1) {
                CallableStatement procedure1;
                for (int i = 0; i < lista_pagos.size(); i++) {
                    procedure1 = conex.prepareCall("{ call MODIFICA_VENTAS_TIPO_PAGO (?,?,?,?,?,?,?,?) }");
                    procedure1.setString("IDBOTICA", entidad.getId_Botica());
                    procedure1.setInt("IDCAJA", entidad.getId_Caja());
                    procedure1.setString("IDVENTA", entidad.getId_Venta());
                    procedure1.setDouble("MONTO", lista_pagos.get(i).getMonto());
                    procedure1.setString("NUMDOC", lista_pagos.get(i).getNumDocumento());
                    procedure1.setString("MIOPERADOR", lista_pagos.get(i).getOperador());
                    procedure1.setInt("TIPOPAGO", lista_pagos.get(i).getId_TipoPago());
                    procedure1.setInt("IDTIPOPAGO", entidad.getId_TipoPago());
                    rs1 = procedure1.executeQuery();
                }

                rs1.next();
                resultado = rs1.getInt(1);
                rs1.close();

                if (resultado == 2) {
                    conex.commit();
                    return resultado; // proceso terminado
                } else {
                    conex.rollback();
                    return resultado; // si es 3 error discrepancia de venta
                }

            } else {
                conex.rollback();
                return resultado; //0 no se puede cambiar
            }

        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public String RetornaEmpaque(String botica,String producto) {


        Connection mycnn = new ConexionPool().getConnection();
        CallableStatement procedure = null;
        //conex=null;
        //conex = new ConexionPool().getConnection();
        try {

            procedure = mycnn.prepareCall("{call RECUPERA_EMPAQUE(?,?)}");
            procedure.setString("vCP", producto);
            procedure.setString("IDBOTICA", botica);
            rs = procedure.executeQuery();
            rs.next();
            empaque = rs.getString(1);
            rs.close();
            mycnn.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != mycnn) {
                try {
                    mycnn.close();
                } catch (SQLException ex) {
                }
            }
        }
        mycnn=null;
        return empaque;

    }

    public double VerificaIGVProducto(String idproducto) {
        double resul6 = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Verifica_IGV_Producto (?) }");
            procedure.setString(1, idproducto);
            rs = procedure.executeQuery();
            rs.next();
            resul6 = rs.getDouble("IGV_Exonerado");
            rs.close();
            return resul6;

        } catch (Exception ex) {
            System.out.println("Error en CAPA DE DATOS METODO VerificaIGVProducto " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }
        return resul6;

    }

    public boolean Cierre_Revision_Caja(Venta entidad) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call CIERRE_REVISION_CAJA (?,?,?,?) }");
            procedure.setString("IDBOTICA", entidad.getId_Botica());
            procedure.setInt("IDCAJA", entidad.getId_Caja());
            procedure.setString("MIFECHA", entidad.getFecha1());
            procedure.setInt("IDTURNO", entidad.getTurno());
            procedure.executeQuery();
            conex.commit();
            resultado = true;
            rs.close();

        } catch (Exception ex) {
            try {
                System.out.println(ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public boolean Existe_Serie_Numero(Venta venta) {

        boolean resultado = true;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call VERIFICA_SERIE_NUMERO (?,?,?,?,?) }");
            procedure.setString("IDBOTICA", venta.getId_Botica());
            procedure.setInt("IDCAJA", venta.getId_Caja());
            procedure.setInt("TIPVENTA", venta.getId_Tipo_Venta());
            procedure.setString("MISERIE", venta.getSerie());
            procedure.setString("MINUMERO", venta.getNumero());
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                resultado = false;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public double Recupera_MontoVenta(Cajas caja) {
        double monto = 0.0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_TOTAL_EFECTIVO (?,?,?,?,?) }");
            procedure.setString("FECHAINICIO", caja.getFecha());
            procedure.setString("INIDBOTICA", caja.getIdbotica());
            procedure.setInt("INIDCAJA", caja.getIdcaja());
            procedure.setInt("INIDCAJERO", caja.getIdpersonal());
            procedure.setInt("MITURNO", caja.getTurno());
            rs = procedure.executeQuery();

            while (rs.next()) {
                monto = rs.getDouble("DEPOSITAR");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Recupera_MontoVenta" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return monto;
    }

    public String RetornaCantidad_Compara(String tipovta) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CANT_TIPOPAGO(?) }");
            procedure.setString("TIPVTA", tipovta);
            rs = procedure.executeQuery();
            rs.next();
            cant = rs.getString(1);
            tipo = rs.getString(2);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cant + "/"+ tipo;

    }
    
    public String Recupera_DocumentoSalida(String idbotica) {
        String dconum = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DOCUMENTOSALIDA (?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                dconum = rs.getString("DOCNUM");
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Recupera_DocumentoSalida" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return dconum;
    }

    public Venta_Delivery GuardaSalida(Venta_Delivery obj, List<String> Internos) {

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call GUARDA_SALIDADELIVERY (?,?,?,?,?,?) }");
            procedure.setString("SALIDA", obj.getNumeroSalida());
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setInt("IDPERSONAL", obj.getId_Personal());
            procedure.setDouble("TOTAL", obj.getTotal());
            procedure.setDouble("EFECTIVO", obj.getEfectivoEntregado());
            procedure.setInt("ACTIVO", obj.getActivo());
            procedure.execute();

            CallableStatement procedure1 = null;
            for (int i = 0; i < Internos.size(); i++) {
                procedure1 = conex.prepareCall("{ call GUARDA_DETALLEELIVERY (?,?,?) }");
                procedure1.setString("SALIDA", obj.getNumeroSalida());
                procedure1.setString("IDBOTICA", obj.getId_Botica());
                procedure1.setString("IDVENTA", Internos.get(i));
                procedure1.execute();
            }

            conex.commit();

        } catch (Exception ex) {
            try {
                obj = null;
                conex.rollback();
                System.out.println("Error GuardaSalida" + ex.getMessage());
            } catch (SQLException ex1) {
                return null;
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;
    }

    public List<Venta_Delivery> Lista_Salidas_Delivery(String idbotica) {
        List<Venta_Delivery> lista = new ArrayList<Venta_Delivery>();
        Venta_Delivery ventaDelivery;
        Personal persona;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_SALIDASDELIVERY (?)}");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                ventaDelivery = new Venta_Delivery();
                persona = new Personal();
                ventaDelivery.setId_Botica(rs.getString("Id_Botica"));
                ventaDelivery.setNumeroSalida(rs.getString("NumeroSalida"));
                ventaDelivery.setHoraSalida(rs.getTime("HoraSalida"));
                ventaDelivery.setHoraLLegada(rs.getTime("HoraLLegada"));
                ventaDelivery.setEfectivoEntregado(rs.getDouble("EfectivoEntregado"));
                ventaDelivery.setVuelto(rs.getDouble("Vuelto"));
                ventaDelivery.setTotal(rs.getDouble("Total"));
                ventaDelivery.setActivo(rs.getInt("Activo"));
                ventaDelivery.setObservacion(rs.getString("Observacion"));
                ventaDelivery.setId_Personal(rs.getInt("Id_Personal"));
                persona.setApellido(rs.getString("Apellido"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setId_Personal(rs.getInt("Id_Personal"));
                ventaDelivery.setPersona(persona);
                lista.add(ventaDelivery);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Lista_Salidas_Delivery" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return lista;
    }

    public List<Detalle_VentaDelivery> Detallle_Salidas_Delivery(String idbotica, String salida) {
        List<Detalle_VentaDelivery> lista = new ArrayList<Detalle_VentaDelivery>();
        Venta_Delivery ventaDelivery;
        Venta venta;
        Detalle_VentaDelivery detalle;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DETALLEDELIVERY (?,?)}");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("NUMERO", salida);
            rs = procedure.executeQuery();

            while (rs.next()) {
                ventaDelivery = new Venta_Delivery();
                venta = new Venta();
                detalle = new Detalle_VentaDelivery();
                ventaDelivery.setId_Botica(rs.getString("Id_Botica"));
                ventaDelivery.setNumeroSalida(rs.getString("NumeroSalida"));
                venta.setId_Venta(rs.getString("Id_Venta"));
                venta.setSerie(rs.getString("Serie"));
                venta.setNumero(rs.getString("Numero"));
                venta.setTotal(rs.getDouble("Total"));
                detalle.setDelivery(ventaDelivery);
                detalle.setVenta(venta);
                lista.add(detalle);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error Detallle_Salidas_Delivery" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return lista;
    }

    public boolean Actualiza_EntradaDelivery(String idbotica, String docnum, double vuelto, String observ) {
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_ENTRADADELIVERY (?,?,?,?)}");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("SALIDA", docnum);
            procedure.setDouble("MIVUELTO", vuelto);
            procedure.setString("OBSER", observ);
            procedure.execute();
            conex.commit();

        } catch (SQLException ex) {
            try {
                conex.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(VentaData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return true;
    }


    public List<Venta> Lista_Internos_Ventas_Delivery(Venta objventa, int op) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_INTERNOS_VENTAS_DELIVERY (?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setString("IDINTERNO", objventa.getId_Venta());
            procedure.setInt("OPCION", op);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("TIPOVENTA"), rs.getString("TIPOPAGO"),
                        rs.getDouble("SubTotal"), rs.getDouble("Total"),
                        rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"),
                        rs.getString("DNI"), rs.getString("VENDEDOR"),
                        rs.getDouble("IGV"), rs.getString("CAJERO"), rs.getInt("Id_Cliente"),
                        rs.getDouble("Redondeo"),rs.getDouble("TotFinal"),
                        rs.getString("tpago"), rs.getString("tredondeo"), rs.getString("MODOIMPRESION")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_Ventas" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }
    public ArrayList<Venta> Lista_Internos_Para_Ndebito(String IdProducto){
        ArrayList<Venta> listaventas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Lista_Internos_VentasNdebito (?) }");
            procedure.setString("IdProducto", IdProducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getString("Fecha_venta"),
                        rs.getString("Serie"),
                        rs.getString("Numero"),
                        rs.getString("Descripcion"),
                        rs.getInt("Unidad"),
                        rs.getInt("Fraccion"),
                        rs.getDouble("Total")
                        ));

            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Internos_VentasNdebito" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;
    }

    

}
