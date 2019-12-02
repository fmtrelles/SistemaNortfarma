/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import entidad.DescuentosInv;
import entidad.DescuentosInvActual;
import entidad.DescuentosInvActualDetalle;
import entidad.DescuentosInvDetalle;
import entidad.DescuentosInvTipo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DescuentosInvData {

    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    Connection conex;
    DescuentosInvDetalle objDescInv;

    public DescuentosInvData() {
        db = new ConexionPool();
    }

    public List<DescuentosInvActualDetalle> ListaDescuentosInv() {

        List<DescuentosInvActualDetalle> milistaDesc = new ArrayList<DescuentosInvActualDetalle>();

        DescuentosInvActualDetalle  cDesc;
        DescuentosInvTipo           descTipo;
        DescuentosInvActual         descInve;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call sp_ListaDescuentosAnioActual() }");
            //procedure.setString("vNombreCruce", nombreCruce);
            rs = procedure.executeQuery();

            while(rs.next()) {
                cDesc       = new DescuentosInvActualDetalle();
                descTipo    = new DescuentosInvTipo();
                descInve    = new DescuentosInvActual();

                descInve.setAnio(rs.getString("Anio"));
                descInve.setMonto(rs.getDouble("total"));
                cDesc.setDescuentoMesActual(descInve);
                descTipo.setNombreDescuento(rs.getString("Descuento"));
                cDesc.setDescuentoTipo(descTipo);
                cDesc.setMontoEnero(rs.getDouble("MontoEnero"));
                cDesc.setMontoFebrero(rs.getDouble("MontoFebrero"));
                cDesc.setMontoMarzo(rs.getDouble("MontoMarzo"));
                cDesc.setMontoAbril(rs.getDouble("MontoAbril"));
                cDesc.setMontoMayo(rs.getDouble("MontoMayo"));
                cDesc.setMontoJunio(rs.getDouble("MontoJunio"));
                cDesc.setMontoJulio(rs.getDouble("MontoJulio"));
                cDesc.setMontoAgosto(rs.getDouble("MontoAgosto"));
                cDesc.setMontoSetiembre(rs.getDouble("MontoSetiembre"));
                cDesc.setMontoOctubre(rs.getDouble("MontoOctubre"));
                cDesc.setMontoNoviembre(rs.getDouble("MontoNoviembre"));
                cDesc.setMontoDiciembre(rs.getDouble("MontoDiciembre"));

                milistaDesc.add(cDesc);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos listaDescuentosInv: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milistaDesc;


    }

    public boolean ActualizaDescuentoActual(DescuentosInvActualDetalle descActual) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_ActualizaDescuentoActual (?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("tDesc", descActual.getDescuentoTipo().getNombreDescuento());
            procedure.setDouble("mEne", descActual.getMontoEnero());
            procedure.setDouble("mFeb", descActual.getMontoFebrero());
            procedure.setDouble("mMar", descActual.getMontoMarzo());
            procedure.setDouble("mAbr", descActual.getMontoAbril());
            procedure.setDouble("mMay", descActual.getMontoMayo());
            procedure.setDouble("mJun", descActual.getMontoJunio());
            procedure.setDouble("mJul", descActual.getMontoJulio());
            procedure.setDouble("mAgo", descActual.getMontoAgosto());
            procedure.setDouble("mSet", descActual.getMontoSetiembre());
            procedure.setDouble("mOct", descActual.getMontoOctubre());
            procedure.setDouble("mNov", descActual.getMontoNoviembre());
            procedure.setDouble("mDic", descActual.getMontoDiciembre());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos Actualiza Descuento Actual:" + ex.toString());
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

    public boolean ActualizaDescuentoBase(int varOpc,String varAnio,double varTotal,double var1,double var2,
            double var3, double var4,double var5,double var6,double var7,double var8,double var9,double var10) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_ActualizaBaseDescuentos(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setInt("vOpcion",varOpc);
            procedure.setString("vAnio",varAnio);
            procedure.setDouble("vTotal",varTotal);
            procedure.setDouble("VM1",var1);
            procedure.setDouble("VM2",var2);
            procedure.setDouble("VM3",var3);
            procedure.setDouble("VM4",var4);
            procedure.setDouble("VM5",var5);
            procedure.setDouble("VM6",var6);
            procedure.setDouble("VM7",var7);
            procedure.setDouble("VM8",var8);
            procedure.setDouble("VM9",var9);
            procedure.setDouble("VM10",var10);
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos Actualiza Descuento Base:" + ex.toString());
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
