package entidad;

import java.sql.Date;

public class Cajas {

    private int idcaja;
    private String cajas;
    String Caja, TipoVenta, Serie, Numeracion;
    String Fecha, Hora;
    int turno;
    private int idpersonal;
    private int miturno;
    private String micaja;
    private double saldo;
    private int idgasto;
    private String DescripGasto;
    private String clientegasto;
    private String caracter;
    private String docugasto;
    private double MontoGasto;
    private int idcajero;
    private Double depositado, depositado_dolares;
    private Date MiFecha;
    private String destino;
    private String origen;
    private String idpersonalMov;
    private int retencion;
    private String submotivo;
    private String descr_Retencion;
    private double Monto_Retencion;
    private double Porcen_Retencion;
    private double dolares;
    private String DescripRetencion;
    private String idbotica;
    private String responsable;
    private int apertura;
    private int idturno;
    private int idsuper;
    private String Id_VentaNC;
    private int esDelivery;
    private int MarcadoDelivery;
    private int idmotivo;
    private String DescripMotivo;
    
    private String idSubmotivo;
    private String DescripSubMotivo;
    private String Nombre_RazonSocial;
    private String desgasto;

    private String idObs;
    private String DescripcionObs;
    private String opc;

    public Cajas() {
    }

    public Cajas(String idbotica, int idcaja, int turno1, int idcajero) {
        this.idbotica = idbotica;
        this.idcaja = idcaja;
        this.miturno = turno1;
        this.idcajero = idcajero;
    }

    public Cajas(String idbotica, int idcaja, int turno1, int idcajero, int supervisor) {
        this.idbotica = idbotica;
        this.idcaja = idcaja;
        this.miturno = turno1;
        this.idcajero = idcajero;
    }

    public Cajas(int Caja1, String TipoVenta1, String Serie1, String Numeracion1, String Fecha1, String Hora1, String Botica1, int turno1, double dolares) {
        this.idcaja = Caja1;
        this.TipoVenta = TipoVenta1;
        this.Serie = Serie1;
        this.Numeracion = Numeracion1;
        this.Fecha = Fecha1;
        this.Hora = Hora1;
        this.idbotica = Botica1;
        this.turno = turno1;
        this.dolares = dolares;
    }

    public Cajas(int idcaja, String idbotica, int idpersonal, String fecha, double deposito, double deposito_dolares, int turno) {
        this.idcaja = idcaja;
        this.idbotica = idbotica;
        this.idpersonal = idpersonal;
        this.Fecha = fecha;
        this.depositado = deposito;
        this.depositado_dolares = deposito_dolares;
        this.turno = turno;
    }
    public Cajas(String IDBOTICA, int IDCAJA, int IDPERSONAL, int TIPOGASTO, String DOC, double MIMONTO, String CONCEP, int idcajero, String Retencion, int idturno, String serie, String numer, String interno) {
        this.idbotica = IDBOTICA;
        this.idcaja = IDCAJA;
        this.idgasto = TIPOGASTO;
        this.DescripGasto = CONCEP;
        this.docugasto = DOC;
        this.MontoGasto = MIMONTO;
        this.idpersonal = IDPERSONAL;
        this.idcajero = idcajero;
        this.DescripRetencion = Retencion;
        this.idturno = idturno;
        this.Serie = serie;
        this.Numeracion = numer;
        this.Id_VentaNC = interno;

    }


    public Cajas(String IDBOTICA, int IDCAJA, int IDPERSONAL, int TIPOGASTO, String DOC, double MIMONTO, String CONCEP, int idcajero, String Retencion, int idturno, String serie, String numer, String interno, String clientegasto, int idmotivo, String origen,String destino, String idpersonalMov, String SubMotivo) {
        this.idbotica = IDBOTICA;
        this.idcaja = IDCAJA;
        this.idgasto = TIPOGASTO;
        this.DescripGasto = CONCEP;
        this.docugasto = DOC;
        this.MontoGasto = MIMONTO;
        this.idpersonal = IDPERSONAL;
        this.idcajero = idcajero;
        this.DescripRetencion = Retencion;
        this.idturno = idturno;
        this.Serie = serie;
        this.Numeracion = numer;
        this.Id_VentaNC = interno;
        this.clientegasto = clientegasto;
        this.idmotivo = idmotivo;
        this.origen = origen;
        this.destino = destino;
        this.idpersonalMov = idpersonalMov;
        this.submotivo = SubMotivo;
    }

    public String getIdSubmotivo() {
        return idSubmotivo;
    }

    public String getDescripSubMotivo() {
        return DescripSubMotivo;
    }

    public void setIdSubmotivo(String idSubmotivo) {
        this.idSubmotivo = idSubmotivo;
    }

    public void setDescripSubMotivo(String DescripSubMotivo) {
        this.DescripSubMotivo = DescripSubMotivo;
    }

    public String getSubmotivo() {
        return submotivo;
    }

    public void setSubmotivo(String submotivo) {
        this.submotivo = submotivo;
    }

    
    
    
    
    public int getMarcadoDelivery() {
        return MarcadoDelivery;
    }

    public void setMarcadoDelivery(int MarcadoDelivery) {
        this.MarcadoDelivery = MarcadoDelivery;
    }

    public int getEsDelivery() {
        return esDelivery;
    }
    public void setEsDelivery(int esDelivery) {
        this.esDelivery = esDelivery;
    }

    public String getidObs() {
        return idObs;
    }
    public void setidObs(String idObs) {
        this.idObs = idObs;
    }
    
    public String getDescripcionObs() {
        return DescripcionObs;
    }
    public void setDescripcionObs(String DescripcionObs) {
        this.DescripcionObs = DescripcionObs;
    }
    
    public String getopc() {
        return opc;
    }
    public void setopc(String opc) {
        this.opc = opc;
    }
    
    public String getId_VentaNC() {
        return Id_VentaNC;
    }

    public void setId_VentaNC(String Id_VentaNC) {
        this.Id_VentaNC = Id_VentaNC;
    }

    public int getMiturno() {
        return miturno;
    }

    public void setMiturno(int miturno) {
        this.miturno = miturno;
    }

    public int getIdSuper() {
        return idsuper;
    }

    public void setIdSuper(int idsuper) {
        this.idsuper = idsuper;
    }

    public int getIdturno() {
        return idturno;
    }

    public void setIdturno(int idturno) {
        this.idturno = idturno;
    }

    public int getApertura() {
        return apertura;
    }

    public void setApertura(int apertura) {
        this.apertura = apertura;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public void setNombre_RazonSocial(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public String getdesgasto() {
        return desgasto;
    }

    public void setdesgasto(String desgasto) {
        this.desgasto = desgasto;
    }

    public String getIdbotica() {
        return idbotica;
    }

    public void setIdbotica(String idbotica) {
        this.idbotica = idbotica;
    }

    public String getDescripRetencion() {
        return DescripRetencion;
    }

    public void setDescripRetencion(String DescripRetencion) {
        this.DescripRetencion = DescripRetencion;
    }

    public double getMonto_Retencion() {
        return Monto_Retencion;
    }

    public void setMonto_Retencion(double Monto_Retencion) {
        this.Monto_Retencion = Monto_Retencion;
    }

    public double getPorcen_Retencion() {
        return Porcen_Retencion;
    }

    public void setPorcen_Retencion(double Porcen_Retencion) {
        this.Porcen_Retencion = Porcen_Retencion;
    }

    public double getdolares() {
        return dolares;
    }

    public void setdolares(double dolares) {
        this.dolares = dolares;
    }

    public String getDescr_Retencion() {
        return descr_Retencion;
    }

    public void setDescr_Retencion(String descr_Retencion) {
        this.descr_Retencion = descr_Retencion;
    }

    public String getDescripMotivo() {
        return DescripMotivo;
    }

    public void setDescripMotivo(String DescripMotivo) {
        this.DescripMotivo = DescripMotivo;
    }
    public int getRetencion() {
        return retencion;
    }

    public void setRetencion(int retencion) {
        this.retencion = retencion;
    }

    public Double getDepositado_dolares() {
        return depositado_dolares;
    }

    public void setDepositado_dolares(Double depositado_dolares) {
        this.depositado_dolares = depositado_dolares;
    }

    public Double getDepositado() {
        return depositado;
    }

    public void setDepositado(Double depositado) {
        this.depositado = depositado;
    }

    public Date getMiFecha() {
        return MiFecha;
    }

    public void setMiFecha(Date MiFecha) {
        this.MiFecha = MiFecha;
    }

    public int getIdcajero() {
        return idcajero;
    }

    public void setIdcajero(int idcajero) {
        this.idcajero = idcajero;
    }

    public double getMontoGasto() {
        return MontoGasto;
    }

    public void setMontoGasto(double MontoGasto) {
        this.MontoGasto = MontoGasto;
    }

    public String getDocugasto() {
        return docugasto;
    }

    public void setDocugasto(String docugasto) {
        this.docugasto = docugasto;
    }

    public Cajas(String micaja, double saldo) {
        this.micaja = micaja;
        this.saldo = saldo;
    }

    public String getDescripGasto() {
        return DescripGasto;
    }

    public void setDescripGasto(String DescripGasto) {
        this.DescripGasto = DescripGasto;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int getIdgasto() {
        return idgasto;
    }
    public void setIdgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public int getIdmotivo() {
        return idmotivo;
    }
    public void setIdmotivo(int idmotivo) {
        this.idmotivo = idmotivo;
    }

    public String getMicaja() {
        return micaja;
    }

    public void setMicaja(String micaja) {
        this.micaja = micaja;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }   

    public void setBotica(String Botica) {
        this.idbotica = Botica;
    }

    public String getCaja() {
        return Caja;
    }

    public void setCaja(String Caja) {
        this.Caja = Caja;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getNumeracion() {
        return Numeracion;
    }

    public void setNumeracion(String Numeracion) {
        this.Numeracion = Numeracion;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String TipoVenta) {
        this.TipoVenta = TipoVenta;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Cajas(int idcaja, String cajas) {
        this.idcaja = idcaja;
        this.cajas = cajas;
    }
    public Cajas(String idObs, String DescripcionObs, String opc) {
        this.idObs = idObs;
        this.DescripcionObs = DescripcionObs;
        this.opc =opc;
    }

    public Cajas(int idcaja) {
        this.idcaja = idcaja;
    }

    public String getCajas() {
        return cajas;
    }

    public void setCajas(String cajas) {
        this.cajas = cajas;
    }

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }


    public String getclientegasto() {
        return clientegasto;
    }

    public void setclientegasto(String clientegasto) {
        this.clientegasto = clientegasto;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.destino = origen;
    }

    public String getIdPersonalMov() {
        return idpersonalMov;
    }

    public void setIdPersonalMov(String idpersonalMov) {
        this.idpersonalMov = idpersonalMov;
    }



}