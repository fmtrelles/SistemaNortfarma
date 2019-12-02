/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;
import CapaDatos.DistritosData;
import entidad.Distritos;
import java.util.List;
/**
 *
 * @author gparedes
 */
public class LogicaListaDistritos {

    DistritosData objDistritos = new DistritosData();

    public LogicaListaDistritos() {
    }

    public List<Distritos> ListaDistritos(int idclase) {

        return objDistritos.ListarDistritos(idclase);
    }

}
