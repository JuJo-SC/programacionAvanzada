package Parte2;

/**
 * Modelo: Insumo — exactamente como aparece en el PDF (pagina 20).
 */
public class Insumo {

    private String idProducto;
    private String producto;
    private String idCategoria;

    public Insumo(String idProducto, String producto, String idCategoria) {
        this.idProducto  = idProducto;
        this.producto    = producto;
        this.idCategoria = idCategoria;
    }

    public String getIdProducto()                 { return idProducto; }
    public void   setIdProducto(String id)        { this.idProducto = id; }
    public String getProducto()                   { return producto; }
    public void   setProducto(String producto)    { this.producto = producto; }
    public String getIdCategoria()                { return idCategoria; }
    public void   setIdCategoria(String idCat)    { this.idCategoria = idCat; }

    @Override
    public String toString() {
        return idProducto + "\t\t" + producto + "\t\t" + idCategoria;
    }
}
