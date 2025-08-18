package com.mycompany.app;

public class PilaStories {

    // Clase Story (nodo)
    static class Story {
        String titulo;
        String usuario;
        int duracionSegundos;
        Story next; // referencia al nodo anterior (debajo en la pila)

        public Story(String titulo, String usuario, int duracionSegundos) {
            this.titulo = titulo;
            this.usuario = usuario;
            this.duracionSegundos = duracionSegundos;
            this.next = null;
        }
    }

    // Clase Pila (Stack)
    static class StoryStack {
        private Story top; // apunta al tope de la pila
        private int size;

        public StoryStack() {
            top = null;
            size = 0;
        }

        // Insertar story (push)
        public void push(String titulo, String usuario, int duracionSegundos) {
            Story nueva = new Story(titulo, usuario, duracionSegundos);
            nueva.next = top; // el nuevo apunta al anterior tope
            top = nueva;      // ahora top es la nueva story
            size++;
        }

        // Eliminar story (pop)
        public Story pop() {
            if (isEmpty()) {
                System.out.println("⚠️ No hay stories para ver.");
                return null;
            }
            Story vista = top;
            top = top.next; // saltamos al siguiente nodo
            size--;
            return vista;
        }

        // Ver story actual sin eliminar (peek)
        public Story peek() {
            return top;
        }

        // Verificar si está vacía
        public boolean isEmpty() {
            return top == null;
        }

        // Número de stories
        public int size() {
            return size;
        }

        // Mostrar todas las stories en la pila
        public void mostrarStories() {
            if (isEmpty()) {
                System.out.println("⚠️ No hay stories en la pila.");
                return;
            }
            System.out.println("📌 Stories en la pila (de la más nueva a la más vieja):");
            Story actual = top;
            while (actual != null) {
                System.out.println(" - " + actual.titulo + " de @" + actual.usuario +
                        " (" + actual.duracionSegundos + "s)");
                actual = actual.next;
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        StoryStack stories = new StoryStack();

        // Agregamos algunas stories (push)
        stories.push("Desayuno", "juan123", 15);
        stories.push("Gym Time", "maria_g", 20);
        stories.push("Viaje a la playa", "alex99", 10);

        // Mostramos el estado actual de la pila
        stories.mostrarStories();

        // Ver la story actual sin eliminar (peek)
        System.out.println("\n👀 Siguiente story: " + stories.peek().titulo);

        // Consumir stories (pop)
        System.out.println("\n▶️ Viendo stories...");
        while (!stories.isEmpty()) {
            Story vista = stories.pop();
            System.out.println("Reproduciendo: " + vista.titulo + " de @" + vista.usuario);
        }

        // Intentamos mostrar después de vaciar
        stories.mostrarStories();
    }
}
