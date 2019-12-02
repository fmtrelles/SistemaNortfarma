package sistemanortfarma;

import CapaLogica.LogicaRecuperaCaja;
import entidad.Cajas;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SeleccionaCaja extends javax.swing.JDialog {

    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    private String idbotica;
    private Object Nomcaja;
    private int idcaja = -1;
    boolean eligio = false;
    private static int Delivery;
    private static int MarcaDelivery;

    public SeleccionaCaja(java.awt.Frame parent, String id, List<Cajas> listacajas1) {
        super(parent, true);
        initComponents();
        listacajas = listacajas1;
        this.setDefaultCloseOperation(0);
        this.setLocationRelativeTo(null);
        idbotica = id;
        setIdcaja(idcaja);
        RceuperaCajas();
    }

    public static int getMarcaDelivery() {
        return MarcaDelivery;
    }

    public static void setMarcaDelivery(int MarcaDelivery) {
        SeleccionaCaja.MarcaDelivery = MarcaDelivery;
    }

    public static int getDelivery() {
        return Delivery;
    }

    public static void setDelivery(int Delivery) {
        SeleccionaCaja.Delivery = Delivery;
    }

    public Object getNomcaja() {
        return Nomcaja;
    }

    public void setNomcaja(Object Nomcaja) {
        this.Nomcaja = Nomcaja;
    }

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }

    private void RceuperaCajas() {
        this.jComboBox1.addItem("--- Seleccione su Caja ---");
        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(SeleccionaCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setBackground(resourceMap.getColor("jComboBox1.background")); // NOI18N
        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(25, 25));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox1MouseEntered(evt);
            }
        });
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() > 0) {
            Nomcaja = jComboBox1.getSelectedItem();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();
            Delivery = listacajas.get(jComboBox1.getSelectedIndex() - 1).getEsDelivery();
            MarcaDelivery= listacajas.get(jComboBox1.getSelectedIndex() - 1).getMarcadoDelivery();
            setNomcaja(Nomcaja);
            setIdcaja(idcaja);
            setDelivery(Delivery);
            setMarcaDelivery(MarcaDelivery);
            eligio = true;
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseEntered
    }//GEN-LAST:event_jComboBox1MouseEntered

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (eligio) {
                this.hide();
            }
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }

    }//GEN-LAST:event_jComboBox1KeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
