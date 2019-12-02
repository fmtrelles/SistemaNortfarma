package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import entidad.UsuarioBotica;
import java.beans.PropertyVetoException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Inventarios extends javax.swing.JFrame {

    DescuentosInventarios objDescuentosInv;
    CrearHojaDeTrabajo objHojaDeTrabajo;
    InformeInv objInformeInv;
    GuiaDeAjustes objGuiaAjustes;
    CruceInventarios objCruce;
    Cargos objCargosDescargos;
    Descargos objDescargos;
    Descargos_Est objDescargosBoticas;
    Descargos_Cli objDescargosClientes;
    DescargosTrastienda objDescargosT;
    RegenerarDescargos objRegenerarDescargos;
    CargosInventarios objCargosInventarios;
    Descargos_Adicionales objDescargosAdicionales;
    ImprimeDescargosBoticas objDesBoticas;
    AnularDescargosClientesAdicionales objAnulaDesClientesAdicionales;
    DescargosInventarios objDesCargosInventarios;
    ProductosSAP objDesProductosSAP;
    CargarGuiaDBF objCargar;
    CargarEnvio3 objCargarEnvio;
    Anulaciones objAnulaciones;
    AnulacionCargosDescargos objACD;
    ReportesInventarios objReportesInventarios;
    public Integer vecesAbierto;
    public Integer vecesAbiertoA;
    public static boolean marcacdo = false;
    private static List<UsuarioBotica> credenciales = new ArrayList<UsuarioBotica>();
    MuestraVentana objetoventana = new MuestraVentana();
    LogicaPersonal objPersonal = new LogicaPersonal();
    private static String usuario;
    private static String cargo;
    private static String idbotica;
    private static int idpesonal;
    private static Date fecha;
    private static Time Hora;
    LogicaFechaHora objlogiccafecha = new LogicaFechaHora();
    OpcionesMenu objmenu;
    LogicaBoticas objBoticas = new LogicaBoticas();

    public Inventarios(OpcionesMenu obj) {
        initComponents();
        objmenu = obj;
        credenciales = objmenu.getUsuariobotica();
        idbotica = objmenu.getIdbotica();
        setIdbotica(idbotica);
        recuperaUsuario();
        RecuperaData();
        vecesAbierto = 0;
        vecesAbiertoA = 0;
        this.setExtendedState(Inventarios.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(0);
        this.jButton12.setVisible(true);
    }

    public static Time getHora() {
        return Hora;
    }

    public static void setHora(Time Hora) {
        Inventarios.Hora = Hora;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        Inventarios.fecha = fecha;
    }

    public static String getCargo() {
        return cargo;
    }

    public static void setCargo(String cargo) {
        Inventarios.cargo = cargo;
    }

    public static String getIdbotica() {
        return idbotica;
    }

    public static void setIdbotica(String idbotica) {
        Inventarios.idbotica = idbotica;
    }

    public static int getIdpesonal() {
        return idpesonal;
    }

    public static void setIdpesonal(int idpesonal) {
        Inventarios.idpesonal = idpesonal;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Inventarios.usuario = usuario;
    }

    private void recuperaUsuario() {
        try {
            usuario = credenciales.get(0).getApellido() +" "+ credenciales.get(0).getNombre();
            cargo = credenciales.get(0).getDescrip_roles();
            idpesonal = credenciales.get(0).getId_Personal();
            idbotica = objmenu.getIdbotica();
            setCargo(cargo);
            setUsuario(usuario);
            setIdbotica(idbotica);
            setIdpesonal(idpesonal);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void RecuperaData() {
        try {
            fecha = objlogiccafecha.RetornaFecha();
            Hora = Time.valueOf(objlogiccafecha.RetornaHora());
            setFecha(fecha);
            setHora(Hora);

            this.jTextField2.setText(Hora.toString());
            this.jTextField1.setText(fecha.toString());
            this.jTextField3.setText(credenciales.get(0).getApellido() + " " + credenciales.get(0).getNombre());
            this.jTextField4.setText(cargo);

        } catch (Exception ex) {
        }

    }

    private boolean VerifivaCredenciales(int valorsubmenu) {
        boolean permiso = false;
        int cant;

        credenciales = login.getUsuariobotica();

        try {
            for (cant = 0; cant < credenciales.size(); cant++) {
                int idpagina = credenciales.get(cant).getIdpagina();
                if (idpagina == valorsubmenu) {
                    cant = credenciales.size();
                    permiso = true;
                }
            }
        } catch (Exception ex) {
        }

        if (permiso == true) {
            objPersonal.TrackingUser(valorsubmenu, OpcionesMenu.getIdpersonal_botica(), 1);
        } else {
            objPersonal.TrackingUser(valorsubmenu, OpcionesMenu.getIdpersonal_botica(), 0);
        }


        return permiso;
    }

    private void CerrarVentana() {
        Habilita(false, jButton3);
        int resul = JOptionPane.showConfirmDialog(this, "DESA SALIR DEL SISTEMA ?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);

        if (resul == 0) {
            marcacdo = false;
            this.hide();
            Habilita(true);
            objmenu.setVisible(true);
        } else {
            Habilita(true);

        }
    }

    private void Habilita(boolean valor, JButton boton) {
        jButton1.setEnabled(valor);
        jButton2.setEnabled(valor);
        jButton5.setEnabled(valor);
        jButton4.setEnabled(valor);
        jButton3.setEnabled(valor);
        jButton6.setEnabled(valor);
        jButton7.setEnabled(valor);
        jButton8.setEnabled(valor);
        jButton9.setEnabled(valor);
        jButton10.setEnabled(valor);
        jButton11.setEnabled(valor);
        boton.setEnabled(!valor);
    }

    public void Habilita(boolean valor) {
        jButton1.setEnabled(valor);
        jButton2.setEnabled(valor);
        jButton5.setEnabled(valor);
        jButton4.setEnabled(valor);
        jButton3.setEnabled(valor);
        jButton6.setEnabled(valor);
        jButton7.setEnabled(valor);
        jButton8.setEnabled(valor);
        jButton9.setEnabled(valor);
        jButton10.setEnabled(valor);
        jButton11.setEnabled(valor);
    }

    private void CargarGuia() {
        if (marcacdo == false) {

            boolean resul = VerifivaCredenciales(15);
            if (resul) {
                if (objBoticas.VerificaBoticaCerrada()) {
                    Habilita(false, jButton1);
                    marcacdo = true;
                    objCargar = new CargarGuiaDBF(objetoventana, this);
                    desktopPane.add(objCargar);
                    objCargar.setLocation(objCargar.getParent().getWidth() / 2 - objCargar.getWidth() / 2, objCargar.getParent().getHeight() / 2 - objCargar.getHeight() / 2);
                    objCargar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Cargos() {

        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {
                boolean resul = VerifivaCredenciales(16);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton2);
                        marcacdo = true;
                        objCargosDescargos = new Cargos(objetoventana, this, objmenu);
                        desktopPane.add(objCargosDescargos);
                        objCargosDescargos.setLocation(objCargosDescargos.getParent().getWidth() / 2 - objCargosDescargos.getWidth() / 2, objCargosDescargos.getParent().getHeight() / 2 - objCargosDescargos.getHeight() / 2);
                        objCargosDescargos.setVisible(true);
                    } else {
                        Habilita(true);
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Mostrar() {
        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(15);
            if (resul) {
                Habilita(false, jButton4);
                objReportesInventarios = new ReportesInventarios(this, objmenu);
                abrirVentanaInterna(objReportesInventarios);
                marcacdo = true;
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Descargoss() {
        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {

                boolean resul = VerifivaCredenciales(16);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton5);
                        marcacdo = true;
                        
                        /*objDescargos = new Descargos(objetoventana, this, objmenu);                        
                        desktopPane.add(objDescargos);
                        objDescargos.setLocation(objDescargos.getParent().getWidth() / 2 - objDescargos.getWidth() / 2, objDescargos.getParent().getHeight() / 2 - objDescargos.getHeight() / 2);
                        objDescargos.setVisible(true);
                        */
                        
                        objDescargosBoticas = new Descargos_Est(objetoventana, this, objmenu,1);
                        desktopPane.add(objDescargosBoticas);
                        objDescargosBoticas.setLocation(objDescargosBoticas.getParent().getWidth() / 2 - objDescargosBoticas.getWidth() / 2, objDescargosBoticas.getParent().getHeight() / 2 - objDescargosBoticas.getHeight() / 2);
                        objDescargosBoticas.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void Descargoscli() {
        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {

                boolean resul = VerifivaCredenciales(16);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton5);
                        marcacdo = true;                                                
                        
                        objDescargosClientes = new Descargos_Cli(objetoventana, this, objmenu);
                        desktopPane.add(objDescargosClientes);
                        objDescargosClientes.setLocation(objDescargosClientes.getParent().getWidth() / 2 - objDescargosClientes.getWidth() / 2, objDescargosClientes.getParent().getHeight() / 2 - objDescargosClientes.getHeight() / 2);
                        objDescargosClientes.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void DescargosTrastienda() {
        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {

                boolean resul = VerifivaCredenciales(81);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton11);
                        marcacdo = true;
                        objDescargosT = new DescargosTrastienda(objetoventana, this, objmenu);
                        desktopPane.add(objDescargosT);
                        objDescargosT.setLocation(objDescargosT.getParent().getWidth() / 2 - objDescargosT.getWidth() / 2, objDescargosT.getParent().getHeight() / 2 - objDescargosT.getHeight() / 2);
                        objDescargosT.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    

    private void RegenerarDescargoss() {
        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {

                boolean resul = VerifivaCredenciales(72);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton5);
                        marcacdo = true;
                        objRegenerarDescargos = new RegenerarDescargos(objetoventana, this, objmenu);
                        desktopPane.add(objRegenerarDescargos);
                        objRegenerarDescargos.setLocation(objRegenerarDescargos.getParent().getWidth() / 2 - objRegenerarDescargos.getWidth() / 2, objRegenerarDescargos.getParent().getHeight() / 2 - objRegenerarDescargos.getHeight() / 2);
                        objRegenerarDescargos.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void DescargosAdicionales() {
        if (marcacdo == false) {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 1) != 1) {

                boolean resul = VerifivaCredenciales(16);

                if (resul) {
                    if (objBoticas.VerificaBoticaCerrada()) {
                        Habilita(false, jButton5);
                        marcacdo = true;
                                               
                        
                        objDescargosAdicionales = new Descargos_Adicionales(objetoventana, this, objmenu,1);
                        desktopPane.add(objDescargosAdicionales);
                        objDescargosAdicionales.setLocation(objDescargosAdicionales.getParent().getWidth() / 2 - objDescargosAdicionales.getWidth() / 2, objDescargosAdicionales.getParent().getHeight() / 2 - objDescargosAdicionales.getHeight() / 2);
                        objDescargosAdicionales.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
        
    private void CargosInventarios() {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(33);
            if (resul) {
                if (objBoticas.VerificaBoticaCerrada()) {
                    marcacdo = true;
                    objCargosInventarios = new CargosInventarios(this);
                    desktopPane.add(objCargosInventarios);
                    objCargosInventarios.setLocation(objCargosInventarios.getParent().getWidth() / 2 - objCargosInventarios.getWidth() / 2, objCargosInventarios.getParent().getHeight() / 2 - objCargosInventarios.getHeight() / 2);
                    objCargosInventarios.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*private void ImpDescargosBoticas() {

        if (marcacdo == false) {
            boolean resul = true;
            if (resul) {
                if (objBoticas.VerificaBoticaCerrada()) {
                    marcacdo = true;
                    objDesBoticas = new ImprimeDescargosBoticas(this,objmenu);
                    desktopPane.add(objDesBoticas);
                    //objCargosInventarios.setLocation(objDesBoticas.getParent().getWidth() / 2 - objDesBoticas.getWidth() / 2, objDesBoticas.getParent().getHeight() / 2 - objDesBoticas.getHeight() / 2);
                    objCargosInventarios.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/
    
            
    private void DesCargosInventarios() {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(34);
            if (resul) {
                if (objBoticas.VerificaBoticaCerrada()) {
                    marcacdo = true;
                    objDesCargosInventarios = new DescargosInventarios(objetoventana, this,1);
                    desktopPane.add(objDesCargosInventarios);
                    objDesCargosInventarios.setLocation(objDesCargosInventarios.getParent().getWidth() / 2 - objDesCargosInventarios.getWidth() / 2, objDesCargosInventarios.getParent().getHeight() / 2 - objDesCargosInventarios.getHeight() / 2);
                    objDesCargosInventarios.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void ProductoSAP() {

        if (marcacdo == false) {
            boolean resul = VerifivaCredenciales(34);
            if (resul) {
                if (objBoticas.VerificaBoticaCerrada()) {
                    marcacdo = true;
                    objDesProductosSAP = new ProductosSAP(objetoventana, this);
                    desktopPane.add(objDesProductosSAP);
                    objDesProductosSAP.setLocation(objDesProductosSAP.getParent().getWidth() / 2 - objDesProductosSAP.getWidth() / 2, objDesProductosSAP.getParent().getHeight() / 2 - objDesProductosSAP.getHeight() / 2);
                    objDesProductosSAP.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton12 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jButton9 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jButton10 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton11 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jToolBar4 = new javax.swing.JToolBar();
        jTextField3 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField4 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField1 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jTextField2 = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jToolBar5 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        jToolBar6 = new javax.swing.JToolBar();
        jLabel6 = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        Ayuda = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        cargarEnvio = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(Inventarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(79, 50));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBar1.add(jSeparator5);

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(60, 50));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jSeparator16.setName("jSeparator16"); // NOI18N
        jToolBar1.add(jSeparator16);

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(83, 50));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        jButton12.setIcon(resourceMap.getIcon("jButton12.icon")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setToolTipText(resourceMap.getString("jButton12.toolTipText")); // NOI18N
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setPreferredSize(new java.awt.Dimension(83, 50));
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jSeparator13.setName("jSeparator13"); // NOI18N
        jToolBar1.add(jSeparator13);

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(75, 50));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jSeparator8.setName("jSeparator8"); // NOI18N
        jToolBar1.add(jSeparator8);

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jSeparator10.setName("jSeparator10"); // NOI18N
        jToolBar1.add(jSeparator10);

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jSeparator11.setName("jSeparator11"); // NOI18N
        jToolBar1.add(jSeparator11);

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        jButton11.setIcon(resourceMap.getIcon("jButton11.icon")); // NOI18N
        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setToolTipText(resourceMap.getString("jButton11.toolTipText")); // NOI18N
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setPreferredSize(new java.awt.Dimension(83, 50));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jSeparator12.setName("jSeparator12"); // NOI18N
        jToolBar1.add(jSeparator12);

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(46, 50));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jPanel1.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFocusable(false);
        jPanel1.setMinimumSize(new java.awt.Dimension(650, 35));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 35));
        jPanel1.setRequestFocusEnabled(false);

        jToolBar4.setBackground(resourceMap.getColor("jToolBar4.background")); // NOI18N
        jToolBar4.setRollover(true);
        jToolBar4.setName("jToolBar4"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setEnabled(false);
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jToolBar5.setBackground(resourceMap.getColor("jToolBar5.background")); // NOI18N
        jToolBar5.setRollover(true);
        jToolBar5.setName("jToolBar5"); // NOI18N

        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setRequestFocusEnabled(false);
        jToolBar5.add(jLabel5);

        jToolBar6.setBackground(resourceMap.getColor("jToolBar5.background")); // NOI18N
        jToolBar6.setRollover(true);
        jToolBar6.setName("jToolBar6"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setRequestFocusEnabled(false);
        jToolBar6.add(jLabel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jToolBar5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        desktopPane.setBackground(resourceMap.getColor("desktopPane.background")); // NOI18N
        desktopPane.setName("desktopPane"); // NOI18N

        menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuBar.setName("menuBar"); // NOI18N

        Ayuda.setMnemonic('A');
        Ayuda.setText(resourceMap.getString("Ayuda.text")); // NOI18N
        Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AyudaActionPerformed(evt);
            }
        });

        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        Ayuda.add(exitMenuItem);

        menuBar.add(Ayuda);

        jMenu2.setMnemonic('M');
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N

        saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(saveMenuItem);

        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(openMenuItem);

        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu2.add(jSeparator1);

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jSeparator7.setName("jSeparator7"); // NOI18N
        jMenu2.add(jSeparator7);

        jMenuItem10.setText(resourceMap.getString("jMenuItem10.text")); // NOI18N
        jMenuItem10.setEnabled(false);
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        cargarEnvio.setText(resourceMap.getString("cargarEnvio.text")); // NOI18N
        cargarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarEnvioActionPerformed1(evt);
            }
        });
        jMenu2.add(cargarEnvio);

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        menuBar.add(jMenu2);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed1(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed1(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jMenu1.add(jSeparator9);

        jMenuItem11.setText(resourceMap.getString("jMenuItem11.text")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu1.add(jMenuItem7);

        menuBar.add(jMenu1);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        menuBar.add(jMenu5);

        jMenu3.setMnemonic('A');
        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setActionCommand(resourceMap.getString("jMenuItem5.actionCommand")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        menuBar.add(jMenu3);

        jMenu4.setMnemonic('A');
        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jSeparator14.setName("jSeparator14"); // NOI18N
        jMenu4.add(jSeparator14);

        jMenuItem15.setText(resourceMap.getString("jMenuItem15.text")); // NOI18N
        jMenuItem15.setName("jMenuItem15"); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jSeparator15.setName("jSeparator15"); // NOI18N
        jMenu4.add(jSeparator15);

        jMenuItem16.setText(resourceMap.getString("jMenuItem16.text")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        menuBar.add(jMenu4);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        Cargos();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AyudaActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        CargarGuia();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void cargarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarEnvioActionPerformed
        // TODO add your handling code here:
        objCargarEnvio = new CargarEnvio3(this);
        objCargarEnvio.pack();
        objCargarEnvio.setVisible(true);
        this.desktopPane.add(objCargarEnvio);
    }//GEN-LAST:event_cargarEnvioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Descargoss();
        } catch (Exception ex) {
            System.out.println("Error Abriendo Descargos" + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (marcacdo == false) {
            marcacdo = true;
            objACD = new AnulacionCargosDescargos(this);
            desktopPane.add(objACD);
            objACD.setLocation(objACD.getParent().getWidth() / 2 - objACD.getWidth() / 2, objACD.getParent().getHeight() / 2 - objACD.getHeight() / 2);
            objACD.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Mostrar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cargarEnvioActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarEnvioActionPerformed1

        boolean resul = VerifivaCredenciales(35);

        if (resul) {
            if (objBoticas.VerificaBoticaCerrada()) {
                if (marcacdo == false) {
                    marcacdo = true;
                    objCargarEnvio = new CargarEnvio3(this);
                    desktopPane.add(objCargarEnvio);
                    objCargarEnvio.setLocation(objCargarEnvio.getParent().getWidth() / 2 - objCargarEnvio.getWidth() / 2, objCargarEnvio.getParent().getHeight() / 2 - objCargarEnvio.getHeight() / 2);
                    objCargarEnvio.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED YA HA CERRADO BOTICA ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_cargarEnvioActionPerformed1

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CargarGuia();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cargos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Mostrar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Descargoss();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed1
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed1

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem9ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed1
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            DesCargosInventarios();
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed1

    private void jMenuItem8ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed1
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 0) == 1) {
            CargosInventarios();
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed1

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 3) == 0) {
            CargosInventarios();
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 0) {
            DesCargosInventarios();
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            marcacdo = true;
            FormBoletas_Manuales objeto = new FormBoletas_Manuales(objetoventana, this);
            desktopPane.add(objeto);
            objeto.setLocation(objeto.getParent().getWidth() / 2 - objeto.getWidth() / 2, objeto.getParent().getHeight() / 2 - objeto.getHeight() / 2);
            objeto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        try {
            RegenerarDescargoss();
        } catch (Exception ex) {
            System.out.println("Error Abriendo Descargos" + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            cruceInventarios();
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            generarHojaInventarios();
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            informe();
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            GuiaAjustes();
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 0) {
            ProductoSAP();
        } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objmenu.getIdpersonal_botica()), 0) == 1) {
            descuentos();
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        DescargosTrastienda();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        Descargoscli();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 5) == 1) {
            //ImpDescargosBoticas();
        
       
       if (marcacdo == false) {
            marcacdo = true;
            //objACD = new AnulacionCargosDescargos(this);
            objDesBoticas = new ImprimeDescargosBoticas(this,objmenu,1);
            desktopPane.add(objDesBoticas);
            objDesBoticas.setLocation(objDesBoticas.getParent().getWidth() / 2 - objDesBoticas.getWidth() / 2, objDesBoticas.getParent().getHeight() / 2 - objDesBoticas.getHeight() / 2);
            objDesBoticas.setVisible(true);
        }
       
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 5) == 1) {
            //ImpDescargosBoticas();
        
       
       if (marcacdo == false) {
            marcacdo = true;
            //objACD = new AnulacionCargosDescargos(this);
            objDesBoticas = new ImprimeDescargosBoticas(this,objmenu,2);
            desktopPane.add(objDesBoticas);
            objDesBoticas.setLocation(objDesBoticas.getParent().getWidth() / 2 - objDesBoticas.getWidth() / 2, objDesBoticas.getParent().getHeight() / 2 - objDesBoticas.getHeight() / 2);
            objDesBoticas.setVisible(true);
        }
       
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        try {
            DescargosAdicionales();
        } catch (Exception ex) {
            System.out.println("Error Abriendo Descargos" + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 5) == 1) {
            //ImpDescargosBoticas();
        
       
       if (marcacdo == false) {
            marcacdo = true;
            //objACD = new AnulacionCargosDescargos(this);
            objDesBoticas = new ImprimeDescargosBoticas(this,objmenu,3);
            desktopPane.add(objDesBoticas);
            objDesBoticas.setLocation(objDesBoticas.getParent().getWidth() / 2 - objDesBoticas.getWidth() / 2, objDesBoticas.getParent().getHeight() / 2 - objDesBoticas.getHeight() / 2);
            objDesBoticas.setVisible(true);
        }
       
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 5) == 1) {
            //ImpDescargosBoticas();
        
       
       if (marcacdo == false) {
            marcacdo = true;
            //objACD = new AnulacionCargosDescargos(this);
            objAnulaDesClientesAdicionales = new AnularDescargosClientesAdicionales(this,objmenu,4);
            desktopPane.add(objAnulaDesClientesAdicionales);
            objAnulaDesClientesAdicionales.setLocation(objAnulaDesClientesAdicionales.getParent().getWidth() / 2 - objAnulaDesClientesAdicionales.getWidth() / 2, objAnulaDesClientesAdicionales.getParent().getHeight() / 2 - objAnulaDesClientesAdicionales.getHeight() / 2);
            objAnulaDesClientesAdicionales.setVisible(true);
        }
       
       } else {
            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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

    private void cruceInventarios() {
            Habilita(false, jButton6);
            marcacdo = true;
            objCruce = new CruceInventarios(objetoventana, this, objmenu, desktopPane);
            desktopPane.add(objCruce);
            objCruce.setLocation(objCruce.getParent().getWidth() / 2 - objCruce.getWidth() / 2, objCruce.getParent().getHeight() / 2 - objCruce.getHeight() / 2);
            objCruce.setVisible(true);        
    }

    private void generarHojaInventarios() {
            Habilita(false, jButton7);
            marcacdo = true;
            objHojaDeTrabajo = new CrearHojaDeTrabajo(objetoventana, this, objmenu, desktopPane);
            desktopPane.add(objHojaDeTrabajo);
            objHojaDeTrabajo.setLocation(objHojaDeTrabajo.getParent().getWidth() / 2 - objHojaDeTrabajo.getWidth() / 2, objHojaDeTrabajo.getParent().getHeight() / 2 - objHojaDeTrabajo.getHeight() / 2);
            objHojaDeTrabajo.setVisible(true);
    }

    private void informe() {
        Habilita(false, jButton8);
        marcacdo = true;
        objInformeInv = new InformeInv(objetoventana, this, objmenu, desktopPane);
        desktopPane.add(objInformeInv);
        objInformeInv.setLocation(objInformeInv.getParent().getWidth() / 2 - objInformeInv.getWidth() / 2, objInformeInv.getParent().getHeight() / 2 - objInformeInv.getHeight() / 2);
        objInformeInv.setVisible(true);
    }

    private void GuiaAjustes() {
        Habilita(false, jButton9);
        marcacdo = true;
        objGuiaAjustes = new GuiaDeAjustes(objetoventana, this, objmenu, desktopPane);
        desktopPane.add(objGuiaAjustes);
        objGuiaAjustes.setLocation(objGuiaAjustes.getParent().getWidth() / 2 - objGuiaAjustes.getWidth() / 2, objGuiaAjustes.getParent().getHeight() / 2 - objGuiaAjustes.getHeight() / 2);
        objGuiaAjustes.setVisible(true);
    }

    private void descuentos() {
            Habilita(false, jButton10);
            marcacdo = true;
            objDescuentosInv = new DescuentosInventarios(objetoventana, this, objmenu, desktopPane);
            desktopPane.add(objDescuentosInv);
            objDescuentosInv.setLocation(objDescuentosInv.getParent().getWidth() / 2 - objDescuentosInv.getWidth() / 2, objDescuentosInv.getParent().getHeight() / 2 - objDescuentosInv.getHeight() / 2);
            objDescuentosInv.setVisible(true);
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Ayuda;
    private javax.swing.JMenuItem cargarEnvio;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField1;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField2;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField3;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
