package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelos.Producto;

public class ControladorProductos implements ActionListener, ListSelectionListener {
    private final ControladorContexto contexto;

    public ControladorProductos(ControladorContexto contexto) {
        this.contexto = contexto;
        contexto.getVistaProductos().btnGuardar.addActionListener(this);
        contexto.getVistaProductos().btnLimpiar.addActionListener(this);
        contexto.getVistaProductos().btnBuscar.addActionListener(this);
        contexto.getVistaProductos().btnMostrarTodos.addActionListener(this);
        contexto.getVistaProductos().btnExportar.addActionListener(this);
        contexto.getVistaProductos().tabla.getSelectionModel().addListSelectionListener(this);
    }

    public void abrirNuevoProducto() {
        contexto.traerAlFrente(contexto.getVistaProductos());
        contexto.limpiarFormularioProducto();
    }

    public void abrirEdicionProducto(Producto producto) {
        contexto.traerAlFrente(contexto.getVistaProductos());
        contexto.cargarFormularioProducto(producto);
    }

    private void ejecutarGuardarProducto() {
        try {
            Producto producto = contexto.construirProductoDesdeFormulario();
            boolean existe = contexto.getGestor().existe(producto.getId());
            if (!contexto.getServicioProductos().guardarProducto(contexto.getGestor(), producto)) {
                if (!existe) {
                    JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No fue posible insertar el registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No fue posible modificar el registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                return;
            }
            if (!existe) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Registro insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Registro actualizado correctamente.");
            }
            contexto.guardarCSV();
            contexto.refrescarTodo();
            contexto.limpiarFormularioProducto();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Formato numerico invalido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), e.getMessage(), "Validacion", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ejecutarBuscarProductos() {
        String criterio = String.valueOf(contexto.getVistaProductos().cmbBuscarPor.getSelectedItem());
        String valor = contexto.getVistaProductos().txtBuscarValor.getText().trim();
        if ("ID".equalsIgnoreCase(criterio) && !valor.isBlank()) {
            try {
                int id = Integer.parseInt(valor);
                Producto encontrado = contexto.getGestor().buscar(id);
                if (encontrado == null) {
                    JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "No se encontro el ID indicado.", "Busqueda", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    contexto.cargarFormularioProducto(encontrado);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "El campo ID solo permite numeros.", "Validacion", JOptionPane.WARNING_MESSAGE);
            }
        }
        contexto.cargarTablaProductos(contexto.getGestor().filtrarProductos(criterio, valor));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == contexto.getVistaProductos().btnGuardar) {
            ejecutarGuardarProducto();
        } else if (source == contexto.getVistaProductos().btnLimpiar) {
            contexto.limpiarFormularioProducto();
        } else if (source == contexto.getVistaProductos().btnBuscar) {
            ejecutarBuscarProductos();
        } else if (source == contexto.getVistaProductos().btnMostrarTodos) {
            contexto.cargarTablaProductos(contexto.getGestor().getProductos());
        } else if (source == contexto.getVistaProductos().btnExportar) {
            contexto.guardarCSV();
            JOptionPane.showMessageDialog(contexto.getVistaPrincipal(), "Lista exportada en data/productos.csv");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == contexto.getVistaProductos().tabla.getSelectionModel()) {
            int fila = contexto.getVistaProductos().tabla.getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(String.valueOf(contexto.getVistaProductos().modeloTabla.getValueAt(fila, 0)));
                Producto p = contexto.getGestor().buscar(id);
                if (p != null) {
                    contexto.cargarFormularioProducto(p);
                }
            }
        }
    }
}
