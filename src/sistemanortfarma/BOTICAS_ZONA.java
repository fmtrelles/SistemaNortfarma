package sistemanortfarma;

import CapaLogica.LogicaZonas;
import CapaLogica.LogicaBoticas;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class BOTICAS_ZONA extends javax.swing.JFrame {

    LogicaZonas objZonas = new LogicaZonas();
    List<entidad.TipoZona> listaZonas;
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<entidad.Boticas> listaBoticas;
    DefaultListModel modeloPermitidos = new DefaultListModel();
    DefaultListModel modeloDisponibles = new DefaultListModel();

    public BOTICAS_ZONA() {
        initComponents();
        setLocationRelativeTo(null);
        cargarZonas();
    }

    private void cargarZonas() {

        listaZonas = objZonas.ListarZonas();

        jComboBox1.addItem("Seleccione Opcion");

        for (int i = 0; i < listaZonas.size(); i++) {
            jComboBox1.addItem(listaZonas.get(i).getDescripcion());
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BOTICAS_ZONA.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(roles));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.setBounds(70, 10, 240, 20);
        jLayeredPane1.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(20, 10, 40, 16);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 330, 41));

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 264, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, -1));

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 70, 130));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, 260));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 220, 320));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, 270));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 200, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarDisponibles(String miZona) {

        modeloDisponibles.removeAllElements();

        listaBoticas = objBoticas.ListarBoticasLibresZonas(miZona);

        for (int i = 0; i < listaBoticas.size(); i++) {

            modeloDisponibles.addElement(listaBoticas.get(i).getDescripcion());
        }

        jList1.setModel(modeloDisponibles);

    }

    private void cargarPermitidos(String miZona) {

        modeloPermitidos.removeAllElements();

        listaBoticas = objBoticas.ListarBoticasDisponiblesZonas(miZona);

        for (int i = 0; i < listaBoticas.size(); i++) {
            modeloPermitidos.addElement(listaBoticas.get(i).getDescripcion());
        }

        jList2.setModel(modeloPermitidos);

    }

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (jLabel2.getText().length() > 0) {
            modeloDisponibles.addElement(modeloPermitidos.get(Integer.valueOf(jLabel2.getText())).toString());
            modeloPermitidos.remove(Integer.valueOf(jLabel2.getText()));
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (jLabel1.getText().length() > 0) {
            modeloPermitidos.addElement(modeloDisponibles.get(Integer.valueOf(jLabel1.getText())).toString());
            modeloDisponibles.remove(Integer.valueOf(jLabel1.getText()));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jLabel1.getText().length() > 0) {

            for (Integer begin = 0; begin < modeloDisponibles.getSize(); begin++) {
                modeloPermitidos.addElement(modeloDisponibles.getElementAt(begin).toString());
            }

            modeloDisponibles.removeAllElements();
            jList1.setModel(modeloDisponibles);

            //System.out.println("Moviendo todos");
        } else {
            System.out.println("Nada");
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        Object w = this.jComboBox1.getSelectedItem();
        Integer indice = this.jComboBox1.getSelectedIndex();

        if (indice > 0) {

            cargarPermitidos((String) w);
            cargarDisponibles((String) w);

        } else {

            /*modeloDisponibles.removeAllElements();
            modeloPermitidos.removeAllElements();*/
        }
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        if (this.jList2.getSelectedIndex() < 0) {

            jLabel2.setText("");

        } else {

            Integer indice = this.jList2.getSelectedIndex();

            jLabel2.setText(String.valueOf(indice));

        }
}//GEN-LAST:event_jList2ValueChanged

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        //System.out.println(this.jList1.getSelectedIndex());
        //System.out.println(this.jList1.getSelectedValue());

        if (this.jList1.getSelectedIndex() < 0) {

            jLabel1.setText("");

        } else {

            Integer indice = this.jList1.getSelectedIndex();

            jLabel1.setText(String.valueOf(indice));

        }
}//GEN-LAST:event_jList1ValueChanged

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        // System.out.println(this.jList1.getSelectedIndex());
        // System.out.println(this.jList1.getSelectedValue());
        //jLabel1.setText( (String) this.jList1.getSelectedValue());
    }//GEN-LAST:event_jList1MouseClicked

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
    private String[] roles = {};
    private String[] disponibles = {};
    private String[] permitidos = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}