/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Abono_Caja;
import entidad.Billetes_Monedas;
import entidad.Cajas;
import entidad.Tipo_Moneda;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;
import entidad.Venta;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class BilletesMonedaData {

    Connection conex;
    private ConexionPool db;
    ResultSet rs;
    Statement stm;

    public BilletesMonedaData() {
        db = new ConexionPool();
    }

    public List<Abono_Caja> ListaBilletes(Cajas caja) {

        List<Abono_Caja> lista = new ArrayList<Abono_Caja>();
        Billetes_Monedas billete;
        Tipo_Moneda tipomoneda;
        Abono_Caja abonoCaja;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_BILLETES(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", caja.getIdbotica());
            procedure.setInt("IDCAJA", caja.getIdcaja());
            procedure.setInt("IDPERSONAL", caja.getIdpersonal());
            procedure.setString("MIFECHA", caja.getFecha());
            procedure.setInt("TURNO", caja.getTurno());
            rs = procedure.executeQuery();

            while (rs.next()) {
                billete = new Billetes_Monedas();
                tipomoneda = new Tipo_Moneda();
                abonoCaja = new Abono_Caja();
                tipomoneda.setTipo_Moneda(rs.getInt("Tipo_Moneda"));
                tipomoneda.setDescripcion(rs.getString("Descripcion"));
                tipomoneda.setSimbolo(rs.getString("Simbolo"));
                abonoCaja.setCantidad(rs.getInt("Cantidad"));
                abonoCaja.setTotal(rs.getDouble("Total"));
                billete.setTipomoneda(tipomoneda);
                billete.setId_Billete(rs.getInt("Id_Billete"));
                billete.setValor(rs.getDouble("Valor"));
                billete.setRuta(rs.getString("Ruta"));
                billete.setId_Billete(rs.getInt("Id_Billete"));
                abonoCaja.setBiilete(billete);
                lista.add(abonoCaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BilletesMonedaData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return lista;

    }


    public List<Abono_Caja> ListaBilletesArqueo(Cajas caja, Date fecha) {

        List<Abono_Caja> lista = new ArrayList<Abono_Caja>();
        Billetes_Monedas billete;
        Tipo_Moneda tipomoneda;
        Abono_Caja abonoCaja;

        Date fechaVta = fecha;
        SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
        String FechaVtaS = formatoVta.format(fechaVta);

        int turnoS = 1;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_BILLETES(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", caja.getIdbotica());
            procedure.setInt("IDCAJA", caja.getIdcaja());
            procedure.setInt("IDPERSONAL", caja.getIdpersonal());
            procedure.setString("MIFECHA", FechaVtaS);
            procedure.setInt("TURNO", turnoS);
            rs = procedure.executeQuery();

            while (rs.next()) {
                billete = new Billetes_Monedas();
                tipomoneda = new Tipo_Moneda();
                abonoCaja = new Abono_Caja();
                tipomoneda.setTipo_Moneda(rs.getInt("Tipo_Moneda"));
                tipomoneda.setDescripcion(rs.getString("Descripcion"));
                tipomoneda.setSimbolo(rs.getString("Simbolo"));
                abonoCaja.setCantidad(rs.getInt("Cantidad"));
                abonoCaja.setTotal(rs.getDouble("Total"));
                billete.setTipomoneda(tipomoneda);
                billete.setId_Billete(rs.getInt("Id_Billete"));
                billete.setValor(rs.getDouble("Valor"));
                billete.setRuta(rs.getString("Ruta"));
                billete.setId_Billete(rs.getInt("Id_Billete"));
                abonoCaja.setBiilete(billete);
                lista.add(abonoCaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BilletesMonedaData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return lista;

    }

    
}
