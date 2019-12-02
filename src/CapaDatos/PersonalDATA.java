package CapaDatos;

import CapaLogica.LogicaFechaHora;
import entidad.Personal;
import entidad.Roles;
import entidad.UsuarioBotica;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.Date;
import entidad.Venta;
import entidad.Abono_Caja;
import entidad.Billetes_Monedas;
import entidad.Cajas;
import entidad.Tipo_Moneda;
import javax.swing.JOptionPane;
//import webservices.*;

/**
 *
 * @author Miguel Gomez S. & neowsker
 */
public class PersonalDATA {

    ResultSet rs;
    Connection conex;
    Statement stm;
    private ConexionPool db;
    List<Personal> listapersonal = new ArrayList<Personal>();
    List<Personal> listapersonalBotica = new ArrayList<Personal>();
    private boolean usuariovalido = false;
    List<UsuarioBotica> listusuariobotica = new ArrayList<UsuarioBotica>();
    List<Personal> listapersonal2 = new ArrayList<Personal>();
    List<Personal> listapersonal3 = new ArrayList<Personal>();
    LogicaFechaHora objFecha = new LogicaFechaHora();

    public PersonalDATA() {
        db = new ConexionPool();
    }

    public List<Personal> ListaCredenciales(Personal objpersonal) throws SQLException {

        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ROLES_USUARIO('" + objpersonal.getDNI() + "','" + objpersonal.getContrasena() + "') }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Rol(rs.getInt("ID_ROL"));
                obj.setId_Pagina(rs.getInt("ID_PAGINA"));
                obj.setDescripcionPagina(rs.getString("Descripcion"));
                obj.setDescripcionRoles(rs.getString("Descripcion"));
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setContrasena(rs.getString("Contrasena"));
                obj.setDNI(rs.getString("DNI"));
                obj.setId_Personal(rs.getInt("Id_Personal"));
                listapersonal.add(obj);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PERSONALDATA :" + ex.toString());
        } catch (Exception e) {
            System.out.println("Error CapaDatos clase PERSONALDATA :" + e.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal;

    }

    public List<Personal> RecuperaDatosPersona(String DNI) {

        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATOS_PERSONAL(?) }");
            procedure.setString("vDNI", DNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDescripcionRoles(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setApellido(rs.getString(3));
                obj.setDNI(rs.getString(4));
                obj.setId_Personal(rs.getInt(5));
                obj.setvId_Modelo_Contrato(rs.getString(6));
                obj.setvFecha_Inicio(rs.getDate(7));
                obj.setvFecha_Fin(rs.getDate(8));
                obj.setvCodigo_Administrativo(rs.getString(9));
                listapersonal.add(obj);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal;

    }

    public List<Personal> ListarPersonalNoBotica(String Existente, Integer contador) {

        List<Personal> listapersonalBoticaXXX = new ArrayList<Personal>();
        ResultSet rs01;
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PERSONAL_MASTER(?,?,?) }");
            procedure.setString(1, Existente);
            procedure.setString(2, "_");
            procedure.setInt(3, contador + 1);
            procedure.executeQuery();
            rs01 = procedure.getResultSet();

            while (rs01.next()) {
                obj = new Personal();
                obj.setDNI(rs01.getString(1));
                obj.setApellido(rs01.getString(2));
                obj.setNombre(rs01.getString(3));
                listapersonalBoticaXXX.add(obj);
            }

            rs01.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println("Catch:" + ex.getMessage());
                }
            }
        }

        return listapersonalBoticaXXX;

    }

    public void RetiraFromBotica(String DNI, String IP) throws SQLException {

        conex = new ConexionPool().Conexion2(IP);
        CallableStatement procedure = conex.prepareCall("{ call RETIRA_BOTICA(?) }");
        procedure.setString(1, DNI);
        rs = procedure.executeQuery();

    }

    public void ActualizaRol(String DNI, String Rol, String IP) throws SQLException {

        conex = new ConexionPool().Conexion2(IP);
        CallableStatement procedure = conex.prepareCall("{ call ACTUALIZA_PERSONAL_BOTICA(?,?) }");
        System.out.println("call ACTUALIZA_PERSONAL_BOTICA('" + DNI + "','" + Rol + "')");
        procedure.setString(1, DNI);
        procedure.setString(2, Rol);
        rs = procedure.executeQuery();

    }

    public List<Personal> RecuperaDatosPersona(int idpersonal) {

        List<Personal> listapersonalx1 = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATOS_PERSONAL(?) }");
            procedure.setInt("IDPERSONAL", idpersonal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDescripcionRoles(rs.getString(1));
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setDNI(rs.getString("DNI"));
                obj.setId_Personal(rs.getInt("Id_Personal"));
                obj.setDescpCargo(rs.getString("cargo"));
                obj.setvId_Modelo_Contrato(rs.getString("Descripcion"));
                listapersonalx1.add(obj);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listapersonalx1;

    }

    public List<Personal> RecuperaDatosPersona2(String DNI) {

        List<Personal> listaDatosPersona = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATOS_PERSONAL2(?) }");
            procedure.setString("vDNI", DNI);

            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDescripcionRoles(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setApellido(rs.getString(3));
                obj.setDNI(rs.getString(4));
                obj.setId_Personal(rs.getInt(5));
                obj.setvId_Modelo_Contrato(rs.getString(6));
                obj.setvFecha_Inicio(rs.getDate(7));
                obj.setvFecha_Fin(rs.getDate(8));
                obj.setvCodigo_Administrativo(rs.getString(9));
                listaDatosPersona.add(obj);
            }

            rs.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaDatosPersona;

    }

    public boolean CambiaClave(String INDNI, int INIDPERSONAL, String nuevaclave) {
        boolean resul = false;

        conex = new ConexionPool().getConnection();

        try {
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CAMBIA_CLAVE_USUARIO(?,?,?) }");
            procedure.setString(1, INDNI);
            procedure.setInt(2, INIDPERSONAL);
            procedure.setString(3, nuevaclave);
            procedure.executeQuery();
            conex.commit();
            resul = true;

        } catch (Exception e) {
            try {
                conex.rollback();
            } catch (SQLException ex) {
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

    public List<String> VerificaImpresora() {
        String impresora = null;
        List<String> listaImpresoras = new ArrayList<String>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_IMPRESORA() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                impresora = rs.getString("Valor");
                listaImpresoras.add(impresora);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaImpresoras;

    }

    public List<UsuarioBotica> Verificausuario(Personal objpersonal) {

        try {

            conex = new ConexionPool().getConnection();
            listusuariobotica.removeAll(listusuariobotica);
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_USUARIO(?,?,?) }");
            procedure.setString(1, objpersonal.getDNI());
            procedure.setString(2, objpersonal.getContrasena());
            procedure.setString(3, objpersonal.getId_botica());
            rs = procedure.executeQuery();
            while (rs.next()) {
                if (rs.getInt("Id_Personal") > 0) {
                    listusuariobotica.add(new UsuarioBotica(rs.getInt("Id_Personal"),
                            rs.getInt("Id_Rol"), rs.getInt("ID_PAGINA"), rs.getString("Nombre"),
                            rs.getString("Apellido"), rs.getString("DNI"), rs.getString("Descripcion"),
                            rs.getInt("Cambio")));
                }
            }
            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listusuariobotica;
    }

    public List<Personal> Verifica_Usuario_Cambio(Personal objpersonal) {
        List<Personal> listaUsuarios = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call USUARIO_CAMBIO(?,?,?) }");
            procedure.setString(1, objpersonal.getDNI());
            procedure.setString(2, objpersonal.getContrasena());
            procedure.setString(3, objpersonal.getId_botica());
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setNombre(rs.getString("PERSONAL"));
                obj.setId_Personal(rs.getInt("Id_Personal"));
                obj.setDescpCargo(rs.getString("CARGO"));
                listaUsuarios.add(obj);
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaUsuarios;

    }

    public boolean Verificausuario_Proforma(Personal objpersonal, String idbotica) {
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERI_USU_MODIF_PROFORMA(?,?,?) }");
            procedure.setString(1, objpersonal.getDNI());
            procedure.setString(2, objpersonal.getContrasena());
            procedure.setString(3, idbotica);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                usuariovalido = true;
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase PERSONALDATA verifica_usuario:" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return usuariovalido;
    }

    public void ActualizaDataMaster(Personal obj) throws SQLException {

        conex = new ConexionPool().getConnection();
        String NuevaFechaInicio = "";
        String NuevaFechaFin = "";
        Pattern p = Pattern.compile("/");

        Date fecha = obj.getvFecha_Inicio();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String resultado = formato.format(fecha);

        if (obj.getvFecha_Fin().toString().length() < 0) {
            NuevaFechaFin = "";
        } else {

            Date fecha2 = obj.getvFecha_Fin();
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
            String resultado2 = formato2.format(fecha2);
            String[] items2 = p.split(resultado2);

            for (int i = items2.length - 1; i >= 0; i--) {
                NuevaFechaFin = NuevaFechaFin + items2[i];
                if (i - 1 >= 0) {
                    NuevaFechaFin = NuevaFechaFin + "-";
                }
            }
        }


        String[] items = p.split(resultado);

        for (int i = items.length - 1; i >= 0; i--) {
            NuevaFechaInicio = NuevaFechaInicio + items[i];
            if (i - 1 >= 0) {
                NuevaFechaInicio = NuevaFechaInicio + "-";
            }
        }

        /*vDN text, vCodAdm text, Contrato text, vFecha_Inicio text, vFecha_Fin text, vRol text*/

        CallableStatement procedure = conex.prepareCall("{ call ACTUALIZA_PERSONAL_MASTER(?,?,?,?,?,?) }");
        procedure.setString("vDN", obj.getDescripcionRoles());
        procedure.setString("vCodAdm", obj.getDNI());
        procedure.setString("Contrato", obj.getvId_Modelo_Contrato());
        procedure.setString("vFecha_Inicio", NuevaFechaInicio);
        procedure.setString("vFecha_Fin", NuevaFechaFin);
        procedure.setString("vRol", obj.getvCodigo_Administrativo());
        procedure.executeQuery();

    }

    public List<Personal> Mostrar_Historial(String DNI) throws SQLException {

        List<Personal> listapersonalXX = new ArrayList<Personal>();
        Personal obj;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = conex.prepareCall("{ call MOSTRAR_HISTORIAL_CONTRATOS(?) }");
        procedure.setString("vDNI", DNI);
        rs = procedure.executeQuery();

        while (rs.next()) {
            obj = new Personal();
            obj.setvId_Modelo_Contrato(rs.getString(1));
            obj.setvCodigo_Administrativo(rs.getString(2));
            obj.setvFecha_Inicio(rs.getDate(3));
            obj.setvFecha_Fin(rs.getDate(4));
            listapersonalXX.add(obj);
        }

        conex.close();
        return listapersonalXX;

    }

    public List<Personal> ListaPersonalBotica(String IP) throws SQLException {

        List<Personal> listapersonal78 = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().Conexion2(IP);
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTAR_PERSONAL_BOTICA() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setContrasena(rs.getString(1));
                obj.setDNI(rs.getString(2));
                listapersonal78.add(obj);
            }
        } catch (Exception ex) {
            System.out.println("ListaPersonalBotica:" + ex.getMessage());
        }

        conex.close();
        return listapersonal78;

    }

    public boolean AgregaPersonalFromMasterToBotica(Personal obj, String IP) throws SQLException, ParseException {
        String NuevaFechaInicio = "";
        boolean valor = false;
        String NuevaFechaFin = objFecha.ConvierteFecha(obj.getvFecha_Inicio());

        conex = new ConexionPool().Conexion2(IP);
        conex.setAutoCommit(false);

        CallableStatement procedure = null;

        if (obj.getvFecha_Inicio() != null) {
            NuevaFechaInicio = objFecha.ConvierteFecha(obj.getvFecha_Inicio());
        }

        if (obj.getvFecha_Fin() != null) {
            NuevaFechaFin = objFecha.ConvierteFecha(obj.getvFecha_Fin());
        }

        try {
            // System.out.println("{ call AGREGAR_PERSONAL_BOTICA('"+ obj.getNombre()+"','"+obj.getApellido()+"','"+obj.getContrasena()+"','"+obj.getDNI()+"','"+obj.getvId_Modelo_Contrato()+"',?,?,?,?) }");
            procedure = conex.prepareCall("{ call AGREGAR_PERSONAL_BOTICA(?,?,?,?,?,?,?,?,?) }");
            procedure.setString(1, obj.getNombre());
            procedure.setString(2, obj.getApellido());
            procedure.setString(3, obj.getContrasena());
            procedure.setString(4, obj.getDNI());
            procedure.setString(5, obj.getvId_Modelo_Contrato());
            procedure.setString(6, NuevaFechaInicio);
            procedure.setString(7, NuevaFechaFin);
            procedure.setString(8, obj.getvCodigo_Administrativo());
            procedure.setInt(9, 0);
            procedure.execute();
            conex.commit();
            valor = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return valor;

    }

    public String AgregaPersonalBotica(Personal objeto) throws SQLException, ParseException {
        String condicion = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_PERSONAL_MASTER(?,?,?,?,?,?,?) }");
            procedure.setString("vNombre", objeto.getApellido());
            procedure.setString("vApellido", objeto.getNombre());
            procedure.setString("vDNI", objeto.getDNI());
            procedure.setString("vId_Modelo_Contrato", objeto.getvId_Modelo_Contrato());
            procedure.setString("vFecha_Inicio", objFecha.ConvierteFecha(objeto.getvFecha_Inicio()));
            procedure.setString("vFecha_Fin", objFecha.ConvierteFecha(objeto.getvFecha_Fin()));
            procedure.setString("vCodigo_Administrativo", objeto.getvCodigo_Administrativo());
            procedure.executeQuery();
            conex.commit();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase PERSONALDATA :" + ex.toString());
            condicion = ex.toString();
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return condicion;
    }

    public boolean Modifica_Datos_Personal(Personal objeto) throws ParseException {

        boolean condicion = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_DATOS_PERSONAL(?,?,?,?,?,?,?) }");
            procedure.setString("vNombre", objeto.getApellido());
            procedure.setString("vApellido", objeto.getNombre());
            procedure.setString("vDNI", objeto.getDNI());
            procedure.setString("vId_Modelo_Contrato", objeto.getvId_Modelo_Contrato());
            procedure.setString("vFecha_Inicio", objFecha.ConvierteFecha(objeto.getvFecha_Inicio()));
            procedure.setString("vFecha_Fin", objFecha.ConvierteFecha(objeto.getvFecha_Fin()));
            procedure.setString("vCodigo_Administrativo", objeto.getvCodigo_Administrativo());
            procedure.executeQuery();
            conex.commit();
            condicion = true;

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Modifica_Datos_Personal :" + ex.toString());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return condicion;



    }

    public String RecuperaApellidos(Integer IdPersonal) throws SQLException {

        String completo = "";
        // System.out.println(" call RECUPERA_APELLIDOS_PERSONAL("+IdPersonal+") ");

        //   try{

        conex = new ConexionPool().getConnection();


        CallableStatement procedure = conex.prepareCall("{ call RECUPERA_APELLIDOS_PERSONAL(?) }");
        procedure.setInt(1, IdPersonal);
        rs = procedure.executeQuery();

        while (rs.next()) {

            completo = rs.getString(1) + ", " + rs.getString(2);
        }

        //   }catch(Exception ex){
        //     System.out.println(ex.getMessage());
        //  }

        return completo;

    }

    public int RecuperaRol(int IdPersonal) {

        Integer myrol = 0;


        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call RECUPERA_ROL(?) }");
            procedure.setInt(1, IdPersonal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                myrol = rs.getInt(1);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase Modifica_Datos_Personal :" + ex.toString());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return myrol;

    }

    public List<Personal> RecuperaCodigoAdministrativo() throws SQLException {

        Personal obj;
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;
        procedure = conex.prepareCall("{ call RECUPERA_CODIGOS_ADMINISTRATIVOS() }");
        rs = procedure.executeQuery();

        while (rs.next()) {
            obj = new Personal();
            obj.setDNI(rs.getString(2));
            obj.setContrasena(rs.getString(1));
            listapersonal2.add(obj);
        }

        rs.close();
        return listapersonal2;
    }

    public List<Personal> ListaPersonalBotica_Default(String idbotica) {

        try {

            Personal obj;
            listapersonal3.removeAll(listapersonal3);
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_PERSONAL_BOTICA_DEFAULT(?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setApellido(rs.getString(1));
                obj.setId_Personal(rs.getInt(2));
                listapersonal3.add(obj);
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonalDATA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal3;

    }

    public List<Personal> Busca_Personal(String idbotica, String vendedor) {

        try {

            Personal obj;
            listapersonal3.removeAll(listapersonal3);
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call BUSCA_MI_PERSONAL(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("VENDEDOR", vendedor);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setApellido(rs.getString(1));
                obj.setId_Personal(rs.getInt(2));
                listapersonal3.add(obj);
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonalDATA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listapersonal3;

    }

    public List<Personal> ListaPersonalMiBotica(String Busqueda) {

        List<Personal> listapersonalxx = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTAR_PERSONAL_BOTICA2(?) }");
            procedure.setString(1, "%" + Busqueda + "%");
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDNI(rs.getString(1));
                obj.setApellido(rs.getString(2));
                listapersonalxx.add(obj);
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listapersonalxx;

    }

    public List<Personal> Lista_Cajeros(String idbotica) {
        List<Personal> Lista_Cajeros = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_CAJEROS(?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt(1));
                obj.setApellido(rs.getString(2));
                Lista_Cajeros.add(obj);
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

        return Lista_Cajeros;

    }

    public List<Personal> Lista_Quimico(String idbotica, String usuario ,Date desde, Date hasta) {
        List<Personal> Lista_quimico = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_QUIMICO(?,?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("USUARIO", usuario);
            procedure.setString("FECHA1", String.valueOf(desde));
            procedure.setString("FECHA2", String.valueOf(hasta));
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt(1));
                obj.setApellido(rs.getString(2));
                Lista_quimico.add(obj);
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

        return Lista_quimico;

    }

    public List<Personal> Lista_Temperatura(String idbotica) {
        List<Personal> Lista_quimico = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_PRODUCTOSTEMPERATURA(?) }");
            procedure.setString("IDBOTICA", idbotica);           
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setidtemperatura(rs.getInt(1));
                obj.setDescripcionTemperatura(rs.getString(2));
                Lista_quimico.add(obj);
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

        return Lista_quimico;

    }

    public List<Venta> Lista_DatosVenta(Venta objventa) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            Date fechaVta = objventa.getFecha_Venta();
            SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
            String FechaVtaS = formatoVta.format(fechaVta);

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call REPORTE_CAJA_SALDO_INICIAL (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setInt("IDCAJA", objventa.getId_Caja());
            procedure.setString("FECHA", FechaVtaS);
            procedure.setInt("IDPERSONAL", objventa.getId_Cajero());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getDouble("Saldo")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_DatosVenta" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> Recupera_DatosArqueo(Venta objventa) {
        List<Venta> Recuperalistaventas = new ArrayList<Venta>();
        Venta obj;
        try {

            Date fechaVta = objventa.getFecha_Venta();
            SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
            String FechaVtaS = formatoVta.format(fechaVta);

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATOSARQUEOCAJA (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setInt("IDCAJA", objventa.getId_Caja());
            procedure.setString("FECHA", FechaVtaS);
            procedure.setInt("IDPERSONAL", objventa.getId_Cajero());
            rs = procedure.executeQuery();

            while (rs.next()) {                

                obj = new Venta();
                obj.setId_Botica(rs.getString(1));
                obj.setId_Caja(rs.getInt(2));
                obj.setTurno(rs.getInt(3));
                obj.setId_Cajero(rs.getInt(4));
                obj.setSupervisor(rs.getInt(5));
                obj.setFecha_Venta(rs.getDate(6));
                obj.setNrendicion(rs.getString(7));
                obj.setSinicial(rs.getDouble(8));
                obj.setTotVNetas(rs.getDouble(9));
                obj.setTotVTarj(rs.getDouble(10));
                obj.setTotVNC(rs.getDouble(11));
                obj.setTotCPersonal(rs.getDouble(12));
                obj.setTotCTercero(rs.getDouble(13));
                obj.setTotEfecVtas(rs.getDouble(14));
                obj.setTotDep(rs.getDouble(15));
                obj.setTotPServ(rs.getDouble(16));
                obj.setTotPCta(rs.getDouble(17));
                obj.setTotRetiros(rs.getDouble(18));
                obj.setTotAgente(rs.getDouble(19));
                obj.setTotOGastos(rs.getDouble(20));
                obj.setTotPCliente(rs.getDouble(21));
                obj.setTotEfectivo(rs.getDouble(22));
                obj.setTotEfecEOfi(rs.getDouble(23));
                obj.setTotMtoDeposito(rs.getDouble(24));
                obj.setTotAbono(rs.getDouble(25));
                obj.setTotDifCaja(rs.getDouble(26));
                obj.setobservaciones(rs.getString(27));

                Recuperalistaventas.add(obj);

            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Recupera_DatosArqueo" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Recuperalistaventas;

    }

    public List<Venta> Lista_VerificaDatosArqueo(Venta objventa) {
        List<Venta> listaventas = new ArrayList<Venta>();

        try {

            Date fechaVta = objventa.getFecha_Venta();
            SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
            String FechaVtaS = formatoVta.format(fechaVta);

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_ARQUEOCAJA (?,?,?,?) }");
            procedure.setString("IDBOTICA", objventa.getId_Botica());
            procedure.setInt("IDCAJA", objventa.getId_Caja());
            procedure.setString("FECHA", FechaVtaS);
            procedure.setInt("IDPERSONAL", objventa.getId_Cajero());
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventas.add(new Venta(rs.getString("RESULT"),"0","0","0","0","0"));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Verifica ArqueoCaja" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaventas;

    }

    public List<Venta> ListaBilletesArqueo(Venta objBilletes) {

        List<Venta> lista = new ArrayList<Venta>();
        Billetes_Monedas billete;
        Tipo_Moneda tipomoneda;
        Venta abonoCaja;

        Date fechaVta = objBilletes.getFecha_Venta();
        SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
        String FechaVtaS = formatoVta.format(fechaVta);

        int turnoS = 1;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_BILLETES(?,?,?,?,?) }");
            procedure.setString("IDBOTICA", objBilletes.getId_Botica());
            procedure.setInt("IDCAJA", objBilletes.getId_Caja());
            procedure.setInt("IDPERSONAL", objBilletes.getId_Cajero());
            procedure.setString("MIFECHA", FechaVtaS);
            procedure.setInt("TURNO", turnoS);


            rs = procedure.executeQuery();

            while (rs.next()) {
                billete = new Billetes_Monedas();
                tipomoneda = new Tipo_Moneda();
                abonoCaja = new Venta();
                tipomoneda.setTipo_Moneda(rs.getInt("Tipo_Moneda"));
                tipomoneda.setDescripcion(rs.getString("Descripcion"));
                tipomoneda.setSimbolo(rs.getString("Simbolo"));
                abonoCaja.setCantidad(rs.getInt("Cantidad"));
                abonoCaja.setTotal(rs.getDouble("Total"));
                billete.setTipomoneda(tipomoneda);
                billete.setId_Billete(rs.getInt("Id_Billete"));
                billete.setValor(rs.getDouble("Valor"));
                billete.setRuta(rs.getString("Ruta"));
                billete.setId_Billete(rs.getInt("Id_Billete"));
                abonoCaja.setBiilete(billete);
                lista.add(abonoCaja);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase BilletesMonedaData :" + ex.toString());
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
    
    public List<Venta> Inserta_datosArqueo(Venta objdatos) {
        List<Venta> listaArqueo = new ArrayList<Venta>();

        try {

            Date fechaVta = objdatos.getFecha_Venta();
            SimpleDateFormat formatoVta = new SimpleDateFormat("yyyy/MM/dd");
            String FechaVtaS = formatoVta.format(fechaVta);

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call INSERTA_ARQUEO_CAJA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setString("IDBOTICA", objdatos.getId_Botica());
            procedure.setInt("IDCAJA", objdatos.getId_Caja());
            procedure.setString("FECHA", FechaVtaS);
            procedure.setInt("IDPERSONAL", objdatos.getId_Cajero());
            procedure.setInt("IDTURNO", objdatos.getTurno());
            procedure.setInt("IDSUPERVISOR", objdatos.getSupervisor());
            procedure.setString("RENDICION", objdatos.getNrendicion());
            procedure.setDouble("SINICIAL", objdatos.getSinicial());
            procedure.setDouble("TOTVTANETAS", objdatos.getTotVNetas());
            procedure.setDouble("TOTVTATARJETAS", objdatos.getTotVTarj());
            procedure.setDouble("TOTVTANC", objdatos.getTotVNC());
            procedure.setDouble("TOTCPERSONAL", objdatos.getTotCPersonal());
            procedure.setDouble("TOTCTERCERO", objdatos.getTotCTercero());
            procedure.setDouble("TOTEFECVTAS", objdatos.getTotEfecVtas());
            procedure.setDouble("TOTDEP", objdatos.getTotDep());
            procedure.setDouble("TOTPAGOSRV", objdatos.getTotPServ());
            procedure.setDouble("TOTPAGOCTA", objdatos.getTotPCta());
            procedure.setDouble("TOTRETIROS", objdatos.getTotRetiros());
            procedure.setDouble("TOTAGENTE", objdatos.getTotAgente());
            procedure.setDouble("TOTOGASTOS", objdatos.getTotOGastos());
            procedure.setDouble("TOTPCLIENTES", objdatos.getTotPCliente());
            procedure.setDouble("TOTEFECTIVO", objdatos.getTotEfectivo());
            procedure.setDouble("TOTEFECENVOFI", objdatos.getTotEfecEOfi());
            procedure.setDouble("TOTMTODEPOSITO", objdatos.getTotMtoDeposito());
            procedure.setDouble("TOTABONO", objdatos.getTotAbono());
            procedure.setDouble("TOTDIFCAJA", objdatos.getTotDifCaja());
            procedure.setString("OBSERVACIONES", objdatos.getobservaciones());
            procedure.setDouble("TOTOINGRESOS", objdatos.getTotOIngresos());
            

            rs = procedure.executeQuery();

            while (rs.next()) {
                listaArqueo.add(new Venta(rs.getDouble("IdArqueo")));
            }

            rs.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Lista_Arqueo" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return listaArqueo;

    }

    public List<Personal> Lista_Supervisores(String idbotica,int usuario) {
        List<Personal> Lista_Super = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_SUPERVISORES(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDPERSONAL", usuario);

            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_PersonalSuper(rs.getInt(1));
                obj.setApellidoSuper(rs.getString(2));
                Lista_Super.add(obj);
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

        return Lista_Super;

    }

    public List<Personal> Lista_Gerentes() {
        List<Personal> Lista_Super = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_GERENTES() }");

            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_PersonalGerencia(rs.getInt(1));
                obj.setApellidoGerencia(rs.getString(2));
                Lista_Super.add(obj);
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

        return Lista_Super;

    }

    public List<Personal> Lista_Operador() {
        List<Personal> Lista_Cajeros = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_OPERADORRECARGAS() }");
            //procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setid_productorecargao(rs.getInt(1));
                obj.setDescpoperadorrec(rs.getString(2));
                Lista_Cajeros.add(obj);
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

        return Lista_Cajeros;

    }

    public List<Personal> Lista_Cajeros1(String idbotica,int idcaja) {
        List<Personal> Lista_Cajeros = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_CAJEROS_APERTURA(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setInt("IDCAJA", idcaja);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt(1));
                obj.setApellido(rs.getString(2));
                Lista_Cajeros.add(obj);
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

        return Lista_Cajeros;

    }

    public List<Personal> RecuperaDatosPersona3(String DNI) {

        List<Personal> listapersonalx1 = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_DATOS_PERSONAL2(?) }");
            procedure.setString(1, DNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDNI(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setApellido(rs.getString(3));
                obj.setId_Personal(rs.getInt(4));
                obj.setDescpCargo(rs.getString(5));
                obj.setvId_Modelo_Contrato(rs.getString(6));
                listapersonalx1.add(obj);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listapersonalx1;


    }

    public String RecuperaCodAdm(String dNI) {

        String xlss = "";

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_COD_ADM_POR_DNI(?) }");
            System.out.println("call RECUPERA_COD_ADM_POR_DNI('" + dNI + "')");
            procedure.setString(1, dNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                xlss = rs.getString(1);
            }

            rs.close();
            conex.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return xlss;

    }

    public Date FechaInicioContrato(String dNI) {
        Date xlss = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FECHA_INICIO_CONTRATO(?) }");
            procedure.setString(1, dNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                xlss = rs.getDate(1);
            }

            rs.close();
            conex.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return xlss;
    }

    public Date FechaFinContrato(String dNI) {
        Date xlss = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FECHA_FIN_CONTRATO(?) }");
            procedure.setString(1, dNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                xlss = rs.getDate(1);
            }

            rs.close();
            conex.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return xlss;
    }

    public String RecuperaRol(String dNI) {
        String xlss = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_ROL_DNI(?) }");
            procedure.setString(1, dNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                xlss = rs.getString(1);
            }
            //sdkfsldkfsldkj
            rs.close();
            conex.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return xlss;
    }

    public String RecuperaDNI(String dNI) {

        String xlss = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RecuperaDNICodAm(?) }");
            System.out.println("call RecuperaDNICodAm('" + dNI + "')");
            procedure.setString(1, dNI);
            rs = procedure.executeQuery();

            while (rs.next()) {
                xlss = rs.getString(1);
            }

            rs.close();
            conex.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return xlss;

    }

    public List<Personal> Lista_Datos_Personal() {

        List<Personal> listapersonalx1 = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_DATOS_PERSONAL() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt("Id_Personal"));
                obj.setNombre(rs.getString("Nombre"));
                obj.setApellido(rs.getString("Apellido"));
                obj.setDNI(rs.getString("DNI"));
                obj.setvId_Modelo_Contrato(rs.getString("Descripcion"));
                obj.setvCodigo_Administrativo(rs.getString("Codigo_Administrativo"));
                listapersonalx1.add(obj);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println("Lista_Datos_Personal :" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listapersonalx1;


    }

    public String RecuperaRolBotica(String dNI, String IP) throws SQLException {

        String Rol = "";
        conex = new ConexionPool().Conexion2(IP);
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call RECUPERA_ROL_BOTICA(?) }");
            procedure.setString(1, dNI);
            procedure.execute();
            rs = procedure.executeQuery();

            while (rs.next()) {
                Rol = rs.getString(1);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return Rol;



    }

    public String Recupera_ID_Personal(String DNI) throws SQLException {

        String Rol = "";
        conex = new ConexionPool().getConnection();
        CallableStatement procedure = null;

        try {

            procedure = conex.prepareCall("{ call RECUPERA_ID_PERSONAL_DNI(?) }");
            procedure.setString(1, DNI);
            procedure.execute();
            rs = procedure.executeQuery();

            while (rs.next()) {
                Rol = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return Rol;
    }

    public int Recupera_ID_Personal_Nombre(String nombre) {

        int idpersonal = 0;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call RECUPERA_ID_PERSONAL_NOMBRE(?) }");
            procedure.setString(1, nombre);
            rs = procedure.executeQuery();

            while (rs.next()) {
                idpersonal = rs.getInt(1);
            }

            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }


        return idpersonal;
    }

    public void TrackingUser(int valorsubmenu, int idpersonal_botica, int estado) {

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call TRACKING_PERSONAL(?,?,?) }");
            procedure.setInt(1, valorsubmenu);
            procedure.setInt(2, idpersonal_botica);
            procedure.setInt(3, estado);
            procedure.executeQuery();
            conex.commit();

        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Registro del Tracking :" + ex.toString());
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

    public List<Personal> Lista_Personal_Credito(String idbotica) {

        List<Personal> listapers = new ArrayList<Personal>();
        Personal obj;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PERSONAL_CREDITO(?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt("Id_Cliente"));
                obj.setNombre(rs.getString("Nombre_RazonSocial"));
                obj.setDNI(rs.getString("DNI"));
                obj.setDireccion(rs.getString("Descripcion"));
                obj.setvCodigo_Administrativo(rs.getString("Codigo_Asociado"));
                obj.setSaldo(rs.getDouble("Saldo"));
                listapers.add(obj);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println("Lista_Personal_Credito :" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listapers;
    }

    public List<Personal> Lista_Personal_Aperturado(String idbotica, String fecha1, String fech2) {

        List<Personal> listapers = new ArrayList<Personal>();
        Personal obj;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_PERSONAL_CREDITO_ASIGNADOS(?,?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("FECHA1", fecha1);
            procedure.setString("FECHA2", fech2);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setId_Personal(rs.getInt("Id_Cliente"));
                obj.setIdempresa(rs.getInt("Id_Empresa"));
                listapers.add(obj);
            }

            rs.close();


        } catch (Exception e) {
            System.out.println("Lista_Personal_Aperturado :" + e.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return listapers;

    }

    public List<Personal> Lista_Motorizados(String filtro) {
        List<Personal> Lista_Motorizados = new ArrayList<Personal>();
        Personal persona;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call LISTA_MOTORIZADOS(?) }");
            procedure.setString("FILTRO", filtro);
            rs = procedure.executeQuery();

            while (rs.next()) {
                persona = new Personal();
                persona.setId_Personal(rs.getInt("Id_Personal"));
                persona.setApellido(rs.getString("mororizado"));
                persona.setDNI(rs.getString("DNI"));
                Lista_Motorizados.add(persona);
            }

            rs.close();
            conex.close();

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

        return Lista_Motorizados;

    }

    public List<Personal> ListaPersonalParaBoticas(String filtro) {
        //System.out.println(listaPersonalParaBoticas(filtro));

        List<service.Personal> lp=listaPersonalParaBoticas(filtro);

        List<Personal> lp2 = new ArrayList<Personal>();

       for(int i=0 ;i<lp.size();i++)
       {
           Personal p=new Personal();
           p.setId_Personal(lp.get(i).getIdPersonal());
           p.setApellido(lp.get(i).getApellido());
           p.setNombre(lp.get(i).getNombre());
           p.setContrasena(lp.get(i).getContrasena());
           p.setDNI(lp.get(i).getDNI());
           p.setvCodigo_Administrativo(lp.get(i).getVCodigoAdministrativo());
           p.setPersonal_Cesado(lp.get(i).getPersonalCesado());
           p.setId_botica(lp.get(i).getIdBotica());
           p.setvId_Modelo_Contrato(lp.get(i).getVIdModeloContrato());
           lp2.add(p);
       }

       return lp2;


    }


    public List<Personal> ListaRUCParaBoticas(String EnviarRUC) {

       List<service.Personal> lprRECDATOS=listaRUCParaBoticas(EnviarRUC);
       List<Personal> lpRUC = new ArrayList<Personal>();
        
       for(int i=0 ;i<lprRECDATOS.size();i++)
       {
           Personal pRUC=new Personal();
           pRUC.setSRVC_RUC(lprRECDATOS.get(i).getRUC());
           pRUC.setSRVC_RAZONSOCIAL(lprRECDATOS.get(i).getRAZONSOCIAL());
           pRUC.setSRVC_DIRECCION(lprRECDATOS.get(i).getDIRECCIONRUC());
           pRUC.setSRVC_ESTADO(lprRECDATOS.get(i).getESTADO());
           pRUC.setSRVC_DIRECCIONFISCAL(lprRECDATOS.get(i).getDIRECFISCAL());
           lpRUC.add(pRUC);
       }

       return lpRUC;

    }
    

    public List<Personal> ListaDireccionesRecuperado(String EnviarRUC) {
        List<Personal> Direcciones = new ArrayList<Personal>();
        Personal persona;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarDireccionesRec(?) }");
            procedure.setString("VRUC", EnviarRUC);
            rs = procedure.executeQuery();

            while (rs.next()) {
                persona = new Personal();
                persona.setSRVC_RUC(rs.getString("RUC_DNI"));
                persona.setSRVC_RAZONSOCIAL(rs.getString("Nombre_RazonSocial"));
                persona.setSRVC_DIRECCION(rs.getString("direccion"));
                persona.setSRVC_ESTADO(rs.getString("estado"));                                
                Direcciones.add(persona);
            }

            rs.close();
            conex.close();

        } catch (Exception ex) {
            System.out.println("Error de procedimiento: "+ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Direcciones;

    }

    public List<Roles> verCargosAgignar() {
        List<Roles> listaRoles = new ArrayList<Roles>();
        Roles rol;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call verCargosAsignar() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                rol = new Roles();
                rol.setId_Rol(rs.getInt("Id_Rol"));
                rol.setDescripcion(rs.getString("Descripcion"));
                listaRoles.add(rol);
            }

            rs.close();
            conex.close();

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
        return listaRoles;
    }

    public boolean AgregarPersonal(Personal personal) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call AGREGAR_PERSONAL_BOTICA (?,?,?,?,?,?,?,?,?) }");
            procedure.setInt("vContrasena", personal.getId_Personal());
            procedure.setString("vNombre", personal.getNombre());
            procedure.setString("vApellido", personal.getApellido());
            procedure.setString("vDNI", personal.getDNI());
            procedure.setString("vId_Modelo_Contrato", personal.getvId_Modelo_Contrato());
            procedure.setString("vFecha_Inicio", objFecha.ConvierteFecha(personal.getvFecha_Inicio()));
            procedure.setString("vFecha_Fin", objFecha.ConvierteFecha(personal.getvFecha_Fin()));
            procedure.setString("vCodigo_Administrativo", personal.getvCodigo_Administrativo());
            procedure.setInt("MyRol", personal.getId_Rol());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  Agregar PErsonal :" + ex.toString());
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

    public List<Personal> validaPersonalExistente(int id_personal) {
        List<Personal> listaPersonal = new ArrayList<Personal>();
        Personal personal;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call validaPersonalExistente(?) }");
            procedure.setInt("vPersonal", id_personal);
            rs = procedure.executeQuery();

            while (rs.next()) {
                personal = new Personal();
                personal.setId_Personal(rs.getInt("Id_Personal"));
                listaPersonal.add(personal);
            }

            rs.close();
            conex.close();

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
        return listaPersonal;
    }

    public List<Personal> ListaPersonalMiBoticaRol(String Busqueda) {
        //MAX Cambiode rol/clave
        List<Personal> listapersonalxx = new ArrayList<Personal>();
        Personal obj;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListaPersonalDNAD(?) }");
            procedure.setString(1,Busqueda);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDNI(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setApellido(rs.getString(3));
                obj.setDescpCargo(rs.getString(4));
                listapersonalxx.add(obj);
            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listapersonalxx;

    }
    public List<Roles> ListaRolesVentas() {
        //MAX Cambiode rol/clave
        List<Roles> listapersonalxx = new ArrayList<Roles>();
        Roles obj;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarRolesPersonalVentas() }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Roles();
                obj.setId_Rol(Integer.parseInt(rs.getString(1)));
                obj.setDescripcion(rs.getString(2));
                listapersonalxx.add(obj);
            }
            rs.close();
        } catch (SQLException ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listapersonalxx;

  }
    public void ActualizaRolPersonal(String Descripcion, String dni){
        //MAX Cambiode rol/clave
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ActualizaRolPersonal(?,?) }");
            procedure.setString("vDes", Descripcion);
            procedure.setString("vDN", dni);
            procedure.execute();
            conex.commit();
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  HorarioTrabajoData :" + ex.toString() + ex.getMessage());
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
    }
    public void CambiarClavePersonal(String dni, String clave){
        //MAX Cambiode rol/clave
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call CambiaClavePersonal(?,?) }");
            procedure.setString("vDN", dni);
            procedure.setString("vClave", clave);
            procedure.execute();
            conex.commit();
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  HorarioTrabajoData :" + ex.toString() + ex.getMessage());
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
    }

   
    
    private static java.util.List<service.Personal> listaPersonalParaBoticas(java.lang.String arg0) {
        service.Verips_Service service = new service.Verips_Service();
        service.Verips port = service.getVeripsPort();
        return port.listaPersonalParaBoticas(arg0);
    }

    private static java.util.List<service.Personal> listaRUCParaBoticas(java.lang.String arg0) {
        service.Verips_Service service = new service.Verips_Service();
        service.Verips port = service.getVeripsPort();
        return port.retornarRUCParaBoticas(arg0);
    }

}
