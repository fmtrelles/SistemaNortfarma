package CapaDatos;

import java.sql.*;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import entidad.Clientes;
import entidad.Venta;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemanortfarma.Cotizacion;
import sistemanortfarma.OpcionesMenu;

/**
 *
 * @author Miguel Gomez S.
 */
public class ClientesData {

    ResultSet rs;
    Statement stm;
    List<Clientes> listaclientes = new ArrayList<Clientes>();
    List<Venta> lista_deudas = new ArrayList<Venta>();
    List<Venta> Lista = new ArrayList<Venta>();
    List<Clientes> ListMovimientos = new ArrayList<Clientes>();
    List<Clientes> ListDescuento = new ArrayList<Clientes>();
    String rucAsociado;
    String result;
    Connection conex;
    private ConexionPool db;

    public ClientesData() {
        db = new ConexionPool();
    }

    public List<Clientes> listaClientes1(String nomcliente, String idboti) {
        try {

            conex = new ConexionPool().getConnection();
            listaclientes.removeAll(listaclientes);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES (?,?) }");
            procedure.setString(1, nomcliente);
            procedure.setString(2, idboti);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getDouble("Saldo"),
                        rs.getString("DNI"), rs.getString("Descripcion"), rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaclientes;
    }
    
    public List<Clientes> listaTransportista(String nomtransp, String idboti, int OPC) {
        try {

            conex = new ConexionPool().getConnection();
            listaclientes.removeAll(listaclientes);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_TRANSPORTISTA (?,?,?) }");
            procedure.setString(1, nomtransp);
            procedure.setString(2, idboti);
            procedure.setInt(3, OPC);
            
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("ID_TRANSPORTISTA"),
                        rs.getString("RUC"),
                        rs.getString("NOMBRE"),
                        rs.getString("NPLACA"),
                        rs.getString("LICENCIA"), rs.getString("CONSTANCIA"), rs.getString("MARCA"),rs.getString("PLACA")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase LISTA_TRANSPORTISTA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaclientes;
    }
    
    public List<Clientes> listaClientes3(String nomcliente, String idboti) {
        try {

            conex = new ConexionPool().getConnection();
            listaclientes.removeAll(listaclientes);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_GASTOS (?,?) }");
            procedure.setString(1, nomcliente);
            procedure.setString(2, idboti);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getDouble("Saldo"),
                        rs.getString("DNI"), rs.getString("Descripcion"), rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaclientes;
    }

        public List<Clientes> ListaClientesGastos(String nomcliente, String idboti) {
        try {

            conex = new ConexionPool().getConnection();
            listaclientes.removeAll(listaclientes);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_GASTOS (?,?) }");
            procedure.setString(1, nomcliente);
            procedure.setString(2, idboti);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente_gasto"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"),
                        rs.getString("Descripcion"),
                        rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTES_GASTOS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaclientes;
    }

    public List<Clientes> listaClientes_Detallado(String idbotica, String nomcliente) {
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_DETALLADO (?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("NOMCLIENTE", nomcliente);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("Nombre_RazonSocial"), rs.getString("Descripcion"),
                        rs.getString("RUC_DNI"), rs.getString("Direccion"), rs.getString("Telefono"),
                        rs.getString("DNI"), rs.getString("Codigo_Asociado"), rs.getString("Codigo_Adm"), rs.getDouble("Saldo")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaclientes;
    }

    public List<Clientes> Lista_Direcciones(String idbotica, int idcliente, String empresa, String direccion) {
        List<Clientes> lista = new ArrayList<Clientes>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DIRECCIONES (?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            procedure.setString("EMPRESA", empresa);
            procedure.setString("VDIRECCION", direccion);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lista.add(new Clientes(rs.getInt("Numero"), rs.getString("Direccion")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
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
    
    //Agregado 16-11
    
    public List<Clientes> Lista_Direcciones_Nuevo(String idbotica, int idcliente, String empresa, String direccion) {
        List<Clientes> lista = new ArrayList<Clientes>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DIRECCIONES_NUEVO (?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            procedure.setString("EMPRESA", empresa);
            procedure.setString("VDIRECCION", direccion);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                lista.add(new Clientes(rs.getInt("Numero"), rs.getString("Direccion")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
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

    public List<Clientes> Lista_Direcciones(String idbotica, int idcliente, int empresa, String proforma) {
        List<Clientes> lista = new ArrayList<Clientes>();

        try {
            Clientes micliente = null;
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DIRECCIONES_ID (?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            procedure.setInt("EMPRESA", empresa);
            procedure.setString("IDPROFORMA", proforma);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                micliente = new Clientes();
                micliente.setId_mov(rs.getInt("Numero"));
                micliente.setDireccion(rs.getString("Direccion"));
                lista.add(micliente);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
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

    public boolean Modifica_Direccion_Cliente(Clientes obj) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_DIRECCION_CLIENTE(?,?,?,?,?)}");
            procedure.setInt("IDCLIENTE", obj.getId_Cliente());
            procedure.setInt("NUMERO", obj.getId_mov());
            procedure.setString("MIDIRECCION", obj.getDireccion());
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setString("EMPRESA", obj.getEmpresa());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA Modifica_Direccion_Cliente :" + ex.toString());
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

    public Clientes listaClientes_Detallado(String idbotica, int idcliente, String empresa) {

        Clientes objcliente = null;

        try {


            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_CLIENTE(?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            procedure.setString("EMPRESA", empresa);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                objcliente = new Clientes(rs.getString("Nombre_RazonSocial"), rs.getString("Direccion"),
                        rs.getString("Telefono"), rs.getString("DNI"),
                        rs.getString("RUC_DNI"));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return objcliente;
    }

    public String RetornaRUC(String Descripcion) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_RUC(?)}");
            procedure.setString("Descripcion", Descripcion);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                rucAsociado = rs.getNString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return rucAsociado;

    }

    public String BUSCAREXISTERUC(String RUC,String Razonsocial,String direccion,int dfiscal,String opcion, int variasdire) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call UPDATE_RUC(?,?,?,?,?,?)}");
            procedure.setString("VRUC", RUC);
            procedure.setString("VRAZONSOCIAL", Razonsocial);
            procedure.setString("VDIRECCION", direccion);
            procedure.setInt("VDIREFISCAL", dfiscal);
            procedure.setString("VOPC", opcion);
            procedure.setInt("VVARIASDIRE", variasdire);
            
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                result = rs.getString(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return result;

    }

    public List<Clientes> BuscarCliente(String telefono, int ind) {
        try {

            listaclientes.removeAll(listaclientes);
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BUSCA_CLIENTES(?,?) }");
            procedure.setString("TELEFONO1", telefono);
            procedure.setInt("FILTRO", ind);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getString("DNI"), rs.getString("telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return listaclientes;

    }

    public boolean ModificaCliente(Clientes obj) {

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_CLIENTE(?,?,?,?,?)}");
            procedure.setInt("IDCLIENTE", obj.getId_Cliente());
            procedure.setString("RUC", obj.getRUC_DNI());
            procedure.setString("DIRECC", obj.getDireccion());
            procedure.setString("TELEFONO", obj.getTelefono());
            procedure.setString("DNI", obj.getDNI());
            procedure.execute();
            conex.commit();
            return true;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return false;


    }

    public boolean GuardarCliente(Clientes obj) {
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_CLIENTE(?,?,?,?,?)}");
            procedure.setString("RUC", obj.getRUC_DNI());
            procedure.setString("DNI", obj.getDNI());
            procedure.setString("NOMBRE", obj.getNombre_RazonSocial());
            procedure.setString("DIRECC", obj.getDireccion());
            procedure.setString("TELE", obj.getTelefono());
            procedure.execute();
            conex.commit();
            return true;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }
        return false;

    }

    public boolean GuardarCliente2(Clientes obj) {

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDAR_CLIENTE2(?,?,?,?,?,?,?,?,?)}");
            procedure.setString(1, obj.getEmpresa().toString());
            procedure.setString(2, obj.getRUC_DNI().toString());
            procedure.setString(3, obj.getNombre_RazonSocial().toString());
            procedure.setString(4, obj.getDireccion().toString());
            procedure.setString(5, obj.getTelefono().toString());
            procedure.setString(6, obj.getCodADm().toString());
            procedure.setString(7, obj.getCodAsociado().toString());
            procedure.setString(8, obj.getId_Botica());
            procedure.setString(9, obj.getDNI());
            procedure.execute();

            conex.commit();
            return true;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.getLocalizedMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
        return false;

    }

    public List<Clientes> listaClientes(String Empresa, String Filtro, String idbotica) {

        List<Clientes> listaclientesXXXXXX = new ArrayList<Clientes>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CLIENTES_EMPRESA(?,?,?) }");
            procedure.setString(1, Empresa);
            procedure.setString(2, Filtro);
            procedure.setString(3, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientesXXXXXX.add(
                        new Clientes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }

        return listaclientesXXXXXX;


    }

    public List<Clientes> MiListaClientes(String Empresa, String Filtro) {

        List<Clientes> listaclientesXXXXXX = new ArrayList<Clientes>();

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Lista_ClientesPorEmpresa(?,?) }");
            procedure.setString(1, Empresa);
            procedure.setString(2, Filtro);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaclientesXXXXXX.add(
                        new Clientes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listaclientesXXXXXX;

    }

    public List<Clientes> listaClientes2(String Empresa, String Filtro) {

        List<Clientes> listaclientesXXXXXX = new ArrayList<Clientes>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTAR_CLIENTES_EMPRESA3(?,?) }");
            procedure.setString(1, Empresa);
            procedure.setString(2, Filtro);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaclientesXXXXXX.add(
                        new Clientes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }

        return listaclientesXXXXXX;
    }

    public Integer CrearCredito(Clientes entidadClietnes) {
        Integer resultado = 1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CREAR_CREDITO(?,?,?,?,?,?,?)}");
            procedure.setInt(1, entidadClietnes.getId_Cliente());
            procedure.setString(2, entidadClietnes.getEmpresa());
            procedure.setString(3, entidadClietnes.getBotica());
            procedure.setDouble(4, entidadClietnes.getMonto());
            procedure.setString(5, entidadClietnes.getVencimiento());
            procedure.setString(6, OpcionesMenu.getIdbotica().toString());
            procedure.setInt(7, OpcionesMenu.getIdpersonal_botica());
            rs = procedure.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt(1);
            }


            conex.commit();
            rs.close();

        } catch (Exception ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
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

    public boolean CrearCredito(List<Clientes> lista) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;

            for (int i = 0; i < lista.size(); i++) {
                procedure = conex.prepareCall("{ call CREAR_CREDITO(?,?,?,?,?,?,?)}");
                procedure.setInt(1, lista.get(i).getId_Cliente());
                procedure.setString(2, lista.get(i).getEmpresa());
                procedure.setString(3, lista.get(i).getBotica());
                procedure.setDouble(4, lista.get(i).getMonto());
                procedure.setString(5, lista.get(i).getVencimiento());
                procedure.setString(6, lista.get(i).getBotica());
                procedure.setInt(7, OpcionesMenu.getIdpersonal_botica());
                procedure.executeQuery();

            }

            conex.commit();
            resultado = true;


        } catch (Exception ex) {
            try {
                resultado = false;
                conex.rollback();
                System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
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

        return resultado;

    }

    public boolean Cierre_Credito(List<Clientes> lista) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure;

            for (int i = 0; i < lista.size(); i++) {
                procedure = conex.prepareCall("{ call CIERRE_CREDITO_PERSONAL(?,?)}");
                procedure.setString(1, lista.get(i).getId_Botica());
                procedure.setInt(2, lista.get(i).getId_Cliente());
                procedure.executeQuery();

            }

            conex.commit();
            resultado = true;


        } catch (Exception ex) {
            try {
                resultado = false;
                conex.rollback();
                System.out.println("Error CapaDatos clase Cierre_Credito :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
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

        return resultado;

    }

    public boolean Verifica_Existe_Ruc(String ruc, String idbotica, int opc) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call VERIFICA_EXISTE_RUC(?,?,?)}");
            procedure.setString(1, idbotica);
            procedure.setString(2, ruc);
            procedure.setInt(3, opc);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                resultado = true;
            }



        } catch (Exception ex) {
            resultado = false;
            System.out.println("Error CapaDatos clase Cierre_Credito :" + ex.toString());

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
    
    public boolean Verifica_Existe_Medico(String codmedico, String nommedico,String cole,int opc) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call GUARDA_MEDICOS(?,?,?,?)}");
            procedure.setString("COLEGIO", cole);
            procedure.setString("CODMEDICO", codmedico);
            procedure.setString("NOMMEDICO", nommedico);
            procedure.setInt("OPC", opc);
            rs = procedure.executeQuery();
            rs.next();

//            id_MEDICO = rs.getInt(1);
            if (rs.getInt(1) == 1) {
                resultado = true;
            }



        } catch (Exception ex) {
            resultado = false;
            System.out.println("Error CapaDatos clase VERIFICA_MEDICO :" + ex.toString());

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
    
    public boolean Verifica_Existe_Ruc_Transportista(String ruc, String idbotica, int opc) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call VERIFICA_EXISTE_RUC(?,?,?)}");
            procedure.setString(1, idbotica);
            procedure.setString(2, ruc);
            procedure.setInt(3, opc);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                resultado = true;
            }



        } catch (Exception ex) {
            resultado = false;
            System.out.println("Error CapaDatos clase Cierre_Credito :" + ex.toString());

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

    public boolean Verifica_Existe_Ruc_Gastos(String ruc, String idbotica) {

        boolean resultado = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure;
            procedure = conex.prepareCall("{ call VERIFICA_EXISTE_RUC_GASTOS(?,?)}");
            procedure.setString(1, idbotica);
            procedure.setString(2, ruc);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                resultado = true;
            }



        } catch (Exception ex) {
            resultado = false;
            System.out.println("Error CapaDatos clase Cierre_Credito :" + ex.toString());

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

    public int EsDescuento(int descrppago) {

        int resul = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call  VERIFICA_ES_DESCUENTO(?) }");
            procedure.setInt(1, descrppago);
            rs = procedure.executeQuery();

            while (rs.next()) {
                resul = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA EsDescuento :" + ex.toString());
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

    public int Recupera_IdCliente_Descuento(String idbotica, int codigo) {

        int resul = 0;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call  RECUPERA_IDCLIENTE_DESCUENTO(?,?) }");
            procedure.setString(1, idbotica);
            procedure.setInt(2, codigo);
            rs = procedure.executeQuery();

            while (rs.next()) {
                resul = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA Recupera_IdCliente_Descuento :" + ex.toString());
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

    public Double RecuperaSaldo(int IdCliente, String idboti) {

        Double saldo = 0.00;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_SALDO(?,?) }");
            procedure.setInt(1, IdCliente);
            procedure.setString(2, idboti);
            rs = procedure.executeQuery();

            while (rs.next()) {
                saldo = rs.getDouble(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }

        return saldo;
    }

    public void DescuentaSaldo(String Cliente, Double valor) {
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call DESCUENTA_SALDO(?,?) }");
            procedure.setString(1, Cliente);
            procedure.setDouble(2, valor);
            procedure.execute();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("x2" + ex.getMessage());
                }
            }

        }
    }

    public Boolean AumentarLinea(Clientes entidadClietnes, String idbotica) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call AUMENTA_SALDO(?,?,?,?,?) }");
            procedure.setInt(1, entidadClietnes.getId_Cliente());
            procedure.setString(2, entidadClietnes.getEmpresa());
            procedure.setDouble(3, entidadClietnes.getMonto());
            procedure.setInt(4, OpcionesMenu.getIdpersonal_botica());
            procedure.setString(5, idbotica);
            procedure.execute();

            return true;

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase TRTTCLIENTESDATA :" + ex.toString());
            return false;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("x2" + ex.getMessage());
                    return false;
                }
            }
            return true;
        }



    }

    public List<Clientes> Lista_Movimientos_Clientes(Clientes obj, String fechaini, String fechafin) {

        try {

            ListMovimientos.removeAll(ListMovimientos);
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call MOVIMIENTOS_DE_CLIENTES(?,?,?) }");
            procedure.setInt(1, obj.getId_Cliente());
            procedure.setString(2, fechaini);
            procedure.setString(3, fechafin);
            rs = procedure.executeQuery();

            while (rs.next()) {
                ListMovimientos.add(
                        new Clientes(rs.getInt("Id_Mov_Credito"), rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"), rs.getString("Nombre_RazonSocial"), rs.getString("Direccion"),
                        rs.getString("DNI"), rs.getDouble("Consumo"), rs.getDate("Fecha"), rs.getDouble("Saldo")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return ListMovimientos;

    }

    public List<Clientes> Verifica_Descuento_Cliente(int idcliente) {
        try {
            ListDescuento.removeAll(ListDescuento);
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DESCUENTOS_DISPONIBLES(?) }");
            procedure.setInt(1, idcliente);
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                ListDescuento.add(
                        new Clientes(rs.getString("Descripcion"),
                        rs.getInt("Porcentaje_Descuento"),
                        rs.getInt("Id_Descuento"), rs.getString("PRODUCTO"), rs.getString("Id_Laboratorio"),
                        rs.getDouble("Precio_Venta"), rs.getDouble("Descuento_Venta"), rs.getDouble("Descuento_Adicional")));

            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return ListDescuento;

    }

    public List<Clientes> BK_Listar_Clientes() throws SQLException {

        List<Clientes> listAllClients = new ArrayList<Clientes>();

        try {
            conex = new ConexionPool().Conexion3();
            CallableStatement procedure = conex.prepareCall("{ call BK_CLIENTES() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                ListDescuento.add(
                        new Clientes(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDouble(10),
                        rs.getDate(11),
                        rs.getString(12)));

            }

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        }

        return listAllClients;

    }

    public void ActualizarMaestra(List<Clientes> listClientes) {

        try {

            conex = new ConexionPool().getConnection();

            for (Integer i = 0; i < listClientes.size(); i++) {

                CallableStatement procedure = conex.prepareCall("{ call RESTAURA_CLIENTES(?,?,?,?,?,?,?,?,?,?,?,?) }");
                procedure.setInt(1, listClientes.get(i).getIdempresa());
                procedure.setInt(2, listClientes.get(i).getId_Cliente());
                procedure.setString(3, listClientes.get(i).getRUC_DNI());
                procedure.setString(4, listClientes.get(i).getNombre_RazonSocial());
                procedure.setString(5, listClientes.get(i).getDireccion());
                procedure.setString(6, listClientes.get(i).getTelefono());
                procedure.setString(7, listClientes.get(i).getDNI());
                procedure.setString(8, listClientes.get(i).getCodAsociado());
                procedure.setString(9, listClientes.get(i).getCodADm());
                procedure.setDouble(10, listClientes.get(i).getSaldo());
                procedure.setDate(11, Date.valueOf(listClientes.get(i).getFecha().toString()));
                procedure.setString(12, listClientes.get(i).getBotica());
                procedure.execute();

            }

            conex.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        }



    }

    public List<Clientes> listaClientes_PORDNI(String DNI) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_PORDNI(?) }");
            procedure.setString("AUXDNI", DNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getDouble("Saldo"),
                        rs.getString("DNI"), rs.getString("Descripcion"), rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaclientes;
    }

    public List<Clientes> listaClientes_PORUC(String RUC) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_PORRUC(?) }");
            procedure.setString("AUXRUC", RUC);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getDouble("Saldo"),
                        rs.getString("DNI"), rs.getString("Descripcion"), rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaclientes;
    }

    public List<Clientes> listaClientes_Telefono(String telefono) {
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_CLIENTES_PORTELEFONO(?) }");
            procedure.setString("MITELEFONO", telefono);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaclientes.add(
                        new Clientes(rs.getInt("Id_Cliente"),
                        rs.getString("RUC_DNI"),
                        rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getDouble("Saldo"),
                        rs.getString("DNI"), rs.getString("Descripcion"), rs.getString("Telefono")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return listaclientes;
    }

    public int GuardarCliente(Clientes objclien, String idbotica) {

        int id_cliente = -1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_CLIENTE(?,?,?,?,?,?,?,?)}");
            procedure.setString("RUC", objclien.getRUC_DNI());
            procedure.setString("DNI", objclien.getDNI());
            procedure.setString("NOMBRE", objclien.getNombre_RazonSocial());
            procedure.setString("DIRECC", objclien.getDireccion());
            procedure.setString("TELE", objclien.getTelefono());
            procedure.setInt("IDEMPRESA", objclien.getIdempresa());
            procedure.setString("CODASOCIADO", objclien.getCodAsociado());
            procedure.setString("IDBOTICA", idbotica);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_cliente = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }


        return id_cliente;

    }
    
    public int GuardaMedico(Clientes objMedico, String idbotica, int opc) {

        int id_MEDICO = -1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_MEDICOS(?,?,?,?,?)}");
            procedure.setString("COLEGIO", objMedico.getCole()); 
            procedure.setString("CODMEDICO", objMedico.getCodmedico());
            procedure.setString("NOMMEDICO", objMedico.getNommedico()); 
            procedure.setString("COLEGIOORIGINAL", objMedico.getColemedico());
            procedure.setInt("OPC", opc);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_MEDICO = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GUARDA_MEDICOS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }


        return id_MEDICO;

    }
    
    public int GuardarTransportista(Clientes objTransportista, String idbotica) {

        int id_transportista = -1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_TRANSPORTISTA(?,?,?,?,?,?,?)}");
            procedure.setString("RUC", objTransportista.getRUC());
            procedure.setString("TMARCA", objTransportista.getMarca());
            procedure.setString("TRANSPORTISTA", objTransportista.getNomtransportista());
            procedure.setString("TPLACA", objTransportista.getNPlaca());
            procedure.setString("TCONSTANCIA", objTransportista.getConstancia());            
            procedure.setString("TLICENCIA", objTransportista.getLicencia());
            procedure.setString("IDBOTICA", idbotica);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_transportista = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase GUARDA_TRANSPORTISTA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }


        return id_transportista;

    }
    

    public int GuardarClienteGastos(Clientes objclien, String idbotica) {

        int id_cliente = -1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_CLIENTE_GASTOS(?,?,?,?,?,?,?)}");
            procedure.setString("RUC", objclien.getRUC_DNI());
            procedure.setString("DNI", objclien.getDNI());
            procedure.setString("NOMBRE", objclien.getNombre_RazonSocial());
            procedure.setString("DIRECC", objclien.getDireccion());
            procedure.setString("TELE", objclien.getTelefono());
            procedure.setInt("IDEMPRESA", objclien.getIdempresa());            
            procedure.setString("IDBOTICA", idbotica);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_cliente = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESGASTOS :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }


        return id_cliente;

    }

    public List<Venta> Lista_Deudas_Clientes(String idbotica, int idcliente) {


        try {

            conex = new ConexionPool().getConnection();
            lista_deudas.removeAll(lista_deudas);
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DEUDAS_CLIENTES(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista_deudas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDouble("Total"), rs.getDate("Fecha_Registro"),
                        rs.getTime("Hora_Venta"), rs.getString("DESCRIPCION"), rs.getString("serie"), rs.getString("Numero")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return lista_deudas;


    }

    public boolean Guarda_Pagos_Credito(List<Venta> lista, List<Venta> detallePagos, String idbotica, int idpersonal, int idcliente, int idcaja) {
        boolean valor = false;
        CallableStatement procedure = null;
        CallableStatement procedure1 = null;
        int pos = 0;
        double montopagado;

        String idventa;
        String FechaVta;
        int idpago = 0;
        double resto = 1;
        double total = 0;
        int marcado = 0;
        //lista: CONTIENE LA LISTA DE PAGOS
        //detallePagos : CONTIENE LOS INTERNOS DE VENTAS A PAGAR
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);

            procedure = conex.prepareCall("{ call GUARDA_PAGOS_CREDITOS(?,?,?,?,?,?,?)}");
            procedure1 = conex.prepareCall("{ call GUARDA_PAGOS_DETALLE(?,?,?,?,?,?,?,?,?)}");


            for (int i = 0; i < lista.size(); i++) {
                String descrip = lista.get(i).getDescripcionTipoPago() + '%';
                montopagado = lista.get(i).getTotal_producto();

                procedure.setString("IDBOTICA", idbotica);
                procedure.setInt("IDCLIENTE", idcliente);
                procedure.setDouble("MONTO", montopagado);
                procedure.setString("DESCRPPAGO", descrip);
                procedure.setInt("IDEPRSONAL", idpersonal);
                procedure.setString("DOCNUM", lista.get(i).getNumero());
                procedure.setInt("IDCAJA", idcaja);
                rs = procedure.executeQuery();
                rs.next();
                idpago = rs.getInt("MIIDPAGO");
                resto = montopagado - detallePagos.get(pos).getTotal();

                do {

                    if (pos >= detallePagos.size()) {
                        resto = -2;
                    } else {

                        idventa = detallePagos.get(pos).getId_Venta();
                        total = detallePagos.get(pos).getTotal();
                        FechaVta = detallePagos.get(pos).getFechaVta();

                        procedure1.setString("IDBOTICA", idbotica);
                        procedure1.setInt("IDPAGO", idpago);
                        procedure1.setInt("IDCLIENTE", idcliente);
                        procedure1.setString("IDVENTA", idventa);
                        procedure1.setDouble("TOTAL", total);
                        procedure1.setDouble("AMORTIZA", montopagado);
                        procedure1.setDouble("MISALDO", 0);
                        procedure1.setInt("IDCAJA", idcaja);
                        procedure1.setString("FECHAVTA", FechaVta);


                        rs = procedure1.executeQuery();
                        rs.next();
                        resto = rs.getDouble("AUXSALDO");

                        if (resto > 0) {
                            resto = rs.getDouble("AUXSALDO");
                            montopagado = resto;
                            pos++;
                        } else {
                            if (resto == 0) {
                                resto = -2;
                                pos++;
                            }
                        }

                    }

                    marcado++;
                } while (resto > 0 || marcado == 0);


            }

            conex.commit();
            rs.close();
            valor = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase Guarda_Pagos_Credito :" + ex.toString());
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public Clientes Lista_Deuda(String idbotica, int idcliente) {
        Clientes obj = null;

        try {

            conex = new ConexionPool().getConnection();
            lista_deudas.removeAll(lista_deudas);
            CallableStatement procedure = conex.prepareCall("{ CALL LISTA_CLIENTE_DEDUDA(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            rs = procedure.executeQuery();

            while (rs.next()) {

                obj = new Clientes(rs.getString("Nombre_RazonSocial"),
                        rs.getString("RUC_DNI"), rs.getString("DNI"),
                        rs.getDouble("Saldo"));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("CapaDatos clase CLIENTESDATA :" + ex.toString());
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

    public List<Venta> Lista_Ventas_Clientes(String idbotica, int idcliente, String fecha1, String fecha2) {

        List<Venta> lista_ventas = new ArrayList<Venta>();

        try {

            //System.out.println("{ call RECUPERA_VENTAS_CLIENTE('"+idbotica+"','"+idcliente+"','"+fecha1+"','"+fecha2+"') }");
            conex = new ConexionPool().getConnection();
            lista_deudas.removeAll(lista_deudas);
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_VENTAS_CLIENTE(?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("FECHA2", fecha2);
            rs = procedure.executeQuery();

            while (rs.next()) {
                lista_ventas.add(new Venta(rs.getString("Id_Venta"),
                        rs.getDate("Fecha_Venta"), rs.getString("Serie"),
                        rs.getString("Numero"), rs.getString("DescripcionTipoPago"), rs.getDouble("Total"),
                        rs.getDouble("SubTotal"), String.valueOf(rs.getInt("tipoventa"))));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return lista_ventas;
    }

    public List<Venta> Lista_Notas_Credito(String idbotica, int idcliente) {

        List<Venta> Lista_Notas_Credito = new ArrayList<Venta>();
        try {
            conex = new ConexionPool().getConnection();
            Lista_Notas_Credito.removeAll(Lista_Notas_Credito);
            CallableStatement procedure = conex.prepareCall("{ call NOTAS_CREDITO_DESCCLIENTE(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCLIENTE", idcliente);
            rs = procedure.executeQuery();
            while (rs.next()) {
                Lista_Notas_Credito.add(new Venta(rs.getDate("Fecha_Registro"),
                        rs.getString("Id_Venta_Nota_Credito"),
                        rs.getString("Numero_Documento"), rs.getDouble("Total")));
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return Lista_Notas_Credito;


    }

    public boolean ModificaDatosCliente(Clientes obj, List<String> listaDirecciones) {
        boolean resul = false;
        CallableStatement procedure1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_DATA_CLIENTE(?,?,?,?,?,?,?)}");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setInt("IDCLIENTE", obj.getId_Cliente());
            procedure.setString("EMPRESA", obj.getEmpresa());
            procedure.setString("RAZONSOCIAL", obj.getNombre_RazonSocial());
            procedure.setString("MIRUC", obj.getRUC_DNI());
            procedure.setString("MIDNI", obj.getDNI());
            procedure.setString("MITELE", obj.getTelefono());
            procedure.executeQuery();

            for (int i = 0; i < listaDirecciones.size(); i++) {
                System.out.println("{ call INGRESA_CLIENTE_DIRECCION('" + obj.getId_Botica() + "','" + obj.getId_Cliente() + "','" + obj.getEmpresa() + "','" + listaDirecciones.get(i) + "')}");
                procedure1 = conex.prepareCall("{ call INGRESA_CLIENTE_DIRECCION(?,?,?,?)}");
                procedure1.setString("IDBOTICA", obj.getId_Botica());
                procedure1.setInt("IDCLIENTE", obj.getId_Cliente());
                procedure1.setString("EMPRESA", obj.getEmpresa());
                procedure1.setString("MIDIRECCION", listaDirecciones.get(i));
                procedure1.executeQuery();
            }

            System.out.println("hize commit");
            conex.commit();
            resul = true;


        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase CLIENTESDATA :" + ex.toString());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
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
    
    
    public boolean ModificaDatosTransportista(Clientes obj) {
        boolean resul = false;
        

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_DATA_TRANSPORTISTA(?,?,?,?,?,?,?,?)}");
            procedure.setString("IDBOTICA", obj.getId_Botica());
            procedure.setInt("IDTRANSPORTISTA", obj.getId_Transportista());
            procedure.setString("V_LICENCIA", obj.getLicencia());
            procedure.setString("RAZONSOCIAL", obj.getNombre_RazonSocial());
            procedure.setString("MIRUC", obj.getRUC_DNI());
            procedure.setString("V_PLACA", obj.getPlaca());
            procedure.setString("V_MARCA", obj.getMarca());
            procedure.setString("V_CONSTANCIA", obj.getConstancia());
            procedure.executeQuery();

            

            //System.out.println("hize commit");
            conex.commit();
            resul = true;


        } catch (Exception ex) {
            try {
                System.out.println("Error CapaDatos clase MODIFICA_DATA_TRASNPORTISTA :" + ex.toString());
                conex.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClientesData.class.getName()).log(Level.SEVERE, null, ex1);
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

    public Clientes Cliente_Defecto() {
        Clientes objcliente = new Clientes();
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call CLIENTE_DEFECTO()}");
            rs = procedure.executeQuery();
            while (rs.next()) {
                objcliente.setId_Cliente(rs.getInt("Id_Cliente"));
                objcliente.setNombre_RazonSocial(rs.getString("Nombre_RazonSocial"));
                objcliente.setDireccion("DIRECCION");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return objcliente;
    }   
    
    public Clientes vercliente(String dni){
        Clientes cli = new Clientes();
        try {
            
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("call Consultarclientes('"+dni+"')");
            rs= procedure.executeQuery();
            while(rs.next())
            {
                cli.setNombre_RazonSocial(rs.getString("Nombre_RazonSocial"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar :" + ex.getMessage());            
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }            
        }  
         return cli; 
    }

    public List<Clientes> ListaVerClientes(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
