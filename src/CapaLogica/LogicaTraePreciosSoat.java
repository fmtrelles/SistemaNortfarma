/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.PreciosSoatData;
import entidad.PreciosSoat;
import java.util.List;

/**
 *
 * @author gparedes
 */
public class LogicaTraePreciosSoat {

    PreciosSoatData objModelosSoat = new PreciosSoatData();

    public LogicaTraePreciosSoat() {
    }

    public List<PreciosSoat> ListaPreciosSoat(int idclase, int idtipo, int idmarca, int idmodelo, int asientos, int zona) {

        return objModelosSoat.ListarPreciosSoat(idclase,idtipo,idmarca,idmodelo,asientos, zona);
    }

}
