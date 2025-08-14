package com.mycompany.app;


// SpotifyPlaylistDemo.java
import java.util.Objects;

public class SpotifyPlaylistDemo {

    /* =========================
       1) Modelo de dominio
       ========================= */
    static class Cancion {
        private final String titulo;
        private final String artista;
        private final double duracionMin;

        public Cancion(String titulo, String artista, double duracionMin) {
            this.titulo = Objects.requireNonNull(titulo);
            this.artista = Objects.requireNonNull(artista);
            this.duracionMin = duracionMin;
        }

        public String getTitulo()  { return titulo; }
        public String getArtista() { return artista; }
        public double getDuracionMin() { return duracionMin; }

        @Override
        public String toString() {
            return "🎵 " + titulo + " - " + artista + " (" + duracionMin + " min)";
        }
    }

    /* =========================
       2) Nodo de la lista
       ========================= */
    static class Nodo {
        Cancion dato;   // la canción
        Nodo next;      // referencia al siguiente nodo

        Nodo(Cancion dato) {
            this.dato = dato;
            this.next = null;
        }
    }

    /* =========================
       3) Lista simplemente enlazada
       ========================= */
    static class Playlist {
        private Nodo head;  // primera canción
        private Nodo tail;  // última canción (para insertar en O(1), opcional pero útil)
        private int size;   // contador de elementos

        public Playlist() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean estaVacia() {
            return head == null;
        }

        public int tamanio() {
            return size;
        }

        /**
         * Inserta al final.
         * Con tail, es O(1). Sin tail, sería O(n) porque habría que recorrer hasta el último nodo.
         */
        public void agregarAlFinal(Cancion c) {
            Nodo nuevo = new Nodo(c);
            if (estaVacia()) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.next = nuevo; // enlazo el último con el nuevo
                tail = nuevo;      // actualizo tail
            }
            size++;
        }

        /**
         * (Opcional didáctico) Inserta al inicio. Útil para comparar complejidades.
         * Operación O(1).
         */
        public void agregarAlInicio(Cancion c) {
            Nodo nuevo = new Nodo(c);
            if (estaVacia()) {
                head = nuevo;
                tail = nuevo;
            } else {
                nuevo.next = head;
                head = nuevo;
            }
            size++;
        }

        /**
         * Recorre la lista y muestra cada canción.
         * Complejidad O(n).
         */
        public void mostrar() {
            if (estaVacia()) {
                System.out.println("(Playlist vacía)");
                return;
            }
            Nodo actual = head;
            while (actual != null) {
                System.out.println(actual.dato);
                actual = actual.next;
            }
        }

        /**
         * Busca la primera canción cuyo título coincida exactamente (case sensitive).
         * Retorna la referencia a la Cancion o null si no existe.
         * Complejidad O(n).
         */
        public Cancion buscarPorTitulo(String titulo) {
            Nodo actual = head;
            while (actual != null) {
                if (actual.dato.getTitulo().equals(titulo)) {
                    return actual.dato;
                }
                actual = actual.next;
            }
            return null;
        }

        /**
         * (Opcional para clase) Elimina la primera ocurrencia por título.
         * Devuelve true si eliminó; false si no encontró.
         * Complejidad O(n).
         */
        public boolean eliminarPorTitulo(String titulo) {
            if (estaVacia()) return false;

            // Caso: head debe eliminarse
            if (head.dato.getTitulo().equals(titulo)) {
                head = head.next;
                if (head == null) { // la lista quedó vacía
                    tail = null;
                }
                size--;
                return true;
            }

            // Caso general: buscar el nodo anterior al que se elimina
            Nodo actual = head;
            while (actual.next != null && !actual.next.dato.getTitulo().equals(titulo)) {
                actual = actual.next;
            }
            if (actual.next == null) {
                return false; // no se encontró
            }

            // "Saltar" el nodo objetivo
            Nodo nodoAEliminar = actual.next;
            actual.next = nodoAEliminar.next;

            // Si eliminamos el último, actualizar tail
            if (nodoAEliminar == tail) {
                tail = actual;
            }

            size--;
            return true;
        }
    }

    /* =========================
       4) Demo en main
       ========================= */
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        // Simulación: agregar canciones en orden (como en una cola de reproducción)
        playlist.agregarAlFinal(new Cancion("Blinding Lights", "The Weeknd", 3.2));
        playlist.agregarAlFinal(new Cancion("Shape of You", "Ed Sheeran", 4.0));
        playlist.agregarAlFinal(new Cancion("As It Was", "Harry Styles", 2.5));

        System.out.println("📀 Playlist Spotify (orden de reproducción):");
        playlist.mostrar();
        System.out.println("Total canciones: " + playlist.tamanio());

        // Buscar por título
        System.out.println("\n🔎 Buscando 'Shape of You'...");
        Cancion hallada = playlist.buscarPorTitulo("Shape of You");
        System.out.println(hallada != null ? "Encontrada: " + hallada : "No encontrada");

        // Eliminar por título (opcional para mostrar modificación de enlaces)
        System.out.println("\n🗑️ Eliminando 'Shape of You'...");
        boolean elimino = playlist.eliminarPorTitulo("Shape of You");
        System.out.println(elimino ? "Eliminada correctamente." : "No se encontró para eliminar.");

        System.out.println("\n📀 Playlist tras eliminar:");
        playlist.mostrar();
        System.out.println("Total canciones: " + playlist.tamanio());
    }
}
