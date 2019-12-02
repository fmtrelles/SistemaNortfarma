/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.HojaTrabajoData;
import entidad.HojaDeTrabajo;

/**
 *
 * @author KELVIN
 */
public class LogicaHojaTrabajo {

    HojaTrabajoData hTrabajo;

    public LogicaHojaTrabajo() {
        hTrabajo = new HojaTrabajoData();
    }

    public boolean InsertaHojaTrabajo(HojaDeTrabajo hMarcacion) {
        return hTrabajo.InsertaHojatrabajo(hMarcacion);
    }

    public HojaDeTrabajo VerIdHoja() {
        return hTrabajo.VerIdHoja();
    }

}
