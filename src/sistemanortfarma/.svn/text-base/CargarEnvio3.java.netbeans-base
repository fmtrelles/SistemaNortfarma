/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CargarEnvio3.java
 *
 * Created on 03-oct-2011, 11:57:36
 */
package sistemanortfarma;

import CapaDatos.ConfiguracionData;
import CapaDatos.ErroresData;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFamilias;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGenericos;
import java.io.File;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import CapaLogica.LogicaLaboratorios;
import CapaLogica.LogicaProducto;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;
import entidad.Boticas;
import entidad.Familias;
import entidad.Genericos;
import entidad.Laboratorios;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.Tipo_Producto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S.
 */
public class CargarEnvio3 extends javax.swing.JInternalFrame {

    int abierto;
    ConfiguracionData objCD = new ConfiguracionData();
    ErroresData objErrores = new ErroresData();
    Inventarios objinventario;
    VerificaSistema objsistema;

    public CargarEnvio3(Inventarios obj) {
        initComponents();
        objinventario = obj;
        Limpia();
    }

    private void Limpia() {

        jLabel5.setText("");
        jLabel8.setText("");
        jLabel11.setText("");
        jLabel14.setText("");
        jLabel17.setText("");

        jLabel4.setVisible(false);
        jLabel7.setVisible(false);
        jLabel10.setVisible(false);
        jLabel13.setVisible(false);
        jLabel16.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel1 = new javax.swing.JLabel();
        lblEstadoProceso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CargarEnvio3.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        panelCurves1.setName("panelCurves1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("lblEstadoProceso.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblEstadoProceso.setFont(resourceMap.getFont("lblEstadoProceso.font")); // NOI18N
        lblEstadoProceso.setText(resourceMap.getString("lblEstadoProceso.text")); // NOI18N
        lblEstadoProceso.setName("lblEstadoProceso"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(32, 30));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(32, 30));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setIcon(resourceMap.getIcon("jLabel10.icon")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(32, 30));

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setIcon(resourceMap.getIcon("jLabel13.icon")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(32, 30));

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setIcon(resourceMap.getIcon("jLabel16.icon")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(32, 30));

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEstadoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                            .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(24, 24, 24))
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblEstadoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelCurves1Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel8))
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ARJ (ARJ FILES)", "ARJ");
        filechooser.setFileFilter(filter);

        int result = filechooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            jTextField1.setText(String.valueOf(file));

            if (this.jTextField1.getText().length() > 0) {
                jButton2.setEnabled(true);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ParallelTask pt1 = new ParallelTask();
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(pt1);
        Deshabilita(false);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void Deshabilita(boolean valor) {
        this.jButton2.setEnabled(valor);
        this.jButton1.setEnabled(valor);
        this.jButton3.setEnabled(valor);
        this.jTextField1.setEnabled(valor);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        objinventario.marcacdo = false;
    }//GEN-LAST:event_jButton3ActionPerformed

    public class ParallelTask implements Runnable {
        //Variables de la tarea paralela

        public void run() {
            //Ejecucion de la Tarea
            HiloCargarEnvio hilo;
            hilo = new HiloCargarEnvio(jTextField1.getText());
            hilo.start();
        }
    }

    public class HiloCargarEnvio extends Thread {

        public DefaultTableModel modeloTabla;
        LogicaLaboratorios objLaboratorios = new LogicaLaboratorios();
        LogicaProducto objProducto = new LogicaProducto();
        LogicaFamilias objFamilias = new LogicaFamilias();
        LogicaGenericos objGenericos = new LogicaGenericos();
        Laboratorios entidadlabs;
        Laboratorios entidad;
        Familias entidadF;
        Genericos entidadG;
        Producto entidad2;
        public Integer contador = 0;
        List<Boticas> listaboticas;
        LogicaBoticas objBoticas = new LogicaBoticas();
        List<ProductosPrecios> listProducto = new ArrayList<ProductosPrecios>();
        List<Familias> listFamilias = new ArrayList<Familias>();
        List<Genericos> listGenericos = new ArrayList<Genericos>();
        List<ProductosPrecios> listProducto2 = new ArrayList<ProductosPrecios>();
        List<Laboratorios> listLabs = new ArrayList<Laboratorios>();
        LogicaFechaHora objFechaHora = new LogicaFechaHora();
        private String Ip = "";
        private String ruta = "";
        private Integer fila;
        MuestraVentana objetoventana = new MuestraVentana();
        Thread showThread;

        public HiloCargarEnvio(String ruta) {
            this.ruta = ruta;
        }

        public void run() {
            Ip = "-";
            fila = 1;

            try {
                try {
                    if (abrirDBF(ruta)) {
                        lblEstadoProceso.setText("Iniciando Proceso");
                        /*Procesando familia*/
                        InputStream inputStream = null;
                        DBFReader reader = null;
                        Object[] rowObjects = null;

                        try {

                            try {
                                inputStream = new FileInputStream(objCD.recuperaRutaDefaultLinux().toString() + "FAMILIAS.DBF"); // take dbf file as program argument
                            } catch (Exception ex) {
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00000");
                                lblEstadoProceso.setText("Error de Ruta." + ex.getMessage());
                                JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                jButton1.setEnabled(true);
                                jButton3.setEnabled(true);
                                jButton2.setEnabled(true);
                            }

                        } catch (FileNotFoundException ex) {
                            lblEstadoProceso.setText("Error 01." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00001");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            jButton1.setEnabled(true);
                            jButton3.setEnabled(true);
                            jButton2.setEnabled(true);
                        }

                        try {
                            reader = new DBFReader(inputStream);
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 02." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00002");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        /*INICIANDO FAMILIAS*/

                        try {

                            jLabel5.setText(" Procesando Familias !! Porfavor Espere ");
                            jLabel4.setVisible(true);

                            while ((rowObjects = reader.nextRecord()) != null) {
                                entidadF = new Familias();
                                entidadF.setId_Familia(rowObjects[0].toString().trim());
                                entidadF.setDescripcion(rowObjects[1].toString().trim());
                                listFamilias.add(entidadF);
                                contador++;
                            }

                            objFamilias.ActualizaFamilias(listFamilias);
                            lblEstadoProceso.setText("Familias Actualizadas");
                            jLabel5.setText(" Familias Actualizadas !! OK ");
                            jLabel4.setVisible(false);

                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 16." + ex.getMessage());
                            jLabel5.setText(ex.getMessage());
                            jLabel4.setVisible(false);
                            listFamilias.removeAll(listFamilias);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00003");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }


                        try {
                            inputStream.close();
                        } catch (IOException ex) {
                            lblEstadoProceso.setText("Error 04." + ex.getMessage());
                            listFamilias.removeAll(listFamilias);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00004");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }


                        /*FIN PROCESANDO FAMILIA*/

                        /*ACTUALIZANDO GENERICOS*/

                        inputStream = null;

                        jLabel8.setText(" Procesando Genericos !! Porfavor Espere ");
                        jLabel7.setVisible(true);

                        try {
                            try {
                                // take dbf file as program argument
                                inputStream = new FileInputStream(objCD.recuperaRutaDefaultLinux().toString() + "GENERICO.DBF"); // take dbf file as program argument
                            } catch (Exception ex) {
                                lblEstadoProceso.setText("Error de Ruta." + ex.getMessage());
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00000");
                                JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (FileNotFoundException ex) {
                            lblEstadoProceso.setText("Error 01." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00001");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }


                        reader = null;

                        try {
                            reader = new DBFReader(inputStream);
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 02." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00002");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        try {

                            while ((rowObjects = reader.nextRecord()) != null) {
                                entidadG = new Genericos();
                                entidadG.setId_Generico(rowObjects[0].toString().trim());
                                entidadG.setDescripcionGenerico(rowObjects[1].toString().trim());
                                listGenericos.add(entidadG);
                                contador++;
                            }

                            objGenericos.ActualizaGenericos(listGenericos);
                            lblEstadoProceso.setText("Genericos Actualizados");
                            jLabel8.setText(" Genericos Actualizadas !! OK ");
                            jLabel7.setVisible(false);


                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 16." + ex.getMessage());
                            jLabel8.setText(ex.getMessage());
                            jLabel7.setVisible(false);
                            listGenericos.removeAll(listGenericos);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00003");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            inputStream.close();
                        } catch (IOException ex) {
                            lblEstadoProceso.setText("Error 04." + ex.getMessage());
                            listGenericos.removeAll(listGenericos);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00004");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        /*FINALIZANDO GENERICOS*/

                        inputStream = null;

                        jLabel11.setText(" Procesando Laboratorios !! Porfavor Espere ");
                        jLabel10.setVisible(true);

                        try {
                            try {                            
                                inputStream = new FileInputStream(objCD.recuperaRutaDefaultLinux().toString() + "PROVLAB.DBF"); // take dbf file as program argument
                            } catch (Exception ex) {
                                lblEstadoProceso.setText("Error de Ruta." + ex.getMessage());
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00000");
                                JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (FileNotFoundException ex) {
                            lblEstadoProceso.setText("Error 01." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00001");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        reader = null;

                        try {
                            reader = new DBFReader(inputStream);
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 02." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00002");
                            JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            while ((rowObjects = reader.nextRecord()) != null) {
                                if (rowObjects[1].toString().trim().compareTo("L") == 0) {                                    
                                    entidad = new Laboratorios(rowObjects[2].toString().trim(), rowObjects[3].toString().trim());
                                    listLabs.add(entidad);
                                }
                                contador++;
                            }

                            try {

                                objLaboratorios.ActualizaLabsBotic(listLabs, Ip, fila);
                                lblEstadoProceso.setText("Laboratorios Actualizados");
                                jLabel11.setText(" Laboratorios Actualizadas !! OK ");
                                jLabel10.setVisible(false);

                            } catch (SQLException ex) {
                                lblEstadoProceso.setText("Error 03." + ex.getMessage());
                                jLabel11.setText(ex.getMessage());
                                jLabel10.setVisible(false);
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00003");
                            }


                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 16." + ex.getMessage());
                            jLabel11.setText(ex.getMessage());
                            jLabel10.setVisible(false);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00004");
                        }

                        try {
                            inputStream.close();
                            jLabel10.setVisible(false);
                        } catch (IOException ex) {
                            lblEstadoProceso.setText("Error 04." + ex.getMessage());
                        }

                        /* ACTUALIZADO PRODUCTOS*/

                        inputStream = null;

                        jLabel14.setText(" Procesando Laboratorios !! Porfavor Espere ");
                        jLabel13.setVisible(true);

                        try {
                            try {
                                inputStream = new FileInputStream(objCD.recuperaRutaDefaultLinux().toString() + "NUEVO.DBF"); // take dbf file as program argument
                            } catch (Exception ex) {
                                lblEstadoProceso.setText("Error RUTA." + ex.getMessage());
                                objErrores.escribirLogErrores(ex.getMessage() + "INV000004");
                            }
                        } catch (FileNotFoundException ex) {
                            lblEstadoProceso.setText("Error 05." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00001");
                        }
                        reader = null;
                        try {
                            reader = new DBFReader(inputStream);
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 06." + ex.getMessage());
                        }
                        try {
                            Producto producto;
                            Laboratorios laboratorio;
                            Productos_Botica productoBotica;
                            ProductosPrecios productoPrecios;
                            Tipo_Producto tipoProducto;

                            while ((rowObjects = reader.nextRecord()) != null) {
                                producto = new Producto();
                                laboratorio = new Laboratorios();
                                productoBotica = new Productos_Botica();
                                productoPrecios = new ProductosPrecios();
                                tipoProducto = new Tipo_Producto();

                                laboratorio.setId_Lab(rowObjects[2].toString().trim());
                                producto.setLaboratorio(laboratorio);
                                producto.setIdProducto(rowObjects[0].toString().trim());
                                producto.setDescripcion(rowObjects[1].toString().trim());
                                producto.setMiEmpaque(rowObjects[8].toString().trim());
                                producto.setIGV_Exonerado(Double.parseDouble(rowObjects[9].toString().trim()));
                                tipoProducto.setId_Tipo_Producto(rowObjects[3].toString().trim());
                                producto.setTipoProducto(tipoProducto);
                                productoBotica.setProducto(producto);
                                productoPrecios.setProductoBotica(productoBotica);
                                productoPrecios.setPrecio_Venta(Double.parseDouble(rowObjects[5].toString().trim()));
                                productoPrecios.setDescuento_Venta(Double.parseDouble(rowObjects[4].toString().trim()));
                                listProducto2.add(productoPrecios);
                            }
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 07." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00002");
                        }

                        try {
                            objProducto.AgregaProductoEnvio(listProducto2);
                            lblEstadoProceso.setText("Productos Nuevos Agregados");
                            jLabel14.setText(" Productos Nuevos Agregados !! OK ");
                            jLabel13.setVisible(false);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                            jLabel14.setText(ex.getMessage());
                            jLabel13.setVisible(false);
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00003");
                        }

                        try {
                            inputStream.close();
                        } catch (IOException ex) {
                            lblEstadoProceso.setText("Error 08." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00004");
                        }
                        try {

                            inputStream = null;
                            try {
                                inputStream = new FileInputStream(objCD.recuperaRutaDefaultLinux().toString() + "PRODUCTO.DBF"); // take dbf file as program argument

                            } catch (Exception ex) {
                                lblEstadoProceso.setText("Error Ruta." + ex.getMessage());
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00000");
                            }
                            reader = null;
                            try {
                                reader = new DBFReader(inputStream);
                            } catch (DBFException ex) {
                                lblEstadoProceso.setText("Error 09." + ex.getMessage());
                            }

                        } catch (FileNotFoundException ex) {
                            lblEstadoProceso.setText("Error 10." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00001");
                        }

                        jLabel17.setText(" Actualizando Precios !! Porfavor Espere ");
                        jLabel16.setVisible(true);

                        try {

                            Producto producto;
                            Laboratorios laboratorio;
                            Familias familia;
                            Genericos generico;
                            Productos_Botica productoBotica;
                            ProductosPrecios productoPrecios;
                            Tipo_Producto tipoProducto;

                            while ((rowObjects = reader.nextRecord()) != null) {
                                Integer codFammm = 0;
                                Integer codGennn = 0;
                                producto = new Producto();
                                familia = new Familias();
                                generico = new Genericos();
                                laboratorio = new Laboratorios();
                                productoBotica = new Productos_Botica();
                                productoPrecios = new ProductosPrecios();
                                tipoProducto = new Tipo_Producto();

                                try {
                                    codFammm = Integer.valueOf(rowObjects[9].toString());
                                } catch (Exception ex) {
                                    codFammm = 9999999;
                                }

                                try {
                                    codGennn = Integer.valueOf(rowObjects[10].toString());
                                } catch (Exception ex) {
                                    codGennn = 9999999;
                                }

                                laboratorio.setId_Lab(rowObjects[2].toString().trim());
                                producto.setLaboratorio(laboratorio);
                                producto.setIdProducto(rowObjects[0].toString().trim());
                                tipoProducto.setId_Tipo_Producto(rowObjects[3].toString().trim());
                                producto.setTipoProducto(tipoProducto);
                                producto.setDescripcion(rowObjects[1].toString().trim());
                                familia.setId_Familia(String.valueOf(codFammm));
                                producto.setIdFamilia(familia);
                                generico.setId_Generico(String.valueOf(codGennn));
                                producto.setIdGenerico(generico);
                                producto.setMiEmpaque(rowObjects[7].toString());
                                producto.setIGV_Exonerado((Double) rowObjects[8]);
                                productoBotica.setProducto(producto);
                                productoPrecios.setProductoBotica(productoBotica);
                                productoPrecios.setPrecio_Venta((Double) rowObjects[4]);
                                productoPrecios.setDescuento_Venta((Double) rowObjects[5]);
                                productoPrecios.setPrecioBotiquin((Double) rowObjects[6]);
                                listProducto.add(productoPrecios);

                            }

                            try {
                                objProducto.ActualizaProductoEnvio(listProducto, Ip, fila);
                                lblEstadoProceso.setText("Actualizando Precios");
                                jLabel17.setText(" Precios Actualizados !! OK ");
                                jLabel16.setVisible(false);
                            } catch (SQLException ex) {
                                lblEstadoProceso.setText("Error 11." + ex.getMessage());
                                jLabel17.setText(ex.getMessage());
                                jLabel16.setVisible(false);
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00002");
                                jLabel16.setVisible(false);
                            }

                            try {
                                inputStream.close();
                            } catch (IOException ex) {
                                lblEstadoProceso.setText("Error 12." + ex.getMessage());
                                objErrores.escribirLogErrores(ex.getMessage() + "INV00003");
                            }
                        } catch (DBFException ex) {
                            lblEstadoProceso.setText("Error 13." + ex.getMessage());
                            objErrores.escribirLogErrores(ex.getMessage() + "INV00004");
                        }

                        /* Productos ACTUALIZADOS*/
                        jButton1.setEnabled(true);
                        jButton3.setEnabled(true);
                        jButton2.setEnabled(true);
                        BorarArchivo();
                        lblEstadoProceso.setText("Proceso Finalizado");
                        JOptionPane.showMessageDialog(this.objetoventana, "PROCESO FINALIZADO CON EXITO", "ENVIO", JOptionPane.INFORMATION_MESSAGE);

                    }


                } catch (SQLException ex) {
                    System.out.println("XYZ:" + ex.getMessage());
                    JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                lblEstadoProceso.setText("Error 14." + ex.getMessage());
                try {
                    objErrores.escribirLogErrores(ex.getMessage() + "INV00005");
                    JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex1) {
                }
            } catch (InterruptedException ex) {
                lblEstadoProceso.setText("Error 15." + ex.getMessage());
                try {
                    objErrores.escribirLogErrores(ex.getMessage() + "INV00006");
                    JOptionPane.showMessageDialog(this.objetoventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex1) {
                }
            }

        }
    }

    private void BorarArchivo() {
        String sistema = "Windows";
        File Ffichero = null;

        if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
            Ffichero = new File("C://ENVIO/ENVIO.ARJ");
        } else {
            Ffichero = new File("/datos/ENVIO.ARJ");
        }
        try {
            if (Ffichero.exists()) {
                Ffichero.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Boolean abrirDBF(String ruta) throws IOException, InterruptedException, SQLException {

        Boolean estado = true;


        try {

            String command;
            command = "unarj e " + ruta + " -y " + objCD.recuperaRutaDefaultLinux().toString();
            //command=”mount -h”;

            final Process process = Runtime.getRuntime().exec(command);
            new Thread() {

                public void run() {

                    try {
                        InputStream is = process.getInputStream();
                        byte[] buffer = new byte[1024];
                        for (int count = 0; (count = is.read(buffer)) >= 0;) {
                            System.out.write(buffer, 0, count);
                        }
                    } catch (Exception e) {
                        // JOptionPane.showMessageDialog(null, "Error 01 :"+e.getMessage(), "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }.start();
            new Thread() {

                public void run() {
                    try {
                        InputStream is = process.getErrorStream();
                        byte[] buffer = new byte[1024];
                        for (int count = 0; (count = is.read(buffer)) >= 0;) {
                            System.err.write(buffer, 0, count);
                        }
                    } catch (Exception e) {
                        // JOptionPane.showMessageDialog(null, "Error 02 :"+e.getMessage(), "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }.start();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Error 03 :"+e.getMessage(), "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }


        return estado;

    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblEstadoProceso;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    // End of variables declaration//GEN-END:variables
}
