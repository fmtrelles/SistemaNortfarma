package CapaLogica;

import CapaDatos.RecuperaCaja;
import entidad.Cajas;
import java.util.List;

public class LogicaRecuperaCaja {

    RecuperaCaja objrecupera;

    public LogicaRecuperaCaja() {
        objrecupera = new RecuperaCaja();
    }
    
    public List<Cajas> RecuperaCajas(String IDBOTICA) {
        return objrecupera.ListaCajas(IDBOTICA);
    }

    public boolean CajaApertura(String IDBOTICA, int idcaja, int idpersonal) {
        return objrecupera.CajaAperturada(IDBOTICA, idcaja, idpersonal);
    }

    public boolean CajaAperturada_Numero(String IDBOTICA, int idcaja, int tipventa) {
        return objrecupera.CajaAperturada_Numero(IDBOTICA, idcaja, tipventa);
    }

    public int Devuelve_Caja(String IDBOTICA, int idcajero) {
        return objrecupera.Devuelve_Caja(IDBOTICA, idcajero);
    }

    public List<Cajas> VERIFICA_CAJAS_USUARIOS(String IDBOTICA, int idcajero) {
        return objrecupera.VERIFICA_CAJAS_USUARIOS(IDBOTICA, idcajero);
    }
    public List<Cajas> EJECUTAPROMO() {
        return objrecupera.EJECUTAPROMOS();
    }
    public List<Cajas> VERIFICA_CAJAS_USUARIOS1(String IDBOTICA, int idcajero) {
        return objrecupera.VERIFICA_CAJAS_USUARIOS1(IDBOTICA, idcajero);
    }
}
