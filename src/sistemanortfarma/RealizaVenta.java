package sistemanortfarma;

import CapaDatos.ClientesData;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProforma;
import CapaLogica.LogicaTiposPagos;
import CapaLogica.LogicaVenta;
import CapaLogica.LogicaClientes;
import CapaLogica.LogicaDocumentosVentas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaRecuperaCaja;
import entidad.Clientes;
import entidad.Descuento;
import entidad.DocumentoVentas;
import entidad.Laboratorios;
import entidad.ListaDetalles;
import entidad.Proforma;
import entidad.ResultadoVenta;
import entidad.TiposPagos;
import CapaLogica.LogicaPersonal;
import entidad.Venta;
import entidad.VentaComparatorByOrdenTotal;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.TipoCambio;
import entidad.TipoVenta;
import entidad.Personal;
import entidad.Ventas_Tipo_Pago;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;
import org.jdesktop.application.Action;
import service.*;
//import webservices.*;
/**
 *
 * @author Miguel Gomez S. gomez
 */
public class RealizaVenta extends javax.swing.JInternalFrame implements KeyListener {

    LogicaProducto objProducto = new LogicaProducto();
    static ProductosPrecios productoPrecio = null;
    LogicaProforma logicaProforma = new LogicaProforma();
    LogicaTiposPagos objlogicapagos = new LogicaTiposPagos();
    LogicaVenta objguardaventa = new LogicaVenta();
    LogicaPersonal logicaPersonal = new LogicaPersonal();
    LogicaClientes objClientes = new LogicaClientes();
    LogicaRecuperaCaja objcaja = new LogicaRecuperaCaja();
    RequisitosFactura objfactura = new RequisitosFactura();
    MuestraVentana objetoventana = new MuestraVentana();
    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
    LogicaDocumentosVentas objDocumentoVentas = new LogicaDocumentosVentas();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    MailClient objmail = new MailClient();
    LogicaProducto objproducto = new LogicaProducto();
    private List<ProductosPrecios> listaProductosdscto = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listadsctogratuita = new ArrayList<ProductosPrecios>();
    Mant_Productos mantProduc = new Mant_Productos();
    List<ProductosPrecios> listsubtotales = new ArrayList<ProductosPrecios>();
    List<Proforma> listproforma = new ArrayList<Proforma>();
    List<Proforma> listproformaDetalle = new ArrayList<Proforma>();
    List<String> listtipoprecio = new ArrayList<String>();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    List<TiposPagos> listtarj = new ArrayList<TiposPagos>();
    List<Venta> listaventa = new ArrayList<Venta>();
    List<Venta> listVentaSinIGV = new ArrayList<Venta>();
    List<ProductosPrecios> productostock = new ArrayList<ProductosPrecios>();
    List<Object> lisProdSinIGV = new ArrayList<Object>();
    List<Ventas_Tipo_Pago> lista_pagos = new ArrayList<Ventas_Tipo_Pago>();
    List<TipoVenta> lis_tipo_venta;
    List<TipoVenta> lis_tipo_venta_1;
    List<Personal> listaPersonalOficina;
    List<Clientes> ListDescuentos = new ArrayList<Clientes>(); //PARA LOS DESCUENTOS ESPECIALES
    List<DocumentoVentas> listaDocumentoVentas;
    List<Producto> listsinstock;
    /*Para las promociones*/
    List<Integer> nuevaPromocion = new ArrayList();
    List<Integer> cantidadElementos = new ArrayList();
    List<ListaDetalles> DetallePromo = new ArrayList<ListaDetalles>();
    List<Object> listaProductosVerifica = new ArrayList<Object>();
    LogicaClientes logcliente = new LogicaClientes();
    //int ordenproducto = 1;
    Proforma objproforma;
    FormClientes objcliente;
    AplicacionVentas objventa;
    ProductosPrecios objsubtotalIgv;
    ProductosPrecios objetostock;
    Ventas_Tipo_Pago objeto;
    Ventas_Tipo_Pago objeto1;
    Descuento midescuentos;
    Clientes def;
    Object[] listadetalle = new Object[12];    
    private DefaultTableModel tablaproforma;
    public static String cupon = "";
    public static String CUPONVTA = "";
    public static String certificado = "";
    public static String EsSAp = "";
    public static boolean espromo;
    private String cantidad;
    private double PVP, PVPx, descuento, parcial, bdpromo;
    int podecimal = 4, cantidadproductos = 0, unidad, fraccion,unifra;
    TableColumnModel colModel;
    TableColumn col, colu_9;
    int ordenproducto = 1;
    private String idproforma, serie, numero, idbotica, montoPagoT,vueltoT, reccupon,recCertificado;
    private double  IGV = 0.0, SubTotal = 0.0 , OpGravada = 0.0, total = 0.0, OpExonerada=0.0, OpInafecta=0.0,OpGratuita=0.0,OpISC=0.0,miigv=0.0,OpBonificacion=0.0,OpDescuentoTotal = 0.0, OpDescuento = 0.0, preciounitario=0.0,auxigv=0.0,
            total_pigv=0.0,miigv_x = 0.0;
    int Id_Personal_Botica_Venta, idcaja, idcliente, idtipopago, bandseleccion = 0, tipventa = 0, filaseleccionado;
    private String idtpred="";
    private Object stockempaque, empaque, stockfraccion;
    private Date fecha;
    private int EsMultiDscto = 0;
    private int cantbolsas = 0;
    private String Id_Tipo_Precio;
    private int podecimalPantalla =2;
    private String dniresponsable, idmedico = "", colegiatura = "", RUC = "",esgratuita1="",esgratuita2="";
    private static int id_personal;
    private int veces, cantpagos = 0, poscant = 0, canttiposPagos = 1;
    String codprodespec; //CODIGO DEL PRODUCTO SI ES QUE HAY DESCUENTO
    String modif;
    String Esgratiola;
    boolean keypress = true;
    private int ventaDelivery;
    BuscarProductos objproductos = null;
    BuscarProductosPromocion objproductosPromocion = null;
    int existe= 0;
    int contarpromo=0;
    private static String id_botica;
    public native void displayHelloWorld();

    public RealizaVenta(AplicacionVentas obj, Object auxidcaja) {
        initComponents();
        //abrirventas();
        //jTextField14.setVisible(false);      
        id_botica = objventa.getIdbotica();
        setId_botica(id_botica);
        txtruc.requestFocus();
        objventa = obj;
        podecimal = OpcionesMenu.getCantidadDecimales();
        podecimalPantalla = OpcionesMenu.getCantidadDecimalesPantalla();
        Id_Tipo_Precio = "01";
        idcaja = Integer.parseInt(auxidcaja.toString());
        setTitle(" Realiza Venta    Caja " + idcaja + " ");
        jLabel35.setText(String.valueOf("CAJA " + idcaja));
        fecha = objventa.getFecha();
        idbotica = objventa.getIdbotica();
        id_personal = objventa.getId_personal_botica();
        tablaproforma = (DefaultTableModel) jTable2.getModel();
        colModel = jTable2.getColumnModel();
        AparienciaTabla();
        txtruc.setDocument(new LimitadorLetras(txtruc, 11));
        txtdni.setDocument(new LimitadorLetras(txtdni, 8));
        jTextField43.setDocument(new LimitadorLetras(jTextField43, 5));        
        jTextField30.setText(objventa.getUsuario());
        CompletaTiposPagos();
        RecuperaTipoVentas();
        HabilitaBotones(false);
        Deshabilita_Check(false);
        jTabbedPane1.setEnabledAt(1, false);
        Recupera_Serie_Numero();
        Muestra_Tipos_Cambios();
        Muestra_Moneda();
        this.jComboBox9.setEnabled(false);
        def = objClientes.Cliente_Defecto();
        RecuperaInterno();
        jTextField11.setVisible(false);
        jTextField40.setVisible(false);
        jTextField7.setVisible(false);
        jTextField43.setVisible(false);
        jLabel8.setVisible(false);
        jButton10.setVisible(false);
        jButton5.setVisible(false);
        txtdni.setVisible(true);
        jTabbedPane2.setSelectedIndex(0);
        JTextComponent editor;
        editor = (JTextComponent) jComboBox8.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jTextField1.requestFocus();
                }
                RealizaOpciones(e);
            }
        });

        jLabel36.setText("");
        jLabel37.setText("");
        jTextField5.setVisible(false);
        jTextField41.setVisible(false);
        jTextField7.setVisible(false);

        jTextField44.setText("0.00");
        jTextField45.setText("0.00");
        jLabel41.setText("");
    }
    
    public static String getId_botica() {
        return id_botica;
    }

    public static void setId_botica(String id_botica) {
        RealizaVenta.id_botica = id_botica;
    }

    public String getIdbotica() {
        return idbotica;
    }

    public void setIdbotica(String idbotica) {
        this.idbotica = idbotica;
    }

    private void Deshabilita_Check(boolean valor) {
        jCheckBox2.setEnabled(valor);
        jCheckBox3.setEnabled(valor);
        jCheckBox4.setEnabled(valor);
        jCheckBox5.setEnabled(valor);
    }

    private void HabilitaBotones(boolean valor) {
        this.jButton1.setEnabled(valor);
        this.jButton2.setEnabled(valor);
        this.jButton4.setEnabled(valor);
        this.jButton6.setEnabled(valor);
        this.jButton8.setEnabled(valor);
        this.jButton9.setEnabled(valor);
        this.jButton10.setEnabled(valor);
        this.jButton11.setEnabled(valor);
        this.jTextField43.setText("0.00");
        //this.jTextField44.setText("0.00");
        //this.jTextField45.setText("0.00");
        
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(jTable2.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable2.getColumnModel().getColumn(8).setCellRenderer(tcr);
        this.jTable2.getColumnModel().getColumn(7).setCellRenderer(tcr);
        this.jTable2.getColumnModel().getColumn(6).setCellRenderer(tcr);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        this.jTable2.getColumnModel().getColumn(5).setCellRenderer(tcenter);

        colu_9 = jTable2.getColumnModel().getColumn(9);
        colu_9.setPreferredWidth(0);
        colu_9.setMinWidth(0);
        colu_9.setMaxWidth(0);

    }

    private void BusquedaProducto() {
        objproductos.setSeleccionaart(false);
        objproductos = new BuscarProductos(objetoventana, Id_Tipo_Precio);
        objproductos.setVisible(true);
        int contProm = 0;
        int idprom1 = 0;
        String nuevocodMultiple="";
        int multiple = 0;
        ProductosPromociones objProm = null;

        if (objproductos.seleccionaart) {

           contProm = objProducto.Verifica_Promocion(objproductos.getProductoPrecio().getProductoBotica().getProducto().getIdProducto()/*,idprom*/);
           multiple = objProducto.Verifica_Promo_PorPrecio(objproductos.getProductoPrecio().getProductoBotica().getProducto().getIdProducto(), 5, 0);
            //contProm = 2;
            
            if (multiple !=1){
                
            
           if (contProm > 1) {
               
               
               String idprod = objproductos.getProductoPrecio().getProductoBotica().getProducto().getIdProducto();
                String Nomprod = objproductos.getProductoPrecio().getProductoBotica().getProducto().getDescripcion();
                objProm = new ProductosPromociones(objetoventana, Nomprod, idprod);
                objProm.pack();
                objProm.setVisible(true);

                idprom1 = objProm.getIdpromo();

                if (idprom1 > 0 ){
                    
                    new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
                    if (ProductoPedido.ingresadet) {
                        if (ProductoPedido.getCantidad().compareTo("") != 0) {
                            AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio(),idprom1,multiple,EsMultiDscto,0);
                        }
                    }
                }
               
            } else {
           


                    new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
                    if (ProductoPedido.ingresadet) {
                        if (ProductoPedido.getCantidad().compareTo("") != 0) {
                            AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio(),idprom1,multiple,EsMultiDscto,0);
                        }
                    }

            }
           
         } else{// hasta aqui empieza multiplo 
                
                new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
                if (ProductoPedido.ingresadet) {
                    if (ProductoPedido.getCantidad().compareTo("") != 0) {
                        AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio(), idprom1,multiple,0,0);
                    }
                }            
        }
        }
    }

    private void BusquedaProductoPromocion() {
        objproductosPromocion.setSeleccionaart(false);
        objproductosPromocion = new BuscarProductosPromocion(objetoventana, Id_Tipo_Precio);
        objproductosPromocion.setVisible(true);
        if (objproductosPromocion.seleccionaart) {
            //new ProductoPedido(objproductosPromocion.getProductoPrecio()).setVisible(true);
            //if (ProductoPedido.ingresadet) {
            // if (ProductoPedido.getCantidad().compareTo("") != 0) {
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    int var = 1;
                    AgregaProformaPromocionPrecio("1", objproductosPromocion.getProductoPrecio(), var);
                } else {
                    int var = 2;
                    AgregaProformaPromocionPrecio("1", objproductosPromocion.getProductoPrecio(), var);
                }
            }
            //}
            //}
        }
    }

    private void Muestra_Tipos_Cambios() {
        List<TipoCambio> misTiposCambios = objguardaventa.Retorna_Tipos_Cambios();
        for (int i = 0; i < misTiposCambios.size(); i++) {
            this.jComboBox6.addItem(misTiposCambios.get(i).getMoneda());
            this.jTextField36.setText(String.valueOf(misTiposCambios.get(i).getTipoCambio()));
        }
    }

    private void Muestra_Moneda(){
        List<TipoCambio> misTiposMoneda = objguardaventa.Retorna_Moneda();
        for (int i = 0; i < misTiposMoneda.size(); i++) {
            this.jComboBox9.addItem(misTiposMoneda.get(i).getMoneda1());
        }
    }
    

    public void Recupera_Serie_Numero() {
        listaDocumentoVentas = objDocumentoVentas.obtenerIniciacionSerie(idbotica, idcaja, comboboxtipodocumento.getSelectedItem().toString());
        if (listaDocumentoVentas.size() > 0) {
            this.jLabel28.setText(String.valueOf(comboboxtipodocumento.getSelectedItem().toString().charAt(0)));
            this.jTextField31.setText(listaDocumentoVentas.get(0).getSerie());
            this.jTextField35.setText(listaDocumentoVentas.get(0).getNumeracion());
        }
    }

    private boolean VerificaStock(String idproduc, int unipedida, int fraccpedida) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();
        int stkempaque;

        try {

            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica);

            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = Integer.parseInt(empaque.toString());

                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }

                //RECUPERO MI STOCK DEL EMPAQUE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = Integer.parseInt(stockempaque.toString());
                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = Integer.parseInt(stockfraccion.toString());

                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unipedida * empaque_tmp + fraccpedida;

                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }

        return resultado;

    }

    private void RecuperaTipoVentas() {
        lis_tipo_venta = new ArrayList<TipoVenta>();
        lis_tipo_venta = objfactura.Retorna_Tipos_Ventas();

        for (int i = 0; i < lis_tipo_venta.size(); i++) {
            comboboxtipodocumento.addItem(lis_tipo_venta.get(i).getDESCRIPCION());
        }
    }

    public void AsignaDatos_Proforma_Adicional(String idprofor) {


        ordenproducto = 1;
        listaProductosVerifica.clear();

        List<Proforma> listformaDetalle = new ArrayList<Proforma>();
        listformaDetalle = logicaProforma.Recupera_Detalle_Proforma(idprofor, idbotica);

        if (Id_Tipo_Precio.compareTo(listformaDetalle.get(0).getId_Tipo_Precio().trim()) == 0) {
            int posicion = listproformaDetalle.size();
            int tamaño = listformaDetalle.size();

            for (int i = 0; i < tamaño; i++) {
                objproforma = new Proforma(listformaDetalle.get(i).getIdproducto(),
                        listformaDetalle.get(i).getDescipcionproducto(), listformaDetalle.get(i).getUNIDAD(),
                        listformaDetalle.get(i).getFRACCION(), listformaDetalle.get(i).getPrecio_Venta(), listformaDetalle.get(i).getDescuento(),
                        listformaDetalle.get(i).getPvx(), listformaDetalle.get(i).getTotal(), listformaDetalle.get(i).getFecha_Vencimiento(), listformaDetalle.get(i).getIGV_Exonerado(),
                        listformaDetalle.get(i).getEmpaque(), listformaDetalle.get(i).getStock_Empaque(), listformaDetalle.get(i).getStock_Fraccion(), listformaDetalle.get(i).getId_laboratorio());
                listproformaDetalle.add(posicion, objproforma);
                posicion++;
            }

            posicion = jTable2.getRowCount();
            Productos_Botica productoBotica;
            Producto producto;

            for (int i = 0; i < tamaño; i++) {
                String idprodu = listformaDetalle.get(i).getIdproducto();
                String descrip = listformaDetalle.get(i).getDescipcionproducto();
                int unidad1 = listformaDetalle.get(i).getUNIDAD();
                int fraccion1 = listformaDetalle.get(i).getFRACCION();
                double pvp = listformaDetalle.get(i).getPrecio_Venta();
                String labora = listformaDetalle.get(i).getId_laboratorio();
                double pvx = listformaDetalle.get(i).getPvx();
                double total1 = listformaDetalle.get(i).getTotal();
                String orden = listformaDetalle.get(i).getOrdenProducto();
                cantidadproductos++;

                double igvaux = listformaDetalle.get(i).getIGV_Exonerado();

                if (igvaux == 0) {
                    lisProdSinIGV.add(idprodu);
                } else {
                    lisProdSinIGV.add(9999);
                }

                objetostock = new ProductosPrecios();
                productoBotica = new Productos_Botica();
                producto = new Producto();

                producto.setEmpaque(listformaDetalle.get(i).getEmpaque());
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(listformaDetalle.get(i).getStock_Empaque());
                productoBotica.setMostrador_Stock_Fraccion(listformaDetalle.get(i).getStock_Fraccion());
                objetostock.setProductoBotica(productoBotica);
                productostock.add(posicion, objetostock);
                posicion++;

                listadetalle[0] = cantidadproductos;
                listadetalle[1] = idprodu;
                listadetalle[2] = descrip;
                listadetalle[3] = labora;
                listadetalle[4] = unidad1;
                listadetalle[5] = fraccion1;
                listadetalle[6] = pvp;
                listadetalle[9] = "0";
                listadetalle[10] = "0";
                listadetalle[11] = orden;
                ordenproducto = Integer.parseInt(orden);

                BigDecimal bd1 = new BigDecimal(pvx);
                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                BigDecimal bd2 = new BigDecimal(total1);
                bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                listadetalle[7] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                listadetalle[8] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                tablaproforma.addRow(listadetalle);

                if (igvaux == 0.0) {
                    lisProdSinIGV.add(idprodu);
                }

                Calcula_Monto(igvaux, pvx, total1);

                listaProductosVerifica.add(listadetalle);


            }

            VerificaPagoFraccionado(idtipopago);
            cantidadproductos = jTable2.getRowCount();
            jTable2.requestFocus();
            jTable2.getSelectionModel().setSelectionInterval(0, 0);

            col = jTable2.getColumnModel().getColumn(0);
            col.setCellRenderer(new ColoredTableCellRenderer());
            col = jTable2.getColumnModel().getColumn(1);
            col.setCellRenderer(new ColoredTableCellRenderer());

        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS NO PUEDES AGREGAR ESTA PROFORMA  ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void Calcula_Monto(double igvaux, double pvx, double total1) {
        double auxparcial = 0.0;
        //total = 0;
        //SubTotal = 0;
        double valortabla = total1;
        OpExonerada = 0.0;
        OpDescuento = 0.0;
        jTextField44.setText("0.00");
        jTextField45.setText("0.00");

        int fila = listsubtotales.size();

        if (igvaux == 0) {
            total += valortabla;
            SubTotal += valortabla;
            OpExonerada +=valortabla;
            objsubtotalIgv = new ProductosPrecios(valortabla, igvaux);
            listsubtotales.add(fila, objsubtotalIgv);
        } else {
            if (IGV == 0) {
                CapturaIGV();
            }

            total += valortabla;
            auxparcial = (valortabla / (1 + (IGV / 100)));

            objsubtotalIgv = new ProductosPrecios(auxparcial, igvaux);
            listsubtotales.add(fila, objsubtotalIgv);
            SubTotal += auxparcial;
        }

        if(valortabla < 0){

            OpDescuento += OpDescuento;
            OpDescuentoTotal += OpDescuentoTotal;
        }

        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();
        /*

        this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
        this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
        double igvauxiliar = total - SubTotal;

        BigDecimal bd3 = new BigDecimal(igvauxiliar);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        igvauxiliar = bd3.doubleValue();

        this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv

        if (this.jCheckBox1.isSelected()) {
            this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        }
        this.jTextField11.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        */

        //AQUI
        String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        int deci = Integer.parseInt(arr[1]);

        String x = Integer.toString(deci);
        int contardeci = x.length();

        if (contardeci > 1){

        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
        String ultimodigito = String.valueOf(ultimoNumero);

            //if (ultimodigito.equals("0") || ultimodigito.equals("5")){
            if (ultimodigito.equals("0")){
                jTextField44.setText("0.00"); //redondeo
                jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                double igvauxiliar = total - SubTotal;

                BigDecimal bd3 = new BigDecimal(igvauxiliar);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                igvauxiliar = bd3.doubleValue();

                jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal

                if (this.jCheckBox1.isSelected()) {
                    this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }
                this.jTextField11.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());


            }else{
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    double igvauxiliar = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(igvauxiliar);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    igvauxiliar = bd3.doubleValue();

                    jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                    if (this.jCheckBox1.isSelected()) {
                        this.jTextField17.setText(bdNT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }
                    this.jTextField11.setText(bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

                }else{

                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                        //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                        double ultimonuevo = Double.valueOf(ultimodigito);
                        Double redondeo = (ultimonuevo /100) * -1;
                        Double nuevototal = total - (ultimonuevo /100);
                        //total = nuevototal;
                        //SubTotal = (total / (1 + (IGV / 100)));
                        BigDecimal bdNT = new BigDecimal(nuevototal);
                        bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        nuevototal = bdNT.doubleValue();


                        jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                        jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                        double igvauxiliar = total - SubTotal;

                        BigDecimal bd3 = new BigDecimal(igvauxiliar);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        igvauxiliar = bd3.doubleValue();

                        jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                        jTextField44.setText(String.valueOf(redondeo)); //redondeo
                        jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                        if (this.jCheckBox1.isSelected()) {
                            this.jTextField17.setText(bdNT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }
                        this.jTextField11.setText(bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

                    }

                }
            }
        }else{

            this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
            double igvauxiliar = total - SubTotal;

            BigDecimal bd3 = new BigDecimal(igvauxiliar);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            igvauxiliar = bd3.doubleValue();

            this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv
            jTextField44.setText("0.0");                                                                    //redondeo
            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar

            if (this.jCheckBox1.isSelected()) {
                this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            this.jTextField11.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
      }

    }
    
    public void AsignaDatos_Proforma(String idprofor, List<Proforma> listaproforma, int fila) {
        try {
            String Tmarcarpromo = "0";
            ordenproducto = 1;
            reccupon="";
            cantbolsas = 0;
           listaProductosVerifica.clear();
           
            limpiardatos();
            idproforma = idprofor;
            jTextField1.setText(idproforma);
            listsinstock = new ArrayList<Producto>();
            veces = 0;

            if (listaproforma.size() > 0) {

                this.txtdni.setText(listaproforma.get(fila).getDniaux());
                this.txtcliente.setText(listaproforma.get(fila).getNomCliente());                
                //this.jtextRUCDNI.setText(listaproforma.get(fila).getRUC());

                List<Clientes> midirecciones = objClientes.Lista_Direcciones(idbotica, listaproforma.get(fila).getId_Cliente(), listaproforma.get(0).getIdEmpresa(), idproforma);
                
                for (int k = 0; k < midirecciones.size(); k++) {
                    jComboBox8.addItem(midirecciones.get(k).getDireccion());
                }

                idcliente = listaproforma.get(fila).getId_Cliente();
                jTextField2.setText(String.valueOf(idcliente));
                idtipopago = listaproforma.get(fila).getId_TipoPago();
                idmedico = listaproforma.get(fila).getId_Medico();
                Id_Tipo_Precio = listaproforma.get(fila).getId_Tipo_Precio();
                Id_Personal_Botica_Venta = listaproforma.get(fila).getId_Personal_Botica_Venta();
                dniresponsable = listaproforma.get(fila).getIdperosnalmodficada();
                RUC = listaproforma.get(fila).getRUC();
                tipventa = listaproforma.get(fila).getId_Tipo_Venta();
                reccupon = listaproforma.get(fila).getcupon();
                cantbolsas = listaproforma.get(fila).getCantbolsas();
                
                if(reccupon==null){ 
                    reccupon ="";
                }

                for (int w = 0; w < lis_tipo_venta.size(); w++) {
                    if (lis_tipo_venta.get(w).getId_Tipo_Venta() == tipventa) {
                        comboboxtipodocumento.setSelectedIndex(w);
                        break;
                    }
                }

                for (int k = 0; k < this.comboboxtipodepago.getItemCount(); k++) {
                    if (idtipopago == listatipospagos.get(k).getId_TipoPago()) {
                        comboboxtipodepago.setSelectedIndex(k);
                        k = comboboxtipodepago.getItemCount();
                    }
                }
                 
                if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 11) {
                    if (!listaproforma.get(fila).getDNI().equals("") && !listaproforma.get(fila).getRUC().equals("")){
                    this.txtruc.setText(listaproforma.get(fila).getDNI());
                    }else if (listaproforma.get(fila).getDNI().equals("") && !listaproforma.get(fila).getRUC().equals("")){
                        this.txtruc.setText(listaproforma.get(fila).getRUC());
                    }else if (!listaproforma.get(fila).getDNI().equals("") && listaproforma.get(fila).getRUC().equals("")){
                        this.txtruc.setText(listaproforma.get(fila).getDNI());
                    }
                }else if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 12) {
                    if (!listaproforma.get(fila).getDNI().equals("") && !listaproforma.get(fila).getRUC().equals("")){
                    this.txtruc.setText(listaproforma.get(fila).getRUC());
                    }else if (listaproforma.get(fila).getDNI().equals("") && !listaproforma.get(fila).getRUC().equals("")){
                        this.txtruc.setText(listaproforma.get(fila).getRUC());
                    }else if (!listaproforma.get(fila).getDNI().equals("") && listaproforma.get(fila).getRUC().equals("")){
                        this.txtruc.setText(listaproforma.get(fila).getDNI());
                    }
                }
                /*
                 * RECUPERO EL DETALLE DE LA PROFORMA
                 */

                listproformaDetalle = logicaProforma.Recupera_Detalle_Proforma(idproforma, idbotica);
                Productos_Botica productoBotica;
                Producto producto;

                // Recupero numero de registros del Jtable
                // Gino Paredes Zurita

                for (int i = 0; i < listproformaDetalle.size(); i++) {
                    listadetalle = new Object[20];
                    String idprodu = listproformaDetalle.get(i).getIdproducto();
                    String descrip = listproformaDetalle.get(i).getDescipcionproducto();
                    int unidad1 = listproformaDetalle.get(i).getUNIDAD();
                    int fraccion1 = listproformaDetalle.get(i).getFRACCION();
                    double pvp = listproformaDetalle.get(i).getPrecio_Venta();
                    String laboratorio = listproformaDetalle.get(i).getId_laboratorio();
                    double pvx = listproformaDetalle.get(i).getPvx();
                    double total1 = listproformaDetalle.get(i).getTotal();
                    String ordenproduto = listproformaDetalle.get(i).getOrdenProducto();
                    BigDecimal bd2 = new BigDecimal(total1);
                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    String orden = listproformaDetalle.get(i).getOrdenProducto();
                    int IdProdRecarga = listproformaDetalle.get(i).getidOperadorRec();
                    int Esgratispromo = listproformaDetalle.get(i).getEsgratispromo();
                    String certMed = listproformaDetalle.get(i).getCertMed();


                    if (total1 < 0) {
                        jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }
                    double igvaux = listproformaDetalle.get(i).getIGV_Exonerado();

                    //MARCO LOS PRODUCTOS QUE TIENES IGV Y LOS QUE NO TIENEN
                    if (igvaux == 0) {
                        lisProdSinIGV.add(idprodu);
                    } else {
                        lisProdSinIGV.add(9999);
                    }

                    objetostock = new ProductosPrecios();
                    productoBotica = new Productos_Botica();
                    producto = new Producto();

                    producto.setEmpaque(listproformaDetalle.get(i).getEmpaque());
                    productoBotica.setProducto(producto);
                    productoBotica.setMostrador_Stock_Empaque(listproformaDetalle.get(i).getStock_Empaque());
                    productoBotica.setMostrador_Stock_Fraccion(listproformaDetalle.get(i).getStock_Fraccion());
                    objetostock.setProductoBotica(productoBotica);
                    productostock.add(i, objetostock);
                    Tmarcarpromo = mantProduc.MarcarPromo(idprodu).toString().trim();
                    if (Tmarcarpromo.equals("")){
                        Tmarcarpromo = "0";
                    }

                    listadetalle[0] = i + 1;
                    listadetalle[1] = idprodu;
                    listadetalle[2] = descrip ;
                    listadetalle[3] = laboratorio;
                    listadetalle[4] = unidad1;
                    listadetalle[5] = fraccion1;
                    listadetalle[6] = pvp;
                    listadetalle[9] = "0";
                    listadetalle[10] = "0";
                    listadetalle[11] = orden;

                    BigDecimal bd1 = new BigDecimal(pvx);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                    // SIS ES DESCUENTO DE COASOCIADO
                    if (idprodu.charAt(0) == 'S') {
                        jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        midescuentos = objProducto.Recupera_Porcen_Descuento(idprodu);
                        veces = 1;
                        Recupera_Posicion_Descuento();
                    }

                    // SI ES VENTA DE SOAT
                    if (descrip.equals("SOAT")) {
                        jLabel36.setText("ESTA ES UNA VENTA DE SOAT");
                        jLabel37.setText("Número de Póliza:");
                        jTextField5.setVisible(true);
                        //jTextField41.setVisible(true);

                        String recPoliza = mantProduc.Recupera_Poliza().toString().trim();
                        String[] recuperaPoliza = recPoliza.split("@");

                        jTextField5.setText(recuperaPoliza[0]);
                        jTextField41.setText(recuperaPoliza[1]);

                        comboboxtipodepago.setEnabled(false);

                        /* if (Integer.parseInt(recuperaPoliza[0]) > Integer.parseInt(recuperaPoliza[1])){

                        JOptionPane.showMessageDialog(this, " mayor  ", "Error", JOptionPane.ERROR_MESSAGE);
                        }*/

                    } else {
                        jLabel36.setText(" ");
                    }

                    listadetalle[7] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[8] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[9] = ordenproduto;
                    listadetalle[12] = IdProdRecarga;
                    listadetalle[13] = igvaux;

                    esgratuita1 = objProducto.RetornaEsGratis(idprodu);
                    if (esgratuita1.equals("1")){
                        listadetalle[14] = listproformaDetalle.get(i).getOpGratuita();//montoDscto;
                    }else{
                        listadetalle[14] = "0";
                    }
                    if(Esgratispromo == 1){
                        listadetalle[15] ="1";
                    }else{
                        listadetalle[15] ="0";
                    }
                    listadetalle[16] = Tmarcarpromo;
                    listadetalle[17] = reccupon;
                    listadetalle[18] = certMed;
                    listadetalle[19] = cantbolsas;
                    
                    tablaproforma.addRow(listadetalle);
                    CalculaMontos(igvaux, i);
                    listaProductosVerifica.add(listadetalle);
                    /*
                     * VERIFICO STOCK DE PRODUCTOS
                     */
                    if (!VerificaStock(idprodu, unidad1, fraccion1)) {
                        Laboratorios laborator = new Laboratorios();
                        Producto miproducto = new Producto();
                        laborator.setId_Lab(laboratorio);
                        miproducto.setLaboratorio(laborator);
                        miproducto.setIdProducto(idprodu);
                        miproducto.setDescripcion(descrip);
                        listsinstock.add(miproducto);
                    }
                }
                VerificaPagoFraccionado(idtipopago);
                cantidadproductos = jTable2.getRowCount();
                col = jTable2.getColumnModel().getColumn(0);
                col.setCellRenderer(new ColoredTableCellRenderer());
                col = jTable2.getColumnModel().getColumn(1);
                col.setCellRenderer(new ColoredTableCellRenderer());

                HabilitaBotones(true);
                Recupera_Serie_Numero();
                jTable2.requestFocus();
                jTable2.getSelectionModel().setSelectionInterval(0, 0);
                CalculaMontoIngresado();
                
                if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 2) {
                    jLabel11.setText(lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getDESCRIPCION());
                    jLabel11.setVisible(true);
                }
            }

        } catch (Exception ex) {
            System.out.println("AsignaDatos_Proforma :" + ex.getMessage());
        }

    }

    private void Recupera_Posicion_Descuento() {
        int posi = 0;

        if (ListDescuentos.size() > 0) {
            for (int k = 0; k < ListDescuentos.size(); k++) {
                if (ListDescuentos.get(k).getId_descuento() == midescuentos.getId_Descuento()) {
                    posi = k;
                    break;
                }
            }
            comboboxdescuento.setSelectedIndex(posi + 1);
        }
    }

    public void Muestra_Sin_Stock() {
        if (listsinstock.size() > 0) {
            ProductosSInStock psin = new ProductosSInStock(objetoventana, true, listsinstock);
            psin.setVisible(true);
        }
        listsinstock.removeAll(listsinstock);
    }

    private void ListaProforma() {
        ListadoProformas pe = new ListadoProformas(objetoventana, this);
        pe.show(true);
    }

    private void VerificaCaja() {
        int resul = 0;
        if( (Integer)mantProduc.Verifica_caja_And_Personal(String.valueOf(id_personal), String.valueOf(idcaja))!=1){//MAX_CODIGO
              JOptionPane.showMessageDialog(this,"Error \n EL USUARIO NO COINCIDE CON LA CAJA ACTUAL", "Error", JOptionPane.ERROR_MESSAGE);
              return;//MAX_CODIGO
        }
        if(Double.parseDouble(jTextField8.getText())<=0){// MAX_CODIGO
            JOptionPane.showMessageDialog(this,"Error \n EL MONTO TOTAL NO PUEDE SER IGUAL O MENOR QUE CERO", "Error", JOptionPane.ERROR_MESSAGE);//MAX_CODIGO
        }
        else {//MAX_CODIGO
        try {

            tipventa = lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta();
            boolean apertura = objcaja.CajaAperturada_Numero(idbotica, idcaja, tipventa);

            if (apertura) {

                resul = objClientes.EsDescuento(listatipospagos.get(comboboxtipodepago.getSelectedIndex()).getId_TipoPago());

                if (resul == 1) {
                    int elcodigo = Integer.parseInt(jTextField2.getText().trim());

                    if (objClientes.Recupera_IdCliente_Descuento(idbotica, elcodigo) == elcodigo) {
                        CalculaVenta(resul);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error \n A este Cliente no se le Puede aplicar \n el Descuento al Personal \n Porfavor Seleccione un Cliente Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    CalculaVenta(resul);
                }

            } else {
                JOptionPane.showMessageDialog(this, "ERROR : \n LO SENTIMOS PORFAVOR ES NECESARIO QUE APERTURE SU CAJA", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            System.out.println("VerificaCaja :" + ex.getMessage());
        }
        }

    }

    private void Cliente_Comun() {
        //this.txtcliente.setText(def.getNombre_RazonSocial());
        this.jTextField2.setText(String.valueOf(def.getId_Cliente()));
        this.jComboBox8.addItem(def.getDireccion());
    }
    //
    
    
       
    //

    private void RealizaOpciones(java.awt.event.KeyEvent evt) {
        try {
            if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
                if (jTable2.getRowCount() == 0) {
                    Cliente_Comun();
                }
                    String PROFOR = jTextField1.getText();
                    if (PROFOR.equals("")){
                        PROFOR = "000000";
                    }
                    modif = objProducto.RetornaPersonalModifica(PROFOR);

                    String[] listaMOdif;
                    String cadena1 = modif;
                    listaMOdif = cadena1.split("/");
                    int modificada = Integer.parseInt(listaMOdif[1]);

                    if (modificada == 1 ){
                        JOptionPane.showMessageDialog(this, " ESTA PROFORMA HA SIDO MODIFICADA, NO PUEDE EDITARLA ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                BusquedaProducto();
                if (!jButton4.isEnabled()) {
                    HabilitaBotones(true);
                }
            }

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F6") == 0) {
                NuevaVenta();
                existe=0;
                contarpromo = 0;
            }

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
                CerrarVentana();
            }

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
                if (Verifica_Fecha()) {                   

                    VerificaCaja();
                    txtcliente.requestFocus();
                    
                    
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS LA HORA DEL SERVIDOR ES INCORRECTA \n PORFAVOR COMUNIQUESE CON INFORMATICA \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                    String correo = objlistabotica.ReornaCorreo(idbotica);
                    objmail.sendMail(correo, "ERROR DE FECHA EN EL SERVIDOR", "PROBLEMA ENCONTRADO CON LA FECHA DEL SERVIDOR \n \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + " \n BOTICA FELICIDAD  " + idbotica);
                }
            }
            
            if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
                if (this.jTable2.getRowCount() == 0) {
                    ListaProforma();
                }
            }

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F4") == 0) {
                if (this.jTextField2.getText().length() == 0) {
                    //Cliente_Comun();
                    //this.jTextField3.setEnabled(false);
                    this.txtcliente.requestFocus();
                } else {
                    new FormClientes(this, objetoventana, idbotica).show(true);
                }
            }//else{
                //if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
                
                //existe = 0;
                //contarpromo = 0;

                //}
            //}
            //this.jTextField4.requestFocus();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private boolean Verifica_Fecha() {
        boolean resul = false;
        resul = objlogiccafecha.VerificaFecha(this.idbotica);
        return resul;
    }

    private void CerrarVentana() {
        if (jTable2.getRowCount() > 0) {

            int pe = JOptionPane.showConfirmDialog(null, " Deseas Salir ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (pe == JOptionPane.YES_OPTION) {
                objventa.marcacdo = false;
                objventa.Habilita(true);
                dispose();
            }
        } else {
            objventa.marcacdo = false;
            objventa.Habilita(true);
            dispose();
        }

        objventa.requestFocus();
    }

    private void RecuperaTarjetas() {
        try {

            int w = jComboBox2.getItemCount();
            if (w == 0) {
                listtarj = objlogicapagos.retornaTarjetas(1);
                for (int i = 0; i < listtarj.size(); i++) {
                    String texto = listtarj.get(i).getDescripcion();
                    this.jComboBox2.addItem(texto);
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA RecuperaTarjetas " + ex.getMessage());
        }
    }

    private int VerificaPagosDiversos() {
        String doc = "";
        String val = null;
        String val1 = null;
        canttiposPagos = 0;
        lista_pagos.removeAll(lista_pagos);

        if (this.jCheckBox1.isSelected()) {
            canttiposPagos++;

            if (this.jTextField17.getText().trim().compareTo("") != 0) {
                val = this.jTextField17.getText().trim();
                int id = Integer.parseInt(jCheckBox1.getToolTipText());
                double valor = Double.parseDouble(val);

                if (valor == 0) {
                    //return 3;                    
                    objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, ""); //este es nuevo
                    lista_pagos.add(objeto);                                            //solo para ventas 0.00
                } else {
                    if (valor < 0) {
                        return -1;
                    } else {
                        objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, "");
                        lista_pagos.add(objeto);
                    }
                }

            } else {
                this.jTextField7.requestFocus();
                return 2;
            }
        }
        if (this.jCheckBox4.isSelected()) {
            canttiposPagos++;

            if (this.jTextField19.getText().trim().compareTo("") != 0) {
                if (this.jTextField12.getText().trim().compareTo("") != 0) {

                    val = this.jTextField19.getText().trim();
                    int id = Integer.parseInt(jCheckBox4.getToolTipText());
                    double valor = Double.parseDouble(val);

                    if (valor == 0) {
                        return 3;
                    } else {
                        if (valor < 0) {
                            return -1;
                        } else {
                            doc = this.jTextField12.getText().trim();
                            objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, "");
                            lista_pagos.add(objeto);
                        }

                    }
                } else {
                    return 5;
                }

            } else {
                this.jTextField9.requestFocus();
                return 2;
            }

        }
        if (this.jCheckBox2.isSelected()) {
            canttiposPagos++;

            if (this.jTextField10.getText().trim().compareTo("") != 0) {
                if (this.jTextField13.getText().trim().compareTo("") != 0) {
                    val = this.jTextField10.getText().trim();
                    int id = Integer.parseInt(jCheckBox2.getToolTipText());
                    double valor = Double.parseDouble(val);

                    if (valor == 0) {
                        return 3;
                    } else {
                        if (valor < 0) {
                            return -1;
                        } else {
                            doc = this.jTextField13.getText().trim();
                            objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, jComboBox5.getSelectedItem().toString());
                            lista_pagos.add(objeto);
                        }
                    }
                } else {
                    this.jTextField13.requestFocus();
                    return 5;

                }

            } else {
                this.jTextField10.requestFocus();
                return 2;
            }

        }
        if (this.jCheckBox3.isSelected()) {
            canttiposPagos++;

            if (this.jTextField18.getText().trim().compareTo("") != 0) {
                val = this.jTextField18.getText().trim();
                val1 = this.jTextField6.getText().trim();

                double valor = Double.parseDouble(val);

                if (valor == 0) {
                    return 3;
                } else if (val1.equals("")) {
                    this.jTextField6.requestFocus();
                    return 8;
                } else {
                    if (valor < 0) {
                        return -1;
                    } else {
                        doc = this.jTextField6.getText().trim();
                        int idpa = listtarj.get(this.jComboBox2.getSelectedIndex()).getId_TipoPago();
                        objeto = new Ventas_Tipo_Pago(idbotica, idpa, valor, fecha, doc, this.jComboBox2.getSelectedItem().toString());
                        lista_pagos.add(objeto);
                    }
                }

            } else {
                this.jTextField18.requestFocus();
                canttiposPagos = 0;
                return 2;
            }

        }

        if (this.jCheckBox5.isSelected()) {
            canttiposPagos++;

            if (this.jTextField39.getText().trim().compareTo("") != 0) {
                if (this.jTextField38.getText().trim().compareTo("") != 0) {
                    val = this.jTextField39.getText().trim();
                    int id = Integer.parseInt(jCheckBox5.getToolTipText());
                    double valor = Double.parseDouble(val);

                    if (valor == 0) {
                        return 3;
                    } else {
                        if (valor < 0) {
                            return -1;
                        } else {
                            doc = this.jTextField38.getText().trim();
                            objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, jComboBox7.getSelectedItem().toString());
                            lista_pagos.add(objeto);
                        }
                    }
                } else {
                    this.jTextField38.requestFocus();
                    return 5;
                }

            } else {
                this.jTextField39.requestFocus();
                return 2;
            }


        }

        if (canttiposPagos > 2) {
            lista_pagos.removeAll(lista_pagos);
            canttiposPagos = 0;
            return 1;
        } else if (canttiposPagos == 0) {
            return 4;
        } else if (canttiposPagos < 2 && listatipospagos.get(comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 15) {
            return 7;
        } else {

            Ventas_Tipo_Pago aux;
            Ventas_Tipo_Pago aux_1;

            for (int i = 0; i < lista_pagos.size(); i++) {
                for (int k = i + 1; k < lista_pagos.size(); k++) {

                    if (lista_pagos.get(k).getMonto() > lista_pagos.get(i).getMonto()) {

                        aux = lista_pagos.get(i);
                        aux_1 = lista_pagos.get(k);

                        lista_pagos.set(i, aux_1);
                        lista_pagos.set(k, aux);

                        i = lista_pagos.size();
                        break;

                    }
                }
            }
            return 0;
        }  //OK GUARDA VENTA

    }

    private void DeshabilitaPagosDiversos(boolean valor) {
        if (valor == false) {
            this.jTextField17.setText("");
            this.jTextField19.setText("");
            this.jTextField18.setText("");
            this.jTextField10.setText("");
            this.jTextField11.setText("");
            this.jTextField12.setText("");
            this.jTextField13.setText("");
        }
    }

    private void Guarda_Venta(int resultado) {

        if (resultado == 0) {
            int posicion = 0;
            Object objet = this.comboboxtipodocumento.getSelectedItem();
            tipventa = lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta();
            String resulta = objfactura.Verifica_Datos_Factura(tipventa, this.txtruc.getText().trim(), this.txtcliente.getText().trim(), jComboBox8.getSelectedItem().toString().trim());

            if (resulta != null) {

                JOptionPane.showMessageDialog(this, resulta.substring(1, resulta.length()), "Error", JOptionPane.ERROR_MESSAGE);

                if (Integer.parseInt(resulta.substring(0, 1)) == 1) {
                    this.txtruc.requestFocus();
                } else if (Integer.parseInt(resulta.substring(0, 1)) == 2) {
                    this.txtcliente.requestFocus();
                } else if (Integer.parseInt(resulta.substring(0, 1)) == 3) {
                    this.jComboBox8.requestFocus();
                }
            } else {

                boolean flag = objfactura.Verifica_Datos_Boleta(tipventa, total, this.txtdni.getText().trim(), posicion, txtcliente.getText().trim(), jComboBox8.getSelectedItem());

                if (!flag) {
                    JOptionPane.showMessageDialog(this, "PORFAVOR PARA ESTE MONTO DE VENTA SE REQUIERE INGRESAR : \n NOMBRE DEL CLIENTE  \n NUMERO DE DNI  \n DIRECCION DEL CLIENTE ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.txtruc.requestFocus();
                    txtdni.setEnabled(true);
                } else {
                    
                    if (tipventa == 7){
                        String msg1= "<html><h3>LA SIGUIENTE VENTA ES UNA BOLETA MANUAL, POR FAVOR VERIFICAR LA :<hr></html>\n";
                        String msg2= "<html><h1> <font color='red'>SERIE : " + jTextField31.getText() + " NUMERO : " + jTextField35.getText() + "</font></h2><hr></html>\n";
                        String msg3= "<html><h3>Y QUE EL MONTO <h1><font color='red'>"+ jTextField45.getText() +" </font></h2> SEA CORRECTO CON EL DOCUMENTO FISICO<hr></html>\n";
                        // JOptionPane.showMessageDialog(this, " LA SIGUIENTE VENTA ES UNA BOLETA MANUAL, POR FAVOR VERIFICAR LA :\n SERIE : "+ this.jTextField31.getText() + " NUMERO : "+ this.jTextField35.getText() + "\n Y QUE EL MONTO SEA CORRECTO ", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
                        
                        int reply = JOptionPane.showOptionDialog(null,msg1 + msg2 + msg3 ,"NORTFARMA",
			           JOptionPane.YES_NO_OPTION,//Botones que apareces para seleccionar
			           JOptionPane.QUESTION_MESSAGE,//Icono por defecto
			           null,    // null para icono por defecto, o un icono personalizado
			           new Object[] { "ACEPTAR", "CANCELAR" },   // botones presonalizados botones por defecto en este caso YES, NO
			           "CANCELAR");//Botón selecconado por defecto

                        if (reply == JOptionPane.YES_OPTION) {
                            
                        
                        if (jTextField1.getText().trim().compareTo("") == 0) {
                            int sumaEspsicotropicoNC=0;
                            for (int ixNC = 0; ixNC < jTable2.getRowCount(); ixNC++) {
                                String compara_idprodNC = String.valueOf(this.jTable2.getValueAt(ixNC, 1));
                                int espsicotropicoNC = objProducto.G_EsPsicotropico(compara_idprodNC,1);
                                sumaEspsicotropicoNC = sumaEspsicotropicoNC + espsicotropicoNC;
                            }
                            int ValidaMedicoNC = objProducto.G_EsPsicotropico("",2);
                            new Medicos(objetoventana).setVisible(true);
                            if (Medicos.getId_Medico() != null) {
                                
                                if(ValidaMedicoNC == 0){
                                    idmedico = Medicos.getId_Medico();
                                    colegiatura = Medicos.getColegiatura();
                                    Guarda_MiVenta(objet);
                                }else{
                                
                                if (sumaEspsicotropicoNC > 0 ){
                                    if (!Medicos.getId_Medico().equals("00000") && !txtcliente.getText().trim().toUpperCase().equals("CLIENTE COMUN")){
                                        if (! txtcliente.getText().trim().equals("") ){
                                            idmedico = Medicos.getId_Medico();
                                            colegiatura = Medicos.getColegiatura();
                                            Guarda_MiVenta(objet);
                                        }else{
                                           JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                        idmedico = Medicos.getId_Medico();
                                        colegiatura = Medicos.getColegiatura();
                                        Guarda_MiVenta(objet);
                                }
                                }
                            } else {
                                Guarda_MiVenta(objet);
                            }
                        
                        }else{   //aqui
                            
                            int sumaEspsicotropicoNC1=0;
                            for (int ixNC1 = 0; ixNC1 < jTable2.getRowCount(); ixNC1++) {
                                String compara_idprodNC1 = String.valueOf(this.jTable2.getValueAt(ixNC1, 1));
                                int espsicotropicoNC1 = objProducto.G_EsPsicotropico(compara_idprodNC1,1);
                                sumaEspsicotropicoNC1 = sumaEspsicotropicoNC1 + espsicotropicoNC1;
                            }
                            int ValidaMedicoNC1 = objProducto.G_EsPsicotropico("",2);
                            new Medicos(objetoventana).setVisible(true);
                            if (Medicos.getId_Medico() != null) {
                                
                                if(ValidaMedicoNC1 == 0){
                                    idmedico = Medicos.getId_Medico();
                                    colegiatura = Medicos.getColegiatura();
                                    Guarda_MiVenta(objet);
                                }else{
                                
                                if (sumaEspsicotropicoNC1 > 0 ){
                                    if (!Medicos.getId_Medico().equals("00000") && !txtcliente.getText().trim().toUpperCase().equals("CLIENTE COMUN")){
                                        if (! txtcliente.getText().trim().equals("") ){
                                            idmedico = Medicos.getId_Medico();
                                            colegiatura = Medicos.getColegiatura();
                                            Guarda_MiVenta(objet);
                                        }else{
                                           JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                        idmedico = Medicos.getId_Medico();
                                        colegiatura = Medicos.getColegiatura();
                                        Guarda_MiVenta(objet);
                                }
                                }
                            } else {
                                Guarda_MiVenta(objet);
                            }
                        }
                        }
                        
                        
                    }else{
                    
                        if (jTextField1.getText().trim().compareTo("") == 0) {
                            int sumaEspsico=0;
                            for (int ix = 0; ix < jTable2.getRowCount(); ix++) {
                                String compara_idprod = String.valueOf(this.jTable2.getValueAt(ix, 1));
                                int espsicotro = objProducto.G_EsPsicotropico(compara_idprod,1);
                                sumaEspsico = sumaEspsico + espsicotro;
                            }
                            int ValidaMedico = objProducto.G_EsPsicotropico("",2);
                            new Medicos(objetoventana).setVisible(true);
                            
                            if (Medicos.getId_Medico() != null) { 
                                
                                if(ValidaMedico == 0){
                                    idmedico = Medicos.getId_Medico();
                                    colegiatura = Medicos.getColegiatura();
                                    Guarda_MiVenta(objet);
                                }else{
                                if (sumaEspsico > 0 ){
                                    if (!Medicos.getId_Medico().equals("00000") && !txtcliente.getText().trim().toUpperCase().equals("CLIENTE COMUN") ){
                                        if (! txtcliente.getText().trim().equals("") ){
                                            idmedico = Medicos.getId_Medico();
                                            colegiatura = Medicos.getColegiatura();
                                            Guarda_MiVenta(objet);
                                        }else{
                                           JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                        idmedico = Medicos.getId_Medico();
                                        colegiatura = Medicos.getColegiatura();
                                        Guarda_MiVenta(objet);
                                }
                                }
                            } /*else {
                                Guarda_MiVenta(objet);
                            }*/
                            
                            
                        }else {
                            
                            int sumaEspsico1=0;
                            for (int ix = 0; ix < jTable2.getRowCount(); ix++) {
                                String compara_idprod1 = String.valueOf(this.jTable2.getValueAt(ix, 1));
                                int espsicotro1 = objProducto.G_EsPsicotropico(compara_idprod1,1);
                                sumaEspsico1 = sumaEspsico1 + espsicotro1;
                            }
                            int ValidaMedico = objProducto.G_EsPsicotropico("",2);
                            new Medicos(objetoventana).setVisible(true);
                            
                            if (Medicos.getId_Medico() != null) { 
                                
                                if(ValidaMedico == 0){
                                    idmedico = Medicos.getId_Medico();
                                    colegiatura = Medicos.getColegiatura();
                                    Guarda_MiVenta(objet);
                                }else{
                                if (sumaEspsico1 > 0 ){
                                    if (!Medicos.getId_Medico().equals("00000") && !txtcliente.getText().trim().toUpperCase().equals("CLIENTE COMUN") ){
                                        if (! txtcliente.getText().trim().equals("") ){
                                            idmedico = Medicos.getId_Medico();
                                            colegiatura = Medicos.getColegiatura();
                                            Guarda_MiVenta(objet);
                                        }else{
                                           JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                        idmedico = Medicos.getId_Medico();
                                        colegiatura = Medicos.getColegiatura();
                                        Guarda_MiVenta(objet);
                                }
                                }
                            } //else {
                                //Guarda_MiVenta(objet);
                            //}
                                //Guarda_MiVenta(objet);
                        }
                    }
                }
            }

        }
        if (resultado == 1) {
            canttiposPagos = 0;
            JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN HASTA DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resultado == 2) {
                canttiposPagos = 0;
                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n DEBE DE INGRESAR UN MONTO PARA CADA TIPO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (resultado == 5) {
                    canttiposPagos = 0;
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n FALTA INGRESAR DATOS PARA SU TIPO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    if (resultado == 3) {
                        canttiposPagos = 0;
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS \n NO SE PUEDE INGRESAR UNA CANTIDAD 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        if (resultado == 4) {
                            canttiposPagos = 0;
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS DEBE DE SELECCIONAR UN TIPO DE PAGO \n PARA REALIZAR LA VENTA  ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }//CIERRE DEL ELSE

        }//CIERRE DEL ELSE
    }

    public void Guarda_MiVenta(Object objet) {
        double monto = 0;
        String dato = "";
        double compara = 0;
        String tipoPago = "";
        String fechadesde = "";
        String fechahasta = "";
        String compararfecha = "";
        String tipoTarjeta = "";

        String tipoDocValidar = "";
        int canti1 = 0;
        int canti2 = 0;
        String cantidad1 = "";
        String cantidad2 = "";
        String cantidad3 = "";
        double m1=0.0;
        double m2=0.0;
        String totalpago="";

        /*if (!this.jTextField17.getText().trim().equals("")){
        m1 = Double.parseDouble(this.jTextField17.getText().trim());
        m2 = Double.parseDouble(this.jTextField44.getText().trim())*-1;
        if (m1 > 0 && m2  > 0 ) {
            if (m1==m2){*/
                totalpago = this.jTextField17.getText().trim();
/*            }
           // m1 = m1 - m2;
            totalpago = this.jTextField45.getText();
        }else{
            totalpago = this.jTextField17.getText();
        }
        }else{
            totalpago = this.jTextField17.getText();
        }*/
        String valueCompara = "";
        MuestraVentana obj = new MuestraVentana(objventa.getIdbotica(), idcaja, tipventa, totalpago, objet.toString().trim());

        monto = Double.parseDouble(this.jTextField21.getText().trim());
        tipoPago = String.valueOf(this.comboboxtipodepago.getSelectedItem());
        tipoTarjeta = String.valueOf(this.jComboBox2.getSelectedItem());

        String validar = mantProduc.Recupera_Promo_CodigoPrecio().toString().trim();
        if (!validar.equals("")){
        String[] recuperacadena = validar.split("@");
        compara = Double.parseDouble(recuperacadena[1]); //monto a comparar
        fechadesde = recuperacadena[2]; //fecha desde
        fechahasta = recuperacadena[3]; //fecha hasta
        compararfecha = recuperacadena[4]; //validofecha
        }
        String validartipoPago = tipoPago.toString().trim();
        String[] recuperatipopago = validartipoPago.split(" ");
        tipoPago = recuperatipopago[0]; //tipo pago

        /*if (tipoTarjeta.equals("Tarjeta VISA UNICA") && tipoPago.equals("20") && monto >= compara && compararfecha.equals("1")) {

            BusquedaProductoPromocion();

            String cant = String.valueOf(jTable2.getRowCount());  //cuenta registros del jtable2
            DefaultTableModel tm = (DefaultTableModel) jTable2.getModel();
            //aca capturo el primer dato de la celda seleccionada
            dato = String.valueOf(tm.getValueAt(jTable2.getSelectedRow(), 8));

            valueCompara = String.valueOf(dato.charAt(0));

            if (cant.equals("7") && valueCompara.equals("-")) {
                JOptionPane.showMessageDialog(this, "EL monto no debe ser negativo en la siguiente boleta", "Error", JOptionPane.ERROR_MESSAGE);

            } else {

                obj.AbrirVentana();
                if (obj.isBanedra()) {
                    serie = obj.getSerie();
                    numero = obj.getNumero();
                    ventaDelivery = obj.getVentaDelivery();
                    montoPagoT = obj.getmontoPago();
                    vueltoT = obj.getvuelto();

                    GuardaVenta(serie, numero,montoPagoT,vueltoT);
                }
            }
        } else {*/

            double totcompara;
            for (int i = 0; i < this.jTable2.getRowCount(); i++) {
                String idproducto = String.valueOf(this.jTable2.getValueAt(i, 1));
                totcompara = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                //jTextField40.setText(Double.toString(totcompara));
            }

            tipoDocValidar = String.valueOf(this.comboboxtipodocumento.getSelectedItem());
            //String cantJTable = String.valueOf(jTable2.getRowCount());
            int cantJTable1 = jTable2.getRowCount();
            cantidad = objguardaventa.RetornaCantidadCompara(tipoDocValidar);

            String[] lista;
            String cadena = cantidad;
            lista = cadena.split("/");

            int canti = Integer.parseInt(lista[0]);      // recupera el numero de items por tipo de doc
            String recTipDoc = String.valueOf(lista[1]); // recupera el id de tipo de doc

            
            obj.AbrirVentana();

            if (obj.isBanedra()) {
                serie = obj.getSerie();
                numero = obj.getNumero();
                ventaDelivery = obj.getVentaDelivery();
                montoPagoT = obj.getmontoPago();
                vueltoT = obj.getvuelto();
                if (montoPagoT.equals("")|| montoPagoT.trim().isEmpty()){
                    montoPagoT="0.00";
                }
                
                GuardaVenta(serie, numero,montoPagoT,vueltoT);
            }
            // }
        //}
    }

    private void CalculaVenta(int resul) {

        String doc = "";
        int resultado = 0;
        double monto = 0;
        boolean escredito = false;
        boolean error = true;
        lista_pagos.removeAll(lista_pagos);

        if (esCredito(idtipopago)) {
            escredito = true;
            if (Double.valueOf(jTextField21.getText().toString()) > objClientes.RecuperaSaldo(Integer.parseInt(jTextField2.getText().toString().trim()), idbotica)) {
                JOptionPane.showMessageDialog(this, "EL CLIENTE NO CUENTA SON SALDO PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                total = Double.parseDouble(jTextField45.getText().trim());
                idtipopago = listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago();
                objeto = new Ventas_Tipo_Pago(idbotica, idtipopago, total, fecha, doc, "");
                lista_pagos.add(objeto);
                Guarda_Venta(0);
            }
        } else {

            if (listatipospagos.get(comboboxtipodepago.getSelectedIndex()).getEsAbono() == 1) {//SI ES ABONO

                if (jTextField38.getText().trim().compareTo("") != 0) {
                    if (jTextField39.getText().trim().compareTo("") != 0) {
                        double mitottal = Double.parseDouble(jTextField39.getText().trim());

                        BigDecimal bd1 = new BigDecimal(mitottal);
                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        mitottal = bd1.doubleValue();                        

                        if (mitottal >= total) {
                            doc = jTextField38.getText().trim();
                            objeto = new Ventas_Tipo_Pago(idbotica, idtipopago, total, fecha, doc, jComboBox7.getSelectedItem().toString());
                            lista_pagos.add(objeto);
                            jTextField11.setText("");
                            Guarda_Venta(0);
                            jTextField38.setText("");
                            jTextField39.setText("");

                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS \n EL IMPORTE DEL ABONO ES MENOR QUE EL MONTO DE VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN IMPORTE VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN NUMERO DE OPERACION VALIDO ", "Error", JOptionPane.ERROR_MESSAGE);
                }


            } else {

                if (resul == 1) {
                    idtipopago = listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago();
                    objeto = new Ventas_Tipo_Pago(idbotica, idtipopago, total, fecha, doc, "");
                    lista_pagos.add(objeto);
                    Guarda_Venta(0);
                } else {

                    Calcula_Pagos();
                    monto = Double.parseDouble(this.jTextField11.getText().trim());

                    BigDecimal bd1 = new BigDecimal(monto);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    monto = bd1.doubleValue();

                    BigDecimal bd2 = new BigDecimal(total);
                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    total = bd2.doubleValue();

                    Double to = Double.parseDouble(jTextField45.getText().trim());
                    BigDecimal bd3 = new BigDecimal(to);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    Double to1 = bd3.doubleValue();


                    //if (monto < total && !escredito) {
                    if (monto < to1 && !escredito) {
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS \n EL PAGO DEL CLIENTE ES MENOR QUE EL MONTO DE LA VENTA \n PORFAVOR REVISAR EL PAGO FRACCIONADO ", "Error", JOptionPane.ERROR_MESSAGE);
                        error = false;
                    }
                    if (monto > to1 && !escredito) {
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS \n EL PAGO DEL CLIENTE ES MAYOR QUE EL MONTO DE LA VENTA \n PORFAVOR REVISAR EL PAGO FRACCIONADO ", "Error", JOptionPane.ERROR_MESSAGE);
                        error = false;
                    } else {
                        resultado = VerificaPagosDiversos();

                        if (resultado == 7) {
                            if (error) {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n PARA PAGOS DIVERSOS ES NECESARIO SELECCIONAR DOS PAGOS FRACCIONADOS ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (resultado == 8) {
                            if (error) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE LOTE - REF \n DEL VOUCHER DE LA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            if (resultado == 5) {
                                if (error) {
                                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN NUMERO DE DOCUMENTO ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                if (error) {
                                    if (resultado == -1) {
                                        JOptionPane.showMessageDialog(this, "LO SENTIMOS  NO PUEDE INGRESAR UNA CANTIDAD NEGATIVA EN UN PAGO  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        monto = Double.parseDouble(this.jTextField11.getText().trim());
                                        Guarda_Venta(resultado);
                                    }
                                }
                            }
                        }
                    }
                }//cierre de else
            }
        }
    }

    private void Calcula_Pagos() {
        double monto = 0;

        if (jCheckBox1.isSelected()) {
            if (jTextField17.getText().trim().compareTo("") != 0) {
                monto = Double.parseDouble(jTextField17.getText().trim());
            }
        }

        if (jCheckBox4.isSelected()) {
            if (jTextField19.getText().trim().compareTo("") != 0) {
                monto += Double.parseDouble(jTextField19.getText().trim());
            }
        }

        if (jCheckBox2.isSelected()) {
            if (jTextField10.getText().trim().compareTo("") != 0) {
                monto += Double.parseDouble(jTextField10.getText().trim());
            }
        }

        if (jCheckBox3.isSelected()) {
            if (jTextField18.getText().trim().compareTo("") != 0) {
                monto += Double.parseDouble(jTextField18.getText().trim());
            }
        }

        if (jCheckBox5.isSelected()) {
            if (jTextField39.getText().trim().compareTo("") != 0) {
                monto += Double.parseDouble(jTextField39.getText().trim());
            }
        }

        BigDecimal bd1 = new BigDecimal(monto);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        monto = bd1.doubleValue();

        this.jTextField11.setText(String.valueOf(monto));

    }

    private void CapturaIGV() {
        IGV = mantProduc.Captura_IGV();
    }

    public void AsignaDatosCliente() {
        try {

            this.jTextField2.setText(objcliente.getCodigoCliente());
            String LeeRUC = objcliente.getRUC_DNI();
            String LeeDNI = objcliente.getDNI();

           /*if (lis_tipo_venta.get(jComboBox3.getSelectedIndex()).getId_Tipo_Venta() == 11) {

                if (LeeRUC.equals("") && !LeeDNI.equals("")){
                    this.jtextRUCDNI.setText(objcliente.getDNI());
                }else{
                    this.jtextRUCDNI.setText(objcliente.getDNI());
                }
            }else{
                if (LeeRUC.equals("") && !LeeDNI.equals("")){
                this.jtextRUCDNI.setText(objcliente.getDNI());
                }else{
                    this.jtextRUCDNI.setText(objcliente.getRUC_DNI());
                }
            }*/
            txtdni.setDocument(new LimitadorLetras(txtdni, 8));
            txtruc.setDocument(new LimitadorLetras(txtruc, 11));
            
            this.txtruc.setText(objcliente.getRUC_DNI());
            this.txtdni.setText(objcliente.getDNI());
            this.txtcliente.setText(objcliente.getNombre_RazonSocial());
            //this.jTextField42.setText(objcliente());
            idcliente = Integer.parseInt(objcliente.getCodigoCliente());
            Recupera_Direcciones();
            this.comboboxtipodocumento.requestFocus();

        } catch (Exception ex) {
            System.out.println("AsignaDatosCliente :" + ex.toString());
        }
    }

    private void Recupera_Direcciones() {
        jComboBox8.removeAllItems();
        List<Clientes> midirecciones = objClientes.Lista_Direcciones(idbotica, Integer.parseInt(objcliente.getCodigoCliente()), objcliente.getEmpresa(),objcliente.getDireccion());

        for (int i = 0; i < midirecciones.size(); i++) {
            jComboBox8.addItem(midirecciones.get(i).getDireccion());
        }
    }

    private void CalculaMontoIngresado() {
        String mens = null;
        double calc1 = 0.0;

        try {

            if (this.jTextField10.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField10.getText().trim();
                calc1 += Double.parseDouble(tota);
            }
            if (this.jTextField17.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField17.getText().trim();
                calc1 += Double.parseDouble(tota);
            }
            if (this.jTextField18.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField18.getText().trim();
                calc1 += Double.parseDouble(tota);

            }
            if (this.jTextField19.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField19.getText().trim();
                calc1 += Double.parseDouble(tota);
            }

            if (this.jTextField39.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField39.getText().trim();
                calc1 += Double.parseDouble(tota);
            }

            idtipopago = listatipospagos.get(comboboxtipodepago.getSelectedIndex()).getId_TipoPago();

            if (esCredito(idtipopago)) {
                jLabel11.setText("LA SIGUIENTE VENTA ES UNA VENTA A CREDITO");
                this.jTextField17.setText("");
            } else {
                jLabel11.setText(" ");
            }


        } catch (Exception ex) {
            System.out.println("");
        }
    }

    public boolean esCredito(int idtipopago) {
        boolean escredito = false;
        escredito = objguardaventa.EsCredito(idtipopago);
        return escredito;
    }

    private void Habilita_Cash(boolean valor) {
        this.jCheckBox2.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox1.setEnabled(!valor);
        this.jCheckBox1.setSelected(!valor);

        this.jTextField17.setEnabled(!valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField13.setEnabled(valor);
        this.jTextField12.setEnabled(valor);

        this.jTextField10.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        this.jTextField13.setText("");
        this.jComboBox5.setEnabled(valor);        
        Habilita_Abono(valor);
        this.jTextField39.setText("");

    }

    private void Habilta_ESCredito(boolean valor) {
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox2.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox4.setSelected(valor);

        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);

        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField12.setEnabled(valor);

        this.jTextField10.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField17.setText("");
        this.jTextField13.setText("");
        this.jTextField12.setText("");
        jLabel11.setVisible(true);
        this.jLabel11.setText(" LA SIGUIENTE ES UNA VENTA A CREDITO ");
        this.jComboBox5.setEnabled(false);
        Habilita_Abono(valor);
        this.jTextField39.setText("");

    }

    private void Habilita_ESDecPersonal(boolean valor) {

        this.jCheckBox1.setSelected(valor);
        this.jCheckBox2.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox4.setSelected(valor);

        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);

        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField12.setEnabled(valor);

        this.jTextField10.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField17.setText("");
        this.jTextField13.setText("");
        this.jTextField12.setText("");
        jLabel11.setVisible(!valor);
        this.jLabel11.setText(" LA SIGUIENTE ES UNA VENTA A DESCUENTO A PERSONAL");
        this.jComboBox5.setEnabled(valor);
        Habilita_Abono(valor);
        this.jTextField39.setText("");
    }

    private void Habilita_EsNotaCredito(boolean valor) {
        jCheckBox2.setSelected(valor);
        jCheckBox3.setSelected(valor);
        jCheckBox1.setSelected(valor);
        jCheckBox4.setSelected(!valor);

        jCheckBox1.setEnabled(valor);
        jCheckBox2.setEnabled(valor);
        jCheckBox3.setEnabled(valor);
        jCheckBox4.setEnabled(!valor);

        jTextField17.setEnabled(valor);
        jTextField17.setText("");
        jTextField10.setEnabled(valor);
        jTextField13.setEnabled(valor);
        jTextField18.setEnabled(valor);
        jTextField12.setEditable(!valor);
        jTextField12.setEnabled(!valor);
        jComboBox5.setEnabled(valor);
        jTextField12.requestFocus();
        Habilita_Abono(valor);
        this.jTextField39.setText("");
        this.jTextField10.setText("");
    }

    private void Habilita_EsCheque(boolean valor) {
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(!valor);
        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField17.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        //this.jTextField10.setText("");
        this.jTextField13.setText("");
        this.jComboBox5.setEnabled(!valor);
        this.jTextField10.setEnabled(!valor);
        this.jTextField13.setEnabled(!valor);
        this.jTextField13.setEnabled(!valor);
        RecuperaBancos();
        Habilita_Abono(valor);
        this.jTextField39.setText("");
        this.jTextField13.requestFocus();
        jCheckBox2.setSelected(true);
        Habilita_cheque();
        this.jTextField39.setText("");
    }

    private void Habilita_cheque(){
        Double restante = total;
        BigDecimal bd2 = new BigDecimal(restante);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        this.jTextField10.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void Habilita_ESAbono(boolean valor) {
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField17.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        this.jTextField10.setEnabled(valor);
        this.jTextField13.setEnabled(valor);
        //this.jTextField39.setText("");
        this.jTextField38.setText("");
        jTextField10.setText("");
        jTextField13.setText("");
        jComboBox5.setEnabled(valor);
        jCheckBox2.setSelected(valor);

    }

    private void Habilita_PagosDiversos(boolean valor) {
        jCheckBox1.setSelected(valor);
        jCheckBox2.setSelected(valor);
        jCheckBox3.setSelected(valor);
        jCheckBox4.setSelected(valor);
        jCheckBox5.setSelected(valor);
        jCheckBox1.setEnabled(!valor);
        jCheckBox2.setEnabled(!valor);
        jCheckBox3.setEnabled(!valor);
        jCheckBox4.setEnabled(!valor);
        jTextField17.setEnabled(valor);
        jTextField17.setText("");
        jTextField10.setEnabled(valor);
        jTextField18.setEnabled(valor);
        jTextField13.setEnabled(valor);
        jCheckBox3.setEnabled(!valor);
        jComboBox5.setEnabled(valor);
        jTextField11.setText("");
        Habilita_Abono(!valor);
        jComboBox7.setEnabled(valor);
        jCheckBox5.setSelected(valor);
        this.jTextField39.setText("");
        this.jTextField39.setEnabled(false);
        this.jTextField38.setEnabled(false);

    }

    private void Deshabilta_Tarjetas(boolean valor) {

        this.jCheckBox2.setSelected(valor);
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);

        this.jCheckBox3.setEnabled(!valor);
        this.jTextField17.setText("");
        this.jTextField10.setText("");
        this.jTextField19.setText("");
        this.jTextField18.setText("");
        this.jTextField11.setText("");
        this.jTextField12.setText("");
        this.jTextField6.setText("");
        this.jTextField13.setText("");
        this.jTextField17.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField11.setEnabled(valor);

        this.txtcliente.setEditable(!valor);
        this.txtcliente.requestFocus();
        this.jTextField18.setText(String.valueOf(total));
        this.jTextField11.setText(String.valueOf(total));
        this.jComboBox5.setEnabled(!valor);

        this.jTextField6.setEnabled(!valor);
        this.jComboBox2.setEnabled(!valor);
        this.jComboBox5.setEnabled(valor);
        this.jTextField18.setEnabled(!valor);
        this.jComboBox2.setEnabled(!valor);
        this.jTextField18.requestFocus();
        RecuperaTarjetas();
        Habilita_Abono(valor);
        this.jTextField39.setText("");

    }

    private void VerificaPagoFraccionado(int ind) {

        int idtipopago1 = ind;
        BigDecimal bd1 = new BigDecimal(total);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd1.doubleValue();

        Double nuevototal1 = Double.parseDouble(this.jTextField45.getText());
        BigDecimal bdNT1 = new BigDecimal(nuevototal1);
        bdNT1 = bdNT1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        //nuevototal = bdNT.doubleValue();


        if (idtipopago1 == 1)//SI ES CASH
        {
            Habilita_Cash(false);
            this.jTextField17.setText(bdNT1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            this.jTextField11.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        } else {
            if (esCredito(idtipopago1))//SI ES CREDITO
            {
                Habilta_ESCredito(false);
            } else {
                if (idtipopago1 == 9)//SI ES DESCUENTO AL PERSONAL
                {
                    Habilita_ESDecPersonal(false);
                } else {
                    if (idtipopago1 == 7)//SI ES NOTA DE CREDITO
                    {
                        Habilita_EsNotaCredito(false);
                    } else {
                        if (idtipopago1 == 6)// SI ES CHEQUE
                        {
                            Habilita_EsCheque(false);
                            this.jTextField10.requestFocus();
                        } else {
                            if (idtipopago1 == 23)//SI ES ABONO
                            {
                                Habilita_Abono(true);
                                Habilita_ESAbono(false);
                            } else {
                                if (idtipopago1 == 15)//SI ES PAGOS DIVERSOS
                                {
                                    Habilita_PagosDiversos(false);
                                } else {
                                    if (idtipopago1 == 20 || idtipopago1 == 16 || idtipopago1 == 17 || idtipopago1 == 18 || idtipopago1 == 5 || idtipopago1 == 10 || idtipopago1 == 3) {
                                        Deshabilta_Tarjetas(false);
                                    } else {
                                        this.jCheckBox1.setSelected(false);
                                        this.jCheckBox2.setSelected(false);
                                        this.jCheckBox3.setSelected(false);
                                        this.jCheckBox4.setSelected(false);

                                        this.jCheckBox1.setEnabled(false);
                                        this.jCheckBox2.setEnabled(false);
                                        this.jCheckBox3.setEnabled(false);
                                        this.jCheckBox4.setEnabled(false);

                                        this.jTextField10.setText("");
                                        this.jTextField18.setText("");
                                        this.jTextField19.setText("");
                                        this.jTextField17.setText("");
                                        this.jTextField13.setText("");
                                        this.jTextField12.setText("");

                                        this.jTextField17.setEnabled(false);
                                        this.jTextField18.setEnabled(false);
                                        this.jTextField19.setEnabled(false);
                                        this.jTextField10.setEnabled(false);
                                        this.jTextField18.setEnabled(false);
                                        this.jTextField12.setEnabled(false);
                                    }
                                }
                            }//cierre else
                        }
                    }
                }
            }
        }
    }

    private void RecuperaBancos() {

        if (this.jComboBox5.getItemCount() == 0) {
            List<String> listaBancos = this.objguardaventa.Lista_Bancos();
            this.jComboBox5.setEnabled(true);
            for (int i = 0; i < listaBancos.size(); i++) {
                this.jComboBox5.addItem(listaBancos.get(i));
            }
        }
    }

    private void RecuperaBancos_1() {

        if (this.jComboBox7.getItemCount() == 0) {
            List<String> listaBancos = this.objguardaventa.Lista_Bancos();
            this.jComboBox7.setEnabled(true);
            for (int i = 0; i < listaBancos.size(); i++) {
                this.jComboBox7.addItem(listaBancos.get(i));
            }
        }
    }

    private void LimpiatTabla() {
        int cant = jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproforma.removeRow(i);
            }
        }
    }

    /****************************************************************
     * METODO PARA VOLVER A REORDENAR LAS CANTIDADES DE LOS PRODUCTOS
     * **************************************************************/
    private void ReordenaTabla(int ultposi) {
        try {

            for (int i = ultposi; i < tablaproforma.getRowCount(); i++) {
                tablaproforma.setValueAt(ultposi + 1, ultposi, 0);
                ultposi++;
            }

        } catch (Exception EX) {
            JOptionPane.showMessageDialog(this, "ERROR AL REORDENAR TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    /****************************************************************
     * METODO PARA ELIMINAR UN PRODUCTO DE LA PROFORMA
     * **************************************************************/
    private void EliminaProducto(int fila) {
        int filas = tablaproforma.getRowCount();
        double auxsubtotal = 0;

        if (filas > 0) {

            try {

                if (fila >= 0) {

                    Confirmar pe = new Confirmar(objetoventana, "<html>Deseas Eliminar este Producto :" + tablaproforma.getValueAt(fila, 2) + "</html>");
                    pe.show(true);

                    if (pe.getConfirmar() == 1) {
                        String idproducto= String.valueOf(tablaproforma.getValueAt(fila, 1));//MAX_CODIGO
                        String CodPromo=(String) mantProduc.Recupera_Codigo_Promo_Aeliminar(idproducto);//MAX_CODIGO PARA NO ELIMINAR PRODUCTOS CON PROMOCION
                        for (int j = 0; j < tablaproforma.getRowCount(); j++) {//MAX_CODIGO
                            if(CodPromo.equals(String.valueOf(tablaproforma.getValueAt(j, 1)))){
                                JOptionPane.showMessageDialog(this, " NO SE PUEDE ELIMINAR EL PRODUCTO PORQUE POSEE UNA PROMOCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        this.jTextField44.setText("");
                        this.jTextField45.setText("");

                        listaProductosVerifica.remove(fila);

                        String c_prod_x =  String.valueOf(jTable2.getValueAt(fila, 1));                
                        double c_cant_x =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 4)));        

                        double Monto_icbpre_x = objProducto.PrecioICBPER(c_prod_x);
                        double Monto_icbpre_Cab_x  = Monto_icbpre_x * c_cant_x;
                        
                        Double valor = Double.parseDouble(String.valueOf(tablaproforma.getValueAt(fila, 8)));
                        String codpro = String.valueOf(tablaproforma.getValueAt(fila, 1));

                        BigDecimal bd5 = new BigDecimal(total);
                        bd5 = bd5.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        total = bd5.doubleValue();

                        total -= valor + Monto_icbpre_Cab_x;

                        double aux = mantProduc.recupera_Igv_Exonerado(codpro);

                        if (aux > 0) {
                            auxsubtotal = (valor / (1 + (IGV / 100)));
                        } else {
                            auxsubtotal = valor;
                        }

                        BigDecimal bd = new BigDecimal(auxsubtotal);
                        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        auxsubtotal = bd.doubleValue();

                        SubTotal -= auxsubtotal;

                        OpGravada -= auxsubtotal;

                        listsubtotales.remove(fila);
                        productostock.remove(fila);

                        int ultposi;
                        ultposi = ((Integer) tablaproforma.getValueAt(fila, 0)).intValue();
                        ultposi--;
                        tablaproforma.removeRow(fila);

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                        cantidadproductos--;

                        if (cantidadproductos == 0) {
                            this.jTextField7.setText(" ");
                            this.jTextField8.setText(" ");
                            this.jTextField9.setText(" ");
                            this.jTextField21.setText(" ");
                            this.jTextField11.setText(" ");
                            this.jTextField44.setText(" ");
                            this.jTextField45.setText(" ");
                            listsubtotales.removeAll(listsubtotales);
                            productostock.removeAll(productostock);
                            //SubTotal = 0.0;
                            OpGravada=0.0;
                            OpInafecta=0.0;
                            IGV = 0;
                            //total = 0.0;
                        }

                        BigDecimal bd1 = new BigDecimal(SubTotal);
                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        SubTotal = bd1.doubleValue();

                        BigDecimal bd2 = new BigDecimal(total);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        total = bd2.doubleValue();

                        BigDecimal bd3 = new BigDecimal(total - SubTotal);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);


                        /*this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                        if (listatipospagos.get(this.jComboBox1.getSelectedIndex()).getId_TipoPago() == 2) {
                            this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }
                        */

                        //AQUI
                        String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
                        int enter = Integer.parseInt(arr[0]);
                        //int deci = Integer.parseInt(arr[1]);
                        String deci = arr[1];

                        //String x = Integer.toString(deci);
                        String x = deci;
                        int contardeci = x.length();

                        if (contardeci > 1){

                        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
                        String ultimodigito = String.valueOf(ultimoNumero);

                           // if (ultimodigito.equals("0") || ultimodigito.equals("5")){
                            if (ultimodigito.equals("0") ){
                                jTextField44.setText("0.00"); //redondeo
                                jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                                jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                                jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal

                                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 2) {
                                    this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                                }

                            }else{
                                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                                    
                                    BigDecimal bdNT = new BigDecimal(nuevototal);
                                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    nuevototal = bdNT.doubleValue();

                                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal                                    
                                    jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 2) {
                                        this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                                    }

                                }else{

                                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                                        //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                                        double ultimonuevo = Double.valueOf(ultimodigito);
                                        Double redondeo = (ultimonuevo /100) * -1;
                                        Double nuevototal = total - (ultimonuevo /100);
                                        
                                        BigDecimal bdNT = new BigDecimal(nuevototal);
                                        bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        nuevototal = bdNT.doubleValue();

                                        jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                                        jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal                                        
                                        jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                                        jTextField44.setText(String.valueOf(redondeo)); //redondeo
                                        jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                                        if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 2) {
                                            this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                                        }

                                    }

                                }
                            }
                        }else{

                            this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                            this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
                            
                            this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv
                            jTextField44.setText("0.00");                                                                    //redondeo
                            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar

                            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 2) {
                                this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            }

                        }
                        nuevaPromocion.remove(mantProduc.verificaPromocion(idproducto,0).get(0).getIdPromo());//MAX_CODIGO elimina prod_promo de arreglo de promociones
                        int i=1000;//MAX_CODIGO
                        for ( i = 0; i < DetallePromo.size(); i++) { //MAX_CODIGO elimina prod_promo de arreglo de detalle de promociones
                            if(DetallePromo.get(i).getIdPromo()==mantProduc.verificaPromocion(idproducto,0).get(0).getIdPromo())
                                DetallePromo.remove(i);
                                break;

                        }



                        CalculaMontoIngresado();

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " DEBES DE SELLECIONAR UN ITEM PARA ELIMINAR ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                System.out.println("Error al ELIMINAR PRODUCTO : " + ex.toString());
                JOptionPane.showMessageDialog(this, "Error al seleccionar en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else //SI NO HAY PRODUCTOS EN LA TABLA
        {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY PRODUCTOS EN LA PROFORMA", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void AgregaProforma(String cantidad, ProductosPrecios precios,int idpromo, int bandmultiple, int EsMultiDcto, int precfijo) {

        boolean resul = VerificaCantidad(cantidad);
        if (unidad == 0 && fraccion == 0) {
            JOptionPane.showMessageDialog(this, "PORFAVOR INGRESE DATOS CORRECTOS", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resul == true) {
                
                if (precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab().equals("CERT")){
                    
                    CertificadosMedicos objCert = null;                                
                    objCert = new CertificadosMedicos(objetoventana, "", "");
                    objCert.pack();
                    objCert.setVisible(true);
                               
                    certificado = objCert.getNcertificado();                    
                    
                    if ((!String.valueOf(certificado).isEmpty())) {
                        
                        BuscaTipoPrecio(precios, idpromo,bandmultiple,EsMultiDcto,precfijo,certificado);
                    }
                    
                    
                }else{
                    BuscaTipoPrecio(precios, idpromo,bandmultiple,EsMultiDcto,precfijo,certificado);                    
                }
                
                
            } else {
                JOptionPane.showMessageDialog(this, "PORFAVOR INGRESE DATOS CORRECTOS", "Error", JOptionPane.ERROR_MESSAGE);
                BusquedaProducto();
            }
        }
    }

    public void AgregaProformaPromocionPrecio(String cantidad, ProductosPrecios precios, int var) {

        boolean resul = VerificaCantidad(cantidad);

        if (unidad == 0 && fraccion == 0) {
            JOptionPane.showMessageDialog(this, "PORFAVOR INGRESE DATOS CORRECTOS", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resul == true) {
                BuscaTipoPrecioPromocion(precios, var);
            } else {
                JOptionPane.showMessageDialog(this, "PORFAVOR INGRESE DATOS CORRECTOS", "Error", JOptionPane.ERROR_MESSAGE);
                BusquedaProducto();
            }
        }
    }
    /*
     *METODO QUE CALCULA SI SE CUENTA CON EL STOCK RESPECTIVO PARA LA VENTA
     */

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        Producto miproducto;
        Productos_Botica productoBotica;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();

        empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica);

        if (empRecuperado.size() > 0) {
            stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
            int stkempaque = Integer.parseInt(stockempaque.toString());

            empaque = empRecuperado.get(0).getProducto().getEmpaque();
            int empaque_tmp = Integer.parseInt(empaque.toString());

            if (empaque_tmp == 0) {
                empaque_tmp = 1;
            }

            stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
            int stfraccion = Integer.parseInt(stockfraccion.toString());
            /* miproducto = new Producto();
            miproducto.setEmpaque(empaque_tmp);
            productoBotica = new Productos_Botica();
            productoBotica.setProducto(miproducto);
            productoBotica.setMostrador_Stock_Empaque(stkempaque);
            productoBotica.setMostrador_Stock_Fraccion(stfraccion);
            objetostock.setProductoBotica(productoBotica);*/
            totalstock = stkempaque * empaque_tmp + stfraccion;
            int cantidadpedida = unidad * empaque_tmp + fraccion;

            if (cantidadpedida <= totalstock) {
                resultado = true;
            }

        }

        return resultado;

    }

    private void AsignaPrecio(ProductosPrecios precios,int idpromo, int bandmultiple, int EsMultiDcto, int precfijo, String  Ncertificado) {

        double parcial1 = 0;
        String estadopromo = "0";
        String OperadorRec="";
        int porprecio = 0;   
        int porpreciofijo=0;
        int porprecioEsSAP = 0;
        int porcupon=0;
        int porEsSAP=0;
        String marcarpromo ="0";
        int savecupon=0;
        cupon = "";
        CUPONVTA ="";
        Double montoDscto =0.00;
        double pv = 0;
        int _Blister = 0;
        double desc =0;
        Integer IdOperadorRec = 0;
        int empaque1 = Integer.parseInt(empaque.toString());
        Producto miproducto;
        Productos_Botica productoBotica;

        if(objProducto.VerificaEsGratuita(precios.getProductoBotica().getProducto().getIdProducto())==1){//MAX_CODIGO
            int Cant[]=objProducto.PromoEstaEnDosGrupos(precios.getProductoBotica().getProducto().getIdProducto(),idpromo);//MAX_CODIGO
         if(Cant[0]>=2){
            if(VerificarSiEstaTablaONotieneProdAsocTabla(precios.getProductoBotica().getProducto().getIdProducto(),Cant)==true){
               JOptionPane.showMessageDialog(this, "LO SENTIMOS ESTE PRODUCTO TIENE PROMOCION GRATUITA ", "Error", JOptionPane.ERROR_MESSAGE);
               Id_Tipo_Precio = precios.getTipoPrecio().getId_Tipo_Precio();
               return;
             }
            }
        }//MAX_CODIGO
        Object[] listadetalle = new Object[20];
        if (empaque1 == 0 && fraccion > 0) {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                Id_Tipo_Precio = precios.getTipoPrecio().getId_Tipo_Precio();
                String nomproducto = precios.getProductoBotica().getProducto().getDescripcion();
                String idproducto = precios.getProductoBotica().getProducto().getIdProducto();
                _Blister = precios.getProductoBotica().getProducto().getBlister();
                
                if (_Blister > 0) {
                    
                    if (precios.getVprecBotiquin().equals("1")) {
                        if (unidad > 0 && fraccion == 0) {
                            pv = Double.parseDouble(String.valueOf(precios.getprecbotiquin()));
                            Id_Tipo_Precio = "03";
                        } else if (unidad == 0 && fraccion > 0) {
                            //pv = Double.parseDouble(String.valueOf(precios.getbotiquinfraccion()));
                            pv = objproducto.Recupera_PrecioVenta(precios.getProductoBotica().getProducto().getIdProducto(),7);
                            Id_Tipo_Precio = "03";
                        }
                    }else{
                        
                        if (unidad > 0 && fraccion == 0) {
                            pv = precios.getPrecio_Venta();
                            Id_Tipo_Precio = "03";
                        }else{
                            pv = objproducto.Recupera_PrecioVenta(precios.getProductoBotica().getProducto().getIdProducto(),7);
                            Id_Tipo_Precio = "03";
                        }
                    }
                }else{
                    
                
                if (precios.getVprecBotiquin().equals("1")){
                      if (unidad > 0 && fraccion == 0){
                           pv = Double.parseDouble(String.valueOf(precios.getprecbotiquin()));
                           Id_Tipo_Precio = "02";

                      }else if (unidad == 0 && fraccion > 0){
                           pv = Double.parseDouble(String.valueOf(precios.getbotiquinfraccion()));
                           Id_Tipo_Precio = "02";
                      }

                    }else{
                    
                        pv = precios.getPrecio_Venta(); // original
                    
                    }

                }
                
                cantidadproductos = jTable2.getRowCount();
                int indice = cantidadproductos;
                cantidadproductos++;

                miproducto = new Producto();
                miproducto.setEmpaque(empaque1);
                productoBotica = new Productos_Botica();
                productoBotica.setProducto(miproducto);
                productoBotica.setMostrador_Stock_Empaque(precios.getProductoBotica().getMostrador_Stock_Empaque());
                productoBotica.setMostrador_Stock_Fraccion(precios.getProductoBotica().getMostrador_Stock_Fraccion());
                objetostock = new ProductosPrecios();
                objetostock.setProductoBotica(productoBotica);

                productostock.add(indice, objetostock);
                Double montoDsctofraccion = 0.0;
                Double descuento2 = 0.00;
                Integer recuperaUnidadesminimo = 0;
                int orden = 0;
                int cantidad = 0;
                int Esrecarga = 0;
                ProductosPromociones obj = null;

                Esrecarga = objProducto.Verifica_EsRecarga(idproducto);
                if (Esrecarga == 1) {
                   IngreseOperadorRecarga objRec = new IngreseOperadorRecarga(this, idbotica,idproducto);
                   objRec.setDefaultCloseOperation(0);
                   objRec.setVisible(true);

                   IdOperadorRec = objRec.getIdOperadorRecarga();
                   OperadorRec = objRec.getDescOperadorRecarga();
                }

                if (BuscarProductos.isEspromo()) {
                    estadopromo = "1";
                    cantidad = objProducto.Verifica_Promocion(idproducto);
                    List<ListaDetalles> listPromocion = mantProduc.verificaPromocion(idproducto,idpromo);
                    
                    cantidad = 1;
                    if (cantidad > 1){
                        obj = new ProductosPromociones(objetoventana, nomproducto, idproducto);
                        obj.pack();
                        obj.setVisible(true);
                        descuento2 = obj.getDescuento();
                        recuperaUnidadesminimo = 1;
                    }else{
                        orden = listPromocion.get(0).getOrden();
                        int vfagrupoparamostrar = 0;
                        if (bandmultiple == 1){
                            vfagrupoparamostrar = objProducto.MostrarPantallaMultiple(idproducto, 0);
                            if (vfagrupoparamostrar == 0){
                                descuento2 = 0.0;
                            }else{
                                descuento2 = armarPromocion(listPromocion, idproducto, unidad,idpromo,bandmultiple);
                            }
                        }else{
                            descuento2 = armarPromocion(listPromocion, idproducto, unidad, idpromo,bandmultiple);
                        }
                        recuperaUnidadesminimo = retornaUnidades(listPromocion, unidad);
                    }                      
                    //System.out.println("la cantidad es: "+cantidad);
                }
                
                listadetalle[4] = unidad;
                //System.out.println("REALIZAR VENTA - La cantidad es: "+objProducto.Verifica_Cantidad_Producto(idproducto));
                if(objProducto.Verifica_Es_Manual(idproducto) == 1 ){

                        int p = JOptionPane.showConfirmDialog(null, "¿VERIFICÓ LOS DATOS DE LA RECETA Y DNI DEL CLIENTE?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (p == JOptionPane.YES_OPTION) {
                            if(unidad <= objProducto.Verifica_Cantidad_Producto(idproducto)) {
                                listadetalle[0] = cantidadproductos;
                                listadetalle[1] = idproducto;
                                listadetalle[2] = nomproducto;
                                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                                listadetalle[4] = unidad;
                                listadetalle[5] = fraccion;
                                listadetalle[6] = pv;

                                listadetalle[9] = "0";
                                listadetalle[10] = estadopromo;
                                listadetalle[11] = "0";
                                listadetalle[12] = "0";

                                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                                listadetalle[13] = igvaux;

                                desc = precios.getDescuento_Venta();
                                BigDecimal bd8 = new BigDecimal(desc);
                                bd8 = bd8.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd8.doubleValue();

                                descuento = Double.parseDouble(listadetalle[6].toString());
                                PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                parcial = PVPx * unidad;

                                if (empaque1 > 0) {
                                    parcial1 = (fraccion * PVPx) / empaque1;
                                }

                                parcial = parcial + parcial1;

                                /********************************************
                                CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                 *********************************************/
                                BigDecimal bd = new BigDecimal(PVPx);
                                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                PVPx = bd.doubleValue();

                                BigDecimal bd1 = new BigDecimal(parcial);
                                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                parcial = bd1.doubleValue();

                                Double ss = Double.valueOf(descuento2);
                                descuento = ss;
                                if(objProducto.Verifica_Es_Promo_Frac(idproducto) == 1) {
                                    montoDsctofraccion = ((PVPx/empaque1) * (descuento)) / 100 * fraccion;
                                }else {
                                    montoDsctofraccion = 0.0;
                                }

                                montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                                listadetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                                if (listadetalle[1].toString() != null) {
                                    tablaproforma.addRow(listadetalle);
                                }
                                listadetalle[14] = "0";
                                listadetalle[15] = "0";
                                listadetalle[16] = "";
                                listadetalle[17] = "";
                                listadetalle[18] = Ncertificado;
                                listadetalle[19] = cantbolsas;
                                
                                //double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                                CalculaMontos(igvaux, indice);
                                listaProductosVerifica.add(listadetalle);
                                if (ss > 0.00) {

                                    listadetalle = new Object[20];
                                    indice = indice + 1;
                                    String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,0,0).toString().trim();
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    if (cantidad > 1) {
                                        listadetalle[1] = obj.getCodPromocion();
                                        listadetalle[2] = obj.getDescripPromocion();
                                    } else {
                                        listadetalle[1] = micodigo;
                                        listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                    }
                                    listadetalle[4] = recuperaUnidadesminimo;
                                    listadetalle[5] = 0;

                                    if (fraccion > 0) {
                                        //montoDsctofraccion = (orden * PVPx) / empaque1;
                                    }

                                    if (orden == 1) {
                                        montoDscto = 0.0;
                                    }

                                    montoDscto = montoDscto + montoDsctofraccion;
                                    BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                                    bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                    listadetalle[6] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[7] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[8] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                                    listadetalle[9] = "1";
                                    listadetalle[10] = "1";
                                    listadetalle[11] = "0";
                                    listadetalle[12] = "0";
                                    listadetalle[13] = igvaux;
                                    listadetalle[14] = "0";
                                    listadetalle[15] = "0";
                                    listadetalle[16] = "";
                                    listadetalle[17] = "";
                                    listadetalle[18] = Ncertificado;
                                    listadetalle[19] = cantbolsas;

                                    if (unidad > 0 && orden == 1) {
                                    } else {
                                        miproducto = new Producto();
                                        miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                        productoBotica = new Productos_Botica();
                                        productoBotica.setProducto(miproducto);
                                        productoBotica.setMostrador_Stock_Empaque(1);
                                        productoBotica.setMostrador_Stock_Fraccion(1);
                                        objetostock = new ProductosPrecios();
                                        objetostock.setProductoBotica(productoBotica);
                                        productostock.add(indice, objetostock);
                                        tablaproforma.addRow(listadetalle);
                                        tablaproforma.setValueAt(1, indice, 4);
                                    }

                                    //ordenproducto = ordenproducto + 1;

                                    CalculaMontos(igvaux, indice);

                                    listaProductosVerifica.add(listadetalle);

                                    for (int i = 0; i < listaProductosVerifica.size(); i++) {
                                        Object[] valor = new Object[1];
                                        valor[0] = listaProductosVerifica.get(i);

                                        Object[] datos = new Object[20];
                                        datos = (Object[]) valor[0];

                                        if (datos[10].toString().trim() == "1" && datos[11].toString().trim() == "0") {

                                            datos[11] = ordenproducto;
                                            datos[12] = IdOperadorRec;
                                            listaProductosVerifica.set(i, datos);
                                        }
                                    }
                                    ordenproducto = ordenproducto + 1;

                                }

                                jTable2.requestFocus();
                                jTable2.changeSelection(indice, indice, false, false);

                                if (igvaux < 1) {
                                    lisProdSinIGV.add(idproducto);
                                    col = jTable2.getColumnModel().getColumn(0);
                                    col.setCellRenderer(new ColoredTableCellRenderer());
                                    col = jTable2.getColumnModel().getColumn(1);
                                    col.setCellRenderer(new ColoredTableCellRenderer());
                                } else {
                                    lisProdSinIGV.add(9999);
                                }
                            }else {
                                JOptionPane.showMessageDialog(this, "CANTIDAD PEDIDA ES MAYOR A "+objProducto.Verifica_Cantidad_Producto(idproducto)+" UNIDADES\nNO SE AGREGARA EL DESCUENTO A ESTE PRODUCTO", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                listadetalle[0] = cantidadproductos;
                                listadetalle[1] = idproducto;
                                listadetalle[2] = nomproducto;
                                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                                listadetalle[4] = unidad;
                                listadetalle[5] = fraccion;
                                listadetalle[6] = pv;

                                descuento = Double.parseDouble(listadetalle[6].toString());
                                PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                parcial = PVPx * unidad;

                                if (empaque1 > 0) {
                                    parcial1 = (fraccion * PVPx) / empaque1;
                                }

                                parcial = parcial + parcial1;

                                /********************************************
                                CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                 *********************************************/
                                BigDecimal bd = new BigDecimal(PVPx);
                                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                PVPx = bd.doubleValue();

                                BigDecimal bd1 = new BigDecimal(parcial);
                                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                parcial = bd1.doubleValue();

                                listadetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                                if (listadetalle[1].toString() != null) {
                                    tablaproforma.addRow(listadetalle);
                                }
                                listadetalle[14] = "0";
                                listadetalle[15] = "0";
                                listadetalle[16] = "";
                                listadetalle[17] = "";
                                listadetalle[18] = Ncertificado;
                                listadetalle[19] = cantbolsas;
                                
                                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                                CalculaMontos(igvaux, indice);
                                listaProductosVerifica.add(listadetalle);
    //                            if (ss > 0.00) {

                                    listadetalle = new Object[20];
                                    indice = indice + 1;
                                    String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,0,0).toString().trim();
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    if (cantidad > 1) {
                                        listadetalle[1] = obj.getCodPromocion();
                                        listadetalle[2] = obj.getDescripPromocion();
                                    } else {
                                        listadetalle[1] = micodigo;
                                        listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                    }
                                    listadetalle[4] = recuperaUnidadesminimo;
                                    listadetalle[5] = 0;

                                    if (unidad > 0 && orden == 1) {
                                    } else {
                                        miproducto = new Producto();
                                        miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                        productoBotica = new Productos_Botica();
                                        productoBotica.setProducto(miproducto);
                                        productoBotica.setMostrador_Stock_Empaque(1);
                                        productoBotica.setMostrador_Stock_Fraccion(1);
                                        objetostock = new ProductosPrecios();
                                        objetostock.setProductoBotica(productoBotica);
                                        productostock.add(indice, objetostock);
    //                                    tablaproforma.addRow(listadetalle);
    //                                    tablaproforma.setValueAt(1, indice, 4);
                                    }

                                    //ordenproducto = ordenproducto + 1;

                                    CalculaMontos(igvaux, indice);

                                    listaProductosVerifica.add(listadetalle);

                                    for (int i = 0; i < listaProductosVerifica.size(); i++) {
                                        Object[] valor = new Object[1];
                                        valor[0] = listaProductosVerifica.get(i);

                                        Object[] datos = new Object[13];
                                        datos = (Object[]) valor[0];

                                        if (datos[10].toString().trim() == "1" && datos[11].toString().trim() == "0") {

                                            datos[11] = ordenproducto;
                                            datos[12] = IdOperadorRec;
                                            listaProductosVerifica.set(i, datos);
                                        }
                                    }
                                    ordenproducto = ordenproducto + 1;

    //                            }

                                jTable2.requestFocus();
                                jTable2.changeSelection(indice, indice, false, false);

                                if (igvaux < 1) {
                                    lisProdSinIGV.add(idproducto);
                                    col = jTable2.getColumnModel().getColumn(0);
                                    col.setCellRenderer(new ColoredTableCellRenderer());
    //                                col = jTable2.getColumnModel().getColumn(1);
    //                                col.setCellRenderer(new ColoredTableCellRenderer());
                                } else {
                                    lisProdSinIGV.add(9999);
                                }
                            }
                            
                        }
                        if(p == JOptionPane.NO_OPTION){
                            JOptionPane.showMessageDialog(this, "NO SE AGREGARA EL DESCUENTO A ESTE PRODUCTO", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                            listadetalle[0] = cantidadproductos;
                            listadetalle[1] = idproducto;
                            listadetalle[2] = nomproducto;
                            listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                            listadetalle[4] = unidad;
                            listadetalle[5] = fraccion;
                            listadetalle[6] = pv;

                            descuento = Double.parseDouble(listadetalle[6].toString());
                            PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                            parcial = PVPx * unidad;

                            if (empaque1 > 0) {
                                parcial1 = (fraccion * PVPx) / empaque1;
                            }

                            parcial = parcial + parcial1;

                            /********************************************
                            CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                             *********************************************/
                            BigDecimal bd = new BigDecimal(PVPx);
                            bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            PVPx = bd.doubleValue();

                            BigDecimal bd1 = new BigDecimal(parcial);
                            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            parcial = bd1.doubleValue();

                            listadetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            if (listadetalle[1].toString() != null) {
                                tablaproforma.addRow(listadetalle);
                            }

                            double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                            CalculaMontos(igvaux, indice);
                            listaProductosVerifica.add(listadetalle);
//                            if (ss > 0.00) {

                                listadetalle = new Object[20];
                                indice = indice + 1;
                                String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,0,0).toString().trim();
                                listadetalle[0] = jTable2.getRowCount() + 1;
                                if (cantidad > 1) {
                                    listadetalle[1] = obj.getCodPromocion();
                                    listadetalle[2] = obj.getDescripPromocion();
                                } else {
                                    listadetalle[1] = micodigo;
                                    listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                }
                                listadetalle[4] = recuperaUnidadesminimo;
                                listadetalle[5] = 0;

                                if (unidad > 0 && orden == 1) {
                                } else {
                                    miproducto = new Producto();
                                    miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                    productoBotica = new Productos_Botica();
                                    productoBotica.setProducto(miproducto);
                                    productoBotica.setMostrador_Stock_Empaque(1);
                                    productoBotica.setMostrador_Stock_Fraccion(1);
                                    objetostock = new ProductosPrecios();
                                    objetostock.setProductoBotica(productoBotica);
                                    productostock.add(indice, objetostock);
//                                    tablaproforma.addRow(listadetalle);
//                                    tablaproforma.setValueAt(1, indice, 4);
                                }

                                //ordenproducto = ordenproducto + 1;

                                CalculaMontos(igvaux, indice);

                                listaProductosVerifica.add(listadetalle);

                                for (int i = 0; i < listaProductosVerifica.size(); i++) {
                                    Object[] valor = new Object[1];
                                    valor[0] = listaProductosVerifica.get(i);

                                    Object[] datos = new Object[13];
                                    datos = (Object[]) valor[0];

                                    if (datos[10].toString().trim() == "1" && datos[11].toString().trim() == "0") {

                                        datos[11] = ordenproducto;
                                        datos[11] = IdOperadorRec;
                                        datos[12] = "0";
                                        listaProductosVerifica.set(i, datos);
                                    }
                                }
                                ordenproducto = ordenproducto + 1;

//                            }

                            jTable2.requestFocus();
                            jTable2.changeSelection(indice, indice, false, false);

                            if (igvaux < 1) {
                                lisProdSinIGV.add(idproducto);
                                col = jTable2.getColumnModel().getColumn(0);
                                col.setCellRenderer(new ColoredTableCellRenderer());
//                                col = jTable2.getColumnModel().getColumn(1);
//                                col.setCellRenderer(new ColoredTableCellRenderer());
                            } else {
                                lisProdSinIGV.add(9999);
                            }
                        }
                }else{

                    listadetalle[0] = cantidadproductos;
                    listadetalle[1] = idproducto;
                    listadetalle[2] = nomproducto + " " + OperadorRec;;
                    listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                    listadetalle[4] = unidad;
                    listadetalle[5] = fraccion;
                    listadetalle[6] = pv;

                    listadetalle[9] = "0";
                    listadetalle[10] = estadopromo;
                    listadetalle[11] = "0";
                    listadetalle[12] = IdOperadorRec;
                    double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                    listadetalle[13] = igvaux;

                   
                    if (precios.getVprecBotiquin().equals("1") || _Blister > 0){

                            if (unidad > 0 && fraccion == 0){

                                desc = precios.getdscto02();
                                BigDecimal bd8 = new BigDecimal(desc);
                                bd8 = bd8.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd8.doubleValue();
                                
                                descuento = Double.parseDouble(listadetalle[6].toString());
                                PVPx = pv - (pv * (desc / 100));                                
                                parcial = PVPx * unidad;
                            }else if (unidad == 0 && fraccion > 0){

                                desc = precios.getdscto01();
                                BigDecimal bd8 = new BigDecimal(desc);
                                bd8 = bd8.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd8.doubleValue();

                                descuento = Double.parseDouble(listadetalle[6].toString());
                                PVPx = pv - (pv *(desc / 100));                                
                                parcial = PVPx * unidad;
                            }

                    }else{



                    desc = precios.getDescuento_Venta();
                    BigDecimal bd8 = new BigDecimal(desc);
                    bd8 = bd8.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    desc = bd8.doubleValue();

                    descuento = Double.parseDouble(listadetalle[6].toString());
                    PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                    parcial = PVPx * unidad;


                    }

                    if (empaque1 > 0) {
                        parcial1 = (fraccion * PVPx) / empaque1;
                        
                        if(_Blister > 0){
                            if (unidad > 0 && fraccion == 0) {
                                    
                                parcial1 = (fraccion * PVPx) / empaque1;
                                    
                            }else{
                                parcial1 = (fraccion * PVPx) / _Blister; 
                            }
                            
                        }
                    }

                    parcial = parcial + parcial1;

                    /********************************************
                    CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                     *********************************************/
                    BigDecimal bd = new BigDecimal(PVPx);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    PVPx = bd.doubleValue();

                    BigDecimal bd1 = new BigDecimal(parcial);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    parcial = bd1.doubleValue();

                    Double ss = Double.valueOf(descuento2);
                    descuento = ss;
                    // montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                    porprecio = objProducto.Verifica_Promo_PorPrecio(idproducto,1,idpromo);
                    if(porprecio == 1 ) {

                        porcupon = objProducto.Verifica_Promo_PorPrecio(idproducto,2,idpromo);
                        if (porcupon==1){
                            
                            
                            CodBarrasCupon objProm = null;                                
                               objProm = new CodBarrasCupon(objetoventana, "", "");
                               objProm.pack();
                               objProm.setVisible(true);
                               
                               
                               cupon = objProm.getCodbarras();
                               CUPONVTA = objProm.getNumcupon();
                            
                            
                            //JOptionPane p = new JOptionPane();
                            //cupon = p.showInputDialog(this, "Ingrese Numero de Cupon  ? ", "CUPON DESCUENTO", JOptionPane.QUESTION_MESSAGE);

                           // if (!String.valueOf(cupon).isEmpty()) {
                           if ((!String.valueOf(cupon).isEmpty()) && (!String.valueOf(CUPONVTA).isEmpty())) {

                                if (cupon != null) {

                                    if (cupon.compareTo("0") != 0) {

                                        if (!String.valueOf(cupon).isEmpty() || cupon != null) {

                                            cupon = cupon.toUpperCase();
                                            savecupon = objProducto.Verifica_Promo_PorPrecio1(idproducto,cupon,1); // gino
                                            //savecupon = objProducto.InsertaCupon(idproducto,3);

                                            if (savecupon == 2 ){
                                                    JOptionPane.showMessageDialog(this, "ESTE CUPON YA ESTA REGISTRADO ", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

                                                }else{
                                                   if (savecupon == 1 ){
                                                       
                                                       porpreciofijo = objProducto.Verifica_Promo_PorPrecio(idproducto, 6, idpromo);
                                                        if (porpreciofijo==1 ){
                                                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                                                            montoDscto = dsctoporprecio;
                                                        }else{

                                                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0);
                                                            montoDscto = (PVPx * (dsctoporprecio)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                                        }

                                                   }else{
                                                       if (savecupon == 3 ){
                                                            JOptionPane.showMessageDialog(this, "ESTE CUPON NO ESTA REGISTRADO ", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

                                                            ss = 0.0;
                                                            cupon ="";
                                                        }
                                                   }

                                                }


                                            //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto)*unidad;
                                            //montoDscto = dsctoporprecio ;

                                         }else{
                                            ss = 0.0;
                                            cupon ="";
                                         }
                                    }else{
                                        ss = 0.0;
                                        cupon ="";
                                    }
                                }else{
                                    ss = 0.0;
                                    cupon ="";
                                }
                            }else{
                                cupon ="";
                                ss = 0.0;
                            }
                        }else{
                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                            montoDscto = dsctoporprecio ;
                        }
                        
                    }else{
                        if (precfijo == 1){
                                montoDscto = descuento;
                            }else{
                                porpreciofijo = objProducto.Verifica_Promo_PorPrecio(idproducto, 6, idpromo);
                                if (porpreciofijo==1 ){
                                    Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                                    montoDscto = dsctoporprecio;
                                }else{
                                    montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                    //montoDscto = (PVPx * (descuento)) / 100 ;
                                }
                            }
                    }


                    porprecioEsSAP = objProducto.Verifica_Promo_PorPrecio(idproducto,3,idpromo);
                        if(porprecioEsSAP == 1 ) {
                            porEsSAP = objProducto.Verifica_Promo_PorPrecio(idproducto,4,idpromo);
                            if (porEsSAP==1){
                                String EsSAP ="";

                                if (unidad > 0){


                                Confirmar_Venta_EsSAP_MSN boti = new Confirmar_Venta_EsSAP_MSN(objetoventana, this);
                                boti.pack();
                                boti.setVisible(true);

                                EsSAP = boti.getresultEsSAP();

                                if (!String.valueOf(EsSAP).isEmpty()) {

                                    if (EsSAP != null) {

                                        if (EsSAP.compareTo("0") != 0) {

                                            if (!String.valueOf(EsSAP).isEmpty() || EsSAP != null) {

                                                EsSAP = EsSAP.toUpperCase();
                                                //savecupon = objProducto.InsertaCupon(idproducto,3);

                                                //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto);
                                                //montoDscto = dsctoporprecio ;
                                                montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                                             }else{
                                                ss = 0.0;
                                                EsSAP ="";
                                             }
                                        }else{
                                            ss = 0.0;
                                            EsSAP ="";
                                        }
                                    }else{
                                        ss = 0.0;
                                        EsSAP ="";
                                    }
                                }else{
                                    EsSAP ="";
                                    ss = 0.0;
                                }

                                }else{
                                    ss = 0.0;
                                    EsSAP ="";
                                }

                            }else{
                                //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto);
                                //montoDscto = dsctoporprecio ;
                                montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                            }

                        }else{
                            if (porprecio == 0) {                                
                                
                                if (precfijo == 1){
                                    montoDscto = descuento;
                                }else{
                                    if(porpreciofijo == 1){

                                        Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                                        montoDscto = dsctoporprecio;

                                    }else{
                                        montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                        //montoDscto = (PVPx * (descuento)) / 100 ;
                                    }
                                }
                            }
                        }

                    int validaMitadGratis = 0;
                    int primerValIngresado = 0;

                        validaMitadGratis = objProducto.Verifica_Es_Mitad_Gratis(idproducto);

                        if(tablaproforma.getRowCount() > 0) {
                           primerValIngresado = Integer.parseInt(tablaproforma.getValueAt(tablaproforma.getRowCount()-1, 4).toString());
                        }

                        if(validaMitadGratis == 1 ) {
                            montoDscto = 0.0;
                            if(empaque1/2 == fraccion) {
                                montoDsctofraccion = ((PVPx * (descuento)) / 100) / 2;
                            }else if(primerValIngresado > 0 && primerValIngresado/2 == unidad){
                                montoDscto = (PVPx * (descuento)) / 100;
                            }else{
                                ss = 0.0;
                            }

                        }

                    listadetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                    if (listadetalle[1].toString() != null) {
                        tablaproforma.addRow(listadetalle);
                    }

                        //montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                    esgratuita1 = objProducto.RetornaEsGratis(idproducto);
                    if (esgratuita1.equals("1")){
                        listadetalle[14] = montoDscto;
                    }else{
                        listadetalle[14] = "0";
                    }

                    if(objProducto.esPromoEmpFraccion(idproducto)==1){//MAX_CODIGO
                                        montoDsctofraccion = (fraccion * PVPx) / empaque1;
                                        Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0);
                                        montoDsctofraccion=montoDsctofraccion*(dsctoporprecio/100);
                                        
                    }
                    marcarpromo = mantProduc.MarcarPromo(idproducto).toString().trim();                    
                    
                    if (marcarpromo.equals("")){
                        marcarpromo = "0";
                    }
                    if (ss > 0.00) {
                        if (EsMultiDcto ==1){
                        idpromo = precios.getGrupo_Id();
                        }
                        String micodigoPROMO = mantProduc.Recupera_Promo_Codigo(idproducto,idpromo,EsMultiDcto).toString().trim();
                        esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto,micodigoPROMO); // promocion
                        
                        
                        if (esgratuita2.equals("1")){
                          listadetalle[15] = "1";
                        }else{
                          listadetalle[15] = "0";                          
                        }
                    }else{
                        listadetalle[15] = "0";
                    }
                        listadetalle[16] = marcarpromo;
                        listadetalle[17] = CUPONVTA;
                        listadetalle[18] = Ncertificado;
                        listadetalle[19] = cantbolsas;

                    
                    
                    CalculaMontos(igvaux, indice);
                    listaProductosVerifica.add(listadetalle);


                    //CODIGO_MAX
                        int temp[]=objProducto.PromoEstaEnDosGrupos(idproducto,idpromo);
                        if(temp[0]>=2){                        
                        for (int i = 0; i < jTable2.getRowCount()-1; i++) {//entro a este metodo si el codigo de la promo es gratuita y esta en dos grupos
                            String CodProducto=(String)jTable2.getValueAt(i, 1);
                                if(idproducto.equals(CodProducto)){ss=0.00;break;}
                             }
                        }//CODIGO_MAX
                        


                    String VerificaFraccComprar = mantProduc.Recupera_Promo_Comprar(idproducto,idpromo,"1").toString().trim();
                    String VerificaRegalar = mantProduc.Recupera_Promo_Comprar(idproducto,idpromo,"2").toString().trim();

                    if (VerificaFraccComprar.equals("0") || VerificaFraccComprar.equals("")){

                    // AQUI AGREGO LA FILA DE PROMOCION AL JTABLE / SI EL PRODUCTO TUVIERA --- GINO PAREDES
                    if (ss > 0.00) {

                        String PromoDescripcion = "";
                        //double monto1 = Double.parseDouble(this.jTextField21.getText().trim());

                        String[] recuperacadenaPromo = nomproducto.split(" ");
                        PromoDescripcion = String.valueOf(recuperacadenaPromo[0]);  //recupera el nombre de la promo

                        /*String validar = mantProduc.Recupera_Promo_CodigoPrecio().toString().trim();
                        String[] recuperacadena = validar.split("@");
                        Double compara1 = Double.parseDouble(recuperacadena[1]); //monto a comparar
                        */
                            listadetalle = new Object[20];
                            indice = indice + 1;
                            String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,idpromo,EsMultiDcto).toString().trim();
                            listadetalle[0] = jTable2.getRowCount() + 1;
                            if (cantidad > 1) {
                                listadetalle[1] = obj.getCodPromocion();
                                listadetalle[2] = obj.getDescripPromocion();
                            } else {
                                listadetalle[1] = micodigo;
                                listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                            }
                            listadetalle[4] = recuperaUnidadesminimo;
                            listadetalle[5] = 0;

                            if (fraccion > 0) {
                               // montoDsctofraccion = (orden * PVPx) / empaque1;
                            }

                            if (orden == 1) {
                                montoDscto = 0.0;
                            }

                            double mtoDsctoOriginal = montoDscto + montoDsctofraccion;
                            if (precfijo == 1){
                                montoDscto = (montoDscto + montoDsctofraccion) * unidad;
                            }else{
                                montoDscto = (montoDscto + montoDsctofraccion);
                            }
                            
                            BigDecimal bd5 = new BigDecimal(mtoDsctoOriginal * (-1));
                            bd5 = bd5.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                
                            BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                            bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                            if (unidad > 0 && fraccion ==0){
                                    unifra = unidad;
                            }else if (unidad == 0 && fraccion >0){
                                    unifra = fraccion;
                            }
                            BigDecimal bd4_1 = new BigDecimal((montoDscto * (-1))/unifra);
                            bd4_1 = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                            listadetalle[6] = bd4_1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[7] = bd5.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[8] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                            /*aqui pregunto si es esgratuita*/


                            listadetalle[9] = "1";
                            listadetalle[10] = "1";
                            listadetalle[11] = "0";
                            listadetalle[12] = "0";
                            listadetalle[13] = igvaux;
                            esgratuita1 = objProducto.RetornaEsGratis(micodigo);
                            if (esgratuita1.equals("1")){
                                listadetalle[14] = montoDscto;
                            }else{
                                listadetalle[14] = "0";
                            }
                            esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto,micodigo); // promocion
                            marcarpromo = mantProduc.MarcarPromo(micodigo).toString().trim();
                            if (esgratuita2.equals("1")){
                              listadetalle[15] = "1";
                            }else{
                              listadetalle[15] = "0";
                            } 
                            listadetalle[16] = marcarpromo;
                            listadetalle[17] = "";
                            listadetalle[18] = Ncertificado;
                            listadetalle[19] = cantbolsas;

                            if (unidad > 0 && orden == 1) {
                            } else {
                                miproducto = new Producto();
                                miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                productoBotica = new Productos_Botica();
                                productoBotica.setProducto(miproducto);
                                productoBotica.setMostrador_Stock_Empaque(1);
                                productoBotica.setMostrador_Stock_Fraccion(1);
                                objetostock = new ProductosPrecios();
                                objetostock.setProductoBotica(productoBotica);                                
                                productostock.add(indice, objetostock);
                                if (montoDscto != 0){
                                tablaproforma.addRow(listadetalle);
                                }

                                int posi = 4;
                                if (unidad > 0 && fraccion ==0){
                                   posi = posi;
                                   unifra = unidad;
                                }else if (unidad == 0 && fraccion >0){
                                   posi = 5;
                                   unifra = fraccion;
                                }

                                tablaproforma.setValueAt(unifra, indice, posi);
                                
                            }

                            CalculaMontos(igvaux, indice);
                            listaProductosVerifica.add(listadetalle);

                            for (int i = 0; i < listaProductosVerifica.size(); i++) {
                                Object[] valor = new Object[1];
                                valor[0] = listaProductosVerifica.get(i);

                                Object[] datos = new Object[19];
                                datos = (Object[]) valor[0];
                                
                                if (datos[10].toString().trim().equals("1") && datos[11].toString().trim().equals("0")) {

                                    datos[11] = ordenproducto;
                                    listaProductosVerifica.set(i, datos);
                                }
                            }
                            ordenproducto = ordenproducto + 1;

                            //marcarpromo = mantProduc.MarcarPromo(idproducto,micodigo,idpromo).toString().trim();       


                            /**************************************
                             *** Desde aqui tercera promocion******
                             **************************************/

                            int idgrup = temp[1];
                            int contardsctograt = objProducto.dsctogratuita(idgrup,0);

                            if (contardsctograt > 1){
                                listadsctogratuita.removeAll(listadsctogratuita);
                                listadsctogratuita = objproducto.Listardsctogratuita(idgrup,1);
                                String idprddscto="";

                                for (int ix = 0; ix < listadsctogratuita.size(); ix++) {

                                    idprddscto = listadsctogratuita.get(ix).getidproducto();
                                    int cantdscto = objProducto.dsctocant(idprddscto);
                                    String nvacant = String.valueOf(cantdscto);
                                    //listaProductosdscto.removeAll(listaProductosdscto);
                                    //listaProductosdscto = objproducto.ListarProductos(idprddscto, id_botica, "01", 1, 1);


                                    listadetalle = new Object[20];
                                    indice = indice + 1;
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    listadetalle[1] = idprddscto;
                                    listadetalle[2] = mantProduc.Recupera_Promo_Nombredscto(idprddscto);
                                    //if (cantdscto > 0){
                                    if (nvacant.equals("1")){
                                        listadetalle[4] = recuperaUnidadesminimo;
                                    }else{
                                        //listadetalle[4] = 4;
                                        listadetalle[4] = cantdscto;
                                    }
                                    listadetalle[5] = 0;

                                    if (orden == 1) {
                                        montoDscto = 0.0;
                                    }

                                    
                                    String recPV = mantProduc.Recupera_promodscto(idprddscto,idbotica,"01",1).toString().trim();
                                    String dscto = mantProduc.Recupera_promodscto(idprddscto,idbotica,"01",2).toString().trim();
                                    String recPV_aux = recPV;
                                    //listaProductosdscto.removeAll(listaProductosdscto);
                                    //listaProductosdscto = objproducto.ListarProductos(idprddscto, id_botica, "01", 1, 1);


                                    double pvxdscto = 0;
                                    BigDecimal bd1_dscto;
                                    double pv_dscto = 0;
                                    double pv_dscto_ori =0;
                                    double desc_dscto = 0;

                                    if ((recPV == null) || (recPV.equals(""))){
                                        recPV = mantProduc.Recupera_promodscto(listadsctogratuita.get(0).getidproducto(),idbotica,"01",1).toString().trim();
                                        dscto = mantProduc.Recupera_promodscto(listadsctogratuita.get(0).getidproducto(),idbotica,"01",2).toString().trim();
                                    }

                                    pv_dscto_ori = Double.parseDouble(recPV);
                                    //if (cantdscto > 0){
                                    if (nvacant.equals("1")){

                                        pv_dscto = Double.parseDouble(recPV);
                                    }else{
                                        //pv_dscto = Double.parseDouble(recPV)* 4 ;
                                        pv_dscto = Double.parseDouble(recPV)* cantdscto;
                                    }
                                    desc_dscto = Double.parseDouble(dscto);
                                    pvxdscto = pv_dscto - (pv_dscto * (desc_dscto / 100));

                                    bd1_dscto = new BigDecimal(pvxdscto);
                                    bd1_dscto = bd1_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                    pvxdscto = bd1_dscto.doubleValue();


                                    if ((recPV_aux == null) || (recPV_aux.equals(""))){
                                        montoDscto = pvxdscto * (-1);
                                        pv_dscto_ori = pv_dscto_ori * (-1);
                                    } else{
                                        montoDscto = pvxdscto;
                                    }
                                    //montoDscto = montoDscto + montoDsctofraccion;

                                    //BigDecimal bd4_dscto = new BigDecimal(montoDscto * (-1));
                                    BigDecimal bd4_dscto = new BigDecimal(montoDscto);
                                    bd4_dscto = bd4_dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    if (unidad > 0 && fraccion ==0){
                                        unifra = unidad;
                                    }else if (unidad == 0 && fraccion >0){
                                        unifra = fraccion;
                                    }
                                    //BigDecimal bd4_1dscto = new BigDecimal((montoDscto * (-1))/unifra);
                                    BigDecimal bd4_1dscto = new BigDecimal(montoDscto /unifra);
                                    bd4_1dscto = bd4_1dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                    listadetalle[6] = pv_dscto_ori;//bd4_1dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[7] = pv_dscto_ori;//bd4_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[8] = bd4_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[9] = "1";
                                    listadetalle[10] = "1";
                                    listadetalle[11] = "0";
                                    listadetalle[12] = "0";
                                    listadetalle[13] = igvaux;

                                    esgratuita1 = objProducto.RetornaEsGratis(idprddscto); //micodigo
                                    if (esgratuita1.equals("1")){
                                       listadetalle[14] = montoDscto;
                                    }else{
                                       listadetalle[14] = "0";
                                    }
                                    esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto,idprddscto); // promocion
                                    marcarpromo = mantProduc.MarcarPromo(idprddscto).toString().trim();
                                    if (esgratuita2.equals("1")){
                                       listadetalle[15] = "1";
                                    }else{
                                       listadetalle[15] = "0";
                                    }
                                    
                                    listadetalle[16] = marcarpromo;
                                    listadetalle[17] = "";
                                    listadetalle[18] = Ncertificado;
                                    listadetalle[19] = cantbolsas;

                                    if (unidad > 0 && orden == 1) {
                                    } else {
                                        miproducto = new Producto();
                                        miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                        productoBotica = new Productos_Botica();
                                        productoBotica.setProducto(miproducto);
                                        productoBotica.setMostrador_Stock_Empaque(1);
                                        productoBotica.setMostrador_Stock_Fraccion(1);
                                        objetostock = new ProductosPrecios();
                                        objetostock.setProductoBotica(productoBotica);
                                        productostock.add(indice, objetostock);

                                      if (montoDscto != 0){
                                         tablaproforma.addRow(listadetalle);
                                      }
                                    int posi = 4;
                                    if (unidad > 0 && fraccion ==0){
                                       posi = posi;
                                       unifra = unidad;
                                    }else if (unidad == 0 && fraccion >0){
                                       posi = 5;
                                       unifra = fraccion;
                                    }
                                    //if (cantdscto > 0){
                                    if (nvacant.equals("1")){

                                    tablaproforma.setValueAt(unifra, indice, posi);
                                    }else{
                                    //tablaproforma.setValueAt(4, indice, posi);
                                    tablaproforma.setValueAt(cantdscto, indice, posi);
                                    //listaempaque.add(empaque1);
                                    }
                                    }

                                    CalculaMontos(igvaux, indice);
                                    listaProductosVerifica.add(listadetalle);
                                    

                                }//fin del for


                            }

                            /**************************************
                             *********** Hasta aqui****************
                             **************************************/


                           
                    }// FIN DE AGREGAR LA PROMO
                    
                    
                    }else{  //Si comprar > 0

                    //    if (Integer.valueOf(VerificaFraccComprar) == fraccion){

                            // AQUI AGREGO LA FILA DE PROMOCION AL JTABLE / SI EL PRODUCTO TUVIERA --- GINO
                            if (ss == 0.00) {

                                if (existe==0){

                                    listaProductos.removeAll(listaProductos);
                                    listaProductos = objproducto.ListarProductos(idproducto, idbotica, "01", 1, 1);

                                    double pvx_aux = 0;
                                    double pv_aux =0;
                                    double desc_aux =0;
                                    BigDecimal bd_aux, bd1_aux;

                                    for (int i1 = 0; i1 < listaProductos.size(); i1++) {

                                        pv_aux = listaProductos.get(i1).getPrecio_Venta();
                                        desc_aux = listaProductos.get(i1).getDescuento_Venta();
                                        pvx_aux = pv_aux - (pv_aux * (desc_aux / 100));

                                        bd1_aux = new BigDecimal(pvx_aux);
                                        bd1_aux = bd1_aux.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                        pvx_aux = bd1_aux.doubleValue();


                                    productoPrecio = listaProductos.get(i1);
                                    productoPrecio.setPVPX(pvx_aux);
                                    espromo = true;

                                    }
                                    int regalo = fraccion / Integer.valueOf(VerificaFraccComprar); // para q se multiplique por la cantidad pedida
                                    String Regalo = "F" + Integer.valueOf(VerificaRegalar) * regalo;
                                    //String Regalo = "F"+VerificaRegalar;
                                    existe=1;
                                    AgregaProforma(Regalo, productoPrecio,idpromo,0,0,0);


                                /*----------------------------------------------------------------------*/
                                    
                                    String PromoDescripcion = "";                        
                                    String[] recuperacadenaPromo = nomproducto.split(" ");
                                    PromoDescripcion = String.valueOf(recuperacadenaPromo[0]);  //recupera el nombre de la promo

                                    double PVPx_aux = Double.valueOf(listadetalle[7].toString()); // lo traigo de arriba
                                    
                                    listadetalle = new Object[20];
                                    indice = indice + 1;
                                    String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,idpromo,0).toString().trim();
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    if (cantidad > 1) {
                                        listadetalle[1] = obj.getCodPromocion();
                                        listadetalle[2] = obj.getDescripPromocion();
                                    } else {
                                        listadetalle[1] = micodigo;
                                        listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                    }
                                    listadetalle[4] = recuperaUnidadesminimo;
                                    listadetalle[5] = 0;
                                    
                                    if (orden == 1) {
                                        montoDscto = 0.0;
                                    }

                                    if (empaque1 > 0) {
                                    parcial1 = (fraccion * PVPx_aux) / empaque1;
                                    }

                                    parcial = parcial1;

                                    BigDecimal bd3_aux = new BigDecimal(parcial);
                                    bd3_aux = bd3_aux.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    parcial = bd3_aux.doubleValue();

                                    //montoDscto = 43.01;
                                    montoDscto = parcial;

                                    montoDscto = montoDscto + montoDsctofraccion;
                                    BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                                    bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                    if (unidad > 0 && fraccion ==0){
                                            unifra = unidad;
                                    }else if (unidad == 0 && fraccion >0){
                                            //String recregalar = mantProduc.Recupera_Promo_Comprar(idproducto,idpromo,"2").toString().trim();
                                            //fraccion = Integer.parseInt(recregalar);
                                            unifra = fraccion;
                                    }
                                    BigDecimal bd4_1 = new BigDecimal((montoDscto * (-1))/unifra);
                                    bd4_1 = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    listadetalle[5] = unifra;  // gino
                                    listadetalle[6] = bd4_1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[7] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[8] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                                    /*aqui pregunto si es esgratuita*/

                                    listadetalle[9] = "1";
                                    listadetalle[10] = "1";
                                    listadetalle[11] = "0";
                                    listadetalle[12] = "0";
                                    listadetalle[13] = igvaux;
                                    esgratuita1 = objProducto.RetornaEsGratis(micodigo);
                                    if (esgratuita1.equals("1")){
                                        listadetalle[14] = montoDscto;
                                    }else{
                                        listadetalle[14] = "0";
                                    }
                                    esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto,micodigo); // promocion
                                    marcarpromo = mantProduc.MarcarPromo(micodigo).toString().trim();
                                    if (esgratuita2.equals("1")){
                                      listadetalle[15] = "1";
                                    }else{
                                      listadetalle[15] = "0";
                                    } 
                                    
                                    listadetalle[16] = marcarpromo;
                                    listadetalle[17] = "";
                                    listadetalle[18] = Ncertificado;
                                    listadetalle[19] = cantbolsas;
                                    
                                    if (unidad > 0 && orden == 1) {
                                    } else {
                                        miproducto = new Producto();
                                        miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                                        productoBotica = new Productos_Botica();
                                        productoBotica.setProducto(miproducto);
                                        productoBotica.setMostrador_Stock_Empaque(1);
                                        productoBotica.setMostrador_Stock_Fraccion(1);
                                        objetostock = new ProductosPrecios();
                                        objetostock.setProductoBotica(productoBotica);                                
                                        productostock.add(indice, objetostock);
                                        if (montoDscto != 0){
                                        tablaproforma.addRow(listadetalle);
                                        }

                                        int posi = 4;
                                        if (unidad > 0 && fraccion ==0){
                                           posi = posi;
                                           unifra = unidad;
                                        }else if (unidad == 0 && fraccion >0){
                                           posi = 5;
                                           unifra = fraccion;
                                        }

                                        tablaproforma.setValueAt(unifra, indice, posi);

                                    }

                                    CalculaMontos(igvaux, indice +1);
                                    listaProductosVerifica.add(listadetalle);
                                    existe = 0;

                                    for (int i = 0; i < listaProductosVerifica.size(); i++) {
                                        Object[] valor = new Object[1];
                                        valor[0] = listaProductosVerifica.get(i);

                                        Object[] datos = new Object[19];
                                        datos = (Object[]) valor[0];

                                        if (datos[10].toString().trim().equals("1") && datos[11].toString().trim().equals("0")) {

                                            datos[11] = ordenproducto;
                                            listaProductosVerifica.set(i, datos);
                                        }
                                    }
                                    ordenproducto = ordenproducto + 1;

                                
                                }
                            }
                        //    }


                    }




                    // FIN DE AGREGAR LA PROMO
                    


                    jTable2.requestFocus();
                    jTable2.changeSelection(indice, indice, false, false);

                    if (igvaux < 1) {
                        lisProdSinIGV.add(idproducto);
                        col = jTable2.getColumnModel().getColumn(0);
                        col.setCellRenderer(new ColoredTableCellRenderer());
                        col = jTable2.getColumnModel().getColumn(1);
                        col.setCellRenderer(new ColoredTableCellRenderer());
                    } else {
                        lisProdSinIGV.add(9999);
                    }
                }            

            } catch (Exception ex) {
                System.out.println("AGREGA PRODUCTO " + ex.getMessage());
            }
        }



        String CodProductox ="";
        String nomproductox ="";
        int contar = jTable2.getRowCount();
        for (int i = 0; i < jTable2.getRowCount(); i++) {
         CodProductox=(String)jTable2.getValueAt(contar-1, 1);
         nomproductox=(String)jTable2.getValueAt(contar-1, 2);
        }
        
        
        if (bandmultiple != 1) { 

            if (unidad > 0){

            List<ListaDetalles> countGrupoPromo = mantProduc.verificaGrupoPromocion(CodProductox);
            int countgrupos = countGrupoPromo.get(0).getnumero();
            int TG_EsMultiple = objProducto.G_EsMultipleDscto(0, CodProductox,1);

            if(countgrupos > 1 ){
                
                if (TG_EsMultiple == 1){   //TG MULTIPLE
                
                if (unidad > 1){
                
                    ProductosPromociones obj1 = null;
                    obj1 = new ProductosPromociones(objetoventana, nomproductox, CodProductox);
                    obj1.pack();
                    obj1.setVisible(true);
                    String cant = obj1.getDescripPromocion();

                    String NuevocodpromoCantidad = mantProduc.NuevocodigoPromocion(CodProductox).toString().trim();

                    String[] arr  = NuevocodpromoCantidad.split(",");  // declaro el array
                    String Nuevocodpromodouble = arr[0];
                    String Cantidad = arr[1];

                    listaProductos.removeAll(listaProductos);
                    listaProductos = objproducto.ListarProductos(Nuevocodpromodouble, idbotica, "01", 1, 1);

                    double pvx = 0;
                    BigDecimal bd, bd1;

                    //for (ProductosPrecios precio : listaProductos) {
                    for (int i = 0; i < listaProductos.size(); i++) {

                        pv = listaProductos.get(i).getPrecio_Venta();
                        desc = listaProductos.get(i).getDescuento_Venta();
                        pvx = pv - (pv * (desc / 100));

                        bd1 = new BigDecimal(pvx);
                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        pvx = bd1.doubleValue();


                    productoPrecio = listaProductos.get(i);
                    productoPrecio.setPVPX(pvx);
                    espromo = true;

                    }
                    if (Cantidad.equals("0")){
                        Cantidad= "1";
                    }

                    unidad = unidad;
                    if (Integer.valueOf(unidad) >1){
                        Double Cant = Double.valueOf(Cantidad) * unidad;
                        int x = (int)Cant.doubleValue();
                        Cantidad = String.valueOf(x);
                    }

                    AgregaProforma(Cantidad, productoPrecio,0,0,0,0);

                }
            }else{   //FIN TG MULTIPLE
                    
                    ProductosPromociones obj1 = null;
                    obj1 = new ProductosPromociones(objetoventana, nomproductox, CodProductox);
                    obj1.pack();
                    obj1.setVisible(true);
                    String cant = obj1.getDescripPromocion();

                    String NuevocodpromoCantidad = mantProduc.NuevocodigoPromocion(CodProductox).toString().trim();

                    String[] arr  = NuevocodpromoCantidad.split(",");  // declaro el array
                    String Nuevocodpromodouble = arr[0];
                    String Cantidad = arr[1];

                    listaProductos.removeAll(listaProductos);
                    listaProductos = objproducto.ListarProductos(Nuevocodpromodouble, idbotica, "01", 1, 1);

                    double pvx = 0;
                    BigDecimal bd, bd1;

                    //for (ProductosPrecios precio : listaProductos) {
                    for (int i = 0; i < listaProductos.size(); i++) {

                        pv = listaProductos.get(i).getPrecio_Venta();
                        desc = listaProductos.get(i).getDescuento_Venta();
                        porpreciofijo = objProducto.Verifica_Promo_PorPrecio(CodProductox, 6, idpromo);
                        if (porpreciofijo==1){
                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(CodProductox,1); //* unidad;
                            pvx = dsctoporprecio;
                        }else{
                            pvx = pv - (pv * (desc / 100));
                        }

                        bd1 = new BigDecimal(pvx);
                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        pvx = bd1.doubleValue();


                    productoPrecio = listaProductos.get(i);
                    productoPrecio.setPVPX(pvx);
                    espromo = true;

                    }
                    if (Cantidad.equals("0")){
                        Cantidad= "1";
                    }

                    unidad = unidad;
                    if (Integer.valueOf(unidad) >1){
                        Double Cant = Double.valueOf(Cantidad) * unidad;
                        int x = (int)Cant.doubleValue();
                        Cantidad = String.valueOf(x);
                    }
                    
                    int idprom1 = obj1.getIdpromo();
                    int Mult_Dcto = objProducto.G_EsMultipleDscto(idprom1, CodProductox,2);
                        
                    if (Mult_Dcto == 1){
                        if (porpreciofijo==1){
                            AgregaProforma(Cantidad, productoPrecio, 0,1,1,1);
                        }else{
                            AgregaProforma(Cantidad, productoPrecio, 0,1,1,0);                    
                        }
                    }else{
                        AgregaProforma(Cantidad, productoPrecio, 0,0,0,0); // 0,0 al final
                    }
                    
                }
                    
            }
            }

        }else{ // multiple 1
            
            if (unidad > 0) {
            
                ProductosPromocionesMultiple objPromMultiple = null;
                String idprod = objproductos.getProductoPrecio().getProductoBotica().getProducto().getIdProducto();
                String Nomprod = objproductos.getProductoPrecio().getProductoBotica().getProducto().getDescripcion();
                
                
                int verificargrupoparamostrar = objProducto.MostrarPantallaMultiple(idprod, 0);                
                if (verificargrupoparamostrar == 1){ 
                
                    objPromMultiple = new ProductosPromocionesMultiple (objetoventana, Nomprod, idprod);
                    objPromMultiple.pack();
                    objPromMultiple.setVisible(true);

                    int idprom1 = objPromMultiple.getIdpromo();
                    String nuevocodMultiple = objPromMultiple.getCodPromocion();


                    String Nuevocodpromodouble = nuevocodMultiple;
                    String Cantidad = "1";

                    listaProductos.removeAll(listaProductos);
                    listaProductos = objproducto.ListarProductos(Nuevocodpromodouble, id_botica, "01", 1, 1);

                    double pvx = 0;
                    BigDecimal bd, bd1;


                    for (int i = 0; i < listaProductos.size(); i++) {

                        pv = listaProductos.get(i).getPrecio_Venta();
                        desc = listaProductos.get(i).getDescuento_Venta();
                        pvx = pv - (pv * (desc / 100));

                        bd1 = new BigDecimal(pvx);
                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        pvx = bd1.doubleValue();

                        productoPrecio = listaProductos.get(i);
                        productoPrecio.setPVPX(pvx);
                        espromo = true;

                    }

                    if (Cantidad.equals("0")) {
                        Cantidad = "1";
                    }
                    unidad = unidad;
                    if (Integer.valueOf(unidad) > 1) {
                        Double Cant = Double.valueOf(Cantidad) * unidad;
                        int x = (int) Cant.doubleValue();
                        Cantidad = String.valueOf(x);
                    }
                    
                    int G_EsMultipleDscto = objProducto.G_EsMultipleDscto(idprom1, CodProductox,2);
                    if (G_EsMultipleDscto == 1){
                    EsMultiDcto = 1;
                    }
                    
                    AgregaProforma(Cantidad, productoPrecio, idprom1,0,EsMultiDcto,0);
                
            
                }
            }
        
        }

    }


    public boolean VerificarSiEstaTablaONotieneProdAsocTabla(String CodProPromo, int[] GruposPromoGrat){//CODIGO_MAX verifico si el cod dela promo ya esta en jtable2 antes de entrar aqui verificar si la promo estaen dos grupo promociones
        for (int i = 0; i < jTable2.getRowCount(); i++) {//entro a este metodo si el codigo de la promo es gratuita y esta en dos grupos
            String CodProducto=(String)jTable2.getValueAt(i, 1);
            if(CodProPromo.equals(CodProducto))
                return true;
            }
         for (int i = 0; i < jTable2.getRowCount(); i++) {
             String CodProducto=(String)jTable2.getValueAt(i, 1);
             int temp[]=objProducto.PromoEstaEnDosGrupos(CodProducto,0);
             for (int j = 1; j < GruposPromoGrat.length; j++) {//recorro desde 1 porque n la pso 0 esta el num de grupos de la promo
                    if(temp[1]==GruposPromoGrat[j])
                        return false;
                }
            }

        return true;
    }

    private void AsignaPrecioPromocion(ProductosPrecios precios, int var) {

        double parcial1 = 0;
        double ss = 0;
        double montoDscto = 0;

        int empaque1 = Integer.parseInt(empaque.toString());
        Producto miproducto;
        Productos_Botica productoBotica;

        if (empaque1 == 0 && fraccion > 0) {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                Id_Tipo_Precio = precios.getTipoPrecio().getId_Tipo_Precio();
                String nomproducto = precios.getProductoBotica().getProducto().getDescripcion();
                String idproducto = precios.getProductoBotica().getProducto().getIdProducto();
                double pv = precios.getPrecio_Venta();
                cantidadproductos = jTable2.getRowCount();
                int indice = cantidadproductos;
                cantidadproductos++;

                miproducto = new Producto();
                miproducto.setEmpaque(empaque1);
                productoBotica = new Productos_Botica();
                productoBotica.setProducto(miproducto);
                productoBotica.setMostrador_Stock_Empaque(precios.getProductoBotica().getMostrador_Stock_Empaque());
                productoBotica.setMostrador_Stock_Fraccion(precios.getProductoBotica().getMostrador_Stock_Fraccion());
                objetostock = new ProductosPrecios();
                objetostock.setProductoBotica(productoBotica);

                productostock.add(indice, objetostock);
                Double montoDsctofraccion = 0.00;
                Double descuento2 = 0.00;
                Double descuento3 = 1.00;
                Integer recuperaUnidadesminimo = 0;
                int orden = 0;
                int cantidad = 0;
                ProductosPromociones obj = null;

                //if (BuscarProductos.isEspromo()) {
                if (var == 2) {
                    cantidad = objProducto.Verifica_PromocionPrecio(idproducto);
                    List<ListaDetalles> listPromocion = mantProduc.verificaPromocionPrecio(idproducto);

                    if (cantidad > 1) {
                        descuento2 = armarPromocion(listPromocion, idproducto, unidad,0,0);
                        recuperaUnidadesminimo = 1;
                    } else {
                        orden = listPromocion.get(0).getOrden();
                        //descuento2 = armarPromocion(listPromocion, idproducto, unidad);
                        descuento2 = descuento2;
                        recuperaUnidadesminimo = retornaUnidades(listPromocion, unidad);
                    }
                }

                listadetalle[0] = cantidadproductos;
                listadetalle[1] = idproducto;
                listadetalle[2] = nomproducto;
                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                listadetalle[4] = unidad;
                listadetalle[5] = fraccion;
                listadetalle[6] = pv;


                double desc = precios.getDescuento_Venta();
                BigDecimal bd8 = new BigDecimal(desc);
                bd8 = bd8.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                desc = bd8.doubleValue();

                descuento = Double.parseDouble(listadetalle[6].toString());
                PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                parcial = PVPx * unidad;

                if (empaque1 > 0) {
                    parcial1 = (fraccion * PVPx) / empaque1;
                }

                if (var == 2) {
                    parcial = (parcial + parcial1) * (-1);
                } else {
                    parcial = parcial + parcial1;
                }

                /********************************************
                CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                 *********************************************/
                BigDecimal bd = new BigDecimal(PVPx);
                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);


                if (var == 2) {

                    PVPx = bd.doubleValue();
                    bdpromo = (bd.doubleValue()) * (-1);
                    BigDecimal bd4 = new BigDecimal(PVPx * (-1));
                    bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    String micodigo = mantProduc.Recupera_Promo_CodigoPrecio().toString().trim();
                    String[] recuperacadena = micodigo.split("@");
                    listadetalle[1] = recuperacadena[0]; //codigo promocion
                    listadetalle[2] = mantProduc.Recupera_Promo_NombrePrecio(recuperacadena[0]);
                    listadetalle[6] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();//pv * (-1);
                    listadetalle[7] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[8] = bdpromo;

                } else {
                    PVPx = bd.doubleValue();
                    //}

                    BigDecimal bd1 = new BigDecimal(parcial);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    parcial = bd1.doubleValue();

                    ss = Double.valueOf(descuento2);
                    descuento = ss;
                    montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                    listadetalle[7] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[8] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                }
                if (listadetalle[1].toString() != null) {
                    tablaproforma.addRow(listadetalle);
                }

                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                CalculaMontos(igvaux, indice);

                if (ss > 0.00) {

                    indice = indice + 1;
                    String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto,0,0).toString().trim();
                    listadetalle[0] = jTable2.getRowCount() + 1;
                    if (cantidad > 1) {
                        listadetalle[1] = obj.getCodPromocion();
                        listadetalle[2] = obj.getDescripPromocion();
                    } else {
                        listadetalle[1] = micodigo;
                        listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                    }
                    listadetalle[4] = recuperaUnidadesminimo;
                    listadetalle[5] = 0;

                    if (fraccion > 0) {
                        montoDsctofraccion = (orden * PVPx) / empaque1;
                    }

                    if (orden == 1) {
                        montoDscto = 0.0;
                    }

                    montoDscto = montoDscto + montoDsctofraccion;
                    BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                    bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                    listadetalle[6] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[7] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    listadetalle[8] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                    if (unidad > 0 && orden == 1) {
                    } else {
                        miproducto = new Producto();
                        miproducto.setEmpaque(mantProduc.Recupera_Empaque(micodigo));
                        productoBotica = new Productos_Botica();
                        productoBotica.setProducto(miproducto);
                        productoBotica.setMostrador_Stock_Empaque(1);
                        productoBotica.setMostrador_Stock_Fraccion(1);
                        objetostock = new ProductosPrecios();
                        objetostock.setProductoBotica(productoBotica);
                        productostock.add(indice, objetostock);
                        tablaproforma.addRow(listadetalle);
                        tablaproforma.setValueAt(1, indice, 4);
                    }

                    CalculaMontos(igvaux, indice);
                }

                jTable2.requestFocus();
                jTable2.changeSelection(indice, indice, false, false);

                if (igvaux < 1) {
                    lisProdSinIGV.add(idproducto);
                    col = jTable2.getColumnModel().getColumn(0);
                    col.setCellRenderer(new ColoredTableCellRenderer());
                    col = jTable2.getColumnModel().getColumn(1);
                    col.setCellRenderer(new ColoredTableCellRenderer());
                } else {
                    lisProdSinIGV.add(9999);
                }

            } catch (Exception ex) {
                System.out.println("AGREGA PRODUCTO" + ex.getMessage());
            }
        }
    }

    private Double armarPromocion(List<ListaDetalles> verificaPromocion, String CodProducto, Integer Cantidad, int idprom, int bndmulti) {

        Integer existePromocion = 0;
        Double Descuento = 0.00;

        if (verificaPromocion.get(0).getIdPromo() > 0) {

            for (Integer g = 0; g < nuevaPromocion.size(); g++) {
                if (nuevaPromocion.get(g).equals(verificaPromocion.get(0).getIdPromo())) {
                    existePromocion = 1;
                }
            }

            if (existePromocion == 0) {
                //Si no existe la promocion la agrego a mi actual Lista de Promociones
                nuevaPromocion.add(verificaPromocion.get(0).getIdPromo());
                cantidadElementos.add(verificaPromocion.get(0).getTotalPromocionales());
                DetallePromo.add(
                        new ListaDetalles(
                        verificaPromocion.get(0).getIdPromo(),
                        verificaPromocion.get(0).getTotalPromocionales(),
                        verificaPromocion.get(0).getDescuento(),
                        CodProducto,
                        Cantidad,
                        1));

                Descuento = objProducto.Recupera_Dscto(CodProducto, 0,idprom,bndmulti);

            } else {
                //En caso exista empiezo a agregarlo a los elementos
                Integer t = 1;                
                int temp=0;//MAX_CODIGO para controlar las promociones puedan ser mas de 2

                //if (verificaPromocion.get(0).getTotalPromocionales() == 3){         //gino /*
                if (verificaPromocion.get(0).getTotalPromocionales() >= 3){         //gino /*           //nuevo promo para 4
                    DetallePromo.add(
                                new ListaDetalles(
                                verificaPromocion.get(0).getIdPromo(),
                                verificaPromocion.get(0).getTotalPromocionales(),
                                verificaPromocion.get(0).getDescuento(),
                                CodProducto,
                                Cantidad,
                                t));

                    contarpromo = contarpromo + t;
                    for (Integer con = 0; con < DetallePromo.size(); con++) {
                        if (DetallePromo.get(con).getIdPromo() == verificaPromocion.get(0).getIdPromo()) {
                            temp=temp+1;//MAX_CODIGO
                            if(temp==verificaPromocion.get(0).getTotalPromocionales())//MAX_CODIGO
                                t++;
                        }
                    }
                    if (verificaPromocion.get(0).getTotalPromocionales() < 3) {
                        t = t + 1;
                    }else if (verificaPromocion.get(0).getTotalPromocionales() ==4) {                     //nuevo promo para 4
                        t = t + 1;                                                                        //nuevo promo para 4
                    }                                                                                     //nuevo promo para 4

                    if (verificaPromocion.get(0).getTotalPromocionales() == 3 && contarpromo == 2) {
                        t = 2;

                    }else if (verificaPromocion.get(0).getTotalPromocionales() == 4 && contarpromo == 3) { //nuevo promo para 4            
                        t = 3;                                                                             //nuevo promo para 4 
                    }else{                                                                                 //nuevo promo para 4
                        t = t - 1;
                    }

                    Descuento = objProducto.Recupera_Dscto(CodProducto, t,idprom,bndmulti);

                }else{                                                             // hasta aqui gino*/

                    for (Integer con = 0; con < DetallePromo.size(); con++) {
                        if (DetallePromo.get(con).getIdPromo() == verificaPromocion.get(0).getIdPromo()) {
                            temp=temp+1;//MAX_CODIGO
                            if(temp==verificaPromocion.get(0).getTotalPromocionales())//MAX_CODIGO
                                t++;
                        }
                    }

                    if (verificaPromocion.get(0).getTotalPromocionales() < 100) {
                        t = t + 1;
                    }

                    //if (t <= verificaPromocion.get(0).getTotalPromocionales()) {
                        DetallePromo.add(
                                new ListaDetalles(
                                verificaPromocion.get(0).getIdPromo(),
                                verificaPromocion.get(0).getTotalPromocionales(),
                                verificaPromocion.get(0).getDescuento(),
                                CodProducto,
                                Cantidad,
                                t));

                    
                    
                        Descuento = objProducto.Recupera_Dscto(CodProducto, t - 1,idprom,bndmulti);

                    /*} else {
                        JOptionPane.showMessageDialog(this, "Se completo los cupos de esta promocion, genere otra venta por fv", "Promocion", JOptionPane.ERROR_MESSAGE);
                    }*/
                }
            }
        }

        return Descuento;

    }

    private Integer retornaUnidades(List<ListaDetalles> verificaPromocion, int unidad) {
        Integer minimo = 0;

        for (Integer con = 0; con < DetallePromo.size(); con++) {
            if (DetallePromo.get(con).getIdPromo() == verificaPromocion.get(0).getIdPromo()) {//Verifico si es la promo q me interesa
                if (DetallePromo.get(con).getCantidadPro() < unidad) {
                    minimo = DetallePromo.get(con).getCantidadPro();
                } else {
                    minimo = unidad;
                }
            }
        }

        return minimo;
    }

    /*******************************************
    METODO QUE CALCULA EL MONTO TOTAL  A PAGAR
     ********************************************/
    private void CalculaMontos(double band_igv, int fila) {
        double auxparcial = 0.0;
        //total = 0;
        //SubTotal = 0;
        OpInafecta = 0.0;
        //OpGravada = 0.0;
        OpDescuentoTotal = 0.0;
        jTextField44.setText(""); 
        jTextField45.setText("");
        jTextField21.setText("");
        //OpDescuento = 0.0;
        
        double valortabla = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 8)));
        String c_prod =  String.valueOf(jTable2.getValueAt(fila, 1));    
        double c_cantU =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 4)));
        double c_cantF =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 5)));
        
        double Monto_icbpre = objProducto.PrecioICBPER(c_prod);        
        double empa=objProducto.PrecioICBPEREMP(c_prod);
               
        double c_cantT = c_cantU * empa + c_cantF;
        double Monto_icbpre_Cab  = Monto_icbpre * c_cantT; 

        if (band_igv == 0) {
            total += valortabla + Monto_icbpre_Cab;
            total_pigv += valortabla;
            SubTotal += valortabla;
            
            OpGravada += valortabla;
            OpInafecta +=valortabla;


            objsubtotalIgv = new ProductosPrecios(valortabla, band_igv);
            listsubtotales.add(fila, objsubtotalIgv);
            
            /*total += valortabla;
            SubTotal += valortabla;            
            OpGravada += SubTotal;
            OpGravada = OpGravada - valortabla;
            SubTotal = OpGravada;
            OpInafecta +=valortabla;
            objsubtotalIgv = new ProductosPrecios(valortabla, band_igv);
            listsubtotales.add(fila, objsubtotalIgv);*/
            
        } else {
            if (IGV == 0) {
                CapturaIGV();
            }           
            
            total += valortabla + Monto_icbpre_Cab;
            total_pigv += valortabla;
            
            auxparcial = (valortabla / (1 + (IGV / 100)));

            objsubtotalIgv = new ProductosPrecios(auxparcial, band_igv);
            listsubtotales.add(fila, objsubtotalIgv);
            SubTotal += auxparcial;
            
            OpGravada += auxparcial;
            /*if (total > 0){
            OpGravada += auxparcial;
            }else{
            OpGravada = 0.0;
            }*/

            

        }

        if(valortabla < 0){
            //OpDescuento = valortabla;
            OpDescuentoTotal += valortabla;
        }

        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(10, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(10, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();
        
        BigDecimal bdtpigv = new BigDecimal(total_pigv);
        bdtpigv = bdtpigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total_pigv = bdtpigv.doubleValue();

        if (!listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getTPredondeo().equals("1")) { //aqui redondeo


            this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            double igvauxiliar = total - SubTotal;// - Monto_icbpre_Cab;
            
            miigv_x = total_pigv - SubTotal;

            BigDecimal bd3 = new BigDecimal(igvauxiliar);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            igvauxiliar = bd3.doubleValue();
            
            BigDecimal bd3_x = new BigDecimal(miigv_x);
            bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            miigv_x = bd3_x.doubleValue();

            this.jTextField9.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                jTextField44.setText("0.00"); //redondeo
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                jTextField44.setText("0.00"); //redondeo
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }

            jTextField44.setText("0.00"); //redondeo
            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

        }else{

        //AQUI
        String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        //int deci = Integer.parseInt(arr[1]);
        String deci = arr[1];

        //String x = Integer.toString(deci);
        String x = deci;
        int contardeci = x.length();

        if (contardeci > 1){

        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
        String ultimodigito = String.valueOf(ultimoNumero);

            //if (ultimodigito.equals("0") || ultimodigito.equals("5")){
            if (ultimodigito.equals("0")){
                jTextField44.setText("0.00"); //redondeo
                jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                double igvauxiliar = total - SubTotal;// - Monto_icbpre_Cab;
                
                miigv_x = total_pigv - SubTotal;

                BigDecimal bd3 = new BigDecimal(igvauxiliar);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                igvauxiliar = bd3.doubleValue();
                
                BigDecimal bd3_x = new BigDecimal(miigv_x);
                bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                miigv_x = bd3_x.doubleValue();

                jTextField9.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal

                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                    this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }
                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                    this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }


            }else{
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    double igvauxiliar = total - SubTotal;// - Monto_icbpre_Cab;
                    
                    miigv_x = total_pigv - SubTotal;

                    BigDecimal bd3 = new BigDecimal(igvauxiliar);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    igvauxiliar = bd3.doubleValue();
                    
                    BigDecimal bd3_x = new BigDecimal(miigv_x);
                    bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    miigv_x = bd3_x.doubleValue();

                    jTextField9.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                    BigDecimal bdNT1 = new BigDecimal(nuevototal);
                    bdNT1 = bdNT1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                        this.jTextField17.setText(bdNT1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }
                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                        this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                }else{

                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                        //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                        double ultimonuevo = Double.valueOf(ultimodigito);
                        Double redondeo = (ultimonuevo /100) * -1;
                        Double nuevototal = total - (ultimonuevo /100);
                        //total = nuevototal;
                        //SubTotal = (total / (1 + (IGV / 100)));
                        BigDecimal bdNT = new BigDecimal(nuevototal);
                        bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        nuevototal = bdNT.doubleValue();


                        jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                        jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                        double igvauxiliar = total - SubTotal;// - Monto_icbpre_Cab;

                        miigv_x = total_pigv - SubTotal;
                        
                        BigDecimal bd3 = new BigDecimal(igvauxiliar);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        igvauxiliar = bd3.doubleValue();

                        BigDecimal bd3_x = new BigDecimal(miigv_x);
                        bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        miigv_x = bd3_x.doubleValue();
                    
                        jTextField9.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                        jTextField44.setText(String.valueOf(redondeo)); //redondeo
                        jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                        BigDecimal bdNT1 = new BigDecimal(nuevototal);
                        bdNT1 = bdNT1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                        if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                            this.jTextField17.setText(bdNT1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }
                        if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                            this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }

                    }

                }
            }
        }else{

            this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
            double igvauxiliar = total - SubTotal;// - Monto_icbpre_Cab;

            miigv_x = total_pigv - SubTotal;
            
            BigDecimal bd3 = new BigDecimal(igvauxiliar);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            igvauxiliar = bd3.doubleValue();
            
            BigDecimal bd3_x = new BigDecimal(miigv_x);
            bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            miigv_x = bd3_x.doubleValue();

            this.jTextField9.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv
            jTextField44.setText("0.00");                                                                    //redondeo
            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar

            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
               this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
               this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }

        }

        }
    }

    private void BuscaTipoPrecio(ProductosPrecios precios,int idpromo, int bandmultiple,int EsMultiDcto, int precfijo,String Ncertificado) {

        if (VerificaStock(precios.getProductoBotica().getProducto().getIdProducto())) {
            AsignaPrecio(precios,idpromo,bandmultiple,EsMultiDcto,precfijo,Ncertificado);
            if (comboboxdescuento.getSelectedIndex() > 0 && veces > 1) {
                EliminaFilaDescuento();
            }
        } else {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY STOCK PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
            BusquedaProducto();
        }
    }

    private void BuscaTipoPrecioPromocion(ProductosPrecios precios, int var) {

        if (VerificaStock(precios.getProductoBotica().getProducto().getIdProducto())) {
            AsignaPrecioPromocion(precios, var);
            if (comboboxdescuento.getSelectedIndex() > 0 && veces > 1) {
                EliminaFilaDescuento();
            }
        } else {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY STOCK PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
            BusquedaProducto();
        }
    }
    /*
     * METODO QUE CALCULA SI LO INGRESADO ES UNIDAD Y FRACCION
     */

    private boolean VerificaCantidad(String cantidad) {
        String unidad1 = "";
        String fraccion1 = "";
        int k = 0;
        boolean segundo = false;
        char car = 'F';
        int cantdef = 0;

        Character w;
        String valor = "";


        for (int i = 0; i < cantidad.length(); i++) {
            w = cantidad.charAt(i);
            if (w.isDigit(w) || w.isLetter(w)) {
                valor = valor + w;
            } else {
                return false;
            }

        }

        for (int i = 0; i < valor.length(); i++) {
            Character caracter = valor.charAt(i);

            if (caracter.isLetter(caracter)) {
                caracter = caracter.toUpperCase(caracter);
                cantdef++;
                if (caracter != car || cantdef > 1) {
                    return false;
                }

            }
            if (caracter.isDigit(caracter) && segundo == false) {
                unidad1 = unidad1 + caracter;
            } else {
                segundo = true;
                if (k == 1) {
                    if (caracter.isDigit(caracter)) {
                        fraccion1 = fraccion1 + caracter;
                    }
                }
                k = 1;
            }
        }

        if (unidad1.compareTo("") == 0) {
            unidad1 = "0";
        }
        if (fraccion1.compareTo("") == 0) {
            fraccion1 = "0";
        }

        unidad = Integer.valueOf(unidad1);
        fraccion = Integer.valueOf(fraccion1);
        return true;
    }

    private void CompletaTiposPagos() {

        int w = comboboxtipodepago.getItemCount();

        if (w == 0) {
            listatipospagos = objfactura.Recupera_Tipos_Pagos(0);
            for (int i = 0; i < listatipospagos.size(); i++) {
                String descrp = listatipospagos.get(i).getId_TipoPago() + " " + listatipospagos.get(i).getDescripcion();
                //String descrp = listatipospagos.get(i).getDescripcion();
                comboboxtipodepago.addItem(descrp);
            }
        }

        /*listatipospagos = new ArrayList<TiposPagos>();
        listatipospagos = objfactura.Recupera_Tipos_Pagos(0);

        for (int i = 0; i < listatipospagos.size(); i++) {
            jComboBox1.addItem(listatipospagos.get(i).getDescripcion());
        }*/
    }

    private void CompletaTiposPago1() {

        int w = comboboxtipodepago.getItemCount();

        if (w == 0) {
            listatipospagos = objfactura.Recupera_Tipos_Pagos(0);
            for (int i = 0; i < listatipospagos.size(); i++) {
                String descrp = listatipospagos.get(i).getId_TipoPago() + " " + listatipospagos.get(i).getDescripcion();
                comboboxtipodepago.addItem(descrp);
            }
        }
    }

    public void MuestraClientes(NuevoCliente obj, int id_cliente) {

        jComboBox8.addItem(obj.getObjcliente().getDireccion());
        txtdni.setText(obj.getObjcliente().getDNI());
        txtcliente.setText(obj.getObjcliente().getNombre_RazonSocial());
        txtruc.setText(obj.getObjcliente().getRUC_DNI());
        idcliente = id_cliente;
        jTextField2.setText(String.valueOf(idcliente));
        txtcliente.requestFocus();

    }

    private void ModificaDatosCliente() {
        txtcliente.setRequestFocusEnabled(true);
        txtcliente.requestFocus();
        txtcliente.setRequestFocusEnabled(true);
        txtcliente.requestFocus();
    }

    private void ModificaMontos(Double valanterior, Double auxparcial, String codpro, int fila) {
        double aux = mantProduc.recupera_Igv_Exonerado(codpro);

        total = 0;

        OpInafecta = 0.0;
        OpGravada = 0.0;
        OpDescuentoTotal = 0.0;

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            total += Double.parseDouble(String.valueOf(jTable2.getValueAt(i, 8)));
        }


        if (aux > 0) //SI TIENE IGV
        {
            CapturaIGV();
            double auxparcial1 = (valanterior / (1 + (IGV / 100)));
            SubTotal = SubTotal - auxparcial1;

            double auxparcial2 = (auxparcial / (1 + (IGV / 100)));
            SubTotal = SubTotal + auxparcial2;

            objsubtotalIgv = new ProductosPrecios(auxparcial2, aux);
            listsubtotales.set(fila, objsubtotalIgv);

            if (total > 0){
            OpGravada += SubTotal;
            }else{
            OpGravada = 0.0;
            }
            //CalculaMontos(aux, fila);
        } else {
            SubTotal = SubTotal - valanterior;
            SubTotal = SubTotal + auxparcial;

            SubTotal += total;
            OpGravada += SubTotal;
            OpGravada = OpGravada - total;

            SubTotal = OpGravada;
            OpInafecta +=total;

            objsubtotalIgv = new ProductosPrecios(auxparcial, aux);
            listsubtotales.set(fila, objsubtotalIgv);
            //CalculaMontos(aux, fila);

        }

        if(total < 0){
            //OpDescuento = valortabla;
            OpDescuentoTotal += total;
        }
        
        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();

        /*jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField9.setText(String.valueOf(IGV));
        jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

        if (listatipospagos.get(this.jComboBox1.getSelectedIndex()).getId_TipoPago() == 1) {
            jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        }*/

        //AQUI
        String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        //int deci = Integer.parseInt(arr[1]);
        String deci = arr[1];

        //String x = Integer.toString(deci);
        String x = deci;
        int contardeci = x.length();

        if (contardeci > 1){

        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
        String ultimodigito = String.valueOf(ultimoNumero);

            //if (ultimodigito.equals("0") || ultimodigito.equals("5")){
            if (ultimodigito.equals("0")){
                jTextField44.setText("0.0"); //redondeo
                jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                auxigv = (total - SubTotal);
                //auxigv = IGV;// (total - SubTotal);

                BigDecimal bd3 = new BigDecimal(auxigv);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                auxigv = bd3.doubleValue();

                jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total final

                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                    jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }

            }else{
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    auxigv = total - SubTotal;
                    //auxigv = IGV;

                    BigDecimal bd3 = new BigDecimal(auxigv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxigv = bd3.doubleValue();

                    jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                        jTextField17.setText(bdNT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                }else{

                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                    //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                    double ultimonuevo = Double.valueOf(ultimodigito);
                    Double redondeo = (ultimonuevo /100) * -1;
                    Double nuevototal = total - (ultimonuevo /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();


                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    auxigv = total - SubTotal;
                    //auxigv = IGV;

                    BigDecimal bd3 = new BigDecimal(auxigv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxigv = bd3.doubleValue();

                    jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                        jTextField17.setText(bdNT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                    }

                }
            }
        }else{

            jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
            auxigv = total - SubTotal;
            //auxigv = IGV;

            BigDecimal bd3 = new BigDecimal(auxigv);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            auxigv = bd3.doubleValue();

            jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
            jTextField44.setText("0.0");                                                                    //redondeo
            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar

            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
               jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
               jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
        }

    }

    private void ModificaCantidadPedida(int fila) {
        double anterior = 0;
        double parcial1 = 0;

        try {

            Laboratorios laboratorio = new Laboratorios();
            laboratorio.setId_Lab(jTable2.getValueAt(fila, 3).toString().trim());
            Producto miproducto = new Producto();
            miproducto.setLaboratorio(laboratorio);
            String codpro = jTable2.getValueAt(fila, 1).toString().trim();
            miproducto.setIdProducto(codpro);
            miproducto.setDescripcion(jTable2.getValueAt(fila, 2).toString().trim());
            int prodblister = mantProduc.Recupera_EsBlister(codpro,1);
            miproducto.setBlister(prodblister); 
            Productos_Botica productoBotica = new Productos_Botica();
            productoBotica.setProducto(miproducto);
            ProductosPrecios precios = new ProductosPrecios();
            precios.setPVPX(Double.parseDouble(jTable2.getValueAt(fila, 7).toString().trim()));
            precios.setprecbotiquin(Double.parseDouble(jTable2.getValueAt(fila, 7).toString().trim()));
            precios.setProductoBotica(productoBotica);
            int empaque2 = mantProduc.Recupera_Empaque(codpro);            
            
            precios.getProductoBotica().getProducto().setEmpaque(empaque2);
            new ProductoPedido(precios,1).setVisible(true);
            if (ProductoPedido.ingresadet) {
                String cantidad = ProductoPedido.getCantidad();
                if (cantidad != null) {
                    boolean resul = VerificaCantidad(cantidad);
                    int Esbotiquin = mantProduc.Recupera_EsBotiquin(codpro,1);

                    if (resul) {
                        if (unidad == 0 && fraccion == 0) {
                            JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (VerificaStock(String.valueOf(jTable2.getValueAt(this.jTable2.getSelectedRow(), 1)))) {

                                if (productostock.get(fila).getProductoBotica().getProducto().getEmpaque() == 0 && fraccion > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {

                                    jTable2.setValueAt(unidad, fila, 4);
                                    jTable2.setValueAt(fraccion, fila, 5);

                                    anterior = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 8)));
                                    
                                    if (prodblister > 0) {
                                        if (Esbotiquin == 1) {
                                            if (unidad > 0 && fraccion == 0) {
                                                PVP = mantProduc.recupera_PrecBotiquin(codpro,3); 
                                                PVPx = mantProduc.recupera_PrecBotiquin(codpro,3);
                                            } else if (unidad == 0 && fraccion > 0) {                                                
                                                PVP = objproducto.Recupera_PrecioVenta(codpro,7);
                                                PVPx = objproducto.Recupera_PrecioVenta(codpro,7);
                                            }
                                        }else{

                                            if (unidad > 0 && fraccion == 0) {
                                                PVP = mantProduc.recupera_PrecBotiquin(codpro,2); //3 
                                                PVPx = mantProduc.recupera_PrecBotiquin(codpro,2); //3
                                            }else{
                                                PVP = objproducto.Recupera_PrecioVenta(codpro,7);
                                                PVPx = PVP;
                                            }
                                        }
                                            
                                    }else{                                   
                                    
                                        if (Esbotiquin == 1 ){
                                            if (unidad == 0 && fraccion > 0) {
                                                PVP = mantProduc.recupera_PrecBotiquin(codpro,2);  
                                                PVPx = mantProduc.recupera_PrecBotiquin(codpro,2);
                                            }else{
                                                if (unidad > 0 && fraccion == 0) {
                                                    PVP = mantProduc.recupera_PrecBotiquin(codpro,3); 
                                                    PVPx = mantProduc.recupera_PrecBotiquin(codpro,3);
                                                }                                                
                                            }   
                                            
                                        }else{
                                            PVP = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 6)));
                                            PVPx = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 7)));
                                        }
                                    
                                    }
                                    
                                    //PVP = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 6)));
                                    unidad = Integer.valueOf(String.valueOf(this.jTable2.getValueAt(fila, 4)));
                                    fraccion = Integer.valueOf(String.valueOf(this.jTable2.getValueAt(fila, 5)));
                                    //PVPx = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 7)));

                                    parcial = PVPx * unidad;

                                    int empaque1 = productostock.get(fila).getProductoBotica().getProducto().getEmpaque();

                                    if (empaque1 > 0) {
                                        parcial1 = (fraccion * PVPx) / empaque1;
                                        
                                        if(prodblister > 0){
                                            if (unidad > 0 && fraccion == 0) {
                                                parcial1 = (fraccion * PVPx) / empaque1;
                                            }else{
                                                parcial1 = (fraccion * PVPx) / prodblister; 
                                            }

                                        }
                                    }

                                    parcial = parcial + parcial1;


                                    /********************************************
                                    CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                     *********************************************/
                                    BigDecimal bd = new BigDecimal(PVPx);
                                    bd = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                    PVPx = bd.doubleValue();

                                    BigDecimal bd1 = new BigDecimal(parcial);
                                    bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                    parcial = bd1.doubleValue();

                                    jTable2.setValueAt(bd.setScale(podecimalPantalla).toPlainString(), fila, 7);
                                    jTable2.setValueAt(bd1.setScale(podecimalPantalla).toPlainString(), fila, 8);

                                    Double val = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));
                                    ModificaMontos(anterior, val, String.valueOf(this.jTable2.getValueAt(fila, 1)), fila);

                                }

                            } else {
                                JOptionPane.showMessageDialog(this, " NO HAYSTOCK PARA LA CANTIDAD INGRESADA ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    } //cierre de resul boolean
                    else {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR DATOS CORRECTOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR EN EL METODO CAPA VISTA METODO MODIFICACANTIDAD" + ex.toString());
        }

    }

    private boolean Verifica_Productos_Afectos() {
        boolean resul = true;
        int id = comboboxdescuento.getSelectedIndex() - 1;
        String idpro = ListDescuentos.get(id).getIdproductodesc();
        double aux = mantProduc.recupera_Igv_Exonerado(idpro);
        String idprodu;

        for (int i = 0; i < this.jTable2.getRowCount(); i++) {
            idprodu = jTable2.getValueAt(i, 1).toString().trim();
            double auxigv = mantProduc.recupera_Igv_Exonerado(idprodu);

            if (aux != auxigv) {
                resul = false;
            }
        }
        return resul;
    }

    private void EliminaFilaDescuento() {
        boolean existe = false;
        int fila = 0;
        int id = comboboxdescuento.getSelectedIndex();
        total = 0;
        SubTotal = 0;
        int filaactual = jTable2.getRowCount();

        for (int i = 0; i < this.jTable2.getRowCount(); i++) {
            if (this.jTable2.getValueAt(i, 1).toString().charAt(0) != 'S') {
                total += Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                String idproduc = "";

                for (int k = i; k < lisProdSinIGV.size(); k++) {
                    idproduc = lisProdSinIGV.get(k).toString();

                    if (idproduc.compareTo("9999") == 0) {
                        double parc = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        //VERIFICO SI ESXISTE UN DESCEUNTO DEL CLIENTE ESPECIAL
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            for (int j = 0; j < ListDescuentos.size(); j++) {
                String idpro = ListDescuentos.get(j).getIdproductodesc();
                if (jTable2.getValueAt(i, 1).toString().trim().compareTo(idpro) == 0) {
                    existe = true;
                    codprodespec = ListDescuentos.get(j).getIdproductodesc();
                    fila = i;
                    i = jTable2.getRowCount();
                    break;
                }
            }
        }

        //SI ES QUE ES UNA FACTURA

        if (id > 0 && lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 2) {

            double porcentaje = ListDescuentos.get(id - 1).get_pocen_descuento();
            double tot1 = ((total * porcentaje) / 100) * -1;
            BigDecimal bd = new BigDecimal(tot1);

            //SI NO EXISTE EL DESCUENTO LO AGREGO
            if (!existe) {

                objsubtotalIgv = new ProductosPrecios(0, 0);
                listsubtotales.add(filaactual, objsubtotalIgv);
                Producto miproducto;
                Productos_Botica productoBotica;

                miproducto = new Producto();
                miproducto.setEmpaque(0);
                productoBotica = new Productos_Botica();
                productoBotica.setProducto(miproducto);
                productoBotica.setMostrador_Stock_Empaque(0);
                productoBotica.setMostrador_Stock_Fraccion(0);
                objetostock = new ProductosPrecios();
                objetostock.setProductoBotica(productoBotica);
                productostock.add(filaactual, objetostock);
                cantidadproductos++;

                jTextField7.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
                total = total + tot1;

                listadetalle[0] = jTable2.getRowCount() + 1;
                listadetalle[1] = ListDescuentos.get(id - 1).getIdproductodesc();
                listadetalle[2] = ListDescuentos.get(id - 1).getPorcen_descuento();
                listadetalle[3] = ListDescuentos.get(id - 1).getId_Laboratorio();
                listadetalle[4] = "1";
                listadetalle[5] = "0";
                listadetalle[6] = ListDescuentos.get(id - 1).getPrecio_venta();
                listadetalle[7] = ListDescuentos.get(id - 1).getDescuento_venta();
                listadetalle[8] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                tablaproforma.addRow(listadetalle);

            } else {
                total = total + tot1;
                jTextField7.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
                tablaproforma.setValueAt(ListDescuentos.get(id - 1).getIdproductodesc(), fila, 1);
                tablaproforma.setValueAt(ListDescuentos.get(id - 1).getPorcen_descuento(), fila, 2);
                tablaproforma.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 8);

            }

        } else {

            if (existe)//LE QUITO EL DESCUENTO
            {
                jTextField8.setText("");
                tablaproforma.removeRow(fila);
                listsubtotales.remove(fila);
                productostock.remove(fila);
                jTextField7.setText("");
                cantidadproductos--;
            }
        }

        BigDecimal bd = new BigDecimal(total);
        SubTotal = total / (1 + (IGV / 100));

        BigDecimal bd2 = new BigDecimal(SubTotal);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd2.doubleValue();

        BigDecimal bd3 = new BigDecimal(total - SubTotal);
        jTextField21.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField8.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

    }

    private void VerificaCreditoEspecial(int idcliente) {

        if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 2) {

            ListDescuentos.removeAll(ListDescuentos);
            ListDescuentos = objClientes.Verifica_Descuento_Cliente(idcliente);

            if (ListDescuentos.size() > 0) {
                comboboxdescuento.addItem(" Seleccionar Descuento ");
                for (int i = 0; i < ListDescuentos.size(); i++) {
                    comboboxdescuento.addItem(ListDescuentos.get(i).getPorcen_descuento());
                }
            }
        }
    }

    public void GuardaVenta(String serie, String numero, String montoPagoT,String vueltoT) {

        String numbercupon = "";
        recCertificado = "";
        String RUCVTA ="";
        Venta objetoventa = null;
        ResultadoVenta objresultado = null;
        double subtotoal1 = 0.0;
        double LeerRedondeo = 0.0;
        double LeerTotFinal = 0.0;
        double total1 = 0.0;
        idcliente = Integer.parseInt(this.jTextField2.getText().trim());
        boolean ok = true;
        int solofactura = 0;

        //BOTICAS CON TICKET REVISO SI HAY DUPLICIDAD DE SERIE Y NUMERO
        if (OpcionesMenu.getConfigBotica() == 1) {
            Venta objven = new Venta();
            objven.setId_Botica(idbotica);
            objven.setId_Caja(idcaja);
            objven.setId_Tipo_Venta(tipventa);
            objven.setSerie(serie);
            objven.setNumero(numero);
            ok = objguardaventa.Existe_Serie_Numero(objven);
        }

        if (ok) {
            //AHORA SE DEBERAN DE MANDAR DATOS PARA EL DETALLE DE LA VENTA

            double tot, pventa, desc;
            double OpDescuento = 0.0;
            double TotEsGratuita = 0.0;
            double OpISC_CAB = 0.0;
            int unid, frac;
            listaventa.removeAll(listaventa);
            listVentaSinIGV.removeAll(listVentaSinIGV);

            String orden;
            int IdOperadorrec;
            int P_idpromocion;
            for (int i = 0; i < this.jTable2.getRowCount(); i++) {
                OpDescuento = 0.0;
                Id_Tipo_Precio = "0";
                
                Object[] valor = new Object[1];
                valor[0] = listaProductosVerifica.get(i);

                Object[] datos = new Object[20];
                datos = (Object[]) valor[0];

                orden = datos[11].toString();
                IdOperadorrec = Integer.parseInt(datos[12].toString());                
                String esgratuitaPromo = String.valueOf(datos[15].toString()); // pregunto si es gratuita la promo
                TotEsGratuita = Double.valueOf(datos[14].toString());
                P_idpromocion = Integer.parseInt(datos[16].toString());
                reccupon = String.valueOf(datos[17].toString());
                recCertificado = String.valueOf(datos[18].toString());

                String idproducto = String.valueOf(this.jTable2.getValueAt(i, 1));
                String esgratuita = objProducto.RetornaEsGratis(idproducto);
                int ICBPER = objProducto.EsICBPER(idproducto);
                int tipdscto = objProducto.G_EsMultipleDscto(0, idproducto,3);
                String nomostrar = objProducto.RetornaNoMostrar(idproducto);
                tot = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                unid = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 4)));
                frac = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 5)));
                pventa = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 6)));
                Id_Tipo_Precio = objProducto.RetornaTipoPrecio(idproducto,unid,frac,1);
                //***
                modif = objProducto.RetornaPersonalModifica(jTextField1.getText());

                String[] listaMOdif;
                String cadena1 = modif;
                listaMOdif = cadena1.split("/");
                int modificada = Integer.parseInt(listaMOdif[1]);
                
                if (idproducto.substring(0,1).equals("X") || modificada == 1 || idproducto.substring(0,3).equals("INV") ){
                    pventa =   pventa;
                }else{                
                    String auxpventa = objProducto.RetornaTipoPrecio(idproducto,unid,frac,2);                 
                    pventa = Double.parseDouble(auxpventa);
                }
                
                Venta enti = objProducto.Recupera_Descuento_Producto(idproducto, jTextField1.getText().trim(), Id_Tipo_Precio,String.valueOf(jTable2.getValueAt(i, 7)));
                desc = enti.getDescuento();
                parcial = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                double auxigv = enti.getIGV();
                String descrip = String.valueOf(this.jTable2.getValueAt(i, 2));
                //double OpDscto = Double.parseDouble(this.jTextField7.getText());
                // orden = String.valueOf(this.jTable2.getValueAt(i, 9));

                double sutot = 0.0;
                
                if (auxigv == 0.0) {
                    //sutot = 0.0;
                    sutot = tot / ((1 + (auxigv / 100)));
                    OpInafecta = tot;
                    OpGravada = 0.0;
                }else{
                    sutot = tot / ((1 + (auxigv / 100)));
                    OpInafecta = 0.0;
                    OpGravada = sutot;
                }
                if (tot < 0){
                    if(esgratuita.equals("1")){//CODIGO_MAX
                        OpGratuita=tot;//CODIGO_MAX
                        
                
                    }else{
                        if (tipdscto == 1){
                            OpDescuento = tot;                        
                       
                        } else{
                            if (nomostrar.equals("0")){
                                OpDescuento = tot;
                            }
                        }
                    }                                        
                }
                if(ICBPER >0){
                    double precioICB = objProducto.PrecioICBPER(idproducto);
                    double empa=objProducto.PrecioICBPEREMP(idproducto);
                    
                    double c_cantT = unid * empa + frac;
                    
                    OpISC= precioICB * c_cantT;                    
                    OpISC_CAB = OpISC_CAB + OpISC; 
                }else{
                    OpISC=0;
                }
                
                String empaque1 = objguardaventa.RetornaEmpaque(idbotica,idproducto);

                listaventa.add(new Venta(idbotica, Id_Tipo_Precio, tot, sutot, idproducto, unid, frac, pventa, desc, descrip, orden,IdOperadorrec,OpGravada,OpExonerada, OpInafecta, OpGratuita,OpISC,auxigv,OpBonificacion,OpDescuentoTotal,OpDescuento,preciounitario,esgratuita,TotEsGratuita,esgratuitaPromo,Integer.valueOf(empaque1),P_idpromocion,recCertificado));
                OpGratuita=0.0;
            }            
            RUCVTA = txtruc.getText().trim();
            
            if (tipventa == 7 || tipventa == 11 || tipventa == 13 ){
               if (!RUCVTA.equals("")){
                   RUCVTA = "";
               } 
            }
            solofactura = objproducto.Essolofactura(idcliente);
            if (solofactura == 1) {
                if(idtipopago ==8){
                
                    if (tipventa != 12){
                        JOptionPane.showMessageDialog(this, " ESTA VENTA SOLO SE VENDERA COMO FACTURA ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            int CantiBolsa =0;
            String idP = this.jTextField1.getText();
            if (!idP.equals("")){
                CantiBolsa = objProducto.CANTBOLSA(idproforma);
            }else{
                
            if (CantiBolsa == 0){
                FrmcantBolsas objCant = null;                                
                objCant = new FrmcantBolsas(objetoventana, "", "");
                objCant.pack();
                //objCant.setVisible(true);
                               
                //CantiBolsa = objCant.getCantbolsas();
                CantiBolsa = 0;
            }
            }
            
            objetoventa = new Venta(idproforma, idbotica, idcliente, idtipopago, tipventa, idmedico, colegiatura, idcaja, fecha, total1, subtotoal1, IGV, serie, numero, fecha, Id_Personal_Botica_Venta, id_personal, dniresponsable, RUCVTA, txtcliente.getText().trim().toUpperCase(), txtdni.getText().trim(), jComboBox8.getSelectedItem().toString().trim().toUpperCase(), ventaDelivery
                    , montoPagoT, vueltoT,reccupon,OpISC_CAB,CantiBolsa);
            Proforma EntidadProforma = null;

            if (comboboxdescuento.getSelectedIndex() > 0) {
                int id = this.comboboxdescuento.getSelectedIndex();
                double mitotal = 0, misubtotal;
                String idpro = null;
                boolean existe = false;

                //VERIFICO SI ESXISTE UN DESCEUNTO DEL CLIENTE ESPECIAL
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    for (int j = 0; j < ListDescuentos.size(); j++) {
                        idpro = ListDescuentos.get(j).getIdproductodesc();
                        if (jTable2.getValueAt(i, 1).toString().trim().compareTo(idpro) == 0) {
                            existe = true;
                            codprodespec = ListDescuentos.get(j).getIdproductodesc();
                            mitotal = Double.parseDouble(jTable2.getValueAt(i, 8).toString().trim());
                            i = jTable2.getRowCount();
                            break;
                        }
                    }
                }

                if (existe) {
                    double auxigv = this.objguardaventa.VerificaIGVProducto(idpro);
                    mitotal = mitotal * -1;

                    if (auxigv == 0.0) {
                        misubtotal = mitotal;
                    } else {
                        misubtotal = mitotal / ((1 + (auxigv / 100)));
                    }

                    BigDecimal bd2 = new BigDecimal(misubtotal);
                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    misubtotal = bd2.doubleValue();

                    EntidadProforma = new Proforma(idbotica, Id_Tipo_Precio, misubtotal, mitotal - misubtotal, mitotal, Id_Personal_Botica_Venta, ListDescuentos.get(id - 1).getPrecio_venta(), ListDescuentos.get(id - 1).getDescuento_venta(), mitotal, mitotal, ListDescuentos.get(id - 1).getDescuento_adicional(), idpro);

                }
            }

            if (esCredito(idtipopago) && idcliente == def.getId_Cliente()) {
                JOptionPane.showMessageDialog(this, "PORFAVOR PARA UN CREDITO AL PERSONAL \n ES NECESARIO QUE SELECCIONE UN CLIENTE CORRECTO ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                try {

                    String polizainical = this.jTextField5.getText();
                    String polizafinal = this.jTextField41.getText();
                    if (polizainical.equals("")) {
                        polizainical = "0";
                    } else {
                        polizainical = polizainical;
                    }

                    if (polizafinal.equals("")) {
                        polizafinal = "0";
                    } else {
                        polizafinal = polizafinal;
                    }

                    if (Integer.parseInt(polizainical) > Integer.parseInt(polizafinal)) {

                        JOptionPane.showMessageDialog(this, " YA NO CUENTA CON POLIZAS ASIGNADAS A SU BOTICA   ", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {

                        //JOptionPane.showMessageDialog(this, listaventa.size(), "Error", JOptionPane.ERROR_MESSAGE);
                        String tipoDocValidar = "";
                        tipoDocValidar = String.valueOf(this.comboboxtipodocumento.getSelectedItem());
                        //String cantJTable = String.valueOf(jTable2.getRowCount());
                        int cantJTable1 = jTable2.getRowCount(); 
                        cantidad = objguardaventa.RetornaCantidadCompara(tipoDocValidar);

                        String[] lista;
                        String cadena = cantidad;
                        lista = cadena.split("/");

                        int canti = Integer.parseInt(lista[0]);      // recupera el numero de items por tipo de doc
                        String recTipDoc = String.valueOf(lista[1]); // recupera el id de tipo de doc

                        //if (listaventa != null){
                        //if (listaventa.size() > 0) {
                        int cantidaddoc = 0;
                        cantidaddoc =Integer.parseInt(lista[0]);
                        

                        int posicion = 1;
                        double suma = 0;
                        double sumaSinIgv = 0;
                        double sumaLVta = 0;
                        double datosigv = 0;
                        double datosSinigv = 0;

                        List<Venta> listaverifica = new ArrayList<Venta>();
                        //Collections.sort(listaventa, new VentaComparatorByOrdenTotal()); se comento para evitar orden
                       
                       
                        List<Object> MyArray = new ArrayList<Object>();
                        if (listaventa.size() > 0) {
                            MyArray.add(listaventa);
                        }
                        if (listVentaSinIGV.size() > 0) {
                            MyArray.add(listVentaSinIGV);
                        }
                        int inthayerror = 0;
                        double intsuma = 0;
                        for (int pos = 0; pos < MyArray.size(); pos++) {

                            if(inthayerror == 1) {
                                break;
                            }

                            Object[] valor = new Object[1];
                            valor[0] = MyArray.get(pos);

                            listaverifica = (List<Venta>) valor[0];


                            String straux = "-1";
                            inthayerror = 0;
                            intsuma = 0;
                            int intposini = 0;

                            for (int i = 0; i < listaverifica.size(); i++) {
                                if (Integer.parseInt(straux) != Integer.parseInt(listaverifica.get(i).getOrden().toString())) {
                                    if (i != 0) {
                                        /*if (intsuma <= 0) {
                                            inthayerror = 1;
                                            break;
                                        }*/
                                    }
                                    straux = listaverifica.get(i).getOrden();
                                    intsuma = 0;
                                    intposini = 0;
                                }

                                if (Integer.parseInt(straux) == Integer.parseInt(listaverifica.get(i).getOrden().toString()) && intposini < cantidaddoc) {
                                    intsuma = intsuma + listaverifica.get(i).getTotal();
                                } else {

                                    /*if (intsuma <= 0) {
                                        inthayerror = 1;
                                        break;
                                    }*/

                                    intsuma = listaverifica.get(i).getTotal();
                                    intposini = 0;
                                }
                                straux = listaverifica.get(i).getOrden();
                                intposini = intposini + 1;
                            }
                        }
                        /*PARA VALIDAR MONTO MAYOR A CERO*/
                        /*if (intsuma <= 0) {
                            inthayerror = 1;
                        }*/

                    for (int i=0; i < listaverifica.size(); i++){
                        if (posicion <= cantidaddoc){
                            suma = suma + listaverifica.get(i).getTotal();
                        }else{
                            posicion = 1;
//                            if (suma < 1){
//                              JOptionPane.showMessageDialog(this,"EL MONTO DEL SIGUIENTE DOCUMENTO NO PUEDE SER NEGATIVO,\n POR FAVOR VERIFICAR LOS PRODUCTOS", "Error", JOptionPane.ERROR_MESSAGE);
//                            }
                            suma = listaverifica.get(i).getTotal();
                        }
                        posicion = posicion +1;
//
                    }
//                            if (suma < 1){
//                              JOptionPane.showMessageDialog(this,"EL MONTO DEL SIGUIENTE DOCUMENTO NO PUEDE SER NEGATIVO,\n POR FAVOR VERIFICAR LOS PRODUCTOS", "Error", JOptionPane.ERROR_MESSAGE);
//                            objresultado = null;
//                            }else

                        if (inthayerror == 1) {
                            JOptionPane.showMessageDialog(this, "EL MONTO DEL SIGUIENTE DOCUMENTO NO PUEDE SER NEGATIVO,\n POR FAVOR VERIFICAR LOS PRODUCTOS", "Error", JOptionPane.ERROR_MESSAGE);
                            objresultado = null;
                        } else {
                            ordenproducto = 1;
                            listaProductosVerifica.clear();
                            String VarPoliza = this.jTextField5.getText().trim().toString();

                            for (int i=0; i < listVentaSinIGV.size(); i++){

                                sumaSinIgv = sumaSinIgv + listVentaSinIGV.get(i).getTotal();
                            }
                            for (int i=0; i < listaventa.size(); i++){

                                sumaLVta = sumaLVta + listaventa.get(i).getTotal();
                            }
                            if (listVentaSinIGV.size()>0){
                                datosigv = 0;
                                datosSinigv = 1;
                            }
                            if (listaventa.size()>0){
                                datosigv = 1;
                                datosSinigv = 0;
                            }


                            //if ((sumaSinIgv > 0  && datosSinigv == 1) || (sumaLVta > 0 && datosigv == 1) ){
                            String MonedaFinal = jComboBox9.getSelectedItem().toString();
                            if (!String.valueOf(cupon).isEmpty()) {
                                        if (cupon != null) {
                                            if (cupon.compareTo("0") != 0) {
                                                if (!String.valueOf(cupon).isEmpty() || cupon != null) {
                                                    numbercupon = cupon;
                                                }else{
                                                    numbercupon = "";
                                                }
                                            }else{
                                                numbercupon = "";
                                            }
                                        }else{
                                            numbercupon = "";
                                        }
                                    }else{
                                    numbercupon = "";
                                    }
                            LeerRedondeo = Double.parseDouble(this.jTextField44.getText());
                            LeerTotFinal = Double.parseDouble(this.jTextField45.getText());

                            objresultado = objguardaventa.GuardarVenta(objetoventa, listVentaSinIGV, listaventa, lista_pagos, EntidadProforma, VarPoliza,MonedaFinal,numbercupon,
                                    LeerRedondeo,LeerTotFinal);
                            //}else{
                            //    JOptionPane.showMessageDialog(this, " LO SENTIMOS HUBO UN ERROR AL GENERAR LA VENTA \n ", "Error", JOptionPane.ERROR_MESSAGE);
                            //}
                        }

                        //  }
                        //}



                        if (objresultado.getIderror() == 1) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS NO CUENTA CON STOCK SUFICIENTE DEL  PRODUCTO :  \n " + objresultado.getIdproducto() + " ", "Error", JOptionPane.ERROR_MESSAGE);
                            listVentaSinIGV.removeAll(listVentaSinIGV);
                            listaventa.removeAll(listaventa);
                        } else if (objresultado.getIderror() == 3) {
                            JOptionPane.showMessageDialog(this, "LA SERIE DE ESTE DOCUMENTO HA EXPIRADO \n PORRFAVOR DEBE DE REALIZAR APERTURA DE ESTE DOCUMENTO", "Error", JOptionPane.ERROR_MESSAGE);
                            listVentaSinIGV.removeAll(listVentaSinIGV);
                            listaventa.removeAll(listaventa);
                        } else {
                            if (objresultado.getIderror() == 2) {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS HUBO UN ERROR AL GENERAR LA VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
                                listVentaSinIGV.removeAll(listVentaSinIGV);
                                listaventa.removeAll(listaventa);
                            }
                            if (objresultado == null) {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS HUBO UN ERROR AL GENERAR LA VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
                                listVentaSinIGV.removeAll(listVentaSinIGV);
                                listaventa.removeAll(listaventa);
                            }
                            if (objresultado.getIderror() == 4) {
                                JOptionPane.showMessageDialog(this, " ERROR AL GUARDAR SU VENTA \n MOTNO DE VENTA NEGATIVO ", "Error", JOptionPane.ERROR_MESSAGE);
                                String correo = objlistabotica.ReornaCorreo(idbotica);
                                objmail.sendMail(correo, "ERROR DE VENTA", "PROBLEMA ENCONTRADO CON MONTO DE VENTA NEGATIVO \n  SE HIZO UN ROLLBACK PARA EL NUMERO DE PROFORMA : " + objresultado.getIdProforma() + "   \n BOTICA FELICIDAD  " + idbotica);
                            } else {
                                if (objresultado.getIderror() == 0) {

                                    /**/
                                    String inter = jTextField37.getText();
                                    double DsctoGlobal2 = 0.0;
                                    if (!jTextField43.getText().equals("")){
                                        DsctoGlobal2 = Double.valueOf(jTextField43.getText());
                                    }
                                                                        
                                    double TOTALGENERALMODIF = Double.valueOf(jTextField21.getText());
                                    double IGVTOTALMODIF = Double.valueOf(this.jTextField9.getText());
                                    double SUBTOTALMODIF = Double.valueOf(this.jTextField8.getText());
                                    
                                    if (DsctoGlobal2 > 0){
                                        objguardaventa.UpdateVtaDsctoGlobal(inter,TOTALGENERALMODIF,SUBTOTALMODIF,IGVTOTALMODIF,DsctoGlobal2);
                                         
                                     }
                                    /**/
                                    
                                    //displayHelloWorld();

                                    limpiardatos();
                                    JOptionPane.showMessageDialog(this, " Venta Realizada Correctamente \n Caja : Caja " + idcaja + "  \n Cajero: " + objventa.getUsuario() + " ", " Venta Realizada   NORTFARMA S.A.C", JOptionPane.INFORMATION_MESSAGE);
                                    jTextField1.setText("");
                                    Recupera_Serie_Numero();
                                    

                                    RecuperaInterno();                                    
                                    if (objresultado.isEsaporte()) {
                                        Proforma_Coasociado objpro = new Proforma_Coasociado(this.objetoventana, true, objresultado.getIdProforma(), this);
                                        objpro.pack();
                                        objpro.setVisible(true);
                                    }
                                }
                            }
                        }
                    } // FIN POLIZA
                } catch (Exception ex) {
                    System.out.println("ERROR AL REALIZAR SU VENTA" + ex.getMessage());
                    listaventa.removeAll(listaventa);
                    listVentaSinIGV.removeAll(listVentaSinIGV);
                }

            }
        }//FIN DE IF OK
        else {
            JOptionPane.showMessageDialog(this, " DOCUMENTO : " + serie + " - " + numero + "  DUPLICADO \n  PORFAVOR COMUNIQUESE CON INFORMATICA ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void RecuperaInterno() {
        try {
            jTextField37.setText(objguardaventa.Recupera_Interno(idbotica));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void NuevaVenta() {
        int pe = JOptionPane.showConfirmDialog(null, " Deseas Realizar una Nueva Venta ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (pe == JOptionPane.YES_OPTION) {
            limpiardatos();
            this.jTextField1.setText("");
        }
    }

    private void limpiardatos() {

        serie = "";
        numero = "";
        poscant = 0;
        IGV = 0.0;
        miigv_x =0;
        total_pigv=0;
        total = 0;
        idproforma = null;
        canttiposPagos = 1;
        cantpagos = 1;
        tipventa = 0;
        idmedico = "";
        SubTotal = 0.0;
        OpGravada = 0.0;
        OpInafecta=0.0;
        cantidadproductos = 0;
        productostock.removeAll(productostock);
        comboboxtipodocumento.setSelectedIndex(0);
        listproforma.removeAll(listproforma);
        listproformaDetalle.removeAll(listproformaDetalle);
        listproformaDetalle.removeAll(listproformaDetalle);
        listsubtotales.removeAll(listsubtotales);
        listtipoprecio.removeAll(listtipoprecio);
        listaventa.removeAll(listaventa);
        listVentaSinIGV.removeAll(listVentaSinIGV);
        listaProductosVerifica.removeAll(listaProductosVerifica);
        jLabel11.setText("");
        jTextField2.setText("");
        txtdni.setText("");
        txtcliente.setText("");
        //jTextField4.setText("");
        jComboBox8.removeAllItems();
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField19.setText("");
        jTextField18.setText("");
        jTextField21.setText("");
        jTextField44.setText("");
        jTextField45.setText("");
        jTextField17.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField42.setText("");
        jButton1.setEnabled(false);
        txtruc.setText("");
        txtruc.requestFocus();
        jCheckBox4.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox2.setSelected(false);
        LimpiatTabla();        
        lista_pagos.removeAll(lista_pagos);
        try{
        comboboxtipodepago.setSelectedIndex(0);
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        comboboxtipodocumento.setSelectedIndex(0);
        HabilitaBotones(false);
        Habilita_Abono(false);
        jCheckBox1.setEnabled(true);
        jCheckBox1.setSelected(true);
        jTextField17.setEnabled(true);
        DetallePromo.removeAll(DetallePromo);
        nuevaPromocion.removeAll(nuevaPromocion);
        cantidadElementos.removeAll(cantidadElementos);
        Id_Tipo_Precio = "01";
        jLabel36.setText("");
        jLabel37.setText("");
        jTextField5.setVisible(false);
        jTextField5.setText("");
        jTextField41.setText("");
        comboboxtipodepago.setEnabled(true);
        jTextField44.setText("0.0");
        jTextField45.setText("0.0");
        existe=0;
        contarpromo=0;
        recCertificado="";
        certificado="";
    }

    private class MuestraVentana extends JFrame {

        private String idbotica;
        private int idcaja, tipoventa;
        GuardaVenta objpadre;
        private String monto;
        RealizaVenta obj;
        String serie;
        String montoPago;
        String vuelto;
        String numero;
        boolean banedra;
        private String documento;
        private int ventaDelivery;

        public MuestraVentana() {
        }

        public MuestraVentana(String idboti, int idca, int idven, String monto, String doc) {
            this.idbotica = idboti;
            this.idcaja = idca;
            this.tipoventa = idven;
            this.monto = monto;
            this.documento = doc;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getSerie() {
            return serie;
        }

        public void setSerie(String serie) {
            this.serie = serie;
        }

        public boolean isBanedra() {
            return banedra;
        }

        public void setBanedra(boolean banedra) {
            this.banedra = banedra;
        }

        public int getVentaDelivery() {
            return ventaDelivery;
        }

        public void setVentaDelivery(int ventaDelivery) {
            this.ventaDelivery = ventaDelivery;
        }

        public String getmontoPago() {
        return montoPago;
        }
        public void setmontoPago(String montoPago) {
            this.montoPago = montoPago;
        }

        public String getvuelto() {
            return vuelto;
        }
        public void setvuelto(String vuelto) {
            this.vuelto = vuelto;
        }

        public void AbrirVentana() {  
            double DsctoGlobal1 = 0.0;
            String redo = "";
            if (!jTextField43.getText().equals("")){
                DsctoGlobal1 = Double.valueOf(jTextField43.getText());
            }
            if (!jTextField44.getText().equals("")){
                redo = jTextField44.getText();
            }
            //if (!monto.equals("")){
             //monto = jTextField45.getText();
            //}
             //Double TOTALGENERALOCULTO1 = Double.valueOf(jTextField11.getText());
             //Double TOTALGENERAL1 = Double.valueOf(jTextField21.getText());
             Double TOTALGENERAL1 = Double.valueOf(jTextField45.getText());
             /*if (DsctoGlobal1 > 0){
                 monto =  String.valueOf(TOTALGENERAL1);
             }*/


            objpadre = new GuardaVenta(this, idbotica, idcaja, tipoventa, documento, monto, obj,redo);
            objpadre.pack();
            objpadre.setVisible(true);
            setNumero(objpadre.getMnumero());
            setSerie(objpadre.getSerie());
            setBanedra(objpadre.isBandera());
            setVentaDelivery(objpadre.getVentaDelivery());
            setmontoPago(objpadre.getmontoPago());
            setvuelto(objpadre.getvuelto());
        }
    }

    @Action
    public void ModificarProducto() {
        jTable2.requestFocus();
    }

    @Action
    public void ModificaCliente() {
        ModificaDatosCliente();
    }

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            setBackground(Color.white);
            int tam = lisProdSinIGV.size();

            for (int i = 0; i < tam; i++) {
                Object codpro = table.getValueAt(row, column);
                if (lisProdSinIGV.get(i) == codpro) {
                    setBackground(Color.PINK);
                }

            }

            super.getTableCellRendererComponent(table, value, false, false, 2, column);

            return this;
        }
    }

    private void InformacionProducto() {
        int seleccionada = -1;
        seleccionada = jTable2.getSelectedRow();
        if (seleccionada >= 0) {
            String idproducto = String.valueOf(this.jTable2.getValueAt(seleccionada, 1));
            Productos_Botica obj = objProducto.InformacionProducto(this.idbotica, idproducto);

            if (obj != null) {
                this.jTextField15.setText(idproducto);
                this.jTextField16.setText(String.valueOf(this.jTable2.getValueAt(jTable2.getSelectedRow(), 2)));
                this.jTextField32.setText(String.valueOf(this.jTable2.getValueAt(jTable2.getSelectedRow(), 7)));
                this.jTextField20.setText(obj.getProducto().getLaboratorio().getDescripcion());
                this.jTextField22.setText(obj.getProducto().getIdFamilia().getDescripcion());
                this.jTextField23.setText(obj.getProducto().getIdGenerico().getDescripcionGenerico());
                this.jTextField24.setText(String.valueOf(obj.getProducto().getEmpaque()));
                this.jTextField25.setText(String.valueOf(obj.getAlmacen_Stock_Empaque()));
                this.jTextField26.setText(String.valueOf(obj.getAlmacen_Stock_Fraccion()));
                this.jTextField27.setText(String.valueOf(obj.getMostrador_Stock_Empaque()));
                this.jTextField28.setText(String.valueOf(obj.getMostrador_Stock_Fraccion()));
                this.jTextField34.setText(String.valueOf(obj.getNumero_Lote()));
                this.jTextField33.setText(String.valueOf(obj.getFecha_Vencimiento_Lote()));

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField29 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTextField21 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jComboBox8 = new javax.swing.JComboBox();
        jTextField42 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        btnconsultar = new javax.swing.JButton();
        btnconsultarRUC = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboboxtipodocumento = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboboxtipodepago = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        comboboxdescuento = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField17 = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField19 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField10 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        jTextField18 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField11 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jTextField6 = new javax.swing.JTextField();
        jCheckBox5 = new javax.swing.JCheckBox();
        jTextField39 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox();
        jTextField38 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jComboBox9 = new javax.swing.JComboBox();
        jButton11 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(RealizaVenta.class);
        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu1KeyPressed(evt);
            }
        });
        jPopupMenu1.add(jMenu1);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem3);

        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setMinimumSize(new java.awt.Dimension(280, 39));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(930, 630));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jTabbedPane1.setFont(resourceMap.getFont("jTabbedPane1.font")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1001, 82));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBackground(resourceMap.getColor("jTable2.background")); // NOI18N
        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Codigo", "Producto", "Laboratorio", "  Unidad", "  Fraccion", "    PVP", "    PVPx", "    Parcial", "orden"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(27);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(445);
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(35);
            jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(35);
            jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable2.getColumnModel().getColumn(7).setResizable(false);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
            jTable2.getColumnModel().getColumn(8).setResizable(false);
            jTable2.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable2.columnModel.title8")); // NOI18N
            jTable2.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable2.columnModel.title9")); // NOI18N
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jTextField29.setText(resourceMap.getString("jTextField29.text")); // NOI18N
        jTextField29.setName("jTextField29"); // NOI18N
        jTextField29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField29KeyPressed(evt);
            }
        });

        jPanel3.setFont(resourceMap.getFont("jPanel3.font")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);

        jLabel26.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setFocusable(false);
        jLabel26.setName("jLabel26"); // NOI18N

        jTextField34.setEditable(false);
        jTextField34.setBackground(resourceMap.getColor("jTextField33.background")); // NOI18N
        jTextField34.setFont(resourceMap.getFont("jTextField33.font")); // NOI18N
        jTextField34.setFocusable(false);
        jTextField34.setName("jTextField34"); // NOI18N

        jLabel32.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setFocusable(false);
        jLabel32.setName("jLabel32"); // NOI18N

        jTextField33.setEditable(false);
        jTextField33.setBackground(resourceMap.getColor("jTextField33.background")); // NOI18N
        jTextField33.setFont(resourceMap.getFont("jTextField33.font")); // NOI18N
        jTextField33.setText(resourceMap.getString("jTextField33.text")); // NOI18N
        jTextField33.setFocusable(false);
        jTextField33.setName("jTextField33"); // NOI18N

        jTextField32.setEditable(false);
        jTextField32.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField32.setFont(resourceMap.getFont("jTextField33.font")); // NOI18N
        jTextField32.setFocusable(false);
        jTextField32.setName("jTextField32"); // NOI18N

        jLabel31.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setFocusable(false);
        jLabel31.setName("jLabel31"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField32, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);

        jLabel22.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setFocusable(false);
        jLabel22.setName("jLabel22"); // NOI18N

        jTextField25.setEditable(false);
        jTextField25.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField25.setFont(resourceMap.getFont("jTextField24.font")); // NOI18N
        jTextField25.setText(resourceMap.getString("jTextField25.text")); // NOI18N
        jTextField25.setFocusable(false);
        jTextField25.setName("jTextField25"); // NOI18N

        jLabel23.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setFocusable(false);
        jLabel23.setName("jLabel23"); // NOI18N

        jTextField26.setEditable(false);
        jTextField26.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField26.setFont(resourceMap.getFont("jTextField24.font")); // NOI18N
        jTextField26.setFocusable(false);
        jTextField26.setName("jTextField26"); // NOI18N

        jLabel24.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setFocusable(false);
        jLabel24.setName("jLabel24"); // NOI18N

        jTextField27.setEditable(false);
        jTextField27.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField27.setFont(resourceMap.getFont("jTextField24.font")); // NOI18N
        jTextField27.setFocusable(false);
        jTextField27.setName("jTextField27"); // NOI18N

        jLabel25.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setFocusable(false);
        jLabel25.setName("jLabel25"); // NOI18N

        jTextField28.setEditable(false);
        jTextField28.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField28.setFont(resourceMap.getFont("jTextField24.font")); // NOI18N
        jTextField28.setFocusable(false);
        jTextField28.setName("jTextField28"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField24.setEditable(false);
        jTextField24.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField24.setFont(resourceMap.getFont("jTextField24.font")); // NOI18N
        jTextField24.setFocusable(false);
        jTextField24.setName("jTextField24"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)))
                                    .addGap(10, 10, 10))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addGap(26, 26, 26)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField28)
                                .addComponent(jTextField25)
                                .addComponent(jTextField27)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);

        jLabel15.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField15.setEditable(false);
        jTextField15.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField15.setFont(resourceMap.getFont("jTextField23.font")); // NOI18N
        jTextField15.setText(resourceMap.getString("jTextField15.text")); // NOI18N
        jTextField15.setFocusable(false);
        jTextField15.setName("jTextField15"); // NOI18N
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
        });

        jLabel16.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jTextField16.setEditable(false);
        jTextField16.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField16.setFont(resourceMap.getFont("jTextField23.font")); // NOI18N
        jTextField16.setFocusable(false);
        jTextField16.setName("jTextField16"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jTextField20.setEditable(false);
        jTextField20.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField20.setFont(resourceMap.getFont("jTextField23.font")); // NOI18N
        jTextField20.setFocusable(false);
        jTextField20.setName("jTextField20"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jTextField22.setEditable(false);
        jTextField22.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField22.setFont(resourceMap.getFont("jTextField23.font")); // NOI18N
        jTextField22.setFocusable(false);
        jTextField22.setName("jTextField22"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jTextField23.setEditable(false);
        jTextField23.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField23.setFont(resourceMap.getFont("jTextField23.font")); // NOI18N
        jTextField23.setFocusable(false);
        jTextField23.setName("jTextField23"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(630, 630, 630)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(219, 219, 219))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getActionMap(RealizaVenta.class, this);
        jButton4.setAction(actionMap.get("ModificarProducto")); // NOI18N
        jButton4.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setMargin(new java.awt.Insets(2, 8, 2, 14));
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(115, 21));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jButton6.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(115, 21));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(resourceMap.getColor("jPanel9.background")); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setOpaque(false);

        jTextField21.setEditable(false);
        jTextField21.setBackground(resourceMap.getColor("jTextField21.background")); // NOI18N
        jTextField21.setFont(resourceMap.getFont("jTextField21.font")); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField21.setDisabledTextColor(resourceMap.getColor("jTextField21.disabledTextColor")); // NOI18N
        jTextField21.setFocusable(false);
        jTextField21.setName("jTextField21"); // NOI18N
        jTextField21.setPreferredSize(new java.awt.Dimension(95, 30));
        jTextField21.setRequestFocusEnabled(false);

        jLabel27.setFont(resourceMap.getFont("jLabel27.font")); // NOI18N
        jLabel27.setForeground(resourceMap.getColor("jLabel27.foreground")); // NOI18N
        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setFocusable(false);
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setRequestFocusEnabled(false);

        jLabel10.setFont(resourceMap.getFont("jLabel27.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel27.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setRequestFocusEnabled(false);

        jLabel9.setFont(resourceMap.getFont("jLabel27.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel27.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setFocusable(false);
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setRequestFocusEnabled(false);

        jTextField8.setEditable(false);
        jTextField8.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField8.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText(resourceMap.getString("jTextField8.text")); // NOI18N
        jTextField8.setDisabledTextColor(resourceMap.getColor("jTextField8.disabledTextColor")); // NOI18N
        jTextField8.setFocusable(false);
        jTextField8.setName("jTextField8"); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText(resourceMap.getString("jTextField9.text")); // NOI18N
        jTextField9.setDisabledTextColor(resourceMap.getColor("jTextField9.disabledTextColor")); // NOI18N
        jTextField9.setFocusable(false);
        jTextField9.setName("jTextField9"); // NOI18N

        jLabel38.setFont(resourceMap.getFont("jLabel38.font")); // NOI18N
        jLabel38.setForeground(resourceMap.getColor("jLabel38.foreground")); // NOI18N
        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        jTextField44.setEditable(false);
        jTextField44.setBackground(resourceMap.getColor("jTextField44.background")); // NOI18N
        jTextField44.setFont(resourceMap.getFont("jTextField44.font")); // NOI18N
        jTextField44.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField44.setName("jTextField44"); // NOI18N
        jTextField44.setPreferredSize(new java.awt.Dimension(95, 25));

        jLabel39.setFont(resourceMap.getFont("jLabel39.font")); // NOI18N
        jLabel39.setForeground(resourceMap.getColor("jLabel39.foreground")); // NOI18N
        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        jTextField45.setEditable(false);
        jTextField45.setBackground(resourceMap.getColor("jTextField45.background")); // NOI18N
        jTextField45.setFont(resourceMap.getFont("jTextField45.font")); // NOI18N
        jTextField45.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField45.setName("jTextField45"); // NOI18N
        jTextField45.setPreferredSize(new java.awt.Dimension(95, 25));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel27)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField45, 0, 1, Short.MAX_VALUE)
                    .addComponent(jTextField44, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jTextField21, 0, 1, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jPanel8.setBackground(resourceMap.getColor("jPanel8.background")); // NOI18N
        jPanel8.setMinimumSize(new java.awt.Dimension(250, 30));
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(650, 31));

        jSeparator1.setName("jSeparator1"); // NOI18N
        jPanel8.add(jSeparator1);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jPanel8.add(jSeparator9);

        jButton2.setAction(actionMap.get("ModificaCliente")); // NOI18N
        jButton2.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setMargin(new java.awt.Insets(2, 8, 2, 14));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(115, 21));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(115, 21));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel12.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel12.border.titleFont"), resourceMap.getColor("jPanel12.border.titleColor"))); // NOI18N
        jPanel12.setName("jPanel12"); // NOI18N
        jPanel12.setOpaque(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(492, 126));

        jLabel2.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel3.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtcliente.setBackground(resourceMap.getColor("txtcliente.background")); // NOI18N
        txtcliente.setName("txtcliente"); // NOI18N
        txtcliente.setPreferredSize(new java.awt.Dimension(210, 20));
        txtcliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtclienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtclienteFocusLost(evt);
            }
        });
        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });
        txtcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtclienteKeyPressed(evt);
            }
        });

        jLabel6.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel6.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setRequestFocusEnabled(false);

        jLabel21.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel21.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        txtruc.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtruc.setFont(resourceMap.getFont("txtruc.font")); // NOI18N
        txtruc.setForeground(resourceMap.getColor("txtruc.foreground")); // NOI18N
        txtruc.setName("txtruc"); // NOI18N
        txtruc.setPreferredSize(new java.awt.Dimension(80, 20));
        txtruc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtrucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrucFocusLost(evt);
            }
        });
        txtruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrucActionPerformed(evt);
            }
        });
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrucKeyPressed(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtdni.setBackground(resourceMap.getColor("txtdni.background")); // NOI18N
        txtdni.setName("txtdni"); // NOI18N
        txtdni.setNextFocusableComponent(btnconsultar);
        txtdni.setPreferredSize(new java.awt.Dimension(100, 20));
        txtdni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdniFocusLost(evt);
            }
        });
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdniKeyPressed(evt);
            }
        });

        jButton7.setFont(resourceMap.getFont("jButton7.font")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setComponentPopupMenu(jPopupMenu2);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(80, 17));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox8.setBackground(resourceMap.getColor("jComboBox8.background")); // NOI18N
        jComboBox8.setEditable(true);
        jComboBox8.setName("jComboBox8"); // NOI18N
        jComboBox8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox8KeyPressed(evt);
            }
        });

        jTextField42.setBackground(resourceMap.getColor("jTextField42.background")); // NOI18N
        jTextField42.setText(resourceMap.getString("jTextField42.text")); // NOI18N
        jTextField42.setName("jTextField42"); // NOI18N

        jLabel40.setForeground(resourceMap.getColor("jLabel40.foreground")); // NOI18N
        jLabel40.setText(resourceMap.getString("jLabel40.text")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        btnconsultar.setAction(actionMap.get("verdni")); // NOI18N
        btnconsultar.setIcon(resourceMap.getIcon("btnconsultar.icon")); // NOI18N
        btnconsultar.setText(resourceMap.getString("btnconsultar.text")); // NOI18N
        btnconsultar.setName("btnconsultar"); // NOI18N
        btnconsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarActionPerformed(evt);
            }
        });
        btnconsultar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnconsultarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnconsultarKeyReleased(evt);
            }
        });

        btnconsultarRUC.setBackground(resourceMap.getColor("btnconsultarRUC.background")); // NOI18N
        btnconsultarRUC.setIcon(resourceMap.getIcon("btnconsultarRUC.icon")); // NOI18N
        btnconsultarRUC.setText(resourceMap.getString("btnconsultarRUC.text")); // NOI18N
        btnconsultarRUC.setDefaultCapable(false);
        btnconsultarRUC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnconsultarRUC.setName("btnconsultarRUC"); // NOI18N
        btnconsultarRUC.setPreferredSize(new java.awt.Dimension(81, 30));
        btnconsultarRUC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarRUCActionPerformed(evt);
            }
        });
        btnconsultarRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnconsultarRUCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnconsultarRUCKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(14, 14, 14))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jTextField42)
                                .addGap(256, 256, 256))
                            .addComponent(txtcliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnconsultarRUC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnconsultar)
                        .addGap(43, 43, 43))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(btnconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconsultarRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setName("jPanel13"); // NOI18N
        jPanel13.setOpaque(false);

        jLabel5.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        comboboxtipodocumento.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        comboboxtipodocumento.setName("comboboxtipodocumento"); // NOI18N
        comboboxtipodocumento.setNextFocusableComponent(comboboxtipodepago);
        comboboxtipodocumento.setPreferredSize(new java.awt.Dimension(200, 19));
        comboboxtipodocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboboxtipodocumentoItemStateChanged(evt);
            }
        });
        comboboxtipodocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboboxtipodocumentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboboxtipodocumentoFocusLost(evt);
            }
        });
        comboboxtipodocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxtipodocumentoActionPerformed(evt);
            }
        });
        comboboxtipodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboboxtipodocumentoKeyPressed(evt);
            }
        });

        jLabel7.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setRequestFocusEnabled(false);

        comboboxtipodepago.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        comboboxtipodepago.setForeground(resourceMap.getColor("comboboxtipodepago.foreground")); // NOI18N
        comboboxtipodepago.setName("comboboxtipodepago"); // NOI18N
        comboboxtipodepago.setNextFocusableComponent(comboboxdescuento);
        comboboxtipodepago.setPreferredSize(new java.awt.Dimension(200, 19));
        comboboxtipodepago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboboxtipodepagoItemStateChanged(evt);
            }
        });
        comboboxtipodepago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboboxtipodepagoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboboxtipodepagoFocusLost(evt);
            }
        });
        comboboxtipodepago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboboxtipodepagoMouseReleased(evt);
            }
        });
        comboboxtipodepago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxtipodepagoActionPerformed(evt);
            }
        });
        comboboxtipodepago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboboxtipodepagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboboxtipodepagoKeyReleased(evt);
            }
        });

        jLabel14.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setFocusable(false);
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setRequestFocusEnabled(false);

        comboboxdescuento.setFont(resourceMap.getFont("comboboxdescuento.font")); // NOI18N
        comboboxdescuento.setName("comboboxdescuento"); // NOI18N
        comboboxdescuento.setNextFocusableComponent(txtdni);
        comboboxdescuento.setPreferredSize(new java.awt.Dimension(200, 19));
        comboboxdescuento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboboxdescuentoItemStateChanged(evt);
            }
        });
        comboboxdescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comboboxdescuentoMouseEntered(evt);
            }
        });
        comboboxdescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxdescuentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboboxdescuento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboboxtipodepago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboboxtipodocumento, 0, 239, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxtipodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxtipodepago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(comboboxdescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton8.setBackground(resourceMap.getColor("jButton8.background")); // NOI18N
        jButton8.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setFocusable(false);
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(130, 23));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton1.setBackground(resourceMap.getColor("jButton1.background")); // NOI18N
        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setMnemonic('G');
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setMargin(new java.awt.Insets(2, 8, 2, 14));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(110, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setMnemonic('C');
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setMargin(new java.awt.Insets(2, 8, 2, 14));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(95, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jPanel16.setName("jPanel16"); // NOI18N
        jPanel16.setOpaque(false);

        jLabel12.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel16.add(jLabel12);

        jTextField1.setEditable(false);
        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText(resourceMap.getString("jTextField1.toolTipText")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 22));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel16.add(jTextField1);

        jLabel34.setForeground(resourceMap.getColor("jLabel34.foreground")); // NOI18N
        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N
        jPanel16.add(jLabel34);

        jTextField37.setEditable(false);
        jTextField37.setBackground(resourceMap.getColor("jTextField37.background")); // NOI18N
        jTextField37.setFont(resourceMap.getFont("jTextField37.font")); // NOI18N
        jTextField37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField37.setText(resourceMap.getString("jTextField37.text")); // NOI18N
        jTextField37.setName("jTextField37"); // NOI18N
        jTextField37.setPreferredSize(new java.awt.Dimension(100, 22));
        jPanel16.add(jTextField37);

        jLabel29.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel29.setForeground(resourceMap.getColor("jLabel29.foreground")); // NOI18N
        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setFocusable(false);
        jLabel29.setName("jLabel29"); // NOI18N

        jTextField30.setEditable(false);
        jTextField30.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        jTextField30.setFont(resourceMap.getFont("jTextField30.font")); // NOI18N
        jTextField30.setForeground(resourceMap.getColor("jTextField30.foreground")); // NOI18N
        jTextField30.setText(resourceMap.getString("jTextField30.text")); // NOI18N
        jTextField30.setFocusable(false);
        jTextField30.setName("jTextField30"); // NOI18N

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);

        jLabel28.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        jPanel7.add(jLabel28);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel7.add(jLabel1);

        jTextField31.setEditable(false);
        jTextField31.setBackground(resourceMap.getColor("jTextField35.background")); // NOI18N
        jTextField31.setFont(resourceMap.getFont("jTextField35.font")); // NOI18N
        jTextField31.setForeground(resourceMap.getColor("jTextField31.foreground")); // NOI18N
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField31.setName("jTextField31"); // NOI18N
        jTextField31.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel7.add(jTextField31);

        jLabel13.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel7.add(jLabel13);

        jTextField35.setEditable(false);
        jTextField35.setBackground(resourceMap.getColor("jTextField35.background")); // NOI18N
        jTextField35.setFont(resourceMap.getFont("jTextField35.font")); // NOI18N
        jTextField35.setForeground(resourceMap.getColor("jTextField31.foreground")); // NOI18N
        jTextField35.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField35.setText(resourceMap.getString("jTextField35.text")); // NOI18N
        jTextField35.setName("jTextField35"); // NOI18N
        jTextField35.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel7.add(jTextField35);

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(30, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5);

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jComboBox6.setName("jComboBox6"); // NOI18N

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jTextField36.setEditable(false);
        jTextField36.setBackground(resourceMap.getColor("jTextField36.background")); // NOI18N
        jTextField36.setFont(resourceMap.getFont("jTextField36.font")); // NOI18N
        jTextField36.setForeground(resourceMap.getColor("jTextField36.foreground")); // NOI18N
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setText(resourceMap.getString("jTextField36.text")); // NOI18N
        jTextField36.setName("jTextField36"); // NOI18N

        jLabel35.setFont(resourceMap.getFont("jLabel35.font")); // NOI18N
        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel15.setBackground(resourceMap.getColor("jPanel15.background")); // NOI18N
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel15.border.titleColor"))); // NOI18N
        jPanel15.setName("jPanel15"); // NOI18N

        jCheckBox1.setFont(resourceMap.getFont("jCheckBox4.font")); // NOI18N
        jCheckBox1.setForeground(resourceMap.getColor("jCheckBox1.foreground")); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(resourceMap.getString("jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextField17.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setName("jTextField17"); // NOI18N
        jTextField17.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField17KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField17KeyReleased(evt);
            }
        });

        jCheckBox4.setFont(resourceMap.getFont("jCheckBox4.font")); // NOI18N
        jCheckBox4.setForeground(resourceMap.getColor("jCheckBox4.foreground")); // NOI18N
        jCheckBox4.setText(resourceMap.getString("jCheckBox4.text")); // NOI18N
        jCheckBox4.setToolTipText(resourceMap.getString("jCheckBox4.toolTipText")); // NOI18N
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jTextField19.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setEnabled(false);
        jTextField19.setName("jTextField19"); // NOI18N
        jTextField19.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField19KeyReleased(evt);
            }
        });

        jCheckBox2.setBackground(resourceMap.getColor("jCheckBox2.background")); // NOI18N
        jCheckBox2.setFont(resourceMap.getFont("jCheckBox4.font")); // NOI18N
        jCheckBox2.setForeground(resourceMap.getColor("jCheckBox2.foreground")); // NOI18N
        jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(resourceMap.getString("jCheckBox2.toolTipText")); // NOI18N
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jTextField10.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setToolTipText(resourceMap.getString("jTextField10.toolTipText")); // NOI18N
        jTextField10.setEnabled(false);
        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jTextField12.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField12.setToolTipText(resourceMap.getString("jTextField12.toolTipText")); // NOI18N
        jTextField12.setEnabled(false);
        jTextField12.setName("jTextField12"); // NOI18N
        jTextField12.setPreferredSize(new java.awt.Dimension(110, 20));
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
        });
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jCheckBox3.setFont(resourceMap.getFont("jCheckBox4.font")); // NOI18N
        jCheckBox3.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox3.setText(resourceMap.getString("jCheckBox3.text")); // NOI18N
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jCheckBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox3KeyPressed(evt);
            }
        });

        jTextField18.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setEnabled(false);
        jTextField18.setName("jTextField18"); // NOI18N
        jTextField18.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField18KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField18KeyReleased(evt);
            }
        });

        jTextField13.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setToolTipText(resourceMap.getString("jTextField13.toolTipText")); // NOI18N
        jTextField13.setEnabled(false);
        jTextField13.setName("jTextField13"); // NOI18N
        jTextField13.setPreferredSize(new java.awt.Dimension(110, 20));
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setEnabled(false);
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(110, 20));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jTextField11.setEditable(false);
        jTextField11.setBackground(resourceMap.getColor("jTextField11.background")); // NOI18N
        jTextField11.setFont(resourceMap.getFont("jTextField11.font")); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.setPreferredSize(new java.awt.Dimension(80, 25));

        jComboBox5.setEnabled(false);
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jCheckBox5.setFont(resourceMap.getFont("jCheckBox5.font")); // NOI18N
        jCheckBox5.setForeground(resourceMap.getColor("jCheckBox5.foreground")); // NOI18N
        jCheckBox5.setText(resourceMap.getString("jCheckBox5.text")); // NOI18N
        jCheckBox5.setToolTipText(resourceMap.getString("jCheckBox5.toolTipText")); // NOI18N
        jCheckBox5.setName("jCheckBox5"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jCheckBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox5KeyPressed(evt);
            }
        });

        jTextField39.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField39.setToolTipText(resourceMap.getString("jTextField39.toolTipText")); // NOI18N
        jTextField39.setEnabled(false);
        jTextField39.setName("jTextField39"); // NOI18N
        jTextField39.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField39KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField39KeyReleased(evt);
            }
        });

        jComboBox7.setEnabled(false);
        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox7KeyPressed(evt);
            }
        });

        jTextField38.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField38.setToolTipText(resourceMap.getString("jTextField38.toolTipText")); // NOI18N
        jTextField38.setEnabled(false);
        jTextField38.setName("jTextField38"); // NOI18N
        jTextField38.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField38.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField38KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField38KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jCheckBox4, 0, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextField39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6)
                    .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox5)
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jTextField40.setEditable(false);
        jTextField40.setBackground(resourceMap.getColor("jTextField40.background")); // NOI18N
        jTextField40.setFont(resourceMap.getFont("jTextField40.font")); // NOI18N
        jTextField40.setForeground(resourceMap.getColor("jTextField40.foreground")); // NOI18N
        jTextField40.setName("jTextField40"); // NOI18N

        jLabel36.setFont(resourceMap.getFont("jLabel36.font")); // NOI18N
        jLabel36.setForeground(resourceMap.getColor("jLabel36.foreground")); // NOI18N
        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N

        jLabel37.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel37.setForeground(resourceMap.getColor("jLabel37.foreground")); // NOI18N
        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setFont(resourceMap.getFont("jTextField5.font")); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        jTextField41.setEditable(false);
        jTextField41.setText(resourceMap.getString("jTextField41.text")); // NOI18N
        jTextField41.setName("jTextField41"); // NOI18N

        jComboBox9.setName("jComboBox9"); // NOI18N

        jButton11.setAction(actionMap.get("ModificaCliente")); // NOI18N
        jButton11.setFont(resourceMap.getFont("jButton11.font")); // NOI18N
        jButton11.setIcon(resourceMap.getIcon("jButton11.icon")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setToolTipText(resourceMap.getString("jButton11.toolTipText")); // NOI18N
        jButton11.setFocusable(false);
        jButton11.setMargin(new java.awt.Insets(2, 8, 2, 14));
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setPreferredSize(new java.awt.Dimension(115, 21));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel27.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel27.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setRequestFocusEnabled(false);

        jTextField7.setEditable(false);
        jTextField7.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField7.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText(resourceMap.getString("jTextField7.text")); // NOI18N
        jTextField7.setDisabledTextColor(resourceMap.getColor("jTextField7.disabledTextColor")); // NOI18N
        jTextField7.setFocusable(false);
        jTextField7.setName("jTextField7"); // NOI18N

        jTextField43.setFont(resourceMap.getFont("jTextField43.font")); // NOI18N
        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.setText(resourceMap.getString("jTextField43.text")); // NOI18N
        jTextField43.setName("jTextField43"); // NOI18N
        jTextField43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField43KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField43KeyReleased(evt);
            }
        });

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(30, 23));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel41.setFont(resourceMap.getFont("jLabel41.font")); // NOI18N
        jLabel41.setForeground(resourceMap.getColor("jLabel41.foreground")); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText(resourceMap.getString("jLabel41.text")); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(65, 65, 65))
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel29))
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37)
                                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton11.getAccessibleContext().setAccessibleDescription(resourceMap.getString("jButton11.AccessibleContext.accessibleDescription")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.jTextField2.getText().length() == 0) {
            Cliente_Comun();
            this.txtcliente.setEnabled(false);
            this.txtcliente.requestFocus();
        } else {
            new FormClientes(this, objetoventana, idbotica).show(true);
        }

}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        BusquedaProducto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
}//GEN-LAST:event_jButton4KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VerificaCaja();
        //jTextField2.requestFocus();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CerrarVentana();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void txtclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclienteKeyPressed

        RealizaOpciones(evt);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtdni.requestFocus();
        }
}//GEN-LAST:event_txtclienteKeyPressed

    private void comboboxtipodepagoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboboxtipodepagoMouseReleased
}//GEN-LAST:event_comboboxtipodepagoMouseReleased

    private void Habilita_Abono(boolean valor) {
        jCheckBox5.setEnabled(valor);
        jCheckBox5.setSelected(valor);
        jTextField38.setEnabled(valor);
        jTextField39.setEnabled(valor);
        jTextField38.setText("");
        jTextField39.setText("");
        jComboBox7.setEnabled(valor);
        Double restante = total;
        BigDecimal bd2 = new BigDecimal(restante);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        this.jTextField39.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        RecuperaBancos_1();
    }

    private void comboboxtipodepagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboboxtipodepagoItemStateChanged

        int indice = comboboxtipodepago.getSelectedIndex();
        idtipopago = listatipospagos.get(indice).getId_TipoPago();
        idtpred = listatipospagos.get(indice).getTPredondeo();
        this.jLabel11.setText("");

        if (poscant > 0) {
            if (esCredito(idtipopago)) {
                DeshabilitaPagosDiversos(false);
                this.jCheckBox1.setSelected(false);
                this.jCheckBox4.setSelected(false);
                this.jCheckBox2.setSelected(false);
                this.jCheckBox3.setSelected(false);
                jTextField17.setEnabled(false);
                jTextField19.setEnabled(false);
                jTextField10.setEnabled(false);
                jTextField18.setEnabled(false);
                jTextField12.setEnabled(false);
                jTextField13.setEnabled(false);
                this.jComboBox2.setEnabled(false);
                this.jLabel11.setText("  VENTA CON CREDITO AL PERSONAL ");
                this.jComboBox5.setEnabled(false);
                this.jComboBox2.setEnabled(false);
                recalcular();
                


            }
            if (idtipopago == 1) {
                this.jComboBox5.setEnabled(false);
                this.jTextField6.setEnabled(false);
                this.jComboBox2.setEnabled(false);
                this.jComboBox5.setEnabled(false);
                this.jTextField39.setText("");
                recalcular();
            } else {
                this.jLabel11.setText("");
            }

            if (idtipopago == 23) {
                Habilita_Abono(true);
                RecuperaBancos_1();
                jTextField39.requestFocus();
                recalcular();
            }
            if (idtipopago == 15){
                jTextField39.setText("");
                recalcular();
            }

            //if (idtipopago == 20) {
            if (idtpred.equals("0")) {

                jTextField44.setText("0.00"); //redondeo
                String total = jTextField21.getText();
                jTextField45.setText(total);
            }

        }
        poscant++;

        if (indice >= 0 && this.jTable2.getRowCount() > 0) {
            VerificaPagoFraccionado(idtipopago);
            this.jTextField18.setText("");
            //this.jTextField10.setText("");
            this.jTextField19.setText("");            
        }


    }//GEN-LAST:event_comboboxtipodepagoItemStateChanged

    private void recalcular(){

        Double Tot = Double.parseDouble(this.jTextField21.getText());
        Double SubTot = Double.parseDouble(this.jTextField8.getText());

        BigDecimal bd1 = new BigDecimal(SubTot);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTot = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(Tot);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        Tot = bd2.doubleValue();

        //AQUI
        
        String[] arr  = String.valueOf(Tot).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        //int deci = Integer.parseInt(arr[1]);
        String deci = arr[1];

        //String x = Integer.toString(deci);
        String x = deci;
        int contardeci = x.length();

        if (contardeci > 1){

        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
        String ultimodigito = String.valueOf(ultimoNumero);

            //if (ultimodigito.equals("0") || ultimodigito.equals("5")){
            if (ultimodigito.equals("0")){
                jTextField44.setText("0.00"); //redondeo
                jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                double igvauxiliar = total - SubTotal;

                BigDecimal bd3 = new BigDecimal(igvauxiliar);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                igvauxiliar = bd3.doubleValue();

                jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal

                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                    this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }
                if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                    this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                }


            }else{
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    double igvauxiliar = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(igvauxiliar);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    igvauxiliar = bd3.doubleValue();

                    jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                    jTextField44.setText(String.valueOf(redondeo)); //redondeo
                    jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                        this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }
                    if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                        this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                }else{

                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                        //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                        double ultimonuevo = Double.valueOf(ultimodigito);
                        Double redondeo = (ultimonuevo /100) * -1;
                        Double nuevototal = total - (ultimonuevo /100);
                        //total = nuevototal;
                        //SubTotal = (total / (1 + (IGV / 100)));
                        BigDecimal bdNT = new BigDecimal(nuevototal);
                        bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        nuevototal = bdNT.doubleValue();


                        jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                        jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                        double igvauxiliar = total - SubTotal;

                        BigDecimal bd3 = new BigDecimal(igvauxiliar);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        igvauxiliar = bd3.doubleValue();

                        jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                        jTextField44.setText(String.valueOf(redondeo)); //redondeo
                        jTextField45.setText(String.valueOf(nuevototal)); //Total a Pagar

                        if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
                            this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }
                        if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                            this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                            this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }

                    }

                }
            }
        }else{

            this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
            double igvauxiliar = total - SubTotal;

            BigDecimal bd3 = new BigDecimal(igvauxiliar);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            igvauxiliar = bd3.doubleValue();

            this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv
            jTextField44.setText("0.00");                                                                    //redondeo
            jTextField45.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar

            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 1) {
               this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
               this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            if (listatipospagos.get(this.comboboxtipodepago.getSelectedIndex()).getId_TipoPago() == 20) {
                this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField11.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            }

        }
    }
    private void comboboxtipodepagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboboxtipodepagoKeyPressed

        RealizaOpciones(evt);
}//GEN-LAST:event_comboboxtipodepagoKeyPressed

    private void comboboxtipodepagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboboxtipodepagoKeyReleased
}//GEN-LAST:event_comboboxtipodepagoKeyReleased

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
        RealizaOpciones(evt);

        if (evt.getKeyCode() == 127) {
            int fila = jTable2.getSelectedRow();
            if (fila >= 0) {
                EliminaProducto(fila);

                if (this.idtipopago == 1) {
                    Double t = Double.parseDouble(this.jTextField45.getText());
                    this.jTextField17.setText(String.valueOf(t)); //total
                    this.jTextField11.setText(String.valueOf(total));
                }

                if (comboboxdescuento.getSelectedIndex() > 0 && veces > 1) {
                    EliminaFilaDescuento();
                }
            }
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            InformacionProducto();
            this.jTabbedPane1.setEnabledAt(1, true);
            this.jTabbedPane1.setSelectedIndex(1);
            this.jTextField29.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (keypress) {

                filaseleccionado = jTable2.getSelectedRow();

                if (filaseleccionado >= 0) {
                     //*** COD_MAX para no mofiicar prod con promocion
                    String idproducto= String.valueOf(jTable2.getValueAt(filaseleccionado, 1));
                    String CodPromo=(String) mantProduc.Recupera_Codigo_Promo_Aeliminar(idproducto);//MAX_CODIGO PARA NO ELIMINAR PRODUCTOS CON PROMOCION
                    for (int j = 0; j < jTable2.getRowCount(); j++) {//MAX_CODIGO
                        if(CodPromo.equals(String.valueOf(jTable2.getValueAt(j, 1)))){
                              JOptionPane.showMessageDialog(this, " NO SE PUEDE MODIFICAR EL PRODUCTO, PORQUE POSEE UNA PROMOCION ", "Error", JOptionPane.ERROR_MESSAGE);
                              return;
                           }
                       }

            //***

                    String PROFOR = jTextField1.getText();
                    if (PROFOR.equals("")){
                        PROFOR = "000000";
                    }
                    modif = objProducto.RetornaPersonalModifica(PROFOR);

                    String[] listaMOdif;
                    String cadena1 = modif;
                    listaMOdif = cadena1.split("/");
                    int modificada = Integer.parseInt(listaMOdif[1]);

                    if (modificada == 1 ){
                        JOptionPane.showMessageDialog(this, " ESTA PROFORMA HA SIDO MODIFICADA, NO PUEDE EDITARLA ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }



                    ModificaCantidadPedida(filaseleccionado);
                    CalculaMontoIngresado();
                    if (comboboxdescuento.getSelectedIndex() > 0 && veces > 1) {
                        EliminaFilaDescuento();
                    }
                    if (this.idtipopago == 1) {
                        Double t = Double.parseDouble(this.jTextField45.getText());
                        this.jTextField17.setText(String.valueOf(t));//total
                        this.jTextField11.setText(String.valueOf(total));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "DEBE DE SELECCIONAR UN ITEM DE LA PROFORMA", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }//GEN-LAST:event_jTable2KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        //RealizaOpciones(evt);
    }//GEN-LAST:event_formKeyPressed

    
    
    private void comboboxtipodocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboboxtipodocumentoItemStateChanged
        try {

            Recupera_Serie_Numero();
            if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 11) {
                //jTextField14.setDocument(new LimitadorLetras(jTextField14, 8));
                EliminaFilaDescuento();
                ListDescuentos.removeAll(ListDescuentos);
                comboboxdescuento.removeAllItems();
                veces = 1;
                jLabel11.setText("");
            } else if (lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 12) {
                //jtextRUCDNI.setDocument(new LimitadorLetras(jtextRUCDNI, 11));
                jLabel11.setText(lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getDESCRIPCION());
            }else{
                jLabel11.setText("");
            }

            //Verificacion
            
            int valor = comboboxtipodocumento.getSelectedIndex();
       
       if(valor==0)
       {
           jLabel11.setText("Boleta de Venta");
           txtcliente.setText("");
           txtruc.setText("");
           txtdni.setEnabled(true);
           txtruc.setEnabled(false); 
           btnconsultarRUC.setEnabled(false); 
           txtdni.requestFocus();
       }
       
       if(valor==1)
       {
           jLabel11.setText("Factura Electrónica, INGRESE RUC");
           //txtcliente.setText("");
           txtdni.setText("");
           txtdni.setEnabled(false);
           btnconsultar.setEnabled(false); 
           txtruc.setEnabled(true);
           btnconsultarRUC.setEnabled(true); 
           txtruc.requestFocus();
       }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_comboboxtipodocumentoItemStateChanged

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        verdni();
        this.comboboxtipodocumento.requestFocus();
        
        //RecuperaCliente();
    }//GEN-LAST:event_txtdniActionPerformed

    private void txtrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucActionPerformed
        comboboxtipodepago.requestFocus();
        //RecuperaCliente();
    }//GEN-LAST:event_txtrucActionPerformed

    private void comboboxtipodocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxtipodocumentoActionPerformed
        String polizainical = this.jTextField5.getText();
        String polizafinal = this.jTextField41.getText();

        if (polizainical.length() > 0 && lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 12) {
            JOptionPane.showMessageDialog(this, " ERROR : NO SE PUEDE APLICAR FACTURA A ESTE TIPO DE VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
            comboboxtipodocumento.removeAllItems();
            RecuperaTipoVentas();
        } else if (polizainical.equals("") && lis_tipo_venta.get(comboboxtipodocumento.getSelectedIndex()).getId_Tipo_Venta() == 12) {
            VerificaCreditoEspecial(idcliente);

        } else {
            comboboxdescuento.removeAllItems();
            EliminaFilaDescuento();
            veces = 1;  
        }


    }//GEN-LAST:event_comboboxtipodocumentoActionPerformed

    private void txtdniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyPressed
        RealizaOpciones(evt);        
    }//GEN-LAST:event_txtdniKeyPressed

    private void RecuperaCliente() {
        //JOptionPane.showMessageDialog(this, "hola");
        String RUCDNICLIENTE = this.txtruc.getText().trim().toUpperCase();
        String V_RUCDNI = "";
        

        char[] campo = RUCDNICLIENTE.toCharArray();
        int tamano = campo.length;

        if (tamano == 8){
           V_RUCDNI = "1";
        }else if (tamano > 8 && tamano <= 11){
           V_RUCDNI = "2";        
        }else if (tamano == 0){
            JOptionPane.showMessageDialog(this, "Ingrese un Documento Valido","NORTFARMA",JOptionPane.INFORMATION_MESSAGE);
            this.txtruc.requestFocus();
            this.jTextField2.setText("");
            this.txtcliente.setText("");
            this.txtdni.setText("");
            this.jTextField42.setText("");
        }

        List<ListaDetalles> listDatosCliente = mantProduc.verificaDatosCliente(RUCDNICLIENTE,V_RUCDNI);
        int cantidad = listDatosCliente.size();

        if (cantidad > 0){
            this.jTextField2.setText(listDatosCliente.get(0).getidcliente().toString());
            this.txtcliente.setText(listDatosCliente.get(0).getrazonsocial().toString());
            jComboBox8.addItem(listDatosCliente.get(0).getdireccion());
            this.jTextField42.setText(listDatosCliente.get(0).getcorreo().toString());
            this.txtdni.setText(listDatosCliente.get(0).getdni1().toString());
        }else{
            JOptionPane.showMessageDialog(this, "Cliente no se encuentra Registrado","NORTFARMA",JOptionPane.INFORMATION_MESSAGE);
            this.jTextField2.setText("");
            this.txtcliente.setText("");
            this.txtdni.setText("");
            this.jTextField42.setText("");

            new FormClientes(this, objetoventana, idbotica).show(true);
        }
        
    }

    private void txtrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyPressed

        RealizaOpciones(evt);

         if(evt.getKeyCode()==10){
            String filtroRUC = txtruc.getText().toString().trim();
            TraerRUCParaAsignar(filtroRUC);
            
        }
    }//GEN-LAST:event_txtrucKeyPressed

    private void comboboxtipodocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboboxtipodocumentoKeyPressed

        RealizaOpciones(evt);
    }//GEN-LAST:event_comboboxtipodocumentoKeyPressed

    private void txtclienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusGained
    }//GEN-LAST:event_txtclienteFocusGained

    private void txtclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusLost
    }//GEN-LAST:event_txtclienteFocusLost

    private void txtdniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusGained
    }//GEN-LAST:event_txtdniFocusGained

    private void txtdniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusLost
    }//GEN-LAST:event_txtdniFocusLost

    private void txtrucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusGained
    }//GEN-LAST:event_txtrucFocusGained

    private void txtrucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusLost
    }//GEN-LAST:event_txtrucFocusLost

    private void comboboxtipodocumentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboboxtipodocumentoFocusGained
    }//GEN-LAST:event_comboboxtipodocumentoFocusGained

    private void comboboxtipodocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboboxtipodocumentoFocusLost
    }//GEN-LAST:event_comboboxtipodocumentoFocusLost

    private void comboboxtipodepagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboboxtipodepagoFocusGained
    }//GEN-LAST:event_comboboxtipodepagoFocusGained

    private void comboboxtipodepagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboboxtipodepagoFocusLost
    }//GEN-LAST:event_comboboxtipodepagoFocusLost

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        this.jComboBox8.requestFocus();
    }//GEN-LAST:event_txtclienteActionPerformed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
    }//GEN-LAST:event_jPanel4KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField29KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField29KeyPressed
        if (evt.KEY_PRESSED == 401) {
            limpiarInformacion();
            this.jTabbedPane1.setEnabledAt(1, false);
            this.jTabbedPane1.setSelectedIndex(0);
            this.jTabbedPane1.setEnabledAt(1, false);
            this.jTable2.requestFocus();
        }
    }//GEN-LAST:event_jTextField29KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    }//GEN-LAST:event_jButton7ActionPerformed

    private void TraerRUCParaAsignar(String EnviarRUC){
        try{
        listaPersonalOficina = logicaPersonal.ListaRUCBoticas(EnviarRUC);
        this.jComboBox8.removeAllItems();
        int i = jComboBox8.getSelectedIndex();
        
        if (listaPersonalOficina.size() > 1) {

            FrmDireccionesClientes obj1 = null;
            obj1 = new FrmDireccionesClientes(objetoventana, listaPersonalOficina);
            obj1.pack();
            obj1.setVisible(true);
            String rec_ruc = obj1.getRUC();
            String rec_dire = obj1.getDIRE();
            String rec_razonsocial = obj1.getRAZONSOCIAL();

            txtcliente.setText(rec_razonsocial);
            jComboBox8.addItem(rec_dire);

        }else{

             txtcliente.setText(listaPersonalOficina.get(0).getSRVC_RAZONSOCIAL());
             jComboBox8.addItem(listaPersonalOficina.get(0).getSRVC_DIRECCION());
        }
        } catch (Exception ex) {
            System.out.println("ERROR SIN CONEXION A INTERNET :" + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error: No se ha podido establecer una conexion a Oficina");
        }
        
    }

    private void NuecoCliente() {
        MuestraVentana ven = new MuestraVentana();
        NuevoCliente obj = new NuevoCliente(this, ven);
        obj.show();
    }
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        NuevaVenta();

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        InformacionProducto();
        this.jTabbedPane1.setEnabledAt(1, true);
        this.jTabbedPane1.setSelectedIndex(1);
        this.jTextField29.requestFocus();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ListadoProformas pe = new ListadoProformas(objetoventana, this, 1);
        pe.show(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu1KeyPressed
    }//GEN-LAST:event_jMenu1KeyPressed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        ListadoProformas pe = new ListadoProformas(objetoventana, this, 1);
        pe.show(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        RealizaOpciones(evt);
    }//GEN-LAST:event_formKeyReleased

    private void comboboxdescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxdescuentoActionPerformed
        if (comboboxdescuento.getSelectedIndex() > 0 && veces > 1) {
            if (Verifica_Productos_Afectos()) {
                EliminaFilaDescuento();
            } else {
                JOptionPane.showMessageDialog(this, " ERROR : DESCUENTO NO APLICABLE ", "Error", JOptionPane.ERROR_MESSAGE);
                Recupera_Posicion_Descuento();
            }

        }
        veces++;
    }//GEN-LAST:event_comboboxdescuentoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        NuecoCliente();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            ConsultaSaldo obsaldo = new ConsultaSaldo(objetoventana, idbotica, Integer.parseInt(this.jTextField2.getText()));
            obsaldo.show(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTextField18KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyReleased
        try {
            if (this.jTextField18.getText().trim().compareTo("") != 0) {

                String val = this.jTextField18.getText().trim();
                double valor = Double.parseDouble(val);

                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField18.setText("");
                    this.jTextField17.requestFocus();

                } else {
                    double sum = total - valor;
                    BigDecimal bd1 = new BigDecimal(sum);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    sum = bd1.doubleValue();

                    if (this.jCheckBox1.isSelected()) {
                        this.jTextField17.setText(String.valueOf(sum));
                    } else if (this.jCheckBox2.isSelected()) {
                        this.jTextField10.setText(String.valueOf(sum));
                    }

                    CalculaMontoIngresado();
                }

            }
        } catch (Exception ex) {
        }

}//GEN-LAST:event_jTextField18KeyReleased

    private void jTextField18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField18KeyPressed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox3.isSelected()) {

                jTextField18.setEnabled(true);
                jTextField6.setEnabled(true);
                jComboBox2.setEnabled(true);
                RecuperaTarjetas();

                if (this.jCheckBox1.isSelected())//CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);
                }
                if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                }
                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }

                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox3.setSelected(false);
                } else {

                    if (k == 1) {
                        if (this.jTextField17.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField17.getText().trim());
                        }
                    } else if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 2) {
                        if (this.jTextField10.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField10.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }

                    if (k > 0) {
                        //Double restante = total - mon;
                        Double restan = Double.parseDouble(jTextField45.getText());
                        Double restante = restan - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        this.jTextField18.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    } else {
                        this.jTextField18.setText(String.valueOf(total));
                    }
                }

            } else {
                this.jTextField6.setEnabled(false);
                this.jTextField18.setEnabled(false);
                this.jComboBox2.setEnabled(false);
                this.jTextField18.setText("");
                this.jTextField6.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        try {


            if (this.jTextField10.getText().trim().compareTo("") != 0) {
                String val = this.jTextField10.getText().trim();
                double valor = Double.parseDouble(val);


                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField10.setText("");
                    this.jTextField17.requestFocus();

                } else {
                    double sum = total - valor;
                    BigDecimal bd1 = new BigDecimal(sum);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    sum = bd1.doubleValue();

                    if (this.jCheckBox1.isSelected()) {
                        this.jTextField17.setText(String.valueOf(sum));
                    } else if (this.jCheckBox3.isSelected()) {
                        this.jTextField18.setText(String.valueOf(sum));
                    }

                    CalculaMontoIngresado();

                }
            }

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField10KeyPressed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox2.isSelected()) {

                jTextField10.setEnabled(true);
                jTextField13.setEnabled(true);
                jComboBox5.setEnabled(true);                
                RecuperaBancos();
                Habilita_cheque();

                if (this.jCheckBox1.isSelected()) //CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);
                }
                if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField19.setEnabled(true);
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox3.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 3;                    
                    this.jTextField18.setEnabled(true);
                    this.jTextField6.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }
                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }


                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox2.setSelected(false);
                } else {

                    if (k == 1) {
                        if (this.jTextField17.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField17.getText().trim());
                        }
                    } else if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 3) {
                        if (this.jTextField18.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField18.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }

                    if (k > 0) {
                        Double restante = total - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        this.jTextField10.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                    this.jTextField10.requestFocus();
                }

            } else {
                this.jTextField10.setEnabled(false);
                this.jTextField13.setEnabled(false);
                this.jTextField10.setText("");
                this.jTextField13.setText("");
                this.jComboBox5.setEnabled(false);
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            MuestraNota_Credito();
        }
}//GEN-LAST:event_jTextField12MouseClicked

    private void MuestraNota_Credito() {
        ListaNotasCreditos notadcr = new ListaNotasCreditos(objetoventana, idbotica);
        notadcr.pack();
        notadcr.setVisible(true);

        try {
            String document = notadcr.getNumero_Documento();
            if (document != null) {
                this.jTextField12.setText(notadcr.getNumero_Documento());
                this.jTextField19.setText(String.valueOf(notadcr.getTotal()));
                this.jTextField11.setText(String.valueOf(notadcr.getTotal()));
                CalculaMontoIngresado();
            }
        } catch (Exception ex) {
        }
    }

    private void jTextField19KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyReleased
}//GEN-LAST:event_jTextField19KeyReleased

    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
}//GEN-LAST:event_jTextField19KeyPressed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
}//GEN-LAST:event_jTextField19ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox4.isSelected()) {

                this.jTextField19.setEnabled(false);
                this.jTextField12.setEnabled(true);


                if (this.jCheckBox1.isSelected())//CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);

                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                    this.jComboBox5.setEnabled(true);
                }
                if (this.jCheckBox3.isSelected())//TARJETA
                {
                    cantpagos++;
                    k = 3;
                    this.jTextField6.setEnabled(true);
                    this.jTextField18.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }

                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }

                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox4.setSelected(false);
                } else {

                    this.jTextField12.setEnabled(true);
                    this.jTextField12.setEditable(true);
                    this.jTextField12.requestFocus();
                }
            } else {
                this.jTextField19.setEnabled(false);
                this.jTextField12.setEnabled(false);
                this.jTextField19.setText("");
                this.jTextField12.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jTextField17KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyReleased

        try {

            if (jTextField17.getText().trim().compareTo("") != 0) {

                String val = this.jTextField17.getText().trim();
                double valor = Double.parseDouble(val);
                BigDecimal bd1 = new BigDecimal(valor);
                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                valor = bd1.doubleValue();

                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField17.setText("");
                    this.jTextField11.setText("");
                    this.jTextField17.requestFocus();
                } else {
                    CalculaMontoIngresado();

                }
                RealizaOpciones(evt);

            }

        } catch (Exception ex) {
        }

}//GEN-LAST:event_jTextField17KeyReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox1.isSelected()) {

                jTextField17.setText("");
                jTextField17.setEnabled(true);

                if (this.jCheckBox4.isSelected()) ///NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;                    
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                    this.jComboBox5.setEnabled(true);
                }

                if (this.jCheckBox3.isSelected())//TARJETA
                {
                    cantpagos++;
                    k = 3;
                    this.jTextField18.setEnabled(true);
                    this.jTextField6.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }

                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }


                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox1.setSelected(false);
                } else {

                    if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 2) {
                        if (this.jTextField10.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField10.getText().trim());
                        }
                    } else if (k == 3) {
                        if (this.jTextField18.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField18.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }


                    if (k > 0) {
                        //Double restante = total - mon;
                        Double restan = Double.parseDouble(jTextField45.getText());
                        Double restante = restan - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);                        
                        this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    } else {
                        if (k == 0) {
                            //Double restante = total;
                            Double restante = Double.parseDouble(jTextField45.getText());
                            BigDecimal bd2 = new BigDecimal(restante);
                            bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            this.jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                        }

                    }

                    this.jTextField17.setEnabled(true);
                    this.jTextField17.requestFocus();

                }

            } else {
                jTextField17.setEnabled(false);
                jTextField17.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        FormEliminaNumeracion OBJ = new FormEliminaNumeracion(objventa, this);
        OBJ.pack();
        OBJ.setVisible(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        MuestraNota_Credito();
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void comboboxtipodepagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxtipodepagoActionPerformed
    }//GEN-LAST:event_comboboxtipodepagoActionPerformed

    private void comboboxdescuentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboboxdescuentoItemStateChanged
    }//GEN-LAST:event_comboboxdescuentoItemStateChanged

    private void comboboxdescuentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboboxdescuentoMouseEntered
    }//GEN-LAST:event_comboboxdescuentoMouseEntered

    private void jTextField38KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField38KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField38KeyPressed

    private void jTextField38KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField38KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38KeyReleased

    private void jTextField39KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField39KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField39KeyPressed

    private void jComboBox7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox7KeyPressed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed

        int k = 0;
        Double mon = 0.0;

        if (this.jCheckBox5.isSelected()) {
            //k = 5;
            jTextField39.setEnabled(true);
            jTextField38.setEnabled(true);
            jComboBox7.setEnabled(true);
            jTextField39.requestFocus();

            if (this.jCheckBox1.isSelected()) //CASH
            {
                cantpagos++;
                k = 1;
                this.jTextField17.setEnabled(true);
            }
            if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
            {
                cantpagos++;
                k = 4;
                this.jTextField19.setEnabled(true);
                this.jTextField12.setEnabled(true);
            }
            if (this.jCheckBox3.isSelected())//SI ES TARJETA
            {
                cantpagos++;
                k = 3;
                this.jTextField18.setEnabled(true);
                this.jTextField6.setEnabled(true);
                this.jComboBox2.setEnabled(true);
            }

            if (this.jCheckBox2.isSelected())//ES CHEQUE
            {
                cantpagos++;
                k = 2;
                this.jTextField10.setEnabled(true);
                this.jTextField13.setEnabled(true);
                this.jComboBox5.setEnabled(true);
            }

            if (cantpagos > 2) {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                cantpagos = 2;
            } else {

                if (k == 1) {
                    if (this.jTextField17.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField17.getText().trim());
                    }
                } else if (k == 4) {
                    if (this.jTextField19.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField19.getText().trim());
                    }
                } else if (k == 3) {
                    if (this.jTextField18.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField18.getText().trim());
                    }
                } else if (k == 5) {
                    if (this.jTextField39.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField39.getText().trim());
                    }
                } else if (k == 2) {
                    if (this.jTextField10.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField10.getText().trim());
                    }
                }

                if (k > 0) {
                    Double restante = total - mon;
                    BigDecimal bd2 = new BigDecimal(restante);
                    this.jTextField39.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                } else {
                    this.jTextField39.setText(String.valueOf(total));
                }

                this.jTextField39.requestFocus();
            }

        } else {
            this.jTextField38.setEnabled(false);
            this.jTextField39.setEnabled(false);
            this.jTextField38.setText("");
            this.jTextField39.setText("");
            this.jComboBox7.setEnabled(false);
            cantpagos--;
        }

        CalculaMontoIngresado();
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jComboBox8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox8KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox8KeyPressed

    private void jCheckBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox5KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jCheckBox5KeyPressed

    private void jCheckBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox3KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jCheckBox3KeyPressed

    private void jTextField39KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField39KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39KeyReleased

    private void jTextField17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField17KeyPressed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        char c;
        char caracter = evt.getKeyChar();
        //JOptionPane.showMessageDialog(this, caracter);
        //capturar el caracter digitado
        c=evt.getKeyChar();
        if(c<'0'|| c>'9')
        {
         if (caracter != '-'){
            evt.consume();//ignora el caracter digitado
            }
        }
        if (jTextField6.getText().length()==10){
        //jTextField6.setText("");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField43KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField43KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43KeyPressed

    private void jTextField43KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField43KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43KeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String DsctoGlobal = this.jTextField43.getText();
        this.jTextField43.setEnabled(false);
        this.jButton10.setEnabled(false);
        if (DsctoGlobal.equals("")){
            DsctoGlobal = "0";
        }
        Double IGVTOTAL = Double.valueOf(this.jTextField9.getText());
        Double SUBTOTALGENERAL = Double.valueOf(this.jTextField8.getText());
        Double TOTALGENERAL = Double.valueOf(this.jTextField21.getText());
        Double TOTALGENERALOCULTO = Double.valueOf(this.jTextField11.getText());
        
        if(Double.valueOf(DsctoGlobal) <= SUBTOTALGENERAL){

            if (TOTALGENERAL > 0 ){

                this.jTextField8.setText("");
                this.jTextField9.setText("");
                this.jTextField21.setText("");


                if (TOTALGENERALOCULTO > 0 && Double.valueOf(DsctoGlobal)== 0){

                    BigDecimal bd1 = new BigDecimal(SubTotal);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    SubTotal = bd1.doubleValue();

                    BigDecimal bd2 = new BigDecimal(total);
                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    total = bd2.doubleValue();

                    this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    double igvauxiliar = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(igvauxiliar);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    igvauxiliar = bd3.doubleValue();

                    this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                }else{


                    double SubTotalModif = SubTotal - Double.valueOf(DsctoGlobal);

                    BigDecimal bd4 = new BigDecimal(SubTotalModif);
                    bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    //SubTotal = bd4.doubleValue();

                    this.jTextField8.setText(bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    double igvauxiliar = SubTotalModif * 0.18;

                    if (IGVTOTAL > 0 ){
                        BigDecimal bd6 = new BigDecimal(igvauxiliar);
                        bd6 = bd6.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        igvauxiliar = bd6.doubleValue();
                        this.jTextField9.setText(bd6.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }else{
                        this.jTextField9.setText("0.0");
                    }

                    double TotalModif = SubTotalModif + igvauxiliar;
                    BigDecimal bd5 = new BigDecimal(TotalModif);
                    bd5 = bd5.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    //total = bd5.doubleValue();

                    this.jTextField21.setText(bd5.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                }
            }else{
                JOptionPane.showMessageDialog(this, "No se puede aplicar descuento global con monto 0", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "EL DESCUENTO GLOBAL NO DEBE SER MAYOR A LA VENTA", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        this.jTextField43.setText("");
        this.jTextField43.setText("0");
        this.jTextField43.setEnabled(true);
        this.jButton10.setEnabled(true);

        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();

        this.jTextField21.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        this.jTextField8.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        double igvauxiliar = total - SubTotal;

        BigDecimal bd3 = new BigDecimal(igvauxiliar);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        igvauxiliar = bd3.doubleValue();

        this.jTextField9.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarActionPerformed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnconsultarActionPerformed

    private void btnconsultarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarKeyPressed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnconsultarKeyPressed

    private void btnconsultarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarKeyReleased
        // TODO add your handling code here:
        //verdni();
    }//GEN-LAST:event_btnconsultarKeyReleased

    private void btnconsultarRUCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarRUCActionPerformed
        // TODO add your handling code here:
        String RUCENVIO = this.txtruc.getText().trim().toUpperCase();
        TraerRUCParaAsignar(RUCENVIO);
    }//GEN-LAST:event_btnconsultarRUCActionPerformed

    private void btnconsultarRUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarRUCKeyPressed
        // TODO add your handling code here:
        String RUCENVIO = this.txtruc.getText().trim().toUpperCase();
        TraerRUCParaAsignar(RUCENVIO);
    }//GEN-LAST:event_btnconsultarRUCKeyPressed

    private void btnconsultarRUCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarRUCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultarRUCKeyReleased

    private void limpiarInformacion() {
        this.jTextField15.setText("");
        this.jTextField16.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField20.setText("");
        this.jTextField22.setText("");
        this.jTextField23.setText("");
        this.jTextField24.setText("");
        this.jTextField25.setText("");
        this.jTextField26.setText("");
        this.jTextField27.setText("");
        this.jTextField28.setText("");
        this.jTextField32.setText("");
        this.jTextField33.setText("");
        this.jTextField34.setText("");
    }
    
    private void verdni(){
        try {
            String RUCDNICLIENTE = this.txtdni.getText().trim().toUpperCase();
            char[] campo = RUCDNICLIENTE.toCharArray();
            int tamano = campo.length;
            String dni = txtdni.getText(); 
            ClientesData cli = new ClientesData();   
            Clientes clientes = new Clientes();            
            clientes = cli.vercliente(dni);             
            if(clientes.Nombre_RazonSocial==null)
            {
                String data =datosDni_1(dni);              
                    txtcliente.setText(data);
                    String verificardatos = this.txtcliente.getText().trim().toUpperCase();
                    char[] campo2 = verificardatos.toCharArray();
                    int tamano2 = campo2.length;
                    if(tamano==8)
                    {
                        if(tamano2!=0)
                        {
                            guardacliente();
                        }else
                        {
                            JOptionPane.showMessageDialog(rootPane, "DNI no existe");
                            txtcliente.setText("CLIENTE COMUN");
                            txtdni.setText("");
                            txtdni.requestFocus();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                        txtcliente.setText("CLIENTE COMUN");
                        txtdni.setText("");
                        txtdni.requestFocus();
                    }
            }
            else   
            { 
                if(tamano== 8){
                txtcliente.setText(clientes.Nombre_RazonSocial);
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                    txtdni.requestFocus();
                }
            }             
        } catch (Exception ex) {
            System.out.println("Error al buscar :" + ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Error en la búsqueda, ingresar datos manualmente");
        }
        //jPanel12.requestFocus();
    }
    
    private void guardacliente() {
        String idbotica=id_botica;
        int idempresa=0;
        String ruccliente="";
        String dni = txtdni.getText();
        String nomcliente=txtcliente.getText();
        String direecion="";
        String telefono="";
        String email="";
        def = new Clientes(ruccliente, dni, nomcliente, direecion, telefono, idempresa, email);
            int resultado = logcliente.GuardarCliente(def, idbotica);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconsultar;
    private javax.swing.JButton btnconsultarRUC;
    private javax.swing.JComboBox comboboxdescuento;
    private javax.swing.JComboBox comboboxtipodepago;
    private javax.swing.JComboBox comboboxtipodocumento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtruc;
    // End of variables declaration//GEN-END:variables

private static String datosDni_1(java.lang.String dni) throws IOException_Exception {
        service.Verips_Service service = new service.Verips_Service();
        service.Verips port = service.getVeripsPort();
        return port.datosDni(dni);
    }

}

