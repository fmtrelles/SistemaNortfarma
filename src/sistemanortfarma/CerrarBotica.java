package sistemanortfarma;

import CapaDatos.ConexionPool;
import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaCajas;
import CapaLogica.LogicaConfiguracion;
import CapaLogica.LogicaFechaHora;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import java.text.DateFormat;
import entidad.Cajas;
import entidad.Surf01;
import entidad.Surf02;
import entidad.Surf10;
import entidad.Surf31;
import entidad.Surf32;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import jxl.write.DateTime;

/**
 *
 * @author miguel
 */
public class CerrarBotica extends javax.swing.JDialog {

    MuestraVentana objetoventana = new MuestraVentana();
    LogicaConfiguracion objConfig = new LogicaConfiguracion();
    AplicacionVentas objventas;
    private String idbotica;
    LogicaFechaHora logfecha = new LogicaFechaHora();
    LogicaCajas logcajas = new LogicaCajas();
    LogicaBoticas logbotica = new LogicaBoticas();
    Object[] mis_cajas = new Object[5];
    private DefaultTableModel Tabla_Cajas;
    boolean ok = true;
    boolean backup = true;
    String ruta = "";
    String carpeta = null;

    static StringBuffer sb = new StringBuffer();
    int i;
    static PreparedStatement estado;
    Connection conex;
    private ConexionPool db;
    

    public CerrarBotica(java.awt.Frame parent, AplicacionVentas obj) {
        super(parent, true);
        initComponents();
        objventas = obj;
        idbotica = objventas.getIdbotica();
        Tabla_Cajas = (DefaultTableModel) jTable1.getModel();
        CargarData();
        MuestraCajas();
        this.setLocationRelativeTo(null);
        OcultaLabel(false);
        Connection conex;
        db = new ConexionPool();

    }

    private void CargarData() {
        this.jLabel9.setText("");
        this.jLabel5.setText("Botica Felicidad " + objventas.getIdbotica());
        this.jLabel6.setText(objventas.getUsuario());
        this.jLabel8.setText(logfecha.RetornaHora());
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel10.setVisible(valor);
        this.jLabel11.setVisible(valor);
    }

    private void MuestraCajas() {
        List<Cajas> doc_cajas = logcajas.Lista_Cajas_Cierre(idbotica, logfecha.RetornaFecha().toString());


        for (int i = 0; i < doc_cajas.size(); i++) {
            mis_cajas[0] = doc_cajas.get(i).getMiFecha();
            mis_cajas[1] = doc_cajas.get(i).getCaja();
            mis_cajas[2] = doc_cajas.get(i).getResponsable();
            mis_cajas[3] = doc_cajas.get(i).getTurno();
            mis_cajas[4] = doc_cajas.get(i).getApertura();
            Tabla_Cajas.addRow(mis_cajas);
            ok = false;
        }

        if (!ok) {
            jButton1.setEnabled(false);
            this.jLabel9.setText("Porfavor Revisar que las Cajas esten Correctamente Cerradas");
        } else {
            jButton1.setEnabled(true);
            jLabel2.setText("");
            this.jLabel9.setText("Cajas Cerradas Correctamente");
        }
    }

    private void Realiza_DBF() {

        surf01 hilo1 = new surf01(logfecha, logbotica, idbotica);
        surf02 hilo2 = new surf02(logfecha, logbotica, idbotica);
        surf31 hilo3 = new surf31(logfecha, logbotica, idbotica);
        MiSurf32 hilo4 = new MiSurf32(logfecha, logbotica, idbotica);
        MiSurf10 hilo5 = new MiSurf10(logfecha, logbotica, idbotica);
        ruta = hilo5.retornaRuta();

        try {

            System.out.println("BACKUP REALIZDO CON EXITO");
            SimpleZip zip = new SimpleZip();
            zip.makeZip(ruta);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(CerrarBotica.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFont(resourceMap.getFont("Form.font")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel9.foreground")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CAJA", "RESPONSABLE", "TURNO", "APERTURA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(65);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel10.setIcon(resourceMap.getIcon("jLabel10.icon")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setForeground(resourceMap.getColor("jLabel11.foreground")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(283, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(208, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)))
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(231, 231, 231))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        objventas.marcacdo = false;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int reply = JOptionPane.showConfirmDialog(null, " Seguro de Realizar Cierre de Botica  ", "NORTFARMA", JOptionPane.YES_NO_OPTION);

        try {
            if (reply == JOptionPane.YES_OPTION) {
                ActualizarPrecios();

                OcultaLabel(true);
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
                objConfig.GenerarBackup(idbotica);

                if (objConfig.Verifica_Realiza_DBF() == 1) {
                    Realiza_DBF();
                }
                System.exit(0);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void ActualizarPrecios(){

         try {

            String s = new String();
            conex = new ConexionPool().getConnection();

            FileReader lector = new FileReader(new File("/datos/envio/SQLFINAL.SQL"));
            //FileReader lector = new FileReader(new File("c:\\AENVIO\\SQLFINAL.SQL"));
            //ruta exacta del archivo sql//
            BufferedReader buffer = new BufferedReader(lector);
            while ((s = buffer.readLine()) != null) {
                sb.append(s);
            }

            String[] inst = sb.toString().split(";");

            for (i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    estado = conex.prepareStatement(inst[i]);
                    estado.executeUpdate();

                    System.out.println("Ejecutando Consulta \t" + i);
                }
            }
            Date fecha = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            boolean resul = logbotica.GuardaBitacora(idbotica,formateador.format(fecha),"CIERREBOTICA");

            conex.close();
           String comando[]={"/bin/sh","-c","mv /datos/envio/SQLFINAL.SQL /datos/envio/guardados/"+formateador.format(fecha)+".SQL"};
           String comandoBorar[]={"/bin/sh","-c","rm -rf /datos/envio/*.SQL"};
            //String comando[]={"copy c:\\AENVIO\\SQLFINAL.SQL c:\\AENVIO\\guardados\\"+formateador.format(fecha)};

           Process P = Runtime.getRuntime().exec(comando);
           P.waitFor();
           Process Q = Runtime.getRuntime().exec(comandoBorar);
           Q.waitFor();
           //Runtime.getRuntime().exec("rm -rf ");
            //return "exito";

        } catch (SQLException ex) {

            //conex.close();

            /*lector.log_errores("Error En Consulta Numero: "+i+"\nPosible:"+ex + "\n\n");
            lector.enviar_email();
            lector.borrar_file();

            lector.enviar_email();
            return "error";*/
        } catch (Exception exe) {
            /*lector.log_errores("Archivo de Actualizaciones no encontrado,Se subio el script al servidor?");
            lector.enviar_email();
            return "error";*/
        }
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
        objventas.marcacdo = false;
    }//GEN-LAST:event_formWindowClosed

    private class MuestraVentana extends JFrame {

        public MuestraVentana() {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

class surf01 {

    LogicaFechaHora logfecha;
    LogicaBoticas logbotica;
    String idbotica;
    String ruta = "";
    String carpeta = null;
    boolean backup;

    public surf01(LogicaFechaHora logfec, LogicaBoticas logboti, String idboti) {
        logfecha = logfec;
        logbotica = logboti;
        idbotica = idboti;
        Genera_Surf01();
    }

    private void Genera_Surf01() {
        FileOutputStream fos = null;


        try {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            int mes = Integer.parseInt(dateFormat.format(logfecha.RetornaFecha()));
            List<Surf01> Lista_Surf01 = logbotica.Generera_Surf01(idbotica, mes);

            /*
             *
             * Character	C	java.lang.String
            Numeric	N	java.lang.Double
            Double	F	lava.lang.Double
            Logical	L	java.lang.Boolean
            Date	D	java.util.Date
             */

            DBFField[] fields = Structura_Surf01();
            DBFWriter writerSurf01 = new DBFWriter();
            writerSurf01.setFields(fields);
            Object rowData[] = new Object[35];

            for (Surf01 entidadSurf01 : Lista_Surf01) {
                rowData = new Object[35];
                rowData[0] = entidadSurf01.getInvnum();
                rowData[1] = logfecha.ConvierteFecha2(entidadSurf01.getInvfec());
                rowData[2] = entidadSurf01.getCuscod();
                rowData[3] = entidadSurf01.getCusnam();
                rowData[4] = entidadSurf01.getCusruc();
                rowData[5] = "";
                rowData[6] = "";
                rowData[7] = entidadSurf01.getInvtot();
                rowData[8] = "";
                rowData[9] = entidadSurf01.getTyppag();
                rowData[10] = "";
                rowData[11] = entidadSurf01.getUSECOD();
                rowData[12] = entidadSurf01.getUSECAJ();
                rowData[13] = entidadSurf01.getINVSTA();
                rowData[14] = entidadSurf01.getINVRUC();
                rowData[15] = entidadSurf01.getTYPDOC();
                rowData[16] = entidadSurf01.getINVTIM();
                rowData[17] = entidadSurf01.getINVIGV();
                rowData[18] = "";
                rowData[19] = "";
                rowData[20] = "";
                rowData[21] = "";
                rowData[22] = entidadSurf01.getCUSCAT();
                rowData[23] = entidadSurf01.getINVREP();
                rowData[24] = entidadSurf01.getINVVTA();
                rowData[25] = entidadSurf01.getSTAATE();
                rowData[26] = "";
                rowData[27] = "";
                rowData[28] = entidadSurf01.getSerie();
                rowData[29] = entidadSurf01.getNumero();
                rowData[30] = "";
                rowData[31] = entidadSurf01.getAdministra();
                rowData[32] = entidadSurf01.getUSEDNI();
                rowData[33] = "";
                rowData[34] = entidadSurf01.getDnicaja();

                writerSurf01.addRecord(rowData);
            }


            try {

                carpeta = this.idbotica + logfecha.RetornaFecha();
                ruta = "/volsys/backup/" + carpeta + "/";

                File Ffichero = new File(ruta);
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }
                fos = new FileOutputStream(ruta + "Surf01.DBF");

            } catch (FileNotFoundException ex) {
                try {
                    ruta = "C:\\BACKUP\\" + carpeta + "\\";
                    File Ffichero = new File(ruta);
                    if (!Ffichero.exists()) {
                        Ffichero.mkdir();
                    }

                    fos = new FileOutputStream(ruta + "Surf01.DBF");
                } catch (FileNotFoundException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }

            writerSurf01.write(fos);
            fos.close();

        } catch (IOException ex) {
            backup = false;
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            backup = false;
            System.out.println(ex.getMessage());
        }


    }

    private DBFField[] Structura_Surf01() {

        DBFField[] fields = null;

        try {


            fields = new DBFField[35];

            fields[0] = new DBFField();
            fields[0].setName("INVNUM");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(6);

            fields[1] = new DBFField();
            fields[1].setName("Invfec");
            fields[1].setDataType(DBFField.FIELD_TYPE_D);

            fields[2] = new DBFField();
            fields[2].setName("cuscod");
            fields[2].setDataType(DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(8);

            fields[3] = new DBFField();
            fields[3].setName("cusnam");
            fields[3].setDataType(DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(70);

            fields[4] = new DBFField();
            fields[4].setName("cusruc");
            fields[4].setDataType(DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(11);

            fields[5] = new DBFField();
            fields[5].setName("cusadr");
            fields[5].setDataType(DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(45);

            fields[6] = new DBFField();
            fields[6].setName("destot");
            fields[6].setDataType(DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(7);


            fields[7] = new DBFField();
            fields[7].setName("invtot");
            fields[7].setDataType(DBFField.FIELD_TYPE_N);
            fields[7].setFieldLength(7);
            fields[7].setDecimalCount(2);

            fields[8] = new DBFField();
            fields[8].setName("destot_n");
            fields[8].setDataType(DBFField.FIELD_TYPE_C);
            fields[8].setFieldLength(7);

            fields[9] = new DBFField();
            fields[9].setName("typpag");
            fields[9].setDataType(DBFField.FIELD_TYPE_C);
            fields[9].setFieldLength(2);

            fields[10] = new DBFField();
            fields[10].setName("numcmp");
            fields[10].setDataType(DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(6);

            fields[11] = new DBFField();
            fields[11].setName("usecod");
            fields[11].setDataType(DBFField.FIELD_TYPE_C);
            fields[11].setFieldLength(3);

            fields[12] = new DBFField();
            fields[12].setName("usecaj");
            fields[12].setDataType(DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(3);

            fields[13] = new DBFField();
            fields[13].setName("invsta");
            fields[13].setDataType(DBFField.FIELD_TYPE_C);
            fields[13].setFieldLength(1);

            fields[14] = new DBFField();
            fields[14].setName("invruc");
            fields[14].setDataType(DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(10);

            fields[15] = new DBFField();
            fields[15].setName("typdoc");
            fields[15].setDataType(DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(1);

            fields[16] = new DBFField();
            fields[16].setName("invtim");
            fields[16].setDataType(DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(5);

            fields[17] = new DBFField();
            fields[17].setName("invigv");
            fields[17].setDataType(DBFField.FIELD_TYPE_N);
            fields[17].setFieldLength(7);
            fields[17].setDecimalCount(2);

            fields[18] = new DBFField();
            fields[18].setName("nroliq");
            fields[18].setDataType(DBFField.FIELD_TYPE_C);
            fields[18].setFieldLength(6);

            fields[19] = new DBFField();
            fields[19].setName("invuti_c");
            fields[19].setDataType(DBFField.FIELD_TYPE_C);
            fields[19].setFieldLength(10);

            fields[20] = new DBFField();
            fields[20].setName("invuti_r");
            fields[20].setDataType(DBFField.FIELD_TYPE_C);
            fields[20].setFieldLength(10);

            fields[21] = new DBFField();
            fields[21].setName("invuti_p");
            fields[21].setDataType(DBFField.FIELD_TYPE_C);
            fields[21].setFieldLength(10);

            fields[22] = new DBFField();
            fields[22].setName("cuscat");
            fields[22].setDataType(DBFField.FIELD_TYPE_C);
            fields[22].setFieldLength(2);

            fields[23] = new DBFField();
            fields[23].setName("invrep");
            fields[23].setDataType(DBFField.FIELD_TYPE_C);
            fields[23].setFieldLength(1);

            fields[24] = new DBFField();
            fields[24].setName("invvta");
            fields[24].setDataType(DBFField.FIELD_TYPE_N);
            fields[24].setFieldLength(10);

            fields[25] = new DBFField();
            fields[25].setName("staate");
            fields[25].setDataType(DBFField.FIELD_TYPE_C);
            fields[25].setFieldLength(1);

            fields[26] = new DBFField();
            fields[26].setName("movnum");
            fields[26].setDataType(DBFField.FIELD_TYPE_C);
            fields[26].setFieldLength(8);

            fields[27] = new DBFField();
            fields[27].setName("invpag");
            fields[27].setDataType(DBFField.FIELD_TYPE_C);
            fields[27].setFieldLength(10);

            fields[28] = new DBFField();
            fields[28].setName("serie");
            fields[28].setDataType(DBFField.FIELD_TYPE_C);
            fields[28].setFieldLength(3);

            fields[29] = new DBFField();
            fields[29].setName("numero");
            fields[29].setDataType(DBFField.FIELD_TYPE_C);
            fields[29].setFieldLength(10);

            fields[30] = new DBFField();
            fields[30].setName("venbotiq");
            fields[30].setDataType(DBFField.FIELD_TYPE_C);
            fields[30].setFieldLength(1);

            fields[31] = new DBFField();
            fields[31].setName("administra");
            fields[31].setDataType(DBFField.FIELD_TYPE_C);
            fields[31].setFieldLength(5);

            fields[32] = new DBFField();
            fields[32].setName("usedni");
            fields[32].setDataType(DBFField.FIELD_TYPE_C);
            fields[32].setFieldLength(8);

            fields[33] = new DBFField();
            fields[33].setName("sercod");
            fields[33].setDataType(DBFField.FIELD_TYPE_C);
            fields[33].setFieldLength(4);

            fields[34] = new DBFField();
            fields[34].setName("dnicaja");
            fields[34].setDataType(DBFField.FIELD_TYPE_C);
            fields[34].setFieldLength(8);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fields;
    }
}

class surf02 {

    LogicaFechaHora logfecha;
    LogicaBoticas logbotica;
    String idbotica;
    String ruta = "";
    String carpeta = null;
    boolean backup;

    public surf02(LogicaFechaHora logfec, LogicaBoticas logboti, String idboti) {
        logfecha = logfec;
        logbotica = logboti;
        idbotica = idboti;
        Genere_Surf02();
    }

    private void Genere_Surf02() {
        FileOutputStream fos = null;

        try {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            int mes = Integer.parseInt(dateFormat.format(logfecha.RetornaFecha()));
            List<Surf02> Lista_Surf02 = logbotica.Generera_Surf02(idbotica, mes);

            DBFField[] fields = Structura_Surf02();
            DBFWriter writerSurf02 = new DBFWriter();
            writerSurf02.setFields(fields);
            Object rowData[] = new Object[22];

            for (Surf02 entidadSurf02 : Lista_Surf02) {
                rowData = new Object[22];
                rowData[0] = entidadSurf02.getInvnum();
                rowData[1] = entidadSurf02.getCodpro();
                rowData[2] = entidadSurf02.getQTYPRO();
                rowData[3] = entidadSurf02.getQTYPRF();
                rowData[4] = entidadSurf02.getPripro();
                rowData[5] = entidadSurf02.getPRISAL();
                rowData[6] = "";
                rowData[7] = "";
                rowData[8] = String.valueOf(entidadSurf02.getSTKALM());
                rowData[9] = String.valueOf(entidadSurf02.getSTKALM_M());
                rowData[10] = String.valueOf(entidadSurf02.getSTKMOS());
                rowData[11] = String.valueOf(entidadSurf02.getSTKMOS_M());
                rowData[12] = String.valueOf(entidadSurf02.getSTKTOD());
                rowData[13] = String.valueOf(entidadSurf02.getSTKTOD_M());
                rowData[14] = "";
                rowData[15] = "";
                rowData[16] = "";
                rowData[17] = "";
                rowData[18] = "";
                rowData[19] = entidadSurf02.getDTOPRO();
                rowData[20] = entidadSurf02.getPORIGV();
                rowData[21] = "";

                writerSurf02.addRecord(rowData);
            }


            try {

                carpeta = this.idbotica + logfecha.RetornaFecha();
                ruta = "/volsys/backup/" + carpeta + "/";

                File Ffichero = new File(ruta);
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }

                fos = new FileOutputStream(ruta + "Surf02.DBF");

            } catch (FileNotFoundException ex) {
                try {
                    ruta = "C:\\BACKUP\\" + carpeta + "\\";
                    File Ffichero = new File(ruta);
                    if (!Ffichero.exists()) {
                        Ffichero.mkdir();
                    }

                    fos = new FileOutputStream(ruta + "Surf02.DBF");
                } catch (FileNotFoundException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }

            writerSurf02.write(fos);
            fos.close();

        } catch (IOException ex) {
            backup = false;
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            backup = false;
            System.out.println(ex.getMessage());
        }

    }

    private DBFField[] Structura_Surf02() {

        DBFField[] fields = null;

        try {


            fields = new DBFField[22];

            fields[0] = new DBFField();
            fields[0].setName("invnum");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(6);

            fields[1] = new DBFField();
            fields[1].setName("codpro");
            fields[1].setDataType(DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(5);

            fields[2] = new DBFField();
            fields[2].setName("qtypro");
            fields[2].setDataType(DBFField.FIELD_TYPE_N);
            fields[2].setFieldLength(9);
            fields[2].setDecimalCount(4);

            fields[3] = new DBFField();
            fields[3].setName("qtyprf");
            fields[3].setDataType(DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(5);

            fields[4] = new DBFField();
            fields[4].setName("pripro");
            fields[4].setDataType(DBFField.FIELD_TYPE_N);
            fields[4].setFieldLength(10);
            fields[4].setDecimalCount(2);

            fields[5] = new DBFField();
            fields[5].setName("prisal");
            fields[5].setDataType(DBFField.FIELD_TYPE_N);
            fields[5].setFieldLength(10);
            fields[5].setDecimalCount(2);

            fields[6] = new DBFField();
            fields[6].setName("costod");
            fields[6].setDataType(DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(10);

            fields[7] = new DBFField();
            fields[7].setName("starec");
            fields[7].setDataType(DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(1);

            fields[8] = new DBFField();
            fields[8].setName("stkalm");
            fields[8].setDataType(DBFField.FIELD_TYPE_C);
            fields[8].setFieldLength(5);

            fields[9] = new DBFField();
            fields[9].setName("stkalm_m");
            fields[9].setDataType(DBFField.FIELD_TYPE_C);
            fields[9].setFieldLength(10);

            fields[10] = new DBFField();
            fields[10].setName("stkmos");
            fields[10].setDataType(DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(10);

            fields[11] = new DBFField();
            fields[11].setName("stkmos_m");
            fields[11].setDataType(DBFField.FIELD_TYPE_C);
            fields[11].setFieldLength(10);

            fields[12] = new DBFField();
            fields[12].setName("stktod");
            fields[12].setDataType(DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(10);

            fields[13] = new DBFField();
            fields[13].setName("stktod_m");
            fields[13].setDataType(DBFField.FIELD_TYPE_C);
            fields[13].setFieldLength(10);

            fields[14] = new DBFField();
            fields[14].setName("stktim");
            fields[14].setDataType(DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(5);

            fields[15] = new DBFField();
            fields[15].setName("costod_r");
            fields[15].setDataType(DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(10);

            fields[16] = new DBFField();
            fields[16].setName("cospro");
            fields[16].setDataType(DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(10);
            fields[16].setDecimalCount(2);

            fields[17] = new DBFField();
            fields[17].setName("codpro_s");
            fields[17].setDataType(DBFField.FIELD_TYPE_C);
            fields[17].setFieldLength(5);

            fields[18] = new DBFField();
            fields[18].setName("dtoadd");
            fields[18].setDataType(DBFField.FIELD_TYPE_C);
            fields[18].setFieldLength(5);


            fields[19] = new DBFField();
            fields[19].setName("dtopro");
            fields[19].setDataType(DBFField.FIELD_TYPE_N);
            fields[19].setFieldLength(10);
            fields[19].setDecimalCount(2);


            fields[20] = new DBFField();
            fields[20].setName("porigv");
            fields[20].setDataType(DBFField.FIELD_TYPE_N);
            fields[20].setFieldLength(10);
            fields[20].setDecimalCount(2);

            fields[21] = new DBFField();
            fields[21].setName("compro");
            fields[21].setDataType(DBFField.FIELD_TYPE_C);
            fields[21].setFieldLength(2);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fields;
    }
}

class surf31 extends Thread {

    LogicaFechaHora logfecha;
    LogicaBoticas logbotica;
    String idbotica;
    String ruta = "";
    String carpeta = null;
    boolean backup;

    public surf31(LogicaFechaHora logfec, LogicaBoticas logboti, String idboti) {
        logfecha = logfec;
        logbotica = logboti;
        idbotica = idboti;
        Genera_Surf31();
    }

    private void Genera_Surf31() {
        FileOutputStream fos = null;

        try {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            int mes = Integer.parseInt(dateFormat.format(logfecha.RetornaFecha()));
            List<Surf31> Lista_Surf31 = logbotica.Generera_Surf31(idbotica, mes);

            DBFField[] fields = Structura_Surf31();
            DBFWriter writerSurf31 = new DBFWriter();
            writerSurf31.setFields(fields);
            Object rowData[] = new Object[25];

            for (Surf31 entidadSurf02 : Lista_Surf31) {
                rowData = new Object[25];
                rowData[0] = entidadSurf02.getCodalm();
                rowData[1] = entidadSurf02.getTypmov();
                rowData[2] = entidadSurf02.getCodprv();
                rowData[3] = entidadSurf02.getDocnum();
                rowData[4] = "";
                rowData[5] = logfecha.ConvierteFecha2(entidadSurf02.getDocdat());
                rowData[6] = "";
                rowData[7] = "";
                rowData[8] = "";
                rowData[9] = entidadSurf02.getDoctot();
                rowData[10] = entidadSurf02.getDocbon();
                rowData[11] = entidadSurf02.getDocobs();
                rowData[12] = entidadSurf02.getUsecod();
                rowData[13] = logfecha.ConvierteFecha2(entidadSurf02.getDatreg());
                rowData[14] = entidadSurf02.getDoctim();
                rowData[15] = "";
                rowData[16] = "";
                rowData[17] = "";
                rowData[18] = "";
                rowData[19] = "";
                rowData[20] = "";
                rowData[21] = "";
                rowData[22] = "";
                rowData[23] = "";
                rowData[24] = entidadSurf02.getAdministra();

                writerSurf31.addRecord(rowData);
            }


            try {

                carpeta = this.idbotica + logfecha.RetornaFecha();
                ruta = "/volsys/backup/" + carpeta + "/";

                File Ffichero = new File(ruta);
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }
                fos = new FileOutputStream(ruta + "Surf031.DBF");

            } catch (FileNotFoundException ex) {
                try {
                    ruta = "C:\\BACKUP\\" + carpeta + "\\";
                    File Ffichero = new File(ruta);
                    if (!Ffichero.exists()) {
                        Ffichero.mkdir();
                    }

                    fos = new FileOutputStream(ruta + "Surf31.DBF");
                } catch (FileNotFoundException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }


            writerSurf31.write(fos);
            fos.close();

        } catch (IOException ex) {
            backup = false;
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            backup = false;
            System.out.println(ex.getMessage());
        }


    }

    private DBFField[] Structura_Surf31() {

        DBFField[] fields = null;

        try {

            fields = new DBFField[25];

            fields[0] = new DBFField();
            fields[0].setName("codalm");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(1);

            fields[1] = new DBFField();
            fields[1].setName("typmov");
            fields[1].setDataType(DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(2);

            fields[2] = new DBFField();
            fields[2].setName("codprv");
            fields[2].setDataType(DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(4);

            fields[3] = new DBFField();
            fields[3].setName("docnum");
            fields[3].setDataType(DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(10);

            fields[4] = new DBFField();
            fields[4].setName("purnum");
            fields[4].setDataType(DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(6);

            fields[5] = new DBFField();
            fields[5].setName("docdat");
            fields[5].setDataType(DBFField.FIELD_TYPE_D);


            fields[6] = new DBFField();
            fields[6].setName("doctyp");
            fields[6].setDataType(DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(2);

            fields[7] = new DBFField();
            fields[7].setName("docdpy");
            fields[7].setDataType(DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(10);


            fields[8] = new DBFField();
            fields[8].setName("docdis");
            fields[8].setDataType(DBFField.FIELD_TYPE_C);
            fields[8].setFieldLength(10);


            fields[9] = new DBFField();
            fields[9].setName("doctot");
            fields[9].setDataType(DBFField.FIELD_TYPE_N);
            fields[9].setFieldLength(10);
            fields[9].setDecimalCount(2);


            fields[10] = new DBFField();
            fields[10].setName("docbon");
            fields[10].setDataType(DBFField.FIELD_TYPE_N);
            fields[10].setFieldLength(10);
            fields[10].setDecimalCount(2);

            fields[11] = new DBFField();
            fields[11].setName("docobs");
            fields[11].setDataType(DBFField.FIELD_TYPE_C);
            fields[11].setFieldLength(70);

            fields[12] = new DBFField();
            fields[12].setName("usecod");
            fields[12].setDataType(DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(3);

            fields[13] = new DBFField();
            fields[13].setName("datreg");
            fields[13].setDataType(DBFField.FIELD_TYPE_D);


            fields[14] = new DBFField();
            fields[14].setName("doctim");
            fields[14].setDataType(DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(5);

            fields[15] = new DBFField();
            fields[15].setName("dtodoc1");
            fields[15].setDataType(DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(5);


            fields[16] = new DBFField();
            fields[16].setName("dtodoc2");
            fields[16].setDataType(DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(5);

            fields[17] = new DBFField();
            fields[17].setName("docsts");
            fields[17].setDataType(DBFField.FIELD_TYPE_C);
            fields[17].setFieldLength(5);

            fields[18] = new DBFField();
            fields[18].setName("docina");
            fields[18].setDataType(DBFField.FIELD_TYPE_C);
            fields[18].setFieldLength(10);

            fields[19] = new DBFField();
            fields[19].setName("docigv");
            fields[19].setDataType(DBFField.FIELD_TYPE_C);
            fields[19].setFieldLength(10);



            fields[20] = new DBFField();
            fields[20].setName("doctvv");
            fields[20].setDataType(DBFField.FIELD_TYPE_C);
            fields[20].setFieldLength(10);


            fields[21] = new DBFField();
            fields[21].setName("docred");
            fields[21].setDataType(DBFField.FIELD_TYPE_C);
            fields[21].setFieldLength(10);


            fields[22] = new DBFField();
            fields[22].setName("mes_halcon");
            fields[22].setDataType(DBFField.FIELD_TYPE_C);
            fields[22].setFieldLength(2);

            fields[23] = new DBFField();
            fields[23].setName("datrecepci");
            fields[23].setDataType(DBFField.FIELD_TYPE_C);

            fields[24] = new DBFField();
            fields[24].setName("administra");
            fields[24].setDataType(DBFField.FIELD_TYPE_C);
            fields[24].setFieldLength(10);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fields;
    }
}

class MiSurf32 extends Thread {

    LogicaFechaHora logfecha;
    LogicaBoticas logbotica;
    String idbotica;
    String ruta = "";
    String carpeta = null;
    boolean backup;

    public MiSurf32(LogicaFechaHora logfec, LogicaBoticas logboti, String idboti) {
        logfecha = logfec;
        logbotica = logboti;
        idbotica = idboti;
        Genera_Surf32();
    }

    private void Genera_Surf32() {

        FileOutputStream fos = null;

        try {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            int mes = Integer.parseInt(dateFormat.format(logfecha.RetornaFecha()));
            List<Surf32> Lista_Surf32 = logbotica.Generera_Surf32(idbotica, mes);

            DBFField[] fields = Structura_Surf32();
            DBFWriter writerSurf32 = new DBFWriter();
            writerSurf32.setFields(fields);
            Object rowData[] = new Object[34];

            for (Surf32 entidadSurf32 : Lista_Surf32) {
                rowData = new Object[34];
                rowData[0] = entidadSurf32.getCodalm();
                rowData[1] = entidadSurf32.getTypmov();
                rowData[2] = entidadSurf32.getCodprv();
                rowData[3] = entidadSurf32.getDocnum();
                rowData[4] = entidadSurf32.getCodpro();
                rowData[5] = "";
                rowData[6] = entidadSurf32.getStksed();
                rowData[7] = entidadSurf32.getStkprf();
                rowData[8] = "";
                rowData[9] = "";
                rowData[10] = "";
                rowData[11] = entidadSurf32.getPrisal();
                rowData[12] = "";
                rowData[13] = "";
                rowData[14] = "";
                rowData[15] = String.valueOf(entidadSurf32.getStkalm());
                rowData[16] = String.valueOf(entidadSurf32.getStkalm_m());
                rowData[17] = String.valueOf(entidadSurf32.getStkmos());
                rowData[18] = String.valueOf(entidadSurf32.getStkmos_m());
                rowData[19] = String.valueOf(entidadSurf32.getStktod());
                rowData[20] = String.valueOf(entidadSurf32.getStktod_m());
                rowData[21] = "";
                rowData[22] = "";
                rowData[23] = entidadSurf32.getStktim();
                rowData[24] = "";
                rowData[25] = "";
                rowData[26] = "";
                rowData[27] = "";
                rowData[28] = "";
                rowData[29] = "";
                rowData[30] = "";
                rowData[31] = "";
                rowData[32] = "";
                rowData[33] = "";

                writerSurf32.addRecord(rowData);

            }


            try {

                carpeta = this.idbotica + logfecha.RetornaFecha();
                ruta = "/volsys/backup/" + carpeta + "/";

                File Ffichero = new File(ruta);
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }
                fos = new FileOutputStream(ruta + "Surf32.DBF");

            } catch (FileNotFoundException ex) {
                try {
                    ruta = "C:\\BACKUP\\" + carpeta + "\\";
                    File Ffichero = new File(ruta);
                    if (!Ffichero.exists()) {
                        Ffichero.mkdir();
                    }

                    fos = new FileOutputStream(ruta + "Surf32.DBF");
                } catch (FileNotFoundException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }

            writerSurf32.write(fos);
            fos.close();

        } catch (IOException ex) {
            backup = false;
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            backup = false;
            System.out.println(ex.getMessage());
        }

    }

    private DBFField[] Structura_Surf32() {

        DBFField[] fields = null;

        try {

            fields = new DBFField[34];

            fields[0] = new DBFField();
            fields[0].setName("codalm");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(1);

            fields[1] = new DBFField();
            fields[1].setName("typmov");
            fields[1].setDataType(DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(2);

            fields[2] = new DBFField();
            fields[2].setName("codprv");
            fields[2].setDataType(DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(4);

            fields[3] = new DBFField();
            fields[3].setName("docnum");
            fields[3].setDataType(DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(10);

            fields[4] = new DBFField();
            fields[4].setName("Codpro");
            fields[4].setDataType(DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(5);


            fields[5] = new DBFField();
            fields[5].setName("qtypro");
            fields[5].setDataType(DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(4);


            fields[6] = new DBFField();
            fields[6].setName("stksed");
            fields[6].setDataType(DBFField.FIELD_TYPE_N);
            fields[6].setFieldLength(9);
            fields[6].setDecimalCount(4);

            fields[7] = new DBFField();
            fields[7].setName("stkprf");
            fields[7].setDataType(DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(4);


            fields[8] = new DBFField();
            fields[8].setName("stkgif");
            fields[8].setDataType(DBFField.FIELD_TYPE_C);
            fields[8].setFieldLength(4);


            fields[9] = new DBFField();
            fields[9].setName("cosini");
            fields[9].setDataType(DBFField.FIELD_TYPE_C);
            fields[9].setFieldLength(10);


            fields[10] = new DBFField();
            fields[10].setName("cosprd");
            fields[10].setDataType(DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(7);

            fields[11] = new DBFField();
            fields[11].setName("prisal");
            fields[11].setDataType(DBFField.FIELD_TYPE_N);
            fields[11].setFieldLength(7);
            fields[11].setDecimalCount(2);

            fields[12] = new DBFField();
            fields[12].setName("stkven");
            fields[12].setDataType(DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(12);


            fields[13] = new DBFField();
            fields[13].setName("starec");
            fields[13].setDataType(DBFField.FIELD_TYPE_C);
            fields[13].setFieldLength(31);


            fields[14] = new DBFField();
            fields[14].setName("stafev");
            fields[14].setDataType(DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(1);

            fields[15] = new DBFField();
            fields[15].setName("stkalm");
            fields[15].setDataType(DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(5);

            fields[16] = new DBFField();
            fields[16].setName("stkalm_m");
            fields[16].setDataType(DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(5);

            fields[17] = new DBFField();
            fields[17].setName("stkmos");
            fields[17].setDataType(DBFField.FIELD_TYPE_C);
            fields[17].setFieldLength(5);

            fields[18] = new DBFField();
            fields[18].setName("stkmos_m");
            fields[18].setDataType(DBFField.FIELD_TYPE_C);
            fields[18].setFieldLength(5);

            fields[19] = new DBFField();
            fields[19].setName("stktod");
            fields[19].setDataType(DBFField.FIELD_TYPE_C);
            fields[19].setFieldLength(5);

            fields[20] = new DBFField();
            fields[20].setName("stktod_m");
            fields[20].setDataType(DBFField.FIELD_TYPE_C);
            fields[20].setFieldLength(5);


            fields[21] = new DBFField();
            fields[21].setName("costod");
            fields[21].setDataType(DBFField.FIELD_TYPE_C);
            fields[21].setFieldLength(7);

            fields[22] = new DBFField();
            fields[22].setName("stkpri");
            fields[22].setDataType(DBFField.FIELD_TYPE_C);
            fields[22].setFieldLength(7);

            fields[23] = new DBFField();
            fields[23].setName("stktim");
            fields[23].setDataType(DBFField.FIELD_TYPE_C);
            fields[23].setFieldLength(5);


            fields[24] = new DBFField();
            fields[24].setName("dtopro");
            fields[24].setDataType(DBFField.FIELD_TYPE_C);
            fields[24].setFieldLength(5);


            fields[25] = new DBFField();
            fields[25].setName("costod_r");
            fields[25].setDataType(DBFField.FIELD_TYPE_C);
            fields[25].setFieldLength(7);


            fields[26] = new DBFField();
            fields[26].setName("cospro");
            fields[26].setDataType(DBFField.FIELD_TYPE_C);
            fields[26].setFieldLength(7);


            fields[27] = new DBFField();
            fields[27].setName("dtopro2");
            fields[27].setDataType(DBFField.FIELD_TYPE_C);
            fields[27].setFieldLength(5);


            fields[28] = new DBFField();
            fields[28].setName("dtopro3");
            fields[28].setDataType(DBFField.FIELD_TYPE_C);
            fields[28].setFieldLength(5);

            fields[29] = new DBFField();
            fields[29].setName("dtopro4");
            fields[29].setDataType(DBFField.FIELD_TYPE_C);
            fields[29].setFieldLength(5);


            fields[30] = new DBFField();
            fields[30].setName("pordcto");
            fields[30].setDataType(DBFField.FIELD_TYPE_C);
            fields[30].setFieldLength(7);

            fields[31] = new DBFField();
            fields[31].setName("totcda");
            fields[31].setDataType(DBFField.FIELD_TYPE_C);
            fields[31].setFieldLength(10);

            fields[32] = new DBFField();
            fields[32].setName("pordctof");
            fields[32].setDataType(DBFField.FIELD_TYPE_C);
            fields[32].setFieldLength(7);

            fields[33] = new DBFField();
            fields[33].setName("lote");
            fields[33].setDataType(DBFField.FIELD_TYPE_C);
            fields[33].setFieldLength(10);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fields;
    }
}

class MiSurf10 {

    LogicaFechaHora logfecha;
    LogicaBoticas logbotica;
    String idbotica;
    String ruta = "";
    String carpeta = null;
    boolean backup;

    public MiSurf10(LogicaFechaHora logfec, LogicaBoticas logboti, String idboti) {
        logfecha = logfec;
        logbotica = logboti;
        idbotica = idboti;
        Genera_Surf10();
    }

    public String retornaRuta() {
        return ruta;
    }

    private void Genera_Surf10() {


        FileOutputStream fos = null;

        try {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            int mes = Integer.parseInt(dateFormat.format(logfecha.RetornaFecha()));
            List<Surf10> Lista_Surf10 = logbotica.Generera_Surf10(idbotica);

            DBFField[] fields = Structura_Surf10();
            DBFWriter writerSurf10 = new DBFWriter();
            writerSurf10.setFields(fields);
            Object rowData[] = new Object[54];

            for (Surf10 entidadSurf10 : Lista_Surf10) {
                rowData = new Object[54];
                rowData[0] = entidadSurf10.getCodpro();
                rowData[1] = entidadSurf10.getDespro();
                rowData[2] = "";
                rowData[3] = entidadSurf10.getCodlab();
                rowData[4] = entidadSurf10.getCodtip();
                rowData[5] = entidadSurf10.getCodfam();
                rowData[6] = entidadSurf10.getCodgen();
                rowData[7] = "";
                rowData[8] = entidadSurf10.getPrisal();
                rowData[9] = "";
                rowData[10] = "";
                rowData[11] = String.valueOf(entidadSurf10.getStktod());
                rowData[12] = String.valueOf(entidadSurf10.getStktod_m());
                rowData[13] = String.valueOf(entidadSurf10.getStkmos());
                rowData[14] = String.valueOf(entidadSurf10.getStkmos_m());
                rowData[15] = String.valueOf(entidadSurf10.getStkalm());
                rowData[16] = String.valueOf(entidadSurf10.getStkalm_m());
                rowData[17] = "";
                rowData[18] = "";
                rowData[19] = "";
                rowData[20] = "";
                rowData[21] = "";
                rowData[22] = "";
                rowData[23] = "";
                rowData[24] = "";
                rowData[25] = "";
                rowData[26] = entidadSurf10.getDtopro();
                rowData[27] = "";
                rowData[28] = "";
                rowData[29] = "";
                rowData[30] = "";
                rowData[21] = "";
                rowData[32] = "";
                rowData[33] = "";
                rowData[34] = "";
                rowData[35] = "";
                rowData[36] = String.valueOf(entidadSurf10.getStkfra());
                rowData[37] = entidadSurf10.getPorigv();
                rowData[38] = "";
                rowData[39] = "";
                rowData[40] = "";
                rowData[41] = "";
                rowData[42] = "";
                rowData[43] = "";
                rowData[44] = "";
                rowData[45] = "";
                rowData[46] = "";
                rowData[47] = "";
                rowData[48] = "";
                rowData[49] = "";
                rowData[50] = "";
                rowData[51] = "";
                rowData[52] = "";
                rowData[53] = "";
                writerSurf10.addRecord(rowData);
            }

            try {

                carpeta = this.idbotica + logfecha.RetornaFecha();
                ruta = "/volsys/backup/" + carpeta + "/";

                File Ffichero = new File(ruta);
                if (!Ffichero.exists()) {
                    Ffichero.mkdir();
                }
                fos = new FileOutputStream(ruta + "Surf10.DBF");

            } catch (FileNotFoundException ex) {
                try {
                    ruta = "C:\\BACKUP\\" + carpeta + "\\";
                    File Ffichero = new File(ruta);
                    if (!Ffichero.exists()) {
                        Ffichero.mkdir();
                    }

                    fos = new FileOutputStream(ruta + "Surf10.DBF");
                } catch (FileNotFoundException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }

            writerSurf10.write(fos);
            fos.close();

        } catch (IOException ex) {
            backup = false;
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            backup = false;
            System.out.println(ex.getMessage());
        }


    }

    private DBFField[] Structura_Surf10() {

        DBFField[] fields = null;

        try {

            fields = new DBFField[54];

            fields[0] = new DBFField();
            fields[0].setName("codpro");
            fields[0].setDataType(DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(5);

            fields[1] = new DBFField();
            fields[1].setName("despro");
            fields[1].setDataType(DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(70);

            fields[2] = new DBFField();
            fields[2].setName("placod");
            fields[2].setDataType(DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(4);

            fields[3] = new DBFField();
            fields[3].setName("codlab");
            fields[3].setDataType(DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(4);

            fields[4] = new DBFField();
            fields[4].setName("codtip");
            fields[4].setDataType(DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(2);

            fields[5] = new DBFField();
            fields[5].setName("codfam");
            fields[5].setDataType(DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(7);

            fields[6] = new DBFField();
            fields[6].setName("codgen");
            fields[6].setDataType(DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(7);

            fields[7] = new DBFField();
            fields[7].setName("stkmin");
            fields[7].setDataType(DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(4);

            fields[8] = new DBFField();
            fields[8].setName("prisal");
            fields[8].setDataType(DBFField.FIELD_TYPE_N);
            fields[8].setFieldLength(7);
            fields[8].setDecimalCount(2);

            fields[9] = new DBFField();
            fields[9].setName("costod");
            fields[9].setDataType(DBFField.FIELD_TYPE_C);
            fields[9].setFieldLength(7);

            fields[10] = new DBFField();
            fields[10].setName("cospro");
            fields[10].setDataType(DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(7);


            fields[11] = new DBFField();
            fields[11].setName("stktod");
            fields[11].setDataType(DBFField.FIELD_TYPE_C);
            fields[11].setFieldLength(10);

            fields[12] = new DBFField();
            fields[12].setName("stktod_m");
            fields[12].setDataType(DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(10);

            fields[13] = new DBFField();
            fields[13].setName("stkmos");
            fields[13].setDataType(DBFField.FIELD_TYPE_C);
            fields[13].setFieldLength(10);

            fields[14] = new DBFField();
            fields[14].setName("stkmos_m");
            fields[14].setDataType(DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(10);

            fields[15] = new DBFField();
            fields[15].setName("stkalm");
            fields[15].setDataType(DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(10);

            fields[16] = new DBFField();
            fields[16].setName("stkalm_m");
            fields[16].setDataType(DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(10);

            fields[17] = new DBFField();
            fields[17].setName("stklda");
            fields[17].setDataType(DBFField.FIELD_TYPE_C);
            fields[17].setFieldLength(10);

            fields[18] = new DBFField();
            fields[18].setName("stkhis");
            fields[18].setDataType(DBFField.FIELD_TYPE_C);
            fields[18].setFieldLength(7);

            fields[19] = new DBFField();
            fields[19].setName("stkhis_m");
            fields[19].setDataType(DBFField.FIELD_TYPE_C);
            fields[19].setFieldLength(7);

            fields[20] = new DBFField();
            fields[20].setName("stkbeg");
            fields[20].setDataType(DBFField.FIELD_TYPE_C);
            fields[20].setFieldLength(7);


            fields[21] = new DBFField();
            fields[21].setName("datmov");
            fields[21].setDataType(DBFField.FIELD_TYPE_C);
            fields[21].setFieldLength(7);

            fields[22] = new DBFField();
            fields[22].setName("datpri");
            fields[22].setDataType(DBFField.FIELD_TYPE_C);
            fields[22].setFieldLength(7);

            fields[23] = new DBFField();
            fields[23].setName("datinc");
            fields[23].setDataType(DBFField.FIELD_TYPE_C);
            fields[23].setFieldLength(7);

            fields[24] = new DBFField();
            fields[24].setName("datven");
            fields[24].setDataType(DBFField.FIELD_TYPE_C);
            fields[24].setFieldLength(7);

            fields[25] = new DBFField();
            fields[25].setName("prosta");
            fields[25].setDataType(DBFField.FIELD_TYPE_C);
            fields[25].setFieldLength(2);

            fields[26] = new DBFField();
            fields[26].setName("dtopro");
            fields[26].setDataType(DBFField.FIELD_TYPE_N);
            fields[26].setFieldLength(6);
            fields[26].setDecimalCount(2);

            fields[27] = new DBFField();
            fields[27].setName("dtodad");
            fields[27].setDataType(DBFField.FIELD_TYPE_C);
            fields[27].setFieldLength(6);

            fields[28] = new DBFField();
            fields[28].setName("dtodah");
            fields[28].setDataType(DBFField.FIELD_TYPE_C);

            fields[29] = new DBFField();
            fields[29].setName("conyea");
            fields[29].setDataType(DBFField.FIELD_TYPE_C);
            fields[29].setFieldLength(6);

            fields[30] = new DBFField();
            fields[30].setName("qtypur");
            fields[30].setDataType(DBFField.FIELD_TYPE_C);
            fields[30].setFieldLength(3);

            fields[31] = new DBFField();
            fields[31].setName("codbar");
            fields[31].setDataType(DBFField.FIELD_TYPE_C);
            fields[31].setFieldLength(16);

            fields[32] = new DBFField();
            fields[32].setName("stkmax");
            fields[32].setDataType(DBFField.FIELD_TYPE_C);
            fields[32].setFieldLength(4);

            fields[33] = new DBFField();
            fields[33].setName("secinv");
            fields[33].setDataType(DBFField.FIELD_TYPE_C);
            fields[33].setFieldLength(4);

            fields[34] = new DBFField();
            fields[34].setName("prisal_r");
            fields[34].setDataType(DBFField.FIELD_TYPE_C);
            fields[34].setFieldLength(7);

            fields[35] = new DBFField();
            fields[35].setName("costod_r");
            fields[35].setDataType(DBFField.FIELD_TYPE_C);
            fields[35].setFieldLength(7);

            fields[36] = new DBFField();
            fields[36].setName("stkfra");
            fields[36].setDataType(DBFField.FIELD_TYPE_C);
            fields[36].setFieldLength(10);

            fields[37] = new DBFField();
            fields[37].setName("porigv");
            fields[37].setDataType(DBFField.FIELD_TYPE_N);
            fields[37].setFieldLength(5);
            fields[37].setDecimalCount(2);

            fields[38] = new DBFField();
            fields[38].setName("poruti");
            fields[38].setDataType(DBFField.FIELD_TYPE_C);
            fields[38].setFieldLength(6);

            fields[39] = new DBFField();
            fields[39].setName("datreg");
            fields[39].setDataType(DBFField.FIELD_TYPE_C);
            fields[39].setFieldLength(30);

            fields[40] = new DBFField();
            fields[40].setName("dosis");
            fields[40].setDataType(DBFField.FIELD_TYPE_C);
            fields[40].setFieldLength(30);

            fields[41] = new DBFField();
            fields[41].setName("docdat");
            fields[41].setDataType(DBFField.FIELD_TYPE_C);
            fields[41].setFieldLength(5);

            fields[42] = new DBFField();
            fields[42].setName("docnum");
            fields[42].setDataType(DBFField.FIELD_TYPE_C);
            fields[42].setFieldLength(10);

            fields[43] = new DBFField();
            fields[43].setName("codprv");
            fields[43].setDataType(DBFField.FIELD_TYPE_C);
            fields[43].setFieldLength(4);

            fields[44] = new DBFField();
            fields[44].setName("stkprf");
            fields[44].setDataType(DBFField.FIELD_TYPE_C);
            fields[44].setFieldLength(4);

            fields[45] = new DBFField();
            fields[45].setName("pordcto");
            fields[45].setDataType(DBFField.FIELD_TYPE_C);
            fields[45].setFieldLength(9);

            fields[46] = new DBFField();
            fields[46].setName("dtopro1");
            fields[46].setDataType(DBFField.FIELD_TYPE_C);
            fields[46].setFieldLength(6);

            fields[47] = new DBFField();
            fields[47].setName("dtopro2");
            fields[47].setDataType(DBFField.FIELD_TYPE_C);
            fields[47].setFieldLength(6);

            fields[48] = new DBFField();
            fields[48].setName("dtopro3");
            fields[48].setDataType(DBFField.FIELD_TYPE_C);
            fields[48].setFieldLength(6);

            fields[49] = new DBFField();
            fields[49].setName("dtopro4");
            fields[49].setDataType(DBFField.FIELD_TYPE_C);
            fields[49].setFieldLength(6);

            fields[50] = new DBFField();
            fields[50].setName("dtoadd");
            fields[50].setDataType(DBFField.FIELD_TYPE_C);
            fields[50].setFieldLength(6);

            fields[51] = new DBFField();
            fields[51].setName("compro");
            fields[51].setDataType(DBFField.FIELD_TYPE_C);
            fields[51].setFieldLength(7);

            fields[52] = new DBFField();
            fields[52].setName("precio");
            fields[52].setDataType(DBFField.FIELD_TYPE_C);
            fields[52].setFieldLength(7);

            fields[53] = new DBFField();
            fields[53].setName("nivel");
            fields[53].setDataType(DBFField.FIELD_TYPE_C);
            fields[53].setFieldLength(1);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return fields;


    }
}

class SimpleZip {

    private static ZipOutputStream zos;

    /**
     * Creates a Zip archive. If the name of the file passed in is a
     * directory, the directory's contents will be made into a Zip file.
     */
    public static void makeZip(String fileName)
            throws IOException, FileNotFoundException {
        try {
            File file = new File(fileName);
            zos = new ZipOutputStream(new FileOutputStream(file + ".zip"));
            //Call recursion.
            recurseFiles(file);
            //We are done adding entries to the zip archive,
            //so close the Zip output stream.
            zos.close();
            fileName = fileName.substring(0, fileName.length() - 1);
            String archivo = fileName + ".zip";
            String comando = "wput " + archivo + " ftp://admin:felicidad@192.168.1.2";
            System.out.println(comando);
            Process p = Runtime.getRuntime().exec(comando);
            //InputStream is = p.getInputStream();
            //BufferedInputStream buffer=new BufferedInputStream(is);
            p.waitFor();

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Recurses down a directory and its subdirectories to look for
     * files to add to the Zip. If the current file being looked at
     * is not a directory, the method adds it to the Zip file.
     */
    private static void recurseFiles(File file)
            throws IOException, FileNotFoundException {

        String fileName = "";
        if (file.isDirectory()) {
            //Create an array with all of the files and subdirectories
            //of the current directory.
            String[] fileNames = file.list();
            if (fileNames != null) {
                //Recursively add each array entry to make sure that we get
                //subdirectories as well as normal files in the directory.
                for (int i = 0; i < fileNames.length; i++) {
                    recurseFiles(new File(file, fileNames[i]));
                }
            }
        } //Otherwise, a file so add it as an entry to the Zip file.
        else {

            byte[] buf = new byte[1024];
            int len;
            //Create a new Zip entry with the file's name.

            System.out.println(file.toString());
            ZipEntry zipEntry = new ZipEntry(file.toString());
            //Create a buffered input stream out of the file


            FileInputStream fin = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fin);
            zos.putNextEntry(zipEntry);
            //Read bytes from the file and write into the Zip archive.


            while ((len = in.read(buf)) >= 0) {
                zos.write(buf, 0, len);
            }
            //Close the input stream.


            in.close();
            //Close this entry in the Zip stream.


            zos.closeEntry();
        }
    }
}