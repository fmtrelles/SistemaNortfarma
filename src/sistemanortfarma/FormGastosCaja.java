package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCajas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import entidad.Cajas;
import entidad.Personal;
import entidad.Turno;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import entidad.Boticas;


/**
 *
 * @author Miguel Gomez S.
 */
public class FormGastosCaja extends javax.swing.JInternalFrame {

    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    String idbotica;
    int idcajero = 0;
    Date fechaInicio = null;
    Date fechaFin = null;
    int codigo;
    LogicaPersonal logperso = new LogicaPersonal();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    AplicacionVentas obj;
    FormClientesGastos objcliente;
    private String usuario;
    LogicaFechaHora logfecja = new LogicaFechaHora();
    LogicaCajas logcajas = new LogicaCajas();
    private int idpersonal;
    String origenMov = "";
    String destinoMov = "";
    String idperMov = "";
    String JCB_SubMotivo="";
    String IdPersonal;
    private int idcaja;
    List<Cajas> doc_cajas = new ArrayList<Cajas>();
    List<Cajas> doc_motivos = new ArrayList<Cajas>();
    List<Cajas> docSub_motivos = new ArrayList<Cajas>();
    List<Cajas> Lista_Retencion = new ArrayList<Cajas>();
    double ImporteRetenido;
    List<Boticas> listaboticas2 = new ArrayList<Boticas>();
    List<Boticas> listaboticas = new ArrayList<Boticas>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaPersonal objPersonal = new LogicaPersonal();
    double MontoPago;
    private boolean esretencion = false;
    LogicaBoticas logbotica = new LogicaBoticas();
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    private String SerieNC, NumeroNC, IdVentaNC;
    JFrame objetoventana;
    FormPersonal formpersonal;
//    FormClientes objcliente;

    /** Creates new form FormGastosCaja */
    public FormGastosCaja(AplicacionVentas ob) {
        initComponents();
        obj = ob;
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        idpersonal = obj.getId_personal_botica();
        fechaInicio = logfecja.RetornaFecha();
        this.jLabel2.setText("Botica Felicidad " + idbotica);
        this.jLabel6.setText(logfecja.ConvierteFecha(fechaInicio));
        this.jLabel12.setText(usuario);
        Deshabilita(false);
        CargarCajeros();
        Cargar_Documentos();
        Cargar_Motivos();
        Cargar_SubMotivos();
        Lista_Turnos();        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        this.jButton1.setEnabled(false);
        this.jTextField8.setVisible(false);
        
        this.txtOrigen.setEnabled(false);
        this.txtDestino.setEnabled(false);
        this.txtIdPersonal.setVisible(false);
        this.txtPersonal.setEnabled(false);
        this.jButton7.setEnabled(false);
        this.txtOrigen.setText("");
        this.txtDestino.setText("");
        this.txtIdPersonal.setText("");
        this.txtPersonal.setText("");
        this.jComboBox6.setEnabled(false);
    }

    
    private void Deshabilita(boolean valor) {
        this.jComboBox1.setEnabled(valor);
        this.jComboBox2.setEnabled(valor);
        this.jComboBox3.setEnabled(valor);
        this.jComboBox4.setEnabled(valor);
        this.jComboBox5.setEnabled(valor);
        this.jComboBox6.setEnabled(valor);

        this.jTextArea1.setEnabled(valor);
        this.jTextField1.setEnabled(valor);
        this.jTextField2.setEnabled(valor);
        this.jTextField5.setEnabled(valor);        

        this.jButton5.setEnabled(valor);
        this.jButton6.setEnabled(valor);
        //this.lasboticas1.setEnabled(valor);
        //this.lasboticas2.setEnabled(valor);
    }

    private void CargarCajeros() {
        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }

        int rol = new login().getId_rol();

        if (rol == 12 || rol == 16 || rol == 20) {
            Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

            for (int i = 0; i < Lista_Cajeros.size(); i++) {
                jComboBox2.addItem(Lista_Cajeros.get(i).getApellido());
            }
        } else {
            jComboBox2.addItem(usuario);
            idcajero = obj.getIdpesonal();
        }
    }

   
    private void Cargar_Documentos() {

        doc_cajas = logcajas.Lista_Documen_Gastos();

        for (int i = 0; i < doc_cajas.size(); i++) {
            String doc = doc_cajas.get(i).getDescripGasto() + "   |      " + doc_cajas.get(i).getCaracter();
            this.jComboBox3.addItem(doc);
        }
    }

    private void Cargar_Motivos(){

        doc_motivos = logcajas.Lista_Documen_Motivo();

        for (int i = 0; i < doc_motivos.size(); i++) {
            String doc = doc_motivos.get(i).getDescripMotivo();
            this.jComboBox5.addItem(doc);
        }

    }
    
    private void Cargar_SubMotivos(){

        docSub_motivos = logcajas.Lista_Documen_SubMotivo();

        for (int i = 0; i < docSub_motivos.size(); i++) {
            String doc = docSub_motivos.get(i).getDescripSubMotivo();
            this.jComboBox6.addItem(doc);
        }

    }


    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
        }
    }

    private String[] ArrayBoticas = {};
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtPersonal = new javax.swing.JTextField();
        txtIdPersonal = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormGastosCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(780, 579));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---  Seleccione su Caja  ---" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jComboBox4.setName("jComboBox4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(580, 254));

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jTextField6.setFont(resourceMap.getFont("jTextField7.font")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N

        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField7.setFont(resourceMap.getFont("jTextField7.font")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField7.setEnabled(false);
        jTextField7.setName("jTextField7"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setName("jTextField8"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

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

        jButton6.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(76, 19));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        txtOrigen.setFont(resourceMap.getFont("txtDestino.font")); // NOI18N
        txtOrigen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtOrigen.setName("txtOrigen"); // NOI18N

        txtDestino.setFont(resourceMap.getFont("txtDestino.font")); // NOI18N
        txtDestino.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDestino.setName("txtDestino"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jButton7.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtPersonal.setEditable(false);
        txtPersonal.setFont(resourceMap.getFont("txtPersonal.font")); // NOI18N
        txtPersonal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonal.setName("txtPersonal"); // NOI18N

        txtIdPersonal.setEditable(false);
        txtIdPersonal.setFont(resourceMap.getFont("txtIdPersonal.font")); // NOI18N
        txtIdPersonal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdPersonal.setName("txtIdPersonal"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jComboBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox6MousePressed(evt);
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jButton6.getAccessibleContext().setAccessibleName(resourceMap.getString("jButton6.AccessibleContext.accessibleName")); // NOI18N

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jTextField4.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField5)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2363, 2363, 2363))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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
                .addGap(155, 155, 155)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jcMousePanel1Layout.setVerticalGroup(
            jcMousePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcMousePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 160));

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel21))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jcMousePanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcMousePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Deshabilita(true);
        this.jButton4.setEnabled(true);
        this.jButton3.setEnabled(false);
        this.txtOrigen.setText("");
        this.txtDestino.setText("");
        this.txtIdPersonal.setText("");
        this.txtPersonal.setText("");
        int mot = doc_motivos.get(jComboBox5.getSelectedIndex()).getIdmotivo();

        if (mot == 1){
            this.txtOrigen.setEnabled(true);
            this.txtDestino.setEnabled(true);
            this.txtIdPersonal.setEnabled(true);
            this.txtPersonal.setEnabled(true);
            this.jButton7.setEnabled(true);
        }else{
            this.txtOrigen.setEnabled(false);
            this.txtDestino.setEnabled(false);
            this.txtIdPersonal.setEnabled(false);
            this.txtPersonal.setEnabled(false);
            this.jButton7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " DESEAS SALIR ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            obj.marcacdo = false;
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int motivo = doc_motivos.get(jComboBox5.getSelectedIndex()).getIdmotivo();
        String IdSubmotivo = docSub_motivos.get(jComboBox6.getSelectedIndex()).getIdSubmotivo();
        String Submotivo = docSub_motivos.get(jComboBox6.getSelectedIndex()).getDescripSubMotivo();
        String origen = this.txtOrigen.getText().trim().toUpperCase();
        String destino = this.txtDestino.getText().trim().toUpperCase();
        String idpersona = this.txtIdPersonal.getText();
        
        if (motivo == 1 && (IdSubmotivo.equals("0"))){
                JOptionPane.showMessageDialog(this, " ESCOGER SUBMOTIVO DE LA MOVILIDAD ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                this.jComboBox6.requestFocus();
        }else if (motivo == 1 && (origen.equals("") || destino.equals(""))){
                JOptionPane.showMessageDialog(this, " ESCOGER ORIGEN Y DESTINO DE LA MOVILIDAD ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }else{
            if (jTextField1.getText().trim().compareTo("") != 0 && jTextField2.getText().trim().compareTo("") != 0) {
                if (jTextField8.getText().trim().compareTo("") == 0){
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE RAZON SOCIAL DE PROVEEDOR ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    this.jButton6.requestFocus();
                    return;
                }else{
                idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();
                int idturno = Lista_Turnos.get(jComboBox4.getSelectedIndex()).getId_Turno();

                if (Lista_Cajeros.size() > 0) {
                    idcajero = Lista_Cajeros.get(jComboBox2.getSelectedIndex()).getId_Personal();
                }

                Cajas entidad = new Cajas(idbotica, idcaja, idturno, idcajero);
                int resul = logbotica.Verifica_Seleccion_Turno(entidad);

                if (resul == 0) {

                    int p = JOptionPane.showConfirmDialog(null, " DESEA GUARDAR ESTE GASTO ", "Confirmar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (p == JOptionPane.YES_OPTION) {
                        if (esretencion) {
                            if (jTextField3.getText().trim().compareTo("") != 0 && jTextField4.getText().trim().compareTo("") != 0) {
                                Guardar_Gasto();
                            } else {
                                JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE SERIE Y NUMERO DE LA RETENCION ", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            Guardar_Gasto();
                        }
                    }

                } else if (resul == -1) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS " + obj.getUsuario() + " \n USTED NO HA APERTURADO " + jComboBox1.getSelectedItem() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (resul == 1) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS " + obj.getUsuario() + " \n USTED NO HA APERTURADO EN EL TURNO " + jComboBox4.getSelectedItem() + "  ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            }else
            {
                JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE TODOS LOS DATOS ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void Verifica_Es_Retencion() {

        int retencion = doc_cajas.get(jComboBox3.getSelectedIndex()).getRetencion();

        try {


            if (retencion > 0) {

                Lista_Retencion = logcajas.Devuelve_Data_Retencion(retencion);
                double retenido = Lista_Retencion.get(0).getPorcen_Retencion();
                double mitotal = Double.parseDouble(jTextField2.getText().trim());

                if (mitotal > Lista_Retencion.get(0).getMonto_Retencion()) {

                    int p = JOptionPane.showConfirmDialog(null, " Desea Aplicar la Retencion a este Documento?", "Confirmar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (p == JOptionPane.YES_OPTION) {

                        esretencion = true;
                        MontoPago = mitotal;
                        jTabbedPane1.setEnabledAt(1, true);
                        jTabbedPane1.setSelectedIndex(1);
                        this.jTextField3.requestFocus();

                        ImporteRetenido = (mitotal * (retenido / 100)) * -1;

                        BigDecimal bd = new BigDecimal(ImporteRetenido);
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                        ImporteRetenido = bd.doubleValue();

                        jTextField5.setText(String.valueOf(ImporteRetenido));

                    }
                }
            }

        } catch (Exception ex) {
        }

    }

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        jTextArea1.requestFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        muestra_Personal();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new FormClientesGastos(this, objetoventana, idbotica).show(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
        int idmot = doc_motivos.get(jComboBox5.getSelectedIndex()).getIdmotivo();
        if (idmot == 1){
            this.txtOrigen.setText("");
            this.txtDestino.setText("");
            this.txtIdPersonal.setText("");
            this.txtPersonal.setText("");
            this.txtOrigen.setEnabled(true);
            this.txtDestino.setEnabled(true);
            this.txtIdPersonal.setEnabled(true);
            this.txtPersonal.setEnabled(true);
            this.jButton7.setEnabled(true);
            this.jComboBox6.setEnabled(true);
            this.jTextArea1.setEditable(false);
        }else
        {
            this.txtOrigen.setText("");
            this.txtDestino.setText("");
            this.txtIdPersonal.setText("");
            this.txtPersonal.setText("");
            this.txtOrigen.setEnabled(false);
            this.txtDestino.setEnabled(false);
            this.txtIdPersonal.setEnabled(false);
            this.txtPersonal.setEnabled(false);
            this.jButton7.setEnabled(false);
            this.jComboBox6.setEnabled(false);
            this.jTextArea1.setEditable(true);
            this.jTextArea1.setText("");
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
        /*int idmot1 = doc_motivos.get(jComboBox5.getSelectedIndex()).getIdmotivo();
        if (idmot1 == 1){
            this.jComboBox6.setEnabled(true);
        }*/
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        NuevoUsuario();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ListaNotasCreditos notadcr = new ListaNotasCreditos(new JFrame(), idbotica);
        notadcr.pack();
        notadcr.setVisible(true);

        try {
            String document = notadcr.getNumero_Documento();
            if (document != null) {
                this.jTextField1.setText(notadcr.getNumero_Documento());
                this.jTextField2.setText(String.valueOf(notadcr.getTotal()));
                IdVentaNC = notadcr.getIdVenta();
                SerieNC = notadcr.getSerie();
                NumeroNC = notadcr.getNumero();
                this.jTextArea1.requestFocus();
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        esretencion = false;
        Verifica_Es_Retencion();
    }//GEN-LAST:event_jTextField2FocusLost

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        if (evt.getStateChange() == 2) {
            if (doc_cajas.get(jComboBox3.getSelectedIndex()).getIdgasto() == 7) {
                jButton1.setEnabled(true);
                jTextField1.setEditable(false);
                jTextField2.setEditable(false);
                jTextField1.setBackground(Color.YELLOW);
                jTextField2.setBackground(Color.YELLOW);
            } else {
                jButton1.setEnabled(false);
                jTextField1.setEditable(true);
                jTextField2.setEditable(true);
                jTextField1.setBackground(Color.WHITE);
                jTextField2.setBackground(Color.WHITE);
                SerieNC = "";
                NumeroNC = "";
                IdVentaNC = "";
            }
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
        String TXT_Mot = doc_motivos.get(jComboBox5.getSelectedIndex()).getDescripMotivo();
        String TXT_SubMot = docSub_motivos.get(jComboBox6.getSelectedIndex()).getDescripSubMotivo();
        jTextArea1.setText(TXT_Mot+ " - " +TXT_SubMot);
        
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox6MouseClicked

    private void jComboBox6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox6MousePressed

    private void jComboBox6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox6KeyReleased

    private void NuevoUsuario() {
        NuevoClienteGastos pe = new NuevoClienteGastos(this, objetoventana,obj.getIdbotica());
        pe.pack();
        pe.setVisible(true);
    }
    private void muestra_Personal() {
        formpersonal = new FormPersonal(objetoventana);
        formpersonal.pack();
        formpersonal.setVisible(true);

        IdPersonal = FormPersonal.getIdpersonal();
        String nombre = FormPersonal.getNombre_Personal();

        if (IdPersonal != null) {
            this.txtIdPersonal.setText(IdPersonal);
            this.txtPersonal.setText(nombre);
        }
    }
   public void AsignaDatosCliente() {
        //if (objcliente.getRUC_DNI() == null) {
          
        jTextField6.setText(objcliente.getRUC_DNI());
        jTextField7.setText(objcliente.getNombre_RazonSocial());
        jTextField8.setText(objcliente.getCodigoCliente());
        jTextField1.requestFocus();
    }
   //public void AsignaDatosCliente1(String RUC_DNI,String Nombre_RazonSocial,String CodigoCliente) {
   public void AsignaDatosCliente1() {

        jTextField6.setText(objcliente.getRUC_DNI());
        jTextField7.setText(objcliente.getNombre_RazonSocial());
        jTextField8.setText(objcliente.getCodigoCliente());
        jTextField1.requestFocus();
    }
    public void MuestraClientes(NuevoClienteGastos obj, int id_cliente) {

        jTextField6.setText(obj.getObjcliente().getRUC_DNI());
        jTextField7.setText(obj.getObjcliente().getNombre_RazonSocial());
        jTextField8.setText(String.valueOf(id_cliente));
        jTextField1.requestFocus();
    }
   
    private void Guardar_Gasto() {
        String doc, concepto,clientegasto,SubMotivo;
        int idgasto,idmotivo;
        Cajas miobj;
        List<Cajas> ListaGastos = new ArrayList<Cajas>();
        String serieRetencion, Numeroretencion;
        

        try {

            idgasto = doc_cajas.get(jComboBox3.getSelectedIndex()).getIdgasto();
            idmotivo = doc_motivos.get(jComboBox5.getSelectedIndex()).getIdmotivo();
            SubMotivo = docSub_motivos.get(jComboBox6.getSelectedIndex()).getDescripSubMotivo();
            MontoPago = Double.parseDouble(this.jTextField2.getText().trim());
            doc = this.jTextField1.getText().trim();
            concepto = this.jTextArea1.getText().trim().toUpperCase();
            clientegasto = this.jTextField8.getText().trim();
            
            if (idmotivo != 1 ){               
                SubMotivo = "";
            }
            if (idmotivo == 1 ){                
                origenMov = this.txtOrigen.getText().trim().toUpperCase();
                destinoMov = this.txtDestino.getText().trim().toUpperCase();
                idperMov = this.txtIdPersonal.getText().trim().toUpperCase();                
            }            

            int idturno = Lista_Turnos.get(jComboBox4.getSelectedIndex()).getId_Turno();

            BigDecimal bd = new BigDecimal(MontoPago);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            MontoPago = bd.doubleValue();

            if (esretencion) {
                serieRetencion = jTextField3.getText().trim();
                Numeroretencion = jTextField4.getText().trim();
                String docu = serieRetencion + " - " + Numeroretencion;

                ListaGastos.add(new Cajas(idbotica, idcaja, idpersonal, idgasto, docu, ImporteRetenido, concepto, idcajero, doc, idturno, SerieNC, NumeroNC, IdVentaNC,clientegasto,idmotivo,origenMov,destinoMov,idperMov,SubMotivo));
            }
            
            Cajas caja = new Cajas(idbotica, idcaja, idpersonal, idgasto, doc, MontoPago, concepto, idcajero, "", idturno, SerieNC, NumeroNC, IdVentaNC,clientegasto,idmotivo,origenMov,destinoMov,idperMov,SubMotivo);

            caja.setFecha(fechaInicio.toString());
            ListaGastos.add(caja);

            //int resul = logbotica.Verifica_TotalMov(idbotica,Integer.valueOf(idperMov),this.jLabel6.getText(),this.jLabel6.getText());
            if (idmotivo == 1 && idperMov.equals("") ){
                JOptionPane.showMessageDialog(this, " DEBE ASIGNAR UN PERSONAL PARA EL GASTO ", "GASTO", JOptionPane.ERROR_MESSAGE);
            } else{

            boolean valor = logcajas.Ingreso_Gastos_Caja(ListaGastos);

            if (valor) {

                Deshabilita(false);
                Limpiar_Datos();
                jButton4.setEnabled(false);
                jButton3.setEnabled(true);
                JOptionPane.showMessageDialog(this, " GASTO REGISTRADO CORRECTAMENTE ", "GASTO REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
                esretencion = false;
                ImporteRetenido = 0;
                MontoPago = 0;
                ListaGastos.removeAll(ListaGastos);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setSelectedIndex(0);
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
                txtOrigen.setText("");
                txtDestino.setText("");
                jTextField8.setText("");
                txtIdPersonal.setText("");
                txtPersonal.setText("");

            } else {
                JOptionPane.showMessageDialog(this, " NO SE PUDO REGISTRAR SU GASTO ", "Error", JOptionPane.ERROR_MESSAGE);
                esretencion = false;
                ImporteRetenido = 0;
                MontoPago = 0;
                ListaGastos.removeAll(ListaGastos);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setSelectedIndex(0);
            }
            }

        } catch (Exception ex) {
        }

    }

    private void Limpiar_Datos() {
        this.jTextField1.setText("");
        this.jTextField2.setText("");
        this.jTextArea1.setText("");
        this.jComboBox3.setSelectedIndex(1);
        this.txtOrigen.setEnabled(false);
        this.txtDestino.setEnabled(false);
        this.txtIdPersonal.setEnabled(false);
        this.txtPersonal.setEnabled(false);
        this.jButton7.setEnabled(false);
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtIdPersonal;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtPersonal;
    // End of variables declaration//GEN-END:variables

    



}
