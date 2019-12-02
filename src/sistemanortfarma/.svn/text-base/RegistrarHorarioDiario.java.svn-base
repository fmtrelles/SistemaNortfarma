/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrHorarioDiario.java
 *
 * Created on 20/08/2013, 10:26:33 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonalHorario;
import entidad.Horarios_Detalle;
import entidad.Horarios_Detalle_Diario;
import entidad.Justificacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class RegistrarHorarioDiario extends javax.swing.JDialog {

    LogicaPersonalHorario loghorario = new LogicaPersonalHorario();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    List<Horarios_Detalle_Diario> listHorario;
    List<Justificacion> listaJustificacion;
    String[] columnas = {"IDHORARIODIARIO", "Fecha", "Personal", "DNI", "H1-INGRESO", "H1-SALIDA", "H2-INGRESO", "H2-SALIDA", "T1-INGRESO", "T1-SALIDA", "T2-INGRESO", "T2-SALIDA", "Opciones"};
    TableColumn colu;
    DefaultTableModel modelo = null;
    JFrame frame;
    TableColumn col, col_1, col_2, col_3;
    int mimes;
    Horarios_Detalle horarioDetalle;

    /** Creates new form RegistrHorarioDiario */
    public RegistrarHorarioDiario(java.awt.Frame parent, boolean modal, String mes, Horarios_Detalle detalle) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        txtmes.setText(mes);
        jLabel1.setText("");
        horarioDetalle = detalle;
        txtpersonal.setText(horarioDetalle.getPersonal().getApellido() + " " + horarioDetalle.getPersonal().getNombre());
        BuscarHorariosDiarios();
        NuevoHorario(false);
        CargarDatos();
    }

    private void AparienciaTabla() {
        try {
            JTableHeader cabecera1 = new JTableHeader(tabla.getColumnModel());
            cabecera1.setReorderingAllowed(false);
            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            tabla.getColumnModel().getColumn(4).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(5).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(6).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(7).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(8).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(9).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(10).setCellRenderer(tcenter);
            tabla.getColumnModel().getColumn(11).setCellRenderer(tcenter);

            colu = tabla.getColumnModel().getColumn(0);
            colu.setPreferredWidth(0);
            colu.setMinWidth(0);
            colu.setMaxWidth(0);

            colu = tabla.getColumnModel().getColumn(1);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(2);
            colu.setPreferredWidth(0);
            colu.setMinWidth(0);
            colu.setMaxWidth(0);

            colu = tabla.getColumnModel().getColumn(3);
            colu.setPreferredWidth(0);
            colu.setMinWidth(0);
            colu.setMaxWidth(0);

            colu = tabla.getColumnModel().getColumn(4);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(5);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(6);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(7);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(8);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(9);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(10);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(11);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

            colu = tabla.getColumnModel().getColumn(12);
            colu.setPreferredWidth(140);
            colu.setMinWidth(140);
            colu.setMaxWidth(140);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void NuevoHorario(boolean valor) {
        jSpinner1.setEnabled(valor);
        jSpinner2.setEnabled(valor);
        jSpinner3.setEnabled(valor);
        jSpinner4.setEnabled(valor);
        jButton1.setEnabled(!valor);
        jComboBox1.setEnabled(valor);
        jTextArea1.setEnabled(valor);
    }

    private void CargarDatos() {

        listaJustificacion = loghorario.ListaJustificacion();
        jComboBox1.addItem("-------- Seleccione Estado -------------");
        for (Justificacion jus : listaJustificacion) {
            jComboBox1.addItem(jus.getDescripcion());
        }

        jSpinner1.setModel(new SpinnerDateModel());
        jSpinner1.setEditor(new JSpinner.DateEditor(jSpinner1, "hh:mm a"));

        jSpinner2.setModel(new SpinnerDateModel());
        jSpinner2.setEditor(new JSpinner.DateEditor(jSpinner2, "hh:mm a"));

        jSpinner3.setModel(new SpinnerDateModel());
        jSpinner3.setEditor(new JSpinner.DateEditor(jSpinner3, "hh:mm a"));

        jSpinner4.setModel(new SpinnerDateModel());
        jSpinner4.setEditor(new JSpinner.DateEditor(jSpinner4, "hh:mm a"));

        /* SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
        jSpinner1.setValue(dateFormat.parse(horarioDetalle.getRT1_Hora_Inicio()));
        jSpinner2.setValue(dateFormat.parse(horarioDetalle.getRT1_Hora_Final()));
        jSpinner3.setValue(dateFormat.parse(horarioDetalle.getRT2_Hora_Inicio()));
        jSpinner4.setValue(dateFormat.parse(horarioDetalle.getRT2_Hora_Final()));
        } catch (ParseException e) {
        e.printStackTrace();
        }*/

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

    private void Guardar_HorarioDiario() {
        Horarios_Detalle_Diario horarioDiario = new Horarios_Detalle_Diario();
        Justificacion justificacion = new Justificacion();

        if (jComboBox1.getSelectedIndex() > 0) {
            int idjustif = listaJustificacion.get(jComboBox1.getSelectedIndex() - 1).getId_Justificacion();
            String obs = jTextArea1.getText().trim().toLowerCase();
            justificacion.setId_Justificacion(idjustif);
            horarioDiario.setId_Justificacion(justificacion);
            horarioDiario.setObservacion(obs);

            Date date = jXDatePicker1.getDate();         
            horarioDiario.setFecha(logfecha.ConvierteFecha(date));

            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
            String resultado = sdf1.format((Date) jSpinner1.getValue());
            String resultado1 = sdf1.format((Date) jSpinner2.getValue());
            String resultado2 = sdf1.format((Date) jSpinner3.getValue());
            String resultado3 = sdf1.format((Date) jSpinner4.getValue());

            if (ComparaHoras(resultado, resultado1)) {
                if (ComparaHoras(resultado2, resultado3)) {

                    int p = JOptionPane.showConfirmDialog(null, " Seguro de Guardar este Horario ", "Confirmar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (p == JOptionPane.YES_OPTION) {
                        horarioDiario.setT1_Hora_Inicio(resultado);
                        horarioDiario.setT1_Hora_Final(resultado1);
                        horarioDiario.setT2_Hora_Inicio(resultado2);
                        horarioDiario.setT2_Hora_Final(resultado3);

                        horarioDiario.setId_Horario_Detalle(horarioDetalle);
                        int resul = loghorario.GuardaHorarioDiario(horarioDiario);
                        if (resul == 1) {
                            jTextArea1.setText("");
                            jComboBox1.setSelectedIndex(0);
                            jButton2.setEnabled(false);
                            jButton1.setEnabled(true);
                            BuscarHorariosDiarios();
                            NuevoHorario(false);
                            JOptionPane.showMessageDialog(this, "EL HORARIO DE : \n " + txtpersonal.getText().trim() + "  A SIDO REGISTRADO", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (resul == 0) {
                                JOptionPane.showMessageDialog(this, "LO SENTIMOS YA EXISTE UN HORARIO INGRESADO EN ESTA FECHA \n DEL PERSONAL " + txtpersonal.getText().trim() + "", "ERROR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "ERROR AL INGRESAR SU HORARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 2 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 1 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "PORFAVOR SELECCIONE JUSTIFICACION", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LimpiatTabla() {
        int cant = this.tabla.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    private void BuscarHorariosDiarios() {
        LimpiatTabla();
        listHorario = loghorario.Lista_HorariosDiarios(horarioDetalle.getId_Horario_Detalle());
        if (listHorario.size() > 0) {
            jLabel1.setText("");


            for (Horarios_Detalle_Diario horarioDiario : listHorario) {

                Object[][] datos = {{horarioDiario.getId_Horario_Detalle_Diario(),
                        logfecha.MysqlToJuliano(horarioDiario.getFecha()),
                        horarioDiario.getId_Horario_Detalle().getPersonal().getApellido() + " " + horarioDiario.getId_Horario_Detalle().getPersonal().getNombre(),
                        horarioDiario.getId_Horario_Detalle().getPersonal().getDNI(),
                        horarioDiario.getId_Horario_Detalle().getRT1_Hora_Inicio(),
                        horarioDiario.getId_Horario_Detalle().getRT1_Hora_Final(),
                        horarioDiario.getId_Horario_Detalle().getRT2_Hora_Inicio(),
                        horarioDiario.getId_Horario_Detalle().getRT2_Hora_Final(),
                        horarioDiario.getT1_Hora_Inicio(),
                        horarioDiario.getT1_Hora_Final(),
                        horarioDiario.getT2_Hora_Inicio(),
                        horarioDiario.getT2_Hora_Final()}};


                if (modelo == null) {
                    modelo = new DefaultTableModel(datos, columnas) {

                        @Override
                        public boolean isCellEditable(int fila, int columna) {
                            if (columna == 12) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    };

                    tabla.setModel(modelo);
                    AparienciaTabla();
                } else {
                    modelo.addRow(datos[0]);
                }

                tabla.setRowHeight(36);// el tamaño de las celulas o celdas
                tabla.setAutoCreateRowSorter(true);
                TableColumn column = tabla.getColumnModel().getColumn(12);

                /***************AÑADADO LAS CLASES EDITAR y VISTA PREVIA**************************/
                column.setCellRenderer(new ButtonsRenderer());
                column.setCellEditor(new ButtonsEditor(tabla));


            }

            col = tabla.getColumnModel().getColumn(4);
            col_1 = tabla.getColumnModel().getColumn(5);
            col_2 = tabla.getColumnModel().getColumn(6);
            col_3 = tabla.getColumnModel().getColumn(7);
            col.setCellRenderer(new ColoredTableCellRenderer());
            col_1.setCellRenderer(new ColoredTableCellRenderer());
            col_2.setCellRenderer(new ColoredTableCellRenderer());
            col_3.setCellRenderer(new ColoredTableCellRenderer());


        } else {
            jLabel1.setText("NO SE HAN REGISTRADO ASISTENCIAS EN ESTE MES");
        }

    }

    private void GenerarReporte() {

        if (listHorario.size() > 0) {

            BusyDialog dialog = new BusyDialog("Generando reporte ... ", new Runnable() {

                public void run() {
                    String mes = txtmes.getText().trim().toUpperCase();
                    int idhorario = listHorario.get(0).getId_Horario_Detalle().getId_Horario_Detalle();
                    JasperViewer view = loghorario.Reporte_HorariosDiarios("REPORTE_HORARIOSDIARIOS", idhorario, mes);
                    view.setTitle("REPORTE HORARIOS DEL PERSONAL");
                    view.setVisible(true);
                    view.setSize(870, 700);
                }
            });
            dialog.setResizable(false);
            dialog.setLocation(700, 400);
            dialog.show(true);

        } else {
            JOptionPane.showMessageDialog(this, "ESTE PERSONAL NO CUENTA CON HORARIOS", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class BusyDialog extends JDialog {

        public BusyDialog(String Message, final Runnable r) {
            super();
            this.setModal(true);
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLayout(new BorderLayout());
            this.getContentPane().add(new JLabel(Message));
            this.pack();
            this.addWindowListener(new WindowAdapter() {

                @Override
                public void windowOpened(WindowEvent e) {
                    super.windowOpened(e);
                    // do something
                    doBusy(r);

                }
            });
        }

        private final void doBusy(Runnable r) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            r.run();
            this.setCursor(Cursor.getDefaultCursor());
            this.dispose();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtpersonal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmes = new javax.swing.JTextField();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(RegistrarHorarioDiario.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        txtpersonal.setBackground(resourceMap.getColor("txtpersonal.background")); // NOI18N
        txtpersonal.setFont(resourceMap.getFont("txtpersonal.font")); // NOI18N
        txtpersonal.setEnabled(false);
        txtpersonal.setName("txtpersonal"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtmes.setEditable(false);
        txtmes.setFont(resourceMap.getFont("txtmes.font")); // NOI18N
        txtmes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmes.setText(resourceMap.getString("txtmes.text")); // NOI18N
        txtmes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        txtmes.setName("txtmes"); // NOI18N

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel4.setName("jPanel4"); // NOI18N

        jSpinner1.setFont(resourceMap.getFont("jSpinner1.font")); // NOI18N
        jSpinner1.setEnabled(false);
        jSpinner1.setName("jSpinner1"); // NOI18N

        jSpinner2.setFont(resourceMap.getFont("jSpinner2.font")); // NOI18N
        jSpinner2.setEnabled(false);
        jSpinner2.setName("jSpinner2"); // NOI18N

        jSpinner3.setFont(resourceMap.getFont("jSpinner3.font")); // NOI18N
        jSpinner3.setEnabled(false);
        jSpinner3.setName("jSpinner3"); // NOI18N

        jSpinner4.setFont(resourceMap.getFont("jSpinner4.font")); // NOI18N
        jSpinner4.setEnabled(false);
        jSpinner4.setName("jSpinner4"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setEnabled(false);
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane2.setViewportView(jTextArea1);

        jLabel14.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel14.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(178, 178, 178)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(72, 72, 72)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setName("jLabel13"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmes, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(543, 543, 543)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(152, 152, 152))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabla.setBackground(resourceMap.getColor("tabla.background")); // NOI18N
        tabla.setForeground(resourceMap.getColor("tabla.foreground")); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setFillsViewportHeight(true);
        tabla.setName("tabla"); // NOI18N
        tabla.setSelectionBackground(resourceMap.getColor("tabla.selectionBackground")); // NOI18N
        tabla.setSelectionForeground(resourceMap.getColor("tabla.selectionForeground")); // NOI18N
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NuevoHorario(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Guardar_HorarioDiario();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (evt.getStateChange() == 2) {
            Color micolor;
            if (jComboBox1.getSelectedIndex() > 0) {
                jTextArea1.requestFocus();
                micolor = new Color(255, 255, 255);
                jTextArea1.setEnabled(true);
                jTextArea1.setBackground(micolor);
                jTextArea1.setEnabled(true);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(true);
                jSpinner3.setEnabled(true);
                jSpinner4.setEnabled(true);
                jButton2.setEnabled(true);
            } else {
                micolor = new Color(236, 233, 216);
                jTextArea1.setEnabled(false);
                jTextArea1.setBackground(micolor);
                jTextArea1.setEnabled(false);
                jSpinner1.setEnabled(false);
                jSpinner2.setEnabled(false);
                jSpinner3.setEnabled(false);
                jSpinner4.setEnabled(false);
                jButton2.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        GenerarReporte();
    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextArea jTextArea1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField txtmes;
    private javax.swing.JTextField txtpersonal;
    // End of variables declaration//GEN-END:variables

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            Color p = new Color(241, 241, 170);
            Color fore = new Color(0, 0, 0);

            if (column == 4) {
                setBackground(p);
                setForeground(fore);
                super.getTableCellRendererComponent(table, value, false, false, 2, column);
            } else {
                if (column == 5) {
                    setBackground(p);
                    setForeground(fore);
                    super.getTableCellRendererComponent(table, value, false, false, 2, column);
                } else {
                    if (column == 6) {
                        setBackground(p);
                        setForeground(fore);
                        super.getTableCellRendererComponent(table, value, false, false, 2, column);
                    } else {
                        if (column == 7) {
                            setBackground(p);
                            setForeground(fore);
                            super.getTableCellRendererComponent(table, value, false, false, 2, column);
                        }
                    }
                }
            }

            return this;
        }
    }

    class ButtonsPanel extends JPanel {
        //   public final java.util.List<JButton> buttons = java.util.Arrays.asList(new JButton("Ver Detalle"), new JButton("Modificar"));

        public final java.util.List<JButton> buttons = java.util.Arrays.asList(new JButton("Eliminar"));

        public ButtonsPanel() {
            super();
            setOpaque(true);
            for (JButton b : buttons) {
                b.setFocusable(false);
                b.setRolloverEnabled(false);
                add(b);
            }
        }
    }

    /********************************************/
    class ButtonsRenderer extends ButtonsPanel implements TableCellRenderer {

        public ButtonsRenderer() {
            super();
            setName("Table.cellRenderer");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    /*******************************************/
    class ButtonsEditor extends ButtonsPanel implements TableCellEditor {

        public ButtonsEditor(final JTable table) {
            super();
            MouseListener ml = new MouseAdapter() {

                public void mousePressed(MouseEvent e) {
                    ButtonModel m = ((JButton) e.getSource()).getModel();
                    if (m.isPressed() && table.isRowSelected(table.getEditingRow()) && e.isControlDown()) {
                        setBackground(table.getBackground());
                    }
                }
            };
            //   buttons.get(0).addMouseListener(ml);
            //  buttons.get(0).setIcon(new ImageIcon(getClass().getResource("/iconos/vista-preliminar-del-documento-icono-6420-96.png")));

            buttons.get(0).addMouseListener(ml);
            // buttons.get(1).setIcon(new ImageIcon(getClass().getResource("/iconos/icon_edit.png")));
            //<----

            /*  buttons.get(0).addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            JOptionPane.showMessageDialog(table, "Vista Previa");
            }
            });*/

            buttons.get(0).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    //  Object o = table.getModel().getValueAt(row, 0);
                    fireEditingStopped();
                    if (listHorario.size() > 0) {
                        if (listHorario.get(row).getDesactivado() == 1) {
                            String fecha = table.getValueAt(row, 1).toString();
                            int p = JOptionPane.showConfirmDialog(null, " Seguro de Eliminar este Horario \n Personal : " + txtpersonal.getText().trim() + " \n  Fecha : " + fecha + " ", "Confirmar",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (p == JOptionPane.YES_OPTION) {
                                if (loghorario.EliminaHorarioDetalleDiario(Integer.parseInt(tabla.getValueAt(row, 0).toString()))) {
                                    BuscarHorariosDiarios();
                                    JOptionPane.showMessageDialog(frame, "HORARIO ANULADO", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "ERROR AL ANULAR SU HORARIO", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "LO SENTIMOS ESTE HORARIO YA SE ENCUENTRA CERRADO", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    fireEditingStopped();
                }
            });


        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.setBackground(table.getSelectionBackground());
            return this;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
        transient protected ChangeEvent changeEvent = null;

        @Override
        public boolean isCellEditable(java.util.EventObject e) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(java.util.EventObject anEvent) {
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

        @Override
        public void cancelCellEditing() {
            fireEditingCanceled();
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
            listenerList.add(CellEditorListener.class, l);
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            listenerList.remove(CellEditorListener.class, l);
        }

        public CellEditorListener[] getCellEditorListeners() {
            return listenerList.getListeners(CellEditorListener.class);
        }

        protected void fireEditingStopped() {

            Object[] listeners = listenerList.getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {

                    if (changeEvent == null) {
                        changeEvent = new ChangeEvent(this);
                    }
                    ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
                }
            }
        }

        protected void fireEditingCanceled() {
            //  devuelve una matriz no nula
            Object[] listeners = listenerList.getListenerList();
            // añade evnetos y la notificación
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {
                    // crea el evento
                    if (changeEvent == null) {
                        changeEvent = new ChangeEvent(this);
                    }
                    ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
                }
            }
        }
    }
}
