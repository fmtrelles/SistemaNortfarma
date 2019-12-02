/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Roles;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class RolesData {

    ResultSet rs;
    Statement stm;
    Connection conex;
    private ConexionPool db;
    private ResultSet rs_pool;
    //variable contenedora para mis datos
    List<Roles> listaroles = new ArrayList<Roles>();

    public RolesData() {
        db = new ConexionPool();
    }

    public List<Roles> ListarRoles() {
        Roles roles;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_ROLES }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                roles = new Roles();
                roles.setId_Rol(rs.getInt("Id_Rol"));
                roles.setDescripcion(rs.getString("Descripcion"));
                listaroles.add(roles);
            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase RolesData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaroles;


    }

    public List<Roles> ListarRolesPersonal() {
        Roles roles;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_ROLESPERSONAL() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                roles = new Roles();
                roles.setId_Rol(rs.getInt("Id_Rol"));
                roles.setDescripcion(rs.getString("Descripcion"));
                listaroles.add(roles);
            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase RolesData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaroles;

    }
}
