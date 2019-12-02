package sistemanortfarma;

import CapaLogica.LogicaClientes;
import entidad.Clientes;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class BusquedaCliente extends JDialog {

    List<Clientes> lista = new ArrayList<Clientes>();
    Object[] arrayclientes = new Object[5];
    private DefaultTableModel tablaclientes;
    String ruc, dni, telefono, nombre, direccion;
    int idcliente;
    private static Clientes obcliente;
    Cotizacion objcotiza;
    int fila = -1;

    /** Creates new form BusquedaCliente */
    public BusquedaCliente(Cotizacion ob, Frame frame) {
        super(frame, true);
        initComponents();
        objcotiza = ob;
        this.setLocationRelativeTo(null);
        tablaclientes = (DefaultTableModel) jTable1.getModel();
    }

    public static Clientes getObcliente() {
        return obcliente;
    }

    public static void setObcliente(Clientes obcliente) {
        BusquedaCliente.obcliente = obcliente;
    }

    private void BuscaCliente() {
        if (jTextField1.getText().trim().compareTo("") != 0) {
            LogicaClientes logcliente = new LogicaClientes();
            LimpiatTabla();
            int ind = this.jComboBox1.getSelectedIndex();

            if (ind == 0) {
                String telef = this.jTextField1.getText().trim();
                telef = telef + '%';
                lista = logcliente.BuscarCliente(telef, ind);

            } else {
                if (ind == 1) {
                    String nom = this.jTextField1.getText().trim();
                    nom = nom + '%';
                    lista = logcliente.BuscarCliente(nom, ind);
                } else {
                    ruc = this.jTextField1.getText().trim();
                    ruc = ruc + '%';
                    lista = logcliente.BuscarCliente(ruc, ind);
                }
            }

            for (int i = 0; i < lista.size(); i++) {
                arrayclientes[0] = lista.get(i).getNombre_RazonSocial();
                tablaclientes.addRow(arrayclientes);
            }

        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE DATOS PARA LA BUSQUEDA ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void CapturaDatos(int fila) {

        try {

            dni = lista.get(fila).getDNI();
            direccion = lista.get(fila).getDireccion();
            ruc = lista.get(fila).getRUC_DNI();
            telefono = lista.get(fila).getTelefono();
            nombre = lista.get(fila).getNombre_RazonSocial();

            this.jTextField4.setText(dni);
            this.jTextField5.setText(ruc);
            this.jTextField6.setText(direccion);
            this.jTextField3.setText(telefono);
            this.jTextField7.setText(nombre);

        } catch (Exception ex) {
        }
    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaclientes.removeRow(i);
            }
        }
    }

    private void RealizaOpciones(KeyEvent evt) {
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
            this.hide();
        } else if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            this.jTextField1.requestFocus();
        } else if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
            Color p = new Color(255, 255, 255);
            if (fila >= 0) {
                this.jTextField4.setEditable(true);
                this.jTextField5.setEditable(true);
                this.jTextField6.setEditable(true);
                this.jTextField3.setEditable(true);

                this.jTextField4.setBackground(p);
                this.jTextField5.setBackground(p);
                this.jTextField6.setBackground(p);
                this.jTextField3.setBackground(p);

                this.jTextField4.requestFocus();
            }
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F6") == 0) {
            Color p = new Color(163, 205, 244);
            if (fila >= 0) {
                this.jTextField4.setEditable(false);
                this.jTextField5.setEditable(false);
                this.jTextField6.setEditable(false);
                this.jTextField3.setEditable(false);

                this.jTextField4.setBackground(p);
                this.jTextField5.setBackground(p);
                this.jTextField6.setBackground(p);
                this.jTextField3.setBackground(p);

                this.jTable1.requestFocus();
            }

        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            dni = jTextField4.getText().trim();
            ruc = jTextField5.getText().trim();
            direccion = jTextField6.getText().trim();
            telefono = jTextField3.getText().trim();

            if (jTextField4.getText().trim().compareTo("") == 0 && lista.get(fila).getDNI().compareTo("") != 0) {
                dni = lista.get(fila).getDNI();
            }

            boolean resul = ModificaDatos();
            if (resul) {
                EnviarDatos();
            }
        }
    }

    private boolean ModificaDatos() {
        LogicaClientes log = new LogicaClientes();

        try {
            idcliente = lista.get(fila).getId_Cliente();
            obcliente = new Clientes(idcliente, ruc, nombre, direccion, dni, telefono);
            boolean band = log.ModificaCliente(obcliente);

            if (band == true) {
                return true;
            }

        } catch (Exception ex) {
        }

        return false;
    }

    private void EnviarDatos() {
        setObcliente(obcliente);
        objcotiza.AsignaClientes();
        this.hide();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BusquedaCliente.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jLayeredPane1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jLayeredPane1.border.titleFont"))); // NOI18N
        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1);

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Telefono", "Nombre", "RUC" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(80, 20));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });
        jPanel2.add(jComboBox1);

        jTextField1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setNextFocusableComponent(jTable1);
        jTextField1.setPreferredSize(new java.awt.Dimension(180, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField1);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel2.setBounds(10, 20, 420, 30);
        jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 450, 60));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(220);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 360, 170));

        jLayeredPane2.setBackground(resourceMap.getColor("jLayeredPane2.background")); // NOI18N
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane2.border.title"))); // NOI18N
        jLayeredPane2.setFocusable(false);
        jLayeredPane2.setFont(resourceMap.getFont("jLayeredPane2.font")); // NOI18N
        jLayeredPane2.setName("jLayeredPane2"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setFocusable(false);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(10, 110, 60, 20);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setFocusable(false);
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(10, 30, 50, 20);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 70, 30, 20);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(10, 90, 60, 14);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        jTextField3.setBounds(70, 110, 110, 20);
        jLayeredPane2.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField4.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.setNextFocusableComponent(jTextField5);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jTextField4.setBounds(70, 50, 110, 20);
        jLayeredPane2.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField5.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField5.setEditable(false);
        jTextField5.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField5.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.setNextFocusableComponent(jTextField6);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        jTextField5.setBounds(70, 70, 110, 20);
        jLayeredPane2.add(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField6.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField6.setEditable(false);
        jTextField6.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.setNextFocusableComponent(jTextField3);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        jTextField6.setBounds(70, 90, 240, 20);
        jLayeredPane2.add(jTextField6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 50, 30, 20);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField7.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField7.setEditable(false);
        jTextField7.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField7.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.setNextFocusableComponent(jTextField5);
        jTextField7.setBounds(70, 30, 240, 20);
        jLayeredPane2.add(jTextField7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 320, 150));

        jLabel7.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 274, 150, 20));

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 274, 90, 20));

        jButton2.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, -1, -1));

        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 110, -1));

        jLabel9.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 274, 160, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        BuscaCliente();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscaCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

        fila = this.jTable1.getSelectedRow();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (fila >= 0) {
                CapturaDatos(fila);
                idcliente = lista.get(fila).getId_Cliente();
                obcliente = new Clientes(idcliente, ruc, nombre, direccion, dni, telefono);
                EnviarDatos();
            }
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        fila = this.jTable1.getSelectedRow();
        CapturaDatos(fila);
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
