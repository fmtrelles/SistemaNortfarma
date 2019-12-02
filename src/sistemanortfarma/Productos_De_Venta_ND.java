package sistemanortfarma;

import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class Productos_De_Venta_ND extends javax.swing.JFrame {

    private String idbotica, idventa;
    private double totalventa = 0.0;
    LogicaVenta logventa = new LogicaVenta();
    List<Venta> listaVenta = new ArrayList<Venta>();
    Object[] datosDetalle = new Object[6];
    private DefaultTableModel model;
    TableColumnModel colModel;
    Venta obj;
    MuestraVentana objetoventana = new MuestraVentana();
    NotaCredito1 objcredito = null;
    NotaDebito objcredito1 = null;
    List<Integer> milista = new ArrayList<Integer>();

    /** Creates new for m Productos_De_Venta */
    public Productos_De_Venta_ND(NotaCredito1 var, String idbot, String idven) {
        initComponents();
        this.idbotica = idbot;
        this.idventa = idven;
        objcredito = var;
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) jTable1.getModel();
        colModel = jTable1.getColumnModel();
        CargarProductos();
        AparienciaTabla();

    }

    public Productos_De_Venta_ND(NotaDebito var, String idbot, String idven, double totalvta) {
        initComponents();
        this.idbotica = idbot;
        this.idventa = idven;
        this.totalventa = totalvta;
        objcredito1 = var;
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) jTable1.getModel();
        colModel = jTable1.getColumnModel();
        CargarProductosNDebito();
        AparienciaTabla();

    }

    public Venta getObj() {
        return obj;
    }

    public void setObj(Venta obj) {
        this.obj = obj;
    }

    private void AparienciaTabla() {
        TableColumn col = colModel.getColumn(0);
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable1.getColumnModel().getColumn(1).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(2).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(5).setCellRenderer(tcenter);

        /*col = jTable1.getColumnModel().getColumn(4);
        col.setCellRenderer(new ColoredTableCellRenderer());
        col = jTable1.getColumnModel().getColumn(5);
        col.setCellRenderer(new ColoredTableCellRenderer());*/
        col = jTable1.getColumnModel().getColumn(3);
        col.setCellRenderer(new ColoredTableCellRenderer());

    }

    private void CerrarVentana() {
        this.dispose();
    }

    private void CargarProductos() {

        LimpiatTabla();
        obj = new Venta(idbotica, idventa);
        setObj(obj);
        listaVenta = logventa.ListaVenta_Detalle(obj);
        int lengthproducto = 0;
        int cant = 0;

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle[0] = listaVenta.get(i).getDescr_Producto();
            datosDetalle[1] = listaVenta.get(i).getUnidad();
            datosDetalle[2] = listaVenta.get(i).getFraccion();
            datosDetalle[3] = listaVenta.get(i).getTotal_producto();
            datosDetalle[4] = listaVenta.get(i).getUnidad();
            datosDetalle[5] = listaVenta.get(i).getFraccion();


            lengthproducto = listaVenta.get(i).getDescr_Producto().length();

            if (lengthproducto > cant) {
                cant = lengthproducto;
            }

            model.addRow(datosDetalle);

        }

        Columnaresize(cant);
        this.jTable1.getSelectionModel().setSelectionInterval(0, 0);


    }

    private void CargarProductosNDebito() {

        LimpiatTabla();
        obj = new Venta(idbotica, idventa);
        setObj(obj);
        listaVenta = logventa.ListaVenta_DetalleND(obj);
        int lengthproducto = 0;
        int cant = 0;

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle[0] = listaVenta.get(i).getDescr_Producto();
            datosDetalle[1] = listaVenta.get(i).getUnidad();
            datosDetalle[2] = listaVenta.get(i).getFraccion();
            datosDetalle[3] = listaVenta.get(i).getTotal_producto();
            datosDetalle[4] = listaVenta.get(i).getUnidad();
            datosDetalle[5] = listaVenta.get(i).getFraccion();


            lengthproducto = listaVenta.get(i).getDescr_Producto().length();

            if (lengthproducto > cant) {
                cant = lengthproducto;
            }

            model.addRow(datosDetalle);

        }

        Columnaresize(cant);
        this.jTable1.getSelectionModel().setSelectionInterval(0, 0);


    }

    private void Columnaresize(int length) {
        TableColumn col1 = colModel.getColumn(0);
        col1.setMaxWidth(length * 12);
        col1.setMinWidth(length * 12);

    }

    private void RealizaOpciones(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            this.hide();
        }

    }

    private void EliminaFila() {
        int fila = this.jTable1.getSelectedRow();

        try {

            if (fila >= 0) {
                Confirmar p = new Confirmar(objetoventana, "<html>DESEAS ELIMINAR EL PRODUCTO:  \n  " + jTable1.getValueAt(fila, 0) + " </html>");
                p.show(true);

                if (p.confirmar == 1) {
                    model.removeRow(fila);
                    listaVenta.remove(fila);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    private boolean VerificaCantidades(int empaque1, int fraccion, Object product) {
        boolean valor = true;

        if (empaque1 == 0 && fraccion > 0) {
            valor = false;
            JOptionPane.showMessageDialog(this, " LO SENTIMOS \n" + product.toString() + "  \n NO SE PUEDE VENDER EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);

        }

        return valor;
    }

    private void SeleccionarProductos() {
        Venta objVenta = null;
        double PVPx = 0;
        double parcund;
        double parcfracc;
        double parcial = 0;
        double MontoND = 0;
        double PV = 0, dcto = 0, total = 0;
        int empaque = 0;
        boolean resultado = false;


        try {

            for (int i = 0; i < this.jTable1.getRowCount(); i++) {

                Object producto = this.jTable1.getValueAt(i, 0);
                Object TotalNB = this.jTable1.getValueAt(i, 3);
                Object unidades = this.jTable1.getValueAt(i, 4);
                Object fracciones = this.jTable1.getValueAt(i, 5);


                int und_sistema = listaVenta.get(i).getUnidad();
                int frac_sistema = listaVenta.get(i).getFraccion();

                int miempaque = listaVenta.get(i).getEmpaque();

                if (miempaque == 0) {
                    miempaque = 1;
                }
                if (unidades == null) {
                    unidades = 0;
                }
                if (fracciones == null) {
                    fracciones = 0;
                }
                if (TotalNB == null) {
                    TotalNB = 0.1;
                }

                int und = Integer.parseInt(unidades.toString());
                int fracc = Integer.parseInt(fracciones.toString());
                double totNB = Double.valueOf(TotalNB.toString());

                if (und <= 0 && fracc <= 0 ) {
                    JOptionPane.showMessageDialog(this, " LO Sentimos La Cantidad Ingresada del Producto  \n" + producto + "  \n No Puede Ser Negativa o Cero", "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                    break;
                } else if ( totNB <= 0 ) {
                    JOptionPane.showMessageDialog(this, " LO Sentimos La Cantidad Ingresada del Producto  \n" + producto + "  \n No Puede Ser Negativa o Cero", "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                    break;
                } else if ( totNB > totalventa ) {
                    JOptionPane.showMessageDialog(this, " LO Sentimos La Cantidad Ingresada del Producto  \n" + producto + "  \n No Puede Ser Mayor al monto de la Venta", "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                    break;
                } else{


                    int total_pedido = (und * miempaque) + fracc;
                    int total_vendido = (und_sistema * miempaque) + frac_sistema;

                    if (total_pedido <= total_vendido) {

                        PV = listaVenta.get(i).getPrecio_Venta();
                        dcto = listaVenta.get(i).getDescuento();
                        empaque = listaVenta.get(i).getEmpaque();
                        total = Double.parseDouble(TotalNB.toString());

                        resultado = VerificaCantidades(empaque, fracc, producto);


                        if (resultado) {

                            PVPx = listaVenta.get(i).getPrecio_Venta_Final();
                            parcund = und * PVPx;

                            if (fracc > 0) {
                                parcfracc = ((fracc * PVPx) / empaque);
                            } else {
                                parcfracc = 0;
                            }

                            BigDecimal bd = new BigDecimal(PVPx);
                            bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
                            PVPx = bd.doubleValue();

                            BigDecimal bd1 = new BigDecimal(parcund);
                            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                            parcund = bd1.doubleValue();

                            BigDecimal bd2 = new BigDecimal(parcfracc);
                            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                            parcfracc = bd2.doubleValue();

                            parcial = parcund + parcfracc;

                            BigDecimal bd3 = new BigDecimal(parcial);
                            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
                            parcial = bd3.doubleValue();

                            BigDecimal bd4 = new BigDecimal(total);
                            bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
                            MontoND = bd4.doubleValue();


                            objVenta = new Venta(listaVenta.get(i).getId_Producto(), listaVenta.get(i).getDescr_Producto(),
                                    listaVenta.get(i).getEmpaque(), und, fracc,
                                    listaVenta.get(i).getPrecio_Venta(), listaVenta.get(i).getDescuento(), PVPx,
                                    parcial, listaVenta.get(i).getIGV(),
                                    listaVenta.get(i).getMostrador_Stock_Empaque(), listaVenta.get(i).getMostrador_Stock_Fraccion(),MontoND);

                            listaVenta.set(i, objVenta);

                        } else {
                            jTable1.setValueAt(listaVenta.get(i).getFraccion(), i, 5);
                            resultado = false;
                            break;
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, " LA CANTIDAD INGRESADA DE  \n" + producto + "  \n  NO PUEDE SER MAYOR QUE LA CANTIDAD LLEVADA EN LA VENTA", "Error", JOptionPane.ERROR_MESSAGE);
                        resultado = false;
                        break;
                    }



                }
            }

            if (resultado) {
                objcredito1.AsignaProductos(listaVenta);
                this.hide();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Productos_De_Venta_ND.class);
        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Und", "Fracc", "Total", "UND", "FRACC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setMnemonic('C');
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(326, 326, 326))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
            int i = this.jTable1.getSelectedRow();

            Object TotalNB = this.jTable1.getValueAt(i, 3);
            Object unidades = this.jTable1.getValueAt(i, 4);
            Object fracciones = this.jTable1.getValueAt(i, 5);

            Object producto = this.jTable1.getValueAt(i, 0);
            int empaque = listaVenta.get(i).getEmpaque();

            if (unidades == null) {
                unidades = 0;
            }
            if (fracciones == null) {
                fracciones = 0;
            }
            if (TotalNB == null) {
                TotalNB = 0.1;
            }

            int und = Integer.parseInt(unidades.toString());
            int fracc = Integer.parseInt(fracciones.toString());
            double totNB = Double.valueOf(TotalNB.toString());

            VerificaCantidades(empaque, fracc, producto);

        }
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        EliminaFila();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SeleccionarProductos();
    }//GEN-LAST:event_jButton1ActionPerformed

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            Color micol = new Color(216, 228, 248);
            setBackground(micol);
            int tam = listaVenta.size();

            for (int i = 0; i < tam; i++) {
                Object codpro = table.getValueAt(row, column);
                if (listaVenta.get(i).getUnidad() >= 0) {
                    setBackground(micol);
                }

            }


            super.getTableCellRendererComponent(table, value, false, false, 2, column);

            return this;
        }
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    // End of variables declaration//GEN-END:variables
}
