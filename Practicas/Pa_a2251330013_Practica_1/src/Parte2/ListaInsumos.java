package Parte2;

import java.util.ArrayList;

/**
 * Modelo: ListaInsumos — exactamente como aparece en el PDF (pagina 20).
 */
public class ListaInsumos {

    private ArrayList<Insumo> insumos;

    public ListaInsumos() {
        this.insumos = new ArrayList<>();
    }

    /** Agrega insumo si el ID no existe. Retorna true si se inserto. */
    public boolean agregarInsumo(Insumo insumo) {
        boolean inserto = true;
        if (buscarInsumoPorId(insumo.getIdProducto()) == null) {
            insumos.add(insumo);
        } else {
            inserto = false;
        }
        return inserto;
    }

    /** Elimina insumo por ID. Retorna true si se elimino. */
    public boolean eliminarInsumoPorId(String id) {
        boolean elimino = true;
        Insumo insumo = buscarInsumoPorId(id);
        if (insumo != null) {
            insumos.remove(insumo);
        } else {
            elimino = false;
        }
        return elimino;
    }

    /** Retorna todos los insumos como texto. */
    public String toString() {
        String resultado = "";
        for (Insumo insumo : insumos) {
            resultado += insumo.toString() + "\n";
        }
        return resultado;
    }

    /** Busca por ID (uso interno). */
    private Insumo buscarInsumoPorId(String id) {
        for (Insumo insumo : insumos) {
            if (insumo.getIdProducto().equals(id)) {
                return insumo;
            }
        }
        return null;
    }

    /** Retorna el nombre del producto dado su ID. */
    public String buscarInsumo(String id) {
        for (Insumo insumo : insumos) {
            if (insumo.getIdProducto().equals(id)) {
                return insumo.getProducto();
            }
        }
        return null;
    }

    /** Retorna los IDs como arreglo de String. */
    public String[] idinsumos() {
        int pos = -1;
        String[] datos = new String[this.insumos.size()];
        for (Insumo nodo : this.insumos) {
            pos++;
            datos[pos] = nodo.getIdProducto();
        }
        return datos;
    }

    /** Carga insumos desde lista de arreglos [id, producto, idCategoria]. */
    public void cargarInsumo(ArrayList<String[]> insumosString) {
        for (String[] cs : insumosString) {
            Insumo nodo = new Insumo(cs[0], cs[1], cs[2]);
            this.agregarInsumo(nodo);
        }
    }

    public ArrayList<Insumo> getInsumos() { return insumos; }
}
