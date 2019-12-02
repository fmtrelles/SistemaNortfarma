/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanortfarma;

/**
 *
 * @author Miguel Gomez S.
 */
import entidad.ProductosPrecios;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import CapaLogica.LogicaProducto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CodigoBarrasCupon extends JDialog {

    private List<ProductosPrecios> listDatosBotiquin = new ArrayList<ProductosPrecios>();
    LogicaProducto objproducto = new LogicaProducto();
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel jPanel = null;
    private JButton BAnular = null;
    private JButton BCancelar = null;
    private JLabel LblClave = null;
    private JLabel LblInforma = null;
    private JLabel Lbl2 = null;
    private JLabel LblTotal = null;    
    
    private JTextField TxtArticulo = null;
    private JTextField TxtCantidad = null;

    //private JLabel LblMensaje = null;
    private JTextField TxtMensaje = null;
    private JTextField TxtTotal = null;
    //private JTextField TxtTotal = null;
    private JTextField TxtPrecioEmpaque = null;
    private JTextField TxtPrecioBlister = null;
    private JTextField TxtPrecioFraccion = null;
    private JTextField TxtPrecio = null;
    static String articulo = new String();  //  @jve:decl-index=0:   
    static String cantidad = new String();  //  @jve:decl-index=0:
    static boolean ingresadet = false;
    ProductosPrecios producPrecio;
    Mant_Productos mantProduc = new Mant_Productos();
    

    int podecimalPantalla = 2;
    private int opcion = 1;

    /**
     * @param owner
     */
    public CodigoBarrasCupon() {
        super();        
        //producPrecio = precios;
        initialize();
        TxtPrecio.setEditable(false);
        //opcion = opc;
    }

    public static boolean isIngresadet() {
        return ingresadet;
    }

    public static void setIngresadet(boolean ingresadet) {
        CodigoBarrasCupon.ingresadet = ingresadet;
    }

    public static String getCantidad() {
        return cantidad;
    }

    public static void setCantidad(String cantidad) {
        CodigoBarrasCupon.cantidad = cantidad;
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        
        try {
            listDatosBotiquin.removeAll(listDatosBotiquin);
            int Ebotiquin = 0;
            
            String ProductoCod=producPrecio.getProductoBotica().getProducto().getIdProducto();
            String Idbot = producPrecio.getId_Botica();
            double PBlister = objproducto.Recupera_PrecioVenta(ProductoCod,7);
            listDatosBotiquin = objproducto.ListarDatosBotiquin(Idbot, ProductoCod);
            
            
            for (int i = 0; i < listDatosBotiquin.size(); i++) {                
                Ebotiquin = listDatosBotiquin.get(i).getEsBotiquin(); 
            }
            //if (Ebotiquin > 0){
            if (Ebotiquin > 0 || PBlister > 0){
                this.setSize(561, 240);
            }else{
                this.setSize(561, 178);
            }
            
            this.setResizable(false);
            this.setModal(true);
            this.setTitle("Cod.Barras - Cupon");
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            this.setContentPane(getJContentPane());
            this.setLocationRelativeTo(null);
            setIngresadet(false);
            setCantidad("");
            cargar();
            TxtCantidad.requestFocus();
            
        } catch (SQLException ex) {
            Logger.getLogger(CodigoBarrasCupon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            LblClave = new JLabel();
            LblClave.setText("COD. BARRAS - CUPON:");
            LblClave.setSize(new Dimension(109, 23));
            LblClave.setLocation(new Point(163, 43));//313,43
            //LblClave.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJPanel(), null);
            jContentPane.add(getBAnular(), null);
            jContentPane.add(getBCancelar(), null);
            //jContentPane.add(getMensaje(), null);
            jContentPane.add(getTotal(), null);
            //jContentPane.add(getPrecioEmpaque(), null);
            //jContentPane.add(getPrecioBlister(), null);
            //jContentPane.add(getPrecioFraccion(), null);
        }
        return jContentPane;
    }

    
    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            Lbl2 = new JLabel();
            Lbl2.setText("CANTIDAD:");
            Lbl2.setLocation(new Point(13, 43));
            Lbl2.setSize(new Dimension(69, 23));
            
            LblTotal = new JLabel();
            LblTotal.setText("TOTAL:");
            LblTotal.setLocation(new Point(370,43));
            LblTotal.setSize(new Dimension(50, 23));
            //LblTotal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            //LblTotal.setFont(new Font("Arial Narrow", Font.BOLD, 15));
            
            LblInforma = new JLabel();
            LblInforma.setHorizontalAlignment(SwingConstants.LEFT);
            LblInforma.setAutoscrolls(false);
            LblInforma.setHorizontalTextPosition(SwingConstants.LEFT);
            LblInforma.setLocation(new Point(13, 11));
            LblInforma.setSize(new Dimension(69, 23));
            LblInforma.setText("ARTICULO:");

            jPanel = new JPanel();
            jPanel.setLayout(null);           
            jPanel.setBounds(new Rectangle(12, 10, 533, 76));
            jPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            jPanel.add(LblInforma, null);
            jPanel.add(Lbl2, null);
            jPanel.add(LblTotal, null);
            jPanel.add(getTxtArticulo(), null);
            jPanel.add(LblClave, null);
            jPanel.add(getTxtPrecio(), null);
            jPanel.add(getTxtCantidad(), null);
            jPanel.add(getTotal(), null);            
            
        }
        return jPanel;
    }

    private JTextField getMensaje() {
        if (TxtMensaje == null) {
            TxtMensaje = new JTextField();
            TxtMensaje.setLocation(new Point(10, 97));
            //TxtMensaje.setDisabledTextColor(Color.darkGray);
            TxtMensaje.setDisabledTextColor(Color.RED);
            TxtMensaje.setEnabled(false);
            TxtMensaje.setFont(new Font("Arial Narrow", Font.BOLD, 15));
            TxtMensaje.setBackground(new Color(243, 243, 243));
            TxtMensaje.setForeground(new Color(255, 0, 0));
            TxtMensaje.setSize(new Dimension(280, 23));
            TxtMensaje.setBorder(null);
            TxtMensaje.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }

                }
            });
        }
        return TxtMensaje;
    }
    
    
    
    private JTextField getTotal() {
        
        if (TxtTotal == null) {
            TxtTotal = new JTextField();
            TxtTotal.setSize(new Dimension(86, 23));
            TxtTotal.setText("0.00");
            TxtTotal.setHorizontalAlignment(JTextField.RIGHT);
            TxtTotal.setFont(new Font("Dialog", Font.BOLD, 15));
            TxtTotal.setLocation(new Point(440, 50)); // 430,43 
            TxtTotal.setDisabledTextColor(Color.RED);
            TxtTotal.setEditable(false);
            TxtTotal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
                        
            TxtTotal.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    dispose();
                }
            });
            
            
        }
        return TxtTotal;
    }
    
    
    private JTextField getPrecioEmpaque() {
        if (TxtPrecioEmpaque == null) {
            TxtPrecioEmpaque = new JTextField();
            TxtPrecioEmpaque.setLocation(new Point(10, 133));          
            TxtPrecioEmpaque.setDisabledTextColor(Color.RED);
            TxtPrecioEmpaque.setEnabled(false);
            TxtPrecioEmpaque.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
            TxtPrecioEmpaque.setBackground(new Color(243, 243, 243));
            TxtPrecioEmpaque.setForeground(new Color(255, 0, 0));
            TxtPrecioEmpaque.setSize(new Dimension(280, 23));
            TxtPrecioEmpaque.setBorder(null);
            TxtPrecioEmpaque.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }

                }
            });
        }
        return TxtPrecioEmpaque;
    }
    
    
    private JTextField getPrecioBlister() {
        if (TxtPrecioBlister == null) {
            TxtPrecioBlister = new JTextField();
            TxtPrecioBlister.setLocation(new Point(10, 155));       
            TxtPrecioBlister.setDisabledTextColor(Color.RED);
            TxtPrecioBlister.setEnabled(false);
            TxtPrecioBlister.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
            TxtPrecioBlister.setBackground(new Color(243, 243, 243));
            TxtPrecioBlister.setForeground(new Color(255, 0, 0));
            TxtPrecioBlister.setSize(new Dimension(280, 23));
            TxtPrecioBlister.setBorder(null);
            TxtPrecioBlister.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }

                }
            });
        }
        return TxtPrecioBlister;
    }
    
    private JTextField getPrecioFraccion() {
        if (TxtPrecioFraccion == null) {
            TxtPrecioFraccion = new JTextField();
            String ProductoCod_x=producPrecio.getProductoBotica().getProducto().getIdProducto();
            double PBlister_x = objproducto.Recupera_PrecioVenta(ProductoCod_x,7);
            if (PBlister_x > 0 ) {
            TxtPrecioFraccion.setLocation(new Point(10, 173)); //169 -- para blister           
            }else{
                TxtPrecioFraccion.setLocation(new Point(10, 155)); //169 -- para blister           
            }
            TxtPrecioFraccion.setDisabledTextColor(Color.RED);
            TxtPrecioFraccion.setEnabled(false);
            TxtPrecioFraccion.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
            TxtPrecioFraccion.setBackground(new Color(243, 243, 243));
            TxtPrecioFraccion.setForeground(new Color(255, 0, 0));
            TxtPrecioFraccion.setSize(new Dimension(280, 23));
            TxtPrecioFraccion.setBorder(null);
            TxtPrecioFraccion.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }

                }
            });
        }
        return TxtPrecioFraccion;
    }
    
    /**
     * This method initializes BAnular
     *
     * @return javax.swing.JButton
     */
    private JButton getBAnular() {
        if (BAnular == null) {
            BAnular = new JButton();
            BAnular.setText("Aceptar");
            BAnular.setToolTipText("Aceptar datos de línea de venta");
            BAnular.setHorizontalTextPosition(SwingConstants.RIGHT);
            BAnular.setHorizontalAlignment(SwingConstants.LEFT);
            BAnular.setLocation(new Point(302, 97));//302,97
            BAnular.setSize(new Dimension(116, 41));
            BAnular.setFont(new Font("Dialog", Font.PLAIN, 12));
            BAnular.setIcon(new ImageIcon("IMAGENES/MODIFICAR.JPG"));
            
            BAnular.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    Cantidad();
                }
            });
            BAnular.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        Cantidad();
                    }

                }
            });
            BAnular.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Cantidad();
                }

                private void getBAnularKeyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }
                }
            });
        }
        return BAnular;
    }

    /**
     * This method initializes BCancelar
     *
     * @return javax.swing.JButton
     */
    private JButton getBCancelar() {
        if (BCancelar == null) {
            BCancelar = new JButton();
            BCancelar.setToolTipText("Cancelar datos y salir");
            BCancelar.setIcon(new ImageIcon("IMAGENES/CANCELAR2.JPG"));
            BCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
            BCancelar.setHorizontalAlignment(SwingConstants.LEFT);
            BCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
            BCancelar.setLocation(new Point(429, 97)); //429,97
            BCancelar.setSize(new Dimension(116, 41)); 
            BCancelar.setText("Cancelar");
            BCancelar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    ingresadet = false;
                    dispose();
                }
            });

            BCancelar.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        ingresadet = false;
                        dispose();
                    }

                }
            });
            BCancelar.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ingresadet = false;
                    dispose();
                }
            });
        }
        return BCancelar;
    }

    /**
     * This method initializes TxtArticulo
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtArticulo() {
        if (TxtArticulo == null) {
            TxtArticulo = new JTextField();
            TxtArticulo.setLocation(new Point(80, 11));
            TxtArticulo.setDisabledTextColor(Color.darkGray);
            TxtArticulo.setEnabled(false);
            TxtArticulo.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            TxtArticulo.setBackground(new Color(243, 243, 243));
            TxtArticulo.setSize(new Dimension(435, 23));
            TxtArticulo.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }

                }
            });
        }
        return TxtArticulo;
    }

    /**
     * This method initializes TxtCantidad
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtCantidad() {
        if (TxtCantidad == null) {
            TxtCantidad = new JTextField();
            TxtCantidad.setSize(new Dimension(71, 23));
            TxtCantidad.setText("1");
            TxtCantidad.setHorizontalAlignment(JTextField.RIGHT);
            TxtCantidad.setFont(new Font("Dialog", Font.BOLD, 14));
            TxtCantidad.setLocation(new Point(80, 43));
            TxtCantidad.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    Cantidad();
                }
            });

            TxtCantidad.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {                        
                        dispose();
                    }

                }   
                
            });  
            

            TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent e) {                   
                    
                    char caracter;
                    caracter = (e.getKeyChar() + "").charAt(0);                      
                    
                    if (Character.isLetter(caracter)) {
                        caracter = Character.toUpperCase(caracter);
                    }
                    if ((caracter + "").matches("[0-9]")) {
                        e.setKeyChar(caracter);                        
                    } else {
                        if (caracter == 'F') {
                            e.setKeyChar(caracter);
                        } else {
                            e.consume();
                        }
                    }
                }
            });           
            
            
        }
        return TxtCantidad;
    }

    /**
     * This method initializes TxtPrecio
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtPrecio() {
        if (TxtPrecio == null) {
            TxtPrecio = new JTextField();
            TxtPrecio.setSize(new Dimension(88, 23));
            TxtPrecio.setText("0.00");
            TxtPrecio.setHorizontalAlignment(JTextField.RIGHT);
            TxtPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
            TxtPrecio.setLocation(new Point(270, 43)); // 430,43
            TxtPrecio.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == 27) {
                        dispose();
                    }
                }
            });
        }
        return TxtPrecio;
    }
    
    
    

    private void cargar() {
        
        try {
            listDatosBotiquin.removeAll(listDatosBotiquin);
            int Empaq = 0;
            double PrecioFrac = 0.0;
            int Ebotiquin = 0;
            
            
            String ProductoCod=producPrecio.getProductoBotica().getProducto().getIdProducto();
            String Idbot = producPrecio.getId_Botica();
            listDatosBotiquin = objproducto.ListarDatosBotiquin(Idbot, ProductoCod);
            
            
            
            for (int i = 0; i < listDatosBotiquin.size(); i++) {
                Empaq = listDatosBotiquin.get(i).getEmpaque(); 
                Ebotiquin = listDatosBotiquin.get(i).getEsBotiquin(); 
                        
            }                    
                    
            if (Empaq == 0){
                Empaq = 1;
            }
            
            
            
            String recupera = mantProduc.Recupera_MensajeProducto(ProductoCod).toString().trim();
            //double PrecioVenta = objproducto.Recupera_PrecioVenta(ProductoCod,5);
            double PrecioVenta = 0.0;
            
            if (recupera == null || recupera.equals("")){
                recupera = "";
            }
            //BigDecimal MuestraPrecio = new BigDecimal(String.valueOf(producPrecio.getPVPX()));
            BigDecimal bdMuestraPrecio = new BigDecimal(PrecioVenta);
            bdMuestraPrecio = bdMuestraPrecio.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
            double Muestra_Precio = bdMuestraPrecio.doubleValue();           
            
            
            TxtArticulo.setText(producPrecio.getProductoBotica().getProducto().getDescripcion() + " - " + producPrecio.getProductoBotica().getProducto().getLaboratorio().getId_Lab());
            //TxtPrecio.setText(String.valueOf(producPrecio.getPVPX()));
            TxtPrecio.setText(String.valueOf(bdMuestraPrecio));
            TxtCantidad.setText(String.valueOf(""));
            TxtMensaje.setText(recupera);
            TxtTotal.setText("0.00");
            
            double PBlister = objproducto.Recupera_PrecioVenta(ProductoCod,7);
            
            //if (Ebotiquin > 0){
            if (Ebotiquin > 0 ){
                
                //String prebo = String.valueOf(producPrecio.getprecbotiquin());
                double PBotiquin = objproducto.Recupera_PrecioVenta(ProductoCod,6);
                //Double.parseDouble(prebo);
                
                BigDecimal bdBotiquin = new BigDecimal(PBotiquin);
                bdBotiquin = bdBotiquin.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                double PrecioVenta_1 = objproducto.Recupera_PrecioVenta(ProductoCod,5);
                
                PrecioFrac = PrecioVenta_1 / Empaq;
                        
                BigDecimal bd_fra = new BigDecimal(PrecioFrac);
                bd_fra = bd_fra.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                
                BigDecimal bd_Blister = new BigDecimal(PBlister);
                bd_Blister = bd_Blister.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                
            
                TxtPrecioEmpaque.setText(" Precio Empaque :   " + bdBotiquin);
                if (PBlister > 0){
                TxtPrecioBlister.setText(" Precio Blister     :   "+ bd_Blister);  
                }else{
                   TxtPrecioBlister.setVisible(false);
                }
                if (PBlister == 0){
                TxtPrecioFraccion.setText(" Precio Fracción  :    " + bd_fra);
                }
            
            }else if (PBlister > 0){
                
                //String prebo = String.valueOf(producPrecio.getprecbotiquin());
                double PBotiquin = objproducto.Recupera_PrecioVenta(ProductoCod,5);
                //Double.parseDouble(prebo);
                
                BigDecimal bdBotiquin = new BigDecimal(PBotiquin);
                bdBotiquin = bdBotiquin.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                double PrecioVenta_1 = objproducto.Recupera_PrecioVenta(ProductoCod,5);
                
                PrecioFrac = PrecioVenta_1 / Empaq;
                        
                BigDecimal bd_fra = new BigDecimal(PrecioFrac);
                bd_fra = bd_fra.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                
                BigDecimal bd_Blister = new BigDecimal(PBlister);
                bd_Blister = bd_Blister.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                
            
                TxtPrecioEmpaque.setText(" Precio Empaque :   " + bdBotiquin);
                if (PBlister > 0){
                TxtPrecioBlister.setText(" Precio Blister     :   "+ bd_Blister);  
                }else{
                   TxtPrecioBlister.setVisible(false);
                }
                if (PBlister == 0){
                TxtPrecioFraccion.setText(" Precio Fracción  :    " + bd_fra);
                }
            
            }else{
                TxtPrecioEmpaque.setVisible(false);
                TxtPrecioBlister.setVisible(false);
                TxtPrecioFraccion.setVisible(false);
            }
            
        } catch (Exception ex) {            
        }
        
    }

    private void Cantidad() {
        if (TxtCantidad.getText().trim().compareTo("") != 0) { 
            
            
            try {
                listDatosBotiquin.removeAll(listDatosBotiquin);
                int _Ebotiquin = 0;
                double precio =0.0;
                double _PBotiquinNormal =0.0;
                
                
                String _ProductoCod=producPrecio.getProductoBotica().getProducto().getIdProducto();
                String _Idbot = producPrecio.getId_Botica();
                int _Blister = producPrecio.getProductoBotica().getProducto().getBlister();
                
                
                listDatosBotiquin = objproducto.ListarDatosBotiquin(_Idbot, _ProductoCod);
                for (int i = 0; i < listDatosBotiquin.size(); i++) {
                    _Ebotiquin = listDatosBotiquin.get(i).getEsBotiquin();
                    
                }               
                
                cantidad = TxtCantidad.getText().trim();
                
                /*gino*/
                char LetraBuscar = 'F';
                int posi = 0;
                
                for(int i=0; i < cantidad.length(); i++ ){                    
                    if( cantidad.charAt(i)== LetraBuscar){
                        posi = i;
                    }
                }
                /*gino*/
                
                if (cantidad.compareTo("0") != 0) {
                    
                    if (posi == 0 ){
                        //aqui
                        int Empaque=producPrecio.getProductoBotica().getProducto().getEmpaque();
                        if (Empaque == 0){
                            Empaque = 1;
                        }
                        
                        String[] parts = cantidad.split("F");
                        int cuentaParrtes = parts.length;
                        
                        if (cuentaParrtes >1){                                  //fracciones
                            String part0 = parts[0];
                            String part1 = parts[1];
                            String simbolo = "";
                            
                            if (opcion == 1){ //prof / genera vta
                                
                            
                                if (_Blister > 0){                                 


                                    if (Integer.valueOf(part1) < Empaque){

                                        int _cant = Integer.parseInt(part1);

                                        if (_Blister > 0){


                                            if (esMultiplo(_cant,_Blister)){



                                            ingresadet = true;
                                            setIngresadet(ingresadet);

                                            if (_Blister > 0){
                                                _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,7);
                                                Empaque = _Blister;
                                            }else{
                                                _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,5); 
                                                Empaque = Empaque;
                                            }
                                            precio = _PBotiquinNormal;
                                            //precio = Double.valueOf(TxtPrecio.getText().trim());                                
                                            double cant = Double.valueOf(part1);
                                            double tot =  cant * (precio/Empaque);

                                            BigDecimal bd_tot = new BigDecimal(tot);
                                            bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                            tot = bd_tot.doubleValue();

                                            BigDecimal bd_preNormal = new BigDecimal(precio);
                                            bd_preNormal = bd_preNormal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

                                            TxtPrecio.setText(String.valueOf(bd_preNormal));
                                            TxtTotal.setText(String.valueOf(bd_tot));
                                            TxtTotal.requestFocus();

                                            //dispose();
                                            }else{
                                                JOptionPane.showMessageDialog(this, " Producto solo se puede vender en Blister ", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }else{

                                            ingresadet = true;
                                            setIngresadet(ingresadet);


                                            _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,5); 
                                            Empaque = Empaque;

                                            precio = _PBotiquinNormal;
                                            //precio = Double.valueOf(TxtPrecio.getText().trim());                                
                                            double cant = Double.valueOf(part1);
                                            double tot =  cant * (precio/Empaque);

                                            BigDecimal bd_tot = new BigDecimal(tot);
                                            bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                            tot = bd_tot.doubleValue();

                                            BigDecimal bd_preNormal = new BigDecimal(precio);
                                            bd_preNormal = bd_preNormal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

                                            TxtPrecio.setText(String.valueOf(bd_preNormal));
                                            TxtTotal.setText(String.valueOf(bd_tot));
                                            TxtTotal.requestFocus();
                                        }

                                    }else{
                                        JOptionPane.showMessageDialog(this, " La cantidad no puede ser mayor al empaque ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }else{

                                    if (Integer.valueOf(part1) <= Empaque){

                                        int _cant = Integer.parseInt(part1);

                                        if (_Blister > 0){


                                            if (esMultiplo(_cant,_Blister)){



                                            ingresadet = true;
                                            setIngresadet(ingresadet);

                                            if (_Blister > 0){
                                                _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,7);
                                                Empaque = _Blister;
                                            }else{
                                                _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,5); 
                                                Empaque = Empaque;
                                            }
                                            precio = _PBotiquinNormal;
                                            //precio = Double.valueOf(TxtPrecio.getText().trim());                                
                                            double cant = Double.valueOf(part1);
                                            double tot =  cant * (precio/Empaque);

                                            BigDecimal bd_tot = new BigDecimal(tot);
                                            bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                            tot = bd_tot.doubleValue();

                                            BigDecimal bd_preNormal = new BigDecimal(precio);
                                            bd_preNormal = bd_preNormal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

                                            TxtPrecio.setText(String.valueOf(bd_preNormal));
                                            TxtTotal.setText(String.valueOf(bd_tot));
                                            TxtTotal.requestFocus();

                                            //dispose();
                                            }else{
                                                JOptionPane.showMessageDialog(this, " Producto solo se puede vender en Blister ", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }else{

                                            ingresadet = true;
                                            setIngresadet(ingresadet);


                                            _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,5); 
                                            Empaque = Empaque;

                                            precio = _PBotiquinNormal;
                                            //precio = Double.valueOf(TxtPrecio.getText().trim());                                
                                            double cant = Double.valueOf(part1);
                                            double tot =  cant * (precio/Empaque);

                                            BigDecimal bd_tot = new BigDecimal(tot);
                                            bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                            tot = bd_tot.doubleValue();

                                            BigDecimal bd_preNormal = new BigDecimal(precio);
                                            bd_preNormal = bd_preNormal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

                                            TxtPrecio.setText(String.valueOf(bd_preNormal));
                                            TxtTotal.setText(String.valueOf(bd_tot));
                                            TxtTotal.requestFocus();
                                        }

                                    }else{
                                        JOptionPane.showMessageDialog(this, " La cantidad no puede ser mayor al empaque ", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            
                            }else{   //Inv
                                
                                
                                if (Integer.valueOf(part1) <= Empaque){

                                    int _cant = Integer.parseInt(part1);
                                        
                                    ingresadet = true;
                                    setIngresadet(ingresadet);


                                    _PBotiquinNormal = objproducto.Recupera_PrecioVenta(_ProductoCod,5); 
                                    Empaque = Empaque;

                                    precio = _PBotiquinNormal;
                                    //precio = Double.valueOf(TxtPrecio.getText().trim());                                
                                    double cant = Double.valueOf(part1);
                                    double tot =  cant * (precio/Empaque);

                                    BigDecimal bd_tot = new BigDecimal(tot);
                                    bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                                    tot = bd_tot.doubleValue();

                                    BigDecimal bd_preNormal = new BigDecimal(precio);
                                    bd_preNormal = bd_preNormal.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);

                                    TxtPrecio.setText(String.valueOf(bd_preNormal));
                                    TxtTotal.setText(String.valueOf(bd_tot));
                                    TxtTotal.requestFocus();
                                        

                                }else{
                                        JOptionPane.showMessageDialog(this, " La cantidad no puede ser mayor al empaque ", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                                
                            
                        }else{                                                  //unidades
                            ingresadet = true;
                            setIngresadet(ingresadet);
                            
                            if (_Ebotiquin > 0 ){                
                                
                                //String _prebo = String.valueOf(producPrecio.getprecbotiquin());                                
                                //double _PBotiquin = Double.parseDouble(_prebo);
                                double _PBotiquin = objproducto.Recupera_PrecioVenta(_ProductoCod,6);                                
                                precio = _PBotiquin;
                            }else if (_Blister > 0){
                                double _PBotiquin = objproducto.Recupera_PrecioVenta(_ProductoCod,5);                                
                                precio = _PBotiquin;
                            }else{
                                double PrecioVenta = objproducto.Recupera_PrecioVenta(_ProductoCod,5);
                                //precio = Double.valueOf(TxtPrecio.getText().trim());
                                precio = PrecioVenta;
                            }
                            double cant = Double.valueOf(TxtCantidad.getText().trim());
                            double tot =  cant * precio;
                            
                            BigDecimal bd_tot = new BigDecimal(tot);
                            bd_tot = bd_tot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                            
                            BigDecimal bd_preBot = new BigDecimal(precio);
                            bd_preBot = bd_preBot.setScale(podecimalPantalla, BigDecimal.ROUND_HALF_UP);
                            
                            TxtPrecio.setText(String.valueOf(bd_preBot));                            
                            TxtTotal.setText(String.valueOf(bd_tot));
                            TxtTotal.requestFocus();
                            //dispose();
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(this, " Porfavor Ingrese una Cantidad Correcta sin Fraccion ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // ---- lo q estaba
                    /*ingresadet = true;
                    setIngresadet(ingresadet);
                    dispose();*/
                } else {
                    JOptionPane.showMessageDialog(this, " Porfavor Ingrese una Cantidad Correcta ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CodigoBarrasCupon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static boolean esMultiplo(int n1,int n2){
        if (n1%n2==0)
            return true;
    	else
            return false;
    }
    
}  //  @jve:decl-index=0:visual-constraint="10,10"

