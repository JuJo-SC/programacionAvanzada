package Parte2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * ACTIVIDAD 1 — Practica03_b
 * Administracion de Categorias usando patron MVC.
 * Opera igual que Practica03_a pero manejando Categorias.
 *
 * Nombres de componentes exactos del documento:
 *   Tid, Tcategoria, Bagregar, Beliminar, Bsalir, Tareacategoria
 */
public class Practica03_b extends JFrame implements ActionListener {

    private ControladorPractica03_b controlador;

    private JPanel      panelFormulario;
    private JLabel      labelId;
    private JLabel      labelCategoria;
    private JScrollPane scrollPane;

    // Componentes con acceso de paquete (el Controlador los usa directamente)
    JTextField  Tid;
    JTextField  Tcategoria;
    JButton     Bagregar;
    JButton     Beliminar;
    JButton     Bsalir;
    JTextArea   Tareacategoria;

    public Practica03_b() {
        super("Administracion de Categorias");
        inicializarComponentes();
        controlador = new ControladorPractica03_b(this);
        controlador.inicializarCategorias();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario);

        labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        panelFormulario.add(labelId);

        Tid = new JTextField(10);
        Tid.setEditable(false);
        Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(Tid);

        labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10, 35, 71, 20);
        panelFormulario.add(labelCategoria);

        Tcategoria = new JTextField(20);
        Tcategoria.setEditable(false);
        Tcategoria.setBounds(91, 35, 147, 20);
        panelFormulario.add(Tcategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 66, 90, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(120, 66, 90, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(220, 66, 70, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 98, 357, 220);
        panelFormulario.add(scrollPane);

        Tareacategoria = new JTextArea(10, 40);
        Tareacategoria.setEditable(false);
        scrollPane.setViewportView(Tareacategoria);

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
                    Practica03_b frame = new Practica03_b();
                    frame.setVisible(true);
                } catch (Exception e) { e.printStackTrace(); }
            }
        });
    }
}
