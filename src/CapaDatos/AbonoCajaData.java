/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Abono_Caja;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Miguel Gomez S.
 */
public class AbonoCajaData {

    Connection conex;
    private ConexionPool db;
    ResultSet rs;
    Statement stm;

    public AbonoCajaData() {
        db = new ConexionPool();
    }

    public int Guardar_Abono(List<Abono_Caja> listaTotal) {
        int reul = 0;
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = null;

            for (int i = 1; i < listaTotal.size(); i++) {          
                procedure = conex.prepareCall("{ call GUARDA_ABONO_CAJA(?,?,?,?,?,?,?,?,?) }");
                procedure.setString("MIBOTICA", listaTotal.get(0).getBotica().getId_Botica());
                procedure.setInt("IDCAJA", listaTotal.get(0).getCaja().getIdcaja());
                procedure.setString("MIFECHA", listaTotal.get(0).getFECHA());
                procedure.setInt("IDCAJERO", listaTotal.get(0).getPersona().getId_personal_Caja());
                procedure.setInt("MITURNO", listaTotal.get(0).getTurno().getId_Turno());
                procedure.setInt("IDBILLETE", listaTotal.get(i).getBiilete().getId_Billete());
                procedure.setInt("MICANTIDAD", listaTotal.get(i).getCantidad());
                procedure.setDouble("MITOTAL", listaTotal.get(i).getTotal());
                procedure.setInt("VALOR",i);
                procedure.execute();
            }

            CallableStatement procedure1 = conex.prepareCall("{ call Ingresa_Abono_Caja (?,?,?,?,?) }");
            procedure1.setString("IDBOTICA", listaTotal.get(0).getBotica().getId_Botica());
            procedure1.setInt("IDCAJA", listaTotal.get(0).getCaja().getIdcaja());
            procedure1.setInt("IDPERSONAL", listaTotal.get(0).getPersona().getId_personal_Caja());
            procedure1.setString("MIFECHA", listaTotal.get(0).getFECHA());
            procedure1.setInt("TURNO", listaTotal.get(0).getTurno().getId_Turno());
            procedure1.execute();

            conex.commit();
            reul = 1;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase AbonoCajaData :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("Error rollback clase AbonoCajaData :" + ex.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return reul;
    }


    public int Guardar_AbonoArqueo(List<Abono_Caja> listaTotal,String rendicion, int idsuper) {
        int reul = 0;
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = null;

            
            for (int i = 1; i < listaTotal.size(); i++) {
                procedure = conex.prepareCall("{ call Guardar_AbonoArqueoCaja(?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setString("MIBOTICA", listaTotal.get(0).getBotica().getId_Botica());
                procedure.setInt("IDCAJA", listaTotal.get(0).getCaja().getIdcaja());
                procedure.setString("MIFECHA", listaTotal.get(0).getFECHA());                
                procedure.setInt("IDCAJERO", listaTotal.get(0).getPersona().getId_personal_Caja());
                procedure.setInt("MITURNO", listaTotal.get(0).getTurno().getId_Turno());
                procedure.setInt("IDBILLETE", listaTotal.get(i).getBiilete().getId_Billete());
                procedure.setInt("MICANTIDAD", listaTotal.get(i).getCantidad());
                procedure.setDouble("MITOTAL", listaTotal.get(i).getTotal());
                procedure.setInt("VALOR",i);
                procedure.setString("RENDICION", rendicion);
                procedure.setInt("IDSUPER",idsuper);

                procedure.execute();
            }
            

            conex.commit();
            reul = 1;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase AbonoCajaData :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("Error rollback clase AbonoCajaData :" + ex.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return reul;
    }

    public int Guardar_EnvioEfecOficina(List<Abono_Caja> listaTotalDatGenerales,List<Abono_Caja> listaTotal,String rendicion, int idgerencia, int usuarioSistema) {
        int reul = 0;
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = null;


            for (int i = 0; i < listaTotal.size(); i++) {
                procedure = conex.prepareCall("{ call Guardar_EnvioEfecOficina(?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setString("MIBOTICA", listaTotalDatGenerales.get(0).getBotica().getId_Botica());
                procedure.setInt("IDCAJA", listaTotalDatGenerales.get(0).getCaja().getIdcaja());
                procedure.setString("MIFECHA", listaTotalDatGenerales.get(0).getFECHA());
                procedure.setInt("IDCAJERO", listaTotalDatGenerales.get(0).getPersona().getId_personal_Caja());
                procedure.setInt("MITURNO", listaTotalDatGenerales.get(0).getTurno().getId_Turno());
                procedure.setInt("IDBILLETE", listaTotal.get(i).getBiilete().getId_Billete());
                procedure.setInt("MICANTIDAD", listaTotal.get(i).getCantidad());
                procedure.setDouble("MITOTAL", listaTotal.get(i).getTotal());
                procedure.setInt("VALOR",i);
                procedure.setString("RENDICION", rendicion);
                procedure.setInt("IDGERENTE",idgerencia);
                procedure.setInt("IDUSUARIOSISTEMA",usuarioSistema);

                procedure.execute();
            }

            conex.commit();
            reul = 1;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase AbonoCajaData :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("Error rollback clase AbonoCajaData :" + ex.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return reul;
    }

}
