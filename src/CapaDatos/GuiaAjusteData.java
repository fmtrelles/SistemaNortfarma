/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import entidad.GuiaAjustes;
import entidad.GuiaAjustesDetalle;
import java.sql.*;

/**
 *
 * @author root
 */
public class GuiaAjusteData {

    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    Connection conex;

    public GuiaAjusteData() {
        db = new ConexionPool();
    }


    public boolean InsertarGuiaAjuste(GuiaAjustes guia) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaGuiaAjuste (?,?,?,?) }");
            procedure.setString("vFecha"    ,(String) guia.getFecha());
            procedure.setString("vSerie"    ,(String) guia.getSerie());
            procedure.setString("vNumero"   ,(String) guia.getNumero());
            procedure.setString("vobs"      ,(String) guia.getObs());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar Guia: " + ex.toString());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK :" + ex1.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {

                }
            }
        }

        return resul;
    }


    public boolean InsertarGuiaDetalle(GuiaAjustesDetalle guiaDetalle, String s, String n) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaGuiaAjusteDet (?,?,?,?) }");

            procedure.setString ("vTipoMov" , guiaDetalle.getTipoMov());
            procedure.setString ("vNumMov"  , guiaDetalle.getNumMov());
            procedure.setString ("vSerie"  , s);
            procedure.setString ("vNumero"  , n);
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar GuiaDetalle: " + ex.toString());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK :" + ex1.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {

                }
            }
        }

        return resul;
    }

}
