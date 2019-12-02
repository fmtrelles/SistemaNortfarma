/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.ProveedorData;
import entidad.Proveedor;
import java.util.List;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaProveedor {

    ProveedorData objProveedor;

    public LogicaProveedor() {
        objProveedor = new ProveedorData();
    }

    public List<Proveedor> ListarProveedor() {
        return objProveedor.ListarProveedor();
    }

    public List<Proveedor> Find_Provedores(int op, String valor) {
        return objProveedor.Find_Provedores(op, valor);
    }
    public List<Proveedor> Find_Personal(int op, String valor) {
        return objProveedor.Find_Personal(op, valor);
    }
}
