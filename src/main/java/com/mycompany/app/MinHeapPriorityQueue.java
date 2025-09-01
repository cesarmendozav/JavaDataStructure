package com.mycompany.app;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeapPriorityQueue<T> {
    private final ArrayList<T> heap = new ArrayList<>();
    private final Comparator<? super T> comp;

    public MinHeapPriorityQueue(Comparator<? super T> comp) { this.comp = comp; }

    public boolean isEmpty() { return heap.isEmpty(); }
    public int size() { return heap.size(); }
    public T peek() { return heap.isEmpty() ? null : heap.get(0); }

    public void add(T value) {
        heap.add(value);              // insertar al final
        siftUp(heap.size() - 1);      // flotar
    }

    public T poll() {
        if (heap.isEmpty()) return null;
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return result;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (comp.compare(heap.get(i), heap.get(parent)) >= 0) break;
            swap(i, parent);
            i = parent;
        }
    }

    private void siftDown(int i) {
        int n = heap.size();
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, smallest = i;
            if (left < n && comp.compare(heap.get(left), heap.get(smallest)) < 0) smallest = left;
            if (right < n && comp.compare(heap.get(right), heap.get(smallest)) < 0) smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
}
