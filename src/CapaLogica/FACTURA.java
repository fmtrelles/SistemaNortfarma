package CapaLogica;

import CapaDatos.TipoDocumentoData;
import CapaDatos.VentaData;
import CapaDatos.ConexionPool;
import entidad.Cajas;
import entidad.Detalle_VentaDelivery;
import entidad.NotaCredito;
import entidad.Proforma;
import entidad.ResultadoVenta;
import entidad.TipoCambio;
import entidad.TipoDocumento;
import entidad.Venta;
import entidad.CABECERA;
import entidad.XML_CAB;
import entidad.DETALLE;
import entidad.Venta_Delivery;
import entidad.Ventas_Tipo_Pago;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import entidad.ProductosPrecios;
import javax.swing.table.DefaultTableModel;
import CapaLogica.LogicaTipoMovimiento;
import java.util.Iterator;
import javax.swing.JList;

public class FACTURA {

    Connection conex;
    Object[] listadeDatos = new Object[9];
    List<Object> listaProductosXML = new ArrayList<Object>();
    static ProductosPrecios productoPrecio = null;
    VentaData objventa;
    CallableStatement procedure = null, procedure1 = null, procedure2 = null, procedure3 = null;
    ResultSet rs, rs1, rs2, rs3;
    TipoDocumentoData objtipoventas;
    public Numero_a_Letra ATEXTO = new Numero_a_Letra();
    LogicaProducto objproducto = new LogicaProducto();
    private List<ProductosPrecios> listaProductos = new ArrayList<ProductosPrecios>();


    public String GeneraXML(String idventa,String id_botica) {

        String Cadena = "";
        listaProductosXML.clear();

        List<CABECERA> listaVtasCabecera = new ArrayList<CABECERA>();
        List<DETALLE> listaVtasDetalle = new ArrayList<DETALLE>();


        Numero_a_Letra NumLetra = new Numero_a_Letra();

        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call XML_FE_CABECERA (?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("PId_Botica", id_botica);

            rs = procedure.executeQuery();

            while (rs.next()) {

                CABECERA cab = new CABECERA();
                cab.setEMPRESA(rs.getString("EMPRESA"));
                cab.setRUC(rs.getString("RUC"));
                cab.setDIRECCION(rs.getString("DIRECCION"));
                cab.setUBIGEO(rs.getString("UBIGEO"));
                cab.setDEPARTAMENTO(rs.getString("DEPARTAMENTO"));
                cab.setPROVINCIA(rs.getString("PROVINCIA"));
                cab.setDISTRITO(rs.getString("DISTRITO"));
                cab.setPAIS(rs.getString("PAIS"));
                cab.setID_BOTICA(rs.getString("ID_BOTICA"));
                cab.setID_VENTA(rs.getString("ID_VENTA"));
                cab.setMONEDA_SIGLAS(rs.getString("MONEDA_SIGLAS"));
                cab.setMONEDA_DESCRIPCION(rs.getString("MONEDA_DESCRIPCION"));
                cab.setFECHA_VENTA(rs.getString("FECHA_VENTA"));
                cab.setFECHA_GENERACION(rs.getString("FECHA_GENERACION"));
                cab.setTOTAL(rs.getDouble("TOTAL"));
                cab.setIGV(rs.getString("IGV"));
                cab.setSUBTOTAL(rs.getString("SUBTOTAL"));
                cab.setC1001(rs.getString("C1001"));
                cab.setVER_C1001(rs.getString("VER_C1001"));
                cab.setC1002(rs.getString("C1002"));
                cab.setVER_C1002(rs.getString("VER_C1002"));
                cab.setC1003(rs.getString("C1003"));
                cab.setVER_C1003(rs.getString("VER_C1003"));
                cab.setC1004(rs.getString("C1004"));
                cab.setVER_C1004(rs.getString("VER_C1004"));
                cab.setC2004(rs.getString("C2004"));
                cab.setVER_C2004(rs.getString("VER_C2004"));
                cab.setC2005(rs.getString("C2005"));
                cab.setVER_C2005(rs.getString("VER_C2005"));
                cab.setC20004(rs.getString("C20004"));
                cab.setVER_C20004(rs.getString("VER_C20004"));
                cab.setID_TIPO_DOCUMENTO_SUNAT(rs.getString("ID_TIPO_DOCUMENTO_SUNAT"));
                cab.setSER_NUM(rs.getString("SER_NUM"));
                cab.setNUM_DOC(rs.getString("NUM_DOC"));
                cab.setTIPO_DOC_IDENTIDAD(rs.getString("TIPO_DOC_IDENTIDAD"));
                cab.setNOMCLIENTE(rs.getString("NOMCLIENTE"));
                cab.setTOTAL_TEXTO(rs.getString("TOTAL_TEXTO"));
                listaVtasCabecera.add(cab);

            }

            double MYTOTAL = listaVtasCabecera.get(0).getTOTAL();
            String money = listaVtasCabecera.get(0).getMONEDA_DESCRIPCION();
            String TOTALTEXTO = ATEXTO.Convertir(String.valueOf(MYTOTAL).toString(), true, money);
            ((CABECERA) listaVtasCabecera.get(0)).setTOTAL_TEXTO(TOTALTEXTO);

            procedure1 = conex.prepareCall("SELECT * FROM XML_BE WHERE GRUPO=10 ORDER BY POSICION");
            rs1 = procedure1.executeQuery();

            List<XML_CAB> LstXML = new ArrayList<XML_CAB>();
            while (rs1.next()) {
                XML_CAB xml = new XML_CAB();
                xml.setGRUPO(rs1.getString("GRUPO"));
                xml.setPOSICION(rs1.getString("POSICION"));
                xml.setTABOPEN(rs1.getString("TABOPEN"));
                xml.setCAMPO(rs1.getString("CAMPO"));
                xml.setDEFECTO(rs1.getString("DEFECTO"));
                xml.setDATO(rs1.getString("DATO"));
                xml.setCIERRE(rs1.getString("CIERRE"));
                xml.setTABCLOSE(rs1.getString("TABCLOSE"));
                xml.setVISUALIZA(rs1.getString("VISUALIZA"));
                LstXML.add(xml);
            }

            for (int i = 0; i < LstXML.size(); i++) {
                XML_CAB item = LstXML.get(i);
                if (item.getVISUALIZA().contains("1001")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getC1001().toString());
                } else if (item.getVISUALIZA().contains("1002")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C1001().toString());
                } else if (item.getVISUALIZA().contains("1003")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C1003().toString());
                } else if (item.getVISUALIZA().contains("1004")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C1004().toString());
                } else if (item.getVISUALIZA().contains("2004")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C2004().toString());
                } else if (item.getVISUALIZA().contains("2005")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C2005().toString());
                } else if (item.getVISUALIZA().contains("20004")) {
                    item.setVISUALIZA(((CABECERA) listaVtasCabecera.get(0)).getVER_C20004().toString());
                }
                LstXML.set(i, item);
            }

            for (int i = 0; i < LstXML.size(); i++) {
                XML_CAB item = LstXML.get(i);
                if (item.getVISUALIZA().contains("1")) {
                    Cadena = Cadena + item.getTABOPEN().toString();
                    if (item.getCAMPO().length()!=0) {
                        Cadena = Cadena + ((CABECERA) listaVtasCabecera.get(0)).RETORNA(item.getCAMPO().toString()).toString();
                    } else if (item.getDEFECTO().length()!=0) {
                        Cadena = Cadena + item.getDEFECTO().toString();
                    }
                    Cadena = Cadena + item.getTABCLOSE().toString();
                    Cadena = Cadena + "\n";
                }
            }

            /* DETALLE VENTA */
            /*****************/
            procedure2 = conex.prepareCall("{ call XML_FE_DETALLE (?,?) }");
            procedure2.setString("PID_VENTA", idventa);
            procedure2.setString("PId_Botica", id_botica);

            rs2 = procedure2.executeQuery();

            while (rs2.next()) {

                DETALLE det = new DETALLE();
                det.setID_BOTICA(rs2.getString("ID_BOTICA"));
                det.setID_VENTA(rs2.getString("ID_VENTA"));
                det.setESGRATUITA(rs2.getString("ESGRATUITA"));
                det.setPOSICION(rs2.getString("POSICION"));
                det.setFRAC_NUM(rs2.getString("FRAC_NUM"));
                det.setOP_GRAVADA(rs2.getString("OP_GRAVADA"));
                det.setOP_INAFECTA(rs2.getString("OP_INAFECTA"));
                det.setOP_EXONERADA(rs2.getString("OP_EXONERADA"));
                det.setOP_GRATUITA(rs2.getString("OP_GRATUITA"));
                det.setVER_C1004(rs2.getString("VER_C1004"));
                det.setOP_DESCUENTO(rs2.getString("OP_DESCUENTO"));
                det.setVER_C6000(rs2.getString("VER_C6000"));
                det.setTOTAL(rs2.getString("TOTAL"));
                det.setIGV(rs2.getString("IGV"));
                det.setLINEEXTIONSIONAMOUNT(rs2.getString("LINEEXTIONSIONAMOUNT"));
                det.setPRECIO_UNITARIO(rs2.getString("PRECIO_UNITARIO"));
                det.setPORCENTAJE_IGV(rs2.getString("PORCENTAJE_IGV"));
                det.setPRODUCTO(rs2.getString("PRODUCTO"));
                det.setTOTAL_UNITARIO_SINIGV(rs2.getString("TOTAL_UNITARIO_SINIGV"));
                det.setTAXEXEMPTIONREASONCODE(rs2.getString("TAXEXEMPTIONREASONCODE"));
                det.setID_PRODUCTO(rs2.getString("ID_PRODUCTO"));
                listaVtasDetalle.add(det);
            }

            procedure3 = conex.prepareCall("SELECT * FROM XML_BE WHERE GRUPO=70 ORDER BY POSICION");
            rs3 = procedure3.executeQuery();

            LstXML.clear();
            LstXML = new ArrayList<XML_CAB>();
            while (rs3.next()) {
                XML_CAB xml = new XML_CAB();
                xml.setGRUPO(rs3.getString("GRUPO"));
                xml.setPOSICION(rs3.getString("POSICION"));
                xml.setTABOPEN(rs3.getString("TABOPEN"));
                xml.setCAMPO(rs3.getString("CAMPO"));
                xml.setDEFECTO(rs3.getString("DEFECTO"));
                xml.setDATO(rs3.getString("DATO"));
                xml.setCIERRE(rs3.getString("CIERRE"));
                xml.setTABCLOSE(rs3.getString("TABCLOSE"));
                xml.setVISUALIZA(rs3.getString("VISUALIZA"));
                LstXML.add(xml);
            }

            for (int i = 0; i < listaVtasDetalle.size(); i++) {

               List<XML_CAB> AuxXML = new ArrayList<XML_CAB>();
                AuxXML.clear();
                for (int z = 0; z < LstXML.size(); z++) {
                    XML_CAB xml = new XML_CAB();
                    xml.setGRUPO(LstXML.get(z).getGRUPO());
                    xml.setPOSICION(LstXML.get(z).getPOSICION());
                    xml.setTABOPEN(LstXML.get(z).getTABOPEN());
                    xml.setCAMPO(LstXML.get(z).getCAMPO());
                    xml.setDEFECTO(LstXML.get(z).getDEFECTO());
                    xml.setDATO(LstXML.get(z).getDATO());
                    xml.setCIERRE(LstXML.get(z).getCIERRE());
                    xml.setTABCLOSE(LstXML.get(z).getTABCLOSE());
                    xml.setVISUALIZA(LstXML.get(z).getVISUALIZA());
                    AuxXML.add(xml);
                }

                for (int j = 0; j < AuxXML.size(); j++) {
                    XML_CAB item = AuxXML.get(j);
                    if (item.getVISUALIZA().contains("6000")) {
                        item.setVISUALIZA(((DETALLE) listaVtasDetalle.get(i)).getVER_C6000().toString());
                    } else if (item.getVISUALIZA().contains("1004")) {
                        item.setVISUALIZA(((DETALLE) listaVtasDetalle.get(i)).getVER_C1004().toString());
                    } else if (item.getVISUALIZA().contains("10004")) {
                        item.setVISUALIZA(((DETALLE) listaVtasDetalle.get(i)).getESGRATUITA().toString());
                    }
                    AuxXML.set(j, item);
                }

                for (int k = 0; k < AuxXML.size(); k++) {
                    XML_CAB item = AuxXML.get(k);
                    if (item.getVISUALIZA().contains("1")) {
                        Cadena = Cadena + item.getTABOPEN().toString();
                        if (item.getCAMPO().length()!=0) {
                            Cadena = Cadena + ((DETALLE) listaVtasDetalle.get(i)).RETORNA(item.getCAMPO().toString()).toString();
                        } else if (item.getDEFECTO().length()!=0) {
                            Cadena = Cadena + item.getDEFECTO().toString();
                        }
                        Cadena = Cadena + item.getTABCLOSE().toString();
                        Cadena = Cadena + "\n";
                    }
                }
            }


            Cadena = Cadena + "</Invoice>";
            Cadena = Cadena + "\n";
            Cadena = Cadena.replace("<MONEDA_SIGLAS>", listaVtasCabecera.get(0).getMONEDA_SIGLAS());

            rs.close();
            rs1.close();
            rs2.close();
            rs3.close();

        } catch (OutOfMemoryError ex) {
            System.out.println("Error de memoria" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Cadena;

    }

        
        
        
        
        
        /*String Cadena = "";
        listaProductosXML.clear();

        List<CABECERA> listaVtasCabecera = new ArrayList<CABECERA>();
        List<CABECERA> listaVtasDetalle = new ArrayList<CABECERA>();
        List<CABECERA> listaXML = new ArrayList<CABECERA>();
        List<CABECERA> listaXMLDetalle = new ArrayList<CABECERA>();
        List<CABECERA> RecuperaXMLBECABECERA = new ArrayList<CABECERA>();
        List<CABECERA> RecuperaXMLBEDETALLE = new ArrayList<CABECERA>();
        Numero_a_Letra NumLetra = new Numero_a_Letra();

        listaVtasCabecera.removeAll(listaVtasCabecera);
        listaVtasDetalle.removeAll(listaVtasDetalle);
        listaXML.removeAll(listaXML);
        listaXMLDetalle.removeAll(listaXMLDetalle);
        RecuperaXMLBECABECERA.removeAll(RecuperaXMLBECABECERA);
        RecuperaXMLBEDETALLE.removeAll(RecuperaXMLBEDETALLE);


        try {

            conex = new ConexionPool().getConnection();
            procedure = conex.prepareCall("{ call XML_FE_CABECERA (?,?) }");
            procedure.setString("PId_Venta", idventa);
            procedure.setString("PId_Botica", id_botica);

            rs = procedure.executeQuery();

            while (rs.next()) {
                listaVtasCabecera.add(
                        new CABECERA(rs.getString("ID_BOTICA"),
                                    rs.getString("ID_VENTA"),
                                    rs.getDate("FECHA_VENTA"),
                                    rs.getString("ID_TIPO_DOCUMENTO_SUNAT"),
                                    rs.getString("SER_NUM"),
                                    rs.getString("NUM_DOC"),
                                    rs.getString("TIPO_DOC_IDENTIDAD"),
                                    rs.getString("NOMCLIENTE"),
                                    rs.getDouble("IGV"),
                                    rs.getDouble("TOTAL"),
                                    rs.getDouble("SUBTOTAL"),
                                    rs.getDouble("C1001"),
                                    rs.getDouble("C1002"),
                                    rs.getDouble("C1003"),
                                    rs.getDouble("C1004"),
                                    rs.getDouble("C2004"),
                                    rs.getDouble("C2005"),
                                    rs.getDouble("C20004"),

                                    rs.getDouble("VER_C1001"),
                                    rs.getDouble("VER_C1002"),
                                    rs.getDouble("VER_C1003"),
                                    rs.getDouble("VER_C1004"),
                                    rs.getDouble("VER_C2004"),
                                    rs.getDouble("VER_C2005"),
                                    rs.getDouble("VER_C20004"),

                                    rs.getString("TOTAL_TEXTO"),
                                    rs.getString("Moneda_Siglas"),
                                    rs.getString("Moneda_Descripcion"),
                                    rs.getString("Empresa"),
                                    rs.getString("RUC"),
                                    rs.getString("Direccion"),
                                    rs.getString("Ubigeo"),
                                    rs.getString("Departamento"),
                                    rs.getString("Provincia"),
                                    rs.getString("Distrito"),
                                    rs.getString("Pais"),
                                    rs.getDate("FECHA_GENERACION")));
            }

            double MYTOTAL = listaVtasCabecera.get(0).getTotal();
            String money = listaVtasCabecera.get(0).getMonedaDescripcion();
            String TOTALTEXTO = ATEXTO.Convertir(String.valueOf(MYTOTAL).toString(),true,money);


            procedure1 = conex.prepareCall("SELECT * FROM XML_FE WHERE GRUPO=10 ORDER BY POSICION");
            rs1 = procedure1.executeQuery();

            while (rs1.next()) {

                RecuperaXMLBECABECERA.add(
                        new CABECERA(rs1.getString("GRUPO"),
                                     rs1.getString("POSICION"),
                                     rs1.getString("TABOPEN"),
                                     rs1.getString("CAMPO"),
                                     rs1.getString("DEFECTO"),
                                     rs1.getString("DATO"),
                                     rs1.getString("CIERRE"),
                                     rs1.getString("TABCLOSE"),
                                     rs1.getString("VISUALIZA")
                                     ));
                

                    //String VISUALIZA = rs1.getString("VISUALIZA");
                    int VISUALIZA = Integer.parseInt(rs1.getString("VISUALIZA"));
                    //if(VISUALIZA.equals("1001")){
                    if(VISUALIZA == 1001){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC1001());
                    }
                    if(VISUALIZA == 1002){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC1002());
                    }
                    if(VISUALIZA == 1003){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC1003());
                    }
                    if(VISUALIZA == 1004){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC1004());
                    }
                    if(VISUALIZA == 2004){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC2004());
                    }
                    if(VISUALIZA == 2005){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC2005());
                    }
                    if(VISUALIZA == 20004){
                        VISUALIZA= (int)(listaVtasCabecera.get(0).getVERC20004());
                    }

                    listaXML.add(
                            new CABECERA(rs1.getString("GRUPO"),
                                         rs1.getString("POSICION"),
                                         rs1.getString("TABOPEN"),
                                         rs1.getString("CAMPO"),
                                         rs1.getString("DEFECTO"),
                                         rs1.getString("DATO"),
                                         rs1.getString("CIERRE"),
                                         rs1.getString("TABCLOSE"),
                                         String.valueOf(VISUALIZA)
                                         ));
            }

            /*String IDVTA = listaVtasCabecera.get(0).getId_Venta();
            String IDBOTI = listaVtasCabecera.get(0).getId_Botica();
            String VER_C1001 = objproducto.RetornaDato("VER_C1001",IDVTA,IDBOTI,"1");
            String VER_C1002 = objproducto.RetornaDato("VER_C1002",IDVTA,IDBOTI,"1");
            String VER_C1003 = objproducto.RetornaDato("VER_C1003",IDVTA,IDBOTI,"1");
            String VER_C1004 = objproducto.RetornaDato("VER_C1004",IDVTA,IDBOTI,"1");
            String VER_C2004 = objproducto.RetornaDato("VER_C2004",IDVTA,IDBOTI,"1");
            String VER_C2005 = objproducto.RetornaDato("VER_C2005",IDVTA,IDBOTI,"1");
            String VER_C20004 = objproducto.RetornaDato("VER_C20004",IDVTA,IDBOTI,"1");

            while (rs1.next()) {
                String VISUALIZA = rs1.getString("VISUALIZA");
                if(VISUALIZA.equals("1001")){
                    VISUALIZA=VER_C1001;
                }
                if(VISUALIZA.equals("1002")){
                    VISUALIZA=VER_C1002;
                }
                if(VISUALIZA.equals("1003")){
                    VISUALIZA=VER_C1003;
                }
                if(VISUALIZA.equals("1004")){
                    VISUALIZA=VER_C1004;
                }
                if(VISUALIZA.equals("2004")){
                    VISUALIZA=VER_C2004;
                }
                if(VISUALIZA.equals("2005")){
                    VISUALIZA=VER_C2005;
                }
                if(VISUALIZA.equals("20004")){
                    VISUALIZA=VER_C20004;
                }

                listaXML.add(
                        new CABECERA(rs1.getString("GRUPO"),
                                     rs1.getString("POSICION"),
                                     rs1.getString("TABOPEN"),
                                     rs1.getString("CAMPO"),
                                     rs1.getString("DEFECTO"),
                                     rs1.getString("DATO"),
                                     rs1.getString("CIERRE"),
                                     rs1.getString("TABCLOSE"),
                                     VISUALIZA
                                     ));
            }*/

           /* for (int i = 0; i < listaXML.size(); i++)
            {
                if (listaXML.get(i).getVISUALIZA().toString().equals("1"))
                {
                    String validadorOpen =listaXML.get(i).getTABOPEN();
                    if (validadorOpen == null){
                        validadorOpen = "";
                    }

                    Cadena = Cadena + validadorOpen;

                    String validadorCampo =listaXML.get(i).getCAMPO();
                    String validadorDefecto = listaXML.get(i).getDEFECTO();
                    String validarClose = listaXML.get(i).getTABCLOSE();
                    String RecuperaCampoCabecera="";

                    if (validadorCampo == null){
                        validadorCampo = "";
                    }
                    if (validadorDefecto == null){
                        validadorDefecto = "";
                    }
                    if (validarClose == null){
                        validarClose = "";
                    }

                    if (!validadorCampo.equals(""))
                    {
                        if (listaXML.get(i).getCAMPO().toString().equals("C1001")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC1001());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("C1002")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC1002());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("C1003")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC1003());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("C1004")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC1004());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("C2004")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC2004());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("C2005")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC2005());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("TOTAL_TEXTO")){
                            RecuperaCampoCabecera = TOTALTEXTO;
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("SER_NUM")){
                            RecuperaCampoCabecera = listaVtasCabecera.get(0).getSerie();
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("FECHA_VENTA")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getFecha_Venta());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("ID_TIPO_DOCUMENTO_SUNAT")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getID_TIPO_DOCUMENTO_SUNAT());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("MONEDA_SIGLAS")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getMonedaSiglas());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals( "RUC")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getRUC());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("EMPRESA")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getEmpresa());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("UBIGEO")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getUbigeo());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("DIRECCION")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getDireccion());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("DEPARTAMENTO")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getDepartamento());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("PROVINCIA")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getProvincia());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("DISTRITO")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getDistrito());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("PAIS")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getPais());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("NUM_DOC")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getNumero());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("TIPO_DOC_IDENTIDAD")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getTIPO_DOC_IDENTIDAD());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("NOMCLIENTE")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getNomCliente());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("IGV")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getIGV());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals( "C20004")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getC20004());
                        }
                        if (listaXML.get(i).getCAMPO().toString().equals("TOTAL")){
                            RecuperaCampoCabecera = String.valueOf(listaVtasCabecera.get(0).getTotal());
                        }
                        //String CampoCabecera = objproducto.RetornaDatoCabecera(listaXML.get(i).getCAMPO().toString(),IDVTA,IDBOTI,TOTALTEXTO);
                        String CampoCabecera =RecuperaCampoCabecera;
                        Cadena = Cadena + CampoCabecera;
                    }
                    else
                        if (!validadorDefecto.equals(""))
                    {
                        Cadena = Cadena + listaXML.get(i).getDEFECTO().toString();
                    }

                    Cadena = Cadena + validarClose;
                    Cadena = Cadena + "\n";
                }

            }

            /* DETALLE VENTA */
            /*****************/
/*
            procedure2 = conex.prepareCall("{ call XML_FE_DETALLE (?,?) }");
            procedure2.setString("PID_VENTA", idventa);
            procedure2.setString("PId_Botica", id_botica);

            rs2 = procedure2.executeQuery();

            while (rs2.next()) {
                listaVtasDetalle.add(
                        new CABECERA(rs2.getString("ID_BOTICA"),
                                    rs2.getString("ID_VENTA"),
                                    rs2.getString("ESGRATUITA"),
                                    rs2.getString("POSICION"),
                                    rs2.getString("FRAC_NUM"),
                                    rs2.getDouble("OP_GRAVADA"),
                                    rs2.getDouble("OP_INAFECTA"),
                                    rs2.getDouble("OP_EXONERADA"),
                                    rs2.getDouble("OP_GRATUITA"),
                                    rs2.getString("VER_C1004"),
                                    rs2.getDouble("OP_DESCUENTO"),
                                    rs2.getString("VER_C6000"),
                                    rs2.getDouble("TOTAL"),
                                    rs2.getDouble("IGV"),
                                    rs2.getDouble("LineExtionsionAmount"),
                                    rs2.getDouble("PRECIO_UNITARIO"),
                                    rs2.getDouble("PORCENTAJE_IGV"),
                                    rs2.getString("PRODUCTO"),
                                    rs2.getDouble("TOTAL_UNITARIO_SINIGV"),
                                    rs2.getDouble("TaxExemptionReasonCode"),
                                    rs2.getString("Id_Producto")));
            }


            procedure3 = conex.prepareCall("SELECT * FROM XML_FE WHERE GRUPO=70 ORDER BY POSICION");
            rs3 = procedure3.executeQuery();

            while (rs3.next()) {

                RecuperaXMLBEDETALLE.add(
                        new CABECERA(rs3.getString("GRUPO"),
                                     rs3.getString("POSICION"),
                                     rs3.getString("TABOPEN"),
                                     rs3.getString("CAMPO"),
                                     rs3.getString("DEFECTO"),
                                     rs3.getString("DATO"),
                                     rs3.getString("CIERRE"),
                                     rs3.getString("TABCLOSE"),
                                     rs3.getString("VISUALIZA")
                                     ));


                    String VISUALIZA1 = rs3.getString("VISUALIZA");
                    String VER_10004="";

                    if(VISUALIZA1.equals("6000")){
                        VISUALIZA1= String.valueOf(listaVtasDetalle.get(0).getVER_C6000());
                    }
                    if(VISUALIZA1.equals("1004")){
                        VISUALIZA1= String.valueOf(listaVtasDetalle.get(0).getVER_C1004());
                    }
                    if(listaVtasDetalle.get(0).getESGRATUITA().equals("0")){
                        VER_10004 = "1";
                    }
                    if(VISUALIZA1.equals("10004")){
                        VISUALIZA1= VER_10004;
                    }

                    listaXMLDetalle.add(
                        new CABECERA(rs3.getString("GRUPO"),
                                     rs3.getString("POSICION"),
                                     rs3.getString("TABOPEN"),
                                     rs3.getString("CAMPO"),
                                     rs3.getString("DEFECTO"),
                                     rs3.getString("DATO"),
                                     rs3.getString("CIERRE"),
                                     rs3.getString("TABCLOSE"),
                                     VISUALIZA1));

            }

                /*String VER_C6000 = objproducto.RetornaDato("VER_C6000",IDVTA,IDBOTI,"2");
                String VER_C1004_1 = objproducto.RetornaDato("VER_C1004",IDVTA,IDBOTI,"2");
                String VER_10004 = listaVtasDetalle.get(0).getESGRATUITA().toString();
                if (VER_10004.equals("0")){
                    VER_10004 = "1";
                }

            while (rs3.next()) {

                String VISUALIZA1 = rs3.getString("VISUALIZA");
                if(VISUALIZA1.equals("6000")){
                    VISUALIZA1=VER_C6000;
                }
                if(VISUALIZA1.equals("1004")){
                    VISUALIZA1=VER_C1004_1;
                }
                if(VISUALIZA1.equals("10004")){
                    VISUALIZA1=VER_10004;
                }
                listaXMLDetalle.add(
                        new CABECERA(rs3.getString("GRUPO"),
                                     rs3.getString("POSICION"),
                                     rs3.getString("TABOPEN"),
                                     rs3.getString("CAMPO"),
                                     rs3.getString("DEFECTO"),
                                     rs3.getString("DATO"),
                                     rs3.getString("CIERRE"),
                                     rs3.getString("TABCLOSE"),
                                     VISUALIZA1));
            }*/
/*
            for (int y = 0; y < listaVtasDetalle.size(); y++)
            {
                for (int i = 0; i < listaXMLDetalle.size(); i++)
                {
                    if (listaXMLDetalle.get(i).getVISUALIZA().toString().equals("1"))
                    {
                        String validadorOpen1 =listaXMLDetalle.get(i).getTABOPEN();
                        if (validadorOpen1 == null){
                            validadorOpen1 = "";
                        }
                        Cadena = Cadena + validadorOpen1;

                        String validadorCampo1 =listaXMLDetalle.get(i).getCAMPO();
                        String validadorDefecto1 = listaXMLDetalle.get(i).getDEFECTO();
                        String validarClose1 = listaXMLDetalle.get(i).getTABCLOSE();
                        String CampoDetalle="";

                        if (validadorCampo1 == null){
                            validadorCampo1 = "";
                        }
                        if (validadorDefecto1 == null){
                            validadorDefecto1 = "";
                        }
                        if (validarClose1 == null){
                            validarClose1 = "";
                        }

                        if (!validadorCampo1.equals(""))
                        {
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("POSICION")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getPOSICION());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("FRAC_NUM")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getFRAC_NUM());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("LineExtionsionAmount")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getLineExtionsionAmount());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("PRECIO_UNITARIO")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getPRECIO_UNITARIO());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("OP_DESCUENTO")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getOP_DESCUENTO());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("IGV")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getIGV());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("OP_GRAVADA")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getOP_GRAVADA());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("PORCENTAJE_IGV")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getPORCENTAJE_IGV());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("TaxExemptionReasonCode")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getTaxExemptionReasonCode());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("PRODUCTO")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getPRODUCTO());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals("Id_Producto")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getID_PRODUCTO());
                            }
                            if (listaXMLDetalle.get(i).getCAMPO().toString().equals( "TOTAL_UNITARIO_SINIGV")){
                                CampoDetalle = String.valueOf(listaVtasDetalle.get(y).getTOTAL_UNITARIO_SINIGV());
                            }
                            //String CampoDetalle = objproducto.RetornaDatoDetalle(listaXMLDetalle.get(i).getCAMPO().toString(),IDVTA,IDBOTI);
                            Cadena = Cadena + CampoDetalle;
                        }
                        else
                            if (!validadorDefecto1.equals(""))
                        {
                            Cadena = Cadena + listaXMLDetalle.get(i).getDEFECTO().toString();
                        }

                        Cadena = Cadena + validarClose1;
                        Cadena = Cadena + "\n";
                    }

                }
            }
            Cadena = Cadena + "</Invoice>";
            Cadena = Cadena + "\n";
            Cadena = Cadena.replace("<MONEDA_SIGLAS>", listaVtasCabecera.get(0).getMonedaSiglas());

            rs.close();
            rs1.close();
            rs2.close();
            rs3.close();

         } catch (OutOfMemoryError ex) {
             System.out.println("Error de memoria" + ex.getMessage());
         } catch (Exception ex) {
             System.out.println("Error XML_LOAD_INTERNO" + ex.getMessage());
         } finally {
             if (null != conex) {
                try {
                   conex.close();
                } catch (SQLException ex) {
                }
            }
         }

        return Cadena;

    }

   */

    
}
