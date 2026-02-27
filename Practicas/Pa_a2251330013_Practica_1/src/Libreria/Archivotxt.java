package Libreria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase utilitaria para manejo de archivos de texto separados por comas (CSV).
 * Exactamente como se muestra en el documento de practica (pagina 26).
 */
public class Archivotxt {

    private String nombreArchivo;

    public Archivotxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /** Guarda el texto recibido en el archivo. */
    public void guardar(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(texto);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga las lineas del archivo.
     * Cada linea se divide por comas; retorna lista plana de elementos String.
     */
    public ArrayList<String> cargar() {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                for (String elemento : elementos) {
                    lineas.add(elemento.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
        return lineas;
    }

    /** Verifica si el archivo existe. */
    public boolean existe() {
        return new java.io.File(nombreArchivo).exists();
    }

    public String getNombreArchivo()               { return nombreArchivo; }
    public void   setNombreArchivo(String nombre)  { this.nombreArchivo = nombre; }
}
