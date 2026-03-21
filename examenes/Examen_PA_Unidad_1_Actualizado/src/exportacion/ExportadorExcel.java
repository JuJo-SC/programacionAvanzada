package exportacion;

import modelos.Producto;
import java.io.*;
import java.util.*;

/**
 * Exporta productos a archivos CSV que Excel puede abrir
 * Genera: 1) Listado completo, 2) Listado por categoría
 */
public class ExportadorExcel {

    /**
     * Exporta todos los productos a CSV
     */
    public static String exportarProductos(ArrayList<Producto> productos) {
        new File("Exportaciones/Excel").mkdirs();
        String ruta = "Exportaciones/Excel/productos.csv";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            // Cabecera
            pw.println("ID,Código,Nombre,Categoría,Tipo,Unidad,P.Compra,P.Venta,Stock,Stock Mín,Activo,Imagen");
            
            // Datos
            for (Producto p : productos) {
                pw.print(p.getId() + ",");
                pw.print("\"" + p.getCodigo() + "\",");
                pw.print("\"" + p.getNombre() + "\",");
                pw.print("\"" + p.getCategoria() + "\",");
                pw.print("\"" + p.getTipoProducto() + "\",");
                pw.print("\"" + p.getUnidadVenta() + "\",");
                pw.print(p.getPrecioCompra() + ",");
                pw.print(p.getPrecioVenta() + ",");
                pw.print(p.getStock() + ",");
                pw.print(p.getStockMinimo() + ",");
                pw.print((p.isActivo() ? "Sí" : "No") + ",");
                pw.println("\"" + p.getImagenRuta() + "\"");
            }
            return ruta;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * Exporta productos agrupados por categoría (un archivo por categoría)
     */
    public static String exportarProductosPorCategoria(ArrayList<Producto> productos) {
        new File("Exportaciones/Excel").mkdirs();
        
        // Agrupar por categoría
        Map<String, ArrayList<Producto>> porCategoria = new LinkedHashMap<>();
        for (Producto p : productos) {
            String cat = p.getCategoria() != null ? p.getCategoria() : "Sin Categoría";
            porCategoria.computeIfAbsent(cat, k -> new ArrayList<>()).add(p);
        }
        
        // Crear un archivo por categoría
        int archivosCreados = 0;
        for (Map.Entry<String, ArrayList<Producto>> entry : porCategoria.entrySet()) {
            String categoria = entry.getKey().replaceAll("[^a-zA-Z0-9]", "_");
            String ruta = "Exportaciones/Excel/productos_" + categoria + ".csv";
            
            try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
                pw.println("ID,Código,Nombre,Tipo,Unidad,P.Venta,Stock,Activo");
                
                for (Producto p : entry.getValue()) {
                    pw.print(p.getId() + ",");
                    pw.print("\"" + p.getCodigo() + "\",");
                    pw.print("\"" + p.getNombre() + "\",");
                    pw.print("\"" + p.getTipoProducto() + "\",");
                    pw.print("\"" + p.getUnidadVenta() + "\",");
                    pw.print(p.getPrecioVenta() + ",");
                    pw.print(p.getStock() + ",");
                    pw.println(p.isActivo() ? "Sí" : "No");
                }
                archivosCreados++;
            } catch (IOException e) {
                return "Error en " + categoria + ": " + e.getMessage();
            }
        }
        
        return "Generados " + archivosCreados + " archivos en Exportaciones/Excel/";
    }
}
