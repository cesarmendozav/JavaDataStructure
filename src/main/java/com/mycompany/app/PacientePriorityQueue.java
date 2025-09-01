package com.mycompany.app;

import java.util.Comparator;

class Paciente {
    String nombre; int prioridad; // 1 = más urgente
    Paciente(String n, int p) { nombre = n; prioridad = p; }
    public String toString() { return nombre + "(p=" + prioridad + ")"; }
}

public class PacientePriorityQueue {
    public static void main(String[] args) {
        MinHeapPriorityQueue<Paciente> pq = new MinHeapPriorityQueue<>(Comparator.comparingInt(p -> p.prioridad));
        pq.add(new Paciente("María", 1));
        pq.add(new Paciente("Luis", 2));
        pq.add(new Paciente("Ana", 3));

        while (!pq.isEmpty()) {
            System.out.println("Atendiendo: " + pq.poll());
        }
    }
}
