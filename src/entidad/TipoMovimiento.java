/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class TipoMovimiento {

    public String Id_TipoMovimiento;
    public String DescripcionMovimiento;    
    public String Direccion;
    public String FRegistro;
    public String FDocumento;
    public String Observaciones;
    private String movimiento;
 

    public TipoMovimiento(String Id_TipoMovimiento, String DescripcionMovimiento) {
        this.Id_TipoMovimiento = Id_TipoMovimiento;
        this.DescripcionMovimiento = DescripcionMovimiento;
    }    

    public TipoMovimiento(String Id_TipoMovimiento, String DescripcionMovimiento, String Direccion) {
        this.Id_TipoMovimiento = Id_TipoMovimiento;
        this.DescripcionMovimiento = DescripcionMovimiento;
        this.Direccion = Direccion;
    }

    public TipoMovimiento(String DescripcionMovimiento) {
        this.DescripcionMovimiento = DescripcionMovimiento;
    }

    /*public TipoMovimiento(int Id_TipoDocumento,String DescripcionTipoDocumento) {
        //throw new UnsupportedOperationException("Not yet implemented");
        this.Id_TipoDocumento = Id_TipoDocumento;
        this.DescripcionTipoDocumento = DescripcionTipoDocumento;
        
    }*/

    public TipoMovimiento() {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getDescripcionMovimiento() {
        return DescripcionMovimiento;
    }

    public void setDescripcionMovimiento(String DescripcionMovimiento) {
        this.DescripcionMovimiento = DescripcionMovimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getId_TipoMovimiento() {
        return Id_TipoMovimiento;
    }

    public void setId_TipoMovimiento(String Id_TipoMovimiento) {
        this.Id_TipoMovimiento = Id_TipoMovimiento;
    }

    public String getFDocumento() {
        return FDocumento;
    }

    public void setFDocumento(String FDocumento) {
        this.FDocumento = FDocumento;
    }

    public String getFRegistro() {
        return FRegistro;
    }

    public void setFRegistro(String FRegistro) {
        this.FRegistro = FRegistro;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    
    public TipoMovimiento(String Id_TipoMovimiento, String movimiento, String DescripcionMovimiento, String Direccion, String FRegistro, String FDocumento, String Observaciones) {
        this.Id_TipoMovimiento = Id_TipoMovimiento;
        this.movimiento = movimiento;
        this.DescripcionMovimiento = DescripcionMovimiento;
        this.Direccion = Direccion;
        this.FRegistro = FRegistro;
        this.FDocumento = FDocumento;
        this.Observaciones = Observaciones;
    }
}