package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaTipoMovimiento;
import CapaLogica.LogicaVenta;
import entidad.Boticas;
import entidad.Guias;
import entidad.TipoDocumento;
import entidad.Venta;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class Anulaciones extends javax.swing.JInternalFrame {

    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    ModeloTabla modeloTabla;
    TableColumn columnaTabla = null;
    List<Venta> listaTipoMovimiento;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Guias> listguias;
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    LogicaVenta objVentas = new LogicaVenta();
    List<TipoDocumento> listventas;
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido = false;
    Integer totalLetras = 0;
    Integer a = 0;
    DetalleMovimiento objrelew;
    public static String NumeroDocumento;
    public static String FechaDocumento;
    public static String TipoMovimiento;
    public static String Observaciones;
    private static String idbotica;
    private static String tipo_almacen;
    private static String Id_tipovta;
    private int idtipoventa;
    AplicacionVentas objventas;

    public static String getTipo_almacen() {
        return tipo_almacen;
    }

    public static void setTipo_almacen(String tipo_almacen) {
        Anulaciones.tipo_almacen = tipo_almacen;
    }

    public static String getFechaDocumento() {
        return FechaDocumento;
    }

    public static void setFechaDocumento(String FechaDocumento) {
        Anulaciones.FechaDocumento = FechaDocumento;
    }

    public static String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public static void setNumeroDocumento(String NumeroDocumento) {
        Anulaciones.NumeroDocumento = NumeroDocumento;
    }

    public static String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public static void setTipoMovimiento(String TipoMovimiento) {
        Anulaciones.TipoMovimiento = TipoMovimiento;
    }

    public static String getObservaciones() {
        return Observaciones;
    }

    public static void setObservaciones(String Observaciones) {
        Anulaciones.Observaciones = Observaciones;
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        Anulaciones.idbotica = idbotica;
    }

    public static String getId_tipovta() {
        return Id_tipovta;
    }

    public static void setId_tipovta(String Id_tipovta) {
        Anulaciones.Id_tipovta = Id_tipovta;
    }

    /** Creates new form Anulaciones */
    public Anulaciones(String idboti, AplicacionVentas obj) {
        modeloTabla = new ModeloTabla();
        idbotica = idboti;
        initComponents();
        objventas = obj;
        construirCabecera();
        AparienciaTabla();
        cargarBoticas2();
        CargarTipoVenta();
        cargarMovimientosNoAnulados();
        txtND.requestFocus();
    }

    Anulaciones() {
        modeloTabla = new ModeloTabla();
        initComponents();
        construirCabecera();
        AparienciaTabla();
        cargarBoticas2();
        CargarTipoVenta();
        cargarMovimientosNoAnulados();
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lasboticas1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        tipoDocumentoVta = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtND = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Anulaciones.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        lasboticas1.setFont(resourceMap.getFont("tipoDocumentoVta.font")); // NOI18N
        lasboticas1.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas1.setEnabled(false);
        lasboticas1.setName("lasboticas1"); // NOI18N
        lasboticas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        tipoDocumentoVta.setFont(resourceMap.getFont("tipoDocumentoVta.font")); // NOI18N
        tipoDocumentoVta.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        tipoDocumentoVta.setName("tipoDocumentoVta"); // NOI18N
        tipoDocumentoVta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoDocumentoVtaItemStateChanged(evt);
            }
        });
        tipoDocumentoVta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tipoDocumentoVtaFocusLost(evt);
            }
        });

        jLabel9.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtND.setFont(resourceMap.getFont("tipoDocumentoVta.font")); // NOI18N
        txtND.setText(resourceMap.getString("txtND.text")); // NOI18N
        txtND.setName("txtND"); // NOI18N
        txtND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNDActionPerformed(evt);
            }
        });
        txtND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tipoDocumentoVta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoDocumentoVta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
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
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(80, 23));
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasboticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas1ActionPerformed
}//GEN-LAST:event_lasboticas1ActionPerformed

    private void Muestra_Anulacion() {

        int marcado = 0;
        String almacen = "";

        NumeroDocumento = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        setNumeroDocumento(NumeroDocumento);

        FechaDocumento = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        setFechaDocumento(FechaDocumento);

        almacen = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();

        Id_tipovta = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();

        /*  if (jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString().compareTo("") != 0) {
        marcado = 1;
        }*/

        //Id_Tipo_Venta

        try {
        if (Id_tipovta.equals("7")|| Id_tipovta.equals("0") /*|| Id_tipovta.equals("13")*/){
            
            Ventana obj = new Ventana();
            objrelew = new DetalleMovimiento(obj, marcado, objventas.getId_personal_botica(), 1, idbotica);
            objrelew.pack();
            objrelew.setVisible(true);

            txtND.setText("");

            for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(seg);
            }
            


            listaTipoMovimiento.removeAll(listaTipoMovimiento);
            cargarMovimientosNoAnulados();

        }else{
              JOptionPane.showMessageDialog(this, " LO SENTIMOS NO SE PUEDE ANULAR ESTE TIPO DE DOCUMENTO ", "Error", JOptionPane.ERROR_MESSAGE);
        }

            NumeroDocumento = "";
            setNumeroDocumento(NumeroDocumento);
            FechaDocumento = "";
            setFechaDocumento(FechaDocumento);


        } catch (Exception ex) {
            System.out.println("CLASE ANULACIONES " + ex.getMessage());
        }



    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Muestra_Anulacion();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Muestra_Anulacion();
        }

    }//GEN-LAST:event_jTable1KeyPressed

    private void txtNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDKeyReleased

    private void tipoDocumentoVtaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoDocumentoVtaItemStateChanged
        if (listventas.size() > 0) {
            idtipoventa = listventas.get(tipoDocumentoVta.getSelectedIndex()).getId_Tipo_Venta();
        }
    }//GEN-LAST:event_tipoDocumentoVtaItemStateChanged

    private void tipoDocumentoVtaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoDocumentoVtaFocusLost
    }//GEN-LAST:event_tipoDocumentoVtaFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        objventas.Habilita(true);
        objventas.marcacdo = false;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDKeyPressed
    }//GEN-LAST:event_txtNDKeyPressed

    private void txtNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNDActionPerformed

        for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(seg);
        }
        listaTipoMovimiento.removeAll(listaTipoMovimiento);
        cargarMovimientosNoAnulados();

    }//GEN-LAST:event_txtNDActionPerformed

    private void CargarTipoVenta() {

        listventas = objVentas.ListarTipoDocumento();

        for (Integer ini = 0; ini < listventas.size(); ini++) {
            this.tipoDocumentoVta.addItem(listventas.get(ini).getDescripcion());
        }

    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    private void cargarMovimientosNoAnulados() {

        String doc = this.txtND.getText().trim();
        doc = doc + '%';
        listaTipoMovimiento = objTipoMovimiento.Lista_Internos(idbotica, doc, idtipoventa);

        if (listaTipoMovimiento.size() > 0) {
            for (Integer i = 0; i < listaTipoMovimiento.size(); i++) {
                if (listaTipoMovimiento.get(i).getId_TipoPago() == 0) {
                    Object[][] data = {
                        {
                            listaTipoMovimiento.get(i).getId_Venta(),
                            listaTipoMovimiento.get(i).getFecha_Venta(),
                            listaTipoMovimiento.get(i).getSerie(),
                            listaTipoMovimiento.get(i).getNumero(),
                            listaTipoMovimiento.get(i).getTotal(),
                            "",listaTipoMovimiento.get(i).getId_Tipo_Venta()

                        }
                    };
                    modeloTabla.addRow(data[0]);
                } else {

                    Object[][] data = {
                        {
                            listaTipoMovimiento.get(i).getId_Venta(),
                            listaTipoMovimiento.get(i).getFecha_Venta(),
                            listaTipoMovimiento.get(i).getSerie(),
                            listaTipoMovimiento.get(i).getNumero(),
                            listaTipoMovimiento.get(i).getTotal(),
                            "CON NOTA DE CREDITO",listaTipoMovimiento.get(i).getId_Tipo_Venta()
                        }
                    };

                    modeloTabla.addRow(data[0]);
                }

            }

            jTable1.setModel(modeloTabla);

        }


    }

    private void construirCabecera() {

        modeloTabla.addColumn("INTERNO");
        modeloTabla.addColumn("FECHA VENTA");
        modeloTabla.addColumn("SERIE ");
        modeloTabla.addColumn("NUMERO");
        modeloTabla.addColumn("TOTAL");
        modeloTabla.addColumn("NC");
        modeloTabla.addColumn("tvta");

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(170);
        columnaTabla.setMinWidth(80);
        columnaTabla.setMaxWidth(80);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(90);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(110);
        columnaTabla.setMinWidth(110);
        columnaTabla.setMaxWidth(110);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
        columnaTabla.setPreferredWidth(20);
        columnaTabla.setMinWidth(90);
        columnaTabla.setMaxWidth(90);

    }

    private void cargarBoticas2() {

        listaboticas2 = objBoticas.ListarobjBoticas();
        lasboticas1.addItem("Seleccione Opcion");
        idbotica = objventas.getIdbotica();


        if (listaboticas2.size() > 0) {

            for (int i = 0; i < listaboticas2.size(); i++) {
                lasboticas1.addItem(listaboticas2.get(i).getDescripcion());

                if (idbotica.compareTo(listaboticas2.get(i).getId_Botica()) == 0) {
                    lasboticas1.setEnabled(false);
                    lasboticas1.setSelectedIndex(i + 1);
                }
            }

            listaboticas2 = objBoticas.DescripcionBoticaDefault();

            if (listaboticas2.size() > 1) {
                //Lo deja desbloqueado ps esta trabajando Inventarios
                lasboticas1.setEnabled(true);
            }


        }


    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    }
    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox lasboticas1;
    private javax.swing.JComboBox tipoDocumentoVta;
    private javax.swing.JTextField txtND;
    // End of variables declaration//GEN-END:variables
}
