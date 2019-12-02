package CapaDatos;

import entidad.TipoZona;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

public class ZonasData {

    ResultSet rs;
    Statement stm;   
    List<TipoZona> listazonas = new ArrayList<TipoZona>();
    private ConexionPool db;
    Connection conex;

    public ZonasData() {
        db = new ConexionPool();
    }

    public List<TipoZona> ListarZonas() {

        if (listazonas.size() > 0) {
            listazonas.removeAll(listazonas);
        }

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_ZONAS }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listazonas.add(
                        new TipoZona(rs.getInt(1),
                        rs.getString(2)));


            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listazonas;


    }

    public void ActualizarZona(String Antiguo, String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_ZONA(?,?) }");
            procedure.setString(1, Antiguo);
            procedure.setString(2, Nuevo);
            procedure.executeQuery();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public void AgregarZonas(String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_ZONA(?) }");
            procedure.setString(1, Nuevo);
            procedure.execute();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


    }

    public void EliminarZonas(String Antiguo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ELIMINAR_ZONA('" + Antiguo + "') }");
            procedure.execute();

            rs = procedure.getResultSet();

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoPagosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


    }

    public List<TipoZona> ListarZonasAcronimo(String TL) {

        List<TipoZona> listazonas007 = new ArrayList<TipoZona>();
        System.out.println("Llegamos");

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DPTOS_POR_ACRONIMO(?) }");
            procedure.setString(1, TL);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listazonas007.add(
                        new TipoZona(
                        rs.getString(1)));


            }
            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        }

        return listazonas007;
    }

    public List<TipoZona> ListarLocacionesPreDefenidas(String TipoLocacion, String Zona) {

        List<TipoZona> lstNewZonas = new ArrayList<TipoZona>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_LOCACIONES_POR_ZONA(?,?) }");

            //System.out.println("call MOSTRAR_LOCACIONES_POR_ZONA('"+TipoLocacion+"','"+Zona+"')");
            procedure.setString(1, TipoLocacion);
            procedure.setString(2, Zona);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lstNewZonas.add(
                        new TipoZona(
                        rs.getString(1)));


            }
            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        }


        return lstNewZonas;

    }

    public List<TipoZona> ListarZonasNueva() {


        List<TipoZona> lstNewZonas = new ArrayList<TipoZona>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_ZONAS_NUEVAS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lstNewZonas.add(
                        new TipoZona(
                        rs.getString(1)));


            }
            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        }


        return lstNewZonas;

    }

    public List<TipoZona> ListarLocacionesNuevas(String Zonax, String TipoLocacionx) {

        List<TipoZona> lstNewZonas = new ArrayList<TipoZona>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_LOCACIONES_NUEVAS(?,?) }");
            procedure.setString(1, Zonax);
            procedure.setString(2, TipoLocacionx);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lstNewZonas.add(
                        new TipoZona(rs.getString(1)));


            }
            rs.close();
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ZonasData :" + ex.toString());
        }

        return lstNewZonas;

    }
}
