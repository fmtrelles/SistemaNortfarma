/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FomCuadreGastos.java
 *
 * Created on 13-ago-2012, 12:35:42
 */
package sistemanortfarma;

import CapaLogica.LogicaCajas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import entidad.Cajas;
import entidad.Personal;
import entidad.Turno;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import entidad.Boticas;
import CapaLogica.LogicaBoticas;
import javax.swing.JComboBox;

/**
 *
 * @author Miguel Gomez S.
 */
public class FomCuadreGastos extends javax.swing.JInternalFrame {

    AplicacionVentas objeto;
    private String idbotica;
    private String usuario;
    private int idpersonal;
    private String seleccionado;
    Date fechaInicio = null;
    String origenMov = "";
    String destinoMov = "";
    LogicaFechaHora logfecja = new LogicaFechaHora();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    LogicaPersonal logperso = new LogicaPersonal();
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    LogicaCajas logcajas = new LogicaCajas();
    List<Cajas> doc_cajas = new ArrayList<Cajas>();
    String Fecha;
    String IdPersonal;
    String idperMov = "";
    int idcajero = 0;
    List<Boticas> listaboticas2 = new ArrayList<Boticas>();
    LogicaBoticas objBoticas = new LogicaBoticas();
    private int idturno;
    FormClientesGastos objcliente;
    LogicaPersonal objPersonal = new LogicaPersonal();
    private int idcaja;
    Object[] datosgastos = new Object[6];
    private DefaultTableModel modelo_gastos;
    List<Boticas> listaboticas = new ArrayList<Boticas>();
    List<Cajas> doc_motivos = new ArrayList<Cajas>();
    List<Cajas> docSub_motivos = new ArrayList<Cajas>();
    TableColumnModel col_gastos;
    private String SerieNC, NumeroNC, IdVentaNC;
    List<Cajas> Lista = new ArrayList<Cajas>();
    JFrame objetoventana;
    FormPersonal formpersonal;

    /** Creates new form FomCuadreGastos */
    public FomCuadreGastos(AplicacionVentas obj) {
        initComponents();
        objeto = obj;
        idbotica = objeto.getIdbotica();
        usuario = objeto.getUsuario();
        idpersonal = objeto.getId_personal_botica();
        fechaInicio = logfecja.RetornaFecha();
        jLabel12.setText(idbotica);
        Ocultar(false);
        Deshabilita_Retiro_Gasto(false);
        Deshabilita_Gatos_Ingresados(false);
        jTabbedPane1.setSelectedIndex(0);
        CargarCajeros();
        Lista_Turnos(); 
        Cargar_Motivos();
        Cargar_SubMotivos();
        modelo_gastos = (DefaultTableModel) jTable1.getModel();
        col_gastos = this.jTable1.getColumnModel();
        this.jTextField8.setVisible(false);

        this.txtOrigen.setEnabled(false);
        this.txtDestino.setEnabled(false);
        this.txtIdPersonal.setVisible(false);
        this.txtPersonal.setEnabled(false);
        this.jButton12.setEnabled(false);
        this.txtOrigen.setText("");
        this.txtDestino.setText("");
        this.txtIdPersonal.setText("");
        this.txtPersonal.setText("");
        //this.jComboBox10.setEnabled(false);
    }

    private void CargarCajeros() {
        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox5.addItem(listacajas.get(i).getCajas());
        }

        int rol = new login().getId_rol();

        if (rol == 12 || rol == 16 || rol == 20) {
            Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

            for (int i = 0; i < Lista_Cajeros.size(); i++) {
                jComboBox6.addItem(Lista_Cajeros.get(i).getApellido());
            }
        } else {
            jComboBox6.addItem(usuario);
            idcajero = objeto.getIdpesonal();
        }

    }
    
    

    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();
        jComboBox7.addItem("---- Seleccione Turno ----");
        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox7.addItem(Lista_Turnos.get(i).getDescripcion());
        }
    }
    private void Cargar_Motivos() {

        jComboBox9.removeAllItems();
        doc_motivos = logcajas.Lista_Documen_Motivo();

        for (int i = 0; i < doc_motivos.size(); i++) {
            String doc = doc_motivos.get(i).getDescripMotivo();
            this.jComboBox9.addItem(doc);
        }

    }
    
    private void Cargar_SubMotivos(){
        jComboBox10.removeAllItems();
        docSub_motivos = logcajas.Lista_Documen_SubMotivo();

        for (int w = 0; w < docSub_motivos.size(); w++) {
            String doc = docSub_motivos.get(w).getDescripSubMotivo();
            this.jComboBox10.addItem(doc);
        }

    }
    private void Ocultar(boolean valor) {
        jLabel8.setVisible(valor);
        jLabel13.setVisible(valor);
        jLabel15.setVisible(valor);
        jLabel9.setVisible(valor);
        jLabel14.setVisible(valor);
        jLabel7.setVisible(valor);
        jLabel10.setVisible(valor);
        jPanel2.setVisible(valor);
    }

    private void Consultar_Gastos() {
        if (Verifica_Datos()) {
            idcaja = listacajas.get(jComboBox5.getSelectedIndex() - 1).getIdcaja();
            VerificaInformacion();
        }
    }

    private void VerificaInformacion() {

        idcaja = listacajas.get(this.jComboBox5.getSelectedIndex() - 1).getIdcaja();
        idturno = Lista_Turnos.get(jComboBox7.getSelectedIndex() - 1).getId_Turno();

        if (Lista_Cajeros.size() > 0) {
            idcajero = Lista_Cajeros.get(jComboBox6.getSelectedIndex() - 1).getId_Personal();
        }
        Fecha = logfecja.ConvierteFecha(this.jXDatePicker1.getDate());

        try {

            Lista.removeAll(Lista);
            Cajas micaja = new Cajas();
            micaja.setIdbotica(idbotica);
            micaja.setIdcajero(idcajero);
            micaja.setIdcaja(idcaja);
            micaja.setFecha(Fecha);
            micaja.setTurno(idturno);
            Lista = logcajas.Lista_Gastos_Caja(micaja);
            this.jLabel14.setText(Fecha);
            this.jLabel15.setText(this.jComboBox6.getSelectedItem().toString());
            this.jLabel10.setText(this.usuario);
            Ocultar(true);
            Deshabilita_Gatos_Ingresados(true);

            if (Lista.size() > 0) {
                for (int i = 0; i < Lista.size(); i++) {
                    datosgastos[0] = Lista.get(i).getDescripGasto();
                    datosgastos[1] = Lista.get(i).getDocugasto();
                    datosgastos[2] = Lista.get(i).getMontoGasto();
                    datosgastos[3] = Lista.get(i).getDescripRetencion();// .getDescr_Retencion();
                    datosgastos[4] = Lista.get(i).getNombre_RazonSocial();
                    datosgastos[5] = Lista.get(i).getdesgasto();
                    modelo_gastos.addRow(datosgastos);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean Verifica_Datos() {

        if (jComboBox5.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox6.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione Cajero ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox7.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }

        return false;

    }

    private void Deshabilita_Retiro_Gasto(boolean valor) {
        this.jTextField1.requestFocus();
        jTabbedPane1.setEnabledAt(2, valor);
        jTabbedPane1.setSelectedIndex(2);
        this.jTextField6.setText("");
        this.jTextField7.setText("");
        this.jTextField8.setText("");
    }

    private void Deshabilita_Gatos_Ingresados(boolean valor) {
        jTabbedPane1.setEnabledAt(1, valor);
        jTabbedPane1.setSelectedIndex(1);
    }

    private void Registrar_Gastos() {

        if (jTextField1.getText().trim().compareTo("") != 0 && jTextField2.getText().trim().compareTo("") != 0 && jTextField8.getText().trim().compareTo("") != 0) {
            int p = JOptionPane.showConfirmDialog(null, " DESEA GUARDAR ESTE GASTO ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (p == JOptionPane.YES_OPTION) {
                Guardar_Gasto();
                jTabbedPane1.setEnabledAt(2, false);
                jTabbedPane1.setEnabledAt(1, true);
                Deshabilita_Retiro_Gasto(false);
                Deshabilita_Gatos_Ingresados(true);
                LimpiatTabla_Gastos();
                VerificaInformacion();

            }
        }
    }

    private void Guardar_Gasto() {
        String doc, concepto,clientegasto,SubMotivo;
        int idgasto,idmotivo;
        List<Cajas> ListaGastos = new ArrayList<Cajas>();
        double MontoPago;

        try {

            idgasto = doc_cajas.get(jComboBox8.getSelectedIndex()).getIdgasto();
            idmotivo = doc_motivos.get(jComboBox9.getSelectedIndex()).getIdmotivo();
            if (idmotivo != 1 ){               
                SubMotivo = "";
            }else{
            SubMotivo = docSub_motivos.get(jComboBox10.getSelectedIndex()).getDescripSubMotivo();
            }
            MontoPago = Double.parseDouble(this.jTextField2.getText().trim());
            doc = this.jTextField1.getText().trim();
            concepto = this.jTextArea1.getText().trim().toUpperCase();
            clientegasto = this.jTextField8.getText().trim();

            if (idmotivo != 1 ){               
                SubMotivo = "";
            }
            
            if (idmotivo == 1 ){
                origenMov = this.txtOrigen.getText().trim().toUpperCase();
                destinoMov = this.txtDestino.getText().trim().toUpperCase();
                idperMov = this.txtIdPersonal.getText().trim().toUpperCase();
            }

            BigDecimal bd = new BigDecimal(MontoPago);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            MontoPago = bd.doubleValue();

            Cajas caja = new Cajas(idbotica, idcaja, idpersonal, idgasto, doc, MontoPago, concepto, idcajero, "", idturno, SerieNC, NumeroNC, IdVentaNC,clientegasto,idmotivo,origenMov,destinoMov,idperMov,SubMotivo);
            caja.setFecha(Fecha);
            ListaGastos.add(caja);
            boolean valor = logcajas.Ingreso_Gastos_Caja(ListaGastos);

            if (valor) {

                jButton4.setEnabled(false);
                JOptionPane.showMessageDialog(this, " GASTO REGISTRADO CORRECTAMENTE ", "GASTO REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
                MontoPago = 0;
                ListaGastos.removeAll(ListaGastos);
                jTextField1.setText("");
                jTextField2.setText("");
                jTextArea1.setText("");

            } else {
                JOptionPane.showMessageDialog(this, " NO SE PUDO REGISTRAR SU GASTO ", "Error", JOptionPane.ERROR_MESSAGE);
                MontoPago = 0;
                ListaGastos.removeAll(ListaGastos);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void Cargar_Documentos() {
        doc_cajas = logcajas.Lista_Documen_Gastos();

        for (int i = 0; i < doc_cajas.size(); i++) {
            String doc = doc_cajas.get(i).getDescripGasto() + "   |      " + doc_cajas.get(i).getCaracter();
            this.jComboBox8.addItem(doc);
        }

    }

    private void Deshabilita(boolean valor) {
        jComboBox5.setEnabled(valor);
        jComboBox6.setEnabled(valor);
        jComboBox7.setEnabled(valor);
        jXDatePicker1.setEnabled(valor);
        jButton1.setEnabled(valor);
    }

    private void LimpiatTabla_Gastos() {
        int cant = this.jTable1.getRowCount();

        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo_gastos.removeRow(i);
            }
        }
    }

    private String[] ArrayBoticas = {};
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox();
        jTextField8 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        txtPersonal = new javax.swing.JTextField();
        txtIdPersonal = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FomCuadreGastos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        panelNice1.setBackground(resourceMap.getColor("panelNice1.background")); // NOI18N
        panelNice1.setName("panelNice1"); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel3.border.titleFont"), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel16.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel36.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N

        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------  Seleccione su Caja  ---------" }));
        jComboBox5.setName("jComboBox5"); // NOI18N

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------ Seleccione Cajero ----" }));
        jComboBox6.setName("jComboBox6"); // NOI18N

        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton9)))
        );

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DOCUMENTO", "NUMERO", "MONTO", "CONCEPTO", "CLIENTE_GASTO", "MOTIVO_GASTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        }

        jScrollPane2.setViewportView(jScrollPane1);

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(107, 43));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(107, 43));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setEnabled(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        jComboBox8.setName("jComboBox8"); // NOI18N
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel19.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane3.setViewportView(jTextArea1);

        jLabel22.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N

        jTextField7.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField7.setEnabled(false);
        jTextField7.setName("jTextField7"); // NOI18N

        jButton10.setFont(resourceMap.getFont("jButton11.font")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(resourceMap.getFont("jButton11.font")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jComboBox9.setName("jComboBox9"); // NOI18N
        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setFont(resourceMap.getFont("jTextField8.font")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setName("jTextField8"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        txtOrigen.setFont(resourceMap.getFont("txtDestino.font")); // NOI18N
        txtOrigen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtOrigen.setName("txtOrigen"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        txtDestino.setFont(resourceMap.getFont("txtDestino.font")); // NOI18N
        txtDestino.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDestino.setName("txtDestino"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        txtPersonal.setEditable(false);
        txtPersonal.setFont(resourceMap.getFont("txtPersonal.font")); // NOI18N
        txtPersonal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonal.setName("txtPersonal"); // NOI18N

        txtIdPersonal.setEditable(false);
        txtIdPersonal.setFont(resourceMap.getFont("txtIdPersonal.font")); // NOI18N
        txtIdPersonal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdPersonal.setName("txtIdPersonal"); // NOI18N

        jComboBox10.setName("jComboBox10"); // NOI18N
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25)
                                .addComponent(jLabel22)
                                .addComponent(jLabel28)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel26))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDestino)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton11))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(txtIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel11.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel11.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel12.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel8.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel8.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel13.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel13.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel15.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel15.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel9.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel14.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel14.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel7.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel7.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel10.setBackground(resourceMap.getColor("jLabel7.background")); // NOI18N
        jLabel10.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(85, 85, 85)))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
        this.jButton1.setEnabled(true);
    }//GEN-LAST:event_jComboBox7ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Consultar_Gastos();
        Deshabilita(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " DESEAS SALIR ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            objeto.marcacdo = false;
            this.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {
            this.jButton4.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            if (Lista.get(this.jTable1.getSelectedRow()).getIdgasto() != 7) {
                String numDoc = String.valueOf(this.jTable1.getValueAt(fila, 1));
                int p = JOptionPane.showConfirmDialog(null, " Desea Eliminar este Gasto : " + numDoc + "", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (p == JOptionPane.YES_OPTION) {

                    Cajas miobj = new Cajas();
                    miobj.setIdbotica(idbotica);
                    miobj.setIdcajero(idcajero);
                    miobj.setIdcaja(idcaja);
                    miobj.setFecha(Fecha);
                    miobj.setTurno(idturno);
                    miobj.setDocugasto(numDoc);

                    if (logcajas.Elimina_Gasto(miobj)) {
                        JOptionPane.showMessageDialog(this, " SU GASTO HA SIDO ELIMINADO  ", "ELIMNADO", JOptionPane.INFORMATION_MESSAGE);
                        LimpiatTabla_Gastos();
                        VerificaInformacion();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS NO PUEDES ELIMINAR ESTE GASTO  ", "ELIMNADO", JOptionPane.ERROR_MESSAGE);
            }



        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTabbedPane1.setEnabledAt(1, false);
        Deshabilita_Retiro_Gasto(true);
        Cargar_Documentos();
        //Cargar_Motivos();
        //Cargar_SubMotivos();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTabbedPane1.setEnabledAt(1, true);
        Deshabilita_Retiro_Gasto(false);
        Deshabilita_Gatos_Ingresados(true);
        //jComboBox9.removeAllItems();
        //jComboBox10.removeAllItems();
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int motivo = doc_motivos.get(jComboBox9.getSelectedIndex()).getIdmotivo();
        String IdSubmotivo = docSub_motivos.get(jComboBox10.getSelectedIndex()).getIdSubmotivo();
        String Submotivo = docSub_motivos.get(jComboBox10.getSelectedIndex()).getDescripSubMotivo();
        String origen = this.txtOrigen.getText().trim().toUpperCase();
        String destino = this.txtDestino.getText().trim().toUpperCase();
        String idpersona = this.txtIdPersonal.getText();
        
        if (motivo == 1 && (IdSubmotivo.equals("0"))){
                JOptionPane.showMessageDialog(this, " ESCOGER SUBMOTIVO DE LA MOVILIDAD ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                this.jComboBox6.requestFocus();
        }else if (motivo == 1 && (origen.equals("") || destino.equals(""))){
                JOptionPane.showMessageDialog(this, " ESCOGER ORIGEN Y DESTINO DE LA MOVILIDAD ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }else{
            Registrar_Gastos();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Deshabilita(true);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jTextField1.setText("");
        jTextField2.setText("");
        jTextArea1.setText("");
        LimpiatTabla_Gastos();
        Deshabilita_Retiro_Gasto(false);
        Deshabilita_Gatos_Ingresados(false);
        jPanel2.setVisible(false);
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " DESEAS SALIR ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            objeto.marcacdo = false;
            this.dispose();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        muestra_Personal();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
        int idmot = doc_motivos.get(jComboBox9.getSelectedIndex()).getIdmotivo();

        if (idmot == 1){
            this.txtOrigen.setText("");
            this.txtDestino.setText("");
            this.txtIdPersonal.setText("");
            this.txtPersonal.setText("");
            this.txtOrigen.setEnabled(true);
            this.txtDestino.setEnabled(true);
            this.txtIdPersonal.setEnabled(true);
            this.txtPersonal.setEnabled(true);
            this.jButton12.setEnabled(true);
            this.jComboBox10.setEnabled(true);            
            this.jTextArea1.setEditable(false);
        }else
        {
            this.txtOrigen.setText("");
            this.txtDestino.setText("");
            this.txtIdPersonal.setText("");
            this.txtPersonal.setText("");
            this.txtOrigen.setEnabled(false);
            this.txtDestino.setEnabled(false);
            this.txtIdPersonal.setEnabled(false);
            this.txtPersonal.setEnabled(false);
            this.jButton12.setEnabled(false);
            this.jComboBox10.setEnabled(false);
            this.jTextArea1.setEditable(true);
            this.jTextArea1.setText("");
        }
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        new FormClientesGastos(this, objetoventana, idbotica).show(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        NuevoUsuario();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ListaNotasCreditos notadcr = new ListaNotasCreditos(new JFrame(), idbotica);
        notadcr.pack();
        notadcr.setVisible(true);

        try {
            String document = notadcr.getNumero_Documento();
            if (document != null) {
                this.jTextField1.setText(notadcr.getNumero_Documento());
                this.jTextField2.setText(String.valueOf(notadcr.getTotal()));
                IdVentaNC = notadcr.getIdVenta();
                SerieNC = notadcr.getSerie();
                NumeroNC = notadcr.getNumero();
                this.jTextArea1.requestFocus();
                jButton6.setEnabled(true);
            } else {
                jButton6.setEnabled(false);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        jButton6.setEnabled(true);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

    }//GEN-LAST:event_jTextField2FocusLost

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        if (evt.getStateChange() == 2) {
            if (doc_cajas.get(jComboBox8.getSelectedIndex()).getIdgasto() == 7) {
                jButton1.setEnabled(true);
                jTextField1.setEditable(false);
                jTextField2.setEditable(false);
                jTextField1.setBackground(Color.YELLOW);
                jTextField2.setBackground(Color.YELLOW);
            } else {
                jButton1.setEnabled(false);
                jTextField1.setEditable(true);
                jTextField2.setEditable(true);
                jTextField1.setBackground(Color.WHITE);
                jTextField2.setBackground(Color.WHITE);
                SerieNC = "";
                NumeroNC = "";
                IdVentaNC = "";
            }
        }
    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
        String TXT_Mot = doc_motivos.get(jComboBox9.getSelectedIndex()).getDescripMotivo();
        String TXT_SubMot = docSub_motivos.get(jComboBox10.getSelectedIndex()).getDescripSubMotivo();
        jTextArea1.setText(TXT_Mot+ " - " +TXT_SubMot);
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void NuevoUsuario() {
        NuevoClienteGastos pe = new NuevoClienteGastos(this, objetoventana,objeto.getIdbotica());
        pe.pack();
        pe.setVisible(true);
    }
    public void AsignaDatosCliente() {
        //if (objcliente.getRUC_DNI() == null) {

        jTextField6.setText(objcliente.getRUC_DNI());
        jTextField7.setText(objcliente.getNombre_RazonSocial());
        jTextField8.setText(objcliente.getCodigoCliente());
        jTextField1.requestFocus();
    }
    public void MuestraClientes(NuevoClienteGastos obj, int id_cliente) {

        jTextField6.setText(obj.getObjcliente().getRUC_DNI());
        jTextField7.setText(obj.getObjcliente().getNombre_RazonSocial());
        jTextField8.setText(String.valueOf(id_cliente));
        jTextField1.requestFocus();
    }
    private void muestra_Personal() {
        formpersonal = new FormPersonal(objetoventana);
        formpersonal.pack();
        formpersonal.setVisible(true);

        IdPersonal = FormPersonal.getIdpersonal();
        String nombre = FormPersonal.getNombre_Personal();

        if (IdPersonal != null) {
            this.txtIdPersonal.setText(IdPersonal);
            this.txtPersonal.setText(nombre);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtIdPersonal;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtPersonal;
    // End of variables declaration//GEN-END:variables
}
