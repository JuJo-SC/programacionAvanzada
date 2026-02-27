package Parte1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Practica02_b2 — JButton con ActionListener y mnemonico de tecla
 * Igual que Practica02_b pero agrega:
 *   - setMnemonic(KeyEvent.VK_S): tecla "S" como acceso rapido
 *   - setDisplayedMnemonicIndex(0): subraya la primera letra del texto del boton
 *
 * NOTA: los objetos que se desean evaluar en actionPerformed deben
 * declararse como variables globales.
 */
public class Practica02_b2 extends JFrame implements ActionListener {

    private JPanel  PanelPrincipal;
    private JButton Bsalir;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica02_b2 frame = new Practica02_b2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica02_b2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 348, 233);
        setTitle("Frame Practica02_b2");
        PanelPrincipal = new JPanel();
        PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(PanelPrincipal);
        PanelPrincipal.setLayout(null);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(104, 87, 89, 23);
        Bsalir.setMnemonic(KeyEvent.VK_S);           // Tecla S como mnemonico
        Bsalir.setDisplayedMnemonicIndex(0);         // Subraya la primera letra
        Bsalir.addActionListener(this);
        PanelPrincipal.add(Bsalir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Bsalir) {
            JOptionPane.showMessageDialog(this, "Hasta Luego");
            this.dispose();
        }
    }
}
