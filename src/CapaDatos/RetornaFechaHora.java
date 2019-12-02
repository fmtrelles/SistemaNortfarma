package CapaDatos;

import java.text.ParseException;
import java.util.Date;
import java.sql.*;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Miguel Gomez S.
 */
public class RetornaFechaHora {

    private Date fechaactual;
    private String horaactual;
    private String Generador;
    private ConexionPool db;
    Connection conex;
    ResultSet rs;
    Statement stm;
    String fechaMysql;

    public RetornaFechaHora() {
        //  con=new Conexion();
        db = new ConexionPool();
    }

    public String ConvierteFecha(Date fechaOtroFormato) {

        String nuevafecha = "";

        try {


            if (fechaOtroFormato.toString().length() > 0) {
                Pattern p = Pattern.compile("/");
                Date fecha = fechaOtroFormato;
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String resultado = formato.format(fecha);
                String[] items = p.split(resultado);

                for (int i = items.length - 1; i >= 0; i--) {
                    nuevafecha = nuevafecha + items[i];
                    if (i - 1 >= 0) {
                        nuevafecha = nuevafecha + "-";
                    }
                }
            }


        } catch (Exception ex) {
            System.out.println("Error en Fecha Entrante:" + ex.getMessage());
        }

        return nuevafecha;

    }

    public Integer devolverEntero(String cantidad) {
        Integer unidades = 0;
        Pattern p = Pattern.compile("F");
        String[] items = p.split(cantidad);
        //  System.out.println("Total Emelentos:"+items.length);

        try {
            unidades = Integer.valueOf(items[0].toString());
        } catch (Exception ex) {
            unidades = 0;
        }

        return unidades;
    }

    public Integer devolverFraccion(String cantidad) {

        cantidad = cantidad.toUpperCase();

        Integer fracciones = 0;
        Pattern p = Pattern.compile("F");

        String[] items = p.split(cantidad);
        // System.out.println("Total Emelentos02:"+items.length);
        try {
            if (items.length > 1) {
                fracciones = Integer.valueOf(items[1].toString());
            }
        } catch (Exception ex) {
            fracciones = 0;
        }
        return fracciones;
    }

    public String MysqlToJuliano(String FechaMysql) {

        String nuevafecha = "";

        Pattern p = Pattern.compile("-");
        String[] items = p.split(FechaMysql);

        for (int i = items.length - 1; i >= 0; i--) {
            nuevafecha = nuevafecha + items[i];
            if (i - 1 >= 0) {
                nuevafecha = nuevafecha + "/";
            }
        }

        fechaMysql = nuevafecha;
        return fechaMysql;

    }

    public String MysqlToJuliano1(String FechaMysql) {

        String nuevafecha = "";

        Pattern p = Pattern.compile("-");
        String[] items = p.split(FechaMysql);

        for (int i = items.length - 1; i >= 0; i--) {
            nuevafecha = nuevafecha + items[i];
            if (i - 1 >= 0) {
                nuevafecha = nuevafecha + "/";
            }
        }

        fechaMysql = nuevafecha;
        return fechaMysql;

    }

    public Date ConvierteFecha2(Date fechaOtroFormato) {

        Date valor = null;

        try {

            String nuevito = ConvierteFecha(fechaOtroFormato);
            String primero = nuevito.substring(0, 4);
            String segundo = nuevito.substring(5, 7);
            String tercero = nuevito.substring(8, 10);
            valor = convertStringToDate(Integer.valueOf(tercero), Integer.valueOf(segundo), Integer.valueOf(primero));

        } catch (ParseException ex) {
            Logger.getLogger(RetornaFechaHora.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;

    }

    public Date convertStringToDate(int date, int month, int year) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String in = date + "/" + month + "/" + year;
        Date theDate = dateFormat.parse(in);
        return theDate;
    }

    /**
     * @param args the command line arguments
     */
    public Date RetornaFecha() {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_FECHA() }");
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            fechaactual = rs.getDate(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return fechaactual;
    }

    public Date RetornaFecha1(int mes, int anio) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_FECHA_MAXIMA(?,?) }");
            procedure.setInt("MES", mes);
            procedure.setInt("ANIO", anio);
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            fechaactual = rs.getDate(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return fechaactual;
    }

    public Date Retorna_Fecha_Cuadre(String IDBOTICA, int IDCAJA) {

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call FECHA_CUADRE(?,?) }");
            procedure.setString("IDBOTICA", IDBOTICA);
            procedure.setInt("IDCAJA", IDCAJA);
            rs = procedure.executeQuery();
            rs.next();
            fechaactual = rs.getDate(1);
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return fechaactual;
    }

    public String DevuelveMes(String ElMEs) {
        String NombreMes = "";
        Integer MesReal;

        MesReal = Integer.valueOf(ElMEs);

        switch (MesReal) {
            case 1:
                NombreMes = "Enero";
                break;
            case 2:
                NombreMes = "Febrero";
                break;
            case 3:
                NombreMes = "Marzo";
                break;
            case 4:
                NombreMes = "Abril";
                break;

            case 5:
                NombreMes = "Mayo";
                break;

            case 6:
                NombreMes = "Junio";
                break;

            case 7:
                NombreMes = "Julio";
                break;

            case 8:
                NombreMes = "Agosto";
                break;

            case 9:
                NombreMes = "Septiembre";
                break;

            case 10:
                NombreMes = "Octubre";
                break;

            case 11:
                NombreMes = "Noviembre";
                break;

            case 12:
                NombreMes = "Diciembre";
                break;
        }



        return NombreMes;
    }

    public String RetornaHora() {
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_HORA }");
            procedure.execute();
            conex.commit();
            rs = procedure.getResultSet();

            rs.next();
            horaactual = rs.getString(1);

            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();

            } catch (SQLException ex1) {
                System.out.println("Error en la CAPADATOS CLASE RetornaFechaHora :" + ex.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return horaactual;
    }

    public String RetornaIdentificacion() {
        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_IDENTIFICACION }");
            procedure.execute();
            conex.commit();
            rs = procedure.getResultSet();

            rs.next();
            Generador = rs.getString(1);

            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();

            } catch (SQLException ex1) {
                System.out.println("Error en la CAPADATOS CLASE RETORNA_IDENTIFICACION :" + ex.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return Generador;
    }

    public int VerificaFecha1(String idbotica) {
        int resul = -1;
        //SI ES 0 FECHA CORRECTA

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_FECHA (?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();
            rs.next();
            resul = rs.getInt(1);
            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error en la CAPADATOS METODO VerificaFecha :" + ex1.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;

    }

    public boolean VerificaFecha(String idbotica) {
        boolean fechaCorrec = false;
        int valor = 0;

        //SI ES 0 FECHA CORRECTA

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call VERIFICA_FECHA (?) }");
            procedure.setString("IDBOTICA", idbotica);
            rs = procedure.executeQuery();
            rs.next();
            int resul = rs.getInt(1);

            if (resul == 0) {
                fechaCorrec = true;
            }

            rs.close();

        } catch (SQLException ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error en la CAPADATOS METODO VerificaFecha :" + ex1.toString());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return fechaCorrec;


    }
}
