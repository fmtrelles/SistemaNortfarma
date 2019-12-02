package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Cajas;
import entidad.DocumentoVentas;
import entidad.Turno;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CajasData {

    ResultSet rs;
    Statement stm;
    List<Cajas> listaCajas = new ArrayList<Cajas>();
    List<Cajas> listaObs = new ArrayList<Cajas>();
    private ConexionPool db;
    Connection conex;

    public CajasData() {
        db = new ConexionPool();
    }

    public List<Cajas> ListarCajas() {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CAJAS }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaCajas.add(
                        new Cajas(rs.getInt("Id_Cajas"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase XXXCAJASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaCajas;

    }

    public List<Cajas> ListarObs() {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_OBSERVACIONES }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaObs.add(
                        new Cajas(rs.getString("Id_Obs"), rs.getString("Descripcion"), "0"));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase XXXCAJASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaObs;

    }

    public List<Cajas> Lista_Saldo_Inicial(String idbotica, String fecha) {

        List<Cajas> saldos_cajas = new ArrayList<Cajas>();
        Cajas micaja = null;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_SALDO_CAJA (?,?)}");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("XFECHA", fecha);
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setMicaja(rs.getString("DESCAJA"));
                micaja.setSaldo(rs.getDouble("Saldo"));
                micaja.setApertura(rs.getInt("Apertura"));
                saldos_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Saldo_Inicial :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return saldos_cajas;

    }

    public List<Cajas> Lista_Documen_Gastos() {

        List<Cajas> doc_cajas = new ArrayList<Cajas>();
        Cajas micaja;
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DOC_GASTOS ()}");
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setIdgasto(rs.getInt("IDGASTO"));
                micaja.setDescripGasto(rs.getString("Descripcion"));
                micaja.setCaracter(rs.getString("Caracter"));
                micaja.setRetencion(rs.getInt("esRetencion"));
                doc_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Documen_Gastos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return doc_cajas;

    }

    public List<Cajas> Lista_Documen_Motivo() {

        List<Cajas> doc_motivos = new ArrayList<Cajas>();
        Cajas micaja1;
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DOC_MOTIVOS ()}");
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja1 = new Cajas();
                micaja1.setIdmotivo(rs.getInt("Id_Motivo"));
                micaja1.setDescripMotivo(rs.getString("Descripcion"));
                doc_motivos.add(micaja1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTA_DOC_GASTOS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return doc_motivos;

    }

    public List<Cajas> Lista_Documen_SubMotivo() {

        List<Cajas> doc_Submotivos = new ArrayList<Cajas>();
        Cajas micaja1;
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DOC_SUBMOTIVOS ()}");
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja1 = new Cajas();
                micaja1.setIdSubmotivo(rs.getString("ID_BOTICA"));
                micaja1.setDescripSubMotivo(rs.getString("DESCRIPCION"));
                doc_Submotivos.add(micaja1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTA_DOC_SUBMOTIVOS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return doc_Submotivos;

    }

    public List<Turno> Lista_Turnos_Aperura(String idbotica, int idcaja, int idpersonal) {

        List<Turno> doc_cajas = new ArrayList<Turno>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TURNOS_APERTURA (?,?,?)}");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCAJA", idcaja);
            procedure.setInt("IDPERSONAL", idpersonal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                doc_cajas.add(new Turno(rs.getInt("Id_Turno"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Turnos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public List<Turno> Lista_Turnos() {

        List<Turno> doc_cajas = new ArrayList<Turno>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TURNOS ()}");
            rs = procedure.executeQuery();

            while (rs.next()) {
                doc_cajas.add(new Turno(rs.getInt("Id_Turno"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Turnos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public List<Cajas> Devuelve_Data_Retencion(int retencion) {

        List<Cajas> doc_cajas = new ArrayList<Cajas>();
        Cajas micaja;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DEVUELVE_DATA_RETENCION (?)}");
            procedure.setInt("IDRETENCION", retencion);
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setRetencion(rs.getInt("Id_Retencion"));
                micaja.setDescripRetencion(rs.getString("Descripcion_Retencion"));
                micaja.setMonto_Retencion(rs.getDouble("Monto_Retencion"));
                micaja.setPorcen_Retencion(rs.getDouble("Porcentaje_Descuento"));
                doc_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Documen_Gastos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public List<Cajas> Lista_Documen_Ingresos() {

        List<Cajas> doc_cajas = new ArrayList<Cajas>();
        Cajas micaja;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DOC_INGRESOS ()}");
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setIdgasto(rs.getInt("IDGASTO"));
                micaja.setDescripGasto(rs.getString("Descripcion"));
                micaja.setCaracter("");
                doc_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Documen_Ingresos :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public boolean Ingreso_Gastos_Caja(List<Cajas> obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call INGRESO_GASTOS_CAJA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            for (int i = 0; i < obj.size(); i++) {
                procedure.setString("IDBOTICA", obj.get(i).getIdbotica());
                procedure.setInt("IDCAJA", obj.get(i).getIdcaja());
                procedure.setInt("IDPERSONAL", obj.get(i).getIdpersonal());
                procedure.setInt("TIPOGASTO", obj.get(i).getIdgasto());
                procedure.setString("DOC", obj.get(i).getDocugasto());
                procedure.setDouble("MIMONTO", obj.get(i).getMontoGasto());
                procedure.setString("CONCEP", obj.get(i).getDescripGasto());
                procedure.setInt("IDCAJERO", obj.get(i).getIdcajero());
                procedure.setString("DESCRIPRETEN", obj.get(i).getDescripRetencion());
                procedure.setInt("IDTURNO", obj.get(i).getIdturno());
                procedure.setString("SERIENC", obj.get(i).getSerie());
                procedure.setString("NUMERONC", obj.get(i).getNumeracion());
                procedure.setString("IDVENTA", obj.get(i).getId_VentaNC());
                procedure.setString("FECHA", obj.get(i).getFecha());
                procedure.setString("CLIENTEGASTO", obj.get(i).getclientegasto());
                procedure.setInt("IDMOTIVOGASTO", obj.get(i).getIdmotivo());
                procedure.setString("ORIGEN", obj.get(i).getOrigen());
                procedure.setString("DESTINO", obj.get(i).getDestino());
                procedure.setString("IDPERSONALMOV", obj.get(i).getIdPersonalMov());
                procedure.setString("SUBMOTIVOGASTO", obj.get(i).getSubmotivo());
                procedure.executeQuery();
            }

            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos Ingreso_Gastos_Caja :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(CajasData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public boolean Ingreso_Caja(Cajas obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            //System.out.println("{ call INGRESO_CAJA('"+obj.getBotica()+"','"+ obj.getIdcaja()+"','"+obj.getIdpersonal()+"','"+obj.getIdgasto()+"','"+obj.getDocugasto()+"','"+obj.getMontoGasto()+"','"+obj.getDescripGasto()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call INGRESO_CAJA(?,?,?,?,?,?,?,?,?) }");
            procedure.setString(1, obj.getIdbotica());
            procedure.setInt(2, obj.getIdcaja());
            procedure.setInt(3, obj.getIdpersonal());
            procedure.setInt(4, obj.getIdgasto());
            procedure.setString(5, obj.getDocugasto());
            procedure.setDouble(6, obj.getMontoGasto());
            procedure.setString(7, obj.getDescripGasto());
            procedure.setInt(8, obj.getIdcajero());
            procedure.setInt(9, obj.getIdturno());
            procedure.executeQuery();
            conex.commit();
            valor = true;

        } catch (Exception ex) {

            System.out.println("Error CapaDatos Ingreso_Gastos_Caja :" + ex.toString());
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

    public boolean Ingreso_Abonado_Caja(Cajas obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call INGRESO_MONTO_ABONADO(?,?,?) }");
            procedure.setString(1, obj.getIdbotica());
            procedure.setInt(2, obj.getIdcaja());
            procedure.setInt(3, obj.getIdcajero());
            procedure.executeQuery();
            conex.commit();
            valor = true;

        } catch (Exception ex) {

            System.out.println("Error CapaDatos Ingreso_Abonado_Caja :" + ex.toString());
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

    public List<Cajas> Lista_Cajas_Cierre(String idbotica, String fecha) {

        List<Cajas> doc_cajas = new ArrayList<Cajas>();
        Cajas micaja;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CIERRE_BOTICA (?,?)}");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("MIFECHA", fecha);
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setIdbotica(idbotica);
                micaja.setMiFecha(rs.getDate("FECHA"));
                micaja.setCaja(rs.getString("Descripcion"));
                micaja.setResponsable(rs.getString("responsable"));
                micaja.setTurno(rs.getInt("Turno"));
                micaja.setHora(rs.getString("HORA"));
                micaja.setApertura(rs.getInt("Apertura"));
                doc_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Cajas_Cierre :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public boolean Verifica_Caja_Cerrada(Cajas obj) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CAJA_CERRADA(?,?,?,?,?) }");
            procedure.setString(1, obj.getIdbotica());
            procedure.setInt(2, obj.getIdcaja());
            procedure.setString(3, obj.getFecha());
            procedure.setInt(4, obj.getTurno());
            procedure.setInt(5, obj.getIdpersonal());
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                valor = true;
            }

            rs.close();

        } catch (Exception ex) {

            System.out.println("Error CapaDatos Verifica_Caja_Cerrada :" + ex.toString());
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

    public List<Cajas> Lista_Gastos_Caja(Cajas obj) {

        List<Cajas> doc_cajas = new ArrayList<Cajas>();
        Cajas micaja;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_GASTOS_CAJA (?,?,?,?,?)}");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("TURNO", obj.getTurno());
            procedure.setString("FECHA", obj.getFecha());
            procedure.setInt("IDCAJERO", obj.getIdcajero());
            rs = procedure.executeQuery();

            while (rs.next()) {
                micaja = new Cajas();
                micaja.setDescripGasto(rs.getString("Descripcion"));
                micaja.setDocugasto(rs.getString("Documento"));
                micaja.setMontoGasto(rs.getDouble("Monto"));
                micaja.setDescripRetencion(rs.getString("Concepto"));
                micaja.setIdgasto(rs.getInt("Id_Tipo_Gasto"));
                micaja.setNombre_RazonSocial(rs.getString("Nombre_RazonSocial"));
                micaja.setdesgasto(rs.getString("desgasto"));
                doc_cajas.add(micaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Lista_Gastos_Caja :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return doc_cajas;

    }

    public boolean Elimina_Gasto(Cajas obj) {

        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ELIMINA_GASTO_CAJA(?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setString("FECHA", obj.getFecha());
            procedure.setInt("IDCAJERO", obj.getIdcajero());
            procedure.setInt("TURNO", obj.getTurno());
            procedure.setString("DOCU", obj.getDocugasto());
            procedure.executeQuery();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error CapaDatos Elimina_Gasto :" + ex.toString());
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

    public List<DocumentoVentas> ListaDocumentos(Cajas obj) {

        DocumentoVentas micajas = null;
        List<DocumentoVentas> lista = new ArrayList<DocumentoVentas>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DOCUMENTOS_APERTURA(?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            rs = procedure.executeQuery();

            while (rs.next()) {
                micajas = new DocumentoVentas();
                micajas.setSerie(rs.getString("SERIE"));
                micajas.setNumeracion(rs.getString("NUMERO"));
                micajas.setDocumento(rs.getString("DESCRIPCION"));
                micajas.setTipoVenta(rs.getInt("ID_Tipo_Venta"));
                micajas.setAperturA(rs.getInt("Apertura"));
                lista.add(micajas);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos CAJASDATA ListaDocumentos :" + ex.toString());
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

    public int VerificaEsBCP(Cajas obj) {
        int valorbcp = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ESBCP(?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            
            rs = procedure.executeQuery();
            rs.next();
            valorbcp = rs.getInt(1);
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos VERIFICA_ESBCP :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return valorbcp;

    }
    
    public int Consulta_Caja_Cerrada(Cajas obj) {
        int valor = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call CONSULTA_CAJA_CERRADA(?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("MITURNO", obj.getIdturno());
            procedure.setInt("IDCAJERO", obj.getIdcajero());
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getInt(1);
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos Consulta_Caja_Cerrada :" + ex.toString());
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

    public int Consulta_Caja(Cajas obj) {
        int valor = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call CONSULTA_CAJA(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("MITURNO", obj.getIdturno());
            procedure.setInt("IDCAJERO", obj.getIdcajero());
            procedure.setDate("MIFECHA", obj.getMiFecha());
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getInt(1);
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos Consulta_Caja :" + ex.toString());
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

    public List<Cajas> ListarCajas_Aperturadas() {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CAJAS_APERTURADAS() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaCajas.add(
                        new Cajas(rs.getInt("Id_Cajas"), rs.getString("Descripcion")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase XXXCAJASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaCajas;

    }
}
