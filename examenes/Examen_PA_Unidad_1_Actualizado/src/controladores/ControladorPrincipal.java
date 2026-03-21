package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exportacion.ExportadorExcel;
import exportacion.ExportadorJSON;
import modelos.DatosIniciales;
import modelos.Producto;

public class ControladorPrincipal implements ActionListener {
    private final ControladorContexto contexto;

    public ControladorPrincipal(ControladorContexto contexto) {
        this.contexto = contexto;
        contexto.getVistaPrincipal().menuProductos.addActionListener(this);
        contexto.getVistaPrincipal().menuInventario.addActionListener(this);
        contexto.getVistaPrincipal().menuPuntoVenta.addActionListener(this);
        contexto.getVistaPrincipal().menuSalir.addActionListener(this);
        
        // Nuevos menús de exportación
        contexto.getVistaPrincipal().menuExportarJSON.addActionListener(this);
        contexto.getVistaPrincipal().menuExportarExcel.addActionListener(this);
        contexto.getVistaPrincipal().menuExportarPorCategoria.addActionListener(this);
        contexto.getVistaPrincipal().menuCargarDatos.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == contexto.getVistaPrincipal().menuProductos) {
            contexto.traerAlFrente(contexto.getVistaProductos());
        } else if (source == contexto.getVistaPrincipal().menuInventario) {
            contexto.traerAlFrente(contexto.getVistaInventario());
        } else if (source == contexto.getVistaPrincipal().menuPuntoVenta) {
            contexto.traerAlFrente(contexto.getVistaPuntoVenta());
        } else if (source == contexto.getVistaPrincipal().menuSalir) {
            contexto.getVistaPrincipal().dispose();
        }
        // Nuevas acciones de exportación
        else if (source == contexto.getVistaPrincipal().menuExportarJSON) {
            exportarJSON();
        } else if (source == contexto.getVistaPrincipal().menuExportarExcel) {
            exportarExcel();
        } else if (source == contexto.getVistaPrincipal().menuExportarPorCategoria) {
            exportarPorCategoria();
        } else if (source == contexto.getVistaPrincipal().menuCargarDatos) {
            cargarDatosIniciales();
        }
    }
    
    private void exportarJSON() {
        try {
            ArrayList<Producto> productos = contexto.getGestor().getProductos();
            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                    "No hay productos para exportar.\nPrimero carga los datos iniciales desde el menú Datos.",
                    "Sin productos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String ruta = ExportadorJSON.exportarProductos(productos);
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "✓ Productos exportados a JSON\n\n" + ruta + "\n\nTotal: " + productos.size() + " productos",
                "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "Error al exportar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exportarExcel() {
        try {
            ArrayList<Producto> productos = contexto.getGestor().getProductos();
            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                    "No hay productos para exportar.",
                    "Sin productos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String ruta = ExportadorExcel.exportarProductos(productos);
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "✓ Productos exportados a Excel/CSV\n\n" + ruta + "\n\nTotal: " + productos.size() + " productos",
                "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "Error al exportar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exportarPorCategoria() {
        try {
            ArrayList<Producto> productos = contexto.getGestor().getProductos();
            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                    "No hay productos para exportar.",
                    "Sin productos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String resultado = ExportadorExcel.exportarProductosPorCategoria(productos);
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "✓ Productos exportados por categoría\n\n" + resultado,
                "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                "Error al exportar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosIniciales() {
        int respuesta = JOptionPane.showConfirmDialog(contexto.getVistaPrincipal(),
            "¿Cargar los 69 productos iniciales en 11 categorías?\n\n" +
            "Esto agregará productos de ejemplo al sistema.",
            "Confirmar carga", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ArrayList<Producto> productos = DatosIniciales.cargarProductos();
                // Agregar cada producto al gestor
                int agregados = 0;
                for (Producto p : productos) {
                    if (!contexto.getGestor().existe(p.getId())) {
                        contexto.getGestor().insertar(p);
                        agregados++;
                    }
                }
                // Guardar a CSV
                contexto.getArchivo().exportarCSV(contexto.getGestor().getProductos());
                
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                    "✓ Datos cargados exitosamente\n\n" +
                    "Total productos agregados: " + agregados + "\n" +
                    "11 categorías incluidas:\n" +
                    "- Abarrotes, Bebidas, Lácteos, Frutas y Verduras\n" +
                    "- Carnes, Salchichonería, Panadería, Limpieza\n" +
                    "- Cuidado Personal, Snacks, Mascotas",
                    "Carga completada", JOptionPane.INFORMATION_MESSAGE);
                    
                // Actualizar vistas si están abiertas
                if (contexto.getVistaInventario() != null) {
                	contexto.cargarTablaInventario(contexto.getGestor().getProductos());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(),
                    "Error al cargar datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}