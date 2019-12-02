/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AgregaProductosCarDes.java
 *
 * Created on 11/04/2011, 07:34:45 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaProducto;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import entidad.Movimiento_Detalle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class AgregaProductosSAP extends JDialog {

    TableColumn columnaTabla = null;
    LogicaProducto objProductos = new LogicaProducto();
    List<ProductosPrecios> listproducto = new ArrayList();
    LogicaBoticas objBoticas = new LogicaBoticas();
    ModeloTabla modeloTabla;
    public static String real = "";
    public static String realDes = "";
    public static String realLab = "";
    public static String cantidad = "";
    private static String idproducto;
    private String idbotica;
    private static int unidad;
    private static int fraccion;
    private static double pvp;
    private static double descto;
    private String alma;
    private String SAPTIPO;
    private int stockempaque;
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    private int filtro;

    /** Creates new form AgregaProductosCarDes */
    public AgregaProductosSAP(Frame obj, String idboti, String almacen, int fil, String SAPTIP) {
        super(obj, true);
        initComponents();
        idbotica = idboti;
        alma = almacen;
        filtro = fil;
        SAPTIPO = SAPTIP;
        setLocationRelativeTo(null);
        modeloTabla = new ModeloTabla();
        construirGuia();
        setCantidad("");
        setIdproducto("");
        setUnidad(-1);
        setFraccion(-1);
        jTextField1.requestFocus();
    }

    public AgregaProductosSAP(Frame obj, String idboti, String almacen, int fil, String SAPTIP, List<Movimiento_Detalle> array) {
        super(obj, true);
        initComponents();
        idbotica = idboti;
        alma = almacen;
        filtro = fil;
        SAPTIPO = SAPTIP;
        setLocationRelativeTo(null);
        modeloTabla = new ModeloTabla();
        construirGuia();
        setCantidad("");
        setIdproducto("");
        setUnidad(-1);
        setFraccion(-1);
        jTextField1.requestFocus();
        CargarDatos(array);
    }

    public static double getDescto() {
        return descto;
    }

    public static void setDescto(double descto) {
        AgregaProductosSAP.descto = descto;
    }

    public static double getPvp() {
        return pvp;
    }

    public static void setPvp(double pvp) {
        AgregaProductosSAP.pvp = pvp;
    }

    public static int getUnidad() {
        return unidad;
    }

    public static void setUnidad(int unidad) {
        AgregaProductosSAP.unidad = unidad;
    }

    public static int getFraccion() {
        return fraccion;
    }

    public static void setFraccion(int fraccion) {
        AgregaProductosSAP.fraccion = fraccion;
    }

    public static String getIdproducto() {
        return idproducto;
    }

    public static void setIdproducto(String idproducto) {
        AgregaProductosSAP.idproducto = idproducto;
    }

    public static String getRealLab() {
        return realLab;
    }

    public static void setRealLab(String realLab) {
        AgregaProductosSAP.realLab = realLab;
    }

    public static String getReal() {
        return real;
    }

    public static void setReal(String real) {
        AgregaProductosSAP.real = real;
    }

    public static String getRealDes() {
        return realDes;
    }

    public static void setRealDes(String realDes) {
        AgregaProductosSAP.realDes = realDes;
    }

    public static String getCantidad() {
        return cantidad;
    }

    public static void setCantidad(String cantidad) {
        AgregaProductosSAP.cantidad = cantidad;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(AgregaProductosSAP.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        labelTask1.setText(resourceMap.getString("labelTask1.text")); // NOI18N
        labelTask1.setDescription(resourceMap.getString("labelTask1.description")); // NOI18N
        labelTask1.setName("labelTask1"); // NOI18N

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
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
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
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, resourceMap.getColor("jPanel1.border.highlightColor"), null)); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descripcion", "Codigo del Producto" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyCode() == 10) {
            BuscarProducto();
        }

        if ((evt.getKeyCode() == 40) && (jTable1.getRowCount() > 0)) {
            jTable1.requestFocus();
            jTable1.setRowSelectionInterval(0, 0);
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void CargarDatos(List<Movimiento_Detalle> array){
       
        listproducto.removeAll(listproducto);

        for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(inicio);
            modeloTabla.isCellEditable(inicio, 1);
            modeloTabla.isCellEditable(inicio, 2);
        }

        jTable1.removeAll();
        jTable1.setModel(modeloTabla);

        int opcion = jComboBox1.getSelectedIndex();
        for (int i = 0; i < array.size(); i++) {
            listproducto = objProductos.ListarProductosSAPCargo(array.get(i).getId_Producto().getIdProducto(), idbotica, opcion, SAPTIPO);
        }

        for (Integer i = 0; i < listproducto.size(); i++) {

            Object[][] data = {
                {
                    listproducto.get(i).getProductoBotica().getProducto().getIdProducto(),
                    listproducto.get(i).getProductoBotica().getProducto().getDescripcion(),
                    listproducto.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Fraccion(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Fraccion(),
                    listproducto.get(i).getPrecio_Venta(),
                    listproducto.get(i).getDescuento_Venta()
                }
            };

            modeloTabla.addRow(data[0]);

        }

    }
    private void BuscarProducto() {

        listproducto.removeAll(listproducto);

        for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(inicio);
            modeloTabla.isCellEditable(inicio, 1);
            modeloTabla.isCellEditable(inicio, 2);
        }

        jTable1.removeAll();
        jTable1.setModel(modeloTabla);

        int opcion = jComboBox1.getSelectedIndex(); 
        listproducto = objProductos.ListarProductosSAP(jTextField1.getText(), idbotica, opcion, SAPTIPO);

        for (Integer i = 0; i < listproducto.size(); i++) {

            Object[][] data = {
                {
                    listproducto.get(i).getProductoBotica().getProducto().getIdProducto(),
                    listproducto.get(i).getProductoBotica().getProducto().getDescripcion(),
                    listproducto.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Fraccion(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Fraccion(),
                    listproducto.get(i).getPrecio_Venta(),
                    listproducto.get(i).getDescuento_Venta()
                }
            };

            modeloTabla.addRow(data[0]);

        }

    }

    private void construirGuia() {

        modeloTabla.addColumn("CODIGO");
        modeloTabla.addColumn("PRODUCTO");
        modeloTabla.addColumn("LAB");
        modeloTabla.addColumn("S-Most-Und");
        modeloTabla.addColumn("S-Most-Frac");
        modeloTabla.addColumn("S-Alm-Und");
        modeloTabla.addColumn("S-Alm-Frac");
        modeloTabla.addColumn("PVP");
        modeloTabla.addColumn("DCTO");

        jTable1.setModel(modeloTabla);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(50);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(260);
        columnaTabla.setMinWidth(260);
        columnaTabla.setMaxWidth(260);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);


        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);


        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);


        AparienciaTabla();

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);

    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() % 2 == 0) {
            devolverDatos();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jTextField1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        devolverDatos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();

        try {

            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, alma);

            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = empaque;

                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }

                //RECUPERO MI STOCK DEL EMPAQUIE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = stockempaque;

                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = stockfraccion;

                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unidad * empaque_tmp + fraccion;

                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }

            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }

        return resultado;

    }

    private void Realiza_Opciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }

        if (evt.getKeyCode() == 10) {
            devolverDatos();
        }
    }

    private void CerrarVentana() {
        dispose();
    }

    public void devolverDatos() {

        boolean ok = false;

        try {


            if (jTable1.getRowCount() > 0) {

                int fila = this.jTable1.getSelectedRow();

                if (fila >= 0) {
                    JOptionPane p = new JOptionPane();
                    cantidad = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

                    if (!String.valueOf(cantidad).isEmpty()) {

                        if (cantidad != null) {

                            if (cantidad.compareTo("0") != 0) {

                                if (!String.valueOf(cantidad).isEmpty() || cantidad != null) {

                                    cantidad = cantidad.toUpperCase();
                                    boolean resul = VerificaCantidad(cantidad);

                                    if (resul) {

                                        if (filtro == 0) {
                                            if (VerificaStock(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())) {
                                                ok = true;
                                            }
                                        } else {
                                            ok = true;
                                        }

                                        if (ok) {
                                            this.setCantidad(cantidad);
                                            real = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                                            setReal(real);

                                            realDes = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                                            setRealDes(realDes);

                                            realLab = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
                                            setRealLab(realLab);

                                            idproducto = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                                            setIdproducto(idproducto);

                                            pvp = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
                                            setPvp(pvp);

                                            descto = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString());
                                            setDescto(descto);

                                            setCantidad(cantidad);
                                            setUnidad(unidad);

                                            dispose();
                                        } else {
                                            JOptionPane.showMessageDialog(this, " No hay Stock Suficiente de este Producto ", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
        }
    }


    /*
     * METODO QUE CALCULA SI LO INGRESADO ES UNIDAD Y FRACCION
     */
    private boolean VerificaCantidad(String cantidad) {
        String unidad1 = "";
        String fraccion1 = "";
        int k = 0;
        boolean segundo = false;
        char car = 'F';
        int cantdef = 0;
        String valor = "";

        cantidad = cantidad.trim();
        Character w;
        valor = valor.trim();

        for (int i = 0; i < cantidad.length(); i++) {
            w = cantidad.charAt(i);
            if (w.isDigit(w) || w.isLetter(w)) {
                valor = valor + w;
            } else {
                return false;
            }
        }


        for (int i = 0; i < valor.length(); i++) {
            Character caracter = valor.charAt(i);

            if (caracter.isLetter(caracter)) {
                caracter = caracter.toUpperCase(caracter);
                cantdef++;
                if (caracter != car || cantdef > 1) {
                    return false;
                }

            }
            if (caracter.isDigit(caracter) && segundo == false) {
                unidad1 = unidad1 + caracter;
            } else {
                segundo = true;
                if (k == 1) {
                    if (caracter.isDigit(caracter)) {
                        fraccion1 = fraccion1 + caracter;
                    }
                }
                k = 1;
            }
        }

        if (unidad1.compareTo("") == 0) {
            unidad1 = "0";
        }
        if (fraccion1.compareTo("") == 0) {
            fraccion1 = "0";
        }

        unidad = Integer.valueOf(unidad1);
        fraccion = Integer.valueOf(fraccion1);
        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    // End of variables declaration//GEN-END:variables
}
