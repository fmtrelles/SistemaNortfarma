/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class DocumentoVentas {

    private String Serie;
    private String Numeracion;
    private Integer TipoVenta;
    private String Botica;
    private String documento;
    private int aperturA;

    public DocumentoVentas() {
    }

    public DocumentoVentas(String Serie, String Numeracion, Integer TipoVenta, String Botica) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.TipoVenta = TipoVenta;
        this.Botica = Botica;
    }

    public int getAperturA() {
        return aperturA;
    }

    public void setAperturA(int aperturA) {
        this.aperturA = aperturA;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getBotica() {
        return Botica;
    }

    public void setBotica(String Botica) {
        this.Botica = Botica;
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

    public Integer getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(Integer TipoVenta) {
        this.TipoVenta = TipoVenta;
    }
}
