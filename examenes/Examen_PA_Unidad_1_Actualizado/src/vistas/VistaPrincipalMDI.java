package vistas;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class VistaPrincipalMDI extends JFrame {
    public final JDesktopPane desktopPane;
    public final JMenuItem menuProductos;
    public final JMenuItem menuInventario;
    public final JMenuItem menuPuntoVenta;
    public final JMenuItem menuSalir;
    // Nuevos menús de exportación
    public final JMenuItem menuExportarJSON;
    public final JMenuItem menuExportarExcel;
    public final JMenuItem menuExportarPorCategoria;
    public final JMenuItem menuCargarDatos;

    public VistaPrincipalMDI() {
        setTitle("Sistema de Catalogo - POS");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1320, 760);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();
        
        // Menú Sistema
        JMenu menuSistema = new JMenu("Sistema");
        menuProductos = new JMenuItem("Productos");
        menuInventario = new JMenuItem("Inventario");
        menuPuntoVenta = new JMenuItem("Punto de Venta");
        menuSalir = new JMenuItem("Salir");
        menuSistema.add(menuProductos);
        menuSistema.add(menuInventario);
        menuSistema.add(menuPuntoVenta);
        menuSistema.addSeparator();
        menuSistema.add(menuSalir);
        
        // Menú Exportar
        JMenu menuExportar = new JMenu("Exportar");
        menuExportarJSON = new JMenuItem("Productos a JSON");
        menuExportarExcel = new JMenuItem("Productos a Excel/CSV");
        menuExportarPorCategoria = new JMenuItem("Productos por Categoría");
        menuExportar.add(menuExportarJSON);
        menuExportar.add(menuExportarExcel);
        menuExportar.add(menuExportarPorCategoria);
        
        // Menú Datos
        JMenu menuDatos = new JMenu("Datos");
        menuCargarDatos = new JMenuItem("Cargar 69 Productos Iniciales");
        menuDatos.add(menuCargarDatos);
        
        menuBar.add(menuSistema);
        menuBar.add(menuExportar);
        menuBar.add(menuDatos);
        setJMenuBar(menuBar);
    }
}
