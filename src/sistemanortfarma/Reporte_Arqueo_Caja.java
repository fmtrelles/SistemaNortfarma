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
import entidad.Venta;
import java.awt.event.KeyEvent;
import CapaLogica.LogicaConexion;

/**
 *
 * @author Miguel Gomez S.
 */
public class Reporte_Arqueo_Caja extends javax.swing.JInternalFrame implements Runnable {

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
    int codigo;
    boolean escogio = false;
    int idcajero = 0;
    int idsuper = 0;
    int verificaArqueo = 0;
    double SI = 0;
    double VN = 0;
    double VT = 0;
    double VNC = 0;
    double CP = 0;
    double CT = 0;
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
    LogicaConexion logconex = new LogicaConexion();

    /** Creates new form Reporte_Caja_Detallado */
    public Reporte_Arqueo_Caja(OpcionesReportes objeto) {
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        initComponents();
        //con = objeto.getCon();        
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
        this.jPanel5.setVisible(false);
        this.jPanel2.setVisible(false);
    }

    public Reporte_Arqueo_Caja(AplicacionVentas objventa) {

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
        this.jPanel5.setVisible(false);
        this.jPanel2.setVisible(false);
    }

    
    private void Lista_Turnos() {        
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox4.addItem("-- Seleccione Turno --");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
        }

    }

    private void Muestra_Tipos_Cambios() {
        misTiposCambios = objVentas.Retorna_Tipos_Cambios();

        for (int i = 0; i < misTiposCambios.size(); i++) {
//            this.jComboBox3.addItem(misTiposCambios.get(i).getMoneda());

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
            Double Sinicial = Double.parseDouble(this.jTextField5.getText());
            Double TotVNetas = Double.parseDouble(this.jTextField6.getText());
            Double TotVTarj = Double.parseDouble(this.jTextField7.getText());
            Double TotVNC = Double.parseDouble(this.jTextField8.getText());
            Double TotCPersonal = Double.parseDouble(this.jTextField17.getText());
            Double TotCTercero = Double.parseDouble(this.jTextField18.getText());
            Double TotEfecVtas = Double.parseDouble(this.jTextField9.getText());
            Double TotDep = Double.parseDouble(this.jTextField16.getText());
            Double TotPServ = Double.parseDouble(this.jTextField20.getText());
            Double TotPCta = Double.parseDouble(this.jTextField21.getText());
            Double TotRetiros = Double.parseDouble(this.jTextField19.getText());
            Double TotAgente = Double.parseDouble(this.jTextField22.getText());
            Double TotEfectivo = Double.parseDouble(this.jTextField10.getText());
            Double TotEfecEOfi = Double.parseDouble(this.jTextField11.getText());
            Double TotMtoDeposito = Double.parseDouble(this.jTextField13.getText());
            Double TotAbono = Double.parseDouble(this.jTextField15.getText());
            Double TotDifCaja = Double.parseDouble(this.jTextField14.getText());
            Double TotOGastos = Double.parseDouble(this.jTextField23.getText());
            Double TotOIngresos = Double.parseDouble(this.jTextField25.getText());
            Double TotPCliente = Double.parseDouble(this.jTextField24.getText());
            String observaciones = this.jTextArea1.getText();

             obj2 = new Venta(idbotica, idcaja,turno,idcajero,supervisor,fechaInicio,Nrendicion,Sinicial,TotVNetas,TotVTarj,TotVNC,
            TotCPersonal,TotCTercero,TotEfecVtas,TotDep,TotPServ,TotPCta,TotRetiros,TotAgente,TotEfectivo,TotEfecEOfi,
            TotMtoDeposito,TotAbono,TotDifCaja,TotOGastos,TotOIngresos,TotPCliente,observaciones);
            listaArqueoCaja = logperso.Lista_InsertaDatosArqueo(obj2);

            if (listaArqueoCaja.size() > 0) {
              idarqueo = String.valueOf(listaArqueoCaja.get(0).getSaldoApertura());
           }

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
        buttonSeven5.setEnabled(true);
        buttonSeven4.setEnabled(true);
        jComboBox4.setEnabled(true);
        OcultaLabel(false);
    }

    private void OcultaLabel(boolean valor) {
//        this.jLabel7.setVisible(valor);
//        this.jLabel8.setVisible(valor);
    }

    private void CargarCajeros() {
        int opc = 2;        
        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }
        int rol = new login().getId_rol();

        int rolacceso = onjproforma.VerificaRolAcceso(rol,opc);
     
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
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        buttonSeven3 = new org.edisoncor.gui.button.ButtonSeven();
        buttonSeven5 = new org.edisoncor.gui.button.ButtonSeven();
        buttonSeven6 = new org.edisoncor.gui.button.ButtonSeven();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Reporte_Arqueo_Caja.class);
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--  Seleccione su Caja  --" }));
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(buttonSeven4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setName("jTextField7"); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setEditable(false);
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText(resourceMap.getString("jTextField9.text")); // NOI18N
        jTextField9.setName("jTextField9"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jTextField10.setBackground(resourceMap.getColor("jTextField10.background")); // NOI18N
        jTextField10.setEditable(false);
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setName("jTextField10"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jTextField12.setBackground(resourceMap.getColor("jTextField12.background")); // NOI18N
        jTextField12.setEditable(false);
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField12.setName("jTextField12"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jTextField13.setBackground(resourceMap.getColor("jTextField13.background")); // NOI18N
        jTextField13.setEditable(false);
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setName("jTextField13"); // NOI18N

        jLabel23.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel23.setForeground(resourceMap.getColor("jLabel23.foreground")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jTextField14.setBackground(resourceMap.getColor("jTextField14.background")); // NOI18N
        jTextField14.setEditable(false);
        jTextField14.setForeground(resourceMap.getColor("jTextField14.foreground")); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField14.setName("jTextField14"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.setName("jTextField15"); // NOI18N
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField17.setName("jTextField17"); // NOI18N
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField18.setName("jTextField18"); // NOI18N
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setName("jTextField16"); // NOI18N
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });

        jLabel28.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField19.setName("jTextField19"); // NOI18N
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField19KeyTyped(evt);
            }
        });

        jLabel30.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField20.setName("jTextField20"); // NOI18N
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField20KeyTyped(evt);
            }
        });

        jLabel31.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField21.setName("jTextField21"); // NOI18N
        jTextField21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField21KeyTyped(evt);
            }
        });

        jLabel25.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel25.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jTextField22.setBackground(resourceMap.getColor("jTextField22.background")); // NOI18N
        jTextField22.setEditable(false);
        jTextField22.setForeground(resourceMap.getColor("jTextField22.foreground")); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField22.setName("jTextField22"); // NOI18N

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField23.setName("jTextField23"); // NOI18N
        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField23KeyTyped(evt);
            }
        });

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField24.setName("jTextField24"); // NOI18N
        jTextField24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField24KeyTyped(evt);
            }
        });

        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        jTextField25.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField25.setName("jTextField25"); // NOI18N
        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField25KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel26)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(135, 135, 135)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel35))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField24)
                            .addComponent(jTextField25)
                            .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                    .addComponent(jLabel18)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel34))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField9)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addGap(380, 380, 380)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(44, 44, 44)))
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel19)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel21)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setName("jPanel2"); // NOI18N

        buttonSeven3.setBackground(resourceMap.getColor("buttonSeven3.background")); // NOI18N
        buttonSeven3.setText(resourceMap.getString("buttonSeven3.text")); // NOI18N
        buttonSeven3.setFont(resourceMap.getFont("buttonSeven3.font")); // NOI18N
        buttonSeven3.setName("buttonSeven3"); // NOI18N
        buttonSeven3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven3ActionPerformed(evt);
            }
        });

        buttonSeven5.setBackground(resourceMap.getColor("buttonSeven5.background")); // NOI18N
        buttonSeven5.setText(resourceMap.getString("buttonSeven5.text")); // NOI18N
        buttonSeven5.setFont(resourceMap.getFont("buttonSeven5.font")); // NOI18N
        buttonSeven5.setName("buttonSeven5"); // NOI18N
        buttonSeven5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven5ActionPerformed(evt);
            }
        });

        buttonSeven6.setBackground(resourceMap.getColor("buttonSeven6.background")); // NOI18N
        buttonSeven6.setText(resourceMap.getString("buttonSeven6.text")); // NOI18N
        buttonSeven6.setActionCommand(resourceMap.getString("buttonSeven6.actionCommand")); // NOI18N
        buttonSeven6.setFont(resourceMap.getFont("buttonSeven6.font")); // NOI18N
        buttonSeven6.setName("buttonSeven6"); // NOI18N
        buttonSeven6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(buttonSeven5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(buttonSeven6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.buttonSeven5.setEnabled(true);
        this.buttonSeven4.setEnabled(true);
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

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        // TODO add your handling code here:
        int k=(int)evt.getKeyChar();
        if (k >= 97 && k <= 122 || k>=65 && k<=90){
        evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        jTextField15.setText("0.00");
        }
        if(k==241 || k==209){
        evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        jTextField15.setText("0.00");
        }
        if(k==10){
        jTextField15.transferFocus();
        }
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped

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
        jTextField6.transferFocus();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        // TODO add your handling code here:
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
        jTextField7.transferFocus();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TODO add your handling code here:
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
        jTextField8.transferFocus();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyTyped
        // TODO add your handling code here:
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
        jTextField17.transferFocus();
        }
    }//GEN-LAST:event_jTextField17KeyTyped

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        // TODO add your handling code here:
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
        jTextField18.transferFocus();
        }
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
        // TODO add your handling code here:
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
        jTextField16.transferFocus();
        }
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jTextField20KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyTyped
        // TODO add your handling code here:
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
        jTextField20.transferFocus();
        }
    }//GEN-LAST:event_jTextField20KeyTyped

    private void jTextField21KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyTyped
        // TODO add your handling code here:
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
        jTextField21.transferFocus();
        }
    }//GEN-LAST:event_jTextField21KeyTyped

    private void jTextField19KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyTyped
        // TODO add your handling code here:
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
        jTextField19.transferFocus();
        }
    }//GEN-LAST:event_jTextField19KeyTyped

    private void jTextField23KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyTyped
        // TODO add your handling code here:
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
        jTextField23.transferFocus();
        }
    }//GEN-LAST:event_jTextField23KeyTyped

    private void jTextField24KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField24KeyTyped
        // TODO add your handling code here:
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
        jTextField24.transferFocus();
        }
    }//GEN-LAST:event_jTextField24KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        // TODO add your handling code here:
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
        jTextField11.transferFocus();
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void buttonSeven2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (p == JOptionPane.YES_OPTION) {

            obreporte.entra = true;
            obj.marcacdo = false;
            dispose();

        }
}//GEN-LAST:event_buttonSeven2ActionPerformed

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

    private void buttonSeven3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven3ActionPerformed
        // TODO add your handling code here:

         String Ab = this.jTextField15.getText() ;
        String tvn_val = this.jTextField6.getText();
        String tvt_val = this.jTextField7.getText();
        String tvnc_val = this.jTextField8.getText();
        String tvcp_val = this.jTextField17.getText();
        String tvct_val = this.jTextField18.getText();

        String tvdep_val = this.jTextField16.getText();
        String tvps_val = this.jTextField20.getText();
        String tvpcta_val = this.jTextField21.getText();
        String tvret_val = this.jTextField19.getText();
        String tvog_val = this.jTextField23.getText();
        String tvoi_val = this.jTextField25.getText();
        String tvpcli_val = this.jTextField24.getText();
        String tvefenof_val = this.jTextField11.getText();


        if (Ab.equals("")|| Ab.trim().length()<=0 || Ab.isEmpty() || Ab.trim().equals(null)|| Ab.trim()==null ){ Ab = "0.0"; this.jTextField6.setText("0.0");}
        if (tvn_val.equals("")|| tvn_val.trim().length()<=0 || tvn_val.isEmpty() || tvn_val.trim().equals(null)|| tvn_val.trim()==null ){ this.jTextField6.setText("0.0");}
        if (tvt_val.equals("")|| tvt_val.trim().length()<=0 || tvt_val.isEmpty() || tvt_val.trim().equals(null)|| tvt_val.trim()==null ){ this.jTextField7.setText("0.0");}
        if (tvnc_val.equals("")|| tvnc_val.trim().length()<=0 || tvnc_val.isEmpty() || tvnc_val.trim().equals(null)|| tvnc_val.trim()==null ){ this.jTextField8.setText("0.0");}
        if (tvcp_val.equals("")|| tvcp_val.trim().length()<=0 || tvcp_val.isEmpty() || tvcp_val.trim().equals(null)|| tvcp_val.trim()==null ){ this.jTextField17.setText("0.0");}
        if (tvct_val.equals("")|| tvct_val.trim().length()<=0 || tvct_val.isEmpty() || tvct_val.trim().equals(null)|| tvct_val.trim()==null ){ this.jTextField18.setText("0.0");}
        if (tvdep_val.equals("")|| tvdep_val.trim().length()<=0 || tvdep_val.isEmpty() || tvdep_val.trim().equals(null)|| tvdep_val.trim()==null ){ this.jTextField16.setText("0.0");}
        if (tvps_val.equals("")|| tvps_val.trim().length()<=0 || tvps_val.isEmpty() || tvps_val.trim().equals(null)|| tvps_val.trim()==null ){ this.jTextField20.setText("0.0");}
        if (tvpcta_val.equals("")|| tvpcta_val.trim().length()<=0 || tvpcta_val.isEmpty() || tvpcta_val.trim().equals(null)|| tvpcta_val.trim()==null ){ this.jTextField21.setText("0.0");}
        if (tvret_val.equals("")|| tvret_val.trim().length()<=0 || tvret_val.isEmpty() || tvret_val.trim().equals(null)|| tvret_val.trim()==null ){ this.jTextField19.setText("0.0");}
        if (tvog_val.equals("")|| tvog_val.trim().length()<=0 || tvog_val.isEmpty() || tvog_val.trim().equals(null)|| tvog_val.trim()==null ){ this.jTextField23.setText("0.0");}
        if (tvoi_val.equals("")|| tvoi_val.trim().length()<=0 || tvoi_val.isEmpty() || tvoi_val.trim().equals(null)|| tvoi_val.trim()==null ){ this.jTextField25.setText("0.0");}
        if (tvpcli_val.equals("")|| tvpcli_val.trim().length()<=0 || tvpcli_val.isEmpty() || tvpcli_val.trim().equals(null)|| tvpcli_val.trim()==null ){ this.jTextField24.setText("0.0");}
        if (tvefenof_val.equals("")|| tvefenof_val.trim().length()<=0 || tvefenof_val.isEmpty() || tvefenof_val.trim().equals(null)|| tvefenof_val.trim()==null ){ this.jTextField11.setText("0.0");}


        double ABO = Double.parseDouble(Ab);

        if (ABO <= 0) {

            JOptionPane.showMessageDialog(this, "Debe Ingresar un Abono","NORTFARMA",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            double SI = Double.parseDouble(this.jTextField5.getText());
            double VN = Double.parseDouble(this.jTextField6.getText());
            double VT = Double.parseDouble(this.jTextField7.getText());
            double VNC = Double.parseDouble(this.jTextField8.getText());
            double CP = Double.parseDouble(this.jTextField17.getText());
            double CT = Double.parseDouble(this.jTextField18.getText());

            double D = Double.parseDouble(this.jTextField16.getText());
            double R = Double.parseDouble(this.jTextField19.getText());
            double PS = Double.parseDouble(this.jTextField20.getText());
            double PC = Double.parseDouble(this.jTextField21.getText());

            double OG = Double.parseDouble(this.jTextField23.getText());
            double OI = Double.parseDouble(this.jTextField25.getText());
            double PCLI = Double.parseDouble(this.jTextField24.getText());

            double TE = Double.parseDouble(this.jTextField10.getText());
            double EO = Double.parseDouble(this.jTextField11.getText());
            double SI1 = Double.parseDouble(this.jTextField12.getText());
            double A = Double.parseDouble(this.jTextField15.getText());


            double EFECVTAS = (SI + VN) - VT - VNC - CP - CT;
            this.jTextField9.setText(String.valueOf(Redondear(EFECVTAS,2)));


            double TAGENTE = (D + PS + PC) - R;
            this.jTextField22.setText(String.valueOf(Redondear(TAGENTE,2)));

            double TOTEFEC = EFECVTAS + TAGENTE + OI - OG;
            this.jTextField10.setText(String.valueOf(Redondear(TOTEFEC,2)));

            double MTODEP = TOTEFEC - EO - SI1;
            this.jTextField13.setText(String.valueOf(Redondear(MTODEP,2)));

            double DIFCAJA = A - MTODEP;
            this.jTextField14.setText(String.valueOf(Redondear(DIFCAJA,2)));

            valida();
        }
    }//GEN-LAST:event_buttonSeven3ActionPerformed

    private void buttonSeven5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven5ActionPerformed
        // TODO add your handling code here:
        try {
            if (Verifica_Datos()) {
                if (Verifica_Informacion()) {
                    String Rendicion = this.jTextField4.getText();
                    String tvnval = this.jTextField6.getText();
                    if (tvnval.equals("")|| tvnval.trim().length()<=0 || tvnval.isEmpty() || tvnval.trim().equals(null)|| tvnval.trim()==null ){ this.jTextField6.setText("0.00");}


                    if (Rendicion.equals("") || Rendicion.length()<0 || Rendicion.trim().equals(null)|| Rendicion.trim()==null){
                        JOptionPane.showMessageDialog(this, "No ha Ingresado Nº de Rendición ","NORTFARMA",JOptionPane.ERROR_MESSAGE);
                        this.jTextField4.requestFocus();
                    }else{

                        EjecutaReporteCaja();
                    }
                }
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_buttonSeven5ActionPerformed

    private void buttonSeven6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven6ActionPerformed

        ocultadatos();
       /* jComboBox2.removeAllItems();
        jComboBox4.removeAllItems();
        jComboBox1.removeAllItems();
        CargarCajeros();
        CargarSupervisores();
        Lista_Turnos();*/
    }//GEN-LAST:event_buttonSeven6ActionPerformed

    private void jTextField25KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyTyped
        // TODO add your handling code here:
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
        jTextField25.transferFocus();
        }
    }//GEN-LAST:event_jTextField25KeyTyped

    private void Recuperadatos(int opc){

        fechaInicio = this.jXDatePicker1.getDate();
        if (escogio) {
            //OcultaLabel(true);
            this.jXDatePicker1.setEnabled(false);
            this.jComboBox2.setEnabled(false);
            this.jComboBox1.setEnabled(false);
            this.buttonSeven5.setEnabled(false);
            this.buttonSeven4.setEnabled(false);
            this.jComboBox4.setEnabled(false);
            this.jPanel5.setVisible(true);
            this.jPanel2.setVisible(true);

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
            this.buttonSeven5.setEnabled(true);
            this.buttonSeven4.setEnabled(true);
            this.jComboBox4.setEnabled(true);
            this.jPanel5.setVisible(false);
            this.jPanel2.setVisible(false);
            this.buttonSeven4.setEnabled(true);
            this.jTextField5.setText("");
            this.jTextField12.setText("");
            this.jTextField4.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcular(){

        
           obj1 = new Venta(idbotica, idcaja,turno,idcajero,idsuper,fechaInicio);
           listaDetalleCaja = logperso.Lista_DatosVenta(obj1);

           if (listaDetalleCaja.size() > 0) {
              this.jTextField5.setText(String.valueOf(listaDetalleCaja.get(0).getSaldoApertura()));
              this.jTextField12.setText(String.valueOf(listaDetalleCaja.get(0).getSaldoApertura()));
           }
           
           this.jTextField6.setText("0.00");
           this.jTextField7.setText("0.00");
           this.jTextField8.setText("0.00");
           this.jTextField9.setText("0.00");
           this.jTextField17.setText("0.00");
           this.jTextField18.setText("0.00");
           this.jTextField16.setText("0.00");
           this.jTextField19.setText("0.00");
           this.jTextField20.setText("0.00");
           this.jTextField21.setText("0.00");
           this.jTextField22.setText("0.00");

           this.jTextField10.setText("0.00");
           this.jTextField11.setText("0.00");
           this.jTextField13.setText("0.00");
           this.jTextField15.setText("0.00");

           this.jTextField14.setText("0.00");

           this.jTextField23.setText("0.00");
           this.jTextField24.setText("0.00");
           this.jTextField25.setText("0.00");

    }

    private void modificar(){


           obj1 = new Venta(idbotica, idcaja,turno,idcajero,idsuper,fechaInicio);
           RecuperaDatosArqueoCaja = logperso.Recupera_DatosArqueoCaja(obj1);

           if (RecuperaDatosArqueoCaja.size() > 0) {
              this.jTextField4.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getNrendicion()));
              this.jTextField5.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getSinicial()));
              this.jTextField6.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotVNetas()));
              this.jTextField7.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotVTarj()));
              this.jTextField8.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotVNC()));
              this.jTextField17.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotCPersonal()));
              this.jTextField18.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotCTercero()));
              this.jTextField9.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotEfecVtas()));
              this.jTextField16.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotDep()));
              this.jTextField20.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotPServ()));
              this.jTextField21.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotPCta()));
              this.jTextField19.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotRetiros()));
              this.jTextField22.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotAgente()));
              this.jTextField23.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotOGastos()));
              this.jTextField24.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotPCliente()));
              this.jTextField25.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotOIngresos()));
              this.jTextField10.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotEfectivo()));
              this.jTextField11.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotEfecEOfi()));
              this.jTextField12.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getSinicial()));
              this.jTextField13.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotMtoDeposito()));
              this.jTextField15.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotAbono()));
              this.jTextField14.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getTotDifCaja()));
              this.jTextArea1.setText(String.valueOf(RecuperaDatosArqueoCaja.get(0).getobservaciones()));
              
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
            this.buttonSeven5.setEnabled(false);
            this.buttonSeven4.setEnabled(false);
            this.jComboBox4.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor debe de Seleccionar su Caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonSeven2;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven3;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven4;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven5;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables
}
