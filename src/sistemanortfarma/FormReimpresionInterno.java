package sistemanortfarma;

import CapaDatos.ConexionPool;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import entidad.TipoVenta;
import entidad.DocumentoVentas;
import CapaLogica.LogicaRecuperaCaja;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import entidad.Cajas;
import entidad.TipoDocumento;
import java.awt.event.ItemEvent;
import entidad.SerieNumero;
import CapaLogica.RecuperaSerieNumero;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import CapaLogica.XML_GENERAR;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import CapaLogica.LogicaFechaHora;
import java.util.regex.Pattern;
import java.sql.*;

import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.text.NumberFormat;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemanortfarma.VerificaSistema;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormReimpresionInterno extends javax.swing.JInternalFrame {

    AplicacionVentas objventas;
    Connection conex;
    private ConexionPool db;
    XML_GENERAR objXML_GENERAR;
    String interno;
    List<Venta> listaInternos = new ArrayList<Venta>();
    List<Venta> listaDatosVta = new ArrayList<Venta>();
    List<Venta> listaVenta = new ArrayList<Venta>();
    LogicaVenta logventa = new LogicaVenta();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaVenta NCReimprime = new LogicaVenta();
    LogicaVenta objTipoVentas = new LogicaVenta();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    List<SerieNumero> listaPagos = new ArrayList<SerieNumero>();
    RecuperaSerieNumero objlogicaPagos = new RecuperaSerieNumero();
    List<Venta> ltaVen_Productos = new ArrayList<Venta>();
    List<Venta> lista_VentaFinal = new ArrayList<Venta>();
    List<TipoVenta> lis_tipo_venta;
    private double MIIGV;
    RequisitosFactura objfactura = new RequisitosFactura();
    List<DocumentoVentas> listaDocumentoVentas;
    Object[] datosDetalle = new Object[6];
    private DefaultTableModel model;
    OpcionesMenu obj1;
    private String Impresora_Factura = obj1.getImpresora_Factura();
    TableColumnModel colModel1;
    int podecimal = 4;
    int podecimalPantalla = 2;
    MuestraVentana objetoventana = new MuestraVentana();
    List<TipoDocumento> listatipoventas = new ArrayList<TipoDocumento>();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    double total = 0;
    double subtotal = 0;
    private String serie = null;
    //private String mnumero = null;
    double igv = 0;
    ResultSet rs;
    Venta obj;
    Venta objNC;
    private int tipoventa;
    private int Rectipvta;
    private String idventa = "";
    private String idbotica = null;
    Object[] datosDetalle_1 = new Object[7];

    Map parameters = new HashMap();
    VerificaSistema obj2;
    URL report_file;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;

    /** Creates new form FormReimpresionInterno */
    public FormReimpresionInterno(AplicacionVentas ob) {
        initComponents();
        objventas = ob;
        idbotica = objventas.getIdbotica();
        model = (DefaultTableModel) jTable1.getModel();
        colModel1 = jTable1.getColumnModel();
        AparienciaTabla();
        this.txtcajero.setBackground(new Color(255, 255, 255));
        this.txtigv.setBackground(new Color(255, 255, 255));
        this.txtnumero.setBackground(new Color(255, 255, 255));
        this.txtserie.setBackground(new Color(255, 255, 255));
        this.txtsubtotal.setBackground(new Color(255, 255, 255));
        this.txttipoventa.setBackground(new Color(255, 255, 255));
        this.txttotal.setBackground(new Color(255, 255, 255));
        this.txtvendedor.setBackground(new Color(255, 255, 255));
        Oculta_Botones(false);
        ListaCajas();
        cargarTipoVentas();
        this.idvta.setVisible(false);
        this.fechavta.setVisible(false);
    }

    private void cargarTipoVentas() {
        listatipoventas = objTipoVentas.ListarTipoDocumentoReimprime();
        for (int i = 0; i < listatipoventas.size(); i++) {
            tipo_documento.addItem(listatipoventas.get(i).getDescripcion());
        }
    }

    private void ListaCajas() {
        listacajas = objcajas.RecuperaCajas(idbotica);

        if (listacajas.size() > 1) {
            this.jComboBox1.addItem("--- Seleccione su Caja ---");
            for (int i = 0; i < listacajas.size(); i++) {
                this.jComboBox1.addItem(listacajas.get(i).getCajas());
            }
        } else {
            if (listacajas.size() == 1) {
                for (int i = 0; i < listacajas.size(); i++) {
                    this.jComboBox1.addItem(listacajas.get(i).getCajas());
                }
            }
        }

    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);

        DefaultTableCellRenderer tleft = new DefaultTableCellRenderer();
        tleft.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tleft);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tleft);

    }

    private void Oculta_Botones(boolean valor) {
        jButton3.setEnabled(valor);
        jButton4.setEnabled(valor);        
    }

    private void limpiatotal(){
        this.txttipoventa.setText("");
        this.txtserie.setText("");
        this.txtnumero.setText("");
        this.txtvendedor.setText("");
        this.txtcajero.setText("");
        this.idvta.setText("");
        this.fechavta.setText("");
        this.txtsubtotal.setText("");
        this.txtigv.setText("");
        this.txttotal.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        tipo_documento = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txttipoventa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtcajero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        txtigv = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        idvta = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        fechavta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormReimpresionInterno.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField31.setBackground(resourceMap.getColor("jTextField31.background")); // NOI18N
        jTextField31.setEditable(false);
        jTextField31.setFont(resourceMap.getFont("jTextField31.font")); // NOI18N
        jTextField31.setForeground(resourceMap.getColor("jTextField31.foreground")); // NOI18N
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField31.setName("jTextField31"); // NOI18N
        jTextField31.setPreferredSize(new java.awt.Dimension(60, 23));

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        tipo_documento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Seleccione Documento ---" }));
        tipo_documento.setName("tipo_documento"); // NOI18N
        tipo_documento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_documentoItemStateChanged(evt);
            }
        });
        tipo_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_documentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(tipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txttipoventa.setBackground(resourceMap.getColor("txtcajero.background")); // NOI18N
        txttipoventa.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txttipoventa.setText(resourceMap.getString("txttipoventa.text")); // NOI18N
        txttipoventa.setEnabled(false);
        txttipoventa.setName("txttipoventa"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtserie.setBackground(resourceMap.getColor("txtcajero.background")); // NOI18N
        txtserie.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txtserie.setText(resourceMap.getString("txtserie.text")); // NOI18N
        txtserie.setEnabled(false);
        txtserie.setName("txtserie"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtnumero.setBackground(resourceMap.getColor("txtcajero.background")); // NOI18N
        txtnumero.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txtnumero.setText(resourceMap.getString("txtnumero.text")); // NOI18N
        txtnumero.setEnabled(false);
        txtnumero.setName("txtnumero"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtvendedor.setBackground(resourceMap.getColor("txtcajero.background")); // NOI18N
        txtvendedor.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txtvendedor.setText(resourceMap.getString("txtvendedor.text")); // NOI18N
        txtvendedor.setEnabled(false);
        txtvendedor.setName("txtvendedor"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Producto", "UND", "FRACC", "PVPx", "Parcial"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setEnabled(false);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtcajero.setBackground(resourceMap.getColor("txtcajero.background")); // NOI18N
        txtcajero.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txtcajero.setText(resourceMap.getString("txtcajero.text")); // NOI18N
        txtcajero.setEnabled(false);
        txtcajero.setName("txtcajero"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txtsubtotal.setBackground(resourceMap.getColor("txttotal.background")); // NOI18N
        txtsubtotal.setFont(resourceMap.getFont("txtigv.font")); // NOI18N
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsubtotal.setText(resourceMap.getString("txtsubtotal.text")); // NOI18N
        txtsubtotal.setEnabled(false);
        txtsubtotal.setName("txtsubtotal"); // NOI18N

        txtigv.setBackground(resourceMap.getColor("txttotal.background")); // NOI18N
        txtigv.setFont(resourceMap.getFont("txtigv.font")); // NOI18N
        txtigv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtigv.setText(resourceMap.getString("txtigv.text")); // NOI18N
        txtigv.setEnabled(false);
        txtigv.setName("txtigv"); // NOI18N

        txttotal.setBackground(resourceMap.getColor("txttotal.background")); // NOI18N
        txttotal.setFont(resourceMap.getFont("txtigv.font")); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotal.setText(resourceMap.getString("txttotal.text")); // NOI18N
        txttotal.setEnabled(false);
        txttotal.setName("txttotal"); // NOI18N

        idvta.setName("idvta"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField5.setFont(resourceMap.getFont("jTextField5.font")); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setEnabled(false);
        jTextField5.setName("jTextField5"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N

        fechavta.setName("fechavta"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(idvta, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fechavta, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtvendedor)
                                .addComponent(txtcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idvta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fechavta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        objventas.marcacdo = false;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BuscaInterno() {
        if (this.jTextField1.getText().trim().compareTo("") != 0) {
            if (this.jTextField1.getText().trim().length() > 0) {
                LimpiatTabla();
                listaInternos.removeAll(listaInternos);
                String NDOC = jTextField1.getText().trim();
                /**/
                int posi1;

                if (this.jComboBox1.getItemCount() > 1) {
                    posi1 = this.jComboBox1.getSelectedIndex() - 1;
                } else {
                    posi1 = this.jComboBox1.getSelectedIndex();
                }
                Rectipvta = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();
                //listaPagos.removeAll(listaPagos);
                //listaPagos = objlogicaPagos.RecuperaSerieNumeroReimprime(idbotica, listacajas.get(posi1).getIdcaja(), tipoventa);
                /**/
                int opc=1;
                int opc1=1;
                int opc2=1;
                String serie = this.jTextField31.getText().trim().toUpperCase();
                obj = new Venta(idbotica, NDOC,listacajas.get(posi1).getIdcaja(),Rectipvta,opc,serie);
                listaInternos = logventa.ReimprimeLista_Internos_Ventas(obj, 0);

                if (listaInternos.size() > 0) {
                    this.txtcajero.setText(listaInternos.get(0).getCajero());
                    this.txtnumero.setText(listaInternos.get(0).getNumero());
                    this.txtserie.setText(listaInternos.get(0).getSerie());
                    this.txttipoventa.setText(listaInternos.get(0).getTipventa());
                    this.txtvendedor.setText(listaInternos.get(0).getNombre());
                    this.txttotal.setText(String.valueOf(listaInternos.get(0).getTotal()));
                    this.txtigv.setText(String.valueOf(listaInternos.get(0).getIGV()));
                    this.txtsubtotal.setText(String.valueOf(listaInternos.get(0).getSubTotal()));
                    this.idvta.setText(String.valueOf(listaInternos.get(0).getId_Venta()));
                    this.jTextField5.setText(String.valueOf(listaInternos.get(0).getredondeo()));
                    this.jTextField6.setText(String.valueOf(listaInternos.get(0).gettotfinal()));
                    this.fechavta.setText(String.valueOf(listaInternos.get(0).getFecha_Venta()));                    

                    CargarProductos();
                }else{
                    limpiatotal();
                }
                Oculta_Botones(true);

            } else {
                JOptionPane.showMessageDialog(this, " Numero de Documento Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, " Porfavor Ingrese su Numero de Documento ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscaInterno();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimpiatTabla();
        listaInternos.removeAll(listaInternos);
        listaVenta.removeAll(listaVenta);
        interno = "";
        Limpia();
        Oculta_Botones(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Confirmar p = new Confirmar(objetoventana, "<html> Deseas Imprimir Este Numero de Documento </html>");
        p.show(true);

        if (p.getConfirmar() == 1) {

            //interno = jTextField1.getText().trim();
            interno = idvta.getText().trim();
            //int ipventa = logventa.Devuelve_Tpo_Venta(interno);
            int ipventa = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();

            if (ipventa == 1) {
                logventa.Imprime_Boleta(interno, idbotica);
            } else {
               /* if (ipventa == 12) {
                    List<Venta> lista1 = new ArrayList<Venta>();
                    Venta objventa1 = new Venta();
                    objventa1.setId_Venta(interno);
                    lista1.add(objventa1);
                    //logventa.Imprime_Factura(interno, idbotica);
                    logventa.ReImprime_Factura(lista1,idbotica);
                } else*/ if (ipventa == 11 || ipventa == 12) {
                    List<Venta> lista = new ArrayList<Venta>();
                    Venta objventa = new Venta();
                    objventa.setId_Venta(interno);
                    lista.add(objventa);
                    boolean valor = logventa.ReImpresion_Ticketera(lista, idbotica,ipventa);
                    if (!valor) {
                        JOptionPane.showMessageDialog(this, "  LO SENTIMOS ERROR AL REIMPRIMIR INTERNO ", "ERROR DE IMPRESION", JOptionPane.ERROR_MESSAGE);
                    }
                }else if (ipventa == 13 || ipventa == 14){
                    int opc=1;
                    listaDatosVta.removeAll(listaDatosVta);
                    
                    objNC = new Venta(idbotica,txtserie.getText().trim().toUpperCase(),
                                      txtnumero.getText().trim().toUpperCase(),idvta.getText().trim().toUpperCase(),opc);

                    listaDatosVta = logventa.Lista_Datos_Ventas(objNC);
                    String obs = logventa.Recupera_Observacion(idbotica,txtserie.getText().trim().toUpperCase(),
                                      txtnumero.getText().trim().toUpperCase());
                    
                    GeneraImpresion(objNC,listaVenta,idvta.getText().trim().toUpperCase(),listaDatosVta,obs);
                    
                }
            }
            objventas.marcacdo = false;
            dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void GeneraImpresion(Venta obj,List<Venta> listaVtasDetalle,String Interno,List<Venta>listaDatosVtaPadre,String obs) {

        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Double> vtadettotal = new ArrayList<Double>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> tunit = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        Double IGV = 0.00, Totalizado = 0.00, sumaTotal = 0.00, SubSub = 0.00,Redondeo=0.0,MiTotal=0.0,TotFinal=0.0;
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0;
        String cliente = "", Np = "", salesman = "", direccion = "", RUC = "", Direccion = null, PLACA = "", DNICLI="",
                botica = "",DireccionFiscal="",serie = null, numero = "",Delivery="",correo="",vtaruc="";
        String vendedor = null, cajero = null;
        Integer Largo = 0;
        int lineas = 0;
        String fecha = null;
        String hora = null;
        Integer Espacios = 7;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        int confirma = 0;
        FileOutputStream os = null;
        PrintStream ps = null;
        CallableStatement procedure = null;

        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps1;

        String ConvTotaLetras ="";
        String  direcCliente="", Nomvendedor="",Nomcajero="",tipodocumento="",
                numdocumento="",documentoemitido="",serienumero="",idvta="",observacion="",docreferencia="",
                sernumref="",fecharef = "", cantidad="", producto="",simbolo="",idmoneda="1",disclaimer="",
                rucbarcode = "",tipdocbarcode="",igvbarcode="",totalbarcode="",tipodocadquiriente="",
                numerodocadquiriente="",firmabarcode="",tituloopgravada="",tituloopinafecta="",tituloopigv="",
                tituloopimportetotal="",tituloopredondeo="",titulooptotalpagar="",titulooptotaldscto="",
                fecha_aux = "", fecha_aux1 = "",money = "";

        Double  OP_GRATUITA=0.0,opimportetotal=0.0,optotalpagar=0.0,
                opimportetotal1=0.0, opgravada1=0.0,opinafecta1 =0.0, opigv1 = 0.0, Redondeo1 = 0.0,
                optotalpagar1 = 0.0, opdescuento1=0.0,totaldet=0.0;

        try{

               confirma = 1;            
                    if (confirma == 1) {
                        
                        file = new FileWriter(Impresora_Factura);
                        //file = new FileWriter("//10.10.1.253//bixolon");
                        buffer = new BufferedWriter(file);
                        ps1 = new PrintWriter(buffer);
                        String miinterno = obj.getId_Venta().toString().trim();
                        sumaTotal = 0.00;

                        String modoImpresionDoc = listaDatosVtaPadre.get(0).gett_impre().toString().trim();

                        //if (conex.isClosed()) {
                            conex = new ConexionPool().getConnection();
                        //}

                        //procedure = conex.prepareCall("{ call PDF_VENTAFINAL (?,?) }");
                        procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_NC (?,?) }");
                        procedure.setString(1, miinterno);
                        //procedure.setString(2, ConvTotaLetras);
                        procedure.setString(2, idbotica);
                        rs = procedure.executeQuery();

                        lista_VentaFinal.removeAll(lista_VentaFinal);
                        NumberFormat formatter = new DecimalFormat("#,##0.00");

                        while (rs.next()) {
                            
                            /*
                            botica = rs.getString("Empresa_Botica");
                            Direccion = rs.getString("Direccion_Botica");
                            DireccionFiscal = rs.getString("Direccion_Empresa");
                            RUC = rs.getString("RUC");

                            cliente = rs.getString("NOMCLIENTE");
                            direcCliente = rs.getString("DIRECCION_CLIENTE");
                            tipodocumento = rs.getString("TIPO_DOCUMENTO");
                            numdocumento = rs.getString("NUMERO_DOCUMENTO");
                            documentoemitido = rs.getString("Documento");
                            serienumero = rs.getString("SerNum");
                            fecha_aux = String.valueOf(rs.getDate("Fecha_Venta"));
                            fecha = objFechaHora.MysqlToJuliano(fecha_aux).toString();
                            hora = rs.getString("Hora_Venta");
                            idvta = rs.getString("Id_Venta");
                            cajero = rs.getString("CAJERO");
                            vendedor = rs.getString("VENDEDOR");
                            TotFinal = rs.getDouble("Total");
                            money = rs.getString("MONEDA");
                            idmoneda = rs.getString("Id_Moneda");
                            observacion = rs.getString("Observacion");
                            docreferencia = rs.getString("DocumentoRef");
                            sernumref = rs.getString("SerNumRef");                            
                            fecha_aux1 = String.valueOf(rs.getDate("Fecha_VentaRef"));
                            fecharef = objFechaHora.MysqlToJuliano(fecha_aux1).toString();
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cantidad = rs.getString("Cantidad");
                            producto = rs.getString("Producto");
                            totaldet = rs.getDouble("Totaldet");
                            String totaldetx = formatter.format(totaldet);
                            String totaldetx1 = totaldetx.replace(".", ";");
                            String totaldetx2 = totaldetx1.replace(",", ".");
                            String totaldetx3 = totaldetx2.replace(";", ",");

                            tituloopgravada = rs.getString("TITULOOPGRAVADA");
                            opgravada1 = rs.getDouble("OPGRAVADA");
                            String opgravada1x = formatter.format(opgravada1);
                            String opgravada1x1 = opgravada1x.replace(".", ";");
                            String opgravada1x2 = opgravada1x1.replace(",", ".");
                            String opgravada1x3 = opgravada1x2.replace(";", ",");
                            if (opgravada1x3.equals("0")|| opgravada1x3.equals("0.0")|| opgravada1x3.equals("0.00")){
                                opgravada1x3 = "";
                            }
                            tituloopinafecta = rs.getString("TITULOINAFECTA");
                            opinafecta1 = rs.getDouble("OPINAFECTA");
                            String opinafecta1x = formatter.format(opinafecta1);
                            String opinafecta1x1 = opinafecta1x.replace(".", ";");
                            String opinafecta1x2 = opinafecta1x1.replace(",", ".");
                            String opinafecta1x3 = opinafecta1x2.replace(";", ",");
                            if (opinafecta1x3.equals("0")|| opinafecta1x3.equals("0.0")|| opinafecta1x3.equals("0.00")){
                               opinafecta1x3 = "";
                            }
                            tituloopigv = rs.getString("TITULOIGV");
                            opigv1 = rs.getDouble("IGV");
                            String opigv1x = formatter.format(opigv1);
                            String opigv1x1 = opigv1x.replace(".", ";");
                            String opigv1x2 = opigv1x1.replace(",", ".");
                            String opigv1x3 = opigv1x2.replace(";", ",");
                            if (opigv1x3.equals("0")|| opigv1x3.equals("0.0")|| opigv1x3.equals("0.00")){
                               opigv1x3 = "";
                            }
                            tituloopimportetotal = rs.getString("TITULOIMPORTETOTAL");
                            opimportetotal1 = rs.getDouble("IMPORTETOTAL");
                            String opimportetotx = formatter.format(opimportetotal1);
                            String opimportetotx1 = opimportetotx.replace(".", ";");
                            String opimportetotx2 = opimportetotx1.replace(",", ".");
                            String opimportetotx3 = opimportetotx2.replace(";", ",");
                            if (opimportetotx3.equals("0")|| opimportetotx3.equals("0.0")|| opimportetotx3.equals("0.00")){
                                opimportetotx3 = "";
                            }
                            tituloopredondeo = rs.getString("TITULOREDONDEO");
                            Redondeo1 = rs.getDouble("REDONDEO");
                            String Redondeo1x = formatter.format(Redondeo1);
                            String Redondeo1x1 = Redondeo1x.replace(".", ";");
                            String Redondeo1x2 = Redondeo1x1.replace(",", ".");
                            String Redondeo1x3 = Redondeo1x2.replace(";", ",");
                            if (Redondeo1x3.equals("0")|| Redondeo1x3.equals("0.0")|| Redondeo1x3.equals("0.00")){
                               Redondeo1x3 = "";
                            }
                            titulooptotalpagar = rs.getString("TITULOTOTALPAGAR");
                            optotalpagar1 = rs.getDouble("TOTALPAGAR");
                            String optotalpagar1x = formatter.format(optotalpagar1);
                            String optotalpagar1x1 = optotalpagar1x.replace(".", ";");
                            String optotalpagar1x2 = optotalpagar1x1.replace(",", ".");
                            String optotalpagar1x3 = optotalpagar1x2.replace(";", ",");
                            if (optotalpagar1x3.equals("0")|| optotalpagar1x3.equals("0.0")|| optotalpagar1x3.equals("0.00")){
                                optotalpagar1x3 = "";
                            }
                            titulooptotaldscto = rs.getString("TITULOTOTALDSCTO");
                            opdescuento1 = rs.getDouble("TOTALDSCTO");
                            String opdescuento1x = formatter.format(opdescuento1);
                            String opdescuento1x1 = opdescuento1x.replace(".", ";");
                            String opdescuento1x2 = opdescuento1x1.replace(",", ".");
                            String opdescuento1x3 = opdescuento1x2.replace(";", ",");
                            if (opdescuento1x3.equals("0")|| opdescuento1x3.equals("0.0")|| opdescuento1x3.equals("0.00")){
                                opdescuento1x3 = "";
                            }

                            disclaimer = rs.getString("DISCLAIMER");

                            rucbarcode = rs.getString("RUCBARCODE");
                            tipdocbarcode = rs.getString("TIPODOCUMENTOBARCODE");
                            igvbarcode = rs.getString("IGVBARCODE");
                            totalbarcode = rs.getString("TOTALBARCODE");
                            tipodocadquiriente = rs.getString("TIPODOCADQUIRIENTE");
                            numerodocadquiriente = rs.getString("NUMERODOCADQUIRIENTE");
                            firmabarcode = rs.getString("Firma");

                            lista_VentaFinal.add(new Venta(botica,Direccion,DireccionFiscal,RUC,
                                    serie,numero,TotFinal,fecha,cliente,direcCliente,tipodocumento,numdocumento,
                                    documentoemitido,serienumero,hora,idvta,cajero,vendedor,money,observacion,
                                    docreferencia,sernumref,fecharef,cantidad,producto,totaldetx3,opgravada1x3,
                                    opinafecta1x3,opigv1x3,opimportetotx3,Redondeo1x3,optotalpagar1x3,opdescuento1x3,
                                    idmoneda,disclaimer,tituloopgravada,tituloopinafecta,tituloopigv,tituloopimportetotal,
                                    tituloopredondeo,titulooptotalpagar,titulooptotaldscto,
                                    rucbarcode,tipdocbarcode,igvbarcode,totalbarcode,tipodocadquiriente,numerodocadquiriente,firmabarcode
                                    ));

                            */
                            cliente = "  " + rs.getString(1);
                            Largo = cliente.length();

                            if (Largo < 35) {
                                for (Integer ini = Largo; ini < 35; ini++) {
                                    cliente = cliente + " ";
                                }
                            }

                            botica = rs.getString("mibotica");
                            DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                            vendedor = rs.getString("vendedor");
                            cajero = rs.getString("cajero");
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cliente = cliente + "     ";
                            ProductoDescripcion.add(rs.getString(6));
                            Labs.add(rs.getString(7));
                            CantidadU.add(rs.getInt(8));
                            CantidadF.add(rs.getInt(9));
                            Precio.add(rs.getDouble(12));  //pvf
                            PrecioF.add(rs.getDouble(10));
                            SubSub = rs.getDouble(13);
                            IGV = rs.getDouble(14);
                            Totalizado = rs.getDouble(15);
                            sumaTotal = sumaTotal + rs.getDouble(10);//+ rs.getDouble(11);
                            fecha = rs.getDate(4).toString();
                            salesman = rs.getString(5);
                            direccion = rs.getString(3);
                            RUC = rs.getString(2);
                            Direccion = rs.getString("Direccion");
                            opgravada = rs.getDouble("OP_GRAVADA");
                            opinafecta = rs.getDouble("Op_Inafecta");
                            opigv = rs.getDouble("IGV");
                            opdescuento = rs.getDouble("Op_Descuento");
                            Delivery = rs.getString("Delivery");
                            correo = rs.getString("CorreoEmpresa");
                            vtadettotal.add(rs.getDouble("ventadetalletotal"));
                            vtaruc = rs.getString("rucventa");
                            hora = rs.getString("Hora_Venta");
                            tunit.add(rs.getDouble("TOTAL_UNITARIO"));
                            //Redondeo = rs.getDouble("Redondeo");
                            //TotFinal = rs.getDouble("TotFinal");
                        }

                        Redondeo = Double.valueOf(this.jTextField5.getText());
                        TotFinal = Double.valueOf(this.jTextField6.getText());
                        opigv = Double.valueOf(this.txtigv.getText());
                        MiTotal = Double.valueOf(this.txttotal.getText());
                        //opgravada = 0.0;
                        
                        BigDecimal bdopgravada = new BigDecimal(opgravada);
                        BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                        BigDecimal bdigv = new BigDecimal(opigv);
                        BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                        BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                        BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                        BigDecimal bdMytotal = new BigDecimal(MiTotal);

                        bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opgravada = bdopgravada.doubleValue();
                        String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opinafecta = bdopinafecta.doubleValue();
                        String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opigv = bdigv.doubleValue();
                        String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opdescuento = bdopdescuento.doubleValue();
                        String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        Redondeo = bdopredondeo.doubleValue();
                        String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        TotFinal = bdoptotfinal.doubleValue();
                        String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        bdMytotal = bdMytotal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        MiTotal  = bdMytotal.doubleValue();
                        String MyTotalFinal = bdMytotal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        
                        String StrTipoDoc = LeerLoadInterno(Interno);
                        String delimiter = "-"; // /
                        String delimiter1 = "-";
                        String[] temp;
                        String[] temp1;
                        temp = fecha.split(delimiter);
                        temp1 = StrTipoDoc.split(delimiter1);

                        String leerfecha = temp[2]+temp[1]+temp[0];
                        String leerStrTipoDoc = temp1[0];
                        String leerNumero = temp1[1];

                        String LeerNuevaRutaXML = "/mnt/XML/" + leerfecha + "/" + idbotica + "/";
                        //String LeerNuevaRutaXML = "D:\\XML\\" + leerfecha + "\\" + idbotica + "\\";
                        String LeerMyFile = RUC + '-' + leerStrTipoDoc + '-' + serie + '-' + leerNumero;

                        objXML_GENERAR = new XML_GENERAR();
                        String XMLGENERADO = objXML_GENERAR.readXML(Interno,LeerNuevaRutaXML,LeerMyFile);

                        String FechaVtaPrint = this.fechavta.getText();
                        String delimiterPrint = "-";
                        String[] temp3;
                        temp3 = FechaVtaPrint.split(delimiterPrint);
                        String leerfechaprint = temp3[2]+"/"+temp3[1]+"/"+temp3[0];

                        String[] temp4;
                        temp4 = listaDatosVtaPadre.get(0).getFecha1().split(delimiterPrint);
                        String leerfechavta = temp4[2]+"/"+temp4[1]+"/"+temp4[0];
                        
/*Modo impresion*/ //termica
if (modoImpresionDoc.equals("1")){

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        ConvTotaLetras = NumLetra.Convertir(optotfinal, true,idmoneda);
                        //String cadenaPDF417 = rucbarcode.trim()+"|"+tipdocbarcode.trim()+"|"+igvbarcode.trim()+"|"+totalbarcode.trim()+"|"+tipodocadquiriente.trim()+"|"+numerodocadquiriente.trim()+"|"+firmabarcode.trim();

                        /*imprimeBoletaNueva(Interno,idbotica,ConvTotaLetras,lista_VentaFinal,cadenaPDF417/*,
                          rucbarcode,tipdocbarcode,serie,numero,igvbarcode,totalbarcode,fecha,tipodocadquiriente,
                          numerodocadquiriente,firmabarcode);*/
                        
                        imprimeBoleta(Interno,idbotica,ConvTotaLetras);

                        /*
                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC :" + RUC);
                        Dibuja_Linea(ps1);
                        ps1.println("        NOTA DE CREDITO ELECTRONICA ");
                        ps1.println("          " + obj.getSerie() + " - " + obj.getNumero());
                        Dibuja_Linea(ps1);                        
                        ps1.println("Fecha :    " + leerfechaprint + "  Hora : " + hora);
                        ps1.println("Caj   :    " + cajero + " Ven : " + salesman + " Int : " + Interno);
                        
                        String DOC ="";
                        if (this.txttipoventa.getText().equals("NOTA CREDITO ELECTRONICA FACTURA") || this.txttipoventa.getText().equals("FACTURA")){
                            DOC = listaDatosVtaPadre.get(0).getRUC();
                        }else{
                            DOC = listaDatosVtaPadre.get(0).getDNI();
                        }
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     :" + cliente);
                        ps1.println("DIRECCION :" + direccion);
                        ps1.println("RUC       :" + DOC);
                        Dibuja_Linea(ps1);
                        ps1.println("Motivo de Emision: " + obs);
                        ps1.println("Documento que Modifica :" + listaDatosVtaPadre.get(0).getDescripcionTipoPago());
                        ps1.println("Serie/Nro :" + listaDatosVtaPadre.get(0).getSerie() + "-" + listaDatosVtaPadre.get(0).getNumero());
                        ps1.println("Fecha :" + leerfechavta);
                        Dibuja_Linea(ps1);
                        ps1.println("Cant     " + "Descripcion" + "                     " + "Importe");
                        Dibuja_Linea(ps1);
                        lineas = 7;

                        String Fusionado = "";
                        Align1 formato = new Align1(15, Align1.JUST_RIGHT,null);

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */
                    
                        //for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE NC
                        /*for (Integer sll = 0; sll < listaVtasDetalle.size(); sll++) {
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            //String sss = ProductoDescripcion.get(sll).toString();
                            //String sss = listaVtasDetalle.get(sll).getId_Producto().toString();
                            String sss = listaVtasDetalle.get(sll).getDescr_Producto();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 3; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            //String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));
                            String Lavtadettotal = MiFormato.format(listaVtasDetalle.get(sll).getTotal_producto());

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = listaVtasDetalle.get(sll).getDescr_Producto().length();
                            if (Largo > 25) {
                                Np = listaVtasDetalle.get(sll).getDescr_Producto().substring(0, Largo); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 30; lpp++) { //29
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                //Np = Np + "\n" + espacio + listaVtasDetalle.get(sll).getDescr_Producto().substring(24, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/
                                
                            /*} else {
                                Np = listaVtasDetalle.get(sll).getDescr_Producto().trim();

                                for (Integer cor = listaVtasDetalle.get(sll).getDescr_Producto().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 29; lpp++) {
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/
                            //}

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            //ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);
                            
                            /*BigDecimal bdT = new BigDecimal(Double.valueOf(Lavtadettotal.replace(",", ".")));
                            ps1.println(Fusionado + "  " + sss + " " + bdT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                             *
                             */

                       /* }

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";

                        //ps1.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;

                        Align1 formato1 = new Align1(28, Align1.JUST_RIGHT,null);

                        Dibuja_Linea(ps1);
                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("TOTAL         : S./ " + formato1.format(MyTotalFinal));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + formato1.format(opdcto));
                        }
                        ps1.println();
                        String ultimo = "              ";                        
                        ps1.println(ultimo);
                        ps1.println(" "+NumLetra.Convertir(optotfinal, true));
                        ps1.println(" ");
                        ps1.println("     " + XMLGENERADO);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                        ps1.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                        ps1.println("www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                        ps1.println("ESTIMADO CLIENTE:");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                        ps1.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                        ps1.println("NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");

                        correr(6, ps1); //9
                        cortar(ps1);
                        */

}else{  // ticketera

                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC :" + RUC);
                        Dibuja_Linea1(ps1);
                        ps1.println("        NOTA DE CREDITO ELECTRONICA ");
                        ps1.println("          " + obj.getSerie() + " - " + obj.getNumero());
                        Dibuja_Linea1(ps1);
                        ps1.println("Fecha : " + leerfechaprint + "  Hora : " + hora);
                        ps1.println("Caj   : " + cajero + " Ven : " + salesman + " Int : " + Interno);

                        String DOC ="";
                        if (this.txttipoventa.getText().equals("NOTA CREDITO ELECTRONICA FACTURA") || this.txttipoventa.getText().equals("FACTURA")){
                            DOC = listaDatosVtaPadre.get(0).getRUC();
                        }else{
                            DOC = listaDatosVtaPadre.get(0).getDNI();
                        }
                        Dibuja_Linea1(ps1);
                        ps1.println("Sr(a)    :" + cliente);
                        ps1.println("DIRECCION:" + direccion);
                        ps1.println("RUC      :" + DOC);
                        Dibuja_Linea1(ps1);
                        ps1.println("Motivo de Emision: " + obs);
                        ps1.println("Documento que Modifica :" + listaDatosVtaPadre.get(0).getDescripcionTipoPago());
                        ps1.println("Serie/Nro :" + listaDatosVtaPadre.get(0).getSerie() + "-" + listaDatosVtaPadre.get(0).getNumero());
                        ps1.println("Fecha :" + leerfechavta);
                        Dibuja_Linea1(ps1);
                        ps1.println("Cant     " + "Descripcion" + "        " + "Importe");
                        Dibuja_Linea1(ps1);
                        lineas = 7;

                        String Fusionado = "";
                        Align1 formato = new Align1(15, Align1.JUST_RIGHT,null);

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */

                        //for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE NC
                        for (Integer sll = 0; sll < listaVtasDetalle.size(); sll++) {
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            //String sss = ProductoDescripcion.get(sll).toString();
                            //String sss = listaVtasDetalle.get(sll).getId_Producto().toString();
                            String sss = listaVtasDetalle.get(sll).getDescr_Producto();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            //String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));
                            String Lavtadettotal = MiFormato.format(listaVtasDetalle.get(sll).getTotal_producto());

                            if (Lavtadettotal.length() < 7) {
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = listaVtasDetalle.get(sll).getDescr_Producto().length();
                            if (Largo > 25) {
                                Np = listaVtasDetalle.get(sll).getDescr_Producto().substring(0, 26); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                Np = Np + "\n" + espacio + listaVtasDetalle.get(sll).getDescr_Producto().substring(26, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/
                                
                            } else {
                                Np = listaVtasDetalle.get(sll).getDescr_Producto().trim();

                                for (Integer cor = listaVtasDetalle.get(sll).getDescr_Producto().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) {
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);

                            /*BigDecimal bdT = new BigDecimal(Double.valueOf(Lavtadettotal.replace(",", ".")));
                            ps1.println(Fusionado + "  " + sss + " " + bdT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                             *
                             */

                        }

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";

                        //ps1.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;
                        /*
                        Align1 formato1 = new Align1(15, Align1.JUST_RIGHT,null);

                        Dibuja_Linea1(ps1);
                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("TOTAL         : S./ " + formato1.format(MyTotalFinal));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + formato1.format(opdcto));
                        }
                        ps1.println();
                        String ultimo = "              ";
                        ps1.println(ultimo);
                        ps1.println(" "+NumLetra.Convertir(optotfinal, true));
                        ps1.println(" ");
                        ps1.println("     " + XMLGENERADO);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                                   + "VENTA ELECTRONICA PUEDE SER");
                        ps1.println("CONSULTADO EN www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                        ps1.println("ESTIMADO CLIENTE: ");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                                   + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                                   + "NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(8, ps1); //9
                        cortar(ps1);*/

}/*Fin de tipo impresion*/

                        //ps1.close();
                        //rs.close();
                        rs.close();
                        //rs2.close();
                        //listaInternos.set(i, null);

                        ProductoDescripcion.removeAll(ProductoDescripcion);
                        Labs.removeAll(Labs);
                        PrecioF.removeAll(PrecioF);
                        Precio.removeAll(Precio);
                        CantidadU.removeAll(CantidadU);
                        CantidadF.removeAll(CantidadF);

                        for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }

                        ps1.close();
                        conex.close();
                        //listaInternos.set(i, null);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora("Error Impresora FileNotFoundException catch" + ex.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora("Error Impresora IOException catch" + et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception  catch :" + pe.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU DOCUMENTO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void imprimeBoletaNueva(String lstInterno,String idbotica, String ConvTotLetras,List<Venta> lstVentaFinal,
            String cadena/*,
            String rucbarcode1, String tipdocbarcode1,String seriebardcode, String numerobarcode,String igvbarcode1,
            String totalbarcode1, String fechabarcode, String tipodocadquiriente1, String numerodocadquiriente1,
            String firmabarcode1*/){

        String sistema = "Windows";
        conex = new ConexionPool().getConnection(); 

        try {

            String miinternovta = lstInterno;
            //parameters.put("PID_NUMLETRAS", ConvTotLetras);
            //parameters.put("cadpdf417", cadena);
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            /*parameters.put("PID_RUCBARCODE", rucbarcode1);
            parameters.put("PID_TIPDOCBARCODE", tipdocbarcode1);
            parameters.put("PID_SERIEBARCODE", seriebardcode);
            parameters.put("PID_NUMEROBARCODE", numerobarcode);
            parameters.put("PID_IGVBARCODE", igvbarcode1);
            parameters.put("PID_TOTALBARCODE", totalbarcode1);
            parameters.put("PID_FECHABARCODE", fechabarcode);
            parameters.put("PID_TIPDOCADQUIRIENTE", tipodocadquiriente1);
            parameters.put("PID_NUMDOCADQUIRIENTE", numerodocadquiriente1);
            parameters.put("PID_FIRMABARCODE", firmabarcode1);*/

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVtaNuevo-OTRO.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            //jasperPrint = JasperFillManager.fillReport(masterReport, parameters, new  JRBeanCollectionDataSource(lstVentaFinal));
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);

            JasperPrintManager.printReport(jasperPrint, false);

            conex.close();

        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public void imprimeBoleta( String lstInternos,String idbotica, String ConvTotLetras) {
        String sistema = "Windows";
        conex = new ConexionPool().getConnection();
        String miinternovta = lstInternos;

        try {
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            if (obj2.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVta.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            //JasperViewer view = new JasperViewer(jasperPrint, false);

            JasperPrintManager.printReport(jasperPrint, false);

            //view.setTitle("REPORTE DE COMPROBANTES");
            //view.setVisible(true);
            //view.setSize(300, 500);

        conex.close();

        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
    
    private void Error_Impresora(String mens, List<Venta> Internos) {
        if (Internos.size() > 0) {
            FormImpresora obprimter = new FormImpresora(objventas, true, idbotica, Internos, mens);
            obprimter.show(true);
        }
    }

    public String LeerLoadInterno(String idventa) {
        String TIPODOCSUNAT = "" ;

        try {

            //conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call XML_LOAD_INTERNO (?) }");
            procedure.setString("PId_Venta", idventa);

            rs = procedure.executeQuery();

            while (rs.next()) {
                 TIPODOCSUNAT =  rs.getString("ID_TIPO_DOCUMENTO_SUNAT")+"-"+rs.getString("Numero");
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return TIPODOCSUNAT;
    }

    private void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println("-------------------------------------------------");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void Dibuja_Linea1(PrintWriter ps) {
        try {
            ps.println(" " + "------------------------------------"); //36
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public class Numero_a_Letra {

        private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco", "seis", "siete", "ocho", "nueve"};
        private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte y ", "treinta y ", "cuarenta y ",
            "cincuenta y ", "sesenta y ", "setenta y ", "ochenta y ", "noventa y "};
        private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

        public Numero_a_Letra() {
        }

        public String Convertir(String numero, boolean mayusculas, String money) {

            // System.out.println(">Dentro de la clase<:"+numero);

            String literal = "";
            String parte_decimal;
            String desmoney = "";
            //si el numero utiliza (.) en lugar de (,) -> se reemplaza
            //XXX numero = numero.replace(",", ".");
            //XXX System.out.println(">Dentro de la 2<:"+numero);
            numero = numero.replace(".", ",");
            // System.out.println(">Dentro de la 2.1<:"+numero);
            //si el numero no tiene parte decimal, se le agrega ,00
            if (numero.indexOf(",") == -1) {
                numero = numero + ",00";
            }
            //se valida formato de entrada -> 0,00 y 999 999 999,00
            if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
                //se divide el numero 0000000,00 -> entero y decimal
                String Num[] = numero.split(",");
                //de da formato al numero decimal
                if (money.equals("1")){
                    desmoney = "Soles.";
                }else{
                    desmoney = "Dolares.";
                }

                parte_decimal = " con " + Num[1] + "/100 " + desmoney;
                //se convierte el numero a literal
                if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                    literal = "Cero ";
                } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                    literal = getMillones(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                    literal = getMiles(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 99) {//si es centena

                    if (Integer.parseInt(Num[1]) < 1 || Num[0].substring(Num[0].length()-2,2).trim().equals("y")) {
                        literal =  getCentenas(Num[0]).substring(0,getCentenas(Num[0]).length()-2 );
                    }else{
                    literal = getCentenas(Num[0]);
                    }
                } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                    literal = getDecenas(Num[0]);

                } else {//sino unidades -> 9
                    literal = getUnidades(Num[0]);
                }
                //devuelve el resultado en mayusculas o minusculas
                if (mayusculas) {
                    return (literal + parte_decimal).toUpperCase();
                } else {
                    return (literal + parte_decimal);
                }
            } else {//error, no se puede convertir
                return literal = null;
            }
        }

        /* funciones para convertir los numeros a literales */
        private String getUnidades(String numero) {// 1 - 9
            //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
            String num = numero.substring(numero.length() - 1);
            return UNIDADES[Integer.parseInt(num)];
        }

        private String getDecenas(String num) {// 99
            int n = Integer.parseInt(num);
            if (n < 10) {//para casos como -> 01 - 09
                return getUnidades(num);
            } else if (n > 19) {//para 20...99
                String u = getUnidades(num);
                if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
                } else {
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + " " + u;
                }
            } else {//numeros entre 11 y 19
                return DECENAS[n - 10];
            }
        }

        private String getCentenas(String num) {// 999 o 099
            if (Integer.parseInt(num) > 99) {//es centena
                if (Integer.parseInt(num) == 100) {//caso especial
                    return " cien ";
                } else {
                    return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
                }
            } else {//por Ej. 099
                //se quita el 0 antes de convertir a decenas
                return getDecenas(Integer.parseInt(num) + "");
            }
        }

        private String getMiles(String numero) {// 999 999
            //obtiene las centenas
            String c = numero.substring(numero.length() - 3);
            //obtiene los miles
            String m = numero.substring(0, numero.length() - 3);
            String n = "";
            //se comprueba que miles tenga valor entero
            if (Integer.parseInt(m) > 0) {
                n = getCentenas(m);
                return n + "mil " + getCentenas(c);
            } else {
                return "" + getCentenas(c);
            }
        }

        private String getMillones(String numero) { //000 000 000
            //se obtiene los miles
            String miles = numero.substring(numero.length() - 6);
            //se obtiene los millones
            String millon = numero.substring(0, numero.length() - 6);
            String n = "";
            if (millon.length() > 1) {
                n = getCentenas(millon) + "millones ";
            } else {
                n = getUnidades(millon) + "millon ";
            }
            return n + getMiles(miles);
        }
    }


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        BuscaInterno();
    }//GEN-LAST:event_jTextField1ActionPerformed

        
    private void tipo_documentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_documentoItemStateChanged
        if (this.tipo_documento.getSelectedIndex() > 0) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getModificable() == 1) {                    
                    Recupera_Numeracion(tipo_documento.getSelectedItem().toString().trim());
                    jTextField1.requestFocus();
                } else {
                    tipo_documento.setSelectedIndex(0);
                    jTextField31.setText("");
                    JOptionPane.showMessageDialog(this, "Lo sentimos no puede Corregir este Tipo de Documento ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }//GEN-LAST:event_tipo_documentoItemStateChanged

    private void Recupera_Numeracion(String Documento) {
        int posi;

        if (this.jComboBox1.getItemCount() > 1) {
            posi = this.jComboBox1.getSelectedIndex() - 1;
        } else {
            posi = this.jComboBox1.getSelectedIndex();
        }

        tipoventa = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();
        listaPagos.removeAll(listaPagos);
        listaPagos = objlogicaPagos.RecuperaSerieNumeroReimprime(idbotica, listacajas.get(posi).getIdcaja(), tipoventa);

        if (listaPagos.size() > 0) {
            serie = listaPagos.get(0).getSerie();
            //mnumero = listaPagos.get(0).getNumero();
            this.jTextField31.setText(serie);
        }

    }
    private void tipo_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_documentoActionPerformed

}//GEN-LAST:event_tipo_documentoActionPerformed

    private void Limpia() {
        this.jTextField1.setText("");
        this.txtserie.setText("");
        this.txtnumero.setText("");
        this.txtcajero.setText("");
        this.txtsubtotal.setText("");
        this.txttipoventa.setText("");
        this.txttotal.setText("");
        this.txtvendedor.setText("");
        this.txtigv.setText("");
    }

    private void CargarProductos() {
        /*String Interno = jTextField1.getText().trim().toUpperCase();
        obj = new Venta(idbotica, Interno);*/
        String NDOC1 = jTextField1.getText().trim();
        /**/
        int posi2;

        if (this.jComboBox1.getItemCount() > 1) {
           posi2 = this.jComboBox1.getSelectedIndex() - 1;
        } else {
           posi2 = this.jComboBox1.getSelectedIndex();
        }
        Rectipvta = listatipoventas.get(tipo_documento.getSelectedIndex() - 1).getId_Tipo_Venta();
        //listaPagos.removeAll(listaPagos);
        //listaPagos = objlogicaPagos.RecuperaSerieNumeroReimprime(idbotica, listacajas.get(posi1).getIdcaja(), tipoventa);
        /**/
        int opc=1;
        int opc1=1;
        int opc2=1;
        String serie = this.jTextField31.getText().trim().toUpperCase();
        obj = new Venta(idbotica, NDOC1,listacajas.get(posi2).getIdcaja(),Rectipvta,opc,serie);

        listaVenta = logventa.ReimprimeListaVenta_Detalle(obj);        

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

    private void LimpiatTabla() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fechavta;
    private javax.swing.JTextField idvta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox tipo_documento;
    private javax.swing.JTextField txtcajero;
    private javax.swing.JTextField txtigv;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttipoventa;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
