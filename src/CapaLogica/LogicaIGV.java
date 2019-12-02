/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.RecuperaIGV;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaIGV {

    RecuperaIGV objigv;

    public LogicaIGV() {
        objigv = new RecuperaIGV();

    }

    public double RecuperaIGV() {
        return objigv.RetornaIGV();
    }

    public double recupera_Igv_Exonerado(String codpro) {
        return objigv.recupera_Igv_Exonerado(codpro);
    }
    
    public double recupera_PrecBotiquin(String codpro, int opc) {
        return objigv.recupera_PrecBotiquin(codpro,opc);
    }
}
