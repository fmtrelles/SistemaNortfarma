package sistemanortfarma;

import CapaLogica.LogicaProforma;
import CapaLogica.LogicaVenta;
import entidad.Proforma;
import entidad.Venta;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class ForMSalida_Producto extends javax.swing.JInternalFrame {

    private String idbotica;
    AplicacionVentas objventa;
    LogicaProforma objlogproforma = new LogicaProforma();
    LogicaVenta logventa = new LogicaVenta();
    List<Proforma> listaproforma = new ArrayList<Proforma>();
    List<Proforma> detalle_proforma = new ArrayList<Proforma>();
    private DefaultTableModel tablaproformas;
    Object[] datosProforma = new Object[3];
    private DefaultTableModel proformaDetalle;
    Object[] detalleProforma = new Object[5];
    TableColumnModel colModel;
    private DefaultTableModel modelventaDetalle;
    Object[] datosventa = new Object[4];
    MuestraVentana objetoventana = new MuestraVentana();
    private int filaeliminar;
    TableColumn colu;
    JCheckBox checkPromocion = new JCheckBox();

    /** Creates new form ForMSalida_Producto */
    public ForMSalida_Producto(AplicacionVentas obj) {
        initComponents();
        objventa = obj;
        idbotica = objventa.getIdbotica();
        colu = jtablaProforma.getColumnModel().getColumn(2);

        tablaproformas = (DefaultTableModel) jtablaProforma.getModel();
        proformaDetalle = (DefaultTableModel) tablaDetalleProforma.getModel();
        modelventaDetalle = (DefaultTableModel) tablaventa.getModel();

        colModel = this.tablaDetalleProforma.getColumnModel();

        AparienciaTabla();
        Oculta_Detalle_Venta(false);
    }

    private void AparienciaTabla() {

        try {

            JTableHeader cabecera = new JTableHeader(colModel);
            cabecera.setReorderingAllowed(false);

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            this.tablaDetalleProforma.getColumnModel().getColumn(2).setCellRenderer(tcenter);
            this.tablaDetalleProforma.getColumnModel().getColumn(3).setCellRenderer(tcenter);

            DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
            tcenter1.setHorizontalAlignment(SwingConstants.CENTER);
            this.tablaventa.getColumnModel().getColumn(2).setCellRenderer(tcenter);
            this.tablaventa.getColumnModel().getColumn(3).setCellRenderer(tcenter);
            this.tablaventa.getColumnModel().getColumn(4).setCellRenderer(tcenter);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void Lista_Proformas() {
        listaproforma = objlogproforma.Lista_Proformas(idbotica);

        for (int i = 0; i < listaproforma.size(); i++) {
            datosProforma[0] = listaproforma.get(i).getId_proforma();
            datosProforma[1] = listaproforma.get(i).getTotal();

            if (listaproforma.get(i).getAtendida() == 0) {
                checkPromocion.setSelected(true);
                datosProforma[2] = true;
            } else {
                checkPromocion.setSelected(false);
                datosProforma[2] = false;
            }


            colu.setCellEditor(new DefaultCellEditor(checkPromocion));
            tablaproformas.addRow(datosProforma);
        }

        jtablaProforma.setRequestFocusEnabled(true);
        jtablaProforma.getSelectionModel().setSelectionInterval(0, 0);
        jtablaProforma.changeSelection(0, 0, false, false);
        jtablaProforma.requestFocus();
    }

    private void RealizaOpciones(KeyEvent evt) {

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F12") == 0) {
            Nueva_Busqueda();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
            Cerrar_Ventana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            if (this.tablaventa.getRowCount() > 0) {
                Atender_Proforma();
            }
        }


    }

    private void Nueva_Busqueda() {

        if (jtablaProforma.getRowCount() > 0) {
            Confirmar p = new Confirmar(objetoventana, "<html> DESEA BUSCAR LAS SIGUIENTES PROFORMAS </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                Nueva_Proforma();
                Oculta_Detalle_Venta(false);
                Limpia_DetalleVenta();
                jLabel12.setText("");
                jLabel13.setText("");
                jLabel16.setText("");
                jTextField2.setText("");
                Deshabilita_BotonesProforma(false);
                Limpia_Proforma_1();
                listaproforma.removeAll(listaproforma);
                detalle_proforma.removeAll(detalle_proforma);
                Lista_Proformas();

            }

        } else {
            Lista_Proformas();
        }



    }

    private void Atender_Proforma() {
        String idventa = this.jLabel16.getText().trim();
        String idproforma = jTextField2.getText().trim();

        if (idventa.compareTo("") != 0 && idproforma.compareTo("") != 0) {

            Confirmar p = new Confirmar(objetoventana, "<html>DESEAS DESPACHAR ESTA PROFORMA </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {

                boolean resul = logventa.Despachar_Venta(idventa, idbotica, idproforma);

                if (resul) {
                    JOptionPane.showMessageDialog(this, " Proforma Atendida ", "OK", JOptionPane.INFORMATION_MESSAGE);
                    Nueva_Proforma();
                    Oculta_Detalle_Venta(false);
                    Limpia_DetalleVenta();
                    jLabel12.setText("");
                    jLabel13.setText("");
                    jLabel16.setText("");
                    tablaproformas.removeRow(filaeliminar);
                    jtablaProforma.getSelectionModel().setSelectionInterval(0, 0);
                    jtablaProforma.changeSelection(0, 0, false, false);
                    jtablaProforma.requestFocus();
                }
            }

        }

    }

    private void Nueva_Entrega() {
        if (tablaventa.getRowCount() > 0) {

            Confirmar p = new Confirmar(objetoventana, "<html> DESEAS ATENDER UNA NUEVA PROFORMA ?  </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                Nueva_Proforma();
                Oculta_Detalle_Venta(false);
                Limpia_DetalleVenta();
                jLabel12.setText("");
                jLabel13.setText("");
                jLabel16.setText("");
                jtablaProforma.getSelectionModel().setSelectionInterval(0, 0);
                jtablaProforma.changeSelection(0, 0, false, false);
                jtablaProforma.requestFocus();
            }

        }

    }

    private void Consultar_Estado() {
        String idproforma = this.jTextField2.getText().trim();
        boolean resul = objlogproforma.Veirifca_Proforma_Vendida(idbotica, idproforma);

        if (resul) {
            List<Venta> milista = logventa.Verifica_Ventas_Salida(idbotica, idproforma);

            if (milista.size() > 0) {

                jLabel12.setText(milista.get(0).getResponsable());
                jLabel13.setText(milista.get(0).getCajero());
                jLabel16.setText(milista.get(0).getId_Venta());

                for (int i = 0; i < milista.size(); i++) {
                    datosventa[0] = i + 1;
                    datosventa[1] = milista.get(i).getDescr_Producto() + " - " + milista.get(i).getId_Laboratorio();
                    datosventa[2] = milista.get(i).getUnidad();
                    datosventa[3] = milista.get(i).getFraccion();
                    modelventaDetalle.addRow(datosventa);
                }


            }

            Oculta_Detalle_Venta(true);

        } else {
            Oculta_Detalle_Venta(false);
        }

    }

    private void Oculta_Detalle_Venta(boolean valor) {
        jPanel5.setVisible(valor);
        tablaventa.setVisible(valor);
        jButton3.setVisible(valor);
        jPanel6.setVisible(valor);
        jButton8.setVisible(valor);

        jButton8.setEnabled(valor);
        jButton3.setEnabled(valor);

    }

    private void Cerrar_Ventana() {
        if (this.jtablaProforma.getRowCount() > 0) {

            Confirmar p = new Confirmar(objetoventana, "<html> DESEAS SALIR ?  </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                this.dispose();
                objventa.marcacdo = false;
            }
        } else {
            this.dispose();
            objventa.marcacdo = false;
        }

        objventa.requestFocus();

    }

    private void Nueva_Proforma() {
        jTextField2.setText("");
        Deshabilita_BotonesProforma(false);
        Limpia_DetalleProforma();
        detalle_proforma.removeAll(detalle_proforma);
        jtablaProforma.requestFocus();
        jtablaProforma.changeSelection(0, 0, false, false);

    }

    private void RecuperaProducto(int fila) {
        String idproforma = jtablaProforma.getValueAt(fila, 0).toString().trim();
        jTextField2.setText(idproforma);
        detalle_proforma.removeAll(detalle_proforma);
        detalle_proforma = objlogproforma.Recupera_Detalle_Proforma(idproforma, idbotica);

        for (int i = 0; i < detalle_proforma.size(); i++) {
            detalleProforma[0] = i + 1;
            detalleProforma[1] = detalle_proforma.get(i).getDescipcionproducto() + " - " + detalle_proforma.get(i).getId_laboratorio();
            detalleProforma[2] = detalle_proforma.get(i).getUNIDAD();
            detalleProforma[3] = detalle_proforma.get(i).getFRACCION();
            proformaDetalle.addRow(detalleProforma);
        }

        tablaDetalleProforma.requestFocus();
        tablaDetalleProforma.changeSelection(0, 0, false, false);
        Deshabilita_BotonesProforma(true);

    }

    private void Deshabilita_BotonesProforma(boolean valor) {
        jButton3.setEnabled(valor);
        jButton8.setEnabled(valor);
    }

    private void Limpia_Proforma_1() {
        int cant = tablaproformas.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproformas.removeRow(i);
            }
        }
    }

    private void Limpia_DetalleProforma() {
        int cant = proformaDetalle.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                proformaDetalle.removeRow(i);
            }
        }
    }

    private void Limpia_DetalleVenta() {
        int cant = modelventaDetalle.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelventaDetalle.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtablaProforma = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleProforma = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ForMSalida_Producto.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFocusable(false);
        setName("Form"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setFocusable(false);
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(916, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jtablaProforma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interno", "Total", "º"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtablaProforma.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtablaProforma.setName("jtablaProforma"); // NOI18N
        jtablaProforma.setSelectionBackground(resourceMap.getColor("jtablaProforma.selectionBackground")); // NOI18N
        jtablaProforma.setSelectionForeground(resourceMap.getColor("jtablaProforma.selectionForeground")); // NOI18N
        jtablaProforma.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaProforma.setShowHorizontalLines(false);
        jtablaProforma.getTableHeader().setReorderingAllowed(false);
        jtablaProforma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablaProformaMouseClicked(evt);
            }
        });
        jtablaProforma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablaProformaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaProformaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtablaProforma);
        jtablaProforma.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaProforma.getColumnModel().getColumn(0).setResizable(false);
        jtablaProforma.getColumnModel().getColumn(0).setPreferredWidth(60);
        jtablaProforma.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jtablaProforma.columnModel.title0")); // NOI18N
        jtablaProforma.getColumnModel().getColumn(1).setResizable(false);
        jtablaProforma.getColumnModel().getColumn(1).setPreferredWidth(60);
        jtablaProforma.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jtablaProforma.columnModel.title2")); // NOI18N
        jtablaProforma.getColumnModel().getColumn(2).setResizable(false);
        jtablaProforma.getColumnModel().getColumn(2).setPreferredWidth(15);
        jtablaProforma.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jtablaProforma.columnModel.title1")); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setFocusable(false);
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaDetalleProforma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "PRODUCTO", "UND", "FRACC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalleProforma.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaDetalleProforma.setName("tablaDetalleProforma"); // NOI18N
        tablaDetalleProforma.setSelectionBackground(resourceMap.getColor("tablaDetalleProforma.selectionBackground")); // NOI18N
        tablaDetalleProforma.setSelectionForeground(resourceMap.getColor("tablaDetalleProforma.selectionForeground")); // NOI18N
        tablaDetalleProforma.setShowHorizontalLines(false);
        tablaDetalleProforma.getTableHeader().setReorderingAllowed(false);
        tablaDetalleProforma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaDetalleProformaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalleProforma);
        tablaDetalleProforma.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaDetalleProforma.getColumnModel().getColumn(0).setResizable(false);
        tablaDetalleProforma.getColumnModel().getColumn(0).setPreferredWidth(10);
        tablaDetalleProforma.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title0")); // NOI18N
        tablaDetalleProforma.getColumnModel().getColumn(1).setResizable(false);
        tablaDetalleProforma.getColumnModel().getColumn(1).setPreferredWidth(250);
        tablaDetalleProforma.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title1")); // NOI18N
        tablaDetalleProforma.getColumnModel().getColumn(2).setResizable(false);
        tablaDetalleProforma.getColumnModel().getColumn(2).setPreferredWidth(20);
        tablaDetalleProforma.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title2")); // NOI18N
        tablaDetalleProforma.getColumnModel().getColumn(3).setResizable(false);
        tablaDetalleProforma.getColumnModel().getColumn(3).setPreferredWidth(20);
        tablaDetalleProforma.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setFocusable(false);
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel7.setBackground(resourceMap.getColor("jPanel7.background")); // NOI18N
        jPanel7.setFocusable(false);
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setForeground(resourceMap.getColor("jLabel17.foreground")); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addContainerGap(6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(557, 557, 557)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setFocusable(false);
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 150));

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setFocusable(false);
        jPanel6.setName("jPanel6"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "PRODUCTO", "UND", "FRACC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaventa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaventa.setName("tablaventa"); // NOI18N
        tablaventa.setSelectionBackground(resourceMap.getColor("tablaventa.selectionBackground")); // NOI18N
        tablaventa.setSelectionForeground(resourceMap.getColor("tablaventa.selectionForeground")); // NOI18N
        tablaventa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaventa.setShowHorizontalLines(false);
        tablaventa.getTableHeader().setReorderingAllowed(false);
        tablaventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaventaKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tablaventa);
        tablaventa.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaventa.getColumnModel().getColumn(0).setResizable(false);
        tablaventa.getColumnModel().getColumn(0).setPreferredWidth(5);
        tablaventa.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title0")); // NOI18N
        tablaventa.getColumnModel().getColumn(1).setResizable(false);
        tablaventa.getColumnModel().getColumn(1).setPreferredWidth(260);
        tablaventa.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title1")); // NOI18N
        tablaventa.getColumnModel().getColumn(2).setResizable(false);
        tablaventa.getColumnModel().getColumn(2).setPreferredWidth(20);
        tablaventa.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title2")); // NOI18N
        tablaventa.getColumnModel().getColumn(3).setResizable(false);
        tablaventa.getColumnModel().getColumn(3).setPreferredWidth(20);
        tablaventa.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablaDetalleProforma.columnModel.title3")); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton8))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtablaProformaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaProformaKeyReleased
    }//GEN-LAST:event_jtablaProformaKeyReleased

    private void jtablaProformaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaProformaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Limpia_DetalleVenta();
            Oculta_Detalle_Venta(false);
            jTextField2.setText("");
            Deshabilita_BotonesProforma(false);
            Limpia_DetalleProforma();
            detalle_proforma.removeAll(detalle_proforma);
            int fila = this.jtablaProforma.getSelectedRow();
            filaeliminar = fila;
            RecuperaProducto(fila);

            if (tablaDetalleProforma.getRowCount() > 0) {
                Consultar_Estado();
                tablaventa.requestFocus();
                tablaventa.getSelectionModel().setSelectionInterval(0, 0);
                tablaventa.changeSelection(0, 0, false, false);
            }

        }

        RealizaOpciones(evt);
    }//GEN-LAST:event_jtablaProformaKeyPressed

    private void jtablaProformaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaProformaMouseClicked
    }//GEN-LAST:event_jtablaProformaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Lista_Proformas();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton4KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton1KeyPressed

    private void tablaDetalleProformaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleProformaKeyPressed
        RealizaOpciones(evt);

        if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
            Nueva_Proforma();
        }

    }//GEN-LAST:event_tablaDetalleProformaKeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton3KeyPressed

    private void tablaventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaventaKeyPressed
        RealizaOpciones(evt);
        if (evt.getKeyText(evt.getKeyCode()).compareTo("Escape") == 0) {
            Nueva_Entrega();
        }

    }//GEN-LAST:event_tablaventaKeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Cerrar_Ventana();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Nueva_Entrega();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Atender_Proforma();
    }//GEN-LAST:event_jButton3ActionPerformed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable jtablaProforma;
    private javax.swing.JTable tablaDetalleProforma;
    private javax.swing.JTable tablaventa;
    // End of variables declaration//GEN-END:variables
}
