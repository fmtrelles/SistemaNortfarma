/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.sql.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Movimiento_Detalle {

    private Movimientos movimiento;
    private int Id_Movimiento_Detalle;
    private Producto Id_Producto;
    private Double Precio_Venta;
    private Double Descuento;
    private int unidad;
    private int fraccion;
    private Double Precio_Venta_Final;
    private Double Total;
    private Double TotalXguia;
    private double Cantidad_En_Empaque;
    private int Almacen_Stock_Empaque;
    private int Almacen_Stock_Fraccion;
    private int Mostrador_Stock_Empaque;
    private int Mostrador_Stock_Fraccion;
    private int Total_Stock_Empaque;
    private int Total_Stock_Fraccion;
    private Date Fecha_Registro;
    private int Id_Personal;
    private String Serie;
    private String Numero;
    private String idguiaajustedet;
    private String botica;
    private String idproducto;
    private String idguiaajuste;
    private String tipmov;
    private String nunmov;
    private int contar;
    private String compuesto;
    private String lab;
    private String descripcion;
    private String codbarras;
    private String fecvcto;
    private String idventa;
    private String Norden;
    private String Ncajas;
    private String RS;
/*-----------------------------------------------------*/

    public String getRS() {
        return RS;
    }

    public void setRS(String RS) {
        this.RS = RS;
    }    
    
    
    public String getNorden() {
        return Norden;
    }

    public String getNcajas() {
        return Ncajas;
    }

    public void setNorden(String Norden) {
        this.Norden = Norden;
    }

    public void setNcajas(String Ncajas) {
        this.Ncajas = Ncajas;
    }    
       
    public String getIdventa() {
        return idventa;
    }

    public void setIdventa(String idventa) {
        this.idventa = idventa;
    }

    public int getContar() {
        return contar;
    }

    public Double getTotalXguia() {
        return TotalXguia;
    }

    public void setTotalXguia(Double TotalXguia) {
        this.TotalXguia = TotalXguia;
    }

    public void setContar(int contar) {
        this.contar = contar;
    }

    public String getCompuesto() {
        return compuesto;
    }

    public String getLab() {
        return lab;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCompuesto(String compuesto) {
        this.compuesto = compuesto;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getidguiaajuste() {
        return idguiaajuste;
    }
    public void setidguiaajuste(String idguiaajuste) {
        this.idguiaajuste = idguiaajuste;
    }

    public String gettipmov() {
        return tipmov;
    }
    public void settipmov(String tipmov) {
        this.tipmov = tipmov;
    }

    public String getnunmov() {
        return nunmov;
    }
    public void setnunmov(String nunmov) {
        this.nunmov = nunmov;
    }


    public String getidproducto() {
        return idproducto;
    }

    public void setidproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getidguiaajustedet() {
        return idguiaajustedet;
    }

    public void setidguiaajustedet(String idguiaajustedet) {
        this.idguiaajustedet = idguiaajustedet;
    }

    public String getbotica() {
        return botica;
    }

    public void setbotica(String botica) {
        this.botica = botica;
    }

    public int getAlmacen_Stock_Empaque() {
        return Almacen_Stock_Empaque;
    }

    public void setAlmacen_Stock_Empaque(int Almacen_Stock_Empaque) {
        this.Almacen_Stock_Empaque = Almacen_Stock_Empaque;
    }

    public int getAlmacen_Stock_Fraccion() {
        return Almacen_Stock_Fraccion;
    }

    public void setAlmacen_Stock_Fraccion(int Almacen_Stock_Fraccion) {
        this.Almacen_Stock_Fraccion = Almacen_Stock_Fraccion;
    }

    public double getCantidad_En_Empaque() {
        return Cantidad_En_Empaque;
    }

    public void setCantidad_En_Empaque(double Cantidad_En_Empaque) {
        this.Cantidad_En_Empaque = Cantidad_En_Empaque;
    }

    public Double getDescuento() {
        return Descuento;
    }

    public void setDescuento(Double Descuento) {
        this.Descuento = Descuento;
    }

    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(Date Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public int getId_Movimiento_Detalle() {
        return Id_Movimiento_Detalle;
    }

    public void setId_Movimiento_Detalle(int Id_Movimiento_Detalle) {
        this.Id_Movimiento_Detalle = Id_Movimiento_Detalle;
    }

    public int getId_Personal() {
        return Id_Personal;
    }

    public void setId_Personal(int Id_Personal) {
        this.Id_Personal = Id_Personal;
    }

    public Producto getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Producto Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public int getMostrador_Stock_Empaque() {
        return Mostrador_Stock_Empaque;
    }

    public void setMostrador_Stock_Empaque(int Mostrador_Stock_Empaque) {
        this.Mostrador_Stock_Empaque = Mostrador_Stock_Empaque;
    }

    public int getMostrador_Stock_Fraccion() {
        return Mostrador_Stock_Fraccion;
    }

    public void setMostrador_Stock_Fraccion(int Mostrador_Stock_Fraccion) {
        this.Mostrador_Stock_Fraccion = Mostrador_Stock_Fraccion;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public Double getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(Double Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public Double getPrecio_Venta_Final() {
        return Precio_Venta_Final;
    }

    public void setPrecio_Venta_Final(Double Precio_Venta_Final) {
        this.Precio_Venta_Final = Precio_Venta_Final;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public int getTotal_Stock_Empaque() {
        return Total_Stock_Empaque;
    }

    public void setTotal_Stock_Empaque(int Total_Stock_Empaque) {
        this.Total_Stock_Empaque = Total_Stock_Empaque;
    }

    public int getTotal_Stock_Fraccion() {
        return Total_Stock_Fraccion;
    }

    public void setTotal_Stock_Fraccion(int Total_Stock_Fraccion) {
        this.Total_Stock_Fraccion = Total_Stock_Fraccion;
    }

    public int getFraccion() {
        return fraccion;
    }

    public void setFraccion(int fraccion) {
        this.fraccion = fraccion;
    }

    public Movimientos getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimientos movimiento) {
        this.movimiento = movimiento;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public Movimiento_Detalle() {
    }
    
    public String getcodbarras() {
        return codbarras;
    }
    public void setcodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public String getFecvcto() {
        return fecvcto;
    }

    public void setFecvcto(String fecvcto) {
        this.fecvcto = fecvcto;
    }
    
    

    public Movimiento_Detalle(String producto, int unidad, int fracc, String idguiaajustedet,String bot,
            String idguiaajuste,String tipmov,String nunmov) {
        this.idproducto = producto;
        this.unidad = unidad;
        this.fraccion = fracc;
        this.idguiaajustedet = idguiaajustedet;
        this.botica = bot;
        this.idguiaajuste = idguiaajuste;
        this.tipmov = tipmov;
        this.nunmov=nunmov;
    }

    
}
