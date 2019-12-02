/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Laboratorios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LaboratoriosData {

    ResultSet rs;
    Connection conex;
    Statement stm;
    private ConexionPool db;
    List<Laboratorios> listaLaboratorios = new ArrayList<Laboratorios>();

    public LaboratoriosData() {
        db = new ConexionPool();
    }

    public void ActualizaLabs(Laboratorios objLabs) {

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZA_LABS(?,? ) }");
            procedure.setString(1, objLabs.getId_Lab());
            procedure.setString(2, objLabs.getDescripcion());
            procedure.executeQuery();
            conex.commit();


        } catch (Exception ex) {

            System.out.println("Error CapaDatos clase LaboratoriosDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public String ActualizaLabsBotica(List<Laboratorios> objLabs, String IP, Integer fila) throws SQLException {


        String cadena = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;

            for (Integer inicio = 0; inicio < objLabs.size(); inicio++) {              
                procedure = conex.prepareCall("{ call ACTUALIZA_LABS (?,?) }");
                procedure.setString(1, objLabs.get(inicio).getId_Lab().toString());
                procedure.setString(2, objLabs.get(inicio).getDescripcion().toString());
                procedure.execute();
            }

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Laboratorios A :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return cadena;

    }

    public List<Laboratorios> ActualizaLabsBotica(String Filtro) {


        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call LISTAR_LABS (?) }");
            procedure.setString(1, Filtro);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaLaboratorios.add(
                        new Laboratorios(rs.getString(1),
                        rs.getString(2)));
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase xLaboratoriosDATA :" + ex.getErrorCode());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaLaboratorios;

    }

    public List<Laboratorios> ListaLabs(String nombre) {

        conex = new ConexionPool().getConnection();


        try {

            conex.setAutoCommit(true);
            CallableStatement procedure = null;


            procedure = conex.prepareCall("{ call LISTAR_LABORATORIOS (?) }");
            procedure.setString(1, nombre);
            rs = procedure.executeQuery();

            while (rs.next()) {

                listaLaboratorios.add(
                        new Laboratorios(rs.getString(1),
                        rs.getString(2)));


            }


            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase xLaboratoriosDATA :" + ex.getErrorCode());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaLaboratorios;

    }

    public List<Laboratorios> ListaLabsFiltro(String LabBuscado, String LabInicio) {

        List<Laboratorios> ListLabs = new ArrayList<Laboratorios>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;            
            procedure = conex.prepareCall("{ call LISTAR_LABS_FILTRO (?,?) }");
            procedure.setString(1, LabBuscado);
            procedure.setString(2, LabInicio);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                ListLabs.add(
                        new Laboratorios(rs.getString(1),
                        rs.getString(2)));
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase xLaboratoriosDATA :" + ex.getErrorCode());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return ListLabs;

    }
    
    
    
    
    public List<Laboratorios> ListaLabsFiltroTrastienda(String LabBuscado, String LabInicio) {

        List<Laboratorios> ListLabs = new ArrayList<Laboratorios>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;            
            procedure = conex.prepareCall("{ call LISTAR_LABS_FILTRO_TRASTIENDA (?,?) }");
            procedure.setString(1, LabBuscado);
            procedure.setString(2, LabInicio);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                ListLabs.add(
                        new Laboratorios(rs.getString(1),
                        rs.getString(2)));
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase xLaboratoriosDATA :" + ex.getErrorCode());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return ListLabs;

    }
}
