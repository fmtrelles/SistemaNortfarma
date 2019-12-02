/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanortfarma;

import entidad.BuscaCaja;
import CapaDatos.DatosBuscaCaja;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import static sistemanortfarma.AplicacionVentas.credenciales;

/**
 *
 * @author Fernando Muñoz
 */
public class TrasladoEfectivo extends javax.swing.JInternalFrame {

    /**
     * Creates new form TrasladoEfectivo
     */
    public TrasladoEfectivo() {
        initComponents();
        leerArchivoServidor();
        convertirbotica();
        cargardatos();
        estadotxt();
    }

    VerificaSistema obj;
    AplicacionVentas objventa;
    int id;
    String botica, servidor, usuario, clave;
    String fecha;
    List<BuscaCaja> lista = new ArrayList<BuscaCaja>();
    Calendar fechatotal = new GregorianCalendar();
    int dia, mes, ano;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtturno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmonto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtrol = new javax.swing.JTextField();
        combocajas = new javax.swing.JComboBox<BuscaCaja>();
        btnprocesar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(TrasladoEfectivo.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtusuario.setText(resourceMap.getString("txtusuario.text")); // NOI18N
        txtusuario.setName("txtusuario"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtturno.setText(resourceMap.getString("txtturno.text")); // NOI18N
        txtturno.setName("txtturno"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtmonto.setText(resourceMap.getString("txtmonto.text")); // NOI18N
        txtmonto.setName("txtmonto"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtrol.setText(resourceMap.getString("txtrol.text")); // NOI18N
        txtrol.setName("txtrol"); // NOI18N

        combocajas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        combocajas.setName("combocajas"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtturno, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(combocajas, javax.swing.GroupLayout.Alignment.LEADING, 0, 99, Short.MAX_VALUE)
                                .addComponent(txtrol, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(combocajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtturno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel2.AccessibleContext.accessibleName")); // NOI18N

        btnprocesar.setIcon(resourceMap.getIcon("btnprocesar.icon")); // NOI18N
        btnprocesar.setText(resourceMap.getString("btnprocesar.text")); // NOI18N
        btnprocesar.setName("btnprocesar"); // NOI18N
        btnprocesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprocesarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(resourceMap.getIcon("btnsalir.icon")); // NOI18N
        btnsalir.setText(resourceMap.getString("btnsalir.text")); // NOI18N
        btnsalir.setName("btnsalir"); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnprocesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnprocesar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        int p = JOptionPane.showConfirmDialog(null, " ¿Desea Cerrar? ", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (p == JOptionPane.YES_OPTION) {
            salir();
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnprocesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprocesarActionPerformed
        // TODO add your handling code here:
        procesar();
    }//GEN-LAST:event_btnprocesarActionPerformed

    private void cargardatos() {
        
        
        ano = fechatotal.get(Calendar.YEAR);
        mes = fechatotal.get(Calendar.MONTH) + 1;
        dia = fechatotal.get(Calendar.DAY_OF_MONTH);

        fecha = String.valueOf(ano) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia);

        id = credenciales.get(0).getId_Personal();
        this.txtusuario.setText(credenciales.get(0).getApellido() + " " + credenciales.get(0).getNombre());
        this.txtrol.setText(credenciales.get(0).getDescrip_roles());
        lista = DatosBuscaCaja.retornacaja(id, botica, fecha);
        combocajas.removeAllItems();
        for (BuscaCaja b : lista) {
            combocajas.addItem(b.getDescripcion());
        }

    }

    private void estadotxt() {
        txtusuario.setEditable(false);
        txtturno.setEditable(false);
        txtrol.setEditable(false);
    }

    private void procesar() {
    }

    private void salir() {
        objventa.marcacdo = false;
        lista.clear();
        this.dispose();
    }

    private void convertirbotica() {
        String bot;
        int boti;
        bot = botica.substring(1);
        boti = Integer.parseInt(bot);
        if (boti < 10) {
            bot = "F" + String.valueOf(boti);
            botica = bot;
        }
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
                    botica = linea.toString();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnprocesar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox combocajas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtmonto;
    private javax.swing.JTextField txtrol;
    private javax.swing.JTextField txtturno;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
