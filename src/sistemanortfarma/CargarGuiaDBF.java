/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CargarGuiaDBF1.java
 *
 * Created on 30/01/2012, 06:17:44 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import entidad.Guias;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.linuxense.javadbf.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.TableColumn;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaProducto;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.TableView.TableRow;

/**
 * 
 */
/**
 *
 * @author Miguel Gomez S.
 */
public class CargarGuiaDBF extends javax.swing.JInternalFrame {

    Double stkSed;
    public DefaultTableModel modeloTabla;
    TableColumn columnaTabla = null;
    TableRow filaTabla = null;
    Integer fila = 0;
    List<Integer> anArray = new ArrayList<Integer>();
    LogicaGuias objGuias = new LogicaGuias();
    LogicaProducto objProducto = new LogicaProducto();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Inventarios objinventario;
    Guias entidadguias = null;
    Guias entidadguias_cabecera = null;
    LogicaBoticas objBoticas = new LogicaBoticas();
    String alma = "M";
    int unidad = 0;
    int fraccion = 0;
    private int idpersonal;
    private String idbotica;
    MuestraVentana objetoventana = new MuestraVentana();
    Mant_Productos mantProduc = new Mant_Productos();

    /** Creates new form CargarGuiaDBF1 */
    public CargarGuiaDBF(JFrame parent, Inventarios obj) {
        initComponents();
        objinventario = obj;
        modeloTabla = (DefaultTableModel) jTable1.getModel();
        AparienciaTabla();
        idpersonal = objinventario.getIdpesonal();
        idbotica = objinventario.getIdbotica();
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    public String agregarDetalleGuia() throws FileNotFoundException, IOException, SQLException {

        List<Guias> listaguias = new ArrayList<Guias>();
        String resul = "";
        String numero = "";

        try {

            InputStream inputStream = new FileInputStream(jTextField1.getText().toString()); // take dbf file as program argument
            DBFReader reader = new DBFReader(inputStream);
            Object[] rowObjects;

            while ((rowObjects = reader.nextRecord()) != null) {
                unidad = 0;
                fraccion = 0;

                Date fecha = (Date) rowObjects[2];
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                Date fecha2 = (Date) rowObjects[17];
                SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                String resultado2 = formato2.format(fecha2);

                if (rowObjects[13] == null) {
                    Integer isF = 0;
                    Double ge = 0.00;

                    for (Integer recorrido = 1; recorrido <= rowObjects[7].toString().length(); recorrido++) {
                        if (rowObjects[7].toString().toUpperCase().charAt(recorrido - 1) == 70) {
                            isF = 1;
                        }
                    }

                    if (isF == 1) {

                        ge = Double.valueOf(rowObjects[15].toString().trim());

                        if (ge < 1) {
                            ge = 1.00;
                        }

                        stkSed = Double.valueOf(rowObjects[7].toString().trim()) / Double.valueOf(rowObjects[15].toString().trim());

                    } else {
                        stkSed = Double.valueOf(rowObjects[7].toString().trim());
                    }
                } else {
                    stkSed = Double.valueOf(rowObjects[13].toString().trim());
                }

                numero = rowObjects[7].toString().trim();
                VerificaCantidad(numero);

                entidadguias = new Guias(alma,
                        idbotica,
                        lblProveedor.getText().toString(),
                        lblNroDocumento.getText().toString(),
                        resultado2,
                        Double.valueOf(rowObjects[11].toString().trim()),
                        idpersonal,
                        rowObjects[6].toString().trim(),
                        Double.valueOf(rowObjects[12].toString().trim()),
                        unidad,
                        fraccion);

                listaguias.add(entidadguias);
            }

            resul = objGuias.AgregarDetalleGuia(listaguias, entidadguias_cabecera);
            inputStream.close();

        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return resul;
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

    public void mostrarGuia(String ruta) throws FileNotFoundException, IOException {

        Integer habilitarBoton = 1;
        String tipoMovimeinto = null;
        Date fecha = null;
        Date fec1 = null;

        try {

            InputStream inputStream = new FileInputStream(ruta); // take dbf file as program argument
            DBFReader reader = new DBFReader(inputStream);

            Object[] rowObjects;
            Integer contador = 0;
            String fec;

            fec1 = logfecha.RetornaFecha();

            while ((rowObjects = reader.nextRecord()) != null) {
                Calendar Cal = Calendar.getInstance();
                fec = Cal.get(Calendar.DATE) + " / " + (Cal.get(Calendar.MONTH) + 1) + " / " + Cal.get(Calendar.YEAR);

                lbltipoMovimiento.setText("CZ");
                tipoMovimeinto = rowObjects[4].toString().trim();
                lblNroDocumento.setText(rowObjects[5].toString().trim());
                lblProveedor.setText(logfecha.MysqlToJuliano(rowObjects[10].toString().trim()));

                fecha = (Date) rowObjects[2];
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String resultado = formato.format(fecha);

                lblfchaDocto.setText(resultado);
                lblCarga.setText(fec.toString());

                Object[][] data = {
                    {rowObjects[6].toString().trim(),
                        rowObjects[9].toString().trim(),
                        rowObjects[8].toString().trim(),
                        rowObjects[7].toString().trim()
                    }};

                if (objGuias.ValidarProductosGuia(idbotica, rowObjects[6].toString().trim()) == 1) {
                } else {
                    anArray.add(contador);
                    habilitarBoton = 0;
                }

                contador++;
                modeloTabla.addRow(data[0]);
            }

            inputStream.close();

        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (anArray.size() > 0) {
            this.jButton4.setEnabled(true);
        }

        if (habilitarBoton == 1) {
            //VERIFICO SI LA GUIA ES PARA LA BOTICA
            boolean resultad = objBoticas.VerificaGuiaPertene(tipoMovimeinto);
            if (!resultad) {
                jButton4.setEnabled(false);
                jLabel7.setText("ERROR : ESTA GUIA NO PERTENECE A ESTA BOTICA ");
            } else {
                jButton4.setEnabled(true);
                jLabel7.setText("");
            }


        } else {
            jButton4.setEnabled(false);
            jLabel7.setText("Error : Lo sentimos existe un producto que no se encuentra en su Stock");
            jLabel7.setForeground(Color.red);
        }

        int diferencia = mantProduc.diferenciasDeFechas(fecha, fec1);

        if (diferencia > 30) {
            jButton4.setEnabled(false);
            jLabel7.setText("ERROR : Lo Sentimos esta Guia no puede cargarse en esta Fecha  ");
            jLabel7.setForeground(Color.red);
        }

    }

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            setBackground(Color.white);

            for (Integer inicio = 0; inicio < anArray.size(); inicio++) {

                if (row == anArray.get(inicio)) {
                    setBackground(Color.lightGray);

                }

            }

            super.getTableCellRendererComponent(table, value, false, false, 2, column);

            return this;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbltipoMovimiento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        lblCarga = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNroDocumento = new javax.swing.JLabel();
        lblfchaDocto = new javax.swing.JLabel();
        lblAlmacen = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CargarGuiaDBF.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(250, 20));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton2)
                .addGap(5, 5, 5)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        lbltipoMovimiento.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        lbltipoMovimiento.setForeground(resourceMap.getColor("lbltipoMovimiento.foreground")); // NOI18N
        lbltipoMovimiento.setText(resourceMap.getString("lbltipoMovimiento.text")); // NOI18N
        lbltipoMovimiento.setName("lbltipoMovimiento"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblProveedor.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        lblProveedor.setForeground(resourceMap.getColor("lblProveedor.foreground")); // NOI18N
        lblProveedor.setText(resourceMap.getString("lblProveedor.text")); // NOI18N
        lblProveedor.setName("lblProveedor"); // NOI18N

        lblCarga.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        lblCarga.setForeground(resourceMap.getColor("lblCarga.foreground")); // NOI18N
        lblCarga.setText(resourceMap.getString("lblCarga.text")); // NOI18N
        lblCarga.setName("lblCarga"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        lblNroDocumento.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        lblNroDocumento.setForeground(resourceMap.getColor("lblNroDocumento.foreground")); // NOI18N
        lblNroDocumento.setText(resourceMap.getString("lblNroDocumento.text")); // NOI18N
        lblNroDocumento.setName("lblNroDocumento"); // NOI18N

        lblfchaDocto.setFont(resourceMap.getFont("lblProveedor.font")); // NOI18N
        lblfchaDocto.setForeground(resourceMap.getColor("lblfchaDocto.foreground")); // NOI18N
        lblfchaDocto.setText(resourceMap.getString("lblfchaDocto.text")); // NOI18N
        lblfchaDocto.setName("lblfchaDocto"); // NOI18N

        lblAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen", "Trastienda" }));
        lblAlmacen.setEnabled(false);
        lblAlmacen.setName("lblAlmacen"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlmacen, 0, 95, Short.MAX_VALUE)
                    .addComponent(lbltipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbltipoMovimiento)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProveedor)
                    .addComponent(jLabel4)
                    .addComponent(lblNroDocumento)
                    .addComponent(lblAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblfchaDocto)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblCarga)))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setFont(resourceMap.getFont("jScrollPane1.font")); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Laboratorio", "Descripcion", "STKPRF"
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
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(280);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        }

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DBF (Foxpro Tables)", "DBF");
            filechooser.setFileFilter(filter);

            int result = filechooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                jTextField1.setText(String.valueOf(file));
                if (this.jTextField1.getText().trim().compareTo("") != 0) {
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                }
            
                
            }
            /*jTextField1.setText("C:/AENVIO/DT0000010.dbf"); //gino
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            */

        } catch (Exception ex) {
            System.out.println("cargar " + ex.getMessage());
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void verificaAlmacenes() {
        String G_ruta = jTextField1.getText();
        String tipalm = "";
        try {
            
        
        InputStream inputStream = new FileInputStream(G_ruta); // take dbf file as program argument
        DBFReader reader = new DBFReader(inputStream);
        Object[] rowObjects;

        while ((rowObjects = reader.nextRecord()) != null) {
            tipalm = rowObjects[3].toString().trim();
        }
            
            if (objBoticas.VerificaPoseeAlmacenes(this.idbotica)) {
                lblAlmacen.setEnabled(true);  
                if (tipalm.equals("T")){
                    lblAlmacen.setSelectedIndex(2);
                }
            } else {
                if (tipalm.equals("T")){
                    lblAlmacen.setSelectedIndex(2);
                }else{
                lblAlmacen.setEnabled(false);
                lblAlmacen.setSelectedIndex(0);
                }
            }
        
        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jTextField1.getText().length() > 0) {

            verificaAlmacenes();

            for (Integer inicio = 0; inicio < modeloTabla.getRowCount(); inicio++) {
                anArray.remove(inicio);
            }

            for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(inicio);
            }

            try {
                mostrarGuia(jTextField1.getText());
                if (jTable1.getRowCount() > 0) {
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(false);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        for (Integer inicio = 0; inicio < modeloTabla.getRowCount(); inicio++) {
            anArray.remove(inicio);
        }

        for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(inicio);
        }

        // lblAlmacen.setText("...");
        lbltipoMovimiento.setText("...");
        lblNroDocumento.setText("...");
        lblProveedor.setText("...");
        lblfchaDocto.setText("...");
        lblCarga.setText("...");
        jLabel7.setText("");
        jButton3.setEnabled(false);
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
        jButton4.setEnabled(false);


}//GEN-LAST:event_jButton3ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    objinventario.Habilita(true);
    objinventario.marcacdo = false;
    this.dispose();
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    String resultado = "";
    entidadguias_cabecera = null;
    alma = "M";

    int p = JOptionPane.showConfirmDialog(null, " Desea Realizar este Cargo ", "Confirmar",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    if (p == JOptionPane.YES_OPTION) {

        if (modeloTabla.getRowCount() > 0) {

            if (lblAlmacen.getSelectedIndex() == 1) {
                alma = "A";
            }else {  // desde aqui -- gino
                if (lblAlmacen.getSelectedIndex() == 2) {
                alma = "T";
                } // hasta aqui
            }

            entidadguias_cabecera = new Guias(alma, idbotica, lblProveedor.getText().toString(), lblNroDocumento.getText().toString(), lblfchaDocto.getText(), OpcionesMenu.getIdpersonal_botica());

            try {

                resultado = agregarDetalleGuia();

                if (resultado.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(null, "Guia Cargada", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                    lblAlmacen.setEnabled(false);
                    jButton1.setEnabled(true);
                    jButton4.setEnabled(false);
                    jButton3.setEnabled(false);
                } else {
                    if (resultado.compareTo("1") == 0) {
                        JOptionPane.showMessageDialog(null, "Guia ya cargada o Error en Estructura", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        jButton1.setEnabled(true);
                        jButton4.setEnabled(false);
                        jButton3.setEnabled(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Lo Sentimos no se Pudo Cargar su Guia ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        jButton1.setEnabled(true);
                        jButton4.setEnabled(false);
                        jButton3.setEnabled(false);
                    }
                }

                lblAlmacen.setEnabled(false);

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            jTextField1.setText("");

            for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(inicio);
            }

            lbltipoMovimiento.setText("...");
            lblNroDocumento.setText("...");
            lblProveedor.setText("...");
            lblfchaDocto.setText("...");
            lblCarga.setText("...");

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Guia", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}//GEN-LAST:event_jButton4ActionPerformed

private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
    // TODO add your handling code here:
}//GEN-LAST:event_jTable1PropertyChange

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    FormProductosNuevos objnuevo = new FormProductosNuevos(objetoventana, true);
    objnuevo.setVisible(true);
}//GEN-LAST:event_jButton6ActionPerformed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox lblAlmacen;
    private javax.swing.JLabel lblCarga;
    private javax.swing.JLabel lblNroDocumento;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblfchaDocto;
    private javax.swing.JLabel lbltipoMovimiento;
    // End of variables declaration//GEN-END:variables
}
