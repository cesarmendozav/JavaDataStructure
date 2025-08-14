package com.mycompany.app;

// SpotifyCircularRepeatAllDemo.java
import java.util.Objects;

public class SpotifyCircularRepeatAllDemo {

    /* ===== Modelo ===== */
    static class Song {
        private final String title;
        private final String artist;
        private final double minutes;

        public Song(String title, String artist, double minutes) {
            this.title  = Objects.requireNonNull(title);
            this.artist = Objects.requireNonNull(artist);
            this.minutes = minutes;
        }
        public String getTitle() { return title; }

        @Override
        public String toString() {
            return "🎵 " + title + " — " + artist + " (" + minutes + " min)";
        }
    }

    /* ===== Nodo doble circular ===== */
    static class Node {
        Song data;
        Node prev, next;
        Node(Song s) { this.data = s; }
    }

    /* ===== Playlist circular tipo Spotify (Repeat All) ===== */
    static class SpotifyCircularPlaylist {
        private Node head, tail, current;
        private int size;
        private boolean repeatOne = false; // Repeat 1 canción (como en Spotify)

        public boolean isEmpty() { return head == null; }
        public int size() { return size; }

        /* Mostrar UNA vuelta completa desde head (evitar bucles infinitos) */
        public void showOneLoopFromHead() {
            if (isEmpty()) { System.out.println("(Playlist vacía)"); return; }
            Node it = head;
            do {
                System.out.println(it.data + (it == current ? "  ⬅️ (sonando)" : ""));
                it = it.next;
            } while (it != head);
        }

        /* -------- Inserciones -------- */

        // Add to Queue (al final)
        public void enqueueEnd(Song s) {
            Node n = new Node(s);
            if (isEmpty()) {
                head = tail = current = n;
                n.next = n.prev = n;
            } else {
                n.prev = tail;
                n.next = head;
                tail.next = n;
                head.prev = n;
                tail = n;
            }
            size++;
        }

        // Play Next (“A continuación”): después de la actual
        public void enqueueNext(Song s) {
            if (isEmpty()) { enqueueEnd(s); return; }
            Node n = new Node(s);
            n.next = current.next;
            n.prev = current;
            current.next.prev = n;
            current.next = n;
            if (current == tail) tail = n;
            size++;
        }

        /* -------- Reproducción -------- */

        public Song nowPlaying() {
            return current == null ? null : current.data;
        }

        public Song play() {
            return nowPlaying();
        }

        public Song next() {
            if (current == null) return null;
            if (!repeatOne) current = current.next; // en circular siempre existe
            return current.data;
        }

        public Song prev() {
            if (current == null) return null;
            if (!repeatOne) current = current.prev;
            return current.data;
        }

        public void toggleRepeatOne(boolean value) {
            this.repeatOne = value;
        }

        /* -------- Búsqueda / Eliminación -------- */

        public Node findByTitle(String title) {
            if (isEmpty()) return null;
            Node it = head;
            do {
                if (it.data.getTitle().equals(title)) return it;
                it = it.next;
            } while (it != head);
            return null;
        }

        // Eliminar la canción actual (p. ej. “Quitar de la cola”)
        public boolean removeCurrent() {
            if (isEmpty()) return false;

            if (size == 1) {
                head = tail = current = null;
                size = 0;
                return true;
            }

            Node toRemove = current;
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;

            if (toRemove == head) head = toRemove.next;
            if (toRemove == tail) tail = toRemove.prev;

            current = toRemove.next; // avanzar a la que sigue
            toRemove.prev = toRemove.next = null;
            size--;
            return true;
        }

        // Eliminar por título (primera coincidencia)
        public boolean removeByTitle(String title) {
            Node target = findByTitle(title);
            if (target == null) return false;

            if (size == 1) {
                head = tail = current = null;
                size = 0;
                return true;
            }

            target.prev.next = target.next;
            target.next.prev = target.prev;

            if (target == head) head = target.next;
            if (target == tail) tail = target.prev;
            if (target == current) current = target.next;

            target.prev = target.next = null;
            size--;
            return true;
        }

        // Saltar N canciones (positivo: next; negativo: prev)
        public Song skip(int n) {
            if (current == null) return null;
            if (repeatOne || n == 0) return current.data;
            int steps = Math.abs(n);
            if (n > 0) while (steps-- > 0) current = current.next;
            else       while (steps-- > 0) current = current.prev;
            return current.data;
        }
    }

    /* ===== Demo: simulando flujo de Spotify ===== */
    public static void main(String[] args) {
        SpotifyCircularPlaylist q = new SpotifyCircularPlaylist();

        // Cargar playlist inicial (Repeat All implícito por ser circular)
        q.enqueueEnd(new Song("Blinding Lights", "The Weeknd", 3.2));
        q.enqueueEnd(new Song("Shape of You",   "Ed Sheeran", 4.0));
        q.enqueueEnd(new Song("As It Was",      "Harry Styles", 2.5));
        q.enqueueEnd(new Song("Flowers",        "Miley Cyrus", 3.2));

        System.out.println("📀 Playlist (una vuelta desde el inicio):");
        q.showOneLoopFromHead();
        System.out.println("Total: " + q.size());

        // Play
        System.out.println("\n▶️ Reproducir actual:");
        System.out.println(q.play());

        // Usuario toca Next varias veces (observa el wrap automático)
        System.out.println("\n⏭️ Next x5 (se notará el ciclo):");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Next " + i + ": " + q.next());
        }

        // Usuario pulsa "Play Next" en una canción
        System.out.println("\n➕ Play Next (A continuación): 'Levitating'");
        q.enqueueNext(new Song("Levitating", "Dua Lipa", 3.4));
        System.out.println("Estado (una vuelta):");
        q.showOneLoopFromHead();

        // Usuario pulsa "Add to Queue" en otra canción (al final)
        System.out.println("\n➕ Add to Queue (al final): 'Anti-Hero'");
        q.enqueueEnd(new Song("Anti-Hero", "Taylor Swift", 3.2));
        System.out.println("Estado (una vuelta):");
        q.showOneLoopFromHead();

        // Usuario “quita de la cola” la canción que está sonando
        System.out.println("\n🗑️ Quitar la que está sonando:");
        q.removeCurrent();
        System.out.println("Ahora sonando: " + q.nowPlaying());

        // Usuario activa Repeat One
        System.out.println("\n🔁 Activar Repeat One y presionar Next x2:");
        q.toggleRepeatOne(true);
        System.out.println("Next 1: " + q.next()); // no cambia
        System.out.println("Next 2: " + q.next()); // no cambia

        // Desactivar Repeat One y retroceder
        System.out.println("\n🔁 Desactivar Repeat One y presionar Previous:");
        q.toggleRepeatOne(false);
        System.out.println("Previous: " + q.prev());

        // Saltar N (positivo = adelante, negativo = atrás)
        System.out.println("\n⏭️⏮️ Saltar +3, luego -2:");
        System.out.println("skip(+3): " + q.skip(3));
        System.out.println("skip(-2): " + q.skip(-2));

        System.out.println("\n📀 Estado final (una vuelta):");
        q.showOneLoopFromHead();
    }
}
