/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Contratos;

/**
 *
 * @author Miguel Gomez S.
 */
public class ContratosData {

    ResultSet rs;
    Statement stm;
    List<Contratos> listaContratos = new ArrayList<Contratos>();
    private ConexionPool db;
    Connection conex;
    private List<Contratos> devolver;

    public ContratosData() {
        db = new ConexionPool();
    }

    public List<Contratos> ListarContratos() {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MODELOS_CONTRATOS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaContratos.add(new Contratos(rs.getString(1), rs.getString(2)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ContratosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaContratos;


    }
}
