package sistemanortfarma;

import entidad.Medico;
import CapaLogica.LogicaMedico;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Miguel Gomez S. & Miguel Gomez S.
 */
public class Medicos extends JDialog {

    LogicaMedico logmedico = new LogicaMedico();
    List<Medico> listamedico = new ArrayList<Medico>();
    private DefaultTableModel modelmedicos;    
    String[] datosDetalle = new String[3];
    private static String Id_Medico = null;
    private static String nombremedico = null;
    private static String colegiatura = null;    
    int opdebusqueda = 0;
    MuestraVentana objetoventana = new MuestraVentana();

    public Medicos(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(null);
        modelmedicos = (DefaultTableModel) jTable1.getModel();        
        cargarmedicos();        
        setId_Medico(null);
        jTextField1.requestFocus();
    }

    public static String getColegiatura() {
        return colegiatura;
    }

    public static void setColegiatura(String colegiatura) {
        Medicos.colegiatura = colegiatura;
    }

    public static String getId_Medico() {
        return Id_Medico;
    }

    public static void setId_Medico(String Id_Medico) {
        Medicos.Id_Medico = Id_Medico;
    }

    public static String getNombremedico() {
        return nombremedico;
    }

    public static void setNombremedico(String nombremedico) {
        Medicos.nombremedico = nombremedico;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Medicos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apellidos", "Codigo" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(82, 21));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(240, 21));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Colegiatura", "Codigo", "Medico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        }

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(60, 41));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimpiatTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelmedicos.removeRow(i);
            }
        }
    }

    
    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    private void BuscaMedico() {
        try {

            String filtro = this.jTextField1.getText().trim();
            int cant = filtro.length();

            if (cant != 0) {
                listamedico.removeAll(listamedico);
                filtro = filtro + '%';
                listamedico = logmedico.BuscaMedicos(filtro, opdebusqueda);
                LimpiatTabla();
                cargarmedicos1();
            }
        } catch (Exception ex) {
        }
    }

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
}//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            this.jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
            Modifica_Datos_Medico();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoMedico();
        }        
        
        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        }
        
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField1KeyPressed

    private void RealizaOpciones(KeyEvent evt) {
        try {

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
               jComboBox1.setFocusable(true);
               jComboBox1.requestFocus(true);

            }


        } catch (Exception ex) {
            System.out.println("RealizaOpciones " + ex.getMessage());
        }
    }
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        try {

            if (jTextField1.getText().trim().compareTo("") == 0) {
                Id_Medico = String.valueOf(this.jTable1.getValueAt(0, 1));
                nombremedico = String.valueOf(this.jTable1.getValueAt(0, 2));
                colegiatura = String.valueOf(this.jTable1.getValueAt(0, 0));
                setId_Medico(Id_Medico);
                setNombremedico(nombremedico);
                setColegiatura(colegiatura);
                dispose();

            } else {
                BuscaMedico();
            }

        } catch (Exception ex) {
            System.out.println("CLASE MEDICOS :" + ex.getMessage());
        }
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        opdebusqueda = this.jComboBox1.getSelectedIndex();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            dispose();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }
        
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            recuperaMedico();
            dispose();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            this.jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
            Modifica_Datos_Medico();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoMedico();
        }
        RealizaOpciones(evt);

    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        recuperaMedico();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            recuperaMedico();
            dispose();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoMedico();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Modifica_Datos_Medico();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void NuevoMedico(){
        NuevoMedico pe = new NuevoMedico(this, objetoventana,"","","",1);
        pe.pack();
        pe.setVisible(true);
    }
    
    private void Modifica_Datos_Medico(){
        String colegio_m = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        String c_med = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        String v_med = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        if (c_med.isEmpty() || c_med.equals("") || c_med.equals("00000")){
            JOptionPane.showMessageDialog(this, " PORFAVOR SELECCIONE UN MEDICO ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
        NuevoMedico pe = new NuevoMedico(this, objetoventana,c_med,v_med,colegio_m,2);
        pe.pack();
        pe.setVisible(true);
        }
    }
    
    private void recuperaMedico() {
        try {
            int fila = this.jTable1.getSelectedRow();
            Id_Medico = String.valueOf(this.jTable1.getValueAt(fila, 1));
            nombremedico = String.valueOf(this.jTable1.getValueAt(fila, 2));
            colegiatura = String.valueOf(this.jTable1.getValueAt(fila, 0));
            setId_Medico(Id_Medico);
            setNombremedico(nombremedico);
            setColegiatura(colegiatura);
        } catch (Exception ex) {
            System.out.println("recuperaMedico " + ex.getMessage());
        }
    }

    private void cargarmedicos() {

        listamedico = logmedico.listaMedicos();
        try {
            for (Medico medico : listamedico) {
                datosDetalle[0] = medico.getColegio();
                datosDetalle[1] = medico.getId_Medico();
                datosDetalle[2] = medico.getNombre();
                modelmedicos.addRow(datosDetalle);
            }
        } catch (Exception ex) {
        }
    }

    private void cargarmedicos1() {
        try {
            for (Medico medico : listamedico) {
                datosDetalle[0] = medico.getColegio();
                datosDetalle[1] = medico.getId_Medico();
                datosDetalle[2] = medico.getNombre();
                modelmedicos.addRow(datosDetalle);
            }
        } catch (Exception ex) {
        }
    }
    
    public void MuestraResulMedico(NuevoMedico obj, int id_cliente) {
        BuscaMedico();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
