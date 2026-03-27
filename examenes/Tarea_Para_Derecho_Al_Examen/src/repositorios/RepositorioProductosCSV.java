package repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelos.DetalleVenta;
import modelos.Producto;
import modelos.RegistroCompra;
import modelos.Ticket;
import util.ConstantesSistema;

public class RepositorioProductosCSV {
    private final Path rutaProductos;
    private final Path rutaHistorial;

    public RepositorioProductosCSV() {
        this.rutaProductos = Paths.get(ConstantesSistema.DATA_DIR, ConstantesSistema.PRODUCTOS_CSV);
        this.rutaHistorial = Paths.get(ConstantesSistema.DATA_DIR, ConstantesSistema.HISTORIAL_CSV);
    }

    public ArrayList<Producto> importarCSV() throws IOException {
        ArrayList<Producto> lista = new ArrayList<>();
        if (!Files.exists(rutaProductos)) {
            Files.createDirectories(rutaProductos.getParent());
            Files.createFile(rutaProductos);
            return lista;
        }
        try (BufferedReader br = Files.newBufferedReader(rutaProductos, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                String[] partes = linea.split(",", -1);
                if (partes.length < 10) {
                    continue;
                }
                int id = Integer.parseInt(partes[0].trim());
                String codigo = partes[1].trim();
                String nombre = partes[2].trim();
                String descripcion = partes[3].trim();
                String categoria = partes[4].trim();
                double precioCompra = Double.parseDouble(partes[5].trim());
                double precioVenta = Double.parseDouble(partes[6].trim());
                int stock = Integer.parseInt(partes[7].trim());
                int stockMinimo = Integer.parseInt(partes[8].trim());
                boolean activo = Boolean.parseBoolean(partes[9].trim());
                Producto producto = Producto.crear(id, codigo, nombre, descripcion, categoria, precioCompra, precioVenta, stock, stockMinimo, activo);
                // Si hay ruta de imagen (campo 11), agregarla
                if (partes.length >= 11 && !partes[10].trim().isEmpty()) {
                    producto.setImagenRuta(partes[10].trim());
                }
                lista.add(producto);
            }
        }
        return lista;
    }

    public void exportarCSV(ArrayList<Producto> lista) throws IOException {
        Files.createDirectories(rutaProductos.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(rutaProductos, StandardCharsets.UTF_8)) {
            for (Producto producto : lista) {
                String linea = producto.getId() + "," + 
                              limpiar(producto.getCodigo()) + "," + 
                              limpiar(producto.getNombre()) + "," + 
                              limpiar(producto.getDescripcion()) + "," + 
                              limpiar(producto.getCategoria()) + "," + 
                              producto.getPrecioCompra() + "," + 
                              producto.getPrecioVenta() + "," + 
                              producto.getStock() + "," + 
                              producto.getStockMinimo() + "," + 
                              producto.isActivo() + "," +
                              limpiar(producto.getImagenRuta());
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    public Path exportarTicket(Ticket ticketData) throws IOException {
        Path directorio = Paths.get(ConstantesSistema.DATA_DIR);
        Files.createDirectories(directorio);
        String nombre = "ticket_" + ticketData.getIdTicket() + ".csv";
        Path ticket = directorio.resolve(nombre);
        try (BufferedWriter bw = Files.newBufferedWriter(ticket, StandardCharsets.UTF_8)) {
            bw.write("TicketId," + limpiar(ticketData.getIdTicket()));
            bw.newLine();
            bw.write("FechaHora," + limpiar(ticketData.getFechaHora()));
            bw.newLine();
            bw.write("Cliente," + limpiar(ticketData.getCliente()));
            bw.newLine();
            bw.write("Cajero," + limpiar(ticketData.getCajero()));
            bw.newLine();
            bw.newLine();
            bw.write("Codigo,Descripcion,Cantidad,PrecioUnitario,Total");
            bw.newLine();
            for (DetalleVenta detalle : ticketData.getDetalles()) {
                bw.write(limpiar(detalle.getCodigo()) + "," + limpiar(detalle.getDescripcion()) + "," + detalle.getCantidad() + "," + detalle.getPrecioUnitario() + "," + detalle.getTotal());
                bw.newLine();
            }
            bw.write(",,,Subtotal," + ticketData.getSubtotal());
            bw.newLine();
            bw.write(",,,IVA," + ticketData.getIva());
            bw.newLine();
            bw.write(",,,Total," + ticketData.getTotal());
            bw.newLine();
        }
        return ticket;
    }

    public void guardarHistorialCompra(ArrayList<DetalleVenta> detalles, String cliente, String cajero, double subtotal, double iva, double total) throws IOException {
        Files.createDirectories(rutaHistorial.getParent());
        boolean crearEncabezado = !Files.exists(rutaHistorial) || Files.size(rutaHistorial) == 0;
        try (BufferedWriter bw = Files.newBufferedWriter(rutaHistorial, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (crearEncabezado) {
                bw.write("IdVenta,FechaHora,Cliente,Cajero,Codigo,Descripcion,Cantidad,PrecioUnitario,TotalLinea,Subtotal,IVA,Total");
                bw.newLine();
            }
            String idVenta = "V" + System.currentTimeMillis();
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (DetalleVenta detalle : detalles) {
                bw.write(idVenta + "," + fecha + "," + limpiar(cliente) + "," + limpiar(cajero) + "," + limpiar(detalle.getCodigo()) + "," + limpiar(detalle.getDescripcion()) + "," + detalle.getCantidad() + "," + detalle.getPrecioUnitario() + "," + detalle.getTotal() + "," + subtotal + "," + iva + "," + total);
                bw.newLine();
            }
        }
    }

    public ArrayList<RegistroCompra> importarHistorialCompras() throws IOException {
        ArrayList<RegistroCompra> historial = new ArrayList<>();
        if (!Files.exists(rutaHistorial)) {
            Files.createDirectories(rutaHistorial.getParent());
            Files.createFile(rutaHistorial);
            return historial;
        }
        try (BufferedReader br = Files.newBufferedReader(rutaHistorial, StandardCharsets.UTF_8)) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                if (primera && (linea.startsWith("IdVenta,") || linea.startsWith("FechaHora,"))) {
                    primera = false;
                    continue;
                }
                primera = false;
                String[] p = linea.split(",", -1);
                if (p.length < 11) {
                    continue;
                }
                try {
                    if (p.length >= 12) {
                        historial.add(new RegistroCompra(
                            p[0].trim(),
                            p[1].trim(),
                            p[2].trim(),
                            p[3].trim(),
                            p[4].trim(),
                            p[5].trim(),
                            Integer.parseInt(p[6].trim()),
                            Double.parseDouble(p[7].trim()),
                            Double.parseDouble(p[8].trim()),
                            Double.parseDouble(p[9].trim()),
                            Double.parseDouble(p[10].trim()),
                            Double.parseDouble(p[11].trim())
                        ));
                    } else {
                        String idGenerado = p[0].trim() + "|" + p[1].trim() + "|" + p[2].trim() + "|" + p[8].trim() + "|" + p[10].trim();
                        historial.add(new RegistroCompra(
                            idGenerado,
                            p[0].trim(),
                            p[1].trim(),
                            p[2].trim(),
                            p[3].trim(),
                            p[4].trim(),
                            Integer.parseInt(p[5].trim()),
                            Double.parseDouble(p[6].trim()),
                            Double.parseDouble(p[7].trim()),
                            Double.parseDouble(p[8].trim()),
                            Double.parseDouble(p[9].trim()),
                            Double.parseDouble(p[10].trim())
                        ));
                    }
                } catch (NumberFormatException ex) {
                    throw new IOException("Linea invalida en historial_ventas.csv: " + linea, ex);
                }
            }
        }
        return historial;
    }

    private String limpiar(String texto) {
        return texto == null ? "" : texto.replace(",", " ");
    }
}