package CapaDatos;

import entidad.ResultadoVenta;
import entidad.TipoDocumento;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoData {

    ResultSet rs;
    Statement stm;
    List<TipoDocumento> listatipoDocumento = new ArrayList<TipoDocumento>();
    List<TipoDocumento> listatipoDocumentoReimprime = new ArrayList<TipoDocumento>();
    private ConexionPool db;
    Connection conex;

    public TipoDocumentoData() {
        db = new ConexionPool();
    }

    public void ActualizarTipoDocumento(String antiguo, String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_TIPO_VENTA(?,?) }");
            procedure.setString(1, antiguo);
            procedure.setString(2, Nuevo);
            procedure.execute();
            rs.close();

        } catch (Exception ex) {
            System.out.println("01. Error CapaDatos clase TipoVentasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }


    }

    public void AgregarTipoDocumentoVenta(String Nuevo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_TIPO_VENTA(?) }");
            procedure.setString(1, Nuevo);
            procedure.execute();

        } catch (Exception ex) {
            System.out.println("02. Error CapaDatos clase TipoVentasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public void EliminarTipoDocumentoVenta(String Antiguo) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ELIMINAR_TIPO_VENTA('" + Antiguo + "') }");
            procedure.execute();

        } catch (Exception ex) {
            System.out.println("03. Error CapaDatos clase TipoVentasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public List<TipoDocumento> ListarTipoDocumento() {
      
        listatipoDocumento.removeAll(listatipoDocumento);
        TipoDocumento docuemntos;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_VENTAS }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                docuemntos=new TipoDocumento();
                docuemntos.setId_Tipo_Venta(rs.getInt("Id_Tipo_Venta"));
                docuemntos.setDescripcion(rs.getString("Descripcion"));
                docuemntos.setModificable(rs.getInt("Modificable"));
                listatipoDocumento.add(docuemntos);
            }
            
            rs.close();

        } catch (Exception ex) {
            System.out.println("04. Error CapaDatos clase TipoVentasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listatipoDocumento;

    }
    public List<TipoDocumento> ListarTipoDocumentoReimprime() {

        listatipoDocumentoReimprime.removeAll(listatipoDocumentoReimprime);
        TipoDocumento docuemntosReimprime;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPODOC_REIMPRIME }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                docuemntosReimprime=new TipoDocumento();
                docuemntosReimprime.setId_Tipo_Venta(rs.getInt("Id_Tipo_Venta"));
                docuemntosReimprime.setDescripcion(rs.getString("Descripcion"));
                docuemntosReimprime.setModificable(rs.getInt("Modificable"));
                listatipoDocumentoReimprime.add(docuemntosReimprime);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("04. Error CapaDatos clase TipoVentasData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listatipoDocumentoReimprime;

    }

    /**********************************************
     *
     * Programador  : Gino Paredes Zurita
     * Fecha        : 16-11-2013
     * Modulo       : Pre-Anulados
     * Tipo         : NUEVO MODULO - FormPreAnulacionInterno.java
     *
     ***********************************************/
    public boolean PreAnula(String Botica, String interno, String variable, int usuario) {

        boolean valor = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call PREANULAR_MOVIMIENTO_VENTA(?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, interno);
            procedure.setString(3, variable);
            procedure.setInt(4, usuario);
            procedure.execute();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                valor = false;
            } catch (SQLException ex1) {
                System.out.println("06. Error CapaDatos clase TipoVentasData :" + ex1.toString());
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
    /*
     * fin
     */
    
    public boolean Anula(String Botica, String Documento, int idpersonal, String observacion) {

        boolean valor = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ANULAR_MOVIMIENTO_VENTA(?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Documento);
            procedure.setInt(3, idpersonal);
            procedure.setString(4, observacion);
            procedure.execute();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                valor = false;
            } catch (SQLException ex1) {
                System.out.println("06. Error CapaDatos clase TipoVentasData :" + ex1.toString());
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

    public boolean Activa(String Botica, String Documento, int idpersonal, String observacion,String fechavta) {

        boolean valor = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ACTIVAR_VENTA(?,?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Documento);
            procedure.setInt(3, idpersonal);
            procedure.setString(4, observacion);
            procedure.setString(5, fechavta);
            procedure.execute();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                valor = false;
            } catch (SQLException ex1) {
                System.out.println("06. Error CapaDatos clase TipoVentasData :" + ex1.toString());
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

    public ResultadoVenta AnulaCargoDescargo(String Botica, String TipoMovimiento, String Documento, String Personal, String alma, String proveedor, String Observacion, String movi) {

        boolean resultado = false;
        ResultadoVenta objresultado = null;
        String nomAnulaCargosDescargos = "";
        
        
        try {
            
            if (movi.equals("AC")){
                nomAnulaCargosDescargos = "ANULAR_MOVIMIENTO_DESCARGOS_CLIENTES"; 
            }else{

                if (alma.equals("T") || TipoMovimiento.equals("RT")){ 
                    nomAnulaCargosDescargos = "ANULAR_MOVIMIENTO_CARGOS_DESCARGOS_TRAST";                
                }else{
                    nomAnulaCargosDescargos = "ANULAR_MOVIMIENTO_CARGOS_DESCARGOS";                
                }
            }
            
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call " + nomAnulaCargosDescargos + "(?,?,?,?,?,?,?) }");
            // System.out.println("call ANULAR_MOVIMIENTO_CARGOS_DESCARGOS('"+Botica+"','"+TipoMovimiento+"','"+Documento+"','"+Personal+"','"+alma+"','"+proveedor+"')");
            procedure.setString(1, Botica);
            procedure.setString(2, TipoMovimiento);
            procedure.setString(3, Documento);
            procedure.setString(4, Personal);
            procedure.setString(5, alma);
            procedure.setString(6, proveedor);
            procedure.setString(7, Observacion);
            

            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == -1) {
                objresultado = new ResultadoVenta(1, rs.getString(3), "", false, "");
                conex.rollback();
                return objresultado;
            }

            conex.commit();
            objresultado = new ResultadoVenta(0, "", "", false, "");

        } catch (Exception ex) {
            try {
                conex.rollback();
                objresultado = new ResultadoVenta(2, "", "", false, "");
                resultado = false;
            } catch (Exception ex1) {
                System.out.println("06. Error CapaDatos clase AnulaCargoDescargo :" + ex1.toString());
            }
            System.out.println("06. Error CapaDatos clase AnulaCargoDescargo :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return objresultado;

    }

    public int Verifica_Validacion() {

        int valor = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_VALIDACION() }");
            //procedure.setInt(1, tipventa);
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getInt("Valor");
            rs.close();

        } catch (Exception ex) {
            valor = 0;
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

    public int Verifica_esFactura(int tipventa) {

        int valor = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ESFACTURA(?) }");
            procedure.setInt(1, tipventa);
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getInt("esFactura");
            rs.close();

        } catch (Exception ex) {
            valor = 0;
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
}
