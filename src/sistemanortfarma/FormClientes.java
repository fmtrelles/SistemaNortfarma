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
import entidad.Personal;
import CapaLogica.LogicaPersonal;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormClientes extends JDialog {

    LogicaClientes objcliente = new LogicaClientes();
    Cotizacion objcotiza;
    CruceInventarios objcruce;
    Descargos_Cli objDescCli;
    CotizacionSoat objcotizasoat;
    DarCredito obcredito;
    RenovarCredito objrenovarCredito;
    ReporteNotasCredito Objreportenotas;
    RealizaVenta objventa;
    FormBoletas_Manuales BoleManual;
    List<Personal> listaRUCOficina;
    List<Personal> listaRUCOficinaRecuperado;
    LogicaPersonal logicaPersonal = new LogicaPersonal();
    /*
    VARIABLES DE LA TABLA CLIENTES
     */
    boolean escogio = false;
    
    Thread showThread;
    public static String CodigoCliente;
    public static int Id_Cliente;
    public static String RUC_DNI;
    public static String Nombre_RazonSocial;
    public static String Direccion;
    private static String DNI;
    public static String Empresa;
    public static String telefono;
    String[] datosDetalle = new String[6];
    List<Clientes> listacleintes;
    private DefaultTableModel modelClientes;
    private static double saldo;
    private int bandera = 0;
    private static String idbotica;
    private String empresa;
    TableColumn columnaTabla = null;
    MuestraVentana objetoventana = new MuestraVentana();
    

    public FormClientes(Cotizacion obj, java.awt.Frame ventana, String idboti) {
        super(ventana, true);
        initComponents();
        objcotiza = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        
    }

    public FormClientes(DarCredito obj, java.awt.Frame ventana) {
        super(ventana, true);
        initComponents();
        obcredito = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        bandera = 2;        
    }

    public FormClientes(RenovarCredito obj, java.awt.Frame ventana) {
        super(ventana, true);
        initComponents();
        objrenovarCredito = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        bandera = 3;        
    }

    public FormClientes(RealizaVenta obj, java.awt.Frame ventana, String idboti) {
        super(ventana, true);
        initComponents();
        bandera = 1;
        idbotica = idboti;
        objventa = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
    }

    public FormClientes(ReporteNotasCredito obj, java.awt.Frame ventana, String idboti) {
        super(ventana, true);
        initComponents();
        bandera = 4;
        idbotica = idboti;
        Objreportenotas = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        
    }

    public FormClientes(FormBoletas_Manuales obj, java.awt.Frame ventana, String idboti) {
        super(ventana, true);
        initComponents();
        bandera = 5;
        idbotica = idboti;
        BoleManual = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        
    }
    public FormClientes(FormBoletas_Manuales obj, java.awt.Frame ventana, String idboti, String inv) {
        super(ventana, true);
        initComponents();
        bandera = 5;
        idbotica = idboti;
        BoleManual = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        AgregaTabla_INV();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jComboBox1.requestFocus();
        
    }

     //Cotizacion obj, java.awt.Frame ventana, String idboti
    FormClientes(CotizacionSoat obj, JFrame ventana, String idboti) {
        //throw new UnsupportedOperationException("Not yet implemented");
        super(ventana, true);
        initComponents();
        bandera = 6;
        objcotizasoat = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        
    }

    FormClientes(CruceInventarios obj, JFrame ventana, String idboti) {
        super(ventana, true);
        initComponents();
        bandera = 7;
        objcruce = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        
    }

    FormClientes(Descargos_Cli obj, JFrame ventana, String idboti) {

        super(ventana, true);
        initComponents();
        bandera = 8;
        objDescCli = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //
        //btnActualiza.setVisible(false);
        //jSeparator6.setVisible(false);
        
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
    }    

    public static String getTelefono() {
        return telefono;
    }

    public static void setTelefono(String telefono) {
        FormClientes.telefono = telefono;
    }

    public static String getEmpresa() {
        return Empresa;
    }

    public static void setEmpresa(String Empresa) {
        FormClientes.Empresa = Empresa;
    }

    public static String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        FormClientes.DNI = DNI;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        FormClientes.saldo = saldo;
    }

    public static String getDireccion() {
        return Direccion;
    }

    public static void setDireccion(String Direccion) {
        FormClientes.Direccion = Direccion;
    }

    public static int getId_Cliente() {
        return Id_Cliente;
    }

    public static void setId_Cliente(int Id_Cliente) {
        FormClientes.Id_Cliente = Id_Cliente;
    }

    public static String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public static void setNombre_RazonSocial(String Nombre_RazonSocial) {
        FormClientes.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public static String getRUC_DNI() {
        return RUC_DNI;
    }

    public static void setRUC_DNI(String RUC_DNI) {
        FormClientes.RUC_DNI = RUC_DNI;
    }

    public static String getCodigoCliente() {
        return CodigoCliente;
    }

    public static void setCodigoCliente(String CodigoCliente) {
        FormClientes.CodigoCliente = CodigoCliente;
    }

    public static String getidbotica() {
        return idbotica;
    }

    public static void setidbotica(String idbotica) {
        FormClientes.idbotica = idbotica;
    }


    public void AgregaTabla() {
        listacleintes = objcliente.ListaClientes1("", idbotica);

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
        btnActualiza = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormClientes.class);
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
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
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jLabel1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Razon Social", "RUC", "DNI", "Telefono" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
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
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(589, Short.MAX_VALUE))
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

        btnActualiza.setIcon(resourceMap.getIcon("btnActualiza.icon")); // NOI18N
        btnActualiza.setText(resourceMap.getString("btnActualiza.text")); // NOI18N
        btnActualiza.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualiza.setName("btnActualiza"); // NOI18N
        btnActualiza.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnActualiza);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

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

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(60, 41));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

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

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setFocusable(false);
        jLabel16.setName("jLabel16"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addComponent(jcMousePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcMousePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

            /*String ENCONTRADO = objcliente.BUSCAREXISTERUC(RUC_DNI,"","",0,"0",0);   //pregunto si esta actualizado en 1
            if (ENCONTRADO.equals("0")){

                try{
                    listaRUCOficina = logicaPersonal.ListaRUCBoticas(RUC_DNI);
                    if (listaRUCOficina.size() > 1) {
                    int variasdire = 1;
                    for (int x = 0; x < listaRUCOficina.size(); x++) {

                        String RUCREC = listaRUCOficina.get(x).getSRVC_RUC();
                        String RAZONSOCIALREC = listaRUCOficina.get(x).getSRVC_RAZONSOCIAL();
                        String DIRECCIONREC = listaRUCOficina.get(x).getSRVC_DIRECCION();
                        int DIRECCIONFISCAL = listaRUCOficina.get(x).getSRVC_DIRECCIONFISCAL();

                        String UPDATERUC = objcliente.BUSCAREXISTERUC(RUCREC,RAZONSOCIALREC,DIRECCIONREC,DIRECCIONFISCAL,"1",variasdire);
                    }
                    listaRUCOficinaRecuperado = logicaPersonal.ListaDireccionesRecuperado(RUC_DNI);
                    FrmDireccionesClientes obj1 = null;
                    obj1 = new FrmDireccionesClientes(objetoventana, listaRUCOficinaRecuperado);
                    obj1.pack();
                    obj1.setVisible(true);

                    Direccion = obj1.getDIRE();

                    }else{
                      int variasdire = 0;
                      for (int x = 0; x < listaRUCOficina.size(); x++) {

                        String RUCREC = listaRUCOficina.get(x).getSRVC_RUC();
                        String RAZONSOCIALREC = listaRUCOficina.get(x).getSRVC_RAZONSOCIAL();
                        String DIRECCIONREC = listaRUCOficina.get(x).getSRVC_DIRECCION();
                        int DIRECCIONFISCAL = listaRUCOficina.get(x).getSRVC_DIRECCIONFISCAL();

                        String UPDATERUC = objcliente.BUSCAREXISTERUC(RUCREC,RAZONSOCIALREC,DIRECCIONREC,DIRECCIONFISCAL,"1",variasdire);
                        }
                    }
                } catch (Exception ex) {
                     System.out.println("ERROR SIN CONEXION A INTERNET :" + ex.getMessage());
                     JOptionPane.showMessageDialog(this, "LO SENTIMOS EN ESTOS MOMENTOS NO PODEMOS CONECTARNOS A OFICINA \n POR FAVOR INGRESE LOS DATOS DEL CLIENTE EN FORMA MANUAL", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }else{

                listaRUCOficinaRecuperado = logicaPersonal.ListaDireccionesRecuperado(RUC_DNI);

                if (listaRUCOficinaRecuperado.size() > 1) {
                    FrmDireccionesClientes obj1 = null;
                    obj1 = new FrmDireccionesClientes(objetoventana, listaRUCOficinaRecuperado);
                    obj1.pack();
                    obj1.setVisible(true);

                    Direccion = obj1.getDIRE();
                }
            }*/


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


            saldo = listacleintes.get(fila).getSaldo();
            DNI = listacleintes.get(fila).getDNI();
            setNombre_RazonSocial(Nombre_RazonSocial);
            setSaldo(saldo);
            setDNI(DNI);

            /*
            REALIZO MIS SET PARA AGREGAR A MIS DATOS Y RECUPERRALOS EN LA CLASE COTIZACION
             */

            setCodigoCliente(CodigoCliente);

            if (bandera == 0) {
                objcotiza.AsignaDatosCliente();
            } else if (bandera == 1) {
                objventa.AsignaDatosCliente();
            } else if (bandera == 2) {
                obcredito.AsignaDatosCliente();
            } else if (bandera == 3) {
                objrenovarCredito.AsignaDatosCliente();
            } else if (bandera == 4) {
                Objreportenotas.AsignaDatosCliente();
            } else if (bandera == 5) {
                BoleManual.Asigna_Datos_Cliente();
            }else if (bandera == 6) {
                objcotizasoat.AsignaDatosCliente();
            }else if (bandera == 7) {
                objcruce.AsignaDatosCliente();
            }else if (bandera == 8) {
                objDescCli.AsignaDatosCliente();
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

    private void NuevoUsuario() {
        NuevoCliente pe = new NuevoCliente(this, this.objetoventana);
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
            NuevoUsuario();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F4") == 0) {
            BuscaCliente();
        }
        RealizaOpciones(evt);
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
            NuevoUsuario();
        }
    RealizaOpciones(evt);
}//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    }//GEN-LAST:event_jTable1KeyReleased

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == 27) {
            dispose();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTextField1.requestFocus();
        }        
        
        RealizaOpciones(evt);
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoUsuario();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
        
            BuscaCliente();
        
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoUsuario();
        }
        
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnActualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizaActionPerformed
        // TODO add your handling code here:
        BuscaCliente();
    }//GEN-LAST:event_btnActualizaActionPerformed

    private void BuscaCliente() {
        Integer vardire = 0;
        String filtro = jTextField1.getText().trim();
        listacleintes.removeAll(listacleintes);
        int ind = this.jComboBox1.getSelectedIndex();        


        if (ind == 1) {  //RUC


            if (!String.valueOf(filtro).isEmpty()) {
                        filtro = filtro + '%';
                        //filtro = filtro;
                        
                        //String ENCONTRADO = objcliente.BUSCAREXISTERUC(filtro,"","",0,"0",0);
                        String ENCONTRADO ="1";

                        if (ENCONTRADO == null || ENCONTRADO.equals("0")){

                            try{

                                listaRUCOficina = logicaPersonal.ListaRUCBoticas(filtro);
                                if (listaRUCOficina.size() > 0) {

                                    //for (int x = 0; x < listaPersonalOficina.size(); x++) {
                                        String RUCREC = listaRUCOficina.get(0).getSRVC_RUC();
                                        String RAZONSOCIALREC = listaRUCOficina.get(0).getSRVC_RAZONSOCIAL();
                                        String DIRECCIONREC = listaRUCOficina.get(0).getSRVC_DIRECCION();
                                        int DIRECFISCALREC= listaRUCOficina.get(0).getSRVC_DIRECCIONFISCAL();

                                        if (ENCONTRADO == null){
                                            vardire = 2;
                                        }
                                        String UPDATERUC = objcliente.BUSCAREXISTERUC(RUCREC,RAZONSOCIALREC,DIRECCIONREC,DIRECFISCALREC,"1",vardire);

                                        /*Nuevamente pregunto para ver cuantas direcciones tiene*/
                                        listaRUCOficina.clear();
                                        listaRUCOficina = logicaPersonal.ListaRUCBoticas(filtro);
                                        if (listaRUCOficina.size() > 1) {

                                            int variasdire1 = 1;
                                            for (int z = 0; z < listaRUCOficina.size(); z++) {

                                                String RUCREC1 = listaRUCOficina.get(z).getSRVC_RUC();
                                                String RAZONSOCIALREC1 = listaRUCOficina.get(z).getSRVC_RAZONSOCIAL();
                                                String DIRECCIONREC1 = listaRUCOficina.get(z).getSRVC_DIRECCION();
                                                int DIRECCIONFISCAL1 = listaRUCOficina.get(z).getSRVC_DIRECCIONFISCAL();

                                                String UPDATERUC1 = objcliente.BUSCAREXISTERUC(RUCREC1,RAZONSOCIALREC1,DIRECCIONREC1,DIRECCIONFISCAL1,"1",variasdire1);
                                            }
                                        }
                                }
                               
                            } catch (Exception ex) {
                            System.out.println("ERROR SIN CONEXION A INTERNET :" + ex.getMessage());                            
                            JOptionPane.showMessageDialog(this, "LO SENTIMOS EN ESTOS MOMENTOS NO PODEMOS CONECTARNOS A OFICINA \n POR FAVOR INGRESE LOS DATOS DEL CLIENTE EN FORMA MANUAL", "Error", JOptionPane.ERROR_MESSAGE);                            
                            this.jTable1.requestFocus();
                            }

                                                        
                        }                        
                        
                        listacleintes = objcliente.ListaClientes_PORRUC(filtro);
                        LimpiatTabla();

                        for (int i = 0; i < listacleintes.size(); i++) {
                            Id_Cliente = listacleintes.get(i).getId_Cliente();
                            RUC_DNI = listacleintes.get(i).getRUC_DNI();
                            Nombre_RazonSocial = listacleintes.get(i).getNombre_RazonSocial();
                            Direccion = listacleintes.get(i).getDireccion();
                            empresa = listacleintes.get(i).getEmpresa();
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
            if (ind == 0) //RAZON SOCIAL
            {


                filtro = '%' + filtro + '%';

                listacleintes = objcliente.ListaClientes1(filtro, idbotica);
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
                if (ind == 2) //buscar por DNI
                {

                    if (!String.valueOf(filtro).isEmpty()) {
                    filtro = filtro + '%';
                    listacleintes = objcliente.ListaClientes_PORDNI(filtro);
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
                    }
                    
                } else {  //TELEFONO

                    if (!String.valueOf(filtro).isEmpty()) {
                        filtro = filtro + '%';
                        listacleintes = objcliente.ListaClientes_Telefono(filtro);
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
                    }
                }
            }
        }
          
    }

    private void RealizaOpciones(KeyEvent evt) {
        try {

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
               jComboBox1.setFocusable(true);
               jComboBox1.requestFocus(true);

            }


        } catch (Exception ex) {
            System.out.println("RealizaOpciones " + ex.getMessage());
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
    private javax.swing.JButton btnActualiza;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    // End of variables declaration//GEN-END:variables
}
