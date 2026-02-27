package Parte1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Practica02_b — JButton con ActionListener (escucha de eventos)
 * Al presionar "Salir" muestra un mensaje de dialogo y cierra la ventana.
 * La clase implementa ActionListener y sobreescribe actionPerformed().
 * El JButton Bsalir se declara como variable global para poder evaluarlo
 * en actionPerformed().
 */
public class Practica02_b extends JFrame implements ActionListener {

    private JPanel PanelPrincipal;
    private JButton Bsalir;   // Variable global: cualquier metodo puede acceder a el

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica02_b frame = new Practica02_b();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica02_b() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Frame Practica02_a");
        PanelPrincipal = new JPanel();
        PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(PanelPrincipal);
        PanelPrincipal.setLayout(null);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(145, 124, 89, 23);
        Bsalir.addActionListener(this);   // Se registra el listener
        PanelPrincipal.add(Bsalir);
    }

    /**
     * Se sobreescribe actionPerformed de la interfaz ActionListener.
     * Verifica si el evento provino del boton Bsalir y responde.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bsalir) {
            JOptionPane.showMessageDialog(this, "Hasta Luego");
            this.dispose();
        }
    }
}
