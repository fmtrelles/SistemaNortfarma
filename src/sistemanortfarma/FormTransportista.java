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
public class FormTransportista extends JDialog {

    LogicaClientes objcliente = new LogicaClientes();
    Cotizacion objcotiza;
    CruceInventarios objcruce;
    Descargos_Cli objTransportista;
    Descargos_Est objTransportista1;
    Descargos_Adicionales objTransportista2;
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
    public static String CodigoTransportista;
    public static String Nombre_Transportista;    
    public static int Id_Transportista;
    public static String RUC;
    //public static String Nombre_RazonSocial;
    //public static String Direccion;
    private static String N_Licencia;
    public static String N_Placa;
    public static String N_Contancia;
    public static String N_Marca;
    public static String N_Placa_1;
    
    String[] datosDetalle = new String[8];
    List<Clientes> listaTransportista;
    private DefaultTableModel modelClientes;
    //private static double saldo;
    private int bandera = 0;
    private static String idbotica;
    private String empresa;
    TableColumn columnaTabla = null;
    MuestraVentana objetoventana = new MuestraVentana();
    

    
    FormTransportista(Descargos_Cli obj, JFrame ventana, String idboti) {

        super(ventana, true);
        initComponents();
        bandera = 1;
        objTransportista = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jButton4.setVisible(true);
        
    }
    
    FormTransportista(Descargos_Est obj, JFrame ventana, String idboti) {

        super(ventana, true);
        initComponents();
        bandera = 2;
        objTransportista1 = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jButton4.setVisible(false);
        
    }
    
    FormTransportista(Descargos_Adicionales obj, JFrame ventana, String idboti) {

        super(ventana, true);
        initComponents();
        bandera = 3;
        objTransportista2 = obj;
        setLocationRelativeTo(null);
        modelClientes = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        AgregaTabla();
        AparienciaTabla();
        this.jTextField1.requestFocus();
        //this.jButton4.setVisible(false);
        
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
        
        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
        
        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
    }    

    public static String getN_Marca() {
        return N_Marca;
    }

    public static String getN_Placa_1() {
        return N_Placa_1;
    }

    public static void setN_Marca(String N_Marca) {
        FormTransportista.N_Marca = N_Marca;
    }

    public static void setN_Placa_1(String N_Placa_1) {
        FormTransportista.N_Placa_1 = N_Placa_1;
    }

    
    public static int getId_Transportista() {
        return Id_Transportista;
    }

    public static String getN_Licencia() {
        return N_Licencia;
    }

    public static String getN_Contancia() {
        return N_Contancia;
    }

    public static String getN_Placa() {
        return N_Placa;
    }
    

    public static void setId_Transportista(int Id_Transportista) {
        FormTransportista.Id_Transportista = Id_Transportista;
    }

    public static void setN_Licencia(String N_Licencia) {
        FormTransportista.N_Licencia = N_Licencia;
    }

    public static void setN_Placa(String N_Placa) {
        FormTransportista.N_Placa = N_Placa;
    }

    public static void setN_Contancia(String N_Contancia) {
        FormTransportista.N_Contancia = N_Contancia;
    }

        

    public static String getNombre_Transportista() {
        return Nombre_Transportista;
    }

    public static void setNombre_Transportista(String Nombre_Transportista) {
        FormTransportista.Nombre_Transportista = Nombre_Transportista;
    }

    public static String getRUC() {
        return RUC;
    }
    
    public static void setRUC(String RUC) {
        FormTransportista.RUC = RUC;
    }

    public static String getCodigoTransportista() {
        return CodigoTransportista;
    }

    public static void setCodigoTransportista(String CodigoTransportista) {
        FormTransportista.CodigoTransportista = CodigoTransportista;
    }

    

    public static String getidbotica() {
        return idbotica;
    }

    public static void setidbotica(String idbotica) {
        FormTransportista.idbotica = idbotica;
    }


    private void AgregaTabla() {

        listaTransportista = objcliente.listaTransportista("", idbotica,2);

        for (int i = 0; i < listaTransportista.size(); i++) {
            Id_Transportista = listaTransportista.get(i).getId_Transportista();
            RUC = listaTransportista.get(i).getRUC_DNI();
            Nombre_Transportista = listaTransportista.get(i).getNombre_RazonSocial();
            N_Placa = listaTransportista.get(i).getNPlaca();
            N_Placa_1 = listaTransportista.get(i).getPlaca();
            N_Marca = listaTransportista.get(i).getMarca();
            N_Licencia = listaTransportista.get(i).getLicencia();
            N_Contancia = listaTransportista.get(i).getConstancia();
            if (N_Contancia== null){
                N_Contancia = "";
            }

            datosDetalle[0] = String.valueOf(Id_Transportista);
            datosDetalle[1] = RUC;
            datosDetalle[2] = Nombre_Transportista;
            datosDetalle[3] = N_Placa;
            datosDetalle[4] = N_Licencia;
            datosDetalle[5] = N_Contancia;
            datosDetalle[6] = N_Marca;
            datosDetalle[7] = N_Placa_1;

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
        jButton5 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormTransportista.class);
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
                "Codigo", "RUC", "Razon Social", "Marca / Placa", "Licencia", "Constancia", "Marca", "Placa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jLabel1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Razon Social", "RUC", " " }));
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
                .addContainerGap(569, Short.MAX_VALUE))
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
                .addContainerGap(44, Short.MAX_VALUE))
            .addComponent(jcMousePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)))
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

    private void Recuepra_Trasportista() {
        try {

            int fila = this.jTable1.getSelectedRow();
            /*
            RECUPERO DATOS PARA AGREGAR A MI CLASE COTIZACION
             */
            CodigoTransportista = this.jTable1.getValueAt(fila, 0).toString().trim();
            N_Placa = this.jTable1.getValueAt(fila, 3).toString().trim();
            Nombre_Transportista = this.jTable1.getValueAt(fila, 2).toString();
            RUC = String.valueOf(this.jTable1.getValueAt(fila, 1));
            N_Licencia = String.valueOf(this.jTable1.getValueAt(fila, 4));
            N_Contancia = String.valueOf(this.jTable1.getValueAt(fila, 5));
            

            if (RUC != null) {
                setRUC(RUC);
            }

            /*
            REALIZO MIS SET PARA AGREGAR A MIS DATOS Y RECUPERRALOS EN LA CLASE COTIZACION
             */
                        
            setNombre_Transportista(Nombre_Transportista);
            setN_Placa(N_Placa);            
            setCodigoTransportista(CodigoTransportista);
            setN_Licencia(N_Licencia);
            setN_Contancia(N_Contancia);

            if (bandera == 1) {
                objTransportista.AsignaDatosTransportista();
            }else if (bandera == 2) {
                objTransportista1.AsignaDatosTransportista();
            }else if (bandera == 3) {
                objTransportista2.AsignaDatosTransportista();
            }

            this.hide();

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE CLIENTES CAPA VISTA METODO jTable1KeyPressed:" + ex.toString());
        }
    }

    private void Modifica_Datos_Transportista() {
        Form_ModificaTransportista objmodifica = new Form_ModificaTransportista(objetoventana, true, idbotica, Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()),String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()),String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString()), String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString()));
        objmodifica.pack();
        objmodifica.setVisible(true);
    }

    private void NuevoTransportista() {
        NuevoTransportista pe = new NuevoTransportista(this, this.objetoventana);
        pe.pack();
        pe.setVisible(true);
    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

           Recuepra_Trasportista();

        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            this.jTextField1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
            Modifica_Datos_Transportista();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoTransportista();
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
            NuevoTransportista();
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
        Recuepra_Trasportista();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jTextField1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Recuepra_Trasportista();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Modifica_Datos_Transportista();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoTransportista();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
        
            BuscaTransportista();
        
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F1") == 0) {
            NuevoTransportista();
        }
        
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void BuscaTransportista() {
        Integer vardire = 0;
        String filtro = jTextField1.getText().trim();
        listaTransportista.removeAll(listaTransportista);
        int ind = this.jComboBox1.getSelectedIndex();        


        if (ind == 1) {  //RUC


            if (!String.valueOf(filtro).isEmpty()) {
                        filtro = filtro + '%';
                        
                        String ENCONTRADO ="1";

                       
                        listaTransportista = objcliente.listaTransportista("", idbotica, ind);                        
                        LimpiatTabla();

                        for (int i = 0; i < listaTransportista.size(); i++) {
                            Id_Transportista = listaTransportista.get(i).getId_Transportista();
                            RUC = listaTransportista.get(i).getRUC_DNI();
                            Nombre_Transportista = listaTransportista.get(i).getNombre_RazonSocial();
                            //Direccion = listaTransportista.get(i).getDireccion();
                            N_Placa = listaTransportista.get(i).getNPlaca();
                            N_Licencia = listaTransportista.get(i).getLicencia();
                            N_Contancia = listaTransportista.get(i).getConstancia();

                            datosDetalle[0] = String.valueOf(Id_Transportista);
                            datosDetalle[1] = RUC;
                            datosDetalle[2] = Nombre_Transportista;
                            datosDetalle[3] = N_Placa;
                            datosDetalle[4] = N_Licencia;
                            datosDetalle[5] = N_Contancia;

                            if (datosDetalle.toString().compareTo("") != 0) {
                                modelClientes.addRow(datosDetalle);
                            }
                        }

                                           
            }
                    
            
        } else {
            if (ind == 0) //RAZON SOCIAL
            {

                if (!String.valueOf(filtro).isEmpty()) {

                filtro = '%' + filtro + '%';

                //listaTransportista = objcliente.ListaClientes1(filtro, idbotica);
                listaTransportista = objcliente.listaTransportista(filtro, idbotica,ind);
                LimpiatTabla();

                for (int i = 0; i < listaTransportista.size(); i++) {
                    Id_Transportista = listaTransportista.get(i).getId_Transportista();
                    RUC = listaTransportista.get(i).getRUC_DNI();
                    Nombre_Transportista = listaTransportista.get(i).getNombre_RazonSocial();
                    //Direccion = listaTransportista.get(i).getDireccion();
                    N_Placa = listaTransportista.get(i).getNPlaca();
                    N_Licencia = listaTransportista.get(i).getLicencia();
                    N_Contancia = listaTransportista.get(i).getConstancia();

                    datosDetalle[0] = String.valueOf(Id_Transportista);
                    datosDetalle[1] = RUC;
                    datosDetalle[2] = Nombre_Transportista;
                    datosDetalle[3] = N_Placa;
                    datosDetalle[4] = N_Licencia;
                    datosDetalle[5] = N_Contancia;

                    if (datosDetalle.toString().compareTo("") != 0) {
                        modelClientes.addRow(datosDetalle);
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    // End of variables declaration//GEN-END:variables
}
