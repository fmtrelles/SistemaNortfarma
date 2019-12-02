package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Clientes {

    public int Id_Cliente;
    public String RUC_DNI;
    public String Nombre_RazonSocial;
    public String Direccion; 
    private String telefono;
    private String placa;
    private String DNI;
    private String Empresa;
    private String Botica;
    private String correo;
    public String CodADm;
    public String CodAsociado;
    public Double monto;
    public String vencimiento;
    double saldo;
    public int idempresa;
    public int tippago;
    public int tipvta;
    public int id_mov;
    public double consumo;
    public Date fecha;
    public int pocen_descuento;
    public String porcen_descuento;
    public int id_descuento;
    public String Id_Botica;
    private String idproductodesc;
    private String id_Laboratorio;
    private double precio_venta;
    private double descuento_venta;
    private double descuento_adicional;
    
    public String RUC;
    public String nomtransportista;   
    private String marca;  
  
    private String codmedico;
    private String nommedico;
    private String cole;
    private String colemedico;
    
    public int Id_Transportista;        
    private String NPlaca;        
    private String Licencia;
    private String Constancia;

    public String getColemedico() {
        return colemedico;
    }

    public void setColemedico(String colemedico) {
        this.colemedico = colemedico;
    }
  
    
    public String getCodmedico() {
        return codmedico;
    }

    public String getNommedico() {
        return nommedico;
    }

    public String getCole() {
        return cole;
    }

    public void setCodmedico(String codmedico) {
        this.codmedico = codmedico;
    }

    public void setNommedico(String nommedico) {
        this.nommedico = nommedico;
    }

    public void setCole(String cole) {
        this.cole = cole;
    }

        
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    
    public String getRUC() {
        return RUC;
    }

    public String getNomtransportista() {
        return nomtransportista;
    }

    public String getMarca() {
        return marca;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public void setNomtransportista(String nomtransportista) {
        this.nomtransportista = nomtransportista;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    
    
    
    
    public Clientes() {
    }

    public Clientes(String codmedico, String nommedico, String colemedico,String cole) {
        this.codmedico = codmedico;
        this.nommedico = nommedico;
        this.colemedico=colemedico;
        this.cole = cole;        
    }
    
    public Clientes(int idempresa, int IdCliente, String ruc, String nombre, String direccion, String dni, double consumo, Date FECHA, double saldo) {
        this.idempresa = idempresa;
        this.Id_Cliente = IdCliente;
        this.Nombre_RazonSocial = nombre;
        this.Direccion = direccion;
        this.DNI = dni;
        this.consumo = consumo;
        this.fecha = FECHA;
        this.saldo = saldo;
        this.RUC_DNI = ruc;
    }

    public Clientes(String Nombre_RazonSocial, String RUC_DNI, String DNI, double saldo) {
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.DNI = DNI;
        this.saldo = saldo;
    }

    public Clientes(int numero, String direccion) {
        this.id_mov = numero;
        if (direccion == null) {
            direccion = "";
        }
        this.Direccion = direccion;
    }

    public Clientes(int Id_Cliente, String Nombre_RazonSocial, String empresa, String ruc, String telefono, String dni, String idbotica) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = ruc;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Empresa = empresa;
        this.telefono = telefono;
        this.DNI = dni;
        this.Id_Botica = idbotica;
    }                 
    public Clientes(int Id_Transp, String Nombre_RazonSocial, String ruc, String licencia,String constancia, String marca,String placa,String idbotica,int opc) {
        this.Id_Transportista = Id_Transp;
        this.RUC_DNI = ruc;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Licencia = licencia;
        this.Constancia = constancia;
        this.marca = marca;
        this.placa = placa;
        this.Id_Botica = idbotica;
    }

    public Clientes(int Id_Cliente, String Nombre_RazonSocial, String empresa, String ruc, String direccion, String telefono, String dni, String CodADm, String CodAsociado, double saldo) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = ruc;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Empresa = empresa;
        this.telefono = telefono;
        this.Direccion = direccion;
        this.CodADm = CodADm;
        this.CodAsociado = CodAsociado;
        this.DNI = dni;
        this.saldo = saldo;
    }

    public Clientes(String Nombre_RazonSocial, String direccion, String telefono, String dni, String rucdni) {
        this.RUC_DNI = rucdni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.telefono = telefono;
        this.Direccion = direccion;
        this.DNI = dni;
    }

    public Clientes(String idbotica, int idcliente, int numero, String direccion, String empresa) {
        this.Id_Botica = idbotica;
        this.Id_Cliente = idcliente;
        this.id_mov = numero;
        this.Direccion = direccion;
        this.Empresa = empresa;
    }

    public Clientes(String descrip_pocen, int porcen, int iddescuento, String producto, String idlab, double pv, double dscventa, double dscadicional) {
        this.pocen_descuento = porcen;
        this.porcen_descuento = descrip_pocen;
        this.id_descuento = iddescuento;
        this.idproductodesc = producto;
        this.id_Laboratorio = idlab;
        this.precio_venta = pv;
        this.descuento_venta = dscventa;
        this.descuento_adicional = dscadicional;
    }

    public int getId_Transportista() {
        return Id_Transportista;
    }

    public String getNPlaca() {
        return NPlaca;
    }

    public String getLicencia() {
        return Licencia;
    }

    public String getConstancia() {
        return Constancia;
    }

    public void setId_Transportista(int Id_Transportista) {
        this.Id_Transportista = Id_Transportista;
    }

    public void setLicencia(String Licencia) {
        this.Licencia = Licencia;
    }

    public void setConstancia(String Constancia) {
        this.Constancia = Constancia;
    }

    public void setNPlaca(String NPlaca) {
        this.NPlaca = NPlaca;
    }

    
    
    
    
    
    
    public double getDescuento_adicional() {
        return descuento_adicional;
    }

    public void setDescuento_adicional(double descuento_adicional) {
        this.descuento_adicional = descuento_adicional;
    }

    public double getDescuento_venta() {
        return descuento_venta;
    }

    public void setDescuento_venta(double descuento_venta) {
        this.descuento_venta = descuento_venta;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getId_Laboratorio() {
        return id_Laboratorio;
    }

    public void setId_Laboratorio(String id_Laboratorio) {
        this.id_Laboratorio = id_Laboratorio;
    }

    public String getIdproductodesc() {
        return idproductodesc;
    }

    public void setIdproductodesc(String idproductodesc) {
        this.idproductodesc = idproductodesc;
    }

    public int getPocen_descuento() {
        return pocen_descuento;
    }

    public void setPocen_descuento(int pocen_descuento) {
        this.pocen_descuento = pocen_descuento;
    }

    public String getId_Botica() {
        return Id_Botica;
    }

    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public int getId_descuento() {
        return id_descuento;
    }

    public void setId_descuento(int id_descuento) {
        this.id_descuento = id_descuento;
    }

    public int get_pocen_descuento() {
        return pocen_descuento;
    }

    public void setId_pocen_descuento(int id_pocen_descuento) {
        this.pocen_descuento = id_pocen_descuento;
    }

    public String getPorcen_descuento() {
        return porcen_descuento;
    }

    public void setPorcen_descuento(String porcen_descuento) {
        this.porcen_descuento = porcen_descuento;
    }

    public Clientes(int idcliente) {
        this.Id_Cliente = idcliente;

    }

    public String getCodADm() {
        return CodADm;
    }

    public void setCodADm(String CodADm) {
        this.CodADm = CodADm;
    }

    public String getCodAsociado() {
        return CodAsociado;
    }

    public void setCodAsociado(String CodAsociado) {
        this.CodAsociado = CodAsociado;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_mov() {
        return id_mov;
    }

    public void setId_mov(int id_mov) {
        this.id_mov = id_mov;
    }

    public String getBotica() {
        return Botica;
    }

    public void setBotica(String Botica) {
        this.Botica = Botica;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public Clientes(int idcliente, String Empresa, String Botica, Double monto, String vencimiento) {
        this.Id_Cliente = idcliente;
        this.Empresa = Empresa;
        this.Botica = Botica;
        this.monto = monto;
        this.vencimiento = vencimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Clientes(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, double saldo, String dni, String empresa, String telefono) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.saldo = saldo;
        this.DNI = dni;
        this.Empresa = empresa;
        this.telefono = telefono;        
    }

    public Clientes(int Id_Transportista, String RUC, String Nombre, String NPlaca, String Licencia, String Constancia, String marca, String placa) {
        this.Id_Transportista = Id_Transportista;
        this.RUC_DNI = RUC;
        this.Nombre_RazonSocial = Nombre;
        this.NPlaca = NPlaca;        
        this.Licencia = Licencia;
        this.Constancia = Constancia;
        this.marca = marca;
        this.placa=placa;
    }    

    public Clientes(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String dni) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.DNI = dni;
    }

    public Clientes(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String dni, String telefono) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = RUC_DNI;
        this.DNI = dni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
    }

    public Clientes(String RUC_DNI, String dni, String Nombre_RazonSocial, String Direccion, String telefono, int idempresa, String codnortfarm) {
        this.RUC_DNI = RUC_DNI;
        this.DNI = dni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.idempresa = idempresa;
        this.CodAsociado = codnortfarm;
    }
    
    public Clientes(String Nombre_RazonSocial,String RUC_DNI, String dni, int tipopago, int tipodoc, int opc,int opc1) {
        this.RUC_DNI = RUC_DNI;
        this.DNI = dni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.tippago = tipopago;
        this.tipvta = tipodoc;
    }

    public Clientes(String RUC_DNI, String dni, String Nombre_RazonSocial, String Direccion, String telefono, int idempresa) {
        this.RUC_DNI = RUC_DNI;
        this.DNI = dni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.idempresa = idempresa;        
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String midireccion) {
        if (midireccion.compareTo("null") == 0 || midireccion == null) {
            setDireccion("");
        } else {
            this.Direccion = midireccion;
        }
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public void setNombre_RazonSocial(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public int gettippago(){
        return tippago;
    }

    public void settipvta(int tipvta){
        this.tipvta = tipvta;
    }

    public int gettipvta(){
        return tipvta;
    }

    public void settippago(int tippago){
        this.tippago = tippago;
    }

    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setRUC_DNI(String RUC_DNI) {
        this.RUC_DNI = RUC_DNI;
    }

    public Clientes(String RUC_DNI, String Nombre_RazonSocial, String Direccion, String telefono, String Empresa, String Botica) {
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.Empresa = Empresa;
        this.Botica = Botica;
    }
    
    public Clientes(String RUC, String constancia, String nomtransportista, String marca, String placa, String licencia,int opc) {
        this.RUC = RUC;
        this.nomtransportista = nomtransportista;
        this.Constancia = constancia;
        this.marca = marca;
        this.NPlaca = placa;
        this.Licencia = licencia;
    }   
    

    public Clientes(String RUC_DNI, String dni, String Nombre_RazonSocial, String Direccion, String telefono, String Empresa, String Botica, String CodADm, String CodAsociado, String botica) {
        this.RUC_DNI = RUC_DNI;
        this.DNI = dni;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.Empresa = Empresa;
        this.Botica = Botica;
        this.CodADm = CodADm;
        this.CodAsociado = CodAsociado;
        this.Id_Botica = botica;
    }

    public Clientes(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public Clientes(String Nombre_RazonSocial, String Empresa, Double monto) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Empresa = Empresa;
        this.monto = monto;
    }

    public Clientes(int idcliente, String Empresa, Double monto) {
        this.Id_Cliente = idcliente;
        this.Empresa = Empresa;
        this.monto = monto;
    }

    public Clientes(String idbotica, int idcliente) {
        this.Id_Cliente = idcliente;
        this.Id_Botica = idbotica;
    }

    public Clientes(String RUC_DNI, String Nombre_RazonSocial) {
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public Clientes(String RUC_DNI, String Nombre_RazonSocial, double saldo) {
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.saldo = saldo;
    }

    public Clientes(int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, double saldo) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.saldo = saldo;
    }

    public Clientes(int idempresa, int Id_Cliente, String RUC_DNI, String Nombre_RazonSocial, String Direccion, String telefono, String DNI, String CodAsociado, String CodADm, double saldo, Date fecha, String Id_Botica) {
        this.Id_Cliente = Id_Cliente;
        this.RUC_DNI = RUC_DNI;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.DNI = DNI;
        this.CodADm = CodADm;
        this.CodAsociado = CodAsociado;
        this.saldo = saldo;
        this.idempresa = idempresa;
        this.fecha = fecha;
        this.Id_Botica = Id_Botica;
    }
}
