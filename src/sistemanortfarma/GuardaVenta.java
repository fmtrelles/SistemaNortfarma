package sistemanortfarma;

import CapaLogica.RecuperaSerieNumero;
import entidad.SerieNumero;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Gomez S.
 */
public class GuardaVenta extends JDialog {

    private String mensaje = "";
    RecuperaSerieNumero objlogicaPagos = new RecuperaSerieNumero();
    List<SerieNumero> listaPagos = new ArrayList<SerieNumero>();
    private static String serie;
    private static String mnumero;
    private static boolean bandera = false;
    private String monto;
    private double montofinal=0.0;
    private static String montoPago;
    private static String vuelto;
    private String redondeo;
    RealizaVenta objventa;
    //variables que recibe
    private String idbotica;
    private int idcaja, tipoventa;
    private String documento;
    private static int ventaDelivery;

    public GuardaVenta(java.awt.Frame parent, String idboti, int idca, int idven, String docu, String auxmonto, RealizaVenta obj, String redo) {
        super(parent, true);
        initComponents();
        jTextField6.setBackground(Color.WHITE);
        jTextField7.setBackground(Color.WHITE);
        objventa = obj;
        setLocationRelativeTo(null);
        idbotica = idboti;
        idcaja = idca;
        tipoventa = idven;
        monto = auxmonto;
        documento = docu;
        redondeo = redo;
        recuperadDatos();
        setBandera(false);
        RevisaDelivery();
        jTextField2.requestFocus();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public static String getMnumero() {
        return mnumero;
    }

    public static boolean isBandera() {
        return bandera;
    }

    public static void setBandera(boolean bandera) {
        GuardaVenta.bandera = bandera;
    }

    public static void setMnumero(String mnumero) {
        GuardaVenta.mnumero = mnumero;
    }

    public static String getSerie() {
        return serie;
    }
    public static void setSerie(String serie) {
        GuardaVenta.serie = serie;
    }

    public static String getmontoPago() {
        return montoPago;
    }
    public static void setmontoPago(String montoPago) {
        GuardaVenta.montoPago = montoPago;
    }

    public static String getvuelto() {
        return vuelto;
    }
    public static void setvuelto(String vuelto) {
        GuardaVenta.vuelto = vuelto;
    }

    public static int getVentaDelivery() {
        return ventaDelivery;
    }

    public static void setVentaDelivery(int ventaDelivery) {
        GuardaVenta.ventaDelivery = ventaDelivery;
    }

    private void Ocultar(boolean valor) {
        jLabel5.setVisible(valor);
        jCheckBox1.setVisible(valor);
    }

    private void RevisaDelivery() {
        if (AplicacionVentas.getDelivery() == 0) {
            jCheckBox1.setSelected(false);
            Ocultar(false);
        } else {
            Ocultar(true);

            if (AplicacionVentas.getMarcadoDelivery() == 1) {
                jCheckBox1.setSelected(true);
            } else {
                jCheckBox1.setSelected(false);
            }
        }
    }

    private void recuperadDatos() {

        if (tipoventa == 12) {
            Color colo = new Color(40, 109, 218);
            Color micolo = new Color(255, 255, 255);
            jPanel2.setBackground(colo);
            jLabel8.setForeground(micolo);
            jLabel9.setForeground(micolo);
            jLabel4.setForeground(micolo);
            jTextField6.setBackground(colo);
            jTextField7.setBackground(colo);
            jTextField6.setForeground(micolo);
            jTextField7.setForeground(micolo);
        }
        if (monto.equals("")){
            monto = "0";
            montofinal = 0;
        }else{

            montofinal = Double.valueOf(monto) - (Double.valueOf(redondeo)*-1);
        }
        listaPagos = objlogicaPagos.RecuperaSerieNumero(idbotica, idcaja, tipoventa);
        jLabel4.setText(" " + documento + " DE VENTA ");
        serie = listaPagos.get(0).getSerie();
        mnumero = listaPagos.get(0).getNumero();
        this.jTextField6.setText(serie);
        this.jTextField7.setText(mnumero);
        this.jTextField1.setText(monto);
        /*BigDecimal bd = new BigDecimal(montofinal);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        String sumaTotal = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        this.jTextField1.setText(String.valueOf(sumaTotal));*/

        if (this.jTextField1.getText().compareTo("") == 0) {
            this.jTextField1.setText(String.valueOf(0));
            monto = this.jTextField1.getText();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(GuardaVenta.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField6.setEditable(false);
        jTextField6.setBackground(resourceMap.getColor("jTextField7.background")); // NOI18N
        jTextField6.setFont(resourceMap.getFont("jTextField7.font")); // NOI18N
        jTextField6.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText(resourceMap.getString("jTextField6.text")); // NOI18N
        jTextField6.setName("jTextField6"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setBackground(resourceMap.getColor("jTextField7.background")); // NOI18N
        jTextField7.setFont(resourceMap.getFont("jTextField7.font")); // NOI18N
        jTextField7.setForeground(resourceMap.getColor("jTextField7.foreground")); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setName("jTextField7"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(12, 12, 12))
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(80, 25));

        jLabel2.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField2.background")); // NOI18N
        jTextField2.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.setNextFocusableComponent(jButton2);
        jTextField2.setPreferredSize(new java.awt.Dimension(80, 25));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField3.setEditable(false);
        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(80, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jButton2.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton2.setMnemonic('G');
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setNextFocusableComponent(jButton3);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setNextFocusableComponent(jButton1);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setMnemonic('C');
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jCheckBox1.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
}//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        RealizaOpciones(evt);
}//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        if (this.jTextField2.getText().trim().length() >= 0) {
            CalculaVuelto();
        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR UNA CANTIDAD EN MONTO ", "Error", JOptionPane.INFORMATION_MESSAGE);
            this.jTextField2.requestFocus();
        }
}//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bandera = true;
            setBandera(bandera);
            if (this.jCheckBox1.isSelected()) {
                ventaDelivery = 1;
            } else {
                ventaDelivery = 0;
            }
             montoPago = this.jTextField2.getText();
            vuelto = this.jTextField3.getText();

            setVentaDelivery(ventaDelivery);
            setmontoPago(montoPago);
            setvuelto(vuelto);
            dispose();
            //objventa.requestFocus();
            //objventa.transferFocus();
        }
}//GEN-LAST:event_jButton2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.jTextField2.getText().trim().compareTo("") != 0) {
            bandera = true;
            setBandera(bandera);
            if (this.jCheckBox1.isSelected()) {
                ventaDelivery = 1;
            } else {
                ventaDelivery = 0;
            }
            montoPago = this.jTextField2.getText();
            vuelto = this.jTextField3.getText();

            setVentaDelivery(ventaDelivery);
            setmontoPago(montoPago);
            setvuelto(vuelto);
            dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, " PORFAVOR DEBE DE INGRESAR UNA CANTIDAD EN MONTO  ", "Error", JOptionPane.INFORMATION_MESSAGE);
            this.jTextField2.requestFocus();
        }

}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTextField2.setText("");
            this.jTextField3.setText("");
            jTextField2.setEditable(true);
            this.jTextField2.requestFocus();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        this.jTextField2.setText("");
        this.jTextField3.setText("");
        jTextField2.setEditable(true);
        this.jTextField2.requestFocus();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.hide();
        }
        RealizaOpciones(evt);
}//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        bandera = false;
        setBandera(bandera);
        this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    private void RealizaOpciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            bandera = false;
            this.setBandera(bandera);
            this.dispose();
        }
    }

    private void CalculaVuelto() {
        double pago;

        try {

            double montopagar = Double.parseDouble(monto);

            if (montopagar == 0) {
                pago = 0;
            } else {
                pago = Double.parseDouble(this.jTextField2.getText().trim());
            }

            if (pago >= montopagar) {
                Double vuelto = pago - montopagar; //montofinal;

                BigDecimal bd1 = new BigDecimal(vuelto);
                bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
                vuelto = bd1.doubleValue();

                jTextField3.setText(bd1.setScale(2).toPlainString());
                jButton2.setEnabled(true);
                jButton2.requestFocus();
                jTextField2.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, " EL PAGO INGRESADO ES MENOR QUE EL COSTO  ", "Error", JOptionPane.INFORMATION_MESSAGE);
                jTextField2.requestFocus();
            }

        } catch (Exception ex) {
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
