package main;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import controladores.ControladorCatalogo;
import modelos.ArchivoProductos;
import modelos.GestorProductos;
import vistas.VistaInventarioInternal;
import vistas.VistaPrincipalMDI;
import vistas.VistaProductosInternal;
import vistas.VistaPuntoVentaInternal;

public class Main {
    private static void aumentarFuenteGlobal(int incremento) {
        Enumeration<Object> claves = UIManager.getDefaults().keys();
        while (claves.hasMoreElements()) {
            Object clave = claves.nextElement();
            Object valor = UIManager.get(clave);
            if (valor instanceof FontUIResource) {
                FontUIResource fuente = (FontUIResource) valor;
                UIManager.put(clave, new FontUIResource(new Font(fuente.getName(), fuente.getStyle(), fuente.getSize() + incremento)));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                aumentarFuenteGlobal(4);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            VistaPrincipalMDI principal = new VistaPrincipalMDI();
            VistaProductosInternal productos = new VistaProductosInternal();
            VistaInventarioInternal inventario = new VistaInventarioInternal();
            VistaPuntoVentaInternal puntoVenta = new VistaPuntoVentaInternal();
            GestorProductos gestor = new GestorProductos();
            ArchivoProductos archivo = new ArchivoProductos();
            new ControladorCatalogo(principal, productos, inventario, puntoVenta, gestor, archivo);
            principal.setVisible(true);
        });
    }
}