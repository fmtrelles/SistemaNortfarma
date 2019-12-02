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
import entidad.Producto;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class ReporteInventarios_Quim extends javax.swing.JInternalFrame {

    public ModeloTabla modeloTabla;
    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento;
    List<Proveedor> listaProveedor;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Varios> Stocks;
    Guias entidadGuias = new Guias();
    Producto entidadProductos = new Producto();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    public static String ProductoRecibido;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    boolean pasaPorStock = true;
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    String report_file;
    JasperReport masterReport = null;
    List<Producto> listproducto = new ArrayList();
    List<Boticas> listBoticas = new ArrayList();
    LogicaBoticas objBotica = new LogicaBoticas();
    ReportesInventarios objreporte;
    Integer fila = 0;
    String idbotica;
    Connection con;
    private String fecha1;
    private String fecha2;
    private int opcion;
    private String documento;

    /** Creates new form ReporteInventarios_Quim */
    public ReporteInventarios_Quim(ReportesInventarios obj, String idboti) {
        initComponents();
        objreporte = obj;
        idbotica = idboti;
        modeloTabla = new ModeloTabla();
        con = obj.getCon();
        construirGuia();
        opcion = 0;
        documento = "";
        listarMovimientos();
        Oculta(false);
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void Oculta(boolean valor) {
        jLabel4.setVisible(valor);
    }

    private void LimpiatTabla() {

        for (Integer i = 0; i < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(i);
        }
    }

    private void construirGuia() {

        modeloTabla.addColumn("BOTICA");
        modeloTabla.addColumn("ALMACEN");
        modeloTabla.addColumn("PROVEEDOR");
        modeloTabla.addColumn("MOVIMIENTO");
        modeloTabla.addColumn("SERIE");
        modeloTabla.addColumn("NUMERO");
        modeloTabla.addColumn("DOCUMENTO");
        modeloTabla.addColumn("FECHA");


        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(50);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(75);
        columnaTabla.setMinWidth(75);
        columnaTabla.setMaxWidth(75);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(200);
        columnaTabla.setMinWidth(200);
        columnaTabla.setMaxWidth(200);


        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(50);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(160);
        columnaTabla.setMinWidth(160);
        columnaTabla.setMaxWidth(160);

    }

    private void listarMovimientos() {

        List<Guias> listGuias = new ArrayList<Guias>();
        LimpiatTabla();
        fecha1 = objFechaHora.ConvierteFecha(this.jXDatePicker1.getDate());
        fecha2 = objFechaHora.ConvierteFecha(this.jXDatePicker2.getDate());
        documento = this.jTextField1.getText().trim() + '%';
        listGuias = objGuias.RecuperaTodosLosMovimientos_Quim(opcion, idbotica, fecha1, fecha2, documento);

        for (int inicio = 0; inicio < listGuias.size(); inicio++) {
            Object[][] data = {
                {
                    listGuias.get(inicio).getId_Botica().toString(),
                    listGuias.get(inicio).getId_TipoAlmacen().toString(),
                    listGuias.get(inicio).getId_Proveedor().toString(),
                    listGuias.get(inicio).getId_TipoMovimiento().toString(),
                    listGuias.get(inicio).getSerie().toString(),
                    listGuias.get(inicio).getNumero().toString(),
                    listGuias.get(inicio).getNumero_Documento().toString(),
                    listGuias.get(inicio).getFecha_Documento().toString()
                }
            };

            modeloTabla.addRow(data[0]);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ReporteInventarios_Quim.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jXTaskPane2.setSpecial(true);
        jXTaskPane2.setTitle(resourceMap.getString("jXTaskPane2.title")); // NOI18N
        jXTaskPane2.setName("jXTaskPane2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N
        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jXDatePicker2.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXTaskPane2Layout = new javax.swing.GroupLayout(jXTaskPane2.getContentPane());
        jXTaskPane2.getContentPane().setLayout(jXTaskPane2Layout);
        jXTaskPane2Layout.setHorizontalGroup(
            jXTaskPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTaskPane2Layout.createSequentialGroup()
                .addGroup(jXTaskPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXTaskPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXTaskPane2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jXTaskPane2Layout.setVerticalGroup(
            jXTaskPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTaskPane2Layout.createSequentialGroup()
                .addGroup(jXTaskPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXTaskPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
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
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        if (!Valida_Fechas()) {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no Puede ser Mayor a la Fecha de Fin  ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void BuscaMovimientos() {
        opcion = 1;
        listarMovimientos();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if (Valida_Fechas()) {
            BuscaMovimientos();
        } else {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no Puede ser Mayor a la Fecha de Fin ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Valida_Fechas()) {
            BuscaMovimientos();
        } else {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no Puede ser Mayor a la Fecha de Fin ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.objreporte.setEntra(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            EjecutaReporteKardex(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString(), con);
        }
        jButton3.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EjecutaReporteKardex(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString(), con);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void EjecutaReporteKardex(String alma, String provee, String idbotica, String documento, String tipo, Connection con) {
        Oculta(true);
        ReportHilo p = new ReportHilo(alma, provee, idbotica, documento, tipo, con);
        p.start();
    }

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.jXDatePicker1.getDate();
        java.util.Date fecha2 = jXDatePicker2.getDate();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }
        return valor;
    }

    public class ReportHilo extends Thread {

        JasperPrint jasperPrint;
        Map parameters = new HashMap();
        JasperReport masterReport = null;
        Connection con;
        String report_file;
        private URL archivo;
        VerificaSistema obj;
        String tipo = "";
        String numero = "";
        String almacen;
        String proveedor, idbotica;

        public ReportHilo(String alma, String provee, String idbotica, String documento, String tipo, Connection con) {
            this.proveedor = provee;
            this.numero = documento;
            this.con = con;
            this.almacen = alma;
            this.idbotica = idbotica;
            this.tipo = tipo;
        }

        public void run() {

            try {

                String sistema = "Windows";
                listBoticas = objBotica.DescripcionBoticaDefault();
                parameters.put("ALMA", almacen);
                parameters.put("BOTICA", idbotica);
                parameters.put("PROVEEDOR", proveedor);
                parameters.put("MINumero", numero);
                parameters.put("MOVIMIENTO", tipo);
                parameters.put("REPORT_CONNECTION", con);

                archivo = this.getClass().getResource("/Reportes/CargosDescargos_QUIM.jasper");

                if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setTitle("REPORTE MOVIMIENTOS DE BOTICA");
                view.setVisible(true);
                view.setSize(850, 850);
                Oculta(false);

            } catch (JRException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    // End of variables declaration//GEN-END:variables
}
