/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Descargos1.java
 *
 * Created on 30/01/2012, 07:54:54 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaMovimientos;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProveedor;
import CapaLogica.LogicaTipoMovimiento;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import entidad.Boticas;
import entidad.Clientes;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import entidad.Producto;
import entidad.Productos_Botica;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import CapaLogica.LogicaClientes;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.lang.Math;
import static java.lang.String.format;
import java.util.Formatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class Descargos_Cli extends javax.swing.JInternalFrame {

    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaMovimientos logdata = new LogicaMovimientos();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    private List<Guias> listaguias = new ArrayList<Guias>();
    private List<Guias> ActualizaNumero = new ArrayList<Guias>();
    LogicaClientes objClientes = new LogicaClientes();
    List<TipoMovimiento> listaTipoMovimiento;
    List<Movimientos> listaSerieNumero;
    List<Proveedor> listaProveedor;
    DefaultTableModel modeloTabla = new DefaultTableModel();
    List<Varios> Stocks = new ArrayList<Varios>();
    Guias entidadGuias = new Guias();
    Producto entidadProductos = new Producto();
    MuestraVentana objetoventana = new MuestraVentana();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido1 = false;
    AgregaProductosCarDes objrelew;
    public static String ProductoRecibido;
    OpcionesMenu objet;
    LogicaPersonal objPersonal = new LogicaPersonal();
    List<Boticas> listaboticas = new ArrayList<Boticas>();
    List<Boticas> listaboticas2 = new ArrayList<Boticas>();
    LogicaConfiguracion objConfiguracion;
    Inventarios objinventario;
    FormClientes objcliente;
    FormTransportista objTranspor;
    private String Impresora = "";
    private String ImpresoraDesc="";
    private String idbotica;
    private int limiteguia;
    private int unidad, fraccion;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    private int stockempaque;
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    FormProveedores formproveedor;
    private String idproveedor;
    Integer a = 0;
    Integer totalLetras = 0;
    Integer enterito = 0;
    String compuesto ="";
    Integer fraccionado = 0;
    Integer valido = 0;
    String seleccionado;
    String TipoX = "";
    String FechaX = "";
    String MovimientoX = "";
    String AlmacenX = "";
    String x = "";
    Integer bandera = 0;
    private String MiSerie;
    private String MiNumero;
    JFrame ventana;
    Inventarios objinventar;

    /** Creates new form Descargos1 */
    public Descargos_Cli(java.awt.Frame parent, Inventarios obj, OpcionesMenu objeto) {
        initComponents();
        objet = objeto;
        objinventar = obj;        
        Impresora = objet.getImpresora_Factura();
        ImpresoraDesc = objet.getImpresora_Descargo();
        idbotica = objet.getIdbotica();
        modeloTabla = new ModeloTabla();
        AparienciaTabla();
        construirGuia();
        cargarBoticas2();
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        jTextField1.setVisible(false);
        jCheckBox1.setVisible(false);
        nrodocumento1.setDocument(new LimitadorLetras(nrodocumento1, 5));
        nrodocumento.setDocument(new LimitadorLetras(nrodocumento, 12));
        jTextField13.setEnabled(false);
        jTextField14.setEnabled(false);
        jTextField15.setEditable(false);

    }

    public Descargos_Cli(java.awt.Frame parent, Inventarios obj) {
        initComponents();
        objinventar = obj;
        modeloTabla = new ModeloTabla();
        AparienciaTabla();
        construirGuia();
        cargarBoticas2();
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        this.jTextField1.setVisible(false);
        nrodocumento1.setDocument(new LimitadorLetras(nrodocumento1, 5));
        nrodocumento.setDocument(new LimitadorLetras(nrodocumento, 12));
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    private void cargarBoticas2() {

        try {

            listaboticas2 = objBoticas.DescripcionBoticaDefault();
            lasboticas1.addItem(listaboticas2.get(0).getId_Botica());

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objet.getIdpersonal_botica()), 1) == 1) {
                /*Si es Controlado INVENTARIOS  - Es cargo*/
                lasboticas2.setVisible(false);
                this.jLabel4.setVisible(false);
                operaciones.setVisible(true);
                operaciones.setEnabled(true);
                listaTipoMovimiento = objTipoMovimiento.ListarMovimientosPorRol(objPersonal.RecuperaRol(objet.getIdpersonal_botica()), 0);
                operaciones.addItem("Seleccion Opcion");
                verificaAlmacenes();
                for (Integer ao = 0; ao < listaTipoMovimiento.size(); ao++) {
                    operaciones.addItem(listaTipoMovimiento.get(ao).getDescripcionMovimiento());
                }

            } else {
                /*Si es Controlado por Boticas - Es cargo*/
                verificaAlmacenes();
                this.jLabel9.setVisible(false);
                this.jLabel4.setVisible(false);
                lasboticas2.setVisible(false);
                operaciones.setVisible(false);
                listaboticas = objBoticas.ListarBoticasMiZona();
                lasboticas2.addItem("Seleccione Opcion");
                for (Integer ao = 0; ao < listaboticas.size(); ao++) {
                    lasboticas2.addItem(listaboticas.get(ao).getDescripcion());
                }
            }
        } catch (Exception ex) {
            System.out.println("05" + ex.getMessage());
        }
        jCheckBox1.setSelected(true);

    }

    private void verificaAlmacenes() {

        if (objBoticas.VerificaPoseeAlmacenes(this.idbotica)) {
            jComboBox2.setEnabled(true);
        } else {
            jComboBox2.setEnabled(false);
        }
    }

    private void limpiarTodo(Boolean valor) {
        try {
            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objet.getIdpersonal_botica()), 1) == 1) {
                operaciones.setSelectedIndex(0);
            } else {
                lasboticas2.setSelectedIndex(0);
            }

            nrodocumento.setText("");
            nrodocumento.setEnabled(valor);
            nrodocumento1.setText("");
            nrodocumento1.setEnabled(valor);
            lasboticas2.setEnabled(valor);
            observ.setEnabled(valor);
            observ.setText("");

            for (Integer i = 0; i < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(i);
            }

            jButton7.setEnabled(valor);
            jButton8.setEnabled(valor);
            jButton9.setEnabled(valor);            
            GenerarGuia.setEnabled(valor);
            jButton2.setEnabled(valor);
            jButton4.setEnabled(!valor);
            fechaDocumento.setEnabled(valor);
            fechaRecepcion.setEnabled(valor);
            operaciones.setEnabled(valor);
            jButton5.setEnabled(valor);
            jTextField13.setEnabled(valor);
            jTextField14.setEnabled(valor);
            

        } catch (Exception ex) {
            System.out.println("LimpiarTOdo:" + ex.getMessage());
        }

    }

    private void cargarBoticas() {

        listaTipoMovimiento = objTipoMovimiento.ListarTipoMovimientoFiltradoDescargo(0);
        lasboticas2.addItem("Seleccione Opcion");

        for (int i = 0; i < listaTipoMovimiento.size(); i++) {
            lasboticas2.addItem(listaTipoMovimiento.get(i).getDescripcionMovimiento());
        }

    }

    private void construirGuia() {

        modeloTabla.addColumn("CODIGO");
        modeloTabla.addColumn("PRODUCTO");
        modeloTabla.addColumn("LAB");
        modeloTabla.addColumn("TOTAL");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("MONTO");
        modeloTabla.addColumn("PVP");
        modeloTabla.addColumn("DSCTO");
        modeloTabla.addColumn("LOTE");
        modeloTabla.addColumn("FECHA VCTO");
        modeloTabla.addColumn("R.S/NSO");

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
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
        
        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setCellRenderer(new ColoredTableCellRenderer());
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);
        
        columnaTabla = jTable1.getColumnModel().getColumn(9);
        columnaTabla.setCellRenderer(new ColoredTableCellRenderer());
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);
        
        columnaTabla = jTable1.getColumnModel().getColumn(10);
        columnaTabla.setCellRenderer(new ColoredTableCellRenderer());
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            if (column == 8 || column == 9 || column == 10) {
                return true;
            }

            return false;
        }
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    private String[] ArrayBoticas = {};

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        GenerarGuia = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lasboticas1 = new javax.swing.JComboBox();
        lasboticas2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        operaciones = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        nrodocumento1 = new javax.swing.JTextField();
        nrodocumento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        fechaDocumento = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        fechaRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observ = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Descargos_Cli.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
            jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
            jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
            jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        }

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setForeground(resourceMap.getColor("jLabel21.foreground")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jButton4.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        GenerarGuia.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        GenerarGuia.setText(resourceMap.getString("GenerarGuia.text")); // NOI18N
        GenerarGuia.setActionCommand(resourceMap.getString("GenerarGuia.actionCommand")); // NOI18N
        GenerarGuia.setEnabled(false);
        GenerarGuia.setName("GenerarGuia"); // NOI18N
        GenerarGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarGuiaActionPerformed(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setActionCommand(resourceMap.getString("jButton5.actionCommand")); // NOI18N
        jButton5.setEnabled(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(551, 551, 551)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GenerarGuia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenerarGuia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        lasboticas1.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas1.setEnabled(false);
        lasboticas1.setName("lasboticas1"); // NOI18N
        lasboticas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas1ActionPerformed(evt);
            }
        });

        lasboticas2.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas2.setEnabled(false);
        lasboticas2.setName("lasboticas2"); // NOI18N
        lasboticas2.setPreferredSize(new java.awt.Dimension(230, 20));
        lasboticas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas2ActionPerformed(evt);
            }
        });
        lasboticas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lasboticas2KeyPressed(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        operaciones.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        operaciones.setEnabled(false);
        operaciones.setName("operaciones"); // NOI18N
        operaciones.setPreferredSize(new java.awt.Dimension(230, 20));
        operaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                operacionesItemStateChanged(evt);
            }
        });
        operaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                operacionesKeyPressed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(operaciones, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lasboticas2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lasboticas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setName("jPanel5"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setToolTipText(resourceMap.getString("jTextField2.toolTipText")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setEditable(false);
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setToolTipText(resourceMap.getString("jTextField3.toolTipText")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setEnabled(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen" }));
        jComboBox2.setEnabled(false);
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        nrodocumento1.setBackground(resourceMap.getColor("nrodocumento1.background")); // NOI18N
        nrodocumento1.setFont(resourceMap.getFont("nrodocumento1.font")); // NOI18N
        nrodocumento1.setForeground(resourceMap.getColor("nrodocumento1.foreground")); // NOI18N
        nrodocumento1.setEnabled(false);
        nrodocumento1.setName("nrodocumento1"); // NOI18N
        nrodocumento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nrodocumento1ActionPerformed(evt);
            }
        });
        nrodocumento1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrodocumento1FocusLost(evt);
            }
        });
        nrodocumento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nrodocumento1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nrodocumento1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrodocumento1KeyTyped(evt);
            }
        });

        nrodocumento.setBackground(resourceMap.getColor("nrodocumento.background")); // NOI18N
        nrodocumento.setFont(resourceMap.getFont("nrodocumento1.font")); // NOI18N
        nrodocumento.setForeground(resourceMap.getColor("nrodocumento.foreground")); // NOI18N
        nrodocumento.setEnabled(false);
        nrodocumento.setName("nrodocumento"); // NOI18N
        nrodocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nrodocumentoActionPerformed(evt);
            }
        });
        nrodocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrodocumentoFocusLost(evt);
            }
        });
        nrodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nrodocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrodocumentoKeyTyped(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(nrodocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrodocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        lblFecha.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        lblFecha.setForeground(resourceMap.getColor("lblFecha.foreground")); // NOI18N
        lblFecha.setText(resourceMap.getString("lblFecha.text")); // NOI18N
        lblFecha.setName("lblFecha"); // NOI18N

        fechaDocumento.setFormats(new String[] { "dd/M/yyyy" });
        fechaDocumento.setName("fechaDocumento"); // NOI18N
        fechaDocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fechaDocumentoFocusLost(evt);
            }
        });
        fechaDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaDocumentoActionPerformed(evt);
            }
        });
        fechaDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaDocumentoKeyPressed(evt);
            }
        });

        jLabel7.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        fechaRecepcion.setFormats(new String[] { "dd/M/yyyy" });
        fechaRecepcion.setName("fechaRecepcion"); // NOI18N
        fechaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fechaRecepcionKeyPressed(evt);
            }
        });

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        observ.setColumns(20);
        observ.setFont(resourceMap.getFont("observ.font")); // NOI18N
        observ.setRows(5);
        observ.setEnabled(false);
        observ.setName("observ"); // NOI18N
        observ.setPreferredSize(new java.awt.Dimension(224, 92));
        observ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                observKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(observ);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblFecha)
                    .addComponent(jLabel1)
                    .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setToolTipText(resourceMap.getString("jTextField4.toolTipText")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setToolTipText(resourceMap.getString("jTextField5.toolTipText")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField6.setEditable(false);
        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setToolTipText(resourceMap.getString("jTextField6.toolTipText")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jTextField10.setEditable(false);
        jTextField10.setForeground(resourceMap.getColor("jTextField10.foreground")); // NOI18N
        jTextField10.setToolTipText(resourceMap.getString("jTextField10.toolTipText")); // NOI18N
        jTextField10.setName("jTextField10"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel8.setName("jPanel8"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setToolTipText(resourceMap.getString("jTextField7.toolTipText")); // NOI18N
        jTextField7.setName("jTextField7"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setToolTipText(resourceMap.getString("jTextField8.toolTipText")); // NOI18N
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setEnabled(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel10.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setToolTipText(resourceMap.getString("jTextField9.toolTipText")); // NOI18N
        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9MouseClicked(evt);
            }
        });

        jLabel16.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel16.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jTextField11.setEditable(false);
        jTextField11.setForeground(resourceMap.getColor("jTextField11.foreground")); // NOI18N
        jTextField11.setToolTipText(resourceMap.getString("jTextField11.toolTipText")); // NOI18N
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });

        jLabel17.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel17.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jTextField12.setEditable(false);
        jTextField12.setForeground(resourceMap.getColor("jTextField12.foreground")); // NOI18N
        jTextField12.setToolTipText(resourceMap.getString("jTextField12.toolTipText")); // NOI18N
        jTextField12.setName("jTextField12"); // NOI18N
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setForeground(resourceMap.getColor("jLabel20.foreground")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jTextField13.setForeground(resourceMap.getColor("jTextField13.foreground")); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setToolTipText(resourceMap.getString("jTextField13.toolTipText")); // NOI18N
        jTextField13.setName("jTextField13"); // NOI18N
        jTextField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField13MouseClicked(evt);
            }
        });

        jTextField14.setForeground(resourceMap.getColor("jTextField14.foreground")); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setToolTipText(resourceMap.getString("jTextField14.toolTipText")); // NOI18N
        jTextField14.setName("jTextField14"); // NOI18N
        jTextField14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField14MouseClicked(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setBackground(resourceMap.getColor("jTextField15.background")); // NOI18N
        jTextField15.setFont(resourceMap.getFont("jTextField15.font")); // NOI18N
        jTextField15.setForeground(resourceMap.getColor("jTextField15.foreground")); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setToolTipText(resourceMap.getString("jTextField15.toolTipText")); // NOI18N
        jTextField15.setName("jTextField15"); // NOI18N
        jTextField15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField14)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jCheckBox1)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Verifica_Cantidad() {
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            JOptionPane p = new JOptionPane();
            String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

            try {

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            cantidad1 = cantidad1.toUpperCase();

                            String idproducto = this.jTable1.getValueAt(fila, 0).toString().trim();
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidad(cantidad1);

                            if (unidad == 0 && fraccion == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccion > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {

                                    if (resul) {

                                        if (VerificaStock(idproducto)) {
                                            jTable1.setValueAt(cantidad1, fila, 3);
                                            calculoMontos();
                                            MiTotal();
                                        } else {
                                            JOptionPane.showMessageDialog(this, " Producto no cuenta con ese Stock  ", "Error", JOptionPane.ERROR_MESSAGE);
                                        }


                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();

        try {

            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, jComboBox2.getSelectedItem().toString());

            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = empaque;

                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }

                //RECUPERO MI STOCK DEL EMPAQUIE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = stockempaque;

                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = stockfraccion;

                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unidad * empaque_tmp + fraccion;

                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }

        return resultado;

    }

    public void AsigaProductos(List<Movimiento_Detalle> listaDetalle) {

        this.jTextField15.setText(listaDetalle.get(0).getIdventa());
        for (int i = 0; i < listaDetalle.size(); i++) {

            String cantidad;
            if (listaDetalle.get(i).getFraccion() == 0) {
                cantidad = String.valueOf(listaDetalle.get(i).getUnidad());
            } else {
                cantidad = String.valueOf(listaDetalle.get(i).getUnidad()) + "F" + listaDetalle.get(i).getFraccion();
            }

            Object[][] data = {
                {
                    listaDetalle.get(i).getId_Producto().getIdProducto(),//codigo producto
                    listaDetalle.get(i).getId_Producto().getDescripcion(),//producto
                    listaDetalle.get(i).getId_Producto().getLaboratorio().getId_Lab(),//laboratorio
                    cantidad,//cantidad pedida
                    listaDetalle.get(i).getPrecio_Venta_Final(),//PVPF
                    listaDetalle.get(i).getTotal(),
                    listaDetalle.get(i).getPrecio_Venta(),//PV
                    listaDetalle.get(i).getDescuento(),//descuento
                    "","",""
                }
            };

            modeloTabla.addRow(data[0]);

        }

        jTable1.setModel(modeloTabla);
        MiTotal();
    }


    /*
     * METODO QUE CALCULA SI LO INGRESADO ES UNIDAD Y FRACCION
     */
    private boolean VerificaCantidad(String cantidad) {
        String unidad1 = "";
        String fraccion1 = "";
        int k = 0;
        boolean segundo = false;
        char car = 'F';
        int cantdef = 0;
        String valor = "";

        cantidad = cantidad.trim();
        Character w;
        valor = valor.trim();

        for (int i = 0; i < cantidad.length(); i++) {
            w = cantidad.charAt(i);
            if (w.isDigit(w) || w.isLetter(w)) {
                valor = valor + w;
            } else {
                return false;
            }
        }


        for (int i = 0; i < valor.length(); i++) {
            Character caracter = valor.charAt(i);

            if (caracter.isLetter(caracter)) {
                caracter = caracter.toUpperCase(caracter);
                cantdef++;
                if (caracter != car || cantdef > 1) {
                    return false;
                }

            }
            if (caracter.isDigit(caracter) && segundo == false) {
                unidad1 = unidad1 + caracter;
            } else {
                segundo = true;
                if (k == 1) {
                    if (caracter.isDigit(caracter)) {
                        fraccion1 = fraccion1 + caracter;
                    }
                }
                k = 1;
            }
        }

        if (unidad1.compareTo("") == 0) {
            unidad1 = "0";
        }
        if (fraccion1.compareTo("") == 0) {
            fraccion1 = "0";
        }

        unidad = Integer.valueOf(unidad1);
        fraccion = Integer.valueOf(fraccion1);
        return true;

    }

    private void Realiza_Opciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            if (jButton5.isEnabled()) {
                Seleccionar();
            }
        }

    }

    private void calculoMontos() {

        double parcial1 = 0;
        int fila = this.jTable1.getSelectedRow();

        try {

            String codpro = this.jTable1.getValueAt(fila, 0).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);

            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idbotica);
            double pv = objProducto.RecuperaPrecio(codpro, idbotica);

            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidad;


            if (empaque1 > 0) {
                parcial1 = (fraccion * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.jTable1.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);


        } catch (Exception ex) {
            System.out.println("calculoMontos() : " + ex.getMessage());
        }

    }

    public double redondear(double numero, double ndecimal) {
        double factor = Math.pow(10, ndecimal);
        return (Math.round(numero * factor) / factor);
    }

    private void MiTotal() {
        double total = 0;

        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            total = total + Double.parseDouble(jTable1.getValueAt(i, 5).toString());
        }

        BigDecimal bd3 = new BigDecimal(total);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd3.doubleValue();

        this.jTextField1.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void Seleccionar() {
        
        String cadena = jTextField4.getText().trim();
    
        if ( (cadena != null) && (!cadena.equals("")) ) {

            BuscarVentasRealizadas objform = new BuscarVentasRealizadas(ventana, this, true,Integer.parseInt(jTextField4.getText()),jTextField5.getText().toString().trim().toUpperCase());
            objform.pack();
            objform.setVisible(true);

        }else{
            JOptionPane.showMessageDialog(null,"PORFAVOR SELECCIONE AL CLIENTE DE LA VENTA", "OK",JOptionPane.ERROR_MESSAGE);
            jButton8.requestFocus();
        }
        
    }
    /*private void Seleccionar() {

        Integer encontrado = 0;
        Ventana obj = new Ventana();
        objrelew = new AgregaProductosCarDes(obj, objet.getIdbotica(), jComboBox2.getSelectedItem().toString(), 0,"0");
        objrelew.pack();
        objrelew.show(true);

        for (Integer a = 0; a < modeloTabla.getRowCount(); a++) {
            if (AgregaProductosCarDes.getReal().compareTo(modeloTabla.getValueAt(a, 0).toString()) == 0) {
                encontrado = 1;
            }
        }

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().length() > 0) {
                unidad = AgregaProductosCarDes.getUnidad();
                fraccion = AgregaProductosCarDes.getFraccion();

                if (unidad != -1 && fraccion != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProducto.Recupera_Empaque(codpro);

                    if (unidad == 0 && fraccion == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccion > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            Object[][] data = {
                                {
                                    AgregaProductosCarDes.getReal(),//codigo producto
                                    AgregaProductosCarDes.getRealDes(),//producto
                                    AgregaProductosCarDes.getRealLab(),//laboratorio
                                    AgregaProductosCarDes.getCantidad(),//cantidad pedida
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosCarDes.getReal(), idbotica) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idbotica)) / 100, 2),//PVPF
                                    0,
                                    AgregaProductosCarDes.getPvp(),//PV
                                    AgregaProductosCarDes.getDescto()//descuento
                                }
                            };

                            modeloTabla.addRow(data[0]);
                            jTable1.setModel(modeloTabla);
                            AgregaProductosCarDes.setReal("");
                            this.jTable1.requestFocus();
                            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);
                            calculoMontos();
                            MiTotal();

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Porfavor Ingrese Datos Correctos", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        jTable1.requestFocus();
        jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);
    }*/

    private void Defecto() {
        this.jTextField2.setText("ZZZZ");
        this.jTextField3.setText("POR CLASIFICAR");
    }

private void RECNUMERODOC(){
    String serie ="";
    String numero="";
    limiteguia=0;
    
    String idmovimirnto = "GI";
    nrodocumento1.setEditable(false);
    nrodocumento.setEditable(false);
    listaSerieNumero = logdata.Devuelve_SerieNumero(idmovimirnto);
    
    for (int ai=0; ai < listaSerieNumero.size(); ai++ ){
        
        serie = listaSerieNumero.get(ai).getSerie();
        numero = listaSerieNumero.get(ai).getNumero();
        limiteguia = listaSerieNumero.get(ai).getLimite();
    }
    
    nrodocumento1.setText(serie);
    nrodocumento.setText(numero);
    
    
    /*if (movi != null) {
        String numero = movi.getNumero_Documento();
        if (!numero.isEmpty()) {
            nrodocumento.setText(numero);
        } else {
            nrodocumento.setText("");
            nrodocumento.setEditable(true);
        }
    } else {
        nrodocumento.setText("");
        nrodocumento.setEditable(true);
    }*/
                
}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    CerrarVentana();
}//GEN-LAST:event_jButton1ActionPerformed

    private void CerrarVentana() {
        if (this.jTable1.getRowCount() > 0) {

            int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                objinventar.Habilita(true);
                objinventario.marcacdo = false;
                this.dispose();
            }
        } else {
            objinventar.Habilita(true);
            objinventario.marcacdo = false;
            this.dispose();
        }

    }

    private String Valida_Documento(int validanum) {

        int miserie = Integer.parseInt(nrodocumento1.getText().trim());
        //int minumero = Integer.parseInt(nrodocumento.getText().trim());
        int minumero = validanum;

        DecimalFormat format = new DecimalFormat("00000");
        MiSerie = format.format(miserie);

        DecimalFormat format1 = new DecimalFormat("000000000000");
        this.MiNumero = format1.format(minumero);

        String docu = MiSerie + "-" + MiNumero;

        return docu;

    }

    private void Generar_DBF(String G_numguia,List<Guias> listaprodguia) {

        DBFField fields[] = new DBFField[18];
        fields[0] = new DBFField();
        fields[0].setName("item");
        fields[0].setDataType(DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(16);

        fields[1] = new DBFField();
        fields[1].setName("partida");
        fields[1].setDataType(DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(30);

        fields[2] = new DBFField();
        fields[2].setName("docdat");
        fields[2].setDataType(DBFField.FIELD_TYPE_D);

        fields[3] = new DBFField();
        fields[3].setName("codalm");
        fields[3].setDataType(DBFField.FIELD_TYPE_C);
        fields[3].setFieldLength(2);

        fields[4] = new DBFField();
        fields[4].setName("typmov");
        fields[4].setDataType(DBFField.FIELD_TYPE_C);
        fields[4].setFieldLength(2);

        fields[5] = new DBFField();
        fields[5].setName("docnum");
        fields[5].setDataType(DBFField.FIELD_TYPE_C);
        fields[5].setFieldLength(10);

        fields[6] = new DBFField();
        fields[6].setName("codpro");
        fields[6].setDataType(DBFField.FIELD_TYPE_C);
        fields[6].setFieldLength(5);

        fields[7] = new DBFField();
        fields[7].setName("stkprf");
        fields[7].setDataType(DBFField.FIELD_TYPE_C);
        fields[7].setFieldLength(4);

        fields[8] = new DBFField();
        fields[8].setName("despro");
        fields[8].setDataType(DBFField.FIELD_TYPE_C);
        fields[8].setFieldLength(30);

        fields[9] = new DBFField();
        fields[9].setName("codlab");
        fields[9].setDataType(DBFField.FIELD_TYPE_C);
        fields[9].setFieldLength(8);

        fields[10] = new DBFField();
        fields[10].setName("codprv");
        fields[10].setDataType(DBFField.FIELD_TYPE_C);
        fields[10].setFieldLength(4);

        fields[11] = new DBFField();
        fields[11].setName("dtopro");
        fields[11].setDataType(DBFField.FIELD_TYPE_N);
        fields[11].setFieldLength(10);

        fields[12] = new DBFField();
        fields[12].setName("prisal");
        fields[12].setDataType(DBFField.FIELD_TYPE_N);
        fields[12].setFieldLength(10);

        fields[13] = new DBFField();
        fields[13].setName("stksed");
        fields[13].setDataType(DBFField.FIELD_TYPE_N);
        fields[13].setFieldLength(9);

        fields[14] = new DBFField();
        fields[14].setName("stkfra");
        fields[14].setDataType(DBFField.FIELD_TYPE_N);
        fields[14].setFieldLength(7);

        fields[15] = new DBFField();
        fields[15].setName("codbar");
        fields[15].setDataType(DBFField.FIELD_TYPE_C);
        fields[15].setFieldLength(16);

        fields[16] = new DBFField();
        fields[16].setName("datreg");
        fields[16].setDataType(DBFField.FIELD_TYPE_D);

        fields[17] = new DBFField();
        fields[17].setName("doctot");
        fields[17].setDataType(DBFField.FIELD_TYPE_N);
        fields[17].setFieldLength(10);

        DBFWriter writer = new DBFWriter();

        try {
            writer.setFields(fields);
        } catch (DBFException ex) {
            System.out.println("Error Campos:" + ex.getMessage());
        }

        Object rowData4[] = new Object[18];
        TipoX = "";

        for (Integer totalProductos = 0; totalProductos < listaprodguia.size(); totalProductos++) {
            rowData4 = new Object[18];
            TipoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            FechaX = objFechaHora.ConvierteFecha2(fechaDocumento.getDate()).toString();
            MovimientoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            AlmacenX = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            rowData4[0] = String.valueOf(totalProductos + 1);
            rowData4[1] = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            rowData4[2] = objFechaHora.ConvierteFecha2(fechaDocumento.getDate());
            x = String.valueOf(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString().trim().charAt(0));
            rowData4[3] = x;
            rowData4[4] = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            //rowData4[5] = nrodocumento1.getText().toString() + "-" + nrodocumento.getText().toString().replaceFirst ("^0*", "");
            rowData4[5] = nrodocumento1.getText().toString() + "-" + G_numguia.replaceFirst ("^0*", "");
            rowData4[6] = listaprodguia.get(totalProductos).getId_Producto();
            boolean gfg = false;
            String cadenita = "";
            String cadenita2 = "";

            for (int z = 0; z < listaprodguia.get(totalProductos).getCompuesto().toString().length(); z++) {
                if (listaprodguia.get(totalProductos).getCompuesto().toString().substring(z, z + 1).compareTo("F") != 0) {
                    if (gfg == false) {
                        cadenita = listaprodguia.get(totalProductos).getCompuesto().toString();
                    }
                } else {
                    cadenita = listaprodguia.get(totalProductos).getCompuesto().toString().substring(0, z);
                    cadenita2 = listaprodguia.get(totalProductos).getCompuesto().toString().substring(z, listaprodguia.get(totalProductos).getCompuesto().toString().length());
                    gfg = true;
                }
            }

            if (cadenita.trim().length() < 1) {
                cadenita = cadenita2;
                gfg = false;
                cadenita2 = "";
            }

            rowData4[7] = cadenita;//modeloTabla.getValueAt(totalProductos, 3);
            rowData4[8] = listaprodguia.get(totalProductos).getDescripcion();
            rowData4[9] = listaprodguia.get(totalProductos).getLaboratorio();
            rowData4[10] = jTextField2.getText().trim();
            rowData4[11] = 0.0;//objProducto.Recupera_Dscto(modeloTabla.getValueAt(totalProductos, 0).toString(), 0);
            rowData4[12] = 0.0;//objProducto.RecuperaPrecio(modeloTabla.getValueAt(totalProductos, 0).toString());
            rowData4[13] = 0.0;
            List<Productos_Botica> lista = objProducto.RecuperEmpaque(listaprodguia.get(totalProductos).getId_Producto().toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());
            rowData4[14] = 0.0;//Double.valueOf(lista.get(0).getEmpaque()) ;
            rowData4[15] = "-";
            rowData4[16] = objFechaHora.RetornaFecha();
            rowData4[17] = 0.0;

            try {
                writer.addRecord(rowData4);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "--" + ex.getLocalizedMessage() + "--" + ex.toString());
            }

            if (gfg == true) {

                rowData4 = new Object[18];
                TipoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                FechaX = objFechaHora.ConvierteFecha((Date) fechaDocumento.getDate());
                MovimientoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                AlmacenX = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                rowData4[0] = String.valueOf(totalProductos + 2);
                rowData4[1] = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                rowData4[2] = objFechaHora.ConvierteFecha2(fechaDocumento.getDate());
                x = String.valueOf(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString().trim().charAt(0));
                rowData4[3] = x;
                rowData4[4] = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                //rowData4[5] = nrodocumento1.getText().toString() + "-" + nrodocumento.getText().toString();
                rowData4[5] = nrodocumento1.getText().toString() + "-" + G_numguia.toString();
                rowData4[6] = listaprodguia.get(totalProductos).getId_Producto();
                rowData4[7] = cadenita2;
                rowData4[8] = listaprodguia.get(totalProductos).getDescripcion();
                rowData4[9] = listaprodguia.get(totalProductos).getLaboratorio();
                rowData4[10] = jTextField2.getText().trim();
                rowData4[11] = 0.0;//objProducto.Recupera_Dscto(modeloTabla.getValueAt(totalProductos, 0).toString(), 0);
                rowData4[12] = 0.0;//objProducto.RecuperaPrecio(modeloTabla.getValueAt(totalProductos, 0).toString());
                rowData4[13] = 0.0;
                lista = objProducto.RecuperEmpaque(listaprodguia.get(totalProductos).getId_Producto().toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());
                rowData4[14] = 0.0;//Double.valueOf(lista.get(0).getEmpaque()) ;
                rowData4[15] = "-";
                rowData4[16] = objFechaHora.RetornaFecha();
                rowData4[17] = 0.0;

                try {
                    writer.addRecord(rowData4);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + "--" + ex.getLocalizedMessage() + "--" + ex.toString());
                }
            }
        }
        /*for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
            rowData4 = new Object[18];
            TipoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            FechaX = objFechaHora.ConvierteFecha2(fechaDocumento.getDate()).toString();
            MovimientoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            AlmacenX = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            rowData4[0] = String.valueOf(totalProductos + 1);
            rowData4[1] = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            rowData4[2] = objFechaHora.ConvierteFecha2(fechaDocumento.getDate());
            x = String.valueOf(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString().trim().charAt(0));
            rowData4[3] = x;
            rowData4[4] = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
            //rowData4[5] = nrodocumento1.getText().toString() + "-" + nrodocumento.getText().toString().replaceFirst ("^0*", "");
            rowData4[5] = nrodocumento1.getText().toString() + "-" + G_numguia.replaceFirst ("^0*", "");
            rowData4[6] = modeloTabla.getValueAt(totalProductos, 0);
            boolean gfg = false;
            String cadenita = "";
            String cadenita2 = "";

            for (int z = 0; z < modeloTabla.getValueAt(totalProductos, 3).toString().length(); z++) {
                if (modeloTabla.getValueAt(totalProductos, 3).toString().substring(z, z + 1).compareTo("F") != 0) {
                    if (gfg == false) {
                        cadenita = modeloTabla.getValueAt(totalProductos, 3).toString();
                    }
                } else {
                    cadenita = modeloTabla.getValueAt(totalProductos, 3).toString().substring(0, z);
                    cadenita2 = modeloTabla.getValueAt(totalProductos, 3).toString().substring(z, modeloTabla.getValueAt(totalProductos, 3).toString().length());
                    gfg = true;
                }
            }

            if (cadenita.trim().length() < 1) {
                cadenita = cadenita2;
                gfg = false;
                cadenita2 = "";
            }

            rowData4[7] = cadenita;//modeloTabla.getValueAt(totalProductos, 3);
            rowData4[8] = modeloTabla.getValueAt(totalProductos, 1);
            rowData4[9] = modeloTabla.getValueAt(totalProductos, 2);
            rowData4[10] = jTextField2.getText().trim();
            rowData4[11] = 0.0;//objProducto.Recupera_Dscto(modeloTabla.getValueAt(totalProductos, 0).toString(), 0);
            rowData4[12] = 0.0;//objProducto.RecuperaPrecio(modeloTabla.getValueAt(totalProductos, 0).toString());
            rowData4[13] = 0.0;
            List<Productos_Botica> lista = objProducto.RecuperEmpaque(modeloTabla.getValueAt(totalProductos, 0).toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());
            rowData4[14] = 0.0;//Double.valueOf(lista.get(0).getEmpaque()) ;
            rowData4[15] = "-";
            rowData4[16] = objFechaHora.RetornaFecha();
            rowData4[17] = 0.0;

            try {
                writer.addRecord(rowData4);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "--" + ex.getLocalizedMessage() + "--" + ex.toString());
            }

            if (gfg == true) {

                rowData4 = new Object[18];
                TipoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                FechaX = objFechaHora.ConvierteFecha((Date) fechaDocumento.getDate());
                MovimientoX = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                AlmacenX = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                rowData4[0] = String.valueOf(totalProductos + 2);
                rowData4[1] = objBoticas.RecuperaDescripcionDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                rowData4[2] = objFechaHora.ConvierteFecha2(fechaDocumento.getDate());
                x = String.valueOf(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString().trim().charAt(0));
                rowData4[3] = x;
                rowData4[4] = objBoticas.RecuperaCodigoDescargo(lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString());
                //rowData4[5] = nrodocumento1.getText().toString() + "-" + nrodocumento.getText().toString();
                rowData4[5] = nrodocumento1.getText().toString() + "-" + G_numguia.toString();
                rowData4[6] = modeloTabla.getValueAt(totalProductos, 0);
                rowData4[7] = cadenita2;
                rowData4[8] = modeloTabla.getValueAt(totalProductos, 1);
                rowData4[9] = modeloTabla.getValueAt(totalProductos, 2);
                rowData4[10] = jTextField2.getText().trim();
                rowData4[11] = 0.0;//objProducto.Recupera_Dscto(modeloTabla.getValueAt(totalProductos, 0).toString(), 0);
                rowData4[12] = 0.0;//objProducto.RecuperaPrecio(modeloTabla.getValueAt(totalProductos, 0).toString());
                rowData4[13] = 0.0;
                lista = objProducto.RecuperEmpaque(modeloTabla.getValueAt(totalProductos, 0).toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());
                rowData4[14] = 0.0;//Double.valueOf(lista.get(0).getEmpaque()) ;
                rowData4[15] = "-";
                rowData4[16] = objFechaHora.RetornaFecha();
                rowData4[17] = 0.0;

                try {
                    writer.addRecord(rowData4);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + "--" + ex.getLocalizedMessage() + "--" + ex.toString());
                }
            }
        }*/

        FileOutputStream fos = null;

        try {
            File Ffichero = new File("/datos/AENVIO/");
            if (!Ffichero.exists()) {
                Ffichero.mkdir();
            }
            //fos = new FileOutputStream("/datos/AENVIO/" + objBoticas.RecuperaAcronimo2(objet.getIdbotica()) + nrodocumento1.getText().substring(0, 2) + this.nrodocumento.getText().substring(nrodocumento.getText().length() - 3, nrodocumento.getText().length()) + ".DBF");
            fos = new FileOutputStream("/datos/AENVIO/" + objBoticas.RecuperaAcronimo2(objet.getIdbotica()) + nrodocumento1.getText().substring(0, 2) + G_numguia.substring(G_numguia.length() - 3, G_numguia.length()) + ".DBF");
        } catch (FileNotFoundException ex) {
            try {
                File Ffichero = new File("C:\\AENVIO\\");
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }
                //fos = new FileOutputStream("C:\\AENVIO\\" + objBoticas.RecuperaAcronimo2(objet.getIdbotica()) + nrodocumento1.getText().substring(0, 2) + this.nrodocumento.getText().substring(nrodocumento.getText().length() - 3, nrodocumento.getText().length()) + ".DBF");
                fos = new FileOutputStream("C:\\AENVIO\\" + objBoticas.RecuperaAcronimo2(objet.getIdbotica()) + nrodocumento1.getText().substring(0, 2) + G_numguia.substring(G_numguia.length() - 3, G_numguia.length()) + ".DBF");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(Descargos_Cli.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        try {
            writer.write(fos);
        } catch (DBFException ex) {
            System.out.println("CARGO DBF." + ex.getMessage());
        }

        try {
            fos.close();
        } catch (IOException ex) {
            System.out.println("CARGO DBF." + ex.getMessage());
        }

    }

    private boolean Imprimir() throws Exception {

        /*PrinterJob job = PrinterJob.getPrinterJob();
        job.printDialog();
        String miprinter = job.getPrintService().getName();*/

        String miprinter = ImpresoraDesc;
        FileOutputStream os = null;
        PrintStream ps = null;

        /*Toca Imprimir*/
        for (Integer copias = 0; copias < 2; copias++) {
            try {
                os = new FileOutputStream(miprinter);
                ps = new PrintStream(os);
                ps.println("                                                        REPORTE DE DESCARGO");
                ps.println("");
                ps.println("");
                ps.println("PARTIDA             : " + TipoX);
                ps.println("FECHA               : " + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()));
                ps.println("ALMACEN             : " + x);
                ps.println("CODIGO MOVIMIENTO   : " + AlmacenX);
                ps.println("");
                ps.println("");
                ps.println("   ITEM  | CODIGO | CANTIDAD |             PRODUCTO           |  LAB  |");
                ps.println("------------------------------------------------------------------------------------------");

                for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
                    ps.println(
                            String.format("%1$-9s", String.valueOf(totalProductos + 1)) + "|"
                            + String.format("%1$-8s", modeloTabla.getValueAt(totalProductos, 0).toString()) + "|"
                            + String.format("%1$-10s", modeloTabla.getValueAt(totalProductos, 3).toString()) + "|"
                            + String.format("%1$-30s", modeloTabla.getValueAt(totalProductos, 1).toString()) + "|"
                            + String.format("%1$-7s", modeloTabla.getValueAt(totalProductos, 2).toString()) + "|");

                }

                for (Integer d = 0; d < 10; d++) {
                    ps.println("");
                }

                ps.println("---------------------------                                    ---------------------------");
                ps.println("    Q.F.  BOTICA                                                     RECEPCION OFICINA    ");

                ps.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL IMPRIMIR SU DESCARGO -- NO SE HA DETECTADO LA IMPRESORA \n PORFAVOR REVISE SI SU IMPRESORA ESTE ENCENDIDA O BIEN CONECTADA ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL IMPRIMIR SU DESCARGO ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void Deshabilitar(boolean valor) {
        lasboticas2.setEnabled(valor);
        nrodocumento1.setEditable(valor);
        nrodocumento.setEditable(valor);
        observ.setEditable(valor);
        fechaDocumento.setEnabled(valor);
        fechaRecepcion.setEnabled(valor);
        jComboBox2.setEnabled(valor);
        jTable1.setEnabled(valor);

    }

    public void AsignaDatosCliente() {
        try {
            LimpiarDatos();
            jTextField4.setText(objcliente.getCodigoCliente());
            jTextField5.setText(objcliente.getNombre_RazonSocial());            
            jTextField10.setText(objcliente.getRUC_DNI());
            
            List<Clientes> midirecciones = objClientes.Lista_Direcciones(idbotica, Integer.parseInt(objcliente.getCodigoCliente()), objcliente.getEmpresa(), objcliente.getDireccion().trim());
            for (int i = 0; i < midirecciones.size(); i++) {
            //jComboBox4.addItem(midirecciones.get(i).getDireccion());
            jTextField6.setText(midirecciones.get(i).getDireccion());
            }
            
        } catch (Exception ex) {
            System.out.println("Error al recuperrar cliente:" + ex.getMessage());
        }
    }
    
    public void AsignaDatosTransportista() {
        try {
            LimpiarDatos_T();
            jTextField7.setText(objTranspor.getCodigoTransportista());            
            jTextField8.setText(objTranspor.getNombre_Transportista());            
            jTextField9.setText(objTranspor.getN_Placa());
            jTextField12.setText(objTranspor.getN_Licencia());
            jTextField11.setText(objTranspor.getN_Contancia());
            
            
            
        } catch (Exception ex) {
            System.out.println("Error al recuperrar cliente:" + ex.getMessage());
        }
    }
    
    private void LimpiarDatos() {
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        
    }
    private void LimpiarDatos_T() {
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
    }
    private void jTextField15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15MouseClicked

    private void jTextField14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14MouseClicked

    private void jTextField13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13MouseClicked

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jTextField9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        new FormTransportista(this, objetoventana, idbotica).show(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new FormClientes(this, objetoventana, idbotica).show(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseClicked

    private void observKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_observKeyPressed

    private void fechaRecepcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaRecepcionKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_fechaRecepcionKeyPressed

    private void fechaDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaDocumentoKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_fechaDocumentoKeyPressed

    private void fechaDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaDocumentoActionPerformed
        if (!Valida_Fechas()) {
            JOptionPane.showMessageDialog(this, " La Fecha del Documento no Puede ser Mayor a la Fecha de Registro ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fechaDocumentoActionPerformed

    private void fechaDocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechaDocumentoFocusLost

    }//GEN-LAST:event_fechaDocumentoFocusLost

    private void nrodocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyTyped
        if (nrodocumento.getText().length() > 10) {
            evt.consume();
        }
    }//GEN-LAST:event_nrodocumentoKeyTyped

    private void nrodocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_nrodocumentoKeyPressed

    private void nrodocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrodocumentoFocusLost

    }//GEN-LAST:event_nrodocumentoFocusLost

    private void nrodocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nrodocumentoActionPerformed
        /*Ñaca Ñaca, You feel afraid!!!! Dont?? You should... hehehe*/
    }//GEN-LAST:event_nrodocumentoActionPerformed

    private void nrodocumento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumento1KeyTyped

    }//GEN-LAST:event_nrodocumento1KeyTyped

    private void nrodocumento1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumento1KeyReleased

    }//GEN-LAST:event_nrodocumento1KeyReleased

    private void nrodocumento1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumento1KeyPressed

    }//GEN-LAST:event_nrodocumento1KeyPressed

    private void nrodocumento1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrodocumento1FocusLost

    }//GEN-LAST:event_nrodocumento1FocusLost

    private void nrodocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nrodocumento1ActionPerformed

    }//GEN-LAST:event_nrodocumento1ActionPerformed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Muestra_Proveedores();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked

    }//GEN-LAST:event_jTextField3MouseClicked

    private void operacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_operacionesKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_operacionesKeyPressed

    private void operacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_operacionesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_operacionesItemStateChanged

    private void lasboticas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lasboticas2KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_lasboticas2KeyPressed

    private void lasboticas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas2ActionPerformed

    }//GEN-LAST:event_lasboticas2ActionPerformed

    private void lasboticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas1ActionPerformed

    }//GEN-LAST:event_lasboticas1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            if (jCheckBox1.isSelected()) {
                if (Imprimir()) {
                    limpiarTodo(false);
                    jCheckBox1.setSelected(true);
                } else {
                    jButton7.setEnabled(true);
                    jButton5.setEnabled(false);
                    GenerarGuia.setEnabled(false);
                    Deshabilitar(false);
                }
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Seleccionar();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarTodo(true);
        jButton7.setEnabled(false);
        Defecto();
        jButton6.setEnabled(true);
        lasboticas2.setEnabled(true);
        RECNUMERODOC();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarTodo(false);
        LimpiarDatos();
        LimpiarDatos_T();
        jButton6.setEnabled(false);
        lasboticas2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void GenerarGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarGuiaActionPerformed

        if (Valida_Fechas()) {
            if (this.jTable1.getRowCount() > 0) {
                /*Primero Valido Cantidades*/
                a = 0;
                totalLetras = 0;
                enterito = 0;
                fraccionado = 0;
                compuesto="";
                String lab="";
                String descrip="";
                valido = 0;
                TipoX = "";
                int cuenta=0;
                int cuentaValidafac=0;
                FechaX = "";
                MovimientoX = "";
                AlmacenX = "";
                x = "";
                bandera = 0;
                String lote = "";
                String fecvcto = "";
                String RS = "";

                String codbarra1 = "";
                String fecvcto1  = "";
                String RS1 = "";
                boolean exito = false;

                String codtrans = jTextField7.getText().trim();
                if (codtrans.equals("") ){
                    codtrans = "0";
                }

                if (objFechaHora.ConvierteFecha(fechaDocumento.getDate()).length() < 10) {
                    JOptionPane.showMessageDialog(null, "1.Seleccione Fecha Documento", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                } else {

                    if (nrodocumento.getText().toString().trim().length() < 1) {
                        JOptionPane.showMessageDialog(null, "Ingrese Numero Documento", "Nortfarma", JOptionPane.ERROR_MESSAGE);

                    } else {
                        valido = 1;
                        /*if (lasboticas2.isVisible()) {
                            if (lasboticas2.getSelectedIndex() < 1) {
                                JOptionPane.showMessageDialog(null, "Seleccione Boticas", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                valido = 0;
                            } else {
                                valido = 1;
                            }
                        } else {
                            //para el caso de ser de inventarios
                            if (operaciones.getSelectedIndex() < 1) {
                                valido = 0;
                                JOptionPane.showMessageDialog(null, "Seleccione Tipo Movimiento", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                valido = 1;
                            }
                        }*/

                        if (valido == 1) {

                            int p = JOptionPane.showConfirmDialog(this, " Desea Generar Este Descargo al Cliente ", "CONFIRMAR", JOptionPane.YES_NO_OPTION);

                            if (p == 0) {
                                boolean validaRS = false;
                                Movimiento_Detalle detalle_aux;
                                Producto producto_aux;
                                List<Movimiento_Detalle> lstProdGuia = new ArrayList<Movimiento_Detalle>();
                                boolean TmpDetalleGuia = objGuias.InsertaTmpProdGuia(lstProdGuia,0);

                                for (Integer v = 0; v < modeloTabla.getRowCount(); v++) {
                                    lote = modeloTabla.getValueAt(v, 8).toString();
                                    fecvcto = modeloTabla.getValueAt(v, 9).toString();
                                    if (lote.trim().equals("") || fecvcto.trim().equals("")){
                                        validaRS = false;
                                        cuenta =cuenta + 1;
                                    }else{
                                        validaRS = true;
                                    }

                                }

                                for (Integer xy = 0; xy < modeloTabla.getRowCount(); xy++) {
                                    detalle_aux = new Movimiento_Detalle();
                                    producto_aux = new Producto();

                                    enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(xy, 3).toString());
                                    fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(xy, 3).toString());
                                    compuesto = modeloTabla.getValueAt(xy, 3).toString();
                                    lab = modeloTabla.getValueAt(xy, 2).toString();
                                    descrip = modeloTabla.getValueAt(xy, 1).toString();
                                    double pv_aux = Double.parseDouble(modeloTabla.getValueAt(xy, 6).toString());
                                    double dcto_aux = Double.parseDouble(modeloTabla.getValueAt(xy, 7).toString());
                                    double pvf_aux = pv_aux - ((pv_aux * dcto_aux) / 100);
                                    double total_aux = Double.parseDouble(modeloTabla.getValueAt(xy, 5).toString());

                                    lote = modeloTabla.getValueAt(xy, 8).toString();
                                    fecvcto = modeloTabla.getValueAt(xy, 9).toString();
                                    RS = modeloTabla.getValueAt(xy, 10).toString();

                                    producto_aux.setIdProducto(modeloTabla.getValueAt(xy, 0).toString());
                                    detalle_aux.setId_Producto(producto_aux);
                                    detalle_aux.setUnidad(enterito);
                                    detalle_aux.setFraccion(fraccionado);
                                    detalle_aux.setPrecio_Venta(pv_aux);
                                    detalle_aux.setDescuento(dcto_aux);
                                    detalle_aux.setPrecio_Venta_Final(pvf_aux);
                                    detalle_aux.setTotal(total_aux);
                                    detalle_aux.setCompuesto(compuesto);
                                    detalle_aux.setLab(lab);
                                    detalle_aux.setDescripcion(descrip);
                                    detalle_aux.setContar(xy);
                                    detalle_aux.setcodbarras(lote);
                                    detalle_aux.setFecvcto(fecvcto);
                                    detalle_aux.setRS(RS);
                                    //detalle_aux.setNorden(NOrden);
                                    //detalle_aux.setNcajas(NCajas);
                                    //detalle_aux.setIdventa(Interno);

                                    lstProdGuia.add(detalle_aux);
                                }

                                TmpDetalleGuia = objGuias.InsertaTmpProdGuia(lstProdGuia,1);

                                /*------------------------------------------------------------------
                                --------------------------------------------------------------------*/

                                int totalprod = modeloTabla.getRowCount();
                                double aux_totalprod= totalprod;

                                double tot_Impresiones = aux_totalprod /  limiteguia;
                                int totalimprefinal=0;

                                if (tot_Impresiones < 0){
                                    totalimprefinal = 1;

                                }else{
                                    double x_tot_impresiones = Math.ceil(tot_Impresiones);
                                    double myDouble  = Double.valueOf(x_tot_impresiones);
                                    totalimprefinal = (int) myDouble;
                                }

                                int X_guiainicio = Integer.valueOf(nrodocumento.getText());

                                //if (validaRS){
                                    if (cuenta < 1){

                                        for (int creaguia = 0; creaguia < totalimprefinal; creaguia++){     // desde aqui

                                            /*if (lasboticas2.isVisible()) {
                                                seleccionado = lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString();
                                            } else {
                                                seleccionado = operaciones.getItemAt(operaciones.getSelectedIndex()).toString();
                                            }*/

                                            int validanumero = X_guiainicio + creaguia;
                                            String docu = Valida_Documento(validanumero);

                                            Movimientos entidad = logdata.Verifica_Movimiento(idbotica, docu/*,2*/);

                                            if (entidad == null) {

                                                entidadGuias = new Guias(
                                                    lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString(),
                                                    jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString(),
                                                    docu, jTextField2.getText().trim(),
                                                    objFechaHora.ConvierteFecha(fechaDocumento.getDate()),
                                                    objet.getIdpersonal_botica(),
                                                    objFechaHora.ConvierteFecha(fechaRecepcion.getDate()),
                                                    //lasboticas2.getSelectedItem().toString(),
                                                    "",
                                                    Double.parseDouble(jTextField1.getText().trim()), MiSerie, String.valueOf(validanumero),
                                                    Integer.valueOf(jTextField4.getText().trim()),Integer.valueOf(codtrans),
                                                    jTextField13.getText().trim(),jTextField14.getText().trim(),
                                                    jTextField15.getText().trim());

                                                if ((objGuias.guiaCargada(entidadGuias, 1, 0) == 0)) {
                                                    Movimiento_Detalle detalle;
                                                    Producto producto;
                                                    List<Movimiento_Detalle> lstDescargo = new ArrayList<Movimiento_Detalle>();

                                                    listaguias.clear();
                                                    listaguias = objGuias.recuperaProdGuia(limiteguia,String.valueOf(validanumero),0);

                                                    double totalXguia=0.0;
                                                    for (Integer totalProductos = 0; totalProductos < listaguias.size(); totalProductos++) {

                                                        detalle = new Movimiento_Detalle();
                                                        producto = new Producto();

                                                        enterito = listaguias.get(totalProductos).getUnidad();
                                                        fraccionado = listaguias.get(totalProductos).getFraccion();

                                                        double pv = listaguias.get(totalProductos).getPrecio_Venta();
                                                        double dcto = listaguias.get(totalProductos).getDescuento();
                                                        double pvf = pv - ((pv * dcto) / 100);
                                                        double total = listaguias.get(totalProductos).getTotal();
                                                        totalXguia += listaguias.get(totalProductos).getTotal();
                                                        codbarra1 = listaguias.get(totalProductos).getCodbarras();
                                                        fecvcto1 = listaguias.get(totalProductos).getFecvcto();
                                                        RS1 = listaguias.get(totalProductos).getRS();

                                                        producto.setIdProducto(listaguias.get(totalProductos).getId_Producto());
                                                        detalle.setId_Producto(producto);
                                                        detalle.setUnidad(enterito);
                                                        detalle.setFraccion(fraccionado);
                                                        detalle.setPrecio_Venta(pv);
                                                        detalle.setDescuento(dcto);
                                                        detalle.setPrecio_Venta_Final(pvf);
                                                        detalle.setTotal(total);
                                                        detalle.setContar(totalProductos);
                                                        detalle.setcodbarras(codbarra1);
                                                        detalle.setFecvcto(fecvcto1);
                                                        detalle.setRS(RS1);
                                                        lstDescargo.add(detalle);

                                                    }

                                                    BigDecimal bd = new BigDecimal(totalXguia);
                                                    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                                                    totalXguia = bd.doubleValue();

                                                    for (Integer lt = 0; lt < listaguias.size(); lt++) {
                                                        int validafec = listaguias.get(lt).getNum();
                                                        //if (lote.trim().equals("") || fecvcto.trim().equals("")){
                                                            if (validafec == -1){
                                                                cuentaValidafac =cuentaValidafac + 1;
                                                            }

                                                        }

                                                        if (cuentaValidafac == 0){

                                                            Movimientos resul = objGuias.DescargarGuiaClientes(entidadGuias, observ.getText().trim().toUpperCase(), lstDescargo,totalXguia);

                                                            if (resul.getIderror() == 0) {
                                                                //JOptionPane.showMessageDialog(null, " Descargo Realizado \n Numero de Documento Generado : " + resul.getId_Producto() + " ", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                                                jButton6.setEnabled(false);

                                                                try {

                                                                    int rec_n_gruia = Integer.valueOf(nrodocumento.getText());
                                                                    int f_guia = rec_n_gruia + creaguia;
                                                                    int nfinalguia = f_guia;

                                                                    Formatter fmt = new Formatter();
                                                                    String G_numguia = String.valueOf(fmt.format("%06d", nfinalguia));

                                                                    //Generar_DBF(G_numguia,listaguias);
                                                                    exito = true;
                                                                    ActualizaNumero.clear();
                                                                    //ActualizaNumero = objGuias.recuperaProdGuia(limiteguia,String.valueOf(validanumero),2);
                                                                    ActualizaNumero = objGuias.ActGuia(limiteguia,String.valueOf(validanumero),2);

                                                                } catch (Exception ex) {
                                                                    JOptionPane.showMessageDialog(null, " ERROR AL GENERAR SU ARCHIVO " + ex.getMessage() + "" + resul.getId_Producto() + " ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                /*if (jCheckBox1.isSelected()) {
                                                                    try {
                                                                        if (Imprimir()) {
                                                                            Deshabilitar(true);
                                                                            limpiarTodo(false);
                                                                            jCheckBox1.setSelected(true);
                                                                        } else {
                                                                            jButton7.setEnabled(true);
                                                                            jButton3.setEnabled(false);
                                                                            jButton5.setEnabled(false);
                                                                            GenerarGuia.setEnabled(false);
                                                                            Deshabilitar(false);
                                                                        }
                                                                    } catch (Exception ex) {
                                                                    }
                                                                }else{*/
                                                                    //limpiarTodo(false);
                                                                    //}

                                                            }//CIERRE DE IF CONFIRMAR
                                                            else {
                                                                if (resul.getIderror() == 2) {
                                                                    JOptionPane.showMessageDialog(null, " Lo sentimos No Cuenta con Stock del Producto \n " + resul.getId_Producto() + " ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, " Error al Realizar su Descargo ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }

                                                        }else{//aqui
                                                            JOptionPane.showMessageDialog(null, "FAVOR VERIFIQUE: \n - Debe ingresar las fechas en el formato Año-Mes-Dia", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                        }

                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "FAVOR VERIFIQUE: \n - Guia ya ha Sido Cargada con Anterioridad \n\n Favor verificar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                    }

                                                }// ya esta cargado el documento
                                                else {
                                                    VerificaDocumento obj = new VerificaDocumento(objetoventana, true, entidad);
                                                    obj.pack();
                                                    obj.setVisible(true);
                                                }
                                            }   //hasta aqui  //fin del for

                                            if (exito){

                                                int ser = Integer.valueOf(nrodocumento1.getText());
                                                int numini = Integer.valueOf(nrodocumento.getText());

                                                if (totalimprefinal == 1){
                                                    totalimprefinal = 0;
                                                }else{
                                                    totalimprefinal = totalimprefinal - 1;
                                                }

                                                int finalguia = numini + totalimprefinal;
                                                int nfinal = finalguia;

                                                Formatter fmt = new Formatter();
                                                Formatter fmt1 = new Formatter();

                                                String numinicio = String.valueOf(fmt.format("%06d", numini));
                                                String numfinal = String.valueOf(fmt1.format("%06d", nfinal));

                                                String msg1= "<html><h1>GUIAS GENERADAS: </h1><hr></html>\n";
                                                String msg2= "<html><h2> desde : >> <font color='red'> " + ser + '-' + numinicio + "</font> hasta :  <font color='red'>" + numfinal + "</h2><hr>\n";
                                                JOptionPane.showMessageDialog(null, msg1 + msg2, "Nortfarma", JOptionPane.INFORMATION_MESSAGE);

                                                limpiarTodo(false);
                                                LimpiarDatos();
                                                LimpiarDatos_T();

                                            }

                                        }else{  // fin de valida RS
                                            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LOTE Y FECHA DE VCTO DE TODOS LOS PRODUCTOS" , "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                        }

                                    }

                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "FAVOR VERIFIQUE: \n- No ha Ingresado Ningun Producto Para su Descargo ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, " La Fecha del Documento no Puede ser Mayor a la Fecha de Registro ", "Error", JOptionPane.ERROR_MESSAGE);
                }
    }//GEN-LAST:event_GenerarGuiaActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        switch (evt.getKeyCode()) {

            case 127:
            if (jTable1.getRowCount() > 0) {
                modeloTabla.removeRow(jTable1.getSelectedRow());
            }

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Verifica_Cantidad();
        }

        Realiza_Opciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private boolean Valida_Fechas() {
        boolean valor = true;
        java.util.Date fecha1 = this.fechaDocumento.getDate();
        java.util.Date fecha2 = this.fechaRecepcion.getDate();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }
        return valor;
    }

    private void Muestra_Proveedores() {
        formproveedor = new FormProveedores(objetoventana);
        formproveedor.pack();
        formproveedor.setVisible(true);
        idproveedor = formproveedor.getIdproveedor();
        String nombre = formproveedor.getNombre_Proveedor();

        if (idproveedor != null) {
            jTextField2.setText(idproveedor);
            jTextField3.setText(nombre);
        }

    }
    
    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            Color micol = new Color(216, 228, 248);
            
            //setBackground(micol);            
            this.setBackground(Color.YELLOW);
            this.setForeground(Color.RED);
            int tam = modeloTabla.getRowCount();

            for (int i = 0; i < tam; i++) {                
                //if (listaVenta.get(i).getUnidad() >= 0) {
                    //setBackground(micol);
                    this.setBackground(Color.YELLOW);
                    this.setForeground(Color.RED);
                //}

            }


            super.getTableCellRendererComponent(table, value, false, false, 2, column);

            return this;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenerarGuia;
    private org.jdesktop.swingx.JXDatePicker fechaDocumento;
    private org.jdesktop.swingx.JXDatePicker fechaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox lasboticas1;
    private javax.swing.JComboBox lasboticas2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField nrodocumento;
    private javax.swing.JTextField nrodocumento1;
    private javax.swing.JTextArea observ;
    private javax.swing.JComboBox operaciones;
    // End of variables declaration//GEN-END:variables
}
