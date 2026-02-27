package Parte2;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * Modelo: ListaCategorias — exactamente como aparece en el PDF (pagina 19).
 */
public class ListaCategorias {

    private ArrayList<Categoria> categorias;

    public ListaCategorias() {
        this.categorias = new ArrayList<>();
    }

    /** Agrega una categoria si el ID no existe previamente. */
    public void agregarCategoria(Categoria categoria) {
        if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
            categorias.add(categoria);
        }
    }

    /** Elimina una categoria por ID. */
    public void eliminarCategoriaPorId(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            categorias.remove(categoria);
        }
    }

    /** Retorna todas las categorias como texto, una por linea. */
    public String toLineas() {
        String resultado = "";
        for (Categoria categoria : categorias) {
            resultado += categoria.toString() + "\n";
        }
        return resultado;
    }

    /** Busca por ID (uso interno). */
    private Categoria buscarCategoriaPorId(String id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdcategoria().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    /** Retorna el nombre de la categoria dado su ID. */
    public String buscarCategoria(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            return categoria.getCategoria();
        }
        return null;
    }

    /** Carga un JComboBox con las categorias y lo retorna. */
    public JComboBox<Categoria> agregarCategoriasAComboBox(JComboBox<Categoria> comboBox) {
        JComboBox<Categoria> aux = comboBox;
        aux.removeAllItems();
        for (Categoria categoria : categorias) {
            aux.addItem(categoria);
        }
        return aux;
    }

    /** Retorna las categorias como arreglo de Object. */
    public Object[] CategoriasArreglo() {
        return this.categorias.toArray();
    }

    /** Retorna los IDs de las categorias como arreglo de String. */
    public String[] idCategoriasArreglo() {
        int pos = -1;
        String[] datos = new String[this.categorias.size()];
        for (Categoria cat : this.categorias) {
            pos++;
            datos[pos] = cat.getIdcategoria();
        }
        return datos;
    }

    /** Verifica si ya existe ese ID. */
    public boolean existeId(String id) {
        return buscarCategoriaPorId(id) != null;
    }

    /** Carga categorias desde lista de arreglos [idCategoria, nombreCategoria]. */
    public void cargarCategorias(ArrayList<String[]> categoriasString) {
        for (String[] cs : categoriasString) {
            Categoria cat = new Categoria(cs[0], cs[1]);
            this.agregarCategoria(cat);
        }
    }

    public ArrayList<Categoria> getCategorias() { return categorias; }
}
