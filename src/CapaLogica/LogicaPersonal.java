/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import CapaDatos.PersonalDATA;
import entidad.Personal;
import entidad.Roles;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import entidad.Venta;

/**
 *
 * @author Miguel Gomez S.
 */
public class LogicaPersonal {

    PersonalDATA objPersonal;

    public LogicaPersonal() {
        objPersonal = new PersonalDATA();
    }

    public String GuardarPersonalMaster(Personal objeto) throws SQLException, ParseException {
        return objPersonal.AgregaPersonalBotica(objeto);
    }

    public boolean Modifica_Datos_Personal(Personal objeto) throws ParseException {
        return objPersonal.Modifica_Datos_Personal(objeto);
    }

    public void ActualizaDataMaster(Personal objeto) throws SQLException {
        objPersonal.ActualizaDataMaster(objeto);
    }

    public List<Personal> MostrarHistorial(String DNI) throws SQLException {
        return objPersonal.Mostrar_Historial(DNI);
    }

    public List<Personal> ListarPersonalMasterNoBotica(String Existentes, Integer contador) throws SQLException {
        return objPersonal.ListarPersonalNoBotica(Existentes, contador);
    }

    public List<Personal> RecuperaDatosPersona(int idpersonal) {
        return objPersonal.RecuperaDatosPersona(idpersonal);
    }

    public void ActualizaRol(String DNI, String Rol, String IP) throws SQLException {
        objPersonal.ActualizaRol(DNI, Rol, IP);
    }

    public void RetiraFromBotica(String DNI, String IP) throws SQLException {
        objPersonal.RetiraFromBotica(DNI, IP);
    }

    public List<Personal> RecuperaDatosPersona2(String DNI) {
        return objPersonal.RecuperaDatosPersona2(DNI);
    }

    public boolean AgregaPersonalFromMasterToBotica(Personal objeto, String IP) throws SQLException, ParseException {
        return objPersonal.AgregaPersonalFromMasterToBotica(objeto, IP);
    }

    public List<Personal> ListaPersonalBotica(String IP) throws SQLException {
        return objPersonal.ListaPersonalBotica(IP);
    }

    public String RecuperaApellidos(Integer IdPersonal) throws SQLException {

        return objPersonal.RecuperaApellidos(IdPersonal);
    }

    public List<Personal> Verifica_Usuario_Cambio(Personal objpersonal) {
        return objPersonal.Verifica_Usuario_Cambio(objpersonal);
    }

    public int RecuperaRol(int IdPersonal) {
        return objPersonal.RecuperaRol(IdPersonal);
    }

    public List<Personal> RecuperaCodigoAdministrativo() throws SQLException {
        return objPersonal.RecuperaCodigoAdministrativo();
    }

    public List<Personal> ListaPersonalBotica_Default(String idbotica) {
        return objPersonal.ListaPersonalBotica_Default(idbotica);
    }

    public boolean CambiaClave(String INDNI, int INIDPERSONAL, String nuevaclave) {
        return objPersonal.CambiaClave(INDNI, INIDPERSONAL, nuevaclave);
    }

    public List<Personal> ListaPersonalMiBotica(String Busqueda) throws SQLException {
        return objPersonal.ListaPersonalMiBotica(Busqueda);
    }

    public List<Personal> Lista_Cajeros(String idbotica) {
        return objPersonal.Lista_Cajeros(idbotica);
    }

    public List<Personal> Lista_Quimico(String idbotica,String usuario,Date desde, Date hasta) {
        return objPersonal.Lista_Quimico(idbotica,usuario, desde, hasta);
    }
    public List<Personal> Lista_Temperatura(String idbotica) {
        return objPersonal.Lista_Temperatura(idbotica);
    }

    public List<Venta> Lista_DatosVenta(Venta obj) {
        return objPersonal.Lista_DatosVenta(obj);
    }
    public List<Venta> Recupera_DatosArqueoCaja(Venta obj) {
        return objPersonal.Recupera_DatosArqueo(obj);
    }
    public List<Venta> Lista_VerificaDatosArqueo(Venta obj) {
        return objPersonal.Lista_VerificaDatosArqueo(obj);
    }
    public List<Venta> Lista_InsertaDatosArqueo(Venta obj2) {
        return objPersonal.Inserta_datosArqueo(obj2);
    }

    public List<Personal> Lista_Supervisores(String idbotica, int usuario) {
        return objPersonal.Lista_Supervisores(idbotica, usuario);
    }
    public List<Personal> Lista_Gerentes() {
        return objPersonal.Lista_Gerentes();
    }

    public List<Personal> Lista_Operador() {
        return objPersonal.Lista_Operador();
    }

    public List<Personal> Lista_Cajeros1(String idbotica, int idcaja) {
        return objPersonal.Lista_Cajeros1(idbotica, idcaja);
    }

    public List<Personal> RecuperaDatosPersona3(String DNI) {
        return objPersonal.RecuperaDatosPersona3(DNI);
    }

    public String RecuperaCodAdm(String dNI) {
        return objPersonal.RecuperaCodAdm(dNI);
    }

    public Date FechaInicioContrato(String dNI) {
        return objPersonal.FechaInicioContrato(dNI);
    }

    public Date FechaFinContrato(String dNI) {
        return objPersonal.FechaFinContrato(dNI);
    }

    public String Recupera_ROL_DNI(String dNI) {
        return objPersonal.RecuperaRol(dNI);
    }

    public String RecuperaDNI(String dNI) {
        return objPersonal.RecuperaDNI(dNI);
    }

    public List<Personal> Lista_Datos_Personal() {
        return objPersonal.Lista_Datos_Personal();
    }

    public String Recupera_ID_Personal(String DNI) throws SQLException {
        return objPersonal.Recupera_ID_Personal(DNI);
    }

    public String RecuperaRolBotica(String dNI, String IP) throws SQLException {
        return objPersonal.RecuperaRolBotica(dNI, IP);
    }

    public int Recupera_ID_Personal_Nombre(String nombre) {
        return objPersonal.Recupera_ID_Personal_Nombre(nombre);
    }

    public void TrackingUser(int valorsubmenu, int idpersonal_botica, int estado) {
        objPersonal.TrackingUser(valorsubmenu, idpersonal_botica, estado);
    }

    public List<Personal> Lista_Personal_Credito(String idbotica) {
        return objPersonal.Lista_Personal_Credito(idbotica);
    }

    public List<Personal> Lista_Personal_Aperturado(String idbotica, String fecha1, String fech2) {
        return objPersonal.Lista_Personal_Aperturado(idbotica, fecha1, fech2);
    }

    public List<Personal> Busca_Personal(String idbotica, String vendedor) {
        return objPersonal.Busca_Personal(idbotica, vendedor);
    }

    public List<Personal> Lista_Motorizados(String filtro) {
        return objPersonal.Lista_Motorizados(filtro);
    }

    public List<Personal> ListaPersonalParaBoticas(String filtro) {
        return objPersonal.ListaPersonalParaBoticas(filtro);
    }

    public List<Personal> ListaRUCBoticas(String EnviarRUC) {
        return objPersonal.ListaRUCParaBoticas(EnviarRUC);
    }

    public List<Personal> ListaDireccionesRecuperado(String RUC) {
        return objPersonal.ListaDireccionesRecuperado(RUC);
    }

    public List<Roles> verCargosAgignar() {
        return objPersonal.verCargosAgignar();
    }

    public List<Personal> validaPersonalExistente(int personal) {
        return objPersonal.validaPersonalExistente(personal);
    }

    public boolean AgregarPersonal(Personal personal) {
        return objPersonal.AgregarPersonal(personal);
    }

       public void ActualizaRolPersonal(String desc, String dni) {
        objPersonal.ActualizaRolPersonal(desc, dni);
    }

    public List<Personal> ListaPersonalMiBoticaRol(String Busqueda) {
        return objPersonal.ListaPersonalMiBoticaRol(Busqueda);
    }

    public void CambiaClavePersonal(String dni, String clave) {
        objPersonal.CambiarClavePersonal(dni, clave);
    }

    public List<Roles> ListaRolesVentas() {
        return objPersonal.ListaRolesVentas();
    }
}
