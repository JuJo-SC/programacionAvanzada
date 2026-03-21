package modelos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import repositorios.RepositorioProductosCSV;

public class ArchivoProductos {
    private final RepositorioProductosCSV repositorio;

    public ArchivoProductos() {
        this.repositorio = new RepositorioProductosCSV();
    }

    public ArrayList<Producto> importarCSV() throws IOException {
        return repositorio.importarCSV();
    }

    public void exportarCSV(ArrayList<Producto> lista) throws IOException {
        repositorio.exportarCSV(lista);
    }

    public Path exportarTicket(Ticket ticketData) throws IOException {
        return repositorio.exportarTicket(ticketData);
    }

    public void guardarHistorialCompra(ArrayList<DetalleVenta> detalles, String cliente, String cajero, double subtotal, double iva, double total) throws IOException {
        repositorio.guardarHistorialCompra(detalles, cliente, cajero, subtotal, iva, total);
    }

    public ArrayList<RegistroCompra> importarHistorialCompras() throws IOException {
        return repositorio.importarHistorialCompras();
    }
}
