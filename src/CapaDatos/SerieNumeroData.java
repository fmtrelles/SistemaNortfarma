package CapaDatos;

import entidad.SerieNumero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SerieNumeroData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private List<SerieNumero> listaserieNumero = new ArrayList<SerieNumero>();
    private List<SerieNumero> listaserieNumeroReimprime = new ArrayList<SerieNumero>();
    private ConexionPool db;

    public SerieNumeroData() {
        db = new ConexionPool();
    }

    public List<SerieNumero> retornaSerieNumero(String idbotica, int idcaja, int tipventa) {
        listaserieNumero.removeAll(listaserieNumero);

        try {

            // System.out.println("{ call RETORNA_SERIE_NUMERO('"+idbotica+"','"+idcaja+"','"+tipventa+"')}");
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_SERIE_NUMERO(?,?,?)}");
            procedure.setString(1, idbotica);
            procedure.setInt(2, idcaja);
            procedure.setInt(3, tipventa);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaserieNumero.add(new SerieNumero(rs.getString(1), rs.getString(2)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error en la CAOA DATOS" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaserieNumero;

    }

    public List<SerieNumero> RecuperaSerieNumeroReimprime(String idbotica, int idcaja, int tipventa) {
        listaserieNumeroReimprime.removeAll(listaserieNumeroReimprime);

        try {            
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_SERIE_NUMERO_REIMPRESION(?,?,?)}");
            procedure.setString(1, idbotica);
            procedure.setInt(2, idcaja);
            procedure.setInt(3, tipventa);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaserieNumeroReimprime.add(new SerieNumero(rs.getString(1), rs.getString(2)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error en la CAOA DATOS" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaserieNumeroReimprime;

    }
}
