package com.mycompany.app;

public class E5_BinarySearch {
    public static int buscar(int[] a, int l, int r, int x) {
        if (l > r) return -1;                  // base
        int m = (l + r) >>> 1;
        if (a[m] == x) return m;
        if (a[m] > x) return buscar(a, l, m-1, x);
        return buscar(a, m+1, r, x);
    }

    public static void main(String[] args) {
        int[] arreglo = {1, 3, 5, 7, 9, 11, 13};
        int x = 7;
        int resultado = buscar(arreglo, 0, arreglo.length - 1, x);
        if (resultado != -1) {
            System.out.println("Elemento " + x + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("Elemento " + x + " no encontrado.");
        }
    }
}
