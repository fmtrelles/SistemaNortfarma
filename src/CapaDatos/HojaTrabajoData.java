/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import com.mysql.jdbc.Statement;
import entidad.HojaDeTrabajo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KELVIN
 */
public class HojaTrabajoData {

    private ConexionPool db;
    ResultSet rs;
    Statement stm;
    Connection conex;

    public HojaTrabajoData() {
        db = new ConexionPool();
    }

    public HojaDeTrabajo VerIdHoja() {

        HojaDeTrabajo miHoja = new HojaDeTrabajo();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call recuperaIdHojaTrabajo () }");
            rs = procedure.executeQuery();

            while(rs.next()) {
                miHoja = new HojaDeTrabajo();

                miHoja.setIdHojaTrabajo(rs.getString("idHojaTrabajo"));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos VerBotica: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return miHoja;
    }

    public boolean InsertaHojatrabajo(HojaDeTrabajo hTrabajo) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_HOJA_DE_TRABAJO_INV (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("vIdBotica", hTrabajo.getIdBotica());
            procedure.setString("vHoraInicio", hTrabajo.getHoraInicio());
            procedure.setString("vHoraAcordada", hTrabajo.getHoraProgramada());
            procedure.setInt("vPuntualidad", hTrabajo.getPuntualidad());
            procedure.setInt("vPersonalPresente", hTrabajo.getPersonalPresente());
            procedure.setString("vTipoInventario", hTrabajo.getTipoInv());
            procedure.setInt("vProdEnvioCronograma", hTrabajo.getProdEnvCrono());
            procedure.setInt("vControlProdVencimiento", hTrabajo.getControlProdVenc());
            procedure.setInt("vHayProdVencidosXvencer", hTrabajo.getHayProdXvencer());
            procedure.setInt("vAjustesXInventarios", hTrabajo.getAjusteInv());
            procedure.setString("vInvMesDesarrollaboEnBoti", hTrabajo.getInvMesDesaBoti());
            procedure.setInt("vBoticaLimpia", hTrabajo.getBoticaLimpia());
            procedure.setString("vPisos", hTrabajo.getPisos());
            procedure.setString("vAnaqueles", hTrabajo.getAnaqueles());
            procedure.setString("vEquipos", hTrabajo.getEquipos());
            procedure.setInt("vQuimicoPresente", hTrabajo.getQuimicoPresente());
            procedure.setString("vParticipacionQuimico", hTrabajo.getParticipacionQuimico());
            procedure.setInt("vAjustesObsEnPlazoIndicado", hTrabajo.getAjusteObsEnPlazo());
            procedure.setInt("vReportaProdMalos", hTrabajo.getReportaMalosProd());
            procedure.setInt("vSupervisoraCoordinacion", hTrabajo.getSupervisoraCoordinacion());
            procedure.setString("vSupervisoraParticipacion", hTrabajo.getSupervisoraParticipacion());
            procedure.setString("vOcurrencias", hTrabajo.getOcurrencias());
            procedure.setString("vObsYrecomendaciones", hTrabajo.getObsyReco());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  InsertaHorarioMarcacion :" + ex.toString());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK :" + ex1.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;
    }

}
