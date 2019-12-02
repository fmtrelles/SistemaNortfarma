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
import entidad.Proveedor;
import entidad.TipoMovimiento;
import entidad.Varios;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DescargosInventarios extends javax.swing.JInternalFrame {

    ModeloTabla modeloTabla;
    TableColumn columnaTabla = null;
    LogicaTipoMovimiento objTipoMovimiento = new LogicaTipoMovimiento();
    LogicaProveedor objProveedor = new LogicaProveedor();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    LogicaProducto objProducto = new LogicaProducto();
    List<TipoMovimiento> listaTipoMovimiento = new ArrayList<TipoMovimiento>();
    List<Proveedor> listaProveedor;
    List<Boticas> listaboticas;
    List<Boticas> listaboticas2;
    List<Varios> Stocks = new ArrayList<Varios>();
    Guias entidadGuias = new Guias();
    LogicaProducto objProductos = new LogicaProducto();
    Producto entidadProductos = new Producto();
    LogicaBoticas objBoticas = new LogicaBoticas();
    LogicaGuias objGuias = new LogicaGuias();
    String BoticaOrigen;
    Integer existeGuia = 0;
    Boolean valido = false;
    AgregaProductosCarDes objrelew;
    public static String ProductoRecibido;
    OpcionesMenu objOpcionesMenu;
    LogicaPersonal objPersonal = new LogicaPersonal();
    boolean pasaPorStock = true;
    private String idbotica;
    private int stockempaque;
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    private int unidad, fraccion;
    MuestraVentana objetoventana = new MuestraVentana();
    private boolean consulta = false;
    LogicaMovimientos log = new LogicaMovimientos();
    int cantidad = 1;
    Inventarios Oonjinventar;
    private boolean inventar = true;
    boolean oculta;
    FormProveedores formproveedor;
    private String idproveedor;
    int opcion;

    /** Creates new form DescargosInventarios1 */
    public DescargosInventarios(java.awt.Frame parent, Inventarios obj,int opc) {
        initComponents();
        idbotica = obj.getIdbotica();
        Oonjinventar = obj;
        modeloTabla = new ModeloTabla();
        construirGuia();
        cargarBoticas2();
        AparienciaTabla();
        verificaAlmacenes();
        MuestraFecha();
        this.jLabel8.setVisible(false);
        this.jTable1.requestFocus();
        this.jButton6.setEnabled(false);
        opcion =opc;
    }

    private void Defecto() {
        this.jTextField2.setText("ZZZZ");
        this.jTextField3.setText("POR CLASIFICAR");
    }

    private void MuestraFecha() {
        lblFecha.setText(objFechaHora.MysqlToJuliano(objFechaHora.RetornaFecha().toString()).toString());
        this.fechaDocumento.setDate(objFechaHora.RetornaFecha());
        this.fechaRecepcion.setDate(objFechaHora.RetornaFecha());
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
        } else {
            jComboBox2.setEnabled(false);
        }
    }

    private void cargarBoticas2() {

        listaboticas2 = objBoticas.DescripcionBoticaDefault();
        lasboticas1.addItem(listaboticas2.get(0).getId_Botica());
        //RECUPERA_ROL

        if (objBoticas.VerificaMostrarOtraBotica(objPersonal.RecuperaRol(objOpcionesMenu.getIdpersonal_botica()), 0) == 1) {
            /*Si es Controlado por Rol - Es cargo*/

            lasboticas2.setVisible(false);
            lasboticas2.addItem("0");  // gino
            operaciones.setVisible(true);
            jLabel6.setVisible(false);
            listaTipoMovimiento = objTipoMovimiento.ListarMovimientosPorRol(objPersonal.RecuperaRol(objOpcionesMenu.getIdpersonal_botica()), 0);
            operaciones.addItem("Seleccion Opcion");
            oculta = false;
            jLabel14.setVisible(false);
            for (Integer ao = 0; ao < listaTipoMovimiento.size(); ao++) {
                operaciones.addItem(listaTipoMovimiento.get(ao).getDescripcionMovimiento());
            }
            /*Ahora cargos los movimientos por Rol*/
            /*Ahora cargos los movimientos por Rol*/
        } else {
            /*Si es Controlado por Boticas - Es cargo*/
            this.jLabel12.setVisible(false);
            inventar = false;
            operaciones.setVisible(false);
            cargarBoticas();
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
            this.Defecto();
            Nuevo();
            consulta = false;
            this.jButton5.setEnabled(false);
            this.jLabel8.setVisible(false);
            this.jLabel8.setText("");
            operaciones.setEnabled(true);
            lasboticas2.setEnabled(true);
            nrodocumento.setEnabled(true);
            limpiarTodo(true);
            MuestraFecha();

        } else {
            int p = JOptionPane.showConfirmDialog(null, " Deseas Realizar un Nuevo Descargo ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (p == JOptionPane.YES_OPTION) {
                this.jLabel8.setText("");
                this.jButton5.setEnabled(false);
                this.jButton6.setEnabled(true);
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

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();


        try {
            String optTipMov = lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString();
            int G_opcion = 1; 
            String EsConsMost = objProductos.recTipoMOv(idproduc, idbotica, G_opcion,optTipMov,4);
            String G_tipomov = objProductos.recTipoMOv(jTextField1.getText(), idbotica, G_opcion,optTipMov,1); 
            if (EsConsMost.equals("0")){                
            
                if (G_tipomov.equals("DT")){

                    empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, "T");
                }else{
                    empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, jComboBox2.getSelectedItem().toString());
                }
            
            }else{
                empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, jComboBox2.getSelectedItem().toString());
            }

            

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
        String optTipMov = lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString();
        String Letra = "F";
        int contador = 0;
        int Rec_cantCB1 = 0;
        String Rec_cantCB ="";
        String EsConsMost ="";
        jTable1.requestFocus();
        
        Ventana obj = new Ventana();        
        objrelew = new AgregaProductosCarDes(obj, idbotica, jComboBox2.getSelectedItem().toString(),0,optTipMov);
        objrelew.pack();
        objrelew.show(true);
        

        if (AgregaProductosCarDes.getReal().length() > 0) {

            unidad = AgregaProductosCarDes.getUnidad();
            fraccion = AgregaProductosCarDes.getFraccion();

            if (unidad != -1 && fraccion != -1) {

                String codpro = AgregaProductosCarDes.getIdproducto();
                int empaque1 = objProducto.Recupera_Empaque(codpro);
                
                try {
                    EsConsMost = objProductos.recTipoMOv(codpro, idbotica, 1,optTipMov,4);
                } catch (SQLException ex) {
                    Logger.getLogger(DescargosInventarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (EsConsMost.equals("1")){
                    
                
                    Rec_cantCB = objProducto.Recupera_CantCB(codpro);                
                    String cadenaDondeBuscar = "F";                                        
                    boolean resultado = Rec_cantCB.contains(cadenaDondeBuscar);

                    if (resultado) {

                        if (unidad == 0){

                            while (Rec_cantCB.indexOf(cadenaDondeBuscar) > -1) {
                            Rec_cantCB = Rec_cantCB.substring(Rec_cantCB.indexOf(cadenaDondeBuscar)+cadenaDondeBuscar.length(),Rec_cantCB.length());
                            contador++;
                            }
                            Rec_cantCB1 = Integer.valueOf(Rec_cantCB);
                            Letra = Letra;
                            //unidad = fraccion;

                        }else{
                            unidad = 0;
                            fraccion = 0;
                        }

                    }else{
                        Rec_cantCB1 = Integer.valueOf(Rec_cantCB);
                        Letra = "";
                    }
                
                }else{
                    Rec_cantCB1 = 100000;
                }
                

                if (unidad == 0 && fraccion == 0) {
                    JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (fraccion <= Rec_cantCB1){   //gino
                        
                    
                    if (empaque1 == 0 && fraccion > 0) {
                        JOptionPane.showMessageDialog(this, " LO SENTIMOS ESTE PRODUCTO \n NO SE PUEDE AGREGAR EN FRACCION ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (VerificaStock(codpro)) {

                            Object[][] data = {
                                {
                                    cantidad,
                                    AgregaProductosCarDes.getReal(),
                                    AgregaProductosCarDes.getRealDes(),
                                    AgregaProductosCarDes.getRealLab(),
                                    AgregaProductosCarDes.getCantidad(),
                                    redondear(objProducto.RecuperaPrecio(AgregaProductosCarDes.getReal(), this.idbotica) * (100 - objProducto.Recupera_Descuento_Producto(AgregaProductosCarDes.getReal(), idbotica)) / 100, 2),
                                    0,
                                    AgregaProductosCarDes.getPvp(),
                                    AgregaProductosCarDes.getDescto()
                                }};

                            cantidad++;
                            modeloTabla.addRow(data[0]);
                            this.jTable1.requestFocus();
                            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);
                            calculoMontos();

                            if (modeloTabla.getRowCount() > 0) {
                                GenerarGuia.setEnabled(true);

                                AgregaProductosCarDes.setReal("");

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Lo Sentimos no Hay Stock Suficiente ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    }else{
                        JOptionPane.showMessageDialog(null, "Cantidad excede el límite (Max: "+Letra+Rec_cantCB+") ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                    }

                }


            } else {
                JOptionPane.showMessageDialog(null, "Porfavor Ingrese Datos Correctos", "Nortfarma", JOptionPane.ERROR_MESSAGE);
            }


            this.jTable1.requestFocus();
            jTable1.changeSelection(this.jTable1.getRowCount() - 1, 0, false, false);

        }

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
            } else {
                lasboticas2.setSelectedIndex(0);
            }

            observ.setEnabled(valor);
            observ.setText("");
            jTextField1.setText("");

            for (Integer i = 0; i < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(i);
            }

            jButton3.setEnabled(valor);
            GenerarGuia.setEnabled(valor);
            jButton2.setEnabled(valor);
            jButton4.setEnabled(!valor);
            jTextField1.setText("");
            nrodocumento.setText("");


        } catch (Exception ex) {
            System.out.println("LimpiarTOdo:" + ex.getMessage());
        }

    }

    private void cargarBoticas() {

        listaTipoMovimiento = objTipoMovimiento.ListarTipoMovimientoFiltradoDescargo(0);
        lasboticas2.addItem("Seleccione Opcion");

        for (int i = 0; i < listaTipoMovimiento.size(); i++) {
            lasboticas2.addItem(listaTipoMovimiento.get(i).getDescripcionMovimiento());
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
        GenerarGuia = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lasboticas1 = new javax.swing.JComboBox();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(DescargosInventarios.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
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

        GenerarGuia.setFont(resourceMap.getFont("jButton4.font")); // NOI18N
        GenerarGuia.setText(resourceMap.getString("GenerarGuia.text")); // NOI18N
        GenerarGuia.setEnabled(false);
        GenerarGuia.setName("GenerarGuia"); // NOI18N
        GenerarGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarGuiaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addComponent(GenerarGuia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GenerarGuia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
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

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setForeground(resourceMap.getColor("jTextField1.foreground")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

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
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lasboticas2, 0, 209, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lasboticas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nrodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblFecha)
                    .addComponent(jLabel11)
                    .addComponent(fechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

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
        
        int contador =0;
        String Letra ="F";
        int Rec_cantCB1 =0;
        String EsConsMost ="";
        boolean x = false;

        try {

            if (fila >= 0) {

                JOptionPane p = new JOptionPane();
                String cantidad1 = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);
                cantidad1 = cantidad1.trim().toUpperCase();

                if (cantidad1.length() > 0) {

                    if (cantidad1.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad1).isEmpty() || cantidad1 != null) {

                            String idproducto = this.jTable1.getValueAt(fila, 1).toString().trim();
                            String Rec_cantCB = objProducto.Recupera_CantCB(idproducto); 
                            EsConsMost = objProductos.recTipoMOv(idproducto, idbotica, 1,"",4);
                            int empaque1 = objProducto.Recupera_Empaque(idproducto);
                            boolean resul = VerificaCantidad(cantidad1);
                            
                            if (EsConsMost.equals("1")){
                            
                            String cadenaDondeBuscar = "F";                                        
                            boolean resultado = Rec_cantCB.contains(cadenaDondeBuscar);                    
                            if (resultado) {
                                
                                if (unidad == 0){

                                    while (Rec_cantCB.indexOf(cadenaDondeBuscar) > -1) {
                                    Rec_cantCB = Rec_cantCB.substring(Rec_cantCB.indexOf(cadenaDondeBuscar)+cadenaDondeBuscar.length(),Rec_cantCB.length());
                                    contador++;
                                    }
                                    while (cantidad1.indexOf(cadenaDondeBuscar) > -1) {
                                    cantidad1 = cantidad1.substring(cantidad1.indexOf(cadenaDondeBuscar)+cadenaDondeBuscar.length(),cantidad1.length());
                                    contador++;
                                    }
                                    Rec_cantCB1 = Integer.valueOf(Rec_cantCB);
                                    Letra = Letra;
                                    unidad = Integer.valueOf(cantidad1);
                                    x=true;
                                }else{
                                    unidad = 0;
                                    fraccion=0;
                                    x=false;
                                }
                            }else{
                                Rec_cantCB1 = Integer.valueOf(Rec_cantCB);
                                Letra = "";
                                x=false;
                            }
                            
                            }else{
                                Rec_cantCB1 = 100000;
                            }
                            
                            
                            
                            

                            if (unidad == 0 && fraccion == 0) {
                                JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR ALGUNA CANTIDAD ", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                
                                if (unidad <= Rec_cantCB1){   //gino
                                   if (x) {
                                        unidad = 0;
                                        fraccion = Integer.valueOf(cantidad1);
                                        cantidad1 = "F"+cantidad1;
                                    }

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
                                
                                }else{
                                JOptionPane.showMessageDialog(null, "Cantidad excede el límite (Max: "+Letra+Rec_cantCB+") ", "Nortfarma", JOptionPane.ERROR_MESSAGE);
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
                String CBprod="";
                //Integer contar=0;
                String G_tMovSeleccionado ="";
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

                                if (lasboticas2.getSelectedIndex() < 1) {
                                    JOptionPane.showMessageDialog(null, "Seleccione Movimiento a Realizar", "Nortfarma", JOptionPane.ERROR_MESSAGE);
                                    valido = 0;
                                } else {
                                    //System.out.println("Ya VALIDE TODO SAPO AHORA SI A GUARDAR");*/
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

                                Confirmar p = new Confirmar(objetoventana, "<html>Deseas Guardar este Descargo </html>");
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
                                    
                                    
                                    /*gino*/
                                    
                                    String EsConsMost = objProductos.recTipoMOv(modeloTabla.getValueAt(0, 1).toString(), idbotica, 1,lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString(),4);
                                    
                                    if (EsConsMost.equals("0")){                                        
                                    
                                        String G_tipomov = objProductos.recTipoMOv("", idbotica, 1,lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString(),1);
                                        if (G_tipomov.equals("DT")){                                                                                   
                                            G_tMovSeleccionado = "Trastienda";    
                                        }else{
                                            G_tMovSeleccionado = jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString();
                                        }
                                    }else{
                                        G_tMovSeleccionado = jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString();
                                    }
                                    
                                    /*fin gino*/
                                    
                                    entidadGuias = new Guias(
                                            lasboticas1.getItemAt(lasboticas1.getSelectedIndex()).toString(),                                            
                                            //jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).toString(),
                                            G_tMovSeleccionado,
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

                                        Movimientos ObjMov = objGuias.DescargarDetalleGuia(entidadGuias, array, observ.getText().trim().toUpperCase(),opcion);

                                        if (ObjMov.getIderror() == 0) {
                                            limpiarTodo(false);
                                            this.jButton5.setEnabled(true);
                                            cantidad = 1;
                                            JOptionPane.showMessageDialog(null, "Descargo Realizado \n Numero de Docmuento Generado :  " + ObjMov.getNumero_Documento() + "", "Nortfarma", JOptionPane.INFORMATION_MESSAGE);
                                            this.jButton6.setEnabled(false);
                                            operaciones.setEnabled(false);
                                            lasboticas2.setEnabled(false);
                                            nrodocumento.setEnabled(false);
                                            jButton5.setEnabled(false);
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

    private void GenerarGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarGuiaActionPerformed
        Integer contar=0;
        String G_tpmov ="";
        int sum = 0;
        String EsConsMost ="";
        
        if (this.observ.getText().trim().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "Porfavor Ingrese una Observacion ", "Error", JOptionPane.ERROR_MESSAGE);
            this.observ.requestFocus();
        }else{
            
            contar = modeloTabla.getRowCount();
            try {
                G_tpmov = objProductos.recTipoMOv("", idbotica, 1,lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString(),1);
            } catch (SQLException ex) {
                Logger.getLogger(DescargosInventarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (G_tpmov.equals("DT")){                
            
                if (contar > 1){
                
                for (Integer tProd = 0; tProd < modeloTabla.getRowCount(); tProd++) {                                        
                    try {
                        EsConsMost = objProductos.recTipoMOv(modeloTabla.getValueAt(tProd, 1).toString(), idbotica, 1,lasboticas2.getItemAt(lasboticas2.getSelectedIndex()).toString(),4);
                    } catch (SQLException ex) {
                        Logger.getLogger(DescargosInventarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        sum = sum + Integer.valueOf(EsConsMost);
                    }
                
                if (sum >0){
                    JOptionPane.showMessageDialog(this, "Hay Productos que son de Cosumo directo, no se pueden descargar");                    
                }else{
                    Guardar_Descargo();                    
                }
                
                }else{
                    Guardar_Descargo();
                }


            }else{
                Guardar_Descargo();                
            }
            
        
        
        }

}//GEN-LAST:event_GenerarGuiaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton5.setEnabled(true);
        limpiarTodo(false);
        jButton6.setEnabled(false);
        operaciones.setEnabled(false);
        lasboticas2.setEnabled(false);
        nrodocumento.setEnabled(false);
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
        if (evt.getStateChange() == 2) {
            try {
                for (Integer i = 0; i < modeloTabla.getRowCount();) {
                modeloTabla.removeRow(i);
                }
                String idmovimirnto = listaTipoMovimiento.get(lasboticas2.getSelectedIndex() - 1).getId_TipoMovimiento();
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

    }//GEN-LAST:event_lasboticas2ItemStateChanged
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
    private org.jdesktop.swingx.JXDatePicker fechaDocumento;
    private org.jdesktop.swingx.JXDatePicker fechaRecepcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JComboBox lasboticas1;
    private javax.swing.JComboBox lasboticas2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField nrodocumento;
    private javax.swing.JTextArea observ;
    private javax.swing.JComboBox operaciones;
    // End of variables declaration//GEN-END:variables
}