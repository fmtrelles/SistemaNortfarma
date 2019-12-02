/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

/**
 *
 * @author Kelvin
 */
public class CruceInventario {

    private int idCruceInv;
    private String nombreCruce;
    private double totalDiferencia;

    public CruceInventario(){
        
    }

    public int getIdCruceInv() {
        return idCruceInv;
    }

    public void setIdCruceInv(int idCruceInv) {
        this.idCruceInv = idCruceInv;
    }

    public String getNombreCruce() {
        return nombreCruce;
    }

    public void setNombreCruce(String nombreCruce) {
        this.nombreCruce = nombreCruce;
    }

    public double getTotalDiferencia() {
        return totalDiferencia;
    }

    public void setTotalDiferencia(double totalDiferencia) {
        this.totalDiferencia = totalDiferencia;
    }
    
}
