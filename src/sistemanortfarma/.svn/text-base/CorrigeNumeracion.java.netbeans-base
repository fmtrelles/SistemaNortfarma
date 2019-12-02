/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CorrigeNumeracion.java
 *
 * Created on 08/03/2012, 04:17:20 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaRecuperaCaja;
import CapaLogica.LogicaVenta;
import CapaLogica.RecuperaSerieNumero;
import entidad.Cajas;
import entidad.SerieNumero;
import entidad.TipoDocumento;
import entidad.Venta;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S.
 */
public class CorrigeNumeracion extends javax.swing.JInternalFrame {

    private int idcaja;
    private String idbotica;
    AplicacionVentas objventas;
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    LogicaVenta objTipoVentas = new LogicaVenta();
    List<TipoDocumento> listatipoventas = new ArrayList<TipoDocumento>();
    List<SerieNumero> listaPagos = new ArrayList<SerieNumero>();
    RecuperaSerieNumero objlogicaPagos = new RecuperaSerieNumero();
    private String serie = null;
    private String mnumero = null;
    private int tipoventa;
    LogicaVenta logventa = new LogicaVenta();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    MailClient objmail = new MailClient();

    /** Creates new form CorrigeNumeracion */
    public CorrigeNumeracion(AplicacionVentas ob) {
        initComponents();
        this.jLabel3.setText("");
        objventas = ob;
        idbotica = objventas.getIdbotica();
        ListaCajas();
        cargarTipoVentas();
    }

    private void cargarTipoVentas() {
        listatipoventas = objTipoVentas.ListarTipoDocumento();
        for (int i = 0; i < listatipoventas.size(); i++) {
            tipo_documento.addItem(listatipoventas.get(i).getDescripcion());
        }
    }

    private void ListaCajas() {
        listacajas = objcajas.RecuperaCajas(idbotica);

        if (listacajas.size() > 1) {
            this.jComboBox1.addItem("--- Seleccione su Caja ---");
            for (int i = 0; i < listacajas.size(); i++) {
                this.jComboBox1.addItem(listacajas.get(i).getCajas());
            }
        } else {
            if (listacajas.size() == 1) {
                for (int i = 0; i < listacajas.size(); i++) {
                    this.jComboBox1.addItem(listacajas.get(i).getCajas());
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tipo_documento = new javax.swing.JComboBox();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CorrigeNumeracion.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2))
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

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        tipo_documento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Seleccione Documento ---" }));
        tipo_documento.setName("tipo_documento"); // NOI18N
        tipo_documento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_documentoItemStateChanged(evt);
            }
        });
        tipo_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_documentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tipo_documento, 0, 206, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Recupera_Numeracion(String Documento) {
        int posi;

        if (this.jComboBox1.getItemCount() > 1) {
            posi = this.jComboBox1.getSelectedIndex() - 1;
        } else {
            posi = this.jComboBox1.getSelectedIndex();
        }

        tipoventa = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();
        listaPagos.removeAll(listaPagos);
        listaPagos = objlogicaPagos.RecuperaSerieNumero(idbotica, listacajas.get(posi).getIdcaja(), tipoventa);

        if (listaPagos.size() > 0) {
            serie = listaPagos.get(0).getSerie();
            mnumero = listaPagos.get(0).getNumero();
            this.jTextField1.setText(serie);
            this.jTextField2.setText(mnumero);
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Guardar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tipo_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_documentoActionPerformed
    }//GEN-LAST:event_tipo_documentoActionPerformed

    private void tipo_documentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_documentoItemStateChanged
        if (this.tipo_documento.getSelectedIndex() > 0) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getModificable() == 1) {
                    jLabel3.setText(tipo_documento.getSelectedItem().toString());
                    Recupera_Numeracion(tipo_documento.getSelectedItem().toString().trim());
                } else {
                    tipo_documento.setSelectedIndex(0);
                    jTextField1.setText("");
                    jTextField2.setText("");
                    jLabel3.setText("");
                    JOptionPane.showMessageDialog(this, "Lo sentimos no puede Corregir este Tipo de Documento ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }//GEN-LAST:event_tipo_documentoItemStateChanged

    private void Guardar() {

        Venta objventa = null;
        int posi;
        int num_Fin;

        if (this.jComboBox1.getItemCount() > 1) {
            posi = this.jComboBox1.getSelectedIndex() - 1;
        } else {
            posi = this.jComboBox1.getSelectedIndex();
        }

        try {

            idcaja = listacajas.get(posi).getIdcaja();
            tipoventa = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();

            if (this.jTextField2.getText().trim().compareTo("") != 0) {

                num_Fin = Integer.parseInt(this.jTextField2.getText().trim());

                int p = JOptionPane.showConfirmDialog(null, " Se Procedera a Modificar la Numeracion en '" + tipo_documento.getSelectedItem() + "' Desea Continuar   ", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (p == JOptionPane.YES_OPTION) {
                    objventa = new Venta(idbotica, this.jTextField2.getText().trim(), idcaja, tipoventa, serie);
                    boolean resultado = logventa.Corrige_Numeracion(objventa);

                    if (resultado) {
                        this.dispose();
                        objventas.marcacdo = false;
                        JOptionPane.showMessageDialog(this, "Su Numeracion De '" + tipo_documento.getSelectedItem() + "' Ha Sido Cambiada", "OK", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error : Porfavor Debes de Ingresar una Numeracion Correcta", "Error", JOptionPane.ERROR_MESSAGE);
            }


            if (Integer.parseInt(this.jTextField2.getText().trim()) < Integer.parseInt(mnumero)) {
                String correo = objlistabotica.ReornaCorreo(idbotica);
                objmail.sendMail(correo, "CORRECCION DE NUMERACION", "Se Ha Corregido una Numeracion ya usada \n \n Serie : " + jTextField1.getText().trim() + " \n Numero : " + mnumero + "  \n BOTICA FELICIDAD  " + idbotica);
            }

        } catch (Exception ex) {
        }



    }

    private void CerrarVentana() {
        int p = JOptionPane.showConfirmDialog(null, " Desea Cerrar ", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (p == JOptionPane.YES_OPTION) {
            objventas.marcacdo = false;
            this.dispose();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox tipo_documento;
    // End of variables declaration//GEN-END:variables
}
