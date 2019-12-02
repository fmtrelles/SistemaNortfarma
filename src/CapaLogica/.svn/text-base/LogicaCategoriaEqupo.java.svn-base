/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.CategoriaEquipoData;
import entidad.CategoriaEquipos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaCategoriaEqupo {

    List<CategoriaEquipos> listCategoriaEqupos = new ArrayList<CategoriaEquipos>();
    CategoriaEquipoData objCategoriaEquipo = new CategoriaEquipoData();

    public List<CategoriaEquipos> RecuperaCategoriasEquipos() throws SQLException {
        return objCategoriaEquipo.RecuperaCategoriasEquipos();
    }

    public String RecuperaDescripcionPorCodigo(String CodigoCategoria) {
        return objCategoriaEquipo.RecuperaDescripcionPorCodigo(CodigoCategoria);
    }

    public String RecuperaDescripcionPadre(String CodigoCategoria) {
        return objCategoriaEquipo.RecuperaDescripcionPadre(CodigoCategoria);
    }
}
