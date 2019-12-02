package CapaLogica;

import CapaDatos.SerieNumeroData;
import entidad.SerieNumero;
import java.util.ArrayList;
import java.util.List;

public class RecuperaSerieNumero {

    private List<SerieNumero> listaserieNumero = new ArrayList<SerieNumero>();
    private List<SerieNumero> listaserieNumeroReimprime = new ArrayList<SerieNumero>();
    SerieNumeroData objdataserienumero = new SerieNumeroData();

    public List<SerieNumero> RecuperaSerieNumero(String idbotica, int idcaja, int tipventa) {
        listaserieNumero = objdataserienumero.retornaSerieNumero(idbotica, idcaja, tipventa);
        return listaserieNumero;
    }
    public List<SerieNumero> RecuperaSerieNumeroReimprime(String idbotica, int idcaja, int tipventa) {
        listaserieNumeroReimprime = objdataserienumero.RecuperaSerieNumeroReimprime(idbotica, idcaja, tipventa);
        return listaserieNumeroReimprime;
    }
}
