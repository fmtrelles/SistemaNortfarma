/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IngresoAbonoCaja.java
 *
 * Created on 12/03/2013, 05:49:18 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaAbonoCajaData;
import CapaLogica.LogicaBilleteMoneda;
import CapaLogica.LogicaVenta;
import entidad.Abono_Caja;
import entidad.Billetes_Monedas;
import entidad.Boticas;
import entidad.Cajas;
import entidad.Personal;
import entidad.TipoCambio;
import entidad.Tipo_Moneda;
import entidad.Turno;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S. Gomez Saavedra
 */
public class IngresoAbonoCaja1 extends javax.swing.JFrame {

    LogicaVenta logVenta = new LogicaVenta();
    LogicaAbonoCajaData logAbono;
    LogicaBilleteMoneda logBilletes = new LogicaBilleteMoneda();
    Object[] mis_abonos = new Object[9];
    Object[] mis_abonos_billetes = new Object[9];
    private DefaultTableModel modelo;
    private DefaultTableModel modelo_1;
    Cajas micaja;
    Object cajero;
    int podecimal = 2;
    OpcionesMenu objet;
    TableColumn columnaTabla = null;
    double tipoCambio;
    int fila;
    AplicacionVentas objeto;

    /** Creates new form IngresoAbonoCaja */
    public IngresoAbonoCaja1(java.awt.Frame parent, Reporte_Caja_Detallado obj, Cajas objcaja, Object caj) {
        //  super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        //objeto = obj;
        podecimal = objet.getCantidadDecimales();
        micaja = objcaja;
        cajero = caj;
        jTable1.setDefaultRenderer(Object.class, new IconCellRenderer());
        jTable2.setDefaultRenderer(Object.class, new IconCellRenderer());
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo_1 = (DefaultTableModel) jTable2.getModel();
        AparienciaTabla();
        MuestraMonto();
        Lista_Billetes();
        Muestra_Datos();
        Muestra_Tipos_Cambios();
    }

    public IngresoAbonoCaja1(AplicacionVentas obj1) {
        initComponents();
        this.setLocationRelativeTo(null);
        objeto = obj1;
        podecimal = objet.getCantidadDecimales();
        //micaja = objcaja;
        //cajero = caj;
        jTable1.setDefaultRenderer(Object.class, new IconCellRenderer());
        jTable2.setDefaultRenderer(Object.class, new IconCellRenderer());
        modelo = (DefaultTableModel) jTable1.getModel();
        modelo_1 = (DefaultTableModel) jTable2.getModel();
        AparienciaTabla();
        //MuestraMonto();
        //Lista_Billetes();
        //Muestra_Datos();
        //Muestra_Tipos_Cambios();
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tleft1 = new DefaultTableCellRenderer();
        tleft1.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tleft1);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tleft1);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(tleft1);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(tleft1);
        DefaultTableCellRenderer tleft2 = new DefaultTableCellRenderer();
        tleft2.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tleft2);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(tleft1);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        jTable1.setColumnSelectionAllowed(true);
        jTable2.setColumnSelectionAllowed(true);

    }

    private void Muestra_Datos() {
        jLabel2.setText(micaja.getIdbotica());
        jLabel3.setText(String.valueOf(micaja.getIdcaja()));
        jLabel4.setText(String.valueOf(micaja.getTurno()));
        jLabel6.setText(micaja.getFecha());
        jLabel12.setText(cajero.toString());
    }

    private void MuestraMonto() {
        double monto = logVenta.Recupera_MontoVenta(micaja);
        BigDecimal bd = new BigDecimal(monto);
        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        txtMontoSis.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void Lista_Billetes() {

        try {

            List<Abono_Caja> miLista = logBilletes.ListaBilletes(micaja);
            jTable1.setRowHeight(50);
            jTable2.setRowHeight(50);

            if (miLista.size() > 0) {
                try {

                    for (Abono_Caja billete : miLista) {
                        if (billete.getBiilete().getTipomoneda().getTipo_Moneda() == 1) {

                            ImageIcon icon = new ImageIcon(getClass().getResource(billete.getBiilete().getRuta()));
                            JLabel precio = new JLabel(billete.getBiilete().getTipomoneda().getSimbolo() + " " + String.valueOf(billete.getBiilete().getValor()));
                            precio.setHorizontalAlignment(JLabel.CENTER);
                            precio.setFont(new Font("Tahoma", Font.BOLD, 16));
                            precio.setForeground(Color.ORANGE);
                            precio.setToolTipText("Monto de la Moneda");

                            mis_abonos[0] = new JLabel(icon);
                            mis_abonos[1] = precio;
                            mis_abonos[2] = billete.getBiilete().getTipomoneda().getTipo_Moneda();
                            mis_abonos[3] = billete.getBiilete().getValor();
                            mis_abonos[4] = billete.getCantidad();
                            mis_abonos[5] = billete.getTotal();
                            mis_abonos[6] = billete.getBiilete().getId_Billete();
                            mis_abonos[7] = billete.getBiilete().getRuta();
                            mis_abonos[8] = billete.getBiilete().getTipomoneda().getSimbolo();
                            modelo.addRow(mis_abonos);
                        } else {

                            ImageIcon icon = new ImageIcon(getClass().getResource(billete.getBiilete().getRuta()));
                            JLabel precio = new JLabel(billete.getBiilete().getTipomoneda().getSimbolo() + " " + String.valueOf(billete.getBiilete().getValor()));
                            precio.setHorizontalAlignment(JLabel.CENTER);
                            precio.setFont(new Font("Tahoma", Font.BOLD, 16));
                            precio.setForeground(Color.ORANGE);
                            precio.setToolTipText("Monto de la Moneda");

                            mis_abonos_billetes[0] = new JLabel(icon);
                            mis_abonos_billetes[1] = precio;
                            mis_abonos_billetes[2] = billete.getBiilete().getTipomoneda().getTipo_Moneda();
                            mis_abonos_billetes[3] = billete.getBiilete().getValor();
                            mis_abonos_billetes[4] = billete.getCantidad();
                            mis_abonos_billetes[5] = billete.getTotal();
                            mis_abonos_billetes[6] = billete.getBiilete().getId_Billete();
                            mis_abonos_billetes[7] = billete.getBiilete().getRuta();
                            mis_abonos_billetes[8] = billete.getBiilete().getTipomoneda().getSimbolo();
                            modelo_1.addRow(mis_abonos_billetes);
                        }
                    }

                    CalculaTotal();

                } catch (Exception ex) {
                    System.out.println("ERROR : " + ex.toString());
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void Muestra_Tipos_Cambios() {
        List<TipoCambio> misTiposCambios = logVenta.Retorna_Tipos_Cambios();

        for (int i = 0; i < misTiposCambios.size(); i++) {
            this.txtTipCambio.setText(String.valueOf(misTiposCambios.get(i).getTipoCambio()));
            tipoCambio = misTiposCambios.get(i).getTipoCambio();
        }
    }

    private void CalculaIngreso(int tabla) {

        int cantIngresada;
        int TipoMoneda;
        double valorBillete;

        try {

            if (fila >= 0) {

                if (tabla == 0) {
                    TipoMoneda = Integer.parseInt(jTable1.getValueAt(fila, 2).toString());
                    cantIngresada = Integer.parseInt(jTable1.getValueAt(fila, 4).toString());
                    valorBillete = Double.parseDouble(jTable1.getValueAt(fila, 3).toString());
                } else {
                    TipoMoneda = Integer.parseInt(jTable2.getValueAt(fila, 2).toString());
                    cantIngresada = Integer.parseInt(jTable2.getValueAt(fila, 4).toString());
                    valorBillete = Double.parseDouble(jTable2.getValueAt(fila, 3).toString());
                }

                if (TipoMoneda <= 2) {
                    double monto = cantIngresada * valorBillete;
                    BigDecimal bd = new BigDecimal(monto);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    monto = bd.doubleValue();

                    if (tabla == 0) {
                        modelo.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    } else {
                        modelo_1.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    }


                } else {
                    double monto = (cantIngresada * valorBillete) * tipoCambio;
                    BigDecimal bd = new BigDecimal(monto);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    monto = bd.doubleValue();
                    if (tabla == 0) {
                        modelo.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    } else {
                        modelo_1.setValueAt(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 5);
                    }
                }

                CalculaTotal();

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void CalculaTotal() {
        double suma = 0.0;

        for (int pos = 0; pos < jTable1.getRowCount(); pos++) {
            Object var = jTable1.getValueAt(pos, 5);
            if (var != null) {
                double valsuma = Double.parseDouble(jTable1.getValueAt(pos, 5).toString());
                suma = suma + valsuma;
            }
        }

        for (int pos = 0; pos < jTable2.getRowCount(); pos++) {
            Object var = jTable2.getValueAt(pos, 5);
            if (var != null) {
                double valsuma = Double.parseDouble(jTable2.getValueAt(pos, 5).toString());
                suma = suma + valsuma;
            }
        }

        BigDecimal bd = new BigDecimal(suma);
        bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        txtIngresado.setText(bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

        Double diferencia = suma - Double.parseDouble(txtMontoSis.getText().trim());
        BigDecimal bd1 = new BigDecimal(diferencia);
        bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
        txtDiferencia.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());

        if (diferencia < 0) {
            txtDiferencia.setBackground(Color.red);
            txtDiferencia.setForeground(Color.WHITE);
        } else {
            Color col = new Color(9, 133, 215);
            txtDiferencia.setBackground(col);
            txtDiferencia.setForeground(Color.WHITE);

        }
    }

    private void Guardar_Abono() {

        try {

            logAbono = new LogicaAbonoCajaData();
            List<Abono_Caja> listaTotal = new ArrayList<Abono_Caja>();
            Abono_Caja abonoCaja;
            Boticas botica = new Boticas();
            Cajas caja = new Cajas();
            Turno turno = new Turno();
            Personal persona = new Personal();
            Billetes_Monedas billeteMoneda;
            Tipo_Moneda tipoMoneda;

            abonoCaja = new Abono_Caja();
            botica.setId_Botica(micaja.getIdbotica());
            caja.setIdcaja(micaja.getIdcaja());
            turno.setId_Turno(micaja.getTurno());
            persona.setId_personal_Caja(micaja.getIdpersonal());
            abonoCaja.setBotica(botica);
            abonoCaja.setPersona(persona);
            abonoCaja.setCaja(caja);
            abonoCaja.setFECHA(micaja.getFecha());
            abonoCaja.setTurno(turno);
            listaTotal.add(abonoCaja);

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object var = jTable1.getValueAt(i, 5);
                if (Double.parseDouble(var.toString().trim()) > 0.0) {
                    abonoCaja = new Abono_Caja();
                    tipoMoneda = new Tipo_Moneda();
                    billeteMoneda = new Billetes_Monedas();
                    billeteMoneda.setValor(Double.parseDouble(jTable1.getValueAt(i, 3).toString().trim()));
                    tipoMoneda.setTipo_Moneda(Integer.parseInt(jTable1.getValueAt(i, 2).toString().trim()));
                    tipoMoneda.setSimbolo(jTable1.getValueAt(i, 8).toString().trim());
                    billeteMoneda.setId_Billete(Integer.parseInt(jTable1.getValueAt(i, 6).toString().trim()));
                    billeteMoneda.setRuta((jTable1.getValueAt(i, 7).toString().trim()));
                    billeteMoneda.setTipomoneda(tipoMoneda);
                    abonoCaja.setBiilete(billeteMoneda);
                    abonoCaja.setCantidad(Integer.parseInt(jTable1.getValueAt(i, 4).toString().trim()));
                    abonoCaja.setTotal(Double.parseDouble(jTable1.getValueAt(i, 5).toString().trim()));
                    listaTotal.add(abonoCaja);
                }
            }

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                Object var = jTable2.getValueAt(i, 5);
                if (Double.parseDouble(var.toString().trim()) > 0.0) {
                    abonoCaja = new Abono_Caja();
                    billeteMoneda = new Billetes_Monedas();
                    tipoMoneda = new Tipo_Moneda();
                    billeteMoneda.setValor(Double.parseDouble(jTable2.getValueAt(i, 3).toString().trim()));
                    tipoMoneda.setTipo_Moneda(Integer.parseInt(jTable2.getValueAt(i, 2).toString().trim()));
                    tipoMoneda.setSimbolo(jTable2.getValueAt(i, 8).toString().trim());
                    billeteMoneda.setId_Billete(Integer.parseInt(jTable2.getValueAt(i, 6).toString().trim()));
                    billeteMoneda.setRuta((jTable2.getValueAt(i, 7).toString().trim()));
                    billeteMoneda.setTipomoneda(tipoMoneda);
                    abonoCaja.setBiilete(billeteMoneda);
                    abonoCaja.setCantidad(Integer.parseInt(jTable2.getValueAt(i, 4).toString().trim()));
                    abonoCaja.setTotal(Double.parseDouble(jTable2.getValueAt(i, 5).toString().trim()));
                    listaTotal.add(abonoCaja);
                }
            }

            if (listaTotal.size() > 1) {

                ConfirmarIngresoAbono confirmar = new ConfirmarIngresoAbono(this, listaTotal);
                confirmar.pack();
                confirmar.setVisible(true);

                if (confirmar.isEstado()) {
                    int reul = logAbono.Guardar_Abono(listaTotal);

                    if (reul == 1) {
                        JOptionPane.showMessageDialog(null, " SU ABONO SE HA REGISTRADO CORRECTAMENTE ", "Ingresado", JOptionPane.INFORMATION_MESSAGE);
     //                   objeto.GeneraReporteEfectivo();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, " ERROR AL INGRESAR SU ABONO \n PORAFAVOR COMINIQUESE CON INFORMATICA", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, " USTED NO HA INGRESADO NINGUN MONTO", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public class IconCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {


            /*************************************/
            //  setForeground(Color.black);
            if (value instanceof JLabel) {
                JLabel label = (JLabel) value;
                label.setOpaque(true);

                if (column == 0 || column == 1) { //EL NUMERO DE LA COLUMNA QUE DESEAMOS PINTAR
                    fillColor(new Color(236, 233, 216), table, label, isSelected);
                } else if (column == 3) {
                    fillColor(new Color(217, 216, 216), table, label, isSelected);
                }

                return label;

            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }

        public void fillColor(Color color, JTable t, JLabel l, boolean isSelected) {

            if (isSelected) {
                l.setBackground(t.getBackground());
                l.setForeground(t.getForeground());
            } else {
                l.setBackground(color);
                l.setForeground(t.getForeground());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTipCambio = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jSeparator1 = new javax.swing.JSeparator();
        buttonSeven2 = new org.edisoncor.gui.button.ButtonSeven();
        buttonSeven3 = new org.edisoncor.gui.button.ButtonSeven();
        txtMontoSis = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        txtDiferencia = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        txtIngresado = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(IngresoAbonoCaja1.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setForeground(resourceMap.getColor("jTable1.foreground")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Billete", "Moneda", "TIPO", "VALOR", "Cantidad", "Total", "ID", "RUTA", "SIMBOL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 160));

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel25.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel26.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel18)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel2)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel25))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel5.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtTipCambio.setBackground(resourceMap.getColor("txtTipCambio.background")); // NOI18N
        txtTipCambio.setEditable(false);
        txtTipCambio.setForeground(resourceMap.getColor("txtTipCambio.foreground")); // NOI18N
        txtTipCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipCambio.setText(resourceMap.getString("txtTipCambio.text")); // NOI18N
        txtTipCambio.setFont(resourceMap.getFont("txtTipCambio.font")); // NOI18N
        txtTipCambio.setName("txtTipCambio"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        buttonSeven2.setBackground(resourceMap.getColor("buttonSeven3.background")); // NOI18N
        buttonSeven2.setText(resourceMap.getString("buttonSeven2.text")); // NOI18N
        buttonSeven2.setFont(resourceMap.getFont("buttonSeven3.font")); // NOI18N
        buttonSeven2.setName("buttonSeven2"); // NOI18N
        buttonSeven2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven2ActionPerformed(evt);
            }
        });

        buttonSeven3.setBackground(resourceMap.getColor("buttonSeven3.background")); // NOI18N
        buttonSeven3.setForeground(resourceMap.getColor("buttonSeven3.foreground")); // NOI18N
        buttonSeven3.setText(resourceMap.getString("buttonSeven3.text")); // NOI18N
        buttonSeven3.setFont(resourceMap.getFont("buttonSeven3.font")); // NOI18N
        buttonSeven3.setName("buttonSeven3"); // NOI18N
        buttonSeven3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSeven3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtMontoSis.setBackground(resourceMap.getColor("txtMontoSis.background")); // NOI18N
        txtMontoSis.setEditable(false);
        txtMontoSis.setForeground(resourceMap.getColor("txtMontoSis.foreground")); // NOI18N
        txtMontoSis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoSis.setText(resourceMap.getString("txtMontoSis.text")); // NOI18N
        txtMontoSis.setFont(resourceMap.getFont("txtMontoSis.font")); // NOI18N
        txtMontoSis.setName("txtMontoSis"); // NOI18N

        txtDiferencia.setBackground(resourceMap.getColor("txtMontoSis.background")); // NOI18N
        txtDiferencia.setEditable(false);
        txtDiferencia.setForeground(resourceMap.getColor("txtMontoSis.foreground")); // NOI18N
        txtDiferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiferencia.setText(resourceMap.getString("txtDiferencia.text")); // NOI18N
        txtDiferencia.setFont(resourceMap.getFont("txtMontoSis.font")); // NOI18N
        txtDiferencia.setName("txtDiferencia"); // NOI18N

        txtIngresado.setBackground(resourceMap.getColor("txtMontoSis.background")); // NOI18N
        txtIngresado.setEditable(false);
        txtIngresado.setForeground(resourceMap.getColor("txtMontoSis.foreground")); // NOI18N
        txtIngresado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIngresado.setText(resourceMap.getString("txtIngresado.text")); // NOI18N
        txtIngresado.setFont(resourceMap.getFont("txtMontoSis.font")); // NOI18N
        txtIngresado.setName("txtIngresado"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setForeground(resourceMap.getColor("jTable2.foreground")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Billete", "Moneda", "TIPO", "VALOR", "CANTIDAD", "TOTAL", "ID", "RUTA", "SIMBOL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(85);
        jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
        jTable2.getColumnModel().getColumn(4).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
        jTable2.getColumnModel().getColumn(5).setResizable(false);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
        jTable2.getColumnModel().getColumn(6).setResizable(false);
        jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
        jTable2.getColumnModel().getColumn(7).setResizable(false);
        jTable2.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
        jTable2.getColumnModel().getColumn(8).setResizable(false);
        jTable2.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable2.columnModel.title8")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDiferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(10, 10, 10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtIngresado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMontoSis, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMontoSis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        fila = this.jTable1.getSelectedRow();

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            CalculaIngreso(0);
        }

    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        fila = this.jTable2.getSelectedRow();
    }//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            CalculaIngreso(1);
        }
    }//GEN-LAST:event_jTable2KeyTyped

    private void buttonSeven2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (p == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_buttonSeven2ActionPerformed

    private void buttonSeven3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven3ActionPerformed
        Guardar_Abono();
}//GEN-LAST:event_buttonSeven3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonSeven2;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven3;
    public javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtDiferencia;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtIngresado;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtMontoSis;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtTipCambio;
    // End of variables declaration//GEN-END:variables
}
