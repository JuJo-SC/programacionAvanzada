package Parte2;

import Libreria.Archivotxt;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Controlador MVC para Practica03_d.
 * Administracion de Insumos con Categorias y persistencia en archivos CSV.
 * Basado en la logica de Practica03_a con soporte de archivos de texto.
 */
public class ControladorPractica03_d {

    private ListaInsumos    listainsumo;
    private ListaCategorias listacategorias;
    private Practica03_d    vista;
    private Archivotxt      archivoInsumos;
    private Archivotxt      archivoCategorias;

    private static final String ARCHIVO_INSUMOS    = "insumos.txt";
    private static final String ARCHIVO_CATEGORIAS = "categorias_prod.txt";

    public ControladorPractica03_d(Practica03_d vista) {
        this.vista             = vista;
        this.listainsumo       = new ListaInsumos();
        this.listacategorias   = new ListaCategorias();
        this.archivoInsumos    = new Archivotxt(ARCHIVO_INSUMOS);
        this.archivoCategorias = new Archivotxt(ARCHIVO_CATEGORIAS);
        inicializarcategorias();
        cargarInsumosDesdeArchivo();
    }

    /**
     * Carga categorias desde archivo si existe; si no, usa datos por defecto
     * (Materiales, Mano de Obra, Maquinaria y Equipo, Servicios).
     */
    public void inicializarcategorias() {
        if (archivoCategorias.existe()) {
            ArrayList<String> datos = archivoCategorias.cargar();
            for (int i = 0; i + 1 < datos.size(); i += 2) {
                listacategorias.agregarCategoria(new Categoria(datos.get(i), datos.get(i + 1)));
            }
        } else {
            listacategorias.agregarCategoria(new Categoria("01", "Materiales"));
            listacategorias.agregarCategoria(new Categoria("02", "Mano de Obra"));
            listacategorias.agregarCategoria(new Categoria("03", "Maquinaria y Equipo"));
            listacategorias.agregarCategoria(new Categoria("04", "Servicios"));
            guardarCategorias();
        }
        // Poblar ComboCategoria
        vista.ComboCategoria.removeAllItems();
        for (Categoria cat : listacategorias.getCategorias()) {
            vista.ComboCategoria.addItem(cat);
        }
        vista.ComboCategoria.setSelectedIndex(0);
    }

    /** Carga insumos desde archivo CSV al iniciar. */
    public void cargarInsumosDesdeArchivo() {
        if (archivoInsumos.existe()) {
            ArrayList<String> datos = archivoInsumos.cargar();
            for (int i = 0; i + 2 < datos.size(); i += 3) {
                Insumo nodo = new Insumo(datos.get(i), datos.get(i + 1), datos.get(i + 2));
                listainsumo.agregarInsumo(nodo);
            }
            vista.areaProductos.setText(listainsumo.toString());
        }
    }

    /** Guarda todos los insumos en archivo CSV. */
    public void guardarInsumos() {
        StringBuilder sb = new StringBuilder();
        for (Insumo ins : listainsumo.getInsumos()) {
            sb.append(ins.getIdProducto()).append(",")
              .append(ins.getProducto()).append(",")
              .append(ins.getIdCategoria()).append("\n");
        }
        archivoInsumos.guardar(sb.toString());
    }

    /** Guarda todas las categorias en archivo CSV. */
    public void guardarCategorias() {
        StringBuilder sb = new StringBuilder();
        for (Categoria cat : listacategorias.getCategorias()) {
            sb.append(cat.getIdcategoria()).append(",")
              .append(cat.getCategoria()).append("\n");
        }
        archivoCategorias.guardar(sb.toString());
    }

    /** Devuelve la vista a su estado inicial. */
    public void VolverAlinicio() {
        vista.Bagregar.setText("Agregar");
        vista.Bsalir.setText("Salir");
        vista.Beliminar.setEnabled(true);
        vista.Tid.setEditable(false);
        vista.Tinsumo.setEditable(false);
        vista.ComboCategoria.setEnabled(true);
        vista.Tid.setText("");
        vista.Tinsumo.setText("");
        vista.ComboCategoria.setSelectedIndex(0);  // Actividad 4
    }

    /** Maneja Agregar / Salvar insumo. */
    public void Altas() {
        if (vista.Bagregar.getText().compareTo("Agregar") == 0) {
            vista.Bagregar.setText("Salvar");
            vista.Bsalir.setText("Cancelar");
            vista.Beliminar.setEnabled(false);
            vista.Tid.setEditable(true);
            vista.Tinsumo.setEditable(true);
            vista.ComboCategoria.setEnabled(true);
            vista.ComboCategoria.setFocusable(true);
            vista.ComboCategoria.setSelectedIndex(0);  // Actividad 4
            vista.Tid.requestFocus();
        } else {
            System.out.println("aqui");
            if (esDatosCompletos()) {
                System.out.println("aqui");
                String id          = vista.Tid.getText().trim();
                String insumo      = vista.Tinsumo.getText().trim();
                String idcategoria = ((Categoria) vista.ComboCategoria.getSelectedItem())
                                         .getIdcategoria().trim();
                Insumo nodo = new Insumo(id, insumo, idcategoria);
                if (!listainsumo.agregarInsumo(nodo)) {
                    JOptionPane.showMessageDialog(vista,
                        "Lo siente el id " + id + " ya existe lo tiene asignado "
                            + listainsumo.buscarInsumo(id),
                        "ID Duplicado", JOptionPane.WARNING_MESSAGE);
                } else {
                    vista.areaProductos.setText(listainsumo.toString());
                    guardarInsumos();
                    VolverAlinicio();
                }
            }
        }
    }

    /** Maneja Eliminar insumo. */
    public void Eliminar() {
        Object[] opciones = listainsumo.idinsumos();
        if (opciones == null || opciones.length == 0) {
            JOptionPane.showMessageDialog(vista, "No hay insumos registrados.",
                "Lista vacia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String id = (String) JOptionPane.showInputDialog(null,
            "Seleccione una opcion:", "Eliminacion de Insumos",
            JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (id != null && !id.isEmpty()) {
            if (!listainsumo.eliminarInsumoPorId(id)) {
                JOptionPane.showMessageDialog(vista, "No existe este id.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vista.areaProductos.setText(listainsumo.toString());
                guardarInsumos();
            }
        }
    }

    private boolean esDatosCompletos() {
        if (vista.Tid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo ID no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tid.requestFocus(); return false;
        }
        if (vista.Tinsumo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo Insumo no puede estar vacio.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            vista.Tinsumo.requestFocus(); return false;
        }
        if (vista.ComboCategoria.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una categoria.",
                "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public ListaInsumos    getListainsumo()      { return listainsumo; }
    public ListaCategorias getListacategorias()  { return listacategorias; }
}
