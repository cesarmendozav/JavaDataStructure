package com.mycompany.app;

public class BuscarArchivoRecursivo {

    public static boolean buscarArchivo(Carpeta carpeta, String nombreArchivo) {
        // Caso base: buscar en archivos de esta carpeta
        for (Archivo a : carpeta.archivos) {
            if (a.nombre.equals(nombreArchivo)) {
                System.out.println("Archivo encontrado en carpeta: " + carpeta.nombre);
                return true;
            }
        }

        // Caso recursivo: buscar en subcarpetas
        for (Carpeta sub : carpeta.subcarpetas) {
            boolean encontrado = buscarArchivo(sub, nombreArchivo);
            if (encontrado) {
                return true; // Propaga hacia arriba si se encontró
            }
        }

        return false; // Caso base: no está en esta carpeta ni en sus subcarpetas
    }

    public static void main(String[] args) {
        // Construcción de ejemplo
        Carpeta documentos = new Carpeta("Documents");
        Carpeta proyecto = new Carpeta("Proyecto");
        Carpeta recursos = new Carpeta("Recursos");
        Carpeta otros = new Carpeta("Otros");

        // Archivos
        proyecto.agregarArchivo(new Archivo("reporte.pdf"));
        recursos.agregarArchivo(new Archivo("tarea.docx"));
        otros.agregarArchivo(new Archivo("notas.txt"));

        // Estructura de carpetas
        proyecto.agregarSubcarpeta(recursos);
        documentos.agregarSubcarpeta(proyecto);
        documentos.agregarSubcarpeta(otros);

        // Búsqueda
        boolean resultado = buscarArchivo(documentos, "tarea.docx");
        if (!resultado) {
            System.out.println("Archivo no encontrado.");
        }
    }
}
