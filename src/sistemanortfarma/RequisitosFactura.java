/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanortfarma;

import CapaLogica.LogicaDocumentosVentas;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaTipoVenta;
import CapaLogica.LogicaTiposPagos;
import entidad.Producto;
import entidad.Productos_Botica;
import entidad.TipoVenta;
import entidad.TiposPagos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Gomez S. Gomez
 */
public class RequisitosFactura {

    private String resultado = null;
    List<TipoVenta> lis_tipo_venta = new ArrayList<TipoVenta>();
    List<TipoVenta> lis_colegios = new ArrayList<TipoVenta>();
    LogicaTipoVenta logtipoventa = new LogicaTipoVenta();
    List<TiposPagos> listatipospagos = new ArrayList<TiposPagos>();
    List<TiposPagos> listatiposMoney = new ArrayList<TiposPagos>();
    LogicaTiposPagos objlogicapagos = new LogicaTiposPagos();
    LogicaProducto objProducto = new LogicaProducto();
    

    public RequisitosFactura() {
    }
    /*
     * VERIFICA LOS DATOS INGRESADOS
    PARA GENERAR LOS DATOS DE UNA VENTA
     *
     *      1  - RUC
     *      2 - CLIENTE
     *      3 - DIRECCION
     */

    public String Verifica_Datos_Factura(int tipventa, String factura, String cliente, String direccion) {
        resultado = null;
        LogicaDocumentosVentas verifica = new LogicaDocumentosVentas();
        int resul = verifica.Verifica_esFactura(tipventa);
        int validaRUC = verifica.Verifica_Validacion();

        String sCadena = "54327654320";
        char[] aCaracteres = factura.toCharArray();
        char[] aFijos = sCadena.toCharArray();
        double mto = 0;
        double sumatotal = 0;
        String valorfinal;
        String ultimovalorRUC="";
        
        for (int x=0;x<aCaracteres.length;x++){            
            //System.out.println("[" + x + "]" + Double.parseDouble(String.valueOf(aCaracteres[x])) * Double.parseDouble(String.valueOf(aFijos[x])));
            mto = Double.parseDouble(String.valueOf(aCaracteres[x])) * Double.parseDouble(String.valueOf(aFijos[x]));
            sumatotal+=mto;            
        }               
        
        double x = sumatotal/11;
        int entero = (int)x;
        double resultValidacionRUC = (11 - (sumatotal-(entero * 11)));
        int resultRuc = (int) Math.floor(resultValidacionRUC);

        if (resul == 1 && factura.length() > 0){
            ultimovalorRUC = String.valueOf(aCaracteres[10]);
        }else if (resul == 1 && factura.compareTo("") == 0){
            ultimovalorRUC = "";
        }

        int contar = String.valueOf(resultRuc).length();

        if (contar > 1 ){
            String numero = String.valueOf(resultRuc);
            char recupera[] = numero.toCharArray();
            valorfinal = String.valueOf(recupera[1]);
        }else{

            valorfinal = String.valueOf(resultRuc);
        }

        
        if (resul == 1 && validaRUC == 1 && factura.length() > 0 && (!ultimovalorRUC.equals(valorfinal)) ){
            resultado = "1LO SENTIMOS \n EL NUMERO DE RUC INGRESADO NO ES CORRECTO";
        }else{
        if (resul == 1 && factura.compareTo("") == 0) {
            resultado = "1LO SENTIMOS \n PARA FACTURA SE REQUIERE INGRESAR UN NUMERO DE RUC ";  //DEBE DE INGRESAR UN NUMERO DE RUC
        } else {
            if (resul == 1 && factura.length() < 11 || factura.length() > 11) {
                resultado = "1LO SENTIMOS \n DEBE INGRESAR UN NUMERO DE RUC CORRECTO";
            } else {
                if (resul == 1 && factura.compareTo("") == 0) {
                    resultado = "2PORFAVOR INGRESE UN NOMBRE DE CLIENTE PARA ESTA VENTA";
                } else {
                    if (resul == 1 && cliente.compareTo("CLIENTE COMUN") == 0) {
                        resultado = "2PORFAVOR INGRESE EL NOMBRE DE CLIENTE PARA ESTA VENTA";
                    } else if (resul == 1 && cliente.compareTo("") == 0) {
                        resultado = "2PORFAVOR INGRESE EL NOMBRE DE CLIENTE PARA ESTA VENTA";
                    } else if (resul == 1 && direccion.compareTo("") == 0) {
                        resultado = "3PORFAVOR ES NECESARIO QUE INGRESE UNA DIRECCION DEL CLIENTE";
                    }

                }
            }
        }
        }
        return resultado;

    }

    /*
    VERIFICA  DATOS OBLIGATORIOS  DE UNA BOLETA CUENADO
    EL MONTO ES UNA SUPERIOR A UN MONTO ESPECIFICADO
     */
    public boolean Verifica_Datos_Boleta(int idtipoventa, double total, String dni, int pos, String razonsocial, Object direccion) {
        boolean flag = true;
        int bandera = 0;
        if (idtipoventa == 11){
            
        
        RecuperaTipoVentas();
        if (lis_tipo_venta.get(pos).getMonto() > 0) {
            if (total >= lis_tipo_venta.get(pos).getMonto()) {
                bandera = 1;
                if (dni.length() != 8) {
                    flag = false;
                } else {
                    if (razonsocial.length() == 0) {
                        flag = false;
                    } else {
                        if (razonsocial.equals("CLIENTE COMUN")) {
                        flag = false;
                        } else {
                            if (direccion.equals("")) {
                                flag = false;
                            }                            
                        }
                    }
                }
            }
        }
        }
        return flag;

    }

    /*
     * RECUPERA LOS TIPOS DE VENTAS EXISTENTES
     * BOLETA , FACTURA, TICKET
     */
    public List<TipoVenta> Retorna_Tipos_Ventas() {
        lis_tipo_venta.removeAll(lis_tipo_venta);
        RecuperaTipoVentas();
        return lis_tipo_venta;
    }
    public List<TipoVenta> Retorna_Colegios() {
        lis_colegios.removeAll(lis_colegios);
        RecuperaColegios();
        return lis_colegios;
    }
    public List<TipoVenta> Retorna_Tipos_VentasSOAT() {
        lis_tipo_venta.removeAll(lis_tipo_venta);
        RecuperaTipoVentasSOAT();
        return lis_tipo_venta;
    }

    /*
     * METODO QUE RECUPERA LOS TIPOS DE PAGOS EXISTENTES
     * CASH, CREDITO,TARJETAS , ETC ....
     */
    public List<TiposPagos> Recupera_Tipos_Pagos(int op) {
        try {
            listatipospagos = objlogicapagos.retornaTipoPagos(op);
        } catch (Exception ex) {
            Logger.getLogger(RequisitosFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listatipospagos;

    }
    public List<TiposPagos> Recupera_Moneda() {
        try {
            listatiposMoney = objlogicapagos.retornaMoneda();
        } catch (Exception ex) {
            Logger.getLogger(RequisitosFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listatiposMoney;

    }


    public List<TiposPagos> Recupera_Tipos_PagosSOAT(int op) {
        try {
            listatipospagos = objlogicapagos.retornaTipoPagosSOAT(op);
        } catch (Exception ex) {
            Logger.getLogger(RequisitosFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listatipospagos;

    }

    private void RecuperaTipoVentas() {
        lis_tipo_venta = logtipoventa.Lista_TiposVentas();
    }
    private void RecuperaColegios() {
        lis_colegios = logtipoventa.Lista_colegios();
    }

    private void RecuperaTipoVentasSOAT(){
        lis_tipo_venta = logtipoventa.Lista_TiposVentasSOAT();
    }

    /*
     * METODO QUE RETORNA LAS LISTAS DE
     * EMPAQUE, STOCK EMPAQUE Y STOCK FRACCION DE MOSTRADOR
     */
    public List<Productos_Botica> Retorna_Producto_Stock(String idproduc, String id_botica) {
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();
        empRecuperado = objProducto.RecuperEmpaque(idproduc, id_botica);
        return empRecuperado;
    }

    /*
     * METODO QUE RETORNA LAS LISTAS DE
     * EMPAQUE, STOCK EMPAQUE Y STOCK FRACCION DE ALMACEN
     */
    public List<Productos_Botica> Retorna_Producto_Stock(String idproduc, String id_botica, String almacen) {
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();
        empRecuperado = objProducto.RecuperEmpaque_Almacen(idproduc, id_botica, almacen);
        return empRecuperado;
    }
}
