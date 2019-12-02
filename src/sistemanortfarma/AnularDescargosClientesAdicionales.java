package sistemanortfarma;

import CapaDatos.VentaData;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaTipoMovimiento;
import CapaLogica.LogicaVenta;
import entidad.Boticas;
import entidad.Guias;
import CapaLogica.LogicaFechaHora;
import java.util.Date;
import entidad.TipoDocumento;
import entidad.Venta;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


import java.awt.Color;
import java.awt.Component;
import java.io.BufferedWriter;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import CapaLogica.LogicaConexion;
import CapaLogica.LogicaIGV;
import CapaLogica.Numero_a_Letra;
import java.sql.ResultSet;
import java.text.DecimalFormat;


import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterLocation;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;


/**
 *
 * @author Miguel Gomez S.
 */
public class AnularDescargosClientesAdicionales extends javax.swing.JInternalFrame {

    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    //ModeloTabla modeloTabla;
    private DefaultTableModel model_pre;
    private DefaultTableModel model_pre_1;
    TableColumnModel colModel_pre;
    TableColumnModel colModel_pre_1;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Connection conex;
    LogicaConexion logconex = new LogicaConexion();
    TableColumn columnaTabla = null;
    List<Venta> listaTipoMovimiento;
    List<Venta> listaTipoMovimiento_1;
    Inventarios objinventario;
    List<Venta> listaDocImprimir;
    List<Venta> listaDocImprimirDetalle;    
    List<Boticas> listaboticas;
    ResultSet rs, rs1, rs2, rs3, rsigv, rsvalida, rs4;
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    private String ImpresoraDesc="";
    List<Boticas> listaboticas2;
    Object[] datosDetalle_pre = new Object[6];
    Object[] datosDetalle_pre_1 = new Object[7];
    Object[] datos_impre = new Object[6];
    List<Guias> listguias;
    JCheckBox checkPromocion = new JCheckBox();
    OpcionesMenu objet;
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    LogicaVenta objVentas = new LogicaVenta();
    List<TipoDocumento> listventas;
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido = false;
    Integer totalLetras = 0;
    Integer a = 0;
    DetalleMovimiento objrelew;
    private String Impresora = "";
    public static String NumeroDocumento;
    public static String FechaDocumento;
    public static String TipoMovimiento;
    public static String Observaciones;
    private static String idbotica;
    private static String tipo_almacen;
    private static String Id_tipovta;
    private int opcion;
    private int opc_aux;
    private int idtipoventa;
    private JComponent component = new JCheckBox();    
    private boolean value = false; // valor de la celda
    //AplicacionVentas objventas;
    Inventarios objventas;
    
    Map parameters = new HashMap();
    VerificaSistema obj1;
    URL report_file;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;
    
    
    

    public static String getTipo_almacen() {
        return tipo_almacen;
    }

    public static void setTipo_almacen(String tipo_almacen) {
        AnularDescargosClientesAdicionales.tipo_almacen = tipo_almacen;
    }

    public static String getFechaDocumento() {
        return FechaDocumento;
    }

    public static void setFechaDocumento(String FechaDocumento) {
        AnularDescargosClientesAdicionales.FechaDocumento = FechaDocumento;
    }

    public static String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public static void setNumeroDocumento(String NumeroDocumento) {
        AnularDescargosClientesAdicionales.NumeroDocumento = NumeroDocumento;
    }

    public static String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public static void setTipoMovimiento(String TipoMovimiento) {
        AnularDescargosClientesAdicionales.TipoMovimiento = TipoMovimiento;
    }

    public static String getObservaciones() {
        return Observaciones;
    }

    public static void setObservaciones(String Observaciones) {
        AnularDescargosClientesAdicionales.Observaciones = Observaciones;
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        AnularDescargosClientesAdicionales.idbotica = idbotica;
    }

    public static String getId_tipovta() {
        return Id_tipovta;
    }

    public static void setId_tipovta(String Id_tipovta) {
        AnularDescargosClientesAdicionales.Id_tipovta = Id_tipovta;
    }

    /** Creates new form Anulaciones */  

    
    public AnularDescargosClientesAdicionales(Inventarios obj,OpcionesMenu objeto, int opc) {
        
        //modeloTabla = new ModeloTabla();
        objet = objeto;        
        Impresora = objet.getImpresora_Factura();
        ImpresoraDesc = objet.getImpresora_Descargo();
        idbotica = objet.getIdbotica();        
        opcion = opc;
        
        initComponents();
        model_pre = (DefaultTableModel) this.jTable2.getModel();
        colModel_pre = jTable2.getColumnModel();
        
        model_pre_1 = (DefaultTableModel) this.jTable3.getModel();
        colModel_pre_1 = jTable3.getColumnModel();
        construirCabecera();
        AparienciaTabla();
            
        ListarMovimientosDescargosBoticas("");
        ListarMovimientosDescargosBoticas_1("");
        
       
        
        txtND.requestFocus();
        txtND1.setEditable(false);
        
        if (opcion == 1){
            txtND1.setText("DESCARGO BOTICAS");
        }else if (opcion == 2){
            txtND1.setText("DESCARGO CLIENTES");
        }else if (opcion == 3){
            txtND1.setText("DESCARGO ADICIONALES");
        }else{
            txtND1.setText("ANULAR DESCARGOS CLIENTES / ADICIONALES");
        }

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable2.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtND = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton2 = new javax.swing.JButton();
        txtND1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(AnularDescargosClientesAdicionales.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);

        jLabel9.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtND.setText(resourceMap.getString("txtND.text")); // NOI18N
        txtND.setName("txtND"); // NOI18N
        txtND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNDActionPerformed(evt);
            }
        });
        txtND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNDKeyReleased(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtND1.setBackground(resourceMap.getColor("txtND1.background")); // NOI18N
        txtND1.setFont(resourceMap.getFont("txtND1.font")); // NOI18N
        txtND1.setForeground(resourceMap.getColor("txtND1.foreground")); // NOI18N
        txtND1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtND1.setName("txtND1"); // NOI18N
        txtND1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtND1ActionPerformed(evt);
            }
        });
        txtND1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtND1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtND1KeyReleased(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descargos para Clientes", "Descargos Adicionales" }));
        jComboBox2.setFocusable(false);
        jComboBox2.setName("jComboBox2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtND1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtND1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel9)
                        .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))))
        );

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(80, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Botica", "Descripcion", "Documento", "Fecha", "Usuario/Cliente", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setColumnSelectionAllowed(true);
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
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(230);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(180);
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(230);
            jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
        }

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(80, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Botica", "Descripcion", "Documento", "Fecha", "Usuario/Cliente", "Estado", "Anulado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable3.setColumnSelectionAllowed(true);
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionBackground(resourceMap.getColor("jTable3.selectionBackground")); // NOI18N
        jTable3.setSelectionForeground(resourceMap.getColor("jTable3.selectionForeground")); // NOI18N
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable3ComponentShown(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable3.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(230);
            jTable3.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(180);
            jTable3.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(230);
            jTable3.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable3.getColumnModel().getColumn(5).setResizable(false);
            jTable3.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
            jTable3.getColumnModel().getColumn(6).setResizable(false);
            jTable3.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable3.columnModel.title6")); // NOI18N
        }

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDKeyReleased
    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jButton1.setEnabled(false);
        
        int cont =0;  /* Var Auxiliar*/
        Boolean checked = false;
        String col="";
        ArrayList<String> selection = new ArrayList<String>();
   
        for(int sel= 0; sel < jTable2.getRowCount(); sel++){
           
           checked = Boolean.valueOf((Boolean)jTable2.getValueAt(sel, 5));
           col = (String) jTable2.getValueAt(sel, 2);
           
           if (checked){                
                selection.add(col);   
                cont++;
           }
           
           
        }
       
        if(cont==0){ /* si es 0 , es porque no hay opciones marcadas*/
            JOptionPane.showMessageDialog(null, "MARQUE UNA OPCION");
            this.jButton1.setEnabled(true);
            return;
        }else{            
            //System.out.println(col);
            for(int sel1=0; sel1 < selection.size(); sel1++){
                
                String doc = selection.get(sel1);
                //Imprimir_Descargos(doc);
                AnulaDescargos(doc);
                buscar();
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed
   
  
    
    public void AnulaDescargos(String doc) {
        int anula = 0;
        
        if (jComboBox2.getSelectedIndex() == 0) {            
            opc_aux = 1; // desc clientes
        }else{            
            opc_aux = 2; // desc Adicionales
        }
        
        int reply = JOptionPane.showConfirmDialog(null, "Seguro de Anular", "NORTFARMA", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                anula = objTipoMovimiento.AnulaDescargoAdicionales(idbotica,doc,opc_aux);
            }
        
            
        if (anula == 1){
            JOptionPane.showMessageDialog(null, "SU MOVIMIENTO HA SIDO ANULADO", "DESCARGO ANULADO", JOptionPane.INFORMATION_MESSAGE);
           this.jButton1.setEnabled(true);    
        }    
            
          
    }
    
    
    
    
    private void txtNDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDKeyPressed
    }//GEN-LAST:event_txtNDKeyPressed

    private void txtNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNDActionPerformed

        /*for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(seg);
        }
        listaTipoMovimiento.removeAll(listaTipoMovimiento);
        ListarMovimientosDescargosBoticas("");*/

    }//GEN-LAST:event_txtNDActionPerformed

    public void limpiatabla(){
        DefaultTableModel tb = (DefaultTableModel) jTable2.getModel();
        int a = jTable2.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
        tb.removeRow(tb.getRowCount()-1);
        }
        
        DefaultTableModel tb_3 = (DefaultTableModel) jTable3.getModel();
        int a1 = jTable3.getRowCount()-1;
        for (int ix = a1; ix >= 0; ix--) {          
        tb_3.removeRow(tb_3.getRowCount()-1);
        }
        
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:              
        buscar();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buscar(){
        listaTipoMovimiento.clear();
        listaTipoMovimiento_1.clear();
        limpiatabla();
        String doc = this.txtND.getText().trim();
        doc = doc + '%';
        ListarMovimientosDescargosBoticas(doc);
        ListarMovimientosDescargosBoticas_1(doc);
    }
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed

    }//GEN-LAST:event_jTable2KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        objinventario.marcacdo = false;
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtND1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtND1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtND1ActionPerformed

    private void txtND1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtND1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtND1KeyPressed

    private void txtND1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtND1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtND1KeyReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3KeyPressed

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable3KeyReleased

    private void jTable3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable3ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3ComponentShown

    

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    private void ListarMovimientosDescargosBoticas(String filtro) {
                
        filtro += '%';
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);
        String var = "0";
        
        if (jComboBox2.getSelectedIndex() == 0) {
            //opcion = 2;
            opc_aux = 2;
        }else{
            //opcion= 7;
            opc_aux = 7;
        }
       
        /*if (opcion == 1){
            opc_aux = 1;
        }else if (opcion == 2){
            opc_aux = 2;
        }else{
            opc_aux = 7;
        }*/
        
        listaTipoMovimiento = objTipoMovimiento.Lista_DescargosBoticas(idbotica, filtro,fechaini,fechafin, opc_aux);

        if (listaTipoMovimiento.size() > 0) {
            for (Integer i = 0; i < listaTipoMovimiento.size(); i++) {
                    
                        datosDetalle_pre[0] = listaTipoMovimiento.get(i).getId_Botica();
                        datosDetalle_pre[1] = listaTipoMovimiento.get(i).getDESCRIPCION(); 
                        datosDetalle_pre[2] = listaTipoMovimiento.get(i).getNumdocumento();
                        datosDetalle_pre[3] = listaTipoMovimiento.get(i).getFecha_Registro(); 
                        datosDetalle_pre[4] = listaTipoMovimiento.get(i).getUsuario();
                        //datosDetalle_pre[5] = listaTipoMovimiento.get(i).getEstado(); 
                        
                        if (listaTipoMovimiento.get(i).getEstado() == 1) {
                            checkPromocion.setSelected(true);
                            datosDetalle_pre[5] = true;
                            
                        } else {
                            checkPromocion.setSelected(false);
                            datosDetalle_pre[5] = false;
                        }
                        
                    
                    
                    //modeloTabla.addRow(data[0]);
                    //modeloTabla.addRow(datosDetalle_pre);
                    model_pre.addRow(datosDetalle_pre);
                

            }

            //jTable1.setModel(modeloTabla);            
            
            jTable2.setRowSelectionAllowed(true);
            jTable2.setColumnSelectionAllowed(false);            

        }
        
        
        
    }
    
    private void ListarMovimientosDescargosBoticas_1(String filtro) {
                
        filtro += '%';
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);
        String var = "0";
        
        if (jComboBox2.getSelectedIndex() == 0) {
            //opcion = 6;
            opc_aux = 11;
        }else{
            //opcion= 8;
            opc_aux = 12;
        }
       
        /*if (opcion == 1){
            opc_aux = 5;
            
        }else if (opcion == 2){
            opc_aux = 6;
            
        }else{
            opc_aux = 8;
        }*/
        listaTipoMovimiento_1 = objTipoMovimiento.Lista_DescargosBoticas_1(idbotica, filtro,fechaini,fechafin,opc_aux);

        if (listaTipoMovimiento_1.size() > 0) {
            for (Integer i = 0; i < listaTipoMovimiento_1.size(); i++) {
                    
                        datosDetalle_pre_1[0] = listaTipoMovimiento_1.get(i).getId_Botica();
                        datosDetalle_pre_1[1] = listaTipoMovimiento_1.get(i).getDESCRIPCION(); 
                        datosDetalle_pre_1[2] = listaTipoMovimiento_1.get(i).getNumdocumento();
                        datosDetalle_pre_1[3] = listaTipoMovimiento_1.get(i).getFecha_Registro(); 
                        datosDetalle_pre_1[4] = listaTipoMovimiento_1.get(i).getUsuario();                         
                        
                        if (listaTipoMovimiento_1.get(i).getEstado() == 1) {
                            checkPromocion.setSelected(true);
                            datosDetalle_pre_1[5] = true;
                            
                        } else {
                            checkPromocion.setSelected(false);
                            datosDetalle_pre_1[5] = false;
                        }
                        
                        datosDetalle_pre_1[6] = listaTipoMovimiento_1.get(i).getAnulado();
                        
                        
                    
                    
                    //modeloTabla.addRow(data[0]);
                    //modeloTabla.addRow(datosDetalle_pre);
                    model_pre_1.addRow(datosDetalle_pre_1);
                    

            }
            jTable3.setRowSelectionAllowed(true);
            jTable3.setColumnSelectionAllowed(false);
            //jTable1.setModel(modeloTabla);  
            
            revisa();
        }
        
        
        
    }
    
    private void revisa(){
        ColorearFilas color = new ColorearFilas(6);
        jTable3.getColumnModel().getColumn(6).setCellRenderer(color);
        //jTable3.setDefaultRenderer(Object.class, (TableCellRenderer) new ColorDefaultCellRenderer(6));
        
        
        
        
        
    }
    private void construirCabecera() {

        
    }

    

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    }
    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JTextField txtND;
    private javax.swing.JTextField txtND1;
    // End of variables declaration//GEN-END:variables
}
