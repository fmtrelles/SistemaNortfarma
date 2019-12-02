/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Gomez S.
 */
public class RecuperaIGV {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private double IGV;
    private ConexionPool db;
    private ResultSet rs_pool;

    public RecuperaIGV() {
        db = new ConexionPool();
    }

    public double recupera_Igv_Exonerado(String codpro) {

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_IGV_EXONERADO (?)}");
            procedure.setString(1, codpro);
            rs = procedure.executeQuery();
            conex.commit();
            rs.next();
            IGV = rs.getDouble("IGV_Exonerado");
            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();
                System.out.println("Error en la CAPADATOS CLASE recupera_Igv_Exonerado :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(RecuperaIGV.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return IGV;

    }
    
    
    public double recupera_PrecBotiquin(String codpro, int opc) {

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ESBOTIQUIN_PRODUCTO (?,?)}");
            procedure.setString(1, codpro);
            procedure.setInt(2, opc);
            rs = procedure.executeQuery();
            conex.commit();
            rs.next();
            IGV = rs.getDouble("Precio_Venta");
            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();
                System.out.println("Error en la CAPADATOS CLASE RECUPERA_ESBOTIQUIN_PRODUCTO :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(RecuperaIGV.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return IGV;

    }

    public double RetornaIGV()  {

        try {
            conex = new ConexionPool().getConnection();           
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_IGV }");                    
            rs = procedure.executeQuery();
            rs.next();
            IGV = rs.getDouble("IGV");
            rs.close();

        } catch (SQLException ex) {          
            System.out.println("Error en la CAPADATOS CLASE RECUPERAIGV :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return IGV;

    }
}
