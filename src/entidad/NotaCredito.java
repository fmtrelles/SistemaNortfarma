package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class NotaCredito {

    private String id_botica;
    private String Numero_Documento;
    private Date Fecha_Documento;
    private double Total;
    private String Id_Venta_Nota_Credito;

    public NotaCredito() {
    }

    public NotaCredito(String Numero_Documento, Date Fecha_Documento, double Total, String Id_Venta_Nota_Credito) {

        this.Numero_Documento = Numero_Documento;
        this.Fecha_Documento = Fecha_Documento;
        this.Total = Total;
        this.Id_Venta_Nota_Credito = Id_Venta_Nota_Credito;
    }

    public Date getFecha_Documento() {
        return Fecha_Documento;
    }

    public void setFecha_Documento(Date Fecha_Documento) {
        this.Fecha_Documento = Fecha_Documento;
    }

    public String getId_Venta_Nota_Credito() {
        return Id_Venta_Nota_Credito;
    }

    public void setId_Venta_Nota_Credito(String Id_Venta_Nota_Credito) {
        this.Id_Venta_Nota_Credito = Id_Venta_Nota_Credito;
    }

    public String getNumero_Documento() {
        return Numero_Documento;
    }

    public void setNumero_Documento(String Numero_Documento) {
        this.Numero_Documento = Numero_Documento;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getId_botica() {
        return id_botica;
    }

    public void setId_botica(String id_botica) {
        this.id_botica = id_botica;
    }
}
