package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class LlaveMala { // Fuerza colisiones: todos los hash son iguales
private final String id;

LlaveMala(String id) { this.id = id; }

@Override
public int hashCode() {
return 42; // Mismo hash para TODAS las llaves -> colisión garantizada
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof LlaveMala)) return false;
LlaveMala that = (LlaveMala) o;
return Objects.equals(this.id, that.id); // verdadera identidad
}

@Override public String toString() { return "Llave(" + id + ")"; }
}

public class ColisionesForzadas {
public static void main(String[] args) {
Map<LlaveMala, String> mapa = new HashMap<>();

LlaveMala ana = new LlaveMala("Ana");
LlaveMala luis = new LlaveMala("Luis");
LlaveMala sofia = new LlaveMala("Sofía");

// 1) Insertar: todas van al MISMO bucket
mapa.put(ana, "Chat con Ana");
mapa.put(luis, "Chat con Luis");
mapa.put(sofia, "Chat con Sofía");

// 2) Buscar: el mapa calcula el mismo índice, recorre la cadena y usa equals
System.out.println(mapa.get(ana)); // "Chat con Ana"
System.out.println(mapa.get(luis)); // "Chat con Luis"
System.out.println(mapa.get(sofia)); // "Chat con Sofía"

// 3) Actualizar una de las entradas en el mismo bucket
mapa.put(new LlaveMala("Ana"), "Chat con Ana (actualizado)");
System.out.println(mapa.get(ana)); // actualizado

// 4) Mostrar para ver que “conviven” en el mismo bucket sin pisarse
mapa.forEach((k, v) -> System.out.println(k + " -> " + v));
}
}
