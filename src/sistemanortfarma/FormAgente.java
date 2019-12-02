package sistemanortfarma;

import CapaLogica.LogicaCajas;
import CapaLogica.LogicaConexion;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaRecuperaCaja;
import CapaLogica.LogicaTiposPagos;
import entidad.Cajas;
import entidad.Personal;
import entidad.TiposPagos;
import entidad.Turno;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
public class FormAgente extends javax.swing.JInternalFrame implements Runnable {

    AplicacionVentas objeto;
    private String idbotica;
    private String usuario;
    private String Responsable;
    MuestraVentana objetoventana = new MuestraVentana();
    List<Cajas> listacajas = new ArrayList<Cajas>();
    LogicaRecuperaCaja objcajas = new LogicaRecuperaCaja();
    List<Personal> Lista_Cajeros = new ArrayList<Personal>();
    LogicaPersonal logperso = new LogicaPersonal();
    private int idcaja;
    int idcajero = 0;
    LogicaTiposPagos logbcp = new LogicaTiposPagos();
    LogicaFechaHora logfecha = new LogicaFechaHora();
    String operacion = "";
    Object[] dataretiros = new Object[2];
    private DefaultTableModel modelo_retiros;
    TableColumnModel col_retiros;
    double monto_Retiros = 0.0;
    int numero_retiros = 0;
    Object[] datadepositos = new Object[2];
    private DefaultTableModel modelo_depositos;
    TableColumnModel col_depositos;
    double monto_depositos = 0.0;
    int numero_depositos = 0;
    Object[] dataservisios = new Object[2];
    private DefaultTableModel modelo_servisios;
    TableColumnModel col_servisios;
    double monto_servisios = 0.0;
    int numero_servisios = 0;
    Object[] dataspagoscuenta = new Object[2];
    private DefaultTableModel modelo_pagos_cuenta;
    TableColumnModel col_pagos_cuenta;
    double monto_pagos_cuenta = 0.0;
    int numero_pagos_cuenta = 0;
    private int tipodemoneda = 0; //si es 0 soles  si es 1 en dolares
    TiposPagos objretiro;
    String Fecha;
    Thread showThread;
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con = null;
    String report_file;
    JasperViewer view;
    private Date fechaFin;
    private URL archivo;
    VerificaSistema objsistema;
    LogicaConexion logconex = new LogicaConexion();
    private int turno;
    LogicaCajas logcajas = new LogicaCajas();
    List<Turno> Lista_Turnos = new ArrayList<Turno>();

    /** Creates new form FormAgente */
    public FormAgente(AplicacionVentas obj) {
        initComponents();
        objeto = obj;
        con = logconex.RetornaConexion();
        idbotica = objeto.getIdbotica();
        Responsable = objeto.getUsuario();
        usuario = obj.getUsuario();
        jLabel12.setText(idbotica);
        CargarCajeros();
        Deshabilita(false);
        Oculta(false);

        modelo_retiros = (DefaultTableModel) tablaretiros.getModel();
        col_retiros = this.tablaretiros.getColumnModel();

        modelo_depositos = (DefaultTableModel) tabladepositos.getModel();
        col_depositos = this.tabladepositos.getColumnModel();

        modelo_servisios = (DefaultTableModel) tablaservicios.getModel();
        col_servisios = this.tablaservicios.getColumnModel();

        modelo_pagos_cuenta = (DefaultTableModel) tablapagoscuenta.getModel();
        col_pagos_cuenta = this.tablapagoscuenta.getColumnModel();

        Deshabilita_Ingreso_ERetiros(false);
        Deshabilita_Ingreso_Depositos(false);
        Deshabilita_Pagos_Servicios(false);
        Deshabilita_Pagos_Cuenta(false);

        this.jLabel9.setText("");
        this.jLabel8.setText("");
        this.jLabel10.setText("");

        Lista_Turnos();

    }

    private void Oculta(boolean valor) {
        jLabel5.setVisible(valor);
        jLabel8.setVisible(valor);
        jLabel7.setVisible(valor);
        jLabel9.setVisible(valor);
        jLabel6.setVisible(valor);
        jLabel10.setVisible(valor);
        jLabel4.setVisible(valor);

    }

    private void Lista_Turnos() {
        Lista_Turnos = logcajas.Lista_Turnos();

        jComboBox7.addItem("---- Seleccione Turno ----");

        for (int i = 0; i < Lista_Turnos.size(); i++) {
            jComboBox7.addItem(Lista_Turnos.get(i).getDescripcion());
        }

    }

    public void run() {
        try {


            String sistema = "Windows";

            System.out.println(idbotica);
            System.out.println(idcajero);
            System.out.println(listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());

            parameters.put("FECHA", jXDatePicker1.getDate());
            parameters.put("IDCAJA", listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja());
            parameters.put("IDBOTICA", idbotica);
            parameters.put("IDCAJERO", idcajero);
            parameters.put("REPORT_CONNECTION", con);

            archivo = this.getClass().getResource("/Reportes/REPORTE_BCP_RETIROS.jasper");

            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
            view = new JasperViewer(jasperPrint, false);
            view.setTitle("Reporte Movimientos BCP");
            view.setVisible(true);
            view.setSize(870, 700);


        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void Deshabilita(boolean valor) {

        if (valor == false) {
            jTabbedPane1.setSelectedIndex(0);
        } else {
            jTabbedPane1.setSelectedIndex(1);
        }

        jTabbedPane1.setEnabledAt(1, valor);
        jTabbedPane1.setEnabledAt(2, valor);
        jTabbedPane1.setEnabledAt(3, valor);
        jTabbedPane1.setEnabledAt(4, valor);


    }

    private void Deshabilita_Ingreso_ERetiros(boolean valor) {
        this.jComboBox3.setEnabled(valor);
        this.operacionRetiro.setEnabled(valor);
        In_Monto_Retiro.setEnabled(valor);
        jButton8.setEnabled(valor);
        jButton7.setEnabled(valor);
        this.jTextField1.setEnabled(valor);
    }

    private void Deshabilita_Ingreso_Depositos(boolean valor) {
        this.OperacionDeposito.setEnabled(valor);
        this.In_Monto_Deposito.setEnabled(valor);
        In_Monto_Retiro.setEnabled(valor);
        jButton15.setEnabled(valor);
        jButton16.setEnabled(valor);
    }

    private void Deshabilita_Pagos_Servicios(boolean valor) {
        this.jComboBox4.setEnabled(valor);
        this.IN_Operacion_Servios.setEnabled(valor);
        In_Monto_Servcios.setEnabled(valor);
        jButton17.setEnabled(valor);
        jButton18.setEnabled(valor);
        this.jTextField6.setEnabled(valor);
    }

    private void Deshabilita_Pagos_Cuenta(boolean valor) {
        this.jComboBox5.setEnabled(valor);
        this.IN_Operacion_Cuenta.setEnabled(valor);
        In_Monto_Cuenta.setEnabled(valor);
        jButton26.setEnabled(valor);
        jButton27.setEnabled(valor);
        this.jTextField9.setEnabled(valor);
    }

    private void CargarCajeros() {

        listacajas = objcajas.RecuperaCajas(idbotica);

        for (int i = 0; i < listacajas.size(); i++) {
            this.jComboBox1.addItem(listacajas.get(i).getCajas());
        }

        int rol = new login().getId_rol();

        Lista_Cajeros = logperso.Lista_Cajeros(idbotica);

        for (int i = 0; i < Lista_Cajeros.size(); i++) {
            jComboBox2.addItem(Lista_Cajeros.get(i).getApellido());
        }

    }

    private void VerificaInformacion() {

        idcaja = listacajas.get(this.jComboBox1.getSelectedIndex() - 1).getIdcaja();
        idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex() - 1).getId_Personal();
        Fecha = logfecha.ConvierteFecha(this.jXDatePicker1.getDate());
        List<TiposPagos> Lista = logbcp.Lista_movimientos_BCP_Cuadre(idbotica, idcajero, idcaja, Fecha, turno, idcaja);

        try {

            DeshabilitaBusqueda(false);
            String numero, operacion1;
            double monto;
            int moneda;
            Deshabilita(true);
            this.jButton10.setEnabled(false);
            this.jLabel9.setText(Fecha);
            this.jLabel8.setText(this.jComboBox2.getSelectedItem().toString());
            this.jLabel10.setText(this.usuario);
            Oculta(true);

            for (int i = 0; i < Lista.size(); i++) {
                numero = Lista.get(i).getNUMERO();
                operacion1 = Lista.get(i).getOPERACION();
                monto = Lista.get(i).getMONTO();
                moneda = Lista.get(i).getTIPO_MONEDA();

                if (operacion1.trim().compareToIgnoreCase("RETIRO") == 0) {
                    dataretiros[0] = numero;
                    dataretiros[1] = monto;
                    monto_Retiros += monto;
                    numero_retiros++;
                    modelo_retiros.addRow(dataretiros);

                }

                if (operacion1.trim().compareToIgnoreCase("DEPOSITOS") == 0) {
                    datadepositos[0] = numero;
                    datadepositos[1] = monto;
                    monto_depositos += monto;
                    numero_depositos++;
                    modelo_depositos.addRow(datadepositos);
                }

                if (operacion1.trim().compareToIgnoreCase("PAGO DE SERVICIOS") == 0) {
                    dataservisios[0] = numero;
                    dataservisios[1] = monto;
                    monto_servisios += monto;
                    numero_servisios++;
                    modelo_servisios.addRow(dataservisios);
                }

                if (operacion1.trim().compareToIgnoreCase("PAGOS DE CUENTA") == 0) {
                    dataspagoscuenta[0] = numero;
                    dataspagoscuenta[1] = monto;
                    monto_pagos_cuenta += monto;
                    numero_pagos_cuenta++;
                    modelo_pagos_cuenta.addRow(dataspagoscuenta);
                }

            }

            /*
             * RETIROS
             */
            BigDecimal bd = new BigDecimal(monto_Retiros);
            this.txttotalretiros.setText(String.valueOf(numero_retiros));
            this.txtmontoretiros.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            /*
             * DEPOSITOS
             */
            BigDecimal bd1 = new BigDecimal(monto_depositos);
            this.txttotaldepositos.setText(String.valueOf(numero_depositos));
            this.txtmontodeporitos.setText(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            /*
             * PAGO DE SERVICIOS
             */
            BigDecimal bd2 = new BigDecimal(monto_servisios);
            this.txttotalservicios.setText(String.valueOf(numero_servisios));
            this.txtmontoservicios.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            /*
             * PAGOS DE CUENTA
             */
            BigDecimal bd3 = new BigDecimal(monto_pagos_cuenta);
            this.txttotalpagoscuenta.setText(String.valueOf(numero_pagos_cuenta));
            this.txtmontopagoscuenta.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void LimpiarTodo() {
        LimpiatTabla_retiros();
        LimpiatTabla_depositos();
        LimpiatTabla_Servios();
        LimpiatTabla_Cuenta();

        monto_Retiros = 0.0;
        numero_retiros = 0;

        monto_depositos = 0.0;
        numero_depositos = 0;

        monto_servisios = 0.0;
        numero_servisios = 0;

        monto_pagos_cuenta = 0.0;
        numero_pagos_cuenta = 0;

        this.txttotalretiros.setText("");
        this.txtmontoretiros.setText("");

        this.txttotaldepositos.setText("");
        this.txtmontodeporitos.setText("");

        this.txttotalservicios.setText("");
        this.txtmontoservicios.setText("");

        this.txttotalpagoscuenta.setText("");
        this.txtmontopagoscuenta.setText("");


        Deshabilita(false);


    }

    private void GuardaRetiro() {

        tipodemoneda = jComboBox3.getSelectedIndex();
        double tipocambio = 0;
        boolean ingresa = true;
        operacion = "RETIRO";

        if (operacionRetiro.getText().trim().compareTo("") != 0 && In_Monto_Retiro.getText().trim().compareTo("") != 0) {

            String numOperacion = operacionRetiro.getText().trim();
            double monto = Double.parseDouble(In_Monto_Retiro.getText().trim());
            double monto1 = Double.parseDouble(In_Monto_Retiro.getText().trim());


            if (this.jComboBox3.getSelectedIndex() == 1) {

                if (this.jTextField1.getText().trim().compareTo("") != 0) {
                    tipocambio = Double.parseDouble(jTextField1.getText().trim());
                    monto1 = monto1 * tipocambio;
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE TIPO DE CAMBIO ", "Error", JOptionPane.ERROR_MESSAGE);
                    ingresa = false;
                }

            }

            if (ingresa) {

                objretiro = new TiposPagos(idbotica, idcajero, operacion, numOperacion, monto, tipodemoneda, tipocambio, turno, idcaja);

                if (monto > 0) {

                    if (logbcp.Agregar_Movimiento_deBCP_Cuadre(objretiro, Fecha)) {

                        this.operacionRetiro.setText("");
                        this.In_Monto_Retiro.setText("");

                        Confirmar p = new Confirmar(objetoventana, "<html> TRANSFERENCIA REALIZADA \n DESEA INGRESAR OTRO RETIRO ?  </html>");
                        p.show(true);

                        if (p.getConfirmar() == 1) {

                            dataretiros[0] = numOperacion;
                            dataretiros[1] = monto1;
                            modelo_retiros.addRow(dataretiros);
                            monto_Retiros = monto_Retiros + monto1;
                            numero_retiros++;

                        } else {
                            Deshabilita_Ingreso_ERetiros(false);
                        }

                        BigDecimal bd = new BigDecimal(monto_Retiros);
                        this.txttotalretiros.setText(String.valueOf(numero_retiros));
                        this.txtmontoretiros.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

                    } else {
                        JOptionPane.showMessageDialog(this, " ERROR AL INGRESAR SU RETIRO ", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UNA CANTIDAD CORRECTA  ", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }

        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE DATOS COMPLETOS ", "Error", JOptionPane.ERROR_MESSAGE);
        }




    }

    private void GuardaDeposito() {

        double tipocambio = 0;
        boolean ingresa = true;
        operacion = "DEPOSITOS";
        tipodemoneda = 0;

        if (OperacionDeposito.getText().trim().compareTo("") != 0 && In_Monto_Deposito.getText().trim().compareTo("") != 0) {

            String numOperacion = OperacionDeposito.getText().trim();
            double monto = Double.parseDouble(In_Monto_Deposito.getText().trim());

            objretiro = new TiposPagos(idbotica, idcajero, operacion, numOperacion, monto, tipodemoneda, tipocambio, turno, idcaja);

            if (monto > 0) {

                if (logbcp.Agregar_Movimiento_deBCP_Cuadre(objretiro, Fecha)) {

                    this.OperacionDeposito.setText("");
                    this.In_Monto_Deposito.setText("");

                    Confirmar p = new Confirmar(objetoventana, "<html> TRANSFERENCIA REALIZADA \n DESEA INGRESAR OTRO DEPOSITO ?  </html>");
                    p.show(true);

                    if (p.getConfirmar() == 1) {

                        datadepositos[0] = numOperacion;
                        datadepositos[1] = monto;
                        modelo_depositos.addRow(datadepositos);

                        monto_depositos += monto;
                        numero_depositos++;

                        OperacionDeposito.requestFocus();
                    } else {
                        Deshabilita_Ingreso_Depositos(false);
                    }

                    BigDecimal bd = new BigDecimal(monto_depositos);
                    this.txttotaldepositos.setText(String.valueOf(numero_depositos));
                    this.txtmontodeporitos.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());


                } else {
                    JOptionPane.showMessageDialog(this, " ERROR AL INGRESAR SU DEPOSITO ", "Error", JOptionPane.ERROR_MESSAGE);
                }


            } else {
                JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UNA CANTIDAD CORRECTA  ", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE DATOS COMPLETOS ", "Error", JOptionPane.ERROR_MESSAGE);
        }





    }

    private void Guarda_PagosServicos() {

        tipodemoneda = jComboBox4.getSelectedIndex();
        double tipocambio = 0;
        boolean ingresa = true;
        operacion = "PAGO DE SERVICIOS";

        if (IN_Operacion_Servios.getText().trim().compareTo("") != 0 && In_Monto_Servcios.getText().trim().compareTo("") != 0) {

            String numOperacion = IN_Operacion_Servios.getText().trim();
            double monto = Double.parseDouble(In_Monto_Servcios.getText().trim());
            double monto1 = Double.parseDouble(In_Monto_Servcios.getText().trim());

            if (this.jComboBox4.getSelectedIndex() == 1) {

                if (this.jTextField6.getText().trim().compareTo("") != 0) {
                    tipocambio = Double.parseDouble(jTextField6.getText().trim());
                    monto1 = monto1 * tipocambio;
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE TIPO DE CAMBIO ", "Error", JOptionPane.ERROR_MESSAGE);
                    ingresa = false;
                }

            }

            if (ingresa) {

                objretiro = new TiposPagos(idbotica, idcajero, operacion, numOperacion, monto, tipodemoneda, tipocambio, turno, idcaja);

                if (monto > 0) {

                    if (logbcp.Agregar_Movimiento_deBCP_Cuadre(objretiro, this.Fecha)) {

                        this.IN_Operacion_Servios.setText("");
                        this.In_Monto_Servcios.setText("");
                        Confirmar p = new Confirmar(objetoventana, "<html> TRANSFERENCIA REALIZADA \n DESEA INGRESAR OTRO PAGO DE SERVICIOS </html>");
                        p.show(true);

                        if (p.getConfirmar() == 1) {
                            dataservisios[0] = numOperacion;
                            dataservisios[1] = monto1;

                            modelo_servisios.addRow(dataservisios);

                            monto_servisios += monto1;
                            numero_servisios++;
                            IN_Operacion_Servios.requestFocus();

                        } else {
                            this.Deshabilita_Pagos_Servicios(false);
                        }


                        BigDecimal bd2 = new BigDecimal(monto_servisios);
                        this.txttotalservicios.setText(String.valueOf(numero_servisios));
                        this.txtmontoservicios.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());


                    } else {
                        JOptionPane.showMessageDialog(this, " ERROR AL INGRESAR SU PAGO ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UNA CANTIDAD CORRECTA  ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

    }

    private void Guarda_PagosCuenta() {

        tipodemoneda = jComboBox5.getSelectedIndex();
        double tipocambio = 0;
        boolean ingresa = true;
        operacion = "PAGOS DE CUENTA";

        if (IN_Operacion_Cuenta.getText().trim().compareTo("") != 0 && In_Monto_Cuenta.getText().trim().compareTo("") != 0) {

            String numOperacion = IN_Operacion_Cuenta.getText().trim();
            double monto = Double.parseDouble(In_Monto_Cuenta.getText().trim());
            double monto1 = Double.parseDouble(In_Monto_Cuenta.getText().trim());

            if (this.jComboBox5.getSelectedIndex() == 1) {

                if (this.jTextField9.getText().trim().compareTo("") != 0) {
                    tipocambio = Double.parseDouble(jTextField9.getText().trim());
                    monto1 = monto1 * tipocambio;
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE TIPO DE CAMBIO ", "Error", JOptionPane.ERROR_MESSAGE);
                    ingresa = false;
                }

            }

            if (ingresa) {

                objretiro = new TiposPagos(idbotica, idcajero, operacion, numOperacion, monto, tipodemoneda, tipocambio, turno, idcaja);

                if (monto > 0) {

                    if (logbcp.Agregar_Movimiento_deBCP_Cuadre(objretiro, this.Fecha)) {

                        this.IN_Operacion_Cuenta.setText("");
                        this.In_Monto_Cuenta.setText("");
                        Confirmar p = new Confirmar(objetoventana, "<html> TRANSFERENCIA REALIZADA \n DESEA INGRESAR OTRO PAGO  </html>");
                        p.show(true);

                        if (p.getConfirmar() == 1) {
                            IN_Operacion_Cuenta.requestFocus();

                            dataspagoscuenta[0] = numOperacion;
                            dataspagoscuenta[1] = monto1;
                            monto_pagos_cuenta += monto1;
                            numero_pagos_cuenta++;
                            modelo_pagos_cuenta.addRow(dataspagoscuenta);

                        } else {
                            this.Deshabilita_Pagos_Cuenta(false);
                        }

                        BigDecimal bd3 = new BigDecimal(monto_pagos_cuenta);
                        this.txttotalpagoscuenta.setText(String.valueOf(numero_pagos_cuenta));
                        this.txtmontopagoscuenta.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());


                    } else {
                        JOptionPane.showMessageDialog(this, " ERROR AL INGRESAR SU PAGO", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, " PORFAVOR INGRESE UNA CANTIDAD CORRECTA  ", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

    }

    private void LimpiatTabla_retiros() {
        int cant = this.tablaretiros.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo_retiros.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_depositos() {
        int cant = this.tabladepositos.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo_depositos.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_Servios() {
        int cant = this.tablaservicios.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo_servisios.removeRow(i);
            }
        }
    }

    private void LimpiatTabla_Cuenta() {
        int cant = this.tablapagoscuenta.getRowCount();
        if (cant >= 1) {
            for (int i = cant - 1; i >= 0; i--) {
                modelo_pagos_cuenta.removeRow(i);
            }
        }
    }

    private void DeshabilitaBusqueda(boolean valor) {
        this.jXDatePicker1.setEnabled(valor);
        this.jComboBox1.setEnabled(valor);
        this.jComboBox2.setEnabled(valor);
        jComboBox7.setEnabled(valor);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton10 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaretiros = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        operacionRetiro = new javax.swing.JTextField();
        In_Monto_Retiro = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txttotalretiros = new javax.swing.JTextField();
        txtmontoretiros = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabladepositos = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txttotaldepositos = new javax.swing.JTextField();
        txtmontodeporitos = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        OperacionDeposito = new javax.swing.JTextField();
        In_Monto_Deposito = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField6 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        IN_Operacion_Servios = new javax.swing.JTextField();
        In_Monto_Servcios = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtmontoservicios = new javax.swing.JTextField();
        txttotalservicios = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaservicios = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablapagoscuenta = new javax.swing.JTable();
        txttotalpagoscuenta = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        IN_Operacion_Cuenta = new javax.swing.JTextField();
        In_Monto_Cuenta = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        txtmontopagoscuenta = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormAgente.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------  Seleccione su Caja  ---------" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "------ Seleccione Cajero ----" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jXDatePicker1.setFormats(new String[] { "dd/M/yyyy" });
        jXDatePicker1.setName("jXDatePicker1"); // NOI18N

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setEnabled(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel36.setText(resourceMap.getString("jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N

        jComboBox7.setName("jComboBox7"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox7, 0, 258, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 258, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, 258, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10))
        );

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel7.setName("jPanel7"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaretiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OPERACION", "MONTO (S./)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaretiros.setName("tablaretiros"); // NOI18N
        tablaretiros.setSelectionBackground(resourceMap.getColor("tablaretiros.selectionBackground")); // NOI18N
        tablaretiros.setSelectionForeground(resourceMap.getColor("tablaretiros.selectionForeground")); // NOI18N
        tablaretiros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaretiros.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaretiros);
        tablaretiros.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaretiros.getColumnModel().getColumn(0).setResizable(false);
        tablaretiros.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaretiros.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title0")); // NOI18N
        tablaretiros.getColumnModel().getColumn(1).setResizable(false);
        tablaretiros.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaretiros.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title1")); // NOI18N

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel6.border.titleColor"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " EN SOLES   \tS/.", " EN DOLARES" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        operacionRetiro.setFont(resourceMap.getFont("operacionRetiro.font")); // NOI18N
        operacionRetiro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        operacionRetiro.setText(resourceMap.getString("operacionRetiro.text")); // NOI18N
        operacionRetiro.setName("operacionRetiro"); // NOI18N

        In_Monto_Retiro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        In_Monto_Retiro.setText(resourceMap.getString("In_Monto_Retiro.text")); // NOI18N
        In_Monto_Retiro.setName("In_Monto_Retiro"); // NOI18N
        In_Monto_Retiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                In_Monto_RetiroActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                            .addComponent(In_Monto_Retiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(operacionRetiro, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(operacionRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(In_Monto_Retiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        txttotalretiros.setBackground(resourceMap.getColor("txttotalretiros.background")); // NOI18N
        txttotalretiros.setFont(resourceMap.getFont("txttotalretiros.font")); // NOI18N
        txttotalretiros.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotalretiros.setText(resourceMap.getString("txttotalretiros.text")); // NOI18N
        txttotalretiros.setEnabled(false);
        txttotalretiros.setName("txttotalretiros"); // NOI18N

        txtmontoretiros.setBackground(resourceMap.getColor("txtmontoretiros.background")); // NOI18N
        txtmontoretiros.setFont(resourceMap.getFont("txttotalretiros.font")); // NOI18N
        txtmontoretiros.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontoretiros.setText(resourceMap.getString("txtmontoretiros.text")); // NOI18N
        txtmontoretiros.setEnabled(false);
        txtmontoretiros.setName("txtmontoretiros"); // NOI18N

        jLabel32.setFont(resourceMap.getFont("jLabel32.font")); // NOI18N
        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmontoretiros)
                            .addComponent(txttotalretiros, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txttotalretiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtmontoretiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tabladepositos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OPERACION", "MONTO ( S./ )"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabladepositos.setName("tabladepositos"); // NOI18N
        tabladepositos.setSelectionBackground(resourceMap.getColor("tabladepositos.selectionBackground")); // NOI18N
        tabladepositos.setSelectionForeground(resourceMap.getColor("tabladepositos.selectionForeground")); // NOI18N
        jScrollPane2.setViewportView(tabladepositos);
        tabladepositos.getColumnModel().getColumn(0).setResizable(false);
        tabladepositos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabladepositos.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tabladepositos.columnModel.title0")); // NOI18N
        tabladepositos.getColumnModel().getColumn(1).setResizable(false);
        tabladepositos.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabladepositos.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tabladepositos.columnModel.title1")); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        txttotaldepositos.setBackground(resourceMap.getColor("txttotaldepositos.background")); // NOI18N
        txttotaldepositos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotaldepositos.setText(resourceMap.getString("txttotaldepositos.text")); // NOI18N
        txttotaldepositos.setEnabled(false);
        txttotaldepositos.setName("txttotaldepositos"); // NOI18N

        txtmontodeporitos.setBackground(resourceMap.getColor("txtmontodeporitos.background")); // NOI18N
        txtmontodeporitos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontodeporitos.setText(resourceMap.getString("txtmontodeporitos.text")); // NOI18N
        txtmontodeporitos.setEnabled(false);
        txtmontodeporitos.setName("txtmontodeporitos"); // NOI18N

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setIcon(resourceMap.getIcon("jButton12.icon")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel8.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel8.border.titleColor"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        OperacionDeposito.setFont(resourceMap.getFont("OperacionDeposito.font")); // NOI18N
        OperacionDeposito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        OperacionDeposito.setText(resourceMap.getString("OperacionDeposito.text")); // NOI18N
        OperacionDeposito.setName("OperacionDeposito"); // NOI18N

        In_Monto_Deposito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        In_Monto_Deposito.setText(resourceMap.getString("In_Monto_Deposito.text")); // NOI18N
        In_Monto_Deposito.setName("In_Monto_Deposito"); // NOI18N
        In_Monto_Deposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                In_Monto_DepositoActionPerformed(evt);
            }
        });

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText(resourceMap.getString("jButton16.text")); // NOI18N
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(OperacionDeposito)
                    .addComponent(In_Monto_Deposito, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addGap(56, 56, 56))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OperacionDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(In_Monto_Deposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel33.setFont(resourceMap.getFont("jLabel33.font")); // NOI18N
        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmontodeporitos)
                            .addComponent(txttotaldepositos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttotaldepositos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtmontodeporitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)))
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel9.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel9.border.titleColor"))); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " EN SOLES   \tS/.", " EN DOLARES\t" }));
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setName("jTextField6"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        IN_Operacion_Servios.setFont(resourceMap.getFont("IN_Operacion_Servios.font")); // NOI18N
        IN_Operacion_Servios.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        IN_Operacion_Servios.setName("IN_Operacion_Servios"); // NOI18N

        In_Monto_Servcios.setFont(resourceMap.getFont("In_Monto_Servcios.font")); // NOI18N
        In_Monto_Servcios.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        In_Monto_Servcios.setName("In_Monto_Servcios"); // NOI18N
        In_Monto_Servcios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                In_Monto_ServciosActionPerformed(evt);
            }
        });

        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                            .addComponent(In_Monto_Servcios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(IN_Operacion_Servios, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(IN_Operacion_Servios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(In_Monto_Servcios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton21.setIcon(resourceMap.getIcon("jButton21.icon")); // NOI18N
        jButton21.setName("jButton21"); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        txtmontoservicios.setBackground(resourceMap.getColor("txtmontoservicios.background")); // NOI18N
        txtmontoservicios.setFont(resourceMap.getFont("txtmontoservicios.font")); // NOI18N
        txtmontoservicios.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontoservicios.setEnabled(false);
        txtmontoservicios.setName("txtmontoservicios"); // NOI18N

        txttotalservicios.setBackground(resourceMap.getColor("txtmontoservicios.background")); // NOI18N
        txttotalservicios.setFont(resourceMap.getFont("txttotalservicios.font")); // NOI18N
        txttotalservicios.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotalservicios.setEnabled(false);
        txttotalservicios.setName("txttotalservicios"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tablaservicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OPERACION", "MONTO ( S./ )"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaservicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaservicios.setName("tablaservicios"); // NOI18N
        tablaservicios.setSelectionBackground(resourceMap.getColor("tablaservicios.selectionBackground")); // NOI18N
        tablaservicios.setSelectionForeground(resourceMap.getColor("tablaservicios.selectionForeground")); // NOI18N
        tablaservicios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaservicios.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaservicios);
        tablaservicios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaservicios.getColumnModel().getColumn(0).setResizable(false);
        tablaservicios.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaservicios.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title0")); // NOI18N
        tablaservicios.getColumnModel().getColumn(1).setResizable(false);
        tablaservicios.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaservicios.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title1")); // NOI18N

        jButton22.setIcon(resourceMap.getIcon("jButton22.icon")); // NOI18N
        jButton22.setName("jButton22"); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel34.setFont(resourceMap.getFont("jLabel34.font")); // NOI18N
        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmontoservicios)
                            .addComponent(txttotalservicios, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txttotalservicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtmontoservicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        tablapagoscuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OPERACION", "MONTO ( S./ )"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablapagoscuenta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablapagoscuenta.setName("tablapagoscuenta"); // NOI18N
        tablapagoscuenta.setSelectionBackground(resourceMap.getColor("tablapagoscuenta.selectionBackground")); // NOI18N
        tablapagoscuenta.setSelectionForeground(resourceMap.getColor("tablapagoscuenta.selectionForeground")); // NOI18N
        tablapagoscuenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablapagoscuenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tablapagoscuenta);
        tablapagoscuenta.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablapagoscuenta.getColumnModel().getColumn(0).setResizable(false);
        tablapagoscuenta.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablapagoscuenta.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title0")); // NOI18N
        tablapagoscuenta.getColumnModel().getColumn(1).setResizable(false);
        tablapagoscuenta.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablapagoscuenta.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaretiros.columnModel.title1")); // NOI18N

        txttotalpagoscuenta.setBackground(resourceMap.getColor("txttotalpagoscuenta.background")); // NOI18N
        txttotalpagoscuenta.setFont(resourceMap.getFont("txttotalpagoscuenta.font")); // NOI18N
        txttotalpagoscuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txttotalpagoscuenta.setEnabled(false);
        txttotalpagoscuenta.setName("txttotalpagoscuenta"); // NOI18N

        jButton23.setIcon(resourceMap.getIcon("jButton23.icon")); // NOI18N
        jButton23.setName("jButton23"); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel10.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel10.border.titleColor"))); // NOI18N
        jPanel10.setName("jPanel10"); // NOI18N

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " EN SOLES   \tS/.", " EN DOLARES\t" }));
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setName("jTextField9"); // NOI18N

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        IN_Operacion_Cuenta.setFont(resourceMap.getFont("IN_Operacion_Cuenta.font")); // NOI18N
        IN_Operacion_Cuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        IN_Operacion_Cuenta.setName("IN_Operacion_Cuenta"); // NOI18N

        In_Monto_Cuenta.setFont(resourceMap.getFont("In_Monto_Cuenta.font")); // NOI18N
        In_Monto_Cuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        In_Monto_Cuenta.setName("In_Monto_Cuenta"); // NOI18N
        In_Monto_Cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                In_Monto_CuentaActionPerformed(evt);
            }
        });

        jButton26.setText(resourceMap.getString("jButton26.text")); // NOI18N
        jButton26.setName("jButton26"); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText(resourceMap.getString("jButton27.text")); // NOI18N
        jButton27.setName("jButton27"); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                            .addComponent(In_Monto_Cuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(IN_Operacion_Cuenta, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(IN_Operacion_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(In_Monto_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26)
                    .addComponent(jButton27))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton28.setIcon(resourceMap.getIcon("jButton28.icon")); // NOI18N
        jButton28.setName("jButton28"); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        txtmontopagoscuenta.setBackground(resourceMap.getColor("txttotalpagoscuenta.background")); // NOI18N
        txtmontopagoscuenta.setFont(resourceMap.getFont("txtmontopagoscuenta.font")); // NOI18N
        txtmontopagoscuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontopagoscuenta.setEnabled(false);
        txtmontopagoscuenta.setName("txtmontopagoscuenta"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel35.setFont(resourceMap.getFont("jLabel35.font")); // NOI18N
        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmontopagoscuenta)
                            .addComponent(txttotalpagoscuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttotalpagoscuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(txtmontopagoscuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

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

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(291, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton11)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
}//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
}//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (listacajas.size() > 0) {
            idcaja = listacajas.get(this.jComboBox1.getSelectedIndex() - 1).getIdcaja();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if (Lista_Cajeros.size() > 0) {
            idcajero = Lista_Cajeros.get(this.jComboBox2.getSelectedIndex() - 1).getId_Personal();
            this.jButton10.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (Verifica_Datos()) {
            turno = Lista_Turnos.get(jComboBox7.getSelectedIndex() - 1).getId_Turno();
            idcaja = listacajas.get(jComboBox1.getSelectedIndex() - 1).getIdcaja();
            VerificaInformacion();
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private boolean Verifica_Datos() {

        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Caja ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione Cajero ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (jComboBox7.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Porfavor Seleccione su Turno ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }

        return false;

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " DESEAS REALIZAR OTRA BUSQUEDA  ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            this.jButton10.setEnabled(true);
            DeshabilitaBusqueda(true);
            LimpiarTodo();
            Oculta(false);
            idcajero = 0;
            idcaja = 0;
            Fecha = "";
            this.operacion = "";
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " DESEAS SALIR ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            objeto.marcacdo = false;
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Deshabilita_Ingreso_ERetiros(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Deshabilita_Ingreso_Depositos(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        Deshabilita_Pagos_Servicios(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        Deshabilita_Pagos_Cuenta(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        Guarda_PagosCuenta();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        GuardaRetiro();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        GuardaDeposito();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        Guarda_PagosServicos();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Deshabilita_Ingreso_ERetiros(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        Deshabilita_Ingreso_Depositos(false);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        Deshabilita_Pagos_Servicios(false);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        Deshabilita_Pagos_Cuenta(false);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int fila = this.tablaretiros.getSelectedRow();

        if (fila >= 0) {

            String movimiento = String.valueOf(this.tablaretiros.getValueAt(fila, 0));
            double valor = Double.parseDouble(this.tablaretiros.getValueAt(fila, 1).toString());
            Confirmar p = new Confirmar(objetoventana, "<html> SEGURA DE ELIMINAR ESTE MOVIMIENTO :" + movimiento + " </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                logbcp.Elimina_Movimiento_BCP(idbotica, movimiento, logfecha.ConvierteFecha(this.jXDatePicker1.getDate()));
                modelo_retiros.removeRow(fila);
                monto_Retiros = monto_Retiros - valor;
                numero_retiros--;

                BigDecimal bd = new BigDecimal(monto_Retiros);
                this.txttotalretiros.setText(String.valueOf(numero_retiros));
                this.txtmontoretiros.setText(bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int fila = this.tabladepositos.getSelectedRow();

        if (fila >= 0) {

            String movimiento = String.valueOf(this.tabladepositos.getValueAt(fila, 0));
            double valor = Double.parseDouble(this.tabladepositos.getValueAt(fila, 1).toString());
            Confirmar p = new Confirmar(objetoventana, "<html> SEGURA DE ELIMINAR ESTE MOVIMIENTO :" + movimiento + " </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                logbcp.Elimina_Movimiento_BCP(idbotica, movimiento, logfecha.ConvierteFecha(this.jXDatePicker1.getDate()));
                modelo_depositos.removeRow(fila);
                monto_depositos = monto_depositos - valor;
                numero_depositos--;

                BigDecimal bd1 = new BigDecimal(monto_depositos);
                this.txttotaldepositos.setText(String.valueOf(numero_depositos));
                this.txtmontodeporitos.setText(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            }

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        int fila = this.tablaservicios.getSelectedRow();

        if (fila >= 0) {

            String movimiento = String.valueOf(this.tablaservicios.getValueAt(fila, 0));
            double valor = Double.parseDouble(this.tablaservicios.getValueAt(fila, 1).toString());
            Confirmar p = new Confirmar(objetoventana, "<html> SEGURA DE ELIMINAR ESTE MOVIMIENTO :" + movimiento + " </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                logbcp.Elimina_Movimiento_BCP(idbotica, movimiento, logfecha.ConvierteFecha(this.jXDatePicker1.getDate()));
                modelo_servisios.removeRow(fila);

                monto_servisios = monto_servisios - valor;
                numero_servisios--;

                BigDecimal bd2 = new BigDecimal(monto_servisios);
                this.txttotalservicios.setText(String.valueOf(numero_servisios));
                this.txtmontoservicios.setText(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

            }

        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        int fila = this.tablapagoscuenta.getSelectedRow();

        if (fila >= 0) {

            String movimiento = String.valueOf(this.tablapagoscuenta.getValueAt(fila, 0));
            double valor = Double.parseDouble(this.tablapagoscuenta.getValueAt(fila, 1).toString());
            Confirmar p = new Confirmar(objetoventana, "<html> SEGURA DE ELIMINAR ESTE MOVIMIENTO :" + movimiento + " </html>");
            p.show(true);

            if (p.getConfirmar() == 1) {
                logbcp.Elimina_Movimiento_BCP(idbotica, movimiento, logfecha.ConvierteFecha(this.jXDatePicker1.getDate()));
                modelo_pagos_cuenta.removeRow(fila);

                monto_pagos_cuenta = monto_pagos_cuenta - valor;
                numero_pagos_cuenta--;

                BigDecimal bd3 = new BigDecimal(monto_pagos_cuenta);
                this.txttotalpagoscuenta.setText(String.valueOf(numero_pagos_cuenta));
                this.txtmontopagoscuenta.setText(bd3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());


            }

        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void In_Monto_RetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_In_Monto_RetiroActionPerformed
        GuardaRetiro();
    }//GEN-LAST:event_In_Monto_RetiroActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {

            showThread = new Thread(this);
            showThread.start();

        } catch (Exception ex) {
        }



    }//GEN-LAST:event_jButton11ActionPerformed

    private void In_Monto_CuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_In_Monto_CuentaActionPerformed
        Guarda_PagosCuenta();
    }//GEN-LAST:event_In_Monto_CuentaActionPerformed

    private void In_Monto_ServciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_In_Monto_ServciosActionPerformed
        Guarda_PagosServicos();
    }//GEN-LAST:event_In_Monto_ServciosActionPerformed

    private void In_Monto_DepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_In_Monto_DepositoActionPerformed
        GuardaDeposito();
    }//GEN-LAST:event_In_Monto_DepositoActionPerformed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IN_Operacion_Cuenta;
    private javax.swing.JTextField IN_Operacion_Servios;
    private javax.swing.JTextField In_Monto_Cuenta;
    private javax.swing.JTextField In_Monto_Deposito;
    private javax.swing.JTextField In_Monto_Retiro;
    private javax.swing.JTextField In_Monto_Servcios;
    private javax.swing.JTextField OperacionDeposito;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JTextField operacionRetiro;
    private javax.swing.JTable tabladepositos;
    private javax.swing.JTable tablapagoscuenta;
    private javax.swing.JTable tablaretiros;
    private javax.swing.JTable tablaservicios;
    private javax.swing.JTextField txtmontodeporitos;
    private javax.swing.JTextField txtmontopagoscuenta;
    private javax.swing.JTextField txtmontoretiros;
    private javax.swing.JTextField txtmontoservicios;
    private javax.swing.JTextField txttotaldepositos;
    private javax.swing.JTextField txttotalpagoscuenta;
    private javax.swing.JTextField txttotalretiros;
    private javax.swing.JTextField txttotalservicios;
    // End of variables declaration//GEN-END:variables
}
