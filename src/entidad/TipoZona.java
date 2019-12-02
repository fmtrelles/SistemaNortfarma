package entidad;

public class TipoZona {

    public int Id_Zona;
    public String Descripcion;

    public TipoZona() {
    }

    public TipoZona(int Id_Zona, String Descripcion) {

        this.Id_Zona = Id_Zona;
        this.Descripcion = Descripcion;

    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getId_Zona() {
        return Id_Zona;
    }

    public void setId_Zona(int Id_Zona) {
        this.Id_Zona = Id_Zona;
    }

    public TipoZona(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
