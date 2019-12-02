/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.PreciosSoat;

/**
 *
 * @author gparedes
 */
public class PreciosSoatData {
    ResultSet rs;
    Statement stm;
    List<PreciosSoat> listaPreciosSoat = new ArrayList<PreciosSoat>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public PreciosSoatData() {
        db = new ConexionPool();
    }

    public List<PreciosSoat> ListarPreciosSoat(int idclase, int idtipo, int idmarca, int idmodelo, int asientos, int zona) {

        PreciosSoat miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_PRECIO_SOAT(?,?,?,?,?,?) }");
            procedure.setInt("IDCLASE", idclase);
            procedure.setInt("IDTIPO", idtipo);
            procedure.setInt("IDMARCA", idmarca);
            procedure.setInt("IDMODELO", idmodelo);
            procedure.setInt("ASIENTOS", asientos);
            procedure.setInt("ZONA", zona);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new PreciosSoat();
                if (rs.getDouble("precio") > 0) {                
                    miClase.setPrecio(rs.getDouble("precio"));
                    miClase.setcomision(rs.getString("comision"));
                    listaPreciosSoat.add(miClase);

                }else{
                    miClase.setPrecio(rs.getDouble("precio"));
                    miClase.setcomision(rs.getString("comision"));
                    listaPreciosSoat.add(miClase);
                }
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


        return listaPreciosSoat;
    }
}
