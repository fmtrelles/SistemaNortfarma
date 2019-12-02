/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentasxLaboratorio.java
 *
 * Created on 26/05/2011, 05:28:06 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaLaboratorios;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaVenta;
import entidad.Boticas;
import entidad.Laboratorios;
import entidad.Personal;
import entidad.Producto;
import entidad.TipoDocumento;
import java.net.URL;
import net.sf.jasperreports.engine.JasperPrint;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class VentasxLaboratorio extends javax.swing.JInternalFrame implements Runnable {

    LogicaVenta objTipoDocumento = new LogicaVenta();
    List<TipoDocumento> listTipoDocumento = new ArrayList<TipoDocumento>();
    List<Boticas> listaboticas = new ArrayList<Boticas>();
    List<Boticas> listaboticas2 = new ArrayList<Boticas>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProductos = new LogicaProducto();
    List<Producto> listproductos = new ArrayList<Producto>();
    MuestraVentana objetoventana = new MuestraVentana();
    List<Producto> listproducto = new ArrayList();
    List<Boticas> listBoticas = new ArrayList();
    LogicaBoticas objBotica = new LogicaBoticas();
    LogicaPersonal objPersonal = new LogicaPersonal();
    List<Personal> listPersonal = new ArrayList<Personal>();
    List<Laboratorios> listlaboratorios = new ArrayList<Laboratorios>();
    LogicaLaboratorios objLaboratorios = new LogicaLaboratorios();
    public DefaultTableModel modeloTabla;
    TableColumn columnaTabla = null;
    TableRow filaTabla = null;
    Integer fila = 0;
    LogicaProducto objProducto = new LogicaProducto();
    OpcionesReportes obreporte;
    private String idbotica;
    Thread showThread;
    JasperPrint jasperPrint;
    Connection con;
    Map parameters = new HashMap();
    URL report_file;
    JasperReport masterReport = null;
    VerificaSistema obj;

    public VentasxLaboratorio(String idboti, OpcionesReportes objeto) {
        initComponents();
        obreporte = objeto;
        idbotica = idboti;
        con = objeto.getCon();
        cargarBoticas();
        OcultaLabel(false);
    }

    public void run() {
        String sistema = "Windows";

        try {

            parameters.put("inicio", desde.getDate());
            parameters.put("fin", hasta.getDate());
            parameters.put("BOTICA", idbotica);
            parameters.put("Linicio", lblLabCod01.getText().toString());
            parameters.put("Lfin", lblLabCod02.getText().toString().toString());
            parameters.put("REPORT_CONNECTION", con);

            report_file = this.getClass().getResource("/Reportes/VentasxLaboratorio.jasper");

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("REPORTE DE VENTAS POR LABORATORIO");
            view.setVisible(true);
            view.setSize(850, 650);
            jButton1.setEnabled(true);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }

        OcultaLabel(false);
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel7.setVisible(valor);
        this.jLabel8.setVisible(valor);
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        boticas = new javax.swing.JComboBox();
        desde = new org.jdesktop.swingx.JXDatePicker();
        hasta = new org.jdesktop.swingx.JXDatePicker();
        lblLabCod01 = new javax.swing.JLabel();
        lblLabDescripcion01 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblLabCod02 = new javax.swing.JLabel();
        lblLabDescripcion02 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(VentasxLaboratorio.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        boticas.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        boticas.setName("boticas"); // NOI18N

        desde.setFont(resourceMap.getFont("hasta.font")); // NOI18N
        desde.setFormats(new String[] { "dd/M/yyyy" });
        desde.setName("desde"); // NOI18N
        desde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desdeActionPerformed(evt);
            }
        });

        hasta.setFont(resourceMap.getFont("hasta.font")); // NOI18N
        hasta.setFormats(new String[] { "dd/M/yyyy" });
        hasta.setName("hasta"); // NOI18N

        lblLabCod01.setText(resourceMap.getString("lblLabCod01.text")); // NOI18N
        lblLabCod01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabCod01.setName("lblLabCod01"); // NOI18N

        lblLabDescripcion01.setText(resourceMap.getString("lblLabDescripcion01.text")); // NOI18N
        lblLabDescripcion01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabDescripcion01.setName("lblLabDescripcion01"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblLabCod02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabCod02.setName("lblLabCod02"); // NOI18N

        lblLabDescripcion02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabDescripcion02.setName("lblLabDescripcion02"); // NOI18N

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(boticas, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE)
                    .addComponent(hasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLabCod01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLabDescripcion01, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLabCod02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLabDescripcion02, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLabDescripcion01, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2))
                    .addComponent(lblLabCod01, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLabDescripcion02, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLabCod02, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LabFin p = new LabFin(this, objetoventana);
        p.show(true);        // TODO add your handling code here:

        this.lblLabCod02.setText(LabFin.getIdLab());
        this.lblLabDescripcion02.setText(LabFin.getDescLab());
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LabInicio p = new LabInicio(this, objetoventana);
        p.show(true);        // TODO add your handling code here:

        this.lblLabCod01.setText(LabInicio.getIdLab());
        this.lblLabDescripcion01.setText(LabInicio.getDescLab());

        this.lblLabCod02.setText("");
        this.lblLabDescripcion02.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Valida_Fechas()) {

            listBoticas = objBotica.DescripcionBoticaDefault();

            if (hasta.getDate() != null && desde.getDate() != null) {
                showThread = new Thread(this);
                showThread.start();
                jButton1.setEnabled(false);
                OcultaLabel(true);

            } else {
                JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar un rango de Fechas \n Para generar su Reporte", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no puede ser Mayor a la Fecha de fin ", "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.desde.getDate();
        java.util.Date fecha2 = hasta.getDate();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }


        return valor;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        obreporte.entra = true;
        dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void desdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desdeActionPerformed
    private void cargarBoticas() {

        listaboticas = objBoticas.ListarobjBoticas();
        int poscion = 0;

        for (Integer i = 0; i < listaboticas.size(); i++) {
            boticas.addItem(listaboticas.get(i).getDescripcion());
            String val = listaboticas.get(i).getId_Botica();

            if (idbotica.compareTo(val) == 0) {
                poscion = i;
            }
        }

        boticas.setSelectedIndex(poscion);
        boticas.setEnabled(false);

    }
    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boticas;
    private org.jdesktop.swingx.JXDatePicker desde;
    private org.jdesktop.swingx.JXDatePicker hasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLabCod01;
    private javax.swing.JLabel lblLabCod02;
    private javax.swing.JLabel lblLabDescripcion01;
    private javax.swing.JLabel lblLabDescripcion02;
    // End of variables declaration//GEN-END:variables
}
