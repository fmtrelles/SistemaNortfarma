/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductosPorLaboratorioStock.java
 *
 * Created on 20/12/2011, 08:34:37 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaProducto;
import entidad.Producto;
import entidad.Tipo_Producto;
import java.net.URL;
import net.sf.jasperreports.engine.JasperPrint;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entidad.Laboratorios;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import CapaDatos.LaboratoriosData;
import CapaLogica.LogicaBoticas;
/**
 *
 * @author Miguel Gomez S. gomez
 */
public class ProductosPorLaboratorioStockTrastienda extends javax.swing.JInternalFrame {

    MuestraVentana objetoventana = new MuestraVentana();
    ModeloTabla modeloTabla = new ModeloTabla();
    LogicaProducto objProducto = new LogicaProducto();
    Connection con;
    ReportesInventarios objreporte;
    LogicaBoticas logbotica = new LogicaBoticas();
    private int stock;
    List<Tipo_Producto> Lista_Tipos = new ArrayList<Tipo_Producto>();
    private String TipoProducto;
    LaboratoriosData objLabs = new LaboratoriosData();
    Object[] Detalle = new Object[3];
    ArrayList datos = new ArrayList();
    private DefaultTableModel model;

    /** Creates new form ProductosPorLaboratorioStock */
    public ProductosPorLaboratorioStockTrastienda(ReportesInventarios obj, int mistock) {
        initComponents();
        objreporte = obj;
        con = obj.getCon();
        stock = mistock;//   0 : sin stock  1: con stock ; 2: con y sin stock
        model = (DefaultTableModel) jTable1.getModel();
        cargarTipoProducto();
        MuestraTitulo();
        ListaTipos();
        Buscar();
        this.jButton3.setVisible(false);
        this.jButton4.setVisible(false);
        this.lblLabCod01.setVisible(false);
        this.lblLabCod02.setVisible(false);
        this.lblLabDescripcion01.setVisible(false);
        this.lblLabDescripcion02.setVisible(false);
        OcultaLabel(false);

    }

    private void MuestraTitulo() {
        if (stock == 1) {
            this.jLabel1.setText("Listado Prod x Lab Sin Stock Trastienda");
        } 
    }

    private void ListaTipos() {
        Lista_Tipos = objProducto.ListarTipoProducto();
        for (int i = 0; i < Lista_Tipos.size(); i++) {
            this.jComboBox2.addItem(Lista_Tipos.get(i).getDescripcion());
        }
    }

    private void Buscar() {

        List<Laboratorios> ListaLabs = new ArrayList<Laboratorios>();
        LimpiatTabla1();
        String buscar = "";
        try {
            ListaLabs = objLabs.ListaLabsFiltroTrastienda("%" + buscar + "%", "");

            for (Integer inicio = 0; inicio < ListaLabs.size(); inicio++) {
                Detalle[0] = ListaLabs.get(inicio).getId_Lab();
                Detalle[1] = ListaLabs.get(inicio).getDescripcion();
                Detalle[2] = Boolean.FALSE;
                model.addRow(Detalle);
            }  
        
        } catch (Exception ex) {
            System.out.println("Error Filtrando:" + ex.getMessage());
        }

    }

    private void LimpiatTabla1() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblLabCod01 = new javax.swing.JLabel();
        lblLabCod02 = new javax.swing.JLabel();
        lblLabDescripcion01 = new javax.swing.JLabel();
        lblLabDescripcion02 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ProductosPorLaboratorioStockTrastienda.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        lblLabCod01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabCod01.setName("lblLabCod01"); // NOI18N

        lblLabCod02.setText(resourceMap.getString("lblLabCod02.text")); // NOI18N
        lblLabCod02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabCod02.setName("lblLabCod02"); // NOI18N

        lblLabDescripcion01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabDescripcion01.setName("lblLabDescripcion01"); // NOI18N

        lblLabDescripcion02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLabDescripcion02.setName("lblLabDescripcion02"); // NOI18N

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed1(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trastienda" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--------------------------------------------" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLabCod02, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLabCod01, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLabDescripcion01, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLabDescripcion02, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblLabDescripcion02, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLabDescripcion01, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLabCod01, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLabCod02, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        jCheckBox1.setLabel(resourceMap.getString("jCheckBox1.label")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jCheckBox1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName(resourceMap.getString("jPanel1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        objreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    }

    private void cargarTipoProducto() {

        for (Integer borrando = 0; borrando < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(borrando);
        }

        modeloTabla.addColumn("ITEM");
        modeloTabla.addColumn("DESCRIPCION");

        List<Tipo_Producto> lstPr = new ArrayList<Tipo_Producto>();
        lstPr = objProducto.Listar_Tipo_Producto();

        for (int i = 0; i < lstPr.size(); i++) {

            Object[][] data = {
                {
                    lstPr.get(i).getId_Tipo_Producto().toString(),
                    lstPr.get(i).getDescripcion().toString()}
            };

            modeloTabla.addRow(data[0]);
        }
    }
private void OcultaLabel(boolean valor) {
        this.jLabel6.setVisible(valor);
        this.jLabel7.setVisible(valor);
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        OcultaLabel(true);
        logbotica.InsertaTemp("","0");               
                
        for (int fila = 0; fila < this.jTable1.getRowCount(); fila++) {
           
           boolean valor = (Boolean) jTable1.getValueAt(fila,2);
            if (valor ){
            //if (this.jTable1.getValueAt(fila, 2).toString().compareTo("true") == 0) {
           
                String idLab = jTable1.getValueAt(fila, 0).toString();
                logbotica.InsertaTemp(idLab,"1");
            }
            
        }
        EjecutaReporteKardex(this.lblLabCod01.getText(), this.lblLabCod02.getText());
        this.jButton2.setEnabled(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed


    private void EjecutaReporteKardex(String inicio, String fin) {
        ReportHilo p = new ReportHilo(inicio, fin, con);
        p.start();
        
    }

    public class ReportHilo extends Thread {

        JasperPrint jasperPrint;
        Map parameters = new HashMap();
        JasperReport masterReport = null;
        Connection con;
        String report_file;
        private URL archivo;
        VerificaSistema obj;
        String inicio = "";
        String fin = "";
        String Tipo = "";
        
        public ReportHilo(String inicio, String fin, Connection con) {
            this.inicio = inicio;
            this.fin = fin;
            this.con = con;

        }

        public void run() {
            try {

                if (jComboBox2.getSelectedIndex() == 0) {
                    TipoProducto = "0";
                } else {
                    TipoProducto = Lista_Tipos.get(jComboBox2.getSelectedIndex() - 1).getId_Tipo_Producto();
                }
                Tipo = "1";
                String sistema = "Windows";
                parameters.put("Inicio", inicio);
                parameters.put("Fin", fin);
                parameters.put("Tipos", Tipo);
                parameters.put("OPCION", stock);
                parameters.put("ALMACEN", String.valueOf(jComboBox1.getSelectedItem().toString()));
                parameters.put("TipoProducto", TipoProducto);
                parameters.put("REPORT_CONNECTION", con);

                archivo = this.getClass().getResource("/Reportes/LabsConStock.jasper");

                if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setTitle("REPORTE DE INVENTARIOS");
                view.setVisible(true);
                view.setSize(850, 850);

                OcultaLabel(false);
                activa();

            } catch (JRException ex) {
                System.out.println("Error Generando Reporte:" + ex.getMessage());
            }
                
        }
        
    }

    private void activa(){
        this.jButton2.setEnabled(true);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //LabInicio p = new LabInicio(this, objetoventana);
        //LaboratoriosTotal p = new LaboratoriosTotal(this,objetoventana);

        //p.show(true);        // TODO add your handling code here:

        this.lblLabCod01.setText(LaboratoriosTotal.getIdLab());
        this.lblLabDescripcion01.setText(LaboratoriosTotal.getDescLab());
        this.lblLabCod02.setText("");
        this.lblLabDescripcion02.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //LabFin p = new LabFin(this, objetoventana);
        //p.show(true);        // TODO add your handling code here:

        this.lblLabCod02.setText(LabFin.getIdLab());
        this.lblLabDescripcion02.setText(LabFin.getDescLab());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed1
        //LabFin p = new LabFin(this, objetoventana);
        //p.show(true);        // TODO add your handling code here:

        this.lblLabCod02.setText(LabFin.getIdLab());
        this.lblLabDescripcion02.setText(LabFin.getDescLab());
    }//GEN-LAST:event_jButton3ActionPerformed1

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
//            Selecciona_Item();
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
  /*      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Selecciona_Item();
        }*/
}//GEN-LAST:event_jTable1KeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:

        if (this.jCheckBox1.isSelected()){
            for (int filax = 0; filax < this.jTable1.getRowCount(); filax++) {               
                jTable1.setValueAt(new Boolean(true),filax,2);
            }

        }else{
            for (int filax = 0; filax < this.jTable1.getRowCount(); filax++) {                
                jTable1.setValueAt(new Boolean(false),filax,2);
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblLabCod01;
    private javax.swing.JLabel lblLabCod02;
    private javax.swing.JLabel lblLabDescripcion01;
    private javax.swing.JLabel lblLabDescripcion02;
    // End of variables declaration//GEN-END:variables
}
