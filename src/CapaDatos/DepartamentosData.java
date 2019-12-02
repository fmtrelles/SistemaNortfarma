/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Departamentos;
/**
 *
 * @author gparedes
 */
public class DepartamentosData {

ResultSet rs;
    Statement stm;
    List<Departamentos> listaDepartamentos = new ArrayList<Departamentos>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public DepartamentosData() {
        db = new ConexionPool();
    }

    public List<Departamentos> ListarDepartamentos(String zonadesc) {
        
        Departamentos miClase;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DEPARTAMENTOS(?) }");
            procedure.setString("ZONADESC", zonadesc);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miClase = new Departamentos();
                miClase.setDescripcion(rs.getString("departamento"));
                miClase.setIdAsociado(rs.getInt("idDepa"));
                listaDepartamentos.add(miClase);
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


        return listaDepartamentos;
    }


}
