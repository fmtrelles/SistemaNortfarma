package CapaLogica;

import CapaDatos.PersonalDATA;
import entidad.Personal;
import entidad.UsuarioBotica;
import java.sql.SQLException;
import java.util.List;

public class LogicaCPersonal {

    PersonalDATA objpersonal;

    public LogicaCPersonal() {
        objpersonal = new PersonalDATA();
    }

    public List<Personal> ListaCredenciales(Personal obj) throws SQLException {
        return objpersonal.ListaCredenciales(obj);
    }

    public List<UsuarioBotica> Verificausuario(Personal obj) {
        return objpersonal.Verificausuario(obj);
    }

    public boolean Verificausuario_Proforma(Personal obj, String idboti) {
        return objpersonal.Verificausuario_Proforma(obj, idboti);
    }

    public List<String> VerificaImpresora() {
        return objpersonal.VerificaImpresora();
    }
}
