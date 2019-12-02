package sistemanortfarma;

import CapaDatos.CajasFondos;
import CapaLogica.LogicaCajas;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import CapaLogica.LogicaVenta;
import entidad.Cajas;
import entidad.Personal;
import entidad.TipoCambio;
import entidad.Turno;
import entidad.Venta;
import entidad.BuscaCaja;
import CapaDatos.DatosBuscaCaja;
import CapaDatos.DatosBuscaTurno;
import entidad.BuscaTurno;
import entidad.Cajas_Fondos;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Miguel Gomez S.
 */
public class ConsultaCaja extends javax.swing.JInternalFrame {

    List prueba = new ArrayList();
    LogicaVenta objVentas = new LogicaVenta();
    List cabeceras = new ArrayList();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    AplicacionVentas obj;
    Date Fecha;
    private int idpersonal;
    private String idbotica;
    private int idcaja;
    MuestraVentana objetoventana = new MuestraVentana();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    LogicaPersonal logperso = new LogicaPersonal();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    private String usuario;
    int pasad = 0;
    int codigo;
    Object[] item = new Object[4];
    Object[] item_pagos = new Object[3];
    private DefaultTableModel tablaproforma;
    private DefaultTableModel tablapagos;
    int idperso;
    Venta pven;
    LogicaCajas logcajas = new LogicaCajas();
    double depositado = 0;
    double deposito_dolares = 0;
    List<TipoCambio> misTiposCambios;
    private int turno;
    List<Turno> Lista_Turnos = new ArrayList<Turno>();
    int transferir = 0;
    List<BuscaCaja> lista = new ArrayList<BuscaCaja>();
    List<BuscaCaja> lista2 = new ArrayList<BuscaCaja>();
    List<BuscaTurno> listaturno = new ArrayList<BuscaTurno>();
    List<BuscaTurno> listaturno2 = new ArrayList<BuscaTurno>();

    /**
     * Creates new form ConsultaCaja1
     */
    public ConsultaCaja(AplicacionVentas objeto) {
        initComponents();

        obj = objeto;
        this.jPanel4.setVisible(false);
        this.jPanel5.setVisible(false);
        AparienciaTabla();
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        Fecha = objFechaHora.RetornaFecha();
        idpersonal = obj.getId_personal_botica();
        CargarCajeros();
        this.jTextField1.setText(Fecha.toString());
        this.jTextField2.setText(objFechaHora.RetornaHora().toString());
        this.jTextField3.setText(usuario);
        this.txtusuario.setText(usuario);
        tablaproforma = (DefaultTableModel) jTable2.getModel();
        tablapagos = (DefaultTableModel) jTable1.getModel();
        Lista_Turnos();
        jLabel12.setVisible(false);
        txtmonto.setText("0.00");
        txtusuario.setEditable(false);
        buscacaja();
        buscaturno();
        verificaturnoycaja();
        this.txtmonto.setEnabled(false);
        this.combocajas.setEnabled(false);
        this.comboturnos.setEnabled(false);
        this.btntransferir.setEnabled(false);
        verificaEsBCP();
    }

    private void verificaEsBCP(){
        Cajas miBCP = new Cajas();
        miBCP.setIdbotica(idbotica);        
        
        int esbcp = logcajas.VerificaEsBCP(miBCP);
        
        if (esbcp == 1){
            this.txtmonto.setEnabled(true);
            this.combocajas.setEnabled(true);
            this.comboturnos.setEnabled(true);
            this.btntransferir.setEnabled(true);
        }
        
    }
    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox4.addItem("---- Seleccione Turno ----");
        comboturnos.addItem("---- Seleccione Turno ----");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox4.addItem(Lista_Turnos.get(i).getDescripcion());
            //comboturnos.addItem(Lista_Turnos.get(i).getDescripcion());
        }

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable2.getColumnModel());
        cabecera.setReorderingAllowed(false);

        JTableHeader cabecera1 = new JTableHeader(this.jTable1.getColumnModel());
        cabecera1.setReorderingAllowed(false);
        jTable1.setTableHeader(cabecera1);

        DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
        tcr3.setHorizontalAlignment(SwingConstants.CENTER);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(tcr3);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(tcr);

        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr1);

        DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();
        tcr4.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr4);

    }

    private void CargarCajeros() {
        try {

            // listacajas = objcajas.RecuperaCajas(idbotica);
            listacajas = logcajas.ListarCajas_Aperturadas();

            for (int i = 0; i < listacajas.size(); i++) {
                jComboBox1.addItem(listacajas.get(i).getCajas());
                //combocajas.addItem(listacajas.get(i).getCajas());
            }

            int rol = new login().getId_rol();

            if (rol == 12 || rol == 16) {
                Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

                for (int i = 0; i < Lista_Cajeros.size(); i++) {
                    jComboBox2.addItem(Lista_Cajeros.get(i).getApellido());
                }
            } else {
                jComboBox2.addItem(usuario);
                codigo = obj.getIdpesonal();
            }

        } catch (Exception ex) {
            System.out.println("CargarCajeros :" + ex.getMessage());
        }

    }

    private void MuestraDetalle_Resumen() {

        if (Lista_Cajeros.size() > 0) {
            idperso = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
        } else {
            idperso = codigo;
        }

        pven = new Venta(String.valueOf(Fecha), String.valueOf(Fecha), idbotica, listacajas.get(this.jComboBox1.getSelectedIndex()).getIdcaja(), idperso, turno);
        List<Venta> LISTA = objVentas.REPORTE_CAJA_SUBDETALLE(pven);
        double total = 0;

        if (LISTA.get(0).getCantidadpagos() == 0) {
            jLabel12.setVisible(true);
            jLabel12.setText("TURNO SIN VENTAS");
            jButton1.setEnabled(true);
        }

        if (LISTA.size() > 0) {
            for (int i = 0; i < LISTA.size(); i++) {
                item[0] = LISTA.get(i).getDescripcionTipoPago();
                item[1] = LISTA.get(i).getCantidadpagos();
                item[2] = LISTA.get(i).getTotal();
                item[3] = LISTA.get(i).getgrupo();
                if (item[3].equals(0)) {
                    tablaproforma.addRow(item);
                    total += LISTA.get(i).getTotal();
                }
            }
            BigDecimal bd = new BigDecimal(total);
            this.jLabel4.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        }

    }

    private void Deshabilita(boolean valor) {
        jComboBox1.setEnabled(valor);
        jComboBox4.setEnabled(valor);
        jComboBox2.setEnabled(valor);
        jButton3.setEnabled(valor);
    }

    private void REPORTE_CAJA_TIPOS_PAGOS() {
        List<Venta> ListaPagos = objVentas.REPORTE_CAJA_TIPOS_PAGOS(pven);
        double total = 0;

        if (ListaPagos.size() > 0) {
            for (int i = 0; i < ListaPagos.size(); i++) {
                item_pagos[0] = ListaPagos.get(i).getDescripcionTipoPago();
                item_pagos[1] = ListaPagos.get(i).getCantidadpagos();
                item_pagos[2] = ListaPagos.get(i).getTotal();
                tablapagos.addRow(item_pagos);
                total += ListaPagos.get(i).getTotal();
            }

            BigDecimal bd = new BigDecimal(total);
            this.jLabel7.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            this.jPanel4.setVisible(true);
            this.jPanel5.setVisible(true);
        }

    }

    private void LimpiatTabla() {
        int cant = this.jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproforma.removeRow(i);
            }
        }
    }

    private void LimpiatTabla1() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablapagos.removeRow(i);
            }
        }
    }

    private void CerrarCaja() {
        int reply = JOptionPane.showConfirmDialog(null, " Desea realizar su Cierre de Caja ? ", "NORTFARMA", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            depositado = 0.0;
            deposito_dolares = 0.0;
            LogicaConfiguracion objConfig = new LogicaConfiguracion();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex()).getIdcaja();

            if (Verifica_Datos()) {
                turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
                Cajas objeto = new Cajas(idcaja, idbotica, idperso, this.jTextField1.getText().trim(), depositado, deposito_dolares, turno);
                boolean resul = objConfig.Cierre_Caja(objeto);

                if (resul) {
                    JOptionPane.showMessageDialog(null, " Su Caja Ha Sido Cerrada Correctamente ", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                    Cerrar();
                } else {
                    JOptionPane.showMessageDialog(null, " Error al Cerra su Caja ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    private void Cerrar() {
        obj.marcacdo = false;
        obj.Habilita(true);
        lista.clear();
        listaturno.clear();
        lista2.clear();
        listaturno2.clear();
        this.dispose();
    }

    private boolean Verifica_Datos() {

        if (jComboBox4.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }

        return false;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtmonto = new javax.swing.JTextField();
        btntransferir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        combocajas = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        comboturnos = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ConsultaCaja.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel3.border.titleFont"), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBackground(resourceMap.getColor("jTable1.background")); // NOI18N
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion Pagos", "Cantidad", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setShowHorizontalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        }

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel5.add(jLabel6);

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 22));
        jPanel5.add(jLabel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel3.border.titleFont"), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBackground(resourceMap.getColor("jTable2.background")); // NOI18N
        jTable2.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setShowHorizontalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
        }

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 22));
        jPanel4.add(jLabel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(109, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setName("jTextField3"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jComboBox4.setForeground(resourceMap.getColor("jComboBox4.foreground")); // NOI18N
        jComboBox4.setName("jComboBox4"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setName("jComboBox2"); // NOI18N

        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, 350, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(11, 11, 11)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtmonto.setForeground(resourceMap.getColor("txtmonto.foreground")); // NOI18N
        txtmonto.setText(resourceMap.getString("txtmonto.text")); // NOI18N
        txtmonto.setName("txtmonto"); // NOI18N

        btntransferir.setIcon(resourceMap.getIcon("btntransferir.icon")); // NOI18N
        btntransferir.setText(resourceMap.getString("btntransferir.text")); // NOI18N
        btntransferir.setName("btntransferir"); // NOI18N
        btntransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransferirActionPerformed(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        combocajas.setForeground(resourceMap.getColor("combocajas.foreground")); // NOI18N
        combocajas.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        combocajas.setName("combocajas");

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        comboturnos.setForeground(resourceMap.getColor("comboturnos.foreground")); // NOI18N
        comboturnos.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        comboturnos.setName("comboturnos"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        txtusuario.setForeground(resourceMap.getColor("txtusuario.foreground")); // NOI18N
        txtusuario.setText(resourceMap.getString("txtusuario.text")); // NOI18N
        txtusuario.setName("txtusuario"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboturnos, javax.swing.GroupLayout.Alignment.LEADING, 0, 154, Short.MAX_VALUE)
                        .addComponent(combocajas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combocajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboturnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntransferir)
                .addContainerGap())
        );

        jLabel8.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel8.AccessibleContext.accessibleName")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LimpiatTabla();
        LimpiatTabla1();
        int miidcajero;

        try {

            turno = Lista_Turnos.get(jComboBox4.getSelectedIndex() - 1).getId_Turno();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex()).getIdcaja();
            Cajas micaja = new Cajas();
            micaja.setIdbotica(idbotica);
            micaja.setIdcaja(idcaja);
            micaja.setIdturno(turno);

            if (Lista_Cajeros.size() > 0) {
                miidcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex()).getId_Personal();
            } else {
                miidcajero = codigo;
            }

            micaja.setIdcajero(miidcajero);
            int resul = logcajas.Consulta_Caja_Cerrada(micaja);

            if (resul == 1) {
                if (Verifica_Datos()) {
                    MuestraDetalle_Resumen();
                    REPORTE_CAJA_TIPOS_PAGOS();
                    Deshabilita(false);
                    jButton1.setEnabled(true);
                }
            } else {
                if (resul == 0) {
                    JOptionPane.showMessageDialog(null, " Lo sentimos : Esta Caja ya se encuentra Cerrada ", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (resul == 2) {
                    JOptionPane.showMessageDialog(null, " No Existe Informacion de este Turno ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //if (transferir == 1) {
            CerrarCaja();
        /*} else {
            JOptionPane.showMessageDialog(rootPane, "Primero asigne monto a transferir", "Error", JOptionPane.ERROR_MESSAGE);
        }*/
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cerrar();
}//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.dispose();
        obj.marcacdo = false;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btntransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransferirActionPerformed
        // TODO add your handling code here:
        transferir();
    }//GEN-LAST:event_btntransferirActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        idcaja = listacajas.get(jComboBox1.getSelectedIndex()).getIdcaja();
        if (idcaja ==1){
            this.txtmonto.setEnabled(true);
            this.combocajas.setEnabled(true);
            this.comboturnos.setEnabled(true);
            this.btntransferir.setEnabled(true);
        }else{
            this.txtmonto.setEnabled(false);
            this.combocajas.setEnabled(false);
            this.comboturnos.setEnabled(false);
            this.btntransferir.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void transferir() {
        String fecha = obtenerfecha();
        Cajas_Fondos cf = new Cajas_Fondos();
        BuscaTurno t = new BuscaTurno();
        BuscaCaja c = new BuscaCaja();
        idpersonal = obj.getId_personal_botica();
        idbotica = obj.getIdbotica();
        int idcaja;
        int idturno;
        double monto;
        idcaja = lista.get(combocajas.getSelectedIndex()).getId();
        idturno = listaturno.get(comboturnos.getSelectedIndex()).getIdturno();
        monto = Double.parseDouble(txtmonto.getText());
        cf.setIdbotica(idbotica);
        cf.setIdcaja(idcaja);
        cf.setFecha(fecha);
        cf.setIdpersonal(idpersonal);
        cf.setMonto(monto);
        if (idcaja == 1) {
            if (idturno == 1) {
                CajasFondos.insertafondo(cf);
            } else {
                JOptionPane.showMessageDialog(null, "Solo Turno 1 puede transferir efectivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo Caja 1 puede transferir efectivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        transferir = 1;
    }

    private void buscaturno() {
        idpersonal = obj.getId_personal_botica();
        idbotica = obj.getIdbotica();
        String fecha = obtenerfecha();
        listaturno = DatosBuscaTurno.retornaturno(idpersonal, idbotica, fecha);
        if (listaturno.size() < 1) {
            transferir = 1;
        }
        comboturnos.removeAllItems();
        for (BuscaTurno b : listaturno) {
            comboturnos.addItem(b.getDescripcion());
        }
    }

    private void buscacaja() {
        String fecha = obtenerfecha();
        lista = DatosBuscaCaja.retornacaja(idpersonal, idbotica, fecha);
        if (lista.size() < 1) {
            transferir = 1;
        }
        combocajas.removeAllItems();
        for (BuscaCaja b : lista) {
            combocajas.addItem(b.getDescripcion());
        }
    }

    private void verificaturnoycaja() {
        if (lista.size() == 1) {
            for (BuscaCaja b : lista) {
                int id = b.getId();
                if (id > 1) {
                    transferir = 1;
                    //btntransferir.setEnabled(false);
                }

            }
        }
        if (listaturno.size() == 1) {
            for (BuscaTurno b : listaturno) {
                int id = b.getIdturno();
                if (id > 1) {
                    transferir = 1;
                    //btntransferir.setEnabled(false);
                }

            }
        }

    }

    private String obtenerfecha() {
        Calendar fechatotal = new GregorianCalendar();
        String fecha;
        int ano, mes, dia;
        ano = fechatotal.get(Calendar.YEAR);
        mes = fechatotal.get(Calendar.MONTH) + 1;
        dia = fechatotal.get(Calendar.DAY_OF_MONTH);
        fecha = String.valueOf(ano) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia);
        return fecha;
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntransferir;
    private javax.swing.JComboBox<String> combocajas;
    private javax.swing.JComboBox<String> comboturnos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField txtmonto;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
