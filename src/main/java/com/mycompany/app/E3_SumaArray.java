package com.mycompany.app;

public class E3_SumaArray {
    public static int suma(int[] a, int l, int r) {
        if (l > r) return 0;                   // base: rango vacío
        if (l == r) return a[l];               // base: un elemento
        int mid = (l + r) >>> 1;
        return suma(a, l, mid) + suma(a, mid+1, r);
    }

    public static void main(String[] args) {
        int[] arreglo = {1, 2, 3, 4, 5};
        int resultado = suma(arreglo, 0, arreglo.length - 1);
        System.out.println("La suma del arreglo es: " + resultado);
    }
}