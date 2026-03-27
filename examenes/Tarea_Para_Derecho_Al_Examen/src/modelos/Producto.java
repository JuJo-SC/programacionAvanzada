package modelos;

/**
 * Clase abstracta base para todos los productos del sistema POS.
 * Jerarquía: Producto → ProductoUnitario, ProductoPorPeso, ProductoPorVolumen
 */
public abstract class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioCompra;
    private double precioVenta;
    private double porcentajeGanancia;
    private int stock;
    private int stockMinimo;
    private boolean activo;
    private String imagenRuta;

    public Producto(int id, String codigo, String nombre, String descripcion, String categoria, double precioCompra, double porcentajeGanancia, int stock, int stockMinimo, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioCompra = precioCompra;
        this.porcentajeGanancia = porcentajeGanancia;
        this.precioVenta = precioCompra * (1 + (porcentajeGanancia / 100.0));
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.activo = activo;
        this.imagenRuta = "imagenes/productos/" + codigo + ".jpg";
    }
    
    // Métodos abstractos que deben implementar las subclases
    public abstract double calcularSubtotal(double cantidad);
    public abstract String getUnidadVenta();
    public abstract boolean cantidadValida(double cantidad);
    public abstract String getTipoProducto();
    
    /**
     * Método factory para crear el tipo correcto de producto según la categoría
     * Por defecto crea ProductoUnitario
     */
    public static Producto crear(int id, String codigo, String nombre, String descripcion, 
                                String categoria, double precioCompra, double porcentajeGanancia, 
                                int stock, int stockMinimo, boolean activo) {
        // Determinar tipo según categoría
        if (categoria != null) {
            String cat = categoria.toLowerCase();
            // Productos por peso
            if (cat.contains("frutas") || cat.contains("verduras") || 
                cat.contains("carnes") || cat.contains("pescados") || 
                cat.contains("salchichoneria")) {
                return new ProductoPorPeso(id, codigo, nombre, descripcion, categoria, 
                                          precioCompra, porcentajeGanancia, stock, stockMinimo, activo);
            }
            // Por volumen (bebidas a granel, actualmente no hay en los datos)
            // Todo lo demás es unitario
        }
        return new ProductoUnitario(id, codigo, nombre, descripcion, categoria, 
                                   precioCompra, porcentajeGanancia, stock, stockMinimo, activo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
        this.precioVenta = this.precioCompra * (1 + (porcentajeGanancia / 100.0));
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getImagenRuta() {
        return imagenRuta;
    }
    
    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta != null ? imagenRuta : "";
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", codigo='" + codigo + "', nombre='" + nombre + "', descripcion='" + descripcion + "', categoria='" + categoria + "', precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", stock=" + stock + ", stockMinimo=" + stockMinimo + ", activo=" + activo + ", imagen='" + imagenRuta + "'}";
    }
}