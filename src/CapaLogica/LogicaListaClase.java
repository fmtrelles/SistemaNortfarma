/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.ClasesData;
import entidad.Clases;
import java.util.List;
/**
 *
 * @author gparedes
 */
public class LogicaListaClase {
    ClasesData objClase = new ClasesData();

    public LogicaListaClase() {
    }
    
    public List<Clases> ListaClases() {
        return objClase.ListarClases();
    }

}
