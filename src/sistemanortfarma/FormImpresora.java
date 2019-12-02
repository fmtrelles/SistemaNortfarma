/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormImpresora1.java
 *
 * Created on 24/07/2012, 07:39:06 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormImpresora extends javax.swing.JDialog {

    List<Venta> listaInternos = new ArrayList<Venta>();
    private String idbotica;
    Object[] datosDetalle = new Object[6];
    private DefaultTableModel model;
    JButton remover = new JButton("Reimpimir");
    TableColumn column;
    DefaultTableModel dm = new DefaultTableModel();
    TableColumn colu;
    JCheckBox checkPromocion = new JCheckBox();
    LogicaVenta logventa = new LogicaVenta();
    LogicaCPersonal logPersonal = new LogicaCPersonal();
    OpcionesMenu obj;
    LogicaBoticas objlistabotica = new LogicaBoticas();
    MailClient objmail = new MailClient();

    /** Creates new form FormImpresora1 */
    public FormImpresora(java.awt.Frame parent, boolean modal, String idboti, List<Venta> lista, String error) {
        super(parent, modal);
        initComponents();
        colu = jTable1.getColumnModel().getColumn(5);
        model = (DefaultTableModel) jTable1.getModel();
        idbotica = idboti;
        listaInternos = lista;
        jLabel3.setText("Faltan en Imprimir los siguientes Internos");
        jLabel2.setText("                   PORFAVOR REVISAR SU NUMERACION     ");
        jLabel4.setText("Error :  " + error);
        setLocationRelativeTo(null);
        AparienciaTabla();
        CargarInternos();
        EnviaCorreo(error);
        setDefaultCloseOperation(0);
    }

    private void EnviaCorreo(String error) {
        String correo = objlistabotica.ReornaCorreo(idbotica);
        objmail.sendMail(correo, "ERROR DE IMPRESION", "No se ha detectado la impresora \n en Botica Felicidad : " + idbotica + " \n Error : " + error);
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);

    }

    private void CargarInternos() {
        for (int i = 0; i < listaInternos.size(); i++) {
            if (listaInternos.get(i) != null) {
                datosDetalle[0] = i + 1;
                datosDetalle[1] = listaInternos.get(i).getId_Venta();
                datosDetalle[2] = listaInternos.get(i).getSerie();
                datosDetalle[3] = listaInternos.get(i).getNumero();
                datosDetalle[4] = listaInternos.get(i).getTotal();
                checkPromocion.setSelected(false);
                colu.setCellEditor(new DefaultCellEditor(checkPromocion));
                model.addRow(datosDetalle);
            }
        }
    }

    private void LeerImpresoras() {
        List<String> listaImpresoras = logPersonal.VerificaImpresora();
        try {
            obj.setImpresora_Boleta(listaImpresoras.get(0));
            obj.setImpresora_Factura(listaImpresoras.get(1));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        panelTranslucido1 = new org.edisoncor.gui.panel.PanelTranslucido();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormImpresora.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jLabel4.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "INTERNO", "SERIE", "NUMERO", "MONTO", "REIMPRIMIR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(55);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        panelTranslucido1.setName("panelTranslucido1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                            .addComponent(panelTranslucido1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = 0;
        LeerImpresoras();

        try {

            int p = JOptionPane.showConfirmDialog(null, " DESEA REIMPRIMIR EL INTERNO  " + model.getValueAt(fila, 1) + " ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {

                String interno = model.getValueAt(fila, 1).toString().trim();
                int idtipventa = logventa.Devuelve_Tpo_Venta(interno);

                if (idtipventa == 1) {
                    logventa.Imprime_Boleta(interno, idbotica);
                    dispose();
                } else {
                    if (idtipventa == 12) {
                        //logventa.Imprime_Factura(interno, idbotica);
                        logventa.ReImprime_Factura(listaInternos, idbotica);
                        dispose();
                    } else {
                        if (idtipventa == 11 ) {
                            boolean resul = logventa.ReImpresion_Ticketera(listaInternos, idbotica,idtipventa);
                            if (resul) {
                                model.removeRow(fila);
                                int cantidad = jTable1.getRowCount();

                                if (cantidad == 0) {
                                    this.dispose();
                                } else {
                                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                                        model.setValueAt(false, i, 5);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "  LO SENTIMOS ERROR AL REIMPRIMIR INTERNO ", "ERROR DE IMPRESION", JOptionPane.ERROR_MESSAGE);
                                model.setValueAt(false, fila, 5);
                            }
                        }
                    }
                }

            } else {
                model.setValueAt(false, fila, 5);
            }

        } catch (Exception ex) {
            System.out.println("REIMPRESION : " + ex.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.edisoncor.gui.panel.PanelTranslucido panelTranslucido1;
    // End of variables declaration//GEN-END:variables
}