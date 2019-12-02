/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BuscaProducts.java
 *
 * Created on 06/09/2013, 08:12:59 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaLaboratorios;
import CapaLogica.LogicaProducto;
import entidad.ProductosPrecios;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author Miguel Gomez S.
 */
public class BuscarProductos extends javax.swing.JDialog {

    static ProductosPrecios productoPrecio = null;
    private static FormLaboratorios lab;
    private static FormGenericos gen;
    public static boolean espromo;
    static boolean seleccionaart = false;
    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listProdDetalle = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> listDatosBotiquin = new ArrayList<ProductosPrecios>();
    private List<ProductosPrecios> ListaPromociones = new ArrayList<ProductosPrecios>();
    private String idbotica, idgenerico, id_laboratorio;
    private String tipprecio = "01";
    int band = 0, totalproductos, posicion = 0, podecimal=4,podecimalPantalla=2, opconstock = 1;
    LogicaProducto objproducto = new LogicaProducto();
    LogicaLaboratorios objlaboratorio = new LogicaLaboratorios();
    LogicaBoticas objBoticas = new LogicaBoticas();
    Object[] datosDetalle = new Object[12];
    Object[] Detalle = new Object[7];
    private DefaultTableModel model, modeldetalle;
    TableColumnModel colModel, colModel_Detalle;
    TableColumn colu, colu_6, columnaTabla;
    JCheckBox checkPromocion = new JCheckBox();
    JFrame objetoventana;
    Inventarios objCruce;

    /** Creates new form BuscaProducts */
    public BuscarProductos(java.awt.Frame objeto, String opprecio) {
        super(objeto, true);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/magnifier.png")).getImage());
        setLocationRelativeTo(null);
        setSeleccionaart(false);
        podecimal = OpcionesMenu.getCantidadDecimales();
        podecimalPantalla= OpcionesMenu.getCantidadDecimalesPantalla();
        colModel_Detalle = jTable2.getColumnModel();
        tipprecio = opprecio;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        modeldetalle = (DefaultTableModel) jTable2.getModel();
        idbotica = AplicacionVentas.getIdbotica();
        colu = jTable1.getColumnModel().getColumn(7);
        colu_6 = jTable1.getColumnModel().getColumn(6);
        jCheckBox1.setSelected(true);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setText("");
        AgregaFiltros();
        AparienciaTabla();
        verificaAlmacenes();
        muestraProductos();
        jTextField1.requestFocus();
    }

    BuscarProductos(java.awt.Frame objeto, String opprecio, Inventarios Inv) {
        super(objeto, true);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/sistemanortfarma/resources/busyicons/magnifier.png")).getImage());
        setLocationRelativeTo(null);
        setSeleccionaart(false);
        podecimal = OpcionesMenu.getCantidadDecimales();
        podecimalPantalla= OpcionesMenu.getCantidadDecimalesPantalla();
        colModel_Detalle = jTable2.getColumnModel();
        tipprecio = opprecio;
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setModel(model);
        modeldetalle = (DefaultTableModel) jTable2.getModel();
        idbotica = Inventarios.getIdbotica();
        colu = jTable1.getColumnModel().getColumn(7);
        colu_6 = jTable1.getColumnModel().getColumn(6);
        jCheckBox1.setSelected(true);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setText("");
        AgregaFiltros();
        AparienciaTabla();
        verificaAlmacenes();
        muestraProductos();
        jTextField1.requestFocus();
    }

    public static boolean isSeleccionaart() {
        return seleccionaart;
    }

    public static void setSeleccionaart(boolean seleccionaart) {
        BuscarProductos.seleccionaart = seleccionaart;
    }

    public static boolean isEspromo() {
        return espromo;
    }

    public static void setEspromo(boolean espromo) {
        BuscarProductos.espromo = espromo;
    }

    public static ProductosPrecios getProductoPrecio() {
        return productoPrecio;
    }

    public static void setProductoPrecio(ProductosPrecios productoPrecio) {
        BuscarProductos.productoPrecio = productoPrecio;
    }

    private void AparienciaTabla() {
        try {
            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(tcenter);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(tcenter);            
            jTable2.getColumnModel().getColumn(3).setCellRenderer(tcenter);
            jTable2.getColumnModel().getColumn(4).setCellRenderer(tcenter);

            DefaultTableCellRenderer deracha = new DefaultTableCellRenderer();
            deracha.setHorizontalAlignment(SwingConstants.RIGHT);
            jTable1.getColumnModel().getColumn(7).setCellRenderer(deracha);
            jTable1.getColumnModel().getColumn(8).setCellRenderer(deracha);
            jTable2.getColumnModel().getColumn(6).setCellRenderer(deracha);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void verificaAlmacenes() {

        if (!AplicacionVentas.isEsAlmacen()) {
            columnaTabla = jTable1.getColumnModel().getColumn(5);
            columnaTabla.setPreferredWidth(0);
            columnaTabla.setMinWidth(0);
            columnaTabla.setMaxWidth(0);

            columnaTabla = jTable1.getColumnModel().getColumn(6);
            columnaTabla.setPreferredWidth(0);
            columnaTabla.setMinWidth(0);
            columnaTabla.setMaxWidth(0);
        }

        colu_6 = jTable1.getColumnModel().getColumn(7);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);

        colu_6 = jTable2.getColumnModel().getColumn(5);
        colu_6.setPreferredWidth(0);
        colu_6.setMinWidth(0);
        colu_6.setMaxWidth(0);
    }

    private void AgregaFiltros() {
        jComboBox2.addItem("Descripcion ");
        jComboBox2.addItem("Codigo de Producto");
        jComboBox2.addItem("Codigo de Barras");
        jButton1.setText("<html>F3 : Buscar <br> Producto</html>");
        jButton2.setText("<html>F4 : Promocion </html>");
        jButton3.setText("<html>F5 : <br> Sustitutos </html>");
    }

    private void muestraProductos() {

        double pv, desc = 0, pvx = 0;
        BigDecimal bd, bd1;
        int fraccion = 0;

        listaProductos = objproducto.ListarProductos("", idbotica, tipprecio, 0, 1);

        if (tipprecio.compareTo("01") == 0) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(0);
        }

        try {

            for (ProductosPrecios precio : listaProductos) {
                pv = precio.getPrecio_Venta();
                datosDetalle[0] = precio.getProductoBotica().getProducto().getIdProducto();
                datosDetalle[1] = precio.getProductoBotica().getProducto().getDescripcion();
                datosDetalle[2] = precio.getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                datosDetalle[3] = precio.getProductoBotica().getMostrador_Stock_Empaque();
                fraccion = precio.getProductoBotica().getMostrador_Stock_Fraccion();

                if (fraccion == 0) {
                    datosDetalle[4] = fraccion;
                } else {
                    datosDetalle[4] = "F" + fraccion;
                }

                bd = new BigDecimal(pv);
                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                pv = bd.doubleValue();

                datosDetalle[5] = precio.getProductoBotica().getAlmacen_Stock_Empaque();
                int almfrac = precio.getProductoBotica().getAlmacen_Stock_Fraccion();

                if (almfrac == 0) {
                    datosDetalle[6] = almfrac;
                } else {
                    datosDetalle[6] = "F" + almfrac;
                }

                datosDetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                desc = precio.getDescuento_Venta();
                pvx = pv - (pv * (desc / 100));

                bd1 = new BigDecimal(pvx);
                bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                pvx = bd1.doubleValue();

                datosDetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
                datosDetalle[9] = precio.getProductoBotica().gettemperatura();
                datosDetalle[10] = precio.getprecbotiquin();

                

                if (datosDetalle.toString().compareTo("") != 0) {
                    if (precio.getId_Producto_Grupo() > 0) {
                        checkPromocion.setSelected(true);
                    } else {
                        checkPromocion.setSelected(false);
                    }
                    colu.setCellEditor(new DefaultCellEditor(checkPromocion));
                    model.addRow(datosDetalle);
                }
            }

            totalproductos = jTable1.getRowCount();

        } catch (Exception ex) {
            System.out.println("muestraProductos " + ex.getMessage());
        }
    }

    private void BusquedaSensitiva() {
        int ind = jComboBox1.getSelectedIndex();
        String filtro;
        int cant;

        try {

            if (ind == 0) {
                //BUSCA POR DESCRIPCION DE PRODUCTO
                filtro = jTextField1.getText().trim() + '%';
                listaProductos.removeAll(listaProductos);
                if (jCheckBox1.isSelected()) {
                    opconstock = 1; //con stock
                } else {
                    opconstock = 0; //sin stock
                }

                LimpiatTabla();

                if (!String.valueOf(idgenerico).isEmpty()) {
                    listaProductos = objproducto.ListarProductos_Lab_Geneico(filtro, idbotica, tipprecio, idgenerico);
                    if (listaProductos.size() > 0) {
                        completaTabla(listaProductos);
                    }
                } else {
                    if (!String.valueOf(id_laboratorio).isEmpty()) {
                        listaProductos = objproducto.ListarProductos_PorLaboratorio(filtro, id_laboratorio, idbotica, tipprecio, opconstock);
                        if (listaProductos.size() > 0) {
                            completaTabla(listaProductos);
                        }
                    } else {
                        listaProductos = objproducto.ListarProductos(filtro, idbotica, tipprecio, ind, opconstock);
                        if (listaProductos.size() > 0) {
                            completaTabla(listaProductos);
                        }
                    }
                }
            } else {
                if (ind == 1) {//BUSCA POR CODIGO DE PRODUCTO
                    filtro = jTextField1.getText().trim() + '%';
                    cant = filtro.length();
                    if (cant != 0) {
                        listaProductos = objproducto.ListarProductos(filtro, idbotica, tipprecio, ind, opconstock);
                        completaTabla(listaProductos);
                    }
                } else {
                    if (ind == 2) {//BSUCA POR CODIGO DE BARRAS
                        filtro = jTextField1.getText().trim() + '%';
                        cant = filtro.length();
                        //if (cant >= 11) {
                        if (cant != 0) {
                            listaProductos = objproducto.ListarProductos(filtro, idbotica, tipprecio, ind, opconstock);
                            completaTabla(listaProductos);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("BusquedaSensitiva " + ex.getMessage());
        }

    }

    private void completaTabla(List<ProductosPrecios> listaProductos) {
        LimpiatTabla();
        double pv = 0;
        double desc = 0;
        double pvx = 0;
        int fraccion;

        for (int i = 0; i < listaProductos.size(); i++) {
            pv = listaProductos.get(i).getPrecio_Venta();
            datosDetalle[0] = listaProductos.get(i).getProductoBotica().getProducto().getIdProducto();
            datosDetalle[1] = listaProductos.get(i).getProductoBotica().getProducto().getDescripcion();
            datosDetalle[2] = listaProductos.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab();
            datosDetalle[3] = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Empaque();
            fraccion = listaProductos.get(i).getProductoBotica().getMostrador_Stock_Fraccion();

            if (fraccion == 0) {
                datosDetalle[4] = "";
            } else {
                datosDetalle[4] = "F" + fraccion;
            }

            BigDecimal bd = new BigDecimal(pv);
            datosDetalle[5] = listaProductos.get(i).getProductoBotica().getAlmacen_Stock_Empaque();
            int almfrac = listaProductos.get(i).getProductoBotica().getAlmacen_Stock_Fraccion();

            if (almfrac == 0) {
                datosDetalle[6] = almfrac;
            } else {
                datosDetalle[6] = "F" + almfrac;
            }

            datosDetalle[7] = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();

            desc = listaProductos.get(i).getDescuento_Venta();
            
            pvx = pv - (pv * (desc / 100));

            BigDecimal bd1 = new BigDecimal(pvx);

            datosDetalle[8] = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP).toPlainString();
            datosDetalle[9] = listaProductos.get(i).getProductoBotica().gettemperatura();
            datosDetalle[10] = listaProductos.get(i).getprecbotiquin();

            if (listaProductos.get(i).getId_Producto_Grupo() > 0) {
                checkPromocion.setSelected(true);
                datosDetalle[11] = true;
            } else {
                checkPromocion.setSelected(false);
                datosDetalle[11] = false;
            }

            colu.setCellEditor(new DefaultCellEditor(checkPromocion));
            model.addRow(datosDetalle);
            totalproductos = listaProductos.size();
        }

    }

    private void RealizaOpciones(KeyEvent evt) {
        try {

            if (evt.getKeyText(evt.getKeyCode()).compareTo("F11") == 0) {
                if (jCheckBox1.isSelected()) {
                    opconstock = 0;
                    jCheckBox1.setSelected(false);
                } else {
                    jCheckBox1.setSelected(true);
                }
                if (jTextField1.getText().trim().compareTo("") != 0) {
                    BusquedaSensitiva();
                    jTable1.changeSelection(0, 0, false, false);
                    jTextField1.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese un Nombre del Producto \n Para consultar su Stock  ", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                if (evt.getKeyText(evt.getKeyCode()).compareTo("F6") == 0) {
                    jTextField1.setText("");
                    idgenerico = "";
                    lab = new FormLaboratorios(objetoventana, this);
                    lab.pack();
                    lab.setVisible(true);
                } else {
                    if (evt.getKeyText(evt.getKeyCode()).compareTo("F2") == 0) {
                        jTextField1.setText("");
                        id_laboratorio = "";
                        gen = new FormGenericos(objetoventana, this);
                        gen.pack();
                        gen.setVisible(true);
                    } else {
                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
                            jTextField1.requestFocus();
                        } else {
                            if (evt.getKeyCode() == 27) {
                                CerrarVentana();
                            } else {
                                if (evt.getKeyText(evt.getKeyCode()).compareTo("F4") == 0) {
                                    ProductosPromocion();
                                } else {
                                    if (evt.getKeyText(evt.getKeyCode()).compareTo("F5") == 0) {
                                        MuestraDetalle();
                                    } else {
                                        if (evt.getKeyText(evt.getKeyCode()).compareTo("F8") == 0) {
                                            int filaseleccionada = jTable1.getSelectedRow();
                                            if (filaseleccionada > 0) {
                                                jLabel15.setText("PRODUCTOS EN CONCURSO DE" + jTable1.getValueAt(filaseleccionada, 1) + " : ");
                                            }
                                        } else {
                                            if (evt.getKeyText(evt.getKeyCode()).compareTo("F7") == 0) {
                                                jComboBox1.setFocusable(true);
                                                jComboBox1.requestFocus(true);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("RealizaOpciones " + ex.getMessage());
        }
    }

    private void MuestraDetalle() {
        if (jTable1.getRowCount() > 0) {
            int filaseleccionada = jTable1.getSelectedRow();
            try {
                if (filaseleccionada >= 0 && totalproductos >= filaseleccionada) {
                    if (filaseleccionada < totalproductos) {
                        jTextField2.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 8)));
                        jTextField4.setText(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getDescripcion());
                        jTextField8.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getMostrador_Stock_Empaque()));
                        jTextField5.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdFamilia().getDescripcion()));
                        idgenerico = String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdGenerico().getId_Generico());
                        if (idgenerico.compareTo("") != 0) {
                            MuestraDetalle(idgenerico);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("MuestraDetalle " + ex.getMessage());
            }
        }
    }

    private void MuestraDetalle(String idgenerico) {
        double desc, pvx, pv = 0;
        listProdDetalle.removeAll(listProdDetalle);

        try {

            LimpiatTabla2();
            listProdDetalle = objproducto.ListarProductos_Detalle(idgenerico, idbotica, tipprecio);

            for (int i = 0; i < listProdDetalle.size(); i++) {
                String descrip = listProdDetalle.get(i).getProductoBotica().getProducto().getDescripcion();
                Detalle[0] = listProdDetalle.get(i).getProductoBotica().getProducto().getIdProducto();
                Detalle[1] = descrip;
                Detalle[2] = listProdDetalle.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                Detalle[3] = listProdDetalle.get(i).getProductoBotica().getMostrador_Stock_Empaque();
                Detalle[4] = listProdDetalle.get(i).getProductoBotica().getMostrador_Stock_Fraccion();
                pv = listProdDetalle.get(i).getPrecio_Venta();

                BigDecimal bd = new BigDecimal(pv);
                bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                pv = bd.doubleValue();

                Detalle[5] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();

                desc = listProdDetalle.get(i).getDescuento_Venta();
                pvx = pv - (pv * (desc / 100));

                BigDecimal bd1 = new BigDecimal(pvx);
                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                pvx = bd1.doubleValue();

                Detalle[6] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                modeldetalle.addRow(Detalle);
            }

            if (listProdDetalle.size() > 0) {
                jLabel15.setText("SUSTITUTOS  DE " + jTable1.getValueAt(jTable1.getSelectedRow(), 1) + " : ");
            } else {
                jLabel15.setText("PRODUCTO SIN SUSTITUTOS ");
            }

        } catch (Exception ex) {
            System.out.println("ERROR METODO BUSCA PRODUCTOS " + ex.getMessage());
        }

    }

    private void ProductosPromocion() {
        if (jTable1.getRowCount() > 0) {
            int filaseleccionada = jTable1.getSelectedRow();
            int idgrupo;
            int idpromocion;
            try {

                if (filaseleccionada >= 0 && totalproductos >= filaseleccionada) {
                    if (filaseleccionada < totalproductos) {
                        idgrupo = listaProductos.get(filaseleccionada).getGrupo_Id();
                        idpromocion = listaProductos.get(filaseleccionada).getId_Promocion();
                        if (idgrupo > 0) {
                            MuestraDetalle(idgrupo, idpromocion);
                            jLabel15.setText("PROMOCIONES  DEL PRODUCTO " + jTable1.getValueAt(filaseleccionada, 1) + " : ");
                            jLabel12.setText(listaProductos.get(filaseleccionada).getDESCRIPPROMOCION());
                        } else {
                            jLabel15.setText("PRODUCTO " + jTable1.getValueAt(filaseleccionada, 1) + " NO TIENE PROMOCION");
                            LimpiatTabla2();
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("ProductosPromocion " + ex.getMessage());
            }
        }
    }

    private void MuestraDetalle(int idgrupo, int idpromocion) {

        double desc, pvx, pv = 0;
        ListaPromociones.removeAll(ListaPromociones);

        try {

            ListaPromociones = objproducto.ListarProductos_Promocion(idbotica, idgrupo, idpromocion);
            jLabel15.setText(ListaPromociones.get(ListaPromociones.size() - 1).getDESCRIPPROMOCION());
            LimpiatTabla2();

            if (ListaPromociones.size() > 0) {
                for (int i = 0; i < ListaPromociones.size(); i++) {
                    String descrip = ListaPromociones.get(i).getProductoBotica().getProducto().getDescripcion();
                    Detalle[0] = ListaPromociones.get(i).getProductoBotica().getProducto().getIdProducto();
                    Detalle[1] = descrip;
                    Detalle[2] = ListaPromociones.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab();
                    Detalle[3] = ListaPromociones.get(i).getProductoBotica().getMostrador_Stock_Empaque();
                    Detalle[4] = ListaPromociones.get(i).getProductoBotica().getMostrador_Stock_Fraccion();
                    pv = ListaPromociones.get(i).getPrecio_Venta();

                    BigDecimal bd = new BigDecimal(pv);
                    bd = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    pv = bd.doubleValue();

                    desc = ListaPromociones.get(i).getDescuento_Venta();
                    pvx = pv - (pv * (desc / 100));

                    BigDecimal bd1 = new BigDecimal(pvx);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    pvx = bd1.doubleValue();

                    Detalle[5] = bd.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    Detalle[6] = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP).toPlainString();
                    modeldetalle.addRow(Detalle);
                }

            } else {
                jLabel15.setText(" ESTE PRODUCTO NO TIENE PROMOCION ");
            }

        } catch (Exception ex) {
            System.out.println("MuestraDetalle " + ex.getMessage());
        }
    }

    private void RecuperaProducto() {

        if (jTable1.getRowCount() > 0) {
            int fila = jTable1.getSelectedRow();
            try {
                if (fila >= 0) {
                    double PVP = listaProductos.get(fila).getPrecio_Venta();
                    if (PVP != 0.0) {
                        productoPrecio = listaProductos.get(fila);
                        productoPrecio.setPVPX(Double.parseDouble(jTable1.getValueAt(fila, 8).toString()));
                        if (jTable1.getValueAt(fila, 11) == null) {
                            espromo = false;
                        } else {
                            espromo = Boolean.valueOf(jTable1.getValueAt(fila, 11).toString());
                        }
                        setEspromo(espromo);
                        setSeleccionaart(true);
                        setProductoPrecio(productoPrecio);
                        CerrarVentana();
                    } else {
                        JOptionPane.showMessageDialog(this, "LO SENTIMOS NO PUEDES AGREGAR UN PRODUCTO CON PRECIO 0.0 ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                System.out.println(" Error RecuperaProducto " + ex.getMessage());
            }
        }
    }

    private void RecuperaProducto1() {
        try {

            if (listProdDetalle.size() > 0) {
                int fila = jTable2.getSelectedRow();
                if (fila >= 0) {
                    double PVP = listProdDetalle.get(fila).getPrecio_Venta();
                    if (PVP != 0.0) {
                        productoPrecio = listProdDetalle.get(fila);
                        productoPrecio.setPVPX(Double.parseDouble(String.valueOf(jTable2.getValueAt(fila, 6))));
                        setSeleccionaart(true);
                        setProductoPrecio(productoPrecio);
                        CerrarVentana();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("RecuperaProducto1 " + ex.getMessage());
        }
    }

    public void AsignaProductosLaboratorios(String idlab, String nom) {
        jLabel1.setVisible(true);
        jLabel10.setVisible(true);
        jLabel10.setText(nom);
        id_laboratorio = idlab;
        BusquedaSensitiva();
    }

    public void AsignaProductosGenericos(String idgene, String nomgenerico) {
        jLabel11.setVisible(true);
        jLabel11.setText(nomgenerico);
        jCheckBox1.setSelected(false);
        idgenerico = idgene;
        BusquedaSensitiva();
    }

    private void CerrarVentana() {
        dispose();
    }

    private void LimpiatTabla() {
        int cant = jTable1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    private void LimpiatTabla2() {
        int cant = jTable2.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modeldetalle.removeRow(i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(BuscarProductos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descripcion", "Codigo", "Codigo de Barras" }));
        jComboBox1.setFocusable(false);
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setNextFocusableComponent(jTextField1);
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jCheckBox1.setFont(resourceMap.getFont("jCheckBox1.font")); // NOI18N
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setFocusable(false);
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setFocusable(false);
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setFocusable(false);
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setFocusable(false);
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setFocusable(false);
        jLabel11.setName("jLabel11"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Lab", "Und", "Fracc", "S-Alm-Und", "S-Alm-Fracc", "    PVP", "    PVPx", "Temperatura", "PrecBotiquin", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(280);
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
            jTable1.getColumnModel().getColumn(9).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
            jTable1.getColumnModel().getColumn(10).setResizable(false);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTable1.columnModel.title11")); // NOI18N
            jTable1.getColumnModel().getColumn(11).setResizable(false);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(11).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        }

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setFocusable(false);
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setFocusable(false);
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setBackground(resourceMap.getColor("jTextField4.background")); // NOI18N
        jTextField4.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setFocusable(false);
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setBackground(resourceMap.getColor("jTextField5.background")); // NOI18N
        jTextField5.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField5.setForeground(resourceMap.getColor("jTextField5.foreground")); // NOI18N
        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setFocusable(false);
        jTextField5.setName("jTextField5"); // NOI18N

        jTextField6.setEditable(false);
        jTextField6.setBackground(resourceMap.getColor("jTextField6.background")); // NOI18N
        jTextField6.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField6.foreground")); // NOI18N
        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setFocusable(false);
        jTextField6.setName("jTextField6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setFocusable(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setBackground(resourceMap.getColor("jTextField8.background")); // NOI18N
        jTextField8.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField8.setForeground(resourceMap.getColor("jTextField8.foreground")); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText(resourceMap.getString("jTextField8.text")); // NOI18N
        jTextField8.setFocusable(false);
        jTextField8.setName("jTextField8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setFocusable(false);
        jLabel9.setName("jLabel9"); // NOI18N

        jComboBox2.setBackground(resourceMap.getColor("jComboBox2.background")); // NOI18N
        jComboBox2.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jComboBox2.setForeground(resourceMap.getColor("jComboBox2.foreground")); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRECIO NORMAL", "PRECIO BOTIQUIN" }));
        jComboBox2.setEnabled(false);
        jComboBox2.setFocusable(false);
        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setFocusable(false);
        jLabel13.setName("jLabel13"); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setBackground(resourceMap.getColor("jTextField9.background")); // NOI18N
        jTextField9.setFont(resourceMap.getFont("jTextField4.font")); // NOI18N
        jTextField9.setForeground(resourceMap.getColor("jTextField9.foreground")); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText(resourceMap.getString("jTextField9.text")); // NOI18N
        jTextField9.setFocusable(false);
        jTextField9.setName("jTextField9"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setFocusable(false);
        jLabel14.setName("jLabel14"); // NOI18N

        jTextField10.setEditable(false);
        jTextField10.setBackground(resourceMap.getColor("jTextField10.background")); // NOI18N
        jTextField10.setFont(resourceMap.getFont("jTextField10.font")); // NOI18N
        jTextField10.setForeground(resourceMap.getColor("jTextField10.foreground")); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setFocusable(false);
        jTextField10.setName("jTextField10"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, 0, 158, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel15.foreground")); // NOI18N
        jLabel15.setFocusable(false);
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(resourceMap.getColor("jLabel12.foreground")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setFocusable(false);
        jLabel12.setName("jLabel12"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Laboratorio", "Und", "Fracc", "PVP", "PVPx"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(35);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(320);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(45);
            jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(45);
            jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
        }

        jButton1.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(22, 22));
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(20, 22));
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(20, 22));
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setFocusable(false);
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setForeground(resourceMap.getColor("jLabel17.foreground")); // NOI18N
        jLabel17.setFocusable(false);
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(resourceMap.getColor("jLabel18.foreground")); // NOI18N
        jLabel18.setFocusable(false);
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setFocusable(false);
        jLabel19.setName("jLabel19"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(658, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(jLabel16)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(418, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(126, 126, 126)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
}//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField1.requestFocus();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int filaseleccionada = jTable1.getSelectedRow();
        if (filaseleccionada >= 0) {
            jLabel15.setText("PROMOCIONES  DEL PRODUCTO " + jTable1.getValueAt(filaseleccionada, 1) + " : ");
            ProductosPromocion();
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int filaseleccionada = jTable1.getSelectedRow();
        if (filaseleccionada >= 0) {
            jLabel15.setText("SUSTITUTOS DE " + jTable1.getValueAt(filaseleccionada, 1) + " : ");
            MuestraDetalle();
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0) {
            jTable1.requestFocus();
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                jLabel11.setVisible(false);
                jLabel10.setVisible(false);
                jLabel10.setText("");
                id_laboratorio = "";
                idgenerico = "";
                jCheckBox1.setSelected(true);
                BusquedaSensitiva();
                jTable1.requestFocus();
                jTable1.getSelectionModel().setSelectionInterval(0, 0);
            }
        }

        RealizaOpciones(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //if(){
                RecuperaProducto();    
            //}else{

            //}
        } else {
            if (evt.getKeyCode() == 9) {
                jTable2.requestFocus();
                jTable2.getSelectionModel().setSelectionInterval(0, 0);
            } else {
                RealizaOpciones(evt);
            }
        }

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyText(evt.getKeyCode()).compareTo("Abajo") == 0 || evt.getKeyText(evt.getKeyCode()).compareTo("Arriba") == 0) {
            LimpiatTabla2();
            listProdDetalle.removeAll(listProdDetalle);
            listDatosBotiquin.removeAll(listDatosBotiquin);
            jLabel15.setText("");
            jLabel12.setText("");
            jLabel17.setText("");
            jLabel19.setText("");
        }        
        int Empaq = 0;
        double PrecioFrac = 0.0;
        int Ebotiquin = 0;
        
        int filaseleccionada = jTable1.getSelectedRow();
        try {
            if (filaseleccionada >= 0 && totalproductos >= filaseleccionada) {
                if (filaseleccionada < totalproductos) {
                    jTextField2.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 8)));
                    jTextField4.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 1)));
                    jTextField8.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 3)));
                    jTextField9.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 4)));
                    jTextField5.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdFamilia().getDescripcion()));
                    jTextField6.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdGenerico().getDescripcionGenerico()));
                    jTextField10.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getCodBarras()));
                    
                    String prd = String.valueOf(jTable1.getValueAt(filaseleccionada, 0));
                    String prebo = String.valueOf(jTable1.getValueAt(filaseleccionada, 10));
                    String prenormal = String.valueOf(jTable1.getValueAt(filaseleccionada, 8));
                    
                    double PB = Double.parseDouble(prebo);
                    double PN = Double.parseDouble(prenormal);
                    double PBlister = objproducto.Recupera_PrecioVenta(prd,7);
                    listDatosBotiquin = objproducto.ListarDatosBotiquin(idbotica, prd);
                    for (int i = 0; i < listDatosBotiquin.size(); i++) {
                        Empaq = listDatosBotiquin.get(i).getEmpaque(); 
                        Ebotiquin = listDatosBotiquin.get(i).getEsBotiquin(); 
                        //prevta = listDatosBotiquin.get(i).getPrecio_Venta(); 
                    }
                    
                    //if (PB > 0){
                    if (Empaq == 0){
                        Empaq = 1;
                    }
                    
                    if (jTable1.getValueAt(filaseleccionada, 11).toString().compareTo("true") == 0) {
                        ProductosPromocion();
                    } else {
                        ListaPromociones.removeAll(ListaPromociones);
                    }
                    
                    if (Ebotiquin > 0 || PBlister > 0){
                        PrecioFrac = PN / Empaq;
                        
                        BigDecimal bd = new BigDecimal(PrecioFrac);
                        bd = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        PrecioFrac = bd.doubleValue();
                        
                        BigDecimal bd1 = new BigDecimal(PB);
                        bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                        
                        //String msg1= "<html><h1>Sr(a): <font color='red'> Producto con precio especial de BOTIQUIN </font></h2><hr></html>\n";
                        // String msg2= "<html><h3>Ud. esta aperturando:<hr></html>\n";
                        
                        
                        jLabel19.setText("<html><h3><font color='red'>   Producto con precio especial de BOTIQUIN </font></h3><hr></html>");
                        jLabel15.setText("   "+String.valueOf(jTable1.getValueAt(filaseleccionada, 1)) + "           LAB:  " + String.valueOf(jTable1.getValueAt(filaseleccionada, 2)) + "           EMPAQUE :  " + Empaq);
                        jLabel12.setText("");
                        jLabel17.setText("   * Precio Normal : S/   " + String.valueOf(jTable1.getValueAt(filaseleccionada, 8)) + "        * Precio Empaque : S/   " + bd1 + "        * Precio Fracción : S/   " + bd);
                    }
                    
                    
                    
                }
            }
        } catch (Exception ex) {
        }


    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            RecuperaProducto1();
        } else {
            RealizaOpciones(evt);
        }

    }//GEN-LAST:event_jTable2KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        listDatosBotiquin.removeAll(listDatosBotiquin);
        jLabel15.setText("");
        jLabel12.setText("");
        jLabel17.setText("");
        jLabel19.setText("");
                
        int Empaq = 0;
        double PrecioFrac = 0.0;
        int Ebotiquin = 0;
        
        int filaseleccionada = jTable1.getSelectedRow();

        if (listaProductos.size() > 0 && filaseleccionada >= 0) {
            try {
                jTextField2.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 8)));
                jTextField4.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 1)));
                jTextField8.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 3)));
                jTextField9.setText(String.valueOf(jTable1.getValueAt(filaseleccionada, 4)));
                jTextField5.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdFamilia().getDescripcion()));
                jTextField6.setText(String.valueOf(listaProductos.get(filaseleccionada).getProductoBotica().getProducto().getIdGenerico().getDescripcionGenerico()));
                
                
                
                String prd = String.valueOf(jTable1.getValueAt(filaseleccionada, 0));
                String prebo = String.valueOf(jTable1.getValueAt(filaseleccionada, 10));
                String prenormal = String.valueOf(jTable1.getValueAt(filaseleccionada, 8));
                
                double PB = Double.parseDouble(prebo);
                double PN = Double.parseDouble(prenormal);
                
                listDatosBotiquin = objproducto.ListarDatosBotiquin(idbotica, prd);
                for (int i = 0; i < listDatosBotiquin.size(); i++) {
                    Empaq = listDatosBotiquin.get(i).getEmpaque();
                    Ebotiquin = listDatosBotiquin.get(i).getEsBotiquin();
                }
                
                if (Empaq == 0){
                    Empaq = 1;
                }
                
                if (jTable1.getValueAt(filaseleccionada, 11).toString().compareTo("true") == 0) {
                    ProductosPromocion();
                } else {
                    ListaPromociones.removeAll(ListaPromociones);
                }
                
                if (Ebotiquin > 0){
                    PrecioFrac = PN / Empaq;
                    
                    BigDecimal bd = new BigDecimal(PrecioFrac);
                    bd = bd.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                    PrecioFrac = bd.doubleValue();
                    
                    BigDecimal bd1 = new BigDecimal(PB);
                    bd1 = bd1.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                    
                    jLabel19.setText("<html><h3><font color='red'>   Producto con precio especial de BOTIQUIN </font></h3><hr></html>");
                    jLabel15.setText("   "+String.valueOf(jTable1.getValueAt(filaseleccionada, 1)) + "           LAB:  " + String.valueOf(jTable1.getValueAt(filaseleccionada, 2)) + "           EMPAQUE :  " + Empaq);
                    jLabel12.setText("");
                    jLabel17.setText("   * Precio Normal : S/   " + String.valueOf(jTable1.getValueAt(filaseleccionada, 8)) + "        * Precio Empaque : S/   " + bd1 + "        * Precio Fracción : S/   " + bd);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BuscarProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        RealizaOpciones(evt);
    }//GEN-LAST:event_jComboBox1KeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
