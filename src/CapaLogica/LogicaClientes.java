package CapaLogica;

import CapaDatos.ClientesData;
import entidad.Clientes;
import entidad.Venta;
import java.sql.SQLException;
import java.util.List;

public class LogicaClientes {

    ClientesData objcliente = new ClientesData();

    public LogicaClientes() {
    }
    
    public List<Clientes> ListaVerClientes (String nombre){
        return objcliente.ListaVerClientes(nombre);
    }
    
    public List<Clientes> ListaClientes1(String nomcliente, String idboti) {
        return objcliente.listaClientes1(nomcliente, idboti);
    }
    public List<Clientes> listaTransportista(String nomcliente, String idboti,int OPC) {
        return objcliente.listaTransportista(nomcliente, idboti, OPC);
    }
    public List<Clientes> ListaClientes3(String nomcliente, String idboti) {
        return objcliente.listaClientes3(nomcliente, idboti);
    }
    public List<Clientes> ListaClientesGastos(String nomcliente, String idboti) {
        return objcliente.ListaClientesGastos(nomcliente, idboti);
    }

    public String RetornaRUC(String Descripcion) {

        return objcliente.RetornaRUC(Descripcion);
    }

    public String BUSCAREXISTERUC(String RUC,String Razonsocial,String direccion,int dfiscal, String opcion, int variasdire) {
        return objcliente.BUSCAREXISTERUC(RUC, Razonsocial, direccion,dfiscal,opcion,variasdire);
    }

    public int GuardarCliente(Clientes objclien, String idbotica) {
        return objcliente.GuardarCliente(objclien, idbotica);
    }
    public int GuardaMedico(Clientes objclien, String idbotica,int opc) {
        return objcliente.GuardaMedico(objclien, idbotica,opc);
    }
    public int GuardarTransportista(Clientes objclien, String idbotica) {
        return objcliente.GuardarTransportista(objclien, idbotica);
    }

    public int GuardarClienteGastos(Clientes objclien, String idbotica) {
        return objcliente.GuardarClienteGastos(objclien, idbotica);
    }

    public List<Clientes> BuscarCliente(String telefono, int ind) {
        return objcliente.BuscarCliente(telefono, ind);
    }

    public boolean ModificaCliente(Clientes obj) {
        return objcliente.ModificaCliente(obj);
    }

    public boolean GuardarCliente2(Clientes objClientes) {
        return objcliente.GuardarCliente2(objClientes);
    }

    public List<Clientes> ListaClientes(String Empresa, String Filtro, String idboti) {
        return objcliente.listaClientes(Empresa, Filtro, idboti);
    }

    public List<Clientes> ListaClientes2(String Empresa, String Filtro) {
        return objcliente.listaClientes2(Empresa, Filtro);
    }

    public List<Clientes> MiListaClientes(String Empresa, String Filtro) {
        return objcliente.MiListaClientes(Empresa, Filtro);
    }

    public Integer CrearCredito(Clientes entidadClietnes) {
        return objcliente.CrearCredito(entidadClietnes);
    }

    public boolean CrearCredito(List<Clientes> lista) {
        return objcliente.CrearCredito(lista);
    }

    public Double RecuperaSaldo(int IdCliente, String idbotica) {
        return objcliente.RecuperaSaldo(IdCliente, idbotica);
    }

    public void DescuentaSaldo(String Cliente, Double valor) {
        objcliente.DescuentaSaldo(Cliente, valor);
    }

    public Boolean AumentarLinea(Clientes entidadClietnes, String idbotica) {
        return objcliente.AumentarLinea(entidadClietnes, idbotica);
    }

    public List<Clientes> Lista_Movimientos_Clientes(Clientes obj, String fecha1, String fecha2) {
        return objcliente.Lista_Movimientos_Clientes(obj, fecha1, fecha2);
    }

    public List<Clientes> Verifica_Descuento_Cliente(int idcliente) {
        return objcliente.Verifica_Descuento_Cliente(idcliente);
    }

    public List<Clientes> ListaClientes_PORDNI(String DNI) {
        return objcliente.listaClientes_PORDNI(DNI);
    }

    public List<Clientes> ListaClientes_PORRUC(String RUC) {
        return objcliente.listaClientes_PORUC(RUC);
    }

    public List<Clientes> BK_Listar_Clientes() throws SQLException {
        return objcliente.BK_Listar_Clientes();
    }

    public List<Clientes> listaClientes_Detallado(String idbotica, String Filtro) {
        return objcliente.listaClientes_Detallado(idbotica, Filtro);
    }

    public List<Venta> Lista_Deudas_Clientes(String idbotica, int idcliente) {
        return objcliente.Lista_Deudas_Clientes(idbotica, idcliente);
    }

    public boolean Guarda_Pagos_Credito(List<Venta> lista, List<Venta> detalle, String idbotica, int idpersonal, int idcliente, int idcaja) {
        return objcliente.Guarda_Pagos_Credito(lista, detalle, idbotica, idpersonal, idcliente, idcaja);
    }

    public Clientes Lista_Deuda(String idbotica, int idcliente) {
        return objcliente.Lista_Deuda(idbotica, idcliente);
    }

    public List<Venta> Lista_Ventas_Clientes(String idbotica, int idcliente, String fecha1, String fecha2) {
        return objcliente.Lista_Ventas_Clientes(idbotica, idcliente, fecha1, fecha2);
    }

    public List<Venta> Lista_Notas_Credito(String idbotica, int idcliente) {
        return objcliente.Lista_Notas_Credito(idbotica, idcliente);
    }

    public int EsDescuento(int descrppago) {
        return objcliente.EsDescuento(descrppago);
    }

    public int Recupera_IdCliente_Descuento(String idbotica, int codigo) {
        return objcliente.Recupera_IdCliente_Descuento(idbotica,codigo);
    }

    public boolean Cierre_Credito(List<Clientes> lista) {
        return objcliente.Cierre_Credito(lista);
    }

    public boolean Verifica_Existe_Ruc(String ruc, String idbotica,int opc) {
        return objcliente.Verifica_Existe_Ruc(ruc, idbotica,opc);
    }
    
    public boolean Verifica_Existe_Medico(String codmedico, String nommedico,String cole,int opc) {
        return objcliente.Verifica_Existe_Medico(codmedico, nommedico,cole,opc);
    }
    
    public boolean Verifica_Existe_Ruc_Transportista(String ruc, String idbotica,int opc) {
        return objcliente.Verifica_Existe_Ruc_Transportista(ruc, idbotica,opc);
    }
    public boolean Verifica_Existe_Ruc_Gastos(String ruc, String idbotica) {
        return objcliente.Verifica_Existe_Ruc_Gastos(ruc, idbotica);
    }

    public Clientes listaClientes_Detallado(String idbotica, int idcliente, String empresa) {
        return objcliente.listaClientes_Detallado(idbotica, idcliente, empresa);
    }

    public List<Clientes> Lista_Direcciones(String idbotica, int idcliente, String empresa, String direccion) {
        return objcliente.Lista_Direcciones(idbotica, idcliente, empresa, direccion);
    }
    
    public List<Clientes> Lista_Direcciones_Nuevo(String idbotica, int idcliente, String empresa, String direccion) {
        return objcliente.Lista_Direcciones_Nuevo(idbotica, idcliente, empresa, direccion);
    }

    public List<Clientes> Lista_Direcciones(String idbotica, int idcliente, int empresa,String proforma) {
        return objcliente.Lista_Direcciones(idbotica, idcliente, empresa,proforma);
    }

    public boolean Modifica_Direccion_Cliente(Clientes obj) {
        return objcliente.Modifica_Direccion_Cliente(obj);
    }

    public boolean ModificaDatosCliente(Clientes obj, List<String> listaDirecciones) {
        return objcliente.ModificaDatosCliente(obj, listaDirecciones);
    }
    public boolean ModificaDatosTransp(Clientes obj) {
        return objcliente.ModificaDatosTransportista(obj);
    }

    public Clientes Cliente_Defecto() {
        return objcliente.Cliente_Defecto();
    }

    public List<Clientes> ListaClientes_Telefono(String telefono) {
        return objcliente.listaClientes_Telefono(telefono);
    }
}
