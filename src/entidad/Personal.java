package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Personal {

    private int Id_Rol;
    private int Id_Pagina;
    private String DescripcionPagina;
    private String DescripcionRoles;
    private String Nombre;
    private String Apellido;
    private String Contrasena;
    private String DNI;
    private String Direccion;
    private int Personal_Cesado;
    private int Id_Personal;
    private int Id_PersonalSuper;
    private String ApellidoSuper;
    private int Id_PersonalGerencia;
    private String ApellidoGerencia;
    private String vId_Modelo_Contrato;
    private Date vFecha_Inicio;
    private Date vFecha_Fin;
    private String vCodigo_Administrativo;
    private String id_botica;
    private String DescpCargo;
    private int Id_personal_Caja;
    private double saldo;
    private int idempresa;
    private int idtemperatura;
    private String DescripcionTemperatura;
    private int id_productorecarga;
    private String Descpoperadorrec;

    private String SRVC_RUC;
    private String SRVC_RAZONSOCIAL;
    private String SRVC_DIRECCION;
    private String SRVC_ESTADO;
    private int SRVC_DIRECCIONFISCAL;

    public Personal() {
    }

    
    public String getSRVC_RUC() {
        return SRVC_RUC;
    }
    public void setSRVC_RUC(String SRVC_RUC) {
        this.SRVC_RUC = SRVC_RUC;
    }

    public String getSRVC_RAZONSOCIAL() {
        return SRVC_RAZONSOCIAL;
    }
    public void setSRVC_RAZONSOCIAL(String SRVC_RAZONSOCIAL) {
        this.SRVC_RAZONSOCIAL = SRVC_RAZONSOCIAL;
    }

    public String getSRVC_DIRECCION() {
        return SRVC_DIRECCION;
    }
    public void setSRVC_DIRECCION(String SRVC_DIRECCION) {
        this.SRVC_DIRECCION = SRVC_DIRECCION;
    }

    public String getSRVC_ESTADO() {
        return SRVC_ESTADO;
    }
    public void setSRVC_ESTADO(String SRVC_ESTADO) {
        this.SRVC_ESTADO = SRVC_ESTADO;
    }

    public int getSRVC_DIRECCIONFISCAL() {
        return SRVC_DIRECCIONFISCAL;
    }
    public void setSRVC_DIRECCIONFISCAL(int SRVC_DIRECCIONFISCAL) {
        this.SRVC_DIRECCIONFISCAL = SRVC_DIRECCIONFISCAL;
    }




    public int getid_productorecarga() {
        return id_productorecarga;
    }
    public void setid_productorecargao(int id_productorecarga) {
        this.id_productorecarga = id_productorecarga;
    }

    public int getidtemperatura() {
        return idtemperatura;
    }
    public void setidtemperatura(int idtemperatura) {
        this.idtemperatura = idtemperatura;
    }

    public String getDescripcionTemperatura() {
        return DescripcionTemperatura;
    }
    public void setDescripcionTemperatura(String DescripcionTemperatura) {
        this.DescripcionTemperatura = DescripcionTemperatura;
    }
    public String getDescpoperadorrec() {
        return Descpoperadorrec;
    }
    public void setDescpoperadorrec(String Descpoperadorrec) {
        this.Descpoperadorrec = Descpoperadorrec;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getApellidoSuper() {
        return ApellidoSuper;
    }

    public void setApellidoSuper(String ApellidoSuper) {
        this.ApellidoSuper = ApellidoSuper;
    }

    public String getApellidoGerencia() {
        return ApellidoGerencia;
    }

    public void setApellidoGerencia(String ApellidoGerencia) {
        this.ApellidoGerencia = ApellidoGerencia;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDescpCargo() {
        return DescpCargo;
    }

    public void setDescpCargo(String DescpCargo) {
        this.DescpCargo = DescpCargo;
    }

    public String getDescripcionPagina() {
        return DescripcionPagina;
    }

    public void setDescripcionPagina(String DescripcionPagina) {
        this.DescripcionPagina = DescripcionPagina;
    }

    public String getDescripcionRoles() {
        return DescripcionRoles;
    }

    public void setDescripcionRoles(String DescripcionRoles) {
        this.DescripcionRoles = DescripcionRoles;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getId_Pagina() {
        return Id_Pagina;
    }

    public void setId_Pagina(int Id_Pagina) {
        this.Id_Pagina = Id_Pagina;
    }

    public int getId_Personal() {
        return Id_Personal;
    }

    public void setId_Personal(int Id_Personal) {
        this.Id_Personal = Id_Personal;
    }

    public int getId_PersonalSuper() {
        return Id_PersonalSuper;
    }

    public void setId_PersonalSuper(int Id_PersonalSuper) {
        this.Id_PersonalSuper = Id_PersonalSuper;
    }

    public int getId_PersonalGerencia() {
        return Id_PersonalGerencia;
    }

    public void setId_PersonalGerencia(int Id_PersonalGerencia) {
        this.Id_PersonalGerencia = Id_PersonalGerencia;
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(int Id_Rol) {
        this.Id_Rol = Id_Rol;
    }

    public int getId_personal_Caja() {
        return Id_personal_Caja;
    }

    public void setId_personal_Caja(int Id_personal_Caja) {
        this.Id_personal_Caja = Id_personal_Caja;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPersonal_Cesado() {
        return Personal_Cesado;
    }

    public void setPersonal_Cesado(int Personal_Cesado) {
        this.Personal_Cesado = Personal_Cesado;
    }

    public String getId_botica() {
        return id_botica;
    }

    public void setId_botica(String id_botica) {
        this.id_botica = id_botica;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getvCodigo_Administrativo() {
        return vCodigo_Administrativo;
    }

    public void setvCodigo_Administrativo(String vCodigo_Administrativo) {
        this.vCodigo_Administrativo = vCodigo_Administrativo;
    }

    public Date getvFecha_Fin() {
        return vFecha_Fin;
    }

    public void setvFecha_Fin(Date vFecha_Fin) {
        this.vFecha_Fin = vFecha_Fin;
    }

    public Date getvFecha_Inicio() {
        return vFecha_Inicio;
    }

    public void setvFecha_Inicio(Date vFecha_Inicio) {
        this.vFecha_Inicio = vFecha_Inicio;
    }

    public String getvId_Modelo_Contrato() {
        return vId_Modelo_Contrato;
    }

    public void setvId_Modelo_Contrato(String vId_Modelo_Contrato) {
        this.vId_Modelo_Contrato = vId_Modelo_Contrato;
    }
}
