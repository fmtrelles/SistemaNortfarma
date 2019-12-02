/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Familias;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class FamiliasData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private ConexionPool db;

    public FamiliasData() {
        db = new ConexionPool();
    }

    public void ActualizaFamilias(List<Familias> listFamilias) {

        try {

            conex = new ConexionPool().getConnection();          
            CallableStatement procedure;

            for (int inicio = 0; inicio < listFamilias.size(); inicio++) {
                procedure = conex.prepareCall("{ call ACTUALIZAR_FAMILIAS(?,?) }");
                procedure.setString(1, listFamilias.get(inicio).getId_Familia());
                procedure.setString(2, listFamilias.get(inicio).getDescripcion());
                procedure.execute();
            }          

        } catch (Exception ex) {           
                System.out.println("Error CapaDatos clase FamiliaDATA :" + ex.getMessage().toString());              
           
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
