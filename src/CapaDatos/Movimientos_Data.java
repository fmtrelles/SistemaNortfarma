/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Movimientos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class Movimientos_Data {

    ResultSet rs;
    Statement stm;
    List<Movimientos> listaMov = new ArrayList<Movimientos>();
    private ConexionPool db;
    Connection conex;

    public Movimientos_Data() {
        db = new ConexionPool();
    }

    public Movimientos Find_Descargos_Inv(String idbotica, Movimientos p) {

        Movimientos OBJMOV = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FIND_BUSCA_DESCARGO (?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDMOV", p.getId_TipoAlmacen());
            procedure.setString("DOC", p.getNumero_Documento());
            procedure.setString("ALM", p.getTipo_movimiento());
            rs = procedure.executeQuery();

            while (rs.next()) {
                OBJMOV = new Movimientos(rs.getDate("Fecha_Documento"), rs.getString("responsable"),
                        rs.getDate("Fecha_Recepcion"), rs.getDate("Fecha_Registro"));
            }
            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return OBJMOV;
    }

    public List<Movimientos> Find_Descargos__Detalle_Inv(String idbotica, Movimientos p) {

        listaMov.removeAll(listaMov);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Find_Descargos__Detalle_Inv (?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDALMA", p.getTipo_movimiento());
            procedure.setString("IDTIPMOV", p.getId_TipoAlmacen());
            procedure.setString("DOCU", p.getNumero_Documento());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaMov.add(new Movimientos(rs.getString("Id_Producto"), rs.getString("Descripcion"),
                        rs.getString("Id_Laboratorio"), rs.getString("CANTIDAD"),
                        rs.getDouble("Precio_Venta_Final"), rs.getDouble("Total")));
            }


            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listaMov;


    }

    public List<Movimientos> Lista_Descargos_Inv(String IDBOTICA, String tipmov, String alm) {

        listaMov.removeAll(listaMov);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DESCARGOS_INVENTARIOS (?,?,?) }");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setString("IDTIPMOV", tipmov);
            procedure.setString("ALM", alm);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaMov.add(new Movimientos(rs.getString("Id_TipoAlmacen"), rs.getString("Id_Proveedor"),
                        rs.getString("Numero_Documento"), rs.getDate("Fecha_Documento"),
                        rs.getString("responsable")));
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listaMov;
    }

    public List<Movimientos> Busca_Descargos_Inv(String IDBOTICA, String tipmov, String doc) {

        listaMov.removeAll(listaMov);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BUSCA_DESCARGOS_INV (?,?,?) }");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setString("IDMOV", tipmov);
            procedure.setString("DOC", doc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaMov.add(new Movimientos(rs.getString("Id_TipoAlmacen"), rs.getString("Id_Proveedor"),
                        rs.getString("Numero_Documento"), rs.getDate("Fecha_Documento"),
                        rs.getString("responsable")));
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return listaMov;
    }

    public Movimientos Verifica_Movimiento(String idbotica, String doc/*, int opc*/) {

        Movimientos objmovimiento = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_DOCUMENTO_CARGADO (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("NUNDOCU", doc);
            //procedure.setInt("OPC", opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                objmovimiento = new Movimientos();
                objmovimiento.setNumero_Documento(rs.getString("Numero_Documento"));
                objmovimiento.setFecha_Registro(rs.getDate("Fecha_Registro"));
                objmovimiento.setHora(rs.getString("Hora"));
                objmovimiento.setResponsable(rs.getString("responsable"));
                objmovimiento.setTipo_movimiento(rs.getString("Descripcion"));
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return objmovimiento;
    }

    public Movimientos Devuelve_Movimeinto(String mov) {

        Movimientos objmovimiento = null;

        try {

            conex = new ConexionPool().getConnection();            
            CallableStatement procedure = conex.prepareCall("{ call DEVUELVE_NUM_DOCUMENTO (?) }");
            procedure.setString("MOVIMIENTO", mov);
            rs = procedure.executeQuery();

            while (rs.next()) {
                objmovimiento = new Movimientos();
                objmovimiento.setNumero_Documento(rs.getString("Numero"));
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
            }
        }

        return objmovimiento;
    }
    
        
    
    public List<Movimientos> Devuelve_SerieNumero(String mov) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DEVUELVE_DOCUMENTO(?) }");
            procedure.setString(1, mov);
           
            rs = procedure.executeQuery();

           
            while (rs.next()) {
                listaMov.add(new Movimientos(rs.getString("Serie"), rs.getString("Numero"), rs.getInt("limite")));
            }            
            

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos DEVUELVE_DOCUMENTO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaMov;
    }

    public Movimientos Devuelve_SerienumeroNC(String idventa,String serieTransaction, String numeroTransaction, String fechaVta,String OPC) {

        Movimientos objmovimiento = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DEVUELVE_SERIENUM_NC (?,?,?,?,?) }");
            procedure.setString("IDVENTA", idventa);
            procedure.setString("SERIEVTA", serieTransaction);
            procedure.setString("NUMEROVTA", numeroTransaction);
            procedure.setString("FECHAVTA", fechaVta);
            procedure.setString("OPCION", OPC);

            rs = procedure.executeQuery();

            while (rs.next()) {
                objmovimiento = new Movimientos();
                objmovimiento.setSerie(rs.getString("Serie"));
                objmovimiento.setNumero_Documento(rs.getString("Numero"));
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conex.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return objmovimiento;
    }
}
