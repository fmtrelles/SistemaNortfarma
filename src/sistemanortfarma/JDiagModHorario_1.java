package sistemanortfarma;


import CapaLogica.LogicaHorariosTrabajadores;
import entidad.Horario_Trabajo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import jxl.write.DateTime;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDiagModHorario.java
 *
 * Created on 17-abr-2014, 16:10:02
 */

/**
 *
 * @author MAX
 */
public class JDiagModHorario_1 extends javax.swing.JDialog {
    private String dni;
        
    /** Creates new form JDiagModHorario */
    public JDiagModHorario_1(java.awt.Frame parent, boolean modal, String dni, String nombres, String cargo) {
        super(parent, modal);
        initComponents();
        this.dni=dni;
        jLabel6.setText(nombres);
        jLabel7.setText(cargo);
        this.MostrarHorario(dni);
        this.setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sm);
        SpinnerDateModel sm2 =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner2 = new javax.swing.JSpinner(sm2);
        SpinnerDateModel sm3 =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner3 = new javax.swing.JSpinner(sm3);
        SpinnerDateModel sm4 =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner4 = new javax.swing.JSpinner(sm4);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Hora Salida :");

        jLabel2.setText("Hora Entrada :");

        jLabel3.setText("Hora Entrada :");

        jLabel5.setText("Hora Salida :");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1,"HH:mm:ss");
        jSpinner1.setEditor(de);
        jSpinner1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSpinner1KeyReleased(evt);
            }
        });

        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner2,"HH:mm:ss");
        jSpinner2.setEditor(de2);

        JSpinner.DateEditor de3 = new JSpinner.DateEditor(jSpinner3,"HH:mm:ss");
        jSpinner3.setEditor(de3);

        JSpinner.DateEditor de4 = new JSpinner.DateEditor(jSpinner4,"HH:mm:ss");
        jSpinner4.setEditor(de4);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Observacion :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jButton1)
                .addContainerGap(162, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner4, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar Horario"));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        jLabel6.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Guarda este Horario? ", "Confirmar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (p == JOptionPane.YES_OPTION) {
                if(ValidarHoras1()==false){
                    JOptionPane.showMessageDialog(null, "TURNO1 : Hora Llegada es mayor que Hora salida \n ", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(ValidarHoras2()==false){
                    JOptionPane.showMessageDialog(null, "TURNO2 : Hora Llegada es mayor que Hora salida \n ", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(ValidarTurnos()==false){
                    JOptionPane.showMessageDialog(null, "Hora Llegada Turno 2, es mayor que Hora salida Turno 1 \n ", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                LogicaHorariosTrabajadores lh=new LogicaHorariosTrabajadores();
                Date a=(Date)jSpinner1.getModel().getValue();
                String t1_ll=a.getHours()+":"+a.getMinutes()+":"+a.getSeconds();
                Date b=(Date)jSpinner2.getModel().getValue();
                String t1_s=b.getHours()+":"+b.getMinutes()+":"+b.getSeconds();
                Date c=(Date)jSpinner3.getModel().getValue();
                String t2_ll=c.getHours()+":"+c.getMinutes()+":"+c.getSeconds();
                Date d=(Date)jSpinner4.getModel().getValue();
                String t2_s=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();

                Horario_Trabajo ht= new Horario_Trabajo();
                ht.setT1_H_Entrada(t1_ll);
                ht.setT1_H_Salida(t1_s);
                ht.setT2_H_Entrada(t2_ll);
                ht.setT2_H_Salida(t2_s);
                ht.setObservacion(jTextArea1.getText());
                lh.Modifica_Horario_Trabajo(ht, dni);
                JOptionPane.showMessageDialog(this, "El horario se ha modificado correctamente");
                this.dispose();
            }


        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSpinner1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner1KeyReleased
    private boolean ValidarHoras1(){
        Date a=(Date)jSpinner1.getModel().getValue();
        Date b=(Date)jSpinner2.getModel().getValue();
        if(a.getHours()>b.getHours())
            return false;
        else if(a.getHours()== b.getHours()){
            if(a.getMinutes()>b.getMinutes())
                return false;
            else if(a.getMinutes()==b.getMinutes()){
                if(a.getSeconds()>=b.getSeconds())
                    return false;
            }
        }
        return true;
    }
    private boolean ValidarHoras2(){
        Date a=(Date)jSpinner3.getModel().getValue();
        Date b=(Date)jSpinner4.getModel().getValue();
        if(a.getHours()>b.getHours())
            return false;
        else if(a.getHours()== b.getHours()){
            if(a.getMinutes()>b.getMinutes())
                return false;
            else if(a.getMinutes()==b.getMinutes()){
                if(a.getSeconds()>=b.getSeconds())
                    return false;
            }
        }
        return true;
    }
    public boolean ValidarTurnos(){
        Date a=(Date)jSpinner2.getModel().getValue();
        Date b=(Date)jSpinner3.getModel().getValue();
        if(a.getHours()>b.getHours())
            return false;
        else if(a.getHours()== b.getHours()){
            if(a.getMinutes()>b.getMinutes())
                return false;
            else if(a.getMinutes()==b.getMinutes()){
                if(a.getSeconds()>b.getSeconds())
                    return false;
            }
        }
        return true;
    }
    public void MostrarHorario(String busqueda){
        LogicaHorariosTrabajadores lh=new LogicaHorariosTrabajadores();
        Horario_Trabajo ht=lh.ListaHorarioTrabajoPersona(busqueda);
        Date fecha = null;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("HH:mm:ss");
        
        String strFecha = ht.getT1_H_Entrada();
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (Exception ex) {}
        jSpinner1.getModel().setValue(fecha);
        strFecha = ht.getT1_H_Salida();
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (Exception ex) {}
        jSpinner2.getModel().setValue(fecha);
        strFecha = ht.getT2_H_Entrada();
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (Exception ex) {}
        jSpinner3.getModel().setValue(fecha);
        strFecha = ht.getT2_H_Salida();
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (Exception ex) {}
        jSpinner4.getModel().setValue(fecha);

    }
    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDiagModHorario dialog = new JDiagModHorario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}