package entidad;

public class ResultadoVenta {

    private int iderror;
    private String idproducto;
    private String numero;
    private boolean esaporte;
    private String IdProforma;

    public ResultadoVenta(int iderror, String idproducto, String numer, boolean esaporte, String IdProfor) {
        this.iderror = iderror;
        this.idproducto = idproducto;
        this.numero = numer;
        this.esaporte = esaporte;
        this.IdProforma = IdProfor;
    }

    public String getIdProforma() {
        return IdProforma;
    }

    public void setIdProforma(String IdProforma) {
        this.IdProforma = IdProforma;
    }

    public boolean isEsaporte() {
        return esaporte;
    }

    public void setEsaporte(boolean esaporte) {
        this.esaporte = esaporte;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ResultadoVenta() {
    }

    public int getIderror() {
        return iderror;
    }

    public void setIderror(int iderror) {
        this.iderror = iderror;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }
}
