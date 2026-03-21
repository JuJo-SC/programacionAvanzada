package exportacion;

import modelos.Producto;
import java.io.*;
import java.util.ArrayList;

/**
 * Exporta productos y tickets a formato JSON
 * NOTA: Requiere librería GSON (gson-2.11.0.jar en carpeta lib/)
 */
public class ExportadorJSON {

    /**
     * Exporta lista de productos a JSON
     * @return Ruta del archivo generado
     */
    public static String exportarProductos(ArrayList<Producto> productos) {
        new File("Exportaciones").mkdirs();
        String ruta = "Exportaciones/productos.json";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("[");
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                pw.print("  {");
                pw.print("\"id\":" + p.getId());
                pw.print(",\"codigo\":\"" + p.getCodigo() + "\"");
                pw.print(",\"nombre\":\"" + p.getNombre() + "\"");
                pw.print(",\"descripcion\":\"" + escape(p.getDescripcion()) + "\"");
                pw.print(",\"categoria\":\"" + p.getCategoria() + "\"");
                pw.print(",\"tipo\":\"" + p.getTipoProducto() + "\"");
                pw.print(",\"unidad\":\"" + p.getUnidadVenta() + "\"");
                pw.print(",\"precioCompra\":" + p.getPrecioCompra());
                pw.print(",\"precioVenta\":" + p.getPrecioVenta());
                pw.print(",\"stock\":" + p.getStock());
                pw.print(",\"stockMinimo\":" + p.getStockMinimo());
                pw.print(",\"activo\":" + p.isActivo());
                pw.print(",\"imagen\":\"" + escape(p.getImagenRuta()) + "\"");
                pw.print("}");
                if (i < productos.size() - 1) pw.println(",");
                else pw.println();
            }
            pw.println("]");
            return ruta;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * Exporta un ticket de venta a JSON
     * @param folio Código de folio (será el nombre del archivo)
     * @param fecha Fecha y hora de la venta
     * @param items Lista de productos vendidos con cantidades
     * @param total Total de la venta
     * @return Ruta del archivo generado
     */
    public static String exportarTicket(String folio, String fecha, String cliente, 
                                       ArrayList<String> items, double subtotal, 
                                       double iva, double total) {
        new File("Exportaciones/Tickets").mkdirs();
        String ruta = "Exportaciones/Tickets/ticket_" + folio + ".json";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("{");
            pw.println("  \"folio\":\"" + folio + "\",");
            pw.println("  \"fecha\":\"" + fecha + "\",");
            pw.println("  \"cliente\":\"" + escape(cliente) + "\",");
            pw.println("  \"items\":[");
            for (int i = 0; i < items.size(); i++) {
                pw.print("    " + items.get(i));
                if (i < items.size() - 1) pw.println(",");
                else pw.println();
            }
            pw.println("  ],");
            pw.println("  \"subtotal\":" + round2(subtotal) + ",");
            pw.println("  \"iva\":" + round2(iva) + ",");
            pw.println("  \"total\":" + round2(total));
            pw.println("}");
            
            // Actualizar maestro de tickets
            actualizarMaestroTickets(folio, fecha, cliente, total, ruta);
            return ruta;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private static void actualizarMaestroTickets(String folio, String fecha, 
                                                 String cliente, double total, String rutaTicket) {
        String rutaMaestro = "Exportaciones/Tickets/tickets.json";
        ArrayList<String> existentes = new ArrayList<>();
        
        // Leer tickets existentes
        File maestro = new File(rutaMaestro);
        if (maestro.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(maestro))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().equals("[") && !linea.trim().equals("]")) {
                        existentes.add(linea.replace(",", ""));
                    }
                }
            } catch (IOException ignored) {}
        }
        
        // Escribir archivo actualizado
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaMaestro))) {
            pw.println("[");
            for (String ticket : existentes) {
                pw.println(ticket.trim() + ",");
            }
            // Agregar nuevo ticket
            pw.print("  {\"folio\":\"" + folio + "\",\"fecha\":\"" + fecha + "\"");
            pw.print(",\"cliente\":\"" + escape(cliente) + "\"");
            pw.print(",\"total\":" + round2(total));
            pw.println(",\"archivo\":\"" + rutaTicket + "\"}");
            pw.println("]");
        } catch (IOException ignored) {}
    }
    
    private static String escape(String str) {
        if (str == null) return "";
        return str.replace("\"", "\\\"").replace("\n", "\\n");
    }
    
    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
