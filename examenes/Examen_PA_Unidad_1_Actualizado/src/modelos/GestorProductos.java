package modelos;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorProductos {
    private final ArrayList<Producto> productos;

    public GestorProductos() {
        this.productos = new ArrayList<>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void reemplazarLista(ArrayList<Producto> lista) {
        productos.clear();
        productos.addAll(lista);
    }

    public boolean existe(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Producto buscar(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public boolean insertar(Producto producto) {
        if (existe(producto.getId())) {
            return false;
        }
        return productos.add(producto);
    }

    public boolean actualizar(Producto actualizado) {
        for (Producto actual : productos) {
            if (actual.getId() == actualizado.getId()) {
                actual.setCodigo(actualizado.getCodigo());
                actual.setNombre(actualizado.getNombre());
                actual.setDescripcion(actualizado.getDescripcion());
                actual.setCategoria(actualizado.getCategoria());
                actual.setPrecioCompra(actualizado.getPrecioCompra());
                actual.setPrecioVenta(actualizado.getPrecioVenta());
                actual.setStock(actualizado.getStock());
                actual.setStockMinimo(actualizado.getStockMinimo());
                actual.setActivo(actualizado.isActivo());
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public int siguienteId() {
        int max = 0;
        for (Producto producto : productos) {
            if (producto.getId() > max) {
                max = producto.getId();
            }
        }
        return max + 1;
    }

    public ArrayList<Producto> filtrarInventario(Integer id, String nombre, String tipo, String estado) {
        ArrayList<Producto> filtrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (id != null && producto.getId() != id) {
                continue;
            }
            if (nombre != null && !nombre.isBlank() && !producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                continue;
            }
            if (tipo != null && !"Todos".equalsIgnoreCase(tipo) && !producto.getCategoria().equalsIgnoreCase(tipo)) {
                continue;
            }
            if (("Disponible".equalsIgnoreCase(estado) && producto.getStock() <= 0) || ("Agotado".equalsIgnoreCase(estado) && producto.getStock() > 0)) {
                continue;
            }
            filtrados.add(producto);
        }
        return filtrados;
    }

    public ArrayList<Producto> filtrarProductos(String criterio, String valor) {
        ArrayList<Producto> filtrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (valor == null || valor.isBlank()) {
                filtrados.add(producto);
                continue;
            }
            String texto = valor.toLowerCase();
            if ("ID".equalsIgnoreCase(criterio)) {
                if (String.valueOf(producto.getId()).contains(texto)) {
                    filtrados.add(producto);
                }
            } else if ("Codigo".equalsIgnoreCase(criterio) || "Código".equalsIgnoreCase(criterio)) {
                if (producto.getCodigo().toLowerCase().contains(texto)) {
                    filtrados.add(producto);
                }
            } else if ("Nombre".equalsIgnoreCase(criterio)) {
                if (producto.getNombre().toLowerCase().contains(texto)) {
                    filtrados.add(producto);
                }
            } else if ("Categoria".equalsIgnoreCase(criterio) || "Categoría".equalsIgnoreCase(criterio)) {
                if (producto.getCategoria().toLowerCase().contains(texto)) {
                    filtrados.add(producto);
                }
            } else {
                filtrados.add(producto);
            }
        }
        return filtrados;
    }
}