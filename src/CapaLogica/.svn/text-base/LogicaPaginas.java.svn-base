/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.PaginasData;
import java.util.List;
import entidad.Paginas;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaPaginas {

    PaginasData objpagina;

    public LogicaPaginas() {
        objpagina = new PaginasData();
    }

    public List<Paginas> ListarPaginas() {

        return objpagina.ListarPaginas();
    }

    public List<Paginas> ListarPaginasDisponibles(String rolSeleccionado) {

        return objpagina.ListarPaginasDisponibles(rolSeleccionado);
    }

    public List<Paginas> ListarPaginasPermitidos(String rolSeleccionado) {

        return objpagina.ListarPaginasPermitidos(rolSeleccionado);
    }

    public void ActualizarPaginasRol(String roles, String listaPaginas, Integer TotalPaginasEnviadas) {
        objpagina = new PaginasData();
        objpagina.ActualizarPaginasRol(roles, listaPaginas, TotalPaginasEnviadas);
    }
}
