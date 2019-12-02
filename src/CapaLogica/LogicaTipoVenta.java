package CapaLogica;

import CapaDatos.ProformaData;
import entidad.TipoVenta;
import java.util.List;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class LogicaTipoVenta {

    ProformaData obj;

    public LogicaTipoVenta() {
        obj = new ProformaData();
    }

    public List<TipoVenta> Lista_TiposVentas() {
        return obj.Lista_TiposVentas();
    }
    public List<TipoVenta> Lista_colegios() {
        return obj.Lista_Colegios();
    }
    public List<TipoVenta> Lista_TiposVentasSOAT() {
        return obj.Lista_TiposVentasSOAT();
    }

    public List<TipoVenta> Lista_TiposVentas_Inventario() {
        return obj.Lista_TiposVentas_Inventario();
    }
}