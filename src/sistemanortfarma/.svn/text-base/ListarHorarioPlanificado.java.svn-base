/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListarHorarioPlanificado.java
 *
 * Created on 15/08/2013, 09:33:39 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonalHorario;
import entidad.Horarios_Detalle;
import entidad.Personal;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
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
public class ListarHorarioPlanificado extends javax.swing.JInternalFrame {

    AplicacionPersonal aplicacion;
    List<Horarios_Detalle> listHorariosPlanif;
    LogicaPersonalHorario logHorarios = new LogicaPersonalHorario();
    LogicaFechaHora logFecha = new LogicaFechaHora();
    String[] columnas = {"FechaRegistro", "Personal", "DNI", "T1-INGRESO", "T1-SALIDA", "T2-INGRESO", "T2-SALIDA", "H1-INGRESO", "H1-SALIDA", "H2-INGRESO", "H2-SALIDA", "Opciones"};
    TableColumn colu;
    DefaultTableModel modelo = null;
    JFrame frame;
    TableColumn col, col_1, col_2, col_3;

    /** Creates new form ListarHorarioPlanificado */
    public ListarHorarioPlanificado(AplicacionPersonal personal) {
        initComponents();
        aplicacion = personal;
        HabilitaBusqueda(false);
        jButton2.setEnabled(true);
    }

    private void HabilitaBusqueda(boolean valor) {
        jButton2.setEnabled(valor);
        generar_reporte_cerco.setEnabled(valor);
        jMonthChooser1.setEnabled(valor);
        buscador_estado.setEnabled(valor);
        jButton1.setEnabled(!valor);
    }

    private void AparienciaTabla() {
        try {
            JTableHeader cabecera1 = new JTableHeader(tabla.getColumnModel());
            cabecera1.setReorderingAllowed(false);
            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            tabla.getColumnModel().getColumn(3).setCellRenderer(tcenter);
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
            colu.setPreferredWidth(300);
            colu.setMinWidth(300);
            colu.setMaxWidth(300);

            colu = tabla.getColumnModel().getColumn(2);
            colu.setPreferredWidth(70);
            colu.setMinWidth(70);
            colu.setMaxWidth(70);

            colu = tabla.getColumnModel().getColumn(3);
            colu.setPreferredWidth(80);
            colu.setMinWidth(80);
            colu.setMaxWidth(80);

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
            colu.setPreferredWidth(380);
            colu.setMinWidth(380);
            colu.setMaxWidth(380);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Lista_DespuesGuardado() {
        LimpiatTabla();
        listHorariosPlanif.removeAll(listHorariosPlanif);
        ListarHorarios("");
    }

    private boolean ListarHorarios(String pers) {
        int mimes = jMonthChooser1.getMonth();
        listHorariosPlanif = logHorarios.ListaHorarios_Planificados(mimes + 1, pers);

        if (listHorariosPlanif.size() > 0) {

            nombres_apellidos2.setText(listHorariosPlanif.get(0).getHorario().getFecha_Registro());
            txtanio.setText(String.valueOf(listHorariosPlanif.get(0).getHorario().getAnio()));
            int aperturado = listHorariosPlanif.get(0).getHorario().getAperturado();

            if (aperturado == 1) {
                generar_reporte_cerco.setEnabled(true);
                Color micolor = new Color(236, 233, 216);
                txtaperturado.setBackground(micolor);
                Color micolor1 = new Color(0, 0, 0);
                txtaperturado.setForeground(micolor1);
                txtaperturado.setText("HORARIO APERTURADO");
            } else {
                generar_reporte_cerco.setEnabled(false);
                Color micolor = new Color(184, 14, 14);
                txtaperturado.setBackground(micolor);
                Color micolor1 = new Color(255, 255, 51);
                txtaperturado.setForeground(micolor1);
                txtaperturado.setText("HORARIO CERRRADO");
            }

            for (Horarios_Detalle detalle : listHorariosPlanif) {

                Object[][] datos = {{detalle.getHorario().getFecha_Registro().toString(),
                        detalle.getPersonal().getApellido() + " " + detalle.getPersonal().getNombre(),
                        detalle.getPersonal().getDNI(),
                        detalle.getPT1_Hora_Inicio(),
                        detalle.getPT1_Hora_Final(),
                        detalle.getPT2_Hora_Inicio(),
                        detalle.getPT2_Hora_Final(),
                        detalle.getRT1_Hora_Inicio(),
                        detalle.getRT1_Hora_Final(),
                        detalle.getRT2_Hora_Inicio(),
                        detalle.getRT2_Hora_Final()}};

                if (modelo == null) {
                    modelo = new DefaultTableModel(datos, columnas) {

                        @Override
                        public boolean isCellEditable(int fila, int columna) {
                            if (columna == 11) {
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
                TableColumn column = tabla.getColumnModel().getColumn(11);

                /***************AÑADADO LAS CLASES EDITAR y VISTA PREVIA**************************/
                column.setCellRenderer(new ButtonsRenderer());
                column.setCellEditor(new ButtonsEditor(tabla));

            }

            col = tabla.getColumnModel().getColumn(7);
            col_1 = tabla.getColumnModel().getColumn(8);
            col_2 = tabla.getColumnModel().getColumn(9);
            col_3 = tabla.getColumnModel().getColumn(10);
            col.setCellRenderer(new ColoredTableCellRenderer());
            col_1.setCellRenderer(new ColoredTableCellRenderer());
            col_2.setCellRenderer(new ColoredTableCellRenderer());
            col_3.setCellRenderer(new ColoredTableCellRenderer());
            return true;
        } else {
            return false;
        }
    }

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above

            Color p = new Color(241, 241, 170);
            Color fore = new Color(0, 0, 0);

            if (column == 7) {
                setBackground(p);
                setForeground(fore);
                super.getTableCellRendererComponent(table, value, false, false, 2, column);
            } else {
                if (column == 8) {
                    setBackground(p);
                    setForeground(fore);
                    super.getTableCellRendererComponent(table, value, false, false, 2, column);
                } else {
                    if (column == 9) {
                        setBackground(p);
                        setForeground(fore);
                        super.getTableCellRendererComponent(table, value, false, false, 2, column);
                    } else {
                        if (column == 10) {
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

    private void LimpiatTabla() {
        int cant = this.tabla.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public String DevolverMes(int mes) {

        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                throw new java.lang.IllegalArgumentException(
                        "El mes debe estar entre 1 y 12");
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

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jToolBar3 = new javax.swing.JToolBar();
        generar_reporte_cerco = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtanio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nombres_apellidos2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtaperturado = new javax.swing.JTextField();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        buscador_estado = new javax.swing.JButton();
        jContentPane = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtpersonal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ListarHorarioPlanificado.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jSeparator3.setForeground(resourceMap.getColor("jSeparator3.foreground")); // NOI18N
        jSeparator3.setName("jSeparator3"); // NOI18N

        jToolBar3.setBackground(resourceMap.getColor("jToolBar3.background")); // NOI18N
        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);
        jToolBar3.setName("jToolBar3"); // NOI18N

        generar_reporte_cerco.setBackground(resourceMap.getColor("generar_reporte_cerco.background")); // NOI18N
        generar_reporte_cerco.setIcon(resourceMap.getIcon("generar_reporte_cerco.icon")); // NOI18N
        generar_reporte_cerco.setToolTipText(resourceMap.getString("generar_reporte_cerco.toolTipText")); // NOI18N
        generar_reporte_cerco.setFocusable(false);
        generar_reporte_cerco.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generar_reporte_cerco.setName("generar_reporte_cerco"); // NOI18N
        generar_reporte_cerco.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generar_reporte_cerco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generar_reporte_cercoActionPerformed(evt);
            }
        });
        jToolBar3.add(generar_reporte_cerco);

        jButton1.setBackground(resourceMap.getColor("generar_reporte_cerco.background")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton1);

        jButton2.setBackground(resourceMap.getColor("generar_reporte_cerco.background")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton2);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setBackground(resourceMap.getColor("jLabel2.background")); // NOI18N
        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtanio.setEditable(false);
        txtanio.setFont(resourceMap.getFont("txtanio.font")); // NOI18N
        txtanio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtanio.setText(resourceMap.getString("txtanio.text")); // NOI18N
        txtanio.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        txtanio.setName("txtanio"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        nombres_apellidos2.setEditable(false);
        nombres_apellidos2.setFont(resourceMap.getFont("nombres_apellidos2.font")); // NOI18N
        nombres_apellidos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombres_apellidos2.setText(resourceMap.getString("nombres_apellidos2.text")); // NOI18N
        nombres_apellidos2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        nombres_apellidos2.setName("nombres_apellidos2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtaperturado.setEditable(false);
        txtaperturado.setFont(resourceMap.getFont("txtaperturado.font")); // NOI18N
        txtaperturado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtaperturado.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        txtaperturado.setName("txtaperturado"); // NOI18N

        jMonthChooser1.setName("jMonthChooser1"); // NOI18N

        buscador_estado.setBackground(resourceMap.getColor("buscador_estado.background")); // NOI18N
        buscador_estado.setFont(resourceMap.getFont("buscador_estado.font")); // NOI18N
        buscador_estado.setIcon(resourceMap.getIcon("buscador_estado.icon")); // NOI18N
        buscador_estado.setToolTipText(resourceMap.getString("buscador_estado.toolTipText")); // NOI18N
        buscador_estado.setName("buscador_estado"); // NOI18N
        buscador_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscador_estadoActionPerformed(evt);
            }
        });

        jContentPane.setBackground(resourceMap.getColor("jContentPane.background")); // NOI18N
        jContentPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), resourceMap.getString("jContentPane.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jContentPane.border.titleColor"))); // NOI18N
        jContentPane.setName("jContentPane"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtpersonal.setText(resourceMap.getString("txtpersonal.text")); // NOI18N
        txtpersonal.setEnabled(false);
        txtpersonal.setName("txtpersonal"); // NOI18N
        txtpersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpersonalActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabla.setBackground(resourceMap.getColor("tabla.background")); // NOI18N
        tabla.setFont(resourceMap.getFont("tabla.font")); // NOI18N
        tabla.setForeground(resourceMap.getColor("tabla.foreground")); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla.setFillsViewportHeight(true);
        tabla.setName("tabla"); // NOI18N
        tabla.setSelectionBackground(resourceMap.getColor("tabla.selectionBackground")); // NOI18N
        tabla.setSelectionForeground(resourceMap.getColor("tabla.selectionForeground")); // NOI18N
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaMouseEntered(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jContentPaneLayout = new javax.swing.GroupLayout(jContentPane);
        jContentPane.setLayout(jContentPaneLayout);
        jContentPaneLayout.setHorizontalGroup(
            jContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jContentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jContentPaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE))
                    .addGroup(jContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jContentPaneLayout.setVerticalGroup(
            jContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jContentPaneLayout.createSequentialGroup()
                .addGroup(jContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(221, 221, 221))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscador_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(595, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(txtanio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombres_apellidos2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtaperturado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jContentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscador_estado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtanio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(nombres_apellidos2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtaperturado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jContentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generar_reporte_cercoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generar_reporte_cercoActionPerformed
        if (listHorariosPlanif.size() > 0) {
            int mimes = jMonthChooser1.getMonth() + 1;
            String mes = DevolverMes(mimes);
            Horarios_Detalle detalle = listHorariosPlanif.get(0);
            RegistrarHorarioPersonal regis = new RegistrarHorarioPersonal(frame, true, mes, detalle);
            regis.show();
        }
}//GEN-LAST:event_generar_reporte_cercoActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " Desea Salir ", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            AplicacionPersonal.marcacdo = false;
            aplicacion.Habilita(true);
            dispose();
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (tabla.getRowCount() == 0) {
            HabilitaBusqueda(true);
            generar_reporte_cerco.setEnabled(false);
        } else {
            int p = JOptionPane.showConfirmDialog(null, " Desea Realizar Nueva Busqueda ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                HabilitaBusqueda(true);
                txtpersonal.setEnabled(false);
                LimpiatTabla();
                generar_reporte_cerco.setEnabled(false);
                txtanio.setText("Año");
                nombres_apellidos2.setText("");
                txtaperturado.setText("");
                txtpersonal.setText("");
                listHorariosPlanif.removeAll(listHorariosPlanif);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buscador_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscador_estadoActionPerformed
        String pers = txtpersonal.getText().trim();
        if (ListarHorarios(pers)) {
            buscador_estado.setEnabled(false);
            jMonthChooser1.setEnabled(false);
            jButton1.setEnabled(true);
            txtpersonal.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "NO EXISTEN HORARIOS EN ESTE MES", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_buscador_estadoActionPerformed

    private void txtpersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpersonalActionPerformed
        LimpiatTabla();
        String pers = txtpersonal.getText().trim() + '%';
        ListarHorarios(pers);
    }//GEN-LAST:event_txtpersonalActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
    }//GEN-LAST:event_tablaMouseClicked
    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaMouseEntered
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscador_estado;
    private javax.swing.JButton generar_reporte_cerco;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jContentPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar jToolBar3;
    public static javax.swing.JTextField nombres_apellidos2;
    private javax.swing.JTable tabla;
    public static javax.swing.JTextField txtanio;
    public static javax.swing.JTextField txtaperturado;
    private javax.swing.JTextField txtpersonal;
    // End of variables declaration//GEN-END:variables

    class ButtonsPanel extends JPanel {
        //   public final java.util.List<JButton> buttons = java.util.Arrays.asList(new JButton("Ver Detalle"), new JButton("Modificar"));

        public final java.util.List<JButton> buttons = java.util.Arrays.asList(new JButton("Modificar"), new JButton("Registrar Asistencia"), new JButton("Reporte Asistencias"));

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
            buttons.get(1).addMouseListener(ml);
            buttons.get(2).addMouseListener(ml);
            buttons.get(2).setIcon(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/1343092374_calendar-search-result.png")));
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
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    //  Object o = table.getModel().getValueAt(row, 0);
                    fireEditingStopped();
                    if (listHorariosPlanif.get(tabla.getSelectedRow()).getDesactivado() == 1) {
                        ModificarHorario formModifica = new ModificarHorario(frame, true, listHorariosPlanif, row);
                        formModifica.show();
                        if (formModifica.getModificado() == 1) {
                            LimpiatTabla();
                            ListarHorarios("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "LO SENTIMOS ESTE HORARIO YA SE ENCUENTRA CERRADO", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            buttons.get(1).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    //  Object o = table.getModel().getValueAt(row, 0);
                    fireEditingStopped();
                    if (listHorariosPlanif.get(tabla.getSelectedRow()).getDesactivado() == 1) {
                        int mimes = jMonthChooser1.getMonth() + 1;
                        String mes = DevolverMes(mimes);
                        Horarios_Detalle horarioDetalle = listHorariosPlanif.get(tabla.getSelectedRow());
                        RegistrarHorarioDiario formModifica = new RegistrarHorarioDiario(frame, true, mes, horarioDetalle);
                        formModifica.show();
                    } else {
                        JOptionPane.showMessageDialog(frame, "LO SENTIMOS ESTE HORARIO YA SE ENCUENTRA CERRADO", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            buttons.get(2).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    //  Object o = table.getModel().getValueAt(row, 0);
                    fireEditingStopped();
                    BusyDialog dialog = new BusyDialog("Generando reporte ... ", new Runnable() {

                        public void run() {
                            int mimes = jMonthChooser1.getMonth() + 1;
                            String mes = DevolverMes(mimes);
                            int iddetalle = listHorariosPlanif.get(tabla.getSelectedRow()).getId_Horario_Detalle();
                            mes = mes.toUpperCase();
                            JasperViewer view = logHorarios.Reporte_HorariosDiarios("DETALLEREPORTE_HORARIOSDIARIOS", iddetalle, mes);
                            view.setTitle("REPORTE HORARIOS DEL PERSONAL");
                            view.setVisible(true);
                            view.setSize(870, 700);
                        }
                    });
                    dialog.setResizable(false);
                    dialog.setLocation(700, 400);
                    dialog.show(true);

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
}
