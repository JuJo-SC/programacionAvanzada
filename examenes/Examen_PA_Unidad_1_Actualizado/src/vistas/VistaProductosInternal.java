package vistas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaProductosInternal extends JInternalFrame {
    public final JTextField txtId;
    public final JTextField txtCodigo;
    public final JTextField txtNombre;
    public final JTextArea txtDescripcion;
    public final JComboBox<String> cmbCategoria;
    public final JTextField txtPrecioCompra;
    public final JTextField txtPrecioVenta;
    public final JTextField txtStockInicial;
    public final JTextField txtStockMinimo;
    public final JRadioButton rbActivo;
    public final JRadioButton rbDesactivado;
    public final ButtonGroup groupEstado;
    public final JButton btnGuardar;
    public final JButton btnLimpiar;
    public final JComboBox<String> cmbBuscarPor;
    public final JTextField txtBuscarValor;
    public final JButton btnBuscar;
    public final JButton btnMostrarTodos;
    public final JButton btnExportar;
    public final JTable tabla;
    public final DefaultTableModel modeloTabla;

    public VistaProductosInternal() {
        super("Productos", true, true, true, true);
        setSize(1260, 650);
        setVisible(true);

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Alta y Edicion"));
        GridBagConstraints gf = new GridBagConstraints();
        gf.insets = new Insets(4, 4, 4, 4);
        gf.fill = GridBagConstraints.HORIZONTAL;
        gf.anchor = GridBagConstraints.WEST;

        gf.gridx = 0; gf.gridy = 0;
        panelFormulario.add(new JLabel("ID [Auto]:"), gf);
        txtId = new JTextField(10);
        txtId.setEnabled(false);
        gf.gridx = 1;
        panelFormulario.add(txtId, gf);

        gf.gridx = 0; gf.gridy = 1;
        panelFormulario.add(new JLabel("Codigo:"), gf);
        txtCodigo = new JTextField(20);
        gf.gridx = 1;
        panelFormulario.add(txtCodigo, gf);

        gf.gridx = 0; gf.gridy = 2;
        panelFormulario.add(new JLabel("Nombre del Producto:"), gf);
        txtNombre = new JTextField(20);
        gf.gridx = 1;
        panelFormulario.add(txtNombre, gf);

        gf.gridx = 0; gf.gridy = 3;
        panelFormulario.add(new JLabel("Descripcion:"), gf);
        txtDescripcion = new JTextArea(4, 20);
        JScrollPane spDescripcion = new JScrollPane(txtDescripcion);
        gf.gridx = 1;
        panelFormulario.add(spDescripcion, gf);

        gf.gridx = 0; gf.gridy = 4;
        panelFormulario.add(new JLabel("Categoria:"), gf);
        cmbCategoria = new JComboBox<>(new String[] {"Alimentos", "Bebidas", "Limpieza", "Electronica", "Ropa", "Hogar", "Papeleria"});
        gf.gridx = 1;
        panelFormulario.add(cmbCategoria, gf);

        gf.gridx = 0; gf.gridy = 5;
        panelFormulario.add(new JLabel("Precio Compra:"), gf);
        txtPrecioCompra = new JTextField(10);
        gf.gridx = 1;
        panelFormulario.add(txtPrecioCompra, gf);

        gf.gridx = 0; gf.gridy = 6;
        panelFormulario.add(new JLabel("Precio Venta:"), gf);
        txtPrecioVenta = new JTextField(10);
        gf.gridx = 1;
        panelFormulario.add(txtPrecioVenta, gf);

        gf.gridx = 0; gf.gridy = 7;
        panelFormulario.add(new JLabel("Stock Inicial:"), gf);
        txtStockInicial = new JTextField(10);
        gf.gridx = 1;
        panelFormulario.add(txtStockInicial, gf);

        gf.gridx = 0; gf.gridy = 8;
        panelFormulario.add(new JLabel("Stock Minimo Alerta:"), gf);
        txtStockMinimo = new JTextField(10);
        gf.gridx = 1;
        panelFormulario.add(txtStockMinimo, gf);

        JPanel estadoPanel = new JPanel(new GridBagLayout());
        estadoPanel.setBorder(BorderFactory.createTitledBorder("Estado Actual"));
        rbActivo = new JRadioButton("Activo", true);
        rbDesactivado = new JRadioButton("Desactivado");
        groupEstado = new ButtonGroup();
        groupEstado.add(rbActivo);
        groupEstado.add(rbDesactivado);
        GridBagConstraints ge = new GridBagConstraints();
        ge.gridx = 0; ge.gridy = 0; ge.anchor = GridBagConstraints.WEST;
        estadoPanel.add(rbActivo, ge);
        ge.gridx = 1;
        estadoPanel.add(rbDesactivado, ge);
        gf.gridx = 0; gf.gridy = 9; gf.gridwidth = 2;
        panelFormulario.add(estadoPanel, gf);

        JPanel botonesFormulario = new JPanel(new GridBagLayout());
        btnGuardar = new JButton("Guardar Cambios");
        btnLimpiar = new JButton("Limpiar Formulario");
        GridBagConstraints gbf = new GridBagConstraints();
        gbf.insets = new Insets(3, 3, 3, 3);
        gbf.gridx = 0; gbf.gridy = 0; gbf.fill = GridBagConstraints.HORIZONTAL;
        botonesFormulario.add(btnGuardar, gbf);
        gbf.gridx = 1;
        botonesFormulario.add(btnLimpiar, gbf);
        gf.gridx = 0; gf.gridy = 10; gf.gridwidth = 2;
        panelFormulario.add(botonesFormulario, gf);

        gbc.gridx = 0;
        gbc.weightx = 0.38;
        contenedor.add(panelFormulario, gbc);

        JPanel panelCatalogo = new JPanel(new GridBagLayout());
        panelCatalogo.setBorder(BorderFactory.createTitledBorder("Catalogo de Productos"));
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;

        modeloTabla = new DefaultTableModel(new Object[] {"ID", "Codigo", "Nombre", "Categoria", "Stock", "P.Venta", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane spTabla = new JScrollPane(tabla);
        spTabla.setPreferredSize(new Dimension(720, 420));
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 5;
        panelCatalogo.add(spTabla, gc);

        JPanel acciones = new JPanel(new GridBagLayout());
        acciones.setBorder(BorderFactory.createTitledBorder("Acciones y Filtros"));
        GridBagConstraints ga = new GridBagConstraints();
        ga.insets = new Insets(3, 3, 3, 3);
        ga.fill = GridBagConstraints.HORIZONTAL;
        ga.gridx = 0; ga.gridy = 0;
        acciones.add(new JLabel("Buscar por:"), ga);
        cmbBuscarPor = new JComboBox<>(new String[] {"ID", "Codigo", "Nombre", "Categoria"});
        ga.gridx = 1;
        acciones.add(cmbBuscarPor, ga);
        txtBuscarValor = new JTextField(12);
        ga.gridx = 2;
        acciones.add(txtBuscarValor, ga);
        btnBuscar = new JButton("Buscar");
        ga.gridx = 3;
        acciones.add(btnBuscar, ga);
        btnMostrarTodos = new JButton("Mostrar Todos");
        ga.gridx = 4;
        acciones.add(btnMostrarTodos, ga);
        btnExportar = new JButton("Exportar Lista");
        ga.gridx = 5;
        acciones.add(btnExportar, ga);
        gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 5; gc.weighty = 0;
        panelCatalogo.add(acciones, gc);

        gbc.gridx = 1;
        gbc.weightx = 0.62;
        contenedor.add(panelCatalogo, gbc);
        setContentPane(contenedor);
    }
}
