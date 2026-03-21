package modelos;

/**
 * Producto que se vende por unidades/piezas (Abarrotes, Limpieza, Snacks, etc.)
 */
public class ProductoUnitario extends Producto {

    public ProductoUnitario(int id, String codigo, String nombre, String descripcion, String categoria, 
                           double precioCompra, double precioVenta, int stock, int stockMinimo, boolean activo) {
        super(id, codigo, nombre, descripcion, categoria, precioCompra, precioVenta, stock, stockMinimo, activo);
    }

    @Override
    public double calcularSubtotal(double cantidad) {
        return cantidad * getPrecioVenta();
    }

    @Override
    public String getUnidadVenta() {
        return "unidad";
    }

    @Override
    public boolean cantidadValida(double cantidad) {
        return cantidad > 0 && cantidad == Math.floor(cantidad); // Solo enteros
    }

    @Override
    public String getTipoProducto() {
        return "Unitario";
    }
}
