package com.mycompany.app;

public class BusquedaProducto {

    public static int buscarProducto(String[] catalogo, String producto, int inicio, int fin) {
        if (inicio > fin) {
            return -1; // no encontrado
        }

        int medio = (inicio + fin) / 2;
        int comparacion = producto.compareToIgnoreCase(catalogo[medio]);

        if (comparacion == 0) {
            return medio; // encontrado
        } else if (comparacion < 0) {
            return buscarProducto(catalogo, producto, inicio, medio - 1);
        } else {
            return buscarProducto(catalogo, producto, medio + 1, fin);
        }
    }

    public static void main(String[] args) {
        String[] catalogo = {"Audífonos", "Bocina", "Cargador", "Celular", "Laptop", "Monitor", "Teclado"};
        String producto = "Celular";

        int resultado = buscarProducto(catalogo, producto, 0, catalogo.length - 1);

        if (resultado != -1) {
            System.out.println("Producto encontrado en la posición: " + resultado);
        } else {
            System.out.println("Producto no disponible en el catálogo.");
        }
    }
}
