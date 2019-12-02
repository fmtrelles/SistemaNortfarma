package entidad;
/*Miguel Gomez S.*/

public class Boticas {

    public String Id_Botica;
    public String Version;
    public Integer Id_Zona;
    public String Descripcion;
    public String Direccion;
    public String Codigo_Cargo;
    public String Codigo_Descargo;
    public String Codigo_Botica;
    public String Descripcion_Cargo;
    public String Descripcion_Descargo;
    public String Serie;
    public String Numeracion;
    private String Correo;

    public Boticas() {
    }

    public String getCodigo_Botica() {
        return Codigo_Botica;
    }

    public void setCodigo_Botica(String Codigo_Botica) {
        this.Codigo_Botica = Codigo_Botica;
    }

    public String getCodigo_Cargo() {
        return Codigo_Cargo;
    }

    public void setCodigo_Cargo(String Codigo_Cargo) {
        this.Codigo_Cargo = Codigo_Cargo;
    }

    public String getCodigo_Descargo() {
        return Codigo_Descargo;
    }

    public void setCodigo_Descargo(String Codigo_Descargo) {
        this.Codigo_Descargo = Codigo_Descargo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDescripcion_Cargo() {
        return Descripcion_Cargo;
    }

    public void setDescripcion_Cargo(String Descripcion_Cargo) {
        this.Descripcion_Cargo = Descripcion_Cargo;
    }

    public String getDescripcion_Descargo() {
        return Descripcion_Descargo;
    }

    public void setDescripcion_Descargo(String Descripcion_Descargo) {
        this.Descripcion_Descargo = Descripcion_Descargo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getId_Botica() {
        return Id_Botica;
    }

    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }


    public Integer getId_Zona() {
        return Id_Zona;
    }

    public void setId_Zona(Integer Id_Zona) {
        this.Id_Zona = Id_Zona;
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
}
