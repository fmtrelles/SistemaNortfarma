/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class MedicoData {

    ResultSet rs;
    Connection conex;
    Statement stm;
    List<Medico> listapersonal = new ArrayList<Medico>();
    private ConexionPool db;

    public MedicoData() {
        db = new ConexionPool();
    }

    public List<Medico> BuscaMedicos(String filtro, int opdebusqueda) {
        try {

            conex = new ConexionPool().getConnection();
          //  System.out.println("{ call BUSCA_MEDICOS ('"+filtro+"','"+opdebusqueda+"') }");
            CallableStatement procedure = conex.prepareCall("{ call BUSCA_MEDICOS (?,?) }");
            procedure.setString(1, filtro);
            procedure.setInt(2, opdebusqueda);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listapersonal.add(new Medico(rs.getString(1),
                        rs.getString(2), rs.getString("Colegio")));

            }

            rs.close();

        } catch (SQLException ex) {
            return null;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal;

    }

    public List<Medico> listaMedicos() {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_MEDICOS() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listapersonal.add(new Medico(rs.getString("Id_Medico"),
                        rs.getString("Nombre"), rs.getString("Colegio")));
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase MEDICODATA :" + ex.toString());
        } catch (Exception e) {
            return null;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal;
    }
}
