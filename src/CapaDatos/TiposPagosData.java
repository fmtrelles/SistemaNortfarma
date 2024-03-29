package CapaDatos;

import CapaLogica.LogicaFechaHora;
import entidad.TiposPagos;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Gomez S.
 */
public class TiposPagosData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private List<TiposPagos> tiposPagos = new ArrayList<TiposPagos>();
    private List<TiposPagos> tipPagos = new ArrayList<TiposPagos>();
    private List<TiposPagos> tipPagosMomey = new ArrayList<TiposPagos>();
    private ConexionPool db;
    LogicaFechaHora logfecha = new LogicaFechaHora();

    public TiposPagosData() {
        db = new ConexionPool();
    }

    public List<TiposPagos> retornaTipoPagos(int band) {

        tiposPagos.removeAll(tiposPagos);
        TiposPagos TiposPagos;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPOS_PAGOS (?) }");
            procedure.setInt("PARAMETRO", band);
            rs = procedure.executeQuery();

            while (rs.next()) {
                TiposPagos=new TiposPagos();
                TiposPagos.setId_TipoPago(rs.getInt("Id_TipoPago"));
                TiposPagos.setDescripcion(rs.getString("DescripcionTipoPago"));
                TiposPagos.setEsAbono(rs.getInt("EsAbono"));
                TiposPagos.setTPredondeo(rs.getString("Redondeo"));
                tiposPagos.add(TiposPagos);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("retornaTipoPagos :"+ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return tiposPagos;

    }

    public List<TiposPagos> retornaMoneda() {

        tipPagosMomey.removeAll(tipPagosMomey);
        TiposPagos TiposMoney;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_MONEDA () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                TiposMoney=new TiposPagos();
                TiposMoney.setId_Moneda(rs.getInt("Id_Moneda"));
                TiposMoney.setMoneda(rs.getString("Moneda"));
                tipPagosMomey.add(TiposMoney);

                //tipPagos.add(new TiposPagos(rs.getInt(1), rs.getString(2)));

            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("RETORNA_MONEDA :"+ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return tipPagosMomey;

    }

    public List<TiposPagos> retornaTipoPagosSOAT(int band) {

        tiposPagos.removeAll(tiposPagos);
        TiposPagos TiposPagos;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPOS_PAGOS_SOAT (?) }");
            procedure.setInt("PARAMETRO", band);
            rs = procedure.executeQuery();

            while (rs.next()) {
                TiposPagos=new TiposPagos();
                TiposPagos.setId_TipoPago(rs.getInt("Id_TipoPago"));
                TiposPagos.setDescripcion(rs.getString("DescripcionTipoPago"));
                TiposPagos.setEsAbono(rs.getInt("EsAbono"));
                tiposPagos.add(TiposPagos);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("retornaTipoPagos :"+ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return tiposPagos;

    }

    public List<TiposPagos> Retorna_Tarjetas(int band) {
        tipPagos.removeAll(tipPagos);
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPOS_PAGOS (?) }");
            procedure.setInt(1, band);
            rs = procedure.executeQuery();
            conex.commit();

            while (rs.next()) {
                tipPagos.add(new TiposPagos(rs.getInt(1), rs.getString(2)));
            }

            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return tipPagos;

    }

    public List<TiposPagos> ListarTipoPago() {

        if (tiposPagos.size() > 0) {
            tiposPagos.removeAll(tiposPagos);
        }

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_PAGOS }");
            procedure.execute();

            rs = procedure.getResultSet();

            while (rs.next()) {
                tiposPagos.add(
                        new TiposPagos(rs.getInt(1),
                        rs.getString(2)));


            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 1TipoPagosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return tiposPagos;

    }

    public Integer EliminarTipoPago(String Antiguo) {

        Integer Resultado = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ELIMINAR_TIPO_PAGO(?) }");
            procedure.setString(1, Antiguo);
            rs = procedure.executeQuery();


            while (rs.next()) {
                Resultado = rs.getInt(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 2TipoPagosData :" + ex.toString());
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

    public void ActualizarTipoPago(String Antiguo, String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_TIPO_PAGO('" + Antiguo + "','" + Nuevo + "') }");
            procedure.execute();

            rs = procedure.getResultSet();

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 3TipoPagosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public void AgregarTipoPago(String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_TIPO_PAGO(?) }");
            procedure.setString(1, Nuevo);
            procedure.executeQuery();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 4TipoPagosData :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


    }

    public boolean Agregar_Movimiento_deBCP(TiposPagos Objbcp) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            //System.out.println("{ call MOVIMIENTO_BCP('"+Objbcp.getIdbotica()+"','"+Objbcp.getId_Personal()+"','"+Objbcp.getOPERACION()+"','"+Objbcp.getNUMERO()+"','"+Objbcp.getMONTO()+"','"+Objbcp.getTIPO_MONEDA()+"','"+Objbcp.getTipo_cambio()+"','"+Objbcp.getIdcaja()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call MOVIMIENTO_BCP(?,?,?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", Objbcp.getIdbotica());
            procedure.setInt("IDPERSONAL", Objbcp.getId_Personal());
            procedure.setString("OPERACION", Objbcp.getOPERACION());
            procedure.setString("NUMERO", Objbcp.getNUMERO());
            procedure.setDouble("MONTO", Objbcp.getMONTO());
            procedure.setInt("TIPO_MONEDA", Objbcp.getTIPO_MONEDA());
            procedure.setDouble("TIPOCAMBIO", Objbcp.getTipo_cambio());
            procedure.setInt("TURNO", Objbcp.getTurno());
            procedure.setInt("CAJA", Objbcp.getIdcaja());
            procedure.executeQuery();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase Agregar_Movimiento_deBCP :" + ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(TiposPagosData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public boolean Agregar_Movimiento_deBCP_Cuadre(TiposPagos Objbcp, String Fecha) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            // System.out.println("{ call MOVIMIENTO_BCP_CUADRE('"+Objbcp.getIdbotica()+"','"+Objbcp.getId_Personal()+"','"+Objbcp.getOPERACION()+"','"+Objbcp.getNUMERO()+"','"+Objbcp.getMONTO()+"','"+Objbcp.getTIPO_MONEDA()+"','"+Objbcp.getTipo_cambio()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call MOVIMIENTO_BCP_CUADRE(?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", Objbcp.getIdbotica());
            procedure.setInt("IDPERSONAL", Objbcp.getId_Personal());
            procedure.setString("OPERACION", Objbcp.getOPERACION());
            procedure.setString("NUMERO", Objbcp.getNUMERO());
            procedure.setDouble("MONTO", Objbcp.getMONTO());
            procedure.setInt("TIPO_MONEDA", Objbcp.getTIPO_MONEDA());
            procedure.setDouble("TIPOCAMBIO", Objbcp.getTipo_cambio());
            procedure.setString("FECHA", Fecha);
            procedure.setInt("TURNO", Objbcp.getTurno());
            procedure.setInt("IDCAJA", Objbcp.getIdcaja());
            procedure.executeQuery();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase Agregar_Movimiento_deBCP :" + ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(TiposPagosData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public boolean Verifica_Clave_BCP(String idbotica, String clave) {

        boolean valor = false;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CLAVE_BCP(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDCLAVE", clave);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                valor = true;
            }

            rs.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 4TipoPagosData :" + ex.getMessage());
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

    public List<TiposPagos> Lista_movimientos_BCP(String idbotica, int idpersonal, int turno, int idcaja) {

        List<TiposPagos> Lista = new ArrayList<TiposPagos>();

        try {

            conex = new ConexionPool().getConnection();
            //System.out.println("{ call LISTA_MOVIMIENTOS_BCP('"+idbotica+"','"+idpersonal+"') }");
            CallableStatement procedure = conex.prepareCall("{ call LISTA_MOVIMIENTOS_BCP(?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDPERSONAL", idpersonal);
            procedure.setInt("MITURNO", turno);
            procedure.setInt("IDCAJA", idcaja);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista.add(new TiposPagos(rs.getString("NUMERO"), rs.getString("OPERACION"), rs.getDouble("MONTO"), rs.getInt("TIPO_MONEDA")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_movimientos_BCP :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista;

    }

    public List<TiposPagos> Lista_movimientos_BCP_Cuadre(String idbotica, int idpersonal, int caja, String fecha, int turno, int idcaja) {

        List<TiposPagos> Lista = new ArrayList<TiposPagos>();

        try {


            conex = new ConexionPool().getConnection();
            //System.out.println("{ call LISTA_MOVIMIENTOS_BCP_CUADRE('"+idbotica+"','"+idpersonal+"','"+caja+"','"+fecha+"','"+turno+"') }");
            CallableStatement procedure = conex.prepareCall("{ call LISTA_MOVIMIENTOS_BCP_CUADRE(?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDPERSONAL", idpersonal);
            procedure.setInt("IDCAJA", caja);
            procedure.setString("FECHA", fecha);
            procedure.setInt("TURNO", turno);
            procedure.setInt("MICAJA", idcaja);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista.add(new TiposPagos(rs.getString("NUMERO"), rs.getString("OPERACION"), rs.getDouble("MONTO"), rs.getInt("TIPO_MONEDA")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_movimientos_BCP :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista;

    }

    public List<TiposPagos> Lista_Totales_BCP(String idbotica, int idpersonal, int turno) {

        List<TiposPagos> Lista_Totales = new ArrayList<TiposPagos>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TOTALES_BCP(?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDPERSONAL", idpersonal);
            procedure.setInt("MITURNO", turno);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Totales.add(new TiposPagos(rs.getString("OPERACION"), rs.getDouble("MONTO"), rs.getInt("CANTIDAD")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Totales_BCP :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Totales;

    }

    public boolean Elimina_Movimiento_BCP(String idbotica, String numero, String fecha) {

        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            System.out.println("{ call ELIMINA_MOVIMIENTO_BCP('" + idbotica + "','" + numero + "','" + fecha + "') }");
            CallableStatement procedure = conex.prepareCall("{ call ELIMINA_MOVIMIENTO_BCP(?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDNUMERO", numero);
            procedure.setString("FECHA1", fecha);
            procedure.executeQuery();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase 4TipoPagosData :" + ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TiposPagosData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public List<TiposPagos> Lista_Tipos_Descuentos() {

        List<TiposPagos> Lista_Totales = new ArrayList<TiposPagos>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CREDITOS() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Totales.add(new TiposPagos(rs.getInt("Id_TipoPago"), rs.getString("DescripcionTipoPago")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Tipos_Descuentos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Totales;

    }
}
