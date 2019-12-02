/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiaDeAjusteMov.java
 *
 * Created on 26/04/2015, 12:38:23 PM
 */

package sistemanortfarma;

import CapaLogica.LogicaConexion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProveedor;
import CapaLogica.LogicaTipoMovimiento;
import entidad.Boticas;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.model.Model;
import entidad.Venta;
import javax.swing.table.TableColumnModel;
import sistemanortfarma.ConsultarEnvioEfecOfi.EjecutatHilo;
import CapaLogica.LogicaVenta;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Kelvin
 */
public class ConsultaGuiaDeAjusteMov extends javax.swing.JDialog{

    JTable tablaGuia;
    GuiaDeAjustes objGuia;
    public ModeloTabla modeloTabla;
    private DefaultTableModel tabladetalle;
    MiModelo modeloTabla_2;
    JFrame objetoventana;
    private int cantidadproductos = 1;
    MiModelo modeloTabla_3;
    private String idventa = "";
    private String idguiaajuste="";
    LogicaFechaHora logfecha = new LogicaFechaHora();
    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    private List<Movimiento_Detalle> listdetalleguiaajuste = new ArrayList<Movimiento_Detalle>();
    private List<Movimiento_Detalle> dellistdetalleguiaajuste = new ArrayList<Movimiento_Detalle>();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    String idbotica;
    JLabel numDoc;
    JLabel tipAlm;
    JLabel tipMov;
    JTable jTable2;
    JTable jTable3;
    private String fecha1;
    private String fecha2;
    private int opcion;
    private String documento;
    VerificaSistema obj;
    Venta obj1;
    Guias entidadGuias = new Guias();
    LogicaGuias objGuias = new LogicaGuias();
    Movimientos movimiento = new Movimientos();
    List<Movimiento_Detalle> listaDetalle = new ArrayList<Movimiento_Detalle>();
    Object[] datosDetalle2 = new Object[7];
    Object[] datosDetalle3 = new Object[2];
    TableColumn colu;
    int cuentaDatos;
    List<Venta> listaInternos = new ArrayList<Venta>();
    List<Venta> listaVenta = new ArrayList<Venta>();
    LogicaVenta logventa = new LogicaVenta();
    Object[] datosDetalle = new Object[7];
    private DefaultTableModel model;
    TableColumnModel colModel1;
    private DefaultTableModel model_notas;
    TableColumnModel colModel;
    Object[] datosDetalle_1 = new Object[14];
    Map parameters = new HashMap();
    URL report_file;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;
    Connection con;
    LogicaConexion logconex = new LogicaConexion();
    

    /** Creates new form GuiaDeAjusteMov */
    public ConsultaGuiaDeAjusteMov(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    ConsultaGuiaDeAjusteMov(GuiaDeAjustes objGuiaAjuste, JTable tabla, TableModel modelo2,String id_Botica, JLabel almacen, JLabel movi, JLabel numDocu, JTable tabla3, TableModel modelo3) {
        initComponents();
        AparienciaTabla();
        model_notas = (DefaultTableModel) tablanotas.getModel();
        colModel = tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        tabladetalle = (DefaultTableModel) jTable1.getModel();
        colModel1 = jTable1.getColumnModel();
        jTabbedPane1.setEnabledAt(1, false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        //addKeyListener(this);
        objGuia = objGuiaAjuste;
        tablaGuia = tabla;
        modeloTabla = new ModeloTabla();        
        idbotica = id_Botica;
        Date date = new Date();
        jDateChooser1.setDate(date);
        jDateChooser2.setDate(date);               
        Map parameters = new HashMap();
        con = logconex.RetornaConexion();
        this.txtguiaajuste.setVisible(false);
        BuscaVenta("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablanotas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtobs = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txttipmov = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtguiaajuste = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ConsultaGuiaDeAjusteMov.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel24.setName("jLabel3"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        jDateChooser2.setName("jDateChooser2"); // NOI18N

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator3.setName("jSeparator3"); // NOI18N

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))
                        .addGap(17, 17, 17))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setIcon(resourceMap.getIcon("jLabel17.icon")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablanotas.setFont(resourceMap.getFont("tablanotas.font")); // NOI18N
        tablanotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "FECHA", "SERIE", "NUMERO", "OBSERVACION", "NUNMOV", "DESCRIP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablanotas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablanotas.setName("tablanotas"); // NOI18N
        tablanotas.setSelectionBackground(resourceMap.getColor("tablanotas.selectionBackground")); // NOI18N
        tablanotas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablanotas.getTableHeader().setReorderingAllowed(false);
        tablanotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablanotasMouseClicked(evt);
            }
        });
        tablanotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablanotasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablanotas);
        tablanotas.getColumnModel().getColumn(0).setMinWidth(60);
        tablanotas.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablanotas.getColumnModel().getColumn(0).setMaxWidth(60);
        tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
        tablanotas.getColumnModel().getColumn(1).setMinWidth(70);
        tablanotas.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablanotas.getColumnModel().getColumn(1).setMaxWidth(70);
        tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
        tablanotas.getColumnModel().getColumn(2).setMinWidth(50);
        tablanotas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(2).setMaxWidth(50);
        tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
        tablanotas.getColumnModel().getColumn(3).setMinWidth(80);
        tablanotas.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablanotas.getColumnModel().getColumn(3).setMaxWidth(80);
        tablanotas.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title3")); // NOI18N
        tablanotas.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title4")); // NOI18N
        tablanotas.getColumnModel().getColumn(5).setMinWidth(0);
        tablanotas.getColumnModel().getColumn(5).setPreferredWidth(0);
        tablanotas.getColumnModel().getColumn(5).setMaxWidth(0);
        tablanotas.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title5")); // NOI18N
        tablanotas.getColumnModel().getColumn(6).setMinWidth(0);
        tablanotas.getColumnModel().getColumn(6).setPreferredWidth(0);
        tablanotas.getColumnModel().getColumn(6).setMaxWidth(0);
        tablanotas.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title6")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(391, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtserie.setEditable(false);
        txtserie.setName("txtserie"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtnumero.setEditable(false);
        txtnumero.setName("txtnumero"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setName("txtFecha"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        txtobs.setEditable(false);
        txtobs.setName("txtobs"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "CODIGO", "PRODUCTO", "LAB", "UNID", "FRAC", "PVPx", "TOTAL", "MOVIMIENTO", "idguiaajustedet", "idbotica", "idguiaajuste", "tipomov", "nummov"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setMinWidth(60);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setMinWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(250);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setMinWidth(60);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setMinWidth(40);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(40);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setMinWidth(40);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(40);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setMinWidth(60);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setMinWidth(60);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(10).setMinWidth(0);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("jTable1.columnModel.title11")); // NOI18N
        jTable1.getColumnModel().getColumn(12).setMinWidth(0);
        jTable1.getColumnModel().getColumn(12).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(12).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(12).setHeaderValue(resourceMap.getString("jTable1.columnModel.title12")); // NOI18N
        jTable1.getColumnModel().getColumn(13).setMinWidth(0);
        jTable1.getColumnModel().getColumn(13).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(13).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(13).setHeaderValue(resourceMap.getString("jTable1.columnModel.title13")); // NOI18N

        txttipmov.setBackground(resourceMap.getColor("txttipmov.background")); // NOI18N
        txttipmov.setEditable(false);
        txttipmov.setFont(resourceMap.getFont("txttipmov.font")); // NOI18N
        txttipmov.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttipmov.setName("txttipmov"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtguiaajuste.setEditable(false);
        txtguiaajuste.setName("txtguiaajuste"); // NOI18N

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtserie)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(171, 171, 171)
                                .addComponent(txtguiaajuste, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtobs, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(txttipmov, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtguiaajuste, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(txttipmov, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscaVenta(String filtro) {
        filtro += '%';

        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jDateChooser1.getDate();
        fecha2 = (Date) this.jDateChooser2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            obj1 = new Venta(idbotica, filtro,fechaini,fechafin);
            listaInternos = logventa.Lista_guiasajuste(obj1);

            LimpiatTabla();

            for (int i = 0; i < listaInternos.size(); i++) {
                datosDetalle[0] = listaInternos.get(i).getidguiaajuste();
                datosDetalle[1] = listaInternos.get(i).getFecha_Registro();
                datosDetalle[2] = listaInternos.get(i).getSerie();
                datosDetalle[3] = listaInternos.get(i).getNumero();
                datosDetalle[4] = listaInternos.get(i).getobs();
                datosDetalle[5] = listaInternos.get(i).getnummov();
                datosDetalle[6] = listaInternos.get(i).getDESCRIPCION();

                model_notas.addRow(datosDetalle);
            }

            tablanotas.getSelectionModel().setSelectionInterval(0, 0);



        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA METODO Buscarguias ajuste");
        }


    }

    private void BuscaIntervaloFechas() {
        
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jDateChooser1.getDate();
        fecha2 = (Date) this.jDateChooser2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            idventa = "";

            if (idventa.length() > 0) {
                idventa += '%';
            }
            obj1 = new Venta(idbotica, idventa, fechaini, fechafin);
            EjecutatHilo obje = new EjecutatHilo(1);
            obje.start();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public class EjecutatHilo extends Thread {

        int tipbusqueda;

        public EjecutatHilo(int op) {
            tipbusqueda = op;
        }

        public void run() {
            try {

                listaInternos.removeAll(listaInternos);
                listaInternos = logventa.Lista_guiasajuste(obj1);


                if (listaInternos.size() > 0) {
                    LimpiatTabla();
                }

                for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getidguiaajuste();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Registro();
                    datosDetalle[2] = listaInternos.get(i).getSerie();
                    datosDetalle[3] = listaInternos.get(i).getNumero();
                    datosDetalle[4] = listaInternos.get(i).getobs();
                    datosDetalle[5] = listaInternos.get(i).getnummov();
                    datosDetalle[6] = listaInternos.get(i).getDESCRIPCION();


                    model_notas.addRow(datosDetalle);
                }

            } catch (Exception ex) {
                System.out.println("ERROR CAPA VISTA METODO Lista_guiasajuste " + ex.getMessage());
            }


            jLabel17.setVisible(false);
            jLabel18.setVisible(false);
        }
    }
    
    private void tablanotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanotasMouseClicked
        if (evt.getClickCount() % 2 == 0) {
            SeleccionarVenta();
        }
}//GEN-LAST:event_tablanotasMouseClicked

    private void SeleccionarVenta() {
        int fila = this.tablanotas.getSelectedRow();
        String miruc;


        if (fila >= 0 && listaInternos.size() > 0) {
            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));

            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setSelectedIndex(1);

            idguiaajuste = String.valueOf(this.tablanotas.getValueAt(fila, 0));
            txtserie.setText(listaInternos.get(fila).getSerie());
            txtnumero.setText(listaInternos.get(fila).getNumero());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha =listaInternos.get(fila).getFecha_Registro();
            txtFecha.setText(df.format(fecha));
            txtobs.setText(listaInternos.get(fila).getobs());
            txttipmov.setText(listaInternos.get(fila).getnummov());
            txtguiaajuste.setText(idguiaajuste);
            CargarProductos();
        }

    }

    private void CargarProductos() {
        String serie = txtserie.getText().trim();
        String numero = txtnumero.getText().trim();
        
        listaVenta = logventa.ListaGuia_Detalle(idguiaajuste,serie,numero);

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle_1[0] = i + 1;
            datosDetalle_1[1] = listaVenta.get(i).getId_Producto();
            datosDetalle_1[2] = listaVenta.get(i).getDescr_Producto();
            datosDetalle_1[3] = listaVenta.get(i).getId_Laboratorio();
            datosDetalle_1[4] = listaVenta.get(i).getUnidad();
            datosDetalle_1[5] = listaVenta.get(i).getFraccion();
            datosDetalle_1[6] = listaVenta.get(i).getpvpx();
            datosDetalle_1[7] = listaVenta.get(i).getTotal();
            datosDetalle_1[8] = listaVenta.get(i).getDESCRIPCION();
            datosDetalle_1[9] = listaVenta.get(i).getid_gruiaAjusteDet();
            datosDetalle_1[10] = listaVenta.get(i).getId_Botica();
            datosDetalle_1[11] = listaVenta.get(i).getid_gruiaAjuste();
            datosDetalle_1[12] = listaVenta.get(i).getid_tipomov();
            datosDetalle_1[13] = listaVenta.get(i).getnummov();

            model.addRow(datosDetalle_1);
        }

    }
    
    private void tablanotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablanotasKeyPressed
       
}//GEN-LAST:event_tablanotasKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //listarMov();
        BuscaIntervaloFechas();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);

        this.txtserie.setText("");
        this.txtnumero.setText("");
        this.txtFecha.setText("");
        this.txtobs.setText("");
        
        LimpiatTabla_Productos();
}//GEN-LAST:event_jButton6ActionPerformed

    private void LimpiatTabla_Productos() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == 127) {
            int fila = jTable1.getSelectedRow();
            if (fila >= 0) {
                EliminaProducto(fila);
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String resultado = "";
        String resultElim = "";
        String Tserie = txtserie.getText().trim();
        String Tnumero = txtnumero.getText().trim();

        listdetalleguiaajuste.removeAll(listdetalleguiaajuste);
        dellistdetalleguiaajuste.removeAll(dellistdetalleguiaajuste);

        if (jTable1.getRowCount() > 0){
            dellistdetalleguiaajuste.add(new Movimiento_Detalle("", 0, 0, "","",txtguiaajuste.getText(),"",""));
            resultElim = logventa.actualizaguiaajustedet(dellistdetalleguiaajuste,Tserie,Tnumero,"1");

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String idproducto = String.valueOf(this.jTable1.getValueAt(i, 1));
                int unida = Integer.parseInt(String.valueOf(this.jTable1.getValueAt(i, 4)));
                int fracc = Integer.parseInt(String.valueOf(this.jTable1.getValueAt(i, 5)));
                String idguiaajustedet  = String.valueOf(this.jTable1.getValueAt(i, 9));
                String bot  = String.valueOf(this.jTable1.getValueAt(i, 10));
                String idguiaajuste  = String.valueOf(this.jTable1.getValueAt(i, 11));
                String tipmov  = String.valueOf(this.jTable1.getValueAt(i, 12));
                String nunmov  = String.valueOf(this.jTable1.getValueAt(i, 13));
                
                listdetalleguiaajuste.add(new Movimiento_Detalle(idproducto, unida, fracc, idguiaajustedet,bot,
                        idguiaajuste,tipmov,nunmov));
            }

            resultado = logventa.actualizaguiaajustedet(listdetalleguiaajuste,Tserie,Tnumero,"2");

            if (resultado.equals("1")){
                JOptionPane.showMessageDialog(this, "SE ACTUALIZÓ LA INFORMACIÓN DE FORMA CORRECTA", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            }
        }



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        imprimeReporte();
    }//GEN-LAST:event_jButton4ActionPerformed


    private void imprimeReporte() {
        String sistema = "Windows";

        try {
            //con = logconex.RetornaConexion();
            //parameters.put("ALMA", jLabel12.getText().toString());
            parameters.put("idNumero", txtnumero.getText().toString().trim());
            parameters.put("idSerie", txtserie.getText().toString().trim());
            //parameters.put("Numero", jLabel14.getText().toString());
            //parameters.put("Tipo", jLabel13.getText().toString());
            parameters.put("Obs", txtobs.getText().toString());

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_GuiaDeAjuste.jasper");

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

    private void EliminaProducto(int fila) {
        int filas = tabladetalle.getRowCount();
        int cantidadValue = 0;
        if (filas > 0) {
            try {
                if (fila >= 0) {
                    //Confirmar pe = new Confirmar(objetoventana, "<html> Desea quitar el Producto : " + tabladetalle.getValueAt(fila, 2) + "  ? </html>");
                    //pe.show(true);
                    String idproducto= String.valueOf(tabladetalle.getValueAt(fila, 1));

                    //if (pe.getConfirmar() == 1) {

                        int ultposi;
                        ultposi = ((Integer) tabladetalle.getValueAt(fila, 0)).intValue();
                        ultposi--;
                        tabladetalle.removeRow(fila);

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                        cantidadproductos--;                        

                     //}

                } else {
                    JOptionPane.showMessageDialog(this, " DEBE DE SELLECIONAR UN ITEM PARA ELIMINAR ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                System.out.println("Error al seleccionar en la tabla:" + ex.toString());
            }


        } else //SI NO HAY PRODUCTOS EN LA TABLA
        {
            JOptionPane.showMessageDialog(this, "ERROR NO HAY PRODUCTOS EN EL DETALLE", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /****************************************************************
     * METODO PARA VOLVER A REORDENAR LAS CANTIDADES DE LOS PRODUCTOS
     * **************************************************************/
    private void ReordenaTabla(int ultposi) {
        try {

            for (int i = ultposi; i < jTable1.getRowCount(); i++) {
                jTable1.setValueAt(ultposi + 1, ultposi, 0);
                ultposi++;
            }

        } catch (Exception EX) {
            JOptionPane.showMessageDialog(this, "ERROR AL REORDENAR TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConsultaGuiaDeAjusteMov dialog = new ConsultaGuiaDeAjusteMov(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtguiaajuste;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtobs;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txttipmov;
    // End of variables declaration//GEN-END:variables

     

    private void LimpiatTabla() {
        int cant = tablanotas.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_notas.removeRow(i);
            }
        }
    }

    private void enviarDatosDetalle() {
        int fila = jTable1.getSelectedRow();

        String numD = jTable1.getValueAt(fila, 4).toString();
        String tipA = jTable1.getValueAt(fila, 1).toString();
        String tipM = jTable1.getValueAt(fila, 3).toString();

        numDoc.setText(numD);
        tipAlm.setText(tipA);
        tipMov.setText(tipM);



        if (fila >= 0) {

            if(cuentaDatos > 0) {
                
            }else {
                LimpiatTabla_2();
            }



            int cant = 1;
            String idbotica = this.jTable1.getValueAt(fila, 0).toString();
            String almacen = this.jTable1.getValueAt(fila, 1).toString();
            String proveedor = this.jTable1.getValueAt(fila, 2).toString();
            String documento = this.jTable1.getValueAt(fila, 4).toString();
            String IdTipoMov = this.jTable1.getValueAt(fila, 7).toString();
            
            Boticas botica = new Boticas();
            botica.setId_Botica(idbotica);
            movimiento.setBotica(botica);
            movimiento.setId_TipoAlmacen(almacen);
            movimiento.setId_Proveedor(proveedor);
            movimiento.setTipo_movimiento(IdTipoMov);
            movimiento.setNumero_Documento(documento);

            //listaDetalle.removeAll(listaDetalle);
            listaDetalle = objTipoMovimiento.Recupera_Movimiento_Detalle(movimiento);

            datosDetalle3[0] = tipM;
            datosDetalle3[1] = numD;
            modeloTabla_3.addRow(datosDetalle3);

            if (listaDetalle.size() > 0) {

                for (Movimiento_Detalle detalle : listaDetalle) {
                    datosDetalle2[0] = cant;
                    datosDetalle2[1] = detalle.getId_Producto().getIdProducto();
                    datosDetalle2[2] = detalle.getId_Producto().getDescripcion();
                    datosDetalle2[3] = detalle.getId_Producto().getLaboratorio().getId_Lab();
                    datosDetalle2[4] = detalle.getUnidad();
                    datosDetalle2[5] = detalle.getFraccion();
                    datosDetalle2[6] = detalle.getTotal();
                    cant++;
                    modeloTabla_2.addRow(datosDetalle2);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"PORFAVOR SELECCIONE UN MOVIMIENTO", "OK",JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
    }

    private void generarCabeceras_3() {
        modeloTabla_3 = new MiModelo();
        modeloTabla_3.addColumn("MOVIMIENTO");
        modeloTabla_3.addColumn("NUMERO");
        jTable3.setModel(modeloTabla_3);
//        AparienciaTabla3();
    }


    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    

    private void construirGuia() {

        modeloTabla.addColumn("BOTICA");
        modeloTabla.addColumn("ALMA");
        modeloTabla.addColumn("PROVEEDOR");
        modeloTabla.addColumn("MOVIMIENTO");
        modeloTabla.addColumn("DOCUMENTO");
        modeloTabla.addColumn("FECHA");
        modeloTabla.addColumn("RESPONSABLE");
        modeloTabla.addColumn("itTipoMov");


        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(50);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(200);
        columnaTabla.setMinWidth(200);
        columnaTabla.setMaxWidth(200);

        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);


    }

    private void generarCabeceras_2() {
        modeloTabla_2 = new MiModelo();
        modeloTabla_2.addColumn("Nº");
        modeloTabla_2.addColumn("Cod");
        modeloTabla_2.addColumn("Producto");
        modeloTabla_2.addColumn("Lab");
        modeloTabla_2.addColumn("Und");
        modeloTabla_2.addColumn("Fracc");
        modeloTabla_2.addColumn("Total");
        jTable2.setModel(modeloTabla_2);
        AparienciaTabla2();
    }

    private void AparienciaTabla2() {
        try {

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(5).setCellRenderer(tcenter);
            jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);
            DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
            tcenter1.setHorizontalAlignment(SwingConstants.RIGHT);
            jTable2.getColumnModel().getColumn(6).setCellRenderer(tcenter1);

            colu = jTable2.getColumnModel().getColumn(0);
            colu.setPreferredWidth(70);
            colu.setMinWidth(70);
            colu.setMaxWidth(70);
            colu = jTable2.getColumnModel().getColumn(1);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(2);
            colu.setPreferredWidth(380);
            colu.setMinWidth(380);
            colu.setMaxWidth(380);
            colu = jTable2.getColumnModel().getColumn(3);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(4);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(5);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(6);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

        } catch (Exception ex) {
            System.out.println("ERRORrrrrr :" + ex.getMessage());
        }

    }

    private void LimpiatTabla_2() {
        int cant = this.jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modeloTabla_2.removeRow(i);
            }
        }
    }


}
