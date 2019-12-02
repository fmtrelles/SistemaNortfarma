package CapaDatos;

import CapaLogica.LogicaFechaHora;
import CapaLogica.LogicaVenta;
import entidad.Producto;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Guias;
import entidad.Movimiento_Detalle;
import entidad.Proforma;
import entidad.Movimientos;
import entidad.Venta;
import java.util.regex.Pattern;
import sistemanortfarma.OpcionesMenu;

public class GuiasData {

    Connection conex;
    PreparedStatement st;
    ResultSet rs;
    Statement stm;
    List<Guias> listaGuias = new ArrayList<Guias>();
    List<Guias> listaProdGuias = new ArrayList<Guias>();
    LogicaFechaHora objFechaHora = new LogicaFechaHora();
    List<Venta> listVentas40 = new ArrayList<Venta>();
    LogicaVenta objVenta = new LogicaVenta();
    private ConexionPool db;

    public GuiasData() {
        db = new ConexionPool();
    }

    public String AgregarGuia(Guias objGuias) throws SQLException {

        String resultado = "0";

        try {

            conex = new ConexionPool().getConnection();
            Pattern p = Pattern.compile("/");
            String[] items = p.split(objGuias.getFecha_Documento());
            String NuevaFecha = "";

            for (int i = items.length - 1; i >= 0; i--) {
                NuevaFecha = NuevaFecha + items[i];
                if (i - 1 >= 0) {
                    NuevaFecha = NuevaFecha + "-";
                }
            }

            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_GUIA('" + objGuias.getId_TipoMovimiento() + "','" + objGuias.getId_TipoAlmacen() + "','" + objGuias.getId_TipoMovimiento() + "','" + objGuias.getId_Proveedor() + "','" + objGuias.getNumero_Documento() + "','" + NuevaFecha + "','" + NuevaFecha + "'," + objGuias.getTotal() + "," + objGuias.getDescuento() + "," + objGuias.getId_Personal() + " ) }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                resultado = rs.getString(1);
            }

            rs.close();
            return resultado;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData03 :" + ex.getMessage().toString());
            return resultado;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public List<Guias> RecuperaDetalleCargo(String BoticaDestino, String NumeroDocumento, String BoticaOrigen) {

        /*Debe ser conexion a la maestra*/
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DETALLE_DESCARGO(?,?,?) }");
            procedure.setString(1, BoticaDestino);
            procedure.setString(2, BoticaOrigen);
            procedure.setString(3, NumeroDocumento);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaGuias.add(new Guias(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }

            rs.close();

        } catch (Exception ex) {

            System.out.println("Error CapaDatos clase GuiasData 04:" + ex.getMessage().toString());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaGuias;

    }

    public Integer VerificaExisteGuiaBoticaDescargo(String BoticaDestino, String BoticaOrigen, String NumeroGuia) {

        Integer resultado = 0;
        /*Debe ser conexion a la maestra*/
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_EXISTE_DESCARGO(?,?,?) }");
            procedure.setString(1, BoticaDestino);
            procedure.setString(2, NumeroGuia);
            procedure.setString(3, BoticaOrigen);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData 05:" + ex.getMessage().toString());
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

    public String AgregarDetalleGuia(List<Guias> objGuias, Guias entidadguias_cabecera) throws SQLException {
        String resultado = "0";
        String nomDetalleGuia = "";

        if (!entidadguias_cabecera.getId_TipoAlmacen().equals("T")){                 
                nomDetalleGuia = "AGREGAR_DETALLE_GUIA";
            }else{                
                nomDetalleGuia = "AGREGAR_DETALLE_GUIA_TRASTIENDA";
            }
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);

            Pattern p1 = Pattern.compile("/");
            String[] items1 = p1.split(entidadguias_cabecera.getFecha_Documento());
            String NuevaFecha = "";

            for (int i = items1.length - 1; i >= 0; i--) {
                NuevaFecha = NuevaFecha + items1[i];
                if (i - 1 >= 0) {
                    NuevaFecha = NuevaFecha + "-";
                }
            }

            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_GUIA('" + entidadguias_cabecera.getId_TipoMovimiento() + "','" + entidadguias_cabecera.getId_TipoAlmacen() + "','" + entidadguias_cabecera.getId_TipoMovimiento() + "','" + entidadguias_cabecera.getId_Proveedor() + "','" + entidadguias_cabecera.getNumero_Documento() + "','" + NuevaFecha + "','" + NuevaFecha + "'," + entidadguias_cabecera.getTotal() + "," + entidadguias_cabecera.getDescuento() + "," + entidadguias_cabecera.getId_Personal() + " ) }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getString(1);
            }



            if (resultado.compareTo("1") == 0) {

                for (Integer c = 0; c < objGuias.size(); c++) {
                    NuevaFecha = "";
                    Pattern p = Pattern.compile("/");
                    String[] items = p.split(objGuias.get(c).getFecha_Registro());

                    for (int i = items.length - 1; i >= 0; i--) {
                        NuevaFecha = NuevaFecha + items[i];
                        if (i - 1 >= 0) {
                            NuevaFecha = NuevaFecha + "-";
                        }
                    }


                    CallableStatement procedure1 = conex.prepareCall("{call " + nomDetalleGuia + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    procedure1.setString(1, objGuias.get(c).getId_TipoMovimiento());
                    procedure1.setString(2, objGuias.get(c).getId_TipoAlmacen());
                    procedure1.setString(3, objGuias.get(c).getId_TipoMovimiento());
                    procedure1.setString(4, objGuias.get(c).getId_Proveedor());
                    procedure1.setString(5, objGuias.get(c).getNumero_Documento());
                    procedure1.setString(6, objGuias.get(c).getId_Producto());
                    procedure1.setDouble(7, objGuias.get(c).getPrecio_Venta());
                    procedure1.setDouble(8, objGuias.get(c).getDescuento());
                    procedure1.setInt(9, objGuias.get(c).getUnidad());
                    procedure1.setInt(10, objGuias.get(c).getFraccion());
                    procedure1.setInt(11, 0);
                    procedure1.setInt(12, 0);
                    procedure1.setInt(13, 0);
                    procedure1.setInt(14, 0);
                    procedure1.setString(15, NuevaFecha);
                    procedure1.setInt(16, objGuias.get(c).getId_Personal());
                    procedure1.execute();
                }

                conex.commit();
                resultado = "0";

            } else {
                conex.rollback();
                resultado = "1";
                return resultado;
            }

        } catch (Exception ex) {
            conex.rollback();
            resultado = "2";
            System.out.println("Error CapaDatos clase JXKKGuiasData :" + ex.getMessage());
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

    

    public Integer ValidarProductosGuia(String Botica, String Producto) {

        Integer resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_PRODUCTO_BOTICA(? ,?) }");
            procedure.setString(1, Botica);
            procedure.setString(2, Producto);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData 06:" + ex.getMessage().toString());
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

    public String RecuperaFechaDocto(String BoticaDestino, String BoticaOrigen, String NumeroDocumento) {

        String fechaDoc = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_FECHA_DOCTO(?,?,?) }");
            procedure.setString(1, BoticaDestino);
            procedure.setString(2, BoticaOrigen);
            procedure.setString(3, NumeroDocumento);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                System.out.println(rs.getString(1));
                fechaDoc = rs.getString(1);
            }

            rs.close();
        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData 07:" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return fechaDoc;
    }

    public void CargarGuiaBotica(Guias entidadGuia, String Obns, int tipo) {

        try {

            if (OpcionesMenu.getIdbotica().compareTo(entidadGuia.getId_TipoAlmacen()) != 1) {
                entidadGuia.setId_TipoAlmacen(entidadGuia.getFecha_Documento());
            }

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call CARGAR(?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString(1, OpcionesMenu.getIdbotica());
            procedure.setString(2, entidadGuia.getId_Proveedor());
            procedure.setString(3, entidadGuia.getId_TipoMovimiento());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getNumero_Documento());
            procedure.setString(7, Obns);
            procedure.setInt(8, tipo);
            procedure.setString(9, entidadGuia.getFecha_Recepcion());
            procedure.setInt(10, 1);
            procedure.execute();
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase UUU001GuiasData :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void DescargarGuiaBotica(Guias entidadGuia, String Obns) throws SQLException {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DESCARGAR(?,?,?,?,?,?,?,?) }");
            conex.setAutoCommit(false);
            procedure.setString(1, entidadGuia.getFecha_Documento());
            procedure.setString(2, entidadGuia.getId_Proveedor());
            procedure.setString(3, entidadGuia.getId_TipoAlmacen());
            procedure.setString(4, entidadGuia.getId_TipoMovimiento());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getNumero_Documento());
            procedure.setString(7, Obns);
            procedure.setString(8, entidadGuia.getFecha_Recepcion());
            procedure.execute();
            conex.commit();
            rs.close();
            conex.close();

        } catch (Exception ex) {
            conex.rollback();
            System.out.println("Error CapaDatos clase GuiasData01 :" + ex.getMessage().toString());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    public Movimientos CargarDetalleGuia(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie) {

        String doc = null;
        Movimientos resul = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CARGAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("Cargadora", entidadGuia.getMovimiento());
            procedure.setString("Documento", entidadGuia.getNumero_Documento());
            procedure.setString("TipoAlmac", entidadGuia.getId_TipoAlmacen());
            procedure.setString("BoticaEnvia", entidadGuia.getId_Botica());
            procedure.setInt("Personal", entidadGuia.getId_Personal());
            procedure.setString("fechaDocto", entidadGuia.getFecha_Documento());
            procedure.setString("OBs", Obns);
            procedure.setInt("Tipo", tipo);
            procedure.setString("FReg", entidadGuia.getFecha_Recepcion());
            procedure.setInt("EsCar", 1);
            procedure.setDouble("TOTAL", entidadGuia.getTotal());
            procedure.setString("IDPROVEEDOR", entidadGuia.getId_Proveedor());
            procedure.setString("MISERIE", serie);
            procedure.setString("MINUMERO", entidadGuia.getNumero());
            rs = procedure.executeQuery();
            rs.next();
            doc = rs.getString(1);

            CallableStatement procedure1;


            for (int i = 0; i < array.size(); i++) {

                procedure1 = conex.prepareCall("{ call CARGAR_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure1.setString(1, entidadGuia.getMovimiento());
                procedure1.setString(2, doc);
                procedure1.setString(3, entidadGuia.getId_Botica());
                procedure1.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure1.setInt(5, entidadGuia.getId_Personal());
                procedure1.setString(6, entidadGuia.getFecha_Documento());
                procedure1.setString(7, array.get(i).getId_Producto().getIdProducto());
                procedure1.setInt(8, array.get(i).getUnidad());
                procedure1.setInt(9, array.get(i).getFraccion());
                procedure1.setInt(10, tipo);
                procedure1.setInt(11, EsCar);
                procedure1.setDouble(12, array.get(i).getPrecio_Venta());
                procedure1.setDouble(13, array.get(i).getDescuento());
                procedure1.setDouble(14, array.get(i).getPrecio_Venta_Final());
                procedure1.setDouble(15, array.get(i).getTotal());
                procedure1.setString(16, entidadGuia.getId_Botica());
                procedure1.setString(17, entidadGuia.getId_Proveedor());
                rs = procedure1.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    resul = new Movimientos(2, array.get(i).getId_Producto().getIdProducto(), serie + doc);//NO HAY STOCK
                    conex.rollback();
                    return resul;
                }
            }

            rs.close();
            conex.commit();
            resul = new Movimientos(0, "", doc);

        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase y002GuiasData :" + ex.getMessage().toString());
                conex.rollback();
                resul = new Movimientos(1, "", doc);
                return resul;
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
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

    public Movimientos CargarDetalleGuia1(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie, String idventa, String fechavta) {

        String doc = null;
        Movimientos resul = null;
        String nomCargoDetalle="";
        
        if (!entidadGuia.getId_TipoAlmacen().equals("Trastienda")){                 
                nomCargoDetalle = "CARGAR_DETALLE";
            }else{                
                nomCargoDetalle = "CARGAR_DETALLE_TRASTIENDA";
            }

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CARGAR1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("Cargadora", entidadGuia.getMovimiento());
            procedure.setString("Documento", entidadGuia.getNumero_Documento());
            procedure.setString("TipoAlmac", entidadGuia.getId_TipoAlmacen());
            procedure.setString("BoticaEnvia", entidadGuia.getId_Botica());
            procedure.setInt("Personal", entidadGuia.getId_Personal());
            procedure.setString("fechaDocto", entidadGuia.getFecha_Documento());
            procedure.setString("OBs", Obns);
            procedure.setInt("Tipo", tipo);
            procedure.setString("FReg", entidadGuia.getFecha_Recepcion());
            procedure.setInt("EsCar", 1);
            procedure.setDouble("TOTAL", entidadGuia.getTotal());
            procedure.setString("IDPROVEEDOR", entidadGuia.getId_Proveedor());
            procedure.setString("MISERIE", serie);
            procedure.setString("MINUMERO", entidadGuia.getNumero());
            procedure.setString("IDVENTA", idventa);
            procedure.setString("FECHAVTA", fechavta);
            rs = procedure.executeQuery();
            rs.next();
            doc = rs.getString(1);

            CallableStatement procedure1;


            for (int i = 0; i < array.size(); i++) {

                procedure1 = conex.prepareCall("{ call " + nomCargoDetalle +"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure1.setString(1, entidadGuia.getMovimiento());
                procedure1.setString(2, doc);
                procedure1.setString(3, entidadGuia.getId_Botica());
                procedure1.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure1.setInt(5, entidadGuia.getId_Personal());
                procedure1.setString(6, entidadGuia.getFecha_Documento());
                procedure1.setString(7, array.get(i).getId_Producto().getIdProducto());
                procedure1.setInt(8, array.get(i).getUnidad());
                procedure1.setInt(9, array.get(i).getFraccion());
                procedure1.setInt(10, tipo);
                procedure1.setInt(11, EsCar);
                procedure1.setDouble(12, array.get(i).getPrecio_Venta());
                procedure1.setDouble(13, array.get(i).getDescuento());
                procedure1.setDouble(14, array.get(i).getPrecio_Venta_Final());
                procedure1.setDouble(15, array.get(i).getTotal());
                procedure1.setString(16, entidadGuia.getId_Botica());
                procedure1.setString(17, entidadGuia.getId_Proveedor());
                rs = procedure1.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    resul = new Movimientos(2, array.get(i).getId_Producto().getIdProducto(), serie + doc);//NO HAY STOCK
                    conex.rollback();
                    return resul;
                }
            }

            rs.close();
            conex.commit();
            resul = new Movimientos(0, "", doc);

        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase y002GuiasData :" + ex.getMessage().toString());
                conex.rollback();
                resul = new Movimientos(1, "", doc);
                return resul;
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
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

    public Movimientos CargarDetalleGuiaCargo(Guias entidadGuia, List<Movimiento_Detalle> array, int tipo, int EsCar, String Obns, int mitipo, String serie,Proforma entidadproformaCliente) {

        String doc = null;
        String idproforma = null;
        Movimientos resul = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CARGAR_NUEVO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("Cargadora", entidadGuia.getMovimiento());
            procedure.setString("Documento", entidadGuia.getNumero_Documento());
            procedure.setString("TipoAlmac", entidadGuia.getId_TipoAlmacen());
            procedure.setString("BoticaEnvia", entidadGuia.getId_Botica());
            procedure.setInt("Personal", entidadGuia.getId_Personal());
            procedure.setString("fechaDocto", entidadGuia.getFecha_Documento());
            procedure.setString("OBs", Obns);
            procedure.setInt("Tipo", tipo);
            procedure.setString("FReg", entidadGuia.getFecha_Recepcion());
            procedure.setInt("EsCar", 1);
            procedure.setDouble("TOTAL", entidadGuia.getTotal());
            procedure.setString("IDPROVEEDOR", entidadGuia.getId_Proveedor());
            procedure.setString("MISERIE", serie);
            procedure.setString("MINUMERO", entidadGuia.getNumero());
            procedure.setString("NOMBRECLIENTE", entidadproformaCliente.getNombre_RazonSocial());
            procedure.setString("RUC", entidadproformaCliente.getRUC());
            procedure.setString("DNI", entidadproformaCliente.getDNI());
            procedure.setInt("TIPPAGO", entidadproformaCliente.getId_TipoPago());
            procedure.setInt("TIPVTA", entidadproformaCliente.getId_Tipo_Venta());
            procedure.setDouble("IGV", entidadproformaCliente.getIGV());

            rs = procedure.executeQuery();
            rs.next();
            doc = rs.getString(1);
            idproforma = rs.getString(2);

            CallableStatement procedure1;

            String inserta = "INSERT INTO PROFORMA_DETALLE (Id_Proforma, Id_Producto, UNIDAD, FRACCION, Precio_Venta, Descuento, PVX, Total, orden_producto,Id_Producto_Recarga) VALUES ";

            for (int i = 0; i < array.size(); i++) {

                procedure1 = conex.prepareCall("{ call CARGAR_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure1.setString(1, entidadGuia.getMovimiento());
                procedure1.setString(2, doc);
                procedure1.setString(3, entidadGuia.getId_Botica());
                procedure1.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure1.setInt(5, entidadGuia.getId_Personal());
                procedure1.setString(6, entidadGuia.getFecha_Documento());
                procedure1.setString(7, array.get(i).getId_Producto().getIdProducto());
                procedure1.setInt(8, array.get(i).getUnidad());
                procedure1.setInt(9, array.get(i).getFraccion());
                procedure1.setInt(10, tipo);
                procedure1.setInt(11, EsCar);
                procedure1.setDouble(12, array.get(i).getPrecio_Venta());
                procedure1.setDouble(13, array.get(i).getDescuento());
                procedure1.setDouble(14, array.get(i).getPrecio_Venta_Final());
                procedure1.setDouble(15, array.get(i).getTotal());
                procedure1.setString(16, entidadGuia.getId_Botica());
                procedure1.setString(17, entidadGuia.getId_Proveedor());
                rs = procedure1.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    resul = new Movimientos(2, array.get(i).getId_Producto().getIdProducto(), serie + doc);//NO HAY STOCK
                    conex.rollback();
                    return resul;
                }
            
            //inserta += "('" + idproforma + "','" + array.get(i).getId_Producto().getIdProducto() + "','" + String.valueOf(array.get(i).getUnidad()) + "','" + String.valueOf(array.get(i).getFraccion()) + "','" + String.valueOf(array.get(i).getPrecio_Venta()) + "','" + String.valueOf(array.get(i).getDescuento()) + "','" + array.get(i).getPrecio_Venta_Final() + "','" + array.get(i).getTotal() + "','" + listaproductos.get(i).getOrdenProducto() + "','" + listaproductos.get(i).getidOperadorRec() + "')" + ",";
            inserta += "('" + idproforma + "','" + array.get(i).getId_Producto().getIdProducto() + "','" + String.valueOf(array.get(i).getUnidad()) + "','" + String.valueOf(array.get(i).getFraccion()) + "','" + String.valueOf(array.get(i).getPrecio_Venta()) + "','" + String.valueOf(array.get(i).getDescuento()) + "','" + array.get(i).getPrecio_Venta_Final() + "','" + array.get(i).getTotal() + "','" + 1 + "','" + 0 + "')" + ",";            
            }
            inserta = inserta.substring(0, inserta.length() - 1);
            st = conex.prepareStatement(inserta);
            st.executeUpdate();


            rs.close();
            conex.commit();
            resul = new Movimientos(0, "", doc);

        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase y002GuiasData :" + ex.getMessage().toString());
                conex.rollback();
                resul = new Movimientos(1, "", doc);
                return resul;
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
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

    public Movimientos DescargarDetalleGuia(Guias entidadGuia, List<Movimiento_Detalle> array, String Obns, int opc) {

        Movimientos obj = null;
        String docu = null;
        
        String nomDesCargo = "";
        String nomDesCargoDetalle = ""; 

        try {

            if (!entidadGuia.getId_TipoAlmacen().equals("Trastienda")){ 
                nomDesCargo = "DESCARGAR";
                nomDesCargoDetalle = "DESCARGAR_DETALLE";
            }else{
                nomDesCargo = "DESCARGAR_TRASTIENDA";
                nomDesCargoDetalle = "DESCARGAR_DETALLE_TRASTIENDA";
            }
            
            conex = new ConexionPool().getConnection(); //
            conex.setAutoCommit(false);
            CallableStatement procedure1 = conex.prepareCall("{ call " + nomDesCargo + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure1.setString(1, entidadGuia.getMovimiento());
            procedure1.setString(2, entidadGuia.getNumero_Documento());
            procedure1.setString(3, entidadGuia.getId_Botica());
            procedure1.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure1.setInt(5, entidadGuia.getId_Personal());
            procedure1.setString(6, entidadGuia.getFecha_Documento());
            procedure1.setString(7, Obns);
            procedure1.setString(8, entidadGuia.getFecha_Recepcion());
            procedure1.setDouble(9, entidadGuia.getTotal());
            procedure1.setString(10, entidadGuia.getId_Proveedor());
            procedure1.setString(11, "");
            procedure1.setString(12, "");
            procedure1.setInt(13,0);
            procedure1.setInt(14, opc);
            rs = procedure1.executeQuery();
            rs.next();
            docu = rs.getString(1);

            CallableStatement procedure;

            for (int i = 0; i < array.size(); i++) { //

                procedure = conex.prepareCall("{ call " + nomDesCargoDetalle + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setString(1, entidadGuia.getMovimiento());
                procedure.setString(2, docu);
                procedure.setString(3, entidadGuia.getId_Botica());
                procedure.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure.setInt(5, entidadGuia.getId_Personal());
                procedure.setString(6, entidadGuia.getNumero_Documento());
                procedure.setString(7, array.get(i).getId_Producto().getIdProducto());
                procedure.setInt(8, array.get(i).getUnidad());
                procedure.setInt(9, array.get(i).getFraccion());
                procedure.setDouble(10, array.get(i).getPrecio_Venta());
                procedure.setDouble(11, array.get(i).getDescuento());
                procedure.setDouble(12, array.get(i).getPrecio_Venta_Final());
                procedure.setDouble(13, array.get(i).getTotal());
                procedure.setString(14, entidadGuia.getId_Botica());
                procedure.setString(15, entidadGuia.getId_Proveedor());
                procedure.setString(16, "");
                procedure.setString(17, "");
                procedure.setString(18, "");
                rs = procedure.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, array.get(i).getId_Producto().getIdProducto(), docu);//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, "", docu);


        } catch (Exception ex) {
            try {
                System.out.println("HIZE UN rollback :" + ex.getMessage().toString());
                conex.rollback();
                obj = new Movimientos(1, "", docu);
                return obj;
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
            }

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;

    }

    public Movimientos DescargarDetalleGuiaDescargo(Guias entidadGuia, List<Movimiento_Detalle> array, String Obns, Proforma entidadproformaCliente) {

        Movimientos obj = null;
        String docu = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure1 = conex.prepareCall("{ call DESCARGAR_NUEVO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure1.setString(1, entidadGuia.getMovimiento());
            procedure1.setString(2, entidadGuia.getNumero_Documento());
            procedure1.setString(3, entidadGuia.getId_Botica());
            procedure1.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure1.setInt(5, entidadGuia.getId_Personal());
            procedure1.setString(6, entidadGuia.getFecha_Documento());
            procedure1.setString(7, Obns);
            procedure1.setString(8, entidadGuia.getFecha_Recepcion());
            procedure1.setDouble(9, entidadGuia.getTotal());
            procedure1.setString(10, entidadGuia.getId_Proveedor());
            procedure1.setString(11, "");
            procedure1.setString(12, "");           
            procedure1.setString(13, entidadproformaCliente.getNombre_RazonSocial());
            procedure1.setString(14, entidadproformaCliente.getRUC());
            procedure1.setString(15, entidadproformaCliente.getDNI());
            procedure1.setInt(16, entidadproformaCliente.getId_TipoPago());
            procedure1.setInt(17, entidadproformaCliente.getId_Tipo_Venta());
            rs = procedure1.executeQuery();
            rs.next();
            docu = rs.getString(1);

            CallableStatement procedure;

            for (int i = 0; i < array.size(); i++) {

                procedure = conex.prepareCall("{ call DESCARGAR_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setString(1, entidadGuia.getMovimiento());
                procedure.setString(2, docu);
                procedure.setString(3, entidadGuia.getId_Botica());
                procedure.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure.setInt(5, entidadGuia.getId_Personal());
                procedure.setString(6, entidadGuia.getNumero_Documento());
                procedure.setString(7, array.get(i).getId_Producto().getIdProducto());
                procedure.setInt(8, array.get(i).getUnidad());
                procedure.setInt(9, array.get(i).getFraccion());
                procedure.setDouble(10, array.get(i).getPrecio_Venta());
                procedure.setDouble(11, array.get(i).getDescuento());
                procedure.setDouble(12, array.get(i).getPrecio_Venta_Final());
                procedure.setDouble(13, array.get(i).getTotal());
                procedure.setString(14, entidadGuia.getId_Botica());
                procedure.setString(15, entidadGuia.getId_Proveedor());
                rs = procedure.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, array.get(i).getId_Producto().getIdProducto(), docu);//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, "", docu);


        } catch (Exception ex) {
            try {
                System.out.println("HIZE UN rollback :" + ex.getMessage().toString());
                conex.rollback();
                obj = new Movimientos(1, "", docu);
                return obj;
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
            }

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;

    }

    public int guiaCargada(Guias entidadGuia, int tipo, int EsCargo) {

        Integer resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GUIA_CARGADA(?,?,?,?,?,?,?,?,?) }");
            procedure.setString(1, entidadGuia.getMovimiento());
            procedure.setString(2, entidadGuia.getNumero_Documento());
            procedure.setString(3, entidadGuia.getId_Botica());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getFecha_Documento());
            procedure.setInt(7, tipo);
            procedure.setInt(8, EsCargo);
            procedure.setString(9, entidadGuia.getId_Proveedor());
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 0x01GuiasData :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("RAGE:" + ex.getMessage());
                }
            }
        }

        return resultado;
    }
    
    
    public boolean InsertaTmpProdGuia(List<Movimiento_Detalle> listProdGuia, int opc) {
        boolean resul = false;       

        try {            
            conex = new ConexionPool().getConnection();
            
            int verificalista = listProdGuia.size();
            if (verificalista==0){
                CallableStatement procedure = conex.prepareCall("{ call INSERTA_TMPPRODGUIA ('" + 0 + "','" + "0" + "','" + 0 + "','" + 0 + "','" + 0.0 + "','" + 0.0 + "','" + 0.0 + "','" + 0.0 + "','" + "0" + "','" + "0" + "','" + "0" + "','" + opc + "','" + "0" + "','" + "0" + "','" + "0" + "') }");
                rs = procedure.executeQuery();
            }
            
            for (int i = 0; i < listProdGuia.size(); i++) {
                
                CallableStatement procedure = conex.prepareCall("{ call INSERTA_TMPPRODGUIA ('" + i + "','" + listProdGuia.get(i).getId_Producto().getIdProducto().toString() + "','" + listProdGuia.get(i).getUnidad() + "','" + listProdGuia.get(i).getFraccion() + "','" + listProdGuia.get(i).getPrecio_Venta() + "','" + listProdGuia.get(i).getDescuento() + "','" + listProdGuia.get(i).getPrecio_Venta_Final() + "','" + listProdGuia.get(i).getTotal() + "','" + listProdGuia.get(i).getCompuesto() + "','" + listProdGuia.get(i).getLab() + "','" + listProdGuia.get(i).getDescripcion() + "','" + opc + "','" + listProdGuia.get(i).getcodbarras()+ "','" + listProdGuia.get(i).getFecvcto()+ "','" + listProdGuia.get(i).getRS()+ "') }");
                rs = procedure.executeQuery(); 
            }
           
            rs.close();
            resul = true;

        } catch (OutOfMemoryError ex) {
            System.out.println("Error en INSERTA_TMPPRODGUIA" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
    
        
    public List<Guias> recuperaProdGuia(int limite, String docu,int opc) {
        try {
            listaProdGuias.clear();
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_PRODGUIA(?,?,?) }");
            procedure.setInt(1, limite);
            procedure.setString(2, docu);
            procedure.setInt(3, opc);
            
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaProdGuias.add(new Guias(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase RECUPERA_PRODGUIA :" + ex.getMessage().toString()); 
            listaProdGuias.add(new Guias(-1,"",0,0,0,0,0,0,"","","","","",""));
            
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProdGuias;

    }
    
    public List<Guias> ActGuia(int limite, String docu,int opc) {
        try {
            listaProdGuias.clear();
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZA_PRODGUIA(?,?,?) }");
            procedure.setInt(1, limite);
            procedure.setString(2, docu);
            procedure.setInt(3, opc);
            
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaProdGuias.add(new Guias(
                        rs.getInt(1)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ACTGUIA :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProdGuias;

    }

    public int VerificaAnulacion(String idventa,String fechavta) {

        Integer resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ANULACIONDOCUMENTO(?,?) }");
            procedure.setString(1, idventa);
            procedure.setString(2, fechavta);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase 0x01GuiasData :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("RAGE:" + ex.getMessage());
                }
            }
        }

        return resultado;
    }

    public List<Guias> RecuperaDetalleMovimiento(String fechaDocumento, String numeroDocumento, String tipoMovimiento) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DETALLE_MOVIMIENTO(?,?,?) }");
            procedure.setString(1, fechaDocumento);
            procedure.setString(2, numeroDocumento);
            procedure.setString(3, tipoMovimiento);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaGuias.add(new Guias(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData02 :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaGuias;


    }

    public void GenerarDBFEnvio(Guias entidadGuia, Producto entidadProductos) {
    }

    public String GeneraNuevoNumero(String Movimiento, String alma) {

        String docto = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GeneraNuevoNumero(?,?) }");
            procedure.setString(1, Movimiento);
            procedure.setString(2, alma);
            rs = procedure.executeQuery();

            while (rs.next()) {
                docto = rs.getString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error GeneraNuevoNumero :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("ex.getMessage()" + ex.getMessage());
                }
            }
        }

        return docto;

    }
    public String RecuperaTipoOperacion(String Movimiento) {

        String idoper = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RecuperaTipoOperacion(?) }");
            procedure.setString(1, Movimiento);
            rs = procedure.executeQuery();

            while (rs.next()) {
                idoper = rs.getString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error GeneraNuevoNumero :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("ex.getMessage()" + ex.getMessage());
                }
            }
        }

        return idoper;

    }

    public List<Guias> RecuperaTodosLosMovimientos(int FILTRO, String idboti, String fecha1, String fecha2, String doc) {

        try {


            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MOVIMIENTOS_INVENTARIOS(?,?,?,?,?) }");
            procedure.setInt("FILTRO", FILTRO);
            procedure.setString("IDBOTICA", idboti);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("FECHA2", fecha2);
            procedure.setString("DOC", doc);
            rs = procedure.executeQuery();
            listaGuias.removeAll(listaGuias);

            while (rs.next()) {
                listaGuias.add(new Guias(
                        rs.getString("Botica"),
                        rs.getString("Almacen"),
                        rs.getString("miproveedor"),
                        rs.getString("Movimiento"),
                        rs.getString("Documento"),
                        rs.getString("Fecha"),
                        rs.getString("Observaciones"),
                        rs.getString("Apellido"),
                        rs.getString("Id_TipoMovimiento")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData02 :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaGuias;


    }

    public List<Guias> RecuperaTodosLosMovimientos_Quim(int FILTRO, String idboti, String fecha1, String fecha2, String doc) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_MOVIMIENTOS_INVENTARIOS_QUIM(?,?,?,?,?) }");
            procedure.setInt("FILTRO", FILTRO);
            procedure.setString("IDBOTICA", idboti);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("FECHA2", fecha2);
            procedure.setString("DOC", doc);
            rs = procedure.executeQuery();
            listaGuias.removeAll(listaGuias);

            while (rs.next()) {
                listaGuias.add(new Guias(
                        rs.getString("Botica"),
                        rs.getString("Almacen"),
                        rs.getString("Movimiento"),
                        rs.getString("proveedor"),
                        rs.getString("Documento"),
                        rs.getString("Serie"),
                        rs.getString("Numero"),
                        rs.getString("Fecha"),
                        rs.getString("Apellido"),
                        rs.getString("Nombre")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData02 :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaGuias;


    }

    public Movimientos DescargarGuiaBotica(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo, double totalXguia, int opc) {

        Movimientos obj = null;
        String docmuento;

        try {

            conex = new ConexionPool().getConnection();  //
            CallableStatement procedure = conex.prepareCall("{ call DESCARGAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            conex.setAutoCommit(false);
            procedure.setString(1, entidadGuia.getMovimiento());
            procedure.setString(2, entidadGuia.getNumero_Documento());
            procedure.setString(3, entidadGuia.getId_Botica());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getFecha_Documento());
            procedure.setString(7, Obns);
            procedure.setString(8, entidadGuia.getFecha_Recepcion());            
            procedure.setDouble(9, totalXguia);
            procedure.setString(10, entidadGuia.getId_Proveedor());
            procedure.setString(11, entidadGuia.getSerie());
            procedure.setString(12, entidadGuia.getNumero());
            procedure.setInt(13, entidadGuia.getIdtransp()); //nuevo
            procedure.setInt(14, opc); //nuevo
            rs = procedure.executeQuery();
            rs.next();
            docmuento = rs.getString(1);

            for (int i = 0; i < lstDescargo.size(); i++) {   //
                CallableStatement procedure2 = conex.prepareCall("{ call DESCARGAR_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure2.setString(1, entidadGuia.getMovimiento());
                procedure2.setString(2, entidadGuia.getNumero_Documento());
                procedure2.setString(3, entidadGuia.getId_Botica());
                procedure2.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure2.setInt(5, entidadGuia.getId_Personal());
                procedure2.setString(6, entidadGuia.getNumero_Documento());
                procedure2.setString(7, lstDescargo.get(i).getId_Producto().getIdProducto());
                procedure2.setInt(8, lstDescargo.get(i).getUnidad());
                procedure2.setInt(9, lstDescargo.get(i).getFraccion());
                procedure2.setDouble(10, lstDescargo.get(i).getPrecio_Venta());
                procedure2.setDouble(11, lstDescargo.get(i).getDescuento());
                procedure2.setDouble(12, lstDescargo.get(i).getPrecio_Venta_Final());
                procedure2.setDouble(13, lstDescargo.get(i).getTotal());
                procedure2.setString(14, entidadGuia.getId_Botica());
                procedure2.setString(15, entidadGuia.getId_Proveedor());
                procedure2.setString(16, lstDescargo.get(i).getcodbarras()); //nuevo
                procedure2.setString(17, lstDescargo.get(i).getFecvcto());   //nuevo
                procedure2.setString(18, lstDescargo.get(i).getRS());   //nuevo
                rs = procedure2.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, lstDescargo.get(i).getId_Producto().getIdProducto(), entidadGuia.getId_Proveedor());//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, docmuento, entidadGuia.getId_Proveedor());//NO HAY STOCK


        } catch (Exception ex) {
            try {
                obj = new Movimientos(1, "", entidadGuia.getId_Proveedor());//NO HAY STOCK
                conex.rollback();
                System.out.println("Error CapaDatos clase GuiasData01 :" + ex.getMessage().toString());
                lstDescargo.removeAll(lstDescargo);

            } catch (SQLException ex1) {
                System.out.println("HIZE UN ROLLBACK " + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;


    }
    
    public Movimientos DescargarGuiaClientes(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo, double totalXguia) {

        Movimientos obj = null;
        String docmuento;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DESCARGAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            conex.setAutoCommit(false);
            procedure.setString(1, entidadGuia.getMovimiento());
            procedure.setString(2, entidadGuia.getNumero_Documento());
            procedure.setString(3, entidadGuia.getId_Botica());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getFecha_Documento());
            procedure.setString(7, Obns);
            procedure.setString(8, entidadGuia.getFecha_Recepcion());
            //procedure.setDouble(9, entidadGuia.getTotal());
            procedure.setDouble(9, totalXguia);
            procedure.setString(10, entidadGuia.getId_Proveedor());
            procedure.setString(11, entidadGuia.getSerie());
            procedure.setString(12, entidadGuia.getNumero());
            procedure.setInt(13, entidadGuia.getIdcli());
            procedure.setInt(14, entidadGuia.getIdtransp());            
            procedure.setString(15, entidadGuia.getNOrden());
            procedure.setString(16, entidadGuia.getNCajas());
            procedure.setString(17, entidadGuia.getId_Venta());
            
            rs = procedure.executeQuery();
            rs.next();
            docmuento = rs.getString(1);

            for (int i = 0; i < lstDescargo.size(); i++) {
                CallableStatement procedure2 = conex.prepareCall("{ call DESCARGAR_DETALLE_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure2.setString(1, entidadGuia.getMovimiento());
                procedure2.setString(2, entidadGuia.getNumero_Documento());
                procedure2.setString(3, entidadGuia.getId_Botica());
                procedure2.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure2.setInt(5, entidadGuia.getId_Personal());
                procedure2.setString(6, entidadGuia.getNumero_Documento());
                procedure2.setString(7, lstDescargo.get(i).getId_Producto().getIdProducto());
                procedure2.setInt(8, lstDescargo.get(i).getUnidad());
                procedure2.setInt(9, lstDescargo.get(i).getFraccion());
                procedure2.setDouble(10, lstDescargo.get(i).getPrecio_Venta());
                procedure2.setDouble(11, lstDescargo.get(i).getDescuento());
                procedure2.setDouble(12, lstDescargo.get(i).getPrecio_Venta_Final());
                procedure2.setDouble(13, lstDescargo.get(i).getTotal());
                procedure2.setString(14, entidadGuia.getId_Botica());
                procedure2.setString(15, entidadGuia.getId_Proveedor());
                procedure2.setString(16, lstDescargo.get(i).getcodbarras());
                procedure2.setString(17, lstDescargo.get(i).getFecvcto());
                procedure2.setString(18, lstDescargo.get(i).getRS());
                rs = procedure2.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, lstDescargo.get(i).getId_Producto().getIdProducto(), entidadGuia.getId_Proveedor());//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, docmuento, entidadGuia.getId_Proveedor());//NO HAY STOCK


        } catch (Exception ex) {
            try {
                obj = new Movimientos(1, "", entidadGuia.getId_Proveedor());//NO HAY STOCK
                conex.rollback();
                System.out.println("Error CapaDatos clase DESCARGAR_DETALLE_CLIENTE :" + ex.getMessage().toString());
                lstDescargo.removeAll(lstDescargo);

            } catch (SQLException ex1) {
                System.out.println("HIZE UN ROLLBACK " + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;


    }
    
        public Movimientos DescargarGuiaBoticaTrastienda(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo) {

        Movimientos obj = null;
        String docmuento;

        try {

            conex = new ConexionPool().getConnection(); // 
            CallableStatement procedure = conex.prepareCall("{ call DESCARGAR_TRASTIENDA(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            conex.setAutoCommit(false);
            procedure.setString(1, entidadGuia.getMovimiento());
            procedure.setString(2, entidadGuia.getNumero_Documento());
            procedure.setString(3, entidadGuia.getId_Botica());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getFecha_Documento());
            procedure.setString(7, Obns);
            procedure.setString(8, entidadGuia.getFecha_Recepcion());
            procedure.setDouble(9, entidadGuia.getTotal());
            procedure.setString(10, entidadGuia.getId_Proveedor());
            procedure.setString(11, entidadGuia.getSerie());
            procedure.setString(12, entidadGuia.getNumero());            
            procedure.setInt(13, 0); //nuevo
            procedure.setInt(14, 0); //nuevo
            rs = procedure.executeQuery();
            rs.next();
            docmuento = rs.getString(1);

            for (int i = 0; i < lstDescargo.size(); i++) { //
                CallableStatement procedure2 = conex.prepareCall("{ call DESCARGAR_DETALLE_TRASTIENDA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure2.setString(1, entidadGuia.getMovimiento());
                procedure2.setString(2, entidadGuia.getNumero_Documento());
                procedure2.setString(3, entidadGuia.getId_Botica());
                procedure2.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure2.setInt(5, entidadGuia.getId_Personal());
                procedure2.setString(6, entidadGuia.getNumero_Documento());
                procedure2.setString(7, lstDescargo.get(i).getId_Producto().getIdProducto());
                procedure2.setInt(8, lstDescargo.get(i).getUnidad());
                procedure2.setInt(9, lstDescargo.get(i).getFraccion());
                procedure2.setDouble(10, lstDescargo.get(i).getPrecio_Venta());
                procedure2.setDouble(11, lstDescargo.get(i).getDescuento());
                procedure2.setDouble(12, lstDescargo.get(i).getPrecio_Venta_Final());
                procedure2.setDouble(13, lstDescargo.get(i).getTotal());
                procedure2.setString(14, entidadGuia.getId_Botica());
                procedure2.setString(15, entidadGuia.getId_Proveedor());                
                procedure2.setString(16, "");//nuevo
                procedure2.setString(17, ""); //nuevo
                procedure2.setString(18, ""); //nuevo
                
                rs = procedure2.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, lstDescargo.get(i).getId_Producto().getIdProducto(), entidadGuia.getId_Proveedor());//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, docmuento, entidadGuia.getId_Proveedor());//NO HAY STOCK


        } catch (Exception ex) {
            try {
                obj = new Movimientos(1, "", entidadGuia.getId_Proveedor());//NO HAY STOCK
                conex.rollback();
                System.out.println("Error CapaDatos clase GuiasData01 :" + ex.getMessage().toString());
                lstDescargo.removeAll(lstDescargo);

            } catch (SQLException ex1) {
                System.out.println("HIZE UN ROLLBACK " + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;


    }

    public Movimientos DescargarGuiaBoticaAdicional(Guias entidadGuia, String Obns, List<Movimiento_Detalle> lstDescargo, double totalXguia,int numult, int opc) {

        Movimientos obj = null;
        String docmuento;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DESCARGAR_ADICIONAL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            conex.setAutoCommit(false);
            procedure.setString(1, entidadGuia.getMovimiento());
            procedure.setString(2, entidadGuia.getNumero_Documento());
            procedure.setString(3, entidadGuia.getId_Botica());
            procedure.setString(4, entidadGuia.getId_TipoAlmacen());
            procedure.setInt(5, entidadGuia.getId_Personal());
            procedure.setString(6, entidadGuia.getFecha_Documento());
            procedure.setString(7, Obns);
            procedure.setString(8, entidadGuia.getFecha_Recepcion());
            //procedure.setDouble(9, entidadGuia.getTotal());
            procedure.setDouble(9, totalXguia);
            procedure.setString(10, entidadGuia.getId_Proveedor());
            procedure.setString(11, entidadGuia.getSerie());
            procedure.setString(12, entidadGuia.getNumero());
            procedure.setInt(13, entidadGuia.getIdtransp()); //nuevo
            procedure.setInt(14, numult); //nuevo
            procedure.setInt(15, opc); //nuevo
            
            rs = procedure.executeQuery();
            rs.next();
            docmuento = rs.getString(1);

            for (int i = 0; i < lstDescargo.size(); i++) {
                CallableStatement procedure2 = conex.prepareCall("{ call DESCARGAR_DETALLE_ADICIONAL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure2.setString(1, entidadGuia.getMovimiento());
                procedure2.setString(2, entidadGuia.getNumero_Documento());
                procedure2.setString(3, entidadGuia.getId_Botica());
                procedure2.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure2.setInt(5, entidadGuia.getId_Personal());
                procedure2.setString(6, entidadGuia.getNumero_Documento());
                procedure2.setString(7, lstDescargo.get(i).getId_Producto().getIdProducto());
                procedure2.setInt(8, lstDescargo.get(i).getUnidad());
                procedure2.setInt(9, lstDescargo.get(i).getFraccion());
                procedure2.setDouble(10, lstDescargo.get(i).getPrecio_Venta());
                procedure2.setDouble(11, lstDescargo.get(i).getDescuento());
                procedure2.setDouble(12, lstDescargo.get(i).getPrecio_Venta_Final());
                procedure2.setDouble(13, lstDescargo.get(i).getTotal());
                procedure2.setString(14, entidadGuia.getId_Botica());
                procedure2.setString(15, entidadGuia.getId_Proveedor());
                procedure2.setString(16, lstDescargo.get(i).getcodbarras()); //nuevo
                procedure2.setString(17, lstDescargo.get(i).getFecvcto());   //nuevo
                procedure2.setString(18, lstDescargo.get(i).getId_Producto().getDescripcion());   //nuevo
                rs = procedure2.executeQuery();
                rs.next();

                if (rs.getInt(1) < 0) {
                    obj = new Movimientos(2, lstDescargo.get(i).getId_Producto().getIdProducto(), entidadGuia.getId_Proveedor());//NO HAY STOCK
                    conex.rollback();
                    return obj;
                }
            }

            conex.commit();
            obj = new Movimientos(0, docmuento, entidadGuia.getId_Proveedor());//NO HAY STOCK


        } catch (Exception ex) {
            try {
                obj = new Movimientos(1, "", entidadGuia.getId_Proveedor());//NO HAY STOCK
                conex.rollback();
                System.out.println("Error CapaDatos clase GuiasData01 :" + ex.getMessage().toString());
                lstDescargo.removeAll(lstDescargo);

            } catch (SQLException ex1) {
                System.out.println("HIZE UN ROLLBACK " + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return obj;


    }
    public boolean CargarGuiaBotica(Guias entidadGuia, String Obns, int tipo, List<Movimiento_Detalle> listProdCargo, int esCar) {

        boolean valor = false;
        String nomCargo = "";
        String nomCargoDetalle = "";        

        try {
            /*Guardando Cabecera*/
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            if (!entidadGuia.getId_TipoAlmacen().equals("Trastienda")){ 
                nomCargo = "CARGAR";
                nomCargoDetalle = "CARGAR_DETALLE";
            }else{
                nomCargo = "CARGAR_TRASTIENDA";
                nomCargoDetalle = "CARGAR_DETALLE_TRASTIENDA";
            }              
           
            CallableStatement procedure = conex.prepareCall("{ call " + nomCargo + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("Cargadora", entidadGuia.getMovimiento());
            procedure.setString("Documento", entidadGuia.getNumero_Documento());
            procedure.setString("BoticaEnvia", entidadGuia.getId_Botica());
            procedure.setString("TipoAlmac", entidadGuia.getId_TipoAlmacen());
            procedure.setInt("Personal", entidadGuia.getId_Personal());
            procedure.setString("fechaDocto", entidadGuia.getFecha_Documento());
            procedure.setString("OBs", Obns);
            procedure.setInt("Tipo", tipo);
            procedure.setString("FReg", entidadGuia.getFecha_Recepcion());
            procedure.setInt("EsCar", 1);
            procedure.setDouble("TOTAL", entidadGuia.getTotal());
            procedure.setString("IDPROVEEDOR", entidadGuia.getId_Proveedor());
            procedure.setString("MISERIE", entidadGuia.getSerie());
            procedure.setString("MINUMERO", entidadGuia.getNumero());
            procedure.execute();

            /*Cargando Detalle*/
            CallableStatement procedure2 = null;

            for (int x = 0; x < listProdCargo.size(); x++) {
                procedure2 = conex.prepareCall("{ call " + nomCargoDetalle + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure2.setString(1, entidadGuia.getMovimiento());
                procedure2.setString(2, entidadGuia.getNumero_Documento());
                procedure2.setString(3, entidadGuia.getId_Botica());
                procedure2.setString(4, entidadGuia.getId_TipoAlmacen());
                procedure2.setInt(5, entidadGuia.getId_Personal());
                procedure2.setString(6, entidadGuia.getFecha_Documento());
                procedure2.setString(7, listProdCargo.get(x).getId_Producto().getIdProducto());
                procedure2.setInt(8, listProdCargo.get(x).getUnidad());
                procedure2.setInt(9, listProdCargo.get(x).getFraccion());
                procedure2.setInt(10, tipo);
                procedure2.setInt(11, esCar);
                procedure2.setDouble(12, 0);
                procedure2.setDouble(13, 0);
                procedure2.setDouble(14, 0);
                procedure2.setDouble(15, 0);
                procedure2.setString(16, entidadGuia.getId_Botica());
                procedure2.setString(17, entidadGuia.getId_Proveedor());
                procedure2.execute();
            }
            
            

            conex.commit();
            valor = true;
            rs.close();

        } catch (Exception ex) {
            try {
                conex.rollback();
                valor = false;
                System.out.println("Error CapaDatos clase CargarGuiaBotica :" + ex.getMessage().toString());
            } catch (SQLException ex1) {
                System.out.println("Error CapaDatos clase CargarGuiaBotica :" + ex.getMessage().toString());
            }

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return valor;

    }

    public int DiasCargo(String idbotica, int idpersonal) {

        Integer resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_FECHACARGO(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDPERSONAL", idpersonal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GuiasData :" + ex.getMessage().toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resultado;
    }
}
