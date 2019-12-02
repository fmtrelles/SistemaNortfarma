/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CruceInventario.java
 *
 * Created on 03/12/2014, 08:37:12 AM
 */

package sistemanortfarma;

import CapaDatos.ConexionPool;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaClientes;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaCruceInventarios;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import CapaLogica.LogicaProducto;
import entidad.CruceDetalle;
import entidad.CruceInventario;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import CapaLogica.LogicaConexion;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProforma;
import CapaLogica.LogicaVenta;
import entidad.Clientes;
import entidad.Descuento;
import entidad.Guias;
import entidad.Laboratorios;
import entidad.ListaDetalles;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import entidad.Personal;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.Proforma;
import entidad.Sobrantes;
import entidad.Sobrantes_Detalles;
import entidad.TipoVenta;
import entidad.TiposPagos;
import entidad.UsuarioBotica;
import entidad.Venta;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Kelvin
 */
public class CruceInventarios extends javax.swing.JInternalFrame implements KeyListener{

    /*para el cruce*/
    LogicaCruceInventarios logicaCruce = new LogicaCruceInventarios();
    LogicaFechaHora logicaGenerico = new LogicaFechaHora();
    LogicaGuias objGuias = new LogicaGuias();
    TableColumn columnaTablaCargo = null;
    TableColumn columnaTablaDescargo = null;
    TableColumn columnaTablaDiferencia = null;
    TableColumn columnaTablaSobrantes = null;
    Inventarios objInventarios;
    List<CruceDetalle> listaCruce;
    List<Sobrantes_Detalles> listaSobra;
    MuestraVentana objetoventana = new MuestraVentana();
    private String idPersonal;
    JDesktopPane desktopPane;

    Map parameters = new HashMap();
    URL report_file;
    VerificaSistema obj;
    JasperReport masterReport = null;
    Connection con;
    Connection conex;
    JasperPrint jasperPrint;
    OpcionesReportes obreporte;
    LogicaConexion logconex = new LogicaConexion();

    /**********************************************************************************************************/
    private DefaultTableModel tablaproforma;
    Object[] listadetalle = new Object[12];
    Inventarios objpricipal;
    LogicaClientes objClientes = new LogicaClientes();
    LogicaProforma onjproforma = new LogicaProforma();
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    LogicaCPersonal logicapersonal = new LogicaCPersonal();
    RequisitosFactura objfactura = new RequisitosFactura();
    LogicaVenta objguardaventa = new LogicaVenta();
    LogicaPersonal lopersonal = new LogicaPersonal();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    List<Integer> listaempaque = new ArrayList<Integer>();
    List<Object> listsubtotales = new ArrayList<Object>();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    List<Object> lisProdSinIGV = new ArrayList<Object>();
    List<TipoVenta> lis_tipo_venta = new ArrayList<TipoVenta>();
    private List<Proforma> listdetalleProforma = new ArrayList<Proforma>();
    /*Para las promociones*/
    List<Integer> nuevaPromocion = new ArrayList();
    List<Integer> cantidadElementos = new ArrayList();
    List<ListaDetalles> DetallePromo = new ArrayList<ListaDetalles>();
    List<Clientes> ListDescuentos = new ArrayList<Clientes>();
    List<Object> listaProductosVerifica = new ArrayList<Object>();
    /*********************************
    VARIABLES PARA MI JTABLE --VENTAS
     ********************************/
    private int bandseleccion = 0, cantidadproductos = 1, podecimal = 2, unidad, fraccion, idcliente, idtipopago, idtipoventa;
    private double PVP, PVPx, descuento, parcial;
    TableColumnModel colModel;
    /**********************************/
    private Object stockempaque, empaque, stockfraccion;
    Proforma entidadproforma;
    FormClientes objcliente;
    private String tipoprecio, idmedico, colegiatura, codprodespec, ruc = null, usuario;
    private Date fechaVenta, FechaRegistro;
    private double IGV = 0, SubTotal = 0.0, total = 0.0, OpGravada = 0.0, OpExonerada=0.0, OpInafecta=0.0,OpGratuita=0.0,OpISC=0.0;
    private static int IdPersonalBoticaVenta;
    private static String id_botica;
    private String precbotiquin = "01";
    private String cantidad;
    double TotEsGratuita = 0.0;
    String esgratuitaPromo ="0";
    private String modif;
    private String esgratuita="",esgratuita2="";
    TableColumn col2, col, colu_6;
    BusquedaCliente objclien;
    Inventarios objventa;
    OpcionesMenu objsesion;
    Descuento p;
    Mant_Productos mantProduc = new Mant_Productos();
    JFrame objetoVentana;
    int resul = -1;
    int ordenproducto = 1;
    BuscarProductos objproductos = null;
    String resultado = "";

    /** Creates new form CruceInventario */
    public CruceInventarios() {
        initComponents();
    }

    CruceInventarios(java.awt.Frame parent, Inventarios obj, OpcionesMenu objmenu, JDesktopPane desktp) {
        initComponents();
        objInventarios = obj;
        desktopPane = desktp;
        /*para el cruce*/
        con = logconex.RetornaConexion();
        conex = logconex.GetConnection();
        fchaDocto.setDate(logicaGenerico.RetornaFecha());
        idboticaCargos = objInventarios.getIdbotica();
        idboticaDescargos = objInventarios.getIdbotica();
        idPersonal = objInventarios.getUsuario();
        tablaproductoCar = new ModeloTabla();
        tablaproductoDes = new ModeloTabla();
        tablaDiferencia = new ModeloTabla();
        construirCargos();
        construirDescargos();
        construirDiferencias();
        obtenerUltimoCruce();
        cargaDatosComboCruce();
        limpiaCampos();
        limpiarTabla();
        
        /*para sobrantes*/
        tablaSobrantes = new ModeloTabla();
        fchaSob.setDate(logicaGenerico.RetornaFecha());
        construirSobrantes();
        obtenerUltimoSobrante();
        cargaDatosComboSob();
        limpiaCamposSob();
        limpiarTablaSob();

        /*para faltantes*/
        objventa = obj;
        objsesion = objmenu;
        podecimal = objmenu.getCantidadDecimales();
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/tab_edit.png")));
        addKeyListener(this);
        precbotiquin = "01";//1 NORMAL 2 BOTIQUIN
        if (precbotiquin.compareTo("02") == 0) {
            jLabel16.setText("VENTA BOTIQUIN");
        } else {
            jLabel16.setText(" ");
        }
        colu_6 = jTable2.getColumnModel().getColumn(6);
        tablaproforma = (DefaultTableModel) jTable2.getModel();
        jTextField11.setText(objInventarios.getUsuario());
        IdPersonalBoticaVenta = objInventarios.getIdpesonal();
        setIdPersonalBoticaVenta(IdPersonalBoticaVenta);
        jTextField12.setText(String.valueOf(IdPersonalBoticaVenta));
        id_botica = objInventarios.getIdbotica();
        setId_botica(id_botica);
        colModel = jTable2.getColumnModel();
        AparienciaTabla();
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jTextField13.setVisible(false);  
        jTextField1.setDocument(new LimitadorLetras(jTextField1, 8));
        jTextField10.setDocument(new LimitadorLetras(jTextField10, 11));
        fechaVenta = objlogiccafecha.RetornaFecha();
        Cliente_Comun();
        RecuperaTipoVentas();
        RecuperaTiposPagos();
        setResizable(false);
        AutoCompleteDecorator.decorate(jComboBox4);
        JTextComponent editor;
        editor = (JTextComponent) jComboBox4.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jTextField1.requestFocus();
                }
                RealizaOpciones(e);
            }
        });
    }

    public static int getIdPersonalBoticaVenta() {
        return IdPersonalBoticaVenta;
    }

    public static void setIdPersonalBoticaVenta(int IdPersonalBoticaVenta) {
        CruceInventarios.IdPersonalBoticaVenta = IdPersonalBoticaVenta;
    }

    public static String getId_botica() {
        return id_botica;
    }

    public static void setId_botica(String id_botica) {
        CruceInventarios.id_botica = id_botica;
    }

    private void RecuperaTipoVentas() {
        lis_tipo_venta = objfactura.Retorna_Tipos_Ventas();
        for (int i = 0; i < lis_tipo_venta.size(); i++) {
            jComboBox2.addItem(lis_tipo_venta.get(i).getDESCRIPCION());
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanelCruce = new javax.swing.JTabbedPane();
        panelCruce = new javax.swing.JPanel();
        panelCabeceraCruce = new javax.swing.JPanel();
        lblCruceDe = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnCruzar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        txtNomCruce = new javax.swing.JTextField();
        cboCruce = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        panelProdCargos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargos = new javax.swing.JTable();
        btnAgregaProdCargos = new javax.swing.JButton();
        btnQuitaProdCargos = new javax.swing.JButton();
        btnModificaCargo = new javax.swing.JButton();
        panelProdDescargos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDescargos = new javax.swing.JTable();
        btnAgrgarProdDescargos = new javax.swing.JButton();
        btnQuitaProdDescargos = new javax.swing.JButton();
        btnModificaDescargo = new javax.swing.JButton();
        panelDiferencia = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDiferencia = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        fchaDocto = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        panelFaltantes = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        msgSaldo = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        panelSobrantes = new javax.swing.JPanel();
        panelCabeceraSob = new javax.swing.JPanel();
        lblDeInventarios2 = new javax.swing.JLabel();
        lblCruceDe2 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        btnGuardarSob = new javax.swing.JButton();
        btnGrabarSob = new javax.swing.JButton();
        cboSob = new javax.swing.JComboBox();
        btbBuscaSobrante = new javax.swing.JButton();
        panelProdSob = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSob = new javax.swing.JTable();
        panelOpc = new javax.swing.JPanel();
        btnAddProd = new javax.swing.JButton();
        btndelProd = new javax.swing.JButton();
        btnCancelSob = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtTotalSob = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        fchaSob = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CruceInventarios.class);
        tabPanelCruce.setBackground(resourceMap.getColor("tabPanelCruce.background")); // NOI18N
        tabPanelCruce.setName("tabPanelCruce"); // NOI18N

        panelCruce.setBackground(resourceMap.getColor("panelCruce.background")); // NOI18N
        panelCruce.setName("panelCruce"); // NOI18N

        panelCabeceraCruce.setBackground(resourceMap.getColor("panelCabeceraCruce.background")); // NOI18N
        panelCabeceraCruce.setName("panelCabeceraCruce"); // NOI18N

        lblCruceDe.setFont(resourceMap.getFont("lblCruceDe.font")); // NOI18N
        lblCruceDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCruceDe.setText(resourceMap.getString("lblCruceDe.text")); // NOI18N
        lblCruceDe.setName("lblCruceDe"); // NOI18N

        btnSalir.setIcon(resourceMap.getIcon("btnSalir.icon")); // NOI18N
        btnSalir.setText(resourceMap.getString("btnSalir.text")); // NOI18N
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCruzar.setIcon(resourceMap.getIcon("btnCruzar.icon")); // NOI18N
        btnCruzar.setText(resourceMap.getString("btnCruzar.text")); // NOI18N
        btnCruzar.setName("btnCruzar"); // NOI18N
        btnCruzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCruzarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(resourceMap.getIcon("btnGuardar.icon")); // NOI18N
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGrabar.setIcon(resourceMap.getIcon("btnGrabar.icon")); // NOI18N
        btnGrabar.setText(resourceMap.getString("btnGrabar.text")); // NOI18N
        btnGrabar.setName("btnGrabar"); // NOI18N
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        txtNomCruce.setBackground(resourceMap.getColor("txtNomCruce.background")); // NOI18N
        txtNomCruce.setEditable(false);
        txtNomCruce.setFont(resourceMap.getFont("txtNomCruce.font")); // NOI18N
        txtNomCruce.setForeground(resourceMap.getColor("txtNomCruce.foreground")); // NOI18N
        txtNomCruce.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomCruce.setText(resourceMap.getString("txtNomCruce.text")); // NOI18N
        txtNomCruce.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNomCruce.setName("txtNomCruce"); // NOI18N

        cboCruce.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Cruce" }));
        cboCruce.setName("cboCruce"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCabeceraCruceLayout = new javax.swing.GroupLayout(panelCabeceraCruce);
        panelCabeceraCruce.setLayout(panelCabeceraCruceLayout);
        panelCabeceraCruceLayout.setHorizontalGroup(
            panelCabeceraCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabeceraCruceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCruceDe, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomCruce, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCruce, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnCruzar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGrabar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCabeceraCruceLayout.setVerticalGroup(
            panelCabeceraCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCabeceraCruceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCabeceraCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCabeceraCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboCruce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNomCruce)
                        .addComponent(jButton1))
                    .addGroup(panelCabeceraCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCruceDe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCruzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        panelProdCargos.setBackground(resourceMap.getColor("panelProdCargos.background")); // NOI18N
        panelProdCargos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("panelProdCargos.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panelProdCargos.border.titleFont"))); // NOI18N
        panelProdCargos.setMaximumSize(new java.awt.Dimension(520, 32767));
        panelProdCargos.setMinimumSize(new java.awt.Dimension(520, 0));
        panelProdCargos.setName("panelProdCargos"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCargos.setName("tblCargos"); // NOI18N
        tblCargos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCargosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCargos);
        tblCargos.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblCargos.columnModel.title0")); // NOI18N
        tblCargos.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblCargos.columnModel.title1")); // NOI18N
        tblCargos.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblCargos.columnModel.title2")); // NOI18N
        tblCargos.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblCargos.columnModel.title3")); // NOI18N

        btnAgregaProdCargos.setIcon(resourceMap.getIcon("btnAgregaProdCargos.icon")); // NOI18N
        btnAgregaProdCargos.setText(resourceMap.getString("btnAgregaProdCargos.text")); // NOI18N
        btnAgregaProdCargos.setName("btnAgregaProdCargos"); // NOI18N
        btnAgregaProdCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaProdCargosActionPerformed(evt);
            }
        });

        btnQuitaProdCargos.setIcon(resourceMap.getIcon("btnQuitaProdCargos.icon")); // NOI18N
        btnQuitaProdCargos.setText(resourceMap.getString("btnQuitaProdCargos.text")); // NOI18N
        btnQuitaProdCargos.setName("btnQuitaProdCargos"); // NOI18N
        btnQuitaProdCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitaProdCargosActionPerformed(evt);
            }
        });

        btnModificaCargo.setIcon(resourceMap.getIcon("btnModificaCargo.icon")); // NOI18N
        btnModificaCargo.setText(resourceMap.getString("btnModificaCargo.text")); // NOI18N
        btnModificaCargo.setName("btnModificaCargo"); // NOI18N
        btnModificaCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificaCargoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProdCargosLayout = new javax.swing.GroupLayout(panelProdCargos);
        panelProdCargos.setLayout(panelProdCargosLayout);
        panelProdCargosLayout.setHorizontalGroup(
            panelProdCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdCargosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregaProdCargos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitaProdCargos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificaCargo)
                .addContainerGap(286, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );
        panelProdCargosLayout.setVerticalGroup(
            panelProdCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdCargosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProdCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregaProdCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitaProdCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificaCargo))
                .addContainerGap())
        );

        panelProdDescargos.setBackground(resourceMap.getColor("panelProdDescargos.background")); // NOI18N
        panelProdDescargos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("panelProdDescargos.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panelProdDescargos.border.titleFont"))); // NOI18N
        panelProdDescargos.setMaximumSize(new java.awt.Dimension(520, 32767));
        panelProdDescargos.setMinimumSize(new java.awt.Dimension(520, 0));
        panelProdDescargos.setName("panelProdDescargos"); // NOI18N
        panelProdDescargos.setPreferredSize(new java.awt.Dimension(520, 277));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblDescargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDescargos.setName("tblDescargos"); // NOI18N
        tblDescargos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDescargosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblDescargos);
        tblDescargos.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblDescargos.columnModel.title0")); // NOI18N
        tblDescargos.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblDescargos.columnModel.title1")); // NOI18N
        tblDescargos.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblDescargos.columnModel.title2")); // NOI18N
        tblDescargos.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblDescargos.columnModel.title3")); // NOI18N

        btnAgrgarProdDescargos.setIcon(resourceMap.getIcon("btnAgrgarProdDescargos.icon")); // NOI18N
        btnAgrgarProdDescargos.setText(resourceMap.getString("btnAgrgarProdDescargos.text")); // NOI18N
        btnAgrgarProdDescargos.setName("btnAgrgarProdDescargos"); // NOI18N
        btnAgrgarProdDescargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrgarProdDescargosActionPerformed(evt);
            }
        });

        btnQuitaProdDescargos.setIcon(resourceMap.getIcon("btnQuitaProdDescargos.icon")); // NOI18N
        btnQuitaProdDescargos.setText(resourceMap.getString("btnQuitaProdDescargos.text")); // NOI18N
        btnQuitaProdDescargos.setName("btnQuitaProdDescargos"); // NOI18N
        btnQuitaProdDescargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitaProdDescargosActionPerformed(evt);
            }
        });

        btnModificaDescargo.setIcon(resourceMap.getIcon("btnModificaDescargo.icon")); // NOI18N
        btnModificaDescargo.setText(resourceMap.getString("btnModificaDescargo.text")); // NOI18N
        btnModificaDescargo.setName("btnModificaDescargo"); // NOI18N
        btnModificaDescargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificaDescargoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProdDescargosLayout = new javax.swing.GroupLayout(panelProdDescargos);
        panelProdDescargos.setLayout(panelProdDescargosLayout);
        panelProdDescargosLayout.setHorizontalGroup(
            panelProdDescargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdDescargosLayout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addComponent(btnModificaDescargo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitaProdDescargos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgrgarProdDescargos)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        panelProdDescargosLayout.setVerticalGroup(
            panelProdDescargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdDescargosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProdDescargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgrgarProdDescargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitaProdDescargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificaDescargo))
                .addContainerGap())
        );

        panelDiferencia.setBackground(resourceMap.getColor("panelDiferencia.background")); // NOI18N
        panelDiferencia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("panelDiferencia.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panelDiferencia.border.titleFont"))); // NOI18N
        panelDiferencia.setName("panelDiferencia"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tblDiferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDiferencia.setName("tblDiferencia"); // NOI18N
        tblDiferencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiferenciaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDiferencia);
        tblDiferencia.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblDiferencia.columnModel.title0")); // NOI18N
        tblDiferencia.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblDiferencia.columnModel.title1")); // NOI18N
        tblDiferencia.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblDiferencia.columnModel.title2")); // NOI18N
        tblDiferencia.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblDiferencia.columnModel.title3")); // NOI18N

        lblTotal.setIcon(resourceMap.getIcon("lblTotal.icon")); // NOI18N
        lblTotal.setText(resourceMap.getString("lblTotal.text")); // NOI18N
        lblTotal.setName("lblTotal"); // NOI18N

        txtTotal.setBackground(resourceMap.getColor("txtTotal.background")); // NOI18N
        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        fchaDocto.setEnabled(false);
        fchaDocto.setName("fchaDocto"); // NOI18N

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDiferenciaLayout = new javax.swing.GroupLayout(panelDiferencia);
        panelDiferencia.setLayout(panelDiferenciaLayout);
        panelDiferenciaLayout.setHorizontalGroup(
            panelDiferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDiferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiferenciaLayout.createSequentialGroup()
                        .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDiferenciaLayout.setVerticalGroup(
            panelDiferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDiferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDiferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCruceLayout = new javax.swing.GroupLayout(panelCruce);
        panelCruce.setLayout(panelCruceLayout);
        panelCruceLayout.setHorizontalGroup(
            panelCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCruceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDiferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCruceLayout.createSequentialGroup()
                        .addComponent(panelProdCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProdDescargos, javax.swing.GroupLayout.PREFERRED_SIZE, 462, Short.MAX_VALUE))
                    .addComponent(panelCabeceraCruce, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        panelCruceLayout.setVerticalGroup(
            panelCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCruceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCabeceraCruce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelCruceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelProdCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProdDescargos, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPanelCruce.addTab(resourceMap.getString("panelCruce.TabConstraints.tabTitle"), panelCruce); // NOI18N

        panelFaltantes.setBackground(resourceMap.getColor("panelFaltantes.background")); // NOI18N
        panelFaltantes.setName("panelFaltantes"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setEditable(false);
        jTextField5.setFont(resourceMap.getFont("jTextField5.font")); // NOI18N
        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField5.setFocusable(false);
        jTextField5.setInheritsPopupMenu(true);
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(225, 25));
        jTextField5.setRequestFocusEnabled(false);

        jTextField6.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        jTextField6.setEditable(false);
        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField6.setFocusable(false);
        jTextField6.setInheritsPopupMenu(true);
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.setPreferredSize(new java.awt.Dimension(60, 25));
        jTextField6.setRequestFocusEnabled(false);

        jPanel9.setBackground(resourceMap.getColor("jPanel9.background")); // NOI18N
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setOpaque(false);
        jPanel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel9KeyPressed(evt);
            }
        });

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setFocusable(false);
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField2.setEnabled(false);
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField14.setBackground(resourceMap.getColor("jTextField14.background")); // NOI18N
        jTextField14.setFont(resourceMap.getFont("jTextField14.font")); // NOI18N
        jTextField14.setForeground(resourceMap.getColor("jTextField14.foreground")); // NOI18N
        jTextField14.setToolTipText(resourceMap.getString("jTextField14.toolTipText")); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField14.setName("jTextField14"); // NOI18N
        jTextField14.setPreferredSize(new java.awt.Dimension(220, 22));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
        });

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setFocusable(false);
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField10.setBackground(resourceMap.getColor("jTextField10.background")); // NOI18N
        jTextField10.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField10.setForeground(resourceMap.getColor("jTextField10.foreground")); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.setPreferredSize(new java.awt.Dimension(100, 20));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(60, 20));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jComboBox4.setEditable(true);
        jComboBox4.setForeground(resourceMap.getColor("jComboBox4.foreground")); // NOI18N
        jComboBox4.setName("jComboBox4"); // NOI18N
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jComboBox4, 0, 459, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setFocusable(false);
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 124));

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setFocusable(false);
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setRequestFocusEnabled(false);

        jTextField8.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField8.setEditable(false);
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

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setFocusable(false);
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setRequestFocusEnabled(false);

        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setEditable(false);
        jTextField9.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
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

        jTextField16.setBackground(resourceMap.getColor("jTextField16.background")); // NOI18N
        jTextField16.setEditable(false);
        jTextField16.setFont(resourceMap.getFont("jTextField16.font")); // NOI18N
        jTextField16.setForeground(resourceMap.getColor("jTextField16.foreground")); // NOI18N
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

        jTextField7.setBackground(resourceMap.getColor("jTextField7.background")); // NOI18N
        jTextField7.setEditable(false);
        jTextField7.setFont(resourceMap.getFont("jTextField7.font")); // NOI18N
        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.setPreferredSize(new java.awt.Dimension(95, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jComboBox1.setBackground(resourceMap.getColor("jComboBox1.background")); // NOI18N
        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1.setEnabled(false);
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setFocusable(false);
        jLabel9.setName("jLabel9"); // NOI18N

        jComboBox2.setBackground(resourceMap.getColor("jComboBox2.background")); // NOI18N
        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(20, 20));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
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
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setFocusable(false);
        jLabel12.setName("jLabel12"); // NOI18N

        jComboBox3.setBackground(resourceMap.getColor("jComboBox3.background")); // NOI18N
        jComboBox3.setFont(resourceMap.getFont("jComboBox3.font")); // NOI18N
        jComboBox3.setForeground(resourceMap.getColor("jComboBox3.foreground")); // NOI18N
        jComboBox3.setEnabled(false);
        jComboBox3.setName("jComboBox3"); // NOI18N
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

        jPanel8.setBackground(resourceMap.getColor("jPanel8.background")); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setFocusable(false);
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13)
        );

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setFocusable(false);
        jLabel14.setName("jLabel14"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 221, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jTextField12.setBackground(resourceMap.getColor("jTextField12.background")); // NOI18N
        jTextField12.setEditable(false);
        jTextField12.setForeground(resourceMap.getColor("jTextField12.foreground")); // NOI18N
        jTextField12.setName("jTextField12"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton2FocusLost(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117))
        );

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setFocusable(false);
        jLabel20.setName("jLabel20"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

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
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
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
        jScrollPane6.setViewportView(jTable2);

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

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setFocusable(false);
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField13.setBackground(resourceMap.getColor("jTextField13.background")); // NOI18N
        jTextField13.setEditable(false);
        jTextField13.setFont(resourceMap.getFont("jTextField13.font")); // NOI18N
        jTextField13.setForeground(resourceMap.getColor("jTextField13.foreground")); // NOI18N
        jTextField13.setName("jTextField13"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        msgSaldo.setFont(resourceMap.getFont("msgSaldo.font")); // NOI18N
        msgSaldo.setName("msgSaldo"); // NOI18N

        jTextField11.setBackground(resourceMap.getColor("jTextField11.background")); // NOI18N
        jTextField11.setEditable(false);
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

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setFocusable(false);
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel24.setFont(resourceMap.getFont("jLabel24.font")); // NOI18N
        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setFocusable(false);
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setBackground(resourceMap.getColor("jLabel25.background")); // NOI18N
        jLabel25.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setFocusable(false);
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(4, 4, 4)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(msgSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel25))
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel15))
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel19))
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msgSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout panelFaltantesLayout = new javax.swing.GroupLayout(panelFaltantes);
        panelFaltantes.setLayout(panelFaltantesLayout);
        panelFaltantesLayout.setHorizontalGroup(
            panelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFaltantesLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelFaltantesLayout.setVerticalGroup(
            panelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFaltantesLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        tabPanelCruce.addTab(resourceMap.getString("panelFaltantes.TabConstraints.tabTitle"), panelFaltantes); // NOI18N

        panelSobrantes.setBackground(resourceMap.getColor("panelSobrantes.background")); // NOI18N
        panelSobrantes.setName("panelSobrantes"); // NOI18N

        panelCabeceraSob.setBackground(resourceMap.getColor("panelCabeceraSob.background")); // NOI18N
        panelCabeceraSob.setName("panelCabeceraSob"); // NOI18N

        lblDeInventarios2.setFont(resourceMap.getFont("lblCruceDe2.font")); // NOI18N
        lblDeInventarios2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeInventarios2.setText(resourceMap.getString("lblDeInventarios2.text")); // NOI18N
        lblDeInventarios2.setName("lblDeInventarios2"); // NOI18N

        lblCruceDe2.setFont(resourceMap.getFont("lblCruceDe2.font")); // NOI18N
        lblCruceDe2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCruceDe2.setText(resourceMap.getString("lblCruceDe2.text")); // NOI18N
        lblCruceDe2.setName("lblCruceDe2"); // NOI18N

        txtNumeroDocumento.setBackground(resourceMap.getColor("txtNumeroDocumento.background")); // NOI18N
        txtNumeroDocumento.setEditable(false);
        txtNumeroDocumento.setFont(resourceMap.getFont("txtNumeroDocumento.font")); // NOI18N
        txtNumeroDocumento.setForeground(resourceMap.getColor("txtNumeroDocumento.foreground")); // NOI18N
        txtNumeroDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroDocumento.setText(resourceMap.getString("txtNumeroDocumento.text")); // NOI18N
        txtNumeroDocumento.setName("txtNumeroDocumento"); // NOI18N

        btnGuardarSob.setIcon(resourceMap.getIcon("btnGuardarSob.icon")); // NOI18N
        btnGuardarSob.setText(resourceMap.getString("btnGuardarSob.text")); // NOI18N
        btnGuardarSob.setName("btnGuardarSob"); // NOI18N
        btnGuardarSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSobActionPerformed(evt);
            }
        });

        btnGrabarSob.setIcon(resourceMap.getIcon("btnGrabarSob.icon")); // NOI18N
        btnGrabarSob.setText(resourceMap.getString("btnGrabarSob.text")); // NOI18N
        btnGrabarSob.setName("btnGrabarSob"); // NOI18N
        btnGrabarSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarSobActionPerformed(evt);
            }
        });

        cboSob.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Sobrante" }));
        cboSob.setName("cboSob"); // NOI18N

        btbBuscaSobrante.setText(resourceMap.getString("btbBuscaSobrante.text")); // NOI18N
        btbBuscaSobrante.setName("btbBuscaSobrante"); // NOI18N
        btbBuscaSobrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbBuscaSobranteActionPerformed(evt);
            }
        });

        panelProdSob.setBackground(resourceMap.getColor("panelProdSob.background")); // NOI18N
        panelProdSob.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("panelProdSob.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panelProdSob.border.titleFont"))); // NOI18N
        panelProdSob.setName("panelProdSob"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        tblSob.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSob.setName("tblSob"); // NOI18N
        tblSob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSobKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblSob);
        tblSob.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblSob.columnModel.title0")); // NOI18N
        tblSob.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblSob.columnModel.title1")); // NOI18N
        tblSob.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblSob.columnModel.title2")); // NOI18N
        tblSob.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblSob.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout panelProdSobLayout = new javax.swing.GroupLayout(panelProdSob);
        panelProdSob.setLayout(panelProdSobLayout);
        panelProdSobLayout.setHorizontalGroup(
            panelProdSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdSobLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelProdSobLayout.setVerticalGroup(
            panelProdSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdSobLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelOpc.setBackground(resourceMap.getColor("panelOpc.background")); // NOI18N
        panelOpc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("panelOpc.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panelOpc.border.titleFont"))); // NOI18N
        panelOpc.setName("panelOpc"); // NOI18N

        btnAddProd.setIcon(resourceMap.getIcon("btnAddProd.icon")); // NOI18N
        btnAddProd.setText(resourceMap.getString("btnAddProd.text")); // NOI18N
        btnAddProd.setName("btnAddProd"); // NOI18N
        btnAddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProdActionPerformed(evt);
            }
        });

        btndelProd.setIcon(resourceMap.getIcon("btndelProd.icon")); // NOI18N
        btndelProd.setText(resourceMap.getString("btndelProd.text")); // NOI18N
        btndelProd.setName("btndelProd"); // NOI18N
        btndelProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelProdActionPerformed(evt);
            }
        });

        btnCancelSob.setIcon(resourceMap.getIcon("btnCancelSob.icon")); // NOI18N
        btnCancelSob.setText(resourceMap.getString("btnCancelSob.text")); // NOI18N
        btnCancelSob.setName("btnCancelSob"); // NOI18N
        btnCancelSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSobActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcLayout = new javax.swing.GroupLayout(panelOpc);
        panelOpc.setLayout(panelOpcLayout);
        panelOpcLayout.setHorizontalGroup(
            panelOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelSob, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(btndelProd, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(btnAddProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOpcLayout.setVerticalGroup(
            panelOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndelProd)
                .addGap(18, 18, 18)
                .addComponent(btnCancelSob, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        txtObs.setColumns(20);
        txtObs.setRows(5);
        txtObs.setName("txtObs"); // NOI18N
        jScrollPane5.setViewportView(txtObs);

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtTotalSob.setBackground(resourceMap.getColor("txtTotalSob.background")); // NOI18N
        txtTotalSob.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalSob.setText(resourceMap.getString("txtTotalSob.text")); // NOI18N
        txtTotalSob.setName("txtTotalSob"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalSob, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalSob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel6.border.titleFont"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        fchaSob.setBackground(resourceMap.getColor("fchaSob.background")); // NOI18N
        fchaSob.setEnabled(false);
        fchaSob.setFont(resourceMap.getFont("fchaSob.font")); // NOI18N
        fchaSob.setName("fchaSob"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fchaSob, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fchaSob, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCabeceraSobLayout = new javax.swing.GroupLayout(panelCabeceraSob);
        panelCabeceraSob.setLayout(panelCabeceraSobLayout);
        panelCabeceraSobLayout.setHorizontalGroup(
            panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                        .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelProdSob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                        .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDeInventarios2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(lblCruceDe2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cboSob, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btbBuscaSobrante)
                        .addGap(110, 110, 110)))
                .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOpc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                        .addComponent(btnGuardarSob, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGrabarSob, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(148, 148, 148))
        );
        panelCabeceraSobLayout.setVerticalGroup(
            panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabeceraSobLayout.createSequentialGroup()
                .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGrabarSob, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(btnGuardarSob, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelOpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                        .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btbBuscaSobrante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboSob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelCabeceraSobLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblCruceDe2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDeInventarios2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProdSob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCabeceraSobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSobrantesLayout = new javax.swing.GroupLayout(panelSobrantes);
        panelSobrantes.setLayout(panelSobrantesLayout);
        panelSobrantesLayout.setHorizontalGroup(
            panelSobrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSobrantesLayout.createSequentialGroup()
                .addGroup(panelSobrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCabeceraSob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSobrantesLayout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelSobrantesLayout.setVerticalGroup(
            panelSobrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSobrantesLayout.createSequentialGroup()
                .addComponent(panelCabeceraSob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        tabPanelCruce.addTab(resourceMap.getString("panelSobrantes.TabConstraints.tabTitle"), panelSobrantes); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelCruce, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelCruce, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregaProdCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaProdCargosActionPerformed
        agregarProductosCargos();
    }//GEN-LAST:event_btnAgregaProdCargosActionPerformed

    private void btnAgrgarProdDescargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrgarProdDescargosActionPerformed
        agregarProductosDescargos();
    }//GEN-LAST:event_btnAgrgarProdDescargosActionPerformed

    private void btnCruzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCruzarActionPerformed
        if(tblCargos.getRowCount()>0 & tblDescargos.getRowCount() > 0){
            tablaDiferencia=(ModeloTabla)tblDiferencia.getModel();
            tablaDiferencia.setRowCount(0);
            txtTotal.setText("0.0");
            calcularDiferencias();
        }else {
            JOptionPane.showMessageDialog(this, "INGRESE PRODUCTOS PARA PODER CRUZAR", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCruzarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(tblDiferencia.getRowCount() > 0){
            obtenerUltimoCruce(); //gino
            guardarCruce();
            limpiarTabla();
            limpiaCampos();
        }else{
            JOptionPane.showMessageDialog(this, "ANTES DE GUARDAR EL CRUCE DEBE CRUZAR LOS PRODCUTOS", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargaCruceInv();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblCargosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCargosKeyPressed
        switch (evt.getKeyCode()) {
            case 10:
                    verificaCantidadCargo();
                break;

            case 127:

                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproductoCar.getValueAt(tblCargos.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    int ultposi;
                    int fila = this.tblCargos.getSelectedRow();
                    ultposi = ((Integer) this.tblCargos.getValueAt(fila, 0)).intValue();
                    ultposi--;
                    tablaproductoCar.removeRow(fila);
                    cantidadCar--;

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                    MiTotalSobrante();
                }

                break;
        }

    }//GEN-LAST:event_tblCargosKeyPressed

    private void tblDescargosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDescargosKeyPressed
        switch (evt.getKeyCode()) {
            case 10:
                    verificaCantidadDesCargo();
                break;

            case 127:

                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproductoDes.getValueAt(tblDescargos.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    int ultposi;
                    int fila = this.tblDescargos.getSelectedRow();
                    ultposi = ((Integer) this.tblDescargos.getValueAt(fila, 0)).intValue();
                    ultposi--;
                    tablaproductoDes.removeRow(fila);
                    cantidadDes--;

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                    MiTotalSobrante();
                }

                break;
        }
    }//GEN-LAST:event_tblDescargosKeyPressed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(tblDiferencia.getRowCount() > 0){
            int opcion = JOptionPane.showConfirmDialog(null, "SEGURO QUE DESEA GRABAR ESTE CRUCE?\nAL HACERLO SE MODIFICARA EL KARDEX", "NORTFARMA", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){

                tablaDiferencia=(ModeloTabla)tblDiferencia.getModel();
                tablaDiferencia.setRowCount(0);
                txtTotal.setText("0.0");
                calcularDiferencias();
                grabarCruceDes();
            }
        }else{
            JOptionPane.showMessageDialog(this, "ANTES DE GRABAR EL CRUCE DEBE CRUZAR LOS PRODCUTOS", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }            
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnGuardarSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSobActionPerformed
        if(tblSob.getRowCount() > 0){
            obtenerUltimoSobrante();
            guardarSobrante();
            limpiaCamposSob();
            limpiarTablaSob();
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS PARA GUARDAR EL CARGO POR SOBRANTE", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarSobActionPerformed

    private void btnGrabarSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarSobActionPerformed
        if(tblSob.getRowCount() > 0){
            int opcion = JOptionPane.showConfirmDialog(null, "SEGURO QUE DESEA GRABAR EL CARGO POR SOBRANTE?\nAL HACERLO SE MODIFICARA EL KARDEX", "NORTFARMA", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){
                grabarSobrante();
                guardarSobrante();
                limpiaCamposSob();
                limpiarTablaSob();
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS PARA GUARDAR EL CARGO POR SOBRANTE", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGrabarSobActionPerformed

    private void btnAddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProdActionPerformed
        agregarProdSobrantes();
    }//GEN-LAST:event_btnAddProdActionPerformed

    private void btbBuscaSobranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbBuscaSobranteActionPerformed
        if(cboSob.getSelectedIndex() > 0){
            cargaSobrante();
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE UN SOBRANTE ADECUADO ...", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btbBuscaSobranteActionPerformed

    private void tblSobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSobKeyPressed
        switch (evt.getKeyCode()) {
            case 10:
                    verificaCantidadSobrante();
                break;

            case 127:

                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaSobrantes.getValueAt(tblSob.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    int ultposi;
                    int fila = this.tblSob.getSelectedRow();
                    ultposi = ((Integer) this.tblSob.getValueAt(fila, 0)).intValue();
                    ultposi--;
                    tablaSobrantes.removeRow(fila);
                    cantidadSob--;

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                    MiTotalSobrante();
                }

                break;
        }
    }//GEN-LAST:event_tblSobKeyPressed

    private void btndelProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelProdActionPerformed
        if(tblSob.getRowCount() > 0){
            if(tblSob.getSelectedRow() >= 0){
                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaSobrantes.getValueAt(tblSob.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);


                if (reply == JOptionPane.YES_OPTION) {

                    int ultposi;
                    int fila = this.tblSob.getSelectedRow();
                    ultposi = ((Integer) this.tblSob.getValueAt(fila, 0)).intValue();
                    ultposi--;
                    tablaSobrantes.removeRow(fila);
                    cantidadSob--;

                                if (ultposi > 0) {
                                    ReordenaTabla(ultposi);
                                } else if (fila == 0) {
                                    ReordenaTabla(fila);
                                }

                    MiTotalSobrante();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO PARA ELIMINAR", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTO PARA ELIMINAR", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btndelProdActionPerformed

    private void btnModificaCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificaCargoActionPerformed
        if(tblCargos.getRowCount() > 0){
            if(tblCargos.getSelectedRow() >= 0){
                modificarProductoCargo();
            }else{
                JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO PARA MODIFICAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL CARGO PARA MODIFICAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificaCargoActionPerformed

    private void btnModificaDescargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificaDescargoActionPerformed
        if(tblDescargos.getRowCount() > 0){
            if(tblDescargos.getSelectedRow() >= 0){
                modificarProductoDesCargo();
            }else{
                JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO PARA MODIFICAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL DESCARGO PARA MODIFICAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificaDescargoActionPerformed

    private void btnQuitaProdCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitaProdCargosActionPerformed
        if(tblCargos.getRowCount() > 0){
            if(tblCargos.getSelectedRow() >= 0) {
                eliminarCargoCruce();
            }else{
                JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO PARA ELIMINAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL CARGO PARA ELIMINAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitaProdCargosActionPerformed

    private void btnQuitaProdDescargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitaProdDescargosActionPerformed
        if(tblDescargos.getRowCount() > 0){
            if(tblDescargos.getSelectedRow() > 0) {
                eliminaDescargoCruce();
            }else {
                JOptionPane.showMessageDialog(this, "SELECCIONE UN PRODUCTO PARA ELIMINAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL DESCARGO PARA ELIMINAR","NORTFARMA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitaProdDescargosActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        jComboBox4.requestFocus();
}//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
        jLabel3.setForeground(new Color(98, 156, 245));
        jTextField14.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
        jLabel3.setForeground(Color.black);
        jTextField14.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField14FocusLost

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField1.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField10.requestFocus();
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jLabel10.setForeground(new Color(98, 156, 245));
        jTextField1.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        jLabel10.setForeground(Color.black);
        jTextField1.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        jTextField4.requestFocus();
}//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
        jLabel5.setForeground(new Color(98, 156, 245));
        jTextField10.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField10FocusGained

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
        jLabel5.setForeground(Color.black);
        jTextField10.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField10FocusLost

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        if (this.jTextField4.getText().trim().compareTo("") == 0) {
            jComboBox1.requestFocus();
        } else {
            String telef = jTextField4.getText().trim();
            List<Clientes> lista = new ArrayList<Clientes>();
            lista = objClientes.BuscarCliente(telef, 0);

            if (lista.size() > 0) {
                jTextField2.setText(String.valueOf(lista.get(0).getId_Cliente()));
                jTextField10.setText(lista.get(0).getRUC_DNI());
                jTextField14.setText(lista.get(0).getNombre_RazonSocial());
                jComboBox4.addItem(lista.get(0).getDireccion());
                jTextField1.setText(lista.get(0).getDNI());
            }
        }
}//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        jLabel6.setForeground(new Color(98, 156, 245));
        jTextField4.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        jLabel6.setForeground(Color.black);
        jTextField4.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField4FocusLost

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField4KeyPressed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed

}//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed

}//GEN-LAST:event_jComboBox4KeyPressed

    private void jPanel9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel9KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jPanel9KeyPressed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

}//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        jLabel7.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        jLabel7.setForeground(Color.black);
}//GEN-LAST:event_jComboBox1FocusLost

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox2.requestFocus();
        }

        if (evt.getKeyCode() == 16) {
            jTextField4.requestFocus();
        }

        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

}//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (listatipospagos.size() > 0) {
            if (listatipospagos.get(this.jComboBox2.getSelectedIndex()).getId_TipoPago() == 2) {
                VerificaCreditoEspecial(jTextField2.getText().trim());
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

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

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

}//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox3KeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jPanel4KeyPressed

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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int fila = jTable2.getSelectedRow();
        if (fila >= 0) {
            jTextField5.setText(this.jTable2.getValueAt(fila, 2).toString());
            jTextField6.setText(this.jTable2.getValueAt(fila, 8).toString());
        }
}//GEN-LAST:event_jTable2MouseClicked

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

                ModificaCantidadPedida(fila);



                if (jComboBox3.getItemCount() > 0) {
                    AsignaCredito();
                }
            }
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("C") == 0) {
            if (jTextField14.getText().trim().compareTo("CLIENTE COMUN") == 0) {
                jTextField14.setText("");
            }

            jTextField14.requestFocus();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("D") == 0) {
            jTextField1.requestFocus();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("R") == 0) {
            jTextField10.requestFocus();
        }


        RealizaOpciones(evt);
}//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased

        int fila = jTable2.getSelectedRow();
        if (fila >= 0) {
            jTextField5.setText(this.jTable2.getValueAt(fila, 2).toString());
            jTextField6.setText(this.jTable2.getValueAt(fila, 8).toString());
        }
}//GEN-LAST:event_jTable2KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//        CambioUsuario();
}//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton7KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        //CambioUsuario();
}//GEN-LAST:event_jTextField11KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ListaProforma();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTable2.getRowCount() > 0) {
            if (Verifica_Fecha()) {
                GeneraProforma();
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS LA HORA DEL SERVIDOR ES INCORRECTA \n PORFAVOR COMUNIQUESE CON INFORMATICA \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS PARA ESTA PROFORMA", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

            imprimeCruceAnterior();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      
            imprimeSobranteAnterior();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnCancelSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSobActionPerformed
        limpiarTablaSob();
        limpiaCamposSob();        
    }//GEN-LAST:event_btnCancelSobActionPerformed

    private void tblDiferenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiferenciaMouseClicked
        if(evt.getClickCount()<=1)
            return;
        String nomCru,CodPro,Descripcion,cantidad="";
        double importe=0.0;
        if(tblDiferencia.getSelectedRow()>=0){
            if((Double)tablaDiferencia.getValueAt(tblDiferencia.getSelectedRow(), 13)<=0.0){
                JOptionPane.showMessageDialog(this,"El producto seleccionado posee una diferencia menor o igual cero","Error", JOptionPane.ERROR_MESSAGE);
              return;
            }
            nomCru = cboCruce.getSelectedItem().toString();
            CodPro=(String)tablaDiferencia.getValueAt(tblDiferencia.getSelectedRow(), 1);
            Descripcion=(String)tablaDiferencia.getValueAt(tblDiferencia.getSelectedRow(), 2);
            importe=(Double)tablaDiferencia.getValueAt(tblDiferencia.getSelectedRow(), 13);
            cantidad=(String)tablaDiferencia.getValueAt(tblDiferencia.getSelectedRow(), 4);
            //this.dispose();
            FormNotaDebitos objeto = new FormNotaDebitos(objetoventana,true,nomCru,CodPro,cantidad,  ""+importe,Descripcion);
            //desktopPane.add(objeto);
            //objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
            objeto.setVisible(true);
        }

    }//GEN-LAST:event_tblDiferenciaMouseClicked

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CruceInventario().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbBuscaSobrante;
    private javax.swing.JButton btnAddProd;
    private javax.swing.JButton btnAgregaProdCargos;
    private javax.swing.JButton btnAgrgarProdDescargos;
    private javax.swing.JButton btnCancelSob;
    private javax.swing.JButton btnCruzar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnGrabarSob;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarSob;
    private javax.swing.JButton btnModificaCargo;
    private javax.swing.JButton btnModificaDescargo;
    private javax.swing.JButton btnQuitaProdCargos;
    private javax.swing.JButton btnQuitaProdDescargos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btndelProd;
    private javax.swing.JComboBox cboCruce;
    private javax.swing.JComboBox cboSob;
    private com.toedter.calendar.JDateChooser fchaDocto;
    private com.toedter.calendar.JDateChooser fchaSob;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblCruceDe;
    private javax.swing.JLabel lblCruceDe2;
    private javax.swing.JLabel lblDeInventarios2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel msgSaldo;
    private javax.swing.JPanel panelCabeceraCruce;
    private javax.swing.JPanel panelCabeceraSob;
    private javax.swing.JPanel panelCruce;
    private javax.swing.JPanel panelDiferencia;
    private javax.swing.JPanel panelFaltantes;
    private javax.swing.JPanel panelOpc;
    private javax.swing.JPanel panelProdCargos;
    private javax.swing.JPanel panelProdDescargos;
    private javax.swing.JPanel panelProdSob;
    private javax.swing.JPanel panelSobrantes;
    private javax.swing.JTabbedPane tabPanelCruce;
    private javax.swing.JTable tblCargos;
    private javax.swing.JTable tblDescargos;
    private javax.swing.JTable tblDiferencia;
    private javax.swing.JTable tblSob;
    private javax.swing.JTextField txtNomCruce;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalSob;
    // End of variables declaration//GEN-END:variables


    /************************************
     ******* CRUCE DE INVENTARIOS *******
     ************************************/
     AgregaProductosCarDes objAgregarProductosCargos;
     private String idboticaCargos;
     LogicaProducto objProducto = new LogicaProducto();
     LogicaProducto objProductoDes = new LogicaProducto();
     private int unidadCar, fraccionCar;
     int cantidadCar = 1;
     ModeloTabla tablaproductoCar;
     private String idboticaDescargos;
     private int unidadDes, fraccionDes;
     int cantidadDes = 1;
     ModeloTabla tablaproductoDes;
     ModeloTabla tablaDiferencia;

     /*Sobrantes*/
     ModeloTabla tablaSobrantes;
     int cantidadSob = 1;
     private int unidadSob, fraccionSob;
     LogicaProducto objProductoSob = new LogicaProducto();


    private void cerrarVentana() {
        int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (p == JOptionPane.YES_OPTION) {
            objInventarios.Habilita(true);
            objInventarios.marcacdo = false;
            this.dispose();
        }
    }


    private void agregarProductosCargos() {

        tblCargos.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objAgregarProductosCargos = new AgregaProductosCarDes(obj, idboticaCargos, "CARGO X CRUCE", 1,"0");
        objAgregarProductosCargos.pack();
        objAgregarProductosCargos.show(true);

        tblCargos.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidadCar = AgregaProductosCarDes.getUnidad();
                fraccionCar = AgregaProductosCarDes.getFraccion();

                if (unidadCar != -1 && fraccionCar != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProducto.Recupera_Empaque(codpro);

                    if (unidadCar == 0 && fraccionCar == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccionCar > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    tblCargos.getRowCount()+1,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosCarDes.getReal(), idboticaCargos) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idboticaCargos)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidadCar++;
                            tablaproductoCar.addRow(data[0]);
                            this.tblCargos.requestFocus();
                            tblCargos.changeSelection(this.tblCargos.getRowCount() - 1, 0, false, false);
                            calculoMontosCargos();
                            AgregaProductosCarDes.setReal("");

                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (tblCargos.getRowCount() > 12) {
            int row = tblCargos.getRowCount() - 1;
            Rectangle rect = tblCargos.getCellRect(row, 0, true);
            tblCargos.scrollRectToVisible(rect);
            tblCargos.clearSelection();
            tblCargos.setRowSelectionInterval(row, row);
            tablaproductoCar.fireTableDataChanged();
        }
        this.tblCargos.requestFocus();
        tblCargos.changeSelection(this.tblCargos.getRowCount() - 1, 0, false, false);

    }

    private void modificarProductoCargo() {
        int filaSel = tblCargos.getSelectedRow();
        tblCargos.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objAgregarProductosCargos = new AgregaProductosCarDes(obj, idboticaCargos, "CARGO X CRUCE", 1,"0");
        objAgregarProductosCargos.pack();
        objAgregarProductosCargos.show(true);

        tblCargos.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidadCar = AgregaProductosCarDes.getUnidad();
                fraccionCar = AgregaProductosCarDes.getFraccion();

                if (unidadCar != -1 && fraccionCar != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProducto.Recupera_Empaque(codpro);

                    if (unidadCar == 0 && fraccionCar == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccionCar > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    tblCargos.getRowCount()+1,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosCarDes.getReal(), idboticaCargos) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idboticaCargos)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidadCar++;
                            tablaproductoCar.addRow(data[0]);
                            this.tblCargos.requestFocus();
                            tblCargos.changeSelection(this.tblCargos.getRowCount() - 1, 0, false, false);
                            calculoMontosCargos();
                            AgregaProductosCarDes.setReal("");
                            
                            tablaproductoCar.moveRow(tablaproductoCar.getRowCount()-1, tablaproductoCar.getRowCount()-1,filaSel);
                            tablaproductoCar.removeRow(filaSel+1);
                            tblCargos.removeAll();
                            tblCargos.setModel(tablaproductoCar);
                            tblCargos.setValueAt(filaSel+1, filaSel, 0);



                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (tblCargos.getRowCount() > 12) {
            int row = tblCargos.getRowCount() - 1;
            Rectangle rect = tblCargos.getCellRect(row, 0, true);
            tblCargos.scrollRectToVisible(rect);
            tblCargos.clearSelection();
            tblCargos.setRowSelectionInterval(row, row);
            tablaproductoCar.fireTableDataChanged();
        }
        this.tblCargos.requestFocus();
        tblCargos.changeSelection(this.tblCargos.getRowCount() - 1, 0, false, false);

    }

    private void modificarProductoDesCargo() {
        int filaSel = tblDescargos.getSelectedRow();
        tblDescargos.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objAgregarProductosCargos = new AgregaProductosCarDes(obj, idboticaDescargos, "DESCARGO X CRUCE", 1,"0");
        objAgregarProductosCargos.pack();
        objAgregarProductosCargos.show(true);

        tblDescargos.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidadDes = AgregaProductosCarDes.getUnidad();
                fraccionDes = AgregaProductosCarDes.getFraccion();

                if (unidadDes != -1 && fraccionDes != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProductoDes.Recupera_Empaque(codpro);

                    if (unidadDes == 0 && fraccionDes == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccionDes > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    tblDescargos.getRowCount()+1,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProductoDes.RecuperaPrecio(AgregaProductosCarDes.getReal(), idboticaDescargos) * (100 - objProductoDes.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idboticaDescargos)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidadDes++;
                            tablaproductoDes.addRow(data[0]);
                            this.tblDescargos.requestFocus();
                            tblDescargos.changeSelection(this.tblDescargos.getRowCount() - 1, 0, false, false);
                            calculoMontosDescargos();
                            AgregaProductosCarDes.setReal("");

                            tablaproductoDes.moveRow(tablaproductoDes.getRowCount()-1, tablaproductoDes.getRowCount()-1,filaSel);
                            tablaproductoDes.removeRow(filaSel+1);
                            tblDescargos.removeAll();
                            tblDescargos.setModel(tablaproductoDes);
                            tblDescargos.setValueAt(filaSel+1, filaSel, 0);
                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (tblDescargos.getRowCount() > 12) {
            int row = tblDescargos.getRowCount() - 1;
            Rectangle rect = tblDescargos.getCellRect(row, 0, true);
            tblDescargos.scrollRectToVisible(rect);
            tblDescargos.clearSelection();
            tblDescargos.setRowSelectionInterval(row, row);
            tablaproductoDes.fireTableDataChanged();
        }
        this.tblDescargos.requestFocus();
        tblDescargos.changeSelection(this.tblDescargos.getRowCount() - 1, 0, false, false);

    }

    private void agregarProductosDescargos() {

        tblDescargos.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objAgregarProductosCargos = new AgregaProductosCarDes(obj, idboticaDescargos, "DESCARGO X CRUCE", 1,"0");
        objAgregarProductosCargos.pack();
        objAgregarProductosCargos.show(true);

        tblDescargos.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidadDes = AgregaProductosCarDes.getUnidad();
                fraccionDes = AgregaProductosCarDes.getFraccion();

                if (unidadDes != -1 && fraccionDes != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProductoDes.Recupera_Empaque(codpro);

                    if (unidadDes == 0 && fraccionDes == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccionDes > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    tblDescargos.getRowCount()+1,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProductoDes.RecuperaPrecio(AgregaProductosCarDes.getReal(), idboticaDescargos) * (100 - objProductoDes.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idboticaDescargos)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidadDes++;
                            tablaproductoDes.addRow(data[0]);
                            this.tblDescargos.requestFocus();
                            tblDescargos.changeSelection(this.tblDescargos.getRowCount() - 1, 0, false, false);
                            calculoMontosDescargos();
                            AgregaProductosCarDes.setReal("");

                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (tblDescargos.getRowCount() > 12) {
            int row = tblDescargos.getRowCount() - 1;
            Rectangle rect = tblDescargos.getCellRect(row, 0, true);
            tblDescargos.scrollRectToVisible(rect);
            tblDescargos.clearSelection();
            tblDescargos.setRowSelectionInterval(row, row);
            tablaproductoDes.fireTableDataChanged();
        }
        this.tblDescargos.requestFocus();
        tblDescargos.changeSelection(this.tblDescargos.getRowCount() - 1, 0, false, false);

    }

    private void cargaCruceInv() {
        if(cboCruce.getSelectedIndex() > 0){
            llenarCruce();
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CRUCE ADECUADO ...", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void calculoMontos() {

        double parcial1 = 0;
        int fila = this.tblCargos.getSelectedRow();

        try {

            String codpro = this.tblCargos.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);


            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idboticaCargos);
            double pv = objProducto.RecuperaPrecio(codpro, idboticaCargos);


            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidadCar;

            if (empaque1 > 0) {
                parcial1 = (fraccionCar * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.tblCargos.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void verificaCantidadCargo() {
        int fila = this.tblCargos.getSelectedRow();
        if (fila >= 0) {

            JOptionPane p = new JOptionPane();
            String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

            try {

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            cantidad1 = cantidad1.toUpperCase();

                            String idproducto = this.tblCargos.getValueAt(fila, 1).toString().trim();
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidadCar(cantidad1);

                            if (unidadCar == 0 && fraccionCar == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccionCar > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (resul) {

                                        tblCargos.setValueAt(cantidad1, fila, 4);
                                        calculoMontosCargos();

                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
            }

        }
    }

    private boolean VerificaCantidadCar(String cantidad) {
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


        unidadCar = Integer.valueOf(unidad1);
        fraccionCar = Integer.valueOf(fraccion1);
        return true;

    }

    private void verificaCantidadDesCargo() {
        int fila = this.tblDescargos.getSelectedRow();
        if (fila >= 0) {

            JOptionPane p = new JOptionPane();
            String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

            try {

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            cantidad1 = cantidad1.toUpperCase();

                            String idproducto = this.tblDescargos.getValueAt(fila, 1).toString().trim();
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidadDes(cantidad1);

                            if (unidadDes == 0 && fraccionDes == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccionDes > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (resul) {

                                        tblDescargos.setValueAt(cantidad1, fila, 4);
                                        calculoMontosDesCargos();

                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
            }

        }
    }

    private boolean VerificaCantidadDes(String cantidad) {
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


        unidadDes = Integer.valueOf(unidad1);
        fraccionDes = Integer.valueOf(fraccion1);
        return true;

    }

    private void grabarCruce() {

        if (tblCargos.getRowCount() > 0) {

            Integer enterito = 0;
            Integer fraccionado = 0;
            Integer tp = 0;
            boolean continua = true;
            Integer bandera = 0;

                /*Cargando Cabecera*/
                Guias entidadGuia = new Guias();
                Producto entidadProductos = new Producto();

                String operacion = "CARGO X CRUCE";

                if (continua) {

                    String docu = recuperaProximoIdInventariosCargo();

                    double valTotal=0;

                    for(int pCar=0;pCar<tblCargos.getRowCount();pCar++) {
                        double val = Double.parseDouble(tablaproductoCar.getValueAt(pCar, 6).toString());
                        valTotal = valTotal+val;
                    }

                    entidadGuia = new Guias(
                            idboticaCargos,
                            "Mostrador",
                            docu,
                            "ZZZZ",
                            logicaGenerico.ConvierteFecha(fchaDocto.getDate()),
                            OpcionesMenu.getIdpersonal_botica(),
                            logicaGenerico.ConvierteFecha(fchaDocto.getDate()),
                            operacion, valTotal);

                    entidadGuia.setNumero(recuperaProximoIdInventariosCargo());
                    Confirmar p = new Confirmar(objetoventana, "DESEA GUARDAR EL CARGO X CRUCE");
                    p.show(true);

                    if (p.getConfirmar() == 1) {

                        if (objGuias.guiaCargada(entidadGuia, tp, 1) == 0) {

                            /*********** CARGAR DETALLE CARGO *****************/
                            Movimiento_Detalle detalle;
                            Producto producto;
                            List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
                            array.removeAll(array);

                            for (Integer totalProductos = 0; totalProductos < tablaproductoCar.getRowCount(); totalProductos++) {
                                detalle = new Movimiento_Detalle();
                                producto = new Producto();
                                bandera = 0;
                                fraccionado = 0;
                                enterito = 0;
                                enterito = logicaGenerico.RecuperaEntero(tablaproductoCar.getValueAt(totalProductos, 4).toString());
                                fraccionado = logicaGenerico.RecuperaFraccion(tablaproductoCar.getValueAt(totalProductos, 4).toString());

                                double pv = Double.parseDouble(tablaproductoCar.getValueAt(totalProductos, 7).toString());
                                double dcto = Double.parseDouble(tablaproductoCar.getValueAt(totalProductos, 8).toString());
                                double pvf = pv - ((pv * dcto) / 100);
                                double total = Double.parseDouble(tablaproductoCar.getValueAt(totalProductos, 6).toString());

                                producto.setIdProducto(tablaproductoCar.getValueAt(totalProductos, 1).toString());
                                detalle.setId_Producto(producto);
                                detalle.setUnidad(enterito);
                                detalle.setFraccion(fraccionado);
                                detalle.setPrecio_Venta(pv);
                                detalle.setPrecio_Venta_Final(pvf);
                                detalle.setDescuento(dcto);
                                detalle.setTotal(total);
                                array.add(detalle);

                            }

                            Movimientos ObjMovCar = objGuias.CargarDetalleGuia(entidadGuia, array, tp, 1, "CARGO X CRUCE", tp, "cargo");

                            guardarCruce();
                            enviarProformaCruce();
                            limpiaCampos();
                            limpiarTabla();
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Este Numero de Guia ya ha sido Cargado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "2. Existen Datos incompletos, Porfavor Verificar!", "Error", JOptionPane.ERROR_MESSAGE);
            }
       
    }

    private void grabarCruceDes() {

        Guias entidadGuias = new Guias();      

            if (tblDescargos.getRowCount() > 0) {

                Integer a = 0;
                Integer enterito = 0;
                Integer fraccionado = 0;
                Integer valido = 1;
                boolean pasaPorStock = true;
                Integer bandera = 0;

                try {

                    if (recuperaProximoIdInventariosDescargo().trim().length() < 1) {
                        JOptionPane.showMessageDialog(null, "Ingrese Numero de la Operacion", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    } else {

                        Confirmar p = new Confirmar(objetoventana, "DESEA GUARDAR EL DESCARGO X CRUCE");
                        p.show(true);

                        if (p.getConfirmar() == 1) {

                                String operacion;

                                operacion = "DESCARGO X CRUCE";

                                double valTotal=0;
                                for(int pDes=0;pDes<tblDescargos.getRowCount();pDes++) {
                                    double val = Double.parseDouble(tablaproductoDes.getValueAt(pDes, 6).toString());
                                    valTotal = valTotal+val;
                                }

                            entidadGuias = new Guias(
                                    idboticaCargos,
                                    "Mostrador",
                                    recuperaProximoIdInventariosDescargo(),
                                    "ZZZZ",
                                    logicaGenerico.ConvierteFecha(fchaDocto.getDate()),
                                    OpcionesMenu.getIdpersonal_botica(),
                                    logicaGenerico.ConvierteFecha(fchaDocto.getDate()),
                                    operacion, valTotal);

                            for (Integer k = 0; k < tablaproductoDes.getRowCount(); k++) {
                                enterito = logicaGenerico.RecuperaEntero(tablaproductoDes.getValueAt(k, 4).toString());
                                fraccionado = logicaGenerico.RecuperaFraccion(tablaproductoDes.getValueAt(k, 4).toString());

                                if (objProducto.validaPosibleDescargo(tablaproductoDes.getValueAt(k, 1).toString(), enterito, fraccionado)) {
                                } else {
                                    pasaPorStock = false;
                                    JOptionPane.showMessageDialog(null, "Productos con Stock Insuficiente - " + tablaproductoDes.getValueAt(k, 2).toString(), "NORTFARMA - INVENTARIOS", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }

                            if ((objGuias.guiaCargada(entidadGuias, 1, 0) == 0) && (pasaPorStock == true)) {
                                Movimiento_Detalle detalle;
                                Producto producto;
                                List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
                                array.removeAll(array);

                                for (Integer totalProductos = 0; totalProductos < tablaproductoDes.getRowCount(); totalProductos++) {
                                    producto = new Producto();
                                    detalle = new Movimiento_Detalle();
                                    bandera = 0;
                                    fraccionado = 0;
                                    enterito = 0;
                                    enterito = logicaGenerico.RecuperaEntero(tablaproductoDes.getValueAt(totalProductos, 4).toString());
                                    fraccionado = logicaGenerico.RecuperaFraccion(tablaproductoDes.getValueAt(totalProductos, 4).toString());

                                    double pv = Double.parseDouble(tablaproductoDes.getValueAt(totalProductos, 7).toString());
                                    double dcto = Double.parseDouble(tablaproductoDes.getValueAt(totalProductos, 8).toString());
                                    double pvf = pv - ((pv * dcto) / 100);
                                    double total = Double.parseDouble(tablaproductoDes.getValueAt(totalProductos, 6).toString());

                                    producto.setIdProducto(tablaproductoDes.getValueAt(totalProductos, 1).toString());
                                    detalle.setId_Producto(producto);
                                    detalle.setUnidad(enterito);
                                    detalle.setFraccion(fraccionado);
                                    detalle.setPrecio_Venta(pv);
                                    detalle.setPrecio_Venta_Final(pvf);
                                    detalle.setDescuento(dcto);
                                    detalle.setTotal(total);
                                    array.add(detalle);

                                }

                                Movimientos ObjMov = objGuias.DescargarDetalleGuia(entidadGuias, array, "DESCARGO X CRUCE",1);

                                grabarCruce();


                            } else {grabarCruce();
                                JOptionPane.showMessageDialog(null, "Ha ocurrido uno de los siguientes problemas: \n- Numero de Documento ya generado.\n- Favor verificar", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }




                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "1. Existen Caracteres Invalidos o se encuentra vacio los campos de Cantidad, fv verifique", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }        
    }

    private void eliminarCargoCruce() {
        int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproductoCar.getValueAt(tblCargos.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                int ultposi;
                int fila = this.tblCargos.getSelectedRow();
                ultposi = ((Integer) this.tblCargos.getValueAt(fila, 0)).intValue();
                ultposi--;
                tablaproductoCar.removeRow(fila);
                cantidadCar--;

                for(int i = 0 ; i<=tblCargos.getRowCount()-1 ; i++){
                    tblCargos.setValueAt(i+1, i, 0);
                }
            }
    }

    private void eliminaDescargoCruce() {
        int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproductoDes.getValueAt(tblDescargos.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    int ultposi;
                    int fila = this.tblDescargos.getSelectedRow();
                    ultposi = ((Integer) this.tblDescargos.getValueAt(fila, 0)).intValue();
                    ultposi--;
                    tablaproductoDes.removeRow(fila);
                    cantidadDes--;

                    for(int i = 0 ; i<=tblDescargos.getRowCount()-1 ; i++){
                        tblDescargos.setValueAt(i+1, i, 0);
                    }

                }
    }

    private void LimpiarDatos() {
        jTextField2.setText("");
        jTextField10.setText("");
        jTextField14.setText("");
        jComboBox4.removeAllItems();
        jTextField1.setText("");
        jTextField4.setText("");
    }

    public void AsignaDatosCliente() {
        try {
            LimpiarDatos();
            jTextField2.setText(objcliente.getCodigoCliente());
            jTextField10.setText(objcliente.getRUC_DNI());
            jTextField14.setText(objcliente.getNombre_RazonSocial());
            jTextField1.setText(objcliente.getDNI());
            jTextField4.setText(objcliente.getTelefono());
            jComboBox3.setEnabled(true);
            jTextField14.requestFocus();
            Recupera_Direcciones();
        } catch (Exception ex) {
            System.out.println("Error al recuperrar cliente:" + ex.getMessage());
        }
    }

    private void Recupera_Direcciones() {
        jComboBox4.removeAllItems();
        List<Clientes> midirecciones = objClientes.Lista_Direcciones(id_botica, Integer.parseInt(objcliente.getCodigoCliente()), objcliente.getEmpresa(),"");
        for (int i = 0; i < midirecciones.size(); i++) {
            jComboBox4.addItem(midirecciones.get(i).getDireccion());
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        RealizaOpciones(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    private void imprimeCruceAnterior() {
        String sistema = "Windows";

        try {
            parameters.put("idPersonal", idPersonal);
            parameters.put("idBotica", idboticaCargos);
            parameters.put("vCruce", cboCruce.getSelectedItem());

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Cruce.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE CRUCES");
            view.setVisible(true);
            view.setSize(850, 850);

        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void imprimeSobranteAnterior() {
        String sistema = "Windows";

        try {
            //con = logconex.RetornaConexion();
            parameters.put("vSobra", cboSob.getSelectedItem());
            parameters.put("idPersonal", idPersonal);
            parameters.put("idBotica", idboticaCargos);

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Sobrante.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE SOBRANTES");
            view.setVisible(true);
            view.setSize(850, 850);

        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    public double redondear(double numero, double ndecimal) {
        double factor = Math.pow(10, ndecimal);
        return (Math.round(numero * factor) / factor);
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void calculoMontosCargos() {

        double parcial1 = 0;
        int fila = this.tblCargos.getSelectedRow();

        try {

            String codpro = this.tblCargos.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);


            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idboticaCargos);
            double pv = objProducto.RecuperaPrecio(codpro, idboticaCargos);


            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidadCar;

            if (empaque1 > 0) {
                parcial1 = (fraccionCar * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.tblCargos.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);

        } catch (Exception ex) {
            System.out.println("Error al calcular monto "+ex.getMessage());
        }
    }

    private void calculoMontosDescargos() {

        double parcial1 = 0;
        int fila = this.tblDescargos.getSelectedRow();

        try {

            String codpro = this.tblDescargos.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProductoDes.Recupera_Empaque(codpro);


            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProductoDes.Recupera_Descuento_Producto(codpro, idboticaDescargos);
            double pv = objProductoDes.RecuperaPrecio(codpro, idboticaDescargos);


            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidadDes;

            if (empaque1 > 0) {
                parcial1 = (fraccionDes * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.tblDescargos.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);

        } catch (Exception ex) {
            System.out.println("Error al calcular monto "+ex.getMessage());
        }
    }

    private void calculoMontosDesCargos() {

        double parcial1 = 0;
        int fila = this.tblDescargos.getSelectedRow();

        try {

            String codpro = this.tblDescargos.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);


            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idboticaDescargos);
            double pv = objProducto.RecuperaPrecio(codpro, idboticaDescargos);


            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidadDes;

            if (empaque1 > 0) {
                parcial1 = (fraccionDes * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.tblDescargos.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);

        } catch (Exception ex) {
            System.out.println("Error al calcular monto "+ex.getMessage());
        }
    }

    private void construirCargos() {

        tablaproductoCar.addColumn("Nº");
        tablaproductoCar.addColumn("COD");
        tablaproductoCar.addColumn("PRODUCTO");
        tablaproductoCar.addColumn("LAB");
        tablaproductoCar.addColumn("CAN");
        tablaproductoCar.addColumn("PRECIO");
        tablaproductoCar.addColumn("MONT");
        tablaproductoCar.addColumn("PVP");
        tablaproductoCar.addColumn("DSCTO");

        tblCargos.setModel(tablaproductoCar);
        columnaTablaCargo = tblCargos.getColumnModel().getColumn(0);
        columnaTablaCargo.setPreferredWidth(25);
        columnaTablaCargo.setMinWidth(25);
        columnaTablaCargo.setMaxWidth(25);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(1);
        columnaTablaCargo.setPreferredWidth(0);
        columnaTablaCargo.setMinWidth(0);
        columnaTablaCargo.setMaxWidth(0);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(2);
        columnaTablaCargo.setPreferredWidth(235);
        columnaTablaCargo.setMinWidth(235);
        columnaTablaCargo.setMaxWidth(235);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(3);
        columnaTablaCargo.setPreferredWidth(40);
        columnaTablaCargo.setMinWidth(40);
        columnaTablaCargo.setMaxWidth(40);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(4);
        columnaTablaCargo.setPreferredWidth(40);
        columnaTablaCargo.setMinWidth(40);
        columnaTablaCargo.setMaxWidth(40);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(5);
        columnaTablaCargo.setPreferredWidth(50);
        columnaTablaCargo.setMinWidth(50);
        columnaTablaCargo.setMaxWidth(50);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(6);
        columnaTablaCargo.setPreferredWidth(55);
        columnaTablaCargo.setMinWidth(55);
        columnaTablaCargo.setMaxWidth(55);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(7);
        columnaTablaCargo.setPreferredWidth(0);
        columnaTablaCargo.setMinWidth(0);
        columnaTablaCargo.setMaxWidth(0);

        columnaTablaCargo = tblCargos.getColumnModel().getColumn(8);
        columnaTablaCargo.setPreferredWidth(0);
        columnaTablaCargo.setMinWidth(0);
        columnaTablaCargo.setMaxWidth(0);
    }

    private void construirDescargos() {

        tablaproductoDes.addColumn("Nº");
        tablaproductoDes.addColumn("COD");
        tablaproductoDes.addColumn("PRODUCTO");
        tablaproductoDes.addColumn("LAB");
        tablaproductoDes.addColumn("CAN");
        tablaproductoDes.addColumn("PRECIO");
        tablaproductoDes.addColumn("MONT");
        tablaproductoDes.addColumn("PVP");
        tablaproductoDes.addColumn("DSCTO");

        tblDescargos.setModel(tablaproductoDes);
        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(0);
        columnaTablaDescargo.setPreferredWidth(25);
        columnaTablaDescargo.setMinWidth(25);
        columnaTablaDescargo.setMaxWidth(25);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(1);
        columnaTablaDescargo.setPreferredWidth(0);
        columnaTablaDescargo.setMinWidth(0);
        columnaTablaDescargo.setMaxWidth(0);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(2);
        columnaTablaDescargo.setPreferredWidth(230);
        columnaTablaDescargo.setMinWidth(230);
        columnaTablaDescargo.setMaxWidth(230);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(3);
        columnaTablaDescargo.setPreferredWidth(40);
        columnaTablaDescargo.setMinWidth(40);
        columnaTablaDescargo.setMaxWidth(40);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(4);
        columnaTablaDescargo.setPreferredWidth(40);
        columnaTablaDescargo.setMinWidth(40);
        columnaTablaDescargo.setMaxWidth(40);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(5);
        columnaTablaDescargo.setPreferredWidth(50);
        columnaTablaDescargo.setMinWidth(50);
        columnaTablaDescargo.setMaxWidth(50);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(6);
        columnaTablaDescargo.setPreferredWidth(55);
        columnaTablaDescargo.setMinWidth(55);
        columnaTablaDescargo.setMaxWidth(55);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(7);
        columnaTablaDescargo.setPreferredWidth(0);
        columnaTablaDescargo.setMinWidth(0);
        columnaTablaDescargo.setMaxWidth(0);

        columnaTablaDescargo = tblDescargos.getColumnModel().getColumn(8);
        columnaTablaDescargo.setPreferredWidth(0);
        columnaTablaDescargo.setMinWidth(0);
        columnaTablaDescargo.setMaxWidth(0);
    }

    private void construirDiferencias() {
        tablaDiferencia.addColumn("Nº");
        tablaDiferencia.addColumn("CODI CAR");
        tablaDiferencia.addColumn("PROD CAR");
        tablaDiferencia.addColumn("LABO");
        tablaDiferencia.addColumn("CANT");
        tablaDiferencia.addColumn("PREC CAR");
        tablaDiferencia.addColumn("MONT");
        tablaDiferencia.addColumn("CODI DES");
        tablaDiferencia.addColumn("PROD DES");
        tablaDiferencia.addColumn("LABO");
        tablaDiferencia.addColumn("CANT");
        tablaDiferencia.addColumn("PREC DES");
        tablaDiferencia.addColumn("MONT");
        tablaDiferencia.addColumn("DIF");
        tablaDiferencia.addColumn("PVPCar");
        tablaDiferencia.addColumn("PVPDes");
        tablaDiferencia.addColumn("DescCar");
        tablaDiferencia.addColumn("DescDes");

        tblDiferencia.setModel(tablaDiferencia);
        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(0);
        columnaTablaDiferencia.setPreferredWidth(25);
        columnaTablaDiferencia.setMinWidth(25);
        columnaTablaDiferencia.setMaxWidth(25);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(1);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(2);
        columnaTablaDiferencia.setPreferredWidth(255);
        columnaTablaDiferencia.setMinWidth(255);
        columnaTablaDiferencia.setMaxWidth(255);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(3);
        columnaTablaDiferencia.setPreferredWidth(45);
        columnaTablaDiferencia.setMinWidth(45);
        columnaTablaDiferencia.setMaxWidth(45);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(4);
        columnaTablaDiferencia.setPreferredWidth(50);
        columnaTablaDiferencia.setMinWidth(50);
        columnaTablaDiferencia.setMaxWidth(50);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(5);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(6);
        columnaTablaDiferencia.setPreferredWidth(50);
        columnaTablaDiferencia.setMinWidth(50);
        columnaTablaDiferencia.setMaxWidth(50);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(7);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(8);
        columnaTablaDiferencia.setPreferredWidth(255);
        columnaTablaDiferencia.setMinWidth(255);
        columnaTablaDiferencia.setMaxWidth(255);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(9);
        columnaTablaDiferencia.setPreferredWidth(45);
        columnaTablaDiferencia.setMinWidth(45);
        columnaTablaDiferencia.setMaxWidth(45);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(10);
        columnaTablaDiferencia.setPreferredWidth(50);
        columnaTablaDiferencia.setMinWidth(50);
        columnaTablaDiferencia.setMaxWidth(50);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(11);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(12);
        columnaTablaDiferencia.setPreferredWidth(50);
        columnaTablaDiferencia.setMinWidth(50);
        columnaTablaDiferencia.setMaxWidth(50);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(13);
        columnaTablaDiferencia.setPreferredWidth(70);
        columnaTablaDiferencia.setMinWidth(70);
        columnaTablaDiferencia.setMaxWidth(70);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(14);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(15);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(16);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);

        columnaTablaDiferencia = tblDiferencia.getColumnModel().getColumn(17);
        columnaTablaDiferencia.setPreferredWidth(0);
        columnaTablaDiferencia.setMinWidth(0);
        columnaTablaDiferencia.setMaxWidth(0);
    }

    private void calcularDiferencias() {
        int cantFilasCargo = tblCargos.getRowCount();
        int cantFilasDescargo = tblDescargos.getRowCount();
        
        if(cantFilasCargo == cantFilasDescargo) {

            for(int i = 0 ; i <= cantFilasCargo-1 ; i++ ) {
                int num = i + 1;
                String codProdCar = tablaproductoCar.getValueAt(i, 1).toString();
                String desProdCar = tablaproductoCar.getValueAt(i, 2).toString();
                String labProdCar = tablaproductoCar.getValueAt(i, 3).toString();
                String canProdCar = tablaproductoCar.getValueAt(i, 4).toString();
                String preProdCar = tablaproductoCar.getValueAt(i, 5).toString();
                String monProdCar = tablaproductoCar.getValueAt(i, 6).toString();
                String codProdDes = tablaproductoDes.getValueAt(i, 1).toString();
                String desProdDes = tablaproductoDes.getValueAt(i, 2).toString();
                String labProdDes = tablaproductoDes.getValueAt(i, 3).toString();
                String canProdDes = tablaproductoDes.getValueAt(i, 4).toString();
                String preProdDes = tablaproductoDes.getValueAt(i, 5).toString();
                String monProdDes = tablaproductoDes.getValueAt(i, 6).toString();
                double diferencia = Double.parseDouble(monProdCar) - Double.parseDouble(monProdDes);
                //difPrecios = Math.round((precioDes - precioCar)*Math.pow(10, 2))/Math.pow(10, 2);
                if(diferencia >= 0) {
                    diferencia = 0;
                }else {
                    diferencia = Math.round((diferencia * -1)*Math.pow(10, 2))/Math.pow(10, 2);
                }
                double pvpCar = Double.parseDouble(tablaproductoCar.getValueAt(i, 7).toString());
                double pvpDes = Double.parseDouble(tablaproductoCar.getValueAt(i, 8).toString());
                double DescCar = Double.parseDouble(tablaproductoDes.getValueAt(i, 7).toString());
                double DescDes = Double.parseDouble(tablaproductoDes.getValueAt(i, 8).toString());
                
                String dif = String.valueOf(diferencia);



                Object [][] datos = {{
                    num,
                    codProdCar,desProdCar,labProdCar,canProdCar,preProdCar,monProdCar,
                    codProdDes,desProdDes,labProdDes,canProdDes,preProdDes,monProdDes,
                    dif, pvpCar, pvpDes, DescCar, DescDes

                }};

                if (tablaDiferencia == null) {
                    tablaDiferencia = new ModeloTabla() {

                        @Override
                        public boolean isCellEditable(int fila, int columna) {
                            if (columna == 11) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    };

                    tblDiferencia.setModel(tablaDiferencia);
                } else {
                    tablaDiferencia.addRow(datos[0]);
                }
                
                Double total = diferencia + Double.parseDouble(txtTotal.getText());
                txtTotal.setText(String.valueOf(Math.round((total)*Math.pow(10, 2))/Math.pow(10, 2)));
            }


        }else{
            JOptionPane.showMessageDialog(this, "No ha ingresado la misma cantidad de productos \nen cargos y descargos", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiaCampos() {
        cboCruce.removeAllItems();
        cboCruce.addItem("Seleccione Cruce");
        cargaDatosComboCruce();
    }

    private void limpiarTabla() {
        txtNomCruce.setText("");
        obtenerUltimoCruce();
        txtTotal.setText("0.0");
        tablaDiferencia=(ModeloTabla)tblDiferencia.getModel();
        tablaDiferencia.setRowCount(0);
        tablaproductoCar=(ModeloTabla)tblCargos.getModel();
        tablaproductoCar.setRowCount(0);
        tablaproductoDes=(ModeloTabla)tblDescargos.getModel();
        tablaproductoDes.setRowCount(0);
        panelProdCargos.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                "CARGO X CRUCE - "+recuperaProximoIdInventariosCargo()
                , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14)));

        panelProdDescargos.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                "DESCARGO X CRUCE - "+recuperaProximoIdInventariosDescargo()
                , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14)));

    }


    private void guardarCruce() {
        CruceInventario cruce;
        CruceDetalle cruceDeta;
        Producto prodCar;
        Laboratorios labCar;
        Producto prodDes;
        Laboratorios labDes;

        try {
            cruce = new CruceInventario();
            cruceDeta = new CruceDetalle();
            prodCar = new Producto();
            labCar = new Laboratorios();
            prodDes = new Producto();
            labDes = new Laboratorios();
            int filtblDif = tblDiferencia.getRowCount()-1;

            // para el cruceInventario
                String nomCruce = txtNomCruce.getText();
                double totalDif = Double.parseDouble(txtTotal.getText());

                cruce.setNombreCruce(nomCruce);
                cruce.setTotalDiferencia(totalDif);

                if (logicaCruce.InsertaCruceInv(cruce)) {}

            for(int j=0;j<=filtblDif;j++){
                // para el cruceDetalle
                    String codCar = tblDiferencia.getValueAt(j, 1).toString();
                    int uniCar = logicaGenerico.RecuperaEntero(tblDiferencia.getValueAt(j, 4).toString());
                    int fraCar = logicaGenerico.RecuperaFraccion(tblDiferencia.getValueAt(j, 4).toString());
                    double preCar = Double.parseDouble(tblDiferencia.getValueAt(j, 5).toString());
                    double monCar = Double.parseDouble(tblDiferencia.getValueAt(j, 6).toString());
                    String codDes = tblDiferencia.getValueAt(j, 7).toString();
                    int uniDes = logicaGenerico.RecuperaEntero(tblDiferencia.getValueAt(j, 10).toString());
                    int fraDes = logicaGenerico.RecuperaFraccion(tblDiferencia.getValueAt(j, 10).toString());
                    double preDes = Double.parseDouble(tblDiferencia.getValueAt(j, 11).toString());
                    double monDes = Double.parseDouble(tblDiferencia.getValueAt(j, 12).toString());
                    double difere = Double.parseDouble(tblDiferencia.getValueAt(j, 13).toString());
                    double pvpCar = Double.parseDouble(tblDiferencia.getValueAt(j, 14).toString());
                    double pvpDes = Double.parseDouble(tblDiferencia.getValueAt(j, 15).toString());
                    double DescCar = Double.parseDouble(tblDiferencia.getValueAt(j, 16).toString());
                    double DescDes = Double.parseDouble(tblDiferencia.getValueAt(j, 17).toString());

                    cruceDeta.setNum(j+1);
                    prodCar.setIdProducto(codCar);
                    cruceDeta.setProdCargo(prodCar);
                    cruceDeta.setUniCargo(uniCar);
                    cruceDeta.setFraCargo(fraCar);
                    cruceDeta.setPreCargo(preCar);
                    cruceDeta.setMonCargo(monCar);
                    prodDes.setIdProducto(codDes);
                    cruceDeta.setProdDesCargo(prodDes);
                    cruceDeta.setUniDesCargo(uniDes);
                    cruceDeta.setFraDesCargo(fraDes);
                    cruceDeta.setPreDesCargo(preDes);
                    cruceDeta.setMonDesCargo(monDes);
                    cruceDeta.setDiferencia(difere);
                    cruceDeta.setPvpCar(pvpCar);
                    cruceDeta.setPvpDes(pvpDes);
                    cruceDeta.setDescCar(DescCar);
                    cruceDeta.setDescDes(DescDes);

                    if(logicaCruce.InsertaCruceDetalle(cruceDeta)) {}

            }

                JOptionPane.showMessageDialog(this, "Se guardo satisfactoriamente el cruce", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
                
                imprimeCruce();

                Double vTotal = Double.parseDouble(txtTotal.getText().toString());

                if(vTotal == 0.0 || vTotal == null) {

                }else{
                    imprimeParte();
                }


        }catch(Exception ex) {
            System.out.println("Error al insertar cruce "+ex.toString());
        }
    }

    private void imprimeCruce()  {
        String sistema = "Windows";

        try {
            parameters.put("idPersonal", idPersonal);
            parameters.put("idBotica", idboticaCargos);
            parameters.put("vCruce", txtNomCruce.getText());

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Cruce.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE CRUCES");
            view.setVisible(true);
            view.setSize(850, 850);
            
        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obtenerUltimoCruce() {
        try {
                Statement comando=conex.createStatement();
                ResultSet registro = comando.executeQuery("select concat('CRU',lpad(substring(nombreCruce,4,5)+1,5,'0')) as 'nombreCruce' "
                        + "from CruceInv where idCruce = (select max(idCruce) from CruceInv);");
                if (registro.next()==true) {
                        txtNomCruce.setText(registro.getString("nombreCruce"));
                } else {
                        txtNomCruce.setText("CRU00001");
                }
        } catch(SQLException ex){
                System.out.println("Error al mostrar el ultimo cruce"+ex.toString());
        }

    }

    public ResultSet cargaComboCruce() throws Exception{
        String consulta = "select nombreCruce from CruceInv order by idCruce desc";
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    private void cargaDatosComboCruce() {
        try {

            ResultSet rs;
            rs = cargaComboCruce();

            while(rs.next()){
                cboCruce.addItem(rs.getString(1));
            }


        } catch (Exception e) {
        }
    }

    private void llenarCruce() {
        
        limpiarTabla();

        try {

                /*llenar total de diferencia del cruce*/
                //con = new ConexionPool().getConnection();
                Statement comando=conex.createStatement();
                ResultSet registro = comando.executeQuery("select totalDiferencia from CruceInv where nombreCruce = '"+cboCruce.getSelectedItem().toString()+"' ;");
                if (registro.next()==true) {
                        txtTotal.setText(registro.getString("totalDiferencia"));
                } else {
                        txtTotal.setText("0.0");
                }
                 //con.close();

        } catch(SQLException ex){
                System.out.println("Error al mostrar totalDiferencia"+ex.toString());
        }
           


        try {
                /*llenar detalle del cruce - tablas cargos, descargos y diferencia*/
                String nomCru = cboCruce.getSelectedItem().toString();
                listaCruce = logicaCruce.ListaCruceDetalle(nomCru);

                if(listaCruce.size() > 0) {
                    for(CruceDetalle detalle : listaCruce) {;

                        Object [][] datosCargos = {{
                            detalle.getNum(),
                            detalle.getProdCargo().getIdProducto(),
                            detalle.getProdCargo().getDescripcion(),
                            detalle.getProdCargo().getLaboratorio().getId_Lab(),
                            detalle.getUniCargo()+"F"+detalle.getFraCargo(),
                            detalle.getPreCargo(),
                            detalle.getMonCargo(),
                            detalle.getPvpCar(),
                            detalle.getDescCar()

                        }};

                        if (tablaproductoCar == null) {
                            tablaproductoCar = new ModeloTabla() {

                                @Override
                                public boolean isCellEditable(int fila, int columna) {
                                    if (columna == 8) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            };

                            tblCargos.setModel(tablaproductoCar);
                        } else {
                            tablaproductoCar.addRow(datosCargos[0]);
                        }

                        Object [][] datosDescargos = {{
                            detalle.getNum(),
                            detalle.getProdDesCargo().getIdProducto(),
                            detalle.getProdDesCargo().getDescripcion(),
                            detalle.getProdDesCargo().getLaboratorio().getId_Lab(),
                            detalle.getUniDesCargo()+"F"+detalle.getFraDesCargo(),
                            detalle.getPreDesCargo(),
                            detalle.getMonDesCargo(),
                            detalle.getPvpDes(),
                            detalle.getDescDes()
                        }};

                        if (tablaproductoDes == null) {
                            tablaproductoDes = new ModeloTabla() {

                                @Override
                                public boolean isCellEditable(int fila, int columna) {
                                    if (columna == 8) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            };

                            tblDescargos.setModel(tablaproductoDes);
                        } else {
                            tablaproductoDes.addRow(datosDescargos[0]);
                        }

                        Object [][] datosDiferencia = {{
                            detalle.getNum(),
                            detalle.getProdCargo().getIdProducto(),
                            detalle.getProdCargo().getDescripcion(),
                            detalle.getProdCargo().getLaboratorio().getId_Lab(),
                            detalle.getUniCargo()+"F"+detalle.getFraCargo(),
                            detalle.getPreCargo(),
                            detalle.getMonCargo(),
                            detalle.getProdDesCargo().getIdProducto(),
                            detalle.getProdDesCargo().getDescripcion(),
                            detalle.getProdDesCargo().getLaboratorio().getId_Lab(),
                            detalle.getUniDesCargo()+"F"+detalle.getFraDesCargo(),
                            detalle.getPreDesCargo(),
                            detalle.getMonDesCargo(),
                            detalle.getDiferencia(),
                            detalle.getPvpCar(),
                            detalle.getPvpDes(),
                            detalle.getDescCar(),
                            detalle.getDescDes()
                        }};

                        if (tablaDiferencia == null) {
                            tablaDiferencia = new ModeloTabla() {

                                @Override
                                public boolean isCellEditable(int fila, int columna) {
                                    if (columna == 17) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            };

                            tblDiferencia.setModel(tablaDiferencia);
                        } else {
                            tablaDiferencia.addRow(datosDiferencia[0]);
                        }

                    }
                }
                
        } catch (Exception e) {
            System.out.println("Error al listar el detalle "+e);
        }
        
    }

    private String recuperaProximoIdInventariosCargo() {
        String ultimoCruceXCargo = "";
        ultimoCruceXCargo = objGuias.GeneraNuevoNumero("CARGO X CRUCE", "Mostrador");

        return ultimoCruceXCargo;
    }

    private String recuperaProximoIdInventariosDescargo() {
        String ultimoCruceXDescargo = "";
        ultimoCruceXDescargo = objGuias.GeneraNuevoNumero("DESCARGO X CRUCE", "Mostrador");

        return ultimoCruceXDescargo;
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    public void enviarProformaCruce() {        
        String total = txtTotal.getText();

        if(Double.parseDouble(total) == 0.0 || total == null) {
            JOptionPane.showMessageDialog(null, "No hay descuento al personal", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }else{
            FormBoletas_Manuales difCruce = new FormBoletas_Manuales(objInventarios.objetoventana,objInventarios, total);
            desktopPane.add(difCruce);
            difCruce.setLocation(difCruce.getParent().getWidth() / 2 - difCruce.getWidth() / 2, difCruce.getParent().getHeight() / 2 - difCruce.getHeight() / 2);
            difCruce.setVisible(true);
        }


        
    }

    /***************************************************************************
     =============================   SOBRANTES    ==============================
     **************************************************************************/


    private void agregarProdSobrantes() {

        tblSob.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objAgregarProductosCargos = new AgregaProductosCarDes(obj, idboticaCargos, "CARGO X SOBRANTE", 1,"0");
        objAgregarProductosCargos.pack();
        objAgregarProductosCargos.show(true);

        tblSob.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidadSob = AgregaProductosCarDes.getUnidad();
                fraccionSob = AgregaProductosCarDes.getFraccion();

                if (unidadSob != -1 && fraccionSob != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProductoSob.Recupera_Empaque(codpro);

                    if (unidadSob == 0 && fraccionSob == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccionSob > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    tblSob.getRowCount()+1,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProductoSob.RecuperaPrecio(AgregaProductosCarDes.getReal(), idboticaCargos) * (100 - objProductoSob.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idboticaCargos)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidadSob++;
                            tablaSobrantes.addRow(data[0]);
                            this.tblSob.requestFocus();
                            tblSob.changeSelection(this.tblSob.getRowCount() - 1, 0, false, false);
                            calculoMontosSob();
                            AgregaProductosCarDes.setReal("");

                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (tblSob.getRowCount() > 12) {
            int row = tblSob.getRowCount() - 1;
            Rectangle rect = tblSob.getCellRect(row, 0, true);
            tblSob.scrollRectToVisible(rect);
            tblSob.clearSelection();
            tblSob.setRowSelectionInterval(row, row);
            tablaSobrantes.fireTableDataChanged();
        }
        this.tblSob.requestFocus();
        tblSob.changeSelection(this.tblSob.getRowCount() - 1, 0, false, false);

    }

    private void calculoMontosSob() {

        double parcial1 = 0;
        int fila = this.tblSob.getSelectedRow();

        try {

            String codpro = this.tblSob.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProductoSob.Recupera_Empaque(codpro);


            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProductoSob.Recupera_Descuento_Producto(codpro, idboticaCargos);
            double pv = objProductoSob.RecuperaPrecio(codpro, idboticaCargos);


            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidadSob;

            if (empaque1 > 0) {
                parcial1 = (fraccionSob * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.tblSob.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);
            MiTotalSobrante();

        } catch (Exception ex) {
            System.out.println("Error al calcular monto "+ex.getMessage());
        }
    }

    private void construirSobrantes() {
        tablaSobrantes.addColumn("Nº");
        tablaSobrantes.addColumn("CODIGO");
        tablaSobrantes.addColumn("PRODUCTO");
        tablaSobrantes.addColumn("LAB");
        tablaSobrantes.addColumn("CANT");
        tablaSobrantes.addColumn("PRECIO");
        tablaSobrantes.addColumn("MONTO");
        tablaSobrantes.addColumn("PVP");
        tablaSobrantes.addColumn("DSCTO");

        tblSob.setModel(tablaSobrantes);
        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(0);
        columnaTablaSobrantes.setPreferredWidth(35);
        columnaTablaSobrantes.setMinWidth(35);
        columnaTablaSobrantes.setMaxWidth(35);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(1);
        columnaTablaSobrantes.setPreferredWidth(80);
        columnaTablaSobrantes.setMinWidth(80);
        columnaTablaSobrantes.setMaxWidth(80);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(2);
        columnaTablaSobrantes.setPreferredWidth(340);
        columnaTablaSobrantes.setMinWidth(340);
        columnaTablaSobrantes.setMaxWidth(340);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(3);
        columnaTablaSobrantes.setPreferredWidth(70);
        columnaTablaSobrantes.setMinWidth(70);
        columnaTablaSobrantes.setMaxWidth(70);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(4);
        columnaTablaSobrantes.setPreferredWidth(70);
        columnaTablaSobrantes.setMinWidth(70);
        columnaTablaSobrantes.setMaxWidth(70);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(5);
        columnaTablaSobrantes.setPreferredWidth(70);
        columnaTablaSobrantes.setMinWidth(70);
        columnaTablaSobrantes.setMaxWidth(70);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(6);
        columnaTablaSobrantes.setPreferredWidth(70);
        columnaTablaSobrantes.setMinWidth(70);
        columnaTablaSobrantes.setMaxWidth(70);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(7);
        columnaTablaSobrantes.setPreferredWidth(0);
        columnaTablaSobrantes.setMinWidth(0);
        columnaTablaSobrantes.setMaxWidth(0);

        columnaTablaSobrantes = tblSob.getColumnModel().getColumn(8);
        columnaTablaSobrantes.setPreferredWidth(0);
        columnaTablaSobrantes.setMinWidth(0);
        columnaTablaSobrantes.setMaxWidth(0);
    }

    private void MiTotalSobrante() {
        double total = 0;

        for (int i = 0; i < this.tblSob.getRowCount(); i++) {
            total = total + Double.parseDouble(tblSob.getValueAt(i, 6).toString());
        }

        BigDecimal bd3 = new BigDecimal(total);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd3.doubleValue();

        this.txtTotalSob.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void obtenerUltimoSobrante() {
        try {
                //con = new ConexionPool().getConnection();
                Statement comando=conex.createStatement();
                ResultSet registro = comando.executeQuery("select concat('SOB',lpad(substring(codSobrante,4,5)+1,5,'0')) as 'codSobrante' from Sobrante where idSobrante = (select max(idSobrante) from Sobrante);");
                if (registro.next()==true) {
                        txtNumeroDocumento.setText(registro.getString("codSobrante"));
                } else {
                        txtNumeroDocumento.setText("SOB00001");
                }
                //con.close();
        } catch(SQLException ex){
                System.out.println("Error al mostrar el ultimo cruce"+ex.toString());
        }
    }

    public ResultSet cargaComboSob() throws Exception{
        //con = new ConexionPool().getConnection();
        String consulta = "select codSobrante from Sobrante order by idSobrante desc";
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    private void cargaDatosComboSob() {
        try {

            ResultSet rs;
            rs = cargaComboSob();

            while(rs.next()){
                cboSob.addItem(rs.getString(1));
            }


        } catch (Exception e) {
        }
    }

    private void guardarSobrante() {
        Sobrantes sobra;
        Sobrantes_Detalles sobraDeta;
        Producto prod;
        Laboratorios lab;

        try {
            sobra = new Sobrantes();
            sobraDeta = new Sobrantes_Detalles();
            prod = new Producto();
            lab = new Laboratorios();
            int filtblSob = tblSob.getRowCount()-1;

            // para el cruceInventario
                String nomCruce = txtNumeroDocumento.getText();
                double totalDif = Double.parseDouble(txtTotalSob.getText());

                sobra.setCodSob(nomCruce);
                sobra.setTotalSobrante(totalDif);

                if (logicaCruce.InsertaSobrante(sobra)) {}

            for(int j=0;j<=filtblSob;j++){

                // para el cruceDetalle
                    String codCar = tblSob.getValueAt(j, 1).toString();
                    int uniCar = logicaGenerico.RecuperaEntero(tblSob.getValueAt(j, 4).toString());
                    int fraCar = logicaGenerico.RecuperaFraccion(tblSob.getValueAt(j, 4).toString());
                    double preCar = Double.parseDouble(tblSob.getValueAt(j, 5).toString());
                    double monCar = Double.parseDouble(tblSob.getValueAt(j, 6).toString());
                    double pvpCar = Double.parseDouble(tblSob.getValueAt(j, 7).toString());
                    double DescCar = Double.parseDouble(tblSob.getValueAt(j, 8).toString());

                    prod.setIdProducto(codCar);
                    sobraDeta.setCodProd(prod);
                    sobraDeta.setUnidades(uniCar);
                    sobraDeta.setFracciones(fraCar);
                    sobraDeta.setPrecio(preCar);
                    sobraDeta.setMonto(monCar);
                    sobraDeta.setPvp(pvpCar);
                    sobraDeta.setDescuento(DescCar);

                    if(logicaCruce.InsertaSobranteDetalle(sobraDeta)) {}

            }

                JOptionPane.showMessageDialog(this, "SE GUARDO SATISFACTORIAMENTE EL SOBRANTE", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

                imprimeSobrante();

        }catch(Exception ex) {
            System.out.println("Error al insertar cruce "+ex.toString());
        }
    }

    private void cargaSobrante() {

        limpiarTablaSob();

        try {

                /*llenar total de diferencia del cruce*/
                //con = new ConexionPool().getConnection();
                Statement comando=conex.createStatement();
                ResultSet registro = comando.executeQuery("select totalSobrante from Sobrante where codSobrante = '"+cboSob.getSelectedItem().toString()+"' ;");
                if (registro.next()==true) {
                        txtTotalSob.setText(registro.getString("totalSobrante"));
                } else {
                        txtTotalSob.setText("0.0");
                }

                //con.close();

        } catch(SQLException ex){
                System.out.println("Error al mostrar total del sobrante "+ex.toString());
        }

        try {
                /*llenar detalle del cruce - tablas cargos, descargos y diferencia*/
                String nomCru = cboSob.getSelectedItem().toString();
                listaSobra = logicaCruce.ListaSobranteDetalle(nomCru);

                if(listaSobra.size() > 0) {
                    for(Sobrantes_Detalles detalle : listaSobra) {;

                        Object [][] datosCargos = {{
                            tblSob.getRowCount() +1,
                            detalle.getCodProd().getIdProducto(),
                            detalle.getCodProd().getDescripcion(),
                            detalle.getCodProd().getLaboratorio().getId_Lab(),
                            detalle.getUnidades()+"F"+detalle.getFracciones(),
                            detalle.getPrecio(),
                            detalle.getMonto(),
                            detalle.getPvp(),
                            detalle.getDescuento()

                        }};

                        if (tablaSobrantes == null) {
                            tablaSobrantes = new ModeloTabla() {

                                @Override
                                public boolean isCellEditable(int fila, int columna) {
                                    if (columna == 8) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            };

                            tblSob.setModel(tablaSobrantes);
                        } else {
                            tablaSobrantes.addRow(datosCargos[0]);
                        }

                    }
                }

        } catch (Exception e) {
            System.out.println("Error al listar el detalle del sobrante "+e);
        }

    }

    public void limpiarTablaSob() {
        txtObs.setText("");
        txtNumeroDocumento.setText("");
        obtenerUltimoSobrante();
        txtTotalSob.setText("0.0");
        tablaSobrantes=(ModeloTabla)tblSob.getModel();
        tablaSobrantes.setRowCount(0);
    }

    private void limpiaCamposSob() {
        cboSob.removeAllItems();
        cboSob.addItem("Seleccione Sobrante");
        cargaDatosComboSob();
        panelProdSob.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                "CARGOS X SOBRANTES - "+recuperaProximoIdCargoXsobrante()
                , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14)));

    }

    private void grabarSobrante() {

        if (tblSob.getRowCount() > 0) {

            Integer enterito = 0;
            Integer fraccionado = 0;
            Integer tp = 0;
            boolean continua = true;
            Integer bandera = 0;

                /*Cargando Cabecera*/
                Guias entidadGuia = new Guias();
                Producto entidadProductos = new Producto();

                String operacion = "CARGO X SOBRANTE";

                if (continua) {

                    String docu = recuperaProximoIdCargoXsobrante();

                    double valTotal=0;

                    for(int pCar=0;pCar<tblSob.getRowCount();pCar++) {
                        double val = Double.parseDouble(tablaSobrantes.getValueAt(pCar, 6).toString());
                        valTotal = valTotal+val;
                    }

                    entidadGuia = new Guias(
                            idboticaCargos,
                            "Mostrador",
                            docu,
                            "ZZZZ",
                            logicaGenerico.ConvierteFecha(fchaSob.getDate()),
                            OpcionesMenu.getIdpersonal_botica(),
                            logicaGenerico.ConvierteFecha(fchaSob.getDate()),
                            operacion, valTotal);

                    entidadGuia.setNumero(recuperaProximoIdCargoXsobrante());
                    Confirmar p = new Confirmar(objetoventana, "DESEA GUARDAR EL CARGO X SOBRANTE");
                    p.show(true);

                    if (p.getConfirmar() == 1) {

                        if (objGuias.guiaCargada(entidadGuia, tp, 1) == 0) {

                            /*********** CARGAR DETALLE CARGO *****************/
                            Movimiento_Detalle detalle;
                            Producto producto;
                            List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
                            array.removeAll(array);

                            for (Integer totalProductos = 0; totalProductos < tablaSobrantes.getRowCount(); totalProductos++) {
                                detalle = new Movimiento_Detalle();
                                producto = new Producto();
                                bandera = 0;
                                fraccionado = 0;
                                enterito = 0;
                                enterito = logicaGenerico.RecuperaEntero(tablaSobrantes.getValueAt(totalProductos, 4).toString());
                                fraccionado = logicaGenerico.RecuperaFraccion(tablaSobrantes.getValueAt(totalProductos, 4).toString());

                                double pv = Double.parseDouble(tablaSobrantes.getValueAt(totalProductos, 7).toString());
                                double dcto = Double.parseDouble(tablaSobrantes.getValueAt(totalProductos, 8).toString());
                                double pvf = pv - ((pv * dcto) / 100);
                                double total = Double.parseDouble(tablaSobrantes.getValueAt(totalProductos, 6).toString());

                                producto.setIdProducto(tablaSobrantes.getValueAt(totalProductos, 1).toString());
                                detalle.setId_Producto(producto);
                                detalle.setUnidad(enterito);
                                detalle.setFraccion(fraccionado);
                                detalle.setPrecio_Venta(pv);
                                detalle.setPrecio_Venta_Final(pvf);
                                detalle.setDescuento(dcto);
                                detalle.setTotal(total);
                                array.add(detalle);

                            }

                            Movimientos ObjMovSob = objGuias.CargarDetalleGuia(entidadGuia, array, tp, 1,txtObs.getText(), tp,"" );

                        } else {
                            JOptionPane.showMessageDialog(null, "Este Numero de Guia ya ha sido Cargado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "2. Existen Datos incompletos, Porfavor Verificar!", "Error", JOptionPane.ERROR_MESSAGE);
            }

    }


    private String recuperaProximoIdCargoXsobrante() {
        String ultimoCargoXSobrante = "";
        ultimoCargoXSobrante = objGuias.GeneraNuevoNumero("CARGO X SOBRANTE", "Mostrador");

        return ultimoCargoXSobrante;
    }

    private void imprimeSobrante() {
        String sistema = "Windows";

        try {
            //con = logconex.RetornaConexion();
            parameters.put("vSobra", txtNumeroDocumento.getText());
            parameters.put("idPersonal", idPersonal);
            parameters.put("idBotica", idboticaCargos);

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Sobrante.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE SOBRANTES");
            view.setVisible(true);
            view.setSize(850, 850);

        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
        

    }

    private void verificaCantidadSobrante() {
        int fila = this.tblSob.getSelectedRow();
        if (fila >= 0) {

            JOptionPane p = new JOptionPane();
            String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

            try {

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            cantidad1 = cantidad1.toUpperCase();

                            String idproducto = this.tblSob.getValueAt(fila, 1).toString().trim();
                            int empaque1 = objProductoSob.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidadSob(cantidad1);

                            if (unidadSob == 0 && fraccionSob == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccionSob > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (resul) {

                                        tblSob.setValueAt(cantidad1, fila, 4);
                                        calculoMontosSob();

                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
            }

        }
    }

    private boolean VerificaCantidadSob(String cantidad) {
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


        unidadSob = Integer.valueOf(unidad1);
        fraccionSob = Integer.valueOf(fraccion1);
        return true;

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
        //jTable2.getColumnModel().getColumn(9).setWidth(0);

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

    private void AsignaPrecio(ProductosPrecios precios) {
        int indfila = cantidadproductos - 1;
        double parcial1 = 0;
        String estadopromo = "0";
        int empaque1 = Integer.parseInt(empaque.toString());
        listadetalle = null;
        Object[] listadetalle = new Object[12];
        if (precbotiquin.compareTo("02") == 0 && fraccion > 0) {
            JOptionPane.showMessageDialog(this, "LO SENTIMOS LOS PRECIOS DE  BOTIQUIN \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (empaque1 == 0 && fraccion > 0) {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                try {
                    String nomproducto = precios.getProductoBotica().getProducto().getDescripcion();
                    tipoprecio = precios.getTipoPrecio().getId_Tipo_Precio();
                    double pv = precios.getPrecio_Venta();
                    listaempaque.add(empaque1);
                    String idproducto = precios.getProductoBotica().getProducto().getIdProducto();
                    Double descuento2 = 0.00;
                    Integer recuperaUnidadesminimo = 0;
                    int orden = 0;
                    int cantidad = 0;
                    ProductosPromociones obj = null;

                    if (objproductos.isEspromo()) {  //pregunta si el campo promocion de la tabla esta lleno
                        estadopromo = "1";
                        cantidad = objProducto.Verifica_Promocion(idproducto); //verifica si existe en la tabla promociones
                        List<ListaDetalles> listPromocion = mantProduc.verificaPromocion(idproducto,0);//recupero los datos de la promo
                        if (cantidad > 1) {
                            obj = new ProductosPromociones(objetoventana, nomproducto, idproducto);
                            obj.pack();
                            obj.setVisible(true);
                            descuento2 = obj.getDescuento();
                            recuperaUnidadesminimo = 1;
                        } else {
                            orden = listPromocion.get(0).getOrden();
                            descuento2 = armarPromocion(listPromocion, idproducto, unidad); // RECUPERA CAMPO DESCUENTO
                            recuperaUnidadesminimo = retornaUnidades(listPromocion, unidad); // RECUPERA CAMPO COUNT ID_PROMO
                        }
                    }
                    listadetalle[4] = unidad;
                    //System.out.println("COTIZACION - La cantidad es: "+objProducto.Verifica_Cantidad_Producto(idproducto));
                    if(objProducto.Verifica_Es_Manual(idproducto) == 1) {
                        
                        int p = JOptionPane.showConfirmDialog(null, "¿VERIFICÓ LOS DATOS DE LA RECETA Y DNI DEL CLIENTE?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (p == JOptionPane.YES_OPTION ) {                        
                        
                            if(unidad <= objProducto.Verifica_Cantidad_Producto(idproducto)) {
                                listadetalle[0] = cantidadproductos;
                                listadetalle[1] = idproducto;
                                listadetalle[2] = nomproducto;
                                listadetalle[3] = precios.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                                listadetalle[4] = unidad;
                                listadetalle[5] = fraccion;

                                if(unidad == 0 && fraccion > 0) {
                                    BigDecimal bd = new BigDecimal(pv);
                                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    pv = bd.doubleValue();

                                    listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                                    PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                    parcial = PVPx * unidad;

                                    if (empaque1 > 0) {
                                        parcial1 = (fraccion * PVPx) / empaque1;
                                    }

                                    parcial = parcial + parcial1;

                                    BigDecimal bd2 = new BigDecimal(PVPx);
                                    bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    PVPx = bd2.doubleValue();

                                    BigDecimal bd3 = new BigDecimal(parcial);
                                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                    parcial = bd3.doubleValue();

                                    listadetalle[7] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[8] = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                    listadetalle[9] = "0";
                                    listadetalle[10] = estadopromo;
                                    listadetalle[11] = "0";

                                    tablaproforma.addRow(listadetalle);
                                    double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                                    CalculaMontos(igvaux, indfila);

                                    if (jComboBox3.getItemCount() > 0) {
                                        AsignaCredito();
                                    }
                                    listaProductosVerifica.add(listadetalle);
                                        listadetalle = new Object[12];
                                        listadetalle[0] = jTable2.getRowCount() + 1;
                                        if (cantidad > 1) {
                                            listadetalle[1] = obj.getCodPromocion();
                                            listadetalle[2] = obj.getDescripPromocion();
                                        } else {
                                            listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto);
                                            listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                        }

                                        listadetalle[4] = recuperaUnidadesminimo;
                                        listadetalle[5] = 0;                                        

                                        CalculaMontos(igvaux, indfila + 1);
                                        listaProductosVerifica.add(listadetalle);

                                    RcepueraTipoPago();
                                    jTable2.requestFocus();
                                    jTable2.changeSelection(indfila, 7, false, true);

                                    if (igvaux < 1) {
                                        lisProdSinIGV.add(idproducto);
                                        col = jTable2.getColumnModel().getColumn(0);
                                        col.setCellRenderer(new ColoredTableCellRenderer());
                                    }
                                }else{

                              BigDecimal bd = new BigDecimal(pv);
                                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                pv = bd.doubleValue();

                                listadetalle[6] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                                double desc = precios.getDescuento_Venta();
                                BigDecimal bd1 = new BigDecimal(desc);
                                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                desc = bd1.doubleValue();

                                PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                                parcial = PVPx * unidad;

                                Double ss = Double.valueOf(descuento2);
                                descuento = ss;
                                Double montoDsctofraccion = 0.00;
                                Double montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                                if (empaque1 > 0) {
                                    parcial1 = (fraccion * PVPx) / empaque1;
                                }
                                parcial = parcial + parcial1;

                                /********************************************
                                CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                 *********************************************/
                                BigDecimal bd2 = new BigDecimal(PVPx);
                                bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                PVPx = bd2.doubleValue();

                                BigDecimal bd3 = new BigDecimal(parcial);
                                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                parcial = bd3.doubleValue();

                                listadetalle[7] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[8] = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[9] = "0";
                                listadetalle[10] = estadopromo;
                                listadetalle[11] = "0";

                                tablaproforma.addRow(listadetalle);
                                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                                CalculaMontos(igvaux, indfila);

                                if (jComboBox3.getItemCount() > 0) {
                                    AsignaCredito();
                                }
                                listaProductosVerifica.add(listadetalle);
                                if (ss > 0.00) {
                                    listadetalle = new Object[12];
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    if (cantidad > 1) {
                                        listadetalle[1] = obj.getCodPromocion();
                                        listadetalle[2] = obj.getDescripPromocion();
                                    } else {
                                        listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto);
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
                                    listadetalle[9] = "1";
                                    listadetalle[10] = "1";
                                    listadetalle[11] = "0";
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
                            }else {
                                JOptionPane.showMessageDialog(this, "CANTIDAD PEDIDA ES MAYOR A "+objProducto.Verifica_Cantidad_Producto(idproducto)+" UNIDADES\nNO SE AGREGARA EL DESCUENTO A ESTE PRODUCTO", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

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

                                /********************************************
                                CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                                 *********************************************/
                                BigDecimal bd2 = new BigDecimal(PVPx);
                                bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                PVPx = bd2.doubleValue();

                                BigDecimal bd3 = new BigDecimal(parcial);
                                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                parcial = bd3.doubleValue();

                                listadetalle[7] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[8] = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                listadetalle[9] = "0";
                                listadetalle[10] = estadopromo;
                                listadetalle[11] = "0";

                                tablaproforma.addRow(listadetalle);
                                double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                                CalculaMontos(igvaux, indfila);

                                if (jComboBox3.getItemCount() > 0) {
                                    AsignaCredito();
                                }
                                listaProductosVerifica.add(listadetalle);
    //                            if (ss > 0.00) {
                                    listadetalle = new Object[12];
                                    listadetalle[0] = jTable2.getRowCount() + 1;
                                    if (cantidad > 1) {
                                        listadetalle[1] = obj.getCodPromocion();
                                        listadetalle[2] = obj.getDescripPromocion();
                                    } else {
                                        listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto);
                                        listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                    }

                                    listadetalle[4] = recuperaUnidadesminimo;
                                    listadetalle[5] = 0;

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

                            System.out.println("La cantidad de productos real es: "+cantidadproductos+",unidades: "+unidad+" y fraccion: "+fraccion);

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

                            /********************************************
                            CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                             *********************************************/
                            BigDecimal bd2 = new BigDecimal(PVPx);
                            bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            PVPx = bd2.doubleValue();

                            BigDecimal bd3 = new BigDecimal(parcial);
                            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            parcial = bd3.doubleValue();

                            listadetalle[7] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[8] = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                            listadetalle[9] = "0";
                            listadetalle[10] = estadopromo;
                            listadetalle[11] = "0";

                            tablaproforma.addRow(listadetalle);
                            double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                            CalculaMontos(igvaux, indfila);

                            if (jComboBox3.getItemCount() > 0) {
                                AsignaCredito();
                            }
                            listaProductosVerifica.add(listadetalle);
//                            if (ss > 0.00) {
                                listadetalle = new Object[12];
                                listadetalle[0] = jTable2.getRowCount() + 1;
                                if (cantidad > 1) {
                                    listadetalle[1] = obj.getCodPromocion();
                                    listadetalle[2] = obj.getDescripPromocion();
                                } else {
                                    listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto);
                                    listadetalle[2] = mantProduc.Recupera_Promo_Nombre(idproducto);
                                }

                                listadetalle[4] = recuperaUnidadesminimo;
                                listadetalle[5] = 0;

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
                    }else{
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

                        double desc = precios.getDescuento_Venta();
                        BigDecimal bd1 = new BigDecimal(desc);
                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        desc = bd1.doubleValue();

                        PVPx = Double.parseDouble(String.valueOf(precios.getPVPX()));
                        parcial = PVPx * unidad;

                        Double ss = Double.valueOf(descuento2);
                        descuento = ss;
                        Double montoDsctofraccion = 0.00;
                        Double montoDscto = (PVPx * (descuento)) / 100 * Double.valueOf(recuperaUnidadesminimo);

                        if (empaque1 > 0) {
                            parcial1 = (fraccion * PVPx) / empaque1;
                        }

                        parcial = parcial + parcial1;

                        /********************************************
                        CONVERTIR DOUBLE A DOS POSICIONES DECIMALES
                         *********************************************/
                        BigDecimal bd2 = new BigDecimal(PVPx);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        PVPx = bd2.doubleValue();

                        BigDecimal bd3 = new BigDecimal(parcial);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        parcial = bd3.doubleValue();

                        listadetalle[7] = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                        listadetalle[8] = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                        listadetalle[9] = "0";
                        listadetalle[10] = estadopromo;
                        listadetalle[11] = "0";

                        tablaproforma.addRow(listadetalle);
                        double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                        CalculaMontos(igvaux, indfila);

                        if (jComboBox3.getItemCount() > 0) {
                            AsignaCredito();
                        }
                        listaProductosVerifica.add(listadetalle);

                        // AQUI AGREGO LA FILA DE PROMOCION AL JTABLE / SI EL PRODUCTO TUVIERA --- GINO

                        if (ss > 0.00) {
                        
                            String PromoDescripcion = "";
                            //double monto = Double.parseDouble(this.jTextField7.getText().trim());

                            String[] recuperacadenaPromo = nomproducto.split(" ");
                            PromoDescripcion = String.valueOf(recuperacadenaPromo[0]);  //recupera el nombre de la promo

                            String validar = mantProduc.Recupera_Promo_CodigoPrecio().toString().trim();
                            String[] recuperacadena = validar.split("@");
                            Double compara = Double.parseDouble(recuperacadena[1]); //monto a comparar

                                listadetalle = new Object[12];
                                listadetalle[0] = jTable2.getRowCount() + 1;
                                if (cantidad > 1) {
                                    listadetalle[1] = obj.getCodPromocion();
                                    listadetalle[2] = obj.getDescripPromocion();
                                } else {
                                    listadetalle[1] = mantProduc.Recupera_Promo_Codigo(idproducto);
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
                                listadetalle[9] = "1";
                                listadetalle[10] = "1";
                                listadetalle[11] = "0";
                                if (unidad > 0 && orden == 1) {
                                } else {
                                    tablaproforma.addRow(listadetalle);
                                    tablaproforma.setValueAt(1, indfila + 1, 4);
                                    listaempaque.add(empaque1);
                                }

                                CalculaMontos(igvaux, indfila + 1);
                                listaProductosVerifica.add(listadetalle);

                            //}

                        }  // FIN DE AGREGAR LA PROMO0


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
    }

    public void AgregaProforma(String cantidad, ProductosPrecios precios) {

        boolean resul1 = VerificaCantidad(cantidad);
        if (unidad == 0 && fraccion == 0) {
            JOptionPane msg = new JOptionPane();
            msg.showMessageDialog(this, "PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resul1 == true) {
                BuscaTipoPrecio(precios);
            } else {
                JOptionPane.showMessageDialog(this, "PORFAVOR INGRESE DATOS CORRECTOS", "Error", JOptionPane.ERROR_MESSAGE);
                BusquedaProducto();
            }
        }
    }

    private void BuscaTipoPrecio(ProductosPrecios precios) {
        if (VerificaStock(precios.getProductoBotica().getProducto().getIdProducto())) {
            AsignaPrecio(precios);
        } else {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY STOCK PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
            BusquedaProducto();
        }
//        jButton3.setEnabled(true);
//        jButton5.setEnabled(true);
    }

    /*******************************************
    METODO QUE CALCULA EL MONTO TOTAL  A PAGAR
     ********************************************/
    private void CalculaMontos(double band_igv, int fila) {
        double auxparcial = 0.0;
        double valortabla = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));

        if (band_igv == 0) {
            total += valortabla;
            SubTotal += valortabla;
            listsubtotales.add(fila, valortabla);
        } else {
            if (IGV == 0) {
                CapturaIGV();
            }
            total += valortabla;
            auxparcial = (valortabla / (1 + (IGV / 100)));
            BigDecimal bd1 = new BigDecimal(auxparcial);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            auxparcial = bd1.doubleValue();
            listsubtotales.add(fila, auxparcial);
            SubTotal += auxparcial;
        }


        BigDecimal bd1 = new BigDecimal(SubTotal);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        SubTotal = bd1.doubleValue();

        BigDecimal bd2 = new BigDecimal(total);
        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        total = bd2.doubleValue();

        cantidadproductos++;

        jTextField7.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField9.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        double miigv = total - SubTotal;

        BigDecimal bd3 = new BigDecimal(miigv);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        miigv = bd3.doubleValue();

        jTextField16.setText(bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
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

        jTextField7.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField9.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        jTextField16.setText(bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

    }

    /*
     * METODO QUE RECUPERA DE LA BD EL VALOR DEL IGV
     */
    private void CapturaIGV() {
        IGV = mantProduc.Captura_IGV();
    }

    public void Guardar() {
        
        String nomcliente = "";
        String dni = "";
        String direccion = "";
        String dato = "";
        double Tot;
        String texto = "";
        int Total = 0;
        String tipoPago = "";
        int canti1 = 0;
        int canti2 = 0;
        String cantidad1 = "";
        String cantidad2 = "";
        String cantidad3 = "";
        String valueCompara = "";
        double igvventa = 0.0;

        listdetalleProforma.removeAll(listdetalleProforma);

        try {

            FechaRegistro = fechaVenta;
            idcliente = Integer.parseInt(this.jTextField2.getText().trim());
            nomcliente = jTextField14.getText().trim().toUpperCase();
            dni = jTextField1.getText().trim();
            direccion = String.valueOf(this.jComboBox4.getSelectedItem());
            igvventa = Double.parseDouble(this.jTextField16.getText());
            double LeerRedondeo = 0.0;
            double LeerTotFinal = 0.0;

            if (direccion == null) {
                direccion = "";
            }

            RcepueraTipoPago();
            IdPersonalBoticaVenta = Integer.parseInt(this.jTextField12.getText().trim());
            entidadproforma = new Proforma(id_botica, idcliente, idtipopago, tipoprecio, idtipoventa, idmedico, fechaVenta, SubTotal, IGV, total,OpGravada,OpExonerada, OpInafecta, OpGratuita,OpISC, FechaRegistro, IdPersonalBoticaVenta, nomcliente, dni, direccion, ruc, colegiatura,igvventa,LeerRedondeo,LeerTotFinal,"");


            int posorden = 1;
            for (int i=0; i< listaProductosVerifica.size() ; i++)
            {
                Object[] valor = new Object[1];
                valor[0] = listaProductosVerifica.get(i);

                Object[] datos = new Object[12];
                datos = (Object[]) valor[0];
                if ( Integer.parseInt(datos[10].toString()) == 1 )
                {
                    datos[11] = posorden;
                    listaProductosVerifica.set(i, datos);
                    if ( Integer.parseInt(datos[9].toString()) == 1 )
                        posorden = posorden + 1;
                }
                else{
                    datos[11] = 0;
                    listaProductosVerifica.set(i, datos);
                    
            }
            }

            String PROFOR = jLabel20.getText();
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

            for (int i = 0; i < jTable2.getRowCount(); i++) {

                  Object[] valor = new Object[1];
                  valor[0] = listaProductosVerifica.get(i);

                  Object[] datos = new Object[12];
                  datos = (Object[]) valor[0];

                  String orden = datos[11].toString();
                  int idOperadorRec =0;

                String idproducto = String.valueOf(this.jTable2.getValueAt(i, 1));
                int unida = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 4)));
                int fracc = Integer.parseInt(String.valueOf(this.jTable2.getValueAt(i, 5)));
                double PrecioVen = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 6)));
                double descu = objProducto.Recupera_Desct_Producto(idproducto, id_botica, tipoprecio,"");
                double pvx = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 7)));
                Tot = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
                listdetalleProforma.add(new Proforma(idproducto, unida, fracc, PrecioVen, descu, pvx, Tot, orden,idOperadorRec,esgratuita,TotEsGratuita,esgratuitaPromo,OpGravada, OpExonerada, OpInafecta, OpGratuita,OpISC));
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


            if (listdetalleProforma.size() > 0) {
                int cantidaddoc = 0;
                if (recTipDoc.equals("1")) {
                    cantidaddoc = 6;
                } else if (recTipDoc.equals("2")) {
                    cantidaddoc = 12;
                }
                
                //Collections.sort(listdetalleProforma);
                //Collections.sort(listaProductosVerifica, new ProformaComparatorByOrdenTotal());

                {
                ordenproducto = 1;
                listaProductosVerifica.clear();
                resultado = onjproforma.ProformaRealizada(entidadproforma, listdetalleProforma,"1",0);

                if (listdetalleProforma.size() > 0 && resultado.compareTo("") != 0) {

                int result = onjproforma.insertaValidarProformaRealizada(resultado);
                new ProformaGenerada(resultado, this, objetoventana,jTextField7.getText()).show(true);
                imprimirProforma();
                LimpiaTodo();
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
            jTextField4.setText("");
            SubTotal = 0.0;
            total = 0.0;
            cantidadproductos = 1;
            colegiatura = "";
            IGV = 0;
            listdetalleProforma.removeAll(listdetalleProforma);
            listsubtotales.removeAll(listsubtotales);
            lisProdSinIGV.removeAll(lisProdSinIGV);
            nuevaPromocion.removeAll(nuevaPromocion);
            cantidadElementos.removeAll(cantidadElementos);
            DetallePromo.removeAll(DetallePromo);
            ListDescuentos.removeAll(ListDescuentos);
            jTextField10.setText("");
            jTextField16.setText("");
            jTextField1.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            Cliente_Comun();
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
            jComboBox4.removeAllItems();
//            jButton3.setEnabled(false);
//            jButton5.setEnabled(false);
            msgSaldo.setText("");
            jLabel19.setVisible(false);
            jLabel20.setVisible(false);
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox3.removeAllItems();
            jTable2.requestFocus();
            jLabel20.setText("");

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

    /**********************************************
     * METODO QUE ELIMINA UN PRODUCTOS DE MI JTABLE
     **********************************************/
    private void EliminaProducto(int fila) {
        int filas = tablaproforma.getRowCount();
        int cantidadValue = 0;
        if (filas > 0) {
            try {
                if (fila >= 0) {
                    Confirmar pe = new Confirmar(objetoventana, "<html> Desea quitar el Producto : " + tablaproforma.getValueAt(fila, 2) + "  ? </html>");
                    pe.show(true);
                    //String idproducto= String.valueOf(tablaproforma.getValueAt(fila, 1));
                    if (pe.getConfirmar() == 1) {
                        //cantidadValue = objProducto.Verifica_Promocion(idproducto);
                        //JOptionPane.showMessageDialog(this, cantidadValue, "Error", JOptionPane.ERROR_MESSAGE);

                        /*if (cantidadValue > 0) {
                        JOptionPane.showMessageDialog(this, cantidadValue+ " es promo cuidado ", "Error", JOptionPane.ERROR_MESSAGE);

                        }else{*/
                        listaProductosVerifica.remove(fila);
                        Double valor = Double.parseDouble(String.valueOf(tablaproforma.getValueAt(fila, 8)));
                        total = total - valor;
                        Double auxsubtotal = Double.parseDouble(String.valueOf(listsubtotales.get(fila)));
                        SubTotal = SubTotal - auxsubtotal;

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
                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        SubTotal = bd1.doubleValue();

                        BigDecimal bd2 = new BigDecimal(total);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        total = bd2.doubleValue();

                        jTextField7.setText(bd2.setScale(podecimal).toPlainString());
                        jTextField9.setText(bd1.setScale(podecimal).toPlainString());

                        col = jTable2.getColumnModel().getColumn(0);
                        col.setCellRenderer(new ColoredTableCellRenderer());
                        col = jTable2.getColumnModel().getColumn(1);
                        col.setCellRenderer(new ColoredTableCellRenderer());

                        //}
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
                IGV = 0.0;
                jTextField7.setText(String.valueOf(total));
                jTextField9.setText(String.valueOf(SubTotal));
                jTextField16.setText(String.valueOf(IGV));
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

    private void RcepueraTipoPago() {
        idtipopago = 9;
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
                int resul1 = objClientes.EsDescuento(9);
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
                ruc = jTextField10.getText().trim();

                if (idtipoventa == 2) {
                    resulta = objfactura.Verifica_Datos_Factura(idtipoventa, ruc, jTextField14.getText().trim(), jComboBox4.getSelectedItem().toString().trim());
                }

                if (resulta != null) {
                    JOptionPane.showMessageDialog(this, resulta.substring(1, resulta.length()), "Error", JOptionPane.ERROR_MESSAGE);

                    if (Integer.parseInt(resulta.substring(0, 1)) == 1) {
                        jTextField10.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 2) {
                        jTextField14.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 3) {
                        jComboBox4.requestFocus();
                    }
                } else {

                    boolean flag = objfactura.Verifica_Datos_Boleta(idtipoventa, total, jTextField1.getText().trim(), posicion, jTextField14.getText().trim(), jComboBox4.getSelectedItem());

                    if (!flag) {
                        JOptionPane.showMessageDialog(this, "PORFAVOR PARA ESTE MONTO DE VENTA SE REQUIERE INGRESAR : \n NOMBRE DEL CLIENTE  \n NUMERO DE DNI  \n DIRECCION DEL CLIENTE ", "Error", JOptionPane.ERROR_MESSAGE);
                        jTextField1.requestFocus();
                        HabilitaBotones(true);
                        HabilitaColor();
                    } else {
                        if (jTextField2.getText().trim().compareTo("") != 0) {
                            new Medicos(objetoventana).setVisible(true);
                            if (Medicos.getId_Medico() != null) {
                                idmedico = Medicos.getId_Medico();
                                colegiatura = Medicos.getColegiatura();
                                GuardaProforma();
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
       // listatipospagos = objfactura.Recupera_Tipos_Pagos(0);
        //for (int i = 0; i < listatipospagos.size(); i++) {
            //String descrp = listatipospagos.get(i).getDescripcion();
            jComboBox1.addItem("Descuento al Personal");
       // }
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
//
//    private void LimpiarDatos() {
//        jTextField2.setText("");
//        jTextField10.setText("");
//        jTextField14.setText("");
//        jComboBox4.removeAllItems();
//        jTextField1.setText("");
//        jTextField4.setText("");
//    }
//
//    public void AsignaDatosCliente() {
//        try {
//            LimpiarDatos();
//            jTextField2.setText(objcliente.getCodigoCliente());
//            jTextField10.setText(objcliente.getRUC_DNI());
//            jTextField14.setText(objcliente.getNombre_RazonSocial());
//            jTextField1.setText(objcliente.getDNI());
//            jTextField4.setText(objcliente.getTelefono());
//            jComboBox3.setEnabled(true);
//            jTextField14.requestFocus();
//            Recupera_Direcciones();
//        } catch (Exception ex) {
//            System.out.println("Error al recuperrar cliente:" + ex.getMessage());
//        }
//    }
//
//    private void Recupera_Direcciones() {
//        jComboBox4.removeAllItems();
//        List<Clientes> midirecciones = objClientes.Lista_Direcciones(id_botica, Integer.parseInt(objcliente.getCodigoCliente()), objcliente.getEmpresa());
//        for (int i = 0; i < midirecciones.size(); i++) {
//            jComboBox4.addItem(midirecciones.get(i).getDireccion());
//        }
//    }

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

    /*************************************
     * METODO QUE MODIFICA LA CANTIDAD PEDIDAD
     * @param fila
     ***************************************/
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
            Productos_Botica productoBotica = new Productos_Botica();
            productoBotica.setProducto(miproducto);
            ProductosPrecios precios = new ProductosPrecios();
            precios.setPVPX(Double.parseDouble(jTable2.getValueAt(fila, 7).toString().trim()));
            precios.setProductoBotica(productoBotica);
            int empaque2 = mantProduc.Recupera_Empaque(codpro);
            precios.getProductoBotica().getProducto().setEmpaque(empaque2);
            new ProductoPedido(precios,2).setVisible(true);
            if (ProductoPedido.ingresadet) {
                String cantidad = ProductoPedido.getCantidad();
                if (cantidad.compareTo("") != 0) {
                    int empaque1 = mantProduc.Recupera_Empaque(codpro);

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
                                        PVP = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 6)));
                                        unidad = Integer.valueOf(String.valueOf(jTable2.getValueAt(fila, 4)));
                                        fraccion = Integer.valueOf(String.valueOf(jTable2.getValueAt(fila, 5)));
                                        PVPx = Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 7)));
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

                                        jTable2.setValueAt(bd.setScale(podecimal).toPlainString(), fila, 7);
                                        jTable2.setValueAt(bd1.setScale(podecimal).toPlainString(), fila, 8);

                                        Double val = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));
                                        ModificaMontos(anterior, val, String.valueOf(this.jTable2.getValueAt(fila, 1)), fila);

                                    }
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

    private Double armarPromocion(List<ListaDetalles> verificaPromocion, String CodProducto, Integer Cantidad) {
        Integer existePromocion = 0;
        Double Descuento = 0.00;
        //nuevaPromocion.clear();

        if (verificaPromocion.get(0).getIdPromo() > 0) {
            for (Integer g = 0; g < nuevaPromocion.size(); g++) {
                if (nuevaPromocion.get(g) == verificaPromocion.get(0).getIdPromo()) {
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

                Descuento = objProducto.Recupera_Dscto(CodProducto, 0,0,0);

            } else {
                //En caso exista empiezo a agregarlo a los elementos
                Integer t = 1;
                for (Integer con = 1; con < DetallePromo.size(); con++) {
                    if (DetallePromo.get(con).getIdPromo() == verificaPromocion.get(0).getIdPromo()) {
                        t++;
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

                    Descuento = objProducto.Recupera_Dscto(CodProducto, t - 1,0,0);
                    System.out.print("ss" + CodProducto);
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
        objproductos = new BuscarProductos(objetoVentana, "01", objInventarios);
        objproductos.setVisible(true);
        if (objproductos.seleccionaart) { //recupera dato de formulario BuscarProducto - RecuperaProducto()
            new ProductoPedido(objproductos.getProductoPrecio(),2).setVisible(true);
            if (ProductoPedido.ingresadet) {
                if (ProductoPedido.getCantidad().compareTo("") != 0) {
                    AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio());
                }
            }
        }
    }

    private void ListaProforma() {
        ListadoProformas pro = new ListadoProformas(objetoVentana, this);
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
//                jButton3.setEnabled(true);
//                jButton5.setEnabled(true);
                List<Proforma> listproformaDetalle = new ArrayList<Proforma>();

                if (listaproforma.get(fila).getId_Proforma().compareTo(idproforma) == 0) {
                    jTextField1.setText(listaproforma.get(fila).getDniaux());
                    jTextField10.setText(listaproforma.get(fila).getRUC());
                    jTextField2.setText(String.valueOf(listaproforma.get(fila).getId_Cliente()));
                    jTextField14.setText(listaproforma.get(fila).getNomCliente());
                    jTextField4.setText(listaproforma.get(fila).getTelefono());
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

//                    for (int k = 0; k < jComboBox1.getItemCount(); k++) {
//                        if (idtipopago == listatipospagos.get(k).getId_TipoPago()) {
                            jComboBox1.setSelectedIndex(0);
//                            k = jComboBox1.getItemCount();
//                        }
//                    }

                    jTextField7.setText(String.valueOf(listaproforma.get(fila).getTotal()));
                    jTextField9.setText(String.valueOf(listaproforma.get(fila).getSubTotal()));

                    int id1 = listaproforma.get(fila).getId_Tipo_Venta();

                    for (int w = 0; w < lis_tipo_venta.size(); w++) {
                        if (lis_tipo_venta.get(w).getId_Tipo_Venta() == id1) {
                            jComboBox2.setSelectedIndex(w);
                            break;
                        }
                    }

                    tipoprecio = listaproforma.get(fila).getId_Tipo_Precio();
                    jLabel20.setText(idproforma);
                    jLabel20.setVisible(true);
                }

                /*
                 * RECUPERO EL DETALLE DE LA PROFORMA
                 */

                LogicaProforma logicaProforma = new LogicaProforma();
                listproformaDetalle = logicaProforma.Recupera_Detalle_Proforma(idproforma, id_botica);
                for (int i = 0; i < listproformaDetalle.size(); i++) {
                     listadetalle = new Object[12];
                    String idprodu = listproformaDetalle.get(i).getIdproducto();
                    String descrip = listproformaDetalle.get(i).getDescipcionproducto();
                    int unidad1 = listproformaDetalle.get(i).getUNIDAD();
                    int fraccion1 = listproformaDetalle.get(i).getFRACCION();
                    double pvp = listproformaDetalle.get(i).getPrecio_Venta();
                    double pvx = listproformaDetalle.get(i).getPvx();
                    double total1 = listproformaDetalle.get(i).getTotal();
                    String orden = listproformaDetalle.get(i).getOrdenProducto();

                    listaempaque.add(listproformaDetalle.get(i).getEmpaque());
                    double igvaux = listproformaDetalle.get(i).getIGV_Exonerado();

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
                    ordenproducto = Integer.parseInt(orden);

                    tablaproforma.addRow(listadetalle);

                    if (igvaux == 0.0) {
                        lisProdSinIGV.add(idprodu);
                    }

                    CalculaMontos(igvaux, i);

                       listaProductosVerifica.add(listadetalle);

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
        jTextField14.setText(def.getNombre_RazonSocial());
        jTextField2.setText(String.valueOf(def.getId_Cliente()));
        jComboBox4.addItem(def.getDireccion());
    }

    private void RealizaOpciones(java.awt.event.KeyEvent evt) {

        try {

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {

                    String PROFOR = this.jLabel20.getText();
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
                
            } else {
                if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
                    ListaProforma();
                } else {
                    if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
                        if (jTable2.getRowCount() > 0) {
                            if (Verifica_Fecha()) {
                                GeneraProforma();
                            } else {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS LA HORA DEL SERVIDOR ES INCORRECTA \n PORFAVOR COMUNIQUESE CON INFORMATICA \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
//                            CambioUsuario();
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
                                            jTextField14.requestFocus();
                                        } else {
                                            new FormClientes(this, objetoventana, id_botica).show(true);
                                        }
                                    }
                                } else {
                                    if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
                                        NuevaProforma();

                                    } else {
                                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
//                                            NuevoUsuario();
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
        jTextField14.setBackground(Color.WHITE);
        jTextField4.setBackground(Color.WHITE);
        jTextField1.setBackground(Color.WHITE);
        jTextField10.setBackground(Color.WHITE);
    }

    private void HabilitaBotones(boolean valor) {
        jTextField14.setEnabled(valor);
        jComboBox4.setEnabled(valor);
        jTextField4.setEnabled(valor);
        jTextField1.setEnabled(valor);
        jTextField10.setEnabled(valor);
        jComboBox2.setEnabled(valor);
        jComboBox1.setEnabled(valor);
    }

    private void recuperaUsuario() {
        try {
            IdPersonalBoticaVenta = objpricipal.getIdpesonal();
            usuario = objpricipal.getUsuario();
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
            jTextField1.setText(objclient.getDNI());
            jComboBox4.addItem(objclient.getDireccion());
            jTextField14.setText(objclient.getNombre_RazonSocial());
            jTextField10.setText(objclient.getRUC_DNI());
            jTextField4.setText(objclient.getTelefono());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void MuestraClientes(NuevoCliente obj, int id_cliente) {
        jComboBox4.addItem(obj.getObjcliente().getDireccion());
        jTextField1.setText(obj.getObjcliente().getDNI());
        jTextField14.setText(obj.getObjcliente().getNombre_RazonSocial());
        jTextField10.setText(obj.getObjcliente().getRUC_DNI());
        jTextField4.setText(obj.getObjcliente().getTelefono());
        idcliente = id_cliente;
        jTextField2.setText(String.valueOf(idcliente));
        HabilitaBotones(true);
        HabilitaColor();
        jTextField14.requestFocus();
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

    public void imprimirProforma() {
        String sistema = "Windows";

        try {
            //con = logconex.RetornaConexion();
            parameters.put("vIdProforma", resultado);
            parameters.put("idPersonal", idPersonal);
            parameters.put("idBotica", idboticaCargos);

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Proforma.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE SOBRANTES");
            view.setVisible(true);
            view.setSize(850, 850);

            ///con.close();
        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void imprimeParte() {
        String sistema = "Windows";

        try {
            //con = logconex.RetornaConexion();
            parameters.put("idCruce", txtNomCruce.getText());
            parameters.put("idBotica", idboticaCargos);

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_ParteInventario.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("PARTE DE INVENTARIO");
            view.setVisible(true);
            view.setSize(850, 850);

            ///con.close();
        } catch (JRException ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }

    }

}