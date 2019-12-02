/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.CruceInventariosData;
import entidad.CruceDetalle;
import entidad.CruceInventario;
import entidad.Sobrantes;
import entidad.Sobrantes_Detalles;
import entidad.Venta;
import java.util.List;

/**
 *
 * @author Kelvin
 */
public class LogicaCruceInventarios {

    CruceInventariosData objCruce;

    public LogicaCruceInventarios() {
        objCruce = new CruceInventariosData();
    }

    public boolean InsertaCruceInv(CruceInventario cruceInv) {
        return objCruce.InsertarCruceInv(cruceInv);
    }

    public boolean InsertaCruceDetalle(CruceDetalle cruceDet) {
        return objCruce.InsertarCruceDetalle(cruceDet);
    }

    public List<CruceDetalle> ListaCruceDetalle(String nombreCruce) {
        return objCruce.ListaCruceInventarios(nombreCruce);
    }

    public boolean InsertaSobrante(Sobrantes sobra) {
        return objCruce.InsertarSobrante(sobra);
    }

    public boolean InsertaSobranteDetalle(Sobrantes_Detalles sobraDeta) {
        return objCruce.InsertarSobranteDetalle(sobraDeta);
    }

    public List<Sobrantes_Detalles> ListaSobranteDetalle(String nomSobrante) {
        return objCruce.ListaSobrante(nomSobrante);
    }

    public List<Venta> ListaVentasInv(String fInicio, String fFin) {
        return objCruce.ListaVentasInventarios(fInicio, fFin);
    }

    public List<Venta> ListaVentasInvDetalle(String vIdVenta) {
        return objCruce.ListaVentasInventariosDet(vIdVenta);
    }

    public boolean InsertaVentaInforme() {
        return objCruce.InsertarVentaInforme();
    }

    public boolean InsertaVentaInformeDet(String idVenta, String tipAlma, String tipDesc, int esRespon) {
        return objCruce.InsertarVentaInformeDet(idVenta, tipAlma, tipDesc, esRespon);
    }

}
