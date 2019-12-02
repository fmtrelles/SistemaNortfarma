/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.EquiposData;
import entidad.Caracteristicas;
import entidad.Equipos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaEquipos {

    EquiposData objEquipos;

    public LogicaEquipos() {
        objEquipos = new EquiposData();
    }

    public List<Equipos> ListarEquiposCompenentesHuerfanos(String Buscador) throws SQLException {
        return objEquipos.ListarEquiposCompenentesHuerfanos(Buscador);
    }

    public List<Equipos> GuardarCabeceraEquipo(Equipos entidadcabecera) {
        return objEquipos.GuardarCabeceraEquipo(entidadcabecera);
    }

    public void GuardarCaracteristicasEquipo(String codigo_Nuevo, List<Caracteristicas> listCaracteristicas2) throws SQLException {
        objEquipos.GuardarCaracteristicasEquipo(codigo_Nuevo, listCaracteristicas2);
    }

    public boolean ValidaCodigoNortfarma(String CodigoNortfarma) {
        return objEquipos.ValidaCodigoNortfarma(CodigoNortfarma);
    }

    public void GuardarComponentes(List<Equipos> lstComponentes) {
        objEquipos.GuardarComponentes(lstComponentes);
    }

    public List<Equipos> ListarTodosLosEquipos(String Buscador) {
        return objEquipos.ListarTodosLosEquipos(Buscador);
    }

    public List<Equipos> MostrarDatosEquipo(String CodigoEquipo) {
        return objEquipos.MostrarDatosEquipo(CodigoEquipo);
    }

    public List<Equipos> MostrarOtrasLocaciones(String CodigoEquipo) throws SQLException {
        return objEquipos.MostrarOtrasLocaciones(CodigoEquipo);
    }

    public List<Equipos> ListarComponentes(String Codigo) {
        return objEquipos.ListarComponentes(Codigo);
    }

    public List<Equipos> ListarNoComponentes(String Codigo) {
        return objEquipos.ListarNoComponentes(Codigo);
    }

    public List<Equipos> ListarCaracteristicas(String CodigoSeleccionado) {
        return objEquipos.ListarCaracteristicas(CodigoSeleccionado);
    }

    public String RecuperaCodigoNortfarma(String codigo_Nuevo) {
        return objEquipos.RecuperaCodigoNortfarma(codigo_Nuevo);
    }

    public String RecuperaTipoLocacionEquipo(String Locacion) {
        return objEquipos.RecuperaTipoLocacionEquipo(Locacion);
    }

    public void RetiraEquipoGuiaRemision(String MiSerie, String MiNumeracion, String CodigoEquipo) {
        objEquipos.RetiraEquipoGuiaRemision(MiSerie, MiNumeracion, CodigoEquipo);
    }
}
