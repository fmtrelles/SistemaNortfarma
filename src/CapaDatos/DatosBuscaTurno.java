/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.BuscaTurno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Muñoz
 */
public class DatosBuscaTurno {

    static Connection conex;
    static ResultSet rs;
    static Statement stm;
    static List<BuscaTurno> lista = new ArrayList<BuscaTurno>();

    public static List<BuscaTurno> retornaturno(int id, String botica, String fecha) {
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{call SP_BUSCATURNO(" + id + ",'" + botica + "','" + fecha + "') }");
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                lista.add(
                        new BuscaTurno(rs.getInt("Id_Turno"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase DatosBuscaTurno :" + ex.toString());
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
