package com.mycompany.app;

import java.util.*;

public class E9_QueueInvertirK {
    public static void invertirK(Queue<Integer> q, int k) {
        if (k == 0 || q.isEmpty()) return;     // base
        int x = q.remove();
        invertirK(q, k-1);
        q.add(x);
    }

    // Rota el resto para que los k invertidos queden delante
    public static void aplicar(Queue<Integer> q, int k) {
        invertirK(q, k);
        int n = q.size() - k;
        for (int i = 0; i < n; i++) q.add(q.remove());
    }

    public static void main(String[] args) {
        Queue<Integer> cola = new LinkedList<>();
        for (int i = 1; i <= 7; i++) cola.add(i);

        int k = 4;
        System.out.println("Cola original: " + cola);
        aplicar(cola, k);
        System.out.println("Cola después de invertir los primeros " + k + " elementos: " + cola);
    }
}

