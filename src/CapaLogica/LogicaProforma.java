package CapaLogica;

import CapaDatos.ProformaData;
import entidad.Proforma;
import java.sql.SQLException;
import java.util.List;

public class LogicaProforma {

    ProformaData objproforma;

    public LogicaProforma() {
        objproforma = new ProformaData();
    }

    public String ProformaRealizada(Proforma objProforma, List<Proforma> lista, String moneda, int cantbolsa) {
        return objproforma.ProformaRealizada(objProforma, lista,moneda,cantbolsa);
    }
    public String MontoProformaRealizada(String mprofor) {
        return objproforma.MontoProformaRealizada(mprofor);
    }
    
    public int insertaValidarProformaRealizada(String resultado) {
        return objproforma.insertavalidaProformaRealizada(resultado);
    }
    public int VerificaCajaFeriado(String fecha) {
        return objproforma.VerificaCajaFeriado(fecha);
    }
    public int VerificaRolAcceso(int rol,int opc) {
        return objproforma.VerificaRolAcceso(rol, opc);
    }
    public String RecuperaNumero(String idbotica) {
        return objproforma.RecuperaNumero(idbotica);
    }
    public String ValidaProformaRealizada(String mprofor) {
        return objproforma.ValidarProformaRealizada(mprofor);
    }
    public String ValidaProformaModificada(String mprofor1) {
        return objproforma.ValidarProformaModificada(mprofor1);
    }
    public int insertaCupon(String resultado,String cupon) {
        return objproforma.insertaCupon(resultado,cupon);
    }

    public String ProformaRealizadaSoat(Proforma objProforma) {
        return objproforma.ProformaRealizadaSoat(objProforma);
    }
    
    public String Realiza_Proforma(Proforma objProforma, List<Proforma> lista) {
        return objproforma.Realiza_Proforma(objProforma, lista);
    }

    public List<Proforma> Recupera_Proforma(String idproforma, String idbotica) throws SQLException {
        return objproforma.Recupera_Proforma(idproforma, idbotica);
    }

    public List<Proforma> Recupera_Proforma_Sensitiva(String idproforma, String idbotica) throws SQLException {
        return objproforma.Recupera_Proforma_Sensitiva(idproforma, idbotica);
    }

    public List<Proforma> Recupera_Detalle_Proforma(String idproforma, String idbotica) {
        return objproforma.Recupera_Detalle_Proforma(idproforma, idbotica);
    }

    public boolean Modifica_proforma(List<Proforma> lista, double totalventa) {
        return objproforma.Modifica_Proforma(lista, totalventa);
    }

    public boolean ModificaProforma(String idproforma, String idboti) {
        return objproforma.ModificaProforma(idproforma, idboti);
    }

    public List<Proforma> Recupera_Proforma_Sensitiva1(String idproforma, String idbotica) throws SQLException {
        return objproforma.Recupera_Proforma_Sensitiva1(idproforma, idbotica);
    }

    public List<Proforma> Recupera_Proforma_Coasociado(String idproforma) {
        return objproforma.Recupera_Proforma_Coasociado(idproforma);
    }

    public List<Proforma> Lista_Proformas(String idbotica) {
        return objproforma.Lista_Proformas(idbotica);
    }

    public boolean Veirifca_Proforma_Vendida(String idbotica, String idproforma) {
        return objproforma.Veirifca_Proforma_Vendida(idbotica, idproforma);
    }
    public String modificaventaSoat(String interno, String serievehi,String placavehi, String polizavehi, String certificadovehi) {
        return objproforma.ModificaVentaRealizadaSoat(interno,serievehi,placavehi,polizavehi,certificadovehi);
    }
}
