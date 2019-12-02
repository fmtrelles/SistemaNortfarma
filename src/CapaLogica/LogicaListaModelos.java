/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.ModelosData;
import entidad.Modelos;
import java.util.List;
/**
 *
 * @author gparedes
 */
public class LogicaListaModelos {
     ModelosData objModelos = new ModelosData();

    public LogicaListaModelos() {
    }

    public List<Modelos> ListaModelos(int idclase) {
    
        return objModelos.ListarModelos(idclase);        
    }

}
