package modelos;

/**
 * Producto que se vende por peso en kilogramos (Frutas, Verduras, Carnes, etc.)
 */
public class ProductoPorPeso extends Producto {

    public ProductoPorPeso(int id, String codigo, String nombre, String descripcion, String categoria, 
                          double precioCompra, double precioVenta, int stock, int stockMinimo, boolean activo) {
        super(id, codigo, nombre, descripcion, categoria, precioCompra, precioVenta, stock, stockMinimo, activo);
    }

    @Override
    public double calcularSubtotal(double cantidad) {
        return cantidad * getPrecioVenta();
    }

    @Override
    public String getUnidadVenta() {
        return "kg";
    }

    @Override
    public boolean cantidadValida(double cantidad) {
        return cantidad > 0;
    }

    @Override
    public String getTipoProducto() {
        return "Por Peso (kg)";
    }
}
