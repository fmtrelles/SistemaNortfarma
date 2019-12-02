package sistemanortfarma;

import CapaDatos.ConexionPool;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Venta;
import java.awt.Color;
import java.awt.event.KeyEvent;
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
import java.io.PrintWriter;
import CapaLogica.LogicaProducto;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
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



/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class NotaDebito extends javax.swing.JInternalFrame {

    Connection conex;
    private ConexionPool db;
    private double MIIGV;
    private DefaultTableModel model_notas;
    LogicaProducto objProducto = new LogicaProducto();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    Mant_Productos mantProduc = new Mant_Productos();
    TableColumnModel colModel;
    private DefaultTableModel model;
    TableColumnModel colModel1;
    Venta obj;
    ResultSet rs;
    List<Venta> listaInternos = new ArrayList<Venta>();
    LogicaVenta logventa = new LogicaVenta();
    private String idventa = "";
    int podecimal = 4;
    int podecimalPantalla = 2;
    OpcionesMenu obj1;
    private String Impresora_Factura = obj1.getImpresora_Factura();
    private String idbotica = null;
    Object[] datosDetalle = new Object[9];
    MuestraVentana objetoventana = new MuestraVentana();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    Productos_De_Venta_ND objPrdocuto = null;
    double total, subtottal,subtottalCabecera,subtottalDetalle,subtottalPantalla;
    double OpGravada = 0.0, OpExonerada=0.0, OpInafecta=0.0,OpGratuita=0.0,OpISC=0.0,miigv=0.0,OpBonificacion=0.0,OpDescuentoTotal = 0.0, OpDescuento = 0.0, preciounitario=0.0,
           OpGravadaDetalle = 0.0,OpGravadaCabecera=0.0,OpInafectaCabecera=0.0 ;
    double IGV;
    List<Venta> ltaVen_Productos = new ArrayList<Venta>();
    List<Venta> listaVenta_Detalle = new ArrayList<Venta>();
    private String serie = null;
    private String numero = null;
    Date fecha;
    int id_peronal;
    AplicacionVentas objventas;

    @SuppressWarnings("static-access")
    public NotaDebito(AplicacionVentas obj) {
        initComponents();
        objventas = obj;
        model_notas = (DefaultTableModel) this.tablanotas.getModel();
        colModel = this.tablanotas.getColumnModel();
        model = (DefaultTableModel) this.jTable1.getModel();
        colModel1 = this.jTable1.getColumnModel();
        idbotica = objventas.getIdbotica();
        fecha = objventas.getFecha();
        id_peronal = objventas.getIdpesonal();
        setTitle("Generar Nota de Debito                                           Usuario:   " + objventas.getUsuario());
        AparienciaTabla();
        AparienciaTabla1();
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        this.txtdni.setDocument(new LimitadorLetras(txtdni, 8));
        this.txtruc.setDocument(new LimitadorLetras(txtruc, 11));
        this.jTextField1.requestFocus();
        jTabbedPane1.setEnabledAt(1, false);
        this.jPanel6.setVisible(false);
        this.jRadioButton1.setSelected(true);
        this.jTextField1.setSize(20, 50);
        CargarNotasCreditos();
        this.txtmoney.setVisible(false);
    }

    public NotaDebito() {
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

    private void CargarNotasCreditos() {
        idventa = this.jTextField1.getText().trim() + '%';
        obj = new Venta(idbotica, idventa);
        listaInternos = logventa.Lista_Internos_Ventas(obj, 0);


        if (listaInternos.size() > 0) {
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

                String tvta = listaInternos.get(fila).getTipventa();

                if (tvta.equals("FACTURA")){
                   this.jButton9.setEnabled(false);
                }else{
                   this.jButton9.setEnabled(true);
                }

            }

        }

    }

    private void CalculaMontos() {

        try {

            double parciali;
            total = 0;
            subtottal = 0;
            subtottalPantalla = 0;
            subtottalDetalle = 0;
            double auxparcial;
            double auxparcialDetalle;

            OpInafectaCabecera = 0.0;
            OpGravada = 0.0;
            OpDescuentoTotal = 0.0;

            for (int i = 0; i < this.jTable1.getRowCount(); i++) {
                parciali = Double.parseDouble(this.jTable1.getValueAt(i, 7).toString());
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
            BigDecimal bd = new BigDecimal(total);
            bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            total = bd.doubleValue();

            BigDecimal bd1 = new BigDecimal(subtottalPantalla);
            bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
            subtottal = bd1.doubleValue();
            
            this.jTextField4.setText(bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());
            this.jTextField2.setText(bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());

            double igv = total - subtottal;
            BigDecimal bd3 = new BigDecimal(igv);

            this.jTextField3.setText(bd3.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString());


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void AsignaProductos(List<Venta> listaVenta) {
        int lengthproducto = 0;
        int cant = 0;
        ltaVen_Productos = listaVenta;
        double parcial, pvpx, totalND;


        for (int i = 0; i < listaVenta.size(); i++) {
            pvpx = ltaVen_Productos.get(i).getPrecio_Venta_Final();
            parcial = ltaVen_Productos.get(i).getTotal_producto();
            totalND = ltaVen_Productos.get(i).gettotalND();

            BigDecimal bd = new BigDecimal(pvpx);
            bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
            total = bd.doubleValue();

            BigDecimal bd1 = new BigDecimal(parcial);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            total = bd1.doubleValue();

            BigDecimal bd2 = new BigDecimal(totalND);
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            totalND = bd2.doubleValue();

            datosDetalle[0] = i + 1;
            datosDetalle[1] = ltaVen_Productos.get(i).getDescr_Producto();
            datosDetalle[2] = ltaVen_Productos.get(i).getUnidad();
            datosDetalle[3] = ltaVen_Productos.get(i).getFraccion();
            //datosDetalle[4] = ltaVen_Productos.get(i).getPrecio_Venta();
            datosDetalle[4] = ltaVen_Productos.get(i).gettotalND();
            datosDetalle[5] = ltaVen_Productos.get(i).getDescuento();
            //datosDetalle[6] = bd.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
            //datosDetalle[7] = bd1.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
            datosDetalle[6] = bd2.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
            datosDetalle[7] = bd2.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();


            lengthproducto = ltaVen_Productos.get(i).getDescr_Producto().length();

            if (lengthproducto > cant) {
                cant = lengthproducto;
            }

            model.addRow(datosDetalle);

        }
        Columnaresize(cant);
        CalculaMontos();
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
                CalculaMontos();
                this.jTable1.requestFocus();
                jTable1.getSelectionModel().setSelectionInterval(0, 0);
            }
        }

    }

    private void GuardarNotaDebito() {

        if (this.jTextArea1.getText().length() > 0) {
                Color p = new Color(236, 233, 216);
                HabilitaCampos(p);

        NODCRSerieNumero mota = new NODCRSerieNumero(objetoventana,idventa,txtserie.getText(),txtnumero.getText(),txtfecha.getText(),"ND");
        mota.pack();
        mota.show(true);      
        

        Venta objventa;
        serie = mota.getSerie();
        numero = mota.getNumero();
        LogicaVenta objlogVenta = new LogicaVenta();
        String idproducto;
        double OpDescuentoCabecera = 0.0;
        double OpDescuentoDetalle = 0.0;
        double auxigv = 0.0;
        double auxigvDetalle = 0.0;
        String esImprimible = "0";
        String lab = "";
        double AUXIGV1 = 0.0;
        int empaque;
        int undad, fraccion;
        double parcial,pvp,dcto,pvpx;
        String MonedaIdvta;
        listaVenta_Detalle.removeAll(listaVenta_Detalle);

        if (serie != null && numero != null) {

            //double subtotal = Double.parseDouble(this.jTextField2.getText().trim());
            double subtotal = subtottal;
            double auxparcial=0.0;
            double auxparcialCabecera=0.0;
            double redon = 0.0;
            double tfinal = 0.0;
            String timpre = "0";

            
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
                Venta enti = objProducto.Recupera_Descuento_Producto(idproducto, "", "01",String.valueOf(pvpx));
                auxigv = enti.getIGV();
                auxigvDetalle = enti.getIGV();
                empaque = ltaVen_Productos.get(i).getEmpaque();
                
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

                    auxparcialCabecera = (parcial / (1 + (IGV / 100)));
                    subtottalCabecera = auxparcialCabecera;
                    OpGravadaDetalle = subtottalCabecera;
                }

                if (pvpx < 0){

                    OpDescuentoCabecera = OpDescuentoCabecera + pvpx;
                    OpDescuentoDetalle = pvpx;
                }

                
                listaVenta_Detalle.add(new Venta(idbotica, idproducto, parcial, undad, fraccion, pvp,dcto,pvpx,id_peronal,auxigvDetalle,sutot,OpInafecta,OpGravada,OpGravadaDetalle,OpDescuentoDetalle,lab,empaque,0,"0",0,1,0,"",0,0));

                
            }

                MonedaIdvta = this.txtmoney.getText().toString();

                boolean resultado = objlogVenta.InsertaNotaCredito(objventa, listaVenta_Detalle,AUXIGV1,OpInafectaCabecera,OpDescuentoCabecera,0,MonedaIdvta,"ND","0");

                esImprimible = mantProduc.Recupera_Imprimible(idventa,idbotica).toString();

                if (esImprimible.equals("1")){
                    GeneraImpresion(objventa,listaVenta_Detalle);
                }

                if (resultado) {
                    JOptionPane.showMessageDialog(this, "SU NOTA DE DEBITO FUE GENERADA CORRECTAMENTE ", "Nota de Debito", JOptionPane.INFORMATION_MESSAGE);
                    objventas.marcacdo = false;
                    objventas.Habilita(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "LO SENTIMOS HUBO UN ERROR AL GENERAR SU NOTA DE DEBITO \n PORFAVOR COMUNIQUESE CON INFORMATICA ", "Nota de Debito", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }

            
        }

        } else {
                JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar una Observacón", "Nota de Debito", JOptionPane.ERROR_MESSAGE);
                ModificaObs(true);
                jTextArea1.requestFocus();
        }
    }

    private void GeneraImpresion(Venta obj,List<Venta> listaVtasDetalle) {
        
        List<String> ProductoDescripcion = new ArrayList<String>();
        List<String> Labs = new ArrayList<String>();
        List<Integer> CantidadU = new ArrayList<Integer>();
        List<Double> vtadettotal = new ArrayList<Double>();
        List<Integer> CantidadF = new ArrayList<Integer>();
        List<Double> Precio = new ArrayList<Double>();
        List<Double> PrecioF = new ArrayList<Double>();
        Double IGV = 0.00, Totalizado = 0.00, sumaTotal = 0.00, SubSub = 0.00;
        Double Total = 0.00,opgravada=0.0,opinafecta=0.0,opigv=0.0,opdescuento=0.0;
        String cliente = "", Np = "", salesman = "", direccion = "", RUC = "", Direccion = null, PLACA = "", DNICLI="",
                botica = "",DireccionFiscal="",serie = null, numero = "",Delivery="",correo="";
        String vendedor = null, cajero = null;
        Integer Largo = 0;
        int lineas = 0;
        String fecha = null;
        Integer Espacios = 7;
        DecimalFormat MiFormato = new DecimalFormat("0.00");
        int confirma = 0;
        FileOutputStream os = null;
        PrintStream ps = null;
        CallableStatement procedure = null;

        FileWriter file;
        BufferedWriter buffer;
        PrintWriter ps1;

        try{

            //if (i > 0) {
              //          JOptionPane.showMessageDialog(ventana, " " + (i + 1) + " Interno de Venta : " + miinterno + " ", "Impresion de Internos de Ventas", JOptionPane.INFORMATION_MESSAGE);
                        confirma = 1;
                //    } else {
                //        confirma = 1;
                //  }

                    if (confirma == 1) {

                        /*os = new FileOutputStream(Impresora_Factura);
                        //   FileOutputStream os = new FileOutputStream(impresora);
                        ps = new PrintStream(os);
                        ps.flush();*/
                        file = new FileWriter(Impresora_Factura);
                        buffer = new BufferedWriter(file);
                        ps1 = new PrintWriter(buffer);
                        String miinterno = obj.getId_Venta().toString().trim();
                        sumaTotal = 0.00;

                        //if (conex.isClosed()) {
                            conex = new ConexionPool().getConnection();
                        //}

                        procedure = conex.prepareCall("{ call RECUPERA_DATA_PARA_NC (?,?) }");
                        procedure.setString(1, miinterno);
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
                            opgravada = rs.getDouble("OP_GRAVADA");
                            opinafecta = rs.getDouble("Op_Inafecta");
                            opigv = rs.getDouble("IGV");
                            opdescuento = rs.getDouble("Op_Descuento");
                            Delivery = rs.getString("Delivery");
                            correo = rs.getString("CorreoEmpresa");
                            vtadettotal.add(rs.getDouble("ventadetalletotal"));
                        }

                        BigDecimal bdopgravada = new BigDecimal(opgravada);
                        BigDecimal bdopinafecta = new BigDecimal(opinafecta);
                        BigDecimal bdigv = new BigDecimal(opigv);
                        BigDecimal bdopdescuento = new BigDecimal(opdescuento);

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

                        /*for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }

                        if (Direccion != null) {
                            ps1.print("           " + Direccion);
                        }

                        for (Integer EspaciosArriba = 0; EspaciosArriba < 2; EspaciosArriba++) {
                            ps1.println("");
                        }*

                        /*ps.println("                                        Ven:  " + salesman + "   " + objFechaHora.RetornaHora() + "");
                        ps.println("");
                        ps.println("         " + cliente + "");
                        ps.println("           " + direccion);
                        ps.println("           " + RUC + "                  " + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "              " + miinterno);
                        ps.println("");
                        ps.println("");
                         *
                         */
                        setFormato(1, ps1);
                        ps1.println(botica);
                        ps1.println(Direccion);
                        ps1.println(DireccionFiscal);
                        ps1.println("RUC :" + RUC);
                        Dibuja_Linea(ps1);
                        ps1.println("     NOTA DE DEBITO ELECTRÓNICA ");
                        ps1.println("         " + obj.getSerie() + " - " + obj.getNumero());
                        Dibuja_Linea(ps1);
                        //ps.println("S/N       :" + maq);
                        ps1.println("Fecha :" + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "  Hora : " + objFechaHora.RetornaHora());
                        ps1.println("Caj   : " + cajero + " Ven : " + salesman + " Int : " + miinterno);
                        /*if (PLACA != null) {
                            ps1.println("Placa   : " + PLACA);
                        }*/
                        Dibuja_Linea(ps1);
                        ps1.println("Sr(a)     :" + cliente);
                        ps1.println("DIRECCION :" + direccion);
                        ps1.println("RUC       :" + RUC);
                        Dibuja_Linea(ps1);
                        ps1.println("Motivo de Emision");
                        ps1.println("Documento que Modifica :" + this.txttipoventa.getText());
                        ps1.println("Serie/Nro :" + this.txtserie.getText() + "-" + this.txtnumero.getText());
                        ps1.println("Fecha :" + this.txtfecha.getText());
                        Dibuja_Linea(ps1);
                        /*
                        ps.println("                                        Ven:  " + salesman + "   " + objFechaHora.RetornaHora() + "");
                        ps.println("");
                        ps.println("         " + cliente + "");
                        ps.println("           " + direccion);
                        ps.println("           " + RUC + "                  " + objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()) + "              " + miinterno);
                        ps.println("");
                        ps.println("");
                         *
                         */
                        ps1.println("Cant     " + "Descripcion" + "                " + "PVP");
                        Dibuja_Linea(ps1);
                        lineas = 7;

                        String Fusionado = "";

                        /*
                    --------  OBTENER EL ANCHO MAS GRANDE LA CANTIDAD
                     */

                    //int maximo = Obtener_Ancho(Cantidad);
                    //maximo++;

                   // for (int k = 0; k < ProductoDescripcion.size(); k++) {
                    //   String cantidad = "";


                        for (Integer sll = 0; sll < ProductoDescripcion.size(); sll++) { //DETALLE FACTURA
                            Fusionado = "";

                            if (CantidadF.get(sll) > 0) {
                                Fusionado = "F" + CantidadF.get(sll).toString();
                            }

                            if (CantidadU.get(sll) > 0) {
                                Fusionado = CantidadU.get(sll).toString() + Fusionado;
                            }

                            String sss = ProductoDescripcion.get(sll).toString();

                            if (Fusionado.length() < 5) {
                                for (Integer xx = Fusionado.length(); xx < 3; xx++) {
                                    Fusionado = " " + Fusionado;
                                }
                            }

                            if (sss.length() < 40) {
                                for (Integer xx = sss.length(); xx < 30; xx++) {
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

                            String Lavtadettotal = MiFormato.format(vtadettotal.get(sll));

                            if (Lavtadettotal.length() < 15) {
                                for (Integer xx = Lavtadettotal.length(); xx < 15; xx++) {
                                    Lavtadettotal = " " + Lavtadettotal;
                                }
                            }

                            //ps1.println(Fusionado + "  " + sss + " " + Labs.get(sll) + elPrecio.replace(",", ".") + elPrecioF.replace(",", "."));
                            ps1.println(Fusionado + "  " + sss + " " + Lavtadettotal);

                        }

                        /*for (Integer esp = Labs.size(); esp < 15; esp++) {
                            ps1.println("");
                        }*/

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


                        /*for (Integer paraIGV = elTotalizado.length(); paraIGV < 92; paraIGV++) {
                            elIGV = " " + elIGV;
                        }

                        for (Integer lo = elTotalizado.length(); lo < 94; lo++) {
                            elTotalizado = " " + elTotalizado;
                        }*/

                        //ps1.println(espacioFooterIGV + "    " + elIGV);
                        //ps1.println(espacioFooterIGV + "    " + elTotalizado);
                        //ps1.println(NumLetra.Convertir(numero1, true));
                        Dibuja_Linea(ps1);
                        ps1.println("OP GRAVADA    : S./ " + opgrav);
                        ps1.println("OP INAFECTA   : S./ " + opinaf);
                        ps1.println("IGV           : S./ " + igvVta);
                        ps1.println("TOTAL         : S./ " + elTotalizado);
                        ps1.println();
                        if ((opdescuento * -1) > 0){
                        ps1.println("TOTAL DSCTO   : S./ " + opdcto);
                        }
                        ps1.println();
                        String ultimo = "              " + Delivery;
                        ultimo += "                   " + correo;
                        ps1.println(ultimo);
                        ps1.println(NumLetra.Convertir(numero1, true));
                        ps1.println(" ");
                        ps1.println("  NO SE ACEPTAN CAMBIOS NI DEVOLUCIONES");
                        ps1.println("        GRACIAS POR SU COMPRA          ");
                        correr(10, ps1);
                        cortar(ps1);
                        //ps1.close();
                        //rs.close();
                        rs.close();
                        //rs2.close();
                        //listaInternos.set(i, null);

                        ProductoDescripcion.removeAll(ProductoDescripcion);
                        Labs.removeAll(Labs);
                        PrecioF.removeAll(PrecioF);
                        Precio.removeAll(Precio);
                        CantidadU.removeAll(CantidadU);
                        CantidadF.removeAll(CantidadF);

                        for (Integer EspaciosArriba = 0; EspaciosArriba < 3; EspaciosArriba++) {
                            ps1.println("");
                        }

                        ps1.close();
                        //listaInternos.set(i, null);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error Impresora FileNotFoundException catch" + ex.getMessage());
            Error_Impresora("Error Impresora FileNotFoundException catch" + ex.getMessage(), listaInternos);
        } catch (IOException et) {
            System.out.println("Error Impresora IOException catch" + et.getMessage());
            Error_Impresora("Error Impresora IOException catch" + et.getMessage(), listaInternos);
        } catch (Exception pe) {
            System.out.println("Error Impresora Exception  catch" + pe.getMessage());
            Error_Impresora("Error Impresora Exception  catch :" + pe.getMessage(), listaInternos);
        }

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
            ps.println("----------------------------------------");
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
            "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
            "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
        private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

        public Numero_a_Letra() {
        }

        public String Convertir(String numero, boolean mayusculas) {

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
                parte_decimal = " y " + Num[1] + "/100 Soles.";
                //se convierte el numero a literal
                if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                    literal = "Cero ";
                } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                    literal = getMillones(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                    literal = getMiles(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                    literal = getCentenas(Num[0]);
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
        txtruc.setEditable(true);
        txtdni.setEditable(true);

        txtcliente.setEnabled(valor);
        txtdireccion.setEnabled(valor);
        txtruc.setEnabled(valor);
        txtdni.setEnabled(valor);

    }

    private void ModificaObs(boolean valor) {
        this.jTextArea1.setEditable(valor);
        txtcliente.setEditable(true);
        txtdireccion.setEditable(true);        
        txtruc.setEditable(true);
        txtdni.setEditable(true);

        txtcliente.setEnabled(valor);
        txtdireccion.setEnabled(valor);
        txtruc.setEnabled(valor);
        txtdni.setEnabled(valor);

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
        jLabel17 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        jButton12 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(NotaDebito.class);
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
        tablanotas.getColumnModel().getColumn(0).setResizable(false);
        tablanotas.getColumnModel().getColumn(0).setPreferredWidth(55);
        tablanotas.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title0")); // NOI18N
        tablanotas.getColumnModel().getColumn(1).setResizable(false);
        tablanotas.getColumnModel().getColumn(1).setPreferredWidth(75);
        tablanotas.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title1")); // NOI18N
        tablanotas.getColumnModel().getColumn(2).setResizable(false);
        tablanotas.getColumnModel().getColumn(2).setPreferredWidth(320);
        tablanotas.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title2")); // NOI18N
        tablanotas.getColumnModel().getColumn(3).setResizable(false);
        tablanotas.getColumnModel().getColumn(3).setPreferredWidth(90);
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
        tablanotas.getColumnModel().getColumn(8).setPreferredWidth(110);
        tablanotas.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("tablanotas.columnModel.title8")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addGap(30, 30, 30))
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

        txtcodigo.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtcodigo.setEditable(false);
        txtcodigo.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtcodigo.setText(resourceMap.getString("txtcodigo.text")); // NOI18N
        txtcodigo.setEnabled(false);
        txtcodigo.setName("txtcodigo"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtruc.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtruc.setEditable(false);
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

        txtdireccion.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtdireccion.setEditable(false);
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

        txtcliente.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtcliente.setEditable(false);
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

        txtdni.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtdni.setEditable(false);
        txtdni.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtdni.setText(resourceMap.getString("txtdni.text")); // NOI18N
        txtdni.setEnabled(false);
        txtdni.setName("txtdni"); // NOI18N
        txtdni.setNextFocusableComponent(jTextArea1);
        txtdni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdniFocusLost(evt);
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
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(txtcliente, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(txtmoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                "Nº", "Producto", "Und", "Fracc", "PVP", "DSCTO", "PVPx", "Parcial"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        txtnumero.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtnumero.setEditable(false);
        txtnumero.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtnumero.setText(resourceMap.getString("txtnumero.text")); // NOI18N
        txtnumero.setName("txtnumero"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        txttipoventa.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txttipoventa.setEditable(false);
        txttipoventa.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txttipoventa.setText(resourceMap.getString("txttipoventa.text")); // NOI18N
        txttipoventa.setName("txttipoventa"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        txtserie.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtserie.setEditable(false);
        txtserie.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtserie.setText(resourceMap.getString("txtserie.text")); // NOI18N
        txtserie.setName("txtserie"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        txtfecha.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtfecha.setEditable(false);
        txtfecha.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtfecha.setText(resourceMap.getString("txtfecha.text")); // NOI18N
        txtfecha.setName("txtfecha"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtsubtotal.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtsubtotal.setEditable(false);
        txtsubtotal.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsubtotal.setText(resourceMap.getString("txtsubtotal.text")); // NOI18N
        txtsubtotal.setName("txtsubtotal"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        TXTIGV.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        TXTIGV.setEditable(false);
        TXTIGV.setFont(resourceMap.getFont("txttipoventa.font")); // NOI18N
        TXTIGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TXTIGV.setText(resourceMap.getString("TXTIGV.text")); // NOI18N
        TXTIGV.setName("TXTIGV"); // NOI18N

        txttotal.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txttotal.setEditable(false);
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

        txtvendedor.setBackground(resourceMap.getColor("txtruc.background")); // NOI18N
        txtvendedor.setEditable(false);
        txtvendedor.setFont(resourceMap.getFont("txtcodigo.font")); // NOI18N
        txtvendedor.setText(resourceMap.getString("txtvendedor.text")); // NOI18N
        txtvendedor.setName("txtvendedor"); // NOI18N

        jPanel5.setBackground(resourceMap.getColor("jPanel5.background")); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addGap(8, 8, 8)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel17)
                .addComponent(jLabel19)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttipoventa, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txttotal, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtsubtotal, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtserie, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtnumero, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtfecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                        .addComponent(TXTIGV, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(388, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addContainerGap(233, Short.MAX_VALUE))
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
                .addGap(163, 163, 163)
                .addComponent(jLabel24)
                .addGap(161, 161, 161)
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(jLabel24)
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
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE))
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
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
        this.txtcliente.setText("");
        this.txtdireccion.setText("");
        this.txtdni.setText("");
        this.txtruc.setText("");
        this.txtfecha.setText("");
        this.jTextArea1.setText("");
        this.txtmoney.setText("");

        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setSelectedIndex(0);
        Color p = new Color(236, 233, 216);
        HabilitaCampos(p);
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.jTable1.getRowCount() > 0) {
            String tipovta = this.txttipoventa.getText();
            
            if (this.txtcliente.getText().length() > 0 && this.txtcliente.getText().trim().compareTo("CLIENTE COMUN") != 0) {

                if (tipovta.equals("FACTURA")){

                    GuardarNotaDebito();

                }else{

                    if (this.txtdni.getText().length() > 0) {
                        Color p = new Color(236, 233, 216);
                        HabilitaCampos(p);
                        GuardarNotaDebito();
                    } else {                        
                        JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar el DNI DEL CLiente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                        ModificaCliente(true);
                        txtdni.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "POrfavor Debe de Ingresar el Nombre del Cliente", "Nota de Credito", JOptionPane.ERROR_MESSAGE);
                ModificaCliente(true);
                txtcliente.requestFocus();
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
        double totalvta = Double.valueOf(this.txttotal.getText());
        objPrdocuto = new Productos_De_Venta_ND(this, idbotica, idventa,totalvta);
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

    private void BuscaIntervaloFechas(int op) {
        Date fecha1 = null;
        Date fecha2 = null;

        String fechaini;
        String fechafin;

//        fecha1 = (Date) this.jXDatePicker1.getDate();
//        fecha2 = (Date) this.jXDatePicker2.getDate();

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

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTIGV;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tablanotas;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtmoney;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttipoventa;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
}
