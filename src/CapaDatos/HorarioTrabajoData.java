/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import com.mysql.jdbc.Statement;
import entidad.Boticas;
import entidad.Horario_Marcacion;
import entidad.Horario_Trabajo;
import entidad.Horarios;
import entidad.Horarios_Detalle;
import entidad.Personal;
import entidad.Roles;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author kelvin
 */
public class HorarioTrabajoData {

    private ConexionPool db;
    ResultSet rs;
    Statement stm;
    Connection conex;

    public HorarioTrabajoData() {
        db = new ConexionPool();
    }

    public List<Horarios_Detalle> ListaHorariosDetalle(String bot) {

        List<Horarios_Detalle> milista = new ArrayList<Horarios_Detalle>();
        Horarios_Detalle detalle;
        Personal personal;
        Roles rol;
        Horarios horarios;
        
        try {
            
            conex = new ConexionPool().getConnection();
            
            CallableStatement procedure = conex.prepareCall("{ call listaHorariosDetalle (?) }");            
            procedure.setString("vBotica", bot);
            rs = procedure.executeQuery();
            
            while(rs.next()) {
                detalle = new Horarios_Detalle();
                personal = new Personal();
                horarios = new Horarios();
                rol = new Roles();
                personal.setId_Personal(rs.getInt("Id_Personal"));
                personal.setApellido(rs.getString("Apellido"));
                personal.setNombre(rs.getString("Nombre"));
                personal.setDNI(rs.getString("DNI"));
                detalle.setPersonal(personal);
                rol.setDescripcion(rs.getString("Descripcion"));
                detalle.setRol(rol);
                detalle.setPT1_Hora_Inicio(rs.getString("PT1_Hora_Inicio"));
                detalle.setPT1_Hora_Final(rs.getString("PT1_Hora_Final"));
                detalle.setPT2_Hora_Inicio(rs.getString("PT2_Hora_Inicio"));
                detalle.setPT2_Hora_Final(rs.getString("PT2_Hora_Final"));
                horarios.setId_Horario(rs.getInt("Id_Horario"));
                detalle.setHorario(horarios);

                milista.add(detalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos ListaHorarios: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;


    }

    public Boticas VerBotica() {

        Boticas miBotica = new Boticas();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VerMiBotica () }");
            rs = procedure.executeQuery();

            while(rs.next()) {
                miBotica = new Boticas();

                miBotica.setId_Botica(rs.getString("Id_Botica"));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos VerBotica: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return miBotica;
    }

    public Horarios VerDatosHorario(int mes, int anio) {

        Horarios miHorario = new Horarios();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VerDatosHorario(?,?) }");
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            rs = procedure.executeQuery();

            while(rs.next()) {
                miHorario = new Horarios();
                
                miHorario.setAperturado(rs.getInt("Aperturado"));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos VerDatosHorario: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return miHorario;
    }

    public Horarios VerDomingos(int mes, int anio, String bot, String fecha) {

        Horarios miHorario = new Horarios();
        String opc = "1";
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VerificaDomingos(?,?,?,?,?) }");
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            procedure.setString("vBotica", bot);
            procedure.setString("vFecha", fecha);
            procedure.setString("vOpc", opc);
            rs = procedure.executeQuery();

            while(rs.next()) {
                miHorario = new Horarios();

                miHorario.setAperturado(rs.getInt("DOMINGO"));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos VerDatosHorario: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return miHorario;
    }

    public Horarios VerInfDomingos(int mes, int anio, String bot, String fecha) {

        Horarios miHorario = new Horarios();
        String opc = "2";

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VerificaDomingos(?,?,?,?,?) }");
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            procedure.setString("vBotica", bot);
            procedure.setString("vFecha", fecha);
            procedure.setString("vOpc", opc);
            rs = procedure.executeQuery();

            while(rs.next()) {
                miHorario = new Horarios();

                miHorario.setAperturado(rs.getInt("SEMANA"));
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos VerDatosHorario: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return miHorario;
    }

    public List<Horario_Trabajo> ListaHorariosTrabajo(String bot, int mes, int anio) {

        List<Horario_Trabajo> milista = new ArrayList<Horario_Trabajo>();
        Horarios_Detalle hdetalle;
        Horario_Trabajo detalle;
        Personal personal;
        Roles rol;
        Horarios horarios;
        Boticas botica;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call listaHorariosTrabajo2 (?,?,?) }");
            procedure.setString("vBotica", bot);
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            rs = procedure.executeQuery();

            while(rs.next()) {
                hdetalle = new Horarios_Detalle();
                detalle = new Horario_Trabajo();
                personal = new Personal();
                horarios = new Horarios();
                rol = new Roles();
                botica = new Boticas();

                detalle.setId_Horario_Trabajo(rs.getInt("Id_Horario_Trabajo"));
                detalle.setRol(rs.getInt("Id_Rol"));
                botica.setId_Botica(rs.getString("Id_Botica"));
                hdetalle.setBotica(botica);
                personal.setId_Personal(rs.getInt("Id_Personal"));
                personal.setApellido(rs.getString("Apellido"));
                personal.setNombre(rs.getString("Nombre"));
                personal.setDNI(rs.getString("DNI"));
                hdetalle.setPersonal(personal);
                rol.setDescripcion(rs.getString("Descripcion"));
                hdetalle.setRol(rol);
                horarios.setId_Horario(rs.getInt("Id_Horario"));
                hdetalle.setHorario(horarios);
                detalle.setHorario_Detalle(hdetalle);
                detalle.setT1_H_Entrada(rs.getString("T1_H_Entrada"));
                detalle.setT1_H_Salida(rs.getString("T1_H_Salida"));
                detalle.setT2_H_Entrada(rs.getString("T2_H_Entrada"));
                detalle.setT2_H_Salida(rs.getString("T2_H_Salida"));
                detalle.setdescestado(rs.getString("estado"));
                milista.add(detalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos ListaHorariosTrabajadore: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;


    }

    public List<Horario_Trabajo> ListaHorariosTrabajoDomingos(String bot, int mes, int anio, String fecha) {

        List<Horario_Trabajo> milista = new ArrayList<Horario_Trabajo>();
        Horarios_Detalle hdetalle;
        Horario_Trabajo detalle;
        Personal personal;
        Roles rol;
        Horarios horarios;
        Boticas botica;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call listaHorariosTrabajoDomingos (?,?,?,?) }");
            procedure.setString("vBotica", bot);
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            procedure.setString("vFecha", fecha);

            rs = procedure.executeQuery();

            while(rs.next()) {
                hdetalle = new Horarios_Detalle();
                detalle = new Horario_Trabajo();
                personal = new Personal();
                horarios = new Horarios();
                rol = new Roles();
                botica = new Boticas();

                detalle.setId_Horario_Trabajo(rs.getInt("Id_Horario_Trabajo"));
                detalle.setRol(rs.getInt("Id_Rol"));
                botica.setId_Botica(rs.getString("Id_Botica"));
                hdetalle.setBotica(botica);
                personal.setId_Personal(rs.getInt("Id_Personal"));
                personal.setApellido(rs.getString("Apellido"));
                personal.setNombre(rs.getString("Nombre"));
                personal.setDNI(rs.getString("DNI"));
                hdetalle.setPersonal(personal);
                rol.setDescripcion(rs.getString("Descripcion"));
                hdetalle.setRol(rol);
                horarios.setId_Horario(rs.getInt("Id_Horario"));
                hdetalle.setHorario(horarios);
                detalle.setHorario_Detalle(hdetalle);
                detalle.setT1_H_Entrada(rs.getString("T1_H_Entrada"));
                detalle.setT1_H_Salida(rs.getString("T1_H_Salida"));
                detalle.setT2_H_Entrada(rs.getString("T2_H_Entrada"));
                detalle.setT2_H_Salida(rs.getString("T2_H_Salida"));
                detalle.setdescestado(rs.getString("estado"));
                milista.add(detalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos ListaHorariosTrabajadore: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;


    }

    public boolean InsertaHorarioMarcacion(Horario_Marcacion hMarcacion) {
        boolean resul = false;
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call InsertarHorarioMarcacion (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setInt("vHorarioTrabajo", hMarcacion.getId_Horario_Trabajo());
            procedure.setInt("vIdRol", hMarcacion.getId_Rol());
            procedure.setString("vFecha", hMarcacion.getFecha());
            procedure.setString("t1In", hMarcacion.getT1_H_Entrada());
            procedure.setString("t1Out", hMarcacion.getT1_H_Salida());
            procedure.setString("t2In", hMarcacion.getT2_H_Entrada());
            procedure.setString("t2Out", hMarcacion.getT2_H_Salida());
            procedure.setString("vTardanza", hMarcacion.getTardanza());
            procedure.setInt("vFalta", hMarcacion.getFalta());
            procedure.setInt("vJustificacion", hMarcacion.getJustificacion());
            procedure.setInt("vJustificacionFalta", hMarcacion.getJustificacionFalta());
            procedure.setString("vObs", hMarcacion.getObservacion());
            procedure.setString("vCmp", hMarcacion.getCmp());
            procedure.setString("vCID", hMarcacion.getCID());
            procedure.setString("t1PIn", hMarcacion.getT1_P_Entrada());
            procedure.setString("t1POut", hMarcacion.getT1_P_Salida());
            procedure.setString("t2PIn", hMarcacion.getT2_P_Entrada());
            procedure.setString("t2POut", hMarcacion.getT2_P_Salida());
            procedure.execute();
            conex.commit();
            resul = true;
        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  InsertaHorarioMarcacion :" + ex.toString());
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

    public List<Horario_Marcacion> ListaHorariosParaModificar(int vIdPersonal) {
        List<Horario_Marcacion> milista = new ArrayList<Horario_Marcacion>();
        Horario_Marcacion hMarcacion;        
        try {            
            conex = new ConexionPool().getConnection();            
            CallableStatement procedure = conex.prepareCall("{ call listaHorariosModificar(?) }");            
            procedure.setInt("vIdPersonal", vIdPersonal);
            rs = procedure.executeQuery();            
            while(rs.next()) {
                hMarcacion = new Horario_Marcacion();
                hMarcacion.setId_Horario_Marcacion(rs.getInt("Id_Horario_Marcacion"));
                hMarcacion.setFecha(rs.getString("Fecha"));
                hMarcacion.setT1_H_Entrada(rs.getString("T1_H_Entrada"));
                hMarcacion.setT1_H_Salida(rs.getString("T1_H_Salida"));
                hMarcacion.setT2_H_Entrada(rs.getString("T2_H_Entrada"));
                hMarcacion.setT2_H_Salida(rs.getString("T2_H_Salida"));
                hMarcacion.setTardanza(rs.getString("Tardanza"));
                hMarcacion.setFalta((char) rs.getInt("Falta"));
                hMarcacion.setJustificacion((char) rs.getInt("Justificacion"));
                hMarcacion.setJustificacionFalta((char) rs.getInt("JustificacionFalta"));
                hMarcacion.setObservacion(rs.getString("Observacion"));
                hMarcacion.setCmp(rs.getString("cmp"));
                hMarcacion.setCID(rs.getString("CID"));
                milista.add(hMarcacion);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos ListaHorarios: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }
        return milista;
    }
    
    public boolean ModificaMarcacion(Horario_Marcacion marcacion) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call modificarHorarioMarcacion (?,?,?,?,?,?,?,?,?,?,?,?) }");
            procedure.setInt("vIdMarcacion", marcacion.getId_Horario_Marcacion());
            procedure.setInt("vJust", marcacion.getJustificacion());
            procedure.setString("vObs", marcacion.getObservacion());
            procedure.setString("vT1In", marcacion.getT1_H_Entrada());
            procedure.setString("vT1Out", marcacion.getT1_H_Salida());
            procedure.setString("vT2In", marcacion.getT2_H_Entrada());
            procedure.setString("vT2Out", marcacion.getT2_H_Salida());
            procedure.setString("vTard", marcacion.getTardanza());
            procedure.setInt("vFalta", marcacion.getFalta());
            procedure.setInt("vJustFalta", marcacion.getJustificacionFalta());
            procedure.setString("vCmp", marcacion.getCmp());
            procedure.setString("vCID", marcacion.getCID());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase ModificaMarcacion :" + ex.toString());
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

    public boolean EliminarMarcacion(Horario_Marcacion marcacion) {

        boolean resul = false;

        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call eliminarMarcacion (?) }");
            procedure.setInt("vIdMarcacion", marcacion.getId_Horario_Marcacion());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase EliminaMarcacion :" + ex.toString());
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

    public void MoodificarHorarioTrabajo(Horario_Trabajo ht, String dni){
        //MAX
        try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call Actualiza_Horario_trabajo(?,?,?,?,?,?) }");
            procedure.setString("vDN", dni);
            procedure.setString("t1_ll", ht.getT1_H_Entrada());
            procedure.setString("t1_s", ht.getT1_H_Salida());
            procedure.setString("t2_ll", ht.getT2_H_Entrada());
            procedure.setString("t2_s", ht.getT2_H_Salida());
            procedure.setString("ob", ht.getObservacion());
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
     public void EliminarHorarioTrabajo(String dni, String obs){
        //MAX
         try {
            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{call Elimina_Horario_Trabajo(?,?)}");
            procedure.setString("vDN", dni);
            procedure.setString("obser", obs);
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
    public String NuevoHorarioTrabajo(String dni, Horario_Trabajo ht){
        //MAX
        String cadena="";
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{call AgregarNuevoPersonalHorarioDetalle(?,?,?,?,?)}");
            procedure.setString("vDN", dni);
            procedure.setString("t1_ll", ht.getT1_H_Entrada());
            procedure.setString("t1_s", ht.getT1_H_Salida());
            procedure.setString("t2_ll", ht.getT2_H_Entrada());
            procedure.setString("t2_s", ht.getT2_H_Salida());
            rs = procedure.executeQuery();

            while (rs.next()) {
                cadena = rs.getString(1).toString();
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


        return cadena;
    }

    public List<Personal> ListaPersonalMiBotica(String Busqueda) {
        //MAX
        List<Personal> listapersonalxx = new ArrayList<Personal>();
        Personal obj;

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call Lista_Personal_HTrabajo(?) }");
            procedure.setString(1,Busqueda);
            rs = procedure.executeQuery();

            while (rs.next()) {
                obj = new Personal();
                obj.setDNI(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setApellido(rs.getString(3));
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
    public Horario_Trabajo ListaHorarioTrabajoPersona(String Busqueda) {
        //MAX
        Horario_Trabajo obj= new Horario_Trabajo();
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{ call ListarHorarioTrabajo(?) }");
            procedure.setString(1,Busqueda);
            rs = procedure.executeQuery();
            while (rs.next()) {
                obj.setT1_H_Entrada(rs.getString(1));
                obj.setT1_H_Salida(rs.getString(2));
                obj.setT2_H_Entrada(rs.getString(3));
                obj.setT2_H_Salida(rs.getString(4));
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


        return obj;

    }
    public String VerificaHorarioTrabajoPersona(String dni){
        //MAX
        String cadena="";
        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = null;
            procedure = conex.prepareCall("{call VerificaHorarioTrabajoPersona(?)}");
            procedure.setString("vDN", dni);
            rs = procedure.executeQuery();
            while (rs.next()) {
                cadena = rs.getString(1).toString();
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


        return cadena;
    }

    public List<Horario_Trabajo> ListaHorariosReporte(String bot, int mes, int anio, String busq) {
        List<Horario_Trabajo> milista = new ArrayList<Horario_Trabajo>();
        Horarios_Detalle hdetalle;
        Horario_Trabajo detalle;
        Personal personal;
        Roles rol;
        Horarios horarios;
        Boticas botica;

        try {

            conex = new ConexionPool().getConnection();

            CallableStatement procedure = conex.prepareCall("{ call listaHorarioReporte (?,?,?,?) }");
            procedure.setString("vBotica", bot);
            procedure.setInt("vMes", mes);
            procedure.setInt("vAnio", anio);
            procedure.setString("vBusq", busq);
            rs = procedure.executeQuery();

            while(rs.next()) {
                hdetalle = new Horarios_Detalle();
                detalle = new Horario_Trabajo();
                personal = new Personal();
                horarios = new Horarios();
                rol = new Roles();
                botica = new Boticas();

//                detalle.setId_Horario_Trabajo(rs.getInt("Id_Horario_Trabajo"));
                botica.setId_Botica(rs.getString("Id_Botica"));
                hdetalle.setBotica(botica);
                personal.setId_Personal(rs.getInt("Id_Personal"));
                personal.setApellido(rs.getString("Apellido"));
                personal.setNombre(rs.getString("Nombre"));
                personal.setDNI(rs.getString("DNI"));
                hdetalle.setPersonal(personal);
                rol.setDescripcion(rs.getString("Descripcion"));
                hdetalle.setRol(rol);
//                horarios.setId_Horario(rs.getInt("Id_Horario"));
//                hdetalle.setHorario(horarios);
                detalle.setHorario_Detalle(hdetalle);
//                detalle.setT1_H_Entrada(rs.getString("T1_H_Entrada"));
//                detalle.setT1_H_Salida(rs.getString("T1_H_Salida"));
//                detalle.setT2_H_Entrada(rs.getString("T2_H_Entrada"));
//                detalle.setT2_H_Salida(rs.getString("T2_H_Salida"));
                milista.add(detalle);
            }

            rs.close();
        }catch(Exception ex){
            System.out.println("Error CapaDatos ListaHorariosTrabajadore: " + ex.toString());
        }finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;
    }
}

