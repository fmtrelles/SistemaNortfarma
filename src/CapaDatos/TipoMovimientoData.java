package CapaDatos;

import entidad.Boticas;
import entidad.Laboratorios;
import entidad.Movimiento_Detalle;
import entidad.Movimientos;
import entidad.Producto;
import entidad.TipoMovimiento;
import entidad.Venta;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class TipoMovimientoData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    List<TipoMovimiento> listatipoDocumento = new ArrayList<TipoMovimiento>();
    List<TipoMovimiento> listatipoDocumento1 = new ArrayList<TipoMovimiento>();
    List<Venta> Lista_Internos = new ArrayList<Venta>();
    List<Venta> Lista_Internos_1 = new ArrayList<Venta>();
    List<Venta> Lista_Mov = new ArrayList<Venta>();
    List<Venta> Lista_Param = new ArrayList<Venta>();
    List<Venta> Lista_DatosVta = new ArrayList<Venta>();
    
    List<Venta> Lista_DescargosCab = new ArrayList<Venta>();
    List<Venta> Lista_DescargosDet = new ArrayList<Venta>();

    public TipoMovimientoData() {
        db = new ConexionPool();
    }

    public List<TipoMovimiento> ListarTipoMovimiento() {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_MOVIMIENTOS }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento.add(
                        new TipoMovimiento(rs.getString(1),
                        rs.getString(2)));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
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

    public List<TipoMovimiento> ListarTipoMovimientoFiltradoDescargo(Integer valor) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_MOVIMIENTOS_FILTRADO_DESCARGO(?) }");
            procedure.setInt(1, valor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento.add(
                        new TipoMovimiento(rs.getString(1), rs.getString(2)));

            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
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

    public List<TipoMovimiento> ListarTipoMovimientoDescargoSAP(Integer valor) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_DESCARGOSAP(?) }");
            procedure.setInt(1, valor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento.add(
                        new TipoMovimiento(rs.getString(1), rs.getString(2)));

            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
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

    public List<TipoMovimiento> ListarTipoMovimientoDescargoSAP1(Integer valor) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TIPO_DESCARGOSAP(?) }");
            procedure.setInt(1, valor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento1.add(
                        new TipoMovimiento(rs.getString(1), rs.getString(2)));

            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listatipoDocumento1;
    }

    public List<Venta> Lista_Internos(String Botica, String Docto, int TV) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_Internos.retainAll(Lista_Internos);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MOVIMIENTOS(?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Docto);
            procedure.setInt(3, TV);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Internos.add(new Venta(
                        rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"),
                        rs.getString("Serie"),
                        rs.getString("Numero"),
                        rs.getDouble("Total"),
                        rs.getInt("NC"),
                        rs.getInt("Id_Tipo_Venta")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Internos;

    }

        
    public List<Venta> Lista_DescargosBoticas(String Botica, String Docto,String fechaini, String fechafin, int opc) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_Internos.retainAll(Lista_Internos);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DESCARGOSBOTICAS(?,?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Docto);
            procedure.setString(3, fechaini.toString());
            procedure.setString(4, fechafin.toString());
            procedure.setInt(5, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Internos.add(new Venta(
                        rs.getString("Id_Botica"),                        
                        rs.getString("Descripcion"),
                        rs.getString("Numero_Documento"),
                        rs.getDate("Fecha_Registro"),
                        rs.getString("Usuario"),rs.getInt("Estado"),rs.getInt("anulado")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTAR_DESCARGOSBOTICAS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Internos;

    }
    
    public List<Venta> Lista_DescargosBoticas_1(String Botica, String Docto,String fechaini, String fechafin, int opc) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_Internos_1.retainAll(Lista_Internos_1);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DESCARGOSBOTICAS(?,?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Docto);
            procedure.setString(3, fechaini.toString());
            procedure.setString(4, fechafin.toString());
            procedure.setInt(5, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Internos_1.add(new Venta(
                        rs.getString("Id_Botica"),                        
                        rs.getString("Descripcion"),
                        rs.getString("Numero_Documento"),
                        rs.getDate("Fecha_Registro"),
                        rs.getString("Usuario"),rs.getInt("Estado"),rs.getInt("anulado")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTAR_DESCARGOSBOTICAS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Internos_1;

    }
    
    public List<Venta> Lista_DescargosBoticasImpre(String Botica, String Docto,String fechaini, String fechafin, int opc) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_DescargosCab.retainAll(Lista_DescargosCab);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DESCARGOSBOTICAS(?,?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Docto);
            procedure.setString(3, fechaini.toString());
            procedure.setString(4, fechafin.toString());
            procedure.setInt(5, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_DescargosCab.add(new Venta(
                        rs.getString("Id_Botica"),                        
                        rs.getString("descbotica"),
                        rs.getString("Direccion"),
                        rs.getString("RUCEmpresa"),
                        rs.getString("Descripcion"),
                        rs.getString("botdestino"),
                        rs.getString("diredestino"),
                        rs.getString("Numero_Documento"),                        
                        rs.getDate("Fecha_Registro"),
                        rs.getString("marcaplaca"),
                        rs.getString("consta"),rs.getString("licen")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTAR_DESCARGOSBOTICAS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_DescargosCab;

    }
    
    public List<Venta> Lista_DescargosBoticasImpreDetalle(String Botica, String Docto,String fechaini, String fechafin, int opc) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_DescargosDet.retainAll(Lista_DescargosDet);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DESCARGOSBOTICAS(?,?,?,?,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Docto);
            procedure.setString(3, fechaini.toString());
            procedure.setString(4, fechafin.toString());
            procedure.setInt(5, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_DescargosDet.add(new Venta(
                        rs.getString("Id_Producto"),                        
                        rs.getString("cant"),
                        rs.getString("Descripcion"),0));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTAR_DESCARGOSBOTICAS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_DescargosDet;

    }
        
    public List<Venta> Lista_Parametros() {

        try {

            conex = new ConexionPool().getConnection();
            Lista_Param.retainAll(Lista_Param);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_PARAMETROSENVIOFTP() }");
            /*procedure.setString(1, Botica);*/
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Param.add(new Venta(
                        rs.getString("Usuario"),
                        rs.getString("hostEnvio"),
                        rs.getString("Puerto"),
                        rs.getString("Passwd"),
                        rs.getString("SubFolder"),'1'));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_Param;

    }

    public List<Venta> Lista_DatosVtas(String idventa) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_DatosVta.retainAll(Lista_DatosVta);
            CallableStatement procedure = conex.prepareCall("{ call XML_LOAD_INTERNO(?) }");
            procedure.setString(1, idventa);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_DatosVta.add(new Venta(
                        rs.getString("Id_Botica"),
                        rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"),
                        rs.getInt("Id_Tipo_Venta"),
                        rs.getString("ID_TIPO_DOCUMENTO_SUNAT"),
                        rs.getString("Serie"),
                        rs.getString("Numero")));
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Lista_DatosVta;

    }
   
    public List<TipoMovimiento> ListarMovimientosCargosDescargos(String Botica, String Docto, String TV, String alma, String fecha1, String fecha2) {
        try {


            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MOVIMIENTOS_CARGOS_DESCARGOS(?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", Botica);
            procedure.setString("DOC", Docto);
            procedure.setString("MOVI", TV);
            procedure.setString("ALMA", alma);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("MIFECHA", fecha2);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento.add(
                        new TipoMovimiento(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));

            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PTMTipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listatipoDocumento;
    }

    public List<TipoMovimiento> ListarMovimientosPorRol(int Rol, int tipo) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CARGOS_DESCARGOS_ROL(?,?) }");
            procedure.setInt(1, Rol);
            procedure.setInt(2, tipo);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listatipoDocumento.add(
                        new TipoMovimiento(rs.getString(1), rs.getString(2)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos TipoMovimientosData :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listatipoDocumento;
    }

    public List<Movimientos> Recupera_Movimiento(Movimientos mov) {

        List<Movimientos> miLista = new ArrayList<Movimientos>();

        try {

            Movimientos movimiento;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_MOVIMIENTO(?,?,?) }");
            procedure.setDate("FECHA1", new java.sql.Date(mov.getFecha_Documento().getTime()));
            procedure.setDate("FECHA2", new java.sql.Date(mov.getFecha_Recepcion().getTime()));
            procedure.setString("DOCU", mov.getNumero_Documento());
            rs = procedure.executeQuery();

            Boticas botica;

            while (rs.next()) {
                botica = new Boticas();
                movimiento = new Movimientos();

                botica.setId_Botica(rs.getString("Id_Botica"));
                movimiento.setBotica(botica);
                movimiento.setId_TipoAlmacen(rs.getString("Id_TipoAlmacen"));
                movimiento.setId_Proveedor(rs.getString("Id_Proveedor"));
                movimiento.setTipo_movimiento(rs.getString("Id_TipoMovimiento"));
                movimiento.setDescripcion(rs.getString("Descripcion"));
                movimiento.setNumero_Documento(rs.getString("Numero_Documento"));
                movimiento.setFecha_Registro(rs.getDate("Fecha_Registro"));
                movimiento.setSerie(rs.getString("Serie"));
                movimiento.setNumero(rs.getString("Numero"));
                movimiento.setTotal(rs.getDouble("Total"));
                movimiento.setResponsable(rs.getString("responsable"));
                miLista.add(movimiento);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos Recupera_Movimiento :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return miLista;
    }
    
    public List<Movimientos> Recupera_MovimientoFactura(Movimientos mov, int opc) {

        List<Movimientos> miListaFact = new ArrayList<Movimientos>();

        try {

            Movimientos movimiento;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_FACTURASCLIENTE(?,?,?,?) }");
            procedure.setDate("FECHA1", new java.sql.Date(mov.getFecha_Documento().getTime()));
            procedure.setDate("FECHA2", new java.sql.Date(mov.getFecha_Recepcion().getTime()));
            procedure.setInt("COD_CLI", mov.getCodcli());
            procedure.setInt("POPC", opc);
            rs = procedure.executeQuery();

            Boticas botica;

            while (rs.next()) {
                botica = new Boticas();
                movimiento = new Movimientos();

                botica.setId_Botica(rs.getString("Id_Botica"));
                movimiento.setBotica(botica);                
                movimiento.setId_Venta(rs.getString("Id_Venta"));
                movimiento.setFecha_Venta(rs.getDate("Fecha_Venta"));
                movimiento.setNombre_RazonSocial(rs.getString("Nombre_RazonSocial"));
                movimiento.setRUC_DNI(rs.getString("RUC_DNI"));                
                movimiento.setDescripcion(rs.getString("Descripcion"));                                
                movimiento.setSerie(rs.getString("Serie"));
                movimiento.setNumero(rs.getString("Numero"));
                movimiento.setTotal(rs.getDouble("Total"));                
                
                miListaFact.add(movimiento);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos RECUPERA_FACTURASCLIENTE :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return miListaFact;
    }

    public List<Movimiento_Detalle> Recupera_Movimiento_Detalle(Movimientos mov) {

        List<Movimiento_Detalle> miLista = new ArrayList<Movimiento_Detalle>();

        try {

            Movimiento_Detalle movDetalle;
            Producto producto;
            Laboratorios laboratorio;

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DETALLE_MOVIMIENTO(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", mov.getBotica().getId_Botica());
            procedure.setString("IDALMA", mov.getId_TipoAlmacen());
            procedure.setString("IDPROVEEDOR", mov.getId_Proveedor());
            procedure.setString("TIPOMOV", mov.getTipo_movimiento());
            procedure.setString("DOCUMENTO", mov.getNumero_Documento());
            rs = procedure.executeQuery();


            while (rs.next()) {
                producto = new Producto();
                movDetalle = new Movimiento_Detalle();
                laboratorio = new Laboratorios();

                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setLaboratorio(laboratorio);
                movDetalle.setId_Producto(producto);
                movDetalle.setUnidad(rs.getInt("unidad"));
                movDetalle.setFraccion(rs.getInt("fraccion"));
                movDetalle.setPrecio_Venta_Final(rs.getDouble("Precio_Venta_Final"));
                movDetalle.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                movDetalle.setDescuento(rs.getDouble("Descuento"));
                movDetalle.setTotal(rs.getDouble("Total"));
                miLista.add(movDetalle);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos Recupera_Movimiento :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return miLista;
    }
    
    
    public List<Movimiento_Detalle> Recupera_Movimiento_Detalle_Factura(Movimientos mov,String fechavta) {

        List<Movimiento_Detalle> miLista = new ArrayList<Movimiento_Detalle>();

        try {

            Movimiento_Detalle movDetalle;
            Producto producto;
            Laboratorios laboratorio;

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DETALLEFACTURASCLIENTE(?,?,?) }");
            procedure.setString("IDBOTICA", mov.getBotica().getId_Botica());
            procedure.setString("IDVENTA", mov.getId_Venta());
            procedure.setString("FECHAVENTA",fechavta);
            
            rs = procedure.executeQuery();


            while (rs.next()) {
                producto = new Producto();
                movDetalle = new Movimiento_Detalle();
                laboratorio = new Laboratorios();

                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setLaboratorio(laboratorio);
                movDetalle.setId_Producto(producto);
                movDetalle.setUnidad(rs.getInt("unidad"));
                movDetalle.setFraccion(rs.getInt("fraccion"));
                movDetalle.setPrecio_Venta_Final(rs.getDouble("Precio_Venta_Final"));
                movDetalle.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                movDetalle.setDescuento(rs.getDouble("Descuento"));
                movDetalle.setTotal(rs.getDouble("Total"));
                movDetalle.setIdventa(rs.getString("Numero_Documento"));
                miLista.add(movDetalle);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos RECUPERA_DETALLEFACTURASCLIENTE :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return miLista;
    }
    
    
    public int AnulaDescargoAdicionales(String idbotica,String numdoc,int opc_aux) {
        int resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ANULA_DESCARGO_ADICIONAL(?,?,?) }");
            procedure.setString("BOTICA", idbotica);
            procedure.setString("DOC", numdoc);
            procedure.setInt("OPC", opc_aux);
            
            rs = procedure.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos ANULA_DESCARGO_ADICIONAL :" + ex.toString());
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
    
    public int VerificaImpreDescargo() {
        int resulta = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_IMPREDESCARGO() }");            
            
            rs = procedure.executeQuery();
            rs.next();
            resulta = rs.getInt(1);
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos VERIFICA_IMPREDESCARGO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resulta;

    }
    
    
    
}
