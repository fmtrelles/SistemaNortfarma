/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacturasDelMes.java
 *
 * Created on 04/01/2012, 05:04:27 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class FacturasDelMes extends javax.swing.JInternalFrame {

    private DefaultTableModel model_notas;
    TableColumnModel colModel;
    private DefaultTableModel model;
    TableColumnModel colModel1;
    Object[] datosDetalle = new Object[6];
    private DefaultTableModel model_resu;
    TableColumnModel colModel2;
    Object[] tbtotales = new Object[4];
    LogicaVenta logventa = new LogicaVenta();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    AplicacionVentas objventas;
    private String idventa = "";
    private String idbotica = null;
    Venta entventa = null;
    int mes;
    List<Venta> Lista_ventas;
    Object[] datosDetalle_1 = new Object[6];

    /** Creates new form FacturasDelMes */
    public FacturasDelMes(AplicacionVentas obj) {
        initComponents();
        objventas = obj;
        idbotica = objventas.getIdbotica();
        jTabbedPane1.setEnabledAt(1, false);

        model_notas = (DefaultTableModel) tablanotas.getModel();
        colModel = tablanotas.getColumnModel();

        model = (DefaultTableModel) this.jTable2.getModel();
        colModel1 = jTable2.getColumnModel();

        model_resu = (DefaultTableModel) this.jTable3.getModel();
        colModel2 = jTable3.getColumnModel();

        this.jButton4.setEnabled(false);
        AparienciaTabla();

    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.tablanotas.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.CENTER);
        this.tablanotas.getColumnModel().getColumn(4).setCellRenderer(tcenter);

        JTableHeader cabecera1 = new JTableHeader(this.jTable3.getColumnModel());
        cabecera1.setReorderingAllowed(false);
        this.jTable3.getColumnModel().getColumn(1).setCellRenderer(tcenter);
        this.jTable3.getColumnModel().getColumn(2).setCellRenderer(tcenter);
        this.jTable3.getColumnModel().getColumn(3).setCellRenderer(tcenter);

        DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
        tcenter1.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        this.jTable2.getColumnModel().getColumn(5).setCellRenderer(tcenter);

        DefaultTableCellRenderer tcenter2 = new DefaultTableCellRenderer();
        tcenter2.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTable2.getColumnModel().getColumn(3).setCellRenderer(tcenter2);
        this.jTable2.getColumnModel().getColumn(2).setCellRenderer(tcenter2);


    }

    private void BuscaVentas() {
        idventa = this.txtInterno.getText().trim() + '%';
        entventa = new Venta(this.idbotica, this.idventa, mes);
        Lista_ventas = logventa.Lista_Ventas_Facturas(entventa);

        if (Lista_ventas.size() > 0) {
            LimpiatTabla();
        }

        for (int i = 0; i < Lista_ventas.size(); i++) {
            datosDetalle[0] = Lista_ventas.get(i).getFechaPago();
            datosDetalle[1] = Lista_ventas.get(i).getId_Venta();
            datosDetalle[2] = Lista_ventas.get(i).getSerie();
            datosDetalle[3] = Lista_ventas.get(i).getNumero();
            datosDetalle[4] = Lista_ventas.get(i).getTotal();
            datosDetalle[5] = Lista_ventas.get(i).getNombre_RazonSocial();
            model_notas.addRow(datosDetalle);
        }


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablanotas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtInterno = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtserie = new javax.swing.JTextField();
        txtnumero = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTRUC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        txtcajero = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FacturasDelMes.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        jMonthChooser1.setName("jMonthChooser1"); // NOI18N
        jPanel1.add(jMonthChooser1);

        jYearChooser1.setName("jYearChooser1"); // NOI18N
        jPanel1.add(jYearChooser1);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablanotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Interno", "Serie", "Numero", "Total", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablanotas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablanotas.setName("tablanotas"); // NOI18N
        tablanotas.setSelectionBackground(resourceMap.getColor("tablanotas.selectionBackground")); // NOI18N
        tablanotas.setSelectionForeground(resourceMap.getColor("tablanotas.selectionForeground")); // NOI18N
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
        tablanotas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablanotas.getColumnModel().getColumn(0).setResizable(false);
        tablanotas.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title5")); // NOI18N
        tablanotas.getColumnModel().getColumn(1).setResizable(false);
        tablanotas.getColumnModel().getColumn(1).setPreferredWidth(30);
        tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
        tablanotas.getColumnModel().getColumn(2).setResizable(false);
        tablanotas.getColumnModel().getColumn(2).setPreferredWidth(25);
        tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
        tablanotas.getColumnModel().getColumn(3).setResizable(false);
        tablanotas.getColumnModel().getColumn(3).setPreferredWidth(60);
        tablanotas.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
        tablanotas.getColumnModel().getColumn(4).setResizable(false);
        tablanotas.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablanotas.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title3")); // NOI18N
        tablanotas.getColumnModel().getColumn(5).setResizable(false);
        tablanotas.getColumnModel().getColumn(5).setPreferredWidth(350);
        tablanotas.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title4")); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtInterno.setBackground(resourceMap.getColor("txtInterno.background")); // NOI18N
        txtInterno.setFont(resourceMap.getFont("txtInterno.font")); // NOI18N
        txtInterno.setForeground(resourceMap.getColor("txtInterno.foreground")); // NOI18N
        txtInterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInterno.setText(resourceMap.getString("txtInterno.text")); // NOI18N
        txtInterno.setName("txtInterno"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        txtserie.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtserie.setEditable(false);
        txtserie.setForeground(resourceMap.getColor("txtserie.foreground")); // NOI18N
        txtserie.setName("txtserie"); // NOI18N

        txtnumero.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtnumero.setEditable(false);
        txtnumero.setForeground(resourceMap.getColor("txtnumero.foreground")); // NOI18N
        txtnumero.setName("txtnumero"); // NOI18N

        txtcliente.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtcliente.setEditable(false);
        txtcliente.setForeground(resourceMap.getColor("txtcliente.foreground")); // NOI18N
        txtcliente.setName("txtcliente"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtdireccion.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtdireccion.setEditable(false);
        txtdireccion.setForeground(resourceMap.getColor("txtdireccion.foreground")); // NOI18N
        txtdireccion.setName("txtdireccion"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        TXTRUC.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        TXTRUC.setEditable(false);
        TXTRUC.setForeground(resourceMap.getColor("TXTRUC.foreground")); // NOI18N
        TXTRUC.setName("TXTRUC"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtvendedor.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtvendedor.setEditable(false);
        txtvendedor.setForeground(resourceMap.getColor("txtvendedor.foreground")); // NOI18N
        txtvendedor.setName("txtvendedor"); // NOI18N

        txtcajero.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtcajero.setEditable(false);
        txtcajero.setForeground(resourceMap.getColor("txtcajero.foreground")); // NOI18N
        txtcajero.setName("txtcajero"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "PRODUCTO", "UND", "FRACC", "PVP", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(280);
        jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
        jTable2.getColumnModel().getColumn(4).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
        jTable2.getColumnModel().getColumn(5).setResizable(false);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtfecha.setBackground(resourceMap.getColor("txtserie.background")); // NOI18N
        txtfecha.setEditable(false);
        txtfecha.setForeground(resourceMap.getColor("txtfecha.foreground")); // NOI18N
        txtfecha.setText(resourceMap.getString("txtfecha.text")); // NOI18N
        txtfecha.setName("txtfecha"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interno", "Total", "SubTotal", "IGV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable3.setColumnSelectionAllowed(true);
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionBackground(resourceMap.getColor("jTable3.selectionBackground")); // NOI18N
        jTable3.setSelectionForeground(resourceMap.getColor("jTable3.selectionForeground")); // NOI18N
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable3.columnModel.title0")); // NOI18N
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable3.columnModel.title1")); // NOI18N
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable3.columnModel.title2")); // NOI18N
        jTable3.getColumnModel().getColumn(3).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable3.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(TXTRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtvendedor)
                            .addComponent(txtcajero, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(txtcliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6))
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4))
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7))
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8))
                    .addComponent(TXTRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel9)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mes = this.jMonthChooser1.getMonth();
        mes++;
        BuscaVentas();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mes = this.jMonthChooser1.getMonth();
        mes++;
        BuscaVentas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablanotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanotasMouseClicked
        if (evt.getClickCount() % 2 == 0) {
            SeleccionarVenta();
            this.jButton1.setEnabled(false);
            this.jButton4.setEnabled(true);
        }
    }//GEN-LAST:event_tablanotasMouseClicked

    private void tablanotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablanotasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SeleccionarVenta();
            this.jButton1.setEnabled(false);
            this.jButton4.setEnabled(true);
        }
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
    }//GEN-LAST:event_tablanotasKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        this.jButton4.setEnabled(false);
        this.txtcajero.setText("");
        this.txtcliente.setText("");
        this.txtdireccion.setText("");
        this.TXTRUC.setText("");
        this.txtnumero.setText("");
        this.jTextField1.setText("");
        this.txtvendedor.setText("");
        LimpiatTabla_Totales();
        this.jButton1.setEnabled(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void SeleccionarVenta() {
        LimpiatTabla_Totales();
        LimpiatTabla_01();
        int fila = this.tablanotas.getSelectedRow();
        String miruc;


        if (fila >= 0 && Lista_ventas.size() > 0) {
            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));

            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setSelectedIndex(1);

            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));
            txtcliente.setText(Lista_ventas.get(fila).getNombre_RazonSocial());
            if (Lista_ventas.get(fila).getRUC() == null) {
                miruc = "";
            } else {
                miruc = String.valueOf(Lista_ventas.get(fila).getRUC());
            }

            jTextField1.setText(Lista_ventas.get(fila).getId_Venta());
            TXTRUC.setText(miruc);
            txtserie.setText(Lista_ventas.get(fila).getSerie());
            txtnumero.setText(Lista_ventas.get(fila).getNumero());
            txtdireccion.setText(Lista_ventas.get(fila).getDireccion());
            txtvendedor.setText(Lista_ventas.get(fila).getApellido());
            txtcajero.setText(Lista_ventas.get(fila).getCajero());
            txtfecha.setText(Lista_ventas.get(fila).getFechaPago().toString());

            tbtotales[0] = Lista_ventas.get(fila).getId_Venta();
            tbtotales[1] = Lista_ventas.get(fila).getTotal();
            tbtotales[2] = Lista_ventas.get(fila).getSubTotal();
            tbtotales[3] = Lista_ventas.get(fila).getIGV();
            model_resu.addRow(tbtotales);

            CargarProductos();


        }

    }

    private void CargarProductos() {
        idventa = jTextField1.getText().trim();
        entventa = new Venta(idbotica, idventa);
        List<Venta> listaVenta = logventa.ListaVenta_Detalle(entventa);

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle_1[0] = i + 1;
            datosDetalle_1[1] = listaVenta.get(i).getDescr_Producto();
            datosDetalle_1[2] = listaVenta.get(i).getUnidad();
            datosDetalle_1[3] = listaVenta.get(i).getFraccion();
            datosDetalle_1[4] = listaVenta.get(i).getPrecio_Venta_Final();
            datosDetalle_1[5] = listaVenta.get(i).getTotal_producto();

            model.addRow(datosDetalle_1);
        }

    }

    private void CerrarVentana() {
        if (this.tablanotas.getRowCount() > 0) {
            int p = JOptionPane.showConfirmDialog(null, " Desea Cerrar Esta Ventana ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                objventas.marcacdo = false;
                LimpiatTabla();
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setSelectedIndex(0);
                dispose();

            }

        } else {

            objventas.marcacdo = false;
            LimpiatTabla();
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setSelectedIndex(0);
            dispose();
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

    private void LimpiatTabla_01() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_Totales() {
        int cant = model_resu.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_resu.removeRow(i);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTRUC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtInterno;
    private javax.swing.JTextField txtcajero;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
