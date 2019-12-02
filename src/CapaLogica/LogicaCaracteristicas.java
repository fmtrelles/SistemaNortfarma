/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.CaracteristicasData;
import entidad.Caracteristicas;
import entidad.CategoriaEquipos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaCaracteristicas {

    List<Caracteristicas> listCaracteristicas = new ArrayList<Caracteristicas>();
    CaracteristicasData objCaracteristicas = new CaracteristicasData();

    public List<Caracteristicas> RecuperaCaracteristicas(String Categoria) {
        return objCaracteristicas.RecuperaCaracteristicas(Categoria);
    }

    public List<Caracteristicas> ListarCaracteristicasTotales() {
        return objCaracteristicas.ListarCaracteristicasTotales();
    }

    public void AgregarCaracteristicaEquipo(String Descripcion, String Categoria) {
        objCaracteristicas.AgregarCaracteristicaEquipo(Descripcion, Categoria);
    }

    public List<CategoriaEquipos> RecuperaSubCategorias(String CategoriaPadre) throws SQLException {
        return objCaracteristicas.RecuperaSubCategorias(CategoriaPadre);
    }
}
