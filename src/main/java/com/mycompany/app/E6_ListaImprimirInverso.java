package com.mycompany.app;

class Nodo {
    int val; Nodo next;
    Nodo(int v) { val = v; }
}

public class E6_ListaImprimirInverso {
    public static void printReverse(Nodo head) {
        if (head == null) return;              // base
        printReverse(head.next);               // baja
        System.out.print(head.val + " ");      // sube
    }

    public static void main(String[] args) {
        // Crear una lista: 1 -> 2 -> 3 -> 4 -> 5
        Nodo head = new Nodo(1);
        head.next = new Nodo(2);
        head.next.next = new Nodo(3);
        head.next.next.next = new Nodo(4);
        head.next.next.next.next = new Nodo(5);

        System.out.print("Lista en orden inverso: ");
        printReverse(head);
        System.out.println();
    }
}