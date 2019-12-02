
package sistemanortfarma;

import CapaLogica.LogicaConexion;
import CapaLogica.LogicaDescuentosInv;
import CapaLogica.MiDefaultTableModel;
import entidad.DescuentosInvActualDetalle;
import entidad.DescuentosInvTipo;
import java.net.URL;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DescuentosAnioActual extends javax.swing.JInternalFrame {

    DescuentosInventarios objDescuentosInventarios;
    JDesktopPane desktopPane;
    Inventarios objInv;
    Connection con;
    Connection conex;
    LogicaConexion logconex = new LogicaConexion();
    LogicaDescuentosInv logicaDesc = new LogicaDescuentosInv();
    List<DescuentosInvActualDetalle> listaDesc;
    private DefaultTableModel tablaDesc;
    private String idPersonal;
    TableColumn columnaTablaDesc = null;
    Map parameters = new HashMap();
    URL report_file;
    VerificaSistema obj;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;
    OpcionesReportes obreporte;

    public DescuentosAnioActual() {
        initComponents();
        
    }

    private void descuentosMensual() {
        String sistema = "Windows";

        try {

            parameters.put("idPersonal", idPersonal);

            if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            } else {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            }

            report_file = this.getClass().getResource("/Reportes/rpt_Descuento_Full.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("RESUMEN DE DESCUENTOS");
            view.setVisible(true);
            view.setSize(850, 850);

        } catch (JRException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    DescuentosAnioActual(java.awt.Frame parent, DescuentosInventarios descInventarios, JDesktopPane desktop, Inventarios objInventarios) {
        initComponents();
        objDescuentosInventarios = descInventarios;
        idPersonal = objInventarios.getUsuario();
        desktopPane = desktop;
        tablaDesc = (DefaultTableModel) tblDesc.getModel();
        tablaDesc = new MiModeloTabla();
        LimpiatTablaDesc();
        construirDesc();
        muestraDescuentos();
        con = logconex.RetornaConexion();
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        txtAnio.setText(String.valueOf(anio).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDesc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(DescuentosAnioActual.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDesc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCUENTO", "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SET", "OCT", "NOV", "DIC"
            }
        ));
        tblDesc.setName("tblDesc"); // NOI18N
        jScrollPane1.setViewportView(tblDesc);
        tblDesc.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title0")); // NOI18N
        tblDesc.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title1")); // NOI18N
        tblDesc.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title2")); // NOI18N
        tblDesc.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title3")); // NOI18N
        tblDesc.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title4")); // NOI18N
        tblDesc.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title5")); // NOI18N
        tblDesc.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title6")); // NOI18N
        tblDesc.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title7")); // NOI18N
        tblDesc.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title8")); // NOI18N
        tblDesc.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title9")); // NOI18N
        tblDesc.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title10")); // NOI18N
        tblDesc.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title11")); // NOI18N
        tblDesc.getColumnModel().getColumn(12).setHeaderValue(resourceMap.getString("tblDesc.columnModel.title12")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setName("jPanel3"); // NOI18N

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtAnio.setText(resourceMap.getString("txtAnio.text")); // NOI18N
        txtAnio.setName("txtAnio"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        guardarDescuentos();
        LimpiatTablaDesc();
        construirDesc();
        muestraDescuentos();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        muestraDescuentos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        descuentosMensual();
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDesc;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    public class MiModeloTabla extends MiDefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == tablaDesc.getColumnCount() - 1) {
                return true;
            } else {
                return true;
            }
        }
    }

    private void LimpiatTablaDesc() {
        int cant = tblDesc.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaDesc.removeRow(i);
            }
        }
    }

    private void construirDesc() {
        tablaDesc.addColumn("DESCUENTO");
        tablaDesc.addColumn("ENE");
        tablaDesc.addColumn("FEB");
        tablaDesc.addColumn("MAR");
        tablaDesc.addColumn("ABR");
        tablaDesc.addColumn("MAY");
        tablaDesc.addColumn("JUN");
        tablaDesc.addColumn("JUL");
        tablaDesc.addColumn("AGO");
        tablaDesc.addColumn("SET");
        tablaDesc.addColumn("OCT");
        tablaDesc.addColumn("NOV");
        tablaDesc.addColumn("DIC");

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(0);
        columnaTablaDesc.setPreferredWidth(155);
        columnaTablaDesc.setMinWidth(155);
        columnaTablaDesc.setMaxWidth(155);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(1);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(2);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(3);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(4);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(5);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(6);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(7);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(8);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(9);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(10);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(11);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

        tblDesc.setModel(tablaDesc);
        columnaTablaDesc = tblDesc.getColumnModel().getColumn(12);
        columnaTablaDesc.setPreferredWidth(45);
        columnaTablaDesc.setMinWidth(45);
        columnaTablaDesc.setMaxWidth(45);

    }

    private void muestraDescuentos() {
        try {
                LimpiatTablaDesc();
            /*llenar detalle del cruce - tablas cargos, descargos y diferencia*/
                listaDesc = logicaDesc.ListaDescuentoInv();

                txtAnio.setText(listaDesc.get(0).getDescuentoMesActual().getAnio().toString());
                txtTotal.setText(String.valueOf(listaDesc.get(0).getDescuentoMesActual().getMonto()).toString());

                if(listaDesc.size() > 0) {
                    for(DescuentosInvActualDetalle desc : listaDesc) {;

                        Object [][] datosDesc = {{
                            desc.getDescuentoTipo().getNombreDescuento(),
                            desc.getMontoEnero(),
                            desc.getMontoFebrero(),
                            desc.getMontoMarzo(),
                            desc.getMontoAbril(),
                            desc.getMontoMayo(),
                            desc.getMontoJunio(),
                            desc.getMontoJulio(),
                            desc.getMontoAgosto(),
                            desc.getMontoSetiembre(),
                            desc.getMontoOctubre(),
                            desc.getMontoNoviembre(),
                            desc.getMontoDiciembre()

                        }};

                        if (tablaDesc == null) {
                            tablaDesc = new ModeloTabla() {

                                @Override
                                public boolean isCellEditable(int fila, int columna) {
                                    if (columna == tablaDesc.getColumnCount() - 1) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            };
                            tblDesc.setModel(tablaDesc);
                        } else {
                            tablaDesc.addRow(datosDesc[0]);
                        }
                    }

                }else {
                    JOptionPane.showMessageDialog(this, "No hay descuentos de inventarios", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
                    

                }

        } catch (Exception e) {
            System.out.println("Error al listar los descuentos "+e);
        }
    }

    private void guardarDescuentos() {
        int p = JOptionPane.showConfirmDialog(null, " Seguro de Guardar los Descuentos", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (p == JOptionPane.YES_OPTION) {

                    int val= 0;
                    for(int fila = 0; fila < tblDesc.getRowCount(); fila ++) {
                        DescuentosInvActualDetalle invActualDetalle = new DescuentosInvActualDetalle();
                        DescuentosInvTipo invTipoDescuento = new DescuentosInvTipo();

                        invTipoDescuento.setNombreDescuento(tblDesc.getValueAt(fila, 0).toString());
                        invActualDetalle.setDescuentoTipo(invTipoDescuento);
                        invActualDetalle.setMontoEnero(Double.parseDouble(tblDesc.getValueAt(fila, 1).toString()));
                        invActualDetalle.setMontoFebrero(Double.parseDouble(tblDesc.getValueAt(fila, 2).toString()));
                        invActualDetalle.setMontoMarzo(Double.parseDouble(tblDesc.getValueAt(fila, 3).toString()));
                        invActualDetalle.setMontoAbril(Double.parseDouble(tblDesc.getValueAt(fila, 4).toString()));
                        invActualDetalle.setMontoMayo(Double.parseDouble(tblDesc.getValueAt(fila, 5).toString()));
                        invActualDetalle.setMontoJunio(Double.parseDouble(tblDesc.getValueAt(fila, 6).toString()));
                        invActualDetalle.setMontoJulio(Double.parseDouble(tblDesc.getValueAt(fila, 7).toString()));
                        invActualDetalle.setMontoAgosto(Double.parseDouble(tblDesc.getValueAt(fila, 8).toString()));
                        invActualDetalle.setMontoSetiembre(Double.parseDouble(tblDesc.getValueAt(fila, 9).toString()));
                        invActualDetalle.setMontoOctubre(Double.parseDouble(tblDesc.getValueAt(fila, 10).toString()));
                        invActualDetalle.setMontoNoviembre(Double.parseDouble(tblDesc.getValueAt(fila, 11).toString()));
                        invActualDetalle.setMontoDiciembre(Double.parseDouble(tblDesc.getValueAt(fila, 12).toString()));

                        if (logicaDesc.ActualizaDescuentoActual(invActualDetalle)) {
                            val = 1;
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS HUBO UN ERROR AL GUARDAR LOS DESCUENTOS", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if(val == 1) {
                        JOptionPane.showMessageDialog(this, "Se han guardado los descuentos", "OK", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(this, "No se pudieron guardar los descuentos", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }


                }
    }

}
