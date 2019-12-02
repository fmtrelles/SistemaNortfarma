/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.GuiaAjusteData;
import entidad.GuiaAjustes;
import entidad.GuiaAjustesDetalle;

/**
 *
 * @author root
 */
public class LogicaGuiaAjuste {

    GuiaAjusteData objGuia;

    public LogicaGuiaAjuste() {
        objGuia = new GuiaAjusteData();
    }

    public boolean InsertaGuia(GuiaAjustes guia) {
        return objGuia.InsertarGuiaAjuste(guia);
    }

    public boolean InsertaGuiaDetalle(GuiaAjustesDetalle guiaDet,String s, String n) {
        return objGuia.InsertarGuiaDetalle(guiaDet,s,n);
    }


}
