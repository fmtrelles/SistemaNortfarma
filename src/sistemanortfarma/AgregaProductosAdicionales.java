/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AgregaProductosCarDes.java
 *
 * Created on 11/04/2011, 07:34:45 PM
 */
package sistemanortfarma;

import CapaLogica.LogicaBoticas;
import CapaLogica.LogicaProducto;
import entidad.Producto;
import entidad.ProductosPrecios;
import entidad.Productos_Botica;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
//import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 *
 * @author Miguel Gomez S.
 */
public class AgregaProductosAdicionales extends JDialog {

    TableColumn columnaTabla = null;
    LogicaProducto objProductos = new LogicaProducto();
    List<ProductosPrecios> listproducto = new ArrayList();
    LogicaBoticas objBoticas = new LogicaBoticas();
    ModeloTabla modeloTabla;
    public static String real = "";
    public static String realDes = "";
    public static String realLab = "";
    public static String cantidad = "";
    private static String idproducto;
    private String idbotica;
    private String TipMovi;
    private static int unidad;
    private static int fraccion;
    private static double pvp;
    private static double descto;
    private String alma;
    private int stockempaque;
    int stkempaque;
    int empaque, stockfraccion;
    RequisitosFactura objfactura = new RequisitosFactura();
    private int filtro;

    /** Creates new form AgregaProductosCarDes */
    public AgregaProductosAdicionales(Frame obj, String idboti, String almacen, int fil,String tipo, String codnuevo) {
        super(obj, true);
        initComponents();
        idbotica = idboti;
        alma = almacen;
        filtro = fil;
        TipMovi = tipo;
        setLocationRelativeTo(null);
        modeloTabla = new ModeloTabla();
        construirGuia();
        setCantidad("");
        setIdproducto("");
        setUnidad(-1);
        setFraccion(-1);
        recuperacodigo(codnuevo);
        jTextField2.requestFocus();
        jTextField4.setEnabled(false);
    }

    public static double getDescto() {
        return descto;
    }

    public static void setDescto(double descto) {
        AgregaProductosAdicionales.descto = descto;
    }

    public static double getPvp() {
        return pvp;
    }

    public static void setPvp(double pvp) {
        AgregaProductosAdicionales.pvp = pvp;
    }

    public static int getUnidad() {
        return unidad;
    }

    public static void setUnidad(int unidad) {
        AgregaProductosAdicionales.unidad = unidad;
    }

    public static int getFraccion() {
        return fraccion;
    }

    public static void setFraccion(int fraccion) {
        AgregaProductosAdicionales.fraccion = fraccion;
    }

    public static String getIdproducto() {
        return idproducto;
    }

    public static void setIdproducto(String idproducto) {
        AgregaProductosAdicionales.idproducto = idproducto;
    }

    public static String getRealLab() {
        return realLab;
    }

    public static void setRealLab(String realLab) {
        AgregaProductosAdicionales.realLab = realLab;
    }

    public static String getReal() {
        return real;
    }

    public static void setReal(String real) {
        AgregaProductosAdicionales.real = real;
    }

    public static String getRealDes() {
        return realDes;
    }

    public static void setRealDes(String realDes) {
        AgregaProductosAdicionales.realDes = realDes;
    }

    public static String getCantidad() {
        return cantidad;
    }

    public static void setCantidad(String cantidad) {
        AgregaProductosAdicionales.cantidad = cantidad;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(AgregaProductosAdicionales.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setBackground(resourceMap.getColor("jTabbedPane1.background")); // NOI18N
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, resourceMap.getColor("jPanel1.border.highlightColor"), null)); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descripcion", "Codigo del Producto" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jTable1.setSelectionForeground(resourceMap.getColor("jTable1.selectionForeground")); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
            jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
            jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
            jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        }

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    }//GEN-LAST:event_jTextField1KeyReleased

    private void recuperacodigo(String codnuevo){
        try {
            String codproadicional;
            
            if (!String.valueOf(codnuevo).isEmpty()){
                //String num_ult = substring(codnuevo, 3,6);                
                String[] parts = codnuevo.split("D");
                //String part1 = parts[0]; // PR
                String num_ult = parts[1]; // 1
                int numult = Integer.valueOf(num_ult);
                codproadicional =  objProductos.recnumero(numult);
            }else{
                codproadicional =  objProductos.recnumero(0);
            }
            
            this.jTextField4.setText(codproadicional);
        } catch (SQLException ex) {
            Logger.getLogger(AgregaProductosAdicionales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyCode() == 10) {
            try {
                BuscarProducto();
            } catch (SQLException ex) {
                Logger.getLogger(AgregaProductosAdicionales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ((evt.getKeyCode() == 40) && (jTable1.getRowCount() > 0)) {
            jTable1.requestFocus();
            jTable1.setRowSelectionInterval(0, 0);
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void BuscarProducto() throws SQLException {

        listproducto.removeAll(listproducto);

        for (Integer inicio = 0; inicio < modeloTabla.getRowCount();) {
            modeloTabla.removeRow(inicio);
            modeloTabla.isCellEditable(inicio, 1);
            modeloTabla.isCellEditable(inicio, 2);
        }

        jTable1.removeAll();
        jTable1.setModel(modeloTabla);

        int opcion = jComboBox1.getSelectedIndex();
        
        String tipomov = objProductos.recTipoMOv(jTextField1.getText(), idbotica, opcion,TipMovi,1);       
        
        if (tipomov.equals("DT") || TipMovi.equals("T") || alma.equals("Trastienda")){
            listproducto = objProductos.ListarProductosCarDes1(jTextField1.getText(), idbotica, opcion,TipMovi,2);
            
            for (Integer i = 0; i < listproducto.size(); i++) {

            Object[][] data = {
                {
                    listproducto.get(i).getProductoBotica().getProducto().getIdProducto(),
                    listproducto.get(i).getProductoBotica().getProducto().getDescripcion(),
                    listproducto.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getMostrador_Stock_Fraccion(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Empaque(),
                    listproducto.get(i).getProductoBotica().getAlmacen_Stock_Fraccion(),
                    listproducto.get(i).getPrecio_Venta(),
                    listproducto.get(i).getDescuento_Venta(),
                    listproducto.get(i).getProductoBotica().getTrastiendaStockEmpaque(),
                    listproducto.get(i).getProductoBotica().getTrastiendaStockFraccion()
                }
            };

            modeloTabla.addRow(data[0]);

            }
            
            
        }else{
            listproducto = objProductos.ListarProductosCarDes(jTextField1.getText(), idbotica, opcion);
        

            for (Integer i = 0; i < listproducto.size(); i++) {

                Object[][] data = {
                    {
                        listproducto.get(i).getProductoBotica().getProducto().getIdProducto(),
                        listproducto.get(i).getProductoBotica().getProducto().getDescripcion(),
                        listproducto.get(i).getProductoBotica().getProducto().getLaboratorio().getId_Lab(),
                        listproducto.get(i).getProductoBotica().getMostrador_Stock_Empaque(),
                        listproducto.get(i).getProductoBotica().getMostrador_Stock_Fraccion(),
                        listproducto.get(i).getProductoBotica().getAlmacen_Stock_Empaque(),
                        listproducto.get(i).getProductoBotica().getAlmacen_Stock_Fraccion(),
                        listproducto.get(i).getPrecio_Venta(),
                        listproducto.get(i).getDescuento_Venta()
                    }
                };

                modeloTabla.addRow(data[0]);

            }
        }

    }

    private void construirGuia() {

        modeloTabla.addColumn("COD");
        modeloTabla.addColumn("PRODUCTO");
        modeloTabla.addColumn("LAB");
        modeloTabla.addColumn("S-Most-Und");
        modeloTabla.addColumn("S-Most-Frac");
        modeloTabla.addColumn("S-Alm-Und");
        modeloTabla.addColumn("S-Alm-Frac");        
        modeloTabla.addColumn("PVP");
        modeloTabla.addColumn("DCTO");
        modeloTabla.addColumn("S-Tras-Und");
        modeloTabla.addColumn("S-Tras-Frac");

        jTable1.setModel(modeloTabla);

        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(250);
        columnaTabla.setMinWidth(250);
        columnaTabla.setMaxWidth(250);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(5);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(6);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(3);
        columnaTabla.setPreferredWidth(50);
        columnaTabla.setMinWidth(50);
        columnaTabla.setMaxWidth(50);
        
        columnaTabla = jTable1.getColumnModel().getColumn(7);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);


        columnaTabla = jTable1.getColumnModel().getColumn(8);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMinWidth(0);
        columnaTabla.setMaxWidth(0);
        
        columnaTabla = jTable1.getColumnModel().getColumn(9);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);

        columnaTabla = jTable1.getColumnModel().getColumn(10);
        columnaTabla.setPreferredWidth(60);
        columnaTabla.setMinWidth(60);
        columnaTabla.setMaxWidth(60);


        AparienciaTabla();

    }

    private void AparienciaTabla() {
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);

    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() % 2 == 0) {
            devolverDatos();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        Realiza_Opciones(evt);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jTextField1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        devolverDatos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String prodAdicional = this.jTextField2.getText().trim();
        String cantidadAdicional = this.jTextField3.getText().trim();
        
        if (!String.valueOf(prodAdicional).isEmpty() || !String.valueOf(cantidadAdicional).isEmpty()){
            AgregarProdAdicional();
        }else{
            JOptionPane.showMessageDialog(this, " Porfavor Ingrese todos los Datos ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private boolean VerificaStock(String idproduc) {
        boolean resultado = false;
        int totalstock;
        List<Productos_Botica> empRecuperado = new ArrayList<Productos_Botica>();

        try {
            
            int G_opcion = jComboBox1.getSelectedIndex();        
            
            String EsConsMost = objProductos.recTipoMOv(idproduc, idbotica, G_opcion,TipMovi,4);            
            
            if (EsConsMost.equals("0")){
                String G_tipomov = objProductos.recTipoMOv(jTextField1.getText(), idbotica, G_opcion,TipMovi,1);               
                if (G_tipomov.equals("DT") /*|| alma.equals("Trastienda")*/){
                    alma = "T";
                }
            }else{
                alma = "Mostrador";
            }


            empRecuperado = objfactura.Retorna_Producto_Stock(idproduc, idbotica, alma);

            if (empRecuperado.size() > 0) {
                //RECUPERO MI EMPAQUE DEL PRODUCTO
                empaque = empRecuperado.get(0).getProducto().getEmpaque();
                int empaque_tmp = empaque;

                if (empaque_tmp == 0) {
                    empaque_tmp = 1;
                }

                //RECUPERO MI STOCK DEL EMPAQUIE
                stockempaque = empRecuperado.get(0).getMostrador_Stock_Empaque();
                stkempaque = stockempaque;

                //RECUPERO MI STOCK FRACCION
                stockfraccion = empRecuperado.get(0).getMostrador_Stock_Fraccion();
                int stfraccion = stockfraccion;

                totalstock = stkempaque * empaque_tmp + stfraccion;
                int cantidadpedida = unidad * empaque_tmp + fraccion;

                if (cantidadpedida <= totalstock) {
                    resultado = true;
                }

            }

        } catch (Exception ex) {
            System.out.println("ERROR EN CLASE COTIZACION METODO VerificaStock :" + ex.getMessage());
        }

        return resultado;

    }

    private void Realiza_Opciones(KeyEvent evt) {
        if (evt.getKeyCode() == 27) {
            CerrarVentana();
        }

        if (evt.getKeyText(evt.getKeyCode()).compareTo("F3") == 0) {
            jTextField1.requestFocus();
        }

        if (evt.getKeyCode() == 10) {
            devolverDatos();
        }
    }

    private void CerrarVentana() {
        dispose();
    }

    public void AgregarProdAdicional(){
        boolean ok = false;

        try {
            cantidad =  this.jTextField3.getText().trim();
                   
                if (cantidad != null) {

                    if (cantidad.compareTo("0") != 0) {

                        if (!String.valueOf(cantidad).isEmpty() || cantidad != null) {

                            cantidad = cantidad.toUpperCase();
                            boolean resul = VerificaCantidad(cantidad);

                            if (resul) {

                                ok = true;
                                        
                                if (ok) {
                                    this.setCantidad(cantidad);
                                    real = this.jTextField4.getText().trim();
                                    setReal(real);

                                            realDes = this.jTextField2.getText().trim();
                                            setRealDes(realDes);

                                            realLab = "ZZZZ";
                                            setRealLab(realLab);

                                            idproducto = this.jTextField4.getText().trim();
                                            setIdproducto(idproducto);

                                            pvp = 0.01;
                                            setPvp(pvp);

                                            descto = 0.01;
                                            setDescto(descto);

                                            setCantidad(cantidad);
                                            setUnidad(unidad);

                                            dispose();
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                
                            } else {
                                JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }

        } catch (Exception ex) {
        }
    }
    
    
    public void devolverDatos() {

        boolean ok = false;

        try {


            if (jTable1.getRowCount() > 0) {

                int fila = this.jTable1.getSelectedRow();

                if (fila >= 0) {
                    JOptionPane p = new JOptionPane();
                    cantidad = p.showInputDialog(this, "Ingrese Cantidad de Productos  ? ", "Ingrese Cantidad", JOptionPane.QUESTION_MESSAGE);

                    if (!String.valueOf(cantidad).isEmpty()) {

                        if (cantidad != null) {

                            if (cantidad.compareTo("0") != 0) {

                                if (!String.valueOf(cantidad).isEmpty() || cantidad != null) {

                                    cantidad = cantidad.toUpperCase();
                                    boolean resul = VerificaCantidad(cantidad);

                                    if (resul) {

                                        if (filtro == 0) {
                                                                                        
                                            if (VerificaStock(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())) {
                                                ok = true;
                                            }
                                        } else {
                                            ok = true;
                                        }

                                        if (ok) {
                                            this.setCantidad(cantidad);
                                            real = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                                            setReal(real);

                                            realDes = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                                            setRealDes(realDes);

                                            realLab = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
                                            setRealLab(realLab);

                                            idproducto = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                                            setIdproducto(idproducto);

                                            pvp = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
                                            setPvp(pvp);

                                            descto = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString());
                                            setDescto(descto);

                                            setCantidad(cantidad);
                                            setUnidad(unidad);

                                            dispose();
                                        } else {
                                            JOptionPane.showMessageDialog(this, " No hay Stock Suficiente de este Producto ", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese Datos Correctos  ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(this, " No Puede Ingresar una Cantidad 0  ", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
        }
    }


    /*
     * METODO QUE CALCULA SI LO INGRESADO ES UNIDAD Y FRACCION
     */
    private boolean VerificaCantidad(String cantidad) {
        String unidad1 = "";
        String fraccion1 = "";
        int k = 0;
        boolean segundo = false;
        char car = 'F';
        int cantdef = 0;
        String valor = "";

        cantidad = cantidad.trim();
        Character w;
        valor = valor.trim();

        for (int i = 0; i < cantidad.length(); i++) {
            w = cantidad.charAt(i);
            if (w.isDigit(w) || w.isLetter(w)) {
                valor = valor + w;
            } else {
                return false;
            }
        }


        for (int i = 0; i < valor.length(); i++) {
            Character caracter = valor.charAt(i);

            if (caracter.isLetter(caracter)) {
                caracter = caracter.toUpperCase(caracter);
                cantdef++;
                if (caracter != car || cantdef > 1) {
                    return false;
                }

            }
            if (caracter.isDigit(caracter) && segundo == false) {
                unidad1 = unidad1 + caracter;
            } else {
                segundo = true;
                if (k == 1) {
                    if (caracter.isDigit(caracter)) {
                        fraccion1 = fraccion1 + caracter;
                    }
                }
                k = 1;
            }
        }

        if (unidad1.compareTo("") == 0) {
            unidad1 = "0";
        }
        if (fraccion1.compareTo("") == 0) {
            fraccion1 = "0";
        }

        unidad = Integer.valueOf(unidad1);
        fraccion = Integer.valueOf(fraccion1);
        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
