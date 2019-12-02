/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Genericos;
import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Guias;
import entidad.Venta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Gomez S.
 */
public class GenericosData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    List<Guias> listaGuias = new ArrayList<Guias>();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    List<Venta> listVentas40 = new ArrayList<Venta>();
    LogicaVenta objVenta = new LogicaVenta();
    private ConexionPool db;

    public GenericosData() {
        db = new ConexionPool();
    }

    public void ActualizaGenericos(List<Genericos> listGenericos) {

        try {

            CallableStatement procedure = null;
            conex = new ConexionPool().getConnection();
            
            for (int inicio = 0; inicio < listGenericos.size(); inicio++) {
                procedure = conex.prepareCall("{ call ACTUALIZAR_GENERICOS(?,?) }");
                procedure.setString(1, listGenericos.get(inicio).getId_Generico());
                procedure.setString(2, listGenericos.get(inicio).getDescripcionGenerico());
                procedure.execute();
            }

        } catch (Exception ex) {           
                System.out.println("Error CapaDatos clase GenericosData 04:" + ex.getMessage().toString());              
           
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
    }
}
