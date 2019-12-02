
package sistemanortfarma;

import CapaLogica.LogicaVenta;
import entidad.TipoDocumento;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Tipo_Ventas_Mantenimiento extends javax.swing.JFrame {

   LogicaVenta objTipoVentas=new LogicaVenta();
   List<TipoDocumento> listatipoventas;

   DefaultListModel modeloCajas = new DefaultListModel();
   DefaultTableModel modeloTabla;
   Object[][] arregloTV;
   TableColumn  columnaTabla = null;

    private int i;

   
    public Tipo_Ventas_Mantenimiento() {

        modeloTabla = new DefaultTableModel();
        initComponents();
        setLocationRelativeTo(null);
        aviso.setVisible(false);
        llenarTablaTipoVentas();
        this.tmpLbl.setVisible(false);
  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        aviso = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }}
            ;
            jSeparator1 = new javax.swing.JSeparator();
            tmpLbl = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Tipo_Ventas_Mantenimiento.class);
            setTitle(resourceMap.getString("Form.title")); // NOI18N
            setName("Form"); // NOI18N
            setResizable(false);
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane2.border.title"))); // NOI18N
            jLayeredPane2.setName("jLayeredPane2"); // NOI18N

            jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
            jLabel2.setName("jLabel2"); // NOI18N
            jLabel2.setBounds(30, 30, 100, 14);
            jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

            descripcion.setText(resourceMap.getString("descripcion.text")); // NOI18N
            descripcion.setEnabled(false);
            descripcion.setName("descripcion"); // NOI18N
            descripcion.setBounds(130, 30, 240, 20);
            jLayeredPane2.add(descripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);

            aviso.setForeground(resourceMap.getColor("aviso.foreground")); // NOI18N
            aviso.setText(resourceMap.getString("aviso.text")); // NOI18N
            aviso.setName("aviso"); // NOI18N
            aviso.setBounds(130, 60, 240, 14);
            jLayeredPane2.add(aviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

            jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
            jButton1.setName("jButton1"); // NOI18N
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jButton1.setBounds(20, 50, 100, 23);
            jLayeredPane2.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

            jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
            jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
            jButton2.setName("jButton2"); // NOI18N
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            jButton2.setBounds(390, 30, 30, 25);
            jLayeredPane2.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

            getContentPane().add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 444, 90));

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
            jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTable1KeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(jTable1);

            getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 170, 260));

            jSeparator1.setName("jSeparator1"); // NOI18N
            getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 430, -1));

            tmpLbl.setText(resourceMap.getString("tmpLbl.text")); // NOI18N
            tmpLbl.setName("tmpLbl"); // NOI18N
            getContentPane().add(tmpLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode()){

            case 127:

                          int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro:" +  (String) modeloTabla.getValueAt(jTable1.getSelectedRow(), 1) , "NORTFARMA", JOptionPane.YES_NO_OPTION);

                           if (reply == JOptionPane.YES_OPTION){
                        
                                objTipoVentas.EliminarTipoDocumentoVenta((String) modeloTabla.getValueAt(jTable1.getSelectedRow(), 1));
                                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                llenarTablaTipoVentas();

                            }

                        break;

            case 113:

                        descripcion.setEnabled(true);
                         descripcion.requestFocus();
                        aviso.setVisible(true);
                        aviso.setText("Se esta Editando : " + modeloTabla.getValueAt( jTable1.getSelectedRow(),1 ).toString());
                        tmpLbl.setText(modeloTabla.getValueAt( jTable1.getSelectedRow(),1 ).toString());
                        descripcion.setText(modeloTabla.getValueAt( jTable1.getSelectedRow(),1 ).toString());
                        break;

        }
        

    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(descripcion.getText().trim().length()>0){
            if(aviso.getText().trim().length()>0){
                JOptionPane.showMessageDialog(null, "Registro Actualizado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

                objTipoVentas.ActualizarTipoDocumento(tmpLbl.getText().trim(), descripcion.getText().trim());
                descripcion.setText("");
                aviso.setText("");


            }else{
                objTipoVentas.AgregarTipoDocumentoVenta(descripcion.getText().trim());
                JOptionPane.showMessageDialog(null, "Registro Agregado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe Digitar la Descripcion, para poder continuar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
        }

        llenarTablaTipoVentas();
        descripcion.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        aviso.setText("");
        descripcion.setEnabled(true);
        descripcion.setText("");
        descripcion.requestFocus();


    }//GEN-LAST:event_jButton1ActionPerformed
  
  private void llenarTablaTipoVentas(){

            Integer total = modeloTabla.getRowCount();

            for(Integer borrando=0; borrando < modeloTabla.getRowCount() ;){
                modeloTabla.removeRow(borrando);
            }
            
            modeloTabla.addColumn("ITEM");
            modeloTabla.addColumn("DESCRIPCION");

            
            listatipoventas = objTipoVentas.ListarTipoDocumento();

            for(int i=0;i<listatipoventas.size();i++){

              Object[][] data = {
              { i,
                listatipoventas.get(i).getDescripcion().toString()} };

               modeloTabla.addRow(data[0]);
              
            }
   
             
              jTable1.setModel(modeloTabla);

            columnaTabla = jTable1.getColumnModel().getColumn(0);
            columnaTabla.setPreferredWidth(40);
            columnaTabla.setMinWidth(40);
            columnaTabla.setMaxWidth(40);

            columnaTabla = jTable1.getColumnModel().getColumn(1);
            columnaTabla.setPreferredWidth(125);
            columnaTabla.setMinWidth(125);
            columnaTabla.setMaxWidth(125);

            

    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aviso;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel tmpLbl;
    // End of variables declaration//GEN-END:variables

}
