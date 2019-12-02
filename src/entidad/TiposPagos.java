/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class TiposPagos {

    private int Id_TipoPago;
    private String Descripcion;
    private String idbotica;
    private int Id_Moneda;
    private String Moneda;
    private int Id_Personal;
    private String OPERACION;
    private String NUMERO;
    private double MONTO;
    private int TIPO_MONEDA;
    private double tipo_cambio;
    private double monto_total;
    private int cantidad_total;
    private int turno;
    private int idcaja;
    private int esAbono;
    private String TPredondeo;


    public TiposPagos() {
    }

    public TiposPagos(int Id_TipoPago, String Descripcion) {
        this.Id_TipoPago = Id_TipoPago;
        this.Descripcion = Descripcion;
    }

    public int getEsAbono() {
        return esAbono;
    }

    public void setEsAbono(int esAbono) {
        this.esAbono = esAbono;
    }

    public int getId_Moneda() {
        return Id_Moneda;
    }

    public void setId_Moneda(int Id_Moneda) {
        this.Id_Moneda = Id_Moneda;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public TiposPagos(String idbotica, int Id_Personal, String OPERACION, String NUMERO, double MONTO, int TIPO_MONEDA, double tipocambio, int turno, int idcaja) {
        this.idbotica = idbotica;
        this.Id_Personal = Id_Personal;
        this.OPERACION = OPERACION;
        this.NUMERO = NUMERO;
        this.MONTO = MONTO;
        this.TIPO_MONEDA = TIPO_MONEDA;
        this.tipo_cambio = tipocambio;
        this.turno = turno;
        this.idcaja = idcaja;
    }

    public TiposPagos(String NUMERO, String operacion, double MONTO, int TIPO_MONEDA) {
        this.NUMERO = NUMERO;
        this.OPERACION = operacion;
        this.MONTO = MONTO;
        this.TIPO_MONEDA = TIPO_MONEDA;
    }

    public TiposPagos(String OPERACION, double monto_total, int cantidad_total) {
        this.OPERACION = OPERACION;
        this.monto_total = monto_total;
        this.cantidad_total = cantidad_total;
    }

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }

    public double getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(double tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public int getId_Personal() {
        return Id_Personal;
    }

    public void setId_Personal(int Id_Personal) {
        this.Id_Personal = Id_Personal;
    }

    public double getMONTO() {
        return MONTO;
    }

    public void setMONTO(double MONTO) {
        this.MONTO = MONTO;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getTPredondeo() {
        return TPredondeo;
    }

    public void setTPredondeo(String TPredondeo) {
        this.TPredondeo = TPredondeo;
    }

    public String getOPERACION() {
        return OPERACION;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setOPERACION(String OPERACION) {
        this.OPERACION = OPERACION;
    }

    public int getTIPO_MONEDA() {
        return TIPO_MONEDA;
    }

    public void setTIPO_MONEDA(int TIPO_MONEDA) {
        this.TIPO_MONEDA = TIPO_MONEDA;
    }

    public String getIdbotica() {
        return idbotica;
    }

    public void setIdbotica(String idbotica) {
        this.idbotica = idbotica;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getId_TipoPago() {
        return Id_TipoPago;
    }

    public void setId_TipoPago(int Id_TipoPago) {
        this.Id_TipoPago = Id_TipoPago;
    }
}
