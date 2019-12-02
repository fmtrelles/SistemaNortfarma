/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductosPromociones.java
 *
 * Created on 01/12/2012, 11:18:57 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaProducto;
import entidad.ListaDetalles;
import entidad.Personal;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import entidad.Venta;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class FrmDireccionesClientes extends javax.swing.JDialog {

    List<ListaDetalles> listaPromociones;    
    private String Id_Producto;
    //List<Venta> listventa = new ArrayList<Venta>();
    LogicaProducto logproducto = new LogicaProducto();
    private DefaultTableModel model;
    Object[] Detalle = new Object[4];
    TableColumnModel colModel;
    private double descuento;
    private String RUC;
    private String DIRE;
    private String RAZONSOCIAL;
    List<ListaDetalles> lista;
    List<Personal> listaDire;
    TableColumn colu_0, colu_2, colu_3;

    public FrmDireccionesClientes(java.awt.Frame parent,List<entidad.Personal> listadirecciones) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.jLabel1.setText("EMPRESA : " + listadirecciones.get(0).getSRVC_RAZONSOCIAL());
        model = (DefaultTableModel) jTable1.getModel();
        colModel = this.jTable1.getColumnModel();
        colu_2 = jTable1.getColumnModel().getColumn(2);
        AparienciaTabla();
        CargaDirecciones(listadirecciones);
        this.jTable1.requestFocus();
        this.jTable1.changeSelection(0, 7, false, true);
    }

    public String getRUC() {
        return RUC;
    }
    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getDIRE() {
        return DIRE;
    }
    public void setDIRE(String DIRE) {
        this.DIRE = DIRE;
    }

    public String getRAZONSOCIAL() {
        return RAZONSOCIAL;
    }
    public void setRAZONSOCIAL(String RAZONSOCIAL) {
        this.RAZONSOCIAL = RAZONSOCIAL;
    }

    


    private void AparienciaTabla() {

        try {

            JTableHeader cabecera = new JTableHeader(colModel);
            cabecera.setReorderingAllowed(false);

            JTableHeader cabecera1 = new JTableHeader(this.jTable1.getColumnModel());
            cabecera1.setReorderingAllowed(false);

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.LEFT);
            this.jTable1.getColumnModel().getColumn(0).setCellRenderer(tcenter);
            this.jTable1.getColumnModel().getColumn(1).setCellRenderer(tcenter);
            this.jTable1.getColumnModel().getColumn(2).setCellRenderer(tcenter);
            this.jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter);

            colu_0 = jTable1.getColumnModel().getColumn(0);
            colu_0.setPreferredWidth(0);
            colu_0.setMinWidth(0);
            colu_0.setMaxWidth(0);

            colu_2 = jTable1.getColumnModel().getColumn(2);
            colu_2.setPreferredWidth(0);
            colu_2.setMinWidth(0);
            colu_2.setMaxWidth(0);

            colu_3 = jTable1.getColumnModel().getColumn(3);
            colu_3.setPreferredWidth(0);
            colu_3.setMinWidth(0);
            colu_3.setMaxWidth(0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void CargaDirecciones(List<Personal> listaDire) {

        //lista = logproducto.Muestra_Promociones(Id_Producto);        

       
        if (listaDire.isEmpty()) {
            this.dispose();
        } else {           
            for (Personal detalle : listaDire) {
                Detalle[0] = detalle.getSRVC_RUC();
                Detalle[1] = detalle.getSRVC_DIRECCION();
                Detalle[2] = detalle.getSRVC_RAZONSOCIAL();
                Detalle[3] = detalle.getSRVC_ESTADO();
                model.addRow(Detalle);               
            }
        }


    }

    private void RecuperaDireccion() {
        int fila = this.jTable1.getSelectedRow();
        
        if (fila >= 0) {
            RUC = jTable1.getValueAt(fila, 0).toString();
            DIRE = jTable1.getValueAt(fila, 1).toString();
            RAZONSOCIAL = jTable1.getValueAt(fila, 2).toString();
            this.setRUC(RUC);
            this.setRAZONSOCIAL(RAZONSOCIAL);
            this.setDIRE(DIRE);

            CerrarVentana();
        }

    }

    private void RealizaOpciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
    }

    private void CerrarVentana() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        panelTranslucido1 = new org.edisoncor.gui.panel.PanelTranslucido();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FrmDireccionesClientes.class);
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        panelTranslucido1.setName("panelTranslucido1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUC", "DIRECCION", "RAZON SOCIAL", "ESTADO"
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
        jTable1.setGridColor(resourceMap.getColor("jTable1.gridColor")); // NOI18N
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
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
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jButton1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        RealizaOpciones(evt);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            RecuperaDireccion();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            RecuperaDireccion();
        }
    }//GEN-LAST:event_jTable1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private org.edisoncor.gui.panel.PanelTranslucido panelTranslucido1;
    // End of variables declaration//GEN-END:variables
}
