/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Provincias;
/**
 *
 * @author gparedes
 */
public class ProvinciasData {

    ResultSet rs;
    Statement stm;
    List<Provincias> listaProvincias = new ArrayList<Provincias>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public ProvinciasData() {
        db = new ConexionPool();
    }

    public List<Provincias> ListarProvincias(int idclase) {

        Provincias miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_PROVINCIAS(?) }");
            procedure.setInt("IDDEPARTAMENTO", idclase);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Provincias();
                miClase.setDescripcion(rs.getString("provincia"));
                miClase.setIdAsociado(rs.getInt("idProv"));
                listaProvincias.add(miClase);
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


        return listaProvincias;
    }

}
