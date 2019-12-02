package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaRecuperaCaja;
import entidad.Cajas;
import entidad.Personal;
import entidad.ProductosPrecios;
import entidad.UsuarioBotica;
import java.beans.PropertyVetoException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;
import CapaLogica.LogicaProforma;

public class AplicacionVentas extends javax.swing.JFrame {

    Cotizacion objcotizacion;
    CotizacionSoat objcotizasoat;
    Recuperavtasoat objRecuperasoat;
    RealizaVenta objventa;
    public static boolean marcacdo = false;
    /*
     * VARIABLES PARA PODER CONTROLAR MIS ACCESOS A USUARIOS
     */
    private static String usuario;
    private static String cargo;
    private static String idbotica;
    private static String identificacion;
    LogicaProforma onjproforma = new LogicaProforma();
    private static int id_personal_botica;
    public static List<UsuarioBotica> credenciales = new ArrayList<UsuarioBotica>();
    OpcionesMenu objsesion;
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    LogicaRecuperaCaja objcaja = new LogicaRecuperaCaja();
    private static Date fecha;
    private static Time Hora;
    MuestraProformas objmuestraproforma;
    NotaCredito1 objnotaCredito1;
    NotaDebito objnotaDebito;
    OpcionesReportes objreporte;
    private LogicaCPersonal logicapersonal = new LogicaCPersonal();
    Personal obj;
    ConsultarVenta objconsulta = null;
    LogicaBoticas logbotica = new LogicaBoticas();
    public static int Delivery;
    public static int MarcadoDelivery;
    public static boolean esAlmacen;
    JFrame objetoventana;

    public AplicacionVentas(OpcionesMenu objsesion1) {
        initComponents();
        objsesion = objsesion1;
        this.setIconImage(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/cart_edit.png")).getImage());
        credenciales = objsesion.getUsuariobotica();
        this.setCredenciales(credenciales);
        idbotica = objsesion.getIdbotica();
        setIdbotica(idbotica);
        setExtendedState(this.MAXIMIZED_BOTH);
        id_personal_botica = objsesion.getIdpersonal_botica();
        setId_personal_botica(id_personal_botica);
        recuperaUsuario();
        RecuperaData();
        jMenu1.requestFocus();
        esAlmacen = logbotica.VerificaPoseeAlmacenes(idbotica);
        this.cmdSecc2.setVisible(false);
        this.cmdHerra2.setVisible(false);
        this.jMenuItem10.setEnabled(true);  //anular internos
        this.jMenuItem34.setEnabled(false);
    }

    public static boolean isEsAlmacen() {
        return esAlmacen;
    }

    public static void setEsAlmacen(boolean esAlmacen) {
        AplicacionVentas.esAlmacen = esAlmacen;
    }

    public static int getMarcadoDelivery() {
        return MarcadoDelivery;
    }

    public static void setMarcadoDelivery(int MarcadoDelivery) {
        AplicacionVentas.MarcadoDelivery = MarcadoDelivery;
    }

    public static int getDelivery() {
        return Delivery;
    }

    public static void setDelivery(int Delivery) {
        AplicacionVentas.Delivery = Delivery;
    }

    public static List<UsuarioBotica> getCredenciales() {
        return credenciales;
    }

    public static void setCredenciales(List<UsuarioBotica> credenciales) {
        AplicacionVentas.credenciales = credenciales;
    }

    public static int getId_personal_botica() {
        return id_personal_botica;
    }

    public static void setId_personal_botica(int id_personal_botica) {
        AplicacionVentas.id_personal_botica = id_personal_botica;
    }

    public static Time getHora() {
        return Hora;
    }

    public static void setHora(Time Hora) {
        AplicacionVentas.Hora = Hora;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        AplicacionVentas.fecha = fecha;
    }

    private void RecuperaData() {
        try {
            fecha = objlogiccafecha.RetornaFecha();
            Hora = Time.valueOf(objlogiccafecha.RetornaHora());
            //identificacion = objlogiccafecha.RetornaIdentificacion();
            setFecha(fecha);
            setHora(Hora);
            this.jTextField1.setText(Hora.toString());
            this.jTextField2.setText(fecha.toString());
            this.jTextField3.setText(credenciales.get(0).getApellido() + " " + credenciales.get(0).getNombre());
            this.jTextField4.setText(cargo);
            //this.jTextField5.setText(identificacion);
        } catch (Exception ex) {
        }
    }

    private void recuperaUsuario() {
        try {
            usuario = credenciales.get(0).getApellido() + " " + credenciales.get(0).getNombre();
            cargo = credenciales.get(0).getDescrip_roles();
            id_personal_botica = credenciales.get(0).getId_Personal();
            idbotica = objsesion.getIdbotica();
            setCargo(cargo);
            setUsuario(usuario);
            setIdbotica(idbotica);
            setIdpesonal(id_personal_botica);
        } catch (Exception ex) {
            System.out.println("RECUPERAUSUARIO :" + ex.getMessage());
        }
    }

    public static int getIdpesonal() {
        return id_personal_botica;
    }

    public static void setIdpesonal(int idpesonal) {
        AplicacionVentas.id_personal_botica = idpesonal;
    }

    public static String getCargo() {
        return cargo;
    }

    public static void setCargo(String cargo) {
        AplicacionVentas.cargo = cargo;
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        AplicacionVentas.idbotica = idbotica;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        AplicacionVentas.usuario = usuario;
    }

    public void abrirVentanaInterna(JInternalFrame ventana) {
        ventana.pack();
        ventana.setVisible(true);
        this.jDesktopPane1.add(ventana);
        try {
            ventana.setMaximum(true);
            ventana.requestFocus();
        } catch (PropertyVetoException ex) {
        }
    }

    public void CerrarVentana(JInternalFrame ventana) {
        ventana.setVisible(false);
        ventana.hide();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JToolBar.Separator();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jSeparator22 = new javax.swing.JToolBar.Separator();
        jToolBar2 = new javax.swing.JToolBar();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        cmdPresta1 = new javax.swing.JButton();
        cmdHerra1 = new javax.swing.JButton();
        jSeparator28 = new javax.swing.JToolBar.Separator();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdPresta = new javax.swing.JButton();
        cmdAyuda1 = new javax.swing.JButton();
        cmdSOAT = new javax.swing.JButton();
        jSeparator23 = new javax.swing.JToolBar.Separator();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        cmdDepa = new javax.swing.JButton();
        cmdHerra = new javax.swing.JButton();
        cmdHerra3 = new javax.swing.JButton();
        cmdHerra2 = new javax.swing.JButton();
        cmdSecc = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        cmdSecc2 = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        cmdSecc1 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        cmdDepa1 = new javax.swing.JButton();
        cmdAyuda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField4 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField2 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField1 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();

        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(AplicacionVentas.class);
        jMenuItem16.setText(resourceMap.getString("jMenuItem16.text")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem16);

        jMenuItem18.setText(resourceMap.getString("jMenuItem18.text")); // NOI18N
        jMenuItem18.setName("jMenuItem18"); // NOI18N
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem18);

        jMenuItem17.setText(resourceMap.getString("jMenuItem17.text")); // NOI18N
        jMenuItem17.setName("jMenuItem17"); // NOI18N
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem17);

        jSeparator26.setName("jSeparator26"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setState(6);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jDesktopPane1.setBackground(resourceMap.getColor("jDesktopPane1.background")); // NOI18N
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        jDesktopPane1.setOpaque(false);

        jSeparator22.setName("jSeparator22"); // NOI18N
        jDesktopPane1.add(jSeparator22);
        jSeparator22.setBounds(0, 0, 0, 6);

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        jSeparator8.setName("jSeparator8"); // NOI18N
        jToolBar2.add(jSeparator8);

        cmdPresta1.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdPresta1.setIcon(resourceMap.getIcon("cmdPresta1.icon")); // NOI18N
        cmdPresta1.setText(resourceMap.getString("cmdPresta1.text")); // NOI18N
        cmdPresta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdPresta1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdPresta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPresta1ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdPresta1);

        cmdHerra1.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdHerra1.setIcon(resourceMap.getIcon("cmdHerra1.icon")); // NOI18N
        cmdHerra1.setText(resourceMap.getString("cmdHerra1.text")); // NOI18N
        cmdHerra1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdHerra1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdHerra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHerra1ActionPerformed1(evt);
            }
        });
        jToolBar2.add(cmdHerra1);

        jSeparator28.setName("jSeparator28"); // NOI18N
        jToolBar2.add(jSeparator28);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar2.add(jSeparator1);

        cmdPresta.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdPresta.setIcon(resourceMap.getIcon("cmdPresta.icon")); // NOI18N
        cmdPresta.setText(resourceMap.getString("cmdPresta.text")); // NOI18N
        cmdPresta.setToolTipText(resourceMap.getString("cmdPresta.toolTipText")); // NOI18N
        cmdPresta.setFocusable(false);
        cmdPresta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdPresta.setName("cmdPresta"); // NOI18N
        cmdPresta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdPresta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrestaActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdPresta);

        cmdAyuda1.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdAyuda1.setIcon(resourceMap.getIcon("cmdAyuda1.icon")); // NOI18N
        cmdAyuda1.setText(resourceMap.getString("cmdAyuda1.text")); // NOI18N
        cmdAyuda1.setToolTipText(resourceMap.getString("cmdAyuda1.toolTipText")); // NOI18N
        cmdAyuda1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdAyuda1.setPreferredSize(new java.awt.Dimension(115, 39));
        cmdAyuda1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdAyuda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAyuda1ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdAyuda1);

        cmdSOAT.setIcon(resourceMap.getIcon("cmdSOAT.icon")); // NOI18N
        cmdSOAT.setText(resourceMap.getString("cmdSOAT.text")); // NOI18N
        cmdSOAT.setToolTipText(resourceMap.getString("cmdSOAT.toolTipText")); // NOI18N
        cmdSOAT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmdSOAT.setFocusable(false);
        cmdSOAT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSOAT.setName("cmdSOAT"); // NOI18N
        cmdSOAT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSOAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSOATActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdSOAT);

        jSeparator23.setName("jSeparator23"); // NOI18N
        jToolBar2.add(jSeparator23);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jToolBar2.add(jSeparator9);

        cmdDepa.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdDepa.setIcon(resourceMap.getIcon("cmdDepa.icon")); // NOI18N
        cmdDepa.setText(resourceMap.getString("cmdDepa.text")); // NOI18N
        cmdDepa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdDepa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdDepa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDepaActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdDepa);

        cmdHerra.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdHerra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdHerra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdHerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHerraActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdHerra);

        cmdHerra3.setFont(resourceMap.getFont("cmdHerra3.font")); // NOI18N
        cmdHerra3.setIcon(resourceMap.getIcon("cmdHerra3.icon")); // NOI18N
        cmdHerra3.setText(resourceMap.getString("cmdHerra3.text")); // NOI18N
        cmdHerra3.setFocusable(false);
        cmdHerra3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdHerra3.setName("cmdHerra3"); // NOI18N
        cmdHerra3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdHerra3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHerra3ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdHerra3);

        cmdHerra2.setFont(resourceMap.getFont("cmdHerra2.font")); // NOI18N
        cmdHerra2.setIcon(resourceMap.getIcon("cmdHerra2.icon")); // NOI18N
        cmdHerra2.setText(resourceMap.getString("cmdHerra2.text")); // NOI18N
        cmdHerra2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdHerra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHerra2ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdHerra2);

        cmdSecc.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdSecc.setIcon(resourceMap.getIcon("cmdSecc.icon")); // NOI18N
        cmdSecc.setText(resourceMap.getString("cmdSecc.text")); // NOI18N
        cmdSecc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSecc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSecc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSeccActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdSecc);

        jSeparator12.setName("jSeparator12"); // NOI18N
        jToolBar2.add(jSeparator12);

        cmdSecc2.setFont(resourceMap.getFont("cmdSecc2.font")); // NOI18N
        cmdSecc2.setIcon(resourceMap.getIcon("cmdSecc2.icon")); // NOI18N
        cmdSecc2.setText(resourceMap.getString("cmdSecc2.text")); // NOI18N
        cmdSecc2.setFocusable(false);
        cmdSecc2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSecc2.setName("cmdSecc2"); // NOI18N
        cmdSecc2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSecc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSecc2ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdSecc2);

        jSeparator14.setName("jSeparator14"); // NOI18N
        jToolBar2.add(jSeparator14);

        cmdSecc1.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdSecc1.setIcon(resourceMap.getIcon("cmdSecc1.icon")); // NOI18N
        cmdSecc1.setText(resourceMap.getString("cmdSecc1.text")); // NOI18N
        cmdSecc1.setFocusable(false);
        cmdSecc1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdSecc1.setName("cmdSecc1"); // NOI18N
        cmdSecc1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdSecc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSecc1ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdSecc1);

        jSeparator15.setName("jSeparator15"); // NOI18N
        jToolBar2.add(jSeparator15);

        cmdDepa1.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
        cmdDepa1.setIcon(resourceMap.getIcon("cmdDepa1.icon")); // NOI18N
        cmdDepa1.setText(resourceMap.getString("cmdDepa1.text")); // NOI18N
        cmdDepa1.setFocusable(false);
        cmdDepa1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdDepa1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdDepa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDepa1ActionPerformed(evt);
            }
        });
        jToolBar2.add(cmdDepa1);

        cmdAyuda.setFont(resourceMap.getFont("cmdPresta.font")); // NOI18N
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
        jToolBar2.add(cmdAyuda);

        jPanel1.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jPanel1.setFocusable(false);
        jPanel1.setMinimumSize(new java.awt.Dimension(650, 35));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 30));
        jPanel1.setRequestFocusEnabled(false);

        jLabel5.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setRequestFocusEnabled(false);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setRequestFocusEnabled(false);

        jTextField3.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setFocusable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField3.setIcon(resourceMap.getIcon("jTextField3.icon")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setEnabled(false);
        jTextField4.setFocusable(false);
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setFocusable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField2.setIcon(resourceMap.getIcon("jTextField2.icon")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setFocusable(false);
        jTextField1.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setBackground(resourceMap.getColor("menuBar.background")); // NOI18N
        menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuBar.setFont(resourceMap.getFont("menuBar.font")); // NOI18N
        menuBar.setName("menuBar"); // NOI18N
        menuBar.setOpaque(false);
        menuBar.setPreferredSize(new java.awt.Dimension(257, 26));

        fileMenu.setBackground(resourceMap.getColor("jMenu1.background")); // NOI18N
        fileMenu.setMnemonic('A');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N
        fileMenu.setPreferredSize(new java.awt.Dimension(60, 19));

        openMenuItem.setBackground(resourceMap.getColor("openMenuItem.background")); // NOI18N
        openMenuItem.setFont(resourceMap.getFont("jMenuItem5.font")); // NOI18N
        openMenuItem.setIcon(resourceMap.getIcon("openMenuItem.icon")); // NOI18N
        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        fileMenu.add(openMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getActionMap(AplicacionVentas.class, this);
        jMenuItem5.setAction(actionMap.get("CambiarDeUsuario")); // NOI18N
        jMenuItem5.setBackground(resourceMap.getColor("openMenuItem.background")); // NOI18N
        jMenuItem5.setFont(resourceMap.getFont("jMenuItem5.font")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        fileMenu.add(jMenuItem5);

        jSeparator2.setBackground(resourceMap.getColor("jSeparator2.background")); // NOI18N
        jSeparator2.setForeground(resourceMap.getColor("jSeparator2.foreground")); // NOI18N
        jSeparator2.setName("jSeparator2"); // NOI18N
        fileMenu.add(jSeparator2);

        jMenuItem3.setAction(actionMap.get("Cerrar")); // NOI18N
        jMenuItem3.setBackground(resourceMap.getColor("openMenuItem.background")); // NOI18N
        jMenuItem3.setFont(resourceMap.getFont("jMenuItem5.font")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        fileMenu.add(jMenuItem3);

        menuBar.add(fileMenu);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N

        jMenuItem12.setFont(resourceMap.getFont("jMenuItem13.font")); // NOI18N
        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem32.setText(resourceMap.getString("jMenuItem32.text")); // NOI18N
        jMenuItem32.setName("jMenuItem32"); // NOI18N
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem32);

        jSeparator7.setName("jSeparator7"); // NOI18N
        jMenu4.add(jSeparator7);

        jMenuItem13.setFont(resourceMap.getFont("jMenuItem13.font")); // NOI18N
        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem42.setText(resourceMap.getString("jMenuItem42.text")); // NOI18N
        jMenuItem42.setName("jMenuItem42"); // NOI18N
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem42);

        menuBar.add(jMenu4);

        jMenu1.setBackground(resourceMap.getColor("jMenu1.background")); // NOI18N
        jMenu1.setMnemonic('P');
        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAction(actionMap.get("RealizaProforma")); // NOI18N
        jMenuItem1.setFont(resourceMap.getFont("jMenuItem2.font")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem8.setAction(actionMap.get("VenBotiquin")); // NOI18N
        jMenuItem8.setFont(resourceMap.getFont("jMenuItem2.font")); // NOI18N
        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem33.setAction(actionMap.get("RealizaNuevaProforma")); // NOI18N
        jMenuItem33.setText(resourceMap.getString("jMenuItem33.text")); // NOI18N
        jMenuItem33.setName("jMenuItem33"); // NOI18N
        jMenu1.add(jMenuItem33);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu1.add(jSeparator4);

        jMenuItem2.setAction(actionMap.get("ModificaProfrom")); // NOI18N
        jMenuItem2.setFont(resourceMap.getFont("jMenuItem2.font")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem35.setAction(actionMap.get("RealizarProformaSoat")); // NOI18N
        jMenuItem35.setText(resourceMap.getString("jMenuItem35.text")); // NOI18N
        jMenuItem35.setName("jMenuItem35"); // NOI18N
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem35);

        menuBar.add(jMenu1);

        jMenu10.setText(resourceMap.getString("jMenu10.text")); // NOI18N
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });

        jMenuItem7.setAction(actionMap.get("Revisar")); // NOI18N
        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem7);

        menuBar.add(jMenu10);

        jMenu5.setBackground(resourceMap.getColor("jMenu1.background")); // NOI18N
        jMenu5.setMnemonic('V');
        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem9.setAction(actionMap.get("GeneraVenta")); // NOI18N
        jMenuItem9.setFont(resourceMap.getFont("jMenuItem11.font")); // NOI18N
        jMenuItem9.setIcon(resourceMap.getIcon("jMenuItem9.icon")); // NOI18N
        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setToolTipText(resourceMap.getString("jMenuItem9.toolTipText")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem22.setFont(resourceMap.getFont("jMenuItem11.font")); // NOI18N
        jMenuItem22.setText(resourceMap.getString("jMenuItem22.text")); // NOI18N
        jMenuItem22.setName("jMenuItem22"); // NOI18N
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem19.setFont(resourceMap.getFont("jMenuItem11.font")); // NOI18N
        jMenuItem19.setText(resourceMap.getString("jMenuItem19.text")); // NOI18N
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed1(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jSeparator16.setName("jSeparator16"); // NOI18N
        jMenu5.add(jSeparator16);

        jMenuItem11.setText(resourceMap.getString("jMenuItem11.text")); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem41.setText(resourceMap.getString("jMenuItem41.text")); // NOI18N
        jMenuItem41.setName("jMenuItem41"); // NOI18N
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem41);
        jMenu5.add(jSeparator3);

        jMenuItem10.setText(resourceMap.getString("jMenuItem10.text")); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuItem34.setText(resourceMap.getString("jMenuItem34.text")); // NOI18N
        jMenuItem34.setName("jMenuItem34"); // NOI18N
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem34);

        jMenuItem36.setAction(actionMap.get("GeneraVenta")); // NOI18N
        jMenuItem36.setIcon(resourceMap.getIcon("jMenuItem36.icon")); // NOI18N
        jMenuItem36.setText(resourceMap.getString("jMenuItem36.text")); // NOI18N
        jMenuItem36.setToolTipText(resourceMap.getString("jMenuItem36.toolTipText")); // NOI18N
        jMenuItem36.setName("jMenuItem36"); // NOI18N
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem36);

        menuBar.add(jMenu5);

        jMenu3.setBackground(resourceMap.getColor("jMenu1.background")); // NOI18N
        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem6.setAction(actionMap.get("NotaCredito")); // NOI18N
        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu3.add(jMenuItem6);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jMenu3.add(jSeparator6);

        jMenuItem20.setText(resourceMap.getString("jMenuItem20.text")); // NOI18N
        jMenuItem20.setName("jMenuItem20"); // NOI18N
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem20);

        jMenuItem23.setText(resourceMap.getString("jMenuItem23.text")); // NOI18N
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem23);

        jSeparator11.setName("jSeparator11"); // NOI18N
        jMenu3.add(jSeparator11);

        jMenuItem31.setText(resourceMap.getString("jMenuItem31.text")); // NOI18N
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenuItem30.setText(resourceMap.getString("jMenuItem30.text")); // NOI18N
        jMenuItem30.setName("jMenuItem30"); // NOI18N
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem30);

        jMenuItem37.setText(resourceMap.getString("jMenuItem37.text")); // NOI18N
        jMenuItem37.setName("jMenuItem37"); // NOI18N
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem37);

        jMenuItem38.setText(resourceMap.getString("jMenuItem38.text")); // NOI18N
        jMenuItem38.setName("jMenuItem38"); // NOI18N
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem38);

        menuBar.add(jMenu3);

        jMenu8.setText(resourceMap.getString("jMenu8.text")); // NOI18N
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });

        jMenuItem24.setText(resourceMap.getString("jMenuItem24.text")); // NOI18N
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenuItem24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenuItem24KeyPressed1(evt);
            }
        });
        jMenu8.add(jMenuItem24);

        jMenuItem29.setText(resourceMap.getString("jMenuItem29.text")); // NOI18N
        jMenuItem29.setName("jMenuItem29"); // NOI18N
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem29);
        jMenu8.add(jSeparator18);

        jMenuItem15.setText(resourceMap.getString("jMenuItem15.text")); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        jMenuItem39.setText(resourceMap.getString("jMenuItem39.text")); // NOI18N
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem39);

        jMenuItem40.setText(resourceMap.getString("jMenuItem40.text")); // NOI18N
        jMenuItem40.setName("jMenuItem40"); // NOI18N
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem40);

        jSeparator17.setName("jSeparator17"); // NOI18N
        jMenu8.add(jSeparator17);

        jMenuItem21.setText(resourceMap.getString("jMenuItem21.text")); // NOI18N
        jMenuItem21.setName("jMenuItem21"); // NOI18N
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        menuBar.add(jMenu8);

        jMenu2.setBackground(resourceMap.getColor("jMenu1.background")); // NOI18N
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setFont(resourceMap.getFont("jMenu2.font")); // NOI18N

        jMenuItem4.setAction(actionMap.get("ReporteCaja")); // NOI18N
        jMenuItem4.setFont(resourceMap.getFont("jMenuItem4.font")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N

        jMenuItem27.setText(resourceMap.getString("jMenuItem27.text")); // NOI18N
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem27);

        jMenuItem26.setText(resourceMap.getString("jMenuItem26.text")); // NOI18N
        jMenuItem26.setName("jMenuItem26"); // NOI18N
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        jMenuItem28.setText(resourceMap.getString("jMenuItem28.text")); // NOI18N
        jMenuItem28.setName("jMenuItem28"); // NOI18N
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem28);

        jMenu2.add(jMenu7);

        menuBar.add(jMenu2);

        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N

        jMenuItem25.setText(resourceMap.getString("jMenuItem25.text")); // NOI18N
        jMenuItem25.setName("jMenuItem25"); // NOI18N
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem25);

        menuBar.add(jMenu6);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        objcotizacion = new Cotizacion("01", this, objsesion);
        objcotizacion.show(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void CerrarSesion() {
        Habilita(false, cmdAyuda);
        int resul = JOptionPane.showConfirmDialog(this, "DESEA SALIR DEL SISTEMA ?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);

        if (resul == 0) {
            marcacdo = false;
            hide();
            Habilita(true);
            objsesion.setVisible(true);
        } else {
            Habilita(true);
        }
    }
    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
    }//GEN-LAST:event_fileMenuActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void ConsultarVenta() {
        try {
            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(23);
                if (resul) {
                    Habilita(false, cmdHerra);
                    marcacdo = true;
                    objconsulta = new ConsultarVenta(this);
                    jDesktopPane1.add(objconsulta);
                    objconsulta.setLocation(objconsulta.getParent().getWidth() / 2 - objconsulta.getWidth() / 2, objconsulta.getParent().getHeight() / 2 - objconsulta.getHeight() / 2);
                    objconsulta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        ConsultarVenta();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void AnularVenta() {
        try {
            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(25);
                if (resul) {
                    Habilita(false, cmdHerra2);
                    marcacdo = true;
                    Anulaciones objanula = new Anulaciones(idbotica, this);
                    jDesktopPane1.add(objanula);
                    objanula.setLocation(objanula.getParent().getWidth() / 2 - objanula.getWidth() / 2, objanula.getParent().getHeight() / 2 - objanula.getHeight() / 2);
                    objanula.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void PermisoAnularCajero() {
        try {
            if (marcacdo == false) {
                //boolean resul = VerifivaCredenciales(25);
                boolean resul = true;
                if (resul) {
                    Habilita(false, cmdHerra2);
                    marcacdo = true;
                    Anulaciones objanula = new Anulaciones(idbotica, this);
                    jDesktopPane1.add(objanula);
                    objanula.setLocation(objanula.getParent().getWidth() / 2 - objanula.getWidth() / 2, objanula.getParent().getHeight() / 2 - objanula.getHeight() / 2);
                    objanula.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        AnularVenta();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
    AperturaBotica();
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
    CerrarCaja();
}//GEN-LAST:event_jMenuItem13ActionPerformed

private void cmdPrestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrestaActionPerformed
    RealizarProforma("01");
}//GEN-LAST:event_cmdPrestaActionPerformed

private void cmdAyuda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAyuda1ActionPerformed
    RealizarProforma1("02");
}//GEN-LAST:event_cmdAyuda1ActionPerformed

private void cmdDepaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDepaActionPerformed
    GenerarVenta1();
}//GEN-LAST:event_cmdDepaActionPerformed

private void cmdHerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerraActionPerformed
    ConsultarVenta();
}//GEN-LAST:event_cmdHerraActionPerformed

private void cmdSeccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSeccActionPerformed

    int rol = credenciales.get(0).getIdrol();
    int opc = 1;
    String fecha = this.jTextField2.getText();
    int feriado = onjproforma.VerificaCajaFeriado(fecha);
    int rolacceso = onjproforma.VerificaRolAcceso(rol, opc);

    if (rolacceso == 1 && feriado == 1) {
        PermisoRealizaNotaCreditoCajero();

    } else {
        RealizaNotaCredito();
    }
}//GEN-LAST:event_cmdSeccActionPerformed

private void cmdAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAyudaActionPerformed
    CerrarSesion();
}//GEN-LAST:event_cmdAyudaActionPerformed

private void cmdHerra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerra1ActionPerformed
    Consulta_Caja();
}//GEN-LAST:event_cmdHerra1ActionPerformed

private void cmdHerra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerra2ActionPerformed

    int rol = credenciales.get(0).getIdrol();
    int opc = 1;
    String fecha = this.jTextField2.getText();
    int feriado = onjproforma.VerificaCajaFeriado(fecha);
    int rolacceso = onjproforma.VerificaRolAcceso(rol, opc);

    if (rolacceso == 1 && feriado == 1) {
        PermisoAnularCajero();

    } else {

        AnularVenta();

    }
}//GEN-LAST:event_cmdHerra2ActionPerformed

private void cmdSecc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSecc1ActionPerformed
    MuestraReportes();
}//GEN-LAST:event_cmdSecc1ActionPerformed

private void cmdPresta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPresta1ActionPerformed
    AperturaBotica();
}//GEN-LAST:event_cmdPresta1ActionPerformed

private void cmdDepa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDepa1ActionPerformed
    CambioUsuario();
}//GEN-LAST:event_cmdDepa1ActionPerformed

private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
    GenerarPago();
}//GEN-LAST:event_jMenuItem15ActionPerformed

private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
    RealizaNotaCredito();
}//GEN-LAST:event_jMenuItem16ActionPerformed

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
    try {

        ReporteNotasCredito repor;
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(26);
            if (resul) {
                marcacdo = true;
                repor = new ReporteNotasCredito(idbotica, this);
                jDesktopPane1.add(repor);
                repor.setLocation(repor.getParent().getWidth() / 2 - repor.getWidth() / 2, repor.getParent().getHeight() / 2 - repor.getHeight() / 2);
                repor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem18ActionPerformed

private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
}//GEN-LAST:event_jMenuItem19ActionPerformed

private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
    FormAgenteBCP objelimina = new FormAgenteBCP(this);
    objelimina.setVisible(true);
}//GEN-LAST:event_jMenuItem20ActionPerformed

private void jMenuItem19ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed1
    boolean resul = VerifivaCredenciales(27);
    if (resul) {
        marcacdo = true;
        CorrigeNumeracion objelimina = new CorrigeNumeracion(this);
        jDesktopPane1.add(objelimina);
        objelimina.setLocation(objelimina.getParent().getWidth() / 2 - objelimina.getWidth() / 2, objelimina.getParent().getHeight() / 2 - objelimina.getHeight() / 2);
        objelimina.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jMenuItem19ActionPerformed1

private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem17ActionPerformed

private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(28);
            if (resul) {
                marcacdo = true;
                FormReimpresionInterno objeto = new FormReimpresionInterno(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem22ActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    objsesion.setVisible(true);
}//GEN-LAST:event_formWindowClosed

private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(31);
            if (resul) {
                marcacdo = true;
                FormAgente objeto = new FormAgente(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem23ActionPerformed

private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(32);
            if (resul) {
                marcacdo = true;
                FormGastosCaja objeto = new FormGastosCaja(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem24ActionPerformed

private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
    SistemaNortfarmaAboutBox pe = new SistemaNortfarmaAboutBox();
    pe.pack();
    pe.setVisible(true);
}//GEN-LAST:event_jMenuItem25ActionPerformed

private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(42);

            if (resul) {
                FacturasDelMes fact = new FacturasDelMes(this);
                this.jDesktopPane1.add(fact);
                fact.setLocation(fact.getParent().getWidth() / 2 - fact.getWidth() / 2, fact.getParent().getHeight() / 2 - fact.getHeight() / 2);
                fact.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    } catch (Exception ex) {
    }

}//GEN-LAST:event_jMenuItem27ActionPerformed

private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed

    try {

        ReporteNotasCredito repor;

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(43);
            if (resul) {
                marcacdo = true;
                repor = new ReporteNotasCredito(idbotica, this);
                jDesktopPane1.add(repor);
                repor.setLocation(repor.getParent().getWidth() / 2 - repor.getWidth() / 2, repor.getParent().getHeight() / 2 - repor.getHeight() / 2);
                repor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem26ActionPerformed

private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
    try {

        Facturas_Cliente repor;

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(44);
            if (resul) {
                marcacdo = true;
                repor = new Facturas_Cliente(this);
                jDesktopPane1.add(repor);
                repor.setLocation(repor.getParent().getWidth() / 2 - repor.getWidth() / 2, repor.getParent().getHeight() / 2 - repor.getHeight() / 2);
                repor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem28ActionPerformed

private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(45);
            if (resul) {
                marcacdo = true;
                ForIngTipoCambio objresgitra = new ForIngTipoCambio(this);
                jDesktopPane1.add(objresgitra);
                objresgitra.setLocation(objresgitra.getParent().getWidth() / 2 - objresgitra.getWidth() / 2, objresgitra.getParent().getHeight() / 2 - objresgitra.getHeight() / 2);
                objresgitra.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem21ActionPerformed

private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(48);
            if (resul) {
                marcacdo = true;
                FormIngresoCaja objeto = new FormIngresoCaja(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem29ActionPerformed

private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
    try {
        boolean resul = VerifivaCredenciales(55);
        if (resul) {
            RevisionCuadreCaja objeto = new RevisionCuadreCaja(this);
            objeto.pack();
            objeto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem30ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(54);
            if (resul) {
                marcacdo = true;
                FomCuadreGastos objeto = new FomCuadreGastos(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem31ActionPerformed

private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
    Consulta_Caja();
}//GEN-LAST:event_jMenuItem32ActionPerformed

private void cmdHerra1ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerra1ActionPerformed1
    Consulta_Caja();
}//GEN-LAST:event_cmdHerra1ActionPerformed1

private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
    Ventas_Delivery();
}//GEN-LAST:event_jMenuItem14ActionPerformed

private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
    // TODO add your handling code here:
    try {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(67);
            if (resul) {
                marcacdo = true;
                FormPreAnulacionInterno objeto = new FormPreAnulacionInterno(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem34ActionPerformed

private void cmdSOATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSOATActionPerformed
    // TODO add your handling code here:
    RealizarProformaSoat("01");
}//GEN-LAST:event_cmdSOATActionPerformed

private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem35ActionPerformed

private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
    // TODO add your handling code here:
    RecuperaSoat();
}//GEN-LAST:event_jMenuItem36ActionPerformed

private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jMenu10ActionPerformed

private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
    // TODO add your handling code here:

    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(74);
            if (resul) {
                //Habilita(false, cmdHerra2);
                marcacdo = true;

                Reporte_Arqueo_Caja objArqueo = new Reporte_Arqueo_Caja(this);
                jDesktopPane1.add(objArqueo);
                objArqueo.setLocation(objArqueo.getParent().getWidth() / 2 - objArqueo.getWidth() / 2, objArqueo.getParent().getHeight() / 2 - objArqueo.getHeight() / 2);
                objArqueo.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }

}//GEN-LAST:event_jMenuItem37ActionPerformed

private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
    // TODO add your handling code here:

    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(75);
            if (resul) {
                //Habilita(false, cmdHerra2);
                marcacdo = true;
                Reporte_Arqueo_CajaDetallado objArqueoDetllado = new Reporte_Arqueo_CajaDetallado(this);
                jDesktopPane1.add(objArqueoDetllado);
                objArqueoDetllado.setLocation(objArqueoDetllado.getParent().getWidth() / 2 - objArqueoDetllado.getWidth() / 2, objArqueoDetllado.getParent().getHeight() / 2 - objArqueoDetllado.getHeight() / 2);
                objArqueoDetllado.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem38ActionPerformed

private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
    // TODO add your handling code here:

    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(54);
            if (resul) {
                marcacdo = true;
                FomCuadreGastos objeto = new FomCuadreGastos(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenu3ActionPerformed

private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
    // TODO add your handling code here:
    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(76);
            if (resul) {
                marcacdo = true;
                Reporte_EnvioEfectivoOficina objeto = new Reporte_EnvioEfectivoOficina(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem39ActionPerformed

private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
    // TODO add your handling code here:

    try {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(77);
            if (resul) {
                marcacdo = true;
                ConsultarEnvioEfecOfi objeto = new ConsultarEnvioEfecOfi(this);
                jDesktopPane1.add(objeto);
                objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
                objeto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_jMenuItem40ActionPerformed

private void cmdHerra3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHerra3ActionPerformed
    ConsultarVenta();
}//GEN-LAST:event_cmdHerra3ActionPerformed

private void cmdSecc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSecc2ActionPerformed
    // TODO add your handling code here:
    RealizaNotaDebito();
}//GEN-LAST:event_cmdSecc2ActionPerformed

private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jMenu8ActionPerformed

private void jMenuItem24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem24KeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem24KeyPressed

private void jMenuItem24KeyPressed1(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem24KeyPressed1
    // TODO add your handling code here:
}//GEN-LAST:event_jMenuItem24KeyPressed1

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        // TODO add your handling code here:
        ultimaventa();
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        // TODO add your handling code here:
        boolean cargar = CargarPromos(25);
        if (cargar){
            JOptionPane.showMessageDialog(this, " ACTUALIZACION COMPLETA ", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void efectivosiguienteturno(){
        if(marcacdo == false){
            marcacdo = true;
            TrasladoEfectivo f = new TrasladoEfectivo();
            jDesktopPane1.add(f);
            f.setLocation(f.getParent().getWidth() / 2 - f.getWidth() / 2, f.getParent().getHeight() / 2 - f.getHeight() / 2);
            f.setVisible(true);
        }
    }
    
    private void ultimaventa() {
        if (marcacdo == false) {
            marcacdo = true;
            UltimaVenta f = new UltimaVenta();
            jDesktopPane1.add(f);
            f.setLocation(f.getParent().getWidth() / 2 - f.getWidth() / 2, f.getParent().getHeight() / 2 - f.getHeight() / 2);
            f.setVisible(true);
        }
    }

    private void Ventas_Delivery() {
        if (marcacdo == false) {
            marcacdo = true;
            FormVentaMotorizado objpago = new FormVentaMotorizado(idbotica, this);
            jDesktopPane1.add(objpago);
            objpago.setLocation(objpago.getParent().getWidth() / 2 - objpago.getWidth() / 2, objpago.getParent().getHeight() / 2 - objpago.getHeight() / 2);
            objpago.setVisible(true);
        }
    }

    private void GenerarPago() {
        try {

            PagoClientes objpago;

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(26);
                if (resul) {
                    marcacdo = true;
                    objpago = new PagoClientes(idbotica, this);
                    abrirVentanaInterna(objpago);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void CerrarCaja() {
        CerrarBotica objcierre;

        try {
            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(24);

                if (resul) {
                    marcacdo = true;
                    objcierre = new CerrarBotica(objetoventana, this);
                    objcierre.pack();
                    objcierre.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void Consulta_Caja() {

        ConsultaCaja objCierreBotica;

        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(24);

                if (resul) {
                    Habilita(false, cmdHerra1);
                    marcacdo = true;
                    objCierreBotica = new ConsultaCaja(this);
                    jDesktopPane1.add(objCierreBotica);
                    objCierreBotica.setLocation(objCierreBotica.getParent().getWidth() / 2 - objCierreBotica.getWidth() / 2, objCierreBotica.getParent().getHeight() / 2 - objCierreBotica.getHeight() / 2);
                    objCierreBotica.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void AperturaBotica() {

        BoticasApertura objBoticasApertura;

        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(21);

                if (resul) {
                    Habilita(false, cmdPresta1);
                    if (logbotica.Verifica_Cierre_Botica(idbotica) == 0) {
                        marcacdo = true;
                        objBoticasApertura = new BoticasApertura(objetoventana, this);
                        objBoticasApertura.pack();
                        objBoticasApertura.setVisible(true);
                    } else {
                        int reply = JOptionPane.showConfirmDialog(null, "ERROR : EL DIA ANTERIOR NO SE REALIZO EL CIERRE DE BOTICA  \n  "
                                + "PORFAVOR CONFIRMAR PARA REALIZAR SU CIERRE RESPECTIVO ", "NORTFARMA", JOptionPane.YES_OPTION);

                        try {
                            if (reply == JOptionPane.YES_OPTION) {
                                LogicaConfiguracion objConfig = new LogicaConfiguracion();
                                MailClient objmail = new MailClient();
                                objConfig.GenerarBackup(idbotica);
                                String correo = logbotica.ReornaCorreo(idbotica);
                                objmail.sendMail(correo, "CIERRE DE BOTICA " + idbotica + "", "SE HA REALIZADO EL CIERRE DE BOTICA EN  FELICIDAD " + idbotica + " \n PORFAVOR REVISAR LA INFORMACION DE LOS surf01,surf02,surf10,surf31,surf32 ");
                                JOptionPane.showMessageDialog(this, " EL CIERRE DE BOTICA FUE REALIZADO ", "CIERRE DE BOTICA", JOptionPane.INFORMATION_MESSAGE);
                                Habilita(true);
                            } else {
                                Habilita(true);
                            }

                        } catch (Exception ex) {
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private boolean CargarPromos(int valorsubmenu) {
        boolean permiso = false;
        //int cant;
        //credenciales.retainAll(credenciales);
        //credenciales = login.getUsuariobotica();
        LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
        objcajas.EJECUTAPROMO();

        /*for (cant = 0; cant < credenciales.size(); cant++) {
            int idpagina = credenciales.get(cant).getIdpagina();
            if (idpagina == valorsubmenu) {
                cant = credenciales.size();
                permiso = true;
            }
        }*/
        permiso = true;
        return permiso;
    }
    
    private boolean VerifivaCredenciales(int valorsubmenu) {
        boolean permiso = false;
        int cant;
        credenciales.retainAll(credenciales);
        credenciales = login.getUsuariobotica();

        for (cant = 0; cant < credenciales.size(); cant++) {
            int idpagina = credenciales.get(cant).getIdpagina();
            if (idpagina == valorsubmenu) {
                cant = credenciales.size();
                permiso = true;
            }
        }

        return permiso;
    }

    private void Habilita(boolean valor, JButton boton) {
        cmdPresta.setEnabled(valor);
        cmdPresta1.setEnabled(valor);
        cmdHerra1.setEnabled(valor);
        cmdAyuda1.setEnabled(valor);
        cmdDepa.setEnabled(valor);
        cmdHerra.setEnabled(valor);
        cmdHerra2.setEnabled(valor);
        cmdHerra3.setEnabled(valor);
        cmdSecc.setEnabled(valor);
        cmdSecc1.setEnabled(valor);
        cmdSecc2.setEnabled(valor);
        cmdAyuda.setEnabled(valor);
        cmdDepa1.setEnabled(valor);
        cmdSOAT.setEnabled(valor);
        boton.setEnabled(!valor);
    }

    public void Habilita(boolean valor) {
        cmdPresta.setEnabled(valor);
        cmdPresta1.setEnabled(valor);
        cmdHerra1.setEnabled(valor);
        cmdAyuda1.setEnabled(valor);
        cmdDepa.setEnabled(valor);
        cmdHerra.setEnabled(valor);
        cmdHerra2.setEnabled(valor);
        cmdHerra3.setEnabled(valor);
        cmdSecc.setEnabled(valor);
        cmdSecc1.setEnabled(valor);
        cmdSecc2.setEnabled(valor);
        cmdAyuda.setEnabled(valor);
        cmdDepa1.setEnabled(valor);
        cmdSOAT.setEnabled(valor);
    }

    @Action
    public void RealizarProforma(String op) {

        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(11);
                if (resul) {
                    Habilita(false, cmdPresta);
                    objcotizacion = new Cotizacion(op, this, objsesion);
                    abrirVentanaInterna(objcotizacion);
                    marcacdo = true;
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                objcotizacion.requestFocus();
            }

        } catch (Exception ex) {
        }
    }

    @Action
    public void RealizarProforma1(String op) {

        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(19);
                if (resul) {
                    Habilita(false, cmdAyuda1);
                    objcotizacion = new Cotizacion(op, this, objsesion);
                    abrirVentanaInterna(objcotizacion);
                    marcacdo = true;
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
        }
    }

    @Action
    public void RealizarProformaSoat(String op) {
        try {
            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(68);
                if (resul) {
                    Habilita(false, cmdSOAT);
                    marcacdo = true;
                    objcotizasoat = new CotizacionSoat(this);
                    abrirVentanaInterna(objcotizasoat);

                    marcacdo = true;
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    @Action
    public void RecuperaSoat() {
        try {
            if (marcacdo == false) {

                boolean resul = VerifivaCredenciales(69);
                if (resul) {

                    marcacdo = true;
                    objRecuperasoat = new Recuperavtasoat(this);
                    jDesktopPane1.add(objRecuperasoat);
                    objRecuperasoat.setLocation(objRecuperasoat.getParent().getWidth() / 2 - objRecuperasoat.getWidth() / 2, objRecuperasoat.getParent().getHeight() / 2 - objRecuperasoat.getHeight() / 2);
                    objRecuperasoat.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void GenerarVenta1() {
        int idcaja = 0;

        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(13);

                if (resul) {

                    Habilita(false, cmdDepa);
                    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
                    List<Cajas> listapersonal = objcajas.VERIFICA_CAJAS_USUARIOS(idbotica, id_personal_botica);

                    if (listapersonal.size() == 1) {
                        idcaja = listapersonal.get(0).getIdcaja();
                        Delivery = listapersonal.get(0).getEsDelivery();
                        MarcadoDelivery = listapersonal.get(0).getMarcadoDelivery();
                        setDelivery(Delivery);
                        setMarcadoDelivery(MarcadoDelivery);
                    } else {
                        if (listapersonal.size() > 1) {
                            SeleccionaCaja selecaja = new SeleccionaCaja(objetoventana, idbotica, listapersonal);
                            selecaja.show(true);
                            idcaja = selecaja.getIdcaja();
                            Delivery = selecaja.getDelivery();
                            MarcadoDelivery = selecaja.getMarcaDelivery();
                            setDelivery(Delivery);
                            setMarcadoDelivery(MarcadoDelivery);
                        }
                    }

                    if (idcaja > 0) {
                        boolean apertura = objcaja.CajaApertura(idbotica, idcaja, id_personal_botica);

                        if (apertura) {
                            objventa = new RealizaVenta(this, idcaja);
                            abrirVentanaInterna(objventa);
                            marcacdo = true;
                        } else {
                            Habilita(true);
                            JOptionPane.showMessageDialog(this, "LO SENTIMOS A UN NO SE HA APERTURADO SU CAJA \n PORFAVOR INGRESE A APERTURAR SU CAJA ", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        Habilita(true);
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A CAJA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ModificaProforma() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(18);
            if (resul) {
                marcacdo = true;
                objmuestraproforma = new MuestraProformas(this);
                jDesktopPane1.add(objmuestraproforma);
                objmuestraproforma.setLocation(objmuestraproforma.getParent().getWidth() / 2 - objmuestraproforma.getWidth() / 2, objmuestraproforma.getParent().getHeight() / 2 - objmuestraproforma.getHeight() / 2);
                objmuestraproforma.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void RealizaNotaCredito() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(20);
            if (resul) {
                Habilita(false, cmdSecc);
                objnotaCredito1 = new NotaCredito1(this);
                abrirVentanaInterna(objnotaCredito1);
                marcacdo = true;
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void RealizaNotaDebito() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(80);
            if (resul) {
                Habilita(false, cmdSecc2);
                objnotaDebito = new NotaDebito(this);
                abrirVentanaInterna(objnotaDebito);
                marcacdo = true;
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void PermisoRealizaNotaCreditoCajero() {
        if (marcacdo == false) {
            //boolean resul = VerifivaCredenciales(20);
            boolean resul = true;
            if (resul) {
                Habilita(false, cmdSecc);
                objnotaCredito1 = new NotaCredito1(this);
                abrirVentanaInterna(objnotaCredito1);
                marcacdo = true;
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void MuestraReportes() {
        try {
            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(30);
                if (resul) {
                    Habilita(false, cmdSecc1);
                    objreporte = new OpcionesReportes(this);
                    abrirVentanaInterna(objreporte);
                    marcacdo = true;
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void CambioUsuario() {
        Habilita(false, cmdDepa1);
        UsuarioClave objcamb = new UsuarioClave(objetoventana, 2, this);
        objcamb.show(true);

        try {

            if (objcamb.getUsuario().compareTo("") != 0 && objcamb.getClave().compareTo("") != 0) {
                obj = new Personal();
                obj.setDNI(objcamb.getUsuario());
                obj.setContrasena(objcamb.getClave());
                obj.setId_botica(idbotica);

                if (logicapersonal.Verificausuario(obj).size() > 0) {
                    credenciales.removeAll(credenciales);
                    login.setUsuariobotica(null);
                    credenciales = logicapersonal.Verificausuario(obj);
                    login.setId_rol(credenciales.get(0).getIdrol());
                    login.setUsuariobotica(credenciales);
                    recuperaUsuario();
                    jTextField3.setText(this.getUsuario());
                    jTextField4.setText(this.getCargo());
                    Habilita(true);
                } else {
                    Habilita(true);
                    JOptionPane.showMessageDialog(this, " USUARIO O CLAVE INCORRECTA ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            Habilita(true);
            System.out.println(ex.getMessage());
        }
    }

    private void RevisarMercaderia() {
        try {

            if (marcacdo == false) {
                boolean resul = VerifivaCredenciales(53);
                if (resul) {
                    ForMSalida_Producto miobj = new ForMSalida_Producto(this);
                    abrirVentanaInterna(miobj);
                    marcacdo = true;
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ActivaProforma() {
        ActivarProforma activa = new ActivarProforma(objetoventana, idbotica);
        activa.show(true);
    }

    @Action
    public void Cerrar() {
        CerrarSesion();
    }

    @Action
    public void RealizaProforma() {
        RealizarProforma("01");
    }

    @Action
    public void RealizarProformaSoat() {
        RealizarProformaSoat("01");
    }

    @Action
    public void ModificaProfrom() {
        ModificaProforma();
    }

    @Action
    public void Cerrar1() {
        CerrarSesion();
    }

    @Action
    public void VenBotiquin() {
        RealizarProforma1("02");
    }

    @Action
    public void NotaCredito() {
        RealizaNotaCredito();
    }

    @Action
    public void GeneraVenta() {
        GenerarVenta1();
    }

    @Action
    public void ReporteCaja() {
        MuestraReportes();
    }

    @Action
    public void CambiarDeUsuario() {
        CambioUsuario();
    }

    @Action
    public void ActivarProforma() {
        ActivaProforma();
    }

    @Action
    public void Revisar() {
        RevisarMercaderia();
    }

    @Action
    public void RealizaNuevaProforma() {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAyuda;
    private javax.swing.JButton cmdAyuda1;
    private javax.swing.JButton cmdDepa;
    private javax.swing.JButton cmdDepa1;
    private javax.swing.JButton cmdHerra;
    private javax.swing.JButton cmdHerra1;
    private javax.swing.JButton cmdHerra2;
    private javax.swing.JButton cmdHerra3;
    private javax.swing.JButton cmdPresta;
    private javax.swing.JButton cmdPresta1;
    private javax.swing.JButton cmdSOAT;
    private javax.swing.JButton cmdSecc;
    private javax.swing.JButton cmdSecc1;
    private javax.swing.JButton cmdSecc2;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator22;
    private javax.swing.JToolBar.Separator jSeparator23;
    private javax.swing.JToolBar.Separator jSeparator26;
    private javax.swing.JToolBar.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField1;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField2;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField3;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField4;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    // End of variables declaration//GEN-END:variables

    private void Reporte_EnvioEfectivoOficina(AplicacionVentas aThis) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
