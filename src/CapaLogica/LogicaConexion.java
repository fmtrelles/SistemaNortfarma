/*Miguel Gomez S. 2011-05-27*/
package CapaLogica;

import CapaDatos.ConexionPool;
import java.sql.Connection;

public class LogicaConexion {

    ConexionPool objcone = new ConexionPool();

    public LogicaConexion() {
    }

    public Connection RetornaConexion() {
        return objcone.RetornaConexionReporte();
    }

    public Connection GetConnection() {
        return objcone.getConnection();
    }

}
