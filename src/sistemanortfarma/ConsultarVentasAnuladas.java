package sistemanortfarma;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JasperFillManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.util.Map;
import java.awt.Frame;
import java.net.URL;
import java.sql.Connection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.text.DateFormat;
import CapaLogica.LogicaConexion;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class ConsultarVentasAnuladas extends javax.swing.JInternalFrame implements KeyListener {

    MuestraVentana objetoventana = new MuestraVentana();
    private DefaultTableModel model_notas;
    private DefaultTableModel model_EnvioDetalle;
    TableColumnModel colModel;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Venta obj;
    Connection con;
    VerificaSistema objsistema;
    private URL archivo;
    private String idventa = "";
    private String idbotica = null;
    List<Venta> listaInternos = new ArrayList<Venta>();
    LogicaVenta logventa = new LogicaVenta();
    Object[] datosDetalle = new Object[6];
    Object[] datosDetalle_1 = new Object[5];
    private DefaultTableModel model;
    TableColumnModel colModel1;
    List<Venta> listaVenta = new ArrayList<Venta>();
    double total = 0;
    JasperReport masterReport = null;
    double subtotal = 0;
    LogicaConexion logconex = new LogicaConexion();
    Map parameters = new HashMap();
    JasperViewer view;
    double igv = 0;
    JasperPrint jasperPrint;
    CargosInventarios objventas;

    /** Creates new form ConsultarVenta1 */
   /* public ConsultarVentasAnuladas(AplicacionVentas obj) {
        initComponents();
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        objventas = obj;
        idbotica = objventas.getIdbotica();

        jTextField2.setText(idbotica);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        model_notas = (DefaultTableModel) tablanotas.getModel();
        model_EnvioDetalle = (DefaultTableModel) jTable1.getModel();
        colModel = tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        colModel1 = jTable1.getColumnModel();
        AparienciaTabla();
        Connection con;
        jTextField1.requestFocus();
        jTabbedPane1.setEnabledAt(1, false);
        OcultaLabel(false);
        this.txtnumero1.setVisible(false);
        this.txtnumero2.setVisible(false);
        this.txtnumero3.setVisible(false);
        this.txtnumero4.setVisible(false);
    }*/

    public ConsultarVentasAnuladas(CargosInventarios obj) {

        initComponents();
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        objventas = obj;
        //idbotica = botica;

        jTextField2.setText(idbotica);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        model_notas = (DefaultTableModel) tablanotas.getModel();
        model_EnvioDetalle = (DefaultTableModel) jTable1.getModel();
        colModel = tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        colModel1 = jTable1.getColumnModel();
        AparienciaTabla();
        Connection con;
        jTextField1.requestFocus();
        jTabbedPane1.setEnabledAt(1, false);
        OcultaLabel(false);
        this.txtnumero1.setVisible(false);
        this.txtnumero2.setVisible(false);
        this.txtnumero3.setVisible(false);
        this.txtnumero4.setVisible(false);
        this.objetoventana = objetoventana;
    }
    
    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.tablanotas.getColumnModel());
        cabecera.setReorderingAllowed(false);
        tablanotas.setTableHeader(cabecera);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.tablanotas.getColumnModel().getColumn(5).setCellRenderer(tcenter);

        JTableHeader cabecera1 = new JTableHeader(this.jTable1.getColumnModel());
        cabecera1.setReorderingAllowed(false);
        this.jTable1.setTableHeader(cabecera1);

        DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
        tcenter1.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);

        DefaultTableCellRenderer tcenter2 = new DefaultTableCellRenderer();
        tcenter2.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter2);
        this.jTable1.getColumnModel().getColumn(2).setCellRenderer(tcenter2);
    }

    private void CargarVentas() {

        try {
            obj = new Venta(idbotica, idventa);

            EjecutatHilo obje = new EjecutatHilo(0);
            obje.start();
        } catch (Exception ex) {
        }


    }

    private void LimpiatTabla() {
        int cant = tablanotas.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_notas.removeRow(i);
            }
        }
    }

    private void CerrarVentana() {
        if (this.tablanotas.getRowCount() > 0) {
            int p = JOptionPane.showConfirmDialog(null, " Desea Cerrar Esta Ventana ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                //objventas.Habilita(true);
                //objventas.marcacdo = false;
                LimpiatTabla();
                listaInternos.removeAll(listaInternos);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setSelectedIndex(0);
                dispose();
            }

        } else {
            //objventas.Habilita(true);
            //objventas.marcacdo = false;
            LimpiatTabla();
            listaInternos.removeAll(listaInternos);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setSelectedIndex(0);
            dispose();
        }
    }

    private void BuscaVenta(String filtro) {
        filtro += '%';

        try {

            obj = new Venta(idbotica, filtro);
            listaInternos = logventa.Lista_Internos_Ventas(obj, 0);

            LimpiatTabla();

            for (int i = 0; i < listaInternos.size(); i++) {
                datosDetalle[0] = listaInternos.get(i).getId_Venta();
                datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                datosDetalle[3] = listaInternos.get(i).getSerie();
                datosDetalle[4] = listaInternos.get(i).getNumero();
                datosDetalle[5] = listaInternos.get(i).getTotal();

                model_notas.addRow(datosDetalle);
            }

            tablanotas.getSelectionModel().setSelectionInterval(0, 0);



        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA METODO BuscarNotaCredito");
        }


    }

    private void SeleccionarVenta() {
        int fila = this.tablanotas.getSelectedRow();
        String miruc;


        if (fila >= 0 && listaInternos.size() > 0) {
            idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));

            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setSelectedIndex(1);

            total = listaInternos.get(fila).getTotal();  
                       
            txtnumero.setText(listaInternos.get(fila).getnumeroentregaefec());
            txttotal.setText(String.valueOf(total));            
            txtUsuariosistema.setText(listaInternos.get(fila).getusuarioingreso());
            txtautorizado.setText(listaInternos.get(fila).getdesgerente());
            txtcajero.setText(listaInternos.get(fila).getNombre());
            
            txtnumero1.setText(String.valueOf(listaInternos.get(fila).getFecha_Registro()));
            txtnumero2.setText(String.valueOf(listaInternos.get(fila).getId_Caja()));
            txtnumero3.setText(String.valueOf(listaInternos.get(fila).getId_Personal_Botica_Caja()));
            txtnumero4.setText(String.valueOf(listaInternos.get(fila).getTurno()));

            CargarProductos();
        }

    }

    private void CargarProductos() {
        obj = new Venta(idbotica, idventa);
        listaVenta = logventa.ListaEnvioEfectivo_Detalle(obj);

        for (int i = 0; i < listaVenta.size(); i++) {

            datosDetalle_1[0] = i + 1;
            datosDetalle_1[1] = listaVenta.get(i).getnumeroentregaefec();
            datosDetalle_1[2] = listaVenta.get(i).getvalorBillete();
            datosDetalle_1[3] = listaVenta.get(i).getCantidad();
            datosDetalle_1[4] = listaVenta.get(i).getTotal();

            model.addRow(datosDetalle_1);
        } 
    }

    private void AnularEnvio(){
        obj = new Venta(idbotica, idventa);
        listaVenta = logventa.ListaAnulaEnvioEfectivo(obj);
        String numero = null;
        for (int i = 0; i < listaVenta.size(); i++) {            
            
            numero = listaVenta.get(i).getnumeroentregaefec();
        }
        limpiar();
        JOptionPane.showMessageDialog(this,"Se Anuló en Registro de Envio Efectivo a Oficina","NORTFARMA",JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiar(){

        this.txtnumero.setText("");
        this.txtUsuariosistema.setText("");
        this.txtautorizado.setText("");
        this.txtcajero.setText("");
        this.txttotal.setText("");
        limpiarTabla();
    }

    private void limpiarTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_EnvioDetalle.removeRow(i);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void OcultaLabel(boolean b) {
     //   throw new UnsupportedOperationException("Not yet implemented");
    }
   
    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    private void BuscaIntervaloFechas_Sen() {
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            idventa = this.jTextField1.getText().trim();

            if (idventa.length() > 0) {
                idventa += '%';
            }

            obj = new Venta(idbotica, idventa, fechaini, fechafin);
            //listaInternos = logventa.Lista_Int_Ventas_Fecha(obj);
            listaInternos = logventa.Lista_EnviosEfectivo(obj);

            if (listaInternos.size() > 0) {
                LimpiatTabla();
            }           

            for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getnumeroentregaefec();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Registro();
                    datosDetalle[2] = listaInternos.get(i).getdescaja();
                    datosDetalle[3] = listaInternos.get(i).getdesturno();
                    datosDetalle[4] = listaInternos.get(i).getNombre();
                    datosDetalle[5] = listaInternos.get(i).getTotal();

                    model_notas.addRow(datosDetalle);
                }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void BuscaDepositosEfectivoOficina() {
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

        fecha1 = (Date) this.jXDatePicker1.getDate();
        fecha2 = (Date) this.jXDatePicker2.getDate();

        fechaini = logfecha.ConvierteFecha(fecha1);
        fechafin = logfecha.ConvierteFecha(fecha2);

        try {

            idventa = this.jTextField1.getText().trim();

            if (idventa.length() > 0) {
                idventa += '%';
            }
            obj = new Venta(idbotica, idventa, fechaini, fechafin);
            EjecutatHilo obje = new EjecutatHilo(1);
            obje.start();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }



    }

    private void LimpiatTabla_Productos() {
        int cant = model.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    public class EjecutatHilo extends Thread {

        int tipbusqueda;

        public EjecutatHilo(int op) {
            tipbusqueda = op;
        }

        public void run() {
            try {

                listaInternos.removeAll(listaInternos);
                if (tipbusqueda == 0) {
                    listaInternos = logventa.Lista_Internos_Ventas(obj, 0);
                } else {
                    listaInternos = logventa.Lista_EnviosEfectivo(obj);
                }

                if (listaInternos.size() > 0) {
                    LimpiatTabla();
                }

                /*rs.getString("Id_Botica"),
                        rs.getInt("ID_CAJA"),rs.getString("CAJA"),
                        rs.getInt("Turno"),rs.getString("turnodesc"), rs.getDate("FECHA"),
                        rs.getInt("Id_Personal"),rs.getString("Cajero"),
                        rs.getInt("Id_Billete"),
                        rs.getDouble("Valor"), rs.getInt("Cantidad"),rs.getDouble("Total"),
                        rs.getString("NroEntrega"), rs.getInt("Id_Gerente"),
                        rs.getString("Gerente"), rs.getInt("Id_UsuarioSistema"),
                        rs.getString("UsuarioIngreso")*/

                for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getnumeroentregaefec();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Registro();
                    datosDetalle[2] = listaInternos.get(i).getdescaja();
                    datosDetalle[3] = listaInternos.get(i).getdesturno();
                    datosDetalle[4] = listaInternos.get(i).getNombre();
                    datosDetalle[5] = listaInternos.get(i).getTotal();

                    model_notas.addRow(datosDetalle);
                }

            } catch (Exception ex) {
                System.out.println("ERROR CAPA VISTA METODO CargarEnvioEfectivo " + ex.getMessage());
            }


            jLabel17.setVisible(false);
            jLabel18.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablanotas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsuariosistema = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtautorizado = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtcajero = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtnumero1 = new javax.swing.JTextField();
        txtnumero2 = new javax.swing.JTextField();
        txtnumero3 = new javax.swing.JTextField();
        txtnumero4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ConsultarVentasAnuladas.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel7.setBackground(resourceMap.getColor("jPanel7.background")); // NOI18N
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jXDatePicker1.setFont(resourceMap.getFont("jXDatePicker2.font")); // NOI18N
        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jXDatePicker2.setFont(resourceMap.getFont("jXDatePicker2.font")); // NOI18N
        jXDatePicker2.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel17.setIcon(resourceMap.getIcon("jLabel17.icon")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablanotas.setFont(resourceMap.getFont("tablanotas.font")); // NOI18N
        tablanotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº ENVIO", "FECHA", "CAJA", "TURNO", "CAJERO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablanotas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablanotas.setName("tablanotas"); // NOI18N
        tablanotas.setSelectionBackground(resourceMap.getColor("tablanotas.selectionBackground")); // NOI18N
        tablanotas.setSelectionForeground(resourceMap.getColor("tablanotas.selectionForeground")); // NOI18N
        tablanotas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablanotas.getTableHeader().setReorderingAllowed(false);
        tablanotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablanotasMouseClicked(evt);
            }
        });
        tablanotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablanotasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablanotas);
        tablanotas.getColumnModel().getColumn(0).setResizable(false);
        tablanotas.getColumnModel().getColumn(0).setPreferredWidth(20);
        tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
        tablanotas.getColumnModel().getColumn(1).setResizable(false);
        tablanotas.getColumnModel().getColumn(1).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
        tablanotas.getColumnModel().getColumn(2).setResizable(false);
        tablanotas.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
        tablanotas.getColumnModel().getColumn(3).setResizable(false);
        tablanotas.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablanotas.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title3")); // NOI18N
        tablanotas.getColumnModel().getColumn(4).setResizable(false);
        tablanotas.getColumnModel().getColumn(4).setPreferredWidth(280);
        tablanotas.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title4")); // NOI18N
        tablanotas.getColumnModel().getColumn(5).setResizable(false);
        tablanotas.getColumnModel().getColumn(5).setPreferredWidth(70);
        tablanotas.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title5")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txtnumero.setEditable(false);
        txtnumero.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        txtnumero.setName("txtnumero"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtUsuariosistema.setEditable(false);
        txtUsuariosistema.setName("txtUsuariosistema"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        jLabel14.setForeground(resourceMap.getColor("jLabel14.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        txtautorizado.setEditable(false);
        txtautorizado.setFont(resourceMap.getFont("txtautorizado.font")); // NOI18N
        txtautorizado.setName("txtautorizado"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("txtnumero.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        txtcajero.setEditable(false);
        txtcajero.setFont(resourceMap.getFont("txtcajero.font")); // NOI18N
        txtcajero.setName("txtcajero"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Nº Envio", "Valor", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        txttotal.setBackground(resourceMap.getColor("txttotal.background")); // NOI18N
        txttotal.setEditable(false);
        txttotal.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotal.setName("txttotal"); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtnumero1.setEditable(false);
        txtnumero1.setName("txtnumero1"); // NOI18N

        txtnumero2.setEditable(false);
        txtnumero2.setName("txtnumero2"); // NOI18N

        txtnumero3.setEditable(false);
        txtnumero3.setName("txtnumero3"); // NOI18N

        txtnumero4.setEditable(false);
        txtnumero4.setName("txtnumero4"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtnumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuariosistema, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                            .addComponent(txtcajero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                            .addComponent(txtautorizado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(282, 282, 282)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(77, 77, 77))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtnumero4)
                                .addComponent(txtnumero3)
                                .addComponent(txtnumero2)
                                .addComponent(txtnumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))))
                        .addGap(131, 131, 131))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtautorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtcajero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuariosistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtnumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumero4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.jLabel17.setVisible(true);
        this.jLabel18.setVisible(true);
        BuscaDepositosEfectivoOficina();
}//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
}//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
}//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String filtro = null;
        filtro = this.jTextField1.getText().trim();

        if (!String.valueOf(filtro).isEmpty()) {
            /*if (this.jRadioButton1.isSelected()) {
                BuscaVenta(filtro);
            } else {*/
                BuscaIntervaloFechas_Sen();
            //}

        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);

        this.txtcajero.setText("");
        this.txtnumero.setText("");        
        total = 0;        
        LimpiatTabla_Productos();
        BuscaDepositosEfectivoOficina();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablanotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanotasMouseClicked

        if (evt.getClickCount() % 2 == 0) {
            SeleccionarVenta();
        }
    }//GEN-LAST:event_tablanotasMouseClicked

    private void tablanotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablanotasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SeleccionarVenta();
        }
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }
    }//GEN-LAST:event_tablanotasKeyPressed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        CerrarVentana();
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int reply = JOptionPane.showConfirmDialog(null, "Seguro de Anular el Documento de Envio a Oficina:", "NORTFARMA", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                
                AnularEnvio();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        GeneraReporteEfectivo2();
    }//GEN-LAST:event_jButton4ActionPerformed

    public void GeneraReporteEfectivo2() {

        //if (Verifica_Datos()) {        

            try {
                String dateInString = txtnumero1.getText();   
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaDate = df.parse(dateInString);

                java.util.Date date = df.parse(dateInString.toString());
                
                String sistema = "Windows";
                parameters.put("MIFECHA", date);
                parameters.put("IDCAJA", Integer.parseInt(txtnumero2.getText()));
                parameters.put("IDBOTICA", idbotica);
                parameters.put("IDPERSONAL", Integer.parseInt(txtnumero3.getText()));
                parameters.put("TURNO", Integer.parseInt(txtnumero4.getText()));
                parameters.put("NUMERO", txtnumero.getText());
                parameters.put("REPORT_CONNECTION", con);

                archivo = this.getClass().getResource("/Reportes/REPORTE_ENVIO_EFECTIVOCAJA.jasper");

                if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                view = new JasperViewer(jasperPrint, false);
                view.setTitle("DETALLE DE ENTREGA DE EFECTIVO");
                view.setVisible(true);
                view.setSize(870, 700);

            }
       catch (Exception ex ){
          System.out.println(ex);
              OcultaLabel(false);
              JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);

       }
            

        //}

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtUsuariosistema;
    private javax.swing.JTextField txtautorizado;
    private javax.swing.JTextField txtcajero;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtnumero1;
    private javax.swing.JTextField txtnumero2;
    private javax.swing.JTextField txtnumero3;
    private javax.swing.JTextField txtnumero4;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
