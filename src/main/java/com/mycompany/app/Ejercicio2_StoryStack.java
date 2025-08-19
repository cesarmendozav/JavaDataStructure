package com.mycompany.app;

class Story {
    String titulo;
    Story next;
    Story(String titulo) {
        this.titulo = titulo;
        this.next = null;
    }
}

public class Ejercicio2_StoryStack {
    private Story top;
    private int size;

    public Ejercicio2_StoryStack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(String titulo) {
        Story nueva = new Story(titulo);
        nueva.next = top;
        top = nueva;
        size++;
    }

    public String pop() {
        if (isEmpty()) return "⚠️ No hay stories.";
        String t = top.titulo;
        top = top.next;
        size--;
        return t;
    }

    public String peek() {
        return isEmpty() ? "⚠️ No hay stories." : top.titulo;
    }

    public static void main(String[] args) {
        Ejercicio2_StoryStack stories = new Ejercicio2_StoryStack();
        stories.push("Desayuno");
        stories.push("Gym");
        stories.push("Playa");

        System.out.println("Story actual: " + stories.peek());
        while (!stories.isEmpty()) {
            System.out.println("Viendo: " + stories.pop());
        }
    }
}