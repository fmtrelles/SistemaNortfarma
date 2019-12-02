/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModificarHorario.java
 *
 * Created on 16/08/2013, 09:46:01 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaPersonalHorario;
import entidad.Horarios_Detalle;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class ModificarHorario extends javax.swing.JDialog {

    LogicaPersonalHorario logHorarios = new LogicaPersonalHorario();
    List<Horarios_Detalle> listHorariosPlanif;
    int mifila;
    private static int Modificado;

    /** Creates new form ModificarHorario */
    public ModificarHorario(java.awt.Frame parent, boolean modal, List<Horarios_Detalle> lista, int row) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        listHorariosPlanif = lista;
        System.out.println("la fila es: "+mifila);
        mifila = row;
        CargarDatos();
        HabilitaHoras(false);
        setModificado(0);

    }

    public static int getModificado() {
        return Modificado;
    }

    public static void setModificado(int Modificado) {
        ModificarHorario.Modificado = Modificado;
    }

    private void CargarDatos() {

        jSpinner1.setModel(new SpinnerDateModel());
        jSpinner1.setEditor(new JSpinner.DateEditor(jSpinner1, "hh:mm a"));

        jSpinner2.setModel(new SpinnerDateModel());
        jSpinner2.setEditor(new JSpinner.DateEditor(jSpinner2, "hh:mm a"));

        jSpinner3.setModel(new SpinnerDateModel());
        jSpinner3.setEditor(new JSpinner.DateEditor(jSpinner3, "hh:mm a"));

        jSpinner4.setModel(new SpinnerDateModel());
        jSpinner4.setEditor(new JSpinner.DateEditor(jSpinner4, "hh:mm a"));

        txtmibotica.setText(listHorariosPlanif.get(mifila).getBotica().getId_Botica());
        txtpersonal.setText(listHorariosPlanif.get(mifila).getPersonal().getApellido() + " " + listHorariosPlanif.get(mifila).getPersonal().getNombre());
        txtdni.setText(listHorariosPlanif.get(mifila).getPersonal().getDNI());
        TXTPT1_Hora_Inicio.setText(listHorariosPlanif.get(mifila).getPT1_Hora_Inicio());
        TXTPT1_Hora_Final.setText(listHorariosPlanif.get(mifila).getPT1_Hora_Final());
        txtPT2_Hora_Inicio.setText(listHorariosPlanif.get(mifila).getPT2_Hora_Inicio());
        txtPT2_Hora_Final.setText(listHorariosPlanif.get(mifila).getPT2_Hora_Final());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        try {
            jSpinner1.setValue(dateFormat.parse(listHorariosPlanif.get(mifila).getRT1_Hora_Inicio()));
            jSpinner2.setValue(dateFormat.parse(listHorariosPlanif.get(mifila).getRT1_Hora_Final()));
            jSpinner3.setValue(dateFormat.parse(listHorariosPlanif.get(mifila).getRT2_Hora_Inicio()));
            jSpinner4.setValue(dateFormat.parse(listHorariosPlanif.get(mifila).getRT2_Hora_Final()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void HabilitaHoras(boolean valor) {
        jSpinner1.setEnabled(valor);
        jSpinner2.setEnabled(valor);
        jSpinner3.setEnabled(valor);
        jSpinner4.setEnabled(valor);
        Color micolor;
        if (valor) {
            micolor = new Color(255, 255, 255);
            modificar.setEnabled(true);
        } else {
            micolor = new Color(236, 233, 216);
            modificar.setEnabled(false);
        }
        txtobs.setEnabled(valor);
        txtobs.setBackground(micolor);
        txtobs.setEnabled(valor);

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

    private void ModificarHorario() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String resultado = sdf.format((Date) jSpinner1.getValue());
        String resultado1 = sdf.format((Date) jSpinner2.getValue());
        String resultado2 = sdf.format((Date) jSpinner3.getValue());
        String resultado3 = sdf.format((Date) jSpinner4.getValue());

        if (ComparaHoras(resultado, resultado1)) {
            if (ComparaHoras(resultado2, resultado3)) {
                int p = JOptionPane.showConfirmDialog(null, " Seguro de Modficar este Horario ", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (p == JOptionPane.YES_OPTION) {
                    Horarios_Detalle detalle = new Horarios_Detalle();
                    detalle.setBotica(listHorariosPlanif.get(mifila).getBotica());
                    detalle.setId_Horario_Detalle(listHorariosPlanif.get(mifila).getId_Horario_Detalle());
                    detalle.setObservacion(txtobs.getText().trim().toLowerCase());

                    detalle.setRT1_Hora_Inicio(resultado);
                    detalle.setRT1_Hora_Final(resultado1);
                    detalle.setRT2_Hora_Inicio(resultado2);
                    detalle.setRT2_Hora_Final(resultado3);

                    if (logHorarios.ModificaHorario(detalle)) {
                        JOptionPane.showMessageDialog(this, txtpersonal.getText() + "  \n  \n SU HORARIO PLANIFICADO A SIDO MODIFICADO", "OK", JOptionPane.INFORMATION_MESSAGE);
                        setModificado(1);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS HUBO UN ERROR AL MODIFICAR SU HORARIO ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 2 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "INGRESE HORAS CORRECTAS DEL TURNO 1 : \n LA HORA DE INGRESO ES MAYOR QUE LA HORA DE SALIDA ", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        txtmibotica = new javax.swing.JTextField();
        txtpersonal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TXTPT1_Hora_Inicio = new javax.swing.JTextField();
        TXTPT1_Hora_Final = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtPT2_Hora_Inicio = new javax.swing.JTextField();
        txtPT2_Hora_Final = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jToolBar1 = new javax.swing.JToolBar();
        registrar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        modificar1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        modificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ModificarHorario.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setName("jPanel3"); // NOI18N

        txtmibotica.setEditable(false);
        txtmibotica.setFont(resourceMap.getFont("txtdni.font")); // NOI18N
        txtmibotica.setText(resourceMap.getString("txtmibotica.text")); // NOI18N
        txtmibotica.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtmibotica.setName("txtmibotica"); // NOI18N
        txtmibotica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmiboticaActionPerformed(evt);
            }
        });

        txtpersonal.setEditable(false);
        txtpersonal.setFont(resourceMap.getFont("txtdni.font")); // NOI18N
        txtpersonal.setText(resourceMap.getString("txtpersonal.text")); // NOI18N
        txtpersonal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtpersonal.setName("txtpersonal"); // NOI18N

        jLabel2.setBackground(resourceMap.getColor("jLabel2.background")); // NOI18N
        jLabel2.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setBackground(resourceMap.getColor("jLabel3.background")); // NOI18N
        jLabel3.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setBackground(resourceMap.getColor("jLabel4.background")); // NOI18N
        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtdni.setEditable(false);
        txtdni.setFont(resourceMap.getFont("txtdni.font")); // NOI18N
        txtdni.setText(resourceMap.getString("txtdni.text")); // NOI18N
        txtdni.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtdni.setName("txtdni"); // NOI18N
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtobs.setBackground(resourceMap.getColor("txtobs.background")); // NOI18N
        txtobs.setColumns(20);
        txtobs.setRows(5);
        txtobs.setEnabled(false);
        txtobs.setName("txtobs"); // NOI18N
        jScrollPane2.setViewportView(txtobs);

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel1.setName("jPanel1"); // NOI18N

        TXTPT1_Hora_Inicio.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        TXTPT1_Hora_Inicio.setEditable(false);
        TXTPT1_Hora_Inicio.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        TXTPT1_Hora_Inicio.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        TXTPT1_Hora_Inicio.setText(resourceMap.getString("TXTPT1_Hora_Inicio.text")); // NOI18N
        TXTPT1_Hora_Inicio.setName("TXTPT1_Hora_Inicio"); // NOI18N
        jPanel1.add(TXTPT1_Hora_Inicio);

        TXTPT1_Hora_Final.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        TXTPT1_Hora_Final.setEditable(false);
        TXTPT1_Hora_Final.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        TXTPT1_Hora_Final.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        TXTPT1_Hora_Final.setText(resourceMap.getString("TXTPT1_Hora_Final.text")); // NOI18N
        TXTPT1_Hora_Final.setName("TXTPT1_Hora_Final"); // NOI18N
        jPanel1.add(TXTPT1_Hora_Final);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jPanel1.add(jSeparator3);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jPanel1.add(jSeparator2);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jPanel1.add(jSeparator4);

        txtPT2_Hora_Inicio.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        txtPT2_Hora_Inicio.setEditable(false);
        txtPT2_Hora_Inicio.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        txtPT2_Hora_Inicio.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        txtPT2_Hora_Inicio.setText(resourceMap.getString("txtPT2_Hora_Inicio.text")); // NOI18N
        txtPT2_Hora_Inicio.setName("txtPT2_Hora_Inicio"); // NOI18N
        jPanel1.add(txtPT2_Hora_Inicio);

        txtPT2_Hora_Final.setBackground(resourceMap.getColor("txtPT2_Hora_Final.background")); // NOI18N
        txtPT2_Hora_Final.setEditable(false);
        txtPT2_Hora_Final.setFont(resourceMap.getFont("txtPT2_Hora_Final.font")); // NOI18N
        txtPT2_Hora_Final.setForeground(resourceMap.getColor("txtPT2_Hora_Final.foreground")); // NOI18N
        txtPT2_Hora_Final.setText(resourceMap.getString("txtPT2_Hora_Final.text")); // NOI18N
        txtPT2_Hora_Final.setName("txtPT2_Hora_Final"); // NOI18N
        jPanel1.add(txtPT2_Hora_Final);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel2.setName("jPanel2"); // NOI18N

        jSpinner1.setEnabled(false);
        jSpinner1.setName("jSpinner1"); // NOI18N

        jSpinner2.setEnabled(false);
        jSpinner2.setName("jSpinner2"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jSpinner3.setEnabled(false);
        jSpinner3.setName("jSpinner3"); // NOI18N

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
                .addComponent(jLabel7)
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
                    .addComponent(jLabel7)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(6, Short.MAX_VALUE))
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

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmibotica, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161)))
                        .addComponent(jLabel12)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmibotica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtdniActionPerformed

    private void txtmiboticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmiboticaActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtmiboticaActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        HabilitaHoras(true);
        registrar.setEnabled(false);
}//GEN-LAST:event_registrarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        ModificarHorario();
}//GEN-LAST:event_modificarActionPerformed

    private void modificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar1ActionPerformed
        dispose();
    }//GEN-LAST:event_modificar1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTPT1_Hora_Final;
    private javax.swing.JTextField TXTPT1_Hora_Inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton modificar;
    private javax.swing.JButton modificar1;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField txtPT2_Hora_Final;
    private javax.swing.JTextField txtPT2_Hora_Inicio;
    public static javax.swing.JTextField txtdni;
    public static javax.swing.JTextField txtmibotica;
    private javax.swing.JTextArea txtobs;
    public static javax.swing.JTextField txtpersonal;
    // End of variables declaration//GEN-END:variables
}
