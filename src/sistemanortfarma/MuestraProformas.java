/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MuestraProformas1.java
 *
 * Created on 21/02/2012, 09:17:25 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaIGV;
import CapaLogica.LogicaProforma;
import entidad.Proforma;
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
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class MuestraProformas extends javax.swing.JInternalFrame {

    UsuarioClave ventUsuaClave;
    AplicacionVentas objpricipal;
    LogicaProforma logicaProforma = new LogicaProforma();
    MuestraVentana objetoventana = new MuestraVentana();
    TableColumnModel colModel;
    private DefaultTableModel tablaproforma;
    LogicaProforma onjproforma = new LogicaProforma();
    LogicaProforma objlogproforma;
    List<Proforma> listaproforma = new ArrayList<Proforma>();
    List<Proforma> lisdetalleproforma = new ArrayList<Proforma>();
    Object[] listadetalle = new Object[8];
    String filtro = "";
    //PARA LA TABLA PRODUCTOS PAGOS
    private DefaultTableModel tablaproducto;
    Object[] arrayproductos = new Object[8];
    TableColumnModel colModel1;
    List<Proforma> listproformaDetalle = new ArrayList<Proforma>();
    int totalproductos = 0;
    int fila;
    String usuario, clave;
    String idproforma;
    String idbotica;
    double subtotal = 0;
    double total = 0;
    private double IGV;
    LogicaIGV objlogicaIGV;
    private Double DA = 0.00;
    AplicacionVentas objventas;
    private double dsctoAdicional;

    /** Creates new form MuestraProformas1 */
    public MuestraProformas(AplicacionVentas obj) {
        initComponents();
        objventas = obj;
        colModel = jTable2.getColumnModel();
        colModel1 = tablapagos1.getColumnModel();
        tablaproforma = (DefaultTableModel) jTable2.getModel();
        tablaproducto = (DefaultTableModel) tablapagos1.getModel();
        idbotica = objpricipal.getIdbotica();
        AparienciaTabla();
        objlogproforma = new LogicaProforma();
        CargaDatos();
        jTabbedPane1.setEnabledAt(1, false);
        this.jTable2.getSelectionModel().setSelectionInterval(0, 0);
        jTextField1.requestFocus();
        this.jTextField1.requestFocus();
    }

    private void AparienciaTabla() {
        try {


            JTableHeader cabecera = new JTableHeader(jTable2.getColumnModel());

            JTableHeader cabecera1 = new JTableHeader(tablapagos1.getColumnModel());

            DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
            tcenter.setHorizontalAlignment(SwingConstants.CENTER);
            this.tablapagos1.getColumnModel().getColumn(2).setCellRenderer(tcenter);
            this.tablapagos1.getColumnModel().getColumn(3).setCellRenderer(tcenter);

            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);

            this.tablapagos1.getColumnModel().getColumn(4).setCellRenderer(tcr);
            this.tablapagos1.getColumnModel().getColumn(5).setCellRenderer(tcr);
            this.tablapagos1.getColumnModel().getColumn(6).setCellRenderer(tcr);
            this.tablapagos1.getColumnModel().getColumn(7).setCellRenderer(tcr);


        } catch (Exception ex) {
        }

    }

    private void LimpiatTabla() {
        int cant = tablapagos1.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproducto.removeRow(i);
            }
        }
    }

    private void LimpiatTabla1() {
        int cant = tablaproforma.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                tablaproforma.removeRow(i);
            }
        }
    }

    private void CargaDatos() {

        try {

            listaproforma = objlogproforma.Recupera_Proforma(filtro, idbotica);

            for (int i = 0; i < listaproforma.size(); i++) {
                arrayproductos[0] = i + 1;
                arrayproductos[1] = listaproforma.get(i).getId_Proforma();
                arrayproductos[2] = listaproforma.get(i).getNomCliente();
                arrayproductos[3] = listaproforma.get(i).getFecha_Registro();
                arrayproductos[4] = listaproforma.get(i).getDescripcionTipoPago();
                arrayproductos[5] = listaproforma.get(i).getDescripciontipoventa();
                arrayproductos[6] = listaproforma.get(i).getTotal();
                arrayproductos[7] = listaproforma.get(i).getNombrePersonal();

                tablaproforma.addRow(arrayproductos);

            }
            total = listaproforma.get(0).getTotal();
            subtotal = listaproforma.get(0).getSubTotal();

            BigDecimal bd1 = new BigDecimal(subtotal);
            bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);

            this.TxtMISubtotal.setText(String.valueOf(bd1));
            this.TxtMiTotal.setText(String.valueOf(total));

        } catch (Exception ex) {
            System.out.println("ERROR AL CARGAR" + ex.getMessage());
        }

    }

    private void DevuelveDatos(int fila) {

        try {
            idproforma = listaproforma.get(fila).getId_Proforma();
            totalproductos = listaproforma.size();

            this.jTextField21.setText(idproforma);
            this.TxtDNI.setText(listaproforma.get(fila).getDNI());
            this.TxtCliente.setText(listaproforma.get(fila).getNombre_RazonSocial());
            this.TxtRuc.setText(listaproforma.get(fila).getRUC_DNI());
            this.TxtCodigo.setText(String.valueOf(listaproforma.get(fila).getId_Cliente()));
            this.TxtTipopago.setText(String.valueOf(listaproforma.get(fila).getDescripcionTipoPago()));
            this.TxtTipoVenta.setText(String.valueOf(listaproforma.get(fila).getDescripciontipoventa()));
            this.TxtTotal.setText(String.valueOf(listaproforma.get(fila).getTotal()));
            this.TxtSubototal.setText(String.valueOf(listaproforma.get(fila).getSubTotal()));
            String vend = String.valueOf(listaproforma.get(fila).getNombrePersonal()) + " " + String.valueOf(listaproforma.get(fila).getApellidoPersonal());
            this.TxtVendedor.setText(vend);
            this.TxtDireccion.setText(String.valueOf(listaproforma.get(fila).getDireccion()));
            RecuperaDetalle(idproforma);

        } catch (Exception ex) {
        }

    }

    private void Columnaresize(String nomproducto) {
        TableColumn col1 = colModel1.getColumn(1);
        col1.setMaxWidth(nomproducto.length() * 12);
        col1.setMinWidth(nomproducto.length() * 12);
    }

    private void RecuperaDetalle(String idproforma) {
        try {
            listproformaDetalle = logicaProforma.Recupera_Detalle_Proforma(idproforma, idbotica);

            for (int i = 0; i < listproformaDetalle.size(); i++) {
                String idprodu = listproformaDetalle.get(i).getIdproducto();
                String descrip = listproformaDetalle.get(i).getDescipcionproducto();
                int unidad1 = listproformaDetalle.get(i).getUNIDAD();
                int fraccion1 = listproformaDetalle.get(i).getFRACCION();
                double pvp = listproformaDetalle.get(i).getPrecio_Venta();
                double descuento1 = listproformaDetalle.get(i).getDescuento();
                double pvx = listproformaDetalle.get(i).getPvx();
                double total1 = listproformaDetalle.get(i).getTotal();


                listadetalle[0] = i + 1;
                listadetalle[1] = descrip;
                listadetalle[2] = unidad1;
                listadetalle[3] = fraccion1;
                listadetalle[4] = pvp;
                listadetalle[5] = descuento1;
                listadetalle[6] = pvx;
                listadetalle[7] = total1;

                Columnaresize(descrip);
                tablaproducto.addRow(listadetalle);

            }
        } catch (Exception ex) {
            System.out.println("error" + ex.toString());
        }


    }

    private void EliminaProducto(int fila) {
        int filas = tablaproducto.getRowCount();

        if (filas > 0) {
            try {
                if (fila >= 0) {
                    //  int confirmado=JOptionPane.showInternalConfirmDialog(this, " DESEAS ELIMINAR EL PRODUCTO :  "+ tablapagos1.getValueAt(fila, 1)+ "  " , "Eliminar",  JOptionPane.YES_NO_OPTION);
                    Confirmar p = new Confirmar(objetoventana, "<html>DESEAS ELIMINAR EL PRODUCTO :  " + tablapagos1.getValueAt(fila, 1) + "  </html>");
                    p.show(true);

                    if (p.confirmar == 1) {
                        int ultposi;
                        ultposi = ((Integer) tablaproducto.getValueAt(fila, 0)).intValue();
                        ultposi--;

                        tablaproducto.removeRow(fila);
                        listproformaDetalle.remove(fila);

                        if (ultposi > 0) {
                            ReordenaTabla(ultposi);
                        } else if (fila == 0) {
                            ReordenaTabla(fila);
                        }

                        CalculaMontos1();
                    }
                }
            } catch (Exception ex) {
            }

        }

    }

    private void ReordenaTabla(int ultposi) {
        try {

            for (int i = ultposi; i < tablaproducto.getRowCount(); i++) {
                tablaproducto.setValueAt(ultposi + 1, ultposi, 0);
                ultposi++;
            }

        } catch (Exception EX) {
            JOptionPane.showMessageDialog(this, "ERROR AL REORDENAR TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void Modifica2(Double DAdicional) {
//ESTA MALLL


        Double dsctoTotal = 0.0;
        Double PrecioFinalConDsctoAdicional;
        Integer empa;
        Double parte1;
        Double parte2;
        Double parte3;
        Double parte4;

        Double precioventa;
        Double cantidad;
        Double precioDscto01;
        Double precioDscto02;
        Double PrecioVenta;
        Double precioxunidad;
        Double unidades;
        Double finals;


        for (int i = 0; i < tablapagos1.getRowCount(); i++) {

            empa = listproformaDetalle.get(i).getEmpaque();

            if (empa == 0) {
                empa = 1;
            }

            unidades = Double.valueOf(tablapagos1.getValueAt(i, 2).toString()) + Double.valueOf(tablapagos1.getValueAt(i, 3).toString()) / empa;
            PrecioVenta = Double.valueOf(tablapagos1.getValueAt(i, 4).toString());
            precioDscto01 = PrecioVenta * (100 - (Double.valueOf(tablapagos1.getValueAt(i, 5).toString()))) / 100;
            precioDscto02 = precioDscto01 * (100 - Double.valueOf(dsctoAdicional)) / 100;

            BigDecimal bd = new BigDecimal(precioDscto02);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            precioDscto02 = bd.doubleValue();

            finals = precioDscto02 * unidades;

            BigDecimal bd2 = new BigDecimal(finals);
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            finals = bd2.doubleValue();

            tablapagos1.setValueAt(precioDscto02, i, 6);
            tablapagos1.setValueAt(finals, i, 7);

            //System.out.println("PrecioFinalConDsctoAdicional:"+PrecioFinalConDsctoAdicional);;

        }

    }

    private void CapturaIGV() {
        try {
            objlogicaIGV = new LogicaIGV();
            IGV = objlogicaIGV.RecuperaIGV();
        } catch (Exception ex) {
            System.out.println("ERROR AL RECUPERRAR EL IGV :" + ex.toString());
        }
    }

    private void CalculaMontos() {
        double auxparcial = 0.0;
        double subaux = 0;
        subtotal = 0;
        total = 0;

        for (int i = 0; i < this.tablapagos1.getRowCount(); i++) {
            auxparcial = Double.parseDouble(this.tablapagos1.getValueAt(i, 7).toString());
            total += auxparcial;
            double band_igv = listproformaDetalle.get(i).getIGV_Exonerado();


            if (band_igv == 0) {
                this.subtotal += auxparcial;
            } else {

                if (IGV == 0) {
                    CapturaIGV();
                }

                subaux = (auxparcial / (1 + (IGV / 100)));
                subtotal += subaux;

            }

        }

        BigDecimal bd = new BigDecimal(total);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd.doubleValue();


        BigDecimal bd1 = new BigDecimal(subtotal);
        bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
        subtotal = bd1.doubleValue();


        this.TxtMISubtotal.setText(String.valueOf(subtotal));
        this.TxtMiTotal.setText(String.valueOf(total));


    }

    private void CalculaMontos1() {
        double auxparcial = 0.0;
        double subaux = 0;
        subtotal = 0;
        total = 0;

        for (int i = 0; i < this.tablapagos1.getRowCount(); i++) {
            auxparcial = Double.parseDouble(this.tablapagos1.getValueAt(i, 7).toString());
            total += auxparcial;
            double band_igv = listproformaDetalle.get(i).getIGV_Exonerado();

            if (band_igv == 0) {
                this.subtotal += auxparcial;
            } else {

                if (IGV == 0) {
                    CapturaIGV();
                }

                subaux = (auxparcial / (1 + (IGV / 100)));
                subtotal += subaux;

            }

        }

        BigDecimal bd = new BigDecimal(total);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = bd.doubleValue();

        BigDecimal bd1 = new BigDecimal(subtotal);
        bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
        subtotal = bd1.doubleValue();

        this.TxtMISubtotal.setText(String.valueOf(subtotal));
        this.TxtMiTotal.setText(String.valueOf(total));
        this.jButton7.setEnabled(true);

    }

    private boolean VerificaStock(int cantidadpedida) {
        boolean resultado = false;
        int totalstock;
        int stkempaque = 0, stockfraccion = 0;
        int empaque = 0;

        try {

            String nomprod = this.tablapagos1.getValueAt(fila, 1).toString();

            for (int i = 0; i < listproformaDetalle.size(); i++) {
                if (listproformaDetalle.get(i).getDescipcionproducto().compareTo(nomprod) == 0) {
                    stkempaque = listproformaDetalle.get(i).getStock_Empaque();
                    empaque = listproformaDetalle.get(i).getEmpaque();
                    i = listproformaDetalle.size();
                }
            }


            if (empaque == 0) {
                empaque = 1;
            }

            stockfraccion = listproformaDetalle.get(fila).getStock_Fraccion();
            totalstock = stkempaque * empaque + stockfraccion;

            if (cantidadpedida <= totalstock) {
                resultado = true;
            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.toString());
        }

        return resultado;

    }

    public void ModificaProforma() {
        int unidad, fraccion;
        double PV, descuento, pvx, total1;
        String idproducto;
        Proforma obj;
        String montoproforma = "";
        String validar = "";
        double montoReal;
        double montovalidado;

        lisdetalleproforma.removeAll(lisdetalleproforma);

        for (int i = 0; i < this.tablapagos1.getRowCount(); i++) {

            unidad = Integer.parseInt(this.tablapagos1.getValueAt(i, 2).toString());
            fraccion = Integer.parseInt(this.tablapagos1.getValueAt(i, 3).toString());
            PV = Double.parseDouble(this.tablapagos1.getValueAt(i, 4).toString());
            descuento = Double.parseDouble(this.tablapagos1.getValueAt(i, 5).toString());
            pvx = Double.parseDouble(this.tablapagos1.getValueAt(i, 6).toString());
            total1 = Double.parseDouble(this.tablapagos1.getValueAt(i, 7).toString());
            idproducto = listproformaDetalle.get(i).getIdproducto();

            obj = new Proforma(this.subtotal, idproforma, idbotica, unidad, fraccion, PV, descuento, pvx, total1, idproducto, usuario, dsctoAdicional);
            lisdetalleproforma.add(obj);
        }

        boolean resultado = logicaProforma.Modifica_proforma(lisdetalleproforma, total);


        if (resultado) {

            montoproforma = onjproforma.MontoProformaRealizada(idproforma);
            validar = onjproforma.ValidaProformaModificada(idproforma);
            montoReal = Double.parseDouble(montoproforma);
            montovalidado = Double.parseDouble(validar);

            if (montoReal == montovalidado ){
            JOptionPane.showMessageDialog(this, " SU PROFORMA HA SIDO MODIFICADA CORRECTAMENTE  ", "Modificada", JOptionPane.INFORMATION_MESSAGE);
            limpiarDatos();
            }
        } else {
            JOptionPane.showMessageDialog(this, " ERROR AL MODIFICAR LA PROFORMA  ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }



    }

    private void limpiarDatos() {
        this.jTextField1.setText("");
        this.TxtCodigo.setText("");
        this.TxtCliente.setText("");
        this.TxtRuc.setText("");
        this.TxtDNI.setText("");
        this.TxtTelefono.setText("");
        this.TxtVendedor.setText("");
        this.TxtTipopago.setText("");
        this.TxtTipoVenta.setText("");
        this.TxtTotal.setText("");
        this.TxtSubototal.setText("");
        this.TxtCargo.setText("");
        this.TxtDireccion.setText("");
        lisdetalleproforma.removeAll(lisdetalleproforma);

        LimpiatTabla();
        idproforma = "";
        this.jTabbedPane1.setEnabledAt(1, false);
        this.jTabbedPane1.setSelectedIndex(0);
        this.jTabbedPane1.setEnabledAt(0, true);
        setVisible(true);

    }

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }

    private void BuscaProforma() {
        String texto = this.jTextField1.getText().trim();
        LimpiatTabla1();
        listaproforma.removeAll(listaproforma);

        try {

            listaproforma = objlogproforma.Recupera_Proforma_Sensitiva(texto, idbotica);

            for (int i = 0; i < listaproforma.size(); i++) {
                arrayproductos[0] = i + 1;
                arrayproductos[1] = listaproforma.get(i).getId_Proforma();
                arrayproductos[2] = listaproforma.get(i).getNomCliente();
                arrayproductos[3] = listaproforma.get(i).getFecha_Registro();
                arrayproductos[4] = listaproforma.get(i).getDescripcionTipoPago();
                arrayproductos[5] = listaproforma.get(i).getDescripciontipoventa();
                arrayproductos[6] = listaproforma.get(i).getTotal();
                arrayproductos[7] = listaproforma.get(i).getNombrePersonal();

                tablaproforma.addRow(arrayproductos);

            }
            total = listaproforma.get(0).getTotal();
            subtotal = listaproforma.get(0).getSubTotal();

            TxtMISubtotal.setText(String.valueOf(subtotal));
            TxtMiTotal.setText(String.valueOf(total));

        } catch (Exception ex) {
        }


    }

    private void CerrarVentana() {
        Confirmar p = new Confirmar(objetoventana, " DESEAS SALIR ? ");
        p.show(true);

        if (p.confirmar == 1) {
            this.dispose();
            objventas.marcacdo = false;
        }

        objventas.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        TxtDireccion = new javax.swing.JTextField();
        TxtRuc = new javax.swing.JTextField();
        TxtCodigo = new javax.swing.JTextField();
        TxtCliente = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        TxtDNI = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        TxtTelefono = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        TxtTipoVenta = new javax.swing.JTextField();
        TxtSubototal = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        TxtCargo = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        TxtIGV = new javax.swing.JTextField();
        TxtVendedor = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        TxtTotal = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        TxtTipopago = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablapagos1 = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        TxtMISubtotal = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        TxtMIIGV = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        TxtMiTotal = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(MuestraProformas.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setFont(resourceMap.getFont("jTable2.font")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Proforma", "Cliente", "Fecha", "Tipo de Pago", "Tipo de Venta", "Total", "Responsable"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setName("jTable2"); // NOI18N
        jTable2.setOpaque(false);
        jTable2.setSelectionBackground(resourceMap.getColor("jTable2.selectionBackground")); // NOI18N
        jTable2.setSelectionForeground(resourceMap.getColor("jTable2.selectionForeground")); // NOI18N
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable2.columnModel.title0")); // NOI18N
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(55);
            jTable2.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable2.columnModel.title1")); // NOI18N
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable2.columnModel.title2")); // NOI18N
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable2.columnModel.title3")); // NOI18N
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable2.columnModel.title4")); // NOI18N
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTable2.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable2.columnModel.title5")); // NOI18N
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable2.columnModel.title6")); // NOI18N
            jTable2.getColumnModel().getColumn(7).setResizable(false);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(180);
            jTable2.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable2.columnModel.title7")); // NOI18N
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel38.setForeground(resourceMap.getColor("jLabel38.foreground")); // NOI18N
        jLabel38.setText(resourceMap.getString("jLabel38.text")); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel39.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel39.setForeground(resourceMap.getColor("jLabel39.foreground")); // NOI18N
        jLabel39.setText(resourceMap.getString("jLabel39.text")); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        jLabel37.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel37.setForeground(resourceMap.getColor("jLabel37.foreground")); // NOI18N
        jLabel37.setText(resourceMap.getString("jLabel37.text")); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N

        TxtDireccion.setEditable(false);
        TxtDireccion.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtDireccion.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtDireccion.setForeground(resourceMap.getColor("TxtDireccion.foreground")); // NOI18N
        TxtDireccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtDireccion.setName("TxtDireccion"); // NOI18N

        TxtRuc.setEditable(false);
        TxtRuc.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtRuc.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtRuc.setForeground(resourceMap.getColor("TxtRuc.foreground")); // NOI18N
        TxtRuc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtRuc.setName("TxtRuc"); // NOI18N

        TxtCodigo.setEditable(false);
        TxtCodigo.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtCodigo.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtCodigo.setForeground(resourceMap.getColor("TxtCodigo.foreground")); // NOI18N
        TxtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtCodigo.setName("TxtCodigo"); // NOI18N

        TxtCliente.setEditable(false);
        TxtCliente.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtCliente.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtCliente.setForeground(resourceMap.getColor("TxtCliente.foreground")); // NOI18N
        TxtCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtCliente.setName("TxtCliente"); // NOI18N

        jLabel40.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel40.setForeground(resourceMap.getColor("jLabel40.foreground")); // NOI18N
        jLabel40.setText(resourceMap.getString("jLabel40.text")); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        TxtDNI.setEditable(false);
        TxtDNI.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtDNI.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtDNI.setForeground(resourceMap.getColor("TxtDNI.foreground")); // NOI18N
        TxtDNI.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtDNI.setName("TxtDNI"); // NOI18N

        jLabel46.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel46.setForeground(resourceMap.getColor("jLabel46.foreground")); // NOI18N
        jLabel46.setText(resourceMap.getString("jLabel46.text")); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N

        jLabel45.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        jLabel45.setForeground(resourceMap.getColor("jLabel45.foreground")); // NOI18N
        jLabel45.setText(resourceMap.getString("jLabel45.text")); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        TxtTelefono.setEditable(false);
        TxtTelefono.setBackground(resourceMap.getColor("TxtDireccion.background")); // NOI18N
        TxtTelefono.setFont(resourceMap.getFont("jLabel37.font")); // NOI18N
        TxtTelefono.setForeground(resourceMap.getColor("TxtTelefono.foreground")); // NOI18N
        TxtTelefono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtTelefono.setName("TxtTelefono"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel47.setForeground(resourceMap.getColor("jLabel47.foreground")); // NOI18N
        jLabel47.setText(resourceMap.getString("jLabel47.text")); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N

        TxtTipoVenta.setEditable(false);
        TxtTipoVenta.setBackground(resourceMap.getColor("TxtTipoVenta.background")); // NOI18N
        TxtTipoVenta.setForeground(resourceMap.getColor("TxtTipoVenta.foreground")); // NOI18N
        TxtTipoVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtTipoVenta.setName("TxtTipoVenta"); // NOI18N

        TxtSubototal.setEditable(false);
        TxtSubototal.setBackground(resourceMap.getColor("TxtSubototal.background")); // NOI18N
        TxtSubototal.setForeground(resourceMap.getColor("TxtSubototal.foreground")); // NOI18N
        TxtSubototal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtSubototal.setName("TxtSubototal"); // NOI18N

        jLabel53.setForeground(resourceMap.getColor("jLabel53.foreground")); // NOI18N
        jLabel53.setText(resourceMap.getString("jLabel53.text")); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N

        TxtCargo.setEditable(false);
        TxtCargo.setBackground(resourceMap.getColor("TxtCargo.background")); // NOI18N
        TxtCargo.setForeground(resourceMap.getColor("TxtCargo.foreground")); // NOI18N
        TxtCargo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtCargo.setName("TxtCargo"); // NOI18N

        jLabel48.setForeground(resourceMap.getColor("jLabel48.foreground")); // NOI18N
        jLabel48.setText(resourceMap.getString("jLabel48.text")); // NOI18N
        jLabel48.setName("jLabel48"); // NOI18N

        TxtIGV.setEditable(false);
        TxtIGV.setBackground(resourceMap.getColor("TxtIGV.background")); // NOI18N
        TxtIGV.setForeground(resourceMap.getColor("TxtIGV.foreground")); // NOI18N
        TxtIGV.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtIGV.setName("TxtIGV"); // NOI18N

        TxtVendedor.setEditable(false);
        TxtVendedor.setBackground(resourceMap.getColor("TxtVendedor.background")); // NOI18N
        TxtVendedor.setForeground(resourceMap.getColor("TxtVendedor.foreground")); // NOI18N
        TxtVendedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtVendedor.setName("TxtVendedor"); // NOI18N

        jLabel49.setForeground(resourceMap.getColor("jLabel49.foreground")); // NOI18N
        jLabel49.setText(resourceMap.getString("jLabel49.text")); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel52.setForeground(resourceMap.getColor("jLabel52.foreground")); // NOI18N
        jLabel52.setText(resourceMap.getString("jLabel52.text")); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N

        TxtTotal.setEditable(false);
        TxtTotal.setBackground(resourceMap.getColor("TxtTotal.background")); // NOI18N
        TxtTotal.setForeground(resourceMap.getColor("TxtTotal.foreground")); // NOI18N
        TxtTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtTotal.setName("TxtTotal"); // NOI18N

        jLabel50.setForeground(resourceMap.getColor("jLabel50.foreground")); // NOI18N
        jLabel50.setText(resourceMap.getString("jLabel50.text")); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N

        TxtTipopago.setEditable(false);
        TxtTipopago.setBackground(resourceMap.getColor("TxtTipopago.background")); // NOI18N
        TxtTipopago.setForeground(resourceMap.getColor("TxtTipopago.foreground")); // NOI18N
        TxtTipopago.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtTipopago.setName("TxtTipopago"); // NOI18N

        jLabel51.setForeground(resourceMap.getColor("jLabel51.foreground")); // NOI18N
        jLabel51.setText(resourceMap.getString("jLabel51.text")); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N

        jLabel19.setForeground(resourceMap.getColor("jLabel19.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jTextField21.setEditable(false);
        jTextField21.setBackground(resourceMap.getColor("jTextField21.background")); // NOI18N
        jTextField21.setFont(resourceMap.getFont("jTextField21.font")); // NOI18N
        jTextField21.setForeground(resourceMap.getColor("jTextField21.foreground")); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setName("jTextField21"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(TxtSubototal)
                                    .addComponent(TxtIGV))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtTipopago))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(TxtCargo)
                            .addComponent(TxtVendedor)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel48)
                                            .addComponent(TxtTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel47)
                                            .addComponent(TxtTipopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel50)
                                            .addComponent(TxtSubototal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(TxtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49)
                            .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39)
                                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(TxtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablapagos1.setForeground(resourceMap.getColor("tablapagos1.foreground")); // NOI18N
        tablapagos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Producto", "Und", "Fracc", "PVP", "DSCTO", "PVPx", "Parcial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablapagos1.setColumnSelectionAllowed(true);
        tablapagos1.setName("tablapagos1"); // NOI18N
        tablapagos1.setSelectionBackground(resourceMap.getColor("tablapagos1.selectionBackground")); // NOI18N
        tablapagos1.setSelectionForeground(resourceMap.getColor("tablapagos1.selectionForeground")); // NOI18N
        tablapagos1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablapagos1.getTableHeader().setReorderingAllowed(false);
        tablapagos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablapagos1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablapagos1);
        tablapagos1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablapagos1.getColumnModel().getColumnCount() > 0) {
            tablapagos1.getColumnModel().getColumn(0).setResizable(false);
            tablapagos1.getColumnModel().getColumn(0).setPreferredWidth(35);
            tablapagos1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title0")); // NOI18N
            tablapagos1.getColumnModel().getColumn(1).setResizable(false);
            tablapagos1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title1")); // NOI18N
            tablapagos1.getColumnModel().getColumn(2).setResizable(false);
            tablapagos1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title2")); // NOI18N
            tablapagos1.getColumnModel().getColumn(3).setResizable(false);
            tablapagos1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title3")); // NOI18N
            tablapagos1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title4")); // NOI18N
            tablapagos1.getColumnModel().getColumn(5).setResizable(false);
            tablapagos1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title5")); // NOI18N
            tablapagos1.getColumnModel().getColumn(6).setResizable(false);
            tablapagos1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title6")); // NOI18N
            tablapagos1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("tablapagos1.columnModel.title7")); // NOI18N
        }

        jLabel54.setFont(resourceMap.getFont("TxtMISubtotal.font")); // NOI18N
        jLabel54.setForeground(resourceMap.getColor("jLabel54.foreground")); // NOI18N
        jLabel54.setText(resourceMap.getString("jLabel54.text")); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N

        TxtMISubtotal.setEditable(false);
        TxtMISubtotal.setFont(resourceMap.getFont("TxtMISubtotal.font")); // NOI18N
        TxtMISubtotal.setForeground(resourceMap.getColor("TxtMISubtotal.foreground")); // NOI18N
        TxtMISubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtMISubtotal.setName("TxtMISubtotal"); // NOI18N

        jLabel56.setFont(resourceMap.getFont("TxtMISubtotal.font")); // NOI18N
        jLabel56.setForeground(resourceMap.getColor("jLabel56.foreground")); // NOI18N
        jLabel56.setText(resourceMap.getString("jLabel56.text")); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N

        TxtMIIGV.setEditable(false);
        TxtMIIGV.setFont(resourceMap.getFont("TxtMISubtotal.font")); // NOI18N
        TxtMIIGV.setForeground(resourceMap.getColor("TxtMIIGV.foreground")); // NOI18N
        TxtMIIGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtMIIGV.setName("TxtMIIGV"); // NOI18N

        jLabel55.setFont(resourceMap.getFont("jLabel55.font")); // NOI18N
        jLabel55.setForeground(resourceMap.getColor("jLabel55.foreground")); // NOI18N
        jLabel55.setText(resourceMap.getString("jLabel55.text")); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N

        TxtMiTotal.setEditable(false);
        TxtMiTotal.setFont(resourceMap.getFont("jLabel55.font")); // NOI18N
        TxtMiTotal.setForeground(resourceMap.getColor("TxtMiTotal.foreground")); // NOI18N
        TxtMiTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtMiTotal.setName("TxtMiTotal"); // NOI18N

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtMISubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtMIIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtMiTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addGap(589, 589, 589))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtMIIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(TxtMISubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel55)
                    .addComponent(TxtMiTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
}//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscaProforma();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            LimpiatTabla();
            this.jTabbedPane1.setEnabledAt(1, true);
            this.jTabbedPane1.setSelectedIndex(1);
            this.jTabbedPane1.setEnabledAt(0, false);
            DevuelveDatos(this.jTable2.getSelectedRow());
        }
}//GEN-LAST:event_jTable2MouseClicked

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LimpiatTabla();
            this.jTabbedPane1.setEnabledAt(1, true);
            this.jTabbedPane1.setSelectedIndex(1);
            this.jTabbedPane1.setEnabledAt(0, false);
            DevuelveDatos(this.jTable2.getSelectedRow());
        }
}//GEN-LAST:event_jTable2KeyPressed

    private void tablapagos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablapagos1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            fila = this.tablapagos1.getSelectedRow();


            if (fila >= 0) {

                double tot2 = 0;
                int cantidadpedida = 0;

                int unidad = Integer.parseInt(tablapagos1.getValueAt(fila, 2).toString().trim());
                int fraccion = Integer.parseInt(tablapagos1.getValueAt(fila, 3).toString().trim());

                double PVP = Double.parseDouble(tablapagos1.getValueAt(fila, 4).toString().trim());
                double descto = Double.parseDouble(tablapagos1.getValueAt(fila, 5).toString().trim());

                int empaque = listproformaDetalle.get(fila).getEmpaque();


                if (empaque == 0) {
                    cantidadpedida = unidad * 1 + fraccion;
                } else {
                    cantidadpedida = unidad * empaque + fraccion;
                }

                if (fraccion > 0 && empaque == 0) {
                    JOptionPane.showMessageDialog(this, " ESTE PRODUCTO NO SE PUEDE VENDER EN FRACCION  ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getFRACCION(), fila, 3);
                    this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getUNIDAD(), fila, 2);
                } else if (unidad == 0 && fraccion == 0) {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE CANTIDADES CORRECTAS  ", "Error", JOptionPane.ERROR_MESSAGE);
                    this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getFRACCION(), fila, 3);
                    this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getUNIDAD(), fila, 2);
                } else {

                    if (VerificaStock(cantidadpedida)) {

                        double pvx = PVP - (PVP * (descto / 100));

                        BigDecimal bd2 = new BigDecimal(pvx);
                        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        pvx = bd2.doubleValue();

                        Double tot1 = unidad * pvx;

                        if (empaque > 0) {
                            tot2 = (fraccion * pvx) / empaque;
                        }

                        double total1 = tot1 + tot2;

                        BigDecimal bd = new BigDecimal(total1);
                        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                        total1 = bd.doubleValue();

                        this.tablapagos1.setValueAt(unidad, fila, 2);
                        this.tablapagos1.setValueAt(fraccion, fila, 3);
                        this.tablapagos1.setValueAt(pvx, fila, 6);
                        this.tablapagos1.setValueAt(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString(), fila, 7);

                        this.tablapagos1.requestFocus();
                        this.jButton7.setEnabled(true);

                        CalculaMontos();

                    } else {
                        JOptionPane.showMessageDialog(this, " NO HAY STOCK PARA ESTA CANTIDAD INGRESADA  ", "Error", JOptionPane.ERROR_MESSAGE);
                        this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getFRACCION(), fila, 3);
                        this.tablapagos1.setValueAt(listproformaDetalle.get(fila).getUNIDAD(), fila, 2);
                    }

                }

            }

        }

        if (evt.getKeyCode() == 127) {
            if (fila >= 0) {
                fila = this.tablapagos1.getSelectedRow();
                EliminaProducto(fila);
            }
        }

    }//GEN-LAST:event_tablapagos1KeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            String cantidad = JOptionPane.showInputDialog(this, "Ingrese el Descuento especial de la Venta ? ", "Ingrese Descuento", JOptionPane.QUESTION_MESSAGE);
            if (cantidad != null) {

                try {
                    dsctoAdicional = Double.parseDouble(cantidad);
                    Modifica2(dsctoAdicional);
                    CalculaMontos();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un Descuento Correcto", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            dsctoAdicional = 0.0;
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        BuscaProforma();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CerrarVentana();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        ventUsuaClave = new UsuarioClave(objetoventana, idbotica,objventas);
        ventUsuaClave.pack();
        ventUsuaClave.setVisible(true);
        usuario = ventUsuaClave.getUsuario();
        clave = ventUsuaClave.getClave();

        if (!usuario.isEmpty() && !clave.isEmpty()) {
            ModificaProforma();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jTabbedPane1.setEnabledAt(1, false);
        this.jTabbedPane1.setSelectedIndex(0);
        this.jTabbedPane1.setEnabledAt(0, true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtCargo;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JTextField TxtCodigo;
    private javax.swing.JTextField TxtDNI;
    private javax.swing.JTextField TxtDireccion;
    private javax.swing.JTextField TxtIGV;
    private javax.swing.JTextField TxtMIIGV;
    private javax.swing.JTextField TxtMISubtotal;
    private javax.swing.JTextField TxtMiTotal;
    private javax.swing.JTextField TxtRuc;
    private javax.swing.JTextField TxtSubototal;
    private javax.swing.JTextField TxtTelefono;
    private javax.swing.JTextField TxtTipoVenta;
    private javax.swing.JTextField TxtTipopago;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JTextField TxtVendedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTable tablapagos1;
    // End of variables declaration//GEN-END:variables
}
