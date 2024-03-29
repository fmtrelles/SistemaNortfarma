/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DescuentosOtrosAños.java
 *
 * Created on 22-jul-2015, 1:57:57
 */

package sistemanortfarma;

import CapaLogica.LogicaConexion;
import CapaLogica.LogicaDescuentosInv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class DescuentosOtrosAnios extends javax.swing.JInternalFrame {

    DescuentosInventarios objDescuentosInventarios;
    JDesktopPane desktopPane;
    LogicaDescuentosInv logicaDesc = new LogicaDescuentosInv();
    Connection conex;
    LogicaConexion logconex = new LogicaConexion();

    /** Creates new form DescuentosOtrosAños */
    public DescuentosOtrosAnios() {
        initComponents();
    }

    DescuentosOtrosAnios(java.awt.Frame parent, DescuentosInventarios descInventarios, JDesktopPane desktop) {
        initComponents();
        objDescuentosInventarios = descInventarios;
        desktopPane = desktop;
        conex = logconex.GetConnection();
        insertaAnios();
                
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
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtPF = new javax.swing.JTextField();
        txtMF = new javax.swing.JTextField();
        txtMM = new javax.swing.JTextField();
        txtMQ = new javax.swing.JTextField();
        txtMV = new javax.swing.JTextField();
        txtMC = new javax.swing.JTextField();
        txtPM = new javax.swing.JTextField();
        txtPQ = new javax.swing.JTextField();
        txtPV = new javax.swing.JTextField();
        txtPC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboAnio = new javax.swing.JComboBox();
        txtTotal = new javax.swing.JTextField();
        chkBase = new javax.swing.JCheckBox();
        btnCalc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(DescuentosOtrosAnios.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtPF.setText(resourceMap.getString("txtPF.text")); // NOI18N
        txtPF.setMinimumSize(new java.awt.Dimension(100, 27));
        txtPF.setName("txtPF"); // NOI18N

        txtMF.setText(resourceMap.getString("txtMF.text")); // NOI18N
        txtMF.setMinimumSize(new java.awt.Dimension(100, 27));
        txtMF.setName("txtMF"); // NOI18N

        txtMM.setText(resourceMap.getString("txtMM.text")); // NOI18N
        txtMM.setMinimumSize(new java.awt.Dimension(100, 27));
        txtMM.setName("txtMM"); // NOI18N

        txtMQ.setText(resourceMap.getString("txtMQ.text")); // NOI18N
        txtMQ.setMinimumSize(new java.awt.Dimension(100, 27));
        txtMQ.setName("txtMQ"); // NOI18N

        txtMV.setText(resourceMap.getString("txtMV.text")); // NOI18N
        txtMV.setMinimumSize(new java.awt.Dimension(100, 27));
        txtMV.setName("txtMV"); // NOI18N

        txtMC.setText(resourceMap.getString("txtMC.text")); // NOI18N
        txtMC.setMinimumSize(new java.awt.Dimension(100, 27));
        txtMC.setName("txtMC"); // NOI18N

        txtPM.setText(resourceMap.getString("txtPM.text")); // NOI18N
        txtPM.setMinimumSize(new java.awt.Dimension(100, 27));
        txtPM.setName("txtPM"); // NOI18N

        txtPQ.setText(resourceMap.getString("txtPQ.text")); // NOI18N
        txtPQ.setMinimumSize(new java.awt.Dimension(100, 27));
        txtPQ.setName("txtPQ"); // NOI18N

        txtPV.setText(resourceMap.getString("txtPV.text")); // NOI18N
        txtPV.setMinimumSize(new java.awt.Dimension(100, 27));
        txtPV.setName("txtPV"); // NOI18N

        txtPC.setText(resourceMap.getString("txtPC.text")); // NOI18N
        txtPC.setMinimumSize(new java.awt.Dimension(100, 27));
        txtPC.setName("txtPC"); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setMaximumSize(new java.awt.Dimension(100, 10));
        jLabel8.setMinimumSize(new java.awt.Dimension(100, 10));
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setMaximumSize(new java.awt.Dimension(100, 10));
        jLabel9.setMinimumSize(new java.awt.Dimension(100, 10));
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setMaximumSize(new java.awt.Dimension(100, 10));
        jLabel10.setMinimumSize(new java.awt.Dimension(100, 10));
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setMaximumSize(new java.awt.Dimension(100, 10));
        jLabel11.setMinimumSize(new java.awt.Dimension(100, 10));
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setMaximumSize(new java.awt.Dimension(100, 10));
        jLabel12.setMinimumSize(new java.awt.Dimension(100, 10));
        jLabel12.setName("jLabel12"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPC, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtPV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        cboAnio.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        cboAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR" }));
        cboAnio.setName("cboAnio"); // NOI18N

        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        chkBase.setText(resourceMap.getString("chkBase.text")); // NOI18N
        chkBase.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkBase.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkBase.setName("chkBase"); // NOI18N
        chkBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkBaseMouseClicked(evt);
            }
        });

        btnCalc.setFont(resourceMap.getFont("btnCalc.font")); // NOI18N
        btnCalc.setIcon(resourceMap.getIcon("btnCalc.icon")); // NOI18N
        btnCalc.setText(resourceMap.getString("btnCalc.text")); // NOI18N
        btnCalc.setName("btnCalc"); // NOI18N
        btnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcActionPerformed(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCalc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBase))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAnio, 0, 148, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkBase))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCalc, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setBackground(resourceMap.getColor("jLabel5.background")); // NOI18N
        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setForeground(resourceMap.getColor("jLabel5.foreground")); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkBaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkBaseMouseClicked
        if(chkBase.isSelected()) {            
            //cboAnio.setEnabled(false);
            txtTotal.setEditable(true);
            btnCalc.setText("CALCULAR");
            cboAnio.removeAllItems();
            cboAnio.addItem("SELECCIONAR");
            cargaDatosComboAnioAnterior();
        }else {
            insertaAnios();
            //cboAnio.setEnabled(true);
            txtTotal.setEditable(false);
            btnCalc.setText("VER TOTAL");
        }
        habilitarCajas();
    }//GEN-LAST:event_chkBaseMouseClicked

    private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed

        if(chkBase.isSelected()) {
            if(cboAnio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN AÑO PARA REFERENCIAR LA BASE", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
            }else {
                calculaMontos(cboAnio.getSelectedItem().toString());
            }
        }else if(!chkBase.isSelected()) {
            if(cboAnio.getSelectedIndex() == 0) {                
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN AÑO", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
            }else {
                verTot();
                habilitarCajas();
            }
        }        
    }//GEN-LAST:event_btnCalcActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   
        if(cboAnio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN AÑO PARA REGISTRAR", "NORTFARMA", JOptionPane.WARNING_MESSAGE);
        }else{
            if(chkBase.isSelected()) {
                calculaMontos(cboAnio.getSelectedItem().toString());
                verTotal();
            }else{
                verificaAniosRegistrados(cboAnio.getSelectedItem().toString());
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalc;
    private javax.swing.JComboBox cboAnio;
    private javax.swing.JCheckBox chkBase;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtMC;
    private javax.swing.JTextField txtMF;
    private javax.swing.JTextField txtMM;
    private javax.swing.JTextField txtMQ;
    private javax.swing.JTextField txtMV;
    private javax.swing.JTextField txtPC;
    private javax.swing.JTextField txtPF;
    private javax.swing.JTextField txtPM;
    private javax.swing.JTextField txtPQ;
    private javax.swing.JTextField txtPV;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables


    private void registrarBase() {

    }

    private void verTot() {
        double mf = Double.parseDouble(txtMF.getText());
        double mm = Double.parseDouble(txtMM.getText());
        double mq = Double.parseDouble(txtMQ.getText());
        double mv = Double.parseDouble(txtMV.getText());
        double mc = Double.parseDouble(txtMC.getText());
        double pf = Double.parseDouble(txtPF.getText());
        double pm = Double.parseDouble(txtPM.getText());
        double pq = Double.parseDouble(txtPQ.getText());
        double pv = Double.parseDouble(txtPV.getText());
        double pc = Double.parseDouble(txtPC.getText());

        double total = (mf+mm+mq+mv+mc+pf+pm+pq+pv+pc);
        double tot = Math.round(total*Math.pow(10,2))/Math.pow(10,2);
        txtTotal.setText(String.valueOf(tot));
    }

    private void habilitarCajas() {

        if(chkBase.isSelected()) {
            txtMC.setEditable(false);
            txtMF.setEditable(false);
            txtMM.setEditable(false);
            txtMQ.setEditable(false);
            txtMV.setEditable(false);
            txtPC.setEditable(false);
            txtPF.setEditable(false);
            txtPM.setEditable(false);
            txtPQ.setEditable(false);
            txtPV.setEditable(false);
        }else {
            txtMC.setEditable(true);
            txtMF.setEditable(true);
            txtMM.setEditable(true);
            txtMQ.setEditable(true);
            txtMV.setEditable(true);
            txtPC.setEditable(true);
            txtPF.setEditable(true);
            txtPM.setEditable(true);
            txtPQ.setEditable(true);
            txtPV.setEditable(true);
        }

    }

    private void verTotal() {
        verTot();
        int var = JOptionPane.showConfirmDialog(this, "EL TOTAL ES "+txtTotal.getText().toString()+"\n DESEA CONTINUAR?", "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
        if(var == 0) {
            guardaDescuento();
        }else {
            JOptionPane.showMessageDialog(this, "VERIFIQUE LOS MONTOS INGRESADOS");
        }

    }

    private void guardaDescuento() {
        int opc;

        if(chkBase.isSelected()) {
            opc = 1;
        }else {
            opc = 0;
        }

        String anio     = cboAnio.getSelectedItem().toString();
        double total    = Double.parseDouble(txtTotal.getText());
        double v1       = Double.parseDouble(txtMF.getText());
        double v2       = Double.parseDouble(txtMM.getText());
        double v3       = Double.parseDouble(txtMQ.getText());
        double v4       = Double.parseDouble(txtMV.getText());
        double v5       = Double.parseDouble(txtMC.getText());
        double v6       = Double.parseDouble(txtPF.getText());
        double v7       = Double.parseDouble(txtPM.getText());
        double v8       = Double.parseDouble(txtPQ.getText());
        double v9       = Double.parseDouble(txtPV.getText());
        double v10       = Double.parseDouble(txtPC.getText());

        if(logicaDesc.ActualizaDescuentoBase(opc,anio,total,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10)) {
            JOptionPane.showMessageDialog(this, "SE GUARDO CORRECTAMENTE EL DESCUENTO DEL AÑO "+anio, "NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }

    }

    public void limpiar() {
        txtMF.setText("0");
        txtMM.setText("0");
        txtMQ.setText("0");
        txtMV.setText("0");
        txtMC.setText("0");
        txtPF.setText("0");
        txtPM.setText("0");
        txtPQ.setText("0");
        txtPV.setText("0");
        txtPC.setText("0");
        txtTotal.setText("");
        cboAnio.setSelectedIndex(0);
        chkBase.setSelected(false);
    }

    public ResultSet cargaComboAnioAnterior() throws Exception{
        String consulta = "select Anio from DescuentoInv where Base = 0 and Actual = 0 order by Anio desc";
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    private void cargaDatosComboAnioAnterior() {
        try {

            ResultSet rs;
            rs = cargaComboAnioAnterior();

            while(rs.next()){
                cboAnio.addItem(rs.getString(1));
            }


        } catch (Exception e) {
        }
    }

    public ResultSet verificaEsAnioRepetido(String anio) throws Exception{
        String consulta = "select count(Anio) from DescuentoInv where Anio = "+anio.toString();
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet verMontosBase(String anio) throws Exception{
        String consulta = "select ROUND((Monto*100/DescuentoInv.TotalDescuento),2), IdTipoDescuento "
                + "from DescuentoDetalle inner join DescuentoInv on DescuentoInv.IdDescuento = "
                + "DescuentoDetalle.IdDescuento where DescuentoInv.Anio = "+anio+" "
                + "order by IdTipoDescuento";
        PreparedStatement ps = conex.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    private void calculaMontos(String Anio) {
        try {
            ResultSet rs ;
            rs = verMontosBase(Anio);

            double montoBase = Double.parseDouble(txtTotal.getText());

            while(rs.next()) {     

                if(rs.getString(2).equals("000001")) {
                    txtMF.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000002")) {
                    txtMM.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000003")) {
                    txtMQ.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000004")) {
                    txtMV.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000005")) {
                    txtMC.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000006") ) {
                    txtPF.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000007")) {
                    txtPM.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000008")) {
                    txtPQ.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000009")) {
                    txtPV.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }else if(rs.getString(2).equals("000010")) {
                    txtPC.setText(String.valueOf(
                            Math.round((rs.getDouble(1)*montoBase/100)*Math.pow(10,2))/Math.pow(10,2)).toString());
                }
            }

        } catch (Exception e) {
        }
    }

    private void verificaAniosRegistrados(String anio) {
        try {
            ResultSet rs = null;
           
            rs = verificaEsAnioRepetido(anio);

            while(rs.next()){
                if(rs.getInt(1) == 1) {
                    JOptionPane.showMessageDialog(this, "YA EXISTE UN REGISTRO DE ESTE AÑO", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    verTotal();
                }
            }
        } catch (Exception e) {
        }
    }

    private void insertaAnios() {
        cboAnio.removeAllItems();
        cboAnio.addItem("SELECCIONAR");
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);

        cboAnio.addItem(anio-1);
        cboAnio.addItem(anio-2);
        cboAnio.addItem(anio-3);

    }

}
