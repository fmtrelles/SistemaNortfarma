package sistemanortfarma;

import CapaLogica.LogicaZonas;
import CapaLogica.LogicaBoticas;
import entidad.Boticas;
import entidad.TipoZona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author MIGUEL
 */
public class BoticasZona extends javax.swing.JDialog {

    LogicaZonas objZonas = new LogicaZonas();
    List<TipoZona> listaZonas;
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<Boticas> listaBoticas = new ArrayList();
    DefaultListModel modeloPermitidos = new DefaultListModel();
    DefaultListModel modeloDisponibles = new DefaultListModel();
    Administracion obj;

    /** Creates new form BoticasZona1 */
    public BoticasZona(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarZonas();
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
    }

    private void cargarZonas() {

        listaZonas = objZonas.ListarZonas();
        jComboBox1.addItem("Seleccione Opcion");

        for (int i = 0; i < listaZonas.size(); i++) {
            jComboBox1.addItem(listaZonas.get(i).getDescripcion());
        }

    }

    private void cargarDisponibles(String miZona) {

        modeloDisponibles.removeAllElements();
        jList1.setModel(modeloDisponibles);
        listaBoticas.removeAll(listaBoticas);

        listaBoticas = objBoticas.ListarBoticasLibresZonas(miZona);

        for (int i = 0; i < listaBoticas.size(); i++) {
            modeloDisponibles.addElement(listaBoticas.get(i).getDescripcion());
        }

        jList1.setModel(modeloDisponibles);

    }

    private void cargarPermitidos(String miZona) {

        modeloPermitidos.removeAllElements();
        jList2.setModel(modeloPermitidos);
        listaBoticas.removeAll(listaBoticas);

        listaBoticas = objBoticas.ListarBoticasDisponiblesZonas(miZona);

        for (int i = 0; i < listaBoticas.size(); i++) {
            modeloPermitidos.addElement(listaBoticas.get(i).getDescripcion());
        }

        jList2.setModel(modeloPermitidos);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BoticasZona.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N
        jXTitledPanel1.getContentContainer().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(roles));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXTitledPanel1.getContentContainer().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jXTitledPanel1.getContentContainer().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 44, -1));

        jLabel3.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jXTitledPanel1.getContentContainer().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 73, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new javax.swing.DefaultComboBoxModel(permitidos));
        jList1.setName("jList1"); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jXTitledPanel1.getContentContainer().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 176, 262));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList2.setModel(new javax.swing.DefaultComboBoxModel(disponibles));
        jList2.setName("jList2"); // NOI18N
        jList2.setPreferredSize(new java.awt.Dimension(0, 300));
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jXTitledPanel1.getContentContainer().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 177, 262));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 50, 30));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 50, 30));

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 50, 30));

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 50, 30));

        jButton6.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 367, -1, -1));

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jXTitledPanel1.getContentContainer().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 90, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        Object w = this.jComboBox1.getSelectedItem();
        Integer indice = this.jComboBox1.getSelectedIndex();

        if (indice > 0) {

            cargarPermitidos((String) w);
            cargarDisponibles((String) w);

        } else {

            modeloDisponibles.removeAllElements();
            modeloPermitidos.removeAllElements();

        }
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (this.jList1.getSelectedIndex() < 0) {

            jLabel1.setText("");

        } else {

            Integer indice = this.jList1.getSelectedIndex();

            jLabel1.setText(String.valueOf(indice));

        }
}//GEN-LAST:event_jList1ValueChanged

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        if (this.jList2.getSelectedIndex() < 0) {

            jLabel2.setText("");

        } else {

            Integer indice = this.jList2.getSelectedIndex();

            jLabel2.setText(String.valueOf(indice));

        }
}//GEN-LAST:event_jList2ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jLabel1.getText().length() > 0) {

            for (Integer begin = 0; begin < modeloDisponibles.getSize(); begin++) {
                modeloPermitidos.addElement(modeloDisponibles.getElementAt(begin).toString());
            }

            modeloDisponibles.removeAllElements();
            jList1.setModel(modeloDisponibles);

        } else {
            System.out.println("Nada");
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (jLabel1.getText().length() > 0) {
            modeloPermitidos.addElement(modeloDisponibles.get(Integer.valueOf(jLabel1.getText())).toString());
            modeloDisponibles.remove(Integer.valueOf(jLabel1.getText()));
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (jLabel2.getText().length() > 0) {
            modeloDisponibles.addElement(modeloPermitidos.get(Integer.valueOf(jLabel2.getText())).toString());
            modeloPermitidos.remove(Integer.valueOf(jLabel2.getText()));
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        if (jLabel2.getText().length() > 0) {

            for (Integer begin = 0; begin < modeloPermitidos.getSize(); begin++) {
                modeloDisponibles.addElement(modeloPermitidos.getElementAt(begin).toString());
            }

            modeloPermitidos.removeAllElements();
            jList2.setModel(modeloPermitidos);

            //System.out.println("Moviendo todos");
        } else {
            System.out.println("Nada");
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        Object w = this.jComboBox1.getSelectedItem();
        String paginasPermitidas = "";

        //Obteniendo las paginas
        for (Integer begin = 0; begin < modeloPermitidos.getSize(); begin++) {

            paginasPermitidas = paginasPermitidas + modeloPermitidos.getElementAt(begin).toString();

            if (begin + 1 < modeloPermitidos.getSize()) {
                paginasPermitidas = paginasPermitidas + ",";
            }

        }

        objBoticas.ActualizarBoticasZona((String) w, paginasPermitidas, modeloPermitidos.getSize());

        JOptionPane.showMessageDialog(null, "Actualizacion de Zona Realizada", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        obj.marcacdo = false;
        this.hide();
    }//GEN-LAST:event_jButton5ActionPerformed
    private String[] roles = {};
    private String[] disponibles = {};
    private String[] permitidos = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    // End of variables declaration//GEN-END:variables
}
