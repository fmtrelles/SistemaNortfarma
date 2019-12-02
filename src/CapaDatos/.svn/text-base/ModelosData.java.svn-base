/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Modelos;

/**
 *
 * @author gparedes
 */
public class ModelosData {

    ResultSet rs;
    Statement stm;
    List<Modelos> listaModelos = new ArrayList<Modelos>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public ModelosData() {
        db = new ConexionPool();
    }

    public List<Modelos> ListarModelos(int idclase) {    

        Modelos miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MODELOS_VEHICULOS(?) }");
            procedure.setInt("IDMODELO", idclase);            
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Modelos();
                miClase.setDescripcion(rs.getString("Descripcion"));
                miClase.setIdAsociado(rs.getInt("Id_Modelo"));
                listaModelos.add(miClase);

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


        return listaModelos;
    }

}
