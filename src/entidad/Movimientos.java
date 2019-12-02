/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Movimientos {

    private Boticas botica;
    private String Id_TipoAlmacen;
    private String Id_Proveedor;
    private String Numero_Documento;
    private Date Fecha_Documento;
    private String responsable;
    private String tipo_movimiento;
    private Date Fecha_Recepcion;
    private Date Fecha_Registro;
    private String cantidad_Pedida;
    private String Id_Producto;
    private String Descripcion;
    private String Id_Laboratorio;
    private double Precio_Venta_Final;
    private double Total;
    private int iderror;
    private String Hora;
    private String serie;
    private String numero;
    private int limite;
    
    private int codcli;
    private String Id_Venta;
    private Date Fecha_Venta;
    private String Nombre_RazonSocial;
    private String RUC_DNI;

    public String getId_Venta() {
        return Id_Venta;
    }

    public Date getFecha_Venta() {
        return Fecha_Venta;
    }

    public String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setId_Venta(String Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public void setFecha_Venta(Date Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }

    public void setNombre_RazonSocial(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public void setRUC_DNI(String RUC_DNI) {
        this.RUC_DNI = RUC_DNI;
    }

    

    public Movimientos() {
    }

    public Movimientos(int iderror, String idproduc, String docmuento) {
        this.iderror = iderror;
        this.Id_Producto = idproduc;
        this.Numero_Documento = docmuento;
    }

    public Movimientos(String Id_TipoAlmacen, String Id_Proveedor, String Numero_Documento, Date Fecha_Documento, String responsable) {
        this.Id_TipoAlmacen = Id_TipoAlmacen;
        this.Id_Proveedor = Id_Proveedor;
        this.Numero_Documento = Numero_Documento;
        this.Fecha_Documento = Fecha_Documento;
        this.responsable = responsable;
    }

    public Movimientos(Date Fecha_Documento, String responsable, Date Fecha_Recepcion, Date Fecha_Registro) {
        this.Fecha_Documento = Fecha_Documento;
        this.responsable = responsable;
        this.Fecha_Recepcion = Fecha_Recepcion;
        this.Fecha_Registro = Fecha_Registro;
    }

    public Movimientos(String Id_TipoAlmacen, String Numero_Documento, String tipo_movimiento) {
        this.Id_TipoAlmacen = Id_TipoAlmacen;
        this.Numero_Documento = Numero_Documento;
        this.tipo_movimiento = tipo_movimiento;
    }

    public Movimientos(String Id_Producto, String Descripcion, String Id_Laboratorio, String cantidad_Pedida, double Precio_Venta_Final, double Total) {
        this.cantidad_Pedida = cantidad_Pedida;
        this.Id_Producto = Id_Producto;
        this.Descripcion = Descripcion;
        this.Id_Laboratorio = Id_Laboratorio;
        this.Precio_Venta_Final = Precio_Venta_Final;
        this.Total = Total;
    }
    
    public Movimientos(String serie, String Numero, int limite) {
        this.serie = serie;
        this.numero = Numero; 
        this.limite = limite;
    }
    
    
    
    

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }
       
        
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Boticas getBotica() {
        return botica;
    }

    public void setBotica(Boticas botica) {
        this.botica = botica;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public int getIderror() {
        return iderror;
    }

    public void setIderror(int iderror) {
        this.iderror = iderror;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getId_Laboratorio() {
        return Id_Laboratorio;
    }

    public void setId_Laboratorio(String Id_Laboratorio) {
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public double getPrecio_Venta_Final() {
        return Precio_Venta_Final;
    }

    public void setPrecio_Venta_Final(double Precio_Venta_Final) {
        this.Precio_Venta_Final = Precio_Venta_Final;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getCantidad_Pedida() {
        return cantidad_Pedida;
    }

    public void setCantidad_Pedida(String cantidad_Pedida) {
        this.cantidad_Pedida = cantidad_Pedida;
    }

    public Date getFecha_Recepcion() {
        return Fecha_Recepcion;
    }

    public void setFecha_Recepcion(Date Fecha_Recepcion) {
        this.Fecha_Recepcion = Fecha_Recepcion;
    }

    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(Date Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Date getFecha_Documento() {
        return Fecha_Documento;
    }

    public void setFecha_Documento(Date Fecha_Documento) {
        this.Fecha_Documento = Fecha_Documento;
    }

    public String getId_Proveedor() {
        return Id_Proveedor;
    }

    public void setId_Proveedor(String Id_Proveedor) {
        this.Id_Proveedor = Id_Proveedor;
    }

    public String getId_TipoAlmacen() {
        return Id_TipoAlmacen;
    }

    public void setId_TipoAlmacen(String Id_TipoAlmacen) {
        this.Id_TipoAlmacen = Id_TipoAlmacen;
    }

    public String getNumero_Documento() {
        return Numero_Documento;
    }

    public void setNumero_Documento(String Numero_Documento) {
        if (Numero_Documento != null) {
            this.Numero_Documento = Numero_Documento;
        } else {
            this.Numero_Documento = "";
        }
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
