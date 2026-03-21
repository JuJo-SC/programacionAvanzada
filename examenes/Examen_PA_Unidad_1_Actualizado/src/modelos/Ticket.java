package modelos;

import java.util.ArrayList;

public class Ticket {
    private final String idTicket;
    private final String fechaHora;
    private final String cliente;
    private final String cajero;
    private final ArrayList<DetalleVenta> detalles;
    private final double subtotal;
    private final double iva;
    private final double total;

    public Ticket(String idTicket, String fechaHora, String cliente, String cajero, ArrayList<DetalleVenta> detalles, double subtotal, double iva, double total) {
        this.idTicket = idTicket;
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.cajero = cajero;
        this.detalles = new ArrayList<>(detalles);
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public String getIdTicket() {
        return idTicket;
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

    public ArrayList<DetalleVenta> getDetalles() {
        return new ArrayList<>(detalles);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Ticket{idTicket='" + idTicket + "', fechaHora='" + fechaHora + "', cliente='" + cliente + "', cajero='" + cajero + "', detalles=" + detalles.size() + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + "}";
    }
}