package servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import modelos.DetalleVenta;
import modelos.RegistroCompra;
import modelos.Ticket;
import util.ConstantesSistema;
import validadores.ValidadorVenta;

public class ServicioVentas {
    private final ValidadorVenta validador;

    public ServicioVentas() {
        this.validador = new ValidadorVenta();
    }

    public void validarCarritoConDatos(ArrayList<DetalleVenta> carrito) {
        validador.validarCarritoConDatos(carrito);
    }

    public void validarClienteYCajero(String cliente, String cajero) {
        validador.validarClienteYCajero(cliente, cajero);
    }

    public void validarCantidadMayorACero(int cantidad) {
        validador.validarCantidadMayorACero(cantidad);
    }

    public double calcularSubtotal(ArrayList<DetalleVenta> carrito) {
        double subtotal = 0;
        Iterator<DetalleVenta> iterator = carrito.iterator();
        while (iterator.hasNext()) {
            subtotal += iterator.next().getTotal();
        }
        return subtotal;
    }

    public double calcularIva(double subtotal) {
        return subtotal * ConstantesSistema.IVA;
    }

    public double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }

    public Ticket crearTicket(ArrayList<DetalleVenta> carrito, String cliente, String cajero, double subtotal, double iva, double total) {
        String idTicket = "T" + System.currentTimeMillis();
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new Ticket(idTicket, fechaHora, cliente, cajero, new ArrayList<>(carrito), subtotal, iva, total);
    }

    public Map<String, ArrayList<RegistroCompra>> agruparHistorialPorCarrito(ArrayList<RegistroCompra> historial) {
        Map<String, ArrayList<RegistroCompra>> comprasPorCarrito = new LinkedHashMap<>();
        for (RegistroCompra r : historial) {
            ArrayList<RegistroCompra> compra = comprasPorCarrito.get(r.getIdVenta());
            if (compra == null) {
                compra = new ArrayList<>();
                comprasPorCarrito.put(r.getIdVenta(), compra);
            }
            compra.add(r);
        }
        return comprasPorCarrito;
    }
}
