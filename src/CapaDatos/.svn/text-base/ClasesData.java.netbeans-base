/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;


import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Clases;

/**
 *
 * @author gparedes
 */
public class ClasesData {

    ResultSet rs;
    Statement stm;
    List<Clases> listaClases = new ArrayList<Clases>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public ClasesData() {
        db = new ConexionPool();
    }

    public List<Clases> ListarClases() {

        Clases miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CLASES_VEHICULOS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Clases();
                miClase.setDescripcion(rs.getString("Descripcion"));
                miClase.setIdAsociado(rs.getInt("Id_Clase"));
                listaClases.add(miClase);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLASESDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaClases;
    }

}
