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
import entidad.Billetes_Monedas;
import entidad.Boticas;
import entidad.Tipo_Moneda;
import java.net.URL;
import java.sql.Connection;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTable;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import CapaLogica.LogicaProforma;
import entidad.Venta;
import java.awt.event.KeyEvent;
import CapaLogica.LogicaConexion;
import CapaLogica.LogicaAbonoCajaData;
import CapaLogica.LogicaBilleteMoneda;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import entidad.Abono_Caja;
import java.text.SimpleDateFormat;

/**
 *
 * @author Miguel Gomez S.
 */
public class Reporte_Arqueo_CajaDetallado extends javax.swing.JInternalFrame implements Runnable {

    AplicacionVentas obj;
    String idbotica;
    int idsesion = 0;
    Date fechaInicio = null;
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    private static String Nomcaja;
    List<Venta> listaDetalleCaja = new ArrayList<Venta>();
    List<Venta> listaArqueoCaja = new ArrayList<Venta>();
    List<Venta> RecuperaDatosArqueoCaja = new ArrayList<Venta>();
    OpcionesReportes obreporte;
    LogicaPersonal logperso = new LogicaPersonal();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    List<Personal> Lista_Supervisores = new ArrayList<Personal>();
    LogicaProforma onjproforma = new LogicaProforma();
    private int podecimal = 2;
    int pasad = 0;
    private String usuario;
    double tipoCambio;
    int codigo;
    boolean escogio = false;
    int fila;
    int idcajero = 0;
    int idsuper = 0;
    int verificaArqueo = 0;    
    String Nrendicion;
    String idsupervisor;
    String idarqueo;

    int idcaja;
    Thread showThread;
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con;
    String report_file;
    JasperViewer view;
    Venta obj1;
    Venta obj2;
    Cajas obj3;
    private Date fechaFin;
    private URL archivo;
    VerificaSistema objsistema;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    double deposito_dolares = 0, depositado = 0;
    //Object[] mis_abonos = new Object[4];
    private DefaultTableModel Tabla_Abonos;
    LogicaVenta objVentas = new LogicaVenta();
    List<TipoCambio> misTiposCambios;
    LogicaCajas logcajas = new LogicaCajas();
    int turno;
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    JFrame ventana;
    LogicaConexion logconex = new LogicaConexion();

    LogicaVenta logVenta = new LogicaVenta();
    LogicaAbonoCajaData logAbono;
    LogicaBilleteMoneda logBilletes = new LogicaBilleteMoneda();
    Object[] mis_abonos = new Object[9];
    Object[] mis_abonos_billetes = new Object[9];
    private DefaultTableModel modelo;
    private DefaultTableModel modelo_1;
    TableColumn columnaTabla = null;
    Cajas micaja;
    Reporte_Caja_Detallado objeto;

    
    public Reporte_Arqueo_CajaDetallado(AplicacionVentas objventa) {

        if (con == null) {
            con = logconex.RetornaConexion();
        }
        initComponents();
        //con = objventa.getCon();        
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        idsesion = obj.getId_personal_botica();
        jTextField1.setText(idbotica);
//        Tabla_Abonos = (DefaultTableModel) jTable1.getModel();
        OcultaLabel(false);
        CargarCajeros();
        CargarSupervisores();
        pasad++;
        Habilita_Abono(false);
        Oculta_Abono(false);
        Lista_Turnos();
        this.jTextField4.requestFocus();
        Reporte_Caja_Detallado objeto;
        

        jTable1.setDefaultRenderer(Object.class, new IconCellRenderer());
        jTable2.setDefaultRenderer(Object.class, new IconCellRenderer());
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo_1 = (DefaultTableModel) jTable2.getModel();
        AparienciaTabla();
        //MuestraMonto();
        
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tleft1 = new DefaultTableCellRenderer();
        tleft1.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tleft1);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tleft1);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(tleft1);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(tleft1);
        DefaultTableCellRenderer tleft2 = new DefaultTableCellRenderer();
        tleft2.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tleft2);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(tleft1);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        jTable1.setColumnSelectionAllowed(true);
        jTable2.setColumnSelectionAllowed(true);

    }

    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox4.addItem("-- Seleccione Turno --");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
        }

    }

    private void Muestra_Tipos_Cambios() {
        List<TipoCambio> misTiposCambios = logVenta.Retorna_Tipos_Cambios();

        for (int i = 0; i < misTiposCambios.size(); i++) {
            this.txtTipCambio.setText(String.valueOf(misTiposCambios.get(i).getTipoCambio()));
            tipoCambio = misTiposCambios.get(i).getTipoCambio();
        }
    }
    
    private void Lista_Billetes() {

        try {
            obj3 = new Cajas(idbotica, idcaja,turno,idcajero,idsuper);
            List<Abono_Caja> miLista = logBilletes.ListaBilletes1(obj3, fechaInicio);
            jTable1.setRowHeight(50);
            jTable2.setRowHeight(50);

            if (miLista.size() > 0) {
                try {

                    for (Abono_Caja billete : miLista) {
                        if (billete.getBiilete().getTipomoneda().getTipo_Moneda() == 1) {

                            ImageIcon icon = new ImageIcon(getClass().getResource(billete.getBiilete().getRuta()));
                            JLabel precio = new JLabel(billete.getBiilete().getTipomoneda().getSimbolo() + " " + String.valueOf(billete.getBiilete().getValor()));
                            precio.setHorizontalAlignment(JLabel.CENTER);
                            precio.setFont(new Font("Tahoma", Font.BOLD, 16));
                            precio.setForeground(Color.ORANGE);
                            precio.setToolTipText("Monto de la Moneda");

                            mis_abonos[0] = new JLabel(icon);
                            mis_abonos[1] = precio;
                            mis_abonos[2] = billete.getBiilete().getTipomoneda().getTipo_Moneda();
                            mis_abonos[3] = billete.getBiilete().getValor();
                            mis_abonos[4] = billete.getCantidad();
                            mis_abonos[5] = billete.getTotal();
                            mis_abonos[6] = billete.getBiilete().getId_Billete();
                            mis_abonos[7] = billete.getBiilete().getRuta();
                            mis_abonos[8] = billete.getBiilete().getTipomoneda().getSimbolo();
                            modelo.addRow(mis_abonos);
                        } else {

                            ImageIcon icon = new ImageIcon(getClass().getResource(billete.getBiilete().getRuta()));
                            JLabel precio = new JLabel(billete.getBiilete().getTipomoneda().getSimbolo() + " " + String.valueOf(billete.getBiilete().getValor()));
                            precio.setHorizontalAlignment(JLabel.CENTER);
                            precio.setFont(new Font("Tahoma", Font.BOLD, 16));
                            precio.setForeground(Color.ORANGE);
                            precio.setToolTipText("Monto de la Moneda");

                            mis_abonos_billetes[0] = new JLabel(icon);
                            mis_abonos_billetes[1] = precio;
                            mis_abonos_billetes[2] = billete.getBiilete().getTipomoneda().getTipo_Moneda();
                            mis_abonos_billetes[3] = billete.getBiilete().getValor();
                            mis_abonos_billetes[4] = billete.getCantidad();
                            mis_abonos_billetes[5] = billete.getTotal();
                            mis_abonos_billetes[6] = billete.getBiilete().getId_Billete();
                            mis_abonos_billetes[7] = billete.getBiilete().getRuta();
                            mis_abonos_billetes[8] = billete.getBiilete().getTipomoneda().getSimbolo();
                            modelo_1.addRow(mis_abonos_billetes);
                        }
                    }

//                    CalculaTotal();

                } catch (Exception ex) {
                    System.out.println("ERROR : " + ex.toString());
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
    private void Oculta_Abono(boolean valor) {
    }

    private void Habilita_Abono(boolean valor) {
        if (valor) {

            Muestra_Tipos_Cambios();
            Oculta_Abono(true);

        } else {

            Oculta_Abono(false);
        }

    }

    public void run() {

        try {
            int supervisor = Lista_Supervisores.get(this.jComboBox5.getSelectedIndex()).getId_PersonalSuper();
            Nrendicion = this.jTextField4.getText();
            
            String sistema = "Windows";

            parameters.put("FECHAFIN", jXDatePicker1.getDate());
            parameters.put("FECHAINICIO", jXDatePicker1.getDate());
            parameters.put("INIDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
            parameters.put("INIDBOTICA", idbotica);
            parameters.put("INIDCAJERO", idcajero);
            parameters.put("TURNO", turno);
            parameters.put("ARQUEO", idarqueo);
            parameters.put("REPORT_LOCALE", new Locale("es", "ES"));
            parameters.put("REPORT_CONNECTION", con);

            archivo = this.getClass().getResource("/Reportes/REPORTE_ARQUEO_CAJA.jasper");

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
            view.setSize(870, 600);

        } catch (JRException e) {
            System.out.println(e.getMessage());
            OcultaLabel(false);
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }

        jXDatePicker1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox4.setEnabled(true);
        OcultaLabel(false);
    }

    private void OcultaLabel(boolean valor) {
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

    private void CargarSupervisores(){
        Lista_Supervisores = logperso.Lista_Supervisores(idbotica,idsesion);

            for (int i = 0; i < Lista_Supervisores.size(); i++) {
                jComboBox5.addItem(Lista_Supervisores.get(i).getApellidoSuper());
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        buttonSeven2 = new org.edisoncor.gui.button.ButtonSeven();
        buttonSeven4 = new org.edisoncor.gui.button.ButtonSeven();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtTipCambio = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMontoSis = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel8 = new javax.swing.JLabel();
        txtIngresado = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel10 = new javax.swing.JLabel();
        txtDiferencia = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        buttonSeven3 = new org.edisoncor.gui.button.ButtonSeven();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Reporte_Arqueo_CajaDetallado.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jXDatePicker1.setFont(resourceMap.getFont("jXDatePicker1.font")); // NOI18N
        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(362, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---  Seleccione  Caja  ---" }));
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

        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        buttonSeven2.setBackground(resourceMap.getColor("buttonSeven2.background")); // NOI18N
        buttonSeven2.setText(resourceMap.getString("buttonSeven2.text")); // NOI18N
        buttonSeven2.setFont(resourceMap.getFont("buttonSeven2.font")); // NOI18N
        buttonSeven2.setName("buttonSeven2"); // NOI18N
        buttonSeven2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven2ActionPerformed(evt);
            }
        });

        buttonSeven4.setBackground(resourceMap.getColor("buttonSeven4.background")); // NOI18N
        buttonSeven4.setText(resourceMap.getString("buttonSeven4.text")); // NOI18N
        buttonSeven4.setFont(resourceMap.getFont("buttonSeven4.font")); // NOI18N
        buttonSeven4.setName("buttonSeven4"); // NOI18N
        buttonSeven4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, 0, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSeven4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setForeground(resourceMap.getColor("jTable1.foreground")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Billete", "Moneda", "TIPO", "VALOR", "Cantidad", "Total", "ID", "RUTA", "SIMBOL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setForeground(resourceMap.getColor("jTable2.foreground")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Billete", "Moneda", "TIPO", "VALOR", "CANTIDAD", "TOTAL", "ID", "RUTA", "SIMBOL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        txtTipCambio.setEditable(false);
        txtTipCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipCambio.setText(resourceMap.getString("txtTipCambio.text")); // NOI18N
        txtTipCambio.setFont(resourceMap.getFont("txtTipCambio.font")); // NOI18N
        txtTipCambio.setName("txtTipCambio"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtMontoSis.setBackground(resourceMap.getColor("txtMontoSis.background")); // NOI18N
        txtMontoSis.setForeground(resourceMap.getColor("txtMontoSis.foreground")); // NOI18N
        txtMontoSis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoSis.setText(resourceMap.getString("txtMontoSis.text")); // NOI18N
        txtMontoSis.setFont(resourceMap.getFont("txtMontoSis.font")); // NOI18N
        txtMontoSis.setName("txtMontoSis"); // NOI18N
        txtMontoSis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoSisKeyTyped(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtIngresado.setBackground(resourceMap.getColor("txtIngresado.background")); // NOI18N
        txtIngresado.setEditable(false);
        txtIngresado.setForeground(resourceMap.getColor("txtIngresado.foreground")); // NOI18N
        txtIngresado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIngresado.setText(resourceMap.getString("txtIngresado.text")); // NOI18N
        txtIngresado.setFont(resourceMap.getFont("txtIngresado.font")); // NOI18N
        txtIngresado.setName("txtIngresado"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtDiferencia.setBackground(resourceMap.getColor("txtDiferencia.background")); // NOI18N
        txtDiferencia.setEditable(false);
        txtDiferencia.setForeground(resourceMap.getColor("txtDiferencia.foreground")); // NOI18N
        txtDiferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiferencia.setText(resourceMap.getString("txtDiferencia.text")); // NOI18N
        txtDiferencia.setFont(resourceMap.getFont("txtDiferencia.font")); // NOI18N
        txtDiferencia.setName("txtDiferencia"); // NOI18N

        buttonSeven3.setBackground(resourceMap.getColor("buttonSeven3.background")); // NOI18N
        buttonSeven3.setText(resourceMap.getString("buttonSeven3.text")); // NOI18N
        buttonSeven3.setFont(resourceMap.getFont("buttonSeven3.font")); // NOI18N
        buttonSeven3.setName("buttonSeven3"); // NOI18N
        buttonSeven3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, 0, 888, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(187, 187, 187)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMontoSis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(txtIngresado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMontoSis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion >= 0) {
            if (pasad > 0) {
                codigo = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

}//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int poscion = this.jComboBox1.getSelectedIndex();
        if (poscion > 0) {
            poscion--;
            escogio = true;
            Nomcaja = String.valueOf(this.jComboBox1.getSelectedItem());
            idcaja = listacajas.get(poscion).getIdcaja();
        }
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void valida(){

        this.jXDatePicker1.setEnabled(true);
        this.jComboBox2.setEnabled(true);
        this.jComboBox1.setEnabled(true);        
        this.jComboBox4.setEnabled(true);
    }

    public static double Redondear(double numero,int digitos)
    {     
        BigDecimal bd1 = new BigDecimal(numero);
        bd1 = bd1.setScale(digitos, BigDecimal.ROUND_HALF_UP);
        numero = bd1.doubleValue();
        return numero;
    }

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        
        /*if (evt.getStateChange() == 2) {
            LimpiatTabla();
            jComboBox4.removeAllItems();
            Lista_Turnos();
        }*/

        

    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        if (this.jTextField4.getText().length()>=6){
        evt.consume();
        }

        int k=(int)evt.getKeyChar();
        if (k >= 97 && k <= 122 || k>=65 && k<=90){
        evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
        if(k==241 || k==209){
        evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
        if(k==10){
        jTextField4.transferFocus();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        fila = this.jTable1.getSelectedRow();
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

}//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            CalculaIngreso(0);
        }
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        fila = this.jTable2.getSelectedRow();
}//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            CalculaIngreso(1);
        }
}//GEN-LAST:event_jTable2KeyTyped

    private void buttonSeven3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven3ActionPerformed
        try{

        String monto = this.txtMontoSis.getText();
        if (monto.equals("")|| monto.trim().length()<=0 || monto.isEmpty() || monto.trim().equals(null)|| monto.trim()==null){
            monto = "0.00";
            this.txtMontoSis.setText("0.00");
        }

        double msistema = Double.parseDouble(monto);
        String Rendicion = this.jTextField4.getText();

        if (Rendicion.equals("") || Rendicion.length()<=0 || Rendicion.trim().equals(null)|| Rendicion.trim()==null){
           JOptionPane.showMessageDialog(this, "No ha Ingresado Nº de Rendición ","NORTFARMA",JOptionPane.ERROR_MESSAGE);
           this.jTextField4.requestFocus();
        }else{
            if (msistema > 0){
                Guardar_Abono();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Debe Ingresar El monto del Sistema","NORTFARMA",JOptionPane.ERROR_MESSAGE);
                this.txtMontoSis.setText("0.00");
                this.txtMontoSis.requestFocus();
            }
        }
        }catch(Exception ex){
            System.out.printf("Error: " + ex);

        }
}//GEN-LAST:event_buttonSeven3ActionPerformed

    private void Guardar_Abono() {

        try {

            logAbono = new LogicaAbonoCajaData();
            List<Abono_Caja> listaTotal = new ArrayList<Abono_Caja>();
            Abono_Caja abonoCaja;
            Boticas botica = new Boticas();
            Cajas caja = new Cajas();
            Turno turno = new Turno();
            Personal persona = new Personal();
            Billetes_Monedas billeteMoneda;
            Tipo_Moneda tipoMoneda;
            //obj3 = new Cajas(idbotica, idcaja,turno,idcajero,idsuper);
            fechaInicio = this.jXDatePicker1.getDate();
            Date fechaVta = fechaInicio;
            SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
            String FechaVtaS = formatoVta.format(fechaVta);

            abonoCaja = new Abono_Caja();
            botica.setId_Botica(obj3.getIdbotica());
            caja.setIdcaja(obj3.getIdcaja());
            turno.setId_Turno(obj3.getMiturno());
            persona.setId_personal_Caja(obj3.getIdcajero());
            abonoCaja.setBotica(botica);
            abonoCaja.setPersona(persona);
            abonoCaja.setCaja(caja);
            abonoCaja.setFECHA(FechaVtaS);
            abonoCaja.setTurno(turno);
            listaTotal.add(abonoCaja);

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object var = jTable1.getValueAt(i, 5);
                if (Double.parseDouble(var.toString().trim()) > 0.0) {
                    abonoCaja = new Abono_Caja();
                    tipoMoneda = new Tipo_Moneda();
                    billeteMoneda = new Billetes_Monedas();
                    billeteMoneda.setValor(Double.parseDouble(jTable1.getValueAt(i, 3).toString().trim()));
                    tipoMoneda.setTipo_Moneda(Integer.parseInt(jTable1.getValueAt(i, 2).toString().trim()));
                    tipoMoneda.setSimbolo(jTable1.getValueAt(i, 8).toString().trim());
                    billeteMoneda.setId_Billete(Integer.parseInt(jTable1.getValueAt(i, 6).toString().trim()));
                    billeteMoneda.setRuta((jTable1.getValueAt(i, 7).toString().trim()));
                    billeteMoneda.setTipomoneda(tipoMoneda);
                    abonoCaja.setBiilete(billeteMoneda);
                    abonoCaja.setCantidad(Integer.parseInt(jTable1.getValueAt(i, 4).toString().trim()));
                    abonoCaja.setTotal(Double.parseDouble(jTable1.getValueAt(i, 5).toString().trim()));
                    listaTotal.add(abonoCaja);
                }
            }

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                Object var = jTable2.getValueAt(i, 5);
                if (Double.parseDouble(var.toString().trim()) > 0.0) {
                    abonoCaja = new Abono_Caja();
                    billeteMoneda = new Billetes_Monedas();
                    tipoMoneda = new Tipo_Moneda();
                    billeteMoneda.setValor(Double.parseDouble(jTable2.getValueAt(i, 3).toString().trim()));
                    tipoMoneda.setTipo_Moneda(Integer.parseInt(jTable2.getValueAt(i, 2).toString().trim()));
                    tipoMoneda.setSimbolo(jTable2.getValueAt(i, 8).toString().trim());
                    billeteMoneda.setId_Billete(Integer.parseInt(jTable2.getValueAt(i, 6).toString().trim()));
                    billeteMoneda.setRuta((jTable2.getValueAt(i, 7).toString().trim()));
                    billeteMoneda.setTipomoneda(tipoMoneda);
                    abonoCaja.setBiilete(billeteMoneda);
                    abonoCaja.setCantidad(Integer.parseInt(jTable2.getValueAt(i, 4).toString().trim()));
                    abonoCaja.setTotal(Double.parseDouble(jTable2.getValueAt(i, 5).toString().trim()));
                    listaTotal.add(abonoCaja);
                }
            }

            if (listaTotal.size() > 1) {
               
                boolean Estado = true;               
                if (Estado) {
                    fechaInicio = this.jXDatePicker1.getDate();
                    String Nren = this.jTextField4.getText();
                    int supervisor = Lista_Supervisores.get(this.jComboBox5.getSelectedIndex()).getId_PersonalSuper();


                    int reul = logAbono.Guardar_AbonoArqueo(listaTotal,Nren,supervisor);

                    if (reul == 1) {
                        JOptionPane.showMessageDialog(null, " SU ABONO SE HA REGISTRADO CORRECTAMENTE ", "Ingresado", JOptionPane.INFORMATION_MESSAGE);
                        
                        GeneraReporteEfectivo1();
                        obreporte.entra = true;
                        obj.marcacdo = false;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, " ERROR AL INGRESAR SU ABONO \n PORAFAVOR COMINIQUESE CON INFORMATICA", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, " USTED NO HA INGRESADO NINGUN MONTO", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void GeneraReporteEfectivo1() {

        if (Verifica_Datos()) {

            try {

                String sistema = "Windows";
                parameters.put("MIFECHA", jXDatePicker1.getDate());
                parameters.put("IDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
                parameters.put("IDBOTICA", idbotica);
                parameters.put("IDPERSONAL", idcajero);
                parameters.put("TURNO", turno);
                parameters.put("REPORT_CONNECTION", con);

                archivo = this.getClass().getResource("/Reportes/REPORTE_ENTREGA_EFECTIVOARQUEO.jasper");

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
    private void buttonSeven2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (p == JOptionPane.YES_OPTION) {
            
            obreporte.entra = true;
            obj.marcacdo = false;
            dispose();
        }
}//GEN-LAST:event_buttonSeven2ActionPerformed

    private void txtMontoSisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoSisKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            CalculaIngreso(0);
        }
    }//GEN-LAST:event_txtMontoSisKeyTyped

    private void buttonSeven4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven4ActionPerformed
        // TODO add your handling code here:
        try {
            if (Verifica_Datos()) {
                if (Verifica_Informacion()) {

                    obj1 = new Venta(idbotica, idcaja,turno,idcajero,idsuper,fechaInicio);
                    listaDetalleCaja = logperso.Lista_VerificaDatosArqueo(obj1);
                    if (listaDetalleCaja.size() > 0) {

                        verificaArqueo = Integer.parseInt(listaDetalleCaja.get(0).getresultado());
                    }

                    if (verificaArqueo == 1){

                        int opcion = JOptionPane.showConfirmDialog(null, "YA EXISTE UN ARQUEO GUARDADO PARA ESTA CAJA Y TURNO?\nDESEA MODIFICARLO", "NORTFARMA", JOptionPane.YES_NO_OPTION);
                        if(opcion == JOptionPane.YES_OPTION){
                            Recuperadatos(1);
                        }

                    }else{

                        Recuperadatos(0);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_buttonSeven4ActionPerformed

    private void Recuperadatos(int opc){

        fechaInicio = this.jXDatePicker1.getDate();
        if (escogio) {
            //OcultaLabel(true);
            this.jXDatePicker1.setEnabled(false);
            this.jComboBox2.setEnabled(false);
            this.jComboBox1.setEnabled(false);           
            this.jComboBox4.setEnabled(false);

            if (opc == 1){
                modificar();
            }
            else
            {
                calcular();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ocultadatos(){

        this.jXDatePicker1.getDate();
        if (escogio) {            
            //OcultaLabel(true);
            this.jXDatePicker1.setEnabled(true);
            this.jComboBox2.setEnabled(true);
            this.jComboBox1.setEnabled(true);
            this.jComboBox4.setEnabled(true);
            this.jTextField4.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcular(){

       Lista_Billetes();
//        Muestra_Datos();
        Muestra_Tipos_Cambios();
    }

    private void modificar(){

    Lista_Billetes();
//        Muestra_Datos();
    Muestra_Tipos_Cambios();
    }

    private void CalculaIngreso(int tabla) {

        int cantIngresada;
        int TipoMoneda;
        double valorBillete;

        try {

            if (fila >= 0) {

                if (tabla == 0) {
                    TipoMoneda = Integer.parseInt(jTable1.getValueAt(fila, 2).toString());
                    cantIngresada = Integer.parseInt(jTable1.getValueAt(fila, 4).toString());
                    valorBillete = Double.parseDouble(jTable1.getValueAt(fila, 3).toString());
                } else {
                    TipoMoneda = Integer.parseInt(jTable2.getValueAt(fila, 2).toString());
                    cantIngresada = Integer.parseInt(jTable2.getValueAt(fila, 4).toString());
                    valorBillete = Double.parseDouble(jTable2.getValueAt(fila, 3).toString());
                }

                if (TipoMoneda <= 2) {
                    double monto = cantIngresada * valorBillete;
                    BigDecimal bd = new BigDecimal(monto);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    monto = bd.doubleValue();

                    if (tabla == 0) {
                        modelo.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    } else {
                        modelo_1.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    }


                } else {
                    double monto = (cantIngresada * valorBillete) * tipoCambio;
                    BigDecimal bd = new BigDecimal(monto);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    monto = bd.doubleValue();
                    if (tabla == 0) {
                        modelo.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    } else {
                        modelo_1.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    }
                }

                CalculaTotal();

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void CalculaTotal() {
        double suma = 0.0;

        for (int pos = 0; pos < jTable1.getRowCount(); pos++) {
            Object var = jTable1.getValueAt(pos, 5);
            if (var != null) {
                double valsuma = Double.parseDouble(jTable1.getValueAt(pos, 5).toString());
                suma = suma + valsuma;
            }
        }

        for (int pos = 0; pos < jTable2.getRowCount(); pos++) {
            Object var = jTable2.getValueAt(pos, 5);
            if (var != null) {
                double valsuma = Double.parseDouble(jTable2.getValueAt(pos, 5).toString());
                suma = suma + valsuma;
            }
        }

        BigDecimal bd = new BigDecimal(suma);
        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        txtIngresado.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

        Double diferencia = suma - Double.parseDouble(txtMontoSis.getText().trim());
        BigDecimal bd1 = new BigDecimal(diferencia);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        txtDiferencia.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

        if (diferencia < 0) {
            txtDiferencia.setBackground(Color.red);
            txtDiferencia.setForeground(Color.WHITE);
        } else {
            Color col = new Color(9, 133, 215);
            txtDiferencia.setBackground(col);
            txtDiferencia.setForeground(Color.WHITE);

        }
    }
    private boolean Verifica_Informacion() {
        boolean valor = true;
        Cajas objcaja = new Cajas();
        objcaja.setIdbotica(idbotica);
        objcaja.setIdcaja(idcaja);
        objcaja.setIdturno(turno);
        objcaja.setIdcajero(idcajero);
        objcaja.setIdSuper(idsuper);
        java.util.Date date1 = jXDatePicker1.getDate();
        objcaja.setMiFecha(new java.sql.Date(date1.getTime()));

        int resul = logcajas.Consulta_Caja(objcaja);

        if (resul == 2) {
            JOptionPane.showMessageDialog(null, " No Existe Informacion de este Turno ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
//            jCheckBox1.setSelected(false);
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

        /*if (jComboBox5.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione Supervisor ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else*/
        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox4.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Lista_Cajeros.size() > 0) {
                idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            }else if (Lista_Supervisores.size() > 0) {
                idsuper = Lista_Supervisores.get(this.jComboBox5.getSelectedIndex()).getId_PersonalSuper();
            }

            fechaInicio = this.jXDatePicker1.getDate();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();
            turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
            return true;
        }

        return false;

    }

    private void EjecutaReporteCaja() {
        fechaInicio = this.jXDatePicker1.getDate();
        if (escogio) {
            showThread = new Thread(this);
            showThread.start();
            OcultaLabel(true);
            this.jXDatePicker1.setEnabled(false);
            this.jComboBox2.setEnabled(false);
            this.jComboBox1.setEnabled(false);
            this.jComboBox4.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class IconCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {


            /*************************************/
            //  setForeground(Color.black);
            if (value instanceof JLabel) {
                JLabel label = (JLabel) value;
                label.setOpaque(true);

                if (column == 0 || column == 1) { //EL NUMERO DE LA COLUMNA QUE DESEAMOS PINTAR
                    fillColor(new Color(236, 233, 216), table, label, isSelected);
                } else if (column == 3) {
                    fillColor(new Color(217, 216, 216), table, label, isSelected);
                }

                return label;

            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }

        public void fillColor(Color color, JTable t, JLabel l, boolean isSelected) {

            if (isSelected) {
                l.setBackground(t.getBackground());
                l.setForeground(t.getForeground());
            } else {
                l.setBackground(color);
                l.setForeground(t.getForeground());
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonSeven2;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven3;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtDiferencia;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtIngresado;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtMontoSis;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtTipCambio;
    // End of variables declaration//GEN-END:variables
}
