package CapaLogica;

import CapaDatos.ZonasData;
import java.util.List;
import entidad.TipoZona;

public class LogicaZonas {

    ZonasData objzona;

    public LogicaZonas() {
        objzona = new ZonasData();
    }

    public List<TipoZona> ListarZonas() {
        return objzona.ListarZonas();
    }

    public void ActualizarZonas(String antiguo, String Nuevo) {
        objzona.ActualizarZona(antiguo, Nuevo);
    }

    public void AgregarZonas(String Nuevo) {
        objzona.AgregarZonas(Nuevo);
    }

    public void EliminarZona(String Antiguo) {
        objzona.EliminarZonas(Antiguo);
    }

    public List<TipoZona> ListarZonasAcronimo(String TL) {
        return objzona.ListarZonasAcronimo(TL);
    }

    public List<TipoZona> ListarLocacionesPreDefenidas(String TipoLocacion, String Zona) {
        return objzona.ListarLocacionesPreDefenidas(TipoLocacion, Zona);
    }

    public List<TipoZona> ListarZonasNueva() {
        return objzona.ListarZonasNueva();
    }

    public List<TipoZona> ListarLocacionesNuevas(String Zonax, String TipoLocacionx) {
        return objzona.ListarLocacionesNuevas(Zonax, TipoLocacionx);
    }
}
