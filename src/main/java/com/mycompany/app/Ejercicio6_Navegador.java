package com.mycompany.app;

import java.util.Stack;

public class Ejercicio6_Navegador {
    Stack<Stack<String>> tabs = new Stack<>();

    public void nuevaTab() {
        tabs.push(new Stack<>());
    }

    public void visitar(String pagina) {
        if (tabs.isEmpty()) nuevaTab();
        tabs.peek().push(pagina);
    }

    public String cerrarTab() {
        return tabs.isEmpty() ? "No hay tabs" : "Cerrando tab con " + tabs.pop().size() + " páginas.";
    }

    public String paginaActual() {
        if (tabs.isEmpty() || tabs.peek().isEmpty()) return "No hay página abierta";
        return "Actual: " + tabs.peek().peek();
    }

    public static void main(String[] args) {
        Ejercicio6_Navegador chrome = new Ejercicio6_Navegador();
        chrome.nuevaTab();
        chrome.visitar("google.com");
        chrome.visitar("youtube.com");

        System.out.println(chrome.paginaActual());
        System.out.println(chrome.cerrarTab());
    }
}
