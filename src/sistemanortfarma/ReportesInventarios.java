package sistemanortfarma;

import CapaLogica.LogicaConexion;
import CapaLogica.LogicaPersonal;
import entidad.UsuarioBotica;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class ReportesInventarios extends javax.swing.JInternalFrame {

    KardexProducto objKardexProducto;
    KardexProductoConsumoBienes objKardexProductoCB;
    public static Integer ventanaAbierta = 0;
    public static Integer contador = 0;
    ProductosLaboratorio objProLab;
    ProductosLaboratorioTrastienda objProLabTras;
    DefaultMutableTreeNode node;
    public static boolean entra = true;
    private String tempidbotica;
    AplicacionVentas objpricipal;
    Inventarios objinventario;
    ProductosPorLaboratorioStock objProductosPorLaboratorioStock;
    ProductosPorLaboratorioStockTrastienda objProductosPorLaboratorioStockT;
    OpcionesMenu objmenu;
    ReporteInventarios objReporteInv;
    ControlTransacciones objControlTransacciones;
    ReporteUltimoEnvio objReporteUltimoEnvio;
    ReportePsicoEstupe objReportePsicotroEstupefacintes;
    ControlRegistrosEspeciales objControlRegistrosEspeciales;
    ReportePsicotropicos objControlReportePsicotropicos;
    private static List<UsuarioBotica> credenciales = new ArrayList<UsuarioBotica>();
    LogicaPersonal objPersonal = new LogicaPersonal();
    LogicaConexion logconex = new LogicaConexion();
    public static Connection con = null;
    private String idbotica;
    VerificaSistema obj;

    public ReportesInventarios(Inventarios obj, OpcionesMenu objeto) {
        initComponents();
        objmenu = objeto;
        credenciales = objmenu.getUsuariobotica();
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        setCon(con);
        objinventario = obj;
        crearArbol();
        tempidbotica = objeto.getIdbotica();
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        ReportesInventarios.con = con;
    }

    public static boolean isEntra() {
        return entra;
    }

    public static void setEntra(boolean entra) {
        ReportesInventarios.entra = entra;
    }

    public static Integer getContador() {
        return contador;
    }

    public static void setContador(Integer contador) {
        ReportesInventarios.contador = contador;
    }

    public static Integer getVentanaAbierta() {
        return ventanaAbierta;
    }

    public static void setVentanaAbierta(Integer ventanaAbierta) {
        ReportesInventarios.ventanaAbierta = ventanaAbierta;
    }

    private void crearArbol() {

        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode(" Opciones ");
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode("  Reportes   ");
        DefaultMutableTreeNode padre1 = new DefaultMutableTreeNode("22. Salir  ");
        DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode("1 .  Kardex  ");
        DefaultMutableTreeNode hijo1 = new DefaultMutableTreeNode("18 .  Kardex Consumo de Bienes  ");
        DefaultMutableTreeNode hijo22 = new DefaultMutableTreeNode("2 .  Listado de Laboratorios ");
        DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode("3 .  Listado Prod. x Con Stock ");
        DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode("4 .  Movimientos de Inventarios  ");
        DefaultMutableTreeNode hijo33 = new DefaultMutableTreeNode("5 .  Movimientos de Botica  ");
        DefaultMutableTreeNode hijo4 = new DefaultMutableTreeNode("6 .  Control de Transacciones  ");
        DefaultMutableTreeNode hijo5 = new DefaultMutableTreeNode("7 .  Listado Prod x Lab Sin Stock");
        DefaultMutableTreeNode hijo51 = new DefaultMutableTreeNode("8 . Reporte de Inventario x Lab");
        DefaultMutableTreeNode hijo52 = new DefaultMutableTreeNode("9 . Listado Prod x Lab");
        DefaultMutableTreeNode hijo6 = new DefaultMutableTreeNode("10 . Listado de Personal");
        DefaultMutableTreeNode hijo7 = new DefaultMutableTreeNode("11 .  Boleta (Detalle)");
        DefaultMutableTreeNode hijo8 = new DefaultMutableTreeNode("12 .  Reporte de Control");
        DefaultMutableTreeNode hijo9 = new DefaultMutableTreeNode("13 .  Movimientos de Venta ");
        DefaultMutableTreeNode hijo10 = new DefaultMutableTreeNode("14 . Reporte de Registros Especiales");
        DefaultMutableTreeNode hijo11 = new DefaultMutableTreeNode("15 . Reporte de Psicotropicos");
        DefaultMutableTreeNode hijo12 = new DefaultMutableTreeNode("16 . Notas de Credito Ventas");
        DefaultMutableTreeNode hijo23 = new DefaultMutableTreeNode("17 . List.Prod.xConStock Trast ");
        DefaultMutableTreeNode hijo24 = new DefaultMutableTreeNode("19 . Repor.InventarioxLab Trast ");
        DefaultMutableTreeNode hijo25 = new DefaultMutableTreeNode("20 . Reporte Ultimo Envio ");
        DefaultMutableTreeNode hijo26 = new DefaultMutableTreeNode("21 . Reporte Psicotro/Estupefacientes ");


        modelo.insertNodeInto(padre, abuelo, 0);
        //modelo.insertNodeInto(submenu,abuelo,1);
        modelo.insertNodeInto(hijo26,padre,0);
        modelo.insertNodeInto(hijo25,padre,0);
        modelo.insertNodeInto(hijo24,padre,0);
        modelo.insertNodeInto(hijo23,padre,0);        
        
        modelo.insertNodeInto(hijo12, padre, 0);
        modelo.insertNodeInto(hijo11, padre, 0);
        modelo.insertNodeInto(hijo10, padre, 0);
        modelo.insertNodeInto(hijo8, padre, 0);
        modelo.insertNodeInto(hijo9, padre, 0);
        modelo.insertNodeInto(hijo7, padre, 0);
        modelo.insertNodeInto(hijo6, padre, 0);
        modelo.insertNodeInto(hijo52, padre, 0);
        modelo.insertNodeInto(hijo51, padre, 0);
        modelo.insertNodeInto(hijo5, padre, 0);
        modelo.insertNodeInto(hijo4, padre, 0);
        modelo.insertNodeInto(hijo33, padre, 0);
        modelo.insertNodeInto(hijo3, padre, 0);
        modelo.insertNodeInto(hijo2, padre, 0);
        modelo.insertNodeInto(hijo22, padre, 0);
        modelo.insertNodeInto(hijo1,padre,0);
        
        
        modelo.insertNodeInto(hijo, padre, 0);        
        modelo.insertNodeInto(padre1, abuelo, 1);
        

        this.jTree1.setModel(modelo);
        this.jTree1.expandRow(0);
        this.jTree1.expandRow(1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        desktopPane = new javax.swing.JDesktopPane();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ReportesInventarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTree1.setName("jTree1"); // NOI18N
        jTree1.setPreferredSize(new java.awt.Dimension(240, 64));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        getContentPane().add(jScrollPane1);

        desktopPane.setBackground(resourceMap.getColor("desktopPane.background")); // NOI18N
        desktopPane.setName("desktopPane"); // NOI18N
        getContentPane().add(desktopPane);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        TreePath parentPath = jTree1.getSelectionPath();

        try {

            node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            Object nodeInfo = node.getUserObject();
            if (node.isLeaf()) {
                VerificaEleccion();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
    }//GEN-LAST:event_jTree1ValueChanged

    public void abrirVentanaInterna(JInternalFrame ventana) {
        this.desktopPane.add(ventana);
        try {
            ventana.setLocation(ventana.getParent().getWidth() / 2 - ventana.getWidth() / 2, ventana.getParent().getHeight() / 2 - ventana.getHeight() / 2);
            ventana.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        ventana.setVisible(true);
    }

    private boolean VerifivaCredenciales(int valorsubmenu) {
        boolean permiso = false;
        int cant;
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

    private void VerificaEleccion() throws JRException {

        setContador(getContador() + 1);
        boolean resul = false;
        // setVentanaAbierta(contador);

        switch (Integer.valueOf(node.toString().substring(0, 2).trim())) {

            case 1:

                resul = VerifivaCredenciales(36);
                if (resul) {

                    if (entra) {
                        //Kardex x Producto
                        entra = false;
                        objKardexProducto = new KardexProducto(tempidbotica, this, objinventario.getUsuario());
                        abrirVentanaInterna(objKardexProducto);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            
            case 18:

                resul = VerifivaCredenciales(82);
                if (resul) {

                    if (entra) {
                        
                        entra = false;
                        objKardexProductoCB = new KardexProductoConsumoBienes(tempidbotica, this, objinventario.getUsuario());
                        abrirVentanaInterna(objKardexProductoCB);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 2:

                resul = VerifivaCredenciales(46);
                if (resul) {

                    if (entra) {
                        //Kardex x Producto
                        entra = false;
                        LabConStock onj = new LabConStock(tempidbotica, this);
                        abrirVentanaInterna(onj);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 3:

                resul = VerifivaCredenciales(37);
                if (resul) {

                    if (entra) {
                        entra = false;
                        objProLab = new ProductosLaboratorio(this, this.tempidbotica);
                        abrirVentanaInterna(objProLab);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }


                break;
            case 4:
                resul = VerifivaCredenciales(38);

                if (resul) {
                    if (entra) {
                        entra = false;

                       // try {
                         //   entra = false;
                            objReporteInv = new ReporteInventarios(this, this.tempidbotica);
                            abrirVentanaInterna(objReporteInv);
                       // } catch (Exception ex) {
                       //     System.out.println("AQUI ES" + ex.getMessage());
                       // }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 5:
                resul = VerifivaCredenciales(47);

                if (resul) {
                    if (entra) {
                        entra = false;

                        try {
                            entra = false;
                            ReporteInventarios_Quim mi = new ReporteInventarios_Quim(this, tempidbotica);
                            abrirVentanaInterna(mi);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES" + ex.getMessage());
                        }



                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case 6:

                resul = VerifivaCredenciales(39);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objControlTransacciones = new ControlTransacciones(this, tempidbotica);
                            abrirVentanaInterna(objControlTransacciones);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(4)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case 7:

                resul = VerifivaCredenciales(40);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objProductosPorLaboratorioStock = new ProductosPorLaboratorioStock(this, 0);
                            abrirVentanaInterna(objProductosPorLaboratorioStock);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(5)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 8:
                resul = VerifivaCredenciales(40);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objProductosPorLaboratorioStock = new ProductosPorLaboratorioStock(this, 1);
                            abrirVentanaInterna(objProductosPorLaboratorioStock);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(5)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 9:
                resul = VerifivaCredenciales(40);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objProductosPorLaboratorioStock = new ProductosPorLaboratorioStock(this, 2);
                            abrirVentanaInterna(objProductosPorLaboratorioStock);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(5)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 10:

                JasperPrint jasperPrint;
                Map parameters = new HashMap();
                JasperReport masterReport = null;
                String report_file;
                URL archivo;
                String tipo = "";
                String numero = "";
                String sistema = "Windows";


                parameters.put("Tipo", tipo);
                parameters.put("Numero", numero);
                parameters.put("IDBOTICA", tempidbotica);
                parameters.put("REPORT_CONNECTION", con);


                archivo = this.getClass().getResource("/Reportes/Personal.jasper");

                if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setTitle("REPORTE PERSONAL DE BOTICA");
                view.setVisible(true);
                view.setSize(850, 850);

                break;

            case 11:

                resul = VerifivaCredenciales(41);

                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            ReimprimeBoleta objInv = new ReimprimeBoleta(this, tempidbotica);
                            abrirVentanaInterna(objInv);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(7)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 12:

                try {
                    entra = false;
                    Reporte_Movimientos objInv = new Reporte_Movimientos(this, tempidbotica);
                    abrirVentanaInterna(objInv);
                } catch (Exception ex) {
                    System.out.println("AQUI ES(7)" + ex.getMessage());
                }

                break;

            case 13:

                try {

                    entra = false;
                    MovimientoInternos objinve = new MovimientoInternos(this, tempidbotica);
                    abrirVentanaInterna(objinve);
                } catch (Exception ex) {
                    System.out.println("AQUI ES(7)" + ex.getMessage());
                }

                break;
            case 14:

                resul = VerifivaCredenciales(71);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objControlRegistrosEspeciales = new ControlRegistrosEspeciales(this, tempidbotica);
                            abrirVentanaInterna(objControlRegistrosEspeciales);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(14)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 15:

                resul = VerifivaCredenciales(73);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objControlReportePsicotropicos = new ReportePsicotropicos(this, tempidbotica, objinventario.getUsuario());
                            abrirVentanaInterna(objControlReportePsicotropicos);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(15)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

             case 16:
                    if (entra) {
                        entra = false;                        
                        Reporte_NotaCreditoInv p = new Reporte_NotaCreditoInv(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
             break;
             
             case 17:

                resul = VerifivaCredenciales(37);
                if (resul) {

                    if (entra) {
                        entra = false;
                        objProLabTras = new ProductosLaboratorioTrastienda(this, this.tempidbotica);
                        abrirVentanaInterna(objProLabTras);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }


            break;
            
            case 19:
                resul = VerifivaCredenciales(40);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objProductosPorLaboratorioStockT = new ProductosPorLaboratorioStockTrastienda(this, 1);
                            abrirVentanaInterna(objProductosPorLaboratorioStockT);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(5)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            break;
            
            case 20:

                resul = VerifivaCredenciales(83);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objReporteUltimoEnvio = new ReporteUltimoEnvio(this, tempidbotica);
                            abrirVentanaInterna(objReporteUltimoEnvio);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(4)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
                
            case 21:

                resul = VerifivaCredenciales(83);
                if (resul) {
                    if (entra) {

                        try {
                            entra = false;
                            objReportePsicotroEstupefacintes = new ReportePsicoEstupe(this, tempidbotica,objinventario.getUsuario());
                            abrirVentanaInterna(objReportePsicotroEstupefacintes);
                        } catch (Exception ex) {
                            System.out.println("AQUI ES(4)" + ex.getMessage());
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;  

            case 22:
                CerrarVentana();
                break;

        }


    }

    private void CerrarVentana() {

        int confirmado = JOptionPane.showInternalConfirmDialog(this, "  DESEAS SALIR ? ", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            objinventario.Habilita(true);
            objinventario.marcacdo = false;
            dispose();
        }

        objinventario.requestFocus();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
