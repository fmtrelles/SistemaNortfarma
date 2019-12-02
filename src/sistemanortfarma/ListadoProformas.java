package sistemanortfarma;

import CapaLogica.LogicaProforma;
import entidad.Proforma;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class ListadoProformas extends javax.swing.JDialog {

    List<Proforma> listaproforma = new ArrayList<Proforma>();
    private DefaultTableModel tablaproformas;
    Object[] arrayproductos = new Object[4];
    TableColumnModel colModel1;
    LogicaProforma objlogproforma = new LogicaProforma();
    AplicacionVentas objpricipal;
    String idbotica;
    private static String idproforma;
    Cotizacion obj;
    CruceInventarios objCruce;
    RealizaVenta objventa;
    int bandera;
    int numeros = 0;

    public ListadoProformas(java.awt.Frame parent, Cotizacion op) {
        super(parent, true);
        initComponents();
        bandera = 0;
        obj = op;
        setLocationRelativeTo(null);
        idbotica = objpricipal.getIdbotica();
        tablaproformas = (DefaultTableModel) jTable1.getModel();
        CargaDatos();
        jTextField1.requestFocus();
    }

    public ListadoProformas(java.awt.Frame parent, RealizaVenta op) {
        super(parent, true);
        initComponents();
        objventa = op;
        bandera = 1;
        setLocationRelativeTo(null);
        idbotica = objpricipal.getIdbotica();
        tablaproformas = (DefaultTableModel) jTable1.getModel();
        CargaDatos();
        jTextField1.requestFocus();
    }

    //CUANDO SE AGREGAN MAS DE UNA PROFORMA A LA VENTA
    public ListadoProformas(java.awt.Frame parent, RealizaVenta op, int num) {
        super(parent, true);
        initComponents();
        objventa = op;
        bandera = 1;
        setLocationRelativeTo(null);
        idbotica = objpricipal.getIdbotica();
        tablaproformas = (DefaultTableModel) jTable1.getModel();
        numeros = 1;
        CargaDatos();
        jTextField1.requestFocus();
    }

    public static String getIdproforma() {
        return idproforma;
    }

    public static void setIdproforma(String idproforma) {
        ListadoProformas.idproforma = idproforma;
    }

    public ListadoProformas(java.awt.Frame parent, CruceInventarios op) {
        super(parent, true);
        initComponents();
        objCruce = op;
        bandera = 2;
        setLocationRelativeTo(null);
        idbotica = Inventarios.getIdbotica();
        tablaproformas = (DefaultTableModel) jTable1.getModel();
        numeros = 1;
        CargaDatos();
        jTextField1.requestFocus();
    }

    private void CargaDatos() {
        try {

            String filtro = this.jTextField1.getText().trim();
            listaproforma = objlogproforma.Recupera_Proforma_Sensitiva(filtro, idbotica);
            LimpiatTabla();

            for (int i = 0; i < listaproforma.size(); i++) {
                arrayproductos[0] = listaproforma.get(i).getId_Proforma();
                arrayproductos[1] = listaproforma.get(i).getApellidoPersonal() + " " + listaproforma.get(i).getNombrePersonal();
                arrayproductos[2] = listaproforma.get(i).getNomCliente();
                arrayproductos[3] = listaproforma.get(i).getTotal();
                tablaproformas.addRow(arrayproductos);
            }

            if (listaproforma.size() > 0) {
                jTable1.getSelectionModel().setSelectionInterval(0, 0);
            }

        } catch (Exception ex) {
        }

    }

    private void RecuperaProforma() {

        if (jTable1.getRowCount() > 0) {
            int fila = jTable1.getSelectedRow();
            idproforma = String.valueOf(jTable1.getValueAt(fila, 0));

            if (idproforma.compareTo("") != 0) {

                if (bandera == 0) {
                    obj.AsignaDatos_Proforma(idproforma, listaproforma, fila);
                } else if(bandera == 1) {
                    if (numeros == 0) {
                        objventa.AsignaDatos_Proforma(idproforma, listaproforma, fila);
                        setIdproforma(idproforma);
                        hide();
                        objventa.Muestra_Sin_Stock();
                    } else {
                        objventa.AsignaDatos_Proforma_Adicional(idproforma);
                    }
                }else if(bandera == 2) {
                    objCruce.AsignaDatos_Proforma(idproforma, listaproforma, fila);
                }

                setIdproforma(idproforma);
                CerrarVentana();
            }
        }

    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproformas.removeRow(i);
            }
        }
    }

    private void CerrarVentana() {
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ListadoProformas.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        labelTask1.setText(resourceMap.getString("labelTask1.text")); // NOI18N
        labelTask1.setFocusable(false);
        labelTask1.setName("labelTask1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setFocusable(false);
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(resourceMap.getString("jTextField1.toolTipText")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setNextFocusableComponent(jTable1);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBackground(resourceMap.getColor("jTable1.background")); // NOI18N
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INTERNO", "VENDEDOR", "CLIENTE", "TOTAL"
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
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(220);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addComponent(labelTask1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        CargaDatos();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            RecuperaProforma();
        }
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            RecuperaProforma();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        RecuperaProforma();
    }//GEN-LAST:event_jTextField1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    // End of variables declaration//GEN-END:variables
}
