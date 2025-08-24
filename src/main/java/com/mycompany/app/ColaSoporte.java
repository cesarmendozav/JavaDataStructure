package com.mycompany.app;

import java.util.LinkedList;
import java.util.Queue;

class Usuario {
String nombre;
public Usuario(String n) { nombre = n; }
public String toString() { return nombre; }
}

public class ColaSoporte {
public static void main(String[] args) {
Queue<Usuario> soporte = new LinkedList<>(); // Create

// Push
soporte.add(new Usuario("Ana"));
soporte.add(new Usuario("Luis"));
soporte.add(new Usuario("Carla"));

System.out.println("Usuarios en espera: " + soporte);

// Pop
System.out.println("Atendiendo (pop): " + soporte.poll());
System.out.println("Usuarios restantes: " + soporte);

// Peek
System.out.println("Siguiente en la fila: " + soporte.peek());

// isEmpty
System.out.println("¿Cola vacía?: " + soporte.isEmpty());
}
}

