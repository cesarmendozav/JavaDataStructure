package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;


class Archivo {
    String nombre;
    Archivo(String nombre) {
        this.nombre = nombre;
    }
}

class Carpeta {
    String nombre;
    List<Archivo> archivos = new ArrayList<>();
    List<Carpeta> subcarpetas = new ArrayList<>();

    Carpeta(String nombre) {
        this.nombre = nombre;
    }

    void agregarArchivo(Archivo archivo) {
        archivos.add(archivo);
    }

    void agregarSubcarpeta(Carpeta carpeta) {
        subcarpetas.add(carpeta);
    }
}
