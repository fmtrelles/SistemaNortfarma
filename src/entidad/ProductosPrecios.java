package entidad;

import java.util.Date;

/**
 *
 * @author Miguel Gomez S.
 */
public class ProductosPrecios {

    private String Id_Botica;
    private Productos_Botica productoBotica;
    private TipoPrecio tipoPrecio;
    private double Precio_Venta;
    private double PVPX;
    private double precioBotiquin;
    private double Descuento_Venta;
    private double Descuento_Adicional;
    private String DescripcionGrupo;
    private int Id_Producto_Grupo;
    private int Grupo_Id;
    private int Id_Promocion;
    private String DESCRIPPROMOCION;
    private Date Fecha_Inicio;
    private Date Fecha_Fin;
    private int PorStock;
    private double PROMOCION;
    private int band_concurso;
    private double IGVExonerado;
    private double Subtotal;
    private int EsBotiquin;
    private double precbotiquin;
    private double botiquinfraccion;
    private double dscto01;
    private double dscto02;
    private int empaque;

    private int IdOperadorRecarga;
    private String DescOperadorRecarga;

    private String GRUPO;
    private String POSICION;
    private String TABOPEN;
    private String CAMPO;
    private String DEFECTO;
    private String DATO;
    private String CIERRE;
    private String TABCLOSE;
    private String VISUALIZA;
    private String VprecBotiquin;
    private String idproducto;

    public ProductosPrecios() {
    }

    public int getEmpaque() {
        return empaque;
    }

    public void setEmpaque(int empaque) {
        this.empaque = empaque;
    }

    

    
    
    public int getEsBotiquin() {
        return EsBotiquin;
    }

    public void setEsBotiquin(int EsBotiquin) {
        this.EsBotiquin = EsBotiquin;
    }

    
    
    
    public ProductosPrecios(double Subtotal, double IGV_Exonerado) {
        this.IGVExonerado = IGV_Exonerado;
        this.Subtotal = Subtotal;
    }


    public String getidproducto() {
        return idproducto;
    }

    public void setidproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdOperadorRecarga() {
        return IdOperadorRecarga;
    }
    public void setIdOperadorRecarga(int IdOperadorRecarga) {
        this.IdOperadorRecarga = IdOperadorRecarga;
    }

    public String getDescOperadorRecarga() {
        return DescOperadorRecarga;
    }

    public void setDescOperadorRecarga(String DescOperadorRecarga) {
        this.DescOperadorRecarga = DescOperadorRecarga;
    }

    public String getVprecBotiquin() {
        return VprecBotiquin;
    }

    public void setVprecBotiquin(String VprecBotiquin) {
        this.VprecBotiquin = VprecBotiquin;
    }




    public double getIGVExonerado() {
        return IGVExonerado;
    }

    public void setIGVExonerado(double IGVExonerado) {
        this.IGVExonerado = IGVExonerado;
    }

    public double getPVPX() {
        return PVPX;
    }

    public void setPVPX(double PVPX) {
        this.PVPX = PVPX;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }
    
    public double getprecbotiquin() {
        return precbotiquin;
    }

    public void setprecbotiquin(double precbotiquin) {
        this.precbotiquin = precbotiquin;
    }

    public double getbotiquinfraccion() {
        return botiquinfraccion;
    }

    public void setbotiquinfraccion(double botiquinfraccion) {
        this.botiquinfraccion = botiquinfraccion;
    }


    public double getdscto01() {
        return dscto01;
    }

    public void setdscto01(double dscto01) {
        this.dscto01 = dscto01;
    }

    public double getdscto02() {
        return dscto02;
    }

    public void setdscto02(double dscto02) {
        this.dscto02 = dscto02;
    }




    public double getPrecioBotiquin() {
        return precioBotiquin;
    }

    public void setPrecioBotiquin(double precioBotiquin) {
        this.precioBotiquin = precioBotiquin;
    }

    public String getDESCRIPPROMOCION() {
        return DESCRIPPROMOCION;
    }

    public void setDESCRIPPROMOCION(String DESCRIPPROMOCION) {
        this.DESCRIPPROMOCION = DESCRIPPROMOCION;
    }

    public String getDescripcionGrupo() {
        return DescripcionGrupo;
    }

    public void setDescripcionGrupo(String DescripcionGrupo) {
        this.DescripcionGrupo = DescripcionGrupo;
    }

    public double getDescuento_Adicional() {
        return Descuento_Adicional;
    }

    public void setDescuento_Adicional(double Descuento_Adicional) {
        this.Descuento_Adicional = Descuento_Adicional;
    }

    public double getDescuento_Venta() {
        return Descuento_Venta;
    }

    public void setDescuento_Venta(double Descuento_Venta) {
        this.Descuento_Venta = Descuento_Venta;
    }

    public Date getFecha_Fin() {
        return Fecha_Fin;
    }

    public void setFecha_Fin(Date Fecha_Fin) {
        this.Fecha_Fin = Fecha_Fin;
    }

    public Date getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(Date Fecha_Inicio) {
        this.Fecha_Inicio = Fecha_Inicio;
    }

    public int getGrupo_Id() {
        return Grupo_Id;
    }

    public void setGrupo_Id(int Grupo_Id) {
        this.Grupo_Id = Grupo_Id;
    }

    public String getId_Botica() {
        return Id_Botica;
    }

    public void setId_Botica(String Id_Botica) {
        this.Id_Botica = Id_Botica;
    }

    public int getId_Producto_Grupo() {
        return Id_Producto_Grupo;
    }

    public void setId_Producto_Grupo(int Id_Producto_Grupo) {
        this.Id_Producto_Grupo = Id_Producto_Grupo;
    }

    public int getId_Promocion() {
        return Id_Promocion;
    }

    public void setId_Promocion(int Id_Promocion) {
        this.Id_Promocion = Id_Promocion;
    }

    public double getPROMOCION() {
        return PROMOCION;
    }

    public void setPROMOCION(double PROMOCION) {
        this.PROMOCION = PROMOCION;
    }

    public int getPorStock() {
        return PorStock;
    }

    public void setPorStock(int PorStock) {
        this.PorStock = PorStock;
    }

    public double getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(double Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public Productos_Botica getProductoBotica() {
        return productoBotica;
    }

    public void setProductoBotica(Productos_Botica productoBotica) {
        this.productoBotica = productoBotica;
    }

    public TipoPrecio getTipoPrecio() {
        return tipoPrecio;
    }

    public void setTipoPrecio(TipoPrecio tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    public int getBand_concurso() {
        return band_concurso;
    }

    public void setBand_concurso(int band_concurso) {
        this.band_concurso = band_concurso;
    }


    public ProductosPrecios(String GRUPO, String POSICION, String TABOPEN, String CAMPO, String DEFECTO,String DATO, String CIERRE,
            String TABCLOSE,String VISUALIZA) {

        this.GRUPO = GRUPO;
        this.POSICION = POSICION;
        this.TABOPEN = TABOPEN;
        this.CAMPO = CAMPO;
        this.DEFECTO = DEFECTO;
        this.DATO = DATO;
        this.CIERRE = CIERRE;
        this.TABCLOSE = TABCLOSE;
        this.VISUALIZA = VISUALIZA;
    }


    public String getGRUPO() {
        return GRUPO;
    }
    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
    }

    public String getPOSICION() {
        return POSICION;
    }
    public void setPOSICION(String POSICION) {
        this.POSICION = POSICION;
    }

    public String getTABOPEN() {
        return TABOPEN;
    }
    public void setTABOPEN(String TABOPEN) {
        this.TABOPEN = TABOPEN;
    }
    public String getVISUALIZA() {
        return VISUALIZA;
    }
    public void setVISUALIZA(String VISUALIZA) {
        this.VISUALIZA = VISUALIZA;
    }

    public String getTABCLOSE() {
        return TABCLOSE;
    }
    public void setTABCLOSE(String TABCLOSE) {
        this.TABCLOSE = TABCLOSE;
    }

    public String getCIERRE() {
        return CIERRE;
    }
    public void setCIERRE(String CIERRE) {
        this.CIERRE = CIERRE;
    }

    public String getDATO() {
        return DATO;
    }
    public void setDATO(String DATO) {
        this.DATO = DATO;
    }

    public String getDEFECTO() {
        return DEFECTO;
    }
    public void setDEFECTO(String DEFECTO) {
        this.DEFECTO = DEFECTO;
    }

    public String getCAMPO() {
        return CAMPO;
    }
    public void setCAMPO(String CAMPO) {
        this.CAMPO = CAMPO;
    }

    
}
