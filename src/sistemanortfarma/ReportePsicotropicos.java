/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlTransacciones.java
 *
 * Created on 15/12/2011, 07:16:14 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProveedor;
import CapaLogica.LogicaTipoMovimiento;
import entidad.Boticas;
import entidad.Guias;
import entidad.ProductosPrecios;
import entidad.Producto;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import java.net.URL;
import java.util.Date;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Miguel Gomez S.
 */
public class ReportePsicotropicos extends javax.swing.JInternalFrame implements Runnable {

    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento;
    List<Proveedor> listaProveedor;
    List<Boticas> listaProductos;
    List<Boticas> listaboticas2;
    List<Varios> Stocks;
    Guias entidadGuias = new Guias();
    Producto entidadProductos = new Producto();
    LogicaFechaHora objHora = new LogicaFechaHora();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    String user;
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    public static String ProductoRecibido;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    boolean pasaPorStock = true;
    JasperPrint jasperPrint;
    Connection con;
    VerificaSistema obj;
    Map parameters = new HashMap();
    String report_file;
    JasperReport masterReport = null;
    List<ProductosPrecios> listproducto = new ArrayList();
    List<Boticas> listBoticas = new ArrayList();
    LogicaBoticas objBotica = new LogicaBoticas();
    ReportesInventarios objreporte;
    public DefaultTableModel modelotabla;
    Integer fila = 0;
    String idbotica;
    int cargar;
    /** Creates new form ControlTransacciones */
    Thread showThread;
    private URL archivo;
    VerificaSistema objsistema;
    private String almacen="";
    private Date fecha1, fecha2;
    private int opcion;
    private String suproducto;

    public ReportePsicotropicos(ReportesInventarios obj, String idboti, String usuario) {
        initComponents();
        objreporte = obj;
        idbotica = idboti;
        user = usuario;
        con = obj.getCon();
        modelotabla = (DefaultTableModel) jTable1.getModel();
        CargarProductos();
        OcultaLabel(false);
        
        this.jTextField2.setVisible(false);
        jLabel5.setEnabled(false);
        jLabel5.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel12.setVisible(false);
        AparienciaTabla();
        this.jCheckBox2.setSelected(false);
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }
    private void OcultaLabel(boolean valor) {
        this.jLabel9.setVisible(valor);
        this.jLabel10.setVisible(valor);
    }

    public void run() {

        try {
            String sistema = "Windows";
       
            parameters.put("Inicio", desde.getDate());
            parameters.put("Fin", hasta.getDate());
            parameters.put("producto", this.jTextField2.getText().toString());
            parameters.put("Botica", idbotica);
            parameters.put("Almacen", 'M');
            parameters.put("REPORT_CONNECTION", con);
            parameters.put("Usuario", user);
            parameters.put("RANGO", 1);
            parameters.put("CARGAR", this.jLabel12.getText().toString());
            
            if (cargar == 0){
                archivo = this.getClass().getResource("/Reportes/KardexPsicotropicoTotal.jasper");
            }else{
                archivo = this.getClass().getResource("/Reportes/SUB_REPORT_PSICOTROPICOS.jasper");
            }

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("KARDEX: " + jLabel5.getText().toString());
            view.setVisible(true);
            view.setSize(850, 850);
            

        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }

        jLabel9.setVisible(false);
        jLabel10.setVisible(false);

    }

    private void abrirArchivo(String archivo) {
        try{
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + archivo);
        }catch(Exception e) {

        }
    }
  

    private void CargarProductos() {

        losproductos.setVisible(true);
        listaProductos = objBoticas.ListarProductosPsicotropicos();
        losproductos.addItem("--- Escoger Genericos ---");

        for (Integer ao = 0; ao < listaProductos.size(); ao++) {
            losproductos.addItem(listaProductos.get(ao).getDescripcion());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        desde = new org.jdesktop.swingx.JXDatePicker();
        hasta = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        losproductos = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ReportePsicotropicos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        desde.setFormats(new String[] { "dd/M/yyyy" });
        desde.setName("desde"); // NOI18N
        desde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desdeActionPerformed(evt);
            }
        });

        hasta.setFormats(new String[] { "dd/M/yyyy" });
        hasta.setName("hasta"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        losproductos.setName("losproductos"); // NOI18N
        losproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                losproductosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                losproductosMousePressed(evt);
            }
        });
        losproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                losproductosActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jCheckBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckBox2KeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Laboratorio", "S-Most-Und", "S-Most-Frac", "S-Alm-Und", "S-Alm-Frac"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jTextField1.setEnabled(false);
        jTextField1.setName("jTextField1"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setIcon(resourceMap.getIcon("jLabel10.icon")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(losproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268)
                        .addComponent(jCheckBox2)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(desde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(339, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(losproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        objreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (this.jCheckBox2.isSelected()) {
            this.losproductos.setEnabled(false);
            cargar = 1;
            this.jLabel12.setText("1");            
            BusquedaProducto(cargar);
            this.jButton3.setEnabled(true);
            

        } else {
            this.losproductos.setEnabled(true);
            this.jLabel12.setText("0");
            jTable1.removeAll();
            cargar = 0;            
            CargarProductos();
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void desdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desdeActionPerformed
        if (!Valida_Fechas()) {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no puede ser mayor a la Fecha de Fin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_desdeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        this.jTextField1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        jLabel5.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        //this.jButton3.setEnabled(true);
}//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (jTable1.getSelectedRow() >= 0) {
            this.jTextField1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jLabel5.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            //this.jButton3.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void losproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_losproductosActionPerformed
        // TODO add your handling code here:
        cargar = 0;        
        BusquedaProducto(cargar);
    }//GEN-LAST:event_losproductosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (Valida_Fechas()) {
            //if (this.jTextField1.getText().trim().compareTo("") != 0) {
                EjecutaReporteKardex();
            //} else {
            //    JOptionPane.showMessageDialog(this, "Porfavor Debe de Seleccionar  un Producto", "Error", JOptionPane.ERROR_MESSAGE);
            //}
        } else {
            JOptionPane.showMessageDialog(this, "Porfavor Seleccione Intervalos de Fechas Correctos ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void losproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_losproductosMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_losproductosMouseClicked

    private void losproductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_losproductosMousePressed
        // TODO add your handling code here:        
    }//GEN-LAST:event_losproductosMousePressed

    private void jCheckBox2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox2KeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCheckBox2KeyReleased

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(this,jCheckBox2.isSelected());
        if (jCheckBox2.isSelected()==true) {
        this.jButton3.setEnabled(true);
        }else{
        this.jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void EjecutaReporteKardex() {

        showThread = new Thread(this);
        showThread.start();
        this.jLabel9.setVisible(true);
        this.jLabel10.setVisible(true);
    }

    private void BusquedaProducto(int cargar){

        listproducto.removeAll(listproducto);

        for (Integer inicio = 0; inicio < modelotabla.getRowCount();) {
            modelotabla.removeRow(inicio);
            modelotabla.isCellEditable(inicio, 1);
            modelotabla.isCellEditable(inicio, 2);
        }

        jTable1.removeAll();
        jTable1.setModel(modelotabla);
        String generico = String.valueOf(losproductos.getSelectedIndex());
        String estado = String.valueOf(this.jLabel12.getText());
        int opcion = 1;
        //this.jButton3.setEnabled(false);
        if (generico.equals("0") ){
            suproducto = "0";
            this.jButton3.setEnabled(false);
        }else{
            suproducto = this.listaProductos.get(this.losproductos.getSelectedIndex() - 1).getId_Botica();
            this.jButton3.setEnabled(true);
        }
        this.jTextField2.setText(suproducto);        

        listproducto = objProducto.ListarProductosCarDesPsicotropicos(jTextField2.getText(), idbotica,opcion, cargar);
            
        for (Integer i = 0; i < listproducto.size(); i++) {
            Object[][] data = {
                {
                    listproducto.get(i).getProductoBotica().getProducto().getIdProducto(),
                    listproducto.get(i).getProductoBotica().getProducto().getDescripcion(),
                    listproducto.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Fraccion(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Fraccion()
                }
            };

            modelotabla.addRow(data[0]);

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

    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker desde;
    private org.jdesktop.swingx.JXDatePicker hasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox losproductos;
    // End of variables declaration//GEN-END:variables
}
