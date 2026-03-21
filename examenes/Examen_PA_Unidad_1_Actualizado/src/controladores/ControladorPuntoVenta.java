package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelos.DetalleVenta;
import modelos.Producto;
import modelos.RegistroCompra;
import modelos.Ticket;
import servicios.ServicioVentas;

public class ControladorPuntoVenta implements ActionListener, ListSelectionListener {
    private final ControladorContexto contexto;
    private final ArrayList<DetalleVenta> carrito;
    private final ServicioVentas servicioVentas;

    public ControladorPuntoVenta(ControladorContexto contexto) {
        this.contexto = contexto;
        this.carrito = new ArrayList<>();
        this.servicioVentas = new ServicioVentas();
        contexto.getVistaPuntoVenta().btnAnadir.addActionListener(this);
        contexto.getVistaPuntoVenta().btnModificar.addActionListener(this);
        contexto.getVistaPuntoVenta().btnEliminar.addActionListener(this);
        contexto.getVistaPuntoVenta().btnLimpiarCarrito.addActionListener(this);
        contexto.getVistaPuntoVenta().btnProcesarPago.addActionListener(this);
        contexto.getVistaPuntoVenta().btnExportarTicket.addActionListener(this);
        contexto.getVistaPuntoVenta().btnHistorialCompras.addActionListener(this);
        contexto.getVistaPuntoVenta().tabla.getSelectionModel().addListSelectionListener(this);
        recalcularTotales();
    }

    private int extraerIdSeleccionadoPuntoVenta() {
        Object item = contexto.getVistaPuntoVenta().cmbProducto.getSelectedItem();
        if (item == null) {
            return -1;
        }
        String texto = item.toString();
        String[] partes = texto.split(" - ");
        return Integer.parseInt(partes[0].trim());
    }

    private void recargarTablaCarrito() {
        contexto.getVistaPuntoVenta().modeloTabla.setRowCount(0);
        for (DetalleVenta detalle : carrito) {
            contexto.getVistaPuntoVenta().modeloTabla.addRow(new Object[] {detalle.getCodigo(), detalle.getDescripcion(), detalle.getCantidad(), String.format(Locale.US, "%.2f", detalle.getPrecioUnitario()), String.format(Locale.US, "%.2f", detalle.getTotal())});
        }
    }

    private DetalleVenta buscarDetallePorCodigo(String codigo) {
        for (DetalleVenta detalle : carrito) {
            if (detalle.getCodigo().equalsIgnoreCase(codigo)) {
                return detalle;
            }
        }
        return null;
    }

    private int cantidadReservada(String codigo) {
        int acumulado = 0;
        for (DetalleVenta detalle : carrito) {
            if (detalle.getCodigo().equalsIgnoreCase(codigo)) {
                acumulado += detalle.getCantidad();
            }
        }
        return acumulado;
    }

    private void ejecutarAnadirCarrito() {
        try {
            int id = extraerIdSeleccionadoPuntoVenta();
            if (id < 0) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Selecciona un producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int cantidad = Integer.parseInt(contexto.getVistaPuntoVenta().txtCantidad.getText().trim());
            servicioVentas.validarCantidadMayorACero(cantidad);
            Producto producto = contexto.getGestor().buscar(id);
            if (producto == null || !producto.isActivo()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Producto no disponible.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int reservado = cantidadReservada(producto.getCodigo());
            if (cantidad + reservado > producto.getStock()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Stock insuficiente para la cantidad solicitada.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return;
            }
            DetalleVenta existente = buscarDetallePorCodigo(producto.getCodigo());
            if (existente == null) {
                carrito.add(new DetalleVenta(producto.getCodigo(), producto.getNombre(), cantidad, producto.getPrecioVenta()));
            } else {
                existente.setCantidad(existente.getCantidad() + cantidad);
            }
            recargarTablaCarrito();
            recalcularTotales();
            contexto.getVistaPuntoVenta().txtCantidad.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), e.getMessage(), "Validacion", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ejecutarModificarCarrito() {
        int fila = contexto.getVistaPuntoVenta().tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Selecciona un detalle para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int nuevaCantidad = Integer.parseInt(contexto.getVistaPuntoVenta().txtCantidad.getText().trim());
            servicioVentas.validarCantidadMayorACero(nuevaCantidad);
            String codigo = String.valueOf(contexto.getVistaPuntoVenta().modeloTabla.getValueAt(fila, 0));
            Producto producto = contexto.getGestor().buscarPorCodigo(codigo);
            if (producto == null) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Producto asociado no encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int yaReservado = cantidadReservada(codigo);
            int cantidadActual = Integer.parseInt(String.valueOf(contexto.getVistaPuntoVenta().modeloTabla.getValueAt(fila, 2)));
            int disponibleReal = producto.getStock() - (yaReservado - cantidadActual);
            if (nuevaCantidad > disponibleReal) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No hay stock suficiente.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return;
            }
            DetalleVenta detalle = buscarDetallePorCodigo(codigo);
            if (detalle != null) {
                detalle.setCantidad(nuevaCantidad);
            }
            recargarTablaCarrito();
            recalcularTotales();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), e.getMessage(), "Validacion", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ejecutarEliminarCarrito() {
        int fila = contexto.getVistaPuntoVenta().tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Selecciona un detalle para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String codigo = String.valueOf(contexto.getVistaPuntoVenta().modeloTabla.getValueAt(fila, 0));
        Iterator<DetalleVenta> iterator = carrito.iterator();
        while (iterator.hasNext()) {
            DetalleVenta detalle = iterator.next();
            if (detalle.getCodigo().equalsIgnoreCase(codigo)) {
                iterator.remove();
                break;
            }
        }
        recargarTablaCarrito();
        recalcularTotales();
    }

    private void recalcularTotales() {
        double subtotal = servicioVentas.calcularSubtotal(carrito);
        double iva = servicioVentas.calcularIva(subtotal);
        double total = servicioVentas.calcularTotal(subtotal, iva);
        contexto.getVistaPuntoVenta().txtSubtotal.setText(String.format(Locale.US, "%.2f", subtotal));
        contexto.getVistaPuntoVenta().txtIva.setText(String.format(Locale.US, "%.2f", iva));
        contexto.getVistaPuntoVenta().txtTotal.setText(String.format(Locale.US, "%.2f", total));
    }

    private void ejecutarProcesarPago() {
        try {
            servicioVentas.validarCarritoConDatos(carrito);
            servicioVentas.validarClienteYCajero(contexto.getVistaPuntoVenta().txtCliente.getText().trim(), contexto.getVistaPuntoVenta().txtCajero.getText().trim());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), e.getMessage(), "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (DetalleVenta detalle : carrito) {
            Producto producto = contexto.getGestor().buscarPorCodigo(detalle.getCodigo());
            if (producto == null) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Producto del carrito no encontrado: " + detalle.getCodigo(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (detalle.getCantidad() > producto.getStock()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Stock insuficiente para " + producto.getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        double subtotal;
        double iva;
        double total;
        try {
            subtotal = Double.parseDouble(contexto.getVistaPuntoVenta().txtSubtotal.getText());
            iva = Double.parseDouble(contexto.getVistaPuntoVenta().txtIva.getText());
            total = Double.parseDouble(contexto.getVistaPuntoVenta().txtTotal.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Totales invalidos para procesar pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Generar folio único basado en timestamp
        String folio = String.valueOf(System.currentTimeMillis());
        String fecha = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        try {
            contexto.getArchivo().guardarHistorialCompra(carrito, contexto.getVistaPuntoVenta().txtCliente.getText().trim(), contexto.getVistaPuntoVenta().txtCajero.getText().trim(), subtotal, iva, total);
            
            // Exportar ticket a JSON con folio como nombre
            exportacion.ExportadorJSON.exportarTicket(
                folio, 
                fecha,
                contexto.getVistaPuntoVenta().txtCliente.getText().trim(),
                convertirCarritoAJSON(carrito),
                subtotal, 
                iva, 
                total
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No se pudo guardar historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for (DetalleVenta detalle : carrito) {
            Producto producto = contexto.getGestor().buscarPorCodigo(detalle.getCodigo());
            producto.setStock(producto.getStock() - detalle.getCantidad());
        }
        contexto.guardarCSV();
        carrito.clear();
        recargarTablaCarrito();
        recalcularTotales();
        contexto.getVistaPuntoVenta().txtCantidad.setText("");
        contexto.refrescarTodo();
        
        JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), 
            "✓ Pago procesado correctamente\n\n" +
            "Folio: " + folio + "\n" +
            "Ticket JSON generado en: Exportaciones/Tickets/ticket_" + folio + ".json\n" +
            "Total: $" + String.format(Locale.US, "%.2f", total));
    }
    
    private ArrayList<String> convertirCarritoAJSON(ArrayList<DetalleVenta> carrito) {
        ArrayList<String> items = new ArrayList<>();
        for (DetalleVenta detalle : carrito) {
            String json = "{\"codigo\":\"" + detalle.getCodigo() + "\"," +
                         "\"producto\":\"" + detalle.getDescripcion().replace("\"", "\\\"") + "\"," +
                         "\"cantidad\":" + detalle.getCantidad() + "," +
                         "\"precioUnitario\":" + detalle.getPrecioUnitario() + "," +
                         "\"subtotal\":" + detalle.getTotal() + "}";
            items.add(json);
        }
        return items;
    }

    private void ejecutarExportarTicket() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No hay datos para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double subtotal = Double.parseDouble(contexto.getVistaPuntoVenta().txtSubtotal.getText());
            double iva = Double.parseDouble(contexto.getVistaPuntoVenta().txtIva.getText());
            double total = Double.parseDouble(contexto.getVistaPuntoVenta().txtTotal.getText());
            Ticket ticketData = servicioVentas.crearTicket(
                carrito,
                contexto.getVistaPuntoVenta().txtCliente.getText().trim(),
                contexto.getVistaPuntoVenta().txtCajero.getText().trim(),
                subtotal,
                iva,
                total
            );
            Path ticket = contexto.getArchivo().exportarTicket(ticketData);
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Ticket exportado: " + ticket.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Error al exportar ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarVerHistorialCompras() {
        try {
            ArrayList<RegistroCompra> historial = contexto.getArchivo().importarHistorialCompras();
            if (historial.isEmpty()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No hay compras registradas.", "Historial", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Map<String, ArrayList<RegistroCompra>> comprasPorCarrito = servicioVentas.agruparHistorialPorCarrito(historial);
            DefaultTableModel modelo = new DefaultTableModel(
                new Object[] {"Carrito", "Fecha", "Cliente", "Cajero", "Codigo", "Producto", "Cantidad", "P.Unit", "Total Linea", "Subtotal", "IVA", "Total Venta"},
                0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            int indice = 1;
            for (Map.Entry<String, ArrayList<RegistroCompra>> entry : comprasPorCarrito.entrySet()) {
                ArrayList<RegistroCompra> compra = entry.getValue();
                if (compra.isEmpty()) {
                    continue;
                }
                RegistroCompra cabecera = compra.get(0);
                boolean primeraFila = true;
                for (RegistroCompra d : compra) {
                    modelo.addRow(new Object[] {
                        primeraFila ? "Carrito " + indice : "",
                        primeraFila ? cabecera.getFechaHora() : "",
                        primeraFila ? cabecera.getCliente() : "",
                        primeraFila ? cabecera.getCajero() : "",
                        d.getCodigo(),
                        d.getDescripcion(),
                        d.getCantidad(),
                        String.format(Locale.US, "%.2f", d.getPrecioUnitario()),
                        String.format(Locale.US, "%.2f", d.getTotalLinea()),
                        primeraFila ? String.format(Locale.US, "%.2f", cabecera.getSubtotalVenta()) : "",
                        primeraFila ? String.format(Locale.US, "%.2f", cabecera.getIvaVenta()) : "",
                        primeraFila ? String.format(Locale.US, "%.2f", cabecera.getTotalVenta()) : ""
                    });
                    primeraFila = false;
                }
                modelo.addRow(new Object[] {"", "", "", "", "", "", "", "", "", "", "", ""});
                indice++;
            }
            JTable tablaHistorial = new JTable(modelo);
            JScrollPane panel = new JScrollPane(tablaHistorial);
            panel.setPreferredSize(new java.awt.Dimension(1100, 360));
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), panel, "Historial de Compras por Carrito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Error al cargar historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == contexto.getVistaPuntoVenta().btnAnadir) {
            ejecutarAnadirCarrito();
        } else if (source == contexto.getVistaPuntoVenta().btnModificar) {
            ejecutarModificarCarrito();
        } else if (source == contexto.getVistaPuntoVenta().btnEliminar) {
            ejecutarEliminarCarrito();
        } else if (source == contexto.getVistaPuntoVenta().btnLimpiarCarrito) {
            carrito.clear();
            recargarTablaCarrito();
            recalcularTotales();
        } else if (source == contexto.getVistaPuntoVenta().btnProcesarPago) {
            ejecutarProcesarPago();
        } else if (source == contexto.getVistaPuntoVenta().btnExportarTicket) {
            ejecutarExportarTicket();
        } else if (source == contexto.getVistaPuntoVenta().btnHistorialCompras) {
            ejecutarVerHistorialCompras();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == contexto.getVistaPuntoVenta().tabla.getSelectionModel()) {
            int fila = contexto.getVistaPuntoVenta().tabla.getSelectedRow();
            if (fila >= 0) {
                contexto.getVistaPuntoVenta().txtCantidad.setText(String.valueOf(contexto.getVistaPuntoVenta().modeloTabla.getValueAt(fila, 2)));
            }
        }
    }
}
