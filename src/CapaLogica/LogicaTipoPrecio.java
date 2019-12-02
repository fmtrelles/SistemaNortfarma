package CapaLogica;

import CapaDatos.TipoPrecioData;
import java.util.List;
import entidad.TipoPrecio;

public class LogicaTipoPrecio {

    TipoPrecioData objtipoprecio;

    public LogicaTipoPrecio() {
        objtipoprecio = new TipoPrecioData();
    }

    public List<TipoPrecio> ListarTipoPrecio() {
        return objtipoprecio.ListarTipoPrecio();
    }

    public void ActualizarTipoPrecio(String antiguo, String Nuevo) {
        objtipoprecio.ActualizarTipoPrecio(antiguo, Nuevo);
    }

    public void AgregarTipoPrecio(String Nuevo) {
        objtipoprecio.AgregarTipoPrecio(Nuevo);
    }

    public Integer EliminarTipoPrecio(String Antiguo) {
        return objtipoprecio.EliminarTipoPrecio(Antiguo);
    }
}
