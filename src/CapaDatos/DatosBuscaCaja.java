/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.BuscaCaja;
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
public class DatosBuscaCaja {
    
    static Connection conex;
    static ResultSet rs;
    static Statement stm;
    static List<BuscaCaja> listaCajas = new ArrayList<BuscaCaja>();    
    
    public static List<BuscaCaja> retornacaja(int id, String botica, String fecha){
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{call SP_BUSCACAJA("+id+",'"+botica+"','"+fecha+"') }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaCajas.add(
                        new BuscaCaja(rs.getInt("Id_Cajas"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase DatosBuscaCaja :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaCajas;
    }
    
}
