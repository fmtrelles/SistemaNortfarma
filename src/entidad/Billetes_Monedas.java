/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author Miguel Gomez S.
 */
public class Billetes_Monedas {

    private int Id_Billete;
    private Tipo_Moneda tipomoneda;
    private double Valor;
    private String Ruta;

    public int getId_Billete() {
        return Id_Billete;
    }

    public void setId_Billete(int Id_Billete) {
        this.Id_Billete = Id_Billete;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public Tipo_Moneda getTipomoneda() {
        return tipomoneda;
    }

    public void setTipomoneda(Tipo_Moneda tipomoneda) {
        this.tipomoneda = tipomoneda;
    }
}
