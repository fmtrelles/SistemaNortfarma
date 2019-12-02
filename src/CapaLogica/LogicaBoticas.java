package CapaLogica;

import CapaDatos.BoticasData;
import java.sql.SQLException;
import java.util.List;
import entidad.Boticas;
import entidad.Cajas;
import entidad.Surf01;
import entidad.Surf02;
import entidad.Surf10;
import entidad.Surf31;
import entidad.Surf32;
import entidad.Ventas_Tipo_Pago;
import java.util.List;
import entidad.Venta;
import CapaDatos.TipoMovimientoData;

public class LogicaBoticas {

    BoticasData objBoticas;
    TipoMovimientoData objTipoMovimiento;

    public LogicaBoticas() {
        objBoticas = new BoticasData();
    }

    public List<Boticas> ListarobjBoticas() {
        return objBoticas.ListarBoticas();
    }

    public List<Boticas> DescripcionBoticaDefault() {
        return objBoticas.DescripcionBoticaDefault();
    }

    public List<Boticas> ListarobjBoticas2(String Local) {
        return objBoticas.ListarBoticas2(Local);
    }

    public List<Boticas> ListarBoticasDisponiblesZonas(String DescripcionZona) {
        return objBoticas.ListarBoticasDisponiblesZonas(DescripcionZona);
    }

    public List<Boticas> ListarBoticasLibresZonas(String DescripcionZona) {
        return objBoticas.ListarBoticasLibresZonas(DescripcionZona);
    }

    public int AperturaBoticas(List<Cajas> MIlista_Cajas, int idpersonal, List<Ventas_Tipo_Pago> Lista_Ingresos) {
        return objBoticas.AperturaBoticas(MIlista_Cajas, idpersonal, Lista_Ingresos);
    }

    public void ActualizarBoticasZona(String Zona, String TipoBoticas, Integer Enviados) {
        objBoticas.ActualizarBoticasZona(Zona, TipoBoticas, Enviados);
    }

    public List<Boticas> lista_Boticas_Acceso() {
        return objBoticas.lista_Boticas_Acceso();
    }

    public List<Boticas> lista_Version() {
        return objBoticas.lista_Version();
    }

    public String DevuelveIP(String Descripcion) {
        return objBoticas.DevuelveIP(Descripcion);

    }

    public int VerificaMostrarOtraBotica(int Rol, int TipoMovimiento) {
        return objBoticas.VerificaMostrarOtraBotica(Rol, TipoMovimiento);
    }

    public List<Boticas> ListarBoticasMiZona() {
        return objBoticas.ListarBoticasMiZona();
    }
    public List<Boticas> ListarProductos() {
        return objBoticas.ListarProductosAlcohol();
    }

    public List<Boticas> ListarProductosPsicotropicos() {
        return objBoticas.ListarProductosPsicotropicos();
    }

    public String RecuperaCodigoDescargo(String Botica) {
        return objBoticas.RecuperaCodigoDescargo(Botica);
    }
    public String RecuperaCodigoDescargoTrastienda(String Botica) {
        return objBoticas.RecuperaCodigoDescargoTrastienda(Botica);
    }

    /*public String insertaFirma(String Botica, String interno, String serie, String numero, String xmlgenerado) {
        return objBoticas.GuardarFirma(Botica, interno, serie, numero, xmlgenerado);
    }*/
    public String RecuperaDescripcionDescargo(String Botica) {
        return objBoticas.RecuperaDescripcionDescargo(Botica);
    }
    public String RecuperaDescripcionDescargoTrastienda(String Botica) {
        return objBoticas.RecuperaDescripcionDescargoTrastienda(Botica);
    }

    public List<Boticas> ListarBoticasIP() {
        return objBoticas.ListarBoticasIP();
    }

    public boolean verificaCierreBotica() throws SQLException {
        return objBoticas.verificaCierreBotica();
    }

    public boolean verificaCierreBoticaIP(String IpBotica) throws SQLException {
        return objBoticas.verificaCierreBoticaIP(IpBotica);
    }

    public List<Boticas> ListarBoticasAcronimo(String tipoLocacion) {
        return objBoticas.ListarBoticasAcronimo(tipoLocacion);
    }

    public List<Boticas> ListarTipoLocacion() {
        return objBoticas.ListarTipoLocacion();
    }

    public Integer RecuperaTotalDefault() {
        return objBoticas.RecuperaTotalDefault();
    }

    public String RecuperaZonaBotica() {
        return objBoticas.RecuperaZonaBotica();
    }

    public String ReornaCorreo(String idBotica) {
        return objBoticas.RetornaCorreo(idBotica);
    }

    public String Retorna_Correodestino(String idBotica) {
        return objBoticas.Retorna_Correodestino(idBotica);
    }

    public String MiID() throws SQLException {
        return objBoticas.MiID();
    }

    public boolean VerificaDocumentoCerrado(String Caja, String TipoVenta, String Serie, String Numeracion, String Botica) {
        return objBoticas.VerificaDocumentoCerrado(Caja, TipoVenta, Serie, Numeracion, Botica);
    }

    public String RetornaIDVtaDetalleMesVigente() throws SQLException {
        return objBoticas.RetornaIDVtaDetalleMesVigente();
    }

    public String RetornaIDMovimientoMesVigente() {
        return objBoticas.RetornaIDMovimientoMesVigente();
    }

    public String RecuperaAcronimo(String DescripcionBotica) {
        return objBoticas.RecuperaAcronimo(DescripcionBotica);
    }

    public String VerificaEnvio(String DescripcionBotica) {
        return objBoticas.VerificaEnvio(DescripcionBotica);
    }
    
    public String RecuperaAcronimo2(String DescripcionBotica) {
        return objBoticas.RecuperaAcronimo2(DescripcionBotica);
    }

    public String DescripcionBoticaPorCargo(String DescripcionBotica) {
        return objBoticas.DescripcionBoticaPorCargo(DescripcionBotica);
    }

    public boolean VerificaPoseeAlmacenes(String DescripcionBotica) {
        return objBoticas.VerificaPoseeAlmacenes(DescripcionBotica);
    }

    public boolean VerificaGuiaPertene(String DescripcionBotica) {
        return objBoticas.VerificaGuiaPertene(DescripcionBotica);
    }

    public String RecuperaIDBotica(String DescripcionBotica) {
        return objBoticas.RecuperaIDBotica(DescripcionBotica);
    }

    public String RecuperaIDPorAcronimo(String elArchivo) {
        return objBoticas.RecuperaIDPorAcronimo(elArchivo);
    }

    public int Verifica_Seleccion_Turno(Cajas obj) {
        return objBoticas.Verifica_Seleccion_Turno(obj);
    }

    public int Verifica_TotalMov(String idbotica, String userMov, String fecha, String doc,int caja,int turno,double mto, int item) {
        return objBoticas.Verifica_TotalMov(idbotica, userMov, fecha,  doc,caja,turno,mto, item);
    }
    public int InsertaTemp(String DatosLab, String OPC) {
        return objBoticas.InsertaTemp(DatosLab,OPC);
    }
    public int Verifica_TotalFecha(String idbotica, String userMov, String fecha, String fecha1) {
        return objBoticas.Verifica_TotalFecha(idbotica, userMov, fecha,  fecha1);
    }
    public int Verifica_MaxItemFecha(String idbotica, String userMov, String fecha, String fecha1) {
        return objBoticas.Verifica_MaxItemFecha(idbotica, userMov, fecha,  fecha1);
    }
    public int Verifica_ITEM(String idbotica, String userMov, String fecha, String fecha1,String doc) {
        return objBoticas.Verifica_ITEM(idbotica, userMov, fecha,  fecha1, doc);
    }
    public int Limia_ITEM(String idbotica, int userMov, String desde, String hasta) {
        return objBoticas.Limpia_ITEM(idbotica, userMov, desde,hasta);
    }
    public int InsertaCorrelativo(String idbotica, int userMov, String desde, String hasta) {
        return objBoticas.InsertaCorrelativo(idbotica, userMov, desde,hasta);
    }
    public int Mto_Limite(String idbotica) {
        return objBoticas.Mto_Limite(idbotica);
    }
    public int Verifica_SUMAITEM(String idbotica, String userMov, String fecha, String fecha1,String item) {
        return objBoticas.Verifica_SUMAITEM(idbotica, userMov, fecha,  fecha1, item);
    }
    public int Trae_ITEMNuevo(String idbotica, String userMov, String fecha, String fecha1,String item,String fechaLinea, String fechaactual) {
        return objBoticas.Trae_ITEMNuevo(idbotica, userMov, fecha,  fecha1, item, fechaLinea,fechaactual);
    }
    public List<Venta> Verifica_TotalItem(String idbotica, int userMov, String desde, String hasta) {
        return objBoticas.Verifica_TotalItem(idbotica, userMov, desde,hasta);
    }

    public List<Venta> Verifica_DatosMov(String idbotica, int userMov, String desde, String hasta) {
        return objBoticas.Lista_DatosMov(idbotica, userMov, desde,hasta);
    }
    public List<Venta> Verifica_DatosMov1(String idbotica, int userMov, String desde, String hasta) {
        return objBoticas.Lista_DatosMov1(idbotica, userMov, desde,hasta);
    }

    public int Verifica_Cierre_Botica(String idbotica) {
        return objBoticas.Verifica_Botica_Cierre(idbotica);
    }

    public List<Surf01> Generera_Surf01(String idbotica, int mes) {
        return objBoticas.Generera_Surf01(idbotica, mes);
    }

    public boolean GuardaBitacora(String idbotica, String fecha, String opcion) {
        return objBoticas.guardarbitacora(idbotica,fecha,opcion);
    }
    
    public List<Surf02> Generera_Surf02(String idbotica, int mes) {
        return objBoticas.Generera_Surf02(idbotica, mes);
    }

    public List<Surf31> Generera_Surf31(String idbotica, int mes) {
        return objBoticas.Generera_Surf31(idbotica, mes);
    }

    public List<Surf32> Generera_Surf32(String idbotica, int mes) {
        return objBoticas.Generera_Surf32(idbotica, mes);
    }

    public List<Surf10> Generera_Surf10(String idbotica) {
        return objBoticas.Generera_Surf10(idbotica);
    }

    public boolean VerificaBoticaCerrada() {
        return objBoticas.VerificaBoticaCerrada();
    }
}
