package CapaDatos;

import entidad.TipoPrecio;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

public class TipoPrecioData {

    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    Connection conex;
    List<TipoPrecio> listaTipoPrecio = new ArrayList<TipoPrecio>();

    public TipoPrecioData() {
        db = new ConexionPool();
    }

    public List<TipoPrecio> ListarTipoPrecio() {
        TipoPrecio tipoPrecio;
        listaTipoPrecio.removeAll(listaTipoPrecio);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_PRECIO }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                tipoPrecio = new TipoPrecio();
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                tipoPrecio.setDescripcion(rs.getString("DescripcionTipoPrecio"));
                listaTipoPrecio.add(tipoPrecio);

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos ListarTipoPrecio :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaTipoPrecio;


    }

    public void ActualizarTipoPrecio(String Antiguo, String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_TIPO_PRECIO(?,?) }");
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

    public void AgregarTipoPrecio(String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_TIPO_PRECIO(?) }");
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

    public Integer EliminarTipoPrecio(String Antiguo) {

        Integer Resultado = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ELIMINAR_TIPO_PRECIO('" + Antiguo + "') }");
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                Resultado = rs.getInt(1);
            }

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

        return Resultado;

    }
}
