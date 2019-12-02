/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author root
 */
public class DescuentosInvTipo {

    private String idDescuentoTipo;
    private String nombreDescuento;
    private DescuentosTipoAlmacen tipoAlmacen;

    public DescuentosInvTipo() {

    }

    public String getIdDescuentoTipo() {
        return idDescuentoTipo;
    }

    public void setIdDescuentoTipo(String idDescuentoTipo) {
        this.idDescuentoTipo = idDescuentoTipo;
    }

    public String getNombreDescuento() {
        return nombreDescuento;
    }

    public void setNombreDescuento(String nombreDescuento) {
        this.nombreDescuento = nombreDescuento;
    }

    public DescuentosTipoAlmacen getTipoAlmacen() {
        return tipoAlmacen;
    }

    public void setTipoAlmacen(DescuentosTipoAlmacen tipoAlmacen) {
        this.tipoAlmacen = tipoAlmacen;
    }

    

}
