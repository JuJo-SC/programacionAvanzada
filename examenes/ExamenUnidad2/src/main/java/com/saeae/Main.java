package com.saeae;

import com.formdev.flatlaf.FlatLightLaf;
import com.saeae.controller.ControladorBaseDatos;
import com.saeae.controller.ControladorEvaluaciones;
import com.saeae.controller.ControladorInstrumentos;
import com.saeae.controller.ControladorReportes;
import com.saeae.view.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Punto de entrada de la aplicacion SAE-AE.
 *
 * Crea los 4 controladores especializados y los inyecta en la vista.
 *   ControladorEvaluaciones  — CRUD de evaluaciones.json
 *   ControladorBaseDatos   — lectura de Datosbase.xlsx
 *   ControladorReportes     — generacion de Excel
 *   ControladorInstrumentos — logica de negocio pura (sin I/O)
 */
public class Main {

    public static void main(String[] args) {
        // Look & Feel: FlatLaf moderno con tipografia institucional
        try {
            FlatLightLaf.setup();
            UIManager.put("defaultFont", new Font("Tahoma", Font.PLAIN, 13));
        } catch (Exception e) {
            System.err.println("FlatLaf no disponible. Usando L&F del sistema.");
        }

        // Lanzar en el Event Dispatch Thread de Swing
        SwingUtilities.invokeLater(() -> {
            // Instanciar los 4 controladores (responsabilidad unica cada uno)
            ControladorEvaluaciones  ctrlEval        = new ControladorEvaluaciones();
            ControladorBaseDatos   ctrlDatos       = new ControladorBaseDatos();
            ControladorReportes     ctrlReporte     = new ControladorReportes();
            ControladorInstrumentos ctrlInstrumento = new ControladorInstrumentos();

            // Inyectar en la vista
            InterfazPrincipal view = new InterfazPrincipal(ctrlEval, ctrlDatos, ctrlReporte, ctrlInstrumento);
            view.setVisible(true);
        });
    }
}
