/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Distritos;
/**
 *
 * @author gparedes
 */
public class DistritosData {

    ResultSet rs;
    Statement stm;
    List<Distritos> listaDistritos = new ArrayList<Distritos>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public DistritosData() {
        db = new ConexionPool();
    }

    public List<Distritos> ListarDistritos(int idclase) {

        Distritos miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DISTRITOS(?) }");
            procedure.setInt("IDPROVINCIA", idclase);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Distritos();
                miClase.setDescripcion(rs.getString("distrito"));
                miClase.setIdAsociado(rs.getInt("idDist"));
                listaDistritos.add(miClase);
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


        return listaDistritos;
    }


}
