/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.*;
import javax.sql.*;
import org.apache.commons.dbcp.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import sistemanortfarma.VerificaSistema;

/**
 *
 * @author Miguel Gomez S. gomez
 */
public class ConexionPool {

    private static DataSource dataSource = null;
    private static DataSource dataSourceIP = null;
    private static DataSource dataSourceMaster = null;
    private DataSource dataSourceOficina = null;
    BasicDataSource basicDataSource;
    ConfiguracionData objCD;
    public String servidor = "";
    public String servidorMaster = "";
    private String usuario;
    private String clave;
    private String bd = "";
    VerificaSistema obj;
    Connection connection = null;

    public Connection getConnection() {
        try {
            if (null == dataSource) {
                dataSource = this.getDataSource();
            }
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            System.out.println("01.CLASE POOL DE CONEXION: " + ex.getMessage());
        }

        return connection;

    }

    public Connection getConnectionOficina() {
        try {
            if (null == dataSourceOficina) {
                dataSourceOficina = this.getDataSourceOficina();
            }            
            connection = dataSourceOficina.getConnection();
        } catch (SQLException ex) {
            System.out.println("01.CLASE POOL DE CONEXION OFICINA: " + ex.getMessage());
        }

        return connection;

    }

    public DataSource getDataSource() {
        leerArchivoServidor();
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://" + servidor + ":3306/" + bd + "");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(clave);
        dataSource = basicDataSource;
        return dataSource;

    }

    public void leerArchivoServidor() {

        String aplicativo = "";
        String sistema = "Windows";

        String archivo;

        if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
            aplicativo = "./servidorBotica.txt";
        } else {
            aplicativo = "/volsys/sistema/servidorBotica.txt";
        }

        try {

            File f = new File(aplicativo.toString());
            BufferedReader entrada;

            entrada = new BufferedReader(new FileReader(aplicativo));
            String linea;
            int posi = 0;


            while (entrada.ready()) {

                linea = "";
                linea = entrada.readLine();

                if (posi == 0) {
                    bd = linea.toString();
                } else {

                    if (posi == 1) {
                        servidor = linea.toString();
                    } else {
                        if (posi == 2) {
                            usuario = linea.toString();
                        } else {
                            if (posi == 3) {
                                clave = linea.toString();
                            }

                        }
                    }
                }

                posi++;

            }//Compranado el servidor

        } catch (IOException e) {
            System.out.println("Error Apertura" + e.getMessage());
        }

    }

    public String leerArchivoMaster() {


        String aplicativo = "";
        aplicativo = System.getProperty("user.dir");


        try {

            //File f = new File(this.getClass().getResource("/servidorMaster.txt").toString());
            File f = new File("/root/Desktop/servidorMaster.txt");
            BufferedReader entrada;
            entrada = new BufferedReader(new FileReader(f));
            String linea;
            while (entrada.ready()) {
                linea = entrada.readLine();

                if (servidorMaster.length() < 1) {
                    servidorMaster = linea.toString();
                }

            }

        } catch (IOException e) {
            System.out.println("Error Apertura" + e.getMessage());
        }

        return servidorMaster;

    }

    public Connection Conexion2(String IP) {
        Connection connectionIP = null;

        try {
            dataSourceIP = this.getDataSource2(IP);
            System.out.println("IP:" + IP);
            connectionIP = dataSourceIP.getConnection();
        } catch (SQLException ex) {
            System.out.println("02. CLASE POOL DE CONEXION: " + ex.getMessage());
        }

        return connectionIP;

    }

    public DataSource getDataSource2(String IP) {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://" + IP + ":3306/F68_142014");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("ADMIN");
        basicDataSource.setPassword("112358");
        dataSourceIP = basicDataSource;
        return dataSourceIP;
    }

    public Connection Conexion3() {
        Connection connectionIP = null;

        try {

            if (null == dataSourceIP) {
                dataSourceIP = this.getDataSource3();
            }

            connectionIP = dataSourceIP.getConnection();

        } catch (SQLException ex) {
            System.out.println("03. CLASE POOL DE CONEXION: " + ex.getMessage());
        }

        return connectionIP;

    }

    public DataSource getDataSource3() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://10.10.1.38:3306/CORREO");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("ADMIN");
        basicDataSource.setPassword("112358");
        dataSource = basicDataSource;
        return dataSource;

    }

    public DataSource getDataSourceOficina() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://190.40.116.105:3306/SISBOTI");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("ADMIN");
        basicDataSource.setPassword("112358");
        dataSourceOficina = basicDataSource;
        return dataSourceOficina;

    }

    public Connection ConexionMaster() {
        Connection connectionIP = null;

        try {

            if (null == this.dataSourceMaster) {
                dataSourceMaster = this.getDataSourceMaster();
            }

            connectionIP = dataSourceIP.getConnection();

        } catch (SQLException ex) {
            System.out.println("04. CLASE POOL DE CONEXION: " + ex.getMessage());
        }

        return connectionIP;

    }

    public DataSource getDataSourceMaster() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://" + leerArchivoMaster() + ":3306/CORREO");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("ADMIN");
        basicDataSource.setPassword("112358");
        dataSource = basicDataSource;
        return dataSource;

    }

    public ConexionPool() {
    }

    public Connection RetornaConexionReporte() {
        Connection connec = null;

        try {
            if (null == dataSource) {
                dataSource = this.getDataSource();
            }
            connec = dataSource.getConnection();

        } catch (SQLException ex) {
            System.out.println("05. CLASE POOL DE CONEXION: " + ex.getMessage());
        }

        return connec;

    }

}
