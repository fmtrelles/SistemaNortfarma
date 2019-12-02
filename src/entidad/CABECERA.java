package entidad;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class CABECERA implements Comparator<CABECERA>, Comparable<CABECERA> {

    String EMPRESA;
    String RUC;
    String DIRECCION;
    String UBIGEO;
    String DEPARTAMENTO;
    String PROVINCIA;
    String DISTRITO;
    String PAIS;
    String ID_BOTICA;
    String ID_VENTA;
    String MONEDA_SIGLAS;
    String MONEDA_DESCRIPCION;
    String FECHA_VENTA;
    String FECHA_GENERACION;
    double TOTAL;
    String IGV;
    String SUBTOTAL;
    String C1001;
    String VER_C1001;
    String C1002;
    String VER_C1002;
    String C1003;
    String VER_C1003;
    String C1004;
    String VER_C1004;
    String C2004;
    String VER_C2004;
    String C2005;
    String VER_C2005;
    String C20004;
    String VER_C20004;
    String ID_TIPO_DOCUMENTO_SUNAT;
    String SER_NUM;
    String NUM_DOC;
    String TIPO_DOC_IDENTIDAD;
    String NOMCLIENTE;
    String TOTAL_TEXTO;
    String Responsecode;
    String OBSERVACIONES;

    public CABECERA() {
    }

    public String getEMPRESA() {
        return this.EMPRESA;
    }

    public void setEMPRESA(String value) {
        this.EMPRESA = value;
    }

    public String getRUC() {
        return this.RUC;
    }

    public void setRUC(String value) {
        this.RUC = value;
    }
    
    public String getResponsecode() {
        return this.Responsecode;
    }

    public void setResponsecode(String value) {
        this.Responsecode = value;
    }
    
    public String getOBSERVACIONES() {
        return this.OBSERVACIONES;
    }

    public void setOBSERVACIONES(String value) {
        this.OBSERVACIONES = value;
    }



    public String getDIRECCION() {
        return this.DIRECCION;
    }

    public void setDIRECCION(String value) {
        this.DIRECCION = value;
    }

    public String getUBIGEO() {
        return this.UBIGEO;
    }

    public void setUBIGEO(String value) {
        this.UBIGEO = value;
    }

    public String getDEPARTAMENTO() {
        return this.DEPARTAMENTO;
    }

    public void setDEPARTAMENTO(String value) {
        this.DEPARTAMENTO = value;
    }

    public String getPROVINCIA() {
        return this.PROVINCIA;
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA = value;
    }

    public String getDISTRITO() {
        return this.DISTRITO;
    }

    public void setDISTRITO(String value) {
        this.DISTRITO = value;
    }

    public String getPAIS() {
        return this.PAIS;
    }

    public void setPAIS(String value) {
        this.PAIS = value;
    }

    public String getID_BOTICA() {
        return this.ID_BOTICA;
    }

    public void setID_BOTICA(String value) {
        this.ID_BOTICA = value;
    }

    public String getID_VENTA() {
        return this.ID_VENTA;
    }

    public void setID_VENTA(String value) {
        this.ID_VENTA = value;
    }

    public String getMONEDA_SIGLAS() {
        return this.MONEDA_SIGLAS;
    }

    public void setMONEDA_SIGLAS(String value) {
        this.MONEDA_SIGLAS = value;
    }

    public String getMONEDA_DESCRIPCION() {
        return this.MONEDA_DESCRIPCION;
    }

    public void setMONEDA_DESCRIPCION(String value) {
        this.MONEDA_DESCRIPCION = value;
    }

    public String getFECHA_VENTA() {
        return this.FECHA_VENTA;
    }

    public void setFECHA_VENTA(String value) {
        this.FECHA_VENTA = value;
    }

    public String getFECHA_GENERACION() {
        return this.FECHA_GENERACION;
    }

    public void setFECHA_GENERACION(String value) {
        this.FECHA_GENERACION = value;
    }

    public double getTOTAL() {
        return this.TOTAL;
    }

    public void setTOTAL(double value) {
        this.TOTAL = value;
    }

    public String getIGV() {
        return this.IGV;
    }

    public void setIGV(String value) {
        this.IGV = value;
    }

    public String getSUBTOTAL() {
        return this.SUBTOTAL;
    }

    public void setSUBTOTAL(String value) {
        this.SUBTOTAL = value;
    }

    public String getC1001() {
        return this.C1001;
    }

    public void setC1001(String value) {
        this.C1001 = value;
    }

    public String getVER_C1001() {
        return this.VER_C1001;
    }

    public void setVER_C1001(String value) {
        this.VER_C1001 = value;
    }

    public String getC1002() {
        return this.C1002;
    }

    public void setC1002(String value) {
        this.C1002 = value;
    }

    public String getVER_C1002() {
        return this.VER_C1002;
    }

    public void setVER_C1002(String value) {
        this.VER_C1002 = value;
    }

    public String getC1003() {
        return this.C1003;
    }

    public void setC1003(String value) {
        this.C1003 = value;
    }

    public String getVER_C1003() {
        return this.VER_C1003;
    }

    public void setVER_C1003(String value) {
        this.VER_C1003 = value;
    }

    public String getC1004() {
        return this.C1004;
    }

    public void setC1004(String value) {
        this.C1004 = value;
    }

    public String getVER_C1004() {
        return this.VER_C1004;
    }

    public void setVER_C1004(String value) {
        this.VER_C1004 = value;
    }

    public String getC2004() {
        return this.C2004;
    }

    public void setC2004(String value) {
        this.C2004 = value;
    }

    public String getVER_C2004() {
        return this.VER_C2004;
    }

    public void setVER_C2004(String value) {
        this.VER_C2004 = value;
    }

    public String getC2005() {
        return this.C2005;
    }

    public void setC2005(String value) {
        this.C2005 = value;
    }

    public String getVER_C2005() {
        return this.VER_C2005;
    }

    public void setVER_C2005(String value) {
        this.VER_C2005 = value;
    }

    public String getC20004() {
        return this.C20004;
    }

    public void setC20004(String value) {
        this.C20004 = value;
    }

    public String getVER_C20004() {
        return this.VER_C20004;
    }

    public void setVER_C20004(String value) {
        this.VER_C20004 = value;
    }

    public String getID_TIPO_DOCUMENTO_SUNAT() {
        return this.ID_TIPO_DOCUMENTO_SUNAT;
    }

    public void setID_TIPO_DOCUMENTO_SUNAT(String value) {
        this.ID_TIPO_DOCUMENTO_SUNAT = value;
    }

    public String getSER_NUM() {
        return this.SER_NUM;
    }

    public void setSER_NUM(String value) {
        this.SER_NUM = value;
    }

    public String getNUM_DOC() {
        return this.NUM_DOC;
    }

    public void setNUM_DOC(String value) {
        this.NUM_DOC = value;
    }

    public String getTIPO_DOC_IDENTIDAD() {
        return this.TIPO_DOC_IDENTIDAD;
    }

    public void setTIPO_DOC_IDENTIDAD(String value) {
        this.TIPO_DOC_IDENTIDAD = value;
    }

    public String getNOMCLIENTE() {
        return this.NOMCLIENTE;
    }

    public void setNOMCLIENTE(String value) {
        this.NOMCLIENTE = value;
    }

    public String getTOTAL_TEXTO() {
        return this.TOTAL_TEXTO;
    }

    public void setTOTAL_TEXTO(String value) {
        this.TOTAL_TEXTO = value;
    }

    public String RETORNA(String CAMPO) {
        if (CAMPO.equals("EMPRESA")) {
            return this.EMPRESA;
        } else if (CAMPO.equals("RUC")) {
            return this.RUC;
        } else if (CAMPO.equals("DIRECCION")) {
            return this.DIRECCION;
        } else if (CAMPO.equals("UBIGEO")) {
            return this.UBIGEO;
        } else if (CAMPO.equals("DEPARTAMENTO")) {
            return this.DEPARTAMENTO;
        } else if (CAMPO.equals("PROVINCIA")) {
            return this.PROVINCIA;
        } else if (CAMPO.equals("DISTRITO")) {
            return this.DISTRITO;
        } else if (CAMPO.equals("PAIS")) {
            return this.PAIS;
        } else if (CAMPO.equals("ID_BOTICA")) {
            return this.ID_BOTICA;
        } else if (CAMPO.equals("ID_VENTA")) {
            return this.ID_VENTA;
        } else if (CAMPO.equals("MONEDA_SIGLAS")) {
            return this.MONEDA_SIGLAS;
        } else if (CAMPO.equals("MONEDA_DESCRIPCION")) {
            return this.MONEDA_DESCRIPCION;
        } else if (CAMPO.equals("FECHA_VENTA")) {
            return this.FECHA_VENTA;
        } else if (CAMPO.equals("FECHA_GENERACION")) {
            return this.FECHA_GENERACION;
        } else if (CAMPO.equals("TOTAL")) {
            return Double.toString(this.TOTAL);
        } else if (CAMPO.equals("IGV")) {
            return this.IGV;
        } else if (CAMPO.equals("SUBTOTAL")) {
            return this.SUBTOTAL;
        } else if (CAMPO.equals("C1001")) {
            return this.C1001;
        } else if (CAMPO.equals("VER_C1001")) {
            return this.VER_C1001;
        } else if (CAMPO.equals("C1002")) {
            return this.C1002;
        } else if (CAMPO.equals("VER_C1002")) {
            return this.VER_C1002;
        } else if (CAMPO.equals("C1003")) {
            return this.C1003;
        } else if (CAMPO.equals("VER_C1003")) {
            return this.VER_C1003;
        } else if (CAMPO.equals("C1004")) {
            return this.C1004;
        } else if (CAMPO.equals("VER_C1004")) {
            return this.VER_C1004;
        } else if (CAMPO.equals("C2004")) {
            return this.C2004;
        } else if (CAMPO.equals("VER_C2004")) {
            return this.VER_C2004;
        } else if (CAMPO.equals("C2005")) {
            if (this.C2005 ==null){
                return "";
            }else{
                return this.C2005;
            }
        } else if (CAMPO.equals("VER_C2005")) {
            return this.VER_C2005;
        } else if (CAMPO.equals("C20004")) {
            return this.C20004;
        } else if (CAMPO.equals("VER_C20004")) {
            return this.VER_C20004;
        } else if (CAMPO.equals("ID_TIPO_DOCUMENTO_SUNAT")) {
            return this.ID_TIPO_DOCUMENTO_SUNAT;
        } else if (CAMPO.equals("SER_NUM")) {
            return this.SER_NUM;
        } else if (CAMPO.equals("NUM_DOC")) {
            return this.NUM_DOC;
        } else if (CAMPO.equals("TIPO_DOC_IDENTIDAD")) {
            return this.TIPO_DOC_IDENTIDAD;
        } else if (CAMPO.equals("NOMCLIENTE")) {
            return this.NOMCLIENTE;
        } else if (CAMPO.equals("TOTAL_TEXTO")) {
            return this.TOTAL_TEXTO;
        } else if (CAMPO.equals("ResponseCode")) {
            return this.Responsecode;
        } else if (CAMPO.equals("OBSERVACIONES")) {
            return this.OBSERVACIONES;
        }
        return "";
    }

    @Override
    public int compare(CABECERA o1, CABECERA o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(CABECERA o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


    /*private String Id_Botica;
    private Date Fecha_Venta;
    private double SubTotal;    
    //private double IGV;
    //private String ID_TIPO_DOCUMENTO_SUNAT;
    private String Serie;
    private String Numero;
    private String id_Venta;
    private String ID_PRODUCTO;
    private String NomCliente;
    private double Total;
    //private String TIPO_DOC_IDENTIDAD;
    //private String TOTAL_TEXTO;
   // private double C1001;
    //private double C1002;
    //private double C1003;
    //private double C1004;
    //private double C2004;
    //private double C2005;
    //private double C20004;

   private double VERC1001;
   private double VERC1002;
   private double VERC1003;
   private double VERC1004;
   private double VERC2004;
   private double VERC2005;
   private double VERC20004;

    private String Id_Venta_REF;
    private Date Fecha_Venta_REF;
    private String ID_TIPO_DOCUMENTO_SUNAT_REF;
    private String Serie_ref;
    private String Id_Cliente_ref;

    private String GRUPO;
    private String POSICION;
    private String TABOPEN;
    private String CAMPO;
    private String DEFECTO;
    private String DATO;
    private String CIERRE;
    private String TABCLOSE;
    private String VISUALIZA;

    private String FRAC_NUM;
    //private String VER_C1004;
    private String VER_C6000;
    private String PRODUCTO;
    private String ESGRATUITA;
    private double OP_GRAVADA;
    private double OP_INAFECTA;
    private double OP_EXONERADA;
    private double OP_GRATUITA;
    private double OP_DESCUENTO;    
    private double LineExtionsionAmount;
    private double PRECIO_UNITARIO;
    private double PORCENTAJE_IGV;    
    private double TOTAL_UNITARIO_SINIGV;
    private double TaxExemptionReasonCode;
    private String MonedaSiglas;
    private String MonedaDescripcion;

    private String Empresa;
    private String RUC;
    private String Direccion;
    private String Ubigeo;
    private String Departamento;
    private String Provincia;
    private String Distrito;
    private String Pais;
    private Date FechaGeneracion;
    private String Observaciones;


    String EMPRESA;
    //String RUC;
    String DIRECCION;
    String UBIGEO;
    String DEPARTAMENTO;
    String PROVINCIA;
    String DISTRITO;
    String PAIS;
    String ID_BOTICA;
    String ID_VENTA;
    String MONEDA_SIGLAS;
    String MONEDA_DESCRIPCION;
    String FECHA_VENTA;
    String FECHA_GENERACION;
    double TOTAL;
    String IGV;
    String SUBTOTAL;
    String C1001;
    String VER_C1001;
    String C1002;
    String VER_C1002;
    String C1003;
    String VER_C1003;
    String C1004;
    String VER_C1004;
    String C2004;
    String VER_C2004;
    String C2005;
    String VER_C2005;
    String C20004;
    String VER_C20004;
    String ID_TIPO_DOCUMENTO_SUNAT;
    String SER_NUM;
    String NUM_DOC;
    String TIPO_DOC_IDENTIDAD;
    String NOMCLIENTE;
    String TOTAL_TEXTO;


    public String getEMPRESA() {
        return this.EMPRESA;
    }

    public void setEMPRESA(String value) {
        this.EMPRESA = value;
    }

    public String getRUC() {
        return this.RUC;
    }

    public void setRUC(String value) {
        this.RUC = value;
    }

    public String getDIRECCION() {
        return this.DIRECCION;
    }

    public void setDIRECCION(String value) {
        this.DIRECCION = value;
    }

    public String getUBIGEO() {
        return this.UBIGEO;
    }

    public void setUBIGEO(String value) {
        this.UBIGEO = value;
    }

    public String getDEPARTAMENTO() {
        return this.DEPARTAMENTO;
    }

    public void setDEPARTAMENTO(String value) {
        this.DEPARTAMENTO = value;
    }

    public String getPROVINCIA() {
        return this.PROVINCIA;
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA = value;
    }

    public String getDISTRITO() {
        return this.DISTRITO;
    }

    public void setDISTRITO(String value) {
        this.DISTRITO = value;
    }

    public String getPAIS() {
        return this.PAIS;
    }

    public void setPAIS(String value) {
        this.PAIS = value;
    }

    public String getID_BOTICA() {
        return this.ID_BOTICA;
    }

    public void setID_BOTICA(String value) {
        this.ID_BOTICA = value;
    }

    public String getID_VENTA() {
        return this.ID_VENTA;
    }

    public void setID_VENTA(String value) {
        this.ID_VENTA = value;
    }

    public String getMONEDA_SIGLAS() {
        return this.MONEDA_SIGLAS;
    }

    public void setMONEDA_SIGLAS(String value) {
        this.MONEDA_SIGLAS = value;
    }

    public String getMONEDA_DESCRIPCION() {
        return this.MONEDA_DESCRIPCION;
    }

    public void setMONEDA_DESCRIPCION(String value) {
        this.MONEDA_DESCRIPCION = value;
    }

    public String getFECHA_VENTA() {
        return this.FECHA_VENTA;
    }

    public void setFECHA_VENTA(String value) {
        this.FECHA_VENTA = value;
    }

    public String getFECHA_GENERACION() {
        return this.FECHA_GENERACION;
    }

    public void setFECHA_GENERACION(String value) {
        this.FECHA_GENERACION = value;
    }

    public double getTOTAL() {
        return this.TOTAL;
    }

    public void setTOTAL(double value) {
        this.TOTAL = value;
    }

    public String getIGV() {
        return this.IGV;
    }

    public void setIGV(String value) {
        this.IGV = value;
    }

    public String getSUBTOTAL() {
        return this.SUBTOTAL;
    }

    public void setSUBTOTAL(String value) {
        this.SUBTOTAL = value;
    }

    public String getC1001() {
        return this.C1001;
    }

    public void setC1001(String value) {
        this.C1001 = value;
    }

    public String getVER_C1001() {
        return this.VER_C1001;
    }

    public void setVER_C1001(String value) {
        this.VER_C1001 = value;
    }

    public String getC1002() {
        return this.C1002;
    }

    public void setC1002(String value) {
        this.C1002 = value;
    }

    public String getVER_C1002() {
        return this.VER_C1002;
    }

    public void setVER_C1002(String value) {
        this.VER_C1002 = value;
    }

    public String getC1003() {
        return this.C1003;
    }

    public void setC1003(String value) {
        this.C1003 = value;
    }

    public String getVER_C1003() {
        return this.VER_C1003;
    }

    public void setVER_C1003(String value) {
        this.VER_C1003 = value;
    }

    public String getC1004() {
        return this.C1004;
    }

    public void setC1004(String value) {
        this.C1004 = value;
    }

    public String getVER_C1004() {
        return this.VER_C1004;
    }

    public void setVER_C1004(String value) {
        this.VER_C1004 = value;
    }

    public String getC2004() {
        return this.C2004;
    }

    public void setC2004(String value) {
        this.C2004 = value;
    }

    public String getVER_C2004() {
        return this.VER_C2004;
    }

    public void setVER_C2004(String value) {
        this.VER_C2004 = value;
    }

    public String getC2005() {
        return this.C2005;
    }

    public void setC2005(String value) {
        this.C2005 = value;
    }

    public String getVER_C2005() {
        return this.VER_C2005;
    }

    public void setVER_C2005(String value) {
        this.VER_C2005 = value;
    }

    public String getC20004() {
        return this.C20004;
    }

    public void setC20004(String value) {
        this.C20004 = value;
    }

    public String getVER_C20004() {
        return this.VER_C20004;
    }

    public void setVER_C20004(String value) {
        this.VER_C20004 = value;
    }

    public String getID_TIPO_DOCUMENTO_SUNAT() {
        return this.ID_TIPO_DOCUMENTO_SUNAT;
    }

    public void setID_TIPO_DOCUMENTO_SUNAT(String value) {
        this.ID_TIPO_DOCUMENTO_SUNAT = value;
    }

    public String getSER_NUM() {
        return this.SER_NUM;
    }

    public void setSER_NUM(String value) {
        this.SER_NUM = value;
    }

    public String getNUM_DOC() {
        return this.NUM_DOC;
    }

    public void setNUM_DOC(String value) {
        this.NUM_DOC = value;
    }

    public String getTIPO_DOC_IDENTIDAD() {
        return this.TIPO_DOC_IDENTIDAD;
    }

    public void setTIPO_DOC_IDENTIDAD(String value) {
        this.TIPO_DOC_IDENTIDAD = value;
    }

    public String getNOMCLIENTE() {
        return this.NOMCLIENTE;
    }

    public void setNOMCLIENTE(String value) {
        this.NOMCLIENTE = value;
    }

    public String getTOTAL_TEXTO() {
        return this.TOTAL_TEXTO;
    }

    public void setTOTAL_TEXTO(String value) {
        this.TOTAL_TEXTO = value;
    }


   
   
    public CABECERA() {
    }
        

    public CABECERA(String ID_BOTICA, String ID_VENTA, Date FECHA_VENTA, String ID_TIPO_DOCUMENTO_SUNAT, String SER_NUM, String NUM_DOC,
           String TIPO_DOC_IDENTIDAD, String NOMCLIENTE, double IGV,double TOTAL, double SUBTOTAL, 
           double C1001,double C1002,double C1003,double C1004,double C2004,double C2005,double C20004,
           double VERC1001,double VERC1002,double VERC1003,double VERC1004,double VERC2004,double VERC2005,double VERC20004,
           String TOTAL_TEXTO,
           String MonedaSiglas, String MonedaDesc,
           String Empresa, String RUC, String Direccion, String Ubigeo, String Departamento, String Provincia,
           String Distrito,String Pais,Date FechaGeneracion) {
        
        this.Id_Botica = ID_BOTICA;
        this.id_Venta = ID_VENTA;
        this.Fecha_Venta = FECHA_VENTA;
        this.ID_TIPO_DOCUMENTO_SUNAT = ID_TIPO_DOCUMENTO_SUNAT;
        this.Serie = SER_NUM;
        this.Numero = NUM_DOC;
        this.TIPO_DOC_IDENTIDAD = TIPO_DOC_IDENTIDAD;
        this.NomCliente = NOMCLIENTE;
        //this.IGV = IGV;
        this.Total = TOTAL;
        this.SubTotal = SUBTOTAL;        
        //this.C1001 = C1001;
        //this.C1002 = C1002;
        //this.C1003 = C1003;
        //this.C1004 = C1004;
        //this.C2004 = C2004;
        //this.C2005 = C2005;
        //this.C20004 = C20004;
        this.TOTAL_TEXTO = TOTAL_TEXTO;
        this.MonedaSiglas = MonedaSiglas;
        this.MonedaDescripcion = MonedaDesc;
        this.Empresa = Empresa;
        this.RUC =  RUC;
        this.Direccion = Direccion;
        this.Ubigeo = Ubigeo;
        this.Departamento = Departamento;
        this.Provincia = Provincia;
        this.Distrito =  Distrito;
        this.Pais =  Pais;
        this.FechaGeneracion = FechaGeneracion;
        this.VERC1001 = VERC1001;
        this.VERC1002 = VERC1002;
        this.VERC1003 = VERC1003;
        this.VERC1004 = VERC1004;
        this.VERC2004 = VERC2004;
        this.VERC2005 = VERC2005;
        this.VERC20004 = VERC20004;

    }

    public CABECERA(String ID_BOTICA, String ID_VENTA, Date FECHA_VENTA, String ID_TIPO_DOCUMENTO_SUNAT, String SER_NUM, String NUM_DOC,
           String TIPO_DOC_IDENTIDAD, String NOMCLIENTE, double IGV,double TOTAL, double SUBTOTAL,
           double C1001,double C1002,double C1003,double C1004,double C2004,double C2005,
           double VERC1001,double VERC1002,double VERC1003,double VERC1004,double VERC2004,double VERC2005,
           String TOTAL_TEXTO,
           String MonedaSiglas, String MonedaDesc,
           String Empresa, String RUC, String Direccion, String Ubigeo, String Departamento, String Provincia,
           String Distrito,String Pais,Date FechaGeneracion, 
           String ID_VENTA_REF,Date FECHA_VENTA_REF,String SER_NUM_REF,String ID_TIPO_DOCUMENTO_SUNAT_REF, String ID_CLIENTE_REF,
           String Observaciones) {

        this.Id_Botica = ID_BOTICA;
        this.id_Venta = ID_VENTA;
        this.Fecha_Venta = FECHA_VENTA;
        this.ID_TIPO_DOCUMENTO_SUNAT = ID_TIPO_DOCUMENTO_SUNAT;
        this.Serie = SER_NUM;
        this.Numero = NUM_DOC;
        this.TIPO_DOC_IDENTIDAD = TIPO_DOC_IDENTIDAD;
        this.NomCliente = NOMCLIENTE;
        //this.IGV = IGV;
        this.Total = TOTAL;
        this.SubTotal = SUBTOTAL;
        //this.C1001 = C1001;
        //this.C1002 = C1002;
        //this.C1003 = C1003;
        //this.C1004 = C1004;
        //this.C2004 = C2004;
        //this.C2005 = C2005;
        this.TOTAL_TEXTO = TOTAL_TEXTO;
        this.MonedaSiglas = MonedaSiglas;
        this.MonedaDescripcion = MonedaDesc;
        this.Empresa = Empresa;
        this.RUC =  RUC;
        this.Direccion = Direccion;
        this.Ubigeo = Ubigeo;
        this.Departamento = Departamento;
        this.Provincia = Provincia;
        this.Distrito =  Distrito;
        this.Pais =  Pais;
        this.FechaGeneracion = FechaGeneracion;
        this.Id_Venta_REF = ID_VENTA_REF;
        this.Fecha_Venta_REF = FECHA_VENTA_REF;
        this.ID_TIPO_DOCUMENTO_SUNAT_REF = ID_TIPO_DOCUMENTO_SUNAT_REF;
        this.Serie_ref = SER_NUM_REF;
        this.Id_Cliente_ref = ID_CLIENTE_REF;
        this.Observaciones = Observaciones;

    }
    public CABECERA(String ID_BOTICA, String ID_VENTA, String ESGRATUITA, String POSICION, String FRAC_NUM, double OP_GRAVADA,
           double OP_INAFECTA, double OP_EXONERADA, double OP_GRATUITA,String VER_C1004, double OP_DESCUENTO,String VER_C6000,
           double TOTAL,double IGV,double LineExtionsionAmount,double PRECIO_UNITARIO,double PORCENTAJE_IGV,String PRODUCTO,
           double TOTAL_UNITARIO_SINIGV,double TaxExemptionReasonCode,String ID_PRODUCTO) {

        this.Id_Botica = ID_BOTICA;
        this.id_Venta = ID_VENTA;
        this.ESGRATUITA = ESGRATUITA;
        this.POSICION = POSICION;
        this.Total = TOTAL;
        //this.IGV = IGV;
        this.FRAC_NUM = FRAC_NUM;
        this.OP_GRAVADA = OP_GRAVADA;
        this.OP_INAFECTA = OP_INAFECTA;
        this.OP_EXONERADA = OP_EXONERADA;
        this.OP_GRATUITA = OP_GRATUITA;
        this.VER_C1004 = VER_C1004;
        this.OP_DESCUENTO = OP_DESCUENTO;
        this.VER_C6000 = VER_C6000;        
        this.LineExtionsionAmount = LineExtionsionAmount;
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
        this.PORCENTAJE_IGV = PORCENTAJE_IGV;
        this.PRODUCTO = PRODUCTO;
        this.TOTAL_UNITARIO_SINIGV = TOTAL_UNITARIO_SINIGV;
        this.TaxExemptionReasonCode = TaxExemptionReasonCode;
        this.ID_PRODUCTO=ID_PRODUCTO;

    }

    public CABECERA(String ID_BOTICA, String ID_VENTA, String POSICION, String FRAC_NUM, double OP_GRAVADA,
           double OP_INAFECTA, double OP_EXONERADA, double OP_DESCUENTO,  String VER_C6000,double TOTAL,double IGV,
           double LineExtionsionAmount,double PRECIO_UNITARIO,double PORCENTAJE_IGV,String PRODUCTO,String ID_PRODUCTO,
           double TOTAL_UNITARIO_SINIGV,double TaxExemptionReasonCode) {

        this.Id_Botica = ID_BOTICA;
        this.id_Venta = ID_VENTA;        
        this.POSICION = POSICION;
        this.Total = TOTAL;
        //this.IGV = IGV;
        this.FRAC_NUM = FRAC_NUM;
        this.OP_GRAVADA = OP_GRAVADA;
        this.OP_INAFECTA = OP_INAFECTA;
        this.OP_EXONERADA = OP_EXONERADA;
        this.OP_DESCUENTO = OP_DESCUENTO;
        this.VER_C6000 = VER_C6000;
        this.LineExtionsionAmount = LineExtionsionAmount;
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
        this.PORCENTAJE_IGV = PORCENTAJE_IGV;
        this.PRODUCTO = PRODUCTO;
        this.TOTAL_UNITARIO_SINIGV = TOTAL_UNITARIO_SINIGV;
        this.TaxExemptionReasonCode = TaxExemptionReasonCode;
        this.ID_PRODUCTO=ID_PRODUCTO;

    }

    
   
    public CABECERA(String GRUPO, String POSICION, String TABOPEN, String CAMPO, String DEFECTO,String DATO, String CIERRE,
            String TABCLOSE,String VISUALIZA) {

        this.GRUPO = GRUPO;
        this.POSICION = POSICION;
        this.TABOPEN = TABOPEN;
        this.CAMPO = CAMPO;
        this.DEFECTO = DEFECTO;
        this.DATO = DATO;
        this.CIERRE = CIERRE;
        this.TABCLOSE = TABCLOSE;
        this.VISUALIZA = VISUALIZA;
    } 

    public String RETORNA(String CAMPO) {
        if (CAMPO.equals("EMPRESA")) {
            return this.EMPRESA;
        } else if (CAMPO.equals("RUC")) {
            return this.RUC;
        } else if (CAMPO.equals("DIRECCION")) {
            return this.DIRECCION;
        } else if (CAMPO.equals("UBIGEO")) {
            return this.UBIGEO;
        } else if (CAMPO.equals("DEPARTAMENTO")) {
            return this.DEPARTAMENTO;
        } else if (CAMPO.equals("PROVINCIA")) {
            return this.PROVINCIA;
        } else if (CAMPO.equals("DISTRITO")) {
            return this.DISTRITO;
        } else if (CAMPO.equals("PAIS")) {
            return this.PAIS;
        } else if (CAMPO.equals("ID_BOTICA")) {
            return this.ID_BOTICA;
        } else if (CAMPO.equals("ID_VENTA")) {
            return this.ID_VENTA;
        } else if (CAMPO.equals("MONEDA_SIGLAS")) {
            return this.MONEDA_SIGLAS;
        } else if (CAMPO.equals("MONEDA_DESCRIPCION")) {
            return this.MONEDA_DESCRIPCION;
        } else if (CAMPO.equals("FECHA_VENTA")) {
            return this.FECHA_VENTA;
        } else if (CAMPO.equals("FECHA_GENERACION")) {
            return this.FECHA_GENERACION;
        } else if (CAMPO.equals("TOTAL")) {
            return Double.toString(this.TOTAL);
        } else if (CAMPO.equals("IGV")) {
            return this.IGV;
        } else if (CAMPO.equals("SUBTOTAL")) {
            return this.SUBTOTAL;
        } else if (CAMPO.equals("C1001")) {
            return this.C1001;
        } else if (CAMPO.equals("VER_C1001")) {
            return this.VER_C1001;
        } else if (CAMPO.equals("C1002")) {
            return this.C1002;
        } else if (CAMPO.equals("VER_C1002")) {
            return this.VER_C1002;
        } else if (CAMPO.equals("C1003")) {
            return this.C1003;
        } else if (CAMPO.equals("VER_C1003")) {
            return this.VER_C1003;
        } else if (CAMPO.equals("C1004")) {
            return this.C1004;
        } else if (CAMPO.equals("VER_C1004")) {
            return this.VER_C1004;
        } else if (CAMPO.equals("C2004")) {
            return this.C2004;
        } else if (CAMPO.equals("VER_C2004")) {
            return this.VER_C2004;
        } else if (CAMPO.equals("C2005")) {
            return this.C2005;
        } else if (CAMPO.equals("VER_C2005")) {
            return this.VER_C2005;
        } else if (CAMPO.equals("C20004")) {
            return this.C20004;
        } else if (CAMPO.equals("VER_C20004")) {
            return this.VER_C20004;
        } else if (CAMPO.equals("ID_TIPO_DOCUMENTO_SUNAT")) {
            return this.ID_TIPO_DOCUMENTO_SUNAT;
        } else if (CAMPO.equals("SER_NUM")) {
            return this.SER_NUM;
        } else if (CAMPO.equals("NUM_DOC")) {
            return this.NUM_DOC;
        } else if (CAMPO.equals("TIPO_DOC_IDENTIDAD")) {
            return this.TIPO_DOC_IDENTIDAD;
        } else if (CAMPO.equals("NOMCLIENTE")) {
            return this.NOMCLIENTE;
        } else if (CAMPO.equals("TOTAL_TEXTO")) {
            return this.TOTAL_TEXTO;
        }
        return "";
    }

    public String getUbigeo() {
        return Ubigeo;
    }
    public void setUbigeo(String Ubigeo) {
        this.Ubigeo = Ubigeo;
    }

    public String getDistrito() {
        return Distrito;
    }
    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

    public String getPais() {
        return Pais;
    }
    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public Date getFechaGeneracion() {
        return FechaGeneracion;
    }
    public void setFechaGeneracion(Date FechaGeneracion) {
        this.FechaGeneracion = FechaGeneracion;
    }

    public String getDepartamento() {
        return Departamento;
    }
    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getProvincia() {
        return Provincia;
    }
    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public String getEmpresa() {
        return Empresa;
    }
    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    /*public String getRUC() {
        return RUC;
    }
    public void setRUC(String RUC) {
        this.RUC = RUC;
    }*/

    /*public String getDireccion() {
        return Direccion;
    }
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }


    public String getMonedaSiglas() {
        return MonedaSiglas;
    }
    public void setMonedaSiglas(String MonedaSiglas) {
        this.MonedaSiglas = MonedaSiglas;
    }
    public String getMonedaDescripcion() {
        return MonedaDescripcion;
    }
    public void setMonedaDescripcion(String MonedaDescripcion) {
        this.MonedaDescripcion = MonedaDescripcion;
    }

    public String getVISUALIZA() {
        return VISUALIZA;
    }
    public void setVISUALIZA(String VISUALIZA) {
        this.VISUALIZA = VISUALIZA;
    }

    public String getTABCLOSE() {
        return TABCLOSE;
    }
    public void setTABCLOSE(String TABCLOSE) {
        this.TABCLOSE = TABCLOSE;
    }

    public String getCIERRE() {
        return CIERRE;
    }
    public void setCIERRE(String CIERRE) {
        this.CIERRE = CIERRE;
    }

    public String getDATO() {
        return DATO;
    }
    public void setDATO(String DATO) {
        this.DATO = DATO;
    }

    public String getDEFECTO() {
        return DEFECTO;
    }
    public void setDEFECTO(String DEFECTO) {
        this.DEFECTO = DEFECTO;
    }

    public String getCAMPO() {
        return CAMPO;
    }
    public void setCAMPO(String CAMPO) {
        this.CAMPO = CAMPO;
    }

    public String getGRUPO() {
        return GRUPO;
    }
    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
    }

    public String getPOSICION() {
        return POSICION;
    }
    public void setPOSICION(String POSICION) {
        this.POSICION = POSICION;
    }

    public String getTABOPEN() {
        return TABOPEN;
    }
    public void setTABOPEN(String TABOPEN) {
        this.TABOPEN = TABOPEN;
    }


    /*public String getID_TIPO_DOCUMENTO_SUNAT() {
        return ID_TIPO_DOCUMENTO_SUNAT;
    }
    public void setID_TIPO_DOCUMENTO_SUNAT(String ID_TIPO_DOCUMENTO_SUNAT) {
        this.ID_TIPO_DOCUMENTO_SUNAT = ID_TIPO_DOCUMENTO_SUNAT;
    }*/

    /*public String getNomCliente() {
        return NomCliente;
    }
    public void setNomCliente(String NomCliente) {
        this.NomCliente = NomCliente;
    }
    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }
    public void setID_PRODUCTO(String ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getId_Venta() {
        return id_Venta;
    }
    public void setId_Venta(String id_Venta) {
        this.id_Venta = id_Venta;
    }

    public double getTotal() {
        return Total;
    }
    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    public Date getFecha_Venta() {
        return Fecha_Venta;
    }
    public void setFecha_Venta(Date Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }
    
    /*public double getIGV() {
        return IGV;
    }
    public void setIGV(double IGV) {
        this.IGV = IGV;
    }*/

    /*public String getId_Botica() {
        return Id_Botica;
    }
    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
    }    

    public String getNumero() {
        return Numero;
    }
    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getSerie() {
        return Serie;
    }
    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public double getSubTotal() {
        return SubTotal;
    }
    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }
    /*
    public String getTIPO_DOC_IDENTIDAD() {
        return TIPO_DOC_IDENTIDAD;
    }
    public void setTIPO_DOC_IDENTIDAD(String TIPO_DOC_IDENTIDAD) {
        this.TIPO_DOC_IDENTIDAD = TIPO_DOC_IDENTIDAD;
    }

    public String getTOTAL_TEXTO() {
        return TOTAL_TEXTO;
    }
    public void setTOTAL_TEXTO(String TOTAL_TEXTO) {
        this.TOTAL_TEXTO = TOTAL_TEXTO;
    }

    public double getC1001() {
        return C1001;
    }
    public void setC1001(double C1001) {
        this.C1001 = C1001;
    }
    
    public double getC1002() {
        return C1002;
    }
    public void setC1002(double C1002) {
        this.C1002 = C1002;
    }

    public double getC1003() {
        return C1003;
    }
    public void setC1003(double C1003) {
        this.C1003 = C1003;
    }

    public double getC1004() {
        return C1004;
    }
    public void setC1004(double C1004) {
        this.C1004 = C1004;
    }

    public double getC2004() {
        return C2004;
    }
    public void setC2004(double C2004) {
        this.C2004 = C2004;
    }

    public double getC2005() {
        return C2005;
    }
    public void setC2005(double C2005) {
        this.C2005 = C2005;
    }

    public double getC20004() {
        return C20004;
    }
    public void setC20004(double C20004) {
        this.C20004 = C20004;
    }*/

    /*public double getVERC1001() {
        return VERC1001;
    }
    public void setVERC1001(double VERC1001) {
        this.VERC1001 = VERC1001;
    }

    public double getVERC1002() {
        return VERC1002;
    }
    public void setVERC1002(double VERC1002) {
        this.VERC1002 = VERC1002;
    }

    public double getVERC1003() {
        return VERC1003;
    }
    public void setVERC1003(double VERC1003) {
        this.VERC1003 = VERC1003;
    }

    /*public double getVERC1004() {
        return VERC1004;
    }
    public void setVERC1004(double VERC1004) {
        this.VERC1004 = VERC1004;
    }*/

    /*public double getVERC2004() {
        return VERC2004;
    }
    public void setVERC2004(double VERC2004) {
        this.VERC2004 = VERC2004;
    }

    public double getVERC2005() {
        return VERC2005;
    }
    public void setVERC2005(double VERC2005) {
        this.VERC2005 = VERC2005;
    }

    public double getVERC20004() {
        return VERC20004;
    }
    public void setVERC20004(double VERC20004) {
        this.VERC20004 = VERC20004;
    }
    
    public String getFRAC_NUM() {
        return FRAC_NUM;
    }
    public void setFRAC_NUM(String FRAC_NUM) {
        this.FRAC_NUM = FRAC_NUM;
    }
    /*public String getVER_C1004() {
        return VER_C1004;
    }
    public void setVER_C1004(String VER_C1004) {
        this.VER_C1004 = VER_C1004;
    }*/
    /*public String getVER_C6000() {
        return VER_C6000;
    }
    public void setVER_C6000(String VER_C6000) {
        this.VER_C6000 = VER_C6000;
    }
    public String getPRODUCTO() {
        return PRODUCTO;
    }
    public void setPRODUCTO(String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }
    public String getESGRATUITA() {
        return ESGRATUITA;
    }
    public void setESGRATUITA(String ESGRATUITA) {
        this.ESGRATUITA = ESGRATUITA;
    }


    public double getOP_GRAVADA() {
        return OP_GRAVADA;
    }
    public void setOP_GRAVADA(double OP_GRAVADA) {
        this.OP_GRAVADA = OP_GRAVADA;
    }

    public double getOP_INAFECTA() {
        return OP_INAFECTA;
    }
    public void setOP_INAFECTA(double OP_INAFECTA) {
        this.OP_INAFECTA = OP_INAFECTA;
    }
    
    public double getOP_EXONERADA() {
        return OP_EXONERADA;
    }
    public void setOP_EXONERADA(double OP_EXONERADA) {
        this.OP_EXONERADA = OP_EXONERADA;
    }
    
    public double getOP_GRATUITA() {
        return OP_GRATUITA;
    }
    public void setOP_GRATUITA(double OP_GRATUITA) {
        this.OP_GRATUITA = OP_GRATUITA;
    }
    
    public double getOP_DESCUENTO() {
        return OP_DESCUENTO;
    }
    public void setOP_DESCUENTO(double OP_DESCUENTO) {
        this.OP_DESCUENTO = OP_DESCUENTO;
    }
    
    public double getLineExtionsionAmount() {
        return LineExtionsionAmount;
    }
    public void setLineExtionsionAmount(double LineExtionsionAmount) {
        this.LineExtionsionAmount = LineExtionsionAmount;
    }
    
    public double getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }
    public void setPRECIO_UNITARIO(double PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }
    
    public double getPORCENTAJE_IGV() {
        return PORCENTAJE_IGV;
    }
    public void setPORCENTAJE_IGV(double PORCENTAJE_IGV) {
        this.PORCENTAJE_IGV = PORCENTAJE_IGV;
    }

    public double getTOTAL_UNITARIO_SINIGV() {
        return TOTAL_UNITARIO_SINIGV;
    }
    public void setTOTAL_UNITARIO_SINIGV(double TOTAL_UNITARIO_SINIGV) {
        this.TOTAL_UNITARIO_SINIGV = TOTAL_UNITARIO_SINIGV;
    }
    public double getTaxExemptionReasonCode() {
        return TaxExemptionReasonCode;
    }
    public void setTaxExemptionReasonCode(double TaxExemptionReasonCode) {
        this.TaxExemptionReasonCode = TaxExemptionReasonCode;
    }
    
    public String getId_Venta_REF() {
        return Id_Venta_REF;
    }
    public void setId_Venta_REF(String Id_Venta_REF) {
        this.Id_Venta_REF = Id_Venta_REF;
    }

    public Date getFecha_Venta_REF() {
        return Fecha_Venta_REF;
    }
    public void setId_Venta_REF(Date Fecha_Venta_REF) {
        this.Fecha_Venta_REF = Fecha_Venta_REF;
    }

    public String getID_TIPO_DOCUMENTO_SUNAT_REF() {
        return ID_TIPO_DOCUMENTO_SUNAT_REF;
    }
    public void setID_TIPO_DOCUMENTO_SUNAT_REF(String ID_TIPO_DOCUMENTO_SUNAT_REF) {
        this.ID_TIPO_DOCUMENTO_SUNAT_REF = ID_TIPO_DOCUMENTO_SUNAT_REF;
    }

    public String getSerie_ref() {
        return Serie_ref;
    }
    public void setSerie_ref(String Serie_ref) {
        this.Serie_ref = Serie_ref;
    }

    public String getId_Cliente_ref() {
        return Id_Cliente_ref;
    }
    public void setId_Cliente_ref(String Id_Cliente_ref) {
        this.Id_Cliente_ref = Id_Cliente_ref;
    }

    public String getObservaciones() {
        return Observaciones;
    }
    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    
    @Override
    public int compare(CABECERA o1, CABECERA o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(CABECERA o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}*/
