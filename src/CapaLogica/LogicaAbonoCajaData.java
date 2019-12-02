/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.AbonoCajaData;
import entidad.Abono_Caja;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaAbonoCajaData {

    AbonoCajaData logData;

    public LogicaAbonoCajaData() {
        logData = new AbonoCajaData();
    }

    public int Guardar_Abono(List<Abono_Caja> listaTotal) {
        return logData.Guardar_Abono(listaTotal);
    }

    public int Guardar_AbonoArqueo(List<Abono_Caja> listaTotal, String rendicion, int idsuper) {
        return logData.Guardar_AbonoArqueo(listaTotal, rendicion, idsuper);
    }
    public int Guardar_EnvioEfecOficina(List<Abono_Caja> listaTotalDatGenerales,List<Abono_Caja> listaTotal, String rendicion, int idgerencia, int usuarioSistema) {
        return logData.Guardar_EnvioEfecOficina(listaTotalDatGenerales,listaTotal, rendicion, idgerencia,usuarioSistema);
    }
}
