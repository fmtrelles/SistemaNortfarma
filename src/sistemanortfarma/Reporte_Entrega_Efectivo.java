/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Reporte_Entrega_Efectivo.java
 *
 * Created on 18/03/2013, 05:52:56 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaCajas;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import entidad.Cajas;
import entidad.Personal;
import entidad.Turno;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class Reporte_Entrega_Efectivo extends javax.swing.JInternalFrame implements Runnable {

    AplicacionVentas obj;
    String idbotica;
    int idsesion = 0;
    Date fechaInicio = null;
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    private static String Nomcaja;
    OpcionesReportes obreporte;
    LogicaPersonal logperso = new LogicaPersonal();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    VerificaSistema objsistema;
    int pasad = 0;
    boolean escogio = false;
    int idcajero = 0;
    int idcaja;
    Thread showThread;
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con;
    String report_file;
    JasperViewer view;  
    private URL archivo;
    int codigo;
    private String usuario;
    LogicaCajas logcajas = new LogicaCajas();
    int turno;
    List<Turno> Lista_Turnos = new ArrayList<Turno>();

    /** Creates new form Reporte_Entrega_Efectivo */
    public Reporte_Entrega_Efectivo(OpcionesReportes objeto) {
        initComponents();
        con = objeto.getCon();
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        idsesion = obj.getId_personal_botica();
        jTextField1.setText(idbotica);
        OcultaLabel(false);
        CargarCajeros();
        Lista_Turnos();
    }

    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox4.addItem("---- Seleccione Turno ----");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
        }
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel7.setVisible(valor);
        this.jLabel8.setVisible(valor);
    }

    private void CargarCajeros() {

        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }


        int rol = new login().getId_rol();

        if (rol == 12 || rol == 16 || rol == 20) {
            Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

            for (int i = 0; i < Lista_Cajeros.size(); i++) {
                jComboBox2.addItem(Lista_Cajeros.get(i).getApellido());
            }

        } else {
            jComboBox2.addItem(usuario);
            idcajero = obj.getIdpesonal();
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Reporte_Entrega_Efectivo.class);
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

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------  Seleccione su Caja  ---------" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox4.setName("jComboBox4"); // NOI18N

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion > 0) {
            poscion--;
            escogio = true;
            Nomcaja = String.valueOf(this.jComboBox1.getSelectedItem());
            idcaja = listacajas.get(poscion).getIdcaja();
        }
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if (pasad > 0) {
            idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
        }
}//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion >= 0) {
            if (pasad > 0) {
                codigo = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        obreporte.entra = true;
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (Verifica_Datos()) {
                turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
                if (Lista_Cajeros.size() > 0) {
                    idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
                }
                EjecutaReporteCaja();
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean Verifica_Datos() {

        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox4.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
            return true;
        }

        return false;
    }

    private void EjecutaReporteCaja() {
        fechaInicio = this.jXDatePicker1.getDate();
        if (escogio) {
            showThread = new Thread(this);
            showThread.start();
            OcultaLabel(true);
            this.jXDatePicker1.setEnabled(false);
            this.jComboBox2.setEnabled(false);
            this.jComboBox1.setEnabled(false);
            this.jButton1.setEnabled(false);
            this.jButton2.setEnabled(false);
            this.jComboBox4.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

        try {

            if (Lista_Cajeros.size() > 0) {
                idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }
            turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
            String sistema = "Windows";
            parameters.put("MIFECHA", jXDatePicker1.getDate());
            parameters.put("IDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
            parameters.put("IDBOTICA", idbotica);
            parameters.put("IDPERSONAL", idcajero);
            parameters.put("TURNO", turno);
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "ES"));
            parameters.put("REPORT_CONNECTION", con);

            archivo = this.getClass().getResource("/Reportes/REPORTE_ENTREGA_EFECTIVO.jasper");

            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            view = new JasperViewer(jasperPrint, false);
            view.setTitle("DETALLE DE ENTREGA DE EFECTIVO");
            view.setVisible(true);
            view.setSize(870, 700);

        } catch (JRException e) {
            System.out.println(e.getMessage());
            OcultaLabel(false);
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }

        jXDatePicker1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox1.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jComboBox4.setEnabled(true);
        OcultaLabel(false);

    }
}
