/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import entidad.CruceDetalle;
import entidad.CruceInventario;
import entidad.Laboratorios;
import entidad.Producto;
import entidad.Sobrantes;
import entidad.Sobrantes_Detalles;
import entidad.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kelvin
 */
public class CruceInventariosData {

    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    Connection conex;

    public CruceInventariosData() {
        db = new ConexionPool();
    }

    public boolean InsertarCruceInv(CruceInventario cruce) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaCruceInv (?,?) }");
            procedure.setString("vNombreCruce", cruce.getNombreCruce());
            procedure.setDouble("vTotalDif",(Double) cruce.getTotalDiferencia());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar Cruce: " + ex.toString());
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
    public boolean InsertarCruceDetalle(CruceDetalle cruce) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaCruceDetalle (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            procedure.setInt("vNumCruce", cruce.getNum());
            procedure.setString("vCodCar", cruce.getProdCargo().getIdProducto());
            procedure.setInt("vUniCar", cruce.getUniCargo());
            procedure.setInt("vFraCar", cruce.getFraCargo());
            procedure.setDouble("vPrecCar",(Double) cruce.getPreCargo());
            procedure.setDouble("vMontCar",(Double) cruce.getMonCargo());
            procedure.setString("vCodDes", cruce.getProdDesCargo().getIdProducto());
            procedure.setInt("vUniDes", cruce.getUniDesCargo());
            procedure.setInt("vFraDes", cruce.getFraDesCargo());
            procedure.setDouble("vPrecDes",(Double) cruce.getPreDesCargo());
            procedure.setDouble("vMontDes",(Double) cruce.getMonDesCargo());
            procedure.setDouble("vDif",(Double) cruce.getDiferencia());
            procedure.setDouble("vPvpCar",(Double) cruce.getPvpCar());
            procedure.setDouble("vPvpDes",(Double) cruce.getPvpDes());
            procedure.setDouble("vDescCar",(Double) cruce.getDescCar());
            procedure.setDouble("vDescDes",(Double) cruce.getDescDes());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar CruceDetalle: " + ex.toString());
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

    public List<CruceDetalle> ListaCruceInventarios(String nombreCruce) {

        List<CruceDetalle> milistaCruce = new ArrayList<CruceDetalle>();
        CruceDetalle cDetalle;
        Producto prodCar;
        Producto prodDes;
        Laboratorios labCar;
        Laboratorios labDes;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call sp_ListaCruceParaModificar(?) }");
            procedure.setString("vNombreCruce", nombreCruce);
            rs = procedure.executeQuery();
            
            while(rs.next()) {
                cDetalle = new CruceDetalle();
                prodCar = new Producto();
                prodDes = new Producto();
                labCar = new Laboratorios();
                labDes = new Laboratorios();

                cDetalle.setNum(rs.getInt("numCruce"));
                prodCar.setIdProducto(rs.getString("codCar"));
                prodCar.setDescripcion(rs.getString("descCargo"));
                labCar.setId_Lab(rs.getString("labCargo"));
                prodCar.setLaboratorio(labCar);
                cDetalle.setProdCargo(prodCar);
                cDetalle.setUniCargo(rs.getInt("uniCar"));
                cDetalle.setFraCargo(rs.getInt("fraCar"));
                cDetalle.setPreCargo(rs.getDouble("precCar"));
                cDetalle.setMonCargo(rs.getDouble("montCar"));
                prodDes.setIdProducto(rs.getString("codDes"));
                prodDes.setDescripcion(rs.getString("descDesca"));
                labDes.setId_Lab(rs.getString("labDesca"));
                prodDes.setLaboratorio(labDes);
                cDetalle.setProdDesCargo(prodDes);
                cDetalle.setUniDesCargo(rs.getInt("uniDes"));
                cDetalle.setFraDesCargo(rs.getInt("fraDes"));
                cDetalle.setPreDesCargo(rs.getDouble("precDes"));
                cDetalle.setMonDesCargo(rs.getDouble("montDes"));
                cDetalle.setDiferencia(rs.getDouble("diferencia"));
                cDetalle.setPvpCar(rs.getDouble("pvpCar"));
                cDetalle.setPvpDes(rs.getDouble("pvpDes"));
                cDetalle.setDescCar(rs.getDouble("DescCar"));
                cDetalle.setDescDes(rs.getDouble("DescDes"));

                milistaCruce.add(cDetalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos listaDetalleCruce: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milistaCruce;


    }

    public ResultSet cargaComboCruce()  throws Exception{
        conex = new ConexionPool().getConnection();
        String consulta = "select nombreCruce from CruceInv order by idCruce desc";
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public boolean InsertarSobrante(Sobrantes sobra) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaSobran(?,?) }");
            procedure.setString("vNombreSob", sobra.getCodSob());
            procedure.setDouble("vTotalSob",(Double) sobra.getTotalSobrante());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar Cruce: " + ex.toString());
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

    public boolean InsertarSobranteDetalle(Sobrantes_Detalles sobraDeta) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_GrabaSobranDetalle (?,?,?,?,?,?,?) }");

            procedure.setString("vCodCar", sobraDeta.getCodProd().getIdProducto());
            procedure.setInt("vUniCar", sobraDeta.getUnidades());
            procedure.setInt("vFraCar", sobraDeta.getFracciones());
            procedure.setDouble("vPrecCar",(Double) sobraDeta.getPrecio());
            procedure.setDouble("vMontCar",(Double) sobraDeta.getMonto());
            procedure.setDouble("vPvpCar",(Double) sobraDeta.getPvp());
            procedure.setDouble("vDescCar",(Double) sobraDeta.getDescuento());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar SobranteDetalle: " + ex.toString());
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

    public List<Sobrantes_Detalles> ListaSobrante(String nomSobrante) {
     List<Sobrantes_Detalles> milistaCruce = new ArrayList<Sobrantes_Detalles>();
        Sobrantes_Detalles cDetalle;
        Producto prodCar;
        Laboratorios labCar;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call sp_ListaSobraParaModificar(?) }");
            procedure.setString("vNombreSobra", nomSobrante);
            rs = procedure.executeQuery();

            while(rs.next()) {
                cDetalle = new Sobrantes_Detalles();
                prodCar = new Producto();
                labCar = new Laboratorios();

                prodCar.setIdProducto(rs.getString("codProd"));
                prodCar.setDescripcion(rs.getString("prod"));
                labCar.setId_Lab(rs.getString("lab"));
                prodCar.setLaboratorio(labCar);
                cDetalle.setCodProd(prodCar);
                cDetalle.setUnidades(rs.getInt("unidades"));
                cDetalle.setFracciones(rs.getInt("fracciones"));
                cDetalle.setPrecio(rs.getDouble("precio"));
                cDetalle.setMonto(rs.getDouble("monto"));
                cDetalle.setPvp(rs.getDouble("pvp"));
                cDetalle.setDescuento(rs.getDouble("descuento"));

                milistaCruce.add(cDetalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos listaSobranteCruce: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milistaCruce;

    }

    public List<Venta> ListaVentasInventarios(String fInicio, String fFin) {

        List<Venta> milistaVentas = new ArrayList<Venta>();
        Venta vVenta;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call sp_ListaBoletasInv(?,?) }");
            procedure.setString("vFInicio", fInicio);
            procedure.setString("vFFin", fFin);
            rs = procedure.executeQuery();

            while(rs.next()) {
                vVenta = new Venta();

                vVenta.setId_Venta(rs.getString("Id_Venta"));
                vVenta.setFecha_Venta(rs.getDate("Fecha_Venta"));
                vVenta.setSerie(rs.getString("Serie"));
                vVenta.setNumero(rs.getString("Numero"));
                vVenta.setSubTotal(rs.getDouble("SubTotal"));
                vVenta.setIGV(rs.getDouble("IGV"));
                vVenta.setTotal(rs.getDouble("Total"));

                milistaVentas.add(vVenta);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos listaVentasInv: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milistaVentas;


    }

    public List<Venta> ListaVentasInventariosDet(String vIdVenta) {

        List<Venta> milistaVentas = new ArrayList<Venta>();
        Venta vVenta;
        Producto vProd;
        Laboratorios vLab;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call sp_ListaBoletasInvDetalle(?) }");
            procedure.setString("vIdVenta", vIdVenta);
            rs = procedure.executeQuery();

            while(rs.next()) {
                milistaVentas.add(new Venta(rs.getString("Id_VentaDetalle"),rs.getString("Id_Producto"), 
                        rs.getString("Descripcion"), rs.getString("Id_Laboratorio"), rs.getInt("unidad"),
                        rs.getInt("fraccion"), rs.getDouble("Precio_Venta_Final"), rs.getDouble("Total")));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos listaVentasInv: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milistaVentas;

    }

    public boolean InsertarVentaInforme() {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_grabaInformeInv () }");

            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar SobranteDetalle: " + ex.toString());
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

    public boolean InsertarVentaInformeDet(String idVenta, String tipAlma, String tipDesc, int esRepon) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call sp_grabaInformeInvDet (?,?,?,?) }");
            procedure.setString("vIdVenta", idVenta);
            procedure.setString("vTipoAlma", tipAlma);
            procedure.setString("vTipoDescuento", tipDesc);
            procedure.setInt("vEsResponsable",esRepon);
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar Informe: " + ex.toString());
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
