/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import static CapaDatos.DatosBuscaCaja.conex;
import entidad.BuscaCaja;
import entidad.Cajas_Fondos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando Muñoz
 */
public class CajasFondos {

    static Connection conex;
    static ResultSet rs;
    static Statement stm;

    public static void insertafondo(Cajas_Fondos cajas) {
        try {
            int var = 2;
            int variable;
            conex = new ConexionPool().getConnection();
            CallableStatement st = null;
            st = conex.prepareCall("CALL SP_INSERTAFONDOS(?,?,?,?,?,?)");
            st.setInt(4, cajas.getIdcaja());
            st.setString(1, cajas.getIdbotica());
            st.setString(2, cajas.getFecha());
            st.setDouble(3, cajas.getMonto());
            st.setInt(5, cajas.getIdpersonal());
            st.registerOutParameter(6, var);
            st.execute();
            variable = st.getInt(6);
            conex.close();
            st.close();
            if (variable == 1) {
                JOptionPane.showMessageDialog(null, "El efectivo se transfirió con éxito ", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El monto no puede ser mayor al saldo inicial", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al transferir efectivo", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error CapaDatos clase INSERTA FONDOS :" + ex.toString());
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
