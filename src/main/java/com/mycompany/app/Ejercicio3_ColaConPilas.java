package com.mycompany.app;

import java.util.Stack;

public class Ejercicio3_ColaConPilas {
    Stack<String> pila1 = new Stack<>();
    Stack<String> pila2 = new Stack<>();

    public void enqueue(String pedido) {
        pila1.push(pedido);
    }

    public String dequeue() {
        if (pila2.isEmpty()) {
            while (!pila1.isEmpty()) {
                pila2.push(pila1.pop());
            }
        }
        return pila2.isEmpty() ? "Cola vacía" : pila2.pop();
    }

    public static void main(String[] args) {
        Ejercicio3_ColaConPilas cola = new Ejercicio3_ColaConPilas();
        cola.enqueue("Latte");
        cola.enqueue("Capuccino");
        cola.enqueue("Espresso");

        System.out.println("Atendido: " + cola.dequeue());
        System.out.println("Atendido: " + cola.dequeue());
    }
}