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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaInventarioInternal extends JInternalFrame {
    public final JTextField txtFiltroId;
    public final JTextField txtFiltroNombre;
    public final JComboBox<String> cmbTipo;
    public final JRadioButton rbTodos;
    public final JRadioButton rbDisponible;
    public final JRadioButton rbAgotado;
    public final ButtonGroup groupEstado;
    public final JButton btnBuscar;
    public final JButton btnLimpiarFiltros;
    public final JButton btnCrearNuevo;
    public final JButton btnModificar;
    public final JButton btnEliminar;
    public final JTable tabla;
    public final DefaultTableModel modeloTabla;

    public VistaInventarioInternal() {
        super("Inventario", true, true, true, true);
        setSize(1260, 650);
        setVisible(true);

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros y Busqueda"));
        GridBagConstraints gf = new GridBagConstraints();
        gf.insets = new Insets(4, 4, 4, 4);
        gf.fill = GridBagConstraints.HORIZONTAL;
        gf.anchor = GridBagConstraints.WEST;

        gf.gridx = 0; gf.gridy = 0;
        panelFiltros.add(new JLabel("ID:"), gf);
        txtFiltroId = new JTextField(12);
        gf.gridx = 1;
        panelFiltros.add(txtFiltroId, gf);

        gf.gridx = 0; gf.gridy = 1;
        panelFiltros.add(new JLabel("Nombre:"), gf);
        txtFiltroNombre = new JTextField(12);
        gf.gridx = 1;
        panelFiltros.add(txtFiltroNombre, gf);

        gf.gridx = 0; gf.gridy = 2;
        panelFiltros.add(new JLabel("Tipo:"), gf);
        cmbTipo = new JComboBox<>(new String[] {
            "Todos", 
            "Despensa Básica", 
            "Lácteos y Huevo", 
            "Bebidas y Líquidos", 
            "Botanas y Dulces", 
            "Frutas y Verduras", 
            "Carnes y Salchichonería", 
            "Cuidado del Hogar", 
            "Higiene y Cuidado Personal", 
            "Alimentos Preparados/Enlatados"
        });
        gf.gridx = 1;
        panelFiltros.add(cmbTipo, gf);

        JPanel panelEstado = new JPanel(new GridBagLayout());
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        rbTodos = new JRadioButton("Todos");
        rbDisponible = new JRadioButton("Disponible", true);
        rbAgotado = new JRadioButton("Agotado");
        groupEstado = new ButtonGroup();
        groupEstado.add(rbTodos);
        groupEstado.add(rbDisponible);
        groupEstado.add(rbAgotado);
        GridBagConstraints ge = new GridBagConstraints();
        ge.gridx = 0; ge.gridy = 0; ge.anchor = GridBagConstraints.WEST;
        panelEstado.add(rbTodos, ge);
        ge.gridy = 1;
        panelEstado.add(rbDisponible, ge);
        ge.gridy = 2;
        panelEstado.add(rbAgotado, ge);
        gf.gridx = 0; gf.gridy = 3; gf.gridwidth = 2;
        panelFiltros.add(panelEstado, gf);

        btnBuscar = new JButton("Buscar");
        btnLimpiarFiltros = new JButton("Limpiar Filtros");
        JPanel pBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(3, 3, 3, 3);
        gb.gridx = 0; gb.gridy = 0;
        pBotones.add(btnBuscar, gb);
        gb.gridx = 1;
        pBotones.add(btnLimpiarFiltros, gb);
        gf.gridx = 0; gf.gridy = 4; gf.gridwidth = 2;
        panelFiltros.add(pBotones, gf);

        gbc.gridx = 0;
        gbc.weightx = 0.28;
        contenedor.add(panelFiltros, gbc);

        JPanel panelVista = new JPanel(new GridBagLayout());
        panelVista.setBorder(BorderFactory.createTitledBorder("Vista de Inventario"));
        GridBagConstraints gv = new GridBagConstraints();
        gv.insets = new Insets(4, 4, 4, 4);
        gv.fill = GridBagConstraints.BOTH;
        gv.weightx = 1;
        gv.weighty = 1;

        modeloTabla = new DefaultTableModel(new Object[] {"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane spTabla = new JScrollPane(tabla);
        spTabla.setPreferredSize(new Dimension(760, 430));
        gv.gridx = 0; gv.gridy = 0; gv.gridwidth = 3;
        panelVista.add(spTabla, gv);

        JPanel panelAcciones = new JPanel(new GridBagLayout());
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones de Seleccion"));
        btnCrearNuevo = new JButton("Crear Nuevo");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        GridBagConstraints ga = new GridBagConstraints();
        ga.insets = new Insets(3, 6, 3, 6);
        ga.gridx = 0; ga.gridy = 0;
        panelAcciones.add(btnCrearNuevo, ga);
        ga.gridx = 1;
        panelAcciones.add(btnModificar, ga);
        ga.gridx = 2;
        panelAcciones.add(btnEliminar, ga);
        gv.gridx = 0; gv.gridy = 1; gv.gridwidth = 3; gv.weighty = 0;
        panelVista.add(panelAcciones, gv);

        gbc.gridx = 1;
        gbc.weightx = 0.72;
        contenedor.add(panelVista, gbc);
        setContentPane(contenedor);
    }
}