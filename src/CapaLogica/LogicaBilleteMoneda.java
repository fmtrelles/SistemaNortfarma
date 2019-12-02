/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.BilletesMonedaData;
import entidad.Abono_Caja;
import entidad.Cajas;
import java.util.List;
import entidad.Venta;
import CapaDatos.PersonalDATA;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaBilleteMoneda {

    BilletesMonedaData logBillete;
    PersonalDATA objPersonal;

    public LogicaBilleteMoneda() {
        logBillete = new BilletesMonedaData();
    }

    public List<Abono_Caja> ListaBilletes(Cajas caja) {
        return logBillete.ListaBilletes(caja);
    }
    public List<Abono_Caja> ListaBilletes1(Cajas obj, Date fecha) {
        return logBillete.ListaBilletesArqueo(obj, fecha);
    }
}
