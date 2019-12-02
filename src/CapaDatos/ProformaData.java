package CapaDatos;

import entidad.Proforma;
import entidad.TipoVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.math.*;
import CapaLogica.LogicaProducto;

/**
 *
 * @author Miguel Gomez S.
 */
public class ProformaData {

    Connection conex;
    ResultSet rs;
    Statement stm;
    PreparedStatement st;
    List<Proforma> listproforma = new ArrayList<Proforma>();
    private int podecimal = 4;
    LogicaProducto objProducto = new LogicaProducto();
    private ConexionPool db;

    public ProformaData() {
        db = new ConexionPool();
    }

    public String ProformaRealizada(Proforma objProforma, List<Proforma> listaproductos, String moneda, int cantbolsa) {
        String resultado = null;
        double total = 0.0, SubTotal = 0.0, resto = 0,igvporcentaje=0.0,opgravada=0.0,opexonerada=0.0,
                opinafecta=0.0,opgratuita=0.0,opisc=0.0,OpBonificacion=0.0,OpDescuentoTotal = 0.0,OpDescuento=0.0,OpDescuento_aux=0.0,OpDescuento_aux_Detalle=0.0,preciounitario=0.0,
                opgravadaDetalle=0.0,opexoneradaDetalle=0.0,opinafectaDetalle =0.0,opiscDetalle=0.0,OpBonificacionDetalle =0.0,
                opDescuentoDetalle=0.0,opgratuitaDetalle;

        try {
            total = 0;
            SubTotal = 0;
            igvporcentaje = 0;
            
            for (int i = 0; i < listaproductos.size(); i++) {

                String Esgratuita = listaproductos.get(i).getesgratuita();
                String EsgratuitaPromo = listaproductos.get(i).getesgratuitaPromo();

                total = total + listaproductos.get(i).getTotal();
                SubTotal = SubTotal + listaproductos.get(i).getSubTotal();
                //idOperadorRec = listaproductos.get(i).getIdOperadorrec();
                igvporcentaje = igvporcentaje + listaproductos.get(i).getIGV();


                if (EsgratuitaPromo.equals("1") ){

                    opgravada = objProforma.getOpGravada();
                    opexonerada = 0;
                    opinafecta = 0;
                    opisc = 0;
                    OpBonificacion = 0;
                    OpDescuento = 0;
                    OpDescuento_aux = OpDescuento_aux + listaproductos.get(i).getTotEsGratuita();
                    opgratuita = OpDescuento_aux;

                }else{

                    opgravada = listaproductos.get(i).getOpGravada();
                    opexonerada = opexonerada + listaproductos.get(i).getOpExonerada();
                    opinafecta = opinafecta + listaproductos.get(i).getOpInafecta();
                    opisc = listaproductos.get(i).getOpISC();
                    OpBonificacion = 0.0;//OpBonificacion + listaproductos.get(i).getOpBonificacion();
                    OpDescuento = 0.0;//OpDescuento +listaproductos.get(i).getOpDescuento();
                    opgratuita = opgratuita + listaproductos.get(i).getTotEsGratuita();//.getOpGratuita();
                }
            }

            if (opinafecta > 0){
                opgravada = opgravada -opinafecta;
            }

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_PROFORMA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedure.setString(1, objProforma.getId_Boticas());
            procedure.setInt(2, objProforma.getId_Cliente());
            procedure.setInt(3, objProforma.getId_TipoPago());
            procedure.setString(4, objProforma.getId_Tipo_Precio());
            procedure.setInt(5, objProforma.getId_Tipo_Venta());
            procedure.setString(6, objProforma.getId_Medico());
            procedure.setDate(7, (Date) objProforma.getFecha_Venta());
            procedure.setDouble(8, objProforma.getSubTotal());
            procedure.setDouble(9, objProforma.getIGV());
            procedure.setDouble(10, objProforma.getTotal());
            //procedure.setDouble(11, objProforma.getOpGravada());
            procedure.setDouble(11, opgravada);
            //procedure.setDouble(12, objProforma.getOpExonerada());
            procedure.setDouble(12, opexonerada);
            //procedure.setDouble(13, objProforma.getOpInafecta());
            procedure.setDouble(13, opinafecta);
            //procedure.setDouble(14, objProforma.getOpGratuita());
            procedure.setDouble(14, opgratuita);
            procedure.setDouble(15, objProforma.getOpISC());
            procedure.setDate(16, (Date) objProforma.getFecha_Registro());
            procedure.setInt(17, objProforma.getId_Personal_Botica_Venta());
            procedure.setString(18, objProforma.getNombre_RazonSocial());
            procedure.setString(19, objProforma.getDNI());
            procedure.setString(20, objProforma.getDireccion());
            procedure.setString(21, objProforma.getRUC());
            procedure.setString(22, objProforma.getColegiatura());
            procedure.setDouble(23, objProforma.getigvventa());
            procedure.setString(24, moneda);
            procedure.setDouble(25, objProforma.getRedondeo());
            procedure.setDouble(26, objProforma.getTotFinal());
            procedure.setString(27, objProforma.getcupon());
            procedure.setInt(28, cantbolsa);
// System.out.println(procedure);
                    

            rs = procedure.executeQuery();
            rs.next(); //para recuperar el id de proforma

            /*
             * INSERTANDO EN EL DETALLE
             */

            String idproforma = rs.getString(1);
            String inserta = "INSERT INTO PROFORMA_DETALLE (Id_Proforma, Id_Producto, UNIDAD, FRACCION, Precio_Venta, Descuento, PVX, Total, Op_Gravada, IGV, Op_Exonerada, Op_Inafecta, Op_gratuita, Op_ISC, orden_producto,Id_Producto_Recarga,fracnum,EsgratuitaPromo,CertificadoMedico) VALUES ";
            /*double opgravada = 0.0;
            double opexonerada = 0.0;
            double opinafecta = 0.0;
            double opgratuita = 0.0;
            double opisc = 0.0;*/
            double igv = 0.0;
            double auxparcial = 0.0;
            //double total = 0.0;
            double subtotal = 0.0;
            double igvVenta = 0.0;
            double miigv = 0.0;
            double fracnum = 0.0;
            
            for (int i = 0; i < listaproductos.size(); i++) {
                igv = listaproductos.get(i).getIGV();
                 total = listaproductos.get(i).getTotal();
                /*if (igv == 0) {
                    //opgravada = total;
                    opinafecta = total;
                } else {
                    auxparcial = (total / (1 + (igv / 100)));
                    BigDecimal bd1 = new BigDecimal(auxparcial);
                    bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                    auxparcial = bd1.doubleValue();
                    opgravada = auxparcial;
                }
                BigDecimal bd1 = new BigDecimal(opgravada);
                bd1 = bd1.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                opgravada = bd1.doubleValue();*/
                igvVenta = listaproductos.get(i).getTotal() - listaproductos.get(i).getSubTotal();
                subtotal = listaproductos.get(i).getSubTotal();

                /*if (igv !=0){
                    miigv = total - opgravada;
                }else{
                    opgravada = 0.0;
                }*/
                if (igvVenta == 0){
                   subtotal = 0.0;
                }

                String Esgratuita = listaproductos.get(i).getesgratuita();
                String EsgratuitaPromo = listaproductos.get(i).getesgratuitaPromo();

                if (EsgratuitaPromo.equals("1") ){

                    opgravadaDetalle = 0;
                    opexoneradaDetalle = 0;
                    opinafectaDetalle = 0;
                    opiscDetalle = 0;
                    OpBonificacionDetalle = 0;
                    opDescuentoDetalle = 0;
                    OpDescuento_aux_Detalle = listaproductos.get(i).getTotEsGratuita();
                    opgratuitaDetalle = listaproductos.get(i).getTotEsGratuita();

                }else{

                    opgravadaDetalle = subtotal;
                    opexoneradaDetalle = listaproductos.get(i).getOpExonerada();
                    opinafectaDetalle = listaproductos.get(i).getOpInafecta();
                    opiscDetalle = listaproductos.get(i).getOpISC();
                    OpBonificacionDetalle = 0.0;//OpBonificacion + listaproductos.get(i).getOpBonificacion();
                    opDescuentoDetalle = 0.0;//OpDescuento +listaproductos.get(i).getOpDescuento();
                    opgratuitaDetalle = listaproductos.get(i).getTotEsGratuita();//.getOpGratuita();
                }

                BigDecimal bd3 = new BigDecimal(igvVenta);
                bd3 = bd3.setScale(podecimal, BigDecimal.ROUND_HALF_UP);
                miigv = bd3.doubleValue();


                String empaque = objProducto.RetornaEmpaque(objProforma.getId_Boticas(),listaproductos.get(i).getIdproducto());
                double unidad = Integer.valueOf((listaproductos.get(i).getUNIDAD()));
                double fraccion = Integer.valueOf(listaproductos.get(i).getFRACCION());

                if (fraccion != 0){
                    fracnum = (unidad + (fraccion /Integer.parseInt(empaque)));
                }else{
                    fracnum = unidad;
                }

                //inserta += "('" + idproforma + "','" + listaproductos.get(i).getIdproducto() + "','" + String.valueOf(listaproductos.get(i).getUNIDAD()) + "','" + String.valueOf(listaproductos.get(i).getFRACCION()) + "','" + String.valueOf(listaproductos.get(i).getPrecio_Venta()) + "','" + String.valueOf(listaproductos.get(i).getDescuento()) + "','" + listaproductos.get(i).getPvx() + "','" + listaproductos.get(i).getTotal() + "','" + opgravada + "','" + miigv + "','" + opexonerada + "','" + opinafecta + "','" + opgratuita + "','" + opisc + "','" + listaproductos.get(i).getOrdenProducto() + "','" + listaproductos.get(i).getidOperadorRec()+ "','" + fracnum + "')" + ",";
                inserta += "('" + idproforma + "','" + listaproductos.get(i).getIdproducto() + "','" + String.valueOf(listaproductos.get(i).getUNIDAD()) + "','" + String.valueOf(listaproductos.get(i).getFRACCION()) + "','" + String.valueOf(listaproductos.get(i).getPrecio_Venta()) + "','" + String.valueOf(listaproductos.get(i).getDescuento()) + "','" + listaproductos.get(i).getPvx() + "','" + listaproductos.get(i).getTotal() + "','" + opgravadaDetalle + "','" + igvVenta + "','" + opexoneradaDetalle + "','" + opinafectaDetalle + "','" + opgratuitaDetalle + "','" + opiscDetalle + "','" + listaproductos.get(i).getOrdenProducto() + "','" + listaproductos.get(i).getidOperadorRec()+ "','" + fracnum + "','" + EsgratuitaPromo + "','" + listaproductos.get(i).getCertMed() + "')" + ",";

                igvVenta = 0.0;
            }

            inserta = inserta.substring(0, inserta.length() - 1);
            st = conex.prepareStatement(inserta);
            st.executeUpdate();
            conex.commit();
            resultado = idproforma;

        } catch (SQLException ex) {
            try {
                System.out.println("ERROR EL INSERTAR EN LA PRODFORMA SE HIZO UN ROLLBACKxxxxxxxx: " + ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println("ERRROR ROLLBACK:" + ex1.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("ERROR EL INSERTAR EN LA PRODFORMA: " + e.toString());
            return null;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public String ValidarProformaRealizada(String proforma) {

        String montovalidado = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_VERIFICAPROFORMA(?)}");
            procedure.setString("PROFORMA", proforma);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            montovalidado = rs.getString(2);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error en MONTOS VALIDADO DE LA PRODFORMA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return montovalidado;

    }

    public int insertaCupon(String proforma,String cupon) {

        int id_validar = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_INSERTACUPON(?,?)}");
            procedure.setString("PROFORMA", proforma);
            procedure.setString("NUMEROCUPON", cupon);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_validar = rs.getInt(2);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase SP_INSERTACUPON :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return id_validar;

    }
    
    public String ValidarProformaModificada(String proforma) {
        String montovalidado1 = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_VERIFICAPROFORMA(?)}");
            procedure.setString("PROFORMA", proforma);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            montovalidado1 = rs.getString(2);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error en MONTOS VALIDADO DE LA PRODFORMA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return montovalidado1;

    }

    public String MontoProformaRealizada(String proforma) {

        String mprofor = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_MONTOPROFORMA(?)}");
            procedure.setString("PROFORMA", proforma);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            mprofor = rs.getString(2);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error en MONTOS DE LA PRODFORMA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return mprofor;        

    }

    public int insertavalidaProformaRealizada(String proforma) {

        int id_validar = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_INSERTAVALIDARPROFORMA(?)}");
            procedure.setString("PROFORMA", proforma);
            
            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            id_validar = rs.getInt(2);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase SP_INSERTAVALIDARPROFORMA :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return id_validar;

    }
    public int VerificaCajaFeriado(String fecha) {

        int feriado = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_CAJAVERIFICA_FERIADO(?)}");
            procedure.setString("FECHADIA", fecha);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            feriado = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase VERIFICAFERIADO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return feriado;

    }

    public int VerificaRolAcceso(int rol, int opc) {

        int Acceso = 0;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_VERIFICAROL(?,?)}");
            procedure.setInt("ROLACCESO", rol);
            procedure.setInt("OPCACCESO", opc);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            Acceso = rs.getInt(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase ROL FERIADO :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return Acceso;

    }
   public String RecuperaNumero(String idbotica) {

        String numero = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call SP_RETORNANUMERO()}");
            //procedure.setString("IDBOTICA", idbotica);

            procedure.execute();
            rs = procedure.getResultSet();
            rs.next();
            conex.commit();
            numero = rs.getString(1);
            rs.close();


        } catch (Exception ex) {
            System.out.println("Error CapaDatos clase retorna numero :" + ex.toString());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }

        }

        return numero;

    }


    public String ProformaRealizadaSoat(Proforma objProforma){//, List<Proforma> listaproductos) {
        String resultado = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_PROFORMA_SOAT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedure.setString(1, objProforma.getId_Boticas());
            procedure.setInt(2, objProforma.getId_Cliente());
            procedure.setInt(3, objProforma.getId_TipoPago());
            procedure.setInt(4, objProforma.getId_Tipo_Venta());
            procedure.setDate(5, (Date) objProforma.getFecha_Venta());
            procedure.setDate(6, (Date) objProforma.getFecha_Registro());
            procedure.setInt(7, objProforma.getId_Personal_Botica_Venta());
            procedure.setString(8, objProforma.getNombre_RazonSocial());
            procedure.setString(9, objProforma.getDNI());
            procedure.setString(10, objProforma.getDireccion());
            procedure.setString(11, objProforma.getRUC());
            procedure.setString(12, objProforma.getTelefono());
            procedure.setInt(13, objProforma.getclase());
            procedure.setInt(14, objProforma.gettipo());
            procedure.setInt(15, objProforma.getmarca());
            procedure.setInt(16, objProforma.getmodelo());
            procedure.setString(17, objProforma.getasientos());
            procedure.setInt(18, objProforma.getzona());
            procedure.setString(19, objProforma.getuso());
            procedure.setDouble(20, objProforma.getprecio());
            procedure.setString(21, objProforma.getanio());
            procedure.setString(22, objProforma.getserie());
            procedure.setString(23, objProforma.getplaca());
            procedure.setString(24, objProforma.getpoliza());
            procedure.setString(25, objProforma.getcertificado());
            procedure.setString(26, objProforma.getdep());
            procedure.setString(27, objProforma.getprov());
            procedure.setString(28, objProforma.getdist());
            procedure.setString(29, objProforma.getfecha1());
            procedure.setString(30, objProforma.getfecha2());
            procedure.setString(31, objProforma.getcomision());


            rs = procedure.executeQuery();
            rs.next(); //para recuperar el id de proforma

            /*
             * INSERTANDO EN EL DETALLE
             */

            String idproforma = rs.getString(1);
            /*String inserta = "INSERT INTO PROFORMA_DETALLE (Id_Proforma, Id_Producto, UNIDAD, FRACCION, Precio_Venta, Descuento, PVX, Total) VALUES ";

            for (int i = 0; i < listaproductos.size(); i++) {
                inserta += "('" + idproforma + "','" + listaproductos.get(i).getIdproducto() + "','" + String.valueOf(listaproductos.get(i).getUNIDAD()) + "','" + String.valueOf(listaproductos.get(i).getFRACCION()) + "','" + String.valueOf(listaproductos.get(i).getPrecio_Venta()) + "','" + String.valueOf(listaproductos.get(i).getDescuento()) + "','" + listaproductos.get(i).getPvx() + "','" + listaproductos.get(i).getTotal() + "')" + ",";
            }

            inserta = inserta.substring(0, inserta.length() - 1);
            st = conex.prepareStatement(inserta);
            st.executeUpdate();*/
            conex.commit();
            resultado = idproforma;

        } catch (SQLException ex) {
            try {
                System.out.println("ERROR EL INSERTAR EN LA PROFORMA SE HIZO UN ROLLBACK: MSJ1. CAPADATOS->PROFORMADATA " + ex.getMessage());
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println("ERRROR ROLLBACK:" + ex1.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("ERROR EL INSERTAR EN LA PRODFORMA:: MSJ2. CAPADATOS->PROFORMADATA " + e.toString());
            return null;
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    public String Realiza_Proforma(Proforma objProforma, List<Proforma> listaproductos) {
        String resultado = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            //System.out.println("{ call GUARDA_PROFORMA_INV ('"+objProforma.getId_Boticas()+"','"+objProforma.getId_Cliente()+"','"+objProforma.getId_Tipo_Venta()+"','"+objProforma.getTotal()+"','"+objProforma.getId_Personal_Botica_Venta()+"','"+ objProforma.getNombre_RazonSocial()+"','"+ objProforma.getDNI()+"','"+ objProforma.getDireccion()+"')}");
            CallableStatement procedure = conex.prepareCall("{ call GUARDA_PROFORMA_INV (?,?,?,?,?,?,?,?)}");
            procedure.setString(1, objProforma.getId_Boticas());
            procedure.setInt(2, objProforma.getId_Cliente());
            procedure.setInt(3, objProforma.getId_Tipo_Venta());
            procedure.setDouble(4, objProforma.getTotal());
            procedure.setInt(5, objProforma.getId_Personal_Botica_Venta());
            procedure.setString(6, objProforma.getNombre_RazonSocial());
            procedure.setString(7, objProforma.getDNI());
            procedure.setString(8, objProforma.getDireccion());
            rs = procedure.executeQuery();
            rs.next();
            String idproforma = rs.getString(1);

            CallableStatement procedure1;

            for (int i = 0; i < listaproductos.size(); i++) {
                procedure1 = conex.prepareCall("{ call GUARDA_DETALLE_PROFORMA (?,?,?)}");
                procedure1.setString(1, idproforma);
                procedure1.setString(2, listaproductos.get(i).getIdproducto());
                procedure1.setDouble(3, listaproductos.get(i).getTotal());
                procedure1.execute();
            }


            conex.commit();
            resultado = idproforma;


        } catch (SQLException ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
            }

            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resultado;

    }

    /*
     * METODO QUE RECUPERA MI PROFORMA
     */
    public List<Proforma> Recupera_Proforma(String idproforma, String idbotica) throws SQLException {
        listproforma.removeAll(listproforma);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_CABECERA_PROFORMA(?,?) }");
            procedure.setString(1, idproforma);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();


            while (rs.next()) {

                if (rs.getInt(1) != -1) {
                    listproforma.add(new Proforma(rs.getInt("Id_Cliente"), rs.getString("RUC_DNI"), rs.getString("Nombre_RazonSocial"),
                            rs.getString("Direccion"), rs.getString("DNI"), rs.getInt("Id_TipoPago"), rs.getString("DescripcionTipoPago"), rs.getInt("Id_Tipo_Venta"), rs.getString(9),
                            rs.getDate("Fecha_Venta"), rs.getInt("Id_Personal_Botica_Venta"), rs.getString("Nombre"), rs.getString("Apellido"),
                            rs.getDouble("SubTotal"), rs.getDouble("IGV"), rs.getDouble("Total"), rs.getDate("Fecha_Registro"), rs.getString("NomCliente"),
                            rs.getString("DNIAUX"), rs.getString("DIRECCIONPROF"), rs.getString("Id_Tipo_Precio"),
                            rs.getString("Id_Medico"), rs.getString("Id_Proforma"),
                            rs.getString("IDPERSONAL"), rs.getString("RUC"), rs.getInt("Id_Empresa"), rs.getString("Telefono"),
                            rs.getDouble("Op_Gravada"),rs.getDouble("Op_Inafecta"),rs.getDouble("Op_Gratuita"),rs.getDouble("Op_Exonerada"),rs.getDouble("Op_ISC"), 
                            rs.getString("Cupon"),rs.getInt("cantbolsas")));
                } else {
                    listproforma.removeAll(listproforma);
                    return listproforma;
                }


            }

            rs.close();

        } catch (SQLException ex) {
            try {
                System.out.println("ERROR AL RECUPERAR LA PRODFORMA SE HIZO UN ROLLBACK: " + ex.getMessage());
                conex.rollback();
                return null;
            } catch (Exception e) {
                System.out.println("ERROR AL RECUPERAR LA PRODFORMA : " + e.toString());
                return null;
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listproforma;
    }

    public List<Proforma> Recupera_Proforma_Sensitiva(String idproforma, String idbotica) {
        listproforma.removeAll(listproforma);

        try {

            conex = new ConexionPool().getConnection();            
            CallableStatement procedure = conex.prepareCall("{ call BUSQUEDA_CABECERA_PROFORMA(?,?) }");
            procedure.setString(1, idproforma);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listproforma.add(new Proforma(rs.getInt("Id_Cliente"), rs.getString("RUC_DNI"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getString("DNI"), rs.getInt("Id_TipoPago"), rs.getString("DescripcionTipoPago"), rs.getInt("Id_Tipo_Venta"), rs.getString(9),
                        rs.getDate("Fecha_Venta"), rs.getInt("Id_Personal_Botica_Venta"), rs.getString("Nombre"), rs.getString("Apellido"),
                        rs.getDouble("SubTotal"), rs.getDouble("IGV"), rs.getDouble("Total"), rs.getDate("Fecha_Registro"), rs.getString("NomCliente"),
                        rs.getString("DNIAUX"), rs.getString("DIRECCIONPROF"), rs.getString("Id_Tipo_Precio"),
                        rs.getString("Id_Medico"), rs.getString("Id_Proforma"),
                        rs.getString("IDPERSONAL"), rs.getString("RUC"), rs.getInt("Id_Empresa"), rs.getString("Telefono"),
                        rs.getDouble("Op_Gravada"),rs.getDouble("Op_Inafecta"),rs.getDouble("Op_Gratuita"),rs.getDouble("Op_Exonerada"),rs.getDouble("Op_ISC"), 
                        rs.getString("Cupon"),rs.getInt("cantbolsas")));

            }

            rs.close();

        } catch (SQLException ex) {
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listproforma;
    }

    public List<Proforma> Recupera_Detalle_Proforma(String idproforma, String idbotica) {
        listproforma.removeAll(listproforma);
        Proforma entiProforma = null;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RECUEPRA_DETALLE_PROFORMA(?,?) }");
            procedure.setString(1, idproforma);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                entiProforma = new Proforma();
                entiProforma.setIdproducto(rs.getString("Id_Producto"));
                entiProforma.setDescipcionproducto(rs.getString("Descripcion"));
                entiProforma.setUNIDAD(rs.getInt("UNIDAD"));
                entiProforma.setFRACCION(rs.getInt("FRACCION"));
                entiProforma.setPrecio_Venta(rs.getDouble("Precio_Venta"));
                entiProforma.setDescuento(rs.getDouble("Descuento"));
                entiProforma.setPvx(rs.getDouble("PVX"));
                entiProforma.setTotal(rs.getDouble("Total"));
                entiProforma.setFecha_Vencimiento(rs.getDate("Fecha_Vencimiento"));
                entiProforma.setIGV_Exonerado(rs.getDouble("IGV_Exonerado"));
                entiProforma.setEmpaque(rs.getInt("Empaque"));
                entiProforma.setStock_Empaque(rs.getInt("Mostrador_Stock_Empaque"));
                entiProforma.setStock_Fraccion(rs.getInt("Mostrador_Stock_Fraccion"));
                entiProforma.setId_laboratorio(rs.getString("LABORATORIO"));
                entiProforma.setId_Tipo_Precio(rs.getString("Id_Tipo_Precio"));
                entiProforma.setOrdenProducto(rs.getString("orden_producto"));
                entiProforma.setidOperadorRec(rs.getInt("Id_Producto_Recarga"));

                entiProforma.setOpGravada(rs.getDouble("Op_Gravada"));
                entiProforma.setOpInafecta(rs.getDouble("Op_Inafecta"));
                entiProforma.setOpGratuita(rs.getDouble("Op_Gratuita"));
                entiProforma.setOpExonerada(rs.getDouble("Op_Exonerada"));
                entiProforma.setOpISC(rs.getDouble("Op_ISC"));
                entiProforma.setEsgratispromo(rs.getInt("EsgratuitaPromo"));
                entiProforma.setCertMed(rs.getString("CertificadoMedico"));
                listproforma.add(entiProforma);
            }

            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPA DATOS Recupera_Detalle_Proforma :" + ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listproforma;

    }

    //METODO PARA MODIFICAR LA PROFORMA
    public boolean Modifica_Proforma(List<Proforma> lista, double totalventa) {
        boolean resul = false;
        CallableStatement procedure = null;

        try {


            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);

            for (int i = 0; i < lista.size(); i++) {
                procedure = conex.prepareCall(" { CALL MODIFICA_PROFORMA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                procedure.setInt(1, i);
                procedure.setDouble(2, totalventa);
                procedure.setDouble(3, lista.get(0).getSubTotal());
                procedure.setString(4, lista.get(i).getId_Boticas());
                procedure.setString(5, lista.get(i).getId_Proforma());
                procedure.setInt(6, lista.get(i).getUNIDAD());
                procedure.setInt(7, lista.get(i).getFRACCION());
                procedure.setDouble(8, lista.get(i).getPrecio_Venta());
                procedure.setDouble(9, lista.get(i).getDescuento());
                procedure.setDouble(10, lista.get(i).getPvx());
                procedure.setDouble(11, lista.get(i).getTotal());
                procedure.setString(12, lista.get(i).getIdproducto());
                procedure.setString(13, lista.get(i).getDNI());
                procedure.setDouble(14, lista.get(i).getDsctoAdicional());
                procedure.execute();

            }
            conex.commit();
            resul = true;

        } catch (SQLException ex) {
            try {
                conex.rollback();
                System.out.println("ERROR CAPA DE DATOS:" + ex.getMessage());
            } catch (SQLException ex1) {
                System.out.println("ERROR CAPA DE DATOS MODOFICA PROFORMA:" + ex.getMessage());
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;
    }

    //METODO PARA MODIFICAR LA VENTA DE SOAT
    public String ModificaVentaRealizadaSoat(String interno, String serievehi,String placavehi, String polizavehi, String certificadovehi) {

        String resul = null;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            //System.out.println("{ call GUARDA_PROFORMA_INV ('"+objProforma.getId_Boticas()+"','"+objProforma.getId_Cliente()+"','"+objProforma.getId_Tipo_Venta()+"','"+objProforma.getTotal()+"','"+objProforma.getId_Personal_Botica_Venta()+"','"+ objProforma.getNombre_RazonSocial()+"','"+ objProforma.getDNI()+"','"+ objProforma.getDireccion()+"')}");
            CallableStatement procedure = conex.prepareCall("{ call MODIFICA_VENTA_SOAT (?,?,?,?,?)}");
                procedure.setString(1, interno);
                procedure.setString(2, serievehi);
                procedure.setString(3, placavehi);
                procedure.setString(4, polizavehi);
                procedure.setString(5, certificadovehi);

            rs = procedure.executeQuery();
            rs.next();
            String idventa = rs.getString(1);

            conex.commit();
            resul = idventa;


        } catch (SQLException ex) {
            try {
                conex.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
            }

            System.out.println(ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return resul;
    }
    
    public List<TipoVenta> Lista_TiposVentas() {
        double monto = 0;
        List<TipoVenta> listaventa = new ArrayList<TipoVenta>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_TIPO_VENTA() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaventa.add(new TipoVenta(rs.getDouble("Monto"), rs.getInt("Id_Tipo_Venta"),
                        rs.getString("DESCRIPCION")));
            }

            rs.close();


        } catch (SQLException ex) {
            System.out.println("ERROR CAPA DE DATOS:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaventa;

    }
    
    public List<TipoVenta> Lista_Colegios() {        
        List<TipoVenta> listacolegio = new ArrayList<TipoVenta>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_COLEGIOS() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listacolegio.add(new TipoVenta(rs.getString("abreviatura"),rs.getString("nombre")));
            }

            rs.close();


        } catch (SQLException ex) {
            System.out.println("ERROR CAPA DE DATOS:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listacolegio;

    }

     public List<TipoVenta> Lista_TiposVentasSOAT() {

     double monto = 0;
     List<TipoVenta> listaventa = new ArrayList<TipoVenta>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_TIPO_VENTASOAT() }");
            procedure.execute();
            rs = procedure.getResultSet();

            while (rs.next()) {
                listaventa.add(new TipoVenta(rs.getDouble("Monto"), rs.getInt("Id_Tipo_Venta"),
                        rs.getString("DESCRIPCION")));
            }

            rs.close();


        } catch (SQLException ex) {
            System.out.println("ERROR CAPA DE DATOS:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listaventa;

    }

    public boolean ModificaProforma(String idproforma, String idboti) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            conex.setAutoCommit(false);
            CallableStatement procedure = conex.prepareCall("{ call ACTIVAR_PROFORMA(?,?) }");
            procedure.setString("IDPROFOR", idproforma);
            procedure.setString("IDBOTI", idboti);
            procedure.execute();
            conex.commit();
            valor = true;


        } catch (SQLException ex) {
            System.out.println("ERROR EN PROFORMA DATA " + ex.getMessage());
        }

        return valor;

    }

    public List<Proforma> Recupera_Proforma_Sensitiva1(String idproforma, String idbotica) throws SQLException {
        listproforma.removeAll(listproforma);

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call BUSQUEDA_CABECERA_PROFORMA1(?,?) }");
            procedure.setString(1, idproforma);
            procedure.setString(2, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listproforma.add(new Proforma(rs.getInt("Id_Cliente"), rs.getString("RUC_DNI"), rs.getString("Nombre_RazonSocial"),
                        rs.getString("Direccion"), rs.getString("DNI"), rs.getInt("Id_TipoPago"), rs.getString("DescripcionTipoPago"), rs.getInt("Id_Tipo_Venta"), rs.getString(9),
                        rs.getDate("Fecha_Venta"), rs.getInt("Id_Personal_Botica_Venta"), rs.getString("Nombre"), rs.getString("Apellido"),
                        rs.getDouble("SubTotal"), rs.getDouble("IGV"), rs.getDouble("Total"), rs.getDate("Fecha_Registro"), rs.getString("NomCliente"),
                        rs.getString("DNIAUX"), rs.getString("DIRECCIONPROF"), rs.getString("Id_Tipo_Precio"),
                        rs.getString("Id_Medico"), rs.getString("Id_Proforma"),
                        rs.getString("IDPERSONAL"), rs.getString("RUC")));

            }

            rs.close();

        } catch (SQLException ex) {
            try {
                System.out.println("ERROR AL RECUPERAR LA PRODFORMA SE HIZO UN ROLLBACK: " + ex.getMessage());
                conex.rollback();
                return null;
            } catch (Exception e) {
                System.out.println("ERROR AL RECUPERAR LA PRODFORMA : " + e.toString());
                return null;
            }
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }


        return listproforma;
    }

    public List<Proforma> Recupera_Proforma_Coasociado(String idproforma) {
        listproforma.removeAll(listproforma);

        try {

            conex = new ConexionPool().getConnection();
            System.out.println("{ call RECUPERA_PROFORMA_COASOCIADO('" + idproforma + "') }");
            CallableStatement procedure = conex.prepareCall("{ call RECUPERA_PROFORMA_COASOCIADO(?) }");
            procedure.setString(1, idproforma);
            rs = procedure.executeQuery();

            while (rs.next()) {
                listproforma.add(new Proforma(rs.getString("Id_Boticas"), rs.getDouble("SubTotal"),
                        rs.getDouble("IGV"), rs.getDouble("Total"), rs.getString("Id_Laboratorio"),
                        rs.getString("Descripcion"), rs.getInt("UNIDAD"), rs.getInt("FRACCION"),
                        rs.getDouble("Total")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPA DATOS RECUPERA_PROFORMA_COASOCIADO :" + ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listproforma;

    }

    public List<Proforma> Lista_Proformas(String idbotica) {

        List<Proforma> milista = new ArrayList<Proforma>();

        try {

            conex = new ConexionPool().getConnection();
            System.out.println("{ call MI_LISTA_PROFORMAS('" + idbotica + "') }");
            CallableStatement procedure = conex.prepareCall("{ call MI_LISTA_PROFORMAS (?) }");
            procedure.setString(1, idbotica);
            rs = procedure.executeQuery();

            while (rs.next()) {
                milista.add(new Proforma(rs.getString("Id_Proforma"), rs.getString("DESCRIPCION"),
                        rs.getDouble("Total"), rs.getString("VENDEDOR"), rs.getString("cliente"), rs.getInt("Atendida")));
            }


            rs.close();

        } catch (Exception ex) {
            System.out.println("ERROR CAPA DATOS RECUPERA_PROFORMA_COASOCIADO :" + ex.getMessage());

        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return milista;
    }

    public boolean Veirifca_Proforma_Vendida(String idbotica, String idproforma) {
        boolean valor = false;

        try {

            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call Verifica_Proforma_Vendida(?,?) }");
            procedure.setString("IDBOTICA", idbotica);
            procedure.setString("IDPROFORMA", idproforma);
            rs = procedure.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                valor = true;
            }


        } catch (SQLException ex) {
            System.out.println("ERROR EN PROFORMA Veirifca_Proforma_Vendida " + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return valor;

    }

    public List<TipoVenta> Lista_TiposVentas_Inventario() {
        List<TipoVenta> listaventa = new ArrayList<TipoVenta>();

        try {
            conex = new ConexionPool().getConnection();
            CallableStatement procedure = conex.prepareCall("{ call RETORNA_TIPO_VENTA () }");
            rs = procedure.executeQuery();

            while (rs.next()) {
                listaventa.add(new TipoVenta(rs.getDouble("Monto"), rs.getInt("Id_Tipo_Venta"),
                        rs.getString("DESCRIPCION")));
            }

            rs.close();


        } catch (SQLException ex) {
            System.out.println("ERROR CAPA DE DATOS:" + ex.getMessage());
        } finally {
            if (null != conex) {
                try {
                    conex.close();
                } catch (SQLException ex) {
                }
            }
        }

        return listaventa;

    }
}
