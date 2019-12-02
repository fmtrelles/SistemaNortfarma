/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaFechaHora;
import entidad.Cajas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class ConfiguracionData {

    List tablas = new ArrayList();
    LogicaFechaHora objFecha = new LogicaFechaHora();
    LogicaBoticas objBotica = new LogicaBoticas();
    String aplicativo = "";
    String separador = "\\";
    String carpetaDefault = "";
    Connection conex;
    ResultSet rs;
    PreparedStatement st;
    PreparedStatement st1;
    List<String> cabeceras = new ArrayList<String>();
    private ConexionPool db;
    public String servidor = "ALGO";

    public ConfiguracionData() {
        db = new ConexionPool();
    }

    public void Tablas_Backup(String idbotica) {

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);            
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_TABLAS_BACKUP (?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.execute();
            conex.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public String recuperaRutaDefaultLinux() throws SQLException {

        String rutaDefault = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RUTA_LINUX () }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                rutaDefault = rs.getString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Cierre_Caja :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return rutaDefault;

    }

    public boolean Cierre_Caja(Cajas obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CIERRA_CAJA (?,?,?,?,?,?,?) }");            
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("IDPERSONAL", obj.getIdpersonal());
            procedure.setString("MIFECHA", obj.getFecha());
            procedure.setDouble("DEPOSITADO", obj.getDepositado());
            procedure.setDouble("DEPOSITADODOLARES", obj.getDepositado_dolares());
            procedure.setInt("TURNO", obj.getTurno());
            procedure.execute();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Cierre_Caja :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK" + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return valor;

    }

    public boolean Ingresa_Abono_Caja(Cajas obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);            
            CallableStatement procedure = conex.prepareCall("{ call Ingresa_Abono_Caja (?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("IDPERSONAL", obj.getIdpersonal());
            procedure.setString("MIFECHA", obj.getFecha());        
            procedure.setInt("TURNO", obj.getTurno());
            procedure.execute();
            conex.commit();
            valor = true;


        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Cierre_Caja :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK" + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return valor;

    }

    public int Verifica_Acceso_Modificacion() {
        int resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ACCESO() }");
            rs = procedure.executeQuery();
            rs.next();
            resultado = rs.getInt("Valor");

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Configura_Impresora :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return resultado;

    }

    public boolean Configura_Impresora(int ubica, String puerto, String nombre) {
        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_CONFIGURACION_IMPRESORA(?,?,?) }");
            procedure.setInt(1, ubica);
            procedure.setString(2, puerto);
            procedure.setString(3, nombre);
            procedure.executeQuery();
            conex.commit();
            resultado = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase Configura_Impresora :" + ex.toString());
            } catch (SQLException ex1) {
                System.out.println("Error HIZE UN ROLLBACK:" + ex.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return resultado;

    }

    public String RecuperaImpresoraActiva() throws SQLException {

        String rutaDefault = "";
        conex = new ConexionPool().getConnection();
        //Primero las tablas por Backup
        CallableStatement procedure = conex.prepareCall("{ call RecuperaImpresoraActiva () }");
        procedure.execute();
        rs = procedure.getResultSet();

        while (rs.next()) {
            rutaDefault = rs.getString(1);
        }

        rs.close();
        conex.close();

        return rutaDefault;

    }

    public int Verifica_Realiza_DBF() {
        int resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_REALIZA_DBF() }");
            rs = procedure.executeQuery();
            rs.next();
            resultado = rs.getInt("Valor");

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Verifica_Realiza_DBF :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return resultado;

    }
}
