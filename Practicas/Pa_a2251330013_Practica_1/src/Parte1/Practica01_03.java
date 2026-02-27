package Parte1;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Practica01_03 — Contenedor Principal: JDialog
 * Ventana emergente que requiere la atencion del usuario.
 */
public class Practica01_03 extends JDialog {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica01_03 dialog = new Practica01_03();
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica01_03() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Dialog Practica01_03");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }
}
