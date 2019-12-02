package entidad;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Venta implements Comparator<Venta>, Comparable<Venta> {

    private String Id_Botica;
    private int Id_Cliente;
    private int IdOperadorrec;
    private int Id_TipoPago;
    private int idpromocion;
    private String Id_Tipo_Precio;
    private int ordenVta;
    private int estado;
    private int Id_Tipo_Venta;
    private String Id_Medico;
    private String montoPagoT;
    private String vueltoT;
    private int Id_Caja;
    private String idguiaajuste;
    private String id_gruiaAjusteDet;
    private String id_gruiaAjuste;
    private String id_tipomov;
    private String nummov;
    private int idvtadet;
    private String reccupon;
    private int idprodrecarga;
    private String unidfra;
    private Date Fecha_Venta;
    private double SubTotal;    
    private double IGV;
    private String tpago;
    private double OP_decuento;
    private double gratuita;
    private String tred;
    private int id_cajero;
    private String numerFE;
    private int supervisor;
    private int empaque;
    private String obs;
    private double OpISC_CAB;
    private String ID_TIPO_DOCUMENTO_SUNAT;
    private String Serie;
    private String Numero;
    private String esgratuitaPromo;
    private Date Fecha_Registro;
    private int Id_Personal_Botica_Venta;
    private int Id_Personal_Botica_Caja;
    private Time Hora_Venta;
    private Time Hora_Registro;
    private double Total;
    private double redondeo;
    private double totfinal;
    private int Cantidad;
    private Billetes_Monedas biilete;
    private int grupo;
    private String Id_Producto;
    private String esgratuita;
    private String observaciones;
    private int unidad;
    private String idtipdocsunat;
    private int fraccion;
    private int arqueo;
    private double Precio_Venta;
    private double totalND;
    private double pvp;
    private double dcto;
    private double OPDescuento;
    private double pvpx;
    private double Descuento;
    private String timpre;
    private String id_proforma;
    private String dniresp_modifica;
    private String id_Venta;
    private String fechavta;
    private int cantidadpagos;
    private String RUC_DNI;
    private String configimpresion;
    private String modoimpresion;
    private String Nombre_RazonSocial;
    private String Direccion;
    private String DNI;
    private String DescripcionTipoPago;
    private String DESCRIPCION;
    private String Nombre;
    private String Apellido;
    private String descr_Producto;
    private String orden;
    private String t_impre;
    private int item;
    private int Empaque;
    private double Precio_Venta_Final;
    private double total_producto;
    private Date Fecha_Vencimiento;
    private String Id_Laboratorio;
    private int Mostrador_Stock_Fraccion;
    private int Mostrador_Stock_Empaque;
    private String RUC;
    private String NomCliente;
    private String cajero;
    private int turno;
    private String tipventa;
    private String tipopago;
    private String caja;
    private String internoInicio;
    private String InternoFinal;
    private int idturno;
    private String rendicion;
    private int cierre;
    private double pagado;
    private double montopagado;
    private double saldo;
    private double SaldoApertura;
    private String resultado;
    private double TotEsGratuita;
    private double notomar;
    private String miTurno;
    private String vendedor;
    private String idcolegiatura;
    private String fecha1;
    private String fecha2;
    private Date fechaPago;
    private int mes;
    private String almacen;
    private String proveedor;
    private String IdMovimiento;
    private String Documento;
    private Date fechadoc, fechareg;
    private String responsable;
    private int anulado;
    private String Campo01;
    private int ventaDelivery;
    private int pre;
    private int anul;
    private String Numeroserie;
    private String Placa;
    private String Poliza;
    private String Certificado;
    private String Partida;
    private Date Docdat;
    private String Codalm;
    private String Docnum;
    private String Typmov;
    private String Codpro;
    private String Stkprf;
    private String Despro;
    private String Codlab;
    private String Codprv;
    private String Dtopro;
    private String Prisal;
    private String Stksed;
    private String Stkfra;
    private String Codbar;
    private Date Datreg;
    private String Doctot;
    
    private String descripcionBotica;    
    private String descargo;
    private String idbotdestino;
    private String diredestino;
    private String marcaplaca;
    private String constancia;
    private String licencia;

    private double OpExonerada;
    private double OpGravada;
    private double OpGravadaDetalle;
    private double OpDescuentoDetalle;
    private double OpInafecta;
    private double OpBonificacion;
    private double preciounitario;
    private double OpDescuento;
    private double OpGratuita;
    private double OpISC;
    private double OpDescuentoTotal;

    private String Nrendicion;
    private double Sinicial;
    private double TotVNetas;
    private double TotVTarj;
    private double TotVNC;
    private double TotCPersonal;
    private double TotCTercero;
    private double TotEfecVtas;
    private double TotDep;
    private double TotPServ;
    private double TotPCta;
    private double TotRetiros;
    private double TotAgente;
    private double TotEfectivo;
    private double TotEfecEOfi;
    private double TotMtoDeposito;
    private double TotAbono;
    private double TotDifCaja;
    private double TotOGastos;
    private double TotOIngresos;
    private double TotPCliente;
    
    private String descaja;
    private String desturno;
    private int idbillete;
    private double valorBillete;
    private String numeroentregaefec;
    private int idgerente;
    private String desgerente;
    private int idusersistema;
    private String usuarioingreso;

    private String Id_Tipo_Gasto;
    private double Monto;
    private String Concepto;
    private int Id_Cliente_gasto;
    private int Id_Motivo;
    private String Origen;
    private String Destino;
    private Date fechafinalmes;
    private String correlativo;
    private String IdpersonalMov;

    private String Usuario;
    private String hostEnvio;
    private String Puerto;
    private String Passwd;
    private String SubFolder;
    private String NDocumento;
    private int tipvta;

    private String empresa_botica;
    private String direccion_botica;
    private String direccion_empresa;
    private String ruc;
    private String cliente;
    private String direccioncliente;
    private String tipodocumento;
    private String numdocumento;
    private String documentoemitido;
    private String serienumero;
    private String hora;
    private String idvta;
    private String moneda;
    private String docreferencia;
    private String sernumref;
    private String fecharef;
    private String opgravada1;
    private String opinafecta1;
    private String opigv;
    private String opimportetotal;
    private String opredondeo;
    private String optotalpagar;
    private String opdescuento1;
    private String tituloopgravada;
    private String tituloopinafecta;
    private String tituloopigv;
    private String tituloopimportetotal;
    private String tituloopredondeo;
    private String titulooptotalpagar;
    private String titulooptotaldscto;
    private String cant;
    private String producto;
    private String totaldet;
    private String idmoneda;
    private String disclaimer;
    private String serie1;
    private String numero1;
    private String fechavta1;
    private double total1;

    private String rucbarcode;
    private String tipdocbarcode;
    private String igvbarcode;
    private String totalbarcode;
    private String tipodocadquiriente;
    private String numerodocadquiriente;
    private String firmabarcode;
    private String movi;
    private String certMedico;
    private int CantiBolsa;

    public int getCantiBolsa() {
        return CantiBolsa;
    }

    public void setCantiBolsa(int CantiBolsa) {
        this.CantiBolsa = CantiBolsa;
    }
    
    
    

    public String getCertMedico() {
        return certMedico;
    }

    public void setCertMedico(String certMedico) {
        this.certMedico = certMedico;
    }
    
    public String getMovi() {
        return movi;
    }

    public void setMovi(String movi) {
        this.movi = movi;
    }

    public double getOpISC_CAB() {
        return OpISC_CAB;
    }

    public void setOpISC_CAB(double OpISC_CAB) {
        this.OpISC_CAB = OpISC_CAB;
    }
    
    
    

    public String getUnidfra() {
        return unidfra;
    }

    public void setUnidfra(String unidfra) {
        this.unidfra = unidfra;
    }
    
    public String getDescripcionBotica() {
        return descripcionBotica;
    }

    public String getDescargo() {
        return descargo;
    }

    public String getIdbotdestino() {
        return idbotdestino;
    }

    public String getDiredestino() {
        return diredestino;
    }

    public String getMarcaplaca() {
        return marcaplaca;
    }

    public String getConstancia() {
        return constancia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setDescripcionBotica(String descripcionBotica) {
        this.descripcionBotica = descripcionBotica;
    }

    public void setDescargo(String descargo) {
        this.descargo = descargo;
    }

    public void setIdbotdestino(String idbotdestino) {
        this.idbotdestino = idbotdestino;
    }

    public void setDiredestino(String diredestino) {
        this.diredestino = diredestino;
    }

    public void setMarcaplaca(String marcaplaca) {
        this.marcaplaca = marcaplaca;
    }

    public void setConstancia(String constancia) {
        this.constancia = constancia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    
    
    
    
    
    public int getOrdenVta() {
        return ordenVta;
    }

    public String getEsgratuita() {
        return esgratuita;
    }

    public void setOrdenVta(int ordenVta) {
        this.ordenVta = ordenVta;
    }

    public void setFechavta(String fechavta) {
        this.fechavta = fechavta;
    }

    public String getReccupon() {
        return reccupon;
    }

    public void setReccupon(String reccupon) {
        this.reccupon = reccupon;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
    
    
    
    public int getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(int idpromocion) {
        this.idpromocion = idpromocion;
    }

    
    
    
    public int getIdprodrecarga() {
        return idprodrecarga;
    }

    public void setIdprodrecarga(int idprodrecarga) {
        this.idprodrecarga = idprodrecarga;
    }

    
    public void setFirmabarcode(String firmabarcode) {
        this.firmabarcode = firmabarcode;
    }

    public void setIgvbarcode(String igvbarcode) {
        this.igvbarcode = igvbarcode;
    }

    public void setNumerodocadquiriente(String numerodocadquiriente) {
        this.numerodocadquiriente = numerodocadquiriente;
    }

    public void setRucbarcode(String rucbarcode) {
        this.rucbarcode = rucbarcode;
    }

    public void setTipdocbarcode(String tipdocbarcode) {
        this.tipdocbarcode = tipdocbarcode;
    }

    public void setTipodocadquiriente(String tipodocadquiriente) {
        this.tipodocadquiriente = tipodocadquiriente;
    }

    public void setTotalbarcode(String totalbarcode) {
        this.totalbarcode = totalbarcode;
    }


    public String getFirmabarcode() {
        return firmabarcode;
    }

    public String getIgvbarcode() {
        return igvbarcode;
    }

    public String getNumerodocadquiriente() {
        return numerodocadquiriente;
    }

    public String getRucbarcode() {
        return rucbarcode;
    }

    public String getTipdocbarcode() {
        return tipdocbarcode;
    }

    public String getTipodocadquiriente() {
        return tipodocadquiriente;
    }

    public String getTotalbarcode() {
        return totalbarcode;
    }


    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setDireccion_botica(String direccion_botica) {
        this.direccion_botica = direccion_botica;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public void setEmpresa_botica(String empresa_botica) {
        this.empresa_botica = empresa_botica;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    
    public void setCant(String cant) {
        this.cant = cant;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setDocreferencia(String docreferencia) {
        this.docreferencia = docreferencia;
    }

    public void setDocumentoemitido(String documentoemitido) {
        this.documentoemitido = documentoemitido;
    }

    public void setFecharef(String fecharef) {
        this.fecharef = fecharef;
    }

    public void setFechavta1(String fechavta1) {
        this.fechavta1 = fechavta1;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdmoneda(String idmoneda) {
        this.idmoneda = idmoneda;
    }

    public void setIdvta(String idvta) {
        this.idvta = idvta;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public void setOpdescuento1(String opdescuento1) {
        this.opdescuento1 = opdescuento1;
    }

    public void setOpgravada1(String opgravada1) {
        this.opgravada1 = opgravada1;
    }

    public void setOpigv(String opigv) {
        this.opigv = opigv;
    }

    public void setOpimportetotal(String opimportetotal) {
        this.opimportetotal = opimportetotal;
    }

    public void setOpinafecta1(String opinafecta1) {
        this.opinafecta1 = opinafecta1;
    }

    public void setOpredondeo(String opredondeo) {
        this.opredondeo = opredondeo;
    }

    public void setOptotalpagar(String optotalpagar) {
        this.optotalpagar = optotalpagar;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setSerie1(String serie1) {
        this.serie1 = serie1;
    }

    public void setSerienumero(String serienumero) {
        this.serienumero = serienumero;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public void setTituloopgravada(String tituloopgravada) {
        this.tituloopgravada = tituloopgravada;
    }

    public void setTituloopigv(String tituloopigv) {
        this.tituloopigv = tituloopigv;
    }

    public void setTituloopimportetotal(String tituloopimportetotal) {
        this.tituloopimportetotal = tituloopimportetotal;
    }

    public void setTituloopinafecta(String tituloopinafecta) {
        this.tituloopinafecta = tituloopinafecta;
    }

    public void setTituloopredondeo(String tituloopredondeo) {
        this.tituloopredondeo = tituloopredondeo;
    }

    public void setTitulooptotaldscto(String titulooptotaldscto) {
        this.titulooptotaldscto = titulooptotaldscto;
    }

    public void setTitulooptotalpagar(String titulooptotalpagar) {
        this.titulooptotalpagar = titulooptotalpagar;
    }

    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    public void setTotaldet(String totaldet) {
        this.totaldet = totaldet;
    }




    public String getNumero1() {
        return numero1;
    }

    public String getSerie1() {
        return serie1;
    }

    public double getTotal1() {
        return total1;
    }

    public String getCant() {
        return cant;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getFechavta1() {
        return fechavta1;
    }

    public String getIdmoneda() {
        return idmoneda;
    }

    public String getProducto() {
        return producto;
    }

    public String getTotaldet() {
        return totaldet;
    }


    public String getCliente() {
        return cliente;
    }

    public String getDireccion_botica() {
        return direccion_botica;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public String getDocreferencia() {
        return docreferencia;
    }

    public String getDocumentoemitido() {
        return documentoemitido;
    }

    public String getEmpresa_botica() {
        return empresa_botica;
    }

    public String getFecharef() {
        return fecharef;
    }

    public String getHora() {
        return hora;
    }

    public String getIdvta() {
        return idvta;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public String getOpdescuento1() {
        return opdescuento1;
    }

    public String getOpgravada1() {
        return opgravada1;
    }

    public String getOpigv() {
        return opigv;
    }

    public String getOpimportetotal() {
        return opimportetotal;
    }

    public String getOpinafecta1() {
        return opinafecta1;
    }

    public String getOpredondeo() {
        return opredondeo;
    }

    public String getOptotalpagar() {
        return optotalpagar;
    }

    public String getRuc() {
        return ruc;
    }

    public String getSerienumero() {
        return serienumero;
    }

    public String getSernumref() {
        return sernumref;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public String getTituloopgravada() {
        return tituloopgravada;
    }

    public String getTituloopigv() {
        return tituloopigv;
    }

    public String getTituloopimportetotal() {
        return tituloopimportetotal;
    }

    public String getTituloopinafecta() {
        return tituloopinafecta;
    }

    public String getTituloopredondeo() {
        return tituloopredondeo;
    }

    public String getTitulooptotaldscto() {
        return titulooptotaldscto;
    }

    public String getTitulooptotalpagar() {
        return titulooptotalpagar;
    }



    public String getid_gruiaAjuste() {
        return id_gruiaAjuste;
    }
    public void setid_gruiaAjuste(String id_gruiaAjuste) {
        this.id_gruiaAjuste = id_gruiaAjuste;
    }

    public String getid_tipomov() {
        return id_tipomov;
    }
    public void setid_tipomov(String id_tipomov) {
        this.id_tipomov = id_tipomov;
    }


    public String getid_gruiaAjusteDet() {
        return id_gruiaAjusteDet;
    }
    public void setid_gruiaAjusteDet(String id_gruiaAjusteDet) {
        this.id_gruiaAjusteDet = id_gruiaAjusteDet;
    }

    public String getnummov() {
        return nummov;
    }
    public void setnummov(String nummov) {
        this.nummov = nummov;
    }


    public String getidguiaajuste() {
        return idguiaajuste;
    }
    public void setidguiaajuste(String idguiaajuste) {
        this.idguiaajuste = idguiaajuste;
    }


    public String gett_impre() {
        return t_impre;
    }
    public void sett_impre(String t_impre) {
        this.t_impre = t_impre;
    }

    public double getgratuita() {
        return gratuita;
    }
    public void setgratuita(double gratuita) {
        this.gratuita = gratuita;
    }

    public double getOPDescuento() {
        return OPDescuento;
    }
    public void setOPDescuento(double OPDescuento) {
        this.OPDescuento = OPDescuento;
    }



    public double getOP_decuento() {
        return OP_decuento;
    }
    public void setOP_decuento(double OP_decuento) {
        this.OP_decuento = OP_decuento;
    }


    public String getconfigimpresion() {
        return configimpresion;
    }
    public void setconfigimpresion(String configimpresion) {
        this.configimpresion = configimpresion;
    }

    public String getmodoimpresion() {
        return modoimpresion;
    }
    public void setmodoimpresion(String modoimpresion) {
        this.modoimpresion = modoimpresion;
    }



    public String getnumerFE() {
        return numerFE;
    }
    public void setnumerFE(String numerFE) {
        this.numerFE = numerFE;
    }

    public String getmontoPagoT() {
        return montoPagoT;
    }
    public void setmontoPagoT(String montoPagoT) {
        this.montoPagoT = montoPagoT;
    }

    public String getvueltoT() {
        return vueltoT;
    }
    public void setvueltoT(String vueltoT) {
        this.vueltoT = vueltoT;
    }

    public String getobs() {
        return obs;
    }
    public void setobs(String obs) {
        this.obs = obs;
    }
    
    public String gettpago() {
        return tpago;
    }
    public void settpago(String tpago) {
        this.tpago = tpago;
    }

    public String gettred() {
        return tred;
    }

    public void settred(String tred) {
        this.tred = tred;
    }

    public String getidtipdocsunat() {
        return idtipdocsunat;
    }
    public void setidtipdocsunat(String idtipdocsunat) {
        this.idtipdocsunat = idtipdocsunat;
    }

    public String getNDocumento() {
        return NDocumento;
    }
    public void setNDocumento(String NDocumento) {
        this.NDocumento = NDocumento;
    }

    public int gettipvta() {
        return tipvta;
    }
    public void settipvta(int tipvta) {
        this.tipvta = tipvta;
    }

    public int getempaque() {
        return empaque;
    }
    public void setempaque(int empaque) {
        this.empaque = empaque;
    }

    public int getidvtadet() {
        return idvtadet;
    }
    public void setidvtadet(int idvtadet) {
        this.idvtadet = idvtadet;
    }




    public String getIdpersonalMov() {
        return IdpersonalMov;
    }
    public void setIdpersonalMov(String IdpersonalMov) {
        this.IdpersonalMov = IdpersonalMov;
    }

    public String getesgratuitaPromo() {
        return esgratuitaPromo;
    }
    public void setesgratuitaPromo(String esgratuitaPromo) {
        this.esgratuitaPromo = esgratuitaPromo;
    }

    public String getID_TIPO_DOCUMENTO_SUNAT() {
        return ID_TIPO_DOCUMENTO_SUNAT;
    }
    public void setID_TIPO_DOCUMENTO_SUNAT(String ID_TIPO_DOCUMENTO_SUNAT) {
        this.ID_TIPO_DOCUMENTO_SUNAT = ID_TIPO_DOCUMENTO_SUNAT;
    }

    public String getcorrelativo() {
        return correlativo;
    }
    public void setcorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getesgratuita() {
        return esgratuita;
    }
    public void setesgratuita(String esgratuita) {
        this.esgratuita = esgratuita;
    }

    public Date getfechafinalmes() {
        return fechafinalmes;
    }
    public void setfechafinalmes(Date fechafinalmes) {
        this.fechafinalmes = fechafinalmes;
    }

    public String getOrigen() {
        return Origen;
    }
    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    public String gethostEnvio() {
        return hostEnvio;
    }
    public void sethostEnvio(String hostEnvio) {
        this.hostEnvio = hostEnvio;
    }
    public String getPuerto() {
        return Puerto;
    }
    public void setPuerto(String Puerto) {
        this.Puerto = Puerto;
    }
    public String getPasswd() {
        return Passwd;
    }
    public void setPasswd(String Passwd) {
        this.Passwd = Passwd;
    }
    public String getSubFolder() {
        return SubFolder;
    }
    public void setSubFolder(String SubFolder) {
        this.SubFolder = SubFolder;
    }


    public int getId_Cliente_gasto() {
        return Id_Cliente_gasto;
    }
    public void setId_Cliente_gasto(int Id_Cliente_gasto) {
        this.Id_Cliente_gasto = Id_Cliente_gasto;
    }

    public int getitem() {
        return item;
    }
    public void setitem(int item) {
        this.item = item;
    }

    public int getId_Motivo() {
        return Id_Motivo;
    }
    public void setId_Motivo(int Id_Motivo) {
        this.Id_Motivo = Id_Motivo;
    }

    public String getId_Tipo_Gasto() {
        return Id_Tipo_Gasto;
    }
    public void setId_Tipo_Gasto(String Id_Tipo_Gasto) {
        this.Id_Tipo_Gasto = Id_Tipo_Gasto;
    }

    public double getMonto() {
        return Monto;
    }
    public void setMonto(double Monto) {
        this.Monto = Monto;
    }

    public double getredondeo() {
        return redondeo;
    }
    public void setredondeo(double redondeo) {
        this.redondeo = redondeo;
    }
    public double gettotfinal() {
        return totfinal;
    }
    public void settotfinal(double totfinal) {
        this.totfinal = totfinal;
    }

    public double getTotEsGratuita() {
        return TotEsGratuita;
    }
    public void setTotEsGratuita(double TotEsGratuita) {
        this.TotEsGratuita = TotEsGratuita;
    }


    public String getConcepto() {
        return Concepto;
    }
    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
    }

    public String getusuarioingreso() {
        return usuarioingreso;
    }
    public void setusuarioingreso(String usuarioingreso) {
        this.usuarioingreso = usuarioingreso;
    }

    public int getidusersistema() {
        return idusersistema;
    }
    public void setidusersistema(int idusersistema) {
        this.idusersistema = idusersistema;
    }

    public String getdesgerente() {
        return desgerente;
    }
    public void setdesgerente(String desgerente) {
        this.desgerente = desgerente;
    }

    public int getidgerente() {
        return idgerente;
    }
    public void setidgerente(int idgerente) {
        this.idgerente = idgerente;
    }

    public String getnumeroentregaefec() {
        return numeroentregaefec;
    }
    public void setnumeroentregaefec(String numeroentregaefec) {
        this.numeroentregaefec = numeroentregaefec;
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

    public double getOpGravadaDetalle() {
        return OpGravadaDetalle;
    }
    public void setOpGravadaDetalle(double OpGravadaDetalle) {
        this.OpGravadaDetalle = OpGravadaDetalle;
    }


    public double getOpDescuentoDetalle() {
        return OpDescuentoDetalle;
    }
    public void setOpDescuentoDetalle(double OpDescuentoDetalle) {
        this.OpDescuentoDetalle = OpDescuentoDetalle;
    }


    public double gettotalND() {
        return totalND;
    }
    public void settotalND(double totalND) {
        this.totalND = totalND;
    }

    public double getOpInafecta() {
        return OpInafecta;
    }
    public void setOpInafecta(double OpInafecta) {
        this.OpInafecta = OpInafecta;
    }

    public double getOpBonificacion() {
        return OpBonificacion;
    }
    public void setOpBonificacion(double OpBonificacion) {
        this.OpBonificacion = OpBonificacion;
    }
    
    public double getOpDescuento() {
        return OpDescuento;
    }
    public void setOpDescuento(double OpDescuento) {
        this.OpDescuento = OpDescuento;
    }

    public double getpreciounitario() {
        return preciounitario;
    }
    public void setpreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
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

    public double getvalorBillete() {
        return valorBillete;
    }
    public void setvalorBillete(double valorBillete) {
        this.valorBillete = valorBillete;
    }

    public String getdescaja() {
        return descaja;
    }
    public void setdescaja(String descaja) {
        this.descaja = descaja;
    }

    public String getdesturno() {
        return desturno;
    }
    public void setdesturno(String desturno) {
        this.desturno = desturno;
    }

    public int getidbillete() {
        return idbillete;
    }
    public void setidbillete(int idbillete) {
        this.idbillete = idbillete;
    }


    public int getVentaDelivery() {
        return ventaDelivery;
    }

    public void setVentaDelivery(int ventaDelivery) {
        this.ventaDelivery = ventaDelivery;
    }    

    public int getAnulado() {
        return anulado;
    }

    public void setAnulado(int anulado) {
        this.anulado = anulado;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Billetes_Monedas getBiilete() {
        return biilete;
    }

    public void setBiilete(Billetes_Monedas biilete) {
        this.biilete = biilete;
    }

    public int getgrupo() {
        return grupo;
    }

    public void setgrupo(int grupo) {
        this.grupo = grupo;
    }


    public String getCampo01() {
        return Campo01;
    }

    public void setCampo01(String Campo01) {
        this.Campo01 = Campo01;
    }

    public String getobservaciones() {
        return observaciones;
    }

    public void setobservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getresultado() {
        return resultado;
    }

    public void setresultado(String resultado) {
        this.resultado = resultado;
    }

    public String getVendedor() {
        return vendedor;
    }

     public String getFechaVta() {
        return fechavta;
    }

    public void setFechaVta(String fechavta) {
        this.fechavta = fechavta;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getCierre() {
        return cierre;
    }

    public void setCierre(int cierre) {
        this.cierre = cierre;
    }

    public String getRendicion() {
        return rendicion;
    }

    public void setRendicion(String rendicion) {
        this.rendicion = rendicion;
    }

    public String getMiTurno() {
        return miTurno;
    }

    public void setMiTurno(String miTurno) {
        this.miTurno = miTurno;
    }

    public String getInternoFinal() {
        return InternoFinal;
    }

    public void setInternoFinal(String InternoFinal) {
        this.InternoFinal = InternoFinal;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public int getIdturno() {
        return idturno;
    }

    public void setIdturno(int idturno) {
        this.idturno = idturno;
    }

    public int getIdOperadorrec() {
        return IdOperadorrec;
    }

    public void setIdOperadorrec(int IdOperadorrec) {
        this.IdOperadorrec = IdOperadorrec;
    }




    public String getInternoInicio() {
        return internoInicio;
    }

    public void setInternoInicio(String internoInicio) {
        this.internoInicio = internoInicio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontopagado() {
        return montopagado;
    }

    public void setMontopagado(double montopagado) {
        this.montopagado = montopagado;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldoApertura() {
        return SaldoApertura;
    }

    public void setSaldoApertura(double SaldoApertura) {
        this.SaldoApertura = SaldoApertura;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getNumeroserie() {
        return Numeroserie;
    }
    public void setNumeroserie(String Numeroserie) {
        this.Numeroserie = Numeroserie;
    }
    public String getPlaca() {
        return Placa;
    }
    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }
    public String getPoliza() {
        return Poliza;
    }
    public void setPoliza(String Poliza) {
        this.Poliza = Poliza;
    }
    public String getCertificado() {
        return Certificado;
    }
    public void setCertificado(String Certificado) {
        this.Certificado = Certificado;
    }

    public double getOpDescuentoTotal(){
        return OpDescuentoTotal;
    }
    public void setOpDescuentoTotal(double OpDescuentoTotal){
        this.OpDescuentoTotal = OpDescuentoTotal;
    }
    
    public int getPre() {
        return pre;
    }
    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getAnul() {
        return anul;
    }
    public void setAnul(int anul) {
        this.anul = anul;
    }

    public Venta() {
    }

    public Venta(String descriptipopago, String numero, Double monto) {
        this.DescripcionTipoPago = descriptipopago;
        this.Numero = numero;
        this.Total = monto;
    }

    public Venta(Date fecha, String idventa, String numero_doc, double total) {
        this.Fecha_Registro = fecha;
        this.id_Venta = idventa;
        this.Numero = numero_doc;
        this.Total = total;

    }

    public Venta(String idbotica,String idventa, String serie, String numero, double total, int tipoVenta, String idtipdocsunat,String fechavta,String numerFE, String configimpresion,
            int idipopago,String modoimpresion) {
        this.Id_Botica = idbotica;
        this.id_Venta = idventa;
        this.Serie = serie;
        this.Numero = numero;
        this.Total = total;
        this.Id_Tipo_Venta=tipoVenta;
        this.idtipdocsunat = idtipdocsunat;
        this.fecha1 = fechavta;
        this.numerFE = numerFE;
        this.configimpresion = configimpresion;
        this.Id_TipoPago = idipopago;
        this.modoimpresion= modoimpresion;
    }


    public Venta(String idventa, String idProducto, String descripcion, String labo, int unidad, int fraccion, double pvp, double total) {
        this.id_Venta = idventa;
        this.Codpro = idProducto;
        this.descr_Producto = descripcion;
        this.Id_Laboratorio = labo;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta_Final = pvp;
        this.total_producto = total;
    }

    public Venta(String fecha, String fecha2, String Id_Botica, int idcaja, int idpersonal, int turno) {
        this.fecha1 = fecha;
        this.fecha2 = fecha2;
        this.Id_Botica = Id_Botica;
        this.Id_Personal_Botica_Caja = idpersonal;
        this.Id_Caja = idcaja;
        this.turno = turno;
    }

    public Venta(String idventa, String serie, String numero, double total, String vendedor, String cajero, String producto, String labora, int und, int fracc, double pvf, double mitotal) {
        this.id_Venta = idventa;
        this.Serie = serie;
        this.Numero = numero;
        this.Total = total;
        this.responsable = vendedor;
        this.cajero = cajero;
        this.descr_Producto = producto;
        this.Id_Laboratorio = labora;
        this.unidad = und;
        this.fraccion = fracc;
        this.Precio_Venta_Final = pvf;
        this.total_producto = mitotal;
    }

    public Venta(String idventa, String serie, String numero, double total, String nomcliente, String tippago, String tipventa, String vendedor, Time horaventa, double subtotal, double igv) {
        this.id_Venta = idventa;
        this.Serie = serie;
        this.Numero = numero;
        this.Total = total;
        this.NomCliente = nomcliente;
        this.tipopago = tippago;
        this.tipventa = tipventa;
        this.vendedor = vendedor;
        this.Hora_Venta = horaventa;
        this.SubTotal = subtotal;
        this.IGV = igv;
    }

    public Venta(String cajero, String caja, String turno, int idcaja, int idpersonal, int idturno, String internoinicio, String internofinal, String numrendicion, int cierre) {
        this.cajero = cajero;
        this.caja = caja;
        this.miTurno = turno;
        this.Id_Caja = idcaja;
        this.Id_Personal_Botica_Caja = idpersonal;
        this.idturno = idturno;
        this.internoInicio = internoinicio;
        this.InternoFinal = internofinal;
        this.rendicion = numrendicion;
        this.cierre = cierre;
    }

    public Venta(String idventa, String doc, String serie, String numero, double total, String respon, String vendedor) {
        this.id_Venta = idventa;
        this.Documento = doc;
        this.Serie = serie;
        this.Numero = numero;
        this.Total = total;
        this.responsable = respon;
        this.Apellido = vendedor;
    }

    public Venta(String idventa, double total, double monto, Double saldo, int bandera) {
        this.id_Venta = idventa;
        this.montopagado = monto;
        this.total_producto = total;
        this.saldo = saldo;
        this.Id_TipoPago = bandera;
    }

    public Venta(String observacion, int aprobado, String idboti, String interno) {
        this.id_Venta = interno;
        this.DESCRIPCION = observacion;
        this.Id_Botica = idboti;
        this.cantidadpagos = aprobado;
    }

    public Venta(String ideventa, Date fechaventa, String serie, String numero, double total, int NC, int tipoventa) {
        this.id_Venta = ideventa;
        this.Total = total;
        this.Fecha_Venta = fechaventa;
        this.Serie = serie;
        this.Numero = numero;
        this.Id_TipoPago = NC;
        this.Id_Tipo_Venta = tipoventa;
    }
    
    public Venta(String idbotica, String descripcion, String numerodocumento, Date fecharegistro,String usuario,int estado, int anulado) {
        this.Id_Botica = idbotica;
        this.DESCRIPCION = descripcion;
        this.Fecha_Registro = fecharegistro;
        this.numdocumento = numerodocumento;
        this.Usuario = usuario;
        this.estado = estado;
        this.anulado = anulado;
    }
    
    public Venta(String idbotica, String descripcionBotica,String direccion, String ruc, String descargo, 
            String idbotdestino, String diredestino, String numerodocumento, Date fecharegistro,
            String marcaplaca, String constancia, String licencia) {
        
        this.Id_Botica = idbotica;
        this.descripcionBotica = descripcionBotica;
        this.direccion_botica=direccion;
        this.RUC = ruc;
        this.descargo = descargo;
        this.idbotdestino = idbotdestino;
        this.diredestino = diredestino;
        this.numdocumento = numerodocumento;
        this.Fecha_Registro = fecharegistro;
        this.marcaplaca = marcaplaca;
        this.constancia = constancia;
        this.licencia = licencia;
    }
    
    public Venta(String Id_Producto, String cant, String descripcion,int opc) {
        this.Id_Producto = Id_Producto;
        this.unidfra = cant;
        this.DESCRIPCION = descripcion;
        
        
    }
    
    public Venta(String Usuario, String hostEnvio, String Puerto, String Passwd, String SubFolder, int opc) {
        this.Usuario = Usuario;
        this.hostEnvio = hostEnvio;
        this.Puerto = Puerto;
        this.Passwd = Passwd;
        this.SubFolder = SubFolder;
        
    }

    public Venta(String Id_Botica, String Id_Venta, Date Fecha_Venta, int Id_Tipo_Venta, String ID_TIPO_DOCUMENTO_SUNAT, String Serie, String Numero) {
        this.Id_Botica = Id_Botica;
        this.id_Venta = Id_Venta;
        this.Fecha_Venta = Fecha_Venta;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.idtipdocsunat = ID_TIPO_DOCUMENTO_SUNAT;
        this.Serie = Serie;
        this.numerFE = Numero;

    }

    public Venta(String Id_Botica,int ID_CAJA,int IdTurno, Date FECHA, String Id_Tipo_Gasto, String Documento,
            double Monto,String Concepto,int Id_Cliente_gasto,int Id_Motivo,String Origen,String Destino,
            Date fechafinalmes,String correlativo,String IdpersonalMov, int item) {
        this.Id_Botica = Id_Botica;
        this.Id_Caja = ID_CAJA;
        this.idturno = IdTurno;
        this.Fecha_Venta = FECHA;
        this.Id_Tipo_Gasto = Id_Tipo_Gasto;
        this.Documento = Documento;
        this.Monto = Monto;
        this.Concepto = Concepto;
        this.Id_Cliente_gasto = Id_Cliente_gasto;
        this.Id_Motivo = Id_Motivo;
        this.Origen = Origen;
        this.Destino = Destino;
        this.fechafinalmes = fechafinalmes;
        this.correlativo = correlativo;
        this.IdpersonalMov = IdpersonalMov;
        this.item = item;
    }

    public Venta(int item,Date fecha) {
        this.item = item;
        this.Fecha_Venta = fecha;
    }

    public Venta(String Id_Botica, String Numero, String Campo01, int idcaja, int itpoventa, int idpersonal, String serie) {
        this.Id_Botica = Id_Botica;
        this.Numero = Numero;
        this.Id_Tipo_Venta = itpoventa;
        this.Id_Caja = idcaja;
        this.Id_Personal_Botica_Caja = idpersonal;
        this.Campo01 = Campo01;
        this.Serie = serie;
    }

    public Venta(String Id_Botica, String Numero, int idcaja, int itpoventa, String serie) {
        this.Id_Botica = Id_Botica;
        this.Numero = Numero;
        this.Id_Tipo_Venta = itpoventa;
        this.Id_Caja = idcaja;
        this.Serie = serie;
    }

    public Venta(String Id_Botica, String interno, int mes) {
        this.Id_Botica = Id_Botica;
        this.id_Venta = interno;
        this.mes = mes;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public int getCantidadpagos() {
        return cantidadpagos;
    }

    public void setCantidadpagos(int cantidadpagos) {
        this.cantidadpagos = cantidadpagos;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getTipventa() {
        return tipventa;
    }

    public void setTipventa(String tipventa) {
        this.tipventa = tipventa;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getIdcolegiatura() {
        return idcolegiatura;
    }

    public void setIdcolegiatura(String idcolegiatura) {
        this.idcolegiatura = idcolegiatura;
    }

    public double getNotomar() {
        return notomar;
    }

    public void setNotomar(double notomar) {
        this.notomar = notomar;
    }

    public String getPartida() {
        return Partida;
    }

    public void setPartida(String Partida) {
        this.Partida = Partida;
    }
    public Date getDocdat() {
        return Docdat;
    }
    public void setDocdat(Date Docdat) {
        this.Docdat = Docdat;
    }
    public String getCodalm() {
        return Codalm;
    }
    public void setCodalm(String Partida) {
        this.Codalm = Codalm;
    }
    public String getDocnum() {
        return Docnum;
    }
    public void setDocnum(String Docnum) {
        this.Docnum = Docnum;
    }
    public String getTypmov() {
        return Typmov;
    }
    public void setTypmov(String Typmov) {
        this.Typmov = Typmov;
    }
    public String getCodpro() {
        return Codpro;
    }
    public void setCodpro(String Codpro) {
        this.Codpro = Codpro;
    }
    public String getStkprf() {
        return Stkprf;
    }
    public void setStkprf(String Stkprf) {
        this.Stkprf = Stkprf;
    }
    public String getDespro() {
        return Despro;
    }
    public void setDespro(String Despro) {
        this.Despro = Despro;
    }
    public String getCodlab() {
        return Codlab;
    }
    public void setCodlab(String Codlab) {
        this.Codlab = Codlab;
    }
    public String getCodprv() {
        return Codprv;
    }
    public void setCodprv(String Codprv) {
        this.Codprv = Codprv;
    }
    public String getDtopro() {
        return Dtopro;
    }
    public void setDtopro(String Dtopro) {
        this.Codprv = Dtopro;
    }
    public String getPrisal() {
        return Prisal;
    }
    public void setPrisal(String Prisal) {
        this.Prisal = Prisal;
    }
    public String getStksed() {
        return Stksed;
    }
    public void setStksed(String Stksed) {
        this.Stksed = Stksed;
    }
    public String getStkfra() {
        return Stkfra;
    }
    public void setStkfra(String Stkfra) {
        this.Stkfra = Stkfra;
    }
    public String getCodbar() {
        return Codbar;
    }
    public void setCodbar(String Codbar) {
        this.Codbar = Codbar;
    }
    public String getDoctot() {
        return Doctot;
    }
    public void setDoctot(String Doctot) {
        this.Doctot = Doctot;
    }
    public Date getDatreg() {
        return Datreg;
    }
    public void setDatreg(Date Datreg) {
        this.Datreg = Datreg;
    }


  
    public Venta (String Empresa_Botica, String Direccion_Botica, String Direccion_Empresa, String RUC,
            String serie, String numero, Double monto,String fecha,
            String cliente,String direcCliente,String tipodocumento,String numdocumento, String documentoemitido,
            String serienumero,String hora,String idvta, String cajero,String vendedor,String money,String observacion,
            String docreferencia,String sernumref,String fecharef, String cantidad, String producto,String totaldet,
            String opgravada,String opinafecta,String opigv,String opimportetotal,
            String Redondeo,String optotalpagar,
            String opdescuento,String idmoneda,String disclaimer,String tituloopgravada,String tituloopinafecta,
            String tituloopigv,String tituloopimportetotal,String tituloopredondeo,String titulooptotalpagar,
            String titulooptotaldscto,
            String rucbarcode,String tipdocbarcode,String igvbarcode,String totalbarcode,String tipodocadquiriente,
            String numerodocadquiriente, String firmabarcode){

        this.empresa_botica = Empresa_Botica;
        this.direccion_botica = Direccion_Botica;
        this.direccion_empresa = Direccion_Empresa;
        this.ruc = RUC;
        this.serie1 = serie;
        this.numero1 = numero;
        this.total1 = monto;
        this.fechavta1 = fecha;
        this.cliente = cliente;
        this.direccioncliente = direcCliente;
        this.tipodocumento = tipodocumento;
        this.numdocumento = numdocumento;
        this.documentoemitido = documentoemitido;
        this.serienumero = serienumero;
        this.hora = hora;
        this.idvta = idvta;
        this.cajero = cajero;
        this.vendedor = vendedor;
        this.moneda = money;
        this.observaciones = observacion;
        this.docreferencia = docreferencia;
        this.sernumref = sernumref;
        this.fecharef = fecharef;
        this.cant = cantidad;
        this.producto = producto;
        this.totaldet = totaldet;
        this.opgravada1 = opgravada;
        this.opinafecta1 = opinafecta;
        this.opigv = opigv;
        this.opimportetotal = opimportetotal;
        this.opredondeo = Redondeo;
        this.optotalpagar = optotalpagar;
        this.opdescuento1 = opdescuento;
        this.idmoneda = idmoneda;
        this.disclaimer = disclaimer;
        this.tituloopgravada = tituloopgravada;
        this.tituloopinafecta = tituloopinafecta;
        this.tituloopigv = tituloopigv;
        this.tituloopimportetotal = tituloopimportetotal;
        this.tituloopredondeo = tituloopredondeo;
        this.titulooptotalpagar = titulooptotalpagar;
        this.titulooptotaldscto = titulooptotaldscto;


        this.rucbarcode = rucbarcode;
        this.tipdocbarcode = tipdocbarcode;
        this.igvbarcode = igvbarcode;
        this.totalbarcode = totalbarcode;
        this.tipodocadquiriente = tipodocadquiriente;
        this.numerodocadquiriente = numerodocadquiriente;
        this.firmabarcode = firmabarcode;

    }


    public Venta(String id_Venta, Date Fecha_Venta, String Nombre_RazonSocial, String tipoventa, String tipopago, double SubTotal, double Total, String Serie, String Numero, String Direcc, String ruc, String dni, String nom, Double igv, String nomcajero, double notomar, int idcliente, int idpersonalcaja) {
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.tipopago = tipopago;
        this.tipventa = tipoventa;
        this.Direccion = Direcc;
        this.RUC = ruc;
        this.Nombre = nom;
        this.IGV = igv;
        this.DNI = dni;
        this.cajero = nomcajero;
        this.notomar = notomar;
        this.Id_Cliente = idcliente;
        this.Id_Personal_Botica_Caja = idpersonalcaja;
    }

    public Venta(String id_Venta, Date Fecha_Venta, String Nombre_RazonSocial, String tipoventa, String tipopago, double SubTotal, double Total, String Serie, String Numero, String Direcc, String ruc, String dni, String nom, 
            Double igv, String nomcajero, double notomar, int idcliente, String tpago, String red,String t_impre) {
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.tipopago = tipopago;
        this.tipventa = tipoventa;
        this.Direccion = Direcc;
        this.RUC = ruc;
        this.Nombre = nom;
        this.IGV = igv;
        this.DNI = dni;
        this.cajero = nomcajero;
        this.notomar = notomar;
        this.Id_Cliente = idcliente;
        this.tpago = tpago;
        this.tred = red;
        this.t_impre=t_impre;
    }

    public Venta(String id_gruiaAjuste, String serie, String numero,Date fecha,  String obs, String nunmov, String descripcion) {
        this.Fecha_Registro = fecha;
        this.Serie = serie;
        this.Numero = numero;
        this.idguiaajuste = id_gruiaAjuste;
        this.obs = obs;
        this.nummov = nunmov;
        this.DESCRIPCION = descripcion;
        
    }

    public Venta(String Id_Botica, int ID_CAJA,String CAJA,int Turno,String turnodesc,Date FECHA, int Id_Personal,
            String Cajero, int Id_Billete,double Valor, int Cantidad,double Total, String NroEntrega, int Id_Gerente,
            String Gerente, int Id_UsuarioSistema, String UsuarioIngreso) {
        this.Id_Botica = Id_Botica;
        this.Id_Caja = ID_CAJA;
        this.descaja = CAJA;
        this.turno = Turno;
        this.desturno = turnodesc;
        this.Fecha_Registro = FECHA;
        this.Id_Personal_Botica_Caja = Id_Personal;
        this.Nombre = Cajero;
        this.idbillete = Id_Billete;
        this.valorBillete = Valor;
        this.Cantidad = Cantidad;
        this.Total = Total;
        this.numeroentregaefec = NroEntrega;
        this.idgerente = Id_Gerente;
        this.desgerente = Gerente;
        this.idusersistema = Id_UsuarioSistema;
        this.usuarioingreso = UsuarioIngreso;
    }

    public Venta(String id_Venta, Date Fecha_Venta, String Nombre_RazonSocial, String tipoventa, String tipopago, double SubTotal, double Total, String Serie, String Numero, String Direcc, String ruc, String dni, String nom, Double igv, String nomcajero, int idcliente,
            double redondeo, double totfinal, String tpago, String red, String t_impre) {
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.tipopago = tipopago;
        this.tipventa = tipoventa;
        this.Direccion = Direcc;
        this.RUC = ruc;
        this.Nombre = nom;
        this.IGV = igv;
        this.DNI = dni;
        this.cajero = nomcajero;
        this.Id_Cliente = idcliente;
        this.redondeo = redondeo;
        this.totfinal = totfinal;
        this.tpago = tpago;
        this.tred = red;
        this.t_impre = t_impre;
    }

    public Venta(String serie1, String numero1, String descripcion,int opc,String DNI,String FechaVenta,String RUC,String timpre) {
        this.Serie = serie1;
        this.Numero = numero1;
        this.DescripcionTipoPago = descripcion;
        this.DNI = DNI;
        this.fecha1 = FechaVenta;
        this.RUC = RUC;
        this.t_impre = timpre;
    }

    public Venta(double Saldo) {
        this.SaldoApertura = Saldo;
    }

    public Venta(String resultado, String opc, String opc1, String opc2, String opc3, String opc4) {
        this.resultado = resultado;
    }
   

    public Venta(String idbotica, String Partida, Date Docdat, String Codalm, String Typmov, String Docnum, String Codpro, String Stkprf,
                String Despro, String Codlab, String Codprv, String Dtopro, String Prisal, String Stksed, String Stkfra,
                String Codbar, Date Datreg, String Doctot, Date Fecha_Recepcion) {
        
        this.Id_Botica = idbotica;
        this.Partida = Partida;
        this.Docdat = Docdat;
        this.Codalm = Codalm;
        this.Typmov = Docnum;
        this.Typmov = Typmov;
        this.Codpro = Codpro;
        this.Stkprf = Stkprf;
        this.Despro = Despro;
        this.Codlab = Codlab;
        this.Codprv = Codprv;
        this.Dtopro = Dtopro;
        this.Prisal = Prisal;
        this.Stksed = Stksed;
        this.Stkfra = Stkfra;
        this.Codbar = Codbar;
        this.Datreg = Datreg;
        this.Doctot = Doctot;
        this.Fecha_Venta = Fecha_Recepcion;
    }

    public Venta(String id_Venta, Date Fecha_Venta, double Total, String Serie, String Numero, String vendedor, String nomcajero, int anulado) {
        this.Fecha_Venta = Fecha_Venta;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.cajero = nomcajero;
        this.vendedor = vendedor;
        this.anulado = anulado;

    }

    public Venta(String id_Venta, Date Fecha_Venta, String Nombre_RazonSocial, String tipoventa, String tipopago, double SubTotal, double Total, String Serie, String Numero, String Direcc, String ruc, String dni, String nom, Double igv, String nomcajero, int idcliente, String serieauto, String placa, String poliza, String certificado) {
        this.Fecha_Venta = Fecha_Venta;
        this.SubTotal = SubTotal;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.Nombre_RazonSocial = Nombre_RazonSocial;
        this.tipopago = tipopago;
        this.tipventa = tipoventa;
        this.Direccion = Direcc;
        this.RUC = ruc;
        this.Nombre = nom;
        this.IGV = igv;
        this.DNI = dni;
        this.cajero = nomcajero;
        this.Id_Cliente = idcliente;
        this.Numeroserie = serieauto;
        this.Placa = placa;
        this.Poliza = poliza;
        this.Certificado = certificado;
    }

    public Date getFechadoc() {
        return fechadoc;
    }

    public void setFechadoc(Date fechadoc) {
        this.fechadoc = fechadoc;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Venta(String Documento, String serie, String numero, Date fechadoc, Date fechareg, String resp, String movi) {
        this.Documento = Documento;
        this.Serie = serie;
        this.Numero = numero;
        this.fechadoc = fechadoc;
        this.fechareg = fechareg;
        this.responsable = resp;
        this.movi = movi;
    }

    public Venta(String idvent, double total, Date FechaRegistro, Time HoraVenta, String DESCRIPCION, String serie, String numero) {
        this.id_Venta = idvent;
        this.Total = total;
        this.Fecha_Registro = FechaRegistro;
        this.Hora_Venta = HoraVenta;
        this.DESCRIPCION = DESCRIPCION;
        this.Serie = serie;
        this.Numero = numero;
    }

    public Venta(String idvent, Date fecha, String serie, String numero, String tipopago, double total, double subtotal, String tipoventa) {
        this.id_Venta = idvent;
        this.Fecha_Venta = fecha;
        this.Serie = serie;
        this.Numero = numero;
        this.tipopago = tipopago;
        this.Total = total;
        this.SubTotal = subtotal;
        this.tipventa = tipoventa;

    }

//PARA MI PAGO DE CLIENTES
    public Venta(String idbotica, String idtipopago, double valor, String doc) {
        this.Id_Botica = idbotica;
        this.DescripcionTipoPago = idtipopago;
        this.total_producto = valor;
        this.Numero = doc;
    }

    public Venta(String idproforma, String Id_Botica, int Id_Cliente, int Id_TipoPago, int Id_Tipo_Venta, String Id_Medico, String colegiatura, int Id_Caja, Date Fecha_Venta, double total, double SubTotal, double IGV, String Serie, String Numero, Date Fecha_Registro, int Id_Personal_Botica_Venta, int Id_Personal_Botica_Caja, String dniresponsable, String ruc, String nomcliente, String dni, String direcc, int delivery
            ,String montoPagoT,String vueltoT,String reccupon,double OpISC_CAB, int CantiBolsa) {
        this.Id_Botica = Id_Botica;
        this.Id_Cliente = Id_Cliente;
        this.Id_TipoPago = Id_TipoPago;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.Id_Medico = Id_Medico;
        this.idcolegiatura = colegiatura;
        this.Id_Caja = Id_Caja;
        this.Fecha_Venta = Fecha_Venta;
        this.Total = total;
        this.SubTotal = SubTotal;
        this.IGV = IGV;
        this.Serie = Serie;
        this.Numero = Numero;
        this.Fecha_Registro = Fecha_Registro;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Id_Personal_Botica_Caja = Id_Personal_Botica_Caja;
        this.id_proforma = idproforma;
        this.dniresp_modifica = dniresponsable;
        this.RUC = ruc;
        this.NomCliente = nomcliente;
        this.DNI = dni;
        this.Direccion = direcc;
        this.ventaDelivery = delivery;
        this.montoPagoT = montoPagoT;
        this.vueltoT = vueltoT;
        this.reccupon = reccupon;
        this.OpISC_CAB=OpISC_CAB;
        this.CantiBolsa = CantiBolsa;
    }

    public Venta(String idbotica, String Id_Tipo_Precio, double Total, double subtotal, String Id_Producto, int unidad, int fraccion, double Precio_Venta, double Descuento,String descrip, String orden, int IdOperadorrec
            ,double OpGravada, double Opexonerada, double OpInafecta, double OpGratuita, double OpISC, double IGV,double OpBonificacion, double OpDescuentoTotal,double OpDescuento,double Precio_Unitario, String esgratuita,
            double TotEsGratuita, String esgratuitaPromo, int empaque, int idpromocion,String certMedico) {
        this.Id_Botica = idbotica;
        this.Id_Tipo_Precio = Id_Tipo_Precio;
        this.Total = Total;
        this.Id_Producto = Id_Producto;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta = Precio_Venta;
        this.Descuento = Descuento;
        this.SubTotal = subtotal;
        this.descr_Producto=descrip;
        this.orden = orden;
        this.IdOperadorrec= IdOperadorrec;
        this.TotEsGratuita = TotEsGratuita;
        this.esgratuitaPromo= esgratuitaPromo;
        this.esgratuita = esgratuita;

        this.OpGravada = OpGravada;
        this.OpExonerada = Opexonerada;
        this.OpInafecta = OpInafecta;
        this.OpGratuita = OpGratuita;
        this.OpISC = OpISC;
        this.IGV = IGV;
        this.OpBonificacion = OpBonificacion;
        this.OpDescuento = OpDescuento;
        this.preciounitario = preciounitario;
        this.OpDescuentoTotal = OpDescuentoTotal;
        this.Empaque = empaque;
        this.idpromocion = idpromocion;
        this.certMedico = certMedico;
        
    }

    public Venta(String Id_Botica, String id_Venta) {
        this.Id_Botica = Id_Botica;
        this.id_Venta = id_Venta;
    }
    public Venta(String Id_Botica, String Documento, int caja, int tipvta,int opc,String serie) {
        this.Id_Botica = Id_Botica;       
        this.NDocumento = Documento;
        this.tipvta = tipvta;
        this.Id_Caja = caja;
        this.Serie = serie;
    }
    public Venta(String Id_Botica, String serie, String numero, String Interno,int opc) {
        this.Id_Botica = Id_Botica;
        this.Numero = numero;
        this.id_Venta = Interno;
        this.Serie = serie;
    }
    public Venta(String Id_Botica, int id_caja, int turno, int idcajero, int idsupervisor, Date fecha) {
        this.Id_Botica = Id_Botica;
        this.Id_Caja = id_caja;
        this.turno = turno;
        this.id_cajero = idcajero;
        this.supervisor = idsupervisor;
        this.Fecha_Venta = fecha;
    }

    public Venta(String Id_Botica, int id_caja, int turno, int idcajero, int idsupervisor, Date fecha ,String Nrendicion,
            double Sinicial,double TotVNetas,double TotVTarj,double TotVNC,double TotCPersonal,double TotCTercero,
            double TotEfecVtas,double TotDep,double TotPServ,double TotPCta,double TotRetiros,double TotAgente,
            double TotEfectivo,double TotEfecEOfi,double TotMtoDeposito,double TotAbono,double TotDifCaja,double TotOGastos,
            double TotOIngresos,double TotPCliente, String observaciones) {
        
        this.Id_Botica = Id_Botica;
        this.Id_Caja = id_caja;
        this.turno = turno;
        this.id_cajero = idcajero;
        this.supervisor = idsupervisor;
        this.Fecha_Venta = fecha;
        this.Nrendicion = Nrendicion;
        this.Sinicial = Sinicial;
        this.TotVNetas = TotVNetas;
        this.TotVTarj = TotVTarj;
        this.TotVNC = TotVNC;
        this.TotCPersonal = TotCPersonal;
        this.TotCTercero = TotCTercero;
        this.TotEfecVtas = TotEfecVtas;
        this.TotDep = TotDep;
        this.TotPServ = TotPServ;
        this.TotPCta = TotPCta;
        this.TotRetiros = TotRetiros;
        this.TotAgente = TotAgente;
        this.TotEfectivo = TotEfectivo;
        this.TotEfecEOfi = TotEfecEOfi;
        this.TotMtoDeposito = TotMtoDeposito;
        this.TotAbono = TotAbono;
        this.TotDifCaja = TotDifCaja;
        this.TotOGastos = TotOGastos;
        this.TotOIngresos = TotOIngresos;
        this.TotPCliente = TotPCliente;
        this.observaciones = observaciones;

    }
    
    public Venta(String Id_Botica, String Serie, String Numero) {
        this.Id_Botica = Id_Botica;
        this.Serie = Serie;
        this.Numero = Numero;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getIdMovimiento() {
        return IdMovimiento;
    }

    public void setIdMovimiento(String IdMovimiento) {
        this.IdMovimiento = IdMovimiento;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Venta(String Id_Botica, String almacen, String proveedor, String idtipomov, String docu, String movi,int opc) {
        this.Id_Botica = Id_Botica;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.IdMovimiento = idtipomov;
        this.Documento = docu;
        this.movi = movi;
    }

    //PARA MI DETALLE DE PAGOS DEL CLIENTE
    public Venta(String idbotica, int idtipo, String idventa) {
        this.Id_Botica = idbotica;
        this.Id_TipoPago = idtipo;
        this.id_Venta = idventa;
    }

    public Venta(String idbotica, int idcaja, String idventa, int tipopago) {
        this.Id_Botica = idbotica;
        this.Id_Caja = idcaja;
        this.id_Venta = idventa;
        this.Id_TipoPago = tipopago;
    }

    public Venta(String idventa, double Total) {//NO TOCAR
        this.Total = Total;
        this.id_Venta = idventa;
    }
    public Venta(String idventa, double Total,String fecha) {//NO TOCAR
        this.Total = Total;
        this.id_Venta = idventa;
        this.fechavta = fecha;
    }

    public Venta(String DescripcionTipoPago, int cantidad, double Total) {
        this.Total = Total;
        this.cantidadpagos = cantidad;
        this.DescripcionTipoPago = DescripcionTipoPago;
    }

    public Venta(String DescripcionTipoPago, int cantidad, double Total, int grupo) {
        this.Total = Total;
        this.cantidadpagos = cantidad;
        this.DescripcionTipoPago = DescripcionTipoPago;
        this.grupo = grupo;
    }

    public Venta(String Id_Botica, String id_Venta, String fec1, String fec2) {
        this.Id_Botica = Id_Botica;
        this.id_Venta = id_Venta;
        this.fecha1 = fec1;
        this.fecha2 = fec2;
    }

    public Venta(String Id_Botica, String id_Venta, int pre, int anul) {
        this.Id_Botica = Id_Botica;
        this.id_Venta = id_Venta;
        this.pre = pre;
        this.anul = anul;
    }
    
    public Venta(String Id_Botica, int idpersona) {
        this.Id_Botica = Id_Botica;
        this.Id_Personal_Botica_Venta = idpersona;
    }

    public Venta(String id_Venta, Date Fecha_Registro, double Total) {
        this.Fecha_Registro = Fecha_Registro;
        this.Total = Total;
        this.id_Venta = id_Venta;
    }

    public Venta(String NroEntrega, double Valor, int Cantidad, double Total) {
        this.numeroentregaefec = NroEntrega;
        this.valorBillete = Valor;
        this.Cantidad = Cantidad;
        this.Total = Total;
    }
    
    public Venta(String NroEntrega) {
        this.numeroentregaefec = NroEntrega;
    }
   

    //PARA RECUPERRAR UNA VENTA ESPECIFICA
    public Venta(String Id_Producto, String descrpproduc, int empaque, int unidad, int fraccion, double pv, double descuento, double prvfinal, double totalproduc, double igv_exone, int mostunid, int mosfracc, String lab,
            double gratuita,double OP_decuento,int idvtadet, int idprodrecarga, int idpromocion, int orden, String Tipo_Precio,
            double isc1,double OP_ISC) {
        this.Id_Producto = Id_Producto;
        this.descr_Producto = descrpproduc;
        this.Empaque = empaque;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta = pv;
        this.Descuento = descuento;
        this.Precio_Venta_Final = prvfinal;
        this.total_producto = totalproduc;
        this.IGV = igv_exone;
        this.Mostrador_Stock_Fraccion = mosfracc;
        this.Mostrador_Stock_Empaque = mostunid;
        this.Id_Laboratorio = lab;
        this.gratuita = gratuita;
        this.OP_decuento = OP_decuento;
        this.idvtadet = idvtadet;
        this.idprodrecarga = idprodrecarga;
        this.idpromocion = idpromocion;
        this.ordenVta = orden;
        this.Id_Tipo_Precio = Tipo_Precio;
        this.OpISC = OP_ISC;
        this.OpISC_CAB = isc1;

    }

    public Venta(String codigo, String producto, String lab,int unidad,int fraccion,double Precio_Venta_Final,double Total,
            String Movimiento,String id_gruiaAjusteDet, String Id_Botica,String id_gruiaAjuste, String tipoMov,String numMov) {
        this.Id_Producto = codigo;
        this.descr_Producto = producto;
        this.Id_Laboratorio = lab;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.pvpx = Precio_Venta_Final;
        this.Total = Total;
        this.DESCRIPCION = Movimiento;
        this.id_gruiaAjusteDet =id_gruiaAjusteDet;
        this.Id_Botica = Id_Botica;
        this.id_gruiaAjuste= id_gruiaAjuste;
        this.id_tipomov = tipoMov;
        this.nummov = numMov;

    }

    public Venta(String Id_Producto, String descrpproduc, int empaque, int unidad, int fraccion, double pv, double descuento, double prvfinal, double totalproduc, double igv_exone, int mostunid, int mosfracc,double totalND) {
        this.Id_Producto = Id_Producto;
        this.descr_Producto = descrpproduc;
        this.Empaque = empaque;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta = pv;
        this.Descuento = descuento;
        this.Precio_Venta_Final = prvfinal;
        this.total_producto = totalproduc;
        this.IGV = igv_exone;
        this.Mostrador_Stock_Fraccion = mosfracc;
        this.Mostrador_Stock_Empaque = mostunid;
        this.totalND = totalND;

    }

    //PARA RECUPERAR EL DETALLE DE MOVIMIENTO
    public Venta(String Id_Producto, String Descripcion, int unidad, int fraccion, double Precio_Venta, double Descuento) {
        this.Id_Producto = Id_Producto;
        this.descr_Producto = Descripcion;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.total_producto = Precio_Venta;
        this.Descuento = Descuento;
    }

    //PARA RECUPERRAR UNA VENTA FACTURAS
    public Venta(String IDVENTA, String SERIE, String NUMERO, double TOTAL, double SUBTOTAL, double IGV, String TIPOPAGO, Date Fecha, String vendedor, String cajero, String cliente, String ruc, String direccion) {
        this.id_Venta = IDVENTA;
        this.Serie = SERIE;
        this.Numero = NUMERO;
        this.Total = TOTAL;
        this.SubTotal = SUBTOTAL;
        this.IGV = IGV;
        this.tipopago = TIPOPAGO;
        this.fechaPago = Fecha;
        this.Apellido = vendedor;
        this.cajero = cajero;
        this.Nombre_RazonSocial = cliente;
        this.RUC = ruc;
        this.Direccion = direccion;

    }

    //PARA UNA NOTA DE CREDITO
    public Venta(String Id_Botica, String serie, String Numero, double Total, String id_Venta, Date fecha, int Id_Personal_Botica_Venta, String cliente, String dni, String ruc, String descripcion, String direcc, double subtotal, double OpGravada,double OpGravadaDetalle,
            double redondeo, double totfinal, String timpre) {
        this.Id_Botica = Id_Botica;
        this.Numero = Numero;
        this.Total = Total;
        this.id_Venta = id_Venta;
        this.Fecha_Venta = fecha;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.NomCliente = cliente;
        this.DNI = dni;
        this.RUC = ruc;
        this.DESCRIPCION = descripcion;
        this.Direccion = direcc;
        this.Serie = serie;
        this.SubTotal = subtotal;
        this.OpGravada = OpGravada;
        this.OpGravadaDetalle = OpGravadaDetalle;
        this.redondeo = redondeo;
        this.totfinal = totfinal;
        this.t_impre = timpre;
    }

    public Venta(String Id_Botica, String Id_Producto, double Precio_Venta, int unidad, int fraccion, int Id_Personal_Botica_Venta) {
        this.Id_Botica = Id_Botica;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Id_Producto = Id_Producto;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta = Precio_Venta;
    }

    public Venta(String Id_Botica, String Id_Producto, double Precio_Venta, int unidad, int fraccion, double pvp,double dcto,double pvpx,int Id_Personal_Botica_Venta,double auxigv,double sutot,double OpInafecta,double OpGravada,double OpGravadaDetalle, double OpDescuentoDetalle,String lab,int empaque,
            double gratuita, String esgratuita,double descuento,int idvtadeta, int idprodrecarga, String tipprecio,
            double opisc_cab, double opisc) {
        this.Id_Botica = Id_Botica;
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
        this.Id_Producto = Id_Producto;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.Precio_Venta = Precio_Venta;
        this.pvp = pvp;
        this.dcto = dcto;
        this.pvpx = pvpx;
        this.IGV = auxigv;
        this.SubTotal = sutot;
        this.OpInafecta = OpInafecta;
        this.OpGravada = OpGravada;
        this.OpGravadaDetalle = OpGravadaDetalle;
        this.OpDescuentoDetalle = OpDescuentoDetalle;
        this.Id_Laboratorio = lab;
        this.empaque = empaque;
        this.gratuita = gratuita;
        this.esgratuitaPromo = esgratuita;
        this.OPDescuento = descuento;
        this.idvtadet = idvtadeta;
        this.idprodrecarga = idprodrecarga;
        this.Id_Tipo_Precio = tipprecio;
        this.OpISC_CAB = opisc_cab;
        this.OpISC = opisc;

    }
     public Venta(String idventa,String fecha ,String Serie, String Numero, String descripcion,   int unidad, int fraccion, double total) {
        this.id_Venta = idventa;//CODIGOMAX
        this.fechavta = fecha;
        this.Serie=Serie;
        this.Numero=Numero;
        this.descr_Producto = descripcion;
        this.unidad = unidad;
        this.fraccion = fraccion;
        this.total_producto = total;
    }

    public String getNomCliente() {
        return NomCliente;
    }

    public void setNomCliente(String NomCliente) {
        this.NomCliente = NomCliente;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
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

    //DETALLE DE NOTA DE CREDIRO
    public String getId_Laboratorio() {
        return Id_Laboratorio;
    }

    public void setId_Laboratorio(String Id_Laboratorio) {
        this.Id_Laboratorio = Id_Laboratorio;
    }

    public Date getFecha_Vencimiento() {
        return Fecha_Vencimiento;
    }

    public void setFecha_Vencimiento(Date Fecha_Vencimiento) {
        this.Fecha_Vencimiento = Fecha_Vencimiento;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
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

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getEmpaque() {
        return Empaque;
    }

    public void setEmpaque(int Empaque) {
        this.Empaque = Empaque;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public void setNombre_RazonSocial(String Nombre_RazonSocial) {
        this.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public double getPrecio_Venta_Final() {
        return Precio_Venta_Final;
    }

    public void setPrecio_Venta_Final(double Precio_Venta_Final) {
        this.Precio_Venta_Final = Precio_Venta_Final;
    }

    public double getpvp() {
        return pvp;
    }

    public void setpvp(double pvp) {
        this.pvp = pvp;
    }

    public double getdcto() {
        return dcto;
    }

    public void setdcto(double dcto) {
        this.dcto = dcto;
    }

    public double getpvpx() {
        return pvpx;
    }

    public void setpvpx(double pvpx) {
        this.pvpx = pvpx;
    }


    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setRUC_DNI(String RUC_DNI) {
        this.RUC_DNI = RUC_DNI;
    }

    public String getDescr_Producto() {
        return descr_Producto;
    }

    public void setDescr_Producto(String descr_Producto) {
        this.descr_Producto = descr_Producto;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public double getTotal_producto() {
        return total_producto;
    }

    public void setTotal_producto(double total_producto) {
        this.total_producto = total_producto;
    }

    public String getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(String id_Venta) {
        this.id_Venta = id_Venta;
    }

    public String getDniresp_modifica() {
        return dniresp_modifica;
    }

    public void setDniresp_modifica(String dniresp_modifica) {
        this.dniresp_modifica = dniresp_modifica;
    }

    public int getFraccion() {
        return fraccion;
    }

    public void setFraccion(int fraccion) {
        this.fraccion = fraccion;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public String getId_proforma() {
        return id_proforma;
    }

    public void setId_proforma(String id_proforma) {
        this.id_proforma = id_proforma;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public double getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(double Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    //PARA LA VENTA DETALLE
    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
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

    public Time getHora_Registro() {
        return Hora_Registro;
    }

    public void setHora_Registro(Time Hora_Registro) {
        this.Hora_Registro = Hora_Registro;
    }

    public Time getHora_Venta() {
        return Hora_Venta;
    }

    public void setHora_Venta(Time Hora_Venta) {
        this.Hora_Venta = Hora_Venta;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }

    public String getId_Botica() {
        return Id_Botica;
    }

    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
    }

    public int getId_Caja() {
        return Id_Caja;
    }

    public void setId_Caja(int Id_Caja) {
        this.Id_Caja = Id_Caja;
    }

    public int getId_Cajero() {
        return id_cajero;
    }

    public void setId_Cajero(int id_cajero) {
        this.id_cajero = id_cajero;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
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

    public int getId_Personal_Botica_Caja() {
        return Id_Personal_Botica_Caja;
    }

    public void setId_Personal_Botica_Caja(int Id_Personal_Botica_Caja) {
        this.Id_Personal_Botica_Caja = Id_Personal_Botica_Caja;
    }

    public int getId_Personal_Botica_Venta() {
        return Id_Personal_Botica_Venta;
    }

    public void setId_Personal_Botica_Venta(int Id_Personal_Botica_Venta) {
        this.Id_Personal_Botica_Venta = Id_Personal_Botica_Venta;
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

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }    

    public String getNrendicion() {
        return Nrendicion;
    }
    public void setNrendicion(String Nrendicion) {
        this.Nrendicion = Nrendicion;
    }
    
    public double getSinicial() {
        return Sinicial;
    }
    public void setSinicial(double Sinicial) {
        this.Sinicial = Sinicial;
    }

    public double getTotVNetas() {
        return TotVNetas;
    }
    public void setTotVNetas(double TotVNetas) {
        this.TotVNetas = TotVNetas;
    }

    public double getTotVTarj() {
        return TotVTarj;
    }
    public void setTotVTarj(double TotVTarj) {
        this.TotVTarj = TotVTarj;
    }

    public double getTotVNC() {
        return TotVNC;
    }
    public void setTotVNC(double TotVNC) {
        this.TotVNC = TotVNC;
    }

    public double getTotCPersonal() {
        return TotCPersonal;
    }
    public void setTotCPersonal(double TotCPersonal) {
        this.TotCPersonal = TotCPersonal;
    }

    public double getTotCTercero() {
        return TotCTercero;
    }
    public void setTotCTercero(double TotCTercero) {
        this.TotCTercero = TotCTercero;
    }

    public double getTotEfecVtas() {
        return TotEfecVtas;
    }
    public void setTotEfecVtas(double TotEfecVtas) {
        this.TotEfecVtas = TotEfecVtas;
    }

    public double getTotDep() {
        return TotDep;
    }
    public void setTotDep(double TotDep) {
        this.TotDep = TotDep;
    }

    public double getTotPServ() {
        return TotPServ;
    }
    public void setTotPServ(double TotPServ) {
        this.TotPServ = TotPServ;
    }

    public double getTotPCta() {
        return TotPCta;
    }
    public void setTotPCta(double TotPCta) {
        this.TotPCta = TotPCta;
    }

    public double getTotRetiros() {
        return TotRetiros;
    }
    public void setTotRetiros(double TotRetiros) {
        this.TotRetiros = TotRetiros;
    }

    public double getTotAgente() {
        return TotAgente;
    }
    public void setTotAgente(double TotAgente) {
        this.TotAgente = TotAgente;
    }

    public double getTotEfectivo() {
        return TotEfectivo;
    }
    public void setTotEfectivo(double TotEfectivo) {
        this.TotEfectivo = TotEfectivo;
    }

    public double getTotEfecEOfi() {
        return TotEfecEOfi;
    }
    public void setTotEfecEOfi(double TotEfecEOfi) {
        this.TotEfecEOfi = TotEfecEOfi;
    }

    public double getTotMtoDeposito() {
        return TotMtoDeposito;
    }
    public void setTotMtoDeposito(double TotMtoDeposito) {
        this.TotMtoDeposito = TotMtoDeposito;
    }

    public double getTotAbono() {
        return TotAbono;
    }
    public void setTotAbono(double TotAbono) {
        this.TotAbono = TotAbono;
    }

    public double getTotDifCaja() {
        return TotDifCaja;
    }
    public void setTotDifCaja(double TotDifCaja) {
        this.TotDifCaja = TotDifCaja;
    }

    public double getTotOGastos() {
        return TotOGastos;
    }
    public void setTotOGastos(double TotOGastos) {
        this.TotOGastos = TotOGastos;
    }
    
    public double getTotOIngresos() {
        return TotOIngresos;
    }
    public void setTotOIngresos(double TotOIngresos) {
        this.TotOIngresos = TotOIngresos;
    }


    public double getTotPCliente() {
        return TotPCliente;
    }
    public void setTotPCliente(double TotPCliente) {
        this.TotPCliente = TotPCliente;
    }

    public int getarqueo() {
        return arqueo;
    }

    public void setarqueo(int arqueo) {
        this.arqueo = arqueo;
    }

    @Override
    public int compare(Venta o1, Venta o2) {
        return Integer.parseInt(o1.orden) - Integer.parseInt(o2.orden);
    }

    @Override
    public int compareTo(Venta o) {
        return (this.orden).compareTo(o.orden);
    }
}
