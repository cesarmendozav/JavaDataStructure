package com.mycompany.app;

import java.util.*;

/**
 * SOLUCIÓN CON RECURSIVIDAD OBLIGATORIA
 * Reto 1: Caja de supermercado (cola de clientes, FIFO)
 *
 * Estructura base: Queue<Customer> usando ArrayDeque.
 * Métodos recursivos obligatorios en esta solución:
 *  - printQueueRecursive()               -> imprime la fila SIN bucles.
 *  - serveNextNRecursive(int n)          -> atiende recursivamente a los próximos n clientes.
 *  - findByNameRecursive(String name)    -> busca por nombre recursivamente (sin bucles).
 *  - reverseQueueRecursive()             -> invierte la cola usando solo recursión.
 *
 * Nota: Para imprimir sin alterar el orden final, el método recorre una vuelta completa
 *       rotando la cola y deteniéndose después de 'size' pasos.
 */
public class SupermarketQueueRecursiveSolution {

    private final Queue<Customer> queue = new ArrayDeque<>();

    public static class Customer {
        public final int id;
        public final String nombre;
        public final List<String> carritoConArticulos;

        public Customer(int id, String nombre, List<String> carritoConArticulos) {
            this.id = id;
            this.nombre = nombre;
            this.carritoConArticulos = carritoConArticulos;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    /** Encola un cliente al final (FIFO). */
    public void enqueue(Customer c) {
        queue.add(c);
    }

    /** Atiende (elimina) y devuelve el primer cliente de la fila, o null si vacía. */
    public Customer dequeue() {
        return queue.poll();
    }

    /** ¿La fila está vacía? */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /** Tamaño actual de la fila. */
    public int size() {
        return queue.size();
    }

    /** ---------- RECURSIVIDAD OBLIGATORIA ---------- */

    /** Imprime la fila con formato [A, B, C] SIN bucles, preservando el orden final. */
    public void printQueueRecursive() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fila actual: [");
        int n = size();
        printQueueRecursiveHelper(n, sb);
        if (n > 0) {
            // eliminar última coma y espacio
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    private void printQueueRecursiveHelper(int remaining, StringBuilder sb) {
        if (remaining == 0) return;
        Customer c = queue.poll();           // tomar frente
        sb.append(c.nombre).append(", ");    // "visitar"
        queue.add(c);                        // reinsertar al final
        printQueueRecursiveHelper(remaining - 1, sb);
    }

    /** Atiende recursivamente a los próximos n clientes, imprimiendo cada atención. */
    public void serveNextNRecursive(int n) {
        if (n <= 0 || queue.isEmpty()) return;
        Customer atendido = dequeue();
        System.out.println("Atendiendo: " + (atendido != null ? atendido.nombre : "Nadie"));
        printQueueRecursive();
        serveNextNRecursive(n - 1);
    }

    /** Busca por nombre recursivamente (una vuelta completa, sin bucles). */
    public Customer findByNameRecursive(String name) {
        int n = size();
        return findByNameRecursiveHelper(name, n);
    }

    private Customer findByNameRecursiveHelper(String name, int remaining) {
        if (remaining == 0) return null;
        Customer c = queue.poll();
        queue.add(c);
        if (c.nombre.equalsIgnoreCase(name)) return c;
        return findByNameRecursiveHelper(name, remaining - 1);
    }

    /** Invierte la cola usando únicamente recursión. */
    public void reverseQueueRecursive() {
        if (queue.isEmpty()) return;
        Customer front = dequeue();
        reverseQueueRecursive();
        queue.add(front);
    }

    /** Demostración en consola */
    public static void main(String[] args) {
        SupermarketQueueRecursiveSolution box = new SupermarketQueueRecursiveSolution();

        box.enqueue(new Customer(1, "Ana", Arrays.asList("Leche", "Pan")));
        box.enqueue(new Customer(2, "Luis", Arrays.asList("Huevos")));
        box.enqueue(new Customer(3, "Carla", Arrays.asList("Cereal", "Fruta")));
        box.enqueue(new Customer(4, "Pedro", Arrays.asList("Agua", "Galletas")));

        // Impresión recursiva
        box.printQueueRecursive(); // [Ana, Luis, Carla, Pedro]

        // Atender dos clientes recursivamente
        box.serveNextNRecursive(2);

        // Búsqueda recursiva
        System.out.println("Buscar 'Carla': " + box.findByNameRecursive("Carla"));
        System.out.println("Buscar 'Mario': " + box.findByNameRecursive("Mario"));

        // Invertir la cola recursivamente
        System.out.println("Invertir la cola de forma recursiva:");
        box.reverseQueueRecursive();
        box.printQueueRecursive();

        // Atender el resto recursivamente
        box.serveNextNRecursive(10); // maneja vacío de forma segura
    }
}