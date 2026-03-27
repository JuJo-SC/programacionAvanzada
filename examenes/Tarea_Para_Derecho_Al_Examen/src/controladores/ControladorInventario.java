package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelos.Producto;

public class ControladorInventario implements ActionListener, ListSelectionListener {
    private final ControladorContexto contexto;
    private final ControladorProductos controladorProductos;

    public ControladorInventario(ControladorContexto contexto, ControladorProductos controladorProductos) {
        this.contexto = contexto;
        this.controladorProductos = controladorProductos;
        contexto.getVistaInventario().btnBuscar.addActionListener(this);
        contexto.getVistaInventario().btnLimpiarFiltros.addActionListener(this);
        contexto.getVistaInventario().btnCrearNuevo.addActionListener(this);
        contexto.getVistaInventario().btnModificar.addActionListener(this);
        contexto.getVistaInventario().btnEliminar.addActionListener(this);
        contexto.getVistaInventario().tabla.getSelectionModel().addListSelectionListener(this);
    }

    private void ejecutarBuscarInventario() {
        Integer id = null;
        String textoId = contexto.getVistaInventario().txtFiltroId.getText().trim();
        String nombre = contexto.getVistaInventario().txtFiltroNombre.getText().trim();
        String tipo = String.valueOf(contexto.getVistaInventario().cmbTipo.getSelectedItem());
        String estado = contexto.getVistaInventario().rbDisponible.isSelected() ? "Disponible" : contexto.getVistaInventario().rbAgotado.isSelected() ? "Agotado" : "Todos";
        if (!textoId.isBlank()) {
            try {
                id = Integer.parseInt(textoId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "ID invalido en filtros.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        contexto.cargarTablaInventario(contexto.getGestor().filtrarInventario(id, nombre, tipo, estado));
    }

    private void ejecutarEliminarDesdeInventario() {
        Integer id = contexto.idDesdeFilaInventario();
        if (id == null) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Selecciona una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(contexto.getVistaPrincipal(), "¿Eliminar registro seleccionado?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (contexto.getGestor().eliminar(id)) {
                contexto.guardarCSV();
                contexto.refrescarTodo();
                contexto.limpiarFormularioProducto();
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Registro eliminado.");
            } else {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No se pudo eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == contexto.getVistaInventario().btnBuscar) {
            ejecutarBuscarInventario();
        } else if (source == contexto.getVistaInventario().btnLimpiarFiltros) {
            contexto.getVistaInventario().txtFiltroId.setText("");
            contexto.getVistaInventario().txtFiltroNombre.setText("");
            contexto.getVistaInventario().cmbTipo.setSelectedIndex(0);
            contexto.getVistaInventario().rbDisponible.setSelected(true);
            contexto.cargarTablaInventario(contexto.getGestor().getProductos());
        } else if (source == contexto.getVistaInventario().btnCrearNuevo) {
            controladorProductos.abrirNuevoProducto();
        } else if (source == contexto.getVistaInventario().btnModificar) {
            Integer id = contexto.idDesdeFilaInventario();
            if (id == null) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Selecciona una fila para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Producto p = contexto.getGestor().buscar(id);
            if (p != null) {
                controladorProductos.abrirEdicionProducto(p);
            }
        } else if (source == contexto.getVistaInventario().btnEliminar) {
            ejecutarEliminarDesdeInventario();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == contexto.getVistaInventario().tabla.getSelectionModel()) {
            int fila = contexto.getVistaInventario().tabla.getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(String.valueOf(contexto.getVistaInventario().modeloTabla.getValueAt(fila, 0)));
                Producto p = contexto.getGestor().buscar(id);
                if (p != null) {
                    contexto.cargarFormularioProducto(p);
                }
            }
        }
    }
}
