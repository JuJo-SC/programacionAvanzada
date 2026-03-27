package validadores;

import java.util.ArrayList;

import modelos.DetalleVenta;

public class ValidadorVenta {
    public void validarCarritoConDatos(ArrayList<DetalleVenta> carrito) {
        if (carrito.isEmpty()) {
            throw new IllegalArgumentException("No hay productos en el carrito.");
        }
    }

    public void validarClienteYCajero(String cliente, String cajero) {
        if (cliente.isBlank() || cajero.isBlank()) {
            throw new IllegalArgumentException("Cliente y cajero son obligatorios.");
        }
    }

    public void validarCantidadMayorACero(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
    }
}
