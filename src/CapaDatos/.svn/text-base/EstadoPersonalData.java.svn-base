/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Estado_Personal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class EstadoPersonalData {

    ResultSet rs;
    Statement stm;
    Connection conex;
    private ConexionPool db;

    public EstadoPersonalData() {
        db = new ConexionPool();
    }

    public List<Estado_Personal> ListaEstado() {
        List<Estado_Personal> lista = new ArrayList<Estado_Personal>();
        Estado_Personal estado;


        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_ESTADOPERSONAL() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                estado = new Estado_Personal();
                estado.setId_Estado(rs.getInt("Id_Estado"));
                estado.setDescripcion(rs.getString("Descripcion"));
                lista.add(estado);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ListaEstadoPersonal :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return lista;

    }
}
