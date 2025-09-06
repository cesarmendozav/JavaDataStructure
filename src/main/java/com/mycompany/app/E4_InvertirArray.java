package com.mycompany.app;

import java.util.Arrays;

public class E4_InvertirArray {
    public static void invertir(int[] a, int i, int j) {
        if (i >= j) return;                    // base
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        invertir(a, i+1, j-1);                 // recursión hacia el centro
    }

    public static void main(String[] args) {
        int[] arreglo = {1, 2, 3, 4, 5};
        System.out.println("Arreglo original: " + Arrays.toString(arreglo));
        invertir(arreglo, 0, arreglo.length - 1);
        System.out.println("Arreglo invertido: " + Arrays.toString(arreglo));
    }
}