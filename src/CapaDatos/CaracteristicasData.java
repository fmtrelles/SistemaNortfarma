/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Caracteristicas;
import entidad.CategoriaEquipos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class CaracteristicasData {

    ResultSet rs;
    Statement stm;
    List<Caracteristicas> listaCaracteristicas = new ArrayList<Caracteristicas>();
    private ConexionPool db;
    Connection conex;

    public CaracteristicasData() {
        db = new ConexionPool();
    }

    public List<Caracteristicas> RecuperaCaracteristicas(String Categoria) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CARACTERISTICAS_POR_CATEGORIA(?) }");
            // System.out.println("call LISTAR_CARACTERISTICAS('"+Categoria+"')");
            procedure.setString(1, Categoria);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaCaracteristicas.add(new Caracteristicas(rs.getString(1)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CaracteristicasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaCaracteristicas;


    }

    public List<Caracteristicas> ListarCaracteristicasTotales() {

        if (listaCaracteristicas.size() > 0) {
            listaCaracteristicas.removeAll(listaCaracteristicas);
        }

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CARACTERISTICAS_EQUIPOS() }");
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaCaracteristicas.add(
                        new Caracteristicas(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));

            }

            rs.close();
            System.out.println("2.listaCaracteristicas:" + listaCaracteristicas.size());
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CaracteristicasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaCaracteristicas;

    }

    public void AgregarCaracteristicaEquipo(String Descripcion, String Categoria) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_CARACTERISTICA(?,?) }");
            procedure.setString(1, Descripcion);
            procedure.setString(2, Categoria);
            procedure.execute();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CaracteristicasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public List<CategoriaEquipos> RecuperaSubCategorias(String CategoriaPadre) throws SQLException {

        List<CategoriaEquipos> lstEquipos = new ArrayList<CategoriaEquipos>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_SUB_CATEGORIAS(?) }");
            procedure.setString(1, CategoriaPadre);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                lstEquipos.add(
                        new CategoriaEquipos(
                        rs.getString(1)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Equipos :" + ex.toString());
        }
        conex.close();
        return lstEquipos;

    }
}
