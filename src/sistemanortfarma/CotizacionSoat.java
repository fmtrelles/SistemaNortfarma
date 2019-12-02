package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCajas;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaEmpresas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaListaClase;
import CapaLogica.LogicaListaTipos;
import CapaLogica.LogicaListaMarcas;
import CapaLogica.LogicaListaModelos;
import CapaLogica.LogicaTraeZonasSoat;
import CapaLogica.LogicaTraePreciosSoat;
import CapaLogica.LogicaTraeDepartamentos;
import CapaLogica.LogicaListaProvincias;
import CapaLogica.LogicaListaDistritos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entidad.Laboratorios;
import CapaLogica.LogicaPersonal;
import entidad.UsuarioBotica;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaClientes;
import java.awt.Component;
import entidad.ListaDetalles;
import entidad.ProductosPrecios;
import entidad.Producto;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import entidad.Cajas;
import entidad.Venta;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import entidad.Empresas;
import CapaLogica.LogicaProducto;
import javax.swing.JOptionPane;
import java.awt.Color;
import entidad.Proforma;
import entidad.TipoVenta;
import entidad.Clases;
import entidad.TiposPagos;
import entidad.Tipos;
import entidad.Marcas;
import entidad.Descuento;
import entidad.Modelos;
import entidad.PreciosSoat;
import entidad.ZonasSoat;
import entidad.Departamentos;
import CapaLogica.LogicaVenta;
import entidad.Provincias;
import entidad.Distritos;
import CapaLogica.LogicaProforma;
import java.util.Date;
import entidad.Personal;
import entidad.Clientes;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.KeyEvent;
import entidad.Productos_Botica;
import java.math.BigDecimal;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JFrame;
import java.util.Calendar;

/**
 *
 * @author gparedes
 */
public class CotizacionSoat extends javax.swing.JInternalFrame {

    //MuestraVentana objetoventana = new MuestraVentana();
    JFrame objetoventana;
    private DefaultTableModel tablaproforma;
    RequisitosFactura objfactura = new RequisitosFactura();
    LogicaConfiguracion objConfig = new LogicaConfiguracion();
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    LogicaPersonal lopersonal = new LogicaPersonal();
    LogicaCPersonal logicapersonal = new LogicaCPersonal();
    LogicaClientes objClientes = new LogicaClientes();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    List<Clientes> ListDescuentos = new ArrayList<Clientes>();
    List<Object> lisProdSinIGV = new ArrayList<Object>();
    List<TipoVenta> lis_tipo_venta = new ArrayList<TipoVenta>();
    LogicaVenta objguardaventa = new LogicaVenta();
    List<Object> listsubtotales = new ArrayList<Object>();
    List<Integer> listaempaque = new ArrayList<Integer>();
    List<ListaDetalles> DetallePromo = new ArrayList<ListaDetalles>();
    Object[] listadetalle = new Object[9];
    Mant_Productos mantProduc = new Mant_Productos();
    LogicaProducto objProducto = new LogicaProducto();
    private List<Proforma> listdetalleProforma = new ArrayList<Proforma>();
    /*Para las promociones*/
    List<Integer> nuevaPromocion = new ArrayList();
    List<Integer> cantidadElementos = new ArrayList();
    private String tipoprecio, idmedico, colegiatura, codprodespec, ruc = null, usuario;
    private int bandseleccion = 0, cantidadproductos = 1, podecimal = 2, unidad, fraccion, idcliente, idtipopago, idtipoventa;
    private double PVP, PVPx, descuento, parcial;
    private static String id_botica;
    private String precbotiquin = "01";
    TableColumn col2, col, colu_6;
    Descuento p;
    private Date fechaVenta, FechaRegistro, FechaInicio, FechaFin;
    FormClientes objcliente;
    AplicacionVentas objventas;
    AplicacionVentas objpricipal;
    AplicacionVentas objventa;
    Proforma entidadproforma;
    Venta obj;
    BuscarProductos objproductos = null;
    TableColumnModel colModel;
    int resul = -1;
    private double IGV = 0, SubTotal = 0.0, total = 0.0;
    private String idbotica = null;
    private Object stockempaque, empaque, stockfraccion;
    private static int IdPersonalBoticaVenta;
    LogicaProforma onjproforma = new LogicaProforma();

    List<Empresas> listEmpresas = new ArrayList<Empresas>();
    LogicaEmpresas objEmpresas = new LogicaEmpresas();    

    List<Clases> listClases = new ArrayList<Clases>();
    LogicaListaClase objClase = new LogicaListaClase();

    List<Tipos> listTipos = new ArrayList<Tipos>();
    LogicaListaTipos objTipos = new LogicaListaTipos();

    List<Marcas> listMarcas = new ArrayList<Marcas>();
    LogicaListaMarcas objMarcas = new LogicaListaMarcas();

    List<Modelos> listModelos = new ArrayList<Modelos>();
    LogicaListaModelos objModelos = new LogicaListaModelos();

    List<ZonasSoat> listZonasSoat = new ArrayList<ZonasSoat>();
    LogicaTraeZonasSoat objZonasSoat = new LogicaTraeZonasSoat();
    
    List<PreciosSoat> listPreciosSoat = new ArrayList<PreciosSoat>();
    LogicaTraePreciosSoat objPreciosSoat = new LogicaTraePreciosSoat();

    List<Departamentos> listDepartamentos = new ArrayList<Departamentos>();
    LogicaTraeDepartamentos objDepartamentos = new LogicaTraeDepartamentos();

    List<Provincias> listProvincias = new ArrayList<Provincias>();
    LogicaListaProvincias objProvincias = new LogicaListaProvincias();
    
    List<Distritos> listDistritos = new ArrayList<Distritos>();
    LogicaListaDistritos objDistritos = new LogicaListaDistritos();    

    public CotizacionSoat(AplicacionVentas obj) {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/tab_edit.png")));
        initComponents();
        //addKeyListener(this);
        objventas = obj;
        idbotica = objventas.getIdbotica();
        fechaVenta = objlogiccafecha.RetornaFecha();        
        id_botica = objpricipal.getIdbotica();
        jTextField11.setText(objpricipal.getUsuario());
        IdPersonalBoticaVenta = objpricipal.getId_personal_botica();
        setIdPersonalBoticaVenta(IdPersonalBoticaVenta);
        jTextField12.setText(String.valueOf(IdPersonalBoticaVenta));
        jTextField5.setDocument(new LimitadorLetras(jTextField5, 8));
        jTextField10.setDocument(new LimitadorLetras(jTextField10, 11));        

        Cliente_Comun();
        RecuperaTipoVentas();
        RecuperaTiposPagos();
        setResizable(false);
        AutoCompleteDecorator.decorate(jComboBox5);
        JTextComponent editor;
        editor = (JTextComponent) jComboBox5.getEditor().getEditorComponent();
        
        editor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //jTextField5.requestFocus();
                }
                RealizaOpciones(e);
            }
        });
        jLabel9.setText("");
        CargarClase();
        CargarMarca();
        CargarZonaSoat();
        // CargarDepartamento();
        
        jComboBox4.removeAllItems();
        jTextField8.setEnabled(false);
        jTextField10.setEnabled(false);
        jTextField14.requestFocus();
        jTextField14.setText("");

        
        
        /* AUMENTO A 1 AÑO LA FECHA */
        java.util.Calendar hoy = java.util.Calendar.getInstance();
        hoy.add(java.util.Calendar.YEAR, 1);
        hasta.setDate(hoy.getTime());


    }
    public static int getIdPersonalBoticaVenta() {
        return IdPersonalBoticaVenta;
    }
    public static void setIdPersonalBoticaVenta(int IdPersonalBoticaVenta) {
        CotizacionSoat.IdPersonalBoticaVenta = IdPersonalBoticaVenta;
    }
    private void RecuperaTipoVentas() {
        lis_tipo_venta = objfactura.Retorna_Tipos_VentasSOAT();
        for (int i = 0; i < lis_tipo_venta.size(); i++) {
            jComboBox7.addItem(lis_tipo_venta.get(i).getDESCRIPCION());
        }
    }
    public static String getId_botica() {
        return id_botica;
    }
    public static void setId_botica(String id_botica) {
        CotizacionSoat.id_botica = id_botica;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButton9 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox();
        lblcomision = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        desde = new org.jdesktop.swingx.JXDatePicker();
        jLabel25 = new javax.swing.JLabel();
        hasta = new org.jdesktop.swingx.JXDatePicker();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jComboBox12 = new javax.swing.JComboBox();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();

        jSplitPane1.setName("jSplitPane1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CotizacionSoat.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setMinimumSize(new java.awt.Dimension(70, 50));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(870, 600));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

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
        jToolBar1.add(jButton8);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

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
        jToolBar1.add(jButton4);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getActionMap(CotizacionSoat.class, this);
        jButton5.setAction(actionMap.get("AgregaCliente1")); // NOI18N
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
        jToolBar1.add(jButton5);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(41, 20));
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
        jToolBar1.add(jButton3);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

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
        jToolBar1.add(jButton6);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setToolTipText(resourceMap.getString("jButton7.toolTipText")); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(120, 22));
        jButton7.setRequestFocusEnabled(false);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        jToolBar1.add(jButton7);

        jPanel12.setBackground(resourceMap.getColor("jPanel12.background")); // NOI18N
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setName("jPanel12"); // NOI18N
        jPanel12.setOpaque(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(505, 160));
        jPanel12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel12KeyPressed(evt);
            }
        });

        jPanel8.setBackground(resourceMap.getColor("jPanel8.background")); // NOI18N
        jPanel8.setFocusable(false);
        jPanel8.setName("jPanel8"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel14.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
        );

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setFocusable(false);
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField4.setEnabled(false);
        jTextField4.setFocusable(false);
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setFocusable(false);
        jLabel16.setName("jLabel16"); // NOI18N

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

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setFocusable(false);
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setFocusable(false);
        jLabel18.setName("jLabel18"); // NOI18N

        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(100, 20));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setFocusable(false);
        jLabel19.setName("jLabel19"); // NOI18N

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

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setFocusable(false);
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.setPreferredSize(new java.awt.Dimension(60, 20));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jComboBox5.setEditable(true);
        jComboBox5.setForeground(resourceMap.getColor("jComboBox5.foreground")); // NOI18N
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel18)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jComboBox6.setForeground(resourceMap.getColor("jComboBox6.foreground")); // NOI18N
        jComboBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jComboBox6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox6FocusLost(evt);
            }
        });
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox6KeyPressed(evt);
            }
        });

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N

        jComboBox7.setForeground(resourceMap.getColor("jComboBox7.foreground")); // NOI18N
        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.setPreferredSize(new java.awt.Dimension(20, 20));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jComboBox7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox7FocusLost(evt);
            }
        });
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox7KeyPressed(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setFocusable(false);
        jLabel11.setName("jLabel11"); // NOI18N

        jComboBox8.setBackground(resourceMap.getColor("jComboBox8.background")); // NOI18N
        jComboBox8.setForeground(resourceMap.getColor("jComboBox8.foreground")); // NOI18N
        jComboBox8.setEnabled(false);
        jComboBox8.setName("jComboBox8"); // NOI18N
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        jComboBox8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox8KeyPressed(evt);
            }
        });

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setFocusable(false);
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addContainerGap(537, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12)
        );

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setFocusable(false);
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(315, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setName("jPanel7"); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setFocusable(false);
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(959, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8)
        );

        jLabel8.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel8.AccessibleContext.accessibleName")); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setName("jComboBox2"); // NOI18N
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
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setName("jComboBox1"); // NOI18N
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

        jLabel4.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jComboBox4.setForeground(resourceMap.getColor("jComboBox4.foreground")); // NOI18N
        jComboBox4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox4.setMaximumSize(new java.awt.Dimension(100, 100));
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jComboBox4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox4FocusLost(evt);
            }
        });
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

        jComboBox3.setForeground(resourceMap.getColor("jComboBox3.foreground")); // NOI18N
        jComboBox3.setMaximumSize(new java.awt.Dimension(100, 100));
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
        jComboBox3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox3FocusLost(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox3KeyReleased(evt);
            }
        });

        jLabel7.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox14.setForeground(resourceMap.getColor("jComboBox14.foreground")); // NOI18N
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBox14.setName("jComboBox14"); // NOI18N
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });
        jComboBox14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox14FocusLost(evt);
            }
        });
        jComboBox14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox14KeyPressed(evt);
            }
        });

        jLabel30.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jComboBox15.setForeground(resourceMap.getColor("jComboBox15.foreground")); // NOI18N
        jComboBox15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox15.setMaximumSize(new java.awt.Dimension(100, 100));
        jComboBox15.setName("jComboBox15"); // NOI18N
        jComboBox15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox15ItemStateChanged(evt);
            }
        });
        jComboBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox15ActionPerformed(evt);
            }
        });
        jComboBox15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox15FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox15FocusLost(evt);
            }
        });
        jComboBox15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox15KeyPressed(evt);
            }
        });

        lblcomision.setText(resourceMap.getString("lblcomision.text")); // NOI18N
        lblcomision.setName("lblcomision"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, 0, 188, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, 210, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox15, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(34, 34, 34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcomision, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcomision, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addContainerGap())
        );

        jLabel6.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel6.AccessibleContext.accessibleName")); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setForeground(resourceMap.getColor("jLabel21.foreground")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setFocusable(false);
        jLabel21.setName("jLabel21"); // NOI18N

        jTextField12.setBackground(resourceMap.getColor("jTextField12.background")); // NOI18N
        jTextField12.setEditable(false);
        jTextField12.setFont(resourceMap.getFont("jTextField12.font")); // NOI18N
        jTextField12.setForeground(resourceMap.getColor("jTextField12.foreground")); // NOI18N
        jTextField12.setName("jTextField12"); // NOI18N

        jTextField11.setBackground(resourceMap.getColor("jTextField11.background")); // NOI18N
        jTextField11.setEditable(false);
        jTextField11.setFont(resourceMap.getFont("jTextField12.font")); // NOI18N
        jTextField11.setForeground(resourceMap.getColor("jTextField11.foreground")); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField11.setFocusable(false);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        desde.setForeground(resourceMap.getColor("desde.foreground")); // NOI18N
        desde.setFormats(new String[] { "dd/M/yyyy" });
        desde.setName("desde"); // NOI18N
        desde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desdeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                desdeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                desdeMouseReleased(evt);
            }
        });
        desde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desdeActionPerformed(evt);
            }
        });
        desde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desdeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                desdeKeyReleased(evt);
            }
        });

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        hasta.setForeground(resourceMap.getColor("hasta.foreground")); // NOI18N
        hasta.setEditable(false);
        hasta.setFormats(new String[] { "dd/M/yyyy" });
        hasta.setName("hasta"); // NOI18N
        hasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hastaKeyPressed(evt);
            }
        });

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setFocusable(false);
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel26.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel26.setForeground(resourceMap.getColor("jLabel26.foreground")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(968, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26)
        );

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2020" }));
        jComboBox10.setName("jComboBox10"); // NOI18N
        jComboBox10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox10KeyPressed(evt);
            }
        });

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });

        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jComboBox11.setForeground(resourceMap.getColor("jComboBox11.foreground")); // NOI18N
        jComboBox11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox11.setName("jComboBox11"); // NOI18N
        jComboBox11.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });
        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });
        jComboBox11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox11FocusLost(evt);
            }
        });
        jComboBox11.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jComboBox11InputMethodTextChanged(evt);
            }
        });
        jComboBox11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox11KeyPressed(evt);
            }
        });

        jComboBox12.setForeground(resourceMap.getColor("jComboBox12.foreground")); // NOI18N
        jComboBox12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox12.setName("jComboBox12"); // NOI18N
        jComboBox12.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox12ItemStateChanged(evt);
            }
        });
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });
        jComboBox12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox12FocusLost(evt);
            }
        });
        jComboBox12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox12KeyPressed(evt);
            }
        });

        jComboBox13.setForeground(resourceMap.getColor("jComboBox13.foreground")); // NOI18N
        jComboBox13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox13.setName("jComboBox13"); // NOI18N
        jComboBox13.setPreferredSize(new java.awt.Dimension(140, 20));
        jComboBox13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox13ItemStateChanged(evt);
            }
        });
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });
        jComboBox13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox13FocusLost(evt);
            }
        });
        jComboBox13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox13KeyPressed(evt);
            }
        });

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jTextField13.setForeground(resourceMap.getColor("jTextField13.foreground")); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField13.setName("jTextField13"); // NOI18N
        jTextField13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField13FocusLost(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel33)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField13)
                    .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox11, 0, 222, Short.MAX_VALUE)
                    .addComponent(jComboBox12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
       RealizaOpciones(evt);
}//GEN-LAST:event_jButton9KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       // ListaProforma();
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton8KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CambioUsuario();
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton4KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (this.jTextField4.getText().trim().length() == 0) {
            HabilitaBotones(true);
            HabilitaColor();
            Cliente_Comun();
            jTextField14.requestFocus();
        } else {
            new FormClientes(this, objetoventana, id_botica).show(true);
        }
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton5KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        NuevoUsuario();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           NuevoUsuario();
        }

        RealizaOpciones(evt);
}//GEN-LAST:event_jButton3KeyPressed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        CerrarVentana();
}//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    CerrarVentana();
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CerrarVentana();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton6KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        int resul = objClientes.EsDescuento(listatipospagos.get(jComboBox6.getSelectedIndex()).getId_TipoPago());

        if (resul == 1) {
            int elcodigo = Integer.parseInt(jTextField6.getText().trim());
            if (objClientes.Recupera_IdCliente_Descuento(id_botica,elcodigo) == elcodigo) {
                GeneraProforma();
            } else {
                JOptionPane.showMessageDialog(this, "Error \n A este Cliente no se le Puede aplicar \n el Descuento al Personal \n Porfavor Seleccione un Cliente Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            GeneraProforma();
        }
}//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton7KeyPressed

    private void RecuperaTiposPagos() {
        listatipospagos = objfactura.Recupera_Tipos_PagosSOAT(0);
        for (int i = 0; i < listatipospagos.size(); i++) {
            String descrp = listatipospagos.get(i).getDescripcion();
            jComboBox6.addItem(descrp);
        }
    }

    public void MuestraClientes(NuevoCliente obj, int id_cliente) {
        jComboBox5.addItem(obj.getObjcliente().getDireccion());
        jTextField5.setText(obj.getObjcliente().getDNI());
        jTextField14.setText(obj.getObjcliente().getNombre_RazonSocial());
        jTextField10.setText(obj.getObjcliente().getRUC_DNI());
        jTextField6.setText(obj.getObjcliente().getTelefono());
        idcliente = id_cliente;
        jTextField4.setText(String.valueOf(idcliente));
        HabilitaBotones(true);
        HabilitaColor();
        jTextField14.requestFocus();
    }
    
    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jPanel4KeyPressed

    private void jComboBox8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox8KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox8KeyPressed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed

}//GEN-LAST:event_jComboBox8ActionPerformed

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged

        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            if (Verifica_Productos_Afectos()) {
                AsignaCredito();
            } else {
                JOptionPane.showMessageDialog(this, " ERROR : DESCUENTO NO APLICABLE ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox1.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox7KeyPressed

    private void jComboBox7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox7FocusLost
        jLabel7.setForeground(Color.black);
}//GEN-LAST:event_jComboBox7FocusLost

    private void jComboBox7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox7FocusGained
        jLabel7.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jComboBox7FocusGained

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        if (listatipospagos.size() > 0) {
            if (listatipospagos.get(this.jComboBox7.getSelectedIndex()).getId_TipoPago() == 2) {
                VerificaCreditoEspecial(jTextField4.getText().trim());
            }
        }
}//GEN-LAST:event_jComboBox7ActionPerformed

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged

}//GEN-LAST:event_jComboBox7ItemStateChanged

    private void jComboBox6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox7.requestFocus();
        }

        if (evt.getKeyCode() == 16) {
            jTextField6.requestFocus();
        }

        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox6KeyPressed

    private void jComboBox6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox6FocusLost
        jLabel13.setForeground(Color.black);
}//GEN-LAST:event_jComboBox6FocusLost

    private void jComboBox6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox6FocusGained
        jLabel13.setForeground(new Color(98, 156, 245));
}//GEN-LAST:event_jComboBox6FocusGained

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed

}//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged

}//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jPanel12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel12KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jPanel12KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField5.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jComboBox5KeyPressed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed

}//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox6.requestFocus();
        }       
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        //jLabel20.setForeground(Color.black);
        //jTextField6.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField6FocusLost

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
       // jLabel20.setForeground(new Color(98, 156, 245));
       // jTextField6.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField6FocusGained

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        
}//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField6.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
           jLabel19.setForeground(Color.black);
           jTextField10.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField10FocusLost

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
            jLabel19.setForeground(new Color(98, 156, 245));
            jTextField10.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField10FocusGained

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        jTextField4.requestFocus();
}//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField6.requestFocus();
        }
        RealizaOpciones(evt);

}//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
           jLabel18.setForeground(Color.black);
           jTextField5.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField5FocusLost

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
            jLabel18.setForeground(new Color(98, 156, 245));
           jTextField5.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField5FocusGained

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.requestFocus();
}//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox5.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
            jLabel16.setForeground(Color.black);
            jTextField14.setBackground(new Color(255, 255, 255));
}//GEN-LAST:event_jTextField14FocusLost

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
            jLabel16.setForeground(new Color(98, 156, 245));
            jTextField14.setBackground(new Color(255, 255, 102));
}//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        jComboBox4.requestFocus();
}//GEN-LAST:event_jTextField14ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:        
        if (evt.getStateChange() == 2) {            
            jComboBox4.removeAllItems();
            CargarModelo();
            this.jTextField3.setText("");
            this.jLabel9.setText("");
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 2) {            
            jComboBox2.removeAllItems();
            CargarTipo();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        CambioUsuario();
}//GEN-LAST:event_jTextField11KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if(jComboBox1.getSelectedIndex()== -1 || jComboBox1.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UNA CLASE DE VEHICULO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox1.requestFocus();
                this.jTextField3.setText("");
                this.jLabel9.setText("");
            } else {
                jComboBox2.requestFocus();
                this.jTextField3.setText("");
                this.jLabel9.setText("");
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jComboBox2.getSelectedIndex()== -1 || jComboBox2.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UN TIPO DE VEHICULO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox2.requestFocus();
            } else {
            jComboBox14.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            if(jComboBox3.getSelectedIndex()== -1 || jComboBox3.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UNA MARCA DE VEHICULO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox3.requestFocus();
            } else {
                jComboBox4.requestFocus();
                }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
        // TODO add your handling code here:
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {           

               if(jComboBox4.getSelectedIndex()== -1 || jComboBox4.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UN MODELO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox4.requestFocus();
            } else {
                jComboBox15.requestFocus();
           }        
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        // TODO add your handling code here:
        jLabel1.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        // TODO add your handling code here:
        jLabel1.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox1FocusLost

    private void jComboBox2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox2FocusGained
        // TODO add your handling code here:
        jLabel2.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox2FocusGained

    private void jComboBox2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox2FocusLost
        // TODO add your handling code here:
        jLabel2.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox2FocusLost

    private void jComboBox3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox3FocusGained
        // TODO add your handling code here:
        jLabel4.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox3FocusGained

    private void jComboBox3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox3FocusLost
        // TODO add your handling code here:
        jLabel4.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox3FocusLost

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        RealizaOpciones(evt);
    }//GEN-LAST:event_formKeyReleased

    private void jComboBox3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3KeyReleased

    private void jComboBox14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox14KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBox3.requestFocus();
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox14KeyPressed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox15ActionPerformed

    private void jComboBox15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox15KeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            CalculaPrecio();

           /* String zonadesc = String.valueOf(jComboBox15.getSelectedItem());
            if (zonadesc.equals("-- Seleccione Zona --")){
                zonadesc = "";
            }else{
                zonadesc = zonadesc;
            }            
            CargarDepartamento(zonadesc);*/
            
            if(jComboBox15.getSelectedIndex()== -1 || jComboBox15.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UNA ZONA", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox15.requestFocus();
            } else {
                String verifica;
                verifica = this.jTextField3.getText();
                if ( (verifica == null) || (verifica.equals("")) || (verifica.equals("0.0")) ) {
                    jComboBox1.requestFocus();
                    lblcomision.setText("");
                    
                } else{
                    jComboBox10.requestFocus();
                }
           }        
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox15KeyPressed

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 2) {
            
            jComboBox12.removeAllItems();
            CargarProvincia();
        }
    }//GEN-LAST:event_jComboBox11ItemStateChanged

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ActionPerformed

    private void jComboBox11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox11FocusGained
        // TODO add your handling code here:
        jLabel29.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox11FocusGained

    private void jComboBox11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox11FocusLost
        // TODO add your handling code here:
        jLabel29.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox11FocusLost

    private void jComboBox11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox11KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jComboBox11.getSelectedIndex()== -1 || jComboBox11.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UN DEPARTAMENTO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox11.requestFocus();
            } else {                
                jComboBox12.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox11KeyPressed

    private void jComboBox12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox12ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 2) {
            
            jComboBox13.removeAllItems();
            CargarDistrito();
            jButton7.setEnabled(true);
            jButton5.setEnabled(true);
        }
        
    }//GEN-LAST:event_jComboBox12ItemStateChanged

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jComboBox12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox12FocusGained
        // TODO add your handling code here:
        jLabel31.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox12FocusGained

    private void jComboBox12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox12FocusLost
        // TODO add your handling code here:
        jLabel31.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox12FocusLost

    private void jComboBox12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox12KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            if(jComboBox12.getSelectedIndex()== -1 || jComboBox12.getSelectedIndex()==0){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR ELIJA UNA PROVINCIA", "Error", JOptionPane.ERROR_MESSAGE);
                this.jComboBox12.requestFocus();
            } else {
            jComboBox13.requestFocus();
                }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox12KeyPressed

    private void jComboBox13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox13ItemStateChanged
        // TODO add your handling code here:        
    }//GEN-LAST:event_jComboBox13ItemStateChanged

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void jComboBox13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox13FocusGained
        // TODO add your handling code here:
        jLabel32.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox13FocusGained

    private void jComboBox13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox13FocusLost
        // TODO add your handling code here:
        jLabel32.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox13FocusLost

    private void jComboBox13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox13KeyPressed
        // TODO add your handling code here:        
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox13KeyPressed

    private void jComboBox10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox10KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField7.requestFocus();
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox10KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if(jTextField7.getText().equals("")){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR INGRESE EL NUMERO DE SERIE", "Error", JOptionPane.ERROR_MESSAGE);
                this.jTextField7.requestFocus();
            } else {
            jTextField13.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField7KeyPressed

    private void desdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desdeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hasta.requestFocus();
        }
        RealizaOpciones(evt);        
    }//GEN-LAST:event_desdeKeyPressed

    private void hastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hastaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField8.requestFocus();
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_hastaKeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jTextField8.getText().equals("")){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR INGRESE NUMERO DE POLIZA", "Error", JOptionPane.ERROR_MESSAGE);
                this.jTextField8.requestFocus();
            } else {
            jTextField9.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if(jTextField9.getText().equals("")){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR INGRESE NUMERO DE CERTIFICADO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jTextField9.requestFocus();
            } else {
            jComboBox11.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

           if(jTextField13.getText().equals("")){
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(this, "POR FAVOR INGRESE LA PLACA DEL VEHICULO", "Error", JOptionPane.ERROR_MESSAGE);
                this.jTextField13.requestFocus();
            } else {
                this.jTextField9.requestFocus();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jComboBox14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox14FocusGained

         jLabel3.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox14FocusGained

    private void jComboBox14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox14FocusLost
        // TODO add your handling code here:
        jLabel3.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox14FocusLost

    private void jComboBox4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox4FocusGained
        // TODO add your handling code here:
        jLabel5.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox4FocusGained

    private void jComboBox4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox4FocusLost
        // TODO add your handling code here:
        jLabel5.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox4FocusLost

    private void jComboBox15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox15FocusLost
        // TODO add your handling code here:
        jLabel30.setForeground(Color.black);
    }//GEN-LAST:event_jComboBox15FocusLost

    private void jComboBox15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox15FocusGained
        // TODO add your handling code here:
        jLabel30.setForeground(new Color(98, 156, 245));
    }//GEN-LAST:event_jComboBox15FocusGained

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        // TODO add your handling code here:
        jLabel23.setForeground(new Color(98, 156, 245));
        jTextField7.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        // TODO add your handling code here:
        jLabel23.setForeground(Color.black);
        jTextField7.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusGained
        // TODO add your handling code here:
        jLabel33.setForeground(new Color(98, 156, 245));
        jTextField13.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_jTextField13FocusGained

    private void jTextField13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusLost
        // TODO add your handling code here:
        jLabel33.setForeground(Color.black);
        jTextField13.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jTextField13FocusLost

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        // TODO add your handling code here:
        jLabel27.setForeground(new Color(98, 156, 245));
        jTextField8.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        // TODO add your handling code here:
        jLabel27.setForeground(Color.black);
        jTextField8.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        // TODO add your handling code here:
        jLabel28.setForeground(Color.black);
        jTextField9.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        // TODO add your handling code here:
        jLabel28.setForeground(new Color(98, 156, 245));
        jTextField9.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_jTextField9FocusGained

    private void jComboBox11InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBox11InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11InputMethodTextChanged

    private void jComboBox15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox15ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 2) {

            String zonadesc = String.valueOf(jComboBox15.getSelectedItem());
            if (zonadesc.equals("-- Seleccione Zona --")){
                zonadesc = "";
            }else{
                zonadesc = zonadesc;
            }
            CalculaPrecio();
            jComboBox11.removeAllItems();
            CargarDepartamento(zonadesc);
        }


            
        
    }//GEN-LAST:event_jComboBox15ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:

        this.jTextField3.setText("");
        this.jLabel9.setText("");
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        this.jTextField3.setText("");
        this.jLabel9.setText("");
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        // TODO add your handling code here:
        this.jTextField3.setText("");
        this.jLabel9.setText("");
    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void desdeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desdeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_desdeMouseClicked

    private void desdeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desdeMousePressed
        // TODO add your handling code here:        
    }//GEN-LAST:event_desdeMousePressed

    private void desdeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desdeMouseReleased
        // TODO add your handling code here:      
    }//GEN-LAST:event_desdeMouseReleased

    private void desdeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desdeKeyReleased
        // TODO add your handling code here:        
    }//GEN-LAST:event_desdeKeyReleased

    private void desdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desdeActionPerformed
        // TODO add your handling code here:

        FechaInicio = this.desde.getDate();

        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
        String fechaSistema=formateador.format(fechaActual);
        String fechaescogida=formateador.format(FechaInicio);
        
        /**Realizamos los llamados a los metodos de ejemplo*/

        String resultadoMenor= compararFechasConDate(fechaescogida,fechaSistema);        
        //JOptionPane.showMessageDialog(this, resultadoMenor);
        if (resultadoMenor == "1"){
            JOptionPane.showMessageDialog(this,"La Fecha que ha escogido no debe ser menor a la del sistema", "Error", JOptionPane.ERROR_MESSAGE);
            desde.setDate(fechaActual);
            /* AUMENTO A 1 AÑO LA FECHA */
            java.util.Calendar hoy = java.util.Calendar.getInstance();
            hoy.add(java.util.Calendar.YEAR, 1);
            hasta.setDate(hoy.getTime());

        }else{

            Calendar cal = Calendar.getInstance();
            cal.setTime(FechaInicio);
            /* AUMENTO A 1 AÑO LA FECHA */
            cal.add(Calendar.YEAR, 1);
            hasta.setDate(cal.getTime());
        }
    }//GEN-LAST:event_desdeActionPerformed


    private String compararFechasConDate(String fecha1, String fechaActual) {

    String resultado="";
        try {
       /**Obtenemos las fechas enviadas en el formato a comparar*/
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
       Date fechaDate1 = formateador.parse(fecha1);
       Date fechaDate2 = formateador.parse(fechaActual);
       
        if ( fechaDate1.before(fechaDate2) ){
        resultado= "1";          //"La Fecha q ha escogido el usuario es menor a la del sistema "
        }else{
         if ( fechaDate2.before(fechaDate1) ){
          resultado= "2"; //"La Fecha q ha escogido el usuario es mayor a la del sistema "
         }else{
          resultado= "3"; //Las Fechas Son iguales
         }
        }
      } catch (ParseException e) {
       System.out.println("Se Produjo un Error!!!  "+e.getMessage());
      }
      return resultado;
 }

    private void CalculaPrecio(){

        listPreciosSoat.clear();

        jTextField3.setText("");
        lblcomision.setText("");
        int asientos =Integer.parseInt( jComboBox14.getSelectedItem().toString());
        try {
            //listModelos.clear();
            listPreciosSoat = objPreciosSoat.ListaPreciosSoat(
                    listClases.get(jComboBox1.getSelectedIndex()-1).getIdAsociado(),
                    listTipos.get(jComboBox2.getSelectedIndex()-1).getIdAsociado(),
                    listMarcas.get(jComboBox3.getSelectedIndex()-1).getIdAsociado(),
                    listModelos.get(jComboBox4.getSelectedIndex()-1).getIdAsociado(),
                    asientos,
                    listZonasSoat.get(jComboBox15.getSelectedIndex()-1).getIdAsociado()
                    );            
            
            String precio="";
            String comision ="";
            precio = String.valueOf(listPreciosSoat.get(0).getPrecio());
            comision = String.valueOf(listPreciosSoat.get(0).getcomision());
            
            if ( (precio == null) || (precio.equals("")) || (precio.equals("0.0")) || (precio.equals("0")) ||
                 (comision == null) || (comision.equals("")) || (comision.equals("0.0"))|| (comision.equals("0"))) {
                    jLabel9.setText("No Hay Precio para este Vehiculo");
                this.jComboBox1.requestFocus();
            }else{                                
                jTextField3.setText(precio);
                lblcomision.setText(comision);
            }
            
        } catch (Exception ex) {
        }

    }

    private void VerificaCreditoEspecial(String idcliente) {
        int id = Integer.parseInt(jTextField4.getText().trim());
        ListDescuentos.removeAll(ListDescuentos);
        ListDescuentos = objClientes.Verifica_Descuento_Cliente(id);
        jComboBox8.removeAllItems();
        if (ListDescuentos.size() > 0) {
            jComboBox8.addItem(" Seleccionar Descuento ");
            for (int i = 0; i < ListDescuentos.size(); i++) {
                jComboBox8.addItem(ListDescuentos.get(i).getPorcen_descuento());
            }
        }
    }
    private void AsignaCredito() {

        if (jComboBox3.getItemCount() > 0) {

            int fila = 0;
            int id = jComboBox3.getSelectedIndex();
            total = 0;
            SubTotal = 0;
//            int filaactual = jTable2.getRowCount();
            boolean existe = false;
            String idpro = ListDescuentos.get(id - 1).getIdproductodesc().trim();

            existe = false;
            
            if (id > 0 && lis_tipo_venta.get(jComboBox2.getSelectedIndex()).getId_Tipo_Venta() == 2) {
                double porcentaje = ListDescuentos.get(id - 1).get_pocen_descuento();
                double tot1 = ((total * porcentaje) / 100) * -1;
                BigDecimal bd = new BigDecimal(tot1);
                codprodespec = ListDescuentos.get(id - 1).getIdproductodesc();
                p = objProducto.Recupera_Porcen_Descuento(codprodespec);              

                if (!existe) {                
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
                 //   jTextField8.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
                    tablaproforma.setValueAt(codprodespec, fila, 1);
                    tablaproforma.setValueAt(ListDescuentos.get(id - 1).getPorcen_descuento(), fila, 2);
                    tablaproforma.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 8);
                }
            } else {
                if (id == 0) {
                    if (existe)//LE QUITO EL DESCUENTO
                    {                   
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
            }
        }

        return resul1;
    }

    
    
    
    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    private void CerrarVentana() {
        int response = JOptionPane.showConfirmDialog(null, " Desea Cerrar Esta Ventana ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {            
            AplicacionVentas.marcacdo = false;
            objventas.Habilita(true);
            dispose();
        }

    }
    private void BusquedaProducto() {
        objproductos.setSeleccionaart(false);
        objproductos = new BuscarProductos(objetoventana, precbotiquin);
        objproductos.setVisible(true);
        if (objproductos.seleccionaart) {
            new ProductoPedido(objproductos.getProductoPrecio(),1).setVisible(true);
            if (ProductoPedido.ingresadet) {
                if (ProductoPedido.getCantidad().compareTo("") != 0) {
                    AgregaProforma(ProductoPedido.getCantidad(), objproductos.getProductoPrecio());
                }
            }
        }
    }
    private void CargarClase() {
        listClases = objClase.ListaClases();
        this.jComboBox1.addItem("-- Seleccione Clase --");
        for (int i = 0; i < listClases.size(); i++) {
            this.jComboBox1.addItem(listClases.get(i).getDescripcion());
        }        
    }    
    private void CargarMarca() {
        listMarcas = objMarcas.ListaMarcas();
        this.jComboBox3.addItem("-- Seleccione Marca --");
        for (int i = 0; i < listMarcas.size(); i++) {
            this.jComboBox3.addItem(listMarcas.get(i).getDescripcion());
        }
    }
    private void CargarDepartamento(String zonadesc) {

        try {
        listDepartamentos.clear();
        listDepartamentos = objDepartamentos.ListaDepartamentos(zonadesc);

        jComboBox11.addItem("-- Seleccione Departamento --");
        for (int i = 0; i < listDepartamentos.size(); i++) {
            this.jComboBox11.addItem(listDepartamentos.get(i).getDescripcion());
        }
        } catch (Exception ex) {
        }
    }
    private void CargarTipo() {
        try {
        listTipos.clear();
        listTipos = objTipos.ListaTipos(listClases.get(jComboBox1.getSelectedIndex()-1).getIdAsociado());
        jComboBox2.addItem("-- Seleccione clase --");        
        for (int i = 0; i < listTipos.size(); i++) {
            
            this.jComboBox2.addItem(listTipos.get(i).getDescripcion());
        }
        } catch (Exception ex) {
        }
    }
    private void CargarModelo() {        
        try {
        listModelos.clear();
        listModelos = objModelos.ListaModelos(listMarcas.get(jComboBox3.getSelectedIndex()-1).getIdAsociado());
        
        jComboBox4.addItem("-- Seleccione Modelo --");
        for (int i = 0; i < listModelos.size(); i++) {

            this.jComboBox4.addItem(listModelos.get(i).getDescripcion());
        }
        } catch (Exception ex) {
        }
    }
    private void CargarProvincia() {
        try {
        listProvincias.clear();
        listProvincias = objProvincias.ListaProvincias(listDepartamentos.get(jComboBox11.getSelectedIndex()-1).getIdAsociado());
        jComboBox12.addItem("-- Seleccione Provincia --");
        for (int i = 0; i < listProvincias.size(); i++) {

            this.jComboBox12.addItem(listProvincias.get(i).getDescripcion());
        }
        } catch (Exception ex) {
        }
    }
    private void CargarDistrito() {
        try {
        listDistritos.clear();
        listDistritos = objDistritos.ListaDistritos(listProvincias.get(jComboBox12.getSelectedIndex()-1).getIdAsociado());
        jComboBox13.addItem("-- Seleccione Distrito --");
        for (int i = 0; i < listDistritos.size(); i++) {

            this.jComboBox13.addItem(listDistritos.get(i).getDescripcion());
        }
        } catch (Exception ex) {
        }
    }
    private void CargarZonaSoat(){
       listZonasSoat = objZonasSoat.ListaZonasSoat();
        this.jComboBox15.addItem("-- Seleccione Zona --");
        for (int i = 0; i < listZonasSoat.size(); i++) {
            this.jComboBox15.addItem(listZonasSoat.get(i).getDescripcion());
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
        jButton7.setEnabled(true);
        jButton5.setEnabled(true);
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

    private void AsignaPrecio(ProductosPrecios precios) {
        int indfila = cantidadproductos - 1;
        double parcial1 = 0;
        int empaque1 = Integer.parseInt(empaque.toString());

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

                    if (objproductos.isEspromo()) {
                        cantidad = objProducto.Verifica_Promocion(idproducto);
                        List<ListaDetalles> listPromocion = mantProduc.verificaPromocion(idproducto,0);
                        if (cantidad > 1) {
                            obj = new ProductosPromociones(objetoventana, nomproducto, idproducto);
                            obj.pack();
                            obj.setVisible(true);
                            descuento2 = obj.getDescuento();
                            recuperaUnidadesminimo = 1;
                        } else {
                            orden = listPromocion.get(0).getOrden();
                            descuento2 = armarPromocion(listPromocion, idproducto, unidad);
                            recuperaUnidadesminimo = retornaUnidades(listPromocion, unidad);
                        }
                    }

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

                    tablaproforma.addRow(listadetalle);
                    double igvaux = precios.getProductoBotica().getProducto().getIGV_Exonerado();

                    CalculaMontos(igvaux, indfila);

                    if (jComboBox3.getItemCount() > 0) {
                        AsignaCredito();
                    }

                    if (ss > 0.00) {
//                        listadetalle[0] = jTable2.getRowCount() + 1;
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

                        if (unidad > 0 && orden == 1) {
                        } else {
                            tablaproforma.addRow(listadetalle);
                            tablaproforma.setValueAt(1, indfila + 1, 4);
                            listaempaque.add(empaque1);
                        }

                        CalculaMontos(igvaux, indfila + 1);
                    }

                    RcepueraTipoPago();                

                    if (igvaux < 1) {
                        lisProdSinIGV.add(idproducto);                  
                        col.setCellRenderer((TableCellRenderer) new ColoredTableCellRenderer());                  
                        col.setCellRenderer((TableCellRenderer) new ColoredTableCellRenderer());
                    }

                } catch (Exception ex) {
                    System.out.println("ERROR EN LA CAPA VISTA METODO AsiignaPrecio : " + ex.toString());
                }
            }
        }
    }
    private void RcepueraTipoPago() {
        idtipopago = listatipospagos.get(jComboBox6.getSelectedIndex()).getId_TipoPago();
    }
/*******************************************
    METODO QUE CALCULA EL MONTO TOTAL  A PAGAR
     ********************************************/
    private void CalculaMontos(double band_igv, int fila) {
        double auxparcial = 0.0;
//        double valortabla = Double.parseDouble(String.valueOf(this.jTable2.getValueAt(fila, 8)));

        if (band_igv == 0) {
        //    total += valortabla;
        //    SubTotal += valortabla;
        //    listsubtotales.add(fila, valortabla);
        } else {
            if (IGV == 0) {
                CapturaIGV();
            }
        //    total += valortabla;
        //    auxparcial = (valortabla / (1 + (IGV / 100)));
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

       // jTextField7.setText(bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
       // jTextField9.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
        double miigv = total - SubTotal;

        BigDecimal bd3 = new BigDecimal(miigv);
        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        miigv = bd3.doubleValue();

       // jTextField16.setText(bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void CapturaIGV() {
        IGV = mantProduc.Captura_IGV();
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
    private void RealizaOpciones(java.awt.event.KeyEvent evt) {
        try {
            if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
                //BusquedaProducto();
            } else {
                if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
                    //ListaProforma();
                } else {
                    if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {                      
                      
                            if (Verifica_Fecha()) {
                                GeneraProforma();
                            } else {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS LA HORA DEL SERVIDOR ES INCORRECTA \n PORFAVOR COMUNIQUESE CON INFORMATICA \n FECHA DEL SERVIDOR : " + objlogiccafecha.RetornaFecha() + " " + objlogiccafecha.RetornaHora() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                       
                    } else {
                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
                            CambioUsuario();
                        } else {
                            if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
                                CerrarVentana();
                            } else {
                                /*if (evt.getKeyText(evt.getKeyCode()).compareTo("F4") == 0) {
                                    if (bandseleccion == 0) {
                                        if (jTextField4.getText().trim().length() == 0) {
                                            HabilitaBotones(true);
                                            HabilitaColor();
                                            Cliente_Comun();
                                            jTextField14.requestFocus();
                                        } else {
                                            new FormClientes(this, objetoventana, id_botica).show(true);
                                        }
                                    }
                                } else {*/
                                    if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
                                        NuevaProforma();
                                    } else {
                                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
                                            NuevoUsuario();
                                        }
                                    }
                                //}
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("RealizaOpciones " + ex.toString());
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
            System.out.println("RECUPERA USUARIO EN COTIZACIONSOAT:" + ex.getMessage());
        }
    }

    private void NuevaProforma() {
       // if (jTable2.getRowCount() > 0) {
            Confirmar pe = new Confirmar(objetoventana, "<html> Deseas Realizar una Nueva Proforma </html>");
            pe.show(true);
            if (pe.getConfirmar() == 1) {
                LimpiaTodo();
            }
      //  }
    }

    /**********************************************
     * METODO QUE ELIMINA UN PRODUCTOS DE MI JTABLE
     **********************************************/
    private void EliminaProducto(int fila) {
        int filas = tablaproforma.getRowCount();

        if (filas > 0) {
            try {
                if (fila >= 0) {
                    Confirmar pe = new Confirmar(objetoventana, "<html> Desea quitar el Producto : " + tablaproforma.getValueAt(fila, 2) + "  ? </html>");
                    pe.show(true);

                    if (pe.getConfirmar() == 1) {

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
       
                        BigDecimal bd1 = new BigDecimal(SubTotal);
                        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        SubTotal = bd1.doubleValue();

                        BigDecimal bd2 = new BigDecimal(total);
                        bd2 = bd2.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        total = bd2.doubleValue();

                        col.setCellRenderer(new ColoredTableCellRenderer());
                        col.setCellRenderer(new ColoredTableCellRenderer());
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
            }
        }
    }

    private void GeneraProforma() {
        boolean band = true;
        RcepueraTipoPago();        
        String resulta = null;

        if (jComboBox7.getItemCount() > 0) {
            RcepueraTipoPago();
            if (esCredito(idtipopago)) {
                if (Double.valueOf(jTextField3.getText().toString()) > objClientes.RecuperaSaldo(Integer.parseInt(jTextField4.getText().toString()), id_botica)) {
                    
                    JOptionPane.showMessageDialog(this, "EL CLIENTE NO CUENTA SON SALDO PARA ESTA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
                    band = false;
                } else {
                    
                }
            } else {
                int resul1 = objClientes.EsDescuento(listatipospagos.get(jComboBox6.getSelectedIndex()).getId_TipoPago());
                if (resul1 == 1) {
                    int elcodigo = Integer.parseInt(jTextField4.getText().trim());
                    if (objClientes.Recupera_IdCliente_Descuento(id_botica,elcodigo) != Integer.parseInt(jTextField4.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Error \n A este Cliente no se le Puede aplicar \n el Descuento al Personal \n Porfavor Seleccione un Cliente Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                        band = false;
                    }
                }
            }

            if (band) {
                int posicion = 0;
                idtipoventa = lis_tipo_venta.get(jComboBox7.getSelectedIndex()).getId_Tipo_Venta();
                ruc = jTextField10.getText().trim();

                if (idtipoventa == 2) {
                    resulta = objfactura.Verifica_Datos_Factura(idtipoventa, ruc, jTextField14.getText().trim(), jComboBox5.getSelectedItem().toString().trim());
                }

                if (resulta != null) {
                    JOptionPane.showMessageDialog(this, resulta.substring(1, resulta.length()), "Error", JOptionPane.ERROR_MESSAGE);

                    if (Integer.parseInt(resulta.substring(0, 1)) == 1) {
                        jTextField10.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 2) {
                        jTextField14.requestFocus();
                    } else if (Integer.parseInt(resulta.substring(0, 1)) == 3) {
                        jComboBox6.requestFocus();
                    }
                } else {
                    String validador = this.jTextField7.getText();
                    String validador1 = this.jTextField8.getText();
                    String validador2 = this.jTextField9.getText();
                    String validador3 = this.jTextField13.getText();
                    String validador4 = this.jTextField14.getText();
                    String validador5 = this.jTextField5.getText();

                    
                        if (validador4 == null || validador4.equals("")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE CLIENTE ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jTextField14.requestFocus();
                        }else if (validador5 == null || validador5.equals("")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE DNI ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jTextField5.requestFocus();
                        }else if (jComboBox5.getSelectedItem().equals("")) {
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE DIRECCION ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox5.requestFocus();
                        }else if (jComboBox1.getSelectedItem().equals("-- Seleccione Clase --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE CLASE ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox1.requestFocus();
                        }else if (jComboBox2.getSelectedItem().equals("-- Seleccione Tipo --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE TIPO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox2.requestFocus();
                        }else if (jComboBox3.getSelectedItem().equals("-- Seleccione Marca --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE MARCA ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox3.requestFocus();
                        }else if (jComboBox4.getSelectedItem().equals("-- Seleccione Modelo --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE MODELO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox4.requestFocus();
                        }else if (validador == null || validador.equals("")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE SERIE ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jTextField7.requestFocus();
                        }else if (validador3 == null || validador3.equals("")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE PLACA ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jTextField13.requestFocus();                        
                        }else if (validador2 == null || validador2.equals("")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR INGRESE CERTIFICADO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jTextField9.requestFocus();
                        }else if (jTextField3.getText().trim().compareTo("")==0) {
                            JOptionPane.showMessageDialog(this, " EL VEHICULO NO CUENTA CON UN PRECIO ASIGNADO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox5.requestFocus();
                        }else if (jComboBox11.getSelectedItem().equals("-- Seleccione Departamento --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE DEPARTAMENTO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox11.requestFocus();
                        }else if (jComboBox12.getSelectedItem().equals("-- Seleccione Provincia --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE PROVINCIA ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox12.requestFocus();
                        }else if (jComboBox13.getSelectedItem().equals("-- Seleccione Distrito --")){
                            JOptionPane.showMessageDialog(this, " POR FAVOR SELECCIONE DISTRITO ", "Error", JOptionPane.ERROR_MESSAGE);
                            this.jComboBox13.requestFocus();
                        }else{

                            if (jTextField4.getText().trim().compareTo("") != 0) {                                
                                
                                Confirmar pe = new Confirmar(objetoventana, "<html> SEGURO DE GRABAR LA PROFORMA PARA SOAT </html>");
                                pe.show(true);
                                if (pe.getConfirmar() == 1) {
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

    public boolean esCredito(int idtipopago) {
        boolean escredito = false;
        escredito = objguardaventa.EsCredito(idtipopago);
        return escredito;
    }

    /*
     * METODO PARA GUARDAR LA PROFORMA
     */
    private void GuardaProforma() {
        int idpersona = Integer.parseInt(this.jTextField12.getText().trim());

        Guardar();
    }   
    
    public void Guardar() {       

        String resultado = "";
        String nomcliente = "";
        String comision = "";
        String dni = "";
        String direccion = "";
        String ruc = "";
        String telefono = "";
        int clase;
        int tipo;
        int marca;
        int modelo ;
        String asientos = "";
        int zona;
        String uso = "";
        Double precio;
        String anio = "";
        String serie = "";
        String placa = "";
        String poliza = "";
        String certificado = "";
        String dep = "";
        String prov = "";
        String dist = "";      
        listdetalleProforma.removeAll(listdetalleProforma);

        FechaInicio = this.desde.getDate();
        FechaFin = this.hasta.getDate();        
        
        try {           

            FechaRegistro = fechaVenta;
            idcliente = Integer.parseInt(this.jTextField4.getText().trim());
            nomcliente = jTextField14.getText().trim().toUpperCase();
            dni = jTextField5.getText().trim();
            direccion = String.valueOf(this.jComboBox5.getSelectedItem());
            ruc = jTextField10.getText().trim();
            telefono = jTextField6.getText().trim();            
            clase = Integer.valueOf(this.jComboBox1.getSelectedIndex());
            tipo = Integer.valueOf(this.jComboBox2.getSelectedIndex());
            marca = Integer.valueOf(this.jComboBox3.getSelectedIndex());
            modelo = Integer.valueOf(this.jComboBox4.getSelectedIndex());
            asientos = String.valueOf(this.jComboBox14.getSelectedItem());
            zona = Integer.valueOf(this.jComboBox15.getSelectedIndex());
            uso = jTextField2.getText().trim();
            precio = Double.valueOf(jTextField3.getText().trim());
            anio = String.valueOf(this.jComboBox10.getSelectedItem());
            serie = jTextField7.getText().trim().toUpperCase();
            placa = jTextField13.getText().trim().toUpperCase();
            poliza = jTextField8.getText().trim();
            certificado = jTextField9.getText().trim().toUpperCase();
            dep = String.valueOf(this.jComboBox11.getSelectedItem());
            prov = String.valueOf(this.jComboBox12.getSelectedItem());
            dist = String.valueOf(this.jComboBox13.getSelectedItem());
            comision = this.lblcomision.getText();

            if (direccion == null) {
                direccion = "";
            }
            if (poliza == null ) {
                poliza = "";
            }

            RcepueraTipoPago();
            IdPersonalBoticaVenta = Integer.parseInt(this.jTextField12.getText().trim());
            entidadproforma = new Proforma(id_botica, idcliente, idtipopago, tipoprecio, idtipoventa, fechaVenta, FechaRegistro, IdPersonalBoticaVenta, nomcliente, dni, direccion, ruc, telefono,
                    clase,tipo,marca,modelo,asientos,zona,uso,precio,anio,serie,placa,poliza,certificado,dep,prov,dist,objlogiccafecha.ConvierteFecha(FechaInicio),objlogiccafecha.ConvierteFecha(FechaFin),comision);
            
            if (Valida_Fechas()) {
                resultado = onjproforma.ProformaRealizadaSoat(entidadproforma);
            }

            if (resultado.compareTo("") != 0) {
                new ProformaGeneradaSoat(resultado, this, objetoventana).show(true);
                LimpiaTodo();
            } else {
                JOptionPane.showMessageDialog(this, " ERROR AL GENERAR LA PROFORMA ", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, " ERROR AL GENERAR LA PROFORMA ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(" ERROR AL GENERAR LA PROFORMA " + ex.getMessage());
        }
    }

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.desde.getDate();
        java.util.Date fecha2 = this.hasta.getDate();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }

        return valor;
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
/*************************************
     * METODO QUE MODIFICA LA CANTIDAD PEDIDAD
     * @param fila
     ***************************************/
    private void ModificaCantidadPedida(int fila) {
        double anterior = 0;
        double parcial1 = 0;

        try {

            Laboratorios laboratorio = new Laboratorios();          
            Producto miproducto = new Producto();
            miproducto.setLaboratorio(laboratorio);          
            Productos_Botica productoBotica = new Productos_Botica();
            productoBotica.setProducto(miproducto);
            ProductosPrecios precios = new ProductosPrecios();          
            precios.setProductoBotica(productoBotica);
            new ProductoPedido(precios,1).setVisible(true);
            if (ProductoPedido.ingresadet) {
                String cantidad = ProductoPedido.getCantidad();
                if (cantidad.compareTo("") != 0) {                 

                    if (cantidad != null) {
                        boolean resul1 = VerificaCantidad(cantidad);
                        if (resul1) {
                            if (unidad == 0 && fraccion == 0) {
                                
                            } else {

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
                if (t <= verificaPromocion.get(0).getTotalPromocionales()) {
                    DetallePromo.add(
                            new ListaDetalles(
                            verificaPromocion.get(0).getIdPromo(),
                            verificaPromocion.get(0).getTotalPromocionales(),
                            verificaPromocion.get(0).getDescuento(),
                            CodProducto,
                            Cantidad,
                            t));

                    Descuento = objProducto.Recupera_Dscto(CodProducto, t - 1,0,0);

                } else {
                    JOptionPane.showMessageDialog(this, "Se completo los cupos de esta promocion, genere otra venta por fv", "Promocion", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        return Descuento;

    }

    private void ModificaMontos(Double valanterior, Double auxparcial, String codpro, int fila) {
        double aux = mantProduc.recupera_Igv_Exonerado(codpro);
        total = 0;

        /*for (int i = 0; i < jTable2.getRowCount(); i++) {
            total += Double.parseDouble(String.valueOf(this.jTable2.getValueAt(i, 8)));
        }*/

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

    }
    
    private void HabilitaColor() {
        jComboBox4.setBackground(Color.WHITE);
        jTextField14.setBackground(Color.WHITE);
        jTextField4.setBackground(Color.WHITE);  
        jTextField10.setBackground(Color.WHITE);
    }

    private void HabilitaBotones(boolean valor) {
        jTextField14.setEnabled(valor);
        jComboBox4.setEnabled(valor);
        jTextField4.setEnabled(valor);
        jTextField10.setEnabled(valor);
        jComboBox2.setEnabled(valor);
        jComboBox1.setEnabled(valor);
    }
    public void LimpiaTodo() {
            
            SubTotal = 0.0;
            total = 0.0;
            cantidadproductos = 1;
            colegiatura = "";
            IGV = 0;
            
            Cliente_Comun();
            jTextField14.setText("");
            jTextField10.setText("");
            jTextField3.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
            jTextField13.setText("");
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);

            jComboBox5.removeAllItems();
            jComboBox1.setSelectedIndex(0);
            jComboBox14.setSelectedIndex(0);
            jComboBox3.setSelectedIndex(0);
            jComboBox4.removeAllItems();
            jComboBox15.setSelectedIndex(0);
            jComboBox10.setSelectedIndex(0);
            jComboBox11.setSelectedIndex(0);
            jComboBox12.removeAllItems();
            jComboBox13.removeAllItems();            
            jComboBox6.setSelectedIndex(0);
            jComboBox7.setSelectedIndex(0);
            jComboBox8.removeAllItems();
            jTextField14.requestFocus();     
    }
    
    private void Cliente_Comun() {
        Clientes def = objClientes.Cliente_Defecto();
        jTextField14.setText(def.getNombre_RazonSocial());
        jTextField4.setText(String.valueOf(def.getId_Cliente()));
        jComboBox4.addItem(def.getDireccion());
        jTextField14.requestFocus();
    }

    private void NuevoUsuario() {
        NuevoCliente pe = new NuevoCliente(this, objetoventana);
        pe.pack();
        pe.setVisible(true);
    }

    public void AsignaDatosCliente() {
        try {
            LimpiarDatos();
            jTextField4.setText(objcliente.getCodigoCliente());
            jTextField10.setText(objcliente.getRUC_DNI());
            jTextField14.setText(objcliente.getNombre_RazonSocial());
            jTextField5.setText(objcliente.getDNI());
            jTextField6.setText(objcliente.getTelefono());            
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
            jComboBox5.addItem(midirecciones.get(i).getDireccion());
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
    private void LimpiarDatos() {
        jTextField6.setText("");
        jTextField10.setText("");
        jTextField14.setText("");
        jComboBox5.removeAllItems();
        jTextField5.setText("");
        jTextField4.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker desde;
    private org.jdesktop.swingx.JXDatePicker hasta;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblcomision;
    // End of variables declaration//GEN-END:variables

}
