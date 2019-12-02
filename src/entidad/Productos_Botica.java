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
public class Productos_Botica {

    private String Id_Botica;
    private Producto producto;
    private int Almacen_Stock_Empaque;
    private int Almacen_Stock_Fraccion;
    private int Mostrador_Stock_Empaque;
    private int Mostrador_Stock_Fraccion;
    private int Total_Stock_Empaque;
    private int Total_Stock_Fraccion;
    private int TrastiendaStockEmpaque;
    private int TrastiendaStockFraccion;
    private String Numero_Lote;
    private String CodBarras;
    private String CodBarras1;
    private Date Fecha_Vencimiento_Lote;
    private String temperatura;

    public int getTrastiendaStockEmpaque() {
        return TrastiendaStockEmpaque;
    }

    public int getTrastiendaStockFraccion() {
        return TrastiendaStockFraccion;
    }

    public void setTrastiendaStockEmpaque(int TrastiendaStockEmpaque) {
        this.TrastiendaStockEmpaque = TrastiendaStockEmpaque;
    }

    public void setTrastiendaStockFraccion(int TrastiendaStockFraccion) {
        this.TrastiendaStockFraccion = TrastiendaStockFraccion;
    }

    
    
    
    public Date getFecha_Vencimiento_Lote() {
        return Fecha_Vencimiento_Lote;
    }

    public void setFecha_Vencimiento_Lote(Date Fecha_Vencimiento_Lote) {
        this.Fecha_Vencimiento_Lote = Fecha_Vencimiento_Lote;
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

    public String getCodBarras() {
        return CodBarras;
    }

    public void setCodBarras(String CodBarras) {
        this.CodBarras = CodBarras;
    }

    public String gettemperatura() {
        return temperatura;
    }

    public void settemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getCodBarras1() {
        return CodBarras1;
    }

    public void setCodBarras1(String CodBarras1) {
        this.CodBarras1 = CodBarras1;
    }

    public String getId_Botica() {
        return Id_Botica;
    }

    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
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

    public String getNumero_Lote() {
        return Numero_Lote;
    }

    public void setNumero_Lote(String Numero_Lote) {
        this.Numero_Lote = Numero_Lote;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
