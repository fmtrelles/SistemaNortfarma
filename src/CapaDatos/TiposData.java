/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Tipos;

/**
 *
 * @author gparedes
 */
public class TiposData {

    ResultSet rs;
    Statement stm;
    List<Tipos> listaTipos = new ArrayList<Tipos>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public TiposData() {
        db = new ConexionPool();
    }

    public List<Tipos> ListarTipos(int idclase) {
        
        Tipos miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_TIPOS_VEHICULOS(?) }");
            procedure.setInt("IDCLASE", idclase);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Tipos();
                miClase.setDescripcion(rs.getString("Descripcion"));
                miClase.setIdAsociado(rs.getInt("Id_Tipo"));
                listaTipos.add(miClase);
                
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


        return listaTipos;
    }


}
