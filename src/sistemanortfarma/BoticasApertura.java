package sistemanortfarma;

import CapaDatos.ConexionPool;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCajas;
import CapaLogica.LogicaDocumentosVentas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Boticas;
import entidad.Cajas;
import entidad.DocumentoVentas;
import CapaLogica.LogicaProducto;
import entidad.TipoDocumento;
import entidad.Turno;
import entidad.Ventas_Tipo_Pago;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class BoticasApertura extends javax.swing.JDialog {

    login objmenu;
    private String idbotica;
    DefaultListModel modeloCajas = new DefaultListModel();
    LogicaProducto objProducto = new LogicaProducto();
    LogicaCajas objcajas = new LogicaCajas();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaVenta objTipoVentas = new LogicaVenta();
    List<TipoDocumento> listatipoventas = new ArrayList<TipoDocumento>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    private String verificarEnvio;
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaDocumentosVentas objDocumentoVentas = new LogicaDocumentosVentas();
    List<DocumentoVentas> listaDocumentoVentas;
    private int idpersonal;
    double dolares = 0.00;
    AplicacionVentas objventas;
    private DefaultTableModel modelopagos;
    //LogicaBoticas logbotica = new LogicaBoticas();
    private Object[] datos_pagos = new Object[2];
    private int cantidadpagos = 3;
    private List<Ventas_Tipo_Pago> Lista_Ingresos = new ArrayList<Ventas_Tipo_Pago>();
    List<Cajas> MIlista_Cajas = new ArrayList<Cajas>();
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    TableColumn colu;
    JCheckBox checkPromocion = new JCheckBox();
    private int marca = 0;
    MiModelo modeloTabla;
    String dni = null;
    int PersonalCompara;
    int cajaescogida;
    Connection conex;
    static StringBuffer sb = new StringBuffer();
    int i;
    static PreparedStatement estado;

    public BoticasApertura(java.awt.Frame parent, AplicacionVentas obj) {
        super(parent, true);
        initComponents();
        objventas = obj;
        jLabel11.setText(objventas.getUsuario());
        jLabel13.setText(objventas.getCargo());
        setLocationRelativeTo(null);
        idbotica = objventas.getIdbotica();
        idpersonal = objmenu.getUsuariobotica().get(0).getId_Personal();
        jLabel2.setText(idbotica);
        AparienciaTabla();
        modelopagos = (DefaultTableModel) jTable4.getModel();
        SaldoInicial();
        mostrarTitulo();
        jcombocaja.setEnabled(false);
        cargarCajas();
        jComboBox4.setEnabled(false);
        jXDatePicker1.setDate(null);
        checkbox1.setEnabled(false);
        this.jTextField1.setText("0.00");
        this.jTextField1.setEnabled(false);
        MuestraFecha();
        modeloTabla = new MiModelo(marca);
        generarCabeceras();
        colu = jTable1.getColumnModel().getColumn(4);
        Connection conex;

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    private void generarCabeceras() {
        modeloTabla.addColumn("CAJA");
        modeloTabla.addColumn("TIPO DOCUMENTO");
        modeloTabla.addColumn("SERIE");
        modeloTabla.addColumn("NUMERO");
        modeloTabla.addColumn("APERTURAR");
        jTable1.setModel(modeloTabla);
    }

    private void mostrarTitulo() {
        listaboticas2 = objBoticas.DescripcionBoticaDefault();
    }

    private void MuestraFecha() {
        this.jLabel15.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
    }

    private void Lista_Turnos() {
        try {
            Lista_Turnos = objcajas.Lista_Turnos_Apertura(idbotica, listacajas.get(jcombocaja.getSelectedIndex() - 1).getIdcaja(), idpersonal);
            jComboBox4.addItem("---- Seleccione Turno ---------");
            for (int i = 0; i < Lista_Turnos.size(); i++) {
                jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
            }
        } catch (Exception ex) {
        }

    }

    private void SaldoInicial() {
        String fecha = obtenerfecha();
        List<Cajas> saldos_cajas = objcajas.Lista_Saldo_Inicial(idbotica, fecha);
        Object array[] = new Object[3];

        for (int i = 0; i < saldos_cajas.size(); i++) {
            array[0] = "Cash (Contado)";
            array[1] = saldos_cajas.get(i).getMicaja();
            array[2] = saldos_cajas.get(i).getSaldo();
            marca = saldos_cajas.get(i).getApertura();
            modelopagos.addRow(array);
        }
    }

    //OBTENER FECHA PARA EL SALDO INICIAL
    private String obtenerfecha() {
        Calendar fechatotal = new GregorianCalendar();
        String fecha;
        int ano, mes, dia;
        ano = fechatotal.get(Calendar.YEAR);
        mes = fechatotal.get(Calendar.MONTH) + 1;
        dia = fechatotal.get(Calendar.DAY_OF_MONTH);
        fecha = String.valueOf(ano) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia);
        return fecha;
    }

    private void cargarCajas() {

        listacajas = objcajas.ListarCajas();
        this.jcombocaja.addItem("---- Seleccione Caja ---------");

        for (int i = 0; i < listacajas.size(); i++) {
            this.jcombocaja.addItem(listacajas.get(i).getCajas());
        }

        jXDatePicker1.setDate(objFechaHora.RetornaFecha());
        this.jFormattedTextField1.setText(objFechaHora.RetornaHora().toString());

    }

    private void Muestra_Documentos() {
        int idcaja = listacajas.get(this.jcombocaja.getSelectedIndex() - 1).getIdcaja();
        Cajas entidad = new Cajas();
        entidad.setIdbotica(idbotica);
        entidad.setIdcaja(idcaja);
        List<DocumentoVentas> milista = objcajas.ListaDocumentos(entidad);

        for (DocumentoVentas obj : milista) {

            String ConCeros = obj.getNumeracion();
            for (Integer cero = ConCeros.length(); cero <= 8; cero++) {
                ConCeros = "0" + ConCeros;
            }

            checkPromocion.setSelected(true);
            colu.setCellEditor(new DefaultCellEditor(checkPromocion));

            Object[][] data = {
                {jcombocaja.getSelectedItem().toString(),
                    obj.getDocumento(),
                    obj.getSerie(),
                    ConCeros, checkPromocion.isSelected()}};

            modeloTabla.getColumnClass(4);
            modeloTabla.addRow(data[0]);
            jButton1.setEnabled(true);
        }

    }

    public boolean isDate(String fechax) {

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        checkbox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox4 = new javax.swing.JComboBox();
        jcombocaja = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BoticasApertura.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(resourceMap.getFont("jTabbedPane1.font")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jXDatePicker1.setToolTipText(resourceMap.getString("jXDatePicker1.toolTipText")); // NOI18N
        jXDatePicker1.setDate(null);
        jXDatePicker1.setFont(resourceMap.getFont("jXDatePicker1.font")); // NOI18N
        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N
        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jFormattedTextField1.setEnabled(false);
        jFormattedTextField1.setFont(resourceMap.getFont("jXDatePicker1.font")); // NOI18N
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setEnabled(false);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setMnemonic('G');
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton2.setMnemonic('C');
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        checkbox1.setFont(resourceMap.getFont("checkbox1.font")); // NOI18N
        checkbox1.setText(resourceMap.getString("checkbox1.text")); // NOI18N
        checkbox1.setName("checkbox1"); // NOI18N
        checkbox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkbox1ItemStateChanged(evt);
            }
        });
        checkbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jcombocaja.setName("jcombocaja"); // NOI18N
        jcombocaja.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcombocajaItemStateChanged(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(210, 210, 210))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcombocaja, javax.swing.GroupLayout.Alignment.LEADING, 0, 218, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(233, 233, 233))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(562, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcombocaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), resourceMap.getIcon("jPanel1.TabConstraints.tabIcon"), jPanel1); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable4.setFont(resourceMap.getFont("jTable4.font")); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "CAJA", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setColumnSelectionAllowed(true);
        jTable4.setEnabled(false);
        jTable4.setName("jTable4"); // NOI18N
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable4);
        jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.getColumnModel().getColumn(0).setResizable(false);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable4.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable4.columnModel.title0")); // NOI18N
        jTable4.getColumnModel().getColumn(1).setResizable(false);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(110);
        jTable4.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable4.columnModel.title2")); // NOI18N
        jTable4.getColumnModel().getColumn(2).setResizable(false);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable4.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable4.columnModel.title1")); // NOI18N

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setToolTipText(resourceMap.getString("jButton8.toolTipText")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setEnabled(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, 0, 0, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), resourceMap.getIcon("jPanel3.TabConstraints.tabIcon"), jPanel3); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setFont(resourceMap.getFont("jPanel2.font")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean RecuperaOtrosIngresos() {
        boolean valor = true;

        try {

            for (int i = 0; i < modelopagos.getRowCount(); i++) {
                String descrp = modelopagos.getValueAt(i, 0).toString();
                double monto = Double.parseDouble(modelopagos.getValueAt(i, 1).toString());

                if (monto >= 0 && descrp.compareTo("") != 0) {
                    Lista_Ingresos.add(new Ventas_Tipo_Pago(descrp, monto));
                } else {
                    JOptionPane.showMessageDialog(null, "Porfavor Ingrese Monto Valido", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    Lista_Ingresos.removeAll(Lista_Ingresos);
                    valor = false;
                    break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Porfavor Ingrese Datos Correctos", "Nortfarma", JOptionPane.ERROR_MESSAGE);
            Lista_Ingresos.removeAll(Lista_Ingresos);
            valor = false;
        }

        return valor;

    }

    private boolean RevisaSeleccion() {

        for (Integer puntero = 0; puntero < this.jTable1.getRowCount(); puntero++) {
            if (this.jTable1.getValueAt(puntero, 4).toString().compareTo("true") == 0) {
                return true;
            }
        }
        return false;
    }

    private void Cierra() {
        objventas.Habilita(true);
        objventas.marcacdo = false;
        dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int turno;

        if (jComboBox4.getSelectedIndex() > 0) {
            if (jcombocaja.getSelectedIndex() > 0) {

                turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();

                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText("0.00");
                }

                /**/
                Ventana obj = new Ventana();
                VerificaUsuarioApertura verifica = new VerificaUsuarioApertura(obj);
                verifica.pack();
                verifica.show(true);

                dni = verifica.getDni();
                cajaescogida = verifica.getCaja();

                PersonalCompara = objProducto.RetornaPersonalApertura(dni, cajaescogida);
                int cajapertura = listacajas.get(jcombocaja.getSelectedIndex() - 1).getIdcaja();
                dolares = Double.parseDouble(jTextField1.getText());

                /**/
                if (RevisaSeleccion()) {

                    if (idpersonal == PersonalCompara && cajapertura == cajaescogida) {

                        String msg1 = "<html><h1>Sr(a): <font color='red'> " + jLabel11.getText() + "</font></h2><hr></html>\n";
                        String msg2 = "<html><h3>Ud. esta aperturando:<hr></html>\n";
                        String msg3 = "<html><h2> CAJA&nbsp;&nbsp;&nbsp;&nbsp;>> <font color='red'> " + jcombocaja.getSelectedItem() + "<hr>\n";
                        String msg4 = "<html><h2> TURNO >> <font color='red'> " + jComboBox4.getSelectedItem() + "<hr>\n";
                        String msg5 = "<html><h3> ¿ Desea Realizar Apertura de Caja ?________________________<hr></html>";

                        //int reply = JOptionPane.showConfirmDialog(null, "Desea Realizar Apertura de Caja", "NORTFARMA", JOptionPane.YES_NO_OPTION);
                        int reply = JOptionPane.showOptionDialog(null, msg1 + msg2 + msg3 + msg4 + msg5, "NORTFARMA",
                                JOptionPane.YES_NO_OPTION,//Botones que apareces para seleccionar
                                JOptionPane.QUESTION_MESSAGE,//Icono por defecto
                                null, // null para icono por defecto, o un icono personalizado
                                new Object[]{"ACEPTAR", "CANCELAR"}, // botones presonalizados botones por defecto en este caso YES, NO
                                "CANCELAR");//Botón selecconado por defecto

                        if (reply == JOptionPane.YES_OPTION) {

                            Date date = jXDatePicker1.getDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            int resul;
                            MIlista_Cajas.removeAll(MIlista_Cajas);
                            for (Integer puntero = 0; puntero < this.jTable1.getRowCount(); puntero++) {
                                if (this.jTable1.getValueAt(puntero, 4).toString().compareTo("true") == 0) {
                                    MIlista_Cajas.add(new Cajas(listacajas.get(jcombocaja.getSelectedIndex() - 1).getIdcaja(),
                                            this.jTable1.getValueAt(puntero, 1).toString(), this.jTable1.getValueAt(puntero, 2).toString(),
                                            this.jTable1.getValueAt(puntero, 3).toString(), sdf.format(date).toString(),
                                            this.jFormattedTextField1.getText(),
                                            this.idbotica, turno, dolares));
                                }
                            }

                            resul = objBoticas.AperturaBoticas(MIlista_Cajas, idpersonal, Lista_Ingresos);

                            if (resul == 0) {

                                verificarEnvio = objBoticas.VerificaEnvio(idbotica);

                                if (verificarEnvio.equals("0")) {
                                    ActualizarPrecios();
                                }

                                JOptionPane.showMessageDialog(null, " Su " + jcombocaja.getSelectedItem().toString() + " Ha Sido Aperturada  ", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                Cierra();
                            } else {
                                if (resul == 1) {
                                    JOptionPane.showMessageDialog(null, "Error : \n Lo sentimos esta Caja esta Aperturada \n Para Aperturar es necesario que " + jcombocaja.getSelectedItem().toString() + " este Cerrada ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    Lista_Ingresos.removeAll(Lista_Ingresos);
                                    MIlista_Cajas.removeAll(MIlista_Cajas);

                                } else {
                                    JOptionPane.showMessageDialog(null, "Lo sentimos Hubo un error al Aperturar su Caja", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    Lista_Ingresos.removeAll(Lista_Ingresos);
                                    MIlista_Cajas.removeAll(MIlista_Cajas);
                                }
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, " Error: " + "El DNI o la Caja que a ingresado no coinciden con el Usuario a Aperturar... Verificar", "Nortfarma", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, " Error: " + "Debe de seleccionar Documentos a Aperturar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, " Error: " + " Pofavor Seleccione su Caja", "Nortfarma", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, " Error: " + " Pofavor Seleccione su Turno", "Nortfarma", JOptionPane.ERROR_MESSAGE);
        }

}//GEN-LAST:event_jButton1ActionPerformed

    private void ActualizarPrecios() {

        try {

            String s = new String();
            conex = new ConexionPool().getConnection();

            FileReader lector = new FileReader(new File("/datos/envio/SQLFINAL.SQL"));
            //FileReader lector = new FileReader(new File("c:\\AENVIO\\SQLFINAL.SQL"));
            //ruta exacta del archivo sql//
            BufferedReader buffer = new BufferedReader(lector);
            while ((s = buffer.readLine()) != null) {
                sb.append(s);
            }

            String[] inst = sb.toString().split(";");

            for (i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    estado = conex.prepareStatement(inst[i]);
                    estado.executeUpdate();

                    System.out.println("Ejecutando Consulta \t" + i);
                }
            }
            Date fecha = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            boolean resul = objBoticas.GuardaBitacora(idbotica, formateador.format(fecha), "APERTURA");

            conex.close();
            String comando[] = {"/bin/sh", "-c", "mv /datos/envio/SQLFINAL.SQL /datos/envio/guardados/" + formateador.format(fecha) + ".SQL"};
            String comandoBorar[] = {"/bin/sh", "-c", "rm -rf /datos/envio/*.SQL"};
            //String comando[]={"copy c:\\AENVIO\\SQLFINAL.SQL c:\\AENVIO\\guardados\\"+formateador.format(fecha)};

            Process P = Runtime.getRuntime().exec(comando);
            P.waitFor();
            Process Q = Runtime.getRuntime().exec(comandoBorar);
            Q.waitFor();
            //Runtime.getRuntime().exec("rm -rf ");
            //return "exito";

        } catch (SQLException ex) {

            //conex.close();

            /*lector.log_errores("Error En Consulta Numero: "+i+"\nPosible:"+ex + "\n\n");
            lector.enviar_email();
            lector.borrar_file();

            lector.enviar_email();
            return "error";*/
        } catch (Exception exe) {
            /*lector.log_errores("Archivo de Actualizaciones no encontrado,Se subio el script al servidor?");
            lector.enviar_email();
            return "error";*/
        }
    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cierra();
}//GEN-LAST:event_jButton2ActionPerformed

    private void AgregaItemPago() {
        cantidadpagos++;
        datos_pagos[0] = "";
        datos_pagos[1] = 0.0;
        modelopagos.addRow(datos_pagos);
    }
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        AgregaItemPago();
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int fila = this.jTable4.getSelectedRow();

        try {
            if (fila >= 0) {
                int reply = JOptionPane.showConfirmDialog(null, "Deseas eliminar este item ?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    cantidadpagos--;
                    modelopagos.removeRow(fila);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes de seleecionar un item de Pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox1ItemStateChanged
    }//GEN-LAST:event_checkbox1ItemStateChanged

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        checkbox1.setEnabled(true);
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void checkbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox1ActionPerformed

        boolean fechaValida = false;
        Date date = jXDatePicker1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (this.jFormattedTextField1.toString().trim().length() > 0) {

            try {

                if (sdf.format(date).toString().length() > 0) {
                    try {
                        if (isDate(sdf.format(date).toString())) {
                            fechaValida = true;
                        } else {
                            fechaValida = false;
                        }

                    } catch (Exception e) {
                        fechaValida = false;
                    }

                    if (fechaValida = true) {

                        if (sdf.format(date).toString().compareToIgnoreCase(jLabel15.getText().trim()) == 0) {
                            int reply = JOptionPane.showConfirmDialog(null, "Desea establecer esta configuracion inicial", "NORTFARMA", JOptionPane.YES_NO_OPTION);

                            if (reply == JOptionPane.YES_OPTION) {

                                this.checkbox1.setEnabled(false);
                                this.jXDatePicker1.setEnabled(false);
                                this.jFormattedTextField1.setEditable(false);
                                this.jcombocaja.setEnabled(true);
                                this.jComboBox4.setEnabled(true);

                            } else {
                                checkbox1.setSelected(false);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha seleccionada es incorrecta \n Porfavor Revisar su fecha de apertura", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                            checkbox1.setSelected(false);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Formato de Fecha Incorrecto, Favor Corregir", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        checkbox1.setEnabled(false);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de Fecha Incorrecto, Favor Corregir", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                checkbox1.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Formato de Hora Incorrecto, Favor Corregir", "Nortfarma", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_checkbox1ActionPerformed

    private void jcombocajaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcombocajaItemStateChanged
        if (evt.getStateChange() == 2) {
            LimpiatTabla();
            jComboBox4.removeAllItems();
            Lista_Turnos();
        }
    }//GEN-LAST:event_jcombocajaItemStateChanged

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modeloTabla.removeRow(i);
            }
        }
    }

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        if (this.jComboBox4.getSelectedIndex() > 0) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                LimpiatTabla();
                Muestra_Documentos();
                jTextField1.setEnabled(true);
                jTextField1.requestFocus();
            }
        }
    }//GEN-LAST:event_jComboBox4ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private javax.swing.JComboBox jcombocaja;
    // End of variables declaration//GEN-END:variables

    public class MiModelo extends DefaultTableModel {

        private int marca;

        public MiModelo(int op) {
            marca = op;
        }

        public boolean isCellEditable(int row, int column) {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columna (row,column), sea o no editable
            if (marca == 0) {
                if (column == 3 || column == 2 || column == 0 || column == 1) {
                    return false;
                } else {
                    return true;
                }

            } else {
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        public Class getColumnClass(int columna) {
            if (columna == 4) {
                return Boolean.class;
            }
            return Object.class;
        }

        public Class ColumnClass(int columna) {
            return Integer.class;
        }
    }
}
