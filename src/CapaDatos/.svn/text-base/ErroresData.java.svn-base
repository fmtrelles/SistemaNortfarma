/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import sistemanortfarma.VerificaSistema;

/**
 *
 * @author Miguel Gomez S.
 */
public class ErroresData {

    VerificaSistema obj;
    String servidor = "";
    String rutita = "";

    public String leerArchivoServidor() {

        String aplicativo = "";
        String sistema = "Windows";

        String archivo;

        if (obj.getsSistemaOperativo().indexOf(sistema) != -1) {
            aplicativo = "./servidorBotica.txt";
            rutita = "./";
        } else {
            aplicativo = "/volsys/sistema/servidorBotica.txt";
            rutita = "/volsys/sistema/";
        }

        try {

            File f = new File(aplicativo.toString());
            BufferedReader entrada;

            entrada = new BufferedReader(new FileReader(aplicativo));
            String linea;
            while (entrada.ready()) {
                linea = entrada.readLine();

                if (servidor.length() < 1) {
                    servidor = linea.toString();
                }

            }//Compranado el servidor

        } catch (IOException e) {
            System.out.println("Error Apertura" + e.getMessage());
        }
        return servidor;
    }

    public void escribirLogErrores(String Mensaje) throws IOException {
        String sFichero = servidor;
        Date fechaAhora = new Date();
        sFichero = rutita + "Errores.txt";

        File fichero = new File(sFichero);

        if (fichero.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            bw.write("ASMINISTRADOR" + fechaAhora + "---" + Mensaje);
        }

    }
}
