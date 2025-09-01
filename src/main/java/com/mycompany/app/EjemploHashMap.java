package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;

public class EjemploHashMap {
public static void main(String[] args) {
// Capacidad inicial 16; carga 0.75 (por defecto).
// Elige capacidad mayor si esperas muchos elementos para reducir colisiones.
Map<String, String> chats = new HashMap<>();

// 1) Insertar (put)
chats.put("+525522334455", "Chat con Ana");
chats.put("+525511223344", "Chat con Luis");
chats.put("+525588991122", "Chat con Sofía");

// 2) Consultar (get)
System.out.println("Chat Ana: " + chats.get("+525522334455"));

// 3) Existencia
System.out.println("¿Existe Luis? " + chats.containsKey("+525511223344"));

// 4) Actualizar (put sobre la misma llave sobrescribe el valor)
chats.put("+525522334455", "Chat con Ana (actualizado)");

// 5) Eliminar
chats.remove("+525588991122");

// 6) Recorrer (NO hay orden garantizado)
System.out.println("=== Todos los chats ===");
chats.forEach((k, v) -> System.out.println(k + " -> " + v));

// 7) Tamaño
System.out.println("Total: " + chats.size());
}
}