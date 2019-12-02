/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

public class XML_CAB implements Comparator<XML_CAB>, Comparable<XML_CAB> {

    String GRUPO;
    String POSICION;
    String TABOPEN;
    String CAMPO = "";
    String DEFECTO;
    String DATO;
    String CIERRE;
    String TABCLOSE;
    String VISUALIZA;

    public XML_CAB() {
    }

    public String getGRUPO() {
        return this.GRUPO;
    }

    public void setGRUPO(String value) {
        this.GRUPO = value;
    }

    public String getPOSICION() {
        return this.POSICION;
    }

    public void setPOSICION(String value) {
        this.POSICION = value;
    }

    public String getTABOPEN() {
        if (TABOPEN == null) {
            return "";
        } else {
            return this.TABOPEN;
        }
    }

    public void setTABOPEN(String value) {
        this.TABOPEN = value;
    }

    public String getCAMPO() {
        if (CAMPO == null) {
            return "";
        } else {
            return this.CAMPO;
        }
    }

    public void setCAMPO(String value) {
        this.CAMPO = value;
    }

    public String getDEFECTO() {
        if (DEFECTO == null) {
            return "";
        } else {
            return this.DEFECTO;
        }
    }

    public void setDEFECTO(String value) {
        this.DEFECTO = value;
    }

    public String getDATO() {
        return this.DATO;
    }

    public void setDATO(String value) {
        this.DATO = value;
    }

    public String getCIERRE() {
        return this.CIERRE;
    }

    public void setCIERRE(String value) {
        this.CIERRE = value;
    }

    public String getTABCLOSE() {
        if (TABCLOSE == null) {
            return "";
        } else {
            return this.TABCLOSE;
        }
    }

    public void setTABCLOSE(String value) {
        this.TABCLOSE = value;
    }

    public String getVISUALIZA() {
        return this.VISUALIZA;
    }

    public void setVISUALIZA(String value) {
        this.VISUALIZA = value;
    }

    @Override
    public int compare(XML_CAB o1, XML_CAB o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(XML_CAB o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
