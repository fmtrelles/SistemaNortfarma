/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cargos1.java
 *
 * Created on 30/01/2012, 06:59:46 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProveedor;
import CapaLogica.LogicaTipoMovimiento;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;
import entidad.Boticas;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Producto;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class Cargos extends javax.swing.JInternalFrame {

    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento;
    List<Proveedor> listaProveedor;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Guias> listguias;
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    Integer totalLetras = 0;
    Integer a = 0;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    private DefaultTableModel tablaproducto;
    DefaultTableModel modeloTabla;
    List<Integer> anArray = new ArrayList<Integer>();
    String elArchivo = "";
    Guias entidadGuias = new Guias();
    Producto entidadProductos = new Producto();
    Inventarios objinventario;
    private String idbotica;
    private String MiSerie;
    private String MiNumero;
    Mant_Productos mantProduc = new Mant_Productos();
    Date FechaDocumento = null;
    Date fechaSistema = null;

    /** Creates new form Cargos1 */
    public Cargos(java.awt.Frame parent, Inventarios obj, OpcionesMenu miobj) {
        initComponents();
        modeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        objOpcionesMenu = miobj;
        objinventario = obj;
        idbotica = objinventario.getIdbotica();
        jTable1.setModel(modeloTabla);
        construirGuia();
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        jLabel1.setVisible(false);
        
    }

    private void construirGuia() {

        modeloTabla.addColumn("CODIGO");
        modeloTabla.addColumn("LAB");
        modeloTabla.addColumn("PRODUCTO");
        modeloTabla.addColumn("TOTAL");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("MONTO");
        modeloTabla.addColumn("PVP");
        modeloTabla.addColumn("DSCTO");

        jTable1.setModel(modeloTabla);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
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

    }

    public void mostrarGuia(String ruta) throws FileNotFoundException, IOException {

        Integer habilitarBoton = 1;

        try {

            InputStream inputStream = new FileInputStream(ruta); // take dbf file as program argument
            DBFReader reader = new DBFReader(inputStream);
            Object[] rowObjects;
            Integer contador = 0;

            while ((rowObjects = reader.nextRecord()) != null) {
                Calendar Cal = Calendar.getInstance();
                String fec = Cal.get(Calendar.DATE) + " / " + (Cal.get(Calendar.MONTH) + 1) + " / " + Cal.get(Calendar.YEAR);

                if (contador == 0) {
                    
                    //lblAlmacen.setText(rowObjects[3].toString().trim());
                    jTextField1.setText(rowObjects[10].toString().trim());
                    FechaDocumento = (Date) rowObjects[2];
                    
                    String TipMov = rowObjects[4].toString().trim();
                    if (!TipMov.equals("RT")){
                       
                        lblAlmacen.setText(rowObjects[3].toString().trim());
                        
                        if (lblAlmacen.getText().compareTo("M") != 0) {
                            lblDescripcionAlmacen.setText("Almacen");
                        } else {
                            lblDescripcionAlmacen.setText("Mostrador");
                        }
                    }else{
                        lblAlmacen.setText("T");
                        lblDescripcionAlmacen.setText("Trastienda");
                    }
                    
                        if (objBoticas.VerificaGuiaPertene(rowObjects[4].toString().trim())) {
                            PROCESAR1.setEnabled(true);
                        } else {
                            lblMensaje.setText("<html><FONT COLOR=RED>GUIA NO TIENE COMO FIN ESTA BOTICA, POR FAVOR VERIFIQUE</FONT></html>");
                            PROCESAR1.setEnabled(false);
                        }
                    

                    lasboticas.setText(objBoticas.RecuperaIDPorAcronimo(elArchivo));
                    lblNroDocumento.setText(rowObjects[5].toString().trim());
                    fechaDocumento.setDate(FechaDocumento);
                }

                Object[][] data = {
                    {rowObjects[6].toString().trim(),
                        rowObjects[9].toString().trim(),
                        rowObjects[8].toString().trim(),
                        rowObjects[7].toString().trim()
                    }};

                contador++;
                modeloTabla.addRow(data[0]);

            }

            inputStream.close();

        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (habilitarBoton == 1) {
        } else {
            jLabel22.setForeground(Color.red);
        }

    }

    private void limpiarTodo(Boolean valor) {
        lblNroDocumento.setText("");
        observaciones.setText("");
        lblDescripcionAlmacen.setText("");
        lblMensaje.setText("");

        lasboticas.setText("");
        observaciones.setEnabled(valor);

        observaciones.setText("");
        lblRuta.setText("");
        lblAlmacen.setText("");

        for (Integer i = 0; i < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(i);
        }

        jButton3.setEnabled(!valor);
        PROCESAR1.setEnabled(valor);
        fechaRecepcion.setEnabled(valor);
        Buscar.setEnabled(valor);
        btnVisualizar.setEnabled(!valor);
        jLabel1.setVisible(!valor);

    }

    private void limpiarTodo2(Boolean valor) throws SQLException {
        lblNroDocumento.setText("");
        observaciones.setText("");
        lblDescripcionAlmacen.setText("");
        lblMensaje.setText("");
        lasboticas.setText("");
        observaciones.setText("");
        lblRuta.setText("");
        lblAlmacen.setText("");

        for (Integer i = 0; i < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(i);
        }
    }

    private void imprimirCargo() {
        try {
            FileOutputStream os = new FileOutputStream("LPT1:");
            PrintStream ps = new PrintStream(os);
            ps.println("prueba de impresion realizada");
            ps.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void validarCantidadProductos() {
        totalLetras = 0;

        for (a = 0; a < tablaproducto.getRowCount(); a++) {
            for (a = 0; a < tablaproducto.getRowCount(); a++) {
                try {
                    for (Integer largo = 0; largo < tablaproducto.getValueAt(a, 3).toString().length(); largo++) {
                        if ((tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).charAt(0) >= 65) && (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).charAt(0) >= 65)) {
                            totalLetras++;
                            if (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70) {

                                try {
                                    if ((tablaproducto.getValueAt(a, 3).toString().substring(largo + 1, largo + 2).toUpperCase().charAt(0) >= 49) && (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).toUpperCase().charAt(0) >= 57)) {
                                        //Solo si el q le precede al F es un numero
                                        totalLetras--;
                                    }
                                } catch (Exception Ex) {
                                    System.out.println(Ex.getMessage());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    totalLetras++;
                }
            }
        }
    }

    private void verificaAlmacenes(String toString) {
        if (objBoticas.VerificaPoseeAlmacenes(idbotica)) {
            jButton2.setEnabled(true);
        } else {
            jButton2.setEnabled(false);
        }
    }

    private void VerificaFecha() {
        fechaSistema = objFechaHora.RetornaFecha();
        int diferencia = mantProduc.diferenciasDeFechas(FechaDocumento, fechaSistema);

        if (diferencia > objGuias.DiasCargo(idbotica, objinventario.getIdpesonal())) {
            jLabel1.setText("LO SENTIMOS ESTA GUIA NO SE PUEDE CARGAR EN ESTA FECHA");
            jLabel1.setVisible(true);
            PROCESAR1.setEnabled(false);
        } else {
            jLabel1.setText("");
            PROCESAR1.setEnabled(true);
        }
    }

    public class Ventana extends JFrame {

        public Ventana() {
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
        jButton3 = new javax.swing.JButton();
        PROCESAR1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblRuta = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lasboticas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblNroDocumento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblAlmacen = new javax.swing.JTextField();
        lblDescripcionAlmacen = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaDocumento = new org.jdesktop.swingx.JXDatePicker();
        fechaRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Cargos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jButton3.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        PROCESAR1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        PROCESAR1.setText(resourceMap.getString("PROCESAR1.text")); // NOI18N
        PROCESAR1.setEnabled(false);
        PROCESAR1.setName("PROCESAR1"); // NOI18N
        PROCESAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROCESAR1ActionPerformed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(PROCESAR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PROCESAR1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lblMensaje.setName("lblMensaje"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        lblRuta.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        lblRuta.setEnabled(false);
        lblRuta.setName("lblRuta"); // NOI18N

        Buscar.setText(resourceMap.getString("Buscar.text")); // NOI18N
        Buscar.setEnabled(false);
        Buscar.setName("Buscar"); // NOI18N
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        btnVisualizar.setText(resourceMap.getString("btnVisualizar.text")); // NOI18N
        btnVisualizar.setEnabled(false);
        btnVisualizar.setName("btnVisualizar"); // NOI18N
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar)
                    .addComponent(btnVisualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        lasboticas.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        lasboticas.setEnabled(false);
        lasboticas.setName("lasboticas"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        lblNroDocumento.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        lblNroDocumento.setEnabled(false);
        lblNroDocumento.setName("lblNroDocumento"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        lblAlmacen.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        lblAlmacen.setEnabled(false);
        lblAlmacen.setName("lblAlmacen"); // NOI18N

        lblDescripcionAlmacen.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        lblDescripcionAlmacen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblDescripcionAlmacen.setName("lblDescripcionAlmacen"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("lblDescripcionAlmacen.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        fechaDocumento.setEnabled(false);
        fechaDocumento.setFormats(new String[] { "dd/M/yyyy" });
        fechaDocumento.setName("fechaDocumento"); // NOI18N

        fechaRecepcion.setEnabled(false);
        fechaRecepcion.setFormats(new String[] { "dd/M/yyyy" });
        fechaRecepcion.setName("fechaRecepcion"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        observaciones.setColumns(20);
        observaciones.setFont(resourceMap.getFont("observaciones.font")); // NOI18N
        observaciones.setRows(5);
        observaciones.setEnabled(false);
        observaciones.setName("observaciones"); // NOI18N
        jScrollPane2.setViewportView(observaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lasboticas, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDescripcionAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addComponent(lblNroDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(fechaRecepcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(fechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lasboticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDescripcionAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel22.setFont(resourceMap.getFont("lblFecha.font")); // NOI18N
        jLabel22.setForeground(resourceMap.getColor("lblFecha.foreground")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        lblFecha.setFont(resourceMap.getFont("lblFecha.font")); // NOI18N
        lblFecha.setForeground(resourceMap.getColor("lblFecha.foreground")); // NOI18N
        lblFecha.setText(resourceMap.getString("lblFecha.text")); // NOI18N
        lblFecha.setName("lblFecha"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(jLabel22))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (lblDescripcionAlmacen.getText().compareTo("Mostrador") != 0) {
            lblDescripcionAlmacen.setText("Mostrador");
            lblAlmacen.setText("M");
        } else {

            lblDescripcionAlmacen.setText("Almacen");
            lblAlmacen.setText("A");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        try {
            // TODO add your handling code here:
            limpiarTodo2(false);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {

            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("GUIAS DE BOTICA", "DBF");
            filechooser.setFileFilter(filter);
            int result = filechooser.showOpenDialog(null); 
          

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                elArchivo = file.getName().toUpperCase();
                lblRuta.setText(String.valueOf(file));                
                btnVisualizar.setEnabled(true);
                Buscar.setEnabled(false);
            }
            
            /*elArchivo = "F0200016.DBF";
            lblRuta.setText("C:/AENVIO/F0200016.DBF"); //gino
            btnVisualizar.setEnabled(true);
            Buscar.setEnabled(false);
            */

        } catch (Exception pe) {
            System.out.println("Error Impresora catch" + pe.getMessage());
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed

        if (lblRuta.getText().length() > 0) {
            try {
                mostrarGuia(lblRuta.getText());
                verificaAlmacenes(lasboticas.getText().toString());
                VerificaFecha();
                btnVisualizar.setEnabled(false);
                Buscar.setEnabled(false);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un archivo", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (this.jTable1.getRowCount() > 0) {
            int p = JOptionPane.showConfirmDialog(null, " Desea Realizar un Nuevo Cargo ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                limpiarTodo(true);                
            }
        } else {
            limpiarTodo(true);
            
        }

        jButton1.setEnabled(true);

}//GEN-LAST:event_jButton3ActionPerformed

    private void PROCESAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROCESAR1ActionPerformed

        Integer a = 0;
        Integer totalLetras = 0;
        Integer enterito = 0;
        Integer fraccionado = 0;

        String seleccionado;

        try {

            if (objFechaHora.ConvierteFecha(fechaDocumento.getDate()).length() < 10) {
                JOptionPane.showMessageDialog(null, "1.Seleccione Fecha Documento", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            } else {

                if (lblNroDocumento.getText().toString().trim().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Ingrese Numero Documento", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    String docu = Valida_Documento();

                    entidadGuias = new Guias(
                            idbotica,
                            lblDescripcionAlmacen.getText().trim(),
                            docu, this.jTextField1.getText().trim(),
                            objFechaHora.ConvierteFecha(fechaDocumento.getDate()),
                            OpcionesMenu.getIdpersonal_botica(),
                            objFechaHora.ConvierteFecha(fechaRecepcion.getDate()),
                            lasboticas.getText().trim(), 0, MiSerie, MiNumero);

                    boolean grabar = false;
                    int suma = 0;

                    if (objGuias.guiaCargada(entidadGuias, 1, 1) == 0) {

                        int p = JOptionPane.showConfirmDialog(null, " Desea Realizar Este Cargo ?", "Confirmar",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (p == JOptionPane.YES_OPTION) {
                            grabar = true;

                            for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
                                fraccionado = 0;
                                enterito = 0;
                                enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(totalProductos, 3).toString());
                                fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(totalProductos, 3).toString());
                                suma = enterito + fraccionado;

                                if (suma >= 0) {
                                    grabar = true;
                                } else {
                                    grabar = false;
                                }
                                suma = 0;
                            }


                            if (grabar == true) {
                                Producto producto;
                                Movimiento_Detalle detalle;
                                List<Movimiento_Detalle> listProdCargo = new ArrayList<Movimiento_Detalle>();

                                for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
                                    producto = new Producto();
                                    detalle = new Movimiento_Detalle();
                                    enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(totalProductos, 3).toString());
                                    fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(totalProductos, 3).toString());
                                    producto.setIdProducto(modeloTabla.getValueAt(totalProductos, 0).toString());
                                    detalle.setId_Producto(producto);
                                    detalle.setUnidad(enterito);
                                    detalle.setFraccion(fraccionado);
                                    listProdCargo.add(detalle);
                                }

                                boolean resul = objGuias.CargarGuiaBotica(entidadGuias, observaciones.getText().trim().toUpperCase(), 1, listProdCargo, 1);

                                if (resul) {
                                    limpiarTodo(false);
                                    JOptionPane.showMessageDialog(null, "Su Cargo Ha Sido Realizado", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al Realizar su Cargo", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Operacion Fallido, verifique los valores", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Esta Guia ya ha Sido Cargada", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "1. Existen Caracteres Invalidos o se encuentra vacio los campos de Cantidad, fv verifique", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_PROCESAR1ActionPerformed

    private String Valida_Documento() {

        String valor = lblNroDocumento.getText().trim();
        String tempserie = "";
        String tempnumero = "";
        int posi = 0;

        for (int i = 0; i < valor.length(); i++) {
            if (valor.charAt(i) == '-') {
                posi = i;
                break;
            } else {
                tempserie = tempserie + valor.charAt(i);
            }
        }

        posi++;
        for (int k = posi; k < valor.length(); k++) {
            tempnumero = tempnumero + valor.charAt(k);
        }

        int miserie = Integer.parseInt(tempserie);
        int minumero = Integer.parseInt(tempnumero);

        DecimalFormat format = new DecimalFormat("00000");
        MiSerie = format.format(miserie);

        DecimalFormat format1 = new DecimalFormat("000000000000");
        MiNumero = format1.format(minumero);

        String docu = MiSerie + "-" + MiNumero;

        return docu;

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.jTable1.getRowCount() > 0) {
            int p = JOptionPane.showConfirmDialog(null, " Desea Cancelar este Cargo ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                limpiarTodo(true);
            }

        } else {
            limpiarTodo(true);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CerrarVentana();
}//GEN-LAST:event_jButton5ActionPerformed

    private void CerrarVentana() {
        if (this.jTable1.getRowCount() > 0) {

            int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                objinventario.Habilita(true);
                objinventario.marcacdo = false;
                this.dispose();
            }
        } else {
            objinventario.Habilita(true);
            objinventario.marcacdo = false;
            this.dispose();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton PROCESAR1;
    private javax.swing.JButton btnVisualizar;
    private org.jdesktop.swingx.JXDatePicker fechaDocumento;
    private org.jdesktop.swingx.JXDatePicker fechaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField lasboticas;
    private javax.swing.JTextField lblAlmacen;
    private javax.swing.JLabel lblDescripcionAlmacen;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTextField lblNroDocumento;
    private javax.swing.JTextField lblRuta;
    private javax.swing.JTextArea observaciones;
    // End of variables declaration//GEN-END:variables
}
