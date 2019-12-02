package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaTipoMovimiento;
import CapaLogica.LogicaVenta;
import entidad.Boticas;
import entidad.Guias;
import entidad.TipoDocumento;
import entidad.TipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Miguel Gomez S.
 */
public class AnulacionCargosDescargos extends javax.swing.JInternalFrame {

    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    ModeloTabla modeloTabla;
    TableColumn columnaTabla = null;
    List<TipoMovimiento> listaTipoMovimiento;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Guias> listguias;
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    LogicaTipoMovimiento objTipoMov = new LogicaTipoMovimiento();
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
    public static String proveedor;
    public static String tipalmacen;
    public static String IdTipoMovimiento;
    public static String TipoDocumento;
    
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Inventarios objinventario;
    private String idbotica;

    /** Creates new form AnulacionCargosDescargos */
    public AnulacionCargosDescargos(Inventarios obj) {
        modeloTabla = new ModeloTabla();
        objinventario = obj;
        idbotica = objinventario.getIdbotica();
        initComponents();
        construirCabecera();
        cargarBoticas2();
        CargarTipoMovimiento();
        AutoCompleteDecorator.decorate(tipoDocumentoVta);
    }

    public static String getIdTipoMovimiento() {
        return IdTipoMovimiento;
    }

    public static void setIdTipoMovimiento(String IdTipoMovimiento) {
        AnulacionCargosDescargos.IdTipoMovimiento = IdTipoMovimiento;
    }

    public static String getProveedor() {
        return proveedor;
    }

    public static void setProveedor(String proveedor) {
        AnulacionCargosDescargos.proveedor = proveedor;
    }

    public static String getTipalmacen() {
        return tipalmacen;
    }

    public static void setTipalmacen(String tipalmacen) {
        AnulacionCargosDescargos.tipalmacen = tipalmacen;
    }

    private void CargarTipoMovimiento() {

        listaTipoMovimiento = objTipoMov.ListarTipoMovimiento();
        tipoDocumentoVta.addItem("Seleccion");

        for (Integer ini = 0; ini < listaTipoMovimiento.size(); ini++) {
            tipoDocumentoVta.addItem(listaTipoMovimiento.get(ini).getDescripcionMovimiento());
        }

    }

    public static String getObservaciones() {
        return Observaciones;
    }

    public static void setObservaciones(String Observaciones) {
        AnulacionCargosDescargos.Observaciones = Observaciones;
    }

    public static String getFechaDocumento() {
        return FechaDocumento;
    }

    public static void setFechaDocumento(String FechaDocumento) {
        AnulacionCargosDescargos.FechaDocumento = FechaDocumento;
    }

    public static String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public static void setNumeroDocumento(String NumeroDocumento) {
        AnulacionCargosDescargos.NumeroDocumento = NumeroDocumento;
    }

    public static String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public static void setTipoMovimiento(String TipoMovimiento) {
        AnulacionCargosDescargos.TipoMovimiento = TipoMovimiento;
    }
    
     public static String getTipoDocumento() {
        return TipoDocumento;
    }

    public static void setTipoDocumento(String TipoDocumento) {
        AnulacionCargosDescargos.TipoDocumento = TipoDocumento;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lasboticas1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tipoDocumentoVta = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtND = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        desde = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        hasta = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(AnulacionCargosDescargos.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
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

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        lasboticas1.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas1.setEnabled(false);
        lasboticas1.setName("lasboticas1"); // NOI18N
        lasboticas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas1ActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

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

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtND.setName("txtND"); // NOI18N
        txtND.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNDFocusGained(evt);
            }
        });
        txtND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNDKeyReleased(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        desde.setFormats(new String[] { "dd/M/yyyy" });
        desde.setName("desde"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen", "Trastienda" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        hasta.setFormats(new String[] { "dd/M/yyyy" });
        hasta.setName("hasta"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(desde, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(hasta, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(lasboticas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoDocumentoVta, 0, 239, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tipoDocumentoVta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        }

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton2.setEnabled(true);

        if (evt.getClickCount() % 2 == 0) {
            Anular();
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void Anular() {

        if (jTable1.getSelectedRow() >= 0) {
            NumeroDocumento = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
            setNumeroDocumento(NumeroDocumento);

            FechaDocumento = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
            setFechaDocumento(FechaDocumento);

            TipoMovimiento = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            setTipoMovimiento(TipoMovimiento);

            proveedor = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
            this.setProveedor(proveedor);

            tipalmacen = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
            this.setTipalmacen(tipalmacen);

            this.IdTipoMovimiento = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            this.setIdTipoMovimiento(IdTipoMovimiento);
            
            TipoDocumento = this.tipoDocumentoVta.getSelectedItem().toString();
            this.setTipoDocumento(TipoDocumento);
            
            try {
                Observaciones = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
            } catch (Exception ex) {
                Observaciones = "";
            }

            setObservaciones(Observaciones);
            Ventana obj = new Ventana();
            objrelew = new DetalleMovimiento(obj, 0, objinventario.getIdpesonal(), 0, idbotica);
            objrelew.pack();
            objrelew.show(true);

            txtND.setText("");

            for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(seg);
            }

            listaTipoMovimiento.removeAll(listaTipoMovimiento);
            cargarMovimientosNoAnulados();

            NumeroDocumento = "";
            setNumeroDocumento(NumeroDocumento);
            FechaDocumento = "";
            setFechaDocumento(FechaDocumento);
            TipoMovimiento = "";
            setTipoMovimiento(TipoMovimiento);
            Observaciones = "";
            setObservaciones(Observaciones);

        }


    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
    }//GEN-LAST:event_jTable1KeyPressed

    private void lasboticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas1ActionPerformed
}//GEN-LAST:event_lasboticas1ActionPerformed

    private void tipoDocumentoVtaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoDocumentoVtaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoDocumentoVtaItemStateChanged

    private void cargarBoticas2() {

        List<Boticas> listaboticas20 = new ArrayList<Boticas>();
        List<Boticas> listaboticasX = new ArrayList<Boticas>();

        Integer BoticaOperando = 0;

        listaboticas20 = objBoticas.ListarobjBoticas();

        lasboticas1.addItem("Seleccione Opcion");

        for (int i = 0; i < listaboticas20.size(); i++) {
            lasboticas1.addItem(listaboticas20.get(i).getId_Botica());
        }

        listaboticasX = objBoticas.DescripcionBoticaDefault();
        System.out.println("listaboticasX.get(0).getId_Botica():" + listaboticasX.get(0).getId_Botica());

        if (listaboticasX.size() > 1) {
            System.out.println("IF");
            //Lo deja desbloqueado ps esta trabajando Inventarios
            lasboticas1.setEnabled(true);
        } else {
            //Bloquea por que es la botica la q trabaja
            System.out.println("ELSE");
            for (BoticaOperando = 0; BoticaOperando < lasboticas1.getItemCount(); BoticaOperando++) {

                lasboticas1.setSelectedIndex(BoticaOperando);

                if (lasboticas1.getItemAt(BoticaOperando).toString().compareTo(listaboticasX.get(0).getId_Botica()) == 0) {
                    BoticaOperando = lasboticas1.getItemCount() + 1000;
                    lasboticas1.setEnabled(false);
                }

            }

        }

    }

    private void construirCabecera() {

        modeloTabla.addColumn("CODIGO");
        modeloTabla.addColumn("MOVIMIENTO");
        modeloTabla.addColumn("DOCUMENTO");
        modeloTabla.addColumn("TIPO ALMACEN");
        modeloTabla.addColumn("PROVEEDOR");
        modeloTabla.addColumn("F. DOCTO");
        modeloTabla.addColumn("OBSERVACIONES");

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(200);
        columnaTabla.setMinWidth(200);
        columnaTabla.setMaxWidth(200);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(180);
        columnaTabla.setMinWidth(180);
        columnaTabla.setMaxWidth(180);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(100);
        columnaTabla.setMinWidth(100);
        columnaTabla.setMaxWidth(100);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

    }

    private void tipoDocumentoVtaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoDocumentoVtaFocusLost
        // TODO add your handling code here:
        for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(seg);
        }

        listaTipoMovimiento.removeAll(listaTipoMovimiento);
        cargarMovimientosNoAnulados();
}//GEN-LAST:event_tipoDocumentoVtaFocusLost

    private void txtNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDKeyReleased
        // TODO add your handling code here:

        if (Valida_Fechas()) {
            for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(seg);
            }

            listaTipoMovimiento.removeAll(listaTipoMovimiento);
            cargarMovimientosNoAnulados();
        } else {
            JOptionPane.showMessageDialog(this, " La Fecha de Inicio no puede ser Mayor a la Fecha de fin ", "Error", JOptionPane.ERROR_MESSAGE);
        }

}//GEN-LAST:event_txtNDKeyReleased

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.desde.getDate();
        java.util.Date fecha2 = hasta.getDate();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }


        return valor;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        objinventario.marcacdo = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNDFocusGained
        for (Integer seg = 0; seg < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(seg);
        }

        listaTipoMovimiento.removeAll(listaTipoMovimiento);
        cargarMovimientosNoAnulados();
    }//GEN-LAST:event_txtNDFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Anular();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cargarMovimientosNoAnulados() {

        if (lasboticas1.getSelectedIndex() > 0) {

            String fecha1 = logfecha.ConvierteFecha(desde.getDate());
            String fecha2 = logfecha.ConvierteFecha(hasta.getDate());
            String mialma = jComboBox1.getSelectedItem().toString();
            idbotica = lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString();
            String docu = '%' + this.txtND.getText().trim() + '%';
            String movimiento = tipoDocumentoVta.getItemAt(tipoDocumentoVta.getSelectedIndex()).toString();

            listaTipoMovimiento = objTipoMovimiento.ListarMovimientosCargosDescargos(idbotica, docu, movimiento, String.valueOf(mialma.charAt(0)), fecha1, fecha2);

            for (Integer i = 0; i < listaTipoMovimiento.size(); i++) {
                Object[][] data = {
                    {
                        listaTipoMovimiento.get(i).getId_TipoMovimiento(),
                        listaTipoMovimiento.get(i).getMovimiento(),
                        listaTipoMovimiento.get(i).getDescripcionMovimiento(),
                        listaTipoMovimiento.get(i).getDireccion(),
                        listaTipoMovimiento.get(i).getFRegistro(),
                        listaTipoMovimiento.get(i).getObservaciones()
                    }
                };

                modeloTabla.addRow(data[0]);

            }

            jTable1.setModel(modeloTabla);
        }

    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    }
    private String[] ArrayBoticas = {};
    private String[] ArrayBoticas2 = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker desde;
    private org.jdesktop.swingx.JXDatePicker hasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
