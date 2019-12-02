/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Paginas;

/**
 *
 * @author Miguel Gomez S.
 */
public class PaginasData {

    ResultSet rs;
    Statement stm;
    List<Paginas> listapaginas = new ArrayList<Paginas>();
    private ConexionPool db;
    Connection conex;

    public PaginasData() {
        db = new ConexionPool();
    }

    public List<Paginas> ListarPaginas() {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PAGINAS }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listapaginas.add(
                        new Paginas(rs.getInt("Id_Cliente"), rs.getString("RUC_DNI")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PAGINASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listapaginas;


    }

    public List<Paginas> ListarPaginasDisponibles(String rolSeleccionado) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PAGINAS_DISPONIBLES(?) }");
            procedure.setString(1, rolSeleccionado);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listapaginas.add(
                        new Paginas(rs.getInt("Id_Pagina"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PAGINASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapaginas;


    }

    public List<Paginas> ListarPaginasPermitidos(String rolSeleccionado) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PAGINAS_PERMITIDOS(?) }");
            procedure.setString(1, rolSeleccionado);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listapaginas.add(
                        new Paginas(rs.getInt("Id_Pagina"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PAGINASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapaginas;

    }

    public void ActualizarPaginasRol(String roles, String listaPaginas, Integer TotalPaginasEnviadas) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_PAGINAS_ROL(?, ?, ?) }");
            procedure.setString(1, roles);
            procedure.setString(2, listaPaginas);
            procedure.setInt(3, TotalPaginasEnviadas);
            procedure.executeQuery();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PAGINASDATA :0000" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }
}
