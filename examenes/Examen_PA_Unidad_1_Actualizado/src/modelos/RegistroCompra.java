package modelos;

public class RegistroCompra {
    private final String idVenta;
    private final String fechaHora;
    private final String cliente;
    private final String cajero;
    private final String codigo;
    private final String descripcion;
    private final int cantidad;
    private final double precioUnitario;
    private final double totalLinea;
    private final double subtotalVenta;
    private final double ivaVenta;
    private final double totalVenta;

    public RegistroCompra(String idVenta, String fechaHora, String cliente, String cajero, String codigo, String descripcion, int cantidad, double precioUnitario, double totalLinea, double subtotalVenta, double ivaVenta, double totalVenta) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.cajero = cajero;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalLinea = totalLinea;
        this.subtotalVenta = subtotalVenta;
        this.ivaVenta = ivaVenta;
        this.totalVenta = totalVenta;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getCliente() {
        return cliente;
    }

    public String getCajero() {
        return cajero;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotalLinea() {
        return totalLinea;
    }

    public double getSubtotalVenta() {
        return subtotalVenta;
    }

    public double getIvaVenta() {
        return ivaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }
}