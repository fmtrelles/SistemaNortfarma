/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.HorarioPersonalData;
import entidad.Horarios_Detalle;
import entidad.Horarios_Detalle_Diario;
import entidad.Justificacion;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaPersonalHorario {

    HorarioPersonalData mihorario;

    public LogicaPersonalHorario() {
        mihorario = new HorarioPersonalData();
    }

    public List<Horarios_Detalle> ListaHorarios_Planificados(int mes, String person) {
        return mihorario.ListaHorarios_Planificados(mes, person);
    }

    public boolean ModificaHorario(Horarios_Detalle detalle) {
        return mihorario.ModificaHorario(detalle);
    }

    public boolean InsertaHorarioPlanificado(Horarios_Detalle detalle) {
        return mihorario.InsertaHorarioPlanificado(detalle);
    }

    public List<Horarios_Detalle_Diario> Lista_HorariosDiarios(int horarioDetalle) {
        return mihorario.Lista_HorariosDiarios(horarioDetalle);
    }

    public List<Justificacion> ListaJustificacion() {
        return mihorario.ListaJustificacion();
    }

    public int GuardaHorarioDiario(Horarios_Detalle_Diario detalle) {
        return mihorario.GuardaHorarioDiario(detalle);
    }

    public boolean EliminaHorarioDetalleDiario(int detalle) {
        return mihorario.EliminaHorarioDetalleDiario(detalle);
    }

    public JasperViewer Reporte_HorariosDiarios(String reporte,int idhorario, String mes) {
       return mihorario.Reporte_HorariosDiarios(reporte,idhorario, mes);
    }
}
