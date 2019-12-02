/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormProductosNuevos.java
 *
 * Created on 14-ago-2012, 10:04:18
 */
package sistemanortfarma;

import CapaLogica.LogicaProducto;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;
import entidad.Laboratorios;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.Tipo_Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormProductosNuevos extends javax.swing.JDialog implements Runnable {

    private DefaultTableModel tablaproforma;
    TableColumn colu_6;
    Thread showThread;

    /** Creates new form FormProductosNuevos */
    public FormProductosNuevos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        tablaproforma = (DefaultTableModel) jTable1.getModel();
        colu_6 = jTable1.getColumnModel().getColumn(6);
        AparienciaTabla();
        OcultaLabel(false);
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tleft = new DefaultTableCellRenderer();
        tleft.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tleft);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tleft);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tleft);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tleft);

        colu_6 = jTable1.getColumnModel().getColumn(8);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        colu_6 = jTable1.getColumnModel().getColumn(9);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        colu_6 = jTable1.getColumnModel().getColumn(5);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        colu_6 = jTable1.getColumnModel().getColumn(6);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        colu_6 = jTable1.getColumnModel().getColumn(10);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

    }

    public void run() {
        GuardarProductos_Nuevos();
        OcultaLabel(false);
    }

    private void MostrarDatos() {
        for (Integer inicio = 0; inicio < tablaproforma.getRowCount(); inicio++) {
            tablaproforma.removeRow(inicio);
        }

        for (Integer inicio = 0; inicio < tablaproforma.getRowCount();) {
            tablaproforma.removeRow(inicio);
        }

        try {
            mostrarGuia(jTextField1.getText().trim());
            if (jTable1.getRowCount() > 0) {
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
                jButton3.setEnabled(true);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void mostrarGuia(String ruta) throws FileNotFoundException, IOException {

        try {

            InputStream inputStream = new FileInputStream(ruta); // take dbf file as program argument
            DBFReader reader = new DBFReader(inputStream);
            Object[] rowObjects;
            Integer contador = 0;

            while ((rowObjects = reader.nextRecord()) != null) {
                Calendar Cal = Calendar.getInstance();
                String fec = Cal.get(Calendar.DATE) + " / " + (Cal.get(Calendar.MONTH) + 1) + " / " + Cal.get(Calendar.YEAR);

                Object[][] data = {
                    {rowObjects[0].toString().trim(),
                        rowObjects[1].toString().trim(),
                        rowObjects[2].toString().trim(),
                        rowObjects[3].toString().trim(),
                        rowObjects[4].toString().trim(),
                        rowObjects[5].toString().trim(),
                        rowObjects[6].toString().trim(),
                        rowObjects[8].toString().trim(),
                        rowObjects[10].toString().trim(),
                        rowObjects[12].toString().trim(),
                        rowObjects[9].toString().trim()
                    }};

                tablaproforma.addRow(data[0]);
                contador++;

            }

            inputStream.close();

        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void GuardarProductos_Nuevos() {
        try {

            Laboratorios laboratorio;
            Producto producto;
            Tipo_Producto tipoProducto;
            ProductosPrecios productoPrecio;
            Productos_Botica productoBotica;

            List<ProductosPrecios> listProducto = new ArrayList<ProductosPrecios>();
            LogicaProducto objProducto = new LogicaProducto();

            for (int i = 0; i < this.jTable1.getRowCount(); i++) {
                if (Double.parseDouble(jTable1.getValueAt(i, 5).toString().trim()) > 0.00) {
                    laboratorio = new Laboratorios();
                    producto = new Producto();
                    tipoProducto = new Tipo_Producto();
                    productoBotica = new Productos_Botica();
                    productoPrecio = new ProductosPrecios();

                    laboratorio.setId_Lab(jTable1.getValueAt(i, 2).toString().trim());
                    producto.setLaboratorio(laboratorio);
                    producto.setIdProducto(jTable1.getValueAt(i, 0).toString().trim());
                    producto.setDescripcion(jTable1.getValueAt(i, 1).toString().trim());
                    producto.setEmpaque(Integer.parseInt(jTable1.getValueAt(i, 7).toString().trim()));
                    producto.setIGV_Exonerado(Double.parseDouble(jTable1.getValueAt(i, 10).toString().trim()));
                    tipoProducto.setId_Tipo_Producto(jTable1.getValueAt(i, 3).toString().trim());
                    producto.setTipoProducto(tipoProducto);
                    productoBotica.setProducto(producto);
                    productoPrecio.setProductoBotica(productoBotica);
                    productoPrecio.setPrecio_Venta(Double.parseDouble(jTable1.getValueAt(i, 5).toString().trim()));
                    productoPrecio.setDescuento_Venta(Double.parseDouble(jTable1.getValueAt(i, 4).toString().trim()));
                    listProducto.add(productoPrecio);
                }
            }

            if (objProducto.AgregaProductoEnvio(listProducto)) {
                dispose();
            }

        } catch (Exception ex) {
            System.out.println("GuardarProductos_Nuevos " + ex.getMessage());
        }
    }

    private void OcultaLabel(boolean valor) {
        jLabel7.setVisible(valor);
        jLabel8.setVisible(valor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormProductosNuevos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        panelNice1.setBackground(resourceMap.getColor("panelNice1.background")); // NOI18N
        panelNice1.setName("panelNice1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODPRO", "PRODUCTO", "LAB", "CODTIP", "DSTPRO", "PRISAL", "COSTOD", "STKFRA", "PORUTI", "CODBAR", "IGV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowHorizontalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setResizable(false);
        jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(10).setResizable(false);
        jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setEnabled(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNice1Layout.createSequentialGroup()
                        .addContainerGap(133, Short.MAX_VALUE)
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addComponent(jLabel7))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DBF (Foxpro Tables)", "DBF");
            filechooser.setFileFilter(filter);

            int result = filechooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                jTextField1.setText(String.valueOf(file));
                if (this.jTextField1.getText().trim().compareTo("") != 0) {
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                    jButton5.setEnabled(true);
                }
            }


        } catch (Exception ex) {
            System.out.println("cargar " + ex.getMessage());
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton3.setEnabled(false);
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);

        jTextField1.setText("");

        for (Integer inicio = 0; inicio < tablaproforma.getRowCount(); inicio++) {
            tablaproforma.removeRow(inicio);
        }

        for (Integer inicio = 0; inicio < tablaproforma.getRowCount();) {
            tablaproforma.removeRow(inicio);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextField1.getText().length() > 0) {
            MostrarDatos();
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        showThread = new Thread(this);
        showThread.start();
        OcultaLabel(true);
        jButton4.setEnabled(false);
        jButton3.setEnabled(false);
        jButton5.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    // End of variables declaration//GEN-END:variables
}
