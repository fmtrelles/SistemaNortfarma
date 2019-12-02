/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentasxBotica.java
 *
 * Created on 29/12/2011, 10:37:10 AM
 */
package sistemanortfarma;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import entidad.Personal;
import java.util.List;
import java.util.ArrayList;
import CapaLogica.LogicaPersonal;
import CapaLogica.LogicaBoticas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import entidad.Venta;

/**
 *
 * @author Miguel Gomez S.
 */
public class ReporteMovilidad extends javax.swing.JInternalFrame implements Runnable {

    OpcionesReportes obreporte;
    String idbotica;
    AplicacionVentas obj;
    String usuario;    
    Date fechaInicio = null;
    Date fechaFin = null;
    List<Venta> listMovilidad = new ArrayList<Venta>();
    List<Venta> listMovilidad_aux = new ArrayList<Venta>();
    List<Venta> listitem = new ArrayList<Venta>();
    List<Venta> MtoTotal = new ArrayList<Venta>();
    String opcion;
    Thread showThread;
    List<Personal> Lista_Personal = new ArrayList<Personal>();
    LogicaPersonal logperso = new LogicaPersonal();
    JasperPrint jasperPrint;
    Map parameters = new HashMap();
    JasperReport masterReport = null;
    Connection con = null;
    String report_file;
    LogicaBoticas logbotica = new LogicaBoticas();
    JasperViewer view;
    private URL archivo;
    private int idcaja;
    VerificaSistema objsistema;

    /** Creates new form VentasxBotica */
    public ReporteMovilidad(OpcionesReportes objeto) {
        initComponents();
        obreporte = objeto;
        idbotica = obj.getIdbotica();
        usuario = obj.getUsuario();
        con = objeto.getCon();
        OcultaLabel(false);
        this.jTextField1.setText(idbotica);
        CargarResponsable();
    }

    private void OcultaLabel(boolean valor) {
        this.jLabel6.setVisible(valor);
        this.jLabel7.setVisible(valor);
    }

    private void CargarResponsable() {
        
        int rol = new login().getId_rol();

        if (rol == 16) {
            Lista_Personal = logperso.Lista_Quimico(idbotica,usuario,desde.getDate(),hasta.getDate());

            for (int i = 0; i < Lista_Personal.size(); i++) {
                jComboBox6.addItem(Lista_Personal.get(i).getApellido());
            }            
        } else {
            jComboBox6.addItem(usuario);
            //idcajero = objeto.getIdpesonal();
        }

    }

    public void run() {
        try {
            String sistema = "Windows";
            int userMov = Lista_Personal.get(this.jComboBox6.getSelectedIndex()).getId_Personal();

        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String fecha1 = sdf.format(desde.getDate());
            String fecha2 = sdf1.format(hasta.getDate());            
//AQUI            
           
            double suma = 0;
            double sumaAcumulada = 0;
            double suma_aux = 0;
            int traeitem_aux = 0;
            String traeFecha="";
            String doc;
            int caja;
            int turno;
            String botica;
            double mto;
            Date fecha;
            String permov;
            int Traeitem;
            int maxitemFecha = 0;
            int it;
            int item = 0;
            int auxdia = 0;

            //int insertacorrelativo = logbotica.InsertaCorrelativo(idbotica,userMov,fecha1,fecha2);

            listMovilidad.removeAll(listMovilidad); 
            int anular = logbotica.Limia_ITEM(idbotica,userMov,fecha1,fecha2);
            int mtoLimite = logbotica.Mto_Limite(idbotica);
            listMovilidad = logbotica.Verifica_DatosMov(idbotica,userMov,fecha1,fecha2);
            for (Integer i = 0; i < listMovilidad.size(); i++) {                         
                         
                doc = listMovilidad.get(i).getDocumento();
                caja=listMovilidad.get(i).getId_Caja();
                turno=listMovilidad.get(i).getIdturno();
                botica=listMovilidad.get(i).getId_Botica();
                mto=listMovilidad.get(i).getMonto();
                fecha = listMovilidad.get(i).getFecha_Venta();
                permov = listMovilidad.get(i).getIdpersonalMov();
                Traeitem = listMovilidad.get(i).getitem();

                /*int cuenta = logbotica.Verifica_TotalFecha(idbotica,String.valueOf(userMov),String.valueOf(fecha),String.valueOf(fecha));
                if (cuenta == 1){
                   suma = 0;
                   suma += listMovilidad.get(i).getMonto();
                   if (suma <= mtoLimite){
                       maxitemFecha = logbotica.Verifica_MaxItemFecha(idbotica,String.valueOf(userMov),String.valueOf(fecha1),String.valueOf(fecha2));
                       item = maxitemFecha +1;
                      logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                   }else{                             
                      suma = mto;
                      if (suma <= mtoLimite){
                          item = 4;
                         logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                      }else{
                         item = maxitemFecha + 1;
                         logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                      }
                         suma =0;
                   }

                }else{*/
                    
                     //listMovilidad_aux.removeAll(listMovilidad_aux);
                     //listMovilidad_aux = logbotica.Verifica_DatosMov1(idbotica,userMov,String.valueOf(fecha),String.valueOf(fecha));
                     //for (Integer x = 0; x < listMovilidad_aux.size(); x++) {
                     traeFecha = String.valueOf(traeFecha);
                     String fechacompara = String.valueOf(fecha); 
                     if (!traeFecha.equals("")){
                        if (!traeFecha.equals(fechacompara)){
                        
                            auxdia = 1;
                         }else
                         {
                            auxdia = 0;
                         }
                     }else
                     {
                        traeFecha = String.valueOf(fecha);
                        auxdia = 0;
                     }
                     
                     suma += listMovilidad.get(i).getMonto();
                     traeitem_aux = traeitem_aux;
                     sumaAcumulada+= mto;

                     doc = listMovilidad.get(i).getDocumento();
                     caja=listMovilidad.get(i).getId_Caja();
                     turno=listMovilidad.get(i).getIdturno();
                     botica=listMovilidad.get(i).getId_Botica();
                     mto=listMovilidad.get(i).getMonto();
                     fecha = listMovilidad.get(i).getFecha_Venta();
                     permov = listMovilidad.get(i).getIdpersonalMov();
                     Traeitem = listMovilidad.get(i).getitem();
                     suma_aux = suma;
                     if (auxdia == 0){
                         if (sumaAcumulada > mtoLimite){
                             suma = mto;
                             suma_aux = suma;
                             sumaAcumulada = mto;
                             int TraeItem = logbotica.Trae_ITEMNuevo(idbotica,String.valueOf(userMov),String.valueOf(fecha1),String.valueOf(fecha2),String.valueOf(traeitem_aux),String.valueOf(traeFecha),String.valueOf(fecha));
                             //traeitem_aux = TraeItem +1;
                             traeitem_aux = TraeItem;
                         }
                     }else{
                            suma = mto;
                             suma_aux = suma;
                             sumaAcumulada = mto;
                             int TraeItem = logbotica.Trae_ITEMNuevo(idbotica,String.valueOf(userMov),String.valueOf(fecha1),String.valueOf(fecha2),String.valueOf(traeitem_aux),String.valueOf(traeFecha),String.valueOf(fecha));
                             //traeitem_aux = TraeItem +1;
                             traeitem_aux = TraeItem;
                     }

                     if (suma <= mtoLimite && sumaAcumulada <= mtoLimite){
                        //int SumaItem = logbotica.Verifica_SUMAITEM(idbotica,String.valueOf(userMov),String.valueOf(fecha1),String.valueOf(fecha2),String.valueOf(traeitem_aux));
                        if (traeitem_aux != 0 && sumaAcumulada <= mtoLimite){
                           item = traeitem_aux;
                        }else if (traeitem_aux != 0 && (sumaAcumulada == mtoLimite || sumaAcumulada >= mtoLimite)){
                           item = traeitem_aux +1;
                        }else{
                           item =1;
                        }
                        logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                     }else if (suma <= mtoLimite && sumaAcumulada > mtoLimite){
                        suma_aux = suma;
                        suma = mto;
                        if (suma <= mtoLimite ){
                           if (traeitem_aux != 0 && suma_aux >= mtoLimite){
                            item = traeitem_aux + 1;
                            }else{
                               item =2;
                            }
                           logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                        }
                           suma =0;
                    }else if (suma > mtoLimite && sumaAcumulada > mtoLimite){
                        suma_aux = suma;
                        suma = mto;
                        if (suma <= mtoLimite ){
                           if (traeitem_aux != 0 && suma_aux >= mtoLimite){
                            item = traeitem_aux + 1;
                            }else{
                               item =2;
                            }
                           logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                        }else{
                            if (traeitem_aux != 0 && suma_aux >= mtoLimite){
                            item = traeitem_aux + 1;
                            }else{
                               item =2;
                            }
                           logbotica.Verifica_TotalMov(botica,permov,String.valueOf(fecha),doc,caja,turno,mto, item);
                        }

                           suma =0;
                        }
                          
                          //sumaAcumulada = sumaAcumulada; //aqui
                          suma =mto;
                          
                          int it_aux = logbotica.Verifica_ITEM(idbotica,String.valueOf(userMov),String.valueOf(fecha),String.valueOf(fecha),doc);
                          traeitem_aux = it_aux;
                          traeFecha = String.valueOf(fecha);

                          
                    //}
             }
             
             listitem.removeAll(listitem);
             listitem = logbotica.Verifica_TotalItem(idbotica,userMov,fecha1,fecha2);             

             for (Integer y = 0; y < listitem.size(); y++) {

                int insertacorrelativo = logbotica.InsertaCorrelativo(idbotica,userMov,fecha1,fecha2);
                it = listitem.get(y).getitem();
                //Date fec1 = listitem.get(y).getFecha_Venta();
                
                parameters.put("OPCION", opcion);
                parameters.put("INIDBOTICA", idbotica);
                parameters.put("USUARIO", usuario);
                parameters.put("FECHAINICIO", desde.getDate());
                parameters.put("FECHAFIN", hasta.getDate());
                //parameters.put("FECHAINICIO", fec1);
                //parameters.put("FECHAFIN", fec1);
                parameters.put("IDUSERMOV", userMov);
                parameters.put("ITEM", it);                
                parameters.put("REPORT_CONNECTION", con);

            //}
                archivo = this.getClass().getResource("/Reportes/Reporte_Movilidad.jasper");

                if (objsistema.getsSistemaOperativo().indexOf(sistema) != -1) {
                    parameters.put("SUBREPORT_DIR", "Reportes/");
                } else {
                    parameters.put("SUBREPORT_DIR", "//Reportes//");
                }


                masterReport = (JasperReport) JRLoader.loadObject(archivo);
                jasperPrint = JasperFillManager.fillReport(masterReport, parameters, con);
                view = new JasperViewer(jasperPrint, false);
                view.setTitle("Ventas Totales de la Botica ");
                view.setVisible(true);
                view.setSize(870, 700);

            }

        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el reporte", "Error 1", JOptionPane.ERROR_MESSAGE);
        }

        //jXDatePicker1.setEnabled(true);
        //jXDatePicker2.setEnabled(true);
        jButton1.setEnabled(true);
        OcultaLabel(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        desde = new org.jdesktop.swingx.JXDatePicker();
        hasta = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(ReporteMovilidad.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jComboBox6.setName("jComboBox6"); // NOI18N

        desde.setName("desde"); // NOI18N

        hasta.setName("hasta"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(61, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(230, 230, 230))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)))
        );

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

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

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setForeground(resourceMap.getColor("jLabel7.foreground")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        OcultaLabel(true);
        fechaInicio = this.desde.getDate();
        fechaFin = this.hasta.getDate();
       // opcion = this.jComboBox1.getSelectedItem().toString();

        showThread = new Thread(this);
        showThread.start();

        //this.jXDatePicker1.setEnabled(false);
        //this.jXDatePicker2.setEnabled(false);
        this.jButton1.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        obreporte.entra = true;
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker desde;
    private org.jdesktop.swingx.JXDatePicker hasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
