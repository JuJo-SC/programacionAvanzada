package servicios;

import java.io.IOException;
import java.util.ArrayList;

import modelos.ArchivoProductos;
import modelos.GestorProductos;
import modelos.Producto;
import validadores.ValidadorProducto;

public class ServicioProductos {
    private final ValidadorProducto validador;

    public ServicioProductos() {
        this.validador = new ValidadorProducto();
    }

    public ArrayList<Producto> cargarInicial(ArchivoProductos archivo) throws IOException {
        return archivo.importarCSV();
    }

    public void guardarCSV(ArchivoProductos archivo, ArrayList<Producto> productos) throws IOException {
        archivo.exportarCSV(productos);
    }

    public Producto construirProducto(String idTexto, String codigo, String nombre, String descripcion, String categoria, String precioCompraTexto, String porcentajeGananciaTexto, String stockTexto, String stockMinimoTexto, boolean activo) {
        validador.validarCamposObligatorios(idTexto, codigo, nombre, descripcion, categoria, precioCompraTexto, porcentajeGananciaTexto, stockTexto, stockMinimoTexto);
        int id = Integer.parseInt(idTexto);
        double precioCompra = Double.parseDouble(precioCompraTexto);
        double porcentajeGanancia = Double.parseDouble(porcentajeGananciaTexto);
        int stock = Integer.parseInt(stockTexto);
        int stockMinimo = Integer.parseInt(stockMinimoTexto);
        return Producto.crear(id, codigo, nombre, descripcion, categoria, precioCompra, porcentajeGanancia, stock, stockMinimo, activo);
    }

    public boolean guardarProducto(GestorProductos gestor, Producto producto) {
        if (!gestor.existe(producto.getId())) {
            return gestor.insertar(producto);
        }
        return gestor.actualizar(producto);
    }
}
