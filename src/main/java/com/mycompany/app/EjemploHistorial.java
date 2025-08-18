package com.mycompany.app;
import java.util.Stack;

public class EjemploHistorial {
    public static void main(String[] args) {
        // Create()
        Stack<String> historial = new Stack<>();

        // Push()
        historial.push("www.google.com");
        historial.push("www.youtube.com");
        historial.push("www.instagram.com");

        // Size()
        System.out.println("Páginas en historial: " + historial.size());

        // Peek()
        System.out.println("Página actual: " + historial.peek());

        // Pop()
        System.out.println("Regresando desde: " + historial.pop());
        System.out.println("Ahora estoy en: " + historial.peek());

        // isEmpty()
        System.out.println("¿Historial vacío? " + historial.isEmpty());
    }
}
