package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import modelos.ArchivoProductos;
import modelos.GestorProductos;
import modelos.Producto;
import servicios.ServicioProductos;
import vistas.VistaInventarioInternal;
import vistas.VistaPrincipalMDI;
import vistas.VistaProductosInternal;
import vistas.VistaPuntoVentaInternal;

public class ControladorContexto {
    private final VistaPrincipalMDI vistaPrincipal;
    private final VistaProductosInternal vistaProductos;
    private final VistaInventarioInternal vistaInventario;
    private final VistaPuntoVentaInternal vistaPuntoVenta;
    private final GestorProductos gestor;
    private final ArchivoProductos archivo;
    private final ServicioProductos servicioProductos;

    public ControladorContexto(VistaPrincipalMDI vistaPrincipal, VistaProductosInternal vistaProductos, VistaInventarioInternal vistaInventario, VistaPuntoVentaInternal vistaPuntoVenta, GestorProductos gestor, ArchivoProductos archivo) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaProductos = vistaProductos;
        this.vistaInventario = vistaInventario;
        this.vistaPuntoVenta = vistaPuntoVenta;
        this.gestor = gestor;
        this.archivo = archivo;
        this.servicioProductos = new ServicioProductos();
    }

    public VistaPrincipalMDI getVistaPrincipal() {
        return vistaPrincipal;
    }

    public VistaProductosInternal getVistaProductos() {
        return vistaProductos;
    }

    public VistaInventarioInternal getVistaInventario() {
        return vistaInventario;
    }

    public VistaPuntoVentaInternal getVistaPuntoVenta() {
        return vistaPuntoVenta;
    }

    public GestorProductos getGestor() {
        return gestor;
    }

    public ArchivoProductos getArchivo() {
        return archivo;
    }

    public ServicioProductos getServicioProductos() {
        return servicioProductos;
    }

    public void cargarDatosIniciales() {
        try {
            gestor.reemplazarLista(servicioProductos.cargarInicial(archivo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Error al importar CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentanasIniciales() {
        mostrarVentana(vistaProductos);
        mostrarVentana(vistaInventario);
        mostrarVentana(vistaPuntoVenta);
    }

    public void mostrarVentana(JInternalFrame frame) {
        vistaPrincipal.desktopPane.add(frame);
        frame.setVisible(true);
        frame.setLocation(20, 20);
    }

    public void traerAlFrente(JInternalFrame frame) {
        try {
            frame.setIcon(false);
            frame.setSelected(true);
            frame.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaPrincipal, "No se pudo enfocar la ventana: " + e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refrescarTodo() {
        cargarTablaProductos(gestor.getProductos());
        cargarTablaInventario(gestor.getProductos());
        actualizarComboProductosVenta();
    }

    public void cargarTablaProductos(ArrayList<Producto> lista) {
        vistaProductos.modeloTabla.setRowCount(0);
        for (Producto p : lista) {
            vistaProductos.modeloTabla.addRow(new Object[] {p.getId(), p.getCodigo(), p.getNombre(), p.getCategoria(), p.getStock(), String.format(Locale.US, "%.2f", p.getPrecioVenta()), p.isActivo() ? "Activo" : "Desactivado"});
        }
    }

    public void cargarTablaInventario(ArrayList<Producto> lista) {
        vistaInventario.modeloTabla.setRowCount(0);
        for (Producto p : lista) {
            vistaInventario.modeloTabla.addRow(new Object[] {p.getId(), p.getNombre(), p.getCategoria(), p.getStock(), String.format(Locale.US, "%.2f", p.getPrecioVenta()), p.getStock() > 0 ? "Disponible" : "Agotado"});
        }
    }

    public void actualizarComboProductosVenta() {
        vistaPuntoVenta.cmbProducto.removeAllItems();
        for (Producto p : gestor.getProductos()) {
            if (p.isActivo() && p.getStock() > 0) {
                vistaPuntoVenta.cmbProducto.addItem(p.getId() + " - " + p.getCodigo() + " - " + p.getNombre() + " (Stock: " + p.getStock() + ")");
            }
        }
    }

    public void limpiarFormularioProducto() {
        vistaProductos.txtId.setText(String.valueOf(gestor.siguienteId()));
        vistaProductos.txtCodigo.setText("");
        vistaProductos.txtNombre.setText("");
        vistaProductos.txtDescripcion.setText("");
        vistaProductos.cmbCategoria.setSelectedIndex(0);
        vistaProductos.txtPrecioCompra.setText("");
        vistaProductos.txtPorcentajeGanancia.setText("");
        vistaProductos.txtPrecioVenta.setText("");
        vistaProductos.txtStockInicial.setText("");
        vistaProductos.txtStockMinimo.setText("");
        vistaProductos.rbActivo.setSelected(true);
        vistaProductos.txtBuscarValor.setText("");
    }

    public void cargarFormularioProducto(Producto p) {
        vistaProductos.txtId.setText(String.valueOf(p.getId()));
        vistaProductos.txtCodigo.setText(p.getCodigo());
        vistaProductos.txtNombre.setText(p.getNombre());
        vistaProductos.txtDescripcion.setText(p.getDescripcion());
        vistaProductos.cmbCategoria.setSelectedItem(p.getCategoria());
        vistaProductos.txtPrecioCompra.setText(String.format(Locale.US, "%.2f", p.getPrecioCompra()));
        vistaProductos.txtPorcentajeGanancia.setText(String.format(Locale.US, "%.2f", p.getPorcentajeGanancia()));
        vistaProductos.txtPrecioVenta.setText(String.format(Locale.US, "%.2f", p.getPrecioVenta()));
        vistaProductos.txtStockInicial.setText(String.valueOf(p.getStock()));
        vistaProductos.txtStockMinimo.setText(String.valueOf(p.getStockMinimo()));
        if (p.isActivo()) {
            vistaProductos.rbActivo.setSelected(true);
        } else {
            vistaProductos.rbDesactivado.setSelected(true);
        }
    }

    public Producto construirProductoDesdeFormulario() {
        String idTexto = vistaProductos.txtId.getText().trim();
        String codigo = vistaProductos.txtCodigo.getText().trim();
        String nombre = vistaProductos.txtNombre.getText().trim();
        String descripcion = vistaProductos.txtDescripcion.getText().trim();
        String categoria = String.valueOf(vistaProductos.cmbCategoria.getSelectedItem());
        String precioCompraTexto = vistaProductos.txtPrecioCompra.getText().trim();
        String porcentajeGananciaTexto = vistaProductos.txtPorcentajeGanancia.getText().trim();
        String stockTexto = vistaProductos.txtStockInicial.getText().trim();
        String stockMinimoTexto = vistaProductos.txtStockMinimo.getText().trim();

        boolean activo = vistaProductos.rbActivo.isSelected();
        return servicioProductos.construirProducto(idTexto, codigo, nombre, descripcion, categoria, precioCompraTexto, porcentajeGananciaTexto, stockTexto, stockMinimoTexto, activo);
    }

    public void guardarCSV() {
        try {
            servicioProductos.guardarCSV(archivo, gestor.getProductos());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Error al guardar CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer idDesdeFilaInventario() {
        int fila = vistaInventario.tabla.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        Object valor = vistaInventario.modeloTabla.getValueAt(fila, 0);
        return Integer.parseInt(String.valueOf(valor));
    }
}
