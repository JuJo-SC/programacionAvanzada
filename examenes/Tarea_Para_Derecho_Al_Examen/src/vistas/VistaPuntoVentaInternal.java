package vistas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaPuntoVentaInternal extends JInternalFrame {
    public final JTextField txtCliente;
    public final JTextField txtCajero;
    public final JComboBox<String> cmbProducto;
    public final JTextField txtCantidad;
    public final JButton btnAnadir;
    public final JButton btnModificar;
    public final JButton btnEliminar;
    public final JTable tabla;
    public final DefaultTableModel modeloTabla;
    public final JTextField txtSubtotal;
    public final JTextField txtIva;
    public final JTextField txtTotal;
    public final JButton btnLimpiarCarrito;
    public final JButton btnProcesarPago;
    public final JButton btnExportarTicket;
    public final JButton btnHistorialCompras;

    public VistaPuntoVentaInternal() {
        super("Punto de Venta", true, true, true, true);
        setSize(1260, 650);
        setVisible(true);

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        contenedor.add(new JLabel("Cliente:"), gbc);
        txtCliente = new JTextField(18);
        gbc.gridx = 1;
        contenedor.add(txtCliente, gbc);
        gbc.gridx = 2;
        contenedor.add(new JLabel("Cajero:"), gbc);
        txtCajero = new JTextField(18);
        gbc.gridx = 3;
        contenedor.add(txtCajero, gbc);

        JPanel seleccion = new JPanel(new GridBagLayout());
        seleccion.setBorder(BorderFactory.createTitledBorder("--- SELECCION DE PRODUCTO ---"));
        GridBagConstraints gs = new GridBagConstraints();
        gs.insets = new Insets(4, 4, 4, 4);
        gs.fill = GridBagConstraints.HORIZONTAL;
        gs.gridx = 0; gs.gridy = 0;
        seleccion.add(new JLabel("Producto:"), gs);
        cmbProducto = new JComboBox<>();
        cmbProducto.setPreferredSize(new Dimension(280, 28));
        gs.gridx = 1;
        seleccion.add(cmbProducto, gs);
        gs.gridx = 2;
        seleccion.add(new JLabel("Cantidad:"), gs);
        txtCantidad = new JTextField(10);
        gs.gridx = 3;
        seleccion.add(txtCantidad, gs);

        btnAnadir = new JButton("Anadir a Carrito");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        gs.gridx = 0; gs.gridy = 1;
        seleccion.add(btnAnadir, gs);
        gs.gridx = 1;
        seleccion.add(btnModificar, gs);
        gs.gridx = 2;
        seleccion.add(btnEliminar, gs);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4; gbc.weightx = 1;
        contenedor.add(seleccion, gbc);

        JPanel panelTabla = new JPanel(new GridBagLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Detalles Transaccion Actual"));
        GridBagConstraints gt = new GridBagConstraints();
        gt.insets = new Insets(4, 4, 4, 4);
        gt.fill = GridBagConstraints.BOTH;
        gt.weightx = 1;
        gt.weighty = 1;
        modeloTabla = new DefaultTableModel(new Object[] {"Cod", "Descrip", "Cant", "P.Unit", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane sp = new JScrollPane(tabla);
        sp.setPreferredSize(new Dimension(1120, 180));
        gt.gridx = 0; gt.gridy = 0;
        panelTabla.add(sp, gt);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        contenedor.add(panelTabla, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        contenedor.add(new JLabel("Subtotal:"), gbc);
        txtSubtotal = new JTextField(10);
        txtSubtotal.setEditable(false);
        gbc.gridx = 1;
        contenedor.add(txtSubtotal, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        contenedor.add(new JLabel("IVA:"), gbc);
        txtIva = new JTextField(10);
        txtIva.setEditable(false);
        gbc.gridx = 1;
        contenedor.add(txtIva, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        contenedor.add(new JLabel("Total a Pagar:"), gbc);
        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);
        gbc.gridx = 1;
        contenedor.add(txtTotal, gbc);

        btnLimpiarCarrito = new JButton("Limpiar Carrito");
        btnProcesarPago = new JButton("Procesar Pago");
        btnExportarTicket = new JButton("Exportar Ticket");
        btnHistorialCompras = new JButton("Historial Compras");
        gbc.gridx = 2; gbc.gridy = 5;
        contenedor.add(btnLimpiarCarrito, gbc);
        gbc.gridx = 3;
        contenedor.add(btnProcesarPago, gbc);
        gbc.gridx = 2; gbc.gridy = 4;
        contenedor.add(btnExportarTicket, gbc);
        gbc.gridx = 3; gbc.gridy = 4;
        contenedor.add(btnHistorialCompras, gbc);

        setContentPane(contenedor);
    }
}
