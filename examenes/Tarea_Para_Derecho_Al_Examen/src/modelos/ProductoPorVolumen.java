package modelos;

/**
 * Producto que se vende por volumen en litros (Bebidas a granel, etc.)
 */
public class ProductoPorVolumen extends Producto {

    public ProductoPorVolumen(int id, String codigo, String nombre, String descripcion, String categoria, 
                             double precioCompra, double porcentajeGanancia, int stock, int stockMinimo, boolean activo) {
        super(id, codigo, nombre, descripcion, categoria, precioCompra, porcentajeGanancia, stock, stockMinimo, activo);
    }

    @Override
    public double calcularSubtotal(double cantidad) {
        return cantidad * getPrecioVenta();
    }

    @Override
    public String getUnidadVenta() {
        return "litro";
    }

    @Override
    public boolean cantidadValida(double cantidad) {
        return cantidad > 0;
    }

    @Override
    public String getTipoProducto() {
        return "Por Volumen (lt)";
    }
}
