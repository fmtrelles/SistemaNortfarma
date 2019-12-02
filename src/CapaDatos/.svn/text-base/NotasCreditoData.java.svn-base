/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.NotasCredito;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Miguel Gomez S.
 */
public class NotasCreditoData {

    private ConexionPool db;
    Connection conex;
    List<NotasCredito> Lista_Notas_Credito = new ArrayList<NotasCredito>();
    ResultSet rs;
    Statement stm;

    public NotasCreditoData() {
        db = new ConexionPool();
    }

    public List<NotasCredito> Lista_Notas_Credito(String idbotica, String fecha1, String fecha2) {

        try {
            conex = new ConexionPool().getConnection();
            //System.out.println("{ call LISTA_NOTAS_CREDITO('"+idbotica+"','"+fecha1+"','"+fecha2+"') }");
            CallableStatement procedure = conex.prepareCall("{ call LISTA_NOTAS_CREDITO(?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("fecha1", fecha1);
            procedure.setString("fecha2", fecha2);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Notas_Credito.add(new NotasCredito(rs.getString("Numero_Documento"),
                        rs.getDate("Fecha_Documento"), rs.getDouble("Total"), rs.getTime("Hora"),
                        rs.getString("Serie"), rs.getString("Numero"), rs.getString("Observaciones"),
                        rs.getString("responsable"), rs.getString("INTERNO"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getString("RUC_DNI"), rs.getString("DNI")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase NOTASDECREDITO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Notas_Credito;


    }

    public List<NotasCredito> Lista_Notas(String idbotica, String doc) {

        try {
            conex = new ConexionPool().getConnection();
            Lista_Notas_Credito.removeAll(Lista_Notas_Credito);
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_NOTA_CREDITOS(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("DOCUMENTO", doc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Notas_Credito.add(new NotasCredito(rs.getString("Numero_Documento"),
                        rs.getDouble("Total"), rs.getString("Serie"), rs.getString("Numero"),
                        rs.getString("NOMCLIENTE"), rs.getString("Id_Venta")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase NOTAS DE CREDITO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Notas_Credito;


    }

    public List<NotasCredito> Lista_Notas_Detalle(String idbotica, String doc) {
        List<NotasCredito> milista = new ArrayList<NotasCredito>();

        try {

            conex = new ConexionPool().getConnection();
            Lista_Notas_Credito.removeAll(Lista_Notas_Credito);
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_NOTA_CREDITO_DETALLE(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("NUMERO", doc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new NotasCredito(rs.getString("Descripcion"),
                        rs.getInt("unidad"), rs.getInt("fraccion"), rs.getDouble("Precio_Venta")));
            }


            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Notas_Detalle :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return milista;


    }
}
