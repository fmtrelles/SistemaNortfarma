package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import entidad.UsuarioBotica;
import java.beans.PropertyVetoException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Administracion extends javax.swing.JFrame {

    TipoZonas objbotizona;
    TipoPrecio objTipoPrecio;
    TipoPago objTipoPago;
    TipoVentas objTipoVentas;
    AsignacionModulos objAsignacionModulos;
    BoticasZona objbotizonas;
    PersonalNuevo objPersonalNuevo;
    PersonalAgregarABotica objpersonalabotica;
    ConsultaCaja objCierreBotica;
    LogicaPersonal objPersonal = new LogicaPersonal();
    Tipo_Zona_Mantenimiento objtipozona;
    Tipo_Pagos_Mantenimiento objtipospagos;
    Tipo_Precio_Mantenimiento objtiposprecio;
    Tipo_Ventas_Mantenimiento objtiposventas;
    BoticasApertura objBoticasApertura;
    BoticasIP objBoticasIP;
    ClientesAgregar objClientesAgregar;
    DarCredito objDarCredito;
    RenovarCredito objRenovarCredito;
    OpcionesMenu objmenu;
    private static String idbotica;
    MuestraVentana objetoventana = new MuestraVentana();
    public static boolean marcacdo = false;
    public static List<UsuarioBotica> credenciales_1 = new ArrayList<UsuarioBotica>();
    private static String usuario;
    private String cargo;
    private Date fecha;
    private Time Hora;
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();

    public Administracion(OpcionesMenu obj) {
        initComponents();
        objmenu = obj;
        credenciales_1 = objmenu.getUsuariobotica();
        idbotica = objmenu.getIdbotica();
        recuperaUsuario();
        this.setIdbotica(idbotica);
        this.setUsuario(usuario);
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        Administracion.idbotica = idbotica;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Administracion.usuario = usuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jToolBar5 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField4 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField2 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField1 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        cmdPresta = new javax.swing.JButton();
        cmdAyuda1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        cmdDepa = new javax.swing.JButton();
        cmdHerra = new javax.swing.JButton();
        cmdSecc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        cmdAyuda = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Administracion.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        desktopPane.setBackground(resourceMap.getColor("desktopPane.background")); // NOI18N
        desktopPane.setName("desktopPane"); // NOI18N

        jToolBar5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar5.setRollover(true);
        jToolBar5.setName("jToolBar5"); // NOI18N
        jToolBar5.setOpaque(false);
        jToolBar5.setPreferredSize(new java.awt.Dimension(100, 20));

        jPanel1.setFocusable(false);
        jPanel1.setMinimumSize(new java.awt.Dimension(650, 35));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 30));
        jPanel1.setRequestFocusEnabled(false);

        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setRequestFocusEnabled(false);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setRequestFocusEnabled(false);

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setIcon(resourceMap.getIcon("jTextField3.icon")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setEnabled(false);
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setName("jTextField1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar5.add(jPanel1);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        cmdPresta.setIcon(resourceMap.getIcon("cmdPresta.icon")); // NOI18N
        cmdPresta.setText(resourceMap.getString("cmdPresta.text")); // NOI18N
        cmdPresta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdPresta.setName("cmdPresta"); // NOI18N
        cmdPresta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdPresta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrestaActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdPresta);

        cmdAyuda1.setIcon(resourceMap.getIcon("cmdAyuda1.icon")); // NOI18N
        cmdAyuda1.setText(resourceMap.getString("cmdAyuda1.text")); // NOI18N
        cmdAyuda1.setFocusable(false);
        cmdAyuda1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdAyuda1.setName("cmdAyuda1"); // NOI18N
        cmdAyuda1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdAyuda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAyuda1ActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdAyuda1);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

        cmdDepa.setIcon(resourceMap.getIcon("cmdDepa.icon")); // NOI18N
        cmdDepa.setText(resourceMap.getString("cmdDepa.text")); // NOI18N
        cmdDepa.setFocusable(false);
        cmdDepa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdDepa.setName("cmdDepa"); // NOI18N
        cmdDepa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdDepa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDepaActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdDepa);

        cmdHerra.setIcon(resourceMap.getIcon("cmdHerra.icon")); // NOI18N
        cmdHerra.setText(resourceMap.getString("cmdHerra.text")); // NOI18N
        cmdHerra.setFocusable(false);
        cmdHerra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdHerra.setName("cmdHerra"); // NOI18N
        cmdHerra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdHerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHerraActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdHerra);

        cmdSecc.setIcon(resourceMap.getIcon("cmdSecc.icon")); // NOI18N
        cmdSecc.setText(resourceMap.getString("cmdSecc.text")); // NOI18N
        cmdSecc.setFocusable(false);
        cmdSecc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSecc.setName("cmdSecc"); // NOI18N
        cmdSecc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSecc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSeccActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdSecc);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        cmdAyuda.setIcon(resourceMap.getIcon("cmdAyuda.icon")); // NOI18N
        cmdAyuda.setText(resourceMap.getString("cmdAyuda.text")); // NOI18N
        cmdAyuda.setFocusable(false);
        cmdAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdAyuda.setName("cmdAyuda"); // NOI18N
        cmdAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAyudaActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdAyuda);

        menuBar.setFont(resourceMap.getFont("menuBar.font")); // NOI18N
        menuBar.setName("menuBar"); // NOI18N

        jMenu5.setMnemonic('B');
        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setFont(resourceMap.getFont("jMenu5.font")); // NOI18N

        jMenuItem11.setText(resourceMap.getString("jMenuItem11.text")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed1(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        menuBar.add(jMenu5);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setFont(resourceMap.getFont("jMenu5.font")); // NOI18N

        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getActionMap(Administracion.class, this);
        jMenuItem6.setAction(actionMap.get("AsignarBoticaZona")); // NOI18N
        jMenuItem6.setIcon(resourceMap.getIcon("jMenuItem6.icon")); // NOI18N
        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        menuBar.add(jMenu2);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N

        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed1(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setIcon(resourceMap.getIcon("jMenuItem9.icon")); // NOI18N
        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem18.setText(resourceMap.getString("jMenuItem18.text")); // NOI18N
        jMenuItem18.setName("jMenuItem18"); // NOI18N
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem18);

        jMenu4.add(jMenu7);

        menuBar.add(jMenu4);

        jMenu6.setMnemonic('M');
        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setFont(resourceMap.getFont("jMenu5.font")); // NOI18N

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed1(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        menuBar.add(jMenu6);

        jMenu1.setMnemonic('C');
        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setFont(resourceMap.getFont("jMenu5.font")); // NOI18N

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem14);

        jMenuItem15.setText(resourceMap.getString("jMenuItem15.text")); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuItem16.setText(resourceMap.getString("jMenuItem16.text")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        menuBar.add(jMenu1);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setFont(resourceMap.getFont("jMenu5.font")); // NOI18N

        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar5, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarVentana() {
        int resul = JOptionPane.showConfirmDialog(this, "DESEA SALIR DEL SISTEMA ?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);

        if (resul == 0) {
            marcacdo = false;
            this.hide();
            objmenu.setVisible(true);
        }
    }

    private void recuperaUsuario() {
        try {

            usuario = credenciales_1.get(0).getApellido() + " " + credenciales_1.get(0).getNombre();
            cargo = credenciales_1.get(0).getDescrip_roles();

            fecha = objlogiccafecha.RetornaFecha();
            Hora = Time.valueOf(objlogiccafecha.RetornaHora());

            this.jTextField3.setText(usuario);
            this.jTextField4.setText(cargo);
            this.jTextField2.setText(fecha.toString());
            this.jTextField1.setText(Hora.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objbotizonas = new BoticasZona(objetoventana);
                objbotizonas.pack();
                objbotizonas.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objbotizona = new TipoZonas(objetoventana, this);
                objbotizona.pack();
                objbotizona.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        objtipospagos = new Tipo_Pagos_Mantenimiento();
        objtipospagos.show(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objTipoPrecio = new TipoPrecio(objetoventana, this);
                objTipoPrecio.pack();
                objTipoPrecio.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objTipoVentas = new TipoVentas(this.objetoventana, this);
                objTipoVentas.pack();
                objTipoVentas.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed1
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objTipoPago = new TipoPago(objetoventana, this);
                objTipoPago.pack();
                objTipoPago.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed1

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objAsignacionModulos = new AsignacionModulos(objetoventana);
                objAsignacionModulos.pack();
                objAsignacionModulos.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void AsignarloBotica() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objpersonalabotica = new PersonalAgregarABotica(this);
                abrirVentanaInterna(objpersonalabotica);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        AsignarloBotica();

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objBoticasIP = new BoticasIP(objetoventana);
                objBoticasIP.pack();
                objBoticasIP.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void Apertura_Credito() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(49);
            if (resul) {
                marcacdo = true;
                Apertura_Credito obj = new Apertura_Credito(this);
                desktopPane.add(obj);
                obj.setLocation(obj.getParent().getWidth() / 2 - obj.getWidth() / 2, obj.getParent().getHeight() / 2 - obj.getHeight() / 2);
                obj.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        Apertura_Credito();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void CrearCliente() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objClientesAgregar = new ClientesAgregar(objetoventana, idbotica, this);
                desktopPane.add(objClientesAgregar);
                objClientesAgregar.setLocation(objClientesAgregar.getParent().getWidth() / 2 - objClientesAgregar.getWidth() / 2, objClientesAgregar.getParent().getHeight() / 2 - objClientesAgregar.getHeight() / 2);
                objClientesAgregar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        CrearCliente();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void RenovarCredito() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objRenovarCredito = new RenovarCredito(objetoventana, idbotica, this);
                desktopPane.add(objRenovarCredito);
                objRenovarCredito.setLocation(objRenovarCredito.getParent().getWidth() / 2 - objRenovarCredito.getWidth() / 2, objRenovarCredito.getParent().getHeight() / 2 - objRenovarCredito.getHeight() / 2);
                objRenovarCredito.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        RenovarCredito();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void AperturaCredito() throws ParseException {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objDarCredito = new DarCredito(objetoventana, idbotica, this);
                objDarCredito.pack();
                objDarCredito.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        try {
            AperturaCredito();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void CrearUsuario() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objPersonalNuevo = new PersonalNuevo(this.objetoventana, this);
                desktopPane.add(objPersonalNuevo);
                objPersonalNuevo.setLocation(objPersonalNuevo.getParent().getWidth() / 2 - objPersonalNuevo.getWidth() / 2, objPersonalNuevo.getParent().getHeight() / 2 - objPersonalNuevo.getHeight() / 2);
                objPersonalNuevo.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

private void jMenuItem8ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed1
    CrearUsuario();
}//GEN-LAST:event_jMenuItem8ActionPerformed1

private void cmdDepaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDepaActionPerformed
    CrearCliente();
}//GEN-LAST:event_cmdDepaActionPerformed

private void cmdHerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerraActionPerformed
        try {
            AperturaCredito();
        } catch (ParseException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_cmdHerraActionPerformed

private void cmdSeccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSeccActionPerformed
    RenovarCredito();
}//GEN-LAST:event_cmdSeccActionPerformed

private void cmdPrestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrestaActionPerformed
    CrearUsuario();
}//GEN-LAST:event_cmdPrestaActionPerformed

private void cmdAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAyudaActionPerformed
    CerrarVentana();
}//GEN-LAST:event_cmdAyudaActionPerformed

private void cmdAyuda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAyuda1ActionPerformed
    AsignarloBotica();
}//GEN-LAST:event_cmdAyuda1ActionPerformed

private void jMenuItem13ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed1
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem13ActionPerformed1

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
    Cierre_Credito();
}//GEN-LAST:event_jMenuItem18ActionPerformed

    private void Cierre_Credito() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(50);
            if (resul) {
                marcacdo = true;
                CierreCredito_Personal obj = new CierreCredito_Personal(this);
                desktopPane.add(obj);
                obj.setLocation(obj.getParent().getWidth() / 2 - obj.getWidth() / 2, obj.getParent().getHeight() / 2 - obj.getHeight() / 2);
                obj.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void abrirVentanaInterna(JInternalFrame ventana) {
        ventana.pack();
        ventana.setVisible(true);
        this.desktopPane.add(ventana);
        try {
            ventana.setMaximum(true);
            ventana.requestFocus();
        } catch (PropertyVetoException ex) {
        }
    }

    private boolean VerifivaCredenciales(int valorsubmenu) {
        boolean permiso = false;
        int cant;

        try {
            for (cant = 0; cant < credenciales_1.size(); cant++) {
                int idpagina = credenciales_1.get(cant).getIdpagina();

                if (idpagina == valorsubmenu) {
                    cant = credenciales_1.size();
                    permiso = true;
                    objPersonal.TrackingUser(valorsubmenu, OpcionesMenu.getIdpersonal_botica(), 1);
                } else {
                    objPersonal.TrackingUser(valorsubmenu, OpcionesMenu.getIdpersonal_botica(), 0);
                }
            }
        } catch (Exception ex) {
        }

        return permiso;
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAyuda;
    private javax.swing.JButton cmdAyuda1;
    private javax.swing.JButton cmdDepa;
    private javax.swing.JButton cmdHerra;
    private javax.swing.JButton cmdPresta;
    private javax.swing.JButton cmdSecc;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField1;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField2;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField3;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
