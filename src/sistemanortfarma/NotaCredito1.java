package sistemanortfarma;

import CapaDatos.ClientesData;
import CapaDatos.ConexionPool;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import entidad.Cajas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import CapaLogica.LogicaCajas;
import java.io.PrintWriter;
import CapaLogica.LogicaProducto;
import java.io.BufferedWriter;
import CapaLogica.XML_GENERAR;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sistemanortfarma.OpcionesMenu;
import java.sql.*;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaClientes;

import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.text.NumberFormat;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemanortfarma.VerificaSistema;
import entidad.Clientes;
import service.IOException_Exception;


/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class NotaCredito1 extends javax.swing.JInternalFrame {

    Connection conex;
    private ConexionPool db;
    private double MIIGV;
    XML_GENERAR objXML_GENERAR;
    private DefaultTableModel model_notas;
    LogicaProducto objProducto = new LogicaProducto();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    Mant_Productos mantProduc = new Mant_Productos();
    LogicaCajas objcajas = new LogicaCajas();
    TableColumnModel colModel;
    private DefaultTableModel model;
    TableColumnModel colModel1;
    LogicaBoticas objlistabotica = new LogicaBoticas();
    Venta obj;
    ResultSet rs,rs3,rs4,rs5;
    List<Venta> listaInternos = new ArrayList<Venta>();
    List<Venta> lista_VentaFinal = new ArrayList<Venta>();
    List<Cajas> listaObservaciones = new ArrayList<Cajas>();
    LogicaVenta logventa = new LogicaVenta();
    private String idventa = "";
    int podecimal = 4;
    int podecimalPantalla = 2;
    OpcionesMenu obj1;
    private String Impresora_Factura = obj1.getImpresora_Factura();
    private String idbotica = null;
    Object[] datosDetalle = new Object[17];
    MuestraVentana objetoventana = new MuestraVentana();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Productos_De_Venta objPrdocuto = null;
    double total = 0.0, subtottal = 0.0,subtottalCabecera = 0.0,subtottalDetalle = 0.0,subtottalPantalla = 0.0;
    double OpGravada = 0.0, OpExonerada=0.0, OpInafecta=0.0,OpGratuita=0.0,OpISC=0.0,miigv=0.0,OpBonificacion=0.0,OpDescuentoTotal = 0.0, OpDescuento = 0.0, preciounitario=0.0,
           OpGravadaDetalle = 0.0,OpGravadaCabecera=0.0,OpInafectaCabecera=0.0 ;
    double IGV;
    List<Venta> ltaVen_Productos = new ArrayList<Venta>();
    List<Venta> listaVenta_Detalle = new ArrayList<Venta>();
    private String serie = null;
    CallableStatement procedure = null, procedure1 = null, procedure2 = null;
    private String numero = null;
    Date fecha;
    int id_peronal;
    AplicacionVentas objventas;
    private static String id_botica;
    Clientes nuevocliente;
    Map parameters = new HashMap();
    VerificaSistema obj2;
    URL report_file;
    JasperReport masterReport = null;
    JasperPrint jasperPrint;
    AplicacionVentas objventa;
    LogicaClientes logcliente = new LogicaClientes();

    @SuppressWarnings("static-access")
    public NotaCredito1(AplicacionVentas obj) {
        id_botica = objventa.getIdbotica();
        setId_botica(id_botica);
        initComponents();
        objventas = obj;
        model_notas = (DefaultTableModel) this.tablanotas.getModel();
        colModel = this.tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        colModel1 = this.jTable1.getColumnModel();
        idbotica = objventas.getIdbotica();
        fecha = objventas.getFecha();
        id_peronal = objventas.getIdpesonal();
        setTitle("Generar Nota de Credito                                           Usuario:   " + objventas.getUsuario());
        AparienciaTabla();
        AparienciaTabla1();
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        this.txtdni.setDocument(new LimitadorLetras(txtdni, 8));
        this.txtruc.setDocument(new LimitadorLetras(txtruc, 11));
        //this.btnbuscar.set
        this.jTextField1.requestFocus();
        jTabbedPane1.setEnabledAt(1, false);
        this.jPanel6.setVisible(false);
        this.jRadioButton1.setSelected(true);
        this.jTextField1.setSize(20, 50);
        this.txttpago.setVisible(false);
        this.txtTImpre.setVisible(false);
        this.txtred.setVisible(false);
        CargarNotasCreditos();
        this.txtmoney.setVisible(false);
        cargarObs();
        this.jScrollPane3.setVisible(false);

        Map parameters = new HashMap();
        VerificaSistema obj2;
        URL report_file;
        JasperReport masterReport = null;
        JasperPrint jasperPrint;
    }

    public NotaCredito1() {
        db = new ConexionPool();
    }
    private void AparienciaTabla() {
        TableColumn col = colModel.getColumn(0);
        JTableHeader cabecera = new JTableHeader(this.tablanotas.getColumnModel());
        cabecera.setReorderingAllowed(false);
        this.tablanotas.setTableHeader(cabecera);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.tablanotas.getColumnModel().getColumn(5).setCellRenderer(tcenter);
        this.tablanotas.getColumnModel().getColumn(6).setCellRenderer(tcenter);
        this.tablanotas.getColumnModel().getColumn(7).setCellRenderer(tcenter);
        this.tablanotas.getColumnModel().getColumn(8).setCellRenderer(tcenter);
    }

    private void AparienciaTabla1() {
        TableColumn col = colModel1.getColumn(0);
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
        this.jTable1.setTableHeader(cabecera);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(5).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(6).setCellRenderer(tcenter);
        this.jTable1.getColumnModel().getColumn(7).setCellRenderer(tcenter);

        DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
        tcenter1.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTable1.getColumnModel().getColumn(2).setCellRenderer(tcenter1);
        this.jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter1);

    }
    private void cargarObs() {

        listaObservaciones = objcajas.ListarObs();
        this.jcomboObs.addItem("-- Seleccione Observacion --");

        for (int i = 0; i < listaObservaciones.size(); i++) {
            this.jcomboObs.addItem(listaObservaciones.get(i).getDescripcionObs());
        }       

    }
    
    public static String getId_botica() {
        return id_botica;
    }

    public static void setId_botica(String id_botica) {
        NotaCredito1.id_botica = id_botica;
    }

    public String getIdbotica() {
        return idbotica;
    }

    public void setIdbotica(String idbotica) {
        this.idbotica = idbotica;
    }

    private void CargarNotasCreditos() {
        idventa = this.jTextField1.getText().trim() + '%';
        obj = new Venta(idbotica, idventa);
        listaInternos = logventa.Lista_Internos_Ventas(obj, 0);


        if (listaInternos.size() > 0) {
            LimpiatTabla();

            for (int i = 0; i < listaInternos.size(); i++) {
                datosDetalle[0] = listaInternos.get(i).getId_Venta();
                datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                //datosDetalle[2] = listaInternos.get(i).getdescaja();
                datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                datosDetalle[3] = listaInternos.get(i).getTipventa();
                datosDetalle[4] = listaInternos.get(i).getTipopago();
                datosDetalle[5] = listaInternos.get(i).getSubTotal();
                datosDetalle[6] = listaInternos.get(i).getTotal();
                datosDetalle[7] = listaInternos.get(i).getSerie();
                datosDetalle[8] = listaInternos.get(i).getNumero();
                model_notas.addRow(datosDetalle);
            }
        }


    }

    private void BuscarNotaCredito(String filtro) {
        filtro += '%';

        try {

            obj = new Venta(idbotica, filtro);
            listaInternos = logventa.Lista_Internos_Ventas(obj, 0);

            if (listaInternos.size() > 0) {
                LimpiatTabla();
            }

            for (int i = 0; i < listaInternos.size(); i++) {
                datosDetalle[0] = listaInternos.get(i).getId_Venta();
                datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                datosDetalle[3] = listaInternos.get(i).getTipventa();
                datosDetalle[4] = listaInternos.get(i).getTipopago();
                datosDetalle[5] = listaInternos.get(i).getSubTotal();
                datosDetalle[6] = listaInternos.get(i).getTotal();
                datosDetalle[7] = listaInternos.get(i).getSerie();
                datosDetalle[8] = listaInternos.get(i).getNumero();

                model_notas.addRow(datosDetalle);
            }

            tablanotas.getSelectionModel().setSelectionInterval(0, 0);



        } catch (Exception ex) {
            System.out.println("ERROR CAPA VISTA METODO BuscarNotaCredito");
        }


    }

    private void SeleccionarVenta() {
        int fila = this.tablanotas.getSelectedRow();

        if (fila >= 0 && tablanotas.getRowCount() > 0) {

            double notomat = listaInternos.get(fila).getNotomar();

            if (notomat > 0) {
                JOptionPane.showMessageDialog(this, "LO SENTIMOS \n NO PUEDES HACER UNA NOTA DE CREDITO \n DE ESTA VENTA PAGADA  ", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
            } else {
                String tvta = listaInternos.get(fila).getTipventa();
                //String OK = logventa.Recupera_FacturaOK(idbotica,String.valueOf(this.tablanotas.getValueAt(fila, 0)),
                //        listaInternos.get(fila).getFecha_Venta().toString());
                //String OK ="1";

                String BOTNCXCAJA = logventa.Recupera_BotNCXCAJA(idbotica);

                String caja = logventa.Recupera_Caja(idbotica,String.valueOf(this.tablanotas.getValueAt(fila, 0)),
                        listaInternos.get(fila).getFecha_Venta().toString(),String.valueOf(id_peronal));

                
                //if ((tvta.equals("FACTURA ELECTRONICA") || tvta.equals("FACTURA")) && !OK.equals("1")){
               if (idbotica.equals(BOTNCXCAJA)){

                if (caja.equals("1") || caja.equals("3")){
                    

                idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));
                String fechavta = listaInternos.get(fila).getFecha_Venta().toString();
                String money = mantProduc.Recupera_Moneda(idventa,fechavta).toString();

                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setEnabledAt(1, true);
                jTabbedPane1.setSelectedIndex(1);
                this.txtcodigo.setText(String.valueOf(listaInternos.get(fila).getId_Cliente()));
                this.txtcliente.setText(listaInternos.get(fila).getNombre_RazonSocial());
                this.txtfecha.setText(String.valueOf(listaInternos.get(fila).getFecha_Venta()));
                this.txtserie.setText(listaInternos.get(fila).getSerie());
                this.txtnumero.setText(listaInternos.get(fila).getNumero());
                this.txttotal.setText(String.valueOf(listaInternos.get(fila).getTotal()));
                this.txtsubtotal.setText(String.valueOf(listaInternos.get(fila).getSubTotal()));
                this.txttipoventa.setText(listaInternos.get(fila).getTipventa());
                this.txtdireccion.setText(listaInternos.get(fila).getDireccion());
                this.txtruc.setText(listaInternos.get(fila).getRUC());
                this.txtvendedor.setText(listaInternos.get(fila).getNombre());
                this.TXTIGV.setText(String.valueOf(listaInternos.get(fila).getIGV()));
                this.txtdni.setText(listaInternos.get(fila).getDNI());
                this.txtmoney.setText(money);
                this.jTextField44.setText(String.valueOf(listaInternos.get(fila).getredondeo()));
                this.jTextField45.setText(String.valueOf(listaInternos.get(fila).gettotfinal()));
                this.txttpago.setText((String.valueOf(listaInternos.get(fila).gettpago())));
                this.txtred.setText((String.valueOf(listaInternos.get(fila).gettred())));
                this.txtTImpre.setText((String.valueOf(listaInternos.get(fila).gett_impre())));

                if (tvta.equals("FACTURA ELECTRONICA") || tvta.equals("FACTURA")){
                   this.jButton9.setEnabled(false);
                   //this.btnbuscar.setEnabled(false);
                }else{
                   this.jButton9.setEnabled(true);
                   //this.btnbuscar.setEnabled(true);
                }

               }else{
                   JOptionPane.showMessageDialog(this, "LO SENTIMOS \n LA NOTA DE CREDITO SOLO DEBE REALIZARSE EN CAJA 1 ", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
               }



             }else{
               if (!caja.equals("1")){
                   JOptionPane.showMessageDialog(this, "LO SENTIMOS \n LA NOTA DE CREDITO SOLO DEBE REALIZARSE EN CAJA 1 ", "Nota de Credito", JOptionPane.ERROR_MESSAGE);

                }else{

                idventa = String.valueOf(this.tablanotas.getValueAt(fila, 0));
                String fechavta = listaInternos.get(fila).getFecha_Venta().toString();
                String money = mantProduc.Recupera_Moneda(idventa,fechavta).toString();

                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setEnabledAt(1, true);
                jTabbedPane1.setSelectedIndex(1);
                this.txtcodigo.setText(String.valueOf(listaInternos.get(fila).getId_Cliente()));
                this.txtcliente.setText(listaInternos.get(fila).getNombre_RazonSocial());
                this.txtfecha.setText(String.valueOf(listaInternos.get(fila).getFecha_Venta()));
                this.txtserie.setText(listaInternos.get(fila).getSerie());
                this.txtnumero.setText(listaInternos.get(fila).getNumero());
                this.txttotal.setText(String.valueOf(listaInternos.get(fila).getTotal()));
                this.txtsubtotal.setText(String.valueOf(listaInternos.get(fila).getSubTotal()));
                this.txttipoventa.setText(listaInternos.get(fila).getTipventa());
                this.txtdireccion.setText(listaInternos.get(fila).getDireccion());
                this.txtruc.setText(listaInternos.get(fila).getRUC());
                this.txtvendedor.setText(listaInternos.get(fila).getNombre());
                this.TXTIGV.setText(String.valueOf(listaInternos.get(fila).getIGV()));
                this.txtdni.setText(listaInternos.get(fila).getDNI());
                this.txtmoney.setText(money);
                this.jTextField44.setText(String.valueOf(listaInternos.get(fila).getredondeo()));
                this.jTextField45.setText(String.valueOf(listaInternos.get(fila).gettotfinal()));
                this.txttpago.setText((String.valueOf(listaInternos.get(fila).gettpago())));
                this.txtred.setText((String.valueOf(listaInternos.get(fila).gettred())));
                this.txtTImpre.setText((String.valueOf(listaInternos.get(fila).gett_impre())));

                if (tvta.equals("FACTURA ELECTRONICA") || tvta.equals("FACTURA")){
                   this.jButton9.setEnabled(false);
                }else{
                   this.jButton9.setEnabled(true);
                }

               }

             }




            }

        }

    }

    private void CalculaMontos(double recmonto) {

        try {

            double parciali=0;
            total = 0;
            subtottal = 0;
            subtottalPantalla = 0;
            subtottalDetalle = 0;
            double auxparcial;
            double auxparcialDetalle;

            OpInafectaCabecera = 0.0;
            OpGravada = 0.0;
            OpDescuentoTotal = 0.0;
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            
            //String c_prod =  String.valueOf(jTable1.getValueAt(fila, 1));
            //double Monto_icbpre = objProducto.PrecioICBPER(c_prod);
            //double Monto_icbpre_Cab  = Monto_icbpre * c_cant;
            
            
            double Monto_icbpre=0.0;
            double Monto_icbpre_Cab=0.0;
            for (int i = 0; i < this.jTable1.getRowCount(); i++) {
                parciali = Double.parseDouble(this.jTable1.getValueAt(i, 7).toString());
                
                String c_prod =  String.valueOf(jTable1.getValueAt(i, 13));
                double c_cantU =  Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 2)));
                double c_cantF =  Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 3)));
                int ICBPER = objProducto.EsICBPER(c_prod);
                double empa=objProducto.PrecioICBPEREMP(c_prod);
                double c_cantT = c_cantU * empa + c_cantF;
                if (ICBPER == 1){
                    Monto_icbpre = objProducto.PrecioICBPER(c_prod);                    
                    Monto_icbpre_Cab  = Monto_icbpre * c_cantT;
                }
                
                
                total += parciali;
                IGV = ltaVen_Productos.get(i).getIGV();

                if (IGV != 0){                    
                    auxparcial = (parciali / (1 + (IGV / 100)));
                    subtottal += auxparcial;
                    subtottalPantalla += auxparcial;
                    OpGravada = subtottal;
                }else{

                    /*auxparcialDetalle = (parciali / (1 + (IGV / 100)));
                    subtottalDetalle += auxparcialDetalle;
                    OpGravadaCabecera = subtottalDetalle - parciali;
                    subtottalDetalle = OpGravadaCabecera;*/

                    auxparcial = (parciali / (1 + (IGV / 100)));
                    subtottal += auxparcial;
                    subtottalPantalla += auxparcial;
                    OpGravada = subtottal - parciali;
                    subtottal = OpGravada;
                    OpInafectaCabecera +=parciali;
                }
                
            }
            total = total + Monto_icbpre_Cab;
            
            BigDecimal bd = new BigDecimal(total);
            bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            total = bd.doubleValue();

            BigDecimal bd1 = new BigDecimal(subtottalPantalla);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            subtottal = bd1.doubleValue();

            if (!this.txtred.getText().equals("1")){

                this.jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                this.jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                
                
                
                double igv = total - subtottal -Monto_icbpre_Cab ;
                BigDecimal bd3 = new BigDecimal(igv);

                this.jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                jTextField5.setText("0.00"); //redondeo
                jTextField6.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal


            }else{
            

                //AQUI
                String[] arr  = String.valueOf(total).split("\\.");  // declaro el array
                int enter = Integer.parseInt(arr[0]);
                //int deci = Integer.parseInt(arr[1]);
                String deci = arr[1];

                //String x = Integer.toString(deci);
                String x = deci;
                int contardeci = x.length();

                if (contardeci > 1){

                char ultimoNumero=String.valueOf(total).charAt(String.valueOf(total).length()-1);
                String ultimodigito = String.valueOf(ultimoNumero);

                    //if (ultimodigito.equals("0") || ultimodigito.equals("5")){
                    if (ultimodigito.equals("0")){
                        jTextField5.setText("0.00"); //redondeo
                        jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                        jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                        double igv = total - subtottal -Monto_icbpre_Cab;


                        BigDecimal bd3 = new BigDecimal(igv);
                        bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        igv = bd3.doubleValue();

                        jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                        jTextField6.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //TotalFinal

                    }else{
                        if (Integer.parseInt(ultimodigito) >= 1 && Integer.parseInt(ultimodigito) <= 4){

                            Double redondeo = (Double.valueOf(ultimodigito) /100) * -1;
                            Double nuevototal = total - (Double.valueOf(ultimodigito) /100);

                            BigDecimal bdNT = new BigDecimal(nuevototal);
                            bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            nuevototal = bdNT.doubleValue();

                            jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                            jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                            double igv = total - subtottal - Monto_icbpre_Cab;

                            BigDecimal bd3 = new BigDecimal(igv);
                            bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                            igv = bd3.doubleValue();

                            BigDecimal bd5 = new BigDecimal(nuevototal);
                            bd5 = bd5.setScale(podecimal, BigDecimal.ROUND_HALF_UP);

                            jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                            jTextField5.setText(String.valueOf(redondeo)); //redondeo
                            //jTextField6.setText(String.valueOf(nuevototal)); //Total a Pagar
                            jTextField6.setText(bd5.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

                        }else{

                            if (Integer.parseInt(ultimodigito) >= 5 && Integer.parseInt(ultimodigito) <= 9){
                                //double ultimonuevo = Double.valueOf(ultimodigito) - 5;
                                double ultimonuevo = Double.valueOf(ultimodigito);
                                Double redondeo = (ultimonuevo /100) * -1;
                                Double nuevototal = total - (ultimonuevo /100);
                                //total = nuevototal;
                                //SubTotal = (total / (1 + (IGV / 100)));
                                BigDecimal bdNT = new BigDecimal(nuevototal);
                                bdNT = bdNT.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                nuevototal = bdNT.doubleValue();


                                jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                                jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); // subtotal
                                double igv = total - subtottal - Monto_icbpre_Cab;

                                BigDecimal bd3 = new BigDecimal(igv);
                                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                                igv = bd3.doubleValue();

                                jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //igv
                                jTextField5.setText(String.valueOf(redondeo)); //redondeo
                                jTextField6.setText(String.valueOf(nuevototal)); //Total a Pagar

                            }

                        }
                    }
                }else{

                    this.jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //total
                    this.jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());  //subtotal
                    double igv = total - subtottal - Monto_icbpre_Cab;

                    BigDecimal bd3 = new BigDecimal(igv);
                    bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    igv = bd3.doubleValue();

                    this.jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());   // igv
                    jTextField5.setText("0.00");                                                                    //redondeo
                    jTextField6.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString()); //Total a pagar


                }
        }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void AsignaProductos(List<Venta> listaVenta) {
        int lengthproducto = 0;
        int cant = 0;
        ltaVen_Productos = listaVenta;
        double parcial, pvpx;
            

        for (int i = 0; i < listaVenta.size(); i++) {
            pvpx = ltaVen_Productos.get(i).getPrecio_Venta_Final();
            parcial = ltaVen_Productos.get(i).getTotal_producto();

            BigDecimal bd = new BigDecimal(pvpx);
            bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
            total = bd.doubleValue();

            BigDecimal bd1 = new BigDecimal(parcial);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            total = bd1.doubleValue();

            datosDetalle[0] = i + 1;
            datosDetalle[1] = ltaVen_Productos.get(i).getDescr_Producto();
            datosDetalle[2] = ltaVen_Productos.get(i).getUnidad();
            datosDetalle[3] = ltaVen_Productos.get(i).getFraccion();
            datosDetalle[4] = ltaVen_Productos.get(i).getPrecio_Venta();
            datosDetalle[5] = ltaVen_Productos.get(i).getDescuento();
            datosDetalle[6] = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            //if (ltaVen_Productos.get(i).getId_Laboratorio().equals("DCTO")){            
            //datosDetalle[7] = ltaVen_Productos.get(i).getOP_decuento();
            //}else{            
            datosDetalle[7] = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            //}
            
            datosDetalle[8] = ltaVen_Productos.get(i).getId_Laboratorio();
            datosDetalle[9] = ltaVen_Productos.get(i).getgratuita();
            datosDetalle[10] = ltaVen_Productos.get(i).getOP_decuento();
            datosDetalle[11] = ltaVen_Productos.get(i).getidvtadet();
            datosDetalle[12] = ltaVen_Productos.get(i).getEmpaque();
            
            datosDetalle[13] = ltaVen_Productos.get(i).getId_Producto();
            datosDetalle[14] = ltaVen_Productos.get(i).getOpISC_CAB();
            datosDetalle[15] = ltaVen_Productos.get(i).getOpISC();


            lengthproducto = ltaVen_Productos.get(i).getDescr_Producto().length();

            if (lengthproducto > cant) {
                cant = lengthproducto;
            }

            model.addRow(datosDetalle);

        }
        Columnaresize(cant);
        double montoISC = ltaVen_Productos.get(0).getOpISC_CAB();
        CalculaMontos(montoISC);
        this.jButton8.setEnabled(false);
        Botones(true);
        jTable1.getSelectionModel().setSelectionInterval(0, 0);
    }

    private void Columnaresize(int length) {
        TableColumn col1 = colModel1.getColumn(1);
        col1.setMaxWidth(length * 12);
        col1.setMinWidth(length * 12);

    }

    private void Botones(boolean valor) {
        this.jButton2.setEnabled(valor);
        this.jButton4.setEnabled(valor);
    }

    private void LimpiatTabla() {
        int cant = tablanotas.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model_notas.removeRow(i);
            }
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

    private void Elimina_Producto() {
        int fila = this.jTable1.getSelectedRow();

        if (fila >= 0) {

            Confirmar p = new Confirmar(objetoventana, "<html>DESEAS ELIMINAR EL PRODUCTO:  \n  " + this.jTable1.getValueAt(fila, 1) + " </html>");
            p.show(true);

            if (p.confirmar == 1) {
                model.removeRow(fila);
                ltaVen_Productos.remove(fila);
                CalculaMontos(0);
                this.jTable1.requestFocus();
                jTable1.getSelectionModel().setSelectionInterval(0, 0);
            }
        }

    }

    private void GuardarNotadeCredito() {
    String cmbObs="";
    String ObsSelected="";
        if (jcomboObs.getSelectedIndex() > 0) {
            cmbObs = String.valueOf(listaObservaciones.get(jcomboObs.getSelectedIndex() - 1).getidObs());
            ObsSelected = String.valueOf(listaObservaciones.get(jcomboObs.getSelectedIndex() - 1).getDescripcionObs());
            //cmbObs = jcomboObs.getSelectedItem().toString();
        /*}
        if (this.jTextArea1.getText().length() > 0) {
                Color p = new Color(236, 233, 216);
                HabilitaCampos(p);*/

        NODCRSerieNumero mota = new NODCRSerieNumero(objetoventana,idventa,txtserie.getText(),txtnumero.getText(),txtfecha.getText(),"NC");
        mota.pack();
        mota.show(true);      
        

        Venta objventa;
        serie = mota.getSerie();
        numero = mota.getNumero();
        LogicaVenta objlogVenta = new LogicaVenta();
        String idproducto;
        int empaque;
        int idvtadeta;
        int idprodrecarga;
        int emp;
        double OpDescuentoCabecera = 0.0;
        double OpgratuitoCabecera = 0.0;
        double OpDescuentoDetalle = 0.0;
        double auxigv = 0.0;
        double auxigvDetalle = 0.0;
        String esImprimible = "0";
        String tipoprecio="";
        double AUXIGV1 = 0.0;
        double gratuita = 0.0;
        double OPdescuento = 0.0;        
        int undad, fraccion;
        String lab="";
        double opisc_CAB=0.0;
        double opisc=0.0;
        double parcial,pvp,dcto,pvpx;
        String MonedaIdvta;
        listaVenta_Detalle.removeAll(listaVenta_Detalle);

        if (serie != null && numero != null) {

            //double subtotal = Double.parseDouble(this.jTextField2.getText().trim());
            double subtotal = subtottal;
            double auxparcial=0.0;
            double auxparcialCabecera=0.0;
            double redon = Double.valueOf(this.jTextField5.getText());
            double tfinal = Double.valueOf(this.jTextField6.getText());
            String timpre = String.valueOf(this.txtTImpre.getText());
            
            objventa = new Venta(idbotica, serie, numero, total, idventa, fecha, id_peronal, txtcliente.getText(), txtdni.getText(), txtruc.getText(), jTextArea1.getText(), txtdireccion.getText(), subtotal,OpGravada,OpGravadaCabecera,
                    redon,tfinal,timpre);

            for (int i = 0; i < this.jTable1.getRowCount(); i++) {
                OpDescuentoDetalle = 0.0;
                idproducto = ltaVen_Productos.get(i).getId_Producto();
                undad = Integer.parseInt(this.jTable1.getValueAt(i, 2).toString());
                fraccion = Integer.parseInt(this.jTable1.getValueAt(i, 3).toString());
                pvp = Double.parseDouble(this.jTable1.getValueAt(i, 4).toString());
                dcto = Double.parseDouble(this.jTable1.getValueAt(i, 5).toString());
                pvpx = Double.parseDouble(this.jTable1.getValueAt(i, 6).toString());
                parcial = Double.parseDouble(this.jTable1.getValueAt(i, 7).toString());
                lab = this.jTable1.getValueAt(i, 8).toString();
              
                gratuita = Double.parseDouble(this.jTable1.getValueAt(i, 9).toString());
                //OPdescuento = Double.parseDouble(this.jTable1.getValueAt(i, 10).toString());   
                
                int ICBPER_1 = objProducto.EsICBPER(idproducto);
                
                if(lab.equals("DCTO") && idproducto.equals("X0500") ){
                OPdescuento = pvp * undad;
                }else{
                    if(lab.equals("DCTO")){
                       OPdescuento = Double.parseDouble(this.jTable1.getValueAt(i, 10).toString());    
                       //OPdescuento = pvp * undad;
                    }else{
                        OPdescuento =0;
                    }
                    
                }
                
               
                idvtadeta = Integer.parseInt(this.jTable1.getValueAt(i, 11).toString());                
                //idprodrecarga = Integer.parseInt(this.jTable1.getValueAt(i, 12).toString()); 
                Venta enti = objProducto.Recupera_Descuento_Producto(idproducto, "", "01",String.valueOf(pvpx));
                auxigv = enti.getIGV();
                auxigvDetalle = enti.getIGV();
                empaque = ltaVen_Productos.get(i).getEmpaque();
                idprodrecarga = ltaVen_Productos.get(i).getIdprodrecarga();
                tipoprecio = ltaVen_Productos.get(i).getId_Tipo_Precio();
                //opisc_CAB = ltaVen_Productos.get(i).getOpISC_CAB(); 
                //opisc = ltaVen_Productos.get(i).getOpISC();
                
                
                //if (ICBPER_1 == 1 && !lab.equals("DCTO")){
                if(ICBPER_1 >0){
                    double Monto_icbpre_1 = objProducto.PrecioICBPER(idproducto);
                    double empa=objProducto.PrecioICBPEREMP(idproducto);
                    double c_cantT = undad * empa + fraccion;
                    
                    //opisc_CAB  = Monto_icbpre_1 * c_cantT;
                    //opisc = Monto_icbpre_1 * c_cantT;
                    
                    opisc = Monto_icbpre_1 * c_cantT;
                    opisc_CAB = opisc_CAB + opisc;
                    


                    //double precioICB = objProducto.PrecioICBPER(idproducto);
                    //double empa=objProducto.PrecioICBPEREMP(idproducto);                    
                    //double c_cantT = unid * empa + frac;
                    
                    //OpISC= precioICB * c_cantT;                    
                    //OpISC_CAB = OpISC_CAB + OpISC;
                    
                    
                    
                    
                    
                }else{
                    //opisc_CAB  =0;
                    opisc =0;
                }
                
                
                AUXIGV1 += auxigv;
                if (AUXIGV1 > 18){
                        AUXIGV1 = 18;
                    }
                double sutot = 0.0;
                if (auxigv == 0.0) {
                    //sutot = 0.0;
                    sutot = parcial / ((1 + (auxigv / 100)));
                    OpInafecta = parcial;
                    //OpInafectaCabecera +=pvpx;

                }else{
                    sutot = parcial / ((1 + (auxigv / 100)));
                    OpInafecta = 0.0;

                    //auxparcialCabecera = (parcial / (1 + (IGV / 100)));
                    auxparcialCabecera = (parcial / (1 + (ltaVen_Productos.get(i).getIGV() / 100)));
                    subtottalCabecera = auxparcialCabecera;
                    OpGravadaDetalle = subtottalCabecera;
                }

                if (pvpx < 0){

                    //OpDescuentoCabecera = OpDescuentoCabecera + pvpx;
                    OpDescuentoDetalle = pvpx;
                }

                String micodigo = mantProduc.Recupera_Promo_Codigo1(idproducto,0).toString().trim();
                String esgratuita2 = objProducto.RetornaEsGratisPromo(idproducto,micodigo); // promocion
                OpDescuentoCabecera = OpDescuentoCabecera + OPdescuento;
                OpgratuitoCabecera = OpgratuitoCabecera + gratuita;
                
                listaVenta_Detalle.add(new Venta(idbotica, idproducto, parcial, undad, fraccion, pvp,dcto,pvpx,id_peronal,auxigvDetalle,sutot,OpInafecta,OpGravada,OpGravadaDetalle,OpDescuentoDetalle,lab,empaque,gratuita,esgratuita2,OPdescuento,idvtadeta,idprodrecarga,tipoprecio,opisc_CAB,opisc));
                
            }

                MonedaIdvta = this.txtmoney.getText().toString();


                int validavta = objlogVenta.ObtieneValidaHay(idventa);

                if (validavta == 0 ){
                                
                    boolean resultado = objlogVenta.InsertaNotaCredito(objventa, listaVenta_Detalle,AUXIGV1,OpInafectaCabecera,OpDescuentoCabecera,OpgratuitoCabecera,MonedaIdvta,"NC",cmbObs);
                    String InternoVta = objlogVenta.recuperainternoNC(serie, numero,this.txtfecha.getText(),this.txttipoventa.getText());

                    if (resultado) {
                    boolean TmpDetalleNC = objlogVenta.InsertaTmpDetalleNC(objventa, listaVenta_Detalle,idventa,"1");
                    }

                    String ImpresionDouble = objlogVenta.recuperaImpresionDoubleNC();

                    objXML_GENERAR = new XML_GENERAR();
                    try {
                        objXML_GENERAR.Execute(LoadInterno(InternoVta));
                    } catch (IOException ex) {
                        Logger.getLogger(NotaCredito1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(NotaCredito1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    esImprimible = mantProduc.Recupera_Imprimible(idventa,idbotica).toString();

                    if (esImprimible.equals("1")){
                        GeneraImpresion(objventa,listaVenta_Detalle,InternoVta,ImpresionDouble,ObsSelected);
                    }

                    if (resultado) {
                        JOptionPane.showMessageDialog(this, "SU NOTA DE CREDITO FUE GENERADA CORRECTAMENTE \n SERIE: "+ serie + "\n NUMERO : " + numero, "Nota de Credito", JOptionPane.INFORMATION_MESSAGE);
                        objventas.marcacdo = false;
                        objventas.Habilita(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS HUBO UN ERROR AL GENERAR SU NOTA DE CREDITO \n PORFAVOR COMUNIQUESE CON INFORMATICA ", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }

                }else{
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS HUBO UN ERROR AL GENERAR SU NOTA DE CREDITO1 \n PORFAVOR COMUNIQUESE CON INFORMATICA ", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                        dispose();
                }

            
        }

        } else {
                JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar una Observacón", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                ModificaObs(true);
                this.jcomboObs.requestFocus();
        }
    }

    private void GeneraImpresion(Venta obj,List<Venta> listaVtasDetalle,String Interno,String ImpresionDouble, String ObsSelec) {
        
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Double> vtadettotal = new ArrayList<Double>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> tunit = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        Double IGV = 0.00, Totalizado = 0.00, sumaTotal = 0.00, SubSub = 0.00,Redondeo=0.0,MiTotal=0.0,TotFinal=0.0;
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0;
        String cliente = "", Np = "", salesman = "", direccion = "", RUC = "", Direccion = null, PLACA = "", DNICLI="",
                botica = "",DireccionFiscal="",serie = null, numero = "",Delivery="",correo="",vtaruc="";
        
        String ConvTotaLetras ="";
        String direcCliente="", Nomvendedor="",Nomcajero="",tipodocumento="",
                numdocumento="",documentoemitido="",serienumero="",idvta="",observacion="",docreferencia="",
                sernumref="",fecharef = "", cantidad="", producto="",simbolo="",idmoneda="",disclaimer="",
                rucbarcode = "",tipdocbarcode="",igvbarcode="",totalbarcode="",tipodocadquiriente="",
                numerodocadquiriente="",firmabarcode="",tituloopgravada="",tituloopinafecta="",tituloopigv="",
                tituloopimportetotal="",tituloopredondeo="",titulooptotalpagar="",titulooptotaldscto="",
                fecha_aux = "",fecha_aux1 = "", hora = "",money = "";

        Double  OP_GRATUITA=0.0,opimportetotal=0.0,optotalpagar=0.0,
                opimportetotal1=0.0, opgravada1=0.0,opinafecta1 =0.0, opigv1 = 0.0, Redondeo1 = 0.0,
                optotalpagar1 = 0.0, opdescuento1=0.0,totaldet=0.0;


        String vendedor = null, cajero = null;
        Integer Largo = 0;
        int dos = 1;
        int lineas = 0;
        String fecha = null;
        String StrTipoDoc ="";
        Integer Espacios = 7;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        int confirma = 0;
        FileOutputStream os = null;
        PrintStream ps = null;
        CallableStatement procedure = null;
        CallableStatement procedure4 = null;
        CallableStatement procedure5 = null;

        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps1;

        try{

            confirma = 1;
            if (confirma == 1) {

                        file = new FileWriter(Impresora_Factura);
                        //file = new FileWriter("//10.10.1.253//BIXOLON//");
                        buffer = new BufferedWriter(file);
                        ps1 = new PrintWriter(buffer);
                        String miinterno = obj.getId_Venta().toString().trim();
                        String modoImpresionDoc = obj.gett_impre().trim();
                        sumaTotal = 0.00;

                        //if (conex.isClosed()) {
                        conex = new ConexionPool().getConnection();
                        //}

                        if (ImpresionDouble.equals("1")){
                            dos = 2;
                        }else{
                            dos = 1;
                        }

                      for (int i = 0; i < dos; i++) {

                        procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_NC (?,?) }");
                        procedure.setString(1, Interno);
                        procedure.setString(2, idbotica);
                        rs = procedure.executeQuery();

                        while (rs.next()) {


                            cliente = "  " + rs.getString(1);
                            Largo = cliente.length();

                            if (Largo < 35) {
                                for (Integer ini = Largo; ini < 35; ini++) {
                                    cliente = cliente + " ";
                                }
                            }

                            botica = rs.getString("mibotica");
                            DireccionFiscal = rs.getString("DIRECCIONFISCAL");
                            vendedor = rs.getString("vendedor");
                            cajero = rs.getString("cajero");
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cliente = cliente + "     ";
                            ProductoDescripcion.add(rs.getString(6));
                            Labs.add(rs.getString(7));
                            CantidadU.add(rs.getInt(8));
                            CantidadF.add(rs.getInt(9));
                            Precio.add(rs.getDouble(12));  //pvf
                            PrecioF.add(rs.getDouble(10));
                            SubSub = rs.getDouble(13);
                            IGV = rs.getDouble(14);
                            Totalizado = rs.getDouble(15);
                            sumaTotal = sumaTotal + rs.getDouble(10);//+ rs.getDouble(11);
                            fecha = rs.getDate(4).toString();
                            salesman = rs.getString(5);
                            direccion = rs.getString(3);
                            RUC = rs.getString(2);
                            Direccion = rs.getString("Direccion");
                            //opgravada = rs.getDouble("OP_GRAVADA");
                            //opinafecta = rs.getDouble("Op_Inafecta");

                            opigv = rs.getDouble("IGV");
                            opdescuento = rs.getDouble("Op_Descuento");

                            Delivery = rs.getString("Delivery");
                            correo = rs.getString("CorreoEmpresa");
                            vtadettotal.add(rs.getDouble("ventadetalletotal"));
                            vtaruc = rs.getString("rucventa");
                            tunit.add(rs.getDouble("TOTAL_UNITARIO"));
                            //Redondeo = rs.getDouble("Redondeo");
                            //TotFinal = rs.getDouble("TotFinal");
                        }
                        
                        Redondeo = Double.valueOf(this.jTextField5.getText());
                        TotFinal = Double.valueOf(this.jTextField6.getText());
                        opigv = Double.valueOf(this.jTextField3.getText());
                        MiTotal = Double.valueOf(this.jTextField4.getText());
                        opgravada = 0.0;
                        opinafecta = 0.0;

                        for (Integer b = 0; b < listaVtasDetalle.size(); b++) {
                            opgravada =listaVtasDetalle.get(b).getOpGravada();
                            //opinafecta =listaVtasDetalle.get(b).getOpInafecta();
                            //opigv +=listaVtasDetalle.get(b).getIGV();
                            opdescuento =listaVtasDetalle.get(b).getOpDescuento();

                        }

                        procedure = conex.prepareCall("{ call RECUPERA_DATOS (?,?) }");
                        procedure.setString(1, Interno);
                        procedure.setString(2, idbotica);
                        rs4 = procedure.executeQuery();

                        while (rs4.next()) {
                            opinafecta = rs4.getDouble("OP_INAFECTA");
                        }
                        //fecha = objFechaHora.MysqlToJuliano(fecha).toString();
                        BigDecimal bdopgravada = new BigDecimal(opgravada);
                        BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                        BigDecimal bdigv = new BigDecimal(opigv);
                        BigDecimal bdopdescuento = new BigDecimal(opdescuento);
                        BigDecimal bdopredondeo = new BigDecimal(Redondeo);
                        BigDecimal bdoptotfinal = new BigDecimal(TotFinal);
                        BigDecimal bdMytotal = new BigDecimal(MiTotal);
                        
                        bdopgravada = bdopgravada.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opgravada = bdopgravada.doubleValue();
                        String opgrav = bdopgravada.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopinafecta = bdopinafecta.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opinafecta = bdopinafecta.doubleValue();
                        String opinaf = bdopinafecta.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdigv = bdigv.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opigv = bdigv.doubleValue();
                        String igvVta = bdigv.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopdescuento = bdopdescuento.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        opdescuento = bdopdescuento.doubleValue();
                        String opdcto = bdopdescuento.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

                        bdopredondeo = bdopredondeo.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        Redondeo = bdopredondeo.doubleValue();
                        String opredondeo = bdopredondeo.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        bdoptotfinal = bdoptotfinal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        TotFinal = bdoptotfinal.doubleValue();
                        String optotfinal = bdoptotfinal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        bdMytotal = bdMytotal.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                        MiTotal  = bdMytotal.doubleValue();
                        String MyTotalFinal = bdMytotal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                        
                        //String StrTipoDoc = LeerLoadInterno(Interno);
                        procedure5 = conex.prepareCall("{ call XML_LOAD_INTERNO(?) }");                        
                        procedure5.setString("PId_Venta", Interno);
                        rs5 = procedure5.executeQuery();

                        while (rs5.next()) {
                            StrTipoDoc =  rs5.getString("ID_TIPO_DOCUMENTO_SUNAT")+"-"+rs5.getString("Numero");
                        }                       

                        String delimiter = "/";
                        String delimiter1 = "-";
                        String[] temp;
                        String[] temp1;
                        
                        String FechaActual = objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString());
                        String FechaActualNC = objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString());
                        String HoraActualNC = objFechaHora.RetornaHora();
                        temp = FechaActual.split(delimiter);
                        temp1 = StrTipoDoc.split(delimiter1);

                        String leerfecha = temp[2]+temp[1]+temp[0];
                        String leerStrTipoDoc = temp1[0];
                        String leerNumero = temp1[1];

                        String LeerNuevaRutaXML = "/mnt/XML/" + leerfecha + "/" + idbotica + "/";
                        //String LeerNuevaRutaXML = "D:\\XML\\" + leerfecha + "\\" + idbotica + "\\";
                        String LeerMyFile = RUC + '-' + leerStrTipoDoc + '-' + obj.getSerie() + '-' + leerNumero;

                        String XMLGENERADO = objXML_GENERAR.readXML(Interno,LeerNuevaRutaXML,LeerMyFile);
                        String recSignature = objXML_GENERAR.readXMLSignature(Interno,LeerNuevaRutaXML,LeerMyFile);
                        
                        if (conex.isClosed()) {
                            conex = new ConexionPool().getConnection();
                        }
                        procedure4 = conex.prepareCall("{ call GUARDA_FIRMADIGITAL(?,?,?,?,?,?) }");
                        procedure4.setString("PId_Botica", idbotica);
                        procedure4.setString("PId_Venta", Interno);
                        procedure4.setString("PId_Serie", obj.getSerie());
                        procedure4.setString("PId_Numero", leerNumero);
                        procedure4.setString("PDigest", XMLGENERADO);
                        procedure4.setString("PSignature", recSignature);
                        rs3 = procedure4.executeQuery();

                        while (rs3.next()) {
                        String Firma2 =  rs3.getString("resultado");
                        }
                        //conex.close();
                        String FechaVtaPrint = this.txtfecha.getText();                        
                        String delimiterPrint = "-";
                        String[] temp3;
                        temp3 = FechaVtaPrint.split(delimiterPrint);
                        String leerfechaprint = temp3[2]+"/"+temp3[1]+"/"+temp3[0];




                        /*
                        procedure = conex.prepareCall("{ call PDF_VENTAFINAL (?,?) }");
                        procedure.setString(1, Interno);
                        procedure.setString(2, ConvTotaLetras);
                        
                        rs = procedure.executeQuery();

                        lista_VentaFinal.removeAll(lista_VentaFinal);
                        NumberFormat formatter = new DecimalFormat("#,##0.00");

                        while (rs.next()) {

                            botica = rs.getString("Empresa_Botica");
                            Direccion = rs.getString("Direccion_Botica");
                            DireccionFiscal = rs.getString("Direccion_Empresa");
                            RUC = rs.getString("RUC");

                            cliente = rs.getString("NOMCLIENTE");
                            direcCliente = rs.getString("DIRECCION_CLIENTE");
                            tipodocumento = rs.getString("TIPO_DOCUMENTO");
                            numdocumento = rs.getString("NUMERO_DOCUMENTO");
                            documentoemitido = rs.getString("Documento");
                            serienumero = rs.getString("SerNum");
                            fecha_aux = String.valueOf(rs.getDate("Fecha_Venta"));
                            fecha = objFechaHora.MysqlToJuliano(fecha_aux).toString();
                            hora = rs.getString("Hora_Venta");
                            idvta = rs.getString("Id_Venta");
                            cajero = rs.getString("CAJERO");
                            vendedor = rs.getString("VENDEDOR");
                            TotFinal = rs.getDouble("Total");
                            money = rs.getString("MONEDA");
                            idmoneda = rs.getString("Id_Moneda");
                            observacion = rs.getString("Observacion");
                            docreferencia = rs.getString("DocumentoRef");
                            sernumref = rs.getString("SerNumRef");
                            fecha_aux1 = String.valueOf(rs.getDate("Fecha_VentaRef"));
                            fecharef = objFechaHora.MysqlToJuliano(fecha_aux1).toString();
                            serie = rs.getString("Serie");
                            numero = rs.getString("Numero");
                            cantidad = rs.getString("Cantidad");
                            producto = rs.getString("Producto");
                            totaldet = rs.getDouble("Totaldet");
                            String totaldetx = formatter.format(totaldet);
                            String totaldetx1 = totaldetx.replace(".", ";");
                            String totaldetx2 = totaldetx1.replace(",", ".");
                            String totaldetx3 = totaldetx2.replace(";", ",");

                            tituloopgravada = rs.getString("TITULOOPGRAVADA");
                            opgravada1 = rs.getDouble("OPGRAVADA");
                            String opgravada1x = formatter.format(opgravada1);
                            String opgravada1x1 = opgravada1x.replace(".", ";");
                            String opgravada1x2 = opgravada1x1.replace(",", ".");
                            String opgravada1x3 = opgravada1x2.replace(";", ",");
                            if (opgravada1x3.equals("0")|| opgravada1x3.equals("0.0")|| opgravada1x3.equals("0.00")){
                                opgravada1x3 = "";
                            }
                            tituloopinafecta = rs.getString("TITULOINAFECTA");
                            opinafecta1 = rs.getDouble("OPINAFECTA");
                            String opinafecta1x = formatter.format(opinafecta1);
                            String opinafecta1x1 = opinafecta1x.replace(".", ";");
                            String opinafecta1x2 = opinafecta1x1.replace(",", ".");
                            String opinafecta1x3 = opinafecta1x2.replace(";", ",");
                            if (opinafecta1x3.equals("0")|| opinafecta1x3.equals("0.0")|| opinafecta1x3.equals("0.00")){
                                opinafecta1x3 = "";
                            }
                            tituloopigv = rs.getString("TITULOIGV");
                            opigv1 = rs.getDouble("IGV");
                            String opigv1x = formatter.format(opigv1);
                            String opigv1x1 = opigv1x.replace(".", ";");
                            String opigv1x2 = opigv1x1.replace(",", ".");
                            String opigv1x3 = opigv1x2.replace(";", ",");
                            if (opigv1x3.equals("0")|| opigv1x3.equals("0.0")|| opigv1x3.equals("0.00")){
                                opigv1x3 = "";
                            }
                            tituloopimportetotal = rs.getString("TITULOIMPORTETOTAL");
                            opimportetotal1 = rs.getDouble("IMPORTETOTAL");
                            String opimportetotx = formatter.format(opimportetotal1);
                            String opimportetotx1 = opimportetotx.replace(".", ";");
                            String opimportetotx2 = opimportetotx1.replace(",", ".");
                            String opimportetotx3 = opimportetotx2.replace(";", ",");
                            if (opimportetotx3.equals("0")|| opimportetotx3.equals("0.0")|| opimportetotx3.equals("0.00")){
                                opimportetotx3 = "";
                            }
                            tituloopredondeo = rs.getString("TITULOREDONDEO");
                            Redondeo1 = rs.getDouble("REDONDEO");
                            String Redondeo1x = formatter.format(Redondeo1);
                            String Redondeo1x1 = Redondeo1x.replace(".", ";");
                            String Redondeo1x2 = Redondeo1x1.replace(",", ".");
                            String Redondeo1x3 = Redondeo1x2.replace(";", ",");
                            if (Redondeo1x3.equals("0")|| Redondeo1x3.equals("0.0")|| Redondeo1x3.equals("0.00")){
                                Redondeo1x3 = "";
                            }
                            titulooptotalpagar = rs.getString("TITULOTOTALPAGAR");
                            optotalpagar1 = rs.getDouble("TOTALPAGAR");
                            String optotalpagar1x = formatter.format(optotalpagar1);
                            String optotalpagar1x1 = optotalpagar1x.replace(".", ";");
                            String optotalpagar1x2 = optotalpagar1x1.replace(",", ".");
                            String optotalpagar1x3 = optotalpagar1x2.replace(";", ",");
                            if (optotalpagar1x3.equals("0")|| optotalpagar1x3.equals("0.0")|| optotalpagar1x3.equals("0.00")){
                                optotalpagar1x3 = "";
                            }
                            titulooptotaldscto = rs.getString("TITULOTOTALDSCTO");
                            opdescuento1 = rs.getDouble("TOTALDSCTO");
                            String opdescuento1x = formatter.format(opdescuento1);
                            String opdescuento1x1 = opdescuento1x.replace(".", ";");
                            String opdescuento1x2 = opdescuento1x1.replace(",", ".");
                            String opdescuento1x3 = opdescuento1x2.replace(";", ",");
                            if (opdescuento1x3.equals("0")|| opdescuento1x3.equals("0.0")|| opdescuento1x3.equals("0.00")){
                                opdescuento1x3 = "";
                            }

                            disclaimer = rs.getString("DISCLAIMER");

                            rucbarcode = rs.getString("RUCBARCODE");
                            tipdocbarcode = rs.getString("TIPODOCUMENTOBARCODE");
                            igvbarcode = rs.getString("IGVBARCODE");
                            totalbarcode = rs.getString("TOTALBARCODE");
                            tipodocadquiriente = rs.getString("TIPODOCADQUIRIENTE");
                            numerodocadquiriente = rs.getString("NUMERODOCADQUIRIENTE");
                            firmabarcode = rs.getString("Firma");

                        lista_VentaFinal.add(new Venta(botica,Direccion,DireccionFiscal,RUC,
                                serie,numero,TotFinal,fecha,cliente,direcCliente,tipodocumento,numdocumento,
                                documentoemitido,serienumero,hora,idvta,cajero,vendedor,money,observacion,
                                docreferencia,sernumref,fecharef,cantidad,producto,totaldetx3,opgravada1x3,
                                opinafecta1x3,opigv1x3,opimportetotx3,Redondeo1x3,optotalpagar1x3,opdescuento1x3,
                                idmoneda,disclaimer,tituloopgravada,tituloopinafecta,tituloopigv,tituloopimportetotal,
                                tituloopredondeo,titulooptotalpagar,titulooptotaldscto,
                                rucbarcode,tipdocbarcode,igvbarcode,totalbarcode,tipodocadquiriente,numerodocadquiriente,firmabarcode
                                ));                            
                        }*/



/*Modo impresion*/ //termica
if (modoImpresionDoc.equals("1")){

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        ConvTotaLetras = NumLetra.Convertir(optotfinal, true,idmoneda);
                        //String cadenaPDF417 = rucbarcode.trim()+"|"+tipdocbarcode.trim()+"|"+igvbarcode.trim()+"|"+totalbarcode.trim()+"|"+tipodocadquiriente.trim()+"|"+numerodocadquiriente.trim()+"|"+firmabarcode.trim();
                        
                        /*imprimeBoletaNueva(Interno,idbotica,ConvTotaLetras,lista_VentaFinal,cadenaPDF417/*,
                          rucbarcode,tipdocbarcode,serie,numero,igvbarcode,totalbarcode,fecha,tipodocadquiriente,
                          numerodocadquiriente,firmabarcode);*/
                        imprimeBoleta(Interno,idbotica,ConvTotaLetras);

                        /*setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC :" + RUC);
                        Dibuja_Linea(ps1);
                        ps1.println("       NOTA DE CREDITO ELECTRONICA ");
                        ps1.println("        " + obj.getSerie() + " - " + obj.getNumero());
                        Dibuja_Linea(ps1);
                        //ps.println("S/N       :" + maq);
                        ps1.println("Fecha :    " + FechaActualNC + "  Hora : " + HoraActualNC);
                        ps1.println("Caj   :    " + cajero + " Ven : " + salesman + " Int : " + Interno);
                                /*if (PLACA != null) {
                                    ps1.println("Placa   : " + PLACA);
                                }*/
                        /*String DOC ="";
                        if (this.txttipoventa.getText().equals("FACTURA ELECTRONICA") || this.txttipoventa.getText().equals("FACTURA")){
                            DOC = this.txtruc.getText();
                        }else{
                            DOC = txtdni.getText();
                        }
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     :" + cliente.trim());
                        ps1.println("DIRECCION :" + direccion);
                        ps1.println("RUC/DNI   :" + DOC);
                        Dibuja_Linea(ps1);
                        ps1.println("Motivo de Emision :" + ObsSelec);
                        ps1.println("Documento que Modifica :" + this.txttipoventa.getText());
                        ps1.println("Serie/Nro :" + this.txtserie.getText() + "-" + this.txtnumero.getText());
                        ps1.println("Fecha     :" + leerfechaprint);
                        Dibuja_Linea(ps1);                       
                        ps1.println("Cant     " + "Descripcion" + "                     " + "Importe");
                        Dibuja_Linea(ps1);
                        lineas = 7;

                        String Fusionado = "";
                        Align1 formato = new Align1(15, Align1.JUST_RIGHT,null);

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */
                    
                        //for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE NC
                        /*for (Integer sll = 0; sll < listaVtasDetalle.size(); sll++) {
                            Fusionado = "";

                            //if (CantidadF.get(sll) > 0) {
                            if (listaVtasDetalle.get(sll).getFraccion() > 0) {
                                //Fusionado = "F" + CantidadF.get(sll).toString();
                                Fusionado = "F" + listaVtasDetalle.get(sll).getFraccion();
                            }

                            //if (CantidadU.get(sll) > 0) {
                            if (listaVtasDetalle.get(sll).getUnidad() > 0) {
                                //Fusionado = CantidadU.get(sll).toString() + Fusionado;
                                if (listaVtasDetalle.get(sll).getId_Laboratorio().equals("BONI") || listaVtasDetalle.get(sll).getId_Laboratorio().equals("DCTO")){
                                    Fusionado = "";
                                }else{
                                    Fusionado = listaVtasDetalle.get(sll).getUnidad() + Fusionado;
                                }
                            }
                           
                            String sss = ltaVen_Productos.get(sll).getDescr_Producto().toString().trim();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }
                            
                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }                           
                            
                            String elPrecioF = MiFormato.format(PrecioF.get(sll));
                            
                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            //String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));
                            //String Lavtadettotal ="0";
                            //if (!listaVtasDetalle.get(sll).getId_Laboratorio().equals("DCTO")){
                            String Lavtadettotal = MiFormato.format(listaVtasDetalle.get(sll).getPrecio_Venta());
                            //}

                            if (Lavtadettotal.length() < 7) {//15
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {//15
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = ltaVen_Productos.get(sll).getDescr_Producto().length();
                            if (Largo > 25) {
                                Np = ltaVen_Productos.get(sll).getDescr_Producto().substring(0, Largo); //25
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 30; lpp++) { //29
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                //Np = Np + "\n" + espacio + ltaVen_Productos.get(sll).getDescr_Producto().substring(24, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            /*} else {
                                Np = ltaVen_Productos.get(sll).getDescr_Producto().trim();

                                for (Integer cor = ltaVen_Productos.get(sll).getDescr_Producto().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 30; lpp++) { //29
                                    espacio1 += " ";
                                }

                                //BigDecimal bd1 = new BigDecimal(Lavtadettotal);
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            /*}

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            //ps1.println(Fusionado + " " + sss + " " + Lavtadettotal.replace(",", "."));
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);


                            /*BigDecimal bdT = new BigDecimal(Double.valueOf(Lavtadettotal.replace(",", ".")));
                            ps1.println(Fusionado + "  " + sss + " " + bdT.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
                             *
                             */

                       /* }

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";

                        //ps1.println(espacioFooterIGV + "    " + Subtotal.replace(",", "."));

                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;                        

                        Align1 formato1 = new Align1(28, Align1.JUST_RIGHT,null);

                        Dibuja_Linea(ps1);
                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("TOTAL         : S./ " + formato1.format(MyTotalFinal));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + formato1.format(opdcto));
                        }
                        ps1.println();
                        String ultimo = "    " ;
                        //ultimo += "        " ;
                        ps1.println(ultimo);                        
                        ps1.println(" "+NumLetra.Convertir(optotfinal, true));
                        ps1.println(" ");
                        ps1.println("     " + XMLGENERADO);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL DOCUMENTO DE ");
                        ps1.println("VENTA ELECTRONICA, PUEDE SER CONSULTADO EN");
                        ps1.println("www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT");
                        ps1.println("ESTIMADO CLIENTE:");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER SOLICITADO DENTRO");
                        ps1.println("DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA");
                        ps1.println("NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(6, ps1);//8
                        cortar(ps1);
                        */
    }else{ // ticketera

                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC :" + RUC);
                        Dibuja_Linea1(ps1);
                        ps1.println("      NOTA DE CREDITO ELECTRONICA ");
                        ps1.println("       " + obj.getSerie() + " - " + obj.getNumero());
                        Dibuja_Linea1(ps1);
                        ps1.println("Fecha: " + FechaActualNC + "  Hora: " + HoraActualNC);
                        ps1.println("Caj  : " + cajero + " Ven : " + salesman + " Int : " + Interno);
                        /*if (PLACA != null) {
                            ps1.println("Placa   : " + PLACA);
                        }*/
                        String DOC ="";
                        if (this.txttipoventa.getText().equals("FACTURA ELECTRONICA") || this.txttipoventa.getText().equals("FACTURA")){
                            DOC = this.txtruc.getText();
                        }else{
                            DOC = txtdni.getText();
                        }
                        Dibuja_Linea1(ps1);
                        ps1.println("Sr(a)    :" + cliente.trim());
                        ps1.println("DIRECCION:" + direccion);
                        ps1.println("RUC/DNI  :" + DOC);
                        Dibuja_Linea1(ps1);
                        ps1.println("Motivo de Emision :" + ObsSelec);
                        ps1.println("Documento que Modifica :" + this.txttipoventa.getText());
                        ps1.println("Serie/Nro :" + this.txtserie.getText() + "-" + this.txtnumero.getText());
                        ps1.println("Fecha     :" + leerfechaprint);
                        Dibuja_Linea1(ps1);
                        ps1.println("Cant     " + "Descripcion" + "        " + "Importe");
                        Dibuja_Linea1(ps1);
                        lineas = 7; 

                        String Fusionado = "";
                        Align1 formato = new Align1(15, Align1.JUST_RIGHT,null);

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */

                        //for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE NC
                        for (Integer sll = 0; sll < listaVtasDetalle.size(); sll++) {
                            Fusionado = "";

                            //if (CantidadF.get(sll) > 0) {
                            if (listaVtasDetalle.get(sll).getFraccion() > 0) {
                                //Fusionado = "F" + CantidadF.get(sll).toString();
                                Fusionado = "F" + listaVtasDetalle.get(sll).getFraccion();
                            }

                            //if (CantidadU.get(sll) > 0) {
                            if (listaVtasDetalle.get(sll).getUnidad() > 0) {
                                //Fusionado = CantidadU.get(sll).toString() + Fusionado;
                                if (listaVtasDetalle.get(sll).getId_Laboratorio().equals("BONI") || listaVtasDetalle.get(sll).getId_Laboratorio().equals("DCTO")){
                                    Fusionado = "";
                                }else{
                                    Fusionado = listaVtasDetalle.get(sll).getUnidad() + Fusionado;
                                }
                            }

                            String sss = ltaVen_Productos.get(sll).getDescr_Producto().toString().trim();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 4; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 25; xx++) {
                                    sss = sss + " ";
                                }
                            }

                            String elPrecio = MiFormato.format(Precio.get(sll));

                            if (elPrecio.length() < 43) {
                                for (Integer xx = elPrecio.length(); xx < 43; xx++) {
                                    elPrecio = " " + elPrecio;
                                }
                            }

                            String elPrecioF = MiFormato.format(PrecioF.get(sll));

                            if (elPrecioF.length() < 22) {
                                for (Integer xx = elPrecioF.length(); xx < 22; xx++) {
                                    elPrecioF = " " + elPrecioF;
                                }
                            }

                            //String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));
                            String Lavtadettotal = MiFormato.format(listaVtasDetalle.get(sll).getPrecio_Venta());

                            if (Lavtadettotal.length() < 7) {//15
                                for (Integer xx = Lavtadettotal.length(); xx < 6; xx++) {//15
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            Largo = 0;
                            Largo = ltaVen_Productos.get(sll).getDescr_Producto().length();
                            if (Largo > 25) {
                                Np = ltaVen_Productos.get(sll).getDescr_Producto().substring(0, 26); //Largo
                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                    espacio1 += " ";
                                }
                                
                                //Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));
                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                String espacio = "     ";
                                /*for (int ki = 0; ki < maximo; ki++) {
                                    espacio += " ";
                                }*/
                                Np = Np + "\n" + espacio + ltaVen_Productos.get(sll).getDescr_Producto().substring(26, Largo);

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + espacio + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            } else {
                                Np = ltaVen_Productos.get(sll).getDescr_Producto().trim();

                                for (Integer cor = ltaVen_Productos.get(sll).getDescr_Producto().length(); cor < 20; cor++) {
                                    Np = Np + " ";
                                }

                                String espacio1 = "";
                                for (int lpp = Np.length(); lpp < 28; lpp++) { //29
                                    espacio1 += " ";
                                }

                                BigDecimal bdPU = new BigDecimal(tunit.get(sll));
                                //Np = Np + espacio1 + formato.format(Lavtadettotal.replace(",", "."));
                                Np = Np + espacio1 + Lavtadettotal.replace(",", ".");

                                /*aqui
                                */
                                /*if(Precio.get(sll)> 0.0){
                                if (!Labs.get(sll).equals("DCTO")){
                                Np = Np + "\n" + "   " + Fusionado + " Unidad x " + bdPU.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                                }
                                }*/

                            }                            
                            ps1.println(Fusionado + " " + Np);
                            //ps1.println(Np);

                        }

                        Numero_a_Letra NumLetra = new Numero_a_Letra();
                        sumaTotal = Math.round(sumaTotal * Math.pow(10, 2)) / Math.pow(10, 2);

                        String Subtotal = "";
                        Subtotal = MiFormato.format(SubSub).toString();

                        for (Integer lo = MiFormato.format(SubSub).toString().length(); lo < 94; lo++) {
                            Subtotal = " " + Subtotal;
                        }

                        /*String espacioFooterIGV = "";
                        espacioFooterIGV = "          ";
                        
                        String elIGV = "";
                        elIGV = MiFormato.format(IGV).toString().replace(",", ".");
                        BigDecimal bd2 = new BigDecimal(MIIGV);
                        elIGV = "(" + bd2.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "%)  " + elIGV;

                        BigDecimal bd = new BigDecimal(Totalizado);
                        String numero1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

                        String elTotalizado = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                        //elTotalizado = "S/. " + elTotalizado;

                        Align1 formato1 = new Align1(15, Align1.JUST_RIGHT,null);

                        Dibuja_Linea1(ps1);
                        ps1.println("OP GRAVADA    : S./ " + formato1.format(opgrav));
                        ps1.println("OP INAFECTA   : S./ " + formato1.format(opinaf));
                        ps1.println("IGV           : S./ " + formato1.format(igvVta));
                        ps1.println("TOTAL         : S./ " + formato1.format(MyTotalFinal));
                        ps1.println("REDONDEO      : S./ " + formato1.format(opredondeo));
                        ps1.println("TOTAL PAGAR   : S./ " + formato1.format(optotfinal));
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + formato1.format(opdcto));
                        }
                        ps1.println();
                        String ultimo = "    " ;
                        //ultimo += "        " ;
                        ps1.println(ultimo);
                        //ps1.println(" "+NumLetra.Convertir(optotfinal, true));
                        ps1.println(" ");
                        ps1.println("   " + XMLGENERADO);
                        ps1.println(" ");
                        ps1.println("ESTA ES UNA REPRESENTACION IMPRESA DEL  DOCUMENTO DE "
                                   + "VENTA ELECTRONICA PUEDE SER");
                        ps1.println("CONSULTADO EN www.nortfarma.com.pe");
                        ps1.println("AUTORIZADO MEDIANTE RESOLUCION NRO.0820050000034/SUNAT ");
                        ps1.println("ESTIMADO CLIENTE: ");
                        ps1.println("CUALQUIER CAMBIO O DEVOLUCION DEBE SER  SOLICITADO DENTRO "
                                   + "DE LAS 24 HORAS Y PRESENTANDO EL COMPROBANTE DE PAGO QUE ");
                        ps1.println("ACREDITE LA COMPRA, SEGUN RESOLUCION DE SUPERINTENDENCIA "
                                   + "NRO. 007-99/SUNAT.");
                        ps1.println("GRACIAS POR SU COMPRA");
                        correr(8, ps1);//8
                        cortar(ps1);*/

    }/*Fin modo impresion*/



                        rs.close();
                        rs3.close();
                        rs4.close();
                        rs5.close();

                        //listaInternos.set(i, null);

                        ProductoDescripcion.removeAll(ProductoDescripcion);
                        Labs.removeAll(Labs);
                        PrecioF.removeAll(PrecioF);
                        Precio.removeAll(Precio);
                        CantidadU.removeAll(CantidadU);
                        CantidadF.removeAll(CantidadF);

                        /*for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }*/
                 }
                        

                        ps1.close();
                        conex.close();
                        //listaInternos.set(i, null);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            //Error_Impresora("Error Impresora FileNotFoundException catch" + ex.getMessage(), listaInternos);            
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU NOTA DE CREDITO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            //Error_Impresora("Error Impresora IOException catch" + et.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU NOTA DE CREDITO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            //Error_Impresora("Error Impresora Exception  catch :" + pe.getMessage(), listaInternos);
            JOptionPane.showMessageDialog(this,"LO SENTIMOS HUBO UN ERROR AL IMPRIMIR SU NOTA DE CREDITO.", "NORTFARMA", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void imprimeBoletaNueva(String lstInternos,String idbotica, String ConvTotLetras,List<Venta> lstVentaFinal,
            String cadena/*,
            String rucbarcode1, String tipdocbarcode1,String seriebardcode, String numerobarcode,String igvbarcode1,
            String totalbarcode1, String fechabarcode, String tipodocadquiriente1, String numerodocadquiriente1,
            String firmabarcode1*/){

        String sistema = "Windows";
       
        try {

            String miinternovta = lstInternos;
            //parameters.put("PID_NUMLETRAS", ConvTotLetras);
            //parameters.put("cadpdf417", cadena);
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            /*parameters.put("PID_RUCBARCODE", rucbarcode1);
            parameters.put("PID_TIPDOCBARCODE", tipdocbarcode1);
            parameters.put("PID_SERIEBARCODE", seriebardcode);
            parameters.put("PID_NUMEROBARCODE", numerobarcode);
            parameters.put("PID_IGVBARCODE", igvbarcode1);
            parameters.put("PID_TOTALBARCODE", totalbarcode1);
            parameters.put("PID_FECHABARCODE", fechabarcode);
            parameters.put("PID_TIPDOCADQUIRIENTE", tipodocadquiriente1);
            parameters.put("PID_NUMDOCADQUIRIENTE", numerodocadquiriente1);
            parameters.put("PID_FIRMABARCODE", firmabarcode1);*/


            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVtaNuevo-OTRO.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            //jasperPrint = JasperFillManager.fillReport(masterReport, parameters, new  JRBeanCollectionDataSource(lstVentaFinal));
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            JasperPrintManager.printReport(jasperPrint, false);


        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void imprimeBoleta( String lstInternos,String idbotica, String ConvTotLetras) {
        String sistema = "Windows";
        //conex = new ConexionPool().getConnection();
        String miinternovta = lstInternos;

        try {
            parameters.put("PID_VENTA", miinternovta);
            parameters.put("PID_NUMLETRAS", ConvTotLetras);

            if (obj2.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            report_file = this.getClass().getResource("/Reportes/ReporteComprobanteVta.jasper");

            masterReport = (JasperReport) JRLoader.loadObject(report_file);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            //JasperViewer view = new JasperViewer(jasperPrint, false);

            JasperPrintManager.printReport(jasperPrint, false);

            //view.setTitle("REPORTE DE COMPROBANTES");
            //view.setVisible(true);
            //view.setSize(300, 500);

        //conex.close();

        } catch (Exception ex) {
            System.out.println("Error de reporte "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el comprobante", "Error ", JOptionPane.ERROR_MESSAGE);

        } /*finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }*/
    }
    
    public String LeerLoadInterno(String idventa) {
        String TIPODOCSUNAT = "" ;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call XML_LOAD_INTERNO (?) }");
            procedure.setString("PId_Venta", idventa);

            rs = procedure.executeQuery();

            while (rs.next()) {
                 TIPODOCSUNAT =  rs.getString("ID_TIPO_DOCUMENTO_SUNAT")+"-"+rs.getString("Numero");
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return TIPODOCSUNAT;
    }
    
    public List<Venta> LoadInterno(String idventa) {
        List<Venta> listaVtas = new ArrayList<Venta>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call XML_LOAD_INTERNO (?) }");
            procedure.setString("PId_Venta", idventa);

            rs = procedure.executeQuery();

            while (rs.next()) {
                listaVtas.add(
                        new Venta(rs.getString("Id_Botica"),
                        rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"),
                        rs.getInt("Id_Tipo_Venta"),
                        rs.getString("ID_TIPO_DOCUMENTO_SUNAT"),
                        rs.getString("Serie"),
                        rs.getString("Numero")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaVtas;
    }
    
    private void Error_Impresora(String mens, List<Venta> Internos) {
        if (Internos.size() > 0) {
            FormImpresora obprimter = new FormImpresora(objventas, true, idbotica, Internos, mens);
            obprimter.show(true);
        }
    }

    private void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println(" " +"-------------------------------------------------"); //36
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void Dibuja_Linea1(PrintWriter ps) {
        try {
            ps.println(" " + "------------------------------------"); //36
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public class Numero_a_Letra {

        private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco", "seis", "siete", "ocho", "nueve"};
        private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte y ", "treinta y ", "cuarenta y ",
            "cincuenta y ", "sesenta y ", "setenta y ", "ochenta y ", "noventa y "};
        private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

        public Numero_a_Letra() {
        }

        public String Convertir(String numero, boolean mayusculas, String money) {

            // System.out.println(">Dentro de la clase<:"+numero);

            String literal = "";
            String parte_decimal;
            //si el numero utiliza (.) en lugar de (,) -> se reemplaza
            //XXX numero = numero.replace(",", ".");
            //XXX System.out.println(">Dentro de la 2<:"+numero);
            numero = numero.replace(".", ",");
            // System.out.println(">Dentro de la 2.1<:"+numero);
            //si el numero no tiene parte decimal, se le agrega ,00
            if (numero.indexOf(",") == -1) {
                numero = numero + ",00";
            }
            //se valida formato de entrada -> 0,00 y 999 999 999,00
            if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
                //se divide el numero 0000000,00 -> entero y decimal
                String Num[] = numero.split(",");
                //de da formato al numero decimal
                parte_decimal = " con " + Num[1] + "/100 Soles.";
                //se convierte el numero a literal
                if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                    literal = "Cero ";
                } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                    literal = getMillones(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                    literal = getMiles(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                    //literal = getCentenas(Num[0]);
                    if (Integer.parseInt(Num[1]) < 1 || Num[0].substring(Num[0].length()-2,2).trim().equals("y")) {
                        literal =  getCentenas(Num[0]).substring(0,getCentenas(Num[0]).length()-2 );
                    }else{
                    literal = getCentenas(Num[0]);
                    }
                } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                    literal = getDecenas(Num[0]);
                } else {//sino unidades -> 9
                    literal = getUnidades(Num[0]);
                }
                //devuelve el resultado en mayusculas o minusculas
                if (mayusculas) {
                    return (literal + parte_decimal).toUpperCase();
                } else {
                    return (literal + parte_decimal);
                }
            } else {//error, no se puede convertir
                return literal = null;
            }
        }

        /* funciones para convertir los numeros a literales */
        private String getUnidades(String numero) {// 1 - 9
            //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
            String num = numero.substring(numero.length() - 1);
            return UNIDADES[Integer.parseInt(num)];
        }

        private String getDecenas(String num) {// 99
            int n = Integer.parseInt(num);
            if (n < 10) {//para casos como -> 01 - 09
                return getUnidades(num);
            } else if (n > 19) {//para 20...99
                String u = getUnidades(num);
                if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
                } else {
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + " " + u;
                }
            } else {//numeros entre 11 y 19
                return DECENAS[n - 10];
            }
        }

        private String getCentenas(String num) {// 999 o 099
            if (Integer.parseInt(num) > 99) {//es centena
                if (Integer.parseInt(num) == 100) {//caso especial
                    return " cien ";
                } else {
                    return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
                }
            } else {//por Ej. 099
                //se quita el 0 antes de convertir a decenas
                return getDecenas(Integer.parseInt(num) + "");
            }
        }

        private String getMiles(String numero) {// 999 999
            //obtiene las centenas
            String c = numero.substring(numero.length() - 3);
            //obtiene los miles
            String m = numero.substring(0, numero.length() - 3);
            String n = "";
            //se comprueba que miles tenga valor entero
            if (Integer.parseInt(m) > 0) {
                n = getCentenas(m);
                return n + "mil " + getCentenas(c);
            } else {
                return "" + getCentenas(c);
            }
        }

        private String getMillones(String numero) { //000 000 000
            //se obtiene los miles
            String miles = numero.substring(numero.length() - 6);
            //se obtiene los millones
            String millon = numero.substring(0, numero.length() - 6);
            String n = "";
            if (millon.length() > 1) {
                n = getCentenas(millon) + "millones ";
            } else {
                n = getUnidades(millon) + "millon ";
            }
            return n + getMiles(miles);
        }
    }
    private void ModificaCliente(boolean valor) {
        txtcliente.setEditable(valor);
        txtdireccion.setEditable(true);
        txtruc.setEditable(false);
        txtdni.setEditable(true);
        //btnbuscar.setEnabled(true);

        txtcliente.setEnabled(valor);
        txtdireccion.setEnabled(valor);
        String tipovtax = this.txttipoventa.getText();
        if (tipovtax.equals("BOLETA ELECTRONICA")){
            txtruc.setText("");
        }
        txtruc.setEnabled(false);
        txtdni.setEnabled(valor);
        jcomboObs.setEnabled(valor);

    }

    private void ModificaObs(boolean valor) {
        String tipovta1 = this.txttipoventa.getText();
        if (tipovta1.equals("FACTURA ELECTRONICA")){

        jTextArea1.setEditable(valor);
        txtcliente.setEditable(false);
        txtdireccion.setEditable(false);
        txtruc.setEditable(false);
        txtdni.setEditable(false);
        //btnbuscar.setEnabled(false);
        jcomboObs.setEditable(true);

        txtcliente.setEnabled(valor);
        txtdireccion.setEnabled(valor);
        txtruc.setEnabled(valor);
        txtdni.setEnabled(valor);
        jcomboObs.setEnabled(valor);

        }else{
        jTextArea1.setEditable(valor);
        txtcliente.setEditable(true);
        txtdireccion.setEditable(true);        
        txtruc.setEditable(true);
        txtdni.setEditable(true);
        jcomboObs.setEditable(true);

        txtcliente.setEnabled(valor);
        txtdireccion.setEnabled(valor);
        txtruc.setEnabled(valor);
        txtdni.setEnabled(valor);
        jcomboObs.setEnabled(valor);
        }

    }

    private void CerrarVentana() {
        int response = JOptionPane.showConfirmDialog(null, " Desea Cerrar Esta Ventana ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            objventas.marcacdo = false;
            objventas.Habilita(true);
            dispose();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablanotas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtmoney = new javax.swing.JTextField();
        jcomboObs = new javax.swing.JComboBox();
        txttpago = new javax.swing.JTextField();
        txtred = new javax.swing.JTextField();
        txtTImpre = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtnumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txttipoventa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TXTIGV = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton12 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(NotaCredito1.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(resourceMap.getFont("jTabbedPane1.font")); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablanotas.setFont(resourceMap.getFont("tablanotas.font")); // NOI18N
        tablanotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interno", "Fecha", "Cliente", "TipoVenta", "TipoPago", "SubTotal", "Total", "Serie", "Numero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        if (tablanotas.getColumnModel().getColumnCount() > 0) {
            tablanotas.getColumnModel().getColumn(0).setResizable(false);
            tablanotas.getColumnModel().getColumn(0).setPreferredWidth(55);
            tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
            tablanotas.getColumnModel().getColumn(1).setResizable(false);
            tablanotas.getColumnModel().getColumn(1).setPreferredWidth(75);
            tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
            tablanotas.getColumnModel().getColumn(2).setResizable(false);
            tablanotas.getColumnModel().getColumn(2).setPreferredWidth(300);
            tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
            tablanotas.getColumnModel().getColumn(3).setResizable(false);
            tablanotas.getColumnModel().getColumn(3).setPreferredWidth(100);
            tablanotas.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title3")); // NOI18N
            tablanotas.getColumnModel().getColumn(4).setResizable(false);
            tablanotas.getColumnModel().getColumn(4).setPreferredWidth(140);
            tablanotas.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title4")); // NOI18N
            tablanotas.getColumnModel().getColumn(5).setResizable(false);
            tablanotas.getColumnModel().getColumn(5).setPreferredWidth(85);
            tablanotas.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title5")); // NOI18N
            tablanotas.getColumnModel().getColumn(6).setResizable(false);
            tablanotas.getColumnModel().getColumn(6).setPreferredWidth(85);
            tablanotas.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title6")); // NOI18N
            tablanotas.getColumnModel().getColumn(7).setResizable(false);
            tablanotas.getColumnModel().getColumn(7).setPreferredWidth(50);
            tablanotas.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title7")); // NOI18N
            tablanotas.getColumnModel().getColumn(8).setResizable(false);
            tablanotas.getColumnModel().getColumn(8).setPreferredWidth(100);
            tablanotas.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title8")); // NOI18N
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel4.border.titleFont"), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);

        jLabel3.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtcodigo.setEditable(false);
        txtcodigo.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtcodigo.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtcodigo.setText(resourceMap.getString("txtcodigo.text")); // NOI18N
        txtcodigo.setEnabled(false);
        txtcodigo.setName("txtcodigo"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtruc.setEditable(false);
        txtruc.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtruc.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtruc.setText(resourceMap.getString("txtruc.text")); // NOI18N
        txtruc.setEnabled(false);
        txtruc.setName("txtruc"); // NOI18N
        txtruc.setNextFocusableComponent(txtdni);
        txtruc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtrucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrucFocusLost(evt);
            }
        });

        jLabel5.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtdireccion.setEditable(false);
        txtdireccion.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtdireccion.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtdireccion.setText(resourceMap.getString("txtdireccion.text")); // NOI18N
        txtdireccion.setEnabled(false);
        txtdireccion.setName("txtdireccion"); // NOI18N
        txtdireccion.setNextFocusableComponent(txtruc);
        txtdireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdireccionFocusLost(evt);
            }
        });

        txtcliente.setEditable(false);
        txtcliente.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtcliente.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtcliente.setText(resourceMap.getString("txtcliente.text")); // NOI18N
        txtcliente.setEnabled(false);
        txtcliente.setName("txtcliente"); // NOI18N
        txtcliente.setNextFocusableComponent(txtdireccion);
        txtcliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtclienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtclienteFocusLost(evt);
            }
        });

        txtdni.setEditable(false);
        txtdni.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtdni.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtdni.setText(resourceMap.getString("txtdni.text")); // NOI18N
        txtdni.setEnabled(false);
        txtdni.setName("txtdni"); // NOI18N
        txtdni.setNextFocusableComponent(btnbuscar);
        txtdni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdniFocusLost(evt);
            }
        });
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });

        jLabel15.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane3.setViewportView(jTextArea1);

        txtmoney.setEditable(false);
        txtmoney.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmoney.setName("txtmoney"); // NOI18N

        jcomboObs.setEnabled(false);
        jcomboObs.setName("jcomboObs"); // NOI18N
        jcomboObs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcomboObsItemStateChanged(evt);
            }
        });

        txttpago.setEditable(false);
        txttpago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttpago.setName("txttpago"); // NOI18N

        txtred.setEditable(false);
        txtred.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtred.setName("txtred"); // NOI18N

        txtTImpre.setEditable(false);
        txtTImpre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTImpre.setName("txtTImpre"); // NOI18N

        btnbuscar.setIcon(resourceMap.getIcon("btnbuscar.icon")); // NOI18N
        btnbuscar.setText(resourceMap.getString("btnbuscar.text")); // NOI18N
        btnbuscar.setEnabled(false);
        btnbuscar.setName("btnbuscar"); // NOI18N
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        btnbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnbuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnbuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jcomboObs, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttpago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtred, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTImpre, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcomboObs)
                            .addComponent(txttpago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtred, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTImpre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jButton8.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setMnemonic('N');
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setToolTipText(resourceMap.getString("jButton5.toolTipText")); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Producto", "Und", "Fracc", "PVP", "DSCTO", "PVPx", "Parcial", "lab", "Gratuita", "descuento", "idvtadet", "idrecarga", "idprod"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true, true, true, true
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(2);
            jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
            jTable1.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
            jTable1.getColumnModel().getColumn(10).setMinWidth(0);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
            jTable1.getColumnModel().getColumn(11).setMinWidth(0);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("jTable1.columnModel.title11")); // NOI18N
            jTable1.getColumnModel().getColumn(12).setMinWidth(0);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(12).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(12).setHeaderValue(resourceMap.getString("jTable1.columnModel.title12")); // NOI18N
            jTable1.getColumnModel().getColumn(13).setMinWidth(0);
            jTable1.getColumnModel().getColumn(13).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(13).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(13).setHeaderValue(resourceMap.getString("jTable1.columnModel.title13")); // NOI18N
        }

        txtnumero.setEditable(false);
        txtnumero.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtnumero.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtnumero.setText(resourceMap.getString("txtnumero.text")); // NOI18N
        txtnumero.setName("txtnumero"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txttipoventa.setEditable(false);
        txttipoventa.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txttipoventa.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txttipoventa.setText(resourceMap.getString("txttipoventa.text")); // NOI18N
        txttipoventa.setName("txttipoventa"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtserie.setEditable(false);
        txtserie.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtserie.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtserie.setText(resourceMap.getString("txtserie.text")); // NOI18N
        txtserie.setName("txtserie"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtfecha.setEditable(false);
        txtfecha.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtfecha.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtfecha.setText(resourceMap.getString("txtfecha.text")); // NOI18N
        txtfecha.setName("txtfecha"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtsubtotal.setEditable(false);
        txtsubtotal.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtsubtotal.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsubtotal.setText(resourceMap.getString("txtsubtotal.text")); // NOI18N
        txtsubtotal.setName("txtsubtotal"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        TXTIGV.setEditable(false);
        TXTIGV.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        TXTIGV.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        TXTIGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TXTIGV.setText(resourceMap.getString("TXTIGV.text")); // NOI18N
        TXTIGV.setName("TXTIGV"); // NOI18N

        txttotal.setEditable(false);
        txttotal.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txttotal.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotal.setText(resourceMap.getString("txttotal.text")); // NOI18N
        txttotal.setName("txttotal"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        txtvendedor.setEditable(false);
        txtvendedor.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtvendedor.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtvendedor.setText(resourceMap.getString("txtvendedor.text")); // NOI18N
        txtvendedor.setName("txtvendedor"); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setName("jTextField3"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField4.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setEnabled(false);
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setFont(resourceMap.getFont("jTextField5.font")); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setEnabled(false);
        jTextField5.setName("jTextField5"); // NOI18N

        jTextField6.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        jTextField6.setFont(resourceMap.getFont("jTextField6.font")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setEnabled(false);
        jTextField6.setName("jTextField6"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel17)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel19)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel20)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel21)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);

        jButton2.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setToolTipText(resourceMap.getString("jButton2.toolTipText")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setMnemonic('G');
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setToolTipText(resourceMap.getString("jButton4.toolTipText")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel38.setFont(resourceMap.getFont("jLabel38.font")); // NOI18N
        jLabel38.setForeground(resourceMap.getColor("jLabel38.foreground")); // NOI18N
        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        jTextField44.setEditable(false);
        jTextField44.setBackground(resourceMap.getColor("jTextField44.background")); // NOI18N
        jTextField44.setFont(resourceMap.getFont("jTextField44.font")); // NOI18N
        jTextField44.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField44.setName("jTextField44"); // NOI18N
        jTextField44.setPreferredSize(new java.awt.Dimension(95, 20));

        jLabel39.setFont(resourceMap.getFont("jLabel39.font")); // NOI18N
        jLabel39.setForeground(resourceMap.getColor("jLabel39.foreground")); // NOI18N
        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        jTextField45.setEditable(false);
        jTextField45.setBackground(resourceMap.getColor("jTextField45.background")); // NOI18N
        jTextField45.setFont(resourceMap.getFont("jTextField45.font")); // NOI18N
        jTextField45.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField45.setName("jTextField45"); // NOI18N
        jTextField45.setPreferredSize(new java.awt.Dimension(95, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttotal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtsubtotal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtserie, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnumero, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtfecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                .addComponent(TXTIGV, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jRadioButton1.setBackground(resourceMap.getColor("jRadioButton1.background")); // NOI18N
        jRadioButton1.setFont(resourceMap.getFont("jRadioButton1.font")); // NOI18N
        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton1);

        jRadioButton2.setBackground(resourceMap.getColor("jRadioButton2.background")); // NOI18N
        jRadioButton2.setFont(resourceMap.getFont("jRadioButton1.font")); // NOI18N
        jRadioButton2.setText(resourceMap.getString("jRadioButton2.text")); // NOI18N
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton2);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        jButton6.setBackground(resourceMap.getColor("jButton6.background")); // NOI18N
        jButton6.setFont(resourceMap.getFont("jButton6.font")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(52, 38));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jComboBox1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Cliente" }));
        jComboBox1.setFocusable(false);
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(73, 22));

        jTextField1.setFont(resourceMap.getFont("jComboBox1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setNextFocusableComponent(tablanotas);
        jTextField1.setPreferredSize(new java.awt.Dimension(10, 25));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton10.setBackground(resourceMap.getColor("jButton10.background")); // NOI18N
        jButton10.setFont(resourceMap.getFont("jButton10.font")); // NOI18N
        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(60, 23));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(423, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar1.add(jPanel1);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBar1.add(jSeparator6);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel6.border.titleFont"), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);

        jLabel23.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jXDatePicker1.setFont(resourceMap.getFont("jXDatePicker2.font")); // NOI18N
        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jXDatePicker2.setFont(resourceMap.getFont("jXDatePicker2.font")); // NOI18N
        jXDatePicker2.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker2.setName("jXDatePicker2"); // NOI18N

        jButton12.setFont(resourceMap.getFont("jButton12.font")); // NOI18N
        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel24)
                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton12))
        );

        jLabel25.setIcon(resourceMap.getIcon("jLabel25.icon")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel26.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel26.setForeground(resourceMap.getColor("jLabel26.foreground")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(16, 16, 16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LimpiatTabla_Productos();
        ltaVen_Productos.removeAll(ltaVen_Productos);
        Botones(true);
        jButton8.setEnabled(true);
        ModificaCliente(false);
        total = 0;
        subtottal = 0;
        this.jTextField2.setText("");
        this.jTextField3.setText("");
        this.jTextField4.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.txtTImpre.setText("");
        this.txttpago.setText("");
        this.txtred.setText("");
        this.txtcliente.setText("");
        this.txtdireccion.setText("");
        this.txtdni.setText("");
        this.txtruc.setText("");
        this.txtfecha.setText("");
        this.jTextArea1.setText("");
        this.txtmoney.setText("");
        this.jcomboObs.setSelectedIndex(0);

        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        Color p = new Color(236, 233, 216);
        HabilitaCampos(p);
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.jTable1.getRowCount() > 0) {
            double valida = Double.parseDouble(this.jTextField6.getText().trim());

            if (valida > 0 ){

            String tipovta = this.txttipoventa.getText();
            
            if (this.txtcliente.getText().length() > 0 && this.txtcliente.getText().trim().compareTo("CLIENTE COMUN") != 0) {

                if (tipovta.equals("FACTURA") || tipovta.equals("FACTURA ELECTRONICA")){

                    GuardarNotadeCredito();

                }else{

                    if (txtdni.getText().length() > 0) {

                        if (txtdni.getText().length() == 8) {
                            Color p = new Color(236, 233, 216);
                            HabilitaCampos(p);
                            GuardarNotadeCredito();
                        } else {
                            JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar el DNI Corecto DEL CLiente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                            ModificaCliente(true);
                            txtdni.requestFocus();
                        }
                    }else if (txtruc.getText().length() > 0) {

                        if (txtruc.getText().length() == 11 ) {
                            Color p = new Color(236, 233, 216);
                            HabilitaCampos(p);
                            GuardarNotadeCredito();
                        } else {
                            JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar RUC/DNI Corecto DEL CLiente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                            ModificaCliente(true);
                            txtruc.requestFocus();
                        }
                   
                    }else if (txtdni.getText().length() <= 0) {
                        JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar el DNI Corecto DEL CLiente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                        txtdni.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar el Nombre del Cliente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                ModificaCliente(true);
                txtcliente.requestFocus();
            }

            } else {
            JOptionPane.showMessageDialog(this, "Lo Sentimos No se puede hacer la Nota de Credito de monto negativo", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lo Sentimos No hay Productos para la Nota de Credito", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (jTextField1.getText().length() % 2 == 0 || jTextField1.getText().length() > 2) {
            if (this.jRadioButton2.isSelected()) {
                BuscaIntervaloFechas(0);
            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablanotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablanotasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SeleccionarVenta();
        }

    }//GEN-LAST:event_tablanotasKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        objPrdocuto = new Productos_De_Venta(this, idbotica, idventa);
        objPrdocuto.show(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Elimina_Producto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == 127) {
            Elimina_Producto();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.txtcliente.requestFocus();
        Color p = new Color(255, 255, 255);
        this.btnbuscar.setEnabled(true);
        HabilitaCampos(p);
        ModificaCliente(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void HabilitaCampos(Color c) {
        this.jTextArea1.setBackground(c);
        this.txtcliente.setBackground(c);
        this.txtcodigo.setBackground(c);
        this.txtdireccion.setBackground(c);
        this.txtdni.setBackground(c);
        this.txtruc.setBackground(c);
        this.jcomboObs.setBackground(c);


    }

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
    }//GEN-LAST:event_jTextField1KeyPressed

    private void tablanotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanotasMouseClicked
        if (evt.getClickCount() % 2 == 0) {
            SeleccionarVenta();
        }

    }//GEN-LAST:event_tablanotasMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        this.jRadioButton2.setSelected(false);
        this.jPanel6.setVisible(false);
        CargarNotasCreditos();
}//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        this.jPanel6.setVisible(true);
        this.jRadioButton1.setSelected(false);
}//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jLabel25.setVisible(true);
        jLabel26.setVisible(true);
        BuscaIntervaloFechas(0);
}//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String filtro = null;
        filtro = this.jTextField1.getText().trim();
        if (this.jRadioButton1.isSelected()) {
            BuscarNotaCredito(filtro);
        } else if (this.jRadioButton2.isSelected()) {
            BuscaIntervaloFechas(1);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtclienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusGained
        txtcliente.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_txtclienteFocusGained

    private void txtdireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdireccionFocusGained
        txtdireccion.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_txtdireccionFocusGained

    private void txtrucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusGained
        txtruc.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_txtrucFocusGained

    private void txtdniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusGained
        txtdni.setBackground(new Color(255, 255, 102));
    }//GEN-LAST:event_txtdniFocusGained

    private void txtclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclienteFocusLost
        txtcliente.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtclienteFocusLost

    private void txtdireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdireccionFocusLost
        txtdireccion.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtdireccionFocusLost

    private void txtrucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusLost
        txtruc.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtrucFocusLost

    private void txtdniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdniFocusLost
        txtdni.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_txtdniFocusLost

    private void jcomboObsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcomboObsItemStateChanged
        if (evt.getStateChange() == 2) {
            //LimpiatTabla();
            //jcomboObs.removeAllItems();
            
        }
}//GEN-LAST:event_jcomboObsItemStateChanged

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyReleased
        // TODO add your handling code here:
        //verdni();
    }//GEN-LAST:event_btnbuscarKeyReleased

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_txtdniActionPerformed

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed
        // TODO add your handling code here:
        verdni();
    }//GEN-LAST:event_btnbuscarKeyPressed

    private void BuscaIntervaloFechas(int op) {
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


            if (op == 0) {
                EjecutatHilo obje = new EjecutatHilo(1);
                obje.start();
            } else {
                listaInternos = logventa.Lista_Int_Ventas_Fecha(obj);
                LimpiatTabla();

                for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getId_Venta();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                    datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                    datosDetalle[3] = listaInternos.get(i).getTipventa();
                    datosDetalle[4] = listaInternos.get(i).getTipopago();
                    datosDetalle[5] = listaInternos.get(i).getSubTotal();
                    datosDetalle[6] = listaInternos.get(i).getTotal();
                    datosDetalle[7] = listaInternos.get(i).getSerie();
                    datosDetalle[8] = listaInternos.get(i).getNumero();
                    model_notas.addRow(datosDetalle);

                }

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
                    listaInternos = logventa.Lista_Int_Ventas_Fecha(obj);
                }

                if (listaInternos.size() > 0) {
                    LimpiatTabla();
                }


                for (int i = 0; i < listaInternos.size(); i++) {
                    datosDetalle[0] = listaInternos.get(i).getId_Venta();
                    datosDetalle[1] = listaInternos.get(i).getFecha_Venta();
                    datosDetalle[2] = listaInternos.get(i).getNombre_RazonSocial();
                    datosDetalle[3] = listaInternos.get(i).getTipventa();
                    datosDetalle[4] = listaInternos.get(i).getTipopago();
                    datosDetalle[5] = listaInternos.get(i).getSubTotal();
                    datosDetalle[6] = listaInternos.get(i).getTotal();
                    datosDetalle[7] = listaInternos.get(i).getSerie();
                    datosDetalle[8] = listaInternos.get(i).getNumero();

                    model_notas.addRow(datosDetalle);
                }

            } catch (Exception ex) {
                System.out.println("ERROR CAPA VISTA METODO CargarVentas " + ex.getMessage());
            }


            jLabel25.setVisible(false);
            jLabel26.setVisible(false);
        }
    }
    
    private void verdni(){
        try {
            String RUCDNICLIENTE = this.txtdni.getText().trim().toUpperCase();
            char[] campo = RUCDNICLIENTE.toCharArray();
            int tamano = campo.length;
            String dni = txtdni.getText(); 
            ClientesData cli = new ClientesData();   
            Clientes clientes = new Clientes();            
            clientes = cli.vercliente(dni);             
            if(clientes.Nombre_RazonSocial==null)
            {                
                String data =datosDni(dni);              
                    txtcliente.setText(data);
                    String verificardatos = this.txtcliente.getText().trim().toUpperCase();
                    char[] campo2 = verificardatos.toCharArray();
                    int tamano2 = campo2.length;
                    if(tamano==8)
                    {
                        if(tamano2!=0)
                        {
                            guardacliente();
                        }else
                        {
                            JOptionPane.showMessageDialog(rootPane, "DNI no existe");
                            txtcliente.setText("CLIENTE COMUN");
                            txtdni.setText("");
                            txtdni.requestFocus();                            
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                        txtcliente.setText("CLIENTE COMUN");
                        txtdni.setText("");
                        txtdni.requestFocus();
                    }
            }
            else   
            { 
                if(tamano== 8){
                txtcliente.setText(clientes.Nombre_RazonSocial);
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Ingrese un DNI válido");
                    txtdni.requestFocus();
                }
            }             
        } catch (Exception ex) {
            System.out.println("Error al buscar :" + ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Error en la búsqueda, ingresar datos manualmente");
        }
    }
    //Guardar cliente si no existe al buscar
    private void guardacliente() {
        String idbotica=id_botica;
        int idempresa=0;
        String ruccliente="";
        String dni = txtdni.getText();
        String nomcliente=txtcliente.getText();
        String direecion="";
        String telefono="";
        String email="";        
        nuevocliente = new Clientes(ruccliente, dni, nomcliente, direecion, telefono, idempresa, email);
            int resultado = logcliente.GuardarCliente(nuevocliente, idbotica);        
    }    
    
    private static String datosDni(java.lang.String dni) throws IOException_Exception {
        service.Verips_Service service = new service.Verips_Service();
        service.Verips port = service.getVeripsPort();
        return port.datosDni(dni);
    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTIGV;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JComboBox jcomboObs;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtTImpre;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtmoney;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtred;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttipoventa;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttpago;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
