/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LabConStock.java
 *
 * Created on 09/02/2012, 07:45:20 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaConexion;
import CapaLogica.LogicaLaboratorios;
import entidad.Laboratorios;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class LabConStock extends javax.swing.JInternalFrame implements Runnable {

    LogicaLaboratorios objLabs = new LogicaLaboratorios();
    List<Laboratorios> listlabs = new ArrayList();
    JasperPrint jasperPrint;
    List<Laboratorios> listlabs2 = new ArrayList();
    LogicaConexion logconex = new LogicaConexion();
    JasperReport masterReport = null;
    String report_file;
    Map parameters = new HashMap();
    Connection con;
    ReportesInventarios objreporte;
    /** Creates new form ProductosLaboratorio */
    String ini = "";
    String fin = "";
    int mostrar;
    private URL archivo;
    VerificaSistema objsistema;
    private String idbotica;
    Thread showThread;

    /** Creates new form LabConStock */
    public LabConStock(String idboti, ReportesInventarios obj) {
        initComponents();
        objreporte = obj;
        con = objreporte.getCon();
        idbotica = idboti;
        cargarLabs();
        OcultaLabel(false);
    }

    public void run() {

        try {

            String sistema = "Windows";
            parameters.put("labInicio", ini);
            parameters.put("labFin", fin);
            parameters.put("IDBOTICA", idbotica);
            parameters.put("MOSTRAR", mostrar);

            if (mostrar == 0){
                archivo = this.getClass().getResource("/Reportes/Laboratorios_con_stock.jasper");
            }else{
                archivo = this.getClass().getResource("/Reportes/Laboratorios_con_stock_INV.jasper");
            }

            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("Laboratorio (STOCK)");
            view.setVisible(true);
            view.setSize(850, 850);

        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }

        OcultaLabel(false);
        this.jButton1.setEnabled(true);
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel7.setVisible(valor);
        this.jLabel8.setVisible(valor);
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    private void cargarLabs() {

        listlabs = objLabs.ListaLabs("%");

        for (Integer comienzo = 0; comienzo < listlabs.size(); comienzo++) {
            labInicio.addItem(listlabs.get(comienzo).getDescripcion() + "( " + listlabs.get(comienzo).getId_Lab() + " )");
        }
    }

    private void cargarLabs2(String Filtro) {

        listlabs2.removeAll(listlabs2);
        labFin.removeAllItems();
        listlabs2 = objLabs.ListaLabs(Filtro);

        for (Integer comienzo = 0; comienzo < listlabs2.size(); comienzo++) {
            labFin.addItem(listlabs2.get(comienzo).getDescripcion() + "( " + listlabs2.get(comienzo).getId_Lab() + " ) ");
        }


    }
    private String[] ArrayBoticas = {};

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labInicio = new javax.swing.JComboBox();
        labFin = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(LabConStock.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        labInicio.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        labInicio.setName("labInicio"); // NOI18N
        labInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                labInicioItemStateChanged(evt);
            }
        });
        labInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labInicioActionPerformed(evt);
            }
        });

        labFin.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        labFin.setName("labFin"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labFin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labInicio, 0, 294, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)
                        .addGap(78, 78, 78)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel8)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        objreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void labInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labInicioActionPerformed
        String codL = "";

        try {
            for (Integer i = 0; i < labInicio.getItemAt(labInicio.getSelectedIndex()).toString().length(); i++) {
                codL = codL + labInicio.getItemAt(labInicio.getSelectedIndex()).toString().substring(i, i + 1);
                if (labInicio.getItemAt(labInicio.getSelectedIndex()).toString().substring(i + 1, i + 2).compareTo(" ") == 0) {
                    i = labInicio.getItemAt(labInicio.getSelectedIndex()).toString().length() + 10;
                }
            }

            cargarLabs2(codL);
            labFin.setEnabled(true);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }//GEN-LAST:event_labInicioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.ini = listlabs.get(labInicio.getSelectedIndex()).getId_Lab();
        this.fin = listlabs2.get(labFin.getSelectedIndex()).getId_Lab();
        mostrar = 0;

        showThread = new Thread(this);
        showThread.start();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void labInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_labInicioItemStateChanged
    }//GEN-LAST:event_labInicioItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.ini = listlabs.get(labInicio.getSelectedIndex()).getId_Lab();
        this.fin = listlabs2.get(labFin.getSelectedIndex()).getId_Lab();
        mostrar = 1;

        showThread = new Thread(this);
        showThread.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox labFin;
    private javax.swing.JComboBox labInicio;
    // End of variables declaration//GEN-END:variables
}
