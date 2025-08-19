package com.mycompany.app;

import java.util.Stack;

public class Ejercicio4_EditorTexto {
    Stack<String> undo = new Stack<>();
    Stack<String> redo = new Stack<>();

    public void escribir(String accion) {
        undo.push(accion);
        redo.clear(); // se invalida lo que había para rehacer
    }

    public String deshacer() {
        if (undo.isEmpty()) return "Nada que deshacer";
        String accion = undo.pop();
        redo.push(accion);
        return "Deshacer: " + accion;
    }

    public String rehacer() {
        if (redo.isEmpty()) return "Nada que rehacer";
        String accion = redo.pop();
        undo.push(accion);
        return "Rehacer: " + accion;
    }

    public static void main(String[] args) {
        Ejercicio4_EditorTexto editor = new Ejercicio4_EditorTexto();
        editor.escribir("Escribir Hola");
        editor.escribir("Negrita en Hola");
        System.out.println(editor.deshacer());
        System.out.println(editor.rehacer());
    }
}