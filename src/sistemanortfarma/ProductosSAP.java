/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DescargosInventarios1.java
 *
 * Created on 30/01/2012, 05:03:00 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaGuias;
import CapaLogica.LogicaMovimientos;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaProveedor;
import CapaLogica.LogicaTipoMovimiento;
import entidad.Boticas;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import entidad.Producto;
import entidad.Productos_Botica;
import entidad.Proforma;
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Miguel Gomez S.
 */
public class ProductosSAP extends javax.swing.JInternalFrame {

    ModeloTabla modeloTabla;
    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento = new ArrayList<TipoMovimiento>();
    List<TipoMovimiento> listaTipoMovimiento1 = new ArrayList<TipoMovimiento>();
    List<Proveedor> listaProveedor;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Varios> Stocks = new ArrayList<Varios>();
    Guias entidadGuias = new Guias();
    Producto entidadProductos = new Producto();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    Integer existeGuia = 0;
    private double IGV = 0, SubTotal = 0.0, total = 0.0;
    Proforma entidadproformaCliente;
    Boolean valido = false;
    AgregaProductosSAP objrelew;
    AgregaProductosSAP objrelew1;
    public static String ProductoRecibido;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    boolean pasaPorStock = true;
    private String idbotica;
    private int stockempaque;
    Mant_Productos mantProduc = new Mant_Productos();
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    private int unidad, fraccion;
    private int unidad1, fraccion1;
    MuestraVentana objetoventana = new MuestraVentana();
    private boolean consulta = false;
    LogicaMovimientos log = new LogicaMovimientos();
    int cantidad = 1;
    int cantidad1 = 1;
    Inventarios Oonjinventar;
    private boolean inventar = true;
    private boolean inventar1 = true;
    boolean oculta;
    FormProveedores formproveedor;
    private String idproveedor;
    ModeloTabla tablaproducto;
    JFrame ventana;

    /** Creates new form DescargosInventarios1 */
    public ProductosSAP(java.awt.Frame parent, Inventarios obj) {
        initComponents();
        idbotica = obj.getIdbotica();
        Oonjinventar = obj;
        tablaproducto = new ModeloTabla();
        modeloTabla = new ModeloTabla();
        construirGuia();
        construirGuiaCargo();
        cargarBoticas2();
        AparienciaTabla();
        AparienciaTablaCargo();
        verificaAlmacenes();
        MuestraFecha();
        this.jLabel8.setVisible(false);
        this.jLabel13.setVisible(false);
        this.jTable1.requestFocus();
        this.jButton6.setEnabled(false);
        this.jButton10.setEnabled(false);
        oculta = false;
        //jPanel2.requestFocus();
    }

    private void Defecto() {
        this.jTextField2.setText("ZZZZ");
        this.jTextField3.setText("POR CLASIFICAR");
        this.jTextField7.setText("ZZZZ");
        this.jTextField8.setText("POR CLASIFICAR");
    }

    private void MuestraFecha() {
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        lblFecha1.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        fechaDocumento.setDate(objFechaHora.RetornaFecha());
        fechaRecepcion.setDate(objFechaHora.RetornaFecha());
        fchaDocto.setDate(objFechaHora.RetornaFecha());
        fchaRecepcion.setDate(objFechaHora.RetornaFecha());
    }

    private void AparienciaTablaCargo() {
        JTableHeader cabeceraCargo = new JTableHeader(this.jTable2.getColumnModel());
        cabeceraCargo.setReorderingAllowed(false);
    }
    private void AparienciaTabla() {

        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);        

        DefaultTableCellRenderer tleft1 = new DefaultTableCellRenderer();
        tleft1.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tleft1);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tleft1);

        DefaultTableCellRenderer tleft = new DefaultTableCellRenderer();
        tleft.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tleft);

    }

    private void verificaAlmacenes() {

        if (objBoticas.VerificaPoseeAlmacenes(this.idbotica)) {
            jComboBox2.setEnabled(true);
            tipoAlmacen.setEnabled(true);
        } else {
            jComboBox2.setEnabled(false);
            tipoAlmacen.setEnabled(false);
        }
    }

    private void cargarBoticas2() {

        listaboticas2 = objBoticas.DescripcionBoticaDefault();
        lasboticas1.addItem(listaboticas2.get(0).getId_Botica());        
        //RECUPERA_ROL

        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objOpcionesMenu.getIdpersonal_botica()), 0) == 1) {
            /*Si es Controlado por Rol - Es cargo*/

            lasboticas2.setVisible(false);
            lasboticas5.setVisible(false);
            operaciones.setVisible(true);
            operaciones2.setVisible(true);
            jLabel6.setVisible(false);
            listaTipoMovimiento = objTipoMovimiento.ListarMovimientosPorRol(objPersonal.RecuperaRol(objOpcionesMenu.getIdpersonal_botica()), 0);
            operaciones.addItem("Seleccion Opcion");
            operaciones2.addItem("Seleccion Opcion");
            oculta = false;
            jLabel14.setVisible(false);
            for (Integer ao = 0; ao < listaTipoMovimiento.size(); ao++) {
                operaciones.addItem(listaTipoMovimiento.get(ao).getDescripcionMovimiento());
            }
            for (Integer ao1 = 0; ao1 < listaTipoMovimiento.size(); ao1++) {
                operaciones2.addItem(listaTipoMovimiento.get(ao1).getDescripcionMovimiento());
            }
            /*Ahora cargos los movimientos por Rol*/
            /*Ahora cargos los movimientos por Rol*/
        } else {
            /*Si es Controlado por Boticas - Es cargo*/
            this.jLabel12.setVisible(false);
            this.jLabel28.setVisible(false);
            inventar = false;
            operaciones.setVisible(false);
            operaciones2.setVisible(false);
            cargarBoticas();
            cargarBoticas1();
            listaboticas = objBoticas.ListarBoticasMiZona();
        }

    }

    private void Realiza_Opciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            if (!this.jButton5.isEnabled()) {
                agregandoProducto();
            }

        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
            Realiza_Nuevo();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            if (!this.jButton5.isEnabled()) {
                Guardar_Descargo();
            }

        }
    }

    private void Realiza_Nuevo() {

        if (this.jTable1.getRowCount() == 0) {

            this.jLabel8.setText("");
            this.jButton5.setEnabled(false);
            this.jButton6.setEnabled(true);
            this.jButton10.setEnabled(true);
            this.Defecto();
            Nuevo();
            consulta = false;
            this.jButton5.setEnabled(false);
            this.jLabel8.setVisible(false);
            this.jLabel8.setText("");
            operaciones.setEnabled(true);
            lasboticas2.setEnabled(true);
            lasboticas5.setEnabled(true);
            nrodocumento.setEnabled(true);
            nrodocumento2.setEnabled(true);
            limpiarTodo(true);
            recuperaDescSAP();
            recuperaCarSAP();
            MuestraFecha();

        } else {
            int p = JOptionPane.showConfirmDialog(null, " Deseas Realizar un Nuevo Descargo ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                this.jLabel8.setText("");
                this.jButton5.setEnabled(false);
                this.jButton6.setEnabled(true);
                this.jButton10.setEnabled(true);
                this.Defecto();
                Nuevo();
                this.jButton5.setEnabled(false);
                this.jLabel8.setVisible(false);
                this.jLabel8.setText("");
                operaciones.setEnabled(true);
                lasboticas2.setEnabled(true);
                nrodocumento.setEnabled(true);
                limpiarTodo(true);
                MuestraFecha();
                consulta = false;

            }

        }

    }

    private void Nuevo() {
        limpiarTodo(true);
    }

    private void recuperaDescSAP(){
        try {
            
                String idmovimirnto = listaTipoMovimiento.get(lasboticas2.getSelectedIndex() ).getId_TipoMovimiento();
                nrodocumento.setEditable(false);
                Movimientos movi = log.Devuelve_Movimeinto(idmovimirnto);
                if (movi != null) {
                    String numero = movi.getNumero_Documento();
                    if (!numero.isEmpty()) {
                        nrodocumento.setText(numero);
                    } else {
                        nrodocumento.setText("");
                        nrodocumento.setEditable(true);
                    }
                } else {
                    nrodocumento.setText("");
                    nrodocumento.setEditable(true);
                }

            } catch (Exception ex) {
            }
    }

    private void recuperaCarSAP(){
         try {
                if (this.lasboticas1.getItemCount() > 0 && lasboticas5.getSelectedIndex() >= 0) {
                    int ind = lasboticas5.getSelectedIndex();
                    if (ind <= this.listaboticas.size()) {
                        jTextField9.setVisible(true);
                        oculta = true;
                    } else {
                        jTextField9.setVisible(false);
                        oculta = false;
                    }
                }

                TipoMovimiento tipoMovi = listaTipoMovimiento1.get(lasboticas5.getSelectedIndex() );

                if (tipoMovi != null) {
                    String idmovimirnto = listaTipoMovimiento1.get(lasboticas5.getSelectedIndex() ).getId_TipoMovimiento();
                    Movimientos movi = log.Devuelve_Movimeinto(idmovimirnto);
                    if (movi != null) {
                        String numero = movi.getNumero_Documento();
                        if (!numero.isEmpty()) {
                            jTextField9.setVisible(false);
                            nrodocumento2.setEditable(false);
                            oculta = false;
                            nrodocumento2.setText(numero);
                        } else {
                            jTextField9.setVisible(true);
                            nrodocumento2.setEditable(true);
                            oculta = true;
                            nrodocumento2.setText("");
                        }
                    } else {
                        nrodocumento2.setText("");
                        nrodocumento2.setEditable(true);
                    }
                } else {
                    jTextField9.setVisible(true);
                    nrodocumento2.setEditable(true);
                    nrodocumento2.setText("");
                }

            } catch (Exception ex) {
            }
    }

    private void CerrarVentana() {
        if (this.jTable1.getRowCount() > 0) {

            int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                this.dispose();
                Oonjinventar.marcacdo = false;
            }
        } else {
            this.dispose();
            Oonjinventar.marcacdo = false;
        }

    }

    private void calculoMontos() {

        double parcial1 = 0;
        int fila = this.jTable1.getSelectedRow();

        try {

            String codpro = this.jTable1.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);

            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idbotica);
            double pv = objProducto.RecuperaPrecio(codpro, idbotica);

            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidad;


            if (empaque1 > 0) {
                parcial1 = (fraccion * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.jTable1.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);
            MiTotal();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void calculoMontosCargo() {

        double parcial1 = 0;
        int fila = this.jTable2.getSelectedRow();

        try {

            String codpro = this.jTable2.getValueAt(fila, 1).toString().trim();
            int empaque1 = objProducto.Recupera_Empaque(codpro);

            if (empaque1 == 0) {
                empaque1 = 1;
            }

            double descuento = objProducto.Recupera_Descuento_Producto(codpro, idbotica);
            double pv = objProducto.RecuperaPrecio(codpro, idbotica);

            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            pv = bd.doubleValue();

            double pvx = pv - (pv * (descuento / 100));

            BigDecimal bd1 = new BigDecimal(pvx);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
            pvx = bd1.doubleValue();

            double parcial = pvx * unidad;


            if (empaque1 > 0) {
                parcial1 = (fraccion * pvx) / empaque1;
            }

            parcial = parcial + parcial1;

            BigDecimal bd3 = new BigDecimal(parcial);
            bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
            parcial = bd3.doubleValue();

            this.jTable2.setValueAt(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 6);
            MiTotalCargo();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void MiTotal() {
        double total = 0;

        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            total = total + Double.parseDouble(jTable1.getValueAt(i, 6).toString());
        }

        BigDecimal bd3 = new BigDecimal(total);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd3.doubleValue();

        this.jTextField1.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        double z = Double.parseDouble(this.jTextField1.getText());
        if (z > 0){
            jButton3.setEnabled(false);
        }else{
            jButton3.setEnabled(true);
        }
    }

    private void MiTotalCargo() {
        double total = 0;

        for (int i = 0; i < this.jTable2.getRowCount(); i++) {
            total = total + Double.parseDouble(jTable2.getValueAt(i, 6).toString());
        }

        BigDecimal bd3 = new BigDecimal(total);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd3.doubleValue();

        this.jTextField4.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();


        try {

            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, jComboBox2.getSelectedItem().toString());

            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = empaque;

                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }

                //RECUPERO MI STOCK DEL EMPAQUIE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = stockempaque;

                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = stockfraccion;

                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unidad * empaque_tmp + fraccion;

                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }

            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }

        return resultado;

    }

    private void agregandoProducto() {
        // TODO add your handling code here:

        jTable1.requestFocus();
        Ventana obj = new Ventana();

        objrelew = new AgregaProductosSAP(obj, idbotica, jComboBox2.getSelectedItem().toString(), 0,"Descargo");
        objrelew.pack();
        objrelew.show(true);

        if (AgregaProductosSAP.getReal().length() > 0) {

            unidad = AgregaProductosSAP.getUnidad();
            fraccion = AgregaProductosSAP.getFraccion();

            if (unidad != -1 && fraccion != -1) {

                String codpro = AgregaProductosSAP.getIdproducto();
                int empaque1 = objProducto.Recupera_Empaque(codpro);

                if (unidad == 0 && fraccion == 0) {
                    JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (empaque1 == 0 && fraccion > 0) {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (VerificaStock(codpro)) {

                            Object[][] data = {
                                {
                                    cantidad,
                                    AgregaProductosSAP.getReal(),
                                    AgregaProductosSAP.getRealDes(),
                                    AgregaProductosSAP.getRealLab(),
                                    AgregaProductosSAP.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosSAP.getReal(), this.idbotica) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosSAP.getReal(), idbotica)) / 100, 2),
                                    0,
                                    AgregaProductosSAP.getPvp(),
                                    AgregaProductosSAP.getDescto()
                                }};

                            cantidad++;
                            modeloTabla.addRow(data[0]);
                            this.jTable1.requestFocus();
                            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);
                            calculoMontos();

                            if (modeloTabla.getRowCount() > 0) {
                                GenerarGuia.setEnabled(true);

                                AgregaProductosSAP.setReal("");

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Lo Sentimos no Hay Stock Suficiente ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }


            } else {
                JOptionPane.showMessageDialog(null, "Porfavor Ingrese Datos Correctos", "Nortfarma", JOptionPane.ERROR_MESSAGE);
            }


            this.jTable1.requestFocus();
            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);

        }

    }


    private void agregandoProductosCargo() {

        jTable2.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();
        Producto producto;

        List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
        array.removeAll(array);

        Movimiento_Detalle detalle;

        for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
            detalle = new Movimiento_Detalle();
            producto = new Producto();
            producto.setIdProducto(modeloTabla.getValueAt(totalProductos, 1).toString());
            
            detalle.setId_Producto(producto);
            array.add(detalle);
        }

        objrelew1 = new AgregaProductosSAP(obj, idbotica, tipoAlmacen.getSelectedItem().toString(), 1, "Cargo",array);
        objrelew1.pack();
        objrelew1.show(true);

        jTable2.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosSAP.getReal().trim().length() > 0) {

                unidad1 = AgregaProductosSAP.getUnidad();
                fraccion1 = AgregaProductosSAP.getFraccion();

                if (unidad1 != -1 && fraccion1 != -1) {

                    String codpro = AgregaProductosSAP.getIdproducto();
                    int empaque1 = objProducto.Recupera_Empaque(codpro);

                    if (unidad1 == 0 && fraccion1 == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccion > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data1 = {
                                {
                                    cantidad1,
                                    AgregaProductosSAP.getReal(),
                                    AgregaProductosSAP.getRealDes(),
                                    AgregaProductosSAP.getRealLab(),
                                    AgregaProductosSAP.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosSAP.getReal(), idbotica) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosSAP.getReal(), this.idbotica)) / 100, 2),
                                    0,
                                    AgregaProductosSAP.getPvp(),
                                    AgregaProductosSAP.getDescto()
                                }
                            };

                            cantidad1++;
                            tablaproducto.addRow(data1[0]);
                            this.jTable2.requestFocus();
                            jTable2.changeSelection(this.jTable2.getRowCount() - 1, 0, false, false);
                            calculoMontosCargo();
                            AgregaProductosSAP.setReal("");

                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (jTable2.getRowCount() > 12) {
            int row = jTable2.getRowCount() - 1;
            //Rectangle rect = jTable1.getCellRect(row, 0, true);
            //jTable1.scrollRectToVisible(rect);
            jTable2.clearSelection();
            jTable2.setRowSelectionInterval(row, row);
            tablaproducto.fireTableDataChanged();
        }
        this.jTable2.requestFocus();
        jTable2.changeSelection(this.jTable2.getRowCount() - 1, 0, false, false);

    }

    public void AsigaProductos(List<Movimiento_Detalle> listaDetalle) {

        for (int i = 0; i < listaDetalle.size(); i++) {

            String micantidad;
            if (listaDetalle.get(i).getFraccion() == 0) {
                micantidad = String.valueOf(listaDetalle.get(i).getUnidad());
            } else {
                micantidad = String.valueOf(listaDetalle.get(i).getUnidad()) + "F" + listaDetalle.get(i).getFraccion();
            }

            Object[][] data1 = {
                {
                    cantidad1,
                    listaDetalle.get(i).getId_Producto().getIdProducto(),//codigo producto
                    listaDetalle.get(i).getId_Producto().getDescripcion(),//producto
                    listaDetalle.get(i).getId_Producto().getLaboratorio().getId_Lab(),//laboratorio
                    micantidad,//cantidad pedida
                    listaDetalle.get(i).getPrecio_Venta_Final(),//PVPF
                    listaDetalle.get(i).getTotal(),
                    listaDetalle.get(i).getPrecio_Venta(),//PV
                    listaDetalle.get(i).getDescuento()//descuento
                }
            };

            cantidad1++;
            tablaproducto.addRow(data1[0]);

        }

        jTable2.setModel(tablaproducto);
        MiTotalCargo();

    }
    
    public double redondear(double numero, double ndecimal) {
        double factor = Math.pow(10, ndecimal);
        return (Math.round(numero * factor) / factor);
    }

    private String recuperaProximoIdInventarios() {
        String casadelmal = "";

        if (inventar) {
            casadelmal = objGuias.GeneraNuevoNumero(this.operaciones.getItemAt(operaciones.getSelectedIndex()).toString(), jComboBox2.getSelectedItem().toString());
        }

        return casadelmal;
    }

    private void limpiarTodo(Boolean valor) {

        try {

            if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 0) == 1) {
                operaciones.setSelectedIndex(0);
                operaciones2.setSelectedIndex(0);
            } else {
                lasboticas2.setSelectedIndex(0);
                lasboticas5.setSelectedIndex(0);
            }

            observ.setEnabled(valor);
            observ.setText("");
            observ1.setEnabled(valor);
            observ1.setText("");
            jTextField1.setText("");
            jTextField4.setText("");

            for (Integer i = 0; i < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(i);
            }
            
            for (Integer y = 0; y < tablaproducto.getRowCount();) {
                tablaproducto.removeRow(y);
            }


            jButton3.setEnabled(valor);
            jButton11.setEnabled(valor);
            jButton15.setEnabled(valor);
            GenerarGuia.setEnabled(valor);
            jButton2.setEnabled(valor);
            jButton4.setEnabled(!valor);
            jTextField1.setText("");
            nrodocumento.setText("");
            nrodocumento2.setText("");


        } catch (Exception ex) {
            System.out.println("LimpiarTOdo:" + ex.getMessage());
        }

    }

    private void cargarBoticas() {
        
        listaTipoMovimiento = objTipoMovimiento.ListarTipoMovimientoDescargoSAP(0);
        //lasboticas2.addItem("Seleccione Opcion");

        for (int i = 0; i < listaTipoMovimiento.size(); i++) {
            lasboticas2.addItem(listaTipoMovimiento.get(i).getDescripcionMovimiento());
        }

    }
    private void cargarBoticas1() {
        
        listaTipoMovimiento1 = objTipoMovimiento.ListarTipoMovimientoDescargoSAP1(1);
        //lasboticas5.addItem("Seleccione Opcion");

        for (int i = 0; i < listaTipoMovimiento1.size(); i++) {
            lasboticas5.addItem(listaTipoMovimiento1.get(i).getDescripcionMovimiento());
        }

    }

    private void construirGuia() {

        modeloTabla.addColumn("Nº");
        modeloTabla.addColumn("CODIGO");
        modeloTabla.addColumn("PRODUCTO");
        modeloTabla.addColumn("LAB");
        modeloTabla.addColumn("TOTAL");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("MONTO");
        modeloTabla.addColumn("PVP");
        modeloTabla.addColumn("DSCTO");

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        jTable1.setModel(modeloTabla);
        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(240);
        columnaTabla.setMinWidth(240);
        columnaTabla.setMaxWidth(240);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

    }

    private void construirGuiaCargo() {

        tablaproducto.addColumn("Nº");
        tablaproducto.addColumn("CODIGO");
        tablaproducto.addColumn("PRODUCTO");
        tablaproducto.addColumn("LAB");
        tablaproducto.addColumn("TOTAL");
        tablaproducto.addColumn("PRECIO");
        tablaproducto.addColumn("MONTO");
        tablaproducto.addColumn("PVP");
        tablaproducto.addColumn("DSCTO");

        jTable2.setModel(tablaproducto);
        columnaTabla = jTable2.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable2.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable2.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(220);
        columnaTabla.setMinWidth(220);
        columnaTabla.setMaxWidth(220);


        columnaTabla = jTable2.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable2.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable2.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable2.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
    }

    private void Lista_Cargos() {

        if (listaTipoMovimiento.size() > 0) {

            String idtipmov = null;
            String alm = this.tipoAlmacen.getSelectedItem().toString().trim();

            if (inventar) {
                if (operaciones2.getSelectedIndex() > 0) {

                    consulta = true;
                    idtipmov = listaTipoMovimiento.get(this.operaciones2.getSelectedIndex() - 1).getId_TipoMovimiento();
                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.operaciones2.getSelectedItem().toString());
                    obj.pack();
                    obj.setVisible(true);

                    String midocumento = obj.getDocumento();
                    String mialm = obj.getAlm();


                    if (!midocumento.isEmpty()) {

                        nrodocumento2.setText(midocumento);

                        if (mialm.charAt(0) == 'M') {
                            tipoAlmacen.setSelectedIndex(0);
                        } else if (mialm.charAt(0) == 'A') {
                            tipoAlmacen.setSelectedIndex(1);
                        }

                        //Muestra_Informacion(idtipmov);
                        jButton11.setEnabled(false);
                        //jButton12.setEnabled(false);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            } else {

                if (lasboticas5.getSelectedIndex() > 0) {
                    consulta = true;

                    for (int i = 0; i < lasboticas5.getItemCount(); i++) {
                        String mov = lasboticas5.getSelectedItem().toString().trim();

                        for (int k = 0; k < listaTipoMovimiento.size(); k++) {

                            if (mov.compareTo(listaTipoMovimiento.get(k).getDescripcionMovimiento().trim()) == 0) {
                                idtipmov = listaTipoMovimiento.get(k).getId_TipoMovimiento();
                                i = lasboticas5.getItemCount();
                                break;
                            }

                        }
                    }

                    alm = this.tipoAlmacen.getSelectedItem().toString().trim();
                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.lasboticas5.getSelectedItem().toString());
                    obj.pack();
                    obj.setVisible(true);

                    String midocumento = obj.getDocumento();
                    String mialm = obj.getAlm();

                    if (!midocumento.isEmpty()) {

                        nrodocumento.setText(midocumento);

                        if (mialm.charAt(0) == 'M') {
                            tipoAlmacen.setSelectedIndex(0);
                        } else if (mialm.charAt(0) == 'A') {
                            tipoAlmacen.setSelectedIndex(1);
                        }

                        //Muestra_Informacion(idtipmov);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    private String[] ArrayBoticas = {};

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lasboticas2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        nrodocumento = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        operaciones = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fechaDocumento = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        fechaRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observ = new javax.swing.JTextArea();
        GenerarGuia = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lasboticas5 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        nrodocumento2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        tipoAlmacen = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        operaciones2 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblFecha1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        observ1 = new javax.swing.JTextArea();
        fchaDocto = new org.jdesktop.swingx.JXDatePicker();
        fchaRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lasboticas1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ProductosSAP.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 585));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowHorizontalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jButton3.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel3.border.titleFont"), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        lasboticas2.setFont(resourceMap.getFont("lasboticas2.font")); // NOI18N
        lasboticas2.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas2.setEnabled(false);
        lasboticas2.setName("lasboticas2"); // NOI18N
        lasboticas2.setPreferredSize(new java.awt.Dimension(200, 20));
        lasboticas2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lasboticas2ItemStateChanged(evt);
            }
        });
        lasboticas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas2ActionPerformed(evt);
            }
        });
        lasboticas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lasboticas2KeyPressed(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        nrodocumento.setBackground(resourceMap.getColor("nrodocumento.background")); // NOI18N
        nrodocumento.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        nrodocumento.setForeground(resourceMap.getColor("nrodocumento.foreground")); // NOI18N
        nrodocumento.setEnabled(false);
        nrodocumento.setName("nrodocumento"); // NOI18N
        nrodocumento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nrodocumentoMouseClicked(evt);
            }
        });
        nrodocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nrodocumentoActionPerformed(evt);
            }
        });
        nrodocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrodocumentoFocusLost(evt);
            }
        });
        nrodocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nrodocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrodocumentoKeyTyped(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setEnabled(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lasboticas2, 0, 166, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(resourceMap.getString("jTextField2.toolTipText")); // NOI18N
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(resourceMap.getString("jTextField3.toolTipText")); // NOI18N
        jTextField3.setFocusable(false);
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox2.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        operaciones.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        operaciones.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        operaciones.setEnabled(false);
        operaciones.setName("operaciones"); // NOI18N
        operaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                operacionesItemStateChanged(evt);
            }
        });
        operaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operacionesActionPerformed(evt);
            }
        });
        operaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                operacionesKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(operaciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        lblFecha.setForeground(resourceMap.getColor("lblFecha.foreground")); // NOI18N
        lblFecha.setText(resourceMap.getString("lblFecha.text")); // NOI18N
        lblFecha.setName("lblFecha"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        fechaDocumento.setEnabled(false);
        fechaDocumento.setFormats(new String[] { "dd/M/yyyy" });
        fechaDocumento.setName("fechaDocumento"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        fechaRecepcion.setEnabled(false);
        fechaRecepcion.setFormats(new String[] { "dd/M/yyyy" });
        fechaRecepcion.setName("fechaRecepcion"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        observ.setColumns(20);
        observ.setFont(resourceMap.getFont("observ.font")); // NOI18N
        observ.setRows(5);
        observ.setEnabled(false);
        observ.setName("observ"); // NOI18N
        observ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                observKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(observ);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(156, 156, 156)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(194, 194, 194))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, 0, 81, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        GenerarGuia.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        GenerarGuia.setText(resourceMap.getString("GenerarGuia.text")); // NOI18N
        GenerarGuia.setEnabled(false);
        GenerarGuia.setName("GenerarGuia"); // NOI18N
        GenerarGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarGuiaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel11.setName("jPanel11"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        lasboticas5.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas5.setEnabled(false);
        lasboticas5.setName("lasboticas5"); // NOI18N
        lasboticas5.setPreferredSize(new java.awt.Dimension(200, 20));
        lasboticas5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lasboticas5ItemStateChanged(evt);
            }
        });
        lasboticas5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas5ActionPerformed(evt);
            }
        });
        lasboticas5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lasboticas5KeyPressed(evt);
            }
        });

        jLabel25.setFont(resourceMap.getFont("jLabel25.font")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setFont(resourceMap.getFont("jTextField9.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setToolTipText(resourceMap.getString("jTextField9.toolTipText")); // NOI18N
        jTextField9.setEnabled(false);
        jTextField9.setName("jTextField9"); // NOI18N

        nrodocumento2.setBackground(resourceMap.getColor("nrodocumento2.background")); // NOI18N
        nrodocumento2.setFont(resourceMap.getFont("nrodocumento2.font")); // NOI18N
        nrodocumento2.setForeground(resourceMap.getColor("nrodocumento2.foreground")); // NOI18N
        nrodocumento2.setEnabled(false);
        nrodocumento2.setName("nrodocumento2"); // NOI18N
        nrodocumento2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nrodocumento2MouseClicked(evt);
            }
        });
        nrodocumento2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrodocumento2FocusLost(evt);
            }
        });
        nrodocumento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nrodocumento2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrodocumento2KeyTyped(evt);
            }
        });

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setEnabled(false);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nrodocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, 0, 0, Short.MAX_VALUE))
                    .addComponent(lasboticas5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lasboticas5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nrodocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel12.setName("jPanel12"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setToolTipText(resourceMap.getString("jTextField7.toolTipText")); // NOI18N
        jTextField7.setFocusable(false);
        jTextField7.setName("jTextField7"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setToolTipText(resourceMap.getString("jTextField8.toolTipText")); // NOI18N
        jTextField8.setFocusable(false);
        jTextField8.setName("jTextField8"); // NOI18N
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        tipoAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen" }));
        tipoAlmacen.setName("tipoAlmacen"); // NOI18N
        tipoAlmacen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoAlmacenItemStateChanged(evt);
            }
        });
        tipoAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAlmacenActionPerformed(evt);
            }
        });
        tipoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoAlmacenKeyPressed(evt);
            }
        });

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        operaciones2.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        operaciones2.setEnabled(false);
        operaciones2.setName("operaciones2"); // NOI18N
        operaciones2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                operaciones2ItemStateChanged(evt);
            }
        });
        operaciones2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operaciones2ActionPerformed(evt);
            }
        });
        operaciones2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                operaciones2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(operaciones2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tipoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(operaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setName("jPanel7"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        lblFecha1.setForeground(resourceMap.getColor("lblFecha1.foreground")); // NOI18N
        lblFecha1.setText(resourceMap.getString("lblFecha1.text")); // NOI18N
        lblFecha1.setName("lblFecha1"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        observ1.setColumns(20);
        observ1.setFont(resourceMap.getFont("observ1.font")); // NOI18N
        observ1.setRows(5);
        observ1.setEnabled(false);
        observ1.setName("observ1"); // NOI18N
        observ1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                observ1KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(observ1);

        fchaDocto.setEnabled(false);
        fchaDocto.setFont(resourceMap.getFont("jXDatePicker2.font")); // NOI18N
        fchaDocto.setFormats(new String[] { "dd/M/yyyy" });
        fchaDocto.setName("fchaDocto"); // NOI18N

        fchaRecepcion.setEnabled(false);
        fchaRecepcion.setFont(resourceMap.getFont("fchaRecepcion.font")); // NOI18N
        fchaRecepcion.setFormats(new String[] { "dd/M/yyyy" });
        fchaRecepcion.setName("fchaRecepcion"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(fchaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecha1)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)))
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fchaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, 0, 81, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), resourceMap.getString("jPanel13.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel13.border.titleFont"), resourceMap.getColor("jPanel13.border.titleColor"))); // NOI18N
        jPanel13.setName("jPanel13"); // NOI18N
        jPanel13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel13KeyPressed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowHorizontalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setEnabled(false);
        jButton11.setFocusable(false);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel29.setName("jLabel29"); // NOI18N

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setEnabled(false);
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setFocusable(false);
        jLabel15.setName("jLabel15"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setFocusable(false);
        jTextField4.setName("jTextField4"); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(236, 236, 236)
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lasboticas1.setFont(resourceMap.getFont("lasboticas1.font")); // NOI18N
        lasboticas1.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas1.setEnabled(false);
        lasboticas1.setName("lasboticas1"); // NOI18N
        lasboticas1.setPreferredSize(new java.awt.Dimension(180, 22));
        lasboticas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, 0, 567, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenerarGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(GenerarGuia, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName(resourceMap.getString("jPanel3.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasboticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas1ActionPerformed
}//GEN-LAST:event_lasboticas1ActionPerformed

    private void lasboticas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas2ActionPerformed
}//GEN-LAST:event_lasboticas2ActionPerformed

    private void operacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operacionesActionPerformed
}//GEN-LAST:event_operacionesActionPerformed

    private void nrodocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nrodocumentoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_nrodocumentoActionPerformed

    private void nrodocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrodocumentoFocusLost
}//GEN-LAST:event_nrodocumentoFocusLost

    private void nrodocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyTyped
        if (nrodocumento.getText().length() > 10) {
            evt.consume();
        }
}//GEN-LAST:event_nrodocumentoKeyTyped

    private void Verifica_Cantidad() {

        int fila = this.jTable1.getSelectedRow();

        try {

            if (fila >= 0) {

                JOptionPane p = new JOptionPane();
                String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);


                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            String idproducto = this.jTable1.getValueAt(fila, 1).toString().trim();
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidad(cantidad1);

                            if (unidad == 0 && fraccion == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccion > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    cantidad1 = cantidad1.toUpperCase();

                                    if (resul) {

                                        if (VerificaStock(idproducto)) {

                                            jTable1.setValueAt(cantidad1, fila, 4);
                                            calculoMontos();

                                        } else {
                                            JOptionPane.showMessageDialog(this, " Producto no cuenta con ese Stock  ", "Error", JOptionPane.ERROR_MESSAGE);
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }

                            }

                        }

                    } else {
                        JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }


        } catch (Exception ex) {
        }


    }

    /*
     * METODO QUE CALCULA SI LO INGRESADO ES UNIDAD Y FRACCION
     */
    private boolean VerificaCantidad(String cantidad) {
        String unidad1 = "";
        String fraccion1 = "";
        int k = 0;
        boolean segundo = false;
        char car = 'F';
        int cantdef = 0;
        String valor = "";

        cantidad = cantidad.trim();
        Character w;
        valor = valor.trim();

        for (int i = 0; i < cantidad.length(); i++) {
            w = cantidad.charAt(i);
            if (w.isDigit(w) || w.isLetter(w)) {
                valor = valor + w;
            } else {
                return false;
            }
        }


        for (int i = 0; i < valor.length(); i++) {
            Character caracter = valor.charAt(i);

            if (caracter.isLetter(caracter)) {
                caracter = caracter.toUpperCase(caracter);
                cantdef++;
                if (caracter != car || cantdef > 1) {
                    return false;
                }

            }
            if (caracter.isDigit(caracter) && segundo == false) {
                unidad1 = unidad1 + caracter;
            } else {
                segundo = true;
                if (k == 1) {
                    if (caracter.isDigit(caracter)) {
                        fraccion1 = fraccion1 + caracter;
                    }
                }
                k = 1;
            }
        }

        if (unidad1.compareTo("") == 0) {
            unidad1 = "0";
        }
        if (fraccion1.compareTo("") == 0) {
            fraccion1 = "0";
        }

        unidad = Integer.valueOf(unidad1);
        fraccion = Integer.valueOf(fraccion1);
        return true;

    }

    /****************************************************************
     * METODO PARA VOLVER A REORDENAR LAS CANTIDADES DE LOS PRODUCTOS
     * **************************************************************/
    private void ReordenaTabla(int ultposi) {
        try {

            for (int i = ultposi; i < this.jTable1.getRowCount(); i++) {
                jTable1.setValueAt(ultposi + 1, ultposi, 0);
                ultposi++;
            }

        } catch (Exception EX) {
            JOptionPane.showMessageDialog(this, "ERROR AL REORDENAR TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void ReordenaTablaCargo(int ultposi) {
        try {

            for (int i = ultposi; i < this.jTable2.getRowCount(); i++) {
                jTable2.setValueAt(ultposi + 1, ultposi, 0);
                ultposi++;
            }

        } catch (Exception EX) {
            JOptionPane.showMessageDialog(this, "ERROR AL REORDENAR TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        Realiza_Opciones(evt);

        switch (evt.getKeyCode()) {

            case 127:

                if (!this.jButton5.isEnabled()) {

                    int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) modeloTabla.getValueAt(jTable1.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {

                        int ultposi;
                        int fila = this.jTable1.getSelectedRow();
                        ultposi = ((Integer) this.jTable1.getValueAt(fila, 0)).intValue();
                        ultposi--;
                        modeloTabla.removeRow(fila);
                        cantidad--;

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                        MiTotal();
                    }


                }

                break;
            case 10:

                if (!this.jButton5.isEnabled()) {
                    Verifica_Cantidad();
                    MiTotal();
                }
                break;

            case 117:
                agregandoProducto();
                break;


        }
}//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    }//GEN-LAST:event_jTable1KeyReleased

    private int Verifica_Producto() {
        Integer en = 0;
        Integer bandera = 0;
        Integer enterito = 0;
        Integer fraccionado = 0;
        Integer totalLetras = 0;
        int resul = -1;


        Stocks.removeAll(Stocks);

        if (jTable1.getRowCount() > 0) {
            //valida caracteres extraños

            for (Integer largo = 0; largo < jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().length(); largo++) {

                if (((jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().substring(largo, largo + 1).charAt(0) >= 48) && (jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().substring(largo, largo + 1).charAt(0) <= 57)) || (jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70)) {
                } else {
                    totalLetras++;
                }
            }

            if (totalLetras == 0) {
                //Valido Empaque

                List<Productos_Botica> lista = objProducto.RecuperEmpaque(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());

                if (lista.get(0).getProducto().getEmpaque() == 0) {
                    //primero Pregunto si existe una F
                    for (Integer largo = 0; largo < jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().length(); largo++) {
                        if (jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70) {
                            en = 1;
                        }
                    }

                    if (en == 1) {
                        jTable1.setValueAt("1", jTable1.getSelectedRow(), 4);
                        resul = jTable1.getSelectedRow();
                        return resul;
                    }

                    lista.get(0).getProducto().setEmpaque(1);
                }


                //Valido Stock
                Stocks = objProducto.RecuperaStock(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString());

                enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(jTable1.getSelectedRow(), 4).toString());
                fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(jTable1.getSelectedRow(), 4).toString());

                if (objProducto.validaPosibleDescargo(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), enterito, fraccionado)) {
                    resul = -1;
                } else {
                    //
                    resul = jTable1.getSelectedRow();
                    return resul;
                }


                double precio = objProducto.RecuperaPrecio(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), this.idbotica) * (100 - objProducto.Recupera_Descuento_Producto(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), OpcionesMenu.getIdbotica())) / 100;
                jTable1.setValueAt(redondear(precio / lista.get(0).getProducto().getEmpaque() * (enterito * lista.get(0).getProducto().getEmpaque() + fraccionado), 3), jTable1.getSelectedRow(), 6);


            } else {
                //else de total de letras
                JOptionPane.showMessageDialog(null, "Existen caracteres extraños por favor haga ingresos validos", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                jTable1.setValueAt(0, jTable1.getSelectedRow(), 4);
            }

        }

        return resul;
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
            agregandoProducto();
            
}//GEN-LAST:event_jButton3ActionPerformed

    private void Guardar_Descargo() {

        List<String> codigoX = new ArrayList<String>();
        List<Integer> unidadX = new ArrayList<Integer>();
        List<Integer> fraccionX = new ArrayList<Integer>();
        boolean encontradoX = false;

        for (int sostart = 0; sostart < jTable1.getRowCount(); sostart++) {

            for (int mini = 0; mini < codigoX.size(); mini++) {

                if (codigoX.get(mini).toString().compareTo(jTable1.getValueAt(sostart, 1).toString()) == 0) {
                    encontradoX = true;
                    unidadX.add(mini, Integer.valueOf(objFechaHora.RecuperaEntero(jTable1.getValueAt(sostart, 4).toString())) + unidadX.get(mini));
                    fraccionX.add(mini, Integer.valueOf(objFechaHora.RecuperaFraccion(jTable1.getValueAt(sostart, 4).toString())) + fraccionX.get(mini));
                }
            }

            if (encontradoX == false) {
                unidadX.add(Integer.valueOf(objFechaHora.RecuperaEntero(jTable1.getValueAt(sostart, 4).toString())));
                fraccionX.add(Integer.valueOf(objFechaHora.RecuperaFraccion(jTable1.getValueAt(sostart, 4).toString())));
                codigoX.add(jTable1.getValueAt(sostart, 1).toString());
            }

            encontradoX = false;
        }

        boolean kamehameha = true;

        if (kamehameha == true) {
            if (jTable1.getRowCount() > 0) {

                Integer a = 0;
                Integer totalLetras = 0;
                Integer enterito = 0;
                Integer fraccionado = 0;
                Integer valido = 0;
                String seleccionado;
                pasaPorStock = true;
                Integer bandera = 0;

                try {

                    if (objFechaHora.ConvierteFecha(fechaDocumento.getDate()).length() < 10) {
                        JOptionPane.showMessageDialog(null, "1.Seleccione Fecha Documento", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    } else {

                        if (nrodocumento.getText().toString().trim().length() < 1) {
                            JOptionPane.showMessageDialog(null, "Ingrese Numero de la Operacion", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        } else {

                            if (lasboticas2.isVisible()) {

                                if (lasboticas2.getSelectedIndex() != 0) {
                                    JOptionPane.showMessageDialog(null, "Seleccione Movimiento a Realizar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    valido = 0;
                                } else {
                                    //System.out.println("Ya VALIDE TODO AHORA SI A GUARDAR");*/
                                    /*Primero Guardo en la Base Local*/
                                    valido = 1;
                                }
                            } else {
                                //para el caso de ser de inventarios
                                if (operaciones.getSelectedIndex() < 1) {
                                    valido = 0;
                                    JOptionPane.showMessageDialog(null, "Seleccione Tipo Movimiento", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    valido = 1;
                                }
                            }

                            if (valido == 1) {

                                Confirmar p = new Confirmar(objetoventana, "<html>Deseas realizar esta Operacion ... \nSe Procederá a Descargar y Cargar este producto </html>");
                                p.show(true);

                                if (p.getConfirmar() == 1) {
                                    if (lasboticas2.isVisible()) {
                                        seleccionado = lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString();
                                    } else {
                                        seleccionado = operaciones.getItemAt(operaciones.getSelectedIndex()).toString();
                                    }

                                    String operacion;

                                    if (inventar) {
                                        operacion = operaciones.getSelectedItem().toString();
                                    } else {
                                        operacion = lasboticas2.getSelectedItem().toString();
                                    }

                                    entidadGuias = new Guias(
                                            lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString(),
                                            jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString(),
                                            nrodocumento.getText().trim().toUpperCase(), this.jTextField2.getText().trim(),
                                            objFechaHora.ConvierteFecha(fechaDocumento.getDate()),
                                            OpcionesMenu.getIdpersonal_botica(),
                                            objFechaHora.ConvierteFecha(fechaRecepcion.getDate()),
                                            operacion, Double.parseDouble(jTextField1.getText().trim()));

                                    for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
                                        enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(totalProductos, 4).toString());
                                        fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(totalProductos, 4).toString());

                                        if (objProducto.validaPosibleDescargo(modeloTabla.getValueAt(totalProductos, 1).toString(), enterito, fraccionado)) {
                                        } else {
                                            pasaPorStock = false;
                                            JOptionPane.showMessageDialog(null, "Productos con Stock Insuficiente:" + modeloTabla.getValueAt(totalProductos, 1).toString(), "NORTFARMA - INVENTARIOS", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }

                                    if ((objGuias.guiaCargada(entidadGuias, 1, 0) == 0) && (pasaPorStock == true)) {
                                        Movimiento_Detalle detalle;
                                        Producto producto;
                                        List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
                                        array.removeAll(array);

                                        for (Integer totalProductos = 0; totalProductos < modeloTabla.getRowCount(); totalProductos++) {
                                            producto = new Producto();
                                            detalle = new Movimiento_Detalle();
                                            bandera = 0;
                                            fraccionado = 0;
                                            enterito = 0;
                                            enterito = objFechaHora.RecuperaEntero(modeloTabla.getValueAt(totalProductos, 4).toString());
                                            fraccionado = objFechaHora.RecuperaFraccion(modeloTabla.getValueAt(totalProductos, 4).toString());

                                            double pv = Double.parseDouble(modeloTabla.getValueAt(totalProductos, 7).toString());
                                            double dcto = Double.parseDouble(modeloTabla.getValueAt(totalProductos, 8).toString());
                                            double pvf = pv - ((pv * dcto) / 100);
                                            double total = Double.parseDouble(modeloTabla.getValueAt(totalProductos, 6).toString());

                                            producto.setIdProducto(modeloTabla.getValueAt(totalProductos, 1).toString());
                                            detalle.setId_Producto(producto);
                                            detalle.setUnidad(enterito);
                                            detalle.setFraccion(fraccionado);
                                            detalle.setPrecio_Venta(pv);
                                            detalle.setPrecio_Venta_Final(pvf);
                                            detalle.setDescuento(dcto);
                                            detalle.setTotal(total);
                                            array.add(detalle);

                                        }

                                        Movimientos ObjMov = objGuias.DescargarDetalleGuia(entidadGuias, array, observ.getText().trim().toUpperCase(),1);

                                        if (ObjMov.getIderror() == 0) {
                                            //limpiarTodo(false);
                                            //this.jButton5.setEnabled(true);
                                            cantidad = 1;
                                            /*JOptionPane.showMessageDialog(null, "Descargo Realizado \n Numero de Docmuento Generado :  " + ObjMov.getNumero_Documento() + "", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                            this.jButton6.setEnabled(false);
                                            operaciones.setEnabled(false);
                                            lasboticas2.setEnabled(false);
                                            nrodocumento.setEnabled(false);
                                            jButton5.setEnabled(false);*/
                                        } else {
                                            if (ObjMov.getIderror() == 1) {
                                                JOptionPane.showMessageDialog(null, " Lo sentimos Hubo un Problema al Realizar su Descargo ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                            } else {
                                                if (ObjMov.getIderror() == 2) {
                                                    JOptionPane.showMessageDialog(null, " Lo sentimos No Cuenta con Stock del Producto \n " + ObjMov.getId_Producto() + " ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Ha ocurrido uno de los siguientes problemas: \n- Numero de Documento ya generado.\n- Favor verificar", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }//termino valido
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "1. Existen Caracteres Invalidos o se encuentra vacio los campos de Cantidad, fv verifique", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void GuardarCargo() {

        if (jTable2.getRowCount() > 0) {

            Integer enterito1 = 0;
            Integer fraccionado1 = 0;
            String proce = "";
            Integer tp = 0;
            Integer Seleccion1 = 0;
            boolean continua = true;
            Integer bandera1 = 0;

            if (this.inventar) {
                Seleccion1 = operaciones2.getSelectedIndex();
            } else {
            Seleccion1 = lasboticas5.getSelectedIndex();
            }

            if ((((Seleccion1 == 0) && (nrodocumento2.getText().length() > 0)) && (jTable2.getRowCount() > 0) && jTextField7.getText().length() > 0)) {
            //if ((nrodocumento2.getText().length() > 0) && (jTable2.getRowCount() > 0) && jTextField7.getText().length() > 0) {

                /*Cargando Cabecera*/
                Guias entidadGuia = new Guias();
                Producto entidadProductos = new Producto();

                if (operaciones2.isVisible()) {
                    proce = operaciones2.getItemAt(operaciones2.getSelectedIndex()).toString();
                    tp = 1;
                    /*En el caso de que sea de Otra boticas*/
                } else {
                    /*En el caso de que sea de Oficina*/
                    proce = lasboticas5.getItemAt(lasboticas5.getSelectedIndex()).toString();
                    tp = 0;
                }


                String operacion;
                if (inventar) {
                    operacion = operaciones2.getSelectedItem().toString();
                } else {
                    operacion = lasboticas5.getSelectedItem().toString();
                }

                if (oculta) {
                    if (this.jTextField9.getText().trim().compareTo("") == 0) {
                        JOptionPane.showMessageDialog(null, "Porfavor Ingrese la Serie ", "Error", JOptionPane.ERROR_MESSAGE);
                        continua = false;
                        this.jTextField9.requestFocus();
                    }

                }

                if (continua) {

                    String miserie = "";

                    if (jTextField9.getText().trim().compareTo("") != 0) {
                        miserie = jTextField9.getText().trim() + '-';
                    }

                    String docu = miserie + nrodocumento2.getText().trim();

                    entidadGuia = new Guias(
                            lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString(),
                            tipoAlmacen.getItemAt(tipoAlmacen.getSelectedIndex()).toString(),
                            docu,
                            jTextField7.getText().trim(),
                            objFechaHora.ConvierteFecha(fchaDocto.getDate()),
                            OpcionesMenu.getIdpersonal_botica(),
                            objFechaHora.ConvierteFecha(fchaRecepcion.getDate()),
                            operacion, Double.parseDouble(jTextField4.getText().trim()));

                    entidadGuia.setNumero(nrodocumento2.getText().trim());
                    //Confirmar p = new Confirmar(objetoventana, " Deseas Guardar este Cargo ");
                    //p.show(true);

                    int confir = 1;
                    //if (p.getConfirmar() == 1) {
                    if (confir == 1) {

                        if (objGuias.guiaCargada(entidadGuia, tp, 1) == 0) {
                            Movimiento_Detalle detalle1;
                            Producto producto;
                            List<Movimiento_Detalle> array2 = new ArrayList<Movimiento_Detalle>();
                            array2.removeAll(array2); //modeloTabla

                            for (Integer totalProductos = 0; totalProductos < tablaproducto.getRowCount(); totalProductos++) {
                                detalle1 = new Movimiento_Detalle();
                                producto = new Producto();
                                bandera1 = 0;
                                fraccionado1 = 0;
                                enterito1 = 0;
                                enterito1 = objFechaHora.RecuperaEntero(tablaproducto.getValueAt(totalProductos, 4).toString());
                                fraccionado1 = objFechaHora.RecuperaFraccion(tablaproducto.getValueAt(totalProductos, 4).toString());

                                double pv = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 7).toString());
                                double dcto = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 8).toString());
                                double pvf = pv - ((pv * dcto) / 100);
                                double total = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 6).toString());

                                producto.setIdProducto(tablaproducto.getValueAt(totalProductos, 1).toString());
                                detalle1.setId_Producto(producto);
                                detalle1.setUnidad(enterito1);
                                detalle1.setFraccion(fraccionado1);
                                detalle1.setPrecio_Venta(pv);
                                detalle1.setPrecio_Venta_Final(pvf);
                                detalle1.setDescuento(dcto);
                                detalle1.setTotal(total);
                                array2.add(detalle1);

                            }

                            Movimientos ObjMov = objGuias.CargarDetalleGuiaCargo(entidadGuia, array2, tp, 1, observ1.getText().trim().toUpperCase(), tp, this.jTextField9.getText().trim(), entidadproformaCliente);

                            if (ObjMov.getIderror() == 0) {
                                limpiarTodo(false);
                                cantidad1 = 1;
                                jButton7.setEnabled(true);
                                JOptionPane.showMessageDialog(null, "Descargo y Cargo Realizado \n Numero de Documento Generado :  " + ObjMov.getNumero_Documento() + "", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                lasboticas5.setEnabled(false);
                                jTextField9.setEnabled(false);
                                nrodocumento2.setEnabled(false);
                                jButton7.setEnabled(false);
                                operaciones2.setEnabled(false);

                                /**/
                                this.jButton5.setEnabled(true);
                                this.jButton6.setEnabled(false);
                                operaciones.setEnabled(false);
                                lasboticas2.setEnabled(false);
                                nrodocumento.setEnabled(false);
                                jButton5.setEnabled(false);
                                /**/
                            } else {
                                if (ObjMov.getIderror() == 1) {
                                    JOptionPane.showMessageDialog(null, " Lo sentimos Hubo un Problema al Realizar su Cargo ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (ObjMov.getIderror() == 2) {
                                        JOptionPane.showMessageDialog(null, " Lo sentimos No Cuenta con Stock del Producto \n " + ObjMov.getId_Producto() + " ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Este Numero de Guia ya ha sido Cargado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "2. Existen Datos incompletos, Porfavor Verificar!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void GenerarGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarGuiaActionPerformed
        
        if (jTable1.getRowCount() > 0){

            if (jTable2.getRowCount() > 0){
        
                IngresePersonalDatos obj = new IngresePersonalDatos(this, idbotica);
                //IngreseCajeroPago obj = new IngreseCajeroPago(this, idbotica,listapersonal);
                obj.setVisible(true);

            }
            else
            {
            JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item de cargo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item de Descargo", "Error", JOptionPane.ERROR_MESSAGE);
        }


        Guardar_Descargo();
        GuardarCargo();
}//GEN-LAST:event_GenerarGuiaActionPerformed

    public void DatosCliente(IngresePersonalDatos obj, int id_cliente) {

        String nombreCliente = obj.getObjcliente().getNombre_RazonSocial();
        String ruc = obj.getObjcliente().getRUC_DNI();
        String dni = obj.getObjcliente().getDNI();
        int tpago = obj.getObjcliente().gettippago();
        int tvta = obj.getObjcliente().gettipvta();
        IGV = mantProduc.Captura_IGV();

        entidadproformaCliente = new Proforma(nombreCliente, ruc, dni, tpago, tvta,IGV);
    }
    

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton5.setEnabled(true);
        limpiarTodo(false);
        jButton6.setEnabled(false);
        jButton10.setEnabled(false);
        operaciones.setEnabled(false);
        operaciones2.setEnabled(false);
        lasboticas2.setEnabled(false);
        lasboticas5.setEnabled(false);
        nrodocumento.setEnabled(false);
        nrodocumento2.setEnabled(false);
        jButton5.setEnabled(false);

}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Realiza_Nuevo();
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void operacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_operacionesKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_operacionesKeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void observKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_observKeyPressed

    private void lasboticas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lasboticas2KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_lasboticas2KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Lista_Descargos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // this.nrodocumento.setText(recuperaProximoIdInventarios());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if (!this.jButton5.isEnabled()) {
            this.nrodocumento.setText(recuperaProximoIdInventarios());
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void operacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_operacionesItemStateChanged
        this.nrodocumento.setText(recuperaProximoIdInventarios());
    }//GEN-LAST:event_operacionesItemStateChanged

    private void nrodocumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nrodocumentoMouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Lista_Descargos();
        }
    }//GEN-LAST:event_nrodocumentoMouseClicked

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Muestra_Proveedores();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void nrodocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_nrodocumentoKeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jButton5KeyPressed

    private void lasboticas2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lasboticas2ItemStateChanged
       /* if (evt.getStateChange() == 1) {
            try {
                String idmovimirnto = listaTipoMovimiento.get(lasboticas2.getSelectedIndex() ).getId_TipoMovimiento();
                nrodocumento.setEditable(false);
                Movimientos movi = log.Devuelve_Movimeinto(idmovimirnto);
                if (movi != null) {
                    String numero = movi.getNumero_Documento();
                    if (!numero.isEmpty()) {
                        nrodocumento.setText(numero);
                    } else {
                        nrodocumento.setText("");
                        nrodocumento.setEditable(true);
                    }
                } else {
                    nrodocumento.setText("");
                    nrodocumento.setEditable(true);
                }

            } catch (Exception ex) {
            }
        }*/

    }//GEN-LAST:event_lasboticas2ItemStateChanged

    private void lasboticas5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lasboticas5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lasboticas5ItemStateChanged

    private void lasboticas5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lasboticas5ActionPerformed

    private void lasboticas5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lasboticas5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lasboticas5KeyPressed

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Muestra_Proveedores();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void tipoAlmacenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoAlmacenItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoAlmacenItemStateChanged

    private void tipoAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoAlmacenActionPerformed

    private void tipoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoAlmacenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoAlmacenKeyPressed

    private void operaciones2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_operaciones2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_operaciones2ItemStateChanged

    private void operaciones2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operaciones2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_operaciones2ActionPerformed

    private void operaciones2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_operaciones2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_operaciones2KeyPressed

    private void nrodocumento2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nrodocumento2MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Lista_Cargos();
        }
}//GEN-LAST:event_nrodocumento2MouseClicked

    private void nrodocumento2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrodocumento2FocusLost

}//GEN-LAST:event_nrodocumento2FocusLost

    private void nrodocumento2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumento2KeyPressed
        this.Realiza_Opciones(evt);
}//GEN-LAST:event_nrodocumento2KeyPressed

    private void nrodocumento2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumento2KeyTyped
        // TODO add your handling code here:
        if (nrodocumento.getText().length() > 10) {
            evt.consume();
        }
}//GEN-LAST:event_nrodocumento2KeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Lista_Cargos();
}//GEN-LAST:event_jButton9ActionPerformed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        Realiza_Opciones(evt);

        switch (evt.getKeyCode()) {

            case 127:

                if (!this.jButton5.isEnabled()) {
                    int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproducto.getValueAt(jTable2.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {

                        int ultposi;
                        int fila = this.jTable2.getSelectedRow();
                        ultposi = ((Integer) this.jTable2.getValueAt(fila, 0)).intValue();
                        ultposi--;
                        tablaproducto.removeRow(fila);
                        cantidad--;

                        if (ultposi > 0) {
                            ReordenaTablaCargo(ultposi);
                        } else if (fila == 0) {
                            ReordenaTablaCargo(fila);
                        }

                        MiTotalCargo();
                    }
                }

                break;

            case 117:

//                agregandoProductos();
                break;


            case 10:
                if (!this.jButton6.isEnabled()) {
                    Verifica_Cantidad();
                    MiTotal();
                }
                break;

        }
}//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased

}//GEN-LAST:event_jTable2KeyReleased

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped

}//GEN-LAST:event_jTable2KeyTyped

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if (jTable1.getRowCount() > 0) {
            
            /*for (int z = 0; z <= jTable1.getRowCount(); z++) {
                String CodPrd = jTable1.getValueAt(z, 1).toString();
                JOptionPane.showMessageDialog(this, CodPrd);                
            }*/
            agregandoProductosCargo();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "POR FAVOR DEBE INGRESAR UN DESCARGO", "NortFarma", JOptionPane.ERROR_MESSAGE);
        }

}//GEN-LAST:event_jButton11ActionPerformed

    

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        BuscarMovimientos objform = new BuscarMovimientos(ventana, this, true);
        objform.pack();
        objform.setVisible(true);
}//GEN-LAST:event_jButton15ActionPerformed

    private void jPanel13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel13KeyPressed
        Realiza_Opciones(evt);
}//GEN-LAST:event_jPanel13KeyPressed

    private void observ1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observ1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_observ1KeyPressed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Lista_Cargos();
}//GEN-LAST:event_jButton7ActionPerformed
    private void Muestra_Proveedores() {
        formproveedor = new FormProveedores(objetoventana);
        formproveedor.pack();
        formproveedor.setVisible(true);

        idproveedor = formproveedor.getIdproveedor();
        String nombre = formproveedor.getNombre_Proveedor();

        if (idproveedor != null) {
            jTextField2.setText(idproveedor);
            jTextField3.setText(nombre);
        }

    }

    private void Muestra_Informacion() {


        String doc = nrodocumento.getText().trim().toUpperCase();
        String alm = jComboBox2.getSelectedItem().toString().trim();
        String idtipmov;

        if (inventar) {
            idtipmov = listaTipoMovimiento.get(this.operaciones.getSelectedIndex() - 1).getId_TipoMovimiento();
        } else {
            idtipmov = listaTipoMovimiento.get(this.lasboticas2.getSelectedIndex() - 1).getId_TipoMovimiento();
        }

        if (alm.compareToIgnoreCase("ALMACEN") == 0) {
            alm = "A";
        } else {
            alm = "M";
        }

        Movimientos p = new Movimientos(idtipmov, doc, alm);
        Movimientos entidad = log.Find_Descargos_Inv(idbotica, p);

        if (entidad != null) {
            for (Integer i = 0; i < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(i);
            }

            fechaDocumento.setDate(entidad.getFecha_Documento());
            fechaRecepcion.setDate(entidad.getFecha_Documento());
            lblFecha.setText(entidad.getFecha_Registro().toString());
            jLabel8.setVisible(true);
            jLabel8.setText("Descargo Realizado Por : " + entidad.getResponsable());
            Mostrar_Detalle(p);
            MiTotal();
            jButton3.setEnabled(false);
            GenerarGuia.setEnabled(false);
        }

    }

    private void Mostrar_Detalle(Movimientos p) {

        List<Movimientos> lista_Deta = log.Find_Descargos__Detalle_Inv(idbotica, p);

        for (int i = 0; i < lista_Deta.size(); i++) {

            Object[][] data = {
                {
                    i + 1,
                    lista_Deta.get(i).getId_Producto(),
                    lista_Deta.get(i).getDescripcion(),
                    lista_Deta.get(i).getId_Laboratorio(),
                    lista_Deta.get(i).getCantidad_Pedida(),
                    lista_Deta.get(i).getPrecio_Venta_Final(),
                    lista_Deta.get(i).getTotal(),
                    0,
                    0
                }};

            modeloTabla.addRow(data[0]);

        }
        this.jTable1.requestFocus();
        jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);

    }

    private void Lista_Descargos() {

        if (listaTipoMovimiento.size() > 0) {

            if (inventar) {
                if (operaciones.getSelectedIndex() > 0) {
                    consulta = true;
                    String idtipmov = listaTipoMovimiento.get(this.operaciones.getSelectedIndex() - 1).getId_TipoMovimiento();
                    String alm = this.jComboBox2.getSelectedItem().toString().trim();

                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.operaciones.getSelectedItem().toString());
                    obj.pack();
                    obj.setVisible(true);

                    String midocumento = obj.getDocumento();
                    String mialm = obj.getAlm();

                    if (midocumento != null || !midocumento.isEmpty()) {
                        nrodocumento.setText(midocumento);
                        if (mialm.charAt(0) == 'M') {
                            jComboBox2.setSelectedIndex(0);
                        } else if (mialm.charAt(0) == 'A') {
                            jComboBox2.setSelectedIndex(1);
                        }
                        Muestra_Informacion();

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }


            } else {

                if (lasboticas2.getSelectedIndex() > 0) {

                    consulta = true;
                    String idtipmov = listaTipoMovimiento.get(this.lasboticas2.getSelectedIndex() - 1).getId_TipoMovimiento();
                    String alm = this.jComboBox2.getSelectedItem().toString().trim();

                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.lasboticas2.getSelectedItem().toString());
                    obj.pack();
                    obj.setVisible(true);

                    String midocumento = obj.getDocumento();
                    String mialm = obj.getAlm();

                    if (!midocumento.isEmpty()) {

                        nrodocumento.setText(midocumento);

                        if (mialm.charAt(0) == 'M') {
                            jComboBox2.setSelectedIndex(0);
                        } else if (mialm.charAt(0) == 'A') {
                            jComboBox2.setSelectedIndex(1);
                        }

                        Muestra_Informacion();

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenerarGuia;
    private org.jdesktop.swingx.JXDatePicker fchaDocto;
    private org.jdesktop.swingx.JXDatePicker fchaRecepcion;
    private org.jdesktop.swingx.JXDatePicker fechaDocumento;
    private org.jdesktop.swingx.JXDatePicker fechaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox lasboticas1;
    private javax.swing.JComboBox lasboticas2;
    private javax.swing.JComboBox lasboticas5;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JTextField nrodocumento;
    private javax.swing.JTextField nrodocumento2;
    private javax.swing.JTextArea observ;
    private javax.swing.JTextArea observ1;
    private javax.swing.JComboBox operaciones;
    private javax.swing.JComboBox operaciones2;
    private javax.swing.JComboBox tipoAlmacen;
    // End of variables declaration//GEN-END:variables
}
