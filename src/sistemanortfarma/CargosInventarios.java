/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CargosInventarios1.java
 *
 * Created on 30/01/2012, 11:23:45 AM
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
import entidad.Proveedor;
import entidad.TipoMovimiento;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXDatePicker;
import sistemanortfarma.FormVtasAnul;
import CapaLogica.LogicaVenta;

/**
 *
 * @author Miguel Gomez S.
 */
public class CargosInventarios extends javax.swing.JInternalFrame {

    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento = new ArrayList<TipoMovimiento>();
    List<Proveedor> listaProveedor;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Guias> listguias;
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    LogicaVenta objVentas = new LogicaVenta();
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    Integer totalLetras = 0;
    Integer a = 0;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    ModeloTabla tablaproducto;
    private String idbotica;
    Inventarios objinventar;
    private int unidad, fraccion;
    int cantidad = 1;
    private int stockempaque;
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    MuestraVentana objetoventana = new MuestraVentana();
    private boolean consulta = false;
    LogicaMovimientos log = new LogicaMovimientos();
    private boolean inventar = true;
    private String IdProveedor;
    private String IdVenta;
    private String fechavta;
    private String idcajero;
    boolean oculta;
    FormProveedores formproveedor;
    FormVentasAnuladas formventasanuladas;
    Movimientos ObjMov;

    JFrame ventana;  

    /** Creates new form CargosInventarios1 */
    public CargosInventarios(Inventarios obj) {
        initComponents();
        objinventar = obj;
        idbotica = objinventar.getIdbotica();
        tablaproducto = new ModeloTabla();
        AparienciaTabla();
        construirGuia();
        cargarBoticas2();
        jLabel12.setVisible(false);
        MuestraFecha();
        jTextField2.enable(false);
        jTextField3.enable(false);
        jButton7.setEnabled(false);
        verificaAlmacenes();
        oculta = false;
        jPanel2.requestFocus();
        jTextField5.setVisible(false);
        jButton9.setVisible(false);        
        

    }

    private void MuestraFecha() {
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        fchaDocto.setDate(objFechaHora.RetornaFecha());
        fchaRecepcion.setDate(objFechaHora.RetornaFecha());
    }

    private void colocar() {
        jTextField2.setText("ZZZZ");
        jTextField3.setText("POR CLASIFICAR");
    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void construirGuia() {

        tablaproducto.addColumn("Nº");
        tablaproducto.addColumn("CODIGO");
        tablaproducto.addColumn("PRODUCTO");
        tablaproducto.addColumn("LAB");
        tablaproducto.addColumn("TOTAL");
        tablaproducto.addColumn("PRECIO");
        tablaproducto.addColumn("MONTO");
        tablaproducto.addColumn("PVP");
        tablaproducto.addColumn("DSCTO");

        jTable1.setModel(tablaproducto);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(270);
        columnaTabla.setMinWidth(270);
        columnaTabla.setMaxWidth(270);


        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(70);
        columnaTabla.setMinWidth(70);
        columnaTabla.setMaxWidth(70);

        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);

        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
    }

    private void verificaAlmacenes() {

        if (objBoticas.VerificaPoseeAlmacenes(this.idbotica)) {
            tipoAlmacen.setEnabled(true);
        } else {
            tipoAlmacen.setEnabled(false);
        }
    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
    }

    private void cargarBoticas2() {

        try {

            Integer BoticaOperando = 0;
            listaboticas2 = objBoticas.DescripcionBoticaDefault();
            lasboticas1.addItem(listaboticas2.get(0).getId_Botica());
            //RECUPERA_ROL
            int elRolcito = objPersonal.RecuperaRol(objOpcionesMenu.getIdpersonal_botica());

            if (objBoticas.VerificaMostrarOtraBotica(elRolcito, 1) == 1) {
                /*Si es Controlado por Rol - Es cargo*/
                lasboticas.setVisible(false);
                operaciones.setVisible(true);
                jTextField4.setVisible(false);
                listaTipoMovimiento = objTipoMovimiento.ListarMovimientosPorRol(elRolcito, 1);
                operaciones.addItem("Seleccion Opcion");
                fchaRecepcion.setVisible(false);
                jLabel14.setVisible(false);
                jLabel2.setVisible(true);
                jLabel7.setVisible(false);
                oculta = false;

                for (Integer ao = 0; ao < listaTipoMovimiento.size(); ao++) {
                    operaciones.addItem(listaTipoMovimiento.get(ao).getDescripcionMovimiento());
                }
                /*Ahora cargos los movimientos por Rol*/
                /*Ahora cargos los movimientos por Rol*/
            } else {
                /*Si es Controlado por Boticas - Es cargo*/
                //System.out.println("Es de Boticas");
                lasboticas.setVisible(true);
                jLabel2.setVisible(false);
                operaciones.setVisible(false);
                nrodocumento.setEditable(true);
                inventar = false;
                /*Ahora cargos las Boticas*/
                listaboticas = objBoticas.ListarBoticasMiZona();
                lasboticas.addItem("Seleccione Opcion");
                cargarBoticas();

                for (Integer ao = 0; ao < listaboticas.size(); ao++) {
                    lasboticas.addItem(listaboticas.get(ao).getDescripcion());
                    listaTipoMovimiento.add(null);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cargarBoticas() {

        listaTipoMovimiento = objTipoMovimiento.ListarTipoMovimientoFiltradoDescargo(1);

        for (int i = 0; i < listaTipoMovimiento.size(); i++) {
            lasboticas.addItem(listaTipoMovimiento.get(i).getDescripcionMovimiento());
        }
    }

    private void Realiza_Opciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            if (!this.jButton6.isEnabled()) {
                agregandoProductos();
            }
        }
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {

            if (this.jTable1.getRowCount() == 0) {
                jButton6.setEnabled(false);
                lasboticas.setEnabled(true);
                jTextField4.setEnabled(true);
                nrodocumento.setEnabled(true);
                fchaDocto.setEnabled(true);
                operaciones.setEnabled(true);
                jLabel12.setVisible(false);
                jTextField2.enable(false);
                jTextField3.enable(false);
                jButton7.setEnabled(true);
                jTextField4.setVisible(true);
                consulta = false;
                limpiarTodo(true);
                MuestraFecha();
                colocar();
                jButton6.setEnabled(false);
                limpiarTodo(true);
                colocar();
                consulta = false;
                this.jTable1.requestFocus();
                this.jTextField4.setVisible(true);

            } else {

                int p = JOptionPane.showConfirmDialog(null, " Deseas Realizar un Nuevo Cargo ?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (p == JOptionPane.YES_OPTION) {
                    jButton6.setEnabled(false);
                    limpiarTodo(true);
                    colocar();
                    consulta = false;
                    this.jTable1.requestFocus();
                    this.jTextField4.setVisible(true);
                }
            }
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            if (!this.jButton6.isEnabled()) {
                GuardarCargo();
            }
        }
    }

    private void CerrarVentana() {
        if (this.jTable1.getRowCount() > 0) {

            int p = JOptionPane.showConfirmDialog(null, " Deseaa Salir ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                this.dispose();
                objinventar.marcacdo = false;
            }
        } else {
            this.dispose();
            objinventar.marcacdo = false;
        }
    }

    private void revisarFracciones() {

        Integer en = 0;
        List<Productos_Botica> lista = null;
        boolean error = false;


        for (int i = 0; i < jTable1.getRowCount(); i++) {
            en = 0;
            totalLetras = 0;
            lista = null;
            error = false;

            /*Primero Reviso los empaques*/
            lista = objProducto.RecuperEmpaque(jTable1.getValueAt(i, 1).toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());

            if (lista.get(0).getProducto().getEmpaque() == 0) {
                lista.get(0).getProducto().setEmpaque(1);
            }

            /*Reviso si hay caracteres extraños*/
            for (Integer largo = 0; largo < jTable1.getValueAt(i, 4).toString().length(); largo++) {
                if (((jTable1.getValueAt(i, 4).toString().substring(largo, largo + 1).charAt(0) >= 48) && (jTable1.getValueAt(i, 4).toString().substring(largo, largo + 1).charAt(0) <= 57)) || (jTable1.getValueAt(i, 3).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70)) {
                } else {
                    totalLetras++;
                }
            }

            /*Reviso si hay una fraccion*/
            for (Integer largo = 0; largo < jTable1.getValueAt(i, 4).toString().length(); largo++) {
                if (jTable1.getValueAt(i, 4).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70) {
                    en = 1;
                }
            }

            /*Valido si se puede realizar la operacion*/

            if ((lista.get(0).getProducto().getEmpaque() == 1) && (en == 1)) {
                jTable1.setValueAt("", i, 3);
                jTable1.editCellAt(i, 3);
                jTable1.setColumnSelectionInterval(3, 3);
                jTable1.setRowSelectionInterval(i, i);
                jTable1.addRowSelectionInterval(i, i);
                error = true;
                JOptionPane.showMessageDialog(null, "۩ El Producto " + jTable1.getValueAt(i, 1).toString() + " No Permite Cantidades en Fraccion ۩", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }

            if (totalLetras == 0) {
            } else {
                jTable1.setValueAt("", i, 3);
                jTable1.setColumnSelectionInterval(3, 3);
                jTable1.setRowSelectionInterval(i, i);
                jTable1.addRowSelectionInterval(i, i);
                error = true;
                JOptionPane.showMessageDialog(null, "1. Existen Caracteres extraños en el registro " + (i + 1) + " por favor verifique", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public double redondear(double numero, double ndecimal) {
        double factor = Math.pow(10, ndecimal);
        return (Math.round(numero * factor) / factor);
    }

    private void agregandoProductos() {

        jTable1.requestFocus();
        Integer encontrado = 0;
        Ventana obj = new Ventana();

        objrelew = new AgregaProductosCarDes(obj, idbotica, tipoAlmacen.getSelectedItem().toString(), 1,"0");
        objrelew.pack();
        objrelew.show(true);

        jTable1.requestFocus();

        if (encontrado == 0) {

            if (AgregaProductosCarDes.getReal().trim().length() > 0) {

                unidad = AgregaProductosCarDes.getUnidad();
                fraccion = AgregaProductosCarDes.getFraccion();

                if (unidad != -1 && fraccion != -1) {

                    String codpro = AgregaProductosCarDes.getIdproducto();
                    int empaque1 = objProducto.Recupera_Empaque(codpro);

                    if (unidad == 0 && fraccion == 0) {
                        JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (empaque1 == 0 && fraccion > 0) {
                            JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Object[][] data = {
                                {
                                    cantidad,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosCarDes.getReal(), idbotica) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), this.idbotica)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }
                            };

                            cantidad++;
                            tablaproducto.addRow(data[0]);
                            this.jTable1.requestFocus();
                            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);
                            calculoMontos();
                            AgregaProductosCarDes.setReal("");

                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ya se agrego el Item, intente Nuevamente", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
        }

        if (jTable1.getRowCount() > 12) {
            int row = jTable1.getRowCount() - 1;
            Rectangle rect = jTable1.getCellRect(row, 0, true);
            jTable1.scrollRectToVisible(rect);
            jTable1.clearSelection();
            jTable1.setRowSelectionInterval(row, row);
            tablaproducto.fireTableDataChanged();
        }
        this.jTable1.requestFocus();
        jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);

    }

    public void AsigaProductos(List<Movimiento_Detalle> listaDetalle) {

        for (int i = 0; i < listaDetalle.size(); i++) {

            String micantidad;
            if (listaDetalle.get(i).getFraccion() == 0) {
                micantidad = String.valueOf(listaDetalle.get(i).getUnidad());
            } else {
                micantidad = String.valueOf(listaDetalle.get(i).getUnidad()) + "F" + listaDetalle.get(i).getFraccion();
            }

            Object[][] data = {
                {
                    cantidad,
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

            cantidad++;
            tablaproducto.addRow(data[0]);

        }

        jTable1.setModel(tablaproducto);
        MiTotal();

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

    private void MiTotal() {
        double total = 0;

        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            total = total + Double.parseDouble(jTable1.getValueAt(i, 6).toString());
        }

        BigDecimal bd3 = new BigDecimal(total);
        bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd3.doubleValue();

        this.jTextField1.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    private void calculoMontos(int i) {

        try {

            List<Productos_Botica> lista = objProducto.RecuperEmpaque(jTable1.getValueAt(i, 0).toString(), listaboticas2.get(this.lasboticas1.getSelectedIndex()).getId_Botica());

            if (lista.get(0).getProducto().getEmpaque() == 0) {
                lista.get(0).getProducto().setEmpaque(1);
            }

            int enterito = objFechaHora.RecuperaEntero(jTable1.getValueAt(i, 3).toString());
            int fraccionado = objFechaHora.RecuperaFraccion(jTable1.getValueAt(i, 3).toString());
            double precio = objProducto.RecuperaPrecio(jTable1.getValueAt(i, 0).toString(), idbotica) * (100 - objProducto.Recupera_Descuento_Producto(jTable1.getValueAt(i, 0).toString(), OpcionesMenu.getIdbotica())) / 100;
            jTable1.setValueAt(redondear(precio / lista.get(0).getProducto().getEmpaque() * (enterito * lista.get(0).getProducto().getEmpaque() + fraccionado), 2), i, 5);

        } catch (Exception ex) {
        }

    }

    private String recuperaProximoIdInventarios() {

        String casadelmal = "";

        if (inventar) {
            casadelmal = objGuias.GeneraNuevoNumero(this.operaciones.getItemAt(operaciones.getSelectedIndex()).toString(), tipoAlmacen.getSelectedItem().toString());
        }

        return casadelmal;
    }

    private String recuperaIdTipoOperacion(){
        String idoperacion = "";

        if (inventar) {
            idoperacion = objGuias.RecuperaIdTipoOperacion(this.operaciones.getItemAt(operaciones.getSelectedIndex()).toString());
        }

        return idoperacion;
    }

    private void limpiarTodo(Boolean valor) {

        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(OpcionesMenu.getIdpersonal_botica()), 1) == 1) {
            operaciones.setSelectedIndex(0);
        } else {
            lasboticas.setSelectedIndex(0);
        }

        observaciones.setText("");
        observaciones.setEnabled(valor);
        nrodocumento.setText("");
        this.jTextField4.setText("");

        /*  System.out.println("tablaproducto.getRowCount():"+tablaproducto.getRowCount());*/

        for (Integer i = 0; i < tablaproducto.getRowCount();) {
            tablaproducto.removeRow(i);
        }

        jButton1.setEnabled(valor);
        jButton4.setEnabled(valor);
        jButton3.setEnabled(!valor);
        jButton2.setEnabled(valor);
        jButton8.setEnabled(valor);
        this.jTextField1.setText("");

    }

    private void imprimirCargo() {

        try {

            FileOutputStream os = new FileOutputStream("LPT1:");
            PrintStream ps = new PrintStream(os);
            ps.println("prueba de impresion realizada");
            ps.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void validarCantidadProductos() {
        totalLetras = 0;

        for (a = 0; a < tablaproducto.getRowCount(); a++) {

            for (a = 0; a < tablaproducto.getRowCount(); a++) {
                try {
                    for (Integer largo = 0; largo < tablaproducto.getValueAt(a, 3).toString().length(); largo++) {
                        if ((tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).charAt(0) >= 65) && (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).charAt(0) >= 65)) {
                            totalLetras++;
                            if (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).toUpperCase().charAt(0) == 70) {

                                try {
                                    if ((tablaproducto.getValueAt(a, 3).toString().substring(largo + 1, largo + 2).toUpperCase().charAt(0) >= 49) && (tablaproducto.getValueAt(a, 3).toString().substring(largo, largo + 1).toUpperCase().charAt(0) >= 57)) {
                                        //Solo si el q le precede al F es un numero
                                        totalLetras--;
                                    }
                                } catch (Exception Ex) {
                                    System.out.println(Ex.getMessage());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    totalLetras++;
                }
            }
        }
    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }
    private String[] ArrayBoticas = {};

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lasboticas1 = new javax.swing.JComboBox();
        lasboticas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        nrodocumento = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tipoAlmacen = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        operaciones = new javax.swing.JComboBox();
        jTextField5 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fchaDocto = new org.jdesktop.swingx.JXDatePicker();
        jLabel14 = new javax.swing.JLabel();
        fchaRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CargosInventarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Operacion"));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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

        jButton4.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setFocusable(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setFocusable(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setFocusable(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jLabel4.setName("jLabel4"); // NOI18N

        jButton3.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)))
                .addGap(27, 27, 27)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setFocusable(false);
        jTextField1.setName("jTextField1"); // NOI18N

        jButton5.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setFocusable(false);
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        lasboticas1.setFont(resourceMap.getFont("tipoAlmacen.font")); // NOI18N
        lasboticas1.setModel(new javax.swing.DefaultComboBoxModel(ArrayBoticas));
        lasboticas1.setEnabled(false);
        lasboticas1.setFocusable(false);
        lasboticas1.setName("lasboticas1"); // NOI18N
        lasboticas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasboticas1ActionPerformed(evt);
            }
        });

        lasboticas.setEnabled(false);
        lasboticas.setFocusable(false);
        lasboticas.setName("lasboticas"); // NOI18N
        lasboticas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lasboticasItemStateChanged(evt);
            }
        });
        lasboticas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lasboticasKeyPressed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setFont(resourceMap.getFont("nrodocumento.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setToolTipText(resourceMap.getString("jTextField4.toolTipText")); // NOI18N
        jTextField4.setEnabled(false);
        jTextField4.setName("jTextField4"); // NOI18N

        nrodocumento.setEditable(false);
        nrodocumento.setBackground(resourceMap.getColor("nrodocumento.background")); // NOI18N
        nrodocumento.setFont(resourceMap.getFont("nrodocumento.font")); // NOI18N
        nrodocumento.setForeground(resourceMap.getColor("nrodocumento.foreground")); // NOI18N
        nrodocumento.setEnabled(false);
        nrodocumento.setName("nrodocumento"); // NOI18N
        nrodocumento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nrodocumentoMouseClicked(evt);
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

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setEnabled(false);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lasboticas, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jButton6)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setText(resourceMap.getString("text")); // NOI18N
        jTextField3.setName(""); // NOI18N

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setFont(resourceMap.getFont("lblFecha.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setName("jLabel8"); // NOI18N

        tipoAlmacen.setFont(resourceMap.getFont("tipoAlmacen.font")); // NOI18N
        tipoAlmacen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrador", "Almacen", "Trastienda" }));
        tipoAlmacen.setName("tipoAlmacen"); // NOI18N
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

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        operaciones.setFont(resourceMap.getFont("tipoAlmacen.font")); // NOI18N
        operaciones.setEnabled(false);
        operaciones.setName("operaciones"); // NOI18N
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

        jTextField5.setEditable(false);
        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setName("jTextField5"); // NOI18N

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tipoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setFocusable(false);
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        lblFecha.setFont(resourceMap.getFont("lblFecha.font")); // NOI18N
        lblFecha.setForeground(resourceMap.getColor("lblFecha.foreground")); // NOI18N
        lblFecha.setText(resourceMap.getString("lblFecha.text")); // NOI18N
        lblFecha.setFocusable(false);
        lblFecha.setName("lblFecha"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("lblFecha.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N

        fchaDocto.setEnabled(false);
        fchaDocto.setFormats(new String[] { "dd/M/yyyy" });
        fchaDocto.setName("fchaDocto"); // NOI18N
        fchaDocto.setNextFocusableComponent(jPanel4);
        fchaDocto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fchaDoctoActionPerformed(evt);
            }
        });
        fchaDocto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fchaDoctoKeyPressed(evt);
            }
        });

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        fchaRecepcion.setEnabled(false);
        fchaRecepcion.setFocusable(false);
        fchaRecepcion.setFormats(new String[] { "dd/M/yyyy" });
        fchaRecepcion.setName("fchaRecepcion"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        observaciones.setColumns(20);
        observaciones.setFont(resourceMap.getFont("observaciones.font")); // NOI18N
        observaciones.setRows(5);
        observaciones.setEnabled(false);
        observaciones.setName("observaciones"); // NOI18N
        observaciones.setPreferredSize(new java.awt.Dimension(224, 80));
        observaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                observacionesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(observaciones);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fchaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(72, 72, 72))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fchaDocto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(fchaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, 0, 819, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jButton5)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lasboticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasboticas1ActionPerformed
}//GEN-LAST:event_lasboticas1ActionPerformed

    private void operacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operacionesActionPerformed
        this.nrodocumento.setText(recuperaProximoIdInventarios());
        this.jTextField5.setText("");
        String oper = recuperaIdTipoOperacion();
        validar(oper);
        
}//GEN-LAST:event_operacionesActionPerformed

    private void validar(String oper){
       if (oper.equals("WF")){
           jTextField5.setText("");
           jTextField5.setVisible(true);
           jButton9.setVisible(true);
           
           tipoAlmacen.setEnabled(false);     //gino
           tipoAlmacen.setSelectedIndex(0);   //gino
       }else if (oper.equals("WA")){          //gino  
           jTextField5.setVisible(false);     //gino
           jButton9.setVisible(false);        //gino
           tipoAlmacen.setEnabled(true);      //gino
           //tipoAlmacen.setSelectedIndex(2);   //gino
           
       /*}else if (oper.equals("WB")){          //gino  
           jTextField5.setVisible(false);     //gino
           jButton9.setVisible(false);        //gino
           tipoAlmacen.setEnabled(true);      //gino
           //tipoAlmacen.setSelectedIndex(2);   //gino
        */   
       }else{           
           jTextField5.setVisible(false);
           jButton9.setVisible(false);
           
            tipoAlmacen.setEnabled(false);      //gino
            tipoAlmacen.setSelectedIndex(0);    //gino
           
       }

        
    }

    private void nrodocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrodocumentoFocusLost
}//GEN-LAST:event_nrodocumentoFocusLost

    private void nrodocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyTyped
        // TODO add your handling code here:
        if (nrodocumento.getText().length() > 10) {
            evt.consume();
        }
}//GEN-LAST:event_nrodocumentoKeyTyped

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

    private void Verifica_Cantidad() {

        int fila = this.jTable1.getSelectedRow();
        if (fila >= 0) {

            JOptionPane p = new JOptionPane();
            String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

            try {

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            cantidad1 = cantidad1.toUpperCase();

                            String idproducto = this.jTable1.getValueAt(fila, 1).toString().trim();
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidad(cantidad1);

                            if (unidad == 0 && fraccion == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                if (empaque1 == 0 && fraccion > 0) {
                                    JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (resul) {

                                        jTable1.setValueAt(cantidad1, fila, 4);
                                        calculoMontos();
                                        MiTotal();

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
            } catch (Exception ex) {
            }

        }
    }

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();

        try {

            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, tipoAlmacen.getSelectedItem().toString());

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

    private void GuardarCargo() {

        if (jTable1.getRowCount() > 0) {

            Integer enterito = 0;
            Integer fraccionado = 0;
            String proce = "";
            Integer tp = 0;
            Integer Seleccion = 0;
            boolean continua = true;
            Integer bandera = 0;


            if (this.inventar) {
                Seleccion = operaciones.getSelectedIndex();
            } else {
                Seleccion = lasboticas.getSelectedIndex();
            }

            if ((((Seleccion > 0) && (nrodocumento.getText().length() > 0)) && (jTable1.getRowCount() > 0) && jTextField2.getText().length() > 0)) {

                /*Cargando Cabecera*/
                Guias entidadGuia = new Guias();
                Producto entidadProductos = new Producto();

                if (operaciones.isVisible()) {
                    proce = operaciones.getItemAt(operaciones.getSelectedIndex()).toString();
                    tp = 1;
                    /*En el caso de que sea de Otra boticas*/
                } else {
                    /*En el caso de que sea de Oficina*/
                    proce = lasboticas.getItemAt(lasboticas.getSelectedIndex()).toString();
                    tp = 0;
                }


                String operacion;
                if (inventar) {
                    operacion = operaciones.getSelectedItem().toString();
                } else {
                    operacion = lasboticas.getSelectedItem().toString();
                }

                if (oculta) {
                    if (this.jTextField4.getText().trim().compareTo("") == 0) {
                        JOptionPane.showMessageDialog(null, "Porfavor Ingrese la Serie ", "Error", JOptionPane.ERROR_MESSAGE);
                        continua = false;
                        this.jTextField4.requestFocus();
                    }

                    

                }

                if (continua) {

                    String miserie = "";

                    if (jTextField4.getText().trim().compareTo("") != 0) {
                        miserie = jTextField4.getText().trim() + '-';
                    }

                    String docu = miserie + nrodocumento.getText().trim();

                    entidadGuia = new Guias(
                            lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString(),
                            tipoAlmacen.getItemAt(tipoAlmacen.getSelectedIndex()).toString(),
                            docu,
                            jTextField2.getText().trim(),
                            objFechaHora.ConvierteFecha(fchaDocto.getDate()),
                            OpcionesMenu.getIdpersonal_botica(),
                            objFechaHora.ConvierteFecha(fchaRecepcion.getDate()),
                            operacion, Double.parseDouble(jTextField1.getText().trim()));

                    entidadGuia.setNumero(nrodocumento.getText().trim());
                    Confirmar p = new Confirmar(objetoventana, " Deseas Guardar este Cargo ");
                    p.show(true);

                    if (p.getConfirmar() == 1) {

                        if (objGuias.guiaCargada(entidadGuia, tp, 1) == 0) {                            

                                Movimiento_Detalle detalle;
                                Producto producto;
                                List<Movimiento_Detalle> array = new ArrayList<Movimiento_Detalle>();
                                array.removeAll(array);

                                for (Integer totalProductos = 0; totalProductos < tablaproducto.getRowCount(); totalProductos++) {
                                    detalle = new Movimiento_Detalle();
                                    producto = new Producto();
                                    bandera = 0;
                                    fraccionado = 0;
                                    enterito = 0;
                                    enterito = objFechaHora.RecuperaEntero(tablaproducto.getValueAt(totalProductos, 4).toString());
                                    fraccionado = objFechaHora.RecuperaFraccion(tablaproducto.getValueAt(totalProductos, 4).toString());

                                    double pv = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 7).toString());
                                    double dcto = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 8).toString());
                                    double pvf = pv - ((pv * dcto) / 100);
                                    double total = Double.parseDouble(tablaproducto.getValueAt(totalProductos, 6).toString());

                                    producto.setIdProducto(tablaproducto.getValueAt(totalProductos, 1).toString());
                                    detalle.setId_Producto(producto);
                                    detalle.setUnidad(enterito);
                                    detalle.setFraccion(fraccionado);
                                    detalle.setPrecio_Venta(pv);
                                    detalle.setPrecio_Venta_Final(pvf);
                                    detalle.setDescuento(dcto);
                                    detalle.setTotal(total);
                                    array.add(detalle);

                                }

                                String operaci = recuperaIdTipoOperacion();
                                if (operaci.equals("WF")){
                                    if (!jTextField5.getText().equals("")){
                                        if (objGuias.VerificaAnulacion(this.jTextField5.getText().trim(),fechavta) == 1){
                                            boolean activa = true;
                                            String bot = lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString();
                                            String documento = this.jTextField5.getText();
                                            String Obs = "INVENTARIO";
                                            int idcaje = Integer.parseInt(idcajero);
                                            activa = objVentas.Activa(bot, documento, idcaje, Obs,fechavta);
                                            ObjMov = objGuias.CargarDetalleGuia1(entidadGuia, array, tp, 1, observaciones.getText().trim().toUpperCase(), tp, this.jTextField4.getText().trim(), this.jTextField5.getText().trim(),fechavta);
                                            
                                        }else{
                                            int reply = JOptionPane.showConfirmDialog(null, "El número de interno elegido esta activo, Desea Anularlo", "NORTFARMA", JOptionPane.YES_NO_OPTION);
                                            boolean resul = true;
                                            if (reply == JOptionPane.YES_OPTION) {
                                                String bot = lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString();
                                                String documento = this.jTextField5.getText();
                                                String Obs = "INVENTARIO";
                                                int idcaje = Integer.parseInt(idcajero);

                                                resul = objVentas.Anula(bot, documento, idcaje, Obs);
                                                ObjMov = objGuias.CargarDetalleGuia1(entidadGuia, array, tp, 1, observaciones.getText().trim().toUpperCase(), tp, this.jTextField4.getText().trim(), this.jTextField5.getText().trim(),fechavta);
                                            }else{
                                                jTextField5.setText("");
                                            }
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(this, "Debe Ingresar un Interno","NORTFARMA",JOptionPane.ERROR_MESSAGE);

                                    }
                                }else{
                                    //Movimientos ObjMov = objGuias.CargarDetalleGuia(entidadGuia, array, tp, 1, observaciones.getText().trim().toUpperCase(), tp, this.jTextField4.getText().trim());
                                    ObjMov = objGuias.CargarDetalleGuia1(entidadGuia, array, tp, 1, observaciones.getText().trim().toUpperCase(), tp, this.jTextField4.getText().trim(), this.jTextField5.getText().trim(),fechavta);
                                }

                                if (ObjMov.getIderror() == 0) {
                                    limpiarTodo(false);
                                    cantidad = 1;
                                    jButton6.setEnabled(true);
                                    JOptionPane.showMessageDialog(null, "Cargo Realizado \n Numero de Documento Generado :  " + ObjMov.getNumero_Documento() + "", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                    lasboticas.setEnabled(false);
                                    jTextField4.setEnabled(false);
                                    nrodocumento.setEnabled(false);
                                    jButton6.setEnabled(false);
                                    operaciones.setEnabled(false);
                                } else {
                                    if (ObjMov.getIderror() == 1) {
                                        JOptionPane.showMessageDialog(null, " Lo sentimos Hubo un Problema al Realizar su Cargo ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (ObjMov.getIderror() == 2) {
                                            JOptionPane.showMessageDialog(null, " Lo sentimos No Cuenta con Stock del Producto \n " + ObjMov.getId_Producto() + " ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                            //}

                        } else {
                            JOptionPane.showMessageDialog(null, "Este Numero de Guia ya ha sido Cargado ! ", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        //}
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "2. Existen Datos incompletos, Porfavor Verificar!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe Ingresar al menos un Item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        Realiza_Opciones(evt);

        switch (evt.getKeyCode()) {

            case 127:

                if (!this.jButton6.isEnabled()) {
                    int reply = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Registro: " + (String) tablaproducto.getValueAt(jTable1.getSelectedRow(), 2), "NORTFARMA", JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {

                        int ultposi;
                        int fila = this.jTable1.getSelectedRow();
                        ultposi = ((Integer) this.jTable1.getValueAt(fila, 0)).intValue();
                        ultposi--;
                        tablaproducto.removeRow(fila);
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

            case 117:

                agregandoProductos();
                break;


            case 10:
                if (!this.jButton6.isEnabled()) {
                    Verifica_Cantidad();
                    MiTotal();
                }
                break;

        }
}//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
}//GEN-LAST:event_jTable1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        agregandoProductos();
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String opera = recuperaIdTipoOperacion();
        if (opera.equals("WF")){
            if (jTextField5.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe Ingresar un Interno","NORTFARMA",JOptionPane.ERROR_MESSAGE);
            }else{

                if (this.observaciones.getText().trim().compareTo("") == 0) {
                   JOptionPane.showMessageDialog(null, "Porfavor Ingrese una Observacion ", "Error", JOptionPane.ERROR_MESSAGE);
                   this.observaciones.requestFocus();
                }else{
                   GuardarCargo();
                }
            }

        }else{

            if (this.observaciones.getText().trim().compareTo("") == 0) {
                JOptionPane.showMessageDialog(null, "Porfavor Ingrese una Observacion ", "Error", JOptionPane.ERROR_MESSAGE);
                this.observaciones.requestFocus();
            }else{
                GuardarCargo();
            }
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton6.setEnabled(true);
        jLabel12.setVisible(false);
        jTextField2.enable(false);
        jTextField3.enable(false);
        jButton7.setEnabled(false);
        jButton6.setEnabled(false);
        lasboticas.setEnabled(false);
        jTextField4.setEnabled(false);
        nrodocumento.setEnabled(false);
        operaciones.setEnabled(false);
        jTextField2.setText("");
        jTextField3.setText("");
        limpiarTodo(false);
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (this.jTable1.getRowCount() == 0) {
            jButton6.setEnabled(false);
            fchaDocto.setEnabled(true);
            lasboticas.setEnabled(true);
            jTextField4.setEnabled(true);
            nrodocumento.setEnabled(true);
            operaciones.setEnabled(true);
            this.jLabel12.setVisible(false);
            this.jTextField2.enable(false);
            this.jTextField3.enable(false);
            this.jButton7.setEnabled(true);
            this.jTextField4.setVisible(true);
            consulta = false;
            limpiarTodo(true);
            MuestraFecha();
            colocar();

        } else {

            int p = JOptionPane.showConfirmDialog(null, " Deseas Realizar un Nuevo Cargo ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                jButton6.setEnabled(false);
                this.jLabel12.setVisible(false);
                this.jTextField2.enable(false);
                this.jTextField3.enable(false);
                this.jButton7.setEnabled(true);
                this.jTextField4.setVisible(true);
                lasboticas.setEnabled(true);
                jTextField4.setEnabled(true);
                nrodocumento.setEnabled(true);
                operaciones.setEnabled(true);
                consulta = false;
                limpiarTodo(true);
                MuestraFecha();
                colocar();

            }
        }

}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void operacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_operacionesKeyPressed
        this.Realiza_Opciones(evt);

    }//GEN-LAST:event_operacionesKeyPressed

    private void tipoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoAlmacenKeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_tipoAlmacenKeyPressed

    private void observacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observacionesKeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_observacionesKeyPressed

    private void lasboticasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lasboticasKeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_lasboticasKeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_jButton1KeyPressed

    private void nrodocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrodocumentoKeyPressed
        this.Realiza_Opciones(evt);
    }//GEN-LAST:event_nrodocumentoKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Lista_Cargos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        muestra_Proveedor();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void lasboticasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lasboticasItemStateChanged

        if (evt.getStateChange() == 2) {

            try {

                if (this.lasboticas1.getItemCount() > 0 && lasboticas.getSelectedIndex() > 0) {
                    int ind = lasboticas.getSelectedIndex();
                    if (ind <= this.listaboticas.size()) {
                        jTextField4.setVisible(true);
                        oculta = true;
                    } else {
                        jTextField4.setVisible(false);
                        oculta = false;
                    }
                }

                TipoMovimiento tipoMovi = listaTipoMovimiento.get(lasboticas.getSelectedIndex() - 1);

                if (tipoMovi != null) {
                    String idmovimirnto = listaTipoMovimiento.get(lasboticas.getSelectedIndex() - 1).getId_TipoMovimiento();
                    Movimientos movi = log.Devuelve_Movimeinto(idmovimirnto);
                    if (movi != null) {
                        String numero = movi.getNumero_Documento();
                        if (!numero.isEmpty()) {
                            jTextField4.setVisible(false);
                            nrodocumento.setEditable(false);
                            oculta = false;
                            nrodocumento.setText(numero);
                        } else {
                            jTextField4.setVisible(true);
                            nrodocumento.setEditable(true);
                            oculta = true;
                            nrodocumento.setText("");
                        }
                    } else {
                        nrodocumento.setText("");
                        nrodocumento.setEditable(true);
                    }
                } else {
                    jTextField4.setVisible(true);
                    nrodocumento.setEditable(true);
                    nrodocumento.setText("");
                }

            } catch (Exception ex) {
            }
        }
    }//GEN-LAST:event_lasboticasItemStateChanged

    private void fchaDoctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fchaDoctoActionPerformed
        if (!Valida_Fechas()) {
            JOptionPane.showMessageDialog(this, " La Fecha del Documento no Puede ser Mayor a la Fecha de Registro ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fchaDoctoActionPerformed

    private void fchaDoctoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fchaDoctoKeyPressed
    }//GEN-LAST:event_fchaDoctoKeyPressed

    private void nrodocumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nrodocumentoMouseClicked
        if (evt.getClickCount() % 2 == 0) {
            Lista_Cargos();
        }
    }//GEN-LAST:event_nrodocumentoMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        BuscarMovimientos objform = new BuscarMovimientos(ventana, this, true);
        objform.pack();
        objform.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        muestraVentasAnuladas();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tipoAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAlmacenActionPerformed
        // TODO add your handling code here:
        /*for (Integer i = 0; i < tablaproducto.getRowCount();) {
            tablaproducto.removeRow(i);
        }
        this.nrodocumento.setText(recuperaProximoIdInventarios());*/
        this.nrodocumento.setText(recuperaProximoIdInventarios());
    }//GEN-LAST:event_tipoAlmacenActionPerformed

    private boolean Valida_Fechas() {
        boolean valor = true;

        java.util.Date fecha1 = this.fchaDocto.getDate();
        java.util.Date fecha2 = objFechaHora.RetornaFecha();

        if (fecha1.compareTo(fecha2) > 0) {
            valor = false;
        }

        return valor;
    }

    private void muestraVentasAnuladas(){
        //        
        formventasanuladas = new FormVentasAnuladas(objetoventana,idbotica);
        formventasanuladas.pack();
        formventasanuladas.setVisible(true);

        IdVenta = formventasanuladas.getIdOperacion();
        fechavta = formventasanuladas.getFechaVta();
        idcajero = formventasanuladas.getIdCajero();

        if (IdVenta != null) {
            this.jTextField5.setText(IdVenta);

        }
    }
    
    private void Lista_Cargos() {

        if (listaTipoMovimiento.size() > 0) {

            String idtipmov = null;
            String alm = this.tipoAlmacen.getSelectedItem().toString().trim();

            if (inventar) {
                if (operaciones.getSelectedIndex() > 0) {

                    consulta = true;
                    idtipmov = listaTipoMovimiento.get(this.operaciones.getSelectedIndex() - 1).getId_TipoMovimiento();
                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.operaciones.getSelectedItem().toString());
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

                        Muestra_Informacion(idtipmov);
                        jButton4.setEnabled(false);
                        jButton1.setEnabled(false);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            } else {

                if (lasboticas.getSelectedIndex() > 0) {
                    consulta = true;

                    for (int i = 0; i < lasboticas.getItemCount(); i++) {
                        String mov = lasboticas.getSelectedItem().toString().trim();

                        for (int k = 0; k < listaTipoMovimiento.size(); k++) {

                            if (mov.compareTo(listaTipoMovimiento.get(k).getDescripcionMovimiento().trim()) == 0) {
                                idtipmov = listaTipoMovimiento.get(k).getId_TipoMovimiento();
                                i = lasboticas.getItemCount();
                                break;
                            }

                        }
                    }

                    alm = this.tipoAlmacen.getSelectedItem().toString().trim();
                    Lista_Desargos obj = new Lista_Desargos(this.idbotica, idtipmov, alm, this.lasboticas.getSelectedItem().toString());
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

                        Muestra_Informacion(idtipmov);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de Seleccionar un Tipo de  Movimiento Para la Busqueda", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    private void muestra_Proveedor() {
        formproveedor = new FormProveedores(objetoventana);
        formproveedor.pack();
        formproveedor.setVisible(true);

        IdProveedor = formproveedor.getIdproveedor();
        String nombre = formproveedor.getNombre_Proveedor();

        if (IdProveedor != null) {
            this.jTextField2.setText(IdProveedor);
            this.jTextField3.setText(nombre);
        }
    }

    private void Muestra_Informacion(String tipomov) {
        try {


            String doc = nrodocumento.getText().trim();
            String alm = tipoAlmacen.getSelectedItem().toString().trim();
            String idtipmov = tipomov;

            if (alm.compareToIgnoreCase("ALMACEN") == 0) {
                alm = "A";
            } else {
                alm = "M";
            }

            Movimientos p = new Movimientos(idtipmov, doc, alm);
            Movimientos entidad = log.Find_Descargos_Inv(idbotica, p);

            if (entidad != null) {
                for (Integer i = 0; i < tablaproducto.getRowCount();) {
                    tablaproducto.removeRow(i);
                }

                fchaDocto.setDate(entidad.getFecha_Documento());
                fchaRecepcion.setDate(entidad.getFecha_Documento());
                lblFecha.setText(entidad.getFecha_Registro().toString());
                this.jLabel12.setVisible(true);
                this.jLabel12.setText("Cargo Realizado Por : " + entidad.getResponsable());
                Mostrar_Detalle(p);
                MiTotal();
            }

        } catch (Exception ex) {
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

            tablaproducto.addRow(data[0]);

        }
        this.jTable1.requestFocus();
        jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);

    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker fchaDocto;
    private org.jdesktop.swingx.JXDatePicker fchaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox lasboticas;
    private javax.swing.JComboBox lasboticas1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField nrodocumento;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JComboBox operaciones;
    private javax.swing.JComboBox tipoAlmacen;
    // End of variables declaration//GEN-END:variables
}
