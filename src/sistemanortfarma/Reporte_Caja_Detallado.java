package sistemanortfarma;

import CapaLogica.LogicaCajas;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import CapaLogica.LogicaVenta;
import entidad.Cajas;
import entidad.Personal;
import entidad.TipoCambio;
import entidad.Turno;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import CapaLogica.LogicaProforma;

/**
 *
 * @author Miguel Gomez S.
 */
public class Reporte_Caja_Detallado extends javax.swing.JInternalFrame implements Runnable {

    AplicacionVentas obj;
    String idbotica;
    int idsesion = 0;
    Date fechaInicio = null;
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    private static String Nomcaja;
    OpcionesReportes obreporte;
    LogicaPersonal logperso = new LogicaPersonal();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    LogicaProforma onjproforma = new LogicaProforma();
    int pasad = 0;
    private String usuario;
    int codigo;
    boolean escogio = false;
    int idcajero = 0;
    int idcaja;
    Thread showThread;
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con;
    String report_file;
    JasperViewer view;
    private Date fechaFin;
    private URL archivo;
    VerificaSistema objsistema;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    double deposito_dolares = 0, depositado = 0;
    Object[] mis_abonos = new Object[4];
    private DefaultTableModel Tabla_Abonos;
    LogicaVenta objVentas = new LogicaVenta();
    List<TipoCambio> misTiposCambios;
    LogicaCajas logcajas = new LogicaCajas();
    int turno;
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    JFrame ventana;

    /** Creates new form Reporte_Caja_Detallado */
    public Reporte_Caja_Detallado(OpcionesReportes objeto) {
        initComponents();
        con = objeto.getCon();
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        idsesion = obj.getId_personal_botica();
        jTextField1.setText(idbotica);
        Tabla_Abonos = (DefaultTableModel) jTable1.getModel();
        OcultaLabel(false);
        CargarCajeros();
        pasad++;
        Habilita_Abono(false);
        Oculta_Abono(false);
        jPanel2.setVisible(false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        Lista_Turnos();

    }

    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox4.addItem("---- Seleccione Turno ----");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
        }

    }

    private void Muestra_Tipos_Cambios() {
        misTiposCambios = objVentas.Retorna_Tipos_Cambios();

        for (int i = 0; i < misTiposCambios.size(); i++) {
            this.jComboBox3.addItem(misTiposCambios.get(i).getMoneda());

        }
    }

    private void Oculta_Abono(boolean valor) {
        this.jPanel2.setVisible(valor);
        this.jTable1.setVisible(valor);
        this.jButton6.setVisible(valor);
    }

    private void Habilita_Abono(boolean valor) {
        if (valor) {

            Muestra_Tipos_Cambios();
            Oculta_Abono(true);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setSelectedIndex(1);
            this.jTextField2.setVisible(valor);
            this.jButton4.setVisible(valor);
            this.jTextField2.requestFocus();
            this.jButton4.setEnabled(valor);

        } else {

            Oculta_Abono(false);
            jTabbedPane1.setEnabledAt(0, true);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setSelectedIndex(0);
            this.jButton4.setEnabled(valor);
            this.jTextField2.setText("");
            this.jTextField2.setVisible(valor);
            this.jButton4.setVisible(valor);
        }

    }

    public void run() {

        try {

            String sistema = "Windows";

            parameters.put("FECHAFIN", jXDatePicker1.getDate());
            parameters.put("FECHAINICIO", jXDatePicker1.getDate());
            parameters.put("INIDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
            parameters.put("INIDBOTICA", idbotica);
            parameters.put("INIDCAJERO", idcajero);
            parameters.put("TURNO", turno);
            parameters.put("REPORT_LOCALE", new Locale("es", "ES"));
            parameters.put("REPORT_CONNECTION", con);

            archivo = this.getClass().getResource("/Reportes/REPORTE_RENDICION_CAJA.jasper");

            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            view = new JasperViewer(jasperPrint, false);
            view.setTitle("Reporte Detallado de Caja");
            view.setVisible(true);
            view.setSize(870, 700);

        } catch (JRException e) {
            System.out.println(e.getMessage());
            OcultaLabel(false);
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }
        jXDatePicker1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox1.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jComboBox4.setEnabled(true);
        OcultaLabel(false);
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel7.setVisible(valor);
        this.jLabel8.setVisible(valor);
    }

    private void CargarCajeros() {
        int opc = 2;

        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }
        int rol = new login().getId_rol();

        int rolacceso = onjproforma.VerificaRolAcceso(rol,opc);

        //if (rol == 12 || rol == 16 || rol == 20) {
        if (rolacceso == 1) {
            Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

            for (int i = 0; i < Lista_Cajeros.size(); i++) {
                jComboBox2.addItem(Lista_Cajeros.get(i).getApellido());
            }

        } else {
            jComboBox2.addItem(usuario);
            idcajero = obj.getIdpesonal();
        }


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Reporte_Caja_Detallado.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------  Seleccione su Caja  ---------" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jComboBox4.setName("jComboBox4"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(330, 330, 330))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)))
                    .addComponent(jButton2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setFocusable(false);
        jPanel4.setName("jPanel4"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setFocusable(false);
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(resourceMap.getString("jTextField2.toolTipText")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(49, 20));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jComboBox3.setName("jComboBox3"); // NOI18N

        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(resourceMap.getString("jTextField3.toolTipText")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(93, 20));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Moneda", "T. Cambio", "Monto", "Total (S./)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setToolTipText(resourceMap.getString("jButton6.toolTipText")); // NOI18N
        jButton6.setEnabled(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(293, 293, 293))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(179, 179, 179))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion > 0) {
            poscion--;
            escogio = true;
            Nomcaja = String.valueOf(this.jComboBox1.getSelectedItem());
            idcaja = listacajas.get(poscion).getIdcaja();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion >= 0) {
            if (pasad > 0) {
                codigo = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        obreporte.entra = true;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (Verifica_Datos()) {
                if (Verifica_Informacion()) {
                    EjecutaReporteCaja();
                }
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        fechaInicio = this.jXDatePicker1.getDate();

        if (fechaInicio.compareTo(logfecha.Retorna_Fecha_Cuadre(idbotica, idcaja)) == 0) {
            if (jCheckBox1.isSelected()) {
                if (Verifica_Datos()) {

                    Cajas objcaja = new Cajas();
                    objcaja.setBotica(idbotica);
                    objcaja.setIdbotica(idbotica);
                    objcaja.setIdcaja(listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
                    objcaja.setFecha(logfecha.ConvierteFecha(this.jXDatePicker1.getDate()));
                    objcaja.setTurno(turno);
                    objcaja.setIdpersonal(idsesion);

                    if (!logcajas.Verifica_Caja_Cerrada(objcaja)) {
                        JOptionPane.showMessageDialog(null, " Porfavor Para Ingresar tu Abono Debes de Cerrar Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
                        jCheckBox1.setSelected(false);
                    } else {

                        if (Verifica_Informacion()) {
                            IngresoAbonoCaja abono = new IngresoAbonoCaja(ventana, this, objcaja, jComboBox2.getSelectedItem());
                            abono.pack();
                            abono.setVisible(true);
                            jCheckBox1.setSelected(false);
                        }
                    }
                } else {
                    jCheckBox1.setSelected(false);
                }

            } else {
                Habilita_Abono(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, " NO PUEDES INGRESAR UN ABONO EN ESTA FECHA ", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.jCheckBox1.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private boolean Verifica_Informacion() {
        boolean valor = true;
        Cajas objcaja = new Cajas();
        objcaja.setIdbotica(idbotica);
        objcaja.setIdcaja(idcaja);
        objcaja.setIdturno(turno);
        objcaja.setIdcajero(idcajero);
        java.util.Date date1 = jXDatePicker1.getDate();
        objcaja.setMiFecha(new java.sql.Date(date1.getTime()));

        int resul = logcajas.Consulta_Caja(objcaja);

        if (resul == 2) {
            JOptionPane.showMessageDialog(null, " No Existe Informacion de este Turno ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            jCheckBox1.setSelected(false);
            valor = false;
        }

        return valor;
    }

    public void GeneraReporteEfectivo() {

        if (Verifica_Datos()) {

            try {

                String sistema = "Windows";
                parameters.put("MIFECHA", jXDatePicker1.getDate());
                parameters.put("IDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
                parameters.put("IDBOTICA", idbotica);
                parameters.put("IDPERSONAL", idcajero);
                parameters.put("TURNO", turno);
                parameters.put("REPORT_CONNECTION", con);

                archivo = this.getClass().getResource("/Reportes/REPORTE_ENTREGA_EFECTIVO.jasper");

                if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                view = new JasperViewer(jasperPrint, false);
                view.setTitle("DETALLE DE ENTREGA DE EFECTIVO");
                view.setVisible(true);
                view.setSize(870, 700);

            } catch (JRException e) {
                System.out.println(e.getMessage());
                OcultaLabel(false);
                JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void LimpiatTabla() {
        int cant = Tabla_Abonos.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                Tabla_Abonos.removeRow(i);
            }
        }
    }

    private boolean Verifica_Datos() {

        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox4.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Lista_Cajeros.size() > 0) {
                idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }

            fechaInicio = this.jXDatePicker1.getDate();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();
            turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
            return true;
        }

        return false;

    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed


        if (jTable1.getRowCount() > 0) {

            LogicaConfiguracion objConfig = new LogicaConfiguracion();

            int reply = JOptionPane.showConfirmDialog(null, " Desea Guardar su Abono ? ", "NORTFARMA", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; i < this.jTable1.getRowCount(); i++) {
                    if (i == 0) {
                        depositado = Double.parseDouble(this.jTable1.getValueAt(i, 2).toString());
                    } else {
                        deposito_dolares = Double.parseDouble(this.jTable1.getValueAt(i, 2).toString());
                    }
                }
            }

            try {

                idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();

                if (Lista_Cajeros.size() > 0) {
                    idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
                }

                turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();

                Cajas objeto = new Cajas(idcaja, idbotica, idcajero, logfecha.RetornaFecha().toString(), depositado, deposito_dolares, turno);
                boolean resul = objConfig.Ingresa_Abono_Caja(objeto);

                if (resul) {
                    JOptionPane.showMessageDialog(null, " ABONO INGRESADO CORRECTAMENTE ", "Ingresado", JOptionPane.INFORMATION_MESSAGE);
                    Habilita_Abono(false);
                    this.jCheckBox1.setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(null, " ERROR AL INGRESAR SU ABONO  ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, " Lo Sentimos \n Debe de Ingresar su Abono ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (this.jTextField3.getText().trim().compareTo("") != 0) {

            deposito_dolares = Double.parseDouble(this.jTextField3.getText().trim());
            mis_abonos[0] = misTiposCambios.get(jComboBox3.getSelectedIndex()).getMoneda();
            mis_abonos[1] = misTiposCambios.get(jComboBox3.getSelectedIndex()).getTipoCambio();
            mis_abonos[2] = jTextField3.getText().trim();
            double mitotal = (Double.parseDouble(jTextField3.getText().trim()) * misTiposCambios.get(jComboBox3.getSelectedIndex()).getTipoCambio());
            BigDecimal bd = new BigDecimal(mitotal);
            mis_abonos[3] = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            Tabla_Abonos.addRow(mis_abonos);
            jTextField3.setText("");

        } else {
            JOptionPane.showMessageDialog(null, " Porfavor Ingrese Primero su Abono en Dolares ", "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.jTextField2.getText().trim().compareTo("") != 0) {

            depositado = Double.parseDouble(this.jTextField2.getText().trim());
            mis_abonos[0] = "Soles";
            mis_abonos[1] = "";
            mis_abonos[2] = depositado;
            mis_abonos[3] = depositado;
            Tabla_Abonos.addRow(mis_abonos);
            jTextField2.setText("");
            this.jTextField3.requestFocus();
            jButton6.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, " Porfavor Ingrese Primero su Abono en S./  ", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        this.jTextField2.setText("");
        jTextField3.setText("");
        LimpiatTabla();
        jCheckBox1.setSelected(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void EjecutaReporteCaja() {
        fechaInicio = this.jXDatePicker1.getDate();
        if (escogio) {
            showThread = new Thread(this);
            showThread.start();
            OcultaLabel(true);
            this.jXDatePicker1.setEnabled(false);
            this.jComboBox2.setEnabled(false);
            this.jComboBox1.setEnabled(false);
            this.jButton1.setEnabled(false);
            this.jButton2.setEnabled(false);
            this.jComboBox4.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables
}
