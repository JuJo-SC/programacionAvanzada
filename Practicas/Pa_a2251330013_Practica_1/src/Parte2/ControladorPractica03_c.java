package Parte2;

import Libreria.Archivotxt;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Controlador MVC para Practica03_c.
 * Administracion de Categorias con persistencia en archivo CSV (categorias.txt).
 */
public class ControladorPractica03_c {

    private ListaCategorias listacategorias;
    private Practica03_c    vista;
    private Archivotxt      archivo;

    private static final String NOMBRE_ARCHIVO = "categorias.txt";

    public ControladorPractica03_c(Practica03_c vista) {
        this.vista           = vista;
        this.listacategorias = new ListaCategorias();
        this.archivo         = new Archivotxt(NOMBRE_ARCHIVO);
        cargarDesdeArchivo();
    }

    /** Carga categorias desde archivo CSV al iniciar. */
    public void cargarDesdeArchivo() {
        if (archivo.existe()) {
            ArrayList<String> datos = archivo.cargar();
            // Reconstruir pares [idCategoria, nombreCategoria] de la lista plana
            for (int i = 0; i + 1 < datos.size(); i += 2) {
                Categoria cat = new Categoria(datos.get(i), datos.get(i + 1));
                listacategorias.agregarCategoria(cat);
            }
            vista.Tareacategoria.setText(listacategorias.toLineas());
        }
    }

    /** Guarda todas las categorias en el archivo CSV. */
    public void guardarEnArchivo() {
        StringBuilder sb = new StringBuilder();
        for (Categoria cat : listacategorias.getCategorias()) {
            sb.append(cat.getIdcategoria()).append(",")
              .append(cat.getCategoria()).append("\n");
        }
        archivo.guardar(sb.toString());
    }

    public void VolverAlinicio() {
        vista.Bagregar.setText("Agregar");
        vista.Bsalir.setText("Salir");
        vista.Beliminar.setEnabled(true);
        vista.Tid.setEditable(false);
        vista.Tcategoria.setEditable(false);
        vista.Tid.setText("");
        vista.Tcategoria.setText("");
    }

    public void Altas() {
        if (vista.Bagregar.getText().compareTo("Agregar") == 0) {
            vista.Bagregar.setText("Salvar");
            vista.Bsalir.setText("Cancelar");
            vista.Beliminar.setEnabled(false);
            vista.Tid.setEditable(true);
            vista.Tcategoria.setEditable(true);
            vista.Tid.requestFocus();
        } else {
            if (esDatosCompletos()) {
                String id        = vista.Tid.getText().trim();
                String categoria = vista.Tcategoria.getText().trim();
                if (listacategorias.existeId(id)) {
                    JOptionPane.showMessageDialog(vista,
                        "Lo siente el id " + id + " ya existe, lo tiene asignado "
                            + listacategorias.buscarCategoria(id),
                        "ID Duplicado", JOptionPane.WARNING_MESSAGE);
                } else {
                    listacategorias.agregarCategoria(new Categoria(id, categoria));
                    vista.Tareacategoria.setText(listacategorias.toLineas());
                    guardarEnArchivo();
                    VolverAlinicio();
                }
            }
        }
    }

    public void Eliminar() {
        Object[] opciones = listacategorias.CategoriasArreglo();
        if (opciones == null || opciones.length == 0) {
            JOptionPane.showMessageDialog(vista, "No hay categorias registradas.",
                "Lista vacia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] ids = listacategorias.idCategoriasArreglo();
        String id = (String) JOptionPane.showInputDialog(null,
            "Seleccione una opcion:", "Eliminacion de Categorias",
            JOptionPane.PLAIN_MESSAGE, null, ids, ids[0]);
        if (id != null && !id.isEmpty()) {
            listacategorias.eliminarCategoriaPorId(id);
            vista.Tareacategoria.setText(listacategorias.toLineas());
            guardarEnArchivo();
        }
    }

    private boolean esDatosCompletos() {
        if (vista.Tid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo ID no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tid.requestFocus(); return false;
        }
        if (vista.Tcategoria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo Categoria no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tcategoria.requestFocus(); return false;
        }
        return true;
    }

    public ListaCategorias getListacategorias() { return listacategorias; }
}
