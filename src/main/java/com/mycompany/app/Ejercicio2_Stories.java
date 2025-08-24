package com.mycompany.app;

import java.util.Stack;

class Story {
String titulo, usuario;
int duracion;

public Story(String titulo, String usuario, int duracion) {
this.titulo = titulo;
this.usuario = usuario;
this.duracion = duracion;
}

@Override
public String toString() {
return titulo + " de @" + usuario + " (" + duracion + "s)";
}
}

public class Ejercicio2_Stories {
public static void main(String[] args) {
Stack<Story> stories = new Stack<>();

stories.push(new Story("Desayuno", "juan123", 15));
stories.push(new Story("Gym Time", "maria_g", 20));
stories.push(new Story("Viaje a la playa", "alex99", 10));

System.out.println("👀 Próxima story: " + stories.peek());

while (!stories.isEmpty()) {
System.out.println("▶️ Viendo: " + stories.pop());
}
}
}