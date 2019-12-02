/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.ConfiguracionData;
import entidad.Cajas;
import java.sql.SQLException;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaConfiguracion {

    ConfiguracionData objConfiguracion;

    public LogicaConfiguracion() {
        objConfiguracion = new ConfiguracionData();
    }

    public void GenerarBackup(String idbotica) {
        objConfiguracion.Tablas_Backup(idbotica);
    }

    public boolean Cierre_Caja(Cajas obj) {
        return objConfiguracion.Cierre_Caja(obj);
    }

    public boolean Ingresa_Abono_Caja(Cajas obj) {
        return objConfiguracion.Ingresa_Abono_Caja(obj);
    }

    public void setObjConfiguracion(ConfiguracionData objConfiguracion) {
        this.objConfiguracion = objConfiguracion;
    }

    public boolean Configura_Impresora(int ubica, String puerto, String nombre) {
        return objConfiguracion.Configura_Impresora(ubica, puerto, nombre);
    }

    public String RecuperaImpresoraActiva() throws SQLException {
        return objConfiguracion.RecuperaImpresoraActiva();
    }

    public int Verifica_Acceso_Modificacion() {
        return objConfiguracion.Verifica_Acceso_Modificacion();
    }

    public int Verifica_Realiza_DBF() {
        return objConfiguracion.Verifica_Realiza_DBF();
    }
}
