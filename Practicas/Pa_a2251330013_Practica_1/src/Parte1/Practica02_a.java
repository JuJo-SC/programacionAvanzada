package Parte1;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Practica02_a — Componente JButton sin ActionListener
 * El boton "Salir" se muestra pero no realiza ninguna accion al presionarlo.
 */
public class Practica02_a extends JFrame {

    private JPanel PanelPrincipal;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica02_a frame = new Practica02_a();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica02_a() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Frame Practica02_a");
        PanelPrincipal = new JPanel();
        PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(PanelPrincipal);
        PanelPrincipal.setLayout(null);

        // Se crea el JButton y se posiciona con setBounds
        JButton Bsalir = new JButton("Salir");
        Bsalir.setBounds(145, 124, 89, 23);
        PanelPrincipal.add(Bsalir);
    }
}
