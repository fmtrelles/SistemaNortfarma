/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class Producto {

    private String idProducto;
    private Laboratorios laboratorio;
    private Familias idFamilia;
    private Genericos idGenerico;
    private String descripcion;
    private Date Fecha_Vencimiento;
    private int Empaque;
    private int Blister;
    private Double IGV_Exonerado;
    private int Id_CONCURSO;
    private String Codigo_Barras;
    private int Id_Tipo_Correlativo;
    private int Id_Clase;
    private int Activo;
    private Tipo_Producto tipoProducto;
    private String miEmpaque;
    private String validatg;


    public Producto() {
    }



    public String getvalidatg() {
        return validatg;
    }

    public void setvalidatg(String validatg) {
        this.validatg = validatg;
    }

    public String getMiEmpaque() {
        return miEmpaque;
    }

    public void setMiEmpaque(String miEmpaque) {
        this.miEmpaque = miEmpaque;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public int getBlister() {
        return Blister;
    }

    public void setBlister(int Blister) {
        this.Blister = Blister;
    }

    public String getCodigo_Barras() {
        return Codigo_Barras;
    }

    public void setCodigo_Barras(String Codigo_Barras) {
        this.Codigo_Barras = Codigo_Barras;
    }

    public int getEmpaque() {
        return Empaque;
    }

    public void setEmpaque(int Empaque) {
        this.Empaque = Empaque;
    }

    public Date getFecha_Vencimiento() {
        return Fecha_Vencimiento;
    }

    public void setFecha_Vencimiento(Date Fecha_Vencimiento) {
        this.Fecha_Vencimiento = Fecha_Vencimiento;
    }

    public Double getIGV_Exonerado() {
        return IGV_Exonerado;
    }

    public void setIGV_Exonerado(Double IGV_Exonerado) {
        this.IGV_Exonerado = IGV_Exonerado;
    }

    public int getId_CONCURSO() {
        return Id_CONCURSO;
    }

    public void setId_CONCURSO(int Id_CONCURSO) {
        this.Id_CONCURSO = Id_CONCURSO;
    }

    public int getId_Clase() {
        return Id_Clase;
    }

    public void setId_Clase(int Id_Clase) {
        this.Id_Clase = Id_Clase;
    }

    public int getId_Tipo_Correlativo() {
        return Id_Tipo_Correlativo;
    }

    public void setId_Tipo_Correlativo(int Id_Tipo_Correlativo) {
        this.Id_Tipo_Correlativo = Id_Tipo_Correlativo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Familias getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Familias idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Genericos getIdGenerico() {
        return idGenerico;
    }

    public void setIdGenerico(Genericos idGenerico) {
        this.idGenerico = idGenerico;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Laboratorios getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorios laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Tipo_Producto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Tipo_Producto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}