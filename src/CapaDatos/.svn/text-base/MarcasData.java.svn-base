/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Marcas;

/**
 *
 * @author gparedes
 */
public class MarcasData {

    ResultSet rs;
    Statement stm;
    List<Marcas> listaMarcas = new ArrayList<Marcas>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public MarcasData() {
        db = new ConexionPool();
    }

    public List<Marcas> ListarMarcas() {

        Marcas miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MARCAS_VEHICULOS() }");            
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Marcas();
                miClase.setDescripcion(rs.getString("Descripcion"));
                miClase.setIdAsociado(rs.getInt("Id_Marca"));
                listaMarcas.add(miClase);

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


        return listaMarcas;
    }


}
