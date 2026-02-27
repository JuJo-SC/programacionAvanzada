package Parte1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 * Practica01_01_b — Contenedores Secundarios
 * JFrame que contiene: JTabbedPane con 4 pestanas:
 *   Pestana 1: JPanel vacio
 *   Pestana 2: JScrollPane con barras siempre visibles
 *   Pestana 3: JDesktopPane
 *   Pestana 4: JInternalFrame dentro de JDesktopPane
 */
public class Practica01_01_b extends JFrame {

    private JTabbedPane contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica01_01_b frame = new Practica01_01_b();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica01_01_b() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Frame Practica01_01_b");

        // Se crea el JTabbedPane como contenido principal
        contentPane = new JTabbedPane();

        // Pestana 1: JPanel vacio
        contentPane.addTab("Pestana 1", new JPanel());

        // Pestana 2: JScrollPane con barras siempre visibles
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.addTab("Pestana 2", scrollPane);

        // Pestana 3: JDesktopPane
        JDesktopPane desktopPane = new JDesktopPane();
        contentPane.addTab("Pestana 3", null, desktopPane, null);

        // Pestana 4: JDesktopPane con JInternalFrame visible
        JDesktopPane desktopPane2 = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
        internalFrame.setBounds(0, 0, 200, 150);
        desktopPane2.add(internalFrame);
        internalFrame.setVisible(true);
        contentPane.addTab("Pestana 4", null, desktopPane2, null);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPane, BorderLayout.CENTER);
    }
}
