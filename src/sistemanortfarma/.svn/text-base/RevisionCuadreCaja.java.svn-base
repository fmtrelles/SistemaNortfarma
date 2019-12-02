package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaConexion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaTiposPagos;
import CapaLogica.LogicaVenta;
import entidad.TiposPagos;
import entidad.Venta;
import entidad.Ventas_Tipo_Pago;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class RevisionCuadreCaja extends javax.swing.JFrame implements Runnable {

    AplicacionVentas obj;
    private String idbotica;
    MuestraVentana objetoventana = new MuestraVentana();
    LogicaVenta objVentas = new LogicaVenta();
    List<Venta> lista_Ventas = new ArrayList<Venta>();
    List<Venta> milista = new ArrayList<Venta>();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    List<Venta> listaVenta = new ArrayList<Venta>();
    List<TiposPagos> listtarj = new ArrayList<TiposPagos>();
    List<Ventas_Tipo_Pago> lista_pagos = new ArrayList<Ventas_Tipo_Pago>();
    Object[] item = new Object[6];
    private DefaultTableModel tablacuadre;
    private DefaultTableModel tabladocumentos;
    private DefaultTableModel tablainternos;
    private DefaultTableModel tablaDetalle;
    private String Fecha;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    RequisitosFactura objfactura = new RequisitosFactura();
    LogicaVenta logventa = new LogicaVenta();
    LogicaTiposPagos objlogicapagos = new LogicaTiposPagos();
    Ventas_Tipo_Pago objeto;
    private DefaultTableModel tablapagos;
    Object[] item_pagos = new Object[3];
    Object[] item_Internos = new Object[4];
    Object[] item_Detalle = new Object[5];
    Venta pven;
    Venta objVenta;
    double total;
    int cantpagos = 0;
    int canttiposPagos = 1;
    private Date fecha;
    int idtipopago;
    MailClient objmail = new MailClient();
    LogicaBoticas objlistabotica = new LogicaBoticas();
    String mensaje;
    private boolean modificado = false;
    //PARA REPORTES
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con = null;
    String report_file;
    JasperViewer view;
    private URL archivo;
    VerificaSistema objsistema;
    Thread showThread;
    LogicaConexion logconex = new LogicaConexion();
    int opcion = 0; // 1 = rendiocn caja ; 2=movimientos de caja

    /** Creates new form RevisionCuadreCaja1 */
    public RevisionCuadreCaja(AplicacionVentas objventa) {
        initComponents();
        obj = objventa;
        if (con == null) {
            con = logconex.RetornaConexion();
        }
        idbotica = obj.getIdbotica();
        this.setLocationRelativeTo(null);
        jLabel47.setText(obj.getUsuario());
        jLabel48.setText(logfecha.ConvierteFecha(logfecha.RetornaFecha()));
        jLabel49.setText(logfecha.RetornaHora());
        tablacuadre = (DefaultTableModel) jTable1.getModel();
        jTable1.setDefaultRenderer(Object.class, new IconCellRenderer());
        tablapagos = (DefaultTableModel) jTable2.getModel();
        tabladocumentos = (DefaultTableModel) jTable3.getModel();
        tablainternos = (DefaultTableModel) jTable4.getModel();
        tablaDetalle = (DefaultTableModel) jTable5.getModel();
        jTable1.setRowHeight(50);
        AparienciaTabla();
        Muestra("");
        this.jLabel53.setVisible(false);
        this.jLabel54.setVisible(false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setEnabledAt(1, false);
        OcultaLabel(false);
    }

    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable2.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
        tcr3.setHorizontalAlignment(SwingConstants.CENTER);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(tcr3);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(tcr3);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable3.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable5.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable5.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable5.getColumnModel().getColumn(4).setCellRenderer(tcr);
    }

    public void run() {
        try {

            if (opcion == 3) {

                jTabbedPane1.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, " INTERNO MODIFICADO ", "MODIFICADO", JOptionPane.INFORMATION_MESSAGE);
                jTabbedPane1.setEnabledAt(2, false);
                OcultaLabel_1(true);
                String correo = objlistabotica.ReornaCorreo(idbotica);
                objmail.sendMail(correo, "INTERNO MODIFICADO", "SE MODIFICO EL TIPO DE PAGO DEL INTERNO : " + mensaje + "" + "\n FECHA : " + logfecha.RetornaFecha() + "\n HORA: " + logfecha.RetornaHora() + " \n BOTICA FELICIDAD  " + idbotica);
                this.jComboBox1.setSelectedIndex(0);
                int fila = jTable1.getSelectedRow();
                pven = new Venta(Fecha, Fecha, idbotica, lista_Ventas.get(fila).getId_Caja(), lista_Ventas.get(fila).getId_Personal_Botica_Caja(), lista_Ventas.get(fila).getIdturno());
                LimpiatTabla_2();
                LimpiatTabla_3();
                LimpiatTabla_4();
                REPORTE_CAJA_TIPOS_PAGOS();
                MuestraDetalle_Resumen();
                Mustra_Internos();
                jTabbedPane1.setEnabledAt(0, true);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(2, false);
                Habilita_Cash(false);
                jCheckBox1.setSelected(false);
                jTextField17.setText("");
                jTextField11.setText("");
                OcultaLabel_1(false);
                this.setDefaultCloseOperation(0);
                modificado = true;
                canttiposPagos = 0;

            } else {

                String sistema = "Windows";
                parameters.put("FECHAFIN", jXDatePicker1.getDate());
                parameters.put("FECHAINICIO", jXDatePicker1.getDate());
                parameters.put("INIDCAJA", lista_Ventas.get(jTable1.getSelectedRow()).getId_Caja());
                parameters.put("INIDBOTICA", idbotica);
                parameters.put("INIDCAJERO", lista_Ventas.get(jTable1.getSelectedRow()).getId_Personal_Botica_Caja());
                parameters.put("TURNO", lista_Ventas.get(jTable1.getSelectedRow()).getIdturno());
                parameters.put("REPORT_CONNECTION", con);

                if (opcion == 1) {
                    archivo = this.getClass().getResource("/Reportes/REPORTE_RENDICION_CAJA.jasper");
                } else if (opcion == 2) {
                    archivo = this.getClass().getResource("/Reportes/REPORTE_VENTAS_DETALLADAS.jasper");
                }


                if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }

                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                view = new JasperViewer(jasperPrint, false);
                view.setTitle("Reporte Detallado de Caja");
                view.setVisible(true);
                view.setSize(870, 700);
                OcultaLabel(false);

            }


        } catch (JRException e) {
            System.out.println(e.getMessage());
            OcultaLabel(false);
            OcultaLabel_1(false);
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void OcultaLabel(boolean valor) {
        this.jLabel51.setVisible(valor);
        this.jLabel52.setVisible(valor);
        buttonTextDown1.setEnabled(!valor);
        buttonTextDown2.setEnabled(!valor);
        buttonTextDown3.setEnabled(!valor);
        buttonTextDown4.setEnabled(!valor);
        buttonTextDown5.setEnabled(!valor);
        buttonTextDown6.setEnabled(!valor);
        jLabel34.setEnabled(!valor);
        jLabel42.setEnabled(!valor);
        jLabel35.setEnabled(!valor);
        jLabel39.setEnabled(!valor);
        jLabel37.setEnabled(!valor);
        jLabel40.setEnabled(!valor);
    }

    private void OcultaLabel_1(boolean valor) {
        this.jLabel53.setVisible(valor);
        this.jLabel54.setVisible(valor);
        jTabbedPane1.setEnabled(!valor);
        jTable4.setEnabled(!valor);
        jTable2.setEnabled(!valor);
        jTable3.setEnabled(!valor);
        jPanel4.setEnabled(!valor);
        jTextField1.setEnabled(!valor);
    }

    private void REPORTE_CAJA_TIPOS_PAGOS() {
        List<Venta> ListaPagos = objVentas.REPORTE_CAJA_TIPOS_PAGOS(pven);

        if (ListaPagos.size() > 0) {
            for (int i = 0; i < ListaPagos.size(); i++) {
                item_pagos[0] = ListaPagos.get(i).getDescripcionTipoPago();
                item_pagos[1] = ListaPagos.get(i).getCantidadpagos();
                item_pagos[2] = ListaPagos.get(i).getTotal();
                tablapagos.addRow(item_pagos);
                total += ListaPagos.get(i).getTotal();
            }

            BigDecimal bd = new BigDecimal(total);

        }
    }

    private void Muestra(String valor) {
        lblInterno.setText(valor);
        lblcliente.setText(valor);
        lbltippago.setText(valor);
        lbltipventa.setText(valor);
        lblserie.setText(valor);
        lblnumero.setText(valor);
        lblvendedor.setText(valor);
        lblhora.setText(valor);
        jLabel27.setText(valor);
        jLabel29.setText(valor);
        lblTotal.setText(valor);
    }

    private void MuestraDetalle_Resumen() {
        List<Venta> LISTA = objVentas.REPORTE_CAJA_SUBDETALLE(pven);

        if (LISTA.size() > 0) {
            for (int i = 0; i < LISTA.size(); i++) {
                item[0] = LISTA.get(i).getDescripcionTipoPago();
                item[1] = LISTA.get(i).getCantidadpagos();
                item[2] = LISTA.get(i).getTotal();
                tabladocumentos.addRow(item);
                total += LISTA.get(i).getTotal();
            }
        }
    }

    private void Listar_Cuadres() {
        Fecha = logfecha.ConvierteFecha(this.jXDatePicker1.getDate());
        LimpiatTabla();

        try {
            lista_Ventas.removeAll(lista_Ventas);
            lista_Ventas = objVentas.Lista_Cuadres_Cajas(idbotica, Fecha);

            if (lista_Ventas.size() > 0) {

                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

                for (int i = 0; i < lista_Ventas.size(); i++) {
                    item[0] = lista_Ventas.get(i).getCajero();
                    item[1] = lista_Ventas.get(i).getMiTurno();
                    item[2] = lista_Ventas.get(i).getCaja();
                    item[3] = lista_Ventas.get(i).getInternoInicio() + " - " + lista_Ventas.get(i).getInternoFinal();
                    item[4] = lista_Ventas.get(i).getRendicion();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/1318792945_application-vnd.ms-excel.png"));

                    if (lista_Ventas.get(i).getCierre() == 1) {
                        JLabel lab = new JLabel(icon);
                        lab.setEnabled(false);
                        item[5] = lab;
                    } else {
                        item[5] = new JLabel(icon);
                    }

                    modelo.addRow(item);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void Mustra_Internos() {
        milista.removeAll(milista);
        milista = objVentas.Lista_Movimeintos_Caja(pven);

        for (int i = 0; i < milista.size(); i++) {
            item_Internos[0] = milista.get(i).getId_Venta();
            item_Internos[1] = milista.get(i).getSerie();
            item_Internos[2] = milista.get(i).getNumero();
            item_Internos[3] = milista.get(i).getTotal();
            tablainternos.addRow(item_Internos);
        }

    }

    private void VisualizaVenta(int fila) {
        if (milista.size() >= 0) {
            lblInterno.setText(this.milista.get(fila).getId_Venta());
            lblcliente.setText(this.milista.get(fila).getNomCliente());
            lbltippago.setText(this.milista.get(fila).getTipopago());
            lbltipventa.setText(this.milista.get(fila).getTipventa());
            lblserie.setText(this.milista.get(fila).getSerie());
            lblnumero.setText(this.milista.get(fila).getNumero());
            lblvendedor.setText(this.milista.get(fila).getVendedor());
            lblhora.setText(this.milista.get(fila).getHora_Venta().toString());
            jLabel27.setText(String.valueOf(this.milista.get(fila).getSubTotal()));
            jLabel29.setText(String.valueOf(this.milista.get(fila).getIGV()));
            lblTotal.setText(String.valueOf(this.milista.get(fila).getTotal()));
            buttonTextDown8.setEnabled(true);
            jLabel55.setText("INTERNO A MODIFICAR : " + milista.get(fila).getId_Venta());
        }
    }

    private void Busca_Interno(String interno) {
        LimpiatTabla_4();
        milista.removeAll(milista);
        milista = objVentas.Busca_Movimientos_Caja(pven, interno);

        for (int i = 0; i < milista.size(); i++) {
            item_Internos[0] = milista.get(i).getId_Venta();
            item_Internos[1] = milista.get(i).getSerie();
            item_Internos[2] = milista.get(i).getNumero();
            item_Internos[3] = milista.get(i).getTotal();
            tablainternos.addRow(item_Internos);
        }

    }

    private void CompletaTiposPagos() {
        int w = jComboBox1.getItemCount();

        if (w == 1) {
            listatipospagos.removeAll(listatipospagos);
            listatipospagos = objfactura.Recupera_Tipos_Pagos(5);
            for (int i = 0; i < listatipospagos.size(); i++) {
                String descrp = listatipospagos.get(i).getId_TipoPago() + " " + listatipospagos.get(i).getDescripcion();
                this.jComboBox1.addItem(descrp);
            }
        }
    }

    private void Verifica_Pago() {

        if (listatipospagos.size() >= 0) {

            int indice = jComboBox1.getSelectedIndex() - 1;
            idtipopago = listatipospagos.get(indice).getId_TipoPago();
            jTextField11.setText("");

            BigDecimal bd1 = new BigDecimal(total);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            total = bd1.doubleValue();

            if (idtipopago == 1)//SI ES CASH
            {
                Habilita_Cash(false);
                this.jTextField17.setText(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField11.setText(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            } else {
                if (idtipopago == 7)//SI ES NOTA DE CREDITO
                {
                    Habilita_EsNotaCredito(false);
                } else {
                    if (idtipopago == 6)// SI ES CHEQUE
                    {
                        Habilita_EsCheque(false);
                        this.jTextField10.requestFocus();
                    } else {
                        if (idtipopago == 23)//SI ES ABONO
                        {
                            Habilita_Abono(true);
                            Habilita_ESAbono(false);
                            jTextField39.requestFocus();
                        } else {
                            if (idtipopago == 15)//SI ES PAGOS DIVERSOS
                            {
                                Habilita_PagosDiversos(false);
                            } else {
                                if (idtipopago == 20 || idtipopago == 16 || idtipopago == 17 || idtipopago == 18 || idtipopago == 5 || idtipopago == 10 || idtipopago == 3) {
                                    Deshabilta_Tarjetas(false);

                                } else {
                                    this.jCheckBox1.setSelected(false);
                                    this.jCheckBox2.setSelected(false);
                                    this.jCheckBox3.setSelected(false);
                                    this.jCheckBox4.setSelected(false);

                                    this.jCheckBox1.setEnabled(false);
                                    this.jCheckBox2.setEnabled(false);
                                    this.jCheckBox3.setEnabled(false);
                                    this.jCheckBox4.setEnabled(false);

                                    this.jTextField10.setText("");
                                    this.jTextField18.setText("");
                                    this.jTextField19.setText("");
                                    this.jTextField17.setText("");
                                    this.jTextField13.setText("");
                                    this.jTextField12.setText("");

                                    this.jTextField17.setEnabled(false);
                                    this.jTextField18.setEnabled(false);
                                    this.jTextField19.setEnabled(false);
                                    this.jTextField10.setEnabled(false);
                                    this.jTextField18.setEnabled(false);
                                    this.jTextField12.setEnabled(false);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private void Habilita_Cash(boolean valor) {
        this.jCheckBox2.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox1.setEnabled(!valor);
        this.jCheckBox1.setSelected(!valor);

        this.jTextField17.setEnabled(!valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField13.setEnabled(valor);
        this.jTextField12.setEnabled(valor);

        this.jTextField10.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        this.jTextField13.setText("");
        this.jComboBox5.setEnabled(valor);
        Habilita_Abono(valor);

    }

    private void Habilita_EsNotaCredito(boolean valor) {
        jCheckBox2.setSelected(valor);
        jCheckBox3.setSelected(valor);
        jCheckBox1.setSelected(valor);
        jCheckBox4.setSelected(!valor);

        jCheckBox1.setEnabled(valor);
        jCheckBox2.setEnabled(valor);
        jCheckBox3.setEnabled(valor);
        jCheckBox4.setEnabled(!valor);

        jTextField17.setEnabled(valor);
        jTextField17.setText("");
        jTextField10.setText("");
        jTextField13.setText("");
        jTextField10.setEnabled(valor);
        jTextField13.setEnabled(valor);
        jTextField18.setEnabled(valor);
        jTextField18.setEditable(valor);
        jTextField12.setEditable(!valor);
        jTextField12.setEnabled(!valor);
        jComboBox5.setEnabled(valor);
        jTextField12.requestFocus();
        jTextField18.setText("");
        jTextField6.setText("");
        Habilita_Abono(valor);

    }

    private void Habilita_EsCheque(boolean valor) {
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(!valor);
        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField17.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        this.jTextField10.setText("");
        this.jTextField13.setText("");
        this.jComboBox5.setEnabled(!valor);
        this.jTextField10.setEnabled(!valor);
        this.jTextField13.setEnabled(!valor);
        this.jTextField13.setEnabled(!valor);
        RecuperaBancos();
        this.jTextField13.requestFocus();
        Habilita_Abono(valor);
    }

    private void Habilita_ESAbono(boolean valor) {
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox3.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox3.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jTextField17.setEnabled(valor);
        this.jTextField18.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField17.setText("");
        this.jTextField18.setText("");
        this.jTextField19.setText("");
        this.jTextField12.setText("");
        this.jTextField10.setEnabled(valor);
        this.jTextField13.setEnabled(valor);
        this.jTextField39.setText("");
        this.jTextField38.setText("");
        jTextField10.setText("");
        jTextField13.setText("");
        jComboBox5.setEnabled(valor);
        jCheckBox2.setSelected(valor);
    }

    private void Habilita_PagosDiversos(boolean valor) {
        jCheckBox1.setSelected(valor);
        jCheckBox2.setSelected(valor);
        jCheckBox3.setSelected(valor);
        jCheckBox4.setSelected(valor);
        jCheckBox5.setSelected(valor);
        jCheckBox1.setEnabled(!valor);
        jCheckBox2.setEnabled(!valor);
        jCheckBox3.setEnabled(!valor);
        jCheckBox4.setEnabled(!valor);
        jTextField17.setEnabled(valor);
        jTextField17.setText("");
        jTextField10.setEnabled(valor);
        jTextField18.setEnabled(valor);
        jTextField13.setEnabled(valor);
        jCheckBox3.setEnabled(!valor);
        jTextField18.setEnabled(valor);
        jComboBox5.setEnabled(valor);
        jTextField11.setText("");
        Habilita_Abono(!valor);
        jCheckBox5.setSelected(valor);
        jTextField19.setText("");
        jTextField12.setText("");
        jTextField18.setText("");
        jTextField6.setText("");
    }

    private void Deshabilta_Tarjetas(boolean valor) {

        this.jCheckBox2.setSelected(valor);
        this.jCheckBox4.setSelected(valor);
        this.jCheckBox1.setSelected(valor);
        this.jCheckBox2.setEnabled(valor);
        this.jCheckBox4.setEnabled(valor);
        this.jCheckBox1.setEnabled(valor);

        this.jCheckBox3.setEnabled(!valor);
        this.jTextField17.setText("");
        this.jTextField10.setText("");
        this.jTextField19.setText("");
        this.jTextField18.setText("");
        this.jTextField11.setText("");
        this.jTextField12.setText("");
        this.jTextField6.setText("");
        this.jTextField13.setText("");
        this.jTextField17.setEnabled(valor);
        this.jTextField12.setEnabled(valor);
        this.jTextField10.setEnabled(valor);
        this.jTextField19.setEnabled(valor);
        this.jTextField11.setEnabled(valor);

        this.jComboBox5.setEnabled(!valor);
        this.jTextField6.setEnabled(!valor);
        this.jTextField18.setEnabled(!valor);
        this.jComboBox2.setEnabled(!valor);
        this.jComboBox5.setEnabled(valor);
        this.jTextField18.setEnabled(!valor);
        this.jTextField18.setEditable(!valor);
        this.jComboBox2.setEnabled(!valor);
        this.jTextField18.requestFocus();
        RecuperaTarjetas();
        Habilita_Abono(valor);

    }

    private void Habilita_Abono(boolean valor) {
        jCheckBox5.setEnabled(valor);
        jCheckBox5.setSelected(valor);
        jTextField38.setEnabled(valor);
        jTextField39.setEnabled(valor);
        jTextField38.setText("");
        jTextField39.setText("");
        jComboBox7.setEnabled(valor);
        RecuperaBancos_1();
    }

    private void RecuperaBancos() {

        if (this.jComboBox5.getItemCount() == 0) {
            List<String> listaBancos = this.logventa.Lista_Bancos();
            this.jComboBox5.setEnabled(true);
            for (int i = 0; i < listaBancos.size(); i++) {
                this.jComboBox5.addItem(listaBancos.get(i));
            }
        }

    }

    private void RecuperaBancos_1() {

        if (this.jComboBox7.getItemCount() == 0) {
            List<String> listaBancos = this.logventa.Lista_Bancos();
            this.jComboBox7.setEnabled(true);
            for (int i = 0; i < listaBancos.size(); i++) {
                this.jComboBox7.addItem(listaBancos.get(i));
            }
        }

    }

    private void RecuperaTarjetas() {
        try {

            int w = jComboBox2.getItemCount();

            if (w == 0) {
                listtarj = objlogicapagos.retornaTarjetas(1);

                for (int i = 0; i < listtarj.size(); i++) {
                    String texto = listtarj.get(i).getDescripcion();
                    this.jComboBox2.addItem(texto);
                }

            }

        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA RecuperaTarjetas " + ex.getMessage());
        }
    }

    private void MuestraNota_Credito() {
        ListaNotasCreditos notadcr = new ListaNotasCreditos(objetoventana, idbotica);
        notadcr.pack();
        notadcr.setVisible(true);

        try {

            String document = notadcr.getNumero_Documento();

            if (document != null) {
                this.jTextField12.setText(notadcr.getNumero_Documento());
                this.jTextField19.setText(String.valueOf(notadcr.getTotal()));
                this.jTextField11.setText(String.valueOf(notadcr.getTotal()));
            }

        } catch (Exception ex) {
        }

    }

    private void LimpiatTabla() {
        int cant = this.jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablacuadre.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_2() {
        int cant = this.jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablapagos.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_3() {
        int cant = this.jTable3.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tabladocumentos.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_4() {
        int cant = this.jTable4.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablainternos.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_5() {
        int cant = this.jTable5.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaDetalle.removeRow(i);
            }
        }
    }

    public class IconCellRenderer extends DefaultTableCellRenderer {

        /**
         * Acá redefinimos como se muestra, vemos q ahora lo forzamos a
         * trabajar con JLabel, pero si no lo es, por ejemplo un String
         * igual lo muestro llamando a Super
         */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JLabel) {
                JLabel label = (JLabel) value;
                label.setOpaque(true);
                fillColor(table, label, isSelected);
                return label;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }



        }

        public boolean isCellEditable(int rowIndex, int colIndex) {

            if (colIndex == 5) {
                return true; //Disallow the editing of any cell
            }
            return false;
        }

        /**
         * Este método es para que pinte el fondo del JLabel cuando
         * lo seleccionamos para que no quede en blanco, desentonando
         * con el resto de las celdas que no son JLabel
         */
        public void fillColor(JTable t, JLabel l, boolean isSelected) {
            if (isSelected) {
                l.setBackground(t.getSelectionBackground());
                l.setForeground(t.getSelectionForeground());
            } else {
                l.setBackground(t.getBackground());
                l.setForeground(t.getForeground());
            }
        }
    }

    private void Deshabilita_Principal(boolean valor) {
        jTabbedPane2.setEnabledAt(0, valor);
        jTabbedPane2.setSelectedIndex(1);
    }

    private void Deshabilita_Rendicion(boolean valor) {
        jTabbedPane2.setEnabledAt(1, valor);
        jTabbedPane2.setSelectedIndex(0);
    }

    private void CalculaMontoIngresado() {
        String mens = null;
        double calc1 = 0.0;

        try {

            if (this.jTextField10.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField10.getText().trim();
                calc1 += Double.parseDouble(tota);
            }
            if (this.jTextField17.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField17.getText().trim();
                calc1 += Double.parseDouble(tota);
            }
            if (this.jTextField18.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField18.getText().trim();
                calc1 += Double.parseDouble(tota);

            }
            if (this.jTextField19.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField19.getText().trim();
                calc1 += Double.parseDouble(tota);
            }

            if (this.jTextField39.getText().compareTo("") != 0) {
                String tota;
                tota = this.jTextField39.getText().trim();
                calc1 += Double.parseDouble(tota);
            }

            BigDecimal bd2 = new BigDecimal(calc1);
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            calc1 = bd2.doubleValue();


            this.jTextField11.setText(String.valueOf(calc1));


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            this.jTextField11.setText(String.valueOf(calc1));
        }
    }

    private boolean Modifica_Pago_Interno() {
        try {

            if (listatipospagos.size() >= 0) {

                int indice = jComboBox1.getSelectedIndex() - 1;
                idtipopago = listatipospagos.get(indice).getId_TipoPago();
                int resultado = 0;
                this.fecha = milista.get(jTable4.getSelectedRow()).getFecha_Venta();
                boolean error = true;
                double monto = 0;

                if (idtipopago == 23) {
                    if (jTextField38.getText().trim().compareTo("") != 0) {
                        if (jTextField39.getText().trim().compareTo("") != 0) {
                            double mitottal = Double.parseDouble(jTextField39.getText().trim());
                            BigDecimal bd1 = new BigDecimal(mitottal);
                            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                            mitottal = bd1.doubleValue();

                            if (mitottal >= total) {
                                String doc = jTextField38.getText().trim();
                                objeto = new Ventas_Tipo_Pago(idbotica, idtipopago, total, fecha, doc, jComboBox7.getSelectedItem().toString());
                                lista_pagos.add(objeto);
                                jTextField11.setText("");
                                jTextField38.setText("");
                                jTextField39.setText("");
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n EL IMPORTE DEL ABONO ES MENOR QUE EL MONTO DE VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN IMPORTE VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN NUMERO DE OPERACION VALIDO ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                    resultado = VerificaPagosDiversos();

                    if (resultado == 2) {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS \n NO PUEDES INGRESAR UN MONTO 0.00", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    } else {
                        if (resultado == 7) {
                            if (error) {
                                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n PARA PAGOS DIVERSOS ES NECESARIO SELECCIONAR DOS PAGOS FRACCIONADOS ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            if (resultado == 5) {
                                if (error) {
                                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UN NUMERO DE DOCUMENTO ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                if (error) {
                                    if (resultado == -1) {
                                        JOptionPane.showMessageDialog(this, "LO SENTIMOS  NO PUEDE INGRESAR UNA CANTIDAD NEGATIVA EN UN PAGO  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (jTextField11.getText().trim().compareTo("") != 0) {
                                            monto = Double.parseDouble(this.jTextField11.getText().trim());
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                return false;

            }

        } catch (Exception ex) {
            System.out.println();
        }

        return false;

    }

    private int VerificaPagosDiversos() {
        String doc = "";
        String val = null;
        canttiposPagos = 0;
        lista_pagos.removeAll(lista_pagos);


        try {

            if (this.jCheckBox1.isSelected()) {
                canttiposPagos++;

                if (this.jTextField17.getText().trim().compareTo("") != 0) {

                    BigDecimal bd2 = new BigDecimal(jTextField17.getText().trim());
                    bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                    double mival = bd2.doubleValue();

                    if (mival != 0.00) {
                        val = this.jTextField17.getText().trim();
                        int id = Integer.parseInt(jCheckBox1.getToolTipText());
                        double valor = Double.parseDouble(val);

                        if (valor == 0) {
                            return 3;
                        } else {
                            if (valor < 0) {
                                return -1;
                            } else {
                                objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, "");
                                lista_pagos.add(objeto);
                            }
                        }
                    } else {
                        jTextField17.requestFocus();
                        return 2;
                    }
                } else {
                    canttiposPagos = 0;
                    jTextField17.requestFocus();
                    return 2;
                }
            }
            if (this.jCheckBox4.isSelected()) {
                canttiposPagos++;

                if (this.jTextField19.getText().trim().compareTo("") != 0) {
                    if (this.jTextField12.getText().trim().compareTo("") != 0) {

                        BigDecimal bd2 = new BigDecimal(jTextField19.getText().trim());
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        double mival = bd2.doubleValue();

                        if (mival != 0.00) {
                            val = this.jTextField19.getText().trim();
                            int id = Integer.parseInt(jCheckBox4.getToolTipText());
                            double valor = Double.parseDouble(val);

                            if (valor == 0) {
                                return 3;
                            } else {
                                if (valor < 0) {
                                    return -1;
                                } else {
                                    doc = this.jTextField12.getText().trim();
                                    objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, "");
                                    lista_pagos.add(objeto);
                                }
                            }

                        } else {
                            jTextField19.requestFocus();
                            return 2;
                        }

                    } else {
                        return 5;
                    }

                } else {
                    canttiposPagos = 0;
                    jTextField19.requestFocus();
                    return 2;
                }

            }
            if (this.jCheckBox2.isSelected()) {
                canttiposPagos++;

                if (this.jTextField10.getText().trim().compareTo("") != 0) {
                    if (this.jTextField13.getText().trim().compareTo("") != 0) {

                        BigDecimal bd2 = new BigDecimal(jTextField10.getText().trim());
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        double mival = bd2.doubleValue();

                        if (mival != 0.00) {
                            val = this.jTextField10.getText().trim();
                            int id = Integer.parseInt(jCheckBox2.getToolTipText());
                            double valor = Double.parseDouble(val);

                            if (valor == 0) {
                                return 3;
                            } else {
                                if (valor < 0) {
                                    return -1;
                                } else {
                                    doc = this.jTextField13.getText().trim();
                                    objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, jComboBox5.getSelectedItem().toString());
                                    lista_pagos.add(objeto);
                                }
                            }
                        } else {
                            this.jTextField19.requestFocus();
                            return 2;
                        }

                    } else {
                        this.jTextField10.requestFocus();
                        return 5;

                    }

                } else {
                    jTextField19.requestFocus();
                    canttiposPagos = 0;
                    return 2;
                }


            }
            if (this.jCheckBox3.isSelected()) {
                canttiposPagos++;

                if (this.jTextField18.getText().trim().compareTo("") != 0) {

                    BigDecimal bd2 = new BigDecimal(jTextField18.getText().trim());
                    bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                    double mival = bd2.doubleValue();

                    if (mival != 0.00) {
                        val = this.jTextField18.getText().trim();
                        double valor = Double.parseDouble(val);

                        if (valor == 0) {
                            return 3;
                        } else {
                            if (valor < 0) {
                                return -1;
                            } else {
                                doc = this.jTextField6.getText().trim();
                                int idpa = listtarj.get(this.jComboBox2.getSelectedIndex()).getId_TipoPago();
                                objeto = new Ventas_Tipo_Pago(idbotica, idpa, valor, fecha, doc, this.jComboBox2.getSelectedItem().toString());
                                lista_pagos.add(objeto);
                            }
                        }
                    } else {
                        jTextField18.requestFocus();
                        canttiposPagos = 0;
                        return 2;
                    }

                } else {
                    jTextField18.requestFocus();
                    canttiposPagos = 0;
                    return 2;
                }
            }

            if (this.jCheckBox5.isSelected()) {
                canttiposPagos++;

                if (jTextField39.getText().trim().compareTo("") != 0) {
                    if (this.jTextField38.getText().trim().compareTo("") != 0) {

                        BigDecimal bd2 = new BigDecimal(jTextField39.getText().trim());
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        double mival = bd2.doubleValue();

                        if (mival != 0.00) {
                            val = this.jTextField39.getText().trim();
                            int id = Integer.parseInt(jCheckBox5.getToolTipText());
                            double valor = Double.parseDouble(val);

                            if (valor == 0) {
                                return 3;
                            } else {
                                if (valor < 0) {
                                    return -1;
                                } else {
                                    doc = jTextField38.getText().trim();
                                    objeto = new Ventas_Tipo_Pago(idbotica, id, valor, fecha, doc, jComboBox7.getSelectedItem().toString());
                                    lista_pagos.add(objeto);
                                }
                            }
                        } else {
                            canttiposPagos = 0;
                            jTextField39.requestFocus();
                            return 2;
                        }


                    } else {
                        jTextField38.requestFocus();
                        return 5;
                    }

                } else {
                    jTextField39.requestFocus();
                    return 2;
                }
            }

            if (canttiposPagos > 2) {
                lista_pagos.removeAll(lista_pagos);
                canttiposPagos = 0;
                return 1;
            } else if (canttiposPagos == 0) {
                return 4;
            } else if (canttiposPagos < 2 && listatipospagos.get(jComboBox1.getSelectedIndex() - 1).getId_TipoPago() == 15) {
                return 7;
            } else {

                Ventas_Tipo_Pago aux;
                Ventas_Tipo_Pago aux_1;

                for (int i = 0; i < lista_pagos.size(); i++) {
                    for (int k = i + 1; k < lista_pagos.size(); k++) {
                        if (lista_pagos.get(k).getMonto() > lista_pagos.get(i).getMonto()) {
                            aux = lista_pagos.get(i);
                            aux_1 = lista_pagos.get(k);
                            lista_pagos.set(i, aux_1);
                            lista_pagos.set(k, aux);
                            i = lista_pagos.size();
                            break;
                        }
                    }
                }
                return 0;
            }  //OK GUARDA VENTA

        } catch (Exception ex) {
            return 4;
        }
    }

    private void Cierre_Revision() {

        int pe = JOptionPane.showConfirmDialog(null, " Confirmar Cierre  ", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (pe == JOptionPane.YES_OPTION) {

            if (logventa.Cierre_Revision_Caja(pven)) {
                JOptionPane.showMessageDialog(this, " INFORME CERRADO ", "CERRADO", JOptionPane.INFORMATION_MESSAGE);
                jTabbedPane2.setEnabledAt(0, true);
                jTabbedPane2.setEnabledAt(1, false);
                jTabbedPane2.setSelectedIndex(0);
                Listar_Cuadres();
                mensaje = "";
                modificado = false;
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new org.edisoncor.gui.tabbedPane.TabbedPaneRound();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        tabbedPaneHeader1 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel9 = new javax.swing.JPanel();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jLabel33 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonTextDown4 = new org.edisoncor.gui.button.ButtonTextDown();
        buttonTextDown5 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        buttonTextDown6 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel37 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        buttonTextDown1 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel42 = new javax.swing.JLabel();
        buttonTextDown2 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel35 = new javax.swing.JLabel();
        buttonTextDown3 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        lblInternos = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTabbedPane1 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel14 = new javax.swing.JPanel();
        lblvendedor = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblnumero = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblInterno = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblcliente = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        buttonTextDown9 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel20 = new javax.swing.JLabel();
        lbltippago = new javax.swing.JLabel();
        lbltipventa = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        buttonTextDown8 = new org.edisoncor.gui.button.ButtonTextDown();
        lblserie = new javax.swing.JLabel();
        buttonTextDown10 = new org.edisoncor.gui.button.ButtonTextDown();
        jLabel21 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        buttonTextDown12 = new org.edisoncor.gui.button.ButtonTextDown();
        jPanel16 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox();
        buttonTextDown7 = new org.edisoncor.gui.button.ButtonTextDown();
        jTextField18 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jTextField39 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        buttonTextDown11 = new org.edisoncor.gui.button.ButtonTextDown();
        jComboBox7 = new javax.swing.JComboBox();
        jTextField19 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField10 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField17 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(RevisionCuadreCaja.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jTabbedPane2.setBackground(resourceMap.getColor("jTabbedPane2.background")); // NOI18N
        jTabbedPane2.setForeground(resourceMap.getColor("jTabbedPane2.foreground")); // NOI18N
        jTabbedPane2.setName("jTabbedPane2"); // NOI18N

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel6.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setBackground(resourceMap.getColor("jTable1.background")); // NOI18N
        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setForeground(resourceMap.getColor("jTable1.foreground")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAJERO", "CAJA", "TURNO", "INTERNO", "RENDICION", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(resourceMap.getColor("jTable1.gridColor")); // NOI18N
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setOpaque(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowVerticalLines(false);
        jTable1.setTableHeader(null);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setResizable(false);

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel6.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel44.setForeground(resourceMap.getColor("jLabel44.foreground")); // NOI18N
        jLabel44.setText(resourceMap.getString("jLabel44.text")); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setForeground(resourceMap.getColor("jLabel44.foreground")); // NOI18N
        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        jLabel46.setForeground(resourceMap.getColor("jLabel44.foreground")); // NOI18N
        jLabel46.setText(resourceMap.getString("jLabel46.text")); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N

        jLabel47.setForeground(resourceMap.getColor("jLabel48.foreground")); // NOI18N
        jLabel47.setText(resourceMap.getString("jLabel47.text")); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N

        jLabel48.setForeground(resourceMap.getColor("jLabel48.foreground")); // NOI18N
        jLabel48.setText(resourceMap.getString("jLabel48.text")); // NOI18N
        jLabel48.setName("jLabel48"); // NOI18N

        jLabel49.setForeground(resourceMap.getColor("jLabel48.foreground")); // NOI18N
        jLabel49.setText(resourceMap.getString("jLabel49.text")); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(594, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(366, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(361, 361, 361))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(513, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(339, 339, 339))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(170, 170, 170)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(127, 127, 127))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(89, 89, 89)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel49))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9))
                .addGap(259, 259, 259)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(113, 113, 113)))
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jPanel11.setName("jPanel11"); // NOI18N

        tabbedPaneHeader1.setFont(resourceMap.getFont("tabbedPaneHeader1.font")); // NOI18N
        tabbedPaneHeader1.setName("tabbedPaneHeader1"); // NOI18N

        jPanel9.setBackground(resourceMap.getColor("jPanel9.background")); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N

        panelNice1.setBackground(resourceMap.getColor("panelNice1.background")); // NOI18N
        panelNice1.setForeground(resourceMap.getColor("panelNice1.foreground")); // NOI18N
        panelNice1.setName("panelNice1"); // NOI18N

        jLabel33.setFont(resourceMap.getFont("jLabel33.font")); // NOI18N
        jLabel33.setForeground(resourceMap.getColor("jLabel33.foreground")); // NOI18N
        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        jLabel14.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel13.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel3.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel15.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setForeground(resourceMap.getColor("jLabel16.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabel36.setName("jLabel36"); // NOI18N

        jLabel38.setName("jLabel38"); // NOI18N

        jLabel41.setName("jLabel41"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(resourceMap.getColor("jPanel1.border.border.lineColor"), 2, true), resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setForeground(resourceMap.getColor("jPanel1.foreground")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        buttonTextDown4.setBackground(resourceMap.getColor("buttonTextDown4.background")); // NOI18N
        buttonTextDown4.setText(resourceMap.getString("buttonTextDown4.text")); // NOI18N
        buttonTextDown4.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown4.setName("buttonTextDown4"); // NOI18N
        buttonTextDown4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown4ActionPerformed(evt);
            }
        });

        buttonTextDown5.setBackground(resourceMap.getColor("buttonTextDown5.background")); // NOI18N
        buttonTextDown5.setText(resourceMap.getString("buttonTextDown5.text")); // NOI18N
        buttonTextDown5.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown5.setName("buttonTextDown5"); // NOI18N
        buttonTextDown5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown5ActionPerformed(evt);
            }
        });

        jLabel40.setIcon(resourceMap.getIcon("jLabel40.icon")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        jLabel39.setIcon(resourceMap.getIcon("jLabel39.icon")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        buttonTextDown6.setBackground(resourceMap.getColor("buttonTextDown6.background")); // NOI18N
        buttonTextDown6.setText(resourceMap.getString("buttonTextDown6.text")); // NOI18N
        buttonTextDown6.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown6.setName("buttonTextDown6"); // NOI18N
        buttonTextDown6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown6ActionPerformed(evt);
            }
        });

        jLabel37.setIcon(resourceMap.getIcon("jLabel37.icon")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N

        jLabel34.setIcon(resourceMap.getIcon("jLabel34.icon")); // NOI18N
        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N

        buttonTextDown1.setBackground(resourceMap.getColor("buttonTextDown1.background")); // NOI18N
        buttonTextDown1.setText(resourceMap.getString("buttonTextDown1.text")); // NOI18N
        buttonTextDown1.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown1.setName("buttonTextDown1"); // NOI18N
        buttonTextDown1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown1ActionPerformed(evt);
            }
        });

        jLabel42.setIcon(resourceMap.getIcon("jLabel42.icon")); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N

        buttonTextDown2.setBackground(resourceMap.getColor("buttonTextDown2.background")); // NOI18N
        buttonTextDown2.setText(resourceMap.getString("buttonTextDown2.text")); // NOI18N
        buttonTextDown2.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown2.setName("buttonTextDown2"); // NOI18N
        buttonTextDown2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown2ActionPerformed(evt);
            }
        });

        jLabel35.setIcon(resourceMap.getIcon("jLabel35.icon")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        buttonTextDown3.setBackground(resourceMap.getColor("buttonTextDown3.background")); // NOI18N
        buttonTextDown3.setText(resourceMap.getString("buttonTextDown3.text")); // NOI18N
        buttonTextDown3.setFont(resourceMap.getFont("buttonTextDown4.font")); // NOI18N
        buttonTextDown3.setName("buttonTextDown3"); // NOI18N
        buttonTextDown3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown3ActionPerformed(evt);
            }
        });

        jLabel50.setIcon(resourceMap.getIcon("jLabel50.icon")); // NOI18N
        jLabel50.setText(resourceMap.getString("jLabel50.text")); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonTextDown4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTextDown1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(220, 220, 220)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonTextDown2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonTextDown5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(108, 108, 108)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonTextDown6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonTextDown3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel39)
                        .addGap(205, 205, 205)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)))
                .addComponent(jLabel50)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonTextDown1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonTextDown3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonTextDown2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTextDown4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTextDown5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTextDown6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel51.setFont(resourceMap.getFont("jLabel51.font")); // NOI18N
        jLabel51.setForeground(resourceMap.getColor("jLabel51.foreground")); // NOI18N
        jLabel51.setText(resourceMap.getString("jLabel51.text")); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N

        jLabel52.setIcon(resourceMap.getIcon("jLabel52.icon")); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(577, Short.MAX_VALUE))
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(543, Short.MAX_VALUE))
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(417, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(664, 664, 664)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNice1Layout.createSequentialGroup()
                    .addContainerGap(572, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(376, 376, 376)))
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGap(147, 147, 147))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel51))
                .addContainerGap())
            .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelNice1Layout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addComponent(jLabel41)
                    .addContainerGap(362, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(panelNice1, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneHeader1.addTab(resourceMap.getString("jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        jPanel8.setBackground(resourceMap.getColor("jPanel8.background")); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable2.setBackground(resourceMap.getColor("jTable2.background")); // NOI18N
        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setForeground(resourceMap.getColor("jTable2.foreground")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion Pagos", "Cantidad", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowHorizontalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
        jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
        jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable3.setBackground(resourceMap.getColor("jTable3.background")); // NOI18N
        jTable3.setFont(resourceMap.getFont("jTable3.font")); // NOI18N
        jTable3.setForeground(resourceMap.getColor("jTable2.foreground")); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setName("jTable3"); // NOI18N
        jTable3.setSelectionBackground(resourceMap.getColor("jTable3.selectionBackground")); // NOI18N
        jTable3.setShowHorizontalLines(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        jLabel30.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        lblInternos.setFont(resourceMap.getFont("lblInternos.font")); // NOI18N
        lblInternos.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        lblInternos.setText(resourceMap.getString("lblInternos.text")); // NOI18N
        lblInternos.setName("lblInternos"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable4.setBackground(resourceMap.getColor("jTable4.background")); // NOI18N
        jTable4.setForeground(resourceMap.getColor("jTable2.foreground")); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INTERNO", "SERIE", "NUMERO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable4.setName("jTable4"); // NOI18N
        jTable4.setSelectionBackground(resourceMap.getColor("jTable4.selectionBackground")); // NOI18N
        jTable4.setSelectionForeground(resourceMap.getColor("jTable4.selectionForeground")); // NOI18N
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.setShowHorizontalLines(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable4KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.getColumnModel().getColumn(0).setResizable(false);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable4.columnModel.title0")); // NOI18N
        jTable4.getColumnModel().getColumn(1).setResizable(false);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable4.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable4.columnModel.title1")); // NOI18N
        jTable4.getColumnModel().getColumn(2).setResizable(false);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable4.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable4.columnModel.title2")); // NOI18N
        jTable4.getColumnModel().getColumn(3).setResizable(false);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable4.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable4.columnModel.title3")); // NOI18N

        jLabel31.setForeground(resourceMap.getColor("jLabel30.foreground")); // NOI18N
        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, resourceMap.getColor("jTabbedPane1.border.highlightColor"), resourceMap.getColor("jTabbedPane1.border.shadowColor"))); // NOI18N
        jTabbedPane1.setFont(resourceMap.getFont("jTabbedPane1.font")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel14.setBackground(resourceMap.getColor("jPanel14.background")); // NOI18N
        jPanel14.setName("jPanel14"); // NOI18N

        lblvendedor.setForeground(resourceMap.getColor("lblvendedor.foreground")); // NOI18N
        lblvendedor.setText(resourceMap.getString("lblvendedor.text")); // NOI18N
        lblvendedor.setName("lblvendedor"); // NOI18N

        jLabel23.setForeground(resourceMap.getColor("jLabel23.foreground")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        lblnumero.setForeground(resourceMap.getColor("lblnumero.foreground")); // NOI18N
        lblnumero.setText(resourceMap.getString("lblnumero.text")); // NOI18N
        lblnumero.setName("lblnumero"); // NOI18N

        jLabel26.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel26.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel27.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel27.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel24.setForeground(resourceMap.getColor("jLabel24.foreground")); // NOI18N
        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        lblhora.setForeground(resourceMap.getColor("lblhora.foreground")); // NOI18N
        lblhora.setText(resourceMap.getString("lblhora.text")); // NOI18N
        lblhora.setName("lblhora"); // NOI18N

        jLabel25.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel25.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        lblTotal.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        lblTotal.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        lblTotal.setText(resourceMap.getString("lblTotal.text")); // NOI18N
        lblTotal.setName("lblTotal"); // NOI18N

        jLabel28.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel28.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel29.setForeground(resourceMap.getColor("jLabel25.foreground")); // NOI18N
        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        lblInterno.setForeground(resourceMap.getColor("lblInterno.foreground")); // NOI18N
        lblInterno.setText(resourceMap.getString("lblInterno.text")); // NOI18N
        lblInterno.setName("lblInterno"); // NOI18N

        jLabel17.setForeground(resourceMap.getColor("jLabel17.foreground")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        lblcliente.setForeground(resourceMap.getColor("lblcliente.foreground")); // NOI18N
        lblcliente.setText(resourceMap.getString("lblcliente.text")); // NOI18N
        lblcliente.setName("lblcliente"); // NOI18N

        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        buttonTextDown9.setBackground(resourceMap.getColor("buttonTextDown9.background")); // NOI18N
        buttonTextDown9.setText(resourceMap.getString("buttonTextDown9.text")); // NOI18N
        buttonTextDown9.setFont(resourceMap.getFont("buttonTextDown9.font")); // NOI18N
        buttonTextDown9.setName("buttonTextDown9"); // NOI18N
        buttonTextDown9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown9ActionPerformed(evt);
            }
        });

        jLabel20.setForeground(resourceMap.getColor("jLabel20.foreground")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        lbltippago.setForeground(resourceMap.getColor("lbltippago.foreground")); // NOI18N
        lbltippago.setText(resourceMap.getString("lbltippago.text")); // NOI18N
        lbltippago.setName("lbltippago"); // NOI18N

        lbltipventa.setForeground(resourceMap.getColor("lbltipventa.foreground")); // NOI18N
        lbltipventa.setText(resourceMap.getString("lbltipventa.text")); // NOI18N
        lbltipventa.setName("lbltipventa"); // NOI18N

        jLabel22.setForeground(resourceMap.getColor("jLabel22.foreground")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        buttonTextDown8.setBackground(resourceMap.getColor("buttonTextDown8.background")); // NOI18N
        buttonTextDown8.setText(resourceMap.getString("buttonTextDown8.text")); // NOI18N
        buttonTextDown8.setEnabled(false);
        buttonTextDown8.setFont(resourceMap.getFont("buttonTextDown9.font")); // NOI18N
        buttonTextDown8.setName("buttonTextDown8"); // NOI18N
        buttonTextDown8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown8ActionPerformed(evt);
            }
        });

        lblserie.setForeground(resourceMap.getColor("lblserie.foreground")); // NOI18N
        lblserie.setText(resourceMap.getString("lblserie.text")); // NOI18N
        lblserie.setName("lblserie"); // NOI18N

        buttonTextDown10.setBackground(resourceMap.getColor("buttonTextDown10.background")); // NOI18N
        buttonTextDown10.setText(resourceMap.getString("buttonTextDown10.text")); // NOI18N
        buttonTextDown10.setFont(resourceMap.getFont("buttonTextDown10.font")); // NOI18N
        buttonTextDown10.setName("buttonTextDown10"); // NOI18N
        buttonTextDown10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown10ActionPerformed(evt);
            }
        });

        jLabel21.setForeground(resourceMap.getColor("jLabel21.foreground")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbltipventa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblserie, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(lblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84))
                            .addComponent(lbltippago, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(lblInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(lblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(buttonTextDown8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(buttonTextDown10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonTextDown9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblInterno)
                    .addComponent(jLabel24)
                    .addComponent(lblhora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcliente)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltippago)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltipventa)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblserie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnumero)
                    .addComponent(jLabel21))
                .addGap(8, 8, 8)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblvendedor))
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel25)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTextDown8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTextDown9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTextDown10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel14.TabConstraints.tabTitle"), jPanel14); // NOI18N

        jPanel15.setBackground(resourceMap.getColor("jPanel15.background")); // NOI18N
        jPanel15.setName("jPanel15"); // NOI18N

        jLabel43.setForeground(resourceMap.getColor("jLabel43.foreground")); // NOI18N
        jLabel43.setText(resourceMap.getString("jLabel43.text")); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTable5.setBackground(resourceMap.getColor("jTable5.background")); // NOI18N
        jTable5.setFont(resourceMap.getFont("jTable5.font")); // NOI18N
        jTable5.setForeground(resourceMap.getColor("jTable5.foreground")); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "PRODUCTO", "UND", "FRACC", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable5.setName("jTable5"); // NOI18N
        jTable5.setSelectionBackground(resourceMap.getColor("jTable5.selectionBackground")); // NOI18N
        jTable5.setSelectionForeground(resourceMap.getColor("jTable5.selectionForeground")); // NOI18N
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.setShowHorizontalLines(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable5);
        jTable5.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.getColumnModel().getColumn(0).setResizable(false);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTable5.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable5.columnModel.title0")); // NOI18N
        jTable5.getColumnModel().getColumn(1).setResizable(false);
        jTable5.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable5.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable5.columnModel.title1")); // NOI18N
        jTable5.getColumnModel().getColumn(2).setResizable(false);
        jTable5.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable5.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable5.columnModel.title2")); // NOI18N
        jTable5.getColumnModel().getColumn(3).setResizable(false);
        jTable5.getColumnModel().getColumn(3).setPreferredWidth(30);
        jTable5.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable5.columnModel.title3")); // NOI18N
        jTable5.getColumnModel().getColumn(4).setResizable(false);
        jTable5.getColumnModel().getColumn(4).setPreferredWidth(30);
        jTable5.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable5.columnModel.title4")); // NOI18N

        buttonTextDown12.setBackground(resourceMap.getColor("buttonTextDown12.background")); // NOI18N
        buttonTextDown12.setText(resourceMap.getString("buttonTextDown12.text")); // NOI18N
        buttonTextDown12.setFont(resourceMap.getFont("buttonTextDown12.font")); // NOI18N
        buttonTextDown12.setName("buttonTextDown12"); // NOI18N
        buttonTextDown12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTextDown12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonTextDown12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel15.TabConstraints.tabTitle"), jPanel15); // NOI18N

        jPanel16.setBackground(resourceMap.getColor("jPanel16.background")); // NOI18N
        jPanel16.setName("jPanel16"); // NOI18N

        jComboBox5.setBackground(resourceMap.getColor("jComboBox5.background")); // NOI18N
        jComboBox5.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox5.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox5.setEnabled(false);
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        buttonTextDown7.setBackground(resourceMap.getColor("buttonTextDown7.background")); // NOI18N
        buttonTextDown7.setText(resourceMap.getString("buttonTextDown7.text")); // NOI18N
        buttonTextDown7.setEnabled(false);
        buttonTextDown7.setFont(resourceMap.getFont("buttonTextDown9.font")); // NOI18N
        buttonTextDown7.setName("buttonTextDown7"); // NOI18N
        buttonTextDown7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown7ActionPerformed(evt);
            }
        });

        jTextField18.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setEnabled(false);
        jTextField18.setName("jTextField18"); // NOI18N
        jTextField18.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField18KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField18KeyReleased(evt);
            }
        });

        jTextField6.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jComboBox2.setBackground(resourceMap.getColor("jComboBox2.background")); // NOI18N
        jComboBox2.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setEnabled(false);
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(110, 20));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jCheckBox5.setBackground(resourceMap.getColor("jCheckBox5.background")); // NOI18N
        jCheckBox5.setFont(resourceMap.getFont("jCheckBox3.font")); // NOI18N
        jCheckBox5.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox5.setText(resourceMap.getString("jCheckBox5.text")); // NOI18N
        jCheckBox5.setToolTipText(resourceMap.getString("jCheckBox5.toolTipText")); // NOI18N
        jCheckBox5.setEnabled(false);
        jCheckBox5.setName("jCheckBox5"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jCheckBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox5KeyPressed(evt);
            }
        });

        jTextField39.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField39.setEnabled(false);
        jTextField39.setName("jTextField39"); // NOI18N
        jTextField39.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField39KeyPressed(evt);
            }
        });

        jTextField38.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField38.setEnabled(false);
        jTextField38.setName("jTextField38"); // NOI18N
        jTextField38.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField38.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField38KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField38KeyReleased(evt);
            }
        });

        jCheckBox3.setBackground(resourceMap.getColor("jCheckBox3.background")); // NOI18N
        jCheckBox3.setFont(resourceMap.getFont("jCheckBox3.font")); // NOI18N
        jCheckBox3.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox3.setText(resourceMap.getString("jCheckBox3.text")); // NOI18N
        jCheckBox3.setEnabled(false);
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.setOpaque(false);
        jCheckBox3.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jCheckBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox3KeyPressed(evt);
            }
        });

        buttonTextDown11.setBackground(resourceMap.getColor("buttonTextDown11.background")); // NOI18N
        buttonTextDown11.setText(resourceMap.getString("buttonTextDown11.text")); // NOI18N
        buttonTextDown11.setFont(resourceMap.getFont("buttonTextDown11.font")); // NOI18N
        buttonTextDown11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTextDown11ActionPerformed(evt);
            }
        });

        jComboBox7.setBackground(resourceMap.getColor("jComboBox7.background")); // NOI18N
        jComboBox7.setFont(resourceMap.getFont("jComboBox2.font")); // NOI18N
        jComboBox7.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox7.setEnabled(false);
        jComboBox7.setName("jComboBox7"); // NOI18N
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox7KeyPressed(evt);
            }
        });

        jTextField19.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setEnabled(false);
        jTextField19.setName("jTextField19"); // NOI18N
        jTextField19.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField19KeyReleased(evt);
            }
        });

        jTextField12.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField12.setToolTipText(resourceMap.getString("jTextField12.toolTipText")); // NOI18N
        jTextField12.setEnabled(false);
        jTextField12.setName("jTextField12"); // NOI18N
        jTextField12.setPreferredSize(new java.awt.Dimension(110, 20));
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
        });
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jCheckBox2.setBackground(resourceMap.getColor("jCheckBox2.background")); // NOI18N
        jCheckBox2.setFont(resourceMap.getFont("jCheckBox3.font")); // NOI18N
        jCheckBox2.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(resourceMap.getString("jCheckBox2.toolTipText")); // NOI18N
        jCheckBox2.setEnabled(false);
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.setOpaque(false);
        jCheckBox2.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jTextField10.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setEnabled(false);
        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jTextField13.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setToolTipText(resourceMap.getString("jTextField13.toolTipText")); // NOI18N
        jTextField13.setEnabled(false);
        jTextField13.setName("jTextField13"); // NOI18N
        jTextField13.setPreferredSize(new java.awt.Dimension(110, 20));
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        jCheckBox1.setBackground(resourceMap.getColor("jCheckBox1.background")); // NOI18N
        jCheckBox1.setFont(resourceMap.getFont("jCheckBox3.font")); // NOI18N
        jCheckBox1.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(resourceMap.getString("jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.setEnabled(false);
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setOpaque(false);
        jCheckBox1.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextField17.setBackground(resourceMap.getColor("jTextField18.background")); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setName("jTextField17"); // NOI18N
        jTextField17.setPreferredSize(new java.awt.Dimension(45, 20));
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField17KeyReleased(evt);
            }
        });

        jTextField11.setEditable(false);
        jTextField11.setFont(resourceMap.getFont("jTextField11.font")); // NOI18N
        jTextField11.setForeground(resourceMap.getColor("jTextField11.foreground")); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.setPreferredSize(new java.awt.Dimension(80, 25));

        jComboBox1.setBackground(resourceMap.getColor("jComboBox1.background")); // NOI18N
        jComboBox1.setForeground(resourceMap.getColor("jComboBox1.foreground")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------  Seleccione Tipo de Pago -----------" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jCheckBox4.setBackground(resourceMap.getColor("jCheckBox4.background")); // NOI18N
        jCheckBox4.setFont(resourceMap.getFont("jCheckBox3.font")); // NOI18N
        jCheckBox4.setForeground(resourceMap.getColor("jCheckBox3.foreground")); // NOI18N
        jCheckBox4.setText(resourceMap.getString("jCheckBox4.text")); // NOI18N
        jCheckBox4.setToolTipText(resourceMap.getString("jCheckBox4.toolTipText")); // NOI18N
        jCheckBox4.setEnabled(false);
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.setOpaque(false);
        jCheckBox4.setPreferredSize(new java.awt.Dimension(100, 21));
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jLabel32.setForeground(resourceMap.getColor("jLabel32.foreground")); // NOI18N
        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jLabel55.setFont(resourceMap.getFont("jLabel55.font")); // NOI18N
        jLabel55.setForeground(resourceMap.getColor("jLabel55.foreground")); // NOI18N
        jLabel55.setText(resourceMap.getString("jLabel55.text")); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                                .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                    .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jTextField13, 0, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox7, 0, 190, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 190, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, 190, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(buttonTextDown7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonTextDown11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonTextDown11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonTextDown7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel16.TabConstraints.tabTitle"), jPanel16); // NOI18N

        jLabel53.setFont(resourceMap.getFont("jLabel53.font")); // NOI18N
        jLabel53.setForeground(resourceMap.getColor("jLabel53.foreground")); // NOI18N
        jLabel53.setText(resourceMap.getString("jLabel53.text")); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N

        jLabel54.setIcon(resourceMap.getIcon("jLabel54.icon")); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblInternos, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30)
                                .addComponent(lblInternos))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel53)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneHeader1.addTab(resourceMap.getString("jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(resourceMap.getString("jPanel11.TabConstraints.tabTitle"), jPanel11); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Listar_Cuadres();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = this.jTable1.getSelectedRow();

        try {
            if (fila >= 0 && jTable1.getSelectedColumn() == 5) {

                int cerrado = lista_Ventas.get(fila).getCierre();

                if (cerrado == 1) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE CUADRE DE CAJA YA SE ENCUENTRA CERRADO ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    pven = new Venta(Fecha, Fecha, idbotica, lista_Ventas.get(fila).getId_Caja(), lista_Ventas.get(fila).getId_Personal_Botica_Caja(), lista_Ventas.get(fila).getIdturno());
                    LimpiatTabla_2();
                    LimpiatTabla_3();
                    LimpiatTabla_4();
                    REPORTE_CAJA_TIPOS_PAGOS();
                    MuestraDetalle_Resumen();
                    Mustra_Internos();
                    tabbedPaneHeader1.setEnabledAt(1, false);
                    tabbedPaneHeader1.setSelectedIndex(0);
                    jLabel4.setText(lista_Ventas.get(fila).getCajero());
                    jLabel15.setText(lista_Ventas.get(fila).getMiTurno());
                    jLabel16.setText(lista_Ventas.get(fila).getCaja());
                    lblInternos.setText(lista_Ventas.get(fila).getInternoInicio() + "   -   " + lista_Ventas.get(fila).getInternoFinal());
                    Deshabilita_Principal(false);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String interno = this.jTextField1.getText().trim() + '%';
        if (interno.compareTo("") != 0) {
            Busca_Interno(interno);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyReleased
        VisualizaVenta(this.jTable4.getSelectedRow());
}//GEN-LAST:event_jTable4KeyReleased

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        VisualizaVenta(this.jTable4.getSelectedRow());

}//GEN-LAST:event_jTable4MouseClicked

    private void buttonTextDown1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown1ActionPerformed
        tabbedPaneHeader1.setEnabledAt(0, false);
        tabbedPaneHeader1.setEnabledAt(1, true);
        tabbedPaneHeader1.setSelectedIndex(1);
    }//GEN-LAST:event_buttonTextDown1ActionPerformed

    private void buttonTextDown7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown7ActionPerformed

        if (Modifica_Pago_Interno()) {
            double monto = Double.parseDouble(this.jTextField11.getText().trim());

            BigDecimal bd1 = new BigDecimal(monto);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            monto = bd1.doubleValue();
            BigDecimal bd2 = new BigDecimal(total);
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            total = bd2.doubleValue();

            if (monto == total) {
                mensaje = lblInterno.getText().trim() + " \n DE : " + lbltippago.getText().trim() + "\n  A : " + jComboBox1.getSelectedItem();
                int pe = JOptionPane.showConfirmDialog(null, " Se Procedera a Modificar el Tipo de Pago \n  Interno " + mensaje + " ", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (pe == JOptionPane.YES_OPTION) {
                    objVenta = new Venta(idbotica, lista_Ventas.get(this.jTable1.getSelectedRow()).getId_Caja(), lblInterno.getText().trim(), idtipopago);
                    int resultado = logventa.Modificar_Tipo_Pagos(objVenta, lista_pagos);

                    if (resultado == 2) {
                        opcion = 3;
                        showThread = new Thread(this);
                        showThread.start();
                    } else {
                        if (resultado == 0) {
                            mensaje = "BOTICA FELICIDAD  : " + idbotica + " \n  ESTA INTENTANDO MODIFCAR EL INTERNO : " + lblInterno.getText().trim() + " QUE YA SE ENCUENTRA COMO PAGO DIVERSO";
                            String correo = objlistabotica.ReornaCorreo(idbotica);
                            objmail.sendMail(correo, "REVISAR BOTICA - CAMBIO DE PAGO  ", "REVISAR : \n " + mensaje + "" + "\n FECHA : " + logfecha.RetornaFecha() + "\n HORA: " + logfecha.RetornaHora() + " \n BOTICA FELICIDAD  " + idbotica);
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS \n LO SENTIMOS NO SE PUEDE MODIFICAR UNA VENTA CON PAGO DIVERSO ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            mensaje = "BOTICA FELICIDAD  : " + idbotica + " \n  ERROR EN DISCREPANCIA DE DATOS CON EL INTERNO : " + lblInterno.getText().trim() + " \n NO COINCIDEN MONTO DE VENTA CON MONTO DE TIPO DE PAGO.";
                            String correo = objlistabotica.ReornaCorreo(idbotica);
                            objmail.sendMail(correo, "ERROR AL INTENTAR CAMBIAR TIPO DE PAGO ", "REVISAR : \n " + mensaje + "" + "\n FECHA : " + logfecha.RetornaFecha() + "\n HORA: " + logfecha.RetornaHora() + " \n BOTICA FELICIDAD  " + idbotica);
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS \n LO SENTIMOS HUBO UN ERROR AL CAMBIAR SU TIPO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n EL PAGO INGRESADO ES MENOR QUE EL MONTO DE VENTA ", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_buttonTextDown7ActionPerformed

    private void buttonTextDown8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown8ActionPerformed
        CompletaTiposPagos();
        buttonTextDown8.setEnabled(false);
        lbltippago.setForeground(Color.YELLOW);
        jLabel20.setForeground(Color.YELLOW);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(2);
        jTabbedPane1.setEnabledAt(2, true);
        total = Double.parseDouble(lblTotal.getText().trim());

    }//GEN-LAST:event_buttonTextDown8ActionPerformed

    private void buttonTextDown9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown9ActionPerformed
        tabbedPaneHeader1.setEnabledAt(1, false);
        tabbedPaneHeader1.setEnabledAt(0, true);
        tabbedPaneHeader1.setSelectedIndex(0);
        lbltippago.setForeground(Color.WHITE);
        jLabel20.setForeground(Color.WHITE);
        buttonTextDown7.setEnabled(false);
        buttonTextDown8.setEnabled(true);
    }//GEN-LAST:event_buttonTextDown9ActionPerformed

    private void buttonTextDown5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown5ActionPerformed
        if (!modificado) {
            jTabbedPane2.setEnabledAt(0, true);
            jTabbedPane2.setEnabledAt(1, false);
            jTabbedPane2.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR CONFIRMAR SU CIERRE - REVISION CUADRE DE CAJA ", "Confrmar Cierre", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonTextDown5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox1.isSelected()) {

                jTextField17.setText("");
                jTextField17.setEnabled(true);

                if (this.jCheckBox4.isSelected()) ///NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                    this.jComboBox5.setEnabled(true);
                }

                if (this.jCheckBox3.isSelected())//TARJETA
                {
                    cantpagos++;
                    k = 3;
                    this.jTextField18.setEnabled(true);
                    this.jTextField6.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }

                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }


                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox1.setSelected(false);
                } else {

                    if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 2) {
                        if (this.jTextField10.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField10.getText().trim());
                        }
                    } else if (k == 3) {
                        if (this.jTextField18.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField18.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }


                    if (k > 0) {
                        Double restante = total - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        this.jTextField17.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                    } else {
                        if (k == 0) {
                            Double restante = total;
                            BigDecimal bd2 = new BigDecimal(restante);
                            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                            this.jTextField17.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                        }

                    }

                    this.jTextField17.setEnabled(true);
                    this.jTextField17.requestFocus();

                }

            } else {
                jTextField17.setEnabled(false);
                jTextField17.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }

}//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField17KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyReleased
        try {

            if (jTextField17.getText().trim().compareTo("") != 0) {
                String val = this.jTextField17.getText().trim();
                double valor = Double.parseDouble(val);
                BigDecimal bd1 = new BigDecimal(valor);
                bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                valor = bd1.doubleValue();

                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField17.setText("");
                    this.jTextField11.setText("");
                    this.jTextField11.setText("");
                    this.jTextField17.requestFocus();
                } else {
                    CalculaMontoIngresado();
                }


            }

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jTextField17KeyReleased

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox4.isSelected()) {

                this.jTextField19.setEnabled(false);
                this.jTextField12.setEnabled(true);


                if (this.jCheckBox1.isSelected())//CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);

                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                    this.jComboBox5.setEnabled(true);
                }
                if (this.jCheckBox3.isSelected())//TARJETA
                {
                    cantpagos++;
                    k = 3;
                    this.jTextField6.setEnabled(true);
                    this.jTextField18.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }

                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }

                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox4.setSelected(false);
                } else {

                    this.jTextField12.setEnabled(true);
                    this.jTextField12.setEditable(true);
                    this.jTextField12.requestFocus();
                }
            } else {
                this.jTextField19.setEnabled(false);
                this.jTextField12.setEnabled(false);
                this.jTextField19.setText("");
                this.jTextField12.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
}//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
}//GEN-LAST:event_jTextField19KeyPressed

    private void jTextField19KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyReleased
}//GEN-LAST:event_jTextField19KeyReleased

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed

        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox2.isSelected()) {

                jTextField10.setEnabled(true);
                jTextField13.setEnabled(true);
                jComboBox5.setEnabled(true);
                RecuperaBancos();

                if (this.jCheckBox1.isSelected()) //CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);
                }
                if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField19.setEnabled(true);
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox3.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 3;
                    this.jTextField18.setEnabled(true);
                    this.jTextField6.setEnabled(true);
                    this.jComboBox2.setEnabled(true);
                }
                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }


                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox2.setSelected(false);
                } else {

                    if (k == 1) {
                        if (this.jTextField17.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField17.getText().trim());
                        }
                    } else if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 3) {
                        if (this.jTextField18.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField18.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }

                    if (k > 0) {
                        Double restante = total - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        this.jTextField10.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                    }

                    this.jTextField10.requestFocus();
                }

            } else {
                this.jTextField10.setEnabled(false);
                this.jTextField13.setEnabled(false);
                this.jTextField10.setText("");
                this.jTextField13.setText("");
                this.jComboBox5.setEnabled(false);
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
}//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
}//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        try {


            if (this.jTextField10.getText().trim().compareTo("") != 0) {
                String val = this.jTextField10.getText().trim();
                double valor = Double.parseDouble(val);


                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField10.setText("");
                    this.jTextField11.setText("");
                    this.jTextField17.requestFocus();

                } else {
                    double sum = total - valor;
                    BigDecimal bd1 = new BigDecimal(sum);
                    bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                    sum = bd1.doubleValue();

                    if (this.jCheckBox1.isSelected()) {
                        this.jTextField17.setText(String.valueOf(sum));
                    } else if (this.jCheckBox3.isSelected()) {
                        this.jTextField18.setText(String.valueOf(sum));
                    }

                    CalculaMontoIngresado();

                }

            }

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            MuestraNota_Credito();
        }
}//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
}//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
}//GEN-LAST:event_jTextField12KeyPressed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed

        try {

            int k = 0;
            Double mon = 0.0;

            if (this.jCheckBox3.isSelected()) {

                jTextField18.setEnabled(true);
                jTextField6.setEnabled(true);
                jComboBox2.setEnabled(true);
                RecuperaTarjetas();

                if (this.jCheckBox1.isSelected())//CASH
                {
                    cantpagos++;
                    k = 1;
                    this.jTextField17.setEnabled(true);
                }
                if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
                {
                    cantpagos++;
                    k = 4;
                    this.jTextField12.setEnabled(true);
                }
                if (this.jCheckBox2.isSelected())//CHEQUE
                {
                    cantpagos++;
                    k = 2;
                    this.jTextField10.setEnabled(true);
                    this.jTextField13.setEnabled(true);
                }
                if (this.jCheckBox5.isSelected())//ABONO
                {
                    cantpagos++;
                    k = 5;
                    this.jTextField38.setEnabled(true);
                    this.jTextField39.setEnabled(true);
                    this.jComboBox7.setEnabled(true);
                }

                if (cantpagos > 2) {
                    JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                    cantpagos = 2;
                    this.jCheckBox3.setSelected(false);
                } else {

                    if (k == 1) {
                        if (this.jTextField17.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField17.getText().trim());
                        }
                    } else if (k == 4) {
                        if (this.jTextField19.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField19.getText().trim());
                        }
                    } else if (k == 2) {
                        if (this.jTextField10.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField10.getText().trim());
                        }
                    } else if (k == 5) {
                        if (this.jTextField39.getText().trim().compareTo("") != 0) {
                            mon = Double.parseDouble(this.jTextField39.getText().trim());
                        }
                    }

                    if (k > 0) {
                        Double restante = total - mon;
                        BigDecimal bd2 = new BigDecimal(restante);
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        this.jTextField18.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                    } else {
                        this.jTextField18.setText(String.valueOf(total));
                    }

                }

            } else {
                this.jTextField6.setEnabled(false);
                this.jTextField18.setEnabled(false);
                this.jComboBox2.setEnabled(false);
                this.jTextField18.setText("");
                this.jTextField6.setText("");
                cantpagos--;
            }

            CalculaMontoIngresado();

        } catch (Exception ex) {
        }
}//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox3KeyPressed
}//GEN-LAST:event_jCheckBox3KeyPressed

    private void jTextField18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyPressed
}//GEN-LAST:event_jTextField18KeyPressed

    private void jTextField18KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyReleased
        try {
            if (this.jTextField18.getText().trim().compareTo("") != 0) {

                String val = this.jTextField18.getText().trim();
                double valor = Double.parseDouble(val);

                if (valor > total) {
                    JOptionPane.showMessageDialog(this, " LA CANTIDAD NO PUEDE EXCEDER AL MONTO DE PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.jTextField18.setText("");
                    this.jTextField11.setText("");
                    this.jTextField17.requestFocus();

                } else {
                    double sum = total - valor;
                    BigDecimal bd1 = new BigDecimal(sum);
                    bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                    sum = bd1.doubleValue();

                    if (this.jCheckBox1.isSelected()) {
                        this.jTextField17.setText(String.valueOf(sum));
                    } else if (this.jCheckBox2.isSelected()) {
                        this.jTextField10.setText(String.valueOf(sum));
                    }

                    CalculaMontoIngresado();
                }

            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jTextField18KeyReleased

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
}//GEN-LAST:event_jTextField13KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
}//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
}//GEN-LAST:event_jComboBox5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
}//GEN-LAST:event_jTextField6KeyPressed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed

        int k = 0;
        Double mon = 0.0;

        if (this.jCheckBox5.isSelected()) {

            jTextField39.setEnabled(true);
            jTextField38.setEnabled(true);
            jComboBox7.setEnabled(true);
            jTextField39.requestFocus();

            if (this.jCheckBox1.isSelected()) //CASH
            {
                cantpagos++;
                k = 1;
                this.jTextField17.setEnabled(true);
            }
            if (this.jCheckBox4.isSelected())//NOTA DE CREDITO
            {
                cantpagos++;
                k = 4;
                this.jTextField19.setEnabled(true);
                this.jTextField12.setEnabled(true);
            }
            if (this.jCheckBox3.isSelected())//ES TARJETA
            {
                cantpagos++;
                k = 3;
                this.jTextField18.setEnabled(true);
                this.jTextField6.setEnabled(true);
                this.jComboBox2.setEnabled(true);
            }

            if (this.jCheckBox2.isSelected())//ES CHEQUE
            {
                cantpagos++;
                k = 2;
                this.jTextField10.setEnabled(true);
                this.jTextField13.setEnabled(true);
                this.jComboBox5.setEnabled(true);
            }


            if (cantpagos > 2) {
                JOptionPane.showMessageDialog(this, " LO SENTIMOS \n SOLO SE ACEPTAN DOS TIPOS DE PAGOS ", "Error", JOptionPane.ERROR_MESSAGE);
                cantpagos = 2;
            } else {

                if (k == 1) {
                    if (this.jTextField17.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField17.getText().trim());
                    }
                } else if (k == 4) {
                    if (this.jTextField19.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField19.getText().trim());
                    }
                } else if (k == 3) {
                    if (this.jTextField18.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField18.getText().trim());
                    }
                } else if (k == 5) {
                    if (this.jTextField39.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField39.getText().trim());
                    }
                } else if (k == 2) {
                    if (this.jTextField10.getText().trim().compareTo("") != 0) {
                        mon = Double.parseDouble(this.jTextField10.getText().trim());
                    }
                }


                if (k > 0) {
                    Double restante = total - mon;
                    BigDecimal bd2 = new BigDecimal(restante);
                    this.jTextField39.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                } else {
                    this.jTextField39.setText(String.valueOf(total));
                }

                this.jTextField39.requestFocus();
            }

        } else {
            this.jTextField38.setEnabled(false);
            this.jTextField39.setEnabled(false);
            this.jTextField38.setText("");
            this.jTextField39.setText("");
            this.jComboBox7.setEnabled(false);
            cantpagos--;
        }

        CalculaMontoIngresado();

}//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox5KeyPressed
}//GEN-LAST:event_jCheckBox5KeyPressed

    private void jTextField39KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField39KeyPressed
}//GEN-LAST:event_jTextField39KeyPressed

    private void jComboBox7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyPressed
}//GEN-LAST:event_jComboBox7KeyPressed

    private void jTextField38KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField38KeyPressed
}//GEN-LAST:event_jTextField38KeyPressed

    private void jTextField38KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField38KeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField38KeyReleased

    private void buttonTextDown11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown11ActionPerformed
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        buttonTextDown8.setEnabled(true);
        Habilita_Cash(false);
        jCheckBox1.setSelected(false);
        jTextField17.setText("");
        jTextField11.setText("");
        this.jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_buttonTextDown11ActionPerformed

    private void buttonTextDown10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown10ActionPerformed
        objVenta = new Venta(idbotica, lblInterno.getText().trim());
        listaVenta.removeAll(listaVenta);
        LimpiatTabla_5();
        listaVenta = logventa.ListaVenta_Detalle(objVenta);

        for (int i = 0; i < listaVenta.size(); i++) {
            item_Detalle[0] = i + 1;
            item_Detalle[1] = listaVenta.get(i).getDescr_Producto();
            item_Detalle[2] = listaVenta.get(i).getUnidad();
            item_Detalle[3] = listaVenta.get(i).getFraccion();
            item_Detalle[4] = listaVenta.get(i).getTotal_producto();
            tablaDetalle.addRow(item_Detalle);
        }

        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_buttonTextDown10ActionPerformed

    private void buttonTextDown12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown12ActionPerformed
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        buttonTextDown8.setEnabled(true);
    }//GEN-LAST:event_buttonTextDown12ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        try {

            if (listatipospagos.size() > 0) {
                Verifica_Pago();
                buttonTextDown7.setEnabled(true);
            } else {
                buttonTextDown7.setEnabled(false);
            }


        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void buttonTextDown3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown3ActionPerformed
        opcion = 1;
        showThread = new Thread(this);
        showThread.start();
        OcultaLabel(true);
    }//GEN-LAST:event_buttonTextDown3ActionPerformed

    private void buttonTextDown2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown2ActionPerformed
        opcion = 2;
        showThread = new Thread(this);
        showThread.start();
        OcultaLabel(true);
    }//GEN-LAST:event_buttonTextDown2ActionPerformed

    private void buttonTextDown6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown6ActionPerformed

        if (!modificado) {
            int pe = JOptionPane.showConfirmDialog(null, " Desea Salir  ", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (pe == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR CONFIRMAR SU CIERRE - REVISION CUADRE DE CAJA ", "Confrmar Cierre", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonTextDown6ActionPerformed

    private void buttonTextDown4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTextDown4ActionPerformed
        Cierre_Revision();
    }//GEN-LAST:event_buttonTextDown4ActionPerformed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown1;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown10;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown11;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown12;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown2;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown3;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown4;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown5;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown6;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown7;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown8;
    private org.edisoncor.gui.button.ButtonTextDown buttonTextDown9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader jTabbedPane1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneRound jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel lblInterno;
    private javax.swing.JLabel lblInternos;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblcliente;
    private javax.swing.JLabel lblhora;
    private javax.swing.JLabel lblnumero;
    private javax.swing.JLabel lblserie;
    private javax.swing.JLabel lbltippago;
    private javax.swing.JLabel lbltipventa;
    private javax.swing.JLabel lblvendedor;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbedPaneHeader1;
    // End of variables declaration//GEN-END:variables
}
