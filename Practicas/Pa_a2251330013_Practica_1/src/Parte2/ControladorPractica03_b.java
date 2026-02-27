package Parte2;

import javax.swing.JOptionPane;

/**
 * Controlador MVC para Practica03_b.
 * Contiene toda la logica de negocio para administracion de Categorias.
 */
public class ControladorPractica03_b {

    private ListaCategorias listacategorias;
    private Practica03_b    vista;

    public ControladorPractica03_b(Practica03_b vista) {
        this.vista           = vista;
        this.listacategorias = new ListaCategorias();
    }

    public void inicializarCategorias() {
        this.listacategorias = new ListaCategorias();
    }

    /** Devuelve la vista a su estado inicial. */
    public void VolverAlinicio() {
        vista.Bagregar.setText("Agregar");
        vista.Bsalir.setText("Salir");
        vista.Beliminar.setEnabled(true);
        vista.Tid.setEditable(false);
        vista.Tcategoria.setEditable(false);
        vista.Tid.setText("");
        vista.Tcategoria.setText("");
    }

    /** Maneja Agregar / Salvar categoria. */
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
                    Categoria nodo = new Categoria(id, categoria);
                    listacategorias.agregarCategoria(nodo);
                    vista.Tareacategoria.setText(listacategorias.toLineas());
                    VolverAlinicio();
                }
            }
        }
    }

    /** Maneja Eliminar categoria. */
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
        }
    }

    private boolean esDatosCompletos() {
        if (vista.Tid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo ID no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tid.requestFocus();
            return false;
        }
        if (vista.Tcategoria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo Categoria no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tcategoria.requestFocus();
            return false;
        }
        return true;
    }

    public ListaCategorias getListacategorias() { return listacategorias; }
}
