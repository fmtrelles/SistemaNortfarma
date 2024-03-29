/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormVentaMotorizado.java
 *
 * Created on 18/06/2013, 03:56:11 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaVenta;
import entidad.Detalle_VentaDelivery;
import entidad.Personal;
import entidad.Venta;
import entidad.Venta_Delivery;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class FormVentaMotorizado extends javax.swing.JInternalFrame {

    AplicacionVentas objventa;
    private String Id_Botica;
    LogicaVenta logventa = new LogicaVenta();
    Object[] datosDetalle = new Object[6];
    private DefaultTableModel model;
    TableColumnModel colModel1;
    Object[] misventas = new Object[4];
    private DefaultTableModel model_1;
    TableColumnModel colModel1_1;
    private int cantidadVentas;
    private double TotalVenta;
    int podecimal = 2;
    LogicaPersonal logpersonal = new LogicaPersonal();
    List<Personal> listMotorizados;
    List<String> Internos = new ArrayList<String>();
    Object[] misventas_3 = new Object[5];
    private DefaultTableModel model_3;
    TableColumnModel colModel1_3;
    Object[] misventas_4 = new Object[4];
    private DefaultTableModel model_4;
    TableColumnModel colModel1_4;

    /** Creates new form FormVentaMotorizado */
    public FormVentaMotorizado(String idboti, AplicacionVentas obj) {
        initComponents();
        listMotorizados = logpersonal.Lista_Motorizados("%");
        objventa = obj;
        podecimal = OpcionesMenu.getCantidadDecimales();
        Id_Botica = idboti;
        model = (DefaultTableModel) jTable1.getModel();
        colModel1 = jTable1.getColumnModel();
        model_1 = (DefaultTableModel) jTable2.getModel();
        colModel1_1 = jTable2.getColumnModel();
        model_3 = (DefaultTableModel) jTable4.getModel();
        colModel1_3 = jTable4.getColumnModel();
        model_4 = (DefaultTableModel) jTable3.getModel();
        colModel1_4 = jTable3.getColumnModel();
        AparienciaTabla();
        BuscarVenta();
        ListaSalidaDelivery();
        setupAutoComplete(txtMotorizado, listMotorizados);
        txtMotorizado.setColumns(30);
        jTabbedPane2.setEnabledAt(1, false);
    }

    private void AparienciaTabla() {
        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        this.jTable2.getColumnModel().getColumn(3).setCellRenderer(tcenter);
        this.jTable4.getColumnModel().getColumn(1).setCellRenderer(tcenter);
        this.jTable4.getColumnModel().getColumn(3).setCellRenderer(tcenter);
        this.jTable3.getColumnModel().getColumn(3).setCellRenderer(tcenter);
    }

    private void BuscarVenta() {
        int valor = jComboBox1.getSelectedIndex();
        String filtro = this.jTextField1.getText().trim() + '%';
        Venta obj = new Venta(Id_Botica, filtro);
        List<Venta> listaInternos = logventa.Lista_Internos_Ventas_Delivery(obj, valor);
        LimpiatTabla_Ventas();
        for (Venta miventa : listaInternos) {
            datosDetalle[0] = miventa.getId_Venta();
            datosDetalle[1] = miventa.getTipventa();
            datosDetalle[2] = miventa.getSerie();
            datosDetalle[3] = miventa.getNumero();
            datosDetalle[4] = miventa.getTotal();
            datosDetalle[5] = "  " + miventa.getCajero();
            model.addRow(datosDetalle);
        }
    }

    private void ListaSalidaDelivery() {
        List<Venta_Delivery> milista = logventa.Lista_Salidas_Delivery(Id_Botica);
        for (Venta_Delivery miventa : milista) {
            misventas_3[0] = miventa.getNumeroSalida();
            misventas_3[1] = miventa.getTotal();
            misventas_3[2] = miventa.getHoraSalida();
            misventas_3[3] = miventa.getEfectivoEntregado();
            misventas_3[4] = miventa.getPersona().getApellido() + ' ' + miventa.getPersona().getNombre();
            model_3.addRow(misventas_3);
        }
    }

    private void CerrarVentana() {
        if (this.jTable2.getRowCount() > 0) {
            int p = JOptionPane.showConfirmDialog(null, " Desea Salir ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                objventa.Habilita(true);
                objventa.marcacdo = false;
                dispose();
            }
        } else {
            objventa.Habilita(true);
            objventa.marcacdo = false;
            dispose();
        }
    }

    private void AgregarVenta() {
        int fila = this.jTable1.getSelectedRow();
        boolean existe = false;

        if (fila >= 0) {
            TotalVenta = 0.0;
            cantidadVentas = 0;
            buttonAction3.setEnabled(true);
            buttonAction2.setEnabled(true);
            jButton1.setEnabled(true);
            String interno = this.jTable1.getValueAt(fila, 0).toString().trim();
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                cantidadVentas++;
                TotalVenta = TotalVenta + Double.parseDouble(jTable2.getValueAt(i, 3).toString().trim());
                if (jTable2.getValueAt(i, 0).toString().trim().compareTo(interno) == 0) {
                    existe = true;
                }
            }
            if (!existe) {
                misventas[0] = this.jTable1.getValueAt(fila, 0);
                misventas[1] = this.jTable1.getValueAt(fila, 2);
                misventas[2] = this.jTable1.getValueAt(fila, 3);
                misventas[3] = this.jTable1.getValueAt(fila, 4);
                model_1.addRow(misventas);
                cantidadVentas++;
                TotalVenta = TotalVenta + Double.parseDouble(jTable2.getValueAt(jTable2.getRowCount() - 1, 3).toString().trim());
            } else {
                JOptionPane.showMessageDialog(null, " Interno : " + interno + " ya esta Agregado ", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            BigDecimal bd1 = new BigDecimal(TotalVenta);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            TotalVenta = bd1.doubleValue();

            jTextField2.setText(String.valueOf(cantidadVentas));
            jTextField3.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            txtMonto.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            txtEfectivo.setText("0.00");

        } else {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione una Venta ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (jTable2.getRowCount() == 0) {
            buttonAction3.setEnabled(false);
            buttonAction2.setEnabled(false);
            jButton1.setEnabled(false);
            TotalVenta = 0.0;
            cantidadVentas = 0;
        }
    }

    private void EliminaItemVenta() {
        int fila = this.jTable2.getSelectedRow();

        if (fila >= 0) {
            this.model_1.removeRow(fila);
            TotalVenta = 0.0;
            cantidadVentas = 0;
            jButton1.setEnabled(true);
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                cantidadVentas++;
                TotalVenta = TotalVenta + Double.parseDouble(jTable2.getValueAt(i, 3).toString().trim());
            }

            BigDecimal bd1 = new BigDecimal(TotalVenta);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            TotalVenta = bd1.doubleValue();

            jTextField2.setText(String.valueOf(cantidadVentas));
            jTextField3.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            txtMonto.setText(bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString());
            txtEfectivo.setText("0.00");
        }

        if (jTable2.getRowCount() == 0) {
            buttonAction3.setEnabled(false);
            buttonAction2.setEnabled(false);
            jButton1.setEnabled(false);
            TotalVenta = 0.0;
            cantidadVentas = 0;
        }
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public static ImageIcon getImageIcon(String url, Map<String, ImageIcon> IMAGE_ICON_CACHE) {
        if (url == null) {
            return null;
        }
        if (IMAGE_ICON_CACHE.get(url) == null) {
            ImageIcon image = null;
            InputStream in = FormVentaMotorizado.class.getResourceAsStream(url);
            if (in != null) {
                try {
                    byte buffer[] = new byte[in.available()];
                    for (int i = 0, n = in.available(); i < n; i++) {
                        buffer[i] = (byte) in.read();
                    }
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image img = toolkit.createImage(buffer);
                    image = new ImageIcon(img);
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            if (image == null) {
                if (ClassLoader.getSystemResource(url) != null) {
                    image = new ImageIcon(ClassLoader.getSystemResource(url));
                } else {
                    image = new ImageIcon(url);
                }
            }
            if (image == null) {
                System.err.println("can't load image '" + url + "'");
            } else {
                IMAGE_ICON_CACHE.put(url, image);
            }
        }
        return (ImageIcon) IMAGE_ICON_CACHE.get(url);
    }

    static void setupAutoComplete(final JTextField txtInput, final List<Personal> items) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        final Map<String, ImageIcon> IMAGE_ICON_CACHE = new HashMap<String, ImageIcon>();

        final JComboBox cbInput = new JComboBox(model) {

            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };

        setAdjusting(cbInput, false);

        for (Personal item : items) {
            model.addElement(item.getApellido());
        }
        cbInput.setSelectedItem(null);

        cbInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        txtInput.setText(cbInput.getSelectedItem().toString());
                    }
                }
            }
        });

        cbInput.setRenderer(new DefaultListCellRenderer() {

            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    this.setText(value.toString());
                    String url = value.toString().replaceAll(" ", "_") + ".png";
                    ImageIcon icon = getImageIcon(url, IMAGE_ICON_CACHE);
                    if (icon != null) {
                        this.setIcon(icon);
                    }
                }
                return this;
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        txtInput.setText(cbInput.getSelectedItem().toString());
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });

        txtInput.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = txtInput.getText();
                if (!input.isEmpty()) {
                    for (Personal item : items) {
                        if (item.getApellido().toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(item.getId_Personal() + " . " + item.getApellido());
                        }
                    }
                }
                cbInput.hidePopup();
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }
        });


        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);
    }

    private void Guarda_VentaDelivery() {
        int idmotorizado;
        try {
            idmotorizado = RecuperaIdMotorizado();
        } catch (Exception ex) {
            idmotorizado = -1;
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione Motorizado", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtMotorizado.requestFocus();
        }
        if (idmotorizado > 0) {
            String docnum = logventa.Recupera_DocumentoSalida(Id_Botica);
            int p = JOptionPane.showConfirmDialog(null, " Desea Registrar  Salida Nº " + docnum + "  ", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (p == JOptionPane.YES_OPTION) {
                try {

                    Internos.removeAll(Internos);
                    for (int i = 0; i < jTable2.getRowCount(); i++) {
                        String idventa = jTable2.getValueAt(i, 0).toString().trim();
                        Internos.add(idventa);
                    }

                    Venta_Delivery objven = new Venta_Delivery();
                    objven.setId_Botica(Id_Botica);
                    objven.setNumeroSalida(docnum);
                    objven.setId_Personal(idmotorizado);
                    objven.setTotal(TotalVenta);
                    objven.setEfectivoEntregado(Double.parseDouble(txtEfectivo.getText().trim()));
                    objven.setActivo(0);

                    Venta_Delivery objsalida = logventa.GuardaSalida(objven, Internos);

                    if (objsalida != null) {
                        JOptionPane.showMessageDialog(null, "Salida Registrada Correctamente", "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                        objventa.Habilita(true);
                        objventa.marcacdo = false;
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar su Salida", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private int RecuperaIdMotorizado() throws Exception {
        String nombre = txtMotorizado.getText().trim();
        String numero = "";

        try {
            for (int i = 0; i < nombre.length(); i++) {
                Character car = nombre.charAt(i);
                if (car.isDigit(car)) {
                    numero = numero + car;
                } else {
                    break;
                }
            }
        } catch (Exception ex) {
            return -1;
        }

        return Integer.parseInt(numero);
    }

    private void VisualizaVenta() {
        int fila = jTable4.getSelectedRow();
        if (fila >= 0) {
            txtsalida.setText(jTable4.getValueAt(fila, 0).toString().trim());
            txtHoraSalida.setText(jTable4.getValueAt(fila, 2).toString());
            txtefectivo.setText(jTable4.getValueAt(fila, 3).toString());
            jTextField9.setEditable(true);
            jTextArea1.setEditable(true);
            Color micolor = new Color(255, 255, 255);
            jTextField9.setBackground(micolor);
            jTextArea1.setBackground(micolor);
            buttonAction6.setEnabled(true);
            buttonAction7.setEnabled(true);
            buttonAction5.setEnabled(false);
            jTable4.setEnabled(false);
            buttonAction5.setEnabled(false);
            jTextField9.requestFocus();
        }
    }

    private void VisualizaDetlleSalida() {
        int fila = jTable4.getSelectedRow();
        if (fila >= 0) {
            List<Detalle_VentaDelivery> milista = logventa.Detallle_Salidas_Delivery(Id_Botica, jTable4.getValueAt(fila, 0).toString().trim());
            LimpiatTabla_4();
            for (Detalle_VentaDelivery detalle : milista) {
                misventas_4[0] = detalle.getVenta().getId_Venta();
                misventas_4[1] = detalle.getVenta().getSerie();
                misventas_4[2] = detalle.getVenta().getNumero();
                misventas_4[3] = detalle.getVenta().getTotal();
                model_4.addRow(misventas_4);
            }
        }

    }

    private void Guardar_EntradaDelivery() {
        if (jTextField9.getText().trim().compareTo("") != 0) {
            try {

                int p = JOptionPane.showConfirmDialog(null, " Desea Actualizar esta Entrada de Delivery  ", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (p == JOptionPane.YES_OPTION) {
                    if (logventa.Actualiza_EntradaDelivery(Id_Botica, txtsalida.getText().trim(), Double.parseDouble(jTextField9.getText().trim()), jTextArea1.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "ENTRADA DE DELIVERY HA SIDO ACTUALIZADA", "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                        LimpiatTabla_2();
                        LimpiatTabla_4();
                        jTextField2.setText("");
                        jTextField3.setText("");
                        txtMotorizado.setText("");
                        txtMonto.setText("");
                        txtEfectivo.setText("");
                        buttonAction3.setEnabled(false);
                        buttonAction2.setEnabled(false);
                        jButton1.setEnabled(false);
                        buttonAction5.setEnabled(true);
                        buttonAction6.setEnabled(false);
                        buttonAction7.setEnabled(false);
                        txtsalida.setText("");
                        txtefectivo.setText("");
                        txtHoraSalida.setText("");
                        jTextField9.setText("");
                        jTextArea1.setText("");
                        jTextField9.setEditable(false);
                        jTextArea1.setEditable(false);
                        Color micolor = new Color(236, 233, 216);
                        jTextField9.setBackground(micolor);
                        jTextArea1.setBackground(micolor);
                        jTable4.setEnabled(true);
                        jTabbedPane1.setSelectedIndex(0);
                        jTabbedPane2.setSelectedIndex(0);
                        jTabbedPane1.setEnabledAt(1, false);
                        jTabbedPane2.setEnabledAt(1, false);

                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ENTRADA DE DELIVERY", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }



            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor Ingrese Vuelto del Delivery ", "Error",
                    JOptionPane.ERROR_MESSAGE);
            jTextField9.requestFocus();
        }
    }

    private void CancelarSalida() {
        int p = JOptionPane.showConfirmDialog(null, " Desea Cancelar esta Salida ", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            LimpiatTabla_2();
            jTextField2.setText("");
            jTextField3.setText("");
            txtMotorizado.setText("");
            txtMonto.setText("");
            txtEfectivo.setText("");
            buttonAction3.setEnabled(false);
            buttonAction2.setEnabled(false);
            jButton1.setEnabled(false);
            TotalVenta = 0.0;
            cantidadVentas = 0;
        }
    }

    private void LimpiatTabla_4() {
        int cant = model_4.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_4.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_2() {
        int cant = model_1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_1.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_Ventas() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        buttonAction4 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction5 = new org.edisoncor.gui.button.ButtonAction();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMotorizado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        buttonAction1 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction2 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction3 = new org.edisoncor.gui.button.ButtonAction();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtsalida = new javax.swing.JTextField();
        txtHoraSalida = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtefectivo = new javax.swing.JTextField();
        buttonAction6 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction7 = new org.edisoncor.gui.button.ButtonAction();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormVentaMotorizado.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Interno", "Numero de Boleta" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interno", "Tipo_Venta", "Serie", "Numero", "Total", "Cajero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(170);
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel7.setName("jPanel7"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SALIDA", "TOTAL", "HORA_SALIDA", "EFECTIVO_ENTREGADO", "MOTORIZADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setName("jTable4"); // NOI18N
        jTable4.setSelectionBackground(resourceMap.getColor("jTable4.selectionBackground")); // NOI18N
        jTable4.setSelectionForeground(resourceMap.getColor("jTable4.selectionForeground")); // NOI18N
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.getColumnModel().getColumn(0).setResizable(false);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable4.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable4.columnModel.title0")); // NOI18N
        jTable4.getColumnModel().getColumn(1).setResizable(false);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable4.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable4.columnModel.title1")); // NOI18N
        jTable4.getColumnModel().getColumn(2).setResizable(false);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable4.columnModel.title2")); // NOI18N
        jTable4.getColumnModel().getColumn(3).setResizable(false);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable4.columnModel.title3")); // NOI18N
        jTable4.getColumnModel().getColumn(4).setResizable(false);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable4.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable4.columnModel.title4")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        buttonAction4.setText(resourceMap.getString("buttonAction4.text")); // NOI18N
        buttonAction4.setFont(resourceMap.getFont("buttonAction4.font")); // NOI18N
        buttonAction4.setName("buttonAction4"); // NOI18N
        buttonAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction4ActionPerformed(evt);
            }
        });

        buttonAction5.setText(resourceMap.getString("buttonAction5.text")); // NOI18N
        buttonAction5.setEnabled(false);
        buttonAction5.setFont(resourceMap.getFont("buttonAction5.font")); // NOI18N
        buttonAction5.setName("buttonAction5"); // NOI18N
        buttonAction5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAction4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAction5, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buttonAction4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAction5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel8.setName("jPanel8"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INTERNO", "SERIE", "NUMERO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtMonto.setBackground(resourceMap.getColor("txtMonto.background")); // NOI18N
        txtMonto.setEditable(false);
        txtMonto.setFont(resourceMap.getFont("txtMonto.font")); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMonto.setText(resourceMap.getString("txtMonto.text")); // NOI18N
        txtMonto.setName("txtMonto"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField2.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtMotorizado.setBackground(resourceMap.getColor("txtMonto.background")); // NOI18N
        txtMotorizado.setFont(resourceMap.getFont("txtMotorizado.font")); // NOI18N
        txtMotorizado.setForeground(resourceMap.getColor("txtMotorizado.foreground")); // NOI18N
        txtMotorizado.setText(resourceMap.getString("txtMotorizado.text")); // NOI18N
        txtMotorizado.setName("txtMotorizado"); // NOI18N
        txtMotorizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMotorizadoMouseClicked(evt);
            }
        });
        txtMotorizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotorizadoActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtEfectivo.setBackground(resourceMap.getColor("txtMonto.background")); // NOI18N
        txtEfectivo.setFont(resourceMap.getFont("txtMonto.font")); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText(resourceMap.getString("txtEfectivo.text")); // NOI18N
        txtEfectivo.setName("txtEfectivo"); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setName("jPanel6"); // NOI18N

        buttonAction1.setIcon(resourceMap.getIcon("buttonAction1.icon")); // NOI18N
        buttonAction1.setText(resourceMap.getString("buttonAction1.text")); // NOI18N
        buttonAction1.setFont(resourceMap.getFont("buttonAction1.font")); // NOI18N
        buttonAction1.setName("buttonAction1"); // NOI18N
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        buttonAction2.setText(resourceMap.getString("buttonAction2.text")); // NOI18N
        buttonAction2.setEnabled(false);
        buttonAction2.setFont(resourceMap.getFont("buttonAction1.font")); // NOI18N
        buttonAction2.setName("buttonAction2"); // NOI18N
        buttonAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction2ActionPerformed(evt);
            }
        });

        buttonAction3.setText(resourceMap.getString("buttonAction3.text")); // NOI18N
        buttonAction3.setEnabled(false);
        buttonAction3.setFont(resourceMap.getFont("buttonAction1.font")); // NOI18N
        buttonAction3.setName("buttonAction3"); // NOI18N
        buttonAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMotorizado, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEfectivo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(229, 229, 229)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMotorizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setName("jPanel9"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtsalida.setBackground(resourceMap.getColor("txtsalida.background")); // NOI18N
        txtsalida.setEditable(false);
        txtsalida.setFont(resourceMap.getFont("txtsalida.font")); // NOI18N
        txtsalida.setText(resourceMap.getString("txtsalida.text")); // NOI18N
        txtsalida.setName("txtsalida"); // NOI18N

        txtHoraSalida.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        txtHoraSalida.setEditable(false);
        txtHoraSalida.setFont(resourceMap.getFont("txtsalida.font")); // NOI18N
        txtHoraSalida.setName("txtHoraSalida"); // NOI18N

        jTextField9.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField9.setEditable(false);
        jTextField9.setFont(resourceMap.getFont("txtsalida.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextArea1.foreground")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setForeground(resourceMap.getColor("jTextArea1.foreground")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane3.setViewportView(jTextArea1);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INTERNO", "SERIE", "NUMERO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionBackground(resourceMap.getColor("jTable3.selectionBackground")); // NOI18N
        jTable3.setSelectionForeground(resourceMap.getColor("jTable3.selectionForeground")); // NOI18N
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable3.columnModel.title0")); // NOI18N
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable3.columnModel.title1")); // NOI18N
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable3.columnModel.title2")); // NOI18N
        jTable3.getColumnModel().getColumn(3).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable3.columnModel.title3")); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtefectivo.setBackground(resourceMap.getColor("txtefectivo.background")); // NOI18N
        txtefectivo.setFont(resourceMap.getFont("txtefectivo.font")); // NOI18N
        txtefectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtefectivo.setName("txtefectivo"); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoraSalida)
                                    .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtefectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(txtefectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonAction6.setText(resourceMap.getString("buttonAction6.text")); // NOI18N
        buttonAction6.setEnabled(false);
        buttonAction6.setFont(resourceMap.getFont("buttonAction6.font")); // NOI18N
        buttonAction6.setName("buttonAction6"); // NOI18N
        buttonAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction6ActionPerformed(evt);
            }
        });

        buttonAction7.setText(resourceMap.getString("buttonAction7.text")); // NOI18N
        buttonAction7.setEnabled(false);
        buttonAction7.setFont(resourceMap.getFont("buttonAction7.font")); // NOI18N
        buttonAction7.setName("buttonAction7"); // NOI18N
        buttonAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAction7, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(buttonAction6, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addGap(158, 158, 158))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonAction6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAction7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        BuscarVenta();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_buttonAction1ActionPerformed

    private void buttonAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction4ActionPerformed
        AgregarVenta();
    }//GEN-LAST:event_buttonAction4ActionPerformed

    private void txtMotorizadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMotorizadoMouseClicked
    }//GEN-LAST:event_txtMotorizadoMouseClicked

    private void txtMotorizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotorizadoActionPerformed
        txtMonto.requestFocus();
    }//GEN-LAST:event_txtMotorizadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EliminaItemVenta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction3ActionPerformed
        Guarda_VentaDelivery();
    }//GEN-LAST:event_buttonAction3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            AgregarVenta();
        }
        jTabbedPane2.setSelectedIndex(0);
        jTabbedPane2.setEnabledAt(1, false);
}//GEN-LAST:event_jTable1MouseClicked

    private void buttonAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction2ActionPerformed
        CancelarSalida();
    }//GEN-LAST:event_buttonAction2ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        jTabbedPane2.setEnabledAt(1, true);
        jTabbedPane2.setSelectedIndex(1);
        VisualizaVenta();
        VisualizaDetlleSalida();
        buttonAction6.setEnabled(false);
        buttonAction7.setEnabled(false);
        Color micolor = new Color(236, 233, 216);
        jTextField9.setBackground(micolor);
        jTextArea1.setBackground(micolor);
        jTextField9.setEditable(false);
        jTextArea1.setEditable(false);
        if (buttonAction6.isEnabled()) {
            buttonAction5.setEnabled(false);
        } else {
            buttonAction5.setEnabled(true);
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int currentTab = jTabbedPane1.getSelectedIndex();
        switch (currentTab) {
            case 0:
                buttonAction4.setEnabled(true);
                buttonAction5.setEnabled(false);
                buttonAction6.setEnabled(false);
                buttonAction7.setEnabled(false);
                txtsalida.setText("");
                txtefectivo.setText("");
                txtHoraSalida.setText("");
                jTextField9.setText("");
                jTextArea1.setText("");
                jTextField9.setEditable(false);
                jTextArea1.setEditable(false);
                Color micolor = new Color(236, 233, 216);
                jTextField9.setBackground(micolor);
                jTextArea1.setBackground(micolor);
                jTable4.setEnabled(true);
                if (jTabbedPane2.getTabCount() > 0) {
                    jTabbedPane2.setSelectedIndex(0);
                    jTabbedPane2.setEnabledAt(1, false);
                    LimpiatTabla_4();
                }
                break;
            case 1:
                if (jTable4.getRowCount() > 0) {
                    buttonAction5.setEnabled(true);
                } else {
                    buttonAction5.setEnabled(false);
                }

                buttonAction4.setEnabled(false);
                jTabbedPane2.setEnabledAt(0, false);
                jTabbedPane2.setEnabledAt(1, true);
                jTabbedPane2.setSelectedIndex(1);
                buttonAction6.setEnabled(false);
                buttonAction7.setEnabled(false);
                break;

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void buttonAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction5ActionPerformed

        VisualizaVenta();
        VisualizaDetlleSalida();
    }//GEN-LAST:event_buttonAction5ActionPerformed
    private void buttonAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction6ActionPerformed
        Guardar_EntradaDelivery();
    }//GEN-LAST:event_buttonAction6ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        jTextArea1.requestFocus();

    }//GEN-LAST:event_jTextField9ActionPerformed

    private void buttonAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction7ActionPerformed
        buttonAction5.setEnabled(true);
        buttonAction6.setEnabled(false);
        buttonAction7.setEnabled(false);
        txtsalida.setText("");
        txtefectivo.setText("");
        txtHoraSalida.setText("");
        jTextField9.setText("");
        jTextArea1.setText("");
        jTextField9.setEditable(false);
        jTextArea1.setEditable(false);
        LimpiatTabla_4();
        Color micolor = new Color(236, 233, 216);
        jTextField9.setBackground(micolor);
        jTextArea1.setBackground(micolor);
        jTable4.setEnabled(true);
    }//GEN-LAST:event_buttonAction7ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction buttonAction1;
    private org.edisoncor.gui.button.ButtonAction buttonAction2;
    private org.edisoncor.gui.button.ButtonAction buttonAction3;
    private org.edisoncor.gui.button.ButtonAction buttonAction4;
    private org.edisoncor.gui.button.ButtonAction buttonAction5;
    private org.edisoncor.gui.button.ButtonAction buttonAction6;
    private org.edisoncor.gui.button.ButtonAction buttonAction7;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMotorizado;
    private javax.swing.JTextField txtefectivo;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables
}
