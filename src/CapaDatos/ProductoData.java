package CapaDatos;

import CapaLogica.LogicaFechaHora;
import entidad.Descuento;
import entidad.Familias;
import entidad.Genericos;
import entidad.Laboratorios;
import entidad.ListaDetalles;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import entidad.TipoPrecio;
import entidad.Tipo_Producto;
import entidad.Varios;
import entidad.Venta;
import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import sistemanortfarma.OpcionesMenu;
import entidad.CABECERA;

public class ProductoData {

    Connection conex;
    ResultSet rs,rstg;
    Statement stm;
    private String cant;
    private String resultEsGratis;
    private String resultNoMostrar;
    private String resultEsGratisPromo;
    private int personal;
    private String tipo;
    private String valor;
    private String campoCabecera;
    private String campoDetalle;
    private String empaque;
    private ConexionPool db;
    private ResultSet rs_pool;
    List<ProductosPrecios> listaproductos = new ArrayList<ProductosPrecios>();
    List<ProductosPrecios> listaDatos = new ArrayList<ProductosPrecios>();
    List<Producto> listaproducto = new ArrayList();
    List<ProductosPrecios> listaproductos_detalle = new ArrayList<ProductosPrecios>();
    List<ProductosPrecios> listaDatosBotiquin = new ArrayList<ProductosPrecios>();
    List<ProductosPrecios> listastock = new ArrayList<ProductosPrecios>();
    List<Varios> Stks = new ArrayList<Varios>();
    List<Genericos> listagenerico = new ArrayList<Genericos>();
    LogicaFechaHora objFecha = new LogicaFechaHora();

    public ProductoData() {
        db = new ConexionPool();
    }

    public double VerificaIGVProducto(String idproducto) {
        double resul = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Verifica_IGV_Producto (?) }");
            procedure.setString(1, idproducto);
            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getDouble("IGV_Exonerado");
            rs.close();
            return resul;

        } catch (Exception ex) {
            System.out.println("Error en CAPA DE DATOS METODO VerificaIGVProducto " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }
        return resul;

    }

    public boolean AgregaProductoEnvio(List<ProductosPrecios> obj) throws SQLException {
        CallableStatement procedure;
        boolean resul = false;

        try {
          
            conex = new ConexionPool().getConnection();
            
            for (Integer in = 0; in < obj.size(); in++) {
                procedure = conex.prepareCall("{ call AGREGA_PRODUCTO_ENVIO (?,?,?,?,?,?,?,?) }");
                procedure.setString("vId_Producto", obj.get(in).getProductoBotica().getProducto().getIdProducto());
                procedure.setString("vId_Laboratorio", obj.get(in).getProductoBotica().getProducto().getLaboratorio().getId_Lab());
                procedure.setString("vDescripcion", obj.get(in).getProductoBotica().getProducto().getDescripcion());
                procedure.setString("vEmpaque", obj.get(in).getProductoBotica().getProducto().getMiEmpaque());
                procedure.setDouble("vIGV_Exonerado", obj.get(in).getProductoBotica().getProducto().getIGV_Exonerado());
                procedure.setDouble("PV", obj.get(in).getPrecio_Venta());
                procedure.setDouble("DESCTO", obj.get(in).getDescuento_Venta());
                procedure.setString("CODTIP", obj.get(in).getProductoBotica().getProducto().getTipoProducto().getId_Tipo_Producto());
                procedure.executeQuery();
            }

            resul = true;

        } catch (Exception e) {
            System.out.println("ERROR PRODUCTO AgregaProductoEnvio" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }

        return resul;

    }

    public String ActualizaProductoEnvio(List<ProductosPrecios> ListaProductoX, String IP, Integer fila) throws SQLException {

        String cadena = "";
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            for (Integer inicio = 0; inicio < ListaProductoX.size(); inicio++) {
                procedure = conex.prepareCall("{ call ACTUALIZAR_PRODUCTO_ENVIO (?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setString("vId_Producto", ListaProductoX.get(inicio).getProductoBotica().getProducto().getIdProducto());
                procedure.setString("vId_Laboratorio", ListaProductoX.get(inicio).getProductoBotica().getProducto().getLaboratorio().getId_Lab());
                procedure.setString("vId_Familia", ListaProductoX.get(inicio).getProductoBotica().getProducto().getIdFamilia().getId_Familia());
                procedure.setString("vId_Generico", ListaProductoX.get(inicio).getProductoBotica().getProducto().getIdGenerico().getId_Generico());
                procedure.setString("vDescripcion", ListaProductoX.get(inicio).getProductoBotica().getProducto().getDescripcion());
                procedure.setString("vEmpaque", ListaProductoX.get(inicio).getProductoBotica().getProducto().getMiEmpaque());
                procedure.setDouble("vIGV_Exonerado", ListaProductoX.get(inicio).getProductoBotica().getProducto().getIGV_Exonerado());
                procedure.setDouble("vPrisal", ListaProductoX.get(inicio).getPrecio_Venta());
                procedure.setDouble("Dscto", ListaProductoX.get(inicio).getDescuento_Venta());
                procedure.setString("laboti", OpcionesMenu.getIdbotica());
                procedure.setDouble("precioBotica", ListaProductoX.get(inicio).getPrecioBotiquin());
                procedure.setString("CODTIP", ListaProductoX.get(inicio).getProductoBotica().getProducto().getTipoProducto().getId_Tipo_Producto());
                procedure.executeQuery();
            }

        } catch (Exception e) {
            System.out.println("error PRODUCTO:" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }

        return cadena;

    }

    public List<ProductosPrecios> ListarProductos_Promocion(String idbotica, int idgrupo, int idpromocion) {
        Laboratorios laboratorio;
        Producto producto;
        Productos_Botica productoBotica;
        ProductosPrecios productoPrecios;
        TipoPrecio tipoPrecio;
        listaproductos_detalle.removeAll(listaproductos_detalle);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_PROMOCION(?,?,?) }");
            procedure.setString(1, idbotica);
            procedure.setInt(2, idgrupo);
            procedure.setInt(3, idpromocion);
            rs = procedure.executeQuery();

            while (rs.next()) {
                laboratorio = new Laboratorios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                productoPrecios = new ProductosPrecios();
                tipoPrecio = new TipoPrecio();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                laboratorio.setDescripcion(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorio);
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoPrecios.setProductoBotica(productoBotica);
                productoPrecios.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecios.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecios.setTipoPrecio(tipoPrecio);
                listaproductos_detalle.add(productoPrecios);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos_detalle;

    }

    public List<ProductosPrecios> ListarProductos_Detalle(String idgenerico, String idbotica, String tipprecio) throws SQLException {
        Laboratorios laboratorio;
        Producto producto;
        Productos_Botica productoBotica;
        ProductosPrecios productoPrecios;
        TipoPrecio tipoPrecio;
        listaproductos_detalle.removeAll(listaproductos_detalle);
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PRODUCTOS_GENERICOS (?,?,?) }");
            procedure.setString(1, idgenerico);
            procedure.setString(2, idbotica);
            procedure.setString(3, tipprecio);
            rs = procedure.executeQuery();

            while (rs.next()) {

                laboratorio = new Laboratorios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                productoPrecios = new ProductosPrecios();
                tipoPrecio = new TipoPrecio();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                laboratorio.setDescripcion(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorio);
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoPrecios.setProductoBotica(productoBotica);
                productoPrecios.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecios.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecios.setTipoPrecio(tipoPrecio);
                listaproductos_detalle.add(productoPrecios);

            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA ListarProductos_Detalle :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos_detalle;

    }
    
    
    public List<ProductosPrecios> ListarDatosBotiquin(String idbotica, String prd) throws SQLException {       
        
        ProductosPrecios productoPrecios;
        
        listaDatosBotiquin.removeAll(listaDatosBotiquin);
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DATOS_BOTIQUIN (?,?) }");            
            procedure.setString(1, idbotica);
            procedure.setString(2, prd);
            rs = procedure.executeQuery();

            while (rs.next()) {                
                
                productoPrecios = new ProductosPrecios();
                
                productoPrecios.setEmpaque(rs.getInt("Empaque"));
                productoPrecios.setEsBotiquin(rs.getInt("Precio_Botiquin"));
                productoPrecios.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                
                
                listaDatosBotiquin.add(productoPrecios);

            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA LISTA_DATOS_BOTIQUIN :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaDatosBotiquin;

    }

    public List<ProductosPrecios> ListarProductos(String filtro, String idbotica, String tipprecio, int op, int opconstock) {
        listaproductos.removeAll(listaproductos);
        CallableStatement procedure,procedure_tg;
        Producto producto;
        Laboratorios laboratorio;
        Familias famila;
        String resultTG="0";
        Genericos genericos;
        TipoPrecio tipoPrecio;
        ProductosPrecios productoPrecio;
        Productos_Botica productoBotica;

        try {
            
            conex = new ConexionPool().getConnection();
          
            if (opconstock == 1) {
                procedure = conex.prepareCall("{ call LISTA_PRODUCTOS (?,?,?,?) }");
            }else if (opconstock == 6) {
                procedure = conex.prepareCall("{ call LISTA_PRODUCTOS (?,?,?,?) }");
            }else {
                procedure = conex.prepareCall("{ call LISTA_PRODUCTOS_TOTAL (?,?,?,?) }");
            }

            procedure.setString("FILTRO", filtro);
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("TIPPRECIO", tipprecio);
            procedure.setInt("OPCION", op);
            rs = procedure.executeQuery();

            while (rs.next()) {               
                producto = new Producto();
                tipoPrecio = new TipoPrecio();
                laboratorio = new Laboratorios();
                famila = new Familias();
                genericos = new Genericos();
                productoBotica = new Productos_Botica();
                productoPrecio = new ProductosPrecios();

                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                laboratorio.setId_Lab(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorio);
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                genericos.setId_Generico(rs.getString("GENERICO"));
                genericos.setDescripcionGenerico(rs.getString("DESGENERICO"));
                producto.setIdGenerico(genericos);
                famila.setDescripcion(rs.getString("FAMILIA"));
                producto.setIdFamilia(famila);
                producto.setBlister(rs.getInt("Blister"));
                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoBotica.settemperatura(rs.getString("temperatura"));
                productoBotica.setCodBarras(rs.getString("Codigo_Barras"));
                productoPrecio.setProductoBotica(productoBotica);
                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setDescuento_Adicional(rs.getDouble("Descuento_Adicional"));
                productoPrecio.setprecbotiquin(rs.getDouble("botiquin"));// botiquin,
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecio.setTipoPrecio(tipoPrecio);
                productoPrecio.setId_Producto_Grupo(rs.getInt("Id_Producto_Grupo"));
                productoPrecio.setGrupo_Id(rs.getInt("Id_Grupo"));
                productoPrecio.setId_Promocion(rs.getInt("Id_Promocion"));
                productoPrecio.setVprecBotiquin(rs.getString("Precio_Botiquin"));
                productoPrecio.setbotiquinfraccion(rs.getDouble("botiquinfraccion"));
                productoPrecio.setdscto01(rs.getDouble("dsctovta01"));
                productoPrecio.setdscto02(rs.getDouble("dsctovta02"));

                //if (resultTG.equals("0")){

                listaproductos.add(productoPrecio);
                //}

            }
            
            rs.close();
            rstg.close();

        } catch (Exception ex) {
            //System.out.println("ERROR CAPADATOS CLASE PRODUCTODATAx ListarProductos:" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos;

    }


    public List<ProductosPrecios> Listardsctogratuita(int Idgrup, int opc) {
        listaproductos.removeAll(listaproductos);
        CallableStatement procedure;        
        ProductosPrecios productoPrecio;
        

        try {

            conex = new ConexionPool().getConnection();

            procedure = conex.prepareCall("{ call dsctogratuita (?,?) }");
            procedure.setInt("PIdgrup", Idgrup);
            procedure.setInt("Popc", opc);



            rs = procedure.executeQuery();

            while (rs.next()) {
                
                productoPrecio = new ProductosPrecios();
                
                productoPrecio.setidproducto(rs.getString("Id_Producto"));

                listaproductos.add(productoPrecio);
                

            }

            rs.close();
            rstg.close();

        } catch (Exception ex) {
            //System.out.println("ERROR CAPADATOS CLASE PRODUCTODATAx ListarProductos:" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos;

    }

    public List<ProductosPrecios> ListarDatos(List<CABECERA> objDatos,String valor) {
        listaDatos.removeAll(listaDatos);
        CallableStatement procedure;
        List<ProductosPrecios> listaDatos = new ArrayList<ProductosPrecios>();

        try {

           conex = new ConexionPool().getConnection();            
           procedure = conex.prepareCall("{ call LISTA_XMLDATOS (?) }");

            procedure.setString("VALOR", valor);
            rs = procedure.executeQuery();

            while (rs.next()) {                
               listaDatos.add(
                        new ProductosPrecios(rs.getString("GRUPO"),
                                     rs.getString("POSICION"),
                                     rs.getString("TABOPEN"),
                                     rs.getString("CAMPO"),
                                     rs.getString("DEFECTO"),
                                     rs.getString("DATO"),
                                     rs.getString("CIERRE"),
                                     rs.getString("TABCLOSE"),
                                     rs.getString("VISUALIZA")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATAx LISTA_XMLDATOS:" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaDatos;

    }

    public List<ProductosPrecios> ListarProductosPromocionesPrecio(String filtro, String idbotica, String tipprecio, int op, int opconstock) {
        listaproductos.removeAll(listaproductos);
        CallableStatement procedure;
        Producto producto;
        Laboratorios laboratorio;
        Familias famila;
        Genericos genericos;
        TipoPrecio tipoPrecio;
        ProductosPrecios productoPrecio;
        Productos_Botica productoBotica;

        try {

            conex = new ConexionPool().getConnection();

            /*if (opconstock == 1) {
                procedure = conex.prepareCall("{ call LISTA_PRODUCTOS (?,?,?,?) }");
            } else {
                procedure = conex.prepareCall("{ call LISTA_PRODUCTOS_TOTAL (?,?,?,?) }");
            }*/
            procedure = conex.prepareCall("{ call LISTA_PRODUCTOSPROMOCIONESPRECIO (?,?,?,?) }");

            procedure.setString("FILTRO", filtro);
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("TIPPRECIO", tipprecio);
            procedure.setInt("OPCION", op);
            rs = procedure.executeQuery();

            while (rs.next()) {
                producto = new Producto();
                tipoPrecio = new TipoPrecio();
                laboratorio = new Laboratorios();
                famila = new Familias();
                genericos = new Genericos();
                productoBotica = new Productos_Botica();
                productoPrecio = new ProductosPrecios();

                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                laboratorio.setId_Lab(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorio);
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                genericos.setId_Generico(rs.getString("GENERICO"));
                genericos.setDescripcionGenerico(rs.getString("DESGENERICO"));
                producto.setIdGenerico(genericos);
                famila.setDescripcion(rs.getString("FAMILIA"));
                producto.setIdFamilia(famila);
                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoPrecio.setProductoBotica(productoBotica);
                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setDescuento_Adicional(rs.getDouble("Descuento_Adicional"));
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecio.setTipoPrecio(tipoPrecio);
                productoPrecio.setId_Producto_Grupo(rs.getInt("Id_Producto_Grupo"));
                productoPrecio.setGrupo_Id(rs.getInt("Id_Grupo"));
                productoPrecio.setId_Promocion(rs.getInt("Id_Promocion"));
                listaproductos.add(productoPrecio);

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA ListarProductospromoprecio:" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos;

    }
    
    public List<ProductosPrecios> ListarProductosCarDes(String filtro, String IDBOTICA, int opcion) {

        List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosCarDes (?,?,?) }");
            procedure.setString("Buscador", filtro + "%");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setLaboratorio(laboratorio);

                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));

                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setProductoBotica(productoBotica);

                listaProductos.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosCarDes:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProductos;

    }
    
    public List<ProductosPrecios> ListarProductosCarDes1(String filtro, String IDBOTICA, int opcion, String tipo,int aux) {

        List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosCBienes (?,?,?,?,?) }");
            procedure.setString("Buscador", filtro + "%");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            procedure.setString("TIPO", tipo);
            procedure.setInt("PAUX", aux);            
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setLaboratorio(laboratorio);

                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoBotica.setTrastiendaStockEmpaque(rs.getInt("Trastienda_Stock_Empaque"));
                productoBotica.setTrastiendaStockFraccion(rs.getInt("Trastienda_Stock_Fraccion"));                

                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setProductoBotica(productoBotica);

                listaProductos.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosCarDes:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProductos;

    }
    
    
    public String RetornaCB(String filtro, String IDBOTICA, int opcion, String tipo,int aux) throws SQLException {

        String cadena = "";        

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosCBienes (?,?,?,?,?) }");
            if (aux == 4){
            procedure.setString("Buscador", filtro);
            }else{
            procedure.setString("Buscador", filtro + "%");
            }
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            procedure.setString("TIPO", tipo);
            procedure.setInt("PAUX", aux);
            
            rs = procedure.executeQuery();          

            while (rs.next()) {
                cadena = rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println("error ListarProductosCBienes:" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }

        return cadena;

    }
    
    public String recnumero(int opc) throws SQLException {

        String correlativo = "";        

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call recnumero (?) }");  
            procedure.setInt("OPCION", opc);
            
            rs = procedure.executeQuery();          

            while (rs.next()) {
                correlativo = rs.getString(1);
            }
            
        } catch (Exception e) {
            System.out.println("error ListarProductosCBienes:" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(">><<" + ex.getMessage());
                }
            }
        }

        return correlativo;

    }

    public List<ProductosPrecios> ListarProductosSAP(String filtro, String IDBOTICA, int opcion, String SAPTIPO) {

        List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosSAP (?,?,?,?) }");
            procedure.setString("Buscador", filtro + "%");            
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            procedure.setString("SAPTIPO", SAPTIPO);
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setLaboratorio(laboratorio);

                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));

                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setProductoBotica(productoBotica);

                listaProductos.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosSAP:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProductos;

    }

    public List<ProductosPrecios> ListarProductosSAPCargo(String filtro, String IDBOTICA, int opcion, String SAPTIPO) {

        List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosSAP (?,?,?,?) }");
            procedure.setString("Buscador", filtro);
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            procedure.setString("SAPTIPO", SAPTIPO);
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setLaboratorio(laboratorio);

                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));

                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setProductoBotica(productoBotica);

                listaProductos.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosSAP:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProductos;

    }

    public List<ProductosPrecios> ListarProductosCarDesPsicotropicos(String filtro, String IDBOTICA, int opcion, int cargar) {

        List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarProductosCarDesPsicotropicos (?,?,?,?) }");
            //procedure.setString("Buscador", filtro + "%");
            procedure.setString("Buscador", filtro);
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            procedure.setInt("CARGAR", cargar);
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setLaboratorio(laboratorio);

                productoBotica.setProducto(producto);
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));

                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setProductoBotica(productoBotica);

                listaProductos.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosCarDesPsicotropicos:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaProductos;

    }

    public List<ProductosPrecios> Lista_Kardex(String filtro, String IDBOTICA, int opcion) {
        List<ProductosPrecios> Lista = new ArrayList<ProductosPrecios>();
        ProductosPrecios productoPrecio;
        Producto producto;
        Productos_Botica productoBotica;
        Laboratorios laboratorio;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_KARDEX (?,?,?) }");
            procedure.setString("Buscador", filtro + "%");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("OPCION", opcion);
            rs = procedure.executeQuery();

            while (rs.next()) {

                productoPrecio = new ProductosPrecios();
                producto = new Producto();
                productoBotica = new Productos_Botica();
                laboratorio = new Laboratorios();

                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setLaboratorio(laboratorio);
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoBotica.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                productoBotica.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                productoPrecio.setProductoBotica(productoBotica);
                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setPVPX(rs.getDouble(4));
                Lista.add(productoPrecio);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductosCarDes:" + ex.getMessage());
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

    public List<Productos_Botica> RecuperEmpaque(String IDProducto, String idbotica) {

        CallableStatement procedure = null;
        List<Productos_Botica> ListaProducto = new ArrayList<Productos_Botica>();
        Productos_Botica productoBotica;
        Producto producto;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_EMPAQUE (?,?) }");
            procedure.setString(1, IDProducto);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                producto = new Producto();
                productoBotica = new Productos_Botica();
                producto.setIdProducto(IDProducto);
                producto.setEmpaque(rs.getInt("Empaque"));
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                ListaProducto.add(productoBotica);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return ListaProducto;
    }

    public List<Productos_Botica> RecuperEmpaque_Alamcen(String IDProducto, String idbotica, String almacen) {

        CallableStatement procedure = null;
        List<Productos_Botica> ListaProducto = new ArrayList<Productos_Botica>();
        Productos_Botica productoBotica;
        Producto producto;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_EMPAQUE_ALMACEN (?,?,?) }");
            procedure.setString(1, IDProducto);
            procedure.setString(2, idbotica);
            procedure.setString(3, almacen);
            rs = procedure.executeQuery();

            while (rs.next()) {
                producto = new Producto();
                productoBotica = new Productos_Botica();
                producto.setEmpaque(rs.getInt("Empaque"));
                productoBotica.setProducto(producto);
                productoBotica.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                productoBotica.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                ListaProducto.add(productoBotica);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RecuperEmpaque_Alamcen:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return ListaProducto;
    }

    public List<Varios> RecuperaStock(String CodPro, String Botica) {

        Integer valor = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_STOCK (?,?) }");
            procedure.setString(1, CodPro);
            procedure.setString(2, Botica);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                Stks.add(new Varios(rs.getInt(1),
                        rs.getInt(2)));

            }

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return Stks;
    }

    public List<Producto> ListarProductos(String idboti, String descrip) {

        Producto producto;
        Laboratorios laboratorio;
        listaproducto.removeAll(listaproducto);

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTAR_PRODUCTOS (?,?) }");
            procedure.setString("BOTICA", idboti);
            procedure.setString("DESCRIPCION1", descrip);
            rs = procedure.executeQuery();

            while (rs.next()) {
                producto = new Producto();
                laboratorio = new Laboratorios();
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                laboratorio.setId_Lab(rs.getString("Id_Laboratorio"));
                producto.setLaboratorio(laboratorio);
                listaproducto.add(producto);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA ListarProductos :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproducto;

    }

    public List<ListaDetalles> verificaPromocion(String IdProducto,int idpromo) {

        List<ListaDetalles> lista = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_PRODUCTO_PROMOCION (?,?) }");
            procedure.setString(1, IdProducto);
            procedure.setInt(2, idpromo);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista.add(new ListaDetalles(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3), rs.getInt(4)));
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOn:" + ex.getMessage());
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

    public List<ListaDetalles> verificaGrupoPromocion(String IdProducto) {

        List<ListaDetalles> contar = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_GRUPOPROMO (?) }");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                contar.add(new ListaDetalles(rs.getInt(1)));
            }


        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase VERIFICA_GRUPOPROMO:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return contar;

    }

    public List<ListaDetalles> verificaDatosGeneralesCliente(String DocCliente,String V_RUCDNI) {

        List<ListaDetalles> lista = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call RECUPERA_DATOS_CLIENTE (?,?) }");
            procedure.setString(1, DocCliente);
            procedure.setString(2, V_RUCDNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista.add(new ListaDetalles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase DatosGeneralesCliente:" + ex.getMessage());
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

    public List<ListaDetalles> verificaPromocionPrecio(String IdProducto) {

        List<ListaDetalles> lista = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_PRODUCTOPROMOCIONPRECIO (?) }");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista.add(new ListaDetalles(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3), rs.getInt(4)));
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOn:" + ex.getMessage());
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

    public Double Recupera_Dscto(String CodProducto, int i,int idprom, int flatmultiple) {
        Double dscto = 0.00;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RETORNA_DSCTO_PROMOCION (?,?,?,?) }");
            procedure.setString(1, CodProducto);
            procedure.setInt(2, i);
            procedure.setInt(3, idprom);
            procedure.setInt(4, flatmultiple);
            rs = procedure.executeQuery();

            while (rs.next()) {
                dscto = rs.getDouble(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return dscto;

    }

    public String Recupera_Promo_Nombre(String CodPRo) {

        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_PROMO (?) }");
            procedure.setString(1, CodPRo);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos procedure RECUPERA_NOMBRE_PROMO :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }
    
    public int EsBolsa(String CodPRo) {

        int CodP =0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESBOLSA (?) }");
            procedure.setString(1, CodPRo);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                CodP = rs.getInt(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos procedure RECUPERA_ESBOLSA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }
    
    public Double Recupera_PrecioVenta(String CodProducto, int opc) {
        Double pv = 0.00;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_PROMO_MULTIPLE (?,?) }");
            procedure.setString(1, CodProducto);
            procedure.setInt(2, opc);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                pv = rs.getDouble(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return pv;

    }
    
    public String Recupera_Promo_Nombre_Muiltiple(String CodPRo, int opc) {

        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_PROMO_MULTIPLE (?,?) }");
            procedure.setString(1, CodPRo);
            procedure.setInt(2, opc);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos procedure RECUPERA_NOMBRE_PROMO_MULTIPLE :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public String Recupera_Promo_Nombredscto(String CodPRo) {

        String CodPr = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_PROMODSCTO (?) }");
            procedure.setString(1, CodPRo);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                CodPr = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos procedure RECUPERA_NOMBRE_PROMODSCTO :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodPr;
    }

    public String Recupera_Promo_NombrePrecio(String CodPRo) {

        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_PROMOPRECIO (?) }");
            procedure.setString(1, CodPRo);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }
    public Object Recupera_Promo_Codigo(String CodPRo,int idprom,int EsMultiDcto) {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_CODIGO (?,?,?) }");
            procedure.setString(1, CodPRo);
            procedure.setInt(2, idprom);
            procedure.setInt(3, EsMultiDcto);

            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public Object Recupera_Promo_Comprar(String CodPRo,int idpromo,String opc) {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_COMPRAR_REGALAR (?,?,?) }");
            procedure.setString(1, CodPRo);
            procedure.setInt(2, idpromo);
            procedure.setString(3, opc);

            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }
    
    public Object MarcarPromociones(String CodPRo/*,String CodPromocion,int idpromo*/) {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call MARCARPROMOCIONES (?) }");
            procedure.setString(1, CodPRo);
            //procedure.setString(2, CodPromocion);
            //procedure.setInt(3, idpromo);

            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public Object Recupera_promodscto(String CodPRo,String botica, String tipoprecio, int opc) {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call recuperapromodscto (?,?,?,?) }");
            procedure.setString(1, CodPRo);
            procedure.setString(2, botica);
            procedure.setString(3, tipoprecio);
            procedure.setInt(4, opc);

            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public Object verificanuevocodigoPromocion(String CodPRo) {
        String nuevo = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VERIFICA_NUEVOCODPROMO (?) }");
            procedure.setString(1, CodPRo);
            rs = procedure.executeQuery();

            while (rs.next()) {
                nuevo = rs.getString(1)+","+rs.getString(2);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase VERIFICA_NUEVOCODPROMO :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return nuevo;
    }

    public Object Recupera_Promo_CodigoPrecio() {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMBRE_CODIGOPRECIO() }");
            //procedure.setString(1, CodPRo);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1)+ "@" + rs.getString(2)+ "@" + rs.getString(3)+ "@" + rs.getString(4)+ "@" + rs.getString(5);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RECUPERA_NOMBRE_CODIGOPRECIO:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public Object Recupera_Moneda(String idventa,String Fechavta) {
        String IdMoney = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_MONEDA(?,?) }");
            procedure.setString(1, idventa);
            procedure.setString(2, Fechavta);
            rs = procedure.executeQuery();

            while (rs.next()) {
                IdMoney = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RECUPERAMONEDA:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return IdMoney;
    }

    public Object Recupera_Imprimible(String idventa,String botica) {
        String IdImprimible = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_IMPRIMIBLE(?,?) }");
            procedure.setString(1, idventa);
            procedure.setString(2, botica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                IdImprimible = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RECUPERA_IMPRIMIBLE:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return IdImprimible;
    }

    public Object Recupera_MensajeProducto(String ProductoCod) {
        String CodP = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_MENSAJEPRODUCTO(?) }");
            procedure.setString(1, ProductoCod);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getString(1);
                if (CodP == null || CodP.equals("")){

                    CodP = "";
                }
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RECUPERA_MENSAJEPRODUCTO:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;
    }

    public Object Recupera_Poliza_Codigo() {
        String CodPol = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CODIGO_POLIZA() }");
            //procedure.setString(1, CodPRo);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodPol = rs.getString(1)+ "@" + rs.getString(2);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA RECUPERA_CODIGO_POLIZA:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodPol;
    }


    public int Recupera_Empaque(String idproducto) {
        int CodP = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_EMPAQUE_PRODUCTO(?) }");
            procedure.setString(1, idproducto);            
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getInt("Empaque");
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;

    }
    
    public int Recupera_Empaque1(String idproducto,int opc) {
        int CodP = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESBOTIQUIN_PRODUCTO(?,?) }");
            procedure.setString(1, idproducto);
            procedure.setInt(2, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getInt("Precio_Botiquin");
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CodP;

    }
    
    
    public int Recupera_EsBlister(String idproducto,int opc) {
        int Bli = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESBLISTER(?,?) }");
            procedure.setString(1, idproducto);
            procedure.setInt(2, opc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Bli = rs.getInt("Blister");
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA BLISTER:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Bli;

    }
    
    public String Recupera_CantCB(String idproducto) {
        String CantCB = "";
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CANTPRODUCTO_CB(?) }");
            procedure.setString(1, idproducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CantCB = rs.getString("cantidad").toUpperCase();
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase RECUPERA_CANTPRODUCTO_CB :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return CantCB;

    }

    public Double RecuperaPrecio(String CodProducto, String idbotica) {

        Double CodP = 0.00;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_PRECIO (?,?) }");
            procedure.setString(1, CodProducto);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodP = rs.getDouble(1);
            }



        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return CodP;

    }

    public Productos_Botica InformacionProducto(String idboti, String idproduc) {

        Laboratorios laboratorio;
        Familias familia;
        Genericos generico;
        Producto producto;
        Productos_Botica obj = null;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call INFORMACION_PRODUCTO(?,?) }");
            procedure.setString("IDBOTI", idboti);
            procedure.setString("IDPRODUC", idproduc);
            rs = procedure.executeQuery();

            while (rs.next()) {
                laboratorio = new Laboratorios();
                familia = new Familias();
                generico = new Genericos();
                producto = new Producto();
                obj = new Productos_Botica();

                laboratorio.setDescripcion(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorio);
                familia.setDescripcion(rs.getString("FAMILIA"));
                producto.setIdFamilia(familia);
                generico.setDescripcionGenerico(rs.getString("GENERICO"));
                producto.setIdGenerico(generico);
                producto.setEmpaque(rs.getInt("Empaque"));
                obj.setProducto(producto);
                obj.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                obj.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                obj.setAlmacen_Stock_Empaque(rs.getInt("Almacen_Stock_Empaque"));
                obj.setAlmacen_Stock_Fraccion(rs.getInt("Almacen_Stock_Fraccion"));
                obj.setNumero_Lote(rs.getString("Numero_Lote"));
                obj.setFecha_Vencimiento_Lote(rs.getDate("Fecha_Vencimiento_Lote"));
            }

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
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

    public List<ProductosPrecios> ListarProductos_PorLaboratorio(String filtro, String laboratorio, String idbotica, String tipprecio, int opconstock) {

        Laboratorios laboratorios;
        Familias familia;
        Genericos generico;
        Producto producto;
        Productos_Botica obj = null;
        ProductosPrecios productoPrecio;
        TipoPrecio tipoPrecio;

        listaproductos.removeAll(listaproductos);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PRODUCTOS_POR_LABORATORIO(?,?,?,?,?) }");
            procedure.setString("FILTRO", filtro);
            procedure.setString("LABORA", laboratorio);
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("TIPPRECIO", tipprecio);
            procedure.setInt("OBJSTOCK", opconstock);
            rs = procedure.executeQuery();

            while (rs.next()) {
                laboratorios = new Laboratorios();
                familia = new Familias();
                generico = new Genericos();
                producto = new Producto();
                tipoPrecio = new TipoPrecio();
                obj = new Productos_Botica();
                productoPrecio = new ProductosPrecios();

                laboratorios.setId_Lab(laboratorio);
                laboratorios.setDescripcion(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorios);
                generico.setId_Generico(rs.getString("GENERICO"));
                generico.setDescripcionGenerico(rs.getString("DESGENERICO"));
                producto.setIdGenerico(generico);
                familia.setDescripcion(rs.getString("DESFAMILIA"));
                producto.setIdFamilia(familia);
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                obj.setProducto(producto);
                obj.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                obj.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoPrecio.setProductoBotica(obj);
                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setId_Producto_Grupo(rs.getInt("Id_Producto_Grupo"));
                productoPrecio.setGrupo_Id(rs.getInt("Id_Grupo"));
                productoPrecio.setId_Promocion(rs.getInt("Id_Promocion"));
                productoPrecio.setDescuento_Adicional(rs.getDouble("Descuento_Adicional"));
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecio.setTipoPrecio(tipoPrecio);
                listaproductos.add(productoPrecio);

            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA ListarProductos_PorLaboratorio :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos;

    }

    public List<ProductosPrecios> ListarProductos_Lab_Geneico(String filtro, String idbotica, String tipprecio, String generico) {

        Laboratorios laboratorios;
        Familias familia;
        Genericos genericos;
        Producto producto;
        Productos_Botica obj = null;
        TipoPrecio tipoPrecio;
        ProductosPrecios productoPrecio;
        listaproductos.removeAll(listaproductos);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PRODUUCTO_LAB_GENE(?,?,?,?) }");
            procedure.setString("FILTRO", filtro);
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("TIPPRECIO", tipprecio);
            procedure.setString("INGENERICO", generico);
            rs = procedure.executeQuery();

            while (rs.next()) {

                laboratorios = new Laboratorios();
                familia = new Familias();
                genericos = new Genericos();
                producto = new Producto();
                obj = new Productos_Botica();
                tipoPrecio = new TipoPrecio();
                productoPrecio = new ProductosPrecios();

                laboratorios.setId_Lab(rs.getString("DescripcionLab"));
                producto.setLaboratorio(laboratorios);
                genericos.setId_Generico(rs.getString("GENERICO"));
                genericos.setDescripcionGenerico(rs.getString("DESGENERICO"));
                producto.setIdGenerico(genericos);
                familia.setDescripcion(rs.getString("DESFAMILIA"));
                producto.setIdFamilia(familia);
                producto.setIdProducto(rs.getString("Id_Producto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setEmpaque(rs.getInt("Empaque"));
                producto.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                obj.setProducto(producto);
                obj.setMostrador_Stock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                obj.setMostrador_Stock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                productoPrecio.setProductoBotica(obj);
                productoPrecio.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                productoPrecio.setDescuento_Venta(rs.getDouble("Descuento_Venta"));
                productoPrecio.setId_Producto_Grupo(rs.getInt("Id_Producto_Grupo"));
                productoPrecio.setGrupo_Id(rs.getInt("Id_Grupo"));
                productoPrecio.setId_Promocion(rs.getInt("Id_Promocion"));
                productoPrecio.setDescuento_Adicional(rs.getDouble("Descuento_Adicional"));
                tipoPrecio.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                productoPrecio.setTipoPrecio(tipoPrecio);
                listaproductos.add(productoPrecio);

            }
            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPADATOS CLASE PRODUCTODATA ListarProductos_Lab_Geneico :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaproductos;

    }

    public List<Genericos> Lista_Genericos(String filtro) {
        Genericos generico;
        listagenerico.removeAll(listagenerico);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_GENERICOS (?) }");
            procedure.setString(1, filtro);
            rs = procedure.executeQuery();

            while (rs.next()) {
                generico = new Genericos();
                generico.setId_Generico(rs.getString("Id_Generico"));
                generico.setDescripcionGenerico(rs.getString("Descripcion"));
                listagenerico.add(generico);
            }
            rs.close();


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listagenerico;


    }

    public Descuento Recupera_Porcen_Descuento(String idproducto) {
        Producto producto;
        Descuento objDescuento = null;
        CallableStatement procedure = null;


        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_PORCEN_DESCUEN (?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                objDescuento = new Descuento();
                producto = new Producto();
                producto.setIdProducto(idproducto);
                objDescuento.setId_Descuento(rs.getInt("Id_Descuento"));
                objDescuento.setPorcentaje_Descuento(rs.getDouble("Porcentaje_Descuento"));
                objDescuento.setProducto(producto);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return objDescuento;
    }

    public boolean validaPosibleDescargo(String CodigoProducto, Integer enterito, Integer fraccionado) {
        boolean razon = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call VERIFICA_MANEJO_STOCK (?,?,?) }");
            procedure.setString(1, CodigoProducto);
            procedure.setInt(2, enterito);
            procedure.setInt(3, fraccionado);
            rs = procedure.executeQuery();
            rs.next();
            razon = rs.getBoolean(1);

        } catch (Exception ex) {
            System.out.println("validaPosibleDescargo " + ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return razon;

    }

    public Venta Recupera_Descuento_Producto(String idproducto, String proforma, String idtipoprecio,String precio) {

        double descto = 0;
        double igv_exonerado = 0;
        CallableStatement procedure = null;
        Venta obj = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RETORNA_DESCUENTO_MIPRODUCTO (?,?,?,?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            procedure.setString("TIPOPRECIO", idtipoprecio);
            procedure.setString("PROFORMA", proforma);
            procedure.setString("PRECIO", precio);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Venta();
                descto = rs.getDouble(1);
                igv_exonerado = rs.getDouble(2);
                obj.setDescuento(descto);
                obj.setIGV(igv_exonerado);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
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

    public double Recupera_Descuento_Producto(String idproducto, String idbotica) {

        double descto = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RETORNA_DESCUENTO_PRODCUTO(?,?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();
            rs.next();
            descto = rs.getDouble(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return descto;
    }

    public double Recupera_Desct_Producto(String idproducto, String idbotica, String tipoprecio,String precio) {

        double descto = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_DESCT_PRODUCTO(?,?,?,?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("TIPOPRECIO", tipoprecio);
            procedure.setString("PRECIO", precio);
            rs = procedure.executeQuery();
            rs.next();
            descto = rs.getDouble(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return descto;

    }

    public String RetornaCantidad_Compara(String tipovta) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CANT_TIPOPAGO(?) }");
            procedure.setString("TIPVTA", tipovta);
            rs = procedure.executeQuery();
            rs.next();
            cant = rs.getString(1);
            tipo = rs.getString(2);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cant + "/"+ tipo;

    }
    public String RetornaEsGratis(String producto) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESGRATUITA(?) }");
            procedure.setString("IDPRODUCTO", producto);
            rs = procedure.executeQuery();
            rs.next();
            resultEsGratis = rs.getString(1);            
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA : RECUPERA_ESGRATUITA" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultEsGratis;

    }

    public String RetornaTipoPrecio(String producto,int unid, int fracc,int opc) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_TIPOPRECIO(?,?,?,?) }");
            procedure.setString("IDPRODUCTO", producto);
            procedure.setInt("UNI", unid);
            procedure.setInt("FRA", fracc);
            procedure.setInt("OPC", opc);
            
            rs = procedure.executeQuery();
            rs.next();
            resultEsGratis = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA : RECUPERA_TIPOPRECIO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultEsGratis;

    }

    public String RetornaNoMostrar(String producto) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_NOMOSTRAR(?) }");
            procedure.setString("IDPRODUCTO", producto);
            rs = procedure.executeQuery();
            rs.next();
            resultNoMostrar = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA : RECUPERA_NOMOSTRAR" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultNoMostrar;

    }
    public String RetornaEsGratisPromo(String producto,String CodPromo) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESGRATUITAPROMOCION(?,?) }");
            procedure.setString("IDPRODUCTO", producto);
            procedure.setString("IDPRODUCTOPROMO", CodPromo);
            rs = procedure.executeQuery();
            rs.next();
            resultEsGratisPromo = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA : RECUPERA_ESGRATUITAPROMOCION" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultEsGratisPromo;

    }

    public String RetornaEmpaque(String botica,String producto) {


        Connection mycnn = new ConexionPool().getConnection();
        CallableStatement procedure = null;
        //conex=null;
        //conex = new ConexionPool().getConnection();
        try {
            
            procedure = mycnn.prepareCall("{call RECUPERA_EMPAQUE(?,?)}");
            procedure.setString("vCP", producto);
            procedure.setString("IDBOTICA", botica);
            rs = procedure.executeQuery();
            rs.next();
            empaque = rs.getString(1);
            rs.close();
            mycnn.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != mycnn) {
                try {
                    mycnn.close();
                } catch (SQLException ex) {
                }
            }
        }
        mycnn=null;
        return empaque;

    }
    
    public String RetornaValidaInterno(String interno, String monto, String fechadoc) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_VALIDARANULAR(?,?,?) }");
            procedure.setString("INTERNO", interno);
            procedure.setString("MONTO", monto);
            procedure.setString("FECHA", fechadoc);
            rs = procedure.executeQuery();
            rs.next();
            cant = rs.getString(1);            
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cant;

    }

    public int RetornaValidaPersonal(String dni, int caja) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_VALIDAPERSONAL(?,?) }");
            procedure.setString("DNIPERSONAL", dni);
            procedure.setInt("CAJA", caja);
            
            rs = procedure.executeQuery();
            rs.next();
            personal = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return personal;


    }
    
    public int Essolofactura(int idcliente) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_ESSOLOFACTURA(?) }");            
            procedure.setInt("IDCLIENTE", idcliente);
            rs = procedure.executeQuery();
            rs.next();
            personal = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return personal;


    }

    public String RetornaProformaModificada(String Proforma) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_PROFORMAMODIFICADA(?) }");
            procedure.setString("PROFOR", Proforma);
            rs = procedure.executeQuery();
            rs.next();
            cant = rs.getString(1);
            tipo = rs.getString(2);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cant + "/"+ tipo;


    }
    public String RetornaCampo(String campo,String idventa,String IDBOTI,String opc) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERA(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOXML", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("OPC", opc);
            rs = procedure.executeQuery();
            rs.next();            
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
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
    public String RetornaCampoNC(String campo,String idventa,String IDBOTI,String opc) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERANC(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOXML", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("OPC", opc);
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
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
    public String RetornaCampoND(String campo,String idventa,String IDBOTI,String opc) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERAND(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOXML", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("OPC", opc);
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
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


    public String RetornaCampoCabecera(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERATOTAL(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("CAMPOTOTALTEXTO", TOTALTEXTO);
            rs = procedure.executeQuery();
            rs.next();
            campoCabecera = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoCabecera;


    }

    public String RetornaCampoCabeceraNC(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERATOTALNC(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("CAMPOTOTALTEXTO", TOTALTEXTO);
            rs = procedure.executeQuery();
            rs.next();
            campoCabecera = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoCabecera;


    }
    public String RetornaCampoCabeceraND(String campo,String idventa,String IDBOTI,String TOTALTEXTO) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPOCABECERATOTALND(?,?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            procedure.setString("CAMPOTOTALTEXTO", TOTALTEXTO);
            rs = procedure.executeQuery();
            rs.next();
            campoCabecera = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoCabecera;


    }

    public String RetornaCampoDetalle(String campo,String idventa, String IDBOTI) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPODETALLETOTAL(?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            rs = procedure.executeQuery();
            rs.next();
            campoDetalle = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoDetalle;


    }
    public String RetornaCampoDetalleNC(String campo,String idventa,String IDBOTI) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPODETALLETOTALNC(?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            rs = procedure.executeQuery();
            rs.next();
            campoDetalle = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoDetalle;


    }

    public String RetornaCampoDetalleND(String campo,String idventa,String IDBOTI) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_CAMPODETALLETOTALND(?,?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("CAMPOCABECERA", campo);
            procedure.setString("IDBOTICA", IDBOTI);
            rs = procedure.executeQuery();
            rs.next();
            campoDetalle = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoDetalle;


    }
    
    public String RetornaLab(String prod) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_LAB(?) }");
            procedure.setString("CodPro", prod);
            
            rs = procedure.executeQuery();
            rs.next();
            campoDetalle = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return campoDetalle;


    }

    public List<Tipo_Producto> Listar_Tipo_Producto() {
        Tipo_Producto tipoProducto;
        List<Tipo_Producto> ltPro = new ArrayList<Tipo_Producto>();
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call LISTAR_TIPO_PRODUCTO () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                tipoProducto = new Tipo_Producto();
                tipoProducto.setId_Tipo_Producto(rs.getString("Id_Tipo_Producto"));
                tipoProducto.setDescripcion(rs.getString("Descripcion"));
                ltPro.add(tipoProducto);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Tipo Producto :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return ltPro;

    }

    public void vaciarFaltantesSobrantes(int tipo) throws SQLException {
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;
        procedure = conex.prepareCall(" delete from  Faltantes_Sobrantes where Tipo=" + tipo + "");
        procedure.execute();
        conex.close();
    }

    public boolean VerificaExisteCruce(String Producto_Padre, String Producto_Hembra) {

        int descto = 0;
        boolean rpta = false;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VERIFICA_EXISTE_COMB_CRUCE (?,?) }");
            procedure.setString(1, Producto_Padre);
            procedure.setString(2, Producto_Hembra);
            rs = procedure.executeQuery();
            rs.next();
            descto = rs.getInt(1);
            rs.close();




        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Verificando Cruce :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        if (descto > 0) {
            rpta = true;
        } else {
            rpta = false;
        }



        return rpta;



    }

    public void GuardarCombCruce(String Producto_Padre, String Producto_Hembra) {

        CallableStatement procedure = null;
        conex = new ConexionPool().getConnection();

        try {

            procedure = conex.prepareCall("{ call INSERTAR_COMB_CRUCE (?,?) }");
            procedure.setString(1, Producto_Padre);
            procedure.setString(2, Producto_Hembra);
            procedure.executeQuery();
            conex.close();

        } catch (SQLException ex) {
            System.out.println("Error Insertando Comb Cruce");
        }

    }

    public int CantidadDecimales() {
        int cantidad = 2;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call RETORNA_DECIMALES () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                cantidad = rs.getInt(1);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos CantidadDecimales " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cantidad;

    }

    public int CantidadDecimalesPantalla() {
        int cantidad = 2;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call RETORNA_DECIMALESPANTALLA () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                cantidad = rs.getInt(1);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos CantidadDecimales " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cantidad;

    }
    
    public List<ListaDetalles> Muestra_Promociones(String IdProducto) {

        List<ListaDetalles> lista = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call MUESTRA_PROMOCIONES (?) }");
            //System.out.println("call MUESTRA_PROMOCIONES ('" + IdProducto + "') ");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista.add(
                        new ListaDetalles(
                        rs.getInt("Id_Promocion"), rs.getString("Descripcion"), rs.getString("Codigo_Promocion"),
                        rs.getDouble("Descuento"), rs.getString("Id_Laboratorio")));
            }

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOn:" + ex.getMessage());
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
    
    
    public List<ListaDetalles> Muestra_Promociones_Multiple(String IdProducto) {

        List<ListaDetalles> lista = new ArrayList<ListaDetalles>();
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call MUESTRA_PROMOCIONES_MULTIPLE (?) }");
            //System.out.println("call MUESTRA_PROMOCIONES ('" + IdProducto + "') ");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista.add(
                        new ListaDetalles(
                        rs.getInt("Id_Promocion"), rs.getString("Descripcion"), rs.getString("Codigo_Promocion"),
                        rs.getDouble("Descuento"), rs.getString("Id_Laboratorio")));
            }

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOn:" + ex.getMessage());
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

    public int Verifica_Promocion(String IdProducto/*,int idprom*/) {

        int cantidad = 0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_PROMOCION (?) }");
            //System.out.println("call MUESTRA_PROMOCIONES ('" + IdProducto + "') ");
            procedure.setString(1, IdProducto);
            // procedure.setInt(2, idprom);
            rs = procedure.executeQuery();
            rs.next();
            cantidad = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOn:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cantidad;

    }

    public int Verifica_EsRecarga(String IdProducto) {

        int cantidad = 1;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_ESRECARGA (?) }");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();
            rs.next();
            cantidad = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase VERIFICARECARGAS:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cantidad;

    }

    public String Recupera_OperadorRecarga(String idproforma,String IdProducto) {

        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_OPERADORRECARGA(?,?) }");
            procedure.setString("PROFOR", idproforma);
            procedure.setString("PROFOR", IdProducto);
            rs = procedure.executeQuery();
            rs.next();            
            tipo = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return tipo;


    }


    public int Verifica_PromocionPrecio(String IdProducto) {

        int cantidad = 1;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call VERIFICA_PROMOCIONPRECIO (?) }");
            //System.out.println("call MUESTRA_PROMOCIONES ('" + IdProducto + "') ");
            procedure.setString(1, IdProducto);
            rs = procedure.executeQuery();
            rs.next();
            cantidad = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCIOnPRECIO:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return cantidad;

    }

    public int Verifica_Es_Manual(String CodProducto) {

        int esManual =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaEsManual (?) }");
            procedure.setString(1, CodProducto);
            rs = procedure.executeQuery();
            rs.next();
            esManual = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase PRODUCTO_PROMOCION Es Manual:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return esManual;
    }

    public int Verifica_Cantidad_Producto(String CodProducto) {

        int Cantidad =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaEsManual (?) }");
            procedure.setString(1, CodProducto);
            rs = procedure.executeQuery();
            rs.next();
            Cantidad = rs.getInt(2);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase BOTICA_GRUPO_PROMOCION Es Manual:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Cantidad;
    }

    public int Verifica_Es_Promo_Frac(String CodProducto) {

        int esPromoFrac =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaEsPromoFraccion (?) }");
            procedure.setString(1, CodProducto);
            rs = procedure.executeQuery();
            rs.next();
            esPromoFrac = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PRODUCTO_PROMOCION Es PromoFrac:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return esPromoFrac;
    }

    public int Verifica_Es_Mitad_Gratis(String CodProducto) {

        int esMitadGratis =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaEsMitadGratis (?) }");
            procedure.setString(1, CodProducto);
            rs = procedure.executeQuery();
            rs.next();
            esMitadGratis = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase PRODUCTO_PROMOCION Es MitadGratis:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return esMitadGratis;
    }


    public int Verifica_Promo_PorPrecio(String CodProducto, int opc, int idpro) {

        int porprecio =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaPromoPorPrecio (?,?,?) }");
            procedure.setString(1, CodProducto);
            procedure.setInt(2, idpro);
            procedure.setInt(3, opc);
            

            rs = procedure.executeQuery();
            rs.next();
            porprecio = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase PRODUCTO_PROMOCION Es MitadGratis:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return porprecio;
    }

    public int Verifica_Promo_PorPrecio1(String CodProducto,String cupon, int opc) {

        int porprecio =0;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call verificaPromoPorCupon (?,?,?) }");
            procedure.setString(1, CodProducto);
            procedure.setString(2, cupon);
            procedure.setInt(3, opc);
            rs = procedure.executeQuery();
            rs.next();
            porprecio = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error CapaDatos clase PRODUCTO_PROMOCION Es MitadGratis:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return porprecio;
    }


    public double Dscto_Promo_PorPrecio(String idproducto,int opc) {

        double descto = 0;
        CallableStatement procedure = null;

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RECUPERA_DESCT_PORPRECIO(?,?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            procedure.setInt("POPC", opc);
            rs = procedure.executeQuery();
            rs.next();
            descto = rs.getDouble(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return descto;
    }

    public int esPromoEmpFraccion(String idproducto) {//MAX_CODIGO

        CallableStatement procedure = null;
        int valorfraccion=0;
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VERIFICA_PROMO_EMPAQUEFRACCION(?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            rs = procedure.executeQuery();
            rs.next();
            valorfraccion = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return valorfraccion;
    }
    public String RecuperaCodPromoAeliminar(String idproducto) {//MAX_CODIGO

        CallableStatement procedure = null;
        String valorfraccion="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call RecuperaCodPromoAEliminar(?) }");
            procedure.setString("IDPRODUCTO", idproducto);
            rs = procedure.executeQuery();
            rs.next();
            valorfraccion = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase ProductoDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return valorfraccion;
    }
    public int Verifica_caja_And_Personal(String idpersonal, String idcaja) {//MAX_CODIGO

        CallableStatement procedure = null;
        String valorfraccion="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VERIFICA_CAJA_AND_PERSONAL(?,?) }");
            procedure.setString("idpersonal", idpersonal);
            procedure.setString("idcaja", idcaja);
            rs = procedure.executeQuery();
            rs.next();
            valorfraccion = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en verificar la caja revisar boticas cajas con id_personal :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valorfraccion);
    }
    public int[] PromoEstaEnDosGrupos(String idproducto, int idpromo) {//MAX_CODIGO
        int[] grupos=null;
        CallableStatement procedure = null;
        int i=0;
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VerificaPromoEstaEnDosGrupos(?,?) }");
            procedure.setString("IdProducto", idproducto);
            procedure.setInt("IdProm", idpromo);
            rs = procedure.executeQuery();
            rs.last();
            grupos=new int[rs.getRow()+2];
            grupos[0]=rs.getRow();
            i++;
            rs.first();
            do {
                  grupos[i]=rs.getInt("Id_Grupo");
                  i++;
            } while (rs.next());
            rs.close();

        } catch (SQLException ex) {
            //System.out.println("Error en procedure VerificaPromoEstaEnDosGrupos :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return grupos;
    }




    public int dsctogratuita(int idgrup, int opc) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call dsctogratuita(?,?) }");
            procedure.setInt("PIdgrup", idgrup);
            procedure.setInt("Popc", opc);
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure dsctogratuita :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }
    
    public int G_EsMultipleDscto(int idgrup, String codpromo,int opc) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call Esmultipledscto(?,?,?) }");
            procedure.setInt("IdPromo", idgrup);
            procedure.setString("CodPromo", codpromo);
            procedure.setInt("Popc", opc);
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure Esmultipledscto :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }
    
    public int G_EsPsicotropico(String codprod,int opc) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call EsPsicotropico(?,?) }");            
            procedure.setString("CodProd", codprod);
            procedure.setInt("Popc", opc);
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure EsPsicotropico :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }
    
    public int G_EsICBPER(String codprod) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call EsICBPER(?) }");            
            procedure.setString("CodPro", codprod);            
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure EsICBPER :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }
    
    public int C_BOLSA(String prof) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call CANTIBOLSA(?) }");            
            procedure.setString("PROFORMA", prof);            
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure CANTIBOLSA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }
    
    public double Precio_EsICBPER(String codprod) {

        CallableStatement procedure = null;
        double valor=0.0;
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call PrecioICBPER(?) }");            
            procedure.setString("CodPro", codprod);            
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getDouble(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure EsICBPER :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return (valor);
    }
    public double Precio_EsICBPEREMP(String codprod) {

        CallableStatement procedure = null;
        double empaque=0.0;
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call EMPICBPERPROD(?) }");            
            procedure.setString("CodPro", codprod);            
            
            rs = procedure.executeQuery();
            rs.next();
            empaque = rs.getDouble(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure EMPICBPERPROD :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return (empaque);
    }

    public int MostrarPantallaMultiple(String idProd, int opc) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call MostrarPromocionesMultiple(?,?) }");
            procedure.setString("IDPRODUCTO", idProd);
            procedure.setInt("Popc", opc);
            
            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure dsctogratuita :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }

    public int dsctocant(String idproducto) {

        CallableStatement procedure = null;
        String valor="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call dsctocantidad(?) }");
            procedure.setString("IdProducto", idproducto);

            rs = procedure.executeQuery();
            rs.next();
            valor = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure dsctocantidad :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valor);
    }



    public int Verifica_Es_Gratuita(String idproducto) {//MAX_CODIGO

        CallableStatement procedure = null;
        String valorfraccion="";
        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call VerificaEsGratuita(?) }");
            procedure.setString("IdProducto", idproducto);
            rs = procedure.executeQuery();
            rs.next();
            valorfraccion = rs.getString(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error en procedure verificaEsGratuita :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Integer.parseInt(valorfraccion);
    }



}