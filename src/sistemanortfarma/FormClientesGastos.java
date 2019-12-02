package sistemanortfarma;

import CapaLogica.LogicaClientes;
import entidad.Clientes;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormClientesGastos extends JDialog {

    LogicaClientes objcliente = new LogicaClientes();
    FormGastosCaja objgastoscaja;
    FomCuadreGastos objcuadregastos;
    NuevoClienteGastos objgastoscaja1;
    FormGastosCaja objgastoscaja2;
    
    /*
    VARIABLES DE LA TABLA CLIENTES
     */
    public static String CodigoCliente;
    public static int Id_Cliente;
    public static String RUC_DNI;
    public static String Nombre_RazonSocial;
    public static String Direccion;
    private static String DNI;
    public static String Empresa;
    public static String telefono;
    public static String idbotica;
    public static String id_botica;
    String[] datosDetalle = new String[6];
    List<Clientes> listacleintes;
    private DefaultTableModel modelClientes;
    private static double saldo;
    private int bandera = 0;
    private int opcion = 0;
    //private String idbotica;
    private String empresa;
    TableColumn columnaTabla = null;
    MuestraVentana objetoventana = new MuestraVentana();
    
    FormClientesGastos(FormGastosCaja obj, java.awt.Frame ventana, String id_botica) {
        //throw new UnsupportedOperationException("Not yet implemented");
        super(ventana, true);
        initComponents();
        objgastoscaja = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = id_botica;
        bandera = 0;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        opcion = 1;
        this.jButton4.setVisible(false);
        //this.jButton5.setVisible(false);
    }

    FormClientesGastos(FomCuadreGastos obj, JFrame ventana, String id_botica) {
        super(ventana, true);
        initComponents();
        objcuadregastos = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = id_botica;
        bandera = 1;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        opcion = 2;
        this.jButton4.setVisible(false);
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
    }

    public static String getBotica() {
        return idbotica;
    }
    public static void setBotica(String idbotica) {
        FormClientesGastos.idbotica = idbotica;
    }
    public static String getTelefono() {
        return telefono;
    }

    public static void setTelefono(String telefono) {
        FormClientesGastos.telefono = telefono;
    }

    public static String getEmpresa() {
        return Empresa;
    }

    public static void setEmpresa(String Empresa) {
        FormClientesGastos.Empresa = Empresa;
    }

    public static String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        FormClientesGastos.DNI = DNI;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        FormClientesGastos.saldo = saldo;
    }

    public static String getDireccion() {
        return Direccion;
    }

    public static void setDireccion(String Direccion) {
        FormClientesGastos.Direccion = Direccion;
    }

    public static int getId_Cliente() {
        return Id_Cliente;
    }

    public static void setId_Cliente(int Id_Cliente) {
        FormClientesGastos.Id_Cliente = Id_Cliente;
    }

    public static String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public static void setNombre_RazonSocial(String Nombre_RazonSocial) {
        FormClientesGastos.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public static String getRUC_DNI() {
        return RUC_DNI;
    }

    public static void setRUC_DNI(String RUC_DNI) {
        FormClientesGastos.RUC_DNI = RUC_DNI;
    }

    public static String getCodigoCliente() {
        return CodigoCliente;
    }

    public static void setCodigoCliente(String CodigoCliente) {
        FormClientesGastos.CodigoCliente = CodigoCliente;
    }

    private void AgregaTabla() {

        listacleintes = objcliente.ListaClientesGastos("", idbotica);

        for (int i = 0; i < listacleintes.size(); i++) {
            Id_Cliente = listacleintes.get(i).getId_Cliente();
            RUC_DNI = listacleintes.get(i).getRUC_DNI();
            Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
            empresa = listacleintes.get(i).getDNI();
            Direccion = listacleintes.get(i).getDireccion();
            telefono = listacleintes.get(i).getTelefono();

            datosDetalle[0] = String.valueOf(Id_Cliente);
            datosDetalle[1] = RUC_DNI;
            datosDetalle[2] = Nombre_RazonSocial;
            datosDetalle[3] = empresa;
            datosDetalle[4] = Direccion;
            datosDetalle[5] = telefono;

            if (datosDetalle.toString().compareTo("") != 0) {
                modelClientes.addRow(datosDetalle);
            }
        }

    }

    private void AgregaTabla_INV() {

        listacleintes = objcliente.ListaClientes1("INV", idbotica);

        for (int i = 0; i < listacleintes.size(); i++) {
            Id_Cliente = listacleintes.get(i).getId_Cliente();
            RUC_DNI = listacleintes.get(i).getRUC_DNI();
            Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
            empresa = listacleintes.get(i).getEmpresa();
            Direccion = listacleintes.get(i).getDireccion();
            telefono = listacleintes.get(i).getTelefono();

            datosDetalle[0] = String.valueOf(Id_Cliente);
            datosDetalle[1] = RUC_DNI;
            datosDetalle[2] = Nombre_RazonSocial;
            datosDetalle[3] = empresa;
            datosDetalle[4] = Direccion;
            datosDetalle[5] = telefono;

            if (datosDetalle.toString().compareTo("") != 0) {
                modelClientes.addRow(datosDetalle);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormClientesGastos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBackground(resourceMap.getColor("jTable1.background")); // NOI18N
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "RUC", "Razon Social", "Empresa", "Direccion", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
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
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(68);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jLabel1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Razon Social", "DNI", "RUC", "Telefono" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(resourceMap.getString("jTextField1.toolTipText")); // NOI18N
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
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jcMousePanel1.setColor1(resourceMap.getColor("jcMousePanel1.color1")); // NOI18N
        jcMousePanel1.setColor2(resourceMap.getColor("jcMousePanel1.color2")); // NOI18N
        jcMousePanel1.setdarker(false);
        jcMousePanel1.setgPosX2(400.0F);
        jcMousePanel1.setIconLogo(resourceMap.getIcon("jcMousePanel1.iconLogo")); // NOI18N
        jcMousePanel1.setModo(3);
        jcMousePanel1.setName("jcMousePanel1"); // NOI18N
        jcMousePanel1.setPosicionLogo(new java.awt.Point(300, 0));
        jcMousePanel1.setPreferredSize(new java.awt.Dimension(640, 78));

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        javax.swing.GroupLayout jcMousePanel1Layout = new javax.swing.GroupLayout(jcMousePanel1);
        jcMousePanel1.setLayout(jcMousePanel1Layout);
        jcMousePanel1Layout.setHorizontalGroup(
            jcMousePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcMousePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
        );
        jcMousePanel1Layout.setVerticalGroup(
            jcMousePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcMousePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jSeparator1.setName("jSeparator1"); // NOI18N

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(60, 41));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

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
        jToolBar1.add(jButton2);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

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
        jToolBar1.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcMousePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcMousePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Recuepra_Cliente() {
        try {

            int fila = this.jTable1.getSelectedRow();
            /*
            RECUPERO DATOS PARA AGREGAR A MI CLASE COTIZACION
             */
            CodigoCliente = this.jTable1.getValueAt(fila, 0).toString().trim();
            Empresa = this.jTable1.getValueAt(fila, 3).toString().trim();
            Nombre_RazonSocial = this.jTable1.getValueAt(fila, 2).toString();
            RUC_DNI = String.valueOf(this.jTable1.getValueAt(fila, 1));
            Direccion = String.valueOf(this.jTable1.getValueAt(fila, 4));
            telefono = listacleintes.get(fila).getTelefono();

            if (RUC_DNI != null) {
                setRUC_DNI(RUC_DNI);
            }

            if (Direccion != null) {
                setDireccion(Direccion);
            }

            if (Empresa != null) {
                setEmpresa(Empresa);
            }

            if (telefono != null) {
                setTelefono(telefono);
            }


            //saldo = listacleintes.get(fila).getSaldo();
            DNI = listacleintes.get(fila).getDNI();
            setNombre_RazonSocial(Nombre_RazonSocial);
            //setSaldo(saldo);
            setDNI(DNI);

            /*
            REALIZO MIS SET PARA AGREGAR A MIS DATOS Y RECUPERRALOS EN LA CLASE COTIZACION
             */

            setCodigoCliente(CodigoCliente);

            if (bandera == 0) {
                
                objgastoscaja.AsignaDatosCliente();             
                
            } else if (bandera == 1) {
            objcuadregastos.AsignaDatosCliente();
            }

            this.hide();

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE CLIENTES CAPA VISTA METODO jTable1KeyPressed:" + ex.toString());
        }
    }

    private void Modifica_Datos_Cliente() {
        Form_ModificaCliente objmodifica = new Form_ModificaCliente(objetoventana, true, idbotica, Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()));
        objmodifica.pack();
        objmodifica.setVisible(true);
    }

    private void NuevoUsuario(String idbotica) {
        NuevoClienteGastos pe = new NuevoClienteGastos(this, this.objetoventana,idbotica);
        pe.pack();
        pe.setVisible(true);
    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Recuepra_Cliente();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            this.jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
            Modifica_Datos_Cliente();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoUsuario(idbotica);
        }

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            this.jTable1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            dispose();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoUsuario(idbotica);
        }

}//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    }//GEN-LAST:event_jTable1KeyReleased

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == 27) {
            dispose();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Recuepra_Cliente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jTextField1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Recuepra_Cliente();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Modifica_Datos_Cliente();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BuscaCliente();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        NuevoUsuario(objgastoscaja.idbotica);
}//GEN-LAST:event_jButton6ActionPerformed

    private void BuscaCliente() {

        String filtro = jTextField1.getText().trim();
        listacleintes.removeAll(listacleintes);
        int ind = this.jComboBox1.getSelectedIndex();

        if (ind == 0) {

            filtro = '%' + filtro + '%';
            listacleintes = objcliente.ListaClientes3(filtro, idbotica);
            LimpiatTabla();

            for (int i = 0; i < listacleintes.size(); i++) {
                Id_Cliente = listacleintes.get(i).getId_Cliente();
                RUC_DNI = listacleintes.get(i).getRUC_DNI();
                Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                empresa = listacleintes.get(i).getEmpresa();
                Direccion = listacleintes.get(i).getDireccion();
                saldo = listacleintes.get(i).getSaldo();
                telefono = listacleintes.get(i).getTelefono();

                datosDetalle[0] = String.valueOf(Id_Cliente);
                datosDetalle[1] = RUC_DNI;
                datosDetalle[2] = Nombre_RazonSocial;
                datosDetalle[3] = empresa;
                datosDetalle[4] = Direccion;
                datosDetalle[5] = telefono;

                if (datosDetalle.toString().compareTo("") != 0) {
                    modelClientes.addRow(datosDetalle);
                }
            }

        } else {
            if (ind == 1) //buscar por dni
            {

                if (!String.valueOf(filtro).isEmpty()) {
                    filtro = filtro + '%';
                    listacleintes = objcliente.ListaClientes_PORDNI(filtro);
                    LimpiatTabla();

                    for (int i = 0; i < listacleintes.size(); i++) {
                        Id_Cliente = listacleintes.get(i).getId_Cliente();
                        RUC_DNI = listacleintes.get(i).getRUC_DNI();
                        Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                        Direccion = listacleintes.get(i).getDireccion();
                        saldo = listacleintes.get(i).getSaldo();
                        telefono = listacleintes.get(i).getTelefono();

                        datosDetalle[0] = String.valueOf(Id_Cliente);
                        datosDetalle[1] = RUC_DNI;
                        datosDetalle[2] = Nombre_RazonSocial;
                        datosDetalle[3] = empresa;
                        datosDetalle[4] = Direccion;
                        datosDetalle[5] = telefono;

                        if (datosDetalle.toString().compareTo("") != 0) {
                            modelClientes.addRow(datosDetalle);
                        }

                    }
                }
            } else {
                if (ind == 2) //buscar por RUC
                {

                    if (!String.valueOf(filtro).isEmpty()) {
                        filtro = filtro + '%';
                        listacleintes = objcliente.ListaClientes_PORRUC(filtro);
                        LimpiatTabla();

                        for (int i = 0; i < listacleintes.size(); i++) {
                            Id_Cliente = listacleintes.get(i).getId_Cliente();
                            RUC_DNI = listacleintes.get(i).getRUC_DNI();
                            Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                            Direccion = listacleintes.get(i).getDireccion();
                            saldo = listacleintes.get(i).getSaldo();
                            telefono = listacleintes.get(i).getTelefono();

                            datosDetalle[0] = String.valueOf(Id_Cliente);
                            datosDetalle[1] = RUC_DNI;
                            datosDetalle[2] = Nombre_RazonSocial;
                            datosDetalle[3] = empresa;
                            datosDetalle[4] = Direccion;
                            datosDetalle[5] = telefono;

                            if (datosDetalle.toString().compareTo("") != 0) {
                                modelClientes.addRow(datosDetalle);
                            }


                        }
                    }
                } else {

                    if (!String.valueOf(filtro).isEmpty()) {
                        filtro = filtro + '%';
                        listacleintes = objcliente.ListaClientes_Telefono(filtro);
                        LimpiatTabla();

                        for (int i = 0; i < listacleintes.size(); i++) {
                            Id_Cliente = listacleintes.get(i).getId_Cliente();
                            RUC_DNI = listacleintes.get(i).getRUC_DNI();
                            Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                            Direccion = listacleintes.get(i).getDireccion();
                            saldo = listacleintes.get(i).getSaldo();
                            telefono = listacleintes.get(i).getTelefono();

                            datosDetalle[0] = String.valueOf(Id_Cliente);
                            datosDetalle[1] = RUC_DNI;
                            datosDetalle[2] = Nombre_RazonSocial;
                            datosDetalle[3] = empresa;
                            datosDetalle[4] = Direccion;
                            datosDetalle[5] = telefono;

                            if (datosDetalle.toString().compareTo("") != 0) {
                                modelClientes.addRow(datosDetalle);
                            }
                        }
                    }
                }
            }
        }
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelClientes.removeRow(i);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    // End of variables declaration//GEN-END:variables
}
