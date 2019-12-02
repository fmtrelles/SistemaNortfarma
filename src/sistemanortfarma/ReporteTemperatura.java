/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentasxBotica.java
 *
 * Created on 29/12/2011, 10:37:10 AM
 */
package sistemanortfarma;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import entidad.Personal;
import java.util.List;
import java.util.ArrayList;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaBoticas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import entidad.Venta;

/**
 *
 * @author Miguel Gomez S.
 */
public class ReporteTemperatura extends javax.swing.JInternalFrame implements Runnable {

    OpcionesReportes obreporte;
    String idbotica;
    AplicacionVentas obj;
    String usuario;    
    Date fechaInicio = null;
    Date fechaFin = null;
    List<Venta> listMovilidad = new ArrayList<Venta>();
    List<Venta> listMovilidad_aux = new ArrayList<Venta>();
    List<Venta> listitem = new ArrayList<Venta>();
    List<Venta> MtoTotal = new ArrayList<Venta>();
    String opcion;
    Thread showThread;
    List<Personal> Lista_Temperatura = new ArrayList<Personal>();
    LogicaPersonal logperso = new LogicaPersonal();
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con = null;
    String report_file;
    LogicaBoticas logbotica = new LogicaBoticas();
    JasperViewer view;
    private URL archivo;
    private int idcaja;
    VerificaSistema objsistema;

    /** Creates new form VentasxBotica */
    public ReporteTemperatura(OpcionesReportes objeto) {
        initComponents();
        obreporte = objeto;
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        con = objeto.getCon();
        OcultaLabel(false);
        this.jTextField1.setText(idbotica);
        CargarOpcionesTemperatura();
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel6.setVisible(valor);
        this.jLabel7.setVisible(valor);
    }

    private void CargarOpcionesTemperatura() {
        
        int rol = new login().getId_rol();

        //if (rol == 16) {
            Lista_Temperatura = logperso.Lista_Temperatura(idbotica);

            for (int i = 0; i < Lista_Temperatura.size(); i++) {
                jComboBox6.addItem(Lista_Temperatura.get(i).getDescripcionTemperatura());
            }            
        /*} else {
            jComboBox6.addItem(usuario);           
        }*/

    }

    public void run() {
        try {
            String sistema = "Windows";
            int idtempera = Lista_Temperatura.get(this.jComboBox6.getSelectedIndex()).getidtemperatura();
        
                //parameters.put("OPCION", opcion);
                parameters.put("INIDBOTICA", idbotica);
                //parameters.put("USUARIO", usuario);
                //parameters.put("FECHAINICIO", desde.getDate());
                //parameters.put("FECHAFIN", hasta.getDate());
                //parameters.put("FECHAINICIO", fec1);
                //parameters.put("FECHAFIN", fec1);
                parameters.put("IDTEMPERATURA", idtempera);
                //parameters.put("ITEM", it);
                parameters.put("REPORT_CONNECTION", con);

            //}
                archivo = this.getClass().getResource("/Reportes/Reporte_Temperatura.jasper");

                if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }


                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                view = new JasperViewer(jasperPrint, false);
                view.setTitle("Ventas Totales de la Botica ");
                view.setVisible(true);
                view.setSize(870, 700);

            //}

        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }

        //jXDatePicker1.setEnabled(true);
        //jXDatePicker2.setEnabled(true);
        jButton1.setEnabled(true);
        OcultaLabel(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ReporteTemperatura.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jComboBox6.setName("jComboBox6"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

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

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        OcultaLabel(true);
       // fechaInicio = this.desde.getDate();
       // fechaFin = this.hasta.getDate();
       // opcion = this.jComboBox1.getSelectedItem().toString();

        showThread = new Thread(this);
        showThread.start();

        //this.jXDatePicker1.setEnabled(false);
        //this.jXDatePicker2.setEnabled(false);
        this.jButton1.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        obreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
