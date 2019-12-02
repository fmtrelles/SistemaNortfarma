 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Empresas;

/**
 *
 * @author Miguel Gomez S.
 */
public class EmpresasData {

    ResultSet rs;
    Statement stm;
    List<Empresas> listaEmpresas = new ArrayList<Empresas>();
    String rucAsociado;
    Connection conex;
    private ConexionPool db;

    public EmpresasData() {
        db = new ConexionPool();
    }

    public List<Empresas> ListarEmpresas() {

        Empresas miempresa;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_EMPRESAS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                miempresa = new Empresas();
                miempresa.setRazonSocial(rs.getString("Descripcion"));
                miempresa.setIdEmpresa(rs.getInt("Id_Empresa"));
                listaEmpresas.add(miempresa);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaEmpresas;
    }
}
