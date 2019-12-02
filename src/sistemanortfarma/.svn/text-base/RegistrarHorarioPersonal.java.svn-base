/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarHorarioPersonal.java
 *
 * Created on 16/08/2013, 05:45:03 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaEstadoPersonal;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaPersonalHorario;
import CapaLogica.LogicaRol;
import entidad.Estado_Personal;
import entidad.Horarios;
import entidad.Horarios_Detalle;
import entidad.Personal;
import entidad.Roles;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Miguel Gomez S.
 */
public class RegistrarHorarioPersonal extends javax.swing.JDialog {

    static List<Personal> listPersonal;
    List<Roles> listaRoles;
    List<Estado_Personal> listaEstado;
    LogicaPersonal logpersonal = new LogicaPersonal();
    LogicaPersonalHorario logpersonalHorario = new LogicaPersonalHorario();
    LogicaEstadoPersonal logestado = new LogicaEstadoPersonal();
    LogicaRol logroles = new LogicaRol();
    Horarios_Detalle horario;

    /** Creates new form RegistrarHorarioPersonal */
    public RegistrarHorarioPersonal(java.awt.Frame parent, boolean modal, String mes, Horarios_Detalle detalle) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        horario = detalle;
        ModificaFormato();
        CargarDatos();
        jLabel2.setText(mes);
    }

    private void CargarDatos() {
        try {
            txtBotica.setText(horario.getBotica().getId_Botica());
            listPersonal = logpersonal.Lista_Datos_Personal();
            listaRoles = logroles.ListarRolesPersonal();
            listaEstado = logestado.ListaEstado();
            jComboBox1.addItem("-----Selecciones Cargo ----");
            for (Roles rol : listaRoles) {
                jComboBox1.addItem(rol.getDescripcion());
            }
            jComboBox2.addItem("-----Selecciones Estado ----");
            for (Estado_Personal estado : listaEstado) {
                jComboBox2.addItem(estado.getDescripcion());
            }

            setupAutoComplete(txtpersonal, listPersonal, jComboBox1, jComboBox2, txtDNI, txtContrato);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void ModificaFormato() {
        jSpinner1.setModel(new SpinnerDateModel());
        jSpinner1.setEditor(new JSpinner.DateEditor(jSpinner1, "hh:mm a"));

        jSpinner2.setModel(new SpinnerDateModel());
        jSpinner2.setEditor(new JSpinner.DateEditor(jSpinner2, "hh:mm a"));

        jSpinner3.setModel(new SpinnerDateModel());
        jSpinner3.setEditor(new JSpinner.DateEditor(jSpinner3, "hh:mm a"));

        jSpinner4.setModel(new SpinnerDateModel());
        jSpinner4.setEditor(new JSpinner.DateEditor(jSpinner4, "hh:mm a"));
    }

    private boolean ComparaHoras(String horaInicial, String horaFinal) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date horaIni;
            Date horaFin;
            horaIni = dateFormat.parse(horaInicial);
            horaFin = dateFormat.parse(horaFinal);
            if (horaIni.compareTo(horaFin) < 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }
    }

    private void Guarda_HorarioPlanificado() {
        Horarios_Detalle midetalle;
        Horarios Tmphorario;
        Personal persona;
        Estado_Personal estado;

        if (jComboBox1.getSelectedIndex() > 0) {
            if (jComboBox2.getSelectedIndex() > 0) {
                try {
                    int idrol = listaRoles.get(jComboBox1.getSelectedIndex() - 1).getId_Rol();
                    midetalle = new Horarios_Detalle();
                    Tmphorario = new Horarios();
                    persona = new Personal();
                    estado = new Estado_Personal();
                    int idpersonal = RecuperaIdMotorizado(txtpersonal);
                    if (idpersonal >= 0) {

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String resultado = sdf.format((Date) jSpinner1.getValue());
                        String resultado1 = sdf.format((Date) jSpinner2.getValue());
                        String resultado2 = sdf.format((Date) jSpinner3.getValue());
                        String resultado3 = sdf.format((Date) jSpinner4.getValue());

                        if (ComparaHoras(resultado, resultado1)) {
                            if (ComparaHoras(resultado2, resultado3)) {

                                int p = JOptionPane.showConfirmDialog(null, " Seguro de Guardar este Horario ", "Confirmar",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (p == JOptionPane.YES_OPTION) {

                                    Tmphorario.setId_Horario(horario.getHorario().getId_Horario());
//                                    midetalle.setBotica(txtBotica.getText().trim());
                                    persona.setId_Personal(idpersonal);
                                    persona.setId_Rol(idrol);
                                    midetalle.setPersonal(persona);
                                    midetalle.setHorario(Tmphorario);
                                    int idestado = listaEstado.get(jComboBox2.getSelectedIndex() - 1).getId_Estado();
                                    estado.setId_Estado(idestado);
                                    midetalle.setEstado(estado);

                                    midetalle.setObservacion(jTextArea1.getText().trim().toLowerCase());
                                    midetalle.setRT1_Hora_Inicio(resultado);
                                    midetalle.setRT1_Hora_Final(resultado1);
                                    midetalle.setRT2_Hora_Inicio(resultado2);
                                    midetalle.setRT2_Hora_Final(resultado3);

                                    if (logpersonalHorario.InsertaHorarioPlanificado(midetalle)) {
                                        JOptionPane.showMessageDialog(this, "EL HORARIO DE : \n " + txtpersonal.getText().trim() + "  A SIDO REGISTRADO", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(this, "ERROR AL INSERTAR HORARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 2 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 1 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "PORFAVOR SELECCIONE ESTADO DEL PERSONAL", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "PORFAVOR SELECCIONE CARGO", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static int RecuperaIdMotorizado(JTextField text) throws Exception {
        String nombre = text.getText().trim();
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

    static void setupAutoComplete(final JTextField txtInput, final List<Personal> items, final JComboBox combo, final JComboBox combo1, final JTextField dni, final JTextField contrato) {
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
                combo.setSelectedIndex(0);
                combo1.setSelectedIndex(0);

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            if (txtInput.getText().trim().compareTo("") != 0) {
                                if (cbInput.getSelectedItem() != null) {
                                    txtInput.setText(cbInput.getSelectedItem().toString());
                                    cbInput.setPopupVisible(false);
                                    int idpersonal = RecuperaIdMotorizado(txtInput);
                                    if (idpersonal >= 0) {
                                        combo.setEnabled(true);
                                        combo1.setEnabled(true);
                                        for (Personal persona : listPersonal) {
                                            if (persona.getId_Personal() == idpersonal) {
                                                dni.setText(persona.getDNI());
                                                contrato.setText(persona.getvId_Modelo_Contrato());
                                                break;
                                            }
                                        }
                                    }
                                }

                            }
                        } catch (Exception ex) {
                            dni.setText("");
                            contrato.setText("");
                            txtInput.setText("");
                            Logger.getLogger(RegistrarHorarioPersonal.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
                            model.addElement(item.getId_Personal() + " . " + item.getApellido() + " " + item.getNombre());
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpersonal = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtContrato = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jToolBar1 = new javax.swing.JToolBar();
        registrar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        modificar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        modificar1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel7 = new javax.swing.JLabel();
        txtBotica = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(RegistrarHorarioPersonal.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtpersonal.setText(resourceMap.getString("txtpersonal.text")); // NOI18N
        txtpersonal.setEnabled(false);
        txtpersonal.setName("txtpersonal"); // NOI18N

        txtDNI.setBackground(resourceMap.getColor("txtDNI.background")); // NOI18N
        txtDNI.setEditable(false);
        txtDNI.setText(resourceMap.getString("txtDNI.text")); // NOI18N
        txtDNI.setName("txtDNI"); // NOI18N

        txtContrato.setEditable(false);
        txtContrato.setText(resourceMap.getString("txtContrato.text")); // NOI18N
        txtContrato.setName("txtContrato"); // NOI18N

        jComboBox1.setEnabled(false);
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel2.setName("jPanel2"); // NOI18N

        jSpinner1.setFont(resourceMap.getFont("jSpinner2.font")); // NOI18N
        jSpinner1.setEnabled(false);
        jSpinner1.setName("jSpinner1"); // NOI18N

        jSpinner2.setFont(resourceMap.getFont("jSpinner2.font")); // NOI18N
        jSpinner2.setEnabled(false);
        jSpinner2.setName("jSpinner2"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jSpinner3.setFont(resourceMap.getFont("jSpinner2.font")); // NOI18N
        jSpinner3.setEnabled(false);
        jSpinner3.setName("jSpinner3"); // NOI18N

        jSpinner4.setFont(resourceMap.getFont("jSpinner2.font")); // NOI18N
        jSpinner4.setEnabled(false);
        jSpinner4.setName("jSpinner4"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        registrar.setBackground(resourceMap.getColor("registrar.background")); // NOI18N
        registrar.setIcon(resourceMap.getIcon("registrar.icon")); // NOI18N
        registrar.setToolTipText(resourceMap.getString("registrar.toolTipText")); // NOI18N
        registrar.setName("registrar"); // NOI18N
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jToolBar1.add(registrar);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

        modificar.setBackground(resourceMap.getColor("modificar.background")); // NOI18N
        modificar.setForeground(resourceMap.getColor("modificar.foreground")); // NOI18N
        modificar.setIcon(resourceMap.getIcon("modificar.icon")); // NOI18N
        modificar.setToolTipText(resourceMap.getString("modificar.toolTipText")); // NOI18N
        modificar.setEnabled(false);
        modificar.setName("modificar"); // NOI18N
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jToolBar1.add(modificar);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        modificar1.setBackground(resourceMap.getColor("modificar1.background")); // NOI18N
        modificar1.setForeground(resourceMap.getColor("modificar1.foreground")); // NOI18N
        modificar1.setIcon(resourceMap.getIcon("modificar1.icon")); // NOI18N
        modificar1.setToolTipText(resourceMap.getString("modificar1.toolTipText")); // NOI18N
        modificar1.setName("modificar1"); // NOI18N
        modificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar1ActionPerformed(evt);
            }
        });
        jToolBar1.add(modificar1);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtBotica.setBackground(resourceMap.getColor("txtBotica.background")); // NOI18N
        txtBotica.setText(resourceMap.getString("txtBotica.text")); // NOI18N
        txtBotica.setEnabled(false);
        txtBotica.setName("txtBotica"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jComboBox2.setEnabled(false);
        jComboBox2.setName("jComboBox2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtContrato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBotica, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtBotica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        txtpersonal.setEnabled(true);
        txtpersonal.requestFocus();
}//GEN-LAST:event_registrarActionPerformed

    private void modificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar1ActionPerformed
        dispose();
}//GEN-LAST:event_modificar1ActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        Guarda_HorarioPlanificado();
}//GEN-LAST:event_modificarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        if (evt.getStateChange() == 2) {
            Color micolor;
            if (jComboBox1.getSelectedIndex() > 0) {
                micolor = new Color(255, 255, 255);
                jTextArea1.setEnabled(true);
                jTextArea1.setBackground(micolor);
                jTextArea1.setEnabled(true);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(true);
                jSpinner3.setEnabled(true);
                jSpinner4.setEnabled(true);
                modificar.setEnabled(true);
            } else {
                micolor = new Color(236, 233, 216);
                jTextArea1.setEnabled(false);
                jTextArea1.setBackground(micolor);
                jTextArea1.setEnabled(false);
                jSpinner1.setEnabled(false);
                jSpinner2.setEnabled(false);
                jSpinner3.setEnabled(false);
                jSpinner4.setEnabled(false);
                modificar.setEnabled(false);

            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton modificar;
    private javax.swing.JButton modificar1;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField txtBotica;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtpersonal;
    // End of variables declaration//GEN-END:variables
}
