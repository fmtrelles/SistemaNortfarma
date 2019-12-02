package sistemanortfarma;

import CapaLogica.LogicaClientes;
import entidad.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FormClientesPorEmpresa2 extends JDialog {

    LogicaClientes objcliente = new LogicaClientes();
    DarCredito objcotiza;
    RenovarCredito objRenueva;
    RenovarCredito objrenovarCredito;
    RealizaVenta objventa;
    /*
    VARIABLES DE LA TABLA CLIENTES
     */
    public static String CodigoCliente;
    public static int Id_Cliente;
    public static String RUC_DNI;
    public static String Nombre_RazonSocial;
    public static String Direccion;
    private static String DNI;
    private static String Empresa;
    private static Double SaldoAccc;
    private int bandera = 0;
    TableColumn columnaTabla = null;
    String[] datosDetalle = new String[4];
    List<Clientes> listacleintes = new ArrayList<Clientes>();
    ModeloTabla modelClientes;
    private static double saldo;
    MovimientosdeCredito objRenueva_1;
    private int marcado = 0;
    TableColumnModel colModel;
    Reporte_MovimientosCredito OBJETO;
    VentasxCredito objcredito;
    VentasxCliente vtscliente;

    public FormClientesPorEmpresa2(RenovarCredito obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        colModel = this.jTable1.getColumnModel();
        objRenueva = obj;
        setLocationRelativeTo(null);
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        setNombre_RazonSocial(null);
        AparienciaTabla();
        BuscaCliente();
        jTextField1.requestFocus();
    }

    public FormClientesPorEmpresa2(MovimientosdeCredito obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        colModel = this.jTable1.getColumnModel();
        setLocationRelativeTo(null);
        objRenueva_1 = obj;
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        setNombre_RazonSocial(null);
        AparienciaTabla();
        BuscaCliente();
        jTextField1.requestFocus();
        marcado = 1;
    }

    public FormClientesPorEmpresa2(Reporte_MovimientosCredito obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        colModel = this.jTable1.getColumnModel();
        setLocationRelativeTo(null);
        OBJETO = obj;
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        setNombre_RazonSocial(null);
        AparienciaTabla();
        BuscaCliente();
        jTextField1.requestFocus();
        marcado = 2;
    }

    public FormClientesPorEmpresa2(VentasxCredito obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        marcado = 3;
        colModel = this.jTable1.getColumnModel();
        setLocationRelativeTo(null);
        objcredito = obj;
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        setNombre_RazonSocial(null);
        AparienciaTabla();
        BuscaCliente();
        jTextField1.requestFocus();

    }

    public FormClientesPorEmpresa2(VentasxCliente obj, java.awt.Frame ventana, String Empresa) {
        super(ventana, true);
        initComponents();
        marcado = 3;
        colModel = this.jTable1.getColumnModel();
        setLocationRelativeTo(null);
        vtscliente = obj;
        modelClientes = new ModeloTabla();
        CrearCabecera();
        setEmpresa(Empresa);
        setNombre_RazonSocial(null);
        AparienciaTabla();
        BuscaCliente();
        jTextField1.requestFocus();

    }

    private void AparienciaTabla() {
        TableColumn col = colModel.getColumn(0);
        JTableHeader cabecera = new JTableHeader(this.jTable1.getColumnModel());
        cabecera.setReorderingAllowed(false);
        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcenter);

    }

    public static Double getSaldoAccc() {
        return SaldoAccc;
    }

    public static void setSaldoAccc(Double SaldoAccc) {
        FormClientesPorEmpresa2.SaldoAccc = SaldoAccc;
    }

    public static String getCodigoCliente() {
        return CodigoCliente;
    }

    public static void setCodigoCliente(String CodigoCliente) {
        FormClientesPorEmpresa2.CodigoCliente = CodigoCliente;
    }

    public static String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        FormClientesPorEmpresa2.DNI = DNI;
    }

    public static String getDireccion() {
        return Direccion;
    }

    public static void setDireccion(String Direccion) {
        FormClientesPorEmpresa2.Direccion = Direccion;
    }

    public static String getEmpresa() {
        return Empresa;
    }

    public static void setEmpresa(String Empresa) {
        FormClientesPorEmpresa2.Empresa = Empresa;
    }

    public static int getId_Cliente() {
        return Id_Cliente;
    }

    public static void setId_Cliente(int Id_Cliente) {
        FormClientesPorEmpresa2.Id_Cliente = Id_Cliente;
    }

    public static String getNombre_RazonSocial() {
        return Nombre_RazonSocial;
    }

    public static void setNombre_RazonSocial(String Nombre_RazonSocial) {
        FormClientesPorEmpresa2.Nombre_RazonSocial = Nombre_RazonSocial;
    }

    public static String getRUC_DNI() {
        return RUC_DNI;
    }

    public static void setRUC_DNI(String RUC_DNI) {
        FormClientesPorEmpresa2.RUC_DNI = RUC_DNI;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        FormClientesPorEmpresa2.saldo = saldo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(FormClientesPorEmpresa2.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyText(evt.getKeyCode()).compareTo("F9") == 0) {
            jTable1.requestFocus();
        }
        if (evt.getKeyCode() == 27) {
            this.hide();
        }

}//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

        if (marcado == 0) {
            if (evt.getKeyCode() == 10) {
                setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
                setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                setRUC_DNI(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                setSaldo(Double.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()));
                dispose();
            }
        } else {
            if (evt.getKeyCode() == 10) {
                setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
                setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                dispose();
            }

        }


}//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() % 2 == 0) {

            if (marcado == 0) {
                setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
                setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                setRUC_DNI(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                setSaldo(Double.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString()));
                dispose();

            } else {
                setId_Cliente(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
                setNombre_RazonSocial(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                dispose();

            }

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
            filtro = "" + '%';
        }

        if (marcado == 3) {
            listacleintes22 = objcliente.MiListaClientes(getEmpresa().toString(), filtro);
        } else {
            listacleintes22 = objcliente.ListaClientes2(getEmpresa().toString(), filtro);
        }

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

    private void CrearCabecera() {

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    // End of variables declaration//GEN-END:variables
}
