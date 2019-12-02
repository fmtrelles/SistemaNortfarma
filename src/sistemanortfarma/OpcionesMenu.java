package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCPersonal;
import entidad.Personal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entidad.UsuarioBotica;
import javax.swing.ImageIcon;

public class OpcionesMenu extends javax.swing.JFrame implements KeyListener {

    login onjlogin;
    private static int idpersonal_botica;
    private static String idbotica;
    Personal opersonal;
    LogicaBoticas objBoticas=new LogicaBoticas();
    public static List<UsuarioBotica> usuariobotica = new ArrayList<UsuarioBotica>();
    LogicaCPersonal logPersonal = new LogicaCPersonal();
    private static AplicacionVentas objventas = null;
    private static Administracion objadministra = null;
    private static Inventarios objinventarios = null;
    private static AplicacionPersonal objpersonal = null;
    private static String impresora_Boleta = null;
    private static String impresora_Factura = null;
    private static String impresora_Descargo = null;
    private static int cantidadDecimales = 4;
    private static int cantidadDecimalesPantalla = 2;
    public static int configBotica = 0;
    Mant_Productos mantproduc;
    private static String correoDestino;

    public OpcionesMenu(String fecha) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/user_edit.png")).getImage());
        addKeyListener(this);
        setLocationRelativeTo(null);
        setUsuariobotica(null);
        opersonal = onjlogin.getObj();
        idbotica = onjlogin.getIdbotica();
        usuariobotica = onjlogin.getUsuariobotica();
        VerificaBotica();
        VerificaImpresora();
        jLabel11.setText("     ");
        jLabel14.setText(usuariobotica.get(0).getApellido() + "  " + usuariobotica.get(0).getNombre());
        jLabel22.setText(fecha);
        mantproduc = new Mant_Productos();
        CargarCorreos();
        setCantidadDecimales(mantproduc.cantidadDecimales());
        setCantidadDecimalesPantalla(mantproduc.CantidadDecimalesPantalla());
    }

    public OpcionesMenu(int op) {
        initComponents();
        setLocationRelativeTo(null);
    }

    public static int getConfigBotica() {
        return configBotica;
    }

    public static void setConfigBotica(int configBotica) {
        OpcionesMenu.configBotica = configBotica;
    }

    public static int getCantidadDecimales() {
        return cantidadDecimales;
    }

    public static void setCantidadDecimales(int cantidadDecimales) {
        OpcionesMenu.cantidadDecimales = cantidadDecimales;
    }

    public static int getCantidadDecimalesPantalla() {
        return cantidadDecimalesPantalla;
    }

    public static void setCantidadDecimalesPantalla(int CantidadDecimalesPantalla) {
        OpcionesMenu.cantidadDecimalesPantalla = CantidadDecimalesPantalla;
    }
    
    public static String getImpresora_Factura() {
        return impresora_Factura;
    }

    public static void setImpresora_Factura(String impresora_Factura) {
        OpcionesMenu.impresora_Factura = impresora_Factura;
    }

    public static String getImpresora_Descargo() {
        return impresora_Descargo;
    }

    public static void setImpresora_Descargo(String impresora_Descargo) {
        OpcionesMenu.impresora_Descargo = impresora_Descargo;
    }


    public static String getImpresora_Boleta() {
        return impresora_Boleta;
    }

    public static void setImpresora_Boleta(String impresora) {
        OpcionesMenu.impresora_Boleta = impresora;
    }

    public static int getIdpersonal_botica() {
        return idpersonal_botica;
    }

    public static void setIdpersonal_botica(int idpersonal_botica) {
        OpcionesMenu.idpersonal_botica = idpersonal_botica;
    }

    public static List<UsuarioBotica> getUsuariobotica() {
        return usuariobotica;
    }

    public static void setUsuariobotica(List<UsuarioBotica> usuariobotica) {
        OpcionesMenu.usuariobotica = usuariobotica;
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        OpcionesMenu.idbotica = idbotica;
    }

    public static String getCorreoDestino() {
        return correoDestino;
    }

    public static void setCorreoDestino(String correoDestino) {
        OpcionesMenu.correoDestino = correoDestino;
    }

    private void VerificaImpresora() {
        List<String> listaImpresoras = logPersonal.VerificaImpresora();

        try {
            impresora_Boleta = listaImpresoras.get(0);
            impresora_Factura = listaImpresoras.get(1);
            impresora_Descargo = listaImpresoras.get(2);
            configBotica = Integer.parseInt(listaImpresoras.get(3));
        } catch (Exception ex) {
            System.out.println("error de impresora :" + ex.getMessage());
        }

        setImpresora_Boleta(impresora_Boleta);
        setImpresora_Factura(impresora_Factura);
        setImpresora_Descargo(impresora_Descargo);
        setConfigBotica(configBotica);
    }

    private void CargarCorreos() {
        correoDestino = objBoticas.Retorna_Correodestino(idbotica);
        setCorreoDestino(correoDestino);
    }

    private void IngresoPersonal() {
        if (VerificaAccesoModulo(66)) {
            this.hide();
            if (objpersonal == null) {
                objpersonal = new AplicacionPersonal(this);
                objpersonal.show(true);
            } else {
                objpersonal.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(SistemaNortfarmaApp.getApplication().getMainFrame(), " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO", "Error de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void IngresoVentas() {
        if (VerificaAccesoModulo(1)) {
            this.hide();
            if (objventas == null) {
                objventas = new AplicacionVentas(this);
                objventas.show(true);
            } else {
                objventas.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(SistemaNortfarmaApp.getApplication().getMainFrame(), " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO", "Error de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void IngresoAdministracion() {
        if (VerificaAccesoModulo(2)) {
            hide();
            if (objadministra == null) {
                objadministra = new Administracion(this);
                objadministra.show(true);
            } else {
                objadministra.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(SistemaNortfarmaApp.getApplication().getMainFrame(), " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO", "Error de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void IngresoInventario() {
        if (VerificaAccesoModulo(3)) {
            hide();
            if (objinventarios == null) {
                objinventarios = new Inventarios(this);
                objinventarios.show(true);
            } else {
                objinventarios.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(SistemaNortfarmaApp.getApplication().getMainFrame(), " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO", "Error de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        toolTipLlamada1 = new org.edisoncor.gui.toolTip.ToolTipLlamada();
        toolTipLlamada2 = new org.edisoncor.gui.toolTip.ToolTipLlamada();
        toolTipLlamada3 = new org.edisoncor.gui.toolTip.ToolTipLlamada();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        toolTipLlamada4 = new org.edisoncor.gui.toolTip.ToolTipLlamada();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(OpcionesMenu.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel1.border.lineColor"), 5, true)); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jToolBar1.add(jLabel10);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jToolBar1.add(jLabel11);

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jToolBar1.add(jLabel12);

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel15.setIcon(resourceMap.getIcon("jLabel15.icon")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jLabel15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLabel15FocusLost(evt);
            }
        });

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel17.setIcon(resourceMap.getIcon("jLabel17.icon")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel19.setIcon(resourceMap.getIcon("jLabel19.icon")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jSeparator3.setName("jSeparator3"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        toolTipLlamada1.setFont(resourceMap.getFont("toolTipLlamada1.font")); // NOI18N
        toolTipLlamada1.setName("toolTipLlamada1"); // NOI18N
        toolTipLlamada1.setTipText(resourceMap.getString("toolTipLlamada1.tipText")); // NOI18N

        javax.swing.GroupLayout toolTipLlamada1Layout = new javax.swing.GroupLayout(toolTipLlamada1);
        toolTipLlamada1.setLayout(toolTipLlamada1Layout);
        toolTipLlamada1Layout.setHorizontalGroup(
            toolTipLlamada1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        toolTipLlamada1Layout.setVerticalGroup(
            toolTipLlamada1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        toolTipLlamada2.setFont(resourceMap.getFont("toolTipLlamada2.font")); // NOI18N
        toolTipLlamada2.setName("toolTipLlamada2"); // NOI18N
        toolTipLlamada2.setTipText(resourceMap.getString("toolTipLlamada2.tipText")); // NOI18N

        javax.swing.GroupLayout toolTipLlamada2Layout = new javax.swing.GroupLayout(toolTipLlamada2);
        toolTipLlamada2.setLayout(toolTipLlamada2Layout);
        toolTipLlamada2Layout.setHorizontalGroup(
            toolTipLlamada2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        toolTipLlamada2Layout.setVerticalGroup(
            toolTipLlamada2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        toolTipLlamada3.setFont(resourceMap.getFont("toolTipLlamada2.font")); // NOI18N
        toolTipLlamada3.setName("toolTipLlamada3"); // NOI18N
        toolTipLlamada3.setTipText(resourceMap.getString("toolTipLlamada3.tipText")); // NOI18N

        javax.swing.GroupLayout toolTipLlamada3Layout = new javax.swing.GroupLayout(toolTipLlamada3);
        toolTipLlamada3.setLayout(toolTipLlamada3Layout);
        toolTipLlamada3Layout.setHorizontalGroup(
            toolTipLlamada3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        toolTipLlamada3Layout.setVerticalGroup(
            toolTipLlamada3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel20.setIcon(resourceMap.getIcon("jLabel20.icon")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        toolTipLlamada4.setFont(resourceMap.getFont("toolTipLlamada4.font")); // NOI18N
        toolTipLlamada4.setName("toolTipLlamada4"); // NOI18N
        toolTipLlamada4.setTipText(resourceMap.getString("toolTipLlamada4.tipText")); // NOI18N

        javax.swing.GroupLayout toolTipLlamada4Layout = new javax.swing.GroupLayout(toolTipLlamada4);
        toolTipLlamada4.setLayout(toolTipLlamada4Layout);
        toolTipLlamada4Layout.setHorizontalGroup(
            toolTipLlamada4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        toolTipLlamada4Layout.setVerticalGroup(
            toolTipLlamada4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toolTipLlamada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toolTipLlamada4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(toolTipLlamada3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toolTipLlamada2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(290, 290, 290)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel22)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolTipLlamada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(toolTipLlamada4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolTipLlamada3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabel19)
                    .addComponent(toolTipLlamada2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        IngresoVentas();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        IngresoPersonal();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        IngresoInventario();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel15FocusLost
    }//GEN-LAST:event_jLabel15FocusLost

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
       IngresoAdministracion();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void VerificaBotica() {
        try {
            idpersonal_botica = usuariobotica.get(0).getId_Personal();
            setIdpersonal_botica(idpersonal_botica);
            jLabel4.setText(idbotica);
        } catch (Exception ex) {
            System.out.println("VerificaBotica " + ex.toString());
        }

    }

    private boolean VerificaAccesoModulo(int op) {

        boolean band = false;
        int idpagina = 0;

        usuariobotica = login.getUsuariobotica();
        setUsuariobotica(usuariobotica);
        VerificaBotica();

        if (usuariobotica.size() > 0) {
            for (int i = 0; i < usuariobotica.size(); i++) {
                idpagina = usuariobotica.get(i).getIdpagina();
                if (idpagina == op) {
                    band = true;
                    i = usuariobotica.size();
                }
            }
        }
        return band;
    }

    public void keyPressed(KeyEvent e) {
        Integer modulo = 0;

        switch (e.getKeyCode()) {
            case 86:
                modulo = 1;
                break;
            case 65:
                modulo = 2;
                break;
            case 73:
                modulo = 3;
                break;
            case 80:
                modulo = 4;
                break;
            case 84:
                System.exit(0);
        }

        if (VerificaAccesoModulo(modulo)) {
            this.hide();
            switch (modulo) {
                case 1:
                    IngresoVentas();
                    break;
                case 2:
                    IngresoAdministracion();
                    break;
                case 3:
                    IngresoInventario();
                    break;
                case 4:
                    IngresoPersonal();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(SistemaNortfarmaApp.getApplication().getMainFrame(), " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO", "Error de sesion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void keyReleased(KeyEvent e) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private org.edisoncor.gui.toolTip.ToolTipLlamada toolTipLlamada1;
    private org.edisoncor.gui.toolTip.ToolTipLlamada toolTipLlamada2;
    private org.edisoncor.gui.toolTip.ToolTipLlamada toolTipLlamada3;
    private org.edisoncor.gui.toolTip.ToolTipLlamada toolTipLlamada4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

   
}
