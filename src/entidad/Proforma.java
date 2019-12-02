/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Proforma implements Comparator<Proforma>, Comparable<Proforma> {

    private String Id_Proforma;
    private String Id_Boticas;
    private int Id_Cliente;
    private int Id_TipoPago;
    private String Id_Tipo_Precio;
    private int Id_Tipo_Venta;
    private String Id_Medico;
    private double Redondeo;
    private double TotFinal;
    private Date Fecha_Venta;
    private double SubTotal;
    private double IGV;
    private String CertMed;
    private double Total;
    private Date Fecha_Registro;
    private int Id_Personal_Botica_Venta;
    private String RUC_DNI;
    private String Nombre_RazonSocial;
    private String cupon;
    private double TotEsGratuita;
    private String Direccion;
    private String DNI;
    private String DescripcionTipoPago;
    private String Descripciontipoventa;
    private String NombrePersonal;
    private String ApellidoPersonal;
    private String idproducto;
    private String esgratuitaPromo;
    private String esgratuita;
    private String descipcionproducto;
    private int UNIDAD;
    private int FRACCION;
    private int idOperadorRec;
    private double Precio_Venta;
    private double Descuento;
    private double pvx;
    private Date Fecha_Vencimiento;
    private double IGV_Exonerado;
    private int Empaque;
    private int Stock_Empaque;
    private int Stock_Fraccion;
    private String NomCliente;
    private String dniaux;
    private String idperosnalmodficada;
    private String DireccionProforma;
    private String RUC;
    private String id_proforma;
    private Double DsctoAdicional;
    private String colegiatura;
    private String id_laboratorio;
    private int atendida;
    private int IdEmpresa;
    private String telefono;

    private int clase;
    private int tipo;
    private int marca;
    private int modelo;
    private String asientos;
    private int zona;
    private String uso;
    private double precio;
    private String anio;
    private String serie;
    private double igvventa;
    private double OpExonerada;
    private double OpGravada;
    private double OpInafecta;
    private double OpGratuita;
    private double OpISC;
    
    private String placa;
    private String poliza;
    private String certificado;
    private String dep;
    private String prov;
    private String dist;
    private String fecha1;
    private String fecha2;
    private String comision;
    private String ordenproducto;
    private int Esgratispromo;
    private int cantbolsas;

    public Proforma() {
    }

    public int getCantbolsas() {
        return cantbolsas;
    }

    public void setCantbolsas(int cantbolsas) {
        this.cantbolsas = cantbolsas;
    }
    
    
    

    public String getCertMed() {
        return CertMed;
    }

    public void setCertMed(String CertMed) {
        this.CertMed = CertMed;
    }

    public void setEmpaque(int Empaque) {
        this.Empaque = Empaque;
    }

    public void setIGV_Exonerado(double IGV_Exonerado) {
        this.IGV_Exonerado = IGV_Exonerado;
    }

    public void setFecha_Vencimiento(Date Fecha_Vencimiento) {
        this.Fecha_Vencimiento = Fecha_Vencimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getRedondeo() {
        return Redondeo;
    }

    public void setRedondeo(Double Redondeo) {
        this.Redondeo = Redondeo;
    }

    public Double getTotFinal() {
        return TotFinal;
    }

    public void setTotFinal(Double TotFinal) {
        this.TotFinal = TotFinal;
    }
    
    public String getcupon() {
        return cupon;
    }

    public void setcupon(String cupon) {
        this.cupon = cupon;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }
    public int getEsgratispromo() {
        return Esgratispromo;
    }

    public void setEsgratispromo(int Esgratispromo) {
        this.Esgratispromo = Esgratispromo;
    }

    public int getAtendida() {
        return atendida;
    }

    public void setAtendida(int atendida) {
        this.atendida = atendida;
    }

    public Double getDsctoAdicional() {
        return DsctoAdicional;
    }

    public void setDsctoAdicional(Double DsctoAdicional) {
        this.DsctoAdicional = DsctoAdicional;
    }

    public Double getigvventa() {
        return igvventa;
    }

    public void setigvventa(Double igvventa) {
        this.igvventa = igvventa;
    }
    
    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(String id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
    }

    public String getOrdenProducto() {
        return ordenproducto;
    }

    public void setOrdenProducto(String ordenproducto) {
        this.ordenproducto = ordenproducto;
    }

    public Proforma(String idproforma, String descrip, double total, String vend, String cliente, int atendida) {
        this.id_proforma = idproforma;
        this.Descripciontipoventa = descrip;
        this.Total = total;
        this.NombrePersonal = vend;
        this.NomCliente = cliente;
        this.atendida = atendida;
    }

    public Proforma(String idproducto, double total) {
        this.idproducto = idproducto;
        this.Total = total;
    }

    public Proforma(String Id_Boticas, double SubTotal, double IGV, double Total, String idlab, String descproducto, int und, int fracc, double total) {
        this.Id_Boticas = Id_Boticas;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Total = Total;
        this.id_laboratorio = idlab;
        this.descipcionproducto = descproducto;
        this.UNIDAD = und;
        this.FRACCION = fracc;
        this.Total = total;

    }

    public Proforma(String Id_Boticas, String Id_Tipo_Precio, double SubTotal, double IGV, double Total, int Id_Personal_Botica_Venta, double pv, double dscventa, double pvpx, double total, double dscadicional, String idproducto) {
        this.Id_Boticas = Id_Boticas;
        this.Id_Tipo_Precio = Id_Tipo_Precio;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Total = Total;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Precio_Venta = pv;
        this.Descuento = dscventa;
        this.pvx = pvpx;
        this.DsctoAdicional = dscadicional;
        this.idproducto = idproducto;
    }
    
    public Proforma(String Id_Boticas, int Id_Cliente, int Id_TipoPago, String Id_Tipo_Precio, int Id_Tipo_Venta, String Id_Medico, Date Fecha_Venta, double SubTotal, double IGV, double Total, double Opgravada, double Opexonerada, double OpInafecta, double OpGratuita, double OpISC, Date Fecha_Registro, int Id_Personal_Botica_Venta, String nomcliente, String dni, String direccion, String ruc, String colegiatura, double igvventa,
            double Redondeo,double TotFinal, String cuponvta) {
        this.Id_Boticas = Id_Boticas;
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Precio = Id_Tipo_Precio;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Id_Medico = Id_Medico;
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.igvventa = igvventa;
        this.Total = Total;

        this.OpGravada = Opgravada;
        this.OpExonerada = Opexonerada;
        this.OpInafecta = OpInafecta;
        this.OpGratuita = OpGratuita;
        this.OpISC = OpISC;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Nombre_RazonSocial = nomcliente;
        this.DNI = dni;
        this.RUC = ruc;
        this.Direccion = direccion;
        this.colegiatura = colegiatura;
        this.Redondeo = Redondeo;
        this.TotFinal = TotFinal;
        this.cupon = cuponvta;
    }
    public Proforma (String nombreCliente, String ruc, String dni, int tpago, int tvta, double IGV){
        this.Nombre_RazonSocial = nombreCliente;
        this.RUC = ruc;
        this.DNI = dni;
        this.Id_TipoPago = tpago;
        this.Id_Tipo_Venta = tvta;
        this.IGV = IGV;
    }
    public Proforma(String Id_Boticas, int Id_Cliente, int Id_Tipo_Venta, double Total, int Id_Personal_Botica_Venta, String nomcliente, String dni, String direccion) {
        this.Id_Boticas = Id_Boticas;
        this.Id_Cliente = Id_Cliente;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Total = Total;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Nombre_RazonSocial = nomcliente;
        this.DNI = dni;
        this.Direccion = direccion;
    }

    //PARA RECUPERRAR LA cabecera de la  PROFORMA
    public Proforma(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String DNI, int Id_TipoPago, String DescripcionTipoPago, int Id_Tipo_Venta, String Descripciontipoventa, Date Fecha_Venta, int Id_Personal_Botica_Venta, String NombrePersonal, String ApellidoPersonal, double SubTotal, double IGV, double Total, Date Fecha_Registro, String NomCliente, String dniauxiliar, String DIRECCION, String IdTipoPrecio, String IdMedico, String IdProforma, String idpersonal, String ruc, int idempresa, String telefono,
            double Opgravada, double OpInafecta, double OpGratuita, double Opexonerada,double OpISC,String cupon,
            int cantbolsas) {
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Total = Total;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.DNI = DNI;
        this.DescripcionTipoPago = DescripcionTipoPago;
        this.Descripciontipoventa = Descripciontipoventa;
        this.NombrePersonal = NombrePersonal;
        this.ApellidoPersonal = ApellidoPersonal;
        this.NomCliente = NomCliente;
        this.dniaux = dniauxiliar;
        this.Id_Tipo_Precio = IdTipoPrecio;
        this.Id_Medico = IdMedico;
        this.Id_Proforma = IdProforma;
        this.idperosnalmodficada = idpersonal;
        this.DireccionProforma = DIRECCION;
        this.RUC = ruc;
        this.IdEmpresa = idempresa;
        this.telefono = telefono;
        //this.idOperadorRec = idOperadorRec;
        this.OpGravada=Opgravada;
        this.OpExonerada =Opexonerada;
        this.OpInafecta = OpInafecta;
        this.OpGratuita=OpGratuita;
        this.OpISC = OpISC;
        this.cupon=cupon;
        this.cantbolsas = cantbolsas;

    }

    public Proforma(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String DNI, int Id_TipoPago, String DescripcionTipoPago, int Id_Tipo_Venta, String Descripciontipoventa, Date Fecha_Venta, int Id_Personal_Botica_Venta, String NombrePersonal, String ApellidoPersonal, double SubTotal, double IGV, double Total, Date Fecha_Registro, String NomCliente, String dniauxiliar, String DIRECCION, String IdTipoPrecio, String IdMedico, String IdProforma, String idpersonal, String ruc) {
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Total = Total;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.DNI = DNI;
        this.DescripcionTipoPago = DescripcionTipoPago;
        this.Descripciontipoventa = Descripciontipoventa;
        this.NombrePersonal = NombrePersonal;
        this.ApellidoPersonal = ApellidoPersonal;
        this.NomCliente = NomCliente;
        this.dniaux = dniauxiliar;
        this.Id_Tipo_Precio = IdTipoPrecio;
        this.Id_Medico = IdMedico;
        this.Id_Proforma = IdProforma;
        this.idperosnalmodficada = idpersonal;
        this.DireccionProforma = DIRECCION;
        this.RUC = ruc;

    }

    public Proforma(String idprofor, int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String DNI, int Id_TipoPago, String DescripcionTipoPago, int Id_Tipo_Venta, String Descripciontipoventa, Date Fecha_Venta, int Id_Personal_Botica_Venta, String NombrePersonal, String ApellidoPersonal, double SubTotal, double IGV, double Total, Date Fecha_Registro, String NomCliente, String dniauxiliar, String DIRECCION, String IdTipoPrecio, String IdMedico, String ruc) {
        this.id_proforma = idprofor;
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Total = Total;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.DNI = DNI;
        this.DescripcionTipoPago = DescripcionTipoPago;
        this.Descripciontipoventa = Descripciontipoventa;
        this.NombrePersonal = NombrePersonal;
        this.ApellidoPersonal = ApellidoPersonal;
        this.NomCliente = NomCliente;
        this.dniaux = dniauxiliar;
        this.Id_Tipo_Precio = IdTipoPrecio;
        this.Id_Medico = IdMedico;
        this.DireccionProforma = DIRECCION;
        this.RUC = ruc;

    }

    //DETALLE DE LA PROFORMA
    public Proforma(String idproducto, String descipcionproducto, int UNIDAD, int FRACCION, double Precio_Venta, double Descuento, double pvx, double Total, Date Fecha_Vencimiento, double IGV_Exonerado, int Empaque, int stockempaque, int stockfraccion, String laboratorio) {
        this.Total = Total;
        this.idproducto = idproducto;
        this.descipcionproducto = descipcionproducto;
        this.UNIDAD = UNIDAD;
        this.FRACCION = FRACCION;
        this.Precio_Venta = Precio_Venta;
        this.Descuento = Descuento;
        this.pvx = pvx;
        this.Fecha_Vencimiento = Fecha_Vencimiento;
        this.IGV_Exonerado = IGV_Exonerado;
        this.Empaque = Empaque;
        this.Stock_Empaque = stockempaque;
        this.Stock_Fraccion = stockfraccion;
        this.id_laboratorio = laboratorio;

    }

    public Proforma(double subtotal, String Id_Proforma, String Id_Boticas, int UNIDAD, int FRACCION, double Precio_Venta, double Descuento, double pvx, double Total, String idproducto, String dni) {
        /*Miguel Gomez S.*/
        this.Id_Proforma = Id_Proforma;
        this.Id_Boticas = Id_Boticas;
        this.Total = Total;
        this.UNIDAD = UNIDAD;
        this.FRACCION = FRACCION;
        this.Precio_Venta = Precio_Venta;
        this.Descuento = Descuento;
        this.pvx = pvx;
        this.idproducto = idproducto;
        this.DNI = dni;
        this.SubTotal = subtotal;
    }

    public Proforma(double subtotal, String Id_Proforma, String Id_Boticas, int UNIDAD, int FRACCION, double Precio_Venta, double Descuento, double pvx, double Total, String idproducto, String dni, Double DsctoAdicional) {
        this.Id_Proforma = Id_Proforma;
        this.Id_Boticas = Id_Boticas;
        this.Total = Total;
        this.UNIDAD = UNIDAD;
        this.FRACCION = FRACCION;
        this.Precio_Venta = Precio_Venta;
        this.Descuento = Descuento;
        this.pvx = pvx;
        this.idproducto = idproducto;
        this.DNI = dni;
        this.SubTotal = subtotal;
        this.DsctoAdicional = DsctoAdicional;
    }

    public Proforma(String Id_Boticas, int Id_Cliente, int Id_TipoPago, String Id_Tipo_Precio, int Id_Tipo_Venta,
            Date Fecha_Venta, Date Fecha_Registro, int Id_Personal_Botica_Venta, String nomcliente, String dni,
            String direccion, String ruc, String telefono, int clase,int tipo,int marca,int modelo,
            String asientos,int zona,String uso,Double precio,String anio,String serie,String placa,String poliza,
            String certificado,String dep,String prov,String dist,String FechaInicio, String FechaFin, String comision){

        this.Id_Boticas = Id_Boticas;
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Precio = Id_Tipo_Precio;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Fecha_Venta = Fecha_Venta;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Nombre_RazonSocial = nomcliente;
        this.DNI = dni;
        this.Direccion = direccion;
        this.RUC = ruc;
        this.telefono = telefono;
        this.clase = clase;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.asientos = asientos;
        this.zona = zona;
        this.uso = uso;
        this.precio = precio;
        this.anio = anio;
        this.serie = serie;
        this.placa = placa;
        this.poliza = poliza;
        this.certificado = certificado;
        this.dep = dep;
        this.prov = prov;
        this.dist = dist;
        this.fecha1 = FechaInicio;
        this.fecha2 = FechaFin;
        this.comision = comision;
        this.ordenproducto = "0";

    }
    
    public String getId_proforma() {
        return id_proforma;
    }

    public void setId_proforma(String id_proforma) {
        this.id_proforma = id_proforma;
    }

    public String getDireccionProforma() {
        return DireccionProforma;
    }

    public void setDireccionProforma(String DireccionProforma) {
        this.DireccionProforma = DireccionProforma;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getIdperosnalmodficada() {
        return idperosnalmodficada;
    }

    public void setIdperosnalmodficada(String idperosnalmodficada) {
        this.idperosnalmodficada = idperosnalmodficada;
    }
//pPARA MODIFICAR LA PROFORMA

    public String getDniaux() {
        return dniaux;
    }

    public void setDniaux(String dniaux) {
        this.dniaux = dniaux;
    }

    public int getStock_Empaque() {
        return Stock_Empaque;
    }

    public void setStock_Empaque(int Stock_Empaque) {
        this.Stock_Empaque = Stock_Empaque;
    }

    public int getStock_Fraccion() {
        return Stock_Fraccion;
    }

    public void setStock_Fraccion(int Stock_Fraccion) {
        this.Stock_Fraccion = Stock_Fraccion;
    }

    public String getNomCliente() {
        return NomCliente;
    }

    public void setNomCliente(String NomCliente) {
        this.NomCliente = NomCliente;
    }

    public int getEmpaque() {
        return Empaque;
    }

    public Date getFecha_Vencimiento() {
        return Fecha_Vencimiento;
    }

    public double getIGV_Exonerado() {
        return IGV_Exonerado;
    }

    public String getesgratuitaPromo() {
        return esgratuitaPromo;
    }
    public void setesgratuitaPromo(String esgratuitaPromo) {
        this.esgratuitaPromo = esgratuitaPromo;
    }

    public String getesgratuita() {
        return esgratuita;
    }
    public void setesgratuita(String esgratuita) {
        this.esgratuita = esgratuita;
    }
    public double getTotEsGratuita() {
        return TotEsGratuita;
    }
    public void setTotEsGratuita(double TotEsGratuita) {
        this.TotEsGratuita = TotEsGratuita;
    }
    
    public Proforma(String idproducto, int UNIDAD, int FRACCION, double Precio_Venta, double Descuento, double pv, double Total, String orden, int idOperadorRec,
            String esgratuita,double TotEsGratuita, String esgratuitaPromo,double Opgravada, double Opexonerada, double OpInafecta, double OpGratuita, double OpISC) {
        this.Total = Total;
        this.idproducto = idproducto;
        this.UNIDAD = UNIDAD;
        this.FRACCION = FRACCION;
        this.Precio_Venta = Precio_Venta;       
        this.pvx = pv;
        this.Descuento = Descuento;
        this.ordenproducto = orden;
        this.idOperadorRec = idOperadorRec;

        this.OpGravada = Opgravada;
        this.OpExonerada = Opexonerada;
        this.OpInafecta = OpInafecta;
        this.OpGratuita = OpGratuita;
        this.OpISC = OpISC;
        this.esgratuitaPromo= esgratuitaPromo;
        this.esgratuita = esgratuita;
    }
    public Proforma(String idproducto, int UNIDAD, int FRACCION, double Precio_Venta, double Descuento, double pv, double Total, double subtotal,String orden, int idOperadorRec,
            String esgratuita,double TotEsGratuita, String esgratuitaPromo,double Opgravada, double Opexonerada, double OpInafecta, double OpGratuita, double OpISC,
            String CertMed) {
        this.Total = Total;
        this.SubTotal = subtotal;
        this.idproducto = idproducto;
        this.UNIDAD = UNIDAD;
        this.FRACCION = FRACCION;
        this.Precio_Venta = Precio_Venta;
        this.TotEsGratuita = TotEsGratuita;
        this.esgratuitaPromo= esgratuitaPromo;
        this.esgratuita = esgratuita;
        this.pvx = pv;
        this.Descuento = Descuento;
        this.ordenproducto = orden;
        this.idOperadorRec = idOperadorRec;

         this.OpGravada = Opgravada;
        this.OpExonerada = Opexonerada;
        this.OpInafecta = OpInafecta;
        this.OpGratuita = OpGratuita;
        this.OpISC = OpISC;
        this.CertMed = CertMed;
    }

    public String getDescipcionproducto() {
        return descipcionproducto;
    }

    public void setDescipcionproducto(String descipcionproducto) {
        this.descipcionproducto = descipcionproducto;
    }

    public String getApellidoPersonal() {
        return ApellidoPersonal;
    }

    public void setApellidoPersonal(String ApellidoPersonal) {
        this.ApellidoPersonal = ApellidoPersonal;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDescripcionTipoPago() {
        return DescripcionTipoPago;
    }

    public void setDescripcionTipoPago(String DescripcionTipoPago) {
        this.DescripcionTipoPago = DescripcionTipoPago;
    }

    public String getDescripciontipoventa() {
        return Descripciontipoventa;
    }

    public void setDescripciontipoventa(String Descripciontipoventa) {
        this.Descripciontipoventa = Descripciontipoventa;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getNombrePersonal() {
        return NombrePersonal;
    }

    public void setNombrePersonal(String NombrePersonal) {
        this.NombrePersonal = NombrePersonal;
    }

    public String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public void setNombre_RazonSocial(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setRUC_DNI(String RUC_DNI) {
        this.RUC_DNI = RUC_DNI;
    }

    public double getPvx() {
        return pvx;
    }

    public void setPvx(double pvx) {
        this.pvx = pvx;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public int getFRACCION() {
        return FRACCION;
    }

    public void setFRACCION(int FRACCION) {
        this.FRACCION = FRACCION;
    }

    public int getidOperadorRec() {
        return idOperadorRec;
    }

    public void setidOperadorRec(int idOperadorRec) {
        this.idOperadorRec = idOperadorRec;
    }



    public double getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(double Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public int getUNIDAD() {
        return UNIDAD;
    }

    public void setUNIDAD(int UNIDAD) {
        this.UNIDAD = UNIDAD;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public Date getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(Date Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public Date getFecha_Venta() {
        return Fecha_Venta;
    }

    public void setFecha_Venta(Date Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }

    public String getId_Boticas() {
        return Id_Boticas;
    }

    public void setId_Boticas(String Id_Boticas) {
        this.Id_Boticas = Id_Boticas;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getId_Medico() {
        return Id_Medico;
    }

    public void setId_Medico(String Id_Medico) {
        this.Id_Medico = Id_Medico;
    }

    public int getId_Personal_Botica_Venta() {
        return Id_Personal_Botica_Venta;
    }

    public void setId_Personal_Botica_Venta(int Id_Personal_Botica_Venta) {
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
    }

    public String getId_Proforma() {
        return Id_Proforma;
    }

    public void setId_Proforma(String Id_Proforma) {
        this.Id_Proforma = Id_Proforma;
    }

    public int getId_TipoPago() {
        return Id_TipoPago;
    }

    public void setId_TipoPago(int Id_TipoPago) {
        this.Id_TipoPago = Id_TipoPago;
    }

    public String getId_Tipo_Precio() {
        return Id_Tipo_Precio;
    }

    public void setId_Tipo_Precio(String Id_Tipo_Precio) {
        this.Id_Tipo_Precio = Id_Tipo_Precio;
    }

    public int getId_Tipo_Venta() {
        return Id_Tipo_Venta;
    }

    public void setId_Tipo_Venta(int Id_Tipo_Venta) {
        this.Id_Tipo_Venta = Id_Tipo_Venta;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    public double getOpExonerada() {
        return OpExonerada;
    }
    public void setOpExonerada(double OpExonerada) {
        this.OpExonerada = OpExonerada;
    }
    
    public double getOpGravada() {
        return OpGravada;
    }
    public void setOpGravada(double OpGravada) {
        this.OpGravada = OpGravada;
    }


    public double getOpInafecta() {
        return OpInafecta;
    }
    public void setOpInafecta(double OpInafecta) {
        this.OpInafecta = OpInafecta;
    }

    public double getOpGratuita() {
        return OpGratuita;
    }
    public void setOpGratuita(double OpGratuita) {
        this.OpGratuita = OpGratuita;
    }

    public double getOpISC() {
        return OpISC;
    }
    public void setOpISC(double OpISC) {
        this.OpISC = OpISC;
    }

    
    public int getclase() {
        return clase;
    }
    public void setclase(int clase) {
        this.clase = clase;
    }
    public int gettipo() {
        return tipo;
    }
    public void settipo(int tipo) {
        this.tipo = tipo;
    }
    public int getmarca() {
        return marca;
    }
    public void setmarca(int marca) {
        this.marca = marca;
    }
    public int getmodelo() {
        return modelo;
    }
    public void setmodelo(int modelo) {
        this.modelo = modelo;
    }
    public String getasientos() {
        return asientos;
    }
    public void setasientos(String asientos) {
        this.asientos = asientos;
    }
    public int getzona() {
        return zona;
    }
    public void setzona(int zona) {
        this.zona = zona;
    }
    public String getuso() {
        return uso;
    }
    public void setuso(String uso) {
        this.uso = uso;
    }
    public double getprecio() {
        return precio;
    }
    public void setprecio(double precio) {
        this.precio = precio;
    }
    public String getanio() {
        return anio;
    }
    public void setanio(String anio) {
        this.anio = anio;
    }
    public String getserie() {
        return serie;
    }
    public void setserie(String serie) {
        this.serie = serie;
    }
    public String getplaca() {
        return placa;
    }
    public void setplaca(String placa) {
        this.placa = placa;
    }
     public String getpoliza() {
        return poliza;
    }
    public void setpoliza(String poliza) {
        this.poliza = poliza;
    }
     public String getcertificado() {
        return certificado;
    }
    public void setcertificado(String certificado) {
        this.certificado = certificado;
    }
    public String getdep() {
        return dep;
    }
    public void setdep(String dep) {
        this.dep = dep;
    }
     public String getprov() {
        return prov;
    }
    public void setprov(String prov) {
        this.prov = prov;
    }
     public String getdist() {
        return dist;
    }
    public void setdist(String dist) {
        this.dist = dist;
    }
    public String getfecha1() {
        return fecha1;
    }
    public void setfecha1(String fecha1) {
        this.fecha1 = fecha1;
    }
    public String getfecha2() {
        return fecha2;
    }

    public void setfecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getcomision() {
        return comision;
    }

    public void setcomision(String comision) {
        this.comision = comision;
    }

    @Override
    public int compare(Proforma o1, Proforma o2) {
        return Integer.parseInt(o1.ordenproducto) - Integer.parseInt(o2.ordenproducto);
    }

    @Override
    public int compareTo(Proforma o) {
        return (this.ordenproducto).compareTo(o.ordenproducto);
    }
}