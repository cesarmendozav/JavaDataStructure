package com.mycompany.app;

public class Ejercicio1_PilaArray {
    private String[] stack;
    private int top;
    private int capacity;

    public Ejercicio1_PilaArray(int capacity) {
        this.capacity = capacity;
        this.stack = new String[capacity];
        this.top = -1; // pila vacía
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void push(String page) {
        if (top == capacity - 1) {
            System.out.println("⚠️ La pila está llena.");
            return;
        }
        stack[++top] = page;
    }

    public String pop() {
        if (isEmpty()) {
            return "⚠️ La pila está vacía.";
        }
        return stack[top--];
    }

    public String peek() {
        if (isEmpty()) {
            return "⚠️ La pila está vacía.";
        }
        return stack[top];
    }

    public static void main(String[] args) {
        Ejercicio1_PilaArray historial = new Ejercicio1_PilaArray(5);

        historial.push("www.google.com");
        historial.push("www.youtube.com");
        historial.push("www.instagram.com");

        System.out.println("Página actual: " + historial.peek());
        System.out.println("Atrás: " + historial.pop());
        System.out.println("Nueva página actual: " + historial.peek());
    }
}