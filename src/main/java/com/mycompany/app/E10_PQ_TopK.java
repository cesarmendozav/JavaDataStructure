package com.mycompany.app;

import java.util.*;

public class E10_PQ_TopK {
    public static List<Integer> topK(PriorityQueue<Integer> maxHeap, int k) {
        List<Integer> res = new ArrayList<>();
        topKRec(maxHeap, k, res);
        return res;
    }

    private static void topKRec(PriorityQueue<Integer> heap, int k, List<Integer> res) {
        if (k == 0 || heap.isEmpty()) return;          // base
        int top = heap.poll();                         // extrae mayor
        res.add(top);
        topKRec(heap, k-1, res);                      // sigue
        heap.offer(top);                               // restaura al subir
    }

    public static void main(String[] args) {
        // Crear un max-heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(Arrays.asList(10, 4, 7, 1, 15, 3, 8));

        int k = 3;
        System.out.println("Heap original: " + pq);
        List<Integer> mayores = topK(pq, k);
        System.out.println("Top " + k + " elementos: " + mayores);
        System.out.println("Heap después: " + pq); // El heap debe quedar igual
    }
}

