/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormProveedores.java
 *
 * Created on 07/02/2012, 01:03:24 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaProveedor;
import entidad.Proveedor;
import java.awt.Frame;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import CapaLogica.LogicaFechaHora;
import entidad.Venta;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import CapaLogica.LogicaVenta;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormVentasAnuladas extends javax.swing.JDialog {

    private static String idproveedor;
    private static String idoperacion;
    private static String fechavta;
    private static String Nombre_Proveedor;
    private static String idcaj;
    LogicaProveedor logprovee = new LogicaProveedor();
    //Object[] datosDetalle = new Object[2];
    private DefaultTableModel model;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    private String idventa = "";
    private String fechaventa = "";
    private String idcajero;
    private String idbotica = null;
    double total = 0;
    double subtotal = 0;
    double igv = 0;
    Venta obj;
    List<Venta> listaInternos = new ArrayList<Venta>();
    LogicaVenta logventa = new LogicaVenta();
    private DefaultTableModel model_notas;
    private DefaultTableModel model_EnvioDetalle;
    TableColumnModel colModel;
    List<Venta> listaVenta = new ArrayList<Venta>();
    Object[] datosDetalle = new Object[6];
    Object[] datosDetalle_1 = new Object[5];

    /** Creates new form FormProveedores */
    public FormVentasAnuladas(Frame parent,String idbotica) {
        super(parent, true);        
        initComponents();
        
        this.setLocationRelativeTo(null);
        model_notas = (DefaultTableModel) tablanotas.getModel();
        colModel = tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        this.setIdproveedor(null);
        this.setNombre_Proveedor(null);
        this.jTextField2.setText(idbotica);
        this.txtidventa.setVisible(false);
        this.txtFechaVta.setVisible(false);
        this.txtidcajero.setVisible(false);


    }

    public static String getNombre_Proveedor() {
        return Nombre_Proveedor;
    }

    public static void setNombre_Proveedor(String Nombre_Proveedor) {
        FormVentasAnuladas.Nombre_Proveedor = Nombre_Proveedor;
    }

    public static String getIdproveedor() {
        return idproveedor;
    }

    public static void setIdproveedor(String idproveedor) {
        FormVentasAnuladas.idproveedor = idproveedor;
    }

    public static String getIdOperacion() {
        return idoperacion;
    }

    public static void setIdOperacion(String idoperacion) {
        FormVentasAnuladas.idoperacion = idoperacion;
    }

    public static String getFechaVta() {
        return fechavta;
    }

    public static void setFechaVta(String fechavta) {
        FormVentasAnuladas.fechavta = fechavta;
    }

    public static String getIdCajero() {
        return idcaj;
    }

    public static void setIdCajero(String idcaj) {
        FormVentasAnuladas.idcaj = idcaj;
    }

    
    private void RecuperaProveedor() {
       try {
                idventa = String.valueOf(this.txtidventa.getText());
                fechaventa = String.valueOf(this.txtFechaVta.getText());
                idcajero = String.valueOf(this.txtidcajero.getText());
                this.setIdOperacion(idventa);
                this.setFechaVta(fechaventa);
                this.setIdCajero(idcajero);

                dispose();           

        } catch (Exception ex) {
        }

    }

    private void RealizaOpciones(KeyEvent evt) {

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }

        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }


    }

    private void CerrarVentana() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablanotas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtcajero = new javax.swing.JTextField();
        txtvendedor = new javax.swing.JTextField();
        txtnumero = new javax.swing.JTextField();
        txtserie = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txttipoventa = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TXTRUC = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtigv = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        txtidventa = new javax.swing.JTextField();
        txtFechaVta = new javax.swing.JTextField();
        txtidcajero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormVentasAnuladas.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setBackground(resourceMap.getColor("jButton4.background")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jButton5))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablanotas.setFont(resourceMap.getFont("tablanotas.font")); // NOI18N
        tablanotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "FECHA", "CLIENTE", "SERIE", "NUMERO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablanotas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablanotas.setName("tablanotas"); // NOI18N
        tablanotas.setSelectionBackground(resourceMap.getColor("tablanotas.selectionBackground")); // NOI18N
        tablanotas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablanotas.getTableHeader().setReorderingAllowed(false);
        tablanotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablanotasMouseClicked(evt);
            }
        });
        tablanotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablanotasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablanotas);
        tablanotas.getColumnModel().getColumn(0).setResizable(false);
        tablanotas.getColumnModel().getColumn(0).setPreferredWidth(20);
        tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
        tablanotas.getColumnModel().getColumn(1).setResizable(false);
        tablanotas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
        tablanotas.getColumnModel().getColumn(2).setResizable(false);
        tablanotas.getColumnModel().getColumn(2).setPreferredWidth(280);
        tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
        tablanotas.getColumnModel().getColumn(3).setResizable(false);
        tablanotas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title3")); // NOI18N
        tablanotas.getColumnModel().getColumn(4).setResizable(false);
        tablanotas.getColumnModel().getColumn(4).setPreferredWidth(60);
        tablanotas.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title4")); // NOI18N
        tablanotas.getColumnModel().getColumn(5).setResizable(false);
        tablanotas.getColumnModel().getColumn(5).setPreferredWidth(70);
        tablanotas.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title5")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        txtcajero.setEditable(false);
        txtcajero.setName("txtcajero"); // NOI18N

        txtvendedor.setEditable(false);
        txtvendedor.setName("txtvendedor"); // NOI18N

        txtnumero.setEditable(false);
        txtnumero.setName("txtnumero"); // NOI18N

        txtserie.setEditable(false);
        txtserie.setName("txtserie"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txttipoventa.setEditable(false);
        txttipoventa.setName("txttipoventa"); // NOI18N

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setName("jComboBox1"); // NOI18N

        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setName("jComboBox2"); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setName("jTextField9"); // NOI18N

        jTextField10.setEditable(false);
        jTextField10.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setName("jTextField10"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addGap(181, 181, 181))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, 137, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtcliente.setEditable(false);
        txtcliente.setName("txtcliente"); // NOI18N

        txtdireccion.setEditable(false);
        txtdireccion.setName("txtdireccion"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        TXTRUC.setEditable(false);
        TXTRUC.setName("TXTRUC"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Descripcion", "Und", "Fracc", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        txtsubtotal.setEditable(false);
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsubtotal.setName("txtsubtotal"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        txtigv.setEditable(false);
        txtigv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtigv.setName("txtigv"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        txttotal.setEditable(false);
        txttotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotal.setName("txttotal"); // NOI18N

        jButton7.setBackground(resourceMap.getColor("jButton7.background")); // NOI18N
        jButton7.setFont(resourceMap.getFont("jButton7.font")); // NOI18N
        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtidventa.setEditable(false);
        txtidventa.setName("txtidventa"); // NOI18N

        txtFechaVta.setEditable(false);
        txtFechaVta.setName("txtFechaVta"); // NOI18N

        txtidcajero.setEditable(false);
        txtidcajero.setName("txtidcajero"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcajero)
                            .addComponent(txtvendedor)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtnumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(txtserie, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtidcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFechaVta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addGap(647, 647, 647))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdireccion)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaVta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtidcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(TXTRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.jLabel17.setVisible(true);
        this.jLabel18.setVisible(true);
        BuscaVtasAnuladas();
}//GEN-LAST:event_jButton5ActionPerformed

    private void BuscaVtasAnuladas() {
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            idventa = this.jTextField1.getText().trim();

            if (idventa.length() > 0) {
                idventa += '%';
            }
            obj = new Venta(jTextField2.getText(), idventa, fechaini, fechafin);
            EjecutatHilo obje = new EjecutatHilo(1);
            obje.start();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }



    }

    public class EjecutatHilo extends Thread {

        int tipbusqueda;

        public EjecutatHilo(int op) {
            tipbusqueda = op;
        }

        public void run() {
            try {

                listaInternos.removeAll(listaInternos);
                if (tipbusqueda == 0) {
                    listaInternos = logventa.Lista_Internos_Ventas(obj, 0);
                } else {
                    listaInternos = logventa.Lista_Internos_VentasAnuladas(obj,0);
                }

                if (listaInternos.size() > 0) {
                    LimpiatTabla();
                }              

                for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getId_Venta();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                    datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                    datosDetalle[3] = listaInternos.get(i).getSerie();
                    datosDetalle[4] = listaInternos.get(i).getNumero();
                    datosDetalle[5] = listaInternos.get(i).getTotal();

                    model_notas.addRow(datosDetalle);
                }

            } catch (Exception ex) {
                System.out.println("ERROR CAPA VISTA METODO CargarEnvioEfectivo " + ex.getMessage());
            }


            jLabel17.setVisible(false);
            jLabel18.setVisible(false);
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);

        this.txtcajero.setText("");
        this.txtnumero.setText("");
        total = 0;
        LimpiatTabla_Productos();
        BuscaVtasAnuladas();
}//GEN-LAST:event_jButton6ActionPerformed

    private void LimpiatTabla_Productos() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CerrarVentana();
}//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
}//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

}//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String filtro = null;
        filtro = this.jTextField1.getText().trim();

        if (!String.valueOf(filtro).isEmpty()) {
            
            BuscaVenta(filtro);
        } else {
            BuscaVtasAnuladas();
            

        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void BuscaVenta(String filtro) {
        filtro += '%';

        try {

            obj = new Venta(jTextField2.getText(), filtro);
            listaInternos = logventa.Lista_Internos_VentasAnuladas(obj, 0);

           LimpiatTabla();
           
               for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getId_Venta();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                    datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                    datosDetalle[3] = listaInternos.get(i).getSerie();
                    datosDetalle[4] = listaInternos.get(i).getNumero();
                    datosDetalle[5] = listaInternos.get(i).getTotal();

                    model_notas.addRow(datosDetalle);
                }

            tablanotas.getSelectionModel().setSelectionInterval(0, 0);



        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA METODO BuscarNotaCredito");
        }


    }

    private void BuscaIntervaloFechas_Sen() {
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            idventa = this.jTextField1.getText().trim();

            if (idventa.length() > 0) {
                idventa += '%';
            }

            obj = new Venta(idbotica, idventa, fechaini, fechafin);
            //listaInternos = logventa.Lista_Int_Ventas_Fecha(obj);
            listaInternos = logventa.Lista_EnviosEfectivo(obj);

            if (listaInternos.size() > 0) {
                LimpiatTabla();
            }

            for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getnumeroentregaefec();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Registro();
                    datosDetalle[2] = listaInternos.get(i).getdescaja();
                    datosDetalle[3] = listaInternos.get(i).getdesturno();
                    datosDetalle[4] = listaInternos.get(i).getNombre();
                    datosDetalle[5] = listaInternos.get(i).getTotal();

                    model_notas.addRow(datosDetalle);
                }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
    private void tablanotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanotasMouseClicked

        if (evt.getClickCount() % 2 == 0) {
            SeleccionarVenta();
        }
}//GEN-LAST:event_tablanotasMouseClicked

    private void tablanotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablanotasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SeleccionarVenta();
        }
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
}//GEN-LAST:event_tablanotasKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        RecuperaProveedor();
    }//GEN-LAST:event_jButton7ActionPerformed


    private void SeleccionarVenta() {
        int fila = this.tablanotas.getSelectedRow();
        String miruc;


        if (fila >= 0 && listaInternos.size() > 0) {
            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));

            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setSelectedIndex(1);

            total = listaInternos.get(fila).getTotal();
            subtotal = listaInternos.get(fila).getSubTotal();
            igv = listaInternos.get(fila).getIGV();

            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));
            txtcliente.setText(listaInternos.get(fila).getNombre_RazonSocial());
            if (listaInternos.get(fila).getRUC() == null) {
                miruc = "";
            } else {
                miruc = String.valueOf(listaInternos.get(fila).getRUC());
            }
            txtidventa.setText(listaInternos.get(fila).getId_Venta());
            txtFechaVta.setText(listaInternos.get(fila).getFecha_Venta().toString());
            TXTRUC.setText(miruc);
            txtserie.setText(listaInternos.get(fila).getSerie());
            txtnumero.setText(listaInternos.get(fila).getNumero());
            txttotal.setText(String.valueOf(total));
            txtsubtotal.setText(String.valueOf(subtotal));
            txttipoventa.setText(listaInternos.get(fila).getTipventa());
            txtdireccion.setText(listaInternos.get(fila).getDireccion());
            txtvendedor.setText(listaInternos.get(fila).getNombre());
            txtigv.setText(String.valueOf(igv));
            txtcajero.setText(listaInternos.get(fila).getCajero());
            txtidcajero.setText(String.valueOf(listaInternos.get(fila).getId_Personal_Botica_Caja()));

            CargarProductos();
        }

    }
    private void CargarProductos() {
        obj = new Venta(jTextField2.getText(), idventa);
        listaVenta = logventa.ListaVenta_Detalle(obj);

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle_1[0] = i + 1;
            datosDetalle_1[1] = listaVenta.get(i).getDescr_Producto();
            datosDetalle_1[2] = listaVenta.get(i).getUnidad();
            datosDetalle_1[3] = listaVenta.get(i).getFraccion();
            datosDetalle_1[4] = listaVenta.get(i).getTotal_producto();

            model.addRow(datosDetalle_1);
        }

        List<Venta> array = logventa.Lista_Tipos_Pagos_Venta(obj);

        for (int i = 0; i < array.size(); i++) {
            if (i == 0) {
                this.jComboBox1.addItem(array.get(i).getId_Venta());
                this.jTextField9.setText(String.valueOf(array.get(i).getTotal()));
            } else {
                this.jComboBox2.addItem(array.get(i).getId_Venta());
                this.jTextField10.setText(String.valueOf(array.get(i).getTotal()));
            }
        }
    }
    private void AnularEnvio(){
        obj = new Venta(idbotica, idventa);
        listaVenta = logventa.ListaAnulaEnvioEfectivo(obj);
        String numero = null;
        for (int i = 0; i < listaVenta.size(); i++) {

            numero = listaVenta.get(i).getnumeroentregaefec();
        }
        limpiar();
        JOptionPane.showMessageDialog(this,"Se Anuló en Registro de Envio Efectivo a Oficina","NORTFARMA",JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiar(){

        this.txtnumero.setText("");
        //this.txtUsuariosistema.setText("");
        //this.txtautorizado.setText("");
        this.txtcajero.setText("");
        this.txttotal.setText("");
        limpiarTabla();
    }

    private void limpiarTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_EnvioDetalle.removeRow(i);
            }
        }
    }
    
    private void LimpiatTabla() {
        int cant = tablanotas.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_notas.removeRow(i);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTRUC;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField9;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtFechaVta;
    private javax.swing.JTextField txtcajero;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtidcajero;
    private javax.swing.JTextField txtidventa;
    private javax.swing.JTextField txtigv;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttipoventa;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
