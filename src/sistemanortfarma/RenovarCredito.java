/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RenovarCredito1.java
 *
 * Created on 04/01/2012, 12:05:52 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaClientes;
import CapaLogica.LogicaEmpresas;
import CapaLogica.LogicaFechaHora;
import entidad.Boticas;
import entidad.Clientes;
import entidad.Empresas;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S.
 */
public class RenovarCredito extends javax.swing.JInternalFrame {

    List<Boticas> listaboticas = new ArrayList<Boticas>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<Empresas> listEmpresas = new ArrayList<Empresas>();
    LogicaEmpresas objEmpresas = new LogicaEmpresas();
    List<Clientes> listClientes = new ArrayList<Clientes>();
    LogicaClientes objClientes = new LogicaClientes();
    List<Clientes> ListMovimientos = new ArrayList<Clientes>();
    Clientes entidadClientes = new Clientes();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    Clientes entidadClietnes;
    MuestraVentana objetoventana = new MuestraVentana();
    FormClientes obclientes;
    private String idbotica;
    List<Clientes> listaclientes = new ArrayList<Clientes>();
    Object[] datosDetalle = new Object[5];
    private DefaultTableModel modelClientes;
    Administracion objeto = null;


    /** Creates new form RenovarCredito1 */
    public RenovarCredito(java.awt.Frame parent, String idboti, Administracion obj) {
        initComponents();
        objeto = obj;
        idbotica = idboti;
        cargarBoticas();
        cargarEmpresas();
        modelClientes = (DefaultTableModel) jTable1.getModel();
        CargasClientes();
        jPanel1.setVisible(false);
    }

    private void CargasClientes() {
        LimpiatTabla();
        String filtro;
        listaclientes.removeAll(listaclientes);
        filtro = this.jTextField1.getText().trim();
        filtro = filtro + '%';
        listaclientes = objClientes.listaClientes_Detallado(idbotica, filtro);

        for (int i = 0; i < listaclientes.size(); i++) {
            datosDetalle[0] = listaclientes.get(i).getId_Cliente();
            datosDetalle[1] = listaclientes.get(i).getNombre_RazonSocial();
            datosDetalle[2] = listaclientes.get(i).getEmpresa();
            datosDetalle[3] = listaclientes.get(i).getRUC_DNI();
            datosDetalle[4] = listaclientes.get(i).getSaldo();
            modelClientes.addRow(datosDetalle);
        }
    }

    private void activadesactiva(Boolean Estado) {
        lasempresas.setEnabled(Estado);
        TxtNombre.setEnabled(Estado);
        aumento.setEnabled(Estado);
        saldoActual.setText("");
        aumento.setText("");
        jButton1.setEnabled(Estado);
        btnNuevo.setEnabled(!Estado);
        btnGuardar.setEnabled(Estado);
        btnCancelar.setEnabled(Estado);
        lasempresas.setSelectedIndex(0);
        TxtNombre.setText("");
        TxtCodigo.setText("");
    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelClientes.removeRow(i);
            }
        }
    }

    private void cargarBoticas() {
        listaboticas = objBoticas.ListarobjBoticas();
    }

    public void AsignaDatosCliente() {
        try {

            if (this.jTable1.getRowCount() > 0) {
                this.LimpiatTabla();
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cargarEmpresas() {
        listEmpresas = objEmpresas.ListaEmpresas();
        lasempresas.addItem("Seleccione Opcion");
        for (int i = 0; i < listEmpresas.size(); i++) {
            lasempresas.addItem(listEmpresas.get(i).getRazonSocial());
        }

    }
    private String[] ArrayBoticas = {};

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lasempresas = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        TxtCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtNombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        saldoActual = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        aumento = new javax.swing.JTextField();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(RenovarCredito.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("aumento.font")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CLIENTE", "EMPRESA", "RUC", "SALDO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        btnNuevo.setMnemonic('N');
        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setMnemonic('G');
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setMnemonic('C');
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir1.setText(resourceMap.getString("btnSalir1.text")); // NOI18N
        btnSalir1.setName("btnSalir1"); // NOI18N
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lasempresas.setFont(resourceMap.getFont("lasempresas.font")); // NOI18N
        lasempresas.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasempresas.setEnabled(false);
        lasempresas.setName("lasempresas"); // NOI18N
        lasempresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasempresasActionPerformed(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        TxtCodigo.setFont(resourceMap.getFont("TxtCodigo.font")); // NOI18N
        TxtCodigo.setEnabled(false);
        TxtCodigo.setName("TxtCodigo"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        TxtNombre.setFont(resourceMap.getFont("TxtCodigo.font")); // NOI18N
        TxtNombre.setEnabled(false);
        TxtNombre.setName("TxtNombre"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2);

        saldoActual.setBackground(resourceMap.getColor("saldoActual.background")); // NOI18N
        saldoActual.setEditable(false);
        saldoActual.setFont(resourceMap.getFont("saldoActual.font")); // NOI18N
        saldoActual.setForeground(resourceMap.getColor("saldoActual.foreground")); // NOI18N
        saldoActual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldoActual.setName("saldoActual"); // NOI18N
        saldoActual.setPreferredSize(new java.awt.Dimension(100, 22));
        jPanel1.add(saldoActual);

        jLabel3.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3);

        aumento.setBackground(resourceMap.getColor("aumento.background")); // NOI18N
        aumento.setEditable(false);
        aumento.setFont(resourceMap.getFont("aumento.font")); // NOI18N
        aumento.setForeground(resourceMap.getColor("aumento.foreground")); // NOI18N
        aumento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        aumento.setName("aumento"); // NOI18N
        aumento.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel1.add(aumento);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lasempresas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(69, 69, 69))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lasempresas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSalir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasempresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasempresasActionPerformed
}//GEN-LAST:event_lasempresasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String idclien = null;
        String saldo;

        FormClientesPorEmpresa2 p = new FormClientesPorEmpresa2(this, objetoventana, lasempresas.getItemAt(lasempresas.getSelectedIndex()).toString());
        p.show(true);

        String nom = p.getNombre_RazonSocial();
        idclien = String.valueOf(p.getId_Cliente());
        saldo = String.valueOf(FormClientesPorEmpresa2.getSaldo());

        if (nom != null && idclien != null) {
            TxtNombre.setText(nom);
            TxtCodigo.setText(idclien);
            saldoActual.setText(saldo);
            jPanel1.setVisible(true);
            aumento.setEditable(true);
            aumento.requestFocus();
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        activadesactiva(true);
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Integer pase = 1;

        if (this.TxtCodigo.getText().length() < 1) {
            pase = 0;
        }
        if (aumento.getText().length() < 1) {
            pase = 0;
        }
        try {
            if (new Double(aumento.getText().toString()) + 1 < 1) {
            }
        } catch (Exception Ex) {
            pase = 0;
        }

        if (pase == 1) {

            int p = JOptionPane.showConfirmDialog(null, " Desea Aumentar el Credito de este Cliente ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                entidadClietnes = new Clientes(
                        Integer.parseInt(this.TxtCodigo.getText().trim()),
                        lasempresas.getItemAt(lasempresas.getSelectedIndex()).toString(),
                        new Double(aumento.getText().toString()));


                if (objClientes.AumentarLinea(entidadClietnes, idbotica)) {
                    JOptionPane.showMessageDialog(this, "Se ha aumentado la linea de Credito", "CREDITO AUMENTADO", JOptionPane.INFORMATION_MESSAGE);
                    LimpiatTabla();
                    CargasClientes();
                    jPanel1.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Se ha producido un error, No se guardo", "Error", JOptionPane.ERROR_MESSAGE);
                }

                activadesactiva(false);


            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique, datos Incorrectos: Cliente, Monto", "Complete Datos", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        jPanel1.setVisible(false);
        TxtCodigo.setText("");
        TxtNombre.setText("");
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        objeto.marcacdo = false;
        dispose();
}//GEN-LAST:event_btnSalir1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        CargasClientes();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CargasClientes();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtCodigo;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JTextField aumento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox lasempresas;
    private javax.swing.JTextField saldoActual;
    // End of variables declaration//GEN-END:variables
}
