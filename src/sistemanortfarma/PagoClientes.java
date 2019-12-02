package sistemanortfarma;

import CapaLogica.LogicaClientes;
import CapaLogica.LogicaConexion;
import entidad.Clientes;
import entidad.TiposPagos;
import entidad.Venta;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import entidad.Cajas;
import CapaLogica.LogicaRecuperaCaja;

/**
 *
 * @author Miguel Gomez S.
 */
public class PagoClientes extends javax.swing.JInternalFrame {

    private String idbotica;
    private int idpersonal;
    private DefaultTableModel modelClientes;
    LogicaClientes objcliente = new LogicaClientes();
    String[] datosDetalle = new String[3];
    private DefaultTableModel modelInternos;
    private Object[] datos = new Object[7];
    List<Venta> ListaInternos;
    List<Clientes> listacleintes = new ArrayList<Clientes>();
    private String CodigoCliente;
    private int Id_Cliente;
    private String RUC_DNI;
    private String Nombre_RazonSocial;
    private String Direccion;
    private String DNI;
    private Object[] datos_pagos = new Object[4];
    private int cantidadpagos = 0;
    private DefaultTableModel modelopagos;
    private double totaldeduda;
    private Object[] datos_notas_credito = new Object[4];
    private DefaultTableModel modelnotas_credito;
    private List<Venta> lista_Pagos = new ArrayList<Venta>();//DONDE GUARDO LOS PAGOS DEL CLIENTE
    private List<Venta> Detallelista_Pagos = new ArrayList<Venta>();
    private int cantpagadas = 0;
    AplicacionVentas objventas;
    public static Connection con = null;
    LogicaConexion logconex = new LogicaConexion();
    RequisitosFactura objfactura = new RequisitosFactura();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    private int idcajero;
    private int idcaja;
    TableColumn col;
    List<Venta> lista_Notas_Credito = new ArrayList<Venta>();//lista de notas de credito
    List<String> pintado = new ArrayList<String>();
    VerificaSistema obje;

    public PagoClientes(String idboti, AplicacionVentas obj) {
        initComponents();
        objventas = obj;
        idbotica = idboti;
        idpersonal = objventas.getIdpesonal();
        modelClientes = (DefaultTableModel) jTable1.getModel();
        modelInternos = (DefaultTableModel) jTable2.getModel();
        modelnotas_credito = (DefaultTableModel) jTable3.getModel();
        modelopagos = (DefaultTableModel) jTable4.getModel();
        AparienciaTabla();
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        RecuperaTiposPagos();
        jLabel12.setText("  ");

        jTable2.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(7).setMinWidth(0);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(0);

    }

    private void RecuperaTiposPagos() {
        JComboBox comboBox = new JComboBox();
        listatipospagos = objfactura.Recupera_Tipos_Pagos(2);

        for (int i = 0; i < listatipospagos.size(); i++) {
            String descrp = listatipospagos.get(i).getDescripcion();
            comboBox.addItem(descrp);

            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
            jTable4.getColumnModel().getColumn(1).setCellEditor(defaultCellEditor);
        }

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
        jTable1.setTableHeader(cabecera);

        JTableHeader cabecera1 = new JTableHeader(this.jTable2.getColumnModel());
        cabecera1.setReorderingAllowed(false);
        jTable2.setTableHeader(cabecera1);

        JTableHeader cabecera2 = new JTableHeader(this.jTable4.getColumnModel());
        cabecera2.setReorderingAllowed(false);
        jTable4.setTableHeader(cabecera2);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.LEFT);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(tcr);

        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(tcr1);

        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable3.getColumnModel().getColumn(3).setCellRenderer(tcr2);



    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton12 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton14 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(PagoClientes.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Razon Social", "RUC", "Codigo" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jToolBar2.setBackground(resourceMap.getColor("jToolBar2.background")); // NOI18N
        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setToolTipText(resourceMap.getString("jButton7.toolTipText")); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton7);

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setToolTipText(resourceMap.getString("jButton9.toolTipText")); // NOI18N
        jButton9.setEnabled(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton9);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar2.add(jSeparator5);

        jButton12.setIcon(resourceMap.getIcon("jButton12.icon")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setToolTipText(resourceMap.getString("jButton12.toolTipText")); // NOI18N
        jButton12.setEnabled(false);
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton12);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar2.add(jSeparator6);

        jSeparator7.setName("jSeparator7"); // NOI18N
        jToolBar2.add(jSeparator7);

        jButton14.setIcon(resourceMap.getIcon("jButton14.icon")); // NOI18N
        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setToolTipText(resourceMap.getString("jButton14.toolTipText")); // NOI18N
        jButton14.setEnabled(false);
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton14);

        jButton13.setIcon(resourceMap.getIcon("jButton13.icon")); // NOI18N
        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setToolTipText(resourceMap.getString("jButton13.toolTipText")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton13);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jTextField5.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField5.setEditable(false);
        jTextField5.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        jTextField6.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField6.setEditable(false);
        jTextField6.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N

        jTabbedPane1.setFont(resourceMap.getFont("jTabbedPane1.font")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interno", "Serie", "Numero", "Documento", "Fecha", "Hora", "Monto", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(68);
        jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title8")); // NOI18N
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
        jTable2.getColumnModel().getColumn(4).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(75);
        jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable2.getColumnModel().getColumn(5).setResizable(false);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
        jTable2.getColumnModel().getColumn(6).setResizable(false);
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
        jTable2.getColumnModel().getColumn(7).setResizable(false);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(15);
        jTable2.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setFont(resourceMap.getFont("jTable3.font")); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Documento", "Interno", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionBackground(resourceMap.getColor("jTable3.selectionBackground")); // NOI18N
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable3.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable3.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
        jTable3.getColumnModel().getColumn(3).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable3.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setEnabled(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(resourceMap.getIcon("jButton11.icon")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setEnabled(false);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable4.setFont(resourceMap.getFont("jTable4.font")); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Modo", "Numero", "Abono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setName("jTable4"); // NOI18N
        jTable4.setSelectionBackground(resourceMap.getColor("jTable4.selectionBackground")); // NOI18N
        jTable4.setSelectionForeground(resourceMap.getColor("jTable4.selectionForeground")); // NOI18N
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable4ComponentAdded(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable4KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getColumn(0).setResizable(false);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTable4.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable4.columnModel.title0")); // NOI18N
        jTable4.getColumnModel().getColumn(1).setResizable(false);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable4.columnModel.title1")); // NOI18N
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable4.columnModel.title2")); // NOI18N
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable4.columnModel.title3")); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(jTextField4)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(14, 14, 14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10))
        );

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "RUC", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Lista_Notas_Credito(int miidcliente) {

        lista_Notas_Credito = objcliente.Lista_Notas_Credito(idbotica, miidcliente);
        for (int i = 0; i < lista_Notas_Credito.size(); i++) {
            datos_notas_credito[0] = lista_Notas_Credito.get(i).getFecha_Registro();
            datos_notas_credito[1] = lista_Notas_Credito.get(i).getNumero();
            datos_notas_credito[2] = lista_Notas_Credito.get(i).getId_Venta();
            datos_notas_credito[3] = lista_Notas_Credito.get(i).getTotal();
            modelnotas_credito.addRow(datos_notas_credito);
        }
    }

    private void PintaTabla() {
    }

    private void Muestra_DeudasCliente() {

        if (listacleintes.size() > 0) {
            String clie = listacleintes.get(this.jTable1.getSelectedRow()).getNombre_RazonSocial();
            Id_Cliente = listacleintes.get(this.jTable1.getSelectedRow()).getId_Cliente();
            Lista_Notas_Credito(Id_Cliente);
            BuscaDeudas(Id_Cliente);
            PintaTabla();
            this.jLabel9.setVisible(true);
            this.jLabel10.setVisible(true);
            DesabilitaBusqueda(false);
            jTextField3.setText(clie);
            this.jButton7.setEnabled(true);
            this.jButton9.setEnabled(true);
            this.jButton14.setEnabled(true);
        } else {
            LimpiatTabla_1();
            LimpiatTabla_3();
            LimpiaData();
        }
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Muestra_DeudasCliente();
        } else {
            VisualizaCliente(this.jTable1.getSelectedRow());
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        Buscaliente();
        this.jTable1.requestFocus();
        this.jTable1.getSelectionModel().setSelectionInterval(0, 0);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Buscaliente();
        this.jTable1.requestFocus();
        this.jTable1.getSelectionModel().setSelectionInterval(0, 0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Muestra_DeudasCliente();
        }

        VisualizaCliente(this.jTable1.getSelectedRow());
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable4ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable4ComponentAdded
}//GEN-LAST:event_jTable4ComponentAdded

    private void jTable4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyPressed
}//GEN-LAST:event_jTable4KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.jButton7.setEnabled(false);
        this.jTable4.setEnabled(true);
        AgregaItemPago();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        AgregaItemPago();
        this.jButton10.setEnabled(true);
}//GEN-LAST:event_jButton8ActionPerformed

    private void Limpia() {
        DesabilitaBusqueda(true);
        cantpagadas = 0;
        this.jLabel12.setText("  ");
        ListaInternos.removeAll(ListaInternos);
        lista_Pagos.removeAll(lista_Pagos);
        Detallelista_Pagos.removeAll(Detallelista_Pagos);
        pintado.removeAll(pintado);
        this.lista_Notas_Credito.retainAll(lista_Notas_Credito);
        totaldeduda = 0;
        this.LimpiatTabla();
        this.LimpiatTabla_1();
        LimpiatTabla_2();
        this.jTextField1.requestFocus();
        this.jButton7.setEnabled(false);
        this.jButton9.setEnabled(false);
        this.jButton8.setEnabled(false);
        this.jButton10.setEnabled(false);
        this.jButton11.setEnabled(false);
        this.jLabel10.setVisible(false);
        this.jButton14.setEnabled(false);
        this.jLabel9.setVisible(false);
        this.jButton12.setEnabled(false);
        LimpiaData();
        LimpiatTabla_3();
        this.jTextField1.setText("");

    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if (this.jTable2.getRowCount() > 0) {
            int response = JOptionPane.showConfirmDialog(null, "Deseas Realizar una Nueva Busqueda ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                Limpia();
            }

        } else {
            Limpia();
        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int fila = this.jTable4.getSelectedRow();

        try {

            if (fila >= 0) {
                int reply = JOptionPane.showConfirmDialog(null, "Deseas eliminar este item ?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    cantidadpagos--;
                    modelopagos.removeRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes de seleecionar un item de Pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception ex) {
        }

}//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //CALCULO LOS MONTOS INGRESADOS
        Venta p = null;
        Venta detalle;
        double TotalPagado = 0;
        boolean ok = true;
        double valor1 = 0;
        double valorventa;

        double resto = 0;

        int response = JOptionPane.showConfirmDialog(null, "Desea Cerrar Item de Ventas ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {

            for (int i = 0; i < this.jTable4.getRowCount(); i++) {

                double valor = Double.parseDouble(modelopagos.getValueAt(i, 3).toString());
                String doc = modelopagos.getValueAt(i, 2).toString();
                String idpago = modelopagos.getValueAt(i, 1).toString();

                if ((idpago.compareToIgnoreCase("Cheque") == 0 || idpago.compareToIgnoreCase("Abono") == 0) && doc.compareTo("") == 0) {
                    JOptionPane.showMessageDialog(this, "Error : Debes de Ingresar el numero de "+ idpago, "Error", JOptionPane.ERROR_MESSAGE);
                    ok = false;
                    lista_Pagos.removeAll(lista_Pagos);
                    break;

                } else {
                    if (valor > 0) {
                        TotalPagado = TotalPagado + valor;
                        p = new Venta(idbotica, this.jTable4.getValueAt(i, 1).toString(), valor, doc);
                        lista_Pagos.add(p);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error : No Puedes Ingresar un Monto Cero ", "Error", JOptionPane.ERROR_MESSAGE);
                        ok = false;
                        lista_Pagos.removeAll(lista_Pagos);
                        break;
                    }

                }

            }

            if (ok) {

                this.jLabel7.setVisible(true);
                this.jLabel8.setVisible(true);

                BigDecimal bd = new BigDecimal(TotalPagado);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                TotalPagado = bd.doubleValue();

                this.jLabel8.setText(String.valueOf(TotalPagado));
                this.jTable4.setEnabled(false);
                this.jButton8.setEnabled(false);
                this.jButton10.setEnabled(false);
                this.jButton11.setEnabled(false);
                this.jButton12.setEnabled(true);

                for (int i = 0; i < this.jTable2.getRowCount(); i++) {

                    valorventa = Double.parseDouble(this.jTable2.getValueAt(i, 6).toString());
                    String idventa = this.jTable2.getValueAt(i, 0).toString();
                    String fechavta = this.jTable2.getValueAt(i, 4).toString();
                    valor1 = valor1 + valorventa;

                    BigDecimal bd1 = new BigDecimal(valor1);
                    bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                    valor1 = bd1.doubleValue();

                    resto = TotalPagado - valor1;

                    BigDecimal bd3 = new BigDecimal(resto);
                    bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
                    resto = bd3.doubleValue();

                    if (TotalPagado >= valor1) {
                        this.jTable2.setValueAt(true, i, 7);
                        detalle = new Venta(idventa, valorventa,fechavta);
                        Detallelista_Pagos.add(detalle);
                    } else {
                        if (resto != 0) {
                            detalle = new Venta(idventa, valorventa,fechavta);
                            Detallelista_Pagos.add(detalle);
                        } else {
                            if (resto == 0 && TotalPagado > 0) {
                                detalle = new Venta(idventa, valorventa,fechavta);
                                Detallelista_Pagos.add(detalle);
                            }

                        }

                        break;
                    }

                }

            }

        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
        LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
        //List<Cajas> listapersonal = objcajas.VERIFICA_CAJAS_USUARIOS(idbotica, objventas.getIdpesonal());
        List<Cajas> listapersonal = objcajas.VERIFICA_CAJAS_USUARIOS1(idbotica, objventas.getIdpesonal());

        IngreseCajeroPago obj = new IngreseCajeroPago(this, idbotica,listapersonal);
        obj.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    public void Guarda(int idpersonal, int idcajaseleccionada) {
        if (lista_Pagos.size() > 0) {

            idcajero = idpersonal;
            idcaja = idcajaseleccionada;
            GuardaPagos();

        }
    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int response = JOptionPane.showConfirmDialog(null, " Desea Cerrar Esta Ventana ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            objventas.marcacdo = false;
            this.dispose();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        if (con == null) {
            con = logconex.RetornaConexion();
        }
        Muestra_Reporte();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void Muestra_Reporte() {
        try {

            JasperPrint jasperPrint;
            Map parameters = new HashMap();
            JasperReport masterReport = null;
            String report_file;
            URL archivo;
            String sistema = "Windows";

            parameters.put("IDBOTICA", idbotica);
            parameters.put("IDCLIENTE", Id_Cliente);
            parameters.put("IDPERSONAL", idpersonal);
            parameters.put("IDCAJA", idcaja);

            archivo = this.getClass().getResource("/Reportes/Reporte_Pagos_Clientes.jasper");

            if (obje.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }


            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("Comprobante");
            view.setVisible(true);
            view.setSize(750, 550);

        } catch (JRException ex) {
            Logger.getLogger(PagoClientes.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void GuardaPagos() {
        
            boolean resultado = objcliente.Guarda_Pagos_Credito(lista_Pagos, Detallelista_Pagos, idbotica, idcajero, Id_Cliente,idcaja);

            if (resultado) {
                JOptionPane.showMessageDialog(this, " Pagos del Cliente Guardados Correctamente ", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                objventas.marcacdo = false;
                this.dispose();
                if (con == null) {
                    con = logconex.RetornaConexion();
                }
                Muestra_Reporte();

            } else {
                JOptionPane.showMessageDialog(this, "LO SENTIMOS : Error al Guardar sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            }        

    }

    private void AgregaItemPago() {
        cantidadpagos++;
        datos_pagos[0] = cantidadpagos;
        datos_pagos[1] = "";
        datos_pagos[2] = "";
        datos_pagos[3] = 0.0;

        modelopagos.addRow(datos_pagos);
        this.jButton8.setEnabled(true);
        this.jButton10.setEnabled(true);
        this.jButton11.setEnabled(true);
        DesabilitaBusqueda(false);
    }

    private void DesabilitaBusqueda(boolean valor) {
        this.jComboBox1.setEnabled(valor);
        this.jTextField1.setEnabled(valor);
        this.jButton1.setEnabled(valor);
        this.jTable1.setEnabled(valor);
    }

    private void VisualizaCliente(int fila) {
        if (fila >= 0) {
            if (listacleintes.size() > 0) {
                this.jTextField2.setText(String.valueOf(listacleintes.get(fila).getId_Cliente()));
                this.jTextField3.setText(String.valueOf(listacleintes.get(fila).getNombre_RazonSocial()));
                this.jTextField4.setText(String.valueOf(listacleintes.get(fila).getDireccion()));
                this.jTextField5.setText(String.valueOf(listacleintes.get(fila).getRUC_DNI()));
                this.jTextField6.setText(String.valueOf(listacleintes.get(fila).getDNI()));
            }
        }
    }

    private void LimpiaData() {
        this.jTextField2.setText("");
        this.jTextField3.setText("");
        this.jTextField4.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
    }

    private void BuscaDeudas(int idcliente) {

        try {

            jLabel12.setText("  ");
            ListaInternos = objcliente.Lista_Deudas_Clientes(idbotica, idcliente);

            if (ListaInternos.size() > 0) {
                LimpiatTabla_1();

                int tamaño = lista_Notas_Credito.size();

                for (int i = 0; i < ListaInternos.size(); i++) {
                    {
                        double monto = ListaInternos.get(i).getTotal();

                        if (i < tamaño) {
                            if (lista_Notas_Credito.get(i).getId_Venta().compareTo(ListaInternos.get(i).getId_Venta()) == 0) {
                                this.pintado.add(ListaInternos.get(i).getId_Venta());
                                col = jTable2.getColumnModel().getColumn(0);
                                col.setCellRenderer(new ColoredTableCellRenderer());
                            } else {
                                pintado.add("999999");
                            }

                        } else {
                            pintado.add("999999");
                        }


                        BigDecimal bd = new BigDecimal(monto);
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                        monto = bd.doubleValue();

                        if (monto > 0) {
                            datos[0] = ListaInternos.get(i).getId_Venta();
                            datos[1] = ListaInternos.get(i).getSerie();
                            datos[2] = ListaInternos.get(i).getNumero();
                            datos[3] = ListaInternos.get(i).getDESCRIPCION();
                            datos[4] = ListaInternos.get(i).getFecha_Registro();
                            datos[5] = ListaInternos.get(i).getHora_Venta();
                            datos[6] = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                            totaldeduda = totaldeduda + monto;
                            modelInternos.addRow(datos);

                        }
                    }
                }

                BigDecimal bd = new BigDecimal(totaldeduda);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                totaldeduda = bd.doubleValue();

                this.jLabel10.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            } else {
                this.jLabel12.setText("CLIENTE SIN DEUDAS");
            }


        } catch (Exception ex) {
            System.out.println("BuscaDeudas " + ex.getMessage());
        }


    }

    private void Buscaliente() {
        int flag = 0;

        flag = this.jComboBox1.getSelectedIndex();
        String campo = this.jTextField1.getText().trim();

        switch (flag) {
            case 0:
                busqueda(campo);
                break;
        }
    }

    private void busqueda(String valor) {
        valor = valor + '%';

        listacleintes = objcliente.ListaClientes1(valor, idbotica);

        if (listacleintes.size() > 0) {
            LimpiatTabla();

            for (int i = 0; i < listacleintes.size(); i++) {
                RUC_DNI = listacleintes.get(i).getRUC_DNI();
                Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                Direccion = listacleintes.get(i).getDireccion();

                datosDetalle[0] = Nombre_RazonSocial;
                datosDetalle[1] = RUC_DNI;
                datosDetalle[2] = Direccion;

                if (datosDetalle.toString().compareTo("") != 0) {
                    modelClientes.addRow(datosDetalle);
                }
            }
        }

    }

    private void LimpiatTabla_1() {
        int cant = this.jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelInternos.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_3() {
        int cant = this.jTable3.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelnotas_credito.removeRow(i);
            }
        }
    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelClientes.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_2() {
        int cant = this.jTable4.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelopagos.removeRow(i);
            }
        }
    }

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            setBackground(Color.white);
            int tam = pintado.size();

            for (int i = 0; i < tam; i++) {
                String miventa = table.getValueAt(row, column).toString().trim();

                if (pintado.get(i).compareTo(miventa) == 0) {
                    setBackground(Color.PINK);
                }
            }

            super.getTableCellRendererComponent(table, value, false, false, 5, column);


            return this;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
