/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.MarcasData;
import entidad.Marcas;
import java.util.List;
/**
 *
 * @author gparedes
 */
public class LogicaListaMarcas {

    MarcasData objMarcas = new MarcasData();

    public LogicaListaMarcas() {
    }

    public List<Marcas> ListaMarcas() {
        return objMarcas.ListarMarcas();
    }

}
