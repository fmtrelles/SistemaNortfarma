/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class ListaDetalles {

    Integer IdPromo;
    Integer TotalPromocionales;
    Double Descuento;
    String CodPro;
    Integer CantidadPro;
    String correo;
    Integer orden;
    Integer numero;
    Integer posicion;
    private String descripPromocion;
    private String idLaboratorio;
    Integer idcliente;
    String razonsocial;
    String direccion;
    String dni;
    String dni1;

    public Integer getCantidadPro() {
        return CantidadPro;
    }

    public void setCantidadPro(Integer CantidadPro) {
        this.CantidadPro = CantidadPro;
    }

    public String getCodPro() {
        return CodPro;
    }

    public void setCodPro(String CodPro) {
        this.CodPro = CodPro;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Double getDescuento() {
        return Descuento;
    }

    public void setDescuento(Double Descuento) {
        this.Descuento = Descuento;
    }

    public Integer getIdPromo() {
        return IdPromo;
    }

    public void setIdPromo(Integer IdPromo) {
        this.IdPromo = IdPromo;
    }

    public Integer getnumero() {
        return numero;
    }

    public void setnumero(Integer numero) {
        this.numero = numero;
    }


    public Integer getidcliente() {
        return idcliente;
    }

    public void setidcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdPosicion() {
        return posicion;
    }

    public void setIdPosicion(Integer posicion) {
        this.posicion = posicion;
    }


    public Integer getTotalPromocionales() {
        return TotalPromocionales;
    }

    public String getDescripPromocion() {
        return descripPromocion;
    }

    public void setDescripPromocion(String descripPromocion) {
        this.descripPromocion = descripPromocion;
    }

    public String getrazonsocial() {
        return razonsocial;
    }
    public void setrazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getdireccion() {
        return direccion;
    }
    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getdni() {
        return dni;
    }
    public void setdni(String dni) {
        this.dni = dni;
    }
    public String getdni1() {
        return dni1;
    }
    public void setdni1(String dni1) {
        this.dni1 = dni1;
    }

    public String getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(String idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public void setTotalPromocionales(Integer TotalPromocionales) {
        this.TotalPromocionales = TotalPromocionales;
    }

    public ListaDetalles(Integer IdPromo, Integer TotalPromocionales, Double Descuento, Integer orden) {
        this.IdPromo = IdPromo;
        this.TotalPromocionales = TotalPromocionales;
        this.Descuento = Descuento;
        this.orden = orden;
    }

    public ListaDetalles(Integer numero) {
        this.numero = numero;
    }

    public ListaDetalles(Integer IdCliente, String RazonSocial, String Direccion, String DNI, String correo,String DNI1) {
        this.idcliente = IdCliente;
        this.razonsocial = RazonSocial;
        this.direccion = Direccion;
        this.dni = DNI;
        this.correo = correo;
        this.dni1 = DNI1;
    }

    public ListaDetalles(Integer IdPromo, Integer TotalPromocionales, Double Descuento) {
        this.IdPromo = IdPromo;
        this.TotalPromocionales = TotalPromocionales;
        this.Descuento = Descuento;
    }

    public ListaDetalles(Integer Idpromocion, String Descripcion, String Codigo_Promocion, double descuento, String idlab) {
        this.IdPromo = Idpromocion;
        this.Descuento = descuento;
        this.descripPromocion = Descripcion;
        this.CodPro = Codigo_Promocion;
        this.idLaboratorio = idlab;
    }

    public ListaDetalles(Integer IdPromo, Integer TotalPromocionales, Double Descuento, String CodPro, Integer orden) {
        this.IdPromo = IdPromo;
        this.TotalPromocionales = TotalPromocionales;
        this.Descuento = Descuento;
        this.CodPro = CodPro;
        this.orden = orden;
    }

    public ListaDetalles(Integer IdPromo, Integer TotalPromocionales, Double Descuento, String CodPro, Integer CantidadPro, Integer orden) {
        this.IdPromo = IdPromo;
        this.TotalPromocionales = TotalPromocionales;
        this.Descuento = Descuento;
        this.CodPro = CodPro;
        this.CantidadPro = CantidadPro;
        this.orden = orden;
    }
}
