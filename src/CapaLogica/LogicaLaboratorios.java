/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import entidad.Laboratorios;
import java.sql.SQLException;
import CapaDatos.LaboratoriosData;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaLaboratorios {

    LaboratoriosData objlabs;

    public LogicaLaboratorios() {
        objlabs = new LaboratoriosData();
    }

    public void ActualizaLabs(Laboratorios objLabs) throws SQLException {
        objlabs.ActualizaLabs(objLabs);
    }

    public String ActualizaLabsBotic(List<Laboratorios> objLabs, String IP, Integer fila) throws SQLException {
        return objlabs.ActualizaLabsBotica(objLabs, IP, fila);
    }

    public List<Laboratorios> ListaLabs1(String nombre) {
        return objlabs.ListaLabs(nombre);
    }

    public List<Laboratorios> ListaLabs(String filtro) {
        return objlabs.ActualizaLabsBotica(filtro);
    }
}
