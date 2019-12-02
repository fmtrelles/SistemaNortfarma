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
import entidad.Producto;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.net.URL;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author Miguel Gomez S.
 */
public class ControlRegistrosEspeciales extends javax.swing.JInternalFrame implements Runnable {

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
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    public static String ProductoRecibido;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    boolean pasaPorStock = true;
    JasperPrint jasperPrint;
    Connection con;
    Map parameters = new HashMap();
    String report_file;
    JasperReport masterReport = null;
    List<Producto> listproducto = new ArrayList();
    List<Boticas> listBoticas = new ArrayList();
    LogicaBoticas objBotica = new LogicaBoticas();
    ReportesInventarios objreporte;
    Integer fila = 0;
    String idbotica;
    /** Creates new form ControlTransacciones */
    Thread showThread;
    private URL archivo;
    VerificaSistema objsistema;
    private String almacen="";
    private Date fecha1, fecha2;
    private int opcion;
    private String suproducto;

    public ControlRegistrosEspeciales(ReportesInventarios obj, String idboti) {
        initComponents();
        objreporte = obj;
        idbotica = idboti;
        con = obj.getCon();
        CargarProductos();
        OcultaLabel(false);
        this.jCheckBox2.setSelected(true);
        //this.jCheckBox1.setSelected(false);
        this.losproductos.setEnabled(false);
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel9.setVisible(valor);
        this.jLabel10.setVisible(valor);
    }

    public void run() {

        try {
            String sistema = "Windows";
            parameters.put("ALMACEN", almacen);
            parameters.put("FECHA1", fecha1);
            parameters.put("FECHA2", fecha2);
            parameters.put("IDBOTICA", idbotica);
            parameters.put("OPCION", opcion);
            parameters.put("SUPRODUCTO", suproducto);
            parameters.put("ESCARGO", jComboBox2.getSelectedIndex());
            parameters.put("REPORT_CONNECTION", con);

            String CarDes = String.valueOf(jComboBox2.getSelectedIndex());

            /*if (this.jCheckBox2.isSelected()) {  //TODOS
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTotGeneral.jasper");
            } else {
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTot.jasper");
            }*/

            if (CarDes.equals("1") && this.jCheckBox2.isSelected()) {//Si es Cargo y TODOS
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTotGeneralCargo.jasper");
            }/*else if (CarDes.equals("1") && !this.jCheckBox2.isSelected()) {//Si es Cargo y selecciono un producto
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTot.jasper");            
            }*/else if (CarDes.equals("2") && this.jCheckBox2.isSelected()) {//Si es DesCargo y TODOS
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTotGeneral.jasper");
            }/*else if (CarDes.equals("2") && !this.jCheckBox2.isSelected()) {//Si es DesCargo y selecciono un producto
                archivo = this.getClass().getResource("/Reportes/TransaccionesRegEspecialesTotDescargo.jasper");
            }*/


            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE INVENTARIOS");
            view.setVisible(true);
            view.setSize(850, 850);
            
            /*masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE INVENTARIOS");
//            view.setVisible(true);
            view.setAlwaysOnTop(true);

            String reporte = "reporte.xls";

            JasperExportManager.exportReportToPdfFile(jasperPrint, reporte);
            JasperCompileManager ro;

            JRExporter jasperXlsExportMgr = new JRXlsExporter();

            jasperXlsExportMgr.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
//            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE );
            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.TRUE );                        ;
            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, java.lang.Boolean.FALSE );
            jasperXlsExportMgr.setParameter( JRExporterParameter.OUTPUT_FILE, new java.io.File(reporte) );
            jasperXlsExportMgr.exportReport();
            abrirArchivo(reporte);
*/

        } catch (JRException ex) {
            System.out.println(ex.getMessage());
            OcultaLabel(false);
        }

        OcultaLabel(false);

    }

    private void abrirArchivo(String archivo) {
        try{
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + archivo);
        }catch(Exception e) {

        }
    }
  /*  private void verReporteExcel() {
        String sistema = "Windows";

        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(chkPersonal.isSelected() && !chkFechas.isSelected()) {
                int mifila = tabla.getSelectedRow();
                int idPersonal = listaHorarios.get(mifila).getHorario_Detalle().getPersonal().getId_Personal();
                System.out.println(idPersonal);
                parameters.put("vPersonal", idPersonal);
                parameters.put("vBotica", miboti);
                parameters.put("fInicio", sdf.format(fInicio.getDate()));
                parameters.put("fFin", sdf.format(fFinal.getDate()));
            }else if(chkFechas.isSelected() && !chkPersonal.isSelected()) {
                parameters.put("vBotica", miboti);
                parameters.put("fInicio", sdf.format(fInicio.getDate()));
                parameters.put("fFinal", sdf.format(fFinal.getDate()));
            }

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            if(chkPersonal.isSelected() && !chkFechas.isSelected()) {
                report_file = this.getClass().getResource("/Reportes/HorariosFaltasTardanzas.jasper");
            }else if(chkFechas.isSelected() && !chkPersonal.isSelected()) {
                report_file = this.getClass().getResource("/Reportes/HorariosFaltasTardanzasFechas.jasper");
            }

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE TARDANZAS Y FALTAS");
//            view.setVisible(true);
            view.setAlwaysOnTop(true);

            String reporte = "reporte.xls";

            JasperExportManager.exportReportToPdfFile(jasperPrint, reporte);
            JasperCompileManager ro;

            JRExporter jasperXlsExportMgr = new JRXlsExporter();

            jasperXlsExportMgr.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
//            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE );
            jasperXlsExportMgr.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, java.lang.Boolean.TRUE );
            jasperXlsExportMgr.setParameter( JRExporterParameter.OUTPUT_FILE, new java.io.File(reporte) );
            jasperXlsExportMgr.exportReport();
            abrirArchivo(reporte);

        } catch (JRException ex) {
            System.out.println("Excepcion: "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }*/

    private void CargarProductos() {

        losproductos.setVisible(true);
        listaProductos = objBoticas.ListarProductos();
        losproductos.addItem("--- Escoger Productos ---");

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
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ControlRegistrosEspeciales.class);
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

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
        jCheckBox2.setEnabled(false);
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Seleccione-", "Cargos", "Descargos" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel9.setIcon(resourceMap.getIcon("jLabel9.icon")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(losproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(desde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(losproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        objreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String opciones = String.valueOf(jComboBox2.getSelectedIndex());
        if (opciones.equals("0")){
            JOptionPane.showMessageDialog(this, " Debe Seleccionar un Movimiento", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (Valida_Fechas()) {
            OcultaLabel(true);

            /*if (this.jComboBox1.getSelectedIndex() == 0) {
                this.almacen = "M";
            } else {
                this.almacen = "A";
            }
            */
            this.almacen = "";

            if (this.jCheckBox2.isSelected()) {  //todosº
                opcion = 0;
                suproducto = "";
            } else {
                opcion = 1;
                suproducto = this.listaProductos.get(this.losproductos.getSelectedIndex() - 1).getId_Botica();
            }
            fecha1 = this.desde.getDate();
            fecha2 = this.hasta.getDate();
            showThread = new Thread(this);
            showThread.start();

        } else {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no puede ser mayor a la Fecha de Fin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (this.jCheckBox2.isSelected()) {
            this.losproductos.setEnabled(false);
        } else {
            this.losproductos.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void desdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desdeActionPerformed
        if (!Valida_Fechas()) {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no puede ser mayor a la Fecha de Fin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_desdeActionPerformed

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.desde.getDate();
        java.util.Date fecha2 = hasta.getDate();

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
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox losproductos;
    // End of variables declaration//GEN-END:variables
}
