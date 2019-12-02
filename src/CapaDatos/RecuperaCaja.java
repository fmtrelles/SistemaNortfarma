package CapaDatos;

import entidad.Cajas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecuperaCaja {

    ResultSet rs;
    Connection conex;
    Statement stm;
    private ConexionPool db;
    List<Cajas> listapersonal = new ArrayList<Cajas>();

    public RecuperaCaja() {
        db = new ConexionPool();
    }

    public List<Cajas> ListaCajas(String IDBOTICA) {
        listapersonal.removeAll(listapersonal);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call SELECCIONA_BOTICA_CAJA (?) }");
            procedure.setString(1, IDBOTICA);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listapersonal.add(new Cajas(rs.getInt(1), rs.getString(2)));
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listapersonal;
    }

    public int Devuelve_Caja(String IDBOTICA, int idcajero) {
        int idcaja = -1;

        try {

            //System.out.println("{ call RECUPERA_IDCAJA ('"+IDBOTICA+"','"+idcajero+"') }");
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_IDCAJA (?,?) }");
            procedure.setString(1, IDBOTICA);
            procedure.setInt(2, idcajero);
            rs = procedure.executeQuery();

            while (rs.next()) {
                idcaja = rs.getInt("ID_CAJA");
            }

            System.out.println("idcaja " + idcaja);
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return idcaja;
    }

    public boolean CajaAperturada(String IDBOTICA, int idcaja, int idpersonal) {
        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CAJA_APERTURADA (?,?,?) }");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("IDCAJA", idcaja);
            procedure.setInt("IDPERSONAL", idpersonal);
            rs = procedure.executeQuery();
            rs.next();

            int ap = rs.getInt("Apertura");

            if (ap == 1) {
                resultado = true;
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return resultado;

    }

    public boolean CajaAperturada_Numero(String IDBOTICA, int idcaja, int idtipventa) {
        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call CAJA_APERTURADA_POR_NUMERO (?,?,?) }");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("IDCAJA", idcaja);
            procedure.setInt("TIPVENTA", idtipventa);
            rs = procedure.executeQuery();
            rs.next();

            int ap = rs.getInt("Apertura");

            if (ap == 1) {
                resultado = true;
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return resultado;

    }

    public List<Cajas> VERIFICA_CAJAS_USUARIOS(String IDBOTICA, int idcajero) {

        listapersonal.removeAll(listapersonal);
        Cajas micaja = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CAJAS_USUARIOS (?,?) }");
            procedure.setString(1, IDBOTICA);
            procedure.setInt(2, idcajero);
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setIdcaja(rs.getInt("ID_CAJA"));
                micaja.setEsDelivery(rs.getInt("EsDelivery"));
                micaja.setCajas(rs.getString("Descripcion"));
                micaja.setMarcadoDelivery(rs.getInt("Marcado"));
                listapersonal.add(micaja);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("ERROR VERIFICA_CAJAS_USUARIOS " + ex.getMessage());
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listapersonal;
    }
    
    public List<Cajas> EJECUTAPROMOS() {

        listapersonal.removeAll(listapersonal);
        Cajas micaja = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call activar_promociones () }");
           
            rs = procedure.executeQuery();            

            rs.close();

        } catch (SQLException ex) {
            System.out.println("ERROR activar_promociones " + ex.getMessage());
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listapersonal;
    }

    public List<Cajas> VERIFICA_CAJAS_USUARIOS1(String IDBOTICA, int idcajero) {

        listapersonal.removeAll(listapersonal);
        Cajas micaja = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CAJAS_USUARIOS1 (?,?) }");
            procedure.setString(1, IDBOTICA);
            procedure.setInt(2, idcajero);
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setIdcaja(rs.getInt("ID_CAJA"));
                micaja.setEsDelivery(rs.getInt("EsDelivery"));
                micaja.setCajas(rs.getString("Descripcion"));
                micaja.setMarcadoDelivery(rs.getInt("Marcado"));
                listapersonal.add(micaja);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("ERROR VERIFICA_CAJAS_USUARIOS " + ex.getMessage());
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listapersonal;
    }
}
