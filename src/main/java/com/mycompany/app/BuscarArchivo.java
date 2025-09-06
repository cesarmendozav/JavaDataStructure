package com.mycompany.app;

import java.io.IOException;
import java.nio.file.*;

public class BuscarArchivo {

    public static Path buscarArchivo(Path dir, String nombre) throws IOException {
        if (!Files.exists(dir)) return null; // Caso base: carpeta no existe

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path p : stream) {
                if (Files.isRegularFile(p) && p.getFileName().toString().equals(nombre)) {
                    return p; // Caso base: archivo encontrado
                }
                if (Files.isDirectory(p)) {
                    Path encontrado = buscarArchivo(p, nombre); // Recursión
                    if (encontrado != null) {
                        return encontrado; // Propaga el resultado hacia arriba
                    }
                }
            }
        }
        return null; // Caso base: no se encontró en esta rama
    }

    public static void main(String[] args) throws IOException {
        Path inicio = Paths.get("C:/Users/user/Documents");
        String archivoBuscado = "tarea.docx";

        Path resultado = buscarArchivo(inicio, archivoBuscado);

        if (resultado != null) {
            System.out.println("Archivo encontrado en: " + resultado);
        } else {
            System.out.println("Archivo no encontrado.");
        }
    }
}