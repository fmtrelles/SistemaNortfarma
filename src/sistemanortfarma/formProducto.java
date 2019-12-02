/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * formProducto1.java
 *
 * Created on 18/10/2012, 10:15:32 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaProducto;
import entidad.ProductosPrecios;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class formProducto extends javax.swing.JDialog {

    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
    LogicaProducto objproducto = new LogicaProducto();
    private String idbotica;
    Object[] datosDetalle = new Object[7];
    private DefaultTableModel model;
    private static String idproducto;
    private static String Nombre_Producto;
    private static String idtipoprecio;
    FormBoletas_Manuales formBoleta;

    /** Creates new form formProducto1 */
    public formProducto(java.awt.Frame parent, boolean modal, String idboti, FormBoletas_Manuales obj) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        idbotica = idboti;
        formBoleta = obj;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        Listar_Productos("");
        this.setIdproducto(null);
        this.setIdtipoprecio(null);
        this.setNombre_Producto("");
    }

    public formProducto(java.awt.Frame parent, boolean modal, String idboti, FormBoletas_Manuales obj,String INV) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        idbotica = idboti;
        formBoleta = obj;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        Listar_ProductosINV("",INV);
        this.setIdproducto(null);
        this.setIdtipoprecio(null);
        this.setNombre_Producto("");
        this.jTextField1.setEnabled(false);
    }

    public formProducto(java.awt.Frame parent, boolean modal, String idboti, FormBoletas_Manuales obj, String INV, String totalX) {
        super(parent, modal);
        initComponents();
        idbotica = idboti;
        formBoleta = obj;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        Listar_ProductosINV("",INV);
        this.setIdproducto(null);
        this.setIdtipoprecio(null);
        this.setNombre_Producto("");
        this.jTextField1.setEnabled(false);
        Selecciona_productoX(totalX);
    }

    public static String getNombre_Producto() {
        return Nombre_Producto;
    }

    public static void setNombre_Producto(String Nombre_Producto) {
        formProducto.Nombre_Producto = Nombre_Producto;
    }

    public static String getIdproducto() {
        return idproducto;
    }

    public static void setIdproducto(String idproducto) {
        formProducto.idproducto = idproducto;
    }

    public static String getIdtipoprecio() {
        return idtipoprecio;
    }

    public static void setIdtipoprecio(String idtipoprecio) {
        formProducto.idtipoprecio = idtipoprecio;
    }

    private void Listar_Productos(String produc) {
        try {

            listaProductos.removeAll(listaProductos);
            listaProductos = objproducto.ListarProductos(produc, idbotica, "01", 0, 1);
            LimpiatTabla();

            for (int i = 0; i < listaProductos.size(); i++) {
                datosDetalle[0] = listaProductos.get(i).getProductoBotica().getProducto().getIdProducto();
                datosDetalle[1] = listaProductos.get(i).getProductoBotica().getProducto().getDescripcion();
                datosDetalle[2] = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Empaque();
                datosDetalle[3] = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Fraccion();
                model.addRow(datosDetalle);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        private void Listar_ProductosINV(String produc, String INV) {
        try {

            listaProductos.removeAll(listaProductos);
            listaProductos = objproducto.ListarProductosINV(produc, idbotica, "01", 6, 6);
            LimpiatTabla();

            for (int i = 0; i < listaProductos.size(); i++) {
                datosDetalle[0] = listaProductos.get(i).getProductoBotica().getProducto().getIdProducto();
                datosDetalle[1] = listaProductos.get(i).getProductoBotica().getProducto().getDescripcion();
                datosDetalle[2] = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Empaque();
                datosDetalle[3] = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Fraccion();
                model.addRow(datosDetalle);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void Selecciona_productoX(String totalXX) {
        if(totalXX == "") {
            int fila = this.jTable1.getSelectedRow();

            if (fila >= 0) {

                idproducto = String.valueOf(this.jTable1.getValueAt(fila, 0));
                Nombre_Producto = String.valueOf(this.jTable1.getValueAt(fila, 1));
                idtipoprecio = listaProductos.get(fila).getTipoPrecio().getId_Tipo_Precio();

                this.setIdproducto(idproducto);
                this.setIdtipoprecio(idtipoprecio);
                this.setNombre_Producto(Nombre_Producto);

                try {

                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Porfavor Ingrese el Importe "));

                    if (monto > 0) {
                        this.dispose();
                        formBoleta.Asigna_Producto(monto);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else {
            int fila = 0;

            if (fila >= 0) {

                idproducto = String.valueOf(this.jTable1.getValueAt(fila, 0));
                Nombre_Producto = String.valueOf(this.jTable1.getValueAt(fila, 1));
                idtipoprecio = listaProductos.get(fila).getTipoPrecio().getId_Tipo_Precio();

                this.setIdproducto(idproducto);
                this.setIdtipoprecio(idtipoprecio);
                this.setNombre_Producto(Nombre_Producto);

                try {

                    double monto = Double.parseDouble(totalXX);
                    if (monto > 0) {
                        this.dispose();
                        formBoleta.Asigna_Producto(monto);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void Selecciona_producto() {
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            idproducto = String.valueOf(this.jTable1.getValueAt(fila, 0));
            Nombre_Producto = String.valueOf(this.jTable1.getValueAt(fila, 1));
            idtipoprecio = listaProductos.get(fila).getTipoPrecio().getId_Tipo_Precio();

            this.setIdproducto(idproducto);
            this.setIdtipoprecio(idtipoprecio);
            this.setNombre_Producto(Nombre_Producto);

            try {

                double monto = Double.parseDouble(JOptionPane.showInputDialog("Porfavor Ingrese el Importe "));

                if (monto > 0) {
                    this.dispose();
                    formBoleta.Asigna_Producto(monto);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex1) {
                JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Importe Correcto ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void RealizaOpciones(KeyEvent evt) {

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.dispose();
        }
    }

    private void LimpiatTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(formProducto.class);
        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        jButton2.setBackground(resourceMap.getColor("jButton2.background")); // NOI18N
        jButton2.setForeground(resourceMap.getColor("jButton2.foreground")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(79, 43));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODPRO", "PRODUCTO", "UND", "FRACC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
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
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(320);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setForeground(resourceMap.getColor("jButton3.foreground")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(79, 43));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                        .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.Listar_Productos(this.jTextField1.getText().trim() + '%');
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        }

        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Selecciona_producto();
        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    // End of variables declaration//GEN-END:variables
}
