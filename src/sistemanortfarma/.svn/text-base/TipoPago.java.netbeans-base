package sistemanortfarma;

import CapaLogica.LogicaTiposPagos;
import entidad.TiposPagos;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author MIguel Gomez
 */
public class TipoPago extends javax.swing.JDialog {

    LogicaTiposPagos objTipoPago = new LogicaTiposPagos();
    List<TiposPagos> listatipopagos;
    DefaultTableModel modeloTabla;
    TableColumn columnaTabla = null;
    Administracion objeto = null;

    /** Creates new form TipoPago1 */
    public TipoPago(java.awt.Frame parent, Administracion obj) {
        super(parent, true);
        initComponents();
        objeto = obj;
        this.setLocationRelativeTo(null);
        AparienciaTabla();
        modeloTabla = new DefaultTableModel();
        llenarTablaTipoPagos();
        tmpLbl.setVisible(false);
        aviso.setVisible(false);
    }

    private void llenarTablaTipoPagos() {

        for (Integer borrando = 0; borrando < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(borrando);
        }

        modeloTabla.addColumn("ITEM");
        modeloTabla.addColumn("DESCRIPCION");

        listatipopagos = objTipoPago.ListarTipoPago();

        for (int i = 0; i < listatipopagos.size(); i++) {

            Object[][] data = {
                {i + 1,
                    listatipopagos.get(i).getDescripcion().toString()}};

            modeloTabla.addRow(data[0]);

        }


        jTable1.setModel(modeloTabla);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(240);
        columnaTabla.setMinWidth(240);
        columnaTabla.setMaxWidth(240);



    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        aviso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }}
            ;
            jLayeredPane3 = new javax.swing.JLayeredPane();
            jLabel1 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            tmpLbl = new javax.swing.JLabel();
            jButton3 = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(TipoPago.class);
            setTitle(resourceMap.getString("Form.title")); // NOI18N
            setName("Form"); // NOI18N
            setResizable(false);
            setUndecorated(true);

            jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
            jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
            jPanel1.setName("jPanel1"); // NOI18N

            jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
            jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
            jLabel2.setName("jLabel2"); // NOI18N

            descripcion.setEnabled(false);
            descripcion.setName("descripcion"); // NOI18N

            jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
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

            aviso.setFont(resourceMap.getFont("aviso.font")); // NOI18N
            aviso.setForeground(resourceMap.getColor("aviso.foreground")); // NOI18N
            aviso.setText(resourceMap.getString("aviso.text")); // NOI18N
            aviso.setName("aviso"); // NOI18N

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(aviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap(32, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, 0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descripcion)
                            .addComponent(jLabel2)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(aviso))
                    .addGap(51, 51, 51))
            );

            jScrollPane1.setName("jScrollPane1"); // NOI18N

            jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
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
            jTable1.setColumnSelectionAllowed(true);
            jTable1.setName("jTable1"); // NOI18N
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTable1KeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(jTable1);
            jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

            jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
            jLayeredPane3.setName("jLayeredPane3"); // NOI18N

            jLabel1.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
            jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
            jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
            jLabel1.setName("jLabel1"); // NOI18N
            jLabel1.setBounds(120, 10, 80, 14);
            jLayeredPane3.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

            jLabel3.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
            jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
            jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
            jLabel3.setName("jLabel3"); // NOI18N
            jLabel3.setBounds(10, 10, 20, -1);
            jLayeredPane3.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

            jLabel4.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
            jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
            jLabel4.setName("jLabel4"); // NOI18N
            jLabel4.setBounds(220, 10, 50, -1);
            jLayeredPane3.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

            jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
            jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
            jLabel5.setName("jLabel5"); // NOI18N
            jLabel5.setBounds(40, 10, 80, -1);
            jLayeredPane3.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

            tmpLbl.setText(resourceMap.getString("tmpLbl.text")); // NOI18N
            tmpLbl.setName("tmpLbl"); // NOI18N

            jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
            jButton3.setName("jButton3"); // NOI18N
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
            jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
            jXTitledPanel1Layout.setHorizontalGroup(
                jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tmpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jXTitledPanel1Layout.setVerticalGroup(
                jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(tmpLbl))
                                .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3)
                            .addGap(3, 3, 3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)))
                    .addGap(15, 15, 15))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (descripcion.getText().trim().length() > 0) {
            if (aviso.getText().trim().length() > 0) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

                objTipoPago.ActualizarTipoPago(tmpLbl.getText().trim(), descripcion.getText().trim());
                descripcion.setText("");
                aviso.setText("");


            } else {
                objTipoPago.AgregarTipoDocumentoPago(descripcion.getText().trim());
                JOptionPane.showMessageDialog(null, "Registro Agregado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Digitar la Descripcion, para poder continuar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
        }

        llenarTablaTipoPagos();
        descripcion.setEnabled(false);
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        aviso.setText("");
        descripcion.setEnabled(true);
        descripcion.setText("");
        descripcion.requestFocus();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        Integer Resultado = 0;

        switch (evt.getKeyCode()) {

            case 127:

                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) modeloTabla.getValueAt(jTable1.getSelectedRow(), 1), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    Resultado = objTipoPago.EliminarTipoPago((String) modeloTabla.getValueAt(jTable1.getSelectedRow(), 1));

                    if (Resultado == 1) {
                        JOptionPane.showMessageDialog(null, "Registro Eliminado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                        descripcion.setText("");
                        descripcion.setEnabled(false);
                        aviso.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Existen Datos Asociados a este Registro, no se procedera a Eliminarlo sin Antes eliminar aquellos registros", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                    }

                    llenarTablaTipoPagos();

                }

                break;

            case 113:

                descripcion.setEnabled(true);
                descripcion.requestFocus();
                aviso.setVisible(true);
                aviso.setText("Se esta Editando : " + modeloTabla.getValueAt(jTable1.getSelectedRow(), 1).toString());
                tmpLbl.setText(modeloTabla.getValueAt(jTable1.getSelectedRow(), 1).toString());
                descripcion.setText(modeloTabla.getValueAt(jTable1.getSelectedRow(), 1).toString());
                break;

        }
}//GEN-LAST:event_jTable1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        objeto.marcacdo = false;
        this.hide();
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aviso;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private javax.swing.JLabel tmpLbl;
    // End of variables declaration//GEN-END:variables
}
