/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.ZonasSoat;

/**
 *
 * @author gparedes
 */
public class ZonasSoatData {
ResultSet rs;
    Statement stm;
    List<ZonasSoat> listaZonasSoat = new ArrayList<ZonasSoat>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public ZonasSoatData() {
        db = new ConexionPool();
    }

    public List<ZonasSoat> ListaZonasSoat() {

        ZonasSoat miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_ZONAS_SOAT() }");
            //procedure.setInt("IDMODELO", idclase);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new ZonasSoat();
                miClase.setDescripcion(rs.getString("Descripcion"));
                miClase.setIdAsociado(rs.getInt("Id_Zona"));
                listaZonasSoat.add(miClase);

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


        return listaZonasSoat;
    }
}
