/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

public class GuiasRemision {

    String Serie;
    String Numeracion;
    String fecha;
    String Direccion;
    String DireccionLLegada;
    String Destinatario;
    String RUC_Destinatario;
    String Marca_Placa;
    String Constancia_Inscripcion;
    String Licencia_Conducir;
    String Empresa_Transporte;
    String RUC_Empresa_Transporte;
    String Tipo_Comprobante_Pago;
    String Cantidad;
    String Unidad;
    String DescripcionDetalleGuia;
    String Observaciones;
    String Responsable;
    Integer Id_Responsable;
    Integer Total;
    Integer Estado;
    String Origen;
    String Destino;

    public GuiasRemision(String Origen, String Destino) {
        this.Origen = Origen;
        this.Destino = Destino;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer Total) {
        this.Total = Total;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getConstancia_Inscripcion() {
        return Constancia_Inscripcion;
    }

    public void setConstancia_Inscripcion(String Constancia_Inscripcion) {
        this.Constancia_Inscripcion = Constancia_Inscripcion;
    }

    public String getDescripcionDetalleGuia() {
        return DescripcionDetalleGuia;
    }

    public void setDescripcionDetalleGuia(String DescripcionDetalleGuia) {
        this.DescripcionDetalleGuia = DescripcionDetalleGuia;
    }

    public String getDestinatario() {
        return Destinatario;
    }

    public void setDestinatario(String Destinatario) {
        this.Destinatario = Destinatario;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDireccionLLegada() {
        return DireccionLLegada;
    }

    public void setDireccionLLegada(String DireccionLLegada) {
        this.DireccionLLegada = DireccionLLegada;
    }

    public String getEmpresa_Transporte() {
        return Empresa_Transporte;
    }

    public void setEmpresa_Transporte(String Empresa_Transporte) {
        this.Empresa_Transporte = Empresa_Transporte;
    }

    public Integer getId_Responsable() {
        return Id_Responsable;
    }

    public void setId_Responsable(Integer Id_Responsable) {
        this.Id_Responsable = Id_Responsable;
    }

    public String getLicencia_Conducir() {
        return Licencia_Conducir;
    }

    public void setLicencia_Conducir(String Licencia_Conducir) {
        this.Licencia_Conducir = Licencia_Conducir;
    }

    public String getMarca_Placa() {
        return Marca_Placa;
    }

    public void setMarca_Placa(String Marca_Placa) {
        this.Marca_Placa = Marca_Placa;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getRUC_Destinatario() {
        return RUC_Destinatario;
    }

    public void setRUC_Destinatario(String RUC_Destinatario) {
        this.RUC_Destinatario = RUC_Destinatario;
    }

    public String getRUC_Empresa_Transporte() {
        return RUC_Empresa_Transporte;
    }

    public void setRUC_Empresa_Transporte(String RUC_Empresa_Transporte) {
        this.RUC_Empresa_Transporte = RUC_Empresa_Transporte;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public String getTipo_Comprobante_Pago() {
        return Tipo_Comprobante_Pago;
    }

    public void setTipo_Comprobante_Pago(String Tipo_Comprobante_Pago) {
        this.Tipo_Comprobante_Pago = Tipo_Comprobante_Pago;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeracion() {
        return Numeracion;
    }

    public void setNumeracion(String Numeracion) {
        this.Numeracion = Numeracion;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public GuiasRemision(String Direccion, String DireccionLLegada, Integer Id_Responsable) {
        this.Direccion = Direccion;
        this.DireccionLLegada = DireccionLLegada;
        this.Id_Responsable = Id_Responsable;
    }

    public GuiasRemision(String Serie, String Numeracion, String fecha, String DireccionLLegada) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.fecha = fecha;
        this.DireccionLLegada = DireccionLLegada;
    }

    public GuiasRemision(String Serie, String Numeracion, String DescripcionDetalleGuia) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.DescripcionDetalleGuia = DescripcionDetalleGuia;
    }

    public GuiasRemision(String Serie, String Numeracion, String fecha, String Direccion, String DireccionLLegada, String RUC_Destinatario, String RUC_Empresa_Transporte, Integer Total, Integer Estado) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.fecha = fecha;
        this.Direccion = Direccion;
        this.DireccionLLegada = DireccionLLegada;
        this.RUC_Destinatario = RUC_Destinatario;
        this.RUC_Empresa_Transporte = RUC_Empresa_Transporte;
        this.Total = Total;
        this.Estado = Estado;
    }

    public GuiasRemision(String Serie, String Numeracion, String RUC_Destinatario, String Marca_Placa, String Constancia_Inscripcion, String Licencia_Conducir, String Empresa_Transporte, String RUC_Empresa_Transporte, String Direccion, Integer Id_Responsable) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.RUC_Destinatario = RUC_Destinatario;
        this.Marca_Placa = Marca_Placa;
        this.Constancia_Inscripcion = Constancia_Inscripcion;
        this.Licencia_Conducir = Licencia_Conducir;
        this.Empresa_Transporte = Empresa_Transporte;
        this.RUC_Empresa_Transporte = RUC_Empresa_Transporte;
        this.Direccion = Direccion;
        this.Id_Responsable = Id_Responsable;
    }

    public GuiasRemision(String Serie, String Numeracion, String fecha, String Direccion, String DireccionLLegada) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.fecha = fecha;
        this.Direccion = Direccion;
        this.DireccionLLegada = DireccionLLegada;
    }

    public GuiasRemision(String Serie, String Numeracion, String fecha, String Direccion, String DireccionLLegada, Integer Estado) {
        this.Serie = Serie;
        this.Numeracion = Numeracion;
        this.fecha = fecha;
        this.Direccion = Direccion;
        this.DireccionLLegada = DireccionLLegada;
        this.Estado = Estado;
    }

    public GuiasRemision(String Origen, String Destino, String Destinatario, String RUC_Destinatario, Integer Id_Responsable, String fecha, String Marca_Placa, String Constancia_Inscripcion, String Licencia_Conducir, String Empresa_Transporte, String RUC_Empresa_Transporte) {
        this.Origen = Origen;
        this.Destino = Destino;
        this.Destinatario = Destinatario;
        this.RUC_Destinatario = RUC_Destinatario;
        this.Id_Responsable = Id_Responsable;
        this.fecha = fecha;
        this.Marca_Placa = Marca_Placa;
        this.Constancia_Inscripcion = Constancia_Inscripcion;
        this.Licencia_Conducir = Licencia_Conducir;
        this.Empresa_Transporte = Empresa_Transporte;
        this.RUC_Empresa_Transporte = RUC_Empresa_Transporte;
    }
}
