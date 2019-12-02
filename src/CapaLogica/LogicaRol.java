/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.RolesData;
import entidad.Roles;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaRol {

    RolesData objroles = new RolesData();

    public LogicaRol() {
    }

    public List<Roles> ListaRoles() {
        return objroles.ListarRoles();
    }

    public List<Roles> ListarRolesPersonal() {
        return objroles.ListarRolesPersonal();
    }
}
