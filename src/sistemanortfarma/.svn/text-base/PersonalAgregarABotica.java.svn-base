/*
 * PersonalAgregarABotica.java
 *
 * Created on 05/04/2011, 04:44:10 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaContratos;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRol;
import CapaLogica.LogicaBoticas;
import entidad.Boticas;
import entidad.Contratos;
import entidad.Personal;
import entidad.Roles;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Neowesker.0.0.0.0.0.0.
 * 
 */
public class PersonalAgregarABotica extends javax.swing.JInternalFrame {

    ResultSet rs;
    public DefaultTableModel modeloTabla;
    DefaultListModel modeloDisponibles = new DefaultListModel();
    DefaultListModel modeloBoticas = new DefaultListModel();
    TableColumn columnaTabla = null;
    DefaultListModel modeloPermitidos = new DefaultListModel();
    LogicaPersonal objPersonal = new LogicaPersonal();
    LogicaFechaHora objHoras = new LogicaFechaHora();
    LogicaBoticas objBoticas = new LogicaBoticas();
    List<Personal> listapersonal;
    List<Boticas> listaboticas;
    Integer seleccionadoBotica;
    LogicaRol objrol = new LogicaRol();
    LogicaContratos objcontratos = new LogicaContratos();
    private Personal entidad;
    Administracion objeto = null;

    /** Creates new form PersonalAgregarABotica */
    public PersonalAgregarABotica(Administracion obj) {
        modeloTabla = new DefaultTableModel();
        initComponents();
        objeto = obj;
        objeto = obj;
        cargarRoles();
        cargarTipoContrato();

        try {
            construirCabecera();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        invisible();
        cargarBoticas();

        // contra.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        enbotica = new javax.swing.JList();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        ipBotica = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lasboticas = new javax.swing.JComboBox();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox();
        cbTipoContrato = new javax.swing.JComboBox();
        despues = new com.toedter.calendar.JDateChooser();
        antes = new com.toedter.calendar.JDateChooser();
        codadm = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        enmaestra = new javax.swing.JList();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        documento = new javax.swing.JTextField();
        nombres = new javax.swing.JTextField();
        apellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        actualizador = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        contra = new javax.swing.JLabel();
        lbdesde = new javax.swing.JLabel();
        lbhasta = new javax.swing.JLabel();
        lbrol = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(PersonalAgregarABotica.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setBorder(null);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane1.border.title"))); // NOI18N
        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jScrollPane3.setEnabled(false);
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        enbotica.setFont(resourceMap.getFont("enmaestra.font")); // NOI18N
        enbotica.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        enbotica.setEnabled(false);
        enbotica.setName("enbotica"); // NOI18N
        enbotica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enboticaMouseClicked(evt);
            }
        });
        enbotica.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                enboticaValueChanged(evt);
            }
        });
        enbotica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enboticaKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(enbotica);

        jScrollPane3.setBounds(10, 20, 270, 460);
        jLayeredPane1.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 290, 500));

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane6.border.title"))); // NOI18N
        jLayeredPane6.setName("jLayeredPane6"); // NOI18N

        ipBotica.setName("ipBotica"); // NOI18N
        ipBotica.setBounds(300, 20, 100, 20);
        jLayeredPane6.add(ipBotica, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setBounds(20, 20, 20, 14);
        jLayeredPane6.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(200, 20, 100, 23);
        jLayeredPane6.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lasboticas.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas.setName("lasboticas"); // NOI18N
        lasboticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticasActionPerformed(evt);
            }
        });
        lasboticas.setBounds(40, 20, 160, 20);
        jLayeredPane6.add(lasboticas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 420, 50));

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane3.border.title"))); // NOI18N
        jLayeredPane3.setEnabled(false);
        jLayeredPane3.setName("jLayeredPane3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(20, 30, 130, 14);
        jLayeredPane3.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(20, 50, 90, 14);
        jLayeredPane3.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(20, 70, 90, 14);
        jLayeredPane3.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setBounds(20, 90, 60, 14);
        jLayeredPane3.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cbRol.setModel(new javax.swing.DefaultComboBoxModel(roles));
        cbRol.setDoubleBuffered(true);
        cbRol.setEnabled(false);
        cbRol.setName("cbRol"); // NOI18N
        cbRol.setBounds(160, 110, 160, 20);
        jLayeredPane3.add(cbRol, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cbTipoContrato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoContrato.setDoubleBuffered(true);
        cbTipoContrato.setEnabled(false);
        cbTipoContrato.setName("cbTipoContrato"); // NOI18N
        cbTipoContrato.setBounds(160, 50, 160, 20);
        jLayeredPane3.add(cbTipoContrato, javax.swing.JLayeredPane.DEFAULT_LAYER);

        despues.setEnabled(false);
        despues.setName("despues"); // NOI18N
        despues.setBounds(160, 90, 130, 20);
        jLayeredPane3.add(despues, javax.swing.JLayeredPane.DEFAULT_LAYER);

        antes.setEnabled(false);
        antes.setName("antes"); // NOI18N
        antes.setBounds(160, 70, 130, 20);
        jLayeredPane3.add(antes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        codadm.setBackground(resourceMap.getColor("apellidos.background")); // NOI18N
        codadm.setDoubleBuffered(true);
        codadm.setEnabled(false);
        codadm.setName("codadm"); // NOI18N
        codadm.setBounds(160, 30, 160, 20);
        jLayeredPane3.add(codadm, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setBounds(20, 140, 90, 14);
        jLayeredPane3.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(20, 110, 90, 16);
        jLayeredPane3.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.setBounds(160, 140, 160, 23);
        jLayeredPane3.add(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 420, 180));

        jLayeredPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane7.border.title"))); // NOI18N
        jLayeredPane7.setName("jLayeredPane7"); // NOI18N

        jScrollPane4.setEnabled(false);
        jScrollPane4.setName("jScrollPane4"); // NOI18N

        enmaestra.setFont(resourceMap.getFont("enmaestra.font")); // NOI18N
        enmaestra.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        enmaestra.setEnabled(false);
        enmaestra.setName("enmaestra"); // NOI18N
        enmaestra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enmaestraMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(enmaestra);

        jScrollPane4.setBounds(10, 20, 290, 460);
        jLayeredPane7.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 310, 500));

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane2.border.title"))); // NOI18N
        jLayeredPane2.setEnabled(false);
        jLayeredPane2.setName("jLayeredPane2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(20, 20, 60, 14);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(20, 60, 60, 14);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        documento.setBackground(resourceMap.getColor("apellidos.background")); // NOI18N
        documento.setDoubleBuffered(true);
        documento.setEnabled(false);
        documento.setName("documento"); // NOI18N
        documento.setBounds(90, 60, 130, 20);
        jLayeredPane2.add(documento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        nombres.setBackground(resourceMap.getColor("apellidos.background")); // NOI18N
        nombres.setDoubleBuffered(true);
        nombres.setEnabled(false);
        nombres.setName("nombres"); // NOI18N
        nombres.setBounds(90, 20, 320, 20);
        jLayeredPane2.add(nombres, javax.swing.JLayeredPane.DEFAULT_LAYER);

        apellidos.setBackground(resourceMap.getColor("apellidos.background")); // NOI18N
        apellidos.setDoubleBuffered(true);
        apellidos.setEnabled(false);
        apellidos.setName("apellidos"); // NOI18N
        apellidos.setBounds(90, 40, 320, 20);
        jLayeredPane2.add(apellidos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setBounds(20, 30, 60, -1);
        jLayeredPane2.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setBounds(20, 40, 60, 14);
        jLayeredPane2.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(270, 60, 34, 14);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 420, 90));

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane4.border.title"))); // NOI18N
        jLayeredPane4.setEnabled(false);
        jLayeredPane4.setName("jLayeredPane4"); // NOI18N

        actualizador.setText(resourceMap.getString("actualizador.text")); // NOI18N
        actualizador.setEnabled(false);
        actualizador.setName("actualizador"); // NOI18N
        actualizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizadorActionPerformed(evt);
            }
        });
        actualizador.setBounds(40, 20, 220, 23);
        jLayeredPane4.add(actualizador, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(280, 20, 120, 23);
        jLayeredPane4.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 420, 60));

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jLayeredPane5.border.title"))); // NOI18N
        jLayeredPane5.setEnabled(false);
        jLayeredPane5.setName("jLayeredPane5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

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
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane2.setViewportView(jTable1);

        jScrollPane2.setBounds(10, 20, 400, 90);
        jLayeredPane5.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 420, 120));

        contra.setText(resourceMap.getString("contra.text")); // NOI18N
        contra.setName("contra"); // NOI18N
        getContentPane().add(contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 110, 10));

        lbdesde.setText(resourceMap.getString("lbdesde.text")); // NOI18N
        lbdesde.setName("lbdesde"); // NOI18N
        getContentPane().add(lbdesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, -1));

        lbhasta.setText(resourceMap.getString("lbhasta.text")); // NOI18N
        lbhasta.setName("lbhasta"); // NOI18N
        getContentPane().add(lbhasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, -1, -1));

        lbrol.setText(resourceMap.getString("lbrol.text")); // NOI18N
        lbrol.setName("lbrol"); // NOI18N
        getContentPane().add(lbrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, -1, -1));

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enmaestraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enmaestraMouseClicked

        actualizador.setEnabled(false);
        habilitarEdicion(false);

        for (Integer vac = 0; vac < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(vac);
        }

        if (evt.getClickCount() % 2 == 0) {
            //Lo agrego a la botica
            if (JOptionPane.showConfirmDialog(new JFrame(), "Seguro de Agregarlo a Botica?", "Operacion Cancelada", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {

                    entidad = new Personal();
                    entidad.setNombre(nombres.getText().toString());
                    entidad.setApellido(apellidos.getText().toString());
                    entidad.setContrasena(objPersonal.Recupera_ID_Personal(documento.getText().toString()));
                    entidad.setDNI(documento.getText().toString());
                    entidad.setvId_Modelo_Contrato(cbTipoContrato.getItemAt(cbTipoContrato.getSelectedIndex()).toString());
                    entidad.setvFecha_Inicio(antes.getDate());
                    entidad.setvFecha_Fin(despues.getDate());
                    entidad.setvCodigo_Administrativo(codadm.getText().toString());

                } catch (SQLException ex) {
                    Logger.getLogger(PersonalAgregarABotica.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    boolean resul = false;
                    try {
                        resul = objPersonal.AgregaPersonalFromMasterToBotica(entidad, ipBotica.getText().toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(PersonalAgregarABotica.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (resul) {
                        JOptionPane.showMessageDialog(this, "Personal Agregado a Botica", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al Guardar Personal", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    System.out.println("Error 01" + ex.getMessage());
                }
                try {
                    refrescarBotica();
                } catch (SQLException ex) {
                    System.out.println("Error 02" + ex.getMessage());
                }


            } else {
                JOptionPane.showMessageDialog(this, "Operacion Cancelada", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            }


        } else {

            seleccionadoBotica = this.enmaestra.getSelectedIndex();

            List<Personal> listaDatosPersonal = new ArrayList<Personal>();

            listaDatosPersonal = objPersonal.RecuperaDatosPersona3(modeloPermitidos.get(seleccionadoBotica).toString().substring(0, 8).trim());
            habilitarEdicion(true);
            // System.out.println("listaDatosPersonal.size():"+listaDatosPersonal.size());

            for (Integer con = 0; con < listaDatosPersonal.size(); con++) {
                cbRol.setEnabled(true);
                nombres.setText(listaDatosPersonal.get(con).getNombre());
                apellidos.setText(listaDatosPersonal.get(con).getApellido());
                codadm.setText(listaDatosPersonal.get(con).getDNI());
                codadm.setText(objPersonal.RecuperaCodAdm(listaDatosPersonal.get(con).getDNI()));//Crear Funcion Para Recuperar Codigo Administrativo
                antes.setDate(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()));
                documento.setText(listaDatosPersonal.get(con).getDNI());

                try {
                    despues.setDate(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()));
                } catch (Exception e) {
                    System.out.println("FECHA FIN CONTRATO VACIA");
                }

                lbrol.setText(listaDatosPersonal.get(con).getDescpCargo());
                try {
                    lbdesde.setText(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()).toString());
                } catch (Exception ex) {
                    System.out.println("FECHA FIN CONTRATO VACIA");
                }

                try {
                    lbhasta.setText(objPersonal.FechaFinContrato(listaDatosPersonal.get(con).getDNI()).toString());
                } catch (Exception ex) {
                    System.out.println("Error:Move like Jagger:" + listaDatosPersonal.get(con).getApellido() + "---" + ex.getMessage());
                }
                if (objPersonal.FechaFinContrato(listaDatosPersonal.get(con).getDNI()) != null) {
                    //      lbhasta.setText(listaDatosPersonal.get(con).getvFecha_Fin().toString());
                }

                Integer ini;
                System.out.println("listaDatosPersonal.get(con).getDescpCargo():" + listaDatosPersonal.get(con).getDescpCargo());
                for (ini = 0; ini < cbRol.getItemCount(); ini++) {
                    cbRol.setSelectedIndex(ini);

                    if (cbRol.getItemAt(ini).toString().compareTo(objPersonal.Recupera_ROL_DNI(listaDatosPersonal.get(con).getDNI()).toString()) == 0) {
                        ini = cbRol.getItemCount() + 10;
                    }

                }

                System.out.println("listaDatosPersonal.get(con).getvId_Modelo_Contrato():" + listaDatosPersonal.get(con).getvId_Modelo_Contrato());
                for (ini = 0; ini < cbTipoContrato.getItemCount(); ini++) {

                    cbTipoContrato.setSelectedIndex(ini);

                    if (cbTipoContrato.getItemAt(ini).toString().compareTo(listaDatosPersonal.get(con).getvId_Modelo_Contrato()) == 0) {
                        ini = cbTipoContrato.getItemCount() + 10;
                    }

                }


            }



        }
        try {
            mostrarHistorianl(this.documento.getText());
        } catch (SQLException ex) {
            System.out.println("<En Maestra>" + ex.getMessage());
        }
}//GEN-LAST:event_enmaestraMouseClicked

    private void enboticaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enboticaKeyPressed
        // TODO add your handling code here:

        switch (evt.getKeyCode()) {

            case 127:

                int reply = JOptionPane.showConfirmDialog(null, "Seguro de Inhabilitar a Personal: ", "NORTFARMA", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {


                    try {

                        objPersonal.RetiraFromBotica(this.documento.getText(), this.ipBotica.getText());

                    } catch (SQLException ex) {
                        System.out.println("Error 03" + ex.getMessage());
                    }

                    try {
                        refrescarBotica();
                    } catch (SQLException ex) {
                        System.out.println("Error 04" + ex.getMessage());
                    }

                }

                break;

            case 113:

                break;

        }
}//GEN-LAST:event_enboticaKeyPressed

    private void enboticaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_enboticaValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_enboticaValueChanged

    private void enboticaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enboticaMouseClicked

        for (Integer vac = 0; vac < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(vac);
        }

        habilitarEdicion(true);
        actualizador.setEnabled(true);

        seleccionadoBotica = this.enbotica.getSelectedIndex();
        List<Personal> listaDatosPersonal = new ArrayList<Personal>();

        listaDatosPersonal = objPersonal.RecuperaDatosPersona3(modeloDisponibles.get(seleccionadoBotica).toString().substring(0, 8).trim());
        // System.out.println("listaDatosPersonal.size():"+listaDatosPersonal.size());
        for (Integer con = 0; con < listaDatosPersonal.size(); con++) {
            cbRol.setEnabled(true);
            nombres.setText(listaDatosPersonal.get(con).getNombre());
            apellidos.setText(listaDatosPersonal.get(con).getApellido());
            codadm.setText(listaDatosPersonal.get(con).getDNI());
            codadm.setText(objPersonal.RecuperaCodAdm(listaDatosPersonal.get(con).getDNI()));//Crear Funcion Para Recuperar Codigo Administrativo
            antes.setDate(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()));
            documento.setText(listaDatosPersonal.get(con).getDNI());

            try {
                despues.setDate(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()));
            } catch (Exception e) {
                System.out.println("FECHA FIN CONTRATO VACIA");
            }
            try {
                lbrol.setText(objPersonal.RecuperaRolBotica(listaDatosPersonal.get(con).getDNI(), this.ipBotica.getText()));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                lbdesde.setText(objPersonal.FechaInicioContrato(listaDatosPersonal.get(con).getDNI()).toString());
            } catch (Exception ex) {
                System.out.println("FECHA FIN CONTRATO VACIA");
            }

            try {
                lbhasta.setText(objPersonal.FechaFinContrato(listaDatosPersonal.get(con).getDNI()).toString());
                if (objPersonal.FechaFinContrato(listaDatosPersonal.get(con).getDNI()) != null) {
                    lbhasta.setText(listaDatosPersonal.get(con).getvFecha_Fin().toString());
                }
            } catch (Exception ex) {
                System.out.println("Error:Move like Jagger:" + listaDatosPersonal.get(con).getApellido() + "---" + ex.getMessage());
            }


            Integer ini;
            System.out.println("listaDatosPersonal.get(con).getDescpCargo():" + listaDatosPersonal.get(con).getDescpCargo());
            for (ini = 0; ini < cbRol.getItemCount(); ini++) {
                cbRol.setSelectedIndex(ini);

                // if(cbRol.getItemAt(ini).toString().compareTo(objPersonal.Recupera_ROL_DNI(listaDatosPersonal.get(con).getDNI()).toString())==0){
                if (cbRol.getItemAt(ini).toString().compareTo(lbrol.getText()) == 0) {
                    ini = cbRol.getItemCount() + 10;
                }

            }

            System.out.println("listaDatosPersonal.get(con).getvId_Modelo_Contrato():" + listaDatosPersonal.get(con).getvId_Modelo_Contrato());
            for (ini = 0; ini < cbTipoContrato.getItemCount(); ini++) {

                cbTipoContrato.setSelectedIndex(ini);

                if (cbTipoContrato.getItemAt(ini).toString().compareTo(listaDatosPersonal.get(con).getvId_Modelo_Contrato()) == 0) {
                    ini = cbTipoContrato.getItemCount() + 10;
                }

            }


        }

        try {
            mostrarHistorianl(documento.getText().toString());
        } catch (SQLException ex) {
            System.out.println("Error 05:" + ex.getMessage());
        }

    }//GEN-LAST:event_enboticaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(this, "MODULO EN CONSTRUCCION", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_jButton3ActionPerformed

    private void lasboticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticasActionPerformed

        Object w = lasboticas.getSelectedItem();
        this.ipBotica.setText(objBoticas.DevuelveIP((String) w));

        if (objBoticas.DevuelveIP((String) w).length() > 1) {
            jButton1.setEnabled(true);
        } else {
            jButton1.setEnabled(false);
        }

}//GEN-LAST:event_lasboticasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {

            if (ipBotica.getText().length() > 0) {
                refrescarBotica();
                jButton1.setEnabled(false);
                lasboticas.setEnabled(false);
                jButton2.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "XNo se pudo realizar Conexión", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "YNo se pudo realizar Conexión", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(true);
        lasboticas.setEnabled(true);
        jButton2.setEnabled(false);
        codadm.setText("");
        cbTipoContrato.setSelectedIndex(0);
        cbRol.setSelectedIndex(0);

        modeloDisponibles.removeAllElements();
        modeloPermitidos.removeAllElements();
}//GEN-LAST:event_jButton2ActionPerformed

    private void actualizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizadorActionPerformed
        System.out.println(lbrol.getText() + "-" + cbRol.getItemAt(this.cbRol.getSelectedIndex()).toString());
        if (lbrol.getText().compareTo(this.cbRol.getItemAt(this.cbRol.getSelectedIndex()).toString()) != 0) {
            System.out.println("H3");
            try {
                // Aqui es cuando se cambia el rol

                actualizaEnBotica(this.documento.getText(), this.cbRol.getItemAt(this.cbRol.getSelectedIndex()).toString());
                JOptionPane.showMessageDialog(this, "ACTUALIZADO", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                System.out.println("Error 06" + ex.getMessage());
            }
        }

        if ((lbdesde.getText().compareTo(objHoras.ConvierteFecha(antes.getDate())) != 0) && (lbdesde.getText().compareTo(objHoras.ConvierteFecha(despues.getDate())) != 0)) {
            System.out.println("HI4");
            try {

                actualizaEnMaster(this.documento.getText(), this.codadm.getText(), this.cbTipoContrato.getItemAt(this.cbTipoContrato.getSelectedIndex()).toString(), this.antes.getDate(), this.despues.getDate(), this.cbRol.getItemAt(this.cbTipoContrato.getSelectedIndex()).toString());
                JOptionPane.showMessageDialog(this, "ACTUALIZADO", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                System.out.println("Maestra" + ex.getMessage());
            }
        }

    }//GEN-LAST:event_actualizadorActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        objeto.marcacdo = false;
        this.dispose();
}//GEN-LAST:event_jButton4ActionPerformed
    private void invisible() {
        contra.setVisible(false);
        lbdesde.setVisible(false);
        lbhasta.setVisible(false);
        lbrol.setVisible(false);
        jLabel2.setVisible(false);
    }

    private void cargarBoticas() {

        listaboticas = objBoticas.ListarobjBoticas();

        lasboticas.addItem("Seleccione Opcion");

        for (int i = 0; i < listaboticas.size(); i++) {
            lasboticas.addItem(listaboticas.get(i).getDescripcion());
        }

    }

    private void refrescarBotica() throws SQLException {

        List<Personal> listapersonalMaestra = new ArrayList<Personal>();
        List<Personal> listapersonalMaestra2 = new ArrayList<Personal>();

//        try {
//            objBoticas.GuardandoIP();
//        } catch (UnknownHostException ex) {
//            System.out.println("Refrescar Botica:001:"+ex.getMessage());
//        }


        listapersonalMaestra = objPersonal.ListaPersonalBotica(ipBotica.getText().toString());

        modeloDisponibles.removeAllElements();
        Integer contador = 1;
        String cadena_verificador = "";

        for (int i = 0; i < listapersonalMaestra.size(); i++) {

            modeloDisponibles.addElement(listapersonalMaestra.get(i).getContrasena() + " - " + listapersonalMaestra.get(i).getDNI());

            cadena_verificador = cadena_verificador + listapersonalMaestra.get(i).getContrasena() + "_";

            contador++;
        }

        modeloPermitidos.removeAllElements();

        enmaestra.setModel(modeloPermitidos);

        listapersonalMaestra2 = objPersonal.ListarPersonalMasterNoBotica(cadena_verificador, contador);
        // System.out.println("listapersonalMaestra2:"+listapersonalMaestra2.size());

        for (int i = 0; i < listapersonalMaestra2.size(); i++) {
            modeloPermitidos.addElement(listapersonalMaestra2.get(i).getDNI() + " - " + listapersonalMaestra2.get(i).getContrasena() + ", " + listapersonalMaestra2.get(i).getId_botica());
        }

        enmaestra.setModel(modeloPermitidos);
        enmaestra.setEnabled(true);

        enbotica.setModel(modeloDisponibles);
        enbotica.setEnabled(true);

    }

    private void mostrarHistorianl(String valorDNI) throws SQLException {
        List<Personal> Historial = new ArrayList<Personal>();

        jTable1.setEnabled(true);

        jTable1.setModel(modeloTabla);

        Historial = objPersonal.MostrarHistorial(valorDNI);

        for (Integer inic = 0; inic < Historial.size(); inic++) {

            String fcf = "";

            if (Historial.get(inic).getvFecha_Fin() == null) {
                fcf = " ";
            } else {
                fcf = Historial.get(inic).getvFecha_Fin().toString();
            }

            Object[][] data = {
                {
                    Historial.get(inic).getvId_Modelo_Contrato(),
                    Historial.get(inic).getvCodigo_Administrativo(),
                    Historial.get(inic).getvFecha_Inicio(),
                    fcf
                }};

            modeloTabla.addRow(data[0]);

        }

        //jTable1.setModel(modeloTabla);


    }

    private void construirCabecera() throws SQLException {
//        try {
//            objBoticas.GuardandoIP();
//        } catch (UnknownHostException ex) {
//            System.out.println(ex.getMessage());
//        }

        modeloTabla.addColumn("TIPO CONTRATO");
        modeloTabla.addColumn("COD. ADM.");
        modeloTabla.addColumn("INICIO");
        modeloTabla.addColumn("FIN");

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(150);
        columnaTabla.setMinWidth(150);
        columnaTabla.setMaxWidth(150);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(120);
        columnaTabla.setMinWidth(120);
        columnaTabla.setMaxWidth(120);

    }

    private void cargarRoles() {

        List<Roles> listaroles = objrol.ListaRoles();

        cbRol.removeAllItems();
        cbRol.addItem("Seleccione Opcion");

        for (int i = 0; i < listaroles.size(); i++) {
            cbRol.addItem(listaroles.get(i).getDescripcion());
        }

    }

    private void cargarTipoContrato() {

        List<Contratos> listacontratos = objcontratos.ListarContratos();

        cbTipoContrato.removeAllItems();
        cbTipoContrato.addItem("Seleccione Opcion");

        for (int i = 0; i < listacontratos.size(); i++) {
            cbTipoContrato.addItem(listacontratos.get(i).getDescripcion());
        }

    }

    private void actualizaEnBotica(String DN, String RolA) throws SQLException {

        objPersonal.ActualizaRol(DN, RolA, this.ipBotica.getText());
    }

    private void actualizaEnMaster(String DN, String Codigo, String Contrato, Date DesdeA, Date FinA, String RolA) throws SQLException {
        entidad = new Personal();
        entidad.setDescripcionRoles(DN);
        entidad.setDNI(Codigo);
        entidad.setvId_Modelo_Contrato(Contrato);
        entidad.setvFecha_Inicio(DesdeA);
        entidad.setvFecha_Fin(FinA);
        entidad.setvCodigo_Administrativo(RolA);
        objPersonal.ActualizaDataMaster(entidad);
    }

    private void habilitarEdicion(Boolean Valor) {
        codadm.setEnabled(Valor);
        antes.setEnabled(Valor);
        despues.setEnabled(Valor);
        cbRol.setEnabled(Valor);
        cbTipoContrato.setEnabled(Valor);



    }
    private String[] roles = {};
    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizador;
    private com.toedter.calendar.JDateChooser antes;
    private javax.swing.JTextField apellidos;
    private javax.swing.JComboBox cbRol;
    private javax.swing.JComboBox cbTipoContrato;
    private javax.swing.JTextField codadm;
    private javax.swing.JLabel contra;
    private com.toedter.calendar.JDateChooser despues;
    private javax.swing.JTextField documento;
    private javax.swing.JList enbotica;
    private javax.swing.JList enmaestra;
    private javax.swing.JTextField ipBotica;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox lasboticas;
    private javax.swing.JLabel lbdesde;
    private javax.swing.JLabel lbhasta;
    private javax.swing.JLabel lbrol;
    private javax.swing.JTextField nombres;
    // End of variables declaration//GEN-END:variables
}
