/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BuscarMovimientos.java
 *
 * Created on 12/03/2013, 09:23:05 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaTipoMovimiento;
import entidad.Boticas;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class BuscarVentasRealizadas extends javax.swing.JDialog {

    Object[] datosDetalle = new Object[9];
    Object[] datosDetalle2 = new Object[7];
    MiModelo modeloTabla;
    MiModelo modeloTabla_2;
    TableColumn colu;
    LogicaTipoMovimiento logMovimeinto = new LogicaTipoMovimiento();
    Movimientos movimiento = new Movimientos();
    Descargos descargos;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    CargosInventarios cargos;
    ProductosSAP ProdSAP;
    Descargos_Cli descargoscli;
    List<Movimiento_Detalle> listaDetalle = new ArrayList<Movimiento_Detalle>();
    int cargo;
    private int codcli;

    /** Creates new form BuscarMovimientos */
    

    public BuscarVentasRealizadas(java.awt.Frame parent, Descargos_Cli descli, boolean modal, int c_cli, String NOMCLI) {
        
        super(parent, modal);
        initComponents();
        descargoscli = descli;
        codcli = c_cli;
        setLocationRelativeTo(null);
        generarCabeceras();
        generarCabeceras_2();
        BuscarMovimientos();
        Activar(false);
        cargo = 0;
        this.jTextField1.setEditable(false);
        this.jTextField1.setText(NOMCLI);
        
        
    }

    private void generarCabeceras() {

        modeloTabla = new MiModelo();
        modeloTabla.addColumn("Botica");
        modeloTabla.addColumn("Interno");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("RUC");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Serie");
        modeloTabla.addColumn("Numero");
        modeloTabla.addColumn("Total");        
        jTable1.setModel(modeloTabla);
        AparienciaTabla();
    }

    private void generarCabeceras_2() {
        modeloTabla_2 = new MiModelo();
        modeloTabla_2.addColumn("Nº");
        modeloTabla_2.addColumn("Cod");
        modeloTabla_2.addColumn("Producto");
        modeloTabla_2.addColumn("Lab");
        modeloTabla_2.addColumn("Und");
        modeloTabla_2.addColumn("Fracc");
        modeloTabla_2.addColumn("PVP");
        jTable2.setModel(modeloTabla_2);
        AparienciaTabla2();
    }

    private void AparienciaTabla() {

        try {

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            jTable1.getColumnModel().getColumn(0).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(tcenter);

            colu = jTable1.getColumnModel().getColumn(0);
            colu.setPreferredWidth(40);
            colu.setMinWidth(40);
            colu.setMaxWidth(40);
            colu = jTable1.getColumnModel().getColumn(1);
            colu.setPreferredWidth(50);
            colu.setMinWidth(50);
            colu.setMaxWidth(50);
            colu = jTable1.getColumnModel().getColumn(2);
            colu.setPreferredWidth(70);
            colu.setMinWidth(70);
            colu.setMaxWidth(70);
            colu = jTable1.getColumnModel().getColumn(3);
            colu.setPreferredWidth(250);
            colu.setMinWidth(250);
            colu.setMaxWidth(250);
            colu = jTable1.getColumnModel().getColumn(4);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable1.getColumnModel().getColumn(5);
            colu.setPreferredWidth(130);
            colu.setMinWidth(130);
            colu.setMaxWidth(130);
            colu = jTable1.getColumnModel().getColumn(6);
            colu.setPreferredWidth(50);
            colu.setMinWidth(50);
            colu.setMaxWidth(50);
            colu = jTable1.getColumnModel().getColumn(7);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable1.getColumnModel().getColumn(8);
            colu.setPreferredWidth(50);
            colu.setMinWidth(50);
            colu.setMaxWidth(50);            

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
        }
    }

    private void AparienciaTabla2() {
        try {

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(5).setCellRenderer(tcenter);
            jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);
            DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
            tcenter1.setHorizontalAlignment(SwingConstants.RIGHT);
            jTable2.getColumnModel().getColumn(6).setCellRenderer(tcenter1);

            colu = jTable2.getColumnModel().getColumn(0);
            colu.setPreferredWidth(40);
            colu.setMinWidth(40);
            colu.setMaxWidth(40);
            colu = jTable2.getColumnModel().getColumn(1);
            colu.setPreferredWidth(40);
            colu.setMinWidth(40);
            colu.setMaxWidth(40);
            colu = jTable2.getColumnModel().getColumn(2);
            colu.setPreferredWidth(280);
            colu.setMinWidth(280);
            colu.setMaxWidth(280);
            colu = jTable2.getColumnModel().getColumn(3);
            colu.setPreferredWidth(60);
            colu.setMinWidth(60);
            colu.setMaxWidth(60);
            colu = jTable2.getColumnModel().getColumn(4);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(5);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);
            colu = jTable2.getColumnModel().getColumn(6);
            colu.setPreferredWidth(90);
            colu.setMinWidth(90);
            colu.setMaxWidth(90);

        } catch (Exception ex) {
            System.out.println("ERROR :" + ex.getMessage());
        }

    }

    private void Activar(boolean resul) {
        //jButton2.setEnabled(resul);
        jXDatePicker1.setEnabled(!resul);
        jXDatePicker2.setEnabled(!resul);
        jTextField1.setEnabled(!resul);
        //jButton1.setEnabled(!resul);
    }

    private void BuscarMovimientos() {

        LimpiatTabla();
        //String texto = jTextField1.getText().trim() + '%';
        
        movimiento.setFecha_Documento(this.jXDatePicker1.getDate());
        movimiento.setFecha_Recepcion(this.jXDatePicker2.getDate());
        //movimiento.setNumero_Documento(texto);
        movimiento.setCodcli(codcli);
        List<Movimientos> listaFacturas = logMovimeinto.Recupera_MovimientoFactura(movimiento,1);

        if (listaFacturas.size() > 0) {
            for (Movimientos movi : listaFacturas) {
                datosDetalle[0] = movi.getBotica().getId_Botica();
                datosDetalle[1] = movi.getId_Venta();
                datosDetalle[2] = movi.getFecha_Venta();
                datosDetalle[3] = movi.getNombre_RazonSocial();
                datosDetalle[4] = movi.getRUC_DNI();
                datosDetalle[5] = movi.getDescripcion();
                datosDetalle[6] = movi.getSerie();
                datosDetalle[7] = movi.getNumero();
                datosDetalle[8] = movi.getTotal();                
                modeloTabla.addRow(datosDetalle);
            }

        }
    }

    private void BuscarMovimeintoDetalle() {
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {
            LimpiatTabla_2();
            int cant = 1;
            String idbotica = this.jTable1.getValueAt(fila, 0).toString();
            String idventa = this.jTable1.getValueAt(fila, 1).toString();
            String fechavta = this.jTable1.getValueAt(fila, 2).toString();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;
            try {

                fecha = formatoDelTexto.parse(fechavta);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            
            
            Date fecha1 = null;
            String fechaini;
            
            fecha1 = (Date) fecha;
            

            fechaini = logfecha.ConvierteFecha(fecha1);
            
            

            Boticas botica = new Boticas();
            botica.setId_Botica(idbotica);
            movimiento.setBotica(botica);
            movimiento.setId_Venta(idventa);
           
            //movimiento.setTipo_movimiento(idmovi);
            //movimiento.setNumero_Documento(documento);

            listaDetalle.removeAll(listaDetalle);
            listaDetalle = logMovimeinto.Recupera_Movimiento_Detalle_Factura(movimiento,fechaini);

            if (listaDetalle.size() > 0) {

                for (Movimiento_Detalle detalle : listaDetalle) {
                    datosDetalle2[0] = cant;
                    datosDetalle2[1] = detalle.getId_Producto().getIdProducto();
                    datosDetalle2[2] = detalle.getId_Producto().getDescripcion();
                    datosDetalle2[3] = detalle.getId_Producto().getLaboratorio().getId_Lab();
                    datosDetalle2[4] = detalle.getUnidad();
                    datosDetalle2[5] = detalle.getFraccion();
                    datosDetalle2[6] = detalle.getTotal();
                    cant++;
                    modeloTabla_2.addRow(datosDetalle2);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "PORFAVOR SELECCIONE UNA FACTURA", "OK",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Cerrar() {
        this.dispose();
    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modeloTabla.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_2() {
        int cant = this.jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modeloTabla_2.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jTextField1 = new javax.swing.JTextField();
        button1 = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BuscarVentasRealizadas.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        button1.setLabel(resourceMap.getString("button1.label")); // NOI18N
        button1.setName("button1"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
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
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        }

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
        }

        jLabel5.setIcon(resourceMap.getIcon("jLabel5.icon")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Cerrar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        BuscarMovimeintoDetalle();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int deta = this.jTable2.getRowCount();
        
        if (deta > 0){
            descargoscli.AsigaProductos(listaDetalle);        
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UNA FACTURA", "OK",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        BuscarMovimientos();
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables
}
