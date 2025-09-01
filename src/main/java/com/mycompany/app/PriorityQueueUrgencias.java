package com.mycompany.app;

import java.util.Comparator;
import java.util.PriorityQueue;

class Paciente {
    String nombre;
    int prioridad; // 1 = más urgente
    Paciente(String n, int p) { this.nombre = n; this.prioridad = p; }
    public String toString() { return nombre + " (p=" + prioridad + ")"; }
}

public class PriorityQueueUrgencias {
    public static void main(String[] args) {
        PriorityQueue<Paciente> pq =
            new PriorityQueue<>(Comparator.comparingInt(p -> p.prioridad));

        pq.add(new Paciente("María", 1)); // emergencia
        pq.add(new Paciente("Luis", 2));  // urgente
        pq.add(new Paciente("Ana", 3));   // consulta

        while (!pq.isEmpty()) {
            System.out.println("Atendiendo: " + pq.poll());
        }
    }
}
