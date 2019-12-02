/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import entidad.Boticas;
import entidad.Horarios_Detalle;
import entidad.Horarios;
import entidad.Horarios_Detalle_Diario;
import entidad.Justificacion;
import entidad.Personal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemanortfarma.VerificaSistema;

/**
 *
 * @author Miguel Gomez S.
 */
public class HorarioPersonalData {

    VerificaSistema objsistema;
    ResultSet rs;
    Statement stm;
    private ConexionPool db;
    Connection conex;

    public HorarioPersonalData() {
        db = new ConexionPool();
    }

    public List<Horarios_Detalle> ListaHorarios_Planificados(int mes, String pers) {

        List<Horarios_Detalle> milista = new ArrayList<Horarios_Detalle>();
        Horarios_Detalle detalle;
        Horarios mihorario;
        Personal persona;
        Boticas botica;

        try {

            conex = new ConexionPool().getConnection();            
            CallableStatement procedure = conex.prepareCall("{ call LISTA_HORARIOSPERSONAL (?,?) }");
            procedure.setInt("MIMES", mes);
            procedure.setString("MIPERSONAL", pers);
            rs = procedure.executeQuery();
            while (rs.next()) {
                detalle = new Horarios_Detalle();
                mihorario = new Horarios();
                persona = new Personal();
                botica = new Boticas();

                mihorario.setId_Horario(rs.getInt("Id_Horario"));
                mihorario.setMes(rs.getInt("Mes"));
                mihorario.setAnio(rs.getInt("Anio"));
                mihorario.setAperturado(rs.getInt("Aperturado"));
                mihorario.setFecha_Registro(rs.getString("Fecha_Registro"));
                detalle.setHorario(mihorario);
                botica.setId_Botica(rs.getString("Id_Botica"));
                detalle.setBotica(botica);
                persona.setId_Personal(rs.getInt("Id_Personal"));
                persona.setApellido(rs.getString("Apellido"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setDNI(rs.getString("DNI"));
                detalle.setPersonal(persona);
                detalle.setId_Horario_Detalle(rs.getInt("Id_Horario_Detalle"));
                detalle.setPT1_Hora_Inicio(rs.getString("PT1_Hora_Inicio"));
                detalle.setPT1_Hora_Final(rs.getString("PT1_Hora_Final"));
                detalle.setPT2_Hora_Inicio(rs.getString("PT2_Hora_Inicio"));
                detalle.setPT2_Hora_Final(rs.getString("PT2_Hora_Final"));
                detalle.setObservacion(rs.getString("Observacion"));
                detalle.setRT1_Hora_Inicio(rs.getString("RT1_Hora_Inicio"));
                detalle.setRT1_Hora_Final(rs.getString("RT1_Hora_Final"));
                detalle.setRT2_Hora_Inicio(rs.getString("RT2_Hora_Inicio"));
                detalle.setRT2_Hora_Final(rs.getString("RT2_Hora_Final"));
                detalle.setDesactivado(rs.getInt("Desactivado"));
                milista.add(detalle);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ListaHorarios_Planificados :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;

    }

    public boolean ModificaHorario(Horarios_Detalle detalle) {

        boolean resul = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_HORARIOPERSONAL (?,?,?,?,?,?,?) }");
            procedure.setInt("IDHORARIO", detalle.getId_Horario_Detalle());
            procedure.setString("IDBOTICA", detalle.getBotica().getId_Botica());
            procedure.setString("H1", detalle.getRT1_Hora_Inicio());
            procedure.setString("H2", detalle.getRT1_Hora_Final());
            procedure.setString("H3", detalle.getRT2_Hora_Inicio());
            procedure.setString("H4", detalle.getRT2_Hora_Final());
            procedure.setString("OBS", detalle.getObservacion());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos clase ModificaHorario :" + ex.toString());
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

    public boolean InsertaHorarioPlanificado(Horarios_Detalle detalle) {
        boolean resul = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call INSERTA_HORARIOPLANIFICADO (?,?,?,?,?,?,?,?,?,?) }");
            procedure.setInt("IDHORARIO", detalle.getHorario().getId_Horario());
            procedure.setString("IDBOTICA", detalle.getBotica().getId_Botica());
            procedure.setInt("IDPERSONAL", detalle.getPersonal().getId_Personal());
            procedure.setInt("IDROL", detalle.getPersonal().getId_Rol());
            procedure.setInt("ESTADO", detalle.getEstado().getId_Estado());
            procedure.setString("OBS", detalle.getObservacion());
            procedure.setString("RT1", detalle.getRT1_Hora_Inicio());
            procedure.setString("RT2", detalle.getRT1_Hora_Final());
            procedure.setString("RT3", detalle.getRT2_Hora_Inicio());
            procedure.setString("RT4", detalle.getRT2_Hora_Final());
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  InsertaHorarioPlanificado :" + ex.toString());
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

    public List<Horarios_Detalle_Diario> Lista_HorariosDiarios(int horarioDetalle) {
        List<Horarios_Detalle_Diario> lista = new ArrayList<Horarios_Detalle_Diario>();
        Horarios_Detalle_Diario horariosDiario;
        Horarios_Detalle detalle;
        Personal persona;
        Boticas botica;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_HORARIOSDIARIOS (?) }");
            procedure.setInt("IDDETALLE", horarioDetalle);
            rs = procedure.executeQuery();

            while (rs.next()) {
                detalle = new Horarios_Detalle();
                horariosDiario = new Horarios_Detalle_Diario();
                persona = new Personal();
                botica = new Boticas();

                botica.setId_Botica(rs.getString("Id_Botica"));
                detalle.setBotica(botica);
                detalle.setId_Horario_Detalle(rs.getInt("Id_Horario_Detalle"));
                persona.setId_Personal(rs.getInt("Id_Personal"));
                persona.setApellido(rs.getString("Apellido"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setDNI(rs.getString("DNI"));
                detalle.setPersonal(persona);
                detalle.setRT1_Hora_Inicio(rs.getString("RT1_Hora_Inicio"));
                detalle.setRT1_Hora_Final(rs.getString("RT1_Hora_Final"));
                detalle.setRT2_Hora_Inicio(rs.getString("RT2_Hora_Inicio"));
                detalle.setRT2_Hora_Final(rs.getString("RT2_Hora_Final"));

                horariosDiario.setId_Horario_Detalle(detalle);
                horariosDiario.setId_Horario_Detalle_Diario(rs.getInt("Id_Horario_Detalle_Diario"));
                horariosDiario.setFecha(rs.getString("mifecha"));
                horariosDiario.setT1_Hora_Inicio(rs.getString("T1_Hora_Inicio"));
                horariosDiario.setT1_Hora_Final(rs.getString("T1_Hora_Final"));
                horariosDiario.setT2_Hora_Inicio(rs.getString("T2_Hora_Inicio"));
                horariosDiario.setT2_Hora_Final(rs.getString("T2_Hora_Final"));
                horariosDiario.setObservacion(rs.getString("Observacion"));
                horariosDiario.setDesactivado(rs.getInt("DESAC"));
                lista.add(horariosDiario);
            }


        } catch (SQLException ex) {
            System.out.println("Error CapaDatos clase Lista_HorariosDiarios :" + ex.toString());
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

    public List<Justificacion> ListaJustificacion() {

        List<Justificacion> lista = new ArrayList<Justificacion>();
        Justificacion justicacion;
        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call LISTA_JUSTIFICACION () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                justicacion = new Justificacion();
                justicacion.setId_Justificacion(rs.getInt("Id_Justificacion"));
                justicacion.setDescripcion(rs.getString("Descripcion"));
                lista.add(justicacion);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ListaJustificacion :" + ex.toString());
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

    public int GuardaHorarioDiario(Horarios_Detalle_Diario detalle) {
        int resul = -1;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_HORARIODIARIO (?,?,?,?,?,?,?,?) }");
            procedure.setInt("IDHORARIO", detalle.getId_Horario_Detalle().getId_Horario_Detalle());
            procedure.setInt("IDJUS", detalle.getId_Justificacion().getId_Justificacion());
            procedure.setString("FECHA", detalle.getFecha());
            procedure.setString("T1", detalle.getT1_Hora_Inicio());
            procedure.setString("T2", detalle.getT1_Hora_Final());
            procedure.setString("T3", detalle.getT2_Hora_Inicio());
            procedure.setString("T4", detalle.getT2_Hora_Final());
            procedure.setString("OBS", detalle.getObservacion());
            rs = procedure.executeQuery();
            conex.commit();
            rs.next();
            resul = rs.getInt(1);

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  GuardaHorarioDiario :" + ex.toString());
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

    public boolean EliminaHorarioDetalleDiario(int detalle) {
        boolean resul = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ELIMINA_HORARIOSDIARIOS (?) }");
            procedure.setInt("IDDETALLE", detalle);
            procedure.execute();
            conex.commit();
            resul = true;

        } catch (Exception ex) {
            try {
                conex.rollback();
                System.out.println("Error CapaDatos  InsertaHorarioPlanificado :" + ex.toString());
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

    public JasperViewer Reporte_HorariosDiarios(String reporte, int idhorario, String mes) {
        JasperPrint jasperPrint;
        Map parameters = new HashMap();
        JasperReport masterReport = null;
        JasperViewer view = null;
        URL archivo;

        try {

            conex = new ConexionPool().getConnection();
            String sistema = "Windows";
            parameters.put("IDHORARIO", idhorario);
            parameters.put("MIMES", mes);
            parameters.put("REPORT_CONNECTION", conex);
            archivo = this.getClass().getResource("/Reportes/" + reporte + ".jasper");

            if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                parameters.put("SUBREPORT_DIR", "Reportes/");
            } else {
                parameters.put("SUBREPORT_DIR", "//Reportes//");
            }

            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            jasperPrint = JasperFillManager.fillReport(masterReport, parameters, conex);
            view = new JasperViewer(jasperPrint, false);

        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return view;

    }
}
