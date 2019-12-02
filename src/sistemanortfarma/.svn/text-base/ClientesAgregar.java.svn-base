/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientesAgregar1.java
 *
 * Created on 04/01/2012, 11:12:49 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaClientes;
import CapaLogica.LogicaEmpresas;
import entidad.Boticas;
import entidad.Empresas;
import entidad.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class ClientesAgregar extends javax.swing.JInternalFrame {

    List<Boticas> listaboticas = new ArrayList<Boticas>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<Empresas> listEmpresas = new ArrayList<Empresas>();
    LogicaEmpresas objEmpresas = new LogicaEmpresas();
    List<Clientes> listClientes = new ArrayList<Clientes>();
    LogicaClientes objClientes = new LogicaClientes();
    Clientes entidadClientes = new Clientes();
    private String idbotica;
    List<Clientes> listaclientes = new ArrayList<Clientes>();
    Object[] datosDetalle = new Object[5];
    private DefaultTableModel modelClientes;
    Administracion objeto = null;

    /** Creates new form ClientesAgregar1 */
    public ClientesAgregar(java.awt.Frame parent, String idboti, Administracion obj) {
        initComponents();
        objeto = obj;
        idbotica = idboti;
        modelClientes = (DefaultTableModel) jTable1.getModel();
        cargarBoticas();
        cargarEmpresas();
        CargasClientes();
        txtFono.setDocument(new LimitadorLetras(txtFono, 8));
        this.txtdni.setDocument(new LimitadorLetras(txtdni, 8));
        txtDocumento.setDocument(new LimitadorLetras(txtDocumento, 11));
    }

    private void validarBotones(Boolean Estado) {


        txtCodAdm.setText("");
        txtCodAdm.setEnabled(Estado);
        txtDireccion.setText("");
        txtDireccion.setEnabled(Estado);
        txtDocumento.setText("");
        txtDocumento.setEnabled(Estado);
        txtFono.setText("");
        txtFono.setEnabled(Estado);
        txtHalcon.setText("");
        txtHalcon.setEnabled(Estado);
        txtNombre.setText("");
        txtdni.setText("");
        txtNombre.setEnabled(Estado);
        this.txtdni.setEnabled(Estado);


        //lasboticas.setEnabled(Estado);
        lasempresas.setEnabled(Estado);
        btnCancelar.setEnabled(Estado);
        btnNuevo.setEnabled(!Estado);
        btnGuardar.setEnabled(Estado);


    }

    private void CargasClientes() {
        LimpiatTabla();
        String filtro;
        listaclientes.removeAll(listaclientes);
        filtro = this.jTextField1.getText().trim();
        filtro = filtro + '%';
        listaclientes = objClientes.listaClientes_Detallado(idbotica, filtro);

        for (int i = 0; i < listaclientes.size(); i++) {

            datosDetalle[0] = listaclientes.get(i).getId_Cliente();
            datosDetalle[1] = listaclientes.get(i).getNombre_RazonSocial();
            datosDetalle[2] = listaclientes.get(i).getEmpresa();
            datosDetalle[3] = listaclientes.get(i).getRUC_DNI();
            datosDetalle[4] = listaclientes.get(i).getDireccion();

            modelClientes.addRow(datosDetalle);
        }
    }

    private void cargarEmpresas() {

        listEmpresas = objEmpresas.ListaEmpresas();

        for (int i = 0; i < listEmpresas.size(); i++) {
            lasempresas.addItem(listEmpresas.get(i).getRazonSocial());
        }

    }

    private void cargarBoticas() {

        listaboticas = objBoticas.ListarobjBoticas();
        lasboticas.addItem("Seleccione Opcion");

        for (int i = 0; i < listaboticas.size(); i++) {

            lasboticas.addItem(listaboticas.get(i).getDescripcion());
            System.out.println(objBoticas.RecuperaIDBotica(listaboticas.get(i).getDescripcion().trim()) + "-" + listaboticas.get(i).getDescripcion() + "-" + OpcionesMenu.getIdbotica().trim());
            if (objBoticas.RecuperaIDBotica(listaboticas.get(i).getDescripcion().trim()).compareTo(OpcionesMenu.getIdbotica().trim()) == 1) {
                System.out.println("IGUAL");
                lasboticas.setSelectedIndex(i);
                i = listaboticas.size() + 1;
            } else {
                System.out.println("Diferentes");

            }

        }

        lasboticas.setEnabled(false);



    }
    private String[] ArrayBoticas = {};

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelClientes.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtFono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHalcon = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lasboticas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        lasempresas = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtCodAdm = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ClientesAgregar.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setFont(resourceMap.getFont("jPanel2.font")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        txtFono.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtFono.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtFono.setEnabled(false);
        txtFono.setName("txtFono"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNombre.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtNombre.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtNombre.setEnabled(false);
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.setNextFocusableComponent(txtDireccion);

        jLabel6.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtDireccion.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtDireccion.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtDireccion.setEnabled(false);
        txtDireccion.setName("txtDireccion"); // NOI18N
        txtDireccion.setNextFocusableComponent(txtCodAdm);

        jLabel1.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtHalcon.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtHalcon.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtHalcon.setEnabled(false);
        txtHalcon.setName("txtHalcon"); // NOI18N
        txtHalcon.setNextFocusableComponent(txtNombre);

        txtDocumento.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtDocumento.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtDocumento.setEnabled(false);
        txtDocumento.setName("txtDocumento"); // NOI18N
        txtDocumento.setNextFocusableComponent(txtdni);

        jLabel4.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        lasboticas.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        lasboticas.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas.setEnabled(false);
        lasboticas.setName("lasboticas"); // NOI18N
        lasboticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticasActionPerformed(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lasempresas.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        lasempresas.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasempresas.setEnabled(false);
        lasempresas.setName("lasempresas"); // NOI18N
        lasempresas.setNextFocusableComponent(txtHalcon);

        jLabel8.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtCodAdm.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtCodAdm.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtCodAdm.setEnabled(false);
        txtCodAdm.setName("txtCodAdm"); // NOI18N
        txtCodAdm.setNextFocusableComponent(txtDocumento);

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtdni.setBackground(resourceMap.getColor("txtFono.background")); // NOI18N
        txtdni.setFont(resourceMap.getFont("txtFono.font")); // NOI18N
        txtdni.setEnabled(false);
        txtdni.setName("txtdni"); // NOI18N
        txtdni.setNextFocusableComponent(txtFono);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lasboticas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lasempresas, javax.swing.GroupLayout.Alignment.LEADING, 0, 189, Short.MAX_VALUE))
                                .addComponent(txtHalcon, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtdni)
                                    .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(txtCodAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lasboticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lasempresas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHalcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCodAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "EMPRESA", "RUC", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnSalir.setText(resourceMap.getString("btnSalir.text")); // NOI18N
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setToolTipText(resourceMap.getString("btnGuardar.toolTipText")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText(resourceMap.getString("btnNuevo.text")); // NOI18N
        btnNuevo.setToolTipText(resourceMap.getString("btnNuevo.toolTipText")); // NOI18N
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasboticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticasActionPerformed
}//GEN-LAST:event_lasboticasActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        validarBotones(true);
        this.txtHalcon.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Integer pase = 1;

        if (txtHalcon.getText().length() != 0) {
            if (txtNombre.getText().length() != 0) {
                // if(txtDocumento.getText().length()!=0) {
                entidadClientes = new Clientes(
                        txtDocumento.getText().toString(),
                        txtdni.getText(),
                        txtNombre.getText().toString(),
                        txtDireccion.getText().toString(),
                        txtFono.getText().toString(),
                        lasempresas.getItemAt(lasempresas.getSelectedIndex()).toString(),
                        lasboticas.getItemAt(lasboticas.getSelectedIndex()).toString(),
                        txtCodAdm.getText().toString(),
                        txtHalcon.getText().toString(), idbotica);

                if (objClientes.GuardarCliente2(entidadClientes)) {
                    JOptionPane.showMessageDialog(this, "CLIENTE REGISTRADO CORRECTAMENTE", "GUARDADO", JOptionPane.INFORMATION_MESSAGE);
                    LimpiatTabla();
                    CargasClientes();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al Guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }

                validarBotones(false);
            } else {
                JOptionPane.showMessageDialog(this, "Porfavor Debe de Ingresar un numero de Documento", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
        } else {
            JOptionPane.showMessageDialog(this, "El Codigo de Halcon Ingresado es Incorrecto ", "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        objeto.marcacdo = false;
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            txtHalcon.setText(listaclientes.get(fila).getCodAsociado());
            txtNombre.setText(listaclientes.get(fila).getNombre_RazonSocial());
            txtDireccion.setText(listaclientes.get(fila).getDireccion());
            txtCodAdm.setText(listaclientes.get(fila).getCodADm());
            txtDocumento.setText(listaclientes.get(fila).getRUC_DNI());
            txtdni.setText(listaclientes.get(fila).getDNI());
            txtFono.setText(listaclientes.get(fila).getTelefono());
        }



    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            txtHalcon.setText(listaclientes.get(fila).getCodAsociado());
            txtNombre.setText(listaclientes.get(fila).getNombre_RazonSocial());
            txtDireccion.setText(listaclientes.get(fila).getDireccion());
            txtCodAdm.setText(listaclientes.get(fila).getCodADm());
            txtDocumento.setText(listaclientes.get(fila).getRUC_DNI());
            txtdni.setText(listaclientes.get(fila).getDNI());
            txtFono.setText(listaclientes.get(fila).getTelefono());
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        CargasClientes();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CargasClientes();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox lasboticas;
    private javax.swing.JComboBox lasempresas;
    private javax.swing.JTextField txtCodAdm;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtHalcon;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables
}
