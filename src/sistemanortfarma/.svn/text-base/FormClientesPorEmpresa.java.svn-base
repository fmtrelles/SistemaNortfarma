package sistemanortfarma;

import CapaLogica.LogicaClientes;
import entidad.Clientes;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public final class FormClientesPorEmpresa extends JDialog {

    LogicaClientes objcliente = new LogicaClientes();
    DarCredito objcotiza;
    RenovarCredito objRenueva;
    RenovarCredito objrenovarCredito;
    RealizaVenta objventa;
    public static String CodigoCliente;
    public static int Id_Cliente;
    public static String RUC_DNI;
    public static String Nombre_RazonSocial;
    public static String Direccion;
    private static String DNI;
    private static String Empresa;
    private static Double SaldoAccc;
    private String idbotica;
    TableColumn columnaTabla = null;
    String[] datosDetalle = new String[4];
    List<Clientes> listacleintes = new ArrayList<Clientes>();
    ModeloTabla modelClientes;
    private static double saldo;

    public static Double getSaldoAccc() {
        return SaldoAccc;
    }

    public static void setSaldoAccc(Double SaldoAccc) {
        FormClientesPorEmpresa.SaldoAccc = SaldoAccc;
    }

    public static String getEmpresa() {
        return Empresa;
    }

    public static void setEmpresa(String Empresa) {
        FormClientesPorEmpresa.Empresa = Empresa;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        FormClientesPorEmpresa.saldo = saldo;
    }

    /** Creates new form FormClientesPorEmpresa */
    public FormClientesPorEmpresa() {
        initComponents();
    }

    public static String getCodigoCliente() {
        return CodigoCliente;
    }

    public static void setCodigoCliente(String CodigoCliente) {
        FormClientesPorEmpresa.CodigoCliente = CodigoCliente;
    }

    public static String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        FormClientesPorEmpresa.DNI = DNI;
    }

    public static String getDireccion() {
        return Direccion;
    }

    public static void setDireccion(String Direccion) {
        FormClientesPorEmpresa.Direccion = Direccion;
    }

    public static int getId_Cliente() {
        return Id_Cliente;
    }

    public static void setId_Cliente(int Id_Cliente) {
        FormClientesPorEmpresa.Id_Cliente = Id_Cliente;
    }

    public static String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public static void setNombre_RazonSocial(String Nombre_RazonSocial) {
        FormClientesPorEmpresa.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public static String getRUC_DNI() {
        return RUC_DNI;
    }

    public static void setRUC_DNI(String RUC_DNI) {
        FormClientesPorEmpresa.RUC_DNI = RUC_DNI;
    }

    public FormClientesPorEmpresa(DarCredito obj, java.awt.Frame ventana, String Empresa, String idboti) {
        super(ventana, true);
        initComponents();
        idbotica = idboti;
        objcotiza = obj;
        setLocationRelativeTo(null);
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        BuscaCliente();
        jTextField1.requestFocus();

    }

    public FormClientesPorEmpresa(RenovarCredito obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        objRenueva = obj;
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        jTextField1.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormClientesPorEmpresa.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
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
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (KeyEvent.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            this.jTable1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == 10) {
            setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
            setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            setRUC_DNI(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            setSaldo(Double.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()));
            dispose();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {
            setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
            setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            setRUC_DNI(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            setSaldo(Double.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()));
            dispose();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        BuscaCliente();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BuscaCliente() {

        List<Clientes> listacleintes22 = new ArrayList<Clientes>();
        String filtro = jTextField1.getText().trim();

        for (Integer x = 0; x < modelClientes.getRowCount();) {
            modelClientes.removeRow(x);
        }

        if (filtro.compareTo("") != 0) {
            filtro = '%' + filtro + '%';
        } else {
            filtro = "";
        }

        listacleintes22 = objcliente.ListaClientes(getEmpresa().toString(), filtro, idbotica);

        for (Integer inicio = 0; inicio < listacleintes22.size(); inicio++) {
            Object[][] data = {
                {inicio + 1,
                    listacleintes22.get(inicio).getId_Cliente(),
                    listacleintes22.get(inicio).getRUC_DNI().toString(),
                    listacleintes22.get(inicio).getNombre_RazonSocial(),
                    listacleintes22.get(inicio).getSaldo()
                }
            };

            modelClientes.addRow(data[0]);
        }

        jTable1.setModel(modelClientes);

    }

    public void CrearCabecera() {

        modelClientes.addColumn("ITEM");
        modelClientes.addColumn("CODIGO");
        modelClientes.addColumn("RUC/DNI");
        modelClientes.addColumn("NOMBRE O RAZON SOCIAL");
        modelClientes.addColumn("SALDO");

        jTable1.setModel(modelClientes);
        columnaTabla = jTable1.getColumnModel().getColumn(0);
        columnaTabla.setPreferredWidth(40);
        columnaTabla.setMinWidth(40);
        columnaTabla.setMaxWidth(40);

        columnaTabla = jTable1.getColumnModel().getColumn(1);
        columnaTabla.setPreferredWidth(100);
        columnaTabla.setMinWidth(100);
        columnaTabla.setMaxWidth(100);

        columnaTabla = jTable1.getColumnModel().getColumn(2);
        columnaTabla.setPreferredWidth(100);
        columnaTabla.setMinWidth(100);
        columnaTabla.setMaxWidth(100);

        columnaTabla = jTable1.getColumnModel().getColumn(4);
        columnaTabla.setPreferredWidth(100);
        columnaTabla.setMinWidth(100);
        columnaTabla.setMaxWidth(100);

    }

    public class ModeloTabla extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
