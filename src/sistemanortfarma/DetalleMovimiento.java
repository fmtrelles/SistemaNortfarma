
/*
 * DetalleMovimiento.java
 *
 * Created on 27/04/2011, 09:05:32 AM
 */
package sistemanortfarma;

import CapaLogica.LogicaGuias;
import CapaLogica.LogicaProducto;
import CapaLogica.LogicaVenta;
import entidad.Guias;
import entidad.ResultadoVenta;
import entidad.Varios;
import entidad.Venta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Miguel Gomez S.
 */
public class DetalleMovimiento extends JDialog {
    
    LogicaGuias objGuias = new LogicaGuias();
    List<Guias> listguias;
    TableColumn columnaTabla;
    LogicaVenta objVentas = new LogicaVenta();
    List<Integer> anArray = new ArrayList<Integer>();
    LogicaProducto objProducto = new LogicaProducto();
    List<Varios> Stocks;
    List<Venta> listaInternos = new ArrayList<Venta>();
    MuestraVentana objetoventana = new MuestraVentana();
    private DefaultTableModel model;
    TableColumnModel colModel1;
    List<Venta> listaVenta = new ArrayList<Venta>();
    VerificaAnulaciones objrelew;
    private String tipo_almacen;
    private String interno = null;
    private String monto = null;
    private String validar= null;
    int marca = 0;
    int idpersonal;
    int mostrarCargos;
    private String idbotica;
    AplicacionVentas objventa;
    String almacen;
    AnulacionCargosDescargos objcargos;
    String idmovimiento;
    String tipomovi;

    public DetalleMovimiento(Frame obj, int marcado, int idepers, int venta, String idboti) {
        super(obj, true);
        initComponents();
        idpersonal = idepers;
        marca = marcado;
        if (marca == 1) {
            this.jButton1.setEnabled(false);
        }
        model = (DefaultTableModel) this.tbDetalle.getModel();
        colModel1 = this.tbDetalle.getColumnModel();
        this.setLocationRelativeTo(null);
        idbotica = idboti;
        mostrarCargos = venta;
        AparienciaTabla();
        mostrarDetalle();
        this.jTextArea1.requestFocus();

    }

    private void AparienciaTabla() {
        TableColumn col = colModel1.getColumn(0);
        JTableHeader cabecera = new JTableHeader(this.tbDetalle.getColumnModel());
        cabecera.setReorderingAllowed(false);

        DefaultTableCellRenderer tcenter = new DefaultTableCellRenderer();
        tcenter.setHorizontalAlignment(SwingConstants.RIGHT);
        this.tbDetalle.getColumnModel().getColumn(4).setCellRenderer(tcenter);

        DefaultTableCellRenderer tcenter1 = new DefaultTableCellRenderer();
        tcenter1.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbDetalle.getColumnModel().getColumn(3).setCellRenderer(tcenter1);
        this.tbDetalle.getColumnModel().getColumn(2).setCellRenderer(tcenter1);

    }

    private void mostrarDetalle() {
        Integer enterito = 0;
        Integer fraccionado = 0;
        listaVenta.removeAll(listaVenta);

        try {

            String numero = "";

            if (mostrarCargos == 0) {
                numero = AnulacionCargosDescargos.getNumeroDocumento();
            } else {
                numero = Anulaciones.getNumeroDocumento();
            }

            String botica = idbotica;
            Venta obj = new Venta(botica, numero);
            Venta obj1 = null;

            if (mostrarCargos == 1) {
                listaInternos = objVentas.Lista_Internos_Ventas(obj, 1);
                tipo_almacen = "Anulacion Venta";

                if (listaInternos.size() > 0) {
                    jTextField3.setText(listaInternos.get(0).getCajero());
                    jTextField4.setText(listaInternos.get(0).getNombre());
                    jTextField1.setText(listaInternos.get(0).getSerie());
                    jTextField2.setText(listaInternos.get(0).getNumero());
                    lblFchaDocumento.setText(String.valueOf(listaInternos.get(0).getFecha_Venta()));
                    lblFchaRegistro.setText(String.valueOf(listaInternos.get(0).getFecha_Venta()));
                    lblDocumento.setText(numero);
                    lblTipoMovimiento.setText(tipo_almacen);
                    lblBotica.setText(botica);
                }

            } else {

                numero = AnulacionCargosDescargos.getNumeroDocumento();
                tipo_almacen = AnulacionCargosDescargos.getTipalmacen();
                idmovimiento = AnulacionCargosDescargos.getIdTipoMovimiento();
                tipomovi = AnulacionCargosDescargos.getTipoDocumento();
                String proveedor = AnulacionCargosDescargos.getProveedor();
                obj1 = new Venta(botica, tipo_almacen, proveedor, idmovimiento, numero,tipomovi,0);
                
                listaInternos = objVentas.Lista_Mostrar_Cargos(obj1);
                jTextField4.setVisible(false);
                jLabel7.setVisible(false);

                if (listaInternos.size() > 0) {
                    jLabel8.setText("Responsable");
                    jTextField3.setText(listaInternos.get(0).getResponsable());
                    jTextField1.setText(listaInternos.get(0).getSerie());
                    jTextField2.setText(listaInternos.get(0).getNumero());
                    lblFchaDocumento.setText(String.valueOf(listaInternos.get(0).getFechadoc()));
                    lblFchaRegistro.setText(String.valueOf(listaInternos.get(0).getFechareg()));
                    lblDocumento.setText(numero);
                    lblTipoMovimiento.setText(tipo_almacen);
                    lblBotica.setText(botica);
                    jTextField5.setText(String.valueOf(listaInternos.get(0).getMovi()));
                }

            }

            if (mostrarCargos == 1) {
                listaVenta = objVentas.ListaVenta_Detalle(obj);
            } else {
                listaVenta = objVentas.ListaMovimiento_Detalle(obj1, lblDocumento.getText().trim());
            }


            int lengthproducto = 0;
            int cant = 0;


            for (Integer i = 0; i < listaVenta.size(); i++) {

                Object[][] data = {
                    {listaVenta.get(i).getId_Producto(),
                        listaVenta.get(i).getDescr_Producto(),
                        listaVenta.get(i).getUnidad(),
                        listaVenta.get(i).getFraccion(),
                        listaVenta.get(i).getTotal_producto()
                    }};

                model.addRow(data[0]);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled()); // see question above
            setBackground(Color.white);

            for (Integer inicio = 0; inicio < anArray.size(); inicio++) {
                if (row == anArray.get(inicio)) {
                    setBackground(Color.lightGray);
                }
            }
            super.getTableCellRendererComponent(table, value, false, false, 2, column);
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblBotica = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblTipoMovimiento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblFchaDocumento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblFchaRegistro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sistemanortfarma.SistemaNortfarmaApp.class).getContext().getResourceMap(DetalleMovimiento.class);
        jLabel16.setBackground(resourceMap.getColor("jLabel16.background")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jXTitledPanel1.setTitle(resourceMap.getString("jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.setName("jXTitledPanel1"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbDetalle.setFont(resourceMap.getFont("tbDetalle.font")); // NOI18N
        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Und", "Fracc", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDetalle.setName("tbDetalle"); // NOI18N
        tbDetalle.setSelectionBackground(resourceMap.getColor("tbDetalle.selectionBackground")); // NOI18N
        tbDetalle.setSelectionForeground(resourceMap.getColor("tbDetalle.selectionForeground")); // NOI18N
        tbDetalle.getTableHeader().setReorderingAllowed(false);
        tbDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalle);
        if (tbDetalle.getColumnModel().getColumnCount() > 0) {
            tbDetalle.getColumnModel().getColumn(0).setResizable(false);
            tbDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbDetalle.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tbDetalle.columnModel.title0")); // NOI18N
            tbDetalle.getColumnModel().getColumn(1).setResizable(false);
            tbDetalle.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbDetalle.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tbDetalle.columnModel.title1")); // NOI18N
            tbDetalle.getColumnModel().getColumn(2).setResizable(false);
            tbDetalle.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbDetalle.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tbDetalle.columnModel.title2")); // NOI18N
            tbDetalle.getColumnModel().getColumn(3).setResizable(false);
            tbDetalle.getColumnModel().getColumn(3).setPreferredWidth(40);
            tbDetalle.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tbDetalle.columnModel.title3")); // NOI18N
            tbDetalle.getColumnModel().getColumn(4).setResizable(false);
            tbDetalle.getColumnModel().getColumn(4).setPreferredWidth(60);
            tbDetalle.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tbDetalle.columnModel.title4")); // NOI18N
        }

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);

        jLabel3.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        lblBotica.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        lblBotica.setEditable(false);
        lblBotica.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        lblBotica.setText(resourceMap.getString("lblBotica.text")); // NOI18N
        lblBotica.setName("lblBotica"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lblTipoMovimiento.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        lblTipoMovimiento.setEditable(false);
        lblTipoMovimiento.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        lblTipoMovimiento.setText(resourceMap.getString("lblTipoMovimiento.text")); // NOI18N
        lblTipoMovimiento.setName("lblTipoMovimiento"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        lblDocumento.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        lblDocumento.setEditable(false);
        lblDocumento.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        lblDocumento.setText(resourceMap.getString("lblDocumento.text")); // NOI18N
        lblDocumento.setName("lblDocumento"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jTextField2.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField2.setEditable(false);
        jTextField2.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        lblFchaDocumento.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        lblFchaDocumento.setEditable(false);
        lblFchaDocumento.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        lblFchaDocumento.setText(resourceMap.getString("lblFchaDocumento.text")); // NOI18N
        lblFchaDocumento.setName("lblFchaDocumento"); // NOI18N
        lblFchaDocumento.setPreferredSize(new java.awt.Dimension(25, 21));

        jLabel12.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        lblFchaRegistro.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        lblFchaRegistro.setEditable(false);
        lblFchaRegistro.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        lblFchaRegistro.setText(resourceMap.getString("lblFchaRegistro.text")); // NOI18N
        lblFchaRegistro.setName("lblFchaRegistro"); // NOI18N
        lblFchaRegistro.setPreferredSize(new java.awt.Dimension(25, 21));

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setEditable(false);
        jTextField3.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField4.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField4.setEditable(false);
        jTextField4.setFont(resourceMap.getFont("jTextField1.font")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBotica)
                    .addComponent(lblTipoMovimiento)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblFchaRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFchaDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                .addGap(49, 49, 49)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblBotica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblFchaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(lblFchaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText(resourceMap.getString("jTextArea1.toolTipText")); // NOI18N
        jTextArea1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea1.setName("jTextArea1"); // NOI18N
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                    .addGroup(jXTitledPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
                .addContainerGap())
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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
            .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (tbDetalle.getRowCount() > 0) {

            
            int reply = JOptionPane.showConfirmDialog(null, "Seguro de Anular el Movimiento Seleccionado:", "NORTFARMA", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                VerificaAnulacion();

            }
        
        }else{

            JOptionPane.showMessageDialog(this, "Debe Elegir un Interno","NORTFARMA", JOptionPane.INFORMATION_MESSAGE);
        }



/*
        boolean resul = true;
        ResultadoVenta objresultado;
        int reply = JOptionPane.showConfirmDialog(null, "Seguro de Anular el Movimiento Seleccionado:", "NORTFARMA", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            try {

                if (mostrarCargos == 1) {
                    resul = objVentas.Anula(lblBotica.getText().toString(), lblDocumento.getText().toString(), idpersonal, jTextArea1.getText().trim().toUpperCase());

                    if (resul) {
                        JOptionPane.showMessageDialog(null, "SU VENTA HA SIDO ANULADA", "VENTA ANULADA", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL ANULAR  SU VENTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    objresultado = objVentas.AnulaCargoDescargo(lblBotica.getText().toString(), idmovimiento, lblDocumento.getText().toString(), String.valueOf(idpersonal), objcargos.getTipalmacen(), objcargos.getProveedor(), jTextArea1.getText());

                    if (objresultado.getIderror() == 0) {
                        JOptionPane.showMessageDialog(null, "SU MOVIMIENTO  HA SIDO ANULADO", "MOVIMIENTO  ANULADO", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else if (objresultado.getIderror() == 1) {
                        JOptionPane.showMessageDialog(null, "ERROR : \n No Cuenta con el Stock del Producto '" + objresultado.getIdproducto() + "' ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo Sentimos Hubo un  error al Anular su Movimiento  ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception ex) {
                System.out.println("Wrror DetalleMovimiento " + ex.getMessage());
            }

        }*/

    }//GEN-LAST:event_jButton1ActionPerformed

    private void VerificaAnulacion(){
        
        if (mostrarCargos == 1) {            
        
            Ventana obj = new Ventana();
            VerificaAnulaciones verifica = new VerificaAnulaciones(obj);
            verifica.pack();
            verifica.show(true);

            interno = verifica.getInterno();
            monto = verifica.getMonto();

            validar = objProducto.RetornaVerificaInterno(interno,monto,lblFchaDocumento.getText());
        }
        else
        {
            validar = "1";
        }

        if (validar.equals("1")){

            boolean resul = true;
            ResultadoVenta objresultado;
             try {

                if (mostrarCargos == 1) {
                    resul = objVentas.Anula(lblBotica.getText().toString(), lblDocumento.getText().toString(), idpersonal, jTextArea1.getText().trim().toUpperCase());

                    if (resul) {
                        JOptionPane.showMessageDialog(null, "SU VENTA HA SIDO ANULADA", "VENTA ANULADA", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL ANULAR  SU VENTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    objresultado = objVentas.AnulaCargoDescargo(lblBotica.getText().toString(), idmovimiento, lblDocumento.getText().toString(), String.valueOf(idpersonal), objcargos.getTipalmacen(), objcargos.getProveedor(), jTextArea1.getText(),jTextField5.getText().trim());

                    if (objresultado.getIderror() == 0) {
                        JOptionPane.showMessageDialog(null, "SU MOVIMIENTO  HA SIDO ANULADO", "MOVIMIENTO  ANULADO", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else if (objresultado.getIderror() == 1) {
                        JOptionPane.showMessageDialog(null, "ERROR : \n No Cuenta con el Stock del Producto '" + objresultado.getIdproducto() + "' ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo Sentimos Hubo un  error al Anular su Movimiento  ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error DetalleMovimiento " + ex.getMessage());
            }

        }else{
            JOptionPane.showMessageDialog(this,"Los datos ingresados no coinciden con los del documento a anular","NORTFARMA",JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public class Ventana extends JFrame {

        public Ventana() {
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        if (marca == 0) {
            if (jTextArea1.getText().trim().length() != 0){
            jButton1.setEnabled(true);
            }else{
            jButton1.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void tbDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleKeyReleased
    private String[] ArrayBoticas = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private javax.swing.JTextField lblBotica;
    private javax.swing.JTextField lblDocumento;
    private javax.swing.JTextField lblFchaDocumento;
    private javax.swing.JTextField lblFchaRegistro;
    private javax.swing.JTextField lblTipoMovimiento;
    private javax.swing.JTable tbDetalle;
    // End of variables declaration//GEN-END:variables
}
