package com.mycompany.app;

import java.util.LinkedList;
import java.util.Queue;

public class ColaCafeteria {
public static void main(String[] args) {
// Create
Queue<String> pedidos = new LinkedList<>();

// Push
pedidos.add("Café Latte");
pedidos.add("Capuccino");
pedidos.add("Frappé");

System.out.println("Pedidos en cola: " + pedidos);

// Peek
System.out.println("Primer pedido (peek): " + pedidos.peek());

// Pop
System.out.println("Atendido (pop): " + pedidos.poll());
System.out.println("Pedidos restantes: " + pedidos);

// Size
System.out.println("Tamaño actual: " + pedidos.size());

// isEmpty
System.out.println("¿Cola vacía?: " + pedidos.isEmpty());
}
}