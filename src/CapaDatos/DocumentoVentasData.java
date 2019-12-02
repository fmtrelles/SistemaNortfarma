/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaLogica.LogicaFechaHora;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.DocumentoVentas;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class DocumentoVentasData {

    LogicaFechaHora objFH = new LogicaFechaHora();
    ResultSet rs;
    Statement stm;
    List<DocumentoVentas> listaclientes = new ArrayList<DocumentoVentas>();
    private ConexionPool db;
    Connection conex;

    public DocumentoVentasData() {
        db = new ConexionPool();
    }

    public List<DocumentoVentas> SerieInicio(String Botica, int Caja, String TipoVenta) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BOTICAS_APERTURA(?,?,? ) }");
            procedure.setString(1, Botica);
            procedure.setInt(2, Caja);
            procedure.setString(3, TipoVenta);            
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new DocumentoVentas(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase DOCUMENTOVENTASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaclientes;

    }

    public boolean validaExisteInicio(String Cajita, String TipoVenta, Date feha) {
        Integer total = 0;


        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VALIDA_EXISTE_INICIO(?,?,? ) }");
            procedure.setString(1, Cajita);
            procedure.setString(2, Cajita);
            procedure.setString(3, objFH.ConvierteFecha(feha));
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                total = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase DOCUMENTOVENTASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }

    
}
