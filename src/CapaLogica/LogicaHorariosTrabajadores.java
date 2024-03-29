/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaLogica;

import CapaDatos.HorarioTrabajoData;
import entidad.Boticas;
import entidad.Horario_Marcacion;
import entidad.Horario_Trabajo;
import entidad.Horarios;
import entidad.Horarios_Detalle;
import entidad.Personal;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class LogicaHorariosTrabajadores {

    HorarioTrabajoData horarioTrabajo;
    
    public LogicaHorariosTrabajadores() {
        horarioTrabajo = new HorarioTrabajoData();
    }

    public List<Horarios_Detalle> ListaHorarios_Trabajo(String bot) {
        return horarioTrabajo.ListaHorariosDetalle(bot);
    }

    public List<Horario_Trabajo> ListaHorariosTrabajo(String bot, int mes, int anio) {
        return horarioTrabajo.ListaHorariosTrabajo(bot, mes, anio);
    }
    public List<Horario_Trabajo> ListaHorariosTrabajoDomingos(String bot, int mes, int anio, String fecha) {
        return horarioTrabajo.ListaHorariosTrabajoDomingos(bot, mes, anio, fecha);
    }

    public Boticas VerBotica() {
        return horarioTrabajo.VerBotica();
    }

    public Horarios VerDatosHorario(int mes, int anio) {
        return horarioTrabajo.VerDatosHorario(mes, anio);
    }

    public Horarios VerificaDomingo(int mes, int anio, String botica, String fecha) {
        return horarioTrabajo.VerDomingos(mes, anio, botica, fecha);
    }

    public Horarios VerificaInfDomingo(int mes, int anio, String botica, String fecha) {
        return horarioTrabajo.VerInfDomingos(mes, anio, botica, fecha);
    }

    public boolean InsertaHorarioMarcacion(Horario_Marcacion hMarcacion) {
        return horarioTrabajo.InsertaHorarioMarcacion(hMarcacion);
    }

    public List<Horario_Marcacion> ListahorariosTrabajador(int vIdPersonal) {
        return horarioTrabajo.ListaHorariosParaModificar(vIdPersonal);
    }

    public boolean ModificaHorario(Horario_Marcacion marcacion) {
        return horarioTrabajo.ModificaMarcacion(marcacion);
    }

    public boolean EliminaMarcacion(Horario_Marcacion marcacion) {
        return horarioTrabajo.EliminarMarcacion(marcacion);
    }

    public Horario_Trabajo ListaHorarioTrabajoPersona(String busqueda){
        return horarioTrabajo.ListaHorarioTrabajoPersona(busqueda);
    }

    public String Nuevo_Horario_trabajo(String dni, Horario_Trabajo ht){
        return horarioTrabajo.NuevoHorarioTrabajo(dni, ht);
    }

    public void Modifica_Horario_Trabajo(Horario_Trabajo ht,String dni){
        horarioTrabajo.MoodificarHorarioTrabajo(ht, dni);
    }

    public void Elimina_Horario_Trabajo(String dni, String obs){
        horarioTrabajo.EliminarHorarioTrabajo(dni, obs);
    }

    public List<Personal> ListaPersonalMiBotica(String Busqueda){
        return horarioTrabajo.ListaPersonalMiBotica(Busqueda);
    }
    public String VerificaHorarioTrabajoPersona(String dni){
        return horarioTrabajo.VerificaHorarioTrabajoPersona(dni);
    }

    public List<Horario_Trabajo> ListaHorariosReprote(String bot, int mes, int anio, String busq) {
        return horarioTrabajo.ListaHorariosReporte(bot, mes, anio, busq);
    }

}
