/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import entidad.CategoriaEquipos;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class CategoriaEquipoData {

    ResultSet rs;
    Statement stm;
    List<CategoriaEquipos> listaCategoriaEquipos = new ArrayList<CategoriaEquipos>();
    private ConexionPool db;
    Connection conex;

    public CategoriaEquipoData() {
        db = new ConexionPool();
    }

    public List<CategoriaEquipos> RecuperaCategoriasEquipos() {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CATEGORIA_EQUIPOS() }");           
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaCategoriaEquipos.add(new CategoriaEquipos(rs.getString(1)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ContratosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaCategoriaEquipos;

    }

    public String RecuperaDescripcionPorCodigo(String CodigoCategoria) {
        String DescripcionCategoria = "";

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DESCRIPCION_CATEGORIA_EQUIPO_POR_CODIGO(?) }");
            procedure.setString(1, CodigoCategoria);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                DescripcionCategoria = rs.getString(1);
            }

            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CategoriaEquiposData :" + ex.toString());
        }

        return DescripcionCategoria;

    }

    public String RecuperaDescripcionPadre(String CodigoCategoria) {
        String DescripcionCategoria = "";

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCRIPCION_PADRE(?) }");
            procedure.setString(1, CodigoCategoria);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                DescripcionCategoria = rs.getString(1);
            }

            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CategoriaEquiposData :" + ex.toString());
        }

        return DescripcionCategoria;
    }
}
