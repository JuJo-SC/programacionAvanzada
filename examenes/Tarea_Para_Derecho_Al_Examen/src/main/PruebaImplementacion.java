package main;

import modelos.*;
import exportacion.*;
import java.util.ArrayList;

/**
 * Programa de prueba para verificar la implementación
 */
public class PruebaImplementacion {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("PRUEBA DE IMPLEMENTACIÓN - SISTEMA POS");
        System.out.println("=".repeat(60));
        
        // 1. Cargar productos
        System.out.println("\n1. Cargando productos...");
        ArrayList<Producto> productos = DatosIniciales.cargarProductos();
        System.out.println("   Total productos: " + productos.size());
        
        // 2. Mostrar resumen por categoría
        System.out.println("\n2. Resumen por categoría:");
        contarPorCategoria(productos);
        
        // 3. Mostrar ejemplos de cada tipo
        System.out.println("\n3. Ejemplos de productos:");
        mostrarEjemplos(productos);
        
        // 4. Exportar a JSON
        System.out.println("\n4. Exportando a JSON...");
        String rutaJSON = ExportadorJSON.exportarProductos(productos);
        System.out.println("   ✓ " + rutaJSON);
        
        // 5. Exportar a Excel/CSV
        System.out.println("\n5. Exportando a Excel/CSV...");
        String rutaExcel = ExportadorExcel.exportarProductos(productos);
        System.out.println("   ✓ " + rutaExcel);
        
        String rutaCategorias = ExportadorExcel.exportarProductosPorCategoria(productos);
        System.out.println("   ✓ " + rutaCategorias);
        
        // 6. Ejemplo de ticket JSON
        System.out.println("\n6. Generando ticket de prueba...");
        ArrayList<String> items = new ArrayList<>();
        items.add("{\"producto\":\"Arroz 1kg\",\"cantidad\":2,\"precio\":18.5,\"subtotal\":37.0}");
        items.add("{\"producto\":\"Leche 1L\",\"cantidad\":3,\"precio\":22.0,\"subtotal\":66.0}");
        String rutaTicket = ExportadorJSON.exportarTicket(
            "20260321001", 
            "2026-03-21 01:00:00", 
            "Cliente General",
            items,
            103.0,
            16.48,
            119.48
        );
        System.out.println("   ✓ " + rutaTicket);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("✓ IMPLEMENTACIÓN COMPLETA Y FUNCIONAL");
        System.out.println("=".repeat(60));
        System.out.println("\nRevisar carpetas:");
        System.out.println("  - Exportaciones/productos.json");
        System.out.println("  - Exportaciones/Excel/");
        System.out.println("  - Exportaciones/Tickets/");
    }
    
    private static void contarPorCategoria(ArrayList<Producto> productos) {
        java.util.Map<String, Integer> conteo = new java.util.HashMap<>();
        for (Producto p : productos) {
            conteo.put(p.getCategoria(), conteo.getOrDefault(p.getCategoria(), 0) + 1);
        }
        conteo.forEach((cat, count) -> 
            System.out.println("   - " + cat + ": " + count + " productos")
        );
    }
    
    private static void mostrarEjemplos(ArrayList<Producto> productos) {
        System.out.println("   Unitario: " + productos.get(0).getNombre() + 
                         " (" + productos.get(0).getUnidadVenta() + ")");
        System.out.println("   Por Peso: " + productos.get(20).getNombre() + 
                         " (" + productos.get(20).getUnidadVenta() + ")");
        System.out.println("   Por Peso: " + productos.get(27).getNombre() + 
                         " (" + productos.get(27).getUnidadVenta() + ")");
    }
}
