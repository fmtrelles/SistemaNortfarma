/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;
/**
 *
 * @author Administrador
 */
public class DETALLE implements Comparator<DETALLE>, Comparable<DETALLE> {

    String ID_BOTICA;
    String ID_VENTA;
    String ESGRATUITA;
    String POSICION;
    String FRAC_NUM;
    String OP_GRAVADA;
    String OP_INAFECTA;
    String OP_EXONERADA;
    String OP_GRATUITA;
    String VER_C1004;
    String OP_DESCUENTO;
    String VER_C6000;
    String TOTAL;
    String IGV;
    String LINEEXTIONSIONAMOUNT;
    String PRECIO_UNITARIO;
    String PORCENTAJE_IGV;
    String PRODUCTO;
    String ID_PRODUCTO;
    String TOTAL_UNITARIO_SINIGV;
    String TAXEXEMPTIONREASONCODE;


    public DETALLE() {
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

    public String getESGRATUITA() {
        return this.ESGRATUITA;
    }
    public void setESGRATUITA(String value) {
        this.ESGRATUITA = value;
    }

    public String getPOSICION() {
        return this.POSICION;
    }
    public void setPOSICION(String value) {
        this.POSICION = value;
    }

    public String getFRAC_NUM() {
        return this.FRAC_NUM;
    }
    public void setFRAC_NUM(String value) {
        this.FRAC_NUM = value;
    }

    public String getOP_GRAVADA() {
        return this.OP_GRAVADA;
    }
    public void setOP_GRAVADA(String value) {
        this.OP_GRAVADA = value;
    }

    public String getOP_INAFECTA() {
        return this.OP_INAFECTA;
    }
    public void setOP_INAFECTA(String value) {
        this.OP_INAFECTA = value;
    }

    public String getOP_EXONERADA() {
        return this.OP_EXONERADA;
    }
    public void setOP_EXONERADA(String value) {
        this.OP_EXONERADA = value;
    }

    public String getOP_GRATUITA() {
        return this.OP_GRATUITA;
    }
    public void setOP_GRATUITA(String value) {
        this.OP_GRATUITA = value;
    }

    public String getVER_C1004() {
        return this.VER_C1004;
    }
    public void setVER_C1004(String value) {
        this.VER_C1004 = value;
    }

    public String getOP_DESCUENTO() {
        return this.OP_DESCUENTO;
    }
    public void setOP_DESCUENTO(String value) {
        this.OP_DESCUENTO = value;
    }

    public String getVER_C6000() {
        return this.VER_C6000;
    }
    public void setVER_C6000(String value) {
        this.VER_C6000 = value;
    }

    public String getTOTAL() {
        return this.TOTAL;
    }
    public void setTOTAL(String value) {
        this.TOTAL = value;
    }

    public String getIGV() {
        return this.IGV;
    }
    public void setIGV(String value) {
        this.IGV = value;
    }

    public String getLINEEXTIONSIONAMOUNT() {
        return this.LINEEXTIONSIONAMOUNT;
    }
    public void setLINEEXTIONSIONAMOUNT(String value) {
        this.LINEEXTIONSIONAMOUNT = value;
    }

    public String getPRECIO_UNITARIO() {
        return this.PRECIO_UNITARIO;
    }
    public void setPRECIO_UNITARIO(String value) {
        this.PRECIO_UNITARIO = value;
    }

    public String getPORCENTAJE_IGV() {
        return this.PORCENTAJE_IGV;
    }
    public void setPORCENTAJE_IGV(String value) {
        this.PORCENTAJE_IGV = value;
    }

    public String getPRODUCTO() {
        return this.PRODUCTO;
    }
    public void setPRODUCTO(String value) {
        this.PRODUCTO = value;
    }

    public String getID_PRODUCTO() {
        return this.ID_PRODUCTO;
    }
    public void setID_PRODUCTO(String value) {
        this.ID_PRODUCTO = value;
    }

    public String getTOTAL_UNITARIO_SINIGV() {
        return this.TOTAL_UNITARIO_SINIGV;
    }
    public void setTOTAL_UNITARIO_SINIGV(String value) {
        this.TOTAL_UNITARIO_SINIGV = value;
    }

    public String getTAXEXEMPTIONREASONCODE() {
        return this.TAXEXEMPTIONREASONCODE;
    }
    public void setTAXEXEMPTIONREASONCODE(String value) {
        this.TAXEXEMPTIONREASONCODE = value;
    }


    public String RETORNA(String CAMPO) {
        if (CAMPO.equals("ID_BOTICA")) {
            return this.ID_BOTICA;
        } else if (CAMPO.equals("ID_VENTA")) {
            return this.ID_VENTA;
        } else if (CAMPO.equals("ESGRATUITA")) {
            return this.ESGRATUITA;
        } else if (CAMPO.equals("POSICION")) {
            return this.POSICION;
        } else if (CAMPO.equals("FRAC_NUM")) {
            return this.FRAC_NUM;
        } else if (CAMPO.equals("OP_GRAVADA")) {
            return this.OP_GRAVADA;
        } else if (CAMPO.equals("OP_INAFECTA")) {
            return this.OP_INAFECTA;
        } else if (CAMPO.equals("OP_EXONERADA")) {
            return this.OP_EXONERADA;
        } else if (CAMPO.equals("OP_GRATUITA")) {
            return this.OP_GRATUITA;
        } else if (CAMPO.equals("VER_C1004")) {
            return this.VER_C1004;
        } else if (CAMPO.equals("OP_DESCUENTO")) {
            return this.OP_DESCUENTO;
        } else if (CAMPO.equals("VER_C6000")) {
            return this.VER_C6000;
        } else if (CAMPO.equals("TOTAL")) {
            return this.TOTAL;
        } else if (CAMPO.equals("IGV")) {
            return this.IGV;
        } else if (CAMPO.equals("LineExtionsionAmount")) {
            return this.LINEEXTIONSIONAMOUNT;
        } else if (CAMPO.equals("PRECIO_UNITARIO")) {
            return this.PRECIO_UNITARIO;
        } else if (CAMPO.equals("PORCENTAJE_IGV")) {
            return this.PORCENTAJE_IGV;
        } else if (CAMPO.equals("PRODUCTO")) {
            return this.PRODUCTO;
        } else if (CAMPO.equals("Id_Producto")) {
            return this.ID_PRODUCTO;
        } else if (CAMPO.equals("TOTAL_UNITARIO_SINIGV")) {
            return this.TOTAL_UNITARIO_SINIGV;
        } else if (CAMPO.equals("TAXEXEMPTIONREASONCODE")) {
            return this.TAXEXEMPTIONREASONCODE;
        }
        return "";
    }

     @Override
    public int compare(DETALLE o1, DETALLE o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(DETALLE o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
