package sistemanortfarma;

import CapaLogica.LogicaConexion;
import entidad.UsuarioBotica;
import java.sql.Connection;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class OpcionesReportes extends javax.swing.JInternalFrame {

    DefaultMutableTreeNode Titulo = null;
    DefaultTreeModel modelo = null;
    DefaultTreeModel modelo1 = null;
    DefaultMutableTreeNode parentNode = null;
    DefaultMutableTreeNode child;
    VentasxFecha objVentasxFecha;
    Reporte_LiquidacionSOAT objVentasxFechaliquidacion;
    VentasxVendedor objVentasxVendedor;
    VentasxProducto objVentasxProducto;
    VentasxLaboratorio objVentasxLaboratorio;
    VentasxCliente objVentasxCliente;
    AplicacionVentas objpricipal;
    private String tempidbotica;
    DefaultMutableTreeNode node;
    public static boolean entra = true;
    public static Connection con = null;
    LogicaConexion logconex = new LogicaConexion();
    List<UsuarioBotica> credenciales;

    public OpcionesReportes(AplicacionVentas obj) {
        initComponents();
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        setCon(con);
        objpricipal = obj;
        credenciales = objpricipal.getCredenciales();
        tempidbotica = objpricipal.getIdbotica();
        String user = objpricipal.getUsuario();
        setTitle("Reportes y Consultas                                   " + user + "");
        CargarArbol();
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        OpcionesReportes.con = con;
    }

    private void CargarArbol() {
        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode(" Reportes y Consultas ");
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode("  Reportes De Caja   ");
        modelo = new DefaultTreeModel(abuelo);

        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode("1 .  Detalle Entrega de Efectivo ");
        DefaultMutableTreeNode hijo22 = new DefaultMutableTreeNode("3 .  Rendicion de Caja  ");
        DefaultMutableTreeNode hijo23 = new DefaultMutableTreeNode("2 .  Movimientos de Caja  ");
        DefaultMutableTreeNode hijo24 = new DefaultMutableTreeNode("5 .  Ventas Totalizado  ");

        DefaultMutableTreeNode submenu = new DefaultMutableTreeNode(" .  Reporte de Ventas  ");

        DefaultMutableTreeNode hijo1 = new DefaultMutableTreeNode("4 . Por Responsable  ");
        DefaultMutableTreeNode hijo111 = new DefaultMutableTreeNode("18. Por Responsable Resumen  ");
        DefaultMutableTreeNode hijo112 = new DefaultMutableTreeNode("23. Ventas Por Vendedor  ");
        DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode("6 . Por Fecha  ");
        DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode("7 . Por Vendedor ");
        DefaultMutableTreeNode hijo8 = new DefaultMutableTreeNode("17. Internos de Venta ");
        DefaultMutableTreeNode hijo9 = new DefaultMutableTreeNode("10. Productos Afectos ");
        DefaultMutableTreeNode hijo4 = new DefaultMutableTreeNode("11. Por Producto ");
        DefaultMutableTreeNode hijo5 = new DefaultMutableTreeNode("12. Por Laboratorio ");
        DefaultMutableTreeNode hijo6 = new DefaultMutableTreeNode("13. Por Cliente ");
        DefaultMutableTreeNode hijo10 = new DefaultMutableTreeNode("15. Ventas Anuladas ");
        DefaultMutableTreeNode hijo11 = new DefaultMutableTreeNode("16. Ventas de la Botica ");
        DefaultMutableTreeNode hijo12 = new DefaultMutableTreeNode("24. Gastos de Caja ");
        DefaultMutableTreeNode hijo14 = new DefaultMutableTreeNode("25. Ventas Delivery ");
        DefaultMutableTreeNode hijo144 = new DefaultMutableTreeNode("26. Ventas SAP ");
        //DefaultMutableTreeNode hijo145 = new DefaultMutableTreeNode("27. Notas de Credito Ventas ");
        DefaultMutableTreeNode hijo148 = new DefaultMutableTreeNode("27. Ventas TotalizadoXvendedor ");
        //DefaultMutableTreeNode hijo146 = new DefaultMutableTreeNode("28. Reporte Liquidacion SOAT ");
        DefaultMutableTreeNode hijo147 = new DefaultMutableTreeNode("29. Productos Temperatura ");
        DefaultMutableTreeNode padre1 = new DefaultMutableTreeNode("33. Salir  ");

        DefaultMutableTreeNode submenu1 = new DefaultMutableTreeNode(" .  Reporte de Creditos  ");
        DefaultMutableTreeNode hijo78 = new DefaultMutableTreeNode("19. Ventas al Credito ");
        DefaultMutableTreeNode hijo7 = new DefaultMutableTreeNode("20. Movimiento de  Creditos ");
        DefaultMutableTreeNode hijo77 = new DefaultMutableTreeNode("21. Detalle de Pagos ");
        DefaultMutableTreeNode hijo79 = new DefaultMutableTreeNode("22. Ventas al Credito X Empresa");

        DefaultMutableTreeNode submenu2 = new DefaultMutableTreeNode(" .  Reporte de Movilidad  ");
        DefaultMutableTreeNode hijo83 = new DefaultMutableTreeNode("32 .  Movilidad  ");


        modelo.insertNodeInto(padre, abuelo, 0);
        modelo.insertNodeInto(submenu, abuelo, 1);
        modelo.insertNodeInto(submenu1, abuelo, 2);
        modelo.insertNodeInto(submenu2, abuelo, 3);

        modelo.insertNodeInto(hijo24, padre, 0);
        modelo.insertNodeInto(hijo22, padre, 0);
        modelo.insertNodeInto(hijo23, padre, 0);
        modelo.insertNodeInto(hijo, padre, 0);
        

        modelo.insertNodeInto(hijo1, submenu, 0);
        modelo.insertNodeInto(hijo2, submenu, 1);
        modelo.insertNodeInto(hijo3, submenu, 2);
        modelo.insertNodeInto(hijo9, submenu, 3);
        modelo.insertNodeInto(hijo4, submenu, 4);
        modelo.insertNodeInto(hijo5, submenu, 5);
        modelo.insertNodeInto(hijo6, submenu, 6);
        modelo.insertNodeInto(hijo10, submenu, 7);
        modelo.insertNodeInto(hijo11, submenu, 8);
        modelo.insertNodeInto(hijo8, submenu, 9);
        modelo.insertNodeInto(hijo111, submenu, 10);
        modelo.insertNodeInto(hijo112, submenu, 11);
        modelo.insertNodeInto(hijo12, submenu, 12);
        modelo.insertNodeInto(hijo144, submenu, 13);
        modelo.insertNodeInto(hijo14, submenu, 13);        
        //modelo.insertNodeInto(hijo146, submenu, 16);
        modelo.insertNodeInto(hijo148, submenu, 15);
        modelo.insertNodeInto(hijo147, submenu, 16);
        


        modelo.insertNodeInto(hijo78, submenu1, 0);
        modelo.insertNodeInto(hijo7, submenu1, 1);
        modelo.insertNodeInto(hijo77, submenu1, 2);
        modelo.insertNodeInto(hijo79, submenu1, 3);

        modelo.insertNodeInto(hijo83, submenu2, 0);
        


        modelo.insertNodeInto(padre1, abuelo, 4);

        this.jTree1.setModel(modelo);

        TreePath treePath = new TreePath(padre.getPath());
        Expandir();

    }

    private void Expandir() {
        for (int x = 0; x < jTree1.getRowCount(); x++) {
            jTree1.expandRow(x);
        }
    }

    private boolean VerifivaCredenciales(int valorsubmenu) {
        boolean permiso = false;
        int cant;

        for (cant = 0; cant < credenciales.size(); cant++) {
            int idpagina = credenciales.get(cant).getIdpagina();

            if (idpagina == valorsubmenu) {
                cant = credenciales.size();
                permiso = true;
            }
        }

        return permiso;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(OpcionesReportes.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTree1.setFont(resourceMap.getFont("jTree1.font")); // NOI18N
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

        jDesktopPane1.setBackground(resourceMap.getColor("jDesktopPane1.background")); // NOI18N
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        jDesktopPane1.setOpaque(false);
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(200, 0));
        getContentPane().add(jDesktopPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VerificaEleccion() {
        try {

            switch (Integer.valueOf(node.toString().substring(0, 2).trim())) {

                case 1:

                    if (entra) {
                        entra = false;
                        Reporte_Entrega_Efectivo objreporte = new Reporte_Entrega_Efectivo(this);
                        abrirVentanaInterna(objreporte);
                    }
                    break;

                case 4:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(56);
                        if (resul) {
                            entra = false;
                            ReporteVentasResp objeto = new ReporteVentasResp(this);
                            abrirVentanaInterna(objeto);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 5:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(57);
                        if (resul) {
                            entra = false;
                            Reporte_VentasTotalizadas objreporte2 = new Reporte_VentasTotalizadas(tempidbotica,this);
                            abrirVentanaInterna(objreporte2);
                    
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 6:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(57);
                        if (resul) {
                            entra = false;
                            objVentasxFecha = new VentasxFecha(tempidbotica, this);
                            abrirVentanaInterna(objVentasxFecha);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 7:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(58);
                        if (resul) {
                            entra = false;
                            objVentasxVendedor = new VentasxVendedor(tempidbotica, this);
                            abrirVentanaInterna(objVentasxVendedor);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 11:
                    if (entra) {

                        boolean resul = VerifivaCredenciales(60);
                        if (resul) {
                            entra = false;
                            objVentasxProducto = new VentasxProducto(tempidbotica, this);
                            abrirVentanaInterna(objVentasxProducto);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;


                case 12:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(61);
                        if (resul) {
                            entra = false;
                            objVentasxLaboratorio = new VentasxLaboratorio(tempidbotica, this);
                            abrirVentanaInterna(objVentasxLaboratorio);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 13:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(62);
                        if (resul) {
                            entra = false;
                            objVentasxCliente = new VentasxCliente(tempidbotica, this);
                            abrirVentanaInterna(objVentasxCliente);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 17:

                    if (entra) {
                        entra = false;
                        Reporte_Internos_venta objreporte3 = new Reporte_Internos_venta(this);
                        abrirVentanaInterna(objreporte3);
                    }
                    break;


                case 3:

                    if (entra) {
                        entra = false;
                        Reporte_Caja_Detallado objreporte1 = new Reporte_Caja_Detallado(this);
                        abrirVentanaInterna(objreporte1);
                    }
                    break;

                case 2:

                    if (entra) {

                        entra = false;
                        Reporte_Movimientos_Caja objreporte2 = new Reporte_Movimientos_Caja(this);
                        abrirVentanaInterna(objreporte2);
                    }
                    break;

                case 18:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(65);
                        if (resul) {
                            entra = false;
                            REPORTE_VENTAS_RESUMEN objeto = new REPORTE_VENTAS_RESUMEN(this);
                            abrirVentanaInterna(objeto);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 10:

                    if (entra) {

                        boolean resul = VerifivaCredenciales(59);
                        if (resul) {
                            entra = false;
                            Reporte_Productos_Afectos objreporte3 = new Reporte_Productos_Afectos(this);
                            abrirVentanaInterna(objreporte3);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }


                    }
                    break;

                case 15:

                    if (entra) {

                        entra = false;
                        Reporte_Ventas_Anuladas objre = new Reporte_Ventas_Anuladas(this);
                        abrirVentanaInterna(objre);

                    }
                    break;

                case 16:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(63);
                        if (resul) {
                            entra = false;
                            VentasxBotica objvent = new VentasxBotica(this);
                            abrirVentanaInterna(objvent);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    break;

                case 20:
                    if (entra) {
                        entra = false;
                        entra = false;
                        MovimientosdeCredito p = new MovimientosdeCredito(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }

                    break;

                case 21:
                    if (entra) {
                        entra = false;
                        Reporte_MovimientosCredito p = new Reporte_MovimientosCredito(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }

                    break;

                case 23:
                    if (entra) {
                        boolean resul = VerifivaCredenciales(64);
                        if (resul) {
                            entra = false;
                            Reporte_Vtas_Vendedor p = new Reporte_Vtas_Vendedor(tempidbotica, this);
                            abrirVentanaInterna(p);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    break;

                case 19:
                    if (entra) {
                        entra = false;
                        VentasxCredito p = new VentasxCredito(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }

                    break;

                case 22:
                    if (entra) {
                        entra = false;
                        VentasCredito_Empresa p = new VentasCredito_Empresa(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
                    break;

                case 24:
                    if (entra) {
                        entra = false;
                        FormReporteGastos p = new FormReporteGastos(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
                    break;

                case 25:
                    if (entra) {
                        entra = false;
                        ReporteVentasDelivery p = new ReporteVentasDelivery(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
                    break;

                case 26:
                    if (entra) {
                        entra = false;
                        Reporte_VentasSAP p = new Reporte_VentasSAP(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
                    break;

                case 27:
                    if (entra) {
                        entra = false;
                        //Reporte_VentasSOAT p = new Reporte_VentasSOAT(tempidbotica, this);
                        //Reporte_NotaCredito p = new Reporte_NotaCredito(tempidbotica, this);
                        ReportVentasTotalizadoxVendedor p = new ReportVentasTotalizadoxVendedor(tempidbotica, this);
                        abrirVentanaInterna(p);
                    }
                    break;

                case 28:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(70);
                        if (resul) {
                            entra = false;                           
                            objVentasxFechaliquidacion = new Reporte_LiquidacionSOAT(tempidbotica, this);
                            abrirVentanaInterna(objVentasxFechaliquidacion);
                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                 case 29:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(79);
                        if (resul) {
                            entra = false;
                            ReporteTemperatura objreporte2 = new ReporteTemperatura(this);
                            abrirVentanaInterna(objreporte2);

                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                break;

                case 32:

                    if (entra) {
                        boolean resul = VerifivaCredenciales(78);
                        if (resul) {
                            entra = false;
                            ReporteMovilidad objreporte2 = new ReporteMovilidad(this);
                            abrirVentanaInterna(objreporte2);

                        } else {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS USTED NO TIENE ACCESO A ESTE MODULO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                break;

                case 33:
                    CerrarVentana();
                    break;

            }

        } catch (Exception ex) {
            System.out.println("VerificaEleccion :" + ex.getMessage());
        }


    }

    public void abrirVentanaInterna(JInternalFrame ventana) {
        this.jDesktopPane1.add(ventana);

        try {
            ventana.setLocation(ventana.getParent().getWidth() / 2 - ventana.getWidth() / 2, ventana.getParent().getHeight() / 2 - ventana.getHeight() / 2);
            ventana.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ventana.setVisible(true);

    }

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
    }//GEN-LAST:event_jTree1ValueChanged

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

    private void CerrarVentana() {
        int confirmado = JOptionPane.showInternalConfirmDialog(this, "  DESEAS SALIR ? ", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            objpricipal.marcacdo = false;
            objpricipal.Habilita(true);
            dispose();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
