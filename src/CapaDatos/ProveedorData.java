/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Proveedor;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S. XD
 */
public class ProveedorData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    //variable contenedora para mis datos
    List<Proveedor> listaproveedores = new ArrayList<Proveedor>();

    public ProveedorData() {
        db = new ConexionPool();
    }

    public List<Proveedor> ListarProveedor() {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PROVEEDORES }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaproveedores.add(new Proveedor(rs.getString("Id_Proveedor"), rs.getString("Descripcion")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ProductoData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaproveedores;
    }

    public List<Proveedor> Find_Provedores(int op, String valor) {

        List<Proveedor> milista = new ArrayList<Proveedor>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FIND_BUSCA_PROVEEDOR (?,?)}");
            procedure.setInt("OP", op);
            procedure.setString("VALOR", valor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Proveedor(rs.getString("Id_Proveedor"),
                        rs.getString("Descripcion")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ProductoData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return milista;
    }

    public List<Proveedor> Find_Personal(int op, String valor) {

        List<Proveedor> milista = new ArrayList<Proveedor>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FIND_BUSCA_PERSONAL (?,?)}");
            procedure.setInt("OP", op);
            procedure.setString("VALOR", valor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Proveedor(rs.getString("Id_Personal"),
                        rs.getString("Apellido")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ProductoData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return milista;
    }
}