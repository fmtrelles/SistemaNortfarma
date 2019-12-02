/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.DescuentosInvData;
import entidad.DescuentosInvActualDetalle;
import java.util.List;

/**
 *
 * @author root
 */
public class LogicaDescuentosInv {

    DescuentosInvData objDescuento;

    public LogicaDescuentosInv() {
        objDescuento = new DescuentosInvData();
    }

    public List<DescuentosInvActualDetalle> ListaDescuentoInv() {
        return objDescuento.ListaDescuentosInv();
    }

    public boolean ActualizaDescuentoActual(DescuentosInvActualDetalle descActualDet) {
        return objDescuento.ActualizaDescuentoActual(descActualDet);
    }

    public boolean ActualizaDescuentoBase(int opc, String anio, double total, double v1, double v2, double v3,
            double v4, double v5, double v6, double v7, double v8, double v9, double v10) {
        return objDescuento.ActualizaDescuentoBase(opc, anio, total, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
    }

}
