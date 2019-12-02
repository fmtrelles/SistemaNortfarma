package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaClientes;
import javax.swing.table.DefaultTableModel;
import java.math.*;
import java.awt.event.*;
import javax.swing.*;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProforma;
import CapaLogica.LogicaVenta;
import CapaDatos.ClientesData;
import entidad.Clientes;
import entidad.Descuento;
import entidad.Laboratorios;
import entidad.ListaDetalles;
import entidad.Personal;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.Proforma;
import entidad.ProformaComparatorByOrdenTotal;
import entidad.TipoVenta;
import entidad.TiposPagos;
import entidad.UsuarioBotica;
import entidad.Venta;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;
import javax.xml.ws.WebServiceClient;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.sql.SQLException;
import service.IOException_Exception;

/**
 *
 * @author Miguel Gomez S.objetoventana
 */
public class Cotizacion extends JInternalFrame implements KeyListener {

    private DefaultTableModel tablaproforma;
    static ProductosPrecios productoPrecio = null;
    Object[] listadetalle = new Object[12];
    
    AplicacionVentas objpricipal;
    LogicaClientes objClientes = new LogicaClientes();
    LogicaProforma onjproforma = new LogicaProforma();
    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listaProductosdscto = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listadsctogratuita = new ArrayList<ProductosPrecios>();
    LogicaProducto objproducto = new LogicaProducto();
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    LogicaCPersonal logicapersonal = new LogicaCPersonal();
    RequisitosFactura objfactura = new RequisitosFactura();
    LogicaVenta objguardaventa = new LogicaVenta();
    LogicaPersonal lopersonal = new LogicaPersonal();
    List<Personal> listaPersonalOficina;
    LogicaPersonal logicaPersonal = new LogicaPersonal();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    List<Integer> listaempaque = new ArrayList<Integer>();
    List<Object> listsubtotales = new ArrayList<Object>();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    List<TiposPagos> listatiposMoneda = new ArrayList<TiposPagos>();
    List<Object> lisProdSinIGV = new ArrayList<Object>();
    List<TipoVenta> lis_tipo_venta = new ArrayList<TipoVenta>();
    private List<Proforma> listdetalleProforma = new ArrayList<Proforma>();
    List<Integer> nuevaPromocion = new ArrayList();
    List<Integer> cantidadElementos = new ArrayList();
    List<ListaDetalles> DetallePromo = new ArrayList<ListaDetalles>();
    List<Clientes> ListDescuentos = new ArrayList<Clientes>();
    List<Object> listaProductosVerifica = new ArrayList<Object>();
    
    /**
     * *******************************
     * VARIABLES PARA MI JTABLE --VENTAS ******************************
     */
    private int bandseleccion = 0, cantidadproductos = 1, podecimal = 4, unidad, fraccion, unifra, idcliente, idtipopago, idtipoventa, tpvta;
    private int podecimalPantalla = 2;
    public static boolean espromo;
    private double PVP, PVPx, descuento, parcial;
    TableColumnModel colModel;
    /**
     * *******************************
     */
    private Object stockempaque, empaque, stockfraccion;
    Proforma entidadproforma;
    FormClientes objcliente;    
    private String tipoprecio, idmedico, colegiatura, codprodespec, ruc = null, usuario;
    private Date fechaVenta, FechaRegistro;
    //private double IGV = 0, SubTotal = 0.0, OpGravada = 0.0, total = 0.0, OpExonerada=0.0, OpInafecta=0.0,OpGratuita=0.0,OpISC=0.0,miigv=0.0;
    private double IGV = 0.0, SubTotal = 0.0, OpGravada = 0.0, total = 0.0, OpExonerada = 0.0, OpInafecta = 0.0, OpGratuita = 0.0, OpISC = 0.0, miigv = 0.0, auxigv = 0.0,
            OpBonificacion = 0.0, OpDescuentoTotal = 0.0, OpDescuento = 0.0, preciounitario = 0.0,total_pigv=0.0,miigv_x = 0.0;
    private static int IdPersonalBoticaVenta;
    private static String id_botica;
    private String esgratuita1 = "", esgratuita2 = "";
    private String precbotiquin = "01";
    private int EsMultiDscto = 0;
    private String cantidad;
    private String modif;    
    // private int bandmultiple = 0;
    public static String cupon = "";
    public static String certificado = "";
    int cantbolsa = 0;
    public static String CUPONVTA="";
    public static String EsSAP = "";
    TableColumn col2, col, colu_6;
    BusquedaCliente objclien;    
    AplicacionVentas objventa;
    OpcionesMenu objsesion;
    Descuento p;
    Mant_Productos mantProduc = new Mant_Productos();
    JFrame objetoventana;
    int resul = -1;
    int ordenproducto = 1;
    BuscarProductos objproductos = null;
    int existe = 0;
    int contarpromo = 0;
    Clientes nuevocliente;
    LogicaClientes logcliente = new LogicaClientes();

    public Cotizacion(String op, AplicacionVentas obj, OpcionesMenu objet) {
        initComponents();
        objventa = obj;
        objsesion = objet;
        podecimal = objet.getCantidadDecimales();
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/tab_edit.png")));
        addKeyListener(this);
        precbotiquin = op;//1 NORMAL 2 BOTIQUIN
        if (precbotiquin.compareTo("02") == 0) {
            jLabel16.setText("VENTA BOTIQUIN");
        } else {
            jLabel16.setText(" ");
        }
        colu_6 = jTable2.getColumnModel().getColumn(6);
        tablaproforma = (DefaultTableModel) jTable2.getModel();
        jTextField11.setText(objpricipal.getUsuario());
        IdPersonalBoticaVenta = objpricipal.getId_personal_botica();
        setIdPersonalBoticaVenta(IdPersonalBoticaVenta);
        jTextField12.setText(String.valueOf(IdPersonalBoticaVenta));
        id_botica = objpricipal.getIdbotica();
        setId_botica(id_botica);
        colModel = jTable2.getColumnModel();
        AparienciaTabla();
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jTextField13.setVisible(false);
        jLabel11.setVisible(false);
        jTextField8.setVisible(false);
        txtdni.setDocument(new LimitadorLetras(txtdni, 8));
        txtruc.setDocument(new LimitadorLetras(txtruc, 11));
        fechaVenta = objlogiccafecha.RetornaFecha();
        Cliente_Comun();
        RecuperaTipoVentas();
        RecuperaTiposPagos();
        Muestra_Moneda();
        jComboBox9.setEnabled(false);
        txtcliente.setText("CLIENTE COMUN");
        //txtdni.setFocusable(true);
        jLabel29.setVisible(false);
        jLabel30.setVisible(false);
        jTextField15.setVisible(false);
        jTextField17.setVisible(false);
        //Buscar Producto
        jButton10.setVisible(false);
        setResizable(false);
        jLabel25.setText("");
        AutoCompleteDecorator.decorate(jComboBox4);
        JTextComponent editor;
        editor = (JTextComponent) jComboBox4.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtdni.requestFocus();
                }
                RealizaOpciones(e);
            }
        });
    }

    public static int getIdPersonalBoticaVenta() {
        return IdPersonalBoticaVenta;
    }

    public static void setIdPersonalBoticaVenta(int IdPersonalBoticaVenta) {
        Cotizacion.IdPersonalBoticaVenta = IdPersonalBoticaVenta;
    }

    public static String getId_botica() {
        return id_botica;
    }

    public static void setId_botica(String id_botica) {
        Cotizacion.id_botica = id_botica;
    }

    private void RecuperaTipoVentas() {
        lis_tipo_venta = objfactura.Retorna_Tipos_Ventas();
        for (int i = 0; i < lis_tipo_venta.size(); i++) {
            jComboBox2.addItem(lis_tipo_venta.get(i).getDESCRIPCION());
        }
    }

    public static ProductosPrecios getProductoPrecio() {
        return productoPrecio;
    }

    public static void setProductoPrecio(ProductosPrecios productoPrecio) {
        BuscarProductos.productoPrecio = productoPrecio;
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable2.getColumnModel());
        cabecera.setReorderingAllowed(false);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(7).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(8).setCellRenderer(tcr);
        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.CENTER);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(tcenter);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        DefaultTableCellRenderer tleft = new DefaultTableCellRenderer();
        tleft.setHorizontalAlignment(SwingConstants.LEFT);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(tleft);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(tleft);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(tleft);
        colu_6 = jTable2.getColumnModel().getColumn(6);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);
    }

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();
        int stkempaque;
        try {
            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, id_botica);
            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = Integer.parseInt(empaque.toString());
                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }
                //RECUPERO MI STOCK DEL EMPAQUIE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = Integer.parseInt(stockempaque.toString());
                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = Integer.parseInt(stockfraccion.toString());
                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unidad * empaque_tmp + fraccion;
                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }
        return resultado;
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
        String valor = "";
        cantidad = cantidad.trim();
        Character w;
        valor = valor.trim();
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

    private void AsignaPrecio(ProductosPrecios precios, int idpromo, int bandmultiple, int EsMultiDcto, int precfijo, String  Ncertificado) {
        int indfila = cantidadproductos - 1;
        double parcial1 = 0;
        String OperadorRec = "";
        int porprecio = 0; 
        int porpreciofijo=0;
        double pv = 0;
        double desc = 0;

        int porprecioEsSAP = 0;
        Double montoDscto = 0.00;
        Double montoDscto1 = 0.00;
        Integer IdOperadorRec = 0;
        String estadopromo = "0";
        int porcupon = 0;
        int savecupon = 0;
        int _Blister = 0;
        int porEsSAP = 0;
        int multiple1 = 0;
        cupon="";
        CUPONVTA="";
        int empaque1 = Integer.parseInt(empaque.toString());
        if (objProducto.VerificaEsGratuita(precios.getProductoBotica().getProducto().getIdProducto()) == 1) {//MAX_CODIGO si es gratuita no se puede vender sola ni mas de dos veces
            int Cant[] = objProducto.PromoEstaEnDosGrupos(precios.getProductoBotica().getProducto().getIdProducto(), idpromo);//MAX_CODIGO
            if (Cant[0] >= 2) {
                if (VerificarSiEstaTablaONotieneProdAsocTabla(precios.getProductoBotica().getProducto().getIdProducto(), Cant) == true) {
                    JOptionPane.showMessageDialog(this, "LO SENTIMOS ESTE PRODUCTO TIENE PROMOCION GRATUITA ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }//MAX_CODIGO
        listadetalle = null;
        Object[] listadetalle = new Object[19];
        if (precbotiquin.compareTo("02") == 0 && fraccion > 0) {
            JOptionPane.showMessageDialog(this, "LO SENTIMOS LOS PRECIOS DE  BOTIQUIN \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (empaque1 == 0 && fraccion > 0) {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String nomproducto = precios.getProductoBotica().getProducto().getDescripcion();
                    tipoprecio = precios.getTipoPrecio().getId_Tipo_Precio();
                    _Blister = precios.getProductoBotica().getProducto().getBlister();
                    
                    if (_Blister > 0) {
                        
                        if (precios.getVprecBotiquin().equals("1")) {
                            if (unidad > 0 && fraccion == 0) {
                                pv = Double.parseDouble(String.valueOf(precios.getprecbotiquin()));
                            } else if (unidad == 0 && fraccion > 0) {
                                //pv = Double.parseDouble(String.valueOf(precios.getbotiquinfraccion()));
                                pv = objproducto.Recupera_PrecioVenta(precios.getProductoBotica().getProducto().getIdProducto(),7);
                            }
                        }else{
                        
                            if (unidad > 0 && fraccion == 0) {
                                pv = precios.getPrecio_Venta(); 
                            }else{

                             pv = objproducto.Recupera_PrecioVenta(precios.getProductoBotica().getProducto().getIdProducto(),7);
                            }
                        }
                        
                    }else{
                        if (precios.getVprecBotiquin().equals("1")) {
                            if (unidad > 0 && fraccion == 0) {
                                pv = Double.parseDouble(String.valueOf(precios.getprecbotiquin()));
                            } else if (unidad == 0 && fraccion > 0) {
                                pv = Double.parseDouble(String.valueOf(precios.getbotiquinfraccion()));
                            }
                        } else {
                            pv = precios.getPrecio_Venta(); // original
                        }
                    }
                    
                
                    listaempaque.add(empaque1);
                    String idproducto = precios.getProductoBotica().getProducto().getIdProducto();
                    Double descuento2 = 0.00;
                    int idprom = 0;
                    Integer recuperaUnidadesminimo = 0;
                    int orden = 0;
                    int cantidad = 0;
                    int Esrecarga = 0;
                    ProductosPromociones obj = null;                    
                    Esrecarga = objProducto.Verifica_EsRecarga(idproducto);
                    if (Esrecarga == 1) {
                        IngreseOperadorRecarga objRec = new IngreseOperadorRecarga(this, id_botica, idproducto);
                        objRec.setDefaultCloseOperation(0);
                        objRec.setVisible(true);
                        IdOperadorRec = objRec.getIdOperadorRecarga();
                        OperadorRec = objRec.getDescOperadorRecarga();
                    }
                    if (objproductos.isEspromo()) {  //pregunta si el campo promocion de la tabla esta lleno
                        estadopromo = "1";
                        cantidad = objProducto.Verifica_Promocion(idproducto); //verifica si existe en la tabla promociones
                        List<ListaDetalles> listPromocion = mantProduc.verificaPromocion(idproducto, idpromo);//recupero los datos de la promo                        
                        cantidad = 1;
                        if (cantidad > 1) {
                            obj = new ProductosPromociones(objetoventana, nomproducto, idproducto);
                            obj.pack();
                            obj.setVisible(true);
                            idprom = obj.getIdpromo();
                            descuento2 = obj.getDescuento();
                            recuperaUnidadesminimo = 1;
                        } else {
                            orden = listPromocion.get(0).getOrden();
                            int vfagrupoparamostrar = 0;
                            if (bandmultiple == 1){
                            vfagrupoparamostrar = objProducto.MostrarPantallaMultiple(idproducto, 0);                
                                if (vfagrupoparamostrar == 0){
                                    descuento2 = 0.0;
                                }else{
                                    descuento2 = armarPromocion(listPromocion, idproducto, unidad, idpromo,bandmultiple); // RECUPERA CAMPO DESCUENTO promociones
                                }
                            }else{
                                descuento2 = armarPromocion(listPromocion, idproducto, unidad, idpromo,bandmultiple); // RECUPERA CAMPO DESCUENTO promociones                                
                            }
                            recuperaUnidadesminimo = retornaUnidades(listPromocion, unidad); // RECUPERA CAMPO COUNT ID_PROMO
                        }
                        //}
                    }
                    listadetalle[4] = unidad;
                    if (objProducto.Verifica_Es_Manual(idproducto) == 1) {
                        int p = JOptionPane.showConfirmDialog(null, "¿VERIFICÓ LOS DATOS DE LA RECETA Y DNI DEL CLIENTE?", "Confirmar",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (p == JOptionPane.YES_OPTION) {
                            if (unidad <= objProducto.Verifica_Cantidad_Producto(idproducto)) {
                                listadetalle[0] = cantidadproductos;
                                listadetalle[1] = idproducto;
                                listadetalle[2] = nomproducto;
                                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                                listadetalle[4] = unidad;
                                listadetalle[5] = fraccion;
                                if (unidad == 0 && fraccion > 0) {
                                    if (objProducto.Verifica_Es_Promo_Frac(idproducto) == 1) {
                                        BigDecimal bd = new BigDecimal(pv);
                                        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        pv = bd.doubleValue();
                                        listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        desc = precios.getDescuento_Venta();
                                        BigDecimal bd1 = new BigDecimal(desc);
                                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        desc = bd1.doubleValue();
                                        PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                        parcial = PVPx * unidad;
                                        Double ss = Double.valueOf(descuento2);
                                        descuento = ss;
                                        //Double montoDsctofraccion = 0.00; -- se agregara la funcion aqui para hallar desc de fraccion
                                        Double montoDsctofraccion = ((PVPx / empaque1) * (descuento)) / 100 * fraccion;
                                        montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                        if (empaque1 > 0) {
                                            parcial1 = (fraccion * PVPx) / empaque1;
                                        }
                                        parcial = parcial + parcial1;

                                        /**
                                         * ******************************************
                                         * CONVERTIR DOUBLE A DOS POSICIONES
                                         * DECIMALES
                                         * *******************************************
                                         */
                                        BigDecimal bd2 = new BigDecimal(PVPx);
                                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        PVPx = bd2.doubleValue();

                                        BigDecimal bd3 = new BigDecimal(parcial);
                                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        parcial = bd3.doubleValue();

                                        listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[9] = "0";
                                        listadetalle[10] = estadopromo;
                                        listadetalle[11] = "0";
                                        listadetalle[12] = "0";
                                        double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                                        listadetalle[13] = igvaux;
                                        listadetalle[14] = "0";
                                        listadetalle[15] = "0";
                                        listadetalle[16] = "";
                                        listadetalle[17] = "";
                                        listadetalle[18] = Ncertificado;

                                        tablaproforma.addRow(listadetalle);

                                        CalculaMontos(igvaux, indfila);

                                        if (jComboBox3.getItemCount() > 0) {
                                            AsignaCredito();
                                        }
                                        listaProductosVerifica.add(listadetalle);
                                        if (ss > 0.00) {
                                            listadetalle = new Object[19];
                                            listadetalle[0] = jTable2.getRowCount() + 1;
                                            if (cantidad > 1) {
                                                listadetalle[1] = obj.getCodPromocion();
                                                listadetalle[2] = obj.getDescripPromocion();
                                            } else {
                                                listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, 0,0);
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

                                            if (unidad > 0 && orden == 1) {
                                            } else {
                                                tablaproforma.addRow(listadetalle);
                                                tablaproforma.setValueAt(1, indfila + 1, 4);
                                                listaempaque.add(empaque1);
                                            }

                                            CalculaMontos(igvaux, indfila + 1);
                                            listaProductosVerifica.add(listadetalle);
                                        }

                                        RcepueraTipoPago();
                                        jTable2.requestFocus();
                                        jTable2.changeSelection(indfila, 7, false, true);

                                        if (igvaux < 1) {
                                            lisProdSinIGV.add(idproducto);
                                            col = jTable2.getColumnModel().getColumn(0);
                                            col.setCellRenderer(new ColoredTableCellRenderer());
                                            col = jTable2.getColumnModel().getColumn(1);
                                            col.setCellRenderer(new ColoredTableCellRenderer());
                                        }
                                    } else {
                                        BigDecimal bd = new BigDecimal(pv);
                                        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        pv = bd.doubleValue();

                                        listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                                        PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                        parcial = PVPx * unidad;

                                        if (empaque1 > 0) {
                                            parcial1 = (fraccion) * PVPx / empaque1;
                                        }

                                        parcial = parcial + parcial1;

                                        BigDecimal bd2 = new BigDecimal(PVPx);
                                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        PVPx = bd2.doubleValue();

                                        BigDecimal bd3 = new BigDecimal(parcial);
                                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        parcial = bd3.doubleValue();

                                        listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[9] = "0";
                                        listadetalle[10] = estadopromo;
                                        listadetalle[11] = "0";
                                        listadetalle[12] = "0";

                                        double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                                        listadetalle[13] = igvaux;
                                        listadetalle[14] = "0";
                                        listadetalle[15] = "0";
                                        listadetalle[16] = "";
                                        listadetalle[17] = "";
                                        listadetalle[18] = Ncertificado;

                                        tablaproforma.addRow(listadetalle);

                                        CalculaMontos(igvaux, indfila);

                                        if (jComboBox3.getItemCount() > 0) {
                                            AsignaCredito();
                                        }
                                        listaProductosVerifica.add(listadetalle);
                                        listadetalle = new Object[19];
                                        listadetalle[0] = jTable2.getRowCount() + 1;
                                        if (cantidad > 1) {
                                            listadetalle[1] = obj.getCodPromocion();
                                            listadetalle[2] = obj.getDescripPromocion();
                                        } else {
                                            listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, 0,0);
                                            listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                        }

                                        listadetalle[4] = recuperaUnidadesminimo;
                                        listadetalle[5] = 0;

                                        if (fraccion > 0) {
                                            CalculaMontos(igvaux, indfila);
                                            listaProductosVerifica.add(listadetalle);
                                        } else {
                                            CalculaMontos(igvaux, indfila + 1);
                                            listaProductosVerifica.add(listadetalle);
                                        }

                                        RcepueraTipoPago();
                                        jTable2.requestFocus();
                                        jTable2.changeSelection(indfila, 7, false, true);

                                        if (igvaux < 1) {
                                            lisProdSinIGV.add(idproducto);
                                            col = jTable2.getColumnModel().getColumn(0);
                                            col.setCellRenderer(new ColoredTableCellRenderer());
                                        }
                                    }

                                } else {

                                    BigDecimal bd = new BigDecimal(pv);
                                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    pv = bd.doubleValue();

                                    listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                                    desc = precios.getDescuento_Venta();
                                    BigDecimal bd1 = new BigDecimal(desc);
                                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    desc = bd1.doubleValue();

                                    PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                    parcial = PVPx * unidad;

                                    Double ss = Double.valueOf(descuento2);
                                    descuento = ss;
                                    //Double montoDsctofraccion = 0.00; -- se agregara la funcion aqui para hallar desc de fraccion
                                    Double montoDsctofraccion = 0.00;
                                    montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                                    if (empaque1 > 0) {
                                        parcial1 = (fraccion * PVPx) / empaque1;
                                    }
                                    parcial = parcial + parcial1;

                                    /**
                                     * ******************************************
                                     * CONVERTIR DOUBLE A DOS POSICIONES
                                     * DECIMALES
                                     * *******************************************
                                     */
                                    BigDecimal bd2 = new BigDecimal(PVPx);
                                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    PVPx = bd2.doubleValue();

                                    BigDecimal bd3 = new BigDecimal(parcial);
                                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    parcial = bd3.doubleValue();

                                    listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[9] = "0";
                                    listadetalle[10] = estadopromo;
                                    listadetalle[11] = "0";
                                    listadetalle[12] = "0";

                                    double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                                    listadetalle[13] = igvaux;
                                    listadetalle[14] = "0";
                                    listadetalle[15] = "0";
                                    listadetalle[16] = "";
                                    listadetalle[17] = "";
                                    listadetalle[18] = Ncertificado;

                                    tablaproforma.addRow(listadetalle);

                                    CalculaMontos(igvaux, indfila);

                                    if (jComboBox3.getItemCount() > 0) {
                                        AsignaCredito();
                                    }
                                    listaProductosVerifica.add(listadetalle);
                                    if (ss > 0.00) {
                                        listadetalle = new Object[19];
                                        listadetalle[0] = jTable2.getRowCount() + 1;
                                        if (cantidad > 1) {
                                            listadetalle[1] = obj.getCodPromocion();
                                            listadetalle[2] = obj.getDescripPromocion();
                                        } else {
                                            listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, 0,0);
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

                                        if (unidad > 0 && orden == 1) {
                                        } else {
                                            tablaproforma.addRow(listadetalle);
                                            tablaproforma.setValueAt(1, indfila + 1, 4);
                                            listaempaque.add(empaque1);
                                        }

                                        CalculaMontos(igvaux, indfila + 1);
                                        listaProductosVerifica.add(listadetalle);
                                    }

                                    RcepueraTipoPago();
                                    jTable2.requestFocus();
                                    jTable2.changeSelection(indfila, 7, false, true);

                                    if (igvaux < 1) {
                                        lisProdSinIGV.add(idproducto);
                                        col = jTable2.getColumnModel().getColumn(0);
                                        col.setCellRenderer(new ColoredTableCellRenderer());
                                        col = jTable2.getColumnModel().getColumn(1);
                                        col.setCellRenderer(new ColoredTableCellRenderer());
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "CANTIDAD PEDIDA ES MAYOR A " + objProducto.Verifica_Cantidad_Producto(idproducto) + " UNIDADES\nNO SE AGREGARA EL DESCUENTO A ESTE PRODUCTO", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

                                listadetalle[0] = cantidadproductos;
                                listadetalle[1] = idproducto;
                                listadetalle[2] = nomproducto;
                                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                                listadetalle[4] = unidad;
                                listadetalle[5] = fraccion;

                                BigDecimal bd = new BigDecimal(pv);
                                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                pv = bd.doubleValue();

                                listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                                //                            double desc = precios.getDescuento_Venta();
                                //                            BigDecimal bd1 = new BigDecimal(desc);
                                //                            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                //                            desc = bd1.doubleValue();
                                PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                parcial = PVPx * unidad;

                                //                            Double ss = Double.valueOf(descuento2);
                                //                            descuento = ss;
                                //                            Double montoDsctofraccion = 0.00;
                                //                            Double montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                if (empaque1 > 0) {
                                    parcial1 = (fraccion * PVPx) / empaque1;
                                }

                                parcial = parcial + parcial1;

                                /**
                                 * ******************************************
                                 * CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                 * *******************************************
                                 */
                                BigDecimal bd2 = new BigDecimal(PVPx);
                                bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                PVPx = bd2.doubleValue();

                                BigDecimal bd3 = new BigDecimal(parcial);
                                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                parcial = bd3.doubleValue();

                                listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[9] = "0";
                                listadetalle[10] = estadopromo;
                                listadetalle[11] = "0";
                                listadetalle[12] = "0";

                                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                                listadetalle[13] = igvaux;
                                listadetalle[14] = "0";
                                listadetalle[15] = "0";
                                listadetalle[16] = "";
                                listadetalle[17] = "";
                                listadetalle[18] = Ncertificado;

                                tablaproforma.addRow(listadetalle);

                                CalculaMontos(igvaux, indfila);

                                if (jComboBox3.getItemCount() > 0) {
                                    AsignaCredito();
                                }
                                listaProductosVerifica.add(listadetalle);
                                //                            if (ss > 0.00) {
                                listadetalle = new Object[19];
                                listadetalle[0] = jTable2.getRowCount() + 1;
                                if (cantidad > 1) {
                                    listadetalle[1] = obj.getCodPromocion();
                                    listadetalle[2] = obj.getDescripPromocion();
                                } else {
                                    listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, 0,0);
                                    listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                }

                                listadetalle[4] = recuperaUnidadesminimo;
                                listadetalle[5] = 0;
                                //                                if (fraccion > 0) {
                                //                                    montoDsctofraccion = (orden * PVPx) / empaque1;
                                //                                }
                                //                                if (orden == 1) {
                                //                                    montoDscto = 0.0;
                                //                                }
                                //
                                //                                montoDscto = montoDscto + montoDsctofraccion;
                                //                                BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                                //                                bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                //                                listadetalle[6] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                //                                listadetalle[7] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                //                                listadetalle[8] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                //                                listadetalle[9] = "1";
                                //                                listadetalle[10] = "1";
                                //                                listadetalle[11] = "0";
                                //                                if (unidad > 0 && orden == 1) {
                                //                                } else {
                                //                                    tablaproforma.addRow(listadetalle);
                                //                                    tablaproforma.setValueAt(1, indfila + 1, 4);
                                //                                    listaempaque.add(empaque1);
                                //                                }
                                CalculaMontos(igvaux, indfila + 1);
                                listaProductosVerifica.add(listadetalle);
                                //                            }

                                RcepueraTipoPago();
                                jTable2.requestFocus();
                                jTable2.changeSelection(indfila, 7, false, true);

                                if (igvaux < 1) {
                                    lisProdSinIGV.add(idproducto);
                                    col = jTable2.getColumnModel().getColumn(0);
                                    col.setCellRenderer(new ColoredTableCellRenderer());
//                                    col = jTable2.getColumnModel().getColumn(1);
//                                    col.setCellRenderer(new ColoredTableCellRenderer());
                                }
                            }

                        }

                        if (p == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(this, "NO SE AGREGARA EL DESCUENTO A ESTE PRODUCTO", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

                            listadetalle[0] = cantidadproductos;
                            listadetalle[1] = idproducto;
                            listadetalle[2] = nomproducto;
                            listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                            listadetalle[4] = unidad;
                            listadetalle[5] = fraccion;

                            //System.out.println("La cantidad de productos real es: "+cantidadproductos+",unidades: "+unidad+" y fraccion: "+fraccion);
                            BigDecimal bd = new BigDecimal(pv);
                            bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            pv = bd.doubleValue();

                            listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

//                            double desc = precios.getDescuento_Venta();
//                            BigDecimal bd1 = new BigDecimal(desc);
//                            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
//                            desc = bd1.doubleValue();
                            PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                            parcial = PVPx * unidad;

//                            Double ss = Double.valueOf(descuento2);
//                            descuento = ss;
//                            Double montoDsctofraccion = 0.00;
//                            Double montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                            if (empaque1 > 0) {
                                parcial1 = (fraccion * PVPx) / empaque1;
                            }

                            parcial = parcial + parcial1;

                            /**
                             * ******************************************
                             * CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                             * *******************************************
                             */
                            BigDecimal bd2 = new BigDecimal(PVPx);
                            bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            PVPx = bd2.doubleValue();

                            BigDecimal bd3 = new BigDecimal(parcial);
                            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            parcial = bd3.doubleValue();

                            listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[9] = "0";
                            listadetalle[10] = estadopromo;
                            listadetalle[11] = "0";
                            listadetalle[12] = "0";
                            double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                            listadetalle[13] = igvaux;
                            listadetalle[14] = "0";
                            listadetalle[15] = "0";
                            listadetalle[16] = "";
                            listadetalle[17] = "";
                            listadetalle[18] = Ncertificado;

                            tablaproforma.addRow(listadetalle);

                            CalculaMontos(igvaux, indfila);

                            if (jComboBox3.getItemCount() > 0) {
                                AsignaCredito();
                            }
                            listaProductosVerifica.add(listadetalle);
//                            if (ss > 0.00) {
                            listadetalle = new Object[19];
                            listadetalle[0] = jTable2.getRowCount() + 1;
                            if (cantidad > 1) {
                                listadetalle[1] = obj.getCodPromocion();
                                listadetalle[2] = obj.getDescripPromocion();
                            } else {
                                listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, 0,0);
                                listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                            }

                            listadetalle[4] = recuperaUnidadesminimo;
                            listadetalle[5] = 0;
//                                if (fraccion > 0) {
//                                    montoDsctofraccion = (orden * PVPx) / empaque1;
//                                }
//                                if (orden == 1) {
//                                    montoDscto = 0.0;
//                                }
//
//                                montoDscto = montoDscto + montoDsctofraccion;
//                                BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
//                                bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

//                                listadetalle[6] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
//                                listadetalle[7] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
//                                listadetalle[8] = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
//                                listadetalle[9] = "1";
//                                listadetalle[10] = "1";
//                                listadetalle[11] = "0";
//                                if (unidad > 0 && orden == 1) {
//                                } else {
//                                    tablaproforma.addRow(listadetalle);
//                                    tablaproforma.setValueAt(1, indfila + 1, 4);
//                                    listaempaque.add(empaque1);
//                                }
                            CalculaMontos(igvaux, indfila + 1);
                            listaProductosVerifica.add(listadetalle);
//                            }

                            RcepueraTipoPago();
                            jTable2.requestFocus();
                            jTable2.changeSelection(indfila, 7, false, true);

                            if (igvaux < 1) {
                                lisProdSinIGV.add(idproducto);
                                col = jTable2.getColumnModel().getColumn(0);
                                col.setCellRenderer(new ColoredTableCellRenderer());
//                                col = jTable2.getColumnModel().getColumn(1);
//                                col.setCellRenderer(new ColoredTableCellRenderer());
                            }
                        }
                    } else {  //Aqui
                        listadetalle[0] = cantidadproductos;
                        listadetalle[1] = idproducto;
                        listadetalle[2] = nomproducto + " " + OperadorRec;
                        listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                        listadetalle[4] = unidad;
                        listadetalle[5] = fraccion;

                        BigDecimal bd = new BigDecimal(pv);
                        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        pv = bd.doubleValue();

                        listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                        if (precios.getVprecBotiquin().equals("1") || _Blister > 0 ) {

                            if (unidad > 0 && fraccion == 0) {

                                desc = precios.getdscto02();
                                BigDecimal bd1 = new BigDecimal(desc);
                                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd1.doubleValue();

                                PVPx = pv - (pv * (desc / 100));
                                //PVPx = Double.parseDouble(String.valueOf(precios.getprecbotiquin()));
                                parcial = PVPx * unidad;
                            } else if (unidad == 0 && fraccion > 0) {

                                desc = precios.getdscto01();
                                BigDecimal bd1 = new BigDecimal(desc);
                                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd1.doubleValue();

                                PVPx = pv - (pv * (desc / 100));
                                //PVPx = Double.parseDouble(String.valueOf(precios.getbotiquinfraccion()));
                                parcial = PVPx * unidad;
                            }

                        } else {

                            desc = precios.getDescuento_Venta();
                            BigDecimal bd1 = new BigDecimal(desc);
                            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            desc = bd1.doubleValue();

                            PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                            /*aqui*/
                            parcial = PVPx * unidad;
                        }

                        Double ss = Double.valueOf(descuento2);
                        descuento = ss;
                        Double montoDsctofraccion = 0.00;
                        if (fraccion > 0) {
                            montoDsctofraccion = (orden * PVPx) / empaque1;
                        }

                        //Double montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                        porprecio = objProducto.Verifica_Promo_PorPrecio(idproducto, 1, idpromo);
                        if (porprecio == 1) {
                            porcupon = objProducto.Verifica_Promo_PorPrecio(idproducto, 2, idpromo);
                            if (porcupon == 1) {
                                
                               CodBarrasCupon objProm = null;                                
                               objProm = new CodBarrasCupon(objetoventana, "", "");
                               objProm.pack();
                               objProm.setVisible(true);
                               
                               
                               cupon = objProm.getCodbarras();
                               CUPONVTA = objProm.getNumcupon();
                               
                                
                                
                                //JOptionPane p = new JOptionPane();
                                //cupon = p.showInputDialog(this, "Ingrese Numero de Cupon  ? ", "CUPON DESCUENTO", JOptionPane.QUESTION_MESSAGE);

                                if ((!String.valueOf(cupon).isEmpty()) && (!String.valueOf(CUPONVTA).isEmpty())) {

                                    if (cupon != null) {

                                        if (cupon.compareTo("0") != 0) {

                                            if (!String.valueOf(cupon).isEmpty() || cupon != null) {

                                                cupon = cupon.toUpperCase();

                                                savecupon = objProducto.Verifica_Promo_PorPrecio1(idproducto, cupon, 1); // gino
                                                //savecupon = objProducto.Verifica_Promo_PorPrecio(idproducto,1); // gino

                                                if (savecupon == 2) {
                                                    JOptionPane.showMessageDialog(this, "ESTE CUPON YA ESTA REGISTRADO ", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

                                                } else {
                                                    if (savecupon == 1) {

                                                        porpreciofijo = objProducto.Verifica_Promo_PorPrecio(idproducto, 6, idpromo);
                                                        if (porpreciofijo==1 ){
                                                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                                                            montoDscto = dsctoporprecio;
                                                        }else{
                                                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0);
                                                            montoDscto = (PVPx * (dsctoporprecio)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                                                        }
                                                        cupon = CUPONVTA;

                                                    } else {
                                                        if (savecupon == 3) {
                                                            JOptionPane.showMessageDialog(this, "ESTE CUPON NO ESTA REGISTRADO ", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

                                                            ss = 0.0;
                                                            cupon = "";
                                                        }
                                                    }

                                                }                                                           // fin gino

                                                //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto); // desbloquear si borro gino
                                                //montoDscto = dsctoporprecio ;                                          // desbloquear si borro gino
                                                //montoDscto = (PVPx * (dsctoporprecio)) / 100 * Double.valueOf(recuperaUnidadesminimo); // desbloquear si borro gino
                                            } else {
                                                ss = 0.0;
                                                cupon = "";
                                            }
                                        } else {
                                            ss = 0.0;
                                            cupon = "";
                                        }
                                    } else {
                                        ss = 0.0;
                                        cupon = "";
                                    }
                                } else {
                                    cupon = "";
                                    ss = 0.0;
                                }

                            } else {
                                Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0) * unidad;
                                montoDscto = dsctoporprecio;
                            }

                        } else {
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

                        porprecioEsSAP = objProducto.Verifica_Promo_PorPrecio(idproducto, 3, idpromo);
                        if (porprecioEsSAP == 1) {
                            porEsSAP = objProducto.Verifica_Promo_PorPrecio(idproducto, 4, idpromo);
                            if (porEsSAP == 1) {

                                if (unidad > 0) {
                                    
                                    Confirmar_Venta_EsSAP_MSN boti = new Confirmar_Venta_EsSAP_MSN(objetoventana, this);
                                    boti.pack();
                                    boti.setVisible(true);

                                    String EsSAP = boti.getresultEsSAP();

                                    if (!String.valueOf(EsSAP).isEmpty()) {

                                        if (EsSAP != null) {

                                            if (EsSAP.compareTo("0") != 0) {

                                                if (!String.valueOf(EsSAP).isEmpty() || EsSAP != null) {

                                                    EsSAP = EsSAP.toUpperCase();
                                                    //savecupon = objProducto.InsertaCupon(idproducto,3);

                                                    //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto);
                                                    //montoDscto = dsctoporprecio ;
                                                    montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                                                } else {
                                                    ss = 0.0;
                                                    EsSAP = "";
                                                }
                                            } else {
                                                ss = 0.0;
                                                EsSAP = "";
                                            }
                                        } else {
                                            ss = 0.0;
                                            EsSAP = "";
                                        }
                                    } else {
                                        EsSAP = "";
                                        ss = 0.0;
                                    }

                                } else {
                                    ss = 0.0;
                                    EsSAP = "";
                                }

                            } else {
                                //Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto);
                                //montoDscto = dsctoporprecio ;
                                montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);
                            }

                        } else {
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

                        if (tablaproforma.getRowCount() > 0) {
                            primerValIngresado = Integer.parseInt(tablaproforma.getValueAt(tablaproforma.getRowCount() - 1, 4).toString());
                        }

                        if (validaMitadGratis == 1) {
                            montoDscto = 0.0;
                            if (empaque1 / 2 == fraccion) {
                                montoDsctofraccion = ((PVPx * (descuento)) / 100) / 2;
                            } else if (primerValIngresado > 0 && primerValIngresado / 2 == unidad) {
                                montoDscto = (PVPx * (descuento)) / 100;
                            } else {
                                ss = 0.0;
                            }

                        }

                        if (empaque1 > 0) {
                            
                            parcial1 = (fraccion * PVPx) / empaque1;
                            
                            if(_Blister > 0){
                                
                                if (unidad > 0 && fraccion == 0) {
                                    
                                    parcial1 = (fraccion * PVPx) / empaque1;
                                    //parcial1 = (unidad * PVPx) / empaque1;
                                    
                                }else{
                                
                                    parcial1 = (fraccion * PVPx) / _Blister; 
                                }
                            }
                            
                        }

                        parcial = parcial + parcial1;

                        /**
                         * ******************************************
                         * CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                         * *******************************************
                         */
                        BigDecimal bd2 = new BigDecimal(PVPx);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        PVPx = bd2.doubleValue();

                        BigDecimal bd3 = new BigDecimal(parcial);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        parcial = bd3.doubleValue();

                        listadetalle[7] = bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        listadetalle[8] = bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        listadetalle[9] = "0";
                        listadetalle[10] = estadopromo;
                        listadetalle[11] = "0";
                        listadetalle[12] = IdOperadorRec;
                        double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();
                        listadetalle[13] = igvaux;
                        listadetalle[14] = "0";
                        listadetalle[15] = "0";
                        

                        tablaproforma.addRow(listadetalle);

                        esgratuita1 = objProducto.RetornaEsGratis(idproducto);
                        if (esgratuita1.equals("1")) {
                            listadetalle[14] = montoDscto;
                        } else {
                            listadetalle[14] = "0";
                        }

                        if (objProducto.esPromoEmpFraccion(idproducto) == 1) {//MAX_CODIGO
                            montoDsctofraccion = (fraccion * PVPx) / empaque1;
                            Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(idproducto,0);
                            montoDsctofraccion = montoDsctofraccion * (dsctoporprecio / 100);
                        }//MAX_CODIGO

                        if (ss > 0.00) {
                            if (EsMultiDcto ==1){
                            idpromo = precios.getGrupo_Id();
                            }
                            String micodigoPROMO = mantProduc.Recupera_Promo_Codigo(idproducto, idpromo,EsMultiDcto).toString().trim();
                            //idpromo =0;
                            
                            esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto, micodigoPROMO); // promocion
                            if (esgratuita2.equals("1")) {
                                listadetalle[15] = "1";
                            } else {
                                listadetalle[15] = "0";
                            }
                        } else {
                            listadetalle[15] = "0";
                        }
                        listadetalle[16] = "";
                        listadetalle[17] = CUPONVTA;
                        listadetalle[18] = Ncertificado;

                        CalculaMontos(igvaux, indfila);

                        if (jComboBox3.getItemCount() > 0) {
                            AsignaCredito();
                        }
                        listaProductosVerifica.add(listadetalle);
                        Double mdscto = Double.valueOf(listadetalle[8].toString());
                        //List<ListaDetalles> countGrupoPromo = mantProduc.verificaGrupoPromocion(idproducto);
                        //String countGrupoPromo = mantProduc.verificaGrupoPromocion(idproducto).toString().trim();
                        //int countgrupos = countGrupoPromo.get(0).getnumero();
                        //CODIGO_MAX
                      //  if (bandmultiple != 1) {
                      
                        int temp[] = objProducto.PromoEstaEnDosGrupos(idproducto, idpromo);
                        
                        //if (temp[0] < 3) { 
                        if(temp[0]>=2 ){
                            //if(countgrupos > 1 ){

                            /*obj = new ProductosPromociones(objetoventana, nomproducto, idproducto);
                            obj.pack();
                            obj.setVisible(true);
                            descuento2 = obj.getDescuento();
                            //ss = descuento2;*/
                            for (int i = 0; i < jTable2.getRowCount() - 1; i++) {//entro a este metodo si el codigo de la promo es gratuita y esta en dos grupos
                                String CodProducto = (String) jTable2.getValueAt(i, 1);
                                if (idproducto.equals(CodProducto)) {
                                    ss = 0.00;
                                    break;
                                }
                            }
                        }
                        
                    //}
                      

                        String VerificaFraccComprar = mantProduc.Recupera_Promo_Comprar(idproducto, idpromo, "1").toString().trim();
                        String VerificaRegalar = mantProduc.Recupera_Promo_Comprar(idproducto, idpromo, "2").toString().trim();
                                    
                            
                        if (VerificaFraccComprar.equals("0") || VerificaFraccComprar.equals("")) {

                            // AQUI AGREGO LA FILA DE PROMOCION AL JTABLE / SI EL PRODUCTO TUVIERA --- GINO
                            if (ss > 0.00) {

                                String PromoDescripcion = "";
                                String[] recuperacadenaPromo = nomproducto.split(" ");
                                PromoDescripcion = String.valueOf(recuperacadenaPromo[0]);  //recupera el nombre de la promo

                                listadetalle = new Object[19];
                                String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto, idpromo,EsMultiDcto).toString().trim();
                                listadetalle[0] = jTable2.getRowCount() + 1;
                                if (cantidad > 1) {
                                    listadetalle[1] = obj.getCodPromocion();
                                    listadetalle[2] = obj.getDescripPromocion();
                                } else {
                                    listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, idpromo,EsMultiDcto);
                                    listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                }

                                listadetalle[4] = recuperaUnidadesminimo;
                                listadetalle[5] = 0;
                                if (empaque1 / 2 == fraccion) {
                                    //montoDsctofraccion = (fraccion * PVPx) / empaque1;
                                    //System.out.println("empaque/2 = fraccion");
                                }
                                if (fraccion > 0) {
                                    //montoDsctofraccion = (orden * PVPx) / empaque1;
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
                                
                                if (unidad > 0 && fraccion == 0) {
                                    unifra = unidad;
                                } else if (unidad == 0 && fraccion > 0) {
                                    unifra = fraccion;                                    
                                }
                                BigDecimal bd4_1 = new BigDecimal((montoDscto * (-1)) / unifra);
                                bd4_1 = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                listadetalle[6] = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[7] = bd4_1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();//bd5
                                listadetalle[8] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[9] = "1";
                                listadetalle[10] = "1";
                                listadetalle[11] = "0";
                                listadetalle[12] = "0";
                                listadetalle[13] = igvaux;

                                esgratuita1 = objProducto.RetornaEsGratis(micodigo);
                                if (esgratuita1.equals("1")) {
                                    listadetalle[14] = montoDscto;
                                } else {
                                    listadetalle[14] = "0";
                                }
                                esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto, micodigo); // promocion
                                if (esgratuita2.equals("1")) {
                                    listadetalle[15] = "1";
                                } else {
                                    listadetalle[15] = "0";
                                }
                                listadetalle[16] = "";
                                listadetalle[17] = CUPONVTA;
                                listadetalle[18] = Ncertificado;

                                if (unidad > 0 && orden == 1) {
                                } else {
                                    if (montoDscto != 0) {
                                        tablaproforma.addRow(listadetalle);
                                    }
                                    int posi = 4;
                                    if (unidad > 0 && fraccion == 0) {
                                        posi = posi;
                                        unifra = unidad;
                                    } else if (unidad == 0 && fraccion > 0) {
                                        posi = 5;
                                        unifra = fraccion;
                                    }

                                    tablaproforma.setValueAt(unifra, indfila + 1, posi);
                                    listaempaque.add(empaque1);
                                }

                                CalculaMontos(igvaux, indfila + 1);
                                listaProductosVerifica.add(listadetalle);

                                /**
                                 * ************************************
                                 *** Desde aqui tercera promocion******
                                 * ************************************
                                 */
                                int idgrup = temp[1];
                                int contardsctograt = objProducto.dsctogratuita(idgrup, 0);

                                if (contardsctograt > 1) {
                                    listadsctogratuita.removeAll(listadsctogratuita);
                                    listadsctogratuita = objproducto.Listardsctogratuita(idgrup, 1);
                                    String idprddscto = "";

                                    for (int ix = 0; ix < listadsctogratuita.size(); ix++) {

                                        idprddscto = listadsctogratuita.get(ix).getidproducto();
                                        int cantdscto = objProducto.dsctocant(idprddscto);
                                        String nvacant = String.valueOf(cantdscto);
                                        //listaProductosdscto.removeAll(listaProductosdscto);
                                        //listaProductosdscto = objproducto.ListarProductos(idprddscto, id_botica, "01", 1, 1);

                                        listadetalle = new Object[19];
                                        listadetalle[0] = jTable2.getRowCount() + 1;
                                        listadetalle[1] = idprddscto;
                                        listadetalle[2] = mantProduc.Recupera_Promo_Nombredscto(idprddscto);
                                        if (nvacant.equals("1")) { // > 0
                                            listadetalle[4] = recuperaUnidadesminimo;
                                        } else {
                                            //listadetalle[4] = 4;
                                            listadetalle[4] = cantdscto;
                                        }
                                        listadetalle[5] = 0;

                                        if (orden == 1) {
                                            montoDscto = 0.0;
                                        }

                                        String recPV = mantProduc.Recupera_promodscto(idprddscto, id_botica, "01", 1).toString().trim();
                                        String dscto = mantProduc.Recupera_promodscto(idprddscto, id_botica, "01", 2).toString().trim();
                                        String recPV_aux = recPV;
                                        //listaProductosdscto.removeAll(listaProductosdscto);
                                        //listaProductosdscto = objproducto.ListarProductos(idprddscto, id_botica, "01", 1, 1);

                                        double pvxdscto = 0;
                                        BigDecimal bd1_dscto;
                                        double pv_dscto = 0;
                                        double pv_dscto_ori = 0;
                                        double desc_dscto = 0;

                                        if ((recPV == null) || (recPV.equals(""))) {
                                            recPV = mantProduc.Recupera_promodscto(listadsctogratuita.get(0).getidproducto(), id_botica, "01", 1).toString().trim();
                                            dscto = mantProduc.Recupera_promodscto(listadsctogratuita.get(0).getidproducto(), id_botica, "01", 2).toString().trim();
                                        }

                                        pv_dscto_ori = Double.parseDouble(recPV);
                                        //if (cantdscto > 0 ){
                                        if (nvacant.equals("1")) {
                                            pv_dscto = Double.parseDouble(recPV);
                                        } else {
                                            //pv_dscto = Double.parseDouble(recPV) * 4;
                                            pv_dscto = Double.parseDouble(recPV) * cantdscto;
                                        }
                                        desc_dscto = Double.parseDouble(dscto);
                                        pvxdscto = pv_dscto - (pv_dscto * (desc_dscto / 100));

                                        bd1_dscto = new BigDecimal(pvxdscto);
                                        bd1_dscto = bd1_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                        pvxdscto = bd1_dscto.doubleValue();

                                        if ((recPV_aux == null) || (recPV_aux.equals(""))) {
                                            montoDscto = pvxdscto * (-1);
                                            pv_dscto_ori = pv_dscto_ori * (-1);
                                        } else {
                                            montoDscto = pvxdscto;
                                        }
                                        //montoDscto = montoDscto + montoDsctofraccion;

                                        //BigDecimal bd4_dscto = new BigDecimal(montoDscto * (-1));
                                        BigDecimal bd4_dscto = new BigDecimal(montoDscto);
                                        bd4_dscto = bd4_dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                        if (unidad > 0 && fraccion == 0) {
                                            unifra = unidad;
                                        } else if (unidad == 0 && fraccion > 0) {
                                            unifra = fraccion;
                                        }
                                        //BigDecimal bd4_1dscto = new BigDecimal((montoDscto * (-1))/unifra);
                                        BigDecimal bd4_1dscto = new BigDecimal(montoDscto / unifra);
                                        bd4_1dscto = bd4_1dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                        BigDecimal bd4_1dscto_ori = new BigDecimal(pv_dscto_ori / unifra);
                                        bd4_1dscto_ori = bd4_1dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                                        listadetalle[6] = bd4_1dscto.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[7] = pv_dscto_ori;//bd4_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[8] = bd4_dscto.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[9] = "1";
                                        listadetalle[10] = "1";
                                        listadetalle[11] = "0";
                                        listadetalle[12] = "0";
                                        listadetalle[13] = igvaux;

                                        esgratuita1 = objProducto.RetornaEsGratis(idprddscto);
                                        if (esgratuita1.equals("1")) {
                                            listadetalle[14] = montoDscto;
                                        } else {
                                            listadetalle[14] = "0";
                                        }
                                        esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto, idprddscto); // promocion
                                        if (esgratuita2.equals("1")) {
                                            listadetalle[15] = "1";
                                        } else {
                                            listadetalle[15] = "0";
                                        }
                                        listadetalle[16] = "";
                                        listadetalle[17] = "";
                                        listadetalle[18] = Ncertificado;

                                        if (unidad > 0 && orden == 1) {
                                        } else {
                                            if (montoDscto != 0) {
                                                tablaproforma.addRow(listadetalle);
                                            }
                                            int posi = 4;
                                            if (unidad > 0 && fraccion == 0) {
                                                posi = posi;
                                                unifra = unidad;
                                            } else if (unidad == 0 && fraccion > 0) {
                                                posi = 5;
                                                unifra = fraccion;
                                            }

                                            tablaproforma.setValueAt(unifra, indfila + 1, posi);
                                            listaempaque.add(empaque1);
                                        }

                                        CalculaMontos(igvaux, indfila + (ix + 2));
                                        listaProductosVerifica.add(listadetalle);

                                    }//fin del for

                                }

                                /**
                                 * ************************************
                                 *********** Hasta aqui****************
                                 * ************************************
                                 */
                            }  // FIN DE AGREGAR LA PROMO

                        } else {  //Si comprar > 0
                            int sum = 0;
                            ArrayList numeros = new ArrayList();
                            
                            /*for (int num = Integer.valueOf(VerificaFraccComprar); num < 31; num++) {
                                
                                sum = sum + Integer.valueOf(VerificaFraccComprar);
                                numeros.add(sum);
                            }
                            
                            for (int n = 0; n < numeros.size(); n++) {
                            */
                    //        if (Integer.valueOf(VerificaFraccComprar) == fraccion) {
                            //if(numeros.get(n).equals(fraccion)){

                                // AQUI AGREGO LA FILA DE PROMOCION AL JTABLE / SI EL PRODUCTO TUVIERA --- GINO
                                if (ss == 0.00) {

                                    if (existe == 0) {
                                        listaProductos.removeAll(listaProductos);
                                        listaProductos = objproducto.ListarProductos(idproducto, id_botica, "01", 1, 1);

                                        double pvx_aux = 0;
                                        double pv_aux = 0;
                                        double desc_aux = 0;
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
                                        int regalo = fraccion / Integer.valueOf(VerificaFraccComprar);
                                        String Regalo = "F" + Integer.valueOf(VerificaRegalar) * regalo;
                                        
                                        existe = 1;
                                        AgregaProforma(Regalo, productoPrecio, idpromo,0,0,0);

                                        //}

                                        /*----------------------------------------------------------------------*/
                                        String PromoDescripcion = "";
                                        String[] recuperacadenaPromo = nomproducto.split(" ");
                                        PromoDescripcion = String.valueOf(recuperacadenaPromo[0]);  //recupera el nombre de la promo

                                        //Object lista=  listaProductosVerifica.get(1);
                                        double PVPx_aux = Double.valueOf(listadetalle[7].toString()); // lo traigo de arriba

                                        listadetalle = new Object[19];
                                        String micodigo = mantProduc.Recupera_Promo_Codigo(idproducto, idpromo,0).toString().trim();
                                        listadetalle[0] = jTable2.getRowCount() + 1;
                                        if (cantidad > 1) {
                                            listadetalle[1] = obj.getCodPromocion();
                                            listadetalle[2] = obj.getDescripPromocion();
                                        } else {
                                            listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto, idpromo,0);
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
                                        
                                        montoDscto = parcial;

                                        BigDecimal bd4 = new BigDecimal(montoDscto * (-1));
                                        bd4 = bd4.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        if (unidad > 0 && fraccion == 0) {
                                            unifra = unidad;
                                        } else if (unidad == 0 && fraccion > 0) {
                                            //String recregalar = mantProduc.Recupera_Promo_Comprar(idproducto, idpromo, "2").toString().trim();
                                            //fraccion = Integer.parseInt(recregalar);
                                            unifra = fraccion;
                                        }
                                        BigDecimal bd4_1 = new BigDecimal((montoDscto * (-1)) / unifra);
                                        bd4_1 = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        listadetalle[5] = unifra;  // gino
                                        listadetalle[6] = bd4_1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[7] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[8] = bd4.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                                        listadetalle[9] = "1";
                                        listadetalle[10] = "1";
                                        listadetalle[11] = "0";
                                        listadetalle[12] = "0";
                                        listadetalle[13] = igvaux;

                                        esgratuita1 = objProducto.RetornaEsGratis(micodigo);
                                        if (esgratuita1.equals("1")) {
                                            listadetalle[14] = montoDscto;
                                        } else {
                                            listadetalle[14] = "0";
                                        }
                                        esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto, micodigo); // promocion
                                        if (esgratuita2.equals("1")) {
                                            listadetalle[15] = "1";
                                        } else {
                                            listadetalle[15] = "0";
                                        }
                                        listadetalle[16] = "";
                                        listadetalle[17] = "";
                                        listadetalle[18] = Ncertificado;

                                        if (unidad > 0 && orden == 1) {
                                        } else {
                                            if (montoDscto != 0) {
                                                tablaproforma.addRow(listadetalle);
                                            }
                                            int posi = 4;
                                            if (unidad > 0 && fraccion == 0) {
                                                posi = posi;
                                                unifra = unidad;
                                            } else if (unidad == 0 && fraccion > 0) {
                                                posi = 5;
                                                unifra = fraccion;
                                            }

                                            tablaproforma.setValueAt(unifra, indfila + 1, posi);
                                            listaempaque.add(empaque1);
                                        }

                                        CalculaMontos(igvaux, indfila + 2);
                                        listaProductosVerifica.add(listadetalle);
                                        existe = 0;
                                    }
                                }
                    //        } // hasta aqui
                            //}
                        }                        
                        

                        // FIN DE AGREGAR LA PROMO0
                        RcepueraTipoPago();
                        jTable2.requestFocus();
                        jTable2.changeSelection(indfila, 7, false, true);

                        if (igvaux < 1) {
                            lisProdSinIGV.add(idproducto);
                            col = jTable2.getColumnModel().getColumn(0);
                            col.setCellRenderer(new ColoredTableCellRenderer());
                            col = jTable2.getColumnModel().getColumn(1);
                            col.setCellRenderer(new ColoredTableCellRenderer());
                        }
                    }

                } catch (Exception ex) {
                    System.out.println("ERROR EN LA CAPA VISTA METODO AsiignaPrecio : " + ex.toString());
                }
            }
        }
        
        
         

        String CodProductox = "";
        String nomproductox = "";
        int contar = jTable2.getRowCount();
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            CodProductox = (String) jTable2.getValueAt(contar - 1, 1);
            nomproductox = (String) jTable2.getValueAt(contar - 1, 2);
        }
        
        
        //multiple1 = objProducto.Verifica_Promo_PorPrecio(CodProductox, 5, idpromo);
        //if (multiple1 != 1) { 
        if (bandmultiple != 1) {
        
        if (unidad > 0) {

            List<ListaDetalles> countGrupoPromo = mantProduc.verificaGrupoPromocion(CodProductox);
            int countgrupos = countGrupoPromo.get(0).getnumero();
            int TG_EsMultiple = objProducto.G_EsMultipleDscto(0, CodProductox,1);
            
            if (countgrupos > 1) {
                
              if (TG_EsMultiple == 1){   //TG MULTIPLE
                
                if (unidad > 1){
                
                    ProductosPromociones obj1 = null;                
                    obj1 = new ProductosPromociones(objetoventana, nomproductox, CodProductox);
                    obj1.pack();
                    obj1.setVisible(true);
                    String cant = obj1.getDescripPromocion();

                    String NuevocodpromoCantidad = mantProduc.NuevocodigoPromocion(CodProductox).toString().trim();                

                    String[] arr = NuevocodpromoCantidad.split(",");  // declaro el array
                    String Nuevocodpromodouble = arr[0];
                    String Cantidad = arr[1];


                        if (TG_EsMultiple == 1){

                            if(unidad % 2 == 0) {                           
                               unidad = unidad /2;
                            }else{
                                unidad = unidad - 1;
                                unidad = unidad /2;
                            }                        

                            Cantidad = String.valueOf(unidad);

                        }

                    listaProductos.removeAll(listaProductos);
                    listaProductos = objproducto.ListarProductos(Nuevocodpromodouble, id_botica, "01", 1, 1);

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
                    if (Cantidad.equals("0")) {
                        Cantidad = "1";
                    }
                    unidad = unidad;
                    if (Integer.valueOf(unidad) > 1) {

                        if (TG_EsMultiple == 1){                         

                            Cantidad = String.valueOf(unidad); 

                        }else{

                            Double Cant = Double.valueOf(Cantidad) * unidad;
                            int x = (int) Cant.doubleValue();
                            Cantidad = String.valueOf(x);
                        }
                    }
                    AgregaProforma(Cantidad, productoPrecio, 0,0,0,0);

                }
                
              }else{   //FIN TG MULTIPLE
                    
                ProductosPromociones obj1 = null;                
                obj1 = new ProductosPromociones(objetoventana, nomproductox, CodProductox);
                obj1.pack();
                obj1.setVisible(true);
                String cant = obj1.getDescripPromocion();

                String NuevocodpromoCantidad = mantProduc.NuevocodigoPromocion(CodProductox).toString().trim();                

                String[] arr = NuevocodpromoCantidad.split(",");  // declaro el array
                String Nuevocodpromodouble = arr[0];
                String Cantidad = arr[1];
                
                
                    if (TG_EsMultiple == 1){
                        
                        if(unidad % 2 == 0) {                           
                           unidad = unidad /2;
                        }else{
                            unidad = unidad - 1;
                            unidad = unidad /2;
                        }                        
                       
                        Cantidad = String.valueOf(unidad);
                        
                    }

                listaProductos.removeAll(listaProductos);
                listaProductos = objproducto.ListarProductos(Nuevocodpromodouble, id_botica, "01", 1, 1);

                double pvx = 0;
                BigDecimal bd, bd1;

                //for (ProductosPrecios precio : listaProductos) {
                for (int i = 0; i < listaProductos.size(); i++) {

                    pv = listaProductos.get(i).getPrecio_Venta();
                    desc = listaProductos.get(i).getDescuento_Venta();
                    porpreciofijo = objProducto.Verifica_Promo_PorPrecio(CodProductox, 6, idpromo);
                    if (porpreciofijo==1){
                        Double dsctoporprecio = objProducto.Dscto_Promo_PorPrecio(CodProductox,1);// * unidad;
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
                if (Cantidad.equals("0")) {
                    Cantidad = "1";
                }
                unidad = unidad;
                if (Integer.valueOf(unidad) > 1) {
                    
                    if (TG_EsMultiple == 1){                         
                        
                        Cantidad = String.valueOf(unidad); 
                        
                    }else{
                    
                        Double Cant = Double.valueOf(Cantidad) * unidad;
                        int x = (int) Cant.doubleValue();
                        Cantidad = String.valueOf(x);
                    }
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
        
    }else{  // multiple 1
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
                    
                
            
                }/*else{
                    JOptionPane.showMessageDialog(this, "NO SE PUEDE VENDER ESTE PRODUCTO POR FAVOR ANULE LA PROMOCION");
                }*/
                    
                
            }
        
        }
            

    }

    public void AgregaProforma(String cantidad, ProductosPrecios precios, int idpromo, int bandmultiple, int EsMultiDcto, int precfijo) {

        boolean resul1 = VerificaCantidad(cantidad);
        if (unidad == 0 && fraccion == 0) {
            JOptionPane msg = new JOptionPane();
            msg.showMessageDialog(this, "PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resul1 == true) {
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

    private void BuscaTipoPrecio(ProductosPrecios precios, int idpromo, int bandmultiple,int EsMultiDcto, int precfijo,String Ncertificado) {
        if (VerificaStock(precios.getProductoBotica().getProducto().getIdProducto())) {
            AsignaPrecio(precios, idpromo,bandmultiple,EsMultiDcto,precfijo,Ncertificado);
        } else {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY STOCK PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
            BusquedaProducto();
        }
        jButton3.setEnabled(true);
        jButton5.setEnabled(true);
    }

    /**
     * *****************************************
     * METODO QUE CALCULA EL MONTO TOTAL A PAGAR
     * ******************************************
     */
    private void CalculaMontos(double band_igv, int fila) {
        double auxparcial = 0.0;
        OpInafecta = 0.0;
        //OpGravada = 0.0;
        OpDescuentoTotal = 0.0;

        double valortabla = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));
        
        String c_prod =  String.valueOf(jTable2.getValueAt(fila, 1));                
        double c_cantU =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 4)));        
        double c_cantF =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 5)));
        
        
        double Monto_icbpre = objProducto.PrecioICBPER(c_prod);
        double empa=objProducto.PrecioICBPEREMP(c_prod);
        
        double c_cantT = c_cantU * empa + c_cantF;
        double Monto_icbpre_Cab  = Monto_icbpre * c_cantT; 
        
        if (band_igv == 0) {
            total += valortabla;
            total_pigv += valortabla;
            SubTotal += valortabla;

            OpGravada += valortabla;
            OpInafecta += valortabla;
            /*OpGravada += SubTotal;
            OpGravada = OpGravada - valortabla;
            OpInafecta +=valortabla;*/
            listsubtotales.add(fila, valortabla);

        } else {
            if (IGV == 0) {
                CapturaIGV();
            }
            //total += valortabla;
            total += valortabla + Monto_icbpre_Cab;
            total_pigv += valortabla;
            
            auxparcial = (valortabla / (1 + (IGV / 100)));

            BigDecimal bd1 = new BigDecimal(auxparcial);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            auxparcial = bd1.doubleValue();
            listsubtotales.add(fila, auxparcial);
            SubTotal += auxparcial;

            OpGravada += auxparcial;
            /*if (total > 0){
                OpGravada += SubTotal;
            }else{
                OpGravada = 0.0;
            }*/
        }

        if (valortabla < 0) {
            OpDescuentoTotal += valortabla;
        }

        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();

        BigDecimal bdtpigv = new BigDecimal(total_pigv);
        bdtpigv = bdtpigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total_pigv = bdtpigv.doubleValue();
        
        cantidadproductos++;

        //jTextField15.setText("0.0"); //redondeo
        jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
        jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
        miigv = total - SubTotal;
        
        miigv_x = total_pigv - SubTotal;
        
        

        BigDecimal bd3 = new BigDecimal(miigv);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        miigv = bd3.doubleValue();
        
        BigDecimal bd3_x = new BigDecimal(miigv_x);
        bd3_x = bd3_x.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        miigv_x = bd3_x.doubleValue();

        jTextField16.setText(bd3_x.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

    }

    private void ModificaMontos(Double valanterior, Double auxparcial, String codpro, int fila) {
        double aux = mantProduc.recupera_Igv_Exonerado(codpro);
        total = 0;

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            total += Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
        }

        if (aux > 0) //SI TIENE IGV
        {
            CapturaIGV();
            double auxparcial1 = (valanterior / (1 + (IGV / 100)));
            SubTotal = SubTotal - auxparcial1;
            double auxparcial2 = (auxparcial / (1 + (IGV / 100)));
            SubTotal = SubTotal + auxparcial2;
            listsubtotales.set(fila, auxparcial2);

        } else {
            SubTotal = SubTotal - valanterior;
            listsubtotales.set(fila, auxparcial);
            SubTotal = SubTotal + auxparcial;
        }

        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();

        double auxigv = (total - SubTotal);

        BigDecimal bd3 = new BigDecimal(auxigv);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        auxigv = bd3.doubleValue();

        jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

        //AQUI
        /*String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        //int deci = Integer.parseInt(arr[1]);
        String deci = arr[1];

        //String x = Integer.toString(deci);
        String x = deci;
        int contardeci = x.length();

        if (contardeci > 1){

        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
        String ultimodigito = String.valueOf(ultimoNumero);

            if (ultimodigito.equals("0") || ultimodigito.equals("5")){
                jTextField15.setText("0.0"); //redondeo
                jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                //miigv = total - SubTotal;
                auxigv = (total - SubTotal);

                BigDecimal bd3 = new BigDecimal(auxigv);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                auxigv = bd3.doubleValue();

                jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //redondeo

            }else{
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    auxigv = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(auxigv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxigv = bd3.doubleValue();

                    jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField15.setText(String.valueOf(redondeo)); //redondeo
                    jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                }else{

                    if (Integer.parseInt(ultimodigito) >= 6 && Integer.parseInt(ultimodigito) <= 9){
                    double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                    Double redondeo = (ultimonuevo /100) * -1;
                    Double nuevototal = total - (ultimonuevo /100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();


                    jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    auxigv = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(auxigv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxigv = bd3.doubleValue();

                    jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField15.setText(String.valueOf(redondeo)); //redondeo
                    jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                    }

                }
            }
        }else{

            jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
            auxigv = total - SubTotal;

            BigDecimal bd3 = new BigDecimal(auxigv);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            auxigv = bd3.doubleValue();

            jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
            jTextField15.setText("0.0");                                                                    //redondeo
            jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar
        }
         */
    }

    /*
     * METODO QUE RECUPERA DE LA BD EL VALOR DEL IGV
     */
    private void CapturaIGV() {
        IGV = mantProduc.Captura_IGV();
    }

    public void Guardar() {
        String resultado = "";
        String montoproforma = "";
        String validar = "";
        String nomcliente = "";
        String dni = "";
        String direccion = "";
        String dato = "";
        double Tot;
        double montoReal;
        double tot, pventa, desc;
        double montovalidado;
        String texto = "";
        int Total = 0;
        String tipoPago = "";
        int canti1 = 0;
        int canti2 = 0;
        String cantidad1 = "";
        String cantidad2 = "";
        String cantidad3 = "";
        String valueCompara = "";
        String CertMed ="";
        String inserta = "";
        double igvventa = 0.0;
        double subtotoal1 = 0.0;
        double total1 = 0.0;
        double OpDescuento = 0.0;
        double TotEsGratuita = 0.0;
        double Redondeo = 0.0;
        double TotFinal = 0.0;
        String cuponvta ="";
        int solofactura = 0;

        listdetalleProforma.removeAll(listdetalleProforma);

        try {

            FechaRegistro = fechaVenta;
            idcliente = Integer.parseInt(this.jTextField2.getText().trim());
            nomcliente = txtcliente.getText().trim().toUpperCase();
            dni = txtdni.getText().trim();
            direccion = String.valueOf(this.jComboBox4.getSelectedItem());
            igvventa = Double.parseDouble(this.jTextField16.getText());
            //double LeerRedondeo = Double.parseDouble(this.jTextField15.getText());
            //double LeerTotFinal = Double.parseDouble(this.jTextField17.getText());
            double LeerRedondeo = 0.0;
            double LeerTotFinal = 0.0;

            if (direccion == null) {
                direccion = "";
            }

            RcepueraTipoPago();
            IdPersonalBoticaVenta = Integer.parseInt(this.jTextField12.getText().trim());
            if (igvventa == 0) {
                SubTotal = 0.0;
            }

            
            int posorden = 1;
            for (int i = 0; i < listaProductosVerifica.size(); i++) {
                Object[] valor = new Object[1];
                valor[0] = listaProductosVerifica.get(i);

                Object[] datos = new Object[19];
                datos = (Object[]) valor[0];
                if (Integer.parseInt(datos[10].toString()) == 1) {
                    datos[11] = posorden;
                    listaProductosVerifica.set(i, datos);
                    if (Integer.parseInt(datos[9].toString()) == 1) {
                        posorden = posorden + 1;
                    }
                } else {
                    datos[11] = 0;
                    listaProductosVerifica.set(i, datos);

                }
                
                cuponvta = String.valueOf(datos[17].toString());
            }          

            entidadproforma = new Proforma(id_botica, idcliente, idtipopago, tipoprecio, idtipoventa, idmedico, fechaVenta, SubTotal, IGV, total, OpGravada, OpExonerada, OpInafecta, OpGratuita, OpISC, FechaRegistro, IdPersonalBoticaVenta, nomcliente, dni, direccion, ruc, colegiatura, miigv, LeerRedondeo, LeerTotFinal,cuponvta);

            
            String PROFOR = jLabel20.getText();
            if (PROFOR.equals("")) {
                PROFOR = "000000";
            }
            modif = objProducto.RetornaPersonalModifica(PROFOR);
            solofactura = objproducto.Essolofactura(idcliente);

            String[] listaMOdif;
            String cadena1 = modif;
            listaMOdif = cadena1.split("/");
            int modificada = Integer.parseInt(listaMOdif[1]);

            if (modificada == 1) {
                JOptionPane.showMessageDialog(this, " ESTA PROFORMA HA SIDO MODIFICADA, NO PUEDE EDITARLA ", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (solofactura == 1) {
                if(idtipopago ==8){
                
                    if (idtipoventa != 12){
                        JOptionPane.showMessageDialog(this, " ESTA VENTA SOLO SE VENDERA COMO FACTURA ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                OpDescuento = 0.0;
                Object[] valor = new Object[1];
                valor[0] = listaProductosVerifica.get(i);

                Object[] datos = new Object[19];
                datos = (Object[]) valor[0];

                String orden = datos[11].toString();
                int idOperadorRec = Integer.parseInt(datos[12].toString());
                String esgratuitaPromo = String.valueOf(datos[15].toString()); // pregunto si es gratuita la promo
                TotEsGratuita = Double.valueOf(datos[14].toString());
                CertMed = String.valueOf(datos[18].toString());
                

                String idproducto = String.valueOf(this.jTable2.getValueAt(i, 1));
                String esgratuita = objProducto.RetornaEsGratis(idproducto);
                int tipdscto = objProducto.G_EsMultipleDscto(0, idproducto,3);
                String nomostrar = objProducto.RetornaNoMostrar(idproducto);
                int unida = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 4)));
                int fracc = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 5)));
                double PrecioVen = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 6)));
                double pvx = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 7)));
                double descu = objProducto.Recupera_Desct_Producto(idproducto, id_botica, tipoprecio, String.valueOf(pvx));
                Tot = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                Venta enti = objProducto.Recupera_Descuento_Producto(idproducto, txtdni.getText().trim(), "01", String.valueOf(pvx));
                desc = enti.getDescuento();
                double auxigv = enti.getIGV();

                double sutot = 0.0;
                if (auxigv == 0.0) {
                    //sutot = 0.0;
                    sutot = Tot / ((1 + (auxigv / 100)));
                    OpInafecta = Tot;
                } else {
                    sutot = Tot / ((1 + (auxigv / 100)));
                    OpInafecta = 0.0;
                }
                if (Tot < 0) {

                    //OpDescuento = Tot;
                    if (esgratuita.equals("1")) {//CODIGO_MAX
                        OpGratuita = Tot;//CODIGO_MAX
                    
                    }else{
                        if (tipdscto == 1){
                            OpDescuento = Tot;                        
                       
                        } else {
                            if (nomostrar.equals("0")) {
                            OpDescuento = Tot;
                            }
                        }    
                    }
                }

                listdetalleProforma.add(new Proforma(idproducto, unida, fracc, PrecioVen, descu, pvx, Tot, sutot, orden, idOperadorRec, esgratuita, TotEsGratuita, esgratuitaPromo, OpGravada, OpExonerada, OpInafecta, OpGratuita, OpISC,CertMed));
                //jTextField13.setText(Double.toString(Tot));
            }

            tipoPago = String.valueOf(this.jComboBox2.getSelectedItem());
            //String cantJTable = String.valueOf(jTable2.getRowCount());
            int cantJTable1 = jTable2.getRowCount();
            cantidad = objProducto.RetornaCantidadCompara(tipoPago);

            String[] lista;
            String cadena = cantidad;
            lista = cadena.split("/");

            int canti = Integer.parseInt(lista[0]);      // recupera el numero de items por tipo de doc de la tabla
            String recTipDoc = String.valueOf(lista[1]); // recupera el id de tipo de doc

            /*
             * LA LISTA CON LOS PRODUCTOS SIN IGV EXONERADO
             *     IDENTIFICAR LOS PRODUCTOS CON IGV Y SIN IGV
             */
            if (listdetalleProforma.size() > 0) {
                int cantidaddoc = 0;
                if (recTipDoc.equals("11")) {
                    cantidaddoc = 100;
                } else if (recTipDoc.equals("12")) {
                    cantidaddoc = 100;
                }

                //Collections.sort(listdetalleProforma);
                //Collections.sort(listaProductosVerifica, new ProformaComparatorByOrdenTotal());
                {
                    ordenproducto = 1;
                    listaProductosVerifica.clear();
                    String MonedaFinal = jComboBox9.getSelectedItem().toString();
                    
                    FrmcantBolsas objCant = null;                                
                    objCant = new FrmcantBolsas(objetoventana, "", "");
                    objCant.pack();
                    //Quitar el mensaje de cantidad de bolsas
                    
                    //objCant.setVisible(true);
                               
                    //cantbolsa = objCant.getCantbolsas();
                    cantbolsa = 0;

                    resultado = onjproforma.ProformaRealizada(entidadproforma, listdetalleProforma, MonedaFinal,cantbolsa);

                    if (listdetalleProforma.size() > 0 && resultado.compareTo("") != 0) {

                        montoproforma = onjproforma.MontoProformaRealizada(resultado);
                        validar = onjproforma.ValidaProformaRealizada(resultado);
                        montoReal = Double.parseDouble(montoproforma);
                        montovalidado = Double.parseDouble(validar);

                        if (montoReal == montovalidado) {

                            if (!String.valueOf(cupon).isEmpty()) {
                                if (cupon != null) {
                                    if (cupon.compareTo("0") != 0) {
                                        if (!String.valueOf(cupon).isEmpty() || cupon != null) {
                                            int savecupon = onjproforma.insertaCupon(resultado, cupon);
                                        }
                                    }
                                }
                            }

                            int result = onjproforma.insertaValidarProformaRealizada(resultado);
                            new ProformaGenerada(resultado, this, objetoventana, jTextField7.getText()).show(true);
                            LimpiaTodo();
                            cupon = "";

                        } else {
                            JOptionPane.showMessageDialog(this, "Error al generar Proforma... vuelva a realizarla", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, " ERROR AL GENERAR LA PROFORMA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

            // }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, " ERROR AL GENERAR LA PROFORMA ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(" ERROR AL GENERAR LA PROFORMA " + ex.getMessage());
        }
    }

    /*
     * METODO PARA GUARDAR LA PROFORMA
     */
    private void GuardaProforma() {

        int idpersona = Integer.parseInt(this.jTextField12.getText().trim());

        if (precbotiquin.compareTo("02") == 0) {
            Venta pe = new Venta(id_botica, idpersona);
            boolean resul1 = objguardaventa.Verifica_Venta_Botiquin(pe);

            if (resul1) {
                Guardar();
            } else {
                Confirmar_Venta_Botiquin boti = new Confirmar_Venta_Botiquin(objetoventana, this);
                boti.pack();
                boti.setVisible(true);
            }

        } else {
            Guardar();
        }
    }

    public void LimpiaTodo() {
        try {

            LimpiatTabla();
            txttelefono.setText("");
            SubTotal = 0.0;
            OpGravada = 0.0;
            OpInafecta = 0.0;
            total = 0.0;
            cantidadproductos = 1;
            colegiatura = "";
            IGV = 0;
            miigv_x =0;
            total_pigv=0;
            
            listdetalleProforma.removeAll(listdetalleProforma);
            listaProductosVerifica.removeAll(listaProductosVerifica);
            listsubtotales.removeAll(listsubtotales);
            lisProdSinIGV.removeAll(lisProdSinIGV);
            nuevaPromocion.removeAll(nuevaPromocion);
            cantidadElementos.removeAll(cantidadElementos);
            DetallePromo.removeAll(DetallePromo);
            ListDescuentos.removeAll(ListDescuentos);
            txtruc.setText("");
            jTextField16.setText("");
            jTextField15.setText("");
            jTextField17.setText("");
            txtdni.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            Cliente_Comun();
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
            jComboBox4.removeAllItems();
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);
            msgSaldo.setText("");
            jLabel19.setVisible(false);
            jLabel20.setVisible(false);
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox3.removeAllItems();
            jTable2.requestFocus();
            jLabel20.setText("");
            contarpromo = 0;
            existe = 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, " SE ESTA APERTURANDO CAJA  ", "Error", JOptionPane.INFORMATION_MESSAGE);
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

    /**
     * ********************************************
     * METODO QUE ELIMINA UN PRODUCTOS DE MI JTABLE
     * ********************************************
     */
    private void EliminaProducto(int fila) {
        int filas = tablaproforma.getRowCount();
        int cantidadValue = 0;
        if (filas > 0) {
            try {
                if (fila >= 0) {
                    Confirmar pe = new Confirmar(objetoventana, "<html> Desea quitar el Producto : " + tablaproforma.getValueAt(fila, 2) + "  ? </html>");
                    pe.show(true);
                    String idproducto = String.valueOf(tablaproforma.getValueAt(fila, 1));

                    if (pe.getConfirmar() == 1) {
                        String CodPromo = (String) mantProduc.Recupera_Codigo_Promo_Aeliminar(idproducto);//MAX_CODIGO PARA NO ELIMINAR PRODUCTOS CON PROMOCION
                        for (int j = 0; j < tablaproforma.getRowCount(); j++) {//MAX_CODIGO
                            if (CodPromo.equals(String.valueOf(tablaproforma.getValueAt(j, 1)))) {
                                JOptionPane.showMessageDialog(this, " NO SE PUEDE ELIMINAR EL PRODUCTO PORQUE POSEE UNA PROMOCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        //cantidadValue = objProducto.Verifica_Promocion(idproducto);
                        //JOptionPane.showMessageDialog(this, cantidadValue, "Error", JOptionPane.ERROR_MESSAGE);

                        /*if (cantidadValue > 0) {
                        JOptionPane.showMessageDialog(this, cantidadValue+ " es promo cuidado ", "Error", JOptionPane.ERROR_MESSAGE);

                        }else{*/
                        listaProductosVerifica.remove(fila);
                        
                        String c_prod_x =  String.valueOf(jTable2.getValueAt(fila, 1));                
                        double c_cant_x =  Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 4)));        

                        double Monto_icbpre_x = objProducto.PrecioICBPER(c_prod_x);
                        double Monto_icbpre_Cab_x  = Monto_icbpre_x * c_cant_x;
                        
                        Double valor = Double.parseDouble(String.valueOf(tablaproforma.getValueAt(fila, 8)));
                        
                        total = total - valor -Monto_icbpre_Cab_x;
                        Double auxsubtotal = Double.parseDouble(String.valueOf(listsubtotales.get(fila)));
                        SubTotal = SubTotal - auxsubtotal;
                        
                        //Double igvaux = Double.valueOf(this.jTextField16.getText());
                        listsubtotales.remove(fila);
                        listaempaque.remove(fila);

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

                        if (jTable2.getRowCount() == 0) {
                            jTextField16.setText(" ");
                            jTextField9.setText(" ");
                            jTextField7.setText(" ");
                            IGV = 0;
                            total = 0;
                            SubTotal = 0;
                            listsubtotales.removeAll(listsubtotales);
                            listaempaque.removeAll(listaempaque);
                        }

                        BigDecimal bd1 = new BigDecimal(SubTotal);
                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        SubTotal = bd1.doubleValue();
                        //total = SubTotal + IGV;
                        BigDecimal bd2 = new BigDecimal(total);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        total = bd2.doubleValue();

                        jTextField7.setText(bd2.setScale(podecimalPantalla).toPlainString());
                        jTextField9.setText(bd1.setScale(podecimalPantalla).toPlainString());

                        //AQUI *************************
                        /*String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
                        int enter = Integer.parseInt(arr[0]);
                        int deci = Integer.parseInt(arr[1]);

                        String x = Integer.toString(deci);
                        int contardeci = x.length();

                        if (contardeci > 1){

                        char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
                        String ultimodigito = String.valueOf(ultimoNumero);

                            if (ultimodigito.equals("0") || ultimodigito.equals("5")){

                                miigv = total - SubTotal;
                                BigDecimal bd3 = new BigDecimal(miigv);
                                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                miigv = bd3.doubleValue();
                                jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                                jTextField15.setText("0.0"); //redondeo
                                jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //redondeo

                            }else{
                                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                                    Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                                    Double nuevototal = total - (Double.valueOf(ultimodigito) /100);

                                    BigDecimal bdNT = new BigDecimal(nuevototal);
                                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    nuevototal = bdNT.doubleValue();

                                    miigv = total - SubTotal;
                                    BigDecimal bd3 = new BigDecimal(miigv);
                                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    miigv = bd3.doubleValue();
                                    jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv


                                    jTextField15.setText(String.valueOf(redondeo)); //redondeo
                                    jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                                }else{

                                    if (Integer.parseInt(ultimodigito) >= 6 && Integer.parseInt(ultimodigito) <= 9){
                                    double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                                    Double redondeo = (ultimonuevo /100) * -1;
                                    Double nuevototal = total - (ultimonuevo /100);

                                    BigDecimal bdNT = new BigDecimal(nuevototal);
                                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    nuevototal = bdNT.doubleValue();

                                    miigv = total - SubTotal;
                                    BigDecimal bd3 = new BigDecimal(miigv);
                                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    miigv = bd3.doubleValue();
                                    jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv


                                    jTextField15.setText(String.valueOf(redondeo)); //redondeo
                                    jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                                    }

                                }
                            }
                        }else{

                            miigv = total - SubTotal;
                            BigDecimal bd3 = new BigDecimal(miigv);
                            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            miigv = bd3.doubleValue();
                            jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv


                            jTextField15.setText("0.0");                                                                    //redondeo
                            jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar
                        }*/
                        //hasta aqui **********************
                        col = jTable2.getColumnModel().getColumn(0);
                        col.setCellRenderer(new ColoredTableCellRenderer());
                        col = jTable2.getColumnModel().getColumn(1);
                        col.setCellRenderer(new ColoredTableCellRenderer());

                        nuevaPromocion.remove(mantProduc.verificaPromocion(idproducto, 0).get(0).getIdPromo());//MAX_CODIGO elimina prod_promo de arreglo de promociones
                        int i = 1000;//MAX_CODIGO
                        for (i = 0; i < DetallePromo.size(); i++) { //MAX_CODIGO elimina prod_promo de arreglo de detalle de promociones
                            if (DetallePromo.get(i).getIdPromo() == mantProduc.verificaPromocion(idproducto, 0).get(0).getIdPromo()) {
                                DetallePromo.remove(i);
                            }
                            break;

                        }

                        //           .add(                   new ListaDetalles(                 verificaPromocion.get(0).getIdPromo(),
                        //  verificaPromocion.get(0).getTotalPromocionales(),             verificaPromocion.get(0).getDescuento(),
                        // CodProducto,                      Cantidad,                    1));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " DEBE DE SELLECIONAR UN ITEM PARA ELIMINAR ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                System.out.println("Error al seleccionar en la tabla:" + ex.toString());
            }

        } else //SI NO HAY PRODUCTOS EN LA TABLA
        {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY PRODUCTOS EN LA PROFORMA", "Error", JOptionPane.ERROR_MESSAGE);

            if (filas == 0) {
                total = 0.0;
                SubTotal = 0.0;
                OpInafecta = 0.0;
                OpGravada = 0.0;
                IGV = 0.0;
                jTextField7.setText(String.valueOf(total));
                jTextField9.setText(String.valueOf(SubTotal));
                jTextField16.setText(String.valueOf(IGV));
            }
        }
    }

    /**
     * **************************************************************
     * METODO PARA VOLVER A REORDENAR LAS CANTIDADES DE LOS PRODUCTOS
     * *************************************************************
     */
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

    private void RcepueraTipoPago() {
        idtipopago = listatipospagos.get(jComboBox1.getSelectedIndex()).getId_TipoPago();
    }

    public boolean esCredito(int idtipopago) {
        boolean escredito = false;
        escredito = objguardaventa.EsCredito(idtipopago);
        return escredito;
    }

    private void GeneraProforma() {
        boolean band = true;
        RcepueraTipoPago();
        msgSaldo.setText("");
        String resulta = null;

        if (jComboBox2.getItemCount() > 0) {
            RcepueraTipoPago();
            if (esCredito(idtipopago)) {
                if (Double.valueOf(jTextField7.getText().toString()) > objClientes.RecuperaSaldo(Integer.parseInt(jTextField2.getText().toString()), id_botica)) {
                    msgSaldo.setText("Saldo Insuficiente para completar la Venta");
                    msgSaldo.setForeground(Color.red);
                    JOptionPane.showMessageDialog(this, "EL CLIENTE NO CUENTA SON SALDO PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
                    band = false;
                } else {
                    msgSaldo.setText("");
                }
            } else {
                int resul1 = objClientes.EsDescuento(listatipospagos.get(jComboBox1.getSelectedIndex()).getId_TipoPago());
                if (resul1 == 1) {
                    int elcodigo = Integer.parseInt(jTextField2.getText().trim());
                    if (objClientes.Recupera_IdCliente_Descuento(id_botica, elcodigo) != Integer.parseInt(jTextField2.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Error \n A este Cliente no se le Puede aplicar \n el Descuento al Personal \n Porfavor Seleccione un Cliente Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                        band = false;
                    }
                }
            }

            if (band) {
                int posicion = 0;
                idtipoventa = lis_tipo_venta.get(jComboBox2.getSelectedIndex()).getId_Tipo_Venta();
                ruc = txtruc.getText().trim();

                if (idtipoventa == 12) {
                    resulta = objfactura.Verifica_Datos_Factura(idtipoventa, ruc, txtcliente.getText().trim(), jComboBox4.getSelectedItem().toString().trim());
                }

                if (resulta != null) {
                    JOptionPane.showMessageDialog(this, resulta.substring(1, resulta.length()), "Error", JOptionPane.ERROR_MESSAGE);

                    if (Integer.parseInt(resulta.substring(0, 1)) == 1) {
                        txtruc.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 2) {
                        txtcliente.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 3) {
                        jComboBox4.requestFocus();
                    }
                } else {

                    boolean flag = objfactura.Verifica_Datos_Boleta(idtipoventa, total, txtdni.getText().trim(), posicion, txtcliente.getText().trim(), jComboBox4.getSelectedItem());

                    if (!flag) {
                        JOptionPane.showMessageDialog(this, "PORFAVOR PARA ESTE MONTO DE VENTA SE REQUIERE INGRESAR : \n NOMBRE DEL CLIENTE  \n NUMERO DE DNI  \n DIRECCION DEL CLIENTE ", "Error", JOptionPane.ERROR_MESSAGE);
                        txtdni.requestFocus();
                        txtdni.setEnabled(true);
                        //HabilitaBotones(true);
                        HabilitaColor();
                    } else {
                        if (jTextField2.getText().trim().compareTo("") != 0) {
                            
                            int sumaEspsicotropico=0;
                            for (int ix = 0; ix < jTable2.getRowCount(); ix++) {
                                String compara_idprod = String.valueOf(this.jTable2.getValueAt(ix, 1));
                                int espsicotropico = objProducto.G_EsPsicotropico(compara_idprod,1);
                                sumaEspsicotropico = sumaEspsicotropico + espsicotropico;
                            }
                            int ValidaMedico = objProducto.G_EsPsicotropico("",2);
                            new Medicos(objetoventana).setVisible(true);
                            if (Medicos.getId_Medico() != null) {
                                
                            if(ValidaMedico == 0){
                                idmedico = Medicos.getId_Medico();
                                colegiatura = Medicos.getColegiatura();
                                GuardaProforma();
                            }else{    
                                
                                
                                if (sumaEspsicotropico > 0 ){
                                    if (!Medicos.getId_Medico().equals("00000") && !txtcliente.getText().trim().toUpperCase().equals("CLIENTE COMUN")){
                                        if (! txtcliente.getText().trim().equals("") ){
                                            idmedico = Medicos.getId_Medico();
                                            colegiatura = Medicos.getColegiatura();
                                            GuardaProforma();
                                        }else{
                                           JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, " PARA ESTA VENTA SE REQUIERE RECETA MEDICA \n Y DEBE INGRESE EL NOMBRE DEL PACIENTE/CLIENTE Y ESCOGER EL MEDICO ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                    idmedico = Medicos.getId_Medico();
                                    colegiatura = Medicos.getColegiatura();
                                    GuardaProforma();
                                }
                            }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, " NO HA SELECCIONADO UN CLIENTE PARA GENERAR SU PROFORMA ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "LO SENTIMOS CAJA NO APERTURADO NINGUN TIPO DE DOCUMENTO", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void RecuperaTiposPagos() {
        listatipospagos = objfactura.Recupera_Tipos_Pagos(0);
        for (int i = 0; i < listatipospagos.size(); i++) {
            String descrp = listatipospagos.get(i).getDescripcion();
            jComboBox1.addItem(descrp);
        }
    }

    private void Muestra_Moneda() {
        listatiposMoneda = objfactura.Recupera_Moneda();
        for (int i = 0; i < listatiposMoneda.size(); i++) {
            String descrp = listatiposMoneda.get(i).getMoneda();
            jComboBox9.addItem(descrp);
        }
    }

    private void VerificaCreditoEspecial(String idcliente) {
        int id = Integer.parseInt(jTextField2.getText().trim());
        ListDescuentos.removeAll(ListDescuentos);
        ListDescuentos = objClientes.Verifica_Descuento_Cliente(id);
        jComboBox3.removeAllItems();
        if (ListDescuentos.size() > 0) {
            jComboBox3.addItem(" Seleccionar Descuento ");
            for (int i = 0; i < ListDescuentos.size(); i++) {
                jComboBox3.addItem(ListDescuentos.get(i).getPorcen_descuento());
            }
        }
    }

    private boolean Verifica_Productos_Afectos() {
        boolean resul1 = true;
        if (ListDescuentos.size() > 0) {
            if (jComboBox3.getItemCount() > 0) {
                int id = jComboBox3.getSelectedIndex() - 1;
                String idpro = ListDescuentos.get(id).getIdproductodesc();
                double aux = mantProduc.recupera_Igv_Exonerado(idpro);
                String idprodu;

                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    idprodu = jTable2.getValueAt(i, 1).toString().trim();
                    double auxigv = mantProduc.recupera_Igv_Exonerado(idprodu);
                    if (aux != auxigv) {
                        resul1 = false;
                    }
                }
            }
        }

        return resul1;
    }

    private void LimpiarDatos() {
        jTextField2.setText("");
        txtruc.setText("");
        txtcliente.setText("");
        jComboBox4.removeAllItems();
        txtdni.setText("");
        txttelefono.setText("");
    }

    public void AsignaDatosCliente() {
        try {
            LimpiarDatos();
            jTextField2.setText(objcliente.getCodigoCliente());
            txtruc.setText(objcliente.getRUC_DNI());
            txtcliente.setText(objcliente.getNombre_RazonSocial());
            txtdni.setText(objcliente.getDNI());
            txttelefono.setText(objcliente.getTelefono());
            jComboBox3.setEnabled(true);
            jComboBox1.requestFocus();
            Recupera_Direcciones();
        } catch (Exception ex) {
            System.out.println("Error al recuperrar cliente:" + ex.getMessage());
        }
    }

    private void Recupera_Direcciones() {
        jComboBox4.removeAllItems();
        List<Clientes> midirecciones = objClientes.Lista_Direcciones(id_botica, Integer.parseInt(objcliente.getCodigoCliente()), objcliente.getEmpresa(), objcliente.getDireccion().trim());
        for (int i = 0; i < midirecciones.size(); i++) {
            jComboBox4.addItem(midirecciones.get(i).getDireccion());
        }
    }

    private void CerrarVentana() {
        if (this.jTable2.getRowCount() > 0) {
            Confirmar pe = new Confirmar(objetoventana, "<html> ESTAS REALIZANDO UNA PROFORMA <br> DESEAS SALIR ?  </html>");
            pe.show(true);
            if (pe.getConfirmar() == 1) {
                objventa.Habilita(true);
                dispose();
                objventa.marcacdo = false;
            }
        } else {
            objventa.Habilita(true);
            dispose();
            objventa.marcacdo = false;
        }
        objventa.requestFocus();
    }

    /**
     * ***********************************
     * METODO QUE MODIFICA LA CANTIDAD PEDIDAD
     *
     * @param fila *************************************
     */
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
                if (cantidad.compareTo("") != 0) {
                    int empaque1 = mantProduc.Recupera_Empaque(codpro);
                    int Esbotiquin = mantProduc.Recupera_EsBotiquin(codpro,1);

                    if (cantidad != null) {
                        boolean resul1 = VerificaCantidad(cantidad);
                        if (resul1) {
                            if (unidad == 0 && fraccion == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (VerificaStock(codpro)) {

                                    if (empaque1 == 0 && fraccion > 0) {
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
                                        unidad = Integer.valueOf(String.valueOf(jTable2.getValueAt(fila, 4)));
                                        fraccion = Integer.valueOf(String.valueOf(jTable2.getValueAt(fila, 5)));
                                        
                                        parcial = PVPx * unidad;

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

                                        /**
                                         * ******************************************
                                         * CONVERTIR DOUBLE A DOS POSICIONES
                                         * DECIMALES
                                         * *******************************************
                                         */
                                        BigDecimal bd = new BigDecimal(PVPx);
                                        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                        PVPx = bd.doubleValue();

                                        BigDecimal bd1 = new BigDecimal(parcial);
                                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                        parcial = bd1.doubleValue();

                                        jTable2.setValueAt(bd.setScale(podecimalPantalla).toPlainString(), fila, 7);
                                        jTable2.setValueAt(bd1.setScale(podecimalPantalla).toPlainString(), fila, 8);

                                        Double val = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));
                                        ModificaMontos(anterior, val, String.valueOf(this.jTable2.getValueAt(fila, 1)), fila);

                                    }//aqui
                                } else {
                                    JOptionPane.showMessageDialog(this, " NO HAY STOCK PARA LA CANTIDAD INGRESADA ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR DATOS CORRECTOS ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR EN EL METODO CAPA VISTA ModificaCantidadPedida" + ex.toString());
        }
    }

    private Double armarPromocion(List<ListaDetalles> verificaPromocion, String CodProducto, Integer Cantidad, int idprom, int bndmulti) {
        Integer existePromocion = 0;
        Double Descuento = 0.00;
        //nuevaPromocion.clear();

        if (verificaPromocion.get(0).getIdPromo() > 0) {
            for (Integer g = 0; g < nuevaPromocion.size(); g++) {
                if (nuevaPromocion.get(g).equals(verificaPromocion.get(0).getIdPromo())) {
                    existePromocion = 1;
                    //existePromocion = 0;
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

                Descuento = objProducto.Recupera_Dscto(CodProducto, 0, idprom, bndmulti);

            } else {
                //En caso exista empiezo a agregarlo a los elementos
                Integer t = 1;
                int temp = 0;//MAX_CODIGO para controlar las promociones puedan ser mas de 2

                //if (verificaPromocion.get(0).getTotalPromocionales() == 3) {         //gino /*
                if (verificaPromocion.get(0).getTotalPromocionales() >= 3) {
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
                            temp = temp + 1;//MAX_CODIGO
                            if (temp == verificaPromocion.get(0).getTotalPromocionales())//MAX_CODIGO
                            {
                                t++;
                            }
                        }
                    }
                    if (verificaPromocion.get(0).getTotalPromocionales() < 3) {                    
                        t = t + 1;
                    }else if (verificaPromocion.get(0).getTotalPromocionales() ==4) {
                        t = t + 1;
                    }

                } else {                                                              // hasta aqui gino*/

                    for (Integer con = 0; con < DetallePromo.size(); con++) {
                        if (DetallePromo.get(con).getIdPromo() == verificaPromocion.get(0).getIdPromo()) {
                            temp = temp + 1;//MAX_CODIGO
                            if (temp == verificaPromocion.get(0).getTotalPromocionales())//MAX_CODIGO
                            {
                                t++;
                            }
                        }
                    }
                    if (verificaPromocion.get(0).getTotalPromocionales() < 3) {
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
                }

                if (verificaPromocion.get(0).getTotalPromocionales() == 3 && contarpromo == 2) {                
                    t = 2;

                } else if (verificaPromocion.get(0).getTotalPromocionales() == 4 && contarpromo == 3) {                
                    t = 3;

                }else{
                    
                    t = t - 1;
                
                }
                //Descuento = objProducto.Recupera_Dscto(CodProducto, t - 1);
                Descuento = objProducto.Recupera_Dscto(CodProducto, t, idprom,bndmulti);

                //System.out.print("ss" + CodProducto);
                /*} else {
                    JOptionPane.showMessageDialog(this, "Se completo los cupos de esta promocion, genere otra venta por fv", "Promocion", JOptionPane.ERROR_MESSAGE);
                }*/
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

    private void BusquedaProducto() {
        objproductos.setSeleccionaart(false);
        objproductos = new BuscarProductos(objetoventana, precbotiquin);
        objproductos.setVisible(true);
        int contProm = 0;
        int idprom1 = 0;
        String nuevocodMultiple="";
        int multiple = 0;
        ProductosPromociones objProm = null;
        

        if (objproductos.seleccionaart) { //recupera dato de formulario BuscarProducto - RecuperaProducto()

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

                if (idprom1 > 0) {

                    new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
                    if (ProductoPedido.ingresadet) {
                        if (ProductoPedido.getCantidad().compareTo("") != 0) {
                            AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio(), idprom1,multiple,EsMultiDscto,0);
                        }
                    }

                }

            } else {

                new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
                if (ProductoPedido.ingresadet) {
                    if (ProductoPedido.getCantidad().compareTo("") != 0) {
                        AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio(), idprom1,multiple,EsMultiDscto,0);
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

    private void ListaProforma() {
        ListadoProformas pro = new ListadoProformas(objetoventana, this);
        pro.show(true);
    }

    public void AsignaDatos_Proforma(String idproforma, List<Proforma> listaproforma, int fila) {
        try {

            ordenproducto = 1;
            listaProductosVerifica.clear();

            if (precbotiquin.compareTo(listaproforma.get(fila).getId_Tipo_Precio().trim()) == 0) {
                LimpiaTodo();
                jComboBox4.removeAllItems();
                jLabel19.setVisible(true);
                jLabel20.setVisible(true);
                jButton3.setEnabled(true);
                jButton5.setEnabled(true);
                List<Proforma> listproformaDetalle = new ArrayList<Proforma>();

                if (listaproforma.get(fila).getId_Proforma().compareTo(idproforma) == 0) {
                    txtdni.setText(listaproforma.get(fila).getDniaux());
                    txtruc.setText(listaproforma.get(fila).getRUC());
                    jTextField2.setText(String.valueOf(listaproforma.get(fila).getId_Cliente()));
                    txtcliente.setText(listaproforma.get(fila).getNomCliente());
                    txttelefono.setText(listaproforma.get(fila).getTelefono());
                    List<Clientes> midirecciones = objClientes.Lista_Direcciones(id_botica, listaproforma.get(fila).getId_Cliente(), listaproforma.get(0).getIdEmpresa(), idproforma);

                    for (int k = 0; k < midirecciones.size(); k++) {
                        jComboBox4.addItem(midirecciones.get(k).getDireccion());
                    }

                    if (jComboBox4.getItemCount() == 0) {
                        if (listaproforma.get(fila).getDireccionProforma() != null) {
                            jComboBox4.addItem(listaproforma.get(fila).getDireccionProforma());
                        }
                    }

                    idtipopago = listaproforma.get(fila).getId_TipoPago();

                    for (int k = 0; k < jComboBox1.getItemCount(); k++) {
                        if (idtipopago == listatipospagos.get(k).getId_TipoPago()) {
                            jComboBox1.setSelectedIndex(k);
                            k = jComboBox1.getItemCount();
                        }
                    }

                    jTextField7.setText(String.valueOf(listaproforma.get(fila).getTotal()));
                    jTextField9.setText(String.valueOf(listaproforma.get(fila).getSubTotal()));

                    int id1 = listaproforma.get(fila).getId_Tipo_Venta();
                    cupon = listaproforma.get(fila).getcupon();

                    for (int w = 0; w < lis_tipo_venta.size(); w++) {
                        if (lis_tipo_venta.get(w).getId_Tipo_Venta() == id1) {
                            jComboBox2.setSelectedIndex(w);
                            break;
                        }
                    }

                    tipoprecio = listaproforma.get(fila).getId_Tipo_Precio();                    
                    jLabel20.setText(idproforma);

                }

                /*
                 * RECUPERO EL DETALLE DE LA PROFORMA
                 */
                LogicaProforma logicaProforma = new LogicaProforma();
                listproformaDetalle = logicaProforma.Recupera_Detalle_Proforma(idproforma, id_botica);
                for (int i = 0; i < listproformaDetalle.size(); i++) {
                    listadetalle = new Object[19];
                    String idprodu = listproformaDetalle.get(i).getIdproducto();
                    String descrip = listproformaDetalle.get(i).getDescipcionproducto();
                    int unidad1 = listproformaDetalle.get(i).getUNIDAD();
                    int fraccion1 = listproformaDetalle.get(i).getFRACCION();
                    double pvp = listproformaDetalle.get(i).getPrecio_Venta();
                    double pvx = listproformaDetalle.get(i).getPvx();
                    double total1 = listproformaDetalle.get(i).getTotal();
                    String orden = listproformaDetalle.get(i).getOrdenProducto();
                    int idOperRecarga = listproformaDetalle.get(i).getidOperadorRec();
                    double gratuita = listproformaDetalle.get(i).getOpGratuita();
                    double esgratuitapromo = listproformaDetalle.get(i).getEsgratispromo();
                    String cupon_prof = listproformaDetalle.get(i).getcupon();
                    int cant = listproformaDetalle.get(i).getCantbolsas();
                    
                    if (cupon_prof == null){
                        cupon_prof = "";
                    }

                    listaempaque.add(listproformaDetalle.get(i).getEmpaque());
                    double igvaux = listproformaDetalle.get(i).getIGV_Exonerado();


                    /*int Esrecarga = objProducto.Verifica_EsRecarga(idprodu);
                    if (Esrecarga == 1) {
                        String DesOperRec = objProducto.Recupera_OperadorRecarga(idproforma, idprodu);
                    }*/
                    listadetalle[0] = i + 1;
                    listadetalle[1] = idprodu;
                    listadetalle[2] = descrip;
                    listadetalle[3] = listproformaDetalle.get(i).getId_laboratorio();
                    listadetalle[4] = unidad1;
                    listadetalle[5] = fraccion1;
                    listadetalle[6] = pvp;
                    listadetalle[7] = pvx;
                    listadetalle[8] = total1;
                    listadetalle[9] = "0";
                    listadetalle[10] = "0";
                    listadetalle[11] = orden;
                    listadetalle[12] = idOperRecarga;
                    listadetalle[13] = "0";
                    listadetalle[14] = gratuita;
                    listadetalle[15] = esgratuitapromo;                    
                    ordenproducto = Integer.parseInt(orden);
                    listadetalle[16] = "";
                    listadetalle[17] = cupon_prof;
                    listadetalle[18] = cant;

                    tablaproforma.addRow(listadetalle);

                    if (igvaux == 0.0) {
                        lisProdSinIGV.add(idprodu);
                    }

                    CalculaMontos(igvaux, i);

//                       ordenproducto = ordenproducto + 1;
                    listaProductosVerifica.add(listadetalle);
                    /*                        for (int y = 0; y < listaProductosVerifica.size(); y++) {
                            Object[] valor = new Object[1];
                            valor[0] = listaProductosVerifica.get(y);

                            Object[] datos = new Object[12];
                            datos = (Object[]) valor[0];

                            if (datos[10].toString() == "1" && datos[11].toString() == "0") {

                                datos[11]= ordenproducto;


                                listaProductosVerifica.set(i, datos);
                            }
                        }
                        ordenproducto = ordenproducto +1;
                     */

                }

                if (this.jTextField2.getText().length() != 0) {
                    HabilitaBotones(true);
                    HabilitaColor();
                    jTable2.requestFocus();
                    jTable2.getSelectionModel().setSelectionInterval(0, 0);
                }

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS NO PUEDES AGREGAR ESTA PROFORMA  ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean Verifica_Fecha() {
        if (resul == -1) {
            resul = objlogiccafecha.VerificaFecha1(id_botica);
        }
        if (resul == 0) {
            return true;
        }
        return false;
    }

    private void Cliente_Comun() {
        Clientes def = objClientes.Cliente_Defecto();
        txtcliente.setText(def.getNombre_RazonSocial());
        jTextField2.setText(String.valueOf(def.getId_Cliente()));

        jComboBox4.addItem(def.getDireccion());

    }

    private void RealizaOpciones(java.awt.event.KeyEvent evt) {

        try {

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {

                String PROFOR = this.jLabel20.getText();
                if (PROFOR.equals("")) {
                    PROFOR = "000000";
                }
                modif = objProducto.RetornaPersonalModifica(PROFOR);

                String[] listaMOdif;
                String cadena1 = modif;
                listaMOdif = cadena1.split("/");
                int modificada = Integer.parseInt(listaMOdif[1]);

                if (modificada == 1) {
                    JOptionPane.showMessageDialog(this, " ESTA PROFORMA HA SIDO MODIFICADA, NO PUEDE EDITARLA ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BusquedaProducto();

            } else {
                if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
                    ListaProforma();
                } else {
                    if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
                        if (jTable2.getRowCount() > 0) {
                            if (Double.parseDouble(jTextField9.getText()) <= 0) {// MAX_CODIGO
                                JOptionPane.showMessageDialog(this, "Error \n EL MONTO TOTAL NO PUEDE SER IGUAL O MENOR QUE CERO", "Error", JOptionPane.ERROR_MESSAGE);//MAX_CODIGO
                            } else {//MAX_CODIGO
                                if (Verifica_Fecha()) {

                                    tpvta = lis_tipo_venta.get(jComboBox2.getSelectedIndex()).getId_Tipo_Venta();
                                    Double mtototal = Double.valueOf(this.jTextField7.getText());
                                    char[] campoDNI = this.txtdni.getText().toCharArray();
                                    char[] campoRUC = this.txtruc.getText().toCharArray();
                                    int tamanoDNI = campoDNI.length;
                                    int tamanoRUC = campoRUC.length;

                                    if (tpvta == 11 && mtototal >= 700 && tamanoDNI == 0) { //DNI
                                        JOptionPane.showMessageDialog(this, " ERROR : POR FAVOR INGRESE SU DNI ", "Error", JOptionPane.ERROR_MESSAGE);
                                        this.txtdni.requestFocus();
                                        this.txtruc.setEnabled(true);
                                    } else if (tpvta == 12 && tamanoRUC == 0) {
                                        JOptionPane.showMessageDialog(this, " ERROR : POR FAVOR INGRESE SU RUC ", "Error", JOptionPane.ERROR_MESSAGE);
                                        this.txtruc.requestFocus();
                                        this.txtdni.setEnabled(true);
                                    } else {
                                        GeneraProforma();
                                    }

                                    //GeneraProforma();
                                } else {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS LA HORA DEL SERVIDOR ES INCORRECTA \n PORFAVOR COMUNIQUESE CON INFORMATICA \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
                            CambioUsuario();
                        } else {
                            if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
                                CerrarVentana();
                            } else {
                                if (evt.getKeyText(evt.getKeyCode()).compareTo("F4") == 0) {
                                    if (bandseleccion == 0) {
                                        if (jTextField2.getText().trim().length() == 0) {
                                            HabilitaBotones(true);
                                            HabilitaColor();
                                            Cliente_Comun();
                                            //jTextField14.requestFocus();
                                            txtdni.requestFocus();
                                        } else {
                                            new FormClientes(this, objetoventana, id_botica).show(true);
                                        }
                                    }
                                } else {
                                    if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
                                        NuevaProforma();
                                        existe = 0;
                                        contarpromo = 0;

                                    } else {
                                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
                                            NuevoUsuario();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("RealizaOpciones " + ex.toString());
        }
    }

    private void NuevaProforma() {
        if (jTable2.getRowCount() > 0) {
            Confirmar pe = new Confirmar(objetoventana, "<html> Deseas Realizar una Nueva Proforma </html>");
            pe.show(true);
            if (pe.getConfirmar() == 1) {
                LimpiaTodo();
            }
        }
    }

    private void HabilitaColor() {
        jComboBox4.setBackground(Color.WHITE);
        txtcliente.setBackground(Color.WHITE);
        txttelefono.setBackground(Color.WHITE);
        txtdni.setBackground(Color.WHITE);
        txtruc.setBackground(Color.WHITE);
    }

    private void HabilitaBotones(boolean valor) {
        txtcliente.setEnabled(valor);
        jComboBox4.setEnabled(valor);
        txttelefono.setEnabled(valor);
        txtdni.setEnabled(valor);
        txtruc.setEnabled(valor);
        jComboBox2.setEnabled(valor);
        jComboBox1.setEnabled(valor);
    }

    private void CambioUsuario() {
        try {

            UsuarioClave objcamb = new UsuarioClave(objetoventana, 1, objventa);
            objcamb.show(true);

            if (objcamb.getUsuario().compareTo("") != 0 && objcamb.getClave().compareTo("") != 0 && objcamb.getNoencontrado() != -1) {
                String usuario1 = objcamb.getUsuario();
                String clave = objcamb.getClave();
                IdPersonalBoticaVenta = objcamb.getIdpersonal();
                Personal obj = new Personal();
                obj.setDNI(usuario1);
                obj.setContrasena(clave);
                obj.setId_botica(id_botica);
                List<UsuarioBotica> lista = logicapersonal.Verificausuario(obj);
                if (lista.size() > 0) {
                    jTextField12.setText(String.valueOf(IdPersonalBoticaVenta));
                    login.setUsuariobotica(null);
                    login.setUsuariobotica(lista);
                    AplicacionVentas.setCredenciales(lista);
                    AplicacionVentas.setId_personal_botica(lista.get(0).getId_Personal());
                    usuario = lista.get(0).getApellido() + " " + lista.get(0).getNombre();
                    AplicacionVentas.setUsuario(usuario);
                    recuperaUsuario();
                }
                jTable2.requestFocus();
            } else {
                if (objcamb.getNoencontrado() == -1) {
                    JOptionPane.showMessageDialog(this, " Usuario o Clave Incorrecta ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, " Usuario o Clave Incorrecta ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            System.out.println("CambioUsuario :" + ex.getMessage());
            JOptionPane.showMessageDialog(this, " Lo sentimos : \n Error al Cambiar de Usuario ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void recuperaUsuario() {
        try {
            IdPersonalBoticaVenta = objpricipal.credenciales.get(0).getId_Personal();
            usuario = objpricipal.credenciales.get(0).getApellido() + " " + objpricipal.credenciales.get(0).getNombre();
            jTextField11.setText(usuario);
            setIdPersonalBoticaVenta(IdPersonalBoticaVenta);
        } catch (Exception ex) {
            System.out.println("RECUPERAUSUARIO EN COTIZACION:" + ex.getMessage());
        }
    }

    public void AsignaClientes() {
        try {
            Clientes objclient = objclien.getObcliente();
            jTextField2.setText(String.valueOf(objclient.getId_Cliente()));
            txtdni.setText(objclient.getDNI());
            jComboBox4.addItem(objclient.getDireccion());
            txtcliente.setText(objclient.getNombre_RazonSocial());
            txtruc.setText(objclient.getRUC_DNI());
            txttelefono.setText(objclient.getTelefono());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void MuestraClientes(NuevoCliente obj, int id_cliente) {
        jComboBox4.addItem(obj.getObjcliente().getDireccion());
        txtdni.setText(obj.getObjcliente().getDNI());
        txtcliente.setText(obj.getObjcliente().getNombre_RazonSocial());
        txtruc.setText(obj.getObjcliente().getRUC_DNI());
        txttelefono.setText(obj.getObjcliente().getTelefono());
        idcliente = id_cliente;
        jTextField2.setText(String.valueOf(idcliente));
        HabilitaBotones(true);
        HabilitaColor();
        txtcliente.requestFocus();
    }

    private void RecalculaDescuento(Double porce) {
        if (total > 0) {
            double auxtotal = 0;

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                auxtotal += Double.parseDouble(this.jTable2.getValueAt(i, 8).toString());
            }

            auxtotal = auxtotal - (auxtotal * (porce / 100));
            total = auxtotal;
            BigDecimal bd1 = new BigDecimal(total);
            jTextField7.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        }
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

    public boolean VerificarSiEstaTablaONotieneProdAsocTabla(String CodProPromo, int[] GruposPromoGrat) {//CODIGO_MAX verifico si el cod dela promo ya esta en jtable2 antes de entrar aqui verificar si la promo estaen dos grupo promociones
        for (int i = 0; i < jTable2.getRowCount(); i++) {//entro a este metodo si el codigo de la promo es gratuita y esta en dos grupos
            String CodProducto = (String) jTable2.getValueAt(i, 1);
            if (CodProPromo.equals(CodProducto)) {
                return true;
            }
        }
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String CodProducto = (String) jTable2.getValueAt(i, 1);
            int temp[] = objProducto.PromoEstaEnDosGrupos(CodProducto, 0);
            for (int j = 1; j < GruposPromoGrat.length; j++) {//recorro desde 1 porque n la pso 0 esta el num de grupos de la promo
                if (temp[1] == GruposPromoGrat[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        // RealizaOpciones(e);
    }

    public void keyReleased(KeyEvent e) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        btnconsultar = new javax.swing.JButton();
        btnconsultarRUC = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        msgSaldo = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel25 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Cotizacion.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(70, 50));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(870, 600));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jComboBox1.setBackground(resourceMap.getColor("jComboBox1.background")); // NOI18N
        jComboBox1.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setNextFocusableComponent(jComboBox2);
        jComboBox1.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setName("jLabel8"); // NOI18N

        jComboBox2.setBackground(resourceMap.getColor("jComboBox1.background")); // NOI18N
        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.setNextFocusableComponent(jComboBox3);
        jComboBox2.setPreferredSize(new java.awt.Dimension(20, 20));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox2FocusLost(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setFocusable(false);
        jLabel9.setName("jLabel9"); // NOI18N

        jComboBox3.setBackground(resourceMap.getColor("jComboBox3.background")); // NOI18N
        jComboBox3.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox3.setForeground(resourceMap.getColor("jComboBox3.foreground")); // NOI18N
        jComboBox3.setEnabled(false);
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.setNextFocusableComponent(jTable2);
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
        );

        jLabel7.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 182, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jPanel6.setFocusable(false);
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(450, 124));

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setFocusable(false);
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setRequestFocusEnabled(false);

        jTextField9.setEditable(false);
        jTextField9.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField9.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField9.setFocusable(false);
        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.setPreferredSize(new java.awt.Dimension(95, 25));
        jTextField9.setRequestFocusEnabled(false);
        jTextField9.setSelectedTextColor(resourceMap.getColor("jTextField9.selectedTextColor")); // NOI18N
        jTextField9.setSelectionColor(resourceMap.getColor("jTextField9.selectionColor")); // NOI18N

        jLabel23.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel23.setForeground(resourceMap.getColor("jLabel23.foreground")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setFocusable(false);
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setRequestFocusEnabled(false);

        jTextField16.setEditable(false);
        jTextField16.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField16.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField16.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField16.setFocusable(false);
        jTextField16.setName("jTextField16"); // NOI18N
        jTextField16.setPreferredSize(new java.awt.Dimension(95, 25));
        jTextField16.setRequestFocusEnabled(false);
        jTextField16.setSelectedTextColor(resourceMap.getColor("jTextField16.selectedTextColor")); // NOI18N
        jTextField16.setSelectionColor(resourceMap.getColor("jTextField16.selectionColor")); // NOI18N

        jLabel28.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel28.setForeground(resourceMap.getColor("jLabel28.foreground")); // NOI18N
        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setBackground(resourceMap.getColor("jTextField7.background")); // NOI18N
        jTextField7.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.setPreferredSize(new java.awt.Dimension(95, 25));

        jLabel29.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel29.setForeground(resourceMap.getColor("jLabel29.foreground")); // NOI18N
        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jTextField15.setEditable(false);
        jTextField15.setBackground(resourceMap.getColor("jTextField15.background")); // NOI18N
        jTextField15.setFont(resourceMap.getFont("jTextField15.font")); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField15.setName("jTextField15"); // NOI18N
        jTextField15.setPreferredSize(new java.awt.Dimension(95, 25));

        jTextField17.setEditable(false);
        jTextField17.setBackground(resourceMap.getColor("jTextField17.background")); // NOI18N
        jTextField17.setFont(resourceMap.getFont("jTextField17.font")); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField17.setName("jTextField17"); // NOI18N
        jTextField17.setPreferredSize(new java.awt.Dimension(95, 25));

        jLabel30.setFont(resourceMap.getFont("jLabel30.font")); // NOI18N
        jLabel30.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel12.setBackground(resourceMap.getColor("jLabel12.background")); // NOI18N
        jLabel12.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setFocusable(false);
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setRequestFocusEnabled(false);

        jTextField5.setEditable(false);
        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField5.setFocusable(false);
        jTextField5.setInheritsPopupMenu(true);
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(225, 25));
        jTextField5.setRequestFocusEnabled(false);

        jTextField6.setEditable(false);
        jTextField6.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField6.setFocusable(false);
        jTextField6.setInheritsPopupMenu(true);
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.setPreferredSize(new java.awt.Dimension(60, 25));
        jTextField6.setRequestFocusEnabled(false);

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setFocusable(false);
        jLabel15.setName("jLabel15"); // NOI18N

        jPanel9.setBackground(resourceMap.getColor("jPanel9.background")); // NOI18N
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setOpaque(false);
        jPanel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel9KeyPressed(evt);
            }
        });

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setFocusable(false);
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jLabel2.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setFocusable(false);
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField2.setEnabled(false);
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setFocusable(false);
        jLabel3.setName("jLabel3"); // NOI18N

        txtcliente.setFont(resourceMap.getFont("txtcliente.font")); // NOI18N
        txtcliente.setForeground(resourceMap.getColor("txtcliente.foreground")); // NOI18N
        txtcliente.setToolTipText(resourceMap.getString("txtcliente.toolTipText")); // NOI18N
        txtcliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcliente.setName("txtcliente"); // NOI18N
        txtcliente.setNextFocusableComponent(jComboBox4);
        txtcliente.setPreferredSize(new java.awt.Dimension(220, 22));
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

        jLabel17.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setFocusable(false);
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N

        txtdni.setBackground(resourceMap.getColor("txtdni.background")); // NOI18N
        txtdni.setForeground(resourceMap.getColor("txtdni.foreground")); // NOI18N
        txtdni.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdni.setName("txtdni"); // NOI18N
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

        jLabel5.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        txtruc.setForeground(resourceMap.getColor("txtruc.foreground")); // NOI18N
        txtruc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtruc.setName("txtruc"); // NOI18N
        txtruc.setNextFocusableComponent(txtcliente);
        txtruc.setPreferredSize(new java.awt.Dimension(100, 20));
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

        jLabel6.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N

        txttelefono.setForeground(resourceMap.getColor("txttelefono.foreground")); // NOI18N
        txttelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefono.setName("txttelefono"); // NOI18N
        txttelefono.setNextFocusableComponent(jComboBox1);
        txttelefono.setPreferredSize(new java.awt.Dimension(60, 20));
        txttelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txttelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txttelefonoFocusLost(evt);
            }
        });
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
        });

        jComboBox4.setEditable(true);
        jComboBox4.setForeground(resourceMap.getColor("jComboBox4.foreground")); // NOI18N
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.setNextFocusableComponent(txtdni);
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

        jComboBox9.setName("jComboBox9"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setFocusable(false);
        jLabel24.setName("jLabel24"); // NOI18N

        btnconsultar.setBackground(resourceMap.getColor("btnconsultar.background")); // NOI18N
        btnconsultar.setIcon(resourceMap.getIcon("btnconsultar.icon")); // NOI18N
        btnconsultar.setText(resourceMap.getString("btnconsultar.text")); // NOI18N
        btnconsultar.setDefaultCapable(false);
        btnconsultar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnconsultar)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnconsultarRUC)))
                        .addGap(140, 140, 140))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtcliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(222, 222, 222)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnconsultarRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setFocusable(false);
        jLabel13.setName("jLabel13"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Codigo", "Producto", "Laboratorio", "Und", "Fracc", "PVP", "PVPx", "Parcial"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(430);
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
            jTable2.getColumnModel().getColumn(7).setResizable(false);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
            jTable2.getColumnModel().getColumn(8).setResizable(false);
            jTable2.getColumnModel().getColumn(8).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable2.columnModel.title8")); // NOI18N
        }

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton2.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setMnemonic('A');
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setMargin(new java.awt.Insets(2, 3, 2, 10));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(173, 15));
        jButton2.setRequestFocusEnabled(false);
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton2FocusLost(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jLabel21.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel21.setForeground(resourceMap.getColor("jLabel21.foreground")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setForeground(resourceMap.getColor("jLabel22.foreground")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField8.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField8.setFocusable(false);
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.setPreferredSize(new java.awt.Dimension(95, 25));
        jTextField8.setRequestFocusEnabled(false);
        jTextField8.setSelectedTextColor(resourceMap.getColor("jTextField8.selectedTextColor")); // NOI18N
        jTextField8.setSelectionColor(resourceMap.getColor("jTextField8.selectionColor")); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setFocusable(false);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)))
                .addGap(117, 117, 117))
        );

        msgSaldo.setFont(resourceMap.getFont("msgSaldo.font")); // NOI18N
        msgSaldo.setName("msgSaldo"); // NOI18N

        jTextField11.setEditable(false);
        jTextField11.setBackground(resourceMap.getColor("jTextField11.background")); // NOI18N
        jTextField11.setFont(resourceMap.getFont("jTextField11.font")); // NOI18N
        jTextField11.setForeground(resourceMap.getColor("jTextField11.foreground")); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField11.setFocusable(false);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });

        jLabel14.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel14.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setFocusable(false);
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setFocusable(false);
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel20.setFocusable(false);
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField12.setEditable(false);
        jTextField12.setBackground(resourceMap.getColor("jTextField12.background")); // NOI18N
        jTextField12.setForeground(resourceMap.getColor("jTextField12.foreground")); // NOI18N
        jTextField12.setText(resourceMap.getString("jTextField12.text")); // NOI18N
        jTextField12.setName("jTextField12"); // NOI18N

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jTextField13.setEditable(false);
        jTextField13.setBackground(resourceMap.getColor("jTextField13.background")); // NOI18N
        jTextField13.setFont(resourceMap.getFont("jTextField13.font")); // NOI18N
        jTextField13.setForeground(resourceMap.getColor("jTextField13.foreground")); // NOI18N
        jTextField13.setName("jTextField13"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel25.setBackground(resourceMap.getColor("jLabel25.background")); // NOI18N
        jLabel25.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel25.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jToolBar2.setBorder(null);
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        jButton9.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton9);

        jButton8.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setToolTipText(resourceMap.getString("jButton8.toolTipText")); // NOI18N
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(110, 22));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton8);

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setToolTipText(resourceMap.getString("jButton10.toolTipText")); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setMaximumSize(new java.awt.Dimension(119, 41));
        jButton10.setMinimumSize(new java.awt.Dimension(119, 41));
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(110, 22));
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton10);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar2.add(jSeparator5);

        jSeparator9.setBackground(resourceMap.getColor("jSeparator9.background")); // NOI18N
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setName("jSeparator9"); // NOI18N
        jToolBar2.add(jSeparator9);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getActionMap(Cotizacion.class, this);
        jButton5.setAction(actionMap.get("AgregaCliente1")); // NOI18N
        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(120, 22));
        jButton5.setRequestFocusEnabled(false);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton1.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setNextFocusableComponent(jComboBox1);
        jButton1.setPreferredSize(new java.awt.Dimension(41, 20));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar2.add(jSeparator2);

        jSeparator7.setName("jSeparator7"); // NOI18N
        jToolBar2.add(jSeparator7);

        jButton4.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(180, 22));
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
        jToolBar2.add(jButton4);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar2.add(jSeparator4);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar2.add(jSeparator6);

        jButton3.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(120, 22));
        jButton3.setRequestFocusEnabled(false);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton3);

        jButton6.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setToolTipText(resourceMap.getString("jButton6.toolTipText")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(120, 22));
        jButton6.setRequestFocusEnabled(false);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });
        jToolBar2.add(jButton6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(msgSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(4, 4, 4)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(429, 429, 429)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel14))
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ListaProforma();
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CambioUsuario();
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (this.jTextField2.getText().trim().length() == 0) {
            HabilitaBotones(true);
            HabilitaColor();
            Cliente_Comun();
            txtcliente.requestFocus();
        } else {
            new FormClientes(this, objetoventana, id_botica).show(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int resul = objClientes.EsDescuento(listatipospagos.get(jComboBox1.getSelectedIndex()).getId_TipoPago());

        if (Double.parseDouble(jTextField7.getText()) <= 0) {// MAX_CODIGO
            JOptionPane.showMessageDialog(this, "Error \n EL MONTO TOTAL NO PUEDE SER IGUAL O MENOR QUE CERO", "Error", JOptionPane.ERROR_MESSAGE);//MAX_CODIGO
        } else {//MAX_CODIGO
            if (resul == 1) {
                int elcodigo = Integer.parseInt(jTextField2.getText().trim());
                if (objClientes.Recupera_IdCliente_Descuento(id_botica, elcodigo) == elcodigo) {
                    GeneraProforma();
                } else {
                    JOptionPane.showMessageDialog(this, "Error \n A este Cliente no se le Puede aplicar \n el Descuento al Personal \n Porfavor Seleccione un Cliente Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                GeneraProforma();
            }
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        CerrarVentana();
}//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CerrarVentana();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton6KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        CambioUsuario();
}//GEN-LAST:event_jTextField11KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CambioUsuario();
}//GEN-LAST:event_jButton7ActionPerformed

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        verdni();
        //RecuperaCliente("1");
}//GEN-LAST:event_txtdniActionPerformed

    private void RecuperaCliente(String opc) {
        String RUCDNICLIENTE = "";
        String V_RUCDNI = "";
        //char[] campo = RUCDNICLIENTE.toCharArray();
        int tamano;

        if (opc == "1") { //DNI
            RUCDNICLIENTE = this.txtdni.getText().trim().toUpperCase();
            char[] campo = RUCDNICLIENTE.toCharArray();
            tamano = campo.length;

        } else {
            RUCDNICLIENTE = this.txtruc.getText().trim().toUpperCase();
            char[] campo = RUCDNICLIENTE.toCharArray();
            tamano = campo.length;

        }

        if (opc == "1" && tamano != 8) {
            JOptionPane.showMessageDialog(this, "Ingrese DNI Valido", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            this.txtcliente.setText("");
            this.txtdni.setText("");
            this.txtdni.requestFocus();
        } else if (opc == "2" && tamano != 11) {
            JOptionPane.showMessageDialog(this, "Ingrese RUC Valido", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            this.txtruc.setText("");
            this.txtruc.requestFocus();
            this.txtdni.setEnabled(false);
        }/*else{

            List<ListaDetalles> listDatosCliente = mantProduc.verificaDatosCliente(RUCDNICLIENTE,opc);

            int cantidad = listDatosCliente.size();

            if (cantidad > 0){

                this.jTextField2.setText(listDatosCliente.get(0).getidcliente().toString());
                this.jTextField14.setText(listDatosCliente.get(0).getrazonsocial().toString());
                this.jTextField1.setText(listDatosCliente.get(0).getdni1().toString());
                if (listDatosCliente.get(0).getidcliente().toString().equals("9999999")){
                this.jComboBox4.addItem("DIRECCION");
                }else{
                this.jComboBox4.addItem(listDatosCliente.get(0).getdireccion().toString());
                }
                this.jTextField10.setText(listDatosCliente.get(0).getdni().toString());
            }else{
                JOptionPane.showMessageDialog(this, "Cliente no se encuentra Registrado","NORTFARMA",JOptionPane.INFORMATION_MESSAGE);
                this.jTextField2.setText("");
                this.jTextField14.setText("");
                this.jTextField10.setText("");
                this.jTextField1.setText("");

                new FormClientes(this, objetoventana, id_botica).show(true);
            }
        }*/
    }
    private void txtdniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusGained
        jLabel10.setForeground(new Color(98, 156, 245));
        txtdni.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_txtdniFocusGained

    private void txtdniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusLost
        jLabel10.setForeground(Color.black);
        txtdni.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_txtdniFocusLost

    private void txtdniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_txtdniKeyPressed

    private void txtrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucActionPerformed
        //jTextField4.requestFocus();
        this.jComboBox1.requestFocus();
        RecuperaCliente("2");

}//GEN-LAST:event_txtrucActionPerformed


    private void txtrucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusGained
        jLabel5.setForeground(new Color(98, 156, 245));
        txtruc.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_txtrucFocusGained

    private void txtrucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusLost
        jLabel5.setForeground(Color.black);
        txtruc.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_txtrucFocusLost

    private void txtrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyPressed
        //RealizaOpciones(evt);
        if(evt.getKeyCode()==10){
        String RUCENVIO = this.txtruc.getText().trim().toUpperCase();
        TraerRUCParaAsignar(RUCENVIO);
        }
}//GEN-LAST:event_txtrucKeyPressed

    private void TraerRUCParaAsignar(String EnviarRUC){
        try{
        listaPersonalOficina = logicaPersonal.ListaRUCBoticas(EnviarRUC);
        this.jComboBox4.removeAllItems();
        int i = jComboBox4.getSelectedIndex();
        //JOptionPane.showMessageDialog(this, i);

        if (listaPersonalOficina.size() > 1) {

            FrmDireccionesClientes obj1 = null;
            obj1 = new FrmDireccionesClientes(objetoventana, listaPersonalOficina);
            obj1.pack();
            obj1.setVisible(true);
            String rec_ruc = obj1.getRUC();
            String rec_dire = obj1.getDIRE();
            String rec_razonsocial = obj1.getRAZONSOCIAL();

            txtcliente.setText(rec_razonsocial);
            jComboBox4.addItem(rec_dire);

        }else{

             txtcliente.setText(listaPersonalOficina.get(0).getSRVC_RAZONSOCIAL());
             jComboBox4.addItem(listaPersonalOficina.get(0).getSRVC_DIRECCION());
        }
        } catch (Exception ex) {
            System.out.println("ERROR SIN CONEXION A INTERNET :" + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error: No se ha podido establecer una conexion a Oficina");
        }
    }

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        if (this.txttelefono.getText().trim().compareTo("") == 0) {
            jComboBox1.requestFocus();
        } else {
            String telef = txttelefono.getText().trim();
            List<Clientes> lista = new ArrayList<Clientes>();
            lista = objClientes.BuscarCliente(telef, 0);

            if (lista.size() > 0) {
                jTextField2.setText(String.valueOf(lista.get(0).getId_Cliente()));
                txtruc.setText(lista.get(0).getRUC_DNI());
                txtcliente.setText(lista.get(0).getNombre_RazonSocial());
                jComboBox4.addItem(lista.get(0).getDireccion());
                txtdni.setText(lista.get(0).getDNI());
            }
        }
}//GEN-LAST:event_txttelefonoActionPerformed

    private void txttelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttelefonoFocusGained
        jLabel6.setForeground(new Color(98, 156, 245));
        txttelefono.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_txttelefonoFocusGained

    private void txttelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttelefonoFocusLost
        jLabel6.setForeground(Color.black);
        txttelefono.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_txttelefonoFocusLost

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_txttelefonoKeyPressed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

        int valor = jComboBox2.getSelectedIndex();

        if (valor == 0) {
            txtcliente.setText("");
            txtruc.setText("");
            txtdni.setEnabled(true);
            txtruc.setEnabled(false);
            btnconsultarRUC.setEnabled(false);
            txtdni.requestFocus();
        }

        if (valor == 1) {
            txtdni.setText("");
            txtdni.setEnabled(false);
            btnconsultar.setEnabled(false);
            txtruc.setEnabled(true);
            btnconsultarRUC.setEnabled(true);
            txtruc.requestFocus();
        }
}//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox2FocusGained
        jLabel7.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jComboBox2FocusGained

    private void jComboBox2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox2FocusLost
        jLabel7.setForeground(Color.black);
}//GEN-LAST:event_jComboBox2FocusLost

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged

        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            if (Verifica_Productos_Afectos()) {
                AsignaCredito();
            } else {
                JOptionPane.showMessageDialog(this, " ERROR : DESCUENTO NO APLICABLE ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox3KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BusquedaProducto();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusGained
        jButton2.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jButton2FocusGained

    private void jButton2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusLost
        jButton2.setForeground(Color.black);
}//GEN-LAST:event_jButton2FocusLost

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton2KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NuevoUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NuevoUsuario();
        }

        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton7KeyPressed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void AsignaCredito() {

        if (jComboBox3.getItemCount() > 0) {

            int fila = 0;
            int id = jComboBox3.getSelectedIndex();
            total = 0;
            SubTotal = 0;
            int filaactual = jTable2.getRowCount();
            boolean existe = false;
            String idpro = ListDescuentos.get(id - 1).getIdproductodesc().trim();

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                existe = false;
                if (this.jTable2.getValueAt(i, 1).toString().trim().charAt(0) != 'S') {
                    total += Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                    String idproduc = "";
                    for (int k = 0; k < lisProdSinIGV.size(); k++) {
                        idproduc = lisProdSinIGV.get(k).toString();
                        if (jTable2.getValueAt(i, 1).toString().compareTo(idproduc) == 0) {
                            existe = true;
                            k = lisProdSinIGV.size();
                        }
                    }

                    if (!existe) {
                        double parc = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                    }
                }
            }

            existe = false;

            //VERIFICO SI ESXISTE UN DESCEUNTO DEL CLIENTE ESPECIAL
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                for (int j = 0; j < ListDescuentos.size(); j++) {
                    idpro = ListDescuentos.get(j).getIdproductodesc();
                    if (jTable2.getValueAt(i, 1).toString().trim().compareTo(idpro) == 0) {
                        existe = true;
                        codprodespec = ListDescuentos.get(j).getIdproductodesc();
                        fila = i;
                        i = jTable2.getRowCount();
                        break;
                    }
                }
            }

            if (id > 0 && lis_tipo_venta.get(jComboBox2.getSelectedIndex()).getId_Tipo_Venta() == 2) {
                double porcentaje = ListDescuentos.get(id - 1).get_pocen_descuento();
                double tot1 = ((total * porcentaje) / 100) * -1;
                BigDecimal bd = new BigDecimal(tot1);
                codprodespec = ListDescuentos.get(id - 1).getIdproductodesc();
                p = objProducto.Recupera_Porcen_Descuento(codprodespec);
                jTextField8.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

                if (!existe) {
                    listsubtotales.add(filaactual, 0);
                    listaempaque.add(filaactual, 0);
                    total = total + tot1;
                    listadetalle[0] = cantidadproductos;
                    listadetalle[1] = ListDescuentos.get(id - 1).getIdproductodesc();
                    listadetalle[2] = ListDescuentos.get(id - 1).getPorcen_descuento();
                    listadetalle[3] = ListDescuentos.get(id - 1).getId_Laboratorio();
                    listadetalle[4] = 1;
                    listadetalle[5] = 0;
                    listadetalle[6] = 0;
                    listadetalle[7] = 0;
                    listadetalle[8] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    tablaproforma.addRow(listadetalle);
                } else {
                    total = total + tot1;
                    jTextField8.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
                    tablaproforma.setValueAt(codprodespec, fila, 1);
                    tablaproforma.setValueAt(ListDescuentos.get(id - 1).getPorcen_descuento(), fila, 2);
                    tablaproforma.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 8);
                }
            } else {
                if (id == 0) {
                    if (existe)//LE QUITO EL DESCUENTO
                    {
                        jTextField8.setText("");
                        tablaproforma.removeRow(fila);
                        listsubtotales.remove(fila);
                        listaempaque.remove(fila);
                        cantidadproductos--;
                    }

                }
            }

            BigDecimal bd = new BigDecimal(total);
            SubTotal = total / (1 + (IGV / 100));

            BigDecimal bd2 = new BigDecimal(SubTotal);
            bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            SubTotal = bd2.doubleValue();
            BigDecimal bd3 = new BigDecimal(total - SubTotal);
            jTextField7.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            jTextField9.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            jTextField16.setText(bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        }
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (listatipospagos.size() > 0) {
            if (listatipospagos.get(this.jComboBox2.getSelectedIndex()).getId_TipoPago() == 2) {
                VerificaCreditoEspecial(jTextField2.getText().trim());
            }
        }
        /*
       int valor = jComboBox2.getSelectedIndex();

       if(valor==0)
       {
           txtruc.setEnabled(false);
       }

       if(valor==1)
       {
           txtdni.setText("");
       }
       //JOptionPane.showMessageDialog(rootPane, valor);
         */
    }//GEN-LAST:event_jComboBox2ActionPerformed


    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton3KeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        RealizaOpciones(evt);
    }//GEN-LAST:event_formKeyReleased

    private void jPanel9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel9KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jPanel9KeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jPanel4KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton9KeyPressed

    private void txtclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtdni.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_txtclienteKeyPressed

    private void txtclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusLost
        jLabel3.setForeground(Color.black);
        txtcliente.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_txtclienteFocusLost

    private void txtclienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusGained
        jLabel3.setForeground(new Color(98, 156, 245));
        txtcliente.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_txtclienteFocusGained

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        jComboBox4.requestFocus();
}//GEN-LAST:event_txtclienteActionPerformed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased

        int fila = jTable2.getSelectedRow();
        if (fila >= 0) {
            jTextField5.setText(this.jTable2.getValueAt(fila, 2).toString());
            jTextField6.setText(this.jTable2.getValueAt(fila, 8).toString());
        }
}//GEN-LAST:event_jTable2KeyReleased

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 127) {
            int fila = jTable2.getSelectedRow();
            if (fila >= 0) {
                EliminaProducto(fila);
                if (this.jComboBox3.getItemCount() > 0) {
                    AsignaCredito();
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int fila = jTable2.getSelectedRow();
            if (fila >= 0) {

                String PROFOR = jLabel20.getText();
                if (PROFOR.equals("")) {
                    PROFOR = "000000";
                }
                //*** COD_MAX para no mofiicar prod con promocion
                String idproducto = String.valueOf(jTable2.getValueAt(fila, 1));
                String CodPromo = (String) mantProduc.Recupera_Codigo_Promo_Aeliminar(idproducto);//MAX_CODIGO PARA NO ELIMINAR PRODUCTOS CON PROMOCION
                for (int j = 0; j < jTable2.getRowCount(); j++) {//MAX_CODIGO
                    if (CodPromo.equals(String.valueOf(jTable2.getValueAt(j, 1)))) {
                        JOptionPane.showMessageDialog(this, " NO SE PUEDE MODIFICAR EL PRODUCTO, PORQUE POSEE UNA PROMOCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                //***
                modif = objProducto.RetornaPersonalModifica(PROFOR);

                String[] listaMOdif;
                String cadena1 = modif;
                listaMOdif = cadena1.split("/");
                int modificada = Integer.parseInt(listaMOdif[1]);

                if (modificada == 1) {
                    JOptionPane.showMessageDialog(this, " ESTA PROFORMA HA SIDO MODIFICADA, NO PUEDE EDITARLA ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModificaCantidadPedida(fila);

                if (jComboBox3.getItemCount() > 0) {
                    AsignaCredito();
                }
            }
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("C") == 0) {
            //if (evt.getKeyText(evt.getKeyCode()).compareTo("D") == 0) {
            if (txtcliente.getText().trim().compareTo("CLIENTE COMUN") == 0) {
                txtcliente.setText("");
            }

            txtcliente.requestFocus();
            //jTextField1.requestFocus();
        }
        /*if (evt.getKeyText(evt.getKeyCode()).compareTo("D") == 0) {
            jTextField1.requestFocus();
        }*/
        if (evt.getKeyText(evt.getKeyCode()).compareTo("R") == 0) {
            txtruc.requestFocus();
        }

        RealizaOpciones(evt);
}//GEN-LAST:event_jTable2KeyPressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int fila = jTable2.getSelectedRow();
        if (fila >= 0) {
            jTextField5.setText(this.jTable2.getValueAt(fila, 2).toString());
            jTextField6.setText(this.jTable2.getValueAt(fila, 8).toString());
        }
}//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox2.requestFocus();
        }

        if (evt.getKeyCode() == 16) {
            txttelefono.requestFocus();
        }

        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        jLabel7.setForeground(Color.black);
}//GEN-LAST:event_jComboBox1FocusLost

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        jLabel7.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        int indice = jComboBox1.getSelectedIndex();
        idtipopago = listatipospagos.get(indice).getId_TipoPago();

        if (idtipopago == 20) {
            jTextField15.setText("0.00"); //redondeo
            String total = jTextField7.getText();
            jTextField17.setText(total);
        } else {
            if (jTable2.getRowCount() > 0) {
                recalcular();
            }
        }
}//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarActionPerformed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnconsultarActionPerformed

    private void btnconsultarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarKeyReleased
        // TODO add your handling code here:
        //verdni();
    }//GEN-LAST:event_btnconsultarKeyReleased

    private void btnconsultarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnconsultarKeyPressed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnconsultarKeyPressed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        BusquedaProducto();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10KeyPressed

    private void recalcular() {

        Double Tot = Double.parseDouble(this.jTextField7.getText());
        Double SubTot = Double.parseDouble(this.jTextField9.getText());

        BigDecimal bd1 = new BigDecimal(SubTot);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTot = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(Tot);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        Tot = bd2.doubleValue();

        //AQUI
        //AQUI
        String[] arr = String.valueOf(total).split("\\.");  // declaro el array
        int enter = Integer.parseInt(arr[0]);
        //int deci = Integer.parseInt(arr[1]);
        String deci = arr[1];

        //String x = Integer.toString(deci);
        String x = deci;
        int contardeci = x.length();

        if (contardeci > 1) {

            char ultimoNumero = String.valueOf(total).charAt(String.valueOf(total).length() - 1);
            String ultimodigito = String.valueOf(ultimoNumero);

            //if (ultimodigito.equals("0") || ultimodigito.equals("5")) {
            if (ultimodigito.equals("0")) {
                jTextField15.setText("0.0"); //redondeo
                jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                //miigv = total - SubTotal;
                auxigv = (total - SubTotal);

                BigDecimal bd3 = new BigDecimal(auxigv);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                auxigv = bd3.doubleValue();

                jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //redondeo

            } else {
                if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4) {

                    Double redondeo = (Double.valueOf(ultimodigito) / 100) * -1;
                    Double nuevototal = total - (Double.valueOf(ultimodigito) / 100);
                    //total = nuevototal;
                    //SubTotal = (total / (1 + (IGV / 100)));
                    BigDecimal bdNT = new BigDecimal(nuevototal);
                    bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    nuevototal = bdNT.doubleValue();

                    jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                    auxigv = total - SubTotal;

                    BigDecimal bd3 = new BigDecimal(auxigv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxigv = bd3.doubleValue();

                    jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                    jTextField15.setText(String.valueOf(redondeo)); //redondeo
                    jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                } else {

                    if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9) {
                        //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                        double ultimonuevo = Double.valueOf(ultimodigito);
                        Double redondeo = (ultimonuevo / 100) * -1;
                        Double nuevototal = total - (ultimonuevo / 100);
                        //total = nuevototal;
                        //SubTotal = (total / (1 + (IGV / 100)));
                        BigDecimal bdNT = new BigDecimal(nuevototal);
                        bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        nuevototal = bdNT.doubleValue();

                        jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                        jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                        auxigv = total - SubTotal;

                        BigDecimal bd3 = new BigDecimal(auxigv);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        auxigv = bd3.doubleValue();

                        jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv

                        jTextField15.setText(String.valueOf(redondeo)); //redondeo
                        jTextField17.setText(String.valueOf(nuevototal)); //Total a Pagar

                    }

                }
            }
        } else {

            jTextField7.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
            jTextField9.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
            auxigv = total - SubTotal;

            BigDecimal bd3 = new BigDecimal(auxigv);
            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            auxigv = bd3.doubleValue();

            jTextField16.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
            jTextField15.setText("0.0");                                                                    //redondeo
            jTextField17.setText(bd2.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar
        }
    }

    private void NuevoUsuario() {
        NuevoCliente pe = new NuevoCliente(this, objetoventana);
        pe.pack();
        pe.setVisible(true);
    }

    @org.jdesktop.application.Action

    // CONSULTAR DNI POR WEBSERVICES
    private void verdni() {
        try {
            String RUCDNICLIENTE = this.txtdni.getText().trim().toUpperCase();
            char[] campo = RUCDNICLIENTE.toCharArray();
            int tamano = campo.length;
            String dni = txtdni.getText();
            ClientesData cli = new ClientesData();
            Clientes clientes = new Clientes();
            clientes = cli.vercliente(dni);
            if (clientes.Nombre_RazonSocial == null) {
                String data = datosDni(dni);
                txtcliente.setText(data);
                String verificardatos = this.txtcliente.getText().trim().toUpperCase();
                char[] campo2 = verificardatos.toCharArray();
                int tamano2 = campo2.length;
                if (tamano == 8) {
                    if (tamano2 != 0) {
                        guardacliente();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "DNI no existe");
                        txtcliente.setText("CLIENTE COMUN");
                        txtdni.setText("");
                        txtdni.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                    txtdni.setText("");
                    txtdni.requestFocus();
                    txtcliente.setText("CLIENTE COMUN");
                }
            } else {
                if (tamano == 8) {
                    txtcliente.setText(clientes.Nombre_RazonSocial);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                    txtdni.requestFocus();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar :" + ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Error en la búsqueda, ingresar datos manualmente");
        }
    }

    //Guardar cliente si no existe al buscar
    private void guardacliente() {
        String idbotica = id_botica;
        int idempresa = 0;
        String ruccliente = "";
        String dni = txtdni.getText();
        String nomcliente = txtcliente.getText();
        String direecion = "";
        String telefono = "";
        String email = "";
        nuevocliente = new Clientes(ruccliente, dni, nomcliente, direecion, telefono, idempresa, email);
        int resultado = logcliente.GuardarCliente(nuevocliente, idbotica);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconsultar;
    private javax.swing.JButton btnconsultarRUC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel msgSaldo;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

    private static String datosDni(java.lang.String dni) throws IOException_Exception {
        service.Verips_Service service = new service.Verips_Service();
        service.Verips port = service.getVeripsPort();
        return port.datosDni(dni);

    }
}
