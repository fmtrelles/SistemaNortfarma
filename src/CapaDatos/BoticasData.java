package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Boticas;
import entidad.Cajas;
import entidad.Surf01;
import entidad.Surf02;
import entidad.Surf10;
import entidad.Surf31;
import entidad.Surf32;
import entidad.Ventas_Tipo_Pago;
import entidad.Venta;

public class BoticasData {

    ConfiguracionData objCD;
    ResultSet rs;
    Statement stm;
    List<Boticas> listaboticas = new ArrayList<Boticas>();
    List<Boticas> listaVersion = new ArrayList<Boticas>();
    List<Boticas> listaboticas2 = new ArrayList<Boticas>();
    List<Boticas> serie = new ArrayList<Boticas>();
    List<Venta> Lista_Mov = new ArrayList<Venta>();
    List<Venta> Lista_Mov_aux = new ArrayList<Venta>();
    List<Venta> Lista_ITEM = new ArrayList<Venta>();
    private ConexionPool db;
    Connection conex;
    Connection conexIP;
    public String servidor = "ALGO";

    public BoticasData() {
        db = new ConexionPool();
    }

    public List<Boticas> lista_Boticas_Acceso() {
        Boticas mibotica;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Boticas_Acceso() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString(1));
                mibotica.setId_Botica(rs.getString(2));
                listaboticas.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaboticas;


    }


    public List<Boticas> lista_Version() {
        Boticas Version;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Lista_Version() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                Version = new Boticas();
                Version.setVersion(rs.getString(1));
                listaVersion.add(Version);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTAVERSION :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaVersion;


    }

    public int Verifica_Seleccion_Turno(Cajas obj) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_SELECCION_TURNO(?,?,?,?) }");
            procedure.setString("IDBOTICA", obj.getIdbotica());
            procedure.setInt("IDCAJA", obj.getIdcaja());
            procedure.setInt("IDTURNO", obj.getMiturno());
            procedure.setInt("IDCAJERO", obj.getIdcajero());
            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int Verifica_TotalMov(String idbotica, String userMov, String fecha, String doc,int caja,int turno,double mto, int item) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_TOTALMOV(?,?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("DOCUMENTO", doc);
            procedure.setInt("CAJA", caja);
            procedure.setInt("TURNO", turno);
            procedure.setDouble("MONTO", mto);
            procedure.setDouble("ITEM", item);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);
            
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int InsertaTemp(String DatosLab,String OP) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            
            CallableStatement procedure = conex.prepareCall("{ call SP_INSERTALAB(?,?) }");
            procedure.setString("OPC", OP);
            procedure.setString("DATOSLAB", DatosLab);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int Verifica_TotalFecha(String idbotica, String userMov, String fecha, String fecha1) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_TOTALFECHA(?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("FECHA1", fecha1);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }
    public int Verifica_MaxItemFecha(String idbotica, String userMov, String fecha, String fecha1) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_MAXITEMFECHA(?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("FECHA1", fecha1);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }
    public int Verifica_ITEM(String idbotica, String userMov, String fecha, String fecha1,String doc) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ITEM(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("DOC", doc);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int Limpia_ITEM(String idbotica, int userMov, String desde, String hasta) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            
            CallableStatement procedure = conex.prepareCall("{ call LIMPIA_ITEM(?,?,?,?) }");
            procedure.setString(1, idbotica);
            procedure.setInt(2, userMov);
            procedure.setString(3, desde);
            procedure.setString(4, hasta);


            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int InsertaCorrelativo(String idbotica, int userMov, String desde, String hasta) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call INSERTA_CORRELATIVOMOV(?,?,?,?) }");
            procedure.setString(1, idbotica);
            procedure.setInt(2, userMov);
            procedure.setString(3, desde);
            procedure.setString(4, hasta);


            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int Mto_Limite(String idbotica) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call TRAE_MONTOLIMITE(?) }");
            procedure.setString(1, idbotica); 

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

    public int Verifica_SUMAITEM(String idbotica, String userMov, String fecha, String fecha1,String item) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_SUMAITEM(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("IT", item);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }
    public int Trae_ITEMNuevo(String idbotica, String userMov, String fecha, String fecha1,String item,String fechaLinea,String fechaactual) {
        int resul = 0;

        try {
            conex = new ConexionPool().getConnection();
            // System.out.println("{ call VERIFICA_SELECCION_TURNO('"+obj.getIdbotica()+"','"+obj.getIdcaja()+"','"+obj.getMiturno()+"','"+obj.getIdcajero()+"') }");
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_SUMAITEM(?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USERMOV", userMov);
            procedure.setString("FECHA", fecha);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("IT", item);
            procedure.setString("FECHAANTERIOR", fechaLinea);
            procedure.setString("FECHAACTUAL", fechaactual);

            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return resul;


    }

   public List<Venta> Verifica_TotalItem(String idbotica, int userMov, String desde, String hasta) {
        try {

            conex = new ConexionPool().getConnection();
            Lista_ITEM.retainAll(Lista_ITEM);
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_TOTALITEM(?,?,?,?) }");

            procedure.setString(1, idbotica);
            procedure.setInt(2, userMov);
            procedure.setString(3, desde);
            procedure.setString(4, hasta);
            //procedure.setInt(5, opc);

            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_ITEM.add(new Venta(
                        rs.getInt("item"),rs.getDate("fecha")));
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

        return Lista_ITEM;


    }

    public List<Venta> Lista_DatosMov(String idbotica, int userMov, String desde, String hasta) {

        try {

            conex = new ConexionPool().getConnection();
            Lista_Mov.retainAll(Lista_Mov);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DATOSMOV(?,?,?,?) }");

            procedure.setString(1, idbotica);
            procedure.setInt(2, userMov);
            procedure.setString(3, desde);
            procedure.setString(4, hasta);

            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Mov.add(new Venta(
                        rs.getString("Id_Botica"),rs.getInt("ID_CAJA"),rs.getInt("IdTurno"),
                        rs.getDate("FECHA"),
                        rs.getString("Id_Tipo_Gasto"),
                        rs.getString("Documento"),
                        rs.getDouble("Monto"),rs.getString("Concepto"),
                        rs.getInt("Id_Cliente_gasto"),rs.getInt("Id_Motivo"),
                        rs.getString("Origen"),rs.getString("Destino"),
                        rs.getDate("fechafinalmes"),rs.getString("correlativo"),
                        rs.getString("IdpersonalMov"),rs.getInt("item")));
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

        return Lista_Mov;

    }
    public List<Venta> Lista_DatosMov1(String idbotica, int userMov, String desde, String hasta) {

        try {
            Lista_Mov_aux.removeAll(Lista_Mov_aux);
            conex = new ConexionPool().getConnection();
            Lista_Mov_aux.retainAll(Lista_Mov_aux);
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_DATOSMOV(?,?,?,?) }");

            procedure.setString(1, idbotica);
            procedure.setInt(2, userMov);
            procedure.setString(3, desde);
            procedure.setString(4, hasta);

            rs = procedure.executeQuery();

            while (rs.next()) {
                Lista_Mov_aux.add(new Venta(
                        rs.getString("Id_Botica"),rs.getInt("ID_CAJA"),rs.getInt("IdTurno"),
                        rs.getDate("FECHA"),
                        rs.getString("Id_Tipo_Gasto"),
                        rs.getString("Documento"),
                        rs.getDouble("Monto"),rs.getString("Concepto"),
                        rs.getInt("Id_Cliente_gasto"),rs.getInt("Id_Motivo"),
                        rs.getString("Origen"),rs.getString("Destino"),
                        rs.getDate("fechafinalmes"),rs.getString("correlativo"),
                        rs.getString("IdpersonalMov"),rs.getInt("item")));
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

        return Lista_Mov_aux;

    }

    public String DevuelveIP(String Descripcion) {

        String newIP = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DEVUELVE_IP(?) }");
            procedure.setString(1, Descripcion);
            procedure.executeQuery();
            rs = procedure.getResultSet();

            while (rs.next()) {
                newIP = rs.getString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Devolviendo IP:" + ex.getMessage() + "<<<>>" + Descripcion);
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return newIP;

    }

    public List<Boticas> ListarBoticasDisponiblesZonas(String DescripcionZona) {

        try {

            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_BOTICAS_DISPONIBLES_ZONA(?) }");
            procedure.setString(1, DescripcionZona);
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas.add(mibotica);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas;

    }

    public List<Boticas> ListarBoticasLibresZonas(String DescripcionZona) {

        try {
            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_BOTICAS_LIBRE_ZONA(?) }");
            procedure.setString(1, DescripcionZona);
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas;

    }

    public List<Boticas> DescripcionBoticaDefault() {

        try {

            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BOTICAS_ACCESO_DESCRIPCION() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas2.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas2;

    }

    public List<Boticas> ListarBoticas() {
        listaboticas.removeAll(listaboticas);

        try {

            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_BOTICAS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas.add(mibotica);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("NeoBoticasData:" + ex.getMessage());
                }
            }
        }

        return listaboticas;

    }

    public List<Boticas> ListarBoticas2(String Local) {

        try {

            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_BOTICAS_PARAMETRO(?) }");
            procedure.setString(1, Local);
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas;


    }

    public int AperturaBoticas(List<Cajas> MIlista_Cajas, int idpersonal, List<Ventas_Tipo_Pago> Lista_Ingresos) {

        int apertura = 0;
        CallableStatement procedure1 = null;
        CallableStatement procedure = null;
        String idbotica, Fecha;
        double Saldo_dolares = 0.0;

        try {

            idbotica = MIlista_Cajas.get(0).getIdbotica();
            int caja = MIlista_Cajas.get(0).getIdcaja();
            Fecha = MIlista_Cajas.get(0).getFecha();
            Saldo_dolares = MIlista_Cajas.get(0).getdolares();

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);

            for (int i = 0; i < MIlista_Cajas.size(); i++) {
                procedure = conex.prepareCall("{ call BOTICAS_GENERAR_APERTURA('" + MIlista_Cajas.get(i).getIdcaja() + "', '" + MIlista_Cajas.get(i).getTipoVenta() + "','" + MIlista_Cajas.get(i).getSerie() + "','" + MIlista_Cajas.get(i).getNumeracion() + "', '" + MIlista_Cajas.get(i).getFecha() + "', '" + MIlista_Cajas.get(i).getHora() + "','" + MIlista_Cajas.get(i).getIdbotica() + "','" + MIlista_Cajas.get(i).getTurno() + "','" + idpersonal + "','" + Saldo_dolares + "') }");
                rs = procedure.executeQuery();
                rs.next();
                int resul = rs.getInt(1);

                if (resul == 1) {
                    conex.rollback();
                    MIlista_Cajas.removeAll(MIlista_Cajas);
                    Lista_Ingresos.removeAll(Lista_Ingresos);
                    apertura = 1;
                    return apertura;
                }

            }

            conex.commit();
            apertura = 0;

        } catch (Exception ex) {
            try {
                conex.rollback();
                MIlista_Cajas.removeAll(MIlista_Cajas);
                Lista_Ingresos.removeAll(Lista_Ingresos);
                System.out.println("Error CapaDatos AperturaBoticas clase BOTICASDATA :" + ex.getMessage());
                apertura = 2;
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK" + ex1.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return apertura;

    }

    public void ActualizarBoticasZona(String Zona, String Boticas, Integer Enviados) {

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call ACTUALIZAR_BOTICAS_ZONA(?,?,?) }");
            // System.out.println("call ACTUALIZAR_BOTICAS_ZONA('"+Zona+"','"+Boticas+"','"+Enviados+"')");
            procedure.setString(1, Zona);
            procedure.setString(2, Boticas);
            procedure.setInt(3, Enviados);
            procedure.execute();
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


    }

    public int VerificaMostrarOtraBotica(int Rol, int TipoMovimiento) {
        Integer resultado = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_TIPO_MUESTRA_CARGOS_DESCARGOS(?,?) }");
            //System.out.println("call VERIFICA_TIPO_MUESTRA_CARGOS_DESCARGOS('" + Rol + "','" + TipoMovimiento + "')");
            procedure.setInt(1, Rol);
            procedure.setInt(2, TipoMovimiento);
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
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

    public List<Boticas> ListarBoticasMiZona() {

        List<Boticas> listaboticasXS = new ArrayList<Boticas>();
        Boticas mibotica;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_BOTICAS_PROCEDENCIA() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticasXS.add(mibotica);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaboticasXS;

    }

    public List<Boticas> ListarProductosAlcohol() {

        List<Boticas> listaboticasXS = new ArrayList<Boticas>();
        Boticas mibotica;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_PRODUCTOS_ALCOHOLES() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Producto"));
                listaboticasXS.add(mibotica);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaboticasXS;

    }

    public List<Boticas> ListarProductosPsicotropicos() {

        List<Boticas> listaboticasXS = new ArrayList<Boticas>();
        Boticas mibotica;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_PRODUCTOS_PSICOTROPICOS() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Generico"));
                listaboticasXS.add(mibotica);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaboticasXS;

    }

    /*public String GuardarFirma(String botica,String idvta,String serie,String numero,String firmadigital) {
        String firma = "" ;

        try {

            //conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_FIRMADIGITAL (?,?,?,?,?) }");
            procedure.setString("PId_Botica", botica);
            procedure.setString("PId_Venta", idvta);
            procedure.setString("PId_Serie", serie);
            procedure.setString("PId_Numero", numero);
            procedure.setString("PFirma", firmadigital);

            rs = procedure.executeQuery();

            while (rs.next()) {
                 firma =  rs.getString("resultado");
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
            return firma;
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
            return firma;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return firma;
    }*/
    
    public String RecuperaCodigoDescargo(String Botica) {
        String CodigoDescargo = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCARGO_BOTICA(?) }");
            procedure.setString(1, Botica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodigoDescargo = rs.getString(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }//PCG

        return CodigoDescargo;


    }

    
    public String RecuperaCodigoDescargoTrastienda(String Botica) {
        String CodigoDescargo = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCARGO_BOTICA_T(?) }");
            procedure.setString(1, Botica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodigoDescargo = rs.getString(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }//PCG

        return CodigoDescargo;


    }
        
    public String RecuperaDescripcionDescargo(String Botica) {


        String CodigoDescargo = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCRIPCION_DESCARGO_BOTICA(?) }");
            procedure.setString(1, Botica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodigoDescargo = rs.getString(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }//PCG

        return CodigoDescargo;

    }
    
    
    public String RecuperaDescripcionDescargoTrastienda(String Botica) {


        String CodigoDescargo = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DESCRIPCION_DESCARGO_BOTICA_T(?) }");
            procedure.setString(1, Botica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                CodigoDescargo = rs.getString(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }//PCG

        return CodigoDescargo;

    }

    public List<Boticas> ListarBoticasIP() {

        Boticas mibotica;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_BOTICAS_IP() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString(1));
                mibotica.setId_Botica(rs.getString(2));
                mibotica.setCorreo(rs.getString(3));
                listaboticas.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas;
    }

    public boolean verificaCierreBotica() throws SQLException {

        Integer validar = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_CIERRE_ANTERIOR() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                validar = rs.getInt(1);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        if (validar > 0) {
            return false;
        } else {
            return true;
        }

    }

    public boolean verificaCierreBoticaIP(String IpBotica) throws SQLException {

        Integer validar = 0;

        try {
            conexIP = new ConexionPool().Conexion2(IpBotica);
            CallableStatement procedure = conexIP.prepareCall("{ call VERIFICA_CIERRE_ANTERIOR() }");
            System.out.println("call VERIFICA_CIERRE_ANTERIOR() ");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                validar = rs.getInt(1);
            }


            rs.close();
            conexIP.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conexIP) {
                try {
                    conexIP.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        if (validar > 0) {
            return false;
        } else {
            return true;
        }


    }

    public List<Boticas> ListarBoticasAcronimo(String TipoLocacion) {

        List<Boticas> listaboticas007 = new ArrayList<Boticas>();
        Boticas mibotica;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_DPTOS_POR_ACRONIMO(?) }");
            procedure.setString(1, TipoLocacion);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString("Descripcion"));
                mibotica.setId_Botica(rs.getString("Id_Botica"));
                listaboticas007.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaboticas007;


    }

    public List<Boticas> ListarTipoLocacion() {
        List<Boticas> listaboticas007 = new ArrayList<Boticas>();

        try {

            Boticas mibotica;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_TIPO_LOCACION() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                mibotica = new Boticas();
                mibotica.setDescripcion(rs.getString(1));
                listaboticas007.add(mibotica);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaboticas007;
    }

    public Integer RecuperaTotalDefault() {
        Integer total = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call TOTAL_DEFAULTA() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return total;

    }

    public String RecuperaZonaBotica() {

        String ZonaName = "";

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DEVOLVER_NOMBRE_ZONA() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                ZonaName = rs.getString(1);
            }

            rs.close();
            conex.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        }

        return ZonaName;


    }

    public String RetornaCorreo(String idbotica) {
        String correo = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Retorna_Correo(?) }");
            procedure.setString(1, idbotica);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            correo = rs.getString("Correo");
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos metodo RetornaCorreo :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return correo;

    }

    public String Retorna_Correodestino(String idbotica) {
        String correo = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_CORREODESTINO(?) }");
            procedure.setString("IDBOTI", idbotica);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            correo = rs.getString("correoDestino");
            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos metodo Retorna_Correodestino :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return correo;

    }

    public String MiID() throws SQLException {

        String OwnID = "";
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = conex.prepareCall("{ call RETORNA_BOTICA_ID() }");
        procedure.execute();
        rs = procedure.getResultSet();

        while (rs.next()) {
            OwnID = rs.getString(1);
        }

        return OwnID;

    }

    public boolean VerificaDocumentoCerrado(String Caja, String TipoVenta, String Serie, String Numeracion, String Botica) {

        Boolean decision = false;
        Integer condicion = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DOCUMENTO_CERRADO(?,?,?,?) }");
            procedure.setString(1, TipoVenta);
            procedure.setString(2, Serie);
            procedure.setString(3, Numeracion);
            procedure.setString(4, Botica);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                condicion = rs.getInt(1);
            }

            if (condicion > 0) {
                decision = false;
            } else {
                decision = true;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return decision;

    }

    public String RetornaIDVtaDetalleMesVigente() throws SQLException {

        String OwnID = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RetornaIDVtaDetalleMesVigente() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

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
        return OwnID;

    }

    public String RetornaIDMovimientoMesVigente() {

        String OwnID = "";
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RetornaIDMovimientoMesVigente() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

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
        return OwnID;

    }

    public String RecuperaAcronimo(String DescripcionBotica) {

        String OwnID = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ID_BOTICA(?) }");
            procedure.setString(1, DescripcionBotica);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

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


        return OwnID;

    }

    public String VerificaEnvio(String DescripcionBotica) {

        String EnvioRealizado = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ENVIO(?) }");
            procedure.setString(1, DescripcionBotica);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                EnvioRealizado = rs.getString(1);
            }

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


        return EnvioRealizado;

    }
    
    public String RecuperaAcronimo2(String DescripcionBotica) {

        String OwnID = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ACRONIMO(?) }");
            procedure.setString(1, DescripcionBotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

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
        return OwnID;

    }

    public String DescripcionBoticaPorCargo(String DescripcionBotica) {

        String OwnID = "";
        try {

            String recuperado = "";
            String boticaDefinida = "";
            int numeral = 0;

            recuperado = DescripcionBotica.substring(0, 3);
            System.out.println("recuperado:" + recuperado);
            boticaDefinida = recuperado.substring(0, 3).toUpperCase();

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_BOTICA_POR_ID(?) }");
            procedure.setString(1, boticaDefinida);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                System.out.println("rs.getString(1):" + rs.getString(1));
                OwnID = rs.getString(1);
            }


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
        return OwnID;

    }

    public boolean VerificaPoseeAlmacenes(String DescripcionBotica) {

        boolean OwnID = false;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ALMACENES_BOTICA(?) }");
            procedure.setString(1, DescripcionBotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getBoolean(1);
            }

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


        return OwnID;


    }

    public boolean VerificaGuiaPertene(String DescripcionBotica) {
        boolean OwnID = false;

        try {

            //System.out.println("{ call VerificaGuiaPertene('"+DescripcionBotica+"') }");
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VerificaGuiaPertene(?) }");
            procedure.setString(1, DescripcionBotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getBoolean(1);
            }

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

        return OwnID;
    }

    public String RecuperaIDBotica(String DescripcionBotica) {
        String OwnID = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ID_BOTICA(?) }");
            procedure.setString(1, DescripcionBotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

            rs.close();

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


        return OwnID;
    }

    public String RecuperaIDPorAcronimo(String elArchivo) {
        String OwnID = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_ID_POR_ACRONIMO_BOTICA(?) }");
            //System.out.println("call RETORNA_ID_POR_ACRONIMO_BOTICA('"+ elArchivo.substring(0, 3) +"')");
            procedure.setString(1, elArchivo.substring(0, 3));
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getString(1);
            }

            rs.close();

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


        return OwnID;

    }

    public int Verifica_Botica_Cierre(String idbotica) {
        int OwnID = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_BOTICA_CIERRE(?) }");
            procedure.setString(1, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                OwnID = rs.getInt(1);
            }

            rs.close();

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

        return OwnID;
    }

    public List<Surf01> Generera_Surf01(String idbotica, int mes) {
        List<Surf01> Lista_Surf01 = new ArrayList<Surf01>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GENERAR_SURF01(?,?) }");
            procedure.setString("Botica", idbotica);
            procedure.setInt("xmes", mes);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Surf01 entidad = new Surf01();
                entidad.setId_botica(rs.getString("Id_botica"));
                entidad.setInvnum(rs.getString("invnum"));
                entidad.setTyppag(rs.getString("typpag"));
                entidad.setInvfec(rs.getDate("invfec"));
                entidad.setCuscod(rs.getString("cuscod"));
                entidad.setCusruc(rs.getString("cusruc"));
                entidad.setInvtot(rs.getDouble("invtot"));
                entidad.setUSECOD(rs.getString("USECOD"));
                entidad.setUSECAJ(rs.getString("USECAJ"));
                entidad.setINVSTA(rs.getString("INVSTA"));
                entidad.setINVRUC(rs.getString("INVRUC"));
                entidad.setTYPDOC(rs.getString("TYPDOC"));
                entidad.setINVTIM(rs.getString("INVTIM"));
                entidad.setINVIGV(rs.getDouble("INVIGV"));
                entidad.setCUSCAT(rs.getString("CUSCAT"));
                entidad.setINVREP(rs.getString("INVREP"));
                entidad.setINVVTA(rs.getDouble("INVVTA"));
                entidad.setSTAATE(rs.getString("STAATE"));
                entidad.setSerie(rs.getString("Serie"));
                entidad.setNumero(rs.getString("Numero"));
                entidad.setUSEDNI(rs.getString("USEDNI"));
                entidad.setAdministra(rs.getString("Administra"));
                entidad.setDnicaja(rs.getString("dnicaja"));
                Lista_Surf01.add(entidad);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Surf01;
    }

    public boolean guardarbitacora(String idbotica,String fecha, String opcion) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDARBITACORA (?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("Fecha", fecha);
            procedure.setString("opc", opcion);

            procedure.execute();
            conex.commit();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Cierre_Caja :" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("HIZE ROLLBACK" + ex1.getMessage());
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
    
    public List<Surf02> Generera_Surf02(String idbotica, int mes) {
        List<Surf02> Lista_Surf02 = new ArrayList<Surf02>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GENERAR_SURF02(?,?) }");
            procedure.setString("Botica", idbotica);
            procedure.setInt("xmes", mes);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Surf02 entidad = new Surf02();
                entidad.setInvnum(rs.getString("invnum"));
                entidad.setCodpro(rs.getString("codpro"));
                entidad.setQTYPRF(rs.getString("QTYPRF"));
                entidad.setQTYPRO(rs.getDouble("QTYPRO"));
                entidad.setPRISAL(rs.getDouble("PRISAL"));
                entidad.setPripro(rs.getDouble("pripro"));
                entidad.setSTKALM(rs.getInt("STKALM"));
                entidad.setSTKALM_M(rs.getInt("STKALM_M"));
                entidad.setSTKMOS(rs.getInt("STKMOS"));
                entidad.setSTKMOS_M(rs.getInt("STKMOS_M"));
                entidad.setSTKTOD(rs.getInt("STKTOD"));
                entidad.setSTKTOD_M(rs.getInt("STKTOD_M"));
                entidad.setINVTIM(rs.getString("INVTIM"));
                entidad.setPORIGV(rs.getDouble("PORIGV"));
                entidad.setDTOPRO(rs.getDouble("DTOPRO"));

                Lista_Surf02.add(entidad);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA Generera_Surf02:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Surf02;
    }

    public List<Surf31> Generera_Surf31(String idbotica, int mes) {
        List<Surf31> Lista_Surf31 = new ArrayList<Surf31>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GENERAR_SURF31(?,?) }");
            procedure.setString("Botica", idbotica);
            procedure.setInt("xmes", mes);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Surf31 entidad = new Surf31();
                entidad.setCodalm(rs.getString("CODALM"));
                entidad.setTypmov(rs.getString("TYPMOV"));
                entidad.setCodprv(rs.getString("TYPMOV"));
                entidad.setCodprv(rs.getString("CODPRV"));
                entidad.setDocnum(rs.getString("DOCNUM"));
                entidad.setDocdat(rs.getDate("DOCDAT"));
                entidad.setDoctot(rs.getDouble("DOCTOT"));
                entidad.setDocobs(rs.getString("DOCOBS"));
                entidad.setAdministra(rs.getString("Administra"));
                entidad.setDatreg(rs.getDate("DATREG"));
                entidad.setUsecod(rs.getString("USECOD"));
                entidad.setDoctim(rs.getString("DOCTIM"));
                Lista_Surf31.add(entidad);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA Generera_Surf31:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Surf31;
    }

    public List<Surf32> Generera_Surf32(String idbotica, int mes) {
        List<Surf32> Lista_Surf32 = new ArrayList<Surf32>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GENERAR_SURF32(?,?) }");
            procedure.setString("Botica", idbotica);
            procedure.setInt("xmes", mes);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Surf32 entidad = new Surf32();
                entidad.setCodalm(rs.getString("CODALM"));
                entidad.setTypmov(rs.getString("TYPMOV"));
                entidad.setCodprv(rs.getString("TYPMOV"));
                entidad.setCodprv(rs.getString("CODPRV"));
                entidad.setDocnum(rs.getString("DOCNUM"));
                entidad.setCodpro(rs.getString("CODPRO"));
                entidad.setPrisal(rs.getDouble("PRISAL"));
                entidad.setStkalm(rs.getInt("STKALM"));
                entidad.setStkalm_m(rs.getInt("STKALM_M"));
                entidad.setStkmos(rs.getInt("STKMOS"));
                entidad.setStkmos_m(rs.getInt("STKMOS_M"));
                entidad.setStktod(rs.getInt("STKTOD"));
                entidad.setStktod_m(rs.getInt("STKTOD_M"));
                entidad.setStktim(rs.getString("STKTIM"));
                entidad.setStkprf(rs.getString("STKPRF"));
                entidad.setStksed(rs.getDouble("STKSED"));
                Lista_Surf32.add(entidad);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Surf32;
    }

    public List<Surf10> Generera_Surf10(String idbotica) {
        List<Surf10> Lista_Surf10 = new ArrayList<Surf10>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call GENERAR_SURF10(?) }");
            procedure.setString("Botica", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                Surf10 entidad = new Surf10();
                entidad.setCodpro(rs.getString("codpro"));
                entidad.setCodlab(rs.getString("codlab"));
                entidad.setCodtip(rs.getString("codtip"));
                entidad.setCodfam(rs.getString("codfam"));
                entidad.setCodfam(rs.getString("codgen"));
                entidad.setDespro(rs.getString("despro"));
                entidad.setStkfra(rs.getInt("stkfra"));
                entidad.setPorigv(rs.getDouble("porigv"));
                entidad.setStkalm(rs.getInt("stkalm"));
                entidad.setStkalm_m(rs.getInt("stkalm_m"));
                entidad.setStkmos(rs.getInt("stkmos"));
                entidad.setStkmos_m(rs.getInt("stkmos_m"));
                entidad.setStktod(rs.getInt("stktod"));
                entidad.setStktod_m(rs.getInt("stktod_m"));
                entidad.setPrisal(rs.getDouble("prisal"));
                entidad.setDtopro(rs.getDouble("dtopro"));
                entidad.setPrecio(rs.getDouble("precio"));
                Lista_Surf10.add(entidad);
            }

            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BOTICASDATA :" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Lista_Surf10;
    }

    public boolean VerificaBoticaCerrada() {
        boolean resul = false;
        int op = 0;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REVISA_BOTICA_CERRADA() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                op = rs.getInt(1);
            }

            rs.close();

            if (op == 1) {
                resul = true;
            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase VerificaBoticaCerrada :" + ex.toString());
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