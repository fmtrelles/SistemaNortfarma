/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author Kelvin
 */
public class CruceDetalle {

    private int idCruceDetalle;
    private CruceInventario idCruce;
    private int num;
    private Producto prodCargo;
    private int uniCargo;
    private int fraCargo;
    private double preCargo;
    private double monCargo;
    private Producto prodDesCargo;
    private int uniDesCargo;
    private int fraDesCargo;
    private double preDesCargo;
    private double monDesCargo;
    private double diferencia;
    private double pvpCar;
    private double pvpDes;
    private double descCar;
    private double descDes;

    public CruceDetalle() {
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public int getFraCargo() {
        return fraCargo;
    }

    public void setFraCargo(int fraCargo) {
        this.fraCargo = fraCargo;
    }

    public int getFraDesCargo() {
        return fraDesCargo;
    }

    public void setFraDesCargo(int fraDesCargo) {
        this.fraDesCargo = fraDesCargo;
    }

    public CruceInventario getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(CruceInventario idCruce) {
        this.idCruce = idCruce;
    }

    public int getIdCruceDetalle() {
        return idCruceDetalle;
    }

    public void setIdCruceDetalle(int idCruceDetalle) {
        this.idCruceDetalle = idCruceDetalle;
    }

    public double getMonCargo() {
        return monCargo;
    }

    public void setMonCargo(double monCargo) {
        this.monCargo = monCargo;
    }

    public double getMonDesCargo() {
        return monDesCargo;
    }

    public void setMonDesCargo(double monDesCargo) {
        this.monDesCargo = monDesCargo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPreCargo() {
        return preCargo;
    }

    public void setPreCargo(double preCargo) {
        this.preCargo = preCargo;
    }

    public double getPreDesCargo() {
        return preDesCargo;
    }

    public void setPreDesCargo(double preDesCargo) {
        this.preDesCargo = preDesCargo;
    }

    public Producto getProdCargo() {
        return prodCargo;
    }

    public void setProdCargo(Producto prodCargo) {
        this.prodCargo = prodCargo;
    }

    public Producto getProdDesCargo() {
        return prodDesCargo;
    }

    public void setProdDesCargo(Producto prodDesCargo) {
        this.prodDesCargo = prodDesCargo;
    }

    public int getUniCargo() {
        return uniCargo;
    }

    public void setUniCargo(int uniCargo) {
        this.uniCargo = uniCargo;
    }

    public int getUniDesCargo() {
        return uniDesCargo;
    }

    public void setUniDesCargo(int uniDesCargo) {
        this.uniDesCargo = uniDesCargo;
    }

    public double getDescCar() {
        return descCar;
    }

    public void setDescCar(double descCar) {
        this.descCar = descCar;
    }

    public double getDescDes() {
        return descDes;
    }

    public void setDescDes(double descDes) {
        this.descDes = descDes;
    }

    public double getPvpCar() {
        return pvpCar;
    }

    public void setPvpCar(double pvpCar) {
        this.pvpCar = pvpCar;
    }

    public double getPvpDes() {
        return pvpDes;
    }

    public void setPvpDes(double pvpDes) {
        this.pvpDes = pvpDes;
    }
    
    
}
