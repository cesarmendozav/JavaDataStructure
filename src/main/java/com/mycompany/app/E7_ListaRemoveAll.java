package com.mycompany.app;

class Nodo {
    int val;
    Nodo next;
    Nodo(int v) { val = v; }
}

public class E7_ListaRemoveAll {
    public static Nodo removeAll(Nodo head, int x) {
        if (head == null) return null;         // base
        head.next = removeAll(head.next, x);   // procesa resto
        return (head.val == x) ? head.next : head;
    }

    public static void printList(Nodo head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Crear una lista: 1 -> 2 -> 3 -> 2 -> 4 -> 2 -> 5
        Nodo head = new Nodo(1);
        head.next = new Nodo(2);
        head.next.next = new Nodo(3);
        head.next.next.next = new Nodo(2);
        head.next.next.next.next = new Nodo(4);
        head.next.next.next.next.next = new Nodo(2);
        head.next.next.next.next.next.next = new Nodo(5);

        System.out.print("Lista original: ");
        printList(head);

        int x = 2;
        head = removeAll(head, x);

        System.out.print("Lista después de eliminar " + x + ": ");
        printList(head);
    }
}