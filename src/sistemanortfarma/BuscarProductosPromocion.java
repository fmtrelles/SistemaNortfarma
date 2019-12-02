package sistemanortfarma;

/**
 *
 * @author gparedes
 */

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaLaboratorios;
import CapaLogica.LogicaProducto;
import entidad.ProductosPrecios;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class BuscarProductosPromocion extends javax.swing.JDialog {
static ProductosPrecios productoPrecio = null;
    private static FormLaboratorios lab;
    private static FormGenericos gen;
    public static boolean espromo;
    static boolean seleccionaart = false;
    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listProdDetalle = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> ListaPromociones = new ArrayList<ProductosPrecios>();
    private String idbotica, idgenerico, id_laboratorio;
    private String tipprecio = "01";
    int band = 0, totalproductos, posicion = 0, podecimal, opconstock = 1;
    LogicaProducto objproducto = new LogicaProducto();
    LogicaLaboratorios objlaboratorio = new LogicaLaboratorios();
    LogicaBoticas objBoticas = new LogicaBoticas();
    Object[] datosDetalle = new Object[10];
    Object[] Detalle = new Object[7];
    private DefaultTableModel model, modeldetalle;
    TableColumnModel colModel, colModel_Detalle;
    TableColumn colu, colu_6, columnaTabla;
    JCheckBox checkPromocion = new JCheckBox();
    JFrame objetoventana;


    /** Creates new form BuscaProducts */
    public BuscarProductosPromocion(java.awt.Frame objeto, String opprecio) {
        super(objeto, true);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/magnifier.png")).getImage());
        setLocationRelativeTo(null);
        setSeleccionaart(false);
        podecimal = OpcionesMenu.getCantidadDecimales();
        //colModel_Detalle = jTable2.getColumnModel();
        tipprecio = opprecio;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        //modeldetalle = (DefaultTableModel) jTable2.getModel();
        idbotica = AplicacionVentas.getIdbotica();
        colu = jTable1.getColumnModel().getColumn(7);
        colu_6 = jTable1.getColumnModel().getColumn(6);
        //jCheckBox1.setSelected(true);
        //jLabel10.setVisible(false);
        //jLabel11.setVisible(false);
        //jLabel12.setText("");
        //AgregaFiltros();
        AparienciaTabla();
        verificaAlmacenes();
        muestraProductos();
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField9.setVisible(false);
        jTextField2.setVisible(false);
        jTextField8.setVisible(false);
        //jTextField1.requestFocus(); 
    }

    private BuscarProductosPromocion(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BuscarProductosPromocion.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Lab", "Und", "Fracc", "S-Alm-Und", "S-Alm-Fracc", "    PVP", "    PVPx", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
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

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setFocusable(false);
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setEditable(false);
        jTextField5.setFont(resourceMap.getFont("jTextField5.font")); // NOI18N
        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setFocusable(false);
        jTextField5.setName("jTextField5"); // NOI18N

        jTextField6.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        jTextField6.setEditable(false);
        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setFocusable(false);
        jTextField6.setName("jTextField6"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField8.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField8.setEditable(false);
        jTextField8.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setFocusable(false);
        jTextField8.setName("jTextField8"); // NOI18N

        jComboBox2.setBackground(resourceMap.getColor("jComboBox2.background")); // NOI18N
        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRECIO NORMAL", "PRECIO BOTIQUIN" }));
        jComboBox2.setEnabled(false);
        jComboBox2.setFocusable(false);
        jComboBox2.setName("jComboBox2"); // NOI18N

        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setEditable(false);
        jTextField9.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setFocusable(false);
        jTextField9.setName("jTextField9"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static boolean isSeleccionaart() {
        return seleccionaart;
    }

    public static void setSeleccionaart(boolean seleccionaart) {
        BuscarProductosPromocion.seleccionaart = seleccionaart;
    }

    public static boolean isEspromo() {
        return espromo;
    }

    public static void setEspromo(boolean espromo) {
        BuscarProductosPromocion.espromo = espromo;
    }

    public static ProductosPrecios getProductoPrecio() {
        return productoPrecio;
    }

    public static void setProductoPrecio(ProductosPrecios productoPrecio) {
        BuscarProductosPromocion.productoPrecio = productoPrecio;
    }

    private void AparienciaTabla() {
        try {
            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(tcenter);
            //jTable2.getColumnModel().getColumn(3).setCellRenderer(tcenter);
            //jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);

            DefaultTableCellRenderer deracha = new DefaultTableCellRenderer();
            deracha.setHorizontalAlignment(SwingConstants.RIGHT);
            jTable1.getColumnModel().getColumn(7).setCellRenderer(deracha);
            jTable1.getColumnModel().getColumn(8).setCellRenderer(deracha);
            //jTable2.getColumnModel().getColumn(6).setCellRenderer(deracha);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int filaseleccionada = jTable1.getSelectedRow();

        if (listaProductos.size() > 0 && filaseleccionada >= 0) {
            jTextField2.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 8)));
            jTextField4.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 1)));
            jTextField8.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 3)));
            jTextField9.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 4)));
            jTextField5.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdFamilia().getDescripcion()));
            jTextField6.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdGenerico().getDescripcionGenerico()));
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            RecuperaProducto();
        } else {
            if (evt.getKeyCode() == 9) {
                //jTable2.requestFocus();
                //jTable2.getSelectionModel().setSelectionInterval(0, 0);
            } else {
                //RealizaOpciones(evt);
            }
        }
}//GEN-LAST:event_jTable1KeyPressed
private void RecuperaProducto() {

        if (jTable1.getRowCount() > 0) {
            int fila = jTable1.getSelectedRow();
            try {
                if (fila >= 0) {
                    double PVP = listaProductos.get(fila).getPrecio_Venta();
                    if (PVP != 0.0) {
                        productoPrecio = listaProductos.get(fila);
                        productoPrecio.setPVPX(Double.parseDouble(jTable1.getValueAt(fila, 8).toString()));
                        if (jTable1.getValueAt(fila, 9) == null) {
                            espromo = false;
                        } else {
                            espromo = Boolean.valueOf(jTable1.getValueAt(fila, 9).toString());
                        }
                        setEspromo(espromo);
                        setSeleccionaart(true);
                        setProductoPrecio(productoPrecio);
                        CerrarVentana();
                    } else {
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS NO PUEDES AGREGAR UN PRODUCTO CON PRECIO 0.0 ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                System.out.println(" Error RecuperaProducto " + ex.getMessage());
            }
        }
    }
private void CerrarVentana() {
        dispose();
    }

    private void LimpiatTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0 || evt.getKeyText(evt.getKeyCode()).compareTo("Arriba") == 0) {
            //LimpiatTabla2();
            listProdDetalle.removeAll(listProdDetalle);
            //jLabel15.setText("");
            //jLabel12.setText("");
        }
        int filaseleccionada = jTable1.getSelectedRow();
        try {
            if (filaseleccionada >= 0 && totalproductos >= filaseleccionada) {
                if (filaseleccionada < totalproductos) {
                    jTextField2.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 8)));
                    jTextField4.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 1)));
                    jTextField8.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 3)));
                    jTextField9.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 4)));
                    jTextField5.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdFamilia().getDescripcion()));
                    jTextField6.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdGenerico().getDescripcionGenerico()));

                    if (jTable1.getValueAt(filaseleccionada, 9).toString().compareTo("true") == 0) {
                        ProductosPromocion();
                    } else {
                        ListaPromociones.removeAll(ListaPromociones);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jTable1KeyReleased
private void ProductosPromocion() {
        if (jTable1.getRowCount() > 0) {
            int filaseleccionada = jTable1.getSelectedRow();
            int idgrupo;
            int idpromocion;
            try {

                if (filaseleccionada >= 0 && totalproductos >= filaseleccionada) {
                    if (filaseleccionada < totalproductos) {
                        idgrupo = listaProductos.get(filaseleccionada).getGrupo_Id();
                        idpromocion = listaProductos.get(filaseleccionada).getId_Promocion();
                        if (idgrupo > 0) {
                            MuestraDetalle(idgrupo, idpromocion);
                            //jLabel15.setText("PROMOCIONES  DEL PRODUCTO " + jTable1.getValueAt(filaseleccionada, 1) + " : ");
                            //jLabel12.setText(listaProductos.get(filaseleccionada).getDESCRIPPROMOCION());
                        } else {
                            //jLabel15.setText("PRODUCTO " + jTable1.getValueAt(filaseleccionada, 1) + " NO TIENE PROMOCION");
                            //LimpiatTabla2();
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("ProductosPromocion " + ex.getMessage());
            }
        }
    }
private void MuestraDetalle(int idgrupo, int idpromocion) {

        double desc, pvx, pv = 0;
        ListaPromociones.removeAll(ListaPromociones);

        try {

            ListaPromociones = objproducto.ListarProductos_Promocion(idbotica, idgrupo, idpromocion);
            //jLabel15.setText(ListaPromociones.get(ListaPromociones.size() - 1).getDESCRIPPROMOCION());
            //LimpiatTabla2();

            if (ListaPromociones.size() > 0) {
                for (int i = 0; i < ListaPromociones.size(); i++) {
                    String descrip = ListaPromociones.get(i).getProductoBotica().getProducto().getDescripcion();
                    Detalle[0] = ListaPromociones.get(i).getProductoBotica().getProducto().getIdProducto();
                    Detalle[1] = descrip;
                    Detalle[2] = ListaPromociones.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                    Detalle[3] = ListaPromociones.get(i).getProductoBotica().getMostrador_Stock_Empaque();
                    Detalle[4] = ListaPromociones.get(i).getProductoBotica().getMostrador_Stock_Fraccion();
                    pv = ListaPromociones.get(i).getPrecio_Venta();

                    BigDecimal bd = new BigDecimal(pv);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    pv = bd.doubleValue();

                    desc = ListaPromociones.get(i).getDescuento_Venta();
                    pvx = pv - (pv * (desc / 100));

                    BigDecimal bd1 = new BigDecimal(pvx);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    pvx = bd1.doubleValue();

                    Detalle[5] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    Detalle[6] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    modeldetalle.addRow(Detalle);
                }

            } else {
               //jLabel15.setText(" ESTE PRODUCTO NO TIENE PROMOCION ");
            }

        } catch (Exception ex) {
            System.out.println("MuestraDetalle " + ex.getMessage());
        }
    }
private void muestraProductos() {

        double pv, desc = 0, pvx = 0;
        BigDecimal bd, bd1;
        int fraccion = 0;

        listaProductos = objproducto.ListarProductosPromocionesPrecios("", idbotica, tipprecio, 0, 1);

        /*if (tipprecio.compareTo("01") == 0) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(1);
        }*/

        try {

            for (ProductosPrecios precio : listaProductos) {
                pv = precio.getPrecio_Venta();
                datosDetalle[0] = precio.getProductoBotica().getProducto().getIdProducto();
                datosDetalle[1] = precio.getProductoBotica().getProducto().getDescripcion();
                datosDetalle[2] = precio.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                datosDetalle[3] = precio.getProductoBotica().getMostrador_Stock_Empaque();
                fraccion = precio.getProductoBotica().getMostrador_Stock_Fraccion();

                if (fraccion == 0) {
                    datosDetalle[4] = fraccion;
                } else {
                    datosDetalle[4] = "F" + fraccion;
                }

                bd = new BigDecimal(pv);
                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                pv = bd.doubleValue();

                datosDetalle[5] = precio.getProductoBotica().getAlmacen_Stock_Empaque();
                int almfrac = precio.getProductoBotica().getAlmacen_Stock_Fraccion();

                if (almfrac == 0) {
                    datosDetalle[6] = almfrac;
                } else {
                    datosDetalle[6] = "F" + almfrac;
                }

                datosDetalle[7] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                desc = precio.getDescuento_Venta();
                pvx = pv - (pv * (desc / 100));

                bd1 = new BigDecimal(pvx);
                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                pvx = bd1.doubleValue();

                datosDetalle[8] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                if (datosDetalle.toString().compareTo("") != 0) {

                    if (precio.getId_Producto_Grupo() > 0) {
                        checkPromocion.setSelected(true);
                        datosDetalle[9] = true;
                    } else {
                        checkPromocion.setSelected(false);
                        datosDetalle[9] = false;
                    }
                    colu.setCellEditor(new DefaultCellEditor(checkPromocion));
                    model.addRow(datosDetalle);
                }
            }

            totalproductos = jTable1.getRowCount();

        } catch (Exception ex) {
            System.out.println("muestraProductos " + ex.getMessage());
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuscarProductosPromocion dialog = new BuscarProductosPromocion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
private void verificaAlmacenes() {

        if (!AplicacionVentas.isEsAlmacen()) {
            columnaTabla = jTable1.getColumnModel().getColumn(5);
            columnaTabla.setPreferredWidth(0);
            columnaTabla.setMinWidth(0);
            columnaTabla.setMaxWidth(0);

            columnaTabla = jTable1.getColumnModel().getColumn(6);
            columnaTabla.setPreferredWidth(0);
            columnaTabla.setMinWidth(0);
            columnaTabla.setMaxWidth(0);
        }

        colu_6 = jTable1.getColumnModel().getColumn(7);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        //colu_6 = jTable2.getColumnModel().getColumn(5);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
