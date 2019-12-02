package entidad;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class TipoVenta {

    private double Monto;
    private int Id_Tipo_Venta;
    private String DESCRIPCION;
    private String abreviatura;
    private String nombre;
    private int Modificable;

    public TipoVenta(String abreviatura,String nombre) {
        this.abreviatura = abreviatura;
        this.nombre = nombre;        
    }
    
    public TipoVenta(double Monto, int Id_Tipo_Venta, String DESCRIPCION) {
        this.Monto = Monto;
        this.Id_Tipo_Venta = Id_Tipo_Venta;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getModificable() {
        return Modificable;
    }

    public void setModificable(int Modificable) {
        this.Modificable = Modificable;
    }

    public TipoVenta() {
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getId_Tipo_Venta() {
        return Id_Tipo_Venta;
    }

    public void setId_Tipo_Venta(int Id_Tipo_Venta) {
        this.Id_Tipo_Venta = Id_Tipo_Venta;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
