package com.mycompany.app;

import java.util.Stack;

public class E8_InvertirStack {
    // Inserta en el fondo de la pila (recursivo)
    private static void pushFondo(Stack<Integer> st, int x) {
        if (st.isEmpty()) { st.push(x); return; }
        int top = st.pop();
        pushFondo(st, x);
        st.push(top);
    }

    // Invierte la pila
    public static void invertir(Stack<Integer> st) {
        if (st.isEmpty()) return;              // base
        int top = st.pop();
        invertir(st);
        pushFondo(st, top);
    }

    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();
        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);

        System.out.println("Pila original: " + pila);
        invertir(pila);
        System.out.println("Pila invertida: " + pila);
    }
}

