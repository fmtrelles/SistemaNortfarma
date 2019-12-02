/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Caracteristicas;
import entidad.Equipos;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import sistemanortfarma.OpcionesMenu;

/**
 *
 * @author Miguel Gomez S.
 */
public class EquiposData {

    ResultSet rs;
    Connection conex;
    Statement stm;
    private ConexionPool db;
    List<Equipos> listaequipos = new ArrayList<Equipos>();
    public static Integer abierto = 0;

    public static Integer getAbierto() {
        return abierto;
    }

    public static void setAbierto(Integer abierto) {
        EquiposData.abierto = abierto;
    }

    public EquiposData() {
        db = new ConexionPool();
    }

    public List<Equipos> ListarEquiposCompenentesHuerfanos(String Buscador) {
        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_COMPONENTES_LIBRES(?) }");
            procedure.setString(1, "%" + Buscador + "%");
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2)));
            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ContratosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return listaEquipos;
    }

    public List<Equipos> GuardarCabeceraEquipo(Equipos entidadcabecera) {

        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_EQUIPO(?,?,?,?) }");

            procedure.setString(1, entidadcabecera.getDescripcion());
            procedure.setString(2, entidadcabecera.getDescripcionCategoria());
            procedure.setString(3, entidadcabecera.getLocacion());
            procedure.setString(4, entidadcabecera.getObservaciones());
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1)));
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }


        return listaEquipos;

    }

    public void GuardarCaracteristicas(String codigo_Nuevo, List<Caracteristicas> listCaracteristicas2) throws SQLException {

        try {

            conex = new ConexionPool().getConnection();

            for (Integer inicio = 0; inicio < listCaracteristicas2.size(); inicio++) {
                CallableStatement procedure = conex.prepareCall("{ call AGREGAR_CARACTERISTICA(?,?) }");
                procedure.setString(1, listCaracteristicas2.get(inicio).getDescripcion());
                procedure.setString(2, listCaracteristicas2.get(inicio).getValor());
                procedure.execute();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean ValidaCodigoNortfarma(String CodigoNortfarma) {
        Boolean condicion = false;


        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call VALIDAR_CODIGO_NORTFARMA(?) }");
            procedure.setString(1, CodigoNortfarma);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                condicion = rs.getBoolean(1);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return condicion;

    }

    public void GuardarComponentes(List<Equipos> lstComponentes) {

        try {

            conex = new ConexionPool().getConnection();

            for (Integer inicio = 0; inicio < lstComponentes.size(); inicio++) {

                CallableStatement procedure = conex.prepareCall("{ call AGREGAR_COMPONENTES(?,?,?) }");
                System.out.println("call AGREGAR_COMPONENTES('" + lstComponentes.get(inicio).getDescripcion() + "','" + lstComponentes.get(inicio).getCodigo_Nortfarma() + "','" + OpcionesMenu.getIdpersonal_botica() + "') ");
                procedure.setString(1, lstComponentes.get(inicio).getDescripcion());
                procedure.setString(2, lstComponentes.get(inicio).getCodigo_Nortfarma());
                procedure.setInt(3, OpcionesMenu.getIdpersonal_botica());
                procedure.execute();

            }

            conex.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Equipos> ListarTodosLosEquipos(String Buscador) {


        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        if (getAbierto() < 1) {
            conex = new ConexionPool().getConnection();
        }

        abierto = abierto + 1;


        try {

            CallableStatement procedure = conex.prepareCall("{ call LISTAR_EQUIPOS_ACTIVOS(?) }");
            System.out.println("call LISTAR_EQUIPOS_ACTIVOS('" + Buscador + "') ");
            procedure.setString(1, "%" + Buscador + "%");
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return listaEquipos;

    }

    public List<Equipos> MostrarDatosEquipo(String CodigoEquipo) {

        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DATOS_EQUIPO(?) }");
            // System.out.println("call MOSTRAR_DATOS_EQUIPO('"+CodigoEquipo+"') ");
            procedure.setString(1, CodigoEquipo);

            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }

            conex.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return listaEquipos;
    }

    public List<Equipos> MostrarOtrasLocaciones(String CodigoEquipo) throws SQLException {
        List<Equipos> listaEquipos = new ArrayList<Equipos>();

        conex = new ConexionPool().getConnection();
        CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_OTRAS_LOCACIONES(?) }");
        procedure.setString(1, CodigoEquipo);
        procedure.execute();

        rs = procedure.getResultSet();

        while (rs.next()) {
            listaEquipos.add(
                    new Equipos(
                    rs.getString(1)));
        }
        conex.close();
        return listaEquipos;

    }

    public List<Equipos> ListarComponentes(String Codigo) {

        List<Equipos> listaEquipos = new ArrayList<Equipos>();


        try {
            //conex= new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call LISTAR_COMPONENTES(?) }");

            procedure.setString(1, Codigo);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return listaEquipos;

    }

    public List<Equipos> ListarNoComponentes(String Codigo) {

        List<Equipos> listaEquipos = new ArrayList<Equipos>();


        try {
            //  conex= new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call LISTAR_NO_COMPONENTES(?) }");

            procedure.setString(1, Codigo);

            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {

                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return listaEquipos;
    }

    public List<Equipos> ListarCaracteristicas(String CodigoSeleccionado) {

        List<Equipos> listaEquipos = new ArrayList<Equipos>();


        try {
            //conex= new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CARACTERISTICAS(?) }");

            procedure.setString(1, CodigoSeleccionado);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                listaEquipos.add(
                        new Equipos(
                        rs.getString(1),
                        rs.getString(2)));
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return listaEquipos;

    }

    public String RecuperaCodigoNortfarma(String codigo_Nuevo) {

        String xiao = "";

        try {
            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_CODIGO_NORTFARMA(?) }");
            System.out.println("call RECUPERA_CODIGO_NORTFARMA('" + codigo_Nuevo + "')");
            procedure.setString(1, codigo_Nuevo);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                xiao = rs.getString(1);
            }
            conex.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return xiao;

    }

    public String RecuperaTipoLocacionEquipo(String Locacion) {

        String xiao = "";

        try {
            //conex= new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_TIPO_LOCACION_POR_LOCACION(?) }");
            System.out.println("call RECUPERA_TIPO_LOCACION_POR_LOCACION('" + Locacion + "')");
            procedure.setString(1, Locacion);
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                xiao = rs.getString(1);
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

        return xiao;

    }

    public void RetiraEquipoGuiaRemision(String MiSerie, String MiNumeracion, String CodigoEquipo) {

        try {
            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call RETIRAR_EQUIPO_GUIA_REMISION(?,?,?) }");
            System.out.println("call RETIRAR_EQUIPO_GUIA_REMISION('" + MiSerie + "','" + MiNumeracion + "','" + CodigoEquipo + "')");
            procedure.setString(1, MiSerie);
            procedure.setString(2, MiNumeracion);
            procedure.setString(3, CodigoEquipo);
            procedure.execute();

            conex.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }

    }

    public void GuardarCaracteristicasEquipo(String codigo_Nuevo, List<Caracteristicas> listCaracteristicas2) {

        try {

            conex = new ConexionPool().getConnection();

            for (Integer i = 0; i < listCaracteristicas2.size(); i++) {

                CallableStatement procedure = conex.prepareCall("{ call AGREGAR_CARACTERISTICA_EQUIPO(?,?,?) }");
                System.out.println("AGREGAR_CARACTERISTICA_EQUIPO('" + listCaracteristicas2.get(i).getDescripcion() + "','" + listCaracteristicas2.get(i).getValor() + "','" + listCaracteristicas2.get(i).getCategoria() + "')");
                procedure.setString(1, listCaracteristicas2.get(i).getCategoria());
                procedure.setString(2, listCaracteristicas2.get(i).getDescripcion());
                procedure.setString(3, listCaracteristicas2.get(i).getValor());
                procedure.execute();

            }


            conex.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase EquiposData :" + ex.toString());
        }


    }
}
