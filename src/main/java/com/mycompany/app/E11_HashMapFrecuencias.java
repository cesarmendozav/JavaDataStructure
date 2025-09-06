package com.mycompany.app;

import java.util.*;

public class E11_HashMapFrecuencias {
    public static Map<Integer, Integer> frecuencias(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        fill(a, 0, map);
        return map;
    }

    private static void fill(int[] a, int i, Map<Integer, Integer> map) {
        if (i == a.length) return;                 // base
        map.merge(a[i], 1, Integer::sum);          // usa API estándar
        fill(a, i+1, map);                         // recursión
    }

    public static void main(String[] args) {
        int[] arreglo = {1, 2, 2, 3, 1, 4, 2, 5, 3, 1};
        Map<Integer, Integer> frecs = frecuencias(arreglo);
        System.out.println("Frecuencias: " + frecs);
    }
}
