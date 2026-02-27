package Parte2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ACTIVIDAD 4 — Practica03_d
 * Administracion de Insumos con Categorias y persistencia en archivos CSV.
 * Basada en Practica03_a con las diferencias del documento:
 *   1. Elimina ComboCategoria.setEditable(false)
 *   2. Agrega    this.ComboCategoria.setEnabled(true)
 *   3. Agrega en Altas(): this.ComboCategoria.setSelectedIndex(0)
 *   4. Carga categorias e insumos desde archivo al iniciar
 *   5. Guarda en archivo al actualizar el textarea
 *
 * Nombres de componentes identicos a Practica03_a:
 *   ComboCategoria, Tid, Tinsumo, Bagregar, Beliminar, Bsalir, areaProductos
 */
public class Practica03_d extends JFrame implements ActionListener {

    private ControladorPractica03_d controlador;

    private JPanel      panelFormulario;
    private JLabel      labelId;
    private JLabel      labelInsumo;
    private JLabel      labelCategoria;
    private JScrollPane scrollPane;

    // Componentes con acceso de paquete (el Controlador los usa directamente)
    JComboBox<Categoria> ComboCategoria;
    JTextField           Tid;
    JTextField           Tinsumo;
    JButton              Bagregar;
    JButton              Beliminar;
    JButton              Bsalir;
    JTextArea            areaProductos;

    public Practica03_d() {
        super("Administracion de Productos");
        inicializarComponentes();
        controlador = new ControladorPractica03_d(this);
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario);

        labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        panelFormulario.add(labelId);

        Tid = new JTextField(10);
        Tid.setEditable(false);
        Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(Tid);

        labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        panelFormulario.add(labelInsumo);

        Tinsumo = new JTextField(20);
        Tinsumo.setEditable(false);
        Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(Tinsumo);

        labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10, 66, 71, 20);
        panelFormulario.add(labelCategoria);

        // Actividad 4: setEnabled(true) en lugar de setEditable(false)
        ComboCategoria = new JComboBox<>();
        this.ComboCategoria.setEnabled(true);
        ComboCategoria.setBounds(91, 66, 160, 20);
        ComboCategoria.addActionListener(this);
        panelFormulario.add(ComboCategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 104, 111, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(153, 104, 111, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(274, 104, 79, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);

        areaProductos = new JTextArea(10, 40);
        areaProductos.setEditable(false);
        scrollPane.setViewportView(areaProductos);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bagregar) {
            controlador.Altas();
        } else if (e.getSource() == Beliminar) {
            controlador.Eliminar();
        } else if (e.getSource() == Bsalir) {
            if (this.Bsalir.getText().compareTo("Cancelar") == 0) {
                controlador.VolverAlinicio();
            } else {
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica03_d frame = new Practica03_d();
                    frame.setVisible(true);
                } catch (Exception e) { e.printStackTrace(); }
            }
        });
    }
}
